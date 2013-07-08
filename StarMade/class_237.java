import java.util.Iterator;
import java.util.List;
import javax.vecmath.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.graphicsengine.forms.Mesh;

public final class class_237
  implements class_965, class_1369
{
  public float field_98;
  private int jdField_field_98_of_type_Int;
  private int jdField_field_106_of_type_Int;
  private float jdField_field_106_of_type_Float;
  private float field_108;
  private Mesh jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh;
  
  public final void a() {}
  
  public final void b()
  {
    GlUtil.d1();
    GlUtil.a31(class_971.field_98.a83());
    GlUtil.b5(10.0F, 10.0F, 10.0F);
    GL11.glEnable(2884);
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    class_1376.field_1559.field_1578 = this;
    class_1376.field_1559.b();
    this.jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh.b();
    class_1376.field_1559.d();
    GL11.glDisable(3042);
    GlUtil.c2();
  }
  
  public final void c()
  {
    this.jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh = ((Mesh)class_969.a2().a4("BlackHole").a152().iterator().next());
    this.jdField_field_106_of_type_Float = 5.0E-007F;
    this.field_108 = 0.7F;
    this.jdField_field_98_of_type_Int = class_969.a2().a5("detail").a153().a1().field_1592;
    this.jdField_field_106_of_type_Int = class_969.a2().a5("detail_normal").a153().a1().field_1592;
  }
  
  public final void d()
  {
    GL13.glActiveTexture(33984);
    GL11.glDisable(3553);
    GL11.glBindTexture(3553, 0);
    GL13.glActiveTexture(33985);
    GL11.glDisable(3553);
    GL11.glBindTexture(3553, 0);
    GL13.glActiveTexture(33984);
  }
  
  public final void a13(class_1377 paramclass_1377)
  {
    GlUtil.a33(paramclass_1377, "cSharp", this.jdField_field_106_of_type_Float);
    GlUtil.a33(paramclass_1377, "cCover", this.field_108);
    GlUtil.a33(paramclass_1377, "cMove", this.jdField_field_98_of_type_Float);
    GlUtil.a41(paramclass_1377, "lightPos", class_971.field_98.a83().field_615, class_971.field_98.a83().field_616, class_971.field_98.a83().field_617, 1.0F);
    GL13.glActiveTexture(33984);
    GL11.glEnable(3553);
    GL11.glBindTexture(3553, this.jdField_field_98_of_type_Int);
    GL13.glActiveTexture(33985);
    GL11.glEnable(3553);
    GL11.glBindTexture(3553, this.jdField_field_106_of_type_Int);
    GL13.glActiveTexture(33986);
    GlUtil.a35(paramclass_1377, "tex", 0);
    GlUtil.a35(paramclass_1377, "nmap", 1);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_237
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */