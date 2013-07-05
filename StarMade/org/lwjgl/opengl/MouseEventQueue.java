/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.Point;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import java.awt.event.MouseWheelEvent;
/*     */ import java.awt.event.MouseWheelListener;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ 
/*     */ class MouseEventQueue extends EventQueue
/*     */   implements MouseListener, MouseMotionListener, MouseWheelListener
/*     */ {
/*     */   private static final int WHEEL_SCALE = 120;
/*     */   public static final int NUM_BUTTONS = 3;
/*     */   private final Component component;
/*     */   private boolean grabbed;
/*     */   private int accum_dx;
/*     */   private int accum_dy;
/*     */   private int accum_dz;
/*     */   private int last_x;
/*     */   private int last_y;
/*     */   private boolean saved_control_state;
/*  72 */   private final ByteBuffer event = ByteBuffer.allocate(22);
/*     */ 
/*  75 */   private final byte[] buttons = new byte[3];
/*     */ 
/*     */   MouseEventQueue(Component component) {
/*  78 */     super(22);
/*  79 */     this.component = component;
/*     */   }
/*     */ 
/*     */   public synchronized void register() {
/*  83 */     resetCursorToCenter();
/*  84 */     if (this.component != null) {
/*  85 */       this.component.addMouseListener(this);
/*  86 */       this.component.addMouseMotionListener(this);
/*  87 */       this.component.addMouseWheelListener(this);
/*     */     }
/*     */   }
/*     */ 
/*     */   public synchronized void unregister() {
/*  92 */     if (this.component != null) {
/*  93 */       this.component.removeMouseListener(this);
/*  94 */       this.component.removeMouseMotionListener(this);
/*  95 */       this.component.removeMouseWheelListener(this);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected Component getComponent() {
/* 100 */     return this.component;
/*     */   }
/*     */ 
/*     */   public synchronized void setGrabbed(boolean grabbed) {
/* 104 */     this.grabbed = grabbed;
/* 105 */     resetCursorToCenter();
/*     */   }
/*     */ 
/*     */   public synchronized boolean isGrabbed() {
/* 109 */     return this.grabbed;
/*     */   }
/*     */ 
/*     */   protected int transformY(int y) {
/* 113 */     if (this.component != null) {
/* 114 */       return this.component.getHeight() - 1 - y;
/*     */     }
/* 116 */     return y;
/*     */   }
/*     */ 
/*     */   protected void resetCursorToCenter() {
/* 120 */     clearEvents();
/* 121 */     this.accum_dx = (this.accum_dy = 0);
/* 122 */     if (this.component != null) {
/* 123 */       Point cursor_location = AWTUtil.getCursorPosition(this.component);
/* 124 */       if (cursor_location != null) {
/* 125 */         this.last_x = cursor_location.x;
/* 126 */         this.last_y = cursor_location.y;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void putMouseEvent(byte button, byte state, int dz, long nanos) {
/* 132 */     if (this.grabbed)
/* 133 */       putMouseEventWithCoords(button, state, 0, 0, dz, nanos);
/*     */     else
/* 135 */       putMouseEventWithCoords(button, state, this.last_x, this.last_y, dz, nanos);
/*     */   }
/*     */ 
/*     */   protected void putMouseEventWithCoords(byte button, byte state, int coord1, int coord2, int dz, long nanos) {
/* 139 */     this.event.clear();
/* 140 */     this.event.put(button).put(state).putInt(coord1).putInt(coord2).putInt(dz).putLong(nanos);
/* 141 */     this.event.flip();
/* 142 */     putEvent(this.event);
/*     */   }
/*     */ 
/*     */   public synchronized void poll(IntBuffer coord_buffer, ByteBuffer buttons_buffer) {
/* 146 */     if (this.grabbed) {
/* 147 */       coord_buffer.put(0, this.accum_dx);
/* 148 */       coord_buffer.put(1, this.accum_dy);
/*     */     } else {
/* 150 */       coord_buffer.put(0, this.last_x);
/* 151 */       coord_buffer.put(1, this.last_y);
/*     */     }
/* 153 */     coord_buffer.put(2, this.accum_dz);
/* 154 */     this.accum_dx = (this.accum_dy = this.accum_dz = 0);
/* 155 */     int old_position = buttons_buffer.position();
/* 156 */     buttons_buffer.put(this.buttons, 0, this.buttons.length);
/* 157 */     buttons_buffer.position(old_position);
/*     */   }
/*     */ 
/*     */   private void setCursorPos(int x, int y, long nanos) {
/* 161 */     y = transformY(y);
/* 162 */     if (this.grabbed)
/* 163 */       return;
/* 164 */     int dx = x - this.last_x;
/* 165 */     int dy = y - this.last_y;
/* 166 */     addDelta(dx, dy);
/* 167 */     this.last_x = x;
/* 168 */     this.last_y = y;
/* 169 */     putMouseEventWithCoords((byte)-1, (byte)0, x, y, 0, nanos);
/*     */   }
/*     */ 
/*     */   protected void addDelta(int dx, int dy) {
/* 173 */     this.accum_dx += dx;
/* 174 */     this.accum_dy += dy;
/*     */   }
/*     */ 
/*     */   public void mouseClicked(MouseEvent e)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void mouseEntered(MouseEvent e)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void mouseExited(MouseEvent e)
/*     */   {
/*     */   }
/*     */ 
/*     */   private void handleButton(MouseEvent e)
/*     */   {
/*     */     byte state;
/* 188 */     switch (e.getID()) {
/*     */     case 501:
/* 190 */       state = 1;
/* 191 */       break;
/*     */     case 502:
/* 193 */       state = 0;
/* 194 */       break;
/*     */     default:
/* 196 */       throw new IllegalArgumentException("Not a valid event ID: " + e.getID());
/*     */     }
/*     */     byte button;
/* 199 */     switch (e.getButton())
/*     */     {
/*     */     case 0:
/* 202 */       return;
/*     */     case 1:
/* 205 */       if (state == 1)
/* 206 */         this.saved_control_state = e.isControlDown();
/*     */       byte button;
/* 207 */       if (this.saved_control_state) {
/* 208 */         if (this.buttons[1] == state)
/* 209 */           return;
/* 210 */         button = 1;
/*     */       } else {
/* 212 */         button = 0;
/*     */       }
/* 214 */       break;
/*     */     case 2:
/* 216 */       button = 2;
/* 217 */       break;
/*     */     case 3:
/* 219 */       if (this.buttons[1] == state)
/* 220 */         return;
/* 221 */       button = 1;
/* 222 */       break;
/*     */     default:
/* 224 */       throw new IllegalArgumentException("Not a valid button: " + e.getButton());
/*     */     }
/* 226 */     setButton(button, state, e.getWhen() * 1000000L);
/*     */   }
/*     */ 
/*     */   public synchronized void mousePressed(MouseEvent e) {
/* 230 */     handleButton(e);
/*     */   }
/*     */ 
/*     */   private void setButton(byte button, byte state, long nanos) {
/* 234 */     this.buttons[button] = state;
/* 235 */     putMouseEvent(button, state, 0, nanos);
/*     */   }
/*     */ 
/*     */   public synchronized void mouseReleased(MouseEvent e) {
/* 239 */     handleButton(e);
/*     */   }
/*     */ 
/*     */   private void handleMotion(MouseEvent e) {
/* 243 */     if (this.grabbed)
/* 244 */       updateDeltas(e.getWhen() * 1000000L);
/*     */     else
/* 246 */       setCursorPos(e.getX(), e.getY(), e.getWhen() * 1000000L);
/*     */   }
/*     */ 
/*     */   public synchronized void mouseDragged(MouseEvent e)
/*     */   {
/* 251 */     handleMotion(e);
/*     */   }
/*     */ 
/*     */   public synchronized void mouseMoved(MouseEvent e) {
/* 255 */     handleMotion(e);
/*     */   }
/*     */ 
/*     */   private void handleWheel(int amount, long nanos) {
/* 259 */     this.accum_dz += amount;
/* 260 */     putMouseEvent((byte)-1, (byte)0, amount, nanos);
/*     */   }
/*     */ 
/*     */   protected void updateDeltas(long nanos) {
/*     */   }
/*     */ 
/*     */   public synchronized void mouseWheelMoved(MouseWheelEvent e) {
/* 267 */     int wheel_amount = -e.getWheelRotation() * 120;
/* 268 */     handleWheel(wheel_amount, e.getWhen() * 1000000L);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.MouseEventQueue
 * JD-Core Version:    0.6.2
 */