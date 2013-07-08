/*  1:   */package com.google.code.tempusfugit.concurrency;
/*  2:   */
/*  3:   */import java.io.PrintStream;
/*  4:   */import java.util.Map;
/*  5:   */
/* 21:   */public class ThreadDump
/* 22:   */{
/* 23:   */  public static void dumpThreads(PrintStream writer)
/* 24:   */  {
/* 25:25 */    DeadlockDetector.printDeadlocks(writer);
/* 26:26 */    Map<Thread, StackTraceElement[]> traces = Thread.getAllStackTraces();
/* 27:27 */    for (Thread thread : traces.keySet()) {
/* 28:28 */      writer.println(String.format("\nThread %s@%d: (state = %s)", new Object[] { thread.getName(), Long.valueOf(thread.getId()), thread.getState() }));
/* 29:29 */      for (StackTraceElement stackTraceElement : (StackTraceElement[])traces.get(thread)) {
/* 30:30 */        writer.println(" - " + stackTraceElement);
/* 31:   */      }
/* 32:   */    }
/* 33:   */  }
/* 34:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.concurrency.ThreadDump
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */