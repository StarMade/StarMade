/*     */ package org.apache.commons.lang3.exception;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.StringWriter;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.apache.commons.lang3.ArrayUtils;
/*     */ import org.apache.commons.lang3.ClassUtils;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.commons.lang3.SystemUtils;
/*     */ 
/*     */ public class ExceptionUtils
/*     */ {
/*     */   static final String WRAPPED_MARKER = " [wrapped] ";
/*  54 */   private static final String[] CAUSE_METHOD_NAMES = { "getCause", "getNextException", "getTargetException", "getException", "getSourceException", "getRootCause", "getCausedByException", "getNested", "getLinkedException", "getNestedException", "getLinkedCause", "getThrowable" };
/*     */ 
/*     */   @Deprecated
/*     */   public static String[] getDefaultCauseMethodNames()
/*     */   {
/*  91 */     return (String[])ArrayUtils.clone(CAUSE_METHOD_NAMES);
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public static Throwable getCause(Throwable throwable)
/*     */   {
/* 124 */     return getCause(throwable, CAUSE_METHOD_NAMES);
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public static Throwable getCause(Throwable throwable, String[] methodNames)
/*     */   {
/* 142 */     if (throwable == null) {
/* 143 */       return null;
/*     */     }
/*     */ 
/* 146 */     if (methodNames == null) {
/* 147 */       methodNames = CAUSE_METHOD_NAMES;
/*     */     }
/*     */ 
/* 150 */     for (String methodName : methodNames) {
/* 151 */       if (methodName != null) {
/* 152 */         Throwable cause = getCauseUsingMethodName(throwable, methodName);
/* 153 */         if (cause != null) {
/* 154 */           return cause;
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 159 */     return null;
/*     */   }
/*     */ 
/*     */   public static Throwable getRootCause(Throwable throwable)
/*     */   {
/* 180 */     List list = getThrowableList(throwable);
/* 181 */     return list.size() < 2 ? null : (Throwable)list.get(list.size() - 1);
/*     */   }
/*     */ 
/*     */   private static Throwable getCauseUsingMethodName(Throwable throwable, String methodName)
/*     */   {
/* 193 */     Method method = null;
/*     */     try {
/* 195 */       method = throwable.getClass().getMethod(methodName, new Class[0]);
/*     */     }
/*     */     catch (NoSuchMethodException ignored)
/*     */     {
/*     */     }
/*     */     catch (SecurityException ignored) {
/*     */     }
/* 202 */     if ((method != null) && (Throwable.class.isAssignableFrom(method.getReturnType())))
/*     */       try {
/* 204 */         return (Throwable)method.invoke(throwable, new Object[0]);
/*     */       }
/*     */       catch (IllegalAccessException ignored)
/*     */       {
/*     */       }
/*     */       catch (IllegalArgumentException ignored) {
/*     */       }
/*     */       catch (InvocationTargetException ignored) {
/*     */       }
/* 213 */     return null;
/*     */   }
/*     */ 
/*     */   public static int getThrowableCount(Throwable throwable)
/*     */   {
/* 234 */     return getThrowableList(throwable).size();
/*     */   }
/*     */ 
/*     */   public static Throwable[] getThrowables(Throwable throwable)
/*     */   {
/* 257 */     List list = getThrowableList(throwable);
/* 258 */     return (Throwable[])list.toArray(new Throwable[list.size()]);
/*     */   }
/*     */ 
/*     */   public static List<Throwable> getThrowableList(Throwable throwable)
/*     */   {
/* 281 */     List list = new ArrayList();
/* 282 */     while ((throwable != null) && (!list.contains(throwable))) {
/* 283 */       list.add(throwable);
/* 284 */       throwable = getCause(throwable);
/*     */     }
/* 286 */     return list;
/*     */   }
/*     */ 
/*     */   public static int indexOfThrowable(Throwable throwable, Class<?> clazz)
/*     */   {
/* 305 */     return indexOf(throwable, clazz, 0, false);
/*     */   }
/*     */ 
/*     */   public static int indexOfThrowable(Throwable throwable, Class<?> clazz, int fromIndex)
/*     */   {
/* 328 */     return indexOf(throwable, clazz, fromIndex, false);
/*     */   }
/*     */ 
/*     */   public static int indexOfType(Throwable throwable, Class<?> type)
/*     */   {
/* 348 */     return indexOf(throwable, type, 0, true);
/*     */   }
/*     */ 
/*     */   public static int indexOfType(Throwable throwable, Class<?> type, int fromIndex)
/*     */   {
/* 372 */     return indexOf(throwable, type, fromIndex, true);
/*     */   }
/*     */ 
/*     */   private static int indexOf(Throwable throwable, Class<?> type, int fromIndex, boolean subclass)
/*     */   {
/* 387 */     if ((throwable == null) || (type == null)) {
/* 388 */       return -1;
/*     */     }
/* 390 */     if (fromIndex < 0) {
/* 391 */       fromIndex = 0;
/*     */     }
/* 393 */     Throwable[] throwables = getThrowables(throwable);
/* 394 */     if (fromIndex >= throwables.length) {
/* 395 */       return -1;
/*     */     }
/* 397 */     if (subclass) {
/* 398 */       for (int i = fromIndex; i < throwables.length; i++) {
/* 399 */         if (type.isAssignableFrom(throwables[i].getClass()))
/* 400 */           return i;
/*     */       }
/*     */     }
/*     */     else {
/* 404 */       for (int i = fromIndex; i < throwables.length; i++) {
/* 405 */         if (type.equals(throwables[i].getClass())) {
/* 406 */           return i;
/*     */         }
/*     */       }
/*     */     }
/* 410 */     return -1;
/*     */   }
/*     */ 
/*     */   public static void printRootCauseStackTrace(Throwable throwable)
/*     */   {
/* 433 */     printRootCauseStackTrace(throwable, System.err);
/*     */   }
/*     */ 
/*     */   public static void printRootCauseStackTrace(Throwable throwable, PrintStream stream)
/*     */   {
/* 456 */     if (throwable == null) {
/* 457 */       return;
/*     */     }
/* 459 */     if (stream == null) {
/* 460 */       throw new IllegalArgumentException("The PrintStream must not be null");
/*     */     }
/* 462 */     String[] trace = getRootCauseStackTrace(throwable);
/* 463 */     for (String element : trace) {
/* 464 */       stream.println(element);
/*     */     }
/* 466 */     stream.flush();
/*     */   }
/*     */ 
/*     */   public static void printRootCauseStackTrace(Throwable throwable, PrintWriter writer)
/*     */   {
/* 489 */     if (throwable == null) {
/* 490 */       return;
/*     */     }
/* 492 */     if (writer == null) {
/* 493 */       throw new IllegalArgumentException("The PrintWriter must not be null");
/*     */     }
/* 495 */     String[] trace = getRootCauseStackTrace(throwable);
/* 496 */     for (String element : trace) {
/* 497 */       writer.println(element);
/*     */     }
/* 499 */     writer.flush();
/*     */   }
/*     */ 
/*     */   public static String[] getRootCauseStackTrace(Throwable throwable)
/*     */   {
/* 517 */     if (throwable == null) {
/* 518 */       return ArrayUtils.EMPTY_STRING_ARRAY;
/*     */     }
/* 520 */     Throwable[] throwables = getThrowables(throwable);
/* 521 */     int count = throwables.length;
/* 522 */     List frames = new ArrayList();
/* 523 */     List nextTrace = getStackFrameList(throwables[(count - 1)]);
/* 524 */     int i = count;
/*     */     while (true) { i--; if (i < 0) break;
/* 525 */       List trace = nextTrace;
/* 526 */       if (i != 0) {
/* 527 */         nextTrace = getStackFrameList(throwables[(i - 1)]);
/* 528 */         removeCommonFrames(trace, nextTrace);
/*     */       }
/* 530 */       if (i == count - 1)
/* 531 */         frames.add(throwables[i].toString());
/*     */       else {
/* 533 */         frames.add(" [wrapped] " + throwables[i].toString());
/*     */       }
/* 535 */       for (int j = 0; j < trace.size(); j++) {
/* 536 */         frames.add(trace.get(j));
/*     */       }
/*     */     }
/* 539 */     return (String[])frames.toArray(new String[frames.size()]);
/*     */   }
/*     */ 
/*     */   public static void removeCommonFrames(List<String> causeFrames, List<String> wrapperFrames)
/*     */   {
/* 551 */     if ((causeFrames == null) || (wrapperFrames == null)) {
/* 552 */       throw new IllegalArgumentException("The List must not be null");
/*     */     }
/* 554 */     int causeFrameIndex = causeFrames.size() - 1;
/* 555 */     int wrapperFrameIndex = wrapperFrames.size() - 1;
/* 556 */     while ((causeFrameIndex >= 0) && (wrapperFrameIndex >= 0))
/*     */     {
/* 559 */       String causeFrame = (String)causeFrames.get(causeFrameIndex);
/* 560 */       String wrapperFrame = (String)wrapperFrames.get(wrapperFrameIndex);
/* 561 */       if (causeFrame.equals(wrapperFrame)) {
/* 562 */         causeFrames.remove(causeFrameIndex);
/*     */       }
/* 564 */       causeFrameIndex--;
/* 565 */       wrapperFrameIndex--;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static String getStackTrace(Throwable throwable)
/*     */   {
/* 583 */     StringWriter sw = new StringWriter();
/* 584 */     PrintWriter pw = new PrintWriter(sw, true);
/* 585 */     throwable.printStackTrace(pw);
/* 586 */     return sw.getBuffer().toString();
/*     */   }
/*     */ 
/*     */   public static String[] getStackFrames(Throwable throwable)
/*     */   {
/* 603 */     if (throwable == null) {
/* 604 */       return ArrayUtils.EMPTY_STRING_ARRAY;
/*     */     }
/* 606 */     return getStackFrames(getStackTrace(throwable));
/*     */   }
/*     */ 
/*     */   static String[] getStackFrames(String stackTrace)
/*     */   {
/* 619 */     String linebreak = SystemUtils.LINE_SEPARATOR;
/* 620 */     StringTokenizer frames = new StringTokenizer(stackTrace, linebreak);
/* 621 */     List list = new ArrayList();
/* 622 */     while (frames.hasMoreTokens()) {
/* 623 */       list.add(frames.nextToken());
/*     */     }
/* 625 */     return (String[])list.toArray(new String[list.size()]);
/*     */   }
/*     */ 
/*     */   static List<String> getStackFrameList(Throwable t)
/*     */   {
/* 641 */     String stackTrace = getStackTrace(t);
/* 642 */     String linebreak = SystemUtils.LINE_SEPARATOR;
/* 643 */     StringTokenizer frames = new StringTokenizer(stackTrace, linebreak);
/* 644 */     List list = new ArrayList();
/* 645 */     boolean traceStarted = false;
/* 646 */     while (frames.hasMoreTokens()) {
/* 647 */       String token = frames.nextToken();
/*     */ 
/* 649 */       int at = token.indexOf("at");
/* 650 */       if ((at != -1) && (token.substring(0, at).trim().length() == 0)) {
/* 651 */         traceStarted = true;
/* 652 */         list.add(token); } else {
/* 653 */         if (traceStarted)
/*     */           break;
/*     */       }
/*     */     }
/* 657 */     return list;
/*     */   }
/*     */ 
/*     */   public static String getMessage(Throwable th)
/*     */   {
/* 672 */     if (th == null) {
/* 673 */       return "";
/*     */     }
/* 675 */     String clsName = ClassUtils.getShortClassName(th, null);
/* 676 */     String msg = th.getMessage();
/* 677 */     return clsName + ": " + StringUtils.defaultString(msg);
/*     */   }
/*     */ 
/*     */   public static String getRootCauseMessage(Throwable th)
/*     */   {
/* 692 */     Throwable root = getRootCause(th);
/* 693 */     root = root == null ? th : root;
/* 694 */     return getMessage(root);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.exception.ExceptionUtils
 * JD-Core Version:    0.6.2
 */