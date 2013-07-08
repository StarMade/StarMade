import org.schema.game.common.data.element.ElementInformation;

public final class class_320
{
  private ElementInformation jdField_field_682_of_type_OrgSchemaGameCommonDataElementElementInformation;
  private class_743 jdField_field_682_of_type_Class_743;
  public int field_682;
  
  public class_320(ElementInformation paramElementInformation, class_743 paramclass_743)
  {
    this.jdField_field_682_of_type_OrgSchemaGameCommonDataElementElementInformation = paramElementInformation;
    this.jdField_field_682_of_type_Class_743 = paramclass_743;
  }
  
  public final String toString()
  {
    return "how many " + this.jdField_field_682_of_type_OrgSchemaGameCommonDataElementElementInformation.getName() + " you want to buy?\nIf you enter too many, the maximal amount you can affort\nwill be displayed.\nCurrent Buying Value: " + this.jdField_field_682_of_type_Class_743.a107(this.jdField_field_682_of_type_OrgSchemaGameCommonDataElementElementInformation, this.jdField_field_682_of_type_Int) + "c (base " + this.jdField_field_682_of_type_Int * this.jdField_field_682_of_type_OrgSchemaGameCommonDataElementElementInformation.getPrice() + "c)";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_320
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */