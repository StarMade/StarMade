/*  1:   */package com.bulletphysics.extras.gimpact;
/*  2:   */
/*  3:   */import com.bulletphysics..Stack;
/*  4:   */import com.bulletphysics.collision.shapes.StaticPlaneShape;
/*  5:   */import com.bulletphysics.linearmath.Transform;
/*  6:   */import com.bulletphysics.linearmath.VectorUtil;
/*  7:   */import javax.vecmath.Matrix3f;
/*  8:   */import javax.vecmath.Vector3f;
/*  9:   */import javax.vecmath.Vector4f;
/* 10:   */
/* 40:   */class PlaneShape
/* 41:   */{
/* 42:   */  public static void get_plane_equation(StaticPlaneShape arg0, Vector4f arg1)
/* 43:   */  {
/* 44:44 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 45:45 */      equation.set(shape.getPlaneNormal(tmp));
/* 46:46 */      equation.w = shape.getPlaneConstant();
/* 47:47 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/* 48:   */    } }
/* 49:   */  
/* 50:50 */  public static void get_plane_equation_transformed(StaticPlaneShape arg0, Transform arg1, Vector4f arg2) { .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();get_plane_equation(shape, equation);
/* 51:   */      
/* 52:52 */      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 53:   */      
/* 54:54 */      trans.basis.getRow(0, tmp);
/* 55:55 */      float x = VectorUtil.dot3(tmp, equation);
/* 56:56 */      trans.basis.getRow(1, tmp);
/* 57:57 */      float y = VectorUtil.dot3(tmp, equation);
/* 58:58 */      trans.basis.getRow(2, tmp);
/* 59:59 */      float z = VectorUtil.dot3(tmp, equation);
/* 60:   */      
/* 61:61 */      float w = VectorUtil.dot3(trans.origin, equation) + equation.w;
/* 62:   */      
/* 63:63 */      equation.set(x, y, z, w);
/* 64:64 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/* 65:   */    }
/* 66:   */  }
/* 67:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.PlaneShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */