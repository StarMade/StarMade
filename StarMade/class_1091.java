import java.util.Random;

public final class class_1091
  extends class_1186
{
  public class_1091(long paramLong)
  {
    super(paramLong);
  }
  
  protected final short a6(float paramFloat)
  {
    short s = 139;
    if ((paramFloat < 3.4F) && (this.jdField_field_248_of_type_JavaUtilRandom.nextFloat() > 0.3F)) {
      s = 140;
    } else if ((paramFloat > 9.1D) && (this.jdField_field_248_of_type_JavaUtilRandom.nextFloat() > 0.68F)) {
      s = 80;
    }
    return s;
  }
  
  public final void a4()
  {
    this.jdField_field_248_of_type_ArrayOfClass_1123 = new class_1123[3];
    this.jdField_field_248_of_type_ArrayOfClass_1123[0] = new class_1078(137, 6, 139);
    this.jdField_field_248_of_type_ArrayOfClass_1123[1] = new class_1078(132, 6, 140);
    this.jdField_field_248_of_type_ArrayOfClass_1123[2] = new class_1078(130, 8, 139);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1091
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */