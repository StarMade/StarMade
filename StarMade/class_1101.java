import javax.vecmath.Vector3f;

public final class class_1101
  extends class_1068
{
  private class_48 field_241 = new class_48();
  private class_48 field_242 = new class_48();
  private class_48 field_243 = new class_48();
  private Vector3f field_240;
  private Vector3f field_244;
  
  public class_1101(class_1068[] paramArrayOfclass_1068, class_48 paramclass_481, class_48 paramclass_482)
  {
    super(paramArrayOfclass_1068, paramclass_481, paramclass_482, 5, 0);
    this.jdField_field_240_of_type_JavaxVecmathVector3f = new Vector3f();
    this.jdField_field_244_of_type_JavaxVecmathVector3f = new Vector3f();
  }
  
  protected final short a(class_48 paramclass_48)
  {
    a8(paramclass_48, this.field_241);
    a8(this.jdField_field_244_of_type_Class_48, this.field_242);
    a8(this.jdField_field_240_of_type_Class_48, this.field_243);
    a9(this.field_243, this.field_242);
    float f;
    if (this.field_241.field_476 < this.field_242.field_476 / 2)
    {
      paramclass_48 = this;
      this.jdField_field_240_of_type_JavaxVecmathVector3f.set(paramclass_48.field_241.field_475 + 0.5F, 0.0F, paramclass_48.field_241.field_477 + 0.5F);
      paramclass_48.jdField_field_244_of_type_JavaxVecmathVector3f.set(paramclass_48.field_242.field_475 / 2.0F, 0.0F, paramclass_48.field_242.field_477 / 2.0F);
      paramclass_48.jdField_field_240_of_type_JavaxVecmathVector3f.sub(paramclass_48.jdField_field_244_of_type_JavaxVecmathVector3f);
      if (((f = paramclass_48.jdField_field_240_of_type_JavaxVecmathVector3f.length()) < 1.0F) && (paramclass_48.field_241.field_476 < paramclass_48.field_242.field_476 - 15)) {
        return 2;
      }
      if ((f < paramclass_48.field_242.field_475 / 2.0F + 0.5F) && ((f > paramclass_48.field_242.field_475 / 2.0F - 1.5F) || (paramclass_48.field_241.field_476 <= 1)))
      {
        if ((paramclass_48.field_241.field_476 > 0) && (paramclass_48.field_241.field_476 % 20 == 0)) {
          return 77;
        }
        return 75;
      }
      return 0;
    }
    paramclass_48 = this;
    this.jdField_field_240_of_type_JavaxVecmathVector3f.set(paramclass_48.field_241.field_475 + 0.5F, 0.0F, paramclass_48.field_241.field_477 + 0.5F);
    paramclass_48.jdField_field_244_of_type_JavaxVecmathVector3f.set(paramclass_48.field_242.field_475 / 2.0F, 0.0F, paramclass_48.field_242.field_477 / 2.0F);
    paramclass_48.jdField_field_240_of_type_JavaxVecmathVector3f.sub(paramclass_48.jdField_field_244_of_type_JavaxVecmathVector3f);
    if (((f = paramclass_48.jdField_field_240_of_type_JavaxVecmathVector3f.length()) < 1.0F) && (paramclass_48.field_241.field_476 < paramclass_48.field_242.field_476 - 15)) {
      return 2;
    }
    if ((f < paramclass_48.field_242.field_475 / 2.0F + 0.5F) && ((f > paramclass_48.field_242.field_475 / 2.0F - 1.5F) || (paramclass_48.field_241.field_476 >= paramclass_48.field_242.field_476 - 1.0F)))
    {
      if ((paramclass_48.field_241.field_476 > 0) && (paramclass_48.field_241.field_476 % 20 == 0)) {
        return 77;
      }
      return 75;
    }
    return 0;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1101
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */