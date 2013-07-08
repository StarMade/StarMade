import org.schema.game.common.data.element.ElementInformation;

public final class class_306
{
  private ElementInformation jdField_field_658_of_type_OrgSchemaGameCommonDataElementElementInformation;
  private class_743 jdField_field_658_of_type_Class_743;
  public int field_658;
  
  public class_306(ElementInformation paramElementInformation, class_743 paramclass_743)
  {
    this.jdField_field_658_of_type_OrgSchemaGameCommonDataElementElementInformation = paramElementInformation;
    this.jdField_field_658_of_type_Class_743 = paramclass_743;
  }
  
  public final String toString()
  {
    return "how many " + this.jdField_field_658_of_type_OrgSchemaGameCommonDataElementElementInformation.getName() + " (base price: " + this.jdField_field_658_of_type_OrgSchemaGameCommonDataElementElementInformation.getPrice() + "c) you want to sell?\nIf you enter too many, the maximal amount you can sell\nwill be automatically displayed.\nCurrent Selling Value: " + this.jdField_field_658_of_type_Class_743.a107(this.jdField_field_658_of_type_OrgSchemaGameCommonDataElementElementInformation, this.jdField_field_658_of_type_Int) + "c (base " + -this.jdField_field_658_of_type_Int * this.jdField_field_658_of_type_OrgSchemaGameCommonDataElementElementInformation.getPrice() + "c)";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_306
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */