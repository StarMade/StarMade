/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.awt.Component;
/*    */ import java.awt.Point;
/*    */ import java.awt.Rectangle;
/*    */ import java.nio.IntBuffer;
/*    */ import org.lwjgl.BufferUtils;
/*    */ 
/*    */ final class MacOSXMouseEventQueue extends MouseEventQueue
/*    */ {
/* 47 */   private final IntBuffer delta_buffer = BufferUtils.createIntBuffer(2);
/*    */   private boolean skip_event;
/*    */   private static boolean is_grabbed;
/*    */ 
/*    */   MacOSXMouseEventQueue(Component component)
/*    */   {
/* 53 */     super(component);
/*    */   }
/*    */ 
/*    */   public void setGrabbed(boolean grab) {
/* 57 */     if (is_grabbed != grab) {
/* 58 */       super.setGrabbed(grab);
/* 59 */       warpCursor();
/* 60 */       grabMouse(grab);
/*    */     }
/*    */   }
/*    */ 
/*    */   private static synchronized void grabMouse(boolean grab) {
/* 65 */     is_grabbed = grab;
/* 66 */     if (!grab)
/* 67 */       nGrabMouse(grab);
/*    */   }
/*    */ 
/*    */   protected void resetCursorToCenter() {
/* 71 */     super.resetCursorToCenter();
/*    */ 
/* 73 */     getMouseDeltas(this.delta_buffer);
/*    */   }
/*    */ 
/*    */   protected void updateDeltas(long nanos) {
/* 77 */     super.updateDeltas(nanos);
/* 78 */     synchronized (this) {
/* 79 */       getMouseDeltas(this.delta_buffer);
/* 80 */       int dx = this.delta_buffer.get(0);
/* 81 */       int dy = -this.delta_buffer.get(1);
/* 82 */       if (this.skip_event) {
/* 83 */         this.skip_event = false;
/* 84 */         nGrabMouse(isGrabbed());
/* 85 */         return;
/*    */       }
/* 87 */       if ((dx != 0) || (dy != 0)) {
/* 88 */         putMouseEventWithCoords((byte)-1, (byte)0, dx, dy, 0, nanos);
/* 89 */         addDelta(dx, dy);
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */   void warpCursor() {
/* 95 */     synchronized (this)
/*    */     {
/* 97 */       this.skip_event = isGrabbed();
/*    */     }
/* 99 */     if (isGrabbed()) {
/* 100 */       Rectangle bounds = getComponent().getBounds();
/* 101 */       Point location_on_screen = getComponent().getLocationOnScreen();
/* 102 */       int x = location_on_screen.x + bounds.width / 2;
/* 103 */       int y = location_on_screen.y + bounds.height / 2;
/* 104 */       nWarpCursor(x, y);
/*    */     }
/*    */   }
/*    */ 
/*    */   private static native void getMouseDeltas(IntBuffer paramIntBuffer);
/*    */ 
/*    */   private static native void nWarpCursor(int paramInt1, int paramInt2);
/*    */ 
/*    */   static native void nGrabMouse(boolean paramBoolean);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.MacOSXMouseEventQueue
 * JD-Core Version:    0.6.2
 */