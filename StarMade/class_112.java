import java.io.PrintStream;
import java.util.HashMap;

public final class class_112
  extends class_12
  implements class_1412
{
  class_773 jdField_field_4_of_type_Class_773;
  private class_116 jdField_field_4_of_type_Class_116;
  class_758 jdField_field_4_of_type_Class_758;
  
  public class_112(class_371 paramclass_371, class_773 paramclass_773, class_758 paramclass_758)
  {
    super(paramclass_371);
    this.jdField_field_4_of_type_Class_773 = paramclass_773;
    this.jdField_field_4_of_type_Class_116 = new class_116(this, paramclass_371, this, "Settings for " + paramclass_758.jdField_field_136_of_type_JavaLangString, "", paramclass_773);
    this.jdField_field_4_of_type_Class_758 = paramclass_758;
  }
  
  public final void handleKeyEvent() {}
  
  public final void a(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    if ((paramclass_939.jdField_field_1163_of_type_Boolean) && (paramclass_939.jdField_field_1163_of_type_Int == 0))
    {
      if ((paramclass_1363.b29().equals("CANCEL")) || (paramclass_1363.b29().equals("X")))
      {
        System.err.println("CANCEL");
        d();
      }
      if ((paramclass_1363 instanceof class_105))
      {
        paramclass_1363 = ((Integer)paramclass_1363.b29()).intValue();
        if ((paramclass_939 = (class_758)this.jdField_field_4_of_type_Class_773.a162().get(this.field_4.a20().getName())) == null)
        {
          this.field_4.a4().b1("You are not in this faction");
          return;
        }
        try
        {
          if ((paramclass_939.jdField_field_136_of_type_JavaLangString.equals(this.jdField_field_4_of_type_Class_758.jdField_field_136_of_type_JavaLangString)) && (paramclass_939.jdField_field_136_of_type_Byte == 4))
          {
            d();
            paramclass_1363 = null;
            new class_118(this, this.field_4, "Confirm", "You are admin of this faction.\nDo you really want to change your role", paramclass_939, paramclass_1363).c1();
            return;
          }
          if (!paramclass_939.d7(this.jdField_field_4_of_type_Class_773)) {
            this.field_4.a4().b1("You cannot change role:\npermission denied!");
          } else if (paramclass_939.jdField_field_136_of_type_Byte < this.jdField_field_4_of_type_Class_758.jdField_field_136_of_type_Byte) {
            this.field_4.a4().b1("You cannot change\nroles of higher ranked\nplayers");
          } else {
            this.jdField_field_4_of_type_Class_773.b32(this.field_4.a20().getName(), this.jdField_field_4_of_type_Class_758.jdField_field_136_of_type_JavaLangString, (byte)paramclass_1363, this.field_4.a12());
          }
          d();
          return;
        }
        catch (Exception localException)
        {
          paramclass_1363 = null;
          localException.printStackTrace();
        }
      }
    }
  }
  
  public final boolean a1()
  {
    return false;
  }
  
  public final class_1363 a3()
  {
    return this.jdField_field_4_of_type_Class_116;
  }
  
  public final void a2() {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_112
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */