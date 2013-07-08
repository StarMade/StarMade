/*  1:   */package org.apache.commons.lang3.concurrent;
/*  2:   */
/* 18:   */public class ConcurrentException
/* 19:   */  extends Exception
/* 20:   */{
/* 21:   */  private static final long serialVersionUID = 6622707671812226130L;
/* 22:   */  
/* 38:   */  protected ConcurrentException() {}
/* 39:   */  
/* 54:   */  public ConcurrentException(Throwable cause)
/* 55:   */  {
/* 56:56 */    super(ConcurrentUtils.checkedException(cause));
/* 57:   */  }
/* 58:   */  
/* 66:   */  public ConcurrentException(String msg, Throwable cause)
/* 67:   */  {
/* 68:68 */    super(msg, ConcurrentUtils.checkedException(cause));
/* 69:   */  }
/* 70:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.concurrent.ConcurrentException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */