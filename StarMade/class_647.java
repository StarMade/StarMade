public final class class_647
{
  Object field_921;
  public short field_921;
  public int field_921;
  
  public final void a(int paramInt)
  {
    if ((this.jdField_field_921_of_type_JavaLangObject instanceof Integer)) {
      this.jdField_field_921_of_type_JavaLangObject = Integer.valueOf(((Integer)this.jdField_field_921_of_type_JavaLangObject).intValue() + paramInt);
    }
  }
  
  public final int a1()
  {
    if ((this.jdField_field_921_of_type_JavaLangObject instanceof Integer)) {
      return ((Integer)this.jdField_field_921_of_type_JavaLangObject).intValue();
    }
    return 1;
  }
  
  public class_647() {}
  
  public class_647(class_647 paramclass_647, int paramInt)
  {
    this.jdField_field_921_of_type_JavaLangObject = paramclass_647.jdField_field_921_of_type_JavaLangObject;
    this.jdField_field_921_of_type_Short = paramclass_647.jdField_field_921_of_type_Short;
    this.jdField_field_921_of_type_Int = paramInt;
  }
  
  public final String toString()
  {
    return "[slot " + this.jdField_field_921_of_type_Int + "; t " + this.jdField_field_921_of_type_Short + "; c " + this.jdField_field_921_of_type_JavaLangObject + "]";
  }
  
  public final void b(int paramInt)
  {
    this.jdField_field_921_of_type_JavaLangObject = Integer.valueOf(paramInt);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_647
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */