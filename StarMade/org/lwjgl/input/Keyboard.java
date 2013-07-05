/*     */ package org.lwjgl.input;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.LWJGLException;
/*     */ import org.lwjgl.Sys;
/*     */ import org.lwjgl.opengl.Display;
/*     */ import org.lwjgl.opengl.InputImplementation;
/*     */ 
/*     */ public class Keyboard
/*     */ {
/*     */   public static final int EVENT_SIZE = 18;
/*     */   public static final int CHAR_NONE = 0;
/*     */   public static final int KEY_NONE = 0;
/*     */   public static final int KEY_ESCAPE = 1;
/*     */   public static final int KEY_1 = 2;
/*     */   public static final int KEY_2 = 3;
/*     */   public static final int KEY_3 = 4;
/*     */   public static final int KEY_4 = 5;
/*     */   public static final int KEY_5 = 6;
/*     */   public static final int KEY_6 = 7;
/*     */   public static final int KEY_7 = 8;
/*     */   public static final int KEY_8 = 9;
/*     */   public static final int KEY_9 = 10;
/*     */   public static final int KEY_0 = 11;
/*     */   public static final int KEY_MINUS = 12;
/*     */   public static final int KEY_EQUALS = 13;
/*     */   public static final int KEY_BACK = 14;
/*     */   public static final int KEY_TAB = 15;
/*     */   public static final int KEY_Q = 16;
/*     */   public static final int KEY_W = 17;
/*     */   public static final int KEY_E = 18;
/*     */   public static final int KEY_R = 19;
/*     */   public static final int KEY_T = 20;
/*     */   public static final int KEY_Y = 21;
/*     */   public static final int KEY_U = 22;
/*     */   public static final int KEY_I = 23;
/*     */   public static final int KEY_O = 24;
/*     */   public static final int KEY_P = 25;
/*     */   public static final int KEY_LBRACKET = 26;
/*     */   public static final int KEY_RBRACKET = 27;
/*     */   public static final int KEY_RETURN = 28;
/*     */   public static final int KEY_LCONTROL = 29;
/*     */   public static final int KEY_A = 30;
/*     */   public static final int KEY_S = 31;
/*     */   public static final int KEY_D = 32;
/*     */   public static final int KEY_F = 33;
/*     */   public static final int KEY_G = 34;
/*     */   public static final int KEY_H = 35;
/*     */   public static final int KEY_J = 36;
/*     */   public static final int KEY_K = 37;
/*     */   public static final int KEY_L = 38;
/*     */   public static final int KEY_SEMICOLON = 39;
/*     */   public static final int KEY_APOSTROPHE = 40;
/*     */   public static final int KEY_GRAVE = 41;
/*     */   public static final int KEY_LSHIFT = 42;
/*     */   public static final int KEY_BACKSLASH = 43;
/*     */   public static final int KEY_Z = 44;
/*     */   public static final int KEY_X = 45;
/*     */   public static final int KEY_C = 46;
/*     */   public static final int KEY_V = 47;
/*     */   public static final int KEY_B = 48;
/*     */   public static final int KEY_N = 49;
/*     */   public static final int KEY_M = 50;
/*     */   public static final int KEY_COMMA = 51;
/*     */   public static final int KEY_PERIOD = 52;
/*     */   public static final int KEY_SLASH = 53;
/*     */   public static final int KEY_RSHIFT = 54;
/*     */   public static final int KEY_MULTIPLY = 55;
/*     */   public static final int KEY_LMENU = 56;
/*     */   public static final int KEY_SPACE = 57;
/*     */   public static final int KEY_CAPITAL = 58;
/*     */   public static final int KEY_F1 = 59;
/*     */   public static final int KEY_F2 = 60;
/*     */   public static final int KEY_F3 = 61;
/*     */   public static final int KEY_F4 = 62;
/*     */   public static final int KEY_F5 = 63;
/*     */   public static final int KEY_F6 = 64;
/*     */   public static final int KEY_F7 = 65;
/*     */   public static final int KEY_F8 = 66;
/*     */   public static final int KEY_F9 = 67;
/*     */   public static final int KEY_F10 = 68;
/*     */   public static final int KEY_NUMLOCK = 69;
/*     */   public static final int KEY_SCROLL = 70;
/*     */   public static final int KEY_NUMPAD7 = 71;
/*     */   public static final int KEY_NUMPAD8 = 72;
/*     */   public static final int KEY_NUMPAD9 = 73;
/*     */   public static final int KEY_SUBTRACT = 74;
/*     */   public static final int KEY_NUMPAD4 = 75;
/*     */   public static final int KEY_NUMPAD5 = 76;
/*     */   public static final int KEY_NUMPAD6 = 77;
/*     */   public static final int KEY_ADD = 78;
/*     */   public static final int KEY_NUMPAD1 = 79;
/*     */   public static final int KEY_NUMPAD2 = 80;
/*     */   public static final int KEY_NUMPAD3 = 81;
/*     */   public static final int KEY_NUMPAD0 = 82;
/*     */   public static final int KEY_DECIMAL = 83;
/*     */   public static final int KEY_F11 = 87;
/*     */   public static final int KEY_F12 = 88;
/*     */   public static final int KEY_F13 = 100;
/*     */   public static final int KEY_F14 = 101;
/*     */   public static final int KEY_F15 = 102;
/*     */   public static final int KEY_F16 = 103;
/*     */   public static final int KEY_F17 = 104;
/*     */   public static final int KEY_F18 = 105;
/*     */   public static final int KEY_KANA = 112;
/*     */   public static final int KEY_F19 = 113;
/*     */   public static final int KEY_CONVERT = 121;
/*     */   public static final int KEY_NOCONVERT = 123;
/*     */   public static final int KEY_YEN = 125;
/*     */   public static final int KEY_NUMPADEQUALS = 141;
/*     */   public static final int KEY_CIRCUMFLEX = 144;
/*     */   public static final int KEY_AT = 145;
/*     */   public static final int KEY_COLON = 146;
/*     */   public static final int KEY_UNDERLINE = 147;
/*     */   public static final int KEY_KANJI = 148;
/*     */   public static final int KEY_STOP = 149;
/*     */   public static final int KEY_AX = 150;
/*     */   public static final int KEY_UNLABELED = 151;
/*     */   public static final int KEY_NUMPADENTER = 156;
/*     */   public static final int KEY_RCONTROL = 157;
/*     */   public static final int KEY_SECTION = 167;
/*     */   public static final int KEY_NUMPADCOMMA = 179;
/*     */   public static final int KEY_DIVIDE = 181;
/*     */   public static final int KEY_SYSRQ = 183;
/*     */   public static final int KEY_RMENU = 184;
/*     */   public static final int KEY_FUNCTION = 196;
/*     */   public static final int KEY_PAUSE = 197;
/*     */   public static final int KEY_HOME = 199;
/*     */   public static final int KEY_UP = 200;
/*     */   public static final int KEY_PRIOR = 201;
/*     */   public static final int KEY_LEFT = 203;
/*     */   public static final int KEY_RIGHT = 205;
/*     */   public static final int KEY_END = 207;
/*     */   public static final int KEY_DOWN = 208;
/*     */   public static final int KEY_NEXT = 209;
/*     */   public static final int KEY_INSERT = 210;
/*     */   public static final int KEY_DELETE = 211;
/*     */   public static final int KEY_CLEAR = 218;
/*     */   public static final int KEY_LMETA = 219;
/*     */ 
/*     */   /** @deprecated */
/*     */   public static final int KEY_LWIN = 219;
/*     */   public static final int KEY_RMETA = 220;
/*     */ 
/*     */   /** @deprecated */
/*     */   public static final int KEY_RWIN = 220;
/*     */   public static final int KEY_APPS = 221;
/*     */   public static final int KEY_POWER = 222;
/*     */   public static final int KEY_SLEEP = 223;
/*     */   public static final int KEYBOARD_SIZE = 256;
/*     */   private static final int BUFFER_SIZE = 50;
/* 226 */   private static final String[] keyName = new String[256];
/* 227 */   private static final Map<String, Integer> keyMap = new HashMap(253);
/*     */   private static int counter;
/* 256 */   private static final int keyCount = counter;
/*     */   private static boolean created;
/*     */   private static boolean repeat_enabled;
/* 265 */   private static final ByteBuffer keyDownBuffer = BufferUtils.createByteBuffer(256);
/*     */   private static ByteBuffer readBuffer;
/* 275 */   private static KeyEvent current_event = new KeyEvent(null);
/*     */ 
/* 278 */   private static KeyEvent tmp_event = new KeyEvent(null);
/*     */   private static boolean initialized;
/*     */   private static InputImplementation implementation;
/*     */ 
/*     */   private static void initialize()
/*     */   {
/* 295 */     if (initialized)
/* 296 */       return;
/* 297 */     Sys.initialize();
/* 298 */     initialized = true;
/*     */   }
/*     */ 
/*     */   private static void create(InputImplementation impl)
/*     */     throws LWJGLException
/*     */   {
/* 308 */     if (created)
/* 309 */       return;
/* 310 */     if (!initialized)
/* 311 */       initialize();
/* 312 */     implementation = impl;
/* 313 */     implementation.createKeyboard();
/* 314 */     created = true;
/* 315 */     readBuffer = ByteBuffer.allocate(900);
/* 316 */     reset();
/*     */   }
/*     */ 
/*     */   public static void create()
/*     */     throws LWJGLException
/*     */   {
/* 326 */     synchronized (OpenGLPackageAccess.global_lock) {
/* 327 */       if (!Display.isCreated()) throw new IllegalStateException("Display must be created.");
/*     */ 
/* 329 */       create(OpenGLPackageAccess.createImplementation());
/*     */     }
/*     */   }
/*     */ 
/*     */   private static void reset() {
/* 334 */     readBuffer.limit(0);
/* 335 */     for (int i = 0; i < keyDownBuffer.remaining(); i++)
/* 336 */       keyDownBuffer.put(i, (byte)0);
/* 337 */     current_event.reset();
/*     */   }
/*     */ 
/*     */   public static boolean isCreated()
/*     */   {
/* 344 */     synchronized (OpenGLPackageAccess.global_lock) {
/* 345 */       return created;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void destroy()
/*     */   {
/* 353 */     synchronized (OpenGLPackageAccess.global_lock) {
/* 354 */       if (!created)
/* 355 */         return;
/* 356 */       created = false;
/* 357 */       implementation.destroyKeyboard();
/* 358 */       reset();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void poll()
/*     */   {
/* 384 */     synchronized (OpenGLPackageAccess.global_lock) {
/* 385 */       if (!created)
/* 386 */         throw new IllegalStateException("Keyboard must be created before you can poll the device");
/* 387 */       implementation.pollKeyboard(keyDownBuffer);
/* 388 */       read();
/*     */     }
/*     */   }
/*     */ 
/*     */   private static void read() {
/* 393 */     readBuffer.compact();
/* 394 */     implementation.readKeyboard(readBuffer);
/* 395 */     readBuffer.flip();
/*     */   }
/*     */ 
/*     */   public static boolean isKeyDown(int key)
/*     */   {
/* 404 */     synchronized (OpenGLPackageAccess.global_lock) {
/* 405 */       if (!created)
/* 406 */         throw new IllegalStateException("Keyboard must be created before you can query key state");
/* 407 */       return keyDownBuffer.get(key) != 0;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static synchronized String getKeyName(int key)
/*     */   {
/* 429 */     return keyName[key];
/*     */   }
/*     */ 
/*     */   public static synchronized int getKeyIndex(String keyName)
/*     */   {
/* 437 */     Integer ret = (Integer)keyMap.get(keyName);
/* 438 */     if (ret == null) {
/* 439 */       return 0;
/*     */     }
/* 441 */     return ret.intValue();
/*     */   }
/*     */ 
/*     */   public static int getNumKeyboardEvents()
/*     */   {
/* 449 */     synchronized (OpenGLPackageAccess.global_lock) {
/* 450 */       if (!created)
/* 451 */         throw new IllegalStateException("Keyboard must be created before you can read events");
/* 452 */       int old_position = readBuffer.position();
/* 453 */       int num_events = 0;
/* 454 */       while ((readNext(tmp_event)) && ((!tmp_event.repeat) || (repeat_enabled)))
/* 455 */         num_events++;
/* 456 */       readBuffer.position(old_position);
/* 457 */       return num_events;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean next()
/*     */   {
/* 473 */     synchronized (OpenGLPackageAccess.global_lock) {
/* 474 */       if (!created)
/* 475 */         throw new IllegalStateException("Keyboard must be created before you can read events");
/*     */       boolean result;
/* 478 */       while (((result = readNext(current_event))) && (current_event.repeat) && (!repeat_enabled));
/* 480 */       return result;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void enableRepeatEvents(boolean enable)
/*     */   {
/* 493 */     synchronized (OpenGLPackageAccess.global_lock) {
/* 494 */       repeat_enabled = enable;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean areRepeatEventsEnabled()
/*     */   {
/* 505 */     synchronized (OpenGLPackageAccess.global_lock) {
/* 506 */       return repeat_enabled;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static boolean readNext(KeyEvent event) {
/* 511 */     if (readBuffer.hasRemaining()) {
/* 512 */       event.key = (readBuffer.getInt() & 0xFF);
/* 513 */       event.state = (readBuffer.get() != 0);
/* 514 */       event.character = readBuffer.getInt();
/* 515 */       event.nanos = readBuffer.getLong();
/* 516 */       event.repeat = (readBuffer.get() == 1);
/* 517 */       return true;
/*     */     }
/* 519 */     return false;
/*     */   }
/*     */ 
/*     */   public static int getKeyCount()
/*     */   {
/* 526 */     return keyCount;
/*     */   }
/*     */ 
/*     */   public static char getEventCharacter()
/*     */   {
/* 533 */     synchronized (OpenGLPackageAccess.global_lock) {
/* 534 */       return (char)current_event.character;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static int getEventKey()
/*     */   {
/* 546 */     synchronized (OpenGLPackageAccess.global_lock) {
/* 547 */       return current_event.key;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean getEventKeyState()
/*     */   {
/* 558 */     synchronized (OpenGLPackageAccess.global_lock) {
/* 559 */       return current_event.state;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static long getEventNanoseconds()
/*     */   {
/* 571 */     synchronized (OpenGLPackageAccess.global_lock) {
/* 572 */       return current_event.nanos;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean isRepeatEvent()
/*     */   {
/* 582 */     synchronized (OpenGLPackageAccess.global_lock) {
/* 583 */       return current_event.repeat;
/*     */     }
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/* 232 */     Field[] fields = Keyboard.class.getFields();
/*     */     try {
/* 234 */       for (Field field : fields)
/* 235 */         if ((Modifier.isStatic(field.getModifiers())) && (Modifier.isPublic(field.getModifiers())) && (Modifier.isFinal(field.getModifiers())) && (field.getType().equals(Integer.TYPE)) && (field.getName().startsWith("KEY_")) && (!field.getName().endsWith("WIN")))
/*     */         {
/* 242 */           int key = field.getInt(null);
/* 243 */           String name = field.getName().substring(4);
/* 244 */           keyName[key] = name;
/* 245 */           keyMap.put(name, Integer.valueOf(key));
/* 246 */           counter += 1;
/*     */         }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   private static final class KeyEvent
/*     */   {
/*     */     private int character;
/*     */     private int key;
/*     */     private boolean state;
/*     */     private long nanos;
/*     */     private boolean repeat;
/*     */ 
/*     */     private void reset()
/*     */     {
/* 604 */       this.character = 0;
/* 605 */       this.key = 0;
/* 606 */       this.state = false;
/* 607 */       this.repeat = false;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.input.Keyboard
 * JD-Core Version:    0.6.2
 */