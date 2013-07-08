import javax.vecmath.Vector4f;
import org.schema.schine.network.client.ClientState;

public final class class_1361
  extends class_1402
{
  private class_1363 field_89;
  
  public class_1361(ClientState paramClientState, float paramFloat1, float paramFloat2, Vector4f paramVector4f, class_1363 paramclass_1363)
  {
    super(paramClientState, paramFloat1, paramFloat2, paramVector4f);
    this.field_89 = paramVector4f;
    this.field_89 = paramclass_1363;
  }
  
  public final void b()
  {
    if (this.field_89 != null)
    {
      this.field_89.b();
      if (this.field_89.field_96) {
        this.field_89.l();
      }
    }
    super.b();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1361
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */