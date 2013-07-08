package org.lwjgl.util.glu;

import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class Util
{
  private static IntBuffer scratch = BufferUtils.createIntBuffer(16);
  
  protected static int ceil(int local_a, int local_b)
  {
    return local_a % local_b == 0 ? local_a / local_b : local_a / local_b + 1;
  }
  
  protected static float[] normalize(float[] local_v)
  {
    float local_r = (float)Math.sqrt(local_v[0] * local_v[0] + local_v[1] * local_v[1] + local_v[2] * local_v[2]);
    if (local_r == 0.0D) {
      return local_v;
    }
    local_r = 1.0F / local_r;
    local_v[0] *= local_r;
    local_v[1] *= local_r;
    local_v[2] *= local_r;
    return local_v;
  }
  
  protected static void cross(float[] local_v1, float[] local_v2, float[] result)
  {
    result[0] = (local_v1[1] * local_v2[2] - local_v1[2] * local_v2[1]);
    result[1] = (local_v1[2] * local_v2[0] - local_v1[0] * local_v2[2]);
    result[2] = (local_v1[0] * local_v2[1] - local_v1[1] * local_v2[0]);
  }
  
  protected static int compPerPix(int format)
  {
    switch (format)
    {
    case 6400: 
    case 6401: 
    case 6402: 
    case 6403: 
    case 6404: 
    case 6405: 
    case 6406: 
    case 6409: 
      return 1;
    case 6410: 
      return 2;
    case 6407: 
    case 32992: 
      return 3;
    case 6408: 
    case 32993: 
      return 4;
    }
    return -1;
  }
  
  protected static int nearestPower(int value)
  {
    int local_i = 1;
    if (value == 0) {
      return -1;
    }
    for (;;)
    {
      if (value == 1) {
        return local_i;
      }
      if (value == 3) {
        return local_i << 2;
      }
      value >>= 1;
      local_i <<= 1;
    }
  }
  
  protected static int bytesPerPixel(int format, int type)
  {
    int local_n;
    switch (format)
    {
    case 6400: 
    case 6401: 
    case 6402: 
    case 6403: 
    case 6404: 
    case 6405: 
    case 6406: 
    case 6409: 
      local_n = 1;
      break;
    case 6410: 
      local_n = 2;
      break;
    case 6407: 
    case 32992: 
      local_n = 3;
      break;
    case 6408: 
    case 32993: 
      local_n = 4;
      break;
    default: 
      local_n = 0;
    }
    int local_m;
    switch (type)
    {
    case 5121: 
      local_m = 1;
      break;
    case 5120: 
      local_m = 1;
      break;
    case 6656: 
      local_m = 1;
      break;
    case 5123: 
      local_m = 2;
      break;
    case 5122: 
      local_m = 2;
      break;
    case 5125: 
      local_m = 4;
      break;
    case 5124: 
      local_m = 4;
      break;
    case 5126: 
      local_m = 4;
      break;
    default: 
      local_m = 0;
    }
    return local_n * local_m;
  }
  
  protected static int glGetIntegerv(int what)
  {
    scratch.rewind();
    GL11.glGetInteger(what, scratch);
    return scratch.get();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.util.glu.Util
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */