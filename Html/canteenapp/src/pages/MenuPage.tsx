import React, { useState } from 'react';
import { db, type MenuItem } from '@/lib/db';
import { useCart } from '@/context/CartContext';
import { useLiveQuery } from 'dexie-react-hooks';
import { motion, AnimatePresence } from 'framer-motion';
import { ShoppingCart, Plus, Minus, Search, Filter, Check, Flame, Coffee, Cookie, UtensilsCrossed } from 'lucide-react';
import { Link } from 'react-router-dom';

const CATEGORY_CONFIG = {
  snacks: { icon: Flame, label: 'Snacks', color: '#ff5722' },
  drinks: { icon: Coffee, label: 'Drinks', color: '#00bcd4' },
  sweets: { icon: Cookie, label: 'Sweets', color: '#e91e63' },
  meals: { icon: UtensilsCrossed, label: 'Meals', color: '#4caf50' },
};

export function MenuPage() {
  const menuItems = useLiveQuery(() => db.menu.toArray()) || [];
  const { addToCart, itemCount } = useCart();
  const [search, setSearch] = useState('');
  const [activeCategory, setActiveCategory] = useState<string>('all');
  const [addedItems, setAddedItems] = useState<Set<number>>(new Set());

  const filteredItems = menuItems.filter((item) => {
    const matchesSearch = item.name.toLowerCase().includes(search.toLowerCase());
    const matchesCategory = activeCategory === 'all' || item.category === activeCategory;
    return matchesSearch && matchesCategory && item.available;
  });

  const categories = ['all', ...Object.keys(CATEGORY_CONFIG)];

  const handleAddToCart = async (item: MenuItem) => {
    await addToCart({
      menuItemId: item.id!,
      name: item.name,
      price: item.price,
      quantity: 1,
    });
    setAddedItems((prev) => new Set(prev).add(item.id!));
    setTimeout(() => {
      setAddedItems((prev) => {
        const next = new Set(prev);
        next.delete(item.id!);
        return next;
      });
    }, 1500);
  };

  return (
    <div className="min-h-screen pb-24">
      {/* Header */}
      <div className="px-4 sm:px-6 pt-6 pb-4 max-w-7xl mx-auto">
        <motion.div initial={{ opacity: 0, y: -10 }} animate={{ opacity: 1, y: 0 }}>
          <h1 className="text-2xl sm:text-3xl font-bold">
            What's on your <span className="gradient-text">mind?</span>
          </h1>
          <p className="text-muted-foreground text-sm mt-1">Pick your favorites from the menu below</p>
        </motion.div>

        {/* Search */}
        <div className="relative mt-4">
          <Search className="absolute left-4 top-1/2 -translate-y-1/2 w-4 h-4 text-muted-foreground" />
          <input
            type="text" value={search} onChange={(e) => setSearch(e.target.value)}
            placeholder="Search menu..." className="w-full pl-11 pr-4 py-3 rounded-xl bg-secondary border border-border text-sm text-foreground placeholder:text-muted-foreground focus:outline-none focus:ring-2 focus:ring-primary/50 transition-all"
          />
        </div>

        {/* Categories */}
        <div className="flex gap-2 mt-4 overflow-x-auto pb-2 scrollbar-hide">
          {categories.map((cat) => {
            const config = cat !== 'all' ? CATEGORY_CONFIG[cat as keyof typeof CATEGORY_CONFIG] : null;
            const Icon = config?.icon;
            return (
              <button key={cat} onClick={() => setActiveCategory(cat)}
                className={`flex items-center gap-2 px-4 py-2 rounded-xl text-sm font-medium whitespace-nowrap transition-all ${activeCategory === cat ? 'gradient-primary text-white shadow-lg' : 'bg-secondary text-muted-foreground hover:text-foreground'}`}>
                {Icon && <Icon className="w-4 h-4" />}
                {cat === 'all' ? 'All' : config?.label}
              </button>
            );
          })}
        </div>
      </div>

      {/* Grid */}
      <div className="px-4 sm:px-6 max-w-7xl mx-auto">
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4">
          <AnimatePresence mode="popLayout">
            {filteredItems.map((item, i) => {
              const catConfig = CATEGORY_CONFIG[item.category];
              const isAdded = addedItems.has(item.id!);
              return (
                <motion.div
                  key={item.id} layout
                  initial={{ opacity: 0, y: 20 }}
                  animate={{ opacity: 1, y: 0 }}
                  exit={{ opacity: 0, scale: 0.9 }}
                  transition={{ delay: i * 0.05 }}
                  className="glass rounded-2xl overflow-hidden group hover:border-primary/30 transition-all duration-300"
                >
                  {/* Category Strip */}
                  <div className="h-1.5 w-full" style={{ background: catConfig.color }} />

                  <div className="p-5">
                    <div className="flex items-start justify-between mb-3">
                      <div>
                        <h3 className="text-lg font-bold text-foreground group-hover:text-primary transition-colors">{item.name}</h3>
                        <p className="text-xs text-muted-foreground mt-1 line-clamp-2">{item.description}</p>
                      </div>
                      <div className="flex items-center gap-1 px-2 py-1 rounded-lg text-xs font-medium" style={{ background: `${catConfig.color}15`, color: catConfig.color }}>
                        {React.createElement(catConfig.icon, { className: 'w-3 h-3' })}
                        {catConfig.label}
                      </div>
                    </div>

                    {/* Options Preview */}
                    {item.options && item.options.length > 0 && (
                      <div className="flex flex-wrap gap-1 mb-3">
                        {item.options.slice(0, 3).map((opt) => (
                          <span key={opt} className="px-2 py-0.5 rounded-md bg-secondary text-[10px] text-muted-foreground">{opt}</span>
                        ))}
                        {item.options.length > 3 && (
                          <span className="px-2 py-0.5 rounded-md bg-secondary text-[10px] text-muted-foreground">+{item.options.length - 3}</span>
                        )}
                      </div>
                    )}

                    {/* Price & Add Button */}
                    <div className="flex items-center justify-between mt-4">
                      <div className="text-2xl font-bold gradient-text">₹{item.price}</div>
                      <motion.button
                        whileTap={{ scale: 0.9 }}
                        onClick={() => handleAddToCart(item)}
                        className={`flex items-center gap-2 px-5 py-2.5 rounded-xl text-sm font-semibold transition-all ${
                          isAdded
                            ? 'bg-success/20 text-success'
                            : 'gradient-primary text-white hover:opacity-90'
                        }`}
                      >
                        {isAdded ? <><Check className="w-4 h-4" /> Added</> : <><Plus className="w-4 h-4" /> Add</>}
                      </motion.button>
                    </div>
                  </div>
                </motion.div>
              );
            })}
          </AnimatePresence>
        </div>

        {filteredItems.length === 0 && (
          <div className="text-center py-16">
            <UtensilsCrossed className="w-12 h-12 text-muted-foreground mx-auto mb-3 opacity-50" />
            <p className="text-muted-foreground">No items found</p>
          </div>
        )}
      </div>

      {/* Floating Cart Button */}
      {itemCount > 0 && (
        <motion.div initial={{ y: 100 }} animate={{ y: 0 }} className="fixed bottom-6 left-1/2 -translate-x-1/2 z-50">
          <Link to="/cart"
            className="flex items-center gap-3 px-6 py-3.5 rounded-2xl gradient-primary text-white font-semibold shadow-2xl glow-primary hover:opacity-90 transition-opacity">
            <ShoppingCart className="w-5 h-5" />
            View Cart ({itemCount})
          </Link>
        </motion.div>
      )}
    </div>
  );
}
