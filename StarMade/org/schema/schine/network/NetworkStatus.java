/*    */ package org.schema.schine.network;
/*    */ 
/*    */ public class NetworkStatus
/*    */ {
/*    */   private long totalBytesSent;
/*    */   private int bytesSent;
/*    */   private int bytesSentPerSecond;
/*    */   private long totalBytesReceived;
/*    */   private int bytesReceived;
/*    */   private int bytesReceivedPerSecond;
/*    */   private long lastSecondSent;
/*    */   private long lastSecondReceived;
/*    */ 
/*    */   public void addBytesReceived(int paramInt)
/*    */   {
/* 20 */     this.totalBytesReceived += paramInt;
/* 21 */     this.bytesReceived += paramInt;
/* 22 */     if (this.lastSecondReceived + 1000L < System.currentTimeMillis()) {
/* 23 */       this.bytesReceivedPerSecond = this.bytesReceived;
/* 24 */       this.bytesReceived = 0;
/* 25 */       this.lastSecondReceived = System.currentTimeMillis();
/*    */     }
/*    */   }
/*    */ 
/*    */   public void addBytesSent(int paramInt)
/*    */   {
/* 33 */     this.totalBytesSent += paramInt;
/* 34 */     this.bytesSent += paramInt;
/* 35 */     if (this.lastSecondSent + 1000L < System.currentTimeMillis())
/*    */     {
/* 37 */       this.bytesSentPerSecond = this.bytesSent;
/* 38 */       this.bytesSent = 0;
/* 39 */       this.lastSecondSent = System.currentTimeMillis();
/*    */     }
/*    */   }
/*    */ 
/*    */   public int getBytesReceivedPerSecond()
/*    */   {
/* 51 */     addBytesReceived(0);
/* 52 */     return this.bytesReceivedPerSecond;
/*    */   }
/*    */ 
/*    */   public int getBytesSentPerSecond()
/*    */   {
/* 59 */     addBytesSent(0);
/* 60 */     return this.bytesSentPerSecond;
/*    */   }
/*    */ 
/*    */   public long getTotalBytesReceived()
/*    */   {
/* 67 */     return this.totalBytesReceived;
/*    */   }
/*    */ 
/*    */   public long getTotalBytesSent()
/*    */   {
/* 74 */     return this.totalBytesSent;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.NetworkStatus
 * JD-Core Version:    0.6.2
 */