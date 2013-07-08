/*  1:   */package org.schema.schine.network.commands;
/*  2:   */
/*  3:   */import org.schema.schine.network.Command;
/*  4:   */import org.schema.schine.network.NetworkProcessor;
/*  5:   */import org.schema.schine.network.client.ClientStateInterface;
/*  6:   */import org.schema.schine.network.server.ServerProcessor;
/*  7:   */import org.schema.schine.network.server.ServerStateInterface;
/*  8:   */
/* 60:   */public class RequestServerTime
/* 61:   */  extends Command
/* 62:   */{
/* 63:   */  private long started;
/* 64:   */  
/* 65:   */  public RequestServerTime()
/* 66:   */  {
/* 67:67 */    this.mode = 0;
/* 68:   */  }
/* 69:   */  
/* 72:   */  public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort)
/* 73:   */  {
/* 74:74 */    long l = System.currentTimeMillis() - this.started;
/* 75:   */    
/* 76:76 */    paramClientStateInterface.setServerTimeOnLogin(((Long)paramArrayOfObject[0]).longValue() + l / 2L);
/* 77:   */  }
/* 78:   */  
/* 81:   */  public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
/* 82:   */  {
/* 83:83 */    createReturnToClient(paramServerStateInterface, paramServerProcessor, paramShort, new Object[] { Long.valueOf(System.currentTimeMillis()) });
/* 84:   */  }
/* 85:   */  
/* 87:   */  public void writeAndCommitParametriziedCommand(Object[] paramArrayOfObject, int paramInt1, int paramInt2, short paramShort, NetworkProcessor paramNetworkProcessor)
/* 88:   */  {
/* 89:89 */    this.started = System.currentTimeMillis();
/* 90:90 */    super.writeAndCommitParametriziedCommand(paramArrayOfObject, paramInt1, paramInt2, paramShort, paramNetworkProcessor);
/* 91:   */  }
/* 92:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.commands.RequestServerTime
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */