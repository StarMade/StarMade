/*  1:   */package com.bulletphysics.collision.narrowphase;
/*  2:   */
/*  3:   */import com.bulletphysics.collision.shapes.ConvexShape;
/*  4:   */import com.bulletphysics.util.ObjectPool;
/*  5:   */
/* 39:   */public class GjkConvexCast
/* 40:   */  extends ConvexCast
/* 41:   */{
/* 42:42 */  protected final ObjectPool<DiscreteCollisionDetectorInterface.ClosestPointInput> pointInputsPool = ObjectPool.get(DiscreteCollisionDetectorInterface.ClosestPointInput.class);
/* 43:   */  
/* 45:   */  private static final int MAX_ITERATIONS = 32;
/* 46:   */  
/* 48:   */  private SimplexSolverInterface simplexSolver;
/* 49:   */  
/* 50:   */  private ConvexShape convexA;
/* 51:   */  
/* 52:   */  private ConvexShape convexB;
/* 53:   */  
/* 54:54 */  private GjkPairDetector gjk = new GjkPairDetector();
/* 55:   */  
/* 56:   */  public GjkConvexCast(ConvexShape convexA, ConvexShape convexB, SimplexSolverInterface simplexSolver) {
/* 57:57 */    this.simplexSolver = simplexSolver;
/* 58:58 */    this.convexA = convexA;
/* 59:59 */    this.convexB = convexB;
/* 60:   */  }
/* 61:   */  
/* 62:   */  /* Error */
/* 63:   */  public boolean calcTimeOfImpact(com.bulletphysics.linearmath.Transform arg1, com.bulletphysics.linearmath.Transform arg2, com.bulletphysics.linearmath.Transform arg3, com.bulletphysics.linearmath.Transform arg4, ConvexCast.CastResult arg5)
/* 64:   */  {
/* 65:   */    // Byte code:
/* 66:   */    //   0: invokestatic 62	com/bulletphysics/$Stack:get	()Lcom/bulletphysics/$Stack;
/* 67:   */    //   3: astore 26
/* 68:   */    //   5: aload 26
/* 69:   */    //   7: dup
/* 70:   */    //   8: invokevirtual 65	com/bulletphysics/$Stack:push$com$bulletphysics$linearmath$Transform	()V
/* 71:   */    //   11: invokevirtual 68	com/bulletphysics/$Stack:push$javax$vecmath$Vector3f	()V
/* 72:   */    //   14: aload_0
/* 73:   */    //   15: getfield 49	com/bulletphysics/collision/narrowphase/GjkConvexCast:simplexSolver	Lcom/bulletphysics/collision/narrowphase/SimplexSolverInterface;
/* 74:   */    //   18: invokevirtual 73	com/bulletphysics/collision/narrowphase/SimplexSolverInterface:reset	()V
/* 75:   */    //   21: aload 26
/* 76:   */    //   23: invokevirtual 77	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/* 77:   */    //   26: astore 6
/* 78:   */    //   28: aload 26
/* 79:   */    //   30: invokevirtual 77	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/* 80:   */    //   33: astore 7
/* 81:   */    //   35: aload 6
/* 82:   */    //   37: aload_2
/* 83:   */    //   38: getfield 83	com/bulletphysics/linearmath/Transform:origin	Ljavax/vecmath/Vector3f;
/* 84:   */    //   41: aload_1
/* 85:   */    //   42: getfield 83	com/bulletphysics/linearmath/Transform:origin	Ljavax/vecmath/Vector3f;
/* 86:   */    //   45: invokevirtual 89	javax/vecmath/Vector3f:sub	(Ljavax/vecmath/Tuple3f;Ljavax/vecmath/Tuple3f;)V
/* 87:   */    //   48: aload 7
/* 88:   */    //   50: aload 4
/* 89:   */    //   52: getfield 83	com/bulletphysics/linearmath/Transform:origin	Ljavax/vecmath/Vector3f;
/* 90:   */    //   55: aload_3
/* 91:   */    //   56: getfield 83	com/bulletphysics/linearmath/Transform:origin	Ljavax/vecmath/Vector3f;
/* 92:   */    //   59: invokevirtual 89	javax/vecmath/Vector3f:sub	(Ljavax/vecmath/Tuple3f;Ljavax/vecmath/Tuple3f;)V
/* 93:   */    //   62: ldc 90
/* 94:   */    //   64: fstore 8
/* 95:   */    //   66: fconst_0
/* 96:   */    //   67: fstore 9
/* 97:   */    //   69: aload 26
/* 98:   */    //   71: invokevirtual 77	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/* 99:   */    //   74: astore 10
/* 100:   */    //   76: aload 10
/* 101:   */    //   78: fconst_1
/* 102:   */    //   79: fconst_0
/* 103:   */    //   80: fconst_0
/* 104:   */    //   81: invokevirtual 94	javax/vecmath/Vector3f:set	(FFF)V
/* 105:   */    //   84: bipush 32
/* 106:   */    //   86: istore 11
/* 107:   */    //   88: aload 26
/* 108:   */    //   90: invokevirtual 77	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/* 109:   */    //   93: astore 12
/* 110:   */    //   95: aload 12
/* 111:   */    //   97: fconst_0
/* 112:   */    //   98: fconst_0
/* 113:   */    //   99: fconst_0
/* 114:   */    //   100: invokevirtual 94	javax/vecmath/Vector3f:set	(FFF)V
/* 115:   */    //   103: iconst_0
/* 116:   */    //   104: istore 13
/* 117:   */    //   106: aload 26
/* 118:   */    //   108: invokevirtual 77	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/* 119:   */    //   111: astore 14
/* 120:   */    //   113: aload 26
/* 121:   */    //   115: invokevirtual 77	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/* 122:   */    //   118: astore 15
/* 123:   */    //   120: aload 15
/* 124:   */    //   122: aload 6
/* 125:   */    //   124: aload 7
/* 126:   */    //   126: invokevirtual 89	javax/vecmath/Vector3f:sub	(Ljavax/vecmath/Tuple3f;Ljavax/vecmath/Tuple3f;)V
/* 127:   */    //   129: fload 9
/* 128:   */    //   131: fstore 16
/* 129:   */    //   133: iconst_0
/* 130:   */    //   134: istore 17
/* 131:   */    //   136: aload 26
/* 132:   */    //   138: invokevirtual 98	com/bulletphysics/$Stack:get$com$bulletphysics$linearmath$Transform	()Lcom/bulletphysics/linearmath/Transform;
/* 133:   */    //   141: astore 18
/* 134:   */    //   143: aload 18
/* 135:   */    //   145: invokevirtual 101	com/bulletphysics/linearmath/Transform:setIdentity	()V
/* 136:   */    //   148: new 103	com/bulletphysics/collision/narrowphase/PointCollector
/* 137:   */    //   151: dup
/* 138:   */    //   152: invokespecial 104	com/bulletphysics/collision/narrowphase/PointCollector:<init>	()V
/* 139:   */    //   155: astore 19
/* 140:   */    //   157: aload_0
/* 141:   */    //   158: getfield 47	com/bulletphysics/collision/narrowphase/GjkConvexCast:gjk	Lcom/bulletphysics/collision/narrowphase/GjkPairDetector;
/* 142:   */    //   161: aload_0
/* 143:   */    //   162: getfield 51	com/bulletphysics/collision/narrowphase/GjkConvexCast:convexA	Lcom/bulletphysics/collision/shapes/ConvexShape;
/* 144:   */    //   165: aload_0
/* 145:   */    //   166: getfield 53	com/bulletphysics/collision/narrowphase/GjkConvexCast:convexB	Lcom/bulletphysics/collision/shapes/ConvexShape;
/* 146:   */    //   169: aload_0
/* 147:   */    //   170: getfield 49	com/bulletphysics/collision/narrowphase/GjkConvexCast:simplexSolver	Lcom/bulletphysics/collision/narrowphase/SimplexSolverInterface;
/* 148:   */    //   173: aconst_null
/* 149:   */    //   174: invokevirtual 108	com/bulletphysics/collision/narrowphase/GjkPairDetector:init	(Lcom/bulletphysics/collision/shapes/ConvexShape;Lcom/bulletphysics/collision/shapes/ConvexShape;Lcom/bulletphysics/collision/narrowphase/SimplexSolverInterface;Lcom/bulletphysics/collision/narrowphase/ConvexPenetrationDepthSolver;)V
/* 150:   */    //   177: aload_0
/* 151:   */    //   178: getfield 42	com/bulletphysics/collision/narrowphase/GjkConvexCast:pointInputsPool	Lcom/bulletphysics/util/ObjectPool;
/* 152:   */    //   181: invokevirtual 111	com/bulletphysics/util/ObjectPool:get	()Ljava/lang/Object;
/* 153:   */    //   184: checkcast 7	com/bulletphysics/collision/narrowphase/DiscreteCollisionDetectorInterface$ClosestPointInput
/* 154:   */    //   187: astore 20
/* 155:   */    //   189: aload 20
/* 156:   */    //   191: invokevirtual 113	com/bulletphysics/collision/narrowphase/DiscreteCollisionDetectorInterface$ClosestPointInput:init	()V
/* 157:   */    //   194: aload 20
/* 158:   */    //   196: getfield 117	com/bulletphysics/collision/narrowphase/DiscreteCollisionDetectorInterface$ClosestPointInput:transformA	Lcom/bulletphysics/linearmath/Transform;
/* 159:   */    //   199: aload_1
/* 160:   */    //   200: invokevirtual 120	com/bulletphysics/linearmath/Transform:set	(Lcom/bulletphysics/linearmath/Transform;)V
/* 161:   */    //   203: aload 20
/* 162:   */    //   205: getfield 123	com/bulletphysics/collision/narrowphase/DiscreteCollisionDetectorInterface$ClosestPointInput:transformB	Lcom/bulletphysics/linearmath/Transform;
/* 163:   */    //   208: aload_3
/* 164:   */    //   209: invokevirtual 120	com/bulletphysics/linearmath/Transform:set	(Lcom/bulletphysics/linearmath/Transform;)V
/* 165:   */    //   212: aload_0
/* 166:   */    //   213: getfield 47	com/bulletphysics/collision/narrowphase/GjkConvexCast:gjk	Lcom/bulletphysics/collision/narrowphase/GjkPairDetector;
/* 167:   */    //   216: aload 20
/* 168:   */    //   218: aload 19
/* 169:   */    //   220: aconst_null
/* 170:   */    //   221: invokevirtual 127	com/bulletphysics/collision/narrowphase/GjkPairDetector:getClosestPoints	(Lcom/bulletphysics/collision/narrowphase/DiscreteCollisionDetectorInterface$ClosestPointInput;Lcom/bulletphysics/collision/narrowphase/DiscreteCollisionDetectorInterface$Result;Lcom/bulletphysics/linearmath/IDebugDraw;)V
/* 171:   */    //   224: aload 19
/* 172:   */    //   226: getfield 131	com/bulletphysics/collision/narrowphase/PointCollector:hasResult	Z
/* 173:   */    //   229: istore 13
/* 174:   */    //   231: aload 14
/* 175:   */    //   233: aload 19
/* 176:   */    //   235: getfield 134	com/bulletphysics/collision/narrowphase/PointCollector:pointInWorld	Ljavax/vecmath/Vector3f;
/* 177:   */    //   238: invokevirtual 137	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/* 178:   */    //   241: iload 13
/* 179:   */    //   243: ifeq +479 -> 722
/* 180:   */    //   246: aload 19
/* 181:   */    //   248: getfield 141	com/bulletphysics/collision/narrowphase/PointCollector:distance	F
/* 182:   */    //   251: fstore 21
/* 183:   */    //   253: aload 12
/* 184:   */    //   255: aload 19
/* 185:   */    //   257: getfield 144	com/bulletphysics/collision/narrowphase/PointCollector:normalOnBInWorld	Ljavax/vecmath/Vector3f;
/* 186:   */    //   260: invokevirtual 137	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/* 187:   */    //   263: fload 21
/* 188:   */    //   265: fload 8
/* 189:   */    //   267: fcmpl
/* 190:   */    //   268: ifle +362 -> 630
/* 191:   */    //   271: iinc 17 1
/* 192:   */    //   274: iload 17
/* 193:   */    //   276: iload 11
/* 194:   */    //   278: if_icmple +27 -> 305
/* 195:   */    //   281: iconst_0
/* 196:   */    //   282: istore 22
/* 197:   */    //   284: aload_0
/* 198:   */    //   285: getfield 42	com/bulletphysics/collision/narrowphase/GjkConvexCast:pointInputsPool	Lcom/bulletphysics/util/ObjectPool;
/* 199:   */    //   288: aload 20
/* 200:   */    //   290: invokevirtual 148	com/bulletphysics/util/ObjectPool:release	(Ljava/lang/Object;)V
/* 201:   */    //   293: iload 22
/* 202:   */    //   295: aload 26
/* 203:   */    //   297: dup
/* 204:   */    //   298: invokevirtual 151	com/bulletphysics/$Stack:pop$com$bulletphysics$linearmath$Transform	()V
/* 205:   */    //   301: invokevirtual 154	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
/* 206:   */    //   304: ireturn
/* 207:   */    //   305: fconst_0
/* 208:   */    //   306: fstore 22
/* 209:   */    //   308: aload 15
/* 210:   */    //   310: aload 12
/* 211:   */    //   312: invokevirtual 158	javax/vecmath/Vector3f:dot	(Ljavax/vecmath/Vector3f;)F
/* 212:   */    //   315: fstore 23
/* 213:   */    //   317: fload 21
/* 214:   */    //   319: fload 23
/* 215:   */    //   321: fdiv
/* 216:   */    //   322: fstore 22
/* 217:   */    //   324: fload 9
/* 218:   */    //   326: fload 22
/* 219:   */    //   328: fsub
/* 220:   */    //   329: fstore 9
/* 221:   */    //   331: fload 9
/* 222:   */    //   333: fconst_1
/* 223:   */    //   334: fcmpl
/* 224:   */    //   335: ifle +27 -> 362
/* 225:   */    //   338: iconst_0
/* 226:   */    //   339: istore 24
/* 227:   */    //   341: aload_0
/* 228:   */    //   342: getfield 42	com/bulletphysics/collision/narrowphase/GjkConvexCast:pointInputsPool	Lcom/bulletphysics/util/ObjectPool;
/* 229:   */    //   345: aload 20
/* 230:   */    //   347: invokevirtual 148	com/bulletphysics/util/ObjectPool:release	(Ljava/lang/Object;)V
/* 231:   */    //   350: iload 24
/* 232:   */    //   352: aload 26
/* 233:   */    //   354: dup
/* 234:   */    //   355: invokevirtual 151	com/bulletphysics/$Stack:pop$com$bulletphysics$linearmath$Transform	()V
/* 235:   */    //   358: invokevirtual 154	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
/* 236:   */    //   361: ireturn
/* 237:   */    //   362: fload 9
/* 238:   */    //   364: fconst_0
/* 239:   */    //   365: fcmpg
/* 240:   */    //   366: ifge +27 -> 393
/* 241:   */    //   369: iconst_0
/* 242:   */    //   370: istore 24
/* 243:   */    //   372: aload_0
/* 244:   */    //   373: getfield 42	com/bulletphysics/collision/narrowphase/GjkConvexCast:pointInputsPool	Lcom/bulletphysics/util/ObjectPool;
/* 245:   */    //   376: aload 20
/* 246:   */    //   378: invokevirtual 148	com/bulletphysics/util/ObjectPool:release	(Ljava/lang/Object;)V
/* 247:   */    //   381: iload 24
/* 248:   */    //   383: aload 26
/* 249:   */    //   385: dup
/* 250:   */    //   386: invokevirtual 151	com/bulletphysics/$Stack:pop$com$bulletphysics$linearmath$Transform	()V
/* 251:   */    //   389: invokevirtual 154	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
/* 252:   */    //   392: ireturn
/* 253:   */    //   393: fload 9
/* 254:   */    //   395: fload 16
/* 255:   */    //   397: fcmpg
/* 256:   */    //   398: ifgt +27 -> 425
/* 257:   */    //   401: iconst_0
/* 258:   */    //   402: istore 24
/* 259:   */    //   404: aload_0
/* 260:   */    //   405: getfield 42	com/bulletphysics/collision/narrowphase/GjkConvexCast:pointInputsPool	Lcom/bulletphysics/util/ObjectPool;
/* 261:   */    //   408: aload 20
/* 262:   */    //   410: invokevirtual 148	com/bulletphysics/util/ObjectPool:release	(Ljava/lang/Object;)V
/* 263:   */    //   413: iload 24
/* 264:   */    //   415: aload 26
/* 265:   */    //   417: dup
/* 266:   */    //   418: invokevirtual 151	com/bulletphysics/$Stack:pop$com$bulletphysics$linearmath$Transform	()V
/* 267:   */    //   421: invokevirtual 154	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
/* 268:   */    //   424: ireturn
/* 269:   */    //   425: fload 9
/* 270:   */    //   427: fstore 16
/* 271:   */    //   429: aload 5
/* 272:   */    //   431: fload 9
/* 273:   */    //   433: invokevirtual 162	com/bulletphysics/collision/narrowphase/ConvexCast$CastResult:debugDraw	(F)V
/* 274:   */    //   436: aload 20
/* 275:   */    //   438: getfield 117	com/bulletphysics/collision/narrowphase/DiscreteCollisionDetectorInterface$ClosestPointInput:transformA	Lcom/bulletphysics/linearmath/Transform;
/* 276:   */    //   441: getfield 83	com/bulletphysics/linearmath/Transform:origin	Ljavax/vecmath/Vector3f;
/* 277:   */    //   444: aload_1
/* 278:   */    //   445: getfield 83	com/bulletphysics/linearmath/Transform:origin	Ljavax/vecmath/Vector3f;
/* 279:   */    //   448: aload_2
/* 280:   */    //   449: getfield 83	com/bulletphysics/linearmath/Transform:origin	Ljavax/vecmath/Vector3f;
/* 281:   */    //   452: fload 9
/* 282:   */    //   454: invokestatic 168	com/bulletphysics/linearmath/VectorUtil:setInterpolate3	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;F)V
/* 283:   */    //   457: aload 20
/* 284:   */    //   459: getfield 123	com/bulletphysics/collision/narrowphase/DiscreteCollisionDetectorInterface$ClosestPointInput:transformB	Lcom/bulletphysics/linearmath/Transform;
/* 285:   */    //   462: getfield 83	com/bulletphysics/linearmath/Transform:origin	Ljavax/vecmath/Vector3f;
/* 286:   */    //   465: aload_3
/* 287:   */    //   466: getfield 83	com/bulletphysics/linearmath/Transform:origin	Ljavax/vecmath/Vector3f;
/* 288:   */    //   469: aload 4
/* 289:   */    //   471: getfield 83	com/bulletphysics/linearmath/Transform:origin	Ljavax/vecmath/Vector3f;
/* 290:   */    //   474: fload 9
/* 291:   */    //   476: invokestatic 168	com/bulletphysics/linearmath/VectorUtil:setInterpolate3	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;F)V
/* 292:   */    //   479: aload_0
/* 293:   */    //   480: getfield 47	com/bulletphysics/collision/narrowphase/GjkConvexCast:gjk	Lcom/bulletphysics/collision/narrowphase/GjkPairDetector;
/* 294:   */    //   483: aload 20
/* 295:   */    //   485: aload 19
/* 296:   */    //   487: aconst_null
/* 297:   */    //   488: invokevirtual 127	com/bulletphysics/collision/narrowphase/GjkPairDetector:getClosestPoints	(Lcom/bulletphysics/collision/narrowphase/DiscreteCollisionDetectorInterface$ClosestPointInput;Lcom/bulletphysics/collision/narrowphase/DiscreteCollisionDetectorInterface$Result;Lcom/bulletphysics/linearmath/IDebugDraw;)V
/* 298:   */    //   491: aload 19
/* 299:   */    //   493: getfield 131	com/bulletphysics/collision/narrowphase/PointCollector:hasResult	Z
/* 300:   */    //   496: ifeq +107 -> 603
/* 301:   */    //   499: aload 19
/* 302:   */    //   501: getfield 141	com/bulletphysics/collision/narrowphase/PointCollector:distance	F
/* 303:   */    //   504: fconst_0
/* 304:   */    //   505: fcmpg
/* 305:   */    //   506: ifge +67 -> 573
/* 306:   */    //   509: aload 5
/* 307:   */    //   511: fload 16
/* 308:   */    //   513: putfield 171	com/bulletphysics/collision/narrowphase/ConvexCast$CastResult:fraction	F
/* 309:   */    //   516: aload 12
/* 310:   */    //   518: aload 19
/* 311:   */    //   520: getfield 144	com/bulletphysics/collision/narrowphase/PointCollector:normalOnBInWorld	Ljavax/vecmath/Vector3f;
/* 312:   */    //   523: invokevirtual 137	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/* 313:   */    //   526: aload 5
/* 314:   */    //   528: getfield 174	com/bulletphysics/collision/narrowphase/ConvexCast$CastResult:normal	Ljavax/vecmath/Vector3f;
/* 315:   */    //   531: aload 12
/* 316:   */    //   533: invokevirtual 137	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/* 317:   */    //   536: aload 5
/* 318:   */    //   538: getfield 177	com/bulletphysics/collision/narrowphase/ConvexCast$CastResult:hitPoint	Ljavax/vecmath/Vector3f;
/* 319:   */    //   541: aload 19
/* 320:   */    //   543: getfield 134	com/bulletphysics/collision/narrowphase/PointCollector:pointInWorld	Ljavax/vecmath/Vector3f;
/* 321:   */    //   546: invokevirtual 137	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/* 322:   */    //   549: iconst_1
/* 323:   */    //   550: istore 24
/* 324:   */    //   552: aload_0
/* 325:   */    //   553: getfield 42	com/bulletphysics/collision/narrowphase/GjkConvexCast:pointInputsPool	Lcom/bulletphysics/util/ObjectPool;
/* 326:   */    //   556: aload 20
/* 327:   */    //   558: invokevirtual 148	com/bulletphysics/util/ObjectPool:release	(Ljava/lang/Object;)V
/* 328:   */    //   561: iload 24
/* 329:   */    //   563: aload 26
/* 330:   */    //   565: dup
/* 331:   */    //   566: invokevirtual 151	com/bulletphysics/$Stack:pop$com$bulletphysics$linearmath$Transform	()V
/* 332:   */    //   569: invokevirtual 154	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
/* 333:   */    //   572: ireturn
/* 334:   */    //   573: aload 14
/* 335:   */    //   575: aload 19
/* 336:   */    //   577: getfield 134	com/bulletphysics/collision/narrowphase/PointCollector:pointInWorld	Ljavax/vecmath/Vector3f;
/* 337:   */    //   580: invokevirtual 137	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/* 338:   */    //   583: aload 12
/* 339:   */    //   585: aload 19
/* 340:   */    //   587: getfield 144	com/bulletphysics/collision/narrowphase/PointCollector:normalOnBInWorld	Ljavax/vecmath/Vector3f;
/* 341:   */    //   590: invokevirtual 137	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/* 342:   */    //   593: aload 19
/* 343:   */    //   595: getfield 141	com/bulletphysics/collision/narrowphase/PointCollector:distance	F
/* 344:   */    //   598: fstore 21
/* 345:   */    //   600: goto +27 -> 627
/* 346:   */    //   603: iconst_0
/* 347:   */    //   604: istore 24
/* 348:   */    //   606: aload_0
/* 349:   */    //   607: getfield 42	com/bulletphysics/collision/narrowphase/GjkConvexCast:pointInputsPool	Lcom/bulletphysics/util/ObjectPool;
/* 350:   */    //   610: aload 20
/* 351:   */    //   612: invokevirtual 148	com/bulletphysics/util/ObjectPool:release	(Ljava/lang/Object;)V
/* 352:   */    //   615: iload 24
/* 353:   */    //   617: aload 26
/* 354:   */    //   619: dup
/* 355:   */    //   620: invokevirtual 151	com/bulletphysics/$Stack:pop$com$bulletphysics$linearmath$Transform	()V
/* 356:   */    //   623: invokevirtual 154	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
/* 357:   */    //   626: ireturn
/* 358:   */    //   627: goto -364 -> 263
/* 359:   */    //   630: aload 12
/* 360:   */    //   632: aload 15
/* 361:   */    //   634: invokevirtual 158	javax/vecmath/Vector3f:dot	(Ljavax/vecmath/Vector3f;)F
/* 362:   */    //   637: aload 5
/* 363:   */    //   639: getfield 180	com/bulletphysics/collision/narrowphase/ConvexCast$CastResult:allowedPenetration	F
/* 364:   */    //   642: fneg
/* 365:   */    //   643: fcmpl
/* 366:   */    //   644: iflt +27 -> 671
/* 367:   */    //   647: iconst_0
/* 368:   */    //   648: istore 22
/* 369:   */    //   650: aload_0
/* 370:   */    //   651: getfield 42	com/bulletphysics/collision/narrowphase/GjkConvexCast:pointInputsPool	Lcom/bulletphysics/util/ObjectPool;
/* 371:   */    //   654: aload 20
/* 372:   */    //   656: invokevirtual 148	com/bulletphysics/util/ObjectPool:release	(Ljava/lang/Object;)V
/* 373:   */    //   659: iload 22
/* 374:   */    //   661: aload 26
/* 375:   */    //   663: dup
/* 376:   */    //   664: invokevirtual 151	com/bulletphysics/$Stack:pop$com$bulletphysics$linearmath$Transform	()V
/* 377:   */    //   667: invokevirtual 154	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
/* 378:   */    //   670: ireturn
/* 379:   */    //   671: aload 5
/* 380:   */    //   673: fload 9
/* 381:   */    //   675: putfield 171	com/bulletphysics/collision/narrowphase/ConvexCast$CastResult:fraction	F
/* 382:   */    //   678: aload 5
/* 383:   */    //   680: getfield 174	com/bulletphysics/collision/narrowphase/ConvexCast$CastResult:normal	Ljavax/vecmath/Vector3f;
/* 384:   */    //   683: aload 12
/* 385:   */    //   685: invokevirtual 137	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/* 386:   */    //   688: aload 5
/* 387:   */    //   690: getfield 177	com/bulletphysics/collision/narrowphase/ConvexCast$CastResult:hitPoint	Ljavax/vecmath/Vector3f;
/* 388:   */    //   693: aload 14
/* 389:   */    //   695: invokevirtual 137	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/* 390:   */    //   698: iconst_1
/* 391:   */    //   699: istore 22
/* 392:   */    //   701: aload_0
/* 393:   */    //   702: getfield 42	com/bulletphysics/collision/narrowphase/GjkConvexCast:pointInputsPool	Lcom/bulletphysics/util/ObjectPool;
/* 394:   */    //   705: aload 20
/* 395:   */    //   707: invokevirtual 148	com/bulletphysics/util/ObjectPool:release	(Ljava/lang/Object;)V
/* 396:   */    //   710: iload 22
/* 397:   */    //   712: aload 26
/* 398:   */    //   714: dup
/* 399:   */    //   715: invokevirtual 151	com/bulletphysics/$Stack:pop$com$bulletphysics$linearmath$Transform	()V
/* 400:   */    //   718: invokevirtual 154	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
/* 401:   */    //   721: ireturn
/* 402:   */    //   722: iconst_0
/* 403:   */    //   723: istore 21
/* 404:   */    //   725: aload_0
/* 405:   */    //   726: getfield 42	com/bulletphysics/collision/narrowphase/GjkConvexCast:pointInputsPool	Lcom/bulletphysics/util/ObjectPool;
/* 406:   */    //   729: aload 20
/* 407:   */    //   731: invokevirtual 148	com/bulletphysics/util/ObjectPool:release	(Ljava/lang/Object;)V
/* 408:   */    //   734: iload 21
/* 409:   */    //   736: aload 26
/* 410:   */    //   738: dup
/* 411:   */    //   739: invokevirtual 151	com/bulletphysics/$Stack:pop$com$bulletphysics$linearmath$Transform	()V
/* 412:   */    //   742: invokevirtual 154	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
/* 413:   */    //   745: ireturn
/* 414:   */    //   746: astore 25
/* 415:   */    //   748: aload_0
/* 416:   */    //   749: getfield 42	com/bulletphysics/collision/narrowphase/GjkConvexCast:pointInputsPool	Lcom/bulletphysics/util/ObjectPool;
/* 417:   */    //   752: aload 20
/* 418:   */    //   754: invokevirtual 148	com/bulletphysics/util/ObjectPool:release	(Ljava/lang/Object;)V
/* 419:   */    //   757: aload 25
/* 420:   */    //   759: athrow
/* 421:   */    //   760: aload 26
/* 422:   */    //   762: dup
/* 423:   */    //   763: invokevirtual 151	com/bulletphysics/$Stack:pop$com$bulletphysics$linearmath$Transform	()V
/* 424:   */    //   766: invokevirtual 154	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
/* 425:   */    //   769: athrow
/* 426:   */    // Line number table:
/* 427:   */    //   Java source line #63	-> byte code offset #14
/* 428:   */    //   Java source line #67	-> byte code offset #21
/* 429:   */    //   Java source line #68	-> byte code offset #28
/* 430:   */    //   Java source line #70	-> byte code offset #35
/* 431:   */    //   Java source line #71	-> byte code offset #48
/* 432:   */    //   Java source line #73	-> byte code offset #62
/* 433:   */    //   Java source line #74	-> byte code offset #66
/* 434:   */    //   Java source line #75	-> byte code offset #69
/* 435:   */    //   Java source line #76	-> byte code offset #76
/* 436:   */    //   Java source line #78	-> byte code offset #84
/* 437:   */    //   Java source line #80	-> byte code offset #88
/* 438:   */    //   Java source line #81	-> byte code offset #95
/* 439:   */    //   Java source line #82	-> byte code offset #103
/* 440:   */    //   Java source line #83	-> byte code offset #106
/* 441:   */    //   Java source line #84	-> byte code offset #113
/* 442:   */    //   Java source line #85	-> byte code offset #120
/* 443:   */    //   Java source line #87	-> byte code offset #129
/* 444:   */    //   Java source line #90	-> byte code offset #133
/* 445:   */    //   Java source line #93	-> byte code offset #136
/* 446:   */    //   Java source line #94	-> byte code offset #143
/* 447:   */    //   Java source line #98	-> byte code offset #148
/* 448:   */    //   Java source line #100	-> byte code offset #157
/* 449:   */    //   Java source line #101	-> byte code offset #177
/* 450:   */    //   Java source line #102	-> byte code offset #189
/* 451:   */    //   Java source line #107	-> byte code offset #194
/* 452:   */    //   Java source line #108	-> byte code offset #203
/* 453:   */    //   Java source line #109	-> byte code offset #212
/* 454:   */    //   Java source line #111	-> byte code offset #224
/* 455:   */    //   Java source line #112	-> byte code offset #231
/* 456:   */    //   Java source line #114	-> byte code offset #241
/* 457:   */    //   Java source line #116	-> byte code offset #246
/* 458:   */    //   Java source line #117	-> byte code offset #253
/* 459:   */    //   Java source line #120	-> byte code offset #263
/* 460:   */    //   Java source line #121	-> byte code offset #271
/* 461:   */    //   Java source line #122	-> byte code offset #274
/* 462:   */    //   Java source line #123	-> byte code offset #281
/* 463:   */    //   Java source line #186	-> byte code offset #284
/* 464:   */    //   Java source line #125	-> byte code offset #305
/* 465:   */    //   Java source line #127	-> byte code offset #308
/* 466:   */    //   Java source line #129	-> byte code offset #317
/* 467:   */    //   Java source line #131	-> byte code offset #324
/* 468:   */    //   Java source line #133	-> byte code offset #331
/* 469:   */    //   Java source line #134	-> byte code offset #338
/* 470:   */    //   Java source line #186	-> byte code offset #341
/* 471:   */    //   Java source line #136	-> byte code offset #362
/* 472:   */    //   Java source line #137	-> byte code offset #369
/* 473:   */    //   Java source line #186	-> byte code offset #372
/* 474:   */    //   Java source line #140	-> byte code offset #393
/* 475:   */    //   Java source line #141	-> byte code offset #401
/* 476:   */    //   Java source line #186	-> byte code offset #404
/* 477:   */    //   Java source line #145	-> byte code offset #425
/* 478:   */    //   Java source line #148	-> byte code offset #429
/* 479:   */    //   Java source line #149	-> byte code offset #436
/* 480:   */    //   Java source line #150	-> byte code offset #457
/* 481:   */    //   Java source line #152	-> byte code offset #479
/* 482:   */    //   Java source line #153	-> byte code offset #491
/* 483:   */    //   Java source line #154	-> byte code offset #499
/* 484:   */    //   Java source line #155	-> byte code offset #509
/* 485:   */    //   Java source line #156	-> byte code offset #516
/* 486:   */    //   Java source line #157	-> byte code offset #526
/* 487:   */    //   Java source line #158	-> byte code offset #536
/* 488:   */    //   Java source line #159	-> byte code offset #549
/* 489:   */    //   Java source line #186	-> byte code offset #552
/* 490:   */    //   Java source line #161	-> byte code offset #573
/* 491:   */    //   Java source line #162	-> byte code offset #583
/* 492:   */    //   Java source line #163	-> byte code offset #593
/* 493:   */    //   Java source line #167	-> byte code offset #603
/* 494:   */    //   Java source line #186	-> byte code offset #606
/* 495:   */    //   Java source line #170	-> byte code offset #627
/* 496:   */    //   Java source line #174	-> byte code offset #630
/* 497:   */    //   Java source line #175	-> byte code offset #647
/* 498:   */    //   Java source line #186	-> byte code offset #650
/* 499:   */    //   Java source line #177	-> byte code offset #671
/* 500:   */    //   Java source line #178	-> byte code offset #678
/* 501:   */    //   Java source line #179	-> byte code offset #688
/* 502:   */    //   Java source line #180	-> byte code offset #698
/* 503:   */    //   Java source line #186	-> byte code offset #701
/* 504:   */    //   Java source line #183	-> byte code offset #722
/* 505:   */    //   Java source line #186	-> byte code offset #725
/* 506:   */    // Local variable table:
/* 507:   */    //   start	length	slot	name	signature
/* 508:   */    //   14	746	0	this	GjkConvexCast
/* 509:   */    //   14	746	1	fromA	com.bulletphysics.linearmath.Transform
/* 510:   */    //   14	746	2	toA	com.bulletphysics.linearmath.Transform
/* 511:   */    //   14	746	3	fromB	com.bulletphysics.linearmath.Transform
/* 512:   */    //   14	746	4	toB	com.bulletphysics.linearmath.Transform
/* 513:   */    //   14	746	5	result	ConvexCast.CastResult
/* 514:   */    //   26	97	6	linVelA	javax.vecmath.Vector3f
/* 515:   */    //   33	92	7	linVelB	javax.vecmath.Vector3f
/* 516:   */    //   64	202	8	radius	float
/* 517:   */    //   67	607	9	lambda	float
/* 518:   */    //   74	3	10	v	javax.vecmath.Vector3f
/* 519:   */    //   86	191	11	maxIter	int
/* 520:   */    //   93	591	12	n	javax.vecmath.Vector3f
/* 521:   */    //   104	138	13	hasResult	boolean
/* 522:   */    //   111	583	14	c	javax.vecmath.Vector3f
/* 523:   */    //   118	515	15	r	javax.vecmath.Vector3f
/* 524:   */    //   131	381	16	lastLambda	float
/* 525:   */    //   134	141	17	numIter	int
/* 526:   */    //   141	3	18	identityTrans	com.bulletphysics.linearmath.Transform
/* 527:   */    //   155	439	19	pointCollector	PointCollector
/* 528:   */    //   187	566	20	input	DiscreteCollisionDetectorInterface.ClosestPointInput
/* 529:   */    //   251	484	21	dist	float
/* 530:   */    //   282	12	22	bool1	boolean
/* 531:   */    //   306	405	22	dLambda	float
/* 532:   */    //   648	63	22	bool2	boolean
/* 533:   */    //   315	5	23	projectedLinearVelocity	float
/* 534:   */    //   339	277	24	bool3	boolean
/* 535:   */    //   746	12	25	localObject	java.lang.Object
/* 536:   */    //   3	758	26	localStack	com.bulletphysics..Stack
/* 537:   */    // Exception table:
/* 538:   */    //   from	to	target	type
/* 539:   */    //   194	284	746	finally
/* 540:   */    //   305	341	746	finally
/* 541:   */    //   362	372	746	finally
/* 542:   */    //   393	404	746	finally
/* 543:   */    //   425	552	746	finally
/* 544:   */    //   573	606	746	finally
/* 545:   */    //   627	650	746	finally
/* 546:   */    //   671	701	746	finally
/* 547:   */    //   722	725	746	finally
/* 548:   */    //   746	748	746	finally
/* 549:   */    //   5	760	760	finally
/* 550:   */  }
/* 551:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.narrowphase.GjkConvexCast
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */