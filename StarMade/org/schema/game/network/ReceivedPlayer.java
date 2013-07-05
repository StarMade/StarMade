/*    */ package org.schema.game.network;
/*    */ 
/*    */ public class ReceivedPlayer
/*    */ {
/*    */   public String name;
/*    */   public long lastLogin;
/*    */   public long lastLogout;
/*    */   public String[] ips;
/*    */ 
/*    */   public void decode(Object[] paramArrayOfObject, int paramInt1, int paramInt2)
/*    */   {
/* 11 */     this.name = ((String)paramArrayOfObject[paramInt1]);
/* 12 */     this.lastLogin = ((Long)paramArrayOfObject[(paramInt1 + 1)]).longValue();
/* 13 */     this.lastLogout = ((Long)paramArrayOfObject[(paramInt1 + 2)]).longValue();
/* 14 */     paramArrayOfObject = (String)paramArrayOfObject[(paramInt1 + 3)];
/* 15 */     this.ips = paramArrayOfObject.split(",");
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 23 */     return this.name;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.ReceivedPlayer
 * JD-Core Version:    0.6.2
 */