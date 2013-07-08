import java.util.Random;

public final class class_1128
  extends class_1186
{
  public class_1128(long paramLong)
  {
    super(paramLong);
  }
  
  protected final short a6(float paramFloat)
  {
    short s = 80;
    if ((paramFloat < 5.1D) && (this.jdField_field_248_of_type_JavaUtilRandom.nextFloat() > 0.1F)) {
      s = 73;
    }
    return s;
  }
  
  public final void a4()
  {
    this.jdField_field_248_of_type_ArrayOfClass_1123 = new class_1123[3];
    this.jdField_field_248_of_type_ArrayOfClass_1123[0] = new class_1078(133, 6, 73);
    this.jdField_field_248_of_type_ArrayOfClass_1123[1] = new class_1078(136, 6, 73);
    this.jdField_field_248_of_type_ArrayOfClass_1123[2] = new class_1078(129, 8, 73);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1128
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */