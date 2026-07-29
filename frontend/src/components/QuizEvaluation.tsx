import React, { useState } from 'react';
import { HelpCircle, CheckCircle2, XCircle, ArrowLeft, RotateCcw, Award } from 'lucide-react';
import confetti from 'canvas-confetti';
import { Quiz } from '../types';

interface QuizEvaluationProps {
  quiz: Quiz;
  onBack: () => void;
  onViewCertificate: () => void;
}

export const QuizEvaluation: React.FC<QuizEvaluationProps> = ({
  quiz,
  onBack,
  onViewCertificate
}) => {
  const [selectedAnswers, setSelectedAnswers] = useState<{ [key: number]: number }>({});
  const [submitted, setSubmitted] = useState(false);
  const [scorePercentage, setScorePercentage] = useState(0);

  const handleSelect = (questionId: number, optionIdx: number) => {
    if (submitted) return;
    setSelectedAnswers({ ...selectedAnswers, [questionId]: optionIdx });
  };

  const handleSubmit = () => {
    let correctCount = 0;
    quiz.questions.forEach((q) => {
      if (selectedAnswers[q.id] === q.correctOptionIndex) {
        correctCount += 1;
      }
    });

    const finalScore = Math.round((correctCount / quiz.questions.length) * 100);
    setScorePercentage(finalScore);
    setSubmitted(true);

    if (finalScore >= quiz.passingScore) {
      confetti({
        particleCount: 100,
        spread: 70,
        origin: { y: 0.6 }
      });
    }
  };

  const passed = scorePercentage >= quiz.passingScore;

  return (
    <div className="p-8 max-w-4xl mx-auto space-y-8">
      {/* Header Bar */}
      <div className="flex items-center justify-between">
        <button
          onClick={onBack}
          className="flex items-center gap-2 text-xs font-semibold text-slate-400 hover:text-white transition"
        >
          <ArrowLeft className="w-4 h-4" />
          <span>Exit Evaluation</span>
        </button>

        <span className="text-xs text-indigo-400 font-mono font-bold bg-indigo-950 px-3 py-1 rounded-full border border-indigo-800">
          Passing Threshold: {quiz.passingScore}%
        </span>
      </div>

      {/* Quiz Banner */}
      <div className="bg-slate-900 border border-slate-800 rounded-3xl p-8 space-y-4">
        <div className="flex items-center gap-3">
          <div className="p-3 rounded-2xl bg-purple-500/10 text-purple-400 border border-purple-500/20">
            <HelpCircle className="w-6 h-6" />
          </div>
          <div>
            <h1 className="text-2xl font-black text-white">{quiz.title}</h1>
            <p className="text-xs text-slate-400">Answer all questions to validate module mastery.</p>
          </div>
        </div>
      </div>

      {/* Questions list */}
      <div className="space-y-6">
        {quiz.questions.map((question, qIdx) => {
          const userChoice = selectedAnswers[question.id];
          const isCorrect = userChoice === question.correctOptionIndex;

          return (
            <div
              key={question.id}
              className="bg-slate-900 border border-slate-800 rounded-2xl p-6 space-y-4"
            >
              <h3 className="font-bold text-slate-100 text-base flex items-start gap-3">
                <span className="w-6 h-6 rounded-lg bg-indigo-600/30 text-indigo-400 text-xs flex items-center justify-center shrink-0 border border-indigo-500/30">
                  {qIdx + 1}
                </span>
                <span>{question.questionText}</span>
              </h3>

              <div className="space-y-2.5 pl-9">
                {question.options.map((opt, optIdx) => {
                  let optStyle = 'bg-slate-950/60 border-slate-800 text-slate-300 hover:bg-slate-800';

                  if (selectedAnswers[question.id] === optIdx) {
                    optStyle = 'bg-indigo-600/20 border-indigo-500 text-indigo-200 font-semibold';
                  }

                  if (submitted) {
                    if (optIdx === question.correctOptionIndex) {
                      optStyle = 'bg-emerald-600/20 border-emerald-500 text-emerald-200 font-semibold';
                    } else if (selectedAnswers[question.id] === optIdx) {
                      optStyle = 'bg-rose-600/20 border-rose-500 text-rose-200';
                    }
                  }

                  return (
                    <button
                      key={optIdx}
                      onClick={() => handleSelect(question.id, optIdx)}
                      className={`w-full text-left p-3.5 rounded-xl border text-xs transition flex items-center justify-between ${optStyle}`}
                    >
                      <span>{opt}</span>
                      {submitted && optIdx === question.correctOptionIndex && (
                        <CheckCircle2 className="w-4 h-4 text-emerald-400" />
                      )}
                      {submitted && selectedAnswers[question.id] === optIdx && optIdx !== question.correctOptionIndex && (
                        <XCircle className="w-4 h-4 text-rose-400" />
                      )}
                    </button>
                  );
                })}
              </div>

              {submitted && (
                <div className="pl-9 pt-2 text-xs text-slate-400 bg-slate-950/40 p-3 rounded-xl border border-slate-800">
                  <span className="font-bold text-slate-300">Explanation: </span>
                  {question.explanation}
                </div>
              )}
            </div>
          );
        })}
      </div>

      {/* Submission / Results Action Footer */}
      {!submitted ? (
        <div className="pt-4 flex justify-end">
          <button
            onClick={handleSubmit}
            disabled={Object.keys(selectedAnswers).length < quiz.questions.length}
            className="px-8 py-3 rounded-xl bg-indigo-600 hover:bg-indigo-500 disabled:opacity-50 text-white font-bold text-sm shadow-xl shadow-indigo-600/30 transition"
          >
            Submit Quiz for Evaluation
          </button>
        </div>
      ) : (
        <div className={`p-8 rounded-3xl border text-center space-y-4 ${
          passed ? 'bg-emerald-950/40 border-emerald-500/40' : 'bg-rose-950/40 border-rose-500/40'
        }`}>
          <h2 className="text-2xl font-black text-white">
            {passed ? '🎉 Congratulations! You Passed!' : 'Needs Revision'}
          </h2>

          <p className="text-slate-300 text-sm">
            Your final score is <span className="font-extrabold text-xl">{scorePercentage}%</span>.
          </p>

          <div className="flex items-center justify-center gap-4 pt-2">
            {passed ? (
              <button
                onClick={onViewCertificate}
                className="px-6 py-3 rounded-xl bg-emerald-600 hover:bg-emerald-500 text-white font-bold text-xs shadow-lg flex items-center gap-2"
              >
                <Award className="w-4 h-4" />
                <span>Claim Certificate of Completion</span>
              </button>
            ) : (
              <button
                onClick={() => {
                  setSubmitted(false);
                  setSelectedAnswers({});
                }}
                className="px-6 py-3 rounded-xl bg-slate-800 hover:bg-slate-700 text-white font-bold text-xs shadow-lg flex items-center gap-2"
              >
                <RotateCcw className="w-4 h-4" />
                <span>Retake Assessment</span>
              </button>
            )}
          </div>
        </div>
      )}
    </div>
  );
};
