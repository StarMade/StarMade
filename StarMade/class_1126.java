import java.util.Random;

public final class class_1126
  extends class_1186
{
  public class_1126(long paramLong)
  {
    super(paramLong);
  }
  
  protected final short a6(float paramFloat)
  {
    short s = 73;
    if ((paramFloat < 3.4F) && (this.jdField_field_248_of_type_JavaUtilRandom.nextFloat() > 0.7F)) {
      s = 74;
    } else if ((paramFloat > 9.1D) && (this.jdField_field_248_of_type_JavaUtilRandom.nextFloat() > 0.68F)) {
      s = 80;
    }
    return s;
  }
  
  public final void a4()
  {
    this.jdField_field_248_of_type_ArrayOfClass_1123 = new class_1123[5];
    this.jdField_field_248_of_type_ArrayOfClass_1123[0] = new class_1078(72, 6, 73);
    this.jdField_field_248_of_type_ArrayOfClass_1123[1] = new class_1078(130, 6, 73);
    this.jdField_field_248_of_type_ArrayOfClass_1123[2] = new class_1078(133, 6, 74);
    this.jdField_field_248_of_type_ArrayOfClass_1123[3] = new class_1078(137, 9, 73);
    this.jdField_field_248_of_type_ArrayOfClass_1123[4] = new class_1078(128, 6, 73);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1126
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */