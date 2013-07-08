import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.lwjgl.opengl.GL11;

public final class class_1394
  extends class_1392
{
  private Vector4f jdField_field_89_of_type_JavaxVecmathVector4f = new Vector4f(0.5F, 0.5F, 0.5F, 0.7F);
  private class_980 jdField_field_89_of_type_Class_980;
  private float jdField_field_89_of_type_Float = 1.4F;
  private float field_90 = 0.2F;
  private float field_92 = 300.0F;
  
  public class_1394(class_980 paramclass_980)
  {
    this.jdField_field_89_of_type_Class_980 = paramclass_980;
  }
  
  public final void b()
  {
    GL11.glDisable(2929);
    GL11.glDisable(2896);
    GL11.glEnable(3042);
    GL11.glEnable(2903);
    class_1380 localclass_1380;
    (localclass_1380 = class_969.a2().a5("box")).a161(a83().field_615, a83().field_616, a83().field_617);
    localclass_1380.b21(false);
    localclass_1380.a29(true);
    localclass_1380.field_89.set(this.jdField_field_89_of_type_Float, this.field_90, 1.0F);
    localclass_1380.c6(this.jdField_field_89_of_type_JavaxVecmathVector4f);
    localclass_1380.b();
    this.jdField_field_89_of_type_Class_980.field_89.set(1.0F, 1.0F, 1.0F);
    this.jdField_field_89_of_type_Class_980.a161(a83().field_615, a83().field_616, a83().field_617);
    this.jdField_field_89_of_type_Class_980.field_89.field_450 = (-(this.field_92 / 2.0F) + 9.0F);
    this.jdField_field_89_of_type_Class_980.field_89.field_451 = (0.0F - this.jdField_field_89_of_type_Class_980.a3() - 3.0F);
    this.jdField_field_89_of_type_Class_980.d();
    this.jdField_field_89_of_type_Class_980.b();
    GL11.glDisable(3042);
    GL11.glDisable(2903);
    GL11.glEnable(2929);
    GL11.glEnable(2896);
  }
  
  public final void c() {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1394
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */