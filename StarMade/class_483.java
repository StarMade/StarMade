import javax.vecmath.Vector3f;
import org.lwjgl.input.Keyboard;
import org.schema.schine.graphicsengine.camera.Camera;

public final class class_483
  extends class_16
{
  private Camera field_4;
  
  public class_483(class_371 paramclass_371)
  {
    super(paramclass_371);
    paramclass_371 = this;
    if (this.field_4 == null)
    {
      class_958 localclass_958 = new class_958();
      paramclass_371.field_4 = new Camera(localclass_958);
    }
    if (class_969.a1() == null) {
      class_969.a9(paramclass_371.field_4);
    }
  }
  
  public final void a12(class_939 paramclass_939)
  {
    super.a12(paramclass_939);
  }
  
  public final void b2(boolean paramBoolean)
  {
    class_1046.field_1306 = paramBoolean;
    if (paramBoolean)
    {
      class_958 localclass_958 = new class_958();
      this.field_4 = new Camera(localclass_958);
      if (class_969.a1() != null) {
        this.field_4.a83().set(class_969.a1().a83());
      }
      class_969.a9(this.field_4);
    }
    super.b2(paramBoolean);
  }
  
  public final void a15(class_941 paramclass_941)
  {
    class_1046.field_1306 = true;
    class_962 localclass_962 = class_969.a1().a184();
    Vector3f localVector3f1 = new Vector3f(localclass_962.field_89.c10());
    Vector3f localVector3f2 = new Vector3f(localclass_962.field_89.f5());
    Vector3f localVector3f3 = new Vector3f(localclass_962.field_89.d7());
    float f = Keyboard.isKeyDown(42) ? 50.0F : 5.0F;
    localVector3f1.scale(f * paramclass_941.a());
    localVector3f2.scale(f * paramclass_941.a());
    localVector3f3.scale(f * paramclass_941.a());
    if ((!Keyboard.isKeyDown(17)) || (!Keyboard.isKeyDown(31)))
    {
      if (Keyboard.isKeyDown(17)) {
        localclass_962.a83().add(localVector3f1);
      }
      if (Keyboard.isKeyDown(31))
      {
        localVector3f1.scale(-1.0F);
        localclass_962.a83().add(localVector3f1);
      }
    }
    if ((!Keyboard.isKeyDown(30)) || (!Keyboard.isKeyDown(32)))
    {
      if (Keyboard.isKeyDown(30)) {
        localclass_962.a83().add(localVector3f3);
      }
      if (Keyboard.isKeyDown(32))
      {
        localVector3f3.scale(-1.0F);
        localclass_962.a83().add(localVector3f3);
      }
    }
    if ((!Keyboard.isKeyDown(16)) || (!Keyboard.isKeyDown(18)))
    {
      if (Keyboard.isKeyDown(16)) {
        localclass_962.a83().add(localVector3f2);
      }
      if (Keyboard.isKeyDown(18))
      {
        localVector3f2.scale(-1.0F);
        localclass_962.a83().add(localVector3f2);
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_483
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */