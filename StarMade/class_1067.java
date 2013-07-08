import com.bulletphysics.linearmath.Transform;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import javax.vecmath.Vector3f;
import org.schema.game.common.data.world.Universe;
import org.schema.game.server.controller.EntityAlreadyExistsException;
import org.schema.game.server.controller.EntityNotFountException;
import org.schema.schine.graphicsengine.core.settings.StateParameterNotFoundException;
import org.schema.schine.graphicsengine.shader.ErrorDialogException;

public abstract class class_1067
  extends class_1063
{
  public class_1067(class_1041 paramclass_1041)
  {
    super(paramclass_1041);
  }
  
  public final void a23(class_48 paramclass_48, int paramInt, class_781... paramVarArgs)
  {
    this.jdField_field_223_of_type_Class_48 = paramclass_48;
    int i = 0;
    int j = 0;
    for (class_781 localclass_781 : paramVarArgs)
    {
      try
      {
        (localObject = new Transform()).setIdentity();
        i += 160;
        int n = 160 * (i / 1600);
        int i1 = i % 1600;
        ((Transform)localObject).origin.set(0.0F, n - Universe.getSectorSizeWithMargin() / 2, i1 - Universe.getSectorSizeWithMargin() / 2);
        String str = "MOB_SIM_" + localclass_781.field_136 + System.currentTimeMillis() + "_" + j;
        localObject = class_1216.field_1429.a4(this.jdField_field_223_of_type_Class_1041, localclass_781.field_136, str, (Transform)localObject, -1, paramInt, -1234, "<simulation>", class_748.field_136);
        if (!this.jdField_field_223_of_type_Class_1041.a62().isSectorLoaded(paramclass_48))
        {
          System.err.println("Spawning in database: " + str + " of blueprint: " + localclass_781.field_136);
          ((class_1133)localObject).a1(paramclass_48, this.jdField_field_223_of_type_Class_1041, -1, this.jdField_field_223_of_type_ItUnimiDsiFastutilObjectsObjectArrayList);
        }
      }
      catch (EntityNotFountException localEntityNotFountException)
      {
        localObject = null;
        localEntityNotFountException.printStackTrace();
      }
      catch (IOException localIOException)
      {
        localObject = null;
        localIOException.printStackTrace();
      }
      catch (ErrorDialogException localErrorDialogException)
      {
        localObject = null;
        localErrorDialogException.printStackTrace();
      }
      catch (EntityAlreadyExistsException localEntityAlreadyExistsException)
      {
        localObject = null;
        localEntityAlreadyExistsException.printStackTrace();
      }
      catch (SQLException localSQLException)
      {
        localObject = null;
        localSQLException.printStackTrace();
      }
      catch (StateParameterNotFoundException localStateParameterNotFoundException)
      {
        Object localObject = null;
        localStateParameterNotFoundException.printStackTrace();
      }
      j++;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1067
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */