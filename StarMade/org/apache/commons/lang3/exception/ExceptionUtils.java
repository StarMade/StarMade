/*   1:    */package org.apache.commons.lang3.exception;
/*   2:    */
/*   3:    */import java.io.PrintStream;
/*   4:    */import java.io.PrintWriter;
/*   5:    */import java.io.StringWriter;
/*   6:    */import java.lang.reflect.InvocationTargetException;
/*   7:    */import java.lang.reflect.Method;
/*   8:    */import java.util.ArrayList;
/*   9:    */import java.util.List;
/*  10:    */import java.util.StringTokenizer;
/*  11:    */import org.apache.commons.lang3.ArrayUtils;
/*  12:    */import org.apache.commons.lang3.ClassUtils;
/*  13:    */import org.apache.commons.lang3.StringUtils;
/*  14:    */import org.apache.commons.lang3.SystemUtils;
/*  15:    */
/*  51:    */public class ExceptionUtils
/*  52:    */{
/*  53:    */  static final String WRAPPED_MARKER = " [wrapped] ";
/*  54: 54 */  private static final String[] CAUSE_METHOD_NAMES = { "getCause", "getNextException", "getTargetException", "getException", "getSourceException", "getRootCause", "getCausedByException", "getNested", "getLinkedException", "getNestedException", "getLinkedCause", "getThrowable" };
/*  55:    */  
/*  88:    */  @Deprecated
/*  89:    */  public static String[] getDefaultCauseMethodNames()
/*  90:    */  {
/*  91: 91 */    return (String[])ArrayUtils.clone(CAUSE_METHOD_NAMES);
/*  92:    */  }
/*  93:    */  
/* 121:    */  @Deprecated
/* 122:    */  public static Throwable getCause(Throwable throwable)
/* 123:    */  {
/* 124:124 */    return getCause(throwable, CAUSE_METHOD_NAMES);
/* 125:    */  }
/* 126:    */  
/* 139:    */  @Deprecated
/* 140:    */  public static Throwable getCause(Throwable throwable, String[] methodNames)
/* 141:    */  {
/* 142:142 */    if (throwable == null) {
/* 143:143 */      return null;
/* 144:    */    }
/* 145:    */    
/* 146:146 */    if (methodNames == null) {
/* 147:147 */      methodNames = CAUSE_METHOD_NAMES;
/* 148:    */    }
/* 149:    */    
/* 150:150 */    for (String methodName : methodNames) {
/* 151:151 */      if (methodName != null) {
/* 152:152 */        Throwable cause = getCauseUsingMethodName(throwable, methodName);
/* 153:153 */        if (cause != null) {
/* 154:154 */          return cause;
/* 155:    */        }
/* 156:    */      }
/* 157:    */    }
/* 158:    */    
/* 159:159 */    return null;
/* 160:    */  }
/* 161:    */  
/* 178:    */  public static Throwable getRootCause(Throwable throwable)
/* 179:    */  {
/* 180:180 */    List<Throwable> list = getThrowableList(throwable);
/* 181:181 */    return list.size() < 2 ? null : (Throwable)list.get(list.size() - 1);
/* 182:    */  }
/* 183:    */  
/* 191:    */  private static Throwable getCauseUsingMethodName(Throwable throwable, String methodName)
/* 192:    */  {
/* 193:193 */    Method method = null;
/* 194:    */    try {
/* 195:195 */      method = throwable.getClass().getMethod(methodName, new Class[0]);
/* 196:    */    }
/* 197:    */    catch (NoSuchMethodException ignored) {}catch (SecurityException ignored) {}
/* 198:    */    
/* 202:202 */    if ((method != null) && (Throwable.class.isAssignableFrom(method.getReturnType()))) {
/* 203:    */      try {
/* 204:204 */        return (Throwable)method.invoke(throwable, new Object[0]);
/* 205:    */      }
/* 206:    */      catch (IllegalAccessException ignored) {}catch (IllegalArgumentException ignored) {}catch (InvocationTargetException ignored) {}
/* 207:    */    }
/* 208:    */    
/* 213:213 */    return null;
/* 214:    */  }
/* 215:    */  
/* 232:    */  public static int getThrowableCount(Throwable throwable)
/* 233:    */  {
/* 234:234 */    return getThrowableList(throwable).size();
/* 235:    */  }
/* 236:    */  
/* 255:    */  public static Throwable[] getThrowables(Throwable throwable)
/* 256:    */  {
/* 257:257 */    List<Throwable> list = getThrowableList(throwable);
/* 258:258 */    return (Throwable[])list.toArray(new Throwable[list.size()]);
/* 259:    */  }
/* 260:    */  
/* 279:    */  public static List<Throwable> getThrowableList(Throwable throwable)
/* 280:    */  {
/* 281:281 */    List<Throwable> list = new ArrayList();
/* 282:282 */    while ((throwable != null) && (!list.contains(throwable))) {
/* 283:283 */      list.add(throwable);
/* 284:284 */      throwable = getCause(throwable);
/* 285:    */    }
/* 286:286 */    return list;
/* 287:    */  }
/* 288:    */  
/* 303:    */  public static int indexOfThrowable(Throwable throwable, Class<?> clazz)
/* 304:    */  {
/* 305:305 */    return indexOf(throwable, clazz, 0, false);
/* 306:    */  }
/* 307:    */  
/* 326:    */  public static int indexOfThrowable(Throwable throwable, Class<?> clazz, int fromIndex)
/* 327:    */  {
/* 328:328 */    return indexOf(throwable, clazz, fromIndex, false);
/* 329:    */  }
/* 330:    */  
/* 346:    */  public static int indexOfType(Throwable throwable, Class<?> type)
/* 347:    */  {
/* 348:348 */    return indexOf(throwable, type, 0, true);
/* 349:    */  }
/* 350:    */  
/* 370:    */  public static int indexOfType(Throwable throwable, Class<?> type, int fromIndex)
/* 371:    */  {
/* 372:372 */    return indexOf(throwable, type, fromIndex, true);
/* 373:    */  }
/* 374:    */  
/* 385:    */  private static int indexOf(Throwable throwable, Class<?> type, int fromIndex, boolean subclass)
/* 386:    */  {
/* 387:387 */    if ((throwable == null) || (type == null)) {
/* 388:388 */      return -1;
/* 389:    */    }
/* 390:390 */    if (fromIndex < 0) {
/* 391:391 */      fromIndex = 0;
/* 392:    */    }
/* 393:393 */    Throwable[] throwables = getThrowables(throwable);
/* 394:394 */    if (fromIndex >= throwables.length) {
/* 395:395 */      return -1;
/* 396:    */    }
/* 397:397 */    if (subclass) {
/* 398:398 */      for (int i = fromIndex; i < throwables.length; i++) {
/* 399:399 */        if (type.isAssignableFrom(throwables[i].getClass())) {
/* 400:400 */          return i;
/* 401:    */        }
/* 402:    */      }
/* 403:    */    } else {
/* 404:404 */      for (int i = fromIndex; i < throwables.length; i++) {
/* 405:405 */        if (type.equals(throwables[i].getClass())) {
/* 406:406 */          return i;
/* 407:    */        }
/* 408:    */      }
/* 409:    */    }
/* 410:410 */    return -1;
/* 411:    */  }
/* 412:    */  
/* 431:    */  public static void printRootCauseStackTrace(Throwable throwable)
/* 432:    */  {
/* 433:433 */    printRootCauseStackTrace(throwable, System.err);
/* 434:    */  }
/* 435:    */  
/* 454:    */  public static void printRootCauseStackTrace(Throwable throwable, PrintStream stream)
/* 455:    */  {
/* 456:456 */    if (throwable == null) {
/* 457:457 */      return;
/* 458:    */    }
/* 459:459 */    if (stream == null) {
/* 460:460 */      throw new IllegalArgumentException("The PrintStream must not be null");
/* 461:    */    }
/* 462:462 */    String[] trace = getRootCauseStackTrace(throwable);
/* 463:463 */    for (String element : trace) {
/* 464:464 */      stream.println(element);
/* 465:    */    }
/* 466:466 */    stream.flush();
/* 467:    */  }
/* 468:    */  
/* 487:    */  public static void printRootCauseStackTrace(Throwable throwable, PrintWriter writer)
/* 488:    */  {
/* 489:489 */    if (throwable == null) {
/* 490:490 */      return;
/* 491:    */    }
/* 492:492 */    if (writer == null) {
/* 493:493 */      throw new IllegalArgumentException("The PrintWriter must not be null");
/* 494:    */    }
/* 495:495 */    String[] trace = getRootCauseStackTrace(throwable);
/* 496:496 */    for (String element : trace) {
/* 497:497 */      writer.println(element);
/* 498:    */    }
/* 499:499 */    writer.flush();
/* 500:    */  }
/* 501:    */  
/* 515:    */  public static String[] getRootCauseStackTrace(Throwable throwable)
/* 516:    */  {
/* 517:517 */    if (throwable == null) {
/* 518:518 */      return ArrayUtils.EMPTY_STRING_ARRAY;
/* 519:    */    }
/* 520:520 */    Throwable[] throwables = getThrowables(throwable);
/* 521:521 */    int count = throwables.length;
/* 522:522 */    List<String> frames = new ArrayList();
/* 523:523 */    List<String> nextTrace = getStackFrameList(throwables[(count - 1)]);
/* 524:524 */    int i = count; for (;;) { i--; if (i < 0) break;
/* 525:525 */      List<String> trace = nextTrace;
/* 526:526 */      if (i != 0) {
/* 527:527 */        nextTrace = getStackFrameList(throwables[(i - 1)]);
/* 528:528 */        removeCommonFrames(trace, nextTrace);
/* 529:    */      }
/* 530:530 */      if (i == count - 1) {
/* 531:531 */        frames.add(throwables[i].toString());
/* 532:    */      } else {
/* 533:533 */        frames.add(" [wrapped] " + throwables[i].toString());
/* 534:    */      }
/* 535:535 */      for (int j = 0; j < trace.size(); j++) {
/* 536:536 */        frames.add(trace.get(j));
/* 537:    */      }
/* 538:    */    }
/* 539:539 */    return (String[])frames.toArray(new String[frames.size()]);
/* 540:    */  }
/* 541:    */  
/* 549:    */  public static void removeCommonFrames(List<String> causeFrames, List<String> wrapperFrames)
/* 550:    */  {
/* 551:551 */    if ((causeFrames == null) || (wrapperFrames == null)) {
/* 552:552 */      throw new IllegalArgumentException("The List must not be null");
/* 553:    */    }
/* 554:554 */    int causeFrameIndex = causeFrames.size() - 1;
/* 555:555 */    int wrapperFrameIndex = wrapperFrames.size() - 1;
/* 556:556 */    while ((causeFrameIndex >= 0) && (wrapperFrameIndex >= 0))
/* 557:    */    {
/* 559:559 */      String causeFrame = (String)causeFrames.get(causeFrameIndex);
/* 560:560 */      String wrapperFrame = (String)wrapperFrames.get(wrapperFrameIndex);
/* 561:561 */      if (causeFrame.equals(wrapperFrame)) {
/* 562:562 */        causeFrames.remove(causeFrameIndex);
/* 563:    */      }
/* 564:564 */      causeFrameIndex--;
/* 565:565 */      wrapperFrameIndex--;
/* 566:    */    }
/* 567:    */  }
/* 568:    */  
/* 581:    */  public static String getStackTrace(Throwable throwable)
/* 582:    */  {
/* 583:583 */    StringWriter sw = new StringWriter();
/* 584:584 */    PrintWriter pw = new PrintWriter(sw, true);
/* 585:585 */    throwable.printStackTrace(pw);
/* 586:586 */    return sw.getBuffer().toString();
/* 587:    */  }
/* 588:    */  
/* 601:    */  public static String[] getStackFrames(Throwable throwable)
/* 602:    */  {
/* 603:603 */    if (throwable == null) {
/* 604:604 */      return ArrayUtils.EMPTY_STRING_ARRAY;
/* 605:    */    }
/* 606:606 */    return getStackFrames(getStackTrace(throwable));
/* 607:    */  }
/* 608:    */  
/* 617:    */  static String[] getStackFrames(String stackTrace)
/* 618:    */  {
/* 619:619 */    String linebreak = SystemUtils.LINE_SEPARATOR;
/* 620:620 */    StringTokenizer frames = new StringTokenizer(stackTrace, linebreak);
/* 621:621 */    List<String> list = new ArrayList();
/* 622:622 */    while (frames.hasMoreTokens()) {
/* 623:623 */      list.add(frames.nextToken());
/* 624:    */    }
/* 625:625 */    return (String[])list.toArray(new String[list.size()]);
/* 626:    */  }
/* 627:    */  
/* 639:    */  static List<String> getStackFrameList(Throwable t)
/* 640:    */  {
/* 641:641 */    String stackTrace = getStackTrace(t);
/* 642:642 */    String linebreak = SystemUtils.LINE_SEPARATOR;
/* 643:643 */    StringTokenizer frames = new StringTokenizer(stackTrace, linebreak);
/* 644:644 */    List<String> list = new ArrayList();
/* 645:645 */    boolean traceStarted = false;
/* 646:646 */    while (frames.hasMoreTokens()) {
/* 647:647 */      String token = frames.nextToken();
/* 648:    */      
/* 649:649 */      int at = token.indexOf("at");
/* 650:650 */      if ((at != -1) && (token.substring(0, at).trim().length() == 0)) {
/* 651:651 */        traceStarted = true;
/* 652:652 */        list.add(token);
/* 653:653 */      } else { if (traceStarted)
/* 654:    */          break;
/* 655:    */      }
/* 656:    */    }
/* 657:657 */    return list;
/* 658:    */  }
/* 659:    */  
/* 670:    */  public static String getMessage(Throwable th)
/* 671:    */  {
/* 672:672 */    if (th == null) {
/* 673:673 */      return "";
/* 674:    */    }
/* 675:675 */    String clsName = ClassUtils.getShortClassName(th, null);
/* 676:676 */    String msg = th.getMessage();
/* 677:677 */    return clsName + ": " + StringUtils.defaultString(msg);
/* 678:    */  }
/* 679:    */  
/* 690:    */  public static String getRootCauseMessage(Throwable th)
/* 691:    */  {
/* 692:692 */    Throwable root = getRootCause(th);
/* 693:693 */    root = root == null ? th : root;
/* 694:694 */    return getMessage(root);
/* 695:    */  }
/* 696:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.exception.ExceptionUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */