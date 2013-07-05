/*    */ package com.google.code.tempusfugit.concurrency;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ThreadDump
/*    */ {
/*    */   public static void dumpThreads(PrintStream writer)
/*    */   {
/* 25 */     DeadlockDetector.printDeadlocks(writer);
/* 26 */     Map traces = Thread.getAllStackTraces();
/* 27 */     for (Thread thread : traces.keySet()) {
/* 28 */       writer.println(String.format("\nThread %s@%d: (state = %s)", new Object[] { thread.getName(), Long.valueOf(thread.getId()), thread.getState() }));
/* 29 */       for (StackTraceElement stackTraceElement : (StackTraceElement[])traces.get(thread))
/* 30 */         writer.println(" - " + stackTraceElement);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.concurrency.ThreadDump
 * JD-Core Version:    0.6.2
 */