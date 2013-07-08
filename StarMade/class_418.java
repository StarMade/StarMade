import com.bulletphysics.linearmath.Transform;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Locale;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;
import org.schema.schine.graphicsengine.camera.Camera;
import org.schema.schine.graphicsengine.shader.ErrorDialogException;
import org.schema.schine.network.objects.container.PhysicsDataContainer;

final class class_418
  extends class_15
{
  class_418(class_431 paramclass_431, class_371 paramclass_371, Object paramObject1, Object paramObject2, String paramString)
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
    a9("SHIPNAME INVALID: " + paramString);
  }
  
  public final boolean a7(String paramString)
  {
    if ((this.field_4.a3() == null) || (this.field_4.a3().getPhysicsDataContainer() == null) || (!this.field_4.a3().getPhysicsDataContainer().isInitialized()))
    {
      System.err.println("[ERROR] Character might not have been initialized");
      return false;
    }
    Transform localTransform = new Transform();
    Vector3f localVector3f = new Vector3f(class_969.a1().c10());
    localTransform.set(this.field_4.a3().getPhysicsDataContainer().getCurrentPhysicsTransform());
    localVector3f.scale(2.0F);
    localTransform.origin.add(localVector3f);
    localTransform.basis.rotY(-1.570796F);
    try
    {
      if (this.field_4.a20().getInventory(null).b2())
      {
        if (!paramString.toLowerCase(Locale.ENGLISH).contains("vehicle"))
        {
          this.field_4.field_4 = class_1070.a(paramString.trim());
          this.field_4.a4().a19(localTransform, new class_48(-2, -2, -2), new class_48(2, 2, 2), this.field_4.a20(), this.field_4.field_4, paramString.trim());
          System.err.println("SENDING LAST NAME: " + this.field_4.field_4 + "; obs: " + countObservers());
          this.field_4.a9(this.field_4.field_4);
          break label298;
        }
      }
      else {
        this.field_4.a4().b1("ERROR\nYou need a Core Element\nto create a ship");
      }
    }
    catch (Exception localException)
    {
      (paramString = localException).printStackTrace();
      throw new ErrorDialogException(paramString.getMessage());
    }
    label298:
    return true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_418
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */