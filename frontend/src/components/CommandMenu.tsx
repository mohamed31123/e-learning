import React, { useEffect } from 'react';
import { Search, Compass, BookOpen, Award, Flame, User, Settings, Sparkles, X } from 'lucide-react';

interface CommandMenuProps {
  isOpen: boolean;
  onClose: () => void;
  onSelectTab: (tab: string) => void;
}

export const CommandMenu: React.FC<CommandMenuProps> = ({ isOpen, onClose, onSelectTab }) => {
  useEffect(() => {
    const handleKeyDown = (e: KeyboardEvent) => {
      if ((e.metaKey || e.ctrlKey) && e.key === 'k') {
        e.preventDefault();
        if (isOpen) onClose();
      }
      if (e.key === 'Escape' && isOpen) {
        onClose();
      }
    };

    window.addEventListener('keydown', handleKeyDown);
    return () => window.removeEventListener('keydown', handleKeyDown);
  }, [isOpen, onClose]);

  if (!isOpen) return null;

  const quickActions = [
    { id: 'dashboard', label: 'Go to Dashboard', icon: Compass },
    { id: 'curriculum', label: 'My Learning Paths', icon: BookOpen },
    { id: 'certificates', label: 'View Certificates', icon: Award },
    { id: 'streaks', label: 'Daily Streak & Habits', icon: Flame },
    { id: 'admin', label: 'Admin Hub', icon: Settings },
    { id: 'profile', label: 'User Profile & Settings', icon: User },
  ];

  return (
    <div className="fixed inset-0 z-50 flex items-start justify-center pt-24 bg-slate-950/80 backdrop-blur-md p-4 animate-in fade-in duration-200">
      <div 
        className="w-full max-w-xl bg-slate-900 border border-slate-800 rounded-2xl shadow-2xl shadow-indigo-500/10 overflow-hidden flex flex-col"
        onClick={(e) => e.stopPropagation()}
      >
        {/* Search Bar Input */}
        <div className="p-4 border-b border-slate-800 flex items-center gap-3">
          <Search className="w-5 h-5 text-indigo-400 shrink-0" />
          <input
            type="text"
            placeholder="Type a command or search..."
            autoFocus
            className="w-full bg-transparent text-slate-100 placeholder-slate-500 focus:outline-none text-sm font-medium"
          />
          <button 
            onClick={onClose} 
            className="p-1 rounded-lg text-slate-500 hover:text-slate-300 hover:bg-slate-800 transition-colors"
          >
            <X className="w-4 h-4" />
          </button>
        </div>

        {/* Quick Actions List */}
        <div className="p-2 max-h-80 overflow-y-auto space-y-1">
          <div className="px-3 py-1.5 text-[11px] font-bold uppercase tracking-wider text-slate-500 flex items-center justify-between">
            <span>Quick Navigation</span>
            <span className="text-[10px] bg-slate-800 text-slate-400 px-1.5 py-0.5 rounded font-mono">ESC to exit</span>
          </div>

          {quickActions.map((action) => {
            const Icon = action.icon;
            return (
              <button
                key={action.id}
                onClick={() => {
                  onSelectTab(action.id);
                  onClose();
                }}
                className="w-full flex items-center justify-between px-3.5 py-2.5 rounded-xl text-slate-300 hover:text-white hover:bg-indigo-600/20 hover:border-indigo-500/30 border border-transparent transition-all group text-left text-sm"
              >
                <div className="flex items-center gap-3">
                  <div className="p-1.5 rounded-lg bg-slate-800 group-hover:bg-indigo-600/40 text-slate-400 group-hover:text-indigo-300 transition-colors">
                    <Icon className="w-4 h-4" />
                  </div>
                  <span className="font-medium">{action.label}</span>
                </div>
                <span className="text-xs text-slate-500 font-mono group-hover:text-indigo-400">Jump</span>
              </button>
            );
          })}
        </div>

        {/* Footer info */}
        <div className="p-3 bg-slate-950/60 border-t border-slate-800/60 flex items-center justify-between text-xs text-slate-500">
          <div className="flex items-center gap-2">
            <Sparkles className="w-3.5 h-3.5 text-indigo-400" />
            <span>EduFlow Command Palette</span>
          </div>
          <span>Use Ctrl + K to toggle</span>
        </div>
      </div>
    </div>
  );
};
