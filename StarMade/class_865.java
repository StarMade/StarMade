import org.schema.game.common.data.element.ElementInformation;

final class class_865
{
  private ElementInformation jdField_field_1100_of_type_OrgSchemaGameCommonDataElementElementInformation;
  
  public class_865(class_895 paramclass_895, ElementInformation paramElementInformation)
  {
    this.jdField_field_1100_of_type_OrgSchemaGameCommonDataElementElementInformation = paramElementInformation;
  }
  
  public final String toString()
  {
    class_743 localclass_743;
    if ((localclass_743 = ((class_371)this.jdField_field_1100_of_type_Class_895.a24()).a5()) == null) {
      return "err";
    }
    int j;
    if ((j = localclass_743.a108().a42(this.jdField_field_1100_of_type_OrgSchemaGameCommonDataElementElementInformation.getId())) < 0) {
      return "n/a";
    }
    int i = localclass_743.a108().a41(j);
    return "in stock: " + String.valueOf(i);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_865
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */