/*  1:   */package org.apache.commons.lang3.exception;
/*  2:   */
/* 18:   */public class CloneFailedException
/* 19:   */  extends RuntimeException
/* 20:   */{
/* 21:   */  private static final long serialVersionUID = 20091223L;
/* 22:   */  
/* 37:   */  public CloneFailedException(String message)
/* 38:   */  {
/* 39:39 */    super(message);
/* 40:   */  }
/* 41:   */  
/* 47:   */  public CloneFailedException(Throwable cause)
/* 48:   */  {
/* 49:49 */    super(cause);
/* 50:   */  }
/* 51:   */  
/* 58:   */  public CloneFailedException(String message, Throwable cause)
/* 59:   */  {
/* 60:60 */    super(message, cause);
/* 61:   */  }
/* 62:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.exception.CloneFailedException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */