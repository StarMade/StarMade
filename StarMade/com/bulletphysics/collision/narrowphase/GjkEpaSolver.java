/*     */ package com.bulletphysics.collision.narrowphase;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.collision.shapes.ConvexShape;
/*     */ import com.bulletphysics.linearmath.MatrixUtil;
/*     */ import com.bulletphysics.linearmath.QuaternionUtil;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.util.ArrayPool;
/*     */ import com.bulletphysics.util.ObjectStackList;
/*     */ import java.util.Arrays;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Quat4f;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class GjkEpaSolver
/*     */ {
/*  52 */   protected final ArrayPool<float[]> floatArrays = ArrayPool.get(Float.TYPE);
/*     */ 
/*  54 */   protected final ObjectStackList<Mkv> stackMkv = new ObjectStackList(Mkv.class);
/*  55 */   protected final ObjectStackList<He> stackHe = new ObjectStackList(He.class);
/*  56 */   protected final ObjectStackList<Face> stackFace = new ObjectStackList(Face.class);
/*     */   private static final float cstInf = 3.4028235E+38F;
/*     */   private static final float cstPi = 3.141593F;
/*     */   private static final float cst2Pi = 6.283186F;
/*     */   private static final int GJK_maxiterations = 128;
/*     */   private static final int GJK_hashsize = 64;
/*     */   private static final int GJK_hashmask = 63;
/*     */   private static final float GJK_insimplex_eps = 1.0E-004F;
/*     */   private static final float GJK_sqinsimplex_eps = 9.999999E-009F;
/*     */   private static final int EPA_maxiterations = 256;
/*     */   private static final float EPA_inface_eps = 0.01F;
/*     */   private static final float EPA_accuracy = 0.001F;
/* 471 */   private static int[] mod3 = { 0, 1, 2, 0, 1 };
/*     */ 
/* 473 */   private static final int[][] tetrahedron_fidx = { { 2, 1, 0 }, { 3, 0, 1 }, { 3, 1, 2 }, { 3, 2, 0 } };
/* 474 */   private static final int[][] tetrahedron_eidx = { { 0, 0, 2, 1 }, { 0, 1, 1, 1 }, { 0, 2, 3, 1 }, { 1, 0, 3, 2 }, { 2, 0, 1, 2 }, { 3, 0, 2, 2 } };
/*     */ 
/* 476 */   private static final int[][] hexahedron_fidx = { { 2, 0, 4 }, { 4, 1, 2 }, { 1, 4, 0 }, { 0, 3, 1 }, { 0, 2, 3 }, { 1, 3, 2 } };
/* 477 */   private static final int[][] hexahedron_eidx = { { 0, 0, 4, 0 }, { 0, 1, 2, 1 }, { 0, 2, 1, 2 }, { 1, 1, 5, 2 }, { 1, 0, 2, 0 }, { 2, 2, 3, 2 }, { 3, 1, 5, 0 }, { 3, 0, 4, 2 }, { 5, 1, 4, 1 } };
/*     */ 
/* 829 */   private GJK gjk = new GJK();
/*     */ 
/*     */   protected void pushStack()
/*     */   {
/*  59 */     this.stackMkv.push();
/*  60 */     this.stackHe.push();
/*  61 */     this.stackFace.push();
/*     */   }
/*     */ 
/*     */   protected void popStack() {
/*  65 */     this.stackMkv.pop();
/*  66 */     this.stackHe.pop();
/*  67 */     this.stackFace.pop();
/*     */   }
/*     */ 
/*     */   public boolean collide(ConvexShape shape0, Transform wtrs0, ConvexShape shape1, Transform wtrs1, float radialmargin, Results results)
/*     */   {
/* 838 */     results.witnesses[0].set(0.0F, 0.0F, 0.0F);
/* 839 */     results.witnesses[1].set(0.0F, 0.0F, 0.0F);
/* 840 */     results.normal.set(0.0F, 0.0F, 0.0F);
/* 841 */     results.depth = 0.0F;
/* 842 */     results.status = ResultsStatus.Separated;
/* 843 */     results.epa_iterations = 0;
/* 844 */     results.gjk_iterations = 0;
/*     */ 
/* 846 */     this.gjk.init(wtrs0.basis, wtrs0.origin, shape0, wtrs1.basis, wtrs1.origin, shape1, radialmargin + 0.001F);
/*     */     try
/*     */     {
/* 851 */       boolean collide = this.gjk.SearchOrigin();
/* 852 */       results.gjk_iterations = (this.gjk.iterations + 1);
/*     */       EPA epa;
/* 853 */       if (collide)
/*     */       {
/* 855 */         epa = new EPA(this.gjk);
/* 856 */         float pd = epa.EvaluatePD();
/* 857 */         results.epa_iterations = (epa.iterations + 1);
/* 858 */         if (pd > 0.0F) {
/* 859 */           results.status = ResultsStatus.Penetrating;
/* 860 */           results.normal.set(epa.normal);
/* 861 */           results.depth = pd;
/* 862 */           results.witnesses[0].set(epa.nearest[0]);
/* 863 */           results.witnesses[1].set(epa.nearest[1]);
/* 864 */           return true;
/*     */         }
/*     */ 
/* 867 */         if (epa.failed) {
/* 868 */           results.status = ResultsStatus.EPA_Failed;
/*     */         }
/*     */ 
/*     */       }
/* 873 */       else if (this.gjk.failed) {
/* 874 */         results.status = ResultsStatus.GJK_Failed;
/*     */       }
/*     */ 
/* 877 */       return 0;
/*     */     }
/*     */     finally {
/* 880 */       this.gjk.destroy();
/*     */     }
/*     */   }
/*     */ 
/*     */   protected class EPA
/*     */   {
/*     */     public GjkEpaSolver.GJK gjk;
/*     */     public GjkEpaSolver.Face root;
/*     */     public int nfaces;
/*     */     public int iterations;
/* 498 */     public final Vector3f[][] features = new Vector3f[2][3];
/* 499 */     public final Vector3f[] nearest = { new Vector3f(), new Vector3f() };
/* 500 */     public final Vector3f normal = new Vector3f();
/*     */     public float depth;
/*     */     public boolean failed;
/*     */ 
/*     */     public EPA(GjkEpaSolver.GJK pgjk)
/*     */     {
/* 505 */       for (int i = 0; i < this.features.length; i++) {
/* 506 */         for (int j = 0; j < this.features[i].length; j++) {
/* 507 */           this.features[i][j] = new Vector3f();
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 513 */       this.gjk = pgjk;
/*     */     }
/*     */ 
/*     */     public Vector3f GetCoordinates(GjkEpaSolver.Face arg1, Vector3f arg2)
/*     */     {
/* 518 */       .Stack localStack = .Stack.get();
/*     */       try { localStack.push$javax$vecmath$Vector3f(); Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 519 */         Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/* 520 */         Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 522 */         Vector3f o = localStack.get$javax$vecmath$Vector3f();
/* 523 */         o.scale(-face.d, face.n);
/*     */ 
/* 525 */         float[] a = (float[])GjkEpaSolver.this.floatArrays.getFixed(3);
/*     */ 
/* 527 */         tmp1.sub(face.v[0].w, o);
/* 528 */         tmp2.sub(face.v[1].w, o);
/* 529 */         tmp.cross(tmp1, tmp2);
/* 530 */         a[0] = tmp.length();
/*     */ 
/* 532 */         tmp1.sub(face.v[1].w, o);
/* 533 */         tmp2.sub(face.v[2].w, o);
/* 534 */         tmp.cross(tmp1, tmp2);
/* 535 */         a[1] = tmp.length();
/*     */ 
/* 537 */         tmp1.sub(face.v[2].w, o);
/* 538 */         tmp2.sub(face.v[0].w, o);
/* 539 */         tmp.cross(tmp1, tmp2);
/* 540 */         a[2] = tmp.length();
/*     */ 
/* 542 */         float sm = a[0] + a[1] + a[2];
/*     */ 
/* 544 */         out.set(a[1], a[2], a[0]);
/* 545 */         out.scale(1.0F / (sm > 0.0F ? sm : 1.0F));
/*     */ 
/* 547 */         GjkEpaSolver.this.floatArrays.release(a);
/*     */ 
/* 549 */         return out; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */     }
/*     */ 
/*     */     public GjkEpaSolver.Face FindBest() {
/* 553 */       GjkEpaSolver.Face bf = null;
/* 554 */       if (this.root != null) {
/* 555 */         GjkEpaSolver.Face cf = this.root;
/* 556 */         float bd = 3.4028235E+38F;
/*     */         do {
/* 558 */           if (cf.d < bd) {
/* 559 */             bd = cf.d;
/* 560 */             bf = cf;
/*     */           }
/*     */         }
/* 563 */         while (null != (cf = cf.next));
/*     */       }
/* 565 */       return bf;
/*     */     }
/*     */ 
/*     */     public boolean Set(GjkEpaSolver.Face arg1, GjkEpaSolver.Mkv arg2, GjkEpaSolver.Mkv arg3, GjkEpaSolver.Mkv arg4) {
/* 569 */       .Stack localStack = .Stack.get();
/*     */       try { localStack.push$javax$vecmath$Vector3f(); Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/* 570 */         Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/* 571 */         Vector3f tmp3 = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 573 */         Vector3f nrm = localStack.get$javax$vecmath$Vector3f();
/* 574 */         tmp1.sub(b.w, a.w);
/* 575 */         tmp2.sub(c.w, a.w);
/* 576 */         nrm.cross(tmp1, tmp2);
/*     */ 
/* 578 */         float len = nrm.length();
/*     */ 
/* 580 */         tmp1.cross(a.w, b.w);
/* 581 */         tmp2.cross(b.w, c.w);
/* 582 */         tmp3.cross(c.w, a.w);
/*     */ 
/* 584 */         boolean valid = (tmp1.dot(nrm) >= -0.01F) && (tmp2.dot(nrm) >= -0.01F) && (tmp3.dot(nrm) >= -0.01F);
/*     */ 
/* 588 */         f.v[0] = a;
/* 589 */         f.v[1] = b;
/* 590 */         f.v[2] = c;
/* 591 */         f.mark = 0;
/* 592 */         f.n.scale(1.0F / (len > 0.0F ? len : 3.4028235E+38F), nrm);
/* 593 */         f.d = Math.max(0.0F, -f.n.dot(a.w));
/* 594 */         return valid; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */     }
/*     */ 
/*     */     public GjkEpaSolver.Face NewFace(GjkEpaSolver.Mkv a, GjkEpaSolver.Mkv b, GjkEpaSolver.Mkv c)
/*     */     {
/* 599 */       GjkEpaSolver.Face pf = (GjkEpaSolver.Face)GjkEpaSolver.this.stackFace.get();
/* 600 */       if (Set(pf, a, b, c)) {
/* 601 */         if (this.root != null) {
/* 602 */           this.root.prev = pf;
/*     */         }
/* 604 */         pf.prev = null;
/* 605 */         pf.next = this.root;
/* 606 */         this.root = pf;
/* 607 */         this.nfaces += 1;
/*     */       }
/*     */       else {
/* 610 */         pf.prev = (pf.next = null);
/*     */       }
/* 612 */       return pf;
/*     */     }
/*     */ 
/*     */     public void Detach(GjkEpaSolver.Face face) {
/* 616 */       if ((face.prev != null) || (face.next != null)) {
/* 617 */         this.nfaces -= 1;
/* 618 */         if (face == this.root) {
/* 619 */           this.root = face.next;
/* 620 */           this.root.prev = null;
/*     */         }
/* 623 */         else if (face.next == null) {
/* 624 */           face.prev.next = null;
/*     */         }
/*     */         else {
/* 627 */           face.prev.next = face.next;
/* 628 */           face.next.prev = face.prev;
/*     */         }
/*     */ 
/* 631 */         face.prev = (face.next = null);
/*     */       }
/*     */     }
/*     */ 
/*     */     public void Link(GjkEpaSolver.Face f0, int e0, GjkEpaSolver.Face f1, int e1) {
/* 636 */       f0.f[e0] = f1; f1.e[e1] = e0;
/* 637 */       f1.f[e1] = f0; f0.e[e0] = e1;
/*     */     }
/*     */ 
/*     */     public GjkEpaSolver.Mkv Support(Vector3f w)
/*     */     {
/* 642 */       GjkEpaSolver.Mkv v = (GjkEpaSolver.Mkv)GjkEpaSolver.this.stackMkv.get();
/* 643 */       this.gjk.Support(w, v);
/* 644 */       return v;
/*     */     }
/*     */ 
/*     */     public int BuildHorizon(int markid, GjkEpaSolver.Mkv w, GjkEpaSolver.Face f, int e, GjkEpaSolver.Face[] cf, GjkEpaSolver.Face[] ff) {
/* 648 */       int ne = 0;
/* 649 */       if (f.mark != markid) {
/* 650 */         int e1 = GjkEpaSolver.mod3[(e + 1)];
/* 651 */         if (f.n.dot(w.w) + f.d > 0.0F) {
/* 652 */           GjkEpaSolver.Face nf = NewFace(f.v[e1], f.v[e], w);
/* 653 */           Link(nf, 0, f, e);
/* 654 */           if (cf[0] != null) {
/* 655 */             Link(cf[0], 1, nf, 2);
/*     */           }
/*     */           else {
/* 658 */             ff[0] = nf;
/*     */           }
/* 660 */           cf[0] = nf;
/* 661 */           ne = 1;
/*     */         }
/*     */         else {
/* 664 */           int e2 = GjkEpaSolver.mod3[(e + 2)];
/* 665 */           Detach(f);
/* 666 */           f.mark = markid;
/* 667 */           ne += BuildHorizon(markid, w, f.f[e1], f.e[e1], cf, ff);
/* 668 */           ne += BuildHorizon(markid, w, f.f[e2], f.e[e2], cf, ff);
/*     */         }
/*     */       }
/* 671 */       return ne;
/*     */     }
/*     */ 
/*     */     public float EvaluatePD() {
/* 675 */       return EvaluatePD(0.001F);
/*     */     }
/*     */ 
/*     */     // ERROR //
/*     */     public float EvaluatePD(float arg1)
/*     */     {
/*     */       // Byte code:
/*     */       //   0: invokestatic 68	com/bulletphysics/$Stack:get	()Lcom/bulletphysics/$Stack;
/*     */       //   3: astore 15
/*     */       //   5: aload 15
/*     */       //   7: invokevirtual 71	com/bulletphysics/$Stack:push$javax$vecmath$Vector3f	()V
/*     */       //   10: aload_0
/*     */       //   11: getfield 40	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:this$0	Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver;
/*     */       //   14: invokevirtual 245	com/bulletphysics/collision/narrowphase/GjkEpaSolver:pushStack	()V
/*     */       //   17: aload 15
/*     */       //   19: invokevirtual 75	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/*     */       //   22: astore_2
/*     */       //   23: aconst_null
/*     */       //   24: astore_3
/*     */       //   25: iconst_1
/*     */       //   26: istore 4
/*     */       //   28: aload_0
/*     */       //   29: ldc 246
/*     */       //   31: putfield 248	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:depth	F
/*     */       //   34: aload_0
/*     */       //   35: getfield 53	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:normal	Ljavax/vecmath/Vector3f;
/*     */       //   38: fconst_0
/*     */       //   39: fconst_0
/*     */       //   40: fconst_0
/*     */       //   41: invokevirtual 120	javax/vecmath/Vector3f:set	(FFF)V
/*     */       //   44: aload_0
/*     */       //   45: aconst_null
/*     */       //   46: putfield 144	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:root	Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Face;
/*     */       //   49: aload_0
/*     */       //   50: iconst_0
/*     */       //   51: putfield 193	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:nfaces	I
/*     */       //   54: aload_0
/*     */       //   55: iconst_0
/*     */       //   56: putfield 250	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:iterations	I
/*     */       //   59: aload_0
/*     */       //   60: iconst_0
/*     */       //   61: putfield 252	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:failed	Z
/*     */       //   64: aload_0
/*     */       //   65: getfield 55	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:gjk	Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$GJK;
/*     */       //   68: invokevirtual 256	com/bulletphysics/collision/narrowphase/GjkEpaSolver$GJK:EncloseOrigin	()Z
/*     */       //   71: ifeq +289 -> 360
/*     */       //   74: aconst_null
/*     */       //   75: checkcast 258	[[I
/*     */       //   78: astore 5
/*     */       //   80: iconst_0
/*     */       //   81: istore 6
/*     */       //   83: iconst_0
/*     */       //   84: istore 7
/*     */       //   86: aconst_null
/*     */       //   87: checkcast 258	[[I
/*     */       //   90: astore 8
/*     */       //   92: iconst_0
/*     */       //   93: istore 9
/*     */       //   95: iconst_0
/*     */       //   96: istore 10
/*     */       //   98: iconst_5
/*     */       //   99: anewarray 16	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Mkv
/*     */       //   102: astore 11
/*     */       //   104: bipush 6
/*     */       //   106: anewarray 12	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Face
/*     */       //   109: astore 12
/*     */       //   111: aload_0
/*     */       //   112: getfield 55	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:gjk	Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$GJK;
/*     */       //   115: getfield 261	com/bulletphysics/collision/narrowphase/GjkEpaSolver$GJK:order	I
/*     */       //   118: lookupswitch	default:+76->194, 3:+26->144, 4:+52->170
/*     */       //   145: aconst_null
/*     */       //   146: lconst_0
/*     */       //   147: astore 5
/*     */       //   149: iconst_0
/*     */       //   150: istore 6
/*     */       //   152: iconst_4
/*     */       //   153: istore 7
/*     */       //   155: invokestatic 268	com/bulletphysics/collision/narrowphase/GjkEpaSolver:access$200	()[[I
/*     */       //   158: astore 8
/*     */       //   160: iconst_0
/*     */       //   161: istore 9
/*     */       //   163: bipush 6
/*     */       //   165: istore 10
/*     */       //   167: goto +27 -> 194
/*     */       //   170: invokestatic 271	com/bulletphysics/collision/narrowphase/GjkEpaSolver:access$300	()[[I
/*     */       //   173: astore 5
/*     */       //   175: iconst_0
/*     */       //   176: istore 6
/*     */       //   178: bipush 6
/*     */       //   180: istore 7
/*     */       //   182: invokestatic 274	com/bulletphysics/collision/narrowphase/GjkEpaSolver:access$400	()[[I
/*     */       //   185: astore 8
/*     */       //   187: iconst_0
/*     */       //   188: istore 9
/*     */       //   190: bipush 9
/*     */       //   192: istore 10
/*     */       //   194: iconst_0
/*     */       //   195: istore 13
/*     */       //   197: iload 13
/*     */       //   199: aload_0
/*     */       //   200: getfield 55	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:gjk	Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$GJK;
/*     */       //   203: getfield 261	com/bulletphysics/collision/narrowphase/GjkEpaSolver$GJK:order	I
/*     */       //   206: if_icmpgt +39 -> 245
/*     */       //   209: aload 11
/*     */       //   211: iload 13
/*     */       //   213: new 16	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Mkv
/*     */       //   216: dup
/*     */       //   217: invokespecial 275	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Mkv:<init>	()V
/*     */       //   220: aastore
/*     */       //   221: aload 11
/*     */       //   223: iload 13
/*     */       //   225: aaload
/*     */       //   226: aload_0
/*     */       //   227: getfield 55	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:gjk	Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$GJK;
/*     */       //   230: getfield 278	com/bulletphysics/collision/narrowphase/GjkEpaSolver$GJK:simplex	[Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Mkv;
/*     */       //   233: iload 13
/*     */       //   235: aaload
/*     */       //   236: invokevirtual 281	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Mkv:set	(Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Mkv;)V
/*     */       //   239: iinc 13 1
/*     */       //   242: goto -45 -> 197
/*     */       //   245: iconst_0
/*     */       //   246: istore 13
/*     */       //   248: iload 13
/*     */       //   250: iload 7
/*     */       //   252: if_icmpge +51 -> 303
/*     */       //   255: aload 12
/*     */       //   257: iload 13
/*     */       //   259: aload_0
/*     */       //   260: aload 11
/*     */       //   262: aload 5
/*     */       //   264: iload 6
/*     */       //   266: aaload
/*     */       //   267: iconst_0
/*     */       //   268: iaload
/*     */       //   269: aaload
/*     */       //   270: aload 11
/*     */       //   272: aload 5
/*     */       //   274: iload 6
/*     */       //   276: aaload
/*     */       //   277: iconst_1
/*     */       //   278: iaload
/*     */       //   279: aaload
/*     */       //   280: aload 11
/*     */       //   282: aload 5
/*     */       //   284: iload 6
/*     */       //   286: aaload
/*     */       //   287: iconst_2
/*     */       //   288: iaload
/*     */       //   289: aaload
/*     */       //   290: invokevirtual 225	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:NewFace	(Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Mkv;Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Mkv;Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Mkv;)Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Face;
/*     */       //   293: aastore
/*     */       //   294: iinc 13 1
/*     */       //   297: iinc 6 1
/*     */       //   300: goto -52 -> 248
/*     */       //   303: iconst_0
/*     */       //   304: istore 13
/*     */       //   306: iload 13
/*     */       //   308: iload 10
/*     */       //   310: if_icmpge +50 -> 360
/*     */       //   313: aload_0
/*     */       //   314: aload 12
/*     */       //   316: aload 8
/*     */       //   318: iload 9
/*     */       //   320: aaload
/*     */       //   321: iconst_0
/*     */       //   322: iaload
/*     */       //   323: aaload
/*     */       //   324: aload 8
/*     */       //   326: iload 9
/*     */       //   328: aaload
/*     */       //   329: iconst_1
/*     */       //   330: iaload
/*     */       //   331: aload 12
/*     */       //   333: aload 8
/*     */       //   335: iload 9
/*     */       //   337: aaload
/*     */       //   338: iconst_2
/*     */       //   339: iaload
/*     */       //   340: aaload
/*     */       //   341: aload 8
/*     */       //   343: iload 9
/*     */       //   345: aaload
/*     */       //   346: iconst_3
/*     */       //   347: iaload
/*     */       //   348: invokevirtual 227	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:Link	(Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Face;ILcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Face;I)V
/*     */       //   351: iinc 13 1
/*     */       //   354: iinc 9 1
/*     */       //   357: goto -51 -> 306
/*     */       //   360: iconst_0
/*     */       //   361: aload_0
/*     */       //   362: getfield 193	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:nfaces	I
/*     */       //   365: if_icmpne +24 -> 389
/*     */       //   368: aload_0
/*     */       //   369: getfield 248	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:depth	F
/*     */       //   372: fstore 5
/*     */       //   374: aload_0
/*     */       //   375: getfield 40	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:this$0	Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver;
/*     */       //   378: invokevirtual 284	com/bulletphysics/collision/narrowphase/GjkEpaSolver:popStack	()V
/*     */       //   381: fload 5
/*     */       //   383: aload 15
/*     */       //   385: invokevirtual 130	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
/*     */       //   388: freturn
/*     */       //   389: aload_0
/*     */       //   390: getfield 250	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:iterations	I
/*     */       //   393: sipush 256
/*     */       //   396: if_icmpge +185 -> 581
/*     */       //   399: aload_0
/*     */       //   400: invokevirtual 286	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:FindBest	()Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Face;
/*     */       //   403: astore 5
/*     */       //   405: aload 5
/*     */       //   407: ifnull +174 -> 581
/*     */       //   410: aload_2
/*     */       //   411: aload 5
/*     */       //   413: getfield 81	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Face:n	Ljavax/vecmath/Vector3f;
/*     */       //   416: invokevirtual 290	javax/vecmath/Vector3f:negate	(Ljavax/vecmath/Tuple3f;)V
/*     */       //   419: aload_0
/*     */       //   420: aload_2
/*     */       //   421: invokevirtual 292	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:Support	(Ljavax/vecmath/Vector3f;)Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Mkv;
/*     */       //   424: astore 6
/*     */       //   426: aload 5
/*     */       //   428: getfield 81	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Face:n	Ljavax/vecmath/Vector3f;
/*     */       //   431: aload 6
/*     */       //   433: getfield 104	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Mkv:w	Ljavax/vecmath/Vector3f;
/*     */       //   436: invokevirtual 157	javax/vecmath/Vector3f:dot	(Ljavax/vecmath/Vector3f;)F
/*     */       //   439: aload 5
/*     */       //   441: getfield 78	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Face:d	F
/*     */       //   444: fadd
/*     */       //   445: fstore 7
/*     */       //   447: aload 5
/*     */       //   449: astore_3
/*     */       //   450: fload 7
/*     */       //   452: fload_1
/*     */       //   453: fneg
/*     */       //   454: fcmpg
/*     */       //   455: ifge +126 -> 581
/*     */       //   458: iconst_1
/*     */       //   459: anewarray 12	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Face
/*     */       //   462: dup
/*     */       //   463: iconst_0
/*     */       //   464: aconst_null
/*     */       //   465: aastore
/*     */       //   466: astore 8
/*     */       //   468: iconst_1
/*     */       //   469: anewarray 12	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Face
/*     */       //   472: dup
/*     */       //   473: iconst_0
/*     */       //   474: aconst_null
/*     */       //   475: aastore
/*     */       //   476: astore 9
/*     */       //   478: iconst_0
/*     */       //   479: istore 10
/*     */       //   481: aload_0
/*     */       //   482: aload 5
/*     */       //   484: invokevirtual 229	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:Detach	(Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Face;)V
/*     */       //   487: aload 5
/*     */       //   489: iinc 4 1
/*     */       //   492: iload 4
/*     */       //   494: putfield 161	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Face:mark	I
/*     */       //   497: iconst_0
/*     */       //   498: istore 11
/*     */       //   500: iload 11
/*     */       //   502: iconst_3
/*     */       //   503: if_icmpge +42 -> 545
/*     */       //   506: iload 10
/*     */       //   508: aload_0
/*     */       //   509: iload 4
/*     */       //   511: aload 6
/*     */       //   513: aload 5
/*     */       //   515: getfield 201	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Face:f	[Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Face;
/*     */       //   518: iload 11
/*     */       //   520: aaload
/*     */       //   521: aload 5
/*     */       //   523: getfield 205	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Face:e	[I
/*     */       //   526: iload 11
/*     */       //   528: iaload
/*     */       //   529: aload 8
/*     */       //   531: aload 9
/*     */       //   533: invokevirtual 231	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:BuildHorizon	(ILcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Mkv;Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Face;I[Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Face;[Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Face;)I
/*     */       //   536: iadd
/*     */       //   537: istore 10
/*     */       //   539: iinc 11 1
/*     */       //   542: goto -42 -> 500
/*     */       //   545: iload 10
/*     */       //   547: iconst_2
/*     */       //   548: if_icmpgt +6 -> 554
/*     */       //   551: goto +30 -> 581
/*     */       //   554: aload_0
/*     */       //   555: aload 8
/*     */       //   557: iconst_0
/*     */       //   558: aaload
/*     */       //   559: iconst_1
/*     */       //   560: aload 9
/*     */       //   562: iconst_0
/*     */       //   563: aaload
/*     */       //   564: iconst_2
/*     */       //   565: invokevirtual 227	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:Link	(Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Face;ILcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Face;I)V
/*     */       //   568: aload_0
/*     */       //   569: dup
/*     */       //   570: getfield 250	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:iterations	I
/*     */       //   573: iconst_1
/*     */       //   574: iadd
/*     */       //   575: putfield 250	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:iterations	I
/*     */       //   578: goto -189 -> 389
/*     */       //   581: aload_3
/*     */       //   582: ifnull +281 -> 863
/*     */       //   585: aload_0
/*     */       //   586: aload_3
/*     */       //   587: aload 15
/*     */       //   589: invokevirtual 75	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/*     */       //   592: invokevirtual 294	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:GetCoordinates	(Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Face;Ljavax/vecmath/Vector3f;)Ljavax/vecmath/Vector3f;
/*     */       //   595: astore 5
/*     */       //   597: aload_0
/*     */       //   598: getfield 53	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:normal	Ljavax/vecmath/Vector3f;
/*     */       //   601: aload_3
/*     */       //   602: getfield 81	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Face:n	Ljavax/vecmath/Vector3f;
/*     */       //   605: invokevirtual 296	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/*     */       //   608: aload_0
/*     */       //   609: fconst_0
/*     */       //   610: aload_3
/*     */       //   611: getfield 78	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Face:d	F
/*     */       //   614: invokestatic 167	java/lang/Math:max	(FF)F
/*     */       //   617: putfield 248	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:depth	F
/*     */       //   620: iconst_0
/*     */       //   621: istore 6
/*     */       //   623: iload 6
/*     */       //   625: iconst_2
/*     */       //   626: if_icmpge +75 -> 701
/*     */       //   629: iload 6
/*     */       //   631: ifeq +9 -> 640
/*     */       //   634: ldc_w 297
/*     */       //   637: goto +4 -> 641
/*     */       //   640: fconst_1
/*     */       //   641: fstore 7
/*     */       //   643: iconst_0
/*     */       //   644: istore 8
/*     */       //   646: iload 8
/*     */       //   648: iconst_3
/*     */       //   649: if_icmpge +46 -> 695
/*     */       //   652: aload_2
/*     */       //   653: fload 7
/*     */       //   655: aload_3
/*     */       //   656: getfield 101	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Face:v	[Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Mkv;
/*     */       //   659: iload 8
/*     */       //   661: aaload
/*     */       //   662: getfield 300	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Mkv:r	Ljavax/vecmath/Vector3f;
/*     */       //   665: invokevirtual 85	javax/vecmath/Vector3f:scale	(FLjavax/vecmath/Tuple3f;)V
/*     */       //   668: aload_0
/*     */       //   669: getfield 55	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:gjk	Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$GJK;
/*     */       //   672: aload_2
/*     */       //   673: iload 6
/*     */       //   675: aload_0
/*     */       //   676: getfield 46	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:features	[[Ljavax/vecmath/Vector3f;
/*     */       //   679: iload 6
/*     */       //   681: aaload
/*     */       //   682: iload 8
/*     */       //   684: aaload
/*     */       //   685: invokevirtual 304	com/bulletphysics/collision/narrowphase/GjkEpaSolver$GJK:LocalSupport	(Ljavax/vecmath/Vector3f;ILjavax/vecmath/Vector3f;)Ljavax/vecmath/Vector3f;
/*     */       //   688: pop
/*     */       //   689: iinc 8 1
/*     */       //   692: goto -46 -> 646
/*     */       //   695: iinc 6 1
/*     */       //   698: goto -75 -> 623
/*     */       //   701: aload 15
/*     */       //   703: invokevirtual 75	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/*     */       //   706: astore 6
/*     */       //   708: aload 15
/*     */       //   710: invokevirtual 75	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/*     */       //   713: astore 7
/*     */       //   715: aload 15
/*     */       //   717: invokevirtual 75	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/*     */       //   720: astore 8
/*     */       //   722: aload 6
/*     */       //   724: aload 5
/*     */       //   726: getfield 307	javax/vecmath/Vector3f:x	F
/*     */       //   729: aload_0
/*     */       //   730: getfield 46	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:features	[[Ljavax/vecmath/Vector3f;
/*     */       //   733: iconst_0
/*     */       //   734: aaload
/*     */       //   735: iconst_0
/*     */       //   736: aaload
/*     */       //   737: invokevirtual 85	javax/vecmath/Vector3f:scale	(FLjavax/vecmath/Tuple3f;)V
/*     */       //   740: aload 7
/*     */       //   742: aload 5
/*     */       //   744: getfield 310	javax/vecmath/Vector3f:y	F
/*     */       //   747: aload_0
/*     */       //   748: getfield 46	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:features	[[Ljavax/vecmath/Vector3f;
/*     */       //   751: iconst_0
/*     */       //   752: aaload
/*     */       //   753: iconst_1
/*     */       //   754: aaload
/*     */       //   755: invokevirtual 85	javax/vecmath/Vector3f:scale	(FLjavax/vecmath/Tuple3f;)V
/*     */       //   758: aload 8
/*     */       //   760: aload 5
/*     */       //   762: getfield 313	javax/vecmath/Vector3f:z	F
/*     */       //   765: aload_0
/*     */       //   766: getfield 46	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:features	[[Ljavax/vecmath/Vector3f;
/*     */       //   769: iconst_0
/*     */       //   770: aaload
/*     */       //   771: iconst_2
/*     */       //   772: aaload
/*     */       //   773: invokevirtual 85	javax/vecmath/Vector3f:scale	(FLjavax/vecmath/Tuple3f;)V
/*     */       //   776: aload_0
/*     */       //   777: getfield 51	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:nearest	[Ljavax/vecmath/Vector3f;
/*     */       //   780: iconst_0
/*     */       //   781: aaload
/*     */       //   782: aload 6
/*     */       //   784: aload 7
/*     */       //   786: aload 8
/*     */       //   788: invokestatic 319	com/bulletphysics/linearmath/VectorUtil:add	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;)V
/*     */       //   791: aload 6
/*     */       //   793: aload 5
/*     */       //   795: getfield 307	javax/vecmath/Vector3f:x	F
/*     */       //   798: aload_0
/*     */       //   799: getfield 46	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:features	[[Ljavax/vecmath/Vector3f;
/*     */       //   802: iconst_1
/*     */       //   803: aaload
/*     */       //   804: iconst_0
/*     */       //   805: aaload
/*     */       //   806: invokevirtual 85	javax/vecmath/Vector3f:scale	(FLjavax/vecmath/Tuple3f;)V
/*     */       //   809: aload 7
/*     */       //   811: aload 5
/*     */       //   813: getfield 310	javax/vecmath/Vector3f:y	F
/*     */       //   816: aload_0
/*     */       //   817: getfield 46	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:features	[[Ljavax/vecmath/Vector3f;
/*     */       //   820: iconst_1
/*     */       //   821: aaload
/*     */       //   822: iconst_1
/*     */       //   823: aaload
/*     */       //   824: invokevirtual 85	javax/vecmath/Vector3f:scale	(FLjavax/vecmath/Tuple3f;)V
/*     */       //   827: aload 8
/*     */       //   829: aload 5
/*     */       //   831: getfield 313	javax/vecmath/Vector3f:z	F
/*     */       //   834: aload_0
/*     */       //   835: getfield 46	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:features	[[Ljavax/vecmath/Vector3f;
/*     */       //   838: iconst_1
/*     */       //   839: aaload
/*     */       //   840: iconst_2
/*     */       //   841: aaload
/*     */       //   842: invokevirtual 85	javax/vecmath/Vector3f:scale	(FLjavax/vecmath/Tuple3f;)V
/*     */       //   845: aload_0
/*     */       //   846: getfield 51	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:nearest	[Ljavax/vecmath/Vector3f;
/*     */       //   849: iconst_1
/*     */       //   850: aaload
/*     */       //   851: aload 6
/*     */       //   853: aload 7
/*     */       //   855: aload 8
/*     */       //   857: invokestatic 319	com/bulletphysics/linearmath/VectorUtil:add	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;)V
/*     */       //   860: goto +8 -> 868
/*     */       //   863: aload_0
/*     */       //   864: iconst_1
/*     */       //   865: putfield 252	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:failed	Z
/*     */       //   868: aload_0
/*     */       //   869: getfield 248	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:depth	F
/*     */       //   872: fstore 5
/*     */       //   874: aload_0
/*     */       //   875: getfield 40	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:this$0	Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver;
/*     */       //   878: invokevirtual 284	com/bulletphysics/collision/narrowphase/GjkEpaSolver:popStack	()V
/*     */       //   881: fload 5
/*     */       //   883: aload 15
/*     */       //   885: invokevirtual 130	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
/*     */       //   888: freturn
/*     */       //   889: astore 14
/*     */       //   891: aload_0
/*     */       //   892: getfield 40	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:this$0	Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver;
/*     */       //   895: invokevirtual 284	com/bulletphysics/collision/narrowphase/GjkEpaSolver:popStack	()V
/*     */       //   898: aload 14
/*     */       //   900: athrow
/*     */       //   901: aload 15
/*     */       //   903: invokevirtual 130	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
/*     */       //   906: athrow
/*     */       //
/*     */       // Exception table:
/*     */       //   from	to	target	type
/*     */       //   17	374	889	finally
/*     */       //   389	874	889	finally
/*     */       //   889	891	889	finally
/*     */       //   5	901	901	finally
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class Face
/*     */   {
/* 480 */     public final GjkEpaSolver.Mkv[] v = new GjkEpaSolver.Mkv[3];
/* 481 */     public final Face[] f = new Face[3];
/* 482 */     public final int[] e = new int[3];
/* 483 */     public final Vector3f n = new Vector3f();
/*     */     public float d;
/*     */     public int mark;
/*     */     public Face prev;
/*     */     public Face next;
/*     */   }
/*     */ 
/*     */   protected class GJK
/*     */   {
/* 122 */     public final GjkEpaSolver.He[] table = new GjkEpaSolver.He[64];
/* 123 */     public final Matrix3f[] wrotations = { new Matrix3f(), new Matrix3f() };
/* 124 */     public final Vector3f[] positions = { new Vector3f(), new Vector3f() };
/* 125 */     public final ConvexShape[] shapes = new ConvexShape[2];
/* 126 */     public final GjkEpaSolver.Mkv[] simplex = new GjkEpaSolver.Mkv[5];
/* 127 */     public final Vector3f ray = new Vector3f();
/*     */     public int order;
/*     */     public int iterations;
/*     */     public float margin;
/*     */     public boolean failed;
/*     */ 
/*     */     public GJK()
/*     */     {
/* 134 */       for (int i = 0; i < this.simplex.length; i++) this.simplex[i] = new GjkEpaSolver.Mkv();
/*     */     }
/*     */ 
/*     */     public GJK(Matrix3f wrot0, Vector3f pos0, ConvexShape shape0, Matrix3f wrot1, Vector3f pos1, ConvexShape shape1)
/*     */     {
/* 143 */       this(wrot0, pos0, shape0, wrot1, pos1, shape1, 0.0F);
/*     */     }
/*     */ 
/*     */     public GJK(Matrix3f wrot0, Vector3f pos0, ConvexShape shape0, Matrix3f wrot1, Vector3f pos1, ConvexShape shape1, float pmargin)
/*     */     {
/* 134 */       for (int i = 0; i < this.simplex.length; i++) this.simplex[i] = new GjkEpaSolver.Mkv();
/*     */ 
/* 150 */       init(wrot0, pos0, shape0, wrot1, pos1, shape1, pmargin);
/*     */     }
/*     */ 
/*     */     public void init(Matrix3f wrot0, Vector3f pos0, ConvexShape shape0, Matrix3f wrot1, Vector3f pos1, ConvexShape shape1, float pmargin)
/*     */     {
/* 157 */       GjkEpaSolver.this.pushStack();
/* 158 */       this.wrotations[0].set(wrot0);
/* 159 */       this.positions[0].set(pos0);
/* 160 */       this.shapes[0] = shape0;
/* 161 */       this.wrotations[1].set(wrot1);
/* 162 */       this.positions[1].set(pos1);
/* 163 */       this.shapes[1] = shape1;
/*     */ 
/* 166 */       this.margin = pmargin;
/* 167 */       this.failed = false;
/*     */     }
/*     */ 
/*     */     public void destroy() {
/* 171 */       GjkEpaSolver.this.popStack();
/*     */     }
/*     */ 
/*     */     public int Hash(Vector3f v)
/*     */     {
/* 176 */       int h = (int)(v.x * 15461.0F) ^ (int)(v.y * 83003.0F) ^ (int)(v.z * 15473.0F);
/* 177 */       return h * 169639 & 0x3F;
/*     */     }
/*     */ 
/*     */     public Vector3f LocalSupport(Vector3f arg1, int arg2, Vector3f arg3) {
/* 181 */       .Stack localStack = .Stack.get();
/*     */       try { localStack.push$javax$vecmath$Vector3f(); Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 182 */         MatrixUtil.transposeTransform(tmp, d, this.wrotations[i]);
/*     */ 
/* 184 */         this.shapes[i].localGetSupportingVertex(tmp, out);
/* 185 */         this.wrotations[i].transform(out);
/* 186 */         out.add(this.positions[i]);
/*     */ 
/* 188 */         return out; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */     }
/*     */ 
/*     */     public void Support(Vector3f arg1, GjkEpaSolver.Mkv arg2) {
/* 192 */       .Stack localStack = .Stack.get();
/*     */       try { localStack.push$javax$vecmath$Vector3f(); v.r.set(d);
/*     */ 
/* 194 */         Vector3f tmp1 = LocalSupport(d, 0, localStack.get$javax$vecmath$Vector3f());
/*     */ 
/* 196 */         Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 197 */         tmp.set(d);
/* 198 */         tmp.negate();
/* 199 */         Vector3f tmp2 = LocalSupport(tmp, 1, localStack.get$javax$vecmath$Vector3f());
/*     */ 
/* 201 */         v.w.sub(tmp1, tmp2);
/* 202 */         v.w.scaleAdd(this.margin, d, v.w);
/*     */         return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */     }
/*     */     public boolean FetchSupport() {
/* 206 */       int h = Hash(this.ray);
/* 207 */       GjkEpaSolver.He e = this.table[h];
/* 208 */       while (e != null) {
/* 209 */         if (e.v.equals(this.ray)) {
/* 210 */           this.order -= 1;
/* 211 */           return false;
/*     */         }
/*     */ 
/* 214 */         e = e.n;
/*     */       }
/*     */ 
/* 219 */       e = (GjkEpaSolver.He)GjkEpaSolver.this.stackHe.get();
/* 220 */       e.v.set(this.ray);
/* 221 */       e.n = this.table[h];
/* 222 */       this.table[h] = e;
/* 223 */       Support(this.ray, this.simplex[(++this.order)]);
/* 224 */       return this.ray.dot(this.simplex[this.order].w) > 0.0F;
/*     */     }
/*     */ 
/*     */     public boolean SolveSimplex2(Vector3f arg1, Vector3f arg2) {
/* 228 */       .Stack localStack = .Stack.get();
/*     */       try { localStack.push$javax$vecmath$Vector3f(); if (ab.dot(ao) >= 0.0F) {
/* 229 */           Vector3f cabo = localStack.get$javax$vecmath$Vector3f();
/* 230 */           cabo.cross(ab, ao);
/* 231 */           if (cabo.lengthSquared() > 9.999999E-009F) {
/* 232 */             this.ray.cross(cabo, ab);
/*     */           }
/*     */           else
/* 235 */             return true;
/*     */         }
/*     */         else
/*     */         {
/* 239 */           this.order = 0;
/* 240 */           this.simplex[0].set(this.simplex[1]);
/* 241 */           this.ray.set(ao);
/*     */         }
/* 243 */         return false; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */     }
/*     */ 
/*     */     public boolean SolveSimplex3(Vector3f arg1, Vector3f arg2, Vector3f arg3)
/*     */     {
/* 248 */       .Stack localStack = .Stack.get();
/*     */       try { localStack.push$javax$vecmath$Vector3f(); Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 249 */         tmp.cross(ab, ac);
/* 250 */         return SolveSimplex3a(ao, ab, ac, tmp); } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */     }
/*     */ 
/*     */     public boolean SolveSimplex3a(Vector3f arg1, Vector3f arg2, Vector3f arg3, Vector3f arg4)
/*     */     {
/* 256 */       .Stack localStack = .Stack.get();
/*     */       try { localStack.push$javax$vecmath$Vector3f(); Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 257 */         tmp.cross(cabc, ab);
/*     */ 
/* 259 */         Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/* 260 */         tmp2.cross(cabc, ac);
/*     */ 
/* 262 */         if (tmp.dot(ao) < -1.0E-004F) {
/* 263 */           this.order = 1;
/* 264 */           this.simplex[0].set(this.simplex[1]);
/* 265 */           this.simplex[1].set(this.simplex[2]);
/* 266 */           return SolveSimplex2(ao, ab);
/*     */         }
/* 268 */         if (tmp2.dot(ao) > 1.0E-004F) {
/* 269 */           this.order = 1;
/* 270 */           this.simplex[1].set(this.simplex[2]);
/* 271 */           return SolveSimplex2(ao, ac);
/*     */         }
/*     */ 
/* 274 */         float d = cabc.dot(ao);
/* 275 */         if (Math.abs(d) > 1.0E-004F) {
/* 276 */           if (d > 0.0F) {
/* 277 */             this.ray.set(cabc);
/*     */           }
/*     */           else {
/* 280 */             this.ray.negate(cabc);
/*     */ 
/* 282 */             GjkEpaSolver.Mkv swapTmp = new GjkEpaSolver.Mkv();
/* 283 */             swapTmp.set(this.simplex[0]);
/* 284 */             this.simplex[0].set(this.simplex[1]);
/* 285 */             this.simplex[1].set(swapTmp);
/*     */           }
/* 287 */           return false;
/*     */         }
/*     */ 
/* 290 */         return true; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */     }
/*     */ 
/*     */     public boolean SolveSimplex4(Vector3f arg1, Vector3f arg2, Vector3f arg3, Vector3f arg4)
/*     */     {
/* 298 */       .Stack localStack = .Stack.get();
/*     */       try { localStack.push$javax$vecmath$Vector3f(); Vector3f crs = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 300 */         Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 301 */         tmp.cross(ab, ac);
/*     */ 
/* 303 */         Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/* 304 */         tmp2.cross(ac, ad);
/*     */ 
/* 306 */         Vector3f tmp3 = localStack.get$javax$vecmath$Vector3f();
/* 307 */         tmp3.cross(ad, ab);
/*     */ 
/* 309 */         if (tmp.dot(ao) > 1.0E-004F) {
/* 310 */           crs.set(tmp);
/* 311 */           this.order = 2;
/* 312 */           this.simplex[0].set(this.simplex[1]);
/* 313 */           this.simplex[1].set(this.simplex[2]);
/* 314 */           this.simplex[2].set(this.simplex[3]);
/* 315 */           return SolveSimplex3a(ao, ab, ac, crs);
/*     */         }
/* 317 */         if (tmp2.dot(ao) > 1.0E-004F) {
/* 318 */           crs.set(tmp2);
/* 319 */           this.order = 2;
/* 320 */           this.simplex[2].set(this.simplex[3]);
/* 321 */           return SolveSimplex3a(ao, ac, ad, crs);
/*     */         }
/* 323 */         if (tmp3.dot(ao) > 1.0E-004F) {
/* 324 */           crs.set(tmp3);
/* 325 */           this.order = 2;
/* 326 */           this.simplex[1].set(this.simplex[0]);
/* 327 */           this.simplex[0].set(this.simplex[2]);
/* 328 */           this.simplex[2].set(this.simplex[3]);
/* 329 */           return SolveSimplex3a(ao, ad, ab, crs);
/*     */         }
/*     */ 
/* 332 */         return true; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */     }
/*     */ 
/*     */     public boolean SearchOrigin()
/*     */     {
/* 337 */       .Stack localStack = .Stack.get();
/*     */       try { localStack.push$javax$vecmath$Vector3f(); Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 338 */         tmp.set(1.0F, 0.0F, 0.0F);
/* 339 */         return SearchOrigin(tmp); } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */     }
/*     */ 
/*     */     public boolean SearchOrigin(Vector3f arg1) {
/* 343 */       .Stack localStack = .Stack.get();
/*     */       try { localStack.push$javax$vecmath$Vector3f(); Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/* 344 */         Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/* 345 */         Vector3f tmp3 = localStack.get$javax$vecmath$Vector3f();
/* 346 */         Vector3f tmp4 = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 348 */         this.iterations = 0;
/* 349 */         this.order = -1;
/* 350 */         this.failed = false;
/* 351 */         this.ray.set(initray);
/* 352 */         this.ray.normalize();
/*     */ 
/* 354 */         Arrays.fill(this.table, null);
/*     */ 
/* 356 */         FetchSupport();
/* 357 */         this.ray.negate(this.simplex[0].w);
/* 358 */         for (; this.iterations < 128; this.iterations += 1) {
/* 359 */           float rl = this.ray.length();
/* 360 */           this.ray.scale(1.0F / (rl > 0.0F ? rl : 1.0F));
/* 361 */           if (FetchSupport()) {
/* 362 */             boolean found = false;
/* 363 */             switch (this.order) {
/*     */             case 1:
/* 365 */               tmp1.negate(this.simplex[1].w);
/* 366 */               tmp2.sub(this.simplex[0].w, this.simplex[1].w);
/* 367 */               found = SolveSimplex2(tmp1, tmp2);
/* 368 */               break;
/*     */             case 2:
/* 371 */               tmp1.negate(this.simplex[2].w);
/* 372 */               tmp2.sub(this.simplex[1].w, this.simplex[2].w);
/* 373 */               tmp3.sub(this.simplex[0].w, this.simplex[2].w);
/* 374 */               found = SolveSimplex3(tmp1, tmp2, tmp3);
/* 375 */               break;
/*     */             case 3:
/* 378 */               tmp1.negate(this.simplex[3].w);
/* 379 */               tmp2.sub(this.simplex[2].w, this.simplex[3].w);
/* 380 */               tmp3.sub(this.simplex[1].w, this.simplex[3].w);
/* 381 */               tmp4.sub(this.simplex[0].w, this.simplex[3].w);
/* 382 */               found = SolveSimplex4(tmp1, tmp2, tmp3, tmp4);
/*     */             }
/*     */ 
/* 386 */             if (found)
/* 387 */               return true;
/*     */           }
/*     */           else
/*     */           {
/* 391 */             return false;
/*     */           }
/*     */         }
/* 394 */         this.failed = true;
/* 395 */         return false; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */     }
/*     */ 
/*     */     public boolean EncloseOrigin() {
/* 399 */       .Stack localStack = .Stack.get();
/*     */       try
/*     */       {
/*     */         .Stack tmp7_5 = localStack; tmp7_5.push$javax$vecmath$Vector3f();
/*     */         .Stack tmp11_7 = tmp7_5; tmp11_7.push$javax$vecmath$Quat4f(); tmp11_7.push$javax$vecmath$Matrix3f(); Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 400 */         Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/* 401 */         Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 403 */         switch (this.order)
/*     */         {
/*     */         case 0:
/* 406 */           break;
/*     */         case 1:
/* 409 */           Vector3f ab = localStack.get$javax$vecmath$Vector3f();
/* 410 */           ab.sub(this.simplex[1].w, this.simplex[0].w);
/*     */ 
/* 412 */           Vector3f[] b = { localStack.get$javax$vecmath$Vector3f(), localStack.get$javax$vecmath$Vector3f(), localStack.get$javax$vecmath$Vector3f() };
/* 413 */           b[0].set(1.0F, 0.0F, 0.0F);
/* 414 */           b[1].set(0.0F, 1.0F, 0.0F);
/* 415 */           b[2].set(0.0F, 0.0F, 1.0F);
/*     */ 
/* 417 */           b[0].cross(ab, b[0]);
/* 418 */           b[1].cross(ab, b[1]);
/* 419 */           b[2].cross(ab, b[2]);
/*     */ 
/* 421 */           float[] m = { b[0].lengthSquared(), b[1].lengthSquared(), b[2].lengthSquared() };
/*     */ 
/* 423 */           Quat4f tmpQuat = localStack.get$javax$vecmath$Quat4f();
/* 424 */           tmp.normalize(ab);
/* 425 */           QuaternionUtil.setRotation(tmpQuat, tmp, 2.094395F);
/*     */ 
/* 427 */           Matrix3f r = localStack.get$javax$vecmath$Matrix3f();
/* 428 */           MatrixUtil.setRotation(r, tmpQuat);
/*     */ 
/* 430 */           Vector3f w = localStack.get$javax$vecmath$Vector3f();
/* 431 */           w.set(b[2]);
/*     */ 
/* 433 */           tmp.normalize(w);
/* 434 */           Support(tmp, this.simplex[4]); r.transform(w);
/* 435 */           tmp.normalize(w);
/* 436 */           Support(tmp, this.simplex[2]); r.transform(w);
/* 437 */           tmp.normalize(w);
/* 438 */           Support(tmp, this.simplex[3]); r.transform(w);
/* 439 */           this.order = 4;
/* 440 */           return true;
/*     */         case 2:
/* 444 */           tmp1.sub(this.simplex[1].w, this.simplex[0].w);
/* 445 */           tmp2.sub(this.simplex[2].w, this.simplex[0].w);
/* 446 */           Vector3f n = localStack.get$javax$vecmath$Vector3f();
/* 447 */           n.cross(tmp1, tmp2);
/* 448 */           n.normalize();
/*     */ 
/* 450 */           Support(n, this.simplex[3]);
/*     */ 
/* 452 */           tmp.negate(n);
/* 453 */           Support(tmp, this.simplex[4]);
/* 454 */           this.order = 4;
/* 455 */           return true;
/*     */         case 3:
/* 459 */           return true;
/*     */         case 4:
/* 462 */           return true;
/*     */         }
/* 464 */         return false;
/*     */       }
/*     */       finally
/*     */       {
/* 464 */         .Stack tmp594_592 = localStack; tmp594_592.pop$javax$vecmath$Vector3f();
/*     */         .Stack tmp598_594 = tmp594_592; tmp598_594.pop$javax$vecmath$Quat4f(); tmp598_594.pop$javax$vecmath$Matrix3f(); } throw finally;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class He
/*     */   {
/* 113 */     public final Vector3f v = new Vector3f();
/*     */     public He n;
/*     */   }
/*     */ 
/*     */   public static class Mkv
/*     */   {
/* 103 */     public final Vector3f w = new Vector3f();
/* 104 */     public final Vector3f r = new Vector3f();
/*     */ 
/*     */     public void set(Mkv m) {
/* 107 */       this.w.set(m.w);
/* 108 */       this.r.set(m.r);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class Results
/*     */   {
/*     */     public GjkEpaSolver.ResultsStatus status;
/*  79 */     public final Vector3f[] witnesses = { new Vector3f(), new Vector3f() };
/*  80 */     public final Vector3f normal = new Vector3f();
/*     */     public float depth;
/*     */     public int epa_iterations;
/*     */     public int gjk_iterations;
/*     */   }
/*     */ 
/*     */   public static enum ResultsStatus
/*     */   {
/*  71 */     Separated, 
/*  72 */     Penetrating, 
/*  73 */     GJK_Failed, 
/*  74 */     EPA_Failed;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.narrowphase.GjkEpaSolver
 * JD-Core Version:    0.6.2
 */