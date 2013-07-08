package com.google.code.tempusfugit.concurrency;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InterruptCapturingThread
  extends Thread
{
  private final CopyOnWriteArrayList<StackTraceElement[]> interrupterStackTraces = new CopyOnWriteArrayList();
  
  public InterruptCapturingThread() {}
  
  public InterruptCapturingThread(Runnable runnable)
  {
    super(runnable);
  }
  
  public InterruptCapturingThread(ThreadGroup threadGroup, Runnable runnable)
  {
    super(threadGroup, runnable);
  }
  
  public InterruptCapturingThread(String name)
  {
    super(name);
  }
  
  public InterruptCapturingThread(ThreadGroup threadGroup, String name)
  {
    super(threadGroup, name);
  }
  
  public InterruptCapturingThread(Runnable runnable, String name)
  {
    super(runnable, name);
  }
  
  public InterruptCapturingThread(ThreadGroup threadGroup, Runnable runnable, String name)
  {
    super(threadGroup, runnable, name);
  }
  
  public InterruptCapturingThread(ThreadGroup threadGroup, Runnable runnable, String name, long stackSize)
  {
    super(threadGroup, runnable, name, stackSize);
  }
  
  public void interrupt()
  {
    this.interrupterStackTraces.add(Thread.currentThread().getStackTrace());
    super.interrupt();
  }
  
  public List<StackTraceElement[]> getInterrupters()
  {
    return new ArrayList(this.interrupterStackTraces);
  }
  
  public void printStackTraceOfInterruptingThreads(PrintStream out)
  {
    Iterator local_i$1 = this.interrupterStackTraces.iterator();
    while (local_i$1.hasNext())
    {
      StackTraceElement[] stackTraceElements = (StackTraceElement[])local_i$1.next();
      for (StackTraceElement stackTraceElement : stackTraceElements) {
        out.print(stackTraceElement + "\n");
      }
      out.print("\n");
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.google.code.tempusfugit.concurrency.InterruptCapturingThread
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */