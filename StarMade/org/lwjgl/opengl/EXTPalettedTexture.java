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
/*  22:    */public final class EXTPalettedTexture
/*  23:    */{
/*  24:    */  public static final int GL_COLOR_INDEX1_EXT = 32994;
/*  25:    */  public static final int GL_COLOR_INDEX2_EXT = 32995;
/*  26:    */  public static final int GL_COLOR_INDEX4_EXT = 32996;
/*  27:    */  public static final int GL_COLOR_INDEX8_EXT = 32997;
/*  28:    */  public static final int GL_COLOR_INDEX12_EXT = 32998;
/*  29:    */  public static final int GL_COLOR_INDEX16_EXT = 32999;
/*  30:    */  public static final int GL_COLOR_TABLE_FORMAT_EXT = 32984;
/*  31:    */  public static final int GL_COLOR_TABLE_WIDTH_EXT = 32985;
/*  32:    */  public static final int GL_COLOR_TABLE_RED_SIZE_EXT = 32986;
/*  33:    */  public static final int GL_COLOR_TABLE_GREEN_SIZE_EXT = 32987;
/*  34:    */  public static final int GL_COLOR_TABLE_BLUE_SIZE_EXT = 32988;
/*  35:    */  public static final int GL_COLOR_TABLE_ALPHA_SIZE_EXT = 32989;
/*  36:    */  public static final int GL_COLOR_TABLE_LUMINANCE_SIZE_EXT = 32990;
/*  37:    */  public static final int GL_COLOR_TABLE_INTENSITY_SIZE_EXT = 32991;
/*  38:    */  public static final int GL_TEXTURE_INDEX_SIZE_EXT = 33005;
/*  39:    */  
/*  40:    */  public static void glColorTableEXT(int target, int internalFormat, int width, int format, int type, ByteBuffer data)
/*  41:    */  {
/*  42: 42 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  43: 43 */    long function_pointer = caps.glColorTableEXT;
/*  44: 44 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  45: 45 */    BufferChecks.checkBuffer(data, GLChecks.calculateImageStorage(data, format, type, width, 1, 1));
/*  46: 46 */    nglColorTableEXT(target, internalFormat, width, format, type, MemoryUtil.getAddress(data), function_pointer);
/*  47:    */  }
/*  48:    */  
/*  49: 49 */  public static void glColorTableEXT(int target, int internalFormat, int width, int format, int type, DoubleBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/*  50: 50 */    long function_pointer = caps.glColorTableEXT;
/*  51: 51 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  52: 52 */    BufferChecks.checkBuffer(data, GLChecks.calculateImageStorage(data, format, type, width, 1, 1));
/*  53: 53 */    nglColorTableEXT(target, internalFormat, width, format, type, MemoryUtil.getAddress(data), function_pointer);
/*  54:    */  }
/*  55:    */  
/*  56: 56 */  public static void glColorTableEXT(int target, int internalFormat, int width, int format, int type, FloatBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/*  57: 57 */    long function_pointer = caps.glColorTableEXT;
/*  58: 58 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  59: 59 */    BufferChecks.checkBuffer(data, GLChecks.calculateImageStorage(data, format, type, width, 1, 1));
/*  60: 60 */    nglColorTableEXT(target, internalFormat, width, format, type, MemoryUtil.getAddress(data), function_pointer);
/*  61:    */  }
/*  62:    */  
/*  63: 63 */  public static void glColorTableEXT(int target, int internalFormat, int width, int format, int type, IntBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/*  64: 64 */    long function_pointer = caps.glColorTableEXT;
/*  65: 65 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  66: 66 */    BufferChecks.checkBuffer(data, GLChecks.calculateImageStorage(data, format, type, width, 1, 1));
/*  67: 67 */    nglColorTableEXT(target, internalFormat, width, format, type, MemoryUtil.getAddress(data), function_pointer);
/*  68:    */  }
/*  69:    */  
/*  70: 70 */  public static void glColorTableEXT(int target, int internalFormat, int width, int format, int type, ShortBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/*  71: 71 */    long function_pointer = caps.glColorTableEXT;
/*  72: 72 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  73: 73 */    BufferChecks.checkBuffer(data, GLChecks.calculateImageStorage(data, format, type, width, 1, 1));
/*  74: 74 */    nglColorTableEXT(target, internalFormat, width, format, type, MemoryUtil.getAddress(data), function_pointer);
/*  75:    */  }
/*  76:    */  
/*  77:    */  static native void nglColorTableEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/*  78:    */  
/*  79: 79 */  public static void glColorSubTableEXT(int target, int start, int count, int format, int type, ByteBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/*  80: 80 */    long function_pointer = caps.glColorSubTableEXT;
/*  81: 81 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  82: 82 */    BufferChecks.checkBuffer(data, GLChecks.calculateImageStorage(data, format, type, count, 1, 1));
/*  83: 83 */    nglColorSubTableEXT(target, start, count, format, type, MemoryUtil.getAddress(data), function_pointer);
/*  84:    */  }
/*  85:    */  
/*  86: 86 */  public static void glColorSubTableEXT(int target, int start, int count, int format, int type, DoubleBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/*  87: 87 */    long function_pointer = caps.glColorSubTableEXT;
/*  88: 88 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  89: 89 */    BufferChecks.checkBuffer(data, GLChecks.calculateImageStorage(data, format, type, count, 1, 1));
/*  90: 90 */    nglColorSubTableEXT(target, start, count, format, type, MemoryUtil.getAddress(data), function_pointer);
/*  91:    */  }
/*  92:    */  
/*  93: 93 */  public static void glColorSubTableEXT(int target, int start, int count, int format, int type, FloatBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/*  94: 94 */    long function_pointer = caps.glColorSubTableEXT;
/*  95: 95 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  96: 96 */    BufferChecks.checkBuffer(data, GLChecks.calculateImageStorage(data, format, type, count, 1, 1));
/*  97: 97 */    nglColorSubTableEXT(target, start, count, format, type, MemoryUtil.getAddress(data), function_pointer);
/*  98:    */  }
/*  99:    */  
/* 100:100 */  public static void glColorSubTableEXT(int target, int start, int count, int format, int type, IntBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 101:101 */    long function_pointer = caps.glColorSubTableEXT;
/* 102:102 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 103:103 */    BufferChecks.checkBuffer(data, GLChecks.calculateImageStorage(data, format, type, count, 1, 1));
/* 104:104 */    nglColorSubTableEXT(target, start, count, format, type, MemoryUtil.getAddress(data), function_pointer);
/* 105:    */  }
/* 106:    */  
/* 107:107 */  public static void glColorSubTableEXT(int target, int start, int count, int format, int type, ShortBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 108:108 */    long function_pointer = caps.glColorSubTableEXT;
/* 109:109 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 110:110 */    BufferChecks.checkBuffer(data, GLChecks.calculateImageStorage(data, format, type, count, 1, 1));
/* 111:111 */    nglColorSubTableEXT(target, start, count, format, type, MemoryUtil.getAddress(data), function_pointer);
/* 112:    */  }
/* 113:    */  
/* 114:    */  static native void nglColorSubTableEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/* 115:    */  
/* 116:116 */  public static void glGetColorTableEXT(int target, int format, int type, ByteBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 117:117 */    long function_pointer = caps.glGetColorTableEXT;
/* 118:118 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 119:119 */    BufferChecks.checkDirect(data);
/* 120:120 */    nglGetColorTableEXT(target, format, type, MemoryUtil.getAddress(data), function_pointer);
/* 121:    */  }
/* 122:    */  
/* 123:123 */  public static void glGetColorTableEXT(int target, int format, int type, DoubleBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 124:124 */    long function_pointer = caps.glGetColorTableEXT;
/* 125:125 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 126:126 */    BufferChecks.checkDirect(data);
/* 127:127 */    nglGetColorTableEXT(target, format, type, MemoryUtil.getAddress(data), function_pointer);
/* 128:    */  }
/* 129:    */  
/* 130:130 */  public static void glGetColorTableEXT(int target, int format, int type, FloatBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 131:131 */    long function_pointer = caps.glGetColorTableEXT;
/* 132:132 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 133:133 */    BufferChecks.checkDirect(data);
/* 134:134 */    nglGetColorTableEXT(target, format, type, MemoryUtil.getAddress(data), function_pointer);
/* 135:    */  }
/* 136:    */  
/* 137:137 */  public static void glGetColorTableEXT(int target, int format, int type, IntBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 138:138 */    long function_pointer = caps.glGetColorTableEXT;
/* 139:139 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 140:140 */    BufferChecks.checkDirect(data);
/* 141:141 */    nglGetColorTableEXT(target, format, type, MemoryUtil.getAddress(data), function_pointer);
/* 142:    */  }
/* 143:    */  
/* 144:144 */  public static void glGetColorTableEXT(int target, int format, int type, ShortBuffer data) { ContextCapabilities caps = GLContext.getCapabilities();
/* 145:145 */    long function_pointer = caps.glGetColorTableEXT;
/* 146:146 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 147:147 */    BufferChecks.checkDirect(data);
/* 148:148 */    nglGetColorTableEXT(target, format, type, MemoryUtil.getAddress(data), function_pointer);
/* 149:    */  }
/* 150:    */  
/* 151:    */  static native void nglGetColorTableEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 152:    */  
/* 153:153 */  public static void glGetColorTableParameterEXT(int target, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 154:154 */    long function_pointer = caps.glGetColorTableParameterivEXT;
/* 155:155 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 156:156 */    BufferChecks.checkBuffer(params, 4);
/* 157:157 */    nglGetColorTableParameterivEXT(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 158:    */  }
/* 159:    */  
/* 160:    */  static native void nglGetColorTableParameterivEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 161:    */  
/* 162:162 */  public static void glGetColorTableParameterEXT(int target, int pname, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 163:163 */    long function_pointer = caps.glGetColorTableParameterfvEXT;
/* 164:164 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 165:165 */    BufferChecks.checkBuffer(params, 4);
/* 166:166 */    nglGetColorTableParameterfvEXT(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 167:    */  }
/* 168:    */  
/* 169:    */  static native void nglGetColorTableParameterfvEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 170:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTPalettedTexture
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */