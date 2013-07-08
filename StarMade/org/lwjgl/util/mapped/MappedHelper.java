/*   1:    */package org.lwjgl.util.mapped;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import org.lwjgl.LWJGLUtil;
/*   5:    */import org.lwjgl.MemoryUtil;
/*   6:    */import sun.misc.Unsafe;
/*   7:    */
/*  47:    */public class MappedHelper
/*  48:    */{
/*  49:    */  public static void setup(MappedObject mo, ByteBuffer buffer, int align, int sizeof)
/*  50:    */  {
/*  51: 51 */    if ((LWJGLUtil.CHECKS) && (mo.baseAddress != 0L)) {
/*  52: 52 */      throw new IllegalStateException("this method should not be called by user-code");
/*  53:    */    }
/*  54: 54 */    if ((LWJGLUtil.CHECKS) && (!buffer.isDirect()))
/*  55: 55 */      throw new IllegalArgumentException("bytebuffer must be direct");
/*  56: 56 */    mo.preventGC = buffer;
/*  57:    */    
/*  58: 58 */    if ((LWJGLUtil.CHECKS) && (align <= 0)) {
/*  59: 59 */      throw new IllegalArgumentException("invalid alignment");
/*  60:    */    }
/*  61: 61 */    if ((LWJGLUtil.CHECKS) && ((sizeof <= 0) || (sizeof % align != 0))) {
/*  62: 62 */      throw new IllegalStateException("sizeof not a multiple of alignment");
/*  63:    */    }
/*  64: 64 */    long addr = MemoryUtil.getAddress(buffer);
/*  65: 65 */    if ((LWJGLUtil.CHECKS) && (addr % align != 0L)) {
/*  66: 66 */      throw new IllegalStateException("buffer address not aligned on " + align + " bytes");
/*  67:    */    }
/*  68: 68 */    mo.baseAddress = (mo.viewAddress = addr);
/*  69:    */  }
/*  70:    */  
/*  71:    */  public static void checkAddress(long viewAddress, MappedObject mapped) {
/*  72: 72 */    mapped.checkAddress(viewAddress);
/*  73:    */  }
/*  74:    */  
/*  75:    */  public static void put_views(MappedSet2 set, int view) {
/*  76: 76 */    set.view(view);
/*  77:    */  }
/*  78:    */  
/*  79:    */  public static void put_views(MappedSet3 set, int view) {
/*  80: 80 */    set.view(view);
/*  81:    */  }
/*  82:    */  
/*  83:    */  public static void put_views(MappedSet4 set, int view) {
/*  84: 84 */    set.view(view);
/*  85:    */  }
/*  86:    */  
/*  87:    */  public static void put_view(MappedObject mapped, int view, int sizeof) {
/*  88: 88 */    mapped.setViewAddress(mapped.baseAddress + view * sizeof);
/*  89:    */  }
/*  90:    */  
/*  91:    */  public static int get_view(MappedObject mapped, int sizeof) {
/*  92: 92 */    return (int)(mapped.viewAddress - mapped.baseAddress) / sizeof;
/*  93:    */  }
/*  94:    */  
/*  95:    */  public static void put_view_shift(MappedObject mapped, int view, int sizeof_shift) {
/*  96: 96 */    mapped.setViewAddress(mapped.baseAddress + (view << sizeof_shift));
/*  97:    */  }
/*  98:    */  
/*  99:    */  public static int get_view_shift(MappedObject mapped, int sizeof_shift) {
/* 100:100 */    return (int)(mapped.viewAddress - mapped.baseAddress) >> sizeof_shift;
/* 101:    */  }
/* 102:    */  
/* 103:    */  public static void put_view_next(MappedObject mapped, int sizeof) {
/* 104:104 */    mapped.setViewAddress(mapped.viewAddress + sizeof);
/* 105:    */  }
/* 106:    */  
/* 107:    */  public static MappedObject dup(MappedObject src, MappedObject dst) {
/* 108:108 */    dst.baseAddress = src.baseAddress;
/* 109:109 */    dst.viewAddress = src.viewAddress;
/* 110:110 */    dst.preventGC = src.preventGC;
/* 111:111 */    return dst;
/* 112:    */  }
/* 113:    */  
/* 114:    */  public static MappedObject slice(MappedObject src, MappedObject dst) {
/* 115:115 */    dst.baseAddress = src.viewAddress;
/* 116:116 */    dst.viewAddress = src.viewAddress;
/* 117:117 */    dst.preventGC = src.preventGC;
/* 118:118 */    return dst;
/* 119:    */  }
/* 120:    */  
/* 121:    */  public static void copy(MappedObject src, MappedObject dst, int bytes) {
/* 122:122 */    if (MappedObject.CHECKS) {
/* 123:123 */      src.checkRange(bytes);
/* 124:124 */      dst.checkRange(bytes);
/* 125:    */    }
/* 126:    */    
/* 127:127 */    MappedObjectUnsafe.INSTANCE.copyMemory(src.viewAddress, dst.viewAddress, bytes);
/* 128:    */  }
/* 129:    */  
/* 130:    */  public static ByteBuffer newBuffer(long address, int capacity) {
/* 131:131 */    return MappedObjectUnsafe.newBuffer(address, capacity);
/* 132:    */  }
/* 133:    */  
/* 137:    */  public static void bput(byte value, long addr)
/* 138:    */  {
/* 139:139 */    MappedObjectUnsafe.INSTANCE.putByte(addr, value);
/* 140:    */  }
/* 141:    */  
/* 142:    */  public static void bput(MappedObject mapped, byte value, int fieldOffset) {
/* 143:143 */    MappedObjectUnsafe.INSTANCE.putByte(mapped.viewAddress + fieldOffset, value);
/* 144:    */  }
/* 145:    */  
/* 146:    */  public static byte bget(long addr) {
/* 147:147 */    return MappedObjectUnsafe.INSTANCE.getByte(addr);
/* 148:    */  }
/* 149:    */  
/* 150:    */  public static byte bget(MappedObject mapped, int fieldOffset) {
/* 151:151 */    return MappedObjectUnsafe.INSTANCE.getByte(mapped.viewAddress + fieldOffset);
/* 152:    */  }
/* 153:    */  
/* 154:    */  public static void bvput(byte value, long addr) {
/* 155:155 */    MappedObjectUnsafe.INSTANCE.putByteVolatile(null, addr, value);
/* 156:    */  }
/* 157:    */  
/* 158:    */  public static void bvput(MappedObject mapped, byte value, int fieldOffset) {
/* 159:159 */    MappedObjectUnsafe.INSTANCE.putByteVolatile(null, mapped.viewAddress + fieldOffset, value);
/* 160:    */  }
/* 161:    */  
/* 162:    */  public static byte bvget(long addr) {
/* 163:163 */    return MappedObjectUnsafe.INSTANCE.getByteVolatile(null, addr);
/* 164:    */  }
/* 165:    */  
/* 166:    */  public static byte bvget(MappedObject mapped, int fieldOffset) {
/* 167:167 */    return MappedObjectUnsafe.INSTANCE.getByteVolatile(null, mapped.viewAddress + fieldOffset);
/* 168:    */  }
/* 169:    */  
/* 171:    */  public static void sput(short value, long addr)
/* 172:    */  {
/* 173:173 */    MappedObjectUnsafe.INSTANCE.putShort(addr, value);
/* 174:    */  }
/* 175:    */  
/* 176:    */  public static void sput(MappedObject mapped, short value, int fieldOffset) {
/* 177:177 */    MappedObjectUnsafe.INSTANCE.putShort(mapped.viewAddress + fieldOffset, value);
/* 178:    */  }
/* 179:    */  
/* 180:    */  public static short sget(long addr) {
/* 181:181 */    return MappedObjectUnsafe.INSTANCE.getShort(addr);
/* 182:    */  }
/* 183:    */  
/* 184:    */  public static short sget(MappedObject mapped, int fieldOffset) {
/* 185:185 */    return MappedObjectUnsafe.INSTANCE.getShort(mapped.viewAddress + fieldOffset);
/* 186:    */  }
/* 187:    */  
/* 188:    */  public static void svput(short value, long addr) {
/* 189:189 */    MappedObjectUnsafe.INSTANCE.putShortVolatile(null, addr, value);
/* 190:    */  }
/* 191:    */  
/* 192:    */  public static void svput(MappedObject mapped, short value, int fieldOffset) {
/* 193:193 */    MappedObjectUnsafe.INSTANCE.putShortVolatile(null, mapped.viewAddress + fieldOffset, value);
/* 194:    */  }
/* 195:    */  
/* 196:    */  public static short svget(long addr) {
/* 197:197 */    return MappedObjectUnsafe.INSTANCE.getShortVolatile(null, addr);
/* 198:    */  }
/* 199:    */  
/* 200:    */  public static short svget(MappedObject mapped, int fieldOffset) {
/* 201:201 */    return MappedObjectUnsafe.INSTANCE.getShortVolatile(null, mapped.viewAddress + fieldOffset);
/* 202:    */  }
/* 203:    */  
/* 205:    */  public static void cput(char value, long addr)
/* 206:    */  {
/* 207:207 */    MappedObjectUnsafe.INSTANCE.putChar(addr, value);
/* 208:    */  }
/* 209:    */  
/* 210:    */  public static void cput(MappedObject mapped, char value, int fieldOffset) {
/* 211:211 */    MappedObjectUnsafe.INSTANCE.putChar(mapped.viewAddress + fieldOffset, value);
/* 212:    */  }
/* 213:    */  
/* 214:    */  public static char cget(long addr) {
/* 215:215 */    return MappedObjectUnsafe.INSTANCE.getChar(addr);
/* 216:    */  }
/* 217:    */  
/* 218:    */  public static char cget(MappedObject mapped, int fieldOffset) {
/* 219:219 */    return MappedObjectUnsafe.INSTANCE.getChar(mapped.viewAddress + fieldOffset);
/* 220:    */  }
/* 221:    */  
/* 222:    */  public static void cvput(char value, long addr) {
/* 223:223 */    MappedObjectUnsafe.INSTANCE.putCharVolatile(null, addr, value);
/* 224:    */  }
/* 225:    */  
/* 226:    */  public static void cvput(MappedObject mapped, char value, int fieldOffset) {
/* 227:227 */    MappedObjectUnsafe.INSTANCE.putCharVolatile(null, mapped.viewAddress + fieldOffset, value);
/* 228:    */  }
/* 229:    */  
/* 230:    */  public static char cvget(long addr) {
/* 231:231 */    return MappedObjectUnsafe.INSTANCE.getCharVolatile(null, addr);
/* 232:    */  }
/* 233:    */  
/* 234:    */  public static char cvget(MappedObject mapped, int fieldOffset) {
/* 235:235 */    return MappedObjectUnsafe.INSTANCE.getCharVolatile(null, mapped.viewAddress + fieldOffset);
/* 236:    */  }
/* 237:    */  
/* 239:    */  public static void iput(int value, long addr)
/* 240:    */  {
/* 241:241 */    MappedObjectUnsafe.INSTANCE.putInt(addr, value);
/* 242:    */  }
/* 243:    */  
/* 244:    */  public static void iput(MappedObject mapped, int value, int fieldOffset) {
/* 245:245 */    MappedObjectUnsafe.INSTANCE.putInt(mapped.viewAddress + fieldOffset, value);
/* 246:    */  }
/* 247:    */  
/* 248:    */  public static int iget(long address) {
/* 249:249 */    return MappedObjectUnsafe.INSTANCE.getInt(address);
/* 250:    */  }
/* 251:    */  
/* 252:    */  public static int iget(MappedObject mapped, int fieldOffset) {
/* 253:253 */    return MappedObjectUnsafe.INSTANCE.getInt(mapped.viewAddress + fieldOffset);
/* 254:    */  }
/* 255:    */  
/* 256:    */  public static void ivput(int value, long addr) {
/* 257:257 */    MappedObjectUnsafe.INSTANCE.putIntVolatile(null, addr, value);
/* 258:    */  }
/* 259:    */  
/* 260:    */  public static void ivput(MappedObject mapped, int value, int fieldOffset) {
/* 261:261 */    MappedObjectUnsafe.INSTANCE.putIntVolatile(null, mapped.viewAddress + fieldOffset, value);
/* 262:    */  }
/* 263:    */  
/* 264:    */  public static int ivget(long address) {
/* 265:265 */    return MappedObjectUnsafe.INSTANCE.getIntVolatile(null, address);
/* 266:    */  }
/* 267:    */  
/* 268:    */  public static int ivget(MappedObject mapped, int fieldOffset) {
/* 269:269 */    return MappedObjectUnsafe.INSTANCE.getIntVolatile(null, mapped.viewAddress + fieldOffset);
/* 270:    */  }
/* 271:    */  
/* 273:    */  public static void fput(float value, long addr)
/* 274:    */  {
/* 275:275 */    MappedObjectUnsafe.INSTANCE.putFloat(addr, value);
/* 276:    */  }
/* 277:    */  
/* 278:    */  public static void fput(MappedObject mapped, float value, int fieldOffset) {
/* 279:279 */    MappedObjectUnsafe.INSTANCE.putFloat(mapped.viewAddress + fieldOffset, value);
/* 280:    */  }
/* 281:    */  
/* 282:    */  public static float fget(long addr) {
/* 283:283 */    return MappedObjectUnsafe.INSTANCE.getFloat(addr);
/* 284:    */  }
/* 285:    */  
/* 286:    */  public static float fget(MappedObject mapped, int fieldOffset) {
/* 287:287 */    return MappedObjectUnsafe.INSTANCE.getFloat(mapped.viewAddress + fieldOffset);
/* 288:    */  }
/* 289:    */  
/* 290:    */  public static void fvput(float value, long addr) {
/* 291:291 */    MappedObjectUnsafe.INSTANCE.putFloatVolatile(null, addr, value);
/* 292:    */  }
/* 293:    */  
/* 294:    */  public static void fvput(MappedObject mapped, float value, int fieldOffset) {
/* 295:295 */    MappedObjectUnsafe.INSTANCE.putFloatVolatile(null, mapped.viewAddress + fieldOffset, value);
/* 296:    */  }
/* 297:    */  
/* 298:    */  public static float fvget(long addr) {
/* 299:299 */    return MappedObjectUnsafe.INSTANCE.getFloatVolatile(null, addr);
/* 300:    */  }
/* 301:    */  
/* 302:    */  public static float fvget(MappedObject mapped, int fieldOffset) {
/* 303:303 */    return MappedObjectUnsafe.INSTANCE.getFloatVolatile(null, mapped.viewAddress + fieldOffset);
/* 304:    */  }
/* 305:    */  
/* 307:    */  public static void jput(long value, long addr)
/* 308:    */  {
/* 309:309 */    MappedObjectUnsafe.INSTANCE.putLong(addr, value);
/* 310:    */  }
/* 311:    */  
/* 312:    */  public static void jput(MappedObject mapped, long value, int fieldOffset) {
/* 313:313 */    MappedObjectUnsafe.INSTANCE.putLong(mapped.viewAddress + fieldOffset, value);
/* 314:    */  }
/* 315:    */  
/* 316:    */  public static long jget(long addr) {
/* 317:317 */    return MappedObjectUnsafe.INSTANCE.getLong(addr);
/* 318:    */  }
/* 319:    */  
/* 320:    */  public static long jget(MappedObject mapped, int fieldOffset) {
/* 321:321 */    return MappedObjectUnsafe.INSTANCE.getLong(mapped.viewAddress + fieldOffset);
/* 322:    */  }
/* 323:    */  
/* 324:    */  public static void jvput(long value, long addr) {
/* 325:325 */    MappedObjectUnsafe.INSTANCE.putLongVolatile(null, addr, value);
/* 326:    */  }
/* 327:    */  
/* 328:    */  public static void jvput(MappedObject mapped, long value, int fieldOffset) {
/* 329:329 */    MappedObjectUnsafe.INSTANCE.putLongVolatile(null, mapped.viewAddress + fieldOffset, value);
/* 330:    */  }
/* 331:    */  
/* 332:    */  public static long jvget(long addr) {
/* 333:333 */    return MappedObjectUnsafe.INSTANCE.getLongVolatile(null, addr);
/* 334:    */  }
/* 335:    */  
/* 336:    */  public static long jvget(MappedObject mapped, int fieldOffset) {
/* 337:337 */    return MappedObjectUnsafe.INSTANCE.getLongVolatile(null, mapped.viewAddress + fieldOffset);
/* 338:    */  }
/* 339:    */  
/* 341:    */  public static void aput(long value, long addr)
/* 342:    */  {
/* 343:343 */    MappedObjectUnsafe.INSTANCE.putAddress(addr, value);
/* 344:    */  }
/* 345:    */  
/* 346:    */  public static void aput(MappedObject mapped, long value, int fieldOffset) {
/* 347:347 */    MappedObjectUnsafe.INSTANCE.putAddress(mapped.viewAddress + fieldOffset, value);
/* 348:    */  }
/* 349:    */  
/* 350:    */  public static long aget(long addr) {
/* 351:351 */    return MappedObjectUnsafe.INSTANCE.getAddress(addr);
/* 352:    */  }
/* 353:    */  
/* 354:    */  public static long aget(MappedObject mapped, int fieldOffset) {
/* 355:355 */    return MappedObjectUnsafe.INSTANCE.getAddress(mapped.viewAddress + fieldOffset);
/* 356:    */  }
/* 357:    */  
/* 359:    */  public static void dput(double value, long addr)
/* 360:    */  {
/* 361:361 */    MappedObjectUnsafe.INSTANCE.putDouble(addr, value);
/* 362:    */  }
/* 363:    */  
/* 364:    */  public static void dput(MappedObject mapped, double value, int fieldOffset) {
/* 365:365 */    MappedObjectUnsafe.INSTANCE.putDouble(mapped.viewAddress + fieldOffset, value);
/* 366:    */  }
/* 367:    */  
/* 368:    */  public static double dget(long addr) {
/* 369:369 */    return MappedObjectUnsafe.INSTANCE.getDouble(addr);
/* 370:    */  }
/* 371:    */  
/* 372:    */  public static double dget(MappedObject mapped, int fieldOffset) {
/* 373:373 */    return MappedObjectUnsafe.INSTANCE.getDouble(mapped.viewAddress + fieldOffset);
/* 374:    */  }
/* 375:    */  
/* 376:    */  public static void dvput(double value, long addr) {
/* 377:377 */    MappedObjectUnsafe.INSTANCE.putDoubleVolatile(null, addr, value);
/* 378:    */  }
/* 379:    */  
/* 380:    */  public static void dvput(MappedObject mapped, double value, int fieldOffset) {
/* 381:381 */    MappedObjectUnsafe.INSTANCE.putDoubleVolatile(null, mapped.viewAddress + fieldOffset, value);
/* 382:    */  }
/* 383:    */  
/* 384:    */  public static double dvget(long addr) {
/* 385:385 */    return MappedObjectUnsafe.INSTANCE.getDoubleVolatile(null, addr);
/* 386:    */  }
/* 387:    */  
/* 388:    */  public static double dvget(MappedObject mapped, int fieldOffset) {
/* 389:389 */    return MappedObjectUnsafe.INSTANCE.getDoubleVolatile(null, mapped.viewAddress + fieldOffset);
/* 390:    */  }
/* 391:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.mapped.MappedHelper
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */