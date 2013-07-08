import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.lwjgl.opengl.GL11;
import org.schema.schine.graphicsengine.core.GlUtil;

public final class class_992
  extends class_1426
{
  private Vector3f field_225;
  private Vector3f field_226;
  
  public class_992(Vector3f paramVector3f1, Vector3f paramVector3f2)
  {
    this.jdField_field_225_of_type_JavaxVecmathVector3f = paramVector3f1;
    this.field_226 = paramVector3f2;
  }
  
  public final void a()
  {
    GL11.glDisable(3553);
    GL11.glEnable(2903);
    GL11.glDisable(2896);
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    if (this.jdField_field_225_of_type_JavaxVecmathVector4f != null) {
      GlUtil.a38(this.jdField_field_225_of_type_JavaxVecmathVector4f.field_596, this.jdField_field_225_of_type_JavaxVecmathVector4f.field_597, this.jdField_field_225_of_type_JavaxVecmathVector4f.field_598, this.jdField_field_225_of_type_JavaxVecmathVector4f.field_599 * a1());
    } else {
      GlUtil.a38(1.0F, 1.0F, 1.0F, a1());
    }
    GL11.glBegin(1);
    GL11.glVertex3f(this.jdField_field_225_of_type_JavaxVecmathVector3f.field_615, this.jdField_field_225_of_type_JavaxVecmathVector3f.field_616, this.jdField_field_225_of_type_JavaxVecmathVector3f.field_617);
    GL11.glVertex3f(this.field_226.field_615, this.field_226.field_616, this.field_226.field_617);
    GL11.glEnd();
    GL11.glDisable(3042);
    GL11.glDisable(2903);
    GL11.glEnable(2896);
    GL11.glEnable(3553);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_992
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */