/*  1:   */package org.schema.game.server.data;
/*  2:   */
/*  4:   */public class PlayerNotFountException
/*  5:   */  extends Exception
/*  6:   */{
/*  7:   */  private static final long serialVersionUID = -4501401334942597976L;
/*  8:   */  
/* 10:   */  public PlayerNotFountException(String paramString)
/* 11:   */  {
/* 12:12 */    super("Player not found: \"" + paramString + "\"");
/* 13:   */  }
/* 14:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.server.data.PlayerNotFountException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */