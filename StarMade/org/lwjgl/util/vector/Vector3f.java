package org.lwjgl.util.vector;

import java.io.Serializable;
import java.nio.FloatBuffer;

public class Vector3f
  extends Vector
  implements Serializable, ReadableVector3f, WritableVector3f
{
  private static final long serialVersionUID = 1L;
  public float field_140;
  public float field_141;
  public float field_142;
  
  public Vector3f() {}
  
  public Vector3f(ReadableVector3f src)
  {
    set(src);
  }
  
  public Vector3f(float local_x, float local_y, float local_z)
  {
    set(local_x, local_y, local_z);
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
  
  public Vector3f set(ReadableVector3f src)
  {
    this.field_140 = src.getX();
    this.field_141 = src.getY();
    this.field_142 = src.getZ();
    return this;
  }
  
  public float lengthSquared()
  {
    return this.field_140 * this.field_140 + this.field_141 * this.field_141 + this.field_142 * this.field_142;
  }
  
  public Vector3f translate(float local_x, float local_y, float local_z)
  {
    this.field_140 += local_x;
    this.field_141 += local_y;
    this.field_142 += local_z;
    return this;
  }
  
  public static Vector3f add(Vector3f left, Vector3f right, Vector3f dest)
  {
    if (dest == null) {
      return new Vector3f(left.field_140 + right.field_140, left.field_141 + right.field_141, left.field_142 + right.field_142);
    }
    dest.set(left.field_140 + right.field_140, left.field_141 + right.field_141, left.field_142 + right.field_142);
    return dest;
  }
  
  public static Vector3f sub(Vector3f left, Vector3f right, Vector3f dest)
  {
    if (dest == null) {
      return new Vector3f(left.field_140 - right.field_140, left.field_141 - right.field_141, left.field_142 - right.field_142);
    }
    dest.set(left.field_140 - right.field_140, left.field_141 - right.field_141, left.field_142 - right.field_142);
    return dest;
  }
  
  public static Vector3f cross(Vector3f left, Vector3f right, Vector3f dest)
  {
    if (dest == null) {
      dest = new Vector3f();
    }
    dest.set(left.field_141 * right.field_142 - left.field_142 * right.field_141, right.field_140 * left.field_142 - right.field_142 * left.field_140, left.field_140 * right.field_141 - left.field_141 * right.field_140);
    return dest;
  }
  
  public Vector negate()
  {
    this.field_140 = (-this.field_140);
    this.field_141 = (-this.field_141);
    this.field_142 = (-this.field_142);
    return this;
  }
  
  public Vector3f negate(Vector3f dest)
  {
    if (dest == null) {
      dest = new Vector3f();
    }
    dest.field_140 = (-this.field_140);
    dest.field_141 = (-this.field_141);
    dest.field_142 = (-this.field_142);
    return dest;
  }
  
  public Vector3f normalise(Vector3f dest)
  {
    float local_l = length();
    if (dest == null) {
      dest = new Vector3f(this.field_140 / local_l, this.field_141 / local_l, this.field_142 / local_l);
    } else {
      dest.set(this.field_140 / local_l, this.field_141 / local_l, this.field_142 / local_l);
    }
    return dest;
  }
  
  public static float dot(Vector3f left, Vector3f right)
  {
    return left.field_140 * right.field_140 + left.field_141 * right.field_141 + left.field_142 * right.field_142;
  }
  
  public static float angle(Vector3f local_a, Vector3f local_b)
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
    return this;
  }
  
  public Vector scale(float scale)
  {
    this.field_140 *= scale;
    this.field_141 *= scale;
    this.field_142 *= scale;
    return this;
  }
  
  public Vector store(FloatBuffer buf)
  {
    buf.put(this.field_140);
    buf.put(this.field_141);
    buf.put(this.field_142);
    return this;
  }
  
  public String toString()
  {
    StringBuilder local_sb = new StringBuilder(64);
    local_sb.append("Vector3f[");
    local_sb.append(this.field_140);
    local_sb.append(", ");
    local_sb.append(this.field_141);
    local_sb.append(", ");
    local_sb.append(this.field_142);
    local_sb.append(']');
    return local_sb.toString();
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
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.util.vector.Vector3f
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */