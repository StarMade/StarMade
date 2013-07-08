import javax.vecmath.Vector3f;
import org.lwjgl.opengl.GL11;
import org.schema.schine.graphicsengine.core.GlUtil;

public class class_1401
  extends class_946
  implements class_1369
{
  private class_1377 jdField_field_98_of_type_Class_1377 = class_1376.field_1551;
  private static class_1391 jdField_field_98_of_type_Class_1391;
  private Vector3f field_108 = new Vector3f();
  private float jdField_field_98_of_type_Float = 1.0F;
  
  public class_1401(class_944 paramclass_944)
  {
    super(paramclass_944, 0.6F);
    if ((!field_106) && (class_1376.field_1551 == null)) {
      throw new AssertionError();
    }
  }
  
  public final void b()
  {
    if (this.jdField_field_98_of_type_Class_944.b1() > 0)
    {
      this.jdField_field_98_of_type_Class_1377.field_1578 = this;
      this.jdField_field_98_of_type_Class_1377.b();
      super.b();
      this.jdField_field_98_of_type_Class_1377.d();
    }
  }
  
  public final float a51(int paramInt, class_950 paramclass_950)
  {
    return paramclass_950.c(paramInt, this.field_108).field_615 * 0.6F;
  }
  
  public final void d()
  {
    GL11.glBindTexture(32879, 0);
  }
  
  public final void c()
  {
    this.jdField_field_98_of_type_Boolean = true;
    if (jdField_field_98_of_type_Class_1391 == null) {
      jdField_field_98_of_type_Class_1391 = class_73.field_135;
    }
    super.c();
  }
  
  public final void a13(class_1377 paramclass_1377)
  {
    GlUtil.a33(paramclass_1377, "time", this.jdField_field_98_of_type_Float);
    GL11.glBindTexture(32879, jdField_field_98_of_type_Class_1391.a1());
    GlUtil.a35(paramclass_1377, "tex", 0);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1401
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */