import java.util.Random;

public final class class_1130
  extends class_1186
{
  public class_1130(long paramLong)
  {
    super(paramLong);
  }
  
  protected final short a6(float paramFloat)
  {
    short s = 73;
    if ((paramFloat < 3.4F) && (this.jdField_field_248_of_type_JavaUtilRandom.nextFloat() > 0.3F)) {
      s = 64;
    } else if ((paramFloat > 9.1D) && (this.jdField_field_248_of_type_JavaUtilRandom.nextFloat() > 0.68F)) {
      s = 275;
    }
    return s;
  }
  
  public final void a4()
  {
    this.jdField_field_248_of_type_ArrayOfClass_1123 = new class_1123[3];
    this.jdField_field_248_of_type_ArrayOfClass_1123[0] = new class_1078(209, 6, 73);
    this.jdField_field_248_of_type_ArrayOfClass_1123[1] = new class_1078(132, 6, 73);
    this.jdField_field_248_of_type_ArrayOfClass_1123[2] = new class_1078(208, 8, 73);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1130
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */