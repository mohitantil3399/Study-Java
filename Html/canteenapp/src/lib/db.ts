import Dexie, { type Table } from 'dexie';

/* ═══════════════════════════════════════════════════════════════
   Canteen App — Local Database Schema (IndexedDB via Dexie.js)
   ═══════════════════════════════════════════════════════════════ */

export interface MenuItem {
  id?: number;
  name: string;
  price: number;
  category: 'snacks' | 'drinks' | 'sweets' | 'meals';
  description: string;
  image?: string;
  available: boolean;
  options?: string[]; // e.g., "Extra Spicy", "No Onion"
  createdAt: number;
  updatedAt: number;
}

export interface OrderItem {
  menuItemId: number;
  name: string;
  price: number;
  quantity: number;
  options?: string[];
}

export interface Order {
  id?: number;
  orderId: string; // unique string id for P2P sync
  userId: string;
  userName: string;
  tableNumber: number;
  items: OrderItem[];
  total: number;
  status: 'pending' | 'preparing' | 'ready' | 'delivered';
  notes?: string;
  createdAt: number;
  updatedAt: number;
}

export interface UserProfile {
  id?: number;
  userId: string;
  name: string;
  email: string;
  phone?: string;
  role: 'admin' | 'user';
  canteenId?: string; // Only for admin
  createdAt: number;
}

export interface CartItem {
  id?: number;
  menuItemId: number;
  name: string;
  price: number;
  quantity: number;
  selectedOptions?: string[];
}

class CanteenDB extends Dexie {
  menu!: Table<MenuItem>;
  orders!: Table<Order>;
  profiles!: Table<UserProfile>;
  cart!: Table<CartItem>;

  constructor() {
    super('canteenApp');

    this.version(1).stores({
      menu: '++id, name, category, available, updatedAt',
      orders: '++id, orderId, userId, tableNumber, status, createdAt',
      profiles: '++id, userId, email, role',
      cart: '++id, menuItemId',
    });
  }
}

export const db = new CanteenDB();

/* ═══ Seed Data — Default Menu ═══ */

export const DEFAULT_MENU: Omit<MenuItem, 'id'>[] = [
  {
    name: 'Samosa',
    price: 15,
    category: 'snacks',
    description: 'Crispy golden samosa with spiced potato filling',
    available: true,
    options: ['Extra Chutney', 'No Chutney'],
    createdAt: Date.now(),
    updatedAt: Date.now(),
  },
  {
    name: 'Maggie',
    price: 30,
    category: 'snacks',
    description: 'Classic Maggi noodles with masala',
    available: true,
    options: ['Extra Spicy', 'With Vegetables', 'With Egg'],
    createdAt: Date.now(),
    updatedAt: Date.now(),
  },
  {
    name: 'Chilli Potato',
    price: 50,
    category: 'snacks',
    description: 'Crispy fried potatoes tossed in spicy chilli sauce',
    available: true,
    options: ['Extra Spicy', 'Less Spicy'],
    createdAt: Date.now(),
    updatedAt: Date.now(),
  },
  {
    name: 'Manchurian',
    price: 60,
    category: 'snacks',
    description: 'Indo-Chinese veg manchurian in tangy gravy',
    available: true,
    options: ['Dry', 'With Gravy', 'Extra Spicy'],
    createdAt: Date.now(),
    updatedAt: Date.now(),
  },
  {
    name: 'Chole Samosa',
    price: 35,
    category: 'meals',
    description: 'Samosa topped with spiced chole and chutneys',
    available: true,
    options: ['Extra Chole', 'Extra Chutney'],
    createdAt: Date.now(),
    updatedAt: Date.now(),
  },
  {
    name: 'Sandwich',
    price: 40,
    category: 'snacks',
    description: 'Grilled veg sandwich with cheese',
    available: true,
    options: ['Extra Cheese', 'No Mayo', 'With Grill'],
    createdAt: Date.now(),
    updatedAt: Date.now(),
  },
  {
    name: 'Cold Drinks',
    price: 30,
    category: 'drinks',
    description: 'Chilled cola, lemon soda, or Fanta',
    available: true,
    options: ['Coca Cola', 'Sprite', 'Fanta', 'Limca'],
    createdAt: Date.now(),
    updatedAt: Date.now(),
  },
  {
    name: 'Chocolates',
    price: 20,
    category: 'sweets',
    description: 'Assorted chocolates – Dairy Milk, KitKat, 5Star',
    available: true,
    options: ['Dairy Milk', 'KitKat', '5Star', 'Perk'],
    createdAt: Date.now(),
    updatedAt: Date.now(),
  },
  {
    name: 'Milkshake',
    price: 50,
    category: 'drinks',
    description: 'Thick creamy milkshake – choose your flavor',
    available: true,
    options: ['Chocolate', 'Vanilla', 'Strawberry', 'Butterscotch', 'Mango'],
    createdAt: Date.now(),
    updatedAt: Date.now(),
  },
];
