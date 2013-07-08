import javax.vecmath.Vector4f;
import org.lwjgl.opengl.GL11;
import org.schema.schine.graphicsengine.core.GlUtil;

public final class class_816
  implements class_1369
{
  private int field_107;
  
  public class_816()
  {
    new class_1433(20.0F);
    this.field_107 = -1;
  }
  
  public final void d()
  {
    GL11.glBindTexture(3553, 0);
  }
  
  public final void a(class_1377 paramclass_1377)
  {
    if (this.field_107 < 0) {
      this.field_107 = class_969.a2().a5("flare").a153().a1().field_1592;
    }
    GlUtil.a42(paramclass_1377, "Param", new Vector4f(230.0F, 0.31F, 210.0F, 0.001F));
    GL11.glBindTexture(3553, this.field_107);
    GlUtil.a35(paramclass_1377, "Texture", 0);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_816
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */