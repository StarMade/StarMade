/*  1:   */package com.google.code.tempusfugit;
/*  2:   */
/* 11:   */public final class WithException<E extends Exception>
/* 12:   */{
/* 13:   */  private final Class<E> type;
/* 14:   */  
/* 22:   */  public static <E extends Exception> WithException<E> with(Class<E> type)
/* 23:   */  {
/* 24:24 */    return new WithException(type);
/* 25:   */  }
/* 26:   */  
/* 27:   */  private WithException(Class<E> type) {
/* 28:28 */    this.type = type;
/* 29:   */  }
/* 30:   */  
/* 31:   */  public Class<E> getType() {
/* 32:32 */    return this.type;
/* 33:   */  }
/* 34:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.WithException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */