package com.bulletphysics.extras.gimpact;

import com.bulletphysics..Stack;
import com.bulletphysics.collision.shapes.StaticPlaneShape;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.linearmath.VectorUtil;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;

class PlaneShape
{
  public static void get_plane_equation(StaticPlaneShape arg0, Vector4f arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      equation.set(shape.getPlaneNormal(tmp));
      equation.field_599 = shape.getPlaneConstant();
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public static void get_plane_equation_transformed(StaticPlaneShape arg0, Transform arg1, Vector4f arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      get_plane_equation(shape, equation);
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      trans.basis.getRow(0, tmp);
      float local_x = VectorUtil.dot3(tmp, equation);
      trans.basis.getRow(1, tmp);
      float local_y = VectorUtil.dot3(tmp, equation);
      trans.basis.getRow(2, tmp);
      float local_z = VectorUtil.dot3(tmp, equation);
      float local_w = VectorUtil.dot3(trans.origin, equation) + equation.field_599;
      equation.set(local_x, local_y, local_z, local_w);
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.extras.gimpact.PlaneShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */