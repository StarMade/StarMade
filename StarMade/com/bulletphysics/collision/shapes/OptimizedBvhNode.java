/*  1:   */package com.bulletphysics.collision.shapes;
/*  2:   */
/*  3:   */import java.io.Serializable;
/*  4:   */import javax.vecmath.Vector3f;
/*  5:   */
/* 34:   */public class OptimizedBvhNode
/* 35:   */  implements Serializable
/* 36:   */{
/* 37:   */  private static final long serialVersionUID = 1L;
/* 38:38 */  public final Vector3f aabbMinOrg = new Vector3f();
/* 39:39 */  public final Vector3f aabbMaxOrg = new Vector3f();
/* 40:   */  
/* 41:   */  public int escapeIndex;
/* 42:   */  
/* 43:   */  public int subPart;
/* 44:   */  public int triangleIndex;
/* 45:   */  
/* 46:   */  public void set(OptimizedBvhNode n)
/* 47:   */  {
/* 48:48 */    this.aabbMinOrg.set(n.aabbMinOrg);
/* 49:49 */    this.aabbMaxOrg.set(n.aabbMaxOrg);
/* 50:50 */    this.escapeIndex = n.escapeIndex;
/* 51:51 */    this.subPart = n.subPart;
/* 52:52 */    this.triangleIndex = n.triangleIndex;
/* 53:   */  }
/* 54:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.OptimizedBvhNode
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */