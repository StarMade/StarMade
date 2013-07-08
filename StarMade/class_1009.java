import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.schema.game.common.data.element.ControlElementMap;
import org.schema.game.server.controller.GameServerController;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.SynchronizationContainerController;

public final class class_1009
  extends class_1133
{
  private int field_227;
  
  public class_1009(StateInterface paramStateInterface, class_1074 paramclass_1074, String paramString1, String paramString2, float[] paramArrayOfFloat, int paramInt, class_48 paramclass_481, class_48 paramclass_482, String paramString3)
  {
    super(paramStateInterface, paramclass_1074, paramString1, paramString2, paramArrayOfFloat, paramclass_481, paramclass_482, paramString3);
    this.jdField_field_227_of_type_Int = paramInt;
  }
  
  public final void a(int paramInt, boolean paramBoolean)
  {
    class_1070.a1(this.jdField_field_1379_of_type_OrgSchemaSchineNetworkStateInterface, this.jdField_field_1379_of_type_JavaLangString);
    String str2 = this.field_1380;
    int i2 = this.jdField_field_227_of_type_Class_48.field_477;
    int i1 = this.jdField_field_227_of_type_Class_48.field_476;
    int n = this.jdField_field_227_of_type_Class_48.field_475;
    int m = this.jdField_field_1379_of_type_Class_48.field_477;
    int k = this.jdField_field_1379_of_type_Class_48.field_476;
    int j = this.jdField_field_1379_of_type_Class_48.field_475;
    float[] arrayOfFloat = this.jdField_field_1379_of_type_ArrayOfFloat;
    String str1 = this.jdField_field_227_of_type_JavaLangString;
    int i = paramInt;
    paramBoolean = this.jdField_field_1379_of_type_JavaLangString;
    paramInt = this.jdField_field_1379_of_type_OrgSchemaSchineNetworkStateInterface;
    class_705 localclass_705;
    (localclass_705 = new class_705(paramInt)).setUniqueIdentifier(paramBoolean);
    localclass_705.getMinPos().b1(new class_48(j, k, m));
    localclass_705.getMaxPos().b1(new class_48(n, i1, i2));
    localclass_705.setId(paramInt.getNextFreeObjectId());
    localclass_705.setSectorId(i);
    localclass_705.setRealName(str1);
    localclass_705.initialize();
    localclass_705.getInitialTransform().setFromOpenGLMatrix(arrayOfFloat);
    localclass_705.setSpawner(str2);
    (paramInt = localclass_705).setFactionId(this.jdField_field_227_of_type_Int);
    paramInt.getControlElementMap().set(this.jdField_field_1379_of_type_Class_1074.a1());
    ((class_1041)this.jdField_field_1379_of_type_OrgSchemaSchineNetworkStateInterface).a59().a4().addNewSynchronizedObjectQueued(paramInt);
  }
  
  public final void a1(class_48 paramclass_48, class_1041 paramclass_1041, int paramInt, ObjectArrayList paramObjectArrayList) {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1009
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */