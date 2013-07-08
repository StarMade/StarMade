package com.bulletphysics.extras.gimpact;

import com.bulletphysics..Stack;
import com.bulletphysics.linearmath.VectorUtil;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;

class GeometryOperations
{
  public static final float PLANEDIREPSILON = 1.0E-007F;
  public static final float PARALELENORMALS = 1.0E-006F;
  
  public static final float CLAMP(float number, float minval, float maxval)
  {
    return number > maxval ? maxval : number < minval ? minval : number;
  }
  
  public static void edge_plane(Vector3f arg0, Vector3f arg1, Vector3f arg2, Vector4f arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f planenormal = localStack.get$javax$vecmath$Vector3f();
      planenormal.sub(local_e2, local_e1);
      planenormal.cross(planenormal, normal);
      planenormal.normalize();
      plane.set(planenormal);
      plane.field_599 = local_e2.dot(planenormal);
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public static void closest_point_on_segment(Vector3f arg0, Vector3f arg1, Vector3f arg2, Vector3f arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f local_n = localStack.get$javax$vecmath$Vector3f();
      local_n.sub(local_e2, local_e1);
      local_cp.sub(local_v, local_e1);
      float _scalar = local_cp.dot(local_n) / local_n.dot(local_n);
      if (_scalar < 0.0F) {
        local_cp = local_e1;
      } else if (_scalar > 1.0F) {
        local_cp = local_e2;
      } else {
        local_cp.scaleAdd(_scalar, local_n, local_e1);
      }
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public static int line_plane_collision(Vector4f plane, Vector3f vDir, Vector3f vPoint, Vector3f pout, float[] tparam, float tmin, float tmax)
  {
    float _dotdir = VectorUtil.dot3(vDir, plane);
    if (Math.abs(_dotdir) < 1.0E-007F)
    {
      tparam[0] = tmax;
      return 0;
    }
    float _dis = ClipPolygon.distance_point_plane(plane, vPoint);
    int returnvalue = _dis < 0.0F ? 2 : 1;
    tparam[0] = (-_dis / _dotdir);
    if (tparam[0] < tmin)
    {
      returnvalue = 0;
      tparam[0] = tmin;
    }
    else if (tparam[0] > tmax)
    {
      returnvalue = 0;
      tparam[0] = tmax;
    }
    pout.scaleAdd(tparam[0], vDir, vPoint);
    return returnvalue;
  }
  
  public static void segment_collision(Vector3f arg0, Vector3f arg1, Vector3f arg2, Vector3f arg3, Vector3f arg4, Vector3f arg5)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$javax$vecmath$Vector3f();
      tmp7_5.push$javax$vecmath$Vector4f();
      Vector3f local_AD = localStack.get$javax$vecmath$Vector3f();
      local_AD.sub(vA2, vA1);
      Vector3f local_BD = localStack.get$javax$vecmath$Vector3f();
      local_BD.sub(vB2, vB1);
      Vector3f local_N = localStack.get$javax$vecmath$Vector3f();
      local_N.cross(local_AD, local_BD);
      float[] local_tp = { local_N.lengthSquared() };
      Vector4f local__M = localStack.get$javax$vecmath$Vector4f();
      if (local_tp[0] < 1.192093E-007F)
      {
        boolean invert_b_order = false;
        local__M.field_596 = vB1.dot(local_AD);
        local__M.field_597 = vB2.dot(local_AD);
        if (local__M.field_596 > local__M.field_597)
        {
          invert_b_order = true;
          local__M.field_596 += local__M.field_597;
          local__M.field_597 = (local__M.field_596 - local__M.field_597);
          local__M.field_596 -= local__M.field_597;
        }
        local__M.field_598 = vA1.dot(local_AD);
        local__M.field_599 = vA2.dot(local_AD);
        local_N.field_615 = ((local__M.field_596 + local__M.field_597) * 0.5F);
        local_N.field_616 = ((local__M.field_598 + local__M.field_599) * 0.5F);
        if (local_N.field_615 < local_N.field_616)
        {
          if (local__M.field_597 < local__M.field_598)
          {
            vPointB = invert_b_order ? vB1 : vB2;
            vPointA = vA1;
          }
          else if (local__M.field_597 < local__M.field_599)
          {
            vPointB = invert_b_order ? vB1 : vB2;
            closest_point_on_segment(vPointA, vPointB, vA1, vA2);
          }
          else
          {
            vPointA = vA2;
            closest_point_on_segment(vPointB, vPointA, vB1, vB2);
          }
        }
        else if (local__M.field_599 < local__M.field_596)
        {
          vPointB = invert_b_order ? vB2 : vB1;
          vPointA = vA2;
        }
        else if (local__M.field_599 < local__M.field_597)
        {
          vPointA = vA2;
          closest_point_on_segment(vPointB, vPointA, vB1, vB2);
        }
        else
        {
          vPointB = invert_b_order ? vB1 : vB2;
          closest_point_on_segment(vPointA, vPointB, vA1, vA2);
        }
        return;
      }
      local_N.cross(local_N, local_BD);
      local__M.set(local_N.field_615, local_N.field_616, local_N.field_617, vB1.dot(local_N));
      line_plane_collision(local__M, local_AD, vA1, vPointA, local_tp, 0.0F, 1.0F);
      vPointB.sub(vPointA, vB1);
      local_tp[0] = vPointB.dot(local_BD);
      local_tp[0] /= local_BD.dot(local_BD);
      local_tp[0] = CLAMP(local_tp[0], 0.0F, 1.0F);
      vPointB.scaleAdd(local_tp[0], local_BD, vB1);
      return;
    }
    finally
    {
      .Stack tmp549_547 = localStack;
      tmp549_547.pop$javax$vecmath$Vector3f();
      tmp549_547.pop$javax$vecmath$Vector4f();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.extras.gimpact.GeometryOperations
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */