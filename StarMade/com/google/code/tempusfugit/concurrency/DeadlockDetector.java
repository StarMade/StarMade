/*    */ package com.google.code.tempusfugit.concurrency;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.lang.management.ManagementFactory;
/*    */ import java.lang.management.ThreadInfo;
/*    */ import java.lang.management.ThreadMXBean;
/*    */ import java.util.Arrays;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ 
/*    */ public class DeadlockDetector
/*    */ {
/* 37 */   private static final ThreadMXBean mbean = ManagementFactory.getThreadMXBean();
/*    */ 
/*    */   public static void printDeadlocks(PrintStream writer) {
/* 40 */     List deadlocks = findDeadlocks();
/* 41 */     if (deadlocks.isEmpty())
/* 42 */       return;
/* 43 */     print(writer, deadlocks);
/*    */   }
/*    */ 
/*    */   private static void print(PrintStream writer, List<ThreadInfo> deadlocks) {
/* 47 */     writer.println("Deadlock detected\n=================\n");
/* 48 */     for (ThreadInfo thread : deadlocks) {
/* 49 */       writer.println(String.format("\"%s\":", new Object[] { thread.getThreadName() }));
/* 50 */       writer.println(String.format("  waiting to lock Monitor of %s ", new Object[] { thread.getLockName() }));
/* 51 */       writer.println(String.format("  which is held by \"%s\"", new Object[] { thread.getLockOwnerName() }));
/* 52 */       writer.println();
/*    */     }
/*    */   }
/*    */ 
/*    */   private static List<ThreadInfo> findDeadlocks()
/*    */   {
/*    */     long[] result;
/*    */     long[] result;
/* 58 */     if (mbean.isSynchronizerUsageSupported())
/* 59 */       result = mbean.findDeadlockedThreads();
/*    */     else
/* 61 */       result = mbean.findMonitorDeadlockedThreads();
/* 62 */     long[] monitorDeadlockedThreads = result;
/* 63 */     if (monitorDeadlockedThreads == null)
/* 64 */       return Collections.emptyList();
/* 65 */     return Arrays.asList(mbean.getThreadInfo(monitorDeadlockedThreads));
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.concurrency.DeadlockDetector
 * JD-Core Version:    0.6.2
 */