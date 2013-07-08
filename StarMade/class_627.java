import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.remote.RemoteIntArray;
import org.schema.schine.network.objects.remote.RemoteIntArrayBuffer;

public abstract class class_627
  extends class_639
{
  boolean field_136;
  
  public class_627(class_635 paramclass_635, class_48 paramclass_48)
  {
    super(paramclass_635, paramclass_48);
  }
  
  public String toString()
  {
    return "ActiveInventory: " + this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.toString();
  }
  
  public abstract void a13();
  
  public final void b4()
  {
    this.jdField_field_136_of_type_Boolean = true;
  }
  
  public final boolean a7()
  {
    return this.jdField_field_136_of_type_Boolean;
  }
  
  public final void c1()
  {
    RemoteIntArray localRemoteIntArray;
    (localRemoteIntArray = new RemoteIntArray(3, (NetworkObject)this.jdField_field_136_of_type_Class_635.getInventoryNetworkObject())).set(0, this.jdField_field_136_of_type_Class_48.field_475);
    localRemoteIntArray.set(1, this.jdField_field_136_of_type_Class_48.field_476);
    localRemoteIntArray.set(2, this.jdField_field_136_of_type_Class_48.field_477);
    this.jdField_field_136_of_type_Class_635.getInventoryNetworkObject().getInventoryActivateBuffer().add(localRemoteIntArray);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_627
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */