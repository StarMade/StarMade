import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;

final class class_324
  implements class_956
{
  class_324(class_322 paramclass_322, short paramShort) {}
  
  public final boolean a(String paramString, class_1079 paramclass_1079)
  {
    try
    {
      if (paramString.length() > 0)
      {
        ElementInformation localElementInformation = ElementKeyMap.getInfo(this.jdField_field_130_of_type_Short);
        if ((paramString = Integer.parseInt(paramString)) <= 0)
        {
          this.jdField_field_130_of_type_Class_322.a6().a4().b1("ERROR: Invalid quantity");
          return false;
        }
        class_743 localclass_743;
        if ((localclass_743 = this.jdField_field_130_of_type_Class_322.a6().a5()) == null)
        {
          this.jdField_field_130_of_type_Class_322.a6().a4().b1("ERROR: no shop available");
          return false;
        }
        if (((i = localclass_743.a108().a42(this.jdField_field_130_of_type_Short)) < 0) || (localclass_743.a108().a41(i) < paramString))
        {
          this.jdField_field_130_of_type_Class_322.a6().a4().b1("ERROR: shop out of item");
          if (i >= 0)
          {
            this.jdField_field_130_of_type_Class_322.a8().a2();
            this.jdField_field_130_of_type_Class_322.a8().a9(String.valueOf(localclass_743.a108().a41(i)));
            this.jdField_field_130_of_type_Class_322.a8().e();
            this.jdField_field_130_of_type_Class_322.a8().g1();
          }
          return false;
        }
        int i = localclass_743.a107(localElementInformation, paramString);
        if (this.jdField_field_130_of_type_Class_322.a6().a20().b5() >= i)
        {
          class_969.b("0022_action - purchase with credits");
          return true;
        }
        paramString = this.jdField_field_130_of_type_Class_322.a6().a20().b5() / localclass_743.a107(localElementInformation, 1);
        this.jdField_field_130_of_type_Class_322.a6().a4().b1("ERROR\nYou can't affort that many!\nYou can only affort " + paramString);
        this.jdField_field_130_of_type_Class_322.a8().a2();
        this.jdField_field_130_of_type_Class_322.a8().a9(String.valueOf(paramString));
        this.jdField_field_130_of_type_Class_322.a8().e();
        this.jdField_field_130_of_type_Class_322.a8().g1();
        return false;
      }
    }
    catch (NumberFormatException localNumberFormatException) {}
    paramclass_1079.onFailedTextCheck("Please only enter numbers!");
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_324
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */