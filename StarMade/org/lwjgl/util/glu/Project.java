package org.lwjgl.util.glu;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class Project
  extends Util
{
  private static final float[] IDENTITY_MATRIX = { 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F };
  private static final FloatBuffer matrix = BufferUtils.createFloatBuffer(16);
  private static final FloatBuffer finalMatrix = BufferUtils.createFloatBuffer(16);
  private static final FloatBuffer tempMatrix = BufferUtils.createFloatBuffer(16);
  private static final float[] field_426 = new float[4];
  private static final float[] out = new float[4];
  private static final float[] forward = new float[3];
  private static final float[] side = new float[3];
  private static final float[] field_427 = new float[3];
  
  private static void __gluMakeIdentityf(FloatBuffer local_m)
  {
    int oldPos = local_m.position();
    local_m.put(IDENTITY_MATRIX);
    local_m.position(oldPos);
  }
  
  private static void __gluMultMatrixVecf(FloatBuffer local_m, float[] local_in, float[] out)
  {
    for (int local_i = 0; local_i < 4; local_i++) {
      out[local_i] = (local_in[0] * local_m.get(local_m.position() + 0 + local_i) + local_in[1] * local_m.get(local_m.position() + 4 + local_i) + local_in[2] * local_m.get(local_m.position() + 8 + local_i) + local_in[3] * local_m.get(local_m.position() + 12 + local_i));
    }
  }
  
  private static boolean __gluInvertMatrixf(FloatBuffer src, FloatBuffer inverse)
  {
    FloatBuffer temp = tempMatrix;
    for (int local_i = 0; local_i < 16; local_i++) {
      temp.put(local_i, src.get(local_i + src.position()));
    }
    __gluMakeIdentityf(inverse);
    for (local_i = 0; local_i < 4; local_i++)
    {
      int swap = local_i;
      for (int local_j = local_i + 1; local_j < 4; local_j++) {
        if (Math.abs(temp.get(local_j * 4 + local_i)) > Math.abs(temp.get(local_i * 4 + local_i))) {
          swap = local_j;
        }
      }
      if (swap != local_i) {
        for (int local_k = 0; local_k < 4; local_k++)
        {
          float local_t = temp.get(local_i * 4 + local_k);
          temp.put(local_i * 4 + local_k, temp.get(swap * 4 + local_k));
          temp.put(swap * 4 + local_k, local_t);
          local_t = inverse.get(local_i * 4 + local_k);
          inverse.put(local_i * 4 + local_k, inverse.get(swap * 4 + local_k));
          inverse.put(swap * 4 + local_k, local_t);
        }
      }
      if (temp.get(local_i * 4 + local_i) == 0.0F) {
        return false;
      }
      float local_t = temp.get(local_i * 4 + local_i);
      for (int local_k = 0; local_k < 4; local_k++)
      {
        temp.put(local_i * 4 + local_k, temp.get(local_i * 4 + local_k) / local_t);
        inverse.put(local_i * 4 + local_k, inverse.get(local_i * 4 + local_k) / local_t);
      }
      for (local_j = 0; local_j < 4; local_j++) {
        if (local_j != local_i)
        {
          local_t = temp.get(local_j * 4 + local_i);
          for (local_k = 0; local_k < 4; local_k++)
          {
            temp.put(local_j * 4 + local_k, temp.get(local_j * 4 + local_k) - temp.get(local_i * 4 + local_k) * local_t);
            inverse.put(local_j * 4 + local_k, inverse.get(local_j * 4 + local_k) - inverse.get(local_i * 4 + local_k) * local_t);
          }
        }
      }
    }
    return true;
  }
  
  private static void __gluMultMatricesf(FloatBuffer local_a, FloatBuffer local_b, FloatBuffer local_r)
  {
    for (int local_i = 0; local_i < 4; local_i++) {
      for (int local_j = 0; local_j < 4; local_j++) {
        local_r.put(local_r.position() + local_i * 4 + local_j, local_a.get(local_a.position() + local_i * 4 + 0) * local_b.get(local_b.position() + 0 + local_j) + local_a.get(local_a.position() + local_i * 4 + 1) * local_b.get(local_b.position() + 4 + local_j) + local_a.get(local_a.position() + local_i * 4 + 2) * local_b.get(local_b.position() + 8 + local_j) + local_a.get(local_a.position() + local_i * 4 + 3) * local_b.get(local_b.position() + 12 + local_j));
      }
    }
  }
  
  public static void gluPerspective(float fovy, float aspect, float zNear, float zFar)
  {
    float radians = fovy / 2.0F * 3.141593F / 180.0F;
    float deltaZ = zFar - zNear;
    float sine = (float)Math.sin(radians);
    if ((deltaZ == 0.0F) || (sine == 0.0F) || (aspect == 0.0F)) {
      return;
    }
    float cotangent = (float)Math.cos(radians) / sine;
    __gluMakeIdentityf(matrix);
    matrix.put(0, cotangent / aspect);
    matrix.put(5, cotangent);
    matrix.put(10, -(zFar + zNear) / deltaZ);
    matrix.put(11, -1.0F);
    matrix.put(14, -2.0F * zNear * zFar / deltaZ);
    matrix.put(15, 0.0F);
    GL11.glMultMatrix(matrix);
  }
  
  public static void gluLookAt(float eyex, float eyey, float eyez, float centerx, float centery, float centerz, float upx, float upy, float upz)
  {
    float[] forward = forward;
    float[] side = side;
    float[] local_up = field_427;
    forward[0] = (centerx - eyex);
    forward[1] = (centery - eyey);
    forward[2] = (centerz - eyez);
    local_up[0] = upx;
    local_up[1] = upy;
    local_up[2] = upz;
    normalize(forward);
    cross(forward, local_up, side);
    normalize(side);
    cross(side, forward, local_up);
    __gluMakeIdentityf(matrix);
    matrix.put(0, side[0]);
    matrix.put(4, side[1]);
    matrix.put(8, side[2]);
    matrix.put(1, local_up[0]);
    matrix.put(5, local_up[1]);
    matrix.put(9, local_up[2]);
    matrix.put(2, -forward[0]);
    matrix.put(6, -forward[1]);
    matrix.put(10, -forward[2]);
    GL11.glMultMatrix(matrix);
    GL11.glTranslatef(-eyex, -eyey, -eyez);
  }
  
  public static boolean gluProject(float objx, float objy, float objz, FloatBuffer modelMatrix, FloatBuffer projMatrix, IntBuffer viewport, FloatBuffer win_pos)
  {
    float[] local_in = field_426;
    float[] out = out;
    local_in[0] = objx;
    local_in[1] = objy;
    local_in[2] = objz;
    local_in[3] = 1.0F;
    __gluMultMatrixVecf(modelMatrix, local_in, out);
    __gluMultMatrixVecf(projMatrix, out, local_in);
    if (local_in[3] == 0.0D) {
      return false;
    }
    local_in[3] = (1.0F / local_in[3] * 0.5F);
    local_in[0] = (local_in[0] * local_in[3] + 0.5F);
    local_in[1] = (local_in[1] * local_in[3] + 0.5F);
    local_in[2] = (local_in[2] * local_in[3] + 0.5F);
    win_pos.put(0, local_in[0] * viewport.get(viewport.position() + 2) + viewport.get(viewport.position() + 0));
    win_pos.put(1, local_in[1] * viewport.get(viewport.position() + 3) + viewport.get(viewport.position() + 1));
    win_pos.put(2, local_in[2]);
    return true;
  }
  
  public static boolean gluUnProject(float winx, float winy, float winz, FloatBuffer modelMatrix, FloatBuffer projMatrix, IntBuffer viewport, FloatBuffer obj_pos)
  {
    float[] local_in = field_426;
    float[] out = out;
    __gluMultMatricesf(modelMatrix, projMatrix, finalMatrix);
    if (!__gluInvertMatrixf(finalMatrix, finalMatrix)) {
      return false;
    }
    local_in[0] = winx;
    local_in[1] = winy;
    local_in[2] = winz;
    local_in[3] = 1.0F;
    local_in[0] = ((local_in[0] - viewport.get(viewport.position() + 0)) / viewport.get(viewport.position() + 2));
    local_in[1] = ((local_in[1] - viewport.get(viewport.position() + 1)) / viewport.get(viewport.position() + 3));
    local_in[0] = (local_in[0] * 2.0F - 1.0F);
    local_in[1] = (local_in[1] * 2.0F - 1.0F);
    local_in[2] = (local_in[2] * 2.0F - 1.0F);
    __gluMultMatrixVecf(finalMatrix, local_in, out);
    if (out[3] == 0.0D) {
      return false;
    }
    out[3] = (1.0F / out[3]);
    obj_pos.put(obj_pos.position() + 0, out[0] * out[3]);
    obj_pos.put(obj_pos.position() + 1, out[1] * out[3]);
    obj_pos.put(obj_pos.position() + 2, out[2] * out[3]);
    return true;
  }
  
  public static void gluPickMatrix(float local_x, float local_y, float deltaX, float deltaY, IntBuffer viewport)
  {
    if ((deltaX <= 0.0F) || (deltaY <= 0.0F)) {
      return;
    }
    GL11.glTranslatef((viewport.get(viewport.position() + 2) - 2.0F * (local_x - viewport.get(viewport.position() + 0))) / deltaX, (viewport.get(viewport.position() + 3) - 2.0F * (local_y - viewport.get(viewport.position() + 1))) / deltaY, 0.0F);
    GL11.glScalef(viewport.get(viewport.position() + 2) / deltaX, viewport.get(viewport.position() + 3) / deltaY, 1.0F);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.util.glu.Project
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */