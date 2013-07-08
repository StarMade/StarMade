/*   1:    */package org.schema.schine.network.commands;
/*   2:    */
/*   3:    */import java.io.PrintStream;
/*   4:    */import org.schema.schine.network.Command;
/*   5:    */import org.schema.schine.network.IdGen;
/*   6:    */import org.schema.schine.network.NetworkProcessor;
/*   7:    */import org.schema.schine.network.RegisteredClientOnServer;
/*   8:    */import org.schema.schine.network.client.ClientStateInterface;
/*   9:    */import org.schema.schine.network.server.ServerControllerInterface;
/*  10:    */import org.schema.schine.network.server.ServerProcessor;
/*  11:    */import org.schema.schine.network.server.ServerStateInterface;
/*  12:    */
/*  64:    */public class LoginAdmin
/*  65:    */  extends Command
/*  66:    */{
/*  67:    */  public static final int SUCCESS_LOGGED_IN = 0;
/*  68:    */  public static final int ERROR_GENRAL_ERROR = -1;
/*  69:    */  public static final int ERROR_ALREADY_LOGGED_IN = -2;
/*  70:    */  public static final int ERROR_ACCESS_DENIED = -3;
/*  71:    */  public static final int ERROR_SERVER_FULL = -4;
/*  72:    */  public static final int ERROR_WRONG_CLIENT_VERSION = -5;
/*  73:    */  public static final int ERROR_YOU_ARE_BANNED = -6;
/*  74:    */  public static final int ERROR_AUTHENTICATION_FAILED = -7;
/*  75:    */  public static final int NOT_LOGGED_IN = -4242;
/*  76:    */  private long started;
/*  77:    */  
/*  78:    */  public LoginAdmin()
/*  79:    */  {
/*  80: 80 */    this.mode = 1;
/*  81:    */  }
/*  82:    */  
/*  83:    */  public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort)
/*  84:    */  {
/*  85: 85 */    paramShort = ((Integer)paramArrayOfObject[0]).intValue();
/*  86: 86 */    float f = ((Float)paramArrayOfObject[3]).floatValue();
/*  87: 87 */    paramClientStateInterface.setServerVersion(f);
/*  88: 88 */    if (paramShort < 0) {
/*  89: 89 */      switch (paramShort) {
/*  90:    */      case -2: 
/*  91: 91 */        System.err.println("[Client] [LOGIN]: ERROR: Already logged in");
/*  92: 92 */        paramClientStateInterface.setId(paramShort);
/*  93: 93 */        return;
/*  94:    */      case -7: 
/*  95: 95 */        System.err.println("[Client] [LOGIN]: ERROR: Authentication Failed");
/*  96: 96 */        paramClientStateInterface.setId(paramShort);
/*  97: 97 */        return;
/*  98:    */      case -3: 
/*  99: 99 */        System.err.println("[Client] [LOGIN]: ERROR: Access Denied");
/* 100:100 */        paramClientStateInterface.setId(paramShort);
/* 101:101 */        return;
/* 102:    */      case -1: 
/* 103:103 */        System.err.println("[Client] [LOGIN]: ERROR: General Error");
/* 104:104 */        paramClientStateInterface.setId(paramShort);
/* 105:105 */        return;
/* 106:    */      case -5: 
/* 107:107 */        System.err.println("[Client] [LOGIN]: ERROR: The version of your client is too old. Try updating with the StarMade-Starter. (server version: " + f + ")");
/* 108:108 */        paramClientStateInterface.setId(paramShort);
/* 109:109 */        return;
/* 110:    */      case -4: 
/* 111:111 */        System.err.println("[Client] [LOGIN]: ERROR: Server FULL Error");
/* 112:112 */        paramClientStateInterface.setId(paramShort);
/* 113:113 */        return;
/* 114:    */      case -6: 
/* 115:115 */        System.err.println("[Client] [LOGIN]: ERROR: You are banned from this server");
/* 116:116 */        paramClientStateInterface.setId(paramShort);
/* 117:117 */        return;
/* 118:    */      }
/* 119:119 */      if (!$assertionsDisabled) throw new AssertionError("something went wrong: " + paramShort);
/* 120:    */    }
/* 121:    */    else
/* 122:    */    {
/* 123:123 */      paramClientStateInterface.setId(((Integer)paramArrayOfObject[1]).intValue());
/* 124:    */      
/* 126:126 */      long l = System.currentTimeMillis() - this.started;
/* 127:    */      
/* 132:132 */      paramClientStateInterface.setServerTimeOnLogin(((Long)paramArrayOfObject[2]).longValue() + l / 2L);
/* 133:    */      
/* 134:134 */      System.err.println("[Client] [LOGIN]: Client sucessfully registered with id: " + paramClientStateInterface.getId());
/* 135:    */    }
/* 136:    */  }
/* 137:    */  
/* 141:    */  public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
/* 142:    */  {
/* 143:143 */    String str1 = (String)paramArrayOfObject[0];
/* 144:144 */    float f1 = paramArrayOfObject.length > 1 ? ((Float)paramArrayOfObject[1]).floatValue() : 0.0F;
/* 145:145 */    String str2 = paramArrayOfObject.length > 2 ? (String)paramArrayOfObject[2] : "";
/* 146:146 */    String str3 = paramArrayOfObject.length > 3 ? (String)paramArrayOfObject[3] : "";
/* 147:147 */    int i = IdGen.getFreeStateId();
/* 148:    */    
/* 149:149 */    if (paramArrayOfObject.length > 4)
/* 150:    */    {
/* 152:152 */      paramArrayOfObject = -5;
/* 153:    */    }
/* 154:    */    else {
/* 155:155 */      System.err.println("[SERVER][LOGIN] new client connected. given id: " + i + ": description: " + str1);
/* 156:    */      
/* 157:    */      RegisteredClientOnServer localRegisteredClientOnServer;
/* 158:    */      
/* 159:159 */      (localRegisteredClientOnServer = new RegisteredClientOnServer(i, str1, paramServerStateInterface)).setProcessor(paramServerProcessor);
/* 160:160 */      paramServerProcessor.setClient(localRegisteredClientOnServer);
/* 161:    */      
/* 162:162 */      if (!paramServerStateInterface.getController().authenticate(str1, str2, str3)) {
/* 163:163 */        paramArrayOfObject = -7;
/* 164:    */      } else {
/* 165:165 */        paramServerStateInterface.getController().protectUserName(str1, str2, str3);
/* 166:166 */        paramArrayOfObject = paramServerStateInterface.getController().registerClient(localRegisteredClientOnServer, f1);
/* 167:    */      }
/* 168:168 */      System.err.println("[SERVER][LOGIN] return code " + paramArrayOfObject);
/* 169:    */    }
/* 170:    */    
/* 173:173 */    if (paramArrayOfObject < 0) {
/* 174:174 */      System.err.println("[SERVER][LOGIN] login failed (" + paramArrayOfObject + "): SET CLIENT TO NULL");
/* 175:175 */      paramServerProcessor.setClient(null);
/* 176:    */    }
/* 177:    */    
/* 183:183 */    float f2 = paramServerStateInterface.getVersion();
/* 184:184 */    System.out.println("[SERVER][LOGIN] login received. returning login info for " + paramServerProcessor.getClient() + ": returnCode: " + paramArrayOfObject);
/* 185:185 */    paramServerStateInterface.getController().broadcastMessage(str1 + " has joined the game", 0);
/* 186:186 */    createReturnToClient(paramServerStateInterface, paramServerProcessor, paramShort, new Object[] { Integer.valueOf(paramArrayOfObject), Integer.valueOf(i), Long.valueOf(System.currentTimeMillis()), Float.valueOf(f2) });
/* 187:187 */    if (paramArrayOfObject < 0) {
/* 188:188 */      paramServerProcessor.disconnectDelayed();
/* 189:189 */      paramServerStateInterface.getController().broadcastMessage(str1 + "'s connection failed (" + paramArrayOfObject + ")", 0);
/* 190:    */    }
/* 191:    */  }
/* 192:    */  
/* 194:    */  public void writeAndCommitParametriziedCommand(Object[] paramArrayOfObject, int paramInt1, int paramInt2, short paramShort, NetworkProcessor paramNetworkProcessor)
/* 195:    */  {
/* 196:196 */    this.started = System.currentTimeMillis();
/* 197:    */    
/* 198:198 */    super.writeAndCommitParametriziedCommand(paramArrayOfObject, paramInt1, paramInt2, paramShort, paramNetworkProcessor);
/* 199:    */  }
/* 200:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.commands.LoginAdmin
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */