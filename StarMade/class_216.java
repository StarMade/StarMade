import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.schema.schine.graphicsengine.camera.Camera;

public final class class_216
  extends class_251
{
  private Transform field_103 = new Transform();
  private static Vector3f field_104 = new Vector3f();
  
  private class_216(Transform paramTransform, String paramString)
  {
    super(paramTransform, paramString);
    this.field_103.set(paramTransform);
  }
  
  public class_216(Transform paramTransform, String paramString, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    this(paramTransform, paramString);
    this.field_104 = new Vector4f(paramFloat1, paramFloat2, paramFloat3, 1.0F);
  }
  
  public final Transform a()
  {
    return this.field_103;
  }
  
  public final void a1(class_941 paramclass_941)
  {
    super.a1(paramclass_941);
    field_104.set(class_969.a1().f5());
    field_104.scale(paramclass_941.a());
    this.field_103.origin.add(field_104);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_216
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */