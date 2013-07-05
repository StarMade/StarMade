/*     */ package org.lwjgl.openal;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.util.HashMap;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.LWJGLException;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ public final class ALC10
/*     */ {
/*  61 */   static final HashMap<Long, ALCcontext> contexts = new HashMap();
/*     */ 
/*  64 */   static final HashMap<Long, ALCdevice> devices = new HashMap();
/*     */   public static final int ALC_INVALID = 0;
/*     */   public static final int ALC_FALSE = 0;
/*     */   public static final int ALC_TRUE = 1;
/*     */   public static final int ALC_NO_ERROR = 0;
/*     */   public static final int ALC_MAJOR_VERSION = 4096;
/*     */   public static final int ALC_MINOR_VERSION = 4097;
/*     */   public static final int ALC_ATTRIBUTES_SIZE = 4098;
/*     */   public static final int ALC_ALL_ATTRIBUTES = 4099;
/*     */   public static final int ALC_DEFAULT_DEVICE_SPECIFIER = 4100;
/*     */   public static final int ALC_DEVICE_SPECIFIER = 4101;
/*     */   public static final int ALC_EXTENSIONS = 4102;
/*     */   public static final int ALC_FREQUENCY = 4103;
/*     */   public static final int ALC_REFRESH = 4104;
/*     */   public static final int ALC_SYNC = 4105;
/*     */   public static final int ALC_INVALID_DEVICE = 40961;
/*     */   public static final int ALC_INVALID_CONTEXT = 40962;
/*     */   public static final int ALC_INVALID_ENUM = 40963;
/*     */   public static final int ALC_INVALID_VALUE = 40964;
/*     */   public static final int ALC_OUT_OF_MEMORY = 40965;
/*     */ 
/*     */   static native void initNativeStubs()
/*     */     throws LWJGLException;
/*     */ 
/*     */   public static String alcGetString(ALCdevice device, int pname)
/*     */   {
/* 155 */     ByteBuffer buffer = nalcGetString(getDevice(device), pname);
/* 156 */     Util.checkALCError(device);
/* 157 */     return MemoryUtil.decodeUTF8(buffer);
/*     */   }
/*     */ 
/*     */   static native ByteBuffer nalcGetString(long paramLong, int paramInt);
/*     */ 
/*     */   public static void alcGetInteger(ALCdevice device, int pname, IntBuffer integerdata)
/*     */   {
/* 182 */     BufferChecks.checkDirect(integerdata);
/* 183 */     nalcGetIntegerv(getDevice(device), pname, integerdata.remaining(), MemoryUtil.getAddress(integerdata));
/* 184 */     Util.checkALCError(device);
/*     */   }
/*     */ 
/*     */   static native void nalcGetIntegerv(long paramLong1, int paramInt1, int paramInt2, long paramLong2);
/*     */ 
/*     */   public static ALCdevice alcOpenDevice(String devicename)
/*     */   {
/* 201 */     ByteBuffer buffer = MemoryUtil.encodeUTF8(devicename);
/* 202 */     long device_address = nalcOpenDevice(MemoryUtil.getAddressSafe(buffer));
/* 203 */     if (device_address != 0L) {
/* 204 */       ALCdevice device = new ALCdevice(device_address);
/* 205 */       synchronized (devices) {
/* 206 */         devices.put(Long.valueOf(device_address), device);
/*     */       }
/* 208 */       return device;
/*     */     }
/* 210 */     return null;
/*     */   }
/*     */ 
/*     */   static native long nalcOpenDevice(long paramLong);
/*     */ 
/*     */   public static boolean alcCloseDevice(ALCdevice device)
/*     */   {
/* 224 */     boolean result = nalcCloseDevice(getDevice(device));
/* 225 */     synchronized (devices) {
/* 226 */       device.setInvalid();
/* 227 */       devices.remove(new Long(device.device));
/*     */     }
/* 229 */     return result;
/*     */   }
/*     */ 
/*     */   static native boolean nalcCloseDevice(long paramLong);
/*     */ 
/*     */   public static ALCcontext alcCreateContext(ALCdevice device, IntBuffer attrList)
/*     */   {
/* 250 */     long context_address = nalcCreateContext(getDevice(device), MemoryUtil.getAddressSafe(attrList));
/* 251 */     Util.checkALCError(device);
/*     */ 
/* 253 */     if (context_address != 0L) {
/* 254 */       ALCcontext context = new ALCcontext(context_address);
/* 255 */       synchronized (contexts) {
/* 256 */         contexts.put(Long.valueOf(context_address), context);
/* 257 */         device.addContext(context);
/*     */       }
/* 259 */       return context;
/*     */     }
/* 261 */     return null;
/*     */   }
/*     */ 
/*     */   static native long nalcCreateContext(long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int alcMakeContextCurrent(ALCcontext context)
/*     */   {
/* 280 */     return nalcMakeContextCurrent(getContext(context));
/*     */   }
/*     */ 
/*     */   static native int nalcMakeContextCurrent(long paramLong);
/*     */ 
/*     */   public static void alcProcessContext(ALCcontext context)
/*     */   {
/* 296 */     nalcProcessContext(getContext(context));
/*     */   }
/*     */ 
/*     */   static native void nalcProcessContext(long paramLong);
/*     */ 
/*     */   public static ALCcontext alcGetCurrentContext()
/*     */   {
/* 307 */     ALCcontext context = null;
/* 308 */     long context_address = nalcGetCurrentContext();
/* 309 */     if (context_address != 0L) {
/* 310 */       synchronized (contexts) {
/* 311 */         context = (ALCcontext)contexts.get(Long.valueOf(context_address));
/*     */       }
/*     */     }
/* 314 */     return context;
/*     */   }
/*     */ 
/*     */   static native long nalcGetCurrentContext();
/*     */ 
/*     */   public static ALCdevice alcGetContextsDevice(ALCcontext context)
/*     */   {
/* 324 */     ALCdevice device = null;
/* 325 */     long device_address = nalcGetContextsDevice(getContext(context));
/* 326 */     if (device_address != 0L) {
/* 327 */       synchronized (devices) {
/* 328 */         device = (ALCdevice)devices.get(Long.valueOf(device_address));
/*     */       }
/*     */     }
/* 331 */     return device;
/*     */   }
/*     */ 
/*     */   static native long nalcGetContextsDevice(long paramLong);
/*     */ 
/*     */   public static void alcSuspendContext(ALCcontext context)
/*     */   {
/* 348 */     nalcSuspendContext(getContext(context));
/*     */   }
/*     */ 
/*     */   static native void nalcSuspendContext(long paramLong);
/*     */ 
/*     */   public static void alcDestroyContext(ALCcontext context)
/*     */   {
/* 359 */     synchronized (contexts) {
/* 360 */       ALCdevice device = alcGetContextsDevice(context);
/* 361 */       nalcDestroyContext(getContext(context));
/* 362 */       device.removeContext(context);
/* 363 */       context.setInvalid();
/*     */     }
/*     */   }
/*     */ 
/*     */   static native void nalcDestroyContext(long paramLong);
/*     */ 
/*     */   public static int alcGetError(ALCdevice device)
/*     */   {
/* 384 */     return nalcGetError(getDevice(device));
/*     */   }
/*     */ 
/*     */   static native int nalcGetError(long paramLong);
/*     */ 
/*     */   public static boolean alcIsExtensionPresent(ALCdevice device, String extName)
/*     */   {
/* 398 */     ByteBuffer buffer = MemoryUtil.encodeASCII(extName);
/* 399 */     boolean result = nalcIsExtensionPresent(getDevice(device), MemoryUtil.getAddress(buffer));
/* 400 */     Util.checkALCError(device);
/* 401 */     return result;
/*     */   }
/*     */ 
/*     */   private static native boolean nalcIsExtensionPresent(long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int alcGetEnumValue(ALCdevice device, String enumName)
/*     */   {
/* 416 */     ByteBuffer buffer = MemoryUtil.encodeASCII(enumName);
/* 417 */     int result = nalcGetEnumValue(getDevice(device), MemoryUtil.getAddress(buffer));
/* 418 */     Util.checkALCError(device);
/* 419 */     return result;
/*     */   }
/*     */   private static native int nalcGetEnumValue(long paramLong1, long paramLong2);
/*     */ 
/*     */   static long getDevice(ALCdevice device) {
/* 424 */     if (device != null) {
/* 425 */       Util.checkALCValidDevice(device);
/* 426 */       return device.device;
/*     */     }
/* 428 */     return 0L;
/*     */   }
/*     */ 
/*     */   static long getContext(ALCcontext context) {
/* 432 */     if (context != null) {
/* 433 */       Util.checkALCValidContext(context);
/* 434 */       return context.context;
/*     */     }
/* 436 */     return 0L;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.openal.ALC10
 * JD-Core Version:    0.6.2
 */