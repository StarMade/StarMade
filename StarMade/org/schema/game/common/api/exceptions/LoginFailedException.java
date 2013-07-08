package org.schema.game.common.api.exceptions;

public class LoginFailedException
  extends Exception
{
  private static final long serialVersionUID = -1347598156325355886L;
  
  public LoginFailedException(int paramInt)
  {
    super("server response code: " + paramInt);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.api.exceptions.LoginFailedException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */