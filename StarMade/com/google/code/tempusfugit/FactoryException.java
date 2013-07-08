/*  1:   */package com.google.code.tempusfugit;
/*  2:   */
/* 11:   */public class FactoryException
/* 12:   */  extends RuntimeException
/* 13:   */{
/* 14:   */  public FactoryException() {}
/* 15:   */  
/* 23:   */  public FactoryException(String message)
/* 24:   */  {
/* 25:25 */    super(message);
/* 26:   */  }
/* 27:   */  
/* 28:   */  public FactoryException(String message, Throwable cause) {
/* 29:29 */    super(message, cause);
/* 30:   */  }
/* 31:   */  
/* 32:   */  public FactoryException(Throwable cause) {
/* 33:33 */    super(cause);
/* 34:   */  }
/* 35:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.FactoryException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */