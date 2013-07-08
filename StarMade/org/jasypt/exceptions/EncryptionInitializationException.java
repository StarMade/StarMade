/*  1:   */package org.jasypt.exceptions;
/*  2:   */
/* 13:   */public final class EncryptionInitializationException
/* 14:   */  extends RuntimeException
/* 15:   */{
/* 16:   */  private static final long serialVersionUID = 8929638240023639778L;
/* 17:   */  
/* 27:   */  public EncryptionInitializationException() {}
/* 28:   */  
/* 38:   */  public EncryptionInitializationException(Throwable t)
/* 39:   */  {
/* 40:40 */    super(t);
/* 41:   */  }
/* 42:   */  
/* 43:   */  public EncryptionInitializationException(String msg, Throwable t) {
/* 44:44 */    super(msg, t);
/* 45:   */  }
/* 46:   */  
/* 47:   */  public EncryptionInitializationException(String msg) {
/* 48:48 */    super(msg);
/* 49:   */  }
/* 50:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.exceptions.EncryptionInitializationException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */