/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.IntBuffer;
/*   4:    */
/*  90:    */public final class ARBFramebufferObject
/*  91:    */{
/*  92:    */  public static final int GL_FRAMEBUFFER = 36160;
/*  93:    */  public static final int GL_READ_FRAMEBUFFER = 36008;
/*  94:    */  public static final int GL_DRAW_FRAMEBUFFER = 36009;
/*  95:    */  public static final int GL_RENDERBUFFER = 36161;
/*  96:    */  public static final int GL_STENCIL_INDEX1 = 36166;
/*  97:    */  public static final int GL_STENCIL_INDEX4 = 36167;
/*  98:    */  public static final int GL_STENCIL_INDEX8 = 36168;
/*  99:    */  public static final int GL_STENCIL_INDEX16 = 36169;
/* 100:    */  public static final int GL_RENDERBUFFER_WIDTH = 36162;
/* 101:    */  public static final int GL_RENDERBUFFER_HEIGHT = 36163;
/* 102:    */  public static final int GL_RENDERBUFFER_INTERNAL_FORMAT = 36164;
/* 103:    */  public static final int GL_RENDERBUFFER_RED_SIZE = 36176;
/* 104:    */  public static final int GL_RENDERBUFFER_GREEN_SIZE = 36177;
/* 105:    */  public static final int GL_RENDERBUFFER_BLUE_SIZE = 36178;
/* 106:    */  public static final int GL_RENDERBUFFER_ALPHA_SIZE = 36179;
/* 107:    */  public static final int GL_RENDERBUFFER_DEPTH_SIZE = 36180;
/* 108:    */  public static final int GL_RENDERBUFFER_STENCIL_SIZE = 36181;
/* 109:    */  public static final int GL_RENDERBUFFER_SAMPLES = 36011;
/* 110:    */  public static final int GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE = 36048;
/* 111:    */  public static final int GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME = 36049;
/* 112:    */  public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL = 36050;
/* 113:    */  public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE = 36051;
/* 114:    */  public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LAYER = 36052;
/* 115:    */  public static final int GL_FRAMEBUFFER_ATTACHMENT_COLOR_ENCODING = 33296;
/* 116:    */  public static final int GL_FRAMEBUFFER_ATTACHMENT_COMPONENT_TYPE = 33297;
/* 117:    */  public static final int GL_FRAMEBUFFER_ATTACHMENT_RED_SIZE = 33298;
/* 118:    */  public static final int GL_FRAMEBUFFER_ATTACHMENT_GREEN_SIZE = 33299;
/* 119:    */  public static final int GL_FRAMEBUFFER_ATTACHMENT_BLUE_SIZE = 33300;
/* 120:    */  public static final int GL_FRAMEBUFFER_ATTACHMENT_ALPHA_SIZE = 33301;
/* 121:    */  public static final int GL_FRAMEBUFFER_ATTACHMENT_DEPTH_SIZE = 33302;
/* 122:    */  public static final int GL_FRAMEBUFFER_ATTACHMENT_STENCIL_SIZE = 33303;
/* 123:    */  public static final int GL_SRGB = 35904;
/* 124:    */  public static final int GL_UNSIGNED_NORMALIZED = 35863;
/* 125:    */  public static final int GL_FRAMEBUFFER_DEFAULT = 33304;
/* 126:    */  public static final int GL_INDEX = 33314;
/* 127:    */  public static final int GL_COLOR_ATTACHMENT0 = 36064;
/* 128:    */  public static final int GL_COLOR_ATTACHMENT1 = 36065;
/* 129:    */  public static final int GL_COLOR_ATTACHMENT2 = 36066;
/* 130:    */  public static final int GL_COLOR_ATTACHMENT3 = 36067;
/* 131:    */  public static final int GL_COLOR_ATTACHMENT4 = 36068;
/* 132:    */  public static final int GL_COLOR_ATTACHMENT5 = 36069;
/* 133:    */  public static final int GL_COLOR_ATTACHMENT6 = 36070;
/* 134:    */  public static final int GL_COLOR_ATTACHMENT7 = 36071;
/* 135:    */  public static final int GL_COLOR_ATTACHMENT8 = 36072;
/* 136:    */  public static final int GL_COLOR_ATTACHMENT9 = 36073;
/* 137:    */  public static final int GL_COLOR_ATTACHMENT10 = 36074;
/* 138:    */  public static final int GL_COLOR_ATTACHMENT11 = 36075;
/* 139:    */  public static final int GL_COLOR_ATTACHMENT12 = 36076;
/* 140:    */  public static final int GL_COLOR_ATTACHMENT13 = 36077;
/* 141:    */  public static final int GL_COLOR_ATTACHMENT14 = 36078;
/* 142:    */  public static final int GL_COLOR_ATTACHMENT15 = 36079;
/* 143:    */  public static final int GL_DEPTH_ATTACHMENT = 36096;
/* 144:    */  public static final int GL_STENCIL_ATTACHMENT = 36128;
/* 145:    */  public static final int GL_DEPTH_STENCIL_ATTACHMENT = 33306;
/* 146:    */  public static final int GL_MAX_SAMPLES = 36183;
/* 147:    */  public static final int GL_FRAMEBUFFER_COMPLETE = 36053;
/* 148:    */  public static final int GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT = 36054;
/* 149:    */  public static final int GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT = 36055;
/* 150:    */  public static final int GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER = 36059;
/* 151:    */  public static final int GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER = 36060;
/* 152:    */  public static final int GL_FRAMEBUFFER_UNSUPPORTED = 36061;
/* 153:    */  public static final int GL_FRAMEBUFFER_INCOMPLETE_MULTISAMPLE = 36182;
/* 154:    */  public static final int GL_FRAMEBUFFER_UNDEFINED = 33305;
/* 155:    */  public static final int GL_FRAMEBUFFER_BINDING = 36006;
/* 156:    */  public static final int GL_DRAW_FRAMEBUFFER_BINDING = 36006;
/* 157:    */  public static final int GL_READ_FRAMEBUFFER_BINDING = 36010;
/* 158:    */  public static final int GL_RENDERBUFFER_BINDING = 36007;
/* 159:    */  public static final int GL_MAX_COLOR_ATTACHMENTS = 36063;
/* 160:    */  public static final int GL_MAX_RENDERBUFFER_SIZE = 34024;
/* 161:    */  public static final int GL_INVALID_FRAMEBUFFER_OPERATION = 1286;
/* 162:    */  public static final int GL_DEPTH_STENCIL = 34041;
/* 163:    */  public static final int GL_UNSIGNED_INT_24_8 = 34042;
/* 164:    */  public static final int GL_DEPTH24_STENCIL8 = 35056;
/* 165:    */  public static final int GL_TEXTURE_STENCIL_SIZE = 35057;
/* 166:    */  
/* 167:    */  public static boolean glIsRenderbuffer(int renderbuffer)
/* 168:    */  {
/* 169:169 */    return GL30.glIsRenderbuffer(renderbuffer);
/* 170:    */  }
/* 171:    */  
/* 172:    */  public static void glBindRenderbuffer(int target, int renderbuffer) {
/* 173:173 */    GL30.glBindRenderbuffer(target, renderbuffer);
/* 174:    */  }
/* 175:    */  
/* 176:    */  public static void glDeleteRenderbuffers(IntBuffer renderbuffers) {
/* 177:177 */    GL30.glDeleteRenderbuffers(renderbuffers);
/* 178:    */  }
/* 179:    */  
/* 180:    */  public static void glDeleteRenderbuffers(int renderbuffer)
/* 181:    */  {
/* 182:182 */    GL30.glDeleteRenderbuffers(renderbuffer);
/* 183:    */  }
/* 184:    */  
/* 185:    */  public static void glGenRenderbuffers(IntBuffer renderbuffers) {
/* 186:186 */    GL30.glGenRenderbuffers(renderbuffers);
/* 187:    */  }
/* 188:    */  
/* 189:    */  public static int glGenRenderbuffers()
/* 190:    */  {
/* 191:191 */    return GL30.glGenRenderbuffers();
/* 192:    */  }
/* 193:    */  
/* 194:    */  public static void glRenderbufferStorage(int target, int internalformat, int width, int height) {
/* 195:195 */    GL30.glRenderbufferStorage(target, internalformat, width, height);
/* 196:    */  }
/* 197:    */  
/* 198:    */  public static void glRenderbufferStorageMultisample(int target, int samples, int internalformat, int width, int height) {
/* 199:199 */    GL30.glRenderbufferStorageMultisample(target, samples, internalformat, width, height);
/* 200:    */  }
/* 201:    */  
/* 202:    */  public static void glGetRenderbufferParameter(int target, int pname, IntBuffer params) {
/* 203:203 */    GL30.glGetRenderbufferParameter(target, pname, params);
/* 204:    */  }
/* 205:    */  
/* 210:    */  @Deprecated
/* 211:    */  public static int glGetRenderbufferParameter(int target, int pname)
/* 212:    */  {
/* 213:213 */    return glGetRenderbufferParameteri(target, pname);
/* 214:    */  }
/* 215:    */  
/* 216:    */  public static int glGetRenderbufferParameteri(int target, int pname)
/* 217:    */  {
/* 218:218 */    return GL30.glGetRenderbufferParameteri(target, pname);
/* 219:    */  }
/* 220:    */  
/* 221:    */  public static boolean glIsFramebuffer(int framebuffer) {
/* 222:222 */    return GL30.glIsFramebuffer(framebuffer);
/* 223:    */  }
/* 224:    */  
/* 225:    */  public static void glBindFramebuffer(int target, int framebuffer) {
/* 226:226 */    GL30.glBindFramebuffer(target, framebuffer);
/* 227:    */  }
/* 228:    */  
/* 229:    */  public static void glDeleteFramebuffers(IntBuffer framebuffers) {
/* 230:230 */    GL30.glDeleteFramebuffers(framebuffers);
/* 231:    */  }
/* 232:    */  
/* 233:    */  public static void glDeleteFramebuffers(int framebuffer)
/* 234:    */  {
/* 235:235 */    GL30.glDeleteFramebuffers(framebuffer);
/* 236:    */  }
/* 237:    */  
/* 238:    */  public static void glGenFramebuffers(IntBuffer framebuffers) {
/* 239:239 */    GL30.glGenFramebuffers(framebuffers);
/* 240:    */  }
/* 241:    */  
/* 242:    */  public static int glGenFramebuffers()
/* 243:    */  {
/* 244:244 */    return GL30.glGenFramebuffers();
/* 245:    */  }
/* 246:    */  
/* 247:    */  public static int glCheckFramebufferStatus(int target) {
/* 248:248 */    return GL30.glCheckFramebufferStatus(target);
/* 249:    */  }
/* 250:    */  
/* 251:    */  public static void glFramebufferTexture1D(int target, int attachment, int textarget, int texture, int level) {
/* 252:252 */    GL30.glFramebufferTexture1D(target, attachment, textarget, texture, level);
/* 253:    */  }
/* 254:    */  
/* 255:    */  public static void glFramebufferTexture2D(int target, int attachment, int textarget, int texture, int level) {
/* 256:256 */    GL30.glFramebufferTexture2D(target, attachment, textarget, texture, level);
/* 257:    */  }
/* 258:    */  
/* 259:    */  public static void glFramebufferTexture3D(int target, int attachment, int textarget, int texture, int level, int layer) {
/* 260:260 */    GL30.glFramebufferTexture3D(target, attachment, textarget, texture, level, layer);
/* 261:    */  }
/* 262:    */  
/* 263:    */  public static void glFramebufferTextureLayer(int target, int attachment, int texture, int level, int layer) {
/* 264:264 */    GL30.glFramebufferTextureLayer(target, attachment, texture, level, layer);
/* 265:    */  }
/* 266:    */  
/* 267:    */  public static void glFramebufferRenderbuffer(int target, int attachment, int renderbuffertarget, int renderbuffer) {
/* 268:268 */    GL30.glFramebufferRenderbuffer(target, attachment, renderbuffertarget, renderbuffer);
/* 269:    */  }
/* 270:    */  
/* 271:    */  public static void glGetFramebufferAttachmentParameter(int target, int attachment, int pname, IntBuffer params) {
/* 272:272 */    GL30.glGetFramebufferAttachmentParameter(target, attachment, pname, params);
/* 273:    */  }
/* 274:    */  
/* 279:    */  @Deprecated
/* 280:    */  public static int glGetFramebufferAttachmentParameter(int target, int attachment, int pname)
/* 281:    */  {
/* 282:282 */    return GL30.glGetFramebufferAttachmentParameteri(target, attachment, pname);
/* 283:    */  }
/* 284:    */  
/* 285:    */  public static int glGetFramebufferAttachmentParameteri(int target, int attachment, int pname)
/* 286:    */  {
/* 287:287 */    return GL30.glGetFramebufferAttachmentParameteri(target, attachment, pname);
/* 288:    */  }
/* 289:    */  
/* 290:    */  public static void glBlitFramebuffer(int srcX0, int srcY0, int srcX1, int srcY1, int dstX0, int dstY0, int dstX1, int dstY1, int mask, int filter) {
/* 291:291 */    GL30.glBlitFramebuffer(srcX0, srcY0, srcX1, srcY1, dstX0, dstY0, dstX1, dstY1, mask, filter);
/* 292:    */  }
/* 293:    */  
/* 294:    */  public static void glGenerateMipmap(int target) {
/* 295:295 */    GL30.glGenerateMipmap(target);
/* 296:    */  }
/* 297:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBFramebufferObject
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */