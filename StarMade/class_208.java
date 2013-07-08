final class class_208
{
  class_208(class_206 paramclass_206) {}
  
  public final String toString()
  {
    if (this.field_632.a11().jdField_field_795_of_type_Int == class_206.a86(this.field_632)) {
      return "*click on block*";
    }
    switch (class_206.a86(this.field_632))
    {
    case 1: 
      if (this.field_632.a11().jdField_field_795_of_type_Boolean) {
        return "unset";
      }
      return "set";
    case 2: 
      if (this.field_632.a11().field_796) {
        return "unset";
      }
      return "set";
    case 4: 
      if (this.field_632.a11().field_797) {
        return "unset";
      }
      return "set";
    }
    return "error";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_208
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */