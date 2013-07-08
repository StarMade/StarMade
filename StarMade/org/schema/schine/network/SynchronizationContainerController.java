/*  1:   */package org.schema.schine.network;
/*  2:   */
/*  3:   */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*  4:   */import java.io.PrintStream;
/*  5:   */import java.util.ArrayList;
/*  6:   */import org.schema.schine.network.objects.remote.RemoteInteger;
/*  7:   */import zP;
/*  8:   */
/*  9:   */public class SynchronizationContainerController
/* 10:   */{
/* 11:11 */  private final ArrayList toAdd = new ArrayList();
/* 12:   */  
/* 13:   */  private final NetworkStateContainer container;
/* 14:   */  
/* 15:   */  private final StateInterface state;
/* 16:   */  private final boolean privateChannel;
/* 17:   */  
/* 18:   */  public SynchronizationContainerController(NetworkStateContainer paramNetworkStateContainer, StateInterface paramStateInterface, boolean paramBoolean)
/* 19:   */  {
/* 20:20 */    this.container = paramNetworkStateContainer;
/* 21:21 */    this.state = paramStateInterface;
/* 22:22 */    this.privateChannel = paramBoolean;
/* 23:   */  }
/* 24:   */  
/* 25:   */  public void addImmediateSynchronizedObject(org.schema.schine.network.objects.Sendable paramSendable) {
/* 26:26 */    synchronized (this.container.getLocalObjects())
/* 27:   */    {
/* 28:28 */      this.container.putLocal(paramSendable.getId(), paramSendable);
/* 29:29 */      paramSendable.newNetworkObject();
/* 30:30 */      paramSendable.getNetworkObject().init();
/* 31:31 */      paramSendable.getNetworkObject().newObject = true;
/* 32:32 */      paramSendable.getNetworkObject().addObserversForFields();
/* 33:33 */      if ((paramSendable instanceof zP)) {
/* 34:34 */        ((zP)paramSendable).initPhysics();
/* 35:   */      }
/* 36:36 */      synchronized (paramSendable.getNetworkObject()) {
/* 37:37 */        paramSendable.updateToFullNetworkObject();
/* 38:   */        
/* 39:39 */        paramSendable.getNetworkObject().setAllFieldsChanged();
/* 40:40 */        paramSendable.getNetworkObject().setChanged(true);
/* 41:   */      }
/* 42:42 */      synchronized (this.container.getRemoteObjects()) {
/* 43:43 */        assert (((Integer)paramSendable.getNetworkObject().id.get()).intValue() >= 0);
/* 44:44 */        assert (paramSendable.getNetworkObject().newObject);
/* 45:   */        
/* 47:47 */        this.container.getRemoteObjects().put(paramSendable.getId(), paramSendable.getNetworkObject());
/* 48:   */      }
/* 49:49 */      getState().notifyOfAddedObject(paramSendable);
/* 50:50 */      return;
/* 51:   */    }
/* 52:   */  }
/* 53:   */  
/* 54:   */  public void addNewSynchronizedObjectQueued(org.schema.schine.network.objects.Sendable paramSendable) {
/* 55:55 */    synchronized (this.toAdd) {
/* 56:56 */      this.toAdd.add(paramSendable);
/* 57:57 */      return;
/* 58:   */    }
/* 59:   */  }
/* 60:   */  
/* 61:   */  public void handleQueuedSynchronizedObjects() {
/* 62:62 */    if (!this.toAdd.isEmpty()) {
/* 63:63 */      long l1 = System.currentTimeMillis();
/* 64:64 */      synchronized (this.toAdd)
/* 65:   */      {
/* 66:66 */        while (!this.toAdd.isEmpty()) {
/* 67:67 */          addImmediateSynchronizedObject((org.schema.schine.network.objects.Sendable)this.toAdd.remove(0));
/* 68:   */        }
/* 69:   */      }
/* 70:   */      
/* 71:   */      long l2;
/* 72:72 */      if ((l2 = System.currentTimeMillis() - l1) > 10L) {
/* 73:73 */        System.err.println("[SERVER][UPDATE] WARNING: handleQueuedSynchronizedObjects update took " + l2 + " on " + this.state);
/* 74:   */      }
/* 75:   */    }
/* 76:   */  }
/* 77:   */  
/* 80:   */  public boolean isPrivateChannel()
/* 81:   */  {
/* 82:82 */    return this.privateChannel;
/* 83:   */  }
/* 84:   */  
/* 87:   */  public StateInterface getState()
/* 88:   */  {
/* 89:89 */    return this.state;
/* 90:   */  }
/* 91:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.SynchronizationContainerController
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */