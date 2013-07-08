/*   1:    */package org.lwjgl.input;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.IntBuffer;
/*   5:    */import java.security.AccessController;
/*   6:    */import java.security.PrivilegedAction;
/*   7:    */import java.util.HashMap;
/*   8:    */import java.util.Map;
/*   9:    */import org.lwjgl.BufferUtils;
/*  10:    */import org.lwjgl.LWJGLException;
/*  11:    */import org.lwjgl.LWJGLUtil;
/*  12:    */import org.lwjgl.Sys;
/*  13:    */import org.lwjgl.opengl.Display;
/*  14:    */import org.lwjgl.opengl.InputImplementation;
/*  15:    */
/*  87:    */public class Mouse
/*  88:    */{
/*  89:    */  public static final int EVENT_SIZE = 22;
/*  90:    */  private static boolean created;
/*  91:    */  private static ByteBuffer buttons;
/*  92:    */  private static int x;
/*  93:    */  private static int y;
/*  94:    */  private static int absolute_x;
/*  95:    */  private static int absolute_y;
/*  96:    */  private static IntBuffer coord_buffer;
/*  97:    */  private static int dx;
/*  98:    */  private static int dy;
/*  99:    */  private static int dwheel;
/* 100:100 */  private static int buttonCount = -1;
/* 101:    */  
/* 103:    */  private static boolean hasWheel;
/* 104:    */  
/* 106:    */  private static Cursor currentCursor;
/* 107:    */  
/* 109:    */  private static String[] buttonName;
/* 110:    */  
/* 112:112 */  private static final Map<String, Integer> buttonMap = new HashMap(16);
/* 113:    */  
/* 115:    */  private static boolean initialized;
/* 116:    */  
/* 117:    */  private static ByteBuffer readBuffer;
/* 118:    */  
/* 119:    */  private static int eventButton;
/* 120:    */  
/* 121:    */  private static boolean eventState;
/* 122:    */  
/* 123:    */  private static int event_dx;
/* 124:    */  
/* 125:    */  private static int event_dy;
/* 126:    */  
/* 127:    */  private static int event_dwheel;
/* 128:    */  
/* 129:    */  private static int event_x;
/* 130:    */  
/* 131:    */  private static int event_y;
/* 132:    */  
/* 133:    */  private static long event_nanos;
/* 134:    */  
/* 135:    */  private static int grab_x;
/* 136:    */  
/* 137:    */  private static int grab_y;
/* 138:    */  
/* 139:    */  private static int last_event_raw_x;
/* 140:    */  
/* 141:    */  private static int last_event_raw_y;
/* 142:    */  
/* 143:    */  private static final int BUFFER_SIZE = 50;
/* 144:    */  
/* 145:    */  private static boolean isGrabbed;
/* 146:    */  
/* 147:    */  private static InputImplementation implementation;
/* 148:    */  
/* 149:149 */  private static final boolean emulateCursorAnimation = (LWJGLUtil.getPlatform() == 3) || (LWJGLUtil.getPlatform() == 2);
/* 150:    */  
/* 152:152 */  private static boolean clipMouseCoordinatesToWindow = !getPrivilegedBoolean("org.lwjgl.input.Mouse.allowNegativeMouseCoords");
/* 153:    */  
/* 164:    */  public static Cursor getNativeCursor()
/* 165:    */  {
/* 166:166 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 167:167 */      return currentCursor;
/* 168:    */    }
/* 169:    */  }
/* 170:    */  
/* 181:    */  public static Cursor setNativeCursor(Cursor cursor)
/* 182:    */    throws LWJGLException
/* 183:    */  {
/* 184:184 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 185:185 */      if ((Cursor.getCapabilities() & 0x1) == 0)
/* 186:186 */        throw new IllegalStateException("Mouse doesn't support native cursors");
/* 187:187 */      Cursor oldCursor = currentCursor;
/* 188:188 */      currentCursor = cursor;
/* 189:189 */      if (isCreated()) {
/* 190:190 */        if (currentCursor != null) {
/* 191:191 */          implementation.setNativeCursor(currentCursor.getHandle());
/* 192:192 */          currentCursor.setTimeout();
/* 193:    */        } else {
/* 194:194 */          implementation.setNativeCursor(null);
/* 195:    */        }
/* 196:    */      }
/* 197:197 */      return oldCursor;
/* 198:    */    }
/* 199:    */  }
/* 200:    */  
/* 201:    */  public static boolean isClipMouseCoordinatesToWindow() {
/* 202:202 */    return clipMouseCoordinatesToWindow;
/* 203:    */  }
/* 204:    */  
/* 205:    */  public static void setClipMouseCoordinatesToWindow(boolean clip) {
/* 206:206 */    clipMouseCoordinatesToWindow = clip;
/* 207:    */  }
/* 208:    */  
/* 217:    */  public static void setCursorPosition(int new_x, int new_y)
/* 218:    */  {
/* 219:219 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 220:220 */      if (!isCreated())
/* 221:221 */        throw new IllegalStateException("Mouse is not created");
/* 222:222 */      x = Mouse.event_x = new_x;
/* 223:223 */      y = Mouse.event_y = new_y;
/* 224:224 */      if ((!isGrabbed()) && ((Cursor.getCapabilities() & 0x1) != 0)) {
/* 225:225 */        implementation.setCursorPosition(x, y);
/* 226:    */      }
/* 227:    */      else {
/* 228:228 */        grab_x = new_x;
/* 229:229 */        grab_y = new_y;
/* 230:    */      }
/* 231:    */    }
/* 232:    */  }
/* 233:    */  
/* 236:    */  private static void initialize()
/* 237:    */  {
/* 238:238 */    Sys.initialize();
/* 239:    */    
/* 241:241 */    buttonName = new String[16];
/* 242:242 */    for (int i = 0; i < 16; i++) {
/* 243:243 */      buttonName[i] = ("BUTTON" + i);
/* 244:244 */      buttonMap.put(buttonName[i], Integer.valueOf(i));
/* 245:    */    }
/* 246:    */    
/* 247:247 */    initialized = true;
/* 248:    */  }
/* 249:    */  
/* 250:    */  private static void resetMouse() {
/* 251:251 */    dx = Mouse.dy = Mouse.dwheel = 0;
/* 252:252 */    readBuffer.position(readBuffer.limit());
/* 253:    */  }
/* 254:    */  
/* 255:    */  static InputImplementation getImplementation() {
/* 256:256 */    return implementation;
/* 257:    */  }
/* 258:    */  
/* 263:    */  private static void create(InputImplementation impl)
/* 264:    */    throws LWJGLException
/* 265:    */  {
/* 266:266 */    if (created)
/* 267:267 */      return;
/* 268:268 */    if (!initialized)
/* 269:269 */      initialize();
/* 270:270 */    implementation = impl;
/* 271:271 */    implementation.createMouse();
/* 272:272 */    hasWheel = implementation.hasWheel();
/* 273:273 */    created = true;
/* 274:    */    
/* 276:276 */    buttonCount = implementation.getButtonCount();
/* 277:277 */    buttons = BufferUtils.createByteBuffer(buttonCount);
/* 278:278 */    coord_buffer = BufferUtils.createIntBuffer(3);
/* 279:279 */    if ((currentCursor != null) && (implementation.getNativeCursorCapabilities() != 0))
/* 280:280 */      setNativeCursor(currentCursor);
/* 281:281 */    readBuffer = ByteBuffer.allocate(1100);
/* 282:282 */    readBuffer.limit(0);
/* 283:283 */    setGrabbed(isGrabbed);
/* 284:    */  }
/* 285:    */  
/* 291:    */  public static void create()
/* 292:    */    throws LWJGLException
/* 293:    */  {
/* 294:294 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 295:295 */      if (!Display.isCreated()) { throw new IllegalStateException("Display must be created.");
/* 296:    */      }
/* 297:297 */      create(OpenGLPackageAccess.createImplementation());
/* 298:    */    }
/* 299:    */  }
/* 300:    */  
/* 303:    */  public static boolean isCreated()
/* 304:    */  {
/* 305:305 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 306:306 */      return created;
/* 307:    */    }
/* 308:    */  }
/* 309:    */  
/* 312:    */  public static void destroy()
/* 313:    */  {
/* 314:314 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 315:315 */      if (!created) return;
/* 316:316 */      created = false;
/* 317:317 */      buttons = null;
/* 318:318 */      coord_buffer = null;
/* 319:    */      
/* 320:320 */      implementation.destroyMouse();
/* 321:    */    }
/* 322:    */  }
/* 323:    */  
/* 347:    */  public static void poll()
/* 348:    */  {
/* 349:349 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 350:350 */      if (!created) throw new IllegalStateException("Mouse must be created before you can poll it");
/* 351:351 */      implementation.pollMouse(coord_buffer, buttons);
/* 352:    */      
/* 354:354 */      int poll_coord1 = coord_buffer.get(0);
/* 355:355 */      int poll_coord2 = coord_buffer.get(1);
/* 356:    */      
/* 357:357 */      int poll_dwheel = coord_buffer.get(2);
/* 358:    */      
/* 359:359 */      if (isGrabbed()) {
/* 360:360 */        dx += poll_coord1;
/* 361:361 */        dy += poll_coord2;
/* 362:362 */        x += poll_coord1;
/* 363:363 */        y += poll_coord2;
/* 364:364 */        absolute_x += poll_coord1;
/* 365:365 */        absolute_y += poll_coord2;
/* 366:    */      } else {
/* 367:367 */        dx = poll_coord1 - absolute_x;
/* 368:368 */        dy = poll_coord2 - absolute_y;
/* 369:369 */        absolute_x = Mouse.x = poll_coord1;
/* 370:370 */        absolute_y = Mouse.y = poll_coord2;
/* 371:    */      }
/* 372:    */      
/* 373:373 */      if (clipMouseCoordinatesToWindow) {
/* 374:374 */        x = Math.min(Display.getWidth() - 1, Math.max(0, x));
/* 375:375 */        y = Math.min(Display.getHeight() - 1, Math.max(0, y));
/* 376:    */      }
/* 377:    */      
/* 378:378 */      dwheel += poll_dwheel;
/* 379:379 */      read();
/* 380:    */    }
/* 381:    */  }
/* 382:    */  
/* 383:    */  private static void read() {
/* 384:384 */    readBuffer.compact();
/* 385:385 */    implementation.readMouse(readBuffer);
/* 386:386 */    readBuffer.flip();
/* 387:    */  }
/* 388:    */  
/* 394:    */  public static boolean isButtonDown(int button)
/* 395:    */  {
/* 396:396 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 397:397 */      if (!created) throw new IllegalStateException("Mouse must be created before you can poll the button state");
/* 398:398 */      if ((button >= buttonCount) || (button < 0)) {
/* 399:399 */        return false;
/* 400:    */      }
/* 401:401 */      return buttons.get(button) == 1;
/* 402:    */    }
/* 403:    */  }
/* 404:    */  
/* 409:    */  public static String getButtonName(int button)
/* 410:    */  {
/* 411:411 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 412:412 */      if ((button >= buttonName.length) || (button < 0)) {
/* 413:413 */        return null;
/* 414:    */      }
/* 415:415 */      return buttonName[button];
/* 416:    */    }
/* 417:    */  }
/* 418:    */  
/* 422:    */  public static int getButtonIndex(String buttonName)
/* 423:    */  {
/* 424:424 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 425:425 */      Integer ret = (Integer)buttonMap.get(buttonName);
/* 426:426 */      if (ret == null) {
/* 427:427 */        return -1;
/* 428:    */      }
/* 429:429 */      return ret.intValue();
/* 430:    */    }
/* 431:    */  }
/* 432:    */  
/* 441:    */  public static boolean next()
/* 442:    */  {
/* 443:443 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 444:444 */      if (!created) throw new IllegalStateException("Mouse must be created before you can read events");
/* 445:445 */      if (readBuffer.hasRemaining()) {
/* 446:446 */        eventButton = readBuffer.get();
/* 447:447 */        eventState = readBuffer.get() != 0;
/* 448:448 */        if (isGrabbed()) {
/* 449:449 */          event_dx = readBuffer.getInt();
/* 450:450 */          event_dy = readBuffer.getInt();
/* 451:451 */          event_x += event_dx;
/* 452:452 */          event_y += event_dy;
/* 453:453 */          last_event_raw_x = event_x;
/* 454:454 */          last_event_raw_y = event_y;
/* 455:    */        } else {
/* 456:456 */          int new_event_x = readBuffer.getInt();
/* 457:457 */          int new_event_y = readBuffer.getInt();
/* 458:458 */          event_dx = new_event_x - last_event_raw_x;
/* 459:459 */          event_dy = new_event_y - last_event_raw_y;
/* 460:460 */          event_x = new_event_x;
/* 461:461 */          event_y = new_event_y;
/* 462:462 */          last_event_raw_x = new_event_x;
/* 463:463 */          last_event_raw_y = new_event_y;
/* 464:    */        }
/* 465:465 */        if (clipMouseCoordinatesToWindow) {
/* 466:466 */          event_x = Math.min(Display.getWidth() - 1, Math.max(0, event_x));
/* 467:467 */          event_y = Math.min(Display.getHeight() - 1, Math.max(0, event_y));
/* 468:    */        }
/* 469:469 */        event_dwheel = readBuffer.getInt();
/* 470:470 */        event_nanos = readBuffer.getLong();
/* 471:471 */        return true;
/* 472:    */      }
/* 473:473 */      return false;
/* 474:    */    }
/* 475:    */  }
/* 476:    */  
/* 479:    */  public static int getEventButton()
/* 480:    */  {
/* 481:481 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 482:482 */      return eventButton;
/* 483:    */    }
/* 484:    */  }
/* 485:    */  
/* 489:    */  public static boolean getEventButtonState()
/* 490:    */  {
/* 491:491 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 492:492 */      return eventState;
/* 493:    */    }
/* 494:    */  }
/* 495:    */  
/* 498:    */  public static int getEventDX()
/* 499:    */  {
/* 500:500 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 501:501 */      return event_dx;
/* 502:    */    }
/* 503:    */  }
/* 504:    */  
/* 507:    */  public static int getEventDY()
/* 508:    */  {
/* 509:509 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 510:510 */      return event_dy;
/* 511:    */    }
/* 512:    */  }
/* 513:    */  
/* 516:    */  public static int getEventX()
/* 517:    */  {
/* 518:518 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 519:519 */      return event_x;
/* 520:    */    }
/* 521:    */  }
/* 522:    */  
/* 525:    */  public static int getEventY()
/* 526:    */  {
/* 527:527 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 528:528 */      return event_y;
/* 529:    */    }
/* 530:    */  }
/* 531:    */  
/* 534:    */  public static int getEventDWheel()
/* 535:    */  {
/* 536:536 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 537:537 */      return event_dwheel;
/* 538:    */    }
/* 539:    */  }
/* 540:    */  
/* 548:    */  public static long getEventNanoseconds()
/* 549:    */  {
/* 550:550 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 551:551 */      return event_nanos;
/* 552:    */    }
/* 553:    */  }
/* 554:    */  
/* 560:    */  public static int getX()
/* 561:    */  {
/* 562:562 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 563:563 */      return x;
/* 564:    */    }
/* 565:    */  }
/* 566:    */  
/* 572:    */  public static int getY()
/* 573:    */  {
/* 574:574 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 575:575 */      return y;
/* 576:    */    }
/* 577:    */  }
/* 578:    */  
/* 581:    */  public static int getDX()
/* 582:    */  {
/* 583:583 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 584:584 */      int result = dx;
/* 585:585 */      dx = 0;
/* 586:586 */      return result;
/* 587:    */    }
/* 588:    */  }
/* 589:    */  
/* 592:    */  public static int getDY()
/* 593:    */  {
/* 594:594 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 595:595 */      int result = dy;
/* 596:596 */      dy = 0;
/* 597:597 */      return result;
/* 598:    */    }
/* 599:    */  }
/* 600:    */  
/* 603:    */  public static int getDWheel()
/* 604:    */  {
/* 605:605 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 606:606 */      int result = dwheel;
/* 607:607 */      dwheel = 0;
/* 608:608 */      return result;
/* 609:    */    }
/* 610:    */  }
/* 611:    */  
/* 614:    */  public static int getButtonCount()
/* 615:    */  {
/* 616:616 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 617:617 */      return buttonCount;
/* 618:    */    }
/* 619:    */  }
/* 620:    */  
/* 623:    */  public static boolean hasWheel()
/* 624:    */  {
/* 625:625 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 626:626 */      return hasWheel;
/* 627:    */    }
/* 628:    */  }
/* 629:    */  
/* 632:    */  public static boolean isGrabbed()
/* 633:    */  {
/* 634:634 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 635:635 */      return isGrabbed;
/* 636:    */    }
/* 637:    */  }
/* 638:    */  
/* 646:    */  public static void setGrabbed(boolean grab)
/* 647:    */  {
/* 648:648 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 649:649 */      boolean grabbed = isGrabbed;
/* 650:650 */      isGrabbed = grab;
/* 651:651 */      if (isCreated()) {
/* 652:652 */        if ((grab) && (!grabbed))
/* 653:    */        {
/* 654:654 */          grab_x = x;
/* 655:655 */          grab_y = y;
/* 656:    */        }
/* 657:657 */        else if ((!grab) && (grabbed))
/* 658:    */        {
/* 659:659 */          if ((Cursor.getCapabilities() & 0x1) != 0) {
/* 660:660 */            implementation.setCursorPosition(grab_x, grab_y);
/* 661:    */          }
/* 662:    */        }
/* 663:663 */        implementation.grabMouse(grab);
/* 664:    */        
/* 665:665 */        poll();
/* 666:666 */        event_x = x;
/* 667:667 */        event_y = y;
/* 668:668 */        last_event_raw_x = x;
/* 669:669 */        last_event_raw_y = y;
/* 670:670 */        resetMouse();
/* 671:    */      }
/* 672:    */    }
/* 673:    */  }
/* 674:    */  
/* 679:    */  public static void updateCursor()
/* 680:    */  {
/* 681:681 */    synchronized (OpenGLPackageAccess.global_lock) {
/* 682:682 */      if ((emulateCursorAnimation) && (currentCursor != null) && (currentCursor.hasTimedOut()) && (isInsideWindow())) {
/* 683:683 */        currentCursor.nextCursor();
/* 684:    */        try {
/* 685:685 */          setNativeCursor(currentCursor);
/* 686:    */        } catch (LWJGLException e) {
/* 687:687 */          if (LWJGLUtil.DEBUG) e.printStackTrace();
/* 688:    */        }
/* 689:    */      }
/* 690:    */    }
/* 691:    */  }
/* 692:    */  
/* 693:    */  static boolean getPrivilegedBoolean(String property_name)
/* 694:    */  {
/* 695:695 */    Boolean value = (Boolean)AccessController.doPrivileged(new PrivilegedAction() {
/* 696:    */      public Boolean run() {
/* 697:697 */        return Boolean.valueOf(Boolean.getBoolean(this.val$property_name));
/* 698:    */      }
/* 699:699 */    });
/* 700:700 */    return value.booleanValue();
/* 701:    */  }
/* 702:    */  
/* 708:    */  public static boolean isInsideWindow()
/* 709:    */  {
/* 710:710 */    return implementation.isInsideWindow();
/* 711:    */  }
/* 712:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.input.Mouse
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */