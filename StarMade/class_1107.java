import javax.vecmath.Vector3f;

public class class_1107
  extends class_1068
{
  private class_48 field_241 = new class_48();
  private class_48 field_242 = new class_48();
  private class_48 field_243 = new class_48();
  private Vector3f jdField_field_240_of_type_JavaxVecmathVector3f = new Vector3f();
  private Vector3f field_244;
  
  public class_1107(class_1068[] paramArrayOfclass_1068, class_48 paramclass_481, class_48 paramclass_482)
  {
    super(paramArrayOfclass_1068, paramclass_481, paramclass_482, 5, 0);
    this.jdField_field_244_of_type_JavaxVecmathVector3f = new Vector3f();
  }
  
  protected final short a(class_48 paramclass_48)
  {
    a8(paramclass_48, this.field_241);
    a8(this.jdField_field_244_of_type_Class_48, this.field_242);
    a8(this.jdField_field_240_of_type_Class_48, this.field_243);
    a9(this.field_243, this.field_242);
    float f1;
    if (this.field_241.field_476 < this.field_242.field_476 / 2)
    {
      paramclass_48 = this;
      this.jdField_field_240_of_type_JavaxVecmathVector3f.set(paramclass_48.field_241.field_475 + 0.5F, 0.0F, paramclass_48.field_241.field_477 + 0.5F);
      paramclass_48.jdField_field_244_of_type_JavaxVecmathVector3f.set(paramclass_48.field_242.field_475 / 2.0F, 0.0F, paramclass_48.field_242.field_477 / 2.0F);
      paramclass_48.jdField_field_240_of_type_JavaxVecmathVector3f.sub(paramclass_48.jdField_field_244_of_type_JavaxVecmathVector3f);
      if ((f1 = paramclass_48.jdField_field_240_of_type_JavaxVecmathVector3f.length()) < paramclass_48.field_242.field_475 / 2.0F)
      {
        int i = paramclass_48.field_242.field_476 / 2 - 4;
        if ((!jdField_field_240_of_type_Boolean) && (i <= 0)) {
          throw new AssertionError();
        }
        if (paramclass_48.field_241.field_476 == i)
        {
          if ((paramclass_48.field_241.field_475 > 0) && (paramclass_48.field_241.field_477 > 0) && (paramclass_48.field_241.field_475 % 5 == 0) && (paramclass_48.field_241.field_477 % 5 == 0)) {
            return 79;
          }
          return 5;
        }
        if (paramclass_48.field_241.field_476 < i)
        {
          int j = paramclass_48.field_241.field_476 - i;
          short s = 5;
          if (j % 2 == 0) {
            s = 75;
          }
          if ((paramclass_48.field_241.field_475 < paramclass_48.field_242.field_475 - j) && (paramclass_48.field_241.field_475 > j) && (paramclass_48.field_241.field_477 < paramclass_48.field_242.field_477 - j) && (paramclass_48.field_241.field_477 > j))
          {
            if (paramclass_48.field_241.field_476 == 0) {
              for (i = 0; i < paramclass_48.field_242.field_475 / 8; i++)
              {
                float f2 = i * 8.0F;
                if ((f1 > f2) && (f1 < f2 + 1.5F))
                {
                  s = 76;
                  break;
                }
              }
            }
            return s;
          }
        }
        if (f1 + 1.5F >= paramclass_48.field_242.field_475 / 2.0F)
        {
          if (paramclass_48.field_241.field_476 == i + 2) {
            return 63;
          }
          if (paramclass_48.field_241.field_476 == i + 3) {
            return 76;
          }
          return 5;
        }
      }
      return 32767;
    }
    paramclass_48 = this;
    this.jdField_field_240_of_type_JavaxVecmathVector3f.set(paramclass_48.field_241.field_475 + 0.5F, paramclass_48.field_241.field_476 * 1.3F, paramclass_48.field_241.field_477 + 0.5F);
    paramclass_48.jdField_field_244_of_type_JavaxVecmathVector3f.set(paramclass_48.field_242.field_475 / 2.0F, paramclass_48.field_242.field_476 / 2.0F, paramclass_48.field_242.field_477 / 2.0F);
    paramclass_48.jdField_field_240_of_type_JavaxVecmathVector3f.sub(paramclass_48.jdField_field_244_of_type_JavaxVecmathVector3f);
    if (((f1 = paramclass_48.jdField_field_240_of_type_JavaxVecmathVector3f.length()) < paramclass_48.field_242.field_475 / 2.0F + 0.5F) && (paramclass_48.field_241.field_476 == (int)(paramclass_48.field_242.field_476 - 4.0F - 1.0F)) && (paramclass_48.field_241.field_475 > 0) && (paramclass_48.field_241.field_477 > 0) && (paramclass_48.field_241.field_475 % 10 == 0) && (paramclass_48.field_241.field_477 % 10 == 0)) {
      return 55;
    }
    if ((f1 < paramclass_48.field_242.field_475 / 2.0F + 0.5F) && ((f1 > paramclass_48.field_242.field_475 / 2.0F - 1.5F) || (paramclass_48.field_241.field_476 >= paramclass_48.field_242.field_476 - 4.0F) || (paramclass_48.field_241.field_477 == paramclass_48.field_242.field_477 - 1)))
    {
      if (paramclass_48.field_241.field_476 < paramclass_48.field_242.field_476 - 4.0F) {
        return 63;
      }
      return 5;
    }
    return 0;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1107
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */