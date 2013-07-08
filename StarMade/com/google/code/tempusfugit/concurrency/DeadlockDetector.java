/*  1:   */package com.google.code.tempusfugit.concurrency;
/*  2:   */
/*  3:   */import java.io.PrintStream;
/*  4:   */import java.lang.management.ManagementFactory;
/*  5:   */import java.lang.management.ThreadInfo;
/*  6:   */import java.lang.management.ThreadMXBean;
/*  7:   */import java.util.Arrays;
/*  8:   */import java.util.Collections;
/*  9:   */import java.util.List;
/* 10:   */
/* 35:   */public class DeadlockDetector
/* 36:   */{
/* 37:37 */  private static final ThreadMXBean mbean = ;
/* 38:   */  
/* 39:   */  public static void printDeadlocks(PrintStream writer) {
/* 40:40 */    List<ThreadInfo> deadlocks = findDeadlocks();
/* 41:41 */    if (deadlocks.isEmpty())
/* 42:42 */      return;
/* 43:43 */    print(writer, deadlocks);
/* 44:   */  }
/* 45:   */  
/* 46:   */  private static void print(PrintStream writer, List<ThreadInfo> deadlocks) {
/* 47:47 */    writer.println("Deadlock detected\n=================\n");
/* 48:48 */    for (ThreadInfo thread : deadlocks) {
/* 49:49 */      writer.println(String.format("\"%s\":", new Object[] { thread.getThreadName() }));
/* 50:50 */      writer.println(String.format("  waiting to lock Monitor of %s ", new Object[] { thread.getLockName() }));
/* 51:51 */      writer.println(String.format("  which is held by \"%s\"", new Object[] { thread.getLockOwnerName() }));
/* 52:52 */      writer.println();
/* 53:   */    }
/* 54:   */  }
/* 55:   */  
/* 56:   */  private static List<ThreadInfo> findDeadlocks() { long[] result;
/* 57:   */    long[] result;
/* 58:58 */    if (mbean.isSynchronizerUsageSupported()) {
/* 59:59 */      result = mbean.findDeadlockedThreads();
/* 60:   */    } else
/* 61:61 */      result = mbean.findMonitorDeadlockedThreads();
/* 62:62 */    long[] monitorDeadlockedThreads = result;
/* 63:63 */    if (monitorDeadlockedThreads == null)
/* 64:64 */      return Collections.emptyList();
/* 65:65 */    return Arrays.asList(mbean.getThreadInfo(monitorDeadlockedThreads));
/* 66:   */  }
/* 67:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.concurrency.DeadlockDetector
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */