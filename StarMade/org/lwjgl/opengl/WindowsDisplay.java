/*    1:     */package org.lwjgl.opengl;
/*    2:     */
/*    3:     */import java.awt.Canvas;
/*    4:     */import java.awt.KeyboardFocusManager;
/*    5:     */import java.lang.reflect.Method;
/*    6:     */import java.nio.ByteBuffer;
/*    7:     */import java.nio.FloatBuffer;
/*    8:     */import java.nio.IntBuffer;
/*    9:     */import javax.swing.SwingUtilities;
/*   10:     */import org.lwjgl.BufferUtils;
/*   11:     */import org.lwjgl.LWJGLException;
/*   12:     */import org.lwjgl.LWJGLUtil;
/*   13:     */import org.lwjgl.MemoryUtil;
/*   14:     */import org.lwjgl.input.Mouse;
/*   15:     */
/*   60:     */final class WindowsDisplay
/*   61:     */  implements DisplayImplementation
/*   62:     */{
/*   63:     */  private static final int GAMMA_LENGTH = 256;
/*   64:     */  private static final int WM_WINDOWPOSCHANGED = 71;
/*   65:     */  private static final int WM_MOVE = 3;
/*   66:     */  private static final int WM_CANCELMODE = 31;
/*   67:     */  private static final int WM_MOUSEMOVE = 512;
/*   68:     */  private static final int WM_LBUTTONDOWN = 513;
/*   69:     */  private static final int WM_LBUTTONUP = 514;
/*   70:     */  private static final int WM_LBUTTONDBLCLK = 515;
/*   71:     */  private static final int WM_RBUTTONDOWN = 516;
/*   72:     */  private static final int WM_RBUTTONUP = 517;
/*   73:     */  private static final int WM_RBUTTONDBLCLK = 518;
/*   74:     */  private static final int WM_MBUTTONDOWN = 519;
/*   75:     */  private static final int WM_MBUTTONUP = 520;
/*   76:     */  private static final int WM_MBUTTONDBLCLK = 521;
/*   77:     */  private static final int WM_XBUTTONDOWN = 523;
/*   78:     */  private static final int WM_XBUTTONUP = 524;
/*   79:     */  private static final int WM_XBUTTONDBLCLK = 525;
/*   80:     */  private static final int WM_MOUSEWHEEL = 522;
/*   81:     */  private static final int WM_CAPTURECHANGED = 533;
/*   82:     */  private static final int WM_MOUSELEAVE = 675;
/*   83:     */  private static final int WM_ENTERSIZEMOVE = 561;
/*   84:     */  private static final int WM_EXITSIZEMOVE = 562;
/*   85:     */  private static final int WM_SIZING = 532;
/*   86:     */  private static final int WM_KEYDOWN = 256;
/*   87:     */  private static final int WM_KEYUP = 257;
/*   88:     */  private static final int WM_SYSKEYUP = 261;
/*   89:     */  private static final int WM_SYSKEYDOWN = 260;
/*   90:     */  private static final int WM_SYSCHAR = 262;
/*   91:     */  private static final int WM_CHAR = 258;
/*   92:     */  private static final int WM_GETICON = 127;
/*   93:     */  private static final int WM_SETICON = 128;
/*   94:     */  private static final int WM_SETCURSOR = 32;
/*   95:     */  private static final int WM_MOUSEACTIVATE = 33;
/*   96:     */  private static final int WM_QUIT = 18;
/*   97:     */  private static final int WM_SYSCOMMAND = 274;
/*   98:     */  private static final int WM_PAINT = 15;
/*   99:     */  private static final int WM_KILLFOCUS = 8;
/*  100:     */  private static final int WM_SETFOCUS = 7;
/*  101:     */  private static final int SC_SIZE = 61440;
/*  102:     */  private static final int SC_MOVE = 61456;
/*  103:     */  private static final int SC_MINIMIZE = 61472;
/*  104:     */  private static final int SC_MAXIMIZE = 61488;
/*  105:     */  private static final int SC_NEXTWINDOW = 61504;
/*  106:     */  private static final int SC_PREVWINDOW = 61520;
/*  107:     */  private static final int SC_CLOSE = 61536;
/*  108:     */  private static final int SC_VSCROLL = 61552;
/*  109:     */  private static final int SC_HSCROLL = 61568;
/*  110:     */  private static final int SC_MOUSEMENU = 61584;
/*  111:     */  private static final int SC_KEYMENU = 61696;
/*  112:     */  private static final int SC_ARRANGE = 61712;
/*  113:     */  private static final int SC_RESTORE = 61728;
/*  114:     */  private static final int SC_TASKLIST = 61744;
/*  115:     */  private static final int SC_SCREENSAVE = 61760;
/*  116:     */  private static final int SC_HOTKEY = 61776;
/*  117:     */  private static final int SC_DEFAULT = 61792;
/*  118:     */  private static final int SC_MONITORPOWER = 61808;
/*  119:     */  private static final int SC_CONTEXTHELP = 61824;
/*  120:     */  private static final int SC_SEPARATOR = 61455;
/*  121:     */  static final int SM_CXCURSOR = 13;
/*  122:     */  static final int SM_CYCURSOR = 14;
/*  123:     */  static final int SM_CMOUSEBUTTONS = 43;
/*  124:     */  static final int SM_MOUSEWHEELPRESENT = 75;
/*  125:     */  private static final int SIZE_RESTORED = 0;
/*  126:     */  private static final int SIZE_MINIMIZED = 1;
/*  127:     */  private static final int SIZE_MAXIMIZED = 2;
/*  128:     */  private static final int WM_SIZE = 5;
/*  129:     */  private static final int WM_ACTIVATE = 6;
/*  130:     */  private static final int WA_INACTIVE = 0;
/*  131:     */  private static final int WA_ACTIVE = 1;
/*  132:     */  private static final int WA_CLICKACTIVE = 2;
/*  133:     */  private static final int SW_NORMAL = 1;
/*  134:     */  private static final int SW_SHOWMINNOACTIVE = 7;
/*  135:     */  private static final int SW_SHOWDEFAULT = 10;
/*  136:     */  private static final int SW_RESTORE = 9;
/*  137:     */  private static final int SW_MAXIMIZE = 3;
/*  138:     */  private static final int ICON_SMALL = 0;
/*  139:     */  private static final int ICON_BIG = 1;
/*  140: 140 */  private static final IntBuffer rect_buffer = BufferUtils.createIntBuffer(4);
/*  141: 141 */  private static final Rect rect = new Rect(null);
/*  142:     */  
/*  143:     */  private static final long HWND_TOP = 0L;
/*  144:     */  
/*  145:     */  private static final long HWND_BOTTOM = 1L;
/*  146:     */  
/*  147:     */  private static final long HWND_TOPMOST = -1L;
/*  148:     */  
/*  149:     */  private static final long HWND_NOTOPMOST = -2L;
/*  150:     */  
/*  151:     */  private static final int SWP_NOSIZE = 1;
/*  152:     */  
/*  153:     */  private static final int SWP_NOMOVE = 2;
/*  154:     */  
/*  155:     */  private static final int SWP_NOZORDER = 4;
/*  156:     */  
/*  157:     */  private static final int SWP_FRAMECHANGED = 32;
/*  158:     */  
/*  159:     */  private static final int GWL_STYLE = -16;
/*  160:     */  
/*  161:     */  private static final int GWL_EXSTYLE = -20;
/*  162:     */  
/*  163:     */  private static final int WS_THICKFRAME = 262144;
/*  164:     */  
/*  165:     */  private static final int WS_MAXIMIZEBOX = 65536;
/*  166:     */  
/*  167:     */  private static final int HTCLIENT = 1;
/*  168:     */  
/*  169:     */  private static final int MK_XBUTTON1 = 32;
/*  170:     */  
/*  171:     */  private static final int MK_XBUTTON2 = 64;
/*  172:     */  private static final int XBUTTON1 = 1;
/*  173:     */  private static final int XBUTTON2 = 2;
/*  174:     */  private static WindowsDisplay current_display;
/*  175:     */  private static boolean cursor_clipped;
/*  176:     */  private WindowsDisplayPeerInfo peer_info;
/*  177:     */  private Object current_cursor;
/*  178:     */  private Canvas parent;
/*  179:     */  private static boolean hasParent;
/*  180:     */  private WindowsKeyboard keyboard;
/*  181:     */  private WindowsMouse mouse;
/*  182:     */  private boolean close_requested;
/*  183:     */  private boolean is_dirty;
/*  184:     */  private ByteBuffer current_gamma;
/*  185:     */  private ByteBuffer saved_gamma;
/*  186:     */  private DisplayMode current_mode;
/*  187:     */  private boolean mode_set;
/*  188:     */  private boolean isMinimized;
/*  189:     */  private boolean isFocused;
/*  190:     */  private boolean redoMakeContextCurrent;
/*  191:     */  private boolean inAppActivate;
/*  192:     */  private boolean resized;
/*  193:     */  private boolean resizable;
/*  194:     */  private boolean maximized;
/*  195:     */  private int x;
/*  196:     */  private int y;
/*  197:     */  private int width;
/*  198:     */  private int height;
/*  199:     */  private long hwnd;
/*  200:     */  private long hdc;
/*  201:     */  private long small_icon;
/*  202:     */  private long large_icon;
/*  203:     */  private boolean iconsLoaded;
/*  204: 204 */  private int captureMouse = -1;
/*  205:     */  private boolean trackingMouse;
/*  206:     */  private boolean mouseInside;
/*  207:     */  
/*  208:     */  static {
/*  209:     */    try {
/*  210: 210 */      Method windowProc = WindowsDisplay.class.getDeclaredMethod("handleMessage", new Class[] { Long.TYPE, Integer.TYPE, Long.TYPE, Long.TYPE, Long.TYPE });
/*  211: 211 */      setWindowProc(windowProc);
/*  212:     */    } catch (NoSuchMethodException e) {
/*  213: 213 */      throw new RuntimeException(e);
/*  214:     */    }
/*  215:     */  }
/*  216:     */  
/*  217:     */  WindowsDisplay() {
/*  218: 218 */    current_display = this;
/*  219:     */  }
/*  220:     */  
/*  221:     */  public void createWindow(DrawableLWJGL drawable, DisplayMode mode, Canvas parent, int x, int y) throws LWJGLException {
/*  222: 222 */    this.close_requested = false;
/*  223: 223 */    this.is_dirty = false;
/*  224: 224 */    this.isMinimized = false;
/*  225: 225 */    this.isFocused = false;
/*  226: 226 */    this.redoMakeContextCurrent = false;
/*  227: 227 */    this.maximized = false;
/*  228: 228 */    this.parent = parent;
/*  229: 229 */    hasParent = parent != null;
/*  230: 230 */    long parent_hwnd = parent != null ? getHwnd(parent) : 0L;
/*  231: 231 */    this.hwnd = nCreateWindow(x, y, mode.getWidth(), mode.getHeight(), (Display.isFullscreen()) || (isUndecorated()), parent != null, parent_hwnd);
/*  232: 232 */    this.resizable = false;
/*  233: 233 */    if (this.hwnd == 0L) {
/*  234: 234 */      throw new LWJGLException("Failed to create window");
/*  235:     */    }
/*  236: 236 */    this.hdc = getDC(this.hwnd);
/*  237: 237 */    if (this.hdc == 0L) {
/*  238: 238 */      nDestroyWindow(this.hwnd);
/*  239: 239 */      throw new LWJGLException("Failed to get dc");
/*  240:     */    }
/*  241:     */    try
/*  242:     */    {
/*  243: 243 */      if ((drawable instanceof DrawableGL)) {
/*  244: 244 */        int format = WindowsPeerInfo.choosePixelFormat(getHdc(), 0, 0, (PixelFormat)drawable.getPixelFormat(), null, true, true, false, true);
/*  245: 245 */        WindowsPeerInfo.setPixelFormat(getHdc(), format);
/*  246:     */      } else {
/*  247: 247 */        this.peer_info = new WindowsDisplayPeerInfo(true);
/*  248: 248 */        ((DrawableGLES)drawable).initialize(this.hwnd, this.hdc, 4, (org.lwjgl.opengles.PixelFormat)drawable.getPixelFormat());
/*  249:     */      }
/*  250: 250 */      this.peer_info.initDC(getHwnd(), getHdc());
/*  251: 251 */      showWindow(getHwnd(), 10);
/*  252:     */      
/*  253: 253 */      updateWidthAndHeight();
/*  254:     */      
/*  255: 255 */      if (parent == null) {
/*  256: 256 */        if (Display.isResizable()) {
/*  257: 257 */          setResizable(true);
/*  258:     */        }
/*  259: 259 */        setForegroundWindow(getHwnd());
/*  260:     */      }
/*  261: 261 */      grabFocus();
/*  262:     */    } catch (LWJGLException e) {
/*  263: 263 */      nReleaseDC(this.hwnd, this.hdc);
/*  264: 264 */      nDestroyWindow(this.hwnd);
/*  265: 265 */      throw e;
/*  266:     */    }
/*  267:     */  }
/*  268:     */  
/*  269:     */  private void updateWidthAndHeight() {
/*  270: 270 */    getClientRect(this.hwnd, rect_buffer);
/*  271: 271 */    rect.copyFromBuffer(rect_buffer);
/*  272: 272 */    this.width = (rect.right - rect.left);
/*  273: 273 */    this.height = (rect.bottom - rect.top);
/*  274:     */  }
/*  275:     */  
/*  277:     */  private static boolean isUndecorated()
/*  278:     */  {
/*  279: 279 */    return Display.getPrivilegedBoolean("org.lwjgl.opengl.Window.undecorated");
/*  280:     */  }
/*  281:     */  
/*  282:     */  private static long getHwnd(Canvas parent) throws LWJGLException {
/*  283: 283 */    AWTCanvasImplementation awt_impl = AWTGLCanvas.createImplementation();
/*  284: 284 */    WindowsPeerInfo parent_peer_info = (WindowsPeerInfo)awt_impl.createPeerInfo(parent, null, null);
/*  285: 285 */    ByteBuffer parent_peer_info_handle = parent_peer_info.lockAndGetHandle();
/*  286:     */    try {
/*  287: 287 */      return parent_peer_info.getHwnd();
/*  288:     */    } finally {
/*  289: 289 */      parent_peer_info.unlock();
/*  290:     */    }
/*  291:     */  }
/*  292:     */  
/*  293:     */  public void destroyWindow() {
/*  294: 294 */    nReleaseDC(this.hwnd, this.hdc);
/*  295: 295 */    nDestroyWindow(this.hwnd);
/*  296: 296 */    freeLargeIcon();
/*  297: 297 */    freeSmallIcon();
/*  298: 298 */    resetCursorClipping();
/*  299:     */  }
/*  300:     */  
/*  301:     */  static void resetCursorClipping()
/*  302:     */  {
/*  303: 303 */    if (cursor_clipped) {
/*  304:     */      try {
/*  305: 305 */        clipCursor(null);
/*  306:     */      } catch (LWJGLException e) {
/*  307: 307 */        LWJGLUtil.log("Failed to reset cursor clipping: " + e);
/*  308:     */      }
/*  309: 309 */      cursor_clipped = false;
/*  310:     */    }
/*  311:     */  }
/*  312:     */  
/*  313:     */  private static void getGlobalClientRect(long hwnd, Rect rect) {
/*  314: 314 */    rect_buffer.put(0, 0).put(1, 0);
/*  315: 315 */    clientToScreen(hwnd, rect_buffer);
/*  316: 316 */    int offset_x = rect_buffer.get(0);
/*  317: 317 */    int offset_y = rect_buffer.get(1);
/*  318: 318 */    getClientRect(hwnd, rect_buffer);
/*  319: 319 */    rect.copyFromBuffer(rect_buffer);
/*  320: 320 */    rect.offset(offset_x, offset_y);
/*  321:     */  }
/*  322:     */  
/*  323:     */  static void setupCursorClipping(long hwnd) throws LWJGLException {
/*  324: 324 */    cursor_clipped = true;
/*  325: 325 */    getGlobalClientRect(hwnd, rect);
/*  326: 326 */    rect.copyToBuffer(rect_buffer);
/*  327: 327 */    clipCursor(rect_buffer);
/*  328:     */  }
/*  329:     */  
/*  330:     */  public void switchDisplayMode(DisplayMode mode) throws LWJGLException
/*  331:     */  {
/*  332: 332 */    nSwitchDisplayMode(mode);
/*  333: 333 */    this.current_mode = mode;
/*  334: 334 */    this.mode_set = true;
/*  335:     */  }
/*  336:     */  
/*  340:     */  private void appActivate(boolean active)
/*  341:     */  {
/*  342: 342 */    if (this.inAppActivate) {
/*  343: 343 */      return;
/*  344:     */    }
/*  345: 345 */    this.inAppActivate = true;
/*  346: 346 */    this.isFocused = active;
/*  347: 347 */    if (active) {
/*  348: 348 */      if (Display.isFullscreen()) {
/*  349: 349 */        restoreDisplayMode();
/*  350:     */      }
/*  351: 351 */      if (this.parent == null) {
/*  352: 352 */        if (this.maximized) {
/*  353: 353 */          showWindow(getHwnd(), 3);
/*  354:     */        } else {
/*  355: 355 */          showWindow(getHwnd(), 9);
/*  356:     */        }
/*  357: 357 */        setForegroundWindow(getHwnd());
/*  358:     */      }
/*  359: 359 */      setFocus(getHwnd());
/*  360: 360 */      this.redoMakeContextCurrent = true;
/*  361: 361 */      if (Display.isFullscreen()) {
/*  362: 362 */        updateClipping();
/*  363:     */      }
/*  364: 364 */      if (this.keyboard != null)
/*  365: 365 */        this.keyboard.fireLostKeyEvents();
/*  366: 366 */    } else if (Display.isFullscreen()) {
/*  367: 367 */      showWindow(getHwnd(), 7);
/*  368: 368 */      resetDisplayMode();
/*  369:     */    } else {
/*  370: 370 */      updateClipping(); }
/*  371: 371 */    updateCursor();
/*  372: 372 */    this.inAppActivate = false;
/*  373:     */  }
/*  374:     */  
/*  377:     */  private void grabFocus()
/*  378:     */  {
/*  379: 379 */    if (this.parent == null) {
/*  380: 380 */      setFocus(getHwnd());
/*  381:     */    } else
/*  382: 382 */      SwingUtilities.invokeLater(new Runnable() {
/*  383:     */        public void run() {
/*  384: 384 */          WindowsDisplay.this.parent.requestFocus();
/*  385:     */        }
/*  386:     */      });
/*  387:     */  }
/*  388:     */  
/*  389:     */  private void restoreDisplayMode() {
/*  390:     */    try {
/*  391: 391 */      doSetGammaRamp(this.current_gamma);
/*  392:     */    } catch (LWJGLException e) {
/*  393: 393 */      LWJGLUtil.log("Failed to restore gamma: " + e.getMessage());
/*  394:     */    }
/*  395:     */    
/*  396: 396 */    if (!this.mode_set) {
/*  397: 397 */      this.mode_set = true;
/*  398:     */      try {
/*  399: 399 */        nSwitchDisplayMode(this.current_mode);
/*  400:     */      } catch (LWJGLException e) {
/*  401: 401 */        LWJGLUtil.log("Failed to restore display mode: " + e.getMessage());
/*  402:     */      }
/*  403:     */    }
/*  404:     */  }
/*  405:     */  
/*  406:     */  public void resetDisplayMode() {
/*  407:     */    try {
/*  408: 408 */      doSetGammaRamp(this.saved_gamma);
/*  409:     */    } catch (LWJGLException e) {
/*  410: 410 */      LWJGLUtil.log("Failed to reset gamma ramp: " + e.getMessage());
/*  411:     */    }
/*  412: 412 */    this.current_gamma = this.saved_gamma;
/*  413: 413 */    if (this.mode_set) {
/*  414: 414 */      this.mode_set = false;
/*  415: 415 */      nResetDisplayMode();
/*  416:     */    }
/*  417: 417 */    resetCursorClipping();
/*  418:     */  }
/*  419:     */  
/*  420:     */  public int getGammaRampLength()
/*  421:     */  {
/*  422: 422 */    return 256;
/*  423:     */  }
/*  424:     */  
/*  425:     */  public void setGammaRamp(FloatBuffer gammaRamp) throws LWJGLException {
/*  426: 426 */    doSetGammaRamp(convertToNativeRamp(gammaRamp));
/*  427:     */  }
/*  428:     */  
/*  429:     */  private void doSetGammaRamp(ByteBuffer native_gamma)
/*  430:     */    throws LWJGLException
/*  431:     */  {
/*  432: 432 */    nSetGammaRamp(native_gamma);
/*  433: 433 */    this.current_gamma = native_gamma;
/*  434:     */  }
/*  435:     */  
/*  436:     */  public String getAdapter()
/*  437:     */  {
/*  438:     */    try {
/*  439: 439 */      String maxObjNo = WindowsRegistry.queryRegistrationKey(3, "HARDWARE\\DeviceMap\\Video", "MaxObjectNumber");
/*  440:     */      
/*  443: 443 */      int maxObjectNumber = maxObjNo.charAt(0);
/*  444: 444 */      String vga_driver_value = "";
/*  445: 445 */      for (int i = 0; i < maxObjectNumber; i++) {
/*  446: 446 */        String adapter_string = WindowsRegistry.queryRegistrationKey(3, "HARDWARE\\DeviceMap\\Video", "\\Device\\Video" + i);
/*  447:     */        
/*  450: 450 */        String root_key = "\\registry\\machine\\";
/*  451: 451 */        if (adapter_string.toLowerCase().startsWith(root_key)) {
/*  452: 452 */          String driver_value = WindowsRegistry.queryRegistrationKey(3, adapter_string.substring(root_key.length()), "InstalledDisplayDrivers");
/*  453:     */          
/*  456: 456 */          if (driver_value.toUpperCase().startsWith("VGA")) {
/*  457: 457 */            vga_driver_value = driver_value;
/*  458: 458 */          } else if ((!driver_value.toUpperCase().startsWith("RDP")) && (!driver_value.toUpperCase().startsWith("NMNDD"))) {
/*  459: 459 */            return driver_value;
/*  460:     */          }
/*  461:     */        }
/*  462:     */      }
/*  463: 463 */      if (!vga_driver_value.equals("")) {
/*  464: 464 */        return vga_driver_value;
/*  465:     */      }
/*  466:     */    } catch (LWJGLException e) {
/*  467: 467 */      LWJGLUtil.log("Exception occurred while querying registry: " + e);
/*  468:     */    }
/*  469: 469 */    return null;
/*  470:     */  }
/*  471:     */  
/*  472:     */  public String getVersion() {
/*  473: 473 */    String driver = getAdapter();
/*  474: 474 */    if (driver != null) {
/*  475: 475 */      String[] drivers = driver.split(",");
/*  476: 476 */      if (drivers.length > 0) {
/*  477: 477 */        WindowsFileVersion version = nGetVersion(drivers[0] + ".dll");
/*  478: 478 */        if (version != null)
/*  479: 479 */          return version.toString();
/*  480:     */      }
/*  481:     */    }
/*  482: 482 */    return null;
/*  483:     */  }
/*  484:     */  
/*  485:     */  public DisplayMode init() throws LWJGLException
/*  486:     */  {
/*  487: 487 */    this.current_gamma = (this.saved_gamma = getCurrentGammaRamp());
/*  488: 488 */    return this.current_mode = getCurrentDisplayMode();
/*  489:     */  }
/*  490:     */  
/*  491:     */  public void setTitle(String title)
/*  492:     */  {
/*  493: 493 */    ByteBuffer buffer = MemoryUtil.encodeUTF16(title);
/*  494: 494 */    nSetTitle(this.hwnd, MemoryUtil.getAddress0(buffer));
/*  495:     */  }
/*  496:     */  
/*  497:     */  public boolean isCloseRequested()
/*  498:     */  {
/*  499: 499 */    boolean saved = this.close_requested;
/*  500: 500 */    this.close_requested = false;
/*  501: 501 */    return saved;
/*  502:     */  }
/*  503:     */  
/*  504:     */  public boolean isVisible() {
/*  505: 505 */    return !this.isMinimized;
/*  506:     */  }
/*  507:     */  
/*  508:     */  public boolean isActive() {
/*  509: 509 */    return this.isFocused;
/*  510:     */  }
/*  511:     */  
/*  512:     */  public boolean isDirty() {
/*  513: 513 */    boolean saved = this.is_dirty;
/*  514: 514 */    this.is_dirty = false;
/*  515: 515 */    return saved;
/*  516:     */  }
/*  517:     */  
/*  518:     */  public PeerInfo createPeerInfo(PixelFormat pixel_format, ContextAttribs attribs) throws LWJGLException {
/*  519: 519 */    this.peer_info = new WindowsDisplayPeerInfo(false);
/*  520: 520 */    return this.peer_info;
/*  521:     */  }
/*  522:     */  
/*  523:     */  public void update()
/*  524:     */  {
/*  525:     */    
/*  526: 526 */    if ((!this.isFocused) && (this.parent != null) && (this.parent.isFocusOwner())) {
/*  527: 527 */      KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
/*  528: 528 */      setFocus(getHwnd());
/*  529:     */    }
/*  530:     */    
/*  531: 531 */    if (this.redoMakeContextCurrent) {
/*  532: 532 */      this.redoMakeContextCurrent = false;
/*  533:     */      
/*  537:     */      try
/*  538:     */      {
/*  539: 539 */        Context context = ((DrawableLWJGL)Display.getDrawable()).getContext();
/*  540: 540 */        if ((context != null) && (context.isCurrent()))
/*  541: 541 */          context.makeCurrent();
/*  542:     */      } catch (LWJGLException e) {
/*  543: 543 */        LWJGLUtil.log("Exception occurred while trying to make context current: " + e);
/*  544:     */      }
/*  545:     */    }
/*  546:     */  }
/*  547:     */  
/*  548:     */  public void reshape(int x, int y, int width, int height)
/*  549:     */  {
/*  550: 550 */    nReshape(getHwnd(), x, y, width, height, (Display.isFullscreen()) || (isUndecorated()), this.parent != null);
/*  551:     */  }
/*  552:     */  
/*  555:     */  public boolean hasWheel()
/*  556:     */  {
/*  557: 557 */    return this.mouse.hasWheel();
/*  558:     */  }
/*  559:     */  
/*  560:     */  public int getButtonCount() {
/*  561: 561 */    return this.mouse.getButtonCount();
/*  562:     */  }
/*  563:     */  
/*  564:     */  public void createMouse() throws LWJGLException {
/*  565: 565 */    this.mouse = new WindowsMouse(getHwnd());
/*  566:     */  }
/*  567:     */  
/*  568:     */  public void destroyMouse() {
/*  569: 569 */    if (this.mouse != null)
/*  570: 570 */      this.mouse.destroy();
/*  571: 571 */    this.mouse = null;
/*  572:     */  }
/*  573:     */  
/*  574:     */  public void pollMouse(IntBuffer coord_buffer, ByteBuffer buttons) {
/*  575: 575 */    this.mouse.poll(coord_buffer, buttons);
/*  576:     */  }
/*  577:     */  
/*  578:     */  public void readMouse(ByteBuffer buffer) {
/*  579: 579 */    this.mouse.read(buffer);
/*  580:     */  }
/*  581:     */  
/*  582:     */  public void grabMouse(boolean grab) {
/*  583: 583 */    this.mouse.grab(grab, shouldGrab());
/*  584: 584 */    updateCursor();
/*  585:     */  }
/*  586:     */  
/*  587:     */  public int getNativeCursorCapabilities() {
/*  588: 588 */    return 1;
/*  589:     */  }
/*  590:     */  
/*  591:     */  public void setCursorPosition(int x, int y) {
/*  592: 592 */    getGlobalClientRect(getHwnd(), rect);
/*  593: 593 */    int transformed_x = rect.left + x;
/*  594: 594 */    int transformed_y = rect.bottom - 1 - y;
/*  595: 595 */    nSetCursorPosition(transformed_x, transformed_y);
/*  596: 596 */    setMousePosition(x, y);
/*  597:     */  }
/*  598:     */  
/*  599:     */  public void setNativeCursor(Object handle) throws LWJGLException
/*  600:     */  {
/*  601: 601 */    this.current_cursor = handle;
/*  602: 602 */    updateCursor();
/*  603:     */  }
/*  604:     */  
/*  605:     */  private void updateCursor() {
/*  606:     */    try {
/*  607: 607 */      if ((this.mouse != null) && (shouldGrab())) {
/*  608: 608 */        nSetNativeCursor(getHwnd(), this.mouse.getBlankCursor());
/*  609:     */      } else
/*  610: 610 */        nSetNativeCursor(getHwnd(), this.current_cursor);
/*  611:     */    } catch (LWJGLException e) {
/*  612: 612 */      LWJGLUtil.log("Failed to update cursor: " + e);
/*  613:     */    }
/*  614:     */  }
/*  615:     */  
/*  616:     */  public int getMinCursorSize()
/*  617:     */  {
/*  618: 618 */    return getSystemMetrics(13);
/*  619:     */  }
/*  620:     */  
/*  621:     */  public int getMaxCursorSize() {
/*  622: 622 */    return getSystemMetrics(13);
/*  623:     */  }
/*  624:     */  
/*  628:     */  private long getHwnd()
/*  629:     */  {
/*  630: 630 */    return this.hwnd;
/*  631:     */  }
/*  632:     */  
/*  633:     */  private long getHdc() {
/*  634: 634 */    return this.hdc;
/*  635:     */  }
/*  636:     */  
/*  640:     */  static void centerCursor(long hwnd)
/*  641:     */  {
/*  642: 642 */    if ((getForegroundWindow() != hwnd) && (!hasParent))
/*  643: 643 */      return;
/*  644: 644 */    getGlobalClientRect(hwnd, rect);
/*  645: 645 */    int local_offset_x = rect.left;
/*  646: 646 */    int local_offset_y = rect.top;
/*  647:     */    
/*  651: 651 */    int center_x = (rect.left + rect.right) / 2;
/*  652: 652 */    int center_y = (rect.top + rect.bottom) / 2;
/*  653: 653 */    nSetCursorPosition(center_x, center_y);
/*  654: 654 */    int local_x = center_x - local_offset_x;
/*  655: 655 */    int local_y = center_y - local_offset_y;
/*  656: 656 */    if (current_display != null)
/*  657: 657 */      current_display.setMousePosition(local_x, transformY(hwnd, local_y));
/*  658:     */  }
/*  659:     */  
/*  660:     */  private void setMousePosition(int x, int y) {
/*  661: 661 */    if (this.mouse != null) {
/*  662: 662 */      this.mouse.setPosition(x, y);
/*  663:     */    }
/*  664:     */  }
/*  665:     */  
/*  666:     */  public void createKeyboard() throws LWJGLException {
/*  667: 667 */    this.keyboard = new WindowsKeyboard(getHwnd());
/*  668:     */  }
/*  669:     */  
/*  670:     */  public void destroyKeyboard() {
/*  671: 671 */    this.keyboard.destroy();
/*  672: 672 */    this.keyboard = null;
/*  673:     */  }
/*  674:     */  
/*  675:     */  public void pollKeyboard(ByteBuffer keyDownBuffer) {
/*  676: 676 */    this.keyboard.poll(keyDownBuffer);
/*  677:     */  }
/*  678:     */  
/*  679:     */  public void readKeyboard(ByteBuffer buffer) {
/*  680: 680 */    this.keyboard.read(buffer);
/*  681:     */  }
/*  682:     */  
/*  685:     */  public Object createCursor(int width, int height, int xHotspot, int yHotspot, int numImages, IntBuffer images, IntBuffer delays)
/*  686:     */    throws LWJGLException
/*  687:     */  {
/*  688: 688 */    return doCreateCursor(width, height, xHotspot, yHotspot, numImages, images, delays);
/*  689:     */  }
/*  690:     */  
/*  691:     */  static Object doCreateCursor(int width, int height, int xHotspot, int yHotspot, int numImages, IntBuffer images, IntBuffer delays) throws LWJGLException {
/*  692: 692 */    return nCreateCursor(width, height, xHotspot, yHotspot, numImages, images, images.position(), delays, delays != null ? delays.position() : -1);
/*  693:     */  }
/*  694:     */  
/*  695:     */  public void destroyCursor(Object cursorHandle) {
/*  696: 696 */    doDestroyCursor(cursorHandle);
/*  697:     */  }
/*  698:     */  
/*  699:     */  public int getPbufferCapabilities()
/*  700:     */  {
/*  701:     */    try
/*  702:     */    {
/*  703: 703 */      return nGetPbufferCapabilities(new PixelFormat(0, 0, 0, 0, 0, 0, 0, 0, false));
/*  704:     */    } catch (LWJGLException e) {
/*  705: 705 */      LWJGLUtil.log("Exception occurred while determining pbuffer capabilities: " + e); }
/*  706: 706 */    return 0;
/*  707:     */  }
/*  708:     */  
/*  710:     */  public boolean isBufferLost(PeerInfo handle)
/*  711:     */  {
/*  712: 712 */    return ((WindowsPbufferPeerInfo)handle).isBufferLost();
/*  713:     */  }
/*  714:     */  
/*  715:     */  public PeerInfo createPbuffer(int width, int height, PixelFormat pixel_format, ContextAttribs attribs, IntBuffer pixelFormatCaps, IntBuffer pBufferAttribs)
/*  716:     */    throws LWJGLException
/*  717:     */  {
/*  718: 718 */    return new WindowsPbufferPeerInfo(width, height, pixel_format, pixelFormatCaps, pBufferAttribs);
/*  719:     */  }
/*  720:     */  
/*  721:     */  public void setPbufferAttrib(PeerInfo handle, int attrib, int value) {
/*  722: 722 */    ((WindowsPbufferPeerInfo)handle).setPbufferAttrib(attrib, value);
/*  723:     */  }
/*  724:     */  
/*  725:     */  public void bindTexImageToPbuffer(PeerInfo handle, int buffer) {
/*  726: 726 */    ((WindowsPbufferPeerInfo)handle).bindTexImageToPbuffer(buffer);
/*  727:     */  }
/*  728:     */  
/*  729:     */  public void releaseTexImageFromPbuffer(PeerInfo handle, int buffer) {
/*  730: 730 */    ((WindowsPbufferPeerInfo)handle).releaseTexImageFromPbuffer(buffer);
/*  731:     */  }
/*  732:     */  
/*  733:     */  private void freeSmallIcon() {
/*  734: 734 */    if (this.small_icon != 0L) {
/*  735: 735 */      destroyIcon(this.small_icon);
/*  736: 736 */      this.small_icon = 0L;
/*  737:     */    }
/*  738:     */  }
/*  739:     */  
/*  740:     */  private void freeLargeIcon() {
/*  741: 741 */    if (this.large_icon != 0L) {
/*  742: 742 */      destroyIcon(this.large_icon);
/*  743: 743 */      this.large_icon = 0L;
/*  744:     */    }
/*  745:     */  }
/*  746:     */  
/*  758:     */  public int setIcon(ByteBuffer[] icons)
/*  759:     */  {
/*  760: 760 */    boolean done_small = false;
/*  761: 761 */    boolean done_large = false;
/*  762: 762 */    int used = 0;
/*  763:     */    
/*  764: 764 */    int small_icon_size = 16;
/*  765: 765 */    int large_icon_size = 32;
/*  766: 766 */    for (ByteBuffer icon : icons) {
/*  767: 767 */      int size = icon.limit() / 4;
/*  768:     */      
/*  769: 769 */      if (((int)Math.sqrt(size) == small_icon_size) && (!done_small)) {
/*  770: 770 */        long small_new_icon = createIcon(small_icon_size, small_icon_size, icon.asIntBuffer());
/*  771: 771 */        sendMessage(this.hwnd, 128L, 0L, small_new_icon);
/*  772: 772 */        freeSmallIcon();
/*  773: 773 */        this.small_icon = small_new_icon;
/*  774: 774 */        used++;
/*  775: 775 */        done_small = true;
/*  776:     */      }
/*  777: 777 */      if (((int)Math.sqrt(size) == large_icon_size) && (!done_large)) {
/*  778: 778 */        long large_new_icon = createIcon(large_icon_size, large_icon_size, icon.asIntBuffer());
/*  779: 779 */        sendMessage(this.hwnd, 128L, 1L, large_new_icon);
/*  780: 780 */        freeLargeIcon();
/*  781: 781 */        this.large_icon = large_new_icon;
/*  782: 782 */        used++;
/*  783: 783 */        done_large = true;
/*  784:     */        
/*  789: 789 */        this.iconsLoaded = false;
/*  790:     */        
/*  792: 792 */        long time = System.nanoTime();
/*  793: 793 */        long MAX_WAIT = 500000000L;
/*  794:     */        for (;;) {
/*  795: 795 */          nUpdate();
/*  796: 796 */          if ((this.iconsLoaded) || (MAX_WAIT < System.nanoTime() - time)) {
/*  797:     */            break;
/*  798:     */          }
/*  799: 799 */          Thread.yield();
/*  800:     */        }
/*  801:     */      }
/*  802:     */    }
/*  803:     */    
/*  804: 804 */    return used;
/*  805:     */  }
/*  806:     */  
/*  812:     */  private void handleMouseButton(int button, int state, long millis)
/*  813:     */  {
/*  814: 814 */    if (this.mouse != null) {
/*  815: 815 */      this.mouse.handleMouseButton((byte)button, (byte)state, millis);
/*  816:     */      
/*  818: 818 */      if ((this.captureMouse == -1) && (button != -1) && (state == 1)) {
/*  819: 819 */        this.captureMouse = button;
/*  820: 820 */        nSetCapture(this.hwnd);
/*  821:     */      }
/*  822:     */      
/*  824: 824 */      if ((this.captureMouse != -1) && (button == this.captureMouse) && (state == 0)) {
/*  825: 825 */        this.captureMouse = -1;
/*  826: 826 */        nReleaseCapture();
/*  827:     */      }
/*  828:     */    }
/*  829:     */  }
/*  830:     */  
/*  831:     */  private boolean shouldGrab() {
/*  832: 832 */    return (!this.isMinimized) && (this.isFocused) && (Mouse.isGrabbed());
/*  833:     */  }
/*  834:     */  
/*  835:     */  private void handleMouseMoved(int x, int y, long millis) {
/*  836: 836 */    if (this.mouse != null) {
/*  837: 837 */      this.mouse.handleMouseMoved(x, y, millis, shouldGrab());
/*  838:     */    }
/*  839:     */  }
/*  840:     */  
/*  843:     */  private void handleMouseScrolled(int amount, long millis)
/*  844:     */  {
/*  845: 845 */    if (this.mouse != null) {
/*  846: 846 */      this.mouse.handleMouseScrolled(amount, millis);
/*  847:     */    }
/*  848:     */  }
/*  849:     */  
/*  850:     */  private void handleChar(long wParam, long lParam, long millis)
/*  851:     */  {
/*  852: 852 */    byte previous_state = (byte)(int)(lParam >>> 30 & 1L);
/*  853: 853 */    byte state = (byte)(int)(1L - (lParam >>> 31 & 1L));
/*  854: 854 */    boolean repeat = state == previous_state;
/*  855: 855 */    if (this.keyboard != null)
/*  856: 856 */      this.keyboard.handleChar((int)(wParam & 0xFFFF), millis, repeat);
/*  857:     */  }
/*  858:     */  
/*  859:     */  private void handleKeyButton(long wParam, long lParam, long millis) {
/*  860: 860 */    byte previous_state = (byte)(int)(lParam >>> 30 & 1L);
/*  861: 861 */    byte state = (byte)(int)(1L - (lParam >>> 31 & 1L));
/*  862: 862 */    boolean repeat = state == previous_state;
/*  863: 863 */    byte extended = (byte)(int)(lParam >>> 24 & 1L);
/*  864: 864 */    int scan_code = (int)(lParam >>> 16 & 0xFF);
/*  865: 865 */    if (this.keyboard != null) {
/*  866: 866 */      this.keyboard.handleKey((int)wParam, scan_code, extended != 0, state, millis, repeat);
/*  867:     */    }
/*  868:     */  }
/*  869:     */  
/*  870:     */  private static int transformY(long hwnd, int y) {
/*  871: 871 */    getClientRect(hwnd, rect_buffer);
/*  872: 872 */    rect.copyFromBuffer(rect_buffer);
/*  873: 873 */    return rect.bottom - rect.top - 1 - y;
/*  874:     */  }
/*  875:     */  
/*  879:     */  private static long handleMessage(long hwnd, int msg, long wParam, long lParam, long millis)
/*  880:     */  {
/*  881: 881 */    if (current_display != null) {
/*  882: 882 */      return current_display.doHandleMessage(hwnd, msg, wParam, lParam, millis);
/*  883:     */    }
/*  884: 884 */    return defWindowProc(hwnd, msg, wParam, lParam);
/*  885:     */  }
/*  886:     */  
/*  888:     */  private void checkCursorState()
/*  889:     */  {
/*  890: 890 */    updateClipping();
/*  891:     */  }
/*  892:     */  
/*  893:     */  private void updateClipping() {
/*  894: 894 */    if (((Display.isFullscreen()) || ((this.mouse != null) && (this.mouse.isGrabbed()))) && (!this.isMinimized) && (this.isFocused) && ((getForegroundWindow() == getHwnd()) || (hasParent))) {
/*  895:     */      try {
/*  896: 896 */        setupCursorClipping(getHwnd());
/*  897:     */      } catch (LWJGLException e) {
/*  898: 898 */        LWJGLUtil.log("setupCursorClipping failed: " + e.getMessage());
/*  899:     */      }
/*  900:     */    } else {
/*  901: 901 */      resetCursorClipping();
/*  902:     */    }
/*  903:     */  }
/*  904:     */  
/*  905:     */  private void setMinimized(boolean m) {
/*  906: 906 */    this.isMinimized = m;
/*  907: 907 */    checkCursorState();
/*  908:     */  }
/*  909:     */  
/*  918:     */  private long doHandleMessage(long hwnd, int msg, long wParam, long lParam, long millis)
/*  919:     */  {
/*  920: 920 */    switch (msg)
/*  921:     */    {
/*  931:     */    case 6: 
/*  932: 932 */      return 0L;
/*  933:     */    case 5: 
/*  934: 934 */      switch ((int)wParam) {
/*  935:     */      case 0: 
/*  936:     */      case 2: 
/*  937: 937 */        this.maximized = ((int)wParam == 2);
/*  938: 938 */        this.resized = true;
/*  939: 939 */        updateWidthAndHeight();
/*  940: 940 */        setMinimized(false);
/*  941: 941 */        break;
/*  942:     */      case 1: 
/*  943: 943 */        setMinimized(true);
/*  944:     */      }
/*  945:     */      
/*  946: 946 */      break;
/*  947:     */    case 532: 
/*  948: 948 */      this.resized = true;
/*  949: 949 */      updateWidthAndHeight();
/*  950: 950 */      break;
/*  951:     */    case 32: 
/*  952: 952 */      if ((lParam & 0xFFFF) == 1L)
/*  953:     */      {
/*  955: 955 */        updateCursor();
/*  956: 956 */        return -1L;
/*  957:     */      }
/*  958:     */      
/*  959: 959 */      return defWindowProc(hwnd, msg, wParam, lParam);
/*  960:     */    
/*  961:     */    case 8: 
/*  962: 962 */      appActivate(false);
/*  963: 963 */      return 0L;
/*  964:     */    case 7: 
/*  965: 965 */      appActivate(true);
/*  966: 966 */      return 0L;
/*  967:     */    case 33: 
/*  968: 968 */      if (!this.isFocused) {
/*  969: 969 */        grabFocus();
/*  970:     */      }
/*  971: 971 */      return 3L;
/*  972:     */    case 512: 
/*  973: 973 */      int xPos = (short)(int)(lParam & 0xFFFF);
/*  974: 974 */      int yPos = transformY(getHwnd(), (short)(int)(lParam >> 16 & 0xFFFF));
/*  975: 975 */      handleMouseMoved(xPos, yPos, millis);
/*  976: 976 */      checkCursorState();
/*  977: 977 */      this.mouseInside = true;
/*  978: 978 */      if (!this.trackingMouse) {
/*  979: 979 */        this.trackingMouse = nTrackMouseEvent(hwnd);
/*  980:     */      }
/*  981: 981 */      return 0L;
/*  982:     */    case 522: 
/*  983: 983 */      int dwheel = (short)(int)(wParam >> 16 & 0xFFFF);
/*  984: 984 */      handleMouseScrolled(dwheel, millis);
/*  985: 985 */      return 0L;
/*  986:     */    case 513: 
/*  987: 987 */      handleMouseButton(0, 1, millis);
/*  988: 988 */      return 0L;
/*  989:     */    case 514: 
/*  990: 990 */      handleMouseButton(0, 0, millis);
/*  991: 991 */      return 0L;
/*  992:     */    case 516: 
/*  993: 993 */      handleMouseButton(1, 1, millis);
/*  994: 994 */      return 0L;
/*  995:     */    case 517: 
/*  996: 996 */      handleMouseButton(1, 0, millis);
/*  997: 997 */      return 0L;
/*  998:     */    case 519: 
/*  999: 999 */      handleMouseButton(2, 1, millis);
/* 1000:1000 */      return 0L;
/* 1001:     */    case 520: 
/* 1002:1002 */      handleMouseButton(2, 0, millis);
/* 1003:1003 */      return 0L;
/* 1004:     */    case 524: 
/* 1005:1005 */      if (wParam >> 16 == 1L) {
/* 1006:1006 */        handleMouseButton(3, 0, millis);
/* 1007:     */      } else {
/* 1008:1008 */        handleMouseButton(4, 0, millis);
/* 1009:     */      }
/* 1010:1010 */      return 1L;
/* 1011:     */    case 523: 
/* 1012:1012 */      if ((wParam & 0xFF) == 32L) {
/* 1013:1013 */        handleMouseButton(3, 1, millis);
/* 1014:     */      } else {
/* 1015:1015 */        handleMouseButton(4, 1, millis);
/* 1016:     */      }
/* 1017:1017 */      return 1L;
/* 1018:     */    case 258: 
/* 1019:     */    case 262: 
/* 1020:1020 */      handleChar(wParam, lParam, millis);
/* 1021:1021 */      return 0L;
/* 1022:     */    
/* 1024:     */    case 257: 
/* 1025:     */    case 261: 
/* 1026:1026 */      if ((wParam == 44L) && (this.keyboard != null) && (!this.keyboard.isKeyDown(183)))
/* 1027:     */      {
/* 1029:1029 */        long fake_lparam = lParam & 0x7FFFFFFF;
/* 1030:     */        
/* 1031:1031 */        fake_lparam &= -1073741825L;
/* 1032:1032 */        handleKeyButton(wParam, fake_lparam, millis);
/* 1033:     */      }
/* 1034:     */    
/* 1036:     */    case 256: 
/* 1037:     */    case 260: 
/* 1038:1038 */      handleKeyButton(wParam, lParam, millis);
/* 1039:1039 */      break;
/* 1040:     */    case 18: 
/* 1041:1041 */      this.close_requested = true;
/* 1042:1042 */      return 0L;
/* 1043:     */    case 274: 
/* 1044:1044 */      switch ((int)(wParam & 0xFFF0)) {
/* 1045:     */      case 61584: 
/* 1046:     */      case 61696: 
/* 1047:     */      case 61760: 
/* 1048:     */      case 61808: 
/* 1049:1049 */        return 0L;
/* 1050:     */      case 61536: 
/* 1051:1051 */        this.close_requested = true;
/* 1052:1052 */        return 0L;
/* 1053:     */      }
/* 1054:1054 */      break;
/* 1055:     */    
/* 1057:     */    case 15: 
/* 1058:1058 */      this.is_dirty = true;
/* 1059:1059 */      break;
/* 1060:     */    case 675: 
/* 1061:1061 */      this.mouseInside = false;
/* 1062:1062 */      this.trackingMouse = false;
/* 1063:1063 */      break;
/* 1064:     */    case 31: 
/* 1065:1065 */      nReleaseCapture();
/* 1066:     */    
/* 1067:     */    case 533: 
/* 1068:1068 */      if (this.captureMouse != -1) {
/* 1069:1069 */        handleMouseButton(this.captureMouse, 0, millis);
/* 1070:1070 */        this.captureMouse = -1;
/* 1071:     */      }
/* 1072:1072 */      return 0L;
/* 1073:     */    case 71: 
/* 1074:1074 */      if (getWindowRect(hwnd, rect_buffer)) {
/* 1075:1075 */        rect.copyFromBuffer(rect_buffer);
/* 1076:1076 */        this.x = rect.top;
/* 1077:1077 */        this.y = rect.bottom;
/* 1078:     */      } else {
/* 1079:1079 */        LWJGLUtil.log("WM_WINDOWPOSCHANGED: Unable to get window rect");
/* 1080:     */      }
/* 1081:1081 */      break;
/* 1082:     */    case 127: 
/* 1083:1083 */      this.iconsLoaded = true;
/* 1084:     */    }
/* 1085:     */    
/* 1086:     */    
/* 1087:1087 */    return defWindowProc(hwnd, msg, wParam, lParam);
/* 1088:     */  }
/* 1089:     */  
/* 1091:     */  public int getX()
/* 1092:     */  {
/* 1093:1093 */    return this.x;
/* 1094:     */  }
/* 1095:     */  
/* 1096:     */  public int getY() {
/* 1097:1097 */    return this.y;
/* 1098:     */  }
/* 1099:     */  
/* 1100:     */  public int getWidth() {
/* 1101:1101 */    return this.width;
/* 1102:     */  }
/* 1103:     */  
/* 1104:     */  public int getHeight() {
/* 1105:1105 */    return this.height;
/* 1106:     */  }
/* 1107:     */  
/* 1109:     */  public boolean isInsideWindow()
/* 1110:     */  {
/* 1111:1111 */    return this.mouseInside;
/* 1112:     */  }
/* 1113:     */  
/* 1114:     */  public void setResizable(boolean resizable) {
/* 1115:1115 */    if (this.resizable != resizable) {
/* 1116:1116 */      int style = (int)getWindowLongPtr(this.hwnd, -16);
/* 1117:1117 */      int styleex = (int)getWindowLongPtr(this.hwnd, -20);
/* 1118:     */      
/* 1120:1120 */      if ((resizable) && (!Display.isFullscreen())) {
/* 1121:1121 */        setWindowLongPtr(this.hwnd, -16, style |= 327680);
/* 1122:     */      } else {
/* 1123:1123 */        setWindowLongPtr(this.hwnd, -16, style &= -327681);
/* 1124:     */      }
/* 1125:     */      
/* 1128:1128 */      getClientRect(this.hwnd, rect_buffer);
/* 1129:1129 */      rect.copyFromBuffer(rect_buffer);
/* 1130:1130 */      adjustWindowRectEx(rect_buffer, style, false, styleex);
/* 1131:1131 */      rect.copyFromBuffer(rect_buffer);
/* 1132:     */      
/* 1134:1134 */      setWindowPos(this.hwnd, 0L, 0, 0, rect.right - rect.left, rect.bottom - rect.top, 38L);
/* 1135:     */      
/* 1136:1136 */      updateWidthAndHeight();
/* 1137:1137 */      this.resized = false;
/* 1138:     */    }
/* 1139:1139 */    this.resizable = resizable;
/* 1140:     */  }
/* 1141:     */  
/* 1143:     */  public boolean wasResized()
/* 1144:     */  {
/* 1145:1145 */    if (this.resized) {
/* 1146:1146 */      this.resized = false;
/* 1147:1147 */      return true;
/* 1148:     */    }
/* 1149:1149 */    return false;
/* 1150:     */  }
/* 1151:     */  
/* 1153:     */  private static native long nCreateWindow(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean1, boolean paramBoolean2, long paramLong) throws LWJGLException;
/* 1154:     */  
/* 1155:     */  private static native void nReleaseDC(long paramLong1, long paramLong2);
/* 1156:     */  
/* 1157:     */  private static final class Rect
/* 1158:     */  {
/* 1159:1159 */    public void copyToBuffer(IntBuffer buffer) { buffer.put(0, this.top).put(1, this.bottom).put(2, this.left).put(3, this.right); }
/* 1160:     */    
/* 1161:     */    public int top;
/* 1162:     */    
/* 1163:1163 */    public void copyFromBuffer(IntBuffer buffer) { this.top = buffer.get(0);
/* 1164:1164 */      this.bottom = buffer.get(1);
/* 1165:1165 */      this.left = buffer.get(2);
/* 1166:1166 */      this.right = buffer.get(3); }
/* 1167:     */    
/* 1168:     */    public int bottom;
/* 1169:     */    
/* 1170:1170 */    public void offset(int offset_x, int offset_y) { this.left += offset_x;
/* 1171:1171 */      this.right += offset_x;
/* 1172:1172 */      this.top += offset_y;
/* 1173:1173 */      this.bottom += offset_y; }
/* 1174:     */    
/* 1175:     */    public int left;
/* 1176:     */    public int right;
/* 1177:1177 */    public static void intersect(Rect r1, Rect r2, Rect dst) { dst.top = Math.max(r1.top, r2.top);
/* 1178:1178 */      dst.bottom = Math.min(r1.bottom, r2.bottom);
/* 1179:1179 */      dst.left = Math.max(r1.left, r2.left);
/* 1180:1180 */      dst.right = Math.min(r1.right, r2.right);
/* 1181:     */    }
/* 1182:     */    
/* 1183:     */    public String toString() {
/* 1184:1184 */      return "Rect: top = " + this.top + " bottom = " + this.bottom + " left = " + this.left + " right = " + this.right + ", width: " + (this.right - this.left) + ", height: " + (this.bottom - this.top);
/* 1185:     */    }
/* 1186:     */  }
/* 1187:     */  
/* 1188:     */  private static native void nDestroyWindow(long paramLong);
/* 1189:     */  
/* 1190:     */  private static native void clipCursor(IntBuffer paramIntBuffer)
/* 1191:     */    throws LWJGLException;
/* 1192:     */  
/* 1193:     */  private static native void nSwitchDisplayMode(DisplayMode paramDisplayMode)
/* 1194:     */    throws LWJGLException;
/* 1195:     */  
/* 1196:     */  private static native void showWindow(long paramLong, int paramInt);
/* 1197:     */  
/* 1198:     */  private static native void setForegroundWindow(long paramLong);
/* 1199:     */  
/* 1200:     */  private static native void setFocus(long paramLong);
/* 1201:     */  
/* 1202:     */  private static native void nResetDisplayMode();
/* 1203:     */  
/* 1204:     */  private static native ByteBuffer convertToNativeRamp(FloatBuffer paramFloatBuffer)
/* 1205:     */    throws LWJGLException;
/* 1206:     */  
/* 1207:     */  private static native ByteBuffer getCurrentGammaRamp()
/* 1208:     */    throws LWJGLException;
/* 1209:     */  
/* 1210:     */  private static native void nSetGammaRamp(ByteBuffer paramByteBuffer)
/* 1211:     */    throws LWJGLException;
/* 1212:     */  
/* 1213:     */  private native WindowsFileVersion nGetVersion(String paramString);
/* 1214:     */  
/* 1215:     */  private static native DisplayMode getCurrentDisplayMode()
/* 1216:     */    throws LWJGLException;
/* 1217:     */  
/* 1218:     */  private static native void nSetTitle(long paramLong1, long paramLong2);
/* 1219:     */  
/* 1220:     */  private static native void nUpdate();
/* 1221:     */  
/* 1222:     */  private static native void nReshape(long paramLong, int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean1, boolean paramBoolean2);
/* 1223:     */  
/* 1224:     */  public native DisplayMode[] getAvailableDisplayModes()
/* 1225:     */    throws LWJGLException;
/* 1226:     */  
/* 1227:     */  private static native void nSetCursorPosition(int paramInt1, int paramInt2);
/* 1228:     */  
/* 1229:     */  static native void nSetNativeCursor(long paramLong, Object paramObject)
/* 1230:     */    throws LWJGLException;
/* 1231:     */  
/* 1232:     */  static native int getSystemMetrics(int paramInt);
/* 1233:     */  
/* 1234:     */  private static native long getDllInstance();
/* 1235:     */  
/* 1236:     */  private static native long getDC(long paramLong);
/* 1237:     */  
/* 1238:     */  private static native long getDesktopWindow();
/* 1239:     */  
/* 1240:     */  private static native long getForegroundWindow();
/* 1241:     */  
/* 1242:     */  public static native ByteBuffer nCreateCursor(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, IntBuffer paramIntBuffer1, int paramInt6, IntBuffer paramIntBuffer2, int paramInt7)
/* 1243:     */    throws LWJGLException;
/* 1244:     */  
/* 1245:     */  static native void doDestroyCursor(Object paramObject);
/* 1246:     */  
/* 1247:     */  private native int nGetPbufferCapabilities(PixelFormat paramPixelFormat)
/* 1248:     */    throws LWJGLException;
/* 1249:     */  
/* 1250:     */  private static native long createIcon(int paramInt1, int paramInt2, IntBuffer paramIntBuffer);
/* 1251:     */  
/* 1252:     */  private static native void destroyIcon(long paramLong);
/* 1253:     */  
/* 1254:     */  private static native long sendMessage(long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/* 1255:     */  
/* 1256:     */  private static native long setWindowLongPtr(long paramLong1, int paramInt, long paramLong2);
/* 1257:     */  
/* 1258:     */  private static native long getWindowLongPtr(long paramLong, int paramInt);
/* 1259:     */  
/* 1260:     */  private static native boolean setWindowPos(long paramLong1, long paramLong2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong3);
/* 1261:     */  
/* 1262:     */  private static native long nSetCapture(long paramLong);
/* 1263:     */  
/* 1264:     */  private static native boolean nReleaseCapture();
/* 1265:     */  
/* 1266:     */  private static native void getClientRect(long paramLong, IntBuffer paramIntBuffer);
/* 1267:     */  
/* 1268:     */  private static native void clientToScreen(long paramLong, IntBuffer paramIntBuffer);
/* 1269:     */  
/* 1270:     */  private static native void setWindowProc(Method paramMethod);
/* 1271:     */  
/* 1272:     */  private static native long defWindowProc(long paramLong1, int paramInt, long paramLong2, long paramLong3);
/* 1273:     */  
/* 1274:     */  private native boolean getWindowRect(long paramLong, IntBuffer paramIntBuffer);
/* 1275:     */  
/* 1276:     */  private native boolean nTrackMouseEvent(long paramLong);
/* 1277:     */  
/* 1278:     */  private native boolean adjustWindowRectEx(IntBuffer paramIntBuffer, int paramInt1, boolean paramBoolean, int paramInt2);
/* 1279:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.WindowsDisplay
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */