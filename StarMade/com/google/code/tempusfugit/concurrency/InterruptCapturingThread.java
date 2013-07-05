/*    */ package com.google.code.tempusfugit.concurrency;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.concurrent.CopyOnWriteArrayList;
/*    */ 
/*    */ public class InterruptCapturingThread extends Thread
/*    */ {
/* 26 */   private final CopyOnWriteArrayList<StackTraceElement[]> interrupterStackTraces = new CopyOnWriteArrayList();
/*    */ 
/*    */   public InterruptCapturingThread() {
/*    */   }
/*    */ 
/*    */   public InterruptCapturingThread(Runnable runnable) {
/* 32 */     super(runnable);
/*    */   }
/*    */ 
/*    */   public InterruptCapturingThread(ThreadGroup threadGroup, Runnable runnable) {
/* 36 */     super(threadGroup, runnable);
/*    */   }
/*    */ 
/*    */   public InterruptCapturingThread(String name) {
/* 40 */     super(name);
/*    */   }
/*    */ 
/*    */   public InterruptCapturingThread(ThreadGroup threadGroup, String name) {
/* 44 */     super(threadGroup, name);
/*    */   }
/*    */ 
/*    */   public InterruptCapturingThread(Runnable runnable, String name) {
/* 48 */     super(runnable, name);
/*    */   }
/*    */ 
/*    */   public InterruptCapturingThread(ThreadGroup threadGroup, Runnable runnable, String name) {
/* 52 */     super(threadGroup, runnable, name);
/*    */   }
/*    */ 
/*    */   public InterruptCapturingThread(ThreadGroup threadGroup, Runnable runnable, String name, long stackSize) {
/* 56 */     super(threadGroup, runnable, name, stackSize);
/*    */   }
/*    */ 
/*    */   public void interrupt()
/*    */   {
/* 61 */     this.interrupterStackTraces.add(Thread.currentThread().getStackTrace());
/* 62 */     super.interrupt();
/*    */   }
/*    */ 
/*    */   public List<StackTraceElement[]> getInterrupters() {
/* 66 */     return new ArrayList(this.interrupterStackTraces);
/*    */   }
/*    */ 
/*    */   public void printStackTraceOfInterruptingThreads(PrintStream out) {
/* 70 */     for (StackTraceElement[] stackTraceElements : this.interrupterStackTraces) {
/* 71 */       for (StackTraceElement stackTraceElement : stackTraceElements) {
/* 72 */         out.print(stackTraceElement + "\n");
/*    */       }
/* 74 */       out.print("\n");
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.concurrency.InterruptCapturingThread
 * JD-Core Version:    0.6.2
 */