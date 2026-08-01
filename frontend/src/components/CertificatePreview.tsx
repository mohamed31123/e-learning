import React from 'react';
import { Download, Share2, ArrowLeft, ShieldCheck } from 'lucide-react';
import type { CertificateData } from '../types';

interface CertificatePreviewProps {
  certificate: CertificateData;
  onBack: () => void;
}

export const CertificatePreview: React.FC<CertificatePreviewProps> = ({
  certificate,
  onBack
}) => {
  return (
    <div className="p-8 max-w-5xl mx-auto space-y-8">
      {/* Action Header */}
      <div className="flex items-center justify-between">
        <button
          onClick={onBack}
          className="flex items-center gap-2 text-xs font-semibold text-slate-400 hover:text-white transition"
        >
          <ArrowLeft className="w-4 h-4" />
          <span>Back to My Dashboard</span>
        </button>

        <div className="flex items-center gap-3">
          <button className="px-4 py-2 rounded-xl bg-slate-800 hover:bg-slate-700 text-white text-xs font-bold transition flex items-center gap-2">
            <Share2 className="w-4 h-4" />
            <span>Share Credential</span>
          </button>

          <button className="px-5 py-2 rounded-xl bg-indigo-600 hover:bg-indigo-500 text-white text-xs font-bold transition flex items-center gap-2 shadow-lg shadow-indigo-600/30">
            <Download className="w-4 h-4" />
            <span>Download PDF</span>
          </button>
        </div>
      </div>

      {/* Official Certificate Paper Frame */}
      <div className="relative rounded-3xl bg-slate-900 border-2 border-amber-500/40 p-12 text-center space-y-8 shadow-2xl overflow-hidden">
        {/* Certificate Watermark Background Decorative Ring */}
        <div className="absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-96 h-96 border border-amber-500/10 rounded-full pointer-events-none" />

        {/* Certificate Header */}
        <div className="space-y-2">
          <div className="inline-flex items-center gap-2 px-3 py-1 rounded-full bg-amber-500/10 text-amber-400 border border-amber-500/30 text-xs font-mono font-semibold">
            <ShieldCheck className="w-4 h-4" />
            <span>VERIFIED ACADEMIC CREDENTIAL</span>
          </div>
          <h1 className="text-3xl font-serif font-bold tracking-wide text-white">
            CERTIFICATE OF COMPLETION
          </h1>
          <p className="text-xs text-slate-400 font-mono">EduFlow Professional Growth Platform</p>
        </div>

        {/* Recipient Details */}
        <div className="space-y-4 max-w-xl mx-auto py-4">
          <p className="text-xs uppercase tracking-widest text-slate-400">This is to certify that</p>
          <h2 className="text-3xl font-black text-amber-300 font-serif border-b border-amber-500/30 pb-3">
            {certificate.recipientName}
          </h2>
          <p className="text-xs text-slate-300 leading-relaxed">
            has successfully completed all requirements, practical lab assessments, and final examinations for the structured learning path:
          </p>
          <h3 className="text-xl font-bold text-white">
            {certificate.pathTitle}
          </h3>
        </div>

        {/* Signatures & Issue Metadata */}
        <div className="pt-8 border-t border-slate-800 grid grid-cols-2 md:grid-cols-3 gap-6 text-left text-xs max-w-2xl mx-auto">
          <div>
            <p className="text-slate-500 font-mono">Issued On</p>
            <p className="font-bold text-white mt-1">{certificate.issueDate}</p>
          </div>

          <div>
            <p className="text-slate-500 font-mono">Lead Instructor</p>
            <p className="font-bold text-white mt-1">{certificate.instructorName}</p>
          </div>

          <div>
            <p className="text-slate-500 font-mono">Credential ID</p>
            <p className="font-mono text-amber-400 mt-1">{certificate.credentialId}</p>
          </div>
        </div>
      </div>
    </div>
  );
};
