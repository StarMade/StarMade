/*  1:   */package org.schema.game.common.data.element;
/*  2:   */
/*  3:   */public class BlockLevel
/*  4:   */{
/*  5:   */  private final short id;
/*  6:   */  private final int level;
/*  7:   */  
/*  8:   */  public BlockLevel(short paramShort, int paramInt) {
/*  9: 9 */    this.id = paramShort;
/* 10:10 */    this.level = paramInt;
/* 11:   */  }
/* 12:   */  
/* 14:   */  public short getIdBase()
/* 15:   */  {
/* 16:16 */    return this.id;
/* 17:   */  }
/* 18:   */  
/* 20:   */  public int getLevel()
/* 21:   */  {
/* 22:22 */    return this.level;
/* 23:   */  }
/* 24:   */  
/* 26:   */  public String toString()
/* 27:   */  {
/* 28:28 */    return "Level: " + this.level + "; Base: " + ElementKeyMap.getInfo(this.id);
/* 29:   */  }
/* 30:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.BlockLevel
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */