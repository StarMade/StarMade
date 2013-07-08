/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.DoubleBuffer;
/*   4:    */import java.nio.FloatBuffer;
/*   5:    */import java.nio.IntBuffer;
/*   6:    */import java.nio.LongBuffer;
/*   7:    */import org.lwjgl.BufferChecks;
/*   8:    */import org.lwjgl.MemoryUtil;
/*   9:    */
/*  65:    */public final class NVVideoCapture
/*  66:    */{
/*  67:    */  public static final int GL_VIDEO_BUFFER_NV = 36896;
/*  68:    */  public static final int GL_VIDEO_BUFFER_BINDING_NV = 36897;
/*  69:    */  public static final int GL_FIELD_UPPER_NV = 36898;
/*  70:    */  public static final int GL_FIELD_LOWER_NV = 36899;
/*  71:    */  public static final int GL_NUM_VIDEO_CAPTURE_STREAMS_NV = 36900;
/*  72:    */  public static final int GL_NEXT_VIDEO_CAPTURE_BUFFER_STATUS_NV = 36901;
/*  73:    */  public static final int GL_LAST_VIDEO_CAPTURE_STATUS_NV = 36903;
/*  74:    */  public static final int GL_VIDEO_BUFFER_PITCH_NV = 36904;
/*  75:    */  public static final int GL_VIDEO_CAPTURE_FRAME_WIDTH_NV = 36920;
/*  76:    */  public static final int GL_VIDEO_CAPTURE_FRAME_HEIGHT_NV = 36921;
/*  77:    */  public static final int GL_VIDEO_CAPTURE_FIELD_UPPER_HEIGHT_NV = 36922;
/*  78:    */  public static final int GL_VIDEO_CAPTURE_FIELD_LOWER_HEIGHT_NV = 36923;
/*  79:    */  public static final int GL_VIDEO_CAPTURE_TO_422_SUPPORTED_NV = 36902;
/*  80:    */  public static final int GL_VIDEO_COLOR_CONVERSION_MATRIX_NV = 36905;
/*  81:    */  public static final int GL_VIDEO_COLOR_CONVERSION_MAX_NV = 36906;
/*  82:    */  public static final int GL_VIDEO_COLOR_CONVERSION_MIN_NV = 36907;
/*  83:    */  public static final int GL_VIDEO_COLOR_CONVERSION_OFFSET_NV = 36908;
/*  84:    */  public static final int GL_VIDEO_BUFFER_INTERNAL_FORMAT_NV = 36909;
/*  85:    */  public static final int GL_VIDEO_CAPTURE_SURFACE_ORIGIN_NV = 36924;
/*  86:    */  public static final int GL_PARTIAL_SUCCESS_NV = 36910;
/*  87:    */  public static final int GL_SUCCESS_NV = 36911;
/*  88:    */  public static final int GL_FAILURE_NV = 36912;
/*  89:    */  public static final int GL_YCBYCR8_422_NV = 36913;
/*  90:    */  public static final int GL_YCBAYCR8A_4224_NV = 36914;
/*  91:    */  public static final int GL_Z6Y10Z6CB10Z6Y10Z6CR10_422_NV = 36915;
/*  92:    */  public static final int GL_Z6Y10Z6CB10Z6A10Z6Y10Z6CR10Z6A10_4224_NV = 36916;
/*  93:    */  public static final int GL_Z4Y12Z4CB12Z4Y12Z4CR12_422_NV = 36917;
/*  94:    */  public static final int GL_Z4Y12Z4CB12Z4A12Z4Y12Z4CR12Z4A12_4224_NV = 36918;
/*  95:    */  public static final int GL_Z4Y12Z4CB12Z4CR12_444_NV = 36919;
/*  96:    */  public static final int GL_NUM_VIDEO_CAPTURE_SLOTS_NV = 8399;
/*  97:    */  public static final int GL_UNIQUE_ID_NV = 8398;
/*  98:    */  
/*  99:    */  public static void glBeginVideoCaptureNV(int video_capture_slot)
/* 100:    */  {
/* 101:101 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 102:102 */    long function_pointer = caps.glBeginVideoCaptureNV;
/* 103:103 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 104:104 */    nglBeginVideoCaptureNV(video_capture_slot, function_pointer);
/* 105:    */  }
/* 106:    */  
/* 107:    */  static native void nglBeginVideoCaptureNV(int paramInt, long paramLong);
/* 108:    */  
/* 109:109 */  public static void glBindVideoCaptureStreamBufferNV(int video_capture_slot, int stream, int frame_region, long offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 110:110 */    long function_pointer = caps.glBindVideoCaptureStreamBufferNV;
/* 111:111 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 112:112 */    nglBindVideoCaptureStreamBufferNV(video_capture_slot, stream, frame_region, offset, function_pointer);
/* 113:    */  }
/* 114:    */  
/* 115:    */  static native void nglBindVideoCaptureStreamBufferNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 116:    */  
/* 117:117 */  public static void glBindVideoCaptureStreamTextureNV(int video_capture_slot, int stream, int frame_region, int target, int texture) { ContextCapabilities caps = GLContext.getCapabilities();
/* 118:118 */    long function_pointer = caps.glBindVideoCaptureStreamTextureNV;
/* 119:119 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 120:120 */    nglBindVideoCaptureStreamTextureNV(video_capture_slot, stream, frame_region, target, texture, function_pointer);
/* 121:    */  }
/* 122:    */  
/* 123:    */  static native void nglBindVideoCaptureStreamTextureNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/* 124:    */  
/* 125:125 */  public static void glEndVideoCaptureNV(int video_capture_slot) { ContextCapabilities caps = GLContext.getCapabilities();
/* 126:126 */    long function_pointer = caps.glEndVideoCaptureNV;
/* 127:127 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 128:128 */    nglEndVideoCaptureNV(video_capture_slot, function_pointer);
/* 129:    */  }
/* 130:    */  
/* 131:    */  static native void nglEndVideoCaptureNV(int paramInt, long paramLong);
/* 132:    */  
/* 133:133 */  public static void glGetVideoCaptureNV(int video_capture_slot, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 134:134 */    long function_pointer = caps.glGetVideoCaptureivNV;
/* 135:135 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 136:136 */    BufferChecks.checkBuffer(params, 1);
/* 137:137 */    nglGetVideoCaptureivNV(video_capture_slot, pname, MemoryUtil.getAddress(params), function_pointer);
/* 138:    */  }
/* 139:    */  
/* 140:    */  static native void nglGetVideoCaptureivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 141:    */  
/* 142:    */  public static int glGetVideoCaptureiNV(int video_capture_slot, int pname) {
/* 143:143 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 144:144 */    long function_pointer = caps.glGetVideoCaptureivNV;
/* 145:145 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 146:146 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 147:147 */    nglGetVideoCaptureivNV(video_capture_slot, pname, MemoryUtil.getAddress(params), function_pointer);
/* 148:148 */    return params.get(0);
/* 149:    */  }
/* 150:    */  
/* 151:    */  public static void glGetVideoCaptureStreamNV(int video_capture_slot, int stream, int pname, IntBuffer params) {
/* 152:152 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 153:153 */    long function_pointer = caps.glGetVideoCaptureStreamivNV;
/* 154:154 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 155:155 */    BufferChecks.checkBuffer(params, 1);
/* 156:156 */    nglGetVideoCaptureStreamivNV(video_capture_slot, stream, pname, MemoryUtil.getAddress(params), function_pointer);
/* 157:    */  }
/* 158:    */  
/* 159:    */  static native void nglGetVideoCaptureStreamivNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 160:    */  
/* 161:    */  public static int glGetVideoCaptureStreamiNV(int video_capture_slot, int stream, int pname) {
/* 162:162 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 163:163 */    long function_pointer = caps.glGetVideoCaptureStreamivNV;
/* 164:164 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 165:165 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 166:166 */    nglGetVideoCaptureStreamivNV(video_capture_slot, stream, pname, MemoryUtil.getAddress(params), function_pointer);
/* 167:167 */    return params.get(0);
/* 168:    */  }
/* 169:    */  
/* 170:    */  public static void glGetVideoCaptureStreamNV(int video_capture_slot, int stream, int pname, FloatBuffer params) {
/* 171:171 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 172:172 */    long function_pointer = caps.glGetVideoCaptureStreamfvNV;
/* 173:173 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 174:174 */    BufferChecks.checkBuffer(params, 1);
/* 175:175 */    nglGetVideoCaptureStreamfvNV(video_capture_slot, stream, pname, MemoryUtil.getAddress(params), function_pointer);
/* 176:    */  }
/* 177:    */  
/* 178:    */  static native void nglGetVideoCaptureStreamfvNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 179:    */  
/* 180:    */  public static float glGetVideoCaptureStreamfNV(int video_capture_slot, int stream, int pname) {
/* 181:181 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 182:182 */    long function_pointer = caps.glGetVideoCaptureStreamfvNV;
/* 183:183 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 184:184 */    FloatBuffer params = APIUtil.getBufferFloat(caps);
/* 185:185 */    nglGetVideoCaptureStreamfvNV(video_capture_slot, stream, pname, MemoryUtil.getAddress(params), function_pointer);
/* 186:186 */    return params.get(0);
/* 187:    */  }
/* 188:    */  
/* 189:    */  public static void glGetVideoCaptureStreamNV(int video_capture_slot, int stream, int pname, DoubleBuffer params) {
/* 190:190 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 191:191 */    long function_pointer = caps.glGetVideoCaptureStreamdvNV;
/* 192:192 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 193:193 */    BufferChecks.checkBuffer(params, 1);
/* 194:194 */    nglGetVideoCaptureStreamdvNV(video_capture_slot, stream, pname, MemoryUtil.getAddress(params), function_pointer);
/* 195:    */  }
/* 196:    */  
/* 197:    */  static native void nglGetVideoCaptureStreamdvNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 198:    */  
/* 199:    */  public static double glGetVideoCaptureStreamdNV(int video_capture_slot, int stream, int pname) {
/* 200:200 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 201:201 */    long function_pointer = caps.glGetVideoCaptureStreamdvNV;
/* 202:202 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 203:203 */    DoubleBuffer params = APIUtil.getBufferDouble(caps);
/* 204:204 */    nglGetVideoCaptureStreamdvNV(video_capture_slot, stream, pname, MemoryUtil.getAddress(params), function_pointer);
/* 205:205 */    return params.get(0);
/* 206:    */  }
/* 207:    */  
/* 208:    */  public static int glVideoCaptureNV(int video_capture_slot, IntBuffer sequence_num, LongBuffer capture_time) {
/* 209:209 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 210:210 */    long function_pointer = caps.glVideoCaptureNV;
/* 211:211 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 212:212 */    BufferChecks.checkBuffer(sequence_num, 1);
/* 213:213 */    BufferChecks.checkBuffer(capture_time, 1);
/* 214:214 */    int __result = nglVideoCaptureNV(video_capture_slot, MemoryUtil.getAddress(sequence_num), MemoryUtil.getAddress(capture_time), function_pointer);
/* 215:215 */    return __result;
/* 216:    */  }
/* 217:    */  
/* 218:    */  static native int nglVideoCaptureNV(int paramInt, long paramLong1, long paramLong2, long paramLong3);
/* 219:    */  
/* 220:220 */  public static void glVideoCaptureStreamParameterNV(int video_capture_slot, int stream, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 221:221 */    long function_pointer = caps.glVideoCaptureStreamParameterivNV;
/* 222:222 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 223:223 */    BufferChecks.checkBuffer(params, 16);
/* 224:224 */    nglVideoCaptureStreamParameterivNV(video_capture_slot, stream, pname, MemoryUtil.getAddress(params), function_pointer);
/* 225:    */  }
/* 226:    */  
/* 227:    */  static native void nglVideoCaptureStreamParameterivNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 228:    */  
/* 229:229 */  public static void glVideoCaptureStreamParameterNV(int video_capture_slot, int stream, int pname, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 230:230 */    long function_pointer = caps.glVideoCaptureStreamParameterfvNV;
/* 231:231 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 232:232 */    BufferChecks.checkBuffer(params, 16);
/* 233:233 */    nglVideoCaptureStreamParameterfvNV(video_capture_slot, stream, pname, MemoryUtil.getAddress(params), function_pointer);
/* 234:    */  }
/* 235:    */  
/* 236:    */  static native void nglVideoCaptureStreamParameterfvNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 237:    */  
/* 238:238 */  public static void glVideoCaptureStreamParameterNV(int video_capture_slot, int stream, int pname, DoubleBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 239:239 */    long function_pointer = caps.glVideoCaptureStreamParameterdvNV;
/* 240:240 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 241:241 */    BufferChecks.checkBuffer(params, 16);
/* 242:242 */    nglVideoCaptureStreamParameterdvNV(video_capture_slot, stream, pname, MemoryUtil.getAddress(params), function_pointer);
/* 243:    */  }
/* 244:    */  
/* 245:    */  static native void nglVideoCaptureStreamParameterdvNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 246:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVVideoCapture
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */