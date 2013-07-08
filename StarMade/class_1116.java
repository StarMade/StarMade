import java.util.Random;
import org.schema.game.common.data.world.SegmentData;
import org.schema.game.common.data.world.Universe;

public final class class_1116
  extends class_1123
{
  private class_1083 field_247;
  
  private class_1116(class_1083 paramclass_1083)
  {
    this.field_247 = paramclass_1083;
  }
  
  public class_1116(class_1083 paramclass_1083, byte paramByte)
  {
    this(paramclass_1083);
  }
  
  public final boolean a(SegmentData paramSegmentData, Random paramRandom, int paramInt1, int paramInt2, int paramInt3)
  {
    paramRandom = paramRandom.nextInt(4) + 4;
    Random localRandom = 1;
    if ((paramInt2 <= 0) || (paramInt2 + paramRandom + 1 > 256)) {
      return false;
    }
    int j;
    int k;
    for (int i = paramInt2; i <= paramInt2 + 1 + paramRandom; i++)
    {
      j = 1;
      if (i == paramInt2) {
        j = 0;
      }
      if (i >= paramInt2 + 1 + paramRandom - 2) {
        j = 2;
      }
      for (k = paramInt1 - j; (k <= paramInt1 + j) && (localRandom != 0); k++) {
        for (m = paramInt3 - j; (m <= paramInt3 + j) && (localRandom != 0); m++) {
          if ((i >= 0) && (i < 64))
          {
            if (((n = paramSegmentData.getType(a2(k), a2(i), a2(m))) != 0) && (n != 89) && (n != this.field_247.c()) && (n != this.field_247.a()) && (n != 84)) {
              localRandom = 0;
            }
          }
          else {
            localRandom = 0;
          }
        }
      }
    }
    if (localRandom == 0) {
      return false;
    }
    if ((((i = paramSegmentData.getType(a2(paramInt1), a2(paramInt2 - 1), a2(paramInt3))) != 82) && (i != this.field_247.a())) || (paramInt2 >= 256 - paramRandom - 1)) {
      return false;
    }
    paramSegmentData.setInfoElementUnsynched(a2(paramInt1), a2(paramInt2 - 1), a2(paramInt3), this.field_247.a(), false);
    int m = 0;
    int n = 0;
    for (localRandom = 0; localRandom < paramRandom; localRandom++) {
      if (paramSegmentData.getType(a2(paramInt1), a2(paramInt2 + localRandom), a2(paramInt3)) == 0)
      {
        paramSegmentData.setInfoElementUnsynched(a2(paramInt1), a2(paramInt2 + localRandom), a2(paramInt3), (short)89, false);
        if ((localRandom > 0) && (localRandom < paramRandom - 1) && (Universe.getRandom().nextFloat() > 0.7F)) {
          if (Universe.getRandom().nextFloat() > 0.5D)
          {
            i = a2(paramInt1);
            j = a2(paramInt2 + localRandom);
            k = a2(paramInt3 + (Universe.getRandom().nextFloat() > 0.5D ? 1 : -1));
            paramSegmentData.setInfoElementUnsynched(i, j, k, (short)89, false);
            if ((n == 0) && (Universe.getRandom().nextFloat() > 0.8D))
            {
              n = 1;
              if (paramInt3 < k) {
                k = (byte)(k + 1);
              } else {
                k = (byte)(k - 1);
              }
              paramSegmentData.setInfoElementUnsynched(i, j, k, (short)89, false);
              paramSegmentData.setInfoElementUnsynched(i, (byte)(j + 1), k, (short)89, false);
            }
          }
          else
          {
            i = a2(paramInt1 + (Universe.getRandom().nextFloat() > 0.5D ? 1 : -1));
            j = a2(paramInt2 + localRandom);
            k = a2(paramInt3);
            paramSegmentData.setInfoElementUnsynched(i, j, k, (short)89, false);
            if ((m == 0) && (Universe.getRandom().nextFloat() > 0.8D))
            {
              m = 1;
              if (paramInt1 < i) {
                i = (byte)(i + 1);
              } else {
                i = (byte)(i - 1);
              }
              paramSegmentData.setInfoElementUnsynched(i, j, k, (short)89, false);
              paramSegmentData.setInfoElementUnsynched(i, (byte)(j + 1), k, (short)89, false);
            }
          }
        }
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
 * Qualified Name:     class_1116
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */