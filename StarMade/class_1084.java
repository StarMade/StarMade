public enum class_1084
{
  private Class jdField_field_1360_of_type_JavaLangClass;
  public class_1043 field_1360;
  
  private class_1084(Class paramClass, class_1043 paramclass_1043)
  {
    this.jdField_field_1360_of_type_JavaLangClass = paramClass;
    this.jdField_field_1360_of_type_Class_1043 = paramclass_1043;
  }
  
  public static class_1084 a(Class paramClass)
  {
    for (int i = 0; i < values().length; i++) {
      if (values()[i].jdField_field_1360_of_type_JavaLangClass == paramClass) {
        return values()[i];
      }
    }
    throw new NullPointerException("SegControllerType Not Found: " + paramClass.getClass());
  }
  
  static
  {
    jdField_field_1360_of_type_Class_1084 = new class_1084("SHIP", 0, kd.class, new class_1086());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1084
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */