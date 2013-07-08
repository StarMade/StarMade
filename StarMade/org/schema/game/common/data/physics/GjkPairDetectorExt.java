/*   1:    */package org.schema.game.common.data.physics;
/*   2:    */
/*   3:    */import com.bulletphysics.BulletStats;
/*   4:    */import com.bulletphysics.collision.narrowphase.ConvexPenetrationDepthSolver;
/*   5:    */import com.bulletphysics.collision.narrowphase.DiscreteCollisionDetectorInterface;
/*   6:    */import com.bulletphysics.collision.narrowphase.DiscreteCollisionDetectorInterface.ClosestPointInput;
/*   7:    */import com.bulletphysics.collision.narrowphase.DiscreteCollisionDetectorInterface.Result;
/*   8:    */import com.bulletphysics.collision.narrowphase.SimplexSolverInterface;
/*   9:    */import com.bulletphysics.collision.shapes.ConvexShape;
/*  10:    */import com.bulletphysics.linearmath.IDebugDraw;
/*  11:    */import com.bulletphysics.linearmath.MatrixUtil;
/*  12:    */import com.bulletphysics.linearmath.Transform;
/*  13:    */import javax.vecmath.Vector3f;
/*  14:    */
/*  46:    */public class GjkPairDetectorExt
/*  47:    */  extends DiscreteCollisionDetectorInterface
/*  48:    */{
/*  49:    */  private static final float REL_ERROR2 = 1.0E-006F;
/*  50: 50 */  private final Vector3f cachedSeparatingAxis = new Vector3f();
/*  51:    */  
/*  52:    */  private ConvexPenetrationDepthSolver penetrationDepthSolver;
/*  53:    */  
/*  54:    */  private SimplexSolverInterface simplexSolver;
/*  55:    */  
/*  56:    */  private ConvexShape minkowskiA;
/*  57:    */  
/*  58:    */  private ConvexShape minkowskiB;
/*  59:    */  private boolean ignoreMargin;
/*  60:    */  public int contacts;
/*  61:    */  public int lastUsedMethod;
/*  62:    */  public int curIter;
/*  63:    */  public int degenerateSimplex;
/*  64:    */  public int catchDegeneracies;
/*  65:    */  private GjkPairDetectorVariables v;
/*  66:    */  public float maxDepth;
/*  67:    */  
/*  68:    */  public GjkPairDetectorExt(GjkPairDetectorVariables paramGjkPairDetectorVariables)
/*  69:    */  {
/*  70: 70 */    this.v = paramGjkPairDetectorVariables;
/*  71:    */  }
/*  72:    */  
/*  73:    */  public void init(ConvexShape paramConvexShape1, ConvexShape paramConvexShape2, SimplexSolverInterface paramSimplexSolverInterface, ConvexPenetrationDepthSolver paramConvexPenetrationDepthSolver) {
/*  74: 74 */    this.cachedSeparatingAxis.set(0.0F, 0.0F, 1.0F);
/*  75: 75 */    this.ignoreMargin = false;
/*  76: 76 */    this.lastUsedMethod = -1;
/*  77: 77 */    this.catchDegeneracies = 1;
/*  78:    */    
/*  79: 79 */    this.penetrationDepthSolver = paramConvexPenetrationDepthSolver;
/*  80: 80 */    this.simplexSolver = paramSimplexSolverInterface;
/*  81: 81 */    this.minkowskiA = paramConvexShape1;
/*  82: 82 */    this.minkowskiB = paramConvexShape2;
/*  83:    */  }
/*  84:    */  
/*  85:    */  public void getClosestPoints(DiscreteCollisionDetectorInterface.ClosestPointInput paramClosestPointInput, DiscreteCollisionDetectorInterface.Result paramResult, IDebugDraw paramIDebugDraw, boolean paramBoolean)
/*  86:    */  {
/*  87: 87 */    this.contacts = 0;
/*  88: 88 */    this.maxDepth = 0.0F;
/*  89:    */    
/*  90: 90 */    paramBoolean = this.v.tmp;
/*  91:    */    
/*  92: 92 */    float f1 = 0.0F;
/*  93:    */    Vector3f localVector3f1;
/*  94: 94 */    (localVector3f1 = this.v.normalInB).set(0.0F, 0.0F, 0.0F);
/*  95: 95 */    Vector3f localVector3f2 = this.v.pointOnA;
/*  96: 96 */    Vector3f localVector3f3 = this.v.pointOnB;
/*  97: 97 */    Transform localTransform1 = this.v.localTransA;
/*  98: 98 */    Transform localTransform2 = this.v.localTransB;
/*  99: 99 */    localTransform1.set(paramClosestPointInput.transformA);
/* 100:100 */    localTransform2.set(paramClosestPointInput.transformB);
/* 101:    */    Vector3f localVector3f4;
/* 102:102 */    (localVector3f4 = this.v.positionOffset).add(localTransform1.origin, localTransform2.origin);
/* 103:103 */    localVector3f4.scale(0.5F);
/* 104:104 */    localTransform1.origin.sub(localVector3f4);
/* 105:105 */    localTransform2.origin.sub(localVector3f4);
/* 106:    */    
/* 107:107 */    float f2 = this.minkowskiA.getMargin();
/* 108:108 */    float f3 = this.minkowskiB.getMargin();
/* 109:    */    
/* 110:110 */    BulletStats.gNumGjkChecks += 1;
/* 111:    */    
/* 113:113 */    if (this.ignoreMargin) {
/* 114:114 */      f2 = 0.0F;
/* 115:115 */      f3 = 0.0F;
/* 116:    */    }
/* 117:    */    
/* 118:118 */    this.curIter = 0;
/* 119:119 */    this.cachedSeparatingAxis.set(0.0F, 1.0F, 0.0F);
/* 120:    */    
/* 122:122 */    int i = 0;
/* 123:123 */    int j = 0;
/* 124:124 */    int k = 1;
/* 125:125 */    this.degenerateSimplex = 0;
/* 126:    */    
/* 127:127 */    this.lastUsedMethod = -1;
/* 128:    */    
/* 130:130 */    float f4 = 3.4028235E+38F;
/* 131:131 */    float f7 = f2 + f3;
/* 132:    */    
/* 135:135 */    this.simplexSolver.reset();
/* 136:    */    
/* 137:137 */    Vector3f localVector3f5 = this.v.seperatingAxisInA;
/* 138:138 */    Vector3f localVector3f6 = this.v.seperatingAxisInB;
/* 139:    */    
/* 140:140 */    Vector3f localVector3f7 = this.v.pInA;
/* 141:141 */    Vector3f localVector3f8 = this.v.qInB;
/* 142:    */    
/* 143:143 */    Vector3f localVector3f9 = this.v.pWorld;
/* 144:144 */    Vector3f localVector3f10 = this.v.qWorld;
/* 145:145 */    Vector3f localVector3f11 = this.v.w;
/* 146:    */    
/* 147:147 */    Vector3f localVector3f12 = this.v.tmpPointOnA;
/* 148:148 */    Vector3f localVector3f13 = this.v.tmpPointOnB;
/* 149:149 */    Vector3f localVector3f14 = this.v.tmpNormalInB;
/* 150:    */    float f5;
/* 151:    */    float f8;
/* 152:    */    for (;;) {
/* 153:153 */      localVector3f5.negate(this.cachedSeparatingAxis);
/* 154:154 */      MatrixUtil.transposeTransform(localVector3f5, localVector3f5, paramClosestPointInput.transformA.basis);
/* 155:    */      
/* 156:156 */      localVector3f6.set(this.cachedSeparatingAxis);
/* 157:157 */      MatrixUtil.transposeTransform(localVector3f6, localVector3f6, paramClosestPointInput.transformB.basis);
/* 158:    */      
/* 159:159 */      this.minkowskiA.localGetSupportingVertexWithoutMargin(localVector3f5, localVector3f7);
/* 160:160 */      this.minkowskiB.localGetSupportingVertexWithoutMargin(localVector3f6, localVector3f8);
/* 161:    */      
/* 162:162 */      localVector3f9.set(localVector3f7);
/* 163:163 */      localTransform1.transform(localVector3f9);
/* 164:    */      
/* 165:165 */      localVector3f10.set(localVector3f8);
/* 166:166 */      localTransform2.transform(localVector3f10);
/* 167:    */      
/* 168:168 */      localVector3f11.sub(localVector3f9, localVector3f10);
/* 169:    */      
/* 173:173 */      if (((f5 = this.cachedSeparatingAxis.dot(localVector3f11)) > 0.0F) && (f5 * f5 > f4 * paramClosestPointInput.maximumDistanceSquared)) {
/* 174:174 */        k = 0;
/* 175:175 */        break;
/* 176:    */      }
/* 177:    */      
/* 179:179 */      if (this.simplexSolver.inSimplex(localVector3f11)) {
/* 180:180 */        this.degenerateSimplex = 1;
/* 181:181 */        j = 1;
/* 182:182 */        break;
/* 183:    */      }
/* 184:    */      
/* 185:185 */      f5 = f4 - f5;
/* 186:186 */      f8 = f4 * 1.0E-006F;
/* 187:    */      
/* 188:188 */      if (f5 <= f8) {
/* 189:189 */        if (f5 <= 0.0F) {
/* 190:190 */          this.degenerateSimplex = 2;
/* 191:    */        }
/* 192:192 */        j = 1;
/* 193:193 */        break;
/* 194:    */      }
/* 195:    */      
/* 196:196 */      this.simplexSolver.addVertex(localVector3f11, localVector3f9, localVector3f10);
/* 197:    */      
/* 199:199 */      if (!this.simplexSolver.closest(this.cachedSeparatingAxis)) {
/* 200:200 */        this.degenerateSimplex = 3;
/* 201:201 */        j = 1;
/* 202:202 */        break;
/* 203:    */      }
/* 204:    */      
/* 205:205 */      if (this.cachedSeparatingAxis.lengthSquared() < 1.0E-006F) {
/* 206:206 */        this.degenerateSimplex = 6;
/* 207:207 */        j = 1;
/* 208:208 */        break;
/* 209:    */      }
/* 210:    */      
/* 211:211 */      f5 = f4;
/* 212:212 */      f4 = this.cachedSeparatingAxis.lengthSquared();
/* 213:    */      
/* 217:217 */      if (f5 - f4 <= 1.192093E-007F * f5) {
/* 218:218 */        this.simplexSolver.backup_closest(this.cachedSeparatingAxis);
/* 219:219 */        j = 1;
/* 220:220 */        break;
/* 221:    */      }
/* 222:    */      
/* 224:224 */      if (this.curIter++ > 1000) {
/* 225:    */        break;
/* 226:    */      }
/* 227:    */      
/* 244:244 */      if ((!this.simplexSolver.fullSimplex() ? 1 : 0) == 0)
/* 245:    */      {
/* 246:246 */        this.simplexSolver.backup_closest(this.cachedSeparatingAxis);
/* 247:247 */        break;
/* 248:    */      }
/* 249:    */    }
/* 250:250 */    if (j != 0) {
/* 251:251 */      this.simplexSolver.compute_points(localVector3f2, localVector3f3);
/* 252:252 */      localVector3f1.sub(localVector3f2, localVector3f3);
/* 253:    */      
/* 255:255 */      if ((f5 = this.cachedSeparatingAxis.lengthSquared()) < 1.0E-004F) {
/* 256:256 */        this.degenerateSimplex = 5;
/* 257:    */      }
/* 258:258 */      if (f5 > 1.421086E-014F) {
/* 259:259 */        f8 = 1.0F / (float)Math.sqrt(f5);
/* 260:260 */        localVector3f1.scale(f8);
/* 261:261 */        f5 = (float)Math.sqrt(f4);
/* 262:    */        
/* 263:263 */        assert (f5 > 0.0F);
/* 264:    */        
/* 265:265 */        paramBoolean.scale(f2 / f5, this.cachedSeparatingAxis);
/* 266:266 */        localVector3f2.sub(paramBoolean);
/* 267:    */        
/* 268:268 */        paramBoolean.scale(f3 / f5, this.cachedSeparatingAxis);
/* 269:269 */        localVector3f3.add(paramBoolean);
/* 270:    */        
/* 271:271 */        f1 = 1.0F / f8 - f7;
/* 272:272 */        i = 1;
/* 273:    */        
/* 274:274 */        this.lastUsedMethod = 1;
/* 275:    */      }
/* 276:    */      else {
/* 277:277 */        this.lastUsedMethod = 2;
/* 278:    */      }
/* 279:    */    }
/* 280:    */    
/* 281:281 */    int m = (this.catchDegeneracies != 0) && (this.penetrationDepthSolver != null) && (this.degenerateSimplex != 0) && (f1 + f7 < 0.01F) ? 1 : 0;
/* 282:    */    
/* 285:285 */    if ((k != 0) && ((i == 0) || (m != 0)))
/* 286:    */    {
/* 289:289 */      if (this.penetrationDepthSolver != null)
/* 290:    */      {
/* 291:291 */        BulletStats.gNumDeepPenetrationChecks += 1;
/* 292:    */        
/* 300:300 */        if (this.penetrationDepthSolver.calcPenDepth(this.simplexSolver, this.minkowskiA, this.minkowskiB, localTransform1, localTransform2, this.cachedSeparatingAxis, localVector3f12, localVector3f13, paramIDebugDraw)) {
/* 301:301 */          localVector3f14.sub(localVector3f13, localVector3f12);
/* 302:    */          
/* 303:    */          float f6;
/* 304:304 */          if ((f6 = localVector3f14.lengthSquared()) > 1.421086E-014F) {
/* 305:305 */            localVector3f14.scale(1.0F / (float)Math.sqrt(f6));
/* 306:306 */            paramBoolean.sub(localVector3f12, localVector3f13);
/* 307:307 */            f6 = -paramBoolean.length();
/* 308:    */            
/* 309:309 */            if ((i == 0) || (f6 < f1)) {
/* 310:310 */              f1 = f6;
/* 311:311 */              localVector3f2.set(localVector3f12);
/* 312:312 */              localVector3f3.set(localVector3f13);
/* 313:313 */              localVector3f1.set(localVector3f14);
/* 314:314 */              i = 1;
/* 315:315 */              this.lastUsedMethod = 3;
/* 316:    */            }
/* 317:    */            
/* 319:    */          }
/* 320:    */          else
/* 321:    */          {
/* 323:323 */            this.lastUsedMethod = 4;
/* 324:    */          }
/* 325:    */        }
/* 326:    */        else {
/* 327:327 */          this.lastUsedMethod = 5;
/* 328:    */        }
/* 329:    */      }
/* 330:    */    }
/* 331:    */    
/* 334:334 */    if (i != 0)
/* 335:    */    {
/* 339:339 */      paramBoolean.add(localVector3f3, localVector3f4);
/* 340:340 */      paramResult.addContactPoint(localVector3f1, paramBoolean, f1);
/* 341:    */      
/* 344:344 */      this.maxDepth = Math.max(f1, this.maxDepth);
/* 345:345 */      this.contacts += 1;
/* 346:    */    }
/* 347:    */  }
/* 348:    */  
/* 349:    */  public void setMinkowskiA(ConvexShape paramConvexShape)
/* 350:    */  {
/* 351:351 */    this.minkowskiA = paramConvexShape;
/* 352:    */  }
/* 353:    */  
/* 354:    */  public void setMinkowskiB(ConvexShape paramConvexShape) {
/* 355:355 */    this.minkowskiB = paramConvexShape;
/* 356:    */  }
/* 357:    */  
/* 358:    */  public void setCachedSeperatingAxis(Vector3f paramVector3f) {
/* 359:359 */    this.cachedSeparatingAxis.set(paramVector3f);
/* 360:    */  }
/* 361:    */  
/* 362:    */  public void setPenetrationDepthSolver(ConvexPenetrationDepthSolver paramConvexPenetrationDepthSolver) {
/* 363:363 */    this.penetrationDepthSolver = paramConvexPenetrationDepthSolver;
/* 364:    */  }
/* 365:    */  
/* 368:    */  public void setIgnoreMargin(boolean paramBoolean)
/* 369:    */  {
/* 370:370 */    this.ignoreMargin = paramBoolean;
/* 371:    */  }
/* 372:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.GjkPairDetectorExt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */