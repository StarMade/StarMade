import com.bulletphysics.linearmath.Transform;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.vecmath.Matrix3f;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3f;
import org.schema.schine.graphicsengine.camera.Camera;
import org.schema.schine.graphicsengine.shader.ErrorDialogException;
import org.schema.schine.network.objects.container.PhysicsDataContainer;

final class class_420
  extends class_15
{
  class_420(class_431 paramclass_431, class_371 paramclass_371, Object paramObject1, Object paramObject2, String paramString)
  {
    super(paramclass_371, 50, paramObject1, paramObject2, paramString);
  }
  
  public final String[] getCommandPrefixes()
  {
    return null;
  }
  
  public final String handleAutoComplete(String paramString1, class_1079 paramclass_1079, String paramString2)
  {
    return paramString1;
  }
  
  public final boolean a1()
  {
    return this.field_4.b().indexOf(this) != this.field_4.b().size() - 1;
  }
  
  public final void a2()
  {
    System.err.println("deactivate");
    this.field_4.e2(false);
  }
  
  public final void onFailedTextCheck(String paramString)
  {
    a9("NAME INVALID: " + paramString);
  }
  
  public final boolean a7(String paramString)
  {
    if ((this.field_4.a3() == null) || (this.field_4.a3().getPhysicsDataContainer() == null) || (!this.field_4.a3().getPhysicsDataContainer().isInitialized())) {
      return false;
    }
    Transform localTransform = new Transform();
    Object localObject = new Vector3f(class_969.a1().c10());
    localTransform.set(this.field_4.a3().getPhysicsDataContainer().getCurrentPhysicsTransform());
    ((Vector3f)localObject).scale(2.0F);
    localTransform.origin.add((Tuple3f)localObject);
    localTransform.basis.rotY(-1.570796F);
    try
    {
      localObject = class_1070.b(paramString.trim());
      this.field_4.a4().a20(localTransform, this.field_4.a20(), (String)localObject, paramString.trim());
    }
    catch (Exception localException)
    {
      (localObject = localException).printStackTrace();
      throw new ErrorDialogException(((Exception)localObject).getMessage());
    }
    return true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_420
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */