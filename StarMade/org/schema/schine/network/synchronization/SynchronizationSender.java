/*     */ package org.schema.schine.network.synchronization;
/*     */ 
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import it.unimi.dsi.fastutil.ints.Int2BooleanOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.schema.schine.network.NetworkStateContainer;
/*     */ import org.schema.schine.network.StateInterface;
/*     */ import org.schema.schine.network.exception.SynchronizationException;
/*     */ import org.schema.schine.network.objects.NetworkObject;
/*     */ import org.schema.schine.network.objects.Sendable;
/*     */ import org.schema.schine.network.objects.remote.RemoteBoolean;
/*     */ import org.schema.schine.network.objects.remote.RemoteInteger;
/*     */ 
/*     */ public class SynchronizationSender
/*     */ {
/*     */   public static final int RETURN_CODE_CHANGED_OBJECT = 1;
/*     */   private static final int RETURN_CODE_NOTHING_CHANGED = 0;
/*     */   private static int debugId;
/*     */   public static boolean clientDebug;
/*     */ 
/*     */   private static void checkIfNeedsUpdateOrRemoved(Sendable paramSendable, NetworkObject paramNetworkObject, List paramList)
/*     */   {
/*  44 */     if (paramSendable.isMarkedForDeleteVolatile()) {
/*  45 */       paramNetworkObject.markedDeleted.set(Boolean.valueOf(paramSendable.isMarkedForDeleteVolatile()), true);
/*  46 */       System.err.println("[DELETE] " + paramSendable.getState() + " MARKING FOR DELETE " + paramSendable);
/*  47 */       paramNetworkObject.setChanged(true);
/*  48 */       paramList.add(paramSendable); return;
/*  49 */     }if ((paramNetworkObject.newObject) || (paramNetworkObject.isChanged())) {
/*  50 */       if ((((Boolean)paramNetworkObject.markedDeleted.get()).booleanValue()) && (!paramSendable.isMarkedForDeleteVolatile()))
/*     */       {
/*  52 */         paramNetworkObject.newObject = false;
/*  53 */         paramNetworkObject.setChanged(false); return;
/*     */       }
/*     */ 
/*  56 */       paramList.add(paramSendable);
/*     */     }
/*     */   }
/*     */ 
/*     */   private static void encodeFullObject(Sendable paramSendable, NetworkObject paramNetworkObject, DataOutputStream paramDataOutputStream, boolean paramBoolean1, boolean paramBoolean2)
/*     */   {
/*  66 */     paramNetworkObject = paramSendable.getNetworkObject();
/*  67 */     if (paramSendable.getId() < 0) {
/*  68 */       throw new IllegalArgumentException("[SENDER] Exception writing negative id for " + paramSendable + ": " + paramSendable.getId() + " " + paramSendable.getState());
/*     */     }
/*  70 */     synchronized (paramNetworkObject) {
/*  71 */       paramSendable.updateToFullNetworkObject();
/*     */ 
/*  75 */       int i = paramDataOutputStream.size();
/*  76 */       paramBoolean2 = NetworkObject.encode(paramSendable, paramNetworkObject, true, paramDataOutputStream, paramBoolean1, paramBoolean2);
/*     */ 
/*  82 */       if ((
/*  82 */         paramDataOutputStream = paramDataOutputStream.size() - 
/*  81 */         i) > 
/*  82 */         5120) {
/*  83 */         System.err.println("[NT] Big FullUpdate: " + paramDataOutputStream + " bytes: " + paramSendable);
/*     */       }
/*     */ 
/*  86 */       if (!paramBoolean1)
/*     */       {
/*  88 */         paramNetworkObject.setChanged(paramBoolean2);
/*  89 */         paramNetworkObject.newObject = false;
/*     */       }
/*     */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static int encodeNetworkObjects(NetworkStateContainer paramNetworkStateContainer, StateInterface paramStateInterface, DataOutputStream paramDataOutputStream, boolean paramBoolean)
/*     */   {
/* 113 */     Object localObject1 = paramNetworkStateContainer.getLocalObjects();
/* 114 */     Int2ObjectOpenHashMap localInt2ObjectOpenHashMap = paramNetworkStateContainer.getRemoteObjects();
/*     */ 
/* 122 */     synchronized (localObject1) {
/* 123 */       synchronized (localInt2ObjectOpenHashMap)
/*     */       {
/* 125 */         ObjectArrayList localObjectArrayList = paramNetworkStateContainer.updateSet;
/* 126 */         Int2BooleanOpenHashMap localInt2BooleanOpenHashMap = paramNetworkStateContainer.newStatesBeforeForce;
/* 127 */         localObjectArrayList.clear();
/* 128 */         localInt2BooleanOpenHashMap.clear();
/*     */ 
/* 131 */         int i = 0;
/* 132 */         int j = 0;
/* 133 */         if (paramBoolean) {
/* 134 */           System.err.println("[SYNC_SENDER] SENDING ALL OBJECTS: " + ((Int2ObjectOpenHashMap)localObject1).size());
/* 135 */           for (localObject2 = localInt2ObjectOpenHashMap.values().iterator(); ((Iterator)localObject2).hasNext(); ) { localObject3 = (NetworkObject)((Iterator)localObject2).next();
/*     */ 
/* 137 */             localInt2BooleanOpenHashMap.put(((Integer)((NetworkObject)localObject3).id.get()).intValue(), ((NetworkObject)localObject3).newObject);
/* 138 */             ((NetworkObject)localObject3).newObject = true;
/* 139 */             j++;
/*     */           }
/*     */         }
/* 142 */         for (Object localObject3 = ((Int2ObjectOpenHashMap)localObject1).values().iterator(); ((Iterator)localObject3).hasNext(); )
/*     */         {
/* 144 */           if ((
/* 144 */             localObject2 = (Sendable)((Iterator)localObject3).next())
/* 144 */             .getId() < 0) {
/* 145 */             throw new IllegalArgumentException("[SENDER] Exception writing negative id for " + localObject2 + ": " + ((Sendable)localObject2).getId() + " " + ((Sendable)localObject2).getState());
/*     */           }
/*     */ 
/* 148 */           ((Sendable)localObject2).isMarkedForDeleteVolatile();
/*     */ 
/* 154 */           if ((
/* 154 */             localObject4 = (NetworkObject)localInt2ObjectOpenHashMap.get(((Sendable)localObject2).getId())) == null)
/*     */           {
/*     */             try
/*     */             {
/* 156 */               throw new SynchronizationException("!!!!!!!!!! sendingState(" + paramStateInterface + ")FATAL-ERROR: " + ((Sendable)localObject2).getId() + " does not exist: " + localInt2ObjectOpenHashMap + ", LOCAL: " + localObject1); } catch (SynchronizationException localSynchronizationException) { localSynchronizationException
/* 159 */                 .printStackTrace();
/*     */ 
/* 161 */               if (!$assertionsDisabled) throw new AssertionError();
/*     */             }
/*     */           }
/*     */ 
/* 165 */           assert ((!paramBoolean) || (((NetworkObject)localObject4).newObject)) : (" failed: forceAll -> objbectNew: " + localObject4 + ": " + ((NetworkObject)localObject4).newObject);
/*     */ 
/* 168 */           checkIfNeedsUpdateOrRemoved((Sendable)localObject2, (NetworkObject)localObject4, localObjectArrayList);
/*     */         }
/*     */         Object localObject4;
/* 177 */         if (localObjectArrayList.isEmpty())
/*     */         {
/* 182 */           return 0;
/*     */         }
/*     */ 
/* 185 */         assert ((!paramBoolean) || (j == localObjectArrayList.size())) : (" force all " + j + ": " + localObjectArrayList.size());
/*     */ 
/* 191 */         paramDataOutputStream.writeInt(localObjectArrayList.size());
/*     */ 
/* 193 */         int k = 0;
/* 194 */         for (Object localObject2 = localObjectArrayList.iterator(); ((Iterator)localObject2).hasNext(); ) { localObject4 = (Sendable)((Iterator)localObject2).next();
/*     */           boolean bool;
/* 205 */           if (!(
/* 205 */             localObject1 = (NetworkObject)localInt2ObjectOpenHashMap.get(((Sendable)localObject4).getId())).newObject)
/*     */           {
/* 212 */             if ((
/* 212 */               bool = encodePartialObjectIfChanged((Sendable)localObject4, (NetworkObject)localObject1, paramDataOutputStream, paramNetworkStateContainer != paramStateInterface.getLocalAndRemoteObjectContainer())))
/*     */             {
/* 213 */               k++;
/*     */             }
/*     */ 
/* 216 */             i = (i != 0) || (bool) ? 1 : 0;
/*     */           }
/*     */           else
/*     */           {
/* 225 */             assert ((!((Boolean)bool.markedDeleted.get()).booleanValue()) || (((Sendable)localObject4).isMarkedForDeleteVolatile()));
/* 226 */             i = 1;
/* 227 */             encodeFullObject((Sendable)localObject4, bool, paramDataOutputStream, false, paramNetworkStateContainer != paramStateInterface.getLocalAndRemoteObjectContainer());
/* 228 */             k++;
/*     */           }
/*     */ 
/* 234 */           if (((Sendable)localObject4).isMarkedForDeleteVolatile())
/*     */           {
/* 236 */             ((Sendable)localObject4).setMarkedForDeleteVolatileSent(true);
/*     */           }
/* 238 */           clientDebug = false;
/*     */         }
/*     */ 
/* 242 */         assert (localObjectArrayList.size() == k) : (" WRONG NUMBER OF OBJECTS WRITTEN: " + localObjectArrayList.size() + " / " + k);
/*     */ 
/* 245 */         if (paramBoolean) {
/* 246 */           for (localObject2 = localInt2ObjectOpenHashMap.values().iterator(); ((Iterator)localObject2).hasNext(); ) (
/* 247 */               localObject4 = (NetworkObject)((Iterator)localObject2).next()).newObject = 
/* 247 */               localInt2BooleanOpenHashMap.get((Integer)((NetworkObject)localObject4).id.get()).booleanValue();
/*     */ 
/*     */         }
/*     */ 
/* 251 */         if (i != 0) {
/* 252 */           return 1;
/*     */         }
/* 254 */         return 0;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private static boolean encodePartialObjectIfChanged(Sendable paramSendable, NetworkObject paramNetworkObject, DataOutputStream paramDataOutputStream, boolean paramBoolean)
/*     */   {
/* 261 */     boolean bool = false;
/* 262 */     synchronized (paramNetworkObject) {
/* 263 */       if (paramNetworkObject.isChanged())
/*     */       {
/* 265 */         assert ((!((Boolean)paramNetworkObject.markedDeleted.get()).booleanValue()) || (paramSendable.isMarkedForDeleteVolatile()));
/*     */ 
/* 268 */         bool = true;
/*     */ 
/* 272 */         paramSendable = paramNetworkObject.encodeChange(paramSendable, paramDataOutputStream, paramBoolean);
/*     */ 
/* 276 */         paramNetworkObject.setChanged(paramSendable);
/* 277 */         paramNetworkObject.newObject = false;
/*     */       }
/*     */     }
/* 280 */     return bool;
/*     */   }
/*     */ 
/*     */   public static void writeObjectForcedWithoutStateChange(NetworkStateContainer paramNetworkStateContainer, StateInterface paramStateInterface, DataOutputStream paramDataOutputStream, boolean paramBoolean)
/*     */   {
/* 300 */     paramBoolean = paramNetworkStateContainer.getLocalObjects();
/* 301 */     Int2ObjectOpenHashMap localInt2ObjectOpenHashMap = paramNetworkStateContainer.getRemoteObjects();
/*     */ 
/* 308 */     synchronized (paramBoolean) {
/* 309 */       synchronized (localInt2ObjectOpenHashMap)
/*     */       {
/* 311 */         paramDataOutputStream.writeInt(paramBoolean.size());
/* 312 */         int i = 0;
/* 313 */         for (Iterator localIterator = paramBoolean.values().iterator(); localIterator.hasNext(); )
/*     */         {
/*     */           Sendable localSendable;
/* 315 */           if ((
/* 315 */             localSendable = (Sendable)localIterator.next())
/* 315 */             .getId() < 0) {
/* 316 */             throw new IllegalArgumentException("[SENDER] Exception writing negative id for " + localSendable + ": " + localSendable.getId() + " " + localSendable.getState());
/*     */           }
/* 318 */           NetworkObject localNetworkObject = (NetworkObject)localInt2ObjectOpenHashMap.get(localSendable.getId());
/*     */ 
/* 320 */           assert (localNetworkObject != null) : (localSendable.getId() + " does not exist: " + localInt2ObjectOpenHashMap + ", LOCAL: " + paramBoolean);
/*     */ 
/* 324 */           encodeFullObject(localSendable, localNetworkObject, paramDataOutputStream, true, paramNetworkStateContainer != paramStateInterface.getLocalAndRemoteObjectContainer());
/* 325 */           i++;
/*     */         }
/*     */ 
/* 332 */         if ((!$assertionsDisabled) && (paramBoolean.size() != i)) throw new AssertionError(" WRONG NUMBER OF OBJECTS WRITTEN: " + paramBoolean.size() + " / " + i);
/*     */       }
/*     */       return;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.synchronization.SynchronizationSender
 * JD-Core Version:    0.6.2
 */