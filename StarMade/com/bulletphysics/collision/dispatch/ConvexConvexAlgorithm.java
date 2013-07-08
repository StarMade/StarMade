/*   1:    */package com.bulletphysics.collision.dispatch;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*   5:    */import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
/*   6:    */import com.bulletphysics.collision.broadphase.Dispatcher;
/*   7:    */import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*   8:    */import com.bulletphysics.collision.narrowphase.ConvexCast.CastResult;
/*   9:    */import com.bulletphysics.collision.narrowphase.ConvexPenetrationDepthSolver;
/*  10:    */import com.bulletphysics.collision.narrowphase.DiscreteCollisionDetectorInterface.ClosestPointInput;
/*  11:    */import com.bulletphysics.collision.narrowphase.GjkConvexCast;
/*  12:    */import com.bulletphysics.collision.narrowphase.GjkPairDetector;
/*  13:    */import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*  14:    */import com.bulletphysics.collision.narrowphase.SimplexSolverInterface;
/*  15:    */import com.bulletphysics.collision.narrowphase.VoronoiSimplexSolver;
/*  16:    */import com.bulletphysics.collision.shapes.ConvexShape;
/*  17:    */import com.bulletphysics.collision.shapes.SphereShape;
/*  18:    */import com.bulletphysics.linearmath.Transform;
/*  19:    */import com.bulletphysics.util.ObjectArrayList;
/*  20:    */import com.bulletphysics.util.ObjectPool;
/*  21:    */import javax.vecmath.Vector3f;
/*  22:    */
/*  42:    */public class ConvexConvexAlgorithm
/*  43:    */  extends CollisionAlgorithm
/*  44:    */{
/*  45:    */  protected final ObjectPool<DiscreteCollisionDetectorInterface.ClosestPointInput> pointInputsPool;
/*  46:    */  private GjkPairDetector gjkPairDetector;
/*  47:    */  public boolean ownManifold;
/*  48:    */  public PersistentManifold manifoldPtr;
/*  49:    */  public boolean lowLevelOfDetail;
/*  50:    */  
/*  51:    */  public ConvexConvexAlgorithm()
/*  52:    */  {
/*  53: 53 */    this.pointInputsPool = ObjectPool.get(DiscreteCollisionDetectorInterface.ClosestPointInput.class);
/*  54:    */    
/*  55: 55 */    this.gjkPairDetector = new GjkPairDetector();
/*  56:    */  }
/*  57:    */  
/*  60:    */  public void init(PersistentManifold mf, CollisionAlgorithmConstructionInfo ci, CollisionObject body0, CollisionObject body1, SimplexSolverInterface simplexSolver, ConvexPenetrationDepthSolver pdSolver)
/*  61:    */  {
/*  62: 62 */    super.init(ci);
/*  63: 63 */    this.gjkPairDetector.init(null, null, simplexSolver, pdSolver);
/*  64: 64 */    this.manifoldPtr = mf;
/*  65: 65 */    this.ownManifold = false;
/*  66: 66 */    this.lowLevelOfDetail = false;
/*  67:    */  }
/*  68:    */  
/*  69:    */  public void destroy()
/*  70:    */  {
/*  71: 71 */    if (this.ownManifold) {
/*  72: 72 */      if (this.manifoldPtr != null) {
/*  73: 73 */        this.dispatcher.releaseManifold(this.manifoldPtr);
/*  74:    */      }
/*  75: 75 */      this.manifoldPtr = null;
/*  76:    */    }
/*  77:    */  }
/*  78:    */  
/*  79:    */  public void setLowLevelOfDetail(boolean useLowLevel) {
/*  80: 80 */    this.lowLevelOfDetail = useLowLevel;
/*  81:    */  }
/*  82:    */  
/*  86:    */  public void processCollision(CollisionObject body0, CollisionObject body1, DispatcherInfo dispatchInfo, ManifoldResult resultOut)
/*  87:    */  {
/*  88: 88 */    if (this.manifoldPtr == null)
/*  89:    */    {
/*  90: 90 */      this.manifoldPtr = this.dispatcher.getNewManifold(body0, body1);
/*  91: 91 */      this.ownManifold = true;
/*  92:    */    }
/*  93: 93 */    resultOut.setPersistentManifold(this.manifoldPtr);
/*  94:    */    
/* 109:109 */    ConvexShape min0 = (ConvexShape)body0.getCollisionShape();
/* 110:110 */    ConvexShape min1 = (ConvexShape)body1.getCollisionShape();
/* 111:    */    
/* 112:112 */    DiscreteCollisionDetectorInterface.ClosestPointInput input = (DiscreteCollisionDetectorInterface.ClosestPointInput)this.pointInputsPool.get();
/* 113:113 */    input.init();
/* 114:    */    
/* 116:116 */    this.gjkPairDetector.setMinkowskiA(min0);
/* 117:117 */    this.gjkPairDetector.setMinkowskiB(min1);
/* 118:118 */    input.maximumDistanceSquared = (min0.getMargin() + min1.getMargin() + this.manifoldPtr.getContactBreakingThreshold());
/* 119:119 */    input.maximumDistanceSquared *= input.maximumDistanceSquared;
/* 120:    */    
/* 124:124 */    body0.getWorldTransform(input.transformA);
/* 125:125 */    body1.getWorldTransform(input.transformB);
/* 126:    */    
/* 127:127 */    this.gjkPairDetector.getClosestPoints(input, resultOut, dispatchInfo.debugDraw);
/* 128:    */    
/* 129:129 */    this.pointInputsPool.release(input);
/* 130:    */    
/* 132:132 */    if (this.ownManifold) {
/* 133:133 */      resultOut.refreshContactPoints();
/* 134:    */    }
/* 135:    */  }
/* 136:    */  
/* 137:137 */  private static boolean disableCcd = false;
/* 138:    */  
/* 139:    */  public float calculateTimeOfImpact(CollisionObject arg1, CollisionObject arg2, DispatcherInfo arg3, ManifoldResult arg4)
/* 140:    */  {
/* 141:141 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform();tmp7_5.push$javax$vecmath$Vector3f();Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 142:    */      
/* 143:143 */      Transform tmpTrans1 = localStack.get$com$bulletphysics$linearmath$Transform();
/* 144:144 */      Transform tmpTrans2 = localStack.get$com$bulletphysics$linearmath$Transform();
/* 145:    */      
/* 150:150 */      float resultFraction = 1.0F;
/* 151:    */      
/* 152:152 */      tmp.sub(col0.getInterpolationWorldTransform(tmpTrans1).origin, col0.getWorldTransform(tmpTrans2).origin);
/* 153:153 */      float squareMot0 = tmp.lengthSquared();
/* 154:    */      
/* 155:155 */      tmp.sub(col1.getInterpolationWorldTransform(tmpTrans1).origin, col1.getWorldTransform(tmpTrans2).origin);
/* 156:156 */      float squareMot1 = tmp.lengthSquared();
/* 157:    */      
/* 158:158 */      if ((squareMot0 < col0.getCcdSquareMotionThreshold()) && (squareMot1 < col1.getCcdSquareMotionThreshold()))
/* 159:    */      {
/* 160:160 */        return resultFraction;
/* 161:    */      }
/* 162:    */      
/* 163:163 */      if (disableCcd) {
/* 164:164 */        return 1.0F;
/* 165:    */      }
/* 166:    */      
/* 167:167 */      Transform tmpTrans3 = localStack.get$com$bulletphysics$linearmath$Transform();
/* 168:168 */      Transform tmpTrans4 = localStack.get$com$bulletphysics$linearmath$Transform();
/* 169:    */      
/* 178:178 */      ConvexShape convex0 = (ConvexShape)col0.getCollisionShape();
/* 179:    */      
/* 180:180 */      SphereShape sphere1 = new SphereShape(col1.getCcdSweptSphereRadius());
/* 181:181 */      ConvexCast.CastResult result = new ConvexCast.CastResult();
/* 182:182 */      VoronoiSimplexSolver voronoiSimplex = new VoronoiSimplexSolver();
/* 183:    */      
/* 185:185 */      GjkConvexCast ccd1 = new GjkConvexCast(convex0, sphere1, voronoiSimplex);
/* 186:    */      
/* 187:187 */      if (ccd1.calcTimeOfImpact(col0.getWorldTransform(tmpTrans1), col0.getInterpolationWorldTransform(tmpTrans2), col1.getWorldTransform(tmpTrans3), col1.getInterpolationWorldTransform(tmpTrans4), result))
/* 188:    */      {
/* 191:191 */        if (col0.getHitFraction() > result.fraction) {
/* 192:192 */          col0.setHitFraction(result.fraction);
/* 193:    */        }
/* 194:    */        
/* 195:195 */        if (col1.getHitFraction() > result.fraction) {
/* 196:196 */          col1.setHitFraction(result.fraction);
/* 197:    */        }
/* 198:    */        
/* 199:199 */        if (resultFraction > result.fraction) {
/* 200:200 */          resultFraction = result.fraction;
/* 201:    */        }
/* 202:    */      }
/* 203:    */      
/* 207:207 */      ConvexShape convex1 = (ConvexShape)col1.getCollisionShape();
/* 208:    */      
/* 209:209 */      SphereShape sphere0 = new SphereShape(col0.getCcdSweptSphereRadius());
/* 210:210 */      ConvexCast.CastResult result = new ConvexCast.CastResult();
/* 211:211 */      VoronoiSimplexSolver voronoiSimplex = new VoronoiSimplexSolver();
/* 212:    */      
/* 214:214 */      GjkConvexCast ccd1 = new GjkConvexCast(sphere0, convex1, voronoiSimplex);
/* 215:    */      
/* 216:216 */      if (ccd1.calcTimeOfImpact(col0.getWorldTransform(tmpTrans1), col0.getInterpolationWorldTransform(tmpTrans2), col1.getWorldTransform(tmpTrans3), col1.getInterpolationWorldTransform(tmpTrans4), result))
/* 217:    */      {
/* 220:220 */        if (col0.getHitFraction() > result.fraction) {
/* 221:221 */          col0.setHitFraction(result.fraction);
/* 222:    */        }
/* 223:    */        
/* 224:224 */        if (col1.getHitFraction() > result.fraction) {
/* 225:225 */          col1.setHitFraction(result.fraction);
/* 226:    */        }
/* 227:    */        
/* 228:228 */        if (resultFraction <= result.fraction) {} }
/* 229:229 */      return result.fraction;
/* 231:    */    }
/* 232:    */    finally
/* 233:    */    {
/* 235:235 */      .Stack tmp477_475 = localStack;tmp477_475.pop$com$bulletphysics$linearmath$Transform();tmp477_475.pop$javax$vecmath$Vector3f();
/* 236:    */    }
/* 237:    */  }
/* 238:    */  
/* 239:    */  public void getAllContactManifolds(ObjectArrayList<PersistentManifold> manifoldArray)
/* 240:    */  {
/* 241:241 */    if ((this.manifoldPtr != null) && (this.ownManifold)) {
/* 242:242 */      manifoldArray.add(this.manifoldPtr);
/* 243:    */    }
/* 244:    */  }
/* 245:    */  
/* 246:    */  public PersistentManifold getManifold() {
/* 247:247 */    return this.manifoldPtr;
/* 248:    */  }
/* 249:    */  
/* 250:    */  public static class CreateFunc
/* 251:    */    extends CollisionAlgorithmCreateFunc
/* 252:    */  {
/* 253:253 */    private final ObjectPool<ConvexConvexAlgorithm> pool = ObjectPool.get(ConvexConvexAlgorithm.class);
/* 254:    */    public ConvexPenetrationDepthSolver pdSolver;
/* 255:    */    public SimplexSolverInterface simplexSolver;
/* 256:    */    
/* 257:    */    public CreateFunc(SimplexSolverInterface simplexSolver, ConvexPenetrationDepthSolver pdSolver)
/* 258:    */    {
/* 259:259 */      this.simplexSolver = simplexSolver;
/* 260:260 */      this.pdSolver = pdSolver;
/* 261:    */    }
/* 262:    */    
/* 263:    */    public CollisionAlgorithm createCollisionAlgorithm(CollisionAlgorithmConstructionInfo ci, CollisionObject body0, CollisionObject body1)
/* 264:    */    {
/* 265:265 */      ConvexConvexAlgorithm algo = (ConvexConvexAlgorithm)this.pool.get();
/* 266:266 */      algo.init(ci.manifold, ci, body0, body1, this.simplexSolver, this.pdSolver);
/* 267:267 */      return algo;
/* 268:    */    }
/* 269:    */    
/* 270:    */    public void releaseCollisionAlgorithm(CollisionAlgorithm algo)
/* 271:    */    {
/* 272:272 */      this.pool.release((ConvexConvexAlgorithm)algo);
/* 273:    */    }
/* 274:    */  }
/* 275:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.dispatch.ConvexConvexAlgorithm
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */