import javax.vecmath.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.schema.schine.graphicsengine.core.GlUtil;

public class class_1351
  implements class_1369
{
  private static int jdField_field_107_of_type_Int = 33992;
  private static int jdField_field_201_of_type_Int = 8;
  private static int field_202 = 33991;
  private static int field_203 = 7;
  private static int field_266 = 33990;
  private static int field_267 = 6;
  private int field_268 = -1;
  public Vector3f field_107;
  private class_1395 jdField_field_107_of_type_Class_1395;
  private float jdField_field_107_of_type_Float;
  private float jdField_field_201_of_type_Float;
  private class_925 jdField_field_107_of_type_Class_925;
  
  public class_1351()
  {
    this.jdField_field_107_of_type_JavaxVecmathVector3f = new Vector3f();
  }
  
  public final void d()
  {
    GL13.glActiveTexture(field_202);
    GL11.glBindTexture(3553, 0);
    GL13.glActiveTexture(jdField_field_107_of_type_Int);
    GL11.glBindTexture(3553, 0);
    GL13.glActiveTexture(field_266);
    GL11.glBindTexture(3553, 0);
    GL13.glActiveTexture(33984);
  }
  
  public final void a6(int paramInt, class_925 paramclass_925)
  {
    this.field_268 = paramInt;
    this.jdField_field_107_of_type_Class_925 = paramclass_925;
  }
  
  public final boolean a7()
  {
    this.jdField_field_107_of_type_Float = (this.jdField_field_107_of_type_JavaxVecmathVector3f.field_615 / class_933.b());
    this.jdField_field_201_of_type_Float = (1.0F - this.jdField_field_107_of_type_JavaxVecmathVector3f.field_616 / class_933.a());
    return true;
  }
  
  public final void a(class_1377 paramclass_1377)
  {
    if (this.jdField_field_107_of_type_Class_1395 == null) {
      this.jdField_field_107_of_type_Class_1395 = class_969.a2().a5("lens_flare").a153().a1();
    }
    if ((!jdField_field_107_of_type_Boolean) && (this.field_268 < 0)) {
      throw new AssertionError();
    }
    GlUtil.a33(paramclass_1377, "screenRatio", class_933.a() / class_933.b());
    GlUtil.a37(paramclass_1377, "lightPositionOnScreen", this.jdField_field_107_of_type_Float, this.jdField_field_201_of_type_Float);
    GlUtil.a41(paramclass_1377, "Param1", this.jdField_field_107_of_type_Float, this.jdField_field_201_of_type_Float, 1.0F / this.jdField_field_107_of_type_Class_925.field_1150, 0.5F / this.jdField_field_107_of_type_Class_1395.field_1591);
    GlUtil.a41(paramclass_1377, "Param2", 1.0F, 0.2F, 1.0F, 1.0F);
    GL13.glActiveTexture(field_202);
    GL11.glBindTexture(3553, this.field_268);
    GlUtil.a35(paramclass_1377, "firstPass", field_203);
    GL13.glActiveTexture(jdField_field_107_of_type_Int);
    GL11.glBindTexture(3553, this.jdField_field_107_of_type_Class_1395.field_1592);
    GlUtil.a35(paramclass_1377, "Texture", jdField_field_201_of_type_Int);
    GL13.glActiveTexture(field_266);
    GL11.glBindTexture(3553, this.jdField_field_107_of_type_Class_925.field_1148);
    GlUtil.a35(paramclass_1377, "Scene", field_267);
    GL13.glActiveTexture(33984);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1351
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */