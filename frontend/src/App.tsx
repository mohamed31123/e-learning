import { useState } from 'react';
import { Sidebar } from './components/Sidebar';
import { LearnerDashboard } from './components/LearnerDashboard';
import { PathDetailCurriculum } from './components/PathDetailCurriculum';
import { LessonReader } from './components/LessonReader';
import { QuizEvaluation } from './components/QuizEvaluation';
import { CertificatePreview } from './components/CertificatePreview';
import { AdminPanel } from './components/AdminPanel';

import { mockLearningPaths, mockCurrentUser } from './mockData';
import type { LearningPath, CertificateData } from './types';

export function App() {
  const [currentTab, setCurrentTab] = useState<string>('dashboard');
  const [selectedPath, setSelectedPath] = useState<LearningPath>(mockLearningPaths[0]);

  // Certificate State
  const sampleCertificate: CertificateData = {
    id: 'cert-9982',
    recipientName: mockCurrentUser.fullName,
    pathTitle: selectedPath.title,
    issueDate: 'July 29, 2026',
    instructorName: selectedPath.instructorName,
    credentialId: 'EDU-TS-2026-9817'
  };

  const handleSelectPath = (path: LearningPath) => {
    setSelectedPath(path);
    setCurrentTab('curriculum');
  };

  const handleOpenLesson = (path: LearningPath) => {
    setSelectedPath(path);
    setCurrentTab('reader');
  };

  return (
    <div className="min-h-screen bg-slate-950 text-slate-100 flex font-sans antialiased selection:bg-indigo-500 selection:text-white">
      {/* Persistent App Sidebar navigation */}
      {currentTab !== 'reader' && (
        <Sidebar
          currentTab={currentTab}
          setCurrentTab={setCurrentTab}
          currentUser={mockCurrentUser}
        />
      )}

      {/* Main View Router */}
      <main className="flex-1 overflow-x-hidden overflow-y-auto">
        {currentTab === 'dashboard' && (
          <LearnerDashboard
            paths={mockLearningPaths}
            currentUser={mockCurrentUser}
            onSelectPath={handleSelectPath}
            onOpenLesson={handleOpenLesson}
          />
        )}

        {currentTab === 'curriculum' && (
          <PathDetailCurriculum
            path={selectedPath}
            onBack={() => setCurrentTab('dashboard')}
            onStartLesson={() => setCurrentTab('reader')}
            onStartQuiz={() => setCurrentTab('quiz')}
          />
        )}

        {currentTab === 'reader' && (
          <LessonReader
            path={selectedPath}
            onBack={() => setCurrentTab('curriculum')}
          />
        )}

        {currentTab === 'quiz' && selectedPath.modules[0]?.quiz && (
          <QuizEvaluation
            quiz={selectedPath.modules[0].quiz}
            onBack={() => setCurrentTab('curriculum')}
            onViewCertificate={() => setCurrentTab('certificates')}
          />
        )}

        {currentTab === 'certificates' && (
          <CertificatePreview
            certificate={sampleCertificate}
            onBack={() => setCurrentTab('dashboard')}
          />
        )}

        {currentTab === 'admin' && (
          <AdminPanel
            paths={mockLearningPaths}
            onAddNewPath={() => alert('Path creation modal triggered')}
          />
        )}
      </main>
    </div>
  );
}

export default App;
