import React, { useState } from 'react';
import { 
  Users, 
  BookOpen, 
  Plus, 
  Search, 
  Trash2, 
  Edit2, 
  CheckCircle2, 
  ShieldAlert,
  Activity,
  Layers
} from 'lucide-react';
import { LearningPath, User } from '../types';

interface AdminPanelProps {
  paths: LearningPath[];
  onAddNewPath: () => void;
}

export const AdminPanel: React.FC<AdminPanelProps> = ({ paths, onAddNewPath }) => {
  const [usersList, setUsersList] = useState<User[]>([
    { id: 1, fullName: 'Alex Rivera', email: 'alex.rivera@eduflow.io', role: 'INSTRUCTOR' },
    { id: 2, fullName: 'Sarah Chen', email: 'sarah.chen@eduflow.io', role: 'INSTRUCTOR' },
    { id: 3, fullName: 'Marcus Vance', email: 'marcus.vance@company.org', role: 'LEARNER' },
    { id: 4, fullName: 'Elena Rodriguez', email: 'elena.r@techcorp.io', role: 'ADMIN' },
  ]);

  const [activeTab, setActiveTab] = useState<'paths' | 'users'>('paths');

  return (
    <div className="p-8 max-w-7xl mx-auto space-y-8">
      {/* Admin Header */}
      <div className="flex flex-col md:flex-row md:items-center justify-between gap-4">
        <div>
          <div className="inline-flex items-center gap-2 px-2.5 py-0.5 rounded-full bg-purple-500/10 text-purple-400 border border-purple-500/30 text-[11px] font-mono font-semibold mb-1">
            <ShieldAlert className="w-3.5 h-3.5" />
            <span>ADMINISTRATOR & INSTRUCTOR CONTROL PANEL</span>
          </div>
          <h1 className="text-2xl font-black text-white">Management Hub</h1>
          <p className="text-xs text-slate-400">
            Overview of learning paths, modules, role-based users, and system analytics.
          </p>
        </div>

        <button
          onClick={onAddNewPath}
          className="px-5 py-2.5 rounded-xl bg-indigo-600 hover:bg-indigo-500 text-white text-xs font-bold transition shadow-lg shadow-indigo-600/30 flex items-center gap-2"
        >
          <Plus className="w-4 h-4" />
          <span>Create Learning Path</span>
        </button>
      </div>

      {/* Stats Widgets */}
      <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
        <div className="p-6 rounded-2xl bg-slate-900 border border-slate-800 flex items-center justify-between">
          <div>
            <p className="text-xs text-slate-400">Total Active Paths</p>
            <p className="text-2xl font-black text-white mt-1">{paths.length}</p>
          </div>
          <div className="p-3 rounded-xl bg-indigo-500/10 text-indigo-400">
            <Layers className="w-6 h-6" />
          </div>
        </div>

        <div className="p-6 rounded-2xl bg-slate-900 border border-slate-800 flex items-center justify-between">
          <div>
            <p className="text-xs text-slate-400">Enrolled Learners</p>
            <p className="text-2xl font-black text-white mt-1">1,248</p>
          </div>
          <div className="p-3 rounded-xl bg-emerald-500/10 text-emerald-400">
            <Users className="w-6 h-6" />
          </div>
        </div>

        <div className="p-6 rounded-2xl bg-slate-900 border border-slate-800 flex items-center justify-between">
          <div>
            <p className="text-xs text-slate-400">System Health</p>
            <p className="text-2xl font-black text-emerald-400 mt-1">99.98%</p>
          </div>
          <div className="p-3 rounded-xl bg-purple-500/10 text-purple-400">
            <Activity className="w-6 h-6" />
          </div>
        </div>
      </div>

      {/* Tabs */}
      <div className="flex items-center border-b border-slate-800 gap-6">
        <button
          onClick={() => setActiveTab('paths')}
          className={`pb-3 text-xs font-bold transition border-b-2 ${
            activeTab === 'paths'
              ? 'border-indigo-500 text-indigo-400'
              : 'border-transparent text-slate-400 hover:text-white'
          }`}
        >
          Learning Paths Management
        </button>
        <button
          onClick={() => setActiveTab('users')}
          className={`pb-3 text-xs font-bold transition border-b-2 ${
            activeTab === 'users'
              ? 'border-indigo-500 text-indigo-400'
              : 'border-transparent text-slate-400 hover:text-white'
          }`}
        >
          User Directory & Roles
        </button>
      </div>

      {/* Path List Table View */}
      {activeTab === 'paths' ? (
        <div className="bg-slate-900 border border-slate-800 rounded-2xl overflow-hidden">
          <div className="p-4 bg-slate-800/40 border-b border-slate-800 flex items-center justify-between">
            <h3 className="text-sm font-bold text-white">Active Catalog Paths</h3>
            <div className="relative w-64">
              <Search className="w-3.5 h-3.5 absolute left-3 top-1/2 -translate-y-1/2 text-slate-400" />
              <input
                type="text"
                placeholder="Search path title..."
                className="w-full bg-slate-950 text-xs text-slate-200 pl-9 pr-3 py-1.5 rounded-lg border border-slate-800 focus:border-indigo-500 outline-none"
              />
            </div>
          </div>

          <table className="w-full text-left text-xs text-slate-300">
            <thead className="bg-slate-950 text-slate-400 uppercase font-mono text-[10px]">
              <tr>
                <th className="p-4">Path Name</th>
                <th className="p-4">Level</th>
                <th className="p-4">Duration</th>
                <th className="p-4">Enrolled</th>
                <th className="p-4 text-right">Actions</th>
              </tr>
            </thead>
            <tbody className="divide-y divide-slate-800">
              {paths.map((p) => (
                <tr key={p.id} className="hover:bg-slate-800/40 transition">
                  <td className="p-4 font-bold text-white flex items-center gap-3">
                    <img src={p.bannerImage} alt={p.title} className="w-10 h-8 rounded object-cover" />
                    <span>{p.title}</span>
                  </td>
                  <td className="p-4 font-mono text-indigo-400">{p.level}</td>
                  <td className="p-4">{p.durationHours}h</td>
                  <td className="p-4">{p.enrolledCount} learners</td>
                  <td className="p-4 text-right space-x-2">
                    <button className="p-1.5 rounded bg-slate-800 text-slate-300 hover:text-indigo-400">
                      <Edit2 className="w-3.5 h-3.5" />
                    </button>
                    <button className="p-1.5 rounded bg-slate-800 text-slate-300 hover:text-rose-400">
                      <Trash2 className="w-3.5 h-3.5" />
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      ) : (
        /* User Directory Table View */
        <div className="bg-slate-900 border border-slate-800 rounded-2xl overflow-hidden">
          <div className="p-4 bg-slate-800/40 border-b border-slate-800 flex items-center justify-between">
            <h3 className="text-sm font-bold text-white">Registered Accounts & RBAC</h3>
          </div>

          <table className="w-full text-left text-xs text-slate-300">
            <thead className="bg-slate-950 text-slate-400 uppercase font-mono text-[10px]">
              <tr>
                <th className="p-4">User</th>
                <th className="p-4">Email</th>
                <th className="p-4">Assigned Role</th>
                <th className="p-4 text-right">Action</th>
              </tr>
            </thead>
            <tbody className="divide-y divide-slate-800">
              {usersList.map((u) => (
                <tr key={u.id} className="hover:bg-slate-800/40 transition">
                  <td className="p-4 font-bold text-white">{u.fullName}</td>
                  <td className="p-4 text-slate-400">{u.email}</td>
                  <td className="p-4">
                    <span className="px-2.5 py-0.5 rounded-full bg-indigo-950 text-indigo-300 border border-indigo-800 text-[10px] font-mono">
                      {u.role}
                    </span>
                  </td>
                  <td className="p-4 text-right">
                    <button className="text-xs text-indigo-400 font-bold hover:underline">
                      Manage Permissions
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
};
