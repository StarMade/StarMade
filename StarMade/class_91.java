import javax.vecmath.Vector4f;
import org.schema.schine.network.client.ClientState;

public final class class_91
  extends class_196
{
  private class_1412 field_90;
  
  public class_91(ClientState paramClientState, class_1412 paramclass_1412, class_781 paramclass_781)
  {
    super(paramClientState, paramclass_1412, "Rate", "Rate " + paramclass_781.field_136);
    this.field_89 = false;
    this.field_90 = paramclass_1412;
  }
  
  public final void c()
  {
    super.c();
    for (int i = 0; i < 10; i++)
    {
      float f = i / 10.0F;
      class_934 localclass_934;
      (localclass_934 = new class_934(a24(), 30, 30, new Vector4f(1.0F - f, f, 0.2F, 1.0F), new Vector4f(1.0F, 1.0F, 1.0F, 1.0F), class_29.e(), String.valueOf(i + 1), this.field_90)).field_89 = new Integer(i);
      if (i < 9) {
        localclass_934.b17(6, 0);
      }
      int j = i * 40;
      localclass_934.a161(j, 35.0F, 0.0F);
      this.field_89.a9(localclass_934);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_91
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */