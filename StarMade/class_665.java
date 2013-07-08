import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import javax.vecmath.Vector3f;
import org.schema.common.util.ByteUtil;
import org.schema.game.network.objects.NetworkSector;
import org.schema.game.network.objects.remote.RemoteItem;
import org.schema.game.network.objects.remote.RemoteItemBuffer;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.Sendable;
import org.schema.schine.network.objects.remote.RemoteBoolean;
import org.schema.schine.network.objects.remote.RemoteInteger;
import org.schema.schine.network.objects.remote.RemoteVector3i;
import org.schema.schine.network.objects.remote.Streamable;
import org.schema.schine.network.server.ServerStateInterface;

public class class_665
  implements Sendable
{
  private final StateInterface jdField_field_34_of_type_OrgSchemaSchineNetworkStateInterface;
  private int jdField_field_34_of_type_Int = -1;
  private final boolean jdField_field_34_of_type_Boolean;
  private boolean jdField_field_35_of_type_Boolean;
  private boolean field_36;
  private NetworkSector jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSector;
  private Map jdField_field_34_of_type_JavaUtilMap = new HashMap();
  private final ArrayList jdField_field_34_of_type_JavaUtilArrayList = new ArrayList();
  private final ArrayList jdField_field_35_of_type_JavaUtilArrayList = new ArrayList();
  private Iterator jdField_field_34_of_type_JavaUtilIterator;
  private long jdField_field_34_of_type_Long;
  private HashSet jdField_field_34_of_type_JavaUtilHashSet = new HashSet();
  private class_670 jdField_field_34_of_type_Class_670;
  
  public class_665(StateInterface paramStateInterface)
  {
    this.jdField_field_34_of_type_OrgSchemaSchineNetworkStateInterface = paramStateInterface;
    this.jdField_field_34_of_type_Boolean = (paramStateInterface instanceof ServerStateInterface);
  }
  
  public String toString()
  {
    if (isOnServer())
    {
      if (a38() != null) {
        return "[SERVER RemoteSector(" + this.jdField_field_34_of_type_Int + ")" + a38().a7() + "; " + a38().field_136 + "]";
      }
      return "[SERVER RemoteSector(" + this.jdField_field_34_of_type_Int + ") sector removed]";
    }
    return "[CLIENT ReSector(" + this.jdField_field_34_of_type_Int + ")" + ((Boolean)this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSector.active.get()).booleanValue() + "; " + a34() + "; ]";
  }
  
  public final class_48 a34()
  {
    return this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSector.pos.getVector();
  }
  
  public void cleanUpOnEntityDelete() {}
  
  public int getId()
  {
    return this.jdField_field_34_of_type_Int;
  }
  
  public final NetworkSector a35()
  {
    return this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSector;
  }
  
  public StateInterface getState()
  {
    return this.jdField_field_34_of_type_OrgSchemaSchineNetworkStateInterface;
  }
  
  public void initFromNetworkObject(NetworkObject paramNetworkObject)
  {
    setId(((Integer)paramNetworkObject.field_87.get()).intValue());
  }
  
  public void initialize()
  {
    if (!isOnServer()) {
      this.jdField_field_34_of_type_JavaUtilMap = new HashMap();
    }
  }
  
  public boolean isMarkedForDeleteVolatile()
  {
    return this.jdField_field_35_of_type_Boolean;
  }
  
  public boolean isMarkedForDeleteVolatileSent()
  {
    return this.field_36;
  }
  
  public boolean isOnServer()
  {
    return this.jdField_field_34_of_type_Boolean;
  }
  
  public void newNetworkObject()
  {
    this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSector = new NetworkSector(getState());
  }
  
  public void setId(int paramInt)
  {
    this.jdField_field_34_of_type_Int = paramInt;
  }
  
  public void setMarkedForDeleteVolatile(boolean paramBoolean)
  {
    this.jdField_field_35_of_type_Boolean = paramBoolean;
  }
  
  public void setMarkedForDeleteVolatileSent(boolean paramBoolean)
  {
    this.field_36 = paramBoolean;
  }
  
  public void updateFromNetworkObject(NetworkObject paramNetworkObject)
  {
    NetworkSector localNetworkSector = (NetworkSector)paramNetworkObject;
    paramNetworkObject = this;
    for (int i = 0; i < localNetworkSector.itemBuffer.getReceiveBuffer().size(); i++)
    {
      RemoteItem localRemoteItem;
      if ((localRemoteItem = (RemoteItem)localNetworkSector.itemBuffer.getReceiveBuffer().get(i)).isAdd()) {
        paramNetworkObject.a37((class_637)localRemoteItem.get());
      } else {
        paramNetworkObject.a5(((class_637)localRemoteItem.get()).b5());
      }
    }
  }
  
  public void updateLocal(class_941 paramclass_941)
  {
    if (isOnServer())
    {
      paramclass_941 = null;
      if ((!this.jdField_field_34_of_type_JavaUtilMap.isEmpty()) || (!this.jdField_field_34_of_type_JavaUtilArrayList.isEmpty()) || (!this.jdField_field_35_of_type_JavaUtilArrayList.isEmpty()))
      {
        paramclass_941 = ((class_1041)getState()).b10().values().iterator();
        while (paramclass_941.hasNext())
        {
          class_748 localclass_748;
          if ((((localclass_748 = (class_748)paramclass_941.next()) instanceof class_748)) && (localclass_748.c2() == getId())) {
            this.jdField_field_34_of_type_JavaUtilHashSet.add(localclass_748);
          }
        }
        a11(true);
        this.jdField_field_34_of_type_JavaUtilHashSet.clear();
      }
    }
    else
    {
      a11(false);
      paramclass_941 = this;
      if ((!field_187) && (!(paramclass_941.jdField_field_34_of_type_OrgSchemaSchineNetworkStateInterface instanceof class_371))) {
        throw new AssertionError();
      }
      ((class_371)paramclass_941.jdField_field_34_of_type_OrgSchemaSchineNetworkStateInterface).a8();
    }
  }
  
  public final void a36(Vector3f paramVector3f, short paramShort, int paramInt)
  {
    if (paramShort != 0) {
      a37(new class_637(class_1041.field_148++, paramShort, paramInt, paramVector3f));
    }
  }
  
  private void a37(class_637 paramclass_637)
  {
    synchronized (this.jdField_field_34_of_type_JavaUtilMap)
    {
      this.jdField_field_34_of_type_JavaUtilArrayList.add(paramclass_637);
      return;
    }
  }
  
  public final void a5(int paramInt)
  {
    synchronized (this.jdField_field_34_of_type_JavaUtilMap)
    {
      if ((class_637)this.jdField_field_34_of_type_JavaUtilMap.get(Integer.valueOf(paramInt)) != null) {
        this.jdField_field_35_of_type_JavaUtilArrayList.add(Integer.valueOf(paramInt));
      } else {
        System.err.println((isOnServer() ? "[SERVER]" : "[CLIENT]") + "[RemoteSector] WARNING: trying to delete item id that doesn't exist: " + paramInt);
      }
      return;
    }
  }
  
  private void a11(boolean paramBoolean)
  {
    Object localObject2;
    if (isOnServer())
    {
      long l = System.currentTimeMillis();
      if ((this.jdField_field_34_of_type_JavaUtilIterator != null) && (l - this.jdField_field_34_of_type_Long > 200L))
      {
        if (!this.jdField_field_34_of_type_JavaUtilIterator.hasNext()) {
          this.jdField_field_34_of_type_JavaUtilIterator = this.jdField_field_34_of_type_JavaUtilMap.values().iterator();
        }
        for (int i = 0; (this.jdField_field_34_of_type_JavaUtilIterator.hasNext()) && (i < 100); i++) {
          if (!(localObject2 = (class_637)this.jdField_field_34_of_type_JavaUtilIterator.next()).a33(l))
          {
            a5(((class_637)localObject2).b5());
          }
          else
          {
            if ((((class_637)localObject2).a7()) && (((class_637)localObject2).a39(a38())) && (paramBoolean)) {
              this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSector.itemBuffer.add(new RemoteItem((class_637)localObject2, Boolean.valueOf(true), this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSector));
            }
            Iterator localIterator = this.jdField_field_34_of_type_JavaUtilHashSet.iterator();
            while ((localIterator.hasNext()) && (!((class_748)localIterator.next()).a118((class_637)localObject2))) {}
          }
        }
        if (!this.jdField_field_34_of_type_JavaUtilIterator.hasNext()) {
          this.jdField_field_34_of_type_Long = l;
        }
      }
    }
    Object localObject1;
    class_637 localclass_637;
    if (!this.jdField_field_34_of_type_JavaUtilArrayList.isEmpty()) {
      synchronized (this.jdField_field_34_of_type_JavaUtilMap)
      {
        while (!this.jdField_field_34_of_type_JavaUtilArrayList.isEmpty())
        {
          localObject1 = (class_637)this.jdField_field_34_of_type_JavaUtilArrayList.remove(0);
          if ((localclass_637 = (class_637)this.jdField_field_34_of_type_JavaUtilMap.put(Integer.valueOf(((class_637)localObject1).b5()), localObject1)) != null)
          {
            ((class_637)localObject1).a38(localclass_637.a28());
            System.err.println("[REMOTESECTOR] " + getState() + " ITEM change: " + localclass_637 + " -> " + localObject1);
          }
          System.err.println("[REMOTESECTOR] ITEM ADDED: " + localObject1 + ": Total: " + this.jdField_field_34_of_type_JavaUtilMap.size());
          if (paramBoolean) {
            this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSector.itemBuffer.add(new RemoteItem((class_637)localObject1, Boolean.valueOf(true), this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSector));
          }
          this.jdField_field_34_of_type_JavaUtilIterator = this.jdField_field_34_of_type_JavaUtilMap.values().iterator();
        }
      }
    }
    if (!this.jdField_field_35_of_type_JavaUtilArrayList.isEmpty()) {
      synchronized (this.jdField_field_34_of_type_JavaUtilMap)
      {
        while (!this.jdField_field_35_of_type_JavaUtilArrayList.isEmpty())
        {
          localObject1 = (Integer)this.jdField_field_35_of_type_JavaUtilArrayList.remove(0);
          localclass_637 = (class_637)this.jdField_field_34_of_type_JavaUtilMap.remove(localObject1);
          if ((paramBoolean) && (localclass_637 != null))
          {
            localObject2 = new RemoteItem(localclass_637, Boolean.valueOf(false), this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSector);
            this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSector.itemBuffer.add((Streamable)localObject2);
          }
          else if (localclass_637 == null)
          {
            System.err.println("[SERVER][REMOTESECTOR] deleted invalid id: " + localObject1);
          }
          this.jdField_field_34_of_type_JavaUtilIterator = this.jdField_field_34_of_type_JavaUtilMap.values().iterator();
        }
        return;
      }
    }
  }
  
  private class_670 a38()
  {
    if ((!field_187) && (!(this.jdField_field_34_of_type_OrgSchemaSchineNetworkStateInterface instanceof class_1041))) {
      throw new AssertionError();
    }
    return this.jdField_field_34_of_type_Class_670;
  }
  
  public void updateToFullNetworkObject()
  {
    this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSector.field_87.set(Integer.valueOf(getId()));
    class_665 localclass_665 = this;
    synchronized (this.jdField_field_34_of_type_JavaUtilMap)
    {
      Iterator localIterator = localclass_665.jdField_field_34_of_type_JavaUtilMap.values().iterator();
      while (localIterator.hasNext())
      {
        class_637 localclass_637 = (class_637)localIterator.next();
        localclass_665.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSector.itemBuffer.add(new RemoteItem(localclass_637, Boolean.valueOf(true), localclass_665.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSector));
      }
    }
    this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSector.pos.set(a38().field_136);
    this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSector.active.set(Boolean.valueOf(a38().a7()));
    Object localObject2;
    int i = ByteUtil.d((localObject2 = a38().field_136).field_475);
    int j = ByteUtil.d(((class_48)localObject2).field_476);
    int k = ByteUtil.d(((class_48)localObject2).field_477);
    (localObject2 = new Vector3f()).set(i - 8, j - 8, k - 8);
    if (((Vector3f)localObject2).length() >= 6.5F) {
      ((Vector3f)localObject2).length();
    }
    class_791.a19(a38().field_136);
  }
  
  public void updateToNetworkObject()
  {
    if (isOnServer())
    {
      this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSector.field_87.set(Integer.valueOf(getId()));
      this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSector.active.set(Boolean.valueOf(a38().a7()));
    }
  }
  
  public final void a39(class_670 paramclass_670)
  {
    this.jdField_field_34_of_type_Int = paramclass_670.a3();
    this.jdField_field_34_of_type_Class_670 = paramclass_670;
  }
  
  public final Map a40()
  {
    return this.jdField_field_34_of_type_JavaUtilMap;
  }
  
  public final void a41(Map paramMap)
  {
    this.jdField_field_34_of_type_JavaUtilMap = paramMap;
    this.jdField_field_34_of_type_JavaUtilIterator = paramMap.values().iterator();
  }
  
  public void destroyPersistent() {}
  
  public boolean isMarkedForPermanentDelete()
  {
    return false;
  }
  
  public void markedForPermanentDelete(boolean paramBoolean) {}
  
  public static void a42(class_1041 paramclass_1041, Collection paramCollection)
  {
    Iterator localIterator = paramclass_1041.b10().values().iterator();
    while (localIterator.hasNext())
    {
      class_748 localclass_748 = (class_748)localIterator.next();
      Sendable localSendable;
      if ((localSendable = (Sendable)paramclass_1041.getLocalAndRemoteObjectContainer().getLocalObjects().get(localclass_748.c2())) != null) {
        paramCollection.add((class_665)localSendable);
      } else {
        System.err.println("[SERVER][REMOTESECTOR] WARNING: REMOTE SECTOR FOR " + localclass_748 + " NOT FOUND: " + localclass_748.c2());
      }
    }
  }
  
  public boolean isUpdatable()
  {
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_665
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */