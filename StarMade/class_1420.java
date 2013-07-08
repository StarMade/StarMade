import javax.vecmath.Vector4f;

public final class class_1420
  implements class_1422
{
  private final Vector4f jdField_field_280_of_type_JavaxVecmathVector4f = new Vector4f();
  private float jdField_field_280_of_type_Float;
  private float jdField_field_281_of_type_Float;
  private String jdField_field_280_of_type_JavaLangString;
  private Vector4f jdField_field_281_of_type_JavaxVecmathVector4f;
  public static final class_1383 field_280;
  
  public class_1420(String paramString, Vector4f paramVector4f)
  {
    this.jdField_field_281_of_type_JavaxVecmathVector4f = paramVector4f;
    this.jdField_field_280_of_type_JavaLangString = paramString;
    this.jdField_field_280_of_type_Float = 15.0F;
    this.jdField_field_281_of_type_Float = (paramString = this).jdField_field_280_of_type_Float;
    paramString.jdField_field_280_of_type_JavaxVecmathVector4f.set(paramString.jdField_field_281_of_type_JavaxVecmathVector4f);
  }
  
  public final Vector4f a()
  {
    return this.jdField_field_280_of_type_JavaxVecmathVector4f;
  }
  
  public final void a1(class_941 paramclass_941)
  {
    this.jdField_field_281_of_type_Float = Math.max(0.0F, this.jdField_field_281_of_type_Float - paramclass_941.a());
    if (this.jdField_field_281_of_type_Float > this.jdField_field_280_of_type_Float - 0.25F)
    {
      this.jdField_field_280_of_type_JavaxVecmathVector4f.field_596 = (1.0F - 0.5F * jdField_field_280_of_type_Class_1383.a1());
      this.jdField_field_280_of_type_JavaxVecmathVector4f.field_597 = (1.0F - 0.5F * jdField_field_280_of_type_Class_1383.a1());
    }
    else
    {
      this.jdField_field_280_of_type_JavaxVecmathVector4f.set(this.jdField_field_281_of_type_JavaxVecmathVector4f);
    }
    if (this.jdField_field_281_of_type_Float < 3.0F)
    {
      paramclass_941 = this.jdField_field_281_of_type_Float / 3.0F;
      this.jdField_field_280_of_type_JavaxVecmathVector4f.field_599 = paramclass_941;
    }
  }
  
  public final String toString()
  {
    return this.jdField_field_280_of_type_JavaLangString;
  }
  
  static
  {
    jdField_field_280_of_type_Class_1383 = new class_1383(25.0F);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1420
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */