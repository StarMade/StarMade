package com.bulletphysics.linearmath;

import com.bulletphysics..Stack;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;

public class AabbUtil2
{
  public static void aabbExpand(Vector3f aabbMin, Vector3f aabbMax, Vector3f expansionMin, Vector3f expansionMax)
  {
    aabbMin.add(expansionMin);
    aabbMax.add(expansionMax);
  }
  
  public static int outcode(Vector3f local_p, Vector3f halfExtent)
  {
    return (local_p.field_615 < -halfExtent.field_615 ? 1 : 0) | (local_p.field_615 > halfExtent.field_615 ? 8 : 0) | (local_p.field_616 < -halfExtent.field_616 ? 2 : 0) | (local_p.field_616 > halfExtent.field_616 ? 16 : 0) | (local_p.field_617 < -halfExtent.field_617 ? 4 : 0) | (local_p.field_617 > halfExtent.field_617 ? 32 : 0);
  }
  
  public static boolean rayAabb(Vector3f arg0, Vector3f arg1, Vector3f arg2, Vector3f arg3, float[] arg4, Vector3f arg5)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f aabbHalfExtent = localStack.get$javax$vecmath$Vector3f();
      Vector3f aabbCenter = localStack.get$javax$vecmath$Vector3f();
      Vector3f source = localStack.get$javax$vecmath$Vector3f();
      Vector3f target = localStack.get$javax$vecmath$Vector3f();
      Vector3f local_r = localStack.get$javax$vecmath$Vector3f();
      Vector3f hitNormal = localStack.get$javax$vecmath$Vector3f();
      aabbHalfExtent.sub(aabbMax, aabbMin);
      aabbHalfExtent.scale(0.5F);
      aabbCenter.add(aabbMax, aabbMin);
      aabbCenter.scale(0.5F);
      source.sub(rayFrom, aabbCenter);
      target.sub(rayTo, aabbCenter);
      int sourceOutcode = outcode(source, aabbHalfExtent);
      int targetOutcode = outcode(target, aabbHalfExtent);
      if ((sourceOutcode & targetOutcode) == 0)
      {
        float lambda_enter = 0.0F;
        float lambda_exit = param[0];
        local_r.sub(target, source);
        float normSign = 1.0F;
        hitNormal.set(0.0F, 0.0F, 0.0F);
        int bit = 1;
        for (int local_j = 0; local_j < 2; local_j++)
        {
          for (int local_i = 0; local_i != 3; local_i++)
          {
            if ((sourceOutcode & bit) != 0)
            {
              float lambda = (-VectorUtil.getCoord(source, local_i) - VectorUtil.getCoord(aabbHalfExtent, local_i) * normSign) / VectorUtil.getCoord(local_r, local_i);
              if (lambda_enter <= lambda)
              {
                lambda_enter = lambda;
                hitNormal.set(0.0F, 0.0F, 0.0F);
                VectorUtil.setCoord(hitNormal, local_i, normSign);
              }
            }
            else if ((targetOutcode & bit) != 0)
            {
              float lambda = (-VectorUtil.getCoord(source, local_i) - VectorUtil.getCoord(aabbHalfExtent, local_i) * normSign) / VectorUtil.getCoord(local_r, local_i);
              lambda_exit = Math.min(lambda_exit, lambda);
            }
            bit <<= 1;
          }
          normSign = -1.0F;
        }
        if (lambda_enter <= lambda_exit)
        {
          param[0] = lambda_enter;
          normal.set(hitNormal);
          return true;
        }
      }
      return false;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public static boolean testAabbAgainstAabb2(Vector3f aabbMin1, Vector3f aabbMax1, Vector3f aabbMin2, Vector3f aabbMax2)
  {
    boolean overlap = true;
    overlap = (aabbMin1.field_615 > aabbMax2.field_615) || (aabbMax1.field_615 < aabbMin2.field_615) ? false : overlap;
    overlap = (aabbMin1.field_617 > aabbMax2.field_617) || (aabbMax1.field_617 < aabbMin2.field_617) ? false : overlap;
    overlap = (aabbMin1.field_616 > aabbMax2.field_616) || (aabbMax1.field_616 < aabbMin2.field_616) ? false : overlap;
    return overlap;
  }
  
  public static boolean testTriangleAgainstAabb2(Vector3f[] vertices, Vector3f aabbMin, Vector3f aabbMax)
  {
    Vector3f local_p1 = vertices[0];
    Vector3f local_p2 = vertices[1];
    Vector3f local_p3 = vertices[2];
    if (Math.min(Math.min(local_p1.field_615, local_p2.field_615), local_p3.field_615) > aabbMax.field_615) {
      return false;
    }
    if (Math.max(Math.max(local_p1.field_615, local_p2.field_615), local_p3.field_615) < aabbMin.field_615) {
      return false;
    }
    if (Math.min(Math.min(local_p1.field_617, local_p2.field_617), local_p3.field_617) > aabbMax.field_617) {
      return false;
    }
    if (Math.max(Math.max(local_p1.field_617, local_p2.field_617), local_p3.field_617) < aabbMin.field_617) {
      return false;
    }
    if (Math.min(Math.min(local_p1.field_616, local_p2.field_616), local_p3.field_616) > aabbMax.field_616) {
      return false;
    }
    return Math.max(Math.max(local_p1.field_616, local_p2.field_616), local_p3.field_616) >= aabbMin.field_616;
  }
  
  public static void transformAabb(Vector3f arg0, float arg1, Transform arg2, Vector3f arg3, Vector3f arg4)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$javax$vecmath$Vector3f();
      tmp7_5.push$javax$vecmath$Matrix3f();
      Vector3f halfExtentsWithMargin = localStack.get$javax$vecmath$Vector3f();
      halfExtents.field_615 += margin;
      halfExtents.field_616 += margin;
      halfExtents.field_617 += margin;
      Matrix3f abs_b = localStack.get$javax$vecmath$Matrix3f(local_t.basis);
      MatrixUtil.absolute(abs_b);
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      Vector3f center = localStack.get$javax$vecmath$Vector3f(local_t.origin);
      Vector3f extent = localStack.get$javax$vecmath$Vector3f();
      abs_b.getRow(0, tmp);
      extent.field_615 = tmp.dot(halfExtentsWithMargin);
      abs_b.getRow(1, tmp);
      extent.field_616 = tmp.dot(halfExtentsWithMargin);
      abs_b.getRow(2, tmp);
      extent.field_617 = tmp.dot(halfExtentsWithMargin);
      aabbMinOut.sub(center, extent);
      aabbMaxOut.add(center, extent);
      return;
    }
    finally
    {
      .Stack tmp186_184 = localStack;
      tmp186_184.pop$javax$vecmath$Vector3f();
      tmp186_184.pop$javax$vecmath$Matrix3f();
    }
  }
  
  public static void transformAabb(Vector3f arg0, Vector3f arg1, float arg2, Transform arg3, Vector3f arg4, Vector3f arg5)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$javax$vecmath$Vector3f();
      tmp7_5.push$javax$vecmath$Matrix3f();
      assert (localAabbMin.field_615 <= localAabbMax.field_615);
      assert (localAabbMin.field_616 <= localAabbMax.field_616);
      assert (localAabbMin.field_617 <= localAabbMax.field_617);
      Vector3f localHalfExtents = localStack.get$javax$vecmath$Vector3f();
      localHalfExtents.sub(localAabbMax, localAabbMin);
      localHalfExtents.scale(0.5F);
      localHalfExtents.field_615 += margin;
      localHalfExtents.field_616 += margin;
      localHalfExtents.field_617 += margin;
      Vector3f localCenter = localStack.get$javax$vecmath$Vector3f();
      localCenter.add(localAabbMax, localAabbMin);
      localCenter.scale(0.5F);
      Matrix3f abs_b = localStack.get$javax$vecmath$Matrix3f(trans.basis);
      MatrixUtil.absolute(abs_b);
      Vector3f center = localStack.get$javax$vecmath$Vector3f(localCenter);
      trans.transform(center);
      Vector3f extent = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      abs_b.getRow(0, tmp);
      extent.field_615 = tmp.dot(localHalfExtents);
      abs_b.getRow(1, tmp);
      extent.field_616 = tmp.dot(localHalfExtents);
      abs_b.getRow(2, tmp);
      extent.field_617 = tmp.dot(localHalfExtents);
      aabbMinOut.sub(center, extent);
      aabbMaxOut.add(center, extent);
      return;
    }
    finally
    {
      .Stack tmp304_302 = localStack;
      tmp304_302.pop$javax$vecmath$Vector3f();
      tmp304_302.pop$javax$vecmath$Matrix3f();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.linearmath.AabbUtil2
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */