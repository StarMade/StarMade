package com.bulletphysics.linearmath;

import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;

public class VectorUtil
{
  public static int maxAxis(Vector3f local_v)
  {
    int maxIndex = -1;
    float maxVal = -1.0E+030F;
    if (local_v.field_615 > maxVal)
    {
      maxIndex = 0;
      maxVal = local_v.field_615;
    }
    if (local_v.field_616 > maxVal)
    {
      maxIndex = 1;
      maxVal = local_v.field_616;
    }
    if (local_v.field_617 > maxVal)
    {
      maxIndex = 2;
      maxVal = local_v.field_617;
    }
    return maxIndex;
  }
  
  public static int maxAxis4(Vector4f local_v)
  {
    int maxIndex = -1;
    float maxVal = -1.0E+030F;
    if (local_v.field_596 > maxVal)
    {
      maxIndex = 0;
      maxVal = local_v.field_596;
    }
    if (local_v.field_597 > maxVal)
    {
      maxIndex = 1;
      maxVal = local_v.field_597;
    }
    if (local_v.field_598 > maxVal)
    {
      maxIndex = 2;
      maxVal = local_v.field_598;
    }
    if (local_v.field_599 > maxVal)
    {
      maxIndex = 3;
      maxVal = local_v.field_599;
    }
    return maxIndex;
  }
  
  public static int closestAxis4(Vector4f vec)
  {
    Vector4f tmp = new Vector4f(vec);
    tmp.absolute();
    return maxAxis4(tmp);
  }
  
  public static float getCoord(Vector3f vec, int num)
  {
    switch (num)
    {
    case 0: 
      return vec.field_615;
    case 1: 
      return vec.field_616;
    case 2: 
      return vec.field_617;
    }
    throw new InternalError();
  }
  
  public static void setCoord(Vector3f vec, int num, float value)
  {
    switch (num)
    {
    case 0: 
      vec.field_615 = value;
      break;
    case 1: 
      vec.field_616 = value;
      break;
    case 2: 
      vec.field_617 = value;
      break;
    default: 
      throw new InternalError();
    }
  }
  
  public static void mulCoord(Vector3f vec, int num, float value)
  {
    switch (num)
    {
    case 0: 
      vec.field_615 *= value;
      break;
    case 1: 
      vec.field_616 *= value;
      break;
    case 2: 
      vec.field_617 *= value;
      break;
    default: 
      throw new InternalError();
    }
  }
  
  public static void setInterpolate3(Vector3f dest, Vector3f local_v0, Vector3f local_v1, float local_rt)
  {
    float local_s = 1.0F - local_rt;
    dest.field_615 = (local_s * local_v0.field_615 + local_rt * local_v1.field_615);
    dest.field_616 = (local_s * local_v0.field_616 + local_rt * local_v1.field_616);
    dest.field_617 = (local_s * local_v0.field_617 + local_rt * local_v1.field_617);
  }
  
  public static void add(Vector3f dest, Vector3f local_v1, Vector3f local_v2)
  {
    local_v1.field_615 += local_v2.field_615;
    local_v1.field_616 += local_v2.field_616;
    local_v1.field_617 += local_v2.field_617;
  }
  
  public static void add(Vector3f dest, Vector3f local_v1, Vector3f local_v2, Vector3f local_v3)
  {
    dest.field_615 = (local_v1.field_615 + local_v2.field_615 + local_v3.field_615);
    dest.field_616 = (local_v1.field_616 + local_v2.field_616 + local_v3.field_616);
    dest.field_617 = (local_v1.field_617 + local_v2.field_617 + local_v3.field_617);
  }
  
  public static void add(Vector3f dest, Vector3f local_v1, Vector3f local_v2, Vector3f local_v3, Vector3f local_v4)
  {
    dest.field_615 = (local_v1.field_615 + local_v2.field_615 + local_v3.field_615 + local_v4.field_615);
    dest.field_616 = (local_v1.field_616 + local_v2.field_616 + local_v3.field_616 + local_v4.field_616);
    dest.field_617 = (local_v1.field_617 + local_v2.field_617 + local_v3.field_617 + local_v4.field_617);
  }
  
  public static void mul(Vector3f dest, Vector3f local_v1, Vector3f local_v2)
  {
    local_v1.field_615 *= local_v2.field_615;
    local_v1.field_616 *= local_v2.field_616;
    local_v1.field_617 *= local_v2.field_617;
  }
  
  public static void div(Vector3f dest, Vector3f local_v1, Vector3f local_v2)
  {
    local_v1.field_615 /= local_v2.field_615;
    local_v1.field_616 /= local_v2.field_616;
    local_v1.field_617 /= local_v2.field_617;
  }
  
  public static void setMin(Vector3f local_a, Vector3f local_b)
  {
    local_a.field_615 = Math.min(local_a.field_615, local_b.field_615);
    local_a.field_616 = Math.min(local_a.field_616, local_b.field_616);
    local_a.field_617 = Math.min(local_a.field_617, local_b.field_617);
  }
  
  public static void setMax(Vector3f local_a, Vector3f local_b)
  {
    local_a.field_615 = Math.max(local_a.field_615, local_b.field_615);
    local_a.field_616 = Math.max(local_a.field_616, local_b.field_616);
    local_a.field_617 = Math.max(local_a.field_617, local_b.field_617);
  }
  
  public static float dot3(Vector4f local_v0, Vector3f local_v1)
  {
    return local_v0.field_596 * local_v1.field_615 + local_v0.field_597 * local_v1.field_616 + local_v0.field_598 * local_v1.field_617;
  }
  
  public static float dot3(Vector4f local_v0, Vector4f local_v1)
  {
    return local_v0.field_596 * local_v1.field_596 + local_v0.field_597 * local_v1.field_597 + local_v0.field_598 * local_v1.field_598;
  }
  
  public static float dot3(Vector3f local_v0, Vector4f local_v1)
  {
    return local_v0.field_615 * local_v1.field_596 + local_v0.field_616 * local_v1.field_597 + local_v0.field_617 * local_v1.field_598;
  }
  
  public static float lengthSquared3(Vector4f local_v)
  {
    return local_v.field_596 * local_v.field_596 + local_v.field_597 * local_v.field_597 + local_v.field_598 * local_v.field_598;
  }
  
  public static void normalize3(Vector4f local_v)
  {
    float norm = (float)(1.0D / Math.sqrt(local_v.field_596 * local_v.field_596 + local_v.field_597 * local_v.field_597 + local_v.field_598 * local_v.field_598));
    local_v.field_596 *= norm;
    local_v.field_597 *= norm;
    local_v.field_598 *= norm;
  }
  
  public static void cross3(Vector3f dest, Vector4f local_v1, Vector4f local_v2)
  {
    float local_x = local_v1.field_597 * local_v2.field_598 - local_v1.field_598 * local_v2.field_597;
    float local_y = local_v2.field_596 * local_v1.field_598 - local_v2.field_598 * local_v1.field_596;
    dest.field_617 = (local_v1.field_596 * local_v2.field_597 - local_v1.field_597 * local_v2.field_596);
    dest.field_615 = local_x;
    dest.field_616 = local_y;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.linearmath.VectorUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */