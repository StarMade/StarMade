/*  1:   */package org.schema.schine.network;
/*  2:   */
/*  4:   */public class LoginFailedException
/*  5:   */  extends Exception
/*  6:   */{
/*  7:   */  private static final long serialVersionUID = 9172243621536784232L;
/*  8:   */  private final int errorCode;
/*  9:   */  
/* 10:   */  public LoginFailedException(int paramInt)
/* 11:   */  {
/* 12:12 */    this.errorCode = paramInt;
/* 13:   */  }
/* 14:   */  
/* 17:   */  public int getErrorCode()
/* 18:   */  {
/* 19:19 */    return this.errorCode;
/* 20:   */  }
/* 21:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.LoginFailedException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */