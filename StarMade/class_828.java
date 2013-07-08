import javax.vecmath.Color4f;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.schema.schine.graphicsengine.camera.Camera;
import org.schema.schine.graphicsengine.core.GlUtil;

final class class_828
  implements class_1369
{
  private Vector4f jdField_field_107_of_type_JavaxVecmathVector4f = new Vector4f(-1.0F, 0.0F, 0.0F, 0.0F);
  
  private class_828(class_834 paramclass_834) {}
  
  public final void d() {}
  
  public final void a(class_1377 paramclass_1377)
  {
    if (this.jdField_field_107_of_type_JavaxVecmathVector4f.field_596 < 0.0F) {
      GlUtil.a42(paramclass_1377, "fvDiffuse", class_834.a47());
    }
    if (!class_834.a48(this.jdField_field_107_of_type_Class_834).jdField_field_1087_of_type_JavaxVecmathColor4f.equals(this.jdField_field_107_of_type_JavaxVecmathVector4f)) {
      GlUtil.a32(paramclass_1377, "fvAtmoColor", class_834.a48(this.jdField_field_107_of_type_Class_834).jdField_field_1087_of_type_JavaxVecmathColor4f);
    } else {
      this.jdField_field_107_of_type_JavaxVecmathVector4f.set(class_834.a48(this.jdField_field_107_of_type_Class_834).jdField_field_1087_of_type_JavaxVecmathColor4f);
    }
    GlUtil.a33(paramclass_1377, "fCloudHeight", class_834.a48(this.jdField_field_107_of_type_Class_834).field_1090);
    GlUtil.a33(paramclass_1377, "fAbsPower", class_834.a48(this.jdField_field_107_of_type_Class_834).field_1088);
    GlUtil.a33(paramclass_1377, "fAtmoDensity", class_834.a48(this.jdField_field_107_of_type_Class_834).jdField_field_1087_of_type_Float);
    GlUtil.a33(paramclass_1377, "fGlowPower", class_834.a48(this.jdField_field_107_of_type_Class_834).field_1089);
    GlUtil.a33(paramclass_1377, "density", 1.5F / class_969.field_1259.a());
    if (class_834.a49(this.jdField_field_107_of_type_Class_834).a3(0, 0, 0))
    {
      GlUtil.a33(paramclass_1377, "dist", class_969.a1().a83().length());
      return;
    }
    GlUtil.a33(paramclass_1377, "dist", -1.0F);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_828
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */