/*    */ package com.bulletphysics.collision.shapes;
/*    */ 
/*    */ import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*    */ import com.bulletphysics.linearmath.Transform;
/*    */ 
/*    */ public class CompoundShapeChild
/*    */ {
/* 36 */   public final Transform transform = new Transform();
/*    */   public CollisionShape childShape;
/*    */   public BroadphaseNativeType childShapeType;
/*    */   public float childMargin;
/*    */ 
/*    */   public boolean equals(Object obj)
/*    */   {
/* 43 */     if ((obj == null) || (!(obj instanceof CompoundShapeChild))) return false;
/* 44 */     CompoundShapeChild child = (CompoundShapeChild)obj;
/* 45 */     return (this.transform.equals(child.transform)) && (this.childShape == child.childShape) && (this.childShapeType == child.childShapeType) && (this.childMargin == child.childMargin);
/*    */   }
/*    */ 
/*    */   public int hashCode()
/*    */   {
/* 53 */     int hash = 7;
/* 54 */     hash = 19 * hash + this.transform.hashCode();
/* 55 */     hash = 19 * hash + this.childShape.hashCode();
/* 56 */     hash = 19 * hash + this.childShapeType.hashCode();
/* 57 */     hash = 19 * hash + Float.floatToIntBits(this.childMargin);
/* 58 */     return hash;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.CompoundShapeChild
 * JD-Core Version:    0.6.2
 */