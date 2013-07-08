package com.bulletphysics.collision.narrowphase;

import com.bulletphysics.collision.shapes.ConvexShape;
import com.bulletphysics.collision.shapes.TriangleCallback;
import com.bulletphysics.collision.shapes.TriangleShape;
import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Vector3f;

public abstract class TriangleConvexcastCallback
  extends TriangleCallback
{
  public ConvexShape convexShape;
  public final Transform convexShapeFrom = new Transform();
  public final Transform convexShapeTo = new Transform();
  public final Transform triangleToWorld = new Transform();
  public float hitFraction;
  public float triangleCollisionMargin;
  
  public TriangleConvexcastCallback(ConvexShape convexShape, Transform convexShapeFrom, Transform convexShapeTo, Transform triangleToWorld, float triangleCollisionMargin)
  {
    this.convexShape = convexShape;
    this.convexShapeFrom.set(convexShapeFrom);
    this.convexShapeTo.set(convexShapeTo);
    this.triangleToWorld.set(triangleToWorld);
    this.hitFraction = 1.0F;
    this.triangleCollisionMargin = triangleCollisionMargin;
  }
  
  public void processTriangle(Vector3f[] triangle, int partId, int triangleIndex)
  {
    TriangleShape triangleShape = new TriangleShape(triangle[0], triangle[1], triangle[2]);
    triangleShape.setMargin(this.triangleCollisionMargin);
    VoronoiSimplexSolver simplexSolver = new VoronoiSimplexSolver();
    GjkEpaPenetrationDepthSolver gjkEpaPenetrationSolver = new GjkEpaPenetrationDepthSolver();
    SubsimplexConvexCast convexCaster = new SubsimplexConvexCast(this.convexShape, triangleShape, simplexSolver);
    ConvexCast.CastResult castResult = new ConvexCast.CastResult();
    castResult.fraction = 1.0F;
    if ((convexCaster.calcTimeOfImpact(this.convexShapeFrom, this.convexShapeTo, this.triangleToWorld, this.triangleToWorld, castResult)) && (castResult.normal.lengthSquared() > 1.0E-004F) && (castResult.fraction < this.hitFraction))
    {
      castResult.normal.normalize();
      reportHit(castResult.normal, castResult.hitPoint, castResult.fraction, partId, triangleIndex);
    }
  }
  
  public abstract float reportHit(Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat, int paramInt1, int paramInt2);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.narrowphase.TriangleConvexcastCallback
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */