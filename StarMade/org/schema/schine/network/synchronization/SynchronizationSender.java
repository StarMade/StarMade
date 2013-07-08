package org.schema.schine.network.synchronization;

import com.bulletphysics.util.ObjectArrayList;
import it.unimi.dsi.fastutil.ints.Int2BooleanOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import java.io.DataOutputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.exception.SynchronizationException;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.Sendable;
import org.schema.schine.network.objects.remote.RemoteBoolean;
import org.schema.schine.network.objects.remote.RemoteInteger;

public class SynchronizationSender
{
  public static final int RETURN_CODE_CHANGED_OBJECT = 1;
  private static final int RETURN_CODE_NOTHING_CHANGED = 0;
  private static int debugId;
  public static boolean clientDebug;
  
  private static void checkIfNeedsUpdateOrRemoved(Sendable paramSendable, NetworkObject paramNetworkObject, List paramList)
  {
    if (paramSendable.isMarkedForDeleteVolatile())
    {
      paramNetworkObject.markedDeleted.set(Boolean.valueOf(paramSendable.isMarkedForDeleteVolatile()), true);
      System.err.println("[DELETE] " + paramSendable.getState() + " MARKING FOR DELETE " + paramSendable);
      paramNetworkObject.setChanged(true);
      paramList.add(paramSendable);
      return;
    }
    if ((paramNetworkObject.newObject) || (paramNetworkObject.isChanged()))
    {
      if ((((Boolean)paramNetworkObject.markedDeleted.get()).booleanValue()) && (!paramSendable.isMarkedForDeleteVolatile()))
      {
        paramNetworkObject.newObject = false;
        paramNetworkObject.setChanged(false);
        return;
      }
      paramList.add(paramSendable);
    }
  }
  
  private static void encodeFullObject(Sendable paramSendable, NetworkObject paramNetworkObject, DataOutputStream paramDataOutputStream, boolean paramBoolean1, boolean paramBoolean2)
  {
    paramNetworkObject = paramSendable.getNetworkObject();
    if (paramSendable.getId() < 0) {
      throw new IllegalArgumentException("[SENDER] Exception writing negative id for " + paramSendable + ": " + paramSendable.getId() + " " + paramSendable.getState());
    }
    synchronized (paramNetworkObject)
    {
      paramSendable.updateToFullNetworkObject();
      int i = paramDataOutputStream.size();
      paramBoolean2 = NetworkObject.encode(paramSendable, paramNetworkObject, true, paramDataOutputStream, paramBoolean1, paramBoolean2);
      if ((paramDataOutputStream = paramDataOutputStream.size() - i) > 5120) {
        System.err.println("[NT] Big FullUpdate: " + paramDataOutputStream + " bytes: " + paramSendable);
      }
      if (!paramBoolean1)
      {
        paramNetworkObject.setChanged(paramBoolean2);
        paramNetworkObject.newObject = false;
      }
      return;
    }
  }
  
