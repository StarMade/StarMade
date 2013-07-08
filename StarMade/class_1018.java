import org.schema.common.FastMath;
import org.schema.schine.graphicsengine.core.settings.StateParameterNotFoundException;

public class class_1018
  extends class_1014
{
  public Object[] field_228;
  private int jdField_field_228_of_type_Int;
  
  public class_1018(Object... paramVarArgs)
  {
    if ((!jdField_field_228_of_type_Boolean) && (paramVarArgs.length <= 0)) {
      throw new AssertionError();
    }
    this.jdField_field_228_of_type_ArrayOfJavaLangObject = paramVarArgs;
  }
  
  public final Object a(String paramString)
  {
    if ((this.jdField_field_228_of_type_ArrayOfJavaLangObject[0] instanceof class_943)) {
      return class_943.a(paramString.split("\\s"));
    }
    for (int i = 0; i < this.jdField_field_228_of_type_ArrayOfJavaLangObject.length; i++) {
      if (paramString.equals(this.jdField_field_228_of_type_ArrayOfJavaLangObject[i].toString()))
      {
        this.jdField_field_228_of_type_Int = i;
        return this.jdField_field_228_of_type_ArrayOfJavaLangObject[this.jdField_field_228_of_type_Int];
      }
    }
    throw new StateParameterNotFoundException(paramString, this.jdField_field_228_of_type_ArrayOfJavaLangObject);
  }
  
  public final String a1()
  {
    return this.jdField_field_228_of_type_ArrayOfJavaLangObject[0].getClass().getSimpleName();
  }
  
  public final Object a2()
  {
    if (this.jdField_field_228_of_type_ArrayOfJavaLangObject.length <= 1) {
      return this.jdField_field_228_of_type_ArrayOfJavaLangObject[this.jdField_field_228_of_type_Int];
    }
    if ((this.jdField_field_228_of_type_ArrayOfJavaLangObject[0] instanceof class_943)) {
      throw new StateParameterNotFoundException("NEXT CALLED ON MANIFOLD STATE", this.jdField_field_228_of_type_ArrayOfJavaLangObject);
    }
    this.jdField_field_228_of_type_Int = ((this.jdField_field_228_of_type_Int + 1) % this.jdField_field_228_of_type_ArrayOfJavaLangObject.length);
    return this.jdField_field_228_of_type_ArrayOfJavaLangObject[this.jdField_field_228_of_type_Int];
  }
  
  public final Object b()
  {
    if (this.jdField_field_228_of_type_ArrayOfJavaLangObject.length <= 1) {
      return this.jdField_field_228_of_type_ArrayOfJavaLangObject[this.jdField_field_228_of_type_Int];
    }
    if ((this.jdField_field_228_of_type_ArrayOfJavaLangObject[0] instanceof class_943)) {
      throw new StateParameterNotFoundException("PREVIOUS CALLED ON MANIFOLD STATE", this.jdField_field_228_of_type_ArrayOfJavaLangObject);
    }
    this.jdField_field_228_of_type_Int = FastMath.a6(this.jdField_field_228_of_type_Int - 1, this.jdField_field_228_of_type_ArrayOfJavaLangObject.length);
    return this.jdField_field_228_of_type_ArrayOfJavaLangObject[this.jdField_field_228_of_type_Int];
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1018
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */