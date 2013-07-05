/*    */ package org.schema.schine.network;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*    */ import java.io.PrintStream;
/*    */ import java.util.ArrayList;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ import org.schema.schine.network.objects.Sendable;
/*    */ import org.schema.schine.network.objects.remote.RemoteInteger;
/*    */ import zL;
/*    */ 
/*    */ public class SynchronizationContainerController
/*    */ {
/* 11 */   private final ArrayList toAdd = new ArrayList();
/*    */   private final NetworkStateContainer container;
/*    */   private final StateInterface state;
/*    */   private final boolean privateChannel;
/*    */ 
/*    */   public SynchronizationContainerController(NetworkStateContainer paramNetworkStateContainer, StateInterface paramStateInterface, boolean paramBoolean)
/*    */   {
/* 20 */     this.container = paramNetworkStateContainer;
/* 21 */     this.state = paramStateInterface;
/* 22 */     this.privateChannel = paramBoolean;
/*    */   }
/*    */ 
/*    */   public void addImmediateSynchronizedObject(Sendable paramSendable) {
/* 26 */     synchronized (this.container.getLocalObjects())
/*    */     {
/* 28 */       this.container.putLocal(paramSendable.getId(), paramSendable);
/* 29 */       paramSendable.newNetworkObject();
/* 30 */       paramSendable.getNetworkObject().init();
/* 31 */       paramSendable.getNetworkObject().newObject = true;
/* 32 */       paramSendable.getNetworkObject().addObserversForFields();
/* 33 */       if ((paramSendable instanceof zL)) {
/* 34 */         ((zL)paramSendable).initPhysics();
/*    */       }
/* 36 */       synchronized (paramSendable.getNetworkObject()) {
/* 37 */         paramSendable.updateToFullNetworkObject();
/*    */ 
/* 39 */         paramSendable.getNetworkObject().setAllFieldsChanged();
/* 40 */         paramSendable.getNetworkObject().setChanged(true);
/*    */       }
/* 42 */       synchronized (this.container.getRemoteObjects()) {
/* 43 */         assert (((Integer)paramSendable.getNetworkObject().id.get()).intValue() >= 0);
/* 44 */         assert (paramSendable.getNetworkObject().newObject);
/*    */ 
/* 47 */         this.container.getRemoteObjects().put(paramSendable.getId(), paramSendable.getNetworkObject());
/*    */       }
/* 49 */       getState().notifyOfAddedObject(paramSendable);
/* 50 */       return;
/*    */     }
/*    */   }
/*    */ 
/*    */   public void addNewSynchronizedObjectQueued(Sendable paramSendable) {
/* 55 */     synchronized (this.toAdd) {
/* 56 */       this.toAdd.add(paramSendable);
/* 57 */       return;
/*    */     }
/*    */   }
/*    */ 
/*    */   public void handleQueuedSynchronizedObjects() {
/* 62 */     if (!this.toAdd.isEmpty()) {
/* 63 */       long l1 = System.currentTimeMillis();
/* 64 */       synchronized (this.toAdd)
/*    */       {
/* 66 */         while (!this.toAdd.isEmpty())
/* 67 */           addImmediateSynchronizedObject((Sendable)this.toAdd.remove(0));
/*    */       }
/*    */       long l2;
/* 72 */       if ((
/* 72 */         l2 = System.currentTimeMillis() - l1) > 
/* 72 */         10L)
/* 73 */         System.err.println("[SERVER][UPDATE] WARNING: handleQueuedSynchronizedObjects update took " + l2 + " on " + this.state);
/*    */     }
/*    */   }
/*    */ 
/*    */   public boolean isPrivateChannel()
/*    */   {
/* 82 */     return this.privateChannel;
/*    */   }
/*    */ 
/*    */   public StateInterface getState()
/*    */   {
/* 89 */     return this.state;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.SynchronizationContainerController
 * JD-Core Version:    0.6.2
 */