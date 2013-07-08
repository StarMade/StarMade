/*   1:    */package org.schema.common.util;
/*   2:    */
/*   3:    */import java.io.InputStream;
/*   4:    */import java.io.OutputStream;
/*   5:    */import java.io.PrintStream;
/*   6:    */import java.nio.ByteBuffer;
/*   7:    */
/*  95:    */public class ByteUtil
/*  96:    */{
/*  97:    */  public static final byte[] a;
/*  98:    */  
/*  99:    */  public static final int a(int paramInt)
/* 100:    */  {
/* 101:101 */    if (paramInt >= 0) return paramInt >> 4; return -(-paramInt >> 4);
/* 102:    */  }
/* 103:    */  
/* 104:    */  public static final int b(int paramInt) {
/* 105:105 */    return paramInt >> 4;
/* 106:    */  }
/* 107:    */  
/* 108:108 */  public static final int c(int paramInt) { return paramInt >> 3; }
/* 109:    */  
/* 118:    */  public static int a(int paramInt1, int paramInt2, int paramInt3)
/* 119:    */  {
/* 120:120 */    paramInt3 = (1 << paramInt3 - paramInt2) - 1;
/* 121:121 */    return paramInt1 >> paramInt2 & paramInt3;
/* 122:    */  }
/* 123:    */  
/* 152:    */  public static float a(byte paramByte1, byte paramByte2, short paramShort, byte paramByte3, byte paramByte4, byte paramByte5, byte paramByte6)
/* 153:    */  {
/* 154:154 */    paramByte1 = paramByte1 << 2;
/* 155:155 */    paramByte2 = paramByte2 << 5;
/* 156:156 */    paramShort = paramShort << 9;
/* 157:157 */    paramByte3 = paramByte3 << 17;
/* 158:158 */    paramByte4 = paramByte4 << 20;
/* 159:159 */    paramByte5 = paramByte5 << 21;
/* 160:160 */    paramByte6 = paramByte6 << 22;
/* 161:    */    
/* 164:164 */    return paramByte1 + paramByte2 + paramShort + paramByte3 + paramByte4 + paramByte5 + paramByte6;
/* 165:    */  }
/* 166:    */  
/* 170:    */  public static float a(float paramFloat, byte paramByte1, byte paramByte2, byte paramByte3)
/* 171:    */  {
/* 172:172 */    paramByte1 = paramByte1 << 12;
/* 173:    */    
/* 174:174 */    paramByte2 = paramByte2 << 16;
/* 175:175 */    paramByte3 = paramByte3 << 20;
/* 176:    */    
/* 177:177 */    return paramFloat + paramByte1 + paramByte2 + paramByte3;
/* 178:    */  }
/* 179:    */  
/* 183:    */  public static int a(byte[] paramArrayOfByte, int paramInt)
/* 184:    */  {
/* 185:185 */    int i = 0;
/* 186:186 */    for (int j = 0; j < 3; j++)
/* 187:    */    {
/* 188:188 */      i = i << 8 ^ paramArrayOfByte[(paramInt + j)] & 0xFF;
/* 189:    */    }
/* 190:    */    
/* 191:191 */    return i;
/* 192:    */  }
/* 193:    */  
/* 207:    */  public static byte[] a(int paramInt)
/* 208:    */  {
/* 209:209 */    byte[] arrayOfByte = new byte[4];
/* 210:210 */    for (int i = 0; i < 4; i++) {
/* 211:211 */      int j = arrayOfByte.length - 1 - i << 3;
/* 212:212 */      arrayOfByte[i] = ((byte)(paramInt >>> j));
/* 213:    */    }
/* 214:214 */    return arrayOfByte;
/* 215:    */  }
/* 216:    */  
/* 222:    */  private static void a(int paramInt1, byte[] paramArrayOfByte, int paramInt2)
/* 223:    */  {
/* 224:224 */    paramArrayOfByte[paramInt2] = ((byte)(paramInt1 >>> 16));
/* 225:225 */    paramArrayOfByte[(paramInt2 + 1)] = ((byte)(paramInt1 >>> 8));
/* 226:226 */    paramArrayOfByte[(paramInt2 + 2)] = ((byte)paramInt1);
/* 227:    */  }
/* 228:    */  
/* 234:    */  public static byte[] a(long paramLong)
/* 235:    */  {
/* 236:    */    byte[] arrayOfByte;
/* 237:    */    
/* 242:242 */    ByteBuffer.wrap(arrayOfByte = new byte[8]).putLong(paramLong);
/* 243:    */    
/* 244:244 */    return arrayOfByte;
/* 245:    */  }
/* 246:    */  
/* 250:    */  public static void main(String[] paramArrayOfString)
/* 251:    */  {
/* 252:252 */    for (paramArrayOfString = 0.0F; paramArrayOfString < 6.0F; paramArrayOfString += 1.0F)
/* 253:    */    {
/* 254:254 */      float f1 = Math.max(0.0F, (float)Math.floor(paramArrayOfString) % 2.0F - (paramArrayOfString - 1.0F) - (paramArrayOfString + 1.0F) % 2.0F);
/* 255:255 */      float f2 = Math.max(0.0F, (float)Math.floor(paramArrayOfString) % 2.0F - (paramArrayOfString - 3.0F) - (paramArrayOfString + 1.0F) % 2.0F);
/* 256:256 */      float f3 = Math.max(0.0F, (float)Math.floor(paramArrayOfString) % 2.0F - (paramArrayOfString - 5.0F) - (paramArrayOfString + 1.0F) % 2.0F);
/* 257:    */      
/* 258:258 */      System.err.println(f1 + ", " + f2 + ", " + f3);
/* 259:    */    }
/* 260:    */  }
/* 261:    */  
/* 269:    */  public static final int d(int paramInt)
/* 270:    */  {
/* 271:271 */    return paramInt & 0xF;
/* 272:    */  }
/* 273:    */  
/* 274:    */  static
/* 275:    */  {
/* 276: 12 */    ByteUtil.class.desiredAssertionStatus();
/* 277:    */    
/* 543:279 */    int i = (ByteUtil.a = new byte[24576]).length / 3;
/* 544:280 */    for (int j = 0; j < i; j = (short)(j + 1)) {
/* 545:281 */      int k = j * 3;
/* 546:    */      
/* 548:284 */      a(a(a(a, k), j, 0, 11), a, k);
/* 549:    */    }
/* 550:    */  }
/* 551:    */  
/* 552:    */  public static void a(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/* 553:    */  {
/* 554:290 */    a(a(a(paramArrayOfByte, paramInt4), paramInt1, paramInt2, paramInt3), paramArrayOfByte, paramInt4);
/* 555:    */  }
/* 556:    */  
/* 562:    */  private static int a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/* 563:    */  {
/* 564:300 */    paramInt4 = (1 << paramInt4 - paramInt3) - 1 << paramInt3;
/* 565:    */    
/* 566:302 */    return paramInt1 & (paramInt4 ^ 0xFFFFFFFF) | paramInt2 << paramInt3 & paramInt4;
/* 567:    */  }
/* 568:    */  
/* 600:    */  public static int a(InputStream paramInputStream)
/* 601:    */  {
/* 602:338 */    int i = 0;
/* 603:    */    
/* 604:340 */    for (int j = 0; j < 4; j++) {
/* 605:341 */      i <<= 8;
/* 606:342 */      int k = 0;
/* 607:343 */      while ((k = paramInputStream.read()) == -1) {}
/* 608:    */      
/* 611:347 */      i ^= k & 0xFF;
/* 612:    */    }
/* 613:    */    
/* 614:350 */    return i;
/* 615:    */  }
/* 616:    */  
/* 617:    */  public static long a(InputStream paramInputStream) {
/* 618:354 */    long l = 0L;
/* 619:355 */    for (int i = 0; i < 8; i++) {
/* 620:356 */      l <<= 8;
/* 621:357 */      int j = 0;
/* 622:358 */      while ((j = paramInputStream.read()) == -1) {}
/* 623:    */      
/* 624:360 */      l ^= j & 0xFF;
/* 625:    */    }
/* 626:362 */    return l;
/* 627:    */  }
/* 628:    */  
/* 655:    */  public static short a(byte[] paramArrayOfByte, int paramInt)
/* 656:    */  {
/* 657:393 */    short s = 0;
/* 658:    */    
/* 659:395 */    for (int i = 0; i < 2; i++)
/* 660:    */    {
/* 661:397 */      s = (short)((short)(s << 8) ^ paramArrayOfByte[(paramInt + i)] & 0xFF);
/* 662:    */    }
/* 663:    */    
/* 664:400 */    return s;
/* 665:    */  }
/* 666:    */  
/* 688:    */  public static void a(short paramShort, byte[] paramArrayOfByte, int paramInt)
/* 689:    */  {
/* 690:426 */    for (int i = 0; i < 2; i++) {
/* 691:427 */      short s = 1 - i << 3;
/* 692:428 */      paramArrayOfByte[(paramInt + i)] = ((byte)(paramShort >>> s));
/* 693:    */    }
/* 694:    */  }
/* 695:    */  
/* 877:    */  public static void a(int paramInt, OutputStream paramOutputStream)
/* 878:    */  {
/* 879:615 */    for (int i = 0; i < 4; i++) {
/* 880:616 */      int j = 3 - i << 3;
/* 881:617 */      paramOutputStream.write((byte)(paramInt >>> j));
/* 882:    */    }
/* 883:    */  }
/* 884:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.common.util.ByteUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */