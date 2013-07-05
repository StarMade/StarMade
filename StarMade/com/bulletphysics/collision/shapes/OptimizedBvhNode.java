/*    */ package com.bulletphysics.collision.shapes;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ public class OptimizedBvhNode
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 38 */   public final Vector3f aabbMinOrg = new Vector3f();
/* 39 */   public final Vector3f aabbMaxOrg = new Vector3f();
/*    */   public int escapeIndex;
/*    */   public int subPart;
/*    */   public int triangleIndex;
/*    */ 
/*    */   public void set(OptimizedBvhNode n)
/*    */   {
/* 48 */     this.aabbMinOrg.set(n.aabbMinOrg);
/* 49 */     this.aabbMaxOrg.set(n.aabbMaxOrg);
/* 50 */     this.escapeIndex = n.escapeIndex;
/* 51 */     this.subPart = n.subPart;
/* 52 */     this.triangleIndex = n.triangleIndex;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.OptimizedBvhNode
 * JD-Core Version:    0.6.2
 */