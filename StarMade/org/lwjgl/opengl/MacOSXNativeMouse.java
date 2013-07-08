package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;

final class MacOSXNativeMouse
  extends EventQueue
{
  private static final int WHEEL_SCALE = 120;
  private static final int NUM_BUTTONS = 3;
  private ByteBuffer window_handle;
  private MacOSXDisplay display;
  private boolean grabbed;
  private float accum_dx;
  private float accum_dy;
  private int accum_dz;
  private float last_x;
  private float last_y;
  private boolean saved_control_state;
  private final ByteBuffer event = ByteBuffer.allocate(22);
  private IntBuffer delta_buffer = BufferUtils.createIntBuffer(2);
  private int skip_event;
  private final byte[] buttons = new byte[3];
  
  MacOSXNativeMouse(MacOSXDisplay display, ByteBuffer window_handle)
  {
    super(22);
    this.display = display;
    this.window_handle = window_handle;
  }
  
  private native void nSetCursorPosition(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2);
  
  public static native void nGrabMouse(boolean paramBoolean);
  
  private native void nRegisterMouseListener(ByteBuffer paramByteBuffer);
  
  private native void nUnregisterMouseListener(ByteBuffer paramByteBuffer);
  
  private static native long nCreateCursor(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, IntBuffer paramIntBuffer1, int paramInt6, IntBuffer paramIntBuffer2, int paramInt7)
    throws LWJGLException;
  
  private static native void nDestroyCursor(long paramLong);
  
  private static native void nSetCursor(long paramLong)
    throws LWJGLException;
  
  public synchronized void register()
  {
    nRegisterMouseListener(this.window_handle);
  }
  
  public static long createCursor(int width, int height, int xHotspot, int yHotspot, int numImages, IntBuffer images, IntBuffer delays)
    throws LWJGLException
  {
    try
    {
      return nCreateCursor(width, height, xHotspot, yHotspot, numImages, images, images.position(), delays, delays != null ? delays.position() : -1);
    }
    catch (LWJGLException local_e)
    {
      throw local_e;
    }
  }
  
  public static void destroyCursor(long cursor_handle)
  {
    nDestroyCursor(cursor_handle);
  }
  
  public static void setCursor(long cursor_handle)
    throws LWJGLException
  {
    try
    {
      nSetCursor(cursor_handle);
    }
    catch (LWJGLException local_e)
    {
      throw local_e;
    }
  }
  
  public synchronized void setCursorPosition(int local_x, int local_y)
  {
    nSetCursorPosition(this.window_handle, local_x, local_y);
  }
  
  public synchronized void unregister()
  {
    nUnregisterMouseListener(this.window_handle);
  }
  
  public synchronized void setGrabbed(boolean grabbed)
  {
    this.grabbed = grabbed;
    nGrabMouse(grabbed);
    this.skip_event = 1;
    this.accum_dx = (this.accum_dy = 0.0F);
  }
  
  public synchronized boolean isGrabbed()
  {
    return this.grabbed;
  }
  
  protected void resetCursorToCenter()
  {
    clearEvents();
    this.accum_dx = (this.accum_dy = 0.0F);
    if (this.display != null)
    {
      this.last_x = (this.display.getWidth() / 2);
      this.last_y = (this.display.getHeight() / 2);
    }
  }
  
  private void putMouseEvent(byte button, byte state, int local_dz, long nanos)
  {
    if (this.grabbed) {
      putMouseEventWithCoords(button, state, 0, 0, local_dz, nanos);
    } else {
      putMouseEventWithCoords(button, state, (int)this.last_x, (int)this.last_y, local_dz, nanos);
    }
  }
  
  protected void putMouseEventWithCoords(byte button, byte state, int coord1, int coord2, int local_dz, long nanos)
  {
    this.event.clear();
    this.event.put(button).put(state).putInt(coord1).putInt(coord2).putInt(local_dz).putLong(nanos);
    this.event.flip();
    putEvent(this.event);
  }
  
  public synchronized void poll(IntBuffer coord_buffer, ByteBuffer buttons_buffer)
  {
    if (this.grabbed)
    {
      coord_buffer.put(0, (int)this.accum_dx);
      coord_buffer.put(1, (int)this.accum_dy);
    }
    else
    {
      coord_buffer.put(0, (int)this.last_x);
      coord_buffer.put(1, (int)this.last_y);
    }
    coord_buffer.put(2, this.accum_dz);
    this.accum_dx = (this.accum_dy = this.accum_dz = 0);
    int old_position = buttons_buffer.position();
    buttons_buffer.put(this.buttons, 0, this.buttons.length);
    buttons_buffer.position(old_position);
  }
  
  private void setCursorPos(float local_x, float local_y, long nanos)
  {
    if (this.grabbed) {
      return;
    }
    float local_dx = local_x - this.last_x;
    float local_dy = local_y - this.last_y;
    addDelta(local_dx, local_dy);
    this.last_x = local_x;
    this.last_y = local_y;
    putMouseEventWithCoords((byte)-1, (byte)0, (int)local_x, (int)local_y, 0, nanos);
  }
  
  protected void addDelta(float local_dx, float local_dy)
  {
    this.accum_dx += local_dx;
    this.accum_dy += -local_dy;
  }
  
  public synchronized void setButton(int button, int state, long nanos)
  {
    this.buttons[button] = ((byte)state);
    putMouseEvent((byte)button, (byte)state, 0, nanos);
  }
  
  public synchronized void mouseMoved(float local_x, float local_y, float local_dx, float local_dy, float local_dz, long nanos)
  {
    if (this.skip_event > 0)
    {
      this.skip_event -= 1;
      if (this.skip_event == 0)
      {
        this.last_x = local_x;
        this.last_y = local_y;
      }
      return;
    }
    if (this.grabbed)
    {
      if ((local_dx != 0.0F) || (local_dy != 0.0F))
      {
        putMouseEventWithCoords((byte)-1, (byte)0, (int)local_dx, (int)-local_dy, 0, nanos);
        addDelta(local_dx, local_dy);
      }
    }
    else {
      setCursorPos(local_x, local_y, nanos);
    }
    if (local_dz != 0.0F)
    {
      if (local_dy == 0.0F) {
        local_dy = local_dx;
      }
      int wheel_amount = (int)(local_dy * 120.0F);
      this.accum_dz += wheel_amount;
      putMouseEvent((byte)-1, (byte)0, wheel_amount, nanos);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.MacOSXNativeMouse
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */