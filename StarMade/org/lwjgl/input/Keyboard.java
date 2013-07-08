/*   1:    */package org.lwjgl.input;
/*   2:    */
/*   3:    */import java.lang.reflect.Field;
/*   4:    */import java.lang.reflect.Modifier;
/*   5:    */import java.nio.ByteBuffer;
/*   6:    */import java.util.HashMap;
/*   7:    */import java.util.Map;
/*   8:    */import org.lwjgl.BufferUtils;
/*   9:    */import org.lwjgl.LWJGLException;
/*  10:    */import org.lwjgl.Sys;
/*  11:    */import org.lwjgl.opengl.Display;
/*  12:    */import org.lwjgl.opengl.InputImplementation;
/*  13:    */
/*  81:    */public class Keyboard
/*  82:    */{
/*  83:    */  public static final int EVENT_SIZE = 18;
/*  84:    */  public static final int CHAR_NONE = 0;
/*  85:    */  public static final int KEY_NONE = 0;
/*  86:    */  public static final int KEY_ESCAPE = 1;
/*  87:    */  public static final int KEY_1 = 2;
/*  88:    */  public static final int KEY_2 = 3;
/*  89:    */  public static final int KEY_3 = 4;
/*  90:    */  public static final int KEY_4 = 5;
/*  91:    */  public static final int KEY_5 = 6;
/*  92:    */  public static final int KEY_6 = 7;
/*  93:    */  public static final int KEY_7 = 8;
/*  94:    */  public static final int KEY_8 = 9;
/*  95:    */  public static final int KEY_9 = 10;
/*  96:    */  public static final int KEY_0 = 11;
/*  97:    */  public static final int KEY_MINUS = 12;
/*  98:    */  public static final int KEY_EQUALS = 13;
/*  99:    */  public static final int KEY_BACK = 14;
/* 100:    */  public static final int KEY_TAB = 15;
/* 101:    */  public static final int KEY_Q = 16;
/* 102:    */  public static final int KEY_W = 17;
/* 103:    */  public static final int KEY_E = 18;
/* 104:    */  public static final int KEY_R = 19;
/* 105:    */  public static final int KEY_T = 20;
/* 106:    */  public static final int KEY_Y = 21;
/* 107:    */  public static final int KEY_U = 22;
/* 108:    */  public static final int KEY_I = 23;
/* 109:    */  public static final int KEY_O = 24;
/* 110:    */  public static final int KEY_P = 25;
/* 111:    */  public static final int KEY_LBRACKET = 26;
/* 112:    */  public static final int KEY_RBRACKET = 27;
/* 113:    */  public static final int KEY_RETURN = 28;
/* 114:    */  public static final int KEY_LCONTROL = 29;
/* 115:    */  public static final int KEY_A = 30;
/* 116:    */  public static final int KEY_S = 31;
/* 117:    */  public static final int KEY_D = 32;
/* 118:    */  public static final int KEY_F = 33;
/* 119:    */  public static final int KEY_G = 34;
/* 120:    */  public static final int KEY_H = 35;
/* 121:    */  public static final int KEY_J = 36;
/* 122:    */  public static final int KEY_K = 37;
/* 123:    */  public static final int KEY_L = 38;
/* 124:    */  public static final int KEY_SEMICOLON = 39;
/* 125:    */  public static final int KEY_APOSTROPHE = 40;
/* 126:    */  public static final int KEY_GRAVE = 41;
/* 127:    */  public static final int KEY_LSHIFT = 42;
/* 128:    */  public static final int KEY_BACKSLASH = 43;
/* 129:    */  public static final int KEY_Z = 44;
/* 130:    */  public static final int KEY_X = 45;
/* 131:    */  public static final int KEY_C = 46;
/* 132:    */  public static final int KEY_V = 47;
/* 133:    */  public static final int KEY_B = 48;
/* 134:    */  public static final int KEY_N = 49;
/* 135:    */  public static final int KEY_M = 50;
/* 136:    */  public static final int KEY_COMMA = 51;
/* 137:    */  public static final int KEY_PERIOD = 52;
/* 138:    */  public static final int KEY_SLASH = 53;
/* 139:    */  public static final int KEY_RSHIFT = 54;
/* 140:    */  public static final int KEY_MULTIPLY = 55;
/* 141:    */  public static final int KEY_LMENU = 56;
/* 142:    */  public static final int KEY_SPACE = 57;
/* 143:    */  public static final int KEY_CAPITAL = 58;
/* 144:    */  public static final int KEY_F1 = 59;
/* 145:    */  public static final int KEY_F2 = 60;
/* 146:    */  public static final int KEY_F3 = 61;
/* 147:    */  public static final int KEY_F4 = 62;
/* 148:    */  public static final int KEY_F5 = 63;
/* 149:    */  public static final int KEY_F6 = 64;
/* 150:    */  public static final int KEY_F7 = 65;
/* 151:    */  public static final int KEY_F8 = 66;
/* 152:    */  public static final int KEY_F9 = 67;
/* 153:    */  public static final int KEY_F10 = 68;
/* 154:    */  public static final int KEY_NUMLOCK = 69;
/* 155:    */  public static final int KEY_SCROLL = 70;
/* 156:    */  public static final int KEY_NUMPAD7 = 71;
/* 157:    */  public static final int KEY_NUMPAD8 = 72;
/* 158:    */  public static final int KEY_NUMPAD9 = 73;
/* 159:    */  public static final int KEY_SUBTRACT = 74;
/* 160:    */  public static final int KEY_NUMPAD4 = 75;
/* 161:    */  public static final int KEY_NUMPAD5 = 76;
/* 162:    */  public static final int KEY_NUMPAD6 = 77;
/* 163:    */  public static final int KEY_ADD = 78;
/* 164:    */  public static final int KEY_NUMPAD1 = 79;
/* 165:    */  public static final int KEY_NUMPAD2 = 80;
/* 166:    */  public static final int KEY_NUMPAD3 = 81;
/* 167:    */  public static final int KEY_NUMPAD0 = 82;
/* 168:    */  public static final int KEY_DECIMAL = 83;
/* 169:    */  public static final int KEY_F11 = 87;
/* 170:    */  public static final int KEY_F12 = 88;
/* 171:    */  public static final int KEY_F13 = 100;
/* 172:    */  public static final int KEY_F14 = 101;
/* 173:    */  public static final int KEY_F15 = 102;
/* 174:    */  public static final int KEY_F16 = 103;
/* 175:    */  public static final int KEY_F17 = 104;
/* 176:    */  public static final int KEY_F18 = 105;
/* 177:    */  public static final int KEY_KANA = 112;
/* 178:    */  public static final int KEY_F19 = 113;
/* 179:    */  public static final int KEY_CONVERT = 121;
/* 180:    */  public static final int KEY_NOCONVERT = 123;
/* 181:    */  public static final int KEY_YEN = 125;
/* 182:    */  public static final int KEY_NUMPADEQUALS = 141;
/* 183:    */  public static final int KEY_CIRCUMFLEX = 144;
/* 184:    */  public static final int KEY_AT = 145;
/* 185:    */  public static final int KEY_COLON = 146;
/* 186:    */  public static final int KEY_UNDERLINE = 147;
/* 187:    */  public static final int KEY_KANJI = 148;
/* 188:    */  public static final int KEY_STOP = 149;
/* 189:    */  public static final int KEY_AX = 150;
/* 190:    */  public static final int KEY_UNLABELED = 151;
/* 191:    */  public static final int KEY_NUMPADENTER = 156;
/* 192:    */  public static final int KEY_RCONTROL = 157;
/* 193:    */  public static final int KEY_SECTION = 167;
/* 194:    */  public static final int KEY_NUMPADCOMMA = 179;
/* 195:    */  public static final int KEY_DIVIDE = 181;
/* 196:    */  public static final int KEY_SYSRQ = 183;
/* 197:    */  public static final int KEY_RMENU = 184;
/* 198:    */  public static final int KEY_FUNCTION = 196;
/* 199:    */  public static final int KEY_PAUSE = 197;
/* 200:    */  public static final int KEY_HOME = 199;
/* 201:    */  public static final int KEY_UP = 200;
/* 202:    */  public static final int KEY_PRIOR = 201;
/* 203:    */  public static final int KEY_LEFT = 203;
/* 204:    */  public static final int KEY_RIGHT = 205;
/* 205:    */  public static final int KEY_END = 207;
/* 206:    */  public static final int KEY_DOWN = 208;
/* 207:    */  public static final int KEY_NEXT = 209;
/* 208:    */  public static final int KEY_INSERT = 210;
/* 209:    */  public static final int KEY_DELETE = 211;
/* 210:    */  public static final int KEY_CLEAR = 218;
/* 211:    */  public static final int KEY_LMETA = 219;
/* 212:    */  /**
/* 213:    */   * @deprecated
/* 214:    */   */
/* 215:    */  public static final int KEY_LWIN = 219;
/* 216:    */  public static final int KEY_RMETA = 220;
/* 217:    */  /**
/* 218:    */   * @deprecated
/* 219:    */   */
/* 220:    */  public static final int KEY_RWIN = 220;
/* 221:    */  public static final int KEY_APPS = 221;
/* 222:    */  public static final int KEY_POWER = 222;
/* 223:    */  public static final int KEY_SLEEP = 223;
/* 224:    */  public static final int KEYBOARD_SIZE = 256;
/* 225:    */  private static final int BUFFER_SIZE = 50;
/* 226:226 */  private static final String[] keyName = new String[256];
/* 227:227 */  private static final Map<String, Integer> keyMap = new HashMap(253);
/* 228:    */  private static int counter;
/* 229:    */  
/* 230:    */  static
/* 231:    */  {
/* 232:232 */    Field[] fields = Keyboard.class.getFields();
/* 233:    */    try {
/* 234:234 */      for (Field field : fields) {
/* 235:235 */        if ((Modifier.isStatic(field.getModifiers())) && (Modifier.isPublic(field.getModifiers())) && (Modifier.isFinal(field.getModifiers())) && (field.getType().equals(Integer.TYPE)) && (field.getName().startsWith("KEY_")) && (!field.getName().endsWith("WIN")))
/* 236:    */        {
/* 242:242 */          int key = field.getInt(null);
/* 243:243 */          String name = field.getName().substring(4);
/* 244:244 */          keyName[key] = name;
/* 245:245 */          keyMap.put(name, Integer.valueOf(key));
/* 246:246 */          counter += 1;
/* 247:    */        }
/* 248:    */      }
/* 249:    */    }
/* 250:    */    catch (Exception e) {}
/* 251:    */  }
/* 252:    */  
/* 256:256 */  private static final int keyCount = counter;
/* 257:    */  
/* 259:    */  private static boolean created;
/* 260:    */  
/* 262:    */  private static boolean repeat_enabled;
/* 263:    */  
/* 265:265 */  private static final ByteBuffer keyDownBuffer = BufferUtils.createByteBuffer(256);
/* 266:    */  
/* 270:    */  private static ByteBuffer readBuffer;
/* 271:    */  
/* 275:275 */  private static KeyEvent current_event = new KeyEvent(null);
/* 276:    */  
/* 278:278 */  private static KeyEvent tmp_event = new KeyEvent(null);
/* 279:    */  
/* 283:    */  private static boolean initialized;
/* 284:    */  
/* 288:    */  private static InputImplementation implementation;
/* 289:    */  
/* 293:    */  private static void initialize()
/* 294:    */  {
/* 295:295 */    if (initialized)
/* 296:296 */      return;
/* 297:297 */    Sys.initialize();
/* 298:298 */    initialized = true;
/* 299:    */  }
/* 300:    */  
/* 305:    */  private static void create(InputImplementation impl)
/* 306:    */    throws LWJGLException
/* 307:    */  {
/* 308:308 */    if (created)
/* 309:309 */      return;
/* 310:310 */    if (!initialized)
/* 311:311 */      initialize();
/* 312:312 */    implementation = impl;
/* 313:313 */    implementation.createKeyboard();
/* 314:314 */    created = true;
/* 315:315 */    readBuffer = ByteBuffer.allocate(900);
/* 316:316 */    reset();
/* 317:    */  }
/* 318:    */  
/* 323:    */  public static void create()
/* 324:    */    throws LWJGLException
/* 325:    */  {
/* 326:326 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 327:327 */      if (!Display.isCreated()) { throw new IllegalStateException("Display must be created.");
/* 328:    */      }
/* 329:329 */      create(OpenGLPackageAccess.createImplementation());
/* 330:    */    }
/* 331:    */  }
/* 332:    */  
/* 333:    */  private static void reset() {
/* 334:334 */    readBuffer.limit(0);
/* 335:335 */    for (int i = 0; i < keyDownBuffer.remaining(); i++)
/* 336:336 */      keyDownBuffer.put(i, (byte)0);
/* 337:337 */    current_event.reset();
/* 338:    */  }
/* 339:    */  
/* 342:    */  public static boolean isCreated()
/* 343:    */  {
/* 344:344 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 345:345 */      return created;
/* 346:    */    }
/* 347:    */  }
/* 348:    */  
/* 351:    */  public static void destroy()
/* 352:    */  {
/* 353:353 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 354:354 */      if (!created)
/* 355:355 */        return;
/* 356:356 */      created = false;
/* 357:357 */      implementation.destroyKeyboard();
/* 358:358 */      reset();
/* 359:    */    }
/* 360:    */  }
/* 361:    */  
/* 382:    */  public static void poll()
/* 383:    */  {
/* 384:384 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 385:385 */      if (!created)
/* 386:386 */        throw new IllegalStateException("Keyboard must be created before you can poll the device");
/* 387:387 */      implementation.pollKeyboard(keyDownBuffer);
/* 388:388 */      read();
/* 389:    */    }
/* 390:    */  }
/* 391:    */  
/* 392:    */  private static void read() {
/* 393:393 */    readBuffer.compact();
/* 394:394 */    implementation.readKeyboard(readBuffer);
/* 395:395 */    readBuffer.flip();
/* 396:    */  }
/* 397:    */  
/* 402:    */  public static boolean isKeyDown(int key)
/* 403:    */  {
/* 404:404 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 405:405 */      if (!created)
/* 406:406 */        throw new IllegalStateException("Keyboard must be created before you can query key state");
/* 407:407 */      return keyDownBuffer.get(key) != 0;
/* 408:    */    }
/* 409:    */  }
/* 410:    */  
/* 427:    */  public static synchronized String getKeyName(int key)
/* 428:    */  {
/* 429:429 */    return keyName[key];
/* 430:    */  }
/* 431:    */  
/* 435:    */  public static synchronized int getKeyIndex(String keyName)
/* 436:    */  {
/* 437:437 */    Integer ret = (Integer)keyMap.get(keyName);
/* 438:438 */    if (ret == null) {
/* 439:439 */      return 0;
/* 440:    */    }
/* 441:441 */    return ret.intValue();
/* 442:    */  }
/* 443:    */  
/* 447:    */  public static int getNumKeyboardEvents()
/* 448:    */  {
/* 449:449 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 450:450 */      if (!created)
/* 451:451 */        throw new IllegalStateException("Keyboard must be created before you can read events");
/* 452:452 */      int old_position = readBuffer.position();
/* 453:453 */      int num_events = 0;
/* 454:454 */      while ((readNext(tmp_event)) && ((!tmp_event.repeat) || (repeat_enabled)))
/* 455:455 */        num_events++;
/* 456:456 */      readBuffer.position(old_position);
/* 457:457 */      return num_events;
/* 458:    */    }
/* 459:    */  }
/* 460:    */  
/* 471:    */  public static boolean next()
/* 472:    */  {
/* 473:473 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 474:474 */      if (!created) {
/* 475:475 */        throw new IllegalStateException("Keyboard must be created before you can read events");
/* 476:    */      }
/* 477:    */      boolean result;
/* 478:478 */      while (((result = readNext(current_event))) && (current_event.repeat) && (!repeat_enabled)) {}
/* 479:    */      
/* 480:480 */      return result;
/* 481:    */    }
/* 482:    */  }
/* 483:    */  
/* 491:    */  public static void enableRepeatEvents(boolean enable)
/* 492:    */  {
/* 493:493 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 494:494 */      repeat_enabled = enable;
/* 495:    */    }
/* 496:    */  }
/* 497:    */  
/* 503:    */  public static boolean areRepeatEventsEnabled()
/* 504:    */  {
/* 505:505 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 506:506 */      return repeat_enabled;
/* 507:    */    }
/* 508:    */  }
/* 509:    */  
/* 510:    */  private static boolean readNext(KeyEvent event) {
/* 511:511 */    if (readBuffer.hasRemaining()) {
/* 512:512 */      event.key = (readBuffer.getInt() & 0xFF);
/* 513:513 */      event.state = (readBuffer.get() != 0);
/* 514:514 */      event.character = readBuffer.getInt();
/* 515:515 */      event.nanos = readBuffer.getLong();
/* 516:516 */      event.repeat = (readBuffer.get() == 1);
/* 517:517 */      return true;
/* 518:    */    }
/* 519:519 */    return false;
/* 520:    */  }
/* 521:    */  
/* 524:    */  public static int getKeyCount()
/* 525:    */  {
/* 526:526 */    return keyCount;
/* 527:    */  }
/* 528:    */  
/* 531:    */  public static char getEventCharacter()
/* 532:    */  {
/* 533:533 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 534:534 */      return (char)current_event.character;
/* 535:    */    }
/* 536:    */  }
/* 537:    */  
/* 544:    */  public static int getEventKey()
/* 545:    */  {
/* 546:546 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 547:547 */      return current_event.key;
/* 548:    */    }
/* 549:    */  }
/* 550:    */  
/* 556:    */  public static boolean getEventKeyState()
/* 557:    */  {
/* 558:558 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 559:559 */      return current_event.state;
/* 560:    */    }
/* 561:    */  }
/* 562:    */  
/* 569:    */  public static long getEventNanoseconds()
/* 570:    */  {
/* 571:571 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 572:572 */      return current_event.nanos;
/* 573:    */    }
/* 574:    */  }
/* 575:    */  
/* 580:    */  public static boolean isRepeatEvent()
/* 581:    */  {
/* 582:582 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 583:583 */      return current_event.repeat;
/* 584:    */    }
/* 585:    */  }
/* 586:    */  
/* 589:    */  private static final class KeyEvent
/* 590:    */  {
/* 591:    */    private int character;
/* 592:    */    
/* 593:    */    private int key;
/* 594:    */    
/* 595:    */    private boolean state;
/* 596:    */    
/* 597:    */    private long nanos;
/* 598:    */    
/* 599:    */    private boolean repeat;
/* 600:    */    
/* 602:    */    private void reset()
/* 603:    */    {
/* 604:604 */      this.character = 0;
/* 605:605 */      this.key = 0;
/* 606:606 */      this.state = false;
/* 607:607 */      this.repeat = false;
/* 608:    */    }
/* 609:    */  }
/* 610:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.input.Keyboard
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */