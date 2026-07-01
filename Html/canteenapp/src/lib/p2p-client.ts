import Peer, { type DataConnection } from 'peerjs';
import { db, type MenuItem, type Order } from './db';

/* ═══════════════════════════════════════════════════════════════
   P2P Client — WebRTC via PeerJS
   
   The Admin acts as the "host" and listens for incoming connections.
   Users connect by knowing the Admin's CanteenID (via QR code).
   
   Message Protocol:
   - { type: 'MENU_REQUEST' }           → User asks for menu
   - { type: 'MENU_RESPONSE', data }    → Admin sends menu
   - { type: 'ORDER_SUBMIT', data }     → User sends order
   - { type: 'ORDER_ACK', data }        → Admin confirms order
   - { type: 'ORDER_UPDATE', data }     → Admin updates order status
   - { type: 'MENU_UPDATE', data }      → Admin broadcasts menu changes
   ═══════════════════════════════════════════════════════════════ */

export type MessageType =
  | 'MENU_REQUEST'
  | 'MENU_RESPONSE'
  | 'ORDER_SUBMIT'
  | 'ORDER_ACK'
  | 'ORDER_UPDATE'
  | 'MENU_UPDATE'
  | 'PING'
  | 'PONG';

export interface P2PMessage {
  type: MessageType;
  data?: any;
  timestamp: number;
}

type MessageHandler = (message: P2PMessage, conn: DataConnection) => void;

export class P2PClient {
  private peer: Peer | null = null;
  private connections: Map<string, DataConnection> = new Map();
  private messageHandlers: Map<MessageType, MessageHandler[]> = new Map();
  private _isConnected = false;
  private _peerId = '';
  private onStatusChange?: (connected: boolean) => void;

  get isConnected() { return this._isConnected; }
  get peerId() { return this._peerId; }
  get connectionCount() { return this.connections.size; }

  /* ═══ Initialize as Admin (Host) ═══ */
  async initAsHost(canteenId: string): Promise<string> {
    return new Promise((resolve, reject) => {
      // Use canteen ID as the peer ID so it's deterministic (static QR)
      this.peer = new Peer(`canteen-${canteenId}`, {
        debug: 1,
      });

      this.peer.on('open', (id) => {
        this._peerId = id;
        this._isConnected = true;
        this.onStatusChange?.(true);
        console.log('[P2P] Host ready:', id);
        resolve(id);
      });

      this.peer.on('connection', (conn) => {
        this.handleConnection(conn);
      });

      this.peer.on('error', (err) => {
        console.error('[P2P] Error:', err);
        if (err.type === 'unavailable-id') {
          // Peer ID already taken — likely admin is already running
          reject(new Error('Canteen is already running on another device. Close it first.'));
        } else {
          this._isConnected = false;
          this.onStatusChange?.(false);
        }
      });

      this.peer.on('disconnected', () => {
        this._isConnected = false;
        this.onStatusChange?.(false);
        // Try to reconnect
        setTimeout(() => this.peer?.reconnect(), 3000);
      });
    });
  }

  /* ═══ Initialize as User (Connect to Host) ═══ */
  async connectToHost(canteenId: string): Promise<DataConnection> {
    return new Promise((resolve, reject) => {
      this.peer = new Peer(undefined, { debug: 1 });

      this.peer.on('open', () => {
        this._peerId = this.peer!.id;
        const conn = this.peer!.connect(`canteen-${canteenId}`, {
          reliable: true,
        });

        conn.on('open', () => {
          this._isConnected = true;
          this.onStatusChange?.(true);
          this.handleConnection(conn);
          console.log('[P2P] Connected to canteen:', canteenId);
          resolve(conn);
        });

        conn.on('error', (err) => {
          console.error('[P2P] Connection error:', err);
          reject(err);
        });

        // Timeout after 10 seconds
        setTimeout(() => {
          if (!this._isConnected) {
            reject(new Error('Connection timed out. Make sure the canteen is online.'));
          }
        }, 10000);
      });

      this.peer.on('error', (err) => {
        console.error('[P2P] Peer error:', err);
        reject(err);
      });
    });
  }

  /* ═══ Handle a new connection ═══ */
  private handleConnection(conn: DataConnection) {
    this.connections.set(conn.peer, conn);

    conn.on('data', (data) => {
      const message = data as P2PMessage;
      const handlers = this.messageHandlers.get(message.type) || [];
      handlers.forEach((handler) => handler(message, conn));
    });

    conn.on('close', () => {
      this.connections.delete(conn.peer);
      console.log('[P2P] Connection closed:', conn.peer);
    });

    conn.on('error', (err) => {
      console.error('[P2P] Connection error:', err);
      this.connections.delete(conn.peer);
    });
  }

  /* ═══ Send message to a specific peer ═══ */
  send(peerId: string, message: Omit<P2PMessage, 'timestamp'>) {
    const conn = this.connections.get(peerId);
    if (conn?.open) {
      conn.send({ ...message, timestamp: Date.now() });
    }
  }

  /* ═══ Broadcast message to all connected peers ═══ */
  broadcast(message: Omit<P2PMessage, 'timestamp'>) {
    const fullMessage = { ...message, timestamp: Date.now() };
    this.connections.forEach((conn) => {
      if (conn.open) {
        conn.send(fullMessage);
      }
    });
  }

  /* ═══ Register a message handler ═══ */
  on(type: MessageType, handler: MessageHandler) {
    const handlers = this.messageHandlers.get(type) || [];
    handlers.push(handler);
    this.messageHandlers.set(type, handlers);
  }

  /* ═══ Set connection status callback ═══ */
  onConnectionStatusChange(callback: (connected: boolean) => void) {
    this.onStatusChange = callback;
  }

  /* ═══ Disconnect and cleanup ═══ */
  disconnect() {
    this.connections.forEach((conn) => conn.close());
    this.connections.clear();
    this.peer?.destroy();
    this.peer = null;
    this._isConnected = false;
    this._peerId = '';
    this.onStatusChange?.(false);
  }
}

/* ═══ Singleton instance ═══ */
export const p2pClient = new P2PClient();
