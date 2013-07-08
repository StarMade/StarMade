/*   1:    */package org.newdawn.slick.particles;
/*   2:    */
/*   3:    */import org.newdawn.slick.Color;
/*   4:    */import org.newdawn.slick.Image;
/*   5:    */import org.newdawn.slick.opengl.TextureImpl;
/*   6:    */import org.newdawn.slick.opengl.renderer.Renderer;
/*   7:    */import org.newdawn.slick.opengl.renderer.SGL;
/*   8:    */
/*  14:    */public class Particle
/*  15:    */{
/*  16: 16 */  protected static SGL GL = ;
/*  17:    */  
/*  19:    */  public static final int INHERIT_POINTS = 1;
/*  20:    */  
/*  22:    */  public static final int USE_POINTS = 2;
/*  23:    */  
/*  24:    */  public static final int USE_QUADS = 3;
/*  25:    */  
/*  26:    */  protected float x;
/*  27:    */  
/*  28:    */  protected float y;
/*  29:    */  
/*  30:    */  protected float velx;
/*  31:    */  
/*  32:    */  protected float vely;
/*  33:    */  
/*  34: 34 */  protected float size = 10.0F;
/*  35:    */  
/*  36: 36 */  protected Color color = Color.white;
/*  37:    */  
/*  38:    */  protected float life;
/*  39:    */  
/*  40:    */  protected float originalLife;
/*  41:    */  
/*  42:    */  private ParticleSystem engine;
/*  43:    */  
/*  44:    */  private ParticleEmitter emitter;
/*  45:    */  
/*  46:    */  protected Image image;
/*  47:    */  
/*  48:    */  protected int type;
/*  49:    */  
/*  50: 50 */  protected int usePoints = 1;
/*  51:    */  
/*  52: 52 */  protected boolean oriented = false;
/*  53:    */  
/*  54: 54 */  protected float scaleY = 1.0F;
/*  55:    */  
/*  61:    */  public Particle(ParticleSystem engine)
/*  62:    */  {
/*  63: 63 */    this.engine = engine;
/*  64:    */  }
/*  65:    */  
/*  70:    */  public float getX()
/*  71:    */  {
/*  72: 72 */    return this.x;
/*  73:    */  }
/*  74:    */  
/*  79:    */  public float getY()
/*  80:    */  {
/*  81: 81 */    return this.y;
/*  82:    */  }
/*  83:    */  
/*  89:    */  public void move(float x, float y)
/*  90:    */  {
/*  91: 91 */    this.x += x;
/*  92: 92 */    this.y += y;
/*  93:    */  }
/*  94:    */  
/*  99:    */  public float getSize()
/* 100:    */  {
/* 101:101 */    return this.size;
/* 102:    */  }
/* 103:    */  
/* 108:    */  public Color getColor()
/* 109:    */  {
/* 110:110 */    return this.color;
/* 111:    */  }
/* 112:    */  
/* 118:    */  public void setImage(Image image)
/* 119:    */  {
/* 120:120 */    this.image = image;
/* 121:    */  }
/* 122:    */  
/* 127:    */  public float getOriginalLife()
/* 128:    */  {
/* 129:129 */    return this.originalLife;
/* 130:    */  }
/* 131:    */  
/* 136:    */  public float getLife()
/* 137:    */  {
/* 138:138 */    return this.life;
/* 139:    */  }
/* 140:    */  
/* 145:    */  public boolean inUse()
/* 146:    */  {
/* 147:147 */    return this.life > 0.0F;
/* 148:    */  }
/* 149:    */  
/* 152:    */  public void render()
/* 153:    */  {
/* 154:154 */    if (((this.engine.usePoints()) && (this.usePoints == 1)) || (this.usePoints == 2))
/* 155:    */    {
/* 156:156 */      TextureImpl.bindNone();
/* 157:157 */      GL.glEnable(2832);
/* 158:158 */      GL.glPointSize(this.size / 2.0F);
/* 159:159 */      this.color.bind();
/* 160:160 */      GL.glBegin(0);
/* 161:161 */      GL.glVertex2f(this.x, this.y);
/* 162:162 */      GL.glEnd();
/* 163:163 */    } else if ((this.oriented) || (this.scaleY != 1.0F)) {
/* 164:164 */      GL.glPushMatrix();
/* 165:    */      
/* 166:166 */      GL.glTranslatef(this.x, this.y, 0.0F);
/* 167:    */      
/* 168:168 */      if (this.oriented) {
/* 169:169 */        float angle = (float)(Math.atan2(this.y, this.x) * 180.0D / 3.141592653589793D);
/* 170:170 */        GL.glRotatef(angle, 0.0F, 0.0F, 1.0F);
/* 171:    */      }
/* 172:    */      
/* 174:174 */      GL.glScalef(1.0F, this.scaleY, 1.0F);
/* 175:    */      
/* 176:176 */      this.image.draw((int)-(this.size / 2.0F), (int)-(this.size / 2.0F), (int)this.size, (int)this.size, this.color);
/* 177:    */      
/* 178:178 */      GL.glPopMatrix();
/* 179:    */    } else {
/* 180:180 */      this.color.bind();
/* 181:181 */      this.image.drawEmbedded((int)(this.x - this.size / 2.0F), (int)(this.y - this.size / 2.0F), (int)this.size, (int)this.size);
/* 182:    */    }
/* 183:    */  }
/* 184:    */  
/* 191:    */  public void update(int delta)
/* 192:    */  {
/* 193:193 */    this.emitter.updateParticle(this, delta);
/* 194:194 */    this.life -= delta;
/* 195:    */    
/* 196:196 */    if (this.life > 0.0F) {
/* 197:197 */      this.x += delta * this.velx;
/* 198:198 */      this.y += delta * this.vely;
/* 199:    */    } else {
/* 200:200 */      this.engine.release(this);
/* 201:    */    }
/* 202:    */  }
/* 203:    */  
/* 211:    */  public void init(ParticleEmitter emitter, float life)
/* 212:    */  {
/* 213:213 */    this.x = 0.0F;
/* 214:214 */    this.emitter = emitter;
/* 215:215 */    this.y = 0.0F;
/* 216:216 */    this.velx = 0.0F;
/* 217:217 */    this.vely = 0.0F;
/* 218:218 */    this.size = 10.0F;
/* 219:219 */    this.type = 0;
/* 220:220 */    this.originalLife = (this.life = life);
/* 221:221 */    this.oriented = false;
/* 222:222 */    this.scaleY = 1.0F;
/* 223:    */  }
/* 224:    */  
/* 230:    */  public void setType(int type)
/* 231:    */  {
/* 232:232 */    this.type = type;
/* 233:    */  }
/* 234:    */  
/* 243:    */  public void setUsePoint(int usePoints)
/* 244:    */  {
/* 245:245 */    this.usePoints = usePoints;
/* 246:    */  }
/* 247:    */  
/* 252:    */  public int getType()
/* 253:    */  {
/* 254:254 */    return this.type;
/* 255:    */  }
/* 256:    */  
/* 262:    */  public void setSize(float size)
/* 263:    */  {
/* 264:264 */    this.size = size;
/* 265:    */  }
/* 266:    */  
/* 272:    */  public void adjustSize(float delta)
/* 273:    */  {
/* 274:274 */    this.size += delta;
/* 275:275 */    this.size = Math.max(0.0F, this.size);
/* 276:    */  }
/* 277:    */  
/* 283:    */  public void setLife(float life)
/* 284:    */  {
/* 285:285 */    this.life = life;
/* 286:    */  }
/* 287:    */  
/* 293:    */  public void adjustLife(float delta)
/* 294:    */  {
/* 295:295 */    this.life += delta;
/* 296:    */  }
/* 297:    */  
/* 301:    */  public void kill()
/* 302:    */  {
/* 303:303 */    this.life = 1.0F;
/* 304:    */  }
/* 305:    */  
/* 317:    */  public void setColor(float r, float g, float b, float a)
/* 318:    */  {
/* 319:319 */    if (this.color == Color.white) {
/* 320:320 */      this.color = new Color(r, g, b, a);
/* 321:    */    } else {
/* 322:322 */      this.color.r = r;
/* 323:323 */      this.color.g = g;
/* 324:324 */      this.color.b = b;
/* 325:325 */      this.color.a = a;
/* 326:    */    }
/* 327:    */  }
/* 328:    */  
/* 336:    */  public void setPosition(float x, float y)
/* 337:    */  {
/* 338:338 */    this.x = x;
/* 339:339 */    this.y = y;
/* 340:    */  }
/* 341:    */  
/* 351:    */  public void setVelocity(float dirx, float diry, float speed)
/* 352:    */  {
/* 353:353 */    this.velx = (dirx * speed);
/* 354:354 */    this.vely = (diry * speed);
/* 355:    */  }
/* 356:    */  
/* 361:    */  public void setSpeed(float speed)
/* 362:    */  {
/* 363:363 */    float currentSpeed = (float)Math.sqrt(this.velx * this.velx + this.vely * this.vely);
/* 364:364 */    this.velx *= speed;
/* 365:365 */    this.vely *= speed;
/* 366:366 */    this.velx /= currentSpeed;
/* 367:367 */    this.vely /= currentSpeed;
/* 368:    */  }
/* 369:    */  
/* 375:    */  public void setVelocity(float velx, float vely)
/* 376:    */  {
/* 377:377 */    setVelocity(velx, vely, 1.0F);
/* 378:    */  }
/* 379:    */  
/* 387:    */  public void adjustPosition(float dx, float dy)
/* 388:    */  {
/* 389:389 */    this.x += dx;
/* 390:390 */    this.y += dy;
/* 391:    */  }
/* 392:    */  
/* 404:    */  public void adjustColor(float r, float g, float b, float a)
/* 405:    */  {
/* 406:406 */    if (this.color == Color.white) {
/* 407:407 */      this.color = new Color(1.0F, 1.0F, 1.0F, 1.0F);
/* 408:    */    }
/* 409:409 */    this.color.r += r;
/* 410:410 */    this.color.g += g;
/* 411:411 */    this.color.b += b;
/* 412:412 */    this.color.a += a;
/* 413:    */  }
/* 414:    */  
/* 426:    */  public void adjustColor(int r, int g, int b, int a)
/* 427:    */  {
/* 428:428 */    if (this.color == Color.white) {
/* 429:429 */      this.color = new Color(1.0F, 1.0F, 1.0F, 1.0F);
/* 430:    */    }
/* 431:    */    
/* 432:432 */    this.color.r += r / 255.0F;
/* 433:433 */    this.color.g += g / 255.0F;
/* 434:434 */    this.color.b += b / 255.0F;
/* 435:435 */    this.color.a += a / 255.0F;
/* 436:    */  }
/* 437:    */  
/* 445:    */  public void adjustVelocity(float dx, float dy)
/* 446:    */  {
/* 447:447 */    this.velx += dx;
/* 448:448 */    this.vely += dy;
/* 449:    */  }
/* 450:    */  
/* 455:    */  public ParticleEmitter getEmitter()
/* 456:    */  {
/* 457:457 */    return this.emitter;
/* 458:    */  }
/* 459:    */  
/* 462:    */  public String toString()
/* 463:    */  {
/* 464:464 */    return super.toString() + " : " + this.life;
/* 465:    */  }
/* 466:    */  
/* 471:    */  public boolean isOriented()
/* 472:    */  {
/* 473:473 */    return this.oriented;
/* 474:    */  }
/* 475:    */  
/* 480:    */  public void setOriented(boolean oriented)
/* 481:    */  {
/* 482:482 */    this.oriented = oriented;
/* 483:    */  }
/* 484:    */  
/* 489:    */  public float getScaleY()
/* 490:    */  {
/* 491:491 */    return this.scaleY;
/* 492:    */  }
/* 493:    */  
/* 498:    */  public void setScaleY(float scaleY)
/* 499:    */  {
/* 500:500 */    this.scaleY = scaleY;
/* 501:    */  }
/* 502:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.particles.Particle
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */