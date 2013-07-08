/*   1:    */package org.newdawn.slick.geom;
/*   2:    */
/*   3:    */import org.newdawn.slick.Color;
/*   4:    */import org.newdawn.slick.Image;
/*   5:    */import org.newdawn.slick.ShapeFill;
/*   6:    */import org.newdawn.slick.opengl.Texture;
/*   7:    */import org.newdawn.slick.opengl.TextureImpl;
/*   8:    */import org.newdawn.slick.opengl.renderer.LineStripRenderer;
/*   9:    */import org.newdawn.slick.opengl.renderer.Renderer;
/*  10:    */import org.newdawn.slick.opengl.renderer.SGL;
/*  11:    */
/*  16:    */public final class ShapeRenderer
/*  17:    */{
/*  18: 18 */  private static SGL GL = ;
/*  19:    */  
/*  20: 20 */  private static LineStripRenderer LSR = Renderer.getLineStripRenderer();
/*  21:    */  
/*  27:    */  public static final void draw(Shape shape)
/*  28:    */  {
/*  29: 29 */    Texture t = TextureImpl.getLastBind();
/*  30: 30 */    TextureImpl.bindNone();
/*  31:    */    
/*  32: 32 */    float[] points = shape.getPoints();
/*  33:    */    
/*  34: 34 */    LSR.start();
/*  35: 35 */    for (int i = 0; i < points.length; i += 2) {
/*  36: 36 */      LSR.vertex(points[i], points[(i + 1)]);
/*  37:    */    }
/*  38:    */    
/*  39: 39 */    if (shape.closed()) {
/*  40: 40 */      LSR.vertex(points[0], points[1]);
/*  41:    */    }
/*  42:    */    
/*  43: 43 */    LSR.end();
/*  44:    */    
/*  45: 45 */    if (t == null) {
/*  46: 46 */      TextureImpl.bindNone();
/*  47:    */    } else {
/*  48: 48 */      t.bind();
/*  49:    */    }
/*  50:    */  }
/*  51:    */  
/*  58:    */  public static final void draw(Shape shape, ShapeFill fill)
/*  59:    */  {
/*  60: 60 */    float[] points = shape.getPoints();
/*  61:    */    
/*  62: 62 */    Texture t = TextureImpl.getLastBind();
/*  63: 63 */    TextureImpl.bindNone();
/*  64:    */    
/*  65: 65 */    float[] center = shape.getCenter();
/*  66: 66 */    GL.glBegin(3);
/*  67: 67 */    for (int i = 0; i < points.length; i += 2) {
/*  68: 68 */      fill.colorAt(shape, points[i], points[(i + 1)]).bind();
/*  69: 69 */      Vector2f offset = fill.getOffsetAt(shape, points[i], points[(i + 1)]);
/*  70: 70 */      GL.glVertex2f(points[i] + offset.x, points[(i + 1)] + offset.y);
/*  71:    */    }
/*  72:    */    
/*  73: 73 */    if (shape.closed()) {
/*  74: 74 */      fill.colorAt(shape, points[0], points[1]).bind();
/*  75: 75 */      Vector2f offset = fill.getOffsetAt(shape, points[0], points[1]);
/*  76: 76 */      GL.glVertex2f(points[0] + offset.x, points[1] + offset.y);
/*  77:    */    }
/*  78: 78 */    GL.glEnd();
/*  79:    */    
/*  80: 80 */    if (t == null) {
/*  81: 81 */      TextureImpl.bindNone();
/*  82:    */    } else {
/*  83: 83 */      t.bind();
/*  84:    */    }
/*  85:    */  }
/*  86:    */  
/*  92:    */  public static boolean validFill(Shape shape)
/*  93:    */  {
/*  94: 94 */    if (shape.getTriangles() == null) {
/*  95: 95 */      return false;
/*  96:    */    }
/*  97: 97 */    return shape.getTriangles().getTriangleCount() != 0;
/*  98:    */  }
/*  99:    */  
/* 105:    */  public static final void fill(Shape shape)
/* 106:    */  {
/* 107:107 */    if (!validFill(shape)) {
/* 108:108 */      return;
/* 109:    */    }
/* 110:    */    
/* 111:111 */    Texture t = TextureImpl.getLastBind();
/* 112:112 */    TextureImpl.bindNone();
/* 113:    */    
/* 114:114 */    fill(shape, new PointCallback()
/* 115:    */    {
/* 116:    */      public float[] preRenderPoint(Shape shape, float x, float y) {
/* 117:117 */        return null;
/* 118:    */      }
/* 119:    */    });
/* 120:    */    
/* 121:121 */    if (t == null) {
/* 122:122 */      TextureImpl.bindNone();
/* 123:    */    } else {
/* 124:124 */      t.bind();
/* 125:    */    }
/* 126:    */  }
/* 127:    */  
/* 134:    */  private static final void fill(Shape shape, PointCallback callback)
/* 135:    */  {
/* 136:136 */    Triangulator tris = shape.getTriangles();
/* 137:    */    
/* 138:138 */    GL.glBegin(4);
/* 139:139 */    for (int i = 0; i < tris.getTriangleCount(); i++) {
/* 140:140 */      for (int p = 0; p < 3; p++) {
/* 141:141 */        float[] pt = tris.getTrianglePoint(i, p);
/* 142:142 */        float[] np = callback.preRenderPoint(shape, pt[0], pt[1]);
/* 143:    */        
/* 144:144 */        if (np == null) {
/* 145:145 */          GL.glVertex2f(pt[0], pt[1]);
/* 146:    */        } else {
/* 147:147 */          GL.glVertex2f(np[0], np[1]);
/* 148:    */        }
/* 149:    */      }
/* 150:    */    }
/* 151:151 */    GL.glEnd();
/* 152:    */  }
/* 153:    */  
/* 160:    */  public static final void texture(Shape shape, Image image)
/* 161:    */  {
/* 162:162 */    texture(shape, image, 0.01F, 0.01F);
/* 163:    */  }
/* 164:    */  
/* 172:    */  public static final void textureFit(Shape shape, Image image)
/* 173:    */  {
/* 174:174 */    textureFit(shape, image, 1.0F, 1.0F);
/* 175:    */  }
/* 176:    */  
/* 185:    */  public static final void texture(Shape shape, final Image image, float scaleX, final float scaleY)
/* 186:    */  {
/* 187:187 */    if (!validFill(shape)) {
/* 188:188 */      return;
/* 189:    */    }
/* 190:    */    
/* 191:191 */    Texture t = TextureImpl.getLastBind();
/* 192:192 */    image.getTexture().bind();
/* 193:    */    
/* 194:194 */    fill(shape, new PointCallback() {
/* 195:    */      public float[] preRenderPoint(Shape shape, float x, float y) {
/* 196:196 */        float tx = x * this.val$scaleX;
/* 197:197 */        float ty = y * scaleY;
/* 198:    */        
/* 199:199 */        tx = image.getTextureOffsetX() + image.getTextureWidth() * tx;
/* 200:200 */        ty = image.getTextureOffsetY() + image.getTextureHeight() * ty;
/* 201:    */        
/* 202:202 */        ShapeRenderer.GL.glTexCoord2f(tx, ty);
/* 203:203 */        return null;
/* 204:    */      }
/* 205:    */      
/* 206:206 */    });
/* 207:207 */    float[] points = shape.getPoints();
/* 208:    */    
/* 209:209 */    if (t == null) {
/* 210:210 */      TextureImpl.bindNone();
/* 211:    */    } else {
/* 212:212 */      t.bind();
/* 213:    */    }
/* 214:    */  }
/* 215:    */  
/* 225:    */  public static final void textureFit(Shape shape, final Image image, float scaleX, final float scaleY)
/* 226:    */  {
/* 227:227 */    if (!validFill(shape)) {
/* 228:228 */      return;
/* 229:    */    }
/* 230:    */    
/* 231:231 */    float[] points = shape.getPoints();
/* 232:    */    
/* 233:233 */    Texture t = TextureImpl.getLastBind();
/* 234:234 */    image.getTexture().bind();
/* 235:    */    
/* 236:236 */    float minX = shape.getX();
/* 237:237 */    float minY = shape.getY();
/* 238:238 */    float maxX = shape.getMaxX() - minX;
/* 239:239 */    float maxY = shape.getMaxY() - minY;
/* 240:    */    
/* 241:241 */    fill(shape, new PointCallback() {
/* 242:    */      public float[] preRenderPoint(Shape shape, float x, float y) {
/* 243:243 */        x -= shape.getMinX();
/* 244:244 */        y -= shape.getMinY();
/* 245:    */        
/* 246:246 */        x /= (shape.getMaxX() - shape.getMinX());
/* 247:247 */        y /= (shape.getMaxY() - shape.getMinY());
/* 248:    */        
/* 249:249 */        float tx = x * this.val$scaleX;
/* 250:250 */        float ty = y * scaleY;
/* 251:    */        
/* 252:252 */        tx = image.getTextureOffsetX() + image.getTextureWidth() * tx;
/* 253:253 */        ty = image.getTextureOffsetY() + image.getTextureHeight() * ty;
/* 254:    */        
/* 255:255 */        ShapeRenderer.GL.glTexCoord2f(tx, ty);
/* 256:256 */        return null;
/* 257:    */      }
/* 258:    */    });
/* 259:    */    
/* 260:260 */    if (t == null) {
/* 261:261 */      TextureImpl.bindNone();
/* 262:    */    } else {
/* 263:263 */      t.bind();
/* 264:    */    }
/* 265:    */  }
/* 266:    */  
/* 273:    */  public static final void fill(Shape shape, ShapeFill fill)
/* 274:    */  {
/* 275:275 */    if (!validFill(shape)) {
/* 276:276 */      return;
/* 277:    */    }
/* 278:    */    
/* 279:279 */    Texture t = TextureImpl.getLastBind();
/* 280:280 */    TextureImpl.bindNone();
/* 281:    */    
/* 282:282 */    float[] center = shape.getCenter();
/* 283:283 */    fill(shape, new PointCallback() {
/* 284:    */      public float[] preRenderPoint(Shape shape, float x, float y) {
/* 285:285 */        this.val$fill.colorAt(shape, x, y).bind();
/* 286:286 */        Vector2f offset = this.val$fill.getOffsetAt(shape, x, y);
/* 287:    */        
/* 288:288 */        return new float[] { offset.x + x, offset.y + y };
/* 289:    */      }
/* 290:    */    });
/* 291:    */    
/* 292:292 */    if (t == null) {
/* 293:293 */      TextureImpl.bindNone();
/* 294:    */    } else {
/* 295:295 */      t.bind();
/* 296:    */    }
/* 297:    */  }
/* 298:    */  
/* 309:    */  public static final void texture(Shape shape, final Image image, final float scaleX, final float scaleY, ShapeFill fill)
/* 310:    */  {
/* 311:311 */    if (!validFill(shape)) {
/* 312:312 */      return;
/* 313:    */    }
/* 314:    */    
/* 315:315 */    Texture t = TextureImpl.getLastBind();
/* 316:316 */    image.getTexture().bind();
/* 317:    */    
/* 318:318 */    final float[] center = shape.getCenter();
/* 319:319 */    fill(shape, new PointCallback() {
/* 320:    */      public float[] preRenderPoint(Shape shape, float x, float y) {
/* 321:321 */        this.val$fill.colorAt(shape, x - center[0], y - center[1]).bind();
/* 322:322 */        Vector2f offset = this.val$fill.getOffsetAt(shape, x, y);
/* 323:    */        
/* 324:324 */        x += offset.x;
/* 325:325 */        y += offset.y;
/* 326:    */        
/* 327:327 */        float tx = x * scaleX;
/* 328:328 */        float ty = y * scaleY;
/* 329:    */        
/* 330:330 */        tx = image.getTextureOffsetX() + image.getTextureWidth() * tx;
/* 331:331 */        ty = image.getTextureOffsetY() + image.getTextureHeight() * ty;
/* 332:    */        
/* 333:333 */        ShapeRenderer.GL.glTexCoord2f(tx, ty);
/* 334:    */        
/* 335:335 */        return new float[] { offset.x + x, offset.y + y };
/* 336:    */      }
/* 337:    */    });
/* 338:    */    
/* 339:339 */    if (t == null) {
/* 340:340 */      TextureImpl.bindNone();
/* 341:    */    } else {
/* 342:342 */      t.bind();
/* 343:    */    }
/* 344:    */  }
/* 345:    */  
/* 352:    */  public static final void texture(Shape shape, Image image, TexCoordGenerator gen)
/* 353:    */  {
/* 354:354 */    Texture t = TextureImpl.getLastBind();
/* 355:    */    
/* 356:356 */    image.getTexture().bind();
/* 357:    */    
/* 358:358 */    float[] center = shape.getCenter();
/* 359:359 */    fill(shape, new PointCallback() {
/* 360:    */      public float[] preRenderPoint(Shape shape, float x, float y) {
/* 361:361 */        Vector2f tex = this.val$gen.getCoordFor(x, y);
/* 362:362 */        ShapeRenderer.GL.glTexCoord2f(tex.x, tex.y);
/* 363:    */        
/* 364:364 */        return new float[] { x, y };
/* 365:    */      }
/* 366:    */    });
/* 367:    */    
/* 368:368 */    if (t == null) {
/* 369:369 */      TextureImpl.bindNone();
/* 370:    */    } else {
/* 371:371 */      t.bind();
/* 372:    */    }
/* 373:    */  }
/* 374:    */  
/* 375:    */  private static abstract interface PointCallback
/* 376:    */  {
/* 377:    */    public abstract float[] preRenderPoint(Shape paramShape, float paramFloat1, float paramFloat2);
/* 378:    */  }
/* 379:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.geom.ShapeRenderer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */