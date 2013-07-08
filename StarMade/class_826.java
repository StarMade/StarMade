import java.util.List;
import javax.vecmath.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.schema.game.common.data.world.Universe;
import org.schema.schine.graphicsengine.camera.Camera;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.graphicsengine.forms.Mesh;

public final class class_826
  implements class_965, class_1369
{
  public float field_98;
  private int field_109;
  private int field_110;
  private float field_106;
  private float field_108;
  public Mesh field_98;
  public class_48 field_98;
  private class_1380 jdField_field_98_of_type_Class_1380;
  public int field_98;
  public int field_106;
  public int field_108;
  private class_354 jdField_field_98_of_type_Class_354 = new class_354();
  private int field_111;
  
  public class_826()
  {
    this.jdField_field_98_of_type_Class_48 = new class_48();
  }
  
  public final void a() {}
  
  public final void b()
  {
    if (!class_969.a1().a187(class_971.field_98.a83())) {
      return;
    }
    GlUtil.d1();
    if ((this.jdField_field_98_of_type_Class_48.field_475 == 0) && (this.jdField_field_98_of_type_Class_48.field_476 == 0) && (this.jdField_field_98_of_type_Class_48.field_477 == 0))
    {
      GlUtil.d1();
      GlUtil.a31(class_969.a1().a83());
      GlUtil.b5(3.0F, 3.0F, 3.0F);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      class_1376.field_1549.c();
      GlUtil.a33(class_1376.field_1549, "time", this.jdField_field_98_of_type_Float * 10.0F);
      this.jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh.b();
      class_1377.e();
      GL11.glDisable(3042);
      GlUtil.c2();
    }
    this.jdField_field_98_of_type_Class_1380.c12(class_971.field_98.a83());
    float f = Math.min(1.0F, Math.max(0.0F, class_971.field_98.a83().length() / (Universe.getSectorSizeWithMargin() << 3))) * 2.0F;
    this.jdField_field_98_of_type_Class_1380.a29(true);
    this.jdField_field_98_of_type_Class_1380.b28(f + 0.5F, f + 0.5F, f + 0.5F);
    this.jdField_field_98_of_type_Class_1380.b21(true);
    if (this.jdField_field_108_of_type_Int == 0)
    {
      GL15.glBeginQuery(35092, this.jdField_field_98_of_type_Int);
      class_1380.d9(this.jdField_field_98_of_type_Class_1380);
      GL15.glEndQuery(35092);
    }
    this.jdField_field_108_of_type_Int += 1;
    this.jdField_field_98_of_type_Class_1380.b21(false);
    if (this.jdField_field_106_of_type_Int > 0)
    {
      this.field_111 = Math.max(1, Math.max(this.jdField_field_106_of_type_Int, this.field_111 - 1));
      class_354.d();
      this.jdField_field_98_of_type_Class_354.b();
    }
    this.jdField_field_98_of_type_Class_1380.b28(1.0F, 1.0F, 1.0F);
    GlUtil.c2();
    GL11.glEnable(2884);
  }
  
  public final void c()
  {
    this.jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh = ((Mesh)class_969.a2().a4("Sphere").a152().get(0));
    this.jdField_field_98_of_type_Class_1380 = class_969.a2().a5("sunSprite-c-");
    this.jdField_field_98_of_type_Int = GL15.glGenQueries();
    this.jdField_field_106_of_type_Float = 5.0E-007F;
    this.jdField_field_108_of_type_Float = 0.7F;
    this.field_109 = class_969.a2().a5("detail").a153().a1().field_1592;
    this.field_110 = class_969.a2().a5("detail_normal").a153().a1().field_1592;
    this.jdField_field_98_of_type_Class_354.c();
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
    GlUtil.a33(paramclass_1377, "cCover", this.jdField_field_108_of_type_Float);
    GlUtil.a33(paramclass_1377, "cMove", this.jdField_field_98_of_type_Float);
    GlUtil.a41(paramclass_1377, "lightPos", class_971.field_98.a83().field_615, class_971.field_98.a83().field_616, class_971.field_98.a83().field_617, 1.0F);
    GL13.glActiveTexture(33984);
    GL11.glEnable(3553);
    GL11.glBindTexture(3553, this.field_109);
    GL13.glActiveTexture(33985);
    GL11.glEnable(3553);
    GL11.glBindTexture(3553, this.field_110);
    GL13.glActiveTexture(33986);
    GlUtil.a35(paramclass_1377, "tex", 0);
    GlUtil.a35(paramclass_1377, "nmap", 1);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_826
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */