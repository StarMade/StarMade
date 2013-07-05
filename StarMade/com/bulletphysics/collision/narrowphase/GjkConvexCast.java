/*     */ package com.bulletphysics.collision.narrowphase;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.collision.shapes.ConvexShape;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.linearmath.VectorUtil;
/*     */ import com.bulletphysics.util.ObjectPool;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class GjkConvexCast extends ConvexCast
/*     */ {
/*  42 */   protected final ObjectPool<DiscreteCollisionDetectorInterface.ClosestPointInput> pointInputsPool = ObjectPool.get(DiscreteCollisionDetectorInterface.ClosestPointInput.class);
/*     */   private static final int MAX_ITERATIONS = 32;
/*     */   private SimplexSolverInterface simplexSolver;
/*     */   private ConvexShape convexA;
/*     */   private ConvexShape convexB;
/*  54 */   private GjkPairDetector gjk = new GjkPairDetector();
/*     */ 
/*     */   public GjkConvexCast(ConvexShape convexA, ConvexShape convexB, SimplexSolverInterface simplexSolver) {
/*  57 */     this.simplexSolver = simplexSolver;
/*  58 */     this.convexA = convexA;
/*  59 */     this.convexB = convexB;
/*     */   }
/*     */ 
/*     */   public boolean calcTimeOfImpact(Transform arg1, Transform arg2, Transform arg3, Transform arg4, ConvexCast.CastResult arg5)
/*     */   {
/*     */     // Byte code:
/*     */     //   0: invokestatic 62	com/bulletphysics/$Stack:get	()Lcom/bulletphysics/$Stack;
/*     */     //   3: astore 26
/*     */     //   5: aload 26
/*     */     //   7: dup
/*     */     //   8: invokevirtual 65	com/bulletphysics/$Stack:push$com$bulletphysics$linearmath$Transform	()V
/*     */     //   11: invokevirtual 68	com/bulletphysics/$Stack:push$javax$vecmath$Vector3f	()V
/*     */     //   14: aload_0
/*     */     //   15: getfield 49	com/bulletphysics/collision/narrowphase/GjkConvexCast:simplexSolver	Lcom/bulletphysics/collision/narrowphase/SimplexSolverInterface;
/*     */     //   18: invokevirtual 73	com/bulletphysics/collision/narrowphase/SimplexSolverInterface:reset	()V
/*     */     //   21: aload 26
/*     */     //   23: invokevirtual 77	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/*     */     //   26: astore 6
/*     */     //   28: aload 26
/*     */     //   30: invokevirtual 77	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/*     */     //   33: astore 7
/*     */     //   35: aload 6
/*     */     //   37: aload_2
/*     */     //   38: getfield 83	com/bulletphysics/linearmath/Transform:origin	Ljavax/vecmath/Vector3f;
/*     */     //   41: aload_1
/*     */     //   42: getfield 83	com/bulletphysics/linearmath/Transform:origin	Ljavax/vecmath/Vector3f;
/*     */     //   45: invokevirtual 89	javax/vecmath/Vector3f:sub	(Ljavax/vecmath/Tuple3f;Ljavax/vecmath/Tuple3f;)V
/*     */     //   48: aload 7
/*     */     //   50: aload 4
/*     */     //   52: getfield 83	com/bulletphysics/linearmath/Transform:origin	Ljavax/vecmath/Vector3f;
/*     */     //   55: aload_3
/*     */     //   56: getfield 83	com/bulletphysics/linearmath/Transform:origin	Ljavax/vecmath/Vector3f;
/*     */     //   59: invokevirtual 89	javax/vecmath/Vector3f:sub	(Ljavax/vecmath/Tuple3f;Ljavax/vecmath/Tuple3f;)V
/*     */     //   62: ldc 90
/*     */     //   64: fstore 8
/*     */     //   66: fconst_0
/*     */     //   67: fstore 9
/*     */     //   69: aload 26
/*     */     //   71: invokevirtual 77	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/*     */     //   74: astore 10
/*     */     //   76: aload 10
/*     */     //   78: fconst_1
/*     */     //   79: fconst_0
/*     */     //   80: fconst_0
/*     */     //   81: invokevirtual 94	javax/vecmath/Vector3f:set	(FFF)V
/*     */     //   84: bipush 32
/*     */     //   86: istore 11
/*     */     //   88: aload 26
/*     */     //   90: invokevirtual 77	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/*     */     //   93: astore 12
/*     */     //   95: aload 12
/*     */     //   97: fconst_0
/*     */     //   98: fconst_0
/*     */     //   99: fconst_0
/*     */     //   100: invokevirtual 94	javax/vecmath/Vector3f:set	(FFF)V
/*     */     //   103: iconst_0
/*     */     //   104: istore 13
/*     */     //   106: aload 26
/*     */     //   108: invokevirtual 77	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/*     */     //   111: astore 14
/*     */     //   113: aload 26
/*     */     //   115: invokevirtual 77	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/*     */     //   118: astore 15
/*     */     //   120: aload 15
/*     */     //   122: aload 6
/*     */     //   124: aload 7
/*     */     //   126: invokevirtual 89	javax/vecmath/Vector3f:sub	(Ljavax/vecmath/Tuple3f;Ljavax/vecmath/Tuple3f;)V
/*     */     //   129: fload 9
/*     */     //   131: fstore 16
/*     */     //   133: iconst_0
/*     */     //   134: istore 17
/*     */     //   136: aload 26
/*     */     //   138: invokevirtual 98	com/bulletphysics/$Stack:get$com$bulletphysics$linearmath$Transform	()Lcom/bulletphysics/linearmath/Transform;
/*     */     //   141: astore 18
/*     */     //   143: aload 18
/*     */     //   145: invokevirtual 101	com/bulletphysics/linearmath/Transform:setIdentity	()V
/*     */     //   148: new 103	com/bulletphysics/collision/narrowphase/PointCollector
/*     */     //   151: dup
/*     */     //   152: invokespecial 104	com/bulletphysics/collision/narrowphase/PointCollector:<init>	()V
/*     */     //   155: astore 19
/*     */     //   157: aload_0
/*     */     //   158: getfield 47	com/bulletphysics/collision/narrowphase/GjkConvexCast:gjk	Lcom/bulletphysics/collision/narrowphase/GjkPairDetector;
/*     */     //   161: aload_0
/*     */     //   162: getfield 51	com/bulletphysics/collision/narrowphase/GjkConvexCast:convexA	Lcom/bulletphysics/collision/shapes/ConvexShape;
/*     */     //   165: aload_0
/*     */     //   166: getfield 53	com/bulletphysics/collision/narrowphase/GjkConvexCast:convexB	Lcom/bulletphysics/collision/shapes/ConvexShape;
/*     */     //   169: aload_0
/*     */     //   170: getfield 49	com/bulletphysics/collision/narrowphase/GjkConvexCast:simplexSolver	Lcom/bulletphysics/collision/narrowphase/SimplexSolverInterface;
/*     */     //   173: aconst_null
/*     */     //   174: invokevirtual 108	com/bulletphysics/collision/narrowphase/GjkPairDetector:init	(Lcom/bulletphysics/collision/shapes/ConvexShape;Lcom/bulletphysics/collision/shapes/ConvexShape;Lcom/bulletphysics/collision/narrowphase/SimplexSolverInterface;Lcom/bulletphysics/collision/narrowphase/ConvexPenetrationDepthSolver;)V
/*     */     //   177: aload_0
/*     */     //   178: getfield 42	com/bulletphysics/collision/narrowphase/GjkConvexCast:pointInputsPool	Lcom/bulletphysics/util/ObjectPool;
/*     */     //   181: invokevirtual 111	com/bulletphysics/util/ObjectPool:get	()Ljava/lang/Object;
/*     */     //   184: checkcast 7	com/bulletphysics/collision/narrowphase/DiscreteCollisionDetectorInterface$ClosestPointInput
/*     */     //   187: astore 20
/*     */     //   189: aload 20
/*     */     //   191: invokevirtual 113	com/bulletphysics/collision/narrowphase/DiscreteCollisionDetectorInterface$ClosestPointInput:init	()V
/*     */     //   194: aload 20
/*     */     //   196: getfield 117	com/bulletphysics/collision/narrowphase/DiscreteCollisionDetectorInterface$ClosestPointInput:transformA	Lcom/bulletphysics/linearmath/Transform;
/*     */     //   199: aload_1
/*     */     //   200: invokevirtual 120	com/bulletphysics/linearmath/Transform:set	(Lcom/bulletphysics/linearmath/Transform;)V
/*     */     //   203: aload 20
/*     */     //   205: getfield 123	com/bulletphysics/collision/narrowphase/DiscreteCollisionDetectorInterface$ClosestPointInput:transformB	Lcom/bulletphysics/linearmath/Transform;
/*     */     //   208: aload_3
/*     */     //   209: invokevirtual 120	com/bulletphysics/linearmath/Transform:set	(Lcom/bulletphysics/linearmath/Transform;)V
/*     */     //   212: aload_0
/*     */     //   213: getfield 47	com/bulletphysics/collision/narrowphase/GjkConvexCast:gjk	Lcom/bulletphysics/collision/narrowphase/GjkPairDetector;
/*     */     //   216: aload 20
/*     */     //   218: aload 19
/*     */     //   220: aconst_null
/*     */     //   221: invokevirtual 127	com/bulletphysics/collision/narrowphase/GjkPairDetector:getClosestPoints	(Lcom/bulletphysics/collision/narrowphase/DiscreteCollisionDetectorInterface$ClosestPointInput;Lcom/bulletphysics/collision/narrowphase/DiscreteCollisionDetectorInterface$Result;Lcom/bulletphysics/linearmath/IDebugDraw;)V
/*     */     //   224: aload 19
/*     */     //   226: getfield 131	com/bulletphysics/collision/narrowphase/PointCollector:hasResult	Z
/*     */     //   229: istore 13
/*     */     //   231: aload 14
/*     */     //   233: aload 19
/*     */     //   235: getfield 134	com/bulletphysics/collision/narrowphase/PointCollector:pointInWorld	Ljavax/vecmath/Vector3f;
/*     */     //   238: invokevirtual 137	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/*     */     //   241: iload 13
/*     */     //   243: ifeq +479 -> 722
/*     */     //   246: aload 19
/*     */     //   248: getfield 141	com/bulletphysics/collision/narrowphase/PointCollector:distance	F
/*     */     //   251: fstore 21
/*     */     //   253: aload 12
/*     */     //   255: aload 19
/*     */     //   257: getfield 144	com/bulletphysics/collision/narrowphase/PointCollector:normalOnBInWorld	Ljavax/vecmath/Vector3f;
/*     */     //   260: invokevirtual 137	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/*     */     //   263: fload 21
/*     */     //   265: fload 8
/*     */     //   267: fcmpl
/*     */     //   268: ifle +362 -> 630
/*     */     //   271: iinc 17 1
/*     */     //   274: iload 17
/*     */     //   276: iload 11
/*     */     //   278: if_icmple +27 -> 305
/*     */     //   281: iconst_0
/*     */     //   282: istore 22
/*     */     //   284: aload_0
/*     */     //   285: getfield 42	com/bulletphysics/collision/narrowphase/GjkConvexCast:pointInputsPool	Lcom/bulletphysics/util/ObjectPool;
/*     */     //   288: aload 20
/*     */     //   290: invokevirtual 148	com/bulletphysics/util/ObjectPool:release	(Ljava/lang/Object;)V
/*     */     //   293: iload 22
/*     */     //   295: aload 26
/*     */     //   297: dup
/*     */     //   298: invokevirtual 151	com/bulletphysics/$Stack:pop$com$bulletphysics$linearmath$Transform	()V
/*     */     //   301: invokevirtual 154	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
/*     */     //   304: ireturn
/*     */     //   305: fconst_0
/*     */     //   306: fstore 22
/*     */     //   308: aload 15
/*     */     //   310: aload 12
/*     */     //   312: invokevirtual 158	javax/vecmath/Vector3f:dot	(Ljavax/vecmath/Vector3f;)F
/*     */     //   315: fstore 23
/*     */     //   317: fload 21
/*     */     //   319: fload 23
/*     */     //   321: fdiv
/*     */     //   322: fstore 22
/*     */     //   324: fload 9
/*     */     //   326: fload 22
/*     */     //   328: fsub
/*     */     //   329: fstore 9
/*     */     //   331: fload 9
/*     */     //   333: fconst_1
/*     */     //   334: fcmpl
/*     */     //   335: ifle +27 -> 362
/*     */     //   338: iconst_0
/*     */     //   339: istore 24
/*     */     //   341: aload_0
/*     */     //   342: getfield 42	com/bulletphysics/collision/narrowphase/GjkConvexCast:pointInputsPool	Lcom/bulletphysics/util/ObjectPool;
/*     */     //   345: aload 20
/*     */     //   347: invokevirtual 148	com/bulletphysics/util/ObjectPool:release	(Ljava/lang/Object;)V
/*     */     //   350: iload 24
/*     */     //   352: aload 26
/*     */     //   354: dup
/*     */     //   355: invokevirtual 151	com/bulletphysics/$Stack:pop$com$bulletphysics$linearmath$Transform	()V
/*     */     //   358: invokevirtual 154	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
/*     */     //   361: ireturn
/*     */     //   362: fload 9
/*     */     //   364: fconst_0
/*     */     //   365: fcmpg
/*     */     //   366: ifge +27 -> 393
/*     */     //   369: iconst_0
/*     */     //   370: istore 24
/*     */     //   372: aload_0
/*     */     //   373: getfield 42	com/bulletphysics/collision/narrowphase/GjkConvexCast:pointInputsPool	Lcom/bulletphysics/util/ObjectPool;
/*     */     //   376: aload 20
/*     */     //   378: invokevirtual 148	com/bulletphysics/util/ObjectPool:release	(Ljava/lang/Object;)V
/*     */     //   381: iload 24
/*     */     //   383: aload 26
/*     */     //   385: dup
/*     */     //   386: invokevirtual 151	com/bulletphysics/$Stack:pop$com$bulletphysics$linearmath$Transform	()V
/*     */     //   389: invokevirtual 154	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
/*     */     //   392: ireturn
/*     */     //   393: fload 9
/*     */     //   395: fload 16
/*     */     //   397: fcmpg
/*     */     //   398: ifgt +27 -> 425
/*     */     //   401: iconst_0
/*     */     //   402: istore 24
/*     */     //   404: aload_0
/*     */     //   405: getfield 42	com/bulletphysics/collision/narrowphase/GjkConvexCast:pointInputsPool	Lcom/bulletphysics/util/ObjectPool;
/*     */     //   408: aload 20
/*     */     //   410: invokevirtual 148	com/bulletphysics/util/ObjectPool:release	(Ljava/lang/Object;)V
/*     */     //   413: iload 24
/*     */     //   415: aload 26
/*     */     //   417: dup
/*     */     //   418: invokevirtual 151	com/bulletphysics/$Stack:pop$com$bulletphysics$linearmath$Transform	()V
/*     */     //   421: invokevirtual 154	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
/*     */     //   424: ireturn
/*     */     //   425: fload 9
/*     */     //   427: fstore 16
/*     */     //   429: aload 5
/*     */     //   431: fload 9
/*     */     //   433: invokevirtual 162	com/bulletphysics/collision/narrowphase/ConvexCast$CastResult:debugDraw	(F)V
/*     */     //   436: aload 20
/*     */     //   438: getfield 117	com/bulletphysics/collision/narrowphase/DiscreteCollisionDetectorInterface$ClosestPointInput:transformA	Lcom/bulletphysics/linearmath/Transform;
/*     */     //   441: getfield 83	com/bulletphysics/linearmath/Transform:origin	Ljavax/vecmath/Vector3f;
/*     */     //   444: aload_1
/*     */     //   445: getfield 83	com/bulletphysics/linearmath/Transform:origin	Ljavax/vecmath/Vector3f;
/*     */     //   448: aload_2
/*     */     //   449: getfield 83	com/bulletphysics/linearmath/Transform:origin	Ljavax/vecmath/Vector3f;
/*     */     //   452: fload 9
/*     */     //   454: invokestatic 168	com/bulletphysics/linearmath/VectorUtil:setInterpolate3	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;F)V
/*     */     //   457: aload 20
/*     */     //   459: getfield 123	com/bulletphysics/collision/narrowphase/DiscreteCollisionDetectorInterface$ClosestPointInput:transformB	Lcom/bulletphysics/linearmath/Transform;
/*     */     //   462: getfield 83	com/bulletphysics/linearmath/Transform:origin	Ljavax/vecmath/Vector3f;
/*     */     //   465: aload_3
/*     */     //   466: getfield 83	com/bulletphysics/linearmath/Transform:origin	Ljavax/vecmath/Vector3f;
/*     */     //   469: aload 4
/*     */     //   471: getfield 83	com/bulletphysics/linearmath/Transform:origin	Ljavax/vecmath/Vector3f;
/*     */     //   474: fload 9
/*     */     //   476: invokestatic 168	com/bulletphysics/linearmath/VectorUtil:setInterpolate3	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;F)V
/*     */     //   479: aload_0
/*     */     //   480: getfield 47	com/bulletphysics/collision/narrowphase/GjkConvexCast:gjk	Lcom/bulletphysics/collision/narrowphase/GjkPairDetector;
/*     */     //   483: aload 20
/*     */     //   485: aload 19
/*     */     //   487: aconst_null
/*     */     //   488: invokevirtual 127	com/bulletphysics/collision/narrowphase/GjkPairDetector:getClosestPoints	(Lcom/bulletphysics/collision/narrowphase/DiscreteCollisionDetectorInterface$ClosestPointInput;Lcom/bulletphysics/collision/narrowphase/DiscreteCollisionDetectorInterface$Result;Lcom/bulletphysics/linearmath/IDebugDraw;)V
/*     */     //   491: aload 19
/*     */     //   493: getfield 131	com/bulletphysics/collision/narrowphase/PointCollector:hasResult	Z
/*     */     //   496: ifeq +107 -> 603
/*     */     //   499: aload 19
/*     */     //   501: getfield 141	com/bulletphysics/collision/narrowphase/PointCollector:distance	F
/*     */     //   504: fconst_0
/*     */     //   505: fcmpg
/*     */     //   506: ifge +67 -> 573
/*     */     //   509: aload 5
/*     */     //   511: fload 16
/*     */     //   513: putfield 171	com/bulletphysics/collision/narrowphase/ConvexCast$CastResult:fraction	F
/*     */     //   516: aload 12
/*     */     //   518: aload 19
/*     */     //   520: getfield 144	com/bulletphysics/collision/narrowphase/PointCollector:normalOnBInWorld	Ljavax/vecmath/Vector3f;
/*     */     //   523: invokevirtual 137	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/*     */     //   526: aload 5
/*     */     //   528: getfield 174	com/bulletphysics/collision/narrowphase/ConvexCast$CastResult:normal	Ljavax/vecmath/Vector3f;
/*     */     //   531: aload 12
/*     */     //   533: invokevirtual 137	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/*     */     //   536: aload 5
/*     */     //   538: getfield 177	com/bulletphysics/collision/narrowphase/ConvexCast$CastResult:hitPoint	Ljavax/vecmath/Vector3f;
/*     */     //   541: aload 19
/*     */     //   543: getfield 134	com/bulletphysics/collision/narrowphase/PointCollector:pointInWorld	Ljavax/vecmath/Vector3f;
/*     */     //   546: invokevirtual 137	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/*     */     //   549: iconst_1
/*     */     //   550: istore 24
/*     */     //   552: aload_0
/*     */     //   553: getfield 42	com/bulletphysics/collision/narrowphase/GjkConvexCast:pointInputsPool	Lcom/bulletphysics/util/ObjectPool;
/*     */     //   556: aload 20
/*     */     //   558: invokevirtual 148	com/bulletphysics/util/ObjectPool:release	(Ljava/lang/Object;)V
/*     */     //   561: iload 24
/*     */     //   563: aload 26
/*     */     //   565: dup
/*     */     //   566: invokevirtual 151	com/bulletphysics/$Stack:pop$com$bulletphysics$linearmath$Transform	()V
/*     */     //   569: invokevirtual 154	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
/*     */     //   572: ireturn
/*     */     //   573: aload 14
/*     */     //   575: aload 19
/*     */     //   577: getfield 134	com/bulletphysics/collision/narrowphase/PointCollector:pointInWorld	Ljavax/vecmath/Vector3f;
/*     */     //   580: invokevirtual 137	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/*     */     //   583: aload 12
/*     */     //   585: aload 19
/*     */     //   587: getfield 144	com/bulletphysics/collision/narrowphase/PointCollector:normalOnBInWorld	Ljavax/vecmath/Vector3f;
/*     */     //   590: invokevirtual 137	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/*     */     //   593: aload 19
/*     */     //   595: getfield 141	com/bulletphysics/collision/narrowphase/PointCollector:distance	F
/*     */     //   598: fstore 21
/*     */     //   600: goto +27 -> 627
/*     */     //   603: iconst_0
/*     */     //   604: istore 24
/*     */     //   606: aload_0
/*     */     //   607: getfield 42	com/bulletphysics/collision/narrowphase/GjkConvexCast:pointInputsPool	Lcom/bulletphysics/util/ObjectPool;
/*     */     //   610: aload 20
/*     */     //   612: invokevirtual 148	com/bulletphysics/util/ObjectPool:release	(Ljava/lang/Object;)V
/*     */     //   615: iload 24
/*     */     //   617: aload 26
/*     */     //   619: dup
/*     */     //   620: invokevirtual 151	com/bulletphysics/$Stack:pop$com$bulletphysics$linearmath$Transform	()V
/*     */     //   623: invokevirtual 154	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
/*     */     //   626: ireturn
/*     */     //   627: goto -364 -> 263
/*     */     //   630: aload 12
/*     */     //   632: aload 15
/*     */     //   634: invokevirtual 158	javax/vecmath/Vector3f:dot	(Ljavax/vecmath/Vector3f;)F
/*     */     //   637: aload 5
/*     */     //   639: getfield 180	com/bulletphysics/collision/narrowphase/ConvexCast$CastResult:allowedPenetration	F
/*     */     //   642: fneg
/*     */     //   643: fcmpl
/*     */     //   644: iflt +27 -> 671
/*     */     //   647: iconst_0
/*     */     //   648: istore 22
/*     */     //   650: aload_0
/*     */     //   651: getfield 42	com/bulletphysics/collision/narrowphase/GjkConvexCast:pointInputsPool	Lcom/bulletphysics/util/ObjectPool;
/*     */     //   654: aload 20
/*     */     //   656: invokevirtual 148	com/bulletphysics/util/ObjectPool:release	(Ljava/lang/Object;)V
/*     */     //   659: iload 22
/*     */     //   661: aload 26
/*     */     //   663: dup
/*     */     //   664: invokevirtual 151	com/bulletphysics/$Stack:pop$com$bulletphysics$linearmath$Transform	()V
/*     */     //   667: invokevirtual 154	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
/*     */     //   670: ireturn
/*     */     //   671: aload 5
/*     */     //   673: fload 9
/*     */     //   675: putfield 171	com/bulletphysics/collision/narrowphase/ConvexCast$CastResult:fraction	F
/*     */     //   678: aload 5
/*     */     //   680: getfield 174	com/bulletphysics/collision/narrowphase/ConvexCast$CastResult:normal	Ljavax/vecmath/Vector3f;
/*     */     //   683: aload 12
/*     */     //   685: invokevirtual 137	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/*     */     //   688: aload 5
/*     */     //   690: getfield 177	com/bulletphysics/collision/narrowphase/ConvexCast$CastResult:hitPoint	Ljavax/vecmath/Vector3f;
/*     */     //   693: aload 14
/*     */     //   695: invokevirtual 137	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/*     */     //   698: iconst_1
/*     */     //   699: istore 22
/*     */     //   701: aload_0
/*     */     //   702: getfield 42	com/bulletphysics/collision/narrowphase/GjkConvexCast:pointInputsPool	Lcom/bulletphysics/util/ObjectPool;
/*     */     //   705: aload 20
/*     */     //   707: invokevirtual 148	com/bulletphysics/util/ObjectPool:release	(Ljava/lang/Object;)V
/*     */     //   710: iload 22
/*     */     //   712: aload 26
/*     */     //   714: dup
/*     */     //   715: invokevirtual 151	com/bulletphysics/$Stack:pop$com$bulletphysics$linearmath$Transform	()V
/*     */     //   718: invokevirtual 154	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
/*     */     //   721: ireturn
/*     */     //   722: iconst_0
/*     */     //   723: istore 21
/*     */     //   725: aload_0
/*     */     //   726: getfield 42	com/bulletphysics/collision/narrowphase/GjkConvexCast:pointInputsPool	Lcom/bulletphysics/util/ObjectPool;
/*     */     //   729: aload 20
/*     */     //   731: invokevirtual 148	com/bulletphysics/util/ObjectPool:release	(Ljava/lang/Object;)V
/*     */     //   734: iload 21
/*     */     //   736: aload 26
/*     */     //   738: dup
/*     */     //   739: invokevirtual 151	com/bulletphysics/$Stack:pop$com$bulletphysics$linearmath$Transform	()V
/*     */     //   742: invokevirtual 154	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
/*     */     //   745: ireturn
/*     */     //   746: astore 25
/*     */     //   748: aload_0
/*     */     //   749: getfield 42	com/bulletphysics/collision/narrowphase/GjkConvexCast:pointInputsPool	Lcom/bulletphysics/util/ObjectPool;
/*     */     //   752: aload 20
/*     */     //   754: invokevirtual 148	com/bulletphysics/util/ObjectPool:release	(Ljava/lang/Object;)V
/*     */     //   757: aload 25
/*     */     //   759: athrow
/*     */     //   760: aload 26
/*     */     //   762: dup
/*     */     //   763: invokevirtual 151	com/bulletphysics/$Stack:pop$com$bulletphysics$linearmath$Transform	()V
/*     */     //   766: invokevirtual 154	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
/*     */     //   769: athrow
/*     */     //
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   194	284	746	finally
/*     */     //   305	341	746	finally
/*     */     //   362	372	746	finally
/*     */     //   393	404	746	finally
/*     */     //   425	552	746	finally
/*     */     //   573	606	746	finally
/*     */     //   627	650	746	finally
/*     */     //   671	701	746	finally
/*     */     //   722	725	746	finally
/*     */     //   746	748	746	finally
/*     */     //   5	760	760	finally
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.narrowphase.GjkConvexCast
 * JD-Core Version:    0.6.2
 */