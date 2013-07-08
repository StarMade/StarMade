package org.schema.game.common.data.element;

public class FactoryResource
{
  public final int count;
  public final short type;
  
  public FactoryResource(int paramInt, short paramShort)
  {
    this.count = paramInt;
    this.type = paramShort;
  }
  
  public String toString()
  {
    return "(" + ElementKeyMap.getInfo(this.type) + " x" + this.count + ")";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.element.FactoryResource
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */