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
/*      */ public final class ARBRobustness
/*      */ {
/*      */   public static final int GL_NO_ERROR = 0;
/*      */   public static final int GL_GUILTY_CONTEXT_RESET_ARB = 33363;
/*      */   public static final int GL_INNOCENT_CONTEXT_RESET_ARB = 33364;
/*      */   public static final int GL_UNKNOWN_CONTEXT_RESET_ARB = 33365;
/*      */   public static final int GL_RESET_NOTIFICATION_STRATEGY_ARB = 33366;
/*      */   public static final int GL_LOSE_CONTEXT_ON_RESET_ARB = 33362;
/*      */   public static final int GL_NO_RESET_NOTIFICATION_ARB = 33377;
/*      */   public static final int GL_CONTEXT_FLAG_ROBUST_ACCESS_BIT_ARB = 4;
/*      */ 
/*      */   public static int glGetGraphicsResetStatusARB()
/*      */   {
/*   39 */     ContextCapabilities caps = GLContext.getCapabilities();
/*   40 */     long function_pointer = caps.glGetGraphicsResetStatusARB;
/*   41 */     BufferChecks.checkFunctionAddress(function_pointer);
/*   42 */     int __result = nglGetGraphicsResetStatusARB(function_pointer);
/*   43 */     return __result;
/*      */   }
/*      */   static native int nglGetGraphicsResetStatusARB(long paramLong);
/*      */ 
/*      */   public static void glGetnMapdvARB(int target, int query, DoubleBuffer v) {
/*   48 */     ContextCapabilities caps = GLContext.getCapabilities();
/*   49 */     long function_pointer = caps.glGetnMapdvARB;
/*   50 */     BufferChecks.checkFunctionAddress(function_pointer);
/*   51 */     BufferChecks.checkDirect(v);
/*   52 */     nglGetnMapdvARB(target, query, v.remaining() << 3, MemoryUtil.getAddress(v), function_pointer);
/*      */   }
/*      */   static native void nglGetnMapdvARB(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetnMapfvARB(int target, int query, FloatBuffer v) {
/*   57 */     ContextCapabilities caps = GLContext.getCapabilities();
/*   58 */     long function_pointer = caps.glGetnMapfvARB;
/*   59 */     BufferChecks.checkFunctionAddress(function_pointer);
/*   60 */     BufferChecks.checkDirect(v);
/*   61 */     nglGetnMapfvARB(target, query, v.remaining() << 2, MemoryUtil.getAddress(v), function_pointer);
/*      */   }
/*      */   static native void nglGetnMapfvARB(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetnMapivARB(int target, int query, IntBuffer v) {
/*   66 */     ContextCapabilities caps = GLContext.getCapabilities();
/*   67 */     long function_pointer = caps.glGetnMapivARB;
/*   68 */     BufferChecks.checkFunctionAddress(function_pointer);
/*   69 */     BufferChecks.checkDirect(v);
/*   70 */     nglGetnMapivARB(target, query, v.remaining() << 2, MemoryUtil.getAddress(v), function_pointer);
/*      */   }
/*      */   static native void nglGetnMapivARB(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetnPixelMapfvARB(int map, FloatBuffer values) {
/*   75 */     ContextCapabilities caps = GLContext.getCapabilities();
/*   76 */     long function_pointer = caps.glGetnPixelMapfvARB;
/*   77 */     BufferChecks.checkFunctionAddress(function_pointer);
/*   78 */     BufferChecks.checkDirect(values);
/*   79 */     nglGetnPixelMapfvARB(map, values.remaining() << 2, MemoryUtil.getAddress(values), function_pointer);
/*      */   }
/*      */   static native void nglGetnPixelMapfvARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetnPixelMapuivARB(int map, IntBuffer values) {
/*   84 */     ContextCapabilities caps = GLContext.getCapabilities();
/*   85 */     long function_pointer = caps.glGetnPixelMapuivARB;
/*   86 */     BufferChecks.checkFunctionAddress(function_pointer);
/*   87 */     BufferChecks.checkDirect(values);
/*   88 */     nglGetnPixelMapuivARB(map, values.remaining() << 2, MemoryUtil.getAddress(values), function_pointer);
/*      */   }
/*      */   static native void nglGetnPixelMapuivARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetnPixelMapusvARB(int map, ShortBuffer values) {
/*   93 */     ContextCapabilities caps = GLContext.getCapabilities();
/*   94 */     long function_pointer = caps.glGetnPixelMapusvARB;
/*   95 */     BufferChecks.checkFunctionAddress(function_pointer);
/*   96 */     BufferChecks.checkDirect(values);
/*   97 */     nglGetnPixelMapusvARB(map, values.remaining() << 1, MemoryUtil.getAddress(values), function_pointer);
/*      */   }
/*      */   static native void nglGetnPixelMapusvARB(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetnPolygonStippleARB(ByteBuffer pattern) {
/*  102 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  103 */     long function_pointer = caps.glGetnPolygonStippleARB;
/*  104 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  105 */     BufferChecks.checkDirect(pattern);
/*  106 */     nglGetnPolygonStippleARB(pattern.remaining(), MemoryUtil.getAddress(pattern), function_pointer);
/*      */   }
/*      */   static native void nglGetnPolygonStippleARB(int paramInt, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetnTexImageARB(int target, int level, int format, int type, ByteBuffer img) {
/*  111 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  112 */     long function_pointer = caps.glGetnTexImageARB;
/*  113 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  114 */     GLChecks.ensurePackPBOdisabled(caps);
/*  115 */     BufferChecks.checkDirect(img);
/*  116 */     nglGetnTexImageARB(target, level, format, type, img.remaining(), MemoryUtil.getAddress(img), function_pointer);
/*      */   }
/*      */   public static void glGetnTexImageARB(int target, int level, int format, int type, DoubleBuffer img) {
/*  119 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  120 */     long function_pointer = caps.glGetnTexImageARB;
/*  121 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  122 */     GLChecks.ensurePackPBOdisabled(caps);
/*  123 */     BufferChecks.checkDirect(img);
/*  124 */     nglGetnTexImageARB(target, level, format, type, img.remaining() << 3, MemoryUtil.getAddress(img), function_pointer);
/*      */   }
/*      */   public static void glGetnTexImageARB(int target, int level, int format, int type, FloatBuffer img) {
/*  127 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  128 */     long function_pointer = caps.glGetnTexImageARB;
/*  129 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  130 */     GLChecks.ensurePackPBOdisabled(caps);
/*  131 */     BufferChecks.checkDirect(img);
/*  132 */     nglGetnTexImageARB(target, level, format, type, img.remaining() << 2, MemoryUtil.getAddress(img), function_pointer);
/*      */   }
/*      */   public static void glGetnTexImageARB(int target, int level, int format, int type, IntBuffer img) {
/*  135 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  136 */     long function_pointer = caps.glGetnTexImageARB;
/*  137 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  138 */     GLChecks.ensurePackPBOdisabled(caps);
/*  139 */     BufferChecks.checkDirect(img);
/*  140 */     nglGetnTexImageARB(target, level, format, type, img.remaining() << 2, MemoryUtil.getAddress(img), function_pointer);
/*      */   }
/*      */   public static void glGetnTexImageARB(int target, int level, int format, int type, ShortBuffer img) {
/*  143 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  144 */     long function_pointer = caps.glGetnTexImageARB;
/*  145 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  146 */     GLChecks.ensurePackPBOdisabled(caps);
/*  147 */     BufferChecks.checkDirect(img);
/*  148 */     nglGetnTexImageARB(target, level, format, type, img.remaining() << 1, MemoryUtil.getAddress(img), function_pointer);
/*      */   }
/*      */   static native void nglGetnTexImageARB(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/*      */ 
/*  152 */   public static void glGetnTexImageARB(int target, int level, int format, int type, int img_bufSize, long img_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  153 */     long function_pointer = caps.glGetnTexImageARB;
/*  154 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  155 */     GLChecks.ensurePackPBOenabled(caps);
/*  156 */     nglGetnTexImageARBBO(target, level, format, type, img_bufSize, img_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglGetnTexImageARBBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glReadnPixelsARB(int x, int y, int width, int height, int format, int type, ByteBuffer data) {
/*  161 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  162 */     long function_pointer = caps.glReadnPixelsARB;
/*  163 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  164 */     GLChecks.ensurePackPBOdisabled(caps);
/*  165 */     BufferChecks.checkDirect(data);
/*  166 */     nglReadnPixelsARB(x, y, width, height, format, type, data.remaining(), MemoryUtil.getAddress(data), function_pointer);
/*      */   }
/*      */   public static void glReadnPixelsARB(int x, int y, int width, int height, int format, int type, DoubleBuffer data) {
/*  169 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  170 */     long function_pointer = caps.glReadnPixelsARB;
/*  171 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  172 */     GLChecks.ensurePackPBOdisabled(caps);
/*  173 */     BufferChecks.checkDirect(data);
/*  174 */     nglReadnPixelsARB(x, y, width, height, format, type, data.remaining() << 3, MemoryUtil.getAddress(data), function_pointer);
/*      */   }
/*      */   public static void glReadnPixelsARB(int x, int y, int width, int height, int format, int type, FloatBuffer data) {
/*  177 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  178 */     long function_pointer = caps.glReadnPixelsARB;
/*  179 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  180 */     GLChecks.ensurePackPBOdisabled(caps);
/*  181 */     BufferChecks.checkDirect(data);
/*  182 */     nglReadnPixelsARB(x, y, width, height, format, type, data.remaining() << 2, MemoryUtil.getAddress(data), function_pointer);
/*      */   }
/*      */   public static void glReadnPixelsARB(int x, int y, int width, int height, int format, int type, IntBuffer data) {
/*  185 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  186 */     long function_pointer = caps.glReadnPixelsARB;
/*  187 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  188 */     GLChecks.ensurePackPBOdisabled(caps);
/*  189 */     BufferChecks.checkDirect(data);
/*  190 */     nglReadnPixelsARB(x, y, width, height, format, type, data.remaining() << 2, MemoryUtil.getAddress(data), function_pointer);
/*      */   }
/*      */   public static void glReadnPixelsARB(int x, int y, int width, int height, int format, int type, ShortBuffer data) {
/*  193 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  194 */     long function_pointer = caps.glReadnPixelsARB;
/*  195 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  196 */     GLChecks.ensurePackPBOdisabled(caps);
/*  197 */     BufferChecks.checkDirect(data);
/*  198 */     nglReadnPixelsARB(x, y, width, height, format, type, data.remaining() << 1, MemoryUtil.getAddress(data), function_pointer);
/*      */   }
/*      */   static native void nglReadnPixelsARB(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong1, long paramLong2);
/*      */ 
/*  202 */   public static void glReadnPixelsARB(int x, int y, int width, int height, int format, int type, int data_bufSize, long data_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  203 */     long function_pointer = caps.glReadnPixelsARB;
/*  204 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  205 */     GLChecks.ensurePackPBOenabled(caps);
/*  206 */     nglReadnPixelsARBBO(x, y, width, height, format, type, data_bufSize, data_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglReadnPixelsARBBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetnColorTableARB(int target, int format, int type, ByteBuffer table) {
/*  211 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  212 */     long function_pointer = caps.glGetnColorTableARB;
/*  213 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  214 */     BufferChecks.checkDirect(table);
/*  215 */     nglGetnColorTableARB(target, format, type, table.remaining(), MemoryUtil.getAddress(table), function_pointer);
/*      */   }
/*      */   public static void glGetnColorTableARB(int target, int format, int type, DoubleBuffer table) {
/*  218 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  219 */     long function_pointer = caps.glGetnColorTableARB;
/*  220 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  221 */     BufferChecks.checkDirect(table);
/*  222 */     nglGetnColorTableARB(target, format, type, table.remaining() << 3, MemoryUtil.getAddress(table), function_pointer);
/*      */   }
/*      */   public static void glGetnColorTableARB(int target, int format, int type, FloatBuffer table) {
/*  225 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  226 */     long function_pointer = caps.glGetnColorTableARB;
/*  227 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  228 */     BufferChecks.checkDirect(table);
/*  229 */     nglGetnColorTableARB(target, format, type, table.remaining() << 2, MemoryUtil.getAddress(table), function_pointer);
/*      */   }
/*      */   static native void nglGetnColorTableARB(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetnConvolutionFilterARB(int target, int format, int type, ByteBuffer image) {
/*  234 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  235 */     long function_pointer = caps.glGetnConvolutionFilterARB;
/*  236 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  237 */     GLChecks.ensurePackPBOdisabled(caps);
/*  238 */     BufferChecks.checkDirect(image);
/*  239 */     nglGetnConvolutionFilterARB(target, format, type, image.remaining(), MemoryUtil.getAddress(image), function_pointer);
/*      */   }
/*      */   public static void glGetnConvolutionFilterARB(int target, int format, int type, DoubleBuffer image) {
/*  242 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  243 */     long function_pointer = caps.glGetnConvolutionFilterARB;
/*  244 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  245 */     GLChecks.ensurePackPBOdisabled(caps);
/*  246 */     BufferChecks.checkDirect(image);
/*  247 */     nglGetnConvolutionFilterARB(target, format, type, image.remaining() << 3, MemoryUtil.getAddress(image), function_pointer);
/*      */   }
/*      */   public static void glGetnConvolutionFilterARB(int target, int format, int type, FloatBuffer image) {
/*  250 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  251 */     long function_pointer = caps.glGetnConvolutionFilterARB;
/*  252 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  253 */     GLChecks.ensurePackPBOdisabled(caps);
/*  254 */     BufferChecks.checkDirect(image);
/*  255 */     nglGetnConvolutionFilterARB(target, format, type, image.remaining() << 2, MemoryUtil.getAddress(image), function_pointer);
/*      */   }
/*      */   public static void glGetnConvolutionFilterARB(int target, int format, int type, IntBuffer image) {
/*  258 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  259 */     long function_pointer = caps.glGetnConvolutionFilterARB;
/*  260 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  261 */     GLChecks.ensurePackPBOdisabled(caps);
/*  262 */     BufferChecks.checkDirect(image);
/*  263 */     nglGetnConvolutionFilterARB(target, format, type, image.remaining() << 2, MemoryUtil.getAddress(image), function_pointer);
/*      */   }
/*      */   public static void glGetnConvolutionFilterARB(int target, int format, int type, ShortBuffer image) {
/*  266 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  267 */     long function_pointer = caps.glGetnConvolutionFilterARB;
/*  268 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  269 */     GLChecks.ensurePackPBOdisabled(caps);
/*  270 */     BufferChecks.checkDirect(image);
/*  271 */     nglGetnConvolutionFilterARB(target, format, type, image.remaining() << 1, MemoryUtil.getAddress(image), function_pointer);
/*      */   }
/*      */   static native void nglGetnConvolutionFilterARB(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*      */ 
/*  275 */   public static void glGetnConvolutionFilterARB(int target, int format, int type, int image_bufSize, long image_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/*  276 */     long function_pointer = caps.glGetnConvolutionFilterARB;
/*  277 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  278 */     GLChecks.ensurePackPBOenabled(caps);
/*  279 */     nglGetnConvolutionFilterARBBO(target, format, type, image_bufSize, image_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglGetnConvolutionFilterARBBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, ByteBuffer column, ByteBuffer span) {
/*  284 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  285 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  286 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  287 */     GLChecks.ensurePackPBOdisabled(caps);
/*  288 */     BufferChecks.checkDirect(row);
/*  289 */     BufferChecks.checkDirect(column);
/*  290 */     BufferChecks.checkDirect(span);
/*  291 */     nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, ByteBuffer column, DoubleBuffer span) {
/*  294 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  295 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  296 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  297 */     GLChecks.ensurePackPBOdisabled(caps);
/*  298 */     BufferChecks.checkDirect(row);
/*  299 */     BufferChecks.checkDirect(column);
/*  300 */     BufferChecks.checkDirect(span);
/*  301 */     nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, ByteBuffer column, FloatBuffer span) {
/*  304 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  305 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  306 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  307 */     GLChecks.ensurePackPBOdisabled(caps);
/*  308 */     BufferChecks.checkDirect(row);
/*  309 */     BufferChecks.checkDirect(column);
/*  310 */     BufferChecks.checkDirect(span);
/*  311 */     nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, ByteBuffer column, IntBuffer span) {
/*  314 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  315 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  316 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  317 */     GLChecks.ensurePackPBOdisabled(caps);
/*  318 */     BufferChecks.checkDirect(row);
/*  319 */     BufferChecks.checkDirect(column);
/*  320 */     BufferChecks.checkDirect(span);
/*  321 */     nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, ByteBuffer column, ShortBuffer span) {
/*  324 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  325 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  326 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  327 */     GLChecks.ensurePackPBOdisabled(caps);
/*  328 */     BufferChecks.checkDirect(row);
/*  329 */     BufferChecks.checkDirect(column);
/*  330 */     BufferChecks.checkDirect(span);
/*  331 */     nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, DoubleBuffer column, ByteBuffer span) {
/*  334 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  335 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  336 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  337 */     GLChecks.ensurePackPBOdisabled(caps);
/*  338 */     BufferChecks.checkDirect(row);
/*  339 */     BufferChecks.checkDirect(column);
/*  340 */     BufferChecks.checkDirect(span);
/*  341 */     nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, DoubleBuffer column, DoubleBuffer span) {
/*  344 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  345 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  346 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  347 */     GLChecks.ensurePackPBOdisabled(caps);
/*  348 */     BufferChecks.checkDirect(row);
/*  349 */     BufferChecks.checkDirect(column);
/*  350 */     BufferChecks.checkDirect(span);
/*  351 */     nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, DoubleBuffer column, FloatBuffer span) {
/*  354 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  355 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  356 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  357 */     GLChecks.ensurePackPBOdisabled(caps);
/*  358 */     BufferChecks.checkDirect(row);
/*  359 */     BufferChecks.checkDirect(column);
/*  360 */     BufferChecks.checkDirect(span);
/*  361 */     nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, DoubleBuffer column, IntBuffer span) {
/*  364 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  365 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  366 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  367 */     GLChecks.ensurePackPBOdisabled(caps);
/*  368 */     BufferChecks.checkDirect(row);
/*  369 */     BufferChecks.checkDirect(column);
/*  370 */     BufferChecks.checkDirect(span);
/*  371 */     nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, DoubleBuffer column, ShortBuffer span) {
/*  374 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  375 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  376 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  377 */     GLChecks.ensurePackPBOdisabled(caps);
/*  378 */     BufferChecks.checkDirect(row);
/*  379 */     BufferChecks.checkDirect(column);
/*  380 */     BufferChecks.checkDirect(span);
/*  381 */     nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, FloatBuffer column, ByteBuffer span) {
/*  384 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  385 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  386 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  387 */     GLChecks.ensurePackPBOdisabled(caps);
/*  388 */     BufferChecks.checkDirect(row);
/*  389 */     BufferChecks.checkDirect(column);
/*  390 */     BufferChecks.checkDirect(span);
/*  391 */     nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, FloatBuffer column, DoubleBuffer span) {
/*  394 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  395 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  396 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  397 */     GLChecks.ensurePackPBOdisabled(caps);
/*  398 */     BufferChecks.checkDirect(row);
/*  399 */     BufferChecks.checkDirect(column);
/*  400 */     BufferChecks.checkDirect(span);
/*  401 */     nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, FloatBuffer column, FloatBuffer span) {
/*  404 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  405 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  406 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  407 */     GLChecks.ensurePackPBOdisabled(caps);
/*  408 */     BufferChecks.checkDirect(row);
/*  409 */     BufferChecks.checkDirect(column);
/*  410 */     BufferChecks.checkDirect(span);
/*  411 */     nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, FloatBuffer column, IntBuffer span) {
/*  414 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  415 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  416 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  417 */     GLChecks.ensurePackPBOdisabled(caps);
/*  418 */     BufferChecks.checkDirect(row);
/*  419 */     BufferChecks.checkDirect(column);
/*  420 */     BufferChecks.checkDirect(span);
/*  421 */     nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, FloatBuffer column, ShortBuffer span) {
/*  424 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  425 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  426 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  427 */     GLChecks.ensurePackPBOdisabled(caps);
/*  428 */     BufferChecks.checkDirect(row);
/*  429 */     BufferChecks.checkDirect(column);
/*  430 */     BufferChecks.checkDirect(span);
/*  431 */     nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, IntBuffer column, ByteBuffer span) {
/*  434 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  435 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  436 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  437 */     GLChecks.ensurePackPBOdisabled(caps);
/*  438 */     BufferChecks.checkDirect(row);
/*  439 */     BufferChecks.checkDirect(column);
/*  440 */     BufferChecks.checkDirect(span);
/*  441 */     nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, IntBuffer column, DoubleBuffer span) {
/*  444 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  445 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  446 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  447 */     GLChecks.ensurePackPBOdisabled(caps);
/*  448 */     BufferChecks.checkDirect(row);
/*  449 */     BufferChecks.checkDirect(column);
/*  450 */     BufferChecks.checkDirect(span);
/*  451 */     nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, IntBuffer column, FloatBuffer span) {
/*  454 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  455 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  456 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  457 */     GLChecks.ensurePackPBOdisabled(caps);
/*  458 */     BufferChecks.checkDirect(row);
/*  459 */     BufferChecks.checkDirect(column);
/*  460 */     BufferChecks.checkDirect(span);
/*  461 */     nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, IntBuffer column, IntBuffer span) {
/*  464 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  465 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  466 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  467 */     GLChecks.ensurePackPBOdisabled(caps);
/*  468 */     BufferChecks.checkDirect(row);
/*  469 */     BufferChecks.checkDirect(column);
/*  470 */     BufferChecks.checkDirect(span);
/*  471 */     nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, IntBuffer column, ShortBuffer span) {
/*  474 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  475 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  476 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  477 */     GLChecks.ensurePackPBOdisabled(caps);
/*  478 */     BufferChecks.checkDirect(row);
/*  479 */     BufferChecks.checkDirect(column);
/*  480 */     BufferChecks.checkDirect(span);
/*  481 */     nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, ShortBuffer column, ByteBuffer span) {
/*  484 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  485 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  486 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  487 */     GLChecks.ensurePackPBOdisabled(caps);
/*  488 */     BufferChecks.checkDirect(row);
/*  489 */     BufferChecks.checkDirect(column);
/*  490 */     BufferChecks.checkDirect(span);
/*  491 */     nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, ShortBuffer column, DoubleBuffer span) {
/*  494 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  495 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  496 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  497 */     GLChecks.ensurePackPBOdisabled(caps);
/*  498 */     BufferChecks.checkDirect(row);
/*  499 */     BufferChecks.checkDirect(column);
/*  500 */     BufferChecks.checkDirect(span);
/*  501 */     nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, ShortBuffer column, FloatBuffer span) {
/*  504 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  505 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  506 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  507 */     GLChecks.ensurePackPBOdisabled(caps);
/*  508 */     BufferChecks.checkDirect(row);
/*  509 */     BufferChecks.checkDirect(column);
/*  510 */     BufferChecks.checkDirect(span);
/*  511 */     nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, ShortBuffer column, IntBuffer span) {
/*  514 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  515 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  516 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  517 */     GLChecks.ensurePackPBOdisabled(caps);
/*  518 */     BufferChecks.checkDirect(row);
/*  519 */     BufferChecks.checkDirect(column);
/*  520 */     BufferChecks.checkDirect(span);
/*  521 */     nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ByteBuffer row, ShortBuffer column, ShortBuffer span) {
/*  524 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  525 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  526 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  527 */     GLChecks.ensurePackPBOdisabled(caps);
/*  528 */     BufferChecks.checkDirect(row);
/*  529 */     BufferChecks.checkDirect(column);
/*  530 */     BufferChecks.checkDirect(span);
/*  531 */     nglGetnSeparableFilterARB(target, format, type, row.remaining(), MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, ByteBuffer column, ByteBuffer span) {
/*  534 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  535 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  536 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  537 */     GLChecks.ensurePackPBOdisabled(caps);
/*  538 */     BufferChecks.checkDirect(row);
/*  539 */     BufferChecks.checkDirect(column);
/*  540 */     BufferChecks.checkDirect(span);
/*  541 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, ByteBuffer column, DoubleBuffer span) {
/*  544 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  545 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  546 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  547 */     GLChecks.ensurePackPBOdisabled(caps);
/*  548 */     BufferChecks.checkDirect(row);
/*  549 */     BufferChecks.checkDirect(column);
/*  550 */     BufferChecks.checkDirect(span);
/*  551 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, ByteBuffer column, FloatBuffer span) {
/*  554 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  555 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  556 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  557 */     GLChecks.ensurePackPBOdisabled(caps);
/*  558 */     BufferChecks.checkDirect(row);
/*  559 */     BufferChecks.checkDirect(column);
/*  560 */     BufferChecks.checkDirect(span);
/*  561 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, ByteBuffer column, IntBuffer span) {
/*  564 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  565 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  566 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  567 */     GLChecks.ensurePackPBOdisabled(caps);
/*  568 */     BufferChecks.checkDirect(row);
/*  569 */     BufferChecks.checkDirect(column);
/*  570 */     BufferChecks.checkDirect(span);
/*  571 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, ByteBuffer column, ShortBuffer span) {
/*  574 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  575 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  576 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  577 */     GLChecks.ensurePackPBOdisabled(caps);
/*  578 */     BufferChecks.checkDirect(row);
/*  579 */     BufferChecks.checkDirect(column);
/*  580 */     BufferChecks.checkDirect(span);
/*  581 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, DoubleBuffer column, ByteBuffer span) {
/*  584 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  585 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  586 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  587 */     GLChecks.ensurePackPBOdisabled(caps);
/*  588 */     BufferChecks.checkDirect(row);
/*  589 */     BufferChecks.checkDirect(column);
/*  590 */     BufferChecks.checkDirect(span);
/*  591 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, DoubleBuffer column, DoubleBuffer span) {
/*  594 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  595 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  596 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  597 */     GLChecks.ensurePackPBOdisabled(caps);
/*  598 */     BufferChecks.checkDirect(row);
/*  599 */     BufferChecks.checkDirect(column);
/*  600 */     BufferChecks.checkDirect(span);
/*  601 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, DoubleBuffer column, FloatBuffer span) {
/*  604 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  605 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  606 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  607 */     GLChecks.ensurePackPBOdisabled(caps);
/*  608 */     BufferChecks.checkDirect(row);
/*  609 */     BufferChecks.checkDirect(column);
/*  610 */     BufferChecks.checkDirect(span);
/*  611 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, DoubleBuffer column, IntBuffer span) {
/*  614 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  615 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  616 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  617 */     GLChecks.ensurePackPBOdisabled(caps);
/*  618 */     BufferChecks.checkDirect(row);
/*  619 */     BufferChecks.checkDirect(column);
/*  620 */     BufferChecks.checkDirect(span);
/*  621 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, DoubleBuffer column, ShortBuffer span) {
/*  624 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  625 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  626 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  627 */     GLChecks.ensurePackPBOdisabled(caps);
/*  628 */     BufferChecks.checkDirect(row);
/*  629 */     BufferChecks.checkDirect(column);
/*  630 */     BufferChecks.checkDirect(span);
/*  631 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, FloatBuffer column, ByteBuffer span) {
/*  634 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  635 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  636 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  637 */     GLChecks.ensurePackPBOdisabled(caps);
/*  638 */     BufferChecks.checkDirect(row);
/*  639 */     BufferChecks.checkDirect(column);
/*  640 */     BufferChecks.checkDirect(span);
/*  641 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, FloatBuffer column, DoubleBuffer span) {
/*  644 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  645 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  646 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  647 */     GLChecks.ensurePackPBOdisabled(caps);
/*  648 */     BufferChecks.checkDirect(row);
/*  649 */     BufferChecks.checkDirect(column);
/*  650 */     BufferChecks.checkDirect(span);
/*  651 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, FloatBuffer column, FloatBuffer span) {
/*  654 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  655 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  656 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  657 */     GLChecks.ensurePackPBOdisabled(caps);
/*  658 */     BufferChecks.checkDirect(row);
/*  659 */     BufferChecks.checkDirect(column);
/*  660 */     BufferChecks.checkDirect(span);
/*  661 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, FloatBuffer column, IntBuffer span) {
/*  664 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  665 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  666 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  667 */     GLChecks.ensurePackPBOdisabled(caps);
/*  668 */     BufferChecks.checkDirect(row);
/*  669 */     BufferChecks.checkDirect(column);
/*  670 */     BufferChecks.checkDirect(span);
/*  671 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, FloatBuffer column, ShortBuffer span) {
/*  674 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  675 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  676 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  677 */     GLChecks.ensurePackPBOdisabled(caps);
/*  678 */     BufferChecks.checkDirect(row);
/*  679 */     BufferChecks.checkDirect(column);
/*  680 */     BufferChecks.checkDirect(span);
/*  681 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, IntBuffer column, ByteBuffer span) {
/*  684 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  685 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  686 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  687 */     GLChecks.ensurePackPBOdisabled(caps);
/*  688 */     BufferChecks.checkDirect(row);
/*  689 */     BufferChecks.checkDirect(column);
/*  690 */     BufferChecks.checkDirect(span);
/*  691 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, IntBuffer column, DoubleBuffer span) {
/*  694 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  695 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  696 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  697 */     GLChecks.ensurePackPBOdisabled(caps);
/*  698 */     BufferChecks.checkDirect(row);
/*  699 */     BufferChecks.checkDirect(column);
/*  700 */     BufferChecks.checkDirect(span);
/*  701 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, IntBuffer column, FloatBuffer span) {
/*  704 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  705 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  706 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  707 */     GLChecks.ensurePackPBOdisabled(caps);
/*  708 */     BufferChecks.checkDirect(row);
/*  709 */     BufferChecks.checkDirect(column);
/*  710 */     BufferChecks.checkDirect(span);
/*  711 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, IntBuffer column, IntBuffer span) {
/*  714 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  715 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  716 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  717 */     GLChecks.ensurePackPBOdisabled(caps);
/*  718 */     BufferChecks.checkDirect(row);
/*  719 */     BufferChecks.checkDirect(column);
/*  720 */     BufferChecks.checkDirect(span);
/*  721 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, IntBuffer column, ShortBuffer span) {
/*  724 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  725 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  726 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  727 */     GLChecks.ensurePackPBOdisabled(caps);
/*  728 */     BufferChecks.checkDirect(row);
/*  729 */     BufferChecks.checkDirect(column);
/*  730 */     BufferChecks.checkDirect(span);
/*  731 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, ShortBuffer column, ByteBuffer span) {
/*  734 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  735 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  736 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  737 */     GLChecks.ensurePackPBOdisabled(caps);
/*  738 */     BufferChecks.checkDirect(row);
/*  739 */     BufferChecks.checkDirect(column);
/*  740 */     BufferChecks.checkDirect(span);
/*  741 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, ShortBuffer column, DoubleBuffer span) {
/*  744 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  745 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  746 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  747 */     GLChecks.ensurePackPBOdisabled(caps);
/*  748 */     BufferChecks.checkDirect(row);
/*  749 */     BufferChecks.checkDirect(column);
/*  750 */     BufferChecks.checkDirect(span);
/*  751 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, ShortBuffer column, FloatBuffer span) {
/*  754 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  755 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  756 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  757 */     GLChecks.ensurePackPBOdisabled(caps);
/*  758 */     BufferChecks.checkDirect(row);
/*  759 */     BufferChecks.checkDirect(column);
/*  760 */     BufferChecks.checkDirect(span);
/*  761 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, ShortBuffer column, IntBuffer span) {
/*  764 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  765 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  766 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  767 */     GLChecks.ensurePackPBOdisabled(caps);
/*  768 */     BufferChecks.checkDirect(row);
/*  769 */     BufferChecks.checkDirect(column);
/*  770 */     BufferChecks.checkDirect(span);
/*  771 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, DoubleBuffer row, ShortBuffer column, ShortBuffer span) {
/*  774 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  775 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  776 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  777 */     GLChecks.ensurePackPBOdisabled(caps);
/*  778 */     BufferChecks.checkDirect(row);
/*  779 */     BufferChecks.checkDirect(column);
/*  780 */     BufferChecks.checkDirect(span);
/*  781 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 3, MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, ByteBuffer column, ByteBuffer span) {
/*  784 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  785 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  786 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  787 */     GLChecks.ensurePackPBOdisabled(caps);
/*  788 */     BufferChecks.checkDirect(row);
/*  789 */     BufferChecks.checkDirect(column);
/*  790 */     BufferChecks.checkDirect(span);
/*  791 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, ByteBuffer column, DoubleBuffer span) {
/*  794 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  795 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  796 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  797 */     GLChecks.ensurePackPBOdisabled(caps);
/*  798 */     BufferChecks.checkDirect(row);
/*  799 */     BufferChecks.checkDirect(column);
/*  800 */     BufferChecks.checkDirect(span);
/*  801 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, ByteBuffer column, FloatBuffer span) {
/*  804 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  805 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  806 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  807 */     GLChecks.ensurePackPBOdisabled(caps);
/*  808 */     BufferChecks.checkDirect(row);
/*  809 */     BufferChecks.checkDirect(column);
/*  810 */     BufferChecks.checkDirect(span);
/*  811 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, ByteBuffer column, IntBuffer span) {
/*  814 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  815 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  816 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  817 */     GLChecks.ensurePackPBOdisabled(caps);
/*  818 */     BufferChecks.checkDirect(row);
/*  819 */     BufferChecks.checkDirect(column);
/*  820 */     BufferChecks.checkDirect(span);
/*  821 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, ByteBuffer column, ShortBuffer span) {
/*  824 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  825 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  826 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  827 */     GLChecks.ensurePackPBOdisabled(caps);
/*  828 */     BufferChecks.checkDirect(row);
/*  829 */     BufferChecks.checkDirect(column);
/*  830 */     BufferChecks.checkDirect(span);
/*  831 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, DoubleBuffer column, ByteBuffer span) {
/*  834 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  835 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  836 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  837 */     GLChecks.ensurePackPBOdisabled(caps);
/*  838 */     BufferChecks.checkDirect(row);
/*  839 */     BufferChecks.checkDirect(column);
/*  840 */     BufferChecks.checkDirect(span);
/*  841 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, DoubleBuffer column, DoubleBuffer span) {
/*  844 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  845 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  846 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  847 */     GLChecks.ensurePackPBOdisabled(caps);
/*  848 */     BufferChecks.checkDirect(row);
/*  849 */     BufferChecks.checkDirect(column);
/*  850 */     BufferChecks.checkDirect(span);
/*  851 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, DoubleBuffer column, FloatBuffer span) {
/*  854 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  855 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  856 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  857 */     GLChecks.ensurePackPBOdisabled(caps);
/*  858 */     BufferChecks.checkDirect(row);
/*  859 */     BufferChecks.checkDirect(column);
/*  860 */     BufferChecks.checkDirect(span);
/*  861 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, DoubleBuffer column, IntBuffer span) {
/*  864 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  865 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  866 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  867 */     GLChecks.ensurePackPBOdisabled(caps);
/*  868 */     BufferChecks.checkDirect(row);
/*  869 */     BufferChecks.checkDirect(column);
/*  870 */     BufferChecks.checkDirect(span);
/*  871 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, DoubleBuffer column, ShortBuffer span) {
/*  874 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  875 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  876 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  877 */     GLChecks.ensurePackPBOdisabled(caps);
/*  878 */     BufferChecks.checkDirect(row);
/*  879 */     BufferChecks.checkDirect(column);
/*  880 */     BufferChecks.checkDirect(span);
/*  881 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, FloatBuffer column, ByteBuffer span) {
/*  884 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  885 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  886 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  887 */     GLChecks.ensurePackPBOdisabled(caps);
/*  888 */     BufferChecks.checkDirect(row);
/*  889 */     BufferChecks.checkDirect(column);
/*  890 */     BufferChecks.checkDirect(span);
/*  891 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, FloatBuffer column, DoubleBuffer span) {
/*  894 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  895 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  896 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  897 */     GLChecks.ensurePackPBOdisabled(caps);
/*  898 */     BufferChecks.checkDirect(row);
/*  899 */     BufferChecks.checkDirect(column);
/*  900 */     BufferChecks.checkDirect(span);
/*  901 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, FloatBuffer column, FloatBuffer span) {
/*  904 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  905 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  906 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  907 */     GLChecks.ensurePackPBOdisabled(caps);
/*  908 */     BufferChecks.checkDirect(row);
/*  909 */     BufferChecks.checkDirect(column);
/*  910 */     BufferChecks.checkDirect(span);
/*  911 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, FloatBuffer column, IntBuffer span) {
/*  914 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  915 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  916 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  917 */     GLChecks.ensurePackPBOdisabled(caps);
/*  918 */     BufferChecks.checkDirect(row);
/*  919 */     BufferChecks.checkDirect(column);
/*  920 */     BufferChecks.checkDirect(span);
/*  921 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, FloatBuffer column, ShortBuffer span) {
/*  924 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  925 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  926 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  927 */     GLChecks.ensurePackPBOdisabled(caps);
/*  928 */     BufferChecks.checkDirect(row);
/*  929 */     BufferChecks.checkDirect(column);
/*  930 */     BufferChecks.checkDirect(span);
/*  931 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, IntBuffer column, ByteBuffer span) {
/*  934 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  935 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  936 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  937 */     GLChecks.ensurePackPBOdisabled(caps);
/*  938 */     BufferChecks.checkDirect(row);
/*  939 */     BufferChecks.checkDirect(column);
/*  940 */     BufferChecks.checkDirect(span);
/*  941 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, IntBuffer column, DoubleBuffer span) {
/*  944 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  945 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  946 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  947 */     GLChecks.ensurePackPBOdisabled(caps);
/*  948 */     BufferChecks.checkDirect(row);
/*  949 */     BufferChecks.checkDirect(column);
/*  950 */     BufferChecks.checkDirect(span);
/*  951 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, IntBuffer column, FloatBuffer span) {
/*  954 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  955 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  956 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  957 */     GLChecks.ensurePackPBOdisabled(caps);
/*  958 */     BufferChecks.checkDirect(row);
/*  959 */     BufferChecks.checkDirect(column);
/*  960 */     BufferChecks.checkDirect(span);
/*  961 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, IntBuffer column, IntBuffer span) {
/*  964 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  965 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  966 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  967 */     GLChecks.ensurePackPBOdisabled(caps);
/*  968 */     BufferChecks.checkDirect(row);
/*  969 */     BufferChecks.checkDirect(column);
/*  970 */     BufferChecks.checkDirect(span);
/*  971 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, IntBuffer column, ShortBuffer span) {
/*  974 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  975 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  976 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  977 */     GLChecks.ensurePackPBOdisabled(caps);
/*  978 */     BufferChecks.checkDirect(row);
/*  979 */     BufferChecks.checkDirect(column);
/*  980 */     BufferChecks.checkDirect(span);
/*  981 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, ShortBuffer column, ByteBuffer span) {
/*  984 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  985 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  986 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  987 */     GLChecks.ensurePackPBOdisabled(caps);
/*  988 */     BufferChecks.checkDirect(row);
/*  989 */     BufferChecks.checkDirect(column);
/*  990 */     BufferChecks.checkDirect(span);
/*  991 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, ShortBuffer column, DoubleBuffer span) {
/*  994 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  995 */     long function_pointer = caps.glGetnSeparableFilterARB;
/*  996 */     BufferChecks.checkFunctionAddress(function_pointer);
/*  997 */     GLChecks.ensurePackPBOdisabled(caps);
/*  998 */     BufferChecks.checkDirect(row);
/*  999 */     BufferChecks.checkDirect(column);
/* 1000 */     BufferChecks.checkDirect(span);
/* 1001 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, ShortBuffer column, FloatBuffer span) {
/* 1004 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1005 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1006 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1007 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1008 */     BufferChecks.checkDirect(row);
/* 1009 */     BufferChecks.checkDirect(column);
/* 1010 */     BufferChecks.checkDirect(span);
/* 1011 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, ShortBuffer column, IntBuffer span) {
/* 1014 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1015 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1016 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1017 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1018 */     BufferChecks.checkDirect(row);
/* 1019 */     BufferChecks.checkDirect(column);
/* 1020 */     BufferChecks.checkDirect(span);
/* 1021 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, FloatBuffer row, ShortBuffer column, ShortBuffer span) {
/* 1024 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1025 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1026 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1027 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1028 */     BufferChecks.checkDirect(row);
/* 1029 */     BufferChecks.checkDirect(column);
/* 1030 */     BufferChecks.checkDirect(span);
/* 1031 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, ByteBuffer column, ByteBuffer span) {
/* 1034 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1035 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1036 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1037 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1038 */     BufferChecks.checkDirect(row);
/* 1039 */     BufferChecks.checkDirect(column);
/* 1040 */     BufferChecks.checkDirect(span);
/* 1041 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, ByteBuffer column, DoubleBuffer span) {
/* 1044 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1045 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1046 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1047 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1048 */     BufferChecks.checkDirect(row);
/* 1049 */     BufferChecks.checkDirect(column);
/* 1050 */     BufferChecks.checkDirect(span);
/* 1051 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, ByteBuffer column, FloatBuffer span) {
/* 1054 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1055 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1056 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1057 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1058 */     BufferChecks.checkDirect(row);
/* 1059 */     BufferChecks.checkDirect(column);
/* 1060 */     BufferChecks.checkDirect(span);
/* 1061 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, ByteBuffer column, IntBuffer span) {
/* 1064 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1065 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1066 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1067 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1068 */     BufferChecks.checkDirect(row);
/* 1069 */     BufferChecks.checkDirect(column);
/* 1070 */     BufferChecks.checkDirect(span);
/* 1071 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, ByteBuffer column, ShortBuffer span) {
/* 1074 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1075 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1076 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1077 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1078 */     BufferChecks.checkDirect(row);
/* 1079 */     BufferChecks.checkDirect(column);
/* 1080 */     BufferChecks.checkDirect(span);
/* 1081 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, DoubleBuffer column, ByteBuffer span) {
/* 1084 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1085 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1086 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1087 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1088 */     BufferChecks.checkDirect(row);
/* 1089 */     BufferChecks.checkDirect(column);
/* 1090 */     BufferChecks.checkDirect(span);
/* 1091 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, DoubleBuffer column, DoubleBuffer span) {
/* 1094 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1095 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1096 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1097 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1098 */     BufferChecks.checkDirect(row);
/* 1099 */     BufferChecks.checkDirect(column);
/* 1100 */     BufferChecks.checkDirect(span);
/* 1101 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, DoubleBuffer column, FloatBuffer span) {
/* 1104 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1105 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1106 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1107 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1108 */     BufferChecks.checkDirect(row);
/* 1109 */     BufferChecks.checkDirect(column);
/* 1110 */     BufferChecks.checkDirect(span);
/* 1111 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, DoubleBuffer column, IntBuffer span) {
/* 1114 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1115 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1116 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1117 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1118 */     BufferChecks.checkDirect(row);
/* 1119 */     BufferChecks.checkDirect(column);
/* 1120 */     BufferChecks.checkDirect(span);
/* 1121 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, DoubleBuffer column, ShortBuffer span) {
/* 1124 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1125 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1126 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1127 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1128 */     BufferChecks.checkDirect(row);
/* 1129 */     BufferChecks.checkDirect(column);
/* 1130 */     BufferChecks.checkDirect(span);
/* 1131 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, FloatBuffer column, ByteBuffer span) {
/* 1134 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1135 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1136 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1137 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1138 */     BufferChecks.checkDirect(row);
/* 1139 */     BufferChecks.checkDirect(column);
/* 1140 */     BufferChecks.checkDirect(span);
/* 1141 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, FloatBuffer column, DoubleBuffer span) {
/* 1144 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1145 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1146 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1147 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1148 */     BufferChecks.checkDirect(row);
/* 1149 */     BufferChecks.checkDirect(column);
/* 1150 */     BufferChecks.checkDirect(span);
/* 1151 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, FloatBuffer column, FloatBuffer span) {
/* 1154 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1155 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1156 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1157 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1158 */     BufferChecks.checkDirect(row);
/* 1159 */     BufferChecks.checkDirect(column);
/* 1160 */     BufferChecks.checkDirect(span);
/* 1161 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, FloatBuffer column, IntBuffer span) {
/* 1164 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1165 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1166 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1167 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1168 */     BufferChecks.checkDirect(row);
/* 1169 */     BufferChecks.checkDirect(column);
/* 1170 */     BufferChecks.checkDirect(span);
/* 1171 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, FloatBuffer column, ShortBuffer span) {
/* 1174 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1175 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1176 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1177 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1178 */     BufferChecks.checkDirect(row);
/* 1179 */     BufferChecks.checkDirect(column);
/* 1180 */     BufferChecks.checkDirect(span);
/* 1181 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, IntBuffer column, ByteBuffer span) {
/* 1184 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1185 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1186 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1187 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1188 */     BufferChecks.checkDirect(row);
/* 1189 */     BufferChecks.checkDirect(column);
/* 1190 */     BufferChecks.checkDirect(span);
/* 1191 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, IntBuffer column, DoubleBuffer span) {
/* 1194 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1195 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1196 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1197 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1198 */     BufferChecks.checkDirect(row);
/* 1199 */     BufferChecks.checkDirect(column);
/* 1200 */     BufferChecks.checkDirect(span);
/* 1201 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, IntBuffer column, FloatBuffer span) {
/* 1204 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1205 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1206 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1207 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1208 */     BufferChecks.checkDirect(row);
/* 1209 */     BufferChecks.checkDirect(column);
/* 1210 */     BufferChecks.checkDirect(span);
/* 1211 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, IntBuffer column, IntBuffer span) {
/* 1214 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1215 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1216 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1217 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1218 */     BufferChecks.checkDirect(row);
/* 1219 */     BufferChecks.checkDirect(column);
/* 1220 */     BufferChecks.checkDirect(span);
/* 1221 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, IntBuffer column, ShortBuffer span) {
/* 1224 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1225 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1226 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1227 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1228 */     BufferChecks.checkDirect(row);
/* 1229 */     BufferChecks.checkDirect(column);
/* 1230 */     BufferChecks.checkDirect(span);
/* 1231 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, ShortBuffer column, ByteBuffer span) {
/* 1234 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1235 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1236 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1237 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1238 */     BufferChecks.checkDirect(row);
/* 1239 */     BufferChecks.checkDirect(column);
/* 1240 */     BufferChecks.checkDirect(span);
/* 1241 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, ShortBuffer column, DoubleBuffer span) {
/* 1244 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1245 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1246 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1247 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1248 */     BufferChecks.checkDirect(row);
/* 1249 */     BufferChecks.checkDirect(column);
/* 1250 */     BufferChecks.checkDirect(span);
/* 1251 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, ShortBuffer column, FloatBuffer span) {
/* 1254 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1255 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1256 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1257 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1258 */     BufferChecks.checkDirect(row);
/* 1259 */     BufferChecks.checkDirect(column);
/* 1260 */     BufferChecks.checkDirect(span);
/* 1261 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, ShortBuffer column, IntBuffer span) {
/* 1264 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1265 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1266 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1267 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1268 */     BufferChecks.checkDirect(row);
/* 1269 */     BufferChecks.checkDirect(column);
/* 1270 */     BufferChecks.checkDirect(span);
/* 1271 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, IntBuffer row, ShortBuffer column, ShortBuffer span) {
/* 1274 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1275 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1276 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1277 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1278 */     BufferChecks.checkDirect(row);
/* 1279 */     BufferChecks.checkDirect(column);
/* 1280 */     BufferChecks.checkDirect(span);
/* 1281 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 2, MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, ByteBuffer column, ByteBuffer span) {
/* 1284 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1285 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1286 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1287 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1288 */     BufferChecks.checkDirect(row);
/* 1289 */     BufferChecks.checkDirect(column);
/* 1290 */     BufferChecks.checkDirect(span);
/* 1291 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, ByteBuffer column, DoubleBuffer span) {
/* 1294 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1295 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1296 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1297 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1298 */     BufferChecks.checkDirect(row);
/* 1299 */     BufferChecks.checkDirect(column);
/* 1300 */     BufferChecks.checkDirect(span);
/* 1301 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, ByteBuffer column, FloatBuffer span) {
/* 1304 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1305 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1306 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1307 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1308 */     BufferChecks.checkDirect(row);
/* 1309 */     BufferChecks.checkDirect(column);
/* 1310 */     BufferChecks.checkDirect(span);
/* 1311 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, ByteBuffer column, IntBuffer span) {
/* 1314 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1315 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1316 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1317 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1318 */     BufferChecks.checkDirect(row);
/* 1319 */     BufferChecks.checkDirect(column);
/* 1320 */     BufferChecks.checkDirect(span);
/* 1321 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, ByteBuffer column, ShortBuffer span) {
/* 1324 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1325 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1326 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1327 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1328 */     BufferChecks.checkDirect(row);
/* 1329 */     BufferChecks.checkDirect(column);
/* 1330 */     BufferChecks.checkDirect(span);
/* 1331 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining(), MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, DoubleBuffer column, ByteBuffer span) {
/* 1334 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1335 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1336 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1337 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1338 */     BufferChecks.checkDirect(row);
/* 1339 */     BufferChecks.checkDirect(column);
/* 1340 */     BufferChecks.checkDirect(span);
/* 1341 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, DoubleBuffer column, DoubleBuffer span) {
/* 1344 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1345 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1346 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1347 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1348 */     BufferChecks.checkDirect(row);
/* 1349 */     BufferChecks.checkDirect(column);
/* 1350 */     BufferChecks.checkDirect(span);
/* 1351 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, DoubleBuffer column, FloatBuffer span) {
/* 1354 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1355 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1356 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1357 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1358 */     BufferChecks.checkDirect(row);
/* 1359 */     BufferChecks.checkDirect(column);
/* 1360 */     BufferChecks.checkDirect(span);
/* 1361 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, DoubleBuffer column, IntBuffer span) {
/* 1364 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1365 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1366 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1367 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1368 */     BufferChecks.checkDirect(row);
/* 1369 */     BufferChecks.checkDirect(column);
/* 1370 */     BufferChecks.checkDirect(span);
/* 1371 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, DoubleBuffer column, ShortBuffer span) {
/* 1374 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1375 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1376 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1377 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1378 */     BufferChecks.checkDirect(row);
/* 1379 */     BufferChecks.checkDirect(column);
/* 1380 */     BufferChecks.checkDirect(span);
/* 1381 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining() << 3, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, FloatBuffer column, ByteBuffer span) {
/* 1384 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1385 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1386 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1387 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1388 */     BufferChecks.checkDirect(row);
/* 1389 */     BufferChecks.checkDirect(column);
/* 1390 */     BufferChecks.checkDirect(span);
/* 1391 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, FloatBuffer column, DoubleBuffer span) {
/* 1394 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1395 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1396 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1397 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1398 */     BufferChecks.checkDirect(row);
/* 1399 */     BufferChecks.checkDirect(column);
/* 1400 */     BufferChecks.checkDirect(span);
/* 1401 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, FloatBuffer column, FloatBuffer span) {
/* 1404 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1405 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1406 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1407 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1408 */     BufferChecks.checkDirect(row);
/* 1409 */     BufferChecks.checkDirect(column);
/* 1410 */     BufferChecks.checkDirect(span);
/* 1411 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, FloatBuffer column, IntBuffer span) {
/* 1414 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1415 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1416 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1417 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1418 */     BufferChecks.checkDirect(row);
/* 1419 */     BufferChecks.checkDirect(column);
/* 1420 */     BufferChecks.checkDirect(span);
/* 1421 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, FloatBuffer column, ShortBuffer span) {
/* 1424 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1425 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1426 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1427 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1428 */     BufferChecks.checkDirect(row);
/* 1429 */     BufferChecks.checkDirect(column);
/* 1430 */     BufferChecks.checkDirect(span);
/* 1431 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, IntBuffer column, ByteBuffer span) {
/* 1434 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1435 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1436 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1437 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1438 */     BufferChecks.checkDirect(row);
/* 1439 */     BufferChecks.checkDirect(column);
/* 1440 */     BufferChecks.checkDirect(span);
/* 1441 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, IntBuffer column, DoubleBuffer span) {
/* 1444 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1445 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1446 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1447 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1448 */     BufferChecks.checkDirect(row);
/* 1449 */     BufferChecks.checkDirect(column);
/* 1450 */     BufferChecks.checkDirect(span);
/* 1451 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, IntBuffer column, FloatBuffer span) {
/* 1454 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1455 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1456 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1457 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1458 */     BufferChecks.checkDirect(row);
/* 1459 */     BufferChecks.checkDirect(column);
/* 1460 */     BufferChecks.checkDirect(span);
/* 1461 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, IntBuffer column, IntBuffer span) {
/* 1464 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1465 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1466 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1467 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1468 */     BufferChecks.checkDirect(row);
/* 1469 */     BufferChecks.checkDirect(column);
/* 1470 */     BufferChecks.checkDirect(span);
/* 1471 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, IntBuffer column, ShortBuffer span) {
/* 1474 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1475 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1476 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1477 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1478 */     BufferChecks.checkDirect(row);
/* 1479 */     BufferChecks.checkDirect(column);
/* 1480 */     BufferChecks.checkDirect(span);
/* 1481 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining() << 2, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, ShortBuffer column, ByteBuffer span) {
/* 1484 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1485 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1486 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1487 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1488 */     BufferChecks.checkDirect(row);
/* 1489 */     BufferChecks.checkDirect(column);
/* 1490 */     BufferChecks.checkDirect(span);
/* 1491 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, ShortBuffer column, DoubleBuffer span) {
/* 1494 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1495 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1496 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1497 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1498 */     BufferChecks.checkDirect(row);
/* 1499 */     BufferChecks.checkDirect(column);
/* 1500 */     BufferChecks.checkDirect(span);
/* 1501 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, ShortBuffer column, FloatBuffer span) {
/* 1504 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1505 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1506 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1507 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1508 */     BufferChecks.checkDirect(row);
/* 1509 */     BufferChecks.checkDirect(column);
/* 1510 */     BufferChecks.checkDirect(span);
/* 1511 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, ShortBuffer column, IntBuffer span) {
/* 1514 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1515 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1516 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1517 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1518 */     BufferChecks.checkDirect(row);
/* 1519 */     BufferChecks.checkDirect(column);
/* 1520 */     BufferChecks.checkDirect(span);
/* 1521 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   public static void glGetnSeparableFilterARB(int target, int format, int type, ShortBuffer row, ShortBuffer column, ShortBuffer span) {
/* 1524 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1525 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1526 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1527 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1528 */     BufferChecks.checkDirect(row);
/* 1529 */     BufferChecks.checkDirect(column);
/* 1530 */     BufferChecks.checkDirect(span);
/* 1531 */     nglGetnSeparableFilterARB(target, format, type, row.remaining() << 1, MemoryUtil.getAddress(row), column.remaining() << 1, MemoryUtil.getAddress(column), MemoryUtil.getAddress(span), function_pointer);
/*      */   }
/*      */   static native void nglGetnSeparableFilterARB(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, int paramInt5, long paramLong2, long paramLong3, long paramLong4);
/*      */ 
/* 1535 */   public static void glGetnSeparableFilterARB(int target, int format, int type, int row_rowBufSize, long row_buffer_offset, int column_columnBufSize, long column_buffer_offset, long span_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1536 */     long function_pointer = caps.glGetnSeparableFilterARB;
/* 1537 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1538 */     GLChecks.ensurePackPBOenabled(caps);
/* 1539 */     nglGetnSeparableFilterARBBO(target, format, type, row_rowBufSize, row_buffer_offset, column_columnBufSize, column_buffer_offset, span_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglGetnSeparableFilterARBBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, int paramInt5, long paramLong2, long paramLong3, long paramLong4);
/*      */ 
/*      */   public static void glGetnHistogramARB(int target, boolean reset, int format, int type, ByteBuffer values) {
/* 1544 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1545 */     long function_pointer = caps.glGetnHistogramARB;
/* 1546 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1547 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1548 */     BufferChecks.checkDirect(values);
/* 1549 */     nglGetnHistogramARB(target, reset, format, type, values.remaining(), MemoryUtil.getAddress(values), function_pointer);
/*      */   }
/*      */   public static void glGetnHistogramARB(int target, boolean reset, int format, int type, DoubleBuffer values) {
/* 1552 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1553 */     long function_pointer = caps.glGetnHistogramARB;
/* 1554 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1555 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1556 */     BufferChecks.checkDirect(values);
/* 1557 */     nglGetnHistogramARB(target, reset, format, type, values.remaining() << 3, MemoryUtil.getAddress(values), function_pointer);
/*      */   }
/*      */   public static void glGetnHistogramARB(int target, boolean reset, int format, int type, FloatBuffer values) {
/* 1560 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1561 */     long function_pointer = caps.glGetnHistogramARB;
/* 1562 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1563 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1564 */     BufferChecks.checkDirect(values);
/* 1565 */     nglGetnHistogramARB(target, reset, format, type, values.remaining() << 2, MemoryUtil.getAddress(values), function_pointer);
/*      */   }
/*      */   public static void glGetnHistogramARB(int target, boolean reset, int format, int type, IntBuffer values) {
/* 1568 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1569 */     long function_pointer = caps.glGetnHistogramARB;
/* 1570 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1571 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1572 */     BufferChecks.checkDirect(values);
/* 1573 */     nglGetnHistogramARB(target, reset, format, type, values.remaining() << 2, MemoryUtil.getAddress(values), function_pointer);
/*      */   }
/*      */   public static void glGetnHistogramARB(int target, boolean reset, int format, int type, ShortBuffer values) {
/* 1576 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1577 */     long function_pointer = caps.glGetnHistogramARB;
/* 1578 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1579 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1580 */     BufferChecks.checkDirect(values);
/* 1581 */     nglGetnHistogramARB(target, reset, format, type, values.remaining() << 1, MemoryUtil.getAddress(values), function_pointer);
/*      */   }
/*      */   static native void nglGetnHistogramARB(int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*      */ 
/* 1585 */   public static void glGetnHistogramARB(int target, boolean reset, int format, int type, int values_bufSize, long values_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1586 */     long function_pointer = caps.glGetnHistogramARB;
/* 1587 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1588 */     GLChecks.ensurePackPBOenabled(caps);
/* 1589 */     nglGetnHistogramARBBO(target, reset, format, type, values_bufSize, values_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglGetnHistogramARBBO(int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetnMinmaxARB(int target, boolean reset, int format, int type, ByteBuffer values) {
/* 1594 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1595 */     long function_pointer = caps.glGetnMinmaxARB;
/* 1596 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1597 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1598 */     BufferChecks.checkDirect(values);
/* 1599 */     nglGetnMinmaxARB(target, reset, format, type, values.remaining(), MemoryUtil.getAddress(values), function_pointer);
/*      */   }
/*      */   public static void glGetnMinmaxARB(int target, boolean reset, int format, int type, DoubleBuffer values) {
/* 1602 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1603 */     long function_pointer = caps.glGetnMinmaxARB;
/* 1604 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1605 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1606 */     BufferChecks.checkDirect(values);
/* 1607 */     nglGetnMinmaxARB(target, reset, format, type, values.remaining() << 3, MemoryUtil.getAddress(values), function_pointer);
/*      */   }
/*      */   public static void glGetnMinmaxARB(int target, boolean reset, int format, int type, FloatBuffer values) {
/* 1610 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1611 */     long function_pointer = caps.glGetnMinmaxARB;
/* 1612 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1613 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1614 */     BufferChecks.checkDirect(values);
/* 1615 */     nglGetnMinmaxARB(target, reset, format, type, values.remaining() << 2, MemoryUtil.getAddress(values), function_pointer);
/*      */   }
/*      */   public static void glGetnMinmaxARB(int target, boolean reset, int format, int type, IntBuffer values) {
/* 1618 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1619 */     long function_pointer = caps.glGetnMinmaxARB;
/* 1620 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1621 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1622 */     BufferChecks.checkDirect(values);
/* 1623 */     nglGetnMinmaxARB(target, reset, format, type, values.remaining() << 2, MemoryUtil.getAddress(values), function_pointer);
/*      */   }
/*      */   public static void glGetnMinmaxARB(int target, boolean reset, int format, int type, ShortBuffer values) {
/* 1626 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1627 */     long function_pointer = caps.glGetnMinmaxARB;
/* 1628 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1629 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1630 */     BufferChecks.checkDirect(values);
/* 1631 */     nglGetnMinmaxARB(target, reset, format, type, values.remaining() << 1, MemoryUtil.getAddress(values), function_pointer);
/*      */   }
/*      */   static native void nglGetnMinmaxARB(int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*      */ 
/* 1635 */   public static void glGetnMinmaxARB(int target, boolean reset, int format, int type, int values_bufSize, long values_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1636 */     long function_pointer = caps.glGetnMinmaxARB;
/* 1637 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1638 */     GLChecks.ensurePackPBOenabled(caps);
/* 1639 */     nglGetnMinmaxARBBO(target, reset, format, type, values_bufSize, values_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglGetnMinmaxARBBO(int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetnCompressedTexImageARB(int target, int lod, ByteBuffer img) {
/* 1644 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1645 */     long function_pointer = caps.glGetnCompressedTexImageARB;
/* 1646 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1647 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1648 */     BufferChecks.checkDirect(img);
/* 1649 */     nglGetnCompressedTexImageARB(target, lod, img.remaining(), MemoryUtil.getAddress(img), function_pointer);
/*      */   }
/*      */   public static void glGetnCompressedTexImageARB(int target, int lod, IntBuffer img) {
/* 1652 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1653 */     long function_pointer = caps.glGetnCompressedTexImageARB;
/* 1654 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1655 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1656 */     BufferChecks.checkDirect(img);
/* 1657 */     nglGetnCompressedTexImageARB(target, lod, img.remaining() << 2, MemoryUtil.getAddress(img), function_pointer);
/*      */   }
/*      */   public static void glGetnCompressedTexImageARB(int target, int lod, ShortBuffer img) {
/* 1660 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1661 */     long function_pointer = caps.glGetnCompressedTexImageARB;
/* 1662 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1663 */     GLChecks.ensurePackPBOdisabled(caps);
/* 1664 */     BufferChecks.checkDirect(img);
/* 1665 */     nglGetnCompressedTexImageARB(target, lod, img.remaining() << 1, MemoryUtil.getAddress(img), function_pointer);
/*      */   }
/*      */   static native void nglGetnCompressedTexImageARB(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/* 1669 */   public static void glGetnCompressedTexImageARB(int target, int lod, int img_bufSize, long img_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 1670 */     long function_pointer = caps.glGetnCompressedTexImageARB;
/* 1671 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1672 */     GLChecks.ensurePackPBOenabled(caps);
/* 1673 */     nglGetnCompressedTexImageARBBO(target, lod, img_bufSize, img_buffer_offset, function_pointer); }
/*      */ 
/*      */   static native void nglGetnCompressedTexImageARBBO(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetnUniformfvARB(int program, int location, FloatBuffer params) {
/* 1678 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1679 */     long function_pointer = caps.glGetnUniformfvARB;
/* 1680 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1681 */     BufferChecks.checkDirect(params);
/* 1682 */     nglGetnUniformfvARB(program, location, params.remaining() << 2, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglGetnUniformfvARB(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetnUniformivARB(int program, int location, IntBuffer params) {
/* 1687 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1688 */     long function_pointer = caps.glGetnUniformivARB;
/* 1689 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1690 */     BufferChecks.checkDirect(params);
/* 1691 */     nglGetnUniformivARB(program, location, params.remaining() << 2, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglGetnUniformivARB(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetnUniformuivARB(int program, int location, IntBuffer params) {
/* 1696 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1697 */     long function_pointer = caps.glGetnUniformuivARB;
/* 1698 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1699 */     BufferChecks.checkDirect(params);
/* 1700 */     nglGetnUniformuivARB(program, location, params.remaining() << 2, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */   static native void nglGetnUniformuivARB(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ 
/*      */   public static void glGetnUniformdvARB(int program, int location, DoubleBuffer params) {
/* 1705 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 1706 */     long function_pointer = caps.glGetnUniformdvARB;
/* 1707 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 1708 */     BufferChecks.checkDirect(params);
/* 1709 */     nglGetnUniformdvARB(program, location, params.remaining() << 3, MemoryUtil.getAddress(params), function_pointer);
/*      */   }
/*      */ 
/*      */   static native void nglGetnUniformdvARB(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBRobustness
 * JD-Core Version:    0.6.2
 */