/*     */ package org.lwjgl.util.glu.tessellation;
/*     */ 
/*     */ class Normal
/*     */ {
/*     */   static boolean SLANTED_SWEEP;
/*     */   static double S_UNIT_X;
/*     */   static double S_UNIT_Y;
/*     */   private static final boolean TRUE_PROJECT = false;
/*     */ 
/*     */   private static double Dot(double[] u, double[] v)
/*     */   {
/* 118 */     return u[0] * v[0] + u[1] * v[1] + u[2] * v[2];
/*     */   }
/*     */ 
/*     */   static void Normalize(double[] v) {
/* 122 */     double len = v[0] * v[0] + v[1] * v[1] + v[2] * v[2];
/*     */ 
/* 124 */     assert (len > 0.0D);
/* 125 */     len = Math.sqrt(len);
/* 126 */     v[0] /= len;
/* 127 */     v[1] /= len;
/* 128 */     v[2] /= len;
/*     */   }
/*     */ 
/*     */   static int LongAxis(double[] v) {
/* 132 */     int i = 0;
/*     */ 
/* 134 */     if (Math.abs(v[1]) > Math.abs(v[0])) {
/* 135 */       i = 1;
/*     */     }
/* 137 */     if (Math.abs(v[2]) > Math.abs(v[i])) {
/* 138 */       i = 2;
/*     */     }
/* 140 */     return i;
/*     */   }
/*     */ 
/*     */   static void ComputeNormal(GLUtessellatorImpl tess, double[] norm)
/*     */   {
/* 148 */     GLUvertex vHead = tess.mesh.vHead;
/*     */ 
/* 151 */     double[] maxVal = new double[3];
/* 152 */     double[] minVal = new double[3];
/* 153 */     GLUvertex[] minVert = new GLUvertex[3];
/* 154 */     GLUvertex[] maxVert = new GLUvertex[3];
/* 155 */     double[] d1 = new double[3];
/* 156 */     double[] d2 = new double[3];
/* 157 */     double[] tNorm = new double[3];
/*     */     double tmp60_59 = (maxVal[2] = -2.0E+150D); maxVal[1] = tmp60_59; maxVal[0] = tmp60_59;
/*     */     double tmp77_76 = (minVal[2] = 2.0E+150D); minVal[1] = tmp77_76; minVal[0] = tmp77_76;
/*     */ 
/* 162 */     for (GLUvertex v = vHead.next; v != vHead; v = v.next) {
/* 163 */       for (int i = 0; i < 3; i++) {
/* 164 */         double c = v.coords[i];
/* 165 */         if (c < minVal[i]) {
/* 166 */           minVal[i] = c;
/* 167 */           minVert[i] = v;
/*     */         }
/* 169 */         if (c > maxVal[i]) {
/* 170 */           maxVal[i] = c;
/* 171 */           maxVert[i] = v;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 179 */     int i = 0;
/* 180 */     if (maxVal[1] - minVal[1] > maxVal[0] - minVal[0]) {
/* 181 */       i = 1;
/*     */     }
/* 183 */     if (maxVal[2] - minVal[2] > maxVal[i] - minVal[i]) {
/* 184 */       i = 2;
/*     */     }
/* 186 */     if (minVal[i] >= maxVal[i])
/*     */     {
/* 188 */       norm[0] = 0.0D;
/* 189 */       norm[1] = 0.0D;
/* 190 */       norm[2] = 1.0D;
/* 191 */       return;
/*     */     }
/*     */ 
/* 197 */     double maxLen2 = 0.0D;
/* 198 */     GLUvertex v1 = minVert[i];
/* 199 */     GLUvertex v2 = maxVert[i];
/* 200 */     d1[0] = (v1.coords[0] - v2.coords[0]);
/* 201 */     d1[1] = (v1.coords[1] - v2.coords[1]);
/* 202 */     d1[2] = (v1.coords[2] - v2.coords[2]);
/* 203 */     for (v = vHead.next; v != vHead; v = v.next) {
/* 204 */       d2[0] = (v.coords[0] - v2.coords[0]);
/* 205 */       d2[1] = (v.coords[1] - v2.coords[1]);
/* 206 */       d2[2] = (v.coords[2] - v2.coords[2]);
/* 207 */       tNorm[0] = (d1[1] * d2[2] - d1[2] * d2[1]);
/* 208 */       tNorm[1] = (d1[2] * d2[0] - d1[0] * d2[2]);
/* 209 */       tNorm[2] = (d1[0] * d2[1] - d1[1] * d2[0]);
/* 210 */       double tLen2 = tNorm[0] * tNorm[0] + tNorm[1] * tNorm[1] + tNorm[2] * tNorm[2];
/* 211 */       if (tLen2 > maxLen2) {
/* 212 */         maxLen2 = tLen2;
/* 213 */         norm[0] = tNorm[0];
/* 214 */         norm[1] = tNorm[1];
/* 215 */         norm[2] = tNorm[2];
/*     */       }
/*     */     }
/*     */ 
/* 219 */     if (maxLen2 <= 0.0D)
/*     */     {
/*     */       double tmp547_546 = (norm[2] = 0.0D); norm[1] = tmp547_546; norm[0] = tmp547_546;
/* 222 */       norm[LongAxis(d1)] = 1.0D;
/*     */     }
/*     */   }
/*     */ 
/*     */   static void CheckOrientation(GLUtessellatorImpl tess)
/*     */   {
/* 228 */     GLUface fHead = tess.mesh.fHead;
/* 229 */     GLUvertex vHead = tess.mesh.vHead;
/*     */ 
/* 235 */     double area = 0.0D;
/* 236 */     for (GLUface f = fHead.next; f != fHead; f = f.next) {
/* 237 */       GLUhalfEdge e = f.anEdge;
/* 238 */       if (e.winding > 0)
/*     */         do {
/* 240 */           area += (e.Org.s - e.Sym.Org.s) * (e.Org.t + e.Sym.Org.t);
/* 241 */           e = e.Lnext;
/* 242 */         }while (e != f.anEdge);
/*     */     }
/* 244 */     if (area < 0.0D)
/*     */     {
/* 246 */       for (GLUvertex v = vHead.next; v != vHead; v = v.next) {
/* 247 */         v.t = (-v.t);
/*     */       }
/* 249 */       tess.tUnit[0] = (-tess.tUnit[0]);
/* 250 */       tess.tUnit[1] = (-tess.tUnit[1]);
/* 251 */       tess.tUnit[2] = (-tess.tUnit[2]);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void __gl_projectPolygon(GLUtessellatorImpl tess)
/*     */   {
/* 259 */     GLUvertex vHead = tess.mesh.vHead;
/*     */ 
/* 261 */     double[] norm = new double[3];
/*     */ 
/* 264 */     boolean computedNormal = false;
/*     */ 
/* 266 */     norm[0] = tess.normal[0];
/* 267 */     norm[1] = tess.normal[1];
/* 268 */     norm[2] = tess.normal[2];
/* 269 */     if ((norm[0] == 0.0D) && (norm[1] == 0.0D) && (norm[2] == 0.0D)) {
/* 270 */       ComputeNormal(tess, norm);
/* 271 */       computedNormal = true;
/*     */     }
/* 273 */     double[] sUnit = tess.sUnit;
/* 274 */     double[] tUnit = tess.tUnit;
/* 275 */     int i = LongAxis(norm);
/*     */ 
/* 301 */     sUnit[i] = 0.0D;
/* 302 */     sUnit[((i + 1) % 3)] = S_UNIT_X;
/* 303 */     sUnit[((i + 2) % 3)] = S_UNIT_Y;
/*     */ 
/* 305 */     tUnit[i] = 0.0D;
/* 306 */     tUnit[((i + 1) % 3)] = (norm[i] > 0.0D ? -S_UNIT_Y : S_UNIT_Y);
/* 307 */     tUnit[((i + 2) % 3)] = (norm[i] > 0.0D ? S_UNIT_X : -S_UNIT_X);
/*     */ 
/* 311 */     for (GLUvertex v = vHead.next; v != vHead; v = v.next) {
/* 312 */       v.s = Dot(v.coords, sUnit);
/* 313 */       v.t = Dot(v.coords, tUnit);
/*     */     }
/* 315 */     if (computedNormal)
/* 316 */       CheckOrientation(tess);
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  99 */     if (SLANTED_SWEEP)
/*     */     {
/* 109 */       S_UNIT_X = 0.5094153956495539D;
/* 110 */       S_UNIT_Y = 0.8605207462201063D;
/*     */     } else {
/* 112 */       S_UNIT_X = 1.0D;
/* 113 */       S_UNIT_Y = 0.0D;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.glu.tessellation.Normal
 * JD-Core Version:    0.6.2
 */