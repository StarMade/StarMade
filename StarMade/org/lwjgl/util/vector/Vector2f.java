package org.lwjgl.util.vector;

import java.io.Serializable;
import java.nio.FloatBuffer;

public class Vector2f
  extends Vector
  implements Serializable, ReadableVector2f, WritableVector2f
{
  private static final long serialVersionUID = 1L;
  public float field_140;
  public float field_141;
  
  public Vector2f() {}
  
  public Vector2f(ReadableVector2f src)
  {
    set(src);
  }
  
  public Vector2f(float local_x, float local_y)
  {
    set(local_x, local_y);
  }
  
  public void set(float local_x, float local_y)
  {
    this.field_140 = local_x;
    this.field_141 = local_y;
  }
  
  public Vector2f set(ReadableVector2f src)
  {
    this.field_140 = src.getX();
    this.field_141 = src.getY();
    return this;
  }
  
  public float lengthSquared()
  {
    return this.field_140 * this.field_140 + this.field_141 * this.field_141;
  }
  
  public Vector2f translate(float local_x, float local_y)
  {
    this.field_140 += local_x;
    this.field_141 += local_y;
    return this;
  }
  
  public Vector negate()
  {
    this.field_140 = (-this.field_140);
    this.field_141 = (-this.field_141);
    return this;
  }
  
  public Vector2f negate(Vector2f dest)
  {
    if (dest == null) {
      dest = new Vector2f();
    }
    dest.field_140 = (-this.field_140);
    dest.field_141 = (-this.field_141);
    return dest;
  }
  
  public Vector2f normalise(Vector2f dest)
  {
    float local_l = length();
    if (dest == null) {
      dest = new Vector2f(this.field_140 / local_l, this.field_141 / local_l);
    } else {
      dest.set(this.field_140 / local_l, this.field_141 / local_l);
    }
    return dest;
  }
  
  public static float dot(Vector2f left, Vector2f right)
  {
    return left.field_140 * right.field_140 + left.field_141 * right.field_141;
  }
  
  public static float angle(Vector2f local_a, Vector2f local_b)
  {
    float dls = dot(local_a, local_b) / (local_a.length() * local_b.length());
    if (dls < -1.0F) {
      dls = -1.0F;
    } else if (dls > 1.0F) {
      dls = 1.0F;
    }
    return (float)Math.acos(dls);
  }
  
  public static Vector2f add(Vector2f left, Vector2f right, Vector2f dest)
  {
    if (dest == null) {
      return new Vector2f(left.field_140 + right.field_140, left.field_141 + right.field_141);
    }
    dest.set(left.field_140 + right.field_140, left.field_141 + right.field_141);
    return dest;
  }
  
  public static Vector2f sub(Vector2f left, Vector2f right, Vector2f dest)
  {
    if (dest == null) {
      return new Vector2f(left.field_140 - right.field_140, left.field_141 - right.field_141);
    }
    dest.set(left.field_140 - right.field_140, left.field_141 - right.field_141);
    return dest;
  }
  
  public Vector store(FloatBuffer buf)
  {
    buf.put(this.field_140);
    buf.put(this.field_141);
    return this;
  }
  
  public Vector load(FloatBuffer buf)
  {
    this.field_140 = buf.get();
    this.field_141 = buf.get();
    return this;
  }
  
  public Vector scale(float scale)
  {
    this.field_140 *= scale;
    this.field_141 *= scale;
    return this;
  }
  
  public String toString()
  {
    StringBuilder local_sb = new StringBuilder(64);
    local_sb.append("Vector2f[");
    local_sb.append(this.field_140);
    local_sb.append(", ");
    local_sb.append(this.field_141);
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
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.util.vector.Vector2f
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */