package com.google.code.tempusfugit.concurrency;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ThreadDump
{
  public static void dumpThreads(PrintStream writer)
  {
    DeadlockDetector.printDeadlocks(writer);
    Map<Thread, StackTraceElement[]> traces = Thread.getAllStackTraces();
    Iterator local_i$1 = traces.keySet().iterator();
    while (local_i$1.hasNext())
    {
      Thread thread = (Thread)local_i$1.next();
      writer.println(String.format("\nThread %s@%d: (state = %s)", new Object[] { thread.getName(), Long.valueOf(thread.getId()), thread.getState() }));
      for (StackTraceElement stackTraceElement : (StackTraceElement[])traces.get(thread)) {
        writer.println(" - " + stackTraceElement);
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.google.code.tempusfugit.concurrency.ThreadDump
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */