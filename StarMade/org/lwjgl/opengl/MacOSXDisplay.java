/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.awt.Canvas;
/*     */ import java.awt.Robot;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.LWJGLException;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ final class MacOSXDisplay
/*     */   implements DisplayImplementation
/*     */ {
/*     */   private static final int PBUFFER_HANDLE_SIZE = 24;
/*     */   private static final int GAMMA_LENGTH = 256;
/*     */   private Canvas canvas;
/*     */   private Robot robot;
/*     */   private MacOSXMouseEventQueue mouse_queue;
/*     */   private KeyboardEventQueue keyboard_queue;
/*     */   private DisplayMode requested_mode;
/*     */   private MacOSXNativeMouse mouse;
/*     */   private MacOSXNativeKeyboard keyboard;
/*     */   private ByteBuffer window;
/*     */   private ByteBuffer context;
/*  80 */   private boolean skipViewportValue = false;
/*  81 */   private static final IntBuffer current_viewport = BufferUtils.createIntBuffer(16);
/*     */   private boolean mouseInsideWindow;
/*     */   private boolean close_requested;
/*  87 */   private boolean native_mode = true;
/*     */ 
/*  89 */   private boolean updateNativeCursor = false;
/*     */ 
/*  91 */   private long currentNativeCursor = 0L;
/*     */ 
/*     */   private native ByteBuffer nCreateWindow(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, ByteBuffer paramByteBuffer1, ByteBuffer paramByteBuffer2)
/*     */     throws LWJGLException;
/*     */ 
/*     */   private native Object nGetCurrentDisplayMode();
/*     */ 
/*     */   private native void nGetDisplayModes(Object paramObject);
/*     */ 
/*     */   private native boolean nIsMiniaturized(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   private native boolean nIsFocused(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   private native void nSetResizable(ByteBuffer paramByteBuffer, boolean paramBoolean);
/*     */ 
/*     */   private native void nResizeWindow(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
/*     */ 
/*     */   private native boolean nWasResized(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   private native int nGetX(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   private native int nGetY(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   private native int nGetWidth(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   private native int nGetHeight(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   private native boolean nIsNativeMode(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   private static boolean isUndecorated()
/*     */   {
/* 124 */     return Display.getPrivilegedBoolean("org.lwjgl.opengl.Window.undecorated");
/*     */   }
/*     */ 
/*     */   public void createWindow(DrawableLWJGL drawable, DisplayMode mode, Canvas parent, int x, int y) throws LWJGLException {
/* 128 */     boolean fullscreen = Display.isFullscreen();
/* 129 */     boolean resizable = Display.isResizable();
/* 130 */     boolean parented = (parent != null) && (!fullscreen);
/*     */ 
/* 132 */     if (parented) this.canvas = parent; else {
/* 133 */       this.canvas = null;
/*     */     }
/* 135 */     this.close_requested = false;
/*     */ 
/* 137 */     DrawableGL gl_drawable = (DrawableGL)Display.getDrawable();
/* 138 */     PeerInfo peer_info = gl_drawable.peer_info;
/* 139 */     ByteBuffer peer_handle = peer_info.lockAndGetHandle();
/* 140 */     ByteBuffer window_handle = parented ? ((MacOSXCanvasPeerInfo)peer_info).window_handle : this.window;
/*     */     try
/*     */     {
/* 144 */       this.window = nCreateWindow(x, y, mode.getWidth(), mode.getHeight(), fullscreen, isUndecorated(), resizable, parented, peer_handle, window_handle);
/*     */ 
/* 148 */       if (fullscreen)
/*     */       {
/* 150 */         this.skipViewportValue = true;
/*     */ 
/* 152 */         if ((current_viewport.get(2) == 0) && (current_viewport.get(3) == 0)) {
/* 153 */           current_viewport.put(2, mode.getWidth());
/* 154 */           current_viewport.put(3, mode.getHeight());
/*     */         }
/*     */       }
/*     */ 
/* 158 */       this.native_mode = nIsNativeMode(peer_handle);
/*     */ 
/* 160 */       if (!this.native_mode) {
/* 161 */         this.robot = AWTUtil.createRobot(this.canvas);
/*     */       }
/*     */     }
/*     */     catch (LWJGLException e)
/*     */     {
/* 166 */       throw e;
/*     */     } finally {
/* 168 */       peer_info.unlock();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void doHandleQuit() {
/* 173 */     synchronized (this) {
/* 174 */       this.close_requested = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void mouseInsideWindow(boolean inside) {
/* 179 */     synchronized (this) {
/* 180 */       this.mouseInsideWindow = inside;
/*     */     }
/* 182 */     this.updateNativeCursor = true;
/*     */   }
/*     */ 
/*     */   public native void nDestroyCALayer(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   public native void nDestroyWindow(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   public void destroyWindow()
/*     */   {
/* 191 */     if (!this.native_mode) {
/* 192 */       DrawableGL gl_drawable = (DrawableGL)Display.getDrawable();
/* 193 */       PeerInfo peer_info = gl_drawable.peer_info;
/* 194 */       if (peer_info != null) {
/* 195 */         ByteBuffer peer_handle = peer_info.getHandle();
/* 196 */         nDestroyCALayer(peer_handle);
/*     */       }
/* 198 */       this.robot = null;
/*     */     }
/*     */ 
/* 201 */     nDestroyWindow(this.window);
/*     */   }
/*     */ 
/*     */   public int getGammaRampLength() {
/* 205 */     return 256;
/*     */   }
/*     */ 
/*     */   public native void setGammaRamp(FloatBuffer paramFloatBuffer) throws LWJGLException;
/*     */ 
/*     */   public String getAdapter() {
/* 211 */     return null;
/*     */   }
/*     */ 
/*     */   public String getVersion() {
/* 215 */     return null;
/*     */   }
/*     */ 
/*     */   private static boolean equals(DisplayMode mode1, DisplayMode mode2) {
/* 219 */     return (mode1.getWidth() == mode2.getWidth()) && (mode1.getHeight() == mode2.getHeight()) && (mode1.getBitsPerPixel() == mode2.getBitsPerPixel()) && (mode1.getFrequency() == mode2.getFrequency());
/*     */   }
/*     */ 
/*     */   public void switchDisplayMode(DisplayMode mode) throws LWJGLException
/*     */   {
/* 224 */     DisplayMode[] modes = getAvailableDisplayModes();
/*     */ 
/* 226 */     for (DisplayMode available_mode : modes) {
/* 227 */       if (equals(available_mode, mode)) {
/* 228 */         this.requested_mode = available_mode;
/* 229 */         return;
/*     */       }
/*     */     }
/*     */ 
/* 233 */     throw new LWJGLException(mode + " is not supported");
/*     */   }
/*     */ 
/*     */   public void resetDisplayMode() {
/* 237 */     this.requested_mode = null;
/* 238 */     restoreGamma();
/*     */   }
/*     */ 
/*     */   private native void restoreGamma();
/*     */ 
/*     */   public Object createDisplayMode(int width, int height, int bitsPerPixel, int refreshRate) {
/* 244 */     return new DisplayMode(width, height, bitsPerPixel, refreshRate);
/*     */   }
/*     */ 
/*     */   public DisplayMode init() throws LWJGLException {
/* 248 */     return (DisplayMode)nGetCurrentDisplayMode();
/*     */   }
/*     */ 
/*     */   public void addDisplayMode(Object modesList, int width, int height, int bitsPerPixel, int refreshRate) {
/* 252 */     List modes = (List)modesList;
/* 253 */     DisplayMode displayMode = new DisplayMode(width, height, bitsPerPixel, refreshRate);
/* 254 */     modes.add(displayMode);
/*     */   }
/*     */ 
/*     */   public DisplayMode[] getAvailableDisplayModes() throws LWJGLException {
/* 258 */     List modes = new ArrayList();
/* 259 */     nGetDisplayModes(modes);
/* 260 */     return (DisplayMode[])modes.toArray(new DisplayMode[modes.size()]);
/*     */   }
/*     */ 
/*     */   private native void nSetTitle(ByteBuffer paramByteBuffer1, ByteBuffer paramByteBuffer2);
/*     */ 
/*     */   public void setTitle(String title) {
/* 266 */     ByteBuffer buffer = MemoryUtil.encodeUTF8(title);
/* 267 */     nSetTitle(this.window, buffer);
/*     */   }
/*     */ 
/*     */   public boolean isCloseRequested()
/*     */   {
/*     */     boolean result;
/* 272 */     synchronized (this) {
/* 273 */       result = this.close_requested;
/* 274 */       this.close_requested = false;
/*     */     }
/* 276 */     return result;
/*     */   }
/*     */ 
/*     */   public boolean isVisible() {
/* 280 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean isActive() {
/* 284 */     if (this.native_mode) {
/* 285 */       return nIsFocused(this.window);
/*     */     }
/*     */ 
/* 288 */     return Display.getParent().hasFocus();
/*     */   }
/*     */ 
/*     */   public Canvas getCanvas()
/*     */   {
/* 293 */     return this.canvas;
/*     */   }
/*     */ 
/*     */   public boolean isDirty() {
/* 297 */     return false;
/*     */   }
/*     */ 
/*     */   public PeerInfo createPeerInfo(PixelFormat pixel_format, ContextAttribs attribs) throws LWJGLException {
/*     */     try {
/* 302 */       return new MacOSXDisplayPeerInfo(pixel_format, attribs, true); } catch (LWJGLException e) {
/*     */     }
/* 304 */     return new MacOSXDisplayPeerInfo(pixel_format, attribs, false);
/*     */   }
/*     */ 
/*     */   public void update()
/*     */   {
/* 309 */     boolean should_update = true;
/*     */ 
/* 311 */     DrawableGL drawable = (DrawableGL)Display.getDrawable();
/* 312 */     if (should_update) {
/* 313 */       drawable.context.update();
/*     */ 
/* 315 */       if (this.skipViewportValue) this.skipViewportValue = false; else
/* 316 */         GL11.glGetInteger(2978, current_viewport);
/* 317 */       GL11.glViewport(current_viewport.get(0), current_viewport.get(1), current_viewport.get(2), current_viewport.get(3));
/*     */     }
/*     */ 
/* 320 */     if ((this.native_mode) && (this.updateNativeCursor)) {
/* 321 */       this.updateNativeCursor = false;
/*     */       try {
/* 323 */         setNativeCursor(Long.valueOf(this.currentNativeCursor));
/*     */       } catch (LWJGLException e) {
/* 325 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void reshape(int x, int y, int width, int height)
/*     */   {
/*     */   }
/*     */ 
/*     */   public boolean hasWheel()
/*     */   {
/* 338 */     return AWTUtil.hasWheel();
/*     */   }
/*     */ 
/*     */   public int getButtonCount() {
/* 342 */     return AWTUtil.getButtonCount();
/*     */   }
/*     */ 
/*     */   public void createMouse() throws LWJGLException {
/* 346 */     if (this.native_mode) {
/* 347 */       this.mouse = new MacOSXNativeMouse(this, this.window);
/* 348 */       this.mouse.register();
/*     */     }
/*     */     else {
/* 351 */       this.mouse_queue = new MacOSXMouseEventQueue(this.canvas);
/* 352 */       this.mouse_queue.register();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void destroyMouse() {
/* 357 */     if (this.native_mode)
/*     */     {
/*     */       try {
/* 360 */         MacOSXNativeMouse.setCursor(0L);
/*     */       }
/*     */       catch (LWJGLException e) {
/*     */       }
/* 364 */       grabMouse(false);
/*     */ 
/* 366 */       if (this.mouse != null) {
/* 367 */         this.mouse.unregister();
/*     */       }
/* 369 */       this.mouse = null;
/*     */     }
/*     */     else {
/* 372 */       if (this.mouse_queue != null) {
/* 373 */         MacOSXMouseEventQueue.nGrabMouse(false);
/* 374 */         this.mouse_queue.unregister();
/*     */       }
/* 376 */       this.mouse_queue = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void pollMouse(IntBuffer coord_buffer, ByteBuffer buttons_buffer) {
/* 381 */     if (this.native_mode) {
/* 382 */       this.mouse.poll(coord_buffer, buttons_buffer);
/*     */     }
/*     */     else
/* 385 */       this.mouse_queue.poll(coord_buffer, buttons_buffer);
/*     */   }
/*     */ 
/*     */   public void readMouse(ByteBuffer buffer)
/*     */   {
/* 390 */     if (this.native_mode) {
/* 391 */       this.mouse.copyEvents(buffer);
/*     */     }
/*     */     else
/* 394 */       this.mouse_queue.copyEvents(buffer);
/*     */   }
/*     */ 
/*     */   public void grabMouse(boolean grab)
/*     */   {
/* 399 */     if (this.native_mode) {
/* 400 */       this.mouse.setGrabbed(grab);
/*     */     }
/*     */     else
/* 403 */       this.mouse_queue.setGrabbed(grab);
/*     */   }
/*     */ 
/*     */   public int getNativeCursorCapabilities()
/*     */   {
/* 408 */     if (this.native_mode) {
/* 409 */       return 7;
/*     */     }
/*     */ 
/* 412 */     return AWTUtil.getNativeCursorCapabilities();
/*     */   }
/*     */ 
/*     */   public void setCursorPosition(int x, int y) {
/* 416 */     if ((this.native_mode) && 
/* 417 */       (this.mouse != null))
/* 418 */       this.mouse.setCursorPosition(x, y);
/*     */   }
/*     */ 
/*     */   public void setNativeCursor(Object handle)
/*     */     throws LWJGLException
/*     */   {
/* 427 */     if (this.native_mode) {
/* 428 */       this.currentNativeCursor = getCursorHandle(handle);
/* 429 */       if (Display.isCreated())
/* 430 */         if (this.mouseInsideWindow) MacOSXNativeMouse.setCursor(this.currentNativeCursor); else
/* 431 */           MacOSXNativeMouse.setCursor(0L);
/*     */     }
/*     */   }
/*     */ 
/*     */   public int getMinCursorSize()
/*     */   {
/* 437 */     return AWTUtil.getMinCursorSize();
/*     */   }
/*     */ 
/*     */   public int getMaxCursorSize() {
/* 441 */     return AWTUtil.getMaxCursorSize();
/*     */   }
/*     */ 
/*     */   public void createKeyboard() throws LWJGLException
/*     */   {
/* 446 */     if (this.native_mode) {
/* 447 */       this.keyboard = new MacOSXNativeKeyboard(this.window);
/* 448 */       this.keyboard.register();
/*     */     }
/*     */     else {
/* 451 */       this.keyboard_queue = new KeyboardEventQueue(this.canvas);
/* 452 */       this.keyboard_queue.register();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void destroyKeyboard() {
/* 457 */     if (this.native_mode) {
/* 458 */       if (this.keyboard != null) {
/* 459 */         this.keyboard.unregister();
/*     */       }
/* 461 */       this.keyboard = null;
/*     */     }
/*     */     else {
/* 464 */       if (this.keyboard_queue != null) {
/* 465 */         this.keyboard_queue.unregister();
/*     */       }
/* 467 */       this.keyboard_queue = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void pollKeyboard(ByteBuffer keyDownBuffer) {
/* 472 */     if (this.native_mode) {
/* 473 */       this.keyboard.poll(keyDownBuffer);
/*     */     }
/*     */     else
/* 476 */       this.keyboard_queue.poll(keyDownBuffer);
/*     */   }
/*     */ 
/*     */   public void readKeyboard(ByteBuffer buffer)
/*     */   {
/* 481 */     if (this.native_mode) {
/* 482 */       this.keyboard.copyEvents(buffer);
/*     */     }
/*     */     else
/* 485 */       this.keyboard_queue.copyEvents(buffer);
/*     */   }
/*     */ 
/*     */   public Object createCursor(int width, int height, int xHotspot, int yHotspot, int numImages, IntBuffer images, IntBuffer delays)
/*     */     throws LWJGLException
/*     */   {
/* 491 */     if (this.native_mode) {
/* 492 */       long cursor = MacOSXNativeMouse.createCursor(width, height, xHotspot, yHotspot, numImages, images, delays);
/* 493 */       return Long.valueOf(cursor);
/*     */     }
/*     */ 
/* 496 */     return AWTUtil.createCursor(width, height, xHotspot, yHotspot, numImages, images, delays);
/*     */   }
/*     */ 
/*     */   public void destroyCursor(Object cursor_handle)
/*     */   {
/* 501 */     long handle = getCursorHandle(cursor_handle);
/*     */ 
/* 504 */     if (this.currentNativeCursor == handle) {
/* 505 */       this.currentNativeCursor = 0L;
/*     */     }
/*     */ 
/* 508 */     MacOSXNativeMouse.destroyCursor(handle);
/*     */   }
/*     */ 
/*     */   private static long getCursorHandle(Object cursor_handle) {
/* 512 */     return cursor_handle != null ? ((Long)cursor_handle).longValue() : 0L;
/*     */   }
/*     */ 
/*     */   public int getPbufferCapabilities() {
/* 516 */     return 1;
/*     */   }
/*     */ 
/*     */   public boolean isBufferLost(PeerInfo handle) {
/* 520 */     return false;
/*     */   }
/*     */ 
/*     */   public PeerInfo createPbuffer(int width, int height, PixelFormat pixel_format, ContextAttribs attribs, IntBuffer pixelFormatCaps, IntBuffer pBufferAttribs)
/*     */     throws LWJGLException
/*     */   {
/* 526 */     return new MacOSXPbufferPeerInfo(width, height, pixel_format, attribs);
/*     */   }
/*     */ 
/*     */   public void setPbufferAttrib(PeerInfo handle, int attrib, int value) {
/* 530 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public void bindTexImageToPbuffer(PeerInfo handle, int buffer) {
/* 534 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public void releaseTexImageFromPbuffer(PeerInfo handle, int buffer) {
/* 538 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public int setIcon(ByteBuffer[] icons)
/*     */   {
/* 582 */     return 0;
/*     */   }
/*     */ 
/*     */   public int getX() {
/* 586 */     return nGetX(this.window);
/*     */   }
/*     */ 
/*     */   public int getY() {
/* 590 */     return nGetY(this.window);
/*     */   }
/*     */ 
/*     */   public int getWidth() {
/* 594 */     return nGetWidth(this.window);
/*     */   }
/*     */ 
/*     */   public int getHeight() {
/* 598 */     return nGetHeight(this.window);
/*     */   }
/*     */ 
/*     */   public boolean isInsideWindow() {
/* 602 */     return this.mouseInsideWindow;
/*     */   }
/*     */ 
/*     */   public void setResizable(boolean resizable) {
/* 606 */     nSetResizable(this.window, resizable);
/*     */   }
/*     */ 
/*     */   public boolean wasResized() {
/* 610 */     return nWasResized(this.window);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.MacOSXDisplay
 * JD-Core Version:    0.6.2
 */