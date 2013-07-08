/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.IntBuffer;
/*   4:    */import org.lwjgl.BufferChecks;
/*   5:    */import org.lwjgl.MemoryUtil;
/*   6:    */
/*  24:    */public final class EXTTextureInteger
/*  25:    */{
/*  26:    */  public static final int GL_RGBA_INTEGER_MODE_EXT = 36254;
/*  27:    */  public static final int GL_RGBA32UI_EXT = 36208;
/*  28:    */  public static final int GL_RGB32UI_EXT = 36209;
/*  29:    */  public static final int GL_ALPHA32UI_EXT = 36210;
/*  30:    */  public static final int GL_INTENSITY32UI_EXT = 36211;
/*  31:    */  public static final int GL_LUMINANCE32UI_EXT = 36212;
/*  32:    */  public static final int GL_LUMINANCE_ALPHA32UI_EXT = 36213;
/*  33:    */  public static final int GL_RGBA16UI_EXT = 36214;
/*  34:    */  public static final int GL_RGB16UI_EXT = 36215;
/*  35:    */  public static final int GL_ALPHA16UI_EXT = 36216;
/*  36:    */  public static final int GL_INTENSITY16UI_EXT = 36217;
/*  37:    */  public static final int GL_LUMINANCE16UI_EXT = 36218;
/*  38:    */  public static final int GL_LUMINANCE_ALPHA16UI_EXT = 36219;
/*  39:    */  public static final int GL_RGBA8UI_EXT = 36220;
/*  40:    */  public static final int GL_RGB8UI_EXT = 36221;
/*  41:    */  public static final int GL_ALPHA8UI_EXT = 36222;
/*  42:    */  public static final int GL_INTENSITY8UI_EXT = 36223;
/*  43:    */  public static final int GL_LUMINANCE8UI_EXT = 36224;
/*  44:    */  public static final int GL_LUMINANCE_ALPHA8UI_EXT = 36225;
/*  45:    */  public static final int GL_RGBA32I_EXT = 36226;
/*  46:    */  public static final int GL_RGB32I_EXT = 36227;
/*  47:    */  public static final int GL_ALPHA32I_EXT = 36228;
/*  48:    */  public static final int GL_INTENSITY32I_EXT = 36229;
/*  49:    */  public static final int GL_LUMINANCE32I_EXT = 36230;
/*  50:    */  public static final int GL_LUMINANCE_ALPHA32I_EXT = 36231;
/*  51:    */  public static final int GL_RGBA16I_EXT = 36232;
/*  52:    */  public static final int GL_RGB16I_EXT = 36233;
/*  53:    */  public static final int GL_ALPHA16I_EXT = 36234;
/*  54:    */  public static final int GL_INTENSITY16I_EXT = 36235;
/*  55:    */  public static final int GL_LUMINANCE16I_EXT = 36236;
/*  56:    */  public static final int GL_LUMINANCE_ALPHA16I_EXT = 36237;
/*  57:    */  public static final int GL_RGBA8I_EXT = 36238;
/*  58:    */  public static final int GL_RGB8I_EXT = 36239;
/*  59:    */  public static final int GL_ALPHA8I_EXT = 36240;
/*  60:    */  public static final int GL_INTENSITY8I_EXT = 36241;
/*  61:    */  public static final int GL_LUMINANCE8I_EXT = 36242;
/*  62:    */  public static final int GL_LUMINANCE_ALPHA8I_EXT = 36243;
/*  63:    */  public static final int GL_RED_INTEGER_EXT = 36244;
/*  64:    */  public static final int GL_GREEN_INTEGER_EXT = 36245;
/*  65:    */  public static final int GL_BLUE_INTEGER_EXT = 36246;
/*  66:    */  public static final int GL_ALPHA_INTEGER_EXT = 36247;
/*  67:    */  public static final int GL_RGB_INTEGER_EXT = 36248;
/*  68:    */  public static final int GL_RGBA_INTEGER_EXT = 36249;
/*  69:    */  public static final int GL_BGR_INTEGER_EXT = 36250;
/*  70:    */  public static final int GL_BGRA_INTEGER_EXT = 36251;
/*  71:    */  public static final int GL_LUMINANCE_INTEGER_EXT = 36252;
/*  72:    */  public static final int GL_LUMINANCE_ALPHA_INTEGER_EXT = 36253;
/*  73:    */  
/*  74:    */  public static void glClearColorIiEXT(int r, int g, int b, int a)
/*  75:    */  {
/*  76: 76 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  77: 77 */    long function_pointer = caps.glClearColorIiEXT;
/*  78: 78 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  79: 79 */    nglClearColorIiEXT(r, g, b, a, function_pointer);
/*  80:    */  }
/*  81:    */  
/*  82:    */  static native void nglClearColorIiEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*  83:    */  
/*  84: 84 */  public static void glClearColorIuiEXT(int r, int g, int b, int a) { ContextCapabilities caps = GLContext.getCapabilities();
/*  85: 85 */    long function_pointer = caps.glClearColorIuiEXT;
/*  86: 86 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  87: 87 */    nglClearColorIuiEXT(r, g, b, a, function_pointer);
/*  88:    */  }
/*  89:    */  
/*  90:    */  static native void nglClearColorIuiEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*  91:    */  
/*  92: 92 */  public static void glTexParameterIEXT(int target, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  93: 93 */    long function_pointer = caps.glTexParameterIivEXT;
/*  94: 94 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  95: 95 */    BufferChecks.checkBuffer(params, 4);
/*  96: 96 */    nglTexParameterIivEXT(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*  97:    */  }
/*  98:    */  
/*  99:    */  static native void nglTexParameterIivEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 100:    */  
/* 101:    */  public static void glTexParameterIiEXT(int target, int pname, int param) {
/* 102:102 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 103:103 */    long function_pointer = caps.glTexParameterIivEXT;
/* 104:104 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 105:105 */    nglTexParameterIivEXT(target, pname, APIUtil.getInt(caps, param), function_pointer);
/* 106:    */  }
/* 107:    */  
/* 108:    */  public static void glTexParameterIuEXT(int target, int pname, IntBuffer params) {
/* 109:109 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 110:110 */    long function_pointer = caps.glTexParameterIuivEXT;
/* 111:111 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 112:112 */    BufferChecks.checkBuffer(params, 4);
/* 113:113 */    nglTexParameterIuivEXT(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 114:    */  }
/* 115:    */  
/* 116:    */  static native void nglTexParameterIuivEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 117:    */  
/* 118:    */  public static void glTexParameterIuiEXT(int target, int pname, int param) {
/* 119:119 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 120:120 */    long function_pointer = caps.glTexParameterIuivEXT;
/* 121:121 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 122:122 */    nglTexParameterIuivEXT(target, pname, APIUtil.getInt(caps, param), function_pointer);
/* 123:    */  }
/* 124:    */  
/* 125:    */  public static void glGetTexParameterIEXT(int target, int pname, IntBuffer params) {
/* 126:126 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 127:127 */    long function_pointer = caps.glGetTexParameterIivEXT;
/* 128:128 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 129:129 */    BufferChecks.checkBuffer(params, 4);
/* 130:130 */    nglGetTexParameterIivEXT(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 131:    */  }
/* 132:    */  
/* 133:    */  static native void nglGetTexParameterIivEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 134:    */  
/* 135:    */  public static int glGetTexParameterIiEXT(int target, int pname) {
/* 136:136 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 137:137 */    long function_pointer = caps.glGetTexParameterIivEXT;
/* 138:138 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 139:139 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 140:140 */    nglGetTexParameterIivEXT(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 141:141 */    return params.get(0);
/* 142:    */  }
/* 143:    */  
/* 144:    */  public static void glGetTexParameterIuEXT(int target, int pname, IntBuffer params) {
/* 145:145 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 146:146 */    long function_pointer = caps.glGetTexParameterIuivEXT;
/* 147:147 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 148:148 */    BufferChecks.checkBuffer(params, 4);
/* 149:149 */    nglGetTexParameterIuivEXT(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 150:    */  }
/* 151:    */  
/* 152:    */  static native void nglGetTexParameterIuivEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 153:    */  
/* 154:    */  public static int glGetTexParameterIuiEXT(int target, int pname) {
/* 155:155 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 156:156 */    long function_pointer = caps.glGetTexParameterIuivEXT;
/* 157:157 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 158:158 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 159:159 */    nglGetTexParameterIuivEXT(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 160:160 */    return params.get(0);
/* 161:    */  }
/* 162:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTTextureInteger
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */