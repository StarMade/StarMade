/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.awt.Canvas;
/*   4:    */import java.awt.Robot;
/*   5:    */import java.nio.ByteBuffer;
/*   6:    */import java.nio.FloatBuffer;
/*   7:    */import java.nio.IntBuffer;
/*   8:    */import java.util.ArrayList;
/*   9:    */import java.util.List;
/*  10:    */import org.lwjgl.BufferUtils;
/*  11:    */import org.lwjgl.LWJGLException;
/*  12:    */import org.lwjgl.MemoryUtil;
/*  13:    */
/*  66:    */final class MacOSXDisplay
/*  67:    */  implements DisplayImplementation
/*  68:    */{
/*  69:    */  private static final int PBUFFER_HANDLE_SIZE = 24;
/*  70:    */  private static final int GAMMA_LENGTH = 256;
/*  71:    */  private Canvas canvas;
/*  72:    */  private Robot robot;
/*  73:    */  private MacOSXMouseEventQueue mouse_queue;
/*  74:    */  private KeyboardEventQueue keyboard_queue;
/*  75:    */  private DisplayMode requested_mode;
/*  76:    */  private MacOSXNativeMouse mouse;
/*  77:    */  private MacOSXNativeKeyboard keyboard;
/*  78:    */  private ByteBuffer window;
/*  79:    */  private ByteBuffer context;
/*  80: 80 */  private boolean skipViewportValue = false;
/*  81: 81 */  private static final IntBuffer current_viewport = BufferUtils.createIntBuffer(16);
/*  82:    */  
/*  83:    */  private boolean mouseInsideWindow;
/*  84:    */  
/*  85:    */  private boolean close_requested;
/*  86:    */  
/*  87: 87 */  private boolean native_mode = true;
/*  88:    */  
/*  89: 89 */  private boolean updateNativeCursor = false;
/*  90:    */  
/*  91: 91 */  private long currentNativeCursor = 0L;
/*  92:    */  
/*  94:    */  private native ByteBuffer nCreateWindow(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, ByteBuffer paramByteBuffer1, ByteBuffer paramByteBuffer2)
/*  95:    */    throws LWJGLException;
/*  96:    */  
/*  98:    */  private native Object nGetCurrentDisplayMode();
/*  99:    */  
/* 100:    */  private native void nGetDisplayModes(Object paramObject);
/* 101:    */  
/* 102:    */  private native boolean nIsMiniaturized(ByteBuffer paramByteBuffer);
/* 103:    */  
/* 104:    */  private native boolean nIsFocused(ByteBuffer paramByteBuffer);
/* 105:    */  
/* 106:    */  private native void nSetResizable(ByteBuffer paramByteBuffer, boolean paramBoolean);
/* 107:    */  
/* 108:    */  private native void nResizeWindow(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
/* 109:    */  
/* 110:    */  private native boolean nWasResized(ByteBuffer paramByteBuffer);
/* 111:    */  
/* 112:    */  private native int nGetX(ByteBuffer paramByteBuffer);
/* 113:    */  
/* 114:    */  private native int nGetY(ByteBuffer paramByteBuffer);
/* 115:    */  
/* 116:    */  private native int nGetWidth(ByteBuffer paramByteBuffer);
/* 117:    */  
/* 118:    */  private native int nGetHeight(ByteBuffer paramByteBuffer);
/* 119:    */  
/* 120:    */  private native boolean nIsNativeMode(ByteBuffer paramByteBuffer);
/* 121:    */  
/* 122:    */  private static boolean isUndecorated()
/* 123:    */  {
/* 124:124 */    return Display.getPrivilegedBoolean("org.lwjgl.opengl.Window.undecorated");
/* 125:    */  }
/* 126:    */  
/* 127:    */  public void createWindow(DrawableLWJGL drawable, DisplayMode mode, Canvas parent, int x, int y) throws LWJGLException {
/* 128:128 */    boolean fullscreen = Display.isFullscreen();
/* 129:129 */    boolean resizable = Display.isResizable();
/* 130:130 */    boolean parented = (parent != null) && (!fullscreen);
/* 131:    */    
/* 132:132 */    if (parented) this.canvas = parent; else {
/* 133:133 */      this.canvas = null;
/* 134:    */    }
/* 135:135 */    this.close_requested = false;
/* 136:    */    
/* 137:137 */    DrawableGL gl_drawable = (DrawableGL)Display.getDrawable();
/* 138:138 */    PeerInfo peer_info = gl_drawable.peer_info;
/* 139:139 */    ByteBuffer peer_handle = peer_info.lockAndGetHandle();
/* 140:140 */    ByteBuffer window_handle = parented ? ((MacOSXCanvasPeerInfo)peer_info).window_handle : this.window;
/* 141:    */    
/* 142:    */    try
/* 143:    */    {
/* 144:144 */      this.window = nCreateWindow(x, y, mode.getWidth(), mode.getHeight(), fullscreen, isUndecorated(), resizable, parented, peer_handle, window_handle);
/* 145:    */      
/* 148:148 */      if (fullscreen)
/* 149:    */      {
/* 150:150 */        this.skipViewportValue = true;
/* 151:    */        
/* 152:152 */        if ((current_viewport.get(2) == 0) && (current_viewport.get(3) == 0)) {
/* 153:153 */          current_viewport.put(2, mode.getWidth());
/* 154:154 */          current_viewport.put(3, mode.getHeight());
/* 155:    */        }
/* 156:    */      }
/* 157:    */      
/* 158:158 */      this.native_mode = nIsNativeMode(peer_handle);
/* 159:    */      
/* 160:160 */      if (!this.native_mode) {
/* 161:161 */        this.robot = AWTUtil.createRobot(this.canvas);
/* 162:    */      }
/* 163:    */    }
/* 164:    */    catch (LWJGLException e)
/* 165:    */    {
/* 166:166 */      throw e;
/* 167:    */    } finally {
/* 168:168 */      peer_info.unlock();
/* 169:    */    }
/* 170:    */  }
/* 171:    */  
/* 172:    */  public void doHandleQuit() {
/* 173:173 */    synchronized (this) {
/* 174:174 */      this.close_requested = true;
/* 175:    */    }
/* 176:    */  }
/* 177:    */  
/* 178:    */  public void mouseInsideWindow(boolean inside) {
/* 179:179 */    synchronized (this) {
/* 180:180 */      this.mouseInsideWindow = inside;
/* 181:    */    }
/* 182:182 */    this.updateNativeCursor = true;
/* 183:    */  }
/* 184:    */  
/* 185:    */  public native void nDestroyCALayer(ByteBuffer paramByteBuffer);
/* 186:    */  
/* 187:    */  public native void nDestroyWindow(ByteBuffer paramByteBuffer);
/* 188:    */  
/* 189:    */  public void destroyWindow()
/* 190:    */  {
/* 191:191 */    if (!this.native_mode) {
/* 192:192 */      DrawableGL gl_drawable = (DrawableGL)Display.getDrawable();
/* 193:193 */      PeerInfo peer_info = gl_drawable.peer_info;
/* 194:194 */      if (peer_info != null) {
/* 195:195 */        ByteBuffer peer_handle = peer_info.getHandle();
/* 196:196 */        nDestroyCALayer(peer_handle);
/* 197:    */      }
/* 198:198 */      this.robot = null;
/* 199:    */    }
/* 200:    */    
/* 201:201 */    nDestroyWindow(this.window);
/* 202:    */  }
/* 203:    */  
/* 204:    */  public int getGammaRampLength() {
/* 205:205 */    return 256;
/* 206:    */  }
/* 207:    */  
/* 208:    */  public native void setGammaRamp(FloatBuffer paramFloatBuffer) throws LWJGLException;
/* 209:    */  
/* 210:    */  public String getAdapter() {
/* 211:211 */    return null;
/* 212:    */  }
/* 213:    */  
/* 214:    */  public String getVersion() {
/* 215:215 */    return null;
/* 216:    */  }
/* 217:    */  
/* 218:    */  private static boolean equals(DisplayMode mode1, DisplayMode mode2) {
/* 219:219 */    return (mode1.getWidth() == mode2.getWidth()) && (mode1.getHeight() == mode2.getHeight()) && (mode1.getBitsPerPixel() == mode2.getBitsPerPixel()) && (mode1.getFrequency() == mode2.getFrequency());
/* 220:    */  }
/* 221:    */  
/* 222:    */  public void switchDisplayMode(DisplayMode mode) throws LWJGLException
/* 223:    */  {
/* 224:224 */    DisplayMode[] modes = getAvailableDisplayModes();
/* 225:    */    
/* 226:226 */    for (DisplayMode available_mode : modes) {
/* 227:227 */      if (equals(available_mode, mode)) {
/* 228:228 */        this.requested_mode = available_mode;
/* 229:229 */        return;
/* 230:    */      }
/* 231:    */    }
/* 232:    */    
/* 233:233 */    throw new LWJGLException(mode + " is not supported");
/* 234:    */  }
/* 235:    */  
/* 236:    */  public void resetDisplayMode() {
/* 237:237 */    this.requested_mode = null;
/* 238:238 */    restoreGamma();
/* 239:    */  }
/* 240:    */  
/* 241:    */  private native void restoreGamma();
/* 242:    */  
/* 243:    */  public Object createDisplayMode(int width, int height, int bitsPerPixel, int refreshRate) {
/* 244:244 */    return new DisplayMode(width, height, bitsPerPixel, refreshRate);
/* 245:    */  }
/* 246:    */  
/* 247:    */  public DisplayMode init() throws LWJGLException {
/* 248:248 */    return (DisplayMode)nGetCurrentDisplayMode();
/* 249:    */  }
/* 250:    */  
/* 251:    */  public void addDisplayMode(Object modesList, int width, int height, int bitsPerPixel, int refreshRate) {
/* 252:252 */    List<DisplayMode> modes = (List)modesList;
/* 253:253 */    DisplayMode displayMode = new DisplayMode(width, height, bitsPerPixel, refreshRate);
/* 254:254 */    modes.add(displayMode);
/* 255:    */  }
/* 256:    */  
/* 257:    */  public DisplayMode[] getAvailableDisplayModes() throws LWJGLException {
/* 258:258 */    List<DisplayMode> modes = new ArrayList();
/* 259:259 */    nGetDisplayModes(modes);
/* 260:260 */    return (DisplayMode[])modes.toArray(new DisplayMode[modes.size()]);
/* 261:    */  }
/* 262:    */  
/* 263:    */  private native void nSetTitle(ByteBuffer paramByteBuffer1, ByteBuffer paramByteBuffer2);
/* 264:    */  
/* 265:    */  public void setTitle(String title) {
/* 266:266 */    ByteBuffer buffer = MemoryUtil.encodeUTF8(title);
/* 267:267 */    nSetTitle(this.window, buffer);
/* 268:    */  }
/* 269:    */  
/* 270:    */  public boolean isCloseRequested() {
/* 271:    */    boolean result;
/* 272:272 */    synchronized (this) {
/* 273:273 */      result = this.close_requested;
/* 274:274 */      this.close_requested = false;
/* 275:    */    }
/* 276:276 */    return result;
/* 277:    */  }
/* 278:    */  
/* 279:    */  public boolean isVisible() {
/* 280:280 */    return true;
/* 281:    */  }
/* 282:    */  
/* 283:    */  public boolean isActive() {
/* 284:284 */    if (this.native_mode) {
/* 285:285 */      return nIsFocused(this.window);
/* 286:    */    }
/* 287:    */    
/* 288:288 */    return Display.getParent().hasFocus();
/* 289:    */  }
/* 290:    */  
/* 291:    */  public Canvas getCanvas()
/* 292:    */  {
/* 293:293 */    return this.canvas;
/* 294:    */  }
/* 295:    */  
/* 296:    */  public boolean isDirty() {
/* 297:297 */    return false;
/* 298:    */  }
/* 299:    */  
/* 300:    */  public PeerInfo createPeerInfo(PixelFormat pixel_format, ContextAttribs attribs) throws LWJGLException {
/* 301:    */    try {
/* 302:302 */      return new MacOSXDisplayPeerInfo(pixel_format, attribs, true);
/* 303:    */    } catch (LWJGLException e) {}
/* 304:304 */    return new MacOSXDisplayPeerInfo(pixel_format, attribs, false);
/* 305:    */  }
/* 306:    */  
/* 307:    */  public void update()
/* 308:    */  {
/* 309:309 */    boolean should_update = true;
/* 310:    */    
/* 311:311 */    DrawableGL drawable = (DrawableGL)Display.getDrawable();
/* 312:312 */    if (should_update) {
/* 313:313 */      drawable.context.update();
/* 314:    */      
/* 315:315 */      if (this.skipViewportValue) this.skipViewportValue = false; else
/* 316:316 */        GL11.glGetInteger(2978, current_viewport);
/* 317:317 */      GL11.glViewport(current_viewport.get(0), current_viewport.get(1), current_viewport.get(2), current_viewport.get(3));
/* 318:    */    }
/* 319:    */    
/* 320:320 */    if ((this.native_mode) && (this.updateNativeCursor)) {
/* 321:321 */      this.updateNativeCursor = false;
/* 322:    */      try {
/* 323:323 */        setNativeCursor(Long.valueOf(this.currentNativeCursor));
/* 324:    */      } catch (LWJGLException e) {
/* 325:325 */        e.printStackTrace();
/* 326:    */      }
/* 327:    */    }
/* 328:    */  }
/* 329:    */  
/* 332:    */  public void reshape(int x, int y, int width, int height) {}
/* 333:    */  
/* 336:    */  public boolean hasWheel()
/* 337:    */  {
/* 338:338 */    return AWTUtil.hasWheel();
/* 339:    */  }
/* 340:    */  
/* 341:    */  public int getButtonCount() {
/* 342:342 */    return AWTUtil.getButtonCount();
/* 343:    */  }
/* 344:    */  
/* 345:    */  public void createMouse() throws LWJGLException {
/* 346:346 */    if (this.native_mode) {
/* 347:347 */      this.mouse = new MacOSXNativeMouse(this, this.window);
/* 348:348 */      this.mouse.register();
/* 349:    */    }
/* 350:    */    else {
/* 351:351 */      this.mouse_queue = new MacOSXMouseEventQueue(this.canvas);
/* 352:352 */      this.mouse_queue.register();
/* 353:    */    }
/* 354:    */  }
/* 355:    */  
/* 356:    */  public void destroyMouse() {
/* 357:357 */    if (this.native_mode)
/* 358:    */    {
/* 359:    */      try {
/* 360:360 */        MacOSXNativeMouse.setCursor(0L);
/* 361:    */      }
/* 362:    */      catch (LWJGLException e) {}
/* 363:    */      
/* 364:364 */      grabMouse(false);
/* 365:    */      
/* 366:366 */      if (this.mouse != null) {
/* 367:367 */        this.mouse.unregister();
/* 368:    */      }
/* 369:369 */      this.mouse = null;
/* 370:    */    }
/* 371:    */    else {
/* 372:372 */      if (this.mouse_queue != null) {
/* 373:373 */        MacOSXMouseEventQueue.nGrabMouse(false);
/* 374:374 */        this.mouse_queue.unregister();
/* 375:    */      }
/* 376:376 */      this.mouse_queue = null;
/* 377:    */    }
/* 378:    */  }
/* 379:    */  
/* 380:    */  public void pollMouse(IntBuffer coord_buffer, ByteBuffer buttons_buffer) {
/* 381:381 */    if (this.native_mode) {
/* 382:382 */      this.mouse.poll(coord_buffer, buttons_buffer);
/* 383:    */    }
/* 384:    */    else {
/* 385:385 */      this.mouse_queue.poll(coord_buffer, buttons_buffer);
/* 386:    */    }
/* 387:    */  }
/* 388:    */  
/* 389:    */  public void readMouse(ByteBuffer buffer) {
/* 390:390 */    if (this.native_mode) {
/* 391:391 */      this.mouse.copyEvents(buffer);
/* 392:    */    }
/* 393:    */    else {
/* 394:394 */      this.mouse_queue.copyEvents(buffer);
/* 395:    */    }
/* 396:    */  }
/* 397:    */  
/* 398:    */  public void grabMouse(boolean grab) {
/* 399:399 */    if (this.native_mode) {
/* 400:400 */      this.mouse.setGrabbed(grab);
/* 401:    */    }
/* 402:    */    else {
/* 403:403 */      this.mouse_queue.setGrabbed(grab);
/* 404:    */    }
/* 405:    */  }
/* 406:    */  
/* 407:    */  public int getNativeCursorCapabilities() {
/* 408:408 */    if (this.native_mode) {
/* 409:409 */      return 7;
/* 410:    */    }
/* 411:    */    
/* 412:412 */    return AWTUtil.getNativeCursorCapabilities();
/* 413:    */  }
/* 414:    */  
/* 415:    */  public void setCursorPosition(int x, int y) {
/* 416:416 */    if ((this.native_mode) && 
/* 417:417 */      (this.mouse != null)) {
/* 418:418 */      this.mouse.setCursorPosition(x, y);
/* 419:    */    }
/* 420:    */  }
/* 421:    */  
/* 424:    */  public void setNativeCursor(Object handle)
/* 425:    */    throws LWJGLException
/* 426:    */  {
/* 427:427 */    if (this.native_mode) {
/* 428:428 */      this.currentNativeCursor = getCursorHandle(handle);
/* 429:429 */      if (Display.isCreated()) {
/* 430:430 */        if (this.mouseInsideWindow) MacOSXNativeMouse.setCursor(this.currentNativeCursor); else
/* 431:431 */          MacOSXNativeMouse.setCursor(0L);
/* 432:    */      }
/* 433:    */    }
/* 434:    */  }
/* 435:    */  
/* 436:    */  public int getMinCursorSize() {
/* 437:437 */    return AWTUtil.getMinCursorSize();
/* 438:    */  }
/* 439:    */  
/* 440:    */  public int getMaxCursorSize() {
/* 441:441 */    return AWTUtil.getMaxCursorSize();
/* 442:    */  }
/* 443:    */  
/* 444:    */  public void createKeyboard() throws LWJGLException
/* 445:    */  {
/* 446:446 */    if (this.native_mode) {
/* 447:447 */      this.keyboard = new MacOSXNativeKeyboard(this.window);
/* 448:448 */      this.keyboard.register();
/* 449:    */    }
/* 450:    */    else {
/* 451:451 */      this.keyboard_queue = new KeyboardEventQueue(this.canvas);
/* 452:452 */      this.keyboard_queue.register();
/* 453:    */    }
/* 454:    */  }
/* 455:    */  
/* 456:    */  public void destroyKeyboard() {
/* 457:457 */    if (this.native_mode) {
/* 458:458 */      if (this.keyboard != null) {
/* 459:459 */        this.keyboard.unregister();
/* 460:    */      }
/* 461:461 */      this.keyboard = null;
/* 462:    */    }
/* 463:    */    else {
/* 464:464 */      if (this.keyboard_queue != null) {
/* 465:465 */        this.keyboard_queue.unregister();
/* 466:    */      }
/* 467:467 */      this.keyboard_queue = null;
/* 468:    */    }
/* 469:    */  }
/* 470:    */  
/* 471:    */  public void pollKeyboard(ByteBuffer keyDownBuffer) {
/* 472:472 */    if (this.native_mode) {
/* 473:473 */      this.keyboard.poll(keyDownBuffer);
/* 474:    */    }
/* 475:    */    else {
/* 476:476 */      this.keyboard_queue.poll(keyDownBuffer);
/* 477:    */    }
/* 478:    */  }
/* 479:    */  
/* 480:    */  public void readKeyboard(ByteBuffer buffer) {
/* 481:481 */    if (this.native_mode) {
/* 482:482 */      this.keyboard.copyEvents(buffer);
/* 483:    */    }
/* 484:    */    else {
/* 485:485 */      this.keyboard_queue.copyEvents(buffer);
/* 486:    */    }
/* 487:    */  }
/* 488:    */  
/* 489:    */  public Object createCursor(int width, int height, int xHotspot, int yHotspot, int numImages, IntBuffer images, IntBuffer delays) throws LWJGLException
/* 490:    */  {
/* 491:491 */    if (this.native_mode) {
/* 492:492 */      long cursor = MacOSXNativeMouse.createCursor(width, height, xHotspot, yHotspot, numImages, images, delays);
/* 493:493 */      return Long.valueOf(cursor);
/* 494:    */    }
/* 495:    */    
/* 496:496 */    return AWTUtil.createCursor(width, height, xHotspot, yHotspot, numImages, images, delays);
/* 497:    */  }
/* 498:    */  
/* 499:    */  public void destroyCursor(Object cursor_handle)
/* 500:    */  {
/* 501:501 */    long handle = getCursorHandle(cursor_handle);
/* 502:    */    
/* 504:504 */    if (this.currentNativeCursor == handle) {
/* 505:505 */      this.currentNativeCursor = 0L;
/* 506:    */    }
/* 507:    */    
/* 508:508 */    MacOSXNativeMouse.destroyCursor(handle);
/* 509:    */  }
/* 510:    */  
/* 511:    */  private static long getCursorHandle(Object cursor_handle) {
/* 512:512 */    return cursor_handle != null ? ((Long)cursor_handle).longValue() : 0L;
/* 513:    */  }
/* 514:    */  
/* 515:    */  public int getPbufferCapabilities() {
/* 516:516 */    return 1;
/* 517:    */  }
/* 518:    */  
/* 519:    */  public boolean isBufferLost(PeerInfo handle) {
/* 520:520 */    return false;
/* 521:    */  }
/* 522:    */  
/* 523:    */  public PeerInfo createPbuffer(int width, int height, PixelFormat pixel_format, ContextAttribs attribs, IntBuffer pixelFormatCaps, IntBuffer pBufferAttribs)
/* 524:    */    throws LWJGLException
/* 525:    */  {
/* 526:526 */    return new MacOSXPbufferPeerInfo(width, height, pixel_format, attribs);
/* 527:    */  }
/* 528:    */  
/* 529:    */  public void setPbufferAttrib(PeerInfo handle, int attrib, int value) {
/* 530:530 */    throw new UnsupportedOperationException();
/* 531:    */  }
/* 532:    */  
/* 533:    */  public void bindTexImageToPbuffer(PeerInfo handle, int buffer) {
/* 534:534 */    throw new UnsupportedOperationException();
/* 535:    */  }
/* 536:    */  
/* 537:    */  public void releaseTexImageFromPbuffer(PeerInfo handle, int buffer) {
/* 538:538 */    throw new UnsupportedOperationException();
/* 539:    */  }
/* 540:    */  
/* 580:    */  public int setIcon(ByteBuffer[] icons)
/* 581:    */  {
/* 582:582 */    return 0;
/* 583:    */  }
/* 584:    */  
/* 585:    */  public int getX() {
/* 586:586 */    return nGetX(this.window);
/* 587:    */  }
/* 588:    */  
/* 589:    */  public int getY() {
/* 590:590 */    return nGetY(this.window);
/* 591:    */  }
/* 592:    */  
/* 593:    */  public int getWidth() {
/* 594:594 */    return nGetWidth(this.window);
/* 595:    */  }
/* 596:    */  
/* 597:    */  public int getHeight() {
/* 598:598 */    return nGetHeight(this.window);
/* 599:    */  }
/* 600:    */  
/* 601:    */  public boolean isInsideWindow() {
/* 602:602 */    return this.mouseInsideWindow;
/* 603:    */  }
/* 604:    */  
/* 605:    */  public void setResizable(boolean resizable) {
/* 606:606 */    nSetResizable(this.window, resizable);
/* 607:    */  }
/* 608:    */  
/* 609:    */  public boolean wasResized() {
/* 610:610 */    return nWasResized(this.window);
/* 611:    */  }
/* 612:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.MacOSXDisplay
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */