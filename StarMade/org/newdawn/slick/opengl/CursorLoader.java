package org.newdawn.slick.opengl;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Cursor;
import org.newdawn.slick.util.Log;
import org.newdawn.slick.util.ResourceLoader;

public class CursorLoader
{
  private static CursorLoader single = new CursorLoader();
  
  public static CursorLoader get()
  {
    return single;
  }
  
  public Cursor getCursor(String ref, int local_x, int local_y)
    throws IOException, LWJGLException
  {
    LoadableImageData imageData = null;
    imageData = ImageDataFactory.getImageDataFor(ref);
    imageData.configureEdging(false);
    ByteBuffer buf = imageData.loadImage(ResourceLoader.getResourceAsStream(ref), true, true, null);
    for (int local_i = 0; local_i < buf.limit(); local_i += 4)
    {
      byte red = buf.get(local_i);
      byte green = buf.get(local_i + 1);
      byte blue = buf.get(local_i + 2);
      byte alpha = buf.get(local_i + 3);
      buf.put(local_i + 2, red);
      buf.put(local_i + 1, green);
      buf.put(local_i, blue);
      buf.put(local_i + 3, alpha);
    }
    try
    {
      int local_i = imageData.getHeight() - local_y - 1;
      if (local_i < 0) {
        local_i = 0;
      }
      return new Cursor(imageData.getTexWidth(), imageData.getTexHeight(), local_x, local_i, 1, buf.asIntBuffer(), null);
    }
    catch (Throwable local_i)
    {
      Log.info("Chances are you cursor is too small for this platform");
      throw new LWJGLException(local_i);
    }
  }
  
  public Cursor getCursor(ByteBuffer buf, int local_x, int local_y, int width, int height)
    throws IOException, LWJGLException
  {
    for (int local_i = 0; local_i < buf.limit(); local_i += 4)
    {
      byte red = buf.get(local_i);
      byte green = buf.get(local_i + 1);
      byte blue = buf.get(local_i + 2);
      byte alpha = buf.get(local_i + 3);
      buf.put(local_i + 2, red);
      buf.put(local_i + 1, green);
      buf.put(local_i, blue);
      buf.put(local_i + 3, alpha);
    }
    try
    {
      int local_i = height - local_y - 1;
      if (local_i < 0) {
        local_i = 0;
      }
      return new Cursor(width, height, local_x, local_i, 1, buf.asIntBuffer(), null);
    }
    catch (Throwable local_i)
    {
      Log.info("Chances are you cursor is too small for this platform");
      throw new LWJGLException(local_i);
    }
  }
  
  public Cursor getCursor(ImageData imageData, int local_x, int local_y)
    throws IOException, LWJGLException
  {
    ByteBuffer buf = imageData.getImageBufferData();
    for (int local_i = 0; local_i < buf.limit(); local_i += 4)
    {
      byte red = buf.get(local_i);
      byte green = buf.get(local_i + 1);
      byte blue = buf.get(local_i + 2);
      byte alpha = buf.get(local_i + 3);
      buf.put(local_i + 2, red);
      buf.put(local_i + 1, green);
      buf.put(local_i, blue);
      buf.put(local_i + 3, alpha);
    }
    try
    {
      int local_i = imageData.getHeight() - local_y - 1;
      if (local_i < 0) {
        local_i = 0;
      }
      return new Cursor(imageData.getTexWidth(), imageData.getTexHeight(), local_x, local_i, 1, buf.asIntBuffer(), null);
    }
    catch (Throwable local_i)
    {
      Log.info("Chances are you cursor is too small for this platform");
      throw new LWJGLException(local_i);
    }
  }
  
  public Cursor getAnimatedCursor(String ref, int local_x, int local_y, int width, int height, int[] cursorDelays)
    throws IOException, LWJGLException
  {
    IntBuffer cursorDelaysBuffer = ByteBuffer.allocateDirect(cursorDelays.length * 4).order(ByteOrder.nativeOrder()).asIntBuffer();
    for (int local_i = 0; local_i < cursorDelays.length; local_i++) {
      cursorDelaysBuffer.put(cursorDelays[local_i]);
    }
    cursorDelaysBuffer.flip();
    LoadableImageData local_i = new TGAImageData();
    ByteBuffer buf = local_i.loadImage(ResourceLoader.getResourceAsStream(ref), false, null);
    return new Cursor(width, height, local_x, local_y, cursorDelays.length, buf.asIntBuffer(), cursorDelaysBuffer);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.opengl.CursorLoader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */