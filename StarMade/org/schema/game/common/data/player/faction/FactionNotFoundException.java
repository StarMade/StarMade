/*  1:   */package org.schema.game.common.data.player.faction;
/*  2:   */
/*  4:   */public class FactionNotFoundException
/*  5:   */  extends Exception
/*  6:   */{
/*  7:   */  private static final long serialVersionUID = 7052997477943103281L;
/*  8:   */  
/*  9:   */  public FactionNotFoundException(Integer paramInteger)
/* 10:   */  {
/* 11:11 */    super("ID: " + paramInteger);
/* 12:   */  }
/* 13:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.player.faction.FactionNotFoundException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */