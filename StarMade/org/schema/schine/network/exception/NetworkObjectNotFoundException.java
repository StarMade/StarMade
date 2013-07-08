package org.schema.schine.network.exception;

import java.util.HashMap;

public class NetworkObjectNotFoundException
  extends Exception
{
  private static final long serialVersionUID = 5077844495312584968L;
  
  public NetworkObjectNotFoundException(int paramInt, Class paramClass, String paramString, HashMap paramHashMap)
  {
    super(paramClass.getSimpleName() + " with id " + paramInt + " not found. updateString: " + paramString + ". available: " + paramHashMap);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.exception.NetworkObjectNotFoundException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */