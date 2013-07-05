/*      */ package org.lwjgl.openal;
/*      */ 
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.DoubleBuffer;
/*      */ import java.nio.FloatBuffer;
/*      */ import java.nio.IntBuffer;
/*      */ import java.nio.ShortBuffer;
/*      */ import org.lwjgl.BufferChecks;
/*      */ import org.lwjgl.LWJGLException;
/*      */ import org.lwjgl.MemoryUtil;
/*      */ 
/*      */ public final class AL10
/*      */ {
/*      */   public static final int AL_INVALID = -1;
/*      */   public static final int AL_NONE = 0;
/*      */   public static final int AL_FALSE = 0;
/*      */   public static final int AL_TRUE = 1;
/*      */   public static final int AL_SOURCE_TYPE = 4135;
/*      */   public static final int AL_SOURCE_ABSOLUTE = 513;
/*      */   public static final int AL_SOURCE_RELATIVE = 514;
/*      */   public static final int AL_CONE_INNER_ANGLE = 4097;
/*      */   public static final int AL_CONE_OUTER_ANGLE = 4098;
/*      */   public static final int AL_PITCH = 4099;
/*      */   public static final int AL_POSITION = 4100;
/*      */   public static final int AL_DIRECTION = 4101;
/*      */   public static final int AL_VELOCITY = 4102;
/*      */   public static final int AL_LOOPING = 4103;
/*      */   public static final int AL_BUFFER = 4105;
/*      */   public static final int AL_GAIN = 4106;
/*      */   public static final int AL_MIN_GAIN = 4109;
/*      */   public static final int AL_MAX_GAIN = 4110;
/*      */   public static final int AL_ORIENTATION = 4111;
/*      */   public static final int AL_REFERENCE_DISTANCE = 4128;
/*      */   public static final int AL_ROLLOFF_FACTOR = 4129;
/*      */   public static final int AL_CONE_OUTER_GAIN = 4130;
/*      */   public static final int AL_MAX_DISTANCE = 4131;
/*      */   public static final int AL_CHANNEL_MASK = 12288;
/*      */   public static final int AL_SOURCE_STATE = 4112;
/*      */   public static final int AL_INITIAL = 4113;
/*      */   public static final int AL_PLAYING = 4114;
/*      */   public static final int AL_PAUSED = 4115;
/*      */   public static final int AL_STOPPED = 4116;
/*      */   public static final int AL_BUFFERS_QUEUED = 4117;
/*      */   public static final int AL_BUFFERS_PROCESSED = 4118;
/*      */   public static final int AL_FORMAT_MONO8 = 4352;
/*      */   public static final int AL_FORMAT_MONO16 = 4353;
/*      */   public static final int AL_FORMAT_STEREO8 = 4354;
/*      */   public static final int AL_FORMAT_STEREO16 = 4355;
/*      */   public static final int AL_FORMAT_VORBIS_EXT = 65539;
/*      */   public static final int AL_FREQUENCY = 8193;
/*      */   public static final int AL_BITS = 8194;
/*      */   public static final int AL_CHANNELS = 8195;
/*      */   public static final int AL_SIZE = 8196;
/*      */ 
/*      */   /** @deprecated */
/*      */   public static final int AL_DATA = 8197;
/*      */   public static final int AL_UNUSED = 8208;
/*      */   public static final int AL_PENDING = 8209;
/*      */   public static final int AL_PROCESSED = 8210;
/*      */   public static final int AL_NO_ERROR = 0;
/*      */   public static final int AL_INVALID_NAME = 40961;
/*      */   public static final int AL_INVALID_ENUM = 40962;
/*      */   public static final int AL_INVALID_VALUE = 40963;
/*      */   public static final int AL_INVALID_OPERATION = 40964;
/*      */   public static final int AL_OUT_OF_MEMORY = 40965;
/*      */   public static final int AL_VENDOR = 45057;
/*      */   public static final int AL_VERSION = 45058;
/*      */   public static final int AL_RENDERER = 45059;
/*      */   public static final int AL_EXTENSIONS = 45060;
/*      */   public static final int AL_DOPPLER_FACTOR = 49152;
/*      */   public static final int AL_DOPPLER_VELOCITY = 49153;
/*      */   public static final int AL_DISTANCE_MODEL = 53248;
/*      */   public static final int AL_INVERSE_DISTANCE = 53249;
/*      */   public static final int AL_INVERSE_DISTANCE_CLAMPED = 53250;
/*      */ 
/*      */   static native void initNativeStubs()
/*      */     throws LWJGLException;
/*      */ 
/*      */   public static void alEnable(int capability)
/*      */   {
/*  341 */     nalEnable(capability);
/*      */   }
/*      */ 
/*      */   static native void nalEnable(int paramInt);
/*      */ 
/*      */   public static void alDisable(int capability)
/*      */   {
/*  353 */     nalDisable(capability);
/*      */   }
/*      */ 
/*      */   static native void nalDisable(int paramInt);
/*      */ 
/*      */   public static boolean alIsEnabled(int capability)
/*      */   {
/*  372 */     boolean __result = nalIsEnabled(capability);
/*  373 */     return __result;
/*      */   }
/*      */ 
/*      */   static native boolean nalIsEnabled(int paramInt);
/*      */ 
/*      */   public static boolean alGetBoolean(int pname)
/*      */   {
/*  391 */     boolean __result = nalGetBoolean(pname);
/*  392 */     return __result;
/*      */   }
/*      */ 
/*      */   static native boolean nalGetBoolean(int paramInt);
/*      */ 
/*      */   public static int alGetInteger(int pname)
/*      */   {
/*  410 */     int __result = nalGetInteger(pname);
/*  411 */     return __result;
/*      */   }
/*      */ 
/*      */   static native int nalGetInteger(int paramInt);
/*      */ 
/*      */   public static float alGetFloat(int pname)
/*      */   {
/*  429 */     float __result = nalGetFloat(pname);
/*  430 */     return __result;
/*      */   }
/*      */ 
/*      */   static native float nalGetFloat(int paramInt);
/*      */ 
/*      */   public static double alGetDouble(int pname)
/*      */   {
/*  448 */     double __result = nalGetDouble(pname);
/*  449 */     return __result;
/*      */   }
/*      */ 
/*      */   static native double nalGetDouble(int paramInt);
/*      */ 
/*      */   public static void alGetInteger(int pname, IntBuffer data)
/*      */   {
/*  468 */     BufferChecks.checkBuffer(data, 1);
/*  469 */     nalGetIntegerv(pname, MemoryUtil.getAddress(data));
/*      */   }
/*      */ 
/*      */   static native void nalGetIntegerv(int paramInt, long paramLong);
/*      */ 
/*      */   public static void alGetFloat(int pname, FloatBuffer data)
/*      */   {
/*  488 */     BufferChecks.checkBuffer(data, 1);
/*  489 */     nalGetFloatv(pname, MemoryUtil.getAddress(data));
/*      */   }
/*      */ 
/*      */   static native void nalGetFloatv(int paramInt, long paramLong);
/*      */ 
/*      */   public static void alGetDouble(int pname, DoubleBuffer data)
/*      */   {
/*  508 */     BufferChecks.checkBuffer(data, 1);
/*  509 */     nalGetDoublev(pname, MemoryUtil.getAddress(data));
/*      */   }
/*      */ 
/*      */   static native void nalGetDoublev(int paramInt, long paramLong);
/*      */ 
/*      */   public static String alGetString(int pname)
/*      */   {
/*  524 */     String __result = nalGetString(pname);
/*  525 */     return __result;
/*      */   }
/*      */ 
/*      */   static native String nalGetString(int paramInt);
/*      */ 
/*      */   public static int alGetError()
/*      */   {
/*  607 */     int __result = nalGetError();
/*  608 */     return __result;
/*      */   }
/*      */ 
/*      */   static native int nalGetError();
/*      */ 
/*      */   public static boolean alIsExtensionPresent(String fname)
/*      */   {
/*  624 */     BufferChecks.checkNotNull(fname);
/*  625 */     boolean __result = nalIsExtensionPresent(fname);
/*  626 */     return __result;
/*      */   }
/*      */ 
/*      */   static native boolean nalIsExtensionPresent(String paramString);
/*      */ 
/*      */   public static int alGetEnumValue(String ename)
/*      */   {
/*  649 */     BufferChecks.checkNotNull(ename);
/*  650 */     int __result = nalGetEnumValue(ename);
/*  651 */     return __result;
/*      */   }
/*      */ 
/*      */   static native int nalGetEnumValue(String paramString);
/*      */ 
/*      */   public static void alListeneri(int pname, int value)
/*      */   {
/*  662 */     nalListeneri(pname, value);
/*      */   }
/*      */ 
/*      */   static native void nalListeneri(int paramInt1, int paramInt2);
/*      */ 
/*      */   public static void alListenerf(int pname, float value)
/*      */   {
/*  673 */     nalListenerf(pname, value);
/*      */   }
/*      */ 
/*      */   static native void nalListenerf(int paramInt, float paramFloat);
/*      */ 
/*      */   public static void alListener(int pname, FloatBuffer value)
/*      */   {
/*  684 */     BufferChecks.checkBuffer(value, 1);
/*  685 */     nalListenerfv(pname, MemoryUtil.getAddress(value));
/*      */   }
/*      */ 
/*      */   static native void nalListenerfv(int paramInt, long paramLong);
/*      */ 
/*      */   public static void alListener3f(int pname, float v1, float v2, float v3)
/*      */   {
/*  698 */     nalListener3f(pname, v1, v2, v3);
/*      */   }
/*      */ 
/*      */   static native void nalListener3f(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3);
/*      */ 
/*      */   public static int alGetListeneri(int pname)
/*      */   {
/*  710 */     int __result = nalGetListeneri(pname);
/*  711 */     return __result;
/*      */   }
/*      */ 
/*      */   static native int nalGetListeneri(int paramInt);
/*      */ 
/*      */   public static float alGetListenerf(int pname)
/*      */   {
/*  723 */     float __result = nalGetListenerf(pname);
/*  724 */     return __result;
/*      */   }
/*      */ 
/*      */   static native float nalGetListenerf(int paramInt);
/*      */ 
/*      */   public static void alGetListener(int pname, FloatBuffer floatdata)
/*      */   {
/*  736 */     BufferChecks.checkBuffer(floatdata, 1);
/*  737 */     nalGetListenerfv(pname, MemoryUtil.getAddress(floatdata));
/*      */   }
/*      */ 
/*      */   static native void nalGetListenerfv(int paramInt, long paramLong);
/*      */ 
/*      */   public static void alGenSources(IntBuffer sources)
/*      */   {
/*  747 */     BufferChecks.checkDirect(sources);
/*  748 */     nalGenSources(sources.remaining(), MemoryUtil.getAddress(sources));
/*      */   }
/*      */ 
/*      */   static native void nalGenSources(int paramInt, long paramLong);
/*      */ 
/*      */   public static int alGenSources() {
/*  754 */     int __result = nalGenSources2(1);
/*  755 */     return __result;
/*      */   }
/*      */ 
/*      */   static native int nalGenSources2(int paramInt);
/*      */ 
/*      */   public static void alDeleteSources(IntBuffer sources)
/*      */   {
/*  765 */     BufferChecks.checkDirect(sources);
/*  766 */     nalDeleteSources(sources.remaining(), MemoryUtil.getAddress(sources));
/*      */   }
/*      */ 
/*      */   static native void nalDeleteSources(int paramInt, long paramLong);
/*      */ 
/*      */   public static void alDeleteSources(int source) {
/*  772 */     nalDeleteSources2(1, source);
/*      */   }
/*      */ 
/*      */   static native void nalDeleteSources2(int paramInt1, int paramInt2);
/*      */ 
/*      */   public static boolean alIsSource(int id)
/*      */   {
/*  783 */     boolean __result = nalIsSource(id);
/*  784 */     return __result;
/*      */   }
/*      */ 
/*      */   static native boolean nalIsSource(int paramInt);
/*      */ 
/*      */   public static void alSourcei(int source, int pname, int value)
/*      */   {
/*  797 */     nalSourcei(source, pname, value);
/*      */   }
/*      */ 
/*      */   static native void nalSourcei(int paramInt1, int paramInt2, int paramInt3);
/*      */ 
/*      */   public static void alSourcef(int source, int pname, float value)
/*      */   {
/*  810 */     nalSourcef(source, pname, value);
/*      */   }
/*      */ 
/*      */   static native void nalSourcef(int paramInt1, int paramInt2, float paramFloat);
/*      */ 
/*      */   public static void alSource(int source, int pname, FloatBuffer value)
/*      */   {
/*  823 */     BufferChecks.checkBuffer(value, 1);
/*  824 */     nalSourcefv(source, pname, MemoryUtil.getAddress(value));
/*      */   }
/*      */ 
/*      */   static native void nalSourcefv(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void alSource3f(int source, int pname, float v1, float v2, float v3)
/*      */   {
/*  839 */     nalSource3f(source, pname, v1, v2, v3);
/*      */   }
/*      */ 
/*      */   static native void nalSource3f(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3);
/*      */ 
/*      */   public static int alGetSourcei(int source, int pname)
/*      */   {
/*  853 */     int __result = nalGetSourcei(source, pname);
/*  854 */     return __result;
/*      */   }
/*      */ 
/*      */   static native int nalGetSourcei(int paramInt1, int paramInt2);
/*      */ 
/*      */   public static float alGetSourcef(int source, int pname)
/*      */   {
/*  868 */     float __result = nalGetSourcef(source, pname);
/*  869 */     return __result;
/*      */   }
/*      */ 
/*      */   static native float nalGetSourcef(int paramInt1, int paramInt2);
/*      */ 
/*      */   public static void alGetSource(int source, int pname, FloatBuffer floatdata)
/*      */   {
/*  883 */     BufferChecks.checkBuffer(floatdata, 1);
/*  884 */     nalGetSourcefv(source, pname, MemoryUtil.getAddress(floatdata));
/*      */   }
/*      */ 
/*      */   static native void nalGetSourcefv(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void alSourcePlay(IntBuffer sources)
/*      */   {
/*  901 */     BufferChecks.checkDirect(sources);
/*  902 */     nalSourcePlayv(sources.remaining(), MemoryUtil.getAddress(sources));
/*      */   }
/*      */ 
/*      */   static native void nalSourcePlayv(int paramInt, long paramLong);
/*      */ 
/*      */   public static void alSourcePause(IntBuffer sources)
/*      */   {
/*  915 */     BufferChecks.checkDirect(sources);
/*  916 */     nalSourcePausev(sources.remaining(), MemoryUtil.getAddress(sources));
/*      */   }
/*      */ 
/*      */   static native void nalSourcePausev(int paramInt, long paramLong);
/*      */ 
/*      */   public static void alSourceStop(IntBuffer sources)
/*      */   {
/*  930 */     BufferChecks.checkDirect(sources);
/*  931 */     nalSourceStopv(sources.remaining(), MemoryUtil.getAddress(sources));
/*      */   }
/*      */ 
/*      */   static native void nalSourceStopv(int paramInt, long paramLong);
/*      */ 
/*      */   public static void alSourceRewind(IntBuffer sources)
/*      */   {
/*  947 */     BufferChecks.checkDirect(sources);
/*  948 */     nalSourceRewindv(sources.remaining(), MemoryUtil.getAddress(sources));
/*      */   }
/*      */ 
/*      */   static native void nalSourceRewindv(int paramInt, long paramLong);
/*      */ 
/*      */   public static void alSourcePlay(int source)
/*      */   {
/*  965 */     nalSourcePlay(source);
/*      */   }
/*      */ 
/*      */   static native void nalSourcePlay(int paramInt);
/*      */ 
/*      */   public static void alSourcePause(int source)
/*      */   {
/*  978 */     nalSourcePause(source);
/*      */   }
/*      */ 
/*      */   static native void nalSourcePause(int paramInt);
/*      */ 
/*      */   public static void alSourceStop(int source)
/*      */   {
/*  992 */     nalSourceStop(source);
/*      */   }
/*      */ 
/*      */   static native void nalSourceStop(int paramInt);
/*      */ 
/*      */   public static void alSourceRewind(int source)
/*      */   {
/* 1008 */     nalSourceRewind(source);
/*      */   }
/*      */ 
/*      */   static native void nalSourceRewind(int paramInt);
/*      */ 
/*      */   public static void alGenBuffers(IntBuffer buffers)
/*      */   {
/* 1018 */     BufferChecks.checkDirect(buffers);
/* 1019 */     nalGenBuffers(buffers.remaining(), MemoryUtil.getAddress(buffers));
/*      */   }
/*      */ 
/*      */   static native void nalGenBuffers(int paramInt, long paramLong);
/*      */ 
/*      */   public static int alGenBuffers() {
/* 1025 */     int __result = nalGenBuffers2(1);
/* 1026 */     return __result;
/*      */   }
/*      */ 
/*      */   static native int nalGenBuffers2(int paramInt);
/*      */ 
/*      */   public static void alDeleteBuffers(IntBuffer buffers)
/*      */   {
/* 1047 */     BufferChecks.checkDirect(buffers);
/* 1048 */     nalDeleteBuffers(buffers.remaining(), MemoryUtil.getAddress(buffers));
/*      */   }
/*      */ 
/*      */   static native void nalDeleteBuffers(int paramInt, long paramLong);
/*      */ 
/*      */   public static void alDeleteBuffers(int buffer) {
/* 1054 */     nalDeleteBuffers2(1, buffer);
/*      */   }
/*      */ 
/*      */   static native void nalDeleteBuffers2(int paramInt1, int paramInt2);
/*      */ 
/*      */   public static boolean alIsBuffer(int buffer)
/*      */   {
/* 1065 */     boolean __result = nalIsBuffer(buffer);
/* 1066 */     return __result;
/*      */   }
/*      */ 
/*      */   static native boolean nalIsBuffer(int paramInt);
/*      */ 
/*      */   public static void alBufferData(int buffer, int format, ByteBuffer data, int freq)
/*      */   {
/* 1098 */     BufferChecks.checkDirect(data);
/* 1099 */     nalBufferData(buffer, format, MemoryUtil.getAddress(data), data.remaining(), freq);
/*      */   }
/*      */ 
/*      */   public static void alBufferData(int buffer, int format, IntBuffer data, int freq)
/*      */   {
/* 1129 */     BufferChecks.checkDirect(data);
/* 1130 */     nalBufferData(buffer, format, MemoryUtil.getAddress(data), data.remaining() << 2, freq);
/*      */   }
/*      */ 
/*      */   public static void alBufferData(int buffer, int format, ShortBuffer data, int freq)
/*      */   {
/* 1160 */     BufferChecks.checkDirect(data);
/* 1161 */     nalBufferData(buffer, format, MemoryUtil.getAddress(data), data.remaining() << 1, freq);
/*      */   }
/*      */ 
/*      */   static native void nalBufferData(int paramInt1, int paramInt2, long paramLong, int paramInt3, int paramInt4);
/*      */ 
/*      */   public static int alGetBufferi(int buffer, int pname)
/*      */   {
/* 1174 */     int __result = nalGetBufferi(buffer, pname);
/* 1175 */     return __result;
/*      */   }
/*      */ 
/*      */   static native int nalGetBufferi(int paramInt1, int paramInt2);
/*      */ 
/*      */   public static float alGetBufferf(int buffer, int pname)
/*      */   {
/* 1189 */     float __result = nalGetBufferf(buffer, pname);
/* 1190 */     return __result;
/*      */   }
/*      */ 
/*      */   static native float nalGetBufferf(int paramInt1, int paramInt2);
/*      */ 
/*      */   public static void alSourceQueueBuffers(int source, IntBuffer buffers)
/*      */   {
/* 1210 */     BufferChecks.checkDirect(buffers);
/* 1211 */     nalSourceQueueBuffers(source, buffers.remaining(), MemoryUtil.getAddress(buffers));
/*      */   }
/*      */ 
/*      */   static native void nalSourceQueueBuffers(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static void alSourceQueueBuffers(int source, int buffer) {
/* 1217 */     nalSourceQueueBuffers2(source, 1, buffer);
/*      */   }
/*      */ 
/*      */   static native void nalSourceQueueBuffers2(int paramInt1, int paramInt2, int paramInt3);
/*      */ 
/*      */   public static void alSourceUnqueueBuffers(int source, IntBuffer buffers)
/*      */   {
/* 1241 */     BufferChecks.checkDirect(buffers);
/* 1242 */     nalSourceUnqueueBuffers(source, buffers.remaining(), MemoryUtil.getAddress(buffers));
/*      */   }
/*      */ 
/*      */   static native void nalSourceUnqueueBuffers(int paramInt1, int paramInt2, long paramLong);
/*      */ 
/*      */   public static int alSourceUnqueueBuffers(int source) {
/* 1248 */     int __result = nalSourceUnqueueBuffers2(source, 1);
/* 1249 */     return __result;
/*      */   }
/*      */ 
/*      */   static native int nalSourceUnqueueBuffers2(int paramInt1, int paramInt2);
/*      */ 
/*      */   public static void alDistanceModel(int value)
/*      */   {
/* 1303 */     nalDistanceModel(value);
/*      */   }
/*      */ 
/*      */   static native void nalDistanceModel(int paramInt);
/*      */ 
/*      */   public static void alDopplerFactor(float value)
/*      */   {
/* 1360 */     nalDopplerFactor(value);
/*      */   }
/*      */ 
/*      */   static native void nalDopplerFactor(float paramFloat);
/*      */ 
/*      */   public static void alDopplerVelocity(float value)
/*      */   {
/* 1417 */     nalDopplerVelocity(value);
/*      */   }
/*      */ 
/*      */   static native void nalDopplerVelocity(float paramFloat);
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.openal.AL10
 * JD-Core Version:    0.6.2
 */