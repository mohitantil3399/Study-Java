# CanteenHub — Manual Setup & Configuration Guide

> **SUSPENSE**: These are the manual steps that require YOUR action to fully unlock all features. The app works without these, but completing them gives you cloud persistence, custom branding, and production deployment.

---

## 1. 🔐 Supabase Cloud Backup (Admin Persistence Across Devices)

The app currently stores everything in your browser's local database. To enable **cross-device login** and **menu backup**, you need a free Supabase project.

### Steps:

1. Go to [https://supabase.com](https://supabase.com) and create a **free account**.
2. Click **"New Project"** → Name it `canteenhub` → Choose a strong database password → Select a region close to you.
3. Wait for the project to finish setting up (~2 minutes).
4. Go to **Settings → API** and copy:
   - **Project URL** (looks like `https://xyzabc.supabase.co`)
   - **anon public key** (a long `eyJ...` string)
5. Create a file `.env` in the project root:

```env
VITE_SUPABASE_URL=https://your-project-id.supabase.co
VITE_SUPABASE_ANON_KEY=your-anon-key-here
```

6. Go to **Table Editor** in Supabase and create a table called `menus`:

| Column | Type | Default |
|--------|------|---------|
| `id` | uuid | `gen_random_uuid()` |
| `canteen_id` | text | — |
| `items` | jsonb | — |
| `updated_at` | timestamptz | `now()` |

7. Enable **Authentication → Email** provider (it's on by default).
8. Go to **Authentication → Policies** and add a policy on the `menus` table:
   - **Name**: `Users can manage their own menus`
   - **Policy**: `auth.uid() IS NOT NULL`
   - **Operations**: SELECT, INSERT, UPDATE

9. Restart the dev server (`npm run dev`) — the app will automatically detect your Supabase config.

---

## 2. 🌐 Deploying to Production

For students to scan QR codes, you need a public URL.

### Option A: Vercel (Recommended, Free)

1. Push this repo to GitHub (already done if you're reading this after `git init`).
2. Go to [https://vercel.com](https://vercel.com) → Import your GitHub repo.
3. Add environment variables (`VITE_SUPABASE_URL`, `VITE_SUPABASE_ANON_KEY`).
4. Deploy — you'll get a URL like `https://canteenhub.vercel.app`.
5. Update your QR codes to use this URL.

### Option B: Firebase Hosting (Free)

```bash
npm install -g firebase-tools
firebase login
firebase init hosting   # Select "dist" as public directory
npm run build
firebase deploy
```

### Option C: Self-Host on Your Phone

If you want it truly local (no internet at all):
1. Run `npm run build`
2. Use a tool like [serve](https://www.npmjs.com/package/serve): `npx serve dist`
3. Connect other phones to your hotspot
4. They access the app at your phone's IP (e.g., `http://192.168.43.1:3000`)

---

## 3. 🎨 Custom Branding

To change the app name, colors, or logo:

### App Name
- Edit `index.html` → Change the `<title>` tag
- Edit `src/components/Navbar.tsx` → Change `CanteenHub` text
- Edit `src/pages/AuthPage.tsx` → Change the logo text

### Colors
- Edit `src/index.css` → Modify the `@theme` block:
  - `--color-primary`: Main accent (default: `#ff5722` orange)
  - `--color-accent`: Secondary accent (default: `#ffab00` amber)
  - `--color-background`: Page background (default: `#0a0a0b` near-black)

### Logo
- Replace `public/vite.svg` with your own SVG logo
- Or update the icon component in `Navbar.tsx` and `AuthPage.tsx`

---

## 4. 📱 Making it a PWA (Progressive Web App)

To allow students to "install" the app on their phone home screen:

1. Install the Vite PWA plugin:
```bash
npm install -D vite-plugin-pwa
```

2. Add to `vite.config.ts`:
```ts
import { VitePWA } from 'vite-plugin-pwa'

export default defineConfig({
  plugins: [
    react(),
    tailwindcss(),
    VitePWA({
      registerType: 'autoUpdate',
      manifest: {
        name: 'CanteenHub',
        short_name: 'Canteen',
        theme_color: '#ff5722',
        background_color: '#0a0a0b',
        display: 'standalone',
        icons: [
          { src: '/icon-192.png', sizes: '192x192', type: 'image/png' },
          { src: '/icon-512.png', sizes: '512x512', type: 'image/png' },
        ],
      },
    }),
  ],
})
```

3. Add icon files (`icon-192.png`, `icon-512.png`) to the `public/` folder.

---

## 5. 🔔 Push Notifications (Advanced)

For the Admin to receive order notifications even when the browser tab is in the background:

1. This requires a **Service Worker** (comes free with PWA setup above).
2. Use the `Notification` API:
```js
if (Notification.permission === 'granted') {
  new Notification('New Order!', { body: 'Table 5 — ₹120' });
} else {
  Notification.requestPermission();
}
```

---

## 6. 🖨️ Printing QR Codes

1. Go to the Admin panel → **QR Codes** page.
2. Set the number of tables.
3. Click **"Print All"** — this uses the browser's print dialog.
4. Print on sticker paper and paste on each table.

> **Tip**: The QR codes are **static** — they never change. You only need to print them once.

---

*Last updated: May 5, 2026*
