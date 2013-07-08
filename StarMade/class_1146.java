import java.util.Random;
import org.schema.common.FastMath;

public final class class_1146
  extends class_1138
{
  private short field_253;
  private short jdField_field_254_of_type_Short = 77;
  private short field_255;
  private int jdField_field_254_of_type_Int = 4;
  private Random jdField_field_254_of_type_JavaUtilRandom;
  
  public class_1146(short paramShort)
  {
    this.jdField_field_253_of_type_Short = paramShort;
    this.field_255 = 286;
    this.jdField_field_254_of_type_JavaUtilRandom = new Random();
    this.jdField_field_253_of_type_Int = 4;
  }
  
  private void a2(long paramLong, int paramInt1, int paramInt2, short[] paramArrayOfShort, double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2, float paramFloat3, int paramInt3, int paramInt4, double paramDouble4, short paramShort)
  {
    ;
    double d1 = (paramInt1 << 4) + 8;
    double d2 = (paramInt2 << 4) + 8;
    float f1 = 0.0F;
    float f2 = 0.0F;
    this.jdField_field_254_of_type_JavaUtilRandom.setSeed(paramLong);
    if (paramInt4 <= 0) {
      paramInt4 = (paramLong = (this.jdField_field_253_of_type_Int << 4) - 16) - this.jdField_field_254_of_type_JavaUtilRandom.nextInt(paramLong / 4);
    }
    paramLong = 0;
    if (paramInt3 == -1)
    {
      paramInt3 = paramInt4 / 2;
      paramLong = 1;
    }
    // INTERNAL ERROR //

/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1146
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */