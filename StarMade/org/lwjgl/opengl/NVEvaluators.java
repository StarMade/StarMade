/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.FloatBuffer;
/*   4:    */import java.nio.IntBuffer;
/*   5:    */import org.lwjgl.BufferChecks;
/*   6:    */import org.lwjgl.MemoryUtil;
/*   7:    */
/*   9:    */public final class NVEvaluators
/*  10:    */{
/*  11:    */  public static final int GL_EVAL_2D_NV = 34496;
/*  12:    */  public static final int GL_EVAL_TRIANGULAR_2D_NV = 34497;
/*  13:    */  public static final int GL_MAP_TESSELLATION_NV = 34498;
/*  14:    */  public static final int GL_MAP_ATTRIB_U_ORDER_NV = 34499;
/*  15:    */  public static final int GL_MAP_ATTRIB_V_ORDER_NV = 34500;
/*  16:    */  public static final int GL_EVAL_FRACTIONAL_TESSELLATION_NV = 34501;
/*  17:    */  public static final int GL_EVAL_VERTEX_ATTRIB0_NV = 34502;
/*  18:    */  public static final int GL_EVAL_VERTEX_ATTRIB1_NV = 34503;
/*  19:    */  public static final int GL_EVAL_VERTEX_ATTRIB2_NV = 34504;
/*  20:    */  public static final int GL_EVAL_VERTEX_ATTRIB3_NV = 34505;
/*  21:    */  public static final int GL_EVAL_VERTEX_ATTRIB4_NV = 34506;
/*  22:    */  public static final int GL_EVAL_VERTEX_ATTRIB5_NV = 34507;
/*  23:    */  public static final int GL_EVAL_VERTEX_ATTRIB6_NV = 34508;
/*  24:    */  public static final int GL_EVAL_VERTEX_ATTRIB7_NV = 34509;
/*  25:    */  public static final int GL_EVAL_VERTEX_ATTRIB8_NV = 34510;
/*  26:    */  public static final int GL_EVAL_VERTEX_ATTRIB9_NV = 34511;
/*  27:    */  public static final int GL_EVAL_VERTEX_ATTRIB10_NV = 34512;
/*  28:    */  public static final int GL_EVAL_VERTEX_ATTRIB11_NV = 34513;
/*  29:    */  public static final int GL_EVAL_VERTEX_ATTRIB12_NV = 34514;
/*  30:    */  public static final int GL_EVAL_VERTEX_ATTRIB13_NV = 34515;
/*  31:    */  public static final int GL_EVAL_VERTEX_ATTRIB14_NV = 34516;
/*  32:    */  public static final int GL_EVAL_VERTEX_ATTRIB15_NV = 34517;
/*  33:    */  public static final int GL_MAX_MAP_TESSELLATION_NV = 34518;
/*  34:    */  public static final int GL_MAX_RATIONAL_EVAL_ORDER_NV = 34519;
/*  35:    */  
/*  36:    */  public static void glGetMapControlPointsNV(int target, int index, int type, int ustride, int vstride, boolean packed, FloatBuffer pPoints)
/*  37:    */  {
/*  38: 38 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  39: 39 */    long function_pointer = caps.glGetMapControlPointsNV;
/*  40: 40 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  41: 41 */    BufferChecks.checkDirect(pPoints);
/*  42: 42 */    nglGetMapControlPointsNV(target, index, type, ustride, vstride, packed, MemoryUtil.getAddress(pPoints), function_pointer);
/*  43:    */  }
/*  44:    */  
/*  45:    */  static native void nglGetMapControlPointsNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, boolean paramBoolean, long paramLong1, long paramLong2);
/*  46:    */  
/*  47: 47 */  public static void glMapControlPointsNV(int target, int index, int type, int ustride, int vstride, int uorder, int vorder, boolean packed, FloatBuffer pPoints) { ContextCapabilities caps = GLContext.getCapabilities();
/*  48: 48 */    long function_pointer = caps.glMapControlPointsNV;
/*  49: 49 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  50: 50 */    BufferChecks.checkDirect(pPoints);
/*  51: 51 */    nglMapControlPointsNV(target, index, type, ustride, vstride, uorder, vorder, packed, MemoryUtil.getAddress(pPoints), function_pointer);
/*  52:    */  }
/*  53:    */  
/*  54:    */  static native void nglMapControlPointsNV(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, boolean paramBoolean, long paramLong1, long paramLong2);
/*  55:    */  
/*  56: 56 */  public static void glMapParameterNV(int target, int pname, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  57: 57 */    long function_pointer = caps.glMapParameterfvNV;
/*  58: 58 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  59: 59 */    BufferChecks.checkBuffer(params, 4);
/*  60: 60 */    nglMapParameterfvNV(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*  61:    */  }
/*  62:    */  
/*  63:    */  static native void nglMapParameterfvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  64:    */  
/*  65: 65 */  public static void glMapParameterNV(int target, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  66: 66 */    long function_pointer = caps.glMapParameterivNV;
/*  67: 67 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  68: 68 */    BufferChecks.checkBuffer(params, 4);
/*  69: 69 */    nglMapParameterivNV(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*  70:    */  }
/*  71:    */  
/*  72:    */  static native void nglMapParameterivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  73:    */  
/*  74: 74 */  public static void glGetMapParameterNV(int target, int pname, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  75: 75 */    long function_pointer = caps.glGetMapParameterfvNV;
/*  76: 76 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  77: 77 */    BufferChecks.checkBuffer(params, 4);
/*  78: 78 */    nglGetMapParameterfvNV(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*  79:    */  }
/*  80:    */  
/*  81:    */  static native void nglGetMapParameterfvNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  82:    */  
/*  83: 83 */  public static void glGetMapParameterNV(int target, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  84: 84 */    long function_pointer = caps.glGetMapParameterivNV;
/*  85: 85 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  86: 86 */    BufferChecks.checkBuffer(params, 4);
/*  87: 87 */    nglGetMapParameterivNV(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*  88:    */  }
/*  89:    */  
/*  90:    */  static native void nglGetMapParameterivNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*  91:    */  
/*  92: 92 */  public static void glGetMapAttribParameterNV(int target, int index, int pname, FloatBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/*  93: 93 */    long function_pointer = caps.glGetMapAttribParameterfvNV;
/*  94: 94 */    BufferChecks.checkFunctionAddress(function_pointer);
/*  95: 95 */    BufferChecks.checkBuffer(params, 4);
/*  96: 96 */    nglGetMapAttribParameterfvNV(target, index, pname, MemoryUtil.getAddress(params), function_pointer);
/*  97:    */  }
/*  98:    */  
/*  99:    */  static native void nglGetMapAttribParameterfvNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 100:    */  
/* 101:101 */  public static void glGetMapAttribParameterNV(int target, int index, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 102:102 */    long function_pointer = caps.glGetMapAttribParameterivNV;
/* 103:103 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 104:104 */    BufferChecks.checkBuffer(params, 4);
/* 105:105 */    nglGetMapAttribParameterivNV(target, index, pname, MemoryUtil.getAddress(params), function_pointer);
/* 106:    */  }
/* 107:    */  
/* 108:    */  static native void nglGetMapAttribParameterivNV(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 109:    */  
/* 110:110 */  public static void glEvalMapsNV(int target, int mode) { ContextCapabilities caps = GLContext.getCapabilities();
/* 111:111 */    long function_pointer = caps.glEvalMapsNV;
/* 112:112 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 113:113 */    nglEvalMapsNV(target, mode, function_pointer);
/* 114:    */  }
/* 115:    */  
/* 116:    */  static native void nglEvalMapsNV(int paramInt1, int paramInt2, long paramLong);
/* 117:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVEvaluators
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */