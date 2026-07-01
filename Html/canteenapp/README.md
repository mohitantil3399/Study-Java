# CanteenHub 🍽️

> A premium, offline-first P2P canteen ordering web app for colleges.

Students scan a QR code on their table, browse the menu, and place orders — all synced directly to the canteen manager's device via WebRTC. No central server required.

## ✨ Features

- **🔒 Local-First** — All data stored on-device using IndexedDB
- **📡 P2P Ordering** — Orders sync directly via WebRTC (PeerJS)
- **📱 Static QR Codes** — Print once, paste on tables forever
- **👨‍🍳 Admin Dashboard** — Real-time order queue with status management
- **🍕 Menu Manager** — Add/edit/delete items, toggle availability
- **🛒 Smart Cart** — Quantity controls, table number, special instructions
- **🔊 Sound Alerts** — Audio notifications for new orders
- **☁️ Optional Cloud Backup** — Supabase integration for admin persistence
- **🎨 Premium UI** — Dark mode, glassmorphism, smooth animations

## 🛠️ Tech Stack

| Layer | Technology |
|-------|-----------|
| Frontend | React + TypeScript + Vite |
| UI | Tailwind CSS + Radix UI |
| Animations | Framer Motion |
| Local DB | Dexie.js (IndexedDB) |
| P2P | PeerJS (WebRTC) |
| Cloud (optional) | Supabase |
| Icons | Lucide React |

## 🚀 Quick Start

```bash
# Install dependencies
npm install

# Start dev server
npm run dev

# Build for production
npm run build
```

## 📖 Usage

### As Canteen Manager (Admin)
1. Register with the **Manager** role
2. Go to **QR Codes** → Print table QR codes
3. Open **Dashboard** to receive and manage orders
4. Use **Manage Menu** to update items and prices

### As Student (User)
1. Scan the QR code on your table
2. Register with the **Student** role
3. Browse the menu, add items to cart
4. Enter your table number and place your order

## 📋 Manual Setup

See [suspense.md](./suspense.md) for:
- Supabase cloud backup configuration
- Production deployment options
- Custom branding guide
- PWA setup instructions

## 📜 License

MIT
