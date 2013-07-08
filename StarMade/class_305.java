import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.schema.schine.graphicsengine.core.GlUtil;

public final class class_305
  extends class_948
  implements class_1369
{
  private class_371 jdField_field_98_of_type_Class_371;
  private class_1380 jdField_field_98_of_type_Class_1380;
  private class_1377 jdField_field_98_of_type_Class_1377;
  private boolean field_108;
  
  public class_305(class_8 paramclass_8)
  {
    super(paramclass_8, (byte)0);
    this.jdField_field_98_of_type_Class_371 = ((class_371)paramclass_8.a3());
    this.field_106 = false;
  }
  
  public final void b()
  {
    if (!this.field_108) {
      c();
    }
    jdField_field_98_of_type_Boolean = Keyboard.isKeyDown(79);
    if (this.field_98.b1() <= 0) {
      return;
    }
    if (!jdField_field_98_of_type_Boolean)
    {
      this.jdField_field_98_of_type_Class_1377.field_1578 = this;
      this.jdField_field_98_of_type_Class_1377.b();
    }
    super.b();
    if (!jdField_field_98_of_type_Boolean) {
      this.jdField_field_98_of_type_Class_1377.d();
    }
  }
  
  public final void d()
  {
    GL11.glBindTexture(3553, 0);
  }
  
  public final void c()
  {
    this.jdField_field_98_of_type_Class_1380 = class_969.a2().a5("starSprite");
    this.jdField_field_98_of_type_Class_1377 = class_1376.field_1546;
    super.c();
    this.field_108 = true;
  }
  
  public final String toString()
  {
    return "ProjectileDrawerVBO [state=" + this.jdField_field_98_of_type_Class_371 + ", count=" + this.field_98.b1() + "]";
  }
  
  public final void a13(class_1377 paramclass_1377)
  {
    GL13.glActiveTexture(33984);
    GL11.glBindTexture(3553, this.jdField_field_98_of_type_Class_1380.a153().a1().field_1592);
    GlUtil.a35(paramclass_1377, "noiseVolume", 0);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_305
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */