import java.util.Random;
import org.schema.common.FastMath;
import org.schema.game.common.data.world.SegmentData;

public class class_1114
  extends class_1123
{
  protected short field_247;
  private int field_247;
  private final short field_250;
  
  public class_1114(short paramShort1, int paramInt, short paramShort2)
  {
    this.jdField_field_247_of_type_Short = paramShort1;
    this.jdField_field_247_of_type_Int = paramInt;
    this.field_250 = paramShort2;
  }
  
  public final boolean a(SegmentData paramSegmentData, Random paramRandom, int paramInt1, int paramInt2, int paramInt3)
  {
    float f = paramRandom.nextFloat() * 3.141593F;
    double d1 = paramInt1 + 8 + FastMath.k(f) * this.jdField_field_247_of_type_Int / 8.0F;
    double d2 = paramInt1 + 8 - FastMath.k(f) * this.jdField_field_247_of_type_Int / 8.0F;
    double d3 = paramInt3 + 8 + FastMath.e(f) * this.jdField_field_247_of_type_Int / 8.0F;
    double d4 = paramInt3 + 8 - FastMath.e(f) * this.jdField_field_247_of_type_Int / 8.0F;
    double d5 = paramInt2 + paramRandom.nextInt(3) - 2;
    double d6 = paramInt2 + paramRandom.nextInt(3) - 2;
    for (paramInt1 = 0; paramInt1 <= this.jdField_field_247_of_type_Int; paramInt1++)
    {
      double d7 = d1 + (d2 - d1) * paramInt1 / this.jdField_field_247_of_type_Int;
      double d8 = d5 + (d6 - d5) * paramInt1 / this.jdField_field_247_of_type_Int;
      double d9 = d3 + (d4 - d3) * paramInt1 / this.jdField_field_247_of_type_Int;
      double d10 = paramRandom.nextDouble() * this.jdField_field_247_of_type_Int / 16.0D;
      double d11 = (FastMath.k(paramInt1 * 3.141593F / this.jdField_field_247_of_type_Int) + 1.0F) * d10 + 1.0D;
      double d12 = (FastMath.k(paramInt1 * 3.141593F / this.jdField_field_247_of_type_Int) + 1.0F) * d10 + 1.0D;
      paramInt2 = (int)(d7 - d11 / 2.0D);
      paramInt3 = (int)(d8 - d12 / 2.0D);
      int i = (int)(d9 - d11 / 2.0D);
      int j = (int)(d7 + d11 / 2.0D);
      int k = (int)(d8 + d12 / 2.0D);
      int m = (int)(d9 + d11 / 2.0D);
      while (paramInt2 <= j)
      {
        double tmp356_355 = ((paramInt2 + 0.5D - d7) / (d11 / 2.0D));
        double d13;
        if (tmp356_355 * (d13 = tmp356_355) < 1.0D) {
          for (int n = paramInt3; n <= k; n++)
          {
            double d14 = (n + 0.5D - d8) / (d12 / 2.0D);
            if (d13 * d13 + d14 * d14 < 1.0D) {
              for (int i1 = i; i1 <= m; i1++)
              {
                double d15 = (i1 + 0.5D - d9) / (tmp356_355 / 2.0D);
                if ((d13 * d13 + d14 * d14 + d15 * d15 < 1.0D) && (paramSegmentData.getType((byte)Math.abs(paramInt2 % 16), (byte)Math.abs(n % 16), (byte)Math.abs(i1 % 16)) == this.field_250)) {
                  a3(paramSegmentData, paramInt2, n, i1, paramRandom);
                }
              }
            }
          }
        }
        paramInt2++;
      }
    }
    return true;
  }
  
  public void a3(SegmentData paramSegmentData, int paramInt1, int paramInt2, int paramInt3, Random paramRandom)
  {
    paramSegmentData.setInfoElementUnsynched((byte)Math.abs(paramInt1 % 16), (byte)Math.abs(paramInt2 % 16), (byte)Math.abs(paramInt3 % 16), this.jdField_field_247_of_type_Short, false);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1114
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */