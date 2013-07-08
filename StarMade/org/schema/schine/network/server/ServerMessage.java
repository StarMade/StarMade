/*  1:   */package org.schema.schine.network.server;
/*  2:   */
/*  4:   */public class ServerMessage
/*  5:   */{
/*  6:   */  public static final int MESSAGE_TYPE_SIMPLE = 0;
/*  7:   */  public static final int MESSAGE_TYPE_INFO = 1;
/*  8:   */  public static final int MESSAGE_TYPE_WANRING = 2;
/*  9:   */  public static final int MESSAGE_TYPE_ERROR = 3;
/* 10:   */  public String prefix;
/* 11:   */  public final String message;
/* 12:   */  public final int type;
/* 13:   */  public final int receiverPlayerId;
/* 14:   */  
/* 15:   */  public ServerMessage(String paramString, int paramInt)
/* 16:   */  {
/* 17:17 */    this.message = paramString;
/* 18:18 */    this.type = paramInt;
/* 19:19 */    this.receiverPlayerId = 0;
/* 20:   */  }
/* 21:   */  
/* 22:   */  public ServerMessage(String paramString, int paramInt1, int paramInt2) {
/* 23:23 */    this.message = paramString;
/* 24:24 */    this.type = paramInt1;
/* 25:25 */    this.receiverPlayerId = paramInt2;
/* 26:   */  }
/* 27:   */  
/* 30:   */  public String toString()
/* 31:   */  {
/* 32:32 */    return "[SERVERMSG (type " + this.type + "): " + this.message + "]";
/* 33:   */  }
/* 34:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.server.ServerMessage
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */