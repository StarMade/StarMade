/*   1:    */package org.schema.schine.network;
/*   2:    */
/*   3:    */import java.io.PrintStream;
/*   4:    */import java.util.Collection;
/*   5:    */import java.util.Collections;
/*   6:    */import java.util.HashMap;
/*   7:    */import java.util.Iterator;
/*   8:    */import java.util.LinkedList;
/*   9:    */import org.schema.schine.network.commands.ExecuteAdminCommand;
/*  10:    */import org.schema.schine.network.commands.GetInfo;
/*  11:    */import org.schema.schine.network.commands.GetNextFreeObjectId;
/*  12:    */import org.schema.schine.network.commands.Login;
/*  13:    */import org.schema.schine.network.commands.LoginAdmin;
/*  14:    */import org.schema.schine.network.commands.LogoutClient;
/*  15:    */import org.schema.schine.network.commands.MessageTo;
/*  16:    */import org.schema.schine.network.commands.Pause;
/*  17:    */import org.schema.schine.network.commands.RequestServerTime;
/*  18:    */import org.schema.schine.network.commands.RequestSynchronizeAll;
/*  19:    */import org.schema.schine.network.commands.Synchronize;
/*  20:    */import org.schema.schine.network.commands.SynchronizePrivateChannel;
/*  21:    */
/*  68:    */public class NetUtil
/*  69:    */{
/*  70: 70 */  public static final int[] RECEIVER_ALL = { -1 };
/*  71:    */  
/*  73: 73 */  public static final int[] RECEIVER_SERVER = { 0 };
/*  74:    */  
/*  75:    */  public static final long MIN_MS_BETWEEN_UPDATES = 30L;
/*  76:    */  
/*  77:    */  public static final float MAX_MS_BETWEEN_UPDATES = 100.0F;
/*  78:    */  
/*  79: 79 */  public static final CommandMap commands = loadDefaultCommands();
/*  80:    */  
/*  81: 81 */  private static final HashMap registeredSendableClasses = new HashMap();
/*  82: 82 */  private static final HashMap registeredSendableClassesKey = new HashMap();
/*  83:    */  
/*  84:    */  public static final byte TYPE_INT = 1;
/*  85:    */  
/*  86:    */  public static final byte TYPE_LONG = 2;
/*  87:    */  
/*  88:    */  public static final byte TYPE_FLOAT = 3;
/*  89:    */  public static final byte TYPE_STRING = 4;
/*  90:    */  public static final byte TYPE_BOOLEAN = 5;
/*  91:    */  public static final byte TYPE_BYTE = 6;
/*  92:    */  public static final byte TYPE_SHORT = 7;
/*  93:    */  public static final byte TYPE_BYTE_ARRAY = 8;
/*  94:    */  public static final byte COMMAND_PING = -1;
/*  95:    */  public static final byte COMMAND_PONG = -2;
/*  96: 96 */  public static final byte[] COMMAND_SUCCESS_DO_NOTHING = { -3 };
/*  97:    */  
/*  99:    */  public static final long WAIT_TIMEOUT = 60000L;
/* 100:    */  
/* 102:    */  public static final int SOCKET_BUFFER_SIZE = 1048576;
/* 103:    */  
/* 105:105 */  private static byte sendableClassKeyGen = -128;
/* 106:    */  
/* 107:    */  public static void addCommandPath(String paramString) {
/* 108:108 */    commands.addCommandPath(paramString);
/* 109:    */  }
/* 110:    */  
/* 114:    */  public static void addSendableClass(Class paramClass, Class... paramVarArgs)
/* 115:    */  {
/* 116:    */    try
/* 117:    */    {
/* 118:118 */      paramClass.getConstructor(paramVarArgs);
/* 119:119 */      assert (registeredSendableClasses.size() < 254) : "time to change to short keys. Byte.MaxValue is reserved as errorcode";
/* 120:120 */      if (!registeredSendableClasses.containsKey(paramClass)) {
/* 121:121 */        paramVarArgs = sendableClassKeyGen++;
/* 122:    */        
/* 123:123 */        registeredSendableClasses.put(Byte.valueOf(paramVarArgs), paramClass);
/* 124:124 */        registeredSendableClassesKey.put(paramClass, Byte.valueOf(paramVarArgs));
/* 125:    */      }
/* 126:    */      return;
/* 127:127 */    } catch (SecurityException localSecurityException) { (paramVarArgs = 
/* 128:    */      
/* 138:138 */        localSecurityException).printStackTrace();System.err.println("Exiting because of exception " + paramVarArgs);System.exit(-1); return;
/* 139:130 */    } catch (NoSuchMethodException localNoSuchMethodException) { 
/* 140:    */      
/* 147:138 */        localNoSuchMethodException.printStackTrace();System.err.println("\n----------------------------------------\n[CRITICAL ERROR]Constructor for sendable class not provided. \nplease implement: public <Sendable>(StateInterface state) constructor for " + paramClass.getName());System.exit(-1);
/* 148:    */    }
/* 149:    */  }
/* 150:    */  
/* 151:142 */  public static void assignCommandIds() { LinkedList localLinkedList = new LinkedList();
/* 152:143 */    for (Iterator localIterator1 = commands.values().iterator(); localIterator1.hasNext();) { localObject = (Command)localIterator1.next();
/* 153:144 */      localLinkedList.add(localObject.getClass().getName()); }
/* 154:    */    Object localObject;
/* 155:146 */    Collections.sort(localLinkedList);
/* 156:147 */    assert (localLinkedList.size() < 127);
/* 157:148 */    Iterator localIterator2; for (int i = 0; i < localLinkedList.size(); i = (byte)(i + 1)) {
/* 158:149 */      localObject = (String)localLinkedList.get(i);
/* 159:150 */      for (localIterator2 = commands.values().iterator(); localIterator2.hasNext();) { Command localCommand;
/* 160:151 */        if ((localCommand = (Command)localIterator2.next()).getClass().getName().equals(localObject)) {
/* 161:152 */          if (localCommand.getClass().equals(Login.class))
/* 162:    */          {
/* 163:154 */            localCommand.setId((byte)0);
/* 164:155 */          } else if (localCommand.getClass().equals(GetInfo.class))
/* 165:    */          {
/* 166:157 */            localCommand.setId((byte)1);
/* 167:158 */          } else if (localCommand.getClass().equals(ExecuteAdminCommand.class))
/* 168:    */          {
/* 169:160 */            localCommand.setId((byte)2);
/* 170:    */          } else {
/* 171:162 */            localCommand.setId((byte)(i + 3));
/* 172:    */          }
/* 173:164 */          commands.addCommandId(localCommand);
/* 174:    */        }
/* 175:    */      }
/* 176:    */    }
/* 177:    */  }
/* 178:    */  
/* 179:    */  public static Class getSendableClass(byte paramByte)
/* 180:    */  {
/* 181:172 */    assert (registeredSendableClasses.containsKey(Byte.valueOf(paramByte))) : (" NOT FOUND: " + paramByte + " in " + registeredSendableClasses);
/* 182:173 */    return (Class)registeredSendableClasses.get(Byte.valueOf(paramByte));
/* 183:    */  }
/* 184:    */  
/* 185:    */  public static byte getSendableKey(Class paramClass) {
/* 186:177 */    assert (registeredSendableClassesKey.containsKey(paramClass)) : (" NOT FOUND: " + paramClass + " in " + registeredSendableClassesKey);
/* 187:178 */    return ((Byte)registeredSendableClassesKey.get(paramClass)).byteValue();
/* 188:    */  }
/* 189:    */  
/* 190:    */  private static CommandMap loadDefaultCommands() {
/* 191:    */    CommandMap localCommandMap;
/* 192:183 */    (localCommandMap = new CommandMap()).add(new Login());
/* 193:184 */    localCommandMap.add(new GetInfo());
/* 194:185 */    localCommandMap.add(new ExecuteAdminCommand());
/* 195:186 */    localCommandMap.add(new LogoutClient());
/* 196:187 */    localCommandMap.add(new MessageTo());
/* 197:188 */    localCommandMap.add(new Pause());
/* 198:189 */    localCommandMap.add(new Synchronize());
/* 199:190 */    localCommandMap.add(new SynchronizePrivateChannel());
/* 200:191 */    localCommandMap.add(new RequestSynchronizeAll());
/* 201:192 */    localCommandMap.add(new GetNextFreeObjectId());
/* 202:193 */    localCommandMap.add(new RequestServerTime());
/* 203:194 */    localCommandMap.add(new LoginAdmin());
/* 204:    */    
/* 209:200 */    return localCommandMap;
/* 210:    */  }
/* 211:    */  
/* 212:    */  public static void serverProcess() {}
/* 213:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.NetUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */