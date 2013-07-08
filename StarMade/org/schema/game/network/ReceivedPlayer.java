package org.schema.game.network;

public class ReceivedPlayer
{
  public String name;
  public long lastLogin;
  public long lastLogout;
  public String[] ips;
  
  public void decode(Object[] paramArrayOfObject, int paramInt1, int paramInt2)
  {
    this.name = ((String)paramArrayOfObject[paramInt1]);
    this.lastLogin = ((Long)paramArrayOfObject[(paramInt1 + 1)]).longValue();
    this.lastLogout = ((Long)paramArrayOfObject[(paramInt1 + 2)]).longValue();
    paramArrayOfObject = (String)paramArrayOfObject[(paramInt1 + 3)];
    this.ips = paramArrayOfObject.split(",");
  }
  
  public String toString()
  {
    return this.name;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.ReceivedPlayer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */