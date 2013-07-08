package org.lwjgl.input;

import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;
import org.lwjgl.NondirectBufferWrapper;
import org.lwjgl.Sys;
import org.lwjgl.opengl.InputImplementation;

public class Cursor
{
  public static final int CURSOR_ONE_BIT_TRANSPARENCY = 1;
  public static final int CURSOR_8_BIT_ALPHA = 2;
  public static final int CURSOR_ANIMATION = 4;
  private final CursorElement[] cursors;
  private int index;
  private boolean destroyed;
  
  public Cursor(int width, int height, int xHotspot, int yHotspot, int numImages, IntBuffer images, IntBuffer delays)
    throws LWJGLException
  {
    synchronized (OpenGLPackageAccess.global_lock)
    {
      if ((getCapabilities() & 0x1) == 0) {
        throw new LWJGLException("Native cursors not supported");
      }
      images = NondirectBufferWrapper.wrapBuffer(images, width * height * numImages);
      if (delays != null) {
        delays = NondirectBufferWrapper.wrapBuffer(delays, numImages);
      }
      if (!Mouse.isCreated()) {
        throw new IllegalStateException("Mouse must be created before creating cursor objects");
      }
      if (width * height * numImages > images.remaining()) {
        throw new IllegalArgumentException("width*height*numImages > images.remaining()");
      }
      if ((xHotspot >= width) || (xHotspot < 0)) {
        throw new IllegalArgumentException("xHotspot > width || xHotspot < 0");
      }
      if ((yHotspot >= height) || (yHotspot < 0)) {
        throw new IllegalArgumentException("yHotspot > height || yHotspot < 0");
      }
      Sys.initialize();
      yHotspot = height - 1 - yHotspot;
      this.cursors = createCursors(width, height, xHotspot, yHotspot, numImages, images, delays);
    }
  }
  
  public static int getMinCursorSize()
  {
    synchronized (OpenGLPackageAccess.global_lock)
    {
      if (!Mouse.isCreated()) {
        throw new IllegalStateException("Mouse must be created.");
      }
      return Mouse.getImplementation().getMinCursorSize();
    }
  }
  
  public static int getMaxCursorSize()
  {
    synchronized (OpenGLPackageAccess.global_lock)
    {
      if (!Mouse.isCreated()) {
        throw new IllegalStateException("Mouse must be created.");
      }
      return Mouse.getImplementation().getMaxCursorSize();
    }
  }
  
  public static int getCapabilities()
  {
    synchronized (OpenGLPackageAccess.global_lock)
    {
      if (Mouse.getImplementation() != null) {
        return Mouse.getImplementation().getNativeCursorCapabilities();
      }
      return OpenGLPackageAccess.createImplementation().getNativeCursorCapabilities();
    }
  }
  
  private static CursorElement[] createCursors(int width, int height, int xHotspot, int yHotspot, int numImages, IntBuffer images, IntBuffer delays)
    throws LWJGLException
  {
    IntBuffer images_copy = BufferUtils.createIntBuffer(images.remaining());
    flipImages(width, height, numImages, images, images_copy);
    CursorElement[] cursors;
    switch (LWJGLUtil.getPlatform())
    {
    case 2: 
      convertARGBtoABGR(images_copy);
      cursors = new CursorElement[numImages];
      for (int local_i = 0; local_i < numImages; local_i++)
      {
        Object handle = Mouse.getImplementation().createCursor(width, height, xHotspot, yHotspot, 1, images_copy, null);
        long delay = delays != null ? delays.get(local_i) : 0L;
        long timeout = System.currentTimeMillis();
        cursors[local_i] = new CursorElement(handle, delay, timeout);
        images_copy.position(width * height * (local_i + 1));
      }
      break;
    case 3: 
      cursors = new CursorElement[numImages];
      for (int local_i = 0; local_i < numImages; local_i++)
      {
        int handle = width * height;
        for (int delay = 0; delay < handle; delay++)
        {
          int index = delay + local_i * handle;
          int timeout = images_copy.get(index) >> 24 & 0xFF;
          if (timeout != 255) {
            images_copy.put(index, 0);
          }
        }
        Object delay = Mouse.getImplementation().createCursor(width, height, xHotspot, yHotspot, 1, images_copy, null);
        long index = delays != null ? delays.get(local_i) : 0L;
        long timeout = System.currentTimeMillis();
        cursors[local_i] = new CursorElement(delay, index, timeout);
        images_copy.position(width * height * (local_i + 1));
      }
      break;
    case 1: 
      Object local_i = Mouse.getImplementation().createCursor(width, height, xHotspot, yHotspot, numImages, images_copy, delays);
      CursorElement handle = new CursorElement(local_i, -1L, -1L);
      cursors = new CursorElement[] { handle };
      break;
    default: 
      throw new RuntimeException("Unknown OS");
    }
    return cursors;
  }
  
  private static void convertARGBtoABGR(IntBuffer imageBuffer)
  {
    for (int local_i = 0; local_i < imageBuffer.limit(); local_i++)
    {
      int argbColor = imageBuffer.get(local_i);
      byte alpha = (byte)(argbColor >>> 24);
      byte blue = (byte)(argbColor >>> 16);
      byte green = (byte)(argbColor >>> 8);
      byte red = (byte)argbColor;
      int abgrColor = ((alpha & 0xFF) << 24) + ((red & 0xFF) << 16) + ((green & 0xFF) << 8) + (blue & 0xFF);
      imageBuffer.put(local_i, abgrColor);
    }
  }
  
  private static void flipImages(int width, int height, int numImages, IntBuffer images, IntBuffer images_copy)
  {
    for (int local_i = 0; local_i < numImages; local_i++)
    {
      int start_index = local_i * width * height;
      flipImage(width, height, start_index, images, images_copy);
    }
  }
  
  private static void flipImage(int width, int height, int start_index, IntBuffer images, IntBuffer images_copy)
  {
    for (int local_y = 0; local_y < height >> 1; local_y++)
    {
      int index_y_1 = local_y * width + start_index;
      int index_y_2 = (height - local_y - 1) * width + start_index;
      for (int local_x = 0; local_x < width; local_x++)
      {
        int index1 = index_y_1 + local_x;
        int index2 = index_y_2 + local_x;
        int temp_pixel = images.get(index1 + images.position());
        images_copy.put(index1, images.get(index2 + images.position()));
        images_copy.put(index2, temp_pixel);
      }
    }
  }
  
  Object getHandle()
  {
    checkValid();
    return this.cursors[this.index].cursorHandle;
  }
  
  private void checkValid()
  {
    if (this.destroyed) {
      throw new IllegalStateException("The cursor is destroyed");
    }
  }
  
  public void destroy()
  {
    synchronized (OpenGLPackageAccess.global_lock)
    {
      if (this.destroyed) {
        return;
      }
      if (Mouse.getNativeCursor() == this) {
        try
        {
          Mouse.setNativeCursor(null);
        }
        catch (LWJGLException local_e) {}
      }
      for (CursorElement cursor : this.cursors) {
        Mouse.getImplementation().destroyCursor(cursor.cursorHandle);
      }
      this.destroyed = true;
    }
  }
  
  protected void setTimeout()
  {
    checkValid();
    this.cursors[this.index].timeout = (System.currentTimeMillis() + this.cursors[this.index].delay);
  }
  
  protected boolean hasTimedOut()
  {
    checkValid();
    return (this.cursors.length > 1) && (this.cursors[this.index].timeout < System.currentTimeMillis());
  }
  
  protected void nextCursor()
  {
    checkValid();
    this.index = (++this.index % this.cursors.length);
  }
  
  private static class CursorElement
  {
    final Object cursorHandle;
    final long delay;
    long timeout;
    
    CursorElement(Object cursorHandle, long delay, long timeout)
    {
      this.cursorHandle = cursorHandle;
      this.delay = delay;
      this.timeout = timeout;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.input.Cursor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */