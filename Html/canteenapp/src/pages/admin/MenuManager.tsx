import React, { useState } from 'react';
import { db, type MenuItem } from '@/lib/db';
import { p2pClient } from '@/lib/p2p-client';
import { useLiveQuery } from 'dexie-react-hooks';
import { motion, AnimatePresence } from 'framer-motion';
import { Plus, Pencil, Trash2, Check, X, Save, Eye, EyeOff, Flame, Coffee, Cookie, UtensilsCrossed } from 'lucide-react';

const CATEGORIES = [
  { value: 'snacks' as const, label: 'Snacks', icon: Flame },
  { value: 'drinks' as const, label: 'Drinks', icon: Coffee },
  { value: 'sweets' as const, label: 'Sweets', icon: Cookie },
  { value: 'meals' as const, label: 'Meals', icon: UtensilsCrossed },
];

export function MenuManager() {
  const menuItems = useLiveQuery(() => db.menu.toArray()) || [];
  const [editingId, setEditingId] = useState<number | null>(null);
  const [showAddForm, setShowAddForm] = useState(false);

  // Form state
  const [formName, setFormName] = useState('');
  const [formPrice, setFormPrice] = useState('');
  const [formCategory, setFormCategory] = useState<MenuItem['category']>('snacks');
  const [formDescription, setFormDescription] = useState('');
  const [formOptions, setFormOptions] = useState('');

  const resetForm = () => {
    setFormName(''); setFormPrice(''); setFormCategory('snacks');
    setFormDescription(''); setFormOptions('');
    setEditingId(null); setShowAddForm(false);
  };

  const startEdit = (item: MenuItem) => {
    setEditingId(item.id!);
    setFormName(item.name);
    setFormPrice(String(item.price));
    setFormCategory(item.category);
    setFormDescription(item.description);
    setFormOptions(item.options?.join(', ') || '');
    setShowAddForm(false);
  };

  const handleSave = async () => {
    const itemData = {
      name: formName,
      price: parseFloat(formPrice),
      category: formCategory,
      description: formDescription,
      options: formOptions.split(',').map((o) => o.trim()).filter(Boolean),
      updatedAt: Date.now(),
    };

    if (editingId) {
      await db.menu.update(editingId, itemData);
    } else {
      await db.menu.add({ ...itemData, available: true, createdAt: Date.now() } as MenuItem);
    }

    // Broadcast menu update to connected users
    const updatedMenu = await db.menu.toArray();
    p2pClient.broadcast({ type: 'MENU_UPDATE', data: updatedMenu });

    resetForm();
  };

  const toggleAvailability = async (id: number, available: boolean) => {
    await db.menu.update(id, { available: !available, updatedAt: Date.now() });
    const updatedMenu = await db.menu.toArray();
    p2pClient.broadcast({ type: 'MENU_UPDATE', data: updatedMenu });
  };

  const deleteItem = async (id: number) => {
    await db.menu.delete(id);
    const updatedMenu = await db.menu.toArray();
    p2pClient.broadcast({ type: 'MENU_UPDATE', data: updatedMenu });
  };

  const renderForm = () => (
    <motion.div initial={{ opacity: 0, height: 0 }} animate={{ opacity: 1, height: 'auto' }} exit={{ opacity: 0, height: 0 }}
      className="glass rounded-2xl p-5 space-y-4">
      <h3 className="font-bold text-lg">{editingId ? 'Edit Item' : 'Add New Item'}</h3>
      <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
        <div>
          <label className="block text-xs font-medium text-muted-foreground mb-1">Name</label>
          <input type="text" value={formName} onChange={(e) => setFormName(e.target.value)}
            className="w-full px-4 py-3 rounded-xl bg-secondary border border-border text-foreground text-sm focus:outline-none focus:ring-2 focus:ring-primary/50 transition-all" />
        </div>
        <div>
          <label className="block text-xs font-medium text-muted-foreground mb-1">Price (₹)</label>
          <input type="number" value={formPrice} onChange={(e) => setFormPrice(e.target.value)}
            className="w-full px-4 py-3 rounded-xl bg-secondary border border-border text-foreground text-sm focus:outline-none focus:ring-2 focus:ring-primary/50 transition-all" />
        </div>
      </div>
      <div>
        <label className="block text-xs font-medium text-muted-foreground mb-1">Category</label>
        <div className="flex gap-2 flex-wrap">
          {CATEGORIES.map((cat) => (
            <button key={cat.value} onClick={() => setFormCategory(cat.value)}
              className={`flex items-center gap-2 px-4 py-2 rounded-xl text-sm font-medium transition-all ${formCategory === cat.value ? 'gradient-primary text-white' : 'bg-secondary text-muted-foreground'}`}>
              <cat.icon className="w-4 h-4" /> {cat.label}
            </button>
          ))}
        </div>
      </div>
      <div>
        <label className="block text-xs font-medium text-muted-foreground mb-1">Description</label>
        <input type="text" value={formDescription} onChange={(e) => setFormDescription(e.target.value)}
          className="w-full px-4 py-3 rounded-xl bg-secondary border border-border text-foreground text-sm focus:outline-none focus:ring-2 focus:ring-primary/50 transition-all" />
      </div>
      <div>
        <label className="block text-xs font-medium text-muted-foreground mb-1">Options (comma-separated)</label>
        <input type="text" value={formOptions} onChange={(e) => setFormOptions(e.target.value)}
          placeholder="Extra Spicy, No Onion, With Cheese"
          className="w-full px-4 py-3 rounded-xl bg-secondary border border-border text-foreground text-sm placeholder:text-muted-foreground focus:outline-none focus:ring-2 focus:ring-primary/50 transition-all" />
      </div>
      <div className="flex gap-3">
        <button onClick={handleSave} disabled={!formName || !formPrice}
          className="flex items-center gap-2 px-6 py-2.5 rounded-xl gradient-primary text-white font-medium text-sm hover:opacity-90 transition-opacity disabled:opacity-50">
          <Save className="w-4 h-4" /> Save
        </button>
        <button onClick={resetForm} className="flex items-center gap-2 px-6 py-2.5 rounded-xl bg-secondary text-muted-foreground font-medium text-sm hover:text-foreground transition-colors">
          <X className="w-4 h-4" /> Cancel
        </button>
      </div>
    </motion.div>
  );

  return (
    <div className="min-h-screen pb-8">
      <div className="max-w-4xl mx-auto px-4 sm:px-6 pt-6">
        <div className="flex items-center justify-between mb-6">
          <div>
            <h1 className="text-2xl sm:text-3xl font-bold">Menu Manager</h1>
            <p className="text-sm text-muted-foreground">{menuItems.length} items</p>
          </div>
          {!showAddForm && !editingId && (
            <button onClick={() => setShowAddForm(true)}
              className="flex items-center gap-2 px-5 py-2.5 rounded-xl gradient-primary text-white font-medium text-sm hover:opacity-90 transition-opacity">
              <Plus className="w-4 h-4" /> Add Item
            </button>
          )}
        </div>

        <AnimatePresence>
          {(showAddForm || editingId) && renderForm()}
        </AnimatePresence>

        <div className="space-y-3 mt-6">
          {menuItems.map((item, i) => (
            <motion.div key={item.id} initial={{ opacity: 0, y: 10 }} animate={{ opacity: 1, y: 0 }}
              transition={{ delay: i * 0.03 }}
              className={`glass rounded-xl p-4 flex flex-wrap items-center gap-4 ${!item.available ? 'opacity-50' : ''}`}>
              <div className="flex-1 min-w-[200px]">
                <div className="flex items-center gap-2">
                  <h3 className="font-semibold">{item.name}</h3>
                  {!item.available && <span className="text-[10px] px-2 py-0.5 rounded-md bg-destructive/10 text-destructive font-medium">Unavailable</span>}
                </div>
                <p className="text-xs text-muted-foreground mt-0.5">{item.description}</p>
              </div>
              <div className="text-xl font-bold gradient-text">₹{item.price}</div>
              <div className="flex items-center gap-2">
                <button onClick={() => toggleAvailability(item.id!, item.available)}
                  className={`p-2 rounded-lg transition-colors ${item.available ? 'text-success bg-success/10' : 'text-muted-foreground bg-secondary'}`}
                  title={item.available ? 'Mark unavailable' : 'Mark available'}>
                  {item.available ? <Eye className="w-4 h-4" /> : <EyeOff className="w-4 h-4" />}
                </button>
                <button onClick={() => startEdit(item)} className="p-2 rounded-lg text-muted-foreground bg-secondary hover:text-primary hover:bg-primary/10 transition-colors">
                  <Pencil className="w-4 h-4" />
                </button>
                <button onClick={() => deleteItem(item.id!)} className="p-2 rounded-lg text-muted-foreground bg-secondary hover:text-destructive hover:bg-destructive/10 transition-colors">
                  <Trash2 className="w-4 h-4" />
                </button>
              </div>
            </motion.div>
          ))}
        </div>
      </div>
    </div>
  );
}
