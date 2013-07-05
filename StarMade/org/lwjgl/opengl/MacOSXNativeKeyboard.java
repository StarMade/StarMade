/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.io.PrintStream;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ final class MacOSXNativeKeyboard extends EventQueue
/*     */ {
/*  49 */   private final byte[] key_states = new byte[256];
/*     */ 
/*  52 */   private final ByteBuffer event = ByteBuffer.allocate(18);
/*     */   private ByteBuffer window_handle;
/*     */   private boolean has_deferred_event;
/*     */   private long deferred_nanos;
/*     */   private int deferred_key_code;
/*     */   private byte deferred_key_state;
/*     */   private int deferred_character;
/*     */   private HashMap<Short, Integer> nativeToLwjglMap;
/*     */ 
/*     */   MacOSXNativeKeyboard(ByteBuffer window_handle)
/*     */   {
/*  65 */     super(18);
/*  66 */     this.nativeToLwjglMap = new HashMap();
/*  67 */     initKeyboardMappings();
/*  68 */     this.window_handle = window_handle;
/*     */   }
/*     */ 
/*     */   private native void nRegisterKeyListener(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   private native void nUnregisterKeyListener(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   private void initKeyboardMappings()
/*     */   {
/*  77 */     this.nativeToLwjglMap.put(Short.valueOf((short)29), Integer.valueOf(11));
/*  78 */     this.nativeToLwjglMap.put(Short.valueOf((short)18), Integer.valueOf(2));
/*  79 */     this.nativeToLwjglMap.put(Short.valueOf((short)19), Integer.valueOf(3));
/*  80 */     this.nativeToLwjglMap.put(Short.valueOf((short)20), Integer.valueOf(4));
/*  81 */     this.nativeToLwjglMap.put(Short.valueOf((short)21), Integer.valueOf(5));
/*  82 */     this.nativeToLwjglMap.put(Short.valueOf((short)23), Integer.valueOf(6));
/*  83 */     this.nativeToLwjglMap.put(Short.valueOf((short)22), Integer.valueOf(7));
/*  84 */     this.nativeToLwjglMap.put(Short.valueOf((short)26), Integer.valueOf(8));
/*  85 */     this.nativeToLwjglMap.put(Short.valueOf((short)28), Integer.valueOf(9));
/*  86 */     this.nativeToLwjglMap.put(Short.valueOf((short)25), Integer.valueOf(10));
/*  87 */     this.nativeToLwjglMap.put(Short.valueOf((short)0), Integer.valueOf(30));
/*  88 */     this.nativeToLwjglMap.put(Short.valueOf((short)11), Integer.valueOf(48));
/*  89 */     this.nativeToLwjglMap.put(Short.valueOf((short)8), Integer.valueOf(46));
/*  90 */     this.nativeToLwjglMap.put(Short.valueOf((short)2), Integer.valueOf(32));
/*  91 */     this.nativeToLwjglMap.put(Short.valueOf((short)14), Integer.valueOf(18));
/*  92 */     this.nativeToLwjglMap.put(Short.valueOf((short)3), Integer.valueOf(33));
/*  93 */     this.nativeToLwjglMap.put(Short.valueOf((short)5), Integer.valueOf(34));
/*  94 */     this.nativeToLwjglMap.put(Short.valueOf((short)4), Integer.valueOf(35));
/*  95 */     this.nativeToLwjglMap.put(Short.valueOf((short)34), Integer.valueOf(23));
/*  96 */     this.nativeToLwjglMap.put(Short.valueOf((short)38), Integer.valueOf(36));
/*  97 */     this.nativeToLwjglMap.put(Short.valueOf((short)40), Integer.valueOf(37));
/*  98 */     this.nativeToLwjglMap.put(Short.valueOf((short)37), Integer.valueOf(38));
/*  99 */     this.nativeToLwjglMap.put(Short.valueOf((short)46), Integer.valueOf(50));
/* 100 */     this.nativeToLwjglMap.put(Short.valueOf((short)45), Integer.valueOf(49));
/* 101 */     this.nativeToLwjglMap.put(Short.valueOf((short)31), Integer.valueOf(24));
/* 102 */     this.nativeToLwjglMap.put(Short.valueOf((short)35), Integer.valueOf(25));
/* 103 */     this.nativeToLwjglMap.put(Short.valueOf((short)12), Integer.valueOf(16));
/* 104 */     this.nativeToLwjglMap.put(Short.valueOf((short)15), Integer.valueOf(19));
/* 105 */     this.nativeToLwjglMap.put(Short.valueOf((short)1), Integer.valueOf(31));
/* 106 */     this.nativeToLwjglMap.put(Short.valueOf((short)17), Integer.valueOf(20));
/* 107 */     this.nativeToLwjglMap.put(Short.valueOf((short)32), Integer.valueOf(22));
/* 108 */     this.nativeToLwjglMap.put(Short.valueOf((short)9), Integer.valueOf(47));
/* 109 */     this.nativeToLwjglMap.put(Short.valueOf((short)13), Integer.valueOf(17));
/* 110 */     this.nativeToLwjglMap.put(Short.valueOf((short)7), Integer.valueOf(45));
/* 111 */     this.nativeToLwjglMap.put(Short.valueOf((short)16), Integer.valueOf(21));
/* 112 */     this.nativeToLwjglMap.put(Short.valueOf((short)6), Integer.valueOf(44));
/*     */ 
/* 114 */     this.nativeToLwjglMap.put(Short.valueOf((short)42), Integer.valueOf(43));
/* 115 */     this.nativeToLwjglMap.put(Short.valueOf((short)43), Integer.valueOf(51));
/* 116 */     this.nativeToLwjglMap.put(Short.valueOf((short)24), Integer.valueOf(13));
/* 117 */     this.nativeToLwjglMap.put(Short.valueOf((short)33), Integer.valueOf(26));
/* 118 */     this.nativeToLwjglMap.put(Short.valueOf((short)27), Integer.valueOf(12));
/* 119 */     this.nativeToLwjglMap.put(Short.valueOf((short)39), Integer.valueOf(40));
/* 120 */     this.nativeToLwjglMap.put(Short.valueOf((short)30), Integer.valueOf(27));
/* 121 */     this.nativeToLwjglMap.put(Short.valueOf((short)41), Integer.valueOf(39));
/* 122 */     this.nativeToLwjglMap.put(Short.valueOf((short)44), Integer.valueOf(53));
/* 123 */     this.nativeToLwjglMap.put(Short.valueOf((short)47), Integer.valueOf(52));
/* 124 */     this.nativeToLwjglMap.put(Short.valueOf((short)50), Integer.valueOf(144));
/*     */ 
/* 126 */     this.nativeToLwjglMap.put(Short.valueOf((short)65), Integer.valueOf(83));
/* 127 */     this.nativeToLwjglMap.put(Short.valueOf((short)67), Integer.valueOf(55));
/* 128 */     this.nativeToLwjglMap.put(Short.valueOf((short)69), Integer.valueOf(78));
/* 129 */     this.nativeToLwjglMap.put(Short.valueOf((short)71), Integer.valueOf(218));
/* 130 */     this.nativeToLwjglMap.put(Short.valueOf((short)75), Integer.valueOf(181));
/* 131 */     this.nativeToLwjglMap.put(Short.valueOf((short)76), Integer.valueOf(156));
/* 132 */     this.nativeToLwjglMap.put(Short.valueOf((short)78), Integer.valueOf(74));
/* 133 */     this.nativeToLwjglMap.put(Short.valueOf((short)81), Integer.valueOf(141));
/*     */ 
/* 135 */     this.nativeToLwjglMap.put(Short.valueOf((short)82), Integer.valueOf(82));
/* 136 */     this.nativeToLwjglMap.put(Short.valueOf((short)83), Integer.valueOf(79));
/* 137 */     this.nativeToLwjglMap.put(Short.valueOf((short)84), Integer.valueOf(80));
/* 138 */     this.nativeToLwjglMap.put(Short.valueOf((short)85), Integer.valueOf(81));
/* 139 */     this.nativeToLwjglMap.put(Short.valueOf((short)86), Integer.valueOf(75));
/* 140 */     this.nativeToLwjglMap.put(Short.valueOf((short)87), Integer.valueOf(76));
/* 141 */     this.nativeToLwjglMap.put(Short.valueOf((short)88), Integer.valueOf(77));
/* 142 */     this.nativeToLwjglMap.put(Short.valueOf((short)89), Integer.valueOf(71));
/* 143 */     this.nativeToLwjglMap.put(Short.valueOf((short)91), Integer.valueOf(72));
/* 144 */     this.nativeToLwjglMap.put(Short.valueOf((short)92), Integer.valueOf(73));
/*     */ 
/* 147 */     this.nativeToLwjglMap.put(Short.valueOf((short)36), Integer.valueOf(28));
/* 148 */     this.nativeToLwjglMap.put(Short.valueOf((short)48), Integer.valueOf(15));
/* 149 */     this.nativeToLwjglMap.put(Short.valueOf((short)49), Integer.valueOf(57));
/* 150 */     this.nativeToLwjglMap.put(Short.valueOf((short)51), Integer.valueOf(14));
/* 151 */     this.nativeToLwjglMap.put(Short.valueOf((short)53), Integer.valueOf(1));
/* 152 */     this.nativeToLwjglMap.put(Short.valueOf((short)54), Integer.valueOf(220));
/* 153 */     this.nativeToLwjglMap.put(Short.valueOf((short)55), Integer.valueOf(219));
/* 154 */     this.nativeToLwjglMap.put(Short.valueOf((short)56), Integer.valueOf(42));
/* 155 */     this.nativeToLwjglMap.put(Short.valueOf((short)57), Integer.valueOf(58));
/* 156 */     this.nativeToLwjglMap.put(Short.valueOf((short)58), Integer.valueOf(56));
/* 157 */     this.nativeToLwjglMap.put(Short.valueOf((short)59), Integer.valueOf(29));
/* 158 */     this.nativeToLwjglMap.put(Short.valueOf((short)60), Integer.valueOf(54));
/* 159 */     this.nativeToLwjglMap.put(Short.valueOf((short)61), Integer.valueOf(184));
/* 160 */     this.nativeToLwjglMap.put(Short.valueOf((short)62), Integer.valueOf(157));
/*     */ 
/* 162 */     this.nativeToLwjglMap.put(Short.valueOf((short)63), Integer.valueOf(196));
/* 163 */     this.nativeToLwjglMap.put(Short.valueOf((short)119), Integer.valueOf(207));
/*     */ 
/* 165 */     this.nativeToLwjglMap.put(Short.valueOf((short)122), Integer.valueOf(59));
/* 166 */     this.nativeToLwjglMap.put(Short.valueOf((short)120), Integer.valueOf(60));
/* 167 */     this.nativeToLwjglMap.put(Short.valueOf((short)99), Integer.valueOf(61));
/* 168 */     this.nativeToLwjglMap.put(Short.valueOf((short)118), Integer.valueOf(62));
/* 169 */     this.nativeToLwjglMap.put(Short.valueOf((short)96), Integer.valueOf(63));
/* 170 */     this.nativeToLwjglMap.put(Short.valueOf((short)97), Integer.valueOf(64));
/* 171 */     this.nativeToLwjglMap.put(Short.valueOf((short)98), Integer.valueOf(65));
/* 172 */     this.nativeToLwjglMap.put(Short.valueOf((short)100), Integer.valueOf(66));
/* 173 */     this.nativeToLwjglMap.put(Short.valueOf((short)101), Integer.valueOf(67));
/* 174 */     this.nativeToLwjglMap.put(Short.valueOf((short)109), Integer.valueOf(68));
/* 175 */     this.nativeToLwjglMap.put(Short.valueOf((short)103), Integer.valueOf(87));
/* 176 */     this.nativeToLwjglMap.put(Short.valueOf((short)111), Integer.valueOf(88));
/* 177 */     this.nativeToLwjglMap.put(Short.valueOf((short)105), Integer.valueOf(100));
/* 178 */     this.nativeToLwjglMap.put(Short.valueOf((short)107), Integer.valueOf(101));
/* 179 */     this.nativeToLwjglMap.put(Short.valueOf((short)113), Integer.valueOf(102));
/* 180 */     this.nativeToLwjglMap.put(Short.valueOf((short)106), Integer.valueOf(103));
/* 181 */     this.nativeToLwjglMap.put(Short.valueOf((short)64), Integer.valueOf(104));
/* 182 */     this.nativeToLwjglMap.put(Short.valueOf((short)79), Integer.valueOf(105));
/* 183 */     this.nativeToLwjglMap.put(Short.valueOf((short)80), Integer.valueOf(113));
/*     */ 
/* 186 */     this.nativeToLwjglMap.put(Short.valueOf((short)117), Integer.valueOf(211));
/* 187 */     this.nativeToLwjglMap.put(Short.valueOf((short)114), Integer.valueOf(210));
/* 188 */     this.nativeToLwjglMap.put(Short.valueOf((short)115), Integer.valueOf(199));
/*     */ 
/* 190 */     this.nativeToLwjglMap.put(Short.valueOf((short)121), Integer.valueOf(209));
/* 191 */     this.nativeToLwjglMap.put(Short.valueOf((short)116), Integer.valueOf(201));
/*     */ 
/* 194 */     this.nativeToLwjglMap.put(Short.valueOf((short)123), Integer.valueOf(203));
/* 195 */     this.nativeToLwjglMap.put(Short.valueOf((short)124), Integer.valueOf(205));
/* 196 */     this.nativeToLwjglMap.put(Short.valueOf((short)125), Integer.valueOf(208));
/* 197 */     this.nativeToLwjglMap.put(Short.valueOf((short)126), Integer.valueOf(200));
/*     */ 
/* 199 */     this.nativeToLwjglMap.put(Short.valueOf((short)10), Integer.valueOf(167));
/*     */ 
/* 201 */     this.nativeToLwjglMap.put(Short.valueOf((short)110), Integer.valueOf(221));
/* 202 */     this.nativeToLwjglMap.put(Short.valueOf((short)297), Integer.valueOf(146));
/*     */   }
/*     */ 
/*     */   public void register() {
/* 206 */     nRegisterKeyListener(this.window_handle);
/*     */   }
/*     */ 
/*     */   public void unregister() {
/* 210 */     nUnregisterKeyListener(this.window_handle);
/*     */   }
/*     */ 
/*     */   public void putKeyboardEvent(int key_code, byte state, int character, long nanos, boolean repeat) {
/* 214 */     this.event.clear();
/* 215 */     this.event.putInt(key_code).put(state).putInt(character).putLong(nanos).put((byte)(repeat ? 1 : 0));
/* 216 */     this.event.flip();
/* 217 */     putEvent(this.event);
/*     */   }
/*     */ 
/*     */   public synchronized void poll(ByteBuffer key_down_buffer) {
/* 221 */     flushDeferredEvent();
/* 222 */     int old_position = key_down_buffer.position();
/* 223 */     key_down_buffer.put(this.key_states);
/* 224 */     key_down_buffer.position(old_position);
/*     */   }
/*     */ 
/*     */   public synchronized void copyEvents(ByteBuffer dest) {
/* 228 */     flushDeferredEvent();
/* 229 */     super.copyEvents(dest);
/*     */   }
/*     */ 
/*     */   private synchronized void handleKey(int key_code, byte state, int character, long nanos) {
/* 233 */     if (character == 65535)
/* 234 */       character = 0;
/* 235 */     if (state == 1) {
/* 236 */       boolean repeat = false;
/* 237 */       if (this.has_deferred_event)
/* 238 */         if ((nanos == this.deferred_nanos) && (this.deferred_key_code == key_code)) {
/* 239 */           this.has_deferred_event = false;
/* 240 */           repeat = true;
/*     */         } else {
/* 242 */           flushDeferredEvent();
/*     */         }
/* 244 */       putKeyEvent(key_code, state, character, nanos, repeat);
/*     */     } else {
/* 246 */       flushDeferredEvent();
/* 247 */       this.has_deferred_event = true;
/* 248 */       this.deferred_nanos = nanos;
/* 249 */       this.deferred_key_code = key_code;
/* 250 */       this.deferred_key_state = state;
/* 251 */       this.deferred_character = character;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void flushDeferredEvent() {
/* 256 */     if (this.has_deferred_event) {
/* 257 */       putKeyEvent(this.deferred_key_code, this.deferred_key_state, this.deferred_character, this.deferred_nanos, false);
/* 258 */       this.has_deferred_event = false;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void putKeyEvent(int key_code, byte state, int character, long nanos, boolean repeat)
/*     */   {
/* 264 */     int mapped_code = getMappedKeyCode((short)key_code);
/* 265 */     if (mapped_code < 0) {
/* 266 */       System.out.println("Unrecognized keycode: " + key_code);
/*     */ 
/* 268 */       return;
/*     */     }
/* 270 */     if (this.key_states[mapped_code] == state)
/* 271 */       repeat = true;
/* 272 */     this.key_states[mapped_code] = state;
/* 273 */     int key_int_char = character & 0xFFFF;
/* 274 */     putKeyboardEvent(mapped_code, state, key_int_char, nanos, repeat);
/*     */   }
/*     */ 
/*     */   private int getMappedKeyCode(short key_code) {
/* 278 */     if (this.nativeToLwjglMap.containsKey(Short.valueOf(key_code))) {
/* 279 */       return ((Integer)this.nativeToLwjglMap.get(Short.valueOf(key_code))).intValue();
/*     */     }
/* 281 */     return -1;
/*     */   }
/*     */ 
/*     */   public void keyPressed(int key_code, int character, long nanos) {
/* 285 */     handleKey(key_code, (byte)1, character, nanos);
/*     */   }
/*     */ 
/*     */   public void keyReleased(int key_code, int character, long nanos) {
/* 289 */     handleKey(key_code, (byte)0, character, nanos);
/*     */   }
/*     */ 
/*     */   public void keyTyped(KeyEvent e)
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.MacOSXNativeKeyboard
 * JD-Core Version:    0.6.2
 */