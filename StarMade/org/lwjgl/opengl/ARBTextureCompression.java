/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import org.lwjgl.BufferChecks;
/*   5:    */import org.lwjgl.MemoryUtil;
/*   6:    */
/*   9:    */public final class ARBTextureCompression
/*  10:    */{
/*  11:    */  public static final int GL_COMPRESSED_ALPHA_ARB = 34025;
/*  12:    */  public static final int GL_COMPRESSED_LUMINANCE_ARB = 34026;
/*  13:    */  public static final int GL_COMPRESSED_LUMINANCE_ALPHA_ARB = 34027;
/*  14:    */  public static final int GL_COMPRESSED_INTENSITY_ARB = 34028;
/*  15:    */  public static final int GL_COMPRESSED_RGB_ARB = 34029;
/*  16:    */  public static final int GL_COMPRESSED_RGBA_ARB = 34030;
/*  17:    */  public static final int GL_TEXTURE_COMPRESSION_HINT_ARB = 34031;
/*  18:    */  public static final int GL_TEXTURE_COMPRESSED_IMAGE_SIZE_ARB = 34464;
/*  19:    */  public static final int GL_TEXTURE_COMPRESSED_ARB = 34465;
/*  20:    */  public static final int GL_NUM_COMPRESSED_TEXTURE_FORMATS_ARB = 34466;
/*  21:    */  public static final int GL_COMPRESSED_TEXTURE_FORMATS_ARB = 34467;
/*  22:    */  
/*  23:    */  public static void glCompressedTexImage1DARB(int target, int level, int internalformat, int width, int border, ByteBuffer pData)
/*  24:    */  {
/*  25: 25 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  26: 26 */    long function_pointer = caps.glCompressedTexImage1DARB;
/*  27: 27 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  28: 28 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  29: 29 */    BufferChecks.checkDirect(pData);
/*  30: 30 */    nglCompressedTexImage1DARB(target, level, internalformat, width, border, pData.remaining(), MemoryUtil.getAddress(pData), function_pointer); }
/*  31:    */  
/*  32:    */  static native void nglCompressedTexImage1DARB(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong1, long paramLong2);
/*  33:    */  
/*  34: 34 */  public static void glCompressedTexImage1DARB(int target, int level, int internalformat, int width, int border, int pData_imageSize, long pData_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  35: 35 */    long function_pointer = caps.glCompressedTexImage1DARB;
/*  36: 36 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  37: 37 */    GLChecks.ensureUnpackPBOenabled(caps);
/*  38: 38 */    nglCompressedTexImage1DARBBO(target, level, internalformat, width, border, pData_imageSize, pData_buffer_offset, function_pointer);
/*  39:    */  }
/*  40:    */  
/*  41:    */  static native void nglCompressedTexImage1DARBBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong1, long paramLong2);
/*  42:    */  
/*  43: 43 */  public static void glCompressedTexImage2DARB(int target, int level, int internalformat, int width, int height, int border, ByteBuffer pData) { ContextCapabilities caps = GLContext.getCapabilities();
/*  44: 44 */    long function_pointer = caps.glCompressedTexImage2DARB;
/*  45: 45 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  46: 46 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  47: 47 */    BufferChecks.checkDirect(pData);
/*  48: 48 */    nglCompressedTexImage2DARB(target, level, internalformat, width, height, border, pData.remaining(), MemoryUtil.getAddress(pData), function_pointer); }
/*  49:    */  
/*  50:    */  static native void nglCompressedTexImage2DARB(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong1, long paramLong2);
/*  51:    */  
/*  52: 52 */  public static void glCompressedTexImage2DARB(int target, int level, int internalformat, int width, int height, int border, int pData_imageSize, long pData_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  53: 53 */    long function_pointer = caps.glCompressedTexImage2DARB;
/*  54: 54 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  55: 55 */    GLChecks.ensureUnpackPBOenabled(caps);
/*  56: 56 */    nglCompressedTexImage2DARBBO(target, level, internalformat, width, height, border, pData_imageSize, pData_buffer_offset, function_pointer);
/*  57:    */  }
/*  58:    */  
/*  59:    */  static native void nglCompressedTexImage2DARBBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong1, long paramLong2);
/*  60:    */  
/*  61: 61 */  public static void glCompressedTexImage3DARB(int target, int level, int internalformat, int width, int height, int depth, int border, ByteBuffer pData) { ContextCapabilities caps = GLContext.getCapabilities();
/*  62: 62 */    long function_pointer = caps.glCompressedTexImage3DARB;
/*  63: 63 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  64: 64 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  65: 65 */    BufferChecks.checkDirect(pData);
/*  66: 66 */    nglCompressedTexImage3DARB(target, level, internalformat, width, height, depth, border, pData.remaining(), MemoryUtil.getAddress(pData), function_pointer); }
/*  67:    */  
/*  68:    */  static native void nglCompressedTexImage3DARB(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong1, long paramLong2);
/*  69:    */  
/*  70: 70 */  public static void glCompressedTexImage3DARB(int target, int level, int internalformat, int width, int height, int depth, int border, int pData_imageSize, long pData_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  71: 71 */    long function_pointer = caps.glCompressedTexImage3DARB;
/*  72: 72 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  73: 73 */    GLChecks.ensureUnpackPBOenabled(caps);
/*  74: 74 */    nglCompressedTexImage3DARBBO(target, level, internalformat, width, height, depth, border, pData_imageSize, pData_buffer_offset, function_pointer);
/*  75:    */  }
/*  76:    */  
/*  77:    */  static native void nglCompressedTexImage3DARBBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong1, long paramLong2);
/*  78:    */  
/*  79: 79 */  public static void glCompressedTexSubImage1DARB(int target, int level, int xoffset, int width, int format, ByteBuffer pData) { ContextCapabilities caps = GLContext.getCapabilities();
/*  80: 80 */    long function_pointer = caps.glCompressedTexSubImage1DARB;
/*  81: 81 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  82: 82 */    GLChecks.ensureUnpackPBOdisabled(caps);
/*  83: 83 */    BufferChecks.checkDirect(pData);
/*  84: 84 */    nglCompressedTexSubImage1DARB(target, level, xoffset, width, format, pData.remaining(), MemoryUtil.getAddress(pData), function_pointer); }
/*  85:    */  
/*  86:    */  static native void nglCompressedTexSubImage1DARB(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong1, long paramLong2);
/*  87:    */  
/*  88: 88 */  public static void glCompressedTexSubImage1DARB(int target, int level, int xoffset, int width, int format, int pData_imageSize, long pData_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  89: 89 */    long function_pointer = caps.glCompressedTexSubImage1DARB;
/*  90: 90 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  91: 91 */    GLChecks.ensureUnpackPBOenabled(caps);
/*  92: 92 */    nglCompressedTexSubImage1DARBBO(target, level, xoffset, width, format, pData_imageSize, pData_buffer_offset, function_pointer);
/*  93:    */  }
/*  94:    */  
/*  95:    */  static native void nglCompressedTexSubImage1DARBBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong1, long paramLong2);
/*  96:    */  
/*  97: 97 */  public static void glCompressedTexSubImage2DARB(int target, int level, int xoffset, int yoffset, int width, int height, int format, ByteBuffer pData) { ContextCapabilities caps = GLContext.getCapabilities();
/*  98: 98 */    long function_pointer = caps.glCompressedTexSubImage2DARB;
/*  99: 99 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 100:100 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 101:101 */    BufferChecks.checkDirect(pData);
/* 102:102 */    nglCompressedTexSubImage2DARB(target, level, xoffset, yoffset, width, height, format, pData.remaining(), MemoryUtil.getAddress(pData), function_pointer); }
/* 103:    */  
/* 104:    */  static native void nglCompressedTexSubImage2DARB(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong1, long paramLong2);
/* 105:    */  
/* 106:106 */  public static void glCompressedTexSubImage2DARB(int target, int level, int xoffset, int yoffset, int width, int height, int format, int pData_imageSize, long pData_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 107:107 */    long function_pointer = caps.glCompressedTexSubImage2DARB;
/* 108:108 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 109:109 */    GLChecks.ensureUnpackPBOenabled(caps);
/* 110:110 */    nglCompressedTexSubImage2DARBBO(target, level, xoffset, yoffset, width, height, format, pData_imageSize, pData_buffer_offset, function_pointer);
/* 111:    */  }
/* 112:    */  
/* 113:    */  static native void nglCompressedTexSubImage2DARBBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, long paramLong1, long paramLong2);
/* 114:    */  
/* 115:115 */  public static void glCompressedTexSubImage3DARB(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, ByteBuffer pData) { ContextCapabilities caps = GLContext.getCapabilities();
/* 116:116 */    long function_pointer = caps.glCompressedTexSubImage3DARB;
/* 117:117 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 118:118 */    GLChecks.ensureUnpackPBOdisabled(caps);
/* 119:119 */    BufferChecks.checkDirect(pData);
/* 120:120 */    nglCompressedTexSubImage3DARB(target, level, xoffset, yoffset, zoffset, width, height, depth, format, pData.remaining(), MemoryUtil.getAddress(pData), function_pointer); }
/* 121:    */  
/* 122:    */  static native void nglCompressedTexSubImage3DARB(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, long paramLong1, long paramLong2);
/* 123:    */  
/* 124:124 */  public static void glCompressedTexSubImage3DARB(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int pData_imageSize, long pData_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 125:125 */    long function_pointer = caps.glCompressedTexSubImage3DARB;
/* 126:126 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 127:127 */    GLChecks.ensureUnpackPBOenabled(caps);
/* 128:128 */    nglCompressedTexSubImage3DARBBO(target, level, xoffset, yoffset, zoffset, width, height, depth, format, pData_imageSize, pData_buffer_offset, function_pointer);
/* 129:    */  }
/* 130:    */  
/* 131:    */  static native void nglCompressedTexSubImage3DARBBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, long paramLong1, long paramLong2);
/* 132:    */  
/* 133:133 */  public static void glGetCompressedTexImageARB(int target, int lod, ByteBuffer pImg) { ContextCapabilities caps = GLContext.getCapabilities();
/* 134:134 */    long function_pointer = caps.glGetCompressedTexImageARB;
/* 135:135 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 136:136 */    GLChecks.ensurePackPBOdisabled(caps);
/* 137:137 */    BufferChecks.checkDirect(pImg);
/* 138:138 */    nglGetCompressedTexImageARB(target, lod, MemoryUtil.getAddress(pImg), function_pointer); }
/* 139:    */  
/* 140:    */  static native void nglGetCompressedTexImageARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 141:    */  
/* 142:142 */  public static void glGetCompressedTexImageARB(int target, int lod, long pImg_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 143:143 */    long function_pointer = caps.glGetCompressedTexImageARB;
/* 144:144 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 145:145 */    GLChecks.ensurePackPBOenabled(caps);
/* 146:146 */    nglGetCompressedTexImageARBBO(target, lod, pImg_buffer_offset, function_pointer);
/* 147:    */  }
/* 148:    */  
/* 149:    */  static native void nglGetCompressedTexImageARBBO(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 150:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBTextureCompression
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */