import javax.vecmath.Vector4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.schema.schine.graphicsengine.core.GlUtil;

public final class class_1371
  implements class_1369
{
  public int field_107;
  public Vector4f field_107;
  
  public class_1371()
  {
    this.jdField_field_107_of_type_JavaxVecmathVector4f = new Vector4f(0.0F, 0.0F, 0.0F, 1.0F);
  }
  
  public final void d()
  {
    GL13.glActiveTexture(33984);
    GL11.glBindTexture(3553, 0);
  }
  
  public final void a(class_1377 paramclass_1377)
  {
    GlUtil.a42(paramclass_1377, "silhouetteColor", this.jdField_field_107_of_type_JavaxVecmathVector4f);
    GL13.glActiveTexture(33984);
    GL11.glBindTexture(3553, this.jdField_field_107_of_type_Int);
    GlUtil.a35(paramclass_1377, "silhouetteTexture", 0);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1371
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */