/*   1:    */package org.lwjgl.util.mapped;
/*   2:    */
/*   3:    */import java.nio.BufferOverflowException;
/*   4:    */import java.nio.ByteBuffer;
/*   5:    */import org.lwjgl.LWJGLUtil;
/*   6:    */import org.lwjgl.MemoryUtil;
/*   7:    */
/*  53:    */public abstract class MappedObject
/*  54:    */{
/*  55: 55 */  static final boolean CHECKS = LWJGLUtil.getPrivilegedBoolean("org.lwjgl.util.mapped.Checks");
/*  56:    */  
/*  61:    */  public long baseAddress;
/*  62:    */  
/*  67:    */  public long viewAddress;
/*  68:    */  
/*  73:    */  ByteBuffer preventGC;
/*  74:    */  
/*  79: 79 */  public static int SIZEOF = -1;
/*  80:    */  
/*  83:    */  public int view;
/*  84:    */  
/*  88:    */  protected final long getViewAddress(int view)
/*  89:    */  {
/*  90: 90 */    throw new InternalError("type not registered");
/*  91:    */  }
/*  92:    */  
/*  93:    */  public final void setViewAddress(long address) {
/*  94: 94 */    if (CHECKS)
/*  95: 95 */      checkAddress(address);
/*  96: 96 */    this.viewAddress = address;
/*  97:    */  }
/*  98:    */  
/*  99:    */  final void checkAddress(long address) {
/* 100:100 */    long base = MemoryUtil.getAddress0(this.preventGC);
/* 101:101 */    int offset = (int)(address - base);
/* 102:102 */    if ((address < base) || (this.preventGC.capacity() < offset + getSizeof()))
/* 103:103 */      throw new IndexOutOfBoundsException(Integer.toString(offset / getSizeof()));
/* 104:    */  }
/* 105:    */  
/* 106:    */  final void checkRange(int bytes) {
/* 107:107 */    if (bytes < 0) {
/* 108:108 */      throw new IllegalArgumentException();
/* 109:    */    }
/* 110:110 */    if (this.preventGC.capacity() < this.viewAddress - MemoryUtil.getAddress0(this.preventGC) + bytes) {
/* 111:111 */      throw new BufferOverflowException();
/* 112:    */    }
/* 113:    */  }
/* 114:    */  
/* 120:    */  public final int getAlign()
/* 121:    */  {
/* 122:122 */    throw new InternalError("type not registered");
/* 123:    */  }
/* 124:    */  
/* 130:    */  public final int getSizeof()
/* 131:    */  {
/* 132:132 */    throw new InternalError("type not registered");
/* 133:    */  }
/* 134:    */  
/* 140:    */  public final int capacity()
/* 141:    */  {
/* 142:142 */    throw new InternalError("type not registered");
/* 143:    */  }
/* 144:    */  
/* 155:    */  public static <T extends MappedObject> T map(ByteBuffer bb)
/* 156:    */  {
/* 157:157 */    throw new InternalError("type not registered");
/* 158:    */  }
/* 159:    */  
/* 171:    */  public static <T extends MappedObject> T map(long address, int capacity)
/* 172:    */  {
/* 173:173 */    throw new InternalError("type not registered");
/* 174:    */  }
/* 175:    */  
/* 186:    */  public static <T extends MappedObject> T malloc(int elementCount)
/* 187:    */  {
/* 188:188 */    throw new InternalError("type not registered");
/* 189:    */  }
/* 190:    */  
/* 197:    */  public final <T extends MappedObject> T dup()
/* 198:    */  {
/* 199:199 */    throw new InternalError("type not registered");
/* 200:    */  }
/* 201:    */  
/* 206:    */  public final <T extends MappedObject> T slice()
/* 207:    */  {
/* 208:208 */    throw new InternalError("type not registered");
/* 209:    */  }
/* 210:    */  
/* 215:    */  public final void runViewConstructor()
/* 216:    */  {
/* 217:217 */    throw new InternalError("type not registered");
/* 218:    */  }
/* 219:    */  
/* 222:    */  public final void next()
/* 223:    */  {
/* 224:224 */    throw new InternalError("type not registered");
/* 225:    */  }
/* 226:    */  
/* 232:    */  public final <T extends MappedObject> void copyTo(T target)
/* 233:    */  {
/* 234:234 */    throw new InternalError("type not registered");
/* 235:    */  }
/* 236:    */  
/* 243:    */  public final <T extends MappedObject> void copyRange(T target, int instances)
/* 244:    */  {
/* 245:245 */    throw new InternalError("type not registered");
/* 246:    */  }
/* 247:    */  
/* 255:    */  public static <T extends MappedObject> Iterable<T> foreach(T mapped)
/* 256:    */  {
/* 257:257 */    return foreach(mapped, mapped.capacity());
/* 258:    */  }
/* 259:    */  
/* 267:    */  public static <T extends MappedObject> Iterable<T> foreach(T mapped, int elementCount)
/* 268:    */  {
/* 269:269 */    return new MappedForeach(mapped, elementCount);
/* 270:    */  }
/* 271:    */  
/* 273:    */  public final <T extends MappedObject> T[] asArray()
/* 274:    */  {
/* 275:275 */    throw new InternalError("type not registered");
/* 276:    */  }
/* 277:    */  
/* 282:    */  public final ByteBuffer backingByteBuffer()
/* 283:    */  {
/* 284:284 */    return this.preventGC;
/* 285:    */  }
/* 286:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.mapped.MappedObject
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */