/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.IntBuffer;
/*   4:    */import org.lwjgl.BufferChecks;
/*   5:    */import org.lwjgl.MemoryUtil;
/*   6:    */
/*  52:    */public final class EXTFramebufferObject
/*  53:    */{
/*  54:    */  public static final int GL_FRAMEBUFFER_EXT = 36160;
/*  55:    */  public static final int GL_RENDERBUFFER_EXT = 36161;
/*  56:    */  public static final int GL_STENCIL_INDEX1_EXT = 36166;
/*  57:    */  public static final int GL_STENCIL_INDEX4_EXT = 36167;
/*  58:    */  public static final int GL_STENCIL_INDEX8_EXT = 36168;
/*  59:    */  public static final int GL_STENCIL_INDEX16_EXT = 36169;
/*  60:    */  public static final int GL_RENDERBUFFER_WIDTH_EXT = 36162;
/*  61:    */  public static final int GL_RENDERBUFFER_HEIGHT_EXT = 36163;
/*  62:    */  public static final int GL_RENDERBUFFER_INTERNAL_FORMAT_EXT = 36164;
/*  63:    */  public static final int GL_RENDERBUFFER_RED_SIZE_EXT = 36176;
/*  64:    */  public static final int GL_RENDERBUFFER_GREEN_SIZE_EXT = 36177;
/*  65:    */  public static final int GL_RENDERBUFFER_BLUE_SIZE_EXT = 36178;
/*  66:    */  public static final int GL_RENDERBUFFER_ALPHA_SIZE_EXT = 36179;
/*  67:    */  public static final int GL_RENDERBUFFER_DEPTH_SIZE_EXT = 36180;
/*  68:    */  public static final int GL_RENDERBUFFER_STENCIL_SIZE_EXT = 36181;
/*  69:    */  public static final int GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE_EXT = 36048;
/*  70:    */  public static final int GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME_EXT = 36049;
/*  71:    */  public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL_EXT = 36050;
/*  72:    */  public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE_EXT = 36051;
/*  73:    */  public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_3D_ZOFFSET_EXT = 36052;
/*  74:    */  public static final int GL_COLOR_ATTACHMENT0_EXT = 36064;
/*  75:    */  public static final int GL_COLOR_ATTACHMENT1_EXT = 36065;
/*  76:    */  public static final int GL_COLOR_ATTACHMENT2_EXT = 36066;
/*  77:    */  public static final int GL_COLOR_ATTACHMENT3_EXT = 36067;
/*  78:    */  public static final int GL_COLOR_ATTACHMENT4_EXT = 36068;
/*  79:    */  public static final int GL_COLOR_ATTACHMENT5_EXT = 36069;
/*  80:    */  public static final int GL_COLOR_ATTACHMENT6_EXT = 36070;
/*  81:    */  public static final int GL_COLOR_ATTACHMENT7_EXT = 36071;
/*  82:    */  public static final int GL_COLOR_ATTACHMENT8_EXT = 36072;
/*  83:    */  public static final int GL_COLOR_ATTACHMENT9_EXT = 36073;
/*  84:    */  public static final int GL_COLOR_ATTACHMENT10_EXT = 36074;
/*  85:    */  public static final int GL_COLOR_ATTACHMENT11_EXT = 36075;
/*  86:    */  public static final int GL_COLOR_ATTACHMENT12_EXT = 36076;
/*  87:    */  public static final int GL_COLOR_ATTACHMENT13_EXT = 36077;
/*  88:    */  public static final int GL_COLOR_ATTACHMENT14_EXT = 36078;
/*  89:    */  public static final int GL_COLOR_ATTACHMENT15_EXT = 36079;
/*  90:    */  public static final int GL_DEPTH_ATTACHMENT_EXT = 36096;
/*  91:    */  public static final int GL_STENCIL_ATTACHMENT_EXT = 36128;
/*  92:    */  public static final int GL_FRAMEBUFFER_COMPLETE_EXT = 36053;
/*  93:    */  public static final int GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT_EXT = 36054;
/*  94:    */  public static final int GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT_EXT = 36055;
/*  95:    */  public static final int GL_FRAMEBUFFER_INCOMPLETE_DIMENSIONS_EXT = 36057;
/*  96:    */  public static final int GL_FRAMEBUFFER_INCOMPLETE_FORMATS_EXT = 36058;
/*  97:    */  public static final int GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER_EXT = 36059;
/*  98:    */  public static final int GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER_EXT = 36060;
/*  99:    */  public static final int GL_FRAMEBUFFER_UNSUPPORTED_EXT = 36061;
/* 100:    */  public static final int GL_FRAMEBUFFER_BINDING_EXT = 36006;
/* 101:    */  public static final int GL_RENDERBUFFER_BINDING_EXT = 36007;
/* 102:    */  public static final int GL_MAX_COLOR_ATTACHMENTS_EXT = 36063;
/* 103:    */  public static final int GL_MAX_RENDERBUFFER_SIZE_EXT = 34024;
/* 104:    */  public static final int GL_INVALID_FRAMEBUFFER_OPERATION_EXT = 1286;
/* 105:    */  
/* 106:    */  public static boolean glIsRenderbufferEXT(int renderbuffer)
/* 107:    */  {
/* 108:108 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 109:109 */    long function_pointer = caps.glIsRenderbufferEXT;
/* 110:110 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 111:111 */    boolean __result = nglIsRenderbufferEXT(renderbuffer, function_pointer);
/* 112:112 */    return __result;
/* 113:    */  }
/* 114:    */  
/* 115:    */  static native boolean nglIsRenderbufferEXT(int paramInt, long paramLong);
/* 116:    */  
/* 117:117 */  public static void glBindRenderbufferEXT(int target, int renderbuffer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 118:118 */    long function_pointer = caps.glBindRenderbufferEXT;
/* 119:119 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 120:120 */    nglBindRenderbufferEXT(target, renderbuffer, function_pointer);
/* 121:    */  }
/* 122:    */  
/* 123:    */  static native void nglBindRenderbufferEXT(int paramInt1, int paramInt2, long paramLong);
/* 124:    */  
/* 125:125 */  public static void glDeleteRenderbuffersEXT(IntBuffer renderbuffers) { ContextCapabilities caps = GLContext.getCapabilities();
/* 126:126 */    long function_pointer = caps.glDeleteRenderbuffersEXT;
/* 127:127 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 128:128 */    BufferChecks.checkDirect(renderbuffers);
/* 129:129 */    nglDeleteRenderbuffersEXT(renderbuffers.remaining(), MemoryUtil.getAddress(renderbuffers), function_pointer);
/* 130:    */  }
/* 131:    */  
/* 132:    */  static native void nglDeleteRenderbuffersEXT(int paramInt, long paramLong1, long paramLong2);
/* 133:    */  
/* 134:    */  public static void glDeleteRenderbuffersEXT(int renderbuffer) {
/* 135:135 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 136:136 */    long function_pointer = caps.glDeleteRenderbuffersEXT;
/* 137:137 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 138:138 */    nglDeleteRenderbuffersEXT(1, APIUtil.getInt(caps, renderbuffer), function_pointer);
/* 139:    */  }
/* 140:    */  
/* 141:    */  public static void glGenRenderbuffersEXT(IntBuffer renderbuffers) {
/* 142:142 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 143:143 */    long function_pointer = caps.glGenRenderbuffersEXT;
/* 144:144 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 145:145 */    BufferChecks.checkDirect(renderbuffers);
/* 146:146 */    nglGenRenderbuffersEXT(renderbuffers.remaining(), MemoryUtil.getAddress(renderbuffers), function_pointer);
/* 147:    */  }
/* 148:    */  
/* 149:    */  static native void nglGenRenderbuffersEXT(int paramInt, long paramLong1, long paramLong2);
/* 150:    */  
/* 151:    */  public static int glGenRenderbuffersEXT() {
/* 152:152 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 153:153 */    long function_pointer = caps.glGenRenderbuffersEXT;
/* 154:154 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 155:155 */    IntBuffer renderbuffers = APIUtil.getBufferInt(caps);
/* 156:156 */    nglGenRenderbuffersEXT(1, MemoryUtil.getAddress(renderbuffers), function_pointer);
/* 157:157 */    return renderbuffers.get(0);
/* 158:    */  }
/* 159:    */  
/* 160:    */  public static void glRenderbufferStorageEXT(int target, int internalformat, int width, int height) {
/* 161:161 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 162:162 */    long function_pointer = caps.glRenderbufferStorageEXT;
/* 163:163 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 164:164 */    nglRenderbufferStorageEXT(target, internalformat, width, height, function_pointer);
/* 165:    */  }
/* 166:    */  
/* 167:    */  static native void nglRenderbufferStorageEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/* 168:    */  
/* 169:169 */  public static void glGetRenderbufferParameterEXT(int target, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 170:170 */    long function_pointer = caps.glGetRenderbufferParameterivEXT;
/* 171:171 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 172:172 */    BufferChecks.checkBuffer(params, 4);
/* 173:173 */    nglGetRenderbufferParameterivEXT(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 174:    */  }
/* 175:    */  
/* 178:    */  static native void nglGetRenderbufferParameterivEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/* 179:    */  
/* 181:    */  @Deprecated
/* 182:    */  public static int glGetRenderbufferParameterEXT(int target, int pname)
/* 183:    */  {
/* 184:184 */    return glGetRenderbufferParameteriEXT(target, pname);
/* 185:    */  }
/* 186:    */  
/* 187:    */  public static int glGetRenderbufferParameteriEXT(int target, int pname)
/* 188:    */  {
/* 189:189 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 190:190 */    long function_pointer = caps.glGetRenderbufferParameterivEXT;
/* 191:191 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 192:192 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 193:193 */    nglGetRenderbufferParameterivEXT(target, pname, MemoryUtil.getAddress(params), function_pointer);
/* 194:194 */    return params.get(0);
/* 195:    */  }
/* 196:    */  
/* 197:    */  public static boolean glIsFramebufferEXT(int framebuffer) {
/* 198:198 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 199:199 */    long function_pointer = caps.glIsFramebufferEXT;
/* 200:200 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 201:201 */    boolean __result = nglIsFramebufferEXT(framebuffer, function_pointer);
/* 202:202 */    return __result;
/* 203:    */  }
/* 204:    */  
/* 205:    */  static native boolean nglIsFramebufferEXT(int paramInt, long paramLong);
/* 206:    */  
/* 207:207 */  public static void glBindFramebufferEXT(int target, int framebuffer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 208:208 */    long function_pointer = caps.glBindFramebufferEXT;
/* 209:209 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 210:210 */    nglBindFramebufferEXT(target, framebuffer, function_pointer);
/* 211:    */  }
/* 212:    */  
/* 213:    */  static native void nglBindFramebufferEXT(int paramInt1, int paramInt2, long paramLong);
/* 214:    */  
/* 215:215 */  public static void glDeleteFramebuffersEXT(IntBuffer framebuffers) { ContextCapabilities caps = GLContext.getCapabilities();
/* 216:216 */    long function_pointer = caps.glDeleteFramebuffersEXT;
/* 217:217 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 218:218 */    BufferChecks.checkDirect(framebuffers);
/* 219:219 */    nglDeleteFramebuffersEXT(framebuffers.remaining(), MemoryUtil.getAddress(framebuffers), function_pointer);
/* 220:    */  }
/* 221:    */  
/* 222:    */  static native void nglDeleteFramebuffersEXT(int paramInt, long paramLong1, long paramLong2);
/* 223:    */  
/* 224:    */  public static void glDeleteFramebuffersEXT(int framebuffer) {
/* 225:225 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 226:226 */    long function_pointer = caps.glDeleteFramebuffersEXT;
/* 227:227 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 228:228 */    nglDeleteFramebuffersEXT(1, APIUtil.getInt(caps, framebuffer), function_pointer);
/* 229:    */  }
/* 230:    */  
/* 231:    */  public static void glGenFramebuffersEXT(IntBuffer framebuffers) {
/* 232:232 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 233:233 */    long function_pointer = caps.glGenFramebuffersEXT;
/* 234:234 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 235:235 */    BufferChecks.checkDirect(framebuffers);
/* 236:236 */    nglGenFramebuffersEXT(framebuffers.remaining(), MemoryUtil.getAddress(framebuffers), function_pointer);
/* 237:    */  }
/* 238:    */  
/* 239:    */  static native void nglGenFramebuffersEXT(int paramInt, long paramLong1, long paramLong2);
/* 240:    */  
/* 241:    */  public static int glGenFramebuffersEXT() {
/* 242:242 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 243:243 */    long function_pointer = caps.glGenFramebuffersEXT;
/* 244:244 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 245:245 */    IntBuffer framebuffers = APIUtil.getBufferInt(caps);
/* 246:246 */    nglGenFramebuffersEXT(1, MemoryUtil.getAddress(framebuffers), function_pointer);
/* 247:247 */    return framebuffers.get(0);
/* 248:    */  }
/* 249:    */  
/* 250:    */  public static int glCheckFramebufferStatusEXT(int target) {
/* 251:251 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 252:252 */    long function_pointer = caps.glCheckFramebufferStatusEXT;
/* 253:253 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 254:254 */    int __result = nglCheckFramebufferStatusEXT(target, function_pointer);
/* 255:255 */    return __result;
/* 256:    */  }
/* 257:    */  
/* 258:    */  static native int nglCheckFramebufferStatusEXT(int paramInt, long paramLong);
/* 259:    */  
/* 260:260 */  public static void glFramebufferTexture1DEXT(int target, int attachment, int textarget, int texture, int level) { ContextCapabilities caps = GLContext.getCapabilities();
/* 261:261 */    long function_pointer = caps.glFramebufferTexture1DEXT;
/* 262:262 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 263:263 */    nglFramebufferTexture1DEXT(target, attachment, textarget, texture, level, function_pointer);
/* 264:    */  }
/* 265:    */  
/* 266:    */  static native void nglFramebufferTexture1DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/* 267:    */  
/* 268:268 */  public static void glFramebufferTexture2DEXT(int target, int attachment, int textarget, int texture, int level) { ContextCapabilities caps = GLContext.getCapabilities();
/* 269:269 */    long function_pointer = caps.glFramebufferTexture2DEXT;
/* 270:270 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 271:271 */    nglFramebufferTexture2DEXT(target, attachment, textarget, texture, level, function_pointer);
/* 272:    */  }
/* 273:    */  
/* 274:    */  static native void nglFramebufferTexture2DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/* 275:    */  
/* 276:276 */  public static void glFramebufferTexture3DEXT(int target, int attachment, int textarget, int texture, int level, int zoffset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 277:277 */    long function_pointer = caps.glFramebufferTexture3DEXT;
/* 278:278 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 279:279 */    nglFramebufferTexture3DEXT(target, attachment, textarget, texture, level, zoffset, function_pointer);
/* 280:    */  }
/* 281:    */  
/* 282:    */  static native void nglFramebufferTexture3DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/* 283:    */  
/* 284:284 */  public static void glFramebufferRenderbufferEXT(int target, int attachment, int renderbuffertarget, int renderbuffer) { ContextCapabilities caps = GLContext.getCapabilities();
/* 285:285 */    long function_pointer = caps.glFramebufferRenderbufferEXT;
/* 286:286 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 287:287 */    nglFramebufferRenderbufferEXT(target, attachment, renderbuffertarget, renderbuffer, function_pointer);
/* 288:    */  }
/* 289:    */  
/* 290:    */  static native void nglFramebufferRenderbufferEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/* 291:    */  
/* 292:292 */  public static void glGetFramebufferAttachmentParameterEXT(int target, int attachment, int pname, IntBuffer params) { ContextCapabilities caps = GLContext.getCapabilities();
/* 293:293 */    long function_pointer = caps.glGetFramebufferAttachmentParameterivEXT;
/* 294:294 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 295:295 */    BufferChecks.checkBuffer(params, 4);
/* 296:296 */    nglGetFramebufferAttachmentParameterivEXT(target, attachment, pname, MemoryUtil.getAddress(params), function_pointer);
/* 297:    */  }
/* 298:    */  
/* 301:    */  static native void nglGetFramebufferAttachmentParameterivEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/* 302:    */  
/* 304:    */  @Deprecated
/* 305:    */  public static int glGetFramebufferAttachmentParameterEXT(int target, int attachment, int pname)
/* 306:    */  {
/* 307:307 */    return glGetFramebufferAttachmentParameteriEXT(target, attachment, pname);
/* 308:    */  }
/* 309:    */  
/* 310:    */  public static int glGetFramebufferAttachmentParameteriEXT(int target, int attachment, int pname)
/* 311:    */  {
/* 312:312 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 313:313 */    long function_pointer = caps.glGetFramebufferAttachmentParameterivEXT;
/* 314:314 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 315:315 */    IntBuffer params = APIUtil.getBufferInt(caps);
/* 316:316 */    nglGetFramebufferAttachmentParameterivEXT(target, attachment, pname, MemoryUtil.getAddress(params), function_pointer);
/* 317:317 */    return params.get(0);
/* 318:    */  }
/* 319:    */  
/* 320:    */  public static void glGenerateMipmapEXT(int target) {
/* 321:321 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 322:322 */    long function_pointer = caps.glGenerateMipmapEXT;
/* 323:323 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 324:324 */    nglGenerateMipmapEXT(target, function_pointer);
/* 325:    */  }
/* 326:    */  
/* 327:    */  static native void nglGenerateMipmapEXT(int paramInt, long paramLong);
/* 328:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTFramebufferObject
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */