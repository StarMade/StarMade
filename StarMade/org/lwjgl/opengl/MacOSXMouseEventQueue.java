/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.awt.Component;
/*   4:    */import java.awt.Point;
/*   5:    */import java.awt.Rectangle;
/*   6:    */import java.nio.IntBuffer;
/*   7:    */import org.lwjgl.BufferUtils;
/*   8:    */
/*  44:    */final class MacOSXMouseEventQueue
/*  45:    */  extends MouseEventQueue
/*  46:    */{
/*  47: 47 */  private final IntBuffer delta_buffer = BufferUtils.createIntBuffer(2);
/*  48:    */  private boolean skip_event;
/*  49:    */  private static boolean is_grabbed;
/*  50:    */  
/*  51:    */  MacOSXMouseEventQueue(Component component)
/*  52:    */  {
/*  53: 53 */    super(component);
/*  54:    */  }
/*  55:    */  
/*  56:    */  public void setGrabbed(boolean grab) {
/*  57: 57 */    if (is_grabbed != grab) {
/*  58: 58 */      super.setGrabbed(grab);
/*  59: 59 */      warpCursor();
/*  60: 60 */      grabMouse(grab);
/*  61:    */    }
/*  62:    */  }
/*  63:    */  
/*  64:    */  private static synchronized void grabMouse(boolean grab) {
/*  65: 65 */    is_grabbed = grab;
/*  66: 66 */    if (!grab)
/*  67: 67 */      nGrabMouse(grab);
/*  68:    */  }
/*  69:    */  
/*  70:    */  protected void resetCursorToCenter() {
/*  71: 71 */    super.resetCursorToCenter();
/*  72:    */    
/*  73: 73 */    getMouseDeltas(this.delta_buffer);
/*  74:    */  }
/*  75:    */  
/*  76:    */  protected void updateDeltas(long nanos) {
/*  77: 77 */    super.updateDeltas(nanos);
/*  78: 78 */    synchronized (this) {
/*  79: 79 */      getMouseDeltas(this.delta_buffer);
/*  80: 80 */      int dx = this.delta_buffer.get(0);
/*  81: 81 */      int dy = -this.delta_buffer.get(1);
/*  82: 82 */      if (this.skip_event) {
/*  83: 83 */        this.skip_event = false;
/*  84: 84 */        nGrabMouse(isGrabbed());
/*  85: 85 */        return;
/*  86:    */      }
/*  87: 87 */      if ((dx != 0) || (dy != 0)) {
/*  88: 88 */        putMouseEventWithCoords((byte)-1, (byte)0, dx, dy, 0, nanos);
/*  89: 89 */        addDelta(dx, dy);
/*  90:    */      }
/*  91:    */    }
/*  92:    */  }
/*  93:    */  
/*  94:    */  void warpCursor() {
/*  95: 95 */    synchronized (this)
/*  96:    */    {
/*  97: 97 */      this.skip_event = isGrabbed();
/*  98:    */    }
/*  99: 99 */    if (isGrabbed()) {
/* 100:100 */      Rectangle bounds = getComponent().getBounds();
/* 101:101 */      Point location_on_screen = getComponent().getLocationOnScreen();
/* 102:102 */      int x = location_on_screen.x + bounds.width / 2;
/* 103:103 */      int y = location_on_screen.y + bounds.height / 2;
/* 104:104 */      nWarpCursor(x, y);
/* 105:    */    }
/* 106:    */  }
/* 107:    */  
/* 108:    */  private static native void getMouseDeltas(IntBuffer paramIntBuffer);
/* 109:    */  
/* 110:    */  private static native void nWarpCursor(int paramInt1, int paramInt2);
/* 111:    */  
/* 112:    */  static native void nGrabMouse(boolean paramBoolean);
/* 113:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.MacOSXMouseEventQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */