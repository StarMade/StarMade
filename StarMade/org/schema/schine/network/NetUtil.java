/*     */ package org.schema.schine.network;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import org.schema.schine.network.commands.ExecuteAdminCommand;
/*     */ import org.schema.schine.network.commands.GetInfo;
/*     */ import org.schema.schine.network.commands.GetNextFreeObjectId;
/*     */ import org.schema.schine.network.commands.Login;
/*     */ import org.schema.schine.network.commands.LoginAdmin;
/*     */ import org.schema.schine.network.commands.LogoutClient;
/*     */ import org.schema.schine.network.commands.MessageTo;
/*     */ import org.schema.schine.network.commands.Pause;
/*     */ import org.schema.schine.network.commands.RequestServerTime;
/*     */ import org.schema.schine.network.commands.RequestSynchronizeAll;
/*     */ import org.schema.schine.network.commands.Synchronize;
/*     */ import org.schema.schine.network.commands.SynchronizePrivateChannel;
/*     */ 
/*     */ public class NetUtil
/*     */ {
/*  70 */   public static final int[] RECEIVER_ALL = { -1 };
/*     */ 
/*  73 */   public static final int[] RECEIVER_SERVER = { 0 };
/*     */   public static final long MIN_MS_BETWEEN_UPDATES = 30L;
/*     */   public static final float MAX_MS_BETWEEN_UPDATES = 100.0F;
/*  79 */   public static final CommandMap commands = loadDefaultCommands();
/*     */ 
/*  81 */   private static final HashMap registeredSendableClasses = new HashMap();
/*  82 */   private static final HashMap registeredSendableClassesKey = new HashMap();
/*     */   public static final byte TYPE_INT = 1;
/*     */   public static final byte TYPE_LONG = 2;
/*     */   public static final byte TYPE_FLOAT = 3;
/*     */   public static final byte TYPE_STRING = 4;
/*     */   public static final byte TYPE_BOOLEAN = 5;
/*     */   public static final byte TYPE_BYTE = 6;
/*     */   public static final byte TYPE_SHORT = 7;
/*     */   public static final byte TYPE_BYTE_ARRAY = 8;
/*     */   public static final byte COMMAND_PING = -1;
/*     */   public static final byte COMMAND_PONG = -2;
/*  96 */   public static final byte[] COMMAND_SUCCESS_DO_NOTHING = { -3 };
/*     */   public static final long WAIT_TIMEOUT = 60000L;
/*     */   public static final int SOCKET_BUFFER_SIZE = 1048576;
/* 105 */   private static byte sendableClassKeyGen = -128;
/*     */ 
/*     */   public static void addCommandPath(String paramString) {
/* 108 */     commands.addCommandPath(paramString);
/*     */   }
/*     */ 
/*     */   public static void addSendableClass(Class paramClass, Class[] paramArrayOfClass)
/*     */   {
/*     */     try
/*     */     {
/* 118 */       paramClass.getConstructor(paramArrayOfClass);
/* 119 */       assert (registeredSendableClasses.size() < 254) : "time to change to short keys. Byte.MaxValue is reserved as errorcode";
/* 120 */       if (!registeredSendableClasses.containsKey(paramClass)) {
/* 121 */         paramArrayOfClass = sendableClassKeyGen++;
/*     */ 
/* 123 */         registeredSendableClasses.put(Byte.valueOf(paramArrayOfClass), paramClass);
/* 124 */         registeredSendableClassesKey.put(paramClass, Byte.valueOf(paramArrayOfClass));
/*     */       }return; } catch (SecurityException localSecurityException) {
/* 126 */       (
/* 127 */         paramArrayOfClass = 
/* 138 */         localSecurityException).printStackTrace();
/* 128 */       System.err.println("Exiting because of exception " + paramArrayOfClass);
/* 129 */       System.exit(-1);
/*     */       return;
/*     */     }
/*     */     catch (NoSuchMethodException localNoSuchMethodException)
/*     */     {
/* 138 */       localNoSuchMethodException.printStackTrace();
/*     */ 
/* 132 */       System.err.println("\n----------------------------------------\n[CRITICAL ERROR]Constructor for sendable class not provided. \nplease implement: public <Sendable>(StateInterface state) constructor for " + paramClass.getName());
/*     */ 
/* 137 */       System.exit(-1);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void assignCommandIds() {
/* 142 */     LinkedList localLinkedList = new LinkedList();
/* 143 */     for (Iterator localIterator1 = commands.values().iterator(); localIterator1.hasNext(); ) { localObject = (Command)localIterator1.next();
/* 144 */       localLinkedList.add(localObject.getClass().getName());
/*     */     }
/*     */     Object localObject;
/* 146 */     Collections.sort(localLinkedList);
/* 147 */     assert (localLinkedList.size() < 127);
/*     */     Iterator localIterator2;
/* 148 */     for (int i = 0; i < localLinkedList.size(); i = (byte)(i + 1)) {
/* 149 */       localObject = (String)localLinkedList.get(i);
/* 150 */       for (localIterator2 = commands.values().iterator(); localIterator2.hasNext(); )
/*     */       {
/*     */         Command localCommand;
/* 151 */         if ((
/* 151 */           localCommand = (Command)localIterator2.next())
/* 151 */           .getClass().getName().equals(localObject)) {
/* 152 */           if (localCommand.getClass().equals(Login.class))
/*     */           {
/* 154 */             localCommand.setId((byte)0);
/* 155 */           } else if (localCommand.getClass().equals(GetInfo.class))
/*     */           {
/* 157 */             localCommand.setId((byte)1);
/* 158 */           } else if (localCommand.getClass().equals(ExecuteAdminCommand.class))
/*     */           {
/* 160 */             localCommand.setId((byte)2);
/*     */           }
/* 162 */           else localCommand.setId((byte)(i + 3));
/*     */ 
/* 164 */           commands.addCommandId(localCommand);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static Class getSendableClass(byte paramByte)
/*     */   {
/* 172 */     assert (registeredSendableClasses.containsKey(Byte.valueOf(paramByte))) : (" NOT FOUND: " + paramByte + " in " + registeredSendableClasses);
/* 173 */     return (Class)registeredSendableClasses.get(Byte.valueOf(paramByte));
/*     */   }
/*     */ 
/*     */   public static byte getSendableKey(Class paramClass) {
/* 177 */     assert (registeredSendableClassesKey.containsKey(paramClass)) : (" NOT FOUND: " + paramClass + " in " + registeredSendableClassesKey);
/* 178 */     return ((Byte)registeredSendableClassesKey.get(paramClass)).byteValue();
/*     */   }
/*     */ 
/*     */   private static CommandMap loadDefaultCommands()
/*     */   {
/*     */     CommandMap localCommandMap;
/* 182 */     (
/* 183 */       localCommandMap = new CommandMap())
/* 183 */       .add(new Login());
/* 184 */     localCommandMap.add(new GetInfo());
/* 185 */     localCommandMap.add(new ExecuteAdminCommand());
/* 186 */     localCommandMap.add(new LogoutClient());
/* 187 */     localCommandMap.add(new MessageTo());
/* 188 */     localCommandMap.add(new Pause());
/* 189 */     localCommandMap.add(new Synchronize());
/* 190 */     localCommandMap.add(new SynchronizePrivateChannel());
/* 191 */     localCommandMap.add(new RequestSynchronizeAll());
/* 192 */     localCommandMap.add(new GetNextFreeObjectId());
/* 193 */     localCommandMap.add(new RequestServerTime());
/* 194 */     localCommandMap.add(new LoginAdmin());
/*     */ 
/* 200 */     return localCommandMap;
/*     */   }
/*     */ 
/*     */   public static void serverProcess()
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.NetUtil
 * JD-Core Version:    0.6.2
 */