/*   1:    */package org.lwjgl.openal;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.IntBuffer;
/*   5:    */import java.util.HashMap;
/*   6:    */import org.lwjgl.BufferUtils;
/*   7:    */import org.lwjgl.LWJGLException;
/*   8:    */import org.lwjgl.LWJGLUtil;
/*   9:    */import org.lwjgl.MemoryUtil;
/*  10:    */
/*  83:    */public final class ALC11
/*  84:    */{
/*  85:    */  public static final int ALC_DEFAULT_ALL_DEVICES_SPECIFIER = 4114;
/*  86:    */  public static final int ALC_ALL_DEVICES_SPECIFIER = 4115;
/*  87:    */  public static final int ALC_CAPTURE_DEVICE_SPECIFIER = 784;
/*  88:    */  public static final int ALC_CAPTURE_DEFAULT_DEVICE_SPECIFIER = 785;
/*  89:    */  public static final int ALC_CAPTURE_SAMPLES = 786;
/*  90:    */  public static final int ALC_MONO_SOURCES = 4112;
/*  91:    */  public static final int ALC_STEREO_SOURCES = 4113;
/*  92:    */  
/*  93:    */  public static ALCdevice alcCaptureOpenDevice(String devicename, int frequency, int format, int buffersize)
/*  94:    */  {
/*  95: 95 */    ByteBuffer buffer = MemoryUtil.encodeASCII(devicename);
/*  96: 96 */    long device_address = nalcCaptureOpenDevice(MemoryUtil.getAddressSafe(buffer), frequency, format, buffersize);
/*  97: 97 */    if (device_address != 0L) {
/*  98: 98 */      ALCdevice device = new ALCdevice(device_address);
/*  99: 99 */      synchronized (ALC10.devices) {
/* 100:100 */        ALC10.devices.put(Long.valueOf(device_address), device);
/* 101:    */      }
/* 102:102 */      return device;
/* 103:    */    }
/* 104:104 */    return null;
/* 105:    */  }
/* 106:    */  
/* 111:    */  private static native long nalcCaptureOpenDevice(long paramLong, int paramInt1, int paramInt2, int paramInt3);
/* 112:    */  
/* 116:    */  public static boolean alcCaptureCloseDevice(ALCdevice device)
/* 117:    */  {
/* 118:118 */    boolean result = nalcCaptureCloseDevice(ALC10.getDevice(device));
/* 119:119 */    synchronized (ALC10.devices) {
/* 120:120 */      device.setInvalid();
/* 121:121 */      ALC10.devices.remove(new Long(device.device));
/* 122:    */    }
/* 123:123 */    return result;
/* 124:    */  }
/* 125:    */  
/* 130:    */  static native boolean nalcCaptureCloseDevice(long paramLong);
/* 131:    */  
/* 136:    */  public static void alcCaptureStart(ALCdevice device)
/* 137:    */  {
/* 138:138 */    nalcCaptureStart(ALC10.getDevice(device));
/* 139:    */  }
/* 140:    */  
/* 144:    */  static native void nalcCaptureStart(long paramLong);
/* 145:    */  
/* 148:    */  public static void alcCaptureStop(ALCdevice device)
/* 149:    */  {
/* 150:150 */    nalcCaptureStop(ALC10.getDevice(device));
/* 151:    */  }
/* 152:    */  
/* 158:    */  static native void nalcCaptureStop(long paramLong);
/* 159:    */  
/* 164:    */  public static void alcCaptureSamples(ALCdevice device, ByteBuffer buffer, int samples)
/* 165:    */  {
/* 166:166 */    nalcCaptureSamples(ALC10.getDevice(device), MemoryUtil.getAddress(buffer), samples);
/* 167:    */  }
/* 168:    */  
/* 169:    */  static native void nalcCaptureSamples(long paramLong1, long paramLong2, int paramInt);
/* 170:    */  
/* 171:    */  static native void initNativeStubs()
/* 172:    */    throws LWJGLException;
/* 173:    */  
/* 174:    */  static boolean initialize()
/* 175:    */  {
/* 176:    */    try
/* 177:    */    {
/* 178:178 */      IntBuffer ib = BufferUtils.createIntBuffer(2);
/* 179:179 */      ALC10.alcGetInteger(AL.getDevice(), 4096, ib);
/* 180:180 */      ib.position(1);
/* 181:181 */      ALC10.alcGetInteger(AL.getDevice(), 4097, ib);
/* 182:    */      
/* 183:183 */      int major = ib.get(0);
/* 184:184 */      int minor = ib.get(1);
/* 185:    */      
/* 187:187 */      if (major >= 1)
/* 188:    */      {
/* 190:190 */        if ((major > 1) || (minor >= 1)) {
/* 191:191 */          initNativeStubs();
/* 192:192 */          AL11.initNativeStubs();
/* 193:    */        }
/* 194:    */      }
/* 195:    */    } catch (LWJGLException le) {
/* 196:196 */      LWJGLUtil.log("failed to initialize ALC11: " + le);
/* 197:197 */      return false;
/* 198:    */    }
/* 199:199 */    return true;
/* 200:    */  }
/* 201:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.openal.ALC11
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */