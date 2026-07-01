import React, { useEffect, useState } from 'react';
import { db, type Order } from '@/lib/db';
import { useAuth } from '@/context/AuthContext';
import { p2pClient } from '@/lib/p2p-client';
import { soundManager } from '@/lib/sounds';
import { useLiveQuery } from 'dexie-react-hooks';
import { motion, AnimatePresence } from 'framer-motion';
import { Clock, ChefHat, CheckCircle2, Package, Users, IndianRupee, ShoppingBag, Wifi, WifiOff, Bell } from 'lucide-react';

const STATUS_CONFIG = {
  pending: { icon: Clock, label: 'Pending', color: '#f59e0b', bg: 'rgba(245,158,11,0.1)', next: 'preparing' as const },
  preparing: { icon: ChefHat, label: 'Preparing', color: '#3b82f6', bg: 'rgba(59,130,246,0.1)', next: 'ready' as const },
  ready: { icon: CheckCircle2, label: 'Ready', color: '#22c55e', bg: 'rgba(34,197,94,0.1)', next: 'delivered' as const },
  delivered: { icon: Package, label: 'Delivered', color: '#a1a1aa', bg: 'rgba(161,161,170,0.1)', next: null },
};

export function AdminDashboard() {
  const { user } = useAuth();
  const orders = useLiveQuery(() => db.orders.reverse().sortBy('createdAt')) || [];
  const [p2pConnected, setP2pConnected] = useState(false);
  const [connectedUsers, setConnectedUsers] = useState(0);
  const [filter, setFilter] = useState<string>('all');

  // Start P2P host
  useEffect(() => {
    if (!user?.canteenId) return;

    const startHost = async () => {
      try {
        await p2pClient.initAsHost(user.canteenId!);
        setP2pConnected(true);
      } catch (err) {
        console.error('P2P host error:', err);
      }
    };

    // Listen for incoming orders
    p2pClient.on('ORDER_SUBMIT', async (msg) => {
      const order = msg.data as Order;
      // Check if order already exists
      const existing = await db.orders.where('orderId').equals(order.orderId).first();
      if (!existing) {
        await db.orders.add(order);
        // Play notification sound
        soundManager.playOrderNotification();
      }
    });

    p2pClient.on('MENU_REQUEST', async (msg, conn) => {
      const menuItems = await db.menu.toArray();
      p2pClient.send(conn.peer, { type: 'MENU_RESPONSE', data: menuItems });
    });

    p2pClient.onConnectionStatusChange(setP2pConnected);
    startHost();

    return () => { p2pClient.disconnect(); };
  }, [user?.canteenId]);

  const updateOrderStatus = async (orderId: number, newStatus: Order['status']) => {
    await db.orders.update(orderId, { status: newStatus, updatedAt: Date.now() });
    soundManager.playStatusChange();
    const order = await db.orders.get(orderId);
    if (order) {
      p2pClient.broadcast({ type: 'ORDER_UPDATE', data: { orderId: order.orderId, status: newStatus } });
    }
  };

  const filteredOrders = orders.filter((o) => filter === 'all' || o.status === filter);

  const stats = {
    total: orders.length,
    pending: orders.filter((o) => o.status === 'pending').length,
    revenue: orders.filter((o) => o.status !== 'pending').reduce((s, o) => s + o.total, 0),
  };

  return (
    <div className="min-h-screen pb-8">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 pt-6">
        {/* Header */}
        <div className="flex items-center justify-between mb-6">
          <div>
            <h1 className="text-2xl sm:text-3xl font-bold">Dashboard</h1>
            <p className="text-sm text-muted-foreground">Manage orders in real time</p>
          </div>
          <div className={`flex items-center gap-2 px-4 py-2 rounded-xl text-sm font-medium ${p2pConnected ? 'bg-success/10 text-success' : 'bg-destructive/10 text-destructive'}`}>
            {p2pConnected ? <Wifi className="w-4 h-4" /> : <WifiOff className="w-4 h-4" />}
            {p2pConnected ? 'Receiving Orders' : 'Offline'}
          </div>
        </div>

        {/* Stats */}
        <div className="grid grid-cols-1 sm:grid-cols-3 gap-4 mb-6">
          {[
            { label: 'Total Orders', value: stats.total, icon: ShoppingBag, color: '#ff5722' },
            { label: 'Pending', value: stats.pending, icon: Bell, color: '#f59e0b' },
            { label: 'Revenue', value: `₹${stats.revenue}`, icon: IndianRupee, color: '#22c55e' },
          ].map((stat) => (
            <motion.div key={stat.label} initial={{ opacity: 0, y: 20 }} animate={{ opacity: 1, y: 0 }}
              className="glass rounded-2xl p-5 flex items-center gap-4">
              <div className="w-12 h-12 rounded-xl flex items-center justify-center" style={{ background: `${stat.color}15` }}>
                <stat.icon className="w-6 h-6" style={{ color: stat.color }} />
              </div>
              <div>
                <p className="text-xs text-muted-foreground">{stat.label}</p>
                <p className="text-2xl font-bold">{stat.value}</p>
              </div>
            </motion.div>
          ))}
        </div>

        {/* Filters */}
        <div className="flex gap-2 mb-4 overflow-x-auto pb-2">
          {['all', 'pending', 'preparing', 'ready', 'delivered'].map((f) => (
            <button key={f} onClick={() => setFilter(f)}
              className={`px-4 py-2 rounded-xl text-sm font-medium capitalize whitespace-nowrap transition-all ${filter === f ? 'gradient-primary text-white' : 'bg-secondary text-muted-foreground hover:text-foreground'}`}>
              {f}
              {f !== 'all' && ` (${orders.filter((o) => o.status === f).length})`}
            </button>
          ))}
        </div>

        {/* Orders List */}
        <div className="space-y-3">
          <AnimatePresence>
            {filteredOrders.map((order, i) => {
              const statusConfig = STATUS_CONFIG[order.status];
              const StatusIcon = statusConfig.icon;
              return (
                <motion.div key={order.id} layout initial={{ opacity: 0, y: 20 }} animate={{ opacity: 1, y: 0 }}
                  exit={{ opacity: 0, x: -50 }} transition={{ delay: i * 0.03 }}
                  className={`glass rounded-2xl p-5 ${order.status === 'pending' ? 'border-l-4 border-l-warning' : ''}`}>
                  <div className="flex flex-wrap items-center justify-between gap-3 mb-3">
                    <div className="flex items-center gap-3">
                      <div className="w-10 h-10 rounded-xl gradient-primary flex items-center justify-center text-white font-bold text-sm">
                        T{order.tableNumber}
                      </div>
                      <div>
                        <p className="font-semibold">{order.userName}</p>
                        <p className="text-xs text-muted-foreground">{new Date(order.createdAt).toLocaleTimeString()} · #{order.orderId.slice(0, 8)}</p>
                      </div>
                    </div>
                    <div className="flex items-center gap-2">
                      <div className="flex items-center gap-1.5 px-3 py-1.5 rounded-full text-xs font-semibold"
                        style={{ background: statusConfig.bg, color: statusConfig.color }}>
                        <StatusIcon className="w-3.5 h-3.5" />
                        {statusConfig.label}
                      </div>
                      {statusConfig.next && (
                        <button onClick={() => updateOrderStatus(order.id!, statusConfig.next!)}
                          className="px-4 py-1.5 rounded-xl text-xs font-semibold bg-primary/10 text-primary hover:bg-primary/20 transition-colors">
                          → {STATUS_CONFIG[statusConfig.next].label}
                        </button>
                      )}
                    </div>
                  </div>
                  <div className="space-y-1.5 border-t border-border pt-3">
                    {order.items.map((item, j) => (
                      <div key={j} className="flex justify-between text-sm">
                        <span>{item.quantity}× {item.name}</span>
                        <span className="text-muted-foreground">₹{item.price * item.quantity}</span>
                      </div>
                    ))}
                  </div>
                  {order.notes && <p className="text-xs text-accent mt-2">📝 {order.notes}</p>}
                  <div className="border-t border-border mt-3 pt-3 flex justify-between font-bold">
                    <span>Total</span><span className="gradient-text">₹{order.total}</span>
                  </div>
                </motion.div>
              );
            })}
          </AnimatePresence>
        </div>

        {filteredOrders.length === 0 && (
          <div className="text-center py-16">
            <ShoppingBag className="w-12 h-12 text-muted-foreground mx-auto mb-3 opacity-50" />
            <p className="text-muted-foreground">No {filter !== 'all' ? filter : ''} orders yet</p>
          </div>
        )}
      </div>
    </div>
  );
}
