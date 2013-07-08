/*   1:    */package org.schema.game.common.data.physics;
/*   2:    */
/*   3:    */import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*   4:    */import com.bulletphysics.collision.dispatch.CollisionAlgorithmCreateFunc;
/*   5:    */import com.bulletphysics.collision.dispatch.CollisionConfiguration;
/*   6:    */import com.bulletphysics.collision.dispatch.ConvexConcaveCollisionAlgorithm.CreateFunc;
/*   7:    */import com.bulletphysics.collision.dispatch.ConvexConcaveCollisionAlgorithm.SwappedCreateFunc;
/*   8:    */import com.bulletphysics.collision.dispatch.ConvexPlaneCollisionAlgorithm.CreateFunc;
/*   9:    */import com.bulletphysics.collision.dispatch.EmptyAlgorithm.CreateFunc;
/*  10:    */import com.bulletphysics.collision.dispatch.SphereSphereCollisionAlgorithm.CreateFunc;
/*  11:    */import com.bulletphysics.collision.narrowphase.GjkEpaPenetrationDepthSolver;
/*  12:    */import com.bulletphysics.collision.narrowphase.VoronoiSimplexSolver;
/*  13:    */
/*  48:    */public class CubeCollissionConfiguration
/*  49:    */  extends CollisionConfiguration
/*  50:    */{
/*  51:    */  protected VoronoiSimplexSolver simplexSolver;
/*  52:    */  protected GjkEpaPenetrationDepthSolver pdSolver;
/*  53:    */  protected CollisionAlgorithmCreateFunc convexConvexCreateFunc;
/*  54:    */  protected CollisionAlgorithmCreateFunc convexConcaveCreateFunc;
/*  55:    */  protected CollisionAlgorithmCreateFunc swappedConvexConcaveCreateFunc;
/*  56:    */  protected CollisionAlgorithmCreateFunc compoundCreateFunc;
/*  57:    */  protected CollisionAlgorithmCreateFunc swappedCompoundCreateFunc;
/*  58:    */  protected CollisionAlgorithmCreateFunc emptyCreateFunc;
/*  59:    */  protected CollisionAlgorithmCreateFunc sphereSphereCF;
/*  60:    */  protected CollisionAlgorithmCreateFunc sphereBoxCF;
/*  61:    */  protected CollisionAlgorithmCreateFunc boxSphereCF;
/*  62:    */  protected CollisionAlgorithmCreateFunc cubesCubesCF;
/*  63:    */  protected CollisionAlgorithmCreateFunc sphereTriangleCF;
/*  64:    */  protected CollisionAlgorithmCreateFunc triangleSphereCF;
/*  65:    */  protected CollisionAlgorithmCreateFunc planeConvexCF;
/*  66:    */  protected CollisionAlgorithmCreateFunc convexPlaneCF;
/*  67:    */  private CubeConvexCollisionAlgorithm.CreateFunc cubesConvexCF;
/*  68:    */  private CubeConvexCollisionAlgorithm.CreateFunc convexCubesCF;
/*  69:    */  
/*  70:    */  public CubeCollissionConfiguration()
/*  71:    */  {
/*  72: 72 */    this.simplexSolver = new VoronoiSimplexSolver();
/*  73:    */    
/*  76: 76 */    this.pdSolver = new GjkEpaPenetrationDepthSolver();
/*  77:    */    
/*  84: 84 */    this.convexConvexCreateFunc = new ConvexConvexExtAlgorithm.CreateFunc(this.simplexSolver, this.pdSolver);
/*  85: 85 */    this.convexConcaveCreateFunc = new ConvexConcaveCollisionAlgorithm.CreateFunc();
/*  86: 86 */    this.swappedConvexConcaveCreateFunc = new ConvexConcaveCollisionAlgorithm.SwappedCreateFunc();
/*  87:    */    
/*  88: 88 */    this.compoundCreateFunc = new CompoundCollisionAlgorithmExt.CreateFunc();
/*  89: 89 */    this.swappedCompoundCreateFunc = new CompoundCollisionAlgorithmExt.SwappedCreateFunc();
/*  90:    */    
/*  91: 91 */    this.emptyCreateFunc = new EmptyAlgorithm.CreateFunc();
/*  92:    */    
/*  93: 93 */    this.sphereSphereCF = new SphereSphereCollisionAlgorithm.CreateFunc();
/*  94:    */    
/* 106:106 */    this.cubesCubesCF = new CubeCubeCollisionAlgorithm.CreateFunc(this.simplexSolver, this.pdSolver);
/* 107:107 */    this.cubesConvexCF = new CubeConvexCollisionAlgorithm.CreateFunc(this.simplexSolver, this.pdSolver);
/* 108:108 */    this.convexCubesCF = new CubeConvexCollisionAlgorithm.CreateFunc(this.simplexSolver, this.pdSolver);
/* 109:109 */    this.convexCubesCF.swapped = true;
/* 110:    */    
/* 112:112 */    this.convexPlaneCF = new ConvexPlaneCollisionAlgorithm.CreateFunc();
/* 113:113 */    this.planeConvexCF = new ConvexPlaneCollisionAlgorithm.CreateFunc();
/* 114:114 */    this.planeConvexCF.swapped = true;
/* 115:    */  }
/* 116:    */  
/* 164:    */  public CollisionAlgorithmCreateFunc getCollisionAlgorithmCreateFunc(BroadphaseNativeType paramBroadphaseNativeType1, BroadphaseNativeType paramBroadphaseNativeType2)
/* 165:    */  {
/* 166:166 */    if ((paramBroadphaseNativeType1 == BroadphaseNativeType.SPHERE_SHAPE_PROXYTYPE) && (paramBroadphaseNativeType2 == BroadphaseNativeType.SPHERE_SHAPE_PROXYTYPE)) {
/* 167:167 */      return this.sphereSphereCF;
/* 168:    */    }
/* 169:169 */    if ((paramBroadphaseNativeType1 == BroadphaseNativeType.TERRAIN_SHAPE_PROXYTYPE) && (paramBroadphaseNativeType2 == BroadphaseNativeType.TERRAIN_SHAPE_PROXYTYPE))
/* 170:    */    {
/* 171:171 */      return this.cubesCubesCF;
/* 172:    */    }
/* 173:173 */    if ((paramBroadphaseNativeType1 == BroadphaseNativeType.TERRAIN_SHAPE_PROXYTYPE) && (paramBroadphaseNativeType2.isConvex()))
/* 174:    */    {
/* 175:175 */      return this.cubesConvexCF;
/* 176:    */    }
/* 177:177 */    if ((paramBroadphaseNativeType1.isConvex()) && (paramBroadphaseNativeType2 == BroadphaseNativeType.TERRAIN_SHAPE_PROXYTYPE))
/* 178:    */    {
/* 179:179 */      return this.convexCubesCF;
/* 180:    */    }
/* 181:    */    
/* 207:207 */    if ((paramBroadphaseNativeType1 == BroadphaseNativeType.FAST_CONCAVE_MESH_PROXYTYPE) && (paramBroadphaseNativeType2 == BroadphaseNativeType.FAST_CONCAVE_MESH_PROXYTYPE)) {
/* 208:208 */      return this.compoundCreateFunc;
/* 209:    */    }
/* 210:210 */    if ((paramBroadphaseNativeType1.isConvex()) && (paramBroadphaseNativeType2 == BroadphaseNativeType.STATIC_PLANE_PROXYTYPE))
/* 211:    */    {
/* 212:212 */      return this.convexPlaneCF;
/* 213:    */    }
/* 214:    */    
/* 215:215 */    if ((paramBroadphaseNativeType2.isConvex()) && (paramBroadphaseNativeType1 == BroadphaseNativeType.STATIC_PLANE_PROXYTYPE))
/* 216:    */    {
/* 217:217 */      return this.planeConvexCF;
/* 218:    */    }
/* 219:    */    
/* 220:220 */    if ((paramBroadphaseNativeType1.isConvex()) && (paramBroadphaseNativeType2.isConvex())) {
/* 221:221 */      return this.convexConvexCreateFunc;
/* 222:    */    }
/* 223:    */    
/* 224:224 */    if ((paramBroadphaseNativeType1.isConvex()) && (paramBroadphaseNativeType2.isConcave())) {
/* 225:225 */      return this.convexConcaveCreateFunc;
/* 226:    */    }
/* 227:    */    
/* 228:228 */    if ((paramBroadphaseNativeType2.isConvex()) && (paramBroadphaseNativeType1.isConcave())) {
/* 229:229 */      return this.swappedConvexConcaveCreateFunc;
/* 230:    */    }
/* 231:    */    
/* 232:232 */    if (paramBroadphaseNativeType1.isCompound())
/* 233:233 */      return this.compoundCreateFunc;
/* 234:234 */    if (paramBroadphaseNativeType2.isCompound()) {
/* 235:235 */      return this.swappedCompoundCreateFunc;
/* 236:    */    }
/* 237:    */    
/* 239:239 */    return this.emptyCreateFunc;
/* 240:    */  }
/* 241:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.CubeCollissionConfiguration
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */