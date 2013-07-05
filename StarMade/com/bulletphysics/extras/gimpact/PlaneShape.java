/*    */ package com.bulletphysics.extras.gimpact;
/*    */ 
/*    */ import com.bulletphysics..Stack;
/*    */ import com.bulletphysics.collision.shapes.StaticPlaneShape;
/*    */ import com.bulletphysics.linearmath.Transform;
/*    */ import com.bulletphysics.linearmath.VectorUtil;
/*    */ import javax.vecmath.Matrix3f;
/*    */ import javax.vecmath.Vector3f;
/*    */ import javax.vecmath.Vector4f;
/*    */ 
/*    */ class PlaneShape
/*    */ {
/*    */   public static void get_plane_equation(StaticPlaneShape arg0, Vector4f arg1)
/*    */   {
/* 44 */     .Stack localStack = .Stack.get();
/*    */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 45 */       equation.set(shape.getPlaneNormal(tmp));
/* 46 */       equation.w = shape.getPlaneConstant();
/*    */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*    */   }
/*    */   public static void get_plane_equation_transformed(StaticPlaneShape arg0, Transform arg1, Vector4f arg2) {
/* 50 */     .Stack localStack = .Stack.get();
/*    */     try { localStack.push$javax$vecmath$Vector3f(); get_plane_equation(shape, equation);
/*    */ 
/* 52 */       Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*    */ 
/* 54 */       trans.basis.getRow(0, tmp);
/* 55 */       float x = VectorUtil.dot3(tmp, equation);
/* 56 */       trans.basis.getRow(1, tmp);
/* 57 */       float y = VectorUtil.dot3(tmp, equation);
/* 58 */       trans.basis.getRow(2, tmp);
/* 59 */       float z = VectorUtil.dot3(tmp, equation);
/*    */ 
/* 61 */       float w = VectorUtil.dot3(trans.origin, equation) + equation.w;
/*    */ 
/* 63 */       equation.set(x, y, z, w);
/*    */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.PlaneShape
 * JD-Core Version:    0.6.2
 */