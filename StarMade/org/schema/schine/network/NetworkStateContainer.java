package org.schema.schine.network;

import com.bulletphysics.util.ObjectArrayList;
import it.unimi.dsi.fastutil.bytes.ByteArrayList;
import it.unimi.dsi.fastutil.ints.Int2BooleanOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import org.schema.schine.network.objects.Sendable;
import org.schema.schine.network.synchronization.GhostSendable;

public class NetworkStateContainer
{
  private final Int2ObjectOpenHashMap localObjects = new Int2ObjectOpenHashMap();
  private final Int2ObjectOpenHashMap localUpdatableObjects = new Int2ObjectOpenHashMap();
  private final Int2ObjectOpenHashMap remoteObjects = new Int2ObjectOpenHashMap();
  private final Object2ObjectOpenHashMap persistentObjects = new Object2ObjectOpenHashMap();
  private final Int2ObjectOpenHashMap ghostObjects = new Int2ObjectOpenHashMap();
  private final boolean privateChannel;
  public ByteArrayList debugReceivedClasses = new ByteArrayList();
  public final ObjectArrayList updateSet = new ObjectArrayList();
  public final Int2BooleanOpenHashMap newStatesBeforeForce = new Int2BooleanOpenHashMap();
  
  public NetworkStateContainer(boolean paramBoolean)
  {
    this.privateChannel = paramBoolean;
  }
  
  public Int2ObjectOpenHashMap getLocalObjects()
  {
    return this.localObjects;
  }
  
  public Int2ObjectOpenHashMap getRemoteObjects()
  {
    return this.remoteObjects;
  }
  
  public void putLocal(int paramInt, Sendable paramSendable)
  {
    assert (paramSendable != null);
    this.localObjects.put(paramInt, paramSendable);
    if (paramSendable.isUpdatable()) {
      getLocalUpdatableObjects().put(paramInt, paramSendable);
    }
  }
  
  public Sendable removeLocal(int paramInt)
  {
    Sendable localSendable;
    if ((localSendable = (Sendable)this.localObjects.remove(paramInt)).isUpdatable()) {
      getLocalUpdatableObjects().remove(paramInt);
    }
    return localSendable;
  }
  
  public String toString()
  {
    return "(Local/Remote: " + this.localObjects + "/" + this.remoteObjects + ")";
  }
  
  public Int2ObjectOpenHashMap getGhostObjects()
  {
    return this.ghostObjects;
  }
  
  public boolean isPrivateChannel()
  {
    return this.privateChannel;
  }
  
  public void checkGhostObjects()
  {
    if (!getGhostObjects().isEmpty())
    {
      long l = System.currentTimeMillis();
      synchronized (getGhostObjects())
      {
        ObjectIterator localObjectIterator = getGhostObjects().values().iterator();
        while (localObjectIterator.hasNext())
        {
          GhostSendable localGhostSendable = (GhostSendable)localObjectIterator.next();
          if (l - localGhostSendable.timeDeleted > 20000L) {
            localObjectIterator.remove();
          }
        }
        return;
      }
    }
  }
  
  public Int2ObjectOpenHashMap getLocalUpdatableObjects()
  {
    return this.localUpdatableObjects;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.NetworkStateContainer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */