/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.awt.Component;
/*   4:    */import java.awt.event.KeyEvent;
/*   5:    */import java.awt.event.KeyListener;
/*   6:    */import java.nio.ByteBuffer;
/*   7:    */
/*  43:    */final class KeyboardEventQueue
/*  44:    */  extends EventQueue
/*  45:    */  implements KeyListener
/*  46:    */{
/*  47: 47 */  private static final int[] KEY_MAP = new int[65535];
/*  48:    */  
/*  49: 49 */  private final byte[] key_states = new byte[256];
/*  50:    */  
/*  52: 52 */  private final ByteBuffer event = ByteBuffer.allocate(18);
/*  53:    */  
/*  54:    */  private final Component component;
/*  55:    */  private boolean has_deferred_event;
/*  56:    */  private long deferred_nanos;
/*  57:    */  private int deferred_key_code;
/*  58:    */  private int deferred_key_location;
/*  59:    */  private byte deferred_key_state;
/*  60:    */  private int deferred_character;
/*  61:    */  
/*  62:    */  static
/*  63:    */  {
/*  64: 64 */    KEY_MAP[48] = 11;
/*  65: 65 */    KEY_MAP[49] = 2;
/*  66: 66 */    KEY_MAP[50] = 3;
/*  67: 67 */    KEY_MAP[51] = 4;
/*  68: 68 */    KEY_MAP[52] = 5;
/*  69: 69 */    KEY_MAP[53] = 6;
/*  70: 70 */    KEY_MAP[54] = 7;
/*  71: 71 */    KEY_MAP[55] = 8;
/*  72: 72 */    KEY_MAP[56] = 9;
/*  73: 73 */    KEY_MAP[57] = 10;
/*  74: 74 */    KEY_MAP[65] = 30;
/*  75:    */    
/*  76: 76 */    KEY_MAP[107] = 78;
/*  77:    */    
/*  81: 81 */    KEY_MAP[65406] = 184;
/*  82:    */    
/*  84: 84 */    KEY_MAP[512] = 145;
/*  85: 85 */    KEY_MAP[66] = 48;
/*  86:    */    
/*  87: 87 */    KEY_MAP[92] = 43;
/*  88: 88 */    KEY_MAP[8] = 14;
/*  89:    */    
/*  91: 91 */    KEY_MAP[67] = 46;
/*  92:    */    
/*  93: 93 */    KEY_MAP[20] = 58;
/*  94: 94 */    KEY_MAP[514] = 144;
/*  95:    */    
/*  96: 96 */    KEY_MAP[93] = 27;
/*  97:    */    
/*  98: 98 */    KEY_MAP[513] = 146;
/*  99: 99 */    KEY_MAP[44] = 51;
/* 100:    */    
/* 102:102 */    KEY_MAP[28] = 121;
/* 103:    */    
/* 105:105 */    KEY_MAP[68] = 32;
/* 106:    */    
/* 122:122 */    KEY_MAP[110] = 83;
/* 123:123 */    KEY_MAP[127] = 211;
/* 124:124 */    KEY_MAP[111] = 181;
/* 125:    */    
/* 126:126 */    KEY_MAP[40] = 208;
/* 127:127 */    KEY_MAP[69] = 18;
/* 128:128 */    KEY_MAP[35] = 207;
/* 129:129 */    KEY_MAP[10] = 28;
/* 130:130 */    KEY_MAP[61] = 13;
/* 131:131 */    KEY_MAP[27] = 1;
/* 132:    */    
/* 134:134 */    KEY_MAP[70] = 33;
/* 135:135 */    KEY_MAP[112] = 59;
/* 136:136 */    KEY_MAP[121] = 68;
/* 137:137 */    KEY_MAP[122] = 87;
/* 138:138 */    KEY_MAP[123] = 88;
/* 139:139 */    KEY_MAP[61440] = 100;
/* 140:140 */    KEY_MAP[61441] = 101;
/* 141:141 */    KEY_MAP[61442] = 102;
/* 142:    */    
/* 146:146 */    KEY_MAP[113] = 60;
/* 147:    */    
/* 152:152 */    KEY_MAP[114] = 61;
/* 153:153 */    KEY_MAP[115] = 62;
/* 154:154 */    KEY_MAP[116] = 63;
/* 155:155 */    KEY_MAP[117] = 64;
/* 156:156 */    KEY_MAP[118] = 65;
/* 157:157 */    KEY_MAP[119] = 66;
/* 158:158 */    KEY_MAP[120] = 67;
/* 159:    */    
/* 162:162 */    KEY_MAP[71] = 34;
/* 163:    */    
/* 164:164 */    KEY_MAP[72] = 35;
/* 165:    */    
/* 168:168 */    KEY_MAP[36] = 199;
/* 169:169 */    KEY_MAP[73] = 23;
/* 170:    */    
/* 171:171 */    KEY_MAP[''] = 210;
/* 172:    */    
/* 173:173 */    KEY_MAP[74] = 36;
/* 174:    */    
/* 177:177 */    KEY_MAP[75] = 37;
/* 178:178 */    KEY_MAP[21] = 112;
/* 179:    */    
/* 180:180 */    KEY_MAP[25] = 148;
/* 181:    */    
/* 186:186 */    KEY_MAP[76] = 38;
/* 187:187 */    KEY_MAP[37] = 203;
/* 188:    */    
/* 190:190 */    KEY_MAP[77] = 50;
/* 191:    */    
/* 192:192 */    KEY_MAP[45] = 12;
/* 193:    */    
/* 194:194 */    KEY_MAP[106] = 55;
/* 195:195 */    KEY_MAP[78] = 49;
/* 196:    */    
/* 197:197 */    KEY_MAP[''] = 69;
/* 198:    */    
/* 199:199 */    KEY_MAP[96] = 82;
/* 200:200 */    KEY_MAP[97] = 79;
/* 201:201 */    KEY_MAP[98] = 80;
/* 202:202 */    KEY_MAP[99] = 81;
/* 203:203 */    KEY_MAP[100] = 75;
/* 204:204 */    KEY_MAP[101] = 76;
/* 205:205 */    KEY_MAP[102] = 77;
/* 206:206 */    KEY_MAP[103] = 71;
/* 207:207 */    KEY_MAP[104] = 72;
/* 208:208 */    KEY_MAP[105] = 73;
/* 209:209 */    KEY_MAP[79] = 24;
/* 210:210 */    KEY_MAP[91] = 26;
/* 211:211 */    KEY_MAP[80] = 25;
/* 212:212 */    KEY_MAP[34] = 209;
/* 213:213 */    KEY_MAP[33] = 201;
/* 214:    */    
/* 215:215 */    KEY_MAP[19] = 197;
/* 216:216 */    KEY_MAP[46] = 52;
/* 217:    */    
/* 221:221 */    KEY_MAP[81] = 16;
/* 222:    */    
/* 224:224 */    KEY_MAP[82] = 19;
/* 225:225 */    KEY_MAP[39] = 205;
/* 226:    */    
/* 228:228 */    KEY_MAP[83] = 31;
/* 229:229 */    KEY_MAP[''] = 70;
/* 230:230 */    KEY_MAP[59] = 39;
/* 231:231 */    KEY_MAP[108] = 83;
/* 232:    */    
/* 233:233 */    KEY_MAP[47] = 53;
/* 234:234 */    KEY_MAP[32] = 57;
/* 235:235 */    KEY_MAP[65480] = 149;
/* 236:236 */    KEY_MAP[109] = 74;
/* 237:237 */    KEY_MAP[84] = 20;
/* 238:238 */    KEY_MAP[9] = 15;
/* 239:239 */    KEY_MAP[85] = 22;
/* 240:    */    
/* 242:242 */    KEY_MAP[38] = 200;
/* 243:243 */    KEY_MAP[86] = 47;
/* 244:244 */    KEY_MAP[87] = 17;
/* 245:245 */    KEY_MAP[88] = 45;
/* 246:246 */    KEY_MAP[89] = 21;
/* 247:247 */    KEY_MAP[90] = 44;
/* 248:    */  }
/* 249:    */  
/* 250:    */  KeyboardEventQueue(Component component) {
/* 251:251 */    super(18);
/* 252:252 */    this.component = component;
/* 253:    */  }
/* 254:    */  
/* 255:    */  public void register() {
/* 256:256 */    this.component.addKeyListener(this);
/* 257:    */  }
/* 258:    */  
/* 266:    */  private void putKeyboardEvent(int key_code, byte state, int character, long nanos, boolean repeat)
/* 267:    */  {
/* 268:268 */    this.event.clear();
/* 269:269 */    this.event.putInt(key_code).put(state).putInt(character).putLong(nanos).put((byte)(repeat ? 1 : 0));
/* 270:270 */    this.event.flip();
/* 271:271 */    putEvent(this.event);
/* 272:    */  }
/* 273:    */  
/* 274:    */  public synchronized void poll(ByteBuffer key_down_buffer) {
/* 275:275 */    flushDeferredEvent();
/* 276:276 */    int old_position = key_down_buffer.position();
/* 277:277 */    key_down_buffer.put(this.key_states);
/* 278:278 */    key_down_buffer.position(old_position);
/* 279:    */  }
/* 280:    */  
/* 281:    */  public synchronized void copyEvents(ByteBuffer dest) {
/* 282:282 */    flushDeferredEvent();
/* 283:283 */    super.copyEvents(dest);
/* 284:    */  }
/* 285:    */  
/* 286:    */  private synchronized void handleKey(int key_code, int key_location, byte state, int character, long nanos) {
/* 287:287 */    if (character == 65535)
/* 288:288 */      character = 0;
/* 289:289 */    if (state == 1) {
/* 290:290 */      boolean repeat = false;
/* 291:291 */      if (this.has_deferred_event)
/* 292:292 */        if ((nanos == this.deferred_nanos) && (this.deferred_key_code == key_code) && (this.deferred_key_location == key_location))
/* 293:    */        {
/* 294:294 */          this.has_deferred_event = false;
/* 295:295 */          repeat = true;
/* 296:    */        } else {
/* 297:297 */          flushDeferredEvent();
/* 298:    */        }
/* 299:299 */      putKeyEvent(key_code, key_location, state, character, nanos, repeat);
/* 300:    */    } else {
/* 301:301 */      flushDeferredEvent();
/* 302:302 */      this.has_deferred_event = true;
/* 303:303 */      this.deferred_nanos = nanos;
/* 304:304 */      this.deferred_key_code = key_code;
/* 305:305 */      this.deferred_key_location = key_location;
/* 306:306 */      this.deferred_key_state = state;
/* 307:307 */      this.deferred_character = character;
/* 308:    */    }
/* 309:    */  }
/* 310:    */  
/* 311:    */  private void flushDeferredEvent() {
/* 312:312 */    if (this.has_deferred_event) {
/* 313:313 */      putKeyEvent(this.deferred_key_code, this.deferred_key_location, this.deferred_key_state, this.deferred_character, this.deferred_nanos, false);
/* 314:314 */      this.has_deferred_event = false;
/* 315:    */    }
/* 316:    */  }
/* 317:    */  
/* 318:    */  private void putKeyEvent(int key_code, int key_location, byte state, int character, long nanos, boolean repeat) {
/* 319:319 */    int key_code_mapped = getMappedKeyCode(key_code, key_location);
/* 320:    */    
/* 321:321 */    if (this.key_states[key_code_mapped] == state)
/* 322:322 */      repeat = true;
/* 323:323 */    this.key_states[key_code_mapped] = state;
/* 324:324 */    int key_int_char = character & 0xFFFF;
/* 325:325 */    putKeyboardEvent(key_code_mapped, state, key_int_char, nanos, repeat);
/* 326:    */  }
/* 327:    */  
/* 328:    */  private int getMappedKeyCode(int key_code, int position)
/* 329:    */  {
/* 330:330 */    switch (key_code) {
/* 331:    */    case 18: 
/* 332:332 */      if (position == 3) {
/* 333:333 */        return 184;
/* 334:    */      }
/* 335:335 */      return 56;
/* 336:    */    case 157: 
/* 337:337 */      if (position == 3) {
/* 338:338 */        return 220;
/* 339:    */      }
/* 340:340 */      return 219;
/* 341:    */    case 16: 
/* 342:342 */      if (position == 3) {
/* 343:343 */        return 54;
/* 344:    */      }
/* 345:345 */      return 42;
/* 346:    */    case 17: 
/* 347:347 */      if (position == 3) {
/* 348:348 */        return 157;
/* 349:    */      }
/* 350:350 */      return 29;
/* 351:    */    }
/* 352:352 */    return KEY_MAP[key_code];
/* 353:    */  }
/* 354:    */  
/* 355:    */  public void keyPressed(KeyEvent e)
/* 356:    */  {
/* 357:357 */    handleKey(e.getKeyCode(), e.getKeyLocation(), (byte)1, e.getKeyChar(), e.getWhen() * 1000000L);
/* 358:    */  }
/* 359:    */  
/* 360:    */  public void keyReleased(KeyEvent e) {
/* 361:361 */    handleKey(e.getKeyCode(), e.getKeyLocation(), (byte)0, 0, e.getWhen() * 1000000L);
/* 362:    */  }
/* 363:    */  
/* 364:    */  public void unregister() {}
/* 365:    */  
/* 366:    */  public void keyTyped(KeyEvent e) {}
/* 367:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.KeyboardEventQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */