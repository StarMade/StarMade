package org.schema.game.common.data.element;

public class BlockFactory
{
  public short enhancer;
  public FactoryResource[][] input;
  public FactoryResource[][] output;
  
  public String toString()
  {
    if (this.input != null) {
      return "Block Factory Products: " + this.input.length;
    }
    return "INPUT";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.element.BlockFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */