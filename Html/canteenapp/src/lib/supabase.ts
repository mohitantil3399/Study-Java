import { createClient } from '@supabase/supabase-js';

/* ═══════════════════════════════════════════════════════════════
   Supabase Client — Serverless Admin Persistence Layer
   
   NOTE: Replace these with your actual Supabase project values.
   The free tier is sufficient for this use-case (menu backup + auth).
   
   Setup steps:
   1. Go to https://supabase.com and create a free project.
   2. Copy your project URL and anon key.
   3. Create a table called "menus" with columns:
      - id (uuid, primary key, default: gen_random_uuid())
      - canteen_id (text, not null)
      - items (jsonb, not null)
      - updated_at (timestamptz, default: now())
   4. Enable Row Level Security and add a policy for authenticated users.
   ═══════════════════════════════════════════════════════════════ */

const SUPABASE_URL = import.meta.env.VITE_SUPABASE_URL || 'https://placeholder.supabase.co';
const SUPABASE_ANON_KEY = import.meta.env.VITE_SUPABASE_ANON_KEY || 'placeholder-key';

export const supabase = createClient(SUPABASE_URL, SUPABASE_ANON_KEY);

/* ═══ Helper: Check if Supabase is configured ═══ */
export function isSupabaseConfigured(): boolean {
  return (
    SUPABASE_URL !== 'https://placeholder.supabase.co' &&
    SUPABASE_ANON_KEY !== 'placeholder-key'
  );
}
