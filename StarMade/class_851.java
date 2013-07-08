import javax.vecmath.Vector3f;
import org.lwjgl.opengl.GL11;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;

public final class class_851
  extends class_1363
{
  private class_371 field_89;
  
  public class_851(ClientState paramClientState)
  {
    super(paramClientState);
    this.field_89 = ((class_371)paramClientState);
  }
  
  public final void a2() {}
  
  public final void b()
  {
    class_747 localclass_747;
    if ((localclass_747 = this.field_89.a25()) != null)
    {
      Vector3f localVector3f1 = new Vector3f(1.0F, 0.0F, 0.0F);
      Vector3f localVector3f2 = new Vector3f(0.0F, 1.0F, 0.0F);
      Vector3f localVector3f3 = new Vector3f(0.0F, -1.0F, 0.0F);
      Vector3f localVector3f4 = GlUtil.e(new Vector3f(), localclass_747.getWorldTransform());
      Vector3f localVector3f5 = GlUtil.f(new Vector3f(), localclass_747.getWorldTransform());
      GlUtil.b(new Vector3f(), localclass_747.getWorldTransform());
      float f = localVector3f2.dot(localVector3f5);
      localVector3f3.dot(localVector3f5);
      f = (f + 1.0F) / 2.0F * 128.0F;
      localVector3f1.dot(localVector3f4);
      GL11.glBegin(1);
      GlUtil.a38(1.0F, 1.0F, 1.0F, 0.4F);
      GL11.glVertex2f(0.0F, f);
      GL11.glVertex2f(128.0F, f);
      GL11.glEnd();
    }
  }
  
  public final void c() {}
  
  public final float a3()
  {
    return 128.0F;
  }
  
  public final float b1()
  {
    return 128.0F;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_851
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */