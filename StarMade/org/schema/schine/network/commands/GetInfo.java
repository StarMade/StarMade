/*   1:    */package org.schema.schine.network.commands;
/*   2:    */
/*   3:    */import java.io.PrintStream;
/*   4:    */import java.util.HashMap;
/*   5:    */import org.schema.schine.network.Command;
/*   6:    */import org.schema.schine.network.NetworkProcessor;
/*   7:    */import org.schema.schine.network.client.ClientStateInterface;
/*   8:    */import org.schema.schine.network.server.ServerProcessor;
/*   9:    */import org.schema.schine.network.server.ServerStateInterface;
/*  10:    */
/*  66:    */public class GetInfo
/*  67:    */  extends Command
/*  68:    */{
/*  69:    */  private static final byte INFO_VERSION = 2;
/*  70:    */  private long started;
/*  71:    */  
/*  72:    */  public GetInfo()
/*  73:    */  {
/*  74: 74 */    this.mode = 1;
/*  75:    */  }
/*  76:    */  
/*  77:    */  public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort)
/*  78:    */  {
/*  79: 79 */    ((Byte)paramArrayOfObject[0]).byteValue();
/*  80: 80 */    paramClientStateInterface = ((Float)paramArrayOfObject[1]).floatValue();
/*  81: 81 */    paramShort = (String)paramArrayOfObject[2];
/*  82: 82 */    String str = (String)paramArrayOfObject[3];
/*  83: 83 */    long l = ((Long)paramArrayOfObject[4]).longValue();
/*  84: 84 */    int i = ((Integer)paramArrayOfObject[5]).intValue();
/*  85: 85 */    paramArrayOfObject = ((Integer)paramArrayOfObject[6]).intValue();
/*  86:    */    
/*  87: 87 */    System.currentTimeMillis();
/*  88:    */    
/*  90: 90 */    System.out.println("[CLIENT][INFO]: CLIENT INFO ");
/*  91: 91 */    System.out.println("[CLIENT][INFO]: Version: " + paramClientStateInterface);
/*  92: 92 */    System.out.println("[CLIENT][INFO]: Name: " + paramShort);
/*  93: 93 */    System.out.println("[CLIENT][INFO]: Description: " + str);
/*  94: 94 */    System.out.println("[CLIENT][INFO]: Started: " + l);
/*  95: 95 */    System.out.println("[CLIENT][INFO]: Players: " + i + "/" + paramArrayOfObject);
/*  96:    */  }
/*  97:    */  
/* 102:    */  public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
/* 103:    */  {
/* 104:104 */    paramArrayOfObject = paramServerStateInterface.getVersion();
/* 105:    */    
/* 106:106 */    String str1 = paramServerStateInterface.getServerName();
/* 107:107 */    String str2 = paramServerStateInterface.getServerDesc();
/* 108:108 */    long l = paramServerStateInterface.getStartTime();
/* 109:109 */    int i = paramServerStateInterface.getClients().size();
/* 110:110 */    int j = paramServerStateInterface.getMaxClients();
/* 111:    */    
/* 112:112 */    createReturnToClient(paramServerStateInterface, paramServerProcessor, paramShort, new Object[] { Byte.valueOf(2), Float.valueOf(paramArrayOfObject), str1, str2, Long.valueOf(l), Integer.valueOf(i), Integer.valueOf(j) });
/* 113:    */    
/* 114:114 */    paramServerProcessor.disconnectAfterSent();
/* 115:    */  }
/* 116:    */  
/* 118:    */  public void writeAndCommitParametriziedCommand(Object[] paramArrayOfObject, int paramInt1, int paramInt2, short paramShort, NetworkProcessor paramNetworkProcessor)
/* 119:    */  {
/* 120:120 */    this.started = System.currentTimeMillis();
/* 121:    */    
/* 122:122 */    super.writeAndCommitParametriziedCommand(paramArrayOfObject, paramInt1, paramInt2, paramShort, paramNetworkProcessor);
/* 123:    */  }
/* 124:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.commands.GetInfo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */