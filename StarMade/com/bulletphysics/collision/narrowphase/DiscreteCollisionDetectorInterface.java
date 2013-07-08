/*  1:   */package com.bulletphysics.collision.narrowphase;
/*  2:   */
/*  3:   */import com.bulletphysics.linearmath.IDebugDraw;
/*  4:   */import com.bulletphysics.linearmath.Transform;
/*  5:   */import javax.vecmath.Vector3f;
/*  6:   */
/* 46:   */public abstract class DiscreteCollisionDetectorInterface
/* 47:   */{
/* 48:   */  public static class ClosestPointInput
/* 49:   */  {
/* 50:50 */    public final Transform transformA = new Transform();
/* 51:51 */    public final Transform transformB = new Transform();
/* 52:   */    public float maximumDistanceSquared;
/* 53:   */    
/* 54:   */    public ClosestPointInput()
/* 55:   */    {
/* 56:56 */      init();
/* 57:   */    }
/* 58:   */    
/* 59:   */    public void init() {
/* 60:60 */      this.maximumDistanceSquared = 3.4028235E+38F;
/* 61:   */    }
/* 62:   */  }
/* 63:   */  
/* 67:   */  public final void getClosestPoints(ClosestPointInput input, Result output, IDebugDraw debugDraw)
/* 68:   */  {
/* 69:69 */    getClosestPoints(input, output, debugDraw, false);
/* 70:   */  }
/* 71:   */  
/* 72:   */  public abstract void getClosestPoints(ClosestPointInput paramClosestPointInput, Result paramResult, IDebugDraw paramIDebugDraw, boolean paramBoolean);
/* 73:   */  
/* 74:   */  public static abstract class Result
/* 75:   */  {
/* 76:   */    public abstract void setShapeIdentifiers(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
/* 77:   */    
/* 78:   */    public abstract void addContactPoint(Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat);
/* 79:   */  }
/* 80:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.narrowphase.DiscreteCollisionDetectorInterface
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */