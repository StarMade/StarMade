package org.schema.schine.network.commands;

import java.io.PrintStream;
import org.schema.schine.network.Command;
import org.schema.schine.network.IdGen;
import org.schema.schine.network.NetworkProcessor;
import org.schema.schine.network.client.ClientStateInterface;
import org.schema.schine.network.server.ServerProcessor;
import org.schema.schine.network.server.ServerStateInterface;

public class Login
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
  public static final int ERROR_NOT_ON_WHITELIST = -8;
  public static final int ERROR_INVALID_USERNAME = -9;
  public static final int NOT_LOGGED_IN = -4242;
  private long started;
  
  public Login()
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
      case -8: 
        System.err.println("[Client] [LOGIN]: ERROR: You are not whitelisted on this server");
        paramClientStateInterface.setId(paramShort);
        return;
      case -9: 
        System.err.println("[Client] [LOGIN]: ERROR: The username is not accepted");
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
    LoginRequest localLoginRequest = new LoginRequest();
    String str1 = (String)paramArrayOfObject[0];
    float f = paramArrayOfObject.length > 1 ? ((Float)paramArrayOfObject[1]).floatValue() : 0.0F;
    String str2 = paramArrayOfObject.length > 2 ? (String)paramArrayOfObject[2] : "";
    paramArrayOfObject = paramArrayOfObject.length > 3 ? (String)paramArrayOfObject[3] : "";
    int i = IdGen.getFreeStateId();
    System.err.println("[SERVER][LOGIN] new client connected. given id: " + i + ": description: " + str1);
    localLoginRequest.state = paramServerStateInterface;
    localLoginRequest.playerName = str1;
    localLoginRequest.version = f;
    localLoginRequest.sessionId = str2;
    localLoginRequest.sessionName = paramArrayOfObject;
    localLoginRequest.field_2069 = i;
    localLoginRequest.serverProcessor = paramServerProcessor;
    localLoginRequest.packetid = paramShort;
    localLoginRequest.login = this;
    paramServerStateInterface.addLoginRequest(localLoginRequest);
    System.err.println("[SERVER][LOGIN] return code 0");
  }
  
  public void writeAndCommitParametriziedCommand(Object[] paramArrayOfObject, int paramInt1, int paramInt2, short paramShort, NetworkProcessor paramNetworkProcessor)
  {
    this.started = System.currentTimeMillis();
    super.writeAndCommitParametriziedCommand(paramArrayOfObject, paramInt1, paramInt2, paramShort, paramNetworkProcessor);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.commands.Login
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */