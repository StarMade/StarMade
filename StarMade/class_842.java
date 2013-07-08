import java.util.List;
import javax.vecmath.Vector3f;
import org.lwjgl.opengl.GL11;
import org.schema.common.FastMath;
import org.schema.schine.graphicsengine.camera.Camera;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.graphicsengine.forms.Mesh;

public final class class_842
  implements class_965, class_1369
{
  private class_1377 jdField_field_98_of_type_Class_1377 = class_1376.field_1575;
  private class_1377 jdField_field_106_of_type_Class_1377 = class_1376.field_1576;
  private float jdField_field_98_of_type_Float;
  private float jdField_field_106_of_type_Float;
  private float field_108;
  private float field_109;
  private float field_110;
  private float field_111;
  private float field_112;
  private float[] jdField_field_98_of_type_ArrayOfFloat = new float[3];
  private float[] jdField_field_106_of_type_ArrayOfFloat = new float[3];
  private float field_113;
  private Mesh jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh;
  private boolean jdField_field_98_of_type_Boolean;
  private float field_114 = -56.0F;
  public class_810 field_98;
  
  public final void a() {}
  
  public final void b()
  {
    if (class_949.field_1194.a4().equals("none")) {
      return;
    }
    if (!this.jdField_field_98_of_type_Boolean) {
      c();
    }
    this.jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh.h1();
    boolean bool;
    if ((bool = a5())) {
      this.jdField_field_98_of_type_Class_1377.b();
    } else {
      this.jdField_field_106_of_type_Class_1377.b();
    }
    GL11.glFrontFace(2304);
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    GL11.glPushMatrix();
    GL11.glTranslatef(0.0F, -this.field_111 - this.field_114, 0.0F);
    this.jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh.i();
    GL11.glPopMatrix();
    GL11.glDisable(3042);
    GL11.glFrontFace(2305);
    if (bool) {
      this.jdField_field_98_of_type_Class_1377.d();
    } else {
      this.jdField_field_106_of_type_Class_1377.d();
    }
    this.jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh.m1();
  }
  
  public final boolean a5()
  {
    Vector3f localVector3f;
    (localVector3f = new Vector3f(class_969.a1().a83())).field_616 += this.field_111 + this.field_114;
    return localVector3f.length() < this.field_112;
  }
  
  public final void d() {}
  
  public final void c()
  {
    if (class_949.field_1194.a4().equals("none")) {
      return;
    }
    this.jdField_field_98_of_type_Float = 0.0025F;
    this.jdField_field_106_of_type_Float = (this.jdField_field_98_of_type_Float * 4.0F * 3.141593F);
    this.field_108 = 0.001F;
    this.field_109 = (this.field_108 * 4.0F * 3.141593F);
    this.field_110 = 70.0F;
    this.field_111 = 1024.0F;
    this.field_112 = 1110.0F;
    this.jdField_field_98_of_type_ArrayOfFloat[0] = 0.65F;
    this.jdField_field_98_of_type_ArrayOfFloat[1] = 0.57F;
    this.jdField_field_98_of_type_ArrayOfFloat[2] = 0.475F;
    this.jdField_field_106_of_type_ArrayOfFloat[0] = FastMath.b2(this.jdField_field_98_of_type_ArrayOfFloat[0], 4.0F);
    this.jdField_field_106_of_type_ArrayOfFloat[1] = FastMath.b2(this.jdField_field_98_of_type_ArrayOfFloat[1], 4.0F);
    this.jdField_field_106_of_type_ArrayOfFloat[2] = FastMath.b2(this.jdField_field_98_of_type_ArrayOfFloat[2], 4.0F);
    this.field_113 = 0.25F;
    this.jdField_field_98_of_type_Class_1377.field_1578 = this;
    this.jdField_field_106_of_type_Class_1377.field_1578 = this;
    this.jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh = ((Mesh)class_969.a2().a4("Atmosphere").a152().get(0));
    this.jdField_field_98_of_type_Boolean = true;
  }
  
  public final void a13(class_1377 paramclass_1377)
  {
    GlUtil.a42(paramclass_1377, "tint", this.jdField_field_98_of_type_Class_810.field_1073);
    void tmp26_23 = new Vector3f(class_969.a1().a83());
    Vector3f localVector3f1;
    tmp26_23.set((localVector3f1 = tmp26_23).field_615, localVector3f1.field_616 + this.field_111 + this.field_114, localVector3f1.field_617);
    GlUtil.a40(paramclass_1377, "v3CameraPos", localVector3f1);
    Vector3f localVector3f2;
    (localVector3f2 = new Vector3f(class_971.field_98.a83())).field_616 += this.field_114 + this.field_111;
    localVector3f2.sub(localVector3f1);
    localVector3f2.normalize();
    GlUtil.a40(paramclass_1377, "v3LightPos", localVector3f2);
    GlUtil.a39(paramclass_1377, "v3InvWavelength", 1.0F / this.jdField_field_106_of_type_ArrayOfFloat[0], 1.0F / this.jdField_field_106_of_type_ArrayOfFloat[1], 1.0F / this.jdField_field_106_of_type_ArrayOfFloat[2]);
    GlUtil.a33(paramclass_1377, "fCameraHeight", localVector3f1.length());
    GlUtil.a33(paramclass_1377, "fCameraHeight2", localVector3f1.lengthSquared());
    GlUtil.a33(paramclass_1377, "fInnerRadius", this.field_111);
    GlUtil.a33(paramclass_1377, "fInnerRadius2", this.field_111 * this.field_111);
    GlUtil.a33(paramclass_1377, "fOuterRadius", this.field_112);
    GlUtil.a33(paramclass_1377, "fOuterRadius2", this.field_112 * this.field_112);
    GlUtil.a33(paramclass_1377, "fKrESun", this.jdField_field_98_of_type_Float * this.field_110);
    GlUtil.a33(paramclass_1377, "fKmESun", this.field_108 * this.field_110);
    GlUtil.a33(paramclass_1377, "fKr4PI", this.jdField_field_106_of_type_Float);
    GlUtil.a33(paramclass_1377, "fKm4PI", this.field_109);
    GlUtil.a33(paramclass_1377, "fScale", 1.0F / (this.field_112 - this.field_111));
    GlUtil.a33(paramclass_1377, "fScaleDepth", this.field_113);
    GlUtil.a33(paramclass_1377, "fScaleOverScaleDepth", 1.0F / (this.field_112 - this.field_111) / this.field_113);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_842
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */