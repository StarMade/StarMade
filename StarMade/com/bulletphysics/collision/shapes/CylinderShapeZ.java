/*    */ package com.bulletphysics.collision.shapes;
/*    */ 
/*    */ import com.bulletphysics..Stack;
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ public class CylinderShapeZ extends CylinderShape
/*    */ {
/*    */   public CylinderShapeZ(Vector3f halfExtents)
/*    */   {
/* 37 */     super(halfExtents, false);
/* 38 */     this.upAxis = 2;
/* 39 */     recalcLocalAabb();
/*    */   }
/*    */ 
/*    */   public Vector3f localGetSupportingVertexWithoutMargin(Vector3f arg1, Vector3f arg2)
/*    */   {
/* 44 */     .Stack localStack = .Stack.get();
/*    */     try { localStack.push$javax$vecmath$Vector3f(); return cylinderLocalSupportZ(getHalfExtentsWithoutMargin(localStack.get$javax$vecmath$Vector3f()), vec, out); } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*    */   }
/*    */ 
/*    */   public void batchedUnitVectorGetSupportingVertexWithoutMargin(Vector3f[] arg1, Vector3f[] arg2, int arg3)
/*    */   {
/* 49 */     .Stack localStack = .Stack.get();
/*    */     try { localStack.push$javax$vecmath$Vector3f(); for (int i = 0; i < numVectors; i++)
/* 50 */         cylinderLocalSupportZ(getHalfExtentsWithoutMargin(localStack.get$javax$vecmath$Vector3f()), vectors[i], supportVerticesOut[i]);
/*    */       return; } finally {
/* 52 */       localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*    */   }
/*    */ 
/*    */   public float getRadius() {
/* 56 */     .Stack localStack = .Stack.get();
/*    */     try { localStack.push$javax$vecmath$Vector3f(); return getHalfExtentsWithMargin(localStack.get$javax$vecmath$Vector3f()).x; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*    */   }
/*    */ 
/*    */   public String getName()
/*    */   {
/* 61 */     return "CylinderZ";
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.CylinderShapeZ
 * JD-Core Version:    0.6.2
 */