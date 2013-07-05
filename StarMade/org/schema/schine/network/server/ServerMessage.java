/*    */ package org.schema.schine.network.server;
/*    */ 
/*    */ public class ServerMessage
/*    */ {
/*    */   public static final int MESSAGE_TYPE_SIMPLE = 0;
/*    */   public static final int MESSAGE_TYPE_INFO = 1;
/*    */   public static final int MESSAGE_TYPE_WANRING = 2;
/*    */   public static final int MESSAGE_TYPE_ERROR = 3;
/*    */   public String prefix;
/*    */   public final String message;
/*    */   public final int type;
/*    */   public final int receiverPlayerId;
/*    */ 
/*    */   public ServerMessage(String paramString, int paramInt)
/*    */   {
/* 17 */     this.message = paramString;
/* 18 */     this.type = paramInt;
/* 19 */     this.receiverPlayerId = 0;
/*    */   }
/*    */ 
/*    */   public ServerMessage(String paramString, int paramInt1, int paramInt2) {
/* 23 */     this.message = paramString;
/* 24 */     this.type = paramInt1;
/* 25 */     this.receiverPlayerId = paramInt2;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 32 */     return "[SERVERMSG (type " + this.type + "): " + this.message + "]";
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.server.ServerMessage
 * JD-Core Version:    0.6.2
 */