import java.util.Arrays;
import java.util.Random;
import org.schema.common.FastMath;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.SegmentData;
import org.schema.game.server.controller.world.factory.terrain.NoiseGeneratorOctaves;

public class class_1119
{
  protected Random field_1373;
  protected NoiseGeneratorOctaves field_1373;
  protected NoiseGeneratorOctaves field_1374;
  protected NoiseGeneratorOctaves field_1375;
  protected NoiseGeneratorOctaves field_1376;
  private class_1068[] jdField_field_1373_of_type_ArrayOfClass_1068;
  private class_1138 jdField_field_1373_of_type_Class_1138;
  private double[] field_1377;
  double[] jdField_field_1373_of_type_ArrayOfDouble;
  double[] jdField_field_1374_of_type_ArrayOfDouble;
  double[] jdField_field_1375_of_type_ArrayOfDouble;
  double[] field_1376;
  private static float[] jdField_field_1373_of_type_ArrayOfFloat;
  private class_1083 jdField_field_1373_of_type_Class_1083;
  private byte jdField_field_1373_of_type_Byte = 2;
  private class_48 jdField_field_1373_of_type_Class_48 = new class_48();
  double jdField_field_1373_of_type_Double = 2.35D;
  private class_1148 jdField_field_1373_of_type_Class_1148;
  protected boolean field_1373;
  protected boolean field_1374;
  private static short[] jdField_field_1373_of_type_ArrayOfShort = new short[16384];
  protected float field_1373;
  private class_1150 jdField_field_1373_of_type_Class_1150;
  private class_48 jdField_field_1374_of_type_Class_48 = new class_48();
  
  public class_1119(long paramLong)
  {
    this.jdField_field_1373_of_type_Float = 7.0F;
    this.jdField_field_1373_of_type_JavaUtilRandom = new Random(paramLong);
    a();
  }
  
  protected void a()
  {
    this.jdField_field_1373_of_type_OrgSchemaGameServerControllerWorldFactoryTerrainNoiseGeneratorOctaves = new NoiseGeneratorOctaves(this.jdField_field_1373_of_type_JavaUtilRandom, 16);
    this.jdField_field_1374_of_type_OrgSchemaGameServerControllerWorldFactoryTerrainNoiseGeneratorOctaves = new NoiseGeneratorOctaves(this.jdField_field_1373_of_type_JavaUtilRandom, 16);
    this.jdField_field_1375_of_type_OrgSchemaGameServerControllerWorldFactoryTerrainNoiseGeneratorOctaves = new NoiseGeneratorOctaves(this.jdField_field_1373_of_type_JavaUtilRandom, 8);
    new NoiseGeneratorOctaves(this.jdField_field_1373_of_type_JavaUtilRandom, 4);
    this.jdField_field_1376_of_type_OrgSchemaGameServerControllerWorldFactoryTerrainNoiseGeneratorOctaves = new NoiseGeneratorOctaves(this.jdField_field_1373_of_type_JavaUtilRandom, 16);
  }
  
