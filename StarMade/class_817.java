import java.io.PrintStream;
import java.util.ArrayList;
import org.schema.schine.network.client.ClientState;

final class class_817
  extends class_173
{
  class_817(ClientState paramClientState, class_1412 paramclass_1412)
  {
    super(paramClientState, paramclass_1412);
  }
  
  public final int a57()
  {
    return a20().a20().h1();
  }
  
  public final void a1(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    if (paramclass_939.a())
    {
      System.err.println("CALLED: " + paramclass_1363 + "; " + paramclass_1363.field_89);
      paramclass_939 = (class_773)((java.lang.Object[])paramclass_1363.field_89)[0];
      paramclass_1363 = (class_758)((java.lang.Object[])paramclass_1363.field_89)[1];
      new class_112(a20(), paramclass_939, paramclass_1363).c1();
    }
  }
  
  public final boolean a4()
  {
    return !a20().b().isEmpty();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_817
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */