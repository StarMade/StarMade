package org.schema.schine.network.synchronization;

import class_1421;
import it.unimi.dsi.fastutil.bytes.ByteArrayList;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import java.io.DataInputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.Iterator;
import org.schema.common.util.ByteUtil;
import org.schema.schine.network.ControllerInterface;
import org.schema.schine.network.NetUtil;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.client.ClientStateInterface;
import org.schema.schine.network.exception.SynchronizationException;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.Sendable;
import org.schema.schine.network.objects.remote.RemoteBoolean;
import org.schema.schine.network.objects.remote.RemoteInteger;
import org.schema.schine.network.server.ServerStateInterface;

public class SynchronizationReceiver
{
  public static Class construcorStateClass = StateInterface.class;
  public static boolean serverDebug;
  
  private static void handleChanged(NetworkStateContainer paramNetworkStateContainer, int paramInt1, StateInterface paramStateInterface, DataInputStream paramDataInputStream, short paramShort, int paramInt2)
  {
    Int2ObjectOpenHashMap localInt2ObjectOpenHashMap = paramNetworkStateContainer.getRemoteObjects();
    synchronized (localObject1 = paramNetworkStateContainer.getLocalObjects())
    {
      synchronized (localInt2ObjectOpenHashMap)
      {
        Object localObject1;
        NetworkObject localNetworkObject = (NetworkObject)localInt2ObjectOpenHashMap.get(paramInt1);
        assert (localNetworkObject != null) : ("could not find entity " + paramInt1 + "; available remote entities: " + localInt2ObjectOpenHashMap);
        synchronized (localNetworkObject)
        {
          localObject1 = (Sendable)((Int2ObjectOpenHashMap)localObject1).get(paramInt1);
          localNetworkObject.field_87.set(Integer.valueOf(((Sendable)localObject1).getId()));
          if ((localObject1.toString().startsWith("Missile")) && ((paramStateInterface instanceof ServerStateInterface)))
          {
            System.err.println("SERVER MISSILE UPDATE " + localObject1);
            serverDebug = true;
          }
          localNetworkObject.decodeChange(paramStateInterface, paramDataInputStream, paramShort, paramNetworkStateContainer != paramStateInterface.getLocalAndRemoteObjectContainer(), paramInt2);
          assert (localObject1 != null) : ("Object with id " + paramInt1 + " is NOT local yet " + paramNetworkStateContainer.getLocalObjects());
          ((Sendable)localObject1).updateFromNetworkObject(localNetworkObject);
          localNetworkObject.clearReceiveBuffers();
          serverDebug = false;
        }
      }
      return;
    }
  }
  
  public static void handleDeleted(NetworkStateContainer paramNetworkStateContainer, StateInterface paramStateInterface, Collection paramCollection)
  {
    paramCollection.clear();
    synchronized (paramNetworkStateContainer.getLocalObjects())
    {
      synchronized (paramNetworkStateContainer.getRemoteObjects())
      {
        Iterator localIterator = paramNetworkStateContainer.getLocalObjects().values().iterator();
        Object localObject1;
        Object localObject2;
        while (localIterator.hasNext())
        {
          localObject1 = (Sendable)localIterator.next();
          if (((localObject2 = (NetworkObject)paramNetworkStateContainer.getRemoteObjects().get(((Sendable)localObject1).getId())) != null) && (((Boolean)((NetworkObject)localObject2).markedDeleted.get()).booleanValue())) {
            if (((paramStateInterface instanceof ServerStateInterface)) && (!((Sendable)localObject1).isMarkedForDeleteVolatileSent()))
            {
              System.err.println("[SERVER] delete not yet sent: " + localObject1);
            }
            else
            {
              paramCollection.add(Integer.valueOf(((Sendable)localObject1).getId()));
              ((NetworkObject)localObject2).onDelete(paramStateInterface);
              ((Sendable)localObject1).cleanUpOnEntityDelete();
              paramNetworkStateContainer.getGhostObjects().put(((Sendable)localObject1).getId(), new GhostSendable(System.currentTimeMillis(), (Sendable)localObject1));
            }
          }
        }
        if (paramCollection.size() > 0)
        {
          localIterator = paramCollection.iterator();
          while (localIterator.hasNext())
          {
            localObject1 = (Integer)localIterator.next();
            localObject2 = paramNetworkStateContainer.removeLocal(((Integer)localObject1).intValue());
            paramNetworkStateContainer.getRemoteObjects().remove(localObject1);
            paramStateInterface.getController().onRemoveEntity((Sendable)localObject2);
            paramStateInterface.notifyOfRemovedObject((Sendable)localObject2);
            System.err.println("[DELETE][" + paramStateInterface + "] Sendable " + localObject1 + "(" + localObject2 + ") Physically DELETING DONE and Notified!");
          }
        }
      }
    }
    paramNetworkStateContainer.checkGhostObjects();
  }
  
  private static void handleNewObject(NetworkStateContainer paramNetworkStateContainer, int paramInt1, int paramInt2, byte paramByte, StateInterface paramStateInterface, DataInputStream paramDataInputStream, short paramShort, boolean paramBoolean)
  {
    Int2ObjectOpenHashMap localInt2ObjectOpenHashMap1 = paramNetworkStateContainer.getRemoteObjects();
    Int2ObjectOpenHashMap localInt2ObjectOpenHashMap2 = paramNetworkStateContainer.getLocalObjects();
    Sendable localSendable = null;
    synchronized (localInt2ObjectOpenHashMap2)
    {
      synchronized (localInt2ObjectOpenHashMap1)
      {
        if (localInt2ObjectOpenHashMap2.containsKey(paramInt1))
        {
          localSendable = (Sendable)localInt2ObjectOpenHashMap2.get(paramInt1);
        }
        else
        {
          Class localClass;
          if ((localClass = NetUtil.getSendableClass(paramByte)) == null) {
            throw new NullPointerException("WRONG CLASS ID RECEIVED: " + paramByte + "; container: " + paramNetworkStateContainer.getClass());
          }
          localSendable = (Sendable)localClass.getConstructor(new Class[] { construcorStateClass }).newInstance(new Object[] { paramStateInterface });
        }
        synchronized (localSendable)
        {
          long l1 = System.currentTimeMillis();
          localSendable.initialize();
          localSendable.newNetworkObject();
          localSendable.getNetworkObject().init();
          synchronized (paramByte = localSendable.getNetworkObject())
          {
            (paramByte = NetworkObject.decode(paramStateInterface, paramDataInputStream, paramByte, paramShort, paramNetworkStateContainer != paramStateInterface.getLocalAndRemoteObjectContainer(), paramInt2)).onInit(paramStateInterface);
            if (paramInt1 != ((Integer)paramByte.field_87.get()).intValue())
            {
              if ((paramStateInterface instanceof ClientStateInterface))
              {
                String str = "[ERROR] in " + paramStateInterface + " received changed object \n|stream for a new object. the id of the received object could \n|not be decoded because it wasnt sent (never ment to be sent). \n|the obj was probably create on the server without knowlegde of \n|this client and has therefore to be re-requested\n|[NTID(" + paramByte.field_87.get() + ") != receivedId[" + paramInt1 + "] received]; (SenderID: " + paramInt2 + "), \n|isSynched(" + paramBoolean + ") that was not yet created in " + paramStateInterface + ", \n|SCHEDULING RESYNC. current remotes: " + localInt2ObjectOpenHashMap1 + ", local: " + localInt2ObjectOpenHashMap2 + "; container: " + paramNetworkStateContainer.getClass();
                throw new SynchronizationException(str);
              }
              if (!$assertionsDisabled) {
                throw new AssertionError("NEW object not correctly en/decoded (probably en)\n on " + paramStateInterface + " received ident: " + paramInt1 + ", \nencoded: " + paramByte.field_87.get() + ", \nmarkedForDel: " + paramByte.markedDeleted.get() + ", \nSENDER: " + paramInt2 + ", \nsynchronized = " + paramBoolean + ", \nremotes: " + paramNetworkStateContainer);
              }
            }
            if (((Integer)paramByte.field_87.get()).intValue() < 0)
            {
              System.err.println("[ERROR][CRITICAL]something fucked up: received id for new object:  " + paramByte.field_87.get());
              return;
            }
            localSendable.setId(((Integer)paramByte.field_87.get()).intValue());
            localSendable.initFromNetworkObject(paramByte);
            if ((localSendable instanceof class_1421)) {
              ((class_1421)localSendable).initPhysics();
            }
            localSendable.updateFromNetworkObject(paramByte);
            paramByte.clearReceiveBuffers();
            paramNetworkStateContainer.putLocal(localSendable.getId(), localSendable);
            if ((paramStateInterface instanceof ClientStateInterface)) {
              paramByte.newObject = false;
            }
            paramByte.addObserversForFields();
            localInt2ObjectOpenHashMap1.put((Integer)paramByte.field_87.get(), paramByte);
            paramStateInterface.notifyOfAddedObject(localSendable);
            long l2;
            if ((l2 = System.currentTimeMillis() - l1) > 10L) {
              System.err.println("[SYNC-RECEIVER] " + paramStateInterface + " DECODING OF NEW OBJECT " + localSendable + " TOOK " + l2);
            }
          }
        }
      }
      return;
    }
  }
  
  public static void update(NetworkStateContainer paramNetworkStateContainer, int paramInt, DataInputStream paramDataInputStream, StateInterface paramStateInterface, boolean paramBoolean1, boolean paramBoolean2, short paramShort)
  {
    Int2ObjectOpenHashMap localInt2ObjectOpenHashMap1 = paramNetworkStateContainer.getRemoteObjects();
    if (paramStateInterface.isReady())
    {
      int i = paramDataInputStream.readInt();
      if (paramBoolean2) {
        System.err.println("[SYNCHRONIZE] FORCED UPDATE");
      }
      int j = 127;
      int n;
      byte b;
      for (int k = 0; k < i; k++)
      {
        n = ByteUtil.a10(paramDataInputStream);
        if (paramBoolean2)
        {
          Object localObject1 = null;
          synchronized (paramNetworkStateContainer.getLocalObjects())
          {
            synchronized (localInt2ObjectOpenHashMap1)
            {
              localInt2ObjectOpenHashMap1.remove(n);
            }
          }
        }
        b = paramDataInputStream.readByte();
        ??? = null;
        if ((paramBoolean1) && (localInt2ObjectOpenHashMap1.containsKey(n)) && (!paramBoolean2))
        {
          handleChanged(paramNetworkStateContainer, n, paramStateInterface, paramDataInputStream, paramShort, paramInt);
        }
        else if ((??? = (GhostSendable)paramNetworkStateContainer.getGhostObjects().get(n)) != null)
        {
          System.err.println(paramStateInterface + ": Exception: Received update for ghost object: " + n + "; ignoring update");
          ((GhostSendable)???).sendable.getNetworkObject().decodeChange(paramStateInterface, paramDataInputStream, paramShort, paramNetworkStateContainer != paramStateInterface.getLocalAndRemoteObjectContainer(), paramInt);
          System.err.println(paramStateInterface + ": Exception: Received update for ghost object: " + n + "; ignoring update: DECODED CHANGE TO GHOST OBJECT " + paramNetworkStateContainer.getGhostObjects().get(n));
        }
        else
        {
          try
          {
            handleNewObject(paramNetworkStateContainer, n, paramInt, b, paramStateInterface, paramDataInputStream, paramShort, paramBoolean1);
          }
          catch (NoSuchMethodException localNoSuchMethodException)
          {
            localNoSuchMethodException.printStackTrace();
            throw new RuntimeException("the object " + b + " does not provide an acceptable contructor to create an instance from a remote object");
          }
        }
        paramNetworkStateContainer.debugReceivedClasses.add(b);
      }
      Int2ObjectOpenHashMap localInt2ObjectOpenHashMap2;
      if ((paramBoolean2) && (!(localInt2ObjectOpenHashMap2 = paramNetworkStateContainer.getLocalObjects()).keySet().equals(localInt2ObjectOpenHashMap1.keySet()))) {
        try
        {
          System.err.println("LOCAL : " + localInt2ObjectOpenHashMap2);
          System.err.println("REMOTE: " + localInt2ObjectOpenHashMap1);
          throw new SynchronizationException("[CLIENT] " + paramStateInterface + " invalid synchronization state: local and remote objects differ");
        }
        catch (SynchronizationException localSynchronizationException)
        {
          localSynchronizationException;
          if (!$assertionsDisabled) {
            throw new AssertionError();
          }
        }
      }
      if (paramDataInputStream.available() > 0)
      {
        System.err.println("[ERROR][CRITICAL] leftover bytes: " + paramDataInputStream.available() + " last decoded class: " + NetUtil.getSendableClass(b));
        for (int m = 0; m < paramNetworkStateContainer.debugReceivedClasses.size(); m++) {
          System.err.println("[DEBUGINFO] decoded class #1: " + NetUtil.getSendableClass(paramNetworkStateContainer.debugReceivedClasses.get(m).byteValue()));
        }
        m = paramDataInputStream.readInt();
        n = paramDataInputStream.readByte();
        System.err.println("[DEBUGINFO] into container: " + paramNetworkStateContainer.getClass());
        System.err.println("[DEBUGINFO] decoded next ID: " + m + "; class " + n + " = " + NetUtil.getSendableClass(n));
        System.err.println("[DEBUGINFO] if class and object id are sane, this means there was a wrong update size transferred");
        System.err.println("[DEBUGINFO] if not, an object did not decode correctly");
      }
    }
    paramNetworkStateContainer.debugReceivedClasses.clear();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.synchronization.SynchronizationReceiver
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */