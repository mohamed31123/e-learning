import React from 'react';
import { 
  CheckCircle2, 
  Circle, 
  PlayCircle, 
  FileText, 
  HelpCircle, 
  Clock, 
  Award, 
  ArrowLeft,
  ChevronRight,
  Share2
} from 'lucide-react';
import { LearningPath } from '../types';

interface PathDetailCurriculumProps {
  path: LearningPath;
  onBack: () => void;
  onStartLesson: (lessonId: number) => void;
  onStartQuiz: (quizId: number) => void;
}

export const PathDetailCurriculum: React.FC<PathDetailCurriculumProps> = ({
  path,
  onBack,
  onStartLesson,
  onStartQuiz
}) => {
  return (
    <div className="p-8 max-w-6xl mx-auto space-y-8">
      {/* Breadcrumb / Back button */}
      <div className="flex items-center justify-between">
        <button
          onClick={onBack}
          className="inline-flex items-center gap-2 text-xs font-semibold text-slate-400 hover:text-white transition-colors"
        >
          <ArrowLeft className="w-4 h-4" />
          <span>Back to Catalog</span>
        </button>

        <button className="p-2 rounded-xl bg-slate-800 text-slate-300 hover:bg-slate-700 transition">
          <Share2 className="w-4 h-4" />
        </button>
      </div>

      {/* Path Header Banner */}
      <div className="relative rounded-3xl bg-slate-900 border border-slate-800 p-8 md:p-10 space-y-6 overflow-hidden">
        <div className="flex flex-wrap items-center gap-3">
          <span className="px-3 py-1 rounded-full bg-indigo-500/20 text-indigo-400 border border-indigo-500/30 text-xs font-bold uppercase tracking-wider">
            {path.level} Path
          </span>
          <span className="text-xs text-slate-400 flex items-center gap-1">
            <Clock className="w-3.5 h-3.5" />
            {path.durationHours} Hours total duration
          </span>
        </div>

        <div className="max-w-3xl space-y-3">
          <h1 className="text-3xl md:text-4xl font-black text-white leading-tight">
            {path.title}
          </h1>
          <p className="text-slate-400 text-sm md:text-base leading-relaxed">
            {path.description}
          </p>
        </div>

        {/* Instructor Info */}
        <div className="pt-4 border-t border-slate-800 flex items-center justify-between flex-wrap gap-4">
          <div className="flex items-center gap-3">
            <img
              src={path.instructorAvatar}
              alt={path.instructorName}
              className="w-11 h-11 rounded-full object-cover ring-2 ring-indigo-500/40"
            />
            <div>
              <p className="text-sm font-bold text-white">{path.instructorName}</p>
              <p className="text-xs text-slate-400">{path.instructorRole}</p>
            </div>
          </div>

          <div className="flex items-center gap-4 bg-slate-800/80 px-6 py-3 rounded-2xl border border-slate-700">
            <div>
              <p className="text-[10px] uppercase text-slate-400 font-semibold">Your Progress</p>
              <p className="text-lg font-black text-indigo-400">{path.progressPercentage}%</p>
            </div>
            <div className="w-24 bg-slate-700 h-2 rounded-full overflow-hidden">
              <div
                className="bg-gradient-to-r from-indigo-500 to-purple-500 h-full rounded-full"
                style={{ width: `${path.progressPercentage}%` }}
              />
            </div>
          </div>
        </div>
      </div>

      {/* Course Curriculum List */}
      <div className="space-y-6">
        <div className="flex items-center justify-between">
          <h2 className="text-xl font-bold text-white">Course Curriculum</h2>
          <span className="text-xs text-slate-400 font-mono">
            {path.modules.length} Modules • {path.modules.reduce((acc, m) => acc + m.courses.length, 0)} Courses
          </span>
        </div>

        <div className="space-y-4">
          {path.modules.map((module, mIdx) => (
            <div
              key={module.id}
              className="bg-slate-900 border border-slate-800 rounded-2xl overflow-hidden"
            >
              {/* Module Header */}
              <div className="p-5 bg-slate-800/40 border-b border-slate-800 flex items-center justify-between">
                <div className="flex items-center gap-3">
                  <span className="w-7 h-7 rounded-lg bg-indigo-600/20 text-indigo-400 font-bold text-xs flex items-center justify-center border border-indigo-500/30">
                    0{mIdx + 1}
                  </span>
                  <h3 className="font-bold text-white text-base">{module.title}</h3>
                </div>

                {module.quiz && (
                  <button
                    onClick={() => onStartQuiz(module.quiz!.id)}
                    className="px-3.5 py-1.5 rounded-xl bg-purple-600/20 hover:bg-purple-600 text-purple-300 hover:text-white border border-purple-500/30 text-xs font-semibold transition flex items-center gap-1.5"
                  >
                    <HelpCircle className="w-3.5 h-3.5" />
                    <span>Module Validation Quiz</span>
                  </button>
                )}
              </div>

              {/* Module Courses & Lessons */}
              <div className="divide-y divide-slate-800/60">
                {module.courses.map((course) => (
                  <div key={course.id} className="p-5 space-y-3">
                    <h4 className="text-sm font-semibold text-slate-300 flex items-center gap-2">
                      <div className="w-1.5 h-1.5 rounded-full bg-indigo-500" />
                      {course.title}
                    </h4>

                    <div className="space-y-2 pl-4">
                      {course.lessons.map((lesson) => (
                        <div
                          key={lesson.id}
                          onClick={() => onStartLesson(lesson.id)}
                          className="group flex items-center justify-between p-3.5 rounded-xl bg-slate-950/40 hover:bg-slate-800/80 border border-slate-800/60 transition cursor-pointer"
                        >
                          <div className="flex items-center gap-3">
                            {lesson.completed ? (
                              <CheckCircle2 className="w-5 h-5 text-emerald-400 shrink-0" />
                            ) : (
                              <Circle className="w-5 h-5 text-slate-600 group-hover:text-indigo-400 shrink-0" />
                            )}
                            <div>
                              <p className="text-xs font-medium text-slate-200 group-hover:text-indigo-300 transition-colors">
                                {lesson.title}
                              </p>
                              <div className="flex items-center gap-2 text-[10px] text-slate-500 mt-0.5">
                                <span className="capitalize">{lesson.contentType}</span>
                                <span>•</span>
                                <span>{lesson.durationMinutes} mins</span>
                              </div>
                            </div>
                          </div>

                          <div className="flex items-center gap-2 text-slate-500 group-hover:text-indigo-400">
                            {lesson.contentType === 'video' ? (
                              <PlayCircle className="w-4 h-4" />
                            ) : (
                              <FileText className="w-4 h-4" />
                            )}
                            <ChevronRight className="w-4 h-4 opacity-0 group-hover:opacity-100 transition-opacity" />
                          </div>
                        </div>
                      ))}
                    </div>
                  </div>
                ))}
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};
