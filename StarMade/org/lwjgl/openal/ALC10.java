/*   1:    */package org.lwjgl.openal;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.IntBuffer;
/*   5:    */import java.util.HashMap;
/*   6:    */import org.lwjgl.BufferChecks;
/*   7:    */import org.lwjgl.LWJGLException;
/*   8:    */import org.lwjgl.MemoryUtil;
/*   9:    */
/*  59:    */public final class ALC10
/*  60:    */{
/*  61: 61 */  static final HashMap<Long, ALCcontext> contexts = new HashMap();
/*  62:    */  
/*  64: 64 */  static final HashMap<Long, ALCdevice> devices = new HashMap();
/*  65:    */  
/*  69:    */  public static final int ALC_INVALID = 0;
/*  70:    */  
/*  74:    */  public static final int ALC_FALSE = 0;
/*  75:    */  
/*  78:    */  public static final int ALC_TRUE = 1;
/*  79:    */  
/*  82:    */  public static final int ALC_NO_ERROR = 0;
/*  83:    */  
/*  86:    */  public static final int ALC_MAJOR_VERSION = 4096;
/*  87:    */  
/*  90:    */  public static final int ALC_MINOR_VERSION = 4097;
/*  91:    */  
/*  94:    */  public static final int ALC_ATTRIBUTES_SIZE = 4098;
/*  95:    */  
/*  98:    */  public static final int ALC_ALL_ATTRIBUTES = 4099;
/*  99:    */  
/* 102:    */  public static final int ALC_DEFAULT_DEVICE_SPECIFIER = 4100;
/* 103:    */  
/* 106:    */  public static final int ALC_DEVICE_SPECIFIER = 4101;
/* 107:    */  
/* 110:    */  public static final int ALC_EXTENSIONS = 4102;
/* 111:    */  
/* 114:    */  public static final int ALC_FREQUENCY = 4103;
/* 115:    */  
/* 118:    */  public static final int ALC_REFRESH = 4104;
/* 119:    */  
/* 122:    */  public static final int ALC_SYNC = 4105;
/* 123:    */  
/* 126:    */  public static final int ALC_INVALID_DEVICE = 40961;
/* 127:    */  
/* 130:    */  public static final int ALC_INVALID_CONTEXT = 40962;
/* 131:    */  
/* 134:    */  public static final int ALC_INVALID_ENUM = 40963;
/* 135:    */  
/* 138:    */  public static final int ALC_INVALID_VALUE = 40964;
/* 139:    */  
/* 142:    */  public static final int ALC_OUT_OF_MEMORY = 40965;
/* 143:    */  
/* 147:    */  static native void initNativeStubs()
/* 148:    */    throws LWJGLException;
/* 149:    */  
/* 153:    */  public static String alcGetString(ALCdevice device, int pname)
/* 154:    */  {
/* 155:155 */    ByteBuffer buffer = nalcGetString(getDevice(device), pname);
/* 156:156 */    Util.checkALCError(device);
/* 157:157 */    return MemoryUtil.decodeUTF8(buffer);
/* 158:    */  }
/* 159:    */  
/* 169:    */  static native ByteBuffer nalcGetString(long paramLong, int paramInt);
/* 170:    */  
/* 180:    */  public static void alcGetInteger(ALCdevice device, int pname, IntBuffer integerdata)
/* 181:    */  {
/* 182:182 */    BufferChecks.checkDirect(integerdata);
/* 183:183 */    nalcGetIntegerv(getDevice(device), pname, integerdata.remaining(), MemoryUtil.getAddress(integerdata));
/* 184:184 */    Util.checkALCError(device);
/* 185:    */  }
/* 186:    */  
/* 192:    */  static native void nalcGetIntegerv(long paramLong1, int paramInt1, int paramInt2, long paramLong2);
/* 193:    */  
/* 199:    */  public static ALCdevice alcOpenDevice(String devicename)
/* 200:    */  {
/* 201:201 */    ByteBuffer buffer = MemoryUtil.encodeUTF8(devicename);
/* 202:202 */    long device_address = nalcOpenDevice(MemoryUtil.getAddressSafe(buffer));
/* 203:203 */    if (device_address != 0L) {
/* 204:204 */      ALCdevice device = new ALCdevice(device_address);
/* 205:205 */      synchronized (devices) {
/* 206:206 */        devices.put(Long.valueOf(device_address), device);
/* 207:    */      }
/* 208:208 */      return device;
/* 209:    */    }
/* 210:210 */    return null;
/* 211:    */  }
/* 212:    */  
/* 217:    */  static native long nalcOpenDevice(long paramLong);
/* 218:    */  
/* 222:    */  public static boolean alcCloseDevice(ALCdevice device)
/* 223:    */  {
/* 224:224 */    boolean result = nalcCloseDevice(getDevice(device));
/* 225:225 */    synchronized (devices) {
/* 226:226 */      device.setInvalid();
/* 227:227 */      devices.remove(new Long(device.device));
/* 228:    */    }
/* 229:229 */    return result;
/* 230:    */  }
/* 231:    */  
/* 239:    */  static native boolean nalcCloseDevice(long paramLong);
/* 240:    */  
/* 248:    */  public static ALCcontext alcCreateContext(ALCdevice device, IntBuffer attrList)
/* 249:    */  {
/* 250:250 */    long context_address = nalcCreateContext(getDevice(device), MemoryUtil.getAddressSafe(attrList));
/* 251:251 */    Util.checkALCError(device);
/* 252:    */    
/* 253:253 */    if (context_address != 0L) {
/* 254:254 */      ALCcontext context = new ALCcontext(context_address);
/* 255:255 */      synchronized (contexts) {
/* 256:256 */        contexts.put(Long.valueOf(context_address), context);
/* 257:257 */        device.addContext(context);
/* 258:    */      }
/* 259:259 */      return context;
/* 260:    */    }
/* 261:261 */    return null;
/* 262:    */  }
/* 263:    */  
/* 270:    */  static native long nalcCreateContext(long paramLong1, long paramLong2);
/* 271:    */  
/* 278:    */  public static int alcMakeContextCurrent(ALCcontext context)
/* 279:    */  {
/* 280:280 */    return nalcMakeContextCurrent(getContext(context));
/* 281:    */  }
/* 282:    */  
/* 288:    */  static native int nalcMakeContextCurrent(long paramLong);
/* 289:    */  
/* 294:    */  public static void alcProcessContext(ALCcontext context)
/* 295:    */  {
/* 296:296 */    nalcProcessContext(getContext(context));
/* 297:    */  }
/* 298:    */  
/* 301:    */  static native void nalcProcessContext(long paramLong);
/* 302:    */  
/* 305:    */  public static ALCcontext alcGetCurrentContext()
/* 306:    */  {
/* 307:307 */    ALCcontext context = null;
/* 308:308 */    long context_address = nalcGetCurrentContext();
/* 309:309 */    if (context_address != 0L) {
/* 310:310 */      synchronized (contexts) {
/* 311:311 */        context = (ALCcontext)contexts.get(Long.valueOf(context_address));
/* 312:    */      }
/* 313:    */    }
/* 314:314 */    return context;
/* 315:    */  }
/* 316:    */  
/* 319:    */  static native long nalcGetCurrentContext();
/* 320:    */  
/* 322:    */  public static ALCdevice alcGetContextsDevice(ALCcontext context)
/* 323:    */  {
/* 324:324 */    ALCdevice device = null;
/* 325:325 */    long device_address = nalcGetContextsDevice(getContext(context));
/* 326:326 */    if (device_address != 0L) {
/* 327:327 */      synchronized (devices) {
/* 328:328 */        device = (ALCdevice)devices.get(Long.valueOf(device_address));
/* 329:    */      }
/* 330:    */    }
/* 331:331 */    return device;
/* 332:    */  }
/* 333:    */  
/* 339:    */  static native long nalcGetContextsDevice(long paramLong);
/* 340:    */  
/* 346:    */  public static void alcSuspendContext(ALCcontext context)
/* 347:    */  {
/* 348:348 */    nalcSuspendContext(getContext(context));
/* 349:    */  }
/* 350:    */  
/* 353:    */  static native void nalcSuspendContext(long paramLong);
/* 354:    */  
/* 357:    */  public static void alcDestroyContext(ALCcontext context)
/* 358:    */  {
/* 359:359 */    synchronized (contexts) {
/* 360:360 */      ALCdevice device = alcGetContextsDevice(context);
/* 361:361 */      nalcDestroyContext(getContext(context));
/* 362:362 */      device.removeContext(context);
/* 363:363 */      context.setInvalid();
/* 364:    */    }
/* 365:    */  }
/* 366:    */  
/* 374:    */  static native void nalcDestroyContext(long paramLong);
/* 375:    */  
/* 382:    */  public static int alcGetError(ALCdevice device)
/* 383:    */  {
/* 384:384 */    return nalcGetError(getDevice(device));
/* 385:    */  }
/* 386:    */  
/* 391:    */  static native int nalcGetError(long paramLong);
/* 392:    */  
/* 396:    */  public static boolean alcIsExtensionPresent(ALCdevice device, String extName)
/* 397:    */  {
/* 398:398 */    ByteBuffer buffer = MemoryUtil.encodeASCII(extName);
/* 399:399 */    boolean result = nalcIsExtensionPresent(getDevice(device), MemoryUtil.getAddress(buffer));
/* 400:400 */    Util.checkALCError(device);
/* 401:401 */    return result;
/* 402:    */  }
/* 403:    */  
/* 408:    */  private static native boolean nalcIsExtensionPresent(long paramLong1, long paramLong2);
/* 409:    */  
/* 414:    */  public static int alcGetEnumValue(ALCdevice device, String enumName)
/* 415:    */  {
/* 416:416 */    ByteBuffer buffer = MemoryUtil.encodeASCII(enumName);
/* 417:417 */    int result = nalcGetEnumValue(getDevice(device), MemoryUtil.getAddress(buffer));
/* 418:418 */    Util.checkALCError(device);
/* 419:419 */    return result;
/* 420:    */  }
/* 421:    */  
/* 422:    */  private static native int nalcGetEnumValue(long paramLong1, long paramLong2);
/* 423:    */  
/* 424:424 */  static long getDevice(ALCdevice device) { if (device != null) {
/* 425:425 */      Util.checkALCValidDevice(device);
/* 426:426 */      return device.device;
/* 427:    */    }
/* 428:428 */    return 0L;
/* 429:    */  }
/* 430:    */  
/* 431:    */  static long getContext(ALCcontext context) {
/* 432:432 */    if (context != null) {
/* 433:433 */      Util.checkALCValidContext(context);
/* 434:434 */      return context.context;
/* 435:    */    }
/* 436:436 */    return 0L;
/* 437:    */  }
/* 438:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.openal.ALC10
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */