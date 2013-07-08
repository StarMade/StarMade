import java.util.HashMap;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;

final class class_125
  extends class_934
{
  class_125(ClientState paramClientState, Object paramObject, class_1412 paramclass_1412, class_930 paramclass_930)
  {
    super(paramClientState, 120, 30, paramObject, paramclass_1412);
  }
  
  public final void b()
  {
    int i = ((class_371)a24()).a20().h1();
    class_773 localclass_773;
    if ((localclass_773 = ((class_371)a24()).a45().a156(i)) != null)
    {
      class_758 localclass_758;
      if (((localclass_758 = (class_758)localclass_773.a162().get(((class_371)a24()).a20().getName())) != null) && (localclass_758.c15(localclass_773)))
      {
        super.b();
        return;
      }
      GlUtil.d1();
      r();
      this.field_89.b();
      GlUtil.c2();
      return;
    }
    GlUtil.d1();
    r();
    this.field_89.b();
    GlUtil.c2();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_125
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */