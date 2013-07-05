/*    */ package org.schema.schine.graphicsengine.core.settings;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ 
/*    */ public class StateParameterNotFoundException extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 7466187531660912979L;
/*    */   private final Object[] jdField_a_of_type_ArrayOfJavaLangObject;
/*    */   private final String jdField_a_of_type_JavaLangString;
/*    */ 
/*    */   public StateParameterNotFoundException(String paramString, Object[] paramArrayOfObject)
/*    */   {
/* 15 */     this.jdField_a_of_type_JavaLangString = paramString;
/* 16 */     this.jdField_a_of_type_ArrayOfJavaLangObject = paramArrayOfObject;
/*    */   }
/*    */ 
/*    */   public String getMessage()
/*    */   {
/* 31 */     if ("parameter \"" + this.jdField_a_of_type_JavaLangString + "\" not recognized; possible parameters: " + this.jdField_a_of_type_ArrayOfJavaLangObject != null) return Arrays.toString(this.jdField_a_of_type_ArrayOfJavaLangObject); return "clazz state";
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.graphicsengine.core.settings.StateParameterNotFoundException
 * JD-Core Version:    0.6.2
 */