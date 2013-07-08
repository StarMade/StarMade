import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.TreeSet;
import org.schema.game.common.controller.elements.missile.MissileController;
import org.schema.game.network.objects.NetworkClientChannel;
import org.schema.game.network.objects.remote.RemoteFactionNewsPost;
import org.schema.game.network.objects.remote.RemoteFactionNewsPostBuffer;
import org.schema.game.network.objects.remote.RemoteMapEntryRequest;
import org.schema.game.network.objects.remote.RemoteMapEntryRequestBuffer;
import org.schema.game.server.controller.GameServerController;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.client.ClientState;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.Sendable;
import org.schema.schine.network.objects.remote.RemoteArray;
import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
import org.schema.schine.network.objects.remote.RemoteBoolean;
import org.schema.schine.network.objects.remote.RemoteBuffer;
import org.schema.schine.network.objects.remote.RemoteInteger;
import org.schema.schine.network.objects.remote.RemoteLong;
import org.schema.schine.network.objects.remote.RemoteString;
import org.schema.schine.network.objects.remote.RemoteStringArray;
import org.schema.schine.network.server.ServerStateInterface;

public class class_45
  extends Observable
  implements Sendable
{
  private StateInterface jdField_field_34_of_type_OrgSchemaSchineNetworkStateInterface;
  private final boolean jdField_field_34_of_type_Boolean;
  private int jdField_field_34_of_type_Int = -121212;
  private NetworkClientChannel jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel;
  private final TreeSet jdField_field_34_of_type_JavaUtilTreeSet = new TreeSet();
  private final ArrayList jdField_field_34_of_type_JavaUtilArrayList = new ArrayList();
  private final class_622 jdField_field_34_of_type_Class_622;
  private int jdField_field_35_of_type_Int = -12312323;
  private boolean jdField_field_35_of_type_Boolean;
  private boolean jdField_field_36_of_type_Boolean;
  private int jdField_field_36_of_type_Int;
  private final class_343 jdField_field_34_of_type_Class_343;
  
  public class_45(StateInterface paramStateInterface)
  {
    this.jdField_field_34_of_type_OrgSchemaSchineNetworkStateInterface = paramStateInterface;
    this.jdField_field_34_of_type_Boolean = (paramStateInterface instanceof ServerStateInterface);
    this.jdField_field_34_of_type_Class_343 = new class_343(this);
    this.jdField_field_34_of_type_Class_622 = new class_622(this);
  }
  
  public void cleanUpOnEntityDelete() {}
  
  public int getId()
  {
    return this.jdField_field_35_of_type_Int;
  }
  
  public final NetworkClientChannel a()
  {
    return this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel;
  }
  
  public StateInterface getState()
  {
    return this.jdField_field_34_of_type_OrgSchemaSchineNetworkStateInterface;
  }
  
  public void initFromNetworkObject(NetworkObject paramNetworkObject)
  {
    setId(((Integer)this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.field_87.get()).intValue());
    this.jdField_field_34_of_type_Int = ((Integer)this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.playerId.get()).intValue();
  }
  
  public void initialize() {}
  
  public final boolean a1()
  {
    return (this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel != null) && (((Boolean)this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.connectionReady.get()).booleanValue());
  }
  
  public boolean isMarkedForDeleteVolatile()
  {
    return this.jdField_field_35_of_type_Boolean;
  }
  
  public boolean isMarkedForDeleteVolatileSent()
  {
    return this.jdField_field_36_of_type_Boolean;
  }
  
  public boolean isOnServer()
  {
    return this.jdField_field_34_of_type_Boolean;
  }
  
  public void newNetworkObject()
  {
    this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel = new NetworkClientChannel(this.jdField_field_34_of_type_OrgSchemaSchineNetworkStateInterface);
  }
  
  public void setId(int paramInt)
  {
    this.jdField_field_35_of_type_Int = paramInt;
  }
  
  public void setMarkedForDeleteVolatile(boolean paramBoolean)
  {
    this.jdField_field_35_of_type_Boolean = paramBoolean;
  }
  
  public void setMarkedForDeleteVolatileSent(boolean paramBoolean)
  {
    this.jdField_field_36_of_type_Boolean = paramBoolean;
  }
  
  public void updateFromNetworkObject(NetworkObject paramNetworkObject)
  {
    setId(((Integer)this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.field_87.get()).intValue());
    this.jdField_field_34_of_type_Class_622.c();
    if (!isOnServer())
    {
      for (paramNetworkObject = 0; paramNetworkObject < this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.factionNewsPosts.getReceiveBuffer().size(); paramNetworkObject++)
      {
        RemoteFactionNewsPost localRemoteFactionNewsPost = (RemoteFactionNewsPost)this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.factionNewsPosts.getReceiveBuffer().get(paramNetworkObject);
        synchronized (this.jdField_field_34_of_type_JavaUtilArrayList)
        {
          System.err.println("[FACTIONMANAGER] received news on vlient channel " + this.jdField_field_34_of_type_OrgSchemaSchineNetworkStateInterface + ": " + localRemoteFactionNewsPost.get());
          this.jdField_field_34_of_type_JavaUtilArrayList.add(localRemoteFactionNewsPost.get());
        }
      }
      this.jdField_field_34_of_type_Class_343.a1(this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel);
    }
    Object localObject3;
    if (isOnServer())
    {
      ((class_1041)this.jdField_field_34_of_type_OrgSchemaSchineNetworkStateInterface).a67().a2(this);
      Object localObject2;
      for (paramNetworkObject = 0; paramNetworkObject < this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.factionNewsRequests.getReceiveBuffer().size(); paramNetworkObject++)
      {
        long l = ((Long)((RemoteLong)this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.factionNewsRequests.getReceiveBuffer().get(paramNetworkObject)).get()).longValue();
        if (((localObject2 = (Sendable)this.jdField_field_34_of_type_OrgSchemaSchineNetworkStateInterface.getLocalAndRemoteObjectContainer().getLocalObjects().get(this.jdField_field_34_of_type_Int)) != null) && ((localObject2 instanceof class_748))) {
          ((class_748)localObject2).a141().a206(this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel, l);
        } else {
          System.err.println("[SERVER][ClientChannel] player not found: " + this.jdField_field_34_of_type_Int);
        }
      }
      for (paramNetworkObject = 0; paramNetworkObject < this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.timeStampRequests.getReceiveBuffer().size(); paramNetworkObject++)
      {
        localObject3 = (String)((RemoteString)this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.timeStampRequests.getReceiveBuffer().get(paramNetworkObject)).get();
        ??? = new File("./server-skins/" + (String)localObject3 + ".png");
        (localObject2 = new RemoteStringArray(2, this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel)).set(0, (String)localObject3);
        if (((File)???).exists()) {
          ((RemoteStringArray)localObject2).set(1, String.valueOf(((File)???).lastModified()));
        } else {
          ((RemoteStringArray)localObject2).set(1, "0");
        }
        this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.timeStampResponses.add((RemoteArray)localObject2);
      }
      for (paramNetworkObject = 0; paramNetworkObject < this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.fileRequests.getReceiveBuffer().size(); paramNetworkObject++)
      {
        localObject3 = (String)((RemoteString)this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.fileRequests.getReceiveBuffer().get(paramNetworkObject)).get();
        System.err.println("[SERVER] received File request for " + (String)localObject3);
        ((class_1041)this.jdField_field_34_of_type_OrgSchemaSchineNetworkStateInterface).a64(this, (String)localObject3);
      }
      ((GameServerController)getState().getController()).a54().fromNetwork(this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel);
      return;
    }
    for (paramNetworkObject = 0; paramNetworkObject < this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.timeStampResponses.getReceiveBuffer().size(); paramNetworkObject++)
    {
      localObject3 = (RemoteStringArray)this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.timeStampResponses.getReceiveBuffer().get(paramNetworkObject);
      ((class_371)this.jdField_field_34_of_type_OrgSchemaSchineNetworkStateInterface).a4().a30().a2((RemoteStringArray)localObject3);
    }
    ((class_52)getState().getController()).a36().a1(this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel);
  }
  
  public void updateLocal(class_941 arg1)
  {
    Map.Entry localEntry;
    if (!this.jdField_field_34_of_type_Boolean)
    {
      if (((??? = (class_371)getState()).a20().h1() != 0) && (???.a20().h1() != this.jdField_field_36_of_type_Int))
      {
        this.jdField_field_34_of_type_JavaUtilTreeSet.clear();
        a2();
        this.jdField_field_36_of_type_Int = ???.a20().h1();
      }
      Object localObject4 = null;
      Object localObject5;
      if (!(??? = this.jdField_field_34_of_type_Class_343).jdField_field_685_of_type_JavaUtilArrayList.isEmpty()) {
        synchronized (???.jdField_field_685_of_type_JavaUtilArrayList)
        {
          while (!???.jdField_field_685_of_type_JavaUtilArrayList.isEmpty())
          {
            localObject5 = (class_341)???.jdField_field_685_of_type_JavaUtilArrayList.remove(0);
            ???.jdField_field_685_of_type_Class_45.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.mapRequests.add(new RemoteMapEntryRequest((class_341)localObject5, false));
          }
        }
      }
      Object localObject2;
      if (!???.field_686.isEmpty()) {
        synchronized (???.field_686)
        {
          while (!???.field_686.isEmpty())
          {
            localObject5 = (class_339)???.field_686.remove(0);
            localObject4 = null;
            if ((localObject2 = (class_438)???.jdField_field_685_of_type_JavaUtilHashMap.get(((class_339)localObject5).jdField_field_683_of_type_Class_48)) == null)
            {
              (localObject2 = new class_437()).jdField_field_136_of_type_Class_48 = ((class_339)localObject5).jdField_field_683_of_type_Class_48;
              ???.jdField_field_685_of_type_JavaUtilHashMap.put(((class_339)localObject5).jdField_field_683_of_type_Class_48, localObject2);
            }
            ((class_438)localObject2).jdField_field_136_of_type_ArrayOfClass_347 = ((class_339)localObject5).jdField_field_683_of_type_ArrayOfClass_347;
            System.err.println("MAP DATA RECEIVED SIZE: " + ((class_339)localObject5).jdField_field_683_of_type_ArrayOfClass_347.length);
            for (int i = 0; i < ((class_339)localObject5).jdField_field_683_of_type_ArrayOfClass_347.length; i++) {
              System.err.println("MAP DATA RECEIVED " + localObject5.jdField_field_683_of_type_ArrayOfClass_347[i]);
            }
            ((class_339)localObject5).jdField_field_683_of_type_ArrayOfClass_347 = null;
          }
        }
      }
      if ((???.jdField_field_685_of_type_Class_45.getState() instanceof ClientState))
      {
        localEntry = null;
        long l;
        if ((((class_371)???.jdField_field_685_of_type_Class_45.getState()).a14().field_4.field_4.field_4.field_6) && ((l = System.currentTimeMillis()) > 60000L))
        {
          localObject2 = ???.jdField_field_685_of_type_ItUnimiDsiFastutilObjectsObject2LongOpenHashMap.entrySet().iterator();
          while (((Iterator)localObject2).hasNext())
          {
            localEntry = (Map.Entry)((Iterator)localObject2).next();
            if (l - ((Long)localEntry.getValue()).longValue() > 300000L)
            {
              ???.a(new class_48((class_48)localEntry.getKey()));
              ???.jdField_field_685_of_type_ItUnimiDsiFastutilObjectsObject2LongOpenHashMap.put(localEntry.getKey(), l);
            }
          }
        }
      }
      if (!this.jdField_field_34_of_type_JavaUtilArrayList.isEmpty()) {
        synchronized (this.jdField_field_34_of_type_JavaUtilArrayList)
        {
          while (!this.jdField_field_34_of_type_JavaUtilArrayList.isEmpty())
          {
            localObject2 = (class_771)this.jdField_field_34_of_type_JavaUtilArrayList.remove(0);
            if ((!this.jdField_field_34_of_type_JavaUtilTreeSet.isEmpty()) && (((class_771)this.jdField_field_34_of_type_JavaUtilTreeSet.iterator().next()).b5() != ((class_371)this.jdField_field_34_of_type_OrgSchemaSchineNetworkStateInterface).a20().h1()))
            {
              System.err.println("[CLIENT] RECEIVED NEWS OF OTHER FACTION -> cleaning news");
              this.jdField_field_34_of_type_JavaUtilTreeSet.clear();
            }
            this.jdField_field_34_of_type_JavaUtilTreeSet.add(localObject2);
            setChanged();
            notifyObservers();
          }
        }
      }
    }
    try
    {
      this.jdField_field_34_of_type_Class_622.d();
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
      this.jdField_field_34_of_type_Class_622.b_();
    }
    if ((!this.jdField_field_34_of_type_Boolean) && (!getState().getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(this.jdField_field_34_of_type_Int)))
    {
      setMarkedForDeleteVolatile(true);
      System.err.println("[SERVER][CLIENTCHANNEL] DELETING: no more player attached");
      localEntry = null;
      this.jdField_field_34_of_type_Class_622.b_();
    }
  }
  
  public final void a2()
  {
    if (this.jdField_field_34_of_type_JavaUtilTreeSet.isEmpty())
    {
      this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.factionNewsRequests.add(new RemoteLong(Long.valueOf(System.currentTimeMillis()), this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel));
      return;
    }
    this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.factionNewsRequests.add(new RemoteLong(Long.valueOf(((class_771)this.jdField_field_34_of_type_JavaUtilTreeSet.iterator().next()).a28()), this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel));
  }
  
  public void updateToFullNetworkObject()
  {
    this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.playerId.set(Integer.valueOf(this.jdField_field_34_of_type_Int));
    this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.field_87.set(Integer.valueOf(getId()));
    updateToNetworkObject();
  }
  
  public void updateToNetworkObject()
  {
    if (isOnServer()) {
      this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.connectionReady.set(Boolean.valueOf(true));
    }
  }
  
  public final void a3(String paramString)
  {
    this.jdField_field_34_of_type_Class_622.b(paramString);
    this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.fileRequests.add(new RemoteString(paramString, this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel));
  }
  
  public final void b(String paramString)
  {
    this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.timeStampRequests.add(new RemoteString(paramString, this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel));
  }
  
  public final int a4()
  {
    return this.jdField_field_34_of_type_Int;
  }
  
  public final void a5(int paramInt)
  {
    this.jdField_field_34_of_type_Int = paramInt;
  }
  
  public final class_622 a6()
  {
    return this.jdField_field_34_of_type_Class_622;
  }
  
  public final TreeSet a7()
  {
    return this.jdField_field_34_of_type_JavaUtilTreeSet;
  }
  
  public void destroyPersistent() {}
  
  public boolean isMarkedForPermanentDelete()
  {
    return false;
  }
  
  public void markedForPermanentDelete(boolean paramBoolean) {}
  
  public final class_343 a8()
  {
    return this.jdField_field_34_of_type_Class_343;
  }
  
  public boolean isUpdatable()
  {
    return true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_45
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */