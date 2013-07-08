/*   1:    */package org.newdawn.slick.opengl.renderer;
/*   2:    */
/*   3:    */import java.nio.DoubleBuffer;
/*   4:    */import java.nio.FloatBuffer;
/*   5:    */import org.lwjgl.BufferUtils;
/*   6:    */import org.lwjgl.opengl.GL11;
/*   7:    */
/*  18:    */public class VAOGLRenderer
/*  19:    */  extends ImmediateModeOGLRenderer
/*  20:    */{
/*  21:    */  private static final int TOLERANCE = 20;
/*  22:    */  public static final int NONE = -1;
/*  23:    */  public static final int MAX_VERTS = 5000;
/*  24: 24 */  private int currentType = -1;
/*  25:    */  
/*  26: 26 */  private float[] color = { 1.0F, 1.0F, 1.0F, 1.0F };
/*  27:    */  
/*  28: 28 */  private float[] tex = { 0.0F, 0.0F };
/*  29:    */  
/*  31:    */  private int vertIndex;
/*  32:    */  
/*  33: 33 */  private float[] verts = new float[15000];
/*  34:    */  
/*  35: 35 */  private float[] cols = new float[20000];
/*  36:    */  
/*  37: 37 */  private float[] texs = new float[15000];
/*  38:    */  
/*  40: 40 */  private FloatBuffer vertices = BufferUtils.createFloatBuffer(15000);
/*  41:    */  
/*  42: 42 */  private FloatBuffer colors = BufferUtils.createFloatBuffer(20000);
/*  43:    */  
/*  44: 44 */  private FloatBuffer textures = BufferUtils.createFloatBuffer(10000);
/*  45:    */  
/*  47: 47 */  private int listMode = 0;
/*  48:    */  
/*  51:    */  public void initDisplay(int width, int height)
/*  52:    */  {
/*  53: 53 */    super.initDisplay(width, height);
/*  54:    */    
/*  55: 55 */    startBuffer();
/*  56: 56 */    GL11.glEnableClientState(32884);
/*  57: 57 */    GL11.glEnableClientState(32888);
/*  58: 58 */    GL11.glEnableClientState(32886);
/*  59:    */  }
/*  60:    */  
/*  63:    */  private void startBuffer()
/*  64:    */  {
/*  65: 65 */    this.vertIndex = 0;
/*  66:    */  }
/*  67:    */  
/*  70:    */  private void flushBuffer()
/*  71:    */  {
/*  72: 72 */    if (this.vertIndex == 0) {
/*  73: 73 */      return;
/*  74:    */    }
/*  75: 75 */    if (this.currentType == -1) {
/*  76: 76 */      return;
/*  77:    */    }
/*  78:    */    
/*  79: 79 */    if (this.vertIndex < 20) {
/*  80: 80 */      GL11.glBegin(this.currentType);
/*  81: 81 */      for (int i = 0; i < this.vertIndex; i++) {
/*  82: 82 */        GL11.glColor4f(this.cols[(i * 4 + 0)], this.cols[(i * 4 + 1)], this.cols[(i * 4 + 2)], this.cols[(i * 4 + 3)]);
/*  83: 83 */        GL11.glTexCoord2f(this.texs[(i * 2 + 0)], this.texs[(i * 2 + 1)]);
/*  84: 84 */        GL11.glVertex3f(this.verts[(i * 3 + 0)], this.verts[(i * 3 + 1)], this.verts[(i * 3 + 2)]);
/*  85:    */      }
/*  86: 86 */      GL11.glEnd();
/*  87: 87 */      this.currentType = -1;
/*  88: 88 */      return;
/*  89:    */    }
/*  90: 90 */    this.vertices.clear();
/*  91: 91 */    this.colors.clear();
/*  92: 92 */    this.textures.clear();
/*  93:    */    
/*  94: 94 */    this.vertices.put(this.verts, 0, this.vertIndex * 3);
/*  95: 95 */    this.colors.put(this.cols, 0, this.vertIndex * 4);
/*  96: 96 */    this.textures.put(this.texs, 0, this.vertIndex * 2);
/*  97:    */    
/*  98: 98 */    this.vertices.flip();
/*  99: 99 */    this.colors.flip();
/* 100:100 */    this.textures.flip();
/* 101:    */    
/* 102:102 */    GL11.glVertexPointer(3, 0, this.vertices);
/* 103:103 */    GL11.glColorPointer(4, 0, this.colors);
/* 104:104 */    GL11.glTexCoordPointer(2, 0, this.textures);
/* 105:    */    
/* 106:106 */    GL11.glDrawArrays(this.currentType, 0, this.vertIndex);
/* 107:107 */    this.currentType = -1;
/* 108:    */  }
/* 109:    */  
/* 112:    */  private void applyBuffer()
/* 113:    */  {
/* 114:114 */    if (this.listMode > 0) {
/* 115:115 */      return;
/* 116:    */    }
/* 117:    */    
/* 118:118 */    if (this.vertIndex != 0) {
/* 119:119 */      flushBuffer();
/* 120:120 */      startBuffer();
/* 121:    */    }
/* 122:    */    
/* 123:123 */    super.glColor4f(this.color[0], this.color[1], this.color[2], this.color[3]);
/* 124:    */  }
/* 125:    */  
/* 128:    */  public void flush()
/* 129:    */  {
/* 130:130 */    super.flush();
/* 131:    */    
/* 132:132 */    applyBuffer();
/* 133:    */  }
/* 134:    */  
/* 137:    */  public void glBegin(int geomType)
/* 138:    */  {
/* 139:139 */    if (this.listMode > 0) {
/* 140:140 */      super.glBegin(geomType);
/* 141:141 */      return;
/* 142:    */    }
/* 143:    */    
/* 144:144 */    if (this.currentType != geomType) {
/* 145:145 */      applyBuffer();
/* 146:146 */      this.currentType = geomType;
/* 147:    */    }
/* 148:    */  }
/* 149:    */  
/* 152:    */  public void glColor4f(float r, float g, float b, float a)
/* 153:    */  {
/* 154:154 */    a *= this.alphaScale;
/* 155:    */    
/* 156:156 */    this.color[0] = r;
/* 157:157 */    this.color[1] = g;
/* 158:158 */    this.color[2] = b;
/* 159:159 */    this.color[3] = a;
/* 160:    */    
/* 161:161 */    if (this.listMode > 0) {
/* 162:162 */      super.glColor4f(r, g, b, a);
/* 163:163 */      return;
/* 164:    */    }
/* 165:    */  }
/* 166:    */  
/* 169:    */  public void glEnd()
/* 170:    */  {
/* 171:171 */    if (this.listMode > 0) {
/* 172:172 */      super.glEnd();
/* 173:173 */      return;
/* 174:    */    }
/* 175:    */  }
/* 176:    */  
/* 179:    */  public void glTexCoord2f(float u, float v)
/* 180:    */  {
/* 181:181 */    if (this.listMode > 0) {
/* 182:182 */      super.glTexCoord2f(u, v);
/* 183:183 */      return;
/* 184:    */    }
/* 185:    */    
/* 186:186 */    this.tex[0] = u;
/* 187:187 */    this.tex[1] = v;
/* 188:    */  }
/* 189:    */  
/* 192:    */  public void glVertex2f(float x, float y)
/* 193:    */  {
/* 194:194 */    if (this.listMode > 0) {
/* 195:195 */      super.glVertex2f(x, y);
/* 196:196 */      return;
/* 197:    */    }
/* 198:    */    
/* 199:199 */    glVertex3f(x, y, 0.0F);
/* 200:    */  }
/* 201:    */  
/* 204:    */  public void glVertex3f(float x, float y, float z)
/* 205:    */  {
/* 206:206 */    if (this.listMode > 0) {
/* 207:207 */      super.glVertex3f(x, y, z);
/* 208:208 */      return;
/* 209:    */    }
/* 210:    */    
/* 211:211 */    this.verts[(this.vertIndex * 3 + 0)] = x;
/* 212:212 */    this.verts[(this.vertIndex * 3 + 1)] = y;
/* 213:213 */    this.verts[(this.vertIndex * 3 + 2)] = z;
/* 214:214 */    this.cols[(this.vertIndex * 4 + 0)] = this.color[0];
/* 215:215 */    this.cols[(this.vertIndex * 4 + 1)] = this.color[1];
/* 216:216 */    this.cols[(this.vertIndex * 4 + 2)] = this.color[2];
/* 217:217 */    this.cols[(this.vertIndex * 4 + 3)] = this.color[3];
/* 218:218 */    this.texs[(this.vertIndex * 2 + 0)] = this.tex[0];
/* 219:219 */    this.texs[(this.vertIndex * 2 + 1)] = this.tex[1];
/* 220:220 */    this.vertIndex += 1;
/* 221:    */    
/* 222:222 */    if ((this.vertIndex > 4950) && 
/* 223:223 */      (isSplittable(this.vertIndex, this.currentType))) {
/* 224:224 */      int type = this.currentType;
/* 225:225 */      applyBuffer();
/* 226:226 */      this.currentType = type;
/* 227:    */    }
/* 228:    */  }
/* 229:    */  
/* 237:    */  private boolean isSplittable(int count, int type)
/* 238:    */  {
/* 239:239 */    switch (type) {
/* 240:    */    case 7: 
/* 241:241 */      return count % 4 == 0;
/* 242:    */    case 4: 
/* 243:243 */      return count % 3 == 0;
/* 244:    */    case 6913: 
/* 245:245 */      return count % 2 == 0;
/* 246:    */    }
/* 247:    */    
/* 248:248 */    return false;
/* 249:    */  }
/* 250:    */  
/* 253:    */  public void glBindTexture(int target, int id)
/* 254:    */  {
/* 255:255 */    applyBuffer();
/* 256:256 */    super.glBindTexture(target, id);
/* 257:    */  }
/* 258:    */  
/* 261:    */  public void glBlendFunc(int src, int dest)
/* 262:    */  {
/* 263:263 */    applyBuffer();
/* 264:264 */    super.glBlendFunc(src, dest);
/* 265:    */  }
/* 266:    */  
/* 269:    */  public void glCallList(int id)
/* 270:    */  {
/* 271:271 */    applyBuffer();
/* 272:272 */    super.glCallList(id);
/* 273:    */  }
/* 274:    */  
/* 277:    */  public void glClear(int value)
/* 278:    */  {
/* 279:279 */    applyBuffer();
/* 280:280 */    super.glClear(value);
/* 281:    */  }
/* 282:    */  
/* 285:    */  public void glClipPlane(int plane, DoubleBuffer buffer)
/* 286:    */  {
/* 287:287 */    applyBuffer();
/* 288:288 */    super.glClipPlane(plane, buffer);
/* 289:    */  }
/* 290:    */  
/* 293:    */  public void glColorMask(boolean red, boolean green, boolean blue, boolean alpha)
/* 294:    */  {
/* 295:295 */    applyBuffer();
/* 296:296 */    super.glColorMask(red, green, blue, alpha);
/* 297:    */  }
/* 298:    */  
/* 301:    */  public void glDisable(int item)
/* 302:    */  {
/* 303:303 */    applyBuffer();
/* 304:304 */    super.glDisable(item);
/* 305:    */  }
/* 306:    */  
/* 309:    */  public void glEnable(int item)
/* 310:    */  {
/* 311:311 */    applyBuffer();
/* 312:312 */    super.glEnable(item);
/* 313:    */  }
/* 314:    */  
/* 317:    */  public void glLineWidth(float width)
/* 318:    */  {
/* 319:319 */    applyBuffer();
/* 320:320 */    super.glLineWidth(width);
/* 321:    */  }
/* 322:    */  
/* 325:    */  public void glPointSize(float size)
/* 326:    */  {
/* 327:327 */    applyBuffer();
/* 328:328 */    super.glPointSize(size);
/* 329:    */  }
/* 330:    */  
/* 333:    */  public void glPopMatrix()
/* 334:    */  {
/* 335:335 */    applyBuffer();
/* 336:336 */    super.glPopMatrix();
/* 337:    */  }
/* 338:    */  
/* 341:    */  public void glPushMatrix()
/* 342:    */  {
/* 343:343 */    applyBuffer();
/* 344:344 */    super.glPushMatrix();
/* 345:    */  }
/* 346:    */  
/* 349:    */  public void glRotatef(float angle, float x, float y, float z)
/* 350:    */  {
/* 351:351 */    applyBuffer();
/* 352:352 */    super.glRotatef(angle, x, y, z);
/* 353:    */  }
/* 354:    */  
/* 357:    */  public void glScalef(float x, float y, float z)
/* 358:    */  {
/* 359:359 */    applyBuffer();
/* 360:360 */    super.glScalef(x, y, z);
/* 361:    */  }
/* 362:    */  
/* 365:    */  public void glScissor(int x, int y, int width, int height)
/* 366:    */  {
/* 367:367 */    applyBuffer();
/* 368:368 */    super.glScissor(x, y, width, height);
/* 369:    */  }
/* 370:    */  
/* 373:    */  public void glTexEnvi(int target, int mode, int value)
/* 374:    */  {
/* 375:375 */    applyBuffer();
/* 376:376 */    super.glTexEnvi(target, mode, value);
/* 377:    */  }
/* 378:    */  
/* 381:    */  public void glTranslatef(float x, float y, float z)
/* 382:    */  {
/* 383:383 */    applyBuffer();
/* 384:384 */    super.glTranslatef(x, y, z);
/* 385:    */  }
/* 386:    */  
/* 389:    */  public void glEndList()
/* 390:    */  {
/* 391:391 */    this.listMode -= 1;
/* 392:392 */    super.glEndList();
/* 393:    */  }
/* 394:    */  
/* 397:    */  public void glNewList(int id, int option)
/* 398:    */  {
/* 399:399 */    this.listMode += 1;
/* 400:400 */    super.glNewList(id, option);
/* 401:    */  }
/* 402:    */  
/* 405:    */  public float[] getCurrentColor()
/* 406:    */  {
/* 407:407 */    return this.color;
/* 408:    */  }
/* 409:    */  
/* 412:    */  public void glLoadMatrix(FloatBuffer buffer)
/* 413:    */  {
/* 414:414 */    flushBuffer();
/* 415:415 */    super.glLoadMatrix(buffer);
/* 416:    */  }
/* 417:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.opengl.renderer.VAOGLRenderer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */