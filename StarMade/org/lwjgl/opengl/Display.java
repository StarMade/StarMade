/*    1:     */package org.lwjgl.opengl;
/*    2:     */
/*    3:     */import java.awt.Canvas;
/*    4:     */import java.awt.event.ComponentAdapter;
/*    5:     */import java.awt.event.ComponentEvent;
/*    6:     */import java.awt.event.ComponentListener;
/*    7:     */import java.nio.ByteBuffer;
/*    8:     */import java.nio.FloatBuffer;
/*    9:     */import java.security.AccessController;
/*   10:     */import java.security.PrivilegedAction;
/*   11:     */import java.util.Arrays;
/*   12:     */import java.util.HashSet;
/*   13:     */import org.lwjgl.BufferUtils;
/*   14:     */import org.lwjgl.LWJGLException;
/*   15:     */import org.lwjgl.LWJGLUtil;
/*   16:     */import org.lwjgl.Sys;
/*   17:     */import org.lwjgl.input.Controllers;
/*   18:     */import org.lwjgl.input.Keyboard;
/*   19:     */import org.lwjgl.input.Mouse;
/*   20:     */
/*   65:     */public final class Display
/*   66:     */{
/*   67:  67 */  private static final Thread shutdown_hook = new Thread()
/*   68:     */  {
/*   69:     */    public void run() {}
/*   70:     */  };
/*   71:     */  
/*   74:     */  private static final DisplayImplementation display_impl;
/*   75:     */  
/*   77:     */  private static final DisplayMode initial_mode;
/*   78:     */  
/*   80:     */  private static Canvas parent;
/*   81:     */  
/*   83:     */  private static DisplayMode current_mode;
/*   84:     */  
/*   86:  86 */  private static int x = -1;
/*   87:     */  
/*   91:     */  private static ByteBuffer[] cached_icons;
/*   92:     */  
/*   95:  95 */  private static int y = -1;
/*   96:     */  
/*   98:  98 */  private static int width = 0;
/*   99:     */  
/*  101: 101 */  private static int height = 0;
/*  102:     */  
/*  104: 104 */  private static String title = "Game";
/*  105:     */  
/*  106:     */  private static boolean fullscreen;
/*  107:     */  
/*  108:     */  private static int swap_interval;
/*  109:     */  
/*  110:     */  private static DrawableLWJGL drawable;
/*  111:     */  
/*  112:     */  private static boolean window_created;
/*  113:     */  
/*  114:     */  private static boolean parent_resized;
/*  115:     */  
/*  116:     */  private static boolean window_resized;
/*  117:     */  
/*  118:     */  private static boolean window_resizable;
/*  119:     */  
/*  120:     */  private static float r;
/*  121:     */  
/*  122:     */  private static float g;
/*  123:     */  
/*  124:     */  private static float b;
/*  125:     */  
/*  126: 126 */  private static final ComponentListener component_listener = new ComponentAdapter() {
/*  127:     */    public void componentResized(ComponentEvent e) {
/*  128: 128 */      synchronized (GlobalLock.lock) {
/*  129: 129 */        Display.access$102(true);
/*  130:     */      }
/*  131:     */    }
/*  132:     */  };
/*  133:     */  
/*  134:     */  static {
/*  135: 135 */    Sys.initialize();
/*  136: 136 */    display_impl = createDisplayImplementation();
/*  137:     */    try {
/*  138: 138 */      current_mode = Display.initial_mode = display_impl.init();
/*  139: 139 */      LWJGLUtil.log("Initial mode: " + initial_mode);
/*  140:     */    } catch (LWJGLException e) {
/*  141: 141 */      throw new RuntimeException(e);
/*  142:     */    }
/*  143:     */  }
/*  144:     */  
/*  149:     */  public static Drawable getDrawable()
/*  150:     */  {
/*  151: 151 */    return drawable;
/*  152:     */  }
/*  153:     */  
/*  154:     */  private static DisplayImplementation createDisplayImplementation() {
/*  155: 155 */    switch () {
/*  156:     */    case 1: 
/*  157: 157 */      return new LinuxDisplay();
/*  158:     */    case 3: 
/*  159: 159 */      return new WindowsDisplay();
/*  160:     */    case 2: 
/*  161: 161 */      return new MacOSXDisplay();
/*  162:     */    }
/*  163: 163 */    throw new IllegalStateException("Unsupported platform");
/*  164:     */  }
/*  165:     */  
/*  184:     */  public static DisplayMode[] getAvailableDisplayModes()
/*  185:     */    throws LWJGLException
/*  186:     */  {
/*  187: 187 */    synchronized (GlobalLock.lock) {
/*  188: 188 */      DisplayMode[] unfilteredModes = display_impl.getAvailableDisplayModes();
/*  189:     */      
/*  190: 190 */      if (unfilteredModes == null) {
/*  191: 191 */        return new DisplayMode[0];
/*  192:     */      }
/*  193:     */      
/*  195: 195 */      HashSet<DisplayMode> modes = new HashSet(unfilteredModes.length);
/*  196:     */      
/*  197: 197 */      modes.addAll(Arrays.asList(unfilteredModes));
/*  198: 198 */      DisplayMode[] filteredModes = new DisplayMode[modes.size()];
/*  199: 199 */      modes.toArray(filteredModes);
/*  200:     */      
/*  201: 201 */      LWJGLUtil.log("Removed " + (unfilteredModes.length - filteredModes.length) + " duplicate displaymodes");
/*  202:     */      
/*  203: 203 */      return filteredModes;
/*  204:     */    }
/*  205:     */  }
/*  206:     */  
/*  211:     */  public static DisplayMode getDesktopDisplayMode()
/*  212:     */  {
/*  213: 213 */    return initial_mode;
/*  214:     */  }
/*  215:     */  
/*  220:     */  public static DisplayMode getDisplayMode()
/*  221:     */  {
/*  222: 222 */    return current_mode;
/*  223:     */  }
/*  224:     */  
/*  234:     */  public static void setDisplayMode(DisplayMode mode)
/*  235:     */    throws LWJGLException
/*  236:     */  {
/*  237: 237 */    synchronized (GlobalLock.lock) {
/*  238: 238 */      if (mode == null)
/*  239: 239 */        throw new NullPointerException("mode must be non-null");
/*  240: 240 */      boolean was_fullscreen = isFullscreen();
/*  241: 241 */      current_mode = mode;
/*  242: 242 */      if (isCreated()) {
/*  243: 243 */        destroyWindow();
/*  244:     */        try
/*  245:     */        {
/*  246: 246 */          if ((was_fullscreen) && (!isFullscreen())) {
/*  247: 247 */            display_impl.resetDisplayMode();
/*  248: 248 */          } else if (isFullscreen())
/*  249: 249 */            switchDisplayMode();
/*  250: 250 */          createWindow();
/*  251: 251 */          makeCurrentAndSetSwapInterval();
/*  252:     */        } catch (LWJGLException e) {
/*  253: 253 */          drawable.destroy();
/*  254: 254 */          display_impl.resetDisplayMode();
/*  255: 255 */          throw e;
/*  256:     */        }
/*  257:     */      }
/*  258:     */    }
/*  259:     */  }
/*  260:     */  
/*  261:     */  private static DisplayMode getEffectiveMode() {
/*  262: 262 */    return (!isFullscreen()) && (parent != null) ? new DisplayMode(parent.getWidth(), parent.getHeight()) : current_mode;
/*  263:     */  }
/*  264:     */  
/*  265:     */  private static int getWindowX() {
/*  266: 266 */    if ((!isFullscreen()) && (parent == null))
/*  267:     */    {
/*  268: 268 */      if (x == -1) {
/*  269: 269 */        return Math.max(0, (initial_mode.getWidth() - current_mode.getWidth()) / 2);
/*  270:     */      }
/*  271: 271 */      return x;
/*  272:     */    }
/*  273:     */    
/*  274: 274 */    return 0;
/*  275:     */  }
/*  276:     */  
/*  277:     */  private static int getWindowY()
/*  278:     */  {
/*  279: 279 */    if ((!isFullscreen()) && (parent == null))
/*  280:     */    {
/*  281: 281 */      if (y == -1) {
/*  282: 282 */        return Math.max(0, (initial_mode.getHeight() - current_mode.getHeight()) / 2);
/*  283:     */      }
/*  284: 284 */      return y;
/*  285:     */    }
/*  286:     */    
/*  287: 287 */    return 0;
/*  288:     */  }
/*  289:     */  
/*  293:     */  private static void createWindow()
/*  294:     */    throws LWJGLException
/*  295:     */  {
/*  296: 296 */    if (window_created) {
/*  297: 297 */      return;
/*  298:     */    }
/*  299: 299 */    Canvas tmp_parent = isFullscreen() ? null : parent;
/*  300: 300 */    if ((tmp_parent != null) && (!tmp_parent.isDisplayable()))
/*  301: 301 */      throw new LWJGLException("Parent.isDisplayable() must be true");
/*  302: 302 */    if (tmp_parent != null) {
/*  303: 303 */      tmp_parent.addComponentListener(component_listener);
/*  304:     */    }
/*  305: 305 */    DisplayMode mode = getEffectiveMode();
/*  306: 306 */    display_impl.createWindow(drawable, mode, tmp_parent, getWindowX(), getWindowY());
/*  307: 307 */    window_created = true;
/*  308:     */    
/*  309: 309 */    width = getDisplayMode().getWidth();
/*  310: 310 */    height = getDisplayMode().getHeight();
/*  311:     */    
/*  312: 312 */    setTitle(title);
/*  313: 313 */    initControls();
/*  314:     */    
/*  316: 316 */    if (cached_icons != null) {
/*  317: 317 */      setIcon(cached_icons);
/*  318:     */    } else {
/*  319: 319 */      setIcon(new ByteBuffer[] { LWJGLUtil.LWJGLIcon32x32, LWJGLUtil.LWJGLIcon16x16 });
/*  320:     */    }
/*  321:     */  }
/*  322:     */  
/*  323:     */  private static void releaseDrawable() {
/*  324:     */    try {
/*  325: 325 */      Context context = drawable.getContext();
/*  326: 326 */      if ((context != null) && (context.isCurrent())) {
/*  327: 327 */        context.releaseCurrent();
/*  328: 328 */        context.releaseDrawable();
/*  329:     */      }
/*  330:     */    } catch (LWJGLException e) {
/*  331: 331 */      LWJGLUtil.log("Exception occurred while trying to release context: " + e);
/*  332:     */    }
/*  333:     */  }
/*  334:     */  
/*  335:     */  private static void destroyWindow() {
/*  336: 336 */    if (!window_created) {
/*  337: 337 */      return;
/*  338:     */    }
/*  339: 339 */    if (parent != null) {
/*  340: 340 */      parent.removeComponentListener(component_listener);
/*  341:     */    }
/*  342: 342 */    releaseDrawable();
/*  343:     */    
/*  345: 345 */    if (Mouse.isCreated()) {
/*  346: 346 */      Mouse.destroy();
/*  347:     */    }
/*  348: 348 */    if (Keyboard.isCreated()) {
/*  349: 349 */      Keyboard.destroy();
/*  350:     */    }
/*  351: 351 */    display_impl.destroyWindow();
/*  352: 352 */    window_created = false;
/*  353:     */  }
/*  354:     */  
/*  355:     */  private static void switchDisplayMode() throws LWJGLException {
/*  356: 356 */    if (!current_mode.isFullscreenCapable()) {
/*  357: 357 */      throw new IllegalStateException("Only modes acquired from getAvailableDisplayModes() can be used for fullscreen display");
/*  358:     */    }
/*  359: 359 */    display_impl.switchDisplayMode(current_mode);
/*  360:     */  }
/*  361:     */  
/*  368:     */  public static void setDisplayConfiguration(float gamma, float brightness, float contrast)
/*  369:     */    throws LWJGLException
/*  370:     */  {
/*  371: 371 */    synchronized (GlobalLock.lock) {
/*  372: 372 */      if (!isCreated()) {
/*  373: 373 */        throw new LWJGLException("Display not yet created.");
/*  374:     */      }
/*  375: 375 */      if ((brightness < -1.0F) || (brightness > 1.0F))
/*  376: 376 */        throw new IllegalArgumentException("Invalid brightness value");
/*  377: 377 */      if (contrast < 0.0F)
/*  378: 378 */        throw new IllegalArgumentException("Invalid contrast value");
/*  379: 379 */      int rampSize = display_impl.getGammaRampLength();
/*  380: 380 */      if (rampSize == 0) {
/*  381: 381 */        throw new LWJGLException("Display configuration not supported");
/*  382:     */      }
/*  383: 383 */      FloatBuffer gammaRamp = BufferUtils.createFloatBuffer(rampSize);
/*  384: 384 */      for (int i = 0; i < rampSize; i++) {
/*  385: 385 */        float intensity = i / (rampSize - 1);
/*  386:     */        
/*  387: 387 */        float rampEntry = (float)Math.pow(intensity, gamma);
/*  388:     */        
/*  389: 389 */        rampEntry += brightness;
/*  390:     */        
/*  391: 391 */        rampEntry = (rampEntry - 0.5F) * contrast + 0.5F;
/*  392:     */        
/*  393: 393 */        if (rampEntry > 1.0F) {
/*  394: 394 */          rampEntry = 1.0F;
/*  395: 395 */        } else if (rampEntry < 0.0F)
/*  396: 396 */          rampEntry = 0.0F;
/*  397: 397 */        gammaRamp.put(i, rampEntry);
/*  398:     */      }
/*  399: 399 */      display_impl.setGammaRamp(gammaRamp);
/*  400: 400 */      LWJGLUtil.log("Gamma set, gamma = " + gamma + ", brightness = " + brightness + ", contrast = " + contrast);
/*  401:     */    }
/*  402:     */  }
/*  403:     */  
/*  409:     */  public static void sync(int fps)
/*  410:     */  {
/*  411: 411 */    Sync.sync(fps);
/*  412:     */  }
/*  413:     */  
/*  414:     */  public static String getTitle()
/*  415:     */  {
/*  416: 416 */    synchronized (GlobalLock.lock) {
/*  417: 417 */      return title;
/*  418:     */    }
/*  419:     */  }
/*  420:     */  
/*  421:     */  public static Canvas getParent()
/*  422:     */  {
/*  423: 423 */    synchronized (GlobalLock.lock) {
/*  424: 424 */      return parent;
/*  425:     */    }
/*  426:     */  }
/*  427:     */  
/*  436:     */  public static void setParent(Canvas parent)
/*  437:     */    throws LWJGLException
/*  438:     */  {
/*  439: 439 */    synchronized (GlobalLock.lock) {
/*  440: 440 */      if (parent != parent) {
/*  441: 441 */        parent = parent;
/*  442: 442 */        if (!isCreated())
/*  443: 443 */          return;
/*  444: 444 */        destroyWindow();
/*  445:     */        try {
/*  446: 446 */          if (isFullscreen()) {
/*  447: 447 */            switchDisplayMode();
/*  448:     */          } else {
/*  449: 449 */            display_impl.resetDisplayMode();
/*  450:     */          }
/*  451: 451 */          createWindow();
/*  452: 452 */          makeCurrentAndSetSwapInterval();
/*  453:     */        } catch (LWJGLException e) {
/*  454: 454 */          drawable.destroy();
/*  455: 455 */          display_impl.resetDisplayMode();
/*  456: 456 */          throw e;
/*  457:     */        }
/*  458:     */      }
/*  459:     */    }
/*  460:     */  }
/*  461:     */  
/*  472:     */  public static void setFullscreen(boolean fullscreen)
/*  473:     */    throws LWJGLException
/*  474:     */  {
/*  475: 475 */    setDisplayModeAndFullscreenInternal(fullscreen, current_mode);
/*  476:     */  }
/*  477:     */  
/*  487:     */  public static void setDisplayModeAndFullscreen(DisplayMode mode)
/*  488:     */    throws LWJGLException
/*  489:     */  {
/*  490: 490 */    setDisplayModeAndFullscreenInternal(mode.isFullscreenCapable(), mode);
/*  491:     */  }
/*  492:     */  
/*  493:     */  private static void setDisplayModeAndFullscreenInternal(boolean fullscreen, DisplayMode mode) throws LWJGLException {
/*  494: 494 */    synchronized (GlobalLock.lock) {
/*  495: 495 */      if (mode == null)
/*  496: 496 */        throw new NullPointerException("mode must be non-null");
/*  497: 497 */      DisplayMode old_mode = current_mode;
/*  498: 498 */      current_mode = mode;
/*  499: 499 */      boolean was_fullscreen = isFullscreen();
/*  500: 500 */      fullscreen = fullscreen;
/*  501: 501 */      if ((was_fullscreen != isFullscreen()) || (!mode.equals(old_mode))) {
/*  502: 502 */        if (!isCreated())
/*  503: 503 */          return;
/*  504: 504 */        destroyWindow();
/*  505:     */        try {
/*  506: 506 */          if (isFullscreen()) {
/*  507: 507 */            switchDisplayMode();
/*  508:     */          } else {
/*  509: 509 */            display_impl.resetDisplayMode();
/*  510:     */          }
/*  511: 511 */          createWindow();
/*  512: 512 */          makeCurrentAndSetSwapInterval();
/*  513:     */        } catch (LWJGLException e) {
/*  514: 514 */          drawable.destroy();
/*  515: 515 */          display_impl.resetDisplayMode();
/*  516: 516 */          throw e;
/*  517:     */        }
/*  518:     */      }
/*  519:     */    }
/*  520:     */  }
/*  521:     */  
/*  522:     */  public static boolean isFullscreen()
/*  523:     */  {
/*  524: 524 */    synchronized (GlobalLock.lock) {
/*  525: 525 */      return (fullscreen) && (current_mode.isFullscreenCapable());
/*  526:     */    }
/*  527:     */  }
/*  528:     */  
/*  533:     */  public static void setTitle(String newTitle)
/*  534:     */  {
/*  535: 535 */    synchronized (GlobalLock.lock) {
/*  536: 536 */      if (newTitle == null) {
/*  537: 537 */        newTitle = "";
/*  538:     */      }
/*  539: 539 */      title = newTitle;
/*  540: 540 */      if (isCreated()) {
/*  541: 541 */        display_impl.setTitle(title);
/*  542:     */      }
/*  543:     */    }
/*  544:     */  }
/*  545:     */  
/*  546:     */  public static boolean isCloseRequested() {
/*  547: 547 */    synchronized (GlobalLock.lock) {
/*  548: 548 */      if (!isCreated())
/*  549: 549 */        throw new IllegalStateException("Cannot determine close requested state of uncreated window");
/*  550: 550 */      return display_impl.isCloseRequested();
/*  551:     */    }
/*  552:     */  }
/*  553:     */  
/*  554:     */  public static boolean isVisible()
/*  555:     */  {
/*  556: 556 */    synchronized (GlobalLock.lock) {
/*  557: 557 */      if (!isCreated())
/*  558: 558 */        throw new IllegalStateException("Cannot determine minimized state of uncreated window");
/*  559: 559 */      return display_impl.isVisible();
/*  560:     */    }
/*  561:     */  }
/*  562:     */  
/*  563:     */  public static boolean isActive()
/*  564:     */  {
/*  565: 565 */    synchronized (GlobalLock.lock) {
/*  566: 566 */      if (!isCreated())
/*  567: 567 */        throw new IllegalStateException("Cannot determine focused state of uncreated window");
/*  568: 568 */      return display_impl.isActive();
/*  569:     */    }
/*  570:     */  }
/*  571:     */  
/*  581:     */  public static boolean isDirty()
/*  582:     */  {
/*  583: 583 */    synchronized (GlobalLock.lock) {
/*  584: 584 */      if (!isCreated())
/*  585: 585 */        throw new IllegalStateException("Cannot determine dirty state of uncreated window");
/*  586: 586 */      return display_impl.isDirty();
/*  587:     */    }
/*  588:     */  }
/*  589:     */  
/*  594:     */  public static void processMessages()
/*  595:     */  {
/*  596: 596 */    synchronized (GlobalLock.lock) {
/*  597: 597 */      if (!isCreated()) {
/*  598: 598 */        throw new IllegalStateException("Display not created");
/*  599:     */      }
/*  600: 600 */      display_impl.update();
/*  601:     */    }
/*  602: 602 */    pollDevices();
/*  603:     */  }
/*  604:     */  
/*  609:     */  public static void swapBuffers()
/*  610:     */    throws LWJGLException
/*  611:     */  {
/*  612: 612 */    synchronized (GlobalLock.lock) {
/*  613: 613 */      if (!isCreated()) {
/*  614: 614 */        throw new IllegalStateException("Display not created");
/*  615:     */      }
/*  616: 616 */      if (LWJGLUtil.DEBUG)
/*  617: 617 */        drawable.checkGLError();
/*  618: 618 */      drawable.swapBuffers();
/*  619:     */    }
/*  620:     */  }
/*  621:     */  
/*  626:     */  public static void update()
/*  627:     */  {
/*  628: 628 */    update(true);
/*  629:     */  }
/*  630:     */  
/*  637:     */  public static void update(boolean processMessages)
/*  638:     */  {
/*  639: 639 */    synchronized (GlobalLock.lock) {
/*  640: 640 */      if (!isCreated()) {
/*  641: 641 */        throw new IllegalStateException("Display not created");
/*  642:     */      }
/*  643:     */      
/*  644: 644 */      if ((display_impl.isVisible()) || (display_impl.isDirty())) {
/*  645:     */        try {
/*  646: 646 */          swapBuffers();
/*  647:     */        } catch (LWJGLException e) {
/*  648: 648 */          throw new RuntimeException(e);
/*  649:     */        }
/*  650:     */      }
/*  651:     */      
/*  652: 652 */      window_resized = (!isFullscreen()) && (parent == null) && (display_impl.wasResized());
/*  653:     */      
/*  654: 654 */      if (window_resized) {
/*  655: 655 */        width = display_impl.getWidth();
/*  656: 656 */        height = display_impl.getHeight();
/*  657:     */      }
/*  658:     */      
/*  659: 659 */      if (parent_resized) {
/*  660: 660 */        reshape();
/*  661: 661 */        parent_resized = false;
/*  662: 662 */        window_resized = true;
/*  663:     */      }
/*  664:     */      
/*  665: 665 */      if (processMessages) {
/*  666: 666 */        processMessages();
/*  667:     */      }
/*  668:     */    }
/*  669:     */  }
/*  670:     */  
/*  671:     */  static void pollDevices() {
/*  672: 672 */    if (Mouse.isCreated()) {
/*  673: 673 */      Mouse.poll();
/*  674: 674 */      Mouse.updateCursor();
/*  675:     */    }
/*  676:     */    
/*  677: 677 */    if (Keyboard.isCreated()) {
/*  678: 678 */      Keyboard.poll();
/*  679:     */    }
/*  680:     */    
/*  681: 681 */    if (Controllers.isCreated()) {
/*  682: 682 */      Controllers.poll();
/*  683:     */    }
/*  684:     */  }
/*  685:     */  
/*  689:     */  public static void releaseContext()
/*  690:     */    throws LWJGLException
/*  691:     */  {
/*  692: 692 */    drawable.releaseContext();
/*  693:     */  }
/*  694:     */  
/*  695:     */  public static boolean isCurrent() throws LWJGLException
/*  696:     */  {
/*  697: 697 */    return drawable.isCurrent();
/*  698:     */  }
/*  699:     */  
/*  703:     */  public static void makeCurrent()
/*  704:     */    throws LWJGLException
/*  705:     */  {
/*  706: 706 */    drawable.makeCurrent();
/*  707:     */  }
/*  708:     */  
/*  709:     */  private static void removeShutdownHook() {
/*  710: 710 */    AccessController.doPrivileged(new PrivilegedAction() {
/*  711:     */      public Object run() {
/*  712: 712 */        Runtime.getRuntime().removeShutdownHook(Display.shutdown_hook);
/*  713: 713 */        return null;
/*  714:     */      }
/*  715:     */    });
/*  716:     */  }
/*  717:     */  
/*  718:     */  private static void registerShutdownHook() {
/*  719: 719 */    AccessController.doPrivileged(new PrivilegedAction() {
/*  720:     */      public Object run() {
/*  721: 721 */        Runtime.getRuntime().addShutdownHook(Display.shutdown_hook);
/*  722: 722 */        return null;
/*  723:     */      }
/*  724:     */    });
/*  725:     */  }
/*  726:     */  
/*  736:     */  public static void create()
/*  737:     */    throws LWJGLException
/*  738:     */  {
/*  739: 739 */    create(new PixelFormat());
/*  740:     */  }
/*  741:     */  
/*  753:     */  public static void create(PixelFormat pixel_format)
/*  754:     */    throws LWJGLException
/*  755:     */  {
/*  756: 756 */    synchronized (GlobalLock.lock) {
/*  757: 757 */      create(pixel_format, null, (ContextAttribs)null);
/*  758:     */    }
/*  759:     */  }
/*  760:     */  
/*  773:     */  public static void create(PixelFormat pixel_format, Drawable shared_drawable)
/*  774:     */    throws LWJGLException
/*  775:     */  {
/*  776: 776 */    synchronized (GlobalLock.lock) {
/*  777: 777 */      create(pixel_format, shared_drawable, (ContextAttribs)null);
/*  778:     */    }
/*  779:     */  }
/*  780:     */  
/*  793:     */  public static void create(PixelFormat pixel_format, ContextAttribs attribs)
/*  794:     */    throws LWJGLException
/*  795:     */  {
/*  796: 796 */    synchronized (GlobalLock.lock) {
/*  797: 797 */      create(pixel_format, null, attribs);
/*  798:     */    }
/*  799:     */  }
/*  800:     */  
/*  814:     */  public static void create(PixelFormat pixel_format, Drawable shared_drawable, ContextAttribs attribs)
/*  815:     */    throws LWJGLException
/*  816:     */  {
/*  817: 817 */    synchronized (GlobalLock.lock) {
/*  818: 818 */      if (isCreated())
/*  819: 819 */        throw new IllegalStateException("Only one LWJGL context may be instantiated at any one time.");
/*  820: 820 */      if (pixel_format == null)
/*  821: 821 */        throw new NullPointerException("pixel_format cannot be null");
/*  822: 822 */      removeShutdownHook();
/*  823: 823 */      registerShutdownHook();
/*  824: 824 */      if (isFullscreen()) {
/*  825: 825 */        switchDisplayMode();
/*  826:     */      }
/*  827: 827 */      DrawableGL drawable = new DrawableGL() {
/*  828:     */        public void destroy() {
/*  829: 829 */          synchronized (GlobalLock.lock) {
/*  830: 830 */            if (!Display.isCreated()) {
/*  831: 831 */              return;
/*  832:     */            }
/*  833: 833 */            Display.access$300();
/*  834: 834 */            super.destroy();
/*  835: 835 */            Display.access$400();
/*  836: 836 */            Display.access$502(Display.access$602(-1));
/*  837: 837 */            Display.access$702(null);
/*  838: 838 */            Display.access$000();
/*  839: 839 */            Display.access$800();
/*  840:     */          }
/*  841:     */        }
/*  842: 842 */      };
/*  843: 843 */      drawable = drawable;
/*  844:     */      try
/*  845:     */      {
/*  846: 846 */        drawable.setPixelFormat(pixel_format, attribs);
/*  847:     */        try {
/*  848: 848 */          createWindow();
/*  849:     */          try {
/*  850: 850 */            drawable.context = new ContextGL(drawable.peer_info, attribs, shared_drawable != null ? ((DrawableGL)shared_drawable).getContext() : null);
/*  851:     */            try {
/*  852: 852 */              makeCurrentAndSetSwapInterval();
/*  853: 853 */              initContext();
/*  854:     */            } catch (LWJGLException e) {
/*  855: 855 */              drawable.destroy();
/*  856: 856 */              throw e;
/*  857:     */            }
/*  858:     */          } catch (LWJGLException e) {
/*  859: 859 */            destroyWindow();
/*  860: 860 */            throw e;
/*  861:     */          }
/*  862:     */        } catch (LWJGLException e) {
/*  863: 863 */          drawable.destroy();
/*  864: 864 */          throw e;
/*  865:     */        }
/*  866:     */      } catch (LWJGLException e) {
/*  867: 867 */        display_impl.resetDisplayMode();
/*  868: 868 */        throw e;
/*  869:     */      }
/*  870:     */    }
/*  871:     */  }
/*  872:     */  
/*  885:     */  public static void create(PixelFormatLWJGL pixel_format)
/*  886:     */    throws LWJGLException
/*  887:     */  {
/*  888: 888 */    synchronized (GlobalLock.lock) {
/*  889: 889 */      create(pixel_format, null, null);
/*  890:     */    }
/*  891:     */  }
/*  892:     */  
/*  905:     */  public static void create(PixelFormatLWJGL pixel_format, Drawable shared_drawable)
/*  906:     */    throws LWJGLException
/*  907:     */  {
/*  908: 908 */    synchronized (GlobalLock.lock) {
/*  909: 909 */      create(pixel_format, shared_drawable, null);
/*  910:     */    }
/*  911:     */  }
/*  912:     */  
/*  925:     */  public static void create(PixelFormatLWJGL pixel_format, org.lwjgl.opengles.ContextAttribs attribs)
/*  926:     */    throws LWJGLException
/*  927:     */  {
/*  928: 928 */    synchronized (GlobalLock.lock) {
/*  929: 929 */      create(pixel_format, null, attribs);
/*  930:     */    }
/*  931:     */  }
/*  932:     */  
/*  946:     */  public static void create(PixelFormatLWJGL pixel_format, Drawable shared_drawable, org.lwjgl.opengles.ContextAttribs attribs)
/*  947:     */    throws LWJGLException
/*  948:     */  {
/*  949: 949 */    synchronized (GlobalLock.lock) {
/*  950: 950 */      if (isCreated())
/*  951: 951 */        throw new IllegalStateException("Only one LWJGL context may be instantiated at any one time.");
/*  952: 952 */      if (pixel_format == null)
/*  953: 953 */        throw new NullPointerException("pixel_format cannot be null");
/*  954: 954 */      removeShutdownHook();
/*  955: 955 */      registerShutdownHook();
/*  956: 956 */      if (isFullscreen()) {
/*  957: 957 */        switchDisplayMode();
/*  958:     */      }
/*  959: 959 */      DrawableGLES drawable = new DrawableGLES()
/*  960:     */      {
/*  961:     */        public void setPixelFormat(PixelFormatLWJGL pf, ContextAttribs attribs) throws LWJGLException {
/*  962: 962 */          throw new UnsupportedOperationException();
/*  963:     */        }
/*  964:     */        
/*  965:     */        public void destroy() {
/*  966: 966 */          synchronized (GlobalLock.lock) {
/*  967: 967 */            if (!Display.isCreated()) {
/*  968: 968 */              return;
/*  969:     */            }
/*  970: 970 */            Display.access$300();
/*  971: 971 */            super.destroy();
/*  972: 972 */            Display.access$400();
/*  973: 973 */            Display.access$502(Display.access$602(-1));
/*  974: 974 */            Display.access$702(null);
/*  975: 975 */            Display.access$000();
/*  976: 976 */            Display.access$800();
/*  977:     */          }
/*  978:     */        }
/*  979: 979 */      };
/*  980: 980 */      drawable = drawable;
/*  981:     */      try
/*  982:     */      {
/*  983: 983 */        drawable.setPixelFormat(pixel_format);
/*  984:     */        try {
/*  985: 985 */          createWindow();
/*  986:     */          try {
/*  987: 987 */            drawable.createContext(attribs, shared_drawable);
/*  988:     */            try {
/*  989: 989 */              makeCurrentAndSetSwapInterval();
/*  990: 990 */              initContext();
/*  991:     */            } catch (LWJGLException e) {
/*  992: 992 */              drawable.destroy();
/*  993: 993 */              throw e;
/*  994:     */            }
/*  995:     */          } catch (LWJGLException e) {
/*  996: 996 */            destroyWindow();
/*  997: 997 */            throw e;
/*  998:     */          }
/*  999:     */        } catch (LWJGLException e) {
/* 1000:1000 */          drawable.destroy();
/* 1001:1001 */          throw e;
/* 1002:     */        }
/* 1003:     */      } catch (LWJGLException e) {
/* 1004:1004 */        display_impl.resetDisplayMode();
/* 1005:1005 */        throw e;
/* 1006:     */      }
/* 1007:     */    }
/* 1008:     */  }
/* 1009:     */  
/* 1017:     */  public static void setInitialBackground(float red, float green, float blue)
/* 1018:     */  {
/* 1019:1019 */    r = red;
/* 1020:1020 */    g = green;
/* 1021:1021 */    b = blue;
/* 1022:     */  }
/* 1023:     */  
/* 1024:     */  private static void makeCurrentAndSetSwapInterval() throws LWJGLException {
/* 1025:     */    
/* 1026:     */    try {
/* 1027:1027 */      drawable.checkGLError();
/* 1028:     */    } catch (OpenGLException e) {
/* 1029:1029 */      LWJGLUtil.log("OpenGL error during context creation: " + e.getMessage());
/* 1030:     */    }
/* 1031:1031 */    setSwapInterval(swap_interval);
/* 1032:     */  }
/* 1033:     */  
/* 1034:     */  private static void initContext() {
/* 1035:1035 */    drawable.initContext(r, g, b);
/* 1036:1036 */    update();
/* 1037:     */  }
/* 1038:     */  
/* 1039:     */  static DisplayImplementation getImplementation() {
/* 1040:1040 */    return display_impl;
/* 1041:     */  }
/* 1042:     */  
/* 1043:     */  static boolean getPrivilegedBoolean(String property_name)
/* 1044:     */  {
/* 1045:1045 */    ((Boolean)AccessController.doPrivileged(new PrivilegedAction()) {
/* 1046:     */      public Boolean run() {
/* 1047:1047 */        return Boolean.valueOf(Boolean.getBoolean(this.val$property_name));
/* 1048:     */      }
/* 1049:     */    }()).booleanValue();
/* 1050:     */  }
/* 1051:     */  
/* 1052:     */  private static void initControls()
/* 1053:     */  {
/* 1054:1054 */    if (!getPrivilegedBoolean("org.lwjgl.opengl.Display.noinput")) {
/* 1055:1055 */      if ((!Mouse.isCreated()) && (!getPrivilegedBoolean("org.lwjgl.opengl.Display.nomouse"))) {
/* 1056:     */        try {
/* 1057:1057 */          Mouse.create();
/* 1058:     */        } catch (LWJGLException e) {
/* 1059:1059 */          if (LWJGLUtil.DEBUG) {
/* 1060:1060 */            e.printStackTrace(System.err);
/* 1061:     */          } else {
/* 1062:1062 */            LWJGLUtil.log("Failed to create Mouse: " + e);
/* 1063:     */          }
/* 1064:     */        }
/* 1065:     */      }
/* 1066:1066 */      if ((!Keyboard.isCreated()) && (!getPrivilegedBoolean("org.lwjgl.opengl.Display.nokeyboard"))) {
/* 1067:     */        try {
/* 1068:1068 */          Keyboard.create();
/* 1069:     */        } catch (LWJGLException e) {
/* 1070:1070 */          if (LWJGLUtil.DEBUG) {
/* 1071:1071 */            e.printStackTrace(System.err);
/* 1072:     */          } else {
/* 1073:1073 */            LWJGLUtil.log("Failed to create Keyboard: " + e);
/* 1074:     */          }
/* 1075:     */        }
/* 1076:     */      }
/* 1077:     */    }
/* 1078:     */  }
/* 1079:     */  
/* 1083:     */  public static void destroy()
/* 1084:     */  {
/* 1085:1085 */    if (isCreated()) {
/* 1086:1086 */      drawable.destroy();
/* 1087:     */    }
/* 1088:     */  }
/* 1089:     */  
/* 1094:     */  private static void reset()
/* 1095:     */  {
/* 1096:1096 */    display_impl.resetDisplayMode();
/* 1097:1097 */    current_mode = initial_mode;
/* 1098:     */  }
/* 1099:     */  
/* 1100:     */  public static boolean isCreated()
/* 1101:     */  {
/* 1102:1102 */    synchronized (GlobalLock.lock) {
/* 1103:1103 */      return window_created;
/* 1104:     */    }
/* 1105:     */  }
/* 1106:     */  
/* 1115:     */  public static void setSwapInterval(int value)
/* 1116:     */  {
/* 1117:1117 */    synchronized (GlobalLock.lock) {
/* 1118:1118 */      swap_interval = value;
/* 1119:1119 */      if (isCreated()) {
/* 1120:1120 */        drawable.setSwapInterval(swap_interval);
/* 1121:     */      }
/* 1122:     */    }
/* 1123:     */  }
/* 1124:     */  
/* 1130:     */  public static void setVSyncEnabled(boolean sync)
/* 1131:     */  {
/* 1132:1132 */    synchronized (GlobalLock.lock) {
/* 1133:1133 */      setSwapInterval(sync ? 1 : 0);
/* 1134:     */    }
/* 1135:     */  }
/* 1136:     */  
/* 1146:     */  public static void setLocation(int new_x, int new_y)
/* 1147:     */  {
/* 1148:1148 */    synchronized (GlobalLock.lock)
/* 1149:     */    {
/* 1150:1150 */      x = new_x;
/* 1151:1151 */      y = new_y;
/* 1152:     */      
/* 1154:1154 */      if ((isCreated()) && (!isFullscreen())) {
/* 1155:1155 */        reshape();
/* 1156:     */      }
/* 1157:     */    }
/* 1158:     */  }
/* 1159:     */  
/* 1160:     */  private static void reshape() {
/* 1161:1161 */    DisplayMode mode = getEffectiveMode();
/* 1162:1162 */    display_impl.reshape(getWindowX(), getWindowY(), mode.getWidth(), mode.getHeight());
/* 1163:     */  }
/* 1164:     */  
/* 1170:     */  public static String getAdapter()
/* 1171:     */  {
/* 1172:1172 */    synchronized (GlobalLock.lock) {
/* 1173:1173 */      return display_impl.getAdapter();
/* 1174:     */    }
/* 1175:     */  }
/* 1176:     */  
/* 1182:     */  public static String getVersion()
/* 1183:     */  {
/* 1184:1184 */    synchronized (GlobalLock.lock) {
/* 1185:1185 */      return display_impl.getVersion();
/* 1186:     */    }
/* 1187:     */  }
/* 1188:     */  
/* 1205:     */  public static int setIcon(ByteBuffer[] icons)
/* 1206:     */  {
/* 1207:1207 */    synchronized (GlobalLock.lock)
/* 1208:     */    {
/* 1210:1210 */      if (cached_icons != icons) {
/* 1211:1211 */        cached_icons = new ByteBuffer[icons.length];
/* 1212:1212 */        for (int i = 0; i < icons.length; i++) {
/* 1213:1213 */          cached_icons[i] = BufferUtils.createByteBuffer(icons[i].capacity());
/* 1214:1214 */          int old_position = icons[i].position();
/* 1215:1215 */          cached_icons[i].put(icons[i]);
/* 1216:1216 */          icons[i].position(old_position);
/* 1217:1217 */          cached_icons[i].flip();
/* 1218:     */        }
/* 1219:     */      }
/* 1220:     */      
/* 1221:1221 */      if ((isCreated()) && (parent == null)) {
/* 1222:1222 */        return display_impl.setIcon(cached_icons);
/* 1223:     */      }
/* 1224:1224 */      return 0;
/* 1225:     */    }
/* 1226:     */  }
/* 1227:     */  
/* 1234:     */  public static void setResizable(boolean resizable)
/* 1235:     */  {
/* 1236:1236 */    window_resizable = resizable;
/* 1237:1237 */    if (isCreated()) {
/* 1238:1238 */      display_impl.setResizable(resizable);
/* 1239:     */    }
/* 1240:     */  }
/* 1241:     */  
/* 1244:     */  public static boolean isResizable()
/* 1245:     */  {
/* 1246:1246 */    return window_resizable;
/* 1247:     */  }
/* 1248:     */  
/* 1254:     */  public static boolean wasResized()
/* 1255:     */  {
/* 1256:1256 */    return window_resized;
/* 1257:     */  }
/* 1258:     */  
/* 1266:     */  public static int getX()
/* 1267:     */  {
/* 1268:1268 */    if (isFullscreen()) {
/* 1269:1269 */      return 0;
/* 1270:     */    }
/* 1271:     */    
/* 1272:1272 */    if (parent != null) {
/* 1273:1273 */      return parent.getX();
/* 1274:     */    }
/* 1275:     */    
/* 1276:1276 */    return display_impl.getX();
/* 1277:     */  }
/* 1278:     */  
/* 1286:     */  public static int getY()
/* 1287:     */  {
/* 1288:1288 */    if (isFullscreen()) {
/* 1289:1289 */      return 0;
/* 1290:     */    }
/* 1291:     */    
/* 1292:1292 */    if (parent != null) {
/* 1293:1293 */      return parent.getY();
/* 1294:     */    }
/* 1295:     */    
/* 1296:1296 */    return display_impl.getY();
/* 1297:     */  }
/* 1298:     */  
/* 1308:     */  public static int getWidth()
/* 1309:     */  {
/* 1310:1310 */    if (isFullscreen()) {
/* 1311:1311 */      return getDisplayMode().getWidth();
/* 1312:     */    }
/* 1313:     */    
/* 1314:1314 */    if (parent != null) {
/* 1315:1315 */      return parent.getWidth();
/* 1316:     */    }
/* 1317:     */    
/* 1318:1318 */    return width;
/* 1319:     */  }
/* 1320:     */  
/* 1330:     */  public static int getHeight()
/* 1331:     */  {
/* 1332:1332 */    if (isFullscreen()) {
/* 1333:1333 */      return getDisplayMode().getHeight();
/* 1334:     */    }
/* 1335:     */    
/* 1336:1336 */    if (parent != null) {
/* 1337:1337 */      return parent.getHeight();
/* 1338:     */    }
/* 1339:     */    
/* 1340:1340 */    return height;
/* 1341:     */  }
/* 1342:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.Display
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */