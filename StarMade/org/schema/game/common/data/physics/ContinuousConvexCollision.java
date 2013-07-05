/*     */ package org.schema.game.common.data.physics;
/*     */ 
/*     */ import com.bulletphysics.collision.narrowphase.ConvexCast.CastResult;
/*     */ import com.bulletphysics.collision.narrowphase.ConvexPenetrationDepthSolver;
/*     */ import com.bulletphysics.collision.narrowphase.DiscreteCollisionDetectorInterface.ClosestPointInput;
/*     */ import com.bulletphysics.collision.narrowphase.PointCollector;
/*     */ import com.bulletphysics.collision.narrowphase.SimplexSolverInterface;
/*     */ import com.bulletphysics.collision.shapes.ConvexShape;
/*     */ import com.bulletphysics.collision.shapes.StaticPlaneShape;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.util.ObjectPool;
/*     */ import java.io.PrintStream;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Tuple3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.common.util.linAlg.TransformTools;
/*     */ import p;
/*     */ 
/*     */ public class ContinuousConvexCollision
/*     */ {
/*     */   private SimplexSolverInterface simplexSolver;
/*     */   private ConvexPenetrationDepthSolver penetrationDepthSolver;
/*     */   private ConvexShape convexA;
/*     */   private ConvexShape convexB1;
/*     */   private StaticPlaneShape planeShape;
/*     */   static final int MAX_ITERATIONS = 64;
/*  33 */   protected final ObjectPool pointInputsPool = ObjectPool.get(DiscreteCollisionDetectorInterface.ClosestPointInput.class);
/*     */ 
/*     */   public ContinuousConvexCollision(ConvexShape paramConvexShape1, ConvexShape paramConvexShape2, SimplexSolverInterface paramSimplexSolverInterface, ConvexPenetrationDepthSolver paramConvexPenetrationDepthSolver)
/*     */   {
/*  41 */     this.simplexSolver = paramSimplexSolverInterface;
/*  42 */     this.penetrationDepthSolver = paramConvexPenetrationDepthSolver;
/*  43 */     this.convexA = paramConvexShape1;
/*  44 */     this.convexB1 = paramConvexShape2;
/*  45 */     this.planeShape = null;
/*     */   }
/*     */ 
/*     */   public ContinuousConvexCollision(ConvexShape paramConvexShape, StaticPlaneShape paramStaticPlaneShape)
/*     */   {
/*  50 */     this.convexA = paramConvexShape;
/*  51 */     this.planeShape = paramStaticPlaneShape;
/*     */   }
/*     */ 
/*     */   boolean calcTimeOfImpact(Transform paramTransform1, Transform paramTransform2, Transform paramTransform3, Transform paramTransform4, ConvexCast.CastResult paramCastResult, GjkPairDetectorVariables paramGjkPairDetectorVariables)
/*     */   {
/*  63 */     Vector3f localVector3f1 = new Vector3f();
/*  64 */     Vector3f localVector3f2 = new Vector3f();
/*  65 */     Vector3f localVector3f3 = new Vector3f();
/*  66 */     Vector3f localVector3f4 = new Vector3f();
/*     */ 
/*  68 */     TransformTools.a(paramTransform1, paramTransform2, 1.0F, localVector3f1, localVector3f2, paramGjkPairDetectorVariables.axis, paramGjkPairDetectorVariables.tmp2, paramGjkPairDetectorVariables.dmat, paramGjkPairDetectorVariables.dorn);
/*  69 */     TransformTools.a(paramTransform3, paramTransform4, 1.0F, localVector3f3, localVector3f4, paramGjkPairDetectorVariables.axis, paramGjkPairDetectorVariables.tmp2, paramGjkPairDetectorVariables.dmat, paramGjkPairDetectorVariables.dorn);
/*     */ 
/*  71 */     if ((p.a(localVector3f1)) || (p.a(localVector3f3))) {
/*  72 */       System.err.println("WARNING: LINEAR VELOCITY WAS NAN: " + localVector3f1 + "; " + localVector3f3);
/*  73 */       return false;
/*     */     }
/*     */ 
/*  76 */     paramTransform2 = this.convexA.getAngularMotionDisc();
/*  77 */     paramTransform4 = this.convexB1 != null ? this.convexB1.getAngularMotionDisc() : 0.0F;
/*     */ 
/*  79 */     paramTransform2 = localVector3f2.length() * paramTransform2 + localVector3f4.length() * paramTransform4;
/*     */ 
/*  81 */     (
/*  83 */       paramTransform4 = new Vector3f())
/*  83 */       .sub(localVector3f3, localVector3f1);
/*     */ 
/*  87 */     if (paramTransform4.length() + 
/*  87 */       paramTransform2 == 0.0F)
/*     */     {
/*  95 */       return false;
/*     */     }
/*     */ 
/* 100 */     float f1 = 0.0F;
/* 101 */     new Vector3f(1.0F, 0.0F, 0.0F);
/*     */ 
/* 103 */     Vector3f localVector3f5 = new Vector3f(0.0F, 0.0F, 0.0F);
/*     */ 
/* 106 */     float f4 = 0.0F;
/*     */ 
/* 112 */     int i = 0;
/*     */ 
/* 116 */     PointCollector localPointCollector = new PointCollector();
/*     */ 
/* 123 */     computeClosestPoints(paramTransform1, paramTransform3, localPointCollector, paramGjkPairDetectorVariables);
/*     */ 
/* 125 */     boolean bool = localPointCollector.hasResult;
/* 126 */     Object localObject1 = localPointCollector.pointInWorld;
/*     */ 
/* 131 */     if (bool)
/*     */     {
/* 144 */       float f2 = localPointCollector.distance + paramCastResult.allowedPenetration;
/*     */ 
/* 146 */       localVector3f5.set(localPointCollector.normalOnBInWorld);
/*     */ 
/* 150 */       if (paramTransform4.dot(localVector3f5) + 
/* 150 */         paramTransform2 <= 1.192093E-007F)
/*     */       {
/* 162 */         return false;
/*     */       }
/*     */ 
/* 169 */       while (f2 > 0.001F)
/*     */       {
/*     */         float f5;
/* 181 */         if ((
/* 181 */           f5 = paramTransform4.dot(localVector3f5)) + 
/* 181 */           paramTransform2 <= 1.192093E-007F)
/*     */         {
/* 192 */           return false;
/*     */         }
/*     */ 
/* 196 */         f2 /= (f5 + paramTransform2);
/*     */ 
/* 202 */         if ((
/* 202 */           f1 = f1 + f2) > 
/* 202 */           1.0F)
/*     */         {
/* 212 */           return false;
/*     */         }
/*     */ 
/* 216 */         if (f1 < 0.0F)
/*     */         {
/* 223 */           System.err.println("HAS RESULT: BUT LAMDA IS TO SMALL " + f1);
/* 224 */           return false;
/*     */         }
/*     */ 
/* 230 */         if (f1 <= f4)
/*     */         {
/* 238 */           System.err.println("HAS RESULT: BUT LAST LAMDA IS <= LAST LAMBDA");
/* 239 */           return false;
/*     */         }
/*     */ 
/* 243 */         f4 = f1;
/*     */         Transform localTransform;
/* 248 */         (
/* 249 */           localTransform = new Transform())
/* 249 */           .setIdentity();
/* 250 */         (
/* 251 */           localObject1 = new Transform())
/* 251 */           .setIdentity();
/*     */ 
/* 255 */         TransformTools.a(paramTransform1, localVector3f1, localVector3f2, f1, localTransform, paramGjkPairDetectorVariables.iAxis, paramGjkPairDetectorVariables.iDorn, paramGjkPairDetectorVariables.iorn0, paramGjkPairDetectorVariables.iPredictOrn, paramGjkPairDetectorVariables.float4Temp);
/* 256 */         TransformTools.a(paramTransform3, localVector3f3, localVector3f4, f1, (Transform)localObject1, paramGjkPairDetectorVariables.iAxis, paramGjkPairDetectorVariables.iDorn, paramGjkPairDetectorVariables.iorn0, paramGjkPairDetectorVariables.iPredictOrn, paramGjkPairDetectorVariables.float4Temp);
/*     */ 
/* 258 */         (
/* 259 */           localObject2 = new Transform())
/* 259 */           .set((Transform)localObject1);
/* 260 */         ((Transform)localObject2).inverse();
/* 261 */         ((Transform)localObject2).mul(localTransform);
/*     */ 
/* 270 */         Object localObject2 = new PointCollector();
/* 271 */         computeClosestPoints(localTransform, (Transform)localObject1, (PointCollector)localObject2, paramGjkPairDetectorVariables);
/*     */ 
/* 273 */         if (((PointCollector)localObject2).hasResult)
/*     */         {
/* 275 */           float f3 = ((PointCollector)localObject2).distance + paramCastResult.allowedPenetration;
/* 276 */           localObject1 = ((PointCollector)localObject2).pointInWorld;
/* 277 */           localVector3f5.set(((PointCollector)localObject2).normalOnBInWorld);
/*     */         }
/*     */         else
/*     */         {
/* 286 */           System.err.println("POINT HAS NO RESULT: -1 " + i);
/*     */ 
/* 288 */           return false;
/*     */         }
/*     */ 
/* 291 */         i++;
/* 292 */         if (i > 64)
/*     */         {
/* 302 */           return false;
/*     */         }
/*     */       }
/*     */ 
/* 306 */       paramCastResult.fraction = f1;
/* 307 */       paramCastResult.normal.set(localVector3f5);
/* 308 */       paramCastResult.hitPoint.set((Tuple3f)localObject1);
/* 309 */       return true;
/*     */     }
/*     */ 
/* 319 */     return false;
/*     */   }
/*     */ 
/*     */   void computeClosestPoints(Transform paramTransform1, Transform paramTransform2, PointCollector paramPointCollector, GjkPairDetectorVariables paramGjkPairDetectorVariables)
/*     */   {
/*     */     Object localObject1;
/* 325 */     if (this.convexB1 != null)
/*     */     {
/* 327 */       this.simplexSolver.reset();
/* 328 */       (
/* 329 */         paramGjkPairDetectorVariables = new GjkPairDetectorExt(paramGjkPairDetectorVariables))
/* 329 */         .init(this.convexA, this.convexB1, this.simplexSolver, this.penetrationDepthSolver);
/* 330 */       (
/* 331 */         localObject1 = (DiscreteCollisionDetectorInterface.ClosestPointInput)this.pointInputsPool.get())
/* 331 */         .init();
/* 332 */       ((DiscreteCollisionDetectorInterface.ClosestPointInput)localObject1).transformA.set(paramTransform1);
/* 333 */       ((DiscreteCollisionDetectorInterface.ClosestPointInput)localObject1).transformB.set(paramTransform2);
/*     */ 
/* 335 */       paramGjkPairDetectorVariables.getClosestPoints((DiscreteCollisionDetectorInterface.ClosestPointInput)localObject1, paramPointCollector, null);
/* 336 */       this.pointInputsPool.release(localObject1);
/* 337 */       return;
/*     */     }
/*     */ 
/* 340 */     paramGjkPairDetectorVariables = this.convexA;
/*     */ 
/* 343 */     Vector3f localVector3f2 = (
/* 343 */       localObject1 = this.planeShape)
/* 343 */       .getPlaneNormal(new Vector3f());
/*     */ 
/* 345 */     float f = ((StaticPlaneShape)localObject1).getPlaneConstant();
/*     */     Object localObject2;
/* 347 */     (
/* 349 */       localObject2 = new Transform())
/* 349 */       .set(paramTransform2);
/* 350 */     ((Transform)localObject2).inverse();
/* 351 */     ((Transform)localObject2).mul(paramTransform1);
/*     */     Object localObject3;
/* 352 */     (
/* 353 */       localObject3 = new Transform())
/* 353 */       .set(paramTransform1);
/* 354 */     ((Transform)localObject3).inverse();
/* 355 */     ((Transform)localObject3).mul(paramTransform2);
/*     */ 
/* 357 */     (
/* 358 */       paramTransform1 = new Matrix3f())
/* 358 */       .set(((Transform)localObject3).basis);
/* 359 */     (
/* 360 */       localObject3 = new Vector3f(localVector3f2))
/* 360 */       .scale(-1.0F);
/* 361 */     paramTransform1.transform((Tuple3f)localObject3);
/*     */ 
/* 363 */     paramTransform1 = paramGjkPairDetectorVariables.localGetSupportingVertex((Vector3f)localObject3, new Vector3f());
/*     */ 
/* 365 */     (
/* 366 */       paramGjkPairDetectorVariables = new Vector3f())
/* 366 */       .set(paramTransform1);
/* 367 */     ((Transform)localObject2).transform(paramTransform1);
/* 368 */     paramTransform1 = localVector3f2.dot(paramGjkPairDetectorVariables) - f;
/*     */ 
/* 370 */     Vector3f localVector3f1 = new Vector3f();
/* 371 */     (
/* 372 */       localObject2 = new Vector3f(localVector3f2))
/* 372 */       .scale(paramTransform1);
/* 373 */     localVector3f1.sub(paramGjkPairDetectorVariables, (Tuple3f)localObject2);
/*     */ 
/* 375 */     paramGjkPairDetectorVariables = new Vector3f(localVector3f1);
/* 376 */     paramTransform2.transform(paramGjkPairDetectorVariables);
/* 377 */     localVector3f1 = new Vector3f(localVector3f2);
/* 378 */     paramTransform2.basis.transform(localVector3f1);
/*     */ 
/* 380 */     paramPointCollector.addContactPoint(localVector3f1, paramGjkPairDetectorVariables, paramTransform1);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.ContinuousConvexCollision
 * JD-Core Version:    0.6.2
 */