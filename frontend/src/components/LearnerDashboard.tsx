import React, { useState } from 'react';
import { 
  Flame, 
  CheckCircle2, 
  Clock, 
  BookOpen, 
  Star, 
  PlayCircle, 
  ArrowRight, 
  TrendingUp, 
  Target,
  Zap,
  Award
} from 'lucide-react';
import type { LearningPath, User } from '../types';

interface LearnerDashboardProps {
  paths: LearningPath[];
  currentUser: User;
  onSelectPath: (path: LearningPath) => void;
  onOpenLesson: (path: LearningPath) => void;
}

export const LearnerDashboard: React.FC<LearnerDashboardProps> = ({
  paths,
  currentUser,
  onSelectPath,
  onOpenLesson
}) => {
  const activePath = paths[0];

  // Daily Habits State (DAU Booster)
  const [dailyHabits, setDailyHabits] = useState([
    { id: 1, text: 'Complete 1 Micro-Lesson', completed: true },
    { id: 2, text: 'Score 80%+ on Daily Quiz', completed: false },
    { id: 3, text: 'Review Code Snippets (15 mins)', completed: false },
  ]);

  const [activeFilter, setActiveFilter] = useState<'all' | 'in_progress' | 'completed'>('all');

  const toggleHabit = (id: number) => {
    setDailyHabits(prev =>
      prev.map(h => h.id === id ? { ...h, completed: !h.completed } : h)
    );
  };

  const habitProgress = Math.round((dailyHabits.filter(h => h.completed).length / dailyHabits.length) * 100);

  return (
    <div className="p-8 space-y-8 max-w-7xl mx-auto">
      {/* Welcome Banner & Daily Habit Widget */}
      <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
        {/* Main Hero Card */}
        <div className="lg:col-span-2 p-8 rounded-3xl bg-gradient-to-br from-slate-900 via-indigo-950/40 to-slate-900 border border-indigo-500/20 shadow-2xl relative overflow-hidden flex flex-col justify-between">
          <div className="absolute top-0 right-0 w-96 h-96 bg-indigo-500/10 rounded-full blur-3xl -mr-20 -mt-20 pointer-events-none" />

          <div>
            <div className="flex items-center gap-2 mb-3">
              <span className="px-3 py-1 rounded-full bg-indigo-500/20 border border-indigo-500/30 text-indigo-300 text-xs font-semibold uppercase tracking-wider flex items-center gap-1.5">
                <Zap className="w-3.5 h-3.5 text-indigo-400" />
                Daily Active Focus
              </span>
            </div>
            <h1 className="text-3xl sm:text-4xl font-extrabold text-white tracking-tight leading-tight mb-2">
              Welcome back, {currentUser.fullName.split(' ')[0]} 👋
            </h1>
            <p className="text-slate-400 text-sm max-w-xl">
              You are on a <span className="text-amber-400 font-bold">7-day learning streak</span>. Keep building your engineering mastery today!
            </p>
          </div>

          {/* Active Learning Path Mini Highlight */}
          <div className="mt-6 pt-6 border-t border-slate-800/80 flex flex-col sm:flex-row sm:items-center justify-between gap-4">
            <div className="flex items-center gap-4">
              <div className="w-12 h-12 rounded-2xl bg-indigo-600/20 border border-indigo-500/30 flex items-center justify-center text-indigo-400 font-bold shrink-0">
                <BookOpen className="w-6 h-6" />
              </div>
              <div>
                <p className="text-xs text-slate-400">Currently Learning</p>
                <p className="text-base font-bold text-white">{activePath.title}</p>
                <div className="flex items-center gap-3 text-xs text-slate-400 mt-1">
                  <span>{activePath.progressPercentage}% Complete</span>
                  <span>•</span>
                  <span>{activePath.durationHours} hrs total</span>
                </div>
              </div>
            </div>

            <button
              onClick={() => onOpenLesson(activePath)}
              className="px-5 py-3 rounded-2xl bg-indigo-600 hover:bg-indigo-500 text-white font-semibold text-sm shadow-lg shadow-indigo-600/30 hover:shadow-indigo-500/50 transition-all flex items-center justify-center gap-2 shrink-0 group"
            >
              <span>Continue Lesson</span>
              <PlayCircle className="w-4 h-4 group-hover:scale-110 transition-transform" />
            </button>
          </div>
        </div>

        {/* Daily Habit & Streak Widget (Habit Loop Booster) */}
        <div className="p-6 rounded-3xl bg-slate-900/90 border border-slate-800 backdrop-blur-md shadow-xl flex flex-col justify-between space-y-4">
          <div>
            <div className="flex items-center justify-between mb-2">
              <span className="text-xs font-bold text-slate-400 uppercase tracking-wider flex items-center gap-1.5">
                <Target className="w-4 h-4 text-emerald-400" />
                Today's Habit Target
              </span>
              <span className="text-xs font-mono font-bold text-emerald-400">{habitProgress}%</span>
            </div>

            {/* Progress Bar */}
            <div className="w-full h-2 bg-slate-950 rounded-full overflow-hidden mb-4">
              <div 
                className="h-full bg-gradient-to-r from-emerald-500 to-teal-400 transition-all duration-500"
                style={{ width: `${habitProgress}%` }}
              />
            </div>

            {/* Habit Checkboxes */}
            <div className="space-y-2.5">
              {dailyHabits.map((habit) => (
                <button
                  key={habit.id}
                  onClick={() => toggleHabit(habit.id)}
                  className={`w-full flex items-center gap-3 p-3 rounded-2xl border text-xs text-left transition-all ${
                    habit.completed
                      ? 'bg-emerald-500/10 border-emerald-500/30 text-emerald-300'
                      : 'bg-slate-950/60 border-slate-800 text-slate-300 hover:bg-slate-800/60'
                  }`}
                >
                  <CheckCircle2 className={`w-4 h-4 shrink-0 ${habit.completed ? 'text-emerald-400' : 'text-slate-600'}`} />
                  <span className={habit.completed ? 'line-through opacity-80' : 'font-medium'}>{habit.text}</span>
                </button>
              ))}
            </div>
          </div>

          <div className="p-3 rounded-2xl bg-amber-500/10 border border-amber-500/20 flex items-center gap-3">
            <Flame className="w-5 h-5 text-amber-400 shrink-0" />
            <p className="text-[11px] text-amber-300 font-medium">Complete 1 more habit to maintain your 🔥 7-day streak!</p>
          </div>
        </div>
      </div>

      {/* KPI Metrics Grid */}
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-5">
        <div className="p-5 rounded-2xl bg-slate-900/80 border border-slate-800 flex items-center gap-4 hover:border-slate-700 transition-all">
          <div className="p-3 rounded-xl bg-indigo-500/10 text-indigo-400">
            <TrendingUp className="w-6 h-6" />
          </div>
          <div>
            <p className="text-xs text-slate-400">Overall Progress</p>
            <p className="text-xl font-extrabold text-white">68%</p>
          </div>
        </div>

        <div className="p-5 rounded-2xl bg-slate-900/80 border border-slate-800 flex items-center gap-4 hover:border-slate-700 transition-all">
          <div className="p-3 rounded-xl bg-purple-500/10 text-purple-400">
            <Clock className="w-6 h-6" />
          </div>
          <div>
            <p className="text-xs text-slate-400">Weekly Focus Time</p>
            <p className="text-xl font-extrabold text-white">12.4 Hours</p>
          </div>
        </div>

        <div className="p-5 rounded-2xl bg-slate-900/80 border border-slate-800 flex items-center gap-4 hover:border-slate-700 transition-all">
          <div className="p-3 rounded-xl bg-emerald-500/10 text-emerald-400">
            <CheckCircle2 className="w-6 h-6" />
          </div>
          <div>
            <p className="text-xs text-slate-400">Completed Lessons</p>
            <p className="text-xl font-extrabold text-white">14 Lessons</p>
          </div>
        </div>

        <div className="p-5 rounded-2xl bg-slate-900/80 border border-slate-800 flex items-center gap-4 hover:border-slate-700 transition-all">
          <div className="p-3 rounded-xl bg-amber-500/10 text-amber-400">
            <Award className="w-6 h-6" />
          </div>
          <div>
            <p className="text-xs text-slate-400">Earned Badges</p>
            <p className="text-xl font-extrabold text-white">5 Certificates</p>
          </div>
        </div>
      </div>

      {/* Main Learning Paths Feed with Filter Tabs */}
      <div className="space-y-4">
        <div className="flex flex-col sm:flex-row sm:items-center justify-between gap-4">
          <div>
            <h2 className="text-xl font-extrabold text-white">Your Learning Paths</h2>
            <p className="text-xs text-slate-400">Curated skill tracks tailored for your role</p>
          </div>

          {/* Filter Tabs */}
          <div className="flex items-center gap-1 p-1 bg-slate-900 rounded-xl border border-slate-800 self-start">
            <button
              onClick={() => setActiveFilter('all')}
              className={`px-3 py-1.5 text-xs font-semibold rounded-lg transition-all ${
                activeFilter === 'all' ? 'bg-indigo-600 text-white shadow-md' : 'text-slate-400 hover:text-slate-200'
              }`}
            >
              All Tracks
            </button>
            <button
              onClick={() => setActiveFilter('in_progress')}
              className={`px-3 py-1.5 text-xs font-semibold rounded-lg transition-all ${
                activeFilter === 'in_progress' ? 'bg-indigo-600 text-white shadow-md' : 'text-slate-400 hover:text-slate-200'
              }`}
            >
              In Progress
            </button>
            <button
              onClick={() => setActiveFilter('completed')}
              className={`px-3 py-1.5 text-xs font-semibold rounded-lg transition-all ${
                activeFilter === 'completed' ? 'bg-indigo-600 text-white shadow-md' : 'text-slate-400 hover:text-slate-200'
              }`}
            >
              Completed
            </button>
          </div>
        </div>

        {/* Path Cards Grid */}
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {paths.map((path) => (
            <div
              key={path.id}
              onClick={() => onSelectPath(path)}
              className="p-6 rounded-3xl bg-slate-900/80 border border-slate-800 hover:border-indigo-500/40 transition-all duration-300 cursor-pointer flex flex-col justify-between group hover:-translate-y-1 shadow-xl hover:shadow-indigo-500/10"
            >
              <div className="space-y-4">
                <div className="flex items-center justify-between">
                  <span className="text-[10px] font-bold uppercase tracking-wider px-2.5 py-1 rounded-full bg-slate-800 text-indigo-400 border border-slate-700">
                    {path.level}
                  </span>
                  <div className="flex items-center gap-1 text-amber-400 text-xs font-bold font-mono">
                    <Star className="w-3.5 h-3.5 fill-amber-400" />
                    <span>{path.rating}</span>
                  </div>
                </div>

                <div>
                  <h3 className="text-lg font-bold text-white group-hover:text-indigo-300 transition-colors">
                    {path.title}
                  </h3>
                  <p className="text-xs text-slate-400 line-clamp-2 mt-1">{path.description}</p>
                </div>
              </div>

              <div className="mt-6 pt-4 border-t border-slate-800/80 space-y-3">
                <div className="flex items-center justify-between text-xs text-slate-400 font-medium">
                  <span>Progress</span>
                  <span className="text-white font-bold">{path.progressPercentage}%</span>
                </div>
                <div className="w-full h-1.5 bg-slate-950 rounded-full overflow-hidden">
                  <div
                    className="h-full bg-indigo-500 rounded-full transition-all duration-300"
                    style={{ width: `${path.progressPercentage}%` }}
                  />
                </div>

                <div className="flex items-center justify-between pt-1">
                  <span className="text-xs text-slate-500">{path.durationHours} Hours Total</span>
                  <div className="flex items-center gap-1 text-xs font-semibold text-indigo-400 group-hover:translate-x-1 transition-transform">
                    <span>View Curriculum</span>
                    <ArrowRight className="w-3.5 h-3.5" />
                  </div>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};
