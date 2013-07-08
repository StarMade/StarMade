import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.schema.game.network.objects.NetworkGameState;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.Sendable;
import org.schema.schine.network.objects.remote.RemoteFloat;
import org.schema.schine.network.objects.remote.RemoteInteger;
import org.schema.schine.network.objects.remote.RemoteString;
import org.schema.schine.network.server.ServerStateInterface;

public class class_800
  implements Sendable
{
  private final StateInterface jdField_field_34_of_type_OrgSchemaSchineNetworkStateInterface;
  private int jdField_field_34_of_type_Int;
  private final boolean jdField_field_34_of_type_Boolean;
  private boolean jdField_field_35_of_type_Boolean;
  private boolean jdField_field_36_of_type_Boolean;
  private NetworkGameState jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkGameState;
  private float jdField_field_34_of_type_Float;
  private float jdField_field_35_of_type_Float;
  private float jdField_field_36_of_type_Float;
  private String jdField_field_34_of_type_JavaLangString;
  private final class_765 jdField_field_34_of_type_Class_765;
  private final class_738 jdField_field_34_of_type_Class_738;
  private String jdField_field_35_of_type_JavaLangString = "(TODO description)";
  private String jdField_field_36_of_type_JavaLangString = "(TODO name)";
  
  public final class_765 a51()
  {
    return this.jdField_field_34_of_type_Class_765;
  }
  
  public class_800(StateInterface paramStateInterface)
  {
    this.jdField_field_34_of_type_OrgSchemaSchineNetworkStateInterface = paramStateInterface;
    this.jdField_field_34_of_type_Boolean = (paramStateInterface instanceof ServerStateInterface);
    if (this.jdField_field_34_of_type_Boolean) {
      this.jdField_field_34_of_type_Float = ((Integer)class_1057.field_1336.a3()).intValue();
    }
    this.jdField_field_34_of_type_Class_765 = new class_765(this);
    this.jdField_field_34_of_type_Class_738 = new class_738(this);
    if (this.jdField_field_34_of_type_Boolean)
    {
      try
      {
        a2();
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
        this.jdField_field_34_of_type_JavaLangString = "";
      }
      this.jdField_field_35_of_type_Float = ((Float)class_1057.field_1342.a3()).floatValue();
      this.jdField_field_36_of_type_Float = ((Float)class_1057.field_1343.a3()).floatValue();
    }
  }
  
  public void cleanUpOnEntityDelete() {}
  
  public int getId()
  {
    return this.jdField_field_34_of_type_Int;
  }
  
  public final NetworkGameState a52()
  {
    return this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkGameState;
  }
  
  public StateInterface getState()
  {
    return this.jdField_field_34_of_type_OrgSchemaSchineNetworkStateInterface;
  }
  
  public void initFromNetworkObject(NetworkObject paramNetworkObject)
  {
    setId(((Integer)paramNetworkObject.field_87.get()).intValue());
    class_765.c1();
    class_738.c1();
    if (!isOnServer())
    {
      this.jdField_field_34_of_type_Float = ((Float)this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkGameState.serverMaxSpeed.get()).floatValue();
      this.jdField_field_35_of_type_Float = ((Float)this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkGameState.linearDamping.get()).floatValue();
      this.jdField_field_36_of_type_Float = ((Float)this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkGameState.rotationalDamping.get()).floatValue();
    }
  }
  
  public void initialize() {}
  
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
    this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkGameState = new NetworkGameState(getState());
  }
  
  public final void a2()
  {
    if (!(localObject = new File("./server-message.txt")).exists())
    {
      ((File)localObject).createNewFile();
      this.jdField_field_34_of_type_JavaLangString = "";
      return;
    }
    Object localObject = new BufferedReader(new FileReader((File)localObject));
    String str = null;
    StringBuffer localStringBuffer = new StringBuffer();
    while ((str = ((BufferedReader)localObject).readLine()) != null) {
      localStringBuffer.append(str + "\n");
    }
    ((BufferedReader)localObject).close();
    this.jdField_field_34_of_type_JavaLangString = localStringBuffer.toString();
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
    this.jdField_field_36_of_type_Boolean = paramBoolean;
  }
  
  public void updateFromNetworkObject(NetworkObject paramNetworkObject)
  {
    if (!this.jdField_field_34_of_type_Boolean) {
      class_1057.b((NetworkGameState)paramNetworkObject);
    }
    this.jdField_field_34_of_type_Class_765.a100(this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkGameState);
    this.jdField_field_34_of_type_Class_738.b16(this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkGameState);
  }
  
  public void updateLocal(class_941 paramclass_941)
  {
    this.jdField_field_34_of_type_Class_765.b4();
    this.jdField_field_34_of_type_Class_738.a13();
  }
  
  public void updateToFullNetworkObject()
  {
    this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkGameState.field_87.set(Integer.valueOf(getId()));
    if ((!field_187) && (this.jdField_field_34_of_type_JavaLangString == null)) {
      throw new AssertionError();
    }
    this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkGameState.serverMessage.set(this.jdField_field_34_of_type_JavaLangString);
    if (this.jdField_field_34_of_type_Boolean)
    {
      class_1057.a(this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkGameState);
      this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkGameState.saveSlotsAllowed.set((Integer)class_1057.field_1332.a3());
      this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkGameState.serverMaxSpeed.set(Float.valueOf(this.jdField_field_34_of_type_Float));
      this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkGameState.linearDamping.set(Float.valueOf(this.jdField_field_35_of_type_Float));
      this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkGameState.rotationalDamping.set(Float.valueOf(this.jdField_field_36_of_type_Float));
    }
    this.jdField_field_34_of_type_Class_765.b16(this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkGameState);
    this.jdField_field_34_of_type_Class_738.a100(this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkGameState);
  }
  
  public void updateToNetworkObject()
  {
    this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkGameState.field_87.set(Integer.valueOf(getId()));
    class_765.d2();
    class_738.b4();
  }
  
  public final class_738 a53()
  {
    return this.jdField_field_34_of_type_Class_738;
  }
  
  public void destroyPersistent() {}
  
  public boolean isMarkedForPermanentDelete()
  {
    return false;
  }
  
  public void markedForPermanentDelete(boolean paramBoolean) {}
  
  public boolean isUpdatable()
  {
    return true;
  }
  
  public final float a54()
  {
    return this.jdField_field_34_of_type_Float;
  }
  
  public String toString()
  {
    return "SenableGameState(" + this.jdField_field_34_of_type_Int + ")";
  }
  
  public final String a55()
  {
    return this.jdField_field_35_of_type_JavaLangString;
  }
  
  public final String b10()
  {
    return this.jdField_field_36_of_type_JavaLangString;
  }
  
  public final float b11()
  {
    return this.jdField_field_35_of_type_Float;
  }
  
  public final float c3()
  {
    return this.jdField_field_36_of_type_Float;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_800
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */