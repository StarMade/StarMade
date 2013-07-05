/*      */ package com.jcraft.jorbis;
/*      */ 
/*      */ class Drft
/*      */ {
/*      */   int n;
/*      */   float[] trigcache;
/*      */   int[] splitcache;
/*   54 */   static int[] ntryh = { 4, 2, 3, 5 };
/*   55 */   static float tpi = 6.283186F;
/*   56 */   static float hsqt2 = 0.7071068F;
/*   57 */   static float taui = 0.8660254F;
/*   58 */   static float taur = -0.5F;
/*   59 */   static float sqrt2 = 1.414214F;
/*      */ 
/*      */   void backward(float[] data)
/*      */   {
/*   35 */     if (this.n == 1)
/*   36 */       return;
/*   37 */     drftb1(this.n, data, this.trigcache, this.trigcache, this.n, this.splitcache);
/*      */   }
/*      */ 
/*      */   void init(int n) {
/*   41 */     this.n = n;
/*   42 */     this.trigcache = new float[3 * n];
/*   43 */     this.splitcache = new int[32];
/*   44 */     fdrffti(n, this.trigcache, this.splitcache);
/*      */   }
/*      */ 
/*      */   void clear() {
/*   48 */     if (this.trigcache != null)
/*   49 */       this.trigcache = null;
/*   50 */     if (this.splitcache != null)
/*   51 */       this.splitcache = null;
/*      */   }
/*      */ 
/*      */   static void drfti1(int n, float[] wa, int index, int[] ifac)
/*      */   {
/*   63 */     int ntry = 0; int j = -1;
/*      */ 
/*   67 */     int nl = n;
/*   68 */     int nf = 0;
/*      */ 
/*   70 */     int state = 101;
/*      */     while (true)
/*      */     {
/*      */       int i;
/*   73 */       switch (state) {
/*      */       case 101:
/*   75 */         j++;
/*   76 */         if (j < 4)
/*   77 */           ntry = ntryh[j];
/*      */         else
/*   79 */           ntry += 2;
/*      */       case 104:
/*   81 */         int nq = nl / ntry;
/*   82 */         int nr = nl - ntry * nq;
/*   83 */         if (nr != 0) {
/*   84 */           state = 101;
/*      */         }
/*      */         else {
/*   87 */           nf++;
/*   88 */           ifac[(nf + 1)] = ntry;
/*   89 */           nl = nq;
/*   90 */           if (ntry != 2) {
/*   91 */             state = 107;
/*      */           }
/*   94 */           else if (nf == 1) {
/*   95 */             state = 107;
/*      */           }
/*      */           else
/*      */           {
/*   99 */             for (i = 1; i < nf; i++) {
/*  100 */               int ib = nf - i + 1;
/*  101 */               ifac[(ib + 1)] = ifac[ib];
/*      */             }
/*  103 */             ifac[2] = 2; }  } break;
/*      */       case 107:
/*  105 */         if (nl != 1) {
/*  106 */           state = 104;
/*      */         }
/*      */         else {
/*  109 */           ifac[0] = n;
/*  110 */           ifac[1] = nf;
/*  111 */           float argh = tpi / n;
/*  112 */           int is = 0;
/*  113 */           int nfm1 = nf - 1;
/*  114 */           int l1 = 1;
/*      */ 
/*  116 */           if (nfm1 == 0) {
/*  117 */             return;
/*      */           }
/*  119 */           for (int k1 = 0; k1 < nfm1; k1++) {
/*  120 */             int ip = ifac[(k1 + 2)];
/*  121 */             int ld = 0;
/*  122 */             int l2 = l1 * ip;
/*  123 */             int ido = n / l2;
/*  124 */             int ipm = ip - 1;
/*      */ 
/*  126 */             for (j = 0; j < ipm; j++) {
/*  127 */               ld += l1;
/*  128 */               i = is;
/*  129 */               float argld = ld * argh;
/*  130 */               float fi = 0.0F;
/*  131 */               for (int ii = 2; ii < ido; ii += 2) {
/*  132 */                 fi += 1.0F;
/*  133 */                 float arg = fi * argld;
/*  134 */                 wa[(index + i++)] = ((float)Math.cos(arg));
/*  135 */                 wa[(index + i++)] = ((float)Math.sin(arg));
/*      */               }
/*  137 */               is += ido;
/*      */             }
/*  139 */             l1 = l2; }  } break; }  } int nfm1;
/*      */     int is;
/*      */     int l1;
/*      */     int k1;
/*      */     float argh; } 
/*  147 */   static void fdrffti(int n, float[] wsave, int[] ifac) { if (n == 1)
/*  148 */       return;
/*  149 */     drfti1(n, wsave, n, ifac);
/*      */   }
/*      */ 
/*      */   static void dradf2(int ido, int l1, float[] cc, float[] ch, float[] wa1, int index)
/*      */   {
/*  158 */     int t1 = 0;
/*      */     int t2;
/*  159 */     int t0 = t2 = l1 * ido;
/*  160 */     int t3 = ido << 1;
/*  161 */     for (int k = 0; k < l1; k++) {
/*  162 */       ch[(t1 << 1)] = (cc[t1] + cc[t2]);
/*  163 */       ch[((t1 << 1) + t3 - 1)] = (cc[t1] - cc[t2]);
/*  164 */       t1 += ido;
/*  165 */       t2 += ido;
/*      */     }
/*      */ 
/*  168 */     if (ido < 2) {
/*  169 */       return;
/*      */     }
/*  171 */     if (ido != 2) {
/*  172 */       t1 = 0;
/*  173 */       t2 = t0;
/*  174 */       for (k = 0; k < l1; k++) {
/*  175 */         t3 = t2;
/*  176 */         int t4 = (t1 << 1) + (ido << 1);
/*  177 */         int t5 = t1;
/*  178 */         int t6 = t1 + t1;
/*  179 */         for (int i = 2; i < ido; i += 2) {
/*  180 */           t3 += 2;
/*  181 */           t4 -= 2;
/*  182 */           t5 += 2;
/*  183 */           t6 += 2;
/*  184 */           float tr2 = wa1[(index + i - 2)] * cc[(t3 - 1)] + wa1[(index + i - 1)] * cc[t3];
/*  185 */           float ti2 = wa1[(index + i - 2)] * cc[t3] - wa1[(index + i - 1)] * cc[(t3 - 1)];
/*  186 */           cc[t5] += ti2;
/*  187 */           ch[t4] = (ti2 - cc[t5]);
/*  188 */           cc[(t5 - 1)] += tr2;
/*  189 */           cc[(t5 - 1)] -= tr2;
/*      */         }
/*  191 */         t1 += ido;
/*  192 */         t2 += ido;
/*      */       }
/*  194 */       if (ido % 2 == 1) {
/*  195 */         return;
/*      */       }
/*      */     }
/*  198 */     t3 = t2 = (t1 = ido) - 1;
/*  199 */     t2 += t0;
/*  200 */     for (k = 0; k < l1; k++) {
/*  201 */       ch[t1] = (-cc[t2]);
/*  202 */       ch[(t1 - 1)] = cc[t3];
/*  203 */       t1 += (ido << 1);
/*  204 */       t2 += ido;
/*  205 */       t3 += ido;
/*      */     }
/*      */   }
/*      */ 
/*      */   static void dradf4(int ido, int l1, float[] cc, float[] ch, float[] wa1, int index1, float[] wa2, int index2, float[] wa3, int index3)
/*      */   {
/*  213 */     int t0 = l1 * ido;
/*      */ 
/*  215 */     int t1 = t0;
/*  216 */     int t4 = t1 << 1;
/*  217 */     int t2 = t1 + (t1 << 1);
/*  218 */     int t3 = 0;
/*      */ 
/*  220 */     for (int k = 0; k < l1; k++) {
/*  221 */       float tr1 = cc[t1] + cc[t2];
/*  222 */       float tr2 = cc[t3] + cc[t4];
/*      */       int tmp63_62 = (t3 << 2); int t5 = tmp63_62; ch[tmp63_62] = (tr1 + tr2);
/*  225 */       ch[((ido << 2) + t5 - 1)] = (tr2 - tr1);
/*      */       int tmp94_93 = (t5 + (ido << 1)); t5 = tmp94_93; ch[(tmp94_93 - 1)] = (cc[t3] - cc[t4]);
/*  227 */       cc[t2] -= cc[t1];
/*      */ 
/*  229 */       t1 += ido;
/*  230 */       t2 += ido;
/*  231 */       t3 += ido;
/*  232 */       t4 += ido;
/*      */     }
/*  234 */     if (ido < 2) {
/*  235 */       return;
/*      */     }
/*  237 */     if (ido != 2) {
/*  238 */       t1 = 0;
/*  239 */       for (k = 0; k < l1; k++) {
/*  240 */         t2 = t1;
/*  241 */         t4 = t1 << 2;
/*      */         int t6;
/*  242 */         int t5 = (t6 = ido << 1) + t4;
/*  243 */         for (int i = 2; i < ido; i += 2) {
/*  244 */           t2 += 2; t3 = t2;
/*  245 */           t4 += 2;
/*  246 */           t5 -= 2;
/*      */ 
/*  248 */           t3 += t0;
/*  249 */           float cr2 = wa1[(index1 + i - 2)] * cc[(t3 - 1)] + wa1[(index1 + i - 1)] * cc[t3];
/*  250 */           float ci2 = wa1[(index1 + i - 2)] * cc[t3] - wa1[(index1 + i - 1)] * cc[(t3 - 1)];
/*  251 */           t3 += t0;
/*  252 */           float cr3 = wa2[(index2 + i - 2)] * cc[(t3 - 1)] + wa2[(index2 + i - 1)] * cc[t3];
/*  253 */           float ci3 = wa2[(index2 + i - 2)] * cc[t3] - wa2[(index2 + i - 1)] * cc[(t3 - 1)];
/*  254 */           t3 += t0;
/*  255 */           float cr4 = wa3[(index3 + i - 2)] * cc[(t3 - 1)] + wa3[(index3 + i - 1)] * cc[t3];
/*  256 */           float ci4 = wa3[(index3 + i - 2)] * cc[t3] - wa3[(index3 + i - 1)] * cc[(t3 - 1)];
/*      */ 
/*  258 */           float tr1 = cr2 + cr4;
/*  259 */           float tr4 = cr4 - cr2;
/*  260 */           float ti1 = ci2 + ci4;
/*  261 */           float ti4 = ci2 - ci4;
/*      */ 
/*  263 */           float ti2 = cc[t2] + ci3;
/*  264 */           float ti3 = cc[t2] - ci3;
/*  265 */           float tr2 = cc[(t2 - 1)] + cr3;
/*  266 */           float tr3 = cc[(t2 - 1)] - cr3;
/*      */ 
/*  268 */           ch[(t4 - 1)] = (tr1 + tr2);
/*  269 */           ch[t4] = (ti1 + ti2);
/*      */ 
/*  271 */           ch[(t5 - 1)] = (tr3 - ti4);
/*  272 */           ch[t5] = (tr4 - ti3);
/*      */ 
/*  274 */           ch[(t4 + t6 - 1)] = (ti4 + tr3);
/*  275 */           ch[(t4 + t6)] = (tr4 + ti3);
/*      */ 
/*  277 */           ch[(t5 + t6 - 1)] = (tr2 - tr1);
/*  278 */           ch[(t5 + t6)] = (ti1 - ti2);
/*      */         }
/*  280 */         t1 += ido;
/*      */       }
/*  282 */       if ((ido & 0x1) != 0) {
/*  283 */         return;
/*      */       }
/*      */     }
/*  286 */     t2 = (t1 = t0 + ido - 1) + (t0 << 1);
/*  287 */     t3 = ido << 2;
/*  288 */     t4 = ido;
/*  289 */     int t5 = ido << 1;
/*  290 */     int t6 = ido;
/*      */ 
/*  292 */     for (k = 0; k < l1; k++) {
/*  293 */       float ti1 = -hsqt2 * (cc[t1] + cc[t2]);
/*  294 */       float tr1 = hsqt2 * (cc[t1] - cc[t2]);
/*      */ 
/*  296 */       ch[(t4 - 1)] = (tr1 + cc[(t6 - 1)]);
/*  297 */       ch[(t4 + t5 - 1)] = (cc[(t6 - 1)] - tr1);
/*      */ 
/*  299 */       ch[t4] = (ti1 - cc[(t1 + t0)]);
/*  300 */       ch[(t4 + t5)] = (ti1 + cc[(t1 + t0)]);
/*      */ 
/*  302 */       t1 += ido;
/*  303 */       t2 += ido;
/*  304 */       t4 += t3;
/*  305 */       t6 += ido;
/*      */     }
/*      */   }
/*      */ 
/*      */   static void dradfg(int ido, int ip, int l1, int idl1, float[] cc, float[] c1, float[] c2, float[] ch, float[] ch2, float[] wa, int index)
/*      */   {
/*  312 */     int t2 = 0;
/*      */ 
/*  315 */     float dcp = 0.0F; float dsp = 0.0F;
/*      */ 
/*  318 */     float arg = tpi / ip;
/*  319 */     dcp = (float)Math.cos(arg);
/*  320 */     dsp = (float)Math.sin(arg);
/*  321 */     int ipph = ip + 1 >> 1;
/*  322 */     int ipp2 = ip;
/*  323 */     int idp2 = ido;
/*  324 */     int nbd = ido - 1 >> 1;
/*  325 */     int t0 = l1 * ido;
/*  326 */     int t10 = ip * ido;
/*      */ 
/*  328 */     int state = 100;
/*      */     while (true)
/*      */     {
/*      */       int ik;
/*      */       int t1;
/*      */       int j;
/*      */       int t3;
/*      */       int k;
/*      */       int i;
/*      */       int t1;
/*      */       int t4;
/*      */       int t5;
/*  330 */       switch (state) {
/*      */       case 101:
/*  332 */         if (ido == 1) {
/*  333 */           state = 119;
/*      */         }
/*      */         else {
/*  336 */           for (ik = 0; ik < idl1; ik++) {
/*  337 */             ch2[ik] = c2[ik];
/*      */           }
/*  339 */           t1 = 0;
/*  340 */           for (j = 1; j < ip; j++) {
/*  341 */             t1 += t0;
/*  342 */             t2 = t1;
/*  343 */             for (int k = 0; k < l1; k++) {
/*  344 */               ch[t2] = c1[t2];
/*  345 */               t2 += ido;
/*      */             }
/*      */           }
/*      */ 
/*  349 */           int is = -ido;
/*  350 */           t1 = 0;
/*  351 */           if (nbd > l1) {
/*  352 */             for (j = 1; j < ip; j++) {
/*  353 */               t1 += t0;
/*  354 */               is += ido;
/*  355 */               t2 = -ido + t1;
/*  356 */               for (int k = 0; k < l1; k++) {
/*  357 */                 int idij = is - 1;
/*  358 */                 t2 += ido;
/*  359 */                 int t3 = t2;
/*  360 */                 for (int i = 2; i < ido; i += 2) {
/*  361 */                   idij += 2;
/*  362 */                   t3 += 2;
/*  363 */                   ch[(t3 - 1)] = (wa[(index + idij - 1)] * c1[(t3 - 1)] + wa[(index + idij)] * c1[t3]);
/*  364 */                   ch[t3] = (wa[(index + idij - 1)] * c1[t3] - wa[(index + idij)] * c1[(t3 - 1)]);
/*      */                 }
/*      */               }
/*      */             }
/*      */ 
/*      */           }
/*      */ 
/*  371 */           for (j = 1; j < ip; j++) {
/*  372 */             is += ido;
/*  373 */             int idij = is - 1;
/*  374 */             t1 += t0;
/*  375 */             t2 = t1;
/*  376 */             for (int i = 2; i < ido; i += 2) {
/*  377 */               idij += 2;
/*  378 */               t2 += 2;
/*  379 */               int t3 = t2;
/*  380 */               for (int k = 0; k < l1; k++) {
/*  381 */                 ch[(t3 - 1)] = (wa[(index + idij - 1)] * c1[(t3 - 1)] + wa[(index + idij)] * c1[t3]);
/*  382 */                 ch[t3] = (wa[(index + idij - 1)] * c1[t3] - wa[(index + idij)] * c1[(t3 - 1)]);
/*  383 */                 t3 += ido;
/*      */               }
/*      */             }
/*      */ 
/*      */           }
/*      */ 
/*  389 */           t1 = 0;
/*  390 */           t2 = ipp2 * t0;
/*  391 */           if (nbd < l1) {
/*  392 */             for (j = 1; j < ipph; j++) {
/*  393 */               t1 += t0;
/*  394 */               t2 -= t0;
/*  395 */               int t3 = t1;
/*  396 */               int t4 = t2;
/*  397 */               for (int i = 2; i < ido; i += 2) {
/*  398 */                 t3 += 2;
/*  399 */                 t4 += 2;
/*  400 */                 int t5 = t3 - ido;
/*  401 */                 int t6 = t4 - ido;
/*  402 */                 for (int k = 0; k < l1; k++) {
/*  403 */                   t5 += ido;
/*  404 */                   t6 += ido;
/*  405 */                   ch[(t5 - 1)] += ch[(t6 - 1)];
/*  406 */                   c1[(t6 - 1)] = (ch[t5] - ch[t6]);
/*  407 */                   ch[t5] += ch[t6];
/*  408 */                   c1[t6] = (ch[(t6 - 1)] - ch[(t5 - 1)]);
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */ 
/*  414 */           for (j = 1; j < ipph; j++) {
/*  415 */             t1 += t0;
/*  416 */             t2 -= t0;
/*  417 */             int t3 = t1;
/*  418 */             int t4 = t2;
/*  419 */             for (int k = 0; k < l1; k++) {
/*  420 */               int t5 = t3;
/*  421 */               int t6 = t4;
/*  422 */               for (int i = 2; i < ido; i += 2) {
/*  423 */                 t5 += 2;
/*  424 */                 t6 += 2;
/*  425 */                 ch[(t5 - 1)] += ch[(t6 - 1)];
/*  426 */                 c1[(t6 - 1)] = (ch[t5] - ch[t6]);
/*  427 */                 ch[t5] += ch[t6];
/*  428 */                 c1[t6] = (ch[(t6 - 1)] - ch[(t5 - 1)]);
/*      */               }
/*  430 */               t3 += ido;
/*  431 */               t4 += ido;
/*      */             }
/*      */           }
/*      */         }
/*      */         break;
/*      */       case 119:
/*  436 */         for (ik = 0; ik < idl1; ik++) {
/*  437 */           c2[ik] = ch2[ik];
/*      */         }
/*  439 */         t1 = 0;
/*  440 */         t2 = ipp2 * idl1;
/*  441 */         for (j = 1; j < ipph; j++) {
/*  442 */           t1 += t0;
/*  443 */           t2 -= t0;
/*  444 */           int t3 = t1 - ido;
/*  445 */           int t4 = t2 - ido;
/*  446 */           for (int k = 0; k < l1; k++) {
/*  447 */             t3 += ido;
/*  448 */             t4 += ido;
/*  449 */             ch[t3] += ch[t4];
/*  450 */             ch[t4] -= ch[t3];
/*      */           }
/*      */         }
/*      */ 
/*  454 */         float ar1 = 1.0F;
/*  455 */         float ai1 = 0.0F;
/*  456 */         t1 = 0;
/*  457 */         t2 = ipp2 * idl1;
/*  458 */         t3 = (ip - 1) * idl1;
/*  459 */         for (int l = 1; l < ipph; l++) {
/*  460 */           t1 += idl1;
/*  461 */           t2 -= idl1;
/*  462 */           float ar1h = dcp * ar1 - dsp * ai1;
/*  463 */           ai1 = dcp * ai1 + dsp * ar1;
/*  464 */           ar1 = ar1h;
/*  465 */           int t4 = t1;
/*  466 */           int t5 = t2;
/*  467 */           int t6 = t3;
/*  468 */           int t7 = idl1;
/*      */ 
/*  470 */           for (ik = 0; ik < idl1; ik++) {
/*  471 */             ch2[(t4++)] = (c2[ik] + ar1 * c2[(t7++)]);
/*  472 */             ch2[(t5++)] = (ai1 * c2[(t6++)]);
/*      */           }
/*      */ 
/*  475 */           float dc2 = ar1;
/*  476 */           float ds2 = ai1;
/*  477 */           float ar2 = ar1;
/*  478 */           float ai2 = ai1;
/*      */ 
/*  480 */           t4 = idl1;
/*  481 */           t5 = (ipp2 - 1) * idl1;
/*  482 */           for (j = 2; j < ipph; j++) {
/*  483 */             t4 += idl1;
/*  484 */             t5 -= idl1;
/*      */ 
/*  486 */             float ar2h = dc2 * ar2 - ds2 * ai2;
/*  487 */             ai2 = dc2 * ai2 + ds2 * ar2;
/*  488 */             ar2 = ar2h;
/*      */ 
/*  490 */             t6 = t1;
/*  491 */             t7 = t2;
/*  492 */             int t8 = t4;
/*  493 */             int t9 = t5;
/*  494 */             for (ik = 0; ik < idl1; ik++) {
/*  495 */               ch2[(t6++)] += ar2 * c2[(t8++)];
/*  496 */               ch2[(t7++)] += ai2 * c2[(t9++)];
/*      */             }
/*      */           }
/*      */         }
/*  500 */         t1 = 0;
/*  501 */         for (j = 1; j < ipph; j++) {
/*  502 */           t1 += idl1;
/*  503 */           t2 = t1;
/*  504 */           for (ik = 0; ik < idl1; ik++) {
/*  505 */             ch2[ik] += c2[(t2++)];
/*      */           }
/*      */         }
/*  508 */         if (ido < l1) {
/*  509 */           state = 132;
/*      */         }
/*      */         else
/*      */         {
/*  513 */           t1 = 0;
/*  514 */           t2 = 0;
/*  515 */           for (k = 0; k < l1; k++) {
/*  516 */             t3 = t1;
/*  517 */             int t4 = t2;
/*  518 */             for (int i = 0; i < ido; i++)
/*  519 */               cc[(t4++)] = ch[(t3++)];
/*  520 */             t1 += ido;
/*  521 */             t2 += t10;
/*      */           }
/*  523 */           state = 135;
/*  524 */         }break;
/*      */       case 132:
/*  527 */         for (i = 0; i < ido; i++) {
/*  528 */           t1 = i;
/*  529 */           t2 = i;
/*  530 */           for (k = 0; k < l1; k++) {
/*  531 */             cc[t2] = ch[t1];
/*  532 */             t1 += ido;
/*  533 */             t2 += t10;
/*      */           }
/*      */         }
/*      */       case 135:
/*  537 */         t1 = 0;
/*  538 */         t2 = ido << 1;
/*  539 */         t3 = 0;
/*  540 */         t4 = ipp2 * t0;
/*  541 */         for (j = 1; j < ipph; j++) {
/*  542 */           t1 += t2;
/*  543 */           t3 += t0;
/*  544 */           t4 -= t0;
/*      */ 
/*  546 */           int t5 = t1;
/*  547 */           int t6 = t3;
/*  548 */           int t7 = t4;
/*      */ 
/*  550 */           for (int k = 0; k < l1; k++) {
/*  551 */             cc[(t5 - 1)] = ch[t6];
/*  552 */             cc[t5] = ch[t7];
/*  553 */             t5 += t10;
/*  554 */             t6 += ido;
/*  555 */             t7 += ido;
/*      */           }
/*      */         }
/*      */ 
/*  559 */         if (ido == 1)
/*  560 */           return;
/*  561 */         if (nbd < l1) {
/*  562 */           state = 141;
/*      */         }
/*      */         else
/*      */         {
/*  566 */           t1 = -ido;
/*  567 */           t3 = 0;
/*  568 */           t4 = 0;
/*  569 */           t5 = ipp2 * t0;
/*  570 */           for (j = 1; j < ipph; j++) {
/*  571 */             t1 += t2;
/*  572 */             t3 += t2;
/*  573 */             t4 += t0;
/*  574 */             t5 -= t0;
/*  575 */             int t6 = t1;
/*  576 */             int t7 = t3;
/*  577 */             int t8 = t4;
/*  578 */             int t9 = t5;
/*  579 */             for (int k = 0; k < l1; k++) {
/*  580 */               for (i = 2; i < ido; i += 2) {
/*  581 */                 int ic = idp2 - i;
/*  582 */                 ch[(i + t8 - 1)] += ch[(i + t9 - 1)];
/*  583 */                 ch[(i + t8 - 1)] -= ch[(i + t9 - 1)];
/*  584 */                 ch[(i + t8)] += ch[(i + t9)];
/*  585 */                 ch[(i + t9)] -= ch[(i + t8)];
/*      */               }
/*  587 */               t6 += t10;
/*  588 */               t7 += t10;
/*  589 */               t8 += ido;
/*  590 */               t9 += ido; } 
/*      */           }return;
/*      */         }
/*      */         break;
/*      */       case 141:
/*  595 */         t1 = -ido;
/*  596 */         t3 = 0;
/*  597 */         t4 = 0;
/*  598 */         t5 = ipp2 * t0;
/*  599 */         for (j = 1; j < ipph; j++) {
/*  600 */           t1 += t2;
/*  601 */           t3 += t2;
/*  602 */           t4 += t0;
/*  603 */           t5 -= t0;
/*  604 */           for (int i = 2; i < ido; i += 2) {
/*  605 */             int t6 = idp2 + t1 - i;
/*  606 */             int t7 = i + t3;
/*  607 */             int t8 = i + t4;
/*  608 */             int t9 = i + t5;
/*  609 */             for (int k = 0; k < l1; k++) {
/*  610 */               ch[(t8 - 1)] += ch[(t9 - 1)];
/*  611 */               ch[(t8 - 1)] -= ch[(t9 - 1)];
/*  612 */               ch[t8] += ch[t9];
/*  613 */               ch[t9] -= ch[t8];
/*  614 */               t6 += t10;
/*  615 */               t7 += t10;
/*  616 */               t8 += ido;
/*  617 */               t9 += ido; }  }  } 
/*      */       }
/*      */     }
/*      */     int t5;
/*      */     int t4;
/*      */     int t3;
/*      */     int t1;
/*      */     int j;
/*      */   }
/*      */ 
/*  631 */   static void drftf1(int n, float[] c, float[] ch, float[] wa, int[] ifac) { int nf = ifac[1];
/*  632 */     int na = 1;
/*  633 */     int l2 = n;
/*  634 */     int iw = n;
/*      */ 
/*  636 */     label383: for (int k1 = 0; k1 < nf; k1++) {
/*  637 */       int kh = nf - k1;
/*  638 */       int ip = ifac[(kh + 1)];
/*  639 */       int l1 = l2 / ip;
/*  640 */       int ido = n / l2;
/*  641 */       int idl1 = ido * l1;
/*  642 */       iw -= (ip - 1) * ido;
/*  643 */       na = 1 - na;
/*      */ 
/*  645 */       int state = 100;
/*      */       while (true)
/*  647 */         switch (state) {
/*      */         case 100:
/*  649 */           if (ip != 4) {
/*  650 */             state = 102;
/*      */           }
/*      */           else
/*      */           {
/*  654 */             int ix2 = iw + ido;
/*  655 */             int ix3 = ix2 + ido;
/*  656 */             if (na != 0)
/*  657 */               dradf4(ido, l1, ch, c, wa, iw - 1, wa, ix2 - 1, wa, ix3 - 1);
/*      */             else
/*  659 */               dradf4(ido, l1, c, ch, wa, iw - 1, wa, ix2 - 1, wa, ix3 - 1);
/*  660 */             state = 110;
/*  661 */           }break;
/*      */         case 102:
/*  663 */           if (ip != 2) {
/*  664 */             state = 104;
/*      */           }
/*  667 */           else if (na != 0) {
/*  668 */             state = 103;
/*      */           }
/*      */           else {
/*  671 */             dradf2(ido, l1, c, ch, wa, iw - 1);
/*  672 */             state = 110;
/*  673 */           }break;
/*      */         case 103:
/*  675 */           dradf2(ido, l1, ch, c, wa, iw - 1);
/*      */         case 104:
/*  677 */           if (ido == 1)
/*  678 */             na = 1 - na;
/*  679 */           if (na != 0) {
/*  680 */             state = 109;
/*      */           }
/*      */           else {
/*  683 */             dradfg(ido, ip, l1, idl1, c, c, c, ch, ch, wa, iw - 1);
/*  684 */             na = 1;
/*  685 */             state = 110;
/*  686 */           }break;
/*      */         case 109:
/*  688 */           dradfg(ido, ip, l1, idl1, ch, ch, ch, c, c, wa, iw - 1);
/*  689 */           na = 0;
/*      */         case 110:
/*  691 */           l2 = l1;
/*  692 */           break label383;
/*      */         case 101:
/*      */         case 105:
/*      */         case 106:
/*      */         case 107:
/*  696 */         case 108: }   } if (na == 1)
/*  697 */       return;
/*  698 */     for (int i = 0; i < n; i++)
/*  699 */       c[i] = ch[i];
/*      */   }
/*      */ 
/*      */   static void dradb2(int ido, int l1, float[] cc, float[] ch, float[] wa1, int index)
/*      */   {
/*  707 */     int t0 = l1 * ido;
/*      */ 
/*  709 */     int t1 = 0;
/*  710 */     int t2 = 0;
/*  711 */     int t3 = (ido << 1) - 1;
/*  712 */     for (int k = 0; k < l1; k++) {
/*  713 */       cc[t2] += cc[(t3 + t2)];
/*  714 */       ch[(t1 + t0)] = (cc[t2] - cc[(t3 + t2)]);
/*  715 */       t2 = t1 += ido << 1;
/*      */     }
/*      */ 
/*  718 */     if (ido < 2)
/*  719 */       return;
/*  720 */     if (ido != 2) {
/*  721 */       t1 = 0;
/*  722 */       t2 = 0;
/*  723 */       for (k = 0; k < l1; k++) {
/*  724 */         t3 = t1;
/*      */         int t4;
/*  725 */         int t5 = (t4 = t2) + (ido << 1);
/*  726 */         int t6 = t0 + t1;
/*  727 */         for (int i = 2; i < ido; i += 2) {
/*  728 */           t3 += 2;
/*  729 */           t4 += 2;
/*  730 */           t5 -= 2;
/*  731 */           t6 += 2;
/*  732 */           cc[(t4 - 1)] += cc[(t5 - 1)];
/*  733 */           float tr2 = cc[(t4 - 1)] - cc[(t5 - 1)];
/*  734 */           cc[t4] -= cc[t5];
/*  735 */           float ti2 = cc[t4] + cc[t5];
/*  736 */           ch[(t6 - 1)] = (wa1[(index + i - 2)] * tr2 - wa1[(index + i - 1)] * ti2);
/*  737 */           ch[t6] = (wa1[(index + i - 2)] * ti2 + wa1[(index + i - 1)] * tr2);
/*      */         }
/*  739 */         t2 = t1 += ido << 1;
/*      */       }
/*  741 */       if (ido % 2 == 1) {
/*  742 */         return;
/*      */       }
/*      */     }
/*  745 */     t1 = ido - 1;
/*  746 */     t2 = ido - 1;
/*  747 */     for (k = 0; k < l1; k++) {
/*  748 */       cc[t2] += cc[t2];
/*  749 */       ch[(t1 + t0)] = (-(cc[(t2 + 1)] + cc[(t2 + 1)]));
/*  750 */       t1 += ido;
/*  751 */       t2 += (ido << 1);
/*      */     }
/*      */   }
/*      */ 
/*      */   static void dradb3(int ido, int l1, float[] cc, float[] ch, float[] wa1, int index1, float[] wa2, int index2)
/*      */   {
/*  759 */     int t0 = l1 * ido;
/*      */ 
/*  761 */     int t1 = 0;
/*  762 */     int t2 = t0 << 1;
/*  763 */     int t3 = ido << 1;
/*  764 */     int t4 = ido + (ido << 1);
/*  765 */     int t5 = 0;
/*  766 */     for (int k = 0; k < l1; k++) {
/*  767 */       float tr2 = cc[(t3 - 1)] + cc[(t3 - 1)];
/*  768 */       float cr2 = cc[t5] + taur * tr2;
/*  769 */       cc[t5] += tr2;
/*  770 */       float ci3 = taui * (cc[t3] + cc[t3]);
/*  771 */       ch[(t1 + t0)] = (cr2 - ci3);
/*  772 */       ch[(t1 + t2)] = (cr2 + ci3);
/*  773 */       t1 += ido;
/*  774 */       t3 += t4;
/*  775 */       t5 += t4;
/*      */     }
/*      */ 
/*  778 */     if (ido == 1) {
/*  779 */       return;
/*      */     }
/*  781 */     t1 = 0;
/*  782 */     t3 = ido << 1;
/*  783 */     for (k = 0; k < l1; k++) {
/*  784 */       int t7 = t1 + (t1 << 1);
/*  785 */       int t6 = t5 = t7 + t3;
/*  786 */       int t8 = t1;
/*      */       int t9;
/*  787 */       int t10 = (t9 = t1 + t0) + t0;
/*      */ 
/*  789 */       for (int i = 2; i < ido; i += 2) {
/*  790 */         t5 += 2;
/*  791 */         t6 -= 2;
/*  792 */         t7 += 2;
/*  793 */         t8 += 2;
/*  794 */         t9 += 2;
/*  795 */         t10 += 2;
/*  796 */         float tr2 = cc[(t5 - 1)] + cc[(t6 - 1)];
/*  797 */         float cr2 = cc[(t7 - 1)] + taur * tr2;
/*  798 */         cc[(t7 - 1)] += tr2;
/*  799 */         float ti2 = cc[t5] - cc[t6];
/*  800 */         float ci2 = cc[t7] + taur * ti2;
/*  801 */         cc[t7] += ti2;
/*  802 */         float cr3 = taui * (cc[(t5 - 1)] - cc[(t6 - 1)]);
/*  803 */         float ci3 = taui * (cc[t5] + cc[t6]);
/*  804 */         float dr2 = cr2 - ci3;
/*  805 */         float dr3 = cr2 + ci3;
/*  806 */         float di2 = ci2 + cr3;
/*  807 */         float di3 = ci2 - cr3;
/*  808 */         ch[(t9 - 1)] = (wa1[(index1 + i - 2)] * dr2 - wa1[(index1 + i - 1)] * di2);
/*  809 */         ch[t9] = (wa1[(index1 + i - 2)] * di2 + wa1[(index1 + i - 1)] * dr2);
/*  810 */         ch[(t10 - 1)] = (wa2[(index2 + i - 2)] * dr3 - wa2[(index2 + i - 1)] * di3);
/*  811 */         ch[t10] = (wa2[(index2 + i - 2)] * di3 + wa2[(index2 + i - 1)] * dr3);
/*      */       }
/*  813 */       t1 += ido;
/*      */     }
/*      */   }
/*      */ 
/*      */   static void dradb4(int ido, int l1, float[] cc, float[] ch, float[] wa1, int index1, float[] wa2, int index2, float[] wa3, int index3)
/*      */   {
/*  821 */     int t0 = l1 * ido;
/*      */ 
/*  823 */     int t1 = 0;
/*  824 */     int t2 = ido << 2;
/*  825 */     int t3 = 0;
/*  826 */     int t6 = ido << 1;
/*  827 */     for (int k = 0; k < l1; k++) {
/*  828 */       int t4 = t3 + t6;
/*  829 */       int t5 = t1;
/*  830 */       float tr3 = cc[(t4 - 1)] + cc[(t4 - 1)];
/*  831 */       float tr4 = cc[t4] + cc[t4];
/*  832 */       float tr1 = cc[t3] - cc[(t4 += t6 - 1)];
/*  833 */       float tr2 = cc[t3] + cc[(t4 - 1)];
/*  834 */       ch[t5] = (tr2 + tr3);
/*      */       int tmp114_113 = (t5 + t0); t5 = tmp114_113; ch[tmp114_113] = (tr1 - tr4);
/*      */       int tmp129_128 = (t5 + t0); t5 = tmp129_128; ch[tmp129_128] = (tr2 - tr3);
/*      */       int tmp144_143 = (t5 + t0); t5 = tmp144_143; ch[tmp144_143] = (tr1 + tr4);
/*  838 */       t1 += ido;
/*  839 */       t3 += t2;
/*      */     }
/*      */ 
/*  842 */     if (ido < 2)
/*  843 */       return;
/*  844 */     if (ido != 2) {
/*  845 */       t1 = 0;
/*  846 */       for (k = 0; k < l1; k++)
/*      */       {
/*      */         int t4;
/*  847 */         int t5 = (t4 = t3 = (t2 = t1 << 2) + t6) + t6;
/*  848 */         int t7 = t1;
/*  849 */         for (int i = 2; i < ido; i += 2) {
/*  850 */           t2 += 2;
/*  851 */           t3 += 2;
/*  852 */           t4 -= 2;
/*  853 */           t5 -= 2;
/*  854 */           t7 += 2;
/*  855 */           float ti1 = cc[t2] + cc[t5];
/*  856 */           float ti2 = cc[t2] - cc[t5];
/*  857 */           float ti3 = cc[t3] - cc[t4];
/*  858 */           float tr4 = cc[t3] + cc[t4];
/*  859 */           float tr1 = cc[(t2 - 1)] - cc[(t5 - 1)];
/*  860 */           float tr2 = cc[(t2 - 1)] + cc[(t5 - 1)];
/*  861 */           float ti4 = cc[(t3 - 1)] - cc[(t4 - 1)];
/*  862 */           float tr3 = cc[(t3 - 1)] + cc[(t4 - 1)];
/*  863 */           ch[(t7 - 1)] = (tr2 + tr3);
/*  864 */           float cr3 = tr2 - tr3;
/*  865 */           ch[t7] = (ti2 + ti3);
/*  866 */           float ci3 = ti2 - ti3;
/*  867 */           float cr2 = tr1 - tr4;
/*  868 */           float cr4 = tr1 + tr4;
/*  869 */           float ci2 = ti1 + ti4;
/*  870 */           float ci4 = ti1 - ti4;
/*      */           int tmp416_415 = (t7 + t0); int t8 = tmp416_415; ch[(tmp416_415 - 1)] = (wa1[(index1 + i - 2)] * cr2 - wa1[(index1 + i - 1)] * ci2);
/*  873 */           ch[t8] = (wa1[(index1 + i - 2)] * ci2 + wa1[(index1 + i - 1)] * cr2);
/*      */           int tmp486_485 = (t8 + t0); t8 = tmp486_485; ch[(tmp486_485 - 1)] = (wa2[(index2 + i - 2)] * cr3 - wa2[(index2 + i - 1)] * ci3);
/*  875 */           ch[t8] = (wa2[(index2 + i - 2)] * ci3 + wa2[(index2 + i - 1)] * cr3);
/*      */           int tmp556_555 = (t8 + t0); t8 = tmp556_555; ch[(tmp556_555 - 1)] = (wa3[(index3 + i - 2)] * cr4 - wa3[(index3 + i - 1)] * ci4);
/*  877 */           ch[t8] = (wa3[(index3 + i - 2)] * ci4 + wa3[(index3 + i - 1)] * cr4);
/*      */         }
/*  879 */         t1 += ido;
/*      */       }
/*  881 */       if (ido % 2 == 1) {
/*  882 */         return;
/*      */       }
/*      */     }
/*  885 */     t1 = ido;
/*  886 */     t2 = ido << 2;
/*  887 */     t3 = ido - 1;
/*  888 */     int t4 = ido + (ido << 1);
/*  889 */     for (k = 0; k < l1; k++) {
/*  890 */       int t5 = t3;
/*  891 */       float ti1 = cc[t1] + cc[t4];
/*  892 */       float ti2 = cc[t4] - cc[t1];
/*  893 */       float tr1 = cc[(t1 - 1)] - cc[(t4 - 1)];
/*  894 */       float tr2 = cc[(t1 - 1)] + cc[(t4 - 1)];
/*  895 */       ch[t5] = (tr2 + tr2);
/*      */       int tmp746_745 = (t5 + t0); t5 = tmp746_745; ch[tmp746_745] = (sqrt2 * (tr1 - ti1));
/*      */       int tmp765_764 = (t5 + t0); t5 = tmp765_764; ch[tmp765_764] = (ti2 + ti2);
/*      */       int tmp780_779 = (t5 + t0); t5 = tmp780_779; ch[tmp780_779] = (-sqrt2 * (tr1 + ti1));
/*      */ 
/*  900 */       t3 += ido;
/*  901 */       t1 += t2;
/*  902 */       t4 += t2;
/*      */     }
/*      */   }
/*      */ 
/*      */   static void dradbg(int ido, int ip, int l1, int idl1, float[] cc, float[] c1, float[] c2, float[] ch, float[] ch2, float[] wa, int index)
/*      */   {
/*  909 */     int ipph = 0; int t0 = 0; int t10 = 0;
/*      */ 
/*  911 */     int nbd = 0;
/*  912 */     float dcp = 0.0F; float dsp = 0.0F;
/*  913 */     int ipp2 = 0;
/*      */ 
/*  915 */     int state = 100;
/*      */     while (true)
/*      */     {
/*      */       int t1;
/*      */       int t2;
/*      */       int k;
/*      */       int i;
/*      */       int t2;
/*      */       int t5;
/*      */       int t7;
/*      */       int j;
/*      */       int t3;
/*      */       int j;
/*      */       int is;
/*  918 */       switch (state) {
/*      */       case 100:
/*  920 */         t10 = ip * ido;
/*  921 */         t0 = l1 * ido;
/*  922 */         float arg = tpi / ip;
/*  923 */         dcp = (float)Math.cos(arg);
/*  924 */         dsp = (float)Math.sin(arg);
/*  925 */         nbd = ido - 1 >>> 1;
/*  926 */         ipp2 = ip;
/*  927 */         ipph = ip + 1 >>> 1;
/*  928 */         if (ido < l1) {
/*  929 */           state = 103;
/*      */         }
/*      */         else {
/*  932 */           t1 = 0;
/*  933 */           t2 = 0;
/*  934 */           for (k = 0; k < l1; k++) {
/*  935 */             int t3 = t1;
/*  936 */             int t4 = t2;
/*  937 */             for (int i = 0; i < ido; i++) {
/*  938 */               ch[t3] = cc[t4];
/*  939 */               t3++;
/*  940 */               t4++;
/*      */             }
/*  942 */             t1 += ido;
/*  943 */             t2 += t10;
/*      */           }
/*  945 */           state = 106;
/*  946 */         }break;
/*      */       case 103:
/*  948 */         t1 = 0;
/*  949 */         for (i = 0; i < ido; i++) {
/*  950 */           t2 = t1;
/*  951 */           int t3 = t1;
/*  952 */           for (k = 0; k < l1; k++) {
/*  953 */             ch[t2] = cc[t3];
/*  954 */             t2 += ido;
/*  955 */             t3 += t10;
/*      */           }
/*  957 */           t1++;
/*      */         }
/*      */       case 106:
/*  960 */         t1 = 0;
/*  961 */         t2 = ipp2 * t0;
/*  962 */         t7 = t5 = ido << 1;
/*  963 */         for (j = 1; j < ipph; j++) {
/*  964 */           t1 += t0;
/*  965 */           t2 -= t0;
/*  966 */           int t3 = t1;
/*  967 */           int t4 = t2;
/*  968 */           int t6 = t5;
/*  969 */           for (int k = 0; k < l1; k++) {
/*  970 */             ch[t3] = (cc[(t6 - 1)] + cc[(t6 - 1)]);
/*  971 */             cc[t6] += cc[t6];
/*  972 */             t3 += ido;
/*  973 */             t4 += ido;
/*  974 */             t6 += t10;
/*      */           }
/*  976 */           t5 += t7;
/*      */         }
/*  978 */         if (ido == 1) {
/*  979 */           state = 116;
/*      */         }
/*  982 */         else if (nbd < l1) {
/*  983 */           state = 112;
/*      */         }
/*      */         else
/*      */         {
/*  987 */           t1 = 0;
/*  988 */           t2 = ipp2 * t0;
/*  989 */           t7 = 0;
/*  990 */           for (j = 1; j < ipph; j++) {
/*  991 */             t1 += t0;
/*  992 */             t2 -= t0;
/*  993 */             int t3 = t1;
/*  994 */             int t4 = t2;
/*      */ 
/*  996 */             t7 += (ido << 1);
/*  997 */             int t8 = t7;
/*  998 */             for (int k = 0; k < l1; k++) {
/*  999 */               t5 = t3;
/* 1000 */               int t6 = t4;
/* 1001 */               int t9 = t8;
/* 1002 */               int t11 = t8;
/* 1003 */               for (i = 2; i < ido; i += 2) {
/* 1004 */                 t5 += 2;
/* 1005 */                 t6 += 2;
/* 1006 */                 t9 += 2;
/* 1007 */                 t11 -= 2;
/* 1008 */                 cc[(t9 - 1)] += cc[(t11 - 1)];
/* 1009 */                 cc[(t9 - 1)] -= cc[(t11 - 1)];
/* 1010 */                 cc[t9] -= cc[t11];
/* 1011 */                 cc[t9] += cc[t11];
/*      */               }
/* 1013 */               t3 += ido;
/* 1014 */               t4 += ido;
/* 1015 */               t8 += t10;
/*      */             }
/*      */           }
/* 1018 */           state = 116;
/* 1019 */         }break;
/*      */       case 112:
/* 1021 */         t1 = 0;
/* 1022 */         t2 = ipp2 * t0;
/* 1023 */         t7 = 0;
/* 1024 */         for (j = 1; j < ipph; j++) {
/* 1025 */           t1 += t0;
/* 1026 */           t2 -= t0;
/* 1027 */           int t3 = t1;
/* 1028 */           int t4 = t2;
/* 1029 */           t7 += (ido << 1);
/* 1030 */           int t8 = t7;
/* 1031 */           int t9 = t7;
/* 1032 */           for (int i = 2; i < ido; i += 2) {
/* 1033 */             t3 += 2;
/* 1034 */             t4 += 2;
/* 1035 */             t8 += 2;
/* 1036 */             t9 -= 2;
/* 1037 */             t5 = t3;
/* 1038 */             int t6 = t4;
/* 1039 */             int t11 = t8;
/* 1040 */             int t12 = t9;
/* 1041 */             for (int k = 0; k < l1; k++) {
/* 1042 */               cc[(t11 - 1)] += cc[(t12 - 1)];
/* 1043 */               cc[(t11 - 1)] -= cc[(t12 - 1)];
/* 1044 */               cc[t11] -= cc[t12];
/* 1045 */               cc[t11] += cc[t12];
/* 1046 */               t5 += ido;
/* 1047 */               t6 += ido;
/* 1048 */               t11 += t10;
/* 1049 */               t12 += t10;
/*      */             }
/*      */           }
/*      */         }
/*      */       case 116:
/* 1054 */         float ar1 = 1.0F;
/* 1055 */         float ai1 = 0.0F;
/* 1056 */         t1 = 0;
/* 1057 */         int t9 = t2 = ipp2 * idl1;
/* 1058 */         t3 = (ip - 1) * idl1;
/* 1059 */         for (int l = 1; l < ipph; l++) {
/* 1060 */           t1 += idl1;
/* 1061 */           t2 -= idl1;
/*      */ 
/* 1063 */           float ar1h = dcp * ar1 - dsp * ai1;
/* 1064 */           ai1 = dcp * ai1 + dsp * ar1;
/* 1065 */           ar1 = ar1h;
/* 1066 */           int t4 = t1;
/* 1067 */           int t5 = t2;
/* 1068 */           int t6 = 0;
/* 1069 */           t7 = idl1;
/* 1070 */           int t8 = t3;
/* 1071 */           for (int ik = 0; ik < idl1; ik++) {
/* 1072 */             ch2[(t6++)] += ar1 * ch2[(t7++)];
/* 1073 */             c2[(t5++)] = (ai1 * ch2[(t8++)]);
/*      */           }
/* 1075 */           float dc2 = ar1;
/* 1076 */           float ds2 = ai1;
/* 1077 */           float ar2 = ar1;
/* 1078 */           float ai2 = ai1;
/*      */ 
/* 1080 */           t6 = idl1;
/* 1081 */           t7 = t9 - idl1;
/* 1082 */           for (j = 2; j < ipph; j++) {
/* 1083 */             t6 += idl1;
/* 1084 */             t7 -= idl1;
/* 1085 */             float ar2h = dc2 * ar2 - ds2 * ai2;
/* 1086 */             ai2 = dc2 * ai2 + ds2 * ar2;
/* 1087 */             ar2 = ar2h;
/* 1088 */             t4 = t1;
/* 1089 */             t5 = t2;
/* 1090 */             int t11 = t6;
/* 1091 */             int t12 = t7;
/* 1092 */             for (ik = 0; ik < idl1; ik++) {
/* 1093 */               c2[(t4++)] += ar2 * ch2[(t11++)];
/* 1094 */               c2[(t5++)] += ai2 * ch2[(t12++)];
/*      */             }
/*      */           }
/*      */         }
/*      */ 
/* 1099 */         t1 = 0;
/* 1100 */         for (j = 1; j < ipph; j++) {
/* 1101 */           t1 += idl1;
/* 1102 */           t2 = t1;
/* 1103 */           for (int ik = 0; ik < idl1; ik++) {
/* 1104 */             ch2[ik] += ch2[(t2++)];
/*      */           }
/*      */         }
/* 1107 */         t1 = 0;
/* 1108 */         t2 = ipp2 * t0;
/* 1109 */         for (j = 1; j < ipph; j++) {
/* 1110 */           t1 += t0;
/* 1111 */           t2 -= t0;
/* 1112 */           t3 = t1;
/* 1113 */           int t4 = t2;
/* 1114 */           for (int k = 0; k < l1; k++) {
/* 1115 */             c1[t3] -= c1[t4];
/* 1116 */             c1[t3] += c1[t4];
/* 1117 */             t3 += ido;
/* 1118 */             t4 += ido;
/*      */           }
/*      */         }
/*      */ 
/* 1122 */         if (ido == 1) {
/* 1123 */           state = 132;
/*      */         }
/* 1126 */         else if (nbd < l1) {
/* 1127 */           state = 128;
/*      */         }
/*      */         else
/*      */         {
/* 1131 */           t1 = 0;
/* 1132 */           t2 = ipp2 * t0;
/* 1133 */           for (j = 1; j < ipph; j++) {
/* 1134 */             t1 += t0;
/* 1135 */             t2 -= t0;
/* 1136 */             t3 = t1;
/* 1137 */             int t4 = t2;
/* 1138 */             for (int k = 0; k < l1; k++) {
/* 1139 */               int t5 = t3;
/* 1140 */               int t6 = t4;
/* 1141 */               for (int i = 2; i < ido; i += 2) {
/* 1142 */                 t5 += 2;
/* 1143 */                 t6 += 2;
/* 1144 */                 c1[(t5 - 1)] -= c1[t6];
/* 1145 */                 c1[(t5 - 1)] += c1[t6];
/* 1146 */                 c1[t5] += c1[(t6 - 1)];
/* 1147 */                 c1[t5] -= c1[(t6 - 1)];
/*      */               }
/* 1149 */               t3 += ido;
/* 1150 */               t4 += ido;
/*      */             }
/*      */           }
/* 1153 */           state = 132;
/* 1154 */         }break;
/*      */       case 128:
/* 1156 */         t1 = 0;
/* 1157 */         t2 = ipp2 * t0;
/* 1158 */         for (j = 1; j < ipph; j++) {
/* 1159 */           t1 += t0;
/* 1160 */           t2 -= t0;
/* 1161 */           t3 = t1;
/* 1162 */           int t4 = t2;
/* 1163 */           for (int i = 2; i < ido; i += 2) {
/* 1164 */             t3 += 2;
/* 1165 */             t4 += 2;
/* 1166 */             int t5 = t3;
/* 1167 */             int t6 = t4;
/* 1168 */             for (int k = 0; k < l1; k++) {
/* 1169 */               c1[(t5 - 1)] -= c1[t6];
/* 1170 */               c1[(t5 - 1)] += c1[t6];
/* 1171 */               c1[t5] += c1[(t6 - 1)];
/* 1172 */               c1[t5] -= c1[(t6 - 1)];
/* 1173 */               t5 += ido;
/* 1174 */               t6 += ido;
/*      */             }
/*      */           }
/*      */         }
/*      */       case 132:
/* 1179 */         if (ido == 1) {
/* 1180 */           return;
/*      */         }
/* 1182 */         for (int ik = 0; ik < idl1; ik++) {
/* 1183 */           c2[ik] = ch2[ik];
/*      */         }
/* 1185 */         t1 = 0;
/* 1186 */         for (j = 1; j < ip; j++) {
/* 1187 */           t2 = t1 += t0;
/* 1188 */           for (int k = 0; k < l1; k++) {
/* 1189 */             c1[t2] = ch[t2];
/* 1190 */             t2 += ido;
/*      */           }
/*      */         }
/*      */ 
/* 1194 */         if (nbd > l1) {
/* 1195 */           state = 139;
/*      */         }
/*      */         else
/*      */         {
/* 1199 */           is = -ido - 1;
/* 1200 */           t1 = 0;
/* 1201 */           for (j = 1; j < ip; j++) {
/* 1202 */             is += ido;
/* 1203 */             t1 += t0;
/* 1204 */             int idij = is;
/* 1205 */             int t2 = t1;
/* 1206 */             for (int i = 2; i < ido; i += 2) {
/* 1207 */               t2 += 2;
/* 1208 */               idij += 2;
/* 1209 */               int t3 = t2;
/* 1210 */               for (int k = 0; k < l1; k++) {
/* 1211 */                 c1[(t3 - 1)] = (wa[(index + idij - 1)] * ch[(t3 - 1)] - wa[(index + idij)] * ch[t3]);
/* 1212 */                 c1[t3] = (wa[(index + idij - 1)] * ch[t3] + wa[(index + idij)] * ch[(t3 - 1)]);
/* 1213 */                 t3 += ido;
/*      */               }
/*      */             }
/*      */           }
/*      */           return;
/*      */         }
/*      */         break;
/*      */       case 139:
/* 1220 */         is = -ido - 1;
/* 1221 */         t1 = 0;
/* 1222 */         for (j = 1; j < ip; j++) {
/* 1223 */           is += ido;
/* 1224 */           t1 += t0;
/* 1225 */           int t2 = t1;
/* 1226 */           for (int k = 0; k < l1; k++) {
/* 1227 */             int idij = is;
/* 1228 */             int t3 = t2;
/* 1229 */             for (int i = 2; i < ido; i += 2) {
/* 1230 */               idij += 2;
/* 1231 */               t3 += 2;
/* 1232 */               c1[(t3 - 1)] = (wa[(index + idij - 1)] * ch[(t3 - 1)] - wa[(index + idij)] * ch[t3]);
/* 1233 */               c1[t3] = (wa[(index + idij - 1)] * ch[t3] + wa[(index + idij)] * ch[(t3 - 1)]);
/*      */             }
/* 1235 */             t2 += ido; }  } 
/*      */       }
/*      */     }
/*      */     int t1;
/*      */     int is;
/*      */     int j;
/*      */   }
/*      */ 
/* 1245 */   static void drftb1(int n, float[] c, float[] ch, float[] wa, int index, int[] ifac) { int l2 = 0;
/*      */ 
/* 1247 */     int ip = 0; int ido = 0; int idl1 = 0;
/*      */ 
/* 1249 */     int nf = ifac[1];
/* 1250 */     int na = 0;
/* 1251 */     int l1 = 1;
/* 1252 */     int iw = 1;
/*      */ 
/* 1254 */     label484: for (int k1 = 0; k1 < nf; k1++) {
/* 1255 */       int state = 100;
/*      */       while (true)
/*      */       {
/*      */         int ix2;
/* 1257 */         switch (state) {
/*      */         case 100:
/* 1259 */           ip = ifac[(k1 + 2)];
/* 1260 */           l2 = ip * l1;
/* 1261 */           ido = n / l2;
/* 1262 */           idl1 = ido * l1;
/* 1263 */           if (ip != 4) {
/* 1264 */             state = 103;
/*      */           }
/*      */           else {
/* 1267 */             ix2 = iw + ido;
/* 1268 */             int ix3 = ix2 + ido;
/*      */ 
/* 1270 */             if (na != 0) {
/* 1271 */               dradb4(ido, l1, ch, c, wa, index + iw - 1, wa, index + ix2 - 1, wa, index + ix3 - 1);
/*      */             }
/*      */             else {
/* 1274 */               dradb4(ido, l1, c, ch, wa, index + iw - 1, wa, index + ix2 - 1, wa, index + ix3 - 1);
/*      */             }
/* 1276 */             na = 1 - na;
/* 1277 */             state = 115;
/* 1278 */           }break;
/*      */         case 103:
/* 1280 */           if (ip != 2) {
/* 1281 */             state = 106;
/*      */           }
/*      */           else
/*      */           {
/* 1285 */             if (na != 0)
/* 1286 */               dradb2(ido, l1, ch, c, wa, index + iw - 1);
/*      */             else
/* 1288 */               dradb2(ido, l1, c, ch, wa, index + iw - 1);
/* 1289 */             na = 1 - na;
/* 1290 */             state = 115;
/* 1291 */           }break;
/*      */         case 106:
/* 1294 */           if (ip != 3) {
/* 1295 */             state = 109;
/*      */           }
/*      */           else
/*      */           {
/* 1299 */             ix2 = iw + ido;
/* 1300 */             if (na != 0)
/* 1301 */               dradb3(ido, l1, ch, c, wa, index + iw - 1, wa, index + ix2 - 1);
/*      */             else
/* 1303 */               dradb3(ido, l1, c, ch, wa, index + iw - 1, wa, index + ix2 - 1);
/* 1304 */             na = 1 - na;
/* 1305 */             state = 115;
/* 1306 */           }break;
/*      */         case 109:
/* 1308 */           if (na != 0)
/* 1309 */             dradbg(ido, ip, l1, idl1, ch, ch, ch, c, c, wa, index + iw - 1);
/*      */           else
/* 1311 */             dradbg(ido, ip, l1, idl1, c, c, c, ch, ch, wa, index + iw - 1);
/* 1312 */           if (ido == 1) {
/* 1313 */             na = 1 - na;
/*      */           }
/*      */         case 115:
/* 1316 */           l1 = l2;
/* 1317 */           iw += (ip - 1) * ido;
/* 1318 */           break label484;
/*      */         }
/*      */       }
/*      */     }
/* 1322 */     if (na == 0)
/* 1323 */       return;
/* 1324 */     for (int i = 0; i < n; i++)
/* 1325 */       c[i] = ch[i];
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jorbis.Drft
 * JD-Core Version:    0.6.2
 */