  public static int encodeNetworkObjects(NetworkStateContainer paramNetworkStateContainer, StateInterface paramStateInterface, DataOutputStream paramDataOutputStream, boolean paramBoolean)
  {
    Object localObject1 = paramNetworkStateContainer.getLocalObjects();
    Int2ObjectOpenHashMap localInt2ObjectOpenHashMap = paramNetworkStateContainer.getRemoteObjects();
    synchronized (localObject1)
    {
      synchronized (localInt2ObjectOpenHashMap)
      {
        ObjectArrayList localObjectArrayList = paramNetworkStateContainer.updateSet;
        Int2BooleanOpenHashMap localInt2BooleanOpenHashMap = paramNetworkStateContainer.newStatesBeforeForce;
        localObjectArrayList.clear();
        localInt2BooleanOpenHashMap.clear();
        int i = 0;
        int j = 0;
        if (paramBoolean)
        {
          System.err.println("[SYNC_SENDER] SENDING ALL OBJECTS: " + ((Int2ObjectOpenHashMap)localObject1).size());
          localObject2 = localInt2ObjectOpenHashMap.values().iterator();
          while (((Iterator)localObject2).hasNext())
          {
            localObject3 = (NetworkObject)((Iterator)localObject2).next();
            localInt2BooleanOpenHashMap.put(((Integer)((NetworkObject)localObject3).field_87.get()).intValue(), ((NetworkObject)localObject3).newObject);
            ((NetworkObject)localObject3).newObject = true;
            j++;
          }
        }
        Object localObject3 = ((Int2ObjectOpenHashMap)localObject1).values().iterator();
        Object localObject4;
        while (((Iterator)localObject3).hasNext())
        {
          if ((localObject2 = (Sendable)((Iterator)localObject3).next()).getId() < 0) {
            throw new IllegalArgumentException("[SENDER] Exception writing negative id for " + localObject2 + ": " + ((Sendable)localObject2).getId() + " " + ((Sendable)localObject2).getState());
          }
          ((Sendable)localObject2).isMarkedForDeleteVolatile();
          if ((localObject4 = (NetworkObject)localInt2ObjectOpenHashMap.get(((Sendable)localObject2).getId())) == null) {
            try
            {
              throw new SynchronizationException("!!!!!!!!!! sendingState(" + paramStateInterface + ")FATAL-ERROR: " + ((Sendable)localObject2).getId() + " does not exist: " + localInt2ObjectOpenHashMap + ", LOCAL: " + localObject1);
            }
            catch (SynchronizationException localSynchronizationException)
            {
              localSynchronizationException;
              if (!$assertionsDisabled) {
                throw new AssertionError();
              }
            }
          }
          assert ((!paramBoolean) || (((NetworkObject)localObject4).newObject)) : (" failed: forceAll -> objbectNew: " + localObject4 + ": " + ((NetworkObject)localObject4).newObject);
          checkIfNeedsUpdateOrRemoved((Sendable)localObject2, (NetworkObject)localObject4, localObjectArrayList);
        }
        if (localObjectArrayList.isEmpty()) {
          return 0;
        }
        assert ((!paramBoolean) || (j == localObjectArrayList.size())) : (" force all " + j + ": " + localObjectArrayList.size());
        paramDataOutputStream.writeInt(localObjectArrayList.size());
        int k = 0;
        Object localObject2 = localObjectArrayList.iterator();
        while (((Iterator)localObject2).hasNext())
        {
          localObject4 = (Sendable)((Iterator)localObject2).next();
          boolean bool;
          if (!(localObject1 = (NetworkObject)localInt2ObjectOpenHashMap.get(((Sendable)localObject4).getId())).newObject)
          {
            if ((bool = encodePartialObjectIfChanged((Sendable)localObject4, (NetworkObject)localObject1, paramDataOutputStream, paramNetworkStateContainer != paramStateInterface.getLocalAndRemoteObjectContainer()))) {
              k++;
            }
            i = (i != 0) || (bool) ? 1 : 0;
          }
          else
          {
            assert ((!((Boolean)bool.markedDeleted.get()).booleanValue()) || (((Sendable)localObject4).isMarkedForDeleteVolatile()));
            i = 1;
            encodeFullObject((Sendable)localObject4, bool, paramDataOutputStream, false, paramNetworkStateContainer != paramStateInterface.getLocalAndRemoteObjectContainer());
            k++;
          }
          if (((Sendable)localObject4).isMarkedForDeleteVolatile()) {
            ((Sendable)localObject4).setMarkedForDeleteVolatileSent(true);
          }
          clientDebug = false;
        }
        assert (localObjectArrayList.size() == k) : (" WRONG NUMBER OF OBJECTS WRITTEN: " + localObjectArrayList.size() + " / " + k);
        if (paramBoolean)
        {
          localObject2 = localInt2ObjectOpenHashMap.values().iterator();
          while (((Iterator)localObject2).hasNext()) {
            (localObject4 = (NetworkObject)((Iterator)localObject2).next()).newObject = localInt2BooleanOpenHashMap.get((Integer)((NetworkObject)localObject4).field_87.get()).booleanValue();
          }
        }
        if (i != 0) {
          return 1;
        }
        return 0;
      }
    }
  }
  
  private static boolean encodePartialObjectIfChanged(Sendable paramSendable, NetworkObject paramNetworkObject, DataOutputStream paramDataOutputStream, boolean paramBoolean)
  {
    boolean bool = false;
    synchronized (paramNetworkObject)
    {
      if (paramNetworkObject.isChanged())
      {
        assert ((!((Boolean)paramNetworkObject.markedDeleted.get()).booleanValue()) || (paramSendable.isMarkedForDeleteVolatile()));
        bool = true;
        paramSendable = paramNetworkObject.encodeChange(paramSendable, paramDataOutputStream, paramBoolean);
        paramNetworkObject.setChanged(paramSendable);
        paramNetworkObject.newObject = false;
      }
    }
    return bool;
  }
  
  public static void writeObjectForcedWithoutStateChange(NetworkStateContainer paramNetworkStateContainer, StateInterface paramStateInterface, DataOutputStream paramDataOutputStream, boolean paramBoolean)
  {
    paramBoolean = paramNetworkStateContainer.getLocalObjects();
    Int2ObjectOpenHashMap localInt2ObjectOpenHashMap = paramNetworkStateContainer.getRemoteObjects();
    synchronized (paramBoolean)
    {
      synchronized (localInt2ObjectOpenHashMap)
      {
        paramDataOutputStream.writeInt(paramBoolean.size());
        int i = 0;
        Iterator localIterator = paramBoolean.values().iterator();
        while (localIterator.hasNext())
        {
          Sendable localSendable;
          if ((localSendable = (Sendable)localIterator.next()).getId() < 0) {
            throw new IllegalArgumentException("[SENDER] Exception writing negative id for " + localSendable + ": " + localSendable.getId() + " " + localSendable.getState());
          }
          NetworkObject localNetworkObject = (NetworkObject)localInt2ObjectOpenHashMap.get(localSendable.getId());
          assert (localNetworkObject != null) : (localSendable.getId() + " does not exist: " + localInt2ObjectOpenHashMap + ", LOCAL: " + paramBoolean);
          encodeFullObject(localSendable, localNetworkObject, paramDataOutputStream, true, paramNetworkStateContainer != paramStateInterface.getLocalAndRemoteObjectContainer());
          i++;
        }
        if ((!$assertionsDisabled) && (paramBoolean.size() != i)) {
          throw new AssertionError(" WRONG NUMBER OF OBJECTS WRITTEN: " + paramBoolean.size() + " / " + i);
        }
      }
      return;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.synchronization.SynchronizationSender
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */