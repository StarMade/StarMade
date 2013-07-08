/*   1:    */package org.lwjgl.util.mapped;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.ByteOrder;
/*   5:    */import java.nio.CharBuffer;
/*   6:    */import java.nio.DoubleBuffer;
/*   7:    */import java.nio.FloatBuffer;
/*   8:    */import java.nio.IntBuffer;
/*   9:    */import java.nio.LongBuffer;
/*  10:    */import java.nio.ShortBuffer;
/*  11:    */import org.lwjgl.LWJGLUtil;
/*  12:    */import org.lwjgl.MemoryUtil;
/*  13:    */import org.lwjgl.PointerBuffer;
/*  14:    */
/*  49:    */public final class CacheUtil
/*  50:    */{
/*  51:    */  private static final int CACHE_LINE_SIZE;
/*  52:    */  
/*  53:    */  static
/*  54:    */  {
/*  55: 55 */    Integer size = LWJGLUtil.getPrivilegedInteger("org.lwjgl.util.mapped.CacheLineSize");
/*  56:    */    
/*  57: 57 */    if (size != null) {
/*  58: 58 */      if (size.intValue() < 1)
/*  59: 59 */        throw new IllegalStateException("Invalid CacheLineSize specified: " + size);
/*  60: 60 */      CACHE_LINE_SIZE = size.intValue();
/*  61: 61 */    } else if (Runtime.getRuntime().availableProcessors() == 1)
/*  62:    */    {
/*  70: 70 */      if (LWJGLUtil.DEBUG)
/*  71: 71 */        LWJGLUtil.log("Cannot detect cache line size on single-core CPUs, assuming 64 bytes.");
/*  72: 72 */      CACHE_LINE_SIZE = 64;
/*  73:    */    } else {
/*  74: 74 */      CACHE_LINE_SIZE = CacheLineSize.getCacheLineSize();
/*  75:    */    }
/*  76:    */  }
/*  77:    */  
/*  84:    */  public static int getCacheLineSize()
/*  85:    */  {
/*  86: 86 */    return CACHE_LINE_SIZE;
/*  87:    */  }
/*  88:    */  
/*  95:    */  public static ByteBuffer createByteBuffer(int size)
/*  96:    */  {
/*  97: 97 */    ByteBuffer buffer = ByteBuffer.allocateDirect(size + CACHE_LINE_SIZE);
/*  98:    */    
/* 100:100 */    if (MemoryUtil.getAddress(buffer) % CACHE_LINE_SIZE != 0L)
/* 101:    */    {
/* 102:102 */      buffer.position(CACHE_LINE_SIZE - (int)(MemoryUtil.getAddress(buffer) & CACHE_LINE_SIZE - 1));
/* 103:    */    }
/* 104:    */    
/* 105:105 */    buffer.limit(buffer.position() + size);
/* 106:106 */    return buffer.slice().order(ByteOrder.nativeOrder());
/* 107:    */  }
/* 108:    */  
/* 116:    */  public static ShortBuffer createShortBuffer(int size)
/* 117:    */  {
/* 118:118 */    return createByteBuffer(size << 1).asShortBuffer();
/* 119:    */  }
/* 120:    */  
/* 128:    */  public static CharBuffer createCharBuffer(int size)
/* 129:    */  {
/* 130:130 */    return createByteBuffer(size << 1).asCharBuffer();
/* 131:    */  }
/* 132:    */  
/* 140:    */  public static IntBuffer createIntBuffer(int size)
/* 141:    */  {
/* 142:142 */    return createByteBuffer(size << 2).asIntBuffer();
/* 143:    */  }
/* 144:    */  
/* 152:    */  public static LongBuffer createLongBuffer(int size)
/* 153:    */  {
/* 154:154 */    return createByteBuffer(size << 3).asLongBuffer();
/* 155:    */  }
/* 156:    */  
/* 164:    */  public static FloatBuffer createFloatBuffer(int size)
/* 165:    */  {
/* 166:166 */    return createByteBuffer(size << 2).asFloatBuffer();
/* 167:    */  }
/* 168:    */  
/* 176:    */  public static DoubleBuffer createDoubleBuffer(int size)
/* 177:    */  {
/* 178:178 */    return createByteBuffer(size << 3).asDoubleBuffer();
/* 179:    */  }
/* 180:    */  
/* 188:    */  public static PointerBuffer createPointerBuffer(int size)
/* 189:    */  {
/* 190:190 */    return new PointerBuffer(createByteBuffer(size * PointerBuffer.getPointerSize()));
/* 191:    */  }
/* 192:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.mapped.CacheUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */