/*  1:   */package com.google.code.tempusfugit.concurrency;
/*  2:   */
/*  3:   */import java.io.PrintStream;
/*  4:   */import java.util.ArrayList;
/*  5:   */import java.util.List;
/*  6:   */import java.util.concurrent.CopyOnWriteArrayList;
/*  7:   */
/* 23:   */public class InterruptCapturingThread
/* 24:   */  extends Thread
/* 25:   */{
/* 26:26 */  private final CopyOnWriteArrayList<StackTraceElement[]> interrupterStackTraces = new CopyOnWriteArrayList();
/* 27:   */  
/* 28:   */  public InterruptCapturingThread() {}
/* 29:   */  
/* 30:   */  public InterruptCapturingThread(Runnable runnable)
/* 31:   */  {
/* 32:32 */    super(runnable);
/* 33:   */  }
/* 34:   */  
/* 35:   */  public InterruptCapturingThread(ThreadGroup threadGroup, Runnable runnable) {
/* 36:36 */    super(threadGroup, runnable);
/* 37:   */  }
/* 38:   */  
/* 39:   */  public InterruptCapturingThread(String name) {
/* 40:40 */    super(name);
/* 41:   */  }
/* 42:   */  
/* 43:   */  public InterruptCapturingThread(ThreadGroup threadGroup, String name) {
/* 44:44 */    super(threadGroup, name);
/* 45:   */  }
/* 46:   */  
/* 47:   */  public InterruptCapturingThread(Runnable runnable, String name) {
/* 48:48 */    super(runnable, name);
/* 49:   */  }
/* 50:   */  
/* 51:   */  public InterruptCapturingThread(ThreadGroup threadGroup, Runnable runnable, String name) {
/* 52:52 */    super(threadGroup, runnable, name);
/* 53:   */  }
/* 54:   */  
/* 55:   */  public InterruptCapturingThread(ThreadGroup threadGroup, Runnable runnable, String name, long stackSize) {
/* 56:56 */    super(threadGroup, runnable, name, stackSize);
/* 57:   */  }
/* 58:   */  
/* 59:   */  public void interrupt()
/* 60:   */  {
/* 61:61 */    this.interrupterStackTraces.add(Thread.currentThread().getStackTrace());
/* 62:62 */    super.interrupt();
/* 63:   */  }
/* 64:   */  
/* 65:   */  public List<StackTraceElement[]> getInterrupters() {
/* 66:66 */    return new ArrayList(this.interrupterStackTraces);
/* 67:   */  }
/* 68:   */  
/* 69:   */  public void printStackTraceOfInterruptingThreads(PrintStream out) {
/* 70:70 */    for (StackTraceElement[] stackTraceElements : this.interrupterStackTraces) {
/* 71:71 */      for (StackTraceElement stackTraceElement : stackTraceElements) {
/* 72:72 */        out.print(stackTraceElement + "\n");
/* 73:   */      }
/* 74:74 */      out.print("\n");
/* 75:   */    }
/* 76:   */  }
/* 77:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.concurrency.InterruptCapturingThread
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */