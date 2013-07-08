public final class class_1060
  extends class_1068
{
  private class_48 field_241 = new class_48();
  private class_48 field_242 = new class_48();
  private class_48 field_243 = new class_48();
  private int field_244;
  private class_48 field_245 = new class_48();
  private class_48 field_246;
  
  public class_1060(class_1068[] paramArrayOfclass_1068, class_48 paramclass_481, class_48 paramclass_482, class_48 paramclass_483)
  {
    super(paramArrayOfclass_1068, paramclass_481, paramclass_482, 5, 0);
    this.jdField_field_244_of_type_Int = 3;
    this.field_246 = paramclass_483;
  }
  
  protected final short a(class_48 paramclass_48)
  {
    a8(paramclass_48, this.field_241);
    a8(this.jdField_field_244_of_type_Class_48, this.field_242);
    a8(this.field_240, this.field_243);
    a9(this.field_243, this.field_242);
    this.field_245.b1(this.field_246);
    class_48 localclass_48 = org.schema.game.common.data.element.Element.DIRECTIONSi[this.jdField_field_244_of_type_Int];
    this.field_245.a1(localclass_48);
    if (((localclass_48.field_475 > 0) && (paramclass_48.field_475 > this.field_246.field_475)) || ((localclass_48.field_475 < 0) && (paramclass_48.field_475 < this.field_246.field_475)) || ((localclass_48.field_476 > 0) && (paramclass_48.field_476 > this.field_246.field_476)) || ((localclass_48.field_476 < 0) && (paramclass_48.field_476 < this.field_246.field_476)) || ((localclass_48.field_477 > 0) && (paramclass_48.field_477 > this.field_246.field_477)) || ((localclass_48.field_477 < 0) && (paramclass_48.field_477 < this.field_246.field_477))) {
      return 32767;
    }
    this.field_245.b1(this.field_246);
    this.field_245.c1(localclass_48);
    if ((this.field_241.field_475 == (this.field_242.field_475 - this.field_243.field_475) / 2) || (this.field_241.field_476 == (this.field_242.field_476 - this.field_243.field_476) / 2))
    {
      if ((this.field_241.field_477 > this.field_242.field_477 - 3) && (this.field_241.field_477 < this.field_242.field_477 - 1)) {
        return 282;
      }
      if (this.field_241.field_477 < this.field_242.field_477 - 1) {
        return 286;
      }
    }
    if ((this.field_241.field_477 % 2 == 0) && (this.field_241.field_477 < this.field_242.field_477 - 4)) {
      return 75;
    }
    return 32767;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1060
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */