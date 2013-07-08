package org.schema.schine.network.exception;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerPortNotAvailableException
  extends Exception
{
  private static final long serialVersionUID = 906116778403491118L;
  private boolean instanceRunning;
  
  public static void main(String[] paramArrayOfString)
  {
    paramArrayOfString = null;
    try
    {
      paramArrayOfString = new ServerSocket(4242);
    }
    catch (IOException localIOException1) {}
    try
    {
      for (;;)
      {
        paramArrayOfString.accept();
      }
    }
    catch (IOException localIOException2)
    {
      localIOException2;
    }
  }
  
  public ServerPortNotAvailableException(String paramString)
  {
    super(paramString);
  }
  
  public boolean isInstanceRunning()
  {
    return this.instanceRunning;
  }
  
  public void setInstanceRunning(boolean paramBoolean)
  {
    this.instanceRunning = paramBoolean;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.exception.ServerPortNotAvailableException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */