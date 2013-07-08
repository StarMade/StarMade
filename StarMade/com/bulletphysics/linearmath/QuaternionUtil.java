package com.bulletphysics.linearmath;

import com.bulletphysics..Stack;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

public class QuaternionUtil
{
  public static float getAngle(Quat4f local_q)
  {
    float local_s = 2.0F * (float)Math.acos(local_q.field_599);
    return local_s;
  }
  
  public static void setRotation(Quat4f local_q, Vector3f axis, float angle)
  {
    float local_d = axis.length();
    assert (local_d != 0.0F);
    float local_s = (float)Math.sin(angle * 0.5F) / local_d;
    local_q.set(axis.field_615 * local_s, axis.field_616 * local_s, axis.field_617 * local_s, (float)Math.cos(angle * 0.5F));
  }
  
  public static Quat4f shortestArcQuat(Vector3f arg0, Vector3f arg1, Quat4f arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f local_c = localStack.get$javax$vecmath$Vector3f();
      local_c.cross(local_v0, local_v1);
      float local_d = local_v0.dot(local_v1);
      if (local_d < -0.9999998807907105D)
      {
        out.set(0.0F, 1.0F, 0.0F, 0.0F);
        return out;
      }
      float local_s = (float)Math.sqrt((1.0F + local_d) * 2.0F);
      float local_rs = 1.0F / local_s;
      out.set(local_c.field_615 * local_rs, local_c.field_616 * local_rs, local_c.field_617 * local_rs, local_s * 0.5F);
      return out;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public static void mul(Quat4f local_q, Vector3f local_w)
  {
    float local_rx = local_q.field_599 * local_w.field_615 + local_q.field_597 * local_w.field_617 - local_q.field_598 * local_w.field_616;
    float local_ry = local_q.field_599 * local_w.field_616 + local_q.field_598 * local_w.field_615 - local_q.field_596 * local_w.field_617;
    float local_rz = local_q.field_599 * local_w.field_617 + local_q.field_596 * local_w.field_616 - local_q.field_597 * local_w.field_615;
    float local_rw = -local_q.field_596 * local_w.field_615 - local_q.field_597 * local_w.field_616 - local_q.field_598 * local_w.field_617;
    local_q.set(local_rx, local_ry, local_rz, local_rw);
  }
  
  public static Vector3f quatRotate(Quat4f arg0, Vector3f arg1, Vector3f arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Quat4f();
      Quat4f local_q = localStack.get$javax$vecmath$Quat4f(rotation);
      mul(local_q, local_v);
      Quat4f tmp = localStack.get$javax$vecmath$Quat4f();
      inverse(tmp, rotation);
      local_q.mul(tmp);
      out.set(local_q.field_596, local_q.field_597, local_q.field_598);
      return out;
    }
    finally
    {
      localStack.pop$javax$vecmath$Quat4f();
    }
  }
  
  public static void inverse(Quat4f local_q)
  {
    local_q.field_596 = (-local_q.field_596);
    local_q.field_597 = (-local_q.field_597);
    local_q.field_598 = (-local_q.field_598);
  }
  
  public static void inverse(Quat4f local_q, Quat4f src)
  {
    local_q.field_596 = (-src.field_596);
    local_q.field_597 = (-src.field_597);
    local_q.field_598 = (-src.field_598);
    local_q.field_599 = src.field_599;
  }
  
  public static void setEuler(Quat4f local_q, float yaw, float pitch, float roll)
  {
    float halfYaw = yaw * 0.5F;
    float halfPitch = pitch * 0.5F;
    float halfRoll = roll * 0.5F;
    float cosYaw = (float)Math.cos(halfYaw);
    float sinYaw = (float)Math.sin(halfYaw);
    float cosPitch = (float)Math.cos(halfPitch);
    float sinPitch = (float)Math.sin(halfPitch);
    float cosRoll = (float)Math.cos(halfRoll);
    float sinRoll = (float)Math.sin(halfRoll);
    local_q.field_596 = (cosRoll * sinPitch * cosYaw + sinRoll * cosPitch * sinYaw);
    local_q.field_597 = (cosRoll * cosPitch * sinYaw - sinRoll * sinPitch * cosYaw);
    local_q.field_598 = (sinRoll * cosPitch * cosYaw - cosRoll * sinPitch * sinYaw);
    local_q.field_599 = (cosRoll * cosPitch * cosYaw + sinRoll * sinPitch * sinYaw);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.linearmath.QuaternionUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */