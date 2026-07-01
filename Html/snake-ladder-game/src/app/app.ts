import { Component, OnInit, ElementRef, ViewChild, signal, computed, HostListener, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
// @ts-ignore
import * as THREE from 'three';

const T = THREE as any;

export type GameMode = 'pvp' | 'pve';
export type ThemeType = 'cyberpunk' | 'candyland' | 'galaxy' | 'underwater';

export interface Player {
  id: number;
  type: 'human' | 'ai';
  position: number;
  name: string;
  color: number;
  mesh?: any;
  inventory: string[];
  isFrozen: boolean;
}

class SoundEngine {
  private ctx: AudioContext | null = null;

  init() {
    if (!this.ctx) {
      this.ctx = new (window.AudioContext || (window as any).webkitAudioContext)();
    }
    if (this.ctx.state === 'suspended') {
      this.ctx.resume();
    }
  }

  private playOscillator(freq: number, type: OscillatorType, duration: number, vol = 0.1, slideFreq?: number) {
    if (!this.ctx) return;
    const osc = this.ctx.createOscillator();
    const gain = this.ctx.createGain();
    osc.type = type;
    osc.connect(gain);
    gain.connect(this.ctx.destination);
    
    osc.frequency.setValueAtTime(freq, this.ctx.currentTime);
    if (slideFreq) {
      osc.frequency.exponentialRampToValueAtTime(slideFreq, this.ctx.currentTime + duration);
    }
    
    gain.gain.setValueAtTime(vol, this.ctx.currentTime);
    gain.gain.exponentialRampToValueAtTime(0.01, this.ctx.currentTime + duration);
    
    osc.start();
    osc.stop(this.ctx.currentTime + duration);
  }

  playClick() { this.playOscillator(800, 'sine', 0.1, 0.1); }
  playRoll() { 
    if (!this.ctx) return;
    for(let i=0; i<6; i++) {
      setTimeout(() => this.playOscillator(300 + Math.random()*600, 'square', 0.05, 0.05), i * 50);
    }
  }
  playStep() { this.playOscillator(400, 'triangle', 0.05, 0.03); }
  playWarp() { 
    this.playOscillator(200, 'sine', 0.8, 0.2, 1200); 
    setTimeout(() => this.playOscillator(300, 'triangle', 0.6, 0.1, 1600), 100);
  }
  playSnakeSound() { 
    this.playOscillator(400, 'sawtooth', 1.0, 0.2, 50); 
  }
  playWin() {
    this.playOscillator(400, 'square', 0.2, 0.1);
    setTimeout(() => this.playOscillator(500, 'square', 0.2, 0.1), 200);
    setTimeout(() => this.playOscillator(600, 'square', 0.2, 0.1), 400);
    setTimeout(() => this.playOscillator(800, 'square', 0.6, 0.15), 600);
  }
}

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule],
  template: `
    <!-- MAIN MENU -->
    @if (gameState() === 'menu') {
      <div class="fixed inset-0 z-50 flex flex-col items-center justify-center bg-slate-900 bg-opacity-95 text-white font-sans backdrop-blur-sm p-4 text-center">
        <div class="mb-8">
          <h1 class="text-5xl sm:text-6xl md:text-8xl font-black mb-4 tracking-tight">COSMIC CLIMB</h1>
          <p class="text-sm sm:text-xl text-slate-400 tracking-widest uppercase font-medium">Warp Gates & Cosmic Snakes</p>
        </div>
        
        <div class="flex flex-col sm:flex-row gap-4 mb-6 z-10 w-full max-w-sm sm:max-w-none px-4">
          <button (click)="gameMode.set('pve')" [class.bg-blue-600]="gameMode() === 'pve'" [class.bg-slate-700]="gameMode() !== 'pve'" class="flex-1 px-6 py-3 rounded-lg font-medium hover:bg-blue-500 transition-all shadow-md">Solo vs AI</button>
          <button (click)="gameMode.set('pvp')" [class.bg-indigo-600]="gameMode() === 'pvp'" [class.bg-slate-700]="gameMode() !== 'pvp'" class="flex-1 px-6 py-3 rounded-lg font-medium hover:bg-indigo-500 transition-all shadow-md">Player vs Player</button>
        </div>

        <div class="mb-8 z-10 w-full max-w-sm">
          <select [value]="currentTheme()" (change)="onThemeChange($event)" class="w-full bg-slate-800 text-white px-4 py-3 rounded-lg border border-slate-600 focus:outline-none focus:border-blue-500 text-center shadow-sm">
            <option value="galaxy">Galaxy Theme</option>
            <option value="cyberpunk">Cyberpunk Theme</option>
            <option value="candyland">Candyland Theme</option>
            <option value="underwater">Underwater Theme</option>
          </select>
        </div>

        <button (click)="startGame()" class="px-12 py-5 text-2xl font-bold rounded-full bg-blue-600 hover:bg-blue-500 shadow-xl transform hover:scale-105 active:scale-95 transition-all text-white z-10">LAUNCH SHIP</button>

        <div class="mt-12 max-w-md text-slate-300 text-xs sm:text-sm p-6 bg-slate-800 rounded-xl border border-slate-700 shadow-lg">
          <p class="mb-2"><span class="text-emerald-400 font-bold">WARP GATES</span> propel you forward.</p>
          <p><span class="text-rose-400 font-bold">COSMIC SNAKES</span> swallow you back.</p>
          <p class="mt-4 text-slate-500 uppercase tracking-widest font-semibold">Reach Sector 100 to win</p>
        </div>
      </div>
    }

    <!-- POPUPS -->
    @if (activePopup()) {
      <div class="fixed inset-0 flex items-center justify-center z-40 pointer-events-none">
        <div class="text-4xl md:text-7xl font-black uppercase tracking-tighter text-center px-4"
             [ngClass]="{
               'text-emerald-400 drop-shadow-[0_0_20px_rgba(52,211,153,0.8)]': activePopup()?.type === 'good',
               'text-rose-500 drop-shadow-[0_0_20px_rgba(244,63,94,0.8)]': activePopup()?.type === 'bad'
             }"
             style="animation: floatUpAndFade 2.5s ease-out forwards; text-shadow: 2px 2px 10px rgba(0,0,0,0.5);">
          {{ activePopup()?.text }}
        </div>
      </div>
    }

    <!-- HUD -->
    @if (gameState() === 'playing') {
      <div class="fixed inset-0 p-4 md:p-8 z-10 pointer-events-none flex flex-col md:flex-row justify-between items-center md:items-start font-sans overflow-y-auto" style="top: 0; height: auto;">
        
        <!-- Left Panel -->
        <div class="bg-slate-800/95 backdrop-blur border border-slate-700 p-4 sm:p-6 rounded-2xl shadow-2xl pointer-events-auto w-full max-w-sm md:w-80 mt-4">
          <div class="flex justify-between items-center mb-1">
            <h3 class="text-slate-400 text-xs font-bold uppercase tracking-widest">Navigation</h3>
            <span class="text-emerald-400 text-[10px] font-mono bg-slate-900 px-2 py-0.5 rounded">DROP: {{ dropTimer() }}s</span>
          </div>
          <div class="text-lg font-bold text-white mb-2">
            Active: <span [style.color]="getPlayerColorHex(activePlayer()?.color)">{{ activePlayer()?.name }}</span>
          </div>
          <div class="text-3xl font-black text-white mb-4">
            SECTOR: <span class="text-amber-400">{{ activePlayer()?.position }}</span>
          </div>
          
          <div class="h-2 w-full bg-slate-700 rounded-full mb-4 overflow-hidden border border-slate-600">
            <div class="h-full transition-all duration-500 shadow-[0_0_10px_rgba(255,255,255,0.2)]" [style.background-color]="getPlayerColorHex(activePlayer()?.color)" [style.width.%]="activePlayer()?.position"></div>
          </div>

          <div class="bg-slate-900 rounded-lg p-3 min-h-[60px] flex items-center justify-center text-center border border-slate-700 shadow-inner">
            <p class="text-slate-200 text-sm font-medium" [innerHTML]="message()"></p>
          </div>
        </div>

        <!-- Right Panel (Controls & Inventory) -->
        <div class="bg-slate-800/95 backdrop-blur border border-slate-700 p-4 sm:p-6 rounded-2xl shadow-2xl pointer-events-auto w-full max-w-sm md:w-72 flex flex-col items-center mt-4 md:mt-4 md:ml-auto md:mr-4">
          <div class="text-slate-400 text-xs font-bold uppercase tracking-widest mb-4">Quantum Drive</div>
          
          <!-- Dice Display -->
          <div class="w-20 h-20 bg-gradient-to-br from-yellow-300 to-yellow-500 rounded-xl shadow-2xl flex items-center justify-center border-4 border-yellow-200 mb-4 transform transition-all"
               [class.animate-pulse]="isAnimating()">
            <span class="text-5xl font-black text-white drop-shadow-lg">{{ diceRoll() }}</span>
          </div>
          
          <button (click)="rollDice()" [disabled]="isAnimating() || activePlayer()?.type === 'ai'"
            class="w-full py-4 rounded-xl font-bold text-lg uppercase transition-all shadow-lg mb-4"
            [ngClass]="{
              'bg-indigo-600 hover:bg-indigo-500 text-white cursor-pointer transform active:scale-95': !isAnimating() && activePlayer()?.type !== 'ai',
              'bg-slate-700 text-slate-500 cursor-not-allowed': isAnimating() || activePlayer()?.type === 'ai'
            }">
            {{ activePlayer()?.type === 'ai' ? 'AI Calculating...' : (isAnimating() ? 'Moving...' : 'Engage') }}
          </button>

          <div class="w-full border-t border-slate-700 pt-3">
            <h4 class="text-[10px] text-slate-400 font-bold uppercase tracking-wider mb-2">Inventory</h4>
            <div class="grid grid-cols-3 gap-2">
              @if (activePlayer()?.inventory?.length === 0) {
                <span class="col-span-3 text-[10px] text-slate-500 italic text-center py-2">No tech available.</span>
              }
              @for (item of activePlayer()?.inventory; track $index) {
                <button (click)="useAbility(item, $index)" [disabled]="isAnimating() || activePlayer()?.type === 'ai'" class="py-2.5 px-1 rounded-lg bg-slate-700 hover:bg-slate-600 text-[9px] text-white border border-slate-600 truncate transition-all shadow-sm">
                  {{ getAbilityIcon(item) }}
                </button>
              }
            </div>
          </div>
        </div>

      </div>
    }

    <!-- WIN SCREEN -->
    @if (gameState() === 'won') {
      <div class="fixed inset-0 z-50 flex flex-col items-center justify-center bg-slate-900/98 text-white font-sans p-4 text-center backdrop-blur-md">
        <h2 class="text-7xl font-black text-amber-400 mb-6 drop-shadow-[0_0_30px_rgba(251,191,36,0.5)]">VICTORY!</h2>
        <p class="text-2xl text-slate-300 mb-12">{{ activePlayer()?.name }} reached the Final Frontier!</p>
        <button (click)="returnToMenu()" class="px-12 py-4 text-xl font-bold rounded-full bg-slate-700 hover:bg-slate-600 border border-slate-600 transition-all shadow-xl">Back to Base</button>
      </div>
    }

    <!-- CANVAS -->
    <div #canvasContainer class="relative w-full bg-black pointer-events-none" style="height: 200vh; min-height: 200vh; display: block;" [class.shake-animation]="isShaking()"></div>
  `,
  styles: [`
    :host { display: block; height: auto; width: 100%; overflow-y: auto; overflow-x: hidden; background: #000; }
    #canvasContainer { position: relative !important; }
    canvas { position: absolute !important; top: 0 !important; left: 0 !important; width: 100% !important; height: auto !important; }
    @keyframes floatUpAndFade {
      0% { opacity: 0; transform: translateY(50px) scale(0.8); }
      15% { opacity: 1; transform: translateY(0px) scale(1.1); }
      80% { opacity: 1; transform: translateY(-40px) scale(1); }
      100% { opacity: 0; transform: translateY(-60px) scale(0.9); }
    }
    @keyframes screenShake {
      0% { transform: translate(2px, 2px); }
      25% { transform: translate(-2px, -2px); }
      50% { transform: translate(-2px, 2px); }
      75% { transform: translate(2px, -2px); }
      100% { transform: translate(0, 0); }
    }
    @keyframes spinDice {
      0% { transform: rotateX(0deg) rotateY(0deg) rotateZ(0deg); }
      100% { transform: rotateX(720deg) rotateY(720deg) rotateZ(720deg); }
    }
    .shake-animation { animation: screenShake 0.1s infinite; animation-duration: 0.5s; }
    .animate-spin { animation: spinDice 0.5s linear infinite; }
  `]
})
export default class App implements OnInit, OnDestroy {
  @ViewChild('canvasContainer', { static: true }) canvasContainer!: ElementRef;

  gameState = signal<'menu' | 'playing' | 'won'>('menu');
  gameMode = signal<GameMode>('pve');
  currentTheme = signal<ThemeType>('galaxy');
  players = signal<Player[]>([]);
  activePlayerIndex = signal(0);
  diceRoll = signal(1);
  isAnimating = signal(false);
  isShaking = signal(false);
  message = signal('Ready for launch.');
  activePopup = signal<{text: string, type: 'good' | 'bad'} | null>(null);
  dropTimer = signal<number>(120);

  private activeIntervals: any[] = [];
  private activeTimeouts: any[] = [];

  activePlayer = computed(() => this.players()[this.activePlayerIndex()]);

  readonly WIN_POSITION = 100;
  readonly WARP_GATES: Record<number, number> = { 4: 14, 9: 31, 20: 38, 28: 84, 40: 59, 51: 67, 63: 81, 71: 91 };
  readonly SNAKES: Record<number, number> = { 17: 7, 54: 34, 62: 19, 64: 60, 87: 24, 93: 73, 95: 75, 99: 78 };
  
  readonly SNAKE_PHRASES = ["Ye kya ho gaya? 😱", "Lag gaye!", "Khatam, Tata, Bye-bye!", "Skill issue!", "Emotional Damage!", "Arrey yaar!", "Abhi toh party shuru hui thi... down!"];
  readonly LADDER_PHRASES = ["To the moon! 🚀", "Warp speed!", "Mauja hi mauja!", "Sidhe swarg!", "Ek number!", "Stonks! 📈"];
  readonly CUT_PHRASES = ["Get rekt!", "Beta, tumse na ho payega!", "Kaisa laga mera mazak? 😂", "Chutti karo!", "Oof! Cut down!", "Uninstalling..."];

  private scene: any;
  private camera: any;
  private renderer: any;
  private boardTiles: any[] = [];
  private animationQueue: { playerIndex: number, target: any, isJump: boolean }[] = [];
  private isThreeLoaded = false;
  private animationFrameId: number = 0;
  private sound = new SoundEngine();
  private dice: any = null;
  private diceRotation = { x: 0, y: 0, z: 0 };
  private isRollingDice = false;
  private dropInterval: any;

  getPlayerColorHex(color: number | undefined): string {
    if (color === undefined) return '#ffffff';
    return '#' + color.toString(16).padStart(6, '0');
  }

  ngOnInit() {
    this.isThreeLoaded = true;
    this.initThreeJS();
  }

  startGame() {
    if (!this.isThreeLoaded) return;
    this.sound.init();
    this.sound.playClick();
    this.clearTimers();
    
    if (this.scene) {
      this.players().forEach((p: Player) => { if (p.mesh) this.scene.remove(p.mesh); });
    }

    this.players.set([{ id: 1, type: 'human', position: 1, name: 'Player 1', color: 0x00ffff, inventory: [], isFrozen: false }]);
    if (this.gameMode() === 'pvp') {
      this.players.update((p: Player[]) => [...p, { id: 2, type: 'human', position: 1, name: 'Player 2', color: 0xff00ff, inventory: [], isFrozen: false }]);
    } else {
      this.players.update((p: Player[]) => [...p, { id: 2, type: 'ai', position: 1, name: 'AI Bot', color: 0xff0000, inventory: [], isFrozen: false }]);
    }
    
    this.activePlayerIndex.set(0);
    this.gameState.set('playing');
    this.message.set('Engage Quantum Drive.');
    
    this.createPlayers();
    this.resetPlayerPositions();
    this.applyTheme(this.currentTheme());
    this.startDropTimer();
  }

  returnToMenu() {
    this.sound.playClick();
    this.clearTimers();
    this.gameState.set('menu');
  }

  private clearTimers() {
    if (this.dropInterval) clearInterval(this.dropInterval);
    this.activeIntervals.forEach((id: any) => clearInterval(id));
    this.activeTimeouts.forEach((id: any) => clearTimeout(id));
    this.activeIntervals = [];
    this.activeTimeouts = [];
    this.animationQueue = [];
  }

  onThemeChange(event: Event) {
    const theme = (event.target as HTMLSelectElement).value as ThemeType;
    this.currentTheme.set(theme);
    this.applyTheme(theme);
  }

  startDropTimer() {
    this.dropTimer.set(120);
    if (this.dropInterval) clearInterval(this.dropInterval);
    this.dropInterval = setInterval(() => {
      if (this.gameState() !== 'playing') return;
      let t = this.dropTimer() - 1;
      if (t <= 0) {
        this.grantRandomAbilities();
        t = 120;
      }
      this.dropTimer.set(t);
    }, 1000);
    this.activeIntervals.push(this.dropInterval);
  }

  grantRandomAbilities() {
    const abilities = ['FREEZE', 'DEMOTE', 'BOOST'];
    this.players.update((ps: Player[]) => {
      const newPs = [...ps];
      newPs.forEach((p: Player) => { 
        if (p.inventory.length < 3) p.inventory.push(abilities[Math.floor(Math.random() * 3)]); 
      });
      return newPs;
    });
    this.sound.playWarp();
    this.triggerPopup("SUPPLY DROP!", "good");
  }

  getAbilityIcon(ability: string) {
    if (ability === 'FREEZE') return '🧊 FREEZE';
    if (ability === 'DEMOTE') return '⬇️ DEMOTE';
    if (ability === 'BOOST') return '🚀 BOOST';
    return ability;
  }

  useAbility(ability: string, index: number) {
    if (this.isAnimating()) return;
    const pIdx = this.activePlayerIndex();
    const oppIdx = (pIdx + 1) % this.players().length;
    
    this.players.update((ps: Player[]) => {
      const n = [...ps];
      n[pIdx].inventory.splice(index, 1);
      return n;
    });

    this.isAnimating.set(true);
    if (ability === 'FREEZE') {
      this.players.update((ps: Player[]) => { const n = [...ps]; n[oppIdx].isFrozen = true; return n; });
      this.triggerPopup("FROZEN!", "good");
      setTimeout(() => this.isAnimating.set(false), 1000);
    } else if (ability === 'DEMOTE') {
      let target = Math.max(1, this.players()[oppIdx].position - 5);
      this.triggerPopup("REDUCE!", "good");
      this.queueAnimation(oppIdx, [target], () => { 
        this.updatePlayerPosition(oppIdx, target); 
        this.checkForCutAndFinish(oppIdx, target, false); 
      });
    } else if (ability === 'BOOST') {
      let target = Math.min(100, this.players()[pIdx].position + 5);
      this.triggerPopup("BOOST!", "good");
      this.queueAnimation(pIdx, [target], () => { 
        this.updatePlayerPosition(pIdx, target); 
        this.checkForCutAndFinish(pIdx, target, false); 
      });
    }
  }

  rollDice() {
    if (this.isAnimating()) return;
    this.sound.playRoll();
    const roll = Math.floor(Math.random() * 6) + 1;
    this.diceRoll.set(roll);
    this.isAnimating.set(true);
    this.isRollingDice = true;
    this.message.set(`${this.activePlayer().name} rolls ${roll}.`);
    
    let rollCount = 0;
    const rollInterval = setInterval(() => {
      rollCount++;
      this.updateDiceDisplay(Math.floor(Math.random() * 6) + 1);
      if (rollCount > 10) {
        clearInterval(rollInterval);
        this.updateDiceDisplay(roll);
        this.isRollingDice = false;
        this.calculateMovement(roll);
      }
    }, 50);
    this.activeIntervals.push(rollInterval);
  }

  private calculateMovement(roll: number) {
    const pIdx = this.activePlayerIndex();
    let start = this.players()[pIdx].position;
    let target = start + roll;
    const path: number[] = [];

    if (target > 100) {
      let over = target - 100;
      for (let i = start + 1; i <= 100; i++) path.push(i);
      for (let i = 1; i <= over; i++) path.push(100 - i);
      target = 100 - over;
    } else {
      for (let i = start + 1; i <= target; i++) path.push(i);
    }

    this.queueAnimation(pIdx, path, () => {
      this.updatePlayerPosition(pIdx, target);
      if (this.WARP_GATES[target]) {
        let warped = this.WARP_GATES[target];
        this.sound.playWarp();
        this.triggerPopup(this.LADDER_PHRASES[Math.floor(Math.random() * this.LADDER_PHRASES.length)], "good");
        this.queueAnimation(pIdx, [warped], () => { 
          this.updatePlayerPosition(pIdx, warped); 
          this.checkForCutAndFinish(pIdx, warped, true); 
        });
      } else if (this.SNAKES[target]) {
        let bit = this.SNAKES[target];
        this.sound.playSnakeSound();
        this.triggerPopup(this.SNAKE_PHRASES[Math.floor(Math.random() * this.SNAKE_PHRASES.length)], "bad");
        this.queueAnimation(pIdx, [bit], () => { 
          this.updatePlayerPosition(pIdx, bit); 
          this.checkForCutAndFinish(pIdx, bit, true); 
        });
      } else {
        this.checkForCutAndFinish(pIdx, target, true);
      }
    });
  }

  private checkForCutAndFinish(pIdx: number, pos: number, endTurn: boolean) {
    if (pos !== 1 && pos !== 100) {
      const activeP = this.players()[pIdx];
      const targetIdx = this.players().findIndex((p: Player) => p.id !== activeP.id && p.position === pos);
      if (targetIdx !== -1) {
        this.sound.playSnakeSound();
        this.isShaking.set(true);
        setTimeout(() => this.isShaking.set(false), 500);
        this.triggerPopup(this.CUT_PHRASES[Math.floor(Math.random() * this.CUT_PHRASES.length)], "bad");
        this.queueAnimation(targetIdx, [1], () => { 
          this.updatePlayerPosition(targetIdx, 1); 
          if (endTurn) this.finishTurn(); else this.isAnimating.set(false); 
        });
        return;
      }
    }
    if (endTurn) this.finishTurn();
    else this.isAnimating.set(false);
  }

  private finishTurn() {
    if (this.players().some((p: Player) => p.position === 100)) {
      this.sound.playWin();
      this.gameState.set('won');
      return;
    }
    let next = (this.activePlayerIndex() + 1) % this.players().length;
    if (this.players()[next].isFrozen) {
      this.triggerPopup("STILL FROZEN!", "bad");
      this.players.update((ps: Player[]) => { const n = [...ps]; n[next].isFrozen = false; return n; });
      next = (next + 1) % this.players().length;
    }
    this.activePlayerIndex.set(next);
    this.isAnimating.set(false);
    
    if (this.players()[next].type === 'ai') {
      const aiTimeout = setTimeout(() => this.handleAITurn(), 1500);
      this.activeTimeouts.push(aiTimeout);
    }
  }

  private handleAITurn() {
    const aiPlayer = this.activePlayer();
    if (aiPlayer?.type !== 'ai' || this.gameState() !== 'playing') return;

    if (aiPlayer.inventory.length > 0 && Math.random() > 0.6) {
      this.useAbility(aiPlayer.inventory[0], 0);
      const waitInterval = setInterval(() => {
        if (!this.isAnimating()) {
          clearInterval(waitInterval);
          this.rollDice();
        }
      }, 200);
      this.activeIntervals.push(waitInterval);
    } else {
      this.rollDice();
    }
  }

  private triggerPopup(text: string, type: 'good' | 'bad') {
    this.activePopup.set({ text, type });
    setTimeout(() => this.activePopup.set(null), 2500);
  }

  // --- THREE JS ---
  private initThreeJS() {
    const container = this.canvasContainer.nativeElement;
    this.scene = new T.Scene();
    const aspect = window.innerWidth / window.innerHeight;
    // Make camera taller to see full board with scrolling
    this.camera = new T.OrthographicCamera(-7 * aspect, 7 * aspect, 12, -8, 1, 1000);
    this.camera.position.set(0, -5, 20);
    this.camera.rotation.x = 0.25;
    this.renderer = new T.WebGLRenderer({ antialias: true, alpha: true });
    // Set renderer to match window size
    this.renderer.setSize(window.innerWidth, window.innerHeight);
    this.renderer.setClearColor(0x000000, 1);
    this.renderer.domElement.style.width = '100%';
    this.renderer.domElement.style.height = 'auto';
    this.renderer.domElement.style.display = 'block';
    container.appendChild(this.renderer.domElement);
    this.scene.add(new T.AmbientLight(0xffffff, 0.7));
    const pLight = new T.PointLight(0xffffff, 1.2, 50);
    pLight.position.set(5, 5, 15);
    this.scene.add(pLight);
    this.createBoard();
    this.createConnections();
    this.createDice();
    this.animate();
  }

  private createDice() {
    const diceSize = 0.6;
    const diceMat = new T.MeshStandardMaterial({ color: 0xffffff, roughness: 0.3, metalness: 0.1 });
    this.dice = new T.Mesh(new T.BoxGeometry(diceSize, diceSize, diceSize), diceMat);
    this.dice.position.set(8, 5, 2);
    this.scene.add(this.dice);
    this.updateDiceDisplay(1);
  }

  private updateDiceDisplay(number: number) {
    if (!this.dice) return;
    const rotations: Record<number, {x: number, y: number, z: number}> = {
      1: { x: 0, y: 0, z: 0 },
      2: { x: 0, y: Math.PI/2, z: 0 },
      3: { x: 0, y: 0, z: -Math.PI/2 },
      4: { x: 0, y: 0, z: Math.PI/2 },
      5: { x: -Math.PI/2, y: 0, z: 0 },
      6: { x: Math.PI/2, y: 0, z: 0 }
    };
    this.diceRotation = rotations[Math.min(6, Math.max(1, number))] || rotations[1];
  }

  private applyTheme(theme: ThemeType) {
    if (!this.scene) return;
    const colors: Record<string, number> = { galaxy: 0x0a0a2a, cyberpunk: 0x050510, candyland: 0xffe4e1, underwater: 0x001e36 };
    this.scene.background = new T.Color(colors[theme] || 0x000000);
  }

  private createBoard() {
    const group = new T.Group();
    for (let i = 1; i <= 100; i++) {
      const pos = this.getTilePosition(i);
      const canv = document.createElement('canvas'); canv.width = 128; canv.height = 128;
      const ctx = canv.getContext('2d')!;
      ctx.fillStyle = (i % 2 === 0) ? '#1e293b' : '#334155'; ctx.fillRect(0,0,128,128);
      ctx.fillStyle = '#94a3b8'; ctx.font = 'bold 60px sans-serif'; ctx.textAlign = 'center'; ctx.fillText(i.toString(), 64, 80);
      const mesh = new T.Mesh(new T.PlaneGeometry(0.9, 0.9), new T.MeshStandardMaterial({ map: new T.CanvasTexture(canv) }));
      mesh.position.copy(pos);
      group.add(mesh);
      this.boardTiles[i] = pos;
    }
    this.scene.add(group);
  }

  private createConnections() {
    Object.entries(this.WARP_GATES).forEach(([s, e]) => this.drawTube(Number(s), e, 0x34d399, true));
    Object.entries(this.SNAKES).forEach(([s, e]) => this.drawTube(Number(s), e, 0xfb7185, false));
  }

  private drawTube(s: number, e: number, color: number, isL: boolean) {
    const start = this.getTilePosition(s), end = this.getTilePosition(e);
    const mid = new T.Vector3((start.x+end.x)/2, (start.y+end.y)/2, start.distanceTo(end)*0.4);
    const curve = new T.QuadraticBezierCurve3(start, mid, end);
    const tubeGeo = new T.TubeGeometry(curve, 20, isL ? 0.08 : 0.12, 8);
    const tubeMat = new T.MeshStandardMaterial({ 
      color, 
      emissive: color, 
      emissiveIntensity: 0.4,
      wireframe: false, 
      transparent: !isL, 
      opacity: isL ? 0.8 : 0.7,
      roughness: 0.4,
      metalness: 0.6
    });
    const tube = new T.Mesh(tubeGeo, tubeMat);
    this.scene.add(tube);
    
    const markerGeo = new T.SphereGeometry(0.15, 16, 16);
    const markerMat = new T.MeshStandardMaterial({ color, emissive: color, emissiveIntensity: 0.8 });
    const startMarker = new T.Mesh(markerGeo, markerMat);
    startMarker.position.copy(start);
    startMarker.position.z = 0.2;
    this.scene.add(startMarker);
    
    const endMarker = new T.Mesh(markerGeo, markerMat);
    endMarker.position.copy(end);
    endMarker.position.z = 0.2;
    this.scene.add(endMarker);
  }

