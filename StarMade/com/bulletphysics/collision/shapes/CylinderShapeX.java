/*    */ package com.bulletphysics.collision.shapes;
/*    */ 
/*    */ import com.bulletphysics..Stack;
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ public class CylinderShapeX extends CylinderShape
/*    */ {
/*    */   public CylinderShapeX(Vector3f halfExtents)
/*    */   {
/* 37 */     super(halfExtents, false);
/* 38 */     this.upAxis = 0;
/* 39 */     recalcLocalAabb();
/*    */   }
/*    */ 
/*    */   public Vector3f localGetSupportingVertexWithoutMargin(Vector3f arg1, Vector3f arg2)
/*    */   {
/* 44 */     .Stack localStack = .Stack.get();
/*    */     try { localStack.push$javax$vecmath$Vector3f(); return cylinderLocalSupportX(getHalfExtentsWithoutMargin(localStack.get$javax$vecmath$Vector3f()), vec, out); } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*    */   }
/*    */ 
/*    */   public void batchedUnitVectorGetSupportingVertexWithoutMargin(Vector3f[] arg1, Vector3f[] arg2, int arg3)
/*    */   {
/* 49 */     .Stack localStack = .Stack.get();
/*    */     try { localStack.push$javax$vecmath$Vector3f(); for (int i = 0; i < numVectors; i++)
/* 50 */         cylinderLocalSupportX(getHalfExtentsWithoutMargin(localStack.get$javax$vecmath$Vector3f()), vectors[i], supportVerticesOut[i]);
/*    */       return; } finally {
/* 52 */       localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*    */   }
/*    */ 
/*    */   public float getRadius() {
/* 56 */     .Stack localStack = .Stack.get();
/*    */     try { localStack.push$javax$vecmath$Vector3f(); return getHalfExtentsWithMargin(localStack.get$javax$vecmath$Vector3f()).y; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*    */   }
/*    */ 
/*    */   public String getName()
/*    */   {
/* 61 */     return "CylinderX";
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.CylinderShapeX
 * JD-Core Version:    0.6.2
 */