/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.LongBuffer;
/*   4:    */import org.lwjgl.BufferChecks;
/*   5:    */import org.lwjgl.MemoryUtil;
/*   6:    */
/*  31:    */public final class NVVertexBufferUnifiedMemory
/*  32:    */{
/*  33:    */  public static final int GL_VERTEX_ATTRIB_ARRAY_UNIFIED_NV = 36638;
/*  34:    */  public static final int GL_ELEMENT_ARRAY_UNIFIED_NV = 36639;
/*  35:    */  public static final int GL_VERTEX_ATTRIB_ARRAY_ADDRESS_NV = 36640;
/*  36:    */  public static final int GL_TEXTURE_COORD_ARRAY_ADDRESS_NV = 36645;
/*  37:    */  public static final int GL_VERTEX_ARRAY_ADDRESS_NV = 36641;
/*  38:    */  public static final int GL_NORMAL_ARRAY_ADDRESS_NV = 36642;
/*  39:    */  public static final int GL_COLOR_ARRAY_ADDRESS_NV = 36643;
/*  40:    */  public static final int GL_INDEX_ARRAY_ADDRESS_NV = 36644;
/*  41:    */  public static final int GL_EDGE_FLAG_ARRAY_ADDRESS_NV = 36646;
/*  42:    */  public static final int GL_SECONDARY_COLOR_ARRAY_ADDRESS_NV = 36647;
/*  43:    */  public static final int GL_FOG_COORD_ARRAY_ADDRESS_NV = 36648;
/*  44:    */  public static final int GL_ELEMENT_ARRAY_ADDRESS_NV = 36649;
/*  45:    */  public static final int GL_VERTEX_ATTRIB_ARRAY_LENGTH_NV = 36650;
/*  46:    */  public static final int GL_TEXTURE_COORD_ARRAY_LENGTH_NV = 36655;
/*  47:    */  public static final int GL_VERTEX_ARRAY_LENGTH_NV = 36651;
/*  48:    */  public static final int GL_NORMAL_ARRAY_LENGTH_NV = 36652;
/*  49:    */  public static final int GL_COLOR_ARRAY_LENGTH_NV = 36653;
/*  50:    */  public static final int GL_INDEX_ARRAY_LENGTH_NV = 36654;
/*  51:    */  public static final int GL_EDGE_FLAG_ARRAY_LENGTH_NV = 36656;
/*  52:    */  public static final int GL_SECONDARY_COLOR_ARRAY_LENGTH_NV = 36657;
/*  53:    */  public static final int GL_FOG_COORD_ARRAY_LENGTH_NV = 36658;
/*  54:    */  public static final int GL_ELEMENT_ARRAY_LENGTH_NV = 36659;
/*  55:    */  
/*  56:    */  public static void glBufferAddressRangeNV(int pname, int index, long address, long length)
/*  57:    */  {
/*  58: 58 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  59: 59 */    long function_pointer = caps.glBufferAddressRangeNV;
/*  60: 60 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  61: 61 */    nglBufferAddressRangeNV(pname, index, address, length, function_pointer);
/*  62:    */  }
/*  63:    */  
/*  64:    */  static native void nglBufferAddressRangeNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3);
/*  65:    */  
/*  66: 66 */  public static void glVertexFormatNV(int size, int type, int stride) { ContextCapabilities caps = GLContext.getCapabilities();
/*  67: 67 */    long function_pointer = caps.glVertexFormatNV;
/*  68: 68 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  69: 69 */    nglVertexFormatNV(size, type, stride, function_pointer);
/*  70:    */  }
/*  71:    */  
/*  72:    */  static native void nglVertexFormatNV(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*  73:    */  
/*  74: 74 */  public static void glNormalFormatNV(int type, int stride) { ContextCapabilities caps = GLContext.getCapabilities();
/*  75: 75 */    long function_pointer = caps.glNormalFormatNV;
/*  76: 76 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  77: 77 */    nglNormalFormatNV(type, stride, function_pointer);
/*  78:    */  }
/*  79:    */  
/*  80:    */  static native void nglNormalFormatNV(int paramInt1, int paramInt2, long paramLong);
/*  81:    */  
/*  82: 82 */  public static void glColorFormatNV(int size, int type, int stride) { ContextCapabilities caps = GLContext.getCapabilities();
/*  83: 83 */    long function_pointer = caps.glColorFormatNV;
/*  84: 84 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  85: 85 */    nglColorFormatNV(size, type, stride, function_pointer);
/*  86:    */  }
/*  87:    */  
/*  88:    */  static native void nglColorFormatNV(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*  89:    */  
/*  90: 90 */  public static void glIndexFormatNV(int type, int stride) { ContextCapabilities caps = GLContext.getCapabilities();
/*  91: 91 */    long function_pointer = caps.glIndexFormatNV;
/*  92: 92 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  93: 93 */    nglIndexFormatNV(type, stride, function_pointer);
/*  94:    */  }
/*  95:    */  
/*  96:    */  static native void nglIndexFormatNV(int paramInt1, int paramInt2, long paramLong);
/*  97:    */  
/*  98: 98 */  public static void glTexCoordFormatNV(int size, int type, int stride) { ContextCapabilities caps = GLContext.getCapabilities();
/*  99: 99 */    long function_pointer = caps.glTexCoordFormatNV;
/* 100:100 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 101:101 */    nglTexCoordFormatNV(size, type, stride, function_pointer);
/* 102:    */  }
/* 103:    */  
/* 104:    */  static native void nglTexCoordFormatNV(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 105:    */  
/* 106:106 */  public static void glEdgeFlagFormatNV(int stride) { ContextCapabilities caps = GLContext.getCapabilities();
/* 107:107 */    long function_pointer = caps.glEdgeFlagFormatNV;
/* 108:108 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 109:109 */    nglEdgeFlagFormatNV(stride, function_pointer);
/* 110:    */  }
/* 111:    */  
/* 112:    */  static native void nglEdgeFlagFormatNV(int paramInt, long paramLong);
/* 113:    */  
/* 114:114 */  public static void glSecondaryColorFormatNV(int size, int type, int stride) { ContextCapabilities caps = GLContext.getCapabilities();
/* 115:115 */    long function_pointer = caps.glSecondaryColorFormatNV;
/* 116:116 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 117:117 */    nglSecondaryColorFormatNV(size, type, stride, function_pointer);
/* 118:    */  }
/* 119:    */  
/* 120:    */  static native void nglSecondaryColorFormatNV(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/* 121:    */  
/* 122:122 */  public static void glFogCoordFormatNV(int type, int stride) { ContextCapabilities caps = GLContext.getCapabilities();
/* 123:123 */    long function_pointer = caps.glFogCoordFormatNV;
/* 124:124 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 125:125 */    nglFogCoordFormatNV(type, stride, function_pointer);
/* 126:    */  }
/* 127:    */  
/* 128:    */  static native void nglFogCoordFormatNV(int paramInt1, int paramInt2, long paramLong);
/* 129:    */  
/* 130:130 */  public static void glVertexAttribFormatNV(int index, int size, int type, boolean normalized, int stride) { ContextCapabilities caps = GLContext.getCapabilities();
/* 131:131 */    long function_pointer = caps.glVertexAttribFormatNV;
/* 132:132 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 133:133 */    nglVertexAttribFormatNV(index, size, type, normalized, stride, function_pointer);
/* 134:    */  }
/* 135:    */  
/* 136:    */  static native void nglVertexAttribFormatNV(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, int paramInt4, long paramLong);
/* 137:    */  
/* 138:138 */  public static void glVertexAttribIFormatNV(int index, int size, int type, int stride) { ContextCapabilities caps = GLContext.getCapabilities();
/* 139:139 */    long function_pointer = caps.glVertexAttribIFormatNV;
/* 140:140 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 141:141 */    nglVertexAttribIFormatNV(index, size, type, stride, function_pointer);
/* 142:    */  }
/* 143:    */  
/* 144:    */  static native void nglVertexAttribIFormatNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/* 145:    */  
/* 146:146 */  public static void glGetIntegeruNV(int value, int index, LongBuffer result) { ContextCapabilities caps = GLContext.getCapabilities();
/* 147:147 */    long function_pointer = caps.glGetIntegerui64i_vNV;
/* 148:148 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 149:149 */    BufferChecks.checkBuffer(result, 1);
/* 150:150 */    nglGetIntegerui64i_vNV(value, index, MemoryUtil.getAddress(result), function_pointer);
/* 151:    */  }
/* 152:    */  
/* 153:    */  static native void nglGetIntegerui64i_vNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 154:    */  
/* 155:    */  public static long glGetIntegerui64NV(int value, int index) {
/* 156:156 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 157:157 */    long function_pointer = caps.glGetIntegerui64i_vNV;
/* 158:158 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 159:159 */    LongBuffer result = APIUtil.getBufferLong(caps);
/* 160:160 */    nglGetIntegerui64i_vNV(value, index, MemoryUtil.getAddress(result), function_pointer);
/* 161:161 */    return result.get(0);
/* 162:    */  }
/* 163:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVVertexBufferUnifiedMemory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */