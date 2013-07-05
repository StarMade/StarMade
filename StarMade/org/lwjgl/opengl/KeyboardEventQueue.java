/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.nio.ByteBuffer;
/*     */ 
/*     */ final class KeyboardEventQueue extends EventQueue
/*     */   implements KeyListener
/*     */ {
/*  47 */   private static final int[] KEY_MAP = new int[65535];
/*     */ 
/*  49 */   private final byte[] key_states = new byte[256];
/*     */ 
/*  52 */   private final ByteBuffer event = ByteBuffer.allocate(18);
/*     */   private final Component component;
/*     */   private boolean has_deferred_event;
/*     */   private long deferred_nanos;
/*     */   private int deferred_key_code;
/*     */   private int deferred_key_location;
/*     */   private byte deferred_key_state;
/*     */   private int deferred_character;
/*     */ 
/*     */   KeyboardEventQueue(Component component)
/*     */   {
/* 251 */     super(18);
/* 252 */     this.component = component;
/*     */   }
/*     */ 
/*     */   public void register() {
/* 256 */     this.component.addKeyListener(this);
/*     */   }
/*     */ 
/*     */   public void unregister()
/*     */   {
/*     */   }
/*     */ 
/*     */   private void putKeyboardEvent(int key_code, byte state, int character, long nanos, boolean repeat)
/*     */   {
/* 268 */     this.event.clear();
/* 269 */     this.event.putInt(key_code).put(state).putInt(character).putLong(nanos).put((byte)(repeat ? 1 : 0));
/* 270 */     this.event.flip();
/* 271 */     putEvent(this.event);
/*     */   }
/*     */ 
/*     */   public synchronized void poll(ByteBuffer key_down_buffer) {
/* 275 */     flushDeferredEvent();
/* 276 */     int old_position = key_down_buffer.position();
/* 277 */     key_down_buffer.put(this.key_states);
/* 278 */     key_down_buffer.position(old_position);
/*     */   }
/*     */ 
/*     */   public synchronized void copyEvents(ByteBuffer dest) {
/* 282 */     flushDeferredEvent();
/* 283 */     super.copyEvents(dest);
/*     */   }
/*     */ 
/*     */   private synchronized void handleKey(int key_code, int key_location, byte state, int character, long nanos) {
/* 287 */     if (character == 65535)
/* 288 */       character = 0;
/* 289 */     if (state == 1) {
/* 290 */       boolean repeat = false;
/* 291 */       if (this.has_deferred_event)
/* 292 */         if ((nanos == this.deferred_nanos) && (this.deferred_key_code == key_code) && (this.deferred_key_location == key_location))
/*     */         {
/* 294 */           this.has_deferred_event = false;
/* 295 */           repeat = true;
/*     */         } else {
/* 297 */           flushDeferredEvent();
/*     */         }
/* 299 */       putKeyEvent(key_code, key_location, state, character, nanos, repeat);
/*     */     } else {
/* 301 */       flushDeferredEvent();
/* 302 */       this.has_deferred_event = true;
/* 303 */       this.deferred_nanos = nanos;
/* 304 */       this.deferred_key_code = key_code;
/* 305 */       this.deferred_key_location = key_location;
/* 306 */       this.deferred_key_state = state;
/* 307 */       this.deferred_character = character;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void flushDeferredEvent() {
/* 312 */     if (this.has_deferred_event) {
/* 313 */       putKeyEvent(this.deferred_key_code, this.deferred_key_location, this.deferred_key_state, this.deferred_character, this.deferred_nanos, false);
/* 314 */       this.has_deferred_event = false;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void putKeyEvent(int key_code, int key_location, byte state, int character, long nanos, boolean repeat) {
/* 319 */     int key_code_mapped = getMappedKeyCode(key_code, key_location);
/*     */ 
/* 321 */     if (this.key_states[key_code_mapped] == state)
/* 322 */       repeat = true;
/* 323 */     this.key_states[key_code_mapped] = state;
/* 324 */     int key_int_char = character & 0xFFFF;
/* 325 */     putKeyboardEvent(key_code_mapped, state, key_int_char, nanos, repeat);
/*     */   }
/*     */ 
/*     */   private int getMappedKeyCode(int key_code, int position)
/*     */   {
/* 330 */     switch (key_code) {
/*     */     case 18:
/* 332 */       if (position == 3) {
/* 333 */         return 184;
/*     */       }
/* 335 */       return 56;
/*     */     case 157:
/* 337 */       if (position == 3) {
/* 338 */         return 220;
/*     */       }
/* 340 */       return 219;
/*     */     case 16:
/* 342 */       if (position == 3) {
/* 343 */         return 54;
/*     */       }
/* 345 */       return 42;
/*     */     case 17:
/* 347 */       if (position == 3) {
/* 348 */         return 157;
/*     */       }
/* 350 */       return 29;
/*     */     }
/* 352 */     return KEY_MAP[key_code];
/*     */   }
/*     */ 
/*     */   public void keyPressed(KeyEvent e)
/*     */   {
/* 357 */     handleKey(e.getKeyCode(), e.getKeyLocation(), (byte)1, e.getKeyChar(), e.getWhen() * 1000000L);
/*     */   }
/*     */ 
/*     */   public void keyReleased(KeyEvent e) {
/* 361 */     handleKey(e.getKeyCode(), e.getKeyLocation(), (byte)0, 0, e.getWhen() * 1000000L);
/*     */   }
/*     */ 
/*     */   public void keyTyped(KeyEvent e)
/*     */   {
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  64 */     KEY_MAP[48] = 11;
/*  65 */     KEY_MAP[49] = 2;
/*  66 */     KEY_MAP[50] = 3;
/*  67 */     KEY_MAP[51] = 4;
/*  68 */     KEY_MAP[52] = 5;
/*  69 */     KEY_MAP[53] = 6;
/*  70 */     KEY_MAP[54] = 7;
/*  71 */     KEY_MAP[55] = 8;
/*  72 */     KEY_MAP[56] = 9;
/*  73 */     KEY_MAP[57] = 10;
/*  74 */     KEY_MAP[65] = 30;
/*     */ 
/*  76 */     KEY_MAP[107] = 78;
/*     */ 
/*  81 */     KEY_MAP[65406] = 184;
/*     */ 
/*  84 */     KEY_MAP[512] = 145;
/*  85 */     KEY_MAP[66] = 48;
/*     */ 
/*  87 */     KEY_MAP[92] = 43;
/*  88 */     KEY_MAP[8] = 14;
/*     */ 
/*  91 */     KEY_MAP[67] = 46;
/*     */ 
/*  93 */     KEY_MAP[20] = 58;
/*  94 */     KEY_MAP[514] = 144;
/*     */ 
/*  96 */     KEY_MAP[93] = 27;
/*     */ 
/*  98 */     KEY_MAP[513] = 146;
/*  99 */     KEY_MAP[44] = 51;
/*     */ 
/* 102 */     KEY_MAP[28] = 121;
/*     */ 
/* 105 */     KEY_MAP[68] = 32;
/*     */ 
/* 122 */     KEY_MAP[110] = 83;
/* 123 */     KEY_MAP[127] = 211;
/* 124 */     KEY_MAP[111] = 181;
/*     */ 
/* 126 */     KEY_MAP[40] = 208;
/* 127 */     KEY_MAP[69] = 18;
/* 128 */     KEY_MAP[35] = 207;
/* 129 */     KEY_MAP[10] = 28;
/* 130 */     KEY_MAP[61] = 13;
/* 131 */     KEY_MAP[27] = 1;
/*     */ 
/* 134 */     KEY_MAP[70] = 33;
/* 135 */     KEY_MAP[112] = 59;
/* 136 */     KEY_MAP[121] = 68;
/* 137 */     KEY_MAP[122] = 87;
/* 138 */     KEY_MAP[123] = 88;
/* 139 */     KEY_MAP[61440] = 100;
/* 140 */     KEY_MAP[61441] = 101;
/* 141 */     KEY_MAP[61442] = 102;
/*     */ 
/* 146 */     KEY_MAP[113] = 60;
/*     */ 
/* 152 */     KEY_MAP[114] = 61;
/* 153 */     KEY_MAP[115] = 62;
/* 154 */     KEY_MAP[116] = 63;
/* 155 */     KEY_MAP[117] = 64;
/* 156 */     KEY_MAP[118] = 65;
/* 157 */     KEY_MAP[119] = 66;
/* 158 */     KEY_MAP[120] = 67;
/*     */ 
/* 162 */     KEY_MAP[71] = 34;
/*     */ 
/* 164 */     KEY_MAP[72] = 35;
/*     */ 
/* 168 */     KEY_MAP[36] = 199;
/* 169 */     KEY_MAP[73] = 23;
/*     */ 
/* 171 */     KEY_MAP[''] = 210;
/*     */ 
/* 173 */     KEY_MAP[74] = 36;
/*     */ 
/* 177 */     KEY_MAP[75] = 37;
/* 178 */     KEY_MAP[21] = 112;
/*     */ 
/* 180 */     KEY_MAP[25] = 148;
/*     */ 
/* 186 */     KEY_MAP[76] = 38;
/* 187 */     KEY_MAP[37] = 203;
/*     */ 
/* 190 */     KEY_MAP[77] = 50;
/*     */ 
/* 192 */     KEY_MAP[45] = 12;
/*     */ 
/* 194 */     KEY_MAP[106] = 55;
/* 195 */     KEY_MAP[78] = 49;
/*     */ 
/* 197 */     KEY_MAP[''] = 69;
/*     */ 
/* 199 */     KEY_MAP[96] = 82;
/* 200 */     KEY_MAP[97] = 79;
/* 201 */     KEY_MAP[98] = 80;
/* 202 */     KEY_MAP[99] = 81;
/* 203 */     KEY_MAP[100] = 75;
/* 204 */     KEY_MAP[101] = 76;
/* 205 */     KEY_MAP[102] = 77;
/* 206 */     KEY_MAP[103] = 71;
/* 207 */     KEY_MAP[104] = 72;
/* 208 */     KEY_MAP[105] = 73;
/* 209 */     KEY_MAP[79] = 24;
/* 210 */     KEY_MAP[91] = 26;
/* 211 */     KEY_MAP[80] = 25;
/* 212 */     KEY_MAP[34] = 209;
/* 213 */     KEY_MAP[33] = 201;
/*     */ 
/* 215 */     KEY_MAP[19] = 197;
/* 216 */     KEY_MAP[46] = 52;
/*     */ 
/* 221 */     KEY_MAP[81] = 16;
/*     */ 
/* 224 */     KEY_MAP[82] = 19;
/* 225 */     KEY_MAP[39] = 205;
/*     */ 
/* 228 */     KEY_MAP[83] = 31;
/* 229 */     KEY_MAP[''] = 70;
/* 230 */     KEY_MAP[59] = 39;
/* 231 */     KEY_MAP[108] = 83;
/*     */ 
/* 233 */     KEY_MAP[47] = 53;
/* 234 */     KEY_MAP[32] = 57;
/* 235 */     KEY_MAP[65480] = 149;
/* 236 */     KEY_MAP[109] = 74;
/* 237 */     KEY_MAP[84] = 20;
/* 238 */     KEY_MAP[9] = 15;
/* 239 */     KEY_MAP[85] = 22;
/*     */ 
/* 242 */     KEY_MAP[38] = 200;
/* 243 */     KEY_MAP[86] = 47;
/* 244 */     KEY_MAP[87] = 17;
/* 245 */     KEY_MAP[88] = 45;
/* 246 */     KEY_MAP[89] = 21;
/* 247 */     KEY_MAP[90] = 44;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.KeyboardEventQueue
 * JD-Core Version:    0.6.2
 */