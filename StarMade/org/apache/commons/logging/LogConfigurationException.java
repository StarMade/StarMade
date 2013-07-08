/*  1:   */package org.apache.commons.logging;
/*  2:   */
/* 23:   */public class LogConfigurationException
/* 24:   */  extends RuntimeException
/* 25:   */{
/* 26:   */  public LogConfigurationException() {}
/* 27:   */  
/* 48:   */  public LogConfigurationException(String message)
/* 49:   */  {
/* 50:50 */    super(message);
/* 51:   */  }
/* 52:   */  
/* 61:   */  public LogConfigurationException(Throwable cause)
/* 62:   */  {
/* 63:63 */    this(cause == null ? null : cause.toString(), cause);
/* 64:   */  }
/* 65:   */  
/* 74:   */  public LogConfigurationException(String message, Throwable cause)
/* 75:   */  {
/* 76:76 */    super(message + " (Caused by " + cause + ")");
/* 77:77 */    this.cause = cause;
/* 78:   */  }
/* 79:   */  
/* 85:85 */  protected Throwable cause = null;
/* 86:   */  
/* 91:   */  public Throwable getCause()
/* 92:   */  {
/* 93:93 */    return this.cause;
/* 94:   */  }
/* 95:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.logging.LogConfigurationException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */