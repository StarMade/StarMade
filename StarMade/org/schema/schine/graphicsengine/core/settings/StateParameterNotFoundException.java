/*  1:   */package org.schema.schine.graphicsengine.core.settings;
/*  2:   */
/*  3:   */import java.util.Arrays;
/*  4:   */
/*  6:   */public class StateParameterNotFoundException
/*  7:   */  extends Exception
/*  8:   */{
/*  9:   */  private static final long serialVersionUID = 7466187531660912979L;
/* 10:   */  private final Object[] jdField_a_of_type_ArrayOfJavaLangObject;
/* 11:   */  private final String jdField_a_of_type_JavaLangString;
/* 12:   */  
/* 13:   */  public StateParameterNotFoundException(String paramString, Object[] paramArrayOfObject)
/* 14:   */  {
/* 15:15 */    this.jdField_a_of_type_JavaLangString = paramString;
/* 16:16 */    this.jdField_a_of_type_ArrayOfJavaLangObject = paramArrayOfObject;
/* 17:   */  }
/* 18:   */  
/* 29:   */  public String getMessage()
/* 30:   */  {
/* 31:31 */    if ("parameter \"" + this.jdField_a_of_type_JavaLangString + "\" not recognized; possible parameters: " + this.jdField_a_of_type_ArrayOfJavaLangObject != null) return Arrays.toString(this.jdField_a_of_type_ArrayOfJavaLangObject); return "clazz state";
/* 32:   */  }
/* 33:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.graphicsengine.core.settings.StateParameterNotFoundException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */