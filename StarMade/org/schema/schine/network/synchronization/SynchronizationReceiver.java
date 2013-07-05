/*     */ package org.schema.schine.network.synchronization;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.bytes.ByteArrayList;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.schema.common.util.ByteUtil;
/*     */ import org.schema.schine.network.ControllerInterface;
/*     */ import org.schema.schine.network.NetUtil;
/*     */ import org.schema.schine.network.NetworkStateContainer;
/*     */ import org.schema.schine.network.StateInterface;
/*     */ import org.schema.schine.network.client.ClientStateInterface;
/*     */ import org.schema.schine.network.exception.SynchronizationException;
/*     */ import org.schema.schine.network.objects.NetworkObject;
/*     */ import org.schema.schine.network.objects.Sendable;
/*     */ import org.schema.schine.network.objects.remote.RemoteBoolean;
/*     */ import org.schema.schine.network.objects.remote.RemoteInteger;
/*     */ import org.schema.schine.network.server.ServerStateInterface;
/*     */ import zL;
/*     */ 
/*     */ public class SynchronizationReceiver
/*     */ {
/*  25 */   public static Class construcorStateClass = StateInterface.class;
/*     */   public static boolean serverDebug;
/*     */ 
/*     */   private static void handleChanged(NetworkStateContainer paramNetworkStateContainer, int paramInt1, StateInterface paramStateInterface, DataInputStream paramDataInputStream, short paramShort, int paramInt2)
/*     */   {
/*  30 */     Int2ObjectOpenHashMap localInt2ObjectOpenHashMap = paramNetworkStateContainer.getRemoteObjects();
/*  31 */     synchronized (
/*  33 */       localObject1 = paramNetworkStateContainer.getLocalObjects())
/*     */     {
/*  34 */       synchronized (localInt2ObjectOpenHashMap)
/*     */       {
/*     */         Object localObject1;
/*  37 */         NetworkObject localNetworkObject = (NetworkObject)localInt2ObjectOpenHashMap.get(paramInt1);
/*  38 */         assert (localNetworkObject != null) : ("could not find entity " + paramInt1 + "; available remote entities: " + localInt2ObjectOpenHashMap);
/*     */ 
/*  40 */         synchronized (localNetworkObject)
/*     */         {
/*  42 */           localObject1 = (Sendable)((Int2ObjectOpenHashMap)localObject1).get(paramInt1);
/*  43 */           localNetworkObject.id.set(Integer.valueOf(((Sendable)localObject1).getId()));
/*     */ 
/*  45 */           if ((localObject1.toString().startsWith("Missile")) && ((paramStateInterface instanceof ServerStateInterface))) {
/*  46 */             System.err.println("SERVER MISSILE UPDATE " + localObject1);
/*  47 */             serverDebug = true;
/*     */           }
/*     */ 
/*  54 */           localNetworkObject.decodeChange(paramStateInterface, paramDataInputStream, paramShort, paramNetworkStateContainer != paramStateInterface.getLocalAndRemoteObjectContainer(), paramInt2);
/*  55 */           assert (localObject1 != null) : ("Object with id " + paramInt1 + " is NOT local yet " + paramNetworkStateContainer.getLocalObjects());
/*     */ 
/*  57 */           ((Sendable)localObject1).updateFromNetworkObject(localNetworkObject);
/*  58 */           localNetworkObject.clearReceiveBuffers();
/*     */ 
/*  73 */           serverDebug = false;
/*     */         }
/*     */       }
/*     */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void handleDeleted(NetworkStateContainer paramNetworkStateContainer, StateInterface paramStateInterface, Collection paramCollection)
/*     */   {
/*  86 */     paramCollection.clear();
/*  87 */     synchronized (paramNetworkStateContainer.getLocalObjects())
/*     */     {
/*     */       Iterator localIterator;
/*     */       Object localObject1;
/*     */       Object localObject2;
/*  88 */       synchronized (paramNetworkStateContainer.getRemoteObjects())
/*     */       {
/*  90 */         for (localIterator = paramNetworkStateContainer.getLocalObjects().values().iterator(); localIterator.hasNext(); ) { localObject1 = (Sendable)localIterator.next();
/*     */ 
/*  94 */           if (((
/*  94 */             localObject2 = (NetworkObject)paramNetworkStateContainer.getRemoteObjects().get(((Sendable)localObject1).getId())) != null) && 
/*  94 */             (((Boolean)((NetworkObject)localObject2).markedDeleted.get()).booleanValue()))
/*     */           {
/*  96 */             if (((paramStateInterface instanceof ServerStateInterface)) && 
/*  97 */               (!((Sendable)localObject1).isMarkedForDeleteVolatileSent())) {
/*  98 */               System.err.println("[SERVER] delete not yet sent: " + localObject1);
/*     */             }
/*     */             else
/*     */             {
/* 102 */               paramCollection.add(Integer.valueOf(((Sendable)localObject1).getId()));
/*     */ 
/* 104 */               ((NetworkObject)localObject2).onDelete(paramStateInterface);
/*     */ 
/* 106 */               ((Sendable)localObject1).cleanUpOnEntityDelete();
/*     */ 
/* 108 */               paramNetworkStateContainer.getGhostObjects().put(((Sendable)localObject1).getId(), new GhostSendable(System.currentTimeMillis(), (Sendable)localObject1));
/*     */             }
/*     */           }
/*     */         }
/*     */ 
/* 113 */         if (paramCollection.size() > 0)
/*     */         {
/* 115 */           for (localIterator = paramCollection.iterator(); localIterator.hasNext(); ) { localObject1 = (Integer)localIterator.next();
/*     */ 
/* 119 */             localObject2 = paramNetworkStateContainer.removeLocal(((Integer)localObject1).intValue());
/* 120 */             paramNetworkStateContainer.getRemoteObjects().remove(localObject1);
/*     */ 
/* 122 */             paramStateInterface.getController().onRemoveEntity((Sendable)localObject2);
/* 123 */             paramStateInterface.notifyOfRemovedObject((Sendable)localObject2);
/* 124 */             System.err.println("[DELETE][" + paramStateInterface + "] Sendable " + localObject1 + "(" + localObject2 + ") Physically DELETING DONE and Notified!");
/*     */           }
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 132 */     paramNetworkStateContainer.checkGhostObjects();
/*     */   }
/*     */ 
/*     */   private static void handleNewObject(NetworkStateContainer paramNetworkStateContainer, int paramInt1, int paramInt2, byte paramByte, StateInterface paramStateInterface, DataInputStream paramDataInputStream, short paramShort, boolean paramBoolean)
/*     */   {
/* 147 */     Int2ObjectOpenHashMap localInt2ObjectOpenHashMap1 = paramNetworkStateContainer.getRemoteObjects();
/* 148 */     Int2ObjectOpenHashMap localInt2ObjectOpenHashMap2 = paramNetworkStateContainer.getLocalObjects();
/*     */ 
/* 150 */     Sendable localSendable = null;
/*     */ 
/* 157 */     synchronized (localInt2ObjectOpenHashMap2) {
/* 158 */       synchronized (localInt2ObjectOpenHashMap1)
/*     */       {
/* 160 */         if (localInt2ObjectOpenHashMap2.containsKey(paramInt1))
/*     */         {
/* 167 */           localSendable = (Sendable)localInt2ObjectOpenHashMap2.get(paramInt1);
/*     */         }
/*     */         else
/*     */         {
/*     */           Class localClass;
/* 173 */           if ((
/* 173 */             localClass = NetUtil.getSendableClass(paramByte)) == null)
/*     */           {
/* 174 */             throw new NullPointerException("WRONG CLASS ID RECEIVED: " + paramByte + "; container: " + paramNetworkStateContainer.getClass());
/*     */           }
/*     */ 
/* 186 */           localSendable = (Sendable)localClass.getConstructor(new Class[] { construcorStateClass })
/* 186 */             .newInstance(new Object[] { paramStateInterface });
/*     */         }
/*     */ 
/* 189 */         synchronized (localSendable) {
/* 190 */           long l1 = System.currentTimeMillis();
/*     */ 
/* 192 */           localSendable.initialize();
/* 193 */           localSendable.newNetworkObject();
/* 194 */           localSendable.getNetworkObject().init();
/* 195 */           synchronized (
/* 196 */             paramByte = localSendable.getNetworkObject())
/*     */           {
/* 198 */             (
/* 202 */               paramByte = NetworkObject.decode(paramStateInterface, paramDataInputStream, paramByte, paramShort, paramNetworkStateContainer != paramStateInterface.getLocalAndRemoteObjectContainer(), paramInt2))
/* 202 */               .onInit(paramStateInterface);
/*     */ 
/* 204 */             if (paramInt1 != ((Integer)paramByte.id.get()).intValue()) {
/* 205 */               if ((paramStateInterface instanceof ClientStateInterface)) {
/* 206 */                 String str = "[ERROR] in " + paramStateInterface + " received changed object \n|stream for a new object. the id of the received object could \n|not be decoded because it wasnt sent (never ment to be sent). \n|the obj was probably create on the server without knowlegde of \n|this client and has therefore to be re-requested\n|[NTID(" + paramByte.id.get() + ") != receivedId[" + paramInt1 + "] received]; (SenderID: " + paramInt2 + "), \n|isSynched(" + paramBoolean + ") that was not yet created in " + paramStateInterface + ", \n|SCHEDULING RESYNC. current remotes: " + localInt2ObjectOpenHashMap1 + ", local: " + localInt2ObjectOpenHashMap2 + "; container: " + paramNetworkStateContainer.getClass();
/*     */ 
/* 222 */                 throw new SynchronizationException(str);
/*     */               }
/* 224 */               if (!$assertionsDisabled) throw new AssertionError("NEW object not correctly en/decoded (probably en)\n on " + paramStateInterface + " received ident: " + paramInt1 + ", \nencoded: " + paramByte.id.get() + ", \nmarkedForDel: " + paramByte.markedDeleted.get() + ", \nSENDER: " + paramInt2 + ", \nsynchronized = " + paramBoolean + ", \nremotes: " + paramNetworkStateContainer);
/*     */ 
/*     */             }
/*     */ 
/* 243 */             if (((Integer)paramByte.id.get()).intValue() < 0) {
/* 244 */               System.err.println("[ERROR][CRITICAL]something fucked up: received id for new object:  " + paramByte.id.get());
/* 245 */               return;
/*     */             }
/* 247 */             localSendable.setId(((Integer)paramByte.id.get()).intValue());
/*     */ 
/* 251 */             localSendable.initFromNetworkObject(paramByte);
/*     */ 
/* 253 */             if ((localSendable instanceof zL)) {
/* 254 */               ((zL)localSendable).initPhysics();
/*     */             }
/* 256 */             localSendable.updateFromNetworkObject(paramByte);
/* 257 */             paramByte.clearReceiveBuffers();
/*     */ 
/* 261 */             paramNetworkStateContainer.putLocal(localSendable.getId(), localSendable);
/*     */ 
/* 264 */             if ((paramStateInterface instanceof ClientStateInterface))
/*     */             {
/* 270 */               paramByte.newObject = false;
/*     */             }
/*     */ 
/* 273 */             paramByte.addObserversForFields();
/*     */ 
/* 275 */             localInt2ObjectOpenHashMap1.put((Integer)paramByte.id.get(), paramByte);
/* 276 */             paramStateInterface.notifyOfAddedObject(localSendable);
/*     */             long l2;
/* 278 */             if ((
/* 278 */               l2 = System.currentTimeMillis() - l1) > 
/* 278 */               10L)
/* 279 */               System.err.println("[SYNC-RECEIVER] " + paramStateInterface + " DECODING OF NEW OBJECT " + localSendable + " TOOK " + l2);
/*     */           }
/*     */         }
/*     */       }
/*     */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void update(NetworkStateContainer paramNetworkStateContainer, int paramInt, DataInputStream paramDataInputStream, StateInterface paramStateInterface, boolean paramBoolean1, boolean paramBoolean2, short paramShort)
/*     */   {
/* 294 */     Int2ObjectOpenHashMap localInt2ObjectOpenHashMap1 = paramNetworkStateContainer.getRemoteObjects();
/*     */ 
/* 302 */     if (paramStateInterface.isReady())
/*     */     {
/* 307 */       int i = paramDataInputStream.readInt();
/*     */ 
/* 309 */       if (paramBoolean2)
/*     */       {
/* 312 */         System.err.println("[SYNCHRONIZE] FORCED UPDATE");
/*     */       }
/* 314 */       int j = 127;
/*     */       int n;
/*     */       byte b;
/* 315 */       for (int k = 0; k < i; k++) {
/* 316 */         n = ByteUtil.a(paramDataInputStream);
/* 317 */         if (paramBoolean2)
/*     */         {
/* 319 */           Object localObject1 = null;
/*     */ 
/* 318 */           synchronized (paramNetworkStateContainer.getLocalObjects())
/*     */           {
/* 320 */             synchronized (localInt2ObjectOpenHashMap1)
/*     */             {
/* 322 */               localInt2ObjectOpenHashMap1.remove(n);
/*     */             }
/*     */           }
/*     */         }
/*     */ 
/* 327 */         b = paramDataInputStream.readByte();
/*     */ 
/* 331 */         ??? = null;
/* 332 */         if ((paramBoolean1) && (localInt2ObjectOpenHashMap1.containsKey(n)) && (!paramBoolean2))
/*     */         {
/* 337 */           handleChanged(paramNetworkStateContainer, n, paramStateInterface, paramDataInputStream, paramShort, paramInt);
/* 338 */         } else if (( = (GhostSendable)paramNetworkStateContainer.getGhostObjects().get(n)) != null)
/*     */         {
/* 340 */           System.err.println(paramStateInterface + ": Exception: Received update for ghost object: " + n + "; ignoring update");
/* 341 */           ((GhostSendable)???).sendable.getNetworkObject().decodeChange(paramStateInterface, paramDataInputStream, paramShort, paramNetworkStateContainer != paramStateInterface.getLocalAndRemoteObjectContainer(), paramInt);
/* 342 */           System.err.println(paramStateInterface + ": Exception: Received update for ghost object: " + n + "; ignoring update: DECODED CHANGE TO GHOST OBJECT " + paramNetworkStateContainer.getGhostObjects().get(n));
/*     */         } else {
/*     */           try {
/* 345 */             handleNewObject(paramNetworkStateContainer, n, paramInt, b, paramStateInterface, paramDataInputStream, paramShort, paramBoolean1);
/*     */           }
/*     */           catch (NoSuchMethodException localNoSuchMethodException)
/*     */           {
/* 364 */             localNoSuchMethodException.printStackTrace();
/*     */ 
/* 360 */             throw new RuntimeException("the object " + b + " does not provide an acceptable contructor to create an instance from a remote object");
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 367 */         paramNetworkStateContainer.debugReceivedClasses.add(b);
/*     */       }
/*     */ 
/* 370 */       if (paramBoolean2)
/*     */       {
/*     */         Int2ObjectOpenHashMap localInt2ObjectOpenHashMap2;
/* 372 */         if (!(
/* 372 */           localInt2ObjectOpenHashMap2 = paramNetworkStateContainer.getLocalObjects())
/* 372 */           .keySet().equals(localInt2ObjectOpenHashMap1.keySet())) {
/*     */           try {
/* 374 */             System.err.println("LOCAL : " + localInt2ObjectOpenHashMap2);
/* 375 */             System.err.println("REMOTE: " + localInt2ObjectOpenHashMap1);
/* 376 */             throw new SynchronizationException("[CLIENT] " + paramStateInterface + " invalid synchronization state: local and remote objects differ"); } catch (SynchronizationException localSynchronizationException) { localSynchronizationException
/* 377 */               .printStackTrace();
/*     */ 
/* 379 */             if (!$assertionsDisabled) throw new AssertionError();
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/* 384 */       if (paramDataInputStream.available() > 0) {
/* 385 */         System.err.println("[ERROR][CRITICAL] leftover bytes: " + paramDataInputStream.available() + " last decoded class: " + NetUtil.getSendableClass(b));
/* 386 */         for (int m = 0; m < paramNetworkStateContainer.debugReceivedClasses.size(); m++) {
/* 387 */           System.err.println("[DEBUGINFO] decoded class #1: " + NetUtil.getSendableClass(paramNetworkStateContainer.debugReceivedClasses.get(m).byteValue()));
/*     */         }
/* 389 */         m = paramDataInputStream.readInt();
/* 390 */         n = paramDataInputStream.readByte();
/* 391 */         System.err.println("[DEBUGINFO] into container: " + paramNetworkStateContainer.getClass());
/* 392 */         System.err.println("[DEBUGINFO] decoded next ID: " + m + "; class " + n + " = " + NetUtil.getSendableClass(n));
/* 393 */         System.err.println("[DEBUGINFO] if class and object id are sane, this means there was a wrong update size transferred");
/* 394 */         System.err.println("[DEBUGINFO] if not, an object did not decode correctly");
/*     */       }
/*     */     }
/*     */ 
/* 398 */     paramNetworkStateContainer.debugReceivedClasses.clear();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.synchronization.SynchronizationReceiver
 * JD-Core Version:    0.6.2
 */