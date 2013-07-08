import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.input.Keyboard;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;

public final class class_843
  extends class_1363
  implements class_1412
{
  private class_930 jdField_field_89_of_type_Class_930;
  private boolean jdField_field_89_of_type_Boolean;
  private class_367 jdField_field_89_of_type_Class_367;
  private class_196 jdField_field_89_of_type_Class_196;
  private static long jdField_field_89_of_type_Long = 0L;
  private static boolean field_90 = false;
  
  public static boolean b3()
  {
    return (field_90) || (System.currentTimeMillis() - jdField_field_89_of_type_Long < 200L);
  }
  
  public class_843(ClientState paramClientState, class_367 paramclass_367)
  {
    super(paramClientState);
    this.jdField_field_89_of_type_Class_367 = paramclass_367;
    this.jdField_field_89_of_type_Class_930 = new class_930(140, 30, class_29.h(), a24());
  }
  
  public final void a1(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    paramclass_1363 = null;
    if ((paramclass_939.jdField_field_1163_of_type_Boolean) && (paramclass_939.jdField_field_1163_of_type_Int == 0))
    {
      paramclass_1363 = (class_371)a24();
      if (!b3())
      {
        System.err.println("PRESSED MOUSE TO ACTIVATE");
        field_90 = true;
        paramclass_1363.b().add(new class_857(this, paramclass_1363));
      }
    }
  }
  
  public final boolean c1()
  {
    class_367[] arrayOfclass_367;
    int i = (arrayOfclass_367 = class_367.values()).length;
    for (int j = 0; j < i; j++)
    {
      class_367 localclass_367;
      if (((localclass_367 = arrayOfclass_367[j]) != this.jdField_field_89_of_type_Class_367) && (localclass_367.a5() == Keyboard.getEventKey()))
      {
        System.err.println("DUIPLICATE KEY");
        class_369 localclass_3692 = localclass_367.a3();
        class_369 localclass_3691;
        if (((a115(localclass_3691 = this.jdField_field_89_of_type_Class_367.a3(), localclass_3692)) || (a115(localclass_3692, localclass_3691)) ? 1 : 0) != 0)
        {
          System.err.println("KEYS RELATED: -> DUPLICATE");
          ((class_371)a24()).a4().b1("WARNING: duplicate detected:\nKeys for \"" + localclass_367.a4() + "\"(" + localclass_367.b1() + ") and\n\"" + this.jdField_field_89_of_type_Class_367.a4() + "\"(" + this.jdField_field_89_of_type_Class_367.b1() + ") have been\nswitched");
          localclass_367.a7(this.jdField_field_89_of_type_Class_367.a5());
        }
      }
    }
    return true;
  }
  
  public final void a2() {}
  
  public final void b()
  {
    if (!this.jdField_field_89_of_type_Boolean) {
      c();
    }
    GlUtil.d1();
    r();
    this.jdField_field_89_of_type_Class_930.field_90.set(0, Keyboard.getKeyName(this.jdField_field_89_of_type_Class_367.a5()));
    this.jdField_field_89_of_type_Class_930.b();
    l();
    GlUtil.c2();
  }
  
  public final float a3()
  {
    return 30.0F;
  }
  
  public final float b1()
  {
    return 140.0F;
  }
  
  public final boolean a4()
  {
    return false;
  }
  
  private static boolean a115(class_369 paramclass_3691, class_369 paramclass_3692)
  {
    for (;;)
    {
      System.err.println("CHEKCING " + paramclass_3691 + " AND " + paramclass_3692);
      if (paramclass_3691 == paramclass_3692) {
        return true;
      }
      if (paramclass_3691.a3()) {
        break;
      }
      paramclass_3691 = paramclass_3691.a2();
    }
    return false;
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_930.field_90 = new ArrayList();
    this.jdField_field_89_of_type_Class_930.field_90.add(Keyboard.getKeyName(this.jdField_field_89_of_type_Class_367.a5()));
    this.jdField_field_89_of_type_Class_930.c();
    this.field_96 = true;
    this.jdField_field_89_of_type_Boolean = true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_843
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */