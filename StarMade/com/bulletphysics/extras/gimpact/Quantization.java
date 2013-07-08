package com.bulletphysics.extras.gimpact;

import com.bulletphysics..Stack;
import com.bulletphysics.linearmath.VectorUtil;
import javax.vecmath.Vector3f;

class Quantization
{
  public static void bt_calc_quantization_parameters(Vector3f arg0, Vector3f arg1, Vector3f arg2, Vector3f arg3, Vector3f arg4, float arg5)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f clampValue = localStack.get$javax$vecmath$Vector3f();
      clampValue.set(quantizationMargin, quantizationMargin, quantizationMargin);
      outMinBound.sub(srcMinBound, clampValue);
      outMaxBound.add(srcMaxBound, clampValue);
      Vector3f aabbSize = localStack.get$javax$vecmath$Vector3f();
      aabbSize.sub(outMaxBound, outMinBound);
      bvhQuantization.set(65535.0F, 65535.0F, 65535.0F);
      VectorUtil.div(bvhQuantization, bvhQuantization, aabbSize);
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public static void bt_quantize_clamp(short[] arg0, Vector3f arg1, Vector3f arg2, Vector3f arg3, Vector3f arg4)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f clampedPoint = localStack.get$javax$vecmath$Vector3f(point);
      VectorUtil.setMax(clampedPoint, min_bound);
      VectorUtil.setMin(clampedPoint, max_bound);
      Vector3f local_v = localStack.get$javax$vecmath$Vector3f();
      local_v.sub(clampedPoint, min_bound);
      VectorUtil.mul(local_v, local_v, bvhQuantization);
      out[0] = ((short)(int)(local_v.field_615 + 0.5F));
      out[1] = ((short)(int)(local_v.field_616 + 0.5F));
      out[2] = ((short)(int)(local_v.field_617 + 0.5F));
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public static Vector3f bt_unquantize(short[] vecIn, Vector3f offset, Vector3f bvhQuantization, Vector3f out)
  {
    out.set((vecIn[0] & 0xFFFF) / bvhQuantization.field_615, (vecIn[1] & 0xFFFF) / bvhQuantization.field_616, (vecIn[2] & 0xFFFF) / bvhQuantization.field_617);
    out.add(offset);
    return out;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.extras.gimpact.Quantization
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */