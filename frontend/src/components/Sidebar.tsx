import React from 'react';
import { 
  BookOpen, 
  Compass, 
  Award, 
  CheckCircle2, 
  Settings, 
  ShieldCheck, 
  Sparkles, 
  LogOut,
  ChevronRight
} from 'lucide-react';
import { User } from '../types';

interface SidebarProps {
  currentTab: string;
  setCurrentTab: (tab: string) => void;
  currentUser: User;
}

export const Sidebar: React.FC<SidebarProps> = ({ currentTab, setCurrentTab, currentUser }) => {
  const navItems = [
    { id: 'dashboard', label: 'Dashboard', icon: Compass },
    { id: 'curriculum', label: 'My Learning Path', icon: BookOpen },
    { id: 'certificates', label: 'Certificates', icon: Award },
    { id: 'reader', label: 'Lesson Reader', icon: CheckCircle2 },
    { id: 'admin', label: 'Admin Hub', icon: ShieldCheck, badge: 'Role: ' + currentUser.role },
  ];

  return (
    <aside className="w-64 bg-slate-900 border-r border-slate-800 flex flex-col justify-between h-screen sticky top-0 z-30">
      <div>
        {/* Brand Logo */}
        <div className="p-6 flex items-center justify-between border-b border-slate-800/80">
          <div className="flex items-center gap-3">
            <div className="w-10 h-10 rounded-xl bg-gradient-to-tr from-indigo-600 via-indigo-500 to-purple-500 flex items-center justify-center text-white shadow-lg shadow-indigo-500/30">
              <Sparkles className="w-5 h-5 animate-pulse" />
            </div>
            <div>
              <h1 className="font-extrabold text-xl tracking-tight text-white flex items-center gap-1">
                EduFlow
              </h1>
              <p className="text-[10px] uppercase tracking-wider font-semibold text-indigo-400">Professional Growth</p>
            </div>
          </div>
        </div>

        {/* Navigation Links */}
        <nav className="p-4 space-y-1.5">
          {navItems.map((item) => {
            const Icon = item.icon;
            const isActive = currentTab === item.id;
            return (
              <button
                key={item.id}
                onClick={() => setCurrentTab(item.id)}
                className={`w-full flex items-center justify-between px-4 py-3 rounded-xl font-medium text-sm transition-all duration-200 ${
                  isActive
                    ? 'bg-indigo-600 text-white shadow-lg shadow-indigo-600/30 font-semibold'
                    : 'text-slate-400 hover:text-slate-200 hover:bg-slate-800/60'
                }`}
              >
                <div className="flex items-center gap-3">
                  <Icon className={`w-5 h-5 ${isActive ? 'text-white' : 'text-slate-400'}`} />
                  <span>{item.label}</span>
                </div>
                {item.badge && (
                  <span className="text-[10px] px-2 py-0.5 rounded-full bg-indigo-950 text-indigo-300 border border-indigo-800 font-mono">
                    {item.badge}
                  </span>
                )}
              </button>
            );
          })}
        </nav>
      </div>

      {/* Upgrade Banner & User Profile */}
      <div className="p-4 space-y-4">
        <div className="rounded-2xl p-4 bg-gradient-to-br from-indigo-900/60 via-purple-900/40 to-slate-900 border border-indigo-500/20 shadow-xl">
          <div className="flex items-center gap-2 mb-1">
            <span className="text-xs font-semibold text-indigo-300">EduFlow Pro</span>
            <span className="text-[10px] bg-indigo-500 text-white font-bold px-1.5 py-0.5 rounded">PRO</span>
          </div>
          <p className="text-xs text-slate-400 mb-3">Unlimited access to 50+ enterprise paths & certificates.</p>
          <button className="w-full py-2 bg-indigo-600 hover:bg-indigo-500 text-white font-medium text-xs rounded-xl shadow-md transition-all flex items-center justify-center gap-1">
            <span>Upgrade Plan</span>
            <ChevronRight className="w-3.5 h-3.5" />
          </button>
        </div>

        <div className="pt-3 border-t border-slate-800 flex items-center justify-between">
          <div className="flex items-center gap-3">
            <img 
              src={currentUser.avatarUrl} 
              alt={currentUser.fullName} 
              className="w-9 h-9 rounded-full object-cover ring-2 ring-indigo-500/50" 
            />
            <div className="text-left">
              <p className="text-xs font-bold text-white leading-tight">{currentUser.fullName}</p>
              <p className="text-[11px] text-slate-400 leading-tight">{currentUser.email}</p>
            </div>
          </div>
          <button className="text-slate-400 hover:text-rose-400 transition-colors p-1.5 rounded-lg hover:bg-slate-800">
            <LogOut className="w-4 h-4" />
          </button>
        </div>
      </div>
    </aside>
  );
};
