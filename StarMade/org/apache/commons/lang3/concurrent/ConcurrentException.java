package org.apache.commons.lang3.concurrent;

public class ConcurrentException
  extends Exception
{
  private static final long serialVersionUID = 6622707671812226130L;
  
  protected ConcurrentException() {}
  
  public ConcurrentException(Throwable cause)
  {
    super(ConcurrentUtils.checkedException(cause));
  }
  
  public ConcurrentException(String msg, Throwable cause)
  {
    super(msg, ConcurrentUtils.checkedException(cause));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.lang3.concurrent.ConcurrentException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */