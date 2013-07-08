package org.lwjgl.opengl;

import java.nio.LongBuffer;

public final class ARBTimerQuery
{
  public static final int GL_TIME_ELAPSED = 35007;
  public static final int GL_TIMESTAMP = 36392;
  
  public static void glQueryCounter(int local_id, int target)
  {
    GL33.glQueryCounter(local_id, target);
  }
  
  public static void glGetQueryObject(int local_id, int pname, LongBuffer params)
  {
    GL33.glGetQueryObject(local_id, pname, params);
  }
  
  public static long glGetQueryObjecti64(int local_id, int pname)
  {
    return GL33.glGetQueryObjecti64(local_id, pname);
  }
  
  public static void glGetQueryObjectu(int local_id, int pname, LongBuffer params)
  {
    GL33.glGetQueryObjectu(local_id, pname, params);
  }
  
  public static long glGetQueryObjectui64(int local_id, int pname)
  {
    return GL33.glGetQueryObjectui64(local_id, pname);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.ARBTimerQuery
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */