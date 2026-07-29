export type Role = 'LEARNER' | 'INSTRUCTOR' | 'ADMIN';

export interface User {
  id: number;
  fullName: string;
  email: string;
  role: Role;
  avatarUrl?: string;
}

export interface Lesson {
  id: number;
  title: string;
  durationMinutes: number;
  completed: boolean;
  contentType: 'video' | 'article' | 'quiz';
  contentUrl?: string;
  videoUrl?: string;
  markdownContent?: string;
}

export interface Course {
  id: number;
  title: string;
  description: string;
  lessons: Lesson[];
}

export interface QuizQuestion {
  id: number;
  questionText: string;
  options: string[];
  correctOptionIndex: number;
  explanation: string;
}

export interface Quiz {
  id: number;
  title: string;
  passingScore: number;
  questions: QuizQuestion[];
}

export interface LearningModule {
  id: number;
  title: string;
  courses: Course[];
  quiz?: Quiz;
}

export interface LearningPath {
  id: number;
  title: string;
  subtitle: string;
  description: string;
  instructorName: string;
  instructorRole: string;
  instructorAvatar: string;
  level: 'Beginner' | 'Intermediate' | 'Advanced';
  durationHours: number;
  enrolledCount: number;
  rating: number;
  bannerImage: string;
  progressPercentage: number;
  modules: LearningModule[];
}

export interface CertificateData {
  id: string;
  recipientName: string;
  pathTitle: string;
  issueDate: string;
  instructorName: string;
  credentialId: string;
}
