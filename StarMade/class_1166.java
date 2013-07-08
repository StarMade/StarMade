import it.unimi.dsi.fastutil.shorts.ShortArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import org.schema.common.FastMath;
import org.schema.common.util.ByteUtil;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.SegmentData;

public final class class_1166
  extends class_1177
{
  private class_1068[] jdField_field_248_of_type_ArrayOfClass_1068;
  private class_1138 jdField_field_248_of_type_Class_1138;
  private short jdField_field_248_of_type_Short = 69;
  private Random jdField_field_248_of_type_JavaUtilRandom;
  private static short[] jdField_field_248_of_type_ArrayOfShort = new short[16384];
  private class_48 jdField_field_248_of_type_Class_48 = new class_48();
  private ShortArrayList jdField_field_248_of_type_ItUnimiDsiFastutilShortsShortArrayList = new ShortArrayList(6);
  private class_48 field_257 = new class_48(-5, 0, -5);
  private class_48 field_258 = new class_48(5, 60, 5);
  
  public class_1166(long paramLong)
  {
    if (this.jdField_field_248_of_type_JavaUtilRandom == null) {
      this.jdField_field_248_of_type_JavaUtilRandom = new Random(paramLong);
    }
    if (this.jdField_field_248_of_type_Class_1138 == null) {
      this.jdField_field_248_of_type_Class_1138 = new class_1146(this.jdField_field_248_of_type_Short);
    }
    this.jdField_field_248_of_type_ArrayOfClass_1068 = new class_1068[38];
    class_48 localclass_481 = new class_48(0, 1, 0);
    paramLong = this;
    class_48 localclass_482;
    (localclass_482 = new class_48(-5, 0, -5)).a1(localclass_481);
    class_48 localclass_483;
    (localclass_483 = new class_48(5, 52, 5)).a1(localclass_481);
    this.jdField_field_248_of_type_ArrayOfClass_1068[0] = new class_1062(paramLong.jdField_field_248_of_type_ArrayOfClass_1068, localclass_482, localclass_483);
    localclass_481 = new class_48(0, 1, 0);
    paramLong = this;
    (localclass_482 = new class_48(-4, 0, -4)).a1(localclass_481);
    (localclass_483 = new class_48(4, 52, 4)).a1(localclass_481);
    this.jdField_field_248_of_type_ArrayOfClass_1068[1] = new class_1064(paramLong.jdField_field_248_of_type_ArrayOfClass_1068, localclass_482, localclass_483);
    this.jdField_field_248_of_type_ArrayOfClass_1068[2] = a10(new class_48(0, 50, 0), 4, 0);
    this.jdField_field_248_of_type_ArrayOfClass_1068[3] = a10(new class_48(0, 50, 0), 4, 1);
    this.jdField_field_248_of_type_ArrayOfClass_1068[4] = b1(new class_48(0, 50, 0), 4, 0);
    this.jdField_field_248_of_type_ArrayOfClass_1068[5] = b1(new class_48(0, 50, 0), 4, 1);
    this.jdField_field_248_of_type_ArrayOfClass_1068[6] = a10(new class_48(0, 50, -55), 4, 0);
    this.jdField_field_248_of_type_ArrayOfClass_1068[7] = a10(new class_48(0, 50, -55), 4, 1);
    this.jdField_field_248_of_type_ArrayOfClass_1068[8] = b1(new class_48(0, 50, -55), 4, 0);
    this.jdField_field_248_of_type_ArrayOfClass_1068[9] = b1(new class_48(0, 50, -55), 4, 1);
    this.jdField_field_248_of_type_ArrayOfClass_1068[10] = a8(new class_48(0, 50, 0), 3);
    this.jdField_field_248_of_type_ArrayOfClass_1068[11] = a10(new class_48(0, 5, 0), 5, 0);
    this.jdField_field_248_of_type_ArrayOfClass_1068[12] = a10(new class_48(0, 5, 0), 5, 1);
    this.jdField_field_248_of_type_ArrayOfClass_1068[13] = b1(new class_48(0, 5, 0), 5, 0);
    this.jdField_field_248_of_type_ArrayOfClass_1068[14] = b1(new class_48(0, 5, 0), 5, 1);
    this.jdField_field_248_of_type_ArrayOfClass_1068[15] = a10(new class_48(0, 5, -55), 5, 0);
    this.jdField_field_248_of_type_ArrayOfClass_1068[16] = a10(new class_48(0, 5, -55), 5, 1);
    this.jdField_field_248_of_type_ArrayOfClass_1068[17] = b1(new class_48(0, 5, -55), 5, 0);
    this.jdField_field_248_of_type_ArrayOfClass_1068[18] = b1(new class_48(0, 5, -55), 5, 1);
    this.jdField_field_248_of_type_ArrayOfClass_1068[19] = a8(new class_48(0, 4, 0), 4);
    this.jdField_field_248_of_type_ArrayOfClass_1068[20] = a9(new class_48(30, 0, 30));
    this.jdField_field_248_of_type_ArrayOfClass_1068[21] = a9(new class_48(-30, 0, 30));
    this.jdField_field_248_of_type_ArrayOfClass_1068[22] = a9(new class_48(-30, 0, -30));
    this.jdField_field_248_of_type_ArrayOfClass_1068[23] = a9(new class_48(30, 0, -30));
    this.jdField_field_248_of_type_ArrayOfClass_1068[24] = b2(new class_48(0, 48, -62), 0);
    this.jdField_field_248_of_type_ArrayOfClass_1068[25] = b2(new class_48(0, 48, 62), 1);
    this.jdField_field_248_of_type_ArrayOfClass_1068[26] = b2(new class_48(62, 48, 0), 4);
    this.jdField_field_248_of_type_ArrayOfClass_1068[27] = b2(new class_48(-62, 48, 0), 5);
    this.jdField_field_248_of_type_ArrayOfClass_1068[28] = b2(new class_48(0, 5, -62), 0);
    this.jdField_field_248_of_type_ArrayOfClass_1068[29] = b2(new class_48(0, 5, 62), 1);
    this.jdField_field_248_of_type_ArrayOfClass_1068[30] = b2(new class_48(62, 5, 0), 4);
    this.jdField_field_248_of_type_ArrayOfClass_1068[31] = b2(new class_48(-62, 5, 0), 5);
    this.jdField_field_248_of_type_ArrayOfClass_1068[32] = b2(new class_48(0, 54, -50), 2);
    this.jdField_field_248_of_type_ArrayOfClass_1068[33] = b2(new class_48(20, 2, 20), 3);
    this.jdField_field_248_of_type_ArrayOfClass_1068[34] = new class_1059(new class_48(2, 1, -2), this.jdField_field_248_of_type_ArrayOfClass_1068, new class_48(-3, 0, -3), new class_48(3, 2, 3), 20);
    this.jdField_field_248_of_type_ArrayOfClass_1068[35] = new class_1059(new class_48(2, 1, 2), this.jdField_field_248_of_type_ArrayOfClass_1068, new class_48(-3, 0, -3), new class_48(3, 2, 3), 20);
    this.jdField_field_248_of_type_ArrayOfClass_1068[36] = new class_1059(new class_48(-2, 1, 2), this.jdField_field_248_of_type_ArrayOfClass_1068, new class_48(-3, 0, -3), new class_48(3, 2, 3), 20);
    this.jdField_field_248_of_type_ArrayOfClass_1068[37] = new class_1059(new class_48(-2, 1, -2), this.jdField_field_248_of_type_ArrayOfClass_1068, new class_48(-3, 0, -3), new class_48(3, 2, 3), 20);
    for (paramLong = 0; paramLong < this.jdField_field_248_of_type_ArrayOfClass_1068.length; paramLong++) {
      this.jdField_field_248_of_type_ArrayOfClass_1068[paramLong].a7();
    }
  }
  
  private class_1068 a10(class_48 paramclass_48, int paramInt1, int paramInt2)
  {
    class_48 localclass_48;
    (localclass_48 = new class_48(-5, -paramInt1, 0)).a1(paramclass_48);
    (paramInt1 = new class_48(5, paramInt1, 55)).a1(paramclass_48);
    this.jdField_field_248_of_type_ItUnimiDsiFastutilShortsShortArrayList.add((short)75);
    this.jdField_field_248_of_type_ItUnimiDsiFastutilShortsShortArrayList.add((short)77);
    this.jdField_field_248_of_type_ItUnimiDsiFastutilShortsShortArrayList.add((short)69);
    this.jdField_field_248_of_type_ItUnimiDsiFastutilShortsShortArrayList.add((short)79);
    this.jdField_field_248_of_type_ItUnimiDsiFastutilShortsShortArrayList.add((short)76);
    this.jdField_field_248_of_type_ItUnimiDsiFastutilShortsShortArrayList.add((short)5);
    this.jdField_field_248_of_type_ItUnimiDsiFastutilShortsShortArrayList.add((short)63);
    Collections.shuffle(this.jdField_field_248_of_type_ItUnimiDsiFastutilShortsShortArrayList, this.jdField_field_248_of_type_JavaUtilRandom);
    return new class_1164(this, this.jdField_field_248_of_type_ArrayOfClass_1068, localclass_48, paramInt1, paramInt2);
  }
  
  private class_1068 b1(class_48 paramclass_48, int paramInt1, int paramInt2)
  {
    class_48 localclass_48;
    (localclass_48 = new class_48(-4, -(paramInt1 - 1), 0)).a1(paramclass_48);
    (paramInt1 = new class_48(4, paramInt1 - 1, 55)).a1(paramclass_48);
    return new class_1173(this.jdField_field_248_of_type_ArrayOfClass_1068, localclass_48, paramInt1, paramInt2);
  }
  
  private class_1068 a8(class_48 paramclass_48, int paramInt)
  {
    class_48 localclass_48;
    (localclass_48 = new class_48(-57.0F, -paramInt, -57.0F)).a1(paramclass_48);
    (paramInt = new class_48(57.0F, paramInt, 57.0F)).a1(paramclass_48);
    return new class_1066(this.jdField_field_248_of_type_ArrayOfClass_1068, localclass_48, paramInt);
  }
  
  private class_1068 b2(class_48 paramclass_48, int paramInt)
  {
    class_48 localclass_481;
    (localclass_481 = new class_48(-10, -10, -10)).a1(paramclass_48);
    class_48 localclass_482;
    (localclass_482 = new class_48(10, 10, 10)).a1(paramclass_48);
    return new class_1111(paramclass_48, this.jdField_field_248_of_type_ArrayOfClass_1068, localclass_481, localclass_482, org.schema.game.common.data.element.Element.orientationMapping[paramInt]);
  }
  
  private class_1068 a9(class_48 paramclass_48)
  {
    class_48 localclass_481;
    (localclass_481 = new class_48(this.field_257)).a1(paramclass_48);
    class_48 localclass_482;
    (localclass_482 = new class_48(this.field_258)).a1(paramclass_48);
    return new class_1101(this.jdField_field_248_of_type_ArrayOfClass_1068, localclass_481, localclass_482);
  }
  
  public final void a3(SegmentController paramSegmentController, Segment paramSegment)
  {
    for (int i = 0; i < this.jdField_field_248_of_type_ArrayOfClass_1068.length; i++) {
      if ((this.jdField_field_248_of_type_ArrayOfClass_1068[i] instanceof class_1168)) {
        ((class_1168)this.jdField_field_248_of_type_ArrayOfClass_1068[i]).field_240 = paramSegment.a15().getControlElementMap();
      }
    }
    this.jdField_field_248_of_type_JavaUtilRandom.setSeed(paramSegmentController.getSeed() + (paramSegment.field_34.field_475 << 4) + (paramSegment.field_34.field_476 << 8) + paramSegment.field_34.field_477);
    if ((paramSegment.field_34.field_476 >= 0) && (FastMath.l(paramSegment.field_34.field_475 * paramSegment.field_34.field_475 + paramSegment.field_34.field_476 * paramSegment.field_34.field_476) <= 96.0F))
    {
      Arrays.fill(jdField_field_248_of_type_ArrayOfShort, (short)0);
      ByteUtil.b(Math.abs(paramSegment.field_34.field_476));
      this.jdField_field_248_of_type_Class_1138.a3(paramSegment, ByteUtil.b(paramSegment.field_34.field_475), ByteUtil.b(paramSegment.field_34.field_477), jdField_field_248_of_type_ArrayOfShort);
      SegmentData localSegmentData1 = paramSegment.a16();
      short[] arrayOfShort1 = jdField_field_248_of_type_ArrayOfShort;
      int k = paramSegment.field_34.field_477;
      j = paramSegment.field_34.field_475;
      localObject1 = this;
      for (int m = 0; m < 16; m++) {
        for (int n = 0; n < 16; n++)
        {
          int i1 = j + n;
          int i2 = k + m;
          if (FastMath.l(i1 * i1 + i2 * i2) < 80.0F)
          {
            SegmentData localSegmentData2 = localSegmentData1;
            short[] arrayOfShort2 = arrayOfShort1;
            int i3 = m;
            i2 = n;
            Object localObject2 = localObject1;
            int i4;
            int i5 = (i4 = Math.abs(localSegmentData2.getSegment().field_34.field_476)) + 16;
            for (int i6 = 63; i6 >= 0; i6--) {
              if (i6 < 64)
              {
                int i7 = ((i2 << 4) + i3 << 6) + i6;
                if ((i6 >= i4) && (i6 < i5))
                {
                  Segment localSegment1 = localSegmentData2.getSegment();
                  localObject2.jdField_field_248_of_type_Class_48.b(localSegment1.field_34.field_475 + i2, i6, localSegment1.field_34.field_477 + i3);
                  short s2 = 0;
                  class_1068[] arrayOfclass_1068;
                  int i10 = (arrayOfclass_1068 = localObject2.jdField_field_248_of_type_ArrayOfClass_1068).length;
                  Short localShort;
                  short s1;
                  int i9;
                  for (int i12 = 0; i12 < i10; localShort++)
                  {
                    class_1068 localclass_1068;
                    if ((localclass_1068 = arrayOfclass_1068[i12]).a3(localObject2.jdField_field_248_of_type_Class_48))
                    {
                      s2 = localclass_1068.b1(localObject2.jdField_field_248_of_type_Class_48);
                      if (((localclass_1068.field_240 >= 6) || (arrayOfShort2[i7] == 0)) && (s2 != 32767))
                      {
                        byte b = localclass_1068.a6(localObject2.jdField_field_248_of_type_Class_48);
                        localShort = Short.valueOf(s2);
                        Segment localSegment2 = localSegment1;
                        s2 = (byte)i3;
                        s1 = (byte)(i6 % 16);
                        i9 = (byte)i2;
                        if ((localShort.shortValue() != 0) && (!localSegment2.a16().containsUnsave((byte)i9, (byte)s1, (byte)s2))) {
                          localSegment2.a16().setInfoElementForcedAddUnsynched((byte)i9, (byte)s1, (byte)s2, localShort.shortValue(), b, (byte)-1, false);
                        }
                      }
                      s2 = 1;
                      break;
                    }
                  }
                  if ((s2 == 0) && (arrayOfShort2[i7] == 0) && (i2 >= 0) && (i6 >= 0) && (i3 >= 0) && (i2 < 16) && (i6 < 64) && (i3 < 16))
                  {
                    i9 = ((i2 - 1 << 4) + i3 << 6) + i6;
                    int i11 = ((i2 + 1 << 4) + i3 << 6) + i6;
                    int i13 = ((i2 << 4) + i3 << 6) + (i6 + 1);
                    int i14 = ((i2 << 4) + i3 << 6) + (i6 - 1);
                    int i8 = ((i2 << 4) + (i3 + 1) << 6) + i6;
                    i7 = ((i2 << 4) + (i3 - 1) << 6) + i6;
                    s1 = 0;
                    if (((i2 > 0) && ((s1 = arrayOfShort2[i9]) > 0)) || ((i2 < 15) && ((s1 = arrayOfShort2[i11]) > 0)) || ((i6 < 63) && ((s1 = arrayOfShort2[i13]) > 0)) || ((i6 > 0) && ((s1 = arrayOfShort2[i14]) > 0)) || ((i3 < 15) && ((s1 = arrayOfShort2[i8]) > 0)) || ((i3 > 0) && ((s1 = arrayOfShort2[i7]) > 0))) {
                      if ((i6 >= 62) && (localObject2.jdField_field_248_of_type_JavaUtilRandom.nextInt(16) == 0)) {
                        localSegmentData2.setInfoElementForcedAddUnsynched((byte)i2, (byte)(i6 % 16), (byte)i3, (short)62, false);
                      } else {
                        localSegmentData2.setInfoElementForcedAddUnsynched((byte)i2, (byte)(i6 % 16), (byte)i3, s1, false);
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
      if (paramSegment.field_34.a3(0, 0, 0))
      {
        paramSegment.a16().setInfoElementForcedAddUnsynched((byte)8, (byte)8, (byte)8, (short)123, false);
        paramSegment.a16().setInfoElementForcedAddUnsynched((byte)8, (byte)9, (byte)8, (short)291, false);
      }
    }
    Object localObject1 = (class_1041)paramSegment.a15().getState();
    for (int j = 0; j < this.jdField_field_248_of_type_ArrayOfClass_1068.length; j++) {
      if (((this.jdField_field_248_of_type_ArrayOfClass_1068[j] instanceof class_1168)) && (((class_1168)this.jdField_field_248_of_type_ArrayOfClass_1068[j]).a2())) {
        ((class_1168)this.jdField_field_248_of_type_ArrayOfClass_1068[j]).a5(((class_1041)localObject1).b11(), paramSegment);
      }
    }
    paramSegmentController.getSegmentBuffer().b6(paramSegment);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1166
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */