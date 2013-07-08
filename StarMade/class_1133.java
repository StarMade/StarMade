import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.IOException;
import java.util.ArrayList;
import javax.vecmath.Vector3f;
import org.schema.game.server.controller.EntityAlreadyExistsException;
import org.schema.game.server.controller.EntityNotFountException;
import org.schema.game.server.data.blueprintnw.BlueprintEntry;
import org.schema.schine.graphicsengine.shader.ErrorDialogException;
import org.schema.schine.network.StateInterface;

public abstract class class_1133
{
  protected StateInterface field_1379;
  protected String field_1379;
  protected String field_227;
  protected float[] field_1379;
  public class_1074 field_1379;
  public class_48 field_1379;
  public class_48 field_227;
  public int field_1379;
  protected String field_1380;
  protected ArrayList field_1379;
  public String field_1381;
  
  public class_1133(StateInterface paramStateInterface, class_1074 paramclass_1074, String paramString1, String paramString2, float[] paramArrayOfFloat, class_48 paramclass_481, class_48 paramclass_482, String paramString3)
  {
    this.jdField_field_1379_of_type_Int = -1;
    this.jdField_field_1379_of_type_Class_1074 = paramclass_1074;
    this.jdField_field_1379_of_type_OrgSchemaSchineNetworkStateInterface = paramStateInterface;
    this.jdField_field_1379_of_type_JavaLangString = paramString1;
    this.jdField_field_227_of_type_JavaLangString = paramString2;
    this.jdField_field_1379_of_type_ArrayOfFloat = paramArrayOfFloat;
    this.jdField_field_1379_of_type_Class_48 = paramclass_481;
    this.jdField_field_227_of_type_Class_48 = paramclass_482;
    this.field_1380 = paramString3;
  }
  
  public final void a2(int paramInt)
  {
    if ((this.jdField_field_1379_of_type_Class_1074 instanceof BlueprintEntry))
    {
      BlueprintEntry localBlueprintEntry = (BlueprintEntry)this.jdField_field_1379_of_type_Class_1074;
      int i = 0;
      int j = 0;
      if (localBlueprintEntry.a14() != null)
      {
        this.jdField_field_1379_of_type_JavaUtilArrayList = new ArrayList();
        for (Object localObject : localBlueprintEntry.a14())
        {
          Transform localTransform;
          (localTransform = new Transform()).setFromOpenGLMatrix(this.jdField_field_1379_of_type_ArrayOfFloat);
          localTransform.origin.field_615 += ((BlueprintEntry)localObject).a15().field_475 - 8;
          localTransform.origin.field_616 += ((BlueprintEntry)localObject).a15().field_476 - 8;
          localTransform.origin.field_617 += ((BlueprintEntry)localObject).a15().field_477 - 8;
          String str;
          if (((BlueprintEntry)localObject).a16() == 7)
          {
            str = this.jdField_field_227_of_type_JavaLangString + "_turret_" + i;
            i++;
          }
          else
          {
            str = this.jdField_field_227_of_type_JavaLangString + "_dock_" + j;
            j++;
          }
          try
          {
            localBlueprintEntry.a19();
            localObject = class_1216.a5(this.jdField_field_1379_of_type_OrgSchemaSchineNetworkStateInterface, str, localTransform, -1, paramInt, (BlueprintEntry)localObject, this.jdField_field_1379_of_type_Int, null, this.field_1380, class_748.field_136);
            this.jdField_field_1379_of_type_JavaUtilArrayList.add(localObject);
          }
          catch (EntityNotFountException localEntityNotFountException)
          {
            localEntityNotFountException;
          }
          catch (IOException localIOException)
          {
            localIOException;
          }
          catch (ErrorDialogException localErrorDialogException)
          {
            localErrorDialogException;
          }
          catch (EntityAlreadyExistsException localEntityAlreadyExistsException)
          {
            localEntityAlreadyExistsException;
          }
        }
      }
    }
  }
  
  public abstract void a(int paramInt, boolean paramBoolean);
  
  public abstract void a1(class_48 paramclass_48, class_1041 paramclass_1041, int paramInt, ObjectArrayList paramObjectArrayList);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1133
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */