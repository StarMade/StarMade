package org.schema.game.server.data;

public class PlayerNotFountException
  extends Exception
{
  private static final long serialVersionUID = -4501401334942597976L;
  
  public PlayerNotFountException(String paramString)
  {
    super("Player not found: \"" + paramString + "\"");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.server.data.PlayerNotFountException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */