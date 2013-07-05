/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.IntBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ public final class EXTFramebufferObject
/*     */ {
/*     */   public static final int GL_FRAMEBUFFER_EXT = 36160;
/*     */   public static final int GL_RENDERBUFFER_EXT = 36161;
/*     */   public static final int GL_STENCIL_INDEX1_EXT = 36166;
/*     */   public static final int GL_STENCIL_INDEX4_EXT = 36167;
/*     */   public static final int GL_STENCIL_INDEX8_EXT = 36168;
/*     */   public static final int GL_STENCIL_INDEX16_EXT = 36169;
/*     */   public static final int GL_RENDERBUFFER_WIDTH_EXT = 36162;
/*     */   public static final int GL_RENDERBUFFER_HEIGHT_EXT = 36163;
/*     */   public static final int GL_RENDERBUFFER_INTERNAL_FORMAT_EXT = 36164;
/*     */   public static final int GL_RENDERBUFFER_RED_SIZE_EXT = 36176;
/*     */   public static final int GL_RENDERBUFFER_GREEN_SIZE_EXT = 36177;
/*     */   public static final int GL_RENDERBUFFER_BLUE_SIZE_EXT = 36178;
/*     */   public static final int GL_RENDERBUFFER_ALPHA_SIZE_EXT = 36179;
/*     */   public static final int GL_RENDERBUFFER_DEPTH_SIZE_EXT = 36180;
/*     */   public static final int GL_RENDERBUFFER_STENCIL_SIZE_EXT = 36181;
/*     */   public static final int GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE_EXT = 36048;
/*     */   public static final int GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME_EXT = 36049;
/*     */   public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL_EXT = 36050;
/*     */   public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE_EXT = 36051;
/*     */   public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_3D_ZOFFSET_EXT = 36052;
/*     */   public static final int GL_COLOR_ATTACHMENT0_EXT = 36064;
/*     */   public static final int GL_COLOR_ATTACHMENT1_EXT = 36065;
/*     */   public static final int GL_COLOR_ATTACHMENT2_EXT = 36066;
/*     */   public static final int GL_COLOR_ATTACHMENT3_EXT = 36067;
/*     */   public static final int GL_COLOR_ATTACHMENT4_EXT = 36068;
/*     */   public static final int GL_COLOR_ATTACHMENT5_EXT = 36069;
/*     */   public static final int GL_COLOR_ATTACHMENT6_EXT = 36070;
/*     */   public static final int GL_COLOR_ATTACHMENT7_EXT = 36071;
/*     */   public static final int GL_COLOR_ATTACHMENT8_EXT = 36072;
/*     */   public static final int GL_COLOR_ATTACHMENT9_EXT = 36073;
/*     */   public static final int GL_COLOR_ATTACHMENT10_EXT = 36074;
/*     */   public static final int GL_COLOR_ATTACHMENT11_EXT = 36075;
/*     */   public static final int GL_COLOR_ATTACHMENT12_EXT = 36076;
/*     */   public static final int GL_COLOR_ATTACHMENT13_EXT = 36077;
/*     */   public static final int GL_COLOR_ATTACHMENT14_EXT = 36078;
/*     */   public static final int GL_COLOR_ATTACHMENT15_EXT = 36079;
/*     */   public static final int GL_DEPTH_ATTACHMENT_EXT = 36096;
/*     */   public static final int GL_STENCIL_ATTACHMENT_EXT = 36128;
/*     */   public static final int GL_FRAMEBUFFER_COMPLETE_EXT = 36053;
/*     */   public static final int GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT_EXT = 36054;
/*     */   public static final int GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT_EXT = 36055;
/*     */   public static final int GL_FRAMEBUFFER_INCOMPLETE_DIMENSIONS_EXT = 36057;
/*     */   public static final int GL_FRAMEBUFFER_INCOMPLETE_FORMATS_EXT = 36058;
/*     */   public static final int GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER_EXT = 36059;
/*     */   public static final int GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER_EXT = 36060;
/*     */   public static final int GL_FRAMEBUFFER_UNSUPPORTED_EXT = 36061;
/*     */   public static final int GL_FRAMEBUFFER_BINDING_EXT = 36006;
/*     */   public static final int GL_RENDERBUFFER_BINDING_EXT = 36007;
/*     */   public static final int GL_MAX_COLOR_ATTACHMENTS_EXT = 36063;
/*     */   public static final int GL_MAX_RENDERBUFFER_SIZE_EXT = 34024;
/*     */   public static final int GL_INVALID_FRAMEBUFFER_OPERATION_EXT = 1286;
/*     */ 
/*     */   public static boolean glIsRenderbufferEXT(int renderbuffer)
/*     */   {
/* 108 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 109 */     long function_pointer = caps.glIsRenderbufferEXT;
/* 110 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 111 */     boolean __result = nglIsRenderbufferEXT(renderbuffer, function_pointer);
/* 112 */     return __result;
/*     */   }
/*     */   static native boolean nglIsRenderbufferEXT(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glBindRenderbufferEXT(int target, int renderbuffer) {
/* 117 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 118 */     long function_pointer = caps.glBindRenderbufferEXT;
/* 119 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 120 */     nglBindRenderbufferEXT(target, renderbuffer, function_pointer);
/*     */   }
/*     */   static native void nglBindRenderbufferEXT(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glDeleteRenderbuffersEXT(IntBuffer renderbuffers) {
/* 125 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 126 */     long function_pointer = caps.glDeleteRenderbuffersEXT;
/* 127 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 128 */     BufferChecks.checkDirect(renderbuffers);
/* 129 */     nglDeleteRenderbuffersEXT(renderbuffers.remaining(), MemoryUtil.getAddress(renderbuffers), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglDeleteRenderbuffersEXT(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glDeleteRenderbuffersEXT(int renderbuffer) {
/* 135 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 136 */     long function_pointer = caps.glDeleteRenderbuffersEXT;
/* 137 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 138 */     nglDeleteRenderbuffersEXT(1, APIUtil.getInt(caps, renderbuffer), function_pointer);
/*     */   }
/*     */ 
/*     */   public static void glGenRenderbuffersEXT(IntBuffer renderbuffers) {
/* 142 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 143 */     long function_pointer = caps.glGenRenderbuffersEXT;
/* 144 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 145 */     BufferChecks.checkDirect(renderbuffers);
/* 146 */     nglGenRenderbuffersEXT(renderbuffers.remaining(), MemoryUtil.getAddress(renderbuffers), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGenRenderbuffersEXT(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int glGenRenderbuffersEXT() {
/* 152 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 153 */     long function_pointer = caps.glGenRenderbuffersEXT;
/* 154 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 155 */     IntBuffer renderbuffers = APIUtil.getBufferInt(caps);
/* 156 */     nglGenRenderbuffersEXT(1, MemoryUtil.getAddress(renderbuffers), function_pointer);
/* 157 */     return renderbuffers.get(0);
/*     */   }
/*     */ 
/*     */   public static void glRenderbufferStorageEXT(int target, int internalformat, int width, int height) {
/* 161 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 162 */     long function_pointer = caps.glRenderbufferStorageEXT;
/* 163 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 164 */     nglRenderbufferStorageEXT(target, internalformat, width, height, function_pointer);
/*     */   }
/*     */   static native void nglRenderbufferStorageEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*     */ 
/*     */   public static void glGetRenderbufferParameterEXT(int target, int pname, IntBuffer params) {
/* 169 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 170 */     long function_pointer = caps.glGetRenderbufferParameterivEXT;
/* 171 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 172 */     BufferChecks.checkBuffer(params, 4);
/* 173 */     nglGetRenderbufferParameterivEXT(target, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetRenderbufferParameterivEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*     */ 
/*     */   @Deprecated
/*     */   public static int glGetRenderbufferParameterEXT(int target, int pname)
/*     */   {
/* 184 */     return glGetRenderbufferParameteriEXT(target, pname);
/*     */   }
/*     */ 
/*     */   public static int glGetRenderbufferParameteriEXT(int target, int pname)
/*     */   {
/* 189 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 190 */     long function_pointer = caps.glGetRenderbufferParameterivEXT;
/* 191 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 192 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 193 */     nglGetRenderbufferParameterivEXT(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 194 */     return params.get(0);
/*     */   }
/*     */ 
/*     */   public static boolean glIsFramebufferEXT(int framebuffer) {
/* 198 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 199 */     long function_pointer = caps.glIsFramebufferEXT;
/* 200 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 201 */     boolean __result = nglIsFramebufferEXT(framebuffer, function_pointer);
/* 202 */     return __result;
/*     */   }
/*     */   static native boolean nglIsFramebufferEXT(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glBindFramebufferEXT(int target, int framebuffer) {
/* 207 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 208 */     long function_pointer = caps.glBindFramebufferEXT;
/* 209 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 210 */     nglBindFramebufferEXT(target, framebuffer, function_pointer);
/*     */   }
/*     */   static native void nglBindFramebufferEXT(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void glDeleteFramebuffersEXT(IntBuffer framebuffers) {
/* 215 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 216 */     long function_pointer = caps.glDeleteFramebuffersEXT;
/* 217 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 218 */     BufferChecks.checkDirect(framebuffers);
/* 219 */     nglDeleteFramebuffersEXT(framebuffers.remaining(), MemoryUtil.getAddress(framebuffers), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglDeleteFramebuffersEXT(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static void glDeleteFramebuffersEXT(int framebuffer) {
/* 225 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 226 */     long function_pointer = caps.glDeleteFramebuffersEXT;
/* 227 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 228 */     nglDeleteFramebuffersEXT(1, APIUtil.getInt(caps, framebuffer), function_pointer);
/*     */   }
/*     */ 
/*     */   public static void glGenFramebuffersEXT(IntBuffer framebuffers) {
/* 232 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 233 */     long function_pointer = caps.glGenFramebuffersEXT;
/* 234 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 235 */     BufferChecks.checkDirect(framebuffers);
/* 236 */     nglGenFramebuffersEXT(framebuffers.remaining(), MemoryUtil.getAddress(framebuffers), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGenFramebuffersEXT(int paramInt, long paramLong1, long paramLong2);
/*     */ 
/*     */   public static int glGenFramebuffersEXT() {
/* 242 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 243 */     long function_pointer = caps.glGenFramebuffersEXT;
/* 244 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 245 */     IntBuffer framebuffers = APIUtil.getBufferInt(caps);
/* 246 */     nglGenFramebuffersEXT(1, MemoryUtil.getAddress(framebuffers), function_pointer);
/* 247 */     return framebuffers.get(0);
/*     */   }
/*     */ 
/*     */   public static int glCheckFramebufferStatusEXT(int target) {
/* 251 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 252 */     long function_pointer = caps.glCheckFramebufferStatusEXT;
/* 253 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 254 */     int __result = nglCheckFramebufferStatusEXT(target, function_pointer);
/* 255 */     return __result;
/*     */   }
/*     */   static native int nglCheckFramebufferStatusEXT(int paramInt, long paramLong);
/*     */ 
/*     */   public static void glFramebufferTexture1DEXT(int target, int attachment, int textarget, int texture, int level) {
/* 260 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 261 */     long function_pointer = caps.glFramebufferTexture1DEXT;
/* 262 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 263 */     nglFramebufferTexture1DEXT(target, attachment, textarget, texture, level, function_pointer);
/*     */   }
/*     */   static native void nglFramebufferTexture1DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*     */ 
/*     */   public static void glFramebufferTexture2DEXT(int target, int attachment, int textarget, int texture, int level) {
/* 268 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 269 */     long function_pointer = caps.glFramebufferTexture2DEXT;
/* 270 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 271 */     nglFramebufferTexture2DEXT(target, attachment, textarget, texture, level, function_pointer);
/*     */   }
/*     */   static native void nglFramebufferTexture2DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*     */ 
/*     */   public static void glFramebufferTexture3DEXT(int target, int attachment, int textarget, int texture, int level, int zoffset) {
/* 276 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 277 */     long function_pointer = caps.glFramebufferTexture3DEXT;
/* 278 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 279 */     nglFramebufferTexture3DEXT(target, attachment, textarget, texture, level, zoffset, function_pointer);
/*     */   }
/*     */   static native void nglFramebufferTexture3DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/*     */ 
/*     */   public static void glFramebufferRenderbufferEXT(int target, int attachment, int renderbuffertarget, int renderbuffer) {
/* 284 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 285 */     long function_pointer = caps.glFramebufferRenderbufferEXT;
/* 286 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 287 */     nglFramebufferRenderbufferEXT(target, attachment, renderbuffertarget, renderbuffer, function_pointer);
/*     */   }
/*     */   static native void nglFramebufferRenderbufferEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*     */ 
/*     */   public static void glGetFramebufferAttachmentParameterEXT(int target, int attachment, int pname, IntBuffer params) {
/* 292 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 293 */     long function_pointer = caps.glGetFramebufferAttachmentParameterivEXT;
/* 294 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 295 */     BufferChecks.checkBuffer(params, 4);
/* 296 */     nglGetFramebufferAttachmentParameterivEXT(target, attachment, pname, MemoryUtil.getAddress(params), function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGetFramebufferAttachmentParameterivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*     */ 
/*     */   @Deprecated
/*     */   public static int glGetFramebufferAttachmentParameterEXT(int target, int attachment, int pname)
/*     */   {
/* 307 */     return glGetFramebufferAttachmentParameteriEXT(target, attachment, pname);
/*     */   }
/*     */ 
/*     */   public static int glGetFramebufferAttachmentParameteriEXT(int target, int attachment, int pname)
/*     */   {
/* 312 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 313 */     long function_pointer = caps.glGetFramebufferAttachmentParameterivEXT;
/* 314 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 315 */     IntBuffer params = APIUtil.getBufferInt(caps);
/* 316 */     nglGetFramebufferAttachmentParameterivEXT(target, attachment, pname, MemoryUtil.getAddress(params), function_pointer);
/* 317 */     return params.get(0);
/*     */   }
/*     */ 
/*     */   public static void glGenerateMipmapEXT(int target) {
/* 321 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 322 */     long function_pointer = caps.glGenerateMipmapEXT;
/* 323 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 324 */     nglGenerateMipmapEXT(target, function_pointer);
/*     */   }
/*     */ 
/*     */   static native void nglGenerateMipmapEXT(int paramInt, long paramLong);
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTFramebufferObject
 * JD-Core Version:    0.6.2
 */