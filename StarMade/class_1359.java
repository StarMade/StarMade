import java.io.IOException;
import org.lwjgl.opengl.GL11;
import org.schema.schine.graphicsengine.core.GlUtil;

public class class_1359
  extends class_1352
  implements class_1369
{
  private class_1377 jdField_field_98_of_type_Class_1377 = class_1376.field_1573;
  private boolean jdField_field_106_of_type_Boolean;
  private static class_1395 jdField_field_98_of_type_Class_1395;
  private float jdField_field_106_of_type_Float = 0.0F;
  
  public class_1359()
  {
    super((byte)0);
    if ((!field_108) && (class_1376.field_1573 == null)) {
      throw new AssertionError();
    }
  }
  
  public final void b()
  {
    if (!this.jdField_field_106_of_type_Boolean) {
      c();
    }
    if (this.jdField_field_98_of_type_Class_944.b1() > 1)
    {
      this.jdField_field_98_of_type_Class_1377.field_1578 = this;
      this.jdField_field_98_of_type_Class_1377.b();
      super.b();
      this.jdField_field_98_of_type_Class_1377.d();
    }
  }
  
  public final void d()
  {
    GL11.glBindTexture(32879, 0);
  }
  
  public final void c()
  {
    this.jdField_field_98_of_type_Boolean = false;
    if (jdField_field_98_of_type_Class_1395 == null) {
      try
      {
        jdField_field_98_of_type_Class_1395 = class_969.a3().a6("data/./effects/noise.jpg", true);
      }
      catch (IOException localIOException)
      {
        localIOException;
      }
    }
    super.c();
    this.jdField_field_106_of_type_Boolean = true;
  }
  
  public final void a1(class_941 paramclass_941)
  {
    for (this.jdField_field_106_of_type_Float += paramclass_941.a(); this.jdField_field_106_of_type_Float > 1.0F; this.jdField_field_106_of_type_Float -= 1.0F) {}
  }
  
  public final void a13(class_1377 paramclass_1377)
  {
    GlUtil.a33(paramclass_1377, "time", this.jdField_field_106_of_type_Float);
    GL11.glBindTexture(3553, jdField_field_98_of_type_Class_1395.field_1592);
    GlUtil.a35(paramclass_1377, "tex", 0);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1359
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */