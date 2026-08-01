import type { LearningPath, User } from './types';

export const mockCurrentUser: User = {
  id: 1,
  fullName: 'Alex Vance',
  email: 'alex.vance@eduflow.io',
  role: 'LEARNER',
  avatarUrl: 'https://images.unsplash.com/photo-1534528741775-53994a69daeb?auto=format&fit=crop&w=250&q=80',
};

export const mockLearningPaths: LearningPath[] = [
  {
    id: 1,
    title: 'Advanced TypeScript Patterns & Architectures',
    subtitle: 'Master the art of scalable type systems for modern enterprise applications.',
    description: 'Master the art of scalable type systems. This path delves deep into the intricate mechanisms of TypeScript, from advanced generics and conditional types to mapped types and performance-optimized type architectures used in modern enterprise frameworks.',
    instructorName: 'Alex Rivera',
    instructorRole: 'Principal Systems Architect',
    instructorAvatar: 'https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?auto=format&fit=crop&w=250&q=80',
    level: 'Advanced',
    durationHours: 24,
    enrolledCount: 1248,
    rating: 4.9,
    progressPercentage: 68,
    bannerImage: 'https://images.unsplash.com/photo-1555066931-4365d14bab8c?auto=format&fit=crop&w=1200&q=80',
    modules: [
      {
        id: 101,
        title: 'Generics & Utility Types',
        courses: [
          {
            id: 201,
            title: 'Foundations of Generic Constraints',
            description: 'Learn how to bound generic type parameters cleanly.',
            lessons: [
              {
                id: 301,
                title: 'Understanding Generic Constraints (`extends`)',
                durationMinutes: 15,
                completed: true,
                contentType: 'video',
                videoUrl: 'https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4',
                markdownContent: 'Generic constraints allow you to restrict the types that can be passed to a type parameter. By using `T extends CertainType`, you ensure T has required fields while preserving exact output types.'
              },
              {
                id: 302,
                title: 'Mapping & Mutability in State Machines',
                durationMinutes: 20,
                completed: true,
                contentType: 'article',
                markdownContent: 'Immutable state updates are essential in modern React architecture. Learn how `readonly` utility wrappers enforce strict compile-time immutability across complex nested objects.'
              }
            ]
          },
          {
            id: 202,
            title: 'Advanced Inference with `infer`',
            description: 'Extract internal component types dynamically.',
            lessons: [
              {
                id: 303,
                title: 'Advanced Conditional Types & infer Keyword',
                durationMinutes: 25,
                completed: false,
                contentType: 'video',
                videoUrl: 'https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4',
                markdownContent: 'Mapped types allow you to take an existing model and transform each of its properties into a new type. Think of them as a "map" function for your types.\n\n```typescript\ntype OptionFlags<Type> = {\n  [Property in keyof Type]: boolean;\n};\n```'
              }
            ]
          }
        ],
        quiz: {
          id: 501,
          title: 'Generics & Conditional Types Mastery Quiz',
          passingScore: 80,
          questions: [
            {
              id: 601,
              questionText: 'What does `T extends infer U ? U : never` do in conditional types?',
              options: [
                'It causes a compiler error.',
                'It extracts and infers the underlying type T into variable U.',
                'It casts T to an array.',
                'It evaluates to null at runtime.'
              ],
              correctOptionIndex: 1,
              explanation: 'The `infer` keyword enables pattern matching inside conditional types to extract inner type parameters.'
            },
            {
              id: 602,
              questionText: 'Which TypeScript operator retrieves all key names of a given type as a union string?',
              options: ['typeof', 'keyof', 'instanceof', 'enumof'],
              correctOptionIndex: 1,
              explanation: 'The `keyof` operator takes an object type and produces a string or numeric literal union of its keys.'
            }
          ]
        }
      },
      {
        id: 102,
        title: 'Mapped & Conditional Types',
        courses: [
          {
            id: 203,
            title: 'Building Type-Safe Event Emitters',
            description: 'Construct end-to-end safe custom dispatchers.',
            lessons: [
              {
                id: 304,
                title: 'Mapped Type Modifiers (-readonly, +?)',
                durationMinutes: 18,
                completed: false,
                contentType: 'article',
                markdownContent: 'Modifying property modifiers allows creating mutable or required copies of existing strict interface contracts.'
              }
            ]
          }
        ]
      }
    ]
  },
  {
    id: 2,
    title: 'Frontend System Design & Micro-Frontends',
    subtitle: 'Scale web applications across distributed engineering teams.',
    description: 'Learn architectural concepts such as module federation, state hydration, web components, performance monitoring, and asset lazy loading in enterprise setups.',
    instructorName: 'Sarah Chen',
    instructorRole: 'Staff Frontend Engineer',
    instructorAvatar: 'https://images.unsplash.com/photo-1494790108377-be9c29b29330?auto=format&fit=crop&w=250&q=80',
    level: 'Intermediate',
    durationHours: 18,
    enrolledCount: 940,
    rating: 4.8,
    progressPercentage: 42,
    bannerImage: 'https://images.unsplash.com/photo-1507238691740-187a5b1d37b8?auto=format&fit=crop&w=1200&q=80',
    modules: [
      {
        id: 103,
        title: 'Module Federation Essentials',
        courses: [
          {
            id: 204,
            title: 'Webpack & Vite Federation Plugins',
            description: 'Configure runtime container sharing.',
            lessons: [
              {
                id: 305,
                title: 'Shared Dependencies & Version Mismatches',
                durationMinutes: 30,
                completed: true,
                contentType: 'video',
                videoUrl: 'https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4',
                markdownContent: 'Avoid duplicate bundle downloads by enforcing strict singleton shared scopes for core React runtimes.'
              }
            ]
          }
        ]
      }
    ]
  },
  {
    id: 3,
    title: 'Fullstack Next.js 15 & Spring Boot Cloud',
    subtitle: 'Connect modern React Server Components with robust Spring Security APIs.',
    description: 'End-to-end guide building resilient microservices with Spring Boot 3.5 and React 19 Frontend architecture.',
    instructorName: 'Jordan Smyth',
    instructorRole: 'Head of Cloud Architecture',
    instructorAvatar: 'https://images.unsplash.com/photo-1500648767791-00dcc994a43e?auto=format&fit=crop&w=250&q=80',
    level: 'Advanced',
    durationHours: 32,
    enrolledCount: 2150,
    rating: 4.95,
    progressPercentage: 15,
    bannerImage: 'https://images.unsplash.com/photo-1461749280684-dccba630e2f6?auto=format&fit=crop&w=1200&q=80',
    modules: []
  }
];
