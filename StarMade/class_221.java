import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.schema.schine.graphicsengine.core.GlUtil;

public final class class_221
  implements class_1369
{
  private int field_107;
  
  public class_221(int paramInt)
  {
    this.field_107 = paramInt;
  }
  
  public final void d()
  {
    GL11.glBindTexture(3553, 0);
  }
  
  public final void a(class_1377 paramclass_1377)
  {
    GlUtil.a41(paramclass_1377, "selectionColor", 0.7F, 0.7F, 0.7F, 1.0F);
    GL13.glActiveTexture(33984);
    GL11.glBindTexture(3553, this.field_107);
    GlUtil.a35(paramclass_1377, "mainTexA", 0);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_221
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */