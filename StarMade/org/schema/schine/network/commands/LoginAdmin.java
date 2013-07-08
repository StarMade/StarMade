package org.schema.schine.network.commands;

import java.io.PrintStream;
import org.schema.schine.network.Command;
import org.schema.schine.network.IdGen;
import org.schema.schine.network.NetworkProcessor;
import org.schema.schine.network.RegisteredClientOnServer;
import org.schema.schine.network.client.ClientStateInterface;
import org.schema.schine.network.server.ServerControllerInterface;
import org.schema.schine.network.server.ServerProcessor;
import org.schema.schine.network.server.ServerStateInterface;

public class LoginAdmin
  extends Command
{
  public static final int SUCCESS_LOGGED_IN = 0;
  public static final int ERROR_GENRAL_ERROR = -1;
  public static final int ERROR_ALREADY_LOGGED_IN = -2;
  public static final int ERROR_ACCESS_DENIED = -3;
  public static final int ERROR_SERVER_FULL = -4;
  public static final int ERROR_WRONG_CLIENT_VERSION = -5;
  public static final int ERROR_YOU_ARE_BANNED = -6;
  public static final int ERROR_AUTHENTICATION_FAILED = -7;
  public static final int NOT_LOGGED_IN = -4242;
  private long started;
  
  public LoginAdmin()
  {
    this.mode = 1;
  }
  
  public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort)
  {
    paramShort = ((Integer)paramArrayOfObject[0]).intValue();
    float f = ((Float)paramArrayOfObject[3]).floatValue();
    paramClientStateInterface.setServerVersion(f);
    if (paramShort < 0)
    {
      switch (paramShort)
      {
      case -2: 
        System.err.println("[Client] [LOGIN]: ERROR: Already logged in");
        paramClientStateInterface.setId(paramShort);
        return;
      case -7: 
        System.err.println("[Client] [LOGIN]: ERROR: Authentication Failed");
        paramClientStateInterface.setId(paramShort);
        return;
      case -3: 
        System.err.println("[Client] [LOGIN]: ERROR: Access Denied");
        paramClientStateInterface.setId(paramShort);
        return;
      case -1: 
        System.err.println("[Client] [LOGIN]: ERROR: General Error");
        paramClientStateInterface.setId(paramShort);
        return;
      case -5: 
        System.err.println("[Client] [LOGIN]: ERROR: The version of your client is too old. Try updating with the StarMade-Starter. (server version: " + f + ")");
        paramClientStateInterface.setId(paramShort);
        return;
      case -4: 
        System.err.println("[Client] [LOGIN]: ERROR: Server FULL Error");
        paramClientStateInterface.setId(paramShort);
        return;
      case -6: 
        System.err.println("[Client] [LOGIN]: ERROR: You are banned from this server");
        paramClientStateInterface.setId(paramShort);
        return;
      }
      if (!$assertionsDisabled) {
        throw new AssertionError("something went wrong: " + paramShort);
      }
    }
    else
    {
      paramClientStateInterface.setId(((Integer)paramArrayOfObject[1]).intValue());
      long l = System.currentTimeMillis() - this.started;
      paramClientStateInterface.setServerTimeOnLogin(((Long)paramArrayOfObject[2]).longValue() + l / 2L);
      System.err.println("[Client] [LOGIN]: Client sucessfully registered with id: " + paramClientStateInterface.getId());
    }
  }
  
  public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
  {
    String str1 = (String)paramArrayOfObject[0];
    float f1 = paramArrayOfObject.length > 1 ? ((Float)paramArrayOfObject[1]).floatValue() : 0.0F;
    String str2 = paramArrayOfObject.length > 2 ? (String)paramArrayOfObject[2] : "";
    String str3 = paramArrayOfObject.length > 3 ? (String)paramArrayOfObject[3] : "";
    int i = IdGen.getFreeStateId();
    if (paramArrayOfObject.length > 4)
    {
      paramArrayOfObject = -5;
    }
    else
    {
      System.err.println("[SERVER][LOGIN] new client connected. given id: " + i + ": description: " + str1);
      RegisteredClientOnServer localRegisteredClientOnServer;
      (localRegisteredClientOnServer = new RegisteredClientOnServer(i, str1, paramServerStateInterface)).setProcessor(paramServerProcessor);
      paramServerProcessor.setClient(localRegisteredClientOnServer);
      if (!paramServerStateInterface.getController().authenticate(str1, str2, str3))
      {
        paramArrayOfObject = -7;
      }
      else
      {
        paramServerStateInterface.getController().protectUserName(str1, str2, str3);
        paramArrayOfObject = paramServerStateInterface.getController().registerClient(localRegisteredClientOnServer, f1);
      }
      System.err.println("[SERVER][LOGIN] return code " + paramArrayOfObject);
    }
    if (paramArrayOfObject < 0)
    {
      System.err.println("[SERVER][LOGIN] login failed (" + paramArrayOfObject + "): SET CLIENT TO NULL");
      paramServerProcessor.setClient(null);
    }
    float f2 = paramServerStateInterface.getVersion();
    System.out.println("[SERVER][LOGIN] login received. returning login info for " + paramServerProcessor.getClient() + ": returnCode: " + paramArrayOfObject);
    paramServerStateInterface.getController().broadcastMessage(str1 + " has joined the game", 0);
    createReturnToClient(paramServerStateInterface, paramServerProcessor, paramShort, new Object[] { Integer.valueOf(paramArrayOfObject), Integer.valueOf(i), Long.valueOf(System.currentTimeMillis()), Float.valueOf(f2) });
    if (paramArrayOfObject < 0)
    {
      paramServerProcessor.disconnectDelayed();
      paramServerStateInterface.getController().broadcastMessage(str1 + "'s connection failed (" + paramArrayOfObject + ")", 0);
    }
  }
  
  public void writeAndCommitParametriziedCommand(Object[] paramArrayOfObject, int paramInt1, int paramInt2, short paramShort, NetworkProcessor paramNetworkProcessor)
  {
    this.started = System.currentTimeMillis();
    super.writeAndCommitParametriziedCommand(paramArrayOfObject, paramInt1, paramInt2, paramShort, paramNetworkProcessor);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.commands.LoginAdmin
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */