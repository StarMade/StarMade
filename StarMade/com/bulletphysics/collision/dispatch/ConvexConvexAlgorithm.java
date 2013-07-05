/*     */ package com.bulletphysics.collision.dispatch;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*     */ import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
/*     */ import com.bulletphysics.collision.broadphase.Dispatcher;
/*     */ import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*     */ import com.bulletphysics.collision.narrowphase.ConvexCast.CastResult;
/*     */ import com.bulletphysics.collision.narrowphase.ConvexPenetrationDepthSolver;
/*     */ import com.bulletphysics.collision.narrowphase.DiscreteCollisionDetectorInterface.ClosestPointInput;
/*     */ import com.bulletphysics.collision.narrowphase.GjkConvexCast;
/*     */ import com.bulletphysics.collision.narrowphase.GjkPairDetector;
/*     */ import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*     */ import com.bulletphysics.collision.narrowphase.SimplexSolverInterface;
/*     */ import com.bulletphysics.collision.narrowphase.VoronoiSimplexSolver;
/*     */ import com.bulletphysics.collision.shapes.ConvexShape;
/*     */ import com.bulletphysics.collision.shapes.SphereShape;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import com.bulletphysics.util.ObjectPool;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class ConvexConvexAlgorithm extends CollisionAlgorithm
/*     */ {
/*     */   protected final ObjectPool<DiscreteCollisionDetectorInterface.ClosestPointInput> pointInputsPool;
/*     */   private GjkPairDetector gjkPairDetector;
/*     */   public boolean ownManifold;
/*     */   public PersistentManifold manifoldPtr;
/*     */   public boolean lowLevelOfDetail;
/* 137 */   private static boolean disableCcd = false;
/*     */ 
/*     */   public ConvexConvexAlgorithm()
/*     */   {
/*  53 */     this.pointInputsPool = ObjectPool.get(DiscreteCollisionDetectorInterface.ClosestPointInput.class);
/*     */ 
/*  55 */     this.gjkPairDetector = new GjkPairDetector();
/*     */   }
/*     */ 
/*     */   public void init(PersistentManifold mf, CollisionAlgorithmConstructionInfo ci, CollisionObject body0, CollisionObject body1, SimplexSolverInterface simplexSolver, ConvexPenetrationDepthSolver pdSolver)
/*     */   {
/*  62 */     super.init(ci);
/*  63 */     this.gjkPairDetector.init(null, null, simplexSolver, pdSolver);
/*  64 */     this.manifoldPtr = mf;
/*  65 */     this.ownManifold = false;
/*  66 */     this.lowLevelOfDetail = false;
/*     */   }
/*     */ 
/*     */   public void destroy()
/*     */   {
/*  71 */     if (this.ownManifold) {
/*  72 */       if (this.manifoldPtr != null) {
/*  73 */         this.dispatcher.releaseManifold(this.manifoldPtr);
/*     */       }
/*  75 */       this.manifoldPtr = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setLowLevelOfDetail(boolean useLowLevel) {
/*  80 */     this.lowLevelOfDetail = useLowLevel;
/*     */   }
/*     */ 
/*     */   public void processCollision(CollisionObject body0, CollisionObject body1, DispatcherInfo dispatchInfo, ManifoldResult resultOut)
/*     */   {
/*  88 */     if (this.manifoldPtr == null)
/*     */     {
/*  90 */       this.manifoldPtr = this.dispatcher.getNewManifold(body0, body1);
/*  91 */       this.ownManifold = true;
/*     */     }
/*  93 */     resultOut.setPersistentManifold(this.manifoldPtr);
/*     */ 
/* 109 */     ConvexShape min0 = (ConvexShape)body0.getCollisionShape();
/* 110 */     ConvexShape min1 = (ConvexShape)body1.getCollisionShape();
/*     */ 
/* 112 */     DiscreteCollisionDetectorInterface.ClosestPointInput input = (DiscreteCollisionDetectorInterface.ClosestPointInput)this.pointInputsPool.get();
/* 113 */     input.init();
/*     */ 
/* 116 */     this.gjkPairDetector.setMinkowskiA(min0);
/* 117 */     this.gjkPairDetector.setMinkowskiB(min1);
/* 118 */     input.maximumDistanceSquared = (min0.getMargin() + min1.getMargin() + this.manifoldPtr.getContactBreakingThreshold());
/* 119 */     input.maximumDistanceSquared *= input.maximumDistanceSquared;
/*     */ 
/* 124 */     body0.getWorldTransform(input.transformA);
/* 125 */     body1.getWorldTransform(input.transformB);
/*     */ 
/* 127 */     this.gjkPairDetector.getClosestPoints(input, resultOut, dispatchInfo.debugDraw);
/*     */ 
/* 129 */     this.pointInputsPool.release(input);
/*     */ 
/* 132 */     if (this.ownManifold)
/* 133 */       resultOut.refreshContactPoints();
/*     */   }
/*     */ 
/*     */   public float calculateTimeOfImpact(CollisionObject arg1, CollisionObject arg2, DispatcherInfo arg3, ManifoldResult arg4)
/*     */   {
/* 141 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform(); tmp7_5.push$javax$vecmath$Vector3f(); Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 143 */       Transform tmpTrans1 = localStack.get$com$bulletphysics$linearmath$Transform();
/* 144 */       Transform tmpTrans2 = localStack.get$com$bulletphysics$linearmath$Transform();
/*     */ 
/* 150 */       float resultFraction = 1.0F;
/*     */ 
/* 152 */       tmp.sub(col0.getInterpolationWorldTransform(tmpTrans1).origin, col0.getWorldTransform(tmpTrans2).origin);
/* 153 */       float squareMot0 = tmp.lengthSquared();
/*     */ 
/* 155 */       tmp.sub(col1.getInterpolationWorldTransform(tmpTrans1).origin, col1.getWorldTransform(tmpTrans2).origin);
/* 156 */       float squareMot1 = tmp.lengthSquared();
/*     */ 
/* 158 */       if ((squareMot0 < col0.getCcdSquareMotionThreshold()) && (squareMot1 < col1.getCcdSquareMotionThreshold()))
/*     */       {
/* 160 */         return resultFraction;
/*     */       }
/*     */ 
/* 163 */       if (disableCcd) {
/* 164 */         return 1.0F;
/*     */       }
/*     */ 
/* 167 */       Transform tmpTrans3 = localStack.get$com$bulletphysics$linearmath$Transform();
/* 168 */       Transform tmpTrans4 = localStack.get$com$bulletphysics$linearmath$Transform();
/*     */ 
/* 178 */       ConvexShape convex0 = (ConvexShape)col0.getCollisionShape();
/*     */ 
/* 180 */       SphereShape sphere1 = new SphereShape(col1.getCcdSweptSphereRadius());
/* 181 */       ConvexCast.CastResult result = new ConvexCast.CastResult();
/* 182 */       VoronoiSimplexSolver voronoiSimplex = new VoronoiSimplexSolver();
/*     */ 
/* 185 */       GjkConvexCast ccd1 = new GjkConvexCast(convex0, sphere1, voronoiSimplex);
/*     */ 
/* 187 */       if (ccd1.calcTimeOfImpact(col0.getWorldTransform(tmpTrans1), col0.getInterpolationWorldTransform(tmpTrans2), col1.getWorldTransform(tmpTrans3), col1.getInterpolationWorldTransform(tmpTrans4), result))
/*     */       {
/* 191 */         if (col0.getHitFraction() > result.fraction) {
/* 192 */           col0.setHitFraction(result.fraction);
/*     */         }
/*     */ 
/* 195 */         if (col1.getHitFraction() > result.fraction) {
/* 196 */           col1.setHitFraction(result.fraction);
/*     */         }
/*     */ 
/* 199 */         if (resultFraction > result.fraction) {
/* 200 */           resultFraction = result.fraction;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 207 */       ConvexShape convex1 = (ConvexShape)col1.getCollisionShape();
/*     */ 
/* 209 */       SphereShape sphere0 = new SphereShape(col0.getCcdSweptSphereRadius());
/* 210 */       ConvexCast.CastResult result = new ConvexCast.CastResult();
/* 211 */       VoronoiSimplexSolver voronoiSimplex = new VoronoiSimplexSolver();
/*     */ 
/* 214 */       GjkConvexCast ccd1 = new GjkConvexCast(sphere0, convex1, voronoiSimplex);
/*     */ 
/* 216 */       if (ccd1.calcTimeOfImpact(col0.getWorldTransform(tmpTrans1), col0.getInterpolationWorldTransform(tmpTrans2), col1.getWorldTransform(tmpTrans3), col1.getInterpolationWorldTransform(tmpTrans4), result))
/*     */       {
/* 220 */         if (col0.getHitFraction() > result.fraction) {
/* 221 */           col0.setHitFraction(result.fraction);
/*     */         }
/*     */ 
/* 224 */         if (col1.getHitFraction() > result.fraction) {
/* 225 */           col1.setHitFraction(result.fraction);
/* 228 */         }
/*     */ if (resultFraction <= result.fraction);
/*     */       }
/* 229 */       return result.fraction;
/*     */     }
/*     */     finally
/*     */     {
/* 235 */       .Stack tmp477_475 = localStack; tmp477_475.pop$com$bulletphysics$linearmath$Transform(); tmp477_475.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void getAllContactManifolds(ObjectArrayList<PersistentManifold> manifoldArray)
/*     */   {
/* 241 */     if ((this.manifoldPtr != null) && (this.ownManifold))
/* 242 */       manifoldArray.add(this.manifoldPtr);
/*     */   }
/*     */ 
/*     */   public PersistentManifold getManifold()
/*     */   {
/* 247 */     return this.manifoldPtr;
/*     */   }
/*     */ 
/*     */   public static class CreateFunc extends CollisionAlgorithmCreateFunc
/*     */   {
/* 253 */     private final ObjectPool<ConvexConvexAlgorithm> pool = ObjectPool.get(ConvexConvexAlgorithm.class);
/*     */     public ConvexPenetrationDepthSolver pdSolver;
/*     */     public SimplexSolverInterface simplexSolver;
/*     */ 
/*     */     public CreateFunc(SimplexSolverInterface simplexSolver, ConvexPenetrationDepthSolver pdSolver) {
/* 259 */       this.simplexSolver = simplexSolver;
/* 260 */       this.pdSolver = pdSolver;
/*     */     }
/*     */ 
/*     */     public CollisionAlgorithm createCollisionAlgorithm(CollisionAlgorithmConstructionInfo ci, CollisionObject body0, CollisionObject body1)
/*     */     {
/* 265 */       ConvexConvexAlgorithm algo = (ConvexConvexAlgorithm)this.pool.get();
/* 266 */       algo.init(ci.manifold, ci, body0, body1, this.simplexSolver, this.pdSolver);
/* 267 */       return algo;
/*     */     }
/*     */ 
/*     */     public void releaseCollisionAlgorithm(CollisionAlgorithm algo)
/*     */     {
/* 272 */       this.pool.release((ConvexConvexAlgorithm)algo);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.dispatch.ConvexConvexAlgorithm
 * JD-Core Version:    0.6.2
 */