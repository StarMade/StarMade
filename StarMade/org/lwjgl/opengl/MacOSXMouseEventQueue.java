package org.lwjgl.opengl;

import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;

final class MacOSXMouseEventQueue
  extends MouseEventQueue
{
  private final IntBuffer delta_buffer = BufferUtils.createIntBuffer(2);
  private boolean skip_event;
  private static boolean is_grabbed;
  
  MacOSXMouseEventQueue(Component component)
  {
    super(component);
  }
  
  public void setGrabbed(boolean grab)
  {
    if (is_grabbed != grab)
    {
      super.setGrabbed(grab);
      warpCursor();
      grabMouse(grab);
    }
  }
  
  private static synchronized void grabMouse(boolean grab)
  {
    is_grabbed = grab;
    if (!grab) {
      nGrabMouse(grab);
    }
  }
  
  protected void resetCursorToCenter()
  {
    super.resetCursorToCenter();
    getMouseDeltas(this.delta_buffer);
  }
  
  protected void updateDeltas(long nanos)
  {
    super.updateDeltas(nanos);
    synchronized (this)
    {
      getMouseDeltas(this.delta_buffer);
      int local_dx = this.delta_buffer.get(0);
      int local_dy = -this.delta_buffer.get(1);
      if (this.skip_event)
      {
        this.skip_event = false;
        nGrabMouse(isGrabbed());
        return;
      }
      if ((local_dx != 0) || (local_dy != 0))
      {
        putMouseEventWithCoords((byte)-1, (byte)0, local_dx, local_dy, 0, nanos);
        addDelta(local_dx, local_dy);
      }
    }
  }
  
  void warpCursor()
  {
    synchronized (this)
    {
      this.skip_event = isGrabbed();
    }
    if (isGrabbed())
    {
      Rectangle bounds = getComponent().getBounds();
      Point location_on_screen = getComponent().getLocationOnScreen();
      int local_x = location_on_screen.x + bounds.width / 2;
      int local_y = location_on_screen.y + bounds.height / 2;
      nWarpCursor(local_x, local_y);
    }
  }
  
  private static native void getMouseDeltas(IntBuffer paramIntBuffer);
  
  private static native void nWarpCursor(int paramInt1, int paramInt2);
  
  static native void nGrabMouse(boolean paramBoolean);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.MacOSXMouseEventQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */