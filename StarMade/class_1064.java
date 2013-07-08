import javax.vecmath.Vector2f;

public final class class_1064
  extends class_1068
{
  private class_48 field_241 = new class_48();
  private class_48 field_242 = new class_48();
  private class_48 field_243 = new class_48();
  private Vector2f field_240;
  private Vector2f field_244;
  
  public class_1064(class_1068[] paramArrayOfclass_1068, class_48 paramclass_481, class_48 paramclass_482)
  {
    super(paramArrayOfclass_1068, paramclass_481, paramclass_482, 7, 0);
    this.jdField_field_240_of_type_JavaxVecmathVector2f = new Vector2f();
    this.jdField_field_244_of_type_JavaxVecmathVector2f = new Vector2f();
  }
  
  protected final short a(class_48 paramclass_48)
  {
    a8(paramclass_48, this.field_241);
    a8(this.jdField_field_244_of_type_Class_48, this.field_242);
    a8(this.jdField_field_240_of_type_Class_48, this.field_243);
    a9(this.field_243, this.field_242);
    this.jdField_field_240_of_type_JavaxVecmathVector2f.set(this.field_241.field_475 + 0.5F, this.field_241.field_477 + 0.5F);
    this.jdField_field_244_of_type_JavaxVecmathVector2f.set(this.field_242.field_475 / 2, this.field_242.field_477 / 2);
    this.jdField_field_244_of_type_JavaxVecmathVector2f.sub(this.jdField_field_240_of_type_JavaxVecmathVector2f);
    if (this.jdField_field_244_of_type_JavaxVecmathVector2f.length() > this.field_242.field_475 / 2) {
      return 32767;
    }
    this.jdField_field_240_of_type_JavaxVecmathVector2f.set(this.field_241.field_475, this.field_241.field_477);
    this.jdField_field_244_of_type_JavaxVecmathVector2f.set(this.field_242.field_475 / 2, this.field_242.field_477 / 2);
    if (this.jdField_field_240_of_type_JavaxVecmathVector2f.equals(this.jdField_field_244_of_type_JavaxVecmathVector2f)) {
      return 113;
    }
    return 0;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1064
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */