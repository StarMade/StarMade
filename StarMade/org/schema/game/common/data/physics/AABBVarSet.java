/*  1:   */package org.schema.game.common.data.physics;
/*  2:   */
/*  3:   */import javax.vecmath.Matrix3f;
/*  4:   */import javax.vecmath.Vector3f;
/*  5:   */
/*  6:   */public class AABBVarSet
/*  7:   */{
/*  8: 8 */  public final Vector3f localHalfExtents = new Vector3f();
/*  9: 9 */  public final Vector3f localCenter = new Vector3f();
/* 10:10 */  public final Matrix3f abs_b = new Matrix3f();
/* 11:11 */  public final Vector3f center = new Vector3f();
/* 12:12 */  public final Vector3f extent = new Vector3f();
/* 13:13 */  public final Vector3f tmp = new Vector3f();
/* 14:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.AABBVarSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */