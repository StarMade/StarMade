import java.util.LinkedList;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.NetworkRemoteController;
import org.schema.schine.network.objects.Sendable;

public final class class_1040
  implements Sendable
{
  private int jdField_field_34_of_type_Int;
  private boolean jdField_field_34_of_type_Boolean;
  private boolean field_35;
  private boolean field_36;
  
  public final void cleanUpOnEntityDelete() {}
  
  public static LinkedList a56()
  {
    return null;
  }
  
  public final int getId()
  {
    return this.jdField_field_34_of_type_Int;
  }
  
  public final StateInterface getState()
  {
    return null;
  }
  
  public final void initFromNetworkObject(NetworkObject paramNetworkObject)
  {
    NetworkRemoteController localNetworkRemoteController = (NetworkRemoteController)paramNetworkObject;
    paramNetworkObject = this;
    if (localNetworkRemoteController == null) {
      throw new IllegalArgumentException("[ERROR] Network error!");
    }
    int i = null.size();
    for (int j = 0; j < i; j++)
    {
      class_1042 localclass_1042 = (class_1042)null.get(j);
      if (localNetworkRemoteController.fTypes[j] == 0)
      {
        if ((localclass_1042 instanceof class_1036)) {
          localclass_1042.a2(Float.valueOf(Float.parseFloat(localNetworkRemoteController.fValues[j])));
        }
        if ((localclass_1042 instanceof class_1038)) {
          localclass_1042.a2(localNetworkRemoteController.fValues[j]);
        }
      }
      if ((localNetworkRemoteController.fTypes[j] == 1) && (localNetworkRemoteController.fValues[j].equals("1")))
      {
        localNetworkRemoteController.fValues[j] = "";
        localclass_1042.a();
        paramNetworkObject.jdField_field_34_of_type_Boolean = true;
      }
    }
  }
  
  public final void initialize() {}
  
  public static boolean a1()
  {
    return null.jdField_field_34_of_type_Boolean;
  }
  
  public final boolean isMarkedForDeleteVolatile()
  {
    return this.field_35;
  }
  
  public final boolean isMarkedForDeleteVolatileSent()
  {
    return this.field_36;
  }
  
  public final boolean isOnServer()
  {
    return false;
  }
  
  public final void newNetworkObject() {}
  
  public static void a2()
  {
    null.jdField_field_34_of_type_Boolean = true;
  }
  
  public final void setId(int paramInt)
  {
    this.jdField_field_34_of_type_Int = paramInt;
  }
  
  public final void setMarkedForDeleteVolatile(boolean paramBoolean)
  {
    this.field_35 = paramBoolean;
  }
  
  public final void setMarkedForDeleteVolatileSent(boolean paramBoolean)
  {
    this.field_36 = paramBoolean;
  }
  
  public final String toString()
  {
    return null + "[" + this.jdField_field_34_of_type_Int + "] " + null;
  }
  
  public final void updateFromNetworkObject(NetworkObject paramNetworkObject) {}
  
  public final void updateLocal(class_941 paramclass_941) {}
  
  public final void updateToFullNetworkObject() {}
  
  public final void updateToNetworkObject()
  {
    NetworkRemoteController localNetworkRemoteController;
    (localNetworkRemoteController = new NetworkRemoteController(getState())).field_87 = this.jdField_field_34_of_type_Int;
    localNetworkRemoteController.entityID = null.a3();
    int i = null.size();
    if (localNetworkRemoteController.fNames == null)
    {
      localNetworkRemoteController.fNames = new String[i];
      localNetworkRemoteController.fTypes = new int[i];
      localNetworkRemoteController.fValues = new String[i];
      localNetworkRemoteController.fControllable = new int[i];
    }
    for (int j = 0; j < i; j++)
    {
      localNetworkRemoteController.fNames[j] = ((class_1042)null.get(j)).a1();
      null.get(j);
      localNetworkRemoteController.fTypes[j] = 0;
      localNetworkRemoteController.fValues[j] = ((class_1042)null.get(j)).a3().toString();
      null.get(j);
      localNetworkRemoteController.fControllable[j] = 0;
    }
    localNetworkRemoteController.name = null;
    localNetworkRemoteController.customName = null;
  }
  
  public final void destroyPersistent() {}
  
  public final boolean isMarkedForPermanentDelete()
  {
    return false;
  }
  
  public final void markedForPermanentDelete(boolean paramBoolean) {}
  
  public final boolean isUpdatable()
  {
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1040
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */