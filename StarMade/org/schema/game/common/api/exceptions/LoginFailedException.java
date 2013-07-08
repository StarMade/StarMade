/*  1:   */package org.schema.game.common.api.exceptions;
/*  2:   */
/*  3:   */public class LoginFailedException
/*  4:   */  extends Exception
/*  5:   */{
/*  6:   */  private static final long serialVersionUID = -1347598156325355886L;
/*  7:   */  
/*  8:   */  public LoginFailedException(int paramInt)
/*  9:   */  {
/* 10:10 */    super("server response code: " + paramInt);
/* 11:   */  }
/* 12:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.api.exceptions.LoginFailedException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */