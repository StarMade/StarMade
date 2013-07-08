import java.util.Random;

public final class class_1089
  extends class_1186
{
  public class_1089(long paramLong)
  {
    super(paramLong);
  }
  
  protected final short a6(float paramFloat)
  {
    short s = 92;
    if ((paramFloat < 3.4F) && (this.jdField_field_248_of_type_JavaUtilRandom.nextFloat() > 0.3F)) {
      s = 91;
    } else if ((paramFloat > 9.1D) && (this.jdField_field_248_of_type_JavaUtilRandom.nextFloat() > 0.68F)) {
      s = 80;
    }
    return s;
  }
  
  public final void a4()
  {
    this.jdField_field_248_of_type_ArrayOfClass_1123 = new class_1123[3];
    this.jdField_field_248_of_type_ArrayOfClass_1123[0] = new class_1078(208, 6, 92);
    this.jdField_field_248_of_type_ArrayOfClass_1123[1] = new class_1078(207, 6, 92);
    this.jdField_field_248_of_type_ArrayOfClass_1123[2] = new class_1078(210, 9, 92);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1089
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */