import java.io.PrintStream;
import java.util.HashMap;
import org.schema.schine.network.client.ClientState;

public final class class_255
  extends class_1363
  implements class_1412
{
  private class_860 field_89;
  
  public class_255(ClientState paramClientState)
  {
    super(paramClientState);
  }
  
  public final void a2() {}
  
  public final void b()
  {
    k();
  }
  
  public final void c()
  {
    this.field_89 = new class_860(a24(), this);
    this.field_89.c();
    a9(this.field_89);
  }
  
  public final float a3()
  {
    return 0.0F;
  }
  
  public final float b1()
  {
    return 0.0F;
  }
  
  public final void a1(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    class_773 localclass_773 = null;
    if ((paramclass_939.jdField_field_1163_of_type_Boolean) && (paramclass_939.jdField_field_1163_of_type_Int == 0))
    {
      System.err.println("Callback " + paramclass_1363 + "; " + paramclass_1363.field_89);
      if ((paramclass_1363.field_89 != null) && (paramclass_1363.field_89.toString().startsWith("JOIN_")))
      {
        paramclass_939 = Integer.parseInt(paramclass_1363.field_89.toString().substring(5));
        if (((class_371)a24()).a20().h1() != 0) {
          new class_256((class_371)a24(), "Confirm", "Do you really want to join this faction\nand leave your current faction?", paramclass_939).c1();
        } else {
          ((class_371)a24()).a20().a141().b6(paramclass_939);
        }
      }
      if ((paramclass_1363.field_89 != null) && (paramclass_1363.field_89.toString().startsWith("REL_")))
      {
        paramclass_939 = Integer.parseInt(paramclass_1363.field_89.toString().substring(4));
        if ((paramclass_1363 = ((class_371)a24()).a14().field_4.field_4.field_4).a6().a20().h1() == 0)
        {
          paramclass_1363.a6().a4().b1("Cannot modify:\nYou are not in any faction");
          return;
        }
        localclass_773 = paramclass_1363.a6().a45().a156(paramclass_1363.a6().a20().h1());
        paramclass_939 = paramclass_1363.a6().a45().a156(paramclass_939);
        if (localclass_773 == null)
        {
          paramclass_1363.a6().a4().b1("Your faction is corrupted\nand does not exist");
          return;
        }
        if (paramclass_939 == null)
        {
          paramclass_1363.a6().a4().b1("Target faction is corrupted\nand does not exist");
          return;
        }
        if (localclass_773 == paramclass_939)
        {
          paramclass_1363.a6().a4().b1("Your faction can't have\nrelations with itself");
          return;
        }
        if (paramclass_939.a3() < 0)
        {
          paramclass_1363.a6().a4().b1("Cannot modify relations\nto an NPC faction!");
          return;
        }
        class_758 localclass_758;
        if (((localclass_758 = (class_758)localclass_773.a162().get(paramclass_1363.a6().a20().getName())) == null) || (!localclass_758.a148(localclass_773)))
        {
          paramclass_1363.a6().a4().b1("Cannot change relation:\nPermission denied!");
          return;
        }
        paramclass_1363.e2(true);
        new class_344(paramclass_1363.a6(), localclass_773, paramclass_939).c1();
      }
    }
  }
  
  public final boolean a4()
  {
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_255
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */