/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.LWJGLException;
/*     */ import org.lwjgl.LWJGLUtil;
/*     */ 
/*     */ final class WindowsMouse
/*     */ {
/*     */   private final long hwnd;
/*     */   private final int mouse_button_count;
/*     */   private final boolean has_wheel;
/*  53 */   private final EventQueue event_queue = new EventQueue(22);
/*     */ 
/*  55 */   private final ByteBuffer mouse_event = ByteBuffer.allocate(22);
/*     */   private final Object blank_cursor;
/*     */   private boolean mouse_grabbed;
/*     */   private byte[] button_states;
/*     */   private int accum_dx;
/*     */   private int accum_dy;
/*     */   private int accum_dwheel;
/*     */   private int last_x;
/*     */   private int last_y;
/*     */ 
/*     */   WindowsMouse(long hwnd)
/*     */     throws LWJGLException
/*     */   {
/*  67 */     this.hwnd = hwnd;
/*  68 */     this.mouse_button_count = Math.min(5, WindowsDisplay.getSystemMetrics(43));
/*  69 */     this.has_wheel = (WindowsDisplay.getSystemMetrics(75) != 0);
/*  70 */     this.blank_cursor = createBlankCursor();
/*  71 */     this.button_states = new byte[this.mouse_button_count];
/*     */   }
/*     */ 
/*     */   private Object createBlankCursor() throws LWJGLException {
/*  75 */     int width = WindowsDisplay.getSystemMetrics(13);
/*  76 */     int height = WindowsDisplay.getSystemMetrics(14);
/*  77 */     IntBuffer pixels = BufferUtils.createIntBuffer(width * height);
/*  78 */     return WindowsDisplay.doCreateCursor(width, height, 0, 0, 1, pixels, null);
/*     */   }
/*     */ 
/*     */   public boolean isGrabbed() {
/*  82 */     return this.mouse_grabbed;
/*     */   }
/*     */ 
/*     */   public boolean hasWheel() {
/*  86 */     return this.has_wheel;
/*     */   }
/*     */ 
/*     */   public int getButtonCount() {
/*  90 */     return this.mouse_button_count;
/*     */   }
/*     */ 
/*     */   public void poll(IntBuffer coord_buffer, ByteBuffer buttons) {
/*  94 */     for (int i = 0; i < coord_buffer.remaining(); i++)
/*  95 */       coord_buffer.put(coord_buffer.position() + i, 0);
/*  96 */     int num_buttons = this.mouse_button_count;
/*  97 */     coord_buffer.put(coord_buffer.position() + 2, this.accum_dwheel);
/*  98 */     if (num_buttons > this.button_states.length)
/*  99 */       num_buttons = this.button_states.length;
/* 100 */     for (int j = 0; j < num_buttons; j++) {
/* 101 */       buttons.put(buttons.position() + j, this.button_states[j]);
/*     */     }
/* 103 */     if (isGrabbed()) {
/* 104 */       coord_buffer.put(coord_buffer.position() + 0, this.accum_dx);
/* 105 */       coord_buffer.put(coord_buffer.position() + 1, this.accum_dy);
/*     */     } else {
/* 107 */       coord_buffer.put(coord_buffer.position() + 0, this.last_x);
/* 108 */       coord_buffer.put(coord_buffer.position() + 1, this.last_y);
/*     */     }
/* 110 */     this.accum_dx = (this.accum_dy = this.accum_dwheel = 0);
/*     */   }
/*     */ 
/*     */   private void putMouseEventWithCoords(byte button, byte state, int coord1, int coord2, int dz, long nanos) {
/* 114 */     this.mouse_event.clear();
/* 115 */     this.mouse_event.put(button).put(state).putInt(coord1).putInt(coord2).putInt(dz).putLong(nanos);
/* 116 */     this.mouse_event.flip();
/* 117 */     this.event_queue.putEvent(this.mouse_event);
/*     */   }
/*     */ 
/*     */   private void putMouseEvent(byte button, byte state, int dz, long nanos) {
/* 121 */     if (this.mouse_grabbed)
/* 122 */       putMouseEventWithCoords(button, state, 0, 0, dz, nanos);
/*     */     else
/* 124 */       putMouseEventWithCoords(button, state, this.last_x, this.last_y, dz, nanos);
/*     */   }
/*     */ 
/*     */   public void read(ByteBuffer buffer) {
/* 128 */     this.event_queue.copyEvents(buffer);
/*     */   }
/*     */ 
/*     */   public Object getBlankCursor() {
/* 132 */     return this.blank_cursor;
/*     */   }
/*     */ 
/*     */   public void grab(boolean grab, boolean should_center) {
/* 136 */     if (grab) {
/* 137 */       if (!this.mouse_grabbed) {
/* 138 */         this.mouse_grabbed = true;
/* 139 */         if (should_center) {
/*     */           try {
/* 141 */             WindowsDisplay.setupCursorClipping(this.hwnd);
/*     */           } catch (LWJGLException e) {
/* 143 */             LWJGLUtil.log("Failed to setup cursor clipping: " + e);
/*     */           }
/* 145 */           centerCursor();
/*     */         }
/*     */       }
/*     */     }
/* 149 */     else if (this.mouse_grabbed) {
/* 150 */       this.mouse_grabbed = false;
/* 151 */       WindowsDisplay.resetCursorClipping();
/*     */     }
/*     */ 
/* 154 */     this.event_queue.clearEvents();
/*     */   }
/*     */ 
/*     */   public void handleMouseScrolled(int event_dwheel, long millis) {
/* 158 */     this.accum_dwheel += event_dwheel;
/* 159 */     putMouseEvent((byte)-1, (byte)0, event_dwheel, millis * 1000000L);
/*     */   }
/*     */ 
/*     */   private void centerCursor() {
/* 163 */     WindowsDisplay.centerCursor(this.hwnd);
/*     */   }
/*     */ 
/*     */   public void setPosition(int x, int y) {
/* 167 */     this.last_x = x;
/* 168 */     this.last_y = y;
/*     */   }
/*     */ 
/*     */   public void destroy() {
/* 172 */     WindowsDisplay.doDestroyCursor(this.blank_cursor);
/*     */   }
/*     */ 
/*     */   public void handleMouseMoved(int x, int y, long millis, boolean should_center) {
/* 176 */     int dx = x - this.last_x;
/* 177 */     int dy = y - this.last_y;
/* 178 */     if ((dx != 0) || (dy != 0)) {
/* 179 */       this.accum_dx += dx;
/* 180 */       this.accum_dy += dy;
/* 181 */       this.last_x = x;
/* 182 */       this.last_y = y;
/* 183 */       long nanos = millis * 1000000L;
/* 184 */       if (this.mouse_grabbed) {
/* 185 */         putMouseEventWithCoords((byte)-1, (byte)0, dx, dy, 0, nanos);
/* 186 */         if (should_center)
/* 187 */           centerCursor();
/*     */       } else {
/* 189 */         putMouseEventWithCoords((byte)-1, (byte)0, x, y, 0, nanos);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void handleMouseButton(byte button, byte state, long millis) {
/* 195 */     putMouseEvent(button, state, 0, millis * 1000000L);
/* 196 */     if (button < this.button_states.length)
/* 197 */       this.button_states[button] = (state != 0 ? 1 : 0);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.WindowsMouse
 * JD-Core Version:    0.6.2
 */