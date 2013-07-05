/*     */ package com.jcraft.jorbis;
/*     */ 
/*     */ import com.jcraft.jogg.Buffer;
/*     */ 
/*     */ class Mapping0 extends FuncMapping
/*     */ {
/*  32 */   static int seq = 0;
/*     */   float[][] pcmbundle;
/*     */   int[] zerobundle;
/*     */   int[] nonzero;
/*     */   Object[] floormemo;
/*     */ 
/*     */   Mapping0()
/*     */   {
/* 186 */     this.pcmbundle = ((float[][])null);
/* 187 */     this.zerobundle = null;
/* 188 */     this.nonzero = null;
/* 189 */     this.floormemo = null;
/*     */   }
/*     */ 
/*     */   void free_info(Object imap)
/*     */   {
/*     */   }
/*     */ 
/*     */   void free_look(Object imap)
/*     */   {
/*     */   }
/*     */ 
/*     */   Object look(DspState vd, InfoMode vm, Object m)
/*     */   {
/*  42 */     Info vi = vd.vi;
/*  43 */     LookMapping0 look = new LookMapping0();
/*  44 */     InfoMapping0 info = look.map = (InfoMapping0)m;
/*  45 */     look.mode = vm;
/*     */ 
/*  47 */     look.time_look = new Object[info.submaps];
/*  48 */     look.floor_look = new Object[info.submaps];
/*  49 */     look.residue_look = new Object[info.submaps];
/*     */ 
/*  51 */     look.time_func = new FuncTime[info.submaps];
/*  52 */     look.floor_func = new FuncFloor[info.submaps];
/*  53 */     look.residue_func = new FuncResidue[info.submaps];
/*     */ 
/*  55 */     for (int i = 0; i < info.submaps; i++) {
/*  56 */       int timenum = info.timesubmap[i];
/*  57 */       int floornum = info.floorsubmap[i];
/*  58 */       int resnum = info.residuesubmap[i];
/*     */ 
/*  60 */       look.time_func[i] = FuncTime.time_P[vi.time_type[timenum]];
/*  61 */       look.time_look[i] = look.time_func[i].look(vd, vm, vi.time_param[timenum]);
/*  62 */       look.floor_func[i] = FuncFloor.floor_P[vi.floor_type[floornum]];
/*  63 */       look.floor_look[i] = look.floor_func[i].look(vd, vm, vi.floor_param[floornum]);
/*     */ 
/*  65 */       look.residue_func[i] = FuncResidue.residue_P[vi.residue_type[resnum]];
/*  66 */       look.residue_look[i] = look.residue_func[i].look(vd, vm, vi.residue_param[resnum]);
/*     */     }
/*     */ 
/*  71 */     if ((vi.psys != 0) && (vd.analysisp != 0));
/*  75 */     look.ch = vi.channels;
/*     */ 
/*  77 */     return look;
/*     */   }
/*     */ 
/*     */   void pack(Info vi, Object imap, Buffer opb) {
/*  81 */     InfoMapping0 info = (InfoMapping0)imap;
/*     */ 
/*  90 */     if (info.submaps > 1) {
/*  91 */       opb.write(1, 1);
/*  92 */       opb.write(info.submaps - 1, 4);
/*     */     }
/*     */     else {
/*  95 */       opb.write(0, 1);
/*     */     }
/*     */ 
/*  98 */     if (info.coupling_steps > 0) {
/*  99 */       opb.write(1, 1);
/* 100 */       opb.write(info.coupling_steps - 1, 8);
/* 101 */       for (int i = 0; i < info.coupling_steps; i++) {
/* 102 */         opb.write(info.coupling_mag[i], Util.ilog2(vi.channels));
/* 103 */         opb.write(info.coupling_ang[i], Util.ilog2(vi.channels));
/*     */       }
/*     */     }
/*     */     else {
/* 107 */       opb.write(0, 1);
/*     */     }
/*     */ 
/* 110 */     opb.write(0, 2);
/*     */ 
/* 113 */     if (info.submaps > 1) {
/* 114 */       for (int i = 0; i < vi.channels; i++)
/* 115 */         opb.write(info.chmuxlist[i], 4);
/*     */     }
/* 117 */     for (int i = 0; i < info.submaps; i++) {
/* 118 */       opb.write(info.timesubmap[i], 8);
/* 119 */       opb.write(info.floorsubmap[i], 8);
/* 120 */       opb.write(info.residuesubmap[i], 8);
/*     */     }
/*     */   }
/*     */ 
/*     */   Object unpack(Info vi, Buffer opb)
/*     */   {
/* 126 */     InfoMapping0 info = new InfoMapping0();
/*     */ 
/* 128 */     if (opb.read(1) != 0) {
/* 129 */       info.submaps = (opb.read(4) + 1);
/*     */     }
/*     */     else {
/* 132 */       info.submaps = 1;
/*     */     }
/*     */ 
/* 135 */     if (opb.read(1) != 0) {
/* 136 */       info.coupling_steps = (opb.read(8) + 1);
/*     */ 
/* 138 */       for (int i = 0; i < info.coupling_steps; i++) {
/* 139 */         int testM = info.coupling_mag[i] = opb.read(Util.ilog2(vi.channels));
/* 140 */         int testA = info.coupling_ang[i] = opb.read(Util.ilog2(vi.channels));
/*     */ 
/* 142 */         if ((testM < 0) || (testA < 0) || (testM == testA) || (testM >= vi.channels) || (testA >= vi.channels))
/*     */         {
/* 145 */           info.free();
/* 146 */           return null;
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 151 */     if (opb.read(2) > 0) {
/* 152 */       info.free();
/* 153 */       return null;
/*     */     }
/*     */ 
/* 156 */     if (info.submaps > 1) {
/* 157 */       for (int i = 0; i < vi.channels; i++) {
/* 158 */         info.chmuxlist[i] = opb.read(4);
/* 159 */         if (info.chmuxlist[i] >= info.submaps) {
/* 160 */           info.free();
/* 161 */           return null;
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 166 */     for (int i = 0; i < info.submaps; i++) {
/* 167 */       info.timesubmap[i] = opb.read(8);
/* 168 */       if (info.timesubmap[i] >= vi.times) {
/* 169 */         info.free();
/* 170 */         return null;
/*     */       }
/* 172 */       info.floorsubmap[i] = opb.read(8);
/* 173 */       if (info.floorsubmap[i] >= vi.floors) {
/* 174 */         info.free();
/* 175 */         return null;
/*     */       }
/* 177 */       info.residuesubmap[i] = opb.read(8);
/* 178 */       if (info.residuesubmap[i] >= vi.residues) {
/* 179 */         info.free();
/* 180 */         return null;
/*     */       }
/*     */     }
/* 183 */     return info;
/*     */   }
/*     */ 
/*     */   synchronized int inverse(Block vb, Object l)
/*     */   {
/* 192 */     DspState vd = vb.vd;
/* 193 */     Info vi = vd.vi;
/* 194 */     LookMapping0 look = (LookMapping0)l;
/* 195 */     InfoMapping0 info = look.map;
/* 196 */     InfoMode mode = look.mode;
/* 197 */     int n = vb.pcmend = vi.blocksizes[vb.W];
/*     */ 
/* 199 */     float[] window = vd.window[vb.W][vb.lW][vb.nW][mode.windowtype];
/* 200 */     if ((this.pcmbundle == null) || (this.pcmbundle.length < vi.channels)) {
/* 201 */       this.pcmbundle = new float[vi.channels][];
/* 202 */       this.nonzero = new int[vi.channels];
/* 203 */       this.zerobundle = new int[vi.channels];
/* 204 */       this.floormemo = new Object[vi.channels];
/*     */     }
/*     */ 
/* 213 */     for (int i = 0; i < vi.channels; i++) {
/* 214 */       float[] pcm = vb.pcm[i];
/* 215 */       int submap = info.chmuxlist[i];
/*     */ 
/* 217 */       this.floormemo[i] = look.floor_func[submap].inverse1(vb, look.floor_look[submap], this.floormemo[i]);
/*     */ 
/* 219 */       if (this.floormemo[i] != null) {
/* 220 */         this.nonzero[i] = 1;
/*     */       }
/*     */       else {
/* 223 */         this.nonzero[i] = 0;
/*     */       }
/* 225 */       for (int j = 0; j < n / 2; j++) {
/* 226 */         pcm[j] = 0.0F;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 231 */     for (int i = 0; i < info.coupling_steps; i++) {
/* 232 */       if ((this.nonzero[info.coupling_mag[i]] != 0) || (this.nonzero[info.coupling_ang[i]] != 0)) {
/* 233 */         this.nonzero[info.coupling_mag[i]] = 1;
/* 234 */         this.nonzero[info.coupling_ang[i]] = 1;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 240 */     for (int i = 0; i < info.submaps; i++) {
/* 241 */       int ch_in_bundle = 0;
/* 242 */       for (int j = 0; j < vi.channels; j++) {
/* 243 */         if (info.chmuxlist[j] == i) {
/* 244 */           if (this.nonzero[j] != 0) {
/* 245 */             this.zerobundle[ch_in_bundle] = 1;
/*     */           }
/*     */           else {
/* 248 */             this.zerobundle[ch_in_bundle] = 0;
/*     */           }
/* 250 */           this.pcmbundle[(ch_in_bundle++)] = vb.pcm[j];
/*     */         }
/*     */       }
/*     */ 
/* 254 */       look.residue_func[i].inverse(vb, look.residue_look[i], this.pcmbundle, this.zerobundle, ch_in_bundle);
/*     */     }
/*     */ 
/* 258 */     for (int i = info.coupling_steps - 1; i >= 0; i--) {
/* 259 */       float[] pcmM = vb.pcm[info.coupling_mag[i]];
/* 260 */       float[] pcmA = vb.pcm[info.coupling_ang[i]];
/*     */ 
/* 262 */       for (int j = 0; j < n / 2; j++) {
/* 263 */         float mag = pcmM[j];
/* 264 */         float ang = pcmA[j];
/*     */ 
/* 266 */         if (mag > 0.0F) {
/* 267 */           if (ang > 0.0F) {
/* 268 */             pcmM[j] = mag;
/* 269 */             pcmA[j] = (mag - ang);
/*     */           }
/*     */           else {
/* 272 */             pcmA[j] = mag;
/* 273 */             pcmM[j] = (mag + ang);
/*     */           }
/*     */ 
/*     */         }
/* 277 */         else if (ang > 0.0F) {
/* 278 */           pcmM[j] = mag;
/* 279 */           pcmA[j] = (mag + ang);
/*     */         }
/*     */         else {
/* 282 */           pcmA[j] = mag;
/* 283 */           pcmM[j] = (mag - ang);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 291 */     for (int i = 0; i < vi.channels; i++) {
/* 292 */       float[] pcm = vb.pcm[i];
/* 293 */       int submap = info.chmuxlist[i];
/* 294 */       look.floor_func[submap].inverse2(vb, look.floor_look[submap], this.floormemo[i], pcm);
/*     */     }
/*     */ 
/* 301 */     for (int i = 0; i < vi.channels; i++) {
/* 302 */       float[] pcm = vb.pcm[i];
/*     */ 
/* 304 */       ((Mdct)vd.transform[vb.W][0]).backward(pcm, pcm);
/*     */     }
/*     */ 
/* 311 */     for (int i = 0; i < vi.channels; i++) {
/* 312 */       float[] pcm = vb.pcm[i];
/* 313 */       if (this.nonzero[i] != 0) {
/* 314 */         for (int j = 0; j < n; j++) {
/* 315 */           pcm[j] *= window[j];
/*     */         }
/*     */       }
/*     */       else {
/* 319 */         for (int j = 0; j < n; j++) {
/* 320 */           pcm[j] = 0.0F;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 328 */     return 0;
/*     */   }
/*     */ 
/*     */   class LookMapping0
/*     */   {
/*     */     InfoMode mode;
/*     */     Mapping0.InfoMapping0 map;
/*     */     Object[] time_look;
/*     */     Object[] floor_look;
/*     */     Object[] floor_state;
/*     */     Object[] residue_look;
/*     */     PsyLook[] psy_look;
/*     */     FuncTime[] time_func;
/*     */     FuncFloor[] floor_func;
/*     */     FuncResidue[] residue_func;
/*     */     int ch;
/*     */     float[][] decay;
/*     */     int lastframe;
/*     */ 
/*     */     LookMapping0()
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   class InfoMapping0
/*     */   {
/*     */     int submaps;
/* 333 */     int[] chmuxlist = new int[256];
/*     */ 
/* 335 */     int[] timesubmap = new int[16];
/* 336 */     int[] floorsubmap = new int[16];
/* 337 */     int[] residuesubmap = new int[16];
/* 338 */     int[] psysubmap = new int[16];
/*     */     int coupling_steps;
/* 341 */     int[] coupling_mag = new int[256];
/* 342 */     int[] coupling_ang = new int[256];
/*     */ 
/*     */     InfoMapping0() {  } 
/* 345 */     void free() { this.chmuxlist = null;
/* 346 */       this.timesubmap = null;
/* 347 */       this.floorsubmap = null;
/* 348 */       this.residuesubmap = null;
/* 349 */       this.psysubmap = null;
/*     */ 
/* 351 */       this.coupling_mag = null;
/* 352 */       this.coupling_ang = null;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jorbis.Mapping0
 * JD-Core Version:    0.6.2
 */