/*   1:    */package org.newdawn.slick.opengl.renderer;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.DoubleBuffer;
/*   5:    */import java.nio.FloatBuffer;
/*   6:    */import java.nio.IntBuffer;
/*   7:    */import org.lwjgl.opengl.ContextCapabilities;
/*   8:    */import org.lwjgl.opengl.EXTSecondaryColor;
/*   9:    */import org.lwjgl.opengl.GL11;
/*  10:    */import org.lwjgl.opengl.GLContext;
/*  11:    */
/*  18:    */public class ImmediateModeOGLRenderer
/*  19:    */  implements SGL
/*  20:    */{
/*  21:    */  private int width;
/*  22:    */  private int height;
/*  23: 23 */  private float[] current = { 1.0F, 1.0F, 1.0F, 1.0F };
/*  24:    */  
/*  25: 25 */  protected float alphaScale = 1.0F;
/*  26:    */  
/*  29:    */  public void initDisplay(int width, int height)
/*  30:    */  {
/*  31: 31 */    this.width = width;
/*  32: 32 */    this.height = height;
/*  33:    */    
/*  34: 34 */    String extensions = GL11.glGetString(7939);
/*  35:    */    
/*  36: 36 */    GL11.glEnable(3553);
/*  37: 37 */    GL11.glShadeModel(7425);
/*  38: 38 */    GL11.glDisable(2929);
/*  39: 39 */    GL11.glDisable(2896);
/*  40:    */    
/*  41: 41 */    GL11.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
/*  42: 42 */    GL11.glClearDepth(1.0D);
/*  43:    */    
/*  44: 44 */    GL11.glEnable(3042);
/*  45: 45 */    GL11.glBlendFunc(770, 771);
/*  46:    */    
/*  47: 47 */    GL11.glViewport(0, 0, width, height);
/*  48: 48 */    GL11.glMatrixMode(5888);
/*  49:    */  }
/*  50:    */  
/*  53:    */  public void enterOrtho(int xsize, int ysize)
/*  54:    */  {
/*  55: 55 */    GL11.glMatrixMode(5889);
/*  56: 56 */    GL11.glLoadIdentity();
/*  57: 57 */    GL11.glOrtho(0.0D, this.width, this.height, 0.0D, 1.0D, -1.0D);
/*  58: 58 */    GL11.glMatrixMode(5888);
/*  59:    */    
/*  60: 60 */    GL11.glTranslatef((this.width - xsize) / 2, (this.height - ysize) / 2, 0.0F);
/*  61:    */  }
/*  62:    */  
/*  66:    */  public void glBegin(int geomType)
/*  67:    */  {
/*  68: 68 */    GL11.glBegin(geomType);
/*  69:    */  }
/*  70:    */  
/*  73:    */  public void glBindTexture(int target, int id)
/*  74:    */  {
/*  75: 75 */    GL11.glBindTexture(target, id);
/*  76:    */  }
/*  77:    */  
/*  80:    */  public void glBlendFunc(int src, int dest)
/*  81:    */  {
/*  82: 82 */    GL11.glBlendFunc(src, dest);
/*  83:    */  }
/*  84:    */  
/*  87:    */  public void glCallList(int id)
/*  88:    */  {
/*  89: 89 */    GL11.glCallList(id);
/*  90:    */  }
/*  91:    */  
/*  94:    */  public void glClear(int value)
/*  95:    */  {
/*  96: 96 */    GL11.glClear(value);
/*  97:    */  }
/*  98:    */  
/* 101:    */  public void glClearColor(float red, float green, float blue, float alpha)
/* 102:    */  {
/* 103:103 */    GL11.glClearColor(red, green, blue, alpha);
/* 104:    */  }
/* 105:    */  
/* 108:    */  public void glClipPlane(int plane, DoubleBuffer buffer)
/* 109:    */  {
/* 110:110 */    GL11.glClipPlane(plane, buffer);
/* 111:    */  }
/* 112:    */  
/* 115:    */  public void glColor4f(float r, float g, float b, float a)
/* 116:    */  {
/* 117:117 */    a *= this.alphaScale;
/* 118:    */    
/* 119:119 */    this.current[0] = r;
/* 120:120 */    this.current[1] = g;
/* 121:121 */    this.current[2] = b;
/* 122:122 */    this.current[3] = a;
/* 123:    */    
/* 124:124 */    GL11.glColor4f(r, g, b, a);
/* 125:    */  }
/* 126:    */  
/* 129:    */  public void glColorMask(boolean red, boolean green, boolean blue, boolean alpha)
/* 130:    */  {
/* 131:131 */    GL11.glColorMask(red, green, blue, alpha);
/* 132:    */  }
/* 133:    */  
/* 136:    */  public void glCopyTexImage2D(int target, int level, int internalFormat, int x, int y, int width, int height, int border)
/* 137:    */  {
/* 138:138 */    GL11.glCopyTexImage2D(target, level, internalFormat, x, y, width, height, border);
/* 139:    */  }
/* 140:    */  
/* 143:    */  public void glDeleteTextures(IntBuffer buffer)
/* 144:    */  {
/* 145:145 */    GL11.glDeleteTextures(buffer);
/* 146:    */  }
/* 147:    */  
/* 150:    */  public void glDisable(int item)
/* 151:    */  {
/* 152:152 */    GL11.glDisable(item);
/* 153:    */  }
/* 154:    */  
/* 157:    */  public void glEnable(int item)
/* 158:    */  {
/* 159:159 */    GL11.glEnable(item);
/* 160:    */  }
/* 161:    */  
/* 166:    */  public void glEnd() {}
/* 167:    */  
/* 172:    */  public void glEndList() {}
/* 173:    */  
/* 178:    */  public int glGenLists(int count)
/* 179:    */  {
/* 180:180 */    return GL11.glGenLists(count);
/* 181:    */  }
/* 182:    */  
/* 185:    */  public void glGetFloat(int id, FloatBuffer ret)
/* 186:    */  {
/* 187:187 */    GL11.glGetFloat(id, ret);
/* 188:    */  }
/* 189:    */  
/* 192:    */  public void glGetInteger(int id, IntBuffer ret)
/* 193:    */  {
/* 194:194 */    GL11.glGetInteger(id, ret);
/* 195:    */  }
/* 196:    */  
/* 199:    */  public void glGetTexImage(int target, int level, int format, int type, ByteBuffer pixels)
/* 200:    */  {
/* 201:201 */    GL11.glGetTexImage(target, level, format, type, pixels);
/* 202:    */  }
/* 203:    */  
/* 206:    */  public void glLineWidth(float width)
/* 207:    */  {
/* 208:208 */    GL11.glLineWidth(width);
/* 209:    */  }
/* 210:    */  
/* 215:    */  public void glLoadIdentity() {}
/* 216:    */  
/* 220:    */  public void glNewList(int id, int option)
/* 221:    */  {
/* 222:222 */    GL11.glNewList(id, option);
/* 223:    */  }
/* 224:    */  
/* 227:    */  public void glPointSize(float size)
/* 228:    */  {
/* 229:229 */    GL11.glPointSize(size);
/* 230:    */  }
/* 231:    */  
/* 236:    */  public void glPopMatrix() {}
/* 237:    */  
/* 242:    */  public void glPushMatrix() {}
/* 243:    */  
/* 248:    */  public void glReadPixels(int x, int y, int width, int height, int format, int type, ByteBuffer pixels)
/* 249:    */  {
/* 250:250 */    GL11.glReadPixels(x, y, width, height, format, type, pixels);
/* 251:    */  }
/* 252:    */  
/* 255:    */  public void glRotatef(float angle, float x, float y, float z)
/* 256:    */  {
/* 257:257 */    GL11.glRotatef(angle, x, y, z);
/* 258:    */  }
/* 259:    */  
/* 262:    */  public void glScalef(float x, float y, float z)
/* 263:    */  {
/* 264:264 */    GL11.glScalef(x, y, z);
/* 265:    */  }
/* 266:    */  
/* 269:    */  public void glScissor(int x, int y, int width, int height)
/* 270:    */  {
/* 271:271 */    GL11.glScissor(x, y, width, height);
/* 272:    */  }
/* 273:    */  
/* 276:    */  public void glTexCoord2f(float u, float v)
/* 277:    */  {
/* 278:278 */    GL11.glTexCoord2f(u, v);
/* 279:    */  }
/* 280:    */  
/* 283:    */  public void glTexEnvi(int target, int mode, int value)
/* 284:    */  {
/* 285:285 */    GL11.glTexEnvi(target, mode, value);
/* 286:    */  }
/* 287:    */  
/* 290:    */  public void glTranslatef(float x, float y, float z)
/* 291:    */  {
/* 292:292 */    GL11.glTranslatef(x, y, z);
/* 293:    */  }
/* 294:    */  
/* 297:    */  public void glVertex2f(float x, float y)
/* 298:    */  {
/* 299:299 */    GL11.glVertex2f(x, y);
/* 300:    */  }
/* 301:    */  
/* 304:    */  public void glVertex3f(float x, float y, float z)
/* 305:    */  {
/* 306:306 */    GL11.glVertex3f(x, y, z);
/* 307:    */  }
/* 308:    */  
/* 312:    */  public void flush() {}
/* 313:    */  
/* 317:    */  public void glTexParameteri(int target, int param, int value)
/* 318:    */  {
/* 319:319 */    GL11.glTexParameteri(target, param, value);
/* 320:    */  }
/* 321:    */  
/* 324:    */  public float[] getCurrentColor()
/* 325:    */  {
/* 326:326 */    return this.current;
/* 327:    */  }
/* 328:    */  
/* 331:    */  public void glDeleteLists(int list, int count)
/* 332:    */  {
/* 333:333 */    GL11.glDeleteLists(list, count);
/* 334:    */  }
/* 335:    */  
/* 338:    */  public void glClearDepth(float value)
/* 339:    */  {
/* 340:340 */    GL11.glClearDepth(value);
/* 341:    */  }
/* 342:    */  
/* 345:    */  public void glDepthFunc(int func)
/* 346:    */  {
/* 347:347 */    GL11.glDepthFunc(func);
/* 348:    */  }
/* 349:    */  
/* 352:    */  public void glDepthMask(boolean mask)
/* 353:    */  {
/* 354:354 */    GL11.glDepthMask(mask);
/* 355:    */  }
/* 356:    */  
/* 359:    */  public void setGlobalAlphaScale(float alphaScale)
/* 360:    */  {
/* 361:361 */    this.alphaScale = alphaScale;
/* 362:    */  }
/* 363:    */  
/* 366:    */  public void glLoadMatrix(FloatBuffer buffer)
/* 367:    */  {
/* 368:368 */    GL11.glLoadMatrix(buffer);
/* 369:    */  }
/* 370:    */  
/* 374:    */  public void glGenTextures(IntBuffer ids)
/* 375:    */  {
/* 376:376 */    GL11.glGenTextures(ids);
/* 377:    */  }
/* 378:    */  
/* 382:    */  public void glGetError()
/* 383:    */  {
/* 384:384 */    GL11.glGetError();
/* 385:    */  }
/* 386:    */  
/* 392:    */  public void glTexImage2D(int target, int i, int dstPixelFormat, int width, int height, int j, int srcPixelFormat, int glUnsignedByte, ByteBuffer textureBuffer)
/* 393:    */  {
/* 394:394 */    GL11.glTexImage2D(target, i, dstPixelFormat, width, height, j, srcPixelFormat, glUnsignedByte, textureBuffer);
/* 395:    */  }
/* 396:    */  
/* 398:    */  public void glTexSubImage2D(int glTexture2d, int i, int pageX, int pageY, int width, int height, int glBgra, int glUnsignedByte, ByteBuffer scratchByteBuffer)
/* 399:    */  {
/* 400:400 */    GL11.glTexSubImage2D(glTexture2d, i, pageX, pageY, width, height, glBgra, glUnsignedByte, scratchByteBuffer);
/* 401:    */  }
/* 402:    */  
/* 406:    */  public boolean canTextureMirrorClamp()
/* 407:    */  {
/* 408:408 */    return GLContext.getCapabilities().GL_EXT_texture_mirror_clamp;
/* 409:    */  }
/* 410:    */  
/* 414:    */  public boolean canSecondaryColor()
/* 415:    */  {
/* 416:416 */    return GLContext.getCapabilities().GL_EXT_secondary_color;
/* 417:    */  }
/* 418:    */  
/* 422:    */  public void glSecondaryColor3ubEXT(byte b, byte c, byte d)
/* 423:    */  {
/* 424:424 */    EXTSecondaryColor.glSecondaryColor3ubEXT(b, c, d);
/* 425:    */  }
/* 426:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.opengl.renderer.ImmediateModeOGLRenderer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */