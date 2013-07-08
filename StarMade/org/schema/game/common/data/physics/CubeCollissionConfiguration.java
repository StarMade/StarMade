package org.schema.game.common.data.physics;

import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
import com.bulletphysics.collision.dispatch.CollisionAlgorithmCreateFunc;
import com.bulletphysics.collision.dispatch.CollisionConfiguration;
import com.bulletphysics.collision.dispatch.ConvexConcaveCollisionAlgorithm.CreateFunc;
import com.bulletphysics.collision.dispatch.ConvexConcaveCollisionAlgorithm.SwappedCreateFunc;
import com.bulletphysics.collision.dispatch.ConvexPlaneCollisionAlgorithm.CreateFunc;
import com.bulletphysics.collision.dispatch.EmptyAlgorithm.CreateFunc;
import com.bulletphysics.collision.dispatch.SphereSphereCollisionAlgorithm.CreateFunc;
import com.bulletphysics.collision.narrowphase.GjkEpaPenetrationDepthSolver;
import com.bulletphysics.collision.narrowphase.VoronoiSimplexSolver;

public class CubeCollissionConfiguration
  extends CollisionConfiguration
{
  protected VoronoiSimplexSolver simplexSolver = new VoronoiSimplexSolver();
  protected GjkEpaPenetrationDepthSolver pdSolver = new GjkEpaPenetrationDepthSolver();
  protected CollisionAlgorithmCreateFunc convexConvexCreateFunc = new ConvexConvexExtAlgorithm.CreateFunc(this.simplexSolver, this.pdSolver);
  protected CollisionAlgorithmCreateFunc convexConcaveCreateFunc = new ConvexConcaveCollisionAlgorithm.CreateFunc();
  protected CollisionAlgorithmCreateFunc swappedConvexConcaveCreateFunc = new ConvexConcaveCollisionAlgorithm.SwappedCreateFunc();
  protected CollisionAlgorithmCreateFunc compoundCreateFunc = new CompoundCollisionAlgorithmExt.CreateFunc();
  protected CollisionAlgorithmCreateFunc swappedCompoundCreateFunc = new CompoundCollisionAlgorithmExt.SwappedCreateFunc();
  protected CollisionAlgorithmCreateFunc emptyCreateFunc = new EmptyAlgorithm.CreateFunc();
  protected CollisionAlgorithmCreateFunc sphereSphereCF = new SphereSphereCollisionAlgorithm.CreateFunc();
  protected CollisionAlgorithmCreateFunc sphereBoxCF;
  protected CollisionAlgorithmCreateFunc boxSphereCF;
  protected CollisionAlgorithmCreateFunc cubesCubesCF = new CubeCubeCollisionAlgorithm.CreateFunc(this.simplexSolver, this.pdSolver);
  protected CollisionAlgorithmCreateFunc sphereTriangleCF;
  protected CollisionAlgorithmCreateFunc triangleSphereCF;
  protected CollisionAlgorithmCreateFunc planeConvexCF;
  protected CollisionAlgorithmCreateFunc convexPlaneCF;
  private CubeConvexCollisionAlgorithm.CreateFunc cubesConvexCF = new CubeConvexCollisionAlgorithm.CreateFunc(this.simplexSolver, this.pdSolver);
  private CubeConvexCollisionAlgorithm.CreateFunc convexCubesCF = new CubeConvexCollisionAlgorithm.CreateFunc(this.simplexSolver, this.pdSolver);
  
  public CubeCollissionConfiguration()
  {
    this.convexCubesCF.swapped = true;
    this.convexPlaneCF = new ConvexPlaneCollisionAlgorithm.CreateFunc();
    this.planeConvexCF = new ConvexPlaneCollisionAlgorithm.CreateFunc();
    this.planeConvexCF.swapped = true;
  }
  
  public CollisionAlgorithmCreateFunc getCollisionAlgorithmCreateFunc(BroadphaseNativeType paramBroadphaseNativeType1, BroadphaseNativeType paramBroadphaseNativeType2)
  {
    if ((paramBroadphaseNativeType1 == BroadphaseNativeType.SPHERE_SHAPE_PROXYTYPE) && (paramBroadphaseNativeType2 == BroadphaseNativeType.SPHERE_SHAPE_PROXYTYPE)) {
      return this.sphereSphereCF;
    }
    if ((paramBroadphaseNativeType1 == BroadphaseNativeType.TERRAIN_SHAPE_PROXYTYPE) && (paramBroadphaseNativeType2 == BroadphaseNativeType.TERRAIN_SHAPE_PROXYTYPE)) {
      return this.cubesCubesCF;
    }
    if ((paramBroadphaseNativeType1 == BroadphaseNativeType.TERRAIN_SHAPE_PROXYTYPE) && (paramBroadphaseNativeType2.isConvex())) {
      return this.cubesConvexCF;
    }
    if ((paramBroadphaseNativeType1.isConvex()) && (paramBroadphaseNativeType2 == BroadphaseNativeType.TERRAIN_SHAPE_PROXYTYPE)) {
      return this.convexCubesCF;
    }
    if ((paramBroadphaseNativeType1 == BroadphaseNativeType.FAST_CONCAVE_MESH_PROXYTYPE) && (paramBroadphaseNativeType2 == BroadphaseNativeType.FAST_CONCAVE_MESH_PROXYTYPE)) {
      return this.compoundCreateFunc;
    }
    if ((paramBroadphaseNativeType1.isConvex()) && (paramBroadphaseNativeType2 == BroadphaseNativeType.STATIC_PLANE_PROXYTYPE)) {
      return this.convexPlaneCF;
    }
    if ((paramBroadphaseNativeType2.isConvex()) && (paramBroadphaseNativeType1 == BroadphaseNativeType.STATIC_PLANE_PROXYTYPE)) {
      return this.planeConvexCF;
    }
    if ((paramBroadphaseNativeType1.isConvex()) && (paramBroadphaseNativeType2.isConvex())) {
      return this.convexConvexCreateFunc;
    }
    if ((paramBroadphaseNativeType1.isConvex()) && (paramBroadphaseNativeType2.isConcave())) {
      return this.convexConcaveCreateFunc;
    }
    if ((paramBroadphaseNativeType2.isConvex()) && (paramBroadphaseNativeType1.isConcave())) {
      return this.swappedConvexConcaveCreateFunc;
    }
    if (paramBroadphaseNativeType1.isCompound()) {
      return this.compoundCreateFunc;
    }
    if (paramBroadphaseNativeType2.isCompound()) {
      return this.swappedCompoundCreateFunc;
    }
    return this.emptyCreateFunc;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.CubeCollissionConfiguration
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */