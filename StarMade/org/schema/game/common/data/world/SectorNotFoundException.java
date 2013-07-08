package org.schema.game.common.data.world;

public class SectorNotFoundException
  extends RuntimeException
{
  private static final long serialVersionUID = 7463903087999058722L;
  
  public SectorNotFoundException(int paramInt)
  {
    super("SECTOR: " + String.valueOf(paramInt));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.world.SectorNotFoundException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */