import React from 'react';
import { db, type Order } from '@/lib/db';
import { useAuth } from '@/context/AuthContext';
import { useLiveQuery } from 'dexie-react-hooks';
import { motion } from 'framer-motion';
import { Clock, ChefHat, CheckCircle2, Package, ArrowLeft } from 'lucide-react';
import { Link } from 'react-router-dom';

const STATUS_CONFIG = {
  pending: { icon: Clock, label: 'Pending', color: '#f59e0b', bg: 'rgba(245,158,11,0.1)' },
  preparing: { icon: ChefHat, label: 'Preparing', color: '#3b82f6', bg: 'rgba(59,130,246,0.1)' },
  ready: { icon: CheckCircle2, label: 'Ready!', color: '#22c55e', bg: 'rgba(34,197,94,0.1)' },
  delivered: { icon: Package, label: 'Delivered', color: '#a1a1aa', bg: 'rgba(161,161,170,0.1)' },
};

export function OrdersPage() {
  const { user } = useAuth();
  const orders = useLiveQuery(
    () => db.orders.where('userId').equals(user?.userId || '').reverse().sortBy('createdAt'),
    [user?.userId]
  ) || [];

  return (
    <div className="min-h-screen pb-8">
      <div className="max-w-2xl mx-auto px-4 sm:px-6 pt-6">
        <div className="flex items-center gap-3 mb-6">
          <Link to="/menu" className="p-2 rounded-xl bg-secondary text-muted-foreground hover:text-foreground transition-colors">
            <ArrowLeft className="w-5 h-5" />
          </Link>
          <div>
            <h1 className="text-2xl font-bold">My Orders</h1>
            <p className="text-sm text-muted-foreground">{orders.length} order{orders.length !== 1 ? 's' : ''}</p>
          </div>
        </div>

        {orders.length === 0 ? (
          <div className="text-center py-16">
            <Package className="w-16 h-16 text-muted-foreground mx-auto mb-4 opacity-40" />
            <p className="text-muted-foreground mb-4">No orders yet</p>
            <Link to="/menu" className="inline-flex px-6 py-3 rounded-xl gradient-primary text-white font-medium">
              Browse Menu
            </Link>
          </div>
        ) : (
          <div className="space-y-4">
            {orders.map((order, i) => {
              const statusConfig = STATUS_CONFIG[order.status];
              const StatusIcon = statusConfig.icon;
              return (
                <motion.div key={order.id} initial={{ opacity: 0, y: 20 }} animate={{ opacity: 1, y: 0 }}
                  transition={{ delay: i * 0.05 }} className="glass rounded-2xl p-5">
                  <div className="flex items-center justify-between mb-3">
                    <div>
                      <p className="text-xs text-muted-foreground">
                        {new Date(order.createdAt).toLocaleString()} · Table {order.tableNumber}
                      </p>
                      <p className="text-xs text-muted-foreground mt-0.5">#{order.orderId.slice(0, 8)}</p>
                    </div>
                    <div className="flex items-center gap-1.5 px-3 py-1.5 rounded-full text-xs font-semibold"
                      style={{ background: statusConfig.bg, color: statusConfig.color }}>
                      <StatusIcon className="w-3.5 h-3.5" />
                      {statusConfig.label}
                    </div>
                  </div>
                  <div className="space-y-2 border-t border-border pt-3">
                    {order.items.map((item, j) => (
                      <div key={j} className="flex justify-between text-sm">
                        <span className="text-foreground">{item.quantity}× {item.name}</span>
                        <span className="text-muted-foreground">₹{item.price * item.quantity}</span>
                      </div>
                    ))}
                  </div>
                  {order.notes && (
                    <p className="text-xs text-muted-foreground mt-2 italic">Note: {order.notes}</p>
                  )}
                  <div className="border-t border-border mt-3 pt-3 flex justify-between font-bold">
                    <span>Total</span>
                    <span className="gradient-text">₹{order.total}</span>
                  </div>
                </motion.div>
              );
            })}
          </div>
        )}
      </div>
    </div>
  );
}
