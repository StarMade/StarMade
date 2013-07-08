/*  1:   */package org.schema.game.common.data.world;
/*  2:   */
/*  4:   */public class SectorNotFoundException
/*  5:   */  extends RuntimeException
/*  6:   */{
/*  7:   */  private static final long serialVersionUID = 7463903087999058722L;
/*  8:   */  
/*  9:   */  public SectorNotFoundException(int paramInt)
/* 10:   */  {
/* 11:11 */    super("SECTOR: " + String.valueOf(paramInt));
/* 12:   */  }
/* 13:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.world.SectorNotFoundException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */