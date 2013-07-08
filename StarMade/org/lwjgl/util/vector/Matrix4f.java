package org.lwjgl.util.vector;

import java.io.Serializable;
import java.nio.FloatBuffer;

public class Matrix4f
  extends Matrix
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  public float m00;
  public float m01;
  public float m02;
  public float m03;
  public float m10;
  public float m11;
  public float m12;
  public float m13;
  public float m20;
  public float m21;
  public float m22;
  public float m23;
  public float m30;
  public float m31;
  public float m32;
  public float m33;
  
  public Matrix4f()
  {
    setIdentity();
  }
  
  public Matrix4f(Matrix4f src)
  {
    load(src);
  }
  
  public String toString()
  {
    StringBuilder buf = new StringBuilder();
    buf.append(this.m00).append(' ').append(this.m10).append(' ').append(this.m20).append(' ').append(this.m30).append('\n');
    buf.append(this.m01).append(' ').append(this.m11).append(' ').append(this.m21).append(' ').append(this.m31).append('\n');
    buf.append(this.m02).append(' ').append(this.m12).append(' ').append(this.m22).append(' ').append(this.m32).append('\n');
    buf.append(this.m03).append(' ').append(this.m13).append(' ').append(this.m23).append(' ').append(this.m33).append('\n');
    return buf.toString();
  }
  
  public Matrix setIdentity()
  {
    return setIdentity(this);
  }
  
  public static Matrix4f setIdentity(Matrix4f local_m)
  {
    local_m.m00 = 1.0F;
    local_m.m01 = 0.0F;
    local_m.m02 = 0.0F;
    local_m.m03 = 0.0F;
    local_m.m10 = 0.0F;
    local_m.m11 = 1.0F;
    local_m.m12 = 0.0F;
    local_m.m13 = 0.0F;
    local_m.m20 = 0.0F;
    local_m.m21 = 0.0F;
    local_m.m22 = 1.0F;
    local_m.m23 = 0.0F;
    local_m.m30 = 0.0F;
    local_m.m31 = 0.0F;
    local_m.m32 = 0.0F;
    local_m.m33 = 1.0F;
    return local_m;
  }
  
  public Matrix setZero()
  {
    return setZero(this);
  }
  
  public static Matrix4f setZero(Matrix4f local_m)
  {
    local_m.m00 = 0.0F;
    local_m.m01 = 0.0F;
    local_m.m02 = 0.0F;
    local_m.m03 = 0.0F;
    local_m.m10 = 0.0F;
    local_m.m11 = 0.0F;
    local_m.m12 = 0.0F;
    local_m.m13 = 0.0F;
    local_m.m20 = 0.0F;
    local_m.m21 = 0.0F;
    local_m.m22 = 0.0F;
    local_m.m23 = 0.0F;
    local_m.m30 = 0.0F;
    local_m.m31 = 0.0F;
    local_m.m32 = 0.0F;
    local_m.m33 = 0.0F;
    return local_m;
  }
  
  public Matrix4f load(Matrix4f src)
  {
    return load(src, this);
  }
  
  public static Matrix4f load(Matrix4f src, Matrix4f dest)
  {
    if (dest == null) {
      dest = new Matrix4f();
    }
    dest.m00 = src.m00;
    dest.m01 = src.m01;
    dest.m02 = src.m02;
    dest.m03 = src.m03;
    dest.m10 = src.m10;
    dest.m11 = src.m11;
    dest.m12 = src.m12;
    dest.m13 = src.m13;
    dest.m20 = src.m20;
    dest.m21 = src.m21;
    dest.m22 = src.m22;
    dest.m23 = src.m23;
    dest.m30 = src.m30;
    dest.m31 = src.m31;
    dest.m32 = src.m32;
    dest.m33 = src.m33;
    return dest;
  }
  
  public Matrix load(FloatBuffer buf)
  {
    this.m00 = buf.get();
    this.m01 = buf.get();
    this.m02 = buf.get();
    this.m03 = buf.get();
    this.m10 = buf.get();
    this.m11 = buf.get();
    this.m12 = buf.get();
    this.m13 = buf.get();
    this.m20 = buf.get();
    this.m21 = buf.get();
    this.m22 = buf.get();
    this.m23 = buf.get();
    this.m30 = buf.get();
    this.m31 = buf.get();
    this.m32 = buf.get();
    this.m33 = buf.get();
    return this;
  }
  
  public Matrix loadTranspose(FloatBuffer buf)
  {
    this.m00 = buf.get();
    this.m10 = buf.get();
    this.m20 = buf.get();
    this.m30 = buf.get();
    this.m01 = buf.get();
    this.m11 = buf.get();
    this.m21 = buf.get();
    this.m31 = buf.get();
    this.m02 = buf.get();
    this.m12 = buf.get();
    this.m22 = buf.get();
    this.m32 = buf.get();
    this.m03 = buf.get();
    this.m13 = buf.get();
    this.m23 = buf.get();
    this.m33 = buf.get();
    return this;
  }
  
  public Matrix store(FloatBuffer buf)
  {
    buf.put(this.m00);
    buf.put(this.m01);
    buf.put(this.m02);
    buf.put(this.m03);
    buf.put(this.m10);
    buf.put(this.m11);
    buf.put(this.m12);
    buf.put(this.m13);
    buf.put(this.m20);
    buf.put(this.m21);
    buf.put(this.m22);
    buf.put(this.m23);
    buf.put(this.m30);
    buf.put(this.m31);
    buf.put(this.m32);
    buf.put(this.m33);
    return this;
  }
  
  public Matrix storeTranspose(FloatBuffer buf)
  {
    buf.put(this.m00);
    buf.put(this.m10);
    buf.put(this.m20);
    buf.put(this.m30);
    buf.put(this.m01);
    buf.put(this.m11);
    buf.put(this.m21);
    buf.put(this.m31);
    buf.put(this.m02);
    buf.put(this.m12);
    buf.put(this.m22);
    buf.put(this.m32);
    buf.put(this.m03);
    buf.put(this.m13);
    buf.put(this.m23);
    buf.put(this.m33);
    return this;
  }
  
  public Matrix store3f(FloatBuffer buf)
  {
    buf.put(this.m00);
    buf.put(this.m01);
    buf.put(this.m02);
    buf.put(this.m10);
    buf.put(this.m11);
    buf.put(this.m12);
    buf.put(this.m20);
    buf.put(this.m21);
    buf.put(this.m22);
    return this;
  }
  
  public static Matrix4f add(Matrix4f left, Matrix4f right, Matrix4f dest)
  {
    if (dest == null) {
      dest = new Matrix4f();
    }
    left.m00 += right.m00;
    left.m01 += right.m01;
    left.m02 += right.m02;
    left.m03 += right.m03;
    left.m10 += right.m10;
    left.m11 += right.m11;
    left.m12 += right.m12;
    left.m13 += right.m13;
    left.m20 += right.m20;
    left.m21 += right.m21;
    left.m22 += right.m22;
    left.m23 += right.m23;
    left.m30 += right.m30;
    left.m31 += right.m31;
    left.m32 += right.m32;
    left.m33 += right.m33;
    return dest;
  }
  
  public static Matrix4f sub(Matrix4f left, Matrix4f right, Matrix4f dest)
  {
    if (dest == null) {
      dest = new Matrix4f();
    }
    left.m00 -= right.m00;
    left.m01 -= right.m01;
    left.m02 -= right.m02;
    left.m03 -= right.m03;
    left.m10 -= right.m10;
    left.m11 -= right.m11;
    left.m12 -= right.m12;
    left.m13 -= right.m13;
    left.m20 -= right.m20;
    left.m21 -= right.m21;
    left.m22 -= right.m22;
    left.m23 -= right.m23;
    left.m30 -= right.m30;
    left.m31 -= right.m31;
    left.m32 -= right.m32;
    left.m33 -= right.m33;
    return dest;
  }
  
  public static Matrix4f mul(Matrix4f left, Matrix4f right, Matrix4f dest)
  {
    if (dest == null) {
      dest = new Matrix4f();
    }
    float m00 = left.m00 * right.m00 + left.m10 * right.m01 + left.m20 * right.m02 + left.m30 * right.m03;
    float m01 = left.m01 * right.m00 + left.m11 * right.m01 + left.m21 * right.m02 + left.m31 * right.m03;
    float m02 = left.m02 * right.m00 + left.m12 * right.m01 + left.m22 * right.m02 + left.m32 * right.m03;
    float m03 = left.m03 * right.m00 + left.m13 * right.m01 + left.m23 * right.m02 + left.m33 * right.m03;
    float m10 = left.m00 * right.m10 + left.m10 * right.m11 + left.m20 * right.m12 + left.m30 * right.m13;
    float m11 = left.m01 * right.m10 + left.m11 * right.m11 + left.m21 * right.m12 + left.m31 * right.m13;
    float m12 = left.m02 * right.m10 + left.m12 * right.m11 + left.m22 * right.m12 + left.m32 * right.m13;
    float m13 = left.m03 * right.m10 + left.m13 * right.m11 + left.m23 * right.m12 + left.m33 * right.m13;
    float m20 = left.m00 * right.m20 + left.m10 * right.m21 + left.m20 * right.m22 + left.m30 * right.m23;
    float m21 = left.m01 * right.m20 + left.m11 * right.m21 + left.m21 * right.m22 + left.m31 * right.m23;
    float m22 = left.m02 * right.m20 + left.m12 * right.m21 + left.m22 * right.m22 + left.m32 * right.m23;
    float m23 = left.m03 * right.m20 + left.m13 * right.m21 + left.m23 * right.m22 + left.m33 * right.m23;
    float m30 = left.m00 * right.m30 + left.m10 * right.m31 + left.m20 * right.m32 + left.m30 * right.m33;
    float m31 = left.m01 * right.m30 + left.m11 * right.m31 + left.m21 * right.m32 + left.m31 * right.m33;
    float m32 = left.m02 * right.m30 + left.m12 * right.m31 + left.m22 * right.m32 + left.m32 * right.m33;
    float m33 = left.m03 * right.m30 + left.m13 * right.m31 + left.m23 * right.m32 + left.m33 * right.m33;
    dest.m00 = m00;
    dest.m01 = m01;
    dest.m02 = m02;
    dest.m03 = m03;
    dest.m10 = m10;
    dest.m11 = m11;
    dest.m12 = m12;
    dest.m13 = m13;
    dest.m20 = m20;
    dest.m21 = m21;
    dest.m22 = m22;
    dest.m23 = m23;
    dest.m30 = m30;
    dest.m31 = m31;
    dest.m32 = m32;
    dest.m33 = m33;
    return dest;
  }
  
  public static Vector4f transform(Matrix4f left, Vector4f right, Vector4f dest)
  {
    if (dest == null) {
      dest = new Vector4f();
    }
    float local_x = left.m00 * right.field_140 + left.m10 * right.field_141 + left.m20 * right.field_142 + left.m30 * right.field_143;
    float local_y = left.m01 * right.field_140 + left.m11 * right.field_141 + left.m21 * right.field_142 + left.m31 * right.field_143;
    float local_z = left.m02 * right.field_140 + left.m12 * right.field_141 + left.m22 * right.field_142 + left.m32 * right.field_143;
    float local_w = left.m03 * right.field_140 + left.m13 * right.field_141 + left.m23 * right.field_142 + left.m33 * right.field_143;
    dest.field_140 = local_x;
    dest.field_141 = local_y;
    dest.field_142 = local_z;
    dest.field_143 = local_w;
    return dest;
  }
  
  public Matrix transpose()
  {
    return transpose(this);
  }
  
  public Matrix4f translate(Vector2f vec)
  {
    return translate(vec, this);
  }
  
  public Matrix4f translate(Vector3f vec)
  {
    return translate(vec, this);
  }
  
  public Matrix4f scale(Vector3f vec)
  {
    return scale(vec, this, this);
  }
  
  public static Matrix4f scale(Vector3f vec, Matrix4f src, Matrix4f dest)
  {
    if (dest == null) {
      dest = new Matrix4f();
    }
    src.m00 *= vec.field_140;
    src.m01 *= vec.field_140;
    src.m02 *= vec.field_140;
    src.m03 *= vec.field_140;
    src.m10 *= vec.field_141;
    src.m11 *= vec.field_141;
    src.m12 *= vec.field_141;
    src.m13 *= vec.field_141;
    src.m20 *= vec.field_142;
    src.m21 *= vec.field_142;
    src.m22 *= vec.field_142;
    src.m23 *= vec.field_142;
    return dest;
  }
  
  public Matrix4f rotate(float angle, Vector3f axis)
  {
    return rotate(angle, axis, this);
  }
  
  public Matrix4f rotate(float angle, Vector3f axis, Matrix4f dest)
  {
    return rotate(angle, axis, this, dest);
  }
  
  public static Matrix4f rotate(float angle, Vector3f axis, Matrix4f src, Matrix4f dest)
  {
    if (dest == null) {
      dest = new Matrix4f();
    }
    float local_c = (float)Math.cos(angle);
    float local_s = (float)Math.sin(angle);
    float oneminusc = 1.0F - local_c;
    float local_xy = axis.field_140 * axis.field_141;
    float local_yz = axis.field_141 * axis.field_142;
    float local_xz = axis.field_140 * axis.field_142;
    float local_xs = axis.field_140 * local_s;
    float local_ys = axis.field_141 * local_s;
    float local_zs = axis.field_142 * local_s;
    float f00 = axis.field_140 * axis.field_140 * oneminusc + local_c;
    float f01 = local_xy * oneminusc + local_zs;
    float f02 = local_xz * oneminusc - local_ys;
    float f10 = local_xy * oneminusc - local_zs;
    float f11 = axis.field_141 * axis.field_141 * oneminusc + local_c;
    float f12 = local_yz * oneminusc + local_xs;
    float f20 = local_xz * oneminusc + local_ys;
    float f21 = local_yz * oneminusc - local_xs;
    float f22 = axis.field_142 * axis.field_142 * oneminusc + local_c;
    float t00 = src.m00 * f00 + src.m10 * f01 + src.m20 * f02;
    float t01 = src.m01 * f00 + src.m11 * f01 + src.m21 * f02;
    float t02 = src.m02 * f00 + src.m12 * f01 + src.m22 * f02;
    float t03 = src.m03 * f00 + src.m13 * f01 + src.m23 * f02;
    float t10 = src.m00 * f10 + src.m10 * f11 + src.m20 * f12;
    float t11 = src.m01 * f10 + src.m11 * f11 + src.m21 * f12;
    float t12 = src.m02 * f10 + src.m12 * f11 + src.m22 * f12;
    float t13 = src.m03 * f10 + src.m13 * f11 + src.m23 * f12;
    dest.m20 = (src.m00 * f20 + src.m10 * f21 + src.m20 * f22);
    dest.m21 = (src.m01 * f20 + src.m11 * f21 + src.m21 * f22);
    dest.m22 = (src.m02 * f20 + src.m12 * f21 + src.m22 * f22);
    dest.m23 = (src.m03 * f20 + src.m13 * f21 + src.m23 * f22);
    dest.m00 = t00;
    dest.m01 = t01;
    dest.m02 = t02;
    dest.m03 = t03;
    dest.m10 = t10;
    dest.m11 = t11;
    dest.m12 = t12;
    dest.m13 = t13;
    return dest;
  }
  
  public Matrix4f translate(Vector3f vec, Matrix4f dest)
  {
    return translate(vec, this, dest);
  }
  
  public static Matrix4f translate(Vector3f vec, Matrix4f src, Matrix4f dest)
  {
    if (dest == null) {
      dest = new Matrix4f();
    }
    dest.m30 += src.m00 * vec.field_140 + src.m10 * vec.field_141 + src.m20 * vec.field_142;
    dest.m31 += src.m01 * vec.field_140 + src.m11 * vec.field_141 + src.m21 * vec.field_142;
    dest.m32 += src.m02 * vec.field_140 + src.m12 * vec.field_141 + src.m22 * vec.field_142;
    dest.m33 += src.m03 * vec.field_140 + src.m13 * vec.field_141 + src.m23 * vec.field_142;
    return dest;
  }
  
  public Matrix4f translate(Vector2f vec, Matrix4f dest)
  {
    return translate(vec, this, dest);
  }
  
  public static Matrix4f translate(Vector2f vec, Matrix4f src, Matrix4f dest)
  {
    if (dest == null) {
      dest = new Matrix4f();
    }
    dest.m30 += src.m00 * vec.field_140 + src.m10 * vec.field_141;
    dest.m31 += src.m01 * vec.field_140 + src.m11 * vec.field_141;
    dest.m32 += src.m02 * vec.field_140 + src.m12 * vec.field_141;
    dest.m33 += src.m03 * vec.field_140 + src.m13 * vec.field_141;
    return dest;
  }
  
  public Matrix4f transpose(Matrix4f dest)
  {
    return transpose(this, dest);
  }
  
  public static Matrix4f transpose(Matrix4f src, Matrix4f dest)
  {
    if (dest == null) {
      dest = new Matrix4f();
    }
    float m00 = src.m00;
    float m01 = src.m10;
    float m02 = src.m20;
    float m03 = src.m30;
    float m10 = src.m01;
    float m11 = src.m11;
    float m12 = src.m21;
    float m13 = src.m31;
    float m20 = src.m02;
    float m21 = src.m12;
    float m22 = src.m22;
    float m23 = src.m32;
    float m30 = src.m03;
    float m31 = src.m13;
    float m32 = src.m23;
    float m33 = src.m33;
    dest.m00 = m00;
    dest.m01 = m01;
    dest.m02 = m02;
    dest.m03 = m03;
    dest.m10 = m10;
    dest.m11 = m11;
    dest.m12 = m12;
    dest.m13 = m13;
    dest.m20 = m20;
    dest.m21 = m21;
    dest.m22 = m22;
    dest.m23 = m23;
    dest.m30 = m30;
    dest.m31 = m31;
    dest.m32 = m32;
    dest.m33 = m33;
    return dest;
  }
  
  public float determinant()
  {
    float local_f = this.m00 * (this.m11 * this.m22 * this.m33 + this.m12 * this.m23 * this.m31 + this.m13 * this.m21 * this.m32 - this.m13 * this.m22 * this.m31 - this.m11 * this.m23 * this.m32 - this.m12 * this.m21 * this.m33);
    local_f -= this.m01 * (this.m10 * this.m22 * this.m33 + this.m12 * this.m23 * this.m30 + this.m13 * this.m20 * this.m32 - this.m13 * this.m22 * this.m30 - this.m10 * this.m23 * this.m32 - this.m12 * this.m20 * this.m33);
    local_f += this.m02 * (this.m10 * this.m21 * this.m33 + this.m11 * this.m23 * this.m30 + this.m13 * this.m20 * this.m31 - this.m13 * this.m21 * this.m30 - this.m10 * this.m23 * this.m31 - this.m11 * this.m20 * this.m33);
    local_f -= this.m03 * (this.m10 * this.m21 * this.m32 + this.m11 * this.m22 * this.m30 + this.m12 * this.m20 * this.m31 - this.m12 * this.m21 * this.m30 - this.m10 * this.m22 * this.m31 - this.m11 * this.m20 * this.m32);
    return local_f;
  }
  
  private static float determinant3x3(float t00, float t01, float t02, float t10, float t11, float t12, float t20, float t21, float t22)
  {
    return t00 * (t11 * t22 - t12 * t21) + t01 * (t12 * t20 - t10 * t22) + t02 * (t10 * t21 - t11 * t20);
  }
  
  public Matrix invert()
  {
    return invert(this, this);
  }
  
  public static Matrix4f invert(Matrix4f src, Matrix4f dest)
  {
    float determinant = src.determinant();
    if (determinant != 0.0F)
    {
      if (dest == null) {
        dest = new Matrix4f();
      }
      float determinant_inv = 1.0F / determinant;
      float t00 = determinant3x3(src.m11, src.m12, src.m13, src.m21, src.m22, src.m23, src.m31, src.m32, src.m33);
      float t01 = -determinant3x3(src.m10, src.m12, src.m13, src.m20, src.m22, src.m23, src.m30, src.m32, src.m33);
      float t02 = determinant3x3(src.m10, src.m11, src.m13, src.m20, src.m21, src.m23, src.m30, src.m31, src.m33);
      float t03 = -determinant3x3(src.m10, src.m11, src.m12, src.m20, src.m21, src.m22, src.m30, src.m31, src.m32);
      float t10 = -determinant3x3(src.m01, src.m02, src.m03, src.m21, src.m22, src.m23, src.m31, src.m32, src.m33);
      float t11 = determinant3x3(src.m00, src.m02, src.m03, src.m20, src.m22, src.m23, src.m30, src.m32, src.m33);
      float t12 = -determinant3x3(src.m00, src.m01, src.m03, src.m20, src.m21, src.m23, src.m30, src.m31, src.m33);
      float t13 = determinant3x3(src.m00, src.m01, src.m02, src.m20, src.m21, src.m22, src.m30, src.m31, src.m32);
      float t20 = determinant3x3(src.m01, src.m02, src.m03, src.m11, src.m12, src.m13, src.m31, src.m32, src.m33);
      float t21 = -determinant3x3(src.m00, src.m02, src.m03, src.m10, src.m12, src.m13, src.m30, src.m32, src.m33);
      float t22 = determinant3x3(src.m00, src.m01, src.m03, src.m10, src.m11, src.m13, src.m30, src.m31, src.m33);
      float t23 = -determinant3x3(src.m00, src.m01, src.m02, src.m10, src.m11, src.m12, src.m30, src.m31, src.m32);
      float t30 = -determinant3x3(src.m01, src.m02, src.m03, src.m11, src.m12, src.m13, src.m21, src.m22, src.m23);
      float t31 = determinant3x3(src.m00, src.m02, src.m03, src.m10, src.m12, src.m13, src.m20, src.m22, src.m23);
      float t32 = -determinant3x3(src.m00, src.m01, src.m03, src.m10, src.m11, src.m13, src.m20, src.m21, src.m23);
      float t33 = determinant3x3(src.m00, src.m01, src.m02, src.m10, src.m11, src.m12, src.m20, src.m21, src.m22);
      dest.m00 = (t00 * determinant_inv);
      dest.m11 = (t11 * determinant_inv);
      dest.m22 = (t22 * determinant_inv);
      dest.m33 = (t33 * determinant_inv);
      dest.m01 = (t10 * determinant_inv);
      dest.m10 = (t01 * determinant_inv);
      dest.m20 = (t02 * determinant_inv);
      dest.m02 = (t20 * determinant_inv);
      dest.m12 = (t21 * determinant_inv);
      dest.m21 = (t12 * determinant_inv);
      dest.m03 = (t30 * determinant_inv);
      dest.m30 = (t03 * determinant_inv);
      dest.m13 = (t31 * determinant_inv);
      dest.m31 = (t13 * determinant_inv);
      dest.m32 = (t23 * determinant_inv);
      dest.m23 = (t32 * determinant_inv);
      return dest;
    }
    return null;
  }
  
  public Matrix negate()
  {
    return negate(this);
  }
  
  public Matrix4f negate(Matrix4f dest)
  {
    return negate(this, dest);
  }
  
  public static Matrix4f negate(Matrix4f src, Matrix4f dest)
  {
    if (dest == null) {
      dest = new Matrix4f();
    }
    dest.m00 = (-src.m00);
    dest.m01 = (-src.m01);
    dest.m02 = (-src.m02);
    dest.m03 = (-src.m03);
    dest.m10 = (-src.m10);
    dest.m11 = (-src.m11);
    dest.m12 = (-src.m12);
    dest.m13 = (-src.m13);
    dest.m20 = (-src.m20);
    dest.m21 = (-src.m21);
    dest.m22 = (-src.m22);
    dest.m23 = (-src.m23);
    dest.m30 = (-src.m30);
    dest.m31 = (-src.m31);
    dest.m32 = (-src.m32);
    dest.m33 = (-src.m33);
    return dest;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.util.vector.Matrix4f
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */