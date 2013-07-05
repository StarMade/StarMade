/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ 
/*     */ final class LinuxEvent
/*     */ {
/*     */   public static final int FocusIn = 9;
/*     */   public static final int FocusOut = 10;
/*     */   public static final int KeyPress = 2;
/*     */   public static final int KeyRelease = 3;
/*     */   public static final int ButtonPress = 4;
/*     */   public static final int ButtonRelease = 5;
/*     */   public static final int MotionNotify = 6;
/*     */   public static final int EnterNotify = 7;
/*     */   public static final int LeaveNotify = 8;
/*     */   public static final int UnmapNotify = 18;
/*     */   public static final int MapNotify = 19;
/*     */   public static final int Expose = 12;
/*     */   public static final int ConfigureNotify = 22;
/*     */   public static final int ClientMessage = 33;
/*     */   private final ByteBuffer event_buffer;
/*     */ 
/*     */   LinuxEvent()
/*     */   {
/*  62 */     this.event_buffer = createEventBuffer();
/*     */   }
/*     */   private static native ByteBuffer createEventBuffer();
/*     */ 
/*     */   public void copyFrom(LinuxEvent event) {
/*  67 */     int pos = this.event_buffer.position();
/*  68 */     int event_pos = event.event_buffer.position();
/*  69 */     this.event_buffer.put(event.event_buffer);
/*  70 */     this.event_buffer.position(pos);
/*  71 */     event.event_buffer.position(event_pos);
/*     */   }
/*     */ 
/*     */   public static native int getPending(long paramLong);
/*     */ 
/*     */   public void sendEvent(long display, long window, boolean propagate, long event_mask) {
/*  77 */     nSendEvent(this.event_buffer, display, window, propagate, event_mask);
/*     */   }
/*     */   private static native void nSendEvent(ByteBuffer paramByteBuffer, long paramLong1, long paramLong2, boolean paramBoolean, long paramLong3);
/*     */ 
/*     */   public boolean filterEvent(long window) {
/*  82 */     return nFilterEvent(this.event_buffer, window);
/*     */   }
/*     */   private static native boolean nFilterEvent(ByteBuffer paramByteBuffer, long paramLong);
/*     */ 
/*     */   public void nextEvent(long display) {
/*  87 */     nNextEvent(display, this.event_buffer);
/*     */   }
/*     */   private static native void nNextEvent(long paramLong, ByteBuffer paramByteBuffer);
/*     */ 
/*     */   public int getType() {
/*  92 */     return nGetType(this.event_buffer);
/*     */   }
/*     */   private static native int nGetType(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   public long getWindow() {
/*  97 */     return nGetWindow(this.event_buffer);
/*     */   }
/*     */   private static native long nGetWindow(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   public void setWindow(long window) {
/* 102 */     nSetWindow(this.event_buffer, window);
/*     */   }
/*     */ 
/*     */   private static native void nSetWindow(ByteBuffer paramByteBuffer, long paramLong);
/*     */ 
/*     */   public int getFocusMode()
/*     */   {
/* 109 */     return nGetFocusMode(this.event_buffer);
/*     */   }
/*     */   private static native int nGetFocusMode(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   public int getFocusDetail() {
/* 114 */     return nGetFocusDetail(this.event_buffer);
/*     */   }
/*     */ 
/*     */   private static native int nGetFocusDetail(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   public long getClientMessageType()
/*     */   {
/* 121 */     return nGetClientMessageType(this.event_buffer);
/*     */   }
/*     */   private static native long nGetClientMessageType(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   public int getClientData(int index) {
/* 126 */     return nGetClientData(this.event_buffer, index);
/*     */   }
/*     */   private static native int nGetClientData(ByteBuffer paramByteBuffer, int paramInt);
/*     */ 
/*     */   public int getClientFormat() {
/* 131 */     return nGetClientFormat(this.event_buffer);
/*     */   }
/*     */ 
/*     */   private static native int nGetClientFormat(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   public long getButtonTime()
/*     */   {
/* 138 */     return nGetButtonTime(this.event_buffer);
/*     */   }
/*     */   private static native long nGetButtonTime(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   public int getButtonState() {
/* 143 */     return nGetButtonState(this.event_buffer);
/*     */   }
/*     */   private static native int nGetButtonState(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   public int getButtonType() {
/* 148 */     return nGetButtonType(this.event_buffer);
/*     */   }
/*     */   private static native int nGetButtonType(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   public int getButtonButton() {
/* 153 */     return nGetButtonButton(this.event_buffer);
/*     */   }
/*     */   private static native int nGetButtonButton(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   public long getButtonRoot() {
/* 158 */     return nGetButtonRoot(this.event_buffer);
/*     */   }
/*     */   private static native long nGetButtonRoot(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   public int getButtonXRoot() {
/* 163 */     return nGetButtonXRoot(this.event_buffer);
/*     */   }
/*     */   private static native int nGetButtonXRoot(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   public int getButtonYRoot() {
/* 168 */     return nGetButtonYRoot(this.event_buffer);
/*     */   }
/*     */   private static native int nGetButtonYRoot(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   public int getButtonX() {
/* 173 */     return nGetButtonX(this.event_buffer);
/*     */   }
/*     */   private static native int nGetButtonX(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   public int getButtonY() {
/* 178 */     return nGetButtonY(this.event_buffer);
/*     */   }
/*     */ 
/*     */   private static native int nGetButtonY(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   public long getKeyAddress()
/*     */   {
/* 185 */     return nGetKeyAddress(this.event_buffer);
/*     */   }
/*     */   private static native long nGetKeyAddress(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   public long getKeyTime() {
/* 190 */     return nGetKeyTime(this.event_buffer);
/*     */   }
/*     */   private static native int nGetKeyTime(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   public int getKeyType() {
/* 195 */     return nGetKeyType(this.event_buffer);
/*     */   }
/*     */   private static native int nGetKeyType(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   public int getKeyKeyCode() {
/* 200 */     return nGetKeyKeyCode(this.event_buffer);
/*     */   }
/*     */   private static native int nGetKeyKeyCode(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   public int getKeyState() {
/* 205 */     return nGetKeyState(this.event_buffer);
/*     */   }
/*     */ 
/*     */   private static native int nGetKeyState(ByteBuffer paramByteBuffer);
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.LinuxEvent
 * JD-Core Version:    0.6.2
 */