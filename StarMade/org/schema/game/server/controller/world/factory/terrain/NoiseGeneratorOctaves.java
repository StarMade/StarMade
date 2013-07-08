/*  1:   */package org.schema.game.server.controller.world.factory.terrain;
/*  2:   */
/*  3:   */import java.util.Arrays;
/*  4:   */import uT;
/*  5:   */
/*  6:   */public class NoiseGeneratorOctaves extends uT
/*  7:   */{
/*  8:   */  private uU[] jdField_a_of_type_ArrayOfUU;
/*  9:   */  private int jdField_a_of_type_Int;
/* 10:   */  private static double[] jdField_a_of_type_ArrayOfDouble;
/* 11:   */  
/* 12:   */  static
/* 13:   */  {
/* 14:14 */    (NoiseGeneratorOctaves.jdField_a_of_type_ArrayOfDouble = new double[32])[0] = 1.0D;
/* 15:15 */    for (int i = 1; i < 32; i++)
/* 16:16 */      jdField_a_of_type_ArrayOfDouble[i] = (jdField_a_of_type_ArrayOfDouble[(i - 1)] / 2.0D);
/* 17:   */  }
/* 18:   */  
/* 19:   */  public static void main(String[] paramArrayOfString) {
/* 20:20 */    long l = System.currentTimeMillis();
/* 21:21 */    System.err.println(System.currentTimeMillis() - l);
/* 22:   */    
/* 26:26 */    l = System.currentTimeMillis();
/* 27:27 */    System.err.println(System.currentTimeMillis() - l);
/* 28:   */  }
/* 29:   */  
/* 33:   */  public NoiseGeneratorOctaves(java.util.Random paramRandom, int paramInt)
/* 34:   */  {
/* 35:35 */    this.jdField_a_of_type_Int = paramInt;
/* 36:36 */    if ((!jdField_a_of_type_Boolean) && (this.jdField_a_of_type_Int > 32)) throw new AssertionError();
/* 37:37 */    this.jdField_a_of_type_ArrayOfUU = new uU[paramInt];
/* 38:   */    
/* 39:39 */    for (int i = 0; i < paramInt; i++)
/* 40:   */    {
/* 41:41 */      this.jdField_a_of_type_ArrayOfUU[i] = new uU(paramRandom);
/* 42:   */    }
/* 43:   */  }
/* 44:   */  
/* 46:   */  public final double[] a(double[] paramArrayOfDouble, int paramInt1, int paramInt2, int paramInt3, int paramInt4, double paramDouble1, double paramDouble2, double paramDouble3)
/* 47:   */  {
/* 48:48 */    if (paramArrayOfDouble == null) {
/* 49:49 */      paramArrayOfDouble = new double[5 * paramInt4 * 5];
/* 50:   */    }
/* 51:   */    else {
/* 52:52 */      Arrays.fill(paramArrayOfDouble, 0.0D);
/* 53:   */    }
/* 54:   */    
/* 56:56 */    for (int i = 0; i < this.jdField_a_of_type_Int; i++) {
/* 57:57 */      double d1 = jdField_a_of_type_ArrayOfDouble[i];
/* 58:   */      
/* 60:60 */      double d2 = paramInt1 * d1 * paramDouble1;
/* 61:61 */      double d3 = paramInt2 * d1 * paramDouble2;
/* 62:62 */      double d4 = paramInt3 * d1 * paramDouble3;
/* 63:63 */      long l1 = d2;
/* 64:64 */      long l2 = d4;
/* 65:65 */      d2 -= l1;
/* 66:66 */      d4 -= l2;
/* 67:67 */      l1 %= 16777216L;
/* 68:68 */      l2 %= 16777216L;
/* 69:69 */      d2 += l1;
/* 70:70 */      d4 += l2;
/* 71:71 */      double d11 = d1;double d10 = paramDouble3 * d1;double d9 = paramDouble2 * d1;double d8 = paramDouble1 * d1;double d7 = d4;double d6 = d3;double d5 = d2;double[] arrayOfDouble = paramArrayOfDouble;uU localuU = this.jdField_a_of_type_ArrayOfUU[i]; double d13; double d12; double d20; double d26; double d28; int i6; int i9; double d39; double d33; if (paramInt4 == 1) { double d18 = d11;double d16 = d10;double d15 = d8;d13 = d7;d12 = d5;arrayOfDouble = arrayOfDouble;localuU = localuU;int j = 0;d20 = 1.0D / d18; for (int k = 0; k < 5; k++) { double d22; int n = (int)(d22 = d12 + k * d15 + localuU.jdField_a_of_type_Double); if (d22 < n) n--; int i1 = n & 0xFF; long tmp278_277 = (d22 - n);double d24 = tmp278_277 * (d22 = tmp278_277) * d22 * (d22 * (d22 * 6.0D - 15.0D) + 10.0D); for (int i3 = 0; i3 < 5; i3++) { int i4 = (int)(d26 = d13 + i3 * d16 + localuU.c); if (d26 < i4) i4--; int i5 = i4 & 0xFF; long tmp362_361 = (d26 - i4);d28 = tmp362_361 * (d26 = tmp362_361) * d26 * (d26 * (d26 * 6.0D - 15.0D) + 10.0D);i6 = localuU.jdField_a_of_type_ArrayOfInt[i1];int i7 = localuU.jdField_a_of_type_ArrayOfInt[i6] + i5;int i8 = localuU.jdField_a_of_type_ArrayOfInt[(i1 + 1)];i9 = localuU.jdField_a_of_type_ArrayOfInt[i8] + i5;double d36 = d26;double d34 = d22;int i15 = localuU.jdField_a_of_type_ArrayOfInt[i7] & 0xF;double d38 = (1 - ((i15 & 0x8) >> 3)) * d34;d39 = i15 >= 4 ? d34 : (i15 != 12) && (i15 != 14) ? d36 : 0.0D;double d30 = uU.a(d24, ((i15 & 0x1) != 0 ? -d38 : d38) + ((i15 & 0x2) != 0 ? -d39 : d39), uU.a(localuU.jdField_a_of_type_ArrayOfInt[i9], d22 - 1.0D, 0.0D, d26));double d32 = uU.a(d24, uU.a(localuU.jdField_a_of_type_ArrayOfInt[(i7 + 1)], d22, 0.0D, d26 - 1.0D), uU.a(localuU.jdField_a_of_type_ArrayOfInt[(i9 + 1)], d22 - 1.0D, 0.0D, d26 - 1.0D));d33 = uU.a(d28, d30, d32);arrayOfDouble[(j++)] += d33 * d20; } } } else { double d21 = d11;d20 = d10;double d19 = d9;double d17 = d8;double d14 = d7;d13 = d6;d12 = d5;arrayOfDouble = arrayOfDouble;localuU = localuU;int m = 0;double d23 = 1.0D / d21;int i2 = -1;double d25 = 0.0D;d26 = 0.0D;double d27 = 0.0D;d28 = 0.0D; for (i6 = 0; i6 < 5; i6++) { double d29; i9 = (int)(d29 = d12 + i6 * d17 + localuU.jdField_a_of_type_Double); if (d29 < i9) i9--; int i10 = i9 & 0xFF; long tmp770_769 = (d29 - i9);double d31 = tmp770_769 * (d29 = tmp770_769) * d29 * (d29 * (d29 * 6.0D - 15.0D) + 10.0D); for (int i11 = 0; i11 < 5; i11++) { int i12 = (int)(d33 = d14 + i11 * d20 + localuU.c); if (d33 < i12) i12--; int i13 = i12 & 0xFF; long tmp854_853 = (d33 - i12);double d35 = tmp854_853 * (d33 = tmp854_853) * d33 * (d33 * (d33 * 6.0D - 15.0D) + 10.0D); for (int i14 = 0; i14 < paramInt4; i14++) { double d37; int i16 = (int)(d37 = d13 + i14 * d19 + localuU.b); if (d37 < i16) i16--; int i17 = i16 & 0xFF; long tmp939_938 = (d37 - i16);tmp770_769 = tmp939_938 * (d37 = tmp939_938) * d37 * (d37 * (d37 * 6.0D - 15.0D) + 10.0D); if ((i14 == 0) || (i17 != i2)) { i2 = i17;int i18 = localuU.jdField_a_of_type_ArrayOfInt[i10] + i17;int i19 = localuU.jdField_a_of_type_ArrayOfInt[(i10 + 1)] + i17;int i20 = localuU.jdField_a_of_type_ArrayOfInt[i18] + i13;int i21 = localuU.jdField_a_of_type_ArrayOfInt[(i18 + 1)] + i13;int i22 = localuU.jdField_a_of_type_ArrayOfInt[i19] + i13;int i23 = localuU.jdField_a_of_type_ArrayOfInt[(i19 + 1)] + i13;d25 = uU.a(d31, uU.a(localuU.jdField_a_of_type_ArrayOfInt[i20], d29, d37, d33), uU.a(localuU.jdField_a_of_type_ArrayOfInt[i22], d29 - 1.0D, d37, d33));d26 = uU.a(d31, uU.a(localuU.jdField_a_of_type_ArrayOfInt[i21], d29, d37 - 1.0D, d33), uU.a(localuU.jdField_a_of_type_ArrayOfInt[i23], d29 - 1.0D, d37 - 1.0D, d33));d27 = uU.a(d31, uU.a(localuU.jdField_a_of_type_ArrayOfInt[(i20 + 1)], d29, d37, d33 - 1.0D), uU.a(localuU.jdField_a_of_type_ArrayOfInt[(i22 + 1)], d29 - 1.0D, d37, d33 - 1.0D));d28 = uU.a(d31, uU.a(localuU.jdField_a_of_type_ArrayOfInt[(i21 + 1)], d29, d37 - 1.0D, d33 - 1.0D), uU.a(localuU.jdField_a_of_type_ArrayOfInt[(i23 + 1)], d29 - 1.0D, d37 - 1.0D, d33 - 1.0D)); } double d40 = uU.a(tmp770_769, d25, d26);double d41 = uU.a(tmp770_769, d27, d28);double d42 = uU.a(d35, d40, d41);arrayOfDouble[(m++)] += d42 * d23;
/* 72:   */            }
/* 73:   */          } } } }
/* 74:74 */    return paramArrayOfDouble;
/* 75:   */  }
/* 76:   */  
/* 79:   */  public final double[] a(double[] paramArrayOfDouble, int paramInt1, int paramInt2, double paramDouble1, double paramDouble2)
/* 80:   */  {
/* 81:81 */    return a(paramArrayOfDouble, paramInt1, 10, paramInt2, 1, paramDouble1, 1.0D, paramDouble2);
/* 82:   */  }
/* 83:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.server.controller.world.factory.terrain.NoiseGeneratorOctaves
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */