import React, { createContext, useContext, useState, useCallback, type ReactNode } from 'react';
import { db, type CartItem } from '@/lib/db';
import { useLiveQuery } from 'dexie-react-hooks';

/* ═══════════════════════════════════════════════════════════════
   Cart Context — Manages the user's shopping cart
   ═══════════════════════════════════════════════════════════════ */

interface CartContextType {
  items: CartItem[];
  itemCount: number;
  total: number;
  addToCart: (item: Omit<CartItem, 'id'>) => Promise<void>;
  removeFromCart: (id: number) => Promise<void>;
  updateQuantity: (id: number, quantity: number) => Promise<void>;
  clearCart: () => Promise<void>;
}

const CartContext = createContext<CartContextType | null>(null);

export function CartProvider({ children }: { children: ReactNode }) {
  const items = useLiveQuery(() => db.cart.toArray()) || [];

  const itemCount = items.reduce((sum, item) => sum + item.quantity, 0);
  const total = items.reduce((sum, item) => sum + item.price * item.quantity, 0);

  const addToCart = useCallback(async (item: Omit<CartItem, 'id'>) => {
    // Check if item already in cart
    const existing = await db.cart.where('menuItemId').equals(item.menuItemId).first();
    if (existing) {
      await db.cart.update(existing.id!, { quantity: existing.quantity + item.quantity });
    } else {
      await db.cart.add(item as CartItem);
    }
  }, []);

  const removeFromCart = useCallback(async (id: number) => {
    await db.cart.delete(id);
  }, []);

  const updateQuantity = useCallback(async (id: number, quantity: number) => {
    if (quantity <= 0) {
      await db.cart.delete(id);
    } else {
      await db.cart.update(id, { quantity });
    }
  }, []);

  const clearCart = useCallback(async () => {
    await db.cart.clear();
  }, []);

  return (
    <CartContext.Provider value={{ items, itemCount, total, addToCart, removeFromCart, updateQuantity, clearCart }}>
      {children}
    </CartContext.Provider>
  );
}

export function useCart() {
  const context = useContext(CartContext);
  if (!context) throw new Error('useCart must be used within CartProvider');
  return context;
}
