import java.io.PrintStream;
import org.schema.schine.network.client.ClientState;

final class class_841
  extends class_1404
{
  class_841(ClientState paramClientState)
  {
    super(paramClientState);
  }
  
  protected final void e()
  {
    int i = ((class_371)a24()).a20().h1();
    if (((class_371)a24()).a45().a156(i) != null)
    {
      class_773.c19(((class_371)a24()).a20(), true);
      return;
    }
    ((class_371)a24()).a4().b1("Cannot change setting!\nYou are in no faction");
    System.err.println("[CLIENT][FactionSetting] faction not found: " + i);
  }
  
  protected final void f()
  {
    int i = ((class_371)a24()).a20().h1();
    if (((class_371)a24()).a45().a156(i) != null)
    {
      class_773.c19(((class_371)a24()).a20(), false);
      return;
    }
    ((class_371)a24()).a4().b1("Cannot change setting!\nYou are in no faction");
    System.err.println("[CLIENT][FactionSetting] faction not found: " + i);
  }
  
  protected final boolean b3()
  {
    int i = ((class_371)a24()).a20().h1();
    class_773 localclass_773;
    if ((localclass_773 = ((class_371)a24()).a45().a156(i)) != null) {
      return localclass_773.d10();
    }
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_841
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */