package com.bulletphysics.linearmath;

import com.bulletphysics..Stack;
import com.bulletphysics.util.ArrayPool;
import javax.vecmath.Matrix3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

public class MatrixUtil
{
  public static void scale(Matrix3f dest, Matrix3f mat, Vector3f local_s)
  {
    mat.m00 *= local_s.field_615;
    mat.m01 *= local_s.field_616;
    mat.m02 *= local_s.field_617;
    mat.m10 *= local_s.field_615;
    mat.m11 *= local_s.field_616;
    mat.m12 *= local_s.field_617;
    mat.m20 *= local_s.field_615;
    mat.m21 *= local_s.field_616;
    mat.m22 *= local_s.field_617;
  }
  
  public static void absolute(Matrix3f mat)
  {
    mat.m00 = Math.abs(mat.m00);
    mat.m01 = Math.abs(mat.m01);
    mat.m02 = Math.abs(mat.m02);
    mat.m10 = Math.abs(mat.m10);
    mat.m11 = Math.abs(mat.m11);
    mat.m12 = Math.abs(mat.m12);
    mat.m20 = Math.abs(mat.m20);
    mat.m21 = Math.abs(mat.m21);
    mat.m22 = Math.abs(mat.m22);
  }
  
  public static void setFromOpenGLSubMatrix(Matrix3f mat, float[] local_m)
  {
    mat.m00 = local_m[0];
    mat.m01 = local_m[4];
    mat.m02 = local_m[8];
    mat.m10 = local_m[1];
    mat.m11 = local_m[5];
    mat.m12 = local_m[9];
    mat.m20 = local_m[2];
    mat.m21 = local_m[6];
    mat.m22 = local_m[10];
  }
  
  public static void getOpenGLSubMatrix(Matrix3f mat, float[] local_m)
  {
    local_m[0] = mat.m00;
    local_m[1] = mat.m10;
    local_m[2] = mat.m20;
    local_m[3] = 0.0F;
    local_m[4] = mat.m01;
    local_m[5] = mat.m11;
    local_m[6] = mat.m21;
    local_m[7] = 0.0F;
    local_m[8] = mat.m02;
    local_m[9] = mat.m12;
    local_m[10] = mat.m22;
    local_m[11] = 0.0F;
  }
  
  public static void setEulerZYX(Matrix3f mat, float eulerX, float eulerY, float eulerZ)
  {
    float local_ci = (float)Math.cos(eulerX);
    float local_cj = (float)Math.cos(eulerY);
    float local_ch = (float)Math.cos(eulerZ);
    float local_si = (float)Math.sin(eulerX);
    float local_sj = (float)Math.sin(eulerY);
    float local_sh = (float)Math.sin(eulerZ);
    float local_cc = local_ci * local_ch;
    float local_cs = local_ci * local_sh;
    float local_sc = local_si * local_ch;
    float local_ss = local_si * local_sh;
    mat.setRow(0, local_cj * local_ch, local_sj * local_sc - local_cs, local_sj * local_cc + local_ss);
    mat.setRow(1, local_cj * local_sh, local_sj * local_ss + local_cc, local_sj * local_cs - local_sc);
    mat.setRow(2, -local_sj, local_cj * local_si, local_cj * local_ci);
  }
  
  private static float tdotx(Matrix3f mat, Vector3f vec)
  {
    return mat.m00 * vec.field_615 + mat.m10 * vec.field_616 + mat.m20 * vec.field_617;
  }
  
  private static float tdoty(Matrix3f mat, Vector3f vec)
  {
    return mat.m01 * vec.field_615 + mat.m11 * vec.field_616 + mat.m21 * vec.field_617;
  }
  
  private static float tdotz(Matrix3f mat, Vector3f vec)
  {
    return mat.m02 * vec.field_615 + mat.m12 * vec.field_616 + mat.m22 * vec.field_617;
  }
  
  public static void transposeTransform(Vector3f dest, Vector3f vec, Matrix3f mat)
  {
    float local_x = tdotx(mat, vec);
    float local_y = tdoty(mat, vec);
    float local_z = tdotz(mat, vec);
    dest.field_615 = local_x;
    dest.field_616 = local_y;
    dest.field_617 = local_z;
  }
  
  public static void setRotation(Matrix3f dest, Quat4f local_q)
  {
    float local_d = local_q.field_596 * local_q.field_596 + local_q.field_597 * local_q.field_597 + local_q.field_598 * local_q.field_598 + local_q.field_599 * local_q.field_599;
    assert (local_d != 0.0F);
    float local_s = 2.0F / local_d;
    float local_xs = local_q.field_596 * local_s;
    float local_ys = local_q.field_597 * local_s;
    float local_zs = local_q.field_598 * local_s;
    float local_wx = local_q.field_599 * local_xs;
    float local_wy = local_q.field_599 * local_ys;
    float local_wz = local_q.field_599 * local_zs;
    float local_xx = local_q.field_596 * local_xs;
    float local_xy = local_q.field_596 * local_ys;
    float local_xz = local_q.field_596 * local_zs;
    float local_yy = local_q.field_597 * local_ys;
    float local_yz = local_q.field_597 * local_zs;
    float local_zz = local_q.field_598 * local_zs;
    dest.m00 = (1.0F - (local_yy + local_zz));
    dest.m01 = (local_xy - local_wz);
    dest.m02 = (local_xz + local_wy);
    dest.m10 = (local_xy + local_wz);
    dest.m11 = (1.0F - (local_xx + local_zz));
    dest.m12 = (local_yz - local_wx);
    dest.m20 = (local_xz - local_wy);
    dest.m21 = (local_yz + local_wx);
    dest.m22 = (1.0F - (local_xx + local_yy));
  }
  
  public static void getRotation(Matrix3f mat, Quat4f dest)
  {
    ArrayPool<float[]> floatArrays = ArrayPool.get(Float.TYPE);
    float trace = mat.m00 + mat.m11 + mat.m22;
    float[] temp = (float[])floatArrays.getFixed(4);
    if (trace > 0.0F)
    {
      float local_s = (float)Math.sqrt(trace + 1.0F);
      temp[3] = (local_s * 0.5F);
      local_s = 0.5F / local_s;
      temp[0] = ((mat.m21 - mat.m12) * local_s);
      temp[1] = ((mat.m02 - mat.m20) * local_s);
      temp[2] = ((mat.m10 - mat.m01) * local_s);
    }
    else
    {
      int local_s = mat.m00 < mat.m22 ? 2 : mat.m00 < mat.m11 ? 1 : mat.m11 < mat.m22 ? 2 : 0;
      int local_j = (local_s + 1) % 3;
      int local_k = (local_s + 2) % 3;
      float local_s1 = (float)Math.sqrt(mat.getElement(local_s, local_s) - mat.getElement(local_j, local_j) - mat.getElement(local_k, local_k) + 1.0F);
      temp[local_s] = (local_s1 * 0.5F);
      local_s1 = 0.5F / local_s1;
      temp[3] = ((mat.getElement(local_k, local_j) - mat.getElement(local_j, local_k)) * local_s1);
      temp[local_j] = ((mat.getElement(local_j, local_s) + mat.getElement(local_s, local_j)) * local_s1);
      temp[local_k] = ((mat.getElement(local_k, local_s) + mat.getElement(local_s, local_k)) * local_s1);
    }
    dest.set(temp[0], temp[1], temp[2], temp[3]);
    floatArrays.release(temp);
  }
  
  private static float cofac(Matrix3f mat, int local_r1, int local_c1, int local_r2, int local_c2)
  {
    return mat.getElement(local_r1, local_c1) * mat.getElement(local_r2, local_c2) - mat.getElement(local_r1, local_c2) * mat.getElement(local_r2, local_c1);
  }
  
  public static void invert(Matrix3f mat)
  {
    float co_x = cofac(mat, 1, 1, 2, 2);
    float co_y = cofac(mat, 1, 2, 2, 0);
    float co_z = cofac(mat, 1, 0, 2, 1);
    float det = mat.m00 * co_x + mat.m01 * co_y + mat.m02 * co_z;
    assert (det != 0.0F);
    float local_s = 1.0F / det;
    float m00 = co_x * local_s;
    float m01 = cofac(mat, 0, 2, 2, 1) * local_s;
    float m02 = cofac(mat, 0, 1, 1, 2) * local_s;
    float m10 = co_y * local_s;
    float m11 = cofac(mat, 0, 0, 2, 2) * local_s;
    float m12 = cofac(mat, 0, 2, 1, 0) * local_s;
    float m20 = co_z * local_s;
    float m21 = cofac(mat, 0, 1, 2, 0) * local_s;
    float m22 = cofac(mat, 0, 0, 1, 1) * local_s;
    mat.m00 = m00;
    mat.m01 = m01;
    mat.m02 = m02;
    mat.m10 = m10;
    mat.m11 = m11;
    mat.m12 = m12;
    mat.m20 = m20;
    mat.m21 = m21;
    mat.m22 = m22;
  }
  
  public static void diagonalize(Matrix3f arg0, Matrix3f arg1, float arg2, int arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f row = localStack.get$javax$vecmath$Vector3f();
      rot.setIdentity();
      for (int step = maxSteps; step > 0; step--)
      {
        int local_p = 0;
        int local_q = 1;
        int local_r = 2;
        float max = Math.abs(mat.m01);
        float local_v = Math.abs(mat.m02);
        if (local_v > max)
        {
          local_q = 2;
          local_r = 1;
          max = local_v;
        }
        local_v = Math.abs(mat.m12);
        if (local_v > max)
        {
          local_p = 1;
          local_q = 2;
          local_r = 0;
          max = local_v;
        }
        float local_t = threshold * (Math.abs(mat.m00) + Math.abs(mat.m11) + Math.abs(mat.m22));
        if (max <= local_t)
        {
          if (max <= 1.192093E-007F * local_t) {
            return;
          }
          step = 1;
        }
        float mpq = mat.getElement(local_p, local_q);
        float theta = (mat.getElement(local_q, local_q) - mat.getElement(local_p, local_p)) / (2.0F * mpq);
        float theta2 = theta * theta;
        float sin;
        float cos;
        float sin;
        if (theta2 * theta2 < 83886080.0F)
        {
          local_t = theta >= 0.0F ? 1.0F / (theta + (float)Math.sqrt(1.0F + theta2)) : 1.0F / (theta - (float)Math.sqrt(1.0F + theta2));
          float cos = 1.0F / (float)Math.sqrt(1.0F + local_t * local_t);
          sin = cos * local_t;
        }
        else
        {
          local_t = 1.0F / (theta * (2.0F + 0.5F / theta2));
          cos = 1.0F - 0.5F * local_t * local_t;
          sin = cos * local_t;
        }
        mat.setElement(local_p, local_q, 0.0F);
        mat.setElement(local_q, local_p, 0.0F);
        mat.setElement(local_p, local_p, mat.getElement(local_p, local_p) - local_t * mpq);
        mat.setElement(local_q, local_q, mat.getElement(local_q, local_q) + local_t * mpq);
        float mrp = mat.getElement(local_r, local_p);
        float mrq = mat.getElement(local_r, local_q);
        mat.setElement(local_r, local_p, cos * mrp - sin * mrq);
        mat.setElement(local_p, local_r, cos * mrp - sin * mrq);
        mat.setElement(local_r, local_q, cos * mrq + sin * mrp);
        mat.setElement(local_q, local_r, cos * mrq + sin * mrp);
        for (int local_i = 0; local_i < 3; local_i++)
        {
          rot.getRow(local_i, row);
          mrp = VectorUtil.getCoord(row, local_p);
          mrq = VectorUtil.getCoord(row, local_q);
          VectorUtil.setCoord(row, local_p, cos * mrp - sin * mrq);
          VectorUtil.setCoord(row, local_q, cos * mrq + sin * mrp);
          rot.setRow(local_i, row);
        }
      }
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.linearmath.MatrixUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */