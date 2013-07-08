package org.lwjgl.opengl;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

class MouseEventQueue
  extends EventQueue
  implements MouseListener, MouseMotionListener, MouseWheelListener
{
  private static final int WHEEL_SCALE = 120;
  public static final int NUM_BUTTONS = 3;
  private final Component component;
  private boolean grabbed;
  private int accum_dx;
  private int accum_dy;
  private int accum_dz;
  private int last_x;
  private int last_y;
  private boolean saved_control_state;
  private final ByteBuffer event = ByteBuffer.allocate(22);
  private final byte[] buttons = new byte[3];
  
  MouseEventQueue(Component component)
  {
    super(22);
    this.component = component;
  }
  
  public synchronized void register()
  {
    resetCursorToCenter();
    if (this.component != null)
    {
      this.component.addMouseListener(this);
      this.component.addMouseMotionListener(this);
      this.component.addMouseWheelListener(this);
    }
  }
  
  public synchronized void unregister()
  {
    if (this.component != null)
    {
      this.component.removeMouseListener(this);
      this.component.removeMouseMotionListener(this);
      this.component.removeMouseWheelListener(this);
    }
  }
  
  protected Component getComponent()
  {
    return this.component;
  }
  
  public synchronized void setGrabbed(boolean grabbed)
  {
    this.grabbed = grabbed;
    resetCursorToCenter();
  }
  
  public synchronized boolean isGrabbed()
  {
    return this.grabbed;
  }
  
  protected int transformY(int local_y)
  {
    if (this.component != null) {
      return this.component.getHeight() - 1 - local_y;
    }
    return local_y;
  }
  
  protected void resetCursorToCenter()
  {
    clearEvents();
    this.accum_dx = (this.accum_dy = 0);
    if (this.component != null)
    {
      Point cursor_location = AWTUtil.getCursorPosition(this.component);
      if (cursor_location != null)
      {
        this.last_x = cursor_location.x;
        this.last_y = cursor_location.y;
      }
    }
  }
  
  private void putMouseEvent(byte button, byte state, int local_dz, long nanos)
  {
    if (this.grabbed) {
      putMouseEventWithCoords(button, state, 0, 0, local_dz, nanos);
    } else {
      putMouseEventWithCoords(button, state, this.last_x, this.last_y, local_dz, nanos);
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
      coord_buffer.put(0, this.accum_dx);
      coord_buffer.put(1, this.accum_dy);
    }
    else
    {
      coord_buffer.put(0, this.last_x);
      coord_buffer.put(1, this.last_y);
    }
    coord_buffer.put(2, this.accum_dz);
    this.accum_dx = (this.accum_dy = this.accum_dz = 0);
    int old_position = buttons_buffer.position();
    buttons_buffer.put(this.buttons, 0, this.buttons.length);
    buttons_buffer.position(old_position);
  }
  
  private void setCursorPos(int local_x, int local_y, long nanos)
  {
    local_y = transformY(local_y);
    if (this.grabbed) {
      return;
    }
    int local_dx = local_x - this.last_x;
    int local_dy = local_y - this.last_y;
    addDelta(local_dx, local_dy);
    this.last_x = local_x;
    this.last_y = local_y;
    putMouseEventWithCoords((byte)-1, (byte)0, local_x, local_y, 0, nanos);
  }
  
  protected void addDelta(int local_dx, int local_dy)
  {
    this.accum_dx += local_dx;
    this.accum_dy += local_dy;
  }
  
  public void mouseClicked(MouseEvent local_e) {}
  
  public void mouseEntered(MouseEvent local_e) {}
  
  public void mouseExited(MouseEvent local_e) {}
  
  private void handleButton(MouseEvent local_e)
  {
    byte state;
    switch (local_e.getID())
    {
    case 501: 
      state = 1;
      break;
    case 502: 
      state = 0;
      break;
    default: 
      throw new IllegalArgumentException("Not a valid event ID: " + local_e.getID());
    }
    byte button;
    switch (local_e.getButton())
    {
    case 0: 
      return;
    case 1: 
      if (state == 1) {
        this.saved_control_state = local_e.isControlDown();
      }
      byte button;
      if (this.saved_control_state)
      {
        if (this.buttons[1] == state) {
          return;
        }
        button = 1;
      }
      else
      {
        button = 0;
      }
      break;
    case 2: 
      button = 2;
      break;
    case 3: 
      if (this.buttons[1] == state) {
        return;
      }
      button = 1;
      break;
    default: 
      throw new IllegalArgumentException("Not a valid button: " + local_e.getButton());
    }
    setButton(button, state, local_e.getWhen() * 1000000L);
  }
  
  public synchronized void mousePressed(MouseEvent local_e)
  {
    handleButton(local_e);
  }
  
  private void setButton(byte button, byte state, long nanos)
  {
    this.buttons[button] = state;
    putMouseEvent(button, state, 0, nanos);
  }
  
  public synchronized void mouseReleased(MouseEvent local_e)
  {
    handleButton(local_e);
  }
  
  private void handleMotion(MouseEvent local_e)
  {
    if (this.grabbed) {
      updateDeltas(local_e.getWhen() * 1000000L);
    } else {
      setCursorPos(local_e.getX(), local_e.getY(), local_e.getWhen() * 1000000L);
    }
  }
  
  public synchronized void mouseDragged(MouseEvent local_e)
  {
    handleMotion(local_e);
  }
  
  public synchronized void mouseMoved(MouseEvent local_e)
  {
    handleMotion(local_e);
  }
  
  private void handleWheel(int amount, long nanos)
  {
    this.accum_dz += amount;
    putMouseEvent((byte)-1, (byte)0, amount, nanos);
  }
  
  protected void updateDeltas(long nanos) {}
  
  public synchronized void mouseWheelMoved(MouseWheelEvent local_e)
  {
    int wheel_amount = -local_e.getWheelRotation() * 120;
    handleWheel(wheel_amount, local_e.getWhen() * 1000000L);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.MouseEventQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */