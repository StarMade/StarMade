package com.google.code.tempusfugit.concurrency;

import java.util.concurrent.ThreadFactory;

public class DefaultThreadFactory
  implements ThreadFactory
{
  public Thread newThread(Runnable runnable)
  {
    return new Thread(runnable);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.google.code.tempusfugit.concurrency.DefaultThreadFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */