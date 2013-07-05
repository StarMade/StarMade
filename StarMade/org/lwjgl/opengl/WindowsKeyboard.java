/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.CharBuffer;
/*     */ import org.lwjgl.LWJGLException;
/*     */ 
/*     */ final class WindowsKeyboard
/*     */ {
/*     */   private static final int MAPVK_VK_TO_VSC = 0;
/*     */   private static final int BUFFER_SIZE = 50;
/*     */   private final long hwnd;
/*  51 */   private final byte[] key_down_buffer = new byte[256];
/*  52 */   private final byte[] virt_key_down_buffer = new byte[256];
/*  53 */   private final EventQueue event_queue = new EventQueue(18);
/*  54 */   private final ByteBuffer tmp_event = ByteBuffer.allocate(18);
/*     */   private boolean grabbed;
/*     */   private boolean has_retained_event;
/*     */   private int retained_key_code;
/*     */   private byte retained_state;
/*     */   private int retained_char;
/*     */   private long retained_millis;
/*     */   private boolean retained_repeat;
/*     */ 
/*     */   WindowsKeyboard(long hwnd)
/*     */     throws LWJGLException
/*     */   {
/*  66 */     this.hwnd = hwnd;
/*     */   }
/*     */   private static native boolean isWindowsNT();
/*     */ 
/*     */   public void destroy() {
/*     */   }
/*     */ 
/*     */   boolean isKeyDown(int lwjgl_keycode) {
/*  74 */     return this.key_down_buffer[lwjgl_keycode] == 1;
/*     */   }
/*     */ 
/*     */   public void grab(boolean grab) {
/*  78 */     if (grab) {
/*  79 */       if (!this.grabbed) {
/*  80 */         this.grabbed = true;
/*     */       }
/*     */     }
/*  83 */     else if (this.grabbed)
/*  84 */       this.grabbed = false;
/*     */   }
/*     */ 
/*     */   public void poll(ByteBuffer keyDownBuffer)
/*     */   {
/*  90 */     int old_position = keyDownBuffer.position();
/*  91 */     keyDownBuffer.put(this.key_down_buffer);
/*  92 */     keyDownBuffer.position(old_position); } 
/*     */   private static native int MapVirtualKey(int paramInt1, int paramInt2);
/*     */ 
/*     */   private static native int ToUnicode(int paramInt1, int paramInt2, ByteBuffer paramByteBuffer, CharBuffer paramCharBuffer, int paramInt3, int paramInt4);
/*     */ 
/*     */   private static native int ToAscii(int paramInt1, int paramInt2, ByteBuffer paramByteBuffer1, ByteBuffer paramByteBuffer2, int paramInt3);
/*     */ 
/*     */   private static native int GetKeyboardState(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   private static native short GetKeyState(int paramInt);
/*     */ 
/*     */   private static native short GetAsyncKeyState(int paramInt);
/*     */ 
/* 103 */   private void putEvent(int keycode, byte state, int ch, long millis, boolean repeat) { this.tmp_event.clear();
/* 104 */     this.tmp_event.putInt(keycode).put(state).putInt(ch).putLong(millis * 1000000L).put((byte)(repeat ? 1 : 0));
/* 105 */     this.tmp_event.flip();
/* 106 */     this.event_queue.putEvent(this.tmp_event); }
/*     */ 
/*     */   private boolean checkShiftKey(int virt_key, byte state)
/*     */   {
/* 110 */     int key_state = GetKeyState(virt_key) >>> 15 & 0x1;
/* 111 */     int lwjgl_code = WindowsKeycodes.mapVirtualKeyToLWJGLCode(virt_key);
/* 112 */     return (this.key_down_buffer[lwjgl_code] == 1 - state) && (key_state == state);
/*     */   }
/*     */ 
/*     */   private int translateShift(int scan_code, byte state) {
/* 116 */     if (checkShiftKey(160, state))
/* 117 */       return 160;
/* 118 */     if (checkShiftKey(161, state)) {
/* 119 */       return 161;
/*     */     }
/* 121 */     if (scan_code == 42) {
/* 122 */       return 160;
/*     */     }
/* 124 */     if (scan_code == 54) {
/* 125 */       return 161;
/*     */     }
/* 127 */     return 160;
/*     */   }
/*     */ 
/*     */   private int translateExtended(int virt_key, int scan_code, byte state, boolean extended)
/*     */   {
/* 133 */     switch (virt_key) {
/*     */     case 16:
/* 135 */       return translateShift(scan_code, state);
/*     */     case 17:
/* 137 */       return extended ? 163 : 162;
/*     */     case 18:
/* 139 */       return extended ? 165 : 164;
/*     */     }
/* 141 */     return virt_key;
/*     */   }
/*     */ 
/*     */   private void flushRetained()
/*     */   {
/* 146 */     if (this.has_retained_event) {
/* 147 */       this.has_retained_event = false;
/* 148 */       putEvent(this.retained_key_code, this.retained_state, this.retained_char, this.retained_millis, this.retained_repeat);
/*     */     }
/*     */   }
/*     */ 
/*     */   private static boolean isKeyPressed(int state) {
/* 153 */     return (state & 0x1) == 1;
/*     */   }
/*     */ 
/*     */   public void handleKey(int virt_key, int scan_code, boolean extended, byte event_state, long millis, boolean repeat) {
/* 157 */     virt_key = translateExtended(virt_key, scan_code, event_state, extended);
/* 158 */     if ((!repeat) && (isKeyPressed(event_state) == isKeyPressed(this.virt_key_down_buffer[virt_key]))) {
/* 159 */       return;
/*     */     }
/* 161 */     flushRetained();
/* 162 */     this.has_retained_event = true;
/* 163 */     int keycode = WindowsKeycodes.mapVirtualKeyToLWJGLCode(virt_key);
/* 164 */     if (keycode < this.key_down_buffer.length) {
/* 165 */       this.key_down_buffer[keycode] = event_state;
/* 166 */       this.virt_key_down_buffer[virt_key] = event_state;
/*     */     }
/* 168 */     this.retained_key_code = keycode;
/* 169 */     this.retained_state = event_state;
/* 170 */     this.retained_millis = millis;
/* 171 */     this.retained_char = 0;
/* 172 */     this.retained_repeat = repeat;
/*     */   }
/*     */ 
/*     */   public void fireLostKeyEvents()
/*     */   {
/* 177 */     for (int i = 0; i < this.virt_key_down_buffer.length; i++)
/* 178 */       if ((isKeyPressed(this.virt_key_down_buffer[i])) && ((GetAsyncKeyState(i) & 0x8000) == 0))
/* 179 */         handleKey(i, 0, false, (byte)0, System.currentTimeMillis(), false);
/*     */   }
/*     */ 
/*     */   public void handleChar(int event_char, long millis, boolean repeat)
/*     */   {
/* 184 */     if ((this.has_retained_event) && (this.retained_char != 0))
/* 185 */       flushRetained();
/* 186 */     if (!this.has_retained_event)
/* 187 */       putEvent(0, (byte)0, event_char, millis, repeat);
/*     */     else
/* 189 */       this.retained_char = event_char;
/*     */   }
/*     */ 
/*     */   public void read(ByteBuffer buffer) {
/* 193 */     flushRetained();
/* 194 */     this.event_queue.copyEvents(buffer);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.WindowsKeyboard
 * JD-Core Version:    0.6.2
 */