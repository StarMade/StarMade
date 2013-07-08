public final class class_401
  extends class_300
{
  private static final long serialVersionUID = 9159389806588076558L;
  private boolean field_158;
  
  public class_401(class_981 paramclass_981, class_371 paramclass_371, String paramString, int paramInt)
  {
    super(paramclass_981, paramclass_371, paramString, paramInt);
  }
  
  protected final boolean a()
  {
    this.field_128.a4().a5();
    class_460.a2();
    if ((this.field_158 != this.jdField_field_128_of_type_Boolean) && (this.jdField_field_128_of_type_Boolean)) {
      this.jdField_field_128_of_type_Long = System.currentTimeMillis();
    }
    this.field_158 = this.jdField_field_128_of_type_Boolean;
    if (!this.jdField_field_128_of_type_Boolean)
    {
      this.field_128.a4().a5();
      class_460.a2();
    }
    else
    {
      return (!this.field_128.a4().a5().b1()) && (System.currentTimeMillis() - this.jdField_field_128_of_type_Long > this.field_128);
    }
    return false;
  }
  
  public final String a1()
  {
    if (this.jdField_field_128_of_type_Boolean)
    {
      this.field_128.a4().a5();
      class_460.a2();
      return super.a1() + "(Tutorial will resume in " + (this.field_128 - (System.currentTimeMillis() - this.jdField_field_128_of_type_Long)) / 1000L + " sec\nor press 'k')";
    }
    return super.a1();
  }
  
  public final boolean c()
  {
    this.field_158 = false;
    return super.c();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_401
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */