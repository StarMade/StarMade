package org.schema.game.common.data.player.faction;

public class FactionNotFoundException
  extends Exception
{
  private static final long serialVersionUID = 7052997477943103281L;
  
  public FactionNotFoundException(Integer paramInteger)
  {
    super("ID: " + paramInteger);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.player.faction.FactionNotFoundException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */