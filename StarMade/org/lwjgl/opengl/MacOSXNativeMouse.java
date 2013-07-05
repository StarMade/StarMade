/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.LWJGLException;
/*     */ 
/*     */ final class MacOSXNativeMouse extends EventQueue
/*     */ {
/*     */   private static final int WHEEL_SCALE = 120;
/*     */   private static final int NUM_BUTTONS = 3;
/*     */   private ByteBuffer window_handle;
/*     */   private MacOSXDisplay display;
/*     */   private boolean grabbed;
/*     */   private float accum_dx;
/*     */   private float accum_dy;
/*     */   private int accum_dz;
/*     */   private float last_x;
/*     */   private float last_y;
/*     */   private boolean saved_control_state;
/*  73 */   private final ByteBuffer event = ByteBuffer.allocate(22);
/*  74 */   private IntBuffer delta_buffer = BufferUtils.createIntBuffer(2);
/*     */   private int skip_event;
/*  77 */   private final byte[] buttons = new byte[3];
/*     */ 
/*     */   MacOSXNativeMouse(MacOSXDisplay display, ByteBuffer window_handle) {
/*  80 */     super(22);
/*  81 */     this.display = display;
/*  82 */     this.window_handle = window_handle;
/*     */   }
/*     */ 
/*     */   private native void nSetCursorPosition(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2);
/*     */ 
/*     */   public static native void nGrabMouse(boolean paramBoolean);
/*     */ 
/*     */   private native void nRegisterMouseListener(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   private native void nUnregisterMouseListener(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   private static native long nCreateCursor(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, IntBuffer paramIntBuffer1, int paramInt6, IntBuffer paramIntBuffer2, int paramInt7) throws LWJGLException;
/*     */ 
/*     */   private static native void nDestroyCursor(long paramLong);
/*     */ 
/*     */   private static native void nSetCursor(long paramLong) throws LWJGLException;
/*     */ 
/*     */   public synchronized void register() {
/* 100 */     nRegisterMouseListener(this.window_handle);
/*     */   }
/*     */ 
/*     */   public static long createCursor(int width, int height, int xHotspot, int yHotspot, int numImages, IntBuffer images, IntBuffer delays) throws LWJGLException {
/*     */     try {
/* 105 */       return nCreateCursor(width, height, xHotspot, yHotspot, numImages, images, images.position(), delays, delays != null ? delays.position() : -1);
/*     */     } catch (LWJGLException e) {
/* 107 */       throw e;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void destroyCursor(long cursor_handle) {
/* 112 */     nDestroyCursor(cursor_handle);
/*     */   }
/*     */ 
/*     */   public static void setCursor(long cursor_handle) throws LWJGLException {
/*     */     try {
/* 117 */       nSetCursor(cursor_handle);
/*     */     } catch (LWJGLException e) {
/* 119 */       throw e;
/*     */     }
/*     */   }
/*     */ 
/*     */   public synchronized void setCursorPosition(int x, int y) {
/* 124 */     nSetCursorPosition(this.window_handle, x, y);
/*     */   }
/*     */ 
/*     */   public synchronized void unregister() {
/* 128 */     nUnregisterMouseListener(this.window_handle);
/*     */   }
/*     */ 
/*     */   public synchronized void setGrabbed(boolean grabbed) {
/* 132 */     this.grabbed = grabbed;
/* 133 */     nGrabMouse(grabbed);
/* 134 */     this.skip_event = 1;
/* 135 */     this.accum_dx = (this.accum_dy = 0.0F);
/*     */   }
/*     */ 
/*     */   public synchronized boolean isGrabbed() {
/* 139 */     return this.grabbed;
/*     */   }
/*     */ 
/*     */   protected void resetCursorToCenter() {
/* 143 */     clearEvents();
/* 144 */     this.accum_dx = (this.accum_dy = 0.0F);
/* 145 */     if (this.display != null) {
/* 146 */       this.last_x = (this.display.getWidth() / 2);
/* 147 */       this.last_y = (this.display.getHeight() / 2);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void putMouseEvent(byte button, byte state, int dz, long nanos) {
/* 152 */     if (this.grabbed)
/* 153 */       putMouseEventWithCoords(button, state, 0, 0, dz, nanos);
/*     */     else
/* 155 */       putMouseEventWithCoords(button, state, (int)this.last_x, (int)this.last_y, dz, nanos);
/*     */   }
/*     */ 
/*     */   protected void putMouseEventWithCoords(byte button, byte state, int coord1, int coord2, int dz, long nanos) {
/* 159 */     this.event.clear();
/* 160 */     this.event.put(button).put(state).putInt(coord1).putInt(coord2).putInt(dz).putLong(nanos);
/* 161 */     this.event.flip();
/* 162 */     putEvent(this.event);
/*     */   }
/*     */ 
/*     */   public synchronized void poll(IntBuffer coord_buffer, ByteBuffer buttons_buffer) {
/* 166 */     if (this.grabbed) {
/* 167 */       coord_buffer.put(0, (int)this.accum_dx);
/* 168 */       coord_buffer.put(1, (int)this.accum_dy);
/*     */     } else {
/* 170 */       coord_buffer.put(0, (int)this.last_x);
/* 171 */       coord_buffer.put(1, (int)this.last_y);
/*     */     }
/* 173 */     coord_buffer.put(2, this.accum_dz);
/* 174 */     this.accum_dx = (this.accum_dy = this.accum_dz = 0);
/* 175 */     int old_position = buttons_buffer.position();
/* 176 */     buttons_buffer.put(this.buttons, 0, this.buttons.length);
/* 177 */     buttons_buffer.position(old_position);
/*     */   }
/*     */ 
/*     */   private void setCursorPos(float x, float y, long nanos) {
/* 181 */     if (this.grabbed)
/* 182 */       return;
/* 183 */     float dx = x - this.last_x;
/* 184 */     float dy = y - this.last_y;
/* 185 */     addDelta(dx, dy);
/* 186 */     this.last_x = x;
/* 187 */     this.last_y = y;
/* 188 */     putMouseEventWithCoords((byte)-1, (byte)0, (int)x, (int)y, 0, nanos);
/*     */   }
/*     */ 
/*     */   protected void addDelta(float dx, float dy) {
/* 192 */     this.accum_dx += dx;
/* 193 */     this.accum_dy += -dy;
/*     */   }
/*     */ 
/*     */   public synchronized void setButton(int button, int state, long nanos) {
/* 197 */     this.buttons[button] = ((byte)state);
/* 198 */     putMouseEvent((byte)button, (byte)state, 0, nanos);
/*     */   }
/*     */ 
/*     */   public synchronized void mouseMoved(float x, float y, float dx, float dy, float dz, long nanos) {
/* 202 */     if (this.skip_event > 0) {
/* 203 */       this.skip_event -= 1;
/* 204 */       if (this.skip_event == 0) {
/* 205 */         this.last_x = x;
/* 206 */         this.last_y = y;
/*     */       }
/* 208 */       return;
/*     */     }
/* 210 */     if (this.grabbed) {
/* 211 */       if ((dx != 0.0F) || (dy != 0.0F)) {
/* 212 */         putMouseEventWithCoords((byte)-1, (byte)0, (int)dx, (int)-dy, 0, nanos);
/* 213 */         addDelta(dx, dy);
/*     */       }
/*     */     }
/* 216 */     else setCursorPos(x, y, nanos);
/*     */ 
/* 218 */     if (dz != 0.0F)
/*     */     {
/* 220 */       if (dy == 0.0F) dy = dx;
/*     */ 
/* 222 */       int wheel_amount = (int)(dy * 120.0F);
/* 223 */       this.accum_dz += wheel_amount;
/* 224 */       putMouseEvent((byte)-1, (byte)0, wheel_amount, nanos);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.MacOSXNativeMouse
 * JD-Core Version:    0.6.2
 */