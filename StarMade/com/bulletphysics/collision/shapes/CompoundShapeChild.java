/*  1:   */package com.bulletphysics.collision.shapes;
/*  2:   */
/*  3:   */import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*  4:   */import com.bulletphysics.linearmath.Transform;
/*  5:   */
/* 34:   */public class CompoundShapeChild
/* 35:   */{
/* 36:36 */  public final Transform transform = new Transform();
/* 37:   */  public CollisionShape childShape;
/* 38:   */  public BroadphaseNativeType childShapeType;
/* 39:   */  public float childMargin;
/* 40:   */  
/* 41:   */  public boolean equals(Object obj)
/* 42:   */  {
/* 43:43 */    if ((obj == null) || (!(obj instanceof CompoundShapeChild))) return false;
/* 44:44 */    CompoundShapeChild child = (CompoundShapeChild)obj;
/* 45:45 */    return (this.transform.equals(child.transform)) && (this.childShape == child.childShape) && (this.childShapeType == child.childShapeType) && (this.childMargin == child.childMargin);
/* 46:   */  }
/* 47:   */  
/* 51:   */  public int hashCode()
/* 52:   */  {
/* 53:53 */    int hash = 7;
/* 54:54 */    hash = 19 * hash + this.transform.hashCode();
/* 55:55 */    hash = 19 * hash + this.childShape.hashCode();
/* 56:56 */    hash = 19 * hash + this.childShapeType.hashCode();
/* 57:57 */    hash = 19 * hash + Float.floatToIntBits(this.childMargin);
/* 58:58 */    return hash;
/* 59:   */  }
/* 60:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.CompoundShapeChild
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */