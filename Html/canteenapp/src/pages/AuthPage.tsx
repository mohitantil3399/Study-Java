import React, { useState } from 'react';
import { useAuth } from '@/context/AuthContext';
import { motion } from 'framer-motion';
import { UtensilsCrossed, Eye, EyeOff, Loader2, UserPlus, LogIn, ChefHat, GraduationCap } from 'lucide-react';

export function AuthPage() {
  const { login, register, isLoading } = useAuth();
  const [mode, setMode] = useState<'login' | 'register'>('login');
  const [role, setRole] = useState<'user' | 'admin'>('user');
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [showPassword, setShowPassword] = useState(false);
  const [error, setError] = useState('');

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError('');
    try {
      if (mode === 'login') {
        await login(email, password);
      } else {
        if (!name.trim()) { setError('Please enter your name.'); return; }
        await register(name, email, password, role);
      }
    } catch (err: any) {
      setError(err.message || 'Something went wrong');
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center px-4 relative overflow-hidden">
      <div className="absolute inset-0 overflow-hidden pointer-events-none">
        <div className="absolute top-1/4 -left-32 w-96 h-96 rounded-full bg-primary/10 blur-[120px]" />
        <div className="absolute bottom-1/4 -right-32 w-96 h-96 rounded-full bg-accent/10 blur-[120px]" />
      </div>

      <motion.div initial={{ opacity: 0, y: 30 }} animate={{ opacity: 1, y: 0 }} transition={{ duration: 0.6 }} className="w-full max-w-md relative z-10">
        <div className="text-center mb-8">
          <motion.div initial={{ scale: 0 }} animate={{ scale: 1 }} transition={{ type: 'spring', bounce: 0.5, delay: 0.2 }}
            className="w-20 h-20 rounded-2xl gradient-primary mx-auto flex items-center justify-center mb-4 glow-primary">
            <UtensilsCrossed className="w-10 h-10 text-white" />
          </motion.div>
          <h1 className="text-3xl font-bold gradient-text">CanteenHub</h1>
          <p className="text-muted-foreground mt-2 text-sm">
            {mode === 'login' ? 'Welcome back! Sign in to continue.' : 'Create your account to get started.'}
          </p>
        </div>

        <div className="glass rounded-2xl p-6 space-y-5">
          <div className="flex rounded-xl bg-secondary p-1">
            <button onClick={() => { setMode('login'); setError(''); }}
              className={`flex-1 py-2.5 rounded-lg text-sm font-medium transition-all flex items-center justify-center gap-2 ${mode === 'login' ? 'gradient-primary text-white shadow-lg' : 'text-muted-foreground hover:text-foreground'}`}>
              <LogIn className="w-4 h-4" /> Sign In
            </button>
            <button onClick={() => { setMode('register'); setError(''); }}
              className={`flex-1 py-2.5 rounded-lg text-sm font-medium transition-all flex items-center justify-center gap-2 ${mode === 'register' ? 'gradient-primary text-white shadow-lg' : 'text-muted-foreground hover:text-foreground'}`}>
              <UserPlus className="w-4 h-4" /> Register
            </button>
          </div>

          {mode === 'register' && (
            <motion.div initial={{ opacity: 0, height: 0 }} animate={{ opacity: 1, height: 'auto' }} className="flex gap-3">
              <button type="button" onClick={() => setRole('user')}
                className={`flex-1 p-4 rounded-xl border-2 transition-all text-center ${role === 'user' ? 'border-primary bg-primary/10 text-primary' : 'border-border text-muted-foreground hover:border-muted-foreground'}`}>
                <GraduationCap className="w-6 h-6 mx-auto mb-2" />
                <div className="text-sm font-semibold">Student</div>
                <div className="text-xs mt-1 opacity-70">Order food</div>
              </button>
              <button type="button" onClick={() => setRole('admin')}
                className={`flex-1 p-4 rounded-xl border-2 transition-all text-center ${role === 'admin' ? 'border-accent bg-accent/10 text-accent' : 'border-border text-muted-foreground hover:border-muted-foreground'}`}>
                <ChefHat className="w-6 h-6 mx-auto mb-2" />
                <div className="text-sm font-semibold">Manager</div>
                <div className="text-xs mt-1 opacity-70">Manage canteen</div>
              </button>
            </motion.div>
          )}

          <form onSubmit={handleSubmit} className="space-y-4">
            {mode === 'register' && (
              <div>
                <label className="block text-xs font-medium text-muted-foreground mb-1.5">Full Name</label>
                <input type="text" value={name} onChange={(e) => setName(e.target.value)} placeholder="Enter your name"
                  className="w-full px-4 py-3 rounded-xl bg-secondary border border-border text-foreground text-sm placeholder:text-muted-foreground focus:outline-none focus:ring-2 focus:ring-primary/50 focus:border-primary transition-all" required />
              </div>
            )}
            <div>
              <label className="block text-xs font-medium text-muted-foreground mb-1.5">Email</label>
              <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} placeholder="you@college.edu"
                className="w-full px-4 py-3 rounded-xl bg-secondary border border-border text-foreground text-sm placeholder:text-muted-foreground focus:outline-none focus:ring-2 focus:ring-primary/50 focus:border-primary transition-all" required />
            </div>
            <div>
              <label className="block text-xs font-medium text-muted-foreground mb-1.5">Password</label>
              <div className="relative">
                <input type={showPassword ? 'text' : 'password'} value={password} onChange={(e) => setPassword(e.target.value)} placeholder="••••••••"
                  className="w-full px-4 py-3 rounded-xl bg-secondary border border-border text-foreground text-sm placeholder:text-muted-foreground focus:outline-none focus:ring-2 focus:ring-primary/50 focus:border-primary transition-all pr-12" required minLength={4} />
                <button type="button" onClick={() => setShowPassword(!showPassword)} className="absolute right-3 top-1/2 -translate-y-1/2 text-muted-foreground hover:text-foreground transition-colors">
                  {showPassword ? <EyeOff className="w-4 h-4" /> : <Eye className="w-4 h-4" />}
                </button>
              </div>
            </div>

            {error && (
              <motion.div initial={{ opacity: 0, y: -5 }} animate={{ opacity: 1, y: 0 }} className="p-3 rounded-xl bg-destructive/10 text-destructive text-sm text-center">
                {error}
              </motion.div>
            )}

            <button type="submit" disabled={isLoading}
              className="w-full py-3.5 rounded-xl gradient-primary text-white font-semibold text-sm hover:opacity-90 transition-opacity disabled:opacity-50 flex items-center justify-center gap-2 glow-primary">
              {isLoading ? <Loader2 className="w-4 h-4 animate-spin" /> : mode === 'login' ? <><LogIn className="w-4 h-4" /> Sign In</> : <><UserPlus className="w-4 h-4" /> Create Account</>}
            </button>
          </form>
        </div>
        <p className="text-center text-xs text-muted-foreground mt-6">Your data is stored locally on your device 🔒</p>
      </motion.div>
    </div>
  );
}
