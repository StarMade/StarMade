package org.schema.schine.network;

import java.io.PrintStream;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import org.schema.schine.network.commands.ExecuteAdminCommand;
import org.schema.schine.network.commands.GetInfo;
import org.schema.schine.network.commands.GetNextFreeObjectId;
import org.schema.schine.network.commands.Login;
import org.schema.schine.network.commands.LoginAdmin;
import org.schema.schine.network.commands.LogoutClient;
import org.schema.schine.network.commands.MessageTo;
import org.schema.schine.network.commands.Pause;
import org.schema.schine.network.commands.RequestServerTime;
import org.schema.schine.network.commands.RequestSynchronizeAll;
import org.schema.schine.network.commands.Synchronize;
import org.schema.schine.network.commands.SynchronizePrivateChannel;

public class NetUtil
{
  public static final int[] RECEIVER_ALL = { -1 };
  public static final int[] RECEIVER_SERVER = { 0 };
  public static final long MIN_MS_BETWEEN_UPDATES = 30L;
  public static final float MAX_MS_BETWEEN_UPDATES = 100.0F;
  public static final CommandMap commands = loadDefaultCommands();
  private static final HashMap registeredSendableClasses = new HashMap();
  private static final HashMap registeredSendableClassesKey = new HashMap();
  public static final byte TYPE_INT = 1;
  public static final byte TYPE_LONG = 2;
  public static final byte TYPE_FLOAT = 3;
  public static final byte TYPE_STRING = 4;
  public static final byte TYPE_BOOLEAN = 5;
  public static final byte TYPE_BYTE = 6;
  public static final byte TYPE_SHORT = 7;
  public static final byte TYPE_BYTE_ARRAY = 8;
  public static final byte COMMAND_PING = -1;
  public static final byte COMMAND_PONG = -2;
  public static final byte[] COMMAND_SUCCESS_DO_NOTHING = { -3 };
  public static final long WAIT_TIMEOUT = 60000L;
  public static final int SOCKET_BUFFER_SIZE = 1048576;
  private static byte sendableClassKeyGen = -128;
  
  public static void addCommandPath(String paramString)
  {
    commands.addCommandPath(paramString);
  }
  
  public static void addSendableClass(Class paramClass, Class... paramVarArgs)
  {
    try
    {
      paramClass.getConstructor(paramVarArgs);
      assert (registeredSendableClasses.size() < 254) : "time to change to short keys. Byte.MaxValue is reserved as errorcode";
      if (!registeredSendableClasses.containsKey(paramClass))
      {
        paramVarArgs = sendableClassKeyGen++;
        registeredSendableClasses.put(Byte.valueOf(paramVarArgs), paramClass);
        registeredSendableClassesKey.put(paramClass, Byte.valueOf(paramVarArgs));
      }
      return;
    }
    catch (SecurityException localSecurityException)
    {
      (paramVarArgs = localSecurityException).printStackTrace();
      System.err.println("Exiting because of exception " + paramVarArgs);
      System.exit(-1);
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      localNoSuchMethodException.printStackTrace();
      System.err.println("\n----------------------------------------\n[CRITICAL ERROR]Constructor for sendable class not provided. \nplease implement: public <Sendable>(StateInterface state) constructor for " + paramClass.getName());
      System.exit(-1);
    }
  }
  
  public static void assignCommandIds()
  {
    LinkedList localLinkedList = new LinkedList();
    Iterator localIterator1 = commands.values().iterator();
    Object localObject;
    while (localIterator1.hasNext())
    {
      localObject = (Command)localIterator1.next();
      localLinkedList.add(localObject.getClass().getName());
    }
    Collections.sort(localLinkedList);
    assert (localLinkedList.size() < 127);
    for (int i = 0; i < localLinkedList.size(); i = (byte)(i + 1))
    {
      localObject = (String)localLinkedList.get(i);
      Iterator localIterator2 = commands.values().iterator();
      while (localIterator2.hasNext())
      {
        Command localCommand;
        if ((localCommand = (Command)localIterator2.next()).getClass().getName().equals(localObject))
        {
          if (localCommand.getClass().equals(Login.class)) {
            localCommand.setId((byte)0);
          } else if (localCommand.getClass().equals(GetInfo.class)) {
            localCommand.setId((byte)1);
          } else if (localCommand.getClass().equals(ExecuteAdminCommand.class)) {
            localCommand.setId((byte)2);
          } else {
            localCommand.setId((byte)(i + 3));
          }
          commands.addCommandId(localCommand);
        }
      }
    }
  }
  
  public static Class getSendableClass(byte paramByte)
  {
    assert (registeredSendableClasses.containsKey(Byte.valueOf(paramByte))) : (" NOT FOUND: " + paramByte + " in " + registeredSendableClasses);
    return (Class)registeredSendableClasses.get(Byte.valueOf(paramByte));
  }
  
  public static byte getSendableKey(Class paramClass)
  {
    assert (registeredSendableClassesKey.containsKey(paramClass)) : (" NOT FOUND: " + paramClass + " in " + registeredSendableClassesKey);
    return ((Byte)registeredSendableClassesKey.get(paramClass)).byteValue();
  }
  
  private static CommandMap loadDefaultCommands()
  {
    CommandMap localCommandMap;
    (localCommandMap = new CommandMap()).add(new Login());
    localCommandMap.add(new GetInfo());
    localCommandMap.add(new ExecuteAdminCommand());
    localCommandMap.add(new LogoutClient());
    localCommandMap.add(new MessageTo());
    localCommandMap.add(new Pause());
    localCommandMap.add(new Synchronize());
    localCommandMap.add(new SynchronizePrivateChannel());
    localCommandMap.add(new RequestSynchronizeAll());
    localCommandMap.add(new GetNextFreeObjectId());
    localCommandMap.add(new RequestServerTime());
    localCommandMap.add(new LoginAdmin());
    return localCommandMap;
  }
  
  public static void serverProcess() {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.NetUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */