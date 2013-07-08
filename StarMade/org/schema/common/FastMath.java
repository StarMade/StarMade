/*   1:    */package org.schema.common;
/*   2:    */
/*   3:    */import java.io.PrintStream;
/*   4:    */import java.util.Random;
/*   5:    */
/*  51:    */public final class FastMath
/*  52:    */{
/*  53: 53 */  private static float[] jdField_a_of_type_ArrayOfFloat = new float[65536];
/*  54:    */  
/*  63:    */  private static float jdField_a_of_type_Float;
/*  64:    */  
/*  73:    */  public static final Random a;
/*  74:    */  
/*  83:    */  private static final float[] jdField_b_of_type_ArrayOfFloat;
/*  84:    */  
/*  93:    */  private static final float[] c;
/*  94:    */  
/* 102:    */  private static final int jdField_a_of_type_Int;
/* 103:    */  
/* 111:    */  private static final float jdField_b_of_type_Float;
/* 112:    */  
/* 120:    */  private static final float[] d;
/* 121:    */  
/* 130:    */  public static float a(float paramFloat)
/* 131:    */  {
/* 132:132 */    if (paramFloat < 0.0F) {
/* 133:133 */      return -paramFloat;
/* 134:    */    }
/* 135:135 */    return paramFloat;
/* 136:    */  }
/* 137:    */  
/* 138:138 */  public static short a(short paramShort) { if (paramShort < 0) return (short)-paramShort; return paramShort;
/* 139:    */  }
/* 140:    */  
/* 149:    */  public static float b(float paramFloat)
/* 150:    */  {
/* 151:151 */    if (-1.0F < paramFloat) {
/* 152:152 */      if (paramFloat < 1.0F) {
/* 153:153 */        return (float)Math.acos(paramFloat);
/* 154:    */      }
/* 155:    */      
/* 156:156 */      return 0.0F;
/* 157:    */    }
/* 158:    */    
/* 159:159 */    return 3.141593F;
/* 160:    */  }
/* 161:    */  
/* 170:    */  public static double a(double paramDouble)
/* 171:    */  {
/* 172:172 */    return (paramDouble * -0.6981317007977321D * paramDouble - 0.8726646259971648D) * paramDouble + 1.570796326794897D;
/* 173:    */  }
/* 174:    */  
/* 175:    */  public static final float a(float paramFloat1, float paramFloat2)
/* 176:    */  {
/* 177:    */    float f2;
/* 178:    */    float f1;
/* 179:179 */    if (paramFloat2 < 0.0F)
/* 180:    */    {
/* 181:181 */      if (paramFloat1 < 0.0F)
/* 182:    */      {
/* 183:183 */        paramFloat2 = -paramFloat2;
/* 184:184 */        paramFloat1 = -paramFloat1;
/* 185:    */        
/* 186:186 */        f2 = 1.0F;
/* 187:    */      }
/* 188:    */      else
/* 189:    */      {
/* 190:190 */        paramFloat2 = -paramFloat2;
/* 191:191 */        f2 = -1.0F;
/* 192:    */      }
/* 193:    */      
/* 194:194 */      f1 = -3.141593F;
/* 195:    */    }
/* 196:    */    else
/* 197:    */    {
/* 198:198 */      if (paramFloat1 < 0.0F)
/* 199:    */      {
/* 200:200 */        paramFloat1 = -paramFloat1;
/* 201:201 */        f2 = -1.0F;
/* 202:    */      }
/* 203:    */      else
/* 204:    */      {
/* 205:205 */        f2 = 1.0F;
/* 206:    */      }
/* 207:    */      
/* 208:208 */      f1 = 0.0F;
/* 209:    */    }
/* 210:    */    
/* 211:211 */    float f3 = jdField_b_of_type_Float / (paramFloat2 < paramFloat1 ? paramFloat1 : paramFloat2);
/* 212:    */    
/* 213:213 */    paramFloat2 = (int)(paramFloat2 * f3);
/* 214:214 */    paramFloat1 = (int)(paramFloat1 * f3);
/* 215:    */    
/* 216:216 */    return (d[(paramFloat1 * jdField_a_of_type_Int + paramFloat2)] + f1) * f2;
/* 217:    */  }
/* 218:    */  
/* 219:    */  static
/* 220:    */  {
/* 221: 55 */    for (int i = 0; i < 65536; i++) {
/* 222: 56 */      jdField_a_of_type_ArrayOfFloat[i] = ((float)Math.sin(i * 3.141592653589793D * 2.0D / 65536.0D));
/* 223:    */    }
/* 224:    */    
/* 236: 70 */    jdField_a_of_type_Float = (float)Math.log(2.0D);
/* 237:    */    
/* 258: 92 */    jdField_a_of_type_JavaUtilRandom = new Random(System.currentTimeMillis());
/* 259:    */    
/* 270:104 */    jdField_b_of_type_ArrayOfFloat = new float[4096];
/* 271:    */    
/* 283:117 */    c = new float[4096];
/* 284:    */    
/* 285:119 */    for (i = 0; i < 4096; i++)
/* 286:    */    {
/* 287:121 */      jdField_b_of_type_ArrayOfFloat[i] = ((float)Math.sin((i + 0.5F) / 4096.0F * 6.283186F));
/* 288:122 */      c[i] = ((float)Math.cos((i + 0.5F) / 4096.0F * 6.283186F));
/* 289:    */    }
/* 290:    */    
/* 393:227 */    jdField_b_of_type_Float = (FastMath.jdField_a_of_type_Int = (int)Math.sqrt(16384.0D)) - 1;
/* 394:    */    
/* 395:229 */    d = new float[16384];
/* 396:    */    
/* 399:233 */    for (i = 0; i < jdField_a_of_type_Int; i++)
/* 400:    */    {
/* 401:235 */      for (int j = 0; j < jdField_a_of_type_Int; j++)
/* 402:    */      {
/* 403:237 */        float f1 = i / jdField_a_of_type_Int;
/* 404:238 */        float f2 = j / jdField_a_of_type_Int;
/* 405:    */        
/* 406:240 */        d[(j * jdField_a_of_type_Int + i)] = ((float)Math.atan2(f2, f1));
/* 407:    */      }
/* 408:    */    }
/* 409:    */  }
/* 410:    */  
/* 504:    */  public static float c(float paramFloat)
/* 505:    */  {
/* 506:340 */    return (float)Math.ceil(paramFloat);
/* 507:    */  }
/* 508:    */  
/* 516:    */  public static byte a(byte paramByte)
/* 517:    */  {
/* 518:352 */    if (paramByte < 0) return 0; if (paramByte > 7) return 7; return paramByte;
/* 519:    */  }
/* 520:    */  
/* 528:    */  public static float a(float paramFloat1, float paramFloat2, float paramFloat3)
/* 529:    */  {
/* 530:364 */    if (paramFloat1 < paramFloat2) return paramFloat2; if (paramFloat1 > paramFloat3) return paramFloat3; return paramFloat1;
/* 531:    */  }
/* 532:    */  
/* 558:    */  public static float d(float paramFloat)
/* 559:    */  {
/* 560:394 */    return h(paramFloat + 1.570796F);
/* 561:    */  }
/* 562:    */  
/* 566:    */  public static final float e(float paramFloat)
/* 567:    */  {
/* 568:402 */    return jdField_a_of_type_ArrayOfFloat[((int)(paramFloat * 10430.38F + 16384.0F) & 0xFFFF)];
/* 569:    */  }
/* 570:    */  
/* 610:    */  public static int a(int paramInt1, int paramInt2)
/* 611:    */  {
/* 612:446 */    if (paramInt2 == 0) {
/* 613:447 */      return 0;
/* 614:    */    }
/* 615:449 */    if (paramInt1 < 0) {
/* 616:450 */      return Math.abs(paramInt1 + 1) % paramInt2;
/* 617:    */    }
/* 618:452 */    return paramInt1 % paramInt2;
/* 619:    */  }
/* 620:    */  
/* 643:    */  public static int b(int paramInt1, int paramInt2)
/* 644:    */  {
/* 645:479 */    if (paramInt2 == 0) {
/* 646:480 */      return 0;
/* 647:    */    }
/* 648:482 */    if (paramInt1 < 0) {
/* 649:483 */      return paramInt2 - 1 - Math.abs(paramInt1 + 1) % paramInt2;
/* 650:    */    }
/* 651:485 */    return paramInt1 % paramInt2;
/* 652:    */  }
/* 653:    */  
/* 680:    */  public static float f(float paramFloat)
/* 681:    */  {
/* 682:516 */    return (float)Math.exp(paramFloat);
/* 683:    */  }
/* 684:    */  
/* 717:    */  public static boolean a(int paramInt)
/* 718:    */  {
/* 719:553 */    return (paramInt > 0) && ((paramInt & paramInt - 1) == 0);
/* 720:    */  }
/* 721:    */  
/* 739:    */  public static float g(float paramFloat)
/* 740:    */  {
/* 741:575 */    return (float)Math.log(paramFloat) / jdField_a_of_type_Float;
/* 742:    */  }
/* 743:    */  
/* 763:    */  public static void main(String[] paramArrayOfString)
/* 764:    */  {
/* 765:599 */    for (paramArrayOfString = 32; paramArrayOfString >= -32; paramArrayOfString--) {
/* 766:600 */      System.err.println(paramArrayOfString + ": " + a(paramArrayOfString, 2));
/* 767:    */    }
/* 768:602 */    System.err.println("-------------------------");
/* 769:603 */    for (paramArrayOfString = 32; paramArrayOfString >= -32; paramArrayOfString--) {
/* 770:604 */      System.err.println(paramArrayOfString + ": " + a(paramArrayOfString, 16));
/* 771:    */    }
/* 772:    */  }
/* 773:    */  
/* 783:    */  public static float a()
/* 784:    */  {
/* 785:619 */    return jdField_a_of_type_JavaUtilRandom.nextFloat();
/* 786:    */  }
/* 787:    */  
/* 861:    */  public static float b(float paramFloat1, float paramFloat2)
/* 862:    */  {
/* 863:697 */    return (float)Math.pow(paramFloat1, paramFloat2);
/* 864:    */  }
/* 865:    */  
/* 918:    */  public static double b(double paramDouble)
/* 919:    */  {
/* 920:754 */    return (1.0D / (1.0D + Math.pow(1.000696D, -paramDouble)) - 0.5D) * 2.0D * 1000000.0D;
/* 921:    */  }
/* 922:    */  
/* 931:    */  public static float h(float paramFloat)
/* 932:    */  {
/* 933:767 */    if (Math.abs(paramFloat %= 6.283186F) > 3.141593F) paramFloat -= 6.283186F; if (Math.abs(paramFloat) > 1.570796F) paramFloat = 3.141593F - paramFloat;
/* 934:768 */    if (Math.abs(paramFloat = paramFloat) <= 0.7853981633974483D) {
/* 935:769 */      return (float)Math.sin(paramFloat);
/* 936:    */    }
/* 937:    */    
/* 938:772 */    return (float)Math.cos(1.570796326794897D - paramFloat);
/* 939:    */  }
/* 940:    */  
/* 941:    */  public static final float i(float paramFloat)
/* 942:    */  {
/* 943:777 */    return jdField_b_of_type_ArrayOfFloat[((int)(paramFloat * 651.89862F) & 0xFFF)];
/* 944:    */  }
/* 945:    */  
/* 946:    */  public static final float j(float paramFloat)
/* 947:    */  {
/* 948:782 */    return c[((int)(paramFloat * 651.89862F) & 0xFFF)];
/* 949:    */  }
/* 950:    */  
/* 954:    */  public static final float k(float paramFloat)
/* 955:    */  {
/* 956:790 */    return jdField_a_of_type_ArrayOfFloat[((int)(paramFloat * 10430.38F) & 0xFFFF)];
/* 957:    */  }
/* 958:    */  
/* 1000:    */  public static float l(float paramFloat)
/* 1001:    */  {
/* 1002:836 */    return (float)Math.sqrt(paramFloat);
/* 1003:    */  }
/* 1004:    */  
/* 1014:    */  public static float m(float paramFloat)
/* 1015:    */  {
/* 1016:850 */    float f2 = 0.5F * paramFloat;
/* 1017:    */    
/* 1018:852 */    int i = Float.floatToIntBits(paramFloat);
/* 1019:    */    
/* 1020:    */    float f1;
/* 1021:    */    
/* 1022:856 */    return (f1 = Float.intBitsToFloat(1597463007 - (i >> 1))) * (1.5F - f2 * f1 * f1) * paramFloat;
/* 1023:    */  }
/* 1024:    */  
/* 1074:    */  public static int a(float paramFloat)
/* 1075:    */  {
/* 1076:910 */    return (int)(paramFloat + 16384.0D) - 16384;
/* 1077:    */  }
/* 1078:    */  
/* 1084:    */  public static int b(float paramFloat)
/* 1085:    */  {
/* 1086:920 */    return 16384 - (int)(16384.0D - paramFloat);
/* 1087:    */  }
/* 1088:    */  
/* 1095:    */  public static float n(float paramFloat)
/* 1096:    */  {
/* 1097:931 */    return (float)Math.tan(paramFloat);
/* 1098:    */  }
/* 1099:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.common.FastMath
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */