# CanteenHub — Feature Roadmap & Future Upgrades

> A living document tracking planned features, enhancements, and the technical approach to implement each one. Organized by priority and complexity.

---

## 🟢 v1.1 — Quick Wins (Immediate Next Release)

### 1. 📥 QR Code Download & Table Tent Cards

**What**: Allow the Admin to download individual or all table QR codes as high-quality PNG images, styled as printable "table tent cards" with branding.

**Why**: Currently QR codes can only be printed via browser print dialog. A downloadable PNG/PDF is far more practical — the Admin can email it to a print shop or print at home.

**How to implement**:
```
Install: npm install html2canvas
```
- Wrap each QR card in a styled `div` with the CanteenHub logo, table number, and "Scan to Order" text.
- Use `html2canvas` to capture the styled `div` as a canvas, then convert to PNG via `canvas.toDataURL('image/png')`.
- Create a temporary `<a>` tag with `download` attribute to trigger the download.
- Add a "Download All as ZIP" button using the `jszip` library to batch-download all table cards.

**Files to modify**:
- `src/pages/admin/QRPage.tsx` — Add download buttons per card + "Download All" button.
- New utility: `src/lib/qr-download.ts`

---

### 2. 📱 Scan-to-Order Flow (Post-Login Table Auto-Fill)

**What**: When a logged-in student scans a table QR code, the app should skip the login page and go directly to the menu with the table number pre-filled. On checkout, the table number is locked and cannot be changed.

**Why**: This is the most natural flow — scan QR → see menu → order. No friction.

**How to implement**:
- The QR URL already contains `?canteenId=XYZ&table=5`.
- In `App.tsx`, parse `canteenId` and `table` from the URL on load.
- If the user is already logged in (has a valid session in `localStorage`), redirect directly to `/menu?table=5`.
- In `CartPage.tsx`, if `table` is present in the URL, pre-fill and lock the table number input.
- Store `canteenId` in context so the P2P connection auto-initiates.

**Files to modify**:
- `src/App.tsx` — URL parameter parsing + smart redirect.
- `src/pages/CartPage.tsx` — Auto-fill table number from URL.
- `src/context/AuthContext.tsx` — Store canteen connection info.

---

### 3. 🔔 Browser Push Notifications (Admin)

**What**: When a new order arrives, show a browser push notification even if the tab is in the background.

**How to implement**:
```javascript
// Request permission on Admin login
if ('Notification' in window && Notification.permission !== 'granted') {
  Notification.requestPermission();
}

// On new order received via P2P
new Notification('🍽️ New Order!', {
  body: `Table ${order.tableNumber} — ${order.items.length} items — ₹${order.total}`,
  icon: '/favicon.svg',
  tag: order.orderId, // Prevents duplicate notifications
});
```

**Files to modify**:
- `src/pages/admin/AdminDashboard.tsx` — Add notification permission request + trigger.

---

## 🟡 v1.2 — Payments & Prepaid Orders

### 4. 💳 UPI QR Payment Integration (Zero-Cost, No Gateway)

**What**: Allow the Admin to set their existing UPI ID. When a student places an order, a UPI payment QR code is generated with the exact amount, enabling instant payment via any UPI app (Google Pay, PhonePe, Paytm, etc.). **No payment gateway fees. No merchant account needed.**

**Why**: College canteens operate on thin margins. A zero-cost payment solution using the Admin's personal/business UPI ID is the most practical approach.

**How it works**:

```
UPI Deep Link Format:
upi://pay?pa=canteen@upi&pn=CanteenHub&am=150.00&cu=INR&tn=Order-abc123&tr=REF-abc123
```

| Parameter | Description | Example |
|-----------|-------------|---------|
| `pa` | Admin's UPI ID | `canteen@ybl` |
| `pn` | Business name | `College Canteen` |
| `am` | Order amount | `150.00` |
| `cu` | Currency | `INR` |
| `tn` | Transaction note | `Order #abc123` |
| `tr` | Reference ID | `REF-abc123` (for reconciliation) |

**Implementation steps**:
1. **Admin Settings** — New settings page where Admin enters their UPI ID (`pa`) and business name (`pn`). Stored in IndexedDB.
2. **Payment Screen** — After "Place Order", show a new `PaymentPage` with:
   - A generated UPI QR code (using `qrcode.react`) encoding the `upi://pay?...` deep link.
   - A "Pay with UPI App" button that opens the deep link (`window.location.href = upiLink`) — this triggers the UPI app selector on mobile.
   - A "Mark as Paid" confirmation button.
3. **Order Status** — Add a `paymentStatus` field to orders: `unpaid` | `paying` | `paid`.
4. **Admin View** — Admin sees payment status on each order card. Can manually verify and mark as confirmed.

> ⚠️ **Important**: Without a payment gateway, automatic payment verification is NOT possible. The Admin must manually verify payments against their bank statement using the `tr` (transaction reference) field. This is acceptable for a college canteen's scale.

**New files**:
- `src/pages/PaymentPage.tsx` — UPI QR display + deep link button.
- `src/pages/admin/SettingsPage.tsx` — UPI ID configuration.
- Update `src/lib/db.ts` — Add `paymentStatus` to Order schema, add `settings` table.

---

### 5. 💰 Prepaid Wallet System (Advanced)

**What**: Students can "top up" their wallet via UPI, and orders are deducted from the balance instantly. No payment needed at checkout — just tap "Place Order".

**Why**: Eliminates per-order payment friction. The canteen knows funds are already collected.

**How to implement**:
- Add a `wallet` table in IndexedDB: `{ userId, balance, transactions[] }`.
- Admin verifies UPI top-up → manually credits the student's wallet via the dashboard.
- At checkout, check `wallet.balance >= order.total`. If yes, deduct and place order.
- Show wallet balance in the Navbar.

---

## 🔵 v2.0 — Smart Canteen Features

### 6. 🤖 AI-Powered Menu Recommendations

**What**: Suggest items to students based on their order history, time of day, and popularity trends.

**How to implement**:
- Track order history per user in IndexedDB.
- Build a simple scoring algorithm:
  ```
  score = (times_ordered × 3) + (category_preference × 2) + (time_relevance × 1)
  ```
  - `time_relevance`: Snacks score higher in the evening, drinks in summer.
- Show a "Recommended for You" section at the top of the menu.
- No external AI API needed — pure on-device logic using order history.

**Files**:
- New: `src/lib/recommendations.ts`
- Modify: `src/pages/MenuPage.tsx` — Add "For You" section.

---

### 7. 📊 Admin Analytics Dashboard

**What**: Visual charts showing sales trends, peak hours, popular items, and revenue over time.

**Metrics to track**:
- Orders per hour (bar chart) → Identify rush hours.
- Top 5 items by quantity (horizontal bar) → Stock planning.
- Revenue per day/week/month (line chart) → Business health.
- Average order value (KPI card).
- Table-wise order distribution (pie chart) → Seating insights.

**How to implement**:
- Use `recharts` or `chart.js` library for visualizations.
- All data is already in IndexedDB — just aggregate and display.
- Add date range filters (Today, This Week, This Month, Custom).

**New files**:
- `src/pages/admin/AnalyticsPage.tsx`
- `src/lib/analytics.ts` — Aggregation functions.

---

### 8. 🏆 Loyalty & Gamification System

**What**: Reward frequent students with points, badges, and discounts to drive repeat orders.

**Features**:
| Feature | Description |
|---------|-------------|
| **Points per Order** | Earn 1 point per ₹10 spent |
| **Tier System** | 🥉 Bronze (0pts) → 🥈 Silver (100pts) → 🥇 Gold (500pts) → 💎 Diamond (1000pts) |
| **Streak Rewards** | Order 5 days in a row → Unlock a free item coupon |
| **Explorer Badge** | Try 7+ different items → Earn "Foodie Explorer" badge |
| **Referral Bonus** | Share the canteen link → Both users get 10 bonus points |
| **Flash Challenges** | Admin can push "First 20 orders today get 2x points" |

**How to implement**:
- Add `loyalty` table in IndexedDB: `{ userId, points, tier, badges[], streaks }`.
- Points are calculated locally after each order.
- Tier upgrades trigger an animated celebration modal.
- Admin can configure reward rules from the settings page.

---

### 9. 🍽️ Dietary Filters & Allergen Tags

**What**: Let students filter menu items by dietary preferences.

**Filters**:
- 🟢 Veg / 🔴 Non-Veg
- 🌾 Gluten-Free
- 🥜 Nut-Free
- 🌶️ Spice Level (Mild / Medium / Hot)
- 🏋️ High Protein
- ⏱️ Prep Time (Quick < 5 min, Standard < 15 min)

**How to implement**:
- Add `tags: string[]` and `prepTime: number` fields to the `MenuItem` schema.
- Admin can set tags while adding/editing items.
- Students can toggle filters on the menu page — saved in their local profile.

---

### 10. 📋 Order Scheduling (Pre-Orders)

**What**: Students can place orders in advance for a specific time slot (e.g., "Lunch at 1:00 PM").

**How to implement**:
- Add a `scheduledFor: number | null` field to the Order schema.
- In the Cart page, add an optional "Schedule for Later" picker with time slots.
- Admin dashboard shows scheduled orders in a separate "Upcoming" tab.
- At the scheduled time, the order moves to the "Pending" queue automatically.

---

## 🟣 v3.0 — Enterprise & Scale

### 11. 📱 Full PWA with Offline Queue

**What**: Make the app installable on phone home screens with a native-like experience, including offline order queuing.

**How to implement**:
- Install `vite-plugin-pwa`.
- Configure `manifest.json` with app name, icons, theme color.
- Service Worker caches the app shell and menu data.
- If the student places an order while offline, it's queued in IndexedDB. Once connection is restored, the Background Sync API pushes it to the Admin.

---

### 12. 🖥️ Kitchen Display System (KDS)

**What**: A dedicated full-screen view for the kitchen staff showing incoming orders in large, readable cards.

**Features**:
- Large font, high contrast layout optimized for wall-mounted tablets.
- Auto-scrolling order queue.
- One-tap status updates.
- Audio chime for new orders.
- Color-coded priority (older orders turn red).

**How to implement**:
- New route: `/admin/kitchen` — A simplified, full-screen version of the dashboard.
- Uses the same IndexedDB data but with a different UI optimized for kitchen use.

---

### 13. 🗣️ Multi-Language Support (i18n)

**What**: Support Hindi, English, and regional languages for wider accessibility in Indian colleges.

**How to implement**:
- Use `react-i18next` library.
- Create translation JSON files for each language.
- Language selector in user profile settings.
- Stored in `localStorage` for persistence.

---

### 14. 🧾 Digital Receipt & Order History Export

**What**: Students can view and download PDF receipts for their orders.

**How to implement**:
- Use `jspdf` library to generate PDF receipts.
- Include: Order ID, items, quantities, prices, total, date, table number.
- Add a "Download Receipt" button on each order card in the Orders page.

---

### 15. 🔐 Admin Role Hierarchy

**What**: Support multiple staff members with different permissions.

**Roles**:
| Role | Permissions |
|------|------------|
| **Owner** | Full access + menu editing + analytics + settings |
| **Manager** | Order management + menu editing |
| **Staff** | Order management only (Kitchen view) |

---

### 16. 🌡️ Real-Time Inventory & Auto-Disable

**What**: Automatically mark items as "Unavailable" when stock runs out.

**How to implement**:
- Add `stock: number` field to MenuItem.
- On each order, decrement stock count.
- When stock reaches 0, auto-set `available: false`.
- Admin can set "Low Stock Alert" threshold.

---

## 💡 Bonus Ideas (Community-Driven)

| Idea | Description | Complexity |
|------|-------------|------------|
| **Group Orders** | Friends at the same table combine orders into one | Medium |
| **Feedback & Ratings** | Rate items after delivery (1-5 stars) | Low |
| **Daily Specials Banner** | Admin highlights a "Special of the Day" | Low |
| **Estimated Prep Time** | Show "Ready in ~8 min" based on queue length | Medium |
| **Order Again** | One-tap re-order from history | Low |
| **Split Bill** | Divide the total among friends at a table | Medium |
| **Canteen Announcements** | Admin broadcasts messages ("Closing in 30 min") | Low |
| **Food Waste Tracker** | Admin logs waste → analytics show trends | Medium |
| **WhatsApp Order Receipt** | Share order details via WhatsApp | Low |
| **Multi-Canteen Support** | One app, multiple canteen outlets on campus | High |

---

## 📌 Priority Matrix

```
                    HIGH IMPACT
                        │
     ┌──────────────────┼──────────────────┐
     │  UPI Payment(4)  │  QR Download(1)  │
     │  Scan-to-Order(2)│  Push Notif(3)   │
     │  Prepaid Wallet  │  Order Again     │
LOW  │──────────────────┼──────────────────│ HIGH
EFFORT│  Daily Specials  │  Analytics(7)    │ EFFORT
     │  Feedback        │  Loyalty(8)      │
     │  Announcements   │  PWA(11)         │
     │                  │  KDS(12)         │
     └──────────────────┼──────────────────┘
                        │
                    LOW IMPACT
```

---

*Last updated: May 5, 2026 — v1.0*
*Maintained by: CanteenHub Development Team*
