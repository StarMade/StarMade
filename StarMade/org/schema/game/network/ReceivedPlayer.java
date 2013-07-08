/*  1:   */package org.schema.game.network;
/*  2:   */
/*  3:   */public class ReceivedPlayer
/*  4:   */{
/*  5:   */  public String name;
/*  6:   */  public long lastLogin;
/*  7:   */  public long lastLogout;
/*  8:   */  public String[] ips;
/*  9:   */  
/* 10:   */  public void decode(Object[] paramArrayOfObject, int paramInt1, int paramInt2) {
/* 11:11 */    this.name = ((String)paramArrayOfObject[paramInt1]);
/* 12:12 */    this.lastLogin = ((Long)paramArrayOfObject[(paramInt1 + 1)]).longValue();
/* 13:13 */    this.lastLogout = ((Long)paramArrayOfObject[(paramInt1 + 2)]).longValue();
/* 14:14 */    paramArrayOfObject = (String)paramArrayOfObject[(paramInt1 + 3)];
/* 15:15 */    this.ips = paramArrayOfObject.split(",");
/* 16:   */  }
/* 17:   */  
/* 21:   */  public String toString()
/* 22:   */  {
/* 23:23 */    return this.name;
/* 24:   */  }
/* 25:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.ReceivedPlayer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */