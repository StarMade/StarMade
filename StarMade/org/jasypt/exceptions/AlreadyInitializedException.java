/*  1:   */package org.jasypt.exceptions;
/*  2:   */
/* 17:   */public final class AlreadyInitializedException
/* 18:   */  extends RuntimeException
/* 19:   */{
/* 20:   */  private static final long serialVersionUID = 4592515503937873874L;
/* 21:   */  
/* 36:   */  public AlreadyInitializedException()
/* 37:   */  {
/* 38:38 */    super("Encryption entity already initialized");
/* 39:   */  }
/* 40:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.exceptions.AlreadyInitializedException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */