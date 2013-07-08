package org.newdawn.slick.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;

public class GlUtil
{
  private static ByteBuffer[] dynamicByteBuffer = new ByteBuffer[1];
  
  public static void destroyDirectByteBuffer(ByteBuffer toBeDestroyed)
    throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException
  {
    Method cleanerMethod = toBeDestroyed.getClass().getMethod("cleaner", new Class[0]);
    cleanerMethod.setAccessible(true);
    Object cleaner = cleanerMethod.invoke(toBeDestroyed, new Object[0]);
    Method cleanMethod = cleaner.getClass().getMethod("clean", new Class[0]);
    cleanMethod.setAccessible(true);
    cleanMethod.invoke(cleaner, new Object[0]);
  }
  
  public static ByteBuffer getDynamicByteBuffer(int size)
  {
    return getDynamicByteBuffer(size, 0);
  }
  
  public static ByteBuffer getDynamicByteBuffer(int size, int index)
  {
    ByteBuffer dynamicByteBuffer = dynamicByteBuffer[index];
    if ((dynamicByteBuffer == null) || (dynamicByteBuffer.capacity() < size))
    {
      if (dynamicByteBuffer != null)
      {
        try
        {
          destroyDirectByteBuffer(dynamicByteBuffer);
        }
        catch (IllegalArgumentException local_e)
        {
          local_e.printStackTrace();
        }
        catch (SecurityException local_e)
        {
          local_e.printStackTrace();
        }
        catch (IllegalAccessException local_e)
        {
          local_e.printStackTrace();
        }
        catch (InvocationTargetException local_e)
        {
          local_e.printStackTrace();
        }
        catch (NoSuchMethodException local_e)
        {
          local_e.printStackTrace();
        }
        dynamicByteBuffer = null;
        System.gc();
      }
      dynamicByteBuffer = BufferUtils.createByteBuffer(size);
    }
    dynamicByteBuffer.limit(size);
    dynamicByteBuffer.rewind();
    return dynamicByteBuffer;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.util.GlUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */