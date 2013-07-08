/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.IntBuffer;
/*   5:    */import org.lwjgl.BufferChecks;
/*   6:    */import org.lwjgl.MemoryUtil;
/*   7:    */
/*  16:    */public final class ARBShadingLanguageInclude
/*  17:    */{
/*  18:    */  public static final int GL_SHADER_INCLUDE_ARB = 36270;
/*  19:    */  public static final int GL_NAMED_STRING_LENGTH_ARB = 36329;
/*  20:    */  public static final int GL_NAMED_STRING_TYPE_ARB = 36330;
/*  21:    */  
/*  22:    */  public static void glNamedStringARB(int type, ByteBuffer name, ByteBuffer string)
/*  23:    */  {
/*  24: 24 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  25: 25 */    long function_pointer = caps.glNamedStringARB;
/*  26: 26 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  27: 27 */    BufferChecks.checkDirect(name);
/*  28: 28 */    BufferChecks.checkDirect(string);
/*  29: 29 */    nglNamedStringARB(type, name.remaining(), MemoryUtil.getAddress(name), string.remaining(), MemoryUtil.getAddress(string), function_pointer);
/*  30:    */  }
/*  31:    */  
/*  32:    */  static native void nglNamedStringARB(int paramInt1, int paramInt2, long paramLong1, int paramInt3, long paramLong2, long paramLong3);
/*  33:    */  
/*  34:    */  public static void glNamedStringARB(int type, CharSequence name, CharSequence string) {
/*  35: 35 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  36: 36 */    long function_pointer = caps.glNamedStringARB;
/*  37: 37 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  38: 38 */    nglNamedStringARB(type, name.length(), APIUtil.getBuffer(caps, name), string.length(), APIUtil.getBuffer(caps, string, name.length()), function_pointer);
/*  39:    */  }
/*  40:    */  
/*  41:    */  public static void glDeleteNamedStringARB(ByteBuffer name) {
/*  42: 42 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  43: 43 */    long function_pointer = caps.glDeleteNamedStringARB;
/*  44: 44 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  45: 45 */    BufferChecks.checkDirect(name);
/*  46: 46 */    nglDeleteNamedStringARB(name.remaining(), MemoryUtil.getAddress(name), function_pointer);
/*  47:    */  }
/*  48:    */  
/*  49:    */  static native void nglDeleteNamedStringARB(int paramInt, long paramLong1, long paramLong2);
/*  50:    */  
/*  51:    */  public static void glDeleteNamedStringARB(CharSequence name) {
/*  52: 52 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  53: 53 */    long function_pointer = caps.glDeleteNamedStringARB;
/*  54: 54 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  55: 55 */    nglDeleteNamedStringARB(name.length(), APIUtil.getBuffer(caps, name), function_pointer);
/*  56:    */  }
/*  57:    */  
/*  58:    */  public static void glCompileShaderIncludeARB(int shader, int count, ByteBuffer path) {
/*  59: 59 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  60: 60 */    long function_pointer = caps.glCompileShaderIncludeARB;
/*  61: 61 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  62: 62 */    BufferChecks.checkDirect(path);
/*  63: 63 */    BufferChecks.checkNullTerminated(path, count);
/*  64: 64 */    nglCompileShaderIncludeARB(shader, count, MemoryUtil.getAddress(path), 0L, function_pointer);
/*  65:    */  }
/*  66:    */  
/*  67:    */  static native void nglCompileShaderIncludeARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
/*  68:    */  
/*  69:    */  public static void glCompileShaderIncludeARB(int shader, CharSequence[] path) {
/*  70: 70 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  71: 71 */    long function_pointer = caps.glCompileShaderIncludeARB;
/*  72: 72 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  73: 73 */    BufferChecks.checkArray(path);
/*  74: 74 */    nglCompileShaderIncludeARB2(shader, path.length, APIUtil.getBuffer(caps, path), APIUtil.getLengths(caps, path), function_pointer);
/*  75:    */  }
/*  76:    */  
/*  77:    */  static native void nglCompileShaderIncludeARB2(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
/*  78:    */  
/*  79: 79 */  public static boolean glIsNamedStringARB(ByteBuffer name) { ContextCapabilities caps = GLContext.getCapabilities();
/*  80: 80 */    long function_pointer = caps.glIsNamedStringARB;
/*  81: 81 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  82: 82 */    BufferChecks.checkDirect(name);
/*  83: 83 */    boolean __result = nglIsNamedStringARB(name.remaining(), MemoryUtil.getAddress(name), function_pointer);
/*  84: 84 */    return __result;
/*  85:    */  }
/*  86:    */  
/*  87:    */  static native boolean nglIsNamedStringARB(int paramInt, long paramLong1, long paramLong2);
/*  88:    */  
/*  89:    */  public static boolean glIsNamedStringARB(CharSequence name) {
/*  90: 90 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  91: 91 */    long function_pointer = caps.glIsNamedStringARB;
/*  92: 92 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  93: 93 */    boolean __result = nglIsNamedStringARB(name.length(), APIUtil.getBuffer(caps, name), function_pointer);
/*  94: 94 */    return __result;
/*  95:    */  }
/*  96:    */  
/*  97:    */  public static void glGetNamedStringARB(ByteBuffer name, IntBuffer stringlen, ByteBuffer string) {
/*  98: 98 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  99: 99 */    long function_pointer = caps.glGetNamedStringARB;
/* 100:100 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 101:101 */    BufferChecks.checkDirect(name);
/* 102:102 */    if (stringlen != null)
/* 103:103 */      BufferChecks.checkBuffer(stringlen, 1);
/* 104:104 */    BufferChecks.checkDirect(string);
/* 105:105 */    nglGetNamedStringARB(name.remaining(), MemoryUtil.getAddress(name), string.remaining(), MemoryUtil.getAddressSafe(stringlen), MemoryUtil.getAddress(string), function_pointer);
/* 106:    */  }
/* 107:    */  
/* 108:    */  static native void nglGetNamedStringARB(int paramInt1, long paramLong1, int paramInt2, long paramLong2, long paramLong3, long paramLong4);
/* 109:    */  
/* 110:    */  public static void glGetNamedStringARB(CharSequence name, IntBuffer stringlen, ByteBuffer string) {
/* 111:111 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 112:112 */    long function_pointer = caps.glGetNamedStringARB;
/* 113:113 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 114:114 */    if (stringlen != null)
/* 115:115 */      BufferChecks.checkBuffer(stringlen, 1);
/* 116:116 */    BufferChecks.checkDirect(string);
/* 117:117 */    nglGetNamedStringARB(name.length(), APIUtil.getBuffer(caps, name), string.remaining(), MemoryUtil.getAddressSafe(stringlen), MemoryUtil.getAddress(string), function_pointer);
/* 118:    */  }
/* 119:    */  
/* 120:    */  public static String glGetNamedStringARB(CharSequence name, int bufSize)
/* 121:    */  {
/* 122:122 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 123:123 */    long function_pointer = caps.glGetNamedStringARB;
/* 124:124 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 125:125 */    IntBuffer string_length = APIUtil.getLengths(caps);
/* 126:126 */    ByteBuffer string = APIUtil.getBufferByte(caps, bufSize + name.length());
/* 127:127 */    nglGetNamedStringARB(name.length(), APIUtil.getBuffer(caps, name), bufSize, MemoryUtil.getAddress0(string_length), MemoryUtil.getAddress(string), function_pointer);
/* 128:128 */    string.limit(name.length() + string_length.get(0));
/* 129:129 */    return APIUtil.getString(caps, string);
/* 130:    */  }
/* 131:    */  
/* 132:    */  public static void glGetNamedStringARB(ByteBuffer name, int pname, IntBuffer params) {
/* 133:133 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 134:134 */    long function_pointer = caps.glGetNamedStringivARB;
/* 135:135 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 136:136 */    BufferChecks.checkDirect(name);
/* 137:137 */    BufferChecks.checkBuffer(params, 1);
/* 138:138 */    nglGetNamedStringivARB(name.remaining(), MemoryUtil.getAddress(name), pname, MemoryUtil.getAddress(params), function_pointer);
/* 139:    */  }
/* 140:    */  
/* 141:    */  static native void nglGetNamedStringivARB(int paramInt1, long paramLong1, int paramInt2, long paramLong2, long paramLong3);
/* 142:    */  
/* 143:    */  public static void glGetNamedStringiARB(CharSequence name, int pname, IntBuffer params) {
/* 144:144 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 145:145 */    long function_pointer = caps.glGetNamedStringivARB;
/* 146:146 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 147:147 */    BufferChecks.checkBuffer(params, 1);
/* 148:148 */    nglGetNamedStringivARB(name.length(), APIUtil.getBuffer(caps, name), pname, MemoryUtil.getAddress(params), function_pointer);
/* 149:    */  }
/* 150:    */  
/* 151:    */  public static int glGetNamedStringiARB(CharSequence name, int pname)
/* 152:    */  {
/* 153:153 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 154:154 */    long function_pointer = caps.glGetNamedStringivARB;
/* 155:155 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 156:156 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 157:157 */    nglGetNamedStringivARB(name.length(), APIUtil.getBuffer(caps, name), pname, MemoryUtil.getAddress(params), function_pointer);
/* 158:158 */    return params.get(0);
/* 159:    */  }
/* 160:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBShadingLanguageInclude
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */