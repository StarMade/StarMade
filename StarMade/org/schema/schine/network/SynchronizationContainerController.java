package org.schema.schine.network;

import class_1421;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.io.PrintStream;
import java.util.ArrayList;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.Sendable;
import org.schema.schine.network.objects.remote.RemoteInteger;

public class SynchronizationContainerController
{
  private final ArrayList toAdd = new ArrayList();
  private final NetworkStateContainer container;
  private final StateInterface state;
  private final boolean privateChannel;
  
  public SynchronizationContainerController(NetworkStateContainer paramNetworkStateContainer, StateInterface paramStateInterface, boolean paramBoolean)
  {
    this.container = paramNetworkStateContainer;
    this.state = paramStateInterface;
    this.privateChannel = paramBoolean;
  }
  
  public void addImmediateSynchronizedObject(Sendable paramSendable)
  {
    synchronized (this.container.getLocalObjects())
    {
      this.container.putLocal(paramSendable.getId(), paramSendable);
      paramSendable.newNetworkObject();
      paramSendable.getNetworkObject().init();
      paramSendable.getNetworkObject().newObject = true;
      paramSendable.getNetworkObject().addObserversForFields();
      if ((paramSendable instanceof class_1421)) {
        ((class_1421)paramSendable).initPhysics();
      }
      synchronized (paramSendable.getNetworkObject())
      {
        paramSendable.updateToFullNetworkObject();
        paramSendable.getNetworkObject().setAllFieldsChanged();
        paramSendable.getNetworkObject().setChanged(true);
      }
      synchronized (this.container.getRemoteObjects())
      {
        assert (((Integer)paramSendable.getNetworkObject().field_87.get()).intValue() >= 0);
        assert (paramSendable.getNetworkObject().newObject);
        this.container.getRemoteObjects().put(paramSendable.getId(), paramSendable.getNetworkObject());
      }
      getState().notifyOfAddedObject(paramSendable);
      return;
    }
  }
  
  public void addNewSynchronizedObjectQueued(Sendable paramSendable)
  {
    synchronized (this.toAdd)
    {
      this.toAdd.add(paramSendable);
      return;
    }
  }
  
  public void handleQueuedSynchronizedObjects()
  {
    if (!this.toAdd.isEmpty())
    {
      long l1 = System.currentTimeMillis();
      synchronized (this.toAdd)
      {
        while (!this.toAdd.isEmpty()) {
          addImmediateSynchronizedObject((Sendable)this.toAdd.remove(0));
        }
      }
      long l2;
      if ((l2 = System.currentTimeMillis() - l1) > 10L) {
        System.err.println("[SERVER][UPDATE] WARNING: handleQueuedSynchronizedObjects update took " + l2 + " on " + this.state);
      }
    }
  }
  
  public boolean isPrivateChannel()
  {
    return this.privateChannel;
  }
  
  public StateInterface getState()
  {
    return this.state;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.SynchronizationContainerController
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */