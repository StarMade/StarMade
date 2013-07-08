package org.schema.schine.network;

public class LoginFailedException
  extends Exception
{
  private static final long serialVersionUID = 9172243621536784232L;
  private final int errorCode;
  
  public LoginFailedException(int paramInt)
  {
    this.errorCode = paramInt;
  }
  
  public int getErrorCode()
  {
    return this.errorCode;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.LoginFailedException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */