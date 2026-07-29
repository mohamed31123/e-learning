import React from 'react';
import { Search, Bell, Clock, BookOpen, Star, PlayCircle, ArrowRight, TrendingUp } from 'lucide-react';
import { LearningPath, User } from '../types';

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

  return (
    <div className="p-8 space-y-8 max-w-7xl mx-auto">
      {/* Top Header bar with Search and Notifications */}
      <div className="flex flex-col md:flex-row md:items-center justify-between gap-4">
        <div>
          <h2 className="text-2xl font-black text-slate-900 dark:text-white">
            Welcome back, {currentUser.fullName.split(' ')[0]} 👋
          </h2>
          <p className="text-slate-500 text-sm mt-0.5">
            Track your ongoing progress and explore newly published enterprise learning paths.
          </p>
        </div>

        <div className="flex items-center gap-4">
          <div className="relative w-72">
            <Search className="w-4 h-4 absolute left-3.5 top-1/2 -translate-y-1/2 text-slate-400" />
            <input
              type="text"
              placeholder="Search courses, paths, topics..."
              className="w-full bg-slate-100 dark:bg-slate-800/80 text-slate-800 dark:text-slate-200 pl-10 pr-4 py-2.5 rounded-xl text-sm border border-transparent focus:border-indigo-500 outline-none transition"
            />
          </div>

          <button className="p-2.5 rounded-xl bg-slate-100 dark:bg-slate-800 text-slate-600 dark:text-slate-300 hover:bg-slate-200 transition relative">
            <Bell className="w-5 h-5" />
            <span className="w-2 h-2 bg-indigo-500 rounded-full absolute top-2 right-2 ring-2 ring-slate-900" />
          </button>
        </div>
      </div>

      {/* Featured Continue Learning Hero Card (Blue/Purple Accent from Design) */}
      <div className="relative overflow-hidden rounded-3xl bg-gradient-to-r from-indigo-600 via-indigo-700 to-purple-700 text-white p-8 md:p-10 shadow-2xl shadow-indigo-600/20">
        <div className="relative z-10 grid grid-cols-1 lg:grid-cols-3 gap-8 items-center">
          <div className="lg:col-span-2 space-y-4">
            <div className="inline-flex items-center gap-2 px-3 py-1 rounded-full bg-white/10 backdrop-blur-md text-xs font-semibold text-indigo-100 border border-white/20">
              <PlayCircle className="w-3.5 h-3.5" />
              <span>Resume Current Learning Path</span>
            </div>

            <h3 className="text-3xl font-black tracking-tight leading-tight">
              {activePath.title}
            </h3>

            <p className="text-indigo-100 text-sm max-w-xl opacity-90 line-clamp-2">
              {activePath.subtitle}
            </p>

            <div className="pt-2 flex flex-wrap items-center gap-4">
              <button
                onClick={() => onOpenLesson(activePath)}
                className="px-6 py-3 rounded-xl bg-white text-indigo-700 font-bold text-sm hover:bg-indigo-50 transition shadow-lg flex items-center gap-2"
              >
                <span>Resume: Advanced Conditional Types</span>
                <ArrowRight className="w-4 h-4" />
              </button>

              <button
                onClick={() => onSelectPath(activePath)}
                className="px-5 py-3 rounded-xl bg-indigo-800/60 hover:bg-indigo-800/90 text-white font-medium text-sm border border-indigo-400/30 transition"
              >
                View Syllabus
              </button>
            </div>
          </div>

          {/* Progress Circular Widget */}
          <div className="flex flex-col items-center justify-center bg-white/10 backdrop-blur-lg p-6 rounded-2xl border border-white/20">
            <div className="relative w-32 h-32 flex items-center justify-center">
              <svg className="w-full h-full transform -rotate-90">
                <circle
                  cx="64"
                  cy="64"
                  r="52"
                  stroke="currentColor"
                  strokeWidth="10"
                  className="text-indigo-900/40"
                  fill="transparent"
                />
                <circle
                  cx="64"
                  cy="64"
                  r="52"
                  stroke="currentColor"
                  strokeWidth="10"
                  strokeDasharray={326}
                  strokeDashoffset={326 - (326 * activePath.progressPercentage) / 100}
                  className="text-white transition-all duration-1000 ease-out"
                  strokeLinecap="round"
                  fill="transparent"
                />
              </svg>
              <div className="absolute flex flex-col items-center">
                <span className="text-3xl font-black">{activePath.progressPercentage}%</span>
                <span className="text-[10px] text-indigo-200 uppercase font-semibold">Overall Completed</span>
              </div>
            </div>
          </div>
        </div>

        {/* Ambient background decoration */}
        <div className="absolute -right-10 -bottom-10 w-80 h-80 bg-white/10 rounded-full blur-3xl pointer-events-none" />
      </div>

      {/* Path Catalog Section */}
      <div className="space-y-6">
        <div className="flex items-center justify-between">
          <div>
            <h3 className="text-xl font-bold text-slate-900 dark:text-white">Path Catalog</h3>
            <p className="text-slate-500 text-xs">Curated learning journeys for modern software engineers.</p>
          </div>

          <div className="flex items-center gap-2">
            <button className="px-3 py-1.5 text-xs font-semibold rounded-lg bg-indigo-600 text-white">All Paths</button>
            <button className="px-3 py-1.5 text-xs font-semibold rounded-lg text-slate-400 hover:text-slate-200">Frontend</button>
            <button className="px-3 py-1.5 text-xs font-semibold rounded-lg text-slate-400 hover:text-slate-200">Backend</button>
          </div>
        </div>

        <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
          {paths.map((path) => (
            <div
              key={path.id}
              className="group bg-white dark:bg-slate-900 rounded-2xl border border-slate-200 dark:border-slate-800 overflow-hidden hover:shadow-xl hover:border-indigo-500/50 transition-all duration-300 flex flex-col justify-between"
            >
              <div>
                <div className="relative h-44 overflow-hidden">
                  <img
                    src={path.bannerImage}
                    alt={path.title}
                    className="w-full h-full object-cover group-hover:scale-105 transition-transform duration-500"
                  />
                  <div className="absolute top-3 left-3 bg-slate-900/80 backdrop-blur-md px-2.5 py-1 rounded-lg text-[11px] font-bold text-indigo-400 border border-slate-700">
                    {path.level}
                  </div>
                  <div className="absolute top-3 right-3 bg-slate-900/80 backdrop-blur-md px-2.5 py-1 rounded-lg text-[11px] font-bold text-amber-400 flex items-center gap-1 border border-slate-700">
                    <Star className="w-3 h-3 fill-amber-400" />
                    <span>{path.rating}</span>
                  </div>
                </div>

                <div className="p-5 space-y-3">
                  <h4 className="font-bold text-slate-900 dark:text-white line-clamp-1 group-hover:text-indigo-400 transition-colors">
                    {path.title}
                  </h4>
                  <p className="text-slate-500 dark:text-slate-400 text-xs line-clamp-2 leading-relaxed">
                    {path.subtitle}
                  </p>

                  <div className="flex items-center gap-3 pt-2">
                    <img
                      src={path.instructorAvatar}
                      alt={path.instructorName}
                      className="w-7 h-7 rounded-full object-cover"
                    />
                    <span className="text-xs text-slate-700 dark:text-slate-300 font-medium">
                      {path.instructorName}
                    </span>
                  </div>
                </div>
              </div>

              <div className="p-5 pt-0 space-y-4">
                <div className="flex items-center justify-between text-xs text-slate-400 border-t border-slate-100 dark:border-slate-800 pt-3">
                  <span className="flex items-center gap-1">
                    <Clock className="w-3.5 h-3.5" />
                    {path.durationHours}h total
                  </span>
                  <span className="flex items-center gap-1">
                    <BookOpen className="w-3.5 h-3.5" />
                    {path.modules.length} modules
                  </span>
                </div>

                <button
                  onClick={() => onSelectPath(path)}
                  className="w-full py-2.5 rounded-xl bg-slate-100 dark:bg-slate-800 hover:bg-indigo-600 hover:text-white text-slate-800 dark:text-slate-200 text-xs font-bold transition-all duration-200"
                >
                  Enroll / View Details
                </button>
              </div>
            </div>
          ))}
        </div>
      </div>

      {/* Weekly Stats Footer Widget */}
      <div className="grid grid-cols-1 md:grid-cols-4 gap-4">
        <div className="p-5 rounded-2xl bg-white dark:bg-slate-900 border border-slate-200 dark:border-slate-800 flex items-center gap-4">
          <div className="p-3 rounded-xl bg-indigo-500/10 text-indigo-500">
            <TrendingUp className="w-6 h-6" />
          </div>
          <div>
            <p className="text-xs text-slate-500">Weekly Learning Focus</p>
            <p className="text-lg font-extrabold text-slate-900 dark:text-white">12.4 Hours</p>
          </div>
        </div>

        <div className="p-5 rounded-2xl bg-white dark:bg-slate-900 border border-slate-200 dark:border-slate-800 flex items-center gap-4">
          <div className="p-3 rounded-xl bg-emerald-500/10 text-emerald-500">
            <CheckCircle2 className="w-6 h-6" />
          </div>
          <div>
            <p className="text-xs text-slate-500">Completed Lessons</p>
            <p className="text-lg font-extrabold text-slate-900 dark:text-white">12 Lessons</p>
          </div>
        </div>

        <div className="p-5 rounded-2xl bg-white dark:bg-slate-900 border border-slate-200 dark:border-slate-800 flex items-center gap-4">
          <div className="p-3 rounded-xl bg-amber-500/10 text-amber-500">
            <Star className="w-6 h-6" />
          </div>
          <div>
            <p className="text-xs text-slate-500">Quiz Passing Avg</p>
            <p className="text-lg font-extrabold text-slate-900 dark:text-white">94% Score</p>
          </div>
        </div>

        <div className="p-5 rounded-2xl bg-white dark:bg-slate-900 border border-slate-200 dark:border-slate-800 flex items-center gap-4">
          <div className="p-3 rounded-xl bg-purple-500/10 text-purple-500">
            <BookOpen className="w-6 h-6" />
          </div>
          <div>
            <p className="text-xs text-slate-500">Certificates Earned</p>
            <p className="text-lg font-extrabold text-slate-900 dark:text-white">03 Verified</p>
          </div>
        </div>
      </div>
    </div>
  );
};
