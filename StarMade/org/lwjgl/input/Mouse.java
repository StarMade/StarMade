/*     */ package org.lwjgl.input;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.LWJGLException;
/*     */ import org.lwjgl.LWJGLUtil;
/*     */ import org.lwjgl.Sys;
/*     */ import org.lwjgl.opengl.Display;
/*     */ import org.lwjgl.opengl.InputImplementation;
/*     */ 
/*     */ public class Mouse
/*     */ {
/*     */   public static final int EVENT_SIZE = 22;
/*     */   private static boolean created;
/*     */   private static ByteBuffer buttons;
/*     */   private static int x;
/*     */   private static int y;
/*     */   private static int absolute_x;
/*     */   private static int absolute_y;
/*     */   private static IntBuffer coord_buffer;
/*     */   private static int dx;
/*     */   private static int dy;
/*     */   private static int dwheel;
/* 100 */   private static int buttonCount = -1;
/*     */   private static boolean hasWheel;
/*     */   private static Cursor currentCursor;
/*     */   private static String[] buttonName;
/* 112 */   private static final Map<String, Integer> buttonMap = new HashMap(16);
/*     */   private static boolean initialized;
/*     */   private static ByteBuffer readBuffer;
/*     */   private static int eventButton;
/*     */   private static boolean eventState;
/*     */   private static int event_dx;
/*     */   private static int event_dy;
/*     */   private static int event_dwheel;
/*     */   private static int event_x;
/*     */   private static int event_y;
/*     */   private static long event_nanos;
/*     */   private static int grab_x;
/*     */   private static int grab_y;
/*     */   private static int last_event_raw_x;
/*     */   private static int last_event_raw_y;
/*     */   private static final int BUFFER_SIZE = 50;
/*     */   private static boolean isGrabbed;
/*     */   private static InputImplementation implementation;
/* 149 */   private static final boolean emulateCursorAnimation = (LWJGLUtil.getPlatform() == 3) || (LWJGLUtil.getPlatform() == 2);
/*     */ 
/* 152 */   private static boolean clipMouseCoordinatesToWindow = !getPrivilegedBoolean("org.lwjgl.input.Mouse.allowNegativeMouseCoords");
/*     */ 
/*     */   public static Cursor getNativeCursor()
/*     */   {
/* 166 */     synchronized (OpenGLPackageAccess.global_lock) {
/* 167 */       return currentCursor;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static Cursor setNativeCursor(Cursor cursor)
/*     */     throws LWJGLException
/*     */   {
/* 184 */     synchronized (OpenGLPackageAccess.global_lock) {
/* 185 */       if ((Cursor.getCapabilities() & 0x1) == 0)
/* 186 */         throw new IllegalStateException("Mouse doesn't support native cursors");
/* 187 */       Cursor oldCursor = currentCursor;
/* 188 */       currentCursor = cursor;
/* 189 */       if (isCreated()) {
/* 190 */         if (currentCursor != null) {
/* 191 */           implementation.setNativeCursor(currentCursor.getHandle());
/* 192 */           currentCursor.setTimeout();
/*     */         } else {
/* 194 */           implementation.setNativeCursor(null);
/*     */         }
/*     */       }
/* 197 */       return oldCursor;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean isClipMouseCoordinatesToWindow() {
/* 202 */     return clipMouseCoordinatesToWindow;
/*     */   }
/*     */ 
/*     */   public static void setClipMouseCoordinatesToWindow(boolean clip) {
/* 206 */     clipMouseCoordinatesToWindow = clip;
/*     */   }
/*     */ 
/*     */   public static void setCursorPosition(int new_x, int new_y)
/*     */   {
/* 219 */     synchronized (OpenGLPackageAccess.global_lock) {
/* 220 */       if (!isCreated())
/* 221 */         throw new IllegalStateException("Mouse is not created");
/* 222 */       x = Mouse.event_x = new_x;
/* 223 */       y = Mouse.event_y = new_y;
/* 224 */       if ((!isGrabbed()) && ((Cursor.getCapabilities() & 0x1) != 0)) {
/* 225 */         implementation.setCursorPosition(x, y);
/*     */       }
/*     */       else {
/* 228 */         grab_x = new_x;
/* 229 */         grab_y = new_y;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private static void initialize()
/*     */   {
/* 238 */     Sys.initialize();
/*     */ 
/* 241 */     buttonName = new String[16];
/* 242 */     for (int i = 0; i < 16; i++) {
/* 243 */       buttonName[i] = ("BUTTON" + i);
/* 244 */       buttonMap.put(buttonName[i], Integer.valueOf(i));
/*     */     }
/*     */ 
/* 247 */     initialized = true;
/*     */   }
/*     */ 
/*     */   private static void resetMouse() {
/* 251 */     dx = Mouse.dy = Mouse.dwheel = 0;
/* 252 */     readBuffer.position(readBuffer.limit());
/*     */   }
/*     */ 
/*     */   static InputImplementation getImplementation() {
/* 256 */     return implementation;
/*     */   }
/*     */ 
/*     */   private static void create(InputImplementation impl)
/*     */     throws LWJGLException
/*     */   {
/* 266 */     if (created)
/* 267 */       return;
/* 268 */     if (!initialized)
/* 269 */       initialize();
/* 270 */     implementation = impl;
/* 271 */     implementation.createMouse();
/* 272 */     hasWheel = implementation.hasWheel();
/* 273 */     created = true;
/*     */ 
/* 276 */     buttonCount = implementation.getButtonCount();
/* 277 */     buttons = BufferUtils.createByteBuffer(buttonCount);
/* 278 */     coord_buffer = BufferUtils.createIntBuffer(3);
/* 279 */     if ((currentCursor != null) && (implementation.getNativeCursorCapabilities() != 0))
/* 280 */       setNativeCursor(currentCursor);
/* 281 */     readBuffer = ByteBuffer.allocate(1100);
/* 282 */     readBuffer.limit(0);
/* 283 */     setGrabbed(isGrabbed);
/*     */   }
/*     */ 
/*     */   public static void create()
/*     */     throws LWJGLException
/*     */   {
/* 294 */     synchronized (OpenGLPackageAccess.global_lock) {
/* 295 */       if (!Display.isCreated()) throw new IllegalStateException("Display must be created.");
/*     */ 
/* 297 */       create(OpenGLPackageAccess.createImplementation());
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean isCreated()
/*     */   {
/* 305 */     synchronized (OpenGLPackageAccess.global_lock) {
/* 306 */       return created;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void destroy()
/*     */   {
/* 314 */     synchronized (OpenGLPackageAccess.global_lock) {
/* 315 */       if (!created) return;
/* 316 */       created = false;
/* 317 */       buttons = null;
/* 318 */       coord_buffer = null;
/*     */ 
/* 320 */       implementation.destroyMouse();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void poll()
/*     */   {
/* 349 */     synchronized (OpenGLPackageAccess.global_lock) {
/* 350 */       if (!created) throw new IllegalStateException("Mouse must be created before you can poll it");
/* 351 */       implementation.pollMouse(coord_buffer, buttons);
/*     */ 
/* 354 */       int poll_coord1 = coord_buffer.get(0);
/* 355 */       int poll_coord2 = coord_buffer.get(1);
/*     */ 
/* 357 */       int poll_dwheel = coord_buffer.get(2);
/*     */ 
/* 359 */       if (isGrabbed()) {
/* 360 */         dx += poll_coord1;
/* 361 */         dy += poll_coord2;
/* 362 */         x += poll_coord1;
/* 363 */         y += poll_coord2;
/* 364 */         absolute_x += poll_coord1;
/* 365 */         absolute_y += poll_coord2;
/*     */       } else {
/* 367 */         dx = poll_coord1 - absolute_x;
/* 368 */         dy = poll_coord2 - absolute_y;
/* 369 */         absolute_x = Mouse.x = poll_coord1;
/* 370 */         absolute_y = Mouse.y = poll_coord2;
/*     */       }
/*     */ 
/* 373 */       if (clipMouseCoordinatesToWindow) {
/* 374 */         x = Math.min(Display.getWidth() - 1, Math.max(0, x));
/* 375 */         y = Math.min(Display.getHeight() - 1, Math.max(0, y));
/*     */       }
/*     */ 
/* 378 */       dwheel += poll_dwheel;
/* 379 */       read();
/*     */     }
/*     */   }
/*     */ 
/*     */   private static void read() {
/* 384 */     readBuffer.compact();
/* 385 */     implementation.readMouse(readBuffer);
/* 386 */     readBuffer.flip();
/*     */   }
/*     */ 
/*     */   public static boolean isButtonDown(int button)
/*     */   {
/* 396 */     synchronized (OpenGLPackageAccess.global_lock) {
/* 397 */       if (!created) throw new IllegalStateException("Mouse must be created before you can poll the button state");
/* 398 */       if ((button >= buttonCount) || (button < 0)) {
/* 399 */         return false;
/*     */       }
/* 401 */       return buttons.get(button) == 1;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static String getButtonName(int button)
/*     */   {
/* 411 */     synchronized (OpenGLPackageAccess.global_lock) {
/* 412 */       if ((button >= buttonName.length) || (button < 0)) {
/* 413 */         return null;
/*     */       }
/* 415 */       return buttonName[button];
/*     */     }
/*     */   }
/*     */ 
/*     */   public static int getButtonIndex(String buttonName)
/*     */   {
/* 424 */     synchronized (OpenGLPackageAccess.global_lock) {
/* 425 */       Integer ret = (Integer)buttonMap.get(buttonName);
/* 426 */       if (ret == null) {
/* 427 */         return -1;
/*     */       }
/* 429 */       return ret.intValue();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean next()
/*     */   {
/* 443 */     synchronized (OpenGLPackageAccess.global_lock) {
/* 444 */       if (!created) throw new IllegalStateException("Mouse must be created before you can read events");
/* 445 */       if (readBuffer.hasRemaining()) {
/* 446 */         eventButton = readBuffer.get();
/* 447 */         eventState = readBuffer.get() != 0;
/* 448 */         if (isGrabbed()) {
/* 449 */           event_dx = readBuffer.getInt();
/* 450 */           event_dy = readBuffer.getInt();
/* 451 */           event_x += event_dx;
/* 452 */           event_y += event_dy;
/* 453 */           last_event_raw_x = event_x;
/* 454 */           last_event_raw_y = event_y;
/*     */         } else {
/* 456 */           int new_event_x = readBuffer.getInt();
/* 457 */           int new_event_y = readBuffer.getInt();
/* 458 */           event_dx = new_event_x - last_event_raw_x;
/* 459 */           event_dy = new_event_y - last_event_raw_y;
/* 460 */           event_x = new_event_x;
/* 461 */           event_y = new_event_y;
/* 462 */           last_event_raw_x = new_event_x;
/* 463 */           last_event_raw_y = new_event_y;
/*     */         }
/* 465 */         if (clipMouseCoordinatesToWindow) {
/* 466 */           event_x = Math.min(Display.getWidth() - 1, Math.max(0, event_x));
/* 467 */           event_y = Math.min(Display.getHeight() - 1, Math.max(0, event_y));
/*     */         }
/* 469 */         event_dwheel = readBuffer.getInt();
/* 470 */         event_nanos = readBuffer.getLong();
/* 471 */         return true;
/*     */       }
/* 473 */       return false;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static int getEventButton()
/*     */   {
/* 481 */     synchronized (OpenGLPackageAccess.global_lock) {
/* 482 */       return eventButton;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean getEventButtonState()
/*     */   {
/* 491 */     synchronized (OpenGLPackageAccess.global_lock) {
/* 492 */       return eventState;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static int getEventDX()
/*     */   {
/* 500 */     synchronized (OpenGLPackageAccess.global_lock) {
/* 501 */       return event_dx;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static int getEventDY()
/*     */   {
/* 509 */     synchronized (OpenGLPackageAccess.global_lock) {
/* 510 */       return event_dy;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static int getEventX()
/*     */   {
/* 518 */     synchronized (OpenGLPackageAccess.global_lock) {
/* 519 */       return event_x;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static int getEventY()
/*     */   {
/* 527 */     synchronized (OpenGLPackageAccess.global_lock) {
/* 528 */       return event_y;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static int getEventDWheel()
/*     */   {
/* 536 */     synchronized (OpenGLPackageAccess.global_lock) {
/* 537 */       return event_dwheel;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static long getEventNanoseconds()
/*     */   {
/* 550 */     synchronized (OpenGLPackageAccess.global_lock) {
/* 551 */       return event_nanos;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static int getX()
/*     */   {
/* 562 */     synchronized (OpenGLPackageAccess.global_lock) {
/* 563 */       return x;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static int getY()
/*     */   {
/* 574 */     synchronized (OpenGLPackageAccess.global_lock) {
/* 575 */       return y;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static int getDX()
/*     */   {
/* 583 */     synchronized (OpenGLPackageAccess.global_lock) {
/* 584 */       int result = dx;
/* 585 */       dx = 0;
/* 586 */       return result;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static int getDY()
/*     */   {
/* 594 */     synchronized (OpenGLPackageAccess.global_lock) {
/* 595 */       int result = dy;
/* 596 */       dy = 0;
/* 597 */       return result;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static int getDWheel()
/*     */   {
/* 605 */     synchronized (OpenGLPackageAccess.global_lock) {
/* 606 */       int result = dwheel;
/* 607 */       dwheel = 0;
/* 608 */       return result;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static int getButtonCount()
/*     */   {
/* 616 */     synchronized (OpenGLPackageAccess.global_lock) {
/* 617 */       return buttonCount;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean hasWheel()
/*     */   {
/* 625 */     synchronized (OpenGLPackageAccess.global_lock) {
/* 626 */       return hasWheel;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean isGrabbed()
/*     */   {
/* 634 */     synchronized (OpenGLPackageAccess.global_lock) {
/* 635 */       return isGrabbed;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void setGrabbed(boolean grab)
/*     */   {
/* 648 */     synchronized (OpenGLPackageAccess.global_lock) {
/* 649 */       boolean grabbed = isGrabbed;
/* 650 */       isGrabbed = grab;
/* 651 */       if (isCreated()) {
/* 652 */         if ((grab) && (!grabbed))
/*     */         {
/* 654 */           grab_x = x;
/* 655 */           grab_y = y;
/*     */         }
/* 657 */         else if ((!grab) && (grabbed))
/*     */         {
/* 659 */           if ((Cursor.getCapabilities() & 0x1) != 0) {
/* 660 */             implementation.setCursorPosition(grab_x, grab_y);
/*     */           }
/*     */         }
/* 663 */         implementation.grabMouse(grab);
/*     */ 
/* 665 */         poll();
/* 666 */         event_x = x;
/* 667 */         event_y = y;
/* 668 */         last_event_raw_x = x;
/* 669 */         last_event_raw_y = y;
/* 670 */         resetMouse();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void updateCursor()
/*     */   {
/* 681 */     synchronized (OpenGLPackageAccess.global_lock) {
/* 682 */       if ((emulateCursorAnimation) && (currentCursor != null) && (currentCursor.hasTimedOut()) && (isInsideWindow())) {
/* 683 */         currentCursor.nextCursor();
/*     */         try {
/* 685 */           setNativeCursor(currentCursor);
/*     */         } catch (LWJGLException e) {
/* 687 */           if (LWJGLUtil.DEBUG) e.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   static boolean getPrivilegedBoolean(String property_name)
/*     */   {
/* 695 */     Boolean value = (Boolean)AccessController.doPrivileged(new PrivilegedAction() {
/*     */       public Boolean run() {
/* 697 */         return Boolean.valueOf(Boolean.getBoolean(this.val$property_name));
/*     */       }
/*     */     });
/* 700 */     return value.booleanValue();
/*     */   }
/*     */ 
/*     */   public static boolean isInsideWindow()
/*     */   {
/* 710 */     return implementation.isInsideWindow();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.input.Mouse
 * JD-Core Version:    0.6.2
 */