import javax.vecmath.Vector3f;

public final class class_1103
  extends class_1068
{
  private class_48 field_241 = new class_48();
  private class_48 field_242 = new class_48();
  private class_48 field_243 = new class_48();
  private class_48 field_245 = new class_48();
  private boolean field_240;
  
  public class_1103(boolean paramBoolean, class_1068[] paramArrayOfclass_1068, class_48 paramclass_481, class_48 paramclass_482, int paramInt)
  {
    super(paramArrayOfclass_1068, paramclass_481, paramclass_482, paramInt, 0);
    new class_48();
    new class_48();
    new Vector3f();
    new Vector3f();
    this.jdField_field_240_of_type_Boolean = paramBoolean;
  }
  
  protected final short a(class_48 paramclass_48)
  {
    a8(paramclass_48, this.field_241);
    a8(this.field_244, this.field_242);
    a8(this.jdField_field_240_of_type_Class_48, this.field_243);
    a9(this.field_243, this.field_242);
    this.field_245.b((this.field_242.field_475 - this.field_243.field_475) / 2, this.field_242.field_476, (this.field_242.field_477 - this.field_243.field_477) / 2);
    paramclass_48 = this.field_242.field_476 - this.field_241.field_476 - 1;
    if ((this.field_245.equals(this.field_241)) || ((this.field_241.field_475 >= this.field_245.field_475 - paramclass_48) && (this.field_241.field_475 <= this.field_245.field_475 + paramclass_48) && (this.field_241.field_477 >= this.field_245.field_477 - paramclass_48) && (this.field_241.field_477 <= this.field_245.field_477 + paramclass_48)))
    {
      if ((paramclass_48 > (this.field_242.field_476 - this.field_243.field_476) / 4) && (this.field_241.field_476 > 1) && (this.field_241.field_475 >= this.field_245.field_475 - paramclass_48 + 2) && (this.field_241.field_475 <= this.field_245.field_475 + paramclass_48 - 2) && (this.field_241.field_477 >= this.field_245.field_477 - paramclass_48 + 2) && (this.field_241.field_477 <= this.field_245.field_477 + paramclass_48 - 2))
      {
        if (((this.field_242.field_475 - this.field_243.field_475) / 4 == this.field_241.field_475) && ((this.field_242.field_477 - this.field_243.field_477) / 4 == this.field_241.field_477))
        {
          if (this.field_241.field_476 < (this.field_242.field_476 - this.field_243.field_476) / 8) {
            return 2;
          }
          if (this.field_241.field_476 == (this.field_242.field_476 - this.field_243.field_476) / 8) {
            return 55;
          }
        }
        if (((this.field_242.field_475 - this.field_243.field_475) / 4 == this.field_241.field_475) && ((this.field_242.field_477 - this.field_243.field_477) / 4 * 3 == this.field_241.field_477))
        {
          if (this.field_241.field_476 < (this.field_242.field_476 - this.field_243.field_476) / 8) {
            return 2;
          }
          if (this.field_241.field_476 == (this.field_242.field_476 - this.field_243.field_476) / 8) {
            return 55;
          }
        }
        if (((this.field_242.field_475 - this.field_243.field_475) / 4 * 3 == this.field_241.field_475) && ((this.field_242.field_477 - this.field_243.field_477) / 4 == this.field_241.field_477))
        {
          if (this.field_241.field_476 < (this.field_242.field_476 - this.field_243.field_476) / 8) {
            return 2;
          }
          if (this.field_241.field_476 == (this.field_242.field_476 - this.field_243.field_476) / 8) {
            return 55;
          }
        }
        if (((this.field_242.field_475 - this.field_243.field_475) / 4 * 3 == this.field_241.field_475) && ((this.field_242.field_477 - this.field_243.field_477) / 4 * 3 == this.field_241.field_477))
        {
          if (this.field_241.field_476 < (this.field_242.field_476 - this.field_243.field_476) / 8) {
            return 2;
          }
          if (this.field_241.field_476 == (this.field_242.field_476 - this.field_243.field_476) / 8) {
            return 55;
          }
        }
        return 0;
      }
      return 79;
    }
    return 32767;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1103
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */