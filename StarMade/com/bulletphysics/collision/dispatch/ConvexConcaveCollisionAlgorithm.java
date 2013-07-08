/*   1:    */package com.bulletphysics.collision.dispatch;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*   5:    */import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
/*   6:    */import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*   7:    */import com.bulletphysics.collision.narrowphase.ConvexCast.CastResult;
/*   8:    */import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*   9:    */import com.bulletphysics.collision.narrowphase.SubsimplexConvexCast;
/*  10:    */import com.bulletphysics.collision.narrowphase.VoronoiSimplexSolver;
/*  11:    */import com.bulletphysics.collision.shapes.CollisionShape;
/*  12:    */import com.bulletphysics.collision.shapes.ConcaveShape;
/*  13:    */import com.bulletphysics.collision.shapes.SphereShape;
/*  14:    */import com.bulletphysics.collision.shapes.TriangleCallback;
/*  15:    */import com.bulletphysics.collision.shapes.TriangleShape;
/*  16:    */import com.bulletphysics.linearmath.Transform;
/*  17:    */import com.bulletphysics.linearmath.VectorUtil;
/*  18:    */import com.bulletphysics.util.ObjectArrayList;
/*  19:    */import com.bulletphysics.util.ObjectPool;
/*  20:    */import javax.vecmath.Vector3f;
/*  21:    */
/*  48:    */public class ConvexConcaveCollisionAlgorithm
/*  49:    */  extends CollisionAlgorithm
/*  50:    */{
/*  51:    */  private boolean isSwapped;
/*  52:    */  private ConvexTriangleCallback btConvexTriangleCallback;
/*  53:    */  
/*  54:    */  public void init(CollisionAlgorithmConstructionInfo ci, CollisionObject body0, CollisionObject body1, boolean isSwapped)
/*  55:    */  {
/*  56: 56 */    super.init(ci);
/*  57: 57 */    this.isSwapped = isSwapped;
/*  58: 58 */    this.btConvexTriangleCallback = new ConvexTriangleCallback(this.dispatcher, body0, body1, isSwapped);
/*  59:    */  }
/*  60:    */  
/*  61:    */  public void destroy()
/*  62:    */  {
/*  63: 63 */    this.btConvexTriangleCallback.destroy();
/*  64:    */  }
/*  65:    */  
/*  66:    */  public void processCollision(CollisionObject arg1, CollisionObject arg2, DispatcherInfo arg3, ManifoldResult arg4)
/*  67:    */  {
/*  68: 68 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();CollisionObject convexBody = this.isSwapped ? body1 : body0;
/*  69: 69 */      CollisionObject triBody = this.isSwapped ? body0 : body1;
/*  70:    */      
/*  71: 71 */      if (triBody.getCollisionShape().isConcave()) {
/*  72: 72 */        CollisionObject triOb = triBody;
/*  73: 73 */        ConcaveShape concaveShape = (ConcaveShape)triOb.getCollisionShape();
/*  74:    */        
/*  75: 75 */        if (convexBody.getCollisionShape().isConvex()) {
/*  76: 76 */          float collisionMarginTriangle = concaveShape.getMargin();
/*  77:    */          
/*  78: 78 */          resultOut.setPersistentManifold(this.btConvexTriangleCallback.manifoldPtr);
/*  79: 79 */          this.btConvexTriangleCallback.setTimeStepAndCounters(collisionMarginTriangle, dispatchInfo, resultOut);
/*  80:    */          
/*  84: 84 */          this.btConvexTriangleCallback.manifoldPtr.setBodies(convexBody, triBody);
/*  85:    */          
/*  86: 86 */          concaveShape.processAllTriangles(this.btConvexTriangleCallback, this.btConvexTriangleCallback.getAabbMin(localStack.get$javax$vecmath$Vector3f()), this.btConvexTriangleCallback.getAabbMax(localStack.get$javax$vecmath$Vector3f()));
/*  87:    */          
/*  91: 91 */          resultOut.refreshContactPoints();
/*  92:    */        }
/*  93:    */      }
/*  94: 94 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/*  95:    */    }
/*  96:    */  }
/*  97:    */  
/*  98: 98 */  public float calculateTimeOfImpact(CollisionObject arg1, CollisionObject arg2, DispatcherInfo arg3, ManifoldResult arg4) { .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform();tmp7_5.push$javax$vecmath$Vector3f();Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*  99:    */      
/* 100:100 */      CollisionObject convexbody = this.isSwapped ? body1 : body0;
/* 101:101 */      CollisionObject triBody = this.isSwapped ? body0 : body1;
/* 102:    */      
/* 107:107 */      tmp.sub(convexbody.getInterpolationWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform()).origin, convexbody.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform()).origin);
/* 108:108 */      float squareMot0 = tmp.lengthSquared();
/* 109:109 */      if (squareMot0 < convexbody.getCcdSquareMotionThreshold()) {
/* 110:110 */        return 1.0F;
/* 111:    */      }
/* 112:    */      
/* 113:113 */      Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 114:    */      
/* 119:119 */      Transform triInv = triBody.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/* 120:120 */      triInv.inverse();
/* 121:    */      
/* 122:122 */      Transform convexFromLocal = localStack.get$com$bulletphysics$linearmath$Transform();
/* 123:123 */      convexFromLocal.mul(triInv, convexbody.getWorldTransform(tmpTrans));
/* 124:    */      
/* 125:125 */      Transform convexToLocal = localStack.get$com$bulletphysics$linearmath$Transform();
/* 126:126 */      convexToLocal.mul(triInv, convexbody.getInterpolationWorldTransform(tmpTrans));
/* 127:    */      
/* 128:128 */      if (triBody.getCollisionShape().isConcave()) {
/* 129:129 */        Vector3f rayAabbMin = localStack.get$javax$vecmath$Vector3f(convexFromLocal.origin);
/* 130:130 */        VectorUtil.setMin(rayAabbMin, convexToLocal.origin);
/* 131:    */        
/* 132:132 */        Vector3f rayAabbMax = localStack.get$javax$vecmath$Vector3f(convexFromLocal.origin);
/* 133:133 */        VectorUtil.setMax(rayAabbMax, convexToLocal.origin);
/* 134:    */        
/* 135:135 */        float ccdRadius0 = convexbody.getCcdSweptSphereRadius();
/* 136:    */        
/* 137:137 */        tmp.set(ccdRadius0, ccdRadius0, ccdRadius0);
/* 138:138 */        rayAabbMin.sub(tmp);
/* 139:139 */        rayAabbMax.add(tmp);
/* 140:    */        
/* 141:141 */        float curHitFraction = 1.0F;
/* 142:142 */        LocalTriangleSphereCastCallback raycastCallback = new LocalTriangleSphereCastCallback(convexFromLocal, convexToLocal, convexbody.getCcdSweptSphereRadius(), curHitFraction);
/* 143:    */        
/* 144:144 */        raycastCallback.hitFraction = convexbody.getHitFraction();
/* 145:    */        
/* 146:146 */        CollisionObject concavebody = triBody;
/* 147:    */        
/* 148:148 */        ConcaveShape triangleMesh = (ConcaveShape)concavebody.getCollisionShape();
/* 149:    */        
/* 150:150 */        if (triangleMesh != null) {
/* 151:151 */          triangleMesh.processAllTriangles(raycastCallback, rayAabbMin, rayAabbMax);
/* 152:    */        }
/* 153:    */        
/* 154:154 */        if (raycastCallback.hitFraction < convexbody.getHitFraction()) {
/* 155:155 */          convexbody.setHitFraction(raycastCallback.hitFraction);
/* 156:156 */          return raycastCallback.hitFraction;
/* 157:    */        }
/* 158:    */      }
/* 159:    */      
/* 160:160 */      return 1.0F; } finally { .Stack tmp379_377 = localStack;tmp379_377.pop$com$bulletphysics$linearmath$Transform();tmp379_377.pop$javax$vecmath$Vector3f();
/* 161:    */    }
/* 162:    */  }
/* 163:    */  
/* 164:    */  public void getAllContactManifolds(ObjectArrayList<PersistentManifold> manifoldArray) {
/* 165:165 */    if (this.btConvexTriangleCallback.manifoldPtr != null) {
/* 166:166 */      manifoldArray.add(this.btConvexTriangleCallback.manifoldPtr);
/* 167:    */    }
/* 168:    */  }
/* 169:    */  
/* 170:    */  public void clearCache() {
/* 171:171 */    this.btConvexTriangleCallback.clearCache();
/* 172:    */  }
/* 173:    */  
/* 174:    */  private static class LocalTriangleSphereCastCallback
/* 175:    */    extends TriangleCallback
/* 176:    */  {
/* 177:177 */    public final Transform ccdSphereFromTrans = new Transform();
/* 178:178 */    public final Transform ccdSphereToTrans = new Transform();
/* 179:179 */    public final Transform meshTransform = new Transform();
/* 180:    */    
/* 181:    */    public float ccdSphereRadius;
/* 182:    */    
/* 183:    */    public float hitFraction;
/* 184:184 */    private final Transform ident = new Transform();
/* 185:    */    
/* 186:    */    public LocalTriangleSphereCastCallback(Transform from, Transform to, float ccdSphereRadius, float hitFraction) {
/* 187:187 */      this.ccdSphereFromTrans.set(from);
/* 188:188 */      this.ccdSphereToTrans.set(to);
/* 189:189 */      this.ccdSphereRadius = ccdSphereRadius;
/* 190:190 */      this.hitFraction = hitFraction;
/* 191:    */      
/* 193:193 */      this.ident.setIdentity();
/* 194:    */    }
/* 195:    */    
/* 200:    */    public void processTriangle(Vector3f[] triangle, int partId, int triangleIndex)
/* 201:    */    {
/* 202:202 */      ConvexCast.CastResult castResult = new ConvexCast.CastResult();
/* 203:203 */      castResult.fraction = this.hitFraction;
/* 204:204 */      SphereShape pointShape = new SphereShape(this.ccdSphereRadius);
/* 205:205 */      TriangleShape triShape = new TriangleShape(triangle[0], triangle[1], triangle[2]);
/* 206:206 */      VoronoiSimplexSolver simplexSolver = new VoronoiSimplexSolver();
/* 207:207 */      SubsimplexConvexCast convexCaster = new SubsimplexConvexCast(pointShape, triShape, simplexSolver);
/* 208:    */      
/* 212:212 */      if ((convexCaster.calcTimeOfImpact(this.ccdSphereFromTrans, this.ccdSphereToTrans, this.ident, this.ident, castResult)) && 
/* 213:213 */        (this.hitFraction > castResult.fraction)) {
/* 214:214 */        this.hitFraction = castResult.fraction;
/* 215:    */      }
/* 216:    */    }
/* 217:    */  }
/* 218:    */  
/* 220:    */  public static class CreateFunc
/* 221:    */    extends CollisionAlgorithmCreateFunc
/* 222:    */  {
/* 223:223 */    private final ObjectPool<ConvexConcaveCollisionAlgorithm> pool = ObjectPool.get(ConvexConcaveCollisionAlgorithm.class);
/* 224:    */    
/* 225:    */    public CollisionAlgorithm createCollisionAlgorithm(CollisionAlgorithmConstructionInfo ci, CollisionObject body0, CollisionObject body1)
/* 226:    */    {
/* 227:227 */      ConvexConcaveCollisionAlgorithm algo = (ConvexConcaveCollisionAlgorithm)this.pool.get();
/* 228:228 */      algo.init(ci, body0, body1, false);
/* 229:229 */      return algo;
/* 230:    */    }
/* 231:    */    
/* 232:    */    public void releaseCollisionAlgorithm(CollisionAlgorithm algo)
/* 233:    */    {
/* 234:234 */      this.pool.release((ConvexConcaveCollisionAlgorithm)algo);
/* 235:    */    }
/* 236:    */  }
/* 237:    */  
/* 238:    */  public static class SwappedCreateFunc extends CollisionAlgorithmCreateFunc {
/* 239:239 */    private final ObjectPool<ConvexConcaveCollisionAlgorithm> pool = ObjectPool.get(ConvexConcaveCollisionAlgorithm.class);
/* 240:    */    
/* 241:    */    public CollisionAlgorithm createCollisionAlgorithm(CollisionAlgorithmConstructionInfo ci, CollisionObject body0, CollisionObject body1)
/* 242:    */    {
/* 243:243 */      ConvexConcaveCollisionAlgorithm algo = (ConvexConcaveCollisionAlgorithm)this.pool.get();
/* 244:244 */      algo.init(ci, body0, body1, true);
/* 245:245 */      return algo;
/* 246:    */    }
/* 247:    */    
/* 248:    */    public void releaseCollisionAlgorithm(CollisionAlgorithm algo)
/* 249:    */    {
/* 250:250 */      this.pool.release((ConvexConcaveCollisionAlgorithm)algo);
/* 251:    */    }
/* 252:    */  }
/* 253:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.dispatch.ConvexConcaveCollisionAlgorithm
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */