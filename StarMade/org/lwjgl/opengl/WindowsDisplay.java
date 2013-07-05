/*      */ package org.lwjgl.opengl;
/*      */ 
/*      */ import java.awt.Canvas;
/*      */ import java.awt.KeyboardFocusManager;
/*      */ import java.lang.reflect.Method;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.FloatBuffer;
/*      */ import java.nio.IntBuffer;
/*      */ import javax.swing.SwingUtilities;
/*      */ import org.lwjgl.BufferUtils;
/*      */ import org.lwjgl.LWJGLException;
/*      */ import org.lwjgl.LWJGLUtil;
/*      */ import org.lwjgl.MemoryUtil;
/*      */ import org.lwjgl.input.Mouse;
/*      */ 
/*      */ final class WindowsDisplay
/*      */   implements DisplayImplementation
/*      */ {
/*      */   private static final int GAMMA_LENGTH = 256;
/*      */   private static final int WM_WINDOWPOSCHANGED = 71;
/*      */   private static final int WM_MOVE = 3;
/*      */   private static final int WM_CANCELMODE = 31;
/*      */   private static final int WM_MOUSEMOVE = 512;
/*      */   private static final int WM_LBUTTONDOWN = 513;
/*      */   private static final int WM_LBUTTONUP = 514;
/*      */   private static final int WM_LBUTTONDBLCLK = 515;
/*      */   private static final int WM_RBUTTONDOWN = 516;
/*      */   private static final int WM_RBUTTONUP = 517;
/*      */   private static final int WM_RBUTTONDBLCLK = 518;
/*      */   private static final int WM_MBUTTONDOWN = 519;
/*      */   private static final int WM_MBUTTONUP = 520;
/*      */   private static final int WM_MBUTTONDBLCLK = 521;
/*      */   private static final int WM_XBUTTONDOWN = 523;
/*      */   private static final int WM_XBUTTONUP = 524;
/*      */   private static final int WM_XBUTTONDBLCLK = 525;
/*      */   private static final int WM_MOUSEWHEEL = 522;
/*      */   private static final int WM_CAPTURECHANGED = 533;
/*      */   private static final int WM_MOUSELEAVE = 675;
/*      */   private static final int WM_ENTERSIZEMOVE = 561;
/*      */   private static final int WM_EXITSIZEMOVE = 562;
/*      */   private static final int WM_SIZING = 532;
/*      */   private static final int WM_KEYDOWN = 256;
/*      */   private static final int WM_KEYUP = 257;
/*      */   private static final int WM_SYSKEYUP = 261;
/*      */   private static final int WM_SYSKEYDOWN = 260;
/*      */   private static final int WM_SYSCHAR = 262;
/*      */   private static final int WM_CHAR = 258;
/*      */   private static final int WM_GETICON = 127;
/*      */   private static final int WM_SETICON = 128;
/*      */   private static final int WM_SETCURSOR = 32;
/*      */   private static final int WM_MOUSEACTIVATE = 33;
/*      */   private static final int WM_QUIT = 18;
/*      */   private static final int WM_SYSCOMMAND = 274;
/*      */   private static final int WM_PAINT = 15;
/*      */   private static final int WM_KILLFOCUS = 8;
/*      */   private static final int WM_SETFOCUS = 7;
/*      */   private static final int SC_SIZE = 61440;
/*      */   private static final int SC_MOVE = 61456;
/*      */   private static final int SC_MINIMIZE = 61472;
/*      */   private static final int SC_MAXIMIZE = 61488;
/*      */   private static final int SC_NEXTWINDOW = 61504;
/*      */   private static final int SC_PREVWINDOW = 61520;
/*      */   private static final int SC_CLOSE = 61536;
/*      */   private static final int SC_VSCROLL = 61552;
/*      */   private static final int SC_HSCROLL = 61568;
/*      */   private static final int SC_MOUSEMENU = 61584;
/*      */   private static final int SC_KEYMENU = 61696;
/*      */   private static final int SC_ARRANGE = 61712;
/*      */   private static final int SC_RESTORE = 61728;
/*      */   private static final int SC_TASKLIST = 61744;
/*      */   private static final int SC_SCREENSAVE = 61760;
/*      */   private static final int SC_HOTKEY = 61776;
/*      */   private static final int SC_DEFAULT = 61792;
/*      */   private static final int SC_MONITORPOWER = 61808;
/*      */   private static final int SC_CONTEXTHELP = 61824;
/*      */   private static final int SC_SEPARATOR = 61455;
/*      */   static final int SM_CXCURSOR = 13;
/*      */   static final int SM_CYCURSOR = 14;
/*      */   static final int SM_CMOUSEBUTTONS = 43;
/*      */   static final int SM_MOUSEWHEELPRESENT = 75;
/*      */   private static final int SIZE_RESTORED = 0;
/*      */   private static final int SIZE_MINIMIZED = 1;
/*      */   private static final int SIZE_MAXIMIZED = 2;
/*      */   private static final int WM_SIZE = 5;
/*      */   private static final int WM_ACTIVATE = 6;
/*      */   private static final int WA_INACTIVE = 0;
/*      */   private static final int WA_ACTIVE = 1;
/*      */   private static final int WA_CLICKACTIVE = 2;
/*      */   private static final int SW_NORMAL = 1;
/*      */   private static final int SW_SHOWMINNOACTIVE = 7;
/*      */   private static final int SW_SHOWDEFAULT = 10;
/*      */   private static final int SW_RESTORE = 9;
/*      */   private static final int SW_MAXIMIZE = 3;
/*      */   private static final int ICON_SMALL = 0;
/*      */   private static final int ICON_BIG = 1;
/*  140 */   private static final IntBuffer rect_buffer = BufferUtils.createIntBuffer(4);
/*  141 */   private static final Rect rect = new Rect(null);
/*      */   private static final long HWND_TOP = 0L;
/*      */   private static final long HWND_BOTTOM = 1L;
/*      */   private static final long HWND_TOPMOST = -1L;
/*      */   private static final long HWND_NOTOPMOST = -2L;
/*      */   private static final int SWP_NOSIZE = 1;
/*      */   private static final int SWP_NOMOVE = 2;
/*      */   private static final int SWP_NOZORDER = 4;
/*      */   private static final int SWP_FRAMECHANGED = 32;
/*      */   private static final int GWL_STYLE = -16;
/*      */   private static final int GWL_EXSTYLE = -20;
/*      */   private static final int WS_THICKFRAME = 262144;
/*      */   private static final int WS_MAXIMIZEBOX = 65536;
/*      */   private static final int HTCLIENT = 1;
/*      */   private static final int MK_XBUTTON1 = 32;
/*      */   private static final int MK_XBUTTON2 = 64;
/*      */   private static final int XBUTTON1 = 1;
/*      */   private static final int XBUTTON2 = 2;
/*      */   private static WindowsDisplay current_display;
/*      */   private static boolean cursor_clipped;
/*      */   private WindowsDisplayPeerInfo peer_info;
/*      */   private Object current_cursor;
/*      */   private Canvas parent;
/*      */   private static boolean hasParent;
/*      */   private WindowsKeyboard keyboard;
/*      */   private WindowsMouse mouse;
/*      */   private boolean close_requested;
/*      */   private boolean is_dirty;
/*      */   private ByteBuffer current_gamma;
/*      */   private ByteBuffer saved_gamma;
/*      */   private DisplayMode current_mode;
/*      */   private boolean mode_set;
/*      */   private boolean isMinimized;
/*      */   private boolean isFocused;
/*      */   private boolean redoMakeContextCurrent;
/*      */   private boolean inAppActivate;
/*      */   private boolean resized;
/*      */   private boolean resizable;
/*      */   private boolean maximized;
/*      */   private int x;
/*      */   private int y;
/*      */   private int width;
/*      */   private int height;
/*      */   private long hwnd;
/*      */   private long hdc;
/*      */   private long small_icon;
/*      */   private long large_icon;
/*      */   private boolean iconsLoaded;
/*  204 */   private int captureMouse = -1;
/*      */   private boolean trackingMouse;
/*      */   private boolean mouseInside;
/*      */ 
/*      */   WindowsDisplay()
/*      */   {
/*  218 */     current_display = this;
/*      */   }
/*      */ 
/*      */   public void createWindow(DrawableLWJGL drawable, DisplayMode mode, Canvas parent, int x, int y) throws LWJGLException {
/*  222 */     this.close_requested = false;
/*  223 */     this.is_dirty = false;
/*  224 */     this.isMinimized = false;
/*  225 */     this.isFocused = false;
/*  226 */     this.redoMakeContextCurrent = false;
/*  227 */     this.maximized = false;
/*  228 */     this.parent = parent;
/*  229 */     hasParent = parent != null;
/*  230 */     long parent_hwnd = parent != null ? getHwnd(parent) : 0L;
/*  231 */     this.hwnd = nCreateWindow(x, y, mode.getWidth(), mode.getHeight(), (Display.isFullscreen()) || (isUndecorated()), parent != null, parent_hwnd);
/*  232 */     this.resizable = false;
/*  233 */     if (this.hwnd == 0L) {
/*  234 */       throw new LWJGLException("Failed to create window");
/*      */     }
/*  236 */     this.hdc = getDC(this.hwnd);
/*  237 */     if (this.hdc == 0L) {
/*  238 */       nDestroyWindow(this.hwnd);
/*  239 */       throw new LWJGLException("Failed to get dc");
/*      */     }
/*      */     try
/*      */     {
/*  243 */       if ((drawable instanceof DrawableGL)) {
/*  244 */         int format = WindowsPeerInfo.choosePixelFormat(getHdc(), 0, 0, (PixelFormat)drawable.getPixelFormat(), null, true, true, false, true);
/*  245 */         WindowsPeerInfo.setPixelFormat(getHdc(), format);
/*      */       } else {
/*  247 */         this.peer_info = new WindowsDisplayPeerInfo(true);
/*  248 */         ((DrawableGLES)drawable).initialize(this.hwnd, this.hdc, 4, (org.lwjgl.opengles.PixelFormat)drawable.getPixelFormat());
/*      */       }
/*  250 */       this.peer_info.initDC(getHwnd(), getHdc());
/*  251 */       showWindow(getHwnd(), 10);
/*      */ 
/*  253 */       updateWidthAndHeight();
/*      */ 
/*  255 */       if (parent == null) {
/*  256 */         if (Display.isResizable()) {
/*  257 */           setResizable(true);
/*      */         }
/*  259 */         setForegroundWindow(getHwnd());
/*      */       }
/*  261 */       grabFocus();
/*      */     } catch (LWJGLException e) {
/*  263 */       nReleaseDC(this.hwnd, this.hdc);
/*  264 */       nDestroyWindow(this.hwnd);
/*  265 */       throw e;
/*      */     }
/*      */   }
/*      */ 
/*      */   private void updateWidthAndHeight() {
/*  270 */     getClientRect(this.hwnd, rect_buffer);
/*  271 */     rect.copyFromBuffer(rect_buffer);
/*  272 */     this.width = (rect.right - rect.left);
/*  273 */     this.height = (rect.bottom - rect.top);
/*      */   }
/*      */ 
/*      */   private static native long nCreateWindow(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean1, boolean paramBoolean2, long paramLong) throws LWJGLException;
/*      */ 
/*      */   private static boolean isUndecorated() {
/*  279 */     return Display.getPrivilegedBoolean("org.lwjgl.opengl.Window.undecorated");
/*      */   }
/*      */ 
/*      */   private static long getHwnd(Canvas parent) throws LWJGLException {
/*  283 */     AWTCanvasImplementation awt_impl = AWTGLCanvas.createImplementation();
/*  284 */     WindowsPeerInfo parent_peer_info = (WindowsPeerInfo)awt_impl.createPeerInfo(parent, null, null);
/*  285 */     ByteBuffer parent_peer_info_handle = parent_peer_info.lockAndGetHandle();
/*      */     try {
/*  287 */       return parent_peer_info.getHwnd();
/*      */     } finally {
/*  289 */       parent_peer_info.unlock();
/*      */     }
/*      */   }
/*      */ 
/*      */   public void destroyWindow() {
/*  294 */     nReleaseDC(this.hwnd, this.hdc);
/*  295 */     nDestroyWindow(this.hwnd);
/*  296 */     freeLargeIcon();
/*  297 */     freeSmallIcon();
/*  298 */     resetCursorClipping(); } 
/*      */   private static native void nReleaseDC(long paramLong1, long paramLong2);
/*      */ 
/*      */   private static native void nDestroyWindow(long paramLong);
/*      */ 
/*  303 */   static void resetCursorClipping() { if (cursor_clipped) {
/*      */       try {
/*  305 */         clipCursor(null);
/*      */       } catch (LWJGLException e) {
/*  307 */         LWJGLUtil.log("Failed to reset cursor clipping: " + e);
/*      */       }
/*  309 */       cursor_clipped = false;
/*      */     } }
/*      */ 
/*      */   private static void getGlobalClientRect(long hwnd, Rect rect)
/*      */   {
/*  314 */     rect_buffer.put(0, 0).put(1, 0);
/*  315 */     clientToScreen(hwnd, rect_buffer);
/*  316 */     int offset_x = rect_buffer.get(0);
/*  317 */     int offset_y = rect_buffer.get(1);
/*  318 */     getClientRect(hwnd, rect_buffer);
/*  319 */     rect.copyFromBuffer(rect_buffer);
/*  320 */     rect.offset(offset_x, offset_y);
/*      */   }
/*      */ 
/*      */   static void setupCursorClipping(long hwnd) throws LWJGLException {
/*  324 */     cursor_clipped = true;
/*  325 */     getGlobalClientRect(hwnd, rect);
/*  326 */     rect.copyToBuffer(rect_buffer);
/*  327 */     clipCursor(rect_buffer);
/*      */   }
/*      */   private static native void clipCursor(IntBuffer paramIntBuffer) throws LWJGLException;
/*      */ 
/*      */   public void switchDisplayMode(DisplayMode mode) throws LWJGLException {
/*  332 */     nSwitchDisplayMode(mode);
/*  333 */     this.current_mode = mode;
/*  334 */     this.mode_set = true;
/*      */   }
/*      */ 
/*      */   private static native void nSwitchDisplayMode(DisplayMode paramDisplayMode)
/*      */     throws LWJGLException;
/*      */ 
/*      */   private void appActivate(boolean active)
/*      */   {
/*  342 */     if (this.inAppActivate) {
/*  343 */       return;
/*      */     }
/*  345 */     this.inAppActivate = true;
/*  346 */     this.isFocused = active;
/*  347 */     if (active) {
/*  348 */       if (Display.isFullscreen()) {
/*  349 */         restoreDisplayMode();
/*      */       }
/*  351 */       if (this.parent == null) {
/*  352 */         if (this.maximized)
/*  353 */           showWindow(getHwnd(), 3);
/*      */         else {
/*  355 */           showWindow(getHwnd(), 9);
/*      */         }
/*  357 */         setForegroundWindow(getHwnd());
/*      */       }
/*  359 */       setFocus(getHwnd());
/*  360 */       this.redoMakeContextCurrent = true;
/*  361 */       if (Display.isFullscreen()) {
/*  362 */         updateClipping();
/*      */       }
/*  364 */       if (this.keyboard != null)
/*  365 */         this.keyboard.fireLostKeyEvents();
/*  366 */     } else if (Display.isFullscreen()) {
/*  367 */       showWindow(getHwnd(), 7);
/*  368 */       resetDisplayMode();
/*      */     } else {
/*  370 */       updateClipping();
/*  371 */     }updateCursor();
/*  372 */     this.inAppActivate = false; } 
/*      */   private static native void showWindow(long paramLong, int paramInt);
/*      */ 
/*      */   private static native void setForegroundWindow(long paramLong);
/*      */ 
/*      */   private static native void setFocus(long paramLong);
/*      */ 
/*  379 */   private void grabFocus() { if (this.parent == null)
/*  380 */       setFocus(getHwnd());
/*      */     else
/*  382 */       SwingUtilities.invokeLater(new Runnable() {
/*      */         public void run() {
/*  384 */           WindowsDisplay.this.parent.requestFocus();
/*      */         }
/*      */       }); }
/*      */ 
/*      */   private void restoreDisplayMode()
/*      */   {
/*      */     try {
/*  391 */       doSetGammaRamp(this.current_gamma);
/*      */     } catch (LWJGLException e) {
/*  393 */       LWJGLUtil.log("Failed to restore gamma: " + e.getMessage());
/*      */     }
/*      */ 
/*  396 */     if (!this.mode_set) {
/*  397 */       this.mode_set = true;
/*      */       try {
/*  399 */         nSwitchDisplayMode(this.current_mode);
/*      */       } catch (LWJGLException e) {
/*  401 */         LWJGLUtil.log("Failed to restore display mode: " + e.getMessage());
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public void resetDisplayMode() {
/*      */     try {
/*  408 */       doSetGammaRamp(this.saved_gamma);
/*      */     } catch (LWJGLException e) {
/*  410 */       LWJGLUtil.log("Failed to reset gamma ramp: " + e.getMessage());
/*      */     }
/*  412 */     this.current_gamma = this.saved_gamma;
/*  413 */     if (this.mode_set) {
/*  414 */       this.mode_set = false;
/*  415 */       nResetDisplayMode();
/*      */     }
/*  417 */     resetCursorClipping();
/*      */   }
/*      */   private static native void nResetDisplayMode();
/*      */ 
/*      */   public int getGammaRampLength() {
/*  422 */     return 256;
/*      */   }
/*      */ 
/*      */   public void setGammaRamp(FloatBuffer gammaRamp) throws LWJGLException {
/*  426 */     doSetGammaRamp(convertToNativeRamp(gammaRamp));
/*      */   }
/*      */   private static native ByteBuffer convertToNativeRamp(FloatBuffer paramFloatBuffer) throws LWJGLException;
/*      */ 
/*      */   private static native ByteBuffer getCurrentGammaRamp() throws LWJGLException;
/*      */ 
/*  432 */   private void doSetGammaRamp(ByteBuffer native_gamma) throws LWJGLException { nSetGammaRamp(native_gamma);
/*  433 */     this.current_gamma = native_gamma; }
/*      */ 
/*      */   private static native void nSetGammaRamp(ByteBuffer paramByteBuffer) throws LWJGLException;
/*      */ 
/*      */   public String getAdapter() {
/*      */     try {
/*  439 */       String maxObjNo = WindowsRegistry.queryRegistrationKey(3, "HARDWARE\\DeviceMap\\Video", "MaxObjectNumber");
/*      */ 
/*  443 */       int maxObjectNumber = maxObjNo.charAt(0);
/*  444 */       String vga_driver_value = "";
/*  445 */       for (int i = 0; i < maxObjectNumber; i++) {
/*  446 */         String adapter_string = WindowsRegistry.queryRegistrationKey(3, "HARDWARE\\DeviceMap\\Video", "\\Device\\Video" + i);
/*      */ 
/*  450 */         String root_key = "\\registry\\machine\\";
/*  451 */         if (adapter_string.toLowerCase().startsWith(root_key)) {
/*  452 */           String driver_value = WindowsRegistry.queryRegistrationKey(3, adapter_string.substring(root_key.length()), "InstalledDisplayDrivers");
/*      */ 
/*  456 */           if (driver_value.toUpperCase().startsWith("VGA"))
/*  457 */             vga_driver_value = driver_value;
/*  458 */           else if ((!driver_value.toUpperCase().startsWith("RDP")) && (!driver_value.toUpperCase().startsWith("NMNDD"))) {
/*  459 */             return driver_value;
/*      */           }
/*      */         }
/*      */       }
/*  463 */       if (!vga_driver_value.equals(""))
/*  464 */         return vga_driver_value;
/*      */     }
/*      */     catch (LWJGLException e) {
/*  467 */       LWJGLUtil.log("Exception occurred while querying registry: " + e);
/*      */     }
/*  469 */     return null;
/*      */   }
/*      */ 
/*      */   public String getVersion() {
/*  473 */     String driver = getAdapter();
/*  474 */     if (driver != null) {
/*  475 */       String[] drivers = driver.split(",");
/*  476 */       if (drivers.length > 0) {
/*  477 */         WindowsFileVersion version = nGetVersion(drivers[0] + ".dll");
/*  478 */         if (version != null)
/*  479 */           return version.toString();
/*      */       }
/*      */     }
/*  482 */     return null;
/*      */   }
/*      */   private native WindowsFileVersion nGetVersion(String paramString);
/*      */ 
/*      */   public DisplayMode init() throws LWJGLException {
/*  487 */     this.current_gamma = (this.saved_gamma = getCurrentGammaRamp());
/*  488 */     return this.current_mode = getCurrentDisplayMode();
/*      */   }
/*      */   private static native DisplayMode getCurrentDisplayMode() throws LWJGLException;
/*      */ 
/*      */   public void setTitle(String title) {
/*  493 */     ByteBuffer buffer = MemoryUtil.encodeUTF16(title);
/*  494 */     nSetTitle(this.hwnd, MemoryUtil.getAddress0(buffer));
/*      */   }
/*      */   private static native void nSetTitle(long paramLong1, long paramLong2);
/*      */ 
/*      */   public boolean isCloseRequested() {
/*  499 */     boolean saved = this.close_requested;
/*  500 */     this.close_requested = false;
/*  501 */     return saved;
/*      */   }
/*      */ 
/*      */   public boolean isVisible() {
/*  505 */     return !this.isMinimized;
/*      */   }
/*      */ 
/*      */   public boolean isActive() {
/*  509 */     return this.isFocused;
/*      */   }
/*      */ 
/*      */   public boolean isDirty() {
/*  513 */     boolean saved = this.is_dirty;
/*  514 */     this.is_dirty = false;
/*  515 */     return saved;
/*      */   }
/*      */ 
/*      */   public PeerInfo createPeerInfo(PixelFormat pixel_format, ContextAttribs attribs) throws LWJGLException {
/*  519 */     this.peer_info = new WindowsDisplayPeerInfo(false);
/*  520 */     return this.peer_info;
/*      */   }
/*      */ 
/*      */   public void update() {
/*  524 */     nUpdate();
/*      */ 
/*  526 */     if ((!this.isFocused) && (this.parent != null) && (this.parent.isFocusOwner())) {
/*  527 */       KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
/*  528 */       setFocus(getHwnd());
/*      */     }
/*      */ 
/*  531 */     if (this.redoMakeContextCurrent) {
/*  532 */       this.redoMakeContextCurrent = false;
/*      */       try
/*      */       {
/*  539 */         Context context = ((DrawableLWJGL)Display.getDrawable()).getContext();
/*  540 */         if ((context != null) && (context.isCurrent()))
/*  541 */           context.makeCurrent();
/*      */       } catch (LWJGLException e) {
/*  543 */         LWJGLUtil.log("Exception occurred while trying to make context current: " + e);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private static native void nUpdate();
/*      */ 
/*  550 */   public void reshape(int x, int y, int width, int height) { nReshape(getHwnd(), x, y, width, height, (Display.isFullscreen()) || (isUndecorated()), this.parent != null); }
/*      */ 
/*      */   private static native void nReshape(long paramLong, int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean1, boolean paramBoolean2);
/*      */ 
/*      */   public native DisplayMode[] getAvailableDisplayModes() throws LWJGLException;
/*      */ 
/*      */   public boolean hasWheel() {
/*  557 */     return this.mouse.hasWheel();
/*      */   }
/*      */ 
/*      */   public int getButtonCount() {
/*  561 */     return this.mouse.getButtonCount();
/*      */   }
/*      */ 
/*      */   public void createMouse() throws LWJGLException {
/*  565 */     this.mouse = new WindowsMouse(getHwnd());
/*      */   }
/*      */ 
/*      */   public void destroyMouse() {
/*  569 */     if (this.mouse != null)
/*  570 */       this.mouse.destroy();
/*  571 */     this.mouse = null;
/*      */   }
/*      */ 
/*      */   public void pollMouse(IntBuffer coord_buffer, ByteBuffer buttons) {
/*  575 */     this.mouse.poll(coord_buffer, buttons);
/*      */   }
/*      */ 
/*      */   public void readMouse(ByteBuffer buffer) {
/*  579 */     this.mouse.read(buffer);
/*      */   }
/*      */ 
/*      */   public void grabMouse(boolean grab) {
/*  583 */     this.mouse.grab(grab, shouldGrab());
/*  584 */     updateCursor();
/*      */   }
/*      */ 
/*      */   public int getNativeCursorCapabilities() {
/*  588 */     return 1;
/*      */   }
/*      */ 
/*      */   public void setCursorPosition(int x, int y) {
/*  592 */     getGlobalClientRect(getHwnd(), rect);
/*  593 */     int transformed_x = rect.left + x;
/*  594 */     int transformed_y = rect.bottom - 1 - y;
/*  595 */     nSetCursorPosition(transformed_x, transformed_y);
/*  596 */     setMousePosition(x, y);
/*      */   }
/*      */   private static native void nSetCursorPosition(int paramInt1, int paramInt2);
/*      */ 
/*      */   public void setNativeCursor(Object handle) throws LWJGLException {
/*  601 */     this.current_cursor = handle;
/*  602 */     updateCursor();
/*      */   }
/*      */ 
/*      */   private void updateCursor() {
/*      */     try {
/*  607 */       if ((this.mouse != null) && (shouldGrab()))
/*  608 */         nSetNativeCursor(getHwnd(), this.mouse.getBlankCursor());
/*      */       else
/*  610 */         nSetNativeCursor(getHwnd(), this.current_cursor);
/*      */     } catch (LWJGLException e) {
/*  612 */       LWJGLUtil.log("Failed to update cursor: " + e);
/*      */     }
/*      */   }
/*      */ 
/*      */   static native void nSetNativeCursor(long paramLong, Object paramObject) throws LWJGLException;
/*      */ 
/*  618 */   public int getMinCursorSize() { return getSystemMetrics(13); }
/*      */ 
/*      */   public int getMaxCursorSize()
/*      */   {
/*  622 */     return getSystemMetrics(13);
/*      */   }
/*      */ 
/*      */   static native int getSystemMetrics(int paramInt);
/*      */ 
/*      */   private static native long getDllInstance();
/*      */ 
/*      */   private long getHwnd() {
/*  630 */     return this.hwnd;
/*      */   }
/*      */ 
/*      */   private long getHdc() {
/*  634 */     return this.hdc;
/*      */   }
/*      */   private static native long getDC(long paramLong);
/*      */ 
/*      */   private static native long getDesktopWindow();
/*      */ 
/*      */   private static native long getForegroundWindow();
/*      */ 
/*  642 */   static void centerCursor(long hwnd) { if ((getForegroundWindow() != hwnd) && (!hasParent))
/*  643 */       return;
/*  644 */     getGlobalClientRect(hwnd, rect);
/*  645 */     int local_offset_x = rect.left;
/*  646 */     int local_offset_y = rect.top;
/*      */ 
/*  651 */     int center_x = (rect.left + rect.right) / 2;
/*  652 */     int center_y = (rect.top + rect.bottom) / 2;
/*  653 */     nSetCursorPosition(center_x, center_y);
/*  654 */     int local_x = center_x - local_offset_x;
/*  655 */     int local_y = center_y - local_offset_y;
/*  656 */     if (current_display != null)
/*  657 */       current_display.setMousePosition(local_x, transformY(hwnd, local_y)); }
/*      */ 
/*      */   private void setMousePosition(int x, int y)
/*      */   {
/*  661 */     if (this.mouse != null)
/*  662 */       this.mouse.setPosition(x, y);
/*      */   }
/*      */ 
/*      */   public void createKeyboard() throws LWJGLException
/*      */   {
/*  667 */     this.keyboard = new WindowsKeyboard(getHwnd());
/*      */   }
/*      */ 
/*      */   public void destroyKeyboard() {
/*  671 */     this.keyboard.destroy();
/*  672 */     this.keyboard = null;
/*      */   }
/*      */ 
/*      */   public void pollKeyboard(ByteBuffer keyDownBuffer) {
/*  676 */     this.keyboard.poll(keyDownBuffer);
/*      */   }
/*      */ 
/*      */   public void readKeyboard(ByteBuffer buffer) {
/*  680 */     this.keyboard.read(buffer);
/*      */   }
/*      */ 
/*      */   public static native ByteBuffer nCreateCursor(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, IntBuffer paramIntBuffer1, int paramInt6, IntBuffer paramIntBuffer2, int paramInt7)
/*      */     throws LWJGLException;
/*      */ 
/*      */   public Object createCursor(int width, int height, int xHotspot, int yHotspot, int numImages, IntBuffer images, IntBuffer delays) throws LWJGLException
/*      */   {
/*  688 */     return doCreateCursor(width, height, xHotspot, yHotspot, numImages, images, delays);
/*      */   }
/*      */ 
/*      */   static Object doCreateCursor(int width, int height, int xHotspot, int yHotspot, int numImages, IntBuffer images, IntBuffer delays) throws LWJGLException {
/*  692 */     return nCreateCursor(width, height, xHotspot, yHotspot, numImages, images, images.position(), delays, delays != null ? delays.position() : -1);
/*      */   }
/*      */ 
/*      */   public void destroyCursor(Object cursorHandle) {
/*  696 */     doDestroyCursor(cursorHandle);
/*      */   }
/*      */ 
/*      */   static native void doDestroyCursor(Object paramObject);
/*      */ 
/*      */   public int getPbufferCapabilities() {
/*      */     try {
/*  703 */       return nGetPbufferCapabilities(new PixelFormat(0, 0, 0, 0, 0, 0, 0, 0, false));
/*      */     } catch (LWJGLException e) {
/*  705 */       LWJGLUtil.log("Exception occurred while determining pbuffer capabilities: " + e);
/*  706 */     }return 0;
/*      */   }
/*      */ 
/*      */   private native int nGetPbufferCapabilities(PixelFormat paramPixelFormat) throws LWJGLException;
/*      */ 
/*      */   public boolean isBufferLost(PeerInfo handle) {
/*  712 */     return ((WindowsPbufferPeerInfo)handle).isBufferLost();
/*      */   }
/*      */ 
/*      */   public PeerInfo createPbuffer(int width, int height, PixelFormat pixel_format, ContextAttribs attribs, IntBuffer pixelFormatCaps, IntBuffer pBufferAttribs)
/*      */     throws LWJGLException
/*      */   {
/*  718 */     return new WindowsPbufferPeerInfo(width, height, pixel_format, pixelFormatCaps, pBufferAttribs);
/*      */   }
/*      */ 
/*      */   public void setPbufferAttrib(PeerInfo handle, int attrib, int value) {
/*  722 */     ((WindowsPbufferPeerInfo)handle).setPbufferAttrib(attrib, value);
/*      */   }
/*      */ 
/*      */   public void bindTexImageToPbuffer(PeerInfo handle, int buffer) {
/*  726 */     ((WindowsPbufferPeerInfo)handle).bindTexImageToPbuffer(buffer);
/*      */   }
/*      */ 
/*      */   public void releaseTexImageFromPbuffer(PeerInfo handle, int buffer) {
/*  730 */     ((WindowsPbufferPeerInfo)handle).releaseTexImageFromPbuffer(buffer);
/*      */   }
/*      */ 
/*      */   private void freeSmallIcon() {
/*  734 */     if (this.small_icon != 0L) {
/*  735 */       destroyIcon(this.small_icon);
/*  736 */       this.small_icon = 0L;
/*      */     }
/*      */   }
/*      */ 
/*      */   private void freeLargeIcon() {
/*  741 */     if (this.large_icon != 0L) {
/*  742 */       destroyIcon(this.large_icon);
/*  743 */       this.large_icon = 0L;
/*      */     }
/*      */   }
/*      */ 
/*      */   public int setIcon(ByteBuffer[] icons)
/*      */   {
/*  760 */     boolean done_small = false;
/*  761 */     boolean done_large = false;
/*  762 */     int used = 0;
/*      */ 
/*  764 */     int small_icon_size = 16;
/*  765 */     int large_icon_size = 32;
/*  766 */     for (ByteBuffer icon : icons) {
/*  767 */       int size = icon.limit() / 4;
/*      */ 
/*  769 */       if (((int)Math.sqrt(size) == small_icon_size) && (!done_small)) {
/*  770 */         long small_new_icon = createIcon(small_icon_size, small_icon_size, icon.asIntBuffer());
/*  771 */         sendMessage(this.hwnd, 128L, 0L, small_new_icon);
/*  772 */         freeSmallIcon();
/*  773 */         this.small_icon = small_new_icon;
/*  774 */         used++;
/*  775 */         done_small = true;
/*      */       }
/*  777 */       if (((int)Math.sqrt(size) == large_icon_size) && (!done_large)) {
/*  778 */         long large_new_icon = createIcon(large_icon_size, large_icon_size, icon.asIntBuffer());
/*  779 */         sendMessage(this.hwnd, 128L, 1L, large_new_icon);
/*  780 */         freeLargeIcon();
/*  781 */         this.large_icon = large_new_icon;
/*  782 */         used++;
/*  783 */         done_large = true;
/*      */ 
/*  789 */         this.iconsLoaded = false;
/*      */ 
/*  792 */         long time = System.nanoTime();
/*  793 */         long MAX_WAIT = 500000000L;
/*      */         while (true) {
/*  795 */           nUpdate();
/*  796 */           if ((this.iconsLoaded) || (MAX_WAIT < System.nanoTime() - time)) {
/*      */             break;
/*      */           }
/*  799 */           Thread.yield();
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*  804 */     return used; } 
/*      */   private static native long createIcon(int paramInt1, int paramInt2, IntBuffer paramIntBuffer);
/*      */ 
/*      */   private static native void destroyIcon(long paramLong);
/*      */ 
/*      */   private static native long sendMessage(long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/*      */ 
/*      */   private static native long setWindowLongPtr(long paramLong1, int paramInt, long paramLong2);
/*      */ 
/*      */   private static native long getWindowLongPtr(long paramLong, int paramInt);
/*      */ 
/*      */   private static native boolean setWindowPos(long paramLong1, long paramLong2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong3);
/*      */ 
/*  814 */   private void handleMouseButton(int button, int state, long millis) { if (this.mouse != null) {
/*  815 */       this.mouse.handleMouseButton((byte)button, (byte)state, millis);
/*      */ 
/*  818 */       if ((this.captureMouse == -1) && (button != -1) && (state == 1)) {
/*  819 */         this.captureMouse = button;
/*  820 */         nSetCapture(this.hwnd);
/*      */       }
/*      */ 
/*  824 */       if ((this.captureMouse != -1) && (button == this.captureMouse) && (state == 0)) {
/*  825 */         this.captureMouse = -1;
/*  826 */         nReleaseCapture();
/*      */       }
/*      */     } }
/*      */ 
/*      */   private boolean shouldGrab()
/*      */   {
/*  832 */     return (!this.isMinimized) && (this.isFocused) && (Mouse.isGrabbed());
/*      */   }
/*      */ 
/*      */   private void handleMouseMoved(int x, int y, long millis) {
/*  836 */     if (this.mouse != null)
/*  837 */       this.mouse.handleMouseMoved(x, y, millis, shouldGrab());
/*      */   }
/*      */ 
/*      */   private static native long nSetCapture(long paramLong);
/*      */ 
/*      */   private static native boolean nReleaseCapture();
/*      */ 
/*      */   private void handleMouseScrolled(int amount, long millis) {
/*  845 */     if (this.mouse != null)
/*  846 */       this.mouse.handleMouseScrolled(amount, millis);
/*      */   }
/*      */ 
/*      */   private static native void getClientRect(long paramLong, IntBuffer paramIntBuffer);
/*      */ 
/*      */   private void handleChar(long wParam, long lParam, long millis) {
/*  852 */     byte previous_state = (byte)(int)(lParam >>> 30 & 1L);
/*  853 */     byte state = (byte)(int)(1L - (lParam >>> 31 & 1L));
/*  854 */     boolean repeat = state == previous_state;
/*  855 */     if (this.keyboard != null)
/*  856 */       this.keyboard.handleChar((int)(wParam & 0xFFFF), millis, repeat);
/*      */   }
/*      */ 
/*      */   private void handleKeyButton(long wParam, long lParam, long millis) {
/*  860 */     byte previous_state = (byte)(int)(lParam >>> 30 & 1L);
/*  861 */     byte state = (byte)(int)(1L - (lParam >>> 31 & 1L));
/*  862 */     boolean repeat = state == previous_state;
/*  863 */     byte extended = (byte)(int)(lParam >>> 24 & 1L);
/*  864 */     int scan_code = (int)(lParam >>> 16 & 0xFF);
/*  865 */     if (this.keyboard != null)
/*  866 */       this.keyboard.handleKey((int)wParam, scan_code, extended != 0, state, millis, repeat);
/*      */   }
/*      */ 
/*      */   private static int transformY(long hwnd, int y)
/*      */   {
/*  871 */     getClientRect(hwnd, rect_buffer);
/*  872 */     rect.copyFromBuffer(rect_buffer);
/*  873 */     return rect.bottom - rect.top - 1 - y;
/*      */   }
/*      */ 
/*      */   private static native void clientToScreen(long paramLong, IntBuffer paramIntBuffer);
/*      */ 
/*      */   private static native void setWindowProc(Method paramMethod);
/*      */ 
/*      */   private static long handleMessage(long hwnd, int msg, long wParam, long lParam, long millis) {
/*  881 */     if (current_display != null) {
/*  882 */       return current_display.doHandleMessage(hwnd, msg, wParam, lParam, millis);
/*      */     }
/*  884 */     return defWindowProc(hwnd, msg, wParam, lParam);
/*      */   }
/*      */ 
/*      */   private static native long defWindowProc(long paramLong1, int paramInt, long paramLong2, long paramLong3);
/*      */ 
/*      */   private void checkCursorState() {
/*  890 */     updateClipping();
/*      */   }
/*      */ 
/*      */   private void updateClipping() {
/*  894 */     if (((Display.isFullscreen()) || ((this.mouse != null) && (this.mouse.isGrabbed()))) && (!this.isMinimized) && (this.isFocused) && ((getForegroundWindow() == getHwnd()) || (hasParent)))
/*      */       try {
/*  896 */         setupCursorClipping(getHwnd());
/*      */       } catch (LWJGLException e) {
/*  898 */         LWJGLUtil.log("setupCursorClipping failed: " + e.getMessage());
/*      */       }
/*      */     else
/*  901 */       resetCursorClipping();
/*      */   }
/*      */ 
/*      */   private void setMinimized(boolean m)
/*      */   {
/*  906 */     this.isMinimized = m;
/*  907 */     checkCursorState();
/*      */   }
/*      */ 
/*      */   private long doHandleMessage(long hwnd, int msg, long wParam, long lParam, long millis)
/*      */   {
/*  920 */     switch (msg)
/*      */     {
/*      */     case 6:
/*  932 */       return 0L;
/*      */     case 5:
/*  934 */       switch ((int)wParam) {
/*      */       case 0:
/*      */       case 2:
/*  937 */         this.maximized = ((int)wParam == 2);
/*  938 */         this.resized = true;
/*  939 */         updateWidthAndHeight();
/*  940 */         setMinimized(false);
/*  941 */         break;
/*      */       case 1:
/*  943 */         setMinimized(true);
/*      */       }
/*      */ 
/*  946 */       break;
/*      */     case 532:
/*  948 */       this.resized = true;
/*  949 */       updateWidthAndHeight();
/*  950 */       break;
/*      */     case 32:
/*  952 */       if ((lParam & 0xFFFF) == 1L)
/*      */       {
/*  955 */         updateCursor();
/*  956 */         return -1L;
/*      */       }
/*      */ 
/*  959 */       return defWindowProc(hwnd, msg, wParam, lParam);
/*      */     case 8:
/*  962 */       appActivate(false);
/*  963 */       return 0L;
/*      */     case 7:
/*  965 */       appActivate(true);
/*  966 */       return 0L;
/*      */     case 33:
/*  968 */       if (!this.isFocused) {
/*  969 */         grabFocus();
/*      */       }
/*  971 */       return 3L;
/*      */     case 512:
/*  973 */       int xPos = (short)(int)(lParam & 0xFFFF);
/*  974 */       int yPos = transformY(getHwnd(), (short)(int)(lParam >> 16 & 0xFFFF));
/*  975 */       handleMouseMoved(xPos, yPos, millis);
/*  976 */       checkCursorState();
/*  977 */       this.mouseInside = true;
/*  978 */       if (!this.trackingMouse) {
/*  979 */         this.trackingMouse = nTrackMouseEvent(hwnd);
/*      */       }
/*  981 */       return 0L;
/*      */     case 522:
/*  983 */       int dwheel = (short)(int)(wParam >> 16 & 0xFFFF);
/*  984 */       handleMouseScrolled(dwheel, millis);
/*  985 */       return 0L;
/*      */     case 513:
/*  987 */       handleMouseButton(0, 1, millis);
/*  988 */       return 0L;
/*      */     case 514:
/*  990 */       handleMouseButton(0, 0, millis);
/*  991 */       return 0L;
/*      */     case 516:
/*  993 */       handleMouseButton(1, 1, millis);
/*  994 */       return 0L;
/*      */     case 517:
/*  996 */       handleMouseButton(1, 0, millis);
/*  997 */       return 0L;
/*      */     case 519:
/*  999 */       handleMouseButton(2, 1, millis);
/* 1000 */       return 0L;
/*      */     case 520:
/* 1002 */       handleMouseButton(2, 0, millis);
/* 1003 */       return 0L;
/*      */     case 524:
/* 1005 */       if (wParam >> 16 == 1L)
/* 1006 */         handleMouseButton(3, 0, millis);
/*      */       else {
/* 1008 */         handleMouseButton(4, 0, millis);
/*      */       }
/* 1010 */       return 1L;
/*      */     case 523:
/* 1012 */       if ((wParam & 0xFF) == 32L)
/* 1013 */         handleMouseButton(3, 1, millis);
/*      */       else {
/* 1015 */         handleMouseButton(4, 1, millis);
/*      */       }
/* 1017 */       return 1L;
/*      */     case 258:
/*      */     case 262:
/* 1020 */       handleChar(wParam, lParam, millis);
/* 1021 */       return 0L;
/*      */     case 257:
/*      */     case 261:
/* 1026 */       if ((wParam == 44L) && (this.keyboard != null) && (!this.keyboard.isKeyDown(183)))
/*      */       {
/* 1029 */         long fake_lparam = lParam & 0x7FFFFFFF;
/*      */ 
/* 1031 */         fake_lparam &= -1073741825L;
/* 1032 */         handleKeyButton(wParam, fake_lparam, millis);
/*      */       }
/*      */ 
/*      */     case 256:
/*      */     case 260:
/* 1038 */       handleKeyButton(wParam, lParam, millis);
/* 1039 */       break;
/*      */     case 18:
/* 1041 */       this.close_requested = true;
/* 1042 */       return 0L;
/*      */     case 274:
/* 1044 */       switch ((int)(wParam & 0xFFF0)) {
/*      */       case 61584:
/*      */       case 61696:
/*      */       case 61760:
/*      */       case 61808:
/* 1049 */         return 0L;
/*      */       case 61536:
/* 1051 */         this.close_requested = true;
/* 1052 */         return 0L;
/*      */       }
/* 1054 */       break;
/*      */     case 15:
/* 1058 */       this.is_dirty = true;
/* 1059 */       break;
/*      */     case 675:
/* 1061 */       this.mouseInside = false;
/* 1062 */       this.trackingMouse = false;
/* 1063 */       break;
/*      */     case 31:
/* 1065 */       nReleaseCapture();
/*      */     case 533:
/* 1068 */       if (this.captureMouse != -1) {
/* 1069 */         handleMouseButton(this.captureMouse, 0, millis);
/* 1070 */         this.captureMouse = -1;
/*      */       }
/* 1072 */       return 0L;
/*      */     case 71:
/* 1074 */       if (getWindowRect(hwnd, rect_buffer)) {
/* 1075 */         rect.copyFromBuffer(rect_buffer);
/* 1076 */         this.x = rect.top;
/* 1077 */         this.y = rect.bottom;
/*      */       } else {
/* 1079 */         LWJGLUtil.log("WM_WINDOWPOSCHANGED: Unable to get window rect");
/*      */       }
/* 1081 */       break;
/*      */     case 127:
/* 1083 */       this.iconsLoaded = true;
/*      */     }
/*      */ 
/* 1087 */     return defWindowProc(hwnd, msg, wParam, lParam);
/*      */   }
/*      */ 
/*      */   private native boolean getWindowRect(long paramLong, IntBuffer paramIntBuffer);
/*      */ 
/*      */   public int getX() {
/* 1093 */     return this.x;
/*      */   }
/*      */ 
/*      */   public int getY() {
/* 1097 */     return this.y;
/*      */   }
/*      */ 
/*      */   public int getWidth() {
/* 1101 */     return this.width;
/*      */   }
/*      */ 
/*      */   public int getHeight() {
/* 1105 */     return this.height;
/*      */   }
/*      */ 
/*      */   private native boolean nTrackMouseEvent(long paramLong);
/*      */ 
/*      */   public boolean isInsideWindow() {
/* 1111 */     return this.mouseInside;
/*      */   }
/*      */ 
/*      */   public void setResizable(boolean resizable) {
/* 1115 */     if (this.resizable != resizable) {
/* 1116 */       int style = (int)getWindowLongPtr(this.hwnd, -16);
/* 1117 */       int styleex = (int)getWindowLongPtr(this.hwnd, -20);
/*      */ 
/* 1120 */       if ((resizable) && (!Display.isFullscreen()))
/* 1121 */         setWindowLongPtr(this.hwnd, -16, style |= 327680);
/*      */       else {
/* 1123 */         setWindowLongPtr(this.hwnd, -16, style &= -327681);
/*      */       }
/*      */ 
/* 1128 */       getClientRect(this.hwnd, rect_buffer);
/* 1129 */       rect.copyFromBuffer(rect_buffer);
/* 1130 */       adjustWindowRectEx(rect_buffer, style, false, styleex);
/* 1131 */       rect.copyFromBuffer(rect_buffer);
/*      */ 
/* 1134 */       setWindowPos(this.hwnd, 0L, 0, 0, rect.right - rect.left, rect.bottom - rect.top, 38L);
/*      */ 
/* 1136 */       updateWidthAndHeight();
/* 1137 */       this.resized = false;
/*      */     }
/* 1139 */     this.resizable = resizable;
/*      */   }
/*      */ 
/*      */   private native boolean adjustWindowRectEx(IntBuffer paramIntBuffer, int paramInt1, boolean paramBoolean, int paramInt2);
/*      */ 
/*      */   public boolean wasResized() {
/* 1145 */     if (this.resized) {
/* 1146 */       this.resized = false;
/* 1147 */       return true;
/*      */     }
/* 1149 */     return false;
/*      */   }
/*      */ 
/*      */   static
/*      */   {
/*      */     try
/*      */     {
/*  210 */       Method windowProc = WindowsDisplay.class.getDeclaredMethod("handleMessage", new Class[] { Long.TYPE, Integer.TYPE, Long.TYPE, Long.TYPE, Long.TYPE });
/*  211 */       setWindowProc(windowProc);
/*      */     } catch (NoSuchMethodException e) {
/*  213 */       throw new RuntimeException(e);
/*      */     }
/*      */   }
/*      */ 
/*      */   private static final class Rect
/*      */   {
/*      */     public int top;
/*      */     public int bottom;
/*      */     public int left;
/*      */     public int right;
/*      */ 
/*      */     public void copyToBuffer(IntBuffer buffer)
/*      */     {
/* 1159 */       buffer.put(0, this.top).put(1, this.bottom).put(2, this.left).put(3, this.right);
/*      */     }
/*      */ 
/*      */     public void copyFromBuffer(IntBuffer buffer) {
/* 1163 */       this.top = buffer.get(0);
/* 1164 */       this.bottom = buffer.get(1);
/* 1165 */       this.left = buffer.get(2);
/* 1166 */       this.right = buffer.get(3);
/*      */     }
/*      */ 
/*      */     public void offset(int offset_x, int offset_y) {
/* 1170 */       this.left += offset_x;
/* 1171 */       this.right += offset_x;
/* 1172 */       this.top += offset_y;
/* 1173 */       this.bottom += offset_y;
/*      */     }
/*      */ 
/*      */     public static void intersect(Rect r1, Rect r2, Rect dst) {
/* 1177 */       dst.top = Math.max(r1.top, r2.top);
/* 1178 */       dst.bottom = Math.min(r1.bottom, r2.bottom);
/* 1179 */       dst.left = Math.max(r1.left, r2.left);
/* 1180 */       dst.right = Math.min(r1.right, r2.right);
/*      */     }
/*      */ 
/*      */     public String toString() {
/* 1184 */       return "Rect: top = " + this.top + " bottom = " + this.bottom + " left = " + this.left + " right = " + this.right + ", width: " + (this.right - this.left) + ", height: " + (this.bottom - this.top);
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.WindowsDisplay
 * JD-Core Version:    0.6.2
 */