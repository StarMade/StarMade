public final class class_1250
  extends class_1163
{
  private static final long serialVersionUID = 1L;
  private long jdField_field_128_of_type_Long;
  private int jdField_field_128_of_type_Int = 60;
  
  public class_1250(class_981 paramclass_981)
  {
    super(paramclass_981);
  }
  
  public final boolean c()
  {
    this.jdField_field_128_of_type_Long = System.currentTimeMillis();
    return false;
  }
  
  public final boolean b()
  {
    return false;
  }
  
  public final boolean d()
  {
    if (System.currentTimeMillis() - this.jdField_field_128_of_type_Long > this.jdField_field_128_of_type_Int * 1000) {
      a12(new class_1210());
    }
    if ((a8() instanceof class_1063)) {
      ((class_1063)a8()).c();
    }
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1250
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */