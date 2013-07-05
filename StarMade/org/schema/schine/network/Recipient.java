package org.schema.schine.network;

public abstract interface Recipient
{
  public abstract int getId();

  public abstract void sendCommand(int paramInt, short paramShort, Class paramClass, Object[] paramArrayOfObject);

  public abstract Object[] sendReturnedCommand(int paramInt, short paramShort, Class paramClass, Object[] paramArrayOfObject);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.Recipient
 * JD-Core Version:    0.6.2
 */