/*   1:    */package com.bulletphysics.collision.dispatch;
/*   2:    */
/*   3:    */import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*   4:    */import com.bulletphysics.collision.narrowphase.ConvexPenetrationDepthSolver;
/*   5:    */import com.bulletphysics.collision.narrowphase.GjkEpaPenetrationDepthSolver;
/*   6:    */import com.bulletphysics.collision.narrowphase.VoronoiSimplexSolver;
/*   7:    */
/*  41:    */public class DefaultCollisionConfiguration
/*  42:    */  extends CollisionConfiguration
/*  43:    */{
/*  44:    */  protected VoronoiSimplexSolver simplexSolver;
/*  45:    */  protected ConvexPenetrationDepthSolver pdSolver;
/*  46:    */  protected CollisionAlgorithmCreateFunc convexConvexCreateFunc;
/*  47:    */  protected CollisionAlgorithmCreateFunc convexConcaveCreateFunc;
/*  48:    */  protected CollisionAlgorithmCreateFunc swappedConvexConcaveCreateFunc;
/*  49:    */  protected CollisionAlgorithmCreateFunc compoundCreateFunc;
/*  50:    */  protected CollisionAlgorithmCreateFunc swappedCompoundCreateFunc;
/*  51:    */  protected CollisionAlgorithmCreateFunc emptyCreateFunc;
/*  52:    */  protected CollisionAlgorithmCreateFunc sphereSphereCF;
/*  53:    */  protected CollisionAlgorithmCreateFunc sphereBoxCF;
/*  54:    */  protected CollisionAlgorithmCreateFunc boxSphereCF;
/*  55:    */  protected CollisionAlgorithmCreateFunc boxBoxCF;
/*  56:    */  protected CollisionAlgorithmCreateFunc sphereTriangleCF;
/*  57:    */  protected CollisionAlgorithmCreateFunc triangleSphereCF;
/*  58:    */  protected CollisionAlgorithmCreateFunc planeConvexCF;
/*  59:    */  protected CollisionAlgorithmCreateFunc convexPlaneCF;
/*  60:    */  
/*  61:    */  public DefaultCollisionConfiguration()
/*  62:    */  {
/*  63: 63 */    this.simplexSolver = new VoronoiSimplexSolver();
/*  64:    */    
/*  67: 67 */    this.pdSolver = new GjkEpaPenetrationDepthSolver();
/*  68:    */    
/*  75: 75 */    this.convexConvexCreateFunc = new ConvexConvexAlgorithm.CreateFunc(this.simplexSolver, this.pdSolver);
/*  76: 76 */    this.convexConcaveCreateFunc = new ConvexConcaveCollisionAlgorithm.CreateFunc();
/*  77: 77 */    this.swappedConvexConcaveCreateFunc = new ConvexConcaveCollisionAlgorithm.SwappedCreateFunc();
/*  78: 78 */    this.compoundCreateFunc = new CompoundCollisionAlgorithm.CreateFunc();
/*  79: 79 */    this.swappedCompoundCreateFunc = new CompoundCollisionAlgorithm.SwappedCreateFunc();
/*  80: 80 */    this.emptyCreateFunc = new EmptyAlgorithm.CreateFunc();
/*  81:    */    
/*  82: 82 */    this.sphereSphereCF = new SphereSphereCollisionAlgorithm.CreateFunc();
/*  83:    */    
/*  96: 96 */    this.convexPlaneCF = new ConvexPlaneCollisionAlgorithm.CreateFunc();
/*  97: 97 */    this.planeConvexCF = new ConvexPlaneCollisionAlgorithm.CreateFunc();
/*  98: 98 */    this.planeConvexCF.swapped = true;
/*  99:    */  }
/* 100:    */  
/* 146:    */  public CollisionAlgorithmCreateFunc getCollisionAlgorithmCreateFunc(BroadphaseNativeType proxyType0, BroadphaseNativeType proxyType1)
/* 147:    */  {
/* 148:148 */    if ((proxyType0 == BroadphaseNativeType.SPHERE_SHAPE_PROXYTYPE) && (proxyType1 == BroadphaseNativeType.SPHERE_SHAPE_PROXYTYPE)) {
/* 149:149 */      return this.sphereSphereCF;
/* 150:    */    }
/* 151:    */    
/* 178:178 */    if ((proxyType0.isConvex()) && (proxyType1 == BroadphaseNativeType.STATIC_PLANE_PROXYTYPE))
/* 179:    */    {
/* 180:180 */      return this.convexPlaneCF;
/* 181:    */    }
/* 182:    */    
/* 183:183 */    if ((proxyType1.isConvex()) && (proxyType0 == BroadphaseNativeType.STATIC_PLANE_PROXYTYPE))
/* 184:    */    {
/* 185:185 */      return this.planeConvexCF;
/* 186:    */    }
/* 187:    */    
/* 188:188 */    if ((proxyType0.isConvex()) && (proxyType1.isConvex())) {
/* 189:189 */      return this.convexConvexCreateFunc;
/* 190:    */    }
/* 191:    */    
/* 192:192 */    if ((proxyType0.isConvex()) && (proxyType1.isConcave())) {
/* 193:193 */      return this.convexConcaveCreateFunc;
/* 194:    */    }
/* 195:    */    
/* 196:196 */    if ((proxyType1.isConvex()) && (proxyType0.isConcave())) {
/* 197:197 */      return this.swappedConvexConcaveCreateFunc;
/* 198:    */    }
/* 199:    */    
/* 200:200 */    if (proxyType0.isCompound()) {
/* 201:201 */      return this.compoundCreateFunc;
/* 202:    */    }
/* 203:    */    
/* 204:204 */    if (proxyType1.isCompound()) {
/* 205:205 */      return this.swappedCompoundCreateFunc;
/* 206:    */    }
/* 207:    */    
/* 210:210 */    return this.emptyCreateFunc;
/* 211:    */  }
/* 212:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.dispatch.DefaultCollisionConfiguration
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */