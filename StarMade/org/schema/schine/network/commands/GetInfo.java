package org.schema.schine.network.commands;

import java.io.PrintStream;
import java.util.HashMap;
import org.schema.schine.network.Command;
import org.schema.schine.network.NetworkProcessor;
import org.schema.schine.network.client.ClientStateInterface;
import org.schema.schine.network.server.ServerProcessor;
import org.schema.schine.network.server.ServerStateInterface;

public class GetInfo
  extends Command
{
  private static final byte INFO_VERSION = 2;
  private long started;
  
  public GetInfo()
  {
    this.mode = 1;
  }
  
  public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort)
  {
    ((Byte)paramArrayOfObject[0]).byteValue();
    paramClientStateInterface = ((Float)paramArrayOfObject[1]).floatValue();
    paramShort = (String)paramArrayOfObject[2];
    String str = (String)paramArrayOfObject[3];
    long l = ((Long)paramArrayOfObject[4]).longValue();
    int i = ((Integer)paramArrayOfObject[5]).intValue();
    paramArrayOfObject = ((Integer)paramArrayOfObject[6]).intValue();
    System.currentTimeMillis();
    System.out.println("[CLIENT][INFO]: CLIENT INFO ");
    System.out.println("[CLIENT][INFO]: Version: " + paramClientStateInterface);
    System.out.println("[CLIENT][INFO]: Name: " + paramShort);
    System.out.println("[CLIENT][INFO]: Description: " + str);
    System.out.println("[CLIENT][INFO]: Started: " + l);
    System.out.println("[CLIENT][INFO]: Players: " + i + "/" + paramArrayOfObject);
  }
  
  public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
  {
    paramArrayOfObject = paramServerStateInterface.getVersion();
    String str1 = paramServerStateInterface.getServerName();
    String str2 = paramServerStateInterface.getServerDesc();
    long l = paramServerStateInterface.getStartTime();
    int i = paramServerStateInterface.getClients().size();
    int j = paramServerStateInterface.getMaxClients();
    createReturnToClient(paramServerStateInterface, paramServerProcessor, paramShort, new Object[] { Byte.valueOf(2), Float.valueOf(paramArrayOfObject), str1, str2, Long.valueOf(l), Integer.valueOf(i), Integer.valueOf(j) });
    paramServerProcessor.disconnectAfterSent();
  }
  
  public void writeAndCommitParametriziedCommand(Object[] paramArrayOfObject, int paramInt1, int paramInt2, short paramShort, NetworkProcessor paramNetworkProcessor)
  {
    this.started = System.currentTimeMillis();
    super.writeAndCommitParametriziedCommand(paramArrayOfObject, paramInt1, paramInt2, paramShort, paramNetworkProcessor);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.commands.GetInfo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */