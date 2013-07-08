/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.DoubleBuffer;
/*   5:    */import java.nio.FloatBuffer;
/*   6:    */import java.nio.IntBuffer;
/*   7:    */import java.nio.ShortBuffer;
/*   8:    */
/*  39:    */public final class ARBVertexProgram
/*  40:    */  extends ARBProgram
/*  41:    */{
/*  42:    */  public static final int GL_VERTEX_PROGRAM_ARB = 34336;
/*  43:    */  public static final int GL_VERTEX_PROGRAM_POINT_SIZE_ARB = 34370;
/*  44:    */  public static final int GL_VERTEX_PROGRAM_TWO_SIDE_ARB = 34371;
/*  45:    */  public static final int GL_COLOR_SUM_ARB = 33880;
/*  46:    */  public static final int GL_VERTEX_ATTRIB_ARRAY_ENABLED_ARB = 34338;
/*  47:    */  public static final int GL_VERTEX_ATTRIB_ARRAY_SIZE_ARB = 34339;
/*  48:    */  public static final int GL_VERTEX_ATTRIB_ARRAY_STRIDE_ARB = 34340;
/*  49:    */  public static final int GL_VERTEX_ATTRIB_ARRAY_TYPE_ARB = 34341;
/*  50:    */  public static final int GL_VERTEX_ATTRIB_ARRAY_NORMALIZED_ARB = 34922;
/*  51:    */  public static final int GL_CURRENT_VERTEX_ATTRIB_ARB = 34342;
/*  52:    */  public static final int GL_VERTEX_ATTRIB_ARRAY_POINTER_ARB = 34373;
/*  53:    */  public static final int GL_PROGRAM_ADDRESS_REGISTERS_ARB = 34992;
/*  54:    */  public static final int GL_MAX_PROGRAM_ADDRESS_REGISTERS_ARB = 34993;
/*  55:    */  public static final int GL_PROGRAM_NATIVE_ADDRESS_REGISTERS_ARB = 34994;
/*  56:    */  public static final int GL_MAX_PROGRAM_NATIVE_ADDRESS_REGISTERS_ARB = 34995;
/*  57:    */  public static final int GL_MAX_VERTEX_ATTRIBS_ARB = 34921;
/*  58:    */  
/*  59:    */  public static void glVertexAttrib1sARB(int index, short x)
/*  60:    */  {
/*  61: 61 */    ARBVertexShader.glVertexAttrib1sARB(index, x);
/*  62:    */  }
/*  63:    */  
/*  64:    */  public static void glVertexAttrib1fARB(int index, float x) {
/*  65: 65 */    ARBVertexShader.glVertexAttrib1fARB(index, x);
/*  66:    */  }
/*  67:    */  
/*  68:    */  public static void glVertexAttrib1dARB(int index, double x) {
/*  69: 69 */    ARBVertexShader.glVertexAttrib1dARB(index, x);
/*  70:    */  }
/*  71:    */  
/*  72:    */  public static void glVertexAttrib2sARB(int index, short x, short y) {
/*  73: 73 */    ARBVertexShader.glVertexAttrib2sARB(index, x, y);
/*  74:    */  }
/*  75:    */  
/*  76:    */  public static void glVertexAttrib2fARB(int index, float x, float y) {
/*  77: 77 */    ARBVertexShader.glVertexAttrib2fARB(index, x, y);
/*  78:    */  }
/*  79:    */  
/*  80:    */  public static void glVertexAttrib2dARB(int index, double x, double y) {
/*  81: 81 */    ARBVertexShader.glVertexAttrib2dARB(index, x, y);
/*  82:    */  }
/*  83:    */  
/*  84:    */  public static void glVertexAttrib3sARB(int index, short x, short y, short z) {
/*  85: 85 */    ARBVertexShader.glVertexAttrib3sARB(index, x, y, z);
/*  86:    */  }
/*  87:    */  
/*  88:    */  public static void glVertexAttrib3fARB(int index, float x, float y, float z) {
/*  89: 89 */    ARBVertexShader.glVertexAttrib3fARB(index, x, y, z);
/*  90:    */  }
/*  91:    */  
/*  92:    */  public static void glVertexAttrib3dARB(int index, double x, double y, double z) {
/*  93: 93 */    ARBVertexShader.glVertexAttrib3dARB(index, x, y, z);
/*  94:    */  }
/*  95:    */  
/*  96:    */  public static void glVertexAttrib4sARB(int index, short x, short y, short z, short w) {
/*  97: 97 */    ARBVertexShader.glVertexAttrib4sARB(index, x, y, z, w);
/*  98:    */  }
/*  99:    */  
/* 100:    */  public static void glVertexAttrib4fARB(int index, float x, float y, float z, float w) {
/* 101:101 */    ARBVertexShader.glVertexAttrib4fARB(index, x, y, z, w);
/* 102:    */  }
/* 103:    */  
/* 104:    */  public static void glVertexAttrib4dARB(int index, double x, double y, double z, double w) {
/* 105:105 */    ARBVertexShader.glVertexAttrib4dARB(index, x, y, z, w);
/* 106:    */  }
/* 107:    */  
/* 108:    */  public static void glVertexAttrib4NubARB(int index, byte x, byte y, byte z, byte w) {
/* 109:109 */    ARBVertexShader.glVertexAttrib4NubARB(index, x, y, z, w);
/* 110:    */  }
/* 111:    */  
/* 112:    */  public static void glVertexAttribPointerARB(int index, int size, boolean normalized, int stride, DoubleBuffer buffer) {
/* 113:113 */    ARBVertexShader.glVertexAttribPointerARB(index, size, normalized, stride, buffer);
/* 114:    */  }
/* 115:    */  
/* 116:116 */  public static void glVertexAttribPointerARB(int index, int size, boolean normalized, int stride, FloatBuffer buffer) { ARBVertexShader.glVertexAttribPointerARB(index, size, normalized, stride, buffer); }
/* 117:    */  
/* 118:    */  public static void glVertexAttribPointerARB(int index, int size, boolean unsigned, boolean normalized, int stride, ByteBuffer buffer) {
/* 119:119 */    ARBVertexShader.glVertexAttribPointerARB(index, size, unsigned, normalized, stride, buffer);
/* 120:    */  }
/* 121:    */  
/* 122:122 */  public static void glVertexAttribPointerARB(int index, int size, boolean unsigned, boolean normalized, int stride, IntBuffer buffer) { ARBVertexShader.glVertexAttribPointerARB(index, size, unsigned, normalized, stride, buffer); }
/* 123:    */  
/* 124:    */  public static void glVertexAttribPointerARB(int index, int size, boolean unsigned, boolean normalized, int stride, ShortBuffer buffer) {
/* 125:125 */    ARBVertexShader.glVertexAttribPointerARB(index, size, unsigned, normalized, stride, buffer);
/* 126:    */  }
/* 127:    */  
/* 128:128 */  public static void glVertexAttribPointerARB(int index, int size, int type, boolean normalized, int stride, long buffer_buffer_offset) { ARBVertexShader.glVertexAttribPointerARB(index, size, type, normalized, stride, buffer_buffer_offset); }
/* 129:    */  
/* 130:    */  public static void glEnableVertexAttribArrayARB(int index)
/* 131:    */  {
/* 132:132 */    ARBVertexShader.glEnableVertexAttribArrayARB(index);
/* 133:    */  }
/* 134:    */  
/* 135:    */  public static void glDisableVertexAttribArrayARB(int index) {
/* 136:136 */    ARBVertexShader.glDisableVertexAttribArrayARB(index);
/* 137:    */  }
/* 138:    */  
/* 139:    */  public static void glGetVertexAttribARB(int index, int pname, FloatBuffer params) {
/* 140:140 */    ARBVertexShader.glGetVertexAttribARB(index, pname, params);
/* 141:    */  }
/* 142:    */  
/* 143:    */  public static void glGetVertexAttribARB(int index, int pname, DoubleBuffer params) {
/* 144:144 */    ARBVertexShader.glGetVertexAttribARB(index, pname, params);
/* 145:    */  }
/* 146:    */  
/* 147:    */  public static void glGetVertexAttribARB(int index, int pname, IntBuffer params) {
/* 148:148 */    ARBVertexShader.glGetVertexAttribARB(index, pname, params);
/* 149:    */  }
/* 150:    */  
/* 151:    */  public static ByteBuffer glGetVertexAttribPointerARB(int index, int pname, long result_size) {
/* 152:152 */    return ARBVertexShader.glGetVertexAttribPointerARB(index, pname, result_size);
/* 153:    */  }
/* 154:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBVertexProgram
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */