import java.lang.reflect.Constructor;

public enum class_1112
{
  private Class jdField_field_1371_of_type_JavaLangClass;
  
  private class_1112(Class paramClass)
  {
    this.jdField_field_1371_of_type_JavaLangClass = paramClass;
  }
  
  public static class_983 a(class_1112 paramclass_1112, class_1063 paramclass_1063)
  {
    return (class_983)paramclass_1112.jdField_field_1371_of_type_JavaLangClass.getConstructor(new Class[] { vY.class, Boolean.TYPE }).newInstance(new Object[] { paramclass_1063, Boolean.valueOf(false) });
  }
  
  public static class_1112 a1(class_1063 paramclass_1063)
  {
    paramclass_1063 = paramclass_1063.field_223.a13().a1();
    for (int i = 0; i < values().length; i++) {
      if (values()[i].jdField_field_1371_of_type_JavaLangClass.isInstance(paramclass_1063)) {
        return values()[i];
      }
    }
    throw new IllegalArgumentException("Could not find simProgram for " + paramclass_1063);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1112
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */