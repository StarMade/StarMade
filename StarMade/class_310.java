final class class_310
  implements class_956
{
  class_310(class_304 paramclass_304, short paramShort) {}
  
  public final boolean a(String paramString, class_1079 paramclass_1079)
  {
    try
    {
      if (paramString.length() > 0)
      {
        paramString = Integer.parseInt(paramString);
        String str = this.jdField_field_130_of_type_Class_304.a6().a20().getInventory(null).b7(this.jdField_field_130_of_type_Short);
        class_743 localclass_743;
        if ((localclass_743 = this.jdField_field_130_of_type_Class_304.a6().a5()) == null)
        {
          this.jdField_field_130_of_type_Class_304.a6().a4().b1("ERROR: no shop available");
          return false;
        }
        if (paramString <= 0)
        {
          this.jdField_field_130_of_type_Class_304.a6().a4().b1("ERROR: Invalid quantity");
          return false;
        }
        int i;
        if ((i = localclass_743.a108().a42(this.jdField_field_130_of_type_Short)) >= 0)
        {
          localclass_743.a108();
          if (localclass_743.a108().a41(i) + paramString > class_649.e())
          {
            this.jdField_field_130_of_type_Class_304.a6().a4().b1("ERROR: Shop cannot stock any\nmore of that item!");
            localclass_743.a108();
            this.jdField_field_130_of_type_Class_304.a8().a9(String.valueOf(class_649.e() - localclass_743.a108().a41(i)));
            this.jdField_field_130_of_type_Class_304.a8().e();
            return false;
          }
        }
        if (paramString <= str)
        {
          class_969.b("0022_action - receive credits");
          return true;
        }
        this.jdField_field_130_of_type_Class_304.a6().a4().b1("ERROR\nYou can't sell that many!\nYou can only sell " + str + "!");
        this.jdField_field_130_of_type_Class_304.a8().a9(String.valueOf(str));
        this.jdField_field_130_of_type_Class_304.a8().e();
        this.jdField_field_130_of_type_Class_304.a8().g1();
        return false;
      }
    }
    catch (NumberFormatException localNumberFormatException) {}
    paramclass_1079.onFailedTextCheck("Please only enter numbers!");
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_310
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */