/*  1:   */package com.bulletphysics.collision.narrowphase;
/*  2:   */
/*  3:   */import com.bulletphysics.linearmath.IDebugDraw;
/*  4:   */import com.bulletphysics.linearmath.Transform;
/*  5:   */import javax.vecmath.Vector3f;
/*  6:   */
/* 43:   */public abstract class ConvexCast
/* 44:   */{
/* 45:   */  public abstract boolean calcTimeOfImpact(Transform paramTransform1, Transform paramTransform2, Transform paramTransform3, Transform paramTransform4, CastResult paramCastResult);
/* 46:   */  
/* 47:   */  public static class CastResult
/* 48:   */  {
/* 49:49 */    public final Transform hitTransformA = new Transform();
/* 50:50 */    public final Transform hitTransformB = new Transform();
/* 51:   */    
/* 52:52 */    public final Vector3f normal = new Vector3f();
/* 53:53 */    public final Vector3f hitPoint = new Vector3f();
/* 54:54 */    public float fraction = 1.0E+030F;
/* 55:55 */    public float allowedPenetration = 0.0F;
/* 56:   */    public IDebugDraw debugDrawer;
/* 57:   */    
/* 58:   */    public void debugDraw(float fraction) {}
/* 59:   */    
/* 60:   */    public void drawCoordSystem(Transform trans) {}
/* 61:   */  }
/* 62:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.narrowphase.ConvexCast
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */