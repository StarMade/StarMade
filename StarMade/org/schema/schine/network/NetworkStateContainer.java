/*     */ package org.schema.schine.network;
/*     */ 
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import it.unimi.dsi.fastutil.bytes.ByteArrayList;
/*     */ import it.unimi.dsi.fastutil.ints.Int2BooleanOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import org.schema.schine.network.objects.Sendable;
/*     */ import org.schema.schine.network.synchronization.GhostSendable;
/*     */ 
/*     */ public class NetworkStateContainer
/*     */ {
/*     */   private final Int2ObjectOpenHashMap localObjects;
/*     */   private final Int2ObjectOpenHashMap localUpdatableObjects;
/*     */   private final Int2ObjectOpenHashMap remoteObjects;
/*     */   private final Object2ObjectOpenHashMap persistentObjects;
/*  28 */   private final Int2ObjectOpenHashMap ghostObjects = new Int2ObjectOpenHashMap();
/*     */   private final boolean privateChannel;
/*  31 */   public ByteArrayList debugReceivedClasses = new ByteArrayList();
/*  32 */   public final ObjectArrayList updateSet = new ObjectArrayList();
/*  33 */   public final Int2BooleanOpenHashMap newStatesBeforeForce = new Int2BooleanOpenHashMap();
/*     */ 
/*     */   public NetworkStateContainer(boolean paramBoolean) {
/*  36 */     this.localObjects = new Int2ObjectOpenHashMap();
/*  37 */     this.remoteObjects = new Int2ObjectOpenHashMap();
/*  38 */     this.persistentObjects = new Object2ObjectOpenHashMap();
/*  39 */     this.localUpdatableObjects = new Int2ObjectOpenHashMap();
/*  40 */     this.privateChannel = paramBoolean;
/*     */   }
/*     */ 
/*     */   public Int2ObjectOpenHashMap getLocalObjects()
/*     */   {
/*  48 */     return this.localObjects;
/*     */   }
/*     */ 
/*     */   public Int2ObjectOpenHashMap getRemoteObjects()
/*     */   {
/*  56 */     return this.remoteObjects;
/*     */   }
/*     */ 
/*     */   public void putLocal(int paramInt, Sendable paramSendable)
/*     */   {
/*  61 */     assert (paramSendable != null);
/*  62 */     this.localObjects.put(paramInt, paramSendable);
/*  63 */     if (paramSendable.isUpdatable())
/*  64 */       getLocalUpdatableObjects().put(paramInt, paramSendable);
/*     */   }
/*     */ 
/*     */   public Sendable removeLocal(int paramInt)
/*     */   {
/*     */     Sendable localSendable;
/*  74 */     if ((
/*  74 */       localSendable = (Sendable)this.localObjects.remove(paramInt))
/*  74 */       .isUpdatable()) {
/*  75 */       getLocalUpdatableObjects().remove(paramInt);
/*     */     }
/*  77 */     return localSendable;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/*  83 */     return "(Local/Remote: " + this.localObjects + "/" + this.remoteObjects + ")";
/*     */   }
/*     */ 
/*     */   public Int2ObjectOpenHashMap getGhostObjects()
/*     */   {
/*  88 */     return this.ghostObjects;
/*     */   }
/*     */ 
/*     */   public boolean isPrivateChannel()
/*     */   {
/*  97 */     return this.privateChannel;
/*     */   }
/*     */ 
/*     */   public void checkGhostObjects()
/*     */   {
/* 102 */     if (!getGhostObjects().isEmpty()) {
/* 103 */       long l = System.currentTimeMillis();
/*     */ 
/* 105 */       synchronized (getGhostObjects()) {
/* 106 */         ObjectIterator localObjectIterator = getGhostObjects().values().iterator();
/* 107 */         while (localObjectIterator.hasNext()) {
/* 108 */           GhostSendable localGhostSendable = (GhostSendable)localObjectIterator.next();
/* 109 */           if (l - localGhostSendable.timeDeleted > 20000L) {
/* 110 */             localObjectIterator.remove();
/*     */           }
/*     */         }
/*     */ 
/* 114 */         return;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public Int2ObjectOpenHashMap getLocalUpdatableObjects()
/*     */   {
/* 123 */     return this.localUpdatableObjects;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.NetworkStateContainer
 * JD-Core Version:    0.6.2
 */