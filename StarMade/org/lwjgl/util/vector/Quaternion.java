package org.lwjgl.util.vector;

import java.nio.FloatBuffer;

public class Quaternion
  extends Vector
  implements ReadableVector4f
{
  private static final long serialVersionUID = 1L;
  public float field_140;
  public float field_141;
  public float field_142;
  public float field_143;
  
  public Quaternion()
  {
    setIdentity();
  }
  
  public Quaternion(ReadableVector4f src)
  {
    set(src);
  }
  
  public Quaternion(float local_x, float local_y, float local_z, float local_w)
  {
    set(local_x, local_y, local_z, local_w);
  }
  
  public void set(float local_x, float local_y)
  {
    this.field_140 = local_x;
    this.field_141 = local_y;
  }
  
  public void set(float local_x, float local_y, float local_z)
  {
    this.field_140 = local_x;
    this.field_141 = local_y;
    this.field_142 = local_z;
  }
  
  public void set(float local_x, float local_y, float local_z, float local_w)
  {
    this.field_140 = local_x;
    this.field_141 = local_y;
    this.field_142 = local_z;
    this.field_143 = local_w;
  }
  
  public Quaternion set(ReadableVector4f src)
  {
    this.field_140 = src.getX();
    this.field_141 = src.getY();
    this.field_142 = src.getZ();
    this.field_143 = src.getW();
    return this;
  }
  
  public Quaternion setIdentity()
  {
    return setIdentity(this);
  }
  
  public static Quaternion setIdentity(Quaternion local_q)
  {
    local_q.field_140 = 0.0F;
    local_q.field_141 = 0.0F;
    local_q.field_142 = 0.0F;
    local_q.field_143 = 1.0F;
    return local_q;
  }
  
  public float lengthSquared()
  {
    return this.field_140 * this.field_140 + this.field_141 * this.field_141 + this.field_142 * this.field_142 + this.field_143 * this.field_143;
  }
  
  public static Quaternion normalise(Quaternion src, Quaternion dest)
  {
    float inv_l = 1.0F / src.length();
    if (dest == null) {
      dest = new Quaternion();
    }
    dest.set(src.field_140 * inv_l, src.field_141 * inv_l, src.field_142 * inv_l, src.field_143 * inv_l);
    return dest;
  }
  
  public Quaternion normalise(Quaternion dest)
  {
    return normalise(this, dest);
  }
  
  public static float dot(Quaternion left, Quaternion right)
  {
    return left.field_140 * right.field_140 + left.field_141 * right.field_141 + left.field_142 * right.field_142 + left.field_143 * right.field_143;
  }
  
  public Quaternion negate(Quaternion dest)
  {
    return negate(this, dest);
  }
  
  public static Quaternion negate(Quaternion src, Quaternion dest)
  {
    if (dest == null) {
      dest = new Quaternion();
    }
    dest.field_140 = (-src.field_140);
    dest.field_141 = (-src.field_141);
    dest.field_142 = (-src.field_142);
    dest.field_143 = src.field_143;
    return dest;
  }
  
  public Vector negate()
  {
    return negate(this, this);
  }
  
  public Vector load(FloatBuffer buf)
  {
    this.field_140 = buf.get();
    this.field_141 = buf.get();
    this.field_142 = buf.get();
    this.field_143 = buf.get();
    return this;
  }
  
  public Vector scale(float scale)
  {
    return scale(scale, this, this);
  }
  
  public static Quaternion scale(float scale, Quaternion src, Quaternion dest)
  {
    if (dest == null) {
      dest = new Quaternion();
    }
    src.field_140 *= scale;
    src.field_141 *= scale;
    src.field_142 *= scale;
    src.field_143 *= scale;
    return dest;
  }
  
  public Vector store(FloatBuffer buf)
  {
    buf.put(this.field_140);
    buf.put(this.field_141);
    buf.put(this.field_142);
    buf.put(this.field_143);
    return this;
  }
  
  public final float getX()
  {
    return this.field_140;
  }
  
  public final float getY()
  {
    return this.field_141;
  }
  
  public final void setX(float local_x)
  {
    this.field_140 = local_x;
  }
  
  public final void setY(float local_y)
  {
    this.field_141 = local_y;
  }
  
  public void setZ(float local_z)
  {
    this.field_142 = local_z;
  }
  
  public float getZ()
  {
    return this.field_142;
  }
  
  public void setW(float local_w)
  {
    this.field_143 = local_w;
  }
  
  public float getW()
  {
    return this.field_143;
  }
  
  public String toString()
  {
    return "Quaternion: " + this.field_140 + " " + this.field_141 + " " + this.field_142 + " " + this.field_143;
  }
  
  public static Quaternion mul(Quaternion left, Quaternion right, Quaternion dest)
  {
    if (dest == null) {
      dest = new Quaternion();
    }
    dest.set(left.field_140 * right.field_143 + left.field_143 * right.field_140 + left.field_141 * right.field_142 - left.field_142 * right.field_141, left.field_141 * right.field_143 + left.field_143 * right.field_141 + left.field_142 * right.field_140 - left.field_140 * right.field_142, left.field_142 * right.field_143 + left.field_143 * right.field_142 + left.field_140 * right.field_141 - left.field_141 * right.field_140, left.field_143 * right.field_143 - left.field_140 * right.field_140 - left.field_141 * right.field_141 - left.field_142 * right.field_142);
    return dest;
  }
  
  public static Quaternion mulInverse(Quaternion left, Quaternion right, Quaternion dest)
  {
    float local_n = right.lengthSquared();
    local_n = local_n == 0.0D ? local_n : 1.0F / local_n;
    if (dest == null) {
      dest = new Quaternion();
    }
    dest.set((left.field_140 * right.field_143 - left.field_143 * right.field_140 - left.field_141 * right.field_142 + left.field_142 * right.field_141) * local_n, (left.field_141 * right.field_143 - left.field_143 * right.field_141 - left.field_142 * right.field_140 + left.field_140 * right.field_142) * local_n, (left.field_142 * right.field_143 - left.field_143 * right.field_142 - left.field_140 * right.field_141 + left.field_141 * right.field_140) * local_n, (left.field_143 * right.field_143 + left.field_140 * right.field_140 + left.field_141 * right.field_141 + left.field_142 * right.field_142) * local_n);
    return dest;
  }
  
  public final void setFromAxisAngle(Vector4f local_a1)
  {
    this.field_140 = local_a1.field_140;
    this.field_141 = local_a1.field_141;
    this.field_142 = local_a1.field_142;
    float local_n = (float)Math.sqrt(this.field_140 * this.field_140 + this.field_141 * this.field_141 + this.field_142 * this.field_142);
    float local_s = (float)(Math.sin(0.5D * local_a1.field_143) / local_n);
    this.field_140 *= local_s;
    this.field_141 *= local_s;
    this.field_142 *= local_s;
    this.field_143 = ((float)Math.cos(0.5D * local_a1.field_143));
  }
  
  public final Quaternion setFromMatrix(Matrix4f local_m)
  {
    return setFromMatrix(local_m, this);
  }
  
  public static Quaternion setFromMatrix(Matrix4f local_m, Quaternion local_q)
  {
    return local_q.setFromMat(local_m.m00, local_m.m01, local_m.m02, local_m.m10, local_m.m11, local_m.m12, local_m.m20, local_m.m21, local_m.m22);
  }
  
  public final Quaternion setFromMatrix(Matrix3f local_m)
  {
    return setFromMatrix(local_m, this);
  }
  
  public static Quaternion setFromMatrix(Matrix3f local_m, Quaternion local_q)
  {
    return local_q.setFromMat(local_m.m00, local_m.m01, local_m.m02, local_m.m10, local_m.m11, local_m.m12, local_m.m20, local_m.m21, local_m.m22);
  }
  
  private Quaternion setFromMat(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22)
  {
    float local_tr = m00 + m11 + m22;
    if (local_tr >= 0.0D)
    {
      float local_s = (float)Math.sqrt(local_tr + 1.0D);
      this.field_143 = (local_s * 0.5F);
      local_s = 0.5F / local_s;
      this.field_140 = ((m21 - m12) * local_s);
      this.field_141 = ((m02 - m20) * local_s);
      this.field_142 = ((m10 - m01) * local_s);
    }
    else
    {
      float max = Math.max(Math.max(m00, m11), m22);
      if (max == m00)
      {
        float local_s = (float)Math.sqrt(m00 - (m11 + m22) + 1.0D);
        this.field_140 = (local_s * 0.5F);
        local_s = 0.5F / local_s;
        this.field_141 = ((m01 + m10) * local_s);
        this.field_142 = ((m20 + m02) * local_s);
        this.field_143 = ((m21 - m12) * local_s);
      }
      else if (max == m11)
      {
        float local_s = (float)Math.sqrt(m11 - (m22 + m00) + 1.0D);
        this.field_141 = (local_s * 0.5F);
        local_s = 0.5F / local_s;
        this.field_142 = ((m12 + m21) * local_s);
        this.field_140 = ((m01 + m10) * local_s);
        this.field_143 = ((m02 - m20) * local_s);
      }
      else
      {
        float local_s = (float)Math.sqrt(m22 - (m00 + m11) + 1.0D);
        this.field_142 = (local_s * 0.5F);
        local_s = 0.5F / local_s;
        this.field_140 = ((m20 + m02) * local_s);
        this.field_141 = ((m12 + m21) * local_s);
        this.field_143 = ((m10 - m01) * local_s);
      }
    }
    return this;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.util.vector.Quaternion
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */