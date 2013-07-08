/*  1:   */package com.bulletphysics.collision.narrowphase;
/*  2:   */
/*  3:   */import com.bulletphysics.collision.shapes.ConvexShape;
/*  4:   */import com.bulletphysics.collision.shapes.TriangleCallback;
/*  5:   */import com.bulletphysics.collision.shapes.TriangleShape;
/*  6:   */import com.bulletphysics.linearmath.Transform;
/*  7:   */import javax.vecmath.Vector3f;
/*  8:   */
/* 36:   */public abstract class TriangleConvexcastCallback
/* 37:   */  extends TriangleCallback
/* 38:   */{
/* 39:   */  public ConvexShape convexShape;
/* 40:40 */  public final Transform convexShapeFrom = new Transform();
/* 41:41 */  public final Transform convexShapeTo = new Transform();
/* 42:42 */  public final Transform triangleToWorld = new Transform();
/* 43:   */  public float hitFraction;
/* 44:   */  public float triangleCollisionMargin;
/* 45:   */  
/* 46:   */  public TriangleConvexcastCallback(ConvexShape convexShape, Transform convexShapeFrom, Transform convexShapeTo, Transform triangleToWorld, float triangleCollisionMargin) {
/* 47:47 */    this.convexShape = convexShape;
/* 48:48 */    this.convexShapeFrom.set(convexShapeFrom);
/* 49:49 */    this.convexShapeTo.set(convexShapeTo);
/* 50:50 */    this.triangleToWorld.set(triangleToWorld);
/* 51:51 */    this.hitFraction = 1.0F;
/* 52:52 */    this.triangleCollisionMargin = triangleCollisionMargin;
/* 53:   */  }
/* 54:   */  
/* 55:   */  public void processTriangle(Vector3f[] triangle, int partId, int triangleIndex) {
/* 56:56 */    TriangleShape triangleShape = new TriangleShape(triangle[0], triangle[1], triangle[2]);
/* 57:57 */    triangleShape.setMargin(this.triangleCollisionMargin);
/* 58:   */    
/* 59:59 */    VoronoiSimplexSolver simplexSolver = new VoronoiSimplexSolver();
/* 60:60 */    GjkEpaPenetrationDepthSolver gjkEpaPenetrationSolver = new GjkEpaPenetrationDepthSolver();
/* 61:   */    
/* 66:66 */    SubsimplexConvexCast convexCaster = new SubsimplexConvexCast(this.convexShape, triangleShape, simplexSolver);
/* 67:   */    
/* 72:72 */    ConvexCast.CastResult castResult = new ConvexCast.CastResult();
/* 73:73 */    castResult.fraction = 1.0F;
/* 74:74 */    if (convexCaster.calcTimeOfImpact(this.convexShapeFrom, this.convexShapeTo, this.triangleToWorld, this.triangleToWorld, castResult))
/* 75:   */    {
/* 76:76 */      if ((castResult.normal.lengthSquared() > 1.0E-004F) && 
/* 77:77 */        (castResult.fraction < this.hitFraction))
/* 78:   */      {
/* 86:86 */        castResult.normal.normalize();
/* 87:   */        
/* 88:88 */        reportHit(castResult.normal, castResult.hitPoint, castResult.fraction, partId, triangleIndex);
/* 89:   */      }
/* 90:   */    }
/* 91:   */  }
/* 92:   */  
/* 93:   */  public abstract float reportHit(Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat, int paramInt1, int paramInt2);
/* 94:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.narrowphase.TriangleConvexcastCallback
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */