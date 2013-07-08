import java.util.Random;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.SegmentData;

public final class class_1125
  extends class_1123
{
  private short jdField_field_247_of_type_Short;
  private short[] jdField_field_247_of_type_ArrayOfShort;
  
  public class_1125(short paramShort, short... paramVarArgs)
  {
    this(Integer.valueOf(4), paramShort, paramVarArgs);
  }
  
  public class_1125(Integer paramInteger, short paramShort, short... paramVarArgs)
  {
    this.jdField_field_247_of_type_ArrayOfShort = paramVarArgs;
    this.jdField_field_247_of_type_Short = paramShort;
    paramInteger.intValue();
  }
  
  public final boolean a(SegmentData paramSegmentData, Random paramRandom, int paramInt1, int paramInt2, int paramInt3)
  {
    int i = Math.abs(paramSegmentData.getSegment().field_34.field_476);
    int j = 0;
    while ((((j = paramSegmentData.getType((byte)(paramInt1 % 16), (byte)(paramInt2 % 16), (byte)(paramInt3 % 16))) == 0) || (j == 85)) && (paramInt2 > 0) && (paramInt2 > i)) {
      paramInt2--;
    }
    if (paramInt2 == i) {
      return false;
    }
    for (j = 0; j < 4; j++)
    {
      int k = paramInt1 + paramRandom.nextInt(8) - paramRandom.nextInt(8);
      int m = paramInt2 + paramRandom.nextInt(4) - paramRandom.nextInt(4);
      int n = paramInt3 + paramRandom.nextInt(8) - paramRandom.nextInt(8);
      if (m > i)
      {
        int i1 = paramSegmentData.getType((byte)(k % 16), (byte)(m % 16), (byte)(n % 16));
        byte b;
        if ((b = (byte)(m % 16 - 1)) >= 0)
        {
          int i2 = paramSegmentData.getType((byte)(k % 16), b, (byte)(n % 16));
          if ((i1 == 0) && (i2 > 0))
          {
            class_1125 localclass_1125 = this;
            for (int i3 = 0; i3 < localclass_1125.jdField_field_247_of_type_ArrayOfShort.length; i3++) {}
            if ((i2 == localclass_1125.jdField_field_247_of_type_ArrayOfShort[i3] ? 1 : 0) != 0) {
              paramSegmentData.setInfoElement((byte)(k % 16), (byte)(m % 16), (byte)(n % 16), this.jdField_field_247_of_type_Short, false);
            }
          }
        }
      }
    }
    return true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1125
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */