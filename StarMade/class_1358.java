import org.lwjgl.opengl.GL11;
import org.schema.schine.graphicsengine.core.GlUtil;

public class class_1358
  extends class_946
  implements class_1369
{
  private class_1377 jdField_field_98_of_type_Class_1377 = class_1376.field_1542;
  private boolean field_106;
  private static class_1395 jdField_field_98_of_type_Class_1395;
  
  public class_1358(class_944 paramclass_944, float paramFloat)
  {
    super(paramclass_944, paramFloat);
    if ((!field_108) && (class_1376.field_1542 == null)) {
      throw new AssertionError();
    }
    this.jdField_field_98_of_type_Boolean = true;
  }
  
  public final void b()
  {
    if (!this.field_106) {
      c();
    }
    if (this.jdField_field_98_of_type_Class_944.b1() > 0)
    {
      this.jdField_field_98_of_type_Class_1377.field_1578 = this;
      this.jdField_field_98_of_type_Class_1377.b();
      super.b();
      this.jdField_field_98_of_type_Class_1377.d();
    }
  }
  
  public final void d()
  {
    GL11.glBindTexture(3553, 0);
  }
  
  public final void c()
  {
    if (jdField_field_98_of_type_Class_1395 == null) {
      jdField_field_98_of_type_Class_1395 = class_969.a2().a5("starSprite").a153().a1();
    }
    super.c();
    this.field_106 = true;
  }
  
  public final void a13(class_1377 paramclass_1377)
  {
    GlUtil.a33(paramclass_1377, "time", 0.0F);
    GL11.glBindTexture(3553, jdField_field_98_of_type_Class_1395.field_1592);
    GlUtil.a35(paramclass_1377, "tex", 0);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1358
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */