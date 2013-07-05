/*    */ package org.schema.game.common.data.physics;
/*    */ 
/*    */ import javax.vecmath.Matrix3f;
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ public class AABBVarSet
/*    */ {
/*  8 */   public final Vector3f localHalfExtents = new Vector3f();
/*  9 */   public final Vector3f localCenter = new Vector3f();
/* 10 */   public final Matrix3f abs_b = new Matrix3f();
/* 11 */   public final Vector3f center = new Vector3f();
/* 12 */   public final Vector3f extent = new Vector3f();
/* 13 */   public final Vector3f tmp = new Vector3f();
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.AABBVarSet
 * JD-Core Version:    0.6.2
 */