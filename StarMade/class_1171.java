import javax.vecmath.Vector3f;

public class class_1171
  extends class_1068
{
  private class_48 field_241 = new class_48();
  private class_48 field_242 = new class_48();
  private class_48 field_243 = new class_48();
  private Vector3f jdField_field_240_of_type_JavaxVecmathVector3f = new Vector3f();
  private Vector3f field_244;
  private static float jdField_field_240_of_type_Float = 2.0F;
  
  public short a4()
  {
    return 5;
  }
  
  public short b()
  {
    return 55;
  }
  
  public short c()
  {
    return 75;
  }
  
  public short d()
  {
    return 76;
  }
  
  public short e()
  {
    return 78;
  }
  
  public class_1171(class_1068[] paramArrayOfclass_1068, class_48 paramclass_481, class_48 paramclass_482, int paramInt)
  {
    super(paramArrayOfclass_1068, paramclass_481, paramclass_482, 5, paramInt);
    this.jdField_field_244_of_type_JavaxVecmathVector3f = new Vector3f();
  }
  
  protected final short a(class_48 paramclass_48)
  {
    a8(paramclass_48, this.field_241);
    a8(this.jdField_field_244_of_type_Class_48, this.field_242);
    a8(this.jdField_field_240_of_type_Class_48, this.field_243);
    a9(this.field_243, this.field_242);
    if (this.field_241.field_476 < this.field_242.field_476 / 2 + jdField_field_240_of_type_Float)
    {
      paramclass_48 = this;
      short s = a4();
      if (paramclass_48.field_241.field_476 == paramclass_48.field_242.field_476 / 2) {
        s = paramclass_48.c();
      } else if ((paramclass_48.field_241.field_476 == paramclass_48.field_242.field_476 / 2 + 1) || (paramclass_48.field_241.field_476 == paramclass_48.field_242.field_476 / 2 - 1)) {
        s = paramclass_48.d();
      } else if (paramclass_48.field_241.field_477 % 8 == 0) {
        if (paramclass_48.field_241.field_476 == 2) {
          s = paramclass_48.b();
        } else {
          s = paramclass_48.e();
        }
      }
      if ((paramclass_48.field_241.field_476 == 0) || (paramclass_48.field_241.field_476 == paramclass_48.field_242.field_476 - 1))
      {
        if ((paramclass_48.field_241.field_476 == 0) && (paramclass_48.field_241.field_475 > 0) && (paramclass_48.field_241.field_475 % 10 == 0) && (paramclass_48.field_241.field_477 % 5 == 0)) {
          return paramclass_48.b();
        }
        return paramclass_48.a4();
      }
      if ((paramclass_48.field_241.field_475 == paramclass_48.field_242.field_475 - 1) || (paramclass_48.field_241.field_475 == 0)) {
        return s;
      }
      return 0;
    }
    paramclass_48 = this;
    this.jdField_field_240_of_type_JavaxVecmathVector3f.set(paramclass_48.field_241.field_475 + 0.5F, paramclass_48.field_241.field_476 * 1.3F, 0.0F);
    paramclass_48.jdField_field_244_of_type_JavaxVecmathVector3f.set(paramclass_48.field_242.field_475 / 2.0F, paramclass_48.field_242.field_476 / 2.0F + jdField_field_240_of_type_Float, 0.0F);
    paramclass_48.jdField_field_240_of_type_JavaxVecmathVector3f.sub(paramclass_48.jdField_field_244_of_type_JavaxVecmathVector3f);
    float f;
    if (((f = paramclass_48.jdField_field_240_of_type_JavaxVecmathVector3f.length()) < paramclass_48.field_242.field_475 / 2.0F + 0.5F) && ((f > paramclass_48.field_242.field_475 / 2.0F - 1.5F) || (paramclass_48.field_241.field_476 == paramclass_48.field_242.field_476 - 1) || (paramclass_48.field_241.field_476 == paramclass_48.field_242.field_476 - 2) || (paramclass_48.field_241.field_477 == paramclass_48.field_242.field_477 - 1))) {
      return paramclass_48.a4();
    }
    return 0;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1171
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */