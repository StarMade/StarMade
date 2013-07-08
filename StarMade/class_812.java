import javax.vecmath.Vector4f;
import org.lwjgl.opengl.GL11;
import org.schema.schine.graphicsengine.core.GlUtil;

public final class class_812
  implements class_1369
{
  public class_1433 field_107;
  private int field_107;
  
  public class_812()
  {
    this.jdField_field_107_of_type_Class_1433 = new class_1433(20.0F);
    this.jdField_field_107_of_type_Int = -1;
  }
  
  public final void d()
  {
    GL11.glBindTexture(3553, 0);
  }
  
  public final void a(class_1377 paramclass_1377)
  {
    if (this.jdField_field_107_of_type_Int < 0) {
      this.jdField_field_107_of_type_Int = class_969.a2().a5("star").a153().a1().field_1592;
    }
    GlUtil.a42(paramclass_1377, "Param", new Vector4f(1.0F, 0.1F, 0.001F, 0.5F));
    GL11.glBindTexture(3553, this.jdField_field_107_of_type_Int);
    GlUtil.a35(paramclass_1377, "Texture", 0);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_812
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */