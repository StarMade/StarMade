import com.bulletphysics.linearmath.Transform;
import org.schema.schine.graphicsengine.camera.Camera;

public final class class_241
  extends Camera
{
  private Transform field_89;
  
  public class_241(class_1382 paramclass_1382)
  {
    super(new class_299(paramclass_1382));
    ((class_960)a184()).a143(paramclass_1382);
    this.field_89 = new Transform();
    this.field_89.setIdentity();
    this.field_89 = new class_1006(this);
  }
  
  public final void a12(class_941 paramclass_941)
  {
    ((class_1006)this.field_89).field_123.set(this.field_89);
    super.a12(paramclass_941);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_241
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */