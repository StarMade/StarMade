package org.schema.schine.graphicsengine.core.settings;

import java.util.Arrays;

public class StateParameterNotFoundException
  extends Exception
{
  private static final long serialVersionUID = 7466187531660912979L;
  private final Object[] jdField_field_1766_of_type_ArrayOfJavaLangObject;
  private final String jdField_field_1766_of_type_JavaLangString;
  
  public StateParameterNotFoundException(String paramString, Object[] paramArrayOfObject)
  {
    this.jdField_field_1766_of_type_JavaLangString = paramString;
    this.jdField_field_1766_of_type_ArrayOfJavaLangObject = paramArrayOfObject;
  }
  
  public String getMessage()
  {
    if ("parameter \"" + this.jdField_field_1766_of_type_JavaLangString + "\" not recognized; possible parameters: " + this.jdField_field_1766_of_type_ArrayOfJavaLangObject != null) {
      return Arrays.toString(this.jdField_field_1766_of_type_ArrayOfJavaLangObject);
    }
    return "clazz state";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.graphicsengine.core.settings.StateParameterNotFoundException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */