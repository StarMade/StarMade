import java.util.Random;
import org.schema.game.common.data.world.Universe;

public final class class_1121
  extends class_1144
{
  public int[] field_79;
  public double field_79;
  public double field_251;
  public double field_252;
  
  public class_1121()
  {
    this(Universe.getRandom());
  }
  
  public class_1121(Random paramRandom)
  {
    this.jdField_field_79_of_type_ArrayOfInt = new int[512];
    this.jdField_field_79_of_type_Double = (paramRandom.nextDouble() * 256.0D);
    this.field_251 = (paramRandom.nextDouble() * 256.0D);
    this.field_252 = (paramRandom.nextDouble() * 256.0D);
    for (int i = 0; i < 256; i++) {
      this.jdField_field_79_of_type_ArrayOfInt[i] = i;
    }
    for (i = 0; i < 256; i++)
    {
      int j = paramRandom.nextInt(256 - i) + i;
      int k = this.jdField_field_79_of_type_ArrayOfInt[i];
      this.jdField_field_79_of_type_ArrayOfInt[i] = this.jdField_field_79_of_type_ArrayOfInt[j];
      this.jdField_field_79_of_type_ArrayOfInt[j] = k;
      this.jdField_field_79_of_type_ArrayOfInt[(i + 256)] = this.jdField_field_79_of_type_ArrayOfInt[i];
    }
  }
  
  public static final double a2(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3)
  {
    switch (paramInt & 0xF)
    {
    case 0: 
      return paramDouble1 + paramDouble2;
    case 1: 
      return -paramDouble1 + paramDouble2;
    case 2: 
      return paramDouble1 - paramDouble2;
    case 3: 
      return -paramDouble1 - paramDouble2;
    case 4: 
      return paramDouble1 + paramDouble1;
    case 5: 
      return -paramDouble1 + paramDouble1;
    case 6: 
      return paramDouble1 - paramDouble1;
    case 7: 
      return -paramDouble1 - paramDouble1;
    case 8: 
      return paramDouble2 + paramDouble1;
    case 9: 
      return -paramDouble2 + paramDouble1;
    case 10: 
      return paramDouble2 - paramDouble1;
    case 11: 
      return -paramDouble2 - paramDouble1;
    case 12: 
      return paramDouble2 + paramDouble3;
    case 13: 
      return -paramDouble2 + paramDouble1;
    case 14: 
      return paramDouble2 - paramDouble1;
    case 15: 
      return -paramDouble2 - paramDouble3;
    }
    return 0.0D;
  }
  
  public static double a3(double paramDouble1, double paramDouble2, double paramDouble3)
  {
    return paramDouble2 + paramDouble1 * (paramDouble3 - paramDouble2);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1121
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */