import React, { useState } from 'react';
import { Search, Bell, Moon, Sun, Flame, Sparkles, Command } from 'lucide-react';
import type { User } from '../types';

interface HeaderProps {
  currentUser: User;
  onOpenCommandMenu: () => void;
}

export const Header: React.FC<HeaderProps> = ({ onOpenCommandMenu }) => {
  const [isDarkMode, setIsDarkMode] = useState(true);
  const [showNotifications, setShowNotifications] = useState(false);

  const notifications = [
    { id: 1, title: '7-Day Streak Achieved!', time: '10m ago', unread: true },
    { id: 2, title: 'New Module Released: Advanced System Architecture', time: '1h ago', unread: true },
    { id: 3, title: 'Certificate Issued for React Mastery', time: '1d ago', unread: false },
  ];

  return (
    <header className="sticky top-0 z-20 bg-slate-950/80 backdrop-blur-md border-b border-slate-800/80 px-8 py-4 flex items-center justify-between">
      {/* Search & Command Menu Launcher */}
      <div className="flex items-center gap-4 flex-1 max-w-xl">
        <button
          onClick={onOpenCommandMenu}
          className="w-full flex items-center justify-between px-4 py-2.5 rounded-xl bg-slate-900/90 border border-slate-800 text-slate-400 hover:border-indigo-500/40 hover:text-slate-200 transition-all group shadow-inner"
        >
          <div className="flex items-center gap-2.5">
            <Search className="w-4 h-4 text-indigo-400 group-hover:scale-110 transition-transform" />
            <span className="text-sm">Search paths, lessons, commands...</span>
          </div>
          <div className="flex items-center gap-1 text-[11px] font-mono text-slate-500 bg-slate-950 px-2 py-0.5 rounded border border-slate-800">
            <Command className="w-3 h-3" />
            <span>K</span>
          </div>
        </button>
      </div>

      {/* Right Controls: Daily Streak, Theme Toggle, Notifications & Profile */}
      <div className="flex items-center gap-4">
        {/* Habit & Streak Counter Badge */}
        <div className="hidden sm:flex items-center gap-2 px-3.5 py-1.5 rounded-full bg-gradient-to-r from-amber-500/10 via-orange-500/10 to-amber-500/10 border border-amber-500/30 text-amber-400 shadow-lg shadow-amber-500/5">
          <Flame className="w-4 h-4 fill-amber-400 text-amber-500 animate-pulse" />
          <span className="text-xs font-black tracking-wide font-mono">7 DAY STREAK</span>
        </div>

        {/* Theme Toggle */}
        <button
          onClick={() => setIsDarkMode(!isDarkMode)}
          className="p-2 rounded-xl bg-slate-900 border border-slate-800 text-slate-400 hover:text-slate-200 hover:bg-slate-800 transition-all"
          title="Toggle Theme"
        >
          {isDarkMode ? <Moon className="w-4 h-4 text-indigo-400" /> : <Sun className="w-4 h-4 text-amber-400" />}
        </button>

        {/* Notifications Popover */}
        <div className="relative">
          <button
            onClick={() => setShowNotifications(!showNotifications)}
            className="relative p-2 rounded-xl bg-slate-900 border border-slate-800 text-slate-400 hover:text-slate-200 hover:bg-slate-800 transition-all"
          >
            <Bell className="w-4 h-4" />
            <span className="absolute top-1.5 right-1.5 w-2 h-2 bg-indigo-500 rounded-full ring-2 ring-slate-950 animate-ping" />
            <span className="absolute top-1.5 right-1.5 w-2 h-2 bg-indigo-500 rounded-full ring-2 ring-slate-950" />
          </button>

          {showNotifications && (
            <div className="absolute right-0 mt-3 w-80 bg-slate-900 border border-slate-800 rounded-2xl shadow-2xl p-4 space-y-3 z-30 animate-in fade-in slide-in-from-top-2 duration-200">
              <div className="flex items-center justify-between border-b border-slate-800 pb-2">
                <span className="text-xs font-bold text-white flex items-center gap-1.5">
                  <Sparkles className="w-3.5 h-3.5 text-indigo-400" />
                  Notifications
                </span>
                <span className="text-[10px] bg-indigo-500/20 text-indigo-300 font-mono px-2 py-0.5 rounded-full">2 New</span>
              </div>
              <div className="space-y-2">
                {notifications.map((n) => (
                  <div key={n.id} className="p-2.5 rounded-xl bg-slate-950/60 border border-slate-800/60 flex items-start justify-between">
                    <div>
                      <p className="text-xs font-medium text-slate-200">{n.title}</p>
                      <p className="text-[10px] text-slate-500">{n.time}</p>
                    </div>
                    {n.unread && <span className="w-2 h-2 bg-indigo-500 rounded-full mt-1 shrink-0" />}
                  </div>
                ))}
              </div>
            </div>
          )}
        </div>
      </div>
    </header>
  );
};
