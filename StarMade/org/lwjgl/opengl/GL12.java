/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.DoubleBuffer;
/*   5:    */import java.nio.FloatBuffer;
/*   6:    */import java.nio.IntBuffer;
/*   7:    */import java.nio.ShortBuffer;
/*   8:    */import org.lwjgl.BufferChecks;
/*   9:    */import org.lwjgl.MemoryUtil;
/*  10:    */
/*  17:    */public final class GL12
/*  18:    */{
/*  19:    */  public static final int GL_TEXTURE_BINDING_3D = 32874;
/*  20:    */  public static final int GL_PACK_SKIP_IMAGES = 32875;
/*  21:    */  public static final int GL_PACK_IMAGE_HEIGHT = 32876;
/*  22:    */  public static final int GL_UNPACK_SKIP_IMAGES = 32877;
/*  23:    */  public static final int GL_UNPACK_IMAGE_HEIGHT = 32878;
/*  24:    */  public static final int GL_TEXTURE_3D = 32879;
/*  25:    */  public static final int GL_PROXY_TEXTURE_3D = 32880;
/*  26:    */  public static final int GL_TEXTURE_DEPTH = 32881;
/*  27:    */  public static final int GL_TEXTURE_WRAP_R = 32882;
/*  28:    */  public static final int GL_MAX_3D_TEXTURE_SIZE = 32883;
/*  29:    */  public static final int GL_BGR = 32992;
/*  30:    */  public static final int GL_BGRA = 32993;
/*  31:    */  public static final int GL_UNSIGNED_BYTE_3_3_2 = 32818;
/*  32:    */  public static final int GL_UNSIGNED_BYTE_2_3_3_REV = 33634;
/*  33:    */  public static final int GL_UNSIGNED_SHORT_5_6_5 = 33635;
/*  34:    */  public static final int GL_UNSIGNED_SHORT_5_6_5_REV = 33636;
/*  35:    */  public static final int GL_UNSIGNED_SHORT_4_4_4_4 = 32819;
/*  36:    */  public static final int GL_UNSIGNED_SHORT_4_4_4_4_REV = 33637;
/*  37:    */  public static final int GL_UNSIGNED_SHORT_5_5_5_1 = 32820;
/*  38:    */  public static final int GL_UNSIGNED_SHORT_1_5_5_5_REV = 33638;
/*  39:    */  public static final int GL_UNSIGNED_INT_8_8_8_8 = 32821;
/*  40:    */  public static final int GL_UNSIGNED_INT_8_8_8_8_REV = 33639;
/*  41:    */  public static final int GL_UNSIGNED_INT_10_10_10_2 = 32822;
/*  42:    */  public static final int GL_UNSIGNED_INT_2_10_10_10_REV = 33640;
/*  43:    */  public static final int GL_RESCALE_NORMAL = 32826;
/*  44:    */  public static final int GL_LIGHT_MODEL_COLOR_CONTROL = 33272;
/*  45:    */  public static final int GL_SINGLE_COLOR = 33273;
/*  46:    */  public static final int GL_SEPARATE_SPECULAR_COLOR = 33274;
/*  47:    */  public static final int GL_CLAMP_TO_EDGE = 33071;
/*  48:    */  public static final int GL_TEXTURE_MIN_LOD = 33082;
/*  49:    */  public static final int GL_TEXTURE_MAX_LOD = 33083;
/*  50:    */  public static final int GL_TEXTURE_BASE_LEVEL = 33084;
/*  51:    */  public static final int GL_TEXTURE_MAX_LEVEL = 33085;
/*  52:    */  public static final int GL_MAX_ELEMENTS_VERTICES = 33000;
/*  53:    */  public static final int GL_MAX_ELEMENTS_INDICES = 33001;
/*  54:    */  public static final int GL_ALIASED_POINT_SIZE_RANGE = 33901;
/*  55:    */  public static final int GL_ALIASED_LINE_WIDTH_RANGE = 33902;
/*  56:    */  public static final int GL_SMOOTH_POINT_SIZE_RANGE = 2834;
/*  57:    */  public static final int GL_SMOOTH_POINT_SIZE_GRANULARITY = 2835;
/*  58:    */  public static final int GL_SMOOTH_LINE_WIDTH_RANGE = 2850;
/*  59:    */  public static final int GL_SMOOTH_LINE_WIDTH_GRANULARITY = 2851;
/*  60:    */  
/*  61:    */  public static void glDrawRangeElements(int mode, int start, int end, ByteBuffer indices)
/*  62:    */  {
/*  63: 63 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  64: 64 */    long function_pointer = caps.glDrawRangeElements;
/*  65: 65 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  66: 66 */    GLChecks.ensureElementVBOdisabled(caps);
/*  67: 67 */    BufferChecks.checkDirect(indices);
/*  68: 68 */    nglDrawRangeElements(mode, start, end, indices.remaining(), 5121, MemoryUtil.getAddress(indices), function_pointer);
/*  69:    */  }
/*  70:    */  
/*  71: 71 */  public static void glDrawRangeElements(int mode, int start, int end, IntBuffer indices) { ContextCapabilities caps = GLContext.getCapabilities();
/*  72: 72 */    long function_pointer = caps.glDrawRangeElements;
/*  73: 73 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  74: 74 */    GLChecks.ensureElementVBOdisabled(caps);
/*  75: 75 */    BufferChecks.checkDirect(indices);
/*  76: 76 */    nglDrawRangeElements(mode, start, end, indices.remaining(), 5125, MemoryUtil.getAddress(indices), function_pointer);
/*  77:    */  }
/*  78:    */  
/*  79: 79 */  public static void glDrawRangeElements(int mode, int start, int end, ShortBuffer indices) { ContextCapabilities caps = GLContext.getCapabilities();
/*  80: 80 */    long function_pointer = caps.glDrawRangeElements;
/*  81: 81 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  82: 82 */    GLChecks.ensureElementVBOdisabled(caps);
/*  83: 83 */    BufferChecks.checkDirect(indices);
/*  84: 84 */    nglDrawRangeElements(mode, start, end, indices.remaining(), 5123, MemoryUtil.getAddress(indices), function_pointer); }
/*  85:    */  
/*  86:    */  static native void nglDrawRangeElements(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/*  87:    */  
/*  88: 88 */  public static void glDrawRangeElements(int mode, int start, int end, int indices_count, int type, long indices_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  89: 89 */    long function_pointer = caps.glDrawRangeElements;
/*  90: 90 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  91: 91 */    GLChecks.ensureElementVBOenabled(caps);
/*  92: 92 */    nglDrawRangeElementsBO(mode, start, end, indices_count, type, indices_buffer_offset, function_pointer);
/*  93:    */  }
/*  94:    */  
/*  95:    */  static native void nglDrawRangeElementsBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/*  96:    */  
/*  97: 97 */  public static void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, ByteBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/*  98: 98 */    long function_pointer = caps.glTexImage3D;
/*  99: 99 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 100:100 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 101:101 */    if (pixels != null)
/* 102:102 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage3DStorage(pixels, format, type, width, height, depth));
/* 103:103 */    nglTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/* 104:    */  }
/* 105:    */  
/* 106:106 */  public static void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, DoubleBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 107:107 */    long function_pointer = caps.glTexImage3D;
/* 108:108 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 109:109 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 110:110 */    if (pixels != null)
/* 111:111 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage3DStorage(pixels, format, type, width, height, depth));
/* 112:112 */    nglTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/* 113:    */  }
/* 114:    */  
/* 115:115 */  public static void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, FloatBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 116:116 */    long function_pointer = caps.glTexImage3D;
/* 117:117 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 118:118 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 119:119 */    if (pixels != null)
/* 120:120 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage3DStorage(pixels, format, type, width, height, depth));
/* 121:121 */    nglTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/* 122:    */  }
/* 123:    */  
/* 124:124 */  public static void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, IntBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 125:125 */    long function_pointer = caps.glTexImage3D;
/* 126:126 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 127:127 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 128:128 */    if (pixels != null)
/* 129:129 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage3DStorage(pixels, format, type, width, height, depth));
/* 130:130 */    nglTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer);
/* 131:    */  }
/* 132:    */  
/* 133:133 */  public static void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, ShortBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 134:134 */    long function_pointer = caps.glTexImage3D;
/* 135:135 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 136:136 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 137:137 */    if (pixels != null)
/* 138:138 */      BufferChecks.checkBuffer(pixels, GLChecks.calculateTexImage3DStorage(pixels, format, type, width, height, depth));
/* 139:139 */    nglTexImage3D(target, level, internalFormat, width, height, depth, border, format, type, MemoryUtil.getAddressSafe(pixels), function_pointer); }
/* 140:    */  
/* 141:    */  static native void nglTexImage3D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong1, long paramLong2);
/* 142:    */  
/* 143:143 */  public static void glTexImage3D(int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 144:144 */    long function_pointer = caps.glTexImage3D;
/* 145:145 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 146:146 */    GLChecks.ensureUnpackPBOenabled(caps);
/* 147:147 */    nglTexImage3DBO(target, level, internalFormat, width, height, depth, border, format, type, pixels_buffer_offset, function_pointer);
/* 148:    */  }
/* 149:    */  
/* 150:    */  static native void nglTexImage3DBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong1, long paramLong2);
/* 151:    */  
/* 152:152 */  public static void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, ByteBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 153:153 */    long function_pointer = caps.glTexSubImage3D;
/* 154:154 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 155:155 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 156:156 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, depth));
/* 157:157 */    nglTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/* 158:    */  }
/* 159:    */  
/* 160:160 */  public static void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, DoubleBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 161:161 */    long function_pointer = caps.glTexSubImage3D;
/* 162:162 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 163:163 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 164:164 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, depth));
/* 165:165 */    nglTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/* 166:    */  }
/* 167:    */  
/* 168:168 */  public static void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, FloatBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 169:169 */    long function_pointer = caps.glTexSubImage3D;
/* 170:170 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 171:171 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 172:172 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, depth));
/* 173:173 */    nglTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/* 174:    */  }
/* 175:    */  
/* 176:176 */  public static void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, IntBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 177:177 */    long function_pointer = caps.glTexSubImage3D;
/* 178:178 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 179:179 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 180:180 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, depth));
/* 181:181 */    nglTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, MemoryUtil.getAddress(pixels), function_pointer);
/* 182:    */  }
/* 183:    */  
/* 184:184 */  public static void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, ShortBuffer pixels) { ContextCapabilities caps = GLContext.getCapabilities();
/* 185:185 */    long function_pointer = caps.glTexSubImage3D;
/* 186:186 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 187:187 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 188:188 */    BufferChecks.checkBuffer(pixels, GLChecks.calculateImageStorage(pixels, format, type, width, height, depth));
/* 189:189 */    nglTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, MemoryUtil.getAddress(pixels), function_pointer); }
/* 190:    */  
/* 191:    */  static native void nglTexSubImage3D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, long paramLong1, long paramLong2);
/* 192:    */  
/* 193:193 */  public static void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, long pixels_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 194:194 */    long function_pointer = caps.glTexSubImage3D;
/* 195:195 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 196:196 */    GLChecks.ensureUnpackPBOenabled(caps);
/* 197:197 */    nglTexSubImage3DBO(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels_buffer_offset, function_pointer);
/* 198:    */  }
/* 199:    */  
/* 200:    */  static native void nglTexSubImage3DBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, long paramLong1, long paramLong2);
/* 201:    */  
/* 202:202 */  public static void glCopyTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int x, int y, int width, int height) { ContextCapabilities caps = GLContext.getCapabilities();
/* 203:203 */    long function_pointer = caps.glCopyTexSubImage3D;
/* 204:204 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 205:205 */    nglCopyTexSubImage3D(target, level, xoffset, yoffset, zoffset, x, y, width, height, function_pointer);
/* 206:    */  }
/* 207:    */  
/* 208:    */  static native void nglCopyTexSubImage3D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, long paramLong);
/* 209:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.GL12
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */