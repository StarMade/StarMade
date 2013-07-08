/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.CharBuffer;
/*   5:    */import java.nio.charset.Charset;
/*   6:    */import java.nio.charset.CharsetDecoder;
/*   7:    */import org.lwjgl.BufferUtils;
/*   8:    */import org.lwjgl.LWJGLUtil;
/*   9:    */
/*  51:    */final class LinuxKeyboard
/*  52:    */{
/*  53:    */  private static final int LockMapIndex = 1;
/*  54:    */  private static final long NoSymbol = 0L;
/*  55:    */  private static final long ShiftMask = 1L;
/*  56:    */  private static final long LockMask = 2L;
/*  57:    */  private static final int XLookupChars = 2;
/*  58:    */  private static final int XLookupBoth = 4;
/*  59:    */  private static final int KEYBOARD_BUFFER_SIZE = 50;
/*  60:    */  private final long xim;
/*  61:    */  private final long xic;
/*  62:    */  private final int numlock_mask;
/*  63:    */  private final int modeswitch_mask;
/*  64:    */  private final int caps_lock_mask;
/*  65:    */  private final int shift_lock_mask;
/*  66:    */  private final ByteBuffer compose_status;
/*  67: 67 */  private final byte[] key_down_buffer = new byte[256];
/*  68: 68 */  private final EventQueue event_queue = new EventQueue(18);
/*  69:    */  
/*  70: 70 */  private final ByteBuffer tmp_event = ByteBuffer.allocate(18);
/*  71: 71 */  private final int[] temp_translation_buffer = new int[50];
/*  72: 72 */  private final ByteBuffer native_translation_buffer = BufferUtils.createByteBuffer(50);
/*  73: 73 */  private final CharsetDecoder utf8_decoder = Charset.forName("UTF-8").newDecoder();
/*  74: 74 */  private final CharBuffer char_buffer = CharBuffer.allocate(50);
/*  75:    */  
/*  76:    */  private boolean has_deferred_event;
/*  77:    */  private int deferred_keycode;
/*  78:    */  private int deferred_event_keycode;
/*  79:    */  private long deferred_nanos;
/*  80:    */  private byte deferred_key_state;
/*  81:    */  
/*  82:    */  LinuxKeyboard(long display, long window)
/*  83:    */  {
/*  84: 84 */    long modifier_map = getModifierMapping(display);
/*  85: 85 */    int tmp_numlock_mask = 0;
/*  86: 86 */    int tmp_modeswitch_mask = 0;
/*  87: 87 */    int tmp_caps_lock_mask = 0;
/*  88: 88 */    int tmp_shift_lock_mask = 0;
/*  89: 89 */    if (modifier_map != 0L) {
/*  90: 90 */      int max_keypermod = getMaxKeyPerMod(modifier_map);
/*  91:    */      
/*  93: 93 */      for (int i = 0; i < 8; i++) {
/*  94: 94 */        for (int j = 0; j < max_keypermod; j++) {
/*  95: 95 */          int key_code = lookupModifierMap(modifier_map, i * max_keypermod + j);
/*  96: 96 */          int key_sym = (int)keycodeToKeySym(display, key_code);
/*  97: 97 */          int mask = 1 << i;
/*  98: 98 */          switch (key_sym) {
/*  99:    */          case 65407: 
/* 100:100 */            tmp_numlock_mask |= mask;
/* 101:101 */            break;
/* 102:    */          case 65406: 
/* 103:103 */            tmp_modeswitch_mask |= mask;
/* 104:104 */            break;
/* 105:    */          case 65509: 
/* 106:106 */            if (i == 1) {
/* 107:107 */              tmp_caps_lock_mask = mask;
/* 108:108 */              tmp_shift_lock_mask = 0; } break;
/* 109:    */          
/* 111:    */          case 65510: 
/* 112:112 */            if ((i == 1) && (tmp_caps_lock_mask == 0)) {
/* 113:113 */              tmp_shift_lock_mask = mask;
/* 114:    */            }
/* 115:    */            break;
/* 116:    */          }
/* 117:    */          
/* 118:    */        }
/* 119:    */      }
/* 120:120 */      freeModifierMapping(modifier_map);
/* 121:    */    }
/* 122:122 */    this.numlock_mask = tmp_numlock_mask;
/* 123:123 */    this.modeswitch_mask = tmp_modeswitch_mask;
/* 124:124 */    this.caps_lock_mask = tmp_caps_lock_mask;
/* 125:125 */    this.shift_lock_mask = tmp_shift_lock_mask;
/* 126:126 */    setDetectableKeyRepeat(display, true);
/* 127:127 */    this.xim = openIM(display);
/* 128:128 */    if (this.xim != 0L) {
/* 129:129 */      this.xic = createIC(this.xim, window);
/* 130:130 */      if (this.xic != 0L) {
/* 131:131 */        setupIMEventMask(display, window, this.xic);
/* 132:    */      } else {
/* 133:133 */        destroy(display);
/* 134:    */      }
/* 135:    */    } else {
/* 136:136 */      this.xic = 0L;
/* 137:    */    }
/* 138:138 */    this.compose_status = allocateComposeStatus(); }
/* 139:    */  
/* 140:    */  private static native long getModifierMapping(long paramLong);
/* 141:    */  
/* 142:    */  private static native void freeModifierMapping(long paramLong);
/* 143:    */  
/* 144:    */  private static native int getMaxKeyPerMod(long paramLong);
/* 145:    */  
/* 146:    */  private static native int lookupModifierMap(long paramLong, int paramInt);
/* 147:    */  private static native long keycodeToKeySym(long paramLong, int paramInt);
/* 148:    */  private static native long openIM(long paramLong);
/* 149:    */  private static native long createIC(long paramLong1, long paramLong2);
/* 150:    */  private static native void setupIMEventMask(long paramLong1, long paramLong2, long paramLong3);
/* 151:    */  private static native ByteBuffer allocateComposeStatus();
/* 152:152 */  private static void setDetectableKeyRepeat(long display, boolean enabled) { boolean success = nSetDetectableKeyRepeat(display, enabled);
/* 153:153 */    if (!success)
/* 154:154 */      LWJGLUtil.log("Failed to set detectable key repeat to " + enabled);
/* 155:    */  }
/* 156:    */  
/* 157:    */  private static native boolean nSetDetectableKeyRepeat(long paramLong, boolean paramBoolean);
/* 158:    */  
/* 159:159 */  public void destroy(long display) { if (this.xic != 0L)
/* 160:160 */      destroyIC(this.xic);
/* 161:161 */    if (this.xim != 0L)
/* 162:162 */      closeIM(this.xim);
/* 163:163 */    setDetectableKeyRepeat(display, false); }
/* 164:    */  
/* 165:    */  private static native void destroyIC(long paramLong);
/* 166:    */  
/* 167:    */  private static native void closeIM(long paramLong);
/* 168:    */  
/* 169:169 */  public void read(ByteBuffer buffer) { flushDeferredEvent();
/* 170:170 */    this.event_queue.copyEvents(buffer);
/* 171:    */  }
/* 172:    */  
/* 173:    */  public void poll(ByteBuffer keyDownBuffer) {
/* 174:174 */    flushDeferredEvent();
/* 175:175 */    int old_position = keyDownBuffer.position();
/* 176:176 */    keyDownBuffer.put(this.key_down_buffer);
/* 177:177 */    keyDownBuffer.position(old_position);
/* 178:    */  }
/* 179:    */  
/* 180:    */  private void putKeyboardEvent(int keycode, byte state, int ch, long nanos, boolean repeat) {
/* 181:181 */    this.tmp_event.clear();
/* 182:182 */    this.tmp_event.putInt(keycode).put(state).putInt(ch).putLong(nanos).put((byte)(repeat ? 1 : 0));
/* 183:183 */    this.tmp_event.flip();
/* 184:184 */    this.event_queue.putEvent(this.tmp_event);
/* 185:    */  }
/* 186:    */  
/* 188:    */  private int lookupStringISO88591(long event_ptr, int[] translation_buffer)
/* 189:    */  {
/* 190:190 */    int num_chars = lookupString(event_ptr, this.native_translation_buffer, this.compose_status);
/* 191:191 */    for (int i = 0; i < num_chars; i++) {
/* 192:192 */      translation_buffer[i] = (this.native_translation_buffer.get(i) & 0xFF);
/* 193:    */    }
/* 194:194 */    return num_chars;
/* 195:    */  }
/* 196:    */  
/* 197:    */  private static native int lookupString(long paramLong, ByteBuffer paramByteBuffer1, ByteBuffer paramByteBuffer2);
/* 198:    */  
/* 199:199 */  private int lookupStringUnicode(long event_ptr, int[] translation_buffer) { int status = utf8LookupString(this.xic, event_ptr, this.native_translation_buffer, this.native_translation_buffer.position(), this.native_translation_buffer.remaining());
/* 200:200 */    if ((status != 2) && (status != 4))
/* 201:201 */      return 0;
/* 202:202 */    this.native_translation_buffer.flip();
/* 203:203 */    this.utf8_decoder.decode(this.native_translation_buffer, this.char_buffer, true);
/* 204:204 */    this.native_translation_buffer.compact();
/* 205:205 */    this.char_buffer.flip();
/* 206:206 */    int i = 0;
/* 207:207 */    while ((this.char_buffer.hasRemaining()) && (i < translation_buffer.length)) {
/* 208:208 */      translation_buffer[(i++)] = this.char_buffer.get();
/* 209:    */    }
/* 210:210 */    this.char_buffer.compact();
/* 211:211 */    return i;
/* 212:    */  }
/* 213:    */  
/* 214:    */  private static native int utf8LookupString(long paramLong1, long paramLong2, ByteBuffer paramByteBuffer, int paramInt1, int paramInt2);
/* 215:    */  
/* 216:216 */  private int lookupString(long event_ptr, int[] translation_buffer) { if (this.xic != 0L) {
/* 217:217 */      return lookupStringUnicode(event_ptr, translation_buffer);
/* 218:    */    }
/* 219:219 */    return lookupStringISO88591(event_ptr, translation_buffer);
/* 220:    */  }
/* 221:    */  
/* 224:    */  private void translateEvent(long event_ptr, int keycode, byte key_state, long nanos, boolean repeat)
/* 225:    */  {
/* 226:226 */    int num_chars = lookupString(event_ptr, this.temp_translation_buffer);
/* 227:227 */    if (num_chars > 0) {
/* 228:228 */      int ch = this.temp_translation_buffer[0];
/* 229:229 */      putKeyboardEvent(keycode, key_state, ch, nanos, repeat);
/* 230:230 */      for (int i = 1; i < num_chars; i++) {
/* 231:231 */        ch = this.temp_translation_buffer[i];
/* 232:232 */        putKeyboardEvent(0, (byte)0, ch, nanos, repeat);
/* 233:    */      }
/* 234:    */    }
/* 235:235 */    putKeyboardEvent(keycode, key_state, 0, nanos, repeat);
/* 236:    */  }
/* 237:    */  
/* 238:    */  private static boolean isKeypadKeysym(long keysym)
/* 239:    */  {
/* 240:240 */    return ((65408L <= keysym) && (keysym <= 65469L)) || ((285212672L <= keysym) && (keysym <= 285278207L));
/* 241:    */  }
/* 242:    */  
/* 243:    */  private static boolean isNoSymbolOrVendorSpecific(long keysym)
/* 244:    */  {
/* 245:245 */    return (keysym == 0L) || ((keysym & 0x10000000) != 0L);
/* 246:    */  }
/* 247:    */  
/* 248:    */  private static long getKeySym(long event_ptr, int group, int index) {
/* 249:249 */    long keysym = lookupKeysym(event_ptr, group * 2 + index);
/* 250:250 */    if ((isNoSymbolOrVendorSpecific(keysym)) && (index == 1)) {
/* 251:251 */      keysym = lookupKeysym(event_ptr, group * 2 + 0);
/* 252:    */    }
/* 253:253 */    if ((isNoSymbolOrVendorSpecific(keysym)) && (group == 1))
/* 254:254 */      keysym = getKeySym(event_ptr, 0, index);
/* 255:255 */    return keysym; }
/* 256:    */  
/* 257:    */  private static native long lookupKeysym(long paramLong, int paramInt);
/* 258:    */  
/* 259:    */  private static native long toUpper(long paramLong);
/* 260:    */  
/* 261:    */  private long mapEventToKeySym(long event_ptr, int event_state) { int group;
/* 262:    */    int group;
/* 263:263 */    if ((event_state & this.modeswitch_mask) != 0) {
/* 264:264 */      group = 1;
/* 265:    */    } else
/* 266:266 */      group = 0;
/* 267:267 */    long keysym; if (((event_state & this.numlock_mask) != 0) && (isKeypadKeysym(keysym = getKeySym(event_ptr, group, 1)))) {
/* 268:268 */      if ((event_state & (1L | this.shift_lock_mask)) != 0L) {
/* 269:269 */        return getKeySym(event_ptr, group, 0);
/* 270:    */      }
/* 271:271 */      return keysym;
/* 272:    */    }
/* 273:273 */    if ((event_state & 0x3) == 0L)
/* 274:274 */      return getKeySym(event_ptr, group, 0);
/* 275:275 */    if ((event_state & 1L) == 0L) {
/* 276:276 */      long keysym = getKeySym(event_ptr, group, 0);
/* 277:277 */      if ((event_state & this.caps_lock_mask) != 0)
/* 278:278 */        keysym = toUpper(keysym);
/* 279:279 */      return keysym;
/* 280:    */    }
/* 281:281 */    long keysym = getKeySym(event_ptr, group, 1);
/* 282:282 */    if ((event_state & this.caps_lock_mask) != 0)
/* 283:283 */      keysym = toUpper(keysym);
/* 284:284 */    return keysym;
/* 285:    */  }
/* 286:    */  
/* 287:    */  private int getKeycode(long event_ptr, int event_state)
/* 288:    */  {
/* 289:289 */    long keysym = mapEventToKeySym(event_ptr, event_state);
/* 290:290 */    int keycode = LinuxKeycodes.mapKeySymToLWJGLKeyCode(keysym);
/* 291:291 */    if (keycode == 0)
/* 292:    */    {
/* 293:293 */      keysym = lookupKeysym(event_ptr, 0);
/* 294:294 */      keycode = LinuxKeycodes.mapKeySymToLWJGLKeyCode(keysym);
/* 295:    */    }
/* 296:296 */    return keycode;
/* 297:    */  }
/* 298:    */  
/* 299:    */  private byte getKeyState(int event_type) {
/* 300:300 */    switch (event_type) {
/* 301:    */    case 2: 
/* 302:302 */      return 1;
/* 303:    */    case 3: 
/* 304:304 */      return 0;
/* 305:    */    }
/* 306:306 */    throw new IllegalArgumentException("Unknown event_type: " + event_type);
/* 307:    */  }
/* 308:    */  
/* 309:    */  private void handleKeyEvent(long event_ptr, long millis, int event_type, int event_keycode, int event_state)
/* 310:    */  {
/* 311:311 */    int keycode = getKeycode(event_ptr, event_state);
/* 312:312 */    byte key_state = getKeyState(event_type);
/* 313:313 */    boolean repeat = key_state == this.key_down_buffer[keycode];
/* 314:314 */    this.key_down_buffer[keycode] = key_state;
/* 315:315 */    long nanos = millis * 1000000L;
/* 316:316 */    if (event_type == 2) {
/* 317:317 */      if (this.has_deferred_event)
/* 318:318 */        if ((nanos == this.deferred_nanos) && (event_keycode == this.deferred_event_keycode)) {
/* 319:319 */          this.has_deferred_event = false;
/* 320:320 */          repeat = true;
/* 321:    */        } else {
/* 322:322 */          flushDeferredEvent();
/* 323:    */        }
/* 324:324 */      translateEvent(event_ptr, keycode, key_state, nanos, repeat);
/* 325:    */    } else {
/* 326:326 */      flushDeferredEvent();
/* 327:327 */      this.has_deferred_event = true;
/* 328:328 */      this.deferred_keycode = keycode;
/* 329:329 */      this.deferred_event_keycode = event_keycode;
/* 330:330 */      this.deferred_nanos = nanos;
/* 331:331 */      this.deferred_key_state = key_state;
/* 332:    */    }
/* 333:    */  }
/* 334:    */  
/* 335:    */  private void flushDeferredEvent() {
/* 336:336 */    if (this.has_deferred_event) {
/* 337:337 */      putKeyboardEvent(this.deferred_keycode, this.deferred_key_state, 0, this.deferred_nanos, false);
/* 338:338 */      this.has_deferred_event = false;
/* 339:    */    }
/* 340:    */  }
/* 341:    */  
/* 342:    */  public boolean filterEvent(LinuxEvent event) {
/* 343:343 */    switch (event.getType()) {
/* 344:    */    case 2: 
/* 345:    */    case 3: 
/* 346:346 */      handleKeyEvent(event.getKeyAddress(), event.getKeyTime(), event.getKeyType(), event.getKeyKeyCode(), event.getKeyState());
/* 347:347 */      return true;
/* 348:    */    }
/* 349:    */    
/* 350:    */    
/* 351:351 */    return false;
/* 352:    */  }
/* 353:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.LinuxKeyboard
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */