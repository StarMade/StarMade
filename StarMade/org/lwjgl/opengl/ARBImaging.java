/*      */ package org.lwjgl.opengl;
/*      */ 
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.DoubleBuffer;
/*      */ import java.nio.FloatBuffer;
/*      */ import java.nio.IntBuffer;
/*      */ import java.nio.ShortBuffer;
/*      */ import org.lwjgl.BufferChecks;
/*      */ import org.lwjgl.MemoryUtil;
/*      */ 
/*      */ public final class ARBImaging
/*      */ {
/*      */   public static final int GL_CONSTANT_COLOR = 32769;
/*      */   public static final int GL_ONE_MINUS_CONSTANT_COLOR = 32770;
/*      */   public static final int GL_CONSTANT_ALPHA = 32771;
/*      */   public static final int GL_ONE_MINUS_CONSTANT_ALPHA = 32772;
/*      */   public static final int GL_BLEND_COLOR = 32773;
/*      */   public static final int GL_FUNC_ADD = 32774;
/*      */   public static final int GL_MIN = 32775;
/*      */   public static final int GL_MAX = 32776;
/*      */   public static final int GL_BLEND_EQUATION = 32777;
/*      */   public static final int GL_FUNC_SUBTRACT = 32778;
/*      */   public static final int GL_FUNC_REVERSE_SUBTRACT = 32779;
/*      */   public static final int GL_COLOR_MATRIX = 32945;
/*      */   public static final int GL_COLOR_MATRIX_STACK_DEPTH = 32946;
/*      */   public static final int GL_MAX_COLOR_MATRIX_STACK_DEPTH = 32947;
/*      */   public static final int GL_POST_COLOR_MATRIX_RED_SCALE = 32948;
/*      */   public static final int GL_POST_COLOR_MATRIX_GREEN_SCALE = 32949;
/*      */   public static final int GL_POST_COLOR_MATRIX_BLUE_SCALE = 32950;
/*      */   public static final int GL_POST_COLOR_MATRIX_ALPHA_SCALE = 32951;
/*      */   public static final int GL_POST_COLOR_MATRIX_RED_BIAS = 32952;
/*      */   public static final int GL_POST_COLOR_MATRIX_GREEN_BIAS = 32953;
/*      */   public static final int GL_POST_COLOR_MATRIX_BLUE_BIAS = 32954;
/*      */   public static final int GL_POST_COLOR_MATRIX_ALPHA_BIAS = 32955;
/*      */   public static final int GL_COLOR_TABLE = 32976;
/*      */   public static final int GL_POST_CONVOLUTION_COLOR_TABLE = 32977;
/*      */   public static final int GL_POST_COLOR_MATRIX_COLOR_TABLE = 32978;
/*      */   public static final int GL_PROXY_COLOR_TABLE = 32979;
/*      */   public static final int GL_PROXY_POST_CONVOLUTION_COLOR_TABLE = 32980;
/*      */   public static final int GL_PROXY_POST_COLOR_MATRIX_COLOR_TABLE = 32981;
/*      */   public static final int GL_COLOR_TABLE_SCALE = 32982;
/*      */   public static final int GL_COLOR_TABLE_BIAS = 32983;
/*      */   public static final int GL_COLOR_TABLE_FORMAT = 32984;
/*      */   public static final int GL_COLOR_TABLE_WIDTH = 32985;
/*      */   public static final int GL_COLOR_TABLE_RED_SIZE = 32986;
/*      */   public static final int GL_COLOR_TABLE_GREEN_SIZE = 32987;
/*      */   public static final int GL_COLOR_TABLE_BLUE_SIZE = 32988;
/*      */   public static final int GL_COLOR_TABLE_ALPHA_SIZE = 32989;
/*      */   public static final int GL_COLOR_TABLE_LUMINANCE_SIZE = 32990;
/*      */   public static final int GL_COLOR_TABLE_INTENSITY_SIZE = 32991;
/*      */   public static final int GL_CONVOLUTION_1D = 32784;
/*      */   public static final int GL_CONVOLUTION_2D = 32785;
/*      */   public static final int GL_SEPARABLE_2D = 32786;
/*      */   public static final int GL_CONVOLUTION_BORDER_MODE = 32787;
/*      */   public static final int GL_CONVOLUTION_FILTER_SCALE = 32788;
/*      */   public static final int GL_CONVOLUTION_FILTER_BIAS = 32789;
/*      */   public static final int GL_REDUCE = 32790;
/*      */   public static final int GL_CONVOLUTION_FORMAT = 32791;
/*      */   public static final int GL_CONVOLUTION_WIDTH = 32792;
/*      */   public static final int GL_CONVOLUTION_HEIGHT = 32793;
/*      */   public static final int GL_MAX_CONVOLUTION_WIDTH = 32794;
/*      */   public static final int GL_MAX_CONVOLUTION_HEIGHT = 32795;
/*      */   public static final int GL_POST_CONVOLUTION_RED_SCALE = 32796;
/*      */   public static final int GL_POST_CONVOLUTION_GREEN_SCALE = 32797;
/*      */   public static final int GL_POST_CONVOLUTION_BLUE_SCALE = 32798;
/*      */   public static final int GL_POST_CONVOLUTION_ALPHA_SCALE = 32799;
/*      */   public static final int GL_POST_CONVOLUTION_RED_BIAS = 32800;
/*      */   public static final int GL_POST_CONVOLUTION_GREEN_BIAS = 32801;
/*      */   public static final int GL_POST_CONVOLUTION_BLUE_BIAS = 32802;
/*      */   public static final int GL_POST_CONVOLUTION_ALPHA_BIAS = 32803;
/*      */   public static final int GL_IGNORE_BORDER = 33104;
/*      */   public static final int GL_CONSTANT_BORDER = 33105;
/*      */   public static final int GL_REPLICATE_BORDER = 33107;
/*      */   public static final int GL_CONVOLUTION_BORDER_COLOR = 33108;
/*      */   public static final int GL_HISTOGRAM = 32804;
/*      */   public static final int GL_PROXY_HISTOGRAM = 32805;
/*      */   public static final int GL_HISTOGRAM_WIDTH = 32806;
/*      */   public static final int GL_HISTOGRAM_FORMAT = 32807;
/*      */   public static final int GL_HISTOGRAM_RED_SIZE = 32808;
/*      */   public static final int GL_HISTOGRAM_GREEN_SIZE = 32809;
/*      */   public static final int GL_HISTOGRAM_BLUE_SIZE = 32810;
/*      */   public static final int GL_HISTOGRAM_ALPHA_SIZE = 32811;
/*      */   public static final int GL_HISTOGRAM_LUMINANCE_SIZE = 32812;
/*      */   public static final int GL_HISTOGRAM_SINK = 32813;
/*      */   public static final int GL_MINMAX = 32814;
/*      */   public static final int GL_MINMAX_FORMAT = 32815;
/*      */   public static final int GL_MINMAX_SINK = 32816;
/*      */   public static final int GL_TABLE_TOO_LARGE = 32817;
/*      */ 
/*      */   public static void glColorTable(int target, int internalFormat, int width, int format, int type, ByteBuffer data)
/*      */   {
/*   98 */     ContextCapabilities caps = GLContext.getCapabilities();
/*   99 */     long function_pointer = caps.glColorTable;
/*  100 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  101 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  102 */     BufferChecks.checkBuffer(data, 256);
/*  103 */     nglColorTable(target, internalFormat, width, format, type, MemoryUtil.getAddress(data), function_pointer);
/*      */   }
/*      */   public static void glColorTable(int target, int internalFormat, int width, int format, int type, DoubleBuffer data) {
/*  106 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  107 */     long function_pointer = caps.glColorTable;
/*  108 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  109 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  110 */     BufferChecks.checkBuffer(data, 256);
/*  111 */     nglColorTable(target, internalFormat, width, format, type, MemoryUtil.getAddress(data), function_pointer);
/*      */   }
/*      */   public static void glColorTable(int target, int internalFormat, int width, int format, int type, FloatBuffer data) {
/*  114 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  115 */     long function_pointer = caps.glColorTable;
/*  116 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  117 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  118 */     BufferChecks.checkBuffer(data, 256);
/*  119 */     nglColorTable(target, internalFormat, width, format, type, MemoryUtil.getAddress(data), function_pointer);
/*      */   }
/*      */   static native void nglColorTable(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/*      */ 
/*  123 */   public static void glColorTable(int target, int internalFormat, int width, int format, int type, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  124 */     long function_pointer = caps.glColorTable;
/*  125 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  126 */     GLChecks.ensureUnpackPBOenabled(caps);
/*  127 */     nglColorTableBO(target, internalFormat, width, format, type, data_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglColorTableBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glColorSubTable(int target, int start, int count, int format, int type, ByteBuffer data) {
/*  132 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  133 */     long function_pointer = caps.glColorSubTable;
/*  134 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  135 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  136 */     BufferChecks.checkBuffer(data, 256);
/*  137 */     nglColorSubTable(target, start, count, format, type, MemoryUtil.getAddress(data), function_pointer);
/*      */   }
/*      */   public static void glColorSubTable(int target, int start, int count, int format, int type, DoubleBuffer data) {
/*  140 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  141 */     long function_pointer = caps.glColorSubTable;
/*  142 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  143 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  144 */     BufferChecks.checkBuffer(data, 256);
/*  145 */     nglColorSubTable(target, start, count, format, type, MemoryUtil.getAddress(data), function_pointer);
/*      */   }
/*      */   public static void glColorSubTable(int target, int start, int count, int format, int type, FloatBuffer data) {
/*  148 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  149 */     long function_pointer = caps.glColorSubTable;
/*  150 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  151 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  152 */     BufferChecks.checkBuffer(data, 256);
/*  153 */     nglColorSubTable(target, start, count, format, type, MemoryUtil.getAddress(data), function_pointer);
/*      */   }
/*      */   static native void nglColorSubTable(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/*      */ 
/*  157 */   public static void glColorSubTable(int target, int start, int count, int format, int type, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  158 */     long function_pointer = caps.glColorSubTable;
/*  159 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  160 */     GLChecks.ensureUnpackPBOenabled(caps);
/*  161 */     nglColorSubTableBO(target, start, count, format, type, data_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglColorSubTableBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glColorTableParameter(int target, int pname, IntBuffer params) {
/*  166 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  167 */     long function_pointer = caps.glColorTableParameteriv;
/*  168 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  169 */     BufferChecks.checkBuffer(params, 4);
/*  170 */     nglColorTableParameteriv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglColorTableParameteriv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glColorTableParameter(int target, int pname, FloatBuffer params) {
/*  175 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  176 */     long function_pointer = caps.glColorTableParameterfv;
/*  177 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  178 */     BufferChecks.checkBuffer(params, 4);
/*  179 */     nglColorTableParameterfv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglColorTableParameterfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glCopyColorSubTable(int target, int start, int x, int y, int width) {
/*  184 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  185 */     long function_pointer = caps.glCopyColorSubTable;
/*  186 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  187 */     nglCopyColorSubTable(target, start, x, y, width, function_pointer);
/*      */   }
/*      */   static native void nglCopyColorSubTable(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*      */ 
/*      */   public static void glCopyColorTable(int target, int internalformat, int x, int y, int width) {
/*  192 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  193 */     long function_pointer = caps.glCopyColorTable;
/*  194 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  195 */     nglCopyColorTable(target, internalformat, x, y, width, function_pointer);
/*      */   }
/*      */   static native void nglCopyColorTable(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*      */ 
/*      */   public static void glGetColorTable(int target, int format, int type, ByteBuffer data) {
/*  200 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  201 */     long function_pointer = caps.glGetColorTable;
/*  202 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  203 */     BufferChecks.checkBuffer(data, 256);
/*  204 */     nglGetColorTable(target, format, type, MemoryUtil.getAddress(data), function_pointer);
/*      */   }
/*      */   public static void glGetColorTable(int target, int format, int type, DoubleBuffer data) {
/*  207 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  208 */     long function_pointer = caps.glGetColorTable;
/*  209 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  210 */     BufferChecks.checkBuffer(data, 256);
/*  211 */     nglGetColorTable(target, format, type, MemoryUtil.getAddress(data), function_pointer);
/*      */   }
/*      */   public static void glGetColorTable(int target, int format, int type, FloatBuffer data) {
/*  214 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  215 */     long function_pointer = caps.glGetColorTable;
/*  216 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  217 */     BufferChecks.checkBuffer(data, 256);
/*  218 */     nglGetColorTable(target, format, type, MemoryUtil.getAddress(data), function_pointer);
/*      */   }
/*      */   static native void nglGetColorTable(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetColorTableParameter(int target, int pname, IntBuffer params) {
/*  223 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  224 */     long function_pointer = caps.glGetColorTableParameteriv;
/*  225 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  226 */     BufferChecks.checkBuffer(params, 4);
/*  227 */     nglGetColorTableParameteriv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglGetColorTableParameteriv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetColorTableParameter(int target, int pname, FloatBuffer params) {
/*  232 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  233 */     long function_pointer = caps.glGetColorTableParameterfv;
/*  234 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  235 */     BufferChecks.checkBuffer(params, 4);
/*  236 */     nglGetColorTableParameterfv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglGetColorTableParameterfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glBlendEquation(int mode) {
/*  241 */     GL14.glBlendEquation(mode);
/*      */   }
/*      */ 
/*      */   public static void glBlendColor(float red, float green, float blue, float alpha) {
/*  245 */     GL14.glBlendColor(red, green, blue, alpha);
/*      */   }
/*      */ 
/*      */   public static void glHistogram(int target, int width, int internalformat, boolean sink) {
/*  249 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  250 */     long function_pointer = caps.glHistogram;
/*  251 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  252 */     nglHistogram(target, width, internalformat, sink, function_pointer);
/*      */   }
/*      */   static native void nglHistogram(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong);
/*      */ 
/*      */   public static void glResetHistogram(int target) {
/*  257 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  258 */     long function_pointer = caps.glResetHistogram;
/*  259 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  260 */     nglResetHistogram(target, function_pointer);
/*      */   }
/*      */   static native void nglResetHistogram(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glGetHistogram(int target, boolean reset, int format, int type, ByteBuffer values) {
/*  265 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  266 */     long function_pointer = caps.glGetHistogram;
/*  267 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  268 */     GLChecks.ensurePackPBOdisabled(caps);
/*  269 */     BufferChecks.checkBuffer(values, 256);
/*  270 */     nglGetHistogram(target, reset, format, type, MemoryUtil.getAddress(values), function_pointer);
/*      */   }
/*      */   public static void glGetHistogram(int target, boolean reset, int format, int type, DoubleBuffer values) {
/*  273 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  274 */     long function_pointer = caps.glGetHistogram;
/*  275 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  276 */     GLChecks.ensurePackPBOdisabled(caps);
/*  277 */     BufferChecks.checkBuffer(values, 256);
/*  278 */     nglGetHistogram(target, reset, format, type, MemoryUtil.getAddress(values), function_pointer);
/*      */   }
/*      */   public static void glGetHistogram(int target, boolean reset, int format, int type, FloatBuffer values) {
/*  281 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  282 */     long function_pointer = caps.glGetHistogram;
/*  283 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  284 */     GLChecks.ensurePackPBOdisabled(caps);
/*  285 */     BufferChecks.checkBuffer(values, 256);
/*  286 */     nglGetHistogram(target, reset, format, type, MemoryUtil.getAddress(values), function_pointer);
/*      */   }
/*      */   public static void glGetHistogram(int target, boolean reset, int format, int type, IntBuffer values) {
/*  289 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  290 */     long function_pointer = caps.glGetHistogram;
/*  291 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  292 */     GLChecks.ensurePackPBOdisabled(caps);
/*  293 */     BufferChecks.checkBuffer(values, 256);
/*  294 */     nglGetHistogram(target, reset, format, type, MemoryUtil.getAddress(values), function_pointer);
/*      */   }
/*      */   public static void glGetHistogram(int target, boolean reset, int format, int type, ShortBuffer values) {
/*  297 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  298 */     long function_pointer = caps.glGetHistogram;
/*  299 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  300 */     GLChecks.ensurePackPBOdisabled(caps);
/*  301 */     BufferChecks.checkBuffer(values, 256);
/*  302 */     nglGetHistogram(target, reset, format, type, MemoryUtil.getAddress(values), function_pointer);
/*      */   }
/*      */   static native void nglGetHistogram(int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*  306 */   public static void glGetHistogram(int target, boolean reset, int format, int type, long values_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  307 */     long function_pointer = caps.glGetHistogram;
/*  308 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  309 */     GLChecks.ensurePackPBOenabled(caps);
/*  310 */     nglGetHistogramBO(target, reset, format, type, values_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglGetHistogramBO(int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetHistogramParameter(int target, int pname, FloatBuffer params) {
/*  315 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  316 */     long function_pointer = caps.glGetHistogramParameterfv;
/*  317 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  318 */     BufferChecks.checkBuffer(params, 256);
/*  319 */     nglGetHistogramParameterfv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglGetHistogramParameterfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetHistogramParameter(int target, int pname, IntBuffer params) {
/*  324 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  325 */     long function_pointer = caps.glGetHistogramParameteriv;
/*  326 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  327 */     BufferChecks.checkBuffer(params, 256);
/*  328 */     nglGetHistogramParameteriv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglGetHistogramParameteriv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glMinmax(int target, int internalformat, boolean sink) {
/*  333 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  334 */     long function_pointer = caps.glMinmax;
/*  335 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  336 */     nglMinmax(target, internalformat, sink, function_pointer);
/*      */   }
/*      */   static native void nglMinmax(int paramInt1, int paramInt2, boolean paramBoolean, long paramLong);
/*      */ 
/*      */   public static void glResetMinmax(int target) {
/*  341 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  342 */     long function_pointer = caps.glResetMinmax;
/*  343 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  344 */     nglResetMinmax(target, function_pointer);
/*      */   }
/*      */   static native void nglResetMinmax(int paramInt, long paramLong);
/*      */ 
/*      */   public static void glGetMinmax(int target, boolean reset, int format, int types, ByteBuffer values) {
/*  349 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  350 */     long function_pointer = caps.glGetMinmax;
/*  351 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  352 */     GLChecks.ensurePackPBOdisabled(caps);
/*  353 */     BufferChecks.checkBuffer(values, 4);
/*  354 */     nglGetMinmax(target, reset, format, types, MemoryUtil.getAddress(values), function_pointer);
/*      */   }
/*      */   public static void glGetMinmax(int target, boolean reset, int format, int types, DoubleBuffer values) {
/*  357 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  358 */     long function_pointer = caps.glGetMinmax;
/*  359 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  360 */     GLChecks.ensurePackPBOdisabled(caps);
/*  361 */     BufferChecks.checkBuffer(values, 4);
/*  362 */     nglGetMinmax(target, reset, format, types, MemoryUtil.getAddress(values), function_pointer);
/*      */   }
/*      */   public static void glGetMinmax(int target, boolean reset, int format, int types, FloatBuffer values) {
/*  365 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  366 */     long function_pointer = caps.glGetMinmax;
/*  367 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  368 */     GLChecks.ensurePackPBOdisabled(caps);
/*  369 */     BufferChecks.checkBuffer(values, 4);
/*  370 */     nglGetMinmax(target, reset, format, types, MemoryUtil.getAddress(values), function_pointer);
/*      */   }
/*      */   public static void glGetMinmax(int target, boolean reset, int format, int types, IntBuffer values) {
/*  373 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  374 */     long function_pointer = caps.glGetMinmax;
/*  375 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  376 */     GLChecks.ensurePackPBOdisabled(caps);
/*  377 */     BufferChecks.checkBuffer(values, 4);
/*  378 */     nglGetMinmax(target, reset, format, types, MemoryUtil.getAddress(values), function_pointer);
/*      */   }
/*      */   public static void glGetMinmax(int target, boolean reset, int format, int types, ShortBuffer values) {
/*  381 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  382 */     long function_pointer = caps.glGetMinmax;
/*  383 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  384 */     GLChecks.ensurePackPBOdisabled(caps);
/*  385 */     BufferChecks.checkBuffer(values, 4);
/*  386 */     nglGetMinmax(target, reset, format, types, MemoryUtil.getAddress(values), function_pointer);
/*      */   }
/*      */   static native void nglGetMinmax(int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*  390 */   public static void glGetMinmax(int target, boolean reset, int format, int types, long values_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  391 */     long function_pointer = caps.glGetMinmax;
/*  392 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  393 */     GLChecks.ensurePackPBOenabled(caps);
/*  394 */     nglGetMinmaxBO(target, reset, format, types, values_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglGetMinmaxBO(int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetMinmaxParameter(int target, int pname, FloatBuffer params) {
/*  399 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  400 */     long function_pointer = caps.glGetMinmaxParameterfv;
/*  401 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  402 */     BufferChecks.checkBuffer(params, 4);
/*  403 */     nglGetMinmaxParameterfv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglGetMinmaxParameterfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetMinmaxParameter(int target, int pname, IntBuffer params) {
/*  408 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  409 */     long function_pointer = caps.glGetMinmaxParameteriv;
/*  410 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  411 */     BufferChecks.checkBuffer(params, 4);
/*  412 */     nglGetMinmaxParameteriv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglGetMinmaxParameteriv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glConvolutionFilter1D(int target, int internalformat, int width, int format, int type, ByteBuffer image) {
/*  417 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  418 */     long function_pointer = caps.glConvolutionFilter1D;
/*  419 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  420 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  421 */     BufferChecks.checkBuffer(image, GLChecks.calculateImageStorage(image, format, type, width, 1, 1));
/*  422 */     nglConvolutionFilter1D(target, internalformat, width, format, type, MemoryUtil.getAddress(image), function_pointer);
/*      */   }
/*      */   public static void glConvolutionFilter1D(int target, int internalformat, int width, int format, int type, DoubleBuffer image) {
/*  425 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  426 */     long function_pointer = caps.glConvolutionFilter1D;
/*  427 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  428 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  429 */     BufferChecks.checkBuffer(image, GLChecks.calculateImageStorage(image, format, type, width, 1, 1));
/*  430 */     nglConvolutionFilter1D(target, internalformat, width, format, type, MemoryUtil.getAddress(image), function_pointer);
/*      */   }
/*      */   public static void glConvolutionFilter1D(int target, int internalformat, int width, int format, int type, FloatBuffer image) {
/*  433 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  434 */     long function_pointer = caps.glConvolutionFilter1D;
/*  435 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  436 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  437 */     BufferChecks.checkBuffer(image, GLChecks.calculateImageStorage(image, format, type, width, 1, 1));
/*  438 */     nglConvolutionFilter1D(target, internalformat, width, format, type, MemoryUtil.getAddress(image), function_pointer);
/*      */   }
/*      */   public static void glConvolutionFilter1D(int target, int internalformat, int width, int format, int type, IntBuffer image) {
/*  441 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  442 */     long function_pointer = caps.glConvolutionFilter1D;
/*  443 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  444 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  445 */     BufferChecks.checkBuffer(image, GLChecks.calculateImageStorage(image, format, type, width, 1, 1));
/*  446 */     nglConvolutionFilter1D(target, internalformat, width, format, type, MemoryUtil.getAddress(image), function_pointer);
/*      */   }
/*      */   public static void glConvolutionFilter1D(int target, int internalformat, int width, int format, int type, ShortBuffer image) {
/*  449 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  450 */     long function_pointer = caps.glConvolutionFilter1D;
/*  451 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  452 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  453 */     BufferChecks.checkBuffer(image, GLChecks.calculateImageStorage(image, format, type, width, 1, 1));
/*  454 */     nglConvolutionFilter1D(target, internalformat, width, format, type, MemoryUtil.getAddress(image), function_pointer);
/*      */   }
/*      */   static native void nglConvolutionFilter1D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/*      */ 
/*  458 */   public static void glConvolutionFilter1D(int target, int internalformat, int width, int format, int type, long image_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  459 */     long function_pointer = caps.glConvolutionFilter1D;
/*  460 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  461 */     GLChecks.ensureUnpackPBOenabled(caps);
/*  462 */     nglConvolutionFilter1DBO(target, internalformat, width, format, type, image_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglConvolutionFilter1DBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glConvolutionFilter2D(int target, int internalformat, int width, int height, int format, int type, ByteBuffer image) {
/*  467 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  468 */     long function_pointer = caps.glConvolutionFilter2D;
/*  469 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  470 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  471 */     BufferChecks.checkBuffer(image, GLChecks.calculateImageStorage(image, format, type, width, height, 1));
/*  472 */     nglConvolutionFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(image), function_pointer);
/*      */   }
/*      */   public static void glConvolutionFilter2D(int target, int internalformat, int width, int height, int format, int type, IntBuffer image) {
/*  475 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  476 */     long function_pointer = caps.glConvolutionFilter2D;
/*  477 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  478 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  479 */     BufferChecks.checkBuffer(image, GLChecks.calculateImageStorage(image, format, type, width, height, 1));
/*  480 */     nglConvolutionFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(image), function_pointer);
/*      */   }
/*      */   public static void glConvolutionFilter2D(int target, int internalformat, int width, int height, int format, int type, ShortBuffer image) {
/*  483 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  484 */     long function_pointer = caps.glConvolutionFilter2D;
/*  485 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  486 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  487 */     BufferChecks.checkBuffer(image, GLChecks.calculateImageStorage(image, format, type, width, height, 1));
/*  488 */     nglConvolutionFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(image), function_pointer);
/*      */   }
/*      */   static native void nglConvolutionFilter2D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong1, long paramLong2);
/*      */ 
/*  492 */   public static void glConvolutionFilter2D(int target, int internalformat, int width, int height, int format, int type, long image_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  493 */     long function_pointer = caps.glConvolutionFilter2D;
/*  494 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  495 */     GLChecks.ensureUnpackPBOenabled(caps);
/*  496 */     nglConvolutionFilter2DBO(target, internalformat, width, height, format, type, image_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglConvolutionFilter2DBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glConvolutionParameterf(int target, int pname, float params) {
/*  501 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  502 */     long function_pointer = caps.glConvolutionParameterf;
/*  503 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  504 */     nglConvolutionParameterf(target, pname, params, function_pointer);
/*      */   }
/*      */   static native void nglConvolutionParameterf(int paramInt1, int paramInt2, float paramFloat, long paramLong);
/*      */ 
/*      */   public static void glConvolutionParameter(int target, int pname, FloatBuffer params) {
/*  509 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  510 */     long function_pointer = caps.glConvolutionParameterfv;
/*  511 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  512 */     BufferChecks.checkBuffer(params, 4);
/*  513 */     nglConvolutionParameterfv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglConvolutionParameterfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glConvolutionParameteri(int target, int pname, int params) {
/*  518 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  519 */     long function_pointer = caps.glConvolutionParameteri;
/*  520 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  521 */     nglConvolutionParameteri(target, pname, params, function_pointer);
/*      */   }
/*      */   static native void nglConvolutionParameteri(int paramInt1, int paramInt2, int paramInt3, long paramLong);
/*      */ 
/*      */   public static void glConvolutionParameter(int target, int pname, IntBuffer params) {
/*  526 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  527 */     long function_pointer = caps.glConvolutionParameteriv;
/*  528 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  529 */     BufferChecks.checkBuffer(params, 4);
/*  530 */     nglConvolutionParameteriv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglConvolutionParameteriv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glCopyConvolutionFilter1D(int target, int internalformat, int x, int y, int width) {
/*  535 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  536 */     long function_pointer = caps.glCopyConvolutionFilter1D;
/*  537 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  538 */     nglCopyConvolutionFilter1D(target, internalformat, x, y, width, function_pointer);
/*      */   }
/*      */   static native void nglCopyConvolutionFilter1D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*      */ 
/*      */   public static void glCopyConvolutionFilter2D(int target, int internalformat, int x, int y, int width, int height) {
/*  543 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  544 */     long function_pointer = caps.glCopyConvolutionFilter2D;
/*  545 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  546 */     nglCopyConvolutionFilter2D(target, internalformat, x, y, width, height, function_pointer);
/*      */   }
/*      */   static native void nglCopyConvolutionFilter2D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/*      */ 
/*      */   public static void glGetConvolutionFilter(int target, int format, int type, ByteBuffer image) {
/*  551 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  552 */     long function_pointer = caps.glGetConvolutionFilter;
/*  553 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  554 */     GLChecks.ensurePackPBOdisabled(caps);
/*  555 */     BufferChecks.checkDirect(image);
/*  556 */     nglGetConvolutionFilter(target, format, type, MemoryUtil.getAddress(image), function_pointer);
/*      */   }
/*      */   public static void glGetConvolutionFilter(int target, int format, int type, DoubleBuffer image) {
/*  559 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  560 */     long function_pointer = caps.glGetConvolutionFilter;
/*  561 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  562 */     GLChecks.ensurePackPBOdisabled(caps);
/*  563 */     BufferChecks.checkDirect(image);
/*  564 */     nglGetConvolutionFilter(target, format, type, MemoryUtil.getAddress(image), function_pointer);
/*      */   }
/*      */   public static void glGetConvolutionFilter(int target, int format, int type, FloatBuffer image) {
/*  567 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  568 */     long function_pointer = caps.glGetConvolutionFilter;
/*  569 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  570 */     GLChecks.ensurePackPBOdisabled(caps);
/*  571 */     BufferChecks.checkDirect(image);
/*  572 */     nglGetConvolutionFilter(target, format, type, MemoryUtil.getAddress(image), function_pointer);
/*      */   }
/*      */   public static void glGetConvolutionFilter(int target, int format, int type, IntBuffer image) {
/*  575 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  576 */     long function_pointer = caps.glGetConvolutionFilter;
/*  577 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  578 */     GLChecks.ensurePackPBOdisabled(caps);
/*  579 */     BufferChecks.checkDirect(image);
/*  580 */     nglGetConvolutionFilter(target, format, type, MemoryUtil.getAddress(image), function_pointer);
/*      */   }
/*      */   public static void glGetConvolutionFilter(int target, int format, int type, ShortBuffer image) {
/*  583 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  584 */     long function_pointer = caps.glGetConvolutionFilter;
/*  585 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  586 */     GLChecks.ensurePackPBOdisabled(caps);
/*  587 */     BufferChecks.checkDirect(image);
/*  588 */     nglGetConvolutionFilter(target, format, type, MemoryUtil.getAddress(image), function_pointer);
/*      */   }
/*      */   static native void nglGetConvolutionFilter(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*  592 */   public static void glGetConvolutionFilter(int target, int format, int type, long image_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  593 */     long function_pointer = caps.glGetConvolutionFilter;
/*  594 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  595 */     GLChecks.ensurePackPBOenabled(caps);
/*  596 */     nglGetConvolutionFilterBO(target, format, type, image_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglGetConvolutionFilterBO(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetConvolutionParameter(int target, int pname, FloatBuffer params) {
/*  601 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  602 */     long function_pointer = caps.glGetConvolutionParameterfv;
/*  603 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  604 */     BufferChecks.checkBuffer(params, 4);
/*  605 */     nglGetConvolutionParameterfv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglGetConvolutionParameterfv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetConvolutionParameter(int target, int pname, IntBuffer params) {
/*  610 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  611 */     long function_pointer = caps.glGetConvolutionParameteriv;
/*  612 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  613 */     BufferChecks.checkBuffer(params, 4);
/*  614 */     nglGetConvolutionParameteriv(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglGetConvolutionParameteriv(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, ByteBuffer row, ByteBuffer column) {
/*  619 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  620 */     long function_pointer = caps.glSeparableFilter2D;
/*  621 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  622 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  623 */     BufferChecks.checkDirect(row);
/*  624 */     BufferChecks.checkDirect(column);
/*  625 */     nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*      */   }
/*      */   public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, ByteBuffer row, DoubleBuffer column) {
/*  628 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  629 */     long function_pointer = caps.glSeparableFilter2D;
/*  630 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  631 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  632 */     BufferChecks.checkDirect(row);
/*  633 */     BufferChecks.checkDirect(column);
/*  634 */     nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*      */   }
/*      */   public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, ByteBuffer row, FloatBuffer column) {
/*  637 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  638 */     long function_pointer = caps.glSeparableFilter2D;
/*  639 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  640 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  641 */     BufferChecks.checkDirect(row);
/*  642 */     BufferChecks.checkDirect(column);
/*  643 */     nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*      */   }
/*      */   public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, ByteBuffer row, IntBuffer column) {
/*  646 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  647 */     long function_pointer = caps.glSeparableFilter2D;
/*  648 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  649 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  650 */     BufferChecks.checkDirect(row);
/*  651 */     BufferChecks.checkDirect(column);
/*  652 */     nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*      */   }
/*      */   public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, ByteBuffer row, ShortBuffer column) {
/*  655 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  656 */     long function_pointer = caps.glSeparableFilter2D;
/*  657 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  658 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  659 */     BufferChecks.checkDirect(row);
/*  660 */     BufferChecks.checkDirect(column);
/*  661 */     nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*      */   }
/*      */   public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, DoubleBuffer row, ByteBuffer column) {
/*  664 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  665 */     long function_pointer = caps.glSeparableFilter2D;
/*  666 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  667 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  668 */     BufferChecks.checkDirect(row);
/*  669 */     BufferChecks.checkDirect(column);
/*  670 */     nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*      */   }
/*      */   public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, DoubleBuffer row, DoubleBuffer column) {
/*  673 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  674 */     long function_pointer = caps.glSeparableFilter2D;
/*  675 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  676 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  677 */     BufferChecks.checkDirect(row);
/*  678 */     BufferChecks.checkDirect(column);
/*  679 */     nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*      */   }
/*      */   public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, DoubleBuffer row, FloatBuffer column) {
/*  682 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  683 */     long function_pointer = caps.glSeparableFilter2D;
/*  684 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  685 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  686 */     BufferChecks.checkDirect(row);
/*  687 */     BufferChecks.checkDirect(column);
/*  688 */     nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*      */   }
/*      */   public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, DoubleBuffer row, IntBuffer column) {
/*  691 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  692 */     long function_pointer = caps.glSeparableFilter2D;
/*  693 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  694 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  695 */     BufferChecks.checkDirect(row);
/*  696 */     BufferChecks.checkDirect(column);
/*  697 */     nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*      */   }
/*      */   public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, DoubleBuffer row, ShortBuffer column) {
/*  700 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  701 */     long function_pointer = caps.glSeparableFilter2D;
/*  702 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  703 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  704 */     BufferChecks.checkDirect(row);
/*  705 */     BufferChecks.checkDirect(column);
/*  706 */     nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*      */   }
/*      */   public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, FloatBuffer row, ByteBuffer column) {
/*  709 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  710 */     long function_pointer = caps.glSeparableFilter2D;
/*  711 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  712 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  713 */     BufferChecks.checkDirect(row);
/*  714 */     BufferChecks.checkDirect(column);
/*  715 */     nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*      */   }
/*      */   public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, FloatBuffer row, DoubleBuffer column) {
/*  718 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  719 */     long function_pointer = caps.glSeparableFilter2D;
/*  720 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  721 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  722 */     BufferChecks.checkDirect(row);
/*  723 */     BufferChecks.checkDirect(column);
/*  724 */     nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*      */   }
/*      */   public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, FloatBuffer row, FloatBuffer column) {
/*  727 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  728 */     long function_pointer = caps.glSeparableFilter2D;
/*  729 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  730 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  731 */     BufferChecks.checkDirect(row);
/*  732 */     BufferChecks.checkDirect(column);
/*  733 */     nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*      */   }
/*      */   public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, FloatBuffer row, IntBuffer column) {
/*  736 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  737 */     long function_pointer = caps.glSeparableFilter2D;
/*  738 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  739 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  740 */     BufferChecks.checkDirect(row);
/*  741 */     BufferChecks.checkDirect(column);
/*  742 */     nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*      */   }
/*      */   public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, FloatBuffer row, ShortBuffer column) {
/*  745 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  746 */     long function_pointer = caps.glSeparableFilter2D;
/*  747 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  748 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  749 */     BufferChecks.checkDirect(row);
/*  750 */     BufferChecks.checkDirect(column);
/*  751 */     nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*      */   }
/*      */   public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, IntBuffer row, ByteBuffer column) {
/*  754 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  755 */     long function_pointer = caps.glSeparableFilter2D;
/*  756 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  757 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  758 */     BufferChecks.checkDirect(row);
/*  759 */     BufferChecks.checkDirect(column);
/*  760 */     nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*      */   }
/*      */   public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, IntBuffer row, DoubleBuffer column) {
/*  763 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  764 */     long function_pointer = caps.glSeparableFilter2D;
/*  765 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  766 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  767 */     BufferChecks.checkDirect(row);
/*  768 */     BufferChecks.checkDirect(column);
/*  769 */     nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*      */   }
/*      */   public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, IntBuffer row, FloatBuffer column) {
/*  772 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  773 */     long function_pointer = caps.glSeparableFilter2D;
/*  774 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  775 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  776 */     BufferChecks.checkDirect(row);
/*  777 */     BufferChecks.checkDirect(column);
/*  778 */     nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*      */   }
/*      */   public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, IntBuffer row, IntBuffer column) {
/*  781 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  782 */     long function_pointer = caps.glSeparableFilter2D;
/*  783 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  784 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  785 */     BufferChecks.checkDirect(row);
/*  786 */     BufferChecks.checkDirect(column);
/*  787 */     nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*      */   }
/*      */   public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, IntBuffer row, ShortBuffer column) {
/*  790 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  791 */     long function_pointer = caps.glSeparableFilter2D;
/*  792 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  793 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  794 */     BufferChecks.checkDirect(row);
/*  795 */     BufferChecks.checkDirect(column);
/*  796 */     nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*      */   }
/*      */   public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, ShortBuffer row, ByteBuffer column) {
/*  799 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  800 */     long function_pointer = caps.glSeparableFilter2D;
/*  801 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  802 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  803 */     BufferChecks.checkDirect(row);
/*  804 */     BufferChecks.checkDirect(column);
/*  805 */     nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*      */   }
/*      */   public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, ShortBuffer row, DoubleBuffer column) {
/*  808 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  809 */     long function_pointer = caps.glSeparableFilter2D;
/*  810 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  811 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  812 */     BufferChecks.checkDirect(row);
/*  813 */     BufferChecks.checkDirect(column);
/*  814 */     nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*      */   }
/*      */   public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, ShortBuffer row, FloatBuffer column) {
/*  817 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  818 */     long function_pointer = caps.glSeparableFilter2D;
/*  819 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  820 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  821 */     BufferChecks.checkDirect(row);
/*  822 */     BufferChecks.checkDirect(column);
/*  823 */     nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*      */   }
/*      */   public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, ShortBuffer row, IntBuffer column) {
/*  826 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  827 */     long function_pointer = caps.glSeparableFilter2D;
/*  828 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  829 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  830 */     BufferChecks.checkDirect(row);
/*  831 */     BufferChecks.checkDirect(column);
/*  832 */     nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*      */   }
/*      */   public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, ShortBuffer row, ShortBuffer column) {
/*  835 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  836 */     long function_pointer = caps.glSeparableFilter2D;
/*  837 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  838 */     GLChecks.ensureUnpackPBOdisabled(caps);
/*  839 */     BufferChecks.checkDirect(row);
/*  840 */     BufferChecks.checkDirect(column);
/*  841 */     nglSeparableFilter2D(target, internalformat, width, height, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), function_pointer);
/*      */   }
/*      */   static native void nglSeparableFilter2D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong1, long paramLong2, long paramLong3);
/*      */ 
/*  845 */   public static void glSeparableFilter2D(int target, int internalformat, int width, int height, int format, int type, long row_buffer_offset, long column_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  846 */     long function_pointer = caps.glSeparableFilter2D;
/*  847 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  848 */     GLChecks.ensureUnpackPBOenabled(caps);
/*  849 */     nglSeparableFilter2DBO(target, internalformat, width, height, format, type, row_buffer_offset, column_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglSeparableFilter2DBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong1, long paramLong2, long paramLong3);
/*      */ 
/*      */   public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, ByteBuffer column, ByteBuffer span) {
/*  854 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  855 */     long function_pointer = caps.glGetSeparableFilter;
/*  856 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  857 */     GLChecks.ensurePackPBOdisabled(caps);
/*  858 */     BufferChecks.checkDirect(row);
/*  859 */     BufferChecks.checkDirect(column);
/*  860 */     BufferChecks.checkDirect(span);
/*  861 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, ByteBuffer column, DoubleBuffer span) {
/*  864 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  865 */     long function_pointer = caps.glGetSeparableFilter;
/*  866 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  867 */     GLChecks.ensurePackPBOdisabled(caps);
/*  868 */     BufferChecks.checkDirect(row);
/*  869 */     BufferChecks.checkDirect(column);
/*  870 */     BufferChecks.checkDirect(span);
/*  871 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, ByteBuffer column, IntBuffer span) {
/*  874 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  875 */     long function_pointer = caps.glGetSeparableFilter;
/*  876 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  877 */     GLChecks.ensurePackPBOdisabled(caps);
/*  878 */     BufferChecks.checkDirect(row);
/*  879 */     BufferChecks.checkDirect(column);
/*  880 */     BufferChecks.checkDirect(span);
/*  881 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, ByteBuffer column, ShortBuffer span) {
/*  884 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  885 */     long function_pointer = caps.glGetSeparableFilter;
/*  886 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  887 */     GLChecks.ensurePackPBOdisabled(caps);
/*  888 */     BufferChecks.checkDirect(row);
/*  889 */     BufferChecks.checkDirect(column);
/*  890 */     BufferChecks.checkDirect(span);
/*  891 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, DoubleBuffer column, ByteBuffer span) {
/*  894 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  895 */     long function_pointer = caps.glGetSeparableFilter;
/*  896 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  897 */     GLChecks.ensurePackPBOdisabled(caps);
/*  898 */     BufferChecks.checkDirect(row);
/*  899 */     BufferChecks.checkDirect(column);
/*  900 */     BufferChecks.checkDirect(span);
/*  901 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, DoubleBuffer column, DoubleBuffer span) {
/*  904 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  905 */     long function_pointer = caps.glGetSeparableFilter;
/*  906 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  907 */     GLChecks.ensurePackPBOdisabled(caps);
/*  908 */     BufferChecks.checkDirect(row);
/*  909 */     BufferChecks.checkDirect(column);
/*  910 */     BufferChecks.checkDirect(span);
/*  911 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, DoubleBuffer column, IntBuffer span) {
/*  914 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  915 */     long function_pointer = caps.glGetSeparableFilter;
/*  916 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  917 */     GLChecks.ensurePackPBOdisabled(caps);
/*  918 */     BufferChecks.checkDirect(row);
/*  919 */     BufferChecks.checkDirect(column);
/*  920 */     BufferChecks.checkDirect(span);
/*  921 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, DoubleBuffer column, ShortBuffer span) {
/*  924 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  925 */     long function_pointer = caps.glGetSeparableFilter;
/*  926 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  927 */     GLChecks.ensurePackPBOdisabled(caps);
/*  928 */     BufferChecks.checkDirect(row);
/*  929 */     BufferChecks.checkDirect(column);
/*  930 */     BufferChecks.checkDirect(span);
/*  931 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, IntBuffer column, ByteBuffer span) {
/*  934 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  935 */     long function_pointer = caps.glGetSeparableFilter;
/*  936 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  937 */     GLChecks.ensurePackPBOdisabled(caps);
/*  938 */     BufferChecks.checkDirect(row);
/*  939 */     BufferChecks.checkDirect(column);
/*  940 */     BufferChecks.checkDirect(span);
/*  941 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, IntBuffer column, DoubleBuffer span) {
/*  944 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  945 */     long function_pointer = caps.glGetSeparableFilter;
/*  946 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  947 */     GLChecks.ensurePackPBOdisabled(caps);
/*  948 */     BufferChecks.checkDirect(row);
/*  949 */     BufferChecks.checkDirect(column);
/*  950 */     BufferChecks.checkDirect(span);
/*  951 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, IntBuffer column, IntBuffer span) {
/*  954 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  955 */     long function_pointer = caps.glGetSeparableFilter;
/*  956 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  957 */     GLChecks.ensurePackPBOdisabled(caps);
/*  958 */     BufferChecks.checkDirect(row);
/*  959 */     BufferChecks.checkDirect(column);
/*  960 */     BufferChecks.checkDirect(span);
/*  961 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, IntBuffer column, ShortBuffer span) {
/*  964 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  965 */     long function_pointer = caps.glGetSeparableFilter;
/*  966 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  967 */     GLChecks.ensurePackPBOdisabled(caps);
/*  968 */     BufferChecks.checkDirect(row);
/*  969 */     BufferChecks.checkDirect(column);
/*  970 */     BufferChecks.checkDirect(span);
/*  971 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, ShortBuffer column, ByteBuffer span) {
/*  974 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  975 */     long function_pointer = caps.glGetSeparableFilter;
/*  976 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  977 */     GLChecks.ensurePackPBOdisabled(caps);
/*  978 */     BufferChecks.checkDirect(row);
/*  979 */     BufferChecks.checkDirect(column);
/*  980 */     BufferChecks.checkDirect(span);
/*  981 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, ShortBuffer column, DoubleBuffer span) {
/*  984 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  985 */     long function_pointer = caps.glGetSeparableFilter;
/*  986 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  987 */     GLChecks.ensurePackPBOdisabled(caps);
/*  988 */     BufferChecks.checkDirect(row);
/*  989 */     BufferChecks.checkDirect(column);
/*  990 */     BufferChecks.checkDirect(span);
/*  991 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, ShortBuffer column, IntBuffer span) {
/*  994 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  995 */     long function_pointer = caps.glGetSeparableFilter;
/*  996 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  997 */     GLChecks.ensurePackPBOdisabled(caps);
/*  998 */     BufferChecks.checkDirect(row);
/*  999 */     BufferChecks.checkDirect(column);
/* 1000 */     BufferChecks.checkDirect(span);
/* 1001 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, ByteBuffer row, ShortBuffer column, ShortBuffer span) {
/* 1004 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1005 */     long function_pointer = caps.glGetSeparableFilter;
/* 1006 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1007 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1008 */     BufferChecks.checkDirect(row);
/* 1009 */     BufferChecks.checkDirect(column);
/* 1010 */     BufferChecks.checkDirect(span);
/* 1011 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, ByteBuffer column, ByteBuffer span) {
/* 1014 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1015 */     long function_pointer = caps.glGetSeparableFilter;
/* 1016 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1017 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1018 */     BufferChecks.checkDirect(row);
/* 1019 */     BufferChecks.checkDirect(column);
/* 1020 */     BufferChecks.checkDirect(span);
/* 1021 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, ByteBuffer column, DoubleBuffer span) {
/* 1024 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1025 */     long function_pointer = caps.glGetSeparableFilter;
/* 1026 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1027 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1028 */     BufferChecks.checkDirect(row);
/* 1029 */     BufferChecks.checkDirect(column);
/* 1030 */     BufferChecks.checkDirect(span);
/* 1031 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, ByteBuffer column, IntBuffer span) {
/* 1034 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1035 */     long function_pointer = caps.glGetSeparableFilter;
/* 1036 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1037 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1038 */     BufferChecks.checkDirect(row);
/* 1039 */     BufferChecks.checkDirect(column);
/* 1040 */     BufferChecks.checkDirect(span);
/* 1041 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, ByteBuffer column, ShortBuffer span) {
/* 1044 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1045 */     long function_pointer = caps.glGetSeparableFilter;
/* 1046 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1047 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1048 */     BufferChecks.checkDirect(row);
/* 1049 */     BufferChecks.checkDirect(column);
/* 1050 */     BufferChecks.checkDirect(span);
/* 1051 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, DoubleBuffer column, ByteBuffer span) {
/* 1054 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1055 */     long function_pointer = caps.glGetSeparableFilter;
/* 1056 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1057 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1058 */     BufferChecks.checkDirect(row);
/* 1059 */     BufferChecks.checkDirect(column);
/* 1060 */     BufferChecks.checkDirect(span);
/* 1061 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, DoubleBuffer column, DoubleBuffer span) {
/* 1064 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1065 */     long function_pointer = caps.glGetSeparableFilter;
/* 1066 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1067 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1068 */     BufferChecks.checkDirect(row);
/* 1069 */     BufferChecks.checkDirect(column);
/* 1070 */     BufferChecks.checkDirect(span);
/* 1071 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, DoubleBuffer column, IntBuffer span) {
/* 1074 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1075 */     long function_pointer = caps.glGetSeparableFilter;
/* 1076 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1077 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1078 */     BufferChecks.checkDirect(row);
/* 1079 */     BufferChecks.checkDirect(column);
/* 1080 */     BufferChecks.checkDirect(span);
/* 1081 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, DoubleBuffer column, ShortBuffer span) {
/* 1084 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1085 */     long function_pointer = caps.glGetSeparableFilter;
/* 1086 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1087 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1088 */     BufferChecks.checkDirect(row);
/* 1089 */     BufferChecks.checkDirect(column);
/* 1090 */     BufferChecks.checkDirect(span);
/* 1091 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, IntBuffer column, ByteBuffer span) {
/* 1094 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1095 */     long function_pointer = caps.glGetSeparableFilter;
/* 1096 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1097 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1098 */     BufferChecks.checkDirect(row);
/* 1099 */     BufferChecks.checkDirect(column);
/* 1100 */     BufferChecks.checkDirect(span);
/* 1101 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, IntBuffer column, DoubleBuffer span) {
/* 1104 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1105 */     long function_pointer = caps.glGetSeparableFilter;
/* 1106 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1107 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1108 */     BufferChecks.checkDirect(row);
/* 1109 */     BufferChecks.checkDirect(column);
/* 1110 */     BufferChecks.checkDirect(span);
/* 1111 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, IntBuffer column, IntBuffer span) {
/* 1114 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1115 */     long function_pointer = caps.glGetSeparableFilter;
/* 1116 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1117 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1118 */     BufferChecks.checkDirect(row);
/* 1119 */     BufferChecks.checkDirect(column);
/* 1120 */     BufferChecks.checkDirect(span);
/* 1121 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, IntBuffer column, ShortBuffer span) {
/* 1124 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1125 */     long function_pointer = caps.glGetSeparableFilter;
/* 1126 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1127 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1128 */     BufferChecks.checkDirect(row);
/* 1129 */     BufferChecks.checkDirect(column);
/* 1130 */     BufferChecks.checkDirect(span);
/* 1131 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, ShortBuffer column, ByteBuffer span) {
/* 1134 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1135 */     long function_pointer = caps.glGetSeparableFilter;
/* 1136 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1137 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1138 */     BufferChecks.checkDirect(row);
/* 1139 */     BufferChecks.checkDirect(column);
/* 1140 */     BufferChecks.checkDirect(span);
/* 1141 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, ShortBuffer column, DoubleBuffer span) {
/* 1144 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1145 */     long function_pointer = caps.glGetSeparableFilter;
/* 1146 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1147 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1148 */     BufferChecks.checkDirect(row);
/* 1149 */     BufferChecks.checkDirect(column);
/* 1150 */     BufferChecks.checkDirect(span);
/* 1151 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, ShortBuffer column, IntBuffer span) {
/* 1154 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1155 */     long function_pointer = caps.glGetSeparableFilter;
/* 1156 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1157 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1158 */     BufferChecks.checkDirect(row);
/* 1159 */     BufferChecks.checkDirect(column);
/* 1160 */     BufferChecks.checkDirect(span);
/* 1161 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, DoubleBuffer row, ShortBuffer column, ShortBuffer span) {
/* 1164 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1165 */     long function_pointer = caps.glGetSeparableFilter;
/* 1166 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1167 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1168 */     BufferChecks.checkDirect(row);
/* 1169 */     BufferChecks.checkDirect(column);
/* 1170 */     BufferChecks.checkDirect(span);
/* 1171 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, ByteBuffer column, ByteBuffer span) {
/* 1174 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1175 */     long function_pointer = caps.glGetSeparableFilter;
/* 1176 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1177 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1178 */     BufferChecks.checkDirect(row);
/* 1179 */     BufferChecks.checkDirect(column);
/* 1180 */     BufferChecks.checkDirect(span);
/* 1181 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, ByteBuffer column, DoubleBuffer span) {
/* 1184 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1185 */     long function_pointer = caps.glGetSeparableFilter;
/* 1186 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1187 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1188 */     BufferChecks.checkDirect(row);
/* 1189 */     BufferChecks.checkDirect(column);
/* 1190 */     BufferChecks.checkDirect(span);
/* 1191 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, ByteBuffer column, IntBuffer span) {
/* 1194 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1195 */     long function_pointer = caps.glGetSeparableFilter;
/* 1196 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1197 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1198 */     BufferChecks.checkDirect(row);
/* 1199 */     BufferChecks.checkDirect(column);
/* 1200 */     BufferChecks.checkDirect(span);
/* 1201 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, ByteBuffer column, ShortBuffer span) {
/* 1204 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1205 */     long function_pointer = caps.glGetSeparableFilter;
/* 1206 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1207 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1208 */     BufferChecks.checkDirect(row);
/* 1209 */     BufferChecks.checkDirect(column);
/* 1210 */     BufferChecks.checkDirect(span);
/* 1211 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, DoubleBuffer column, ByteBuffer span) {
/* 1214 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1215 */     long function_pointer = caps.glGetSeparableFilter;
/* 1216 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1217 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1218 */     BufferChecks.checkDirect(row);
/* 1219 */     BufferChecks.checkDirect(column);
/* 1220 */     BufferChecks.checkDirect(span);
/* 1221 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, DoubleBuffer column, DoubleBuffer span) {
/* 1224 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1225 */     long function_pointer = caps.glGetSeparableFilter;
/* 1226 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1227 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1228 */     BufferChecks.checkDirect(row);
/* 1229 */     BufferChecks.checkDirect(column);
/* 1230 */     BufferChecks.checkDirect(span);
/* 1231 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, DoubleBuffer column, IntBuffer span) {
/* 1234 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1235 */     long function_pointer = caps.glGetSeparableFilter;
/* 1236 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1237 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1238 */     BufferChecks.checkDirect(row);
/* 1239 */     BufferChecks.checkDirect(column);
/* 1240 */     BufferChecks.checkDirect(span);
/* 1241 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, DoubleBuffer column, ShortBuffer span) {
/* 1244 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1245 */     long function_pointer = caps.glGetSeparableFilter;
/* 1246 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1247 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1248 */     BufferChecks.checkDirect(row);
/* 1249 */     BufferChecks.checkDirect(column);
/* 1250 */     BufferChecks.checkDirect(span);
/* 1251 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, IntBuffer column, ByteBuffer span) {
/* 1254 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1255 */     long function_pointer = caps.glGetSeparableFilter;
/* 1256 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1257 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1258 */     BufferChecks.checkDirect(row);
/* 1259 */     BufferChecks.checkDirect(column);
/* 1260 */     BufferChecks.checkDirect(span);
/* 1261 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, IntBuffer column, DoubleBuffer span) {
/* 1264 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1265 */     long function_pointer = caps.glGetSeparableFilter;
/* 1266 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1267 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1268 */     BufferChecks.checkDirect(row);
/* 1269 */     BufferChecks.checkDirect(column);
/* 1270 */     BufferChecks.checkDirect(span);
/* 1271 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, IntBuffer column, IntBuffer span) {
/* 1274 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1275 */     long function_pointer = caps.glGetSeparableFilter;
/* 1276 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1277 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1278 */     BufferChecks.checkDirect(row);
/* 1279 */     BufferChecks.checkDirect(column);
/* 1280 */     BufferChecks.checkDirect(span);
/* 1281 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, IntBuffer column, ShortBuffer span) {
/* 1284 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1285 */     long function_pointer = caps.glGetSeparableFilter;
/* 1286 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1287 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1288 */     BufferChecks.checkDirect(row);
/* 1289 */     BufferChecks.checkDirect(column);
/* 1290 */     BufferChecks.checkDirect(span);
/* 1291 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, ShortBuffer column, ByteBuffer span) {
/* 1294 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1295 */     long function_pointer = caps.glGetSeparableFilter;
/* 1296 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1297 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1298 */     BufferChecks.checkDirect(row);
/* 1299 */     BufferChecks.checkDirect(column);
/* 1300 */     BufferChecks.checkDirect(span);
/* 1301 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, ShortBuffer column, DoubleBuffer span) {
/* 1304 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1305 */     long function_pointer = caps.glGetSeparableFilter;
/* 1306 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1307 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1308 */     BufferChecks.checkDirect(row);
/* 1309 */     BufferChecks.checkDirect(column);
/* 1310 */     BufferChecks.checkDirect(span);
/* 1311 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, ShortBuffer column, IntBuffer span) {
/* 1314 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1315 */     long function_pointer = caps.glGetSeparableFilter;
/* 1316 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1317 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1318 */     BufferChecks.checkDirect(row);
/* 1319 */     BufferChecks.checkDirect(column);
/* 1320 */     BufferChecks.checkDirect(span);
/* 1321 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, FloatBuffer row, ShortBuffer column, ShortBuffer span) {
/* 1324 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1325 */     long function_pointer = caps.glGetSeparableFilter;
/* 1326 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1327 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1328 */     BufferChecks.checkDirect(row);
/* 1329 */     BufferChecks.checkDirect(column);
/* 1330 */     BufferChecks.checkDirect(span);
/* 1331 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, ByteBuffer column, ByteBuffer span) {
/* 1334 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1335 */     long function_pointer = caps.glGetSeparableFilter;
/* 1336 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1337 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1338 */     BufferChecks.checkDirect(row);
/* 1339 */     BufferChecks.checkDirect(column);
/* 1340 */     BufferChecks.checkDirect(span);
/* 1341 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, ByteBuffer column, DoubleBuffer span) {
/* 1344 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1345 */     long function_pointer = caps.glGetSeparableFilter;
/* 1346 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1347 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1348 */     BufferChecks.checkDirect(row);
/* 1349 */     BufferChecks.checkDirect(column);
/* 1350 */     BufferChecks.checkDirect(span);
/* 1351 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, ByteBuffer column, IntBuffer span) {
/* 1354 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1355 */     long function_pointer = caps.glGetSeparableFilter;
/* 1356 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1357 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1358 */     BufferChecks.checkDirect(row);
/* 1359 */     BufferChecks.checkDirect(column);
/* 1360 */     BufferChecks.checkDirect(span);
/* 1361 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, ByteBuffer column, ShortBuffer span) {
/* 1364 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1365 */     long function_pointer = caps.glGetSeparableFilter;
/* 1366 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1367 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1368 */     BufferChecks.checkDirect(row);
/* 1369 */     BufferChecks.checkDirect(column);
/* 1370 */     BufferChecks.checkDirect(span);
/* 1371 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, DoubleBuffer column, ByteBuffer span) {
/* 1374 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1375 */     long function_pointer = caps.glGetSeparableFilter;
/* 1376 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1377 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1378 */     BufferChecks.checkDirect(row);
/* 1379 */     BufferChecks.checkDirect(column);
/* 1380 */     BufferChecks.checkDirect(span);
/* 1381 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, DoubleBuffer column, DoubleBuffer span) {
/* 1384 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1385 */     long function_pointer = caps.glGetSeparableFilter;
/* 1386 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1387 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1388 */     BufferChecks.checkDirect(row);
/* 1389 */     BufferChecks.checkDirect(column);
/* 1390 */     BufferChecks.checkDirect(span);
/* 1391 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, DoubleBuffer column, IntBuffer span) {
/* 1394 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1395 */     long function_pointer = caps.glGetSeparableFilter;
/* 1396 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1397 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1398 */     BufferChecks.checkDirect(row);
/* 1399 */     BufferChecks.checkDirect(column);
/* 1400 */     BufferChecks.checkDirect(span);
/* 1401 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, DoubleBuffer column, ShortBuffer span) {
/* 1404 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1405 */     long function_pointer = caps.glGetSeparableFilter;
/* 1406 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1407 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1408 */     BufferChecks.checkDirect(row);
/* 1409 */     BufferChecks.checkDirect(column);
/* 1410 */     BufferChecks.checkDirect(span);
/* 1411 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, IntBuffer column, ByteBuffer span) {
/* 1414 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1415 */     long function_pointer = caps.glGetSeparableFilter;
/* 1416 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1417 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1418 */     BufferChecks.checkDirect(row);
/* 1419 */     BufferChecks.checkDirect(column);
/* 1420 */     BufferChecks.checkDirect(span);
/* 1421 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, IntBuffer column, DoubleBuffer span) {
/* 1424 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1425 */     long function_pointer = caps.glGetSeparableFilter;
/* 1426 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1427 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1428 */     BufferChecks.checkDirect(row);
/* 1429 */     BufferChecks.checkDirect(column);
/* 1430 */     BufferChecks.checkDirect(span);
/* 1431 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, IntBuffer column, IntBuffer span) {
/* 1434 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1435 */     long function_pointer = caps.glGetSeparableFilter;
/* 1436 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1437 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1438 */     BufferChecks.checkDirect(row);
/* 1439 */     BufferChecks.checkDirect(column);
/* 1440 */     BufferChecks.checkDirect(span);
/* 1441 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, IntBuffer column, ShortBuffer span) {
/* 1444 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1445 */     long function_pointer = caps.glGetSeparableFilter;
/* 1446 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1447 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1448 */     BufferChecks.checkDirect(row);
/* 1449 */     BufferChecks.checkDirect(column);
/* 1450 */     BufferChecks.checkDirect(span);
/* 1451 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, ShortBuffer column, ByteBuffer span) {
/* 1454 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1455 */     long function_pointer = caps.glGetSeparableFilter;
/* 1456 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1457 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1458 */     BufferChecks.checkDirect(row);
/* 1459 */     BufferChecks.checkDirect(column);
/* 1460 */     BufferChecks.checkDirect(span);
/* 1461 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, ShortBuffer column, DoubleBuffer span) {
/* 1464 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1465 */     long function_pointer = caps.glGetSeparableFilter;
/* 1466 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1467 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1468 */     BufferChecks.checkDirect(row);
/* 1469 */     BufferChecks.checkDirect(column);
/* 1470 */     BufferChecks.checkDirect(span);
/* 1471 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, ShortBuffer column, IntBuffer span) {
/* 1474 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1475 */     long function_pointer = caps.glGetSeparableFilter;
/* 1476 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1477 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1478 */     BufferChecks.checkDirect(row);
/* 1479 */     BufferChecks.checkDirect(column);
/* 1480 */     BufferChecks.checkDirect(span);
/* 1481 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, IntBuffer row, ShortBuffer column, ShortBuffer span) {
/* 1484 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1485 */     long function_pointer = caps.glGetSeparableFilter;
/* 1486 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1487 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1488 */     BufferChecks.checkDirect(row);
/* 1489 */     BufferChecks.checkDirect(column);
/* 1490 */     BufferChecks.checkDirect(span);
/* 1491 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, ByteBuffer column, ByteBuffer span) {
/* 1494 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1495 */     long function_pointer = caps.glGetSeparableFilter;
/* 1496 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1497 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1498 */     BufferChecks.checkDirect(row);
/* 1499 */     BufferChecks.checkDirect(column);
/* 1500 */     BufferChecks.checkDirect(span);
/* 1501 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, ByteBuffer column, DoubleBuffer span) {
/* 1504 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1505 */     long function_pointer = caps.glGetSeparableFilter;
/* 1506 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1507 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1508 */     BufferChecks.checkDirect(row);
/* 1509 */     BufferChecks.checkDirect(column);
/* 1510 */     BufferChecks.checkDirect(span);
/* 1511 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, ByteBuffer column, IntBuffer span) {
/* 1514 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1515 */     long function_pointer = caps.glGetSeparableFilter;
/* 1516 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1517 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1518 */     BufferChecks.checkDirect(row);
/* 1519 */     BufferChecks.checkDirect(column);
/* 1520 */     BufferChecks.checkDirect(span);
/* 1521 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, ByteBuffer column, ShortBuffer span) {
/* 1524 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1525 */     long function_pointer = caps.glGetSeparableFilter;
/* 1526 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1527 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1528 */     BufferChecks.checkDirect(row);
/* 1529 */     BufferChecks.checkDirect(column);
/* 1530 */     BufferChecks.checkDirect(span);
/* 1531 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, DoubleBuffer column, ByteBuffer span) {
/* 1534 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1535 */     long function_pointer = caps.glGetSeparableFilter;
/* 1536 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1537 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1538 */     BufferChecks.checkDirect(row);
/* 1539 */     BufferChecks.checkDirect(column);
/* 1540 */     BufferChecks.checkDirect(span);
/* 1541 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, DoubleBuffer column, DoubleBuffer span) {
/* 1544 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1545 */     long function_pointer = caps.glGetSeparableFilter;
/* 1546 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1547 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1548 */     BufferChecks.checkDirect(row);
/* 1549 */     BufferChecks.checkDirect(column);
/* 1550 */     BufferChecks.checkDirect(span);
/* 1551 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, DoubleBuffer column, IntBuffer span) {
/* 1554 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1555 */     long function_pointer = caps.glGetSeparableFilter;
/* 1556 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1557 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1558 */     BufferChecks.checkDirect(row);
/* 1559 */     BufferChecks.checkDirect(column);
/* 1560 */     BufferChecks.checkDirect(span);
/* 1561 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, DoubleBuffer column, ShortBuffer span) {
/* 1564 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1565 */     long function_pointer = caps.glGetSeparableFilter;
/* 1566 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1567 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1568 */     BufferChecks.checkDirect(row);
/* 1569 */     BufferChecks.checkDirect(column);
/* 1570 */     BufferChecks.checkDirect(span);
/* 1571 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, IntBuffer column, ByteBuffer span) {
/* 1574 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1575 */     long function_pointer = caps.glGetSeparableFilter;
/* 1576 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1577 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1578 */     BufferChecks.checkDirect(row);
/* 1579 */     BufferChecks.checkDirect(column);
/* 1580 */     BufferChecks.checkDirect(span);
/* 1581 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, IntBuffer column, DoubleBuffer span) {
/* 1584 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1585 */     long function_pointer = caps.glGetSeparableFilter;
/* 1586 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1587 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1588 */     BufferChecks.checkDirect(row);
/* 1589 */     BufferChecks.checkDirect(column);
/* 1590 */     BufferChecks.checkDirect(span);
/* 1591 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, IntBuffer column, IntBuffer span) {
/* 1594 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1595 */     long function_pointer = caps.glGetSeparableFilter;
/* 1596 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1597 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1598 */     BufferChecks.checkDirect(row);
/* 1599 */     BufferChecks.checkDirect(column);
/* 1600 */     BufferChecks.checkDirect(span);
/* 1601 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, IntBuffer column, ShortBuffer span) {
/* 1604 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1605 */     long function_pointer = caps.glGetSeparableFilter;
/* 1606 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1607 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1608 */     BufferChecks.checkDirect(row);
/* 1609 */     BufferChecks.checkDirect(column);
/* 1610 */     BufferChecks.checkDirect(span);
/* 1611 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, ShortBuffer column, ByteBuffer span) {
/* 1614 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1615 */     long function_pointer = caps.glGetSeparableFilter;
/* 1616 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1617 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1618 */     BufferChecks.checkDirect(row);
/* 1619 */     BufferChecks.checkDirect(column);
/* 1620 */     BufferChecks.checkDirect(span);
/* 1621 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, ShortBuffer column, DoubleBuffer span) {
/* 1624 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1625 */     long function_pointer = caps.glGetSeparableFilter;
/* 1626 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1627 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1628 */     BufferChecks.checkDirect(row);
/* 1629 */     BufferChecks.checkDirect(column);
/* 1630 */     BufferChecks.checkDirect(span);
/* 1631 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, ShortBuffer column, IntBuffer span) {
/* 1634 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1635 */     long function_pointer = caps.glGetSeparableFilter;
/* 1636 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1637 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1638 */     BufferChecks.checkDirect(row);
/* 1639 */     BufferChecks.checkDirect(column);
/* 1640 */     BufferChecks.checkDirect(span);
/* 1641 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetSeparableFilter(int target, int format, int type, ShortBuffer row, ShortBuffer column, ShortBuffer span) {
/* 1644 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1645 */     long function_pointer = caps.glGetSeparableFilter;
/* 1646 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1647 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1648 */     BufferChecks.checkDirect(row);
/* 1649 */     BufferChecks.checkDirect(column);
/* 1650 */     BufferChecks.checkDirect(span);
/* 1651 */     nglGetSeparableFilter(target, format, type, MemoryUtil.getAddress(row), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   static native void nglGetSeparableFilter(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/*      */ 
/* 1655 */   public static void glGetSeparableFilter(int target, int format, int type, long row_buffer_offset, long column_buffer_offset, long span_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1656 */     long function_pointer = caps.glGetSeparableFilter;
/* 1657 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1658 */     GLChecks.ensurePackPBOenabled(caps);
/* 1659 */     nglGetSeparableFilterBO(target, format, type, row_buffer_offset, column_buffer_offset, span_buffer_offset, function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetSeparableFilterBO(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBImaging
 * JD-Core Version:    0.6.2
 */