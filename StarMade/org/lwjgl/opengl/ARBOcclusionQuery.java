/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.IntBuffer;
/*   4:    */import org.lwjgl.BufferChecks;
/*   5:    */import org.lwjgl.MemoryUtil;
/*   6:    */
/*  22:    */public final class ARBOcclusionQuery
/*  23:    */{
/*  24:    */  public static final int GL_SAMPLES_PASSED_ARB = 35092;
/*  25:    */  public static final int GL_QUERY_COUNTER_BITS_ARB = 34916;
/*  26:    */  public static final int GL_CURRENT_QUERY_ARB = 34917;
/*  27:    */  public static final int GL_QUERY_RESULT_ARB = 34918;
/*  28:    */  public static final int GL_QUERY_RESULT_AVAILABLE_ARB = 34919;
/*  29:    */  
/*  30:    */  public static void glGenQueriesARB(IntBuffer ids)
/*  31:    */  {
/*  32: 32 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  33: 33 */    long function_pointer = caps.glGenQueriesARB;
/*  34: 34 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  35: 35 */    BufferChecks.checkDirect(ids);
/*  36: 36 */    nglGenQueriesARB(ids.remaining(), MemoryUtil.getAddress(ids), function_pointer);
/*  37:    */  }
/*  38:    */  
/*  39:    */  static native void nglGenQueriesARB(int paramInt, long paramLong1, long paramLong2);
/*  40:    */  
/*  41:    */  public static int glGenQueriesARB() {
/*  42: 42 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  43: 43 */    long function_pointer = caps.glGenQueriesARB;
/*  44: 44 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  45: 45 */    IntBuffer ids = APIUtil.getBufferInt(caps);
/*  46: 46 */    nglGenQueriesARB(1, MemoryUtil.getAddress(ids), function_pointer);
/*  47: 47 */    return ids.get(0);
/*  48:    */  }
/*  49:    */  
/*  50:    */  public static void glDeleteQueriesARB(IntBuffer ids) {
/*  51: 51 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  52: 52 */    long function_pointer = caps.glDeleteQueriesARB;
/*  53: 53 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  54: 54 */    BufferChecks.checkDirect(ids);
/*  55: 55 */    nglDeleteQueriesARB(ids.remaining(), MemoryUtil.getAddress(ids), function_pointer);
/*  56:    */  }
/*  57:    */  
/*  58:    */  static native void nglDeleteQueriesARB(int paramInt, long paramLong1, long paramLong2);
/*  59:    */  
/*  60:    */  public static void glDeleteQueriesARB(int id) {
/*  61: 61 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  62: 62 */    long function_pointer = caps.glDeleteQueriesARB;
/*  63: 63 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  64: 64 */    nglDeleteQueriesARB(1, APIUtil.getInt(caps, id), function_pointer);
/*  65:    */  }
/*  66:    */  
/*  67:    */  public static boolean glIsQueryARB(int id) {
/*  68: 68 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  69: 69 */    long function_pointer = caps.glIsQueryARB;
/*  70: 70 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  71: 71 */    boolean __result = nglIsQueryARB(id, function_pointer);
/*  72: 72 */    return __result;
/*  73:    */  }
/*  74:    */  
/*  75:    */  static native boolean nglIsQueryARB(int paramInt, long paramLong);
/*  76:    */  
/*  77: 77 */  public static void glBeginQueryARB(int target, int id) { ContextCapabilities caps = GLContext.getCapabilities();
/*  78: 78 */    long function_pointer = caps.glBeginQueryARB;
/*  79: 79 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  80: 80 */    nglBeginQueryARB(target, id, function_pointer);
/*  81:    */  }
/*  82:    */  
/*  83:    */  static native void nglBeginQueryARB(int paramInt1, int paramInt2, long paramLong);
/*  84:    */  
/*  85: 85 */  public static void glEndQueryARB(int target) { ContextCapabilities caps = GLContext.getCapabilities();
/*  86: 86 */    long function_pointer = caps.glEndQueryARB;
/*  87: 87 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  88: 88 */    nglEndQueryARB(target, function_pointer);
/*  89:    */  }
/*  90:    */  
/*  91:    */  static native void nglEndQueryARB(int paramInt, long paramLong);
/*  92:    */  
/*  93: 93 */  public static void glGetQueryARB(int target, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  94: 94 */    long function_pointer = caps.glGetQueryivARB;
/*  95: 95 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  96: 96 */    BufferChecks.checkBuffer(params, 1);
/*  97: 97 */    nglGetQueryivARB(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*  98:    */  }
/*  99:    */  
/* 102:    */  static native void nglGetQueryivARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 103:    */  
/* 105:    */  @Deprecated
/* 106:    */  public static int glGetQueryARB(int target, int pname)
/* 107:    */  {
/* 108:108 */    return glGetQueryiARB(target, pname);
/* 109:    */  }
/* 110:    */  
/* 111:    */  public static int glGetQueryiARB(int target, int pname)
/* 112:    */  {
/* 113:113 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 114:114 */    long function_pointer = caps.glGetQueryivARB;
/* 115:115 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 116:116 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 117:117 */    nglGetQueryivARB(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 118:118 */    return params.get(0);
/* 119:    */  }
/* 120:    */  
/* 121:    */  public static void glGetQueryObjectARB(int id, int pname, IntBuffer params) {
/* 122:122 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 123:123 */    long function_pointer = caps.glGetQueryObjectivARB;
/* 124:124 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 125:125 */    BufferChecks.checkBuffer(params, 1);
/* 126:126 */    nglGetQueryObjectivARB(id, pname, MemoryUtil.getAddress(params), function_pointer);
/* 127:    */  }
/* 128:    */  
/* 129:    */  static native void nglGetQueryObjectivARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 130:    */  
/* 131:    */  public static int glGetQueryObjectiARB(int id, int pname) {
/* 132:132 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 133:133 */    long function_pointer = caps.glGetQueryObjectivARB;
/* 134:134 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 135:135 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 136:136 */    nglGetQueryObjectivARB(id, pname, MemoryUtil.getAddress(params), function_pointer);
/* 137:137 */    return params.get(0);
/* 138:    */  }
/* 139:    */  
/* 140:    */  public static void glGetQueryObjectuARB(int id, int pname, IntBuffer params) {
/* 141:141 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 142:142 */    long function_pointer = caps.glGetQueryObjectuivARB;
/* 143:143 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 144:144 */    BufferChecks.checkBuffer(params, 1);
/* 145:145 */    nglGetQueryObjectuivARB(id, pname, MemoryUtil.getAddress(params), function_pointer);
/* 146:    */  }
/* 147:    */  
/* 148:    */  static native void nglGetQueryObjectuivARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 149:    */  
/* 150:    */  public static int glGetQueryObjectuiARB(int id, int pname) {
/* 151:151 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 152:152 */    long function_pointer = caps.glGetQueryObjectuivARB;
/* 153:153 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 154:154 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 155:155 */    nglGetQueryObjectuivARB(id, pname, MemoryUtil.getAddress(params), function_pointer);
/* 156:156 */    return params.get(0);
/* 157:    */  }
/* 158:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBOcclusionQuery
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */