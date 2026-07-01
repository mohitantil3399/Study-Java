import React, { useState } from 'react';
import { useAuth } from '@/context/AuthContext';
import { QRCodeSVG } from 'qrcode.react';
import { motion } from 'framer-motion';
import { QrCode, Copy, Check, Printer, Plus, Minus } from 'lucide-react';

export function QRPage() {
  const { user } = useAuth();
  const [tableCount, setTableCount] = useState(10);
  const [copiedTable, setCopiedTable] = useState<number | null>(null);
  const baseUrl = window.location.origin;

  const getQRValue = (table: number) =>
    `${baseUrl}/?canteenId=${user?.canteenId}&table=${table}`;

  const handleCopy = (table: number) => {
    navigator.clipboard.writeText(getQRValue(table));
    setCopiedTable(table);
    setTimeout(() => setCopiedTable(null), 2000);
  };

  const handlePrint = () => {
    window.print();
  };

  return (
    <div className="min-h-screen pb-8">
      <div className="max-w-5xl mx-auto px-4 sm:px-6 pt-6">
        <div className="flex flex-wrap items-center justify-between gap-4 mb-6">
          <div>
            <h1 className="text-2xl sm:text-3xl font-bold">Table QR Codes</h1>
            <p className="text-sm text-muted-foreground">Print and paste these on each table</p>
          </div>
          <div className="flex items-center gap-3">
            <div className="flex items-center gap-2 glass rounded-xl px-3 py-2">
              <button onClick={() => setTableCount(Math.max(1, tableCount - 1))}
                className="p-1 rounded-lg bg-secondary text-muted-foreground hover:text-foreground">
                <Minus className="w-4 h-4" />
              </button>
              <span className="text-sm font-bold w-8 text-center">{tableCount}</span>
              <button onClick={() => setTableCount(tableCount + 1)}
                className="p-1 rounded-lg bg-secondary text-muted-foreground hover:text-foreground">
                <Plus className="w-4 h-4" />
              </button>
              <span className="text-xs text-muted-foreground">tables</span>
            </div>
            <button onClick={handlePrint}
              className="flex items-center gap-2 px-5 py-2.5 rounded-xl gradient-primary text-white font-medium text-sm hover:opacity-90 transition-opacity">
              <Printer className="w-4 h-4" /> Print All
            </button>
          </div>
        </div>

        {/* Canteen ID */}
        <div className="glass rounded-2xl p-5 mb-6">
          <p className="text-xs text-muted-foreground mb-1">Your Canteen ID</p>
          <div className="flex items-center gap-3">
            <code className="text-lg font-mono font-bold gradient-text">{user?.canteenId}</code>
            <button onClick={() => { navigator.clipboard.writeText(user?.canteenId || ''); }}
              className="p-2 rounded-lg bg-secondary text-muted-foreground hover:text-foreground transition-colors">
              <Copy className="w-4 h-4" />
            </button>
          </div>
          <p className="text-xs text-muted-foreground mt-2">This ID is permanent. All QR codes will always connect to your canteen.</p>
        </div>

        {/* QR Grid */}
        <div className="grid grid-cols-2 sm:grid-cols-3 lg:grid-cols-4 xl:grid-cols-5 gap-4 print:grid-cols-3">
          {Array.from({ length: tableCount }, (_, i) => i + 1).map((table) => (
            <motion.div key={table} initial={{ opacity: 0, scale: 0.9 }} animate={{ opacity: 1, scale: 1 }}
              transition={{ delay: table * 0.03 }}
              className="glass rounded-2xl p-4 text-center print:break-inside-avoid print:border print:border-gray-300">
              <div className="text-xs text-muted-foreground mb-2 print:text-black">Table</div>
              <div className="text-3xl font-bold gradient-text mb-3 print:text-black">{table}</div>
              <div className="bg-white rounded-xl p-3 inline-block mb-3">
                <QRCodeSVG
                  value={getQRValue(table)}
                  size={120}
                  level="M"
                  bgColor="#ffffff"
                  fgColor="#000000"
                />
              </div>
              <div className="text-[10px] text-muted-foreground mb-2 print:text-black">Scan to Order</div>
              <button onClick={() => handleCopy(table)}
                className="flex items-center gap-1 mx-auto px-3 py-1.5 rounded-lg bg-secondary text-xs text-muted-foreground hover:text-foreground transition-colors print:hidden">
                {copiedTable === table ? <><Check className="w-3 h-3 text-success" /> Copied</> : <><Copy className="w-3 h-3" /> Copy Link</>}
              </button>
            </motion.div>
          ))}
        </div>
      </div>
    </div>
  );
}
