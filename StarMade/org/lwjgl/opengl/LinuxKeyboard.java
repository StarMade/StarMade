/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.CharBuffer;
/*     */ import java.nio.charset.Charset;
/*     */ import java.nio.charset.CharsetDecoder;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.LWJGLUtil;
/*     */ 
/*     */ final class LinuxKeyboard
/*     */ {
/*     */   private static final int LockMapIndex = 1;
/*     */   private static final long NoSymbol = 0L;
/*     */   private static final long ShiftMask = 1L;
/*     */   private static final long LockMask = 2L;
/*     */   private static final int XLookupChars = 2;
/*     */   private static final int XLookupBoth = 4;
/*     */   private static final int KEYBOARD_BUFFER_SIZE = 50;
/*     */   private final long xim;
/*     */   private final long xic;
/*     */   private final int numlock_mask;
/*     */   private final int modeswitch_mask;
/*     */   private final int caps_lock_mask;
/*     */   private final int shift_lock_mask;
/*     */   private final ByteBuffer compose_status;
/*  67 */   private final byte[] key_down_buffer = new byte[256];
/*  68 */   private final EventQueue event_queue = new EventQueue(18);
/*     */ 
/*  70 */   private final ByteBuffer tmp_event = ByteBuffer.allocate(18);
/*  71 */   private final int[] temp_translation_buffer = new int[50];
/*  72 */   private final ByteBuffer native_translation_buffer = BufferUtils.createByteBuffer(50);
/*  73 */   private final CharsetDecoder utf8_decoder = Charset.forName("UTF-8").newDecoder();
/*  74 */   private final CharBuffer char_buffer = CharBuffer.allocate(50);
/*     */   private boolean has_deferred_event;
/*     */   private int deferred_keycode;
/*     */   private int deferred_event_keycode;
/*     */   private long deferred_nanos;
/*     */   private byte deferred_key_state;
/*     */ 
/*     */   LinuxKeyboard(long display, long window)
/*     */   {
/*  84 */     long modifier_map = getModifierMapping(display);
/*  85 */     int tmp_numlock_mask = 0;
/*  86 */     int tmp_modeswitch_mask = 0;
/*  87 */     int tmp_caps_lock_mask = 0;
/*  88 */     int tmp_shift_lock_mask = 0;
/*  89 */     if (modifier_map != 0L) {
/*  90 */       int max_keypermod = getMaxKeyPerMod(modifier_map);
/*     */ 
/*  93 */       for (int i = 0; i < 8; i++) {
/*  94 */         for (int j = 0; j < max_keypermod; j++) {
/*  95 */           int key_code = lookupModifierMap(modifier_map, i * max_keypermod + j);
/*  96 */           int key_sym = (int)keycodeToKeySym(display, key_code);
/*  97 */           int mask = 1 << i;
/*  98 */           switch (key_sym) {
/*     */           case 65407:
/* 100 */             tmp_numlock_mask |= mask;
/* 101 */             break;
/*     */           case 65406:
/* 103 */             tmp_modeswitch_mask |= mask;
/* 104 */             break;
/*     */           case 65509:
/* 106 */             if (i == 1) {
/* 107 */               tmp_caps_lock_mask = mask;
/* 108 */               tmp_shift_lock_mask = 0; } break;
/*     */           case 65510:
/* 112 */             if ((i == 1) && (tmp_caps_lock_mask == 0)) {
/* 113 */               tmp_shift_lock_mask = mask;
/*     */             }
/*     */             break;
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/* 120 */       freeModifierMapping(modifier_map);
/*     */     }
/* 122 */     this.numlock_mask = tmp_numlock_mask;
/* 123 */     this.modeswitch_mask = tmp_modeswitch_mask;
/* 124 */     this.caps_lock_mask = tmp_caps_lock_mask;
/* 125 */     this.shift_lock_mask = tmp_shift_lock_mask;
/* 126 */     setDetectableKeyRepeat(display, true);
/* 127 */     this.xim = openIM(display);
/* 128 */     if (this.xim != 0L) {
/* 129 */       this.xic = createIC(this.xim, window);
/* 130 */       if (this.xic != 0L)
/* 131 */         setupIMEventMask(display, window, this.xic);
/*     */       else
/* 133 */         destroy(display);
/*     */     }
/*     */     else {
/* 136 */       this.xic = 0L;
/*     */     }
/* 138 */     this.compose_status = allocateComposeStatus(); } 
/*     */   private static native long getModifierMapping(long paramLong);
/*     */ 
/*     */   private static native void freeModifierMapping(long paramLong);
/*     */ 
/*     */   private static native int getMaxKeyPerMod(long paramLong);
/*     */ 
/*     */   private static native int lookupModifierMap(long paramLong, int paramInt);
/*     */ 
/*     */   private static native long keycodeToKeySym(long paramLong, int paramInt);
/*     */ 
/*     */   private static native long openIM(long paramLong);
/*     */ 
/*     */   private static native long createIC(long paramLong1, long paramLong2);
/*     */ 
/*     */   private static native void setupIMEventMask(long paramLong1, long paramLong2, long paramLong3);
/*     */ 
/*     */   private static native ByteBuffer allocateComposeStatus();
/*     */ 
/* 152 */   private static void setDetectableKeyRepeat(long display, boolean enabled) { boolean success = nSetDetectableKeyRepeat(display, enabled);
/* 153 */     if (!success)
/* 154 */       LWJGLUtil.log("Failed to set detectable key repeat to " + enabled); }
/*     */ 
/*     */   private static native boolean nSetDetectableKeyRepeat(long paramLong, boolean paramBoolean);
/*     */ 
/*     */   public void destroy(long display) {
/* 159 */     if (this.xic != 0L)
/* 160 */       destroyIC(this.xic);
/* 161 */     if (this.xim != 0L)
/* 162 */       closeIM(this.xim);
/* 163 */     setDetectableKeyRepeat(display, false);
/*     */   }
/*     */   private static native void destroyIC(long paramLong);
/*     */ 
/*     */   private static native void closeIM(long paramLong);
/*     */ 
/* 169 */   public void read(ByteBuffer buffer) { flushDeferredEvent();
/* 170 */     this.event_queue.copyEvents(buffer); }
/*     */ 
/*     */   public void poll(ByteBuffer keyDownBuffer)
/*     */   {
/* 174 */     flushDeferredEvent();
/* 175 */     int old_position = keyDownBuffer.position();
/* 176 */     keyDownBuffer.put(this.key_down_buffer);
/* 177 */     keyDownBuffer.position(old_position);
/*     */   }
/*     */ 
/*     */   private void putKeyboardEvent(int keycode, byte state, int ch, long nanos, boolean repeat) {
/* 181 */     this.tmp_event.clear();
/* 182 */     this.tmp_event.putInt(keycode).put(state).putInt(ch).putLong(nanos).put((byte)(repeat ? 1 : 0));
/* 183 */     this.tmp_event.flip();
/* 184 */     this.event_queue.putEvent(this.tmp_event);
/*     */   }
/*     */ 
/*     */   private int lookupStringISO88591(long event_ptr, int[] translation_buffer)
/*     */   {
/* 190 */     int num_chars = lookupString(event_ptr, this.native_translation_buffer, this.compose_status);
/* 191 */     for (int i = 0; i < num_chars; i++) {
/* 192 */       translation_buffer[i] = (this.native_translation_buffer.get(i) & 0xFF);
/*     */     }
/* 194 */     return num_chars;
/*     */   }
/*     */   private static native int lookupString(long paramLong, ByteBuffer paramByteBuffer1, ByteBuffer paramByteBuffer2);
/*     */ 
/*     */   private int lookupStringUnicode(long event_ptr, int[] translation_buffer) {
/* 199 */     int status = utf8LookupString(this.xic, event_ptr, this.native_translation_buffer, this.native_translation_buffer.position(), this.native_translation_buffer.remaining());
/* 200 */     if ((status != 2) && (status != 4))
/* 201 */       return 0;
/* 202 */     this.native_translation_buffer.flip();
/* 203 */     this.utf8_decoder.decode(this.native_translation_buffer, this.char_buffer, true);
/* 204 */     this.native_translation_buffer.compact();
/* 205 */     this.char_buffer.flip();
/* 206 */     int i = 0;
/* 207 */     while ((this.char_buffer.hasRemaining()) && (i < translation_buffer.length)) {
/* 208 */       translation_buffer[(i++)] = this.char_buffer.get();
/*     */     }
/* 210 */     this.char_buffer.compact();
/* 211 */     return i;
/*     */   }
/*     */   private static native int utf8LookupString(long paramLong1, long paramLong2, ByteBuffer paramByteBuffer, int paramInt1, int paramInt2);
/*     */ 
/*     */   private int lookupString(long event_ptr, int[] translation_buffer) {
/* 216 */     if (this.xic != 0L) {
/* 217 */       return lookupStringUnicode(event_ptr, translation_buffer);
/*     */     }
/* 219 */     return lookupStringISO88591(event_ptr, translation_buffer);
/*     */   }
/*     */ 
/*     */   private void translateEvent(long event_ptr, int keycode, byte key_state, long nanos, boolean repeat)
/*     */   {
/* 226 */     int num_chars = lookupString(event_ptr, this.temp_translation_buffer);
/* 227 */     if (num_chars > 0) {
/* 228 */       int ch = this.temp_translation_buffer[0];
/* 229 */       putKeyboardEvent(keycode, key_state, ch, nanos, repeat);
/* 230 */       for (int i = 1; i < num_chars; i++) {
/* 231 */         ch = this.temp_translation_buffer[i];
/* 232 */         putKeyboardEvent(0, (byte)0, ch, nanos, repeat);
/*     */       }
/*     */     }
/* 235 */     putKeyboardEvent(keycode, key_state, 0, nanos, repeat);
/*     */   }
/*     */ 
/*     */   private static boolean isKeypadKeysym(long keysym)
/*     */   {
/* 240 */     return ((65408L <= keysym) && (keysym <= 65469L)) || ((285212672L <= keysym) && (keysym <= 285278207L));
/*     */   }
/*     */ 
/*     */   private static boolean isNoSymbolOrVendorSpecific(long keysym)
/*     */   {
/* 245 */     return (keysym == 0L) || ((keysym & 0x10000000) != 0L);
/*     */   }
/*     */ 
/*     */   private static long getKeySym(long event_ptr, int group, int index) {
/* 249 */     long keysym = lookupKeysym(event_ptr, group * 2 + index);
/* 250 */     if ((isNoSymbolOrVendorSpecific(keysym)) && (index == 1)) {
/* 251 */       keysym = lookupKeysym(event_ptr, group * 2 + 0);
/*     */     }
/* 253 */     if ((isNoSymbolOrVendorSpecific(keysym)) && (group == 1))
/* 254 */       keysym = getKeySym(event_ptr, 0, index);
/* 255 */     return keysym;
/*     */   }
/*     */ 
/*     */   private static native long lookupKeysym(long paramLong, int paramInt);
/*     */ 
/*     */   private static native long toUpper(long paramLong);
/*     */ 
/*     */   private long mapEventToKeySym(long event_ptr, int event_state)
/*     */   {
/*     */     int group;
/*     */     int group;
/* 263 */     if ((event_state & this.modeswitch_mask) != 0)
/* 264 */       group = 1;
/*     */     else
/* 266 */       group = 0;
/*     */     long keysym;
/* 267 */     if (((event_state & this.numlock_mask) != 0) && (isKeypadKeysym(keysym = getKeySym(event_ptr, group, 1)))) {
/* 268 */       if ((event_state & (1L | this.shift_lock_mask)) != 0L) {
/* 269 */         return getKeySym(event_ptr, group, 0);
/*     */       }
/* 271 */       return keysym;
/*     */     }
/* 273 */     if ((event_state & 0x3) == 0L)
/* 274 */       return getKeySym(event_ptr, group, 0);
/* 275 */     if ((event_state & 1L) == 0L) {
/* 276 */       long keysym = getKeySym(event_ptr, group, 0);
/* 277 */       if ((event_state & this.caps_lock_mask) != 0)
/* 278 */         keysym = toUpper(keysym);
/* 279 */       return keysym;
/*     */     }
/* 281 */     long keysym = getKeySym(event_ptr, group, 1);
/* 282 */     if ((event_state & this.caps_lock_mask) != 0)
/* 283 */       keysym = toUpper(keysym);
/* 284 */     return keysym;
/*     */   }
/*     */ 
/*     */   private int getKeycode(long event_ptr, int event_state)
/*     */   {
/* 289 */     long keysym = mapEventToKeySym(event_ptr, event_state);
/* 290 */     int keycode = LinuxKeycodes.mapKeySymToLWJGLKeyCode(keysym);
/* 291 */     if (keycode == 0)
/*     */     {
/* 293 */       keysym = lookupKeysym(event_ptr, 0);
/* 294 */       keycode = LinuxKeycodes.mapKeySymToLWJGLKeyCode(keysym);
/*     */     }
/* 296 */     return keycode;
/*     */   }
/*     */ 
/*     */   private byte getKeyState(int event_type) {
/* 300 */     switch (event_type) {
/*     */     case 2:
/* 302 */       return 1;
/*     */     case 3:
/* 304 */       return 0;
/*     */     }
/* 306 */     throw new IllegalArgumentException("Unknown event_type: " + event_type);
/*     */   }
/*     */ 
/*     */   private void handleKeyEvent(long event_ptr, long millis, int event_type, int event_keycode, int event_state)
/*     */   {
/* 311 */     int keycode = getKeycode(event_ptr, event_state);
/* 312 */     byte key_state = getKeyState(event_type);
/* 313 */     boolean repeat = key_state == this.key_down_buffer[keycode];
/* 314 */     this.key_down_buffer[keycode] = key_state;
/* 315 */     long nanos = millis * 1000000L;
/* 316 */     if (event_type == 2) {
/* 317 */       if (this.has_deferred_event)
/* 318 */         if ((nanos == this.deferred_nanos) && (event_keycode == this.deferred_event_keycode)) {
/* 319 */           this.has_deferred_event = false;
/* 320 */           repeat = true;
/*     */         } else {
/* 322 */           flushDeferredEvent();
/*     */         }
/* 324 */       translateEvent(event_ptr, keycode, key_state, nanos, repeat);
/*     */     } else {
/* 326 */       flushDeferredEvent();
/* 327 */       this.has_deferred_event = true;
/* 328 */       this.deferred_keycode = keycode;
/* 329 */       this.deferred_event_keycode = event_keycode;
/* 330 */       this.deferred_nanos = nanos;
/* 331 */       this.deferred_key_state = key_state;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void flushDeferredEvent() {
/* 336 */     if (this.has_deferred_event) {
/* 337 */       putKeyboardEvent(this.deferred_keycode, this.deferred_key_state, 0, this.deferred_nanos, false);
/* 338 */       this.has_deferred_event = false;
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean filterEvent(LinuxEvent event) {
/* 343 */     switch (event.getType()) {
/*     */     case 2:
/*     */     case 3:
/* 346 */       handleKeyEvent(event.getKeyAddress(), event.getKeyTime(), event.getKeyType(), event.getKeyKeyCode(), event.getKeyState());
/* 347 */       return true;
/*     */     }
/*     */ 
/* 351 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.LinuxKeyboard
 * JD-Core Version:    0.6.2
 */