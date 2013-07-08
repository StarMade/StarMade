/*   1:    */package org.schema.game.common.data.physics;
/*   2:    */
/*   3:    */import com.bulletphysics.collision.narrowphase.ConvexCast.CastResult;
/*   4:    */import com.bulletphysics.collision.narrowphase.ConvexPenetrationDepthSolver;
/*   5:    */import com.bulletphysics.collision.narrowphase.DiscreteCollisionDetectorInterface.ClosestPointInput;
/*   6:    */import com.bulletphysics.collision.narrowphase.PointCollector;
/*   7:    */import com.bulletphysics.collision.narrowphase.SimplexSolverInterface;
/*   8:    */import com.bulletphysics.collision.shapes.ConvexShape;
/*   9:    */import com.bulletphysics.collision.shapes.StaticPlaneShape;
/*  10:    */import com.bulletphysics.linearmath.Transform;
/*  11:    */import com.bulletphysics.util.ObjectPool;
/*  12:    */import java.io.PrintStream;
/*  13:    */import javax.vecmath.Matrix3f;
/*  14:    */import javax.vecmath.Tuple3f;
/*  15:    */import javax.vecmath.Vector3f;
/*  16:    */import org.schema.common.util.linAlg.TransformTools;
/*  17:    */import p;
/*  18:    */
/*  25:    */public class ContinuousConvexCollision
/*  26:    */{
/*  27:    */  private SimplexSolverInterface simplexSolver;
/*  28:    */  private ConvexPenetrationDepthSolver penetrationDepthSolver;
/*  29:    */  private ConvexShape convexA;
/*  30:    */  private ConvexShape convexB1;
/*  31:    */  private StaticPlaneShape planeShape;
/*  32:    */  static final int MAX_ITERATIONS = 64;
/*  33: 33 */  protected final ObjectPool pointInputsPool = ObjectPool.get(DiscreteCollisionDetectorInterface.ClosestPointInput.class);
/*  34:    */  
/*  39:    */  public ContinuousConvexCollision(ConvexShape paramConvexShape1, ConvexShape paramConvexShape2, SimplexSolverInterface paramSimplexSolverInterface, ConvexPenetrationDepthSolver paramConvexPenetrationDepthSolver)
/*  40:    */  {
/*  41: 41 */    this.simplexSolver = paramSimplexSolverInterface;
/*  42: 42 */    this.penetrationDepthSolver = paramConvexPenetrationDepthSolver;
/*  43: 43 */    this.convexA = paramConvexShape1;
/*  44: 44 */    this.convexB1 = paramConvexShape2;
/*  45: 45 */    this.planeShape = null;
/*  46:    */  }
/*  47:    */  
/*  48:    */  public ContinuousConvexCollision(ConvexShape paramConvexShape, StaticPlaneShape paramStaticPlaneShape)
/*  49:    */  {
/*  50: 50 */    this.convexA = paramConvexShape;
/*  51: 51 */    this.planeShape = paramStaticPlaneShape;
/*  52:    */  }
/*  53:    */  
/*  61:    */  boolean calcTimeOfImpact(Transform paramTransform1, Transform paramTransform2, Transform paramTransform3, Transform paramTransform4, ConvexCast.CastResult paramCastResult, GjkPairDetectorVariables paramGjkPairDetectorVariables)
/*  62:    */  {
/*  63: 63 */    Vector3f localVector3f1 = new Vector3f();
/*  64: 64 */    Vector3f localVector3f2 = new Vector3f();
/*  65: 65 */    Vector3f localVector3f3 = new Vector3f();
/*  66: 66 */    Vector3f localVector3f4 = new Vector3f();
/*  67:    */    
/*  68: 68 */    TransformTools.a(paramTransform1, paramTransform2, 1.0F, localVector3f1, localVector3f2, paramGjkPairDetectorVariables.axis, paramGjkPairDetectorVariables.tmp2, paramGjkPairDetectorVariables.dmat, paramGjkPairDetectorVariables.dorn);
/*  69: 69 */    TransformTools.a(paramTransform3, paramTransform4, 1.0F, localVector3f3, localVector3f4, paramGjkPairDetectorVariables.axis, paramGjkPairDetectorVariables.tmp2, paramGjkPairDetectorVariables.dmat, paramGjkPairDetectorVariables.dorn);
/*  70:    */    
/*  71: 71 */    if ((p.a(localVector3f1)) || (p.a(localVector3f3))) {
/*  72: 72 */      System.err.println("WARNING: LINEAR VELOCITY WAS NAN: " + localVector3f1 + "; " + localVector3f3);
/*  73: 73 */      return false;
/*  74:    */    }
/*  75:    */    
/*  76: 76 */    paramTransform2 = this.convexA.getAngularMotionDisc();
/*  77: 77 */    paramTransform4 = this.convexB1 != null ? this.convexB1.getAngularMotionDisc() : 0.0F;
/*  78:    */    
/*  79: 79 */    paramTransform2 = localVector3f2.length() * paramTransform2 + localVector3f4.length() * paramTransform4;
/*  80:    */    
/*  81: 81 */    (
/*  82:    */    
/*  83: 83 */      paramTransform4 = new Vector3f()).sub(localVector3f3, localVector3f1);
/*  84:    */    
/*  87: 87 */    if (paramTransform4.length() + paramTransform2 == 0.0F)
/*  88:    */    {
/*  95: 95 */      return false;
/*  96:    */    }
/*  97:    */    
/* 100:100 */    float f1 = 0.0F;
/* 101:101 */    new Vector3f(1.0F, 0.0F, 0.0F);
/* 102:    */    
/* 103:103 */    Vector3f localVector3f5 = new Vector3f(0.0F, 0.0F, 0.0F);
/* 104:    */    
/* 106:106 */    float f4 = 0.0F;
/* 107:    */    
/* 112:112 */    int i = 0;
/* 113:    */    
/* 116:116 */    PointCollector localPointCollector = new PointCollector();
/* 117:    */    
/* 123:123 */    computeClosestPoints(paramTransform1, paramTransform3, localPointCollector, paramGjkPairDetectorVariables);
/* 124:    */    
/* 125:125 */    boolean bool = localPointCollector.hasResult;
/* 126:126 */    Object localObject1 = localPointCollector.pointInWorld;
/* 127:    */    
/* 131:131 */    if (bool)
/* 132:    */    {
/* 144:144 */      float f2 = localPointCollector.distance + paramCastResult.allowedPenetration;
/* 145:    */      
/* 146:146 */      localVector3f5.set(localPointCollector.normalOnBInWorld);
/* 147:    */      
/* 150:150 */      if (paramTransform4.dot(localVector3f5) + paramTransform2 <= 1.192093E-007F)
/* 151:    */      {
/* 162:162 */        return false;
/* 163:    */      }
/* 164:    */      
/* 169:169 */      while (f2 > 0.001F)
/* 170:    */      {
/* 171:    */        float f5;
/* 172:    */        
/* 181:181 */        if ((f5 = paramTransform4.dot(localVector3f5)) + paramTransform2 <= 1.192093E-007F)
/* 182:    */        {
/* 192:192 */          return false;
/* 193:    */        }
/* 194:    */        
/* 196:196 */        f2 /= (f5 + paramTransform2);
/* 197:    */        
/* 202:202 */        if ((f1 = f1 + f2) > 1.0F)
/* 203:    */        {
/* 212:212 */          return false;
/* 213:    */        }
/* 214:    */        
/* 216:216 */        if (f1 < 0.0F)
/* 217:    */        {
/* 223:223 */          System.err.println("HAS RESULT: BUT LAMDA IS TO SMALL " + f1);
/* 224:224 */          return false;
/* 225:    */        }
/* 226:    */        
/* 230:230 */        if (f1 <= f4)
/* 231:    */        {
/* 238:238 */          System.err.println("HAS RESULT: BUT LAST LAMDA IS <= LAST LAMBDA");
/* 239:239 */          return false;
/* 240:    */        }
/* 241:    */        
/* 243:243 */        f4 = f1;
/* 244:    */        
/* 246:    */        Transform localTransform;
/* 247:    */        
/* 249:249 */        (localTransform = new Transform()).setIdentity();
/* 250:250 */        (
/* 251:251 */          localObject1 = new Transform()).setIdentity();
/* 252:    */        
/* 255:255 */        TransformTools.a(paramTransform1, localVector3f1, localVector3f2, f1, localTransform, paramGjkPairDetectorVariables.iAxis, paramGjkPairDetectorVariables.iDorn, paramGjkPairDetectorVariables.iorn0, paramGjkPairDetectorVariables.iPredictOrn, paramGjkPairDetectorVariables.float4Temp);
/* 256:256 */        TransformTools.a(paramTransform3, localVector3f3, localVector3f4, f1, (Transform)localObject1, paramGjkPairDetectorVariables.iAxis, paramGjkPairDetectorVariables.iDorn, paramGjkPairDetectorVariables.iorn0, paramGjkPairDetectorVariables.iPredictOrn, paramGjkPairDetectorVariables.float4Temp);
/* 257:    */        
/* 258:258 */        (
/* 259:259 */          localObject2 = new Transform()).set((Transform)localObject1);
/* 260:260 */        ((Transform)localObject2).inverse();
/* 261:261 */        ((Transform)localObject2).mul(localTransform);
/* 262:    */        
/* 270:270 */        Object localObject2 = new PointCollector();
/* 271:271 */        computeClosestPoints(localTransform, (Transform)localObject1, (PointCollector)localObject2, paramGjkPairDetectorVariables);
/* 272:    */        
/* 273:273 */        if (((PointCollector)localObject2).hasResult)
/* 274:    */        {
/* 275:275 */          float f3 = ((PointCollector)localObject2).distance + paramCastResult.allowedPenetration;
/* 276:276 */          localObject1 = ((PointCollector)localObject2).pointInWorld;
/* 277:277 */          localVector3f5.set(((PointCollector)localObject2).normalOnBInWorld);
/* 281:    */        }
/* 282:    */        else
/* 283:    */        {
/* 286:286 */          System.err.println("POINT HAS NO RESULT: -1 " + i);
/* 287:    */          
/* 288:288 */          return false;
/* 289:    */        }
/* 290:    */        
/* 291:291 */        i++;
/* 292:292 */        if (i > 64)
/* 293:    */        {
/* 302:302 */          return false;
/* 303:    */        }
/* 304:    */      }
/* 305:    */      
/* 306:306 */      paramCastResult.fraction = f1;
/* 307:307 */      paramCastResult.normal.set(localVector3f5);
/* 308:308 */      paramCastResult.hitPoint.set((Tuple3f)localObject1);
/* 309:309 */      return true;
/* 310:    */    }
/* 311:    */    
/* 319:319 */    return false;
/* 320:    */  }
/* 321:    */  
/* 322:    */  void computeClosestPoints(Transform paramTransform1, Transform paramTransform2, PointCollector paramPointCollector, GjkPairDetectorVariables paramGjkPairDetectorVariables)
/* 323:    */  {
/* 324:    */    Object localObject1;
/* 325:325 */    if (this.convexB1 != null)
/* 326:    */    {
/* 327:327 */      this.simplexSolver.reset();
/* 328:328 */      (
/* 329:329 */        paramGjkPairDetectorVariables = new GjkPairDetectorExt(paramGjkPairDetectorVariables)).init(this.convexA, this.convexB1, this.simplexSolver, this.penetrationDepthSolver);
/* 330:330 */      (
/* 331:331 */        localObject1 = (DiscreteCollisionDetectorInterface.ClosestPointInput)this.pointInputsPool.get()).init();
/* 332:332 */      ((DiscreteCollisionDetectorInterface.ClosestPointInput)localObject1).transformA.set(paramTransform1);
/* 333:333 */      ((DiscreteCollisionDetectorInterface.ClosestPointInput)localObject1).transformB.set(paramTransform2);
/* 334:    */      
/* 335:335 */      paramGjkPairDetectorVariables.getClosestPoints((DiscreteCollisionDetectorInterface.ClosestPointInput)localObject1, paramPointCollector, null);
/* 336:336 */      this.pointInputsPool.release(localObject1);
/* 337:337 */      return;
/* 338:    */    }
/* 339:    */    
/* 340:340 */    paramGjkPairDetectorVariables = this.convexA;
/* 341:    */    
/* 343:343 */    Vector3f localVector3f2 = (localObject1 = this.planeShape).getPlaneNormal(new Vector3f());
/* 344:    */    
/* 345:345 */    float f = ((StaticPlaneShape)localObject1).getPlaneConstant();
/* 346:    */    
/* 347:    */    Object localObject2;
/* 348:    */    
/* 349:349 */    (localObject2 = new Transform()).set(paramTransform2);
/* 350:350 */    ((Transform)localObject2).inverse();
/* 351:351 */    ((Transform)localObject2).mul(paramTransform1);
/* 352:    */    Object localObject3;
/* 353:353 */    (localObject3 = new Transform()).set(paramTransform1);
/* 354:354 */    ((Transform)localObject3).inverse();
/* 355:355 */    ((Transform)localObject3).mul(paramTransform2);
/* 356:    */    
/* 357:357 */    (
/* 358:358 */      paramTransform1 = new Matrix3f()).set(((Transform)localObject3).basis);
/* 359:359 */    (
/* 360:360 */      localObject3 = new Vector3f(localVector3f2)).scale(-1.0F);
/* 361:361 */    paramTransform1.transform((Tuple3f)localObject3);
/* 362:    */    
/* 363:363 */    paramTransform1 = paramGjkPairDetectorVariables.localGetSupportingVertex((Vector3f)localObject3, new Vector3f());
/* 364:    */    
/* 365:365 */    (
/* 366:366 */      paramGjkPairDetectorVariables = new Vector3f()).set(paramTransform1);
/* 367:367 */    ((Transform)localObject2).transform(paramTransform1);
/* 368:368 */    paramTransform1 = localVector3f2.dot(paramGjkPairDetectorVariables) - f;
/* 369:    */    
/* 370:370 */    Vector3f localVector3f1 = new Vector3f();
/* 371:371 */    (
/* 372:372 */      localObject2 = new Vector3f(localVector3f2)).scale(paramTransform1);
/* 373:373 */    localVector3f1.sub(paramGjkPairDetectorVariables, (Tuple3f)localObject2);
/* 374:    */    
/* 375:375 */    paramGjkPairDetectorVariables = new Vector3f(localVector3f1);
/* 376:376 */    paramTransform2.transform(paramGjkPairDetectorVariables);
/* 377:377 */    localVector3f1 = new Vector3f(localVector3f2);
/* 378:378 */    paramTransform2.basis.transform(localVector3f1);
/* 379:    */    
/* 380:380 */    paramPointCollector.addContactPoint(localVector3f1, paramGjkPairDetectorVariables, paramTransform1);
/* 381:    */  }
/* 382:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.ContinuousConvexCollision
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */