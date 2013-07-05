/*     */ package org.lwjgl.util.glu.tessellation;
/*     */ 
/*     */ class Geom
/*     */ {
/*     */   static double EdgeEval(GLUvertex u, GLUvertex v, GLUvertex w)
/*     */   {
/* 104 */     assert ((VertLeq(u, v)) && (VertLeq(v, w)));
/*     */ 
/* 106 */     double gapL = v.s - u.s;
/* 107 */     double gapR = w.s - v.s;
/*     */ 
/* 109 */     if (gapL + gapR > 0.0D) {
/* 110 */       if (gapL < gapR) {
/* 111 */         return v.t - u.t + (u.t - w.t) * (gapL / (gapL + gapR));
/*     */       }
/* 113 */       return v.t - w.t + (w.t - u.t) * (gapR / (gapL + gapR));
/*     */     }
/*     */ 
/* 117 */     return 0.0D;
/*     */   }
/*     */ 
/*     */   static double EdgeSign(GLUvertex u, GLUvertex v, GLUvertex w)
/*     */   {
/* 123 */     assert ((VertLeq(u, v)) && (VertLeq(v, w)));
/*     */ 
/* 125 */     double gapL = v.s - u.s;
/* 126 */     double gapR = w.s - v.s;
/*     */ 
/* 128 */     if (gapL + gapR > 0.0D) {
/* 129 */       return (v.t - w.t) * gapL + (v.t - u.t) * gapR;
/*     */     }
/*     */ 
/* 132 */     return 0.0D;
/*     */   }
/*     */ 
/*     */   static double TransEval(GLUvertex u, GLUvertex v, GLUvertex w)
/*     */   {
/* 153 */     assert ((TransLeq(u, v)) && (TransLeq(v, w)));
/*     */ 
/* 155 */     double gapL = v.t - u.t;
/* 156 */     double gapR = w.t - v.t;
/*     */ 
/* 158 */     if (gapL + gapR > 0.0D) {
/* 159 */       if (gapL < gapR) {
/* 160 */         return v.s - u.s + (u.s - w.s) * (gapL / (gapL + gapR));
/*     */       }
/* 162 */       return v.s - w.s + (w.s - u.s) * (gapR / (gapL + gapR));
/*     */     }
/*     */ 
/* 166 */     return 0.0D;
/*     */   }
/*     */ 
/*     */   static double TransSign(GLUvertex u, GLUvertex v, GLUvertex w)
/*     */   {
/* 176 */     assert ((TransLeq(u, v)) && (TransLeq(v, w)));
/*     */ 
/* 178 */     double gapL = v.t - u.t;
/* 179 */     double gapR = w.t - v.t;
/*     */ 
/* 181 */     if (gapL + gapR > 0.0D) {
/* 182 */       return (v.s - w.s) * gapL + (v.s - u.s) * gapR;
/*     */     }
/*     */ 
/* 185 */     return 0.0D;
/*     */   }
/*     */ 
/*     */   static boolean VertCCW(GLUvertex u, GLUvertex v, GLUvertex w)
/*     */   {
/* 196 */     return u.s * (v.t - w.t) + v.s * (w.t - u.t) + w.s * (u.t - v.t) >= 0.0D;
/*     */   }
/*     */ 
/*     */   static double Interpolate(double a, double x, double b, double y)
/*     */   {
/* 208 */     a = a < 0.0D ? 0.0D : a;
/* 209 */     b = b < 0.0D ? 0.0D : b;
/* 210 */     if (a <= b) {
/* 211 */       if (b == 0.0D) {
/* 212 */         return (x + y) / 2.0D;
/*     */       }
/* 214 */       return x + (y - x) * (a / (a + b));
/*     */     }
/*     */ 
/* 217 */     return y + (x - y) * (b / (a + b));
/*     */   }
/*     */ 
/*     */   static void EdgeIntersect(GLUvertex o1, GLUvertex d1, GLUvertex o2, GLUvertex d2, GLUvertex v)
/*     */   {
/* 238 */     if (!VertLeq(o1, d1)) {
/* 239 */       GLUvertex temp = o1;
/* 240 */       o1 = d1;
/* 241 */       d1 = temp;
/*     */     }
/* 243 */     if (!VertLeq(o2, d2)) {
/* 244 */       GLUvertex temp = o2;
/* 245 */       o2 = d2;
/* 246 */       d2 = temp;
/*     */     }
/* 248 */     if (!VertLeq(o1, o2)) {
/* 249 */       GLUvertex temp = o1;
/* 250 */       o1 = o2;
/* 251 */       o2 = temp;
/* 252 */       temp = d1;
/* 253 */       d1 = d2;
/* 254 */       d2 = temp;
/*     */     }
/*     */ 
/* 257 */     if (!VertLeq(o2, d1))
/*     */     {
/* 259 */       v.s = ((o2.s + d1.s) / 2.0D);
/* 260 */     } else if (VertLeq(d1, d2))
/*     */     {
/* 262 */       double z1 = EdgeEval(o1, o2, d1);
/* 263 */       double z2 = EdgeEval(o2, d1, d2);
/* 264 */       if (z1 + z2 < 0.0D) {
/* 265 */         z1 = -z1;
/* 266 */         z2 = -z2;
/*     */       }
/* 268 */       v.s = Interpolate(z1, o2.s, z2, d1.s);
/*     */     }
/*     */     else {
/* 271 */       double z1 = EdgeSign(o1, o2, d1);
/* 272 */       double z2 = -EdgeSign(o1, d2, d1);
/* 273 */       if (z1 + z2 < 0.0D) {
/* 274 */         z1 = -z1;
/* 275 */         z2 = -z2;
/*     */       }
/* 277 */       v.s = Interpolate(z1, o2.s, z2, d2.s);
/*     */     }
/*     */ 
/* 282 */     if (!TransLeq(o1, d1)) {
/* 283 */       GLUvertex temp = o1;
/* 284 */       o1 = d1;
/* 285 */       d1 = temp;
/*     */     }
/* 287 */     if (!TransLeq(o2, d2)) {
/* 288 */       GLUvertex temp = o2;
/* 289 */       o2 = d2;
/* 290 */       d2 = temp;
/*     */     }
/* 292 */     if (!TransLeq(o1, o2)) {
/* 293 */       GLUvertex temp = o2;
/* 294 */       o2 = o1;
/* 295 */       o1 = temp;
/* 296 */       temp = d2;
/* 297 */       d2 = d1;
/* 298 */       d1 = temp;
/*     */     }
/*     */ 
/* 301 */     if (!TransLeq(o2, d1))
/*     */     {
/* 303 */       v.t = ((o2.t + d1.t) / 2.0D);
/* 304 */     } else if (TransLeq(d1, d2))
/*     */     {
/* 306 */       double z1 = TransEval(o1, o2, d1);
/* 307 */       double z2 = TransEval(o2, d1, d2);
/* 308 */       if (z1 + z2 < 0.0D) {
/* 309 */         z1 = -z1;
/* 310 */         z2 = -z2;
/*     */       }
/* 312 */       v.t = Interpolate(z1, o2.t, z2, d1.t);
/*     */     }
/*     */     else {
/* 315 */       double z1 = TransSign(o1, o2, d1);
/* 316 */       double z2 = -TransSign(o1, d2, d1);
/* 317 */       if (z1 + z2 < 0.0D) {
/* 318 */         z1 = -z1;
/* 319 */         z2 = -z2;
/*     */       }
/* 321 */       v.t = Interpolate(z1, o2.t, z2, d2.t);
/*     */     }
/*     */   }
/*     */ 
/*     */   static boolean VertEq(GLUvertex u, GLUvertex v) {
/* 326 */     return (u.s == v.s) && (u.t == v.t);
/*     */   }
/*     */ 
/*     */   static boolean VertLeq(GLUvertex u, GLUvertex v) {
/* 330 */     return (u.s < v.s) || ((u.s == v.s) && (u.t <= v.t));
/*     */   }
/*     */ 
/*     */   static boolean TransLeq(GLUvertex u, GLUvertex v)
/*     */   {
/* 336 */     return (u.t < v.t) || ((u.t == v.t) && (u.s <= v.s));
/*     */   }
/*     */ 
/*     */   static boolean EdgeGoesLeft(GLUhalfEdge e) {
/* 340 */     return VertLeq(e.Sym.Org, e.Org);
/*     */   }
/*     */ 
/*     */   static boolean EdgeGoesRight(GLUhalfEdge e) {
/* 344 */     return VertLeq(e.Org, e.Sym.Org);
/*     */   }
/*     */ 
/*     */   static double VertL1dist(GLUvertex u, GLUvertex v) {
/* 348 */     return Math.abs(u.s - v.s) + Math.abs(u.t - v.t);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.glu.tessellation.Geom
 * JD-Core Version:    0.6.2
 */