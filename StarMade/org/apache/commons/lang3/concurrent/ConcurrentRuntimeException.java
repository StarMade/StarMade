/*  1:   */package org.apache.commons.lang3.concurrent;
/*  2:   */
/* 19:   */public class ConcurrentRuntimeException
/* 20:   */  extends RuntimeException
/* 21:   */{
/* 22:   */  private static final long serialVersionUID = -6582182735562919670L;
/* 23:   */  
/* 39:   */  protected ConcurrentRuntimeException() {}
/* 40:   */  
/* 56:   */  public ConcurrentRuntimeException(Throwable cause)
/* 57:   */  {
/* 58:58 */    super(ConcurrentUtils.checkedException(cause));
/* 59:   */  }
/* 60:   */  
/* 68:   */  public ConcurrentRuntimeException(String msg, Throwable cause)
/* 69:   */  {
/* 70:70 */    super(msg, ConcurrentUtils.checkedException(cause));
/* 71:   */  }
/* 72:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.concurrent.ConcurrentRuntimeException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */