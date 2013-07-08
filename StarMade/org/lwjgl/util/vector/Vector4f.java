package org.lwjgl.util.vector;

import java.io.Serializable;
import java.nio.FloatBuffer;

public class Vector4f
  extends Vector
  implements Serializable, ReadableVector4f, WritableVector4f
{
  private static final long serialVersionUID = 1L;
  public float field_140;
  public float field_141;
  public float field_142;
  public float field_143;
  
  public Vector4f() {}
  
  public Vector4f(ReadableVector4f src)
  {
    set(src);
  }
  
  public Vector4f(float local_x, float local_y, float local_z, float local_w)
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
  
  public Vector4f set(ReadableVector4f src)
  {
    this.field_140 = src.getX();
    this.field_141 = src.getY();
    this.field_142 = src.getZ();
    this.field_143 = src.getW();
    return this;
  }
  
  public float lengthSquared()
  {
    return this.field_140 * this.field_140 + this.field_141 * this.field_141 + this.field_142 * this.field_142 + this.field_143 * this.field_143;
  }
  
  public Vector4f translate(float local_x, float local_y, float local_z, float local_w)
  {
    this.field_140 += local_x;
    this.field_141 += local_y;
    this.field_142 += local_z;
    this.field_143 += local_w;
    return this;
  }
  
  public static Vector4f add(Vector4f left, Vector4f right, Vector4f dest)
  {
    if (dest == null) {
      return new Vector4f(left.field_140 + right.field_140, left.field_141 + right.field_141, left.field_142 + right.field_142, left.field_143 + right.field_143);
    }
    dest.set(left.field_140 + right.field_140, left.field_141 + right.field_141, left.field_142 + right.field_142, left.field_143 + right.field_143);
    return dest;
  }
  
  public static Vector4f sub(Vector4f left, Vector4f right, Vector4f dest)
  {
    if (dest == null) {
      return new Vector4f(left.field_140 - right.field_140, left.field_141 - right.field_141, left.field_142 - right.field_142, left.field_143 - right.field_143);
    }
    dest.set(left.field_140 - right.field_140, left.field_141 - right.field_141, left.field_142 - right.field_142, left.field_143 - right.field_143);
    return dest;
  }
  
  public Vector negate()
  {
    this.field_140 = (-this.field_140);
    this.field_141 = (-this.field_141);
    this.field_142 = (-this.field_142);
    this.field_143 = (-this.field_143);
    return this;
  }
  
  public Vector4f negate(Vector4f dest)
  {
    if (dest == null) {
      dest = new Vector4f();
    }
    dest.field_140 = (-this.field_140);
    dest.field_141 = (-this.field_141);
    dest.field_142 = (-this.field_142);
    dest.field_143 = (-this.field_143);
    return dest;
  }
  
  public Vector4f normalise(Vector4f dest)
  {
    float local_l = length();
    if (dest == null) {
      dest = new Vector4f(this.field_140 / local_l, this.field_141 / local_l, this.field_142 / local_l, this.field_143 / local_l);
    } else {
      dest.set(this.field_140 / local_l, this.field_141 / local_l, this.field_142 / local_l, this.field_143 / local_l);
    }
    return dest;
  }
  
  public static float dot(Vector4f left, Vector4f right)
  {
    return left.field_140 * right.field_140 + left.field_141 * right.field_141 + left.field_142 * right.field_142 + left.field_143 * right.field_143;
  }
  
  public static float angle(Vector4f local_a, Vector4f local_b)
  {
    float dls = dot(local_a, local_b) / (local_a.length() * local_b.length());
    if (dls < -1.0F) {
      dls = -1.0F;
    } else if (dls > 1.0F) {
      dls = 1.0F;
    }
    return (float)Math.acos(dls);
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
    this.field_140 *= scale;
    this.field_141 *= scale;
    this.field_142 *= scale;
    this.field_143 *= scale;
    return this;
  }
  
  public Vector store(FloatBuffer buf)
  {
    buf.put(this.field_140);
    buf.put(this.field_141);
    buf.put(this.field_142);
    buf.put(this.field_143);
    return this;
  }
  
  public String toString()
  {
    return "Vector4f: " + this.field_140 + " " + this.field_141 + " " + this.field_142 + " " + this.field_143;
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
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.util.vector.Vector4f
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */