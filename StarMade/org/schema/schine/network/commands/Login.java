/*   1:    */package org.schema.schine.network.commands;
/*   2:    */
/*   3:    */import java.io.PrintStream;
/*   4:    */import org.schema.schine.network.Command;
/*   5:    */import org.schema.schine.network.IdGen;
/*   6:    */import org.schema.schine.network.NetworkProcessor;
/*   7:    */import org.schema.schine.network.client.ClientStateInterface;
/*   8:    */import org.schema.schine.network.server.ServerProcessor;
/*   9:    */import org.schema.schine.network.server.ServerStateInterface;
/*  10:    */
/*  66:    */public class Login
/*  67:    */  extends Command
/*  68:    */{
/*  69:    */  public static final int SUCCESS_LOGGED_IN = 0;
/*  70:    */  public static final int ERROR_GENRAL_ERROR = -1;
/*  71:    */  public static final int ERROR_ALREADY_LOGGED_IN = -2;
/*  72:    */  public static final int ERROR_ACCESS_DENIED = -3;
/*  73:    */  public static final int ERROR_SERVER_FULL = -4;
/*  74:    */  public static final int ERROR_WRONG_CLIENT_VERSION = -5;
/*  75:    */  public static final int ERROR_YOU_ARE_BANNED = -6;
/*  76:    */  public static final int ERROR_AUTHENTICATION_FAILED = -7;
/*  77:    */  public static final int ERROR_NOT_ON_WHITELIST = -8;
/*  78:    */  public static final int ERROR_INVALID_USERNAME = -9;
/*  79:    */  public static final int NOT_LOGGED_IN = -4242;
/*  80:    */  private long started;
/*  81:    */  
/*  82:    */  public Login()
/*  83:    */  {
/*  84: 84 */    this.mode = 1;
/*  85:    */  }
/*  86:    */  
/*  87:    */  public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort)
/*  88:    */  {
/*  89: 89 */    paramShort = ((Integer)paramArrayOfObject[0]).intValue();
/*  90: 90 */    float f = ((Float)paramArrayOfObject[3]).floatValue();
/*  91: 91 */    paramClientStateInterface.setServerVersion(f);
/*  92: 92 */    if (paramShort < 0) {
/*  93: 93 */      switch (paramShort) {
/*  94:    */      case -2: 
/*  95: 95 */        System.err.println("[Client] [LOGIN]: ERROR: Already logged in");
/*  96: 96 */        paramClientStateInterface.setId(paramShort);
/*  97: 97 */        return;
/*  98:    */      case -7: 
/*  99: 99 */        System.err.println("[Client] [LOGIN]: ERROR: Authentication Failed");
/* 100:100 */        paramClientStateInterface.setId(paramShort);
/* 101:101 */        return;
/* 102:    */      case -3: 
/* 103:103 */        System.err.println("[Client] [LOGIN]: ERROR: Access Denied");
/* 104:104 */        paramClientStateInterface.setId(paramShort);
/* 105:105 */        return;
/* 106:    */      case -1: 
/* 107:107 */        System.err.println("[Client] [LOGIN]: ERROR: General Error");
/* 108:108 */        paramClientStateInterface.setId(paramShort);
/* 109:109 */        return;
/* 110:    */      case -5: 
/* 111:111 */        System.err.println("[Client] [LOGIN]: ERROR: The version of your client is too old. Try updating with the StarMade-Starter. (server version: " + f + ")");
/* 112:112 */        paramClientStateInterface.setId(paramShort);
/* 113:113 */        return;
/* 114:    */      case -4: 
/* 115:115 */        System.err.println("[Client] [LOGIN]: ERROR: Server FULL Error");
/* 116:116 */        paramClientStateInterface.setId(paramShort);
/* 117:117 */        return;
/* 118:    */      case -6: 
/* 119:119 */        System.err.println("[Client] [LOGIN]: ERROR: You are banned from this server");
/* 120:120 */        paramClientStateInterface.setId(paramShort);
/* 121:121 */        return;
/* 122:    */      case -8: 
/* 123:123 */        System.err.println("[Client] [LOGIN]: ERROR: You are not whitelisted on this server");
/* 124:124 */        paramClientStateInterface.setId(paramShort);
/* 125:125 */        return;
/* 126:    */      case -9: 
/* 127:127 */        System.err.println("[Client] [LOGIN]: ERROR: The username is not accepted");
/* 128:128 */        paramClientStateInterface.setId(paramShort);
/* 129:129 */        return;
/* 130:    */      }
/* 131:131 */      if (!$assertionsDisabled) throw new AssertionError("something went wrong: " + paramShort);
/* 132:    */    }
/* 133:    */    else
/* 134:    */    {
/* 135:135 */      paramClientStateInterface.setId(((Integer)paramArrayOfObject[1]).intValue());
/* 136:    */      
/* 138:138 */      long l = System.currentTimeMillis() - this.started;
/* 139:    */      
/* 144:144 */      paramClientStateInterface.setServerTimeOnLogin(((Long)paramArrayOfObject[2]).longValue() + l / 2L);
/* 145:    */      
/* 146:146 */      System.err.println("[Client] [LOGIN]: Client sucessfully registered with id: " + paramClientStateInterface.getId());
/* 147:    */    }
/* 148:    */  }
/* 149:    */  
/* 154:    */  public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
/* 155:    */  {
/* 156:156 */    LoginRequest localLoginRequest = new LoginRequest();
/* 157:    */    
/* 160:160 */    String str1 = (String)paramArrayOfObject[0];
/* 161:161 */    float f = paramArrayOfObject.length > 1 ? ((Float)paramArrayOfObject[1]).floatValue() : 0.0F;
/* 162:162 */    String str2 = paramArrayOfObject.length > 2 ? (String)paramArrayOfObject[2] : "";
/* 163:163 */    paramArrayOfObject = paramArrayOfObject.length > 3 ? (String)paramArrayOfObject[3] : "";
/* 164:    */    
/* 165:165 */    int i = IdGen.getFreeStateId();
/* 166:    */    
/* 169:169 */    System.err.println("[SERVER][LOGIN] new client connected. given id: " + i + ": description: " + str1);
/* 170:    */    
/* 171:171 */    localLoginRequest.state = paramServerStateInterface;
/* 172:172 */    localLoginRequest.playerName = str1;
/* 173:173 */    localLoginRequest.version = f;
/* 174:174 */    localLoginRequest.sessionId = str2;
/* 175:175 */    localLoginRequest.sessionName = paramArrayOfObject;
/* 176:176 */    localLoginRequest.id = i;
/* 177:177 */    localLoginRequest.serverProcessor = paramServerProcessor;
/* 178:178 */    localLoginRequest.packetid = paramShort;
/* 179:179 */    localLoginRequest.login = this;
/* 180:    */    
/* 181:181 */    paramServerStateInterface.addLoginRequest(localLoginRequest);
/* 182:    */    
/* 183:183 */    System.err.println("[SERVER][LOGIN] return code 0");
/* 184:    */  }
/* 185:    */  
/* 195:    */  public void writeAndCommitParametriziedCommand(Object[] paramArrayOfObject, int paramInt1, int paramInt2, short paramShort, NetworkProcessor paramNetworkProcessor)
/* 196:    */  {
/* 197:197 */    this.started = System.currentTimeMillis();
/* 198:    */    
/* 199:199 */    super.writeAndCommitParametriziedCommand(paramArrayOfObject, paramInt1, paramInt2, paramShort, paramNetworkProcessor);
/* 200:    */  }
/* 201:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.commands.Login
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */