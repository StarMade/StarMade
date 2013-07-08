package org.schema.schine.network;

public abstract interface Recipient
{
  public abstract int getId();
  
  public abstract void sendCommand(int paramInt, short paramShort, Class paramClass, Object... paramVarArgs);
  
  public abstract Object[] sendReturnedCommand(int paramInt, short paramShort, Class paramClass, Object... paramVarArgs);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.Recipient
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */