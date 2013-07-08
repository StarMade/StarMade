/*   1:    */package org.schema.game.client.view.cubes;
/*   2:    */
/*   3:    */import dO;
/*   4:    */import java.io.PrintStream;
/*   5:    */import java.nio.FloatBuffer;
/*   6:    */import java.nio.IntBuffer;
/*   7:    */import java.nio.ShortBuffer;
/*   8:    */import org.lwjgl.BufferUtils;
/*   9:    */import org.schema.common.FastMath;
/*  10:    */import org.schema.common.util.ByteUtil;
/*  11:    */import org.schema.game.common.data.element.ElementInformation;
/*  12:    */import org.schema.game.common.data.element.ElementKeyMap;
/*  13:    */import org.schema.game.common.data.world.Segment;
/*  14:    */import q;
/*  15:    */import zk;
/*  16:    */
/*  17:    */public class CubeMeshBufferContainer
/*  18:    */{
/*  19:    */  public ShortBuffer a;
/*  20:    */  public FloatBuffer a;
/*  21:    */  public IntBuffer a;
/*  22:    */  public ShortBuffer b;
/*  23:    */  private java.nio.ByteBuffer jdField_a_of_type_JavaNioByteBuffer;
/*  24:    */  private java.nio.ByteBuffer jdField_b_of_type_JavaNioByteBuffer;
/*  25:    */  public static final int a;
/*  26:    */  
/*  27:    */  public static int a(byte paramByte1, byte paramByte2, byte paramByte3)
/*  28:    */  {
/*  29: 29 */    return ((paramByte3 << 8) + (paramByte2 << 4) + paramByte1) * 3 * 24;
/*  30:    */  }
/*  31:    */  
/*  50: 50 */  static { jdField_a_of_type_Int = zk.jdField_a_of_type_Int; }
/*  51:    */  
/*  52:    */  public static CubeMeshBufferContainer a() { CubeMeshBufferContainer localCubeMeshBufferContainer1;
/*  53: 53 */    CubeMeshBufferContainer localCubeMeshBufferContainer2; (localCubeMeshBufferContainer2 = localCubeMeshBufferContainer1 = new CubeMeshBufferContainer()).jdField_a_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(4096);localCubeMeshBufferContainer2.jdField_b_of_type_JavaNioShortBuffer = BufferUtils.createShortBuffer(4096);localCubeMeshBufferContainer2.jdField_a_of_type_JavaNioByteBuffer = BufferUtils.createByteBuffer(294912);localCubeMeshBufferContainer2.jdField_b_of_type_JavaNioByteBuffer = BufferUtils.createByteBuffer(4096);localCubeMeshBufferContainer2.jdField_a_of_type_JavaNioShortBuffer = BufferUtils.createShortBuffer(4096);localCubeMeshBufferContainer2.jdField_a_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(49152 * jdField_a_of_type_Int);localCubeMeshBufferContainer2.jdField_a_of_type_JavaNioFloatBuffer.rewind();
/*  54: 54 */    return localCubeMeshBufferContainer1;
/*  55:    */  }
/*  56:    */  
/*  57: 57 */  public static void main(String[] paramArrayOfString) { System.err.println("index 96 kb");
/*  58:    */    
/*  59: 59 */    System.err.println("attrib 384 kb");
/*  60:    */    
/*  61: 61 */    System.err.println("other " + 196608 * jdField_a_of_type_Int / 1024 + " kb");
/*  62:    */    
/*  66: 66 */    paramArrayOfString = (float)Math.floor(8.53125D);
/*  67:    */    
/*  68: 68 */    float f1 = (float)Math.floor((2184.0D - paramArrayOfString * 256.0D) / 16.0D);
/*  69: 69 */    float f2 = 2184.0F - (f1 * 16.0F + paramArrayOfString * 256.0F);
/*  70:    */    
/*  71: 71 */    System.err.println("2184.0: " + f2 + ", " + f1 + ", " + paramArrayOfString);
/*  72:    */  }
/*  73:    */  
/*  91: 91 */  public final byte a(int paramInt1, int paramInt2, int paramInt3) { return this.jdField_a_of_type_JavaNioByteBuffer.get(paramInt1 + paramInt2 * 3 + paramInt3); }
/*  92:    */  
/*  93:    */  public final byte a(byte paramByte1, byte paramByte2, byte paramByte3) {
/*  94: 94 */    paramByte1 = paramByte1 + (paramByte2 << 4) + (paramByte3 << 8);
/*  95: 95 */    return this.jdField_b_of_type_JavaNioByteBuffer.get(paramByte1);
/*  96:    */  }
/*  97:    */  
/* 107:    */  public final void a()
/* 108:    */  {
/* 109:109 */    for (int i = 0; i < 4096; i++)
/* 110:110 */      this.jdField_b_of_type_JavaNioByteBuffer.put(i, (byte)0);
/* 111:    */  }
/* 112:    */  
/* 113:    */  public final void a(int paramInt1, byte paramByte, int paramInt2, int paramInt3) {
/* 114:114 */    this.jdField_a_of_type_JavaNioByteBuffer.put(paramInt1 + paramInt2 * 3 + paramInt3, paramByte);
/* 115:    */  }
/* 116:    */  
/* 121:    */  public final void a(int paramInt, byte paramByte)
/* 122:    */  {
/* 123:123 */    this.jdField_b_of_type_JavaNioByteBuffer.put(paramInt, paramByte);
/* 124:    */  }
/* 125:    */  
/* 129:129 */  private static final int[][] jdField_a_of_type_Array2dOfInt = { { 0, 1, 2, 3, 5, 4 }, { 1, 0, 3, 2, 4, 5 }, { 1, 0, 5, 4, 2, 3 }, { 0, 1, 4, 5, 3, 2 }, { 4, 5, 2, 3, 0, 1 }, { 5, 4, 2, 3, 1, 0 } };
/* 130:    */  
/* 143:143 */  private static final int[][] jdField_b_of_type_Array2dOfInt = { { 3, 3, 4, 5, 3, 3 }, { 3, 3, 5, 4, 3, 3 }, { 3, 3, 4, 5, 3, 3 }, { 3, 3, 5, 4, 3, 3 }, { 3, 3, 4, 5, 3, 3 }, { 3, 3, 4, 5, 3, 3 } };
/* 144:    */  
/* 155:    */  public static byte a(int paramInt1, int paramInt2)
/* 156:    */  {
/* 157:157 */    return (byte)(5 - jdField_b_of_type_Array2dOfInt[paramInt2][paramInt1]);
/* 158:    */  }
/* 159:    */  
/* 160:    */  public static byte b(int paramInt1, int paramInt2)
/* 161:    */  {
/* 162:162 */    return (byte)(5 - jdField_a_of_type_Array2dOfInt[paramInt2][paramInt1]);
/* 163:    */  }
/* 164:    */  
/* 167:    */  public static final int a(CubeMeshBufferContainer paramCubeMeshBufferContainer, int paramInt1, int paramInt2, org.schema.game.common.data.world.SegmentData paramSegmentData, int paramInt3, int paramInt4, short paramShort)
/* 168:    */  {
/* 169:169 */    int i = org.schema.game.common.data.world.SegmentData.getLightInfoIndexFromIndex(paramInt2);
/* 170:    */    
/* 172:172 */    int j = (paramShort = ElementKeyMap.getInfo(paramShort)).getIndividualSides();
/* 173:173 */    boolean bool = paramShort.isAnimated();
/* 174:174 */    int m = paramShort.getBlockStyle();
/* 175:175 */    int n = paramSegmentData.getOrientation(paramInt2);
/* 176:    */    
/* 179:179 */    if ((!paramSegmentData.isActive(paramInt2)) && ((m == 1) || (m == 2))) {
/* 180:180 */      if ((!jdField_a_of_type_Boolean) && (paramShort.canActivate())) throw new AssertionError();
/* 181:181 */      n = (byte)(n + 8);
/* 182:    */    }
/* 183:    */    
/* 187:187 */    int i1 = 0;
/* 188:    */    
/* 189:189 */    if (j == 6) {
/* 190:190 */      n = (byte)Math.max(0, Math.min(5, n));
/* 191:191 */      if ((!jdField_a_of_type_Boolean) && (n >= 6)) throw new AssertionError("Orientation wrong: " + n);
/* 192:192 */      i1 = b(paramInt4, n);
/* 193:193 */    } else if (j == 3) {
/* 194:194 */      n = (byte)Math.max(0, Math.min(5, n));
/* 195:195 */      if ((!jdField_a_of_type_Boolean) && (n >= 6)) throw new AssertionError("Orientation wrong: " + n);
/* 196:196 */      i1 = a(paramInt4, n);
/* 197:    */    }
/* 198:    */    
/* 202:202 */    float f = paramSegmentData.getHitpoints(paramInt2) / paramShort.getMaxHitPoints();
/* 203:203 */    byte b1 = 0;
/* 204:    */    
/* 205:205 */    if (f < 1.0F)
/* 206:    */    {
/* 207:207 */      b1 = FastMath.a((byte)(int)((1.0F - f) * 7.0F));
/* 208:    */    }
/* 209:    */    
/* 212:212 */    if ((paramCubeMeshBufferContainer.jdField_b_of_type_JavaNioByteBuffer.get(paramInt2 / 3) & paramInt3) == paramInt3) { dO.a();
/* 213:213 */      paramInt2 = paramInt4 << 2;
/* 214:    */      
/* 215:215 */      paramInt3 = 0;
/* 216:216 */      if ((bool) && (
/* 217:217 */        (j != 3) || (
/* 218:218 */        (paramInt4 != 2) && (paramInt4 != 3)))) {
/* 219:219 */        paramInt3 = 1;
/* 220:    */      }
/* 221:    */      
/* 226:226 */      j = (byte)(Math.abs(paramShort.getTextureId() + i1) / 256);
/* 227:227 */      paramShort = (short)((paramShort.getTextureId() + i1) % 256);
/* 228:    */      
/* 231:231 */      int k = ByteUtil.b(paramSegmentData.getSegment().a.jdField_a_of_type_Int) + 128;
/* 232:232 */      i1 = ByteUtil.b(paramSegmentData.getSegment().a.b) + 128;
/* 233:    */      
/* 235:235 */      paramSegmentData = (ByteUtil.b(paramSegmentData.getSegment().a.c) + 128 << 16) + (i1 << 8) + k;
/* 236:    */      
/* 237:237 */      if (m > 0)
/* 238:    */      {
/* 239:239 */        dO.a[(m - 1)][n].a(paramInt4, j, paramShort, b1, paramInt3, i, paramInt2, paramInt1, paramSegmentData, paramCubeMeshBufferContainer);
/* 240:    */      } else {
/* 241:241 */        dO.b(paramInt4, j, paramShort, b1, paramInt3, i, paramInt2, paramInt1, paramSegmentData, paramCubeMeshBufferContainer);
/* 242:    */      }
/* 243:    */      
/* 245:245 */      return 4;
/* 246:    */    }
/* 247:247 */    return 0;
/* 248:    */  }
/* 249:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.client.view.cubes.CubeMeshBufferContainer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */