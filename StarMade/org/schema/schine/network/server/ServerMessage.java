package org.schema.schine.network.server;

public class ServerMessage
{
  public static final int MESSAGE_TYPE_SIMPLE = 0;
  public static final int MESSAGE_TYPE_INFO = 1;
  public static final int MESSAGE_TYPE_WANRING = 2;
  public static final int MESSAGE_TYPE_ERROR = 3;
  public String prefix;
  public final String message;
  public final int type;
  public final int receiverPlayerId;
  
  public ServerMessage(String paramString, int paramInt)
  {
    this.message = paramString;
    this.type = paramInt;
    this.receiverPlayerId = 0;
  }
  
  public ServerMessage(String paramString, int paramInt1, int paramInt2)
  {
    this.message = paramString;
    this.type = paramInt1;
    this.receiverPlayerId = paramInt2;
  }
  
  public String toString()
  {
    return "[SERVERMSG (type " + this.type + "): " + this.message + "]";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.server.ServerMessage
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */