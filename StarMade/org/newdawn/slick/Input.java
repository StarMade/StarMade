/*    1:     */package org.newdawn.slick;
/*    2:     */
/*    3:     */import java.io.IOException;
/*    4:     */import java.io.OutputStream;
/*    5:     */import java.util.ArrayList;
/*    6:     */import java.util.Arrays;
/*    7:     */import java.util.HashSet;
/*    8:     */import java.util.Iterator;
/*    9:     */import org.lwjgl.LWJGLException;
/*   10:     */import org.lwjgl.input.Controller;
/*   11:     */import org.lwjgl.input.Controllers;
/*   12:     */import org.lwjgl.input.Keyboard;
/*   13:     */import org.lwjgl.input.Mouse;
/*   14:     */import org.lwjgl.opengl.Display;
/*   15:     */import org.newdawn.slick.util.Log;
/*   16:     */
/*  174:     */public class Input
/*  175:     */{
/*  176:     */  public static final int ANY_CONTROLLER = -1;
/*  177:     */  private static final int MAX_BUTTONS = 100;
/*  178:     */  public static final int KEY_ESCAPE = 1;
/*  179:     */  public static final int KEY_1 = 2;
/*  180:     */  public static final int KEY_2 = 3;
/*  181:     */  public static final int KEY_3 = 4;
/*  182:     */  public static final int KEY_4 = 5;
/*  183:     */  public static final int KEY_5 = 6;
/*  184:     */  public static final int KEY_6 = 7;
/*  185:     */  public static final int KEY_7 = 8;
/*  186:     */  public static final int KEY_8 = 9;
/*  187:     */  public static final int KEY_9 = 10;
/*  188:     */  public static final int KEY_0 = 11;
/*  189:     */  public static final int KEY_MINUS = 12;
/*  190:     */  public static final int KEY_EQUALS = 13;
/*  191:     */  public static final int KEY_BACK = 14;
/*  192:     */  public static final int KEY_TAB = 15;
/*  193:     */  public static final int KEY_Q = 16;
/*  194:     */  public static final int KEY_W = 17;
/*  195:     */  public static final int KEY_E = 18;
/*  196:     */  public static final int KEY_R = 19;
/*  197:     */  public static final int KEY_T = 20;
/*  198:     */  public static final int KEY_Y = 21;
/*  199:     */  public static final int KEY_U = 22;
/*  200:     */  public static final int KEY_I = 23;
/*  201:     */  public static final int KEY_O = 24;
/*  202:     */  public static final int KEY_P = 25;
/*  203:     */  public static final int KEY_LBRACKET = 26;
/*  204:     */  public static final int KEY_RBRACKET = 27;
/*  205:     */  public static final int KEY_RETURN = 28;
/*  206:     */  public static final int KEY_ENTER = 28;
/*  207:     */  public static final int KEY_LCONTROL = 29;
/*  208:     */  public static final int KEY_A = 30;
/*  209:     */  public static final int KEY_S = 31;
/*  210:     */  public static final int KEY_D = 32;
/*  211:     */  public static final int KEY_F = 33;
/*  212:     */  public static final int KEY_G = 34;
/*  213:     */  public static final int KEY_H = 35;
/*  214:     */  public static final int KEY_J = 36;
/*  215:     */  public static final int KEY_K = 37;
/*  216:     */  public static final int KEY_L = 38;
/*  217:     */  public static final int KEY_SEMICOLON = 39;
/*  218:     */  public static final int KEY_APOSTROPHE = 40;
/*  219:     */  public static final int KEY_GRAVE = 41;
/*  220:     */  public static final int KEY_LSHIFT = 42;
/*  221:     */  public static final int KEY_BACKSLASH = 43;
/*  222:     */  public static final int KEY_Z = 44;
/*  223:     */  public static final int KEY_X = 45;
/*  224:     */  public static final int KEY_C = 46;
/*  225:     */  public static final int KEY_V = 47;
/*  226:     */  public static final int KEY_B = 48;
/*  227:     */  public static final int KEY_N = 49;
/*  228:     */  public static final int KEY_M = 50;
/*  229:     */  public static final int KEY_COMMA = 51;
/*  230:     */  public static final int KEY_PERIOD = 52;
/*  231:     */  public static final int KEY_SLASH = 53;
/*  232:     */  public static final int KEY_RSHIFT = 54;
/*  233:     */  public static final int KEY_MULTIPLY = 55;
/*  234:     */  public static final int KEY_LMENU = 56;
/*  235:     */  public static final int KEY_SPACE = 57;
/*  236:     */  public static final int KEY_CAPITAL = 58;
/*  237:     */  public static final int KEY_F1 = 59;
/*  238:     */  public static final int KEY_F2 = 60;
/*  239:     */  public static final int KEY_F3 = 61;
/*  240:     */  public static final int KEY_F4 = 62;
/*  241:     */  public static final int KEY_F5 = 63;
/*  242:     */  public static final int KEY_F6 = 64;
/*  243:     */  public static final int KEY_F7 = 65;
/*  244:     */  public static final int KEY_F8 = 66;
/*  245:     */  public static final int KEY_F9 = 67;
/*  246:     */  public static final int KEY_F10 = 68;
/*  247:     */  public static final int KEY_NUMLOCK = 69;
/*  248:     */  public static final int KEY_SCROLL = 70;
/*  249:     */  public static final int KEY_NUMPAD7 = 71;
/*  250:     */  public static final int KEY_NUMPAD8 = 72;
/*  251:     */  public static final int KEY_NUMPAD9 = 73;
/*  252:     */  public static final int KEY_SUBTRACT = 74;
/*  253:     */  public static final int KEY_NUMPAD4 = 75;
/*  254:     */  public static final int KEY_NUMPAD5 = 76;
/*  255:     */  public static final int KEY_NUMPAD6 = 77;
/*  256:     */  public static final int KEY_ADD = 78;
/*  257:     */  public static final int KEY_NUMPAD1 = 79;
/*  258:     */  public static final int KEY_NUMPAD2 = 80;
/*  259:     */  public static final int KEY_NUMPAD3 = 81;
/*  260:     */  public static final int KEY_NUMPAD0 = 82;
/*  261:     */  public static final int KEY_DECIMAL = 83;
/*  262:     */  public static final int KEY_F11 = 87;
/*  263:     */  public static final int KEY_F12 = 88;
/*  264:     */  public static final int KEY_F13 = 100;
/*  265:     */  public static final int KEY_F14 = 101;
/*  266:     */  public static final int KEY_F15 = 102;
/*  267:     */  public static final int KEY_KANA = 112;
/*  268:     */  public static final int KEY_CONVERT = 121;
/*  269:     */  public static final int KEY_NOCONVERT = 123;
/*  270:     */  public static final int KEY_YEN = 125;
/*  271:     */  public static final int KEY_NUMPADEQUALS = 141;
/*  272:     */  public static final int KEY_CIRCUMFLEX = 144;
/*  273:     */  public static final int KEY_AT = 145;
/*  274:     */  public static final int KEY_COLON = 146;
/*  275:     */  public static final int KEY_UNDERLINE = 147;
/*  276:     */  public static final int KEY_KANJI = 148;
/*  277:     */  public static final int KEY_STOP = 149;
/*  278:     */  public static final int KEY_AX = 150;
/*  279:     */  public static final int KEY_UNLABELED = 151;
/*  280:     */  public static final int KEY_NUMPADENTER = 156;
/*  281:     */  public static final int KEY_RCONTROL = 157;
/*  282:     */  public static final int KEY_NUMPADCOMMA = 179;
/*  283:     */  public static final int KEY_DIVIDE = 181;
/*  284:     */  public static final int KEY_SYSRQ = 183;
/*  285:     */  public static final int KEY_RMENU = 184;
/*  286:     */  public static final int KEY_PAUSE = 197;
/*  287:     */  public static final int KEY_HOME = 199;
/*  288:     */  public static final int KEY_UP = 200;
/*  289:     */  public static final int KEY_PRIOR = 201;
/*  290:     */  public static final int KEY_LEFT = 203;
/*  291:     */  public static final int KEY_RIGHT = 205;
/*  292:     */  public static final int KEY_END = 207;
/*  293:     */  public static final int KEY_DOWN = 208;
/*  294:     */  public static final int KEY_NEXT = 209;
/*  295:     */  public static final int KEY_INSERT = 210;
/*  296:     */  public static final int KEY_DELETE = 211;
/*  297:     */  public static final int KEY_LWIN = 219;
/*  298:     */  public static final int KEY_RWIN = 220;
/*  299:     */  public static final int KEY_APPS = 221;
/*  300:     */  public static final int KEY_POWER = 222;
/*  301:     */  public static final int KEY_SLEEP = 223;
/*  302:     */  public static final int KEY_LALT = 56;
/*  303:     */  public static final int KEY_RALT = 184;
/*  304:     */  private static final int LEFT = 0;
/*  305:     */  private static final int RIGHT = 1;
/*  306:     */  private static final int UP = 2;
/*  307:     */  private static final int DOWN = 3;
/*  308:     */  private static final int BUTTON1 = 4;
/*  309:     */  private static final int BUTTON2 = 5;
/*  310:     */  private static final int BUTTON3 = 6;
/*  311:     */  private static final int BUTTON4 = 7;
/*  312:     */  private static final int BUTTON5 = 8;
/*  313:     */  private static final int BUTTON6 = 9;
/*  314:     */  private static final int BUTTON7 = 10;
/*  315:     */  private static final int BUTTON8 = 11;
/*  316:     */  private static final int BUTTON9 = 12;
/*  317:     */  private static final int BUTTON10 = 13;
/*  318:     */  public static final int MOUSE_LEFT_BUTTON = 0;
/*  319:     */  public static final int MOUSE_RIGHT_BUTTON = 1;
/*  320:     */  public static final int MOUSE_MIDDLE_BUTTON = 2;
/*  321: 321 */  private static boolean controllersInited = false;
/*  322:     */  
/*  323: 323 */  private static ArrayList controllers = new ArrayList();
/*  324:     */  
/*  326:     */  private int lastMouseX;
/*  327:     */  
/*  328:     */  private int lastMouseY;
/*  329:     */  
/*  330: 330 */  protected boolean[] mousePressed = new boolean[10];
/*  331:     */  
/*  332: 332 */  private boolean[][] controllerPressed = new boolean[100][100];
/*  333:     */  
/*  335: 335 */  protected char[] keys = new char[1024];
/*  336:     */  
/*  337: 337 */  protected boolean[] pressed = new boolean[1024];
/*  338:     */  
/*  339: 339 */  protected long[] nextRepeat = new long[1024];
/*  340:     */  
/*  342: 342 */  private boolean[][] controls = new boolean[10][110];
/*  343:     */  
/*  344: 344 */  protected boolean consumed = false;
/*  345:     */  
/*  346: 346 */  protected HashSet allListeners = new HashSet();
/*  347:     */  
/*  348: 348 */  protected ArrayList keyListeners = new ArrayList();
/*  349:     */  
/*  350: 350 */  protected ArrayList keyListenersToAdd = new ArrayList();
/*  351:     */  
/*  352: 352 */  protected ArrayList mouseListeners = new ArrayList();
/*  353:     */  
/*  354: 354 */  protected ArrayList mouseListenersToAdd = new ArrayList();
/*  355:     */  
/*  356: 356 */  protected ArrayList controllerListeners = new ArrayList();
/*  357:     */  
/*  359:     */  private int wheel;
/*  360:     */  
/*  361:     */  private int height;
/*  362:     */  
/*  363: 363 */  private boolean displayActive = true;
/*  364:     */  
/*  366:     */  private boolean keyRepeat;
/*  367:     */  
/*  369:     */  private int keyRepeatInitial;
/*  370:     */  
/*  371:     */  private int keyRepeatInterval;
/*  372:     */  
/*  373:     */  private boolean paused;
/*  374:     */  
/*  375: 375 */  private float scaleX = 1.0F;
/*  376:     */  
/*  377: 377 */  private float scaleY = 1.0F;
/*  378:     */  
/*  379: 379 */  private float xoffset = 0.0F;
/*  380:     */  
/*  381: 381 */  private float yoffset = 0.0F;
/*  382:     */  
/*  384: 384 */  private int doubleClickDelay = 250;
/*  385:     */  
/*  386: 386 */  private long doubleClickTimeout = 0L;
/*  387:     */  
/*  389:     */  private int clickX;
/*  390:     */  
/*  392:     */  private int clickY;
/*  393:     */  
/*  394:     */  private int clickButton;
/*  395:     */  
/*  396: 396 */  private int pressedX = -1;
/*  397:     */  
/*  399: 399 */  private int pressedY = -1;
/*  400:     */  
/*  402: 402 */  private int mouseClickTolerance = 5;
/*  403:     */  
/*  407:     */  public static void disableControllers()
/*  408:     */  {
/*  409: 409 */    controllersInited = true;
/*  410:     */  }
/*  411:     */  
/*  416:     */  public Input(int height)
/*  417:     */  {
/*  418: 418 */    init(height);
/*  419:     */  }
/*  420:     */  
/*  427:     */  public void setDoubleClickInterval(int delay)
/*  428:     */  {
/*  429: 429 */    this.doubleClickDelay = delay;
/*  430:     */  }
/*  431:     */  
/*  437:     */  public void setMouseClickTolerance(int mouseClickTolerance)
/*  438:     */  {
/*  439: 439 */    this.mouseClickTolerance = mouseClickTolerance;
/*  440:     */  }
/*  441:     */  
/*  447:     */  public void setScale(float scaleX, float scaleY)
/*  448:     */  {
/*  449: 449 */    this.scaleX = scaleX;
/*  450: 450 */    this.scaleY = scaleY;
/*  451:     */  }
/*  452:     */  
/*  458:     */  public void setOffset(float xoffset, float yoffset)
/*  459:     */  {
/*  460: 460 */    this.xoffset = xoffset;
/*  461: 461 */    this.yoffset = yoffset;
/*  462:     */  }
/*  463:     */  
/*  466:     */  public void resetInputTransform()
/*  467:     */  {
/*  468: 468 */    setOffset(0.0F, 0.0F);
/*  469: 469 */    setScale(1.0F, 1.0F);
/*  470:     */  }
/*  471:     */  
/*  476:     */  public void addListener(InputListener listener)
/*  477:     */  {
/*  478: 478 */    addKeyListener(listener);
/*  479: 479 */    addMouseListener(listener);
/*  480: 480 */    addControllerListener(listener);
/*  481:     */  }
/*  482:     */  
/*  487:     */  public void addKeyListener(KeyListener listener)
/*  488:     */  {
/*  489: 489 */    this.keyListenersToAdd.add(listener);
/*  490:     */  }
/*  491:     */  
/*  496:     */  private void addKeyListenerImpl(KeyListener listener)
/*  497:     */  {
/*  498: 498 */    if (this.keyListeners.contains(listener)) {
/*  499: 499 */      return;
/*  500:     */    }
/*  501: 501 */    this.keyListeners.add(listener);
/*  502: 502 */    this.allListeners.add(listener);
/*  503:     */  }
/*  504:     */  
/*  509:     */  public void addMouseListener(MouseListener listener)
/*  510:     */  {
/*  511: 511 */    this.mouseListenersToAdd.add(listener);
/*  512:     */  }
/*  513:     */  
/*  518:     */  private void addMouseListenerImpl(MouseListener listener)
/*  519:     */  {
/*  520: 520 */    if (this.mouseListeners.contains(listener)) {
/*  521: 521 */      return;
/*  522:     */    }
/*  523: 523 */    this.mouseListeners.add(listener);
/*  524: 524 */    this.allListeners.add(listener);
/*  525:     */  }
/*  526:     */  
/*  531:     */  public void addControllerListener(ControllerListener listener)
/*  532:     */  {
/*  533: 533 */    if (this.controllerListeners.contains(listener)) {
/*  534: 534 */      return;
/*  535:     */    }
/*  536: 536 */    this.controllerListeners.add(listener);
/*  537: 537 */    this.allListeners.add(listener);
/*  538:     */  }
/*  539:     */  
/*  542:     */  public void removeAllListeners()
/*  543:     */  {
/*  544: 544 */    removeAllKeyListeners();
/*  545: 545 */    removeAllMouseListeners();
/*  546: 546 */    removeAllControllerListeners();
/*  547:     */  }
/*  548:     */  
/*  551:     */  public void removeAllKeyListeners()
/*  552:     */  {
/*  553: 553 */    this.allListeners.removeAll(this.keyListeners);
/*  554: 554 */    this.keyListeners.clear();
/*  555:     */  }
/*  556:     */  
/*  559:     */  public void removeAllMouseListeners()
/*  560:     */  {
/*  561: 561 */    this.allListeners.removeAll(this.mouseListeners);
/*  562: 562 */    this.mouseListeners.clear();
/*  563:     */  }
/*  564:     */  
/*  567:     */  public void removeAllControllerListeners()
/*  568:     */  {
/*  569: 569 */    this.allListeners.removeAll(this.controllerListeners);
/*  570: 570 */    this.controllerListeners.clear();
/*  571:     */  }
/*  572:     */  
/*  578:     */  public void addPrimaryListener(InputListener listener)
/*  579:     */  {
/*  580: 580 */    removeListener(listener);
/*  581:     */    
/*  582: 582 */    this.keyListeners.add(0, listener);
/*  583: 583 */    this.mouseListeners.add(0, listener);
/*  584: 584 */    this.controllerListeners.add(0, listener);
/*  585:     */    
/*  586: 586 */    this.allListeners.add(listener);
/*  587:     */  }
/*  588:     */  
/*  593:     */  public void removeListener(InputListener listener)
/*  594:     */  {
/*  595: 595 */    removeKeyListener(listener);
/*  596: 596 */    removeMouseListener(listener);
/*  597: 597 */    removeControllerListener(listener);
/*  598:     */  }
/*  599:     */  
/*  604:     */  public void removeKeyListener(KeyListener listener)
/*  605:     */  {
/*  606: 606 */    this.keyListeners.remove(listener);
/*  607:     */    
/*  608: 608 */    if ((!this.mouseListeners.contains(listener)) && (!this.controllerListeners.contains(listener))) {
/*  609: 609 */      this.allListeners.remove(listener);
/*  610:     */    }
/*  611:     */  }
/*  612:     */  
/*  617:     */  public void removeControllerListener(ControllerListener listener)
/*  618:     */  {
/*  619: 619 */    this.controllerListeners.remove(listener);
/*  620:     */    
/*  621: 621 */    if ((!this.mouseListeners.contains(listener)) && (!this.keyListeners.contains(listener))) {
/*  622: 622 */      this.allListeners.remove(listener);
/*  623:     */    }
/*  624:     */  }
/*  625:     */  
/*  630:     */  public void removeMouseListener(MouseListener listener)
/*  631:     */  {
/*  632: 632 */    this.mouseListeners.remove(listener);
/*  633:     */    
/*  634: 634 */    if ((!this.controllerListeners.contains(listener)) && (!this.keyListeners.contains(listener))) {
/*  635: 635 */      this.allListeners.remove(listener);
/*  636:     */    }
/*  637:     */  }
/*  638:     */  
/*  643:     */  void init(int height)
/*  644:     */  {
/*  645: 645 */    this.height = height;
/*  646: 646 */    this.lastMouseX = getMouseX();
/*  647: 647 */    this.lastMouseY = getMouseY();
/*  648:     */  }
/*  649:     */  
/*  655:     */  public static String getKeyName(int code)
/*  656:     */  {
/*  657: 657 */    return Keyboard.getKeyName(code);
/*  658:     */  }
/*  659:     */  
/*  666:     */  public boolean isKeyPressed(int code)
/*  667:     */  {
/*  668: 668 */    if (this.pressed[code] != 0) {
/*  669: 669 */      this.pressed[code] = false;
/*  670: 670 */      return true;
/*  671:     */    }
/*  672:     */    
/*  673: 673 */    return false;
/*  674:     */  }
/*  675:     */  
/*  681:     */  public boolean isMousePressed(int button)
/*  682:     */  {
/*  683: 683 */    if (this.mousePressed[button] != 0) {
/*  684: 684 */      this.mousePressed[button] = false;
/*  685: 685 */      return true;
/*  686:     */    }
/*  687:     */    
/*  688: 688 */    return false;
/*  689:     */  }
/*  690:     */  
/*  697:     */  public boolean isControlPressed(int button)
/*  698:     */  {
/*  699: 699 */    return isControlPressed(button, 0);
/*  700:     */  }
/*  701:     */  
/*  709:     */  public boolean isControlPressed(int button, int controller)
/*  710:     */  {
/*  711: 711 */    if (this.controllerPressed[controller][button] != 0) {
/*  712: 712 */      this.controllerPressed[controller][button] = 0;
/*  713: 713 */      return true;
/*  714:     */    }
/*  715:     */    
/*  716: 716 */    return false;
/*  717:     */  }
/*  718:     */  
/*  722:     */  public void clearControlPressedRecord()
/*  723:     */  {
/*  724: 724 */    for (int i = 0; i < controllers.size(); i++) {
/*  725: 725 */      Arrays.fill(this.controllerPressed[i], false);
/*  726:     */    }
/*  727:     */  }
/*  728:     */  
/*  733:     */  public void clearKeyPressedRecord()
/*  734:     */  {
/*  735: 735 */    Arrays.fill(this.pressed, false);
/*  736:     */  }
/*  737:     */  
/*  742:     */  public void clearMousePressedRecord()
/*  743:     */  {
/*  744: 744 */    Arrays.fill(this.mousePressed, false);
/*  745:     */  }
/*  746:     */  
/*  752:     */  public boolean isKeyDown(int code)
/*  753:     */  {
/*  754: 754 */    return Keyboard.isKeyDown(code);
/*  755:     */  }
/*  756:     */  
/*  761:     */  public int getAbsoluteMouseX()
/*  762:     */  {
/*  763: 763 */    return Mouse.getX();
/*  764:     */  }
/*  765:     */  
/*  770:     */  public int getAbsoluteMouseY()
/*  771:     */  {
/*  772: 772 */    return this.height - Mouse.getY();
/*  773:     */  }
/*  774:     */  
/*  779:     */  public int getMouseX()
/*  780:     */  {
/*  781: 781 */    return (int)(Mouse.getX() * this.scaleX + this.xoffset);
/*  782:     */  }
/*  783:     */  
/*  788:     */  public int getMouseY()
/*  789:     */  {
/*  790: 790 */    return (int)((this.height - Mouse.getY()) * this.scaleY + this.yoffset);
/*  791:     */  }
/*  792:     */  
/*  798:     */  public boolean isMouseButtonDown(int button)
/*  799:     */  {
/*  800: 800 */    return Mouse.isButtonDown(button);
/*  801:     */  }
/*  802:     */  
/*  807:     */  private boolean anyMouseDown()
/*  808:     */  {
/*  809: 809 */    for (int i = 0; i < 3; i++) {
/*  810: 810 */      if (Mouse.isButtonDown(i)) {
/*  811: 811 */        return true;
/*  812:     */      }
/*  813:     */    }
/*  814:     */    
/*  815: 815 */    return false;
/*  816:     */  }
/*  817:     */  
/*  821:     */  public int getControllerCount()
/*  822:     */  {
/*  823:     */    try
/*  824:     */    {
/*  825: 825 */      initControllers();
/*  826:     */    } catch (SlickException e) {
/*  827: 827 */      throw new RuntimeException("Failed to initialise controllers");
/*  828:     */    }
/*  829:     */    
/*  830: 830 */    return controllers.size();
/*  831:     */  }
/*  832:     */  
/*  838:     */  public int getAxisCount(int controller)
/*  839:     */  {
/*  840: 840 */    return ((Controller)controllers.get(controller)).getAxisCount();
/*  841:     */  }
/*  842:     */  
/*  849:     */  public float getAxisValue(int controller, int axis)
/*  850:     */  {
/*  851: 851 */    return ((Controller)controllers.get(controller)).getAxisValue(axis);
/*  852:     */  }
/*  853:     */  
/*  860:     */  public String getAxisName(int controller, int axis)
/*  861:     */  {
/*  862: 862 */    return ((Controller)controllers.get(controller)).getAxisName(axis);
/*  863:     */  }
/*  864:     */  
/*  870:     */  public boolean isControllerLeft(int controller)
/*  871:     */  {
/*  872: 872 */    if (controller >= getControllerCount()) {
/*  873: 873 */      return false;
/*  874:     */    }
/*  875:     */    
/*  876: 876 */    if (controller == -1) {
/*  877: 877 */      for (int i = 0; i < controllers.size(); i++) {
/*  878: 878 */        if (isControllerLeft(i)) {
/*  879: 879 */          return true;
/*  880:     */        }
/*  881:     */      }
/*  882:     */      
/*  883: 883 */      return false;
/*  884:     */    }
/*  885:     */    
/*  886: 886 */    return (((Controller)controllers.get(controller)).getXAxisValue() < -0.5F) || (((Controller)controllers.get(controller)).getPovX() < -0.5F);
/*  887:     */  }
/*  888:     */  
/*  895:     */  public boolean isControllerRight(int controller)
/*  896:     */  {
/*  897: 897 */    if (controller >= getControllerCount()) {
/*  898: 898 */      return false;
/*  899:     */    }
/*  900:     */    
/*  901: 901 */    if (controller == -1) {
/*  902: 902 */      for (int i = 0; i < controllers.size(); i++) {
/*  903: 903 */        if (isControllerRight(i)) {
/*  904: 904 */          return true;
/*  905:     */        }
/*  906:     */      }
/*  907:     */      
/*  908: 908 */      return false;
/*  909:     */    }
/*  910:     */    
/*  911: 911 */    return (((Controller)controllers.get(controller)).getXAxisValue() > 0.5F) || (((Controller)controllers.get(controller)).getPovX() > 0.5F);
/*  912:     */  }
/*  913:     */  
/*  920:     */  public boolean isControllerUp(int controller)
/*  921:     */  {
/*  922: 922 */    if (controller >= getControllerCount()) {
/*  923: 923 */      return false;
/*  924:     */    }
/*  925:     */    
/*  926: 926 */    if (controller == -1) {
/*  927: 927 */      for (int i = 0; i < controllers.size(); i++) {
/*  928: 928 */        if (isControllerUp(i)) {
/*  929: 929 */          return true;
/*  930:     */        }
/*  931:     */      }
/*  932:     */      
/*  933: 933 */      return false;
/*  934:     */    }
/*  935: 935 */    return (((Controller)controllers.get(controller)).getYAxisValue() < -0.5F) || (((Controller)controllers.get(controller)).getPovY() < -0.5F);
/*  936:     */  }
/*  937:     */  
/*  944:     */  public boolean isControllerDown(int controller)
/*  945:     */  {
/*  946: 946 */    if (controller >= getControllerCount()) {
/*  947: 947 */      return false;
/*  948:     */    }
/*  949:     */    
/*  950: 950 */    if (controller == -1) {
/*  951: 951 */      for (int i = 0; i < controllers.size(); i++) {
/*  952: 952 */        if (isControllerDown(i)) {
/*  953: 953 */          return true;
/*  954:     */        }
/*  955:     */      }
/*  956:     */      
/*  957: 957 */      return false;
/*  958:     */    }
/*  959:     */    
/*  960: 960 */    return (((Controller)controllers.get(controller)).getYAxisValue() > 0.5F) || (((Controller)controllers.get(controller)).getPovY() > 0.5F);
/*  961:     */  }
/*  962:     */  
/*  971:     */  public boolean isButtonPressed(int index, int controller)
/*  972:     */  {
/*  973: 973 */    if (controller >= getControllerCount()) {
/*  974: 974 */      return false;
/*  975:     */    }
/*  976:     */    
/*  977: 977 */    if (controller == -1) {
/*  978: 978 */      for (int i = 0; i < controllers.size(); i++) {
/*  979: 979 */        if (isButtonPressed(index, i)) {
/*  980: 980 */          return true;
/*  981:     */        }
/*  982:     */      }
/*  983:     */      
/*  984: 984 */      return false;
/*  985:     */    }
/*  986:     */    
/*  987: 987 */    return ((Controller)controllers.get(controller)).isButtonPressed(index);
/*  988:     */  }
/*  989:     */  
/*  995:     */  public boolean isButton1Pressed(int controller)
/*  996:     */  {
/*  997: 997 */    return isButtonPressed(0, controller);
/*  998:     */  }
/*  999:     */  
/* 1005:     */  public boolean isButton2Pressed(int controller)
/* 1006:     */  {
/* 1007:1007 */    return isButtonPressed(1, controller);
/* 1008:     */  }
/* 1009:     */  
/* 1015:     */  public boolean isButton3Pressed(int controller)
/* 1016:     */  {
/* 1017:1017 */    return isButtonPressed(2, controller);
/* 1018:     */  }
/* 1019:     */  
/* 1023:     */  public void initControllers()
/* 1024:     */    throws SlickException
/* 1025:     */  {
/* 1026:1026 */    if (controllersInited) {
/* 1027:1027 */      return;
/* 1028:     */    }
/* 1029:     */    
/* 1030:1030 */    controllersInited = true;
/* 1031:     */    try {
/* 1032:1032 */      Controllers.create();
/* 1033:1033 */      int count = Controllers.getControllerCount();
/* 1034:     */      
/* 1035:1035 */      for (int i = 0; i < count; i++) {
/* 1036:1036 */        Controller controller = Controllers.getController(i);
/* 1037:     */        
/* 1038:1038 */        if ((controller.getButtonCount() >= 3) && (controller.getButtonCount() < 100)) {
/* 1039:1039 */          controllers.add(controller);
/* 1040:     */        }
/* 1041:     */      }
/* 1042:     */      
/* 1043:1043 */      Log.info("Found " + controllers.size() + " controllers");
/* 1044:1044 */      for (int i = 0; i < controllers.size(); i++) {
/* 1045:1045 */        Log.info(i + " : " + ((Controller)controllers.get(i)).getName());
/* 1046:     */      }
/* 1047:     */    } catch (LWJGLException e) {
/* 1048:1048 */      if ((e.getCause() instanceof ClassNotFoundException)) {
/* 1049:1049 */        throw new SlickException("Unable to create controller - no jinput found - add jinput.jar to your classpath");
/* 1050:     */      }
/* 1051:1051 */      throw new SlickException("Unable to create controllers");
/* 1052:     */    }
/* 1053:     */    catch (NoClassDefFoundError e) {}
/* 1054:     */  }
/* 1055:     */  
/* 1059:     */  public void consumeEvent()
/* 1060:     */  {
/* 1061:1061 */    this.consumed = true;
/* 1062:     */  }
/* 1063:     */  
/* 1088:     */  private int resolveEventKey(int key, char c)
/* 1089:     */  {
/* 1090:1090 */    if ((c == '=') || (key == 0)) {
/* 1091:1091 */      return 13;
/* 1092:     */    }
/* 1093:     */    
/* 1094:1094 */    return key;
/* 1095:     */  }
/* 1096:     */  
/* 1104:     */  public void considerDoubleClick(int button, int x, int y)
/* 1105:     */  {
/* 1106:1106 */    if (this.doubleClickTimeout == 0L) {
/* 1107:1107 */      this.clickX = x;
/* 1108:1108 */      this.clickY = y;
/* 1109:1109 */      this.clickButton = button;
/* 1110:1110 */      this.doubleClickTimeout = (System.currentTimeMillis() + this.doubleClickDelay);
/* 1111:1111 */      fireMouseClicked(button, x, y, 1);
/* 1112:     */    }
/* 1113:1113 */    else if ((this.clickButton == button) && 
/* 1114:1114 */      (System.currentTimeMillis() < this.doubleClickTimeout)) {
/* 1115:1115 */      fireMouseClicked(button, x, y, 2);
/* 1116:1116 */      this.doubleClickTimeout = 0L;
/* 1117:     */    }
/* 1118:     */  }
/* 1119:     */  
/* 1127:     */  public void poll(int width, int height)
/* 1128:     */  {
/* 1129:1129 */    if (this.paused) {
/* 1130:1130 */      clearControlPressedRecord();
/* 1131:1131 */      clearKeyPressedRecord();
/* 1132:1132 */      clearMousePressedRecord();
/* 1133:     */      
/* 1134:1134 */      while (Keyboard.next()) {}
/* 1135:1135 */      while (Mouse.next()) {}
/* 1136:1136 */      return;
/* 1137:     */    }
/* 1138:     */    
/* 1139:1139 */    if (!Display.isActive()) {
/* 1140:1140 */      clearControlPressedRecord();
/* 1141:1141 */      clearKeyPressedRecord();
/* 1142:1142 */      clearMousePressedRecord();
/* 1143:     */    }
/* 1144:     */    
/* 1146:1146 */    for (int i = 0; i < this.keyListenersToAdd.size(); i++) {
/* 1147:1147 */      addKeyListenerImpl((KeyListener)this.keyListenersToAdd.get(i));
/* 1148:     */    }
/* 1149:1149 */    this.keyListenersToAdd.clear();
/* 1150:1150 */    for (int i = 0; i < this.mouseListenersToAdd.size(); i++) {
/* 1151:1151 */      addMouseListenerImpl((MouseListener)this.mouseListenersToAdd.get(i));
/* 1152:     */    }
/* 1153:1153 */    this.mouseListenersToAdd.clear();
/* 1154:     */    
/* 1155:1155 */    if ((this.doubleClickTimeout != 0L) && 
/* 1156:1156 */      (System.currentTimeMillis() > this.doubleClickTimeout)) {
/* 1157:1157 */      this.doubleClickTimeout = 0L;
/* 1158:     */    }
/* 1159:     */    
/* 1161:1161 */    this.height = height;
/* 1162:     */    
/* 1163:1163 */    Iterator allStarts = this.allListeners.iterator();
/* 1164:1164 */    while (allStarts.hasNext()) {
/* 1165:1165 */      ControlledInputReciever listener = (ControlledInputReciever)allStarts.next();
/* 1166:1166 */      listener.inputStarted();
/* 1167:     */    }
/* 1168:     */    
/* 1169:1169 */    while (Keyboard.next()) {
/* 1170:1170 */      if (Keyboard.getEventKeyState()) {
/* 1171:1171 */        int eventKey = resolveEventKey(Keyboard.getEventKey(), Keyboard.getEventCharacter());
/* 1172:     */        
/* 1173:1173 */        this.keys[eventKey] = Keyboard.getEventCharacter();
/* 1174:1174 */        this.pressed[eventKey] = true;
/* 1175:1175 */        this.nextRepeat[eventKey] = (System.currentTimeMillis() + this.keyRepeatInitial);
/* 1176:     */        
/* 1177:1177 */        this.consumed = false;
/* 1178:1178 */        for (int i = 0; i < this.keyListeners.size(); i++) {
/* 1179:1179 */          KeyListener listener = (KeyListener)this.keyListeners.get(i);
/* 1180:     */          
/* 1181:1181 */          if (listener.isAcceptingInput()) {
/* 1182:1182 */            listener.keyPressed(eventKey, Keyboard.getEventCharacter());
/* 1183:1183 */            if (this.consumed) {
/* 1184:     */              break;
/* 1185:     */            }
/* 1186:     */          }
/* 1187:     */        }
/* 1188:     */      } else {
/* 1189:1189 */        int eventKey = resolveEventKey(Keyboard.getEventKey(), Keyboard.getEventCharacter());
/* 1190:1190 */        this.nextRepeat[eventKey] = 0L;
/* 1191:     */        
/* 1192:1192 */        this.consumed = false;
/* 1193:1193 */        for (int i = 0; i < this.keyListeners.size(); i++) {
/* 1194:1194 */          KeyListener listener = (KeyListener)this.keyListeners.get(i);
/* 1195:1195 */          if (listener.isAcceptingInput()) {
/* 1196:1196 */            listener.keyReleased(eventKey, this.keys[eventKey]);
/* 1197:1197 */            if (this.consumed) {
/* 1198:     */              break;
/* 1199:     */            }
/* 1200:     */          }
/* 1201:     */        }
/* 1202:     */      }
/* 1203:     */    }
/* 1204:     */    
/* 1205:1205 */    while (Mouse.next()) {
/* 1206:1206 */      if (Mouse.getEventButton() >= 0) {
/* 1207:1207 */        if (Mouse.getEventButtonState()) {
/* 1208:1208 */          this.consumed = false;
/* 1209:1209 */          this.mousePressed[Mouse.getEventButton()] = true;
/* 1210:     */          
/* 1211:1211 */          this.pressedX = ((int)(this.xoffset + Mouse.getEventX() * this.scaleX));
/* 1212:1212 */          this.pressedY = ((int)(this.yoffset + (height - Mouse.getEventY()) * this.scaleY));
/* 1213:     */          
/* 1214:1214 */          for (int i = 0; i < this.mouseListeners.size(); i++) {
/* 1215:1215 */            MouseListener listener = (MouseListener)this.mouseListeners.get(i);
/* 1216:1216 */            if (listener.isAcceptingInput()) {
/* 1217:1217 */              listener.mousePressed(Mouse.getEventButton(), this.pressedX, this.pressedY);
/* 1218:1218 */              if (this.consumed) {
/* 1219:     */                break;
/* 1220:     */              }
/* 1221:     */            }
/* 1222:     */          }
/* 1223:     */        } else {
/* 1224:1224 */          this.consumed = false;
/* 1225:1225 */          this.mousePressed[Mouse.getEventButton()] = false;
/* 1226:     */          
/* 1227:1227 */          int releasedX = (int)(this.xoffset + Mouse.getEventX() * this.scaleX);
/* 1228:1228 */          int releasedY = (int)(this.yoffset + (height - Mouse.getEventY()) * this.scaleY);
/* 1229:1229 */          if ((this.pressedX != -1) && (this.pressedY != -1) && (Math.abs(this.pressedX - releasedX) < this.mouseClickTolerance) && (Math.abs(this.pressedY - releasedY) < this.mouseClickTolerance))
/* 1230:     */          {
/* 1233:1233 */            considerDoubleClick(Mouse.getEventButton(), releasedX, releasedY);
/* 1234:1234 */            this.pressedX = (this.pressedY = -1);
/* 1235:     */          }
/* 1236:     */          
/* 1237:1237 */          for (int i = 0; i < this.mouseListeners.size(); i++) {
/* 1238:1238 */            MouseListener listener = (MouseListener)this.mouseListeners.get(i);
/* 1239:1239 */            if (listener.isAcceptingInput()) {
/* 1240:1240 */              listener.mouseReleased(Mouse.getEventButton(), releasedX, releasedY);
/* 1241:1241 */              if (this.consumed) {
/* 1242:     */                break;
/* 1243:     */              }
/* 1244:     */            }
/* 1245:     */          }
/* 1246:     */        }
/* 1247:     */      } else {
/* 1248:1248 */        if ((Mouse.isGrabbed()) && (this.displayActive) && (
/* 1249:1249 */          (Mouse.getEventDX() != 0) || (Mouse.getEventDY() != 0))) {
/* 1250:1250 */          this.consumed = false;
/* 1251:1251 */          for (int i = 0; i < this.mouseListeners.size(); i++) {
/* 1252:1252 */            MouseListener listener = (MouseListener)this.mouseListeners.get(i);
/* 1253:1253 */            if (listener.isAcceptingInput()) {
/* 1254:1254 */              if (anyMouseDown()) {
/* 1255:1255 */                listener.mouseDragged(0, 0, Mouse.getEventDX(), -Mouse.getEventDY());
/* 1256:     */              } else {
/* 1257:1257 */                listener.mouseMoved(0, 0, Mouse.getEventDX(), -Mouse.getEventDY());
/* 1258:     */              }
/* 1259:     */              
/* 1260:1260 */              if (this.consumed) {
/* 1261:     */                break;
/* 1262:     */              }
/* 1263:     */            }
/* 1264:     */          }
/* 1265:     */        }
/* 1266:     */        
/* 1268:1268 */        int dwheel = Mouse.getEventDWheel();
/* 1269:1269 */        this.wheel += dwheel;
/* 1270:1270 */        if (dwheel != 0) {
/* 1271:1271 */          this.consumed = false;
/* 1272:1272 */          for (int i = 0; i < this.mouseListeners.size(); i++) {
/* 1273:1273 */            MouseListener listener = (MouseListener)this.mouseListeners.get(i);
/* 1274:1274 */            if (listener.isAcceptingInput()) {
/* 1275:1275 */              listener.mouseWheelMoved(dwheel);
/* 1276:1276 */              if (this.consumed) {
/* 1277:     */                break;
/* 1278:     */              }
/* 1279:     */            }
/* 1280:     */          }
/* 1281:     */        }
/* 1282:     */      }
/* 1283:     */    }
/* 1284:     */    
/* 1285:1285 */    if ((!this.displayActive) || (Mouse.isGrabbed())) {
/* 1286:1286 */      this.lastMouseX = getMouseX();
/* 1287:1287 */      this.lastMouseY = getMouseY();
/* 1288:     */    }
/* 1289:1289 */    else if ((this.lastMouseX != getMouseX()) || (this.lastMouseY != getMouseY())) {
/* 1290:1290 */      this.consumed = false;
/* 1291:1291 */      for (int i = 0; i < this.mouseListeners.size(); i++) {
/* 1292:1292 */        MouseListener listener = (MouseListener)this.mouseListeners.get(i);
/* 1293:1293 */        if (listener.isAcceptingInput()) {
/* 1294:1294 */          if (anyMouseDown()) {
/* 1295:1295 */            listener.mouseDragged(this.lastMouseX, this.lastMouseY, getMouseX(), getMouseY());
/* 1296:     */          } else {
/* 1297:1297 */            listener.mouseMoved(this.lastMouseX, this.lastMouseY, getMouseX(), getMouseY());
/* 1298:     */          }
/* 1299:1299 */          if (this.consumed) {
/* 1300:     */            break;
/* 1301:     */          }
/* 1302:     */        }
/* 1303:     */      }
/* 1304:1304 */      this.lastMouseX = getMouseX();
/* 1305:1305 */      this.lastMouseY = getMouseY();
/* 1306:     */    }
/* 1307:     */    
/* 1309:1309 */    if (controllersInited) {
/* 1310:1310 */      for (int i = 0; i < getControllerCount(); i++) {
/* 1311:1311 */        int count = ((Controller)controllers.get(i)).getButtonCount() + 3;
/* 1312:1312 */        count = Math.min(count, 24);
/* 1313:1313 */        for (int c = 0; c <= count; c++) {
/* 1314:1314 */          if ((this.controls[i][c] != 0) && (!isControlDwn(c, i))) {
/* 1315:1315 */            this.controls[i][c] = 0;
/* 1316:1316 */            fireControlRelease(c, i);
/* 1317:1317 */          } else if ((this.controls[i][c] == 0) && (isControlDwn(c, i))) {
/* 1318:1318 */            this.controllerPressed[i][c] = 1;
/* 1319:1319 */            this.controls[i][c] = 1;
/* 1320:1320 */            fireControlPress(c, i);
/* 1321:     */          }
/* 1322:     */        }
/* 1323:     */      }
/* 1324:     */    }
/* 1325:     */    
/* 1326:1326 */    if (this.keyRepeat) {
/* 1327:1327 */      for (int i = 0; i < 1024; i++) {
/* 1328:1328 */        if ((this.pressed[i] != 0) && (this.nextRepeat[i] != 0L) && 
/* 1329:1329 */          (System.currentTimeMillis() > this.nextRepeat[i])) {
/* 1330:1330 */          this.nextRepeat[i] = (System.currentTimeMillis() + this.keyRepeatInterval);
/* 1331:1331 */          this.consumed = false;
/* 1332:1332 */          for (int j = 0; j < this.keyListeners.size(); j++) {
/* 1333:1333 */            KeyListener listener = (KeyListener)this.keyListeners.get(j);
/* 1334:     */            
/* 1335:1335 */            if (listener.isAcceptingInput()) {
/* 1336:1336 */              listener.keyPressed(i, this.keys[i]);
/* 1337:1337 */              if (this.consumed) {
/* 1338:     */                break;
/* 1339:     */              }
/* 1340:     */            }
/* 1341:     */          }
/* 1342:     */        }
/* 1343:     */      }
/* 1344:     */    }
/* 1345:     */    
/* 1348:1348 */    Iterator all = this.allListeners.iterator();
/* 1349:1349 */    while (all.hasNext()) {
/* 1350:1350 */      ControlledInputReciever listener = (ControlledInputReciever)all.next();
/* 1351:1351 */      listener.inputEnded();
/* 1352:     */    }
/* 1353:     */    
/* 1354:1354 */    if (Display.isCreated()) {
/* 1355:1355 */      this.displayActive = Display.isActive();
/* 1356:     */    }
/* 1357:     */  }
/* 1358:     */  
/* 1363:     */  /**
/* 1364:     */   * @deprecated
/* 1365:     */   */
/* 1366:     */  public void enableKeyRepeat(int initial, int interval)
/* 1367:     */  {
/* 1368:1368 */    Keyboard.enableRepeatEvents(true);
/* 1369:     */  }
/* 1370:     */  
/* 1374:     */  public void enableKeyRepeat()
/* 1375:     */  {
/* 1376:1376 */    Keyboard.enableRepeatEvents(true);
/* 1377:     */  }
/* 1378:     */  
/* 1381:     */  public void disableKeyRepeat()
/* 1382:     */  {
/* 1383:1383 */    Keyboard.enableRepeatEvents(false);
/* 1384:     */  }
/* 1385:     */  
/* 1390:     */  public boolean isKeyRepeatEnabled()
/* 1391:     */  {
/* 1392:1392 */    return Keyboard.areRepeatEventsEnabled();
/* 1393:     */  }
/* 1394:     */  
/* 1400:     */  private void fireControlPress(int index, int controllerIndex)
/* 1401:     */  {
/* 1402:1402 */    this.consumed = false;
/* 1403:1403 */    for (int i = 0; i < this.controllerListeners.size(); i++) {
/* 1404:1404 */      ControllerListener listener = (ControllerListener)this.controllerListeners.get(i);
/* 1405:1405 */      if (listener.isAcceptingInput()) {
/* 1406:1406 */        switch (index) {
/* 1407:     */        case 0: 
/* 1408:1408 */          listener.controllerLeftPressed(controllerIndex);
/* 1409:1409 */          break;
/* 1410:     */        case 1: 
/* 1411:1411 */          listener.controllerRightPressed(controllerIndex);
/* 1412:1412 */          break;
/* 1413:     */        case 2: 
/* 1414:1414 */          listener.controllerUpPressed(controllerIndex);
/* 1415:1415 */          break;
/* 1416:     */        case 3: 
/* 1417:1417 */          listener.controllerDownPressed(controllerIndex);
/* 1418:1418 */          break;
/* 1419:     */        
/* 1420:     */        default: 
/* 1421:1421 */          listener.controllerButtonPressed(controllerIndex, index - 4 + 1);
/* 1422:     */        }
/* 1423:     */        
/* 1424:1424 */        if (this.consumed) {
/* 1425:     */          break;
/* 1426:     */        }
/* 1427:     */      }
/* 1428:     */    }
/* 1429:     */  }
/* 1430:     */  
/* 1436:     */  private void fireControlRelease(int index, int controllerIndex)
/* 1437:     */  {
/* 1438:1438 */    this.consumed = false;
/* 1439:1439 */    for (int i = 0; i < this.controllerListeners.size(); i++) {
/* 1440:1440 */      ControllerListener listener = (ControllerListener)this.controllerListeners.get(i);
/* 1441:1441 */      if (listener.isAcceptingInput()) {
/* 1442:1442 */        switch (index) {
/* 1443:     */        case 0: 
/* 1444:1444 */          listener.controllerLeftReleased(controllerIndex);
/* 1445:1445 */          break;
/* 1446:     */        case 1: 
/* 1447:1447 */          listener.controllerRightReleased(controllerIndex);
/* 1448:1448 */          break;
/* 1449:     */        case 2: 
/* 1450:1450 */          listener.controllerUpReleased(controllerIndex);
/* 1451:1451 */          break;
/* 1452:     */        case 3: 
/* 1453:1453 */          listener.controllerDownReleased(controllerIndex);
/* 1454:1454 */          break;
/* 1455:     */        
/* 1456:     */        default: 
/* 1457:1457 */          listener.controllerButtonReleased(controllerIndex, index - 4 + 1);
/* 1458:     */        }
/* 1459:     */        
/* 1460:1460 */        if (this.consumed) {
/* 1461:     */          break;
/* 1462:     */        }
/* 1463:     */      }
/* 1464:     */    }
/* 1465:     */  }
/* 1466:     */  
/* 1473:     */  private boolean isControlDwn(int index, int controllerIndex)
/* 1474:     */  {
/* 1475:1475 */    switch (index) {
/* 1476:     */    case 0: 
/* 1477:1477 */      return isControllerLeft(controllerIndex);
/* 1478:     */    case 1: 
/* 1479:1479 */      return isControllerRight(controllerIndex);
/* 1480:     */    case 2: 
/* 1481:1481 */      return isControllerUp(controllerIndex);
/* 1482:     */    case 3: 
/* 1483:1483 */      return isControllerDown(controllerIndex);
/* 1484:     */    }
/* 1485:     */    
/* 1486:1486 */    if (index >= 4) {
/* 1487:1487 */      return isButtonPressed(index - 4, controllerIndex);
/* 1488:     */    }
/* 1489:     */    
/* 1490:1490 */    throw new RuntimeException("Unknown control index");
/* 1491:     */  }
/* 1492:     */  
/* 1496:     */  public void pause()
/* 1497:     */  {
/* 1498:1498 */    this.paused = true;
/* 1499:     */    
/* 1501:1501 */    clearKeyPressedRecord();
/* 1502:1502 */    clearMousePressedRecord();
/* 1503:1503 */    clearControlPressedRecord();
/* 1504:     */  }
/* 1505:     */  
/* 1508:     */  public void resume()
/* 1509:     */  {
/* 1510:1510 */    this.paused = false;
/* 1511:     */  }
/* 1512:     */  
/* 1520:     */  private void fireMouseClicked(int button, int x, int y, int clickCount)
/* 1521:     */  {
/* 1522:1522 */    this.consumed = false;
/* 1523:1523 */    for (int i = 0; i < this.mouseListeners.size(); i++) {
/* 1524:1524 */      MouseListener listener = (MouseListener)this.mouseListeners.get(i);
/* 1525:1525 */      if (listener.isAcceptingInput()) {
/* 1526:1526 */        listener.mouseClicked(button, x, y, clickCount);
/* 1527:1527 */        if (this.consumed) {
/* 1528:     */          break;
/* 1529:     */        }
/* 1530:     */      }
/* 1531:     */    }
/* 1532:     */  }
/* 1533:     */  
/* 1534:     */  private class NullOutputStream
/* 1535:     */    extends OutputStream
/* 1536:     */  {
/* 1537:     */    private NullOutputStream() {}
/* 1538:     */    
/* 1539:     */    public void write(int b)
/* 1540:     */      throws IOException
/* 1541:     */    {}
/* 1542:     */  }
/* 1543:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.Input
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */