import React, { useState } from 'react';
import { useCart } from '@/context/CartContext';
import { useAuth } from '@/context/AuthContext';
import { db, type Order } from '@/lib/db';
import { p2pClient } from '@/lib/p2p-client';
import { motion, AnimatePresence } from 'framer-motion';
import { Minus, Plus, Trash2, ShoppingBag, ArrowLeft, MapPin, StickyNote, CheckCircle2, Loader2 } from 'lucide-react';
import { Link, useNavigate, useSearchParams } from 'react-router-dom';

export function CartPage() {
  const { items, total, updateQuantity, removeFromCart, clearCart } = useCart();
  const { user } = useAuth();
  const navigate = useNavigate();
  const [searchParams] = useSearchParams();
  const [tableNumber, setTableNumber] = useState(searchParams.get('table') || '');
  const [notes, setNotes] = useState('');
  const [isPlacing, setIsPlacing] = useState(false);
  const [orderPlaced, setOrderPlaced] = useState(false);

  const handlePlaceOrder = async () => {
    if (!tableNumber || items.length === 0 || !user) return;
    setIsPlacing(true);

    try {
      const order: Order = {
        orderId: crypto.randomUUID(),
        userId: user.userId,
        userName: user.name,
        tableNumber: parseInt(tableNumber),
        items: items.map((i) => ({
          menuItemId: i.menuItemId,
          name: i.name,
          price: i.price,
          quantity: i.quantity,
          options: i.selectedOptions,
        })),
        total,
        status: 'pending',
        notes: notes || undefined,
        createdAt: Date.now(),
        updatedAt: Date.now(),
      };

      // Save locally
      await db.orders.add(order);

      // Send via P2P if connected
      if (p2pClient.isConnected) {
        p2pClient.broadcast({ type: 'ORDER_SUBMIT', data: order });
      }

      await clearCart();
      setOrderPlaced(true);
    } catch (err) {
      console.error('Order error:', err);
    } finally {
      setIsPlacing(false);
    }
  };

  if (orderPlaced) {
    return (
      <div className="min-h-screen flex items-center justify-center px-4">
        <motion.div initial={{ scale: 0.8, opacity: 0 }} animate={{ scale: 1, opacity: 1 }} className="text-center">
          <motion.div initial={{ scale: 0 }} animate={{ scale: 1 }} transition={{ type: 'spring', bounce: 0.5, delay: 0.2 }}
            className="w-24 h-24 rounded-full bg-success/20 mx-auto flex items-center justify-center mb-6">
            <CheckCircle2 className="w-12 h-12 text-success" />
          </motion.div>
          <h2 className="text-2xl font-bold mb-2">Order Placed! 🎉</h2>
          <p className="text-muted-foreground mb-6">Your order has been sent to the canteen. Sit tight!</p>
          <div className="flex gap-3 justify-center">
            <Link to="/menu" className="px-6 py-3 rounded-xl bg-secondary text-foreground font-medium hover:bg-border transition-colors">
              Order More
            </Link>
            <Link to="/orders" className="px-6 py-3 rounded-xl gradient-primary text-white font-medium hover:opacity-90 transition-opacity">
              View Orders
            </Link>
          </div>
        </motion.div>
      </div>
    );
  }

  return (
    <div className="min-h-screen pb-8">
      <div className="max-w-2xl mx-auto px-4 sm:px-6 pt-6">
        {/* Header */}
        <div className="flex items-center gap-3 mb-6">
          <Link to="/menu" className="p-2 rounded-xl bg-secondary text-muted-foreground hover:text-foreground transition-colors">
            <ArrowLeft className="w-5 h-5" />
          </Link>
          <div>
            <h1 className="text-2xl font-bold">Your Cart</h1>
            <p className="text-sm text-muted-foreground">{items.length} item{items.length !== 1 ? 's' : ''}</p>
          </div>
        </div>

        {items.length === 0 ? (
          <div className="text-center py-16">
            <ShoppingBag className="w-16 h-16 text-muted-foreground mx-auto mb-4 opacity-40" />
            <p className="text-muted-foreground mb-4">Your cart is empty</p>
            <Link to="/menu" className="inline-flex px-6 py-3 rounded-xl gradient-primary text-white font-medium">
              Browse Menu
            </Link>
          </div>
        ) : (
          <div className="space-y-6">
            {/* Cart Items */}
            <div className="space-y-3">
              <AnimatePresence>
                {items.map((item) => (
                  <motion.div key={item.id} layout exit={{ opacity: 0, x: -100 }}
                    className="glass rounded-xl p-4 flex items-center gap-4">
                    <div className="flex-1 min-w-0">
                      <h3 className="font-semibold text-foreground truncate">{item.name}</h3>
                      <p className="text-sm text-primary font-bold">₹{item.price}</p>
                    </div>
                    <div className="flex items-center gap-2">
                      <button onClick={() => updateQuantity(item.id!, item.quantity - 1)}
                        className="w-8 h-8 rounded-lg bg-secondary flex items-center justify-center text-muted-foreground hover:text-foreground transition-colors">
                        <Minus className="w-4 h-4" />
                      </button>
                      <span className="w-8 text-center font-bold">{item.quantity}</span>
                      <button onClick={() => updateQuantity(item.id!, item.quantity + 1)}
                        className="w-8 h-8 rounded-lg bg-secondary flex items-center justify-center text-muted-foreground hover:text-foreground transition-colors">
                        <Plus className="w-4 h-4" />
                      </button>
                    </div>
                    <div className="text-right min-w-[60px]">
                      <p className="font-bold">₹{item.price * item.quantity}</p>
                    </div>
                    <button onClick={() => removeFromCart(item.id!)}
                      className="p-2 rounded-lg text-muted-foreground hover:text-destructive hover:bg-destructive/10 transition-colors">
                      <Trash2 className="w-4 h-4" />
                    </button>
                  </motion.div>
                ))}
              </AnimatePresence>
            </div>

            {/* Table Number */}
            <div className="glass rounded-xl p-4">
              <label className="flex items-center gap-2 text-sm font-medium text-muted-foreground mb-2">
                <MapPin className="w-4 h-4" /> Table Number
              </label>
              <input type="number" value={tableNumber} onChange={(e) => setTableNumber(e.target.value)}
                placeholder="Enter your table number"
                className="w-full px-4 py-3 rounded-xl bg-secondary border border-border text-foreground text-sm placeholder:text-muted-foreground focus:outline-none focus:ring-2 focus:ring-primary/50 transition-all"
                min={1} required />
            </div>

            {/* Notes */}
            <div className="glass rounded-xl p-4">
              <label className="flex items-center gap-2 text-sm font-medium text-muted-foreground mb-2">
                <StickyNote className="w-4 h-4" /> Special Instructions
              </label>
              <textarea value={notes} onChange={(e) => setNotes(e.target.value)}
                placeholder="Any special requests? (e.g., extra spicy, no onion)"
                className="w-full px-4 py-3 rounded-xl bg-secondary border border-border text-foreground text-sm placeholder:text-muted-foreground focus:outline-none focus:ring-2 focus:ring-primary/50 transition-all resize-none h-20" />
            </div>

            {/* Summary */}
            <div className="glass rounded-xl p-4 space-y-3">
              <div className="flex justify-between text-sm"><span className="text-muted-foreground">Subtotal</span><span>₹{total}</span></div>
              <div className="border-t border-border pt-3 flex justify-between text-lg font-bold">
                <span>Total</span><span className="gradient-text">₹{total}</span>
              </div>
            </div>

            {/* Place Order */}
            <motion.button whileTap={{ scale: 0.98 }} onClick={handlePlaceOrder}
              disabled={isPlacing || !tableNumber}
              className="w-full py-4 rounded-2xl gradient-primary text-white font-bold text-lg hover:opacity-90 transition-opacity disabled:opacity-50 flex items-center justify-center gap-3 glow-primary">
              {isPlacing ? <Loader2 className="w-5 h-5 animate-spin" /> : <ShoppingBag className="w-5 h-5" />}
              {isPlacing ? 'Placing Order...' : `Place Order — ₹${total}`}
            </motion.button>
          </div>
        )}
      </div>
    </div>
  );
}
