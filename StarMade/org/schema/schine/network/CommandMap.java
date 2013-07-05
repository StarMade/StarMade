/*     */ package org.schema.schine.network;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import k;
/*     */ import zZ;
/*     */ 
/*     */ public class CommandMap
/*     */ {
/*     */   private HashMap classMap;
/*     */   private HashMap idCommandMap;
/*     */ 
/*     */   public static HashMap parseCommands(String paramString, HashMap paramHashMap)
/*     */   {
/*  71 */     HashMap localHashMap = new HashMap();
/*     */     Object localObject;
/*     */     try
/*     */     {
/*  74 */       localHashMap = zZ.a.a(paramString);
/*  75 */       paramHashMap.putAll(localHashMap);
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/*  80 */       System.err.println("Commmand Map Parsing failed: " + localException.getMessage());
/*  81 */       System.err.println("[BACKUP]: retrieving commands from filesystem failed. trying jar... ");
/*     */ 
/*  83 */       localObject = new File("./");
/*  84 */       System.err.println(((File)localObject).getAbsolutePath());
/*  85 */       System.err.println(Arrays.toString(((File)localObject).list()));
/*     */     }
/*     */ 
/*  90 */     if (localHashMap.isEmpty()) {
/*  91 */       System.err.println("################# Loading from JAR #####################");
/*     */ 
/*  93 */       System.err.println("########################################################");
/*     */ 
/*  95 */       localObject = zZ.a.a(paramString);
/*     */       try
/*     */       {
/*  98 */         for (localObject = ((List)localObject).iterator(); ((Iterator)localObject).hasNext(); )
/*     */         {
/* 103 */           if (((
/* 103 */             paramString = ((Class)((Iterator)localObject).next())
/* 101 */             .newInstance()) instanceof Command))
/*     */           {
/* 104 */             paramString = (Command)paramString;
/*     */ 
/* 106 */             paramHashMap.put(paramString.getClass(), paramString);
/*     */           }
/*     */         }
/*     */ 
/*     */       }
/*     */       catch (InstantiationException localInstantiationException)
/*     */       {
/* 113 */         localInstantiationException.printStackTrace();
/*     */       }
/*     */       catch (IllegalAccessException localIllegalAccessException)
/*     */       {
/* 113 */         localIllegalAccessException.printStackTrace();
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 117 */     return paramHashMap;
/*     */   }
/*     */ 
/*     */   public CommandMap()
/*     */   {
/* 132 */     this.classMap = new HashMap();
/* 133 */     this.idCommandMap = new HashMap();
/*     */   }
/*     */ 
/*     */   public void add(Command paramCommand)
/*     */   {
/* 138 */     this.classMap.put(paramCommand.getClass(), paramCommand);
/*     */   }
/*     */ 
/*     */   public void addCommandId(Command paramCommand) {
/* 142 */     this.idCommandMap.put(Byte.valueOf(paramCommand.getId()), paramCommand);
/*     */   }
/*     */ 
/*     */   public void addCommandPath(String paramString) {
/* 146 */     parseCommands(paramString, this.classMap);
/*     */   }
/*     */   public Command getByClass(Class paramClass) {
/* 149 */     return (Command)this.classMap.get(paramClass);
/*     */   }
/*     */   public Command getById(byte paramByte) {
/* 152 */     assert (this.idCommandMap.containsKey(Byte.valueOf(paramByte))) : ("NOT FOUND " + paramByte + " in " + this.idCommandMap);
/* 153 */     return (Command)this.idCommandMap.get(Byte.valueOf(paramByte));
/*     */   }
/*     */   public void getByString() {
/*     */   }
/*     */ 
/*     */   public Collection values() {
/* 159 */     return this.classMap.values();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.CommandMap
 * JD-Core Version:    0.6.2
 */