  private void a2(int paramInt1, int paramInt2, short[] paramArrayOfShort, SegmentData paramSegmentData, boolean paramBoolean)
  {
    paramInt1 = 64 - paramInt1 << 4;
    paramInt2 = 64 - paramInt2 << 4;
    int i = 0;
    int j = 0;
    int k = paramInt1 + (15 - j);
    int m = paramInt2 + (15 - i);
    float f = FastMath.l(k * k + m * m);
    m = 64;
    if (f > 202.0F)
    {
      m = (int)(64.0F - (32.0F - FastMath.l((240.0F - f) * 40.0F)));
      m = Math.max(1, m);
    }
    if (f < 240.0F)
    {
      SegmentData localSegmentData = paramSegmentData;
      short[] arrayOfShort = paramArrayOfShort;
      int i1 = m;
      int n = i;
      m = j;
      class_1119 localclass_1119 = this;
      int i2 = -1;
      int i3;
      int i4 = (i3 = Math.abs(localSegmentData.getSegment().field_34.field_476)) + 16;
      int i5 = 63;
      if (i5 < i1)
      {
        int i6 = ((m << 4) + n << 6) + i5;
        if ((arrayOfShort[i6] > 0) && (i2 < 0)) {
          i2 = i5;
        }
        if ((i5 >= i3) && (i5 < i4))
        {
          if (localclass_1119.jdField_field_1373_of_type_ArrayOfClass_1068 != null)
          {
            localclass_1119.jdField_field_1374_of_type_Class_48.b(localSegmentData.getSegment().field_34.field_475 + m, i5, localSegmentData.getSegment().field_34.field_477 + n);
            int i7 = 0;
            int i8;
            i7++;
          }
          localSegmentData.setInfoElementForcedAddUnsynched((byte)m, (byte)(15 - i5 % 16), (byte)n, (short)(arrayOfShort[i6] > 0 ? arrayOfShort[i6] : 0), false);
          localSegmentData.setInfoElementForcedAddUnsynched((byte)m, (byte)(i5 % 16), (byte)n, (short)(arrayOfShort[i6] > 0 ? arrayOfShort[i6] : (i2 > 0) && (i5 == i2) ? localclass_1119.jdField_field_1373_of_type_Class_1083.c() : paramBoolean ? localclass_1119.jdField_field_1373_of_type_Class_1083.c() : (localclass_1119.jdField_field_1373_of_type_ArrayOfClass_1068[i7].a3(localclass_1119.jdField_field_1374_of_type_Class_48)) && ((i8 = localclass_1119.jdField_field_1373_of_type_ArrayOfClass_1068[i7].b1(localclass_1119.jdField_field_1374_of_type_Class_48)) != 32767) ? i8 : 0), false);
        }
      }
      i5--;
    }
    j++;
    i++;
  }
  
  public final SegmentData a3(SegmentData paramSegmentData, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    int j;
    if ((paramInt2 > 3) && (this.jdField_field_1373_of_type_ArrayOfClass_1068 != null))
    {
      paramInt2 = paramSegmentData.getSegment();
      paramInt1 = this;
      paramInt3 = new class_48();
      for (paramBoolean = false; paramBoolean < true; paramBoolean = (byte)(paramBoolean + true)) {
        for (int i = 0; i < 16; j = (byte)(i + 1))
        {
          int m;
          for (int k = 0; k < 16; m = (byte)(k + 1))
          {
            paramInt3.b1(paramInt2.field_34);
            paramInt3.field_475 += k;
            paramInt3.field_476 += i;
            paramInt3.field_477 += paramBoolean;
            class_1068[] arrayOfclass_1068;
            int n = (arrayOfclass_1068 = paramInt1.jdField_field_1373_of_type_ArrayOfClass_1068).length;
            for (int i1 = 0; i1 < n; i1++)
            {
              class_1068 localclass_1068;
              if ((localclass_1068 = arrayOfclass_1068[i1]).a3(paramInt3))
              {
                short s;
                if ((s = localclass_1068.b1(paramInt3)) == 32767) {
                  break;
                }
                paramInt2.a16().setInfoElementForcedAddUnsynched(k, i, paramBoolean, s, false);
                break;
              }
            }
          }
        }
      }
      paramSegmentData.getSegmentController().getSegmentBuffer().b6(paramSegmentData.getSegment());
      return paramSegmentData;
    }
    if ((!jdField_field_1375_of_type_Boolean) && ((paramInt1 < 0) || (paramInt2 < 0) || (paramInt3 < 0))) {
      throw new AssertionError();
    }
    if (this.jdField_field_1373_of_type_Class_1150 == null) {
      this.jdField_field_1373_of_type_Class_1150 = new class_1150(this.jdField_field_1373_of_type_Class_1083.c(), this.jdField_field_1373_of_type_Class_1083.a(), this.jdField_field_1373_of_type_Class_1083.d());
    }
    if (this.jdField_field_1373_of_type_Class_1138 == null) {
      this.jdField_field_1373_of_type_Class_1138 = new class_1136(this.jdField_field_1373_of_type_Class_1083.c(), this.jdField_field_1373_of_type_Class_1083.b(), this.jdField_field_1373_of_type_Class_1083.a(), this.jdField_field_1373_of_type_Class_1083.d());
    }
    if (this.jdField_field_1373_of_type_Class_1148 == null) {
      this.jdField_field_1373_of_type_Class_1148 = new class_1148(this.jdField_field_1373_of_type_Class_1083.c(), this.jdField_field_1373_of_type_Class_1083.a(), this.jdField_field_1373_of_type_Class_1083.d());
    }
    Arrays.fill(jdField_field_1373_of_type_ArrayOfShort, (short)0);
    this.jdField_field_1373_of_type_JavaUtilRandom.setSeed(paramInt1 * 341873128712L + paramInt3 * 132897987541L + (paramBoolean ? 2345235L : 0L));
    a4(paramInt1, paramInt2, paramInt3, jdField_field_1373_of_type_ArrayOfShort);
    if (this.jdField_field_1373_of_type_Boolean) {
      this.jdField_field_1373_of_type_Class_1148.a3(paramSegmentData.getSegment(), paramInt1, paramInt3, jdField_field_1373_of_type_ArrayOfShort);
    } else if (this.jdField_field_1374_of_type_Boolean) {
      this.jdField_field_1373_of_type_Class_1150.a3(paramSegmentData.getSegment(), paramInt1, paramInt3, jdField_field_1373_of_type_ArrayOfShort);
    } else {
      this.jdField_field_1373_of_type_Class_1138.a3(paramSegmentData.getSegment(), paramInt1, paramInt3, jdField_field_1373_of_type_ArrayOfShort);
    }
    a2(paramInt1, paramInt3, jdField_field_1373_of_type_ArrayOfShort, paramSegmentData, paramBoolean);
    for (paramBoolean = false; paramBoolean < this.jdField_field_1373_of_type_Class_1083.a1().length; paramBoolean++) {
      for (j = 0; j < 10; j++)
      {
        this.jdField_field_1373_of_type_Class_1083.a1();
        this.jdField_field_1373_of_type_Class_1083.a1()[paramBoolean].a(paramSegmentData, this.jdField_field_1373_of_type_JavaUtilRandom, (paramInt1 << 4) + this.jdField_field_1373_of_type_Class_1083.a1()[paramBoolean].a1(this.jdField_field_1373_of_type_JavaUtilRandom), (paramInt2 << 4) + this.jdField_field_1373_of_type_JavaUtilRandom.nextInt(16), (paramInt3 << 4) + this.jdField_field_1373_of_type_Class_1083.a1()[paramBoolean].b(this.jdField_field_1373_of_type_JavaUtilRandom));
      }
    }
    paramSegmentData.getSegmentController().getSegmentBuffer().b6(paramSegmentData.getSegment());
    return paramSegmentData;
  }
  
  private void a4(int paramInt1, int paramInt2, int paramInt3, short[] paramArrayOfShort)
  {
    this.jdField_field_1373_of_type_Class_48.b(paramInt1, paramInt2, paramInt3);
    int k = 0;
    int j = paramInt3 << 2;
    int i = paramInt1 << 2;
    arrayOfDouble = this.field_1377;
    paramInt3 = this;
    if (arrayOfDouble == null) {
      arrayOfDouble = new double[425];
    }
    paramInt3.a1(i, j);
    int m = 0;
    int n = 0;
    for (int i1 = 0; i1 < 5; i1++) {
      for (int i2 = 0; i2 < 5; i2++)
      {
        int i3 = -2;
        int i4 = -2;
        i3++;
        double d1;
        if ((d1 = paramInt3.jdField_field_1376_of_type_ArrayOfDouble[n] / 8000.0D) < 0.0D) {
          d1 = -d1 * 0.3D;
        }
        if ((d1 = d1 * 3.0D - 2.0D) < 0.0D)
        {
          if (d1 /= 2.0D < -1.0D) {
            d1 = -1.0D;
          }
          d1 = d1 / 1.4D / 2.0D;
        }
        else
        {
          if (d1 > 1.0D) {
            d1 = 1.0D;
          }
          d1 /= 8.0D;
        }
        n++;
        for (double d2 = 0.0D; d2 < 17.0D; d2 += 1.0D)
        {
          double d3 = -0.550000011920929D + d1 * paramInt3.jdField_field_1373_of_type_Double;
          double d4 = 14.5D + d3 * paramInt3.jdField_field_1373_of_type_Float;
          double d6;
          if ((d6 = (d2 - d4) * 12.0D * 64.0D / 64.0D) < 0.0D) {
            d6 *= 2.0D;
          }
          double d7 = paramInt3.jdField_field_1374_of_type_ArrayOfDouble[m] / 512.0D;
          double d8 = paramInt3.jdField_field_1375_of_type_ArrayOfDouble[m] / 512.0D;
          double d9;
          double d5;
          if ((d9 = (paramInt3.jdField_field_1373_of_type_ArrayOfDouble[m] / 10.0D + 1.0D) / 2.0D) < 0.0D) {
            d5 = d7;
          } else if (d9 > 1.0D) {
            d5 = d8;
          } else {
            d5 = d7 + (d8 - d7) * d9;
          }
          d5 -= d6;
          if (d2 > 13.0D)
          {
            double d10 = (float)(d2 - 13.0D) / 3.0F;
            d5 = d5 * (1.0D - d10) + d10 * -10.0D;
          }
          arrayOfDouble[m] = d5;
          m++;
        }
      }
    }
  }
  
  protected void a1(int paramInt1, int paramInt2)
  {
    this.jdField_field_1376_of_type_ArrayOfDouble = this.jdField_field_1376_of_type_OrgSchemaGameServerControllerWorldFactoryTerrainNoiseGeneratorOctaves.a1(this.jdField_field_1376_of_type_ArrayOfDouble, paramInt1, paramInt2, 200.0D, 200.0D);
    this.jdField_field_1373_of_type_ArrayOfDouble = this.jdField_field_1375_of_type_OrgSchemaGameServerControllerWorldFactoryTerrainNoiseGeneratorOctaves.a(this.jdField_field_1373_of_type_ArrayOfDouble, paramInt1, 0, paramInt2, 17, 8.555150000000001D, 4.277575000000001D, 8.555150000000001D);
    this.jdField_field_1374_of_type_ArrayOfDouble = this.jdField_field_1373_of_type_OrgSchemaGameServerControllerWorldFactoryTerrainNoiseGeneratorOctaves.a(this.jdField_field_1374_of_type_ArrayOfDouble, paramInt1, 0, paramInt2, 17, 684.41200000000003D, 684.41200000000003D, 684.41200000000003D);
    this.jdField_field_1375_of_type_ArrayOfDouble = this.jdField_field_1374_of_type_OrgSchemaGameServerControllerWorldFactoryTerrainNoiseGeneratorOctaves.a(this.jdField_field_1375_of_type_ArrayOfDouble, paramInt1, 0, paramInt2, 17, 684.41200000000003D, 684.41200000000003D, 684.41200000000003D);
  }
  
  public final void b()
  {
    this.jdField_field_1373_of_type_Double = 1.15D;
  }
  
  public final void a5(class_1083 paramclass_1083)
  {
    this.jdField_field_1373_of_type_Class_1083 = paramclass_1083;
  }
  
  public final void a6(class_1068[] paramArrayOfclass_1068)
  {
    this.jdField_field_1373_of_type_ArrayOfClass_1068 = paramArrayOfclass_1068;
  }
  
  public final void a7(Segment paramSegment)
  {
    if (this.jdField_field_1373_of_type_ArrayOfClass_1068 != null)
    {
      class_1041 localclass_1041 = (class_1041)paramSegment.a15().getState();
      for (int i = 0; i < this.jdField_field_1373_of_type_ArrayOfClass_1068.length; i++) {
        if (((this.jdField_field_1373_of_type_ArrayOfClass_1068[i] instanceof class_1168)) && (((class_1168)this.jdField_field_1373_of_type_ArrayOfClass_1068[i]).a2())) {
          ((class_1168)this.jdField_field_1373_of_type_ArrayOfClass_1068[i]).a5(localclass_1041.b11(), paramSegment);
        }
      }
    }
  }
  
  static
  {
    jdField_field_1373_of_type_ArrayOfFloat = new float[25];
    for (int i = -2; i <= 2; i++) {
      for (int j = -2; j <= 2; j++)
      {
        float f = 10.0F / FastMath.l(i * i + j * j + 0.2F);
        jdField_field_1373_of_type_ArrayOfFloat[(i + 2 + (j + 2) * 5)] = f;
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1119
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */