import javax.vecmath.Vector2f;

public class class_1066
  extends class_1068
{
  private Vector2f jdField_field_240_of_type_JavaxVecmathVector2f;
  private float jdField_field_240_of_type_Float;
  private float field_244;
  
  public class_1066(class_1068[] paramArrayOfclass_1068, class_48 paramclass_481, class_48 paramclass_482)
  {
    super(paramArrayOfclass_1068, paramclass_481, paramclass_482, 5, 0);
    new class_48();
    new class_48();
    new class_48();
    this.jdField_field_240_of_type_JavaxVecmathVector2f = new Vector2f();
    new Vector2f();
    this.jdField_field_244_of_type_Float = 5.0F;
    this.jdField_field_240_of_type_Float = 57.0F;
  }
  
  public final boolean a3(class_48 paramclass_48)
  {
    if ((paramclass_48.field_476 >= this.jdField_field_240_of_type_Class_48.field_476) && (paramclass_48.field_476 <= this.jdField_field_244_of_type_Class_48.field_476))
    {
      this.jdField_field_240_of_type_JavaxVecmathVector2f.field_577 = paramclass_48.field_475;
      this.jdField_field_240_of_type_JavaxVecmathVector2f.field_578 = paramclass_48.field_477;
      if ((this.jdField_field_240_of_type_JavaxVecmathVector2f.length() < this.jdField_field_240_of_type_Float + this.jdField_field_244_of_type_Float) && (this.jdField_field_240_of_type_JavaxVecmathVector2f.length() > this.jdField_field_240_of_type_Float - this.jdField_field_244_of_type_Float)) {
        return true;
      }
    }
    return false;
  }
  
  protected final short a(class_48 paramclass_48)
  {
    if ((!jdField_field_240_of_type_Boolean) && (!a3(paramclass_48))) {
      throw new AssertionError();
    }
    if ((paramclass_48.field_476 > this.jdField_field_240_of_type_Class_48.field_476) && (paramclass_48.field_476 < this.jdField_field_244_of_type_Class_48.field_476))
    {
      this.jdField_field_240_of_type_JavaxVecmathVector2f.field_577 = paramclass_48.field_475;
      this.jdField_field_240_of_type_JavaxVecmathVector2f.field_578 = paramclass_48.field_477;
      if ((this.jdField_field_240_of_type_JavaxVecmathVector2f.length() < this.jdField_field_240_of_type_Float + this.jdField_field_244_of_type_Float) && (this.jdField_field_240_of_type_JavaxVecmathVector2f.length() > this.jdField_field_240_of_type_Float - this.jdField_field_244_of_type_Float))
      {
        if ((this.jdField_field_240_of_type_JavaxVecmathVector2f.length() < this.jdField_field_240_of_type_Float + (this.jdField_field_244_of_type_Float - 2.0F)) && (this.jdField_field_240_of_type_JavaxVecmathVector2f.length() > this.jdField_field_240_of_type_Float - (this.jdField_field_244_of_type_Float - 2.0F))) {
          return 0;
        }
        return 5;
      }
    }
    else if ((paramclass_48.field_476 == this.jdField_field_240_of_type_Class_48.field_476) || (paramclass_48.field_476 == this.jdField_field_244_of_type_Class_48.field_476))
    {
      this.jdField_field_240_of_type_JavaxVecmathVector2f.field_577 = paramclass_48.field_475;
      this.jdField_field_240_of_type_JavaxVecmathVector2f.field_578 = paramclass_48.field_477;
      if ((this.jdField_field_240_of_type_JavaxVecmathVector2f.length() < this.jdField_field_240_of_type_Float + 1.0F) && (this.jdField_field_240_of_type_JavaxVecmathVector2f.length() > this.jdField_field_240_of_type_Float - 1.0F)) {
        return 55;
      }
      return 5;
    }
    return 0;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1066
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */