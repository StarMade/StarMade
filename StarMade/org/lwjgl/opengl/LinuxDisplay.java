/*      */ package org.lwjgl.opengl;
/*      */ 
/*      */ import java.awt.Canvas;
/*      */ import java.awt.event.FocusEvent;
/*      */ import java.awt.event.FocusListener;
/*      */ import java.io.BufferedReader;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStreamReader;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.FloatBuffer;
/*      */ import java.nio.IntBuffer;
/*      */ import java.security.AccessController;
/*      */ import java.security.PrivilegedAction;
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import org.lwjgl.BufferUtils;
/*      */ import org.lwjgl.LWJGLException;
/*      */ import org.lwjgl.LWJGLUtil;
/*      */ import org.lwjgl.MemoryUtil;
/*      */ 
/*      */ final class LinuxDisplay
/*      */   implements DisplayImplementation
/*      */ {
/*      */   public static final int CurrentTime = 0;
/*      */   public static final int GrabSuccess = 0;
/*      */   public static final int AutoRepeatModeOff = 0;
/*      */   public static final int AutoRepeatModeOn = 1;
/*      */   public static final int AutoRepeatModeDefault = 2;
/*      */   public static final int None = 0;
/*      */   private static final int KeyPressMask = 1;
/*      */   private static final int KeyReleaseMask = 2;
/*      */   private static final int ButtonPressMask = 4;
/*      */   private static final int ButtonReleaseMask = 8;
/*      */   private static final int NotifyAncestor = 0;
/*      */   private static final int NotifyNonlinear = 3;
/*      */   private static final int NotifyPointer = 5;
/*      */   private static final int NotifyPointerRoot = 6;
/*      */   private static final int NotifyDetailNone = 7;
/*      */   private static final int SetModeInsert = 0;
/*      */   private static final int SaveSetRoot = 1;
/*      */   private static final int SaveSetUnmap = 1;
/*      */   private static final int X_SetInputFocus = 42;
/*      */   private static final int FULLSCREEN_LEGACY = 1;
/*      */   private static final int FULLSCREEN_NETWM = 2;
/*      */   private static final int WINDOWED = 3;
/*   97 */   private static int current_window_mode = 3;
/*      */   private static final int XRANDR = 10;
/*      */   private static final int XF86VIDMODE = 11;
/*      */   private static final int NONE = 12;
/*      */   private static long display;
/*      */   private static long current_window;
/*      */   private static long saved_error_handler;
/*      */   private static int display_connection_usage_count;
/*      */   private final LinuxEvent event_buffer;
/*      */   private final LinuxEvent tmp_event_buffer;
/*      */   private int current_displaymode_extension;
/*      */   private long delete_atom;
/*      */   private PeerInfo peer_info;
/*      */   private ByteBuffer saved_gamma;
/*      */   private ByteBuffer current_gamma;
/*      */   private DisplayMode saved_mode;
/*      */   private DisplayMode current_mode;
/*      */   private XRandR.Screen[] savedXrandrConfig;
/*      */   private boolean keyboard_grabbed;
/*      */   private boolean pointer_grabbed;
/*      */   private boolean input_released;
/*      */   private boolean grab;
/*      */   private boolean focused;
/*      */   private boolean minimized;
/*      */   private boolean dirty;
/*      */   private boolean close_requested;
/*      */   private long current_cursor;
/*      */   private long blank_cursor;
/*      */   private boolean mouseInside;
/*      */   private boolean resizable;
/*      */   private boolean resized;
/*      */   private int window_x;
/*      */   private int window_y;
/*      */   private int window_width;
/*      */   private int window_height;
/*      */   private Canvas parent;
/*      */   private long parent_window;
/*      */   private static boolean xembedded;
/*      */   private long parent_proxy_focus_window;
/*      */   private boolean parent_focused;
/*      */   private boolean parent_focus_changed;
/*      */   private long last_window_focus;
/*      */   private LinuxKeyboard keyboard;
/*      */   private LinuxMouse mouse;
/*      */   private final FocusListener focus_listener;
/*      */ 
/*      */   LinuxDisplay()
/*      */   {
/*  112 */     this.event_buffer = new LinuxEvent();
/*  113 */     this.tmp_event_buffer = new LinuxEvent();
/*      */ 
/*  116 */     this.current_displaymode_extension = 12;
/*      */ 
/*  143 */     this.mouseInside = true;
/*      */ 
/*  158 */     this.last_window_focus = 0L;
/*      */ 
/*  163 */     this.focus_listener = new FocusListener() {
/*      */       public void focusGained(FocusEvent e) {
/*  165 */         synchronized (GlobalLock.lock) {
/*  166 */           LinuxDisplay.this.parent_focused = true;
/*  167 */           LinuxDisplay.this.parent_focus_changed = true;
/*      */         }
/*      */       }
/*      */ 
/*  171 */       public void focusLost(FocusEvent e) { synchronized (GlobalLock.lock) {
/*  172 */           LinuxDisplay.this.parent_focused = false;
/*  173 */           LinuxDisplay.this.parent_focus_changed = true;
/*      */         } }
/*      */     };
/*      */   }
/*      */ 
/*      */   private static ByteBuffer getCurrentGammaRamp() throws LWJGLException {
/*  179 */     lockAWT();
/*      */     try {
/*  181 */       incDisplay();
/*      */       try
/*      */       {
/*      */         ByteBuffer localByteBuffer;
/*  183 */         if (isXF86VidModeSupported()) {
/*  184 */           return nGetCurrentGammaRamp(getDisplay(), getDefaultScreen());
/*      */         }
/*  186 */         return null;
/*      */       } finally {
/*      */       }
/*      */     }
/*      */     finally {
/*  191 */       unlockAWT();
/*      */     }
/*      */   }
/*      */ 
/*      */   private static native ByteBuffer nGetCurrentGammaRamp(long paramLong, int paramInt)
/*      */     throws LWJGLException;
/*      */ 
/*      */   private static int getBestDisplayModeExtension()
/*      */   {
/*      */     int result;
/*      */     int result;
/*  198 */     if (isXrandrSupported()) {
/*  199 */       LWJGLUtil.log("Using Xrandr for display mode switching");
/*  200 */       result = 10;
/*      */     }
/*      */     else
/*      */     {
/*      */       int result;
/*  201 */       if (isXF86VidModeSupported()) {
/*  202 */         LWJGLUtil.log("Using XF86VidMode for display mode switching");
/*  203 */         result = 11;
/*      */       } else {
/*  205 */         LWJGLUtil.log("No display mode extensions available");
/*  206 */         result = 12;
/*      */       }
/*      */     }
/*  208 */     return result;
/*      */   }
/*      */ 
/*      */   private static boolean isXrandrSupported() {
/*  212 */     if (Display.getPrivilegedBoolean("LWJGL_DISABLE_XRANDR"))
/*  213 */       return false;
/*  214 */     lockAWT();
/*      */     try {
/*  216 */       incDisplay();
/*      */       try {
/*  218 */         return nIsXrandrSupported(getDisplay());
/*      */       } finally {
/*      */       }
/*      */     }
/*      */     catch (LWJGLException e) {
/*  223 */       LWJGLUtil.log("Got exception while querying Xrandr support: " + e);
/*  224 */       return false;
/*      */     } finally {
/*  226 */       unlockAWT();
/*      */     }
/*      */   }
/*      */ 
/*      */   private static native boolean nIsXrandrSupported(long paramLong) throws LWJGLException;
/*      */ 
/*  232 */   private static boolean isXF86VidModeSupported() { lockAWT();
/*      */     try {
/*  234 */       incDisplay();
/*      */       try {
/*  236 */         return nIsXF86VidModeSupported(getDisplay());
/*      */       } finally {
/*      */       }
/*      */     }
/*      */     catch (LWJGLException e) {
/*  241 */       LWJGLUtil.log("Got exception while querying XF86VM support: " + e);
/*  242 */       return false;
/*      */     } finally {
/*  244 */       unlockAWT();
/*      */     } }
/*      */ 
/*      */   private static native boolean nIsXF86VidModeSupported(long paramLong) throws LWJGLException;
/*      */ 
/*      */   private static boolean isNetWMFullscreenSupported() throws LWJGLException {
/*  250 */     if (Display.getPrivilegedBoolean("LWJGL_DISABLE_NETWM"))
/*  251 */       return false;
/*  252 */     lockAWT();
/*      */     try {
/*  254 */       incDisplay();
/*      */       try {
/*  256 */         return nIsNetWMFullscreenSupported(getDisplay(), getDefaultScreen());
/*      */       } finally {
/*      */       }
/*      */     }
/*      */     catch (LWJGLException e) {
/*  261 */       LWJGLUtil.log("Got exception while querying NetWM support: " + e);
/*  262 */       return false;
/*      */     } finally {
/*  264 */       unlockAWT();
/*      */     }
/*      */   }
/*      */ 
/*      */   private static native boolean nIsNetWMFullscreenSupported(long paramLong, int paramInt)
/*      */     throws LWJGLException;
/*      */ 
/*      */   static void lockAWT()
/*      */   {
/*      */     try
/*      */     {
/*  275 */       nLockAWT();
/*      */     } catch (LWJGLException e) {
/*  277 */       LWJGLUtil.log("Caught exception while locking AWT: " + e);
/*      */     }
/*      */   }
/*      */ 
/*      */   private static native void nLockAWT() throws LWJGLException;
/*      */ 
/*      */   static void unlockAWT() {
/*      */     try { nUnlockAWT();
/*      */     } catch (LWJGLException e) {
/*  286 */       LWJGLUtil.log("Caught exception while unlocking AWT: " + e);
/*      */     }
/*      */   }
/*      */ 
/*      */   private static native void nUnlockAWT()
/*      */     throws LWJGLException;
/*      */ 
/*      */   static void incDisplay() throws LWJGLException
/*      */   {
/*  295 */     if (display_connection_usage_count == 0)
/*      */     {
/*      */       try {
/*  298 */         GLContext.loadOpenGLLibrary();
/*  299 */         org.lwjgl.opengles.GLContext.loadOpenGLLibrary();
/*      */       } catch (Throwable t) {
/*      */       }
/*  302 */       saved_error_handler = setErrorHandler();
/*  303 */       display = openDisplay();
/*      */     }
/*      */ 
/*  306 */     display_connection_usage_count += 1; } 
/*      */   private static native int callErrorHandler(long paramLong1, long paramLong2, long paramLong3);
/*      */ 
/*      */   private static native long setErrorHandler();
/*      */ 
/*      */   private static native long resetErrorHandler(long paramLong);
/*      */ 
/*      */   private static native void synchronize(long paramLong, boolean paramBoolean);
/*      */ 
/*  314 */   private static int globalErrorHandler(long display, long event_ptr, long error_display, long serial, long error_code, long request_code, long minor_code) throws LWJGLException { if ((xembedded) && (request_code == 42L)) return 0;
/*      */ 
/*  316 */     if (display == getDisplay()) {
/*  317 */       String error_msg = getErrorText(display, error_code);
/*  318 */       throw new LWJGLException("X Error - disp: 0x" + Long.toHexString(error_display) + " serial: " + serial + " error: " + error_msg + " request_code: " + request_code + " minor_code: " + minor_code);
/*  319 */     }if (saved_error_handler != 0L)
/*  320 */       return callErrorHandler(saved_error_handler, display, event_ptr);
/*  321 */     return 0;
/*      */   }
/*      */ 
/*      */   private static native String getErrorText(long paramLong1, long paramLong2);
/*      */ 
/*      */   static void decDisplay()
/*      */   {
/*      */   }
/*      */ 
/*      */   static native long openDisplay()
/*      */     throws LWJGLException;
/*      */ 
/*      */   static native void closeDisplay(long paramLong);
/*      */ 
/*      */   private int getWindowMode(boolean fullscreen)
/*      */     throws LWJGLException
/*      */   {
/*  346 */     if (fullscreen) {
/*  347 */       if ((this.current_displaymode_extension == 10) && (isNetWMFullscreenSupported())) {
/*  348 */         LWJGLUtil.log("Using NetWM for fullscreen window");
/*  349 */         return 2;
/*      */       }
/*  351 */       LWJGLUtil.log("Using legacy mode for fullscreen window");
/*  352 */       return 1;
/*      */     }
/*      */ 
/*  355 */     return 3;
/*      */   }
/*      */ 
/*      */   static long getDisplay() {
/*  359 */     if (display_connection_usage_count <= 0)
/*  360 */       throw new InternalError("display_connection_usage_count = " + display_connection_usage_count);
/*  361 */     return display;
/*      */   }
/*      */ 
/*      */   static int getDefaultScreen() {
/*  365 */     return nGetDefaultScreen(getDisplay());
/*      */   }
/*      */   static native int nGetDefaultScreen(long paramLong);
/*      */ 
/*      */   static long getWindow() {
/*  370 */     return current_window;
/*      */   }
/*      */ 
/*      */   private void ungrabKeyboard() {
/*  374 */     if (this.keyboard_grabbed) {
/*  375 */       nUngrabKeyboard(getDisplay());
/*  376 */       this.keyboard_grabbed = false;
/*      */     }
/*      */   }
/*      */ 
/*      */   static native int nUngrabKeyboard(long paramLong);
/*      */ 
/*  382 */   private void grabKeyboard() { if (!this.keyboard_grabbed) {
/*  383 */       int res = nGrabKeyboard(getDisplay(), getWindow());
/*  384 */       if (res == 0)
/*  385 */         this.keyboard_grabbed = true;
/*      */     } }
/*      */ 
/*      */   static native int nGrabKeyboard(long paramLong1, long paramLong2);
/*      */ 
/*      */   private void grabPointer() {
/*  391 */     if (!this.pointer_grabbed) {
/*  392 */       int result = nGrabPointer(getDisplay(), getWindow(), 0L);
/*  393 */       if (result == 0) {
/*  394 */         this.pointer_grabbed = true;
/*      */ 
/*  396 */         if (isLegacyFullscreen())
/*  397 */           nSetViewPort(getDisplay(), getWindow(), getDefaultScreen()); 
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   static native int nGrabPointer(long paramLong1, long paramLong2, long paramLong3);
/*      */ 
/*      */   private static native void nSetViewPort(long paramLong1, long paramLong2, int paramInt);
/*      */ 
/*  406 */   private void ungrabPointer() { if (this.pointer_grabbed) {
/*  407 */       this.pointer_grabbed = false;
/*  408 */       nUngrabPointer(getDisplay());
/*      */     } }
/*      */ 
/*      */   static native int nUngrabPointer(long paramLong);
/*      */ 
/*      */   private static boolean isFullscreen() {
/*  414 */     return (current_window_mode == 1) || (current_window_mode == 2);
/*      */   }
/*      */ 
/*      */   private boolean shouldGrab() {
/*  418 */     return (!this.input_released) && (this.grab) && (this.mouse != null);
/*      */   }
/*      */ 
/*      */   private void updatePointerGrab() {
/*  422 */     if ((isFullscreen()) || (shouldGrab()))
/*  423 */       grabPointer();
/*      */     else {
/*  425 */       ungrabPointer();
/*      */     }
/*  427 */     updateCursor();
/*      */   }
/*      */ 
/*      */   private void updateCursor()
/*      */   {
/*      */     long cursor;
/*      */     long cursor;
/*  432 */     if (shouldGrab())
/*  433 */       cursor = this.blank_cursor;
/*      */     else {
/*  435 */       cursor = this.current_cursor;
/*      */     }
/*  437 */     nDefineCursor(getDisplay(), getWindow(), cursor);
/*      */   }
/*      */   private static native void nDefineCursor(long paramLong1, long paramLong2, long paramLong3);
/*      */ 
/*      */   private static boolean isLegacyFullscreen() {
/*  442 */     return current_window_mode == 1;
/*      */   }
/*      */ 
/*      */   private void updateKeyboardGrab() {
/*  446 */     if (isLegacyFullscreen())
/*  447 */       grabKeyboard();
/*      */     else
/*  449 */       ungrabKeyboard();
/*      */   }
/*      */ 
/*      */   public void createWindow(DrawableLWJGL drawable, DisplayMode mode, Canvas parent, int x, int y) throws LWJGLException {
/*  453 */     lockAWT();
/*      */     try {
/*  455 */       incDisplay();
/*      */       try {
/*  457 */         if ((drawable instanceof DrawableGLES)) {
/*  458 */           this.peer_info = new LinuxDisplayPeerInfo();
/*      */         }
/*  460 */         ByteBuffer handle = this.peer_info.lockAndGetHandle();
/*      */         try {
/*  462 */           current_window_mode = getWindowMode(Display.isFullscreen());
/*      */ 
/*  465 */           if (current_window_mode != 3) {
/*  466 */             Compiz.setLegacyFullscreenSupport(true);
/*      */           }
/*      */ 
/*  471 */           boolean undecorated = (Display.getPrivilegedBoolean("org.lwjgl.opengl.Window.undecorated")) || ((current_window_mode != 3) && (Display.getPrivilegedBoolean("org.lwjgl.opengl.Window.undecorated_fs")));
/*  472 */           this.parent = parent;
/*  473 */           this.parent_window = (parent != null ? getHandle(parent) : getRootWindow(getDisplay(), getDefaultScreen()));
/*  474 */           this.resizable = Display.isResizable();
/*  475 */           this.resized = false;
/*  476 */           this.window_x = x;
/*  477 */           this.window_y = y;
/*  478 */           this.window_width = mode.getWidth();
/*  479 */           this.window_height = mode.getHeight();
/*  480 */           current_window = nCreateWindow(getDisplay(), getDefaultScreen(), handle, mode, current_window_mode, x, y, undecorated, this.parent_window, this.resizable);
/*  481 */           mapRaised(getDisplay(), current_window);
/*  482 */           xembedded = (parent != null) && (isAncestorXEmbedded(this.parent_window));
/*  483 */           this.blank_cursor = createBlankCursor();
/*  484 */           this.current_cursor = 0L;
/*  485 */           this.focused = false;
/*  486 */           this.input_released = false;
/*  487 */           this.pointer_grabbed = false;
/*  488 */           this.keyboard_grabbed = false;
/*  489 */           this.close_requested = false;
/*  490 */           this.grab = false;
/*  491 */           this.minimized = false;
/*  492 */           this.dirty = true;
/*      */ 
/*  494 */           if ((drawable instanceof DrawableGLES)) {
/*  495 */             ((DrawableGLES)drawable).initialize(current_window, getDisplay(), 4, (org.lwjgl.opengles.PixelFormat)drawable.getPixelFormat());
/*      */           }
/*  497 */           if (parent != null) {
/*  498 */             parent.addFocusListener(this.focus_listener);
/*  499 */             this.parent_focused = parent.isFocusOwner();
/*  500 */             this.parent_focus_changed = true;
/*      */           }
/*      */         } finally {
/*  503 */           this.peer_info.unlock();
/*      */         }
/*      */       }
/*      */       catch (LWJGLException e) {
/*  507 */         throw e;
/*      */       }
/*      */     } finally {
/*  510 */       unlockAWT(); }  } 
/*      */   private static native long nCreateWindow(long paramLong1, int paramInt1, ByteBuffer paramByteBuffer, DisplayMode paramDisplayMode, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean1, long paramLong2, boolean paramBoolean2) throws LWJGLException;
/*      */ 
/*      */   private static native long getRootWindow(long paramLong, int paramInt);
/*      */ 
/*      */   private static native boolean hasProperty(long paramLong1, long paramLong2, long paramLong3);
/*      */ 
/*      */   private static native long getParentWindow(long paramLong1, long paramLong2) throws LWJGLException;
/*      */ 
/*      */   private static native int getChildCount(long paramLong1, long paramLong2) throws LWJGLException;
/*      */ 
/*      */   private static native void mapRaised(long paramLong1, long paramLong2);
/*      */ 
/*      */   private static native void reparentWindow(long paramLong1, long paramLong2, long paramLong3, int paramInt1, int paramInt2);
/*      */ 
/*      */   private static native long nGetInputFocus(long paramLong) throws LWJGLException;
/*      */ 
/*      */   private static native void nSetInputFocus(long paramLong1, long paramLong2, long paramLong3);
/*      */ 
/*      */   private static native void nSetWindowSize(long paramLong1, long paramLong2, int paramInt1, int paramInt2, boolean paramBoolean);
/*      */ 
/*      */   private static native int nGetX(long paramLong1, long paramLong2);
/*      */ 
/*      */   private static native int nGetY(long paramLong1, long paramLong2);
/*      */ 
/*      */   private static native int nGetWidth(long paramLong1, long paramLong2);
/*      */ 
/*      */   private static native int nGetHeight(long paramLong1, long paramLong2);
/*      */ 
/*  529 */   private static boolean isAncestorXEmbedded(long window) throws LWJGLException { long xembed_atom = internAtom("_XEMBED_INFO", true);
/*  530 */     if (xembed_atom != 0L) {
/*  531 */       long w = window;
/*  532 */       while (w != 0L) {
/*  533 */         if (hasProperty(getDisplay(), w, xembed_atom))
/*  534 */           return true;
/*  535 */         w = getParentWindow(getDisplay(), w);
/*      */       }
/*      */     }
/*  538 */     return false; }
/*      */ 
/*      */   private static long getHandle(Canvas parent) throws LWJGLException
/*      */   {
/*  542 */     AWTCanvasImplementation awt_impl = AWTGLCanvas.createImplementation();
/*  543 */     LinuxPeerInfo parent_peer_info = (LinuxPeerInfo)awt_impl.createPeerInfo(parent, null, null);
/*  544 */     ByteBuffer parent_peer_info_handle = parent_peer_info.lockAndGetHandle();
/*      */     try {
/*  546 */       return parent_peer_info.getDrawable();
/*      */     } finally {
/*  548 */       parent_peer_info.unlock();
/*      */     }
/*      */   }
/*      */ 
/*      */   private void updateInputGrab() {
/*  553 */     updatePointerGrab();
/*  554 */     updateKeyboardGrab();
/*      */   }
/*      */ 
/*      */   public void destroyWindow() {
/*  558 */     lockAWT();
/*      */     try {
/*  560 */       if (this.parent != null)
/*  561 */         this.parent.removeFocusListener(this.focus_listener);
/*      */       try
/*      */       {
/*  564 */         setNativeCursor(null);
/*      */       } catch (LWJGLException e) {
/*  566 */         LWJGLUtil.log("Failed to reset cursor: " + e.getMessage());
/*      */       }
/*  568 */       nDestroyCursor(getDisplay(), this.blank_cursor);
/*  569 */       this.blank_cursor = 0L;
/*  570 */       ungrabKeyboard();
/*  571 */       nDestroyWindow(getDisplay(), getWindow());
/*  572 */       decDisplay();
/*      */ 
/*  574 */       if (current_window_mode != 3)
/*  575 */         Compiz.setLegacyFullscreenSupport(false);
/*      */     } finally {
/*  577 */       unlockAWT();
/*      */     }
/*      */   }
/*      */ 
/*      */   static native void nDestroyWindow(long paramLong1, long paramLong2);
/*      */ 
/*  583 */   public void switchDisplayMode(DisplayMode mode) throws LWJGLException { lockAWT();
/*      */     try {
/*  585 */       switchDisplayModeOnTmpDisplay(mode);
/*  586 */       this.current_mode = mode;
/*      */     } finally {
/*  588 */       unlockAWT();
/*      */     } }
/*      */ 
/*      */   private void switchDisplayModeOnTmpDisplay(DisplayMode mode) throws LWJGLException
/*      */   {
/*  593 */     incDisplay();
/*      */     try {
/*  595 */       nSwitchDisplayMode(getDisplay(), getDefaultScreen(), this.current_displaymode_extension, mode);
/*      */     } finally {
/*  597 */       decDisplay();
/*      */     }
/*      */   }
/*      */ 
/*      */   private static native void nSwitchDisplayMode(long paramLong, int paramInt1, int paramInt2, DisplayMode paramDisplayMode) throws LWJGLException;
/*      */ 
/*  603 */   private static long internAtom(String atom_name, boolean only_if_exists) throws LWJGLException { incDisplay();
/*      */     try {
/*  605 */       return nInternAtom(getDisplay(), atom_name, only_if_exists);
/*      */     } finally {
/*  607 */       decDisplay();
/*      */     } }
/*      */ 
/*      */   static native long nInternAtom(long paramLong, String paramString, boolean paramBoolean);
/*      */ 
/*      */   public void resetDisplayMode() {
/*  613 */     lockAWT();
/*      */     try {
/*  615 */       if ((this.current_displaymode_extension == 10) && (this.savedXrandrConfig.length > 0))
/*      */       {
/*  617 */         AccessController.doPrivileged(new PrivilegedAction() {
/*      */           public Object run() {
/*  619 */             XRandR.setConfiguration(LinuxDisplay.this.savedXrandrConfig);
/*  620 */             return null;
/*      */           }
/*      */         });
/*      */       }
/*      */       else
/*      */       {
/*  626 */         switchDisplayMode(this.saved_mode);
/*      */       }
/*  628 */       if (isXF86VidModeSupported()) {
/*  629 */         doSetGamma(this.saved_gamma);
/*      */       }
/*  631 */       Compiz.setLegacyFullscreenSupport(false);
/*      */     } catch (LWJGLException e) {
/*  633 */       LWJGLUtil.log("Caught exception while resetting mode: " + e);
/*      */     } finally {
/*  635 */       unlockAWT();
/*      */     }
/*      */   }
/*      */ 
/*      */   public int getGammaRampLength() {
/*  640 */     if (!isXF86VidModeSupported())
/*  641 */       return 0;
/*  642 */     lockAWT();
/*      */     try
/*      */     {
/*  645 */       incDisplay();
/*      */       try {
/*  647 */         return nGetGammaRampLength(getDisplay(), getDefaultScreen());
/*      */       } catch (LWJGLException e) {
/*  649 */         LWJGLUtil.log("Got exception while querying gamma length: " + e);
/*  650 */         return 0;
/*      */       }
/*      */       finally
/*      */       {
/*      */       }
/*      */     }
/*      */     catch (LWJGLException e)
/*      */     {
/*      */       int j;
/*  655 */       LWJGLUtil.log("Failed to get gamma ramp length: " + e);
/*  656 */       return 0;
/*      */     }
/*      */     finally {
/*  659 */       unlockAWT();
/*      */     }
/*      */   }
/*      */ 
/*      */   private static native int nGetGammaRampLength(long paramLong, int paramInt) throws LWJGLException;
/*      */ 
/*  665 */   public void setGammaRamp(FloatBuffer gammaRamp) throws LWJGLException { if (!isXF86VidModeSupported())
/*  666 */       throw new LWJGLException("No gamma ramp support (Missing XF86VM extension)");
/*  667 */     doSetGamma(convertToNativeRamp(gammaRamp)); }
/*      */ 
/*      */   private void doSetGamma(ByteBuffer native_gamma) throws LWJGLException
/*      */   {
/*  671 */     lockAWT();
/*      */     try {
/*  673 */       setGammaRampOnTmpDisplay(native_gamma);
/*  674 */       this.current_gamma = native_gamma;
/*      */     } finally {
/*  676 */       unlockAWT();
/*      */     }
/*      */   }
/*      */ 
/*      */   private static void setGammaRampOnTmpDisplay(ByteBuffer native_gamma) throws LWJGLException {
/*  681 */     incDisplay();
/*      */     try {
/*  683 */       nSetGammaRamp(getDisplay(), getDefaultScreen(), native_gamma);
/*      */     } finally {
/*  685 */       decDisplay();
/*      */     }
/*      */   }
/*      */ 
/*      */   private static native void nSetGammaRamp(long paramLong, int paramInt, ByteBuffer paramByteBuffer) throws LWJGLException;
/*      */ 
/*  691 */   private static ByteBuffer convertToNativeRamp(FloatBuffer ramp) throws LWJGLException { return nConvertToNativeRamp(ramp, ramp.position(), ramp.remaining()); }
/*      */ 
/*      */   private static native ByteBuffer nConvertToNativeRamp(FloatBuffer paramFloatBuffer, int paramInt1, int paramInt2) throws LWJGLException;
/*      */ 
/*      */   public String getAdapter() {
/*  696 */     return null;
/*      */   }
/*      */ 
/*      */   public String getVersion() {
/*  700 */     return null;
/*      */   }
/*      */ 
/*      */   public DisplayMode init() throws LWJGLException {
/*  704 */     lockAWT();
/*      */     try {
/*  706 */       Compiz.init();
/*      */ 
/*  708 */       this.delete_atom = internAtom("WM_DELETE_WINDOW", false);
/*  709 */       this.current_displaymode_extension = getBestDisplayModeExtension();
/*  710 */       if (this.current_displaymode_extension == 12)
/*  711 */         throw new LWJGLException("No display mode extension is available");
/*  712 */       DisplayMode[] modes = getAvailableDisplayModes();
/*  713 */       if ((modes == null) || (modes.length == 0))
/*  714 */         throw new LWJGLException("No modes available");
/*  715 */       switch (this.current_displaymode_extension) {
/*      */       case 10:
/*  717 */         this.savedXrandrConfig = ((XRandR.Screen[])AccessController.doPrivileged(new PrivilegedAction() {
/*      */           public XRandR.Screen[] run() {
/*  719 */             return XRandR.getConfiguration();
/*      */           }
/*      */         }));
/*  722 */         this.saved_mode = getCurrentXRandrMode();
/*  723 */         break;
/*      */       case 11:
/*  725 */         this.saved_mode = modes[0];
/*  726 */         break;
/*      */       default:
/*  728 */         throw new LWJGLException("Unknown display mode extension: " + this.current_displaymode_extension);
/*      */       }
/*  730 */       this.current_mode = this.saved_mode;
/*  731 */       this.saved_gamma = getCurrentGammaRamp();
/*  732 */       this.current_gamma = this.saved_gamma;
/*  733 */       return this.saved_mode;
/*      */     } finally {
/*  735 */       unlockAWT();
/*      */     }
/*      */   }
/*      */ 
/*      */   private static DisplayMode getCurrentXRandrMode() throws LWJGLException {
/*  740 */     lockAWT();
/*      */     try {
/*  742 */       incDisplay();
/*      */       try {
/*  744 */         return nGetCurrentXRandrMode(getDisplay(), getDefaultScreen());
/*      */       } finally {
/*      */       }
/*      */     }
/*      */     finally {
/*  749 */       unlockAWT();
/*      */     }
/*      */   }
/*      */ 
/*      */   private static native DisplayMode nGetCurrentXRandrMode(long paramLong, int paramInt) throws LWJGLException;
/*      */ 
/*      */   public void setTitle(String title)
/*      */   {
/*  757 */     lockAWT();
/*      */     try {
/*  759 */       ByteBuffer titleText = MemoryUtil.encodeUTF8(title);
/*  760 */       nSetTitle(getDisplay(), getWindow(), MemoryUtil.getAddress(titleText), titleText.remaining() - 1);
/*      */     } finally {
/*  762 */       unlockAWT();
/*      */     }
/*      */   }
/*      */ 
/*      */   private static native void nSetTitle(long paramLong1, long paramLong2, long paramLong3, int paramInt);
/*      */ 
/*  768 */   public boolean isCloseRequested() { boolean result = this.close_requested;
/*  769 */     this.close_requested = false;
/*  770 */     return result; }
/*      */ 
/*      */   public boolean isVisible()
/*      */   {
/*  774 */     return !this.minimized;
/*      */   }
/*      */ 
/*      */   public boolean isActive() {
/*  778 */     return (this.focused) || (isLegacyFullscreen());
/*      */   }
/*      */ 
/*      */   public boolean isDirty() {
/*  782 */     boolean result = this.dirty;
/*  783 */     this.dirty = false;
/*  784 */     return result;
/*      */   }
/*      */ 
/*      */   public PeerInfo createPeerInfo(PixelFormat pixel_format, ContextAttribs attribs) throws LWJGLException {
/*  788 */     this.peer_info = new LinuxDisplayPeerInfo(pixel_format);
/*  789 */     return this.peer_info;
/*      */   }
/*      */ 
/*      */   private void relayEventToParent(LinuxEvent event_buffer, int event_mask) {
/*  793 */     this.tmp_event_buffer.copyFrom(event_buffer);
/*  794 */     this.tmp_event_buffer.setWindow(this.parent_window);
/*  795 */     this.tmp_event_buffer.sendEvent(getDisplay(), this.parent_window, true, event_mask);
/*      */   }
/*      */ 
/*      */   private void relayEventToParent(LinuxEvent event_buffer) {
/*  799 */     if (this.parent == null)
/*  800 */       return;
/*  801 */     switch (event_buffer.getType()) {
/*      */     case 2:
/*  803 */       relayEventToParent(event_buffer, 1);
/*  804 */       break;
/*      */     case 3:
/*  806 */       relayEventToParent(event_buffer, 1);
/*  807 */       break;
/*      */     case 4:
/*  809 */       if ((xembedded) || (!this.focused)) relayEventToParent(event_buffer, 1); break;
/*      */     case 5:
/*  812 */       if ((xembedded) || (!this.focused)) relayEventToParent(event_buffer, 1); break;
/*      */     }
/*      */   }
/*      */ 
/*      */   private void processEvents()
/*      */   {
/*  820 */     while (LinuxEvent.getPending(getDisplay()) > 0) {
/*  821 */       this.event_buffer.nextEvent(getDisplay());
/*  822 */       long event_window = this.event_buffer.getWindow();
/*  823 */       relayEventToParent(this.event_buffer);
/*  824 */       if ((event_window == getWindow()) && (!this.event_buffer.filterEvent(event_window)) && ((this.mouse == null) || (!this.mouse.filterEvent(this.grab, shouldWarpPointer(), this.event_buffer))) && ((this.keyboard == null) || (!this.keyboard.filterEvent(this.event_buffer))))
/*      */       {
/*  828 */         switch (this.event_buffer.getType()) {
/*      */         case 9:
/*  830 */           setFocused(true, this.event_buffer.getFocusDetail());
/*  831 */           break;
/*      */         case 10:
/*  833 */           setFocused(false, this.event_buffer.getFocusDetail());
/*  834 */           break;
/*      */         case 33:
/*  836 */           if ((this.event_buffer.getClientFormat() == 32) && (this.event_buffer.getClientData(0) == this.delete_atom))
/*  837 */             this.close_requested = true; break;
/*      */         case 19:
/*  840 */           this.dirty = true;
/*  841 */           this.minimized = false;
/*  842 */           break;
/*      */         case 18:
/*  844 */           this.dirty = true;
/*  845 */           this.minimized = true;
/*  846 */           break;
/*      */         case 12:
/*  848 */           this.dirty = true;
/*  849 */           break;
/*      */         case 22:
/*  851 */           int x = nGetX(getDisplay(), getWindow());
/*  852 */           int y = nGetY(getDisplay(), getWindow());
/*      */ 
/*  854 */           int width = nGetWidth(getDisplay(), getWindow());
/*  855 */           int height = nGetHeight(getDisplay(), getWindow());
/*      */ 
/*  857 */           this.window_x = x;
/*  858 */           this.window_y = y;
/*      */ 
/*  860 */           if ((this.window_width != width) || (this.window_height != height)) {
/*  861 */             this.resized = true;
/*  862 */             this.window_width = width;
/*  863 */             this.window_height = height; } break;
/*      */         case 7:
/*  868 */           this.mouseInside = true;
/*  869 */           break;
/*      */         case 8:
/*  871 */           this.mouseInside = false;
/*      */         case 11:
/*      */         case 13:
/*      */         case 14:
/*      */         case 15:
/*      */         case 16:
/*      */         case 17:
/*      */         case 20:
/*      */         case 21:
/*      */         case 23:
/*      */         case 24:
/*      */         case 25:
/*      */         case 26:
/*      */         case 27:
/*      */         case 28:
/*      */         case 29:
/*      */         case 30:
/*      */         case 31:
/*      */         case 32: }  }  }  } 
/*  880 */   public void update() { lockAWT();
/*      */     try {
/*  882 */       processEvents();
/*  883 */       checkInput();
/*      */     } finally {
/*  885 */       unlockAWT();
/*      */     } }
/*      */ 
/*      */   public void reshape(int x, int y, int width, int height)
/*      */   {
/*  890 */     lockAWT();
/*      */     try {
/*  892 */       nReshape(getDisplay(), getWindow(), x, y, width, height);
/*      */     } finally {
/*  894 */       unlockAWT();
/*      */     }
/*      */   }
/*      */ 
/*      */   private static native void nReshape(long paramLong1, long paramLong2, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
/*      */ 
/*  900 */   public DisplayMode[] getAvailableDisplayModes() throws LWJGLException { lockAWT();
/*      */     try {
/*  902 */       incDisplay();
/*      */       try {
/*  904 */         DisplayMode[] modes = nGetAvailableDisplayModes(getDisplay(), getDefaultScreen(), this.current_displaymode_extension);
/*  905 */         return modes;
/*      */       } finally {
/*      */       }
/*      */     }
/*      */     finally {
/*  910 */       unlockAWT();
/*      */     } }
/*      */ 
/*      */   private static native DisplayMode[] nGetAvailableDisplayModes(long paramLong, int paramInt1, int paramInt2) throws LWJGLException;
/*      */ 
/*      */   public boolean hasWheel()
/*      */   {
/*  917 */     return true;
/*      */   }
/*      */ 
/*      */   public int getButtonCount() {
/*  921 */     return this.mouse.getButtonCount();
/*      */   }
/*      */ 
/*      */   public void createMouse() throws LWJGLException {
/*  925 */     lockAWT();
/*      */     try {
/*  927 */       this.mouse = new LinuxMouse(getDisplay(), getWindow(), getWindow());
/*      */     } finally {
/*  929 */       unlockAWT();
/*      */     }
/*      */   }
/*      */ 
/*      */   public void destroyMouse() {
/*  934 */     this.mouse = null;
/*  935 */     updateInputGrab();
/*      */   }
/*      */ 
/*      */   public void pollMouse(IntBuffer coord_buffer, ByteBuffer buttons) {
/*  939 */     lockAWT();
/*      */     try {
/*  941 */       this.mouse.poll(this.grab, coord_buffer, buttons);
/*      */     } finally {
/*  943 */       unlockAWT();
/*      */     }
/*      */   }
/*      */ 
/*      */   public void readMouse(ByteBuffer buffer) {
/*  948 */     lockAWT();
/*      */     try {
/*  950 */       this.mouse.read(buffer);
/*      */     } finally {
/*  952 */       unlockAWT();
/*      */     }
/*      */   }
/*      */ 
/*      */   public void setCursorPosition(int x, int y) {
/*  957 */     lockAWT();
/*      */     try {
/*  959 */       this.mouse.setCursorPosition(x, y);
/*      */     } finally {
/*  961 */       unlockAWT();
/*      */     }
/*      */   }
/*      */ 
/*      */   private void checkInput() {
/*  966 */     if (this.parent == null) return;
/*      */ 
/*  968 */     if (xembedded) {
/*  969 */       long current_focus_window = 0L;
/*      */ 
/*  971 */       if ((this.last_window_focus != current_focus_window) || (this.parent_focused != this.focused)) {
/*  972 */         if (isParentWindowActive(current_focus_window)) {
/*  973 */           if (this.parent_focused) {
/*  974 */             nSetInputFocus(getDisplay(), current_window, 0L);
/*  975 */             this.last_window_focus = current_window;
/*  976 */             this.focused = true;
/*      */           }
/*      */           else
/*      */           {
/*  980 */             nSetInputFocus(getDisplay(), this.parent_proxy_focus_window, 0L);
/*  981 */             this.last_window_focus = this.parent_proxy_focus_window;
/*  982 */             this.focused = false;
/*      */           }
/*      */         }
/*      */         else {
/*  986 */           this.last_window_focus = current_focus_window;
/*  987 */           this.focused = false;
/*      */         }
/*      */       }
/*      */ 
/*      */     }
/*  992 */     else if ((this.parent_focus_changed) && (this.parent_focused)) {
/*  993 */       setInputFocusUnsafe(getWindow());
/*  994 */       this.parent_focus_changed = false;
/*      */     }
/*      */   }
/*      */ 
/*      */   private void setInputFocusUnsafe(long window)
/*      */   {
/*      */     try {
/* 1001 */       nSetInputFocus(getDisplay(), window, 0L);
/* 1002 */       nSync(getDisplay(), false);
/*      */     }
/*      */     catch (LWJGLException e) {
/* 1005 */       LWJGLUtil.log("Got exception while trying to focus: " + e);
/*      */     }
/*      */   }
/*      */ 
/*      */   private static native void nSync(long paramLong, boolean paramBoolean)
/*      */     throws LWJGLException;
/*      */ 
/*      */   private boolean isParentWindowActive(long window)
/*      */   {
/*      */     try
/*      */     {
/* 1024 */       if (window == current_window) return true;
/*      */ 
/* 1027 */       if (getChildCount(getDisplay(), window) != 0) return false;
/*      */ 
/* 1030 */       long parent_window = getParentWindow(getDisplay(), window);
/*      */ 
/* 1033 */       if (parent_window == 0L) return false;
/*      */ 
/* 1036 */       long w = current_window;
/*      */ 
/* 1038 */       while (w != 0L) {
/* 1039 */         w = getParentWindow(getDisplay(), w);
/* 1040 */         if (w == parent_window) {
/* 1041 */           this.parent_proxy_focus_window = window;
/* 1042 */           return true;
/*      */         }
/*      */       }
/*      */     } catch (LWJGLException e) {
/* 1046 */       LWJGLUtil.log("Failed to detect if parent window is active: " + e.getMessage());
/* 1047 */       return true;
/*      */     }
/*      */ 
/* 1050 */     return false;
/*      */   }
/*      */ 
/*      */   private void setFocused(boolean got_focus, int focus_detail) {
/* 1054 */     if ((this.focused == got_focus) || (focus_detail == 7) || (focus_detail == 5) || (focus_detail == 6) || (xembedded))
/* 1055 */       return;
/* 1056 */     this.focused = got_focus;
/*      */ 
/* 1058 */     if (this.focused) {
/* 1059 */       acquireInput();
/*      */     }
/*      */     else
/* 1062 */       releaseInput();
/*      */   }
/*      */ 
/*      */   private void releaseInput()
/*      */   {
/* 1067 */     if ((isLegacyFullscreen()) || (this.input_released))
/* 1068 */       return;
/* 1069 */     this.input_released = true;
/* 1070 */     updateInputGrab();
/* 1071 */     if (current_window_mode == 2) {
/* 1072 */       nIconifyWindow(getDisplay(), getWindow(), getDefaultScreen());
/*      */       try {
/* 1074 */         if ((this.current_displaymode_extension == 10) && (this.savedXrandrConfig.length > 0))
/*      */         {
/* 1076 */           AccessController.doPrivileged(new PrivilegedAction() {
/*      */             public Object run() {
/* 1078 */               XRandR.setConfiguration(LinuxDisplay.this.savedXrandrConfig);
/* 1079 */               return null;
/*      */             }
/*      */           });
/*      */         }
/*      */         else
/*      */         {
/* 1085 */           switchDisplayModeOnTmpDisplay(this.saved_mode);
/*      */         }
/* 1087 */         setGammaRampOnTmpDisplay(this.saved_gamma);
/*      */       } catch (LWJGLException e) {
/* 1089 */         LWJGLUtil.log("Failed to restore saved mode: " + e.getMessage());
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private static native void nIconifyWindow(long paramLong1, long paramLong2, int paramInt);
/*      */ 
/* 1096 */   private void acquireInput() { if ((isLegacyFullscreen()) || (!this.input_released))
/* 1097 */       return;
/* 1098 */     this.input_released = false;
/* 1099 */     updateInputGrab();
/* 1100 */     if (current_window_mode == 2)
/*      */       try {
/* 1102 */         switchDisplayModeOnTmpDisplay(this.current_mode);
/* 1103 */         setGammaRampOnTmpDisplay(this.current_gamma);
/*      */       } catch (LWJGLException e) {
/* 1105 */         LWJGLUtil.log("Failed to restore mode: " + e.getMessage());
/*      */       }
/*      */   }
/*      */ 
/*      */   public void grabMouse(boolean new_grab)
/*      */   {
/* 1111 */     lockAWT();
/*      */     try {
/* 1113 */       if (new_grab != this.grab) {
/* 1114 */         this.grab = new_grab;
/* 1115 */         updateInputGrab();
/* 1116 */         this.mouse.changeGrabbed(this.grab, shouldWarpPointer());
/*      */       }
/*      */     } finally {
/* 1119 */       unlockAWT();
/*      */     }
/*      */   }
/*      */ 
/*      */   private boolean shouldWarpPointer() {
/* 1124 */     return (this.pointer_grabbed) && (shouldGrab());
/*      */   }
/*      */ 
/*      */   public int getNativeCursorCapabilities() {
/* 1128 */     lockAWT();
/*      */     try {
/* 1130 */       incDisplay();
/*      */       try {
/* 1132 */         return nGetNativeCursorCapabilities(getDisplay());
/*      */       } finally {
/*      */       }
/*      */     }
/*      */     catch (LWJGLException e) {
/* 1137 */       throw new RuntimeException(e);
/*      */     } finally {
/* 1139 */       unlockAWT();
/*      */     }
/*      */   }
/*      */ 
/*      */   private static native int nGetNativeCursorCapabilities(long paramLong) throws LWJGLException;
/*      */ 
/* 1145 */   public void setNativeCursor(Object handle) throws LWJGLException { this.current_cursor = getCursorHandle(handle);
/* 1146 */     lockAWT();
/*      */     try {
/* 1148 */       updateCursor();
/*      */     } finally {
/* 1150 */       unlockAWT();
/*      */     } }
/*      */ 
/*      */   public int getMinCursorSize()
/*      */   {
/* 1155 */     lockAWT();
/*      */     try {
/* 1157 */       incDisplay();
/*      */       try {
/* 1159 */         return nGetMinCursorSize(getDisplay(), getWindow());
/*      */       } finally {
/*      */       }
/*      */     }
/*      */     catch (LWJGLException e) {
/* 1164 */       LWJGLUtil.log("Exception occurred in getMinCursorSize: " + e);
/* 1165 */       return 0;
/*      */     } finally {
/* 1167 */       unlockAWT();
/*      */     }
/*      */   }
/*      */ 
/*      */   private static native int nGetMinCursorSize(long paramLong1, long paramLong2);
/*      */ 
/* 1173 */   public int getMaxCursorSize() { lockAWT();
/*      */     try {
/* 1175 */       incDisplay();
/*      */       try {
/* 1177 */         return nGetMaxCursorSize(getDisplay(), getWindow());
/*      */       } finally {
/*      */       }
/*      */     }
/*      */     catch (LWJGLException e) {
/* 1182 */       LWJGLUtil.log("Exception occurred in getMaxCursorSize: " + e);
/* 1183 */       return 0;
/*      */     } finally {
/* 1185 */       unlockAWT();
/*      */     } }
/*      */ 
/*      */   private static native int nGetMaxCursorSize(long paramLong1, long paramLong2);
/*      */ 
/*      */   public void createKeyboard() throws LWJGLException
/*      */   {
/* 1192 */     lockAWT();
/*      */     try {
/* 1194 */       this.keyboard = new LinuxKeyboard(getDisplay(), getWindow());
/*      */     } finally {
/* 1196 */       unlockAWT();
/*      */     }
/*      */   }
/*      */ 
/*      */   public void destroyKeyboard() {
/* 1201 */     lockAWT();
/*      */     try {
/* 1203 */       this.keyboard.destroy(getDisplay());
/* 1204 */       this.keyboard = null;
/*      */     } finally {
/* 1206 */       unlockAWT();
/*      */     }
/*      */   }
/*      */ 
/*      */   public void pollKeyboard(ByteBuffer keyDownBuffer) {
/* 1211 */     lockAWT();
/*      */     try {
/* 1213 */       this.keyboard.poll(keyDownBuffer);
/*      */     } finally {
/* 1215 */       unlockAWT();
/*      */     }
/*      */   }
/*      */ 
/*      */   public void readKeyboard(ByteBuffer buffer) {
/* 1220 */     lockAWT();
/*      */     try {
/* 1222 */       this.keyboard.read(buffer);
/*      */     } finally {
/* 1224 */       unlockAWT();
/*      */     }
/*      */   }
/*      */ 
/*      */   private static native long nCreateCursor(long paramLong, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, IntBuffer paramIntBuffer1, int paramInt6, IntBuffer paramIntBuffer2, int paramInt7) throws LWJGLException;
/*      */ 
/*      */   private static long createBlankCursor() {
/* 1231 */     return nCreateBlankCursor(getDisplay(), getWindow());
/*      */   }
/*      */   static native long nCreateBlankCursor(long paramLong1, long paramLong2);
/*      */ 
/*      */   public Object createCursor(int width, int height, int xHotspot, int yHotspot, int numImages, IntBuffer images, IntBuffer delays) throws LWJGLException {
/* 1236 */     lockAWT();
/*      */     try {
/* 1238 */       incDisplay();
/*      */       try {
/* 1240 */         long cursor = nCreateCursor(getDisplay(), width, height, xHotspot, yHotspot, numImages, images, images.position(), delays, delays != null ? delays.position() : -1);
/* 1241 */         return Long.valueOf(cursor);
/*      */       }
/*      */       catch (LWJGLException e) {
/* 1244 */         throw e;
/*      */       }
/*      */     } finally {
/* 1247 */       unlockAWT();
/*      */     }
/*      */   }
/*      */ 
/*      */   private static long getCursorHandle(Object cursor_handle) {
/* 1252 */     return cursor_handle != null ? ((Long)cursor_handle).longValue() : 0L;
/*      */   }
/*      */ 
/*      */   public void destroyCursor(Object cursorHandle) {
/* 1256 */     lockAWT();
/*      */     try {
/* 1258 */       nDestroyCursor(getDisplay(), getCursorHandle(cursorHandle));
/* 1259 */       decDisplay();
/*      */     } finally {
/* 1261 */       unlockAWT();
/*      */     }
/*      */   }
/*      */ 
/*      */   static native void nDestroyCursor(long paramLong1, long paramLong2);
/*      */ 
/* 1267 */   public int getPbufferCapabilities() { lockAWT();
/*      */     try {
/* 1269 */       incDisplay();
/*      */       try {
/* 1271 */         return nGetPbufferCapabilities(getDisplay(), getDefaultScreen());
/*      */       } finally {
/*      */       }
/*      */     }
/*      */     catch (LWJGLException e) {
/* 1276 */       LWJGLUtil.log("Exception occurred in getPbufferCapabilities: " + e);
/* 1277 */       return 0;
/*      */     } finally {
/* 1279 */       unlockAWT();
/*      */     } }
/*      */ 
/*      */   private static native int nGetPbufferCapabilities(long paramLong, int paramInt);
/*      */ 
/*      */   public boolean isBufferLost(PeerInfo handle) {
/* 1285 */     return false;
/*      */   }
/*      */ 
/*      */   public PeerInfo createPbuffer(int width, int height, PixelFormat pixel_format, ContextAttribs attribs, IntBuffer pixelFormatCaps, IntBuffer pBufferAttribs)
/*      */     throws LWJGLException
/*      */   {
/* 1291 */     return new LinuxPbufferPeerInfo(width, height, pixel_format);
/*      */   }
/*      */ 
/*      */   public void setPbufferAttrib(PeerInfo handle, int attrib, int value) {
/* 1295 */     throw new UnsupportedOperationException();
/*      */   }
/*      */ 
/*      */   public void bindTexImageToPbuffer(PeerInfo handle, int buffer) {
/* 1299 */     throw new UnsupportedOperationException();
/*      */   }
/*      */ 
/*      */   public void releaseTexImageFromPbuffer(PeerInfo handle, int buffer) {
/* 1303 */     throw new UnsupportedOperationException();
/*      */   }
/*      */ 
/*      */   private static ByteBuffer convertIcon(ByteBuffer icon, int width, int height) {
/* 1307 */     ByteBuffer icon_rgb = BufferUtils.createByteBuffer(icon.capacity());
/*      */ 
/* 1312 */     int depth = 4;
/*      */ 
/* 1314 */     for (int y = 0; y < height; y++) {
/* 1315 */       for (int x = 0; x < width; x++) {
/* 1316 */         byte r = icon.get(x * 4 + y * width * 4);
/* 1317 */         byte g = icon.get(x * 4 + y * width * 4 + 1);
/* 1318 */         byte b = icon.get(x * 4 + y * width * 4 + 2);
/*      */ 
/* 1320 */         icon_rgb.put(x * depth + y * width * depth, b);
/* 1321 */         icon_rgb.put(x * depth + y * width * depth + 1, g);
/* 1322 */         icon_rgb.put(x * depth + y * width * depth + 2, r);
/*      */       }
/*      */     }
/* 1325 */     return icon_rgb;
/*      */   }
/*      */ 
/*      */   private static ByteBuffer convertIconMask(ByteBuffer icon, int width, int height) {
/* 1329 */     ByteBuffer icon_mask = BufferUtils.createByteBuffer(icon.capacity() / 4 / 8);
/*      */ 
/* 1334 */     int depth = 4;
/*      */ 
/* 1336 */     for (int y = 0; y < height; y++) {
/* 1337 */       for (int x = 0; x < width; x++) {
/* 1338 */         byte a = icon.get(x * 4 + y * width * 4 + 3);
/*      */ 
/* 1340 */         int mask_index = x + y * width;
/* 1341 */         int mask_byte_index = mask_index / 8;
/* 1342 */         int mask_bit_index = mask_index % 8;
/* 1343 */         byte bit = (a & 0xFF) >= 127 ? 1 : 0;
/* 1344 */         byte new_byte = (byte)((icon_mask.get(mask_byte_index) | bit << mask_bit_index) & 0xFF);
/* 1345 */         icon_mask.put(mask_byte_index, new_byte);
/*      */       }
/*      */     }
/* 1348 */     return icon_mask;
/*      */   }
/*      */ 
/*      */   public int setIcon(ByteBuffer[] icons)
/*      */   {
/* 1364 */     lockAWT();
/*      */     try {
/* 1366 */       incDisplay();
/*      */       try {
/* 1368 */         for (ByteBuffer icon : icons) {
/* 1369 */           int size = icon.limit() / 4;
/* 1370 */           int dimension = (int)Math.sqrt(size);
/* 1371 */           if (dimension > 0) {
/* 1372 */             ByteBuffer icon_rgb = convertIcon(icon, dimension, dimension);
/* 1373 */             ByteBuffer icon_mask = convertIconMask(icon, dimension, dimension);
/* 1374 */             nSetWindowIcon(getDisplay(), getWindow(), icon_rgb, icon_rgb.capacity(), icon_mask, icon_mask.capacity(), dimension, dimension);
/* 1375 */             return 1;
/*      */           }
/*      */         }
/* 1378 */         return 0;
/*      */       } finally {
/*      */       }
/*      */     }
/*      */     catch (LWJGLException e) {
/* 1383 */       LWJGLUtil.log("Failed to set display icon: " + e);
/* 1384 */       return 0;
/*      */     } finally {
/* 1386 */       unlockAWT();
/*      */     }
/*      */   }
/*      */ 
/*      */   private static native void nSetWindowIcon(long paramLong1, long paramLong2, ByteBuffer paramByteBuffer1, int paramInt1, ByteBuffer paramByteBuffer2, int paramInt2, int paramInt3, int paramInt4);
/*      */ 
/*      */   public int getX() {
/* 1393 */     return this.window_x;
/*      */   }
/*      */ 
/*      */   public int getY() {
/* 1397 */     return this.window_y;
/*      */   }
/*      */ 
/*      */   public int getWidth() {
/* 1401 */     return this.window_width;
/*      */   }
/*      */ 
/*      */   public int getHeight() {
/* 1405 */     return this.window_height;
/*      */   }
/*      */ 
/*      */   public boolean isInsideWindow() {
/* 1409 */     return this.mouseInside;
/*      */   }
/*      */ 
/*      */   public void setResizable(boolean resizable) {
/* 1413 */     if (this.resizable == resizable) {
/* 1414 */       return;
/*      */     }
/*      */ 
/* 1417 */     this.resizable = resizable;
/* 1418 */     nSetWindowSize(getDisplay(), getWindow(), this.window_width, this.window_height, resizable);
/*      */   }
/*      */ 
/*      */   public boolean wasResized() {
/* 1422 */     if (this.resized) {
/* 1423 */       this.resized = false;
/* 1424 */       return true;
/*      */     }
/*      */ 
/* 1427 */     return false;
/*      */   }
/*      */ 
/*      */   private static final class Compiz
/*      */   {
/*      */     private static boolean applyFix;
/*      */     private static Provider provider;
/*      */ 
/*      */     static void init()
/*      */     {
/* 1448 */       if (Display.getPrivilegedBoolean("org.lwjgl.opengl.Window.nocompiz_lfs")) {
/* 1449 */         return;
/*      */       }
/* 1451 */       AccessController.doPrivileged(new PrivilegedAction()
/*      */       {
/*      */         public Object run() {
/*      */           try {
/* 1455 */             if (!LinuxDisplay.Compiz.isProcessActive("compiz")) {
/* 1456 */               Object localObject1 = null;
/*      */ 
/* 1546 */               return null;
/*      */             }
/* 1458 */             LinuxDisplay.Compiz.access$402(null);
/*      */ 
/* 1460 */             String providerName = null;
/*      */ 
/* 1463 */             if (LinuxDisplay.Compiz.isProcessActive("dbus-daemon")) {
/* 1464 */               providerName = "Dbus";
/* 1465 */               LinuxDisplay.Compiz.access$402(new LinuxDisplay.Compiz.Provider()
/*      */               {
/*      */                 private static final String KEY = "/org/freedesktop/compiz/workarounds/allscreens/legacy_fullscreen";
/*      */ 
/*      */                 public boolean hasLegacyFullscreenSupport() throws LWJGLException {
/* 1470 */                   List output = LinuxDisplay.Compiz.run(new String[] { "dbus-send", "--print-reply", "--type=method_call", "--dest=org.freedesktop.compiz", "/org/freedesktop/compiz/workarounds/allscreens/legacy_fullscreen", "org.freedesktop.compiz.get" });
/*      */ 
/* 1474 */                   if ((output == null) || (output.size() < 2)) {
/* 1475 */                     throw new LWJGLException("Invalid Dbus reply.");
/*      */                   }
/* 1477 */                   String line = (String)output.get(0);
/*      */ 
/* 1479 */                   if (!line.startsWith("method return")) {
/* 1480 */                     throw new LWJGLException("Invalid Dbus reply.");
/*      */                   }
/* 1482 */                   line = ((String)output.get(1)).trim();
/* 1483 */                   if ((!line.startsWith("boolean")) || (line.length() < 12)) {
/* 1484 */                     throw new LWJGLException("Invalid Dbus reply.");
/*      */                   }
/* 1486 */                   return "true".equalsIgnoreCase(line.substring("boolean".length() + 1));
/*      */                 }
/*      */ 
/*      */                 public void setLegacyFullscreenSupport(boolean state) throws LWJGLException {
/* 1490 */                   if (LinuxDisplay.Compiz.run(new String[] { "dbus-send", "--type=method_call", "--dest=org.freedesktop.compiz", "/org/freedesktop/compiz/workarounds/allscreens/legacy_fullscreen", "org.freedesktop.compiz.set", "boolean:" + Boolean.toString(state) }) == null)
/*      */                   {
/* 1493 */                     throw new LWJGLException("Failed to apply Compiz LFS workaround.");
/*      */                   }
/*      */                 } } );
/*      */             }
/*      */             else {
/*      */               try {
/* 1499 */                 Runtime.getRuntime().exec("gconftool");
/*      */ 
/* 1501 */                 providerName = "gconftool";
/* 1502 */                 LinuxDisplay.Compiz.access$402(new LinuxDisplay.Compiz.Provider()
/*      */                 {
/*      */                   private static final String KEY = "/apps/compiz/plugins/workarounds/allscreens/options/legacy_fullscreen";
/*      */ 
/*      */                   public boolean hasLegacyFullscreenSupport() throws LWJGLException {
/* 1507 */                     List output = LinuxDisplay.Compiz.run(new String[] { "gconftool", "-g", "/apps/compiz/plugins/workarounds/allscreens/options/legacy_fullscreen" });
/*      */ 
/* 1511 */                     if ((output == null) || (output.size() == 0)) {
/* 1512 */                       throw new LWJGLException("Invalid gconftool reply.");
/*      */                     }
/* 1514 */                     return Boolean.parseBoolean(((String)output.get(0)).trim());
/*      */                   }
/*      */ 
/*      */                   public void setLegacyFullscreenSupport(boolean state) throws LWJGLException {
/* 1518 */                     if (LinuxDisplay.Compiz.run(new String[] { "gconftool", "-s", "/apps/compiz/plugins/workarounds/allscreens/options/legacy_fullscreen", "-s", Boolean.toString(state), "-t", "bool" }) == null)
/*      */                     {
/* 1521 */                       throw new LWJGLException("Failed to apply Compiz LFS workaround.");
/*      */                     }
/* 1523 */                     if (state)
/*      */                     {
/*      */                       try
/*      */                       {
/* 1527 */                         Thread.sleep(200L);
/*      */                       } catch (InterruptedException e) {
/* 1529 */                         e.printStackTrace();
/*      */                       }
/*      */                     }
/*      */                   }
/*      */                 });
/*      */               }
/*      */               catch (IOException e)
/*      */               {
/*      */               }
/*      */             }
/* 1539 */             if ((LinuxDisplay.Compiz.provider != null) && (!LinuxDisplay.Compiz.provider.hasLegacyFullscreenSupport())) {
/* 1540 */               LinuxDisplay.Compiz.access$602(true);
/* 1541 */               LWJGLUtil.log("Using " + providerName + " to apply Compiz LFS workaround.");
/*      */             }
/*      */ 
/* 1546 */             return null;
/*      */           }
/*      */           catch (LWJGLException e)
/*      */           {
/* 1543 */             e = 
/* 1546 */               e; return null; } finally {  } return null;
/*      */         }
/*      */       });
/*      */     }
/*      */ 
/*      */     static void setLegacyFullscreenSupport(boolean enabled)
/*      */     {
/* 1553 */       if (!applyFix) {
/* 1554 */         return;
/*      */       }
/* 1556 */       AccessController.doPrivileged(new PrivilegedAction() {
/*      */         public Object run() {
/*      */           try {
/* 1559 */             LinuxDisplay.Compiz.provider.setLegacyFullscreenSupport(this.val$enabled);
/*      */           } catch (LWJGLException e) {
/* 1561 */             LWJGLUtil.log("Failed to change Compiz Legacy Fullscreen Support. Reason: " + e.getMessage());
/*      */           }
/* 1563 */           return null;
/*      */         }
/*      */       });
/*      */     }
/*      */ 
/*      */     private static List<String> run(String[] command) throws LWJGLException {
/* 1569 */       List output = new ArrayList();
/*      */       try
/*      */       {
/* 1572 */         Process p = Runtime.getRuntime().exec(command);
/*      */         try {
/* 1574 */           int exitValue = p.waitFor();
/* 1575 */           if (exitValue != 0)
/* 1576 */             return null;
/*      */         } catch (InterruptedException e) {
/* 1578 */           throw new LWJGLException("Process interrupted.", e);
/*      */         }
/*      */ 
/* 1581 */         BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
/*      */         String line;
/* 1584 */         while ((line = br.readLine()) != null) {
/* 1585 */           output.add(line);
/*      */         }
/* 1587 */         br.close();
/*      */       } catch (IOException e) {
/* 1589 */         throw new LWJGLException("Process failed.", e);
/*      */       }
/*      */ 
/* 1592 */       return output;
/*      */     }
/*      */ 
/*      */     private static boolean isProcessActive(String processName) throws LWJGLException {
/* 1596 */       List output = run(new String[] { "ps", "-C", processName });
/* 1597 */       if (output == null) {
/* 1598 */         return false;
/*      */       }
/* 1600 */       for (String line : output) {
/* 1601 */         if (line.contains(processName)) {
/* 1602 */           return true;
/*      */         }
/*      */       }
/* 1605 */       return false;
/*      */     }
/*      */ 
/*      */     private static abstract interface Provider
/*      */     {
/*      */       public abstract boolean hasLegacyFullscreenSupport()
/*      */         throws LWJGLException;
/*      */ 
/*      */       public abstract void setLegacyFullscreenSupport(boolean paramBoolean)
/*      */         throws LWJGLException;
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.LinuxDisplay
 * JD-Core Version:    0.6.2
 */