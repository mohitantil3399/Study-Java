import React, { createContext, useContext, useState, useEffect, useCallback, type ReactNode } from 'react';
import { db, type UserProfile, DEFAULT_MENU } from '@/lib/db';
import { supabase, isSupabaseConfigured } from '@/lib/supabase';

/* ═══════════════════════════════════════════════════════════════
   Auth Context — Local-first authentication with optional cloud sync
   ═══════════════════════════════════════════════════════════════ */

interface AuthContextType {
  user: UserProfile | null;
  isLoading: boolean;
  isAdmin: boolean;
  login: (email: string, password: string) => Promise<void>;
  register: (name: string, email: string, password: string, role: 'admin' | 'user') => Promise<void>;
  logout: () => void;
}

const AuthContext = createContext<AuthContextType | null>(null);

export function AuthProvider({ children }: { children: ReactNode }) {
  const [user, setUser] = useState<UserProfile | null>(null);
  const [isLoading, setIsLoading] = useState(true);

  const isAdmin = user?.role === 'admin';

  /* ═══ Check for existing session on mount ═══ */
  useEffect(() => {
    const loadSession = async () => {
      try {
        // Check localStorage for persisted session
        const savedUserId = localStorage.getItem('canteen_user_id');
        if (savedUserId) {
          const profile = await db.profiles.where('userId').equals(savedUserId).first();
          if (profile) {
            setUser(profile);
          } else {
            localStorage.removeItem('canteen_user_id');
          }
        }
      } catch (err) {
        console.error('Session load error:', err);
      } finally {
        setIsLoading(false);
      }
    };

    loadSession();
  }, []);

  /* ═══ Register ═══ */
  const register = useCallback(async (name: string, email: string, password: string, role: 'admin' | 'user') => {
    setIsLoading(true);
    try {
      // Check if email already exists locally
      const existing = await db.profiles.where('email').equals(email).first();
      if (existing) {
        throw new Error('An account with this email already exists.');
      }

      const userId = crypto.randomUUID();
      const canteenId = role === 'admin' ? crypto.randomUUID().split('-')[0] : undefined;

      const profile: UserProfile = {
        userId,
        name,
        email,
        role,
        canteenId,
        createdAt: Date.now(),
      };

      await db.profiles.add(profile);

      // If admin, seed the default menu
      if (role === 'admin') {
        const existingMenu = await db.menu.count();
        if (existingMenu === 0) {
          await db.menu.bulkAdd(DEFAULT_MENU.map(item => ({ ...item })));
        }
      }

      // Persist session
      localStorage.setItem('canteen_user_id', userId);
      localStorage.setItem('canteen_user_role', role);
      if (canteenId) {
        localStorage.setItem('canteen_id', canteenId);
      }

      // Optional: Cloud backup for admin
      if (role === 'admin' && isSupabaseConfigured()) {
        try {
          await supabase.auth.signUp({ email, password });
        } catch (err) {
          console.warn('Cloud backup unavailable, continuing locally:', err);
        }
      }

      setUser(profile);
    } finally {
      setIsLoading(false);
    }
  }, []);

  /* ═══ Login ═══ */
  const login = useCallback(async (email: string, password: string) => {
    setIsLoading(true);
    try {
      // Try local first
      const profile = await db.profiles.where('email').equals(email).first();
      if (profile) {
        localStorage.setItem('canteen_user_id', profile.userId);
        localStorage.setItem('canteen_user_role', profile.role);
        if (profile.canteenId) {
          localStorage.setItem('canteen_id', profile.canteenId);
        }
        setUser(profile);
        return;
      }

      // Try cloud if configured
      if (isSupabaseConfigured()) {
        const { data, error } = await supabase.auth.signInWithPassword({ email, password });
        if (error) throw new Error(error.message);

        // Sync profile from cloud to local
        if (data.user) {
          const cloudProfile: UserProfile = {
            userId: data.user.id,
            name: data.user.user_metadata?.name || email.split('@')[0],
            email,
            role: data.user.user_metadata?.role || 'user',
            canteenId: data.user.user_metadata?.canteenId,
            createdAt: Date.now(),
          };
          await db.profiles.add(cloudProfile);
          localStorage.setItem('canteen_user_id', cloudProfile.userId);
          localStorage.setItem('canteen_user_role', cloudProfile.role);
          setUser(cloudProfile);
          return;
        }
      }

      throw new Error('No account found with this email. Please register first.');
    } finally {
      setIsLoading(false);
    }
  }, []);

  /* ═══ Logout ═══ */
  const logout = useCallback(() => {
    localStorage.removeItem('canteen_user_id');
    localStorage.removeItem('canteen_user_role');
    setUser(null);
  }, []);

  return (
    <AuthContext.Provider value={{ user, isLoading, isAdmin, login, register, logout }}>
      {children}
    </AuthContext.Provider>
  );
}

export function useAuth() {
  const context = useContext(AuthContext);
  if (!context) throw new Error('useAuth must be used within AuthProvider');
  return context;
}
