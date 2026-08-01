import React, { useState } from 'react';
import { 
  ArrowLeft, 
  CheckCircle, 
  Play, 
  FileText, 
  Download, 
  FolderArchive, 
  CheckCircle2
} from 'lucide-react';
import type { Lesson, LearningPath } from '../types';

interface LessonReaderProps {
  path: LearningPath;
  onBack: () => void;
}

export const LessonReader: React.FC<LessonReaderProps> = ({ path, onBack }) => {
  const currentLesson: Lesson = path.modules[0]?.courses[1]?.lessons[0] || {
    id: 303,
    title: 'Advanced Conditional Types & infer Keyword',
    durationMinutes: 25,
    completed: false,
    contentType: 'video',
    videoUrl: 'https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4',
    markdownContent: `Mapped types allow you to take an existing model and transform each of its properties into a new type. Think of them as a "map" function for your types.\n\n\`\`\`typescript\ntype OptionFlags<Type> = {\n  [Property in keyof Type]: boolean;\n};\n\`\`\``
  };

  const [isCompleted, setIsCompleted] = useState(currentLesson.completed);

  return (
    <div className="flex h-screen overflow-hidden bg-slate-950 text-slate-100">
      {/* Left Sidebar: Lesson Navigation List */}
      <div className="w-80 border-r border-slate-800 bg-slate-900 flex flex-col justify-between hidden md:flex">
        <div>
          <div className="p-5 border-b border-slate-800 flex items-center justify-between">
            <button
              onClick={onBack}
              className="flex items-center gap-2 text-xs font-semibold text-slate-400 hover:text-white transition"
            >
              <ArrowLeft className="w-4 h-4" />
              <span>Back to Path</span>
            </button>
            <span className="text-[11px] font-mono bg-indigo-950 text-indigo-300 px-2 py-0.5 rounded border border-indigo-800">
              68% Done
            </span>
          </div>

          <div className="p-4 space-y-4 overflow-y-auto max-h-[calc(100vh-140px)]">
            <h4 className="text-xs uppercase font-bold tracking-wider text-slate-500">
              Course Content
            </h4>

            {path.modules.flatMap((m) => m.courses).map((course) => (
              <div key={course.id} className="space-y-2">
                <p className="text-xs font-bold text-slate-300 px-2">{course.title}</p>
                <div className="space-y-1">
                  {course.lessons.map((lesson) => (
                    <button
                      key={lesson.id}
                      className={`w-full text-left px-3 py-2.5 rounded-xl text-xs flex items-center justify-between transition ${
                        lesson.id === currentLesson.id
                          ? 'bg-indigo-600 text-white font-semibold shadow-md'
                          : 'hover:bg-slate-800/80 text-slate-400'
                      }`}
                    >
                      <div className="flex items-center gap-2.5 truncate">
                        <Play className="w-3.5 h-3.5 shrink-0" />
                        <span className="truncate">{lesson.title}</span>
                      </div>
                      {lesson.completed && <CheckCircle2 className="w-3.5 h-3.5 text-emerald-400 shrink-0" />}
                    </button>
                  ))}
                </div>
              </div>
            ))}
          </div>
        </div>

        {/* Footer info inside sidebar */}
        <div className="p-4 border-t border-slate-800">
          <button className="w-full py-2 bg-slate-800 hover:bg-slate-700 text-xs font-medium rounded-xl text-slate-300 transition">
            Download Offline Notes (.PDF)
          </button>
        </div>
      </div>

      {/* Main Content Area */}
      <div className="flex-1 flex flex-col overflow-y-auto">
        {/* Header */}
        <div className="p-4 px-8 border-b border-slate-800/80 bg-slate-900/60 backdrop-blur-md flex items-center justify-between sticky top-0 z-20">
          <div className="flex items-center gap-3">
            <span className="px-2.5 py-1 rounded-md bg-indigo-500/20 text-indigo-400 text-xs font-bold uppercase">
              Lesson 03
            </span>
            <h2 className="text-base font-bold text-white truncate max-w-lg">
              {currentLesson.title}
            </h2>
          </div>

          <button
            onClick={() => setIsCompleted(!isCompleted)}
            className={`px-4 py-2 rounded-xl text-xs font-bold transition flex items-center gap-2 ${
              isCompleted
                ? 'bg-emerald-600/20 text-emerald-300 border border-emerald-500/30'
                : 'bg-indigo-600 hover:bg-indigo-500 text-white shadow-lg shadow-indigo-600/30'
            }`}
          >
            <CheckCircle className="w-4 h-4" />
            <span>{isCompleted ? 'Completed' : 'Mark Lesson as Completed'}</span>
          </button>
        </div>

        {/* Lesson Video Player (Matching design standard) */}
        <div className="p-8 space-y-8 max-w-5xl mx-auto w-full">
          <div className="relative rounded-3xl overflow-hidden bg-black aspect-video border border-slate-800 shadow-2xl group">
            <video
              src={currentLesson.videoUrl}
              controls
              className="w-full h-full object-cover"
              poster={path.bannerImage}
            />
          </div>

          {/* Lesson Title & Notes Section */}
          <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">
            <div className="lg:col-span-2 space-y-6">
              <div className="space-y-2">
                <h3 className="text-2xl font-black text-white">Lesson Overview & Notes</h3>
                <p className="text-slate-400 text-sm leading-relaxed">
                  Mapped types allow you to take an existing model and transform each of its properties into a new type. Think of them as a "map" function for your types.
                </p>
              </div>

              {/* Code Snippet Box */}
              <div className="p-5 rounded-2xl bg-slate-900 border border-slate-800 space-y-3">
                <div className="flex items-center justify-between text-xs text-slate-400 border-b border-slate-800 pb-2">
                  <span className="font-mono text-indigo-400">AdvancedConditionalTypes.ts</span>
                  <span>TypeScript 5.4</span>
                </div>
                <pre className="font-mono text-xs text-emerald-400 leading-relaxed overflow-x-auto">
{`type OptionFlags<Type> = {
  [Property in keyof Type]: boolean;
};

type FeatureFlags = OptionFlags<{
  darkMode: () => void;
  newUserOnboarding: () => void;
}>;`}
                </pre>
              </div>
            </div>

            {/* Resources & Download Sidebar */}
            <div className="space-y-4">
              <h4 className="text-xs uppercase font-bold tracking-wider text-slate-400">
                Lesson Resources
              </h4>

              <div className="p-4 rounded-2xl bg-slate-900 border border-slate-800 space-y-3">
                <div className="flex items-center gap-3 p-3 rounded-xl bg-slate-800/60 hover:bg-slate-800 transition cursor-pointer">
                  <FileText className="w-5 h-5 text-indigo-400" />
                  <div className="flex-1 truncate">
                    <p className="text-xs font-bold text-slate-200 truncate">lesson_summary.pdf</p>
                    <p className="text-[10px] text-slate-400">2.4 MB • PDF Document</p>
                  </div>
                  <Download className="w-4 h-4 text-slate-400" />
                </div>

                <div className="flex items-center gap-3 p-3 rounded-xl bg-slate-800/60 hover:bg-slate-800 transition cursor-pointer">
                  <FolderArchive className="w-5 h-5 text-purple-400" />
                  <div className="flex-1 truncate">
                    <p className="text-xs font-bold text-slate-200 truncate">exercise_files.zip</p>
                    <p className="text-[10px] text-slate-400">14.8 MB • Starter Code</p>
                  </div>
                  <Download className="w-4 h-4 text-slate-400" />
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