  private createPlayers() {
    this.players().forEach((p: Player) => {
      const mesh = new T.Mesh(new T.SphereGeometry(0.25, 16, 16), new T.MeshStandardMaterial({ color: p.color, emissive: p.color }));
      this.scene.add(mesh);
      p.mesh = mesh;
    });
  }

  private resetPlayerPositions() {
    this.players().forEach((p: Player) => { 
      if (p.mesh && this.boardTiles[1]) p.mesh.position.copy(this.boardTiles[1]); 
    });
  }

  private getTilePosition(i: number) {
    const cols = 10, row = Math.floor((i - 1) / cols);
    let col = (i - 1) % cols;
    if (row % 2 === 1) col = cols - 1 - col;
    return new T.Vector3(col - cols / 2 + 0.5, -row, 0);
  }

  private queueAnimation(pIdx: number, targets: number[], onC: () => void) {
    targets.forEach(target => {
      const isJ = Math.abs(target - this.players()[pIdx].position) > 5;
      const targetPos = this.getTilePosition(target);
      if (isJ) this.animationQueue.push({ playerIndex: pIdx, target: targetPos.clone().setZ(0.1), isJump: true });
      if (isJ) this.animationQueue.push({ playerIndex: pIdx, target: targetPos.clone().setZ(0.1), isJump: false });
    });
    const check = setInterval(() => { 
      if (!this.animationQueue.some((a: any) => a.playerIndex === pIdx)) { 
        clearInterval(check); 
        onC(); 
      } 
    }, 50);
    this.activeIntervals.push(check);
  }

  private updatePlayerPosition(i: number, pos: number) {
    this.players.update((ps: Player[]) => { const n = [...ps]; n[i].position = pos; return n; });
  }

  private animate = () => {
    this.animationFrameId = requestAnimationFrame(this.animate);
    
    if (this.isRollingDice && this.dice) {
      this.dice.rotation.x += 0.3;
      this.dice.rotation.y += 0.3;
      this.dice.rotation.z += 0.3;
    } else if (this.dice) {
      this.dice.rotation.x += (this.diceRotation.x - this.dice.rotation.x) * 0.05;
      this.dice.rotation.y += (this.diceRotation.y - this.dice.rotation.y) * 0.05;
      this.dice.rotation.z += (this.diceRotation.z - this.dice.rotation.z) * 0.05;
    }
    
    if (this.animationQueue.length > 0) {
      const a = this.animationQueue[0], p = this.players()[a.playerIndex];
      if (p && p.mesh) {
        p.mesh.position.lerp(a.target, 0.15);
        if (p.mesh.position.distanceTo(a.target) < 0.05) { 
          p.mesh.position.copy(a.target); 
          if (!a.isJump) this.sound.playStep(); 
          this.animationQueue.shift(); 
        }
      } else {
        this.animationQueue.shift();
      }
    }
    if (this.renderer && this.scene && this.camera) {
      this.renderer.render(this.scene, this.camera);
    }
  };

  @HostListener('window:resize')
  onResize() {
    if (!this.renderer || !this.camera) return;
    const aspect = window.innerWidth / window.innerHeight;
    this.camera.left = -7 * aspect; 
    this.camera.right = 7 * aspect;
    this.camera.top = 12;
    this.camera.bottom = -8;
    this.camera.updateProjectionMatrix();
    this.renderer.setSize(window.innerWidth, window.innerHeight);
  }

  ngOnDestroy() { 
    if (this.animationFrameId) cancelAnimationFrame(this.animationFrameId); 
    this.clearTimers();
  }
}
