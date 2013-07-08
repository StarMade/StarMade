package org.schema.game.common.data.element;

public class BlockLevel
{
  private final short field_2247;
  private final int level;
  
  public BlockLevel(short paramShort, int paramInt)
  {
    this.field_2247 = paramShort;
    this.level = paramInt;
  }
  
  public short getIdBase()
  {
    return this.field_2247;
  }
  
  public int getLevel()
  {
    return this.level;
  }
  
  public String toString()
  {
    return "Level: " + this.level + "; Base: " + ElementKeyMap.getInfo(this.field_2247);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.element.BlockLevel
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */