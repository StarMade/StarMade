import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.Random;
import org.schema.game.common.controller.database.DatabaseIndex;
import org.schema.game.common.data.element.ControlElementMap;
import org.schema.game.common.data.element.ControlElementMapper;
import org.schema.game.common.data.world.Universe;
import org.schema.game.server.controller.GameServerController;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.SynchronizationContainerController;

public final class class_1092
  extends class_1133
{
  private int field_227;
  
  public class_1092(StateInterface paramStateInterface, class_1074 paramclass_1074, String paramString1, String paramString2, float[] paramArrayOfFloat, int paramInt, class_48 paramclass_481, class_48 paramclass_482, String paramString3)
  {
    super(paramStateInterface, paramclass_1074, paramString1, paramString2, paramArrayOfFloat, paramclass_481, paramclass_482, paramString3);
    this.jdField_field_227_of_type_Int = paramInt;
  }
  
  public final void a(int paramInt, boolean paramBoolean)
  {
    class_1070.a1(this.jdField_field_1379_of_type_OrgSchemaSchineNetworkStateInterface, this.jdField_field_1379_of_type_JavaLangString);
    (paramInt = class_1070.a2(this.jdField_field_1379_of_type_OrgSchemaSchineNetworkStateInterface, this.jdField_field_1379_of_type_JavaLangString, paramInt, this.jdField_field_227_of_type_JavaLangString, this.jdField_field_1379_of_type_ArrayOfFloat, this.jdField_field_1379_of_type_Class_48.field_475, this.jdField_field_1379_of_type_Class_48.field_476, this.jdField_field_1379_of_type_Class_48.field_477, this.jdField_field_227_of_type_Class_48.field_475, this.jdField_field_227_of_type_Class_48.field_476, this.jdField_field_227_of_type_Class_48.field_477)).getControlElementMap().getControllingMap().set(this.jdField_field_1379_of_type_Class_1074.a1());
    if (this.jdField_field_227_of_type_Int != 0)
    {
      paramInt.a87().a183(class_776.field_1032).a174("Any", true);
      paramInt.a87().a183(class_776.field_1033).a174("Ship", true);
      paramInt.a87().a183(class_776.field_1034).a174("true", true);
      paramInt.a87().a13();
    }
    if (paramBoolean)
    {
      this.jdField_field_1379_of_type_Class_1074.a3();
      class_737.a13();
    }
    ((class_1041)this.jdField_field_1379_of_type_OrgSchemaSchineNetworkStateInterface).a59().a4().addNewSynchronizedObjectQueued(paramInt);
  }
  
  public final void a1(class_48 paramclass_48, class_1041 paramclass_1041, int paramInt, ObjectArrayList paramObjectArrayList)
  {
    (paramObjectArrayList = new Transform()).setFromOpenGLMatrix(this.jdField_field_1379_of_type_ArrayOfFloat);
    paramclass_1041.a66().a18(this.jdField_field_1379_of_type_JavaLangString, paramclass_48, 5, Universe.getRandom().nextLong(), "<sim>", "<sim>", this.jdField_field_227_of_type_JavaLangString, this.jdField_field_227_of_type_Int, paramObjectArrayList.origin, this.jdField_field_1379_of_type_Class_48, this.jdField_field_227_of_type_Class_48, paramInt);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1092
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */