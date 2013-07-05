/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.DoubleBuffer;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.nio.LongBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ public final class NVVideoCapture
/*     */ {
/*     */   public static final int GL_VIDEO_BUFFER_NV = 36896;
/*     */   public static final int GL_VIDEO_BUFFER_BINDING_NV = 36897;
/*     */   public static final int GL_FIELD_UPPER_NV = 36898;
/*     */   public static final int GL_FIELD_LOWER_NV = 36899;
/*     */   public static final int GL_NUM_VIDEO_CAPTURE_STREAMS_NV = 36900;
/*     */   public static final int GL_NEXT_VIDEO_CAPTURE_BUFFER_STATUS_NV = 36901;
/*     */   public static final int GL_LAST_VIDEO_CAPTURE_STATUS_NV = 36903;
/*     */   public static final int GL_VIDEO_BUFFER_PITCH_NV = 36904;
/*     */   public static final int GL_VIDEO_CAPTURE_FRAME_WIDTH_NV = 36920;
/*     */   public static final int GL_VIDEO_CAPTURE_FRAME_HEIGHT_NV = 36921;
/*     */   public static final int GL_VIDEO_CAPTURE_FIELD_UPPER_HEIGHT_NV = 36922;
/*     */   public static final int GL_VIDEO_CAPTURE_FIELD_LOWER_HEIGHT_NV = 36923;
/*     */   public static final int GL_VIDEO_CAPTURE_TO_422_SUPPORTED_NV = 36902;
/*     */   public static final int GL_VIDEO_COLOR_CONVERSION_MATRIX_NV = 36905;
/*     */   public static final int GL_VIDEO_COLOR_CONVERSION_MAX_NV = 36906;
/*     */   public static final int GL_VIDEO_COLOR_CONVERSION_MIN_NV = 36907;
/*     */   public static final int GL_VIDEO_COLOR_CONVERSION_OFFSET_NV = 36908;
/*     */   public static final int GL_VIDEO_BUFFER_INTERNAL_FORMAT_NV = 36909;
/*     */   public static final int GL_VIDEO_CAPTURE_SURFACE_ORIGIN_NV = 36924;
/*     */   public static final int GL_PARTIAL_SUCCESS_NV = 36910;
/*     */   public static final int GL_SUCCESS_NV = 36911;
/*     */   public static final int GL_FAILURE_NV = 36912;
/*     */   public static final int GL_YCBYCR8_422_NV = 36913;
/*     */   public static final int GL_YCBAYCR8A_4224_NV = 36914;
/*     */   public static final int GL_Z6Y10Z6CB10Z6Y10Z6CR10_422_NV = 36915;
/*     */   public static final int GL_Z6Y10Z6CB10Z6A10Z6Y10Z6CR10Z6A10_4224_NV = 36916;
/*     */   public static final int GL_Z4Y12Z4CB12Z4Y12Z4CR12_422_NV = 36917;
/*     */   public static final int GL_Z4Y12Z4CB12Z4A12Z4Y12Z4CR12Z4A12_4224_NV = 36918;
/*     */   public static final int GL_Z4Y12Z4CB12Z4CR12_444_NV = 36919;
/*     */   public static final int GL_NUM_VIDEO_CAPTURE_SLOTS_NV = 8399;
/*     */   public static final int GL_UNIQUE_ID_NV = 8398;
/*     */ 
/*     */   public static void glBeginVideoCaptureNV(int video_capture_slot)
/*     */   {
/* 101 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 102 */     long function_pointer = caps.glBeginVideoCaptureNV;
/* 103 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 104 */     nglBeginVideoCaptureNV(video_capture_slot, function_pointer);
/*     */   }
/*     */   static native void nglBeginVideoCaptureNV(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glBindVideoCaptureStreamBufferNV(int video_capture_slot, int stream, int frame_region, long offset) {
/* 109 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 110 */     long function_pointer = caps.glBindVideoCaptureStreamBufferNV;
/* 111 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 112 */     nglBindVideoCaptureStreamBufferNV(video_capture_slot, stream, frame_region, offset, function_pointer);
/*     */   }
/*     */   static native void nglBindVideoCaptureStreamBufferNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glBindVideoCaptureStreamTextureNV(int video_capture_slot, int stream, int frame_region, int target, int texture) {
/* 117 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 118 */     long function_pointer = caps.glBindVideoCaptureStreamTextureNV;
/* 119 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 120 */     nglBindVideoCaptureStreamTextureNV(video_capture_slot, stream, frame_region, target, texture, function_pointer);
/*     */   }
/*     */   static native void nglBindVideoCaptureStreamTextureNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*     */ 
/*     */   public static void glEndVideoCaptureNV(int video_capture_slot) {
/* 125 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 126 */     long function_pointer = caps.glEndVideoCaptureNV;
/* 127 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 128 */     nglEndVideoCaptureNV(video_capture_slot, function_pointer);
/*     */   }
/*     */   static native void nglEndVideoCaptureNV(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glGetVideoCaptureNV(int video_capture_slot, int pname, IntBuffer params) {
/* 133 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 134 */     long function_pointer = caps.glGetVideoCaptureivNV;
/* 135 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 136 */     BufferChecks.checkBuffer(params, 1);
/* 137 */     nglGetVideoCaptureivNV(video_capture_slot, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetVideoCaptureivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int glGetVideoCaptureiNV(int video_capture_slot, int pname) {
/* 143 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 144 */     long function_pointer = caps.glGetVideoCaptureivNV;
/* 145 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 146 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 147 */     nglGetVideoCaptureivNV(video_capture_slot, pname, MemoryUtil.getAddress(params), function_pointer);
/* 148 */     return params.get(0);
/*     */   }
/*     */ 
/*     */   public static void glGetVideoCaptureStreamNV(int video_capture_slot, int stream, int pname, IntBuffer params) {
/* 152 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 153 */     long function_pointer = caps.glGetVideoCaptureStreamivNV;
/* 154 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 155 */     BufferChecks.checkBuffer(params, 1);
/* 156 */     nglGetVideoCaptureStreamivNV(video_capture_slot, stream, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetVideoCaptureStreamivNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int glGetVideoCaptureStreamiNV(int video_capture_slot, int stream, int pname) {
/* 162 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 163 */     long function_pointer = caps.glGetVideoCaptureStreamivNV;
/* 164 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 165 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 166 */     nglGetVideoCaptureStreamivNV(video_capture_slot, stream, pname, MemoryUtil.getAddress(params), function_pointer);
/* 167 */     return params.get(0);
/*     */   }
/*     */ 
/*     */   public static void glGetVideoCaptureStreamNV(int video_capture_slot, int stream, int pname, FloatBuffer params) {
/* 171 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 172 */     long function_pointer = caps.glGetVideoCaptureStreamfvNV;
/* 173 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 174 */     BufferChecks.checkBuffer(params, 1);
/* 175 */     nglGetVideoCaptureStreamfvNV(video_capture_slot, stream, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetVideoCaptureStreamfvNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static float glGetVideoCaptureStreamfNV(int video_capture_slot, int stream, int pname) {
/* 181 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 182 */     long function_pointer = caps.glGetVideoCaptureStreamfvNV;
/* 183 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 184 */     FloatBuffer params = APIUtil.getBufferFloat(caps);
/* 185 */     nglGetVideoCaptureStreamfvNV(video_capture_slot, stream, pname, MemoryUtil.getAddress(params), function_pointer);
/* 186 */     return params.get(0);
/*     */   }
/*     */ 
/*     */   public static void glGetVideoCaptureStreamNV(int video_capture_slot, int stream, int pname, DoubleBuffer params) {
/* 190 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 191 */     long function_pointer = caps.glGetVideoCaptureStreamdvNV;
/* 192 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 193 */     BufferChecks.checkBuffer(params, 1);
/* 194 */     nglGetVideoCaptureStreamdvNV(video_capture_slot, stream, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetVideoCaptureStreamdvNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static double glGetVideoCaptureStreamdNV(int video_capture_slot, int stream, int pname) {
/* 200 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 201 */     long function_pointer = caps.glGetVideoCaptureStreamdvNV;
/* 202 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 203 */     DoubleBuffer params = APIUtil.getBufferDouble(caps);
/* 204 */     nglGetVideoCaptureStreamdvNV(video_capture_slot, stream, pname, MemoryUtil.getAddress(params), function_pointer);
/* 205 */     return params.get(0);
/*     */   }
/*     */ 
/*     */   public static int glVideoCaptureNV(int video_capture_slot, IntBuffer sequence_num, LongBuffer capture_time) {
/* 209 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 210 */     long function_pointer = caps.glVideoCaptureNV;
/* 211 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 212 */     BufferChecks.checkBuffer(sequence_num, 1);
/* 213 */     BufferChecks.checkBuffer(capture_time, 1);
/* 214 */     int __result = nglVideoCaptureNV(video_capture_slot, MemoryUtil.getAddress(sequence_num), MemoryUtil.getAddress(capture_time), function_pointer);
/* 215 */     return __result;
/*     */   }
/*     */   static native int nglVideoCaptureNV(int paramInt, long paramLong1, long paramLong2, long paramLong3);
/*     */ 
/*     */   public static void glVideoCaptureStreamParameterNV(int video_capture_slot, int stream, int pname, IntBuffer params) {
/* 220 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 221 */     long function_pointer = caps.glVideoCaptureStreamParameterivNV;
/* 222 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 223 */     BufferChecks.checkBuffer(params, 16);
/* 224 */     nglVideoCaptureStreamParameterivNV(video_capture_slot, stream, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglVideoCaptureStreamParameterivNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVideoCaptureStreamParameterNV(int video_capture_slot, int stream, int pname, FloatBuffer params) {
/* 229 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 230 */     long function_pointer = caps.glVideoCaptureStreamParameterfvNV;
/* 231 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 232 */     BufferChecks.checkBuffer(params, 16);
/* 233 */     nglVideoCaptureStreamParameterfvNV(video_capture_slot, stream, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */   static native void nglVideoCaptureStreamParameterfvNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glVideoCaptureStreamParameterNV(int video_capture_slot, int stream, int pname, DoubleBuffer params) {
/* 238 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 239 */     long function_pointer = caps.glVideoCaptureStreamParameterdvNV;
/* 240 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 241 */     BufferChecks.checkBuffer(params, 16);
/* 242 */     nglVideoCaptureStreamParameterdvNV(video_capture_slot, stream, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglVideoCaptureStreamParameterdvNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVVideoCapture
 * JD-Core Version:    0.6.2
 */