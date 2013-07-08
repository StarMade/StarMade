import java.util.Random;
import org.schema.game.common.data.world.SegmentData;

public final class class_1076
  extends class_1123
{
  public class_1076()
  {
    this((byte)0);
  }
  
  private class_1076(byte paramByte) {}
  
  public final boolean a(SegmentData paramSegmentData, Random paramRandom, int paramInt1, int paramInt2, int paramInt3)
  {
    int i = paramRandom.nextInt(3) + 4;
    int j = 1;
    if ((paramInt2 <= 0) || (paramInt2 + i + 1 > 256)) {
      return false;
    }
    int m;
    int n;
    int i2;
    for (int k = paramInt2; k <= paramInt2 + 1 + i; k++)
    {
      m = 1;
      if (k == paramInt2) {
        m = 0;
      }
      if (k >= paramInt2 + 1 + i - 2) {
        m = 2;
      }
      for (n = paramInt1 - m; (n <= paramInt1 + m) && (j != 0); n++) {
        for (i1 = paramInt3 - m; (i1 <= paramInt3 + m) && (j != 0); i1++) {
          if ((k >= 0) && (k < 64))
          {
            if (((i2 = paramSegmentData.getType(a2(n), a2(k), a2(i1))) != 0) && (i2 != 85) && (i2 != 82) && (i2 != 87) && (i2 != 84)) {
              j = 0;
            }
          }
          else {
            j = 0;
          }
        }
      }
    }
    if (j == 0) {
      return false;
    }
    if ((((k = paramSegmentData.getType(a2(paramInt1), a2(paramInt2 - 1), a2(paramInt3))) != 82) && (k != 87)) || (paramInt2 >= 256 - i - 1)) {
      return false;
    }
    paramSegmentData.setInfoElementUnsynched(a2(paramInt1), a2(paramInt2 - 1), a2(paramInt3), (short)87, false);
    for (int i1 = paramInt2 - 3 + i; i1 <= paramInt2 + i; i1++)
    {
      i2 = i1 - (paramInt2 + i);
      j = 1 - i2 / 2;
      for (k = paramInt1 - j; k <= paramInt1 + j; k++)
      {
        m = k - paramInt1;
        for (n = paramInt3 - j; n <= paramInt3 + j; n++)
        {
          int i3 = n - paramInt3;
          if ((Math.abs(m) != j) || (Math.abs(i3) != j) || ((paramRandom.nextInt(2) != 0) && (i2 != 0))) {
            paramSegmentData.setInfoElementUnsynched(a2(k), a2(i1), a2(n), (short)85, false);
          }
        }
      }
    }
    for (i1 = 0; i1 < i; i1++) {
      if (((i2 = paramSegmentData.getType(a2(paramInt1), a2(paramInt2 + i1), a2(paramInt3))) == 0) || (i2 == 85)) {
        paramSegmentData.setInfoElementUnsynched(a2(paramInt1), a2(paramInt2 + i1), a2(paramInt3), (short)84, false);
      }
    }
    return true;
  }
  
  public final int a1(Random paramRandom)
  {
    return 4 + paramRandom.nextInt(8);
  }
  
  public final int b(Random paramRandom)
  {
    return 4 + paramRandom.nextInt(8);
  }
  
  private static byte a2(int paramInt)
  {
    return (byte)(Math.abs(paramInt) % 16);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1076
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */