/*   1:    */package org.newdawn.slick.geom;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import org.newdawn.slick.util.FastTrig;
/*   5:    */
/*  22:    */public class Vector2f
/*  23:    */  implements Serializable
/*  24:    */{
/*  25:    */  private static final long serialVersionUID = 1339934L;
/*  26:    */  public float x;
/*  27:    */  public float y;
/*  28:    */  
/*  29:    */  public strictfp Vector2f() {}
/*  30:    */  
/*  31:    */  public strictfp Vector2f(float[] coords)
/*  32:    */  {
/*  33: 33 */    this.x = coords[0];
/*  34: 34 */    this.y = coords[1];
/*  35:    */  }
/*  36:    */  
/*  41:    */  public strictfp Vector2f(double theta)
/*  42:    */  {
/*  43: 43 */    this.x = 1.0F;
/*  44: 44 */    this.y = 0.0F;
/*  45: 45 */    setTheta(theta);
/*  46:    */  }
/*  47:    */  
/*  54:    */  public strictfp void setTheta(double theta)
/*  55:    */  {
/*  56: 56 */    if ((theta < -360.0D) || (theta > 360.0D)) {
/*  57: 57 */      theta %= 360.0D;
/*  58:    */    }
/*  59: 59 */    if (theta < 0.0D) {
/*  60: 60 */      theta = 360.0D + theta;
/*  61:    */    }
/*  62: 62 */    double oldTheta = getTheta();
/*  63: 63 */    if ((theta < -360.0D) || (theta > 360.0D)) {
/*  64: 64 */      oldTheta %= 360.0D;
/*  65:    */    }
/*  66: 66 */    if (theta < 0.0D) {
/*  67: 67 */      oldTheta = 360.0D + oldTheta;
/*  68:    */    }
/*  69:    */    
/*  70: 70 */    float len = length();
/*  71: 71 */    this.x = (len * (float)FastTrig.cos(StrictMath.toRadians(theta)));
/*  72: 72 */    this.y = (len * (float)FastTrig.sin(StrictMath.toRadians(theta)));
/*  73:    */  }
/*  74:    */  
/*  87:    */  public strictfp Vector2f add(double theta)
/*  88:    */  {
/*  89: 89 */    setTheta(getTheta() + theta);
/*  90:    */    
/*  91: 91 */    return this;
/*  92:    */  }
/*  93:    */  
/*  99:    */  public strictfp Vector2f sub(double theta)
/* 100:    */  {
/* 101:101 */    setTheta(getTheta() - theta);
/* 102:    */    
/* 103:103 */    return this;
/* 104:    */  }
/* 105:    */  
/* 110:    */  public strictfp double getTheta()
/* 111:    */  {
/* 112:112 */    double theta = StrictMath.toDegrees(StrictMath.atan2(this.y, this.x));
/* 113:113 */    if ((theta < -360.0D) || (theta > 360.0D)) {
/* 114:114 */      theta %= 360.0D;
/* 115:    */    }
/* 116:116 */    if (theta < 0.0D) {
/* 117:117 */      theta = 360.0D + theta;
/* 118:    */    }
/* 119:    */    
/* 120:120 */    return theta;
/* 121:    */  }
/* 122:    */  
/* 127:    */  public strictfp float getX()
/* 128:    */  {
/* 129:129 */    return this.x;
/* 130:    */  }
/* 131:    */  
/* 136:    */  public strictfp float getY()
/* 137:    */  {
/* 138:138 */    return this.y;
/* 139:    */  }
/* 140:    */  
/* 145:    */  public strictfp Vector2f(Vector2f other)
/* 146:    */  {
/* 147:147 */    this(other.getX(), other.getY());
/* 148:    */  }
/* 149:    */  
/* 155:    */  public strictfp Vector2f(float x, float y)
/* 156:    */  {
/* 157:157 */    this.x = x;
/* 158:158 */    this.y = y;
/* 159:    */  }
/* 160:    */  
/* 165:    */  public strictfp void set(Vector2f other)
/* 166:    */  {
/* 167:167 */    set(other.getX(), other.getY());
/* 168:    */  }
/* 169:    */  
/* 175:    */  public strictfp float dot(Vector2f other)
/* 176:    */  {
/* 177:177 */    return this.x * other.getX() + this.y * other.getY();
/* 178:    */  }
/* 179:    */  
/* 186:    */  public strictfp Vector2f set(float x, float y)
/* 187:    */  {
/* 188:188 */    this.x = x;
/* 189:189 */    this.y = y;
/* 190:    */    
/* 191:191 */    return this;
/* 192:    */  }
/* 193:    */  
/* 198:    */  public strictfp Vector2f getPerpendicular()
/* 199:    */  {
/* 200:200 */    return new Vector2f(-this.y, this.x);
/* 201:    */  }
/* 202:    */  
/* 208:    */  public strictfp Vector2f set(float[] pt)
/* 209:    */  {
/* 210:210 */    return set(pt[0], pt[1]);
/* 211:    */  }
/* 212:    */  
/* 217:    */  public strictfp Vector2f negate()
/* 218:    */  {
/* 219:219 */    return new Vector2f(-this.x, -this.y);
/* 220:    */  }
/* 221:    */  
/* 226:    */  public strictfp Vector2f negateLocal()
/* 227:    */  {
/* 228:228 */    this.x = (-this.x);
/* 229:229 */    this.y = (-this.y);
/* 230:    */    
/* 231:231 */    return this;
/* 232:    */  }
/* 233:    */  
/* 240:    */  public strictfp Vector2f add(Vector2f v)
/* 241:    */  {
/* 242:242 */    this.x += v.getX();
/* 243:243 */    this.y += v.getY();
/* 244:    */    
/* 245:245 */    return this;
/* 246:    */  }
/* 247:    */  
/* 254:    */  public strictfp Vector2f sub(Vector2f v)
/* 255:    */  {
/* 256:256 */    this.x -= v.getX();
/* 257:257 */    this.y -= v.getY();
/* 258:    */    
/* 259:259 */    return this;
/* 260:    */  }
/* 261:    */  
/* 268:    */  public strictfp Vector2f scale(float a)
/* 269:    */  {
/* 270:270 */    this.x *= a;
/* 271:271 */    this.y *= a;
/* 272:    */    
/* 273:273 */    return this;
/* 274:    */  }
/* 275:    */  
/* 280:    */  public strictfp Vector2f normalise()
/* 281:    */  {
/* 282:282 */    float l = length();
/* 283:    */    
/* 284:284 */    if (l == 0.0F) {
/* 285:285 */      return this;
/* 286:    */    }
/* 287:    */    
/* 288:288 */    this.x /= l;
/* 289:289 */    this.y /= l;
/* 290:290 */    return this;
/* 291:    */  }
/* 292:    */  
/* 297:    */  public strictfp Vector2f getNormal()
/* 298:    */  {
/* 299:299 */    Vector2f cp = copy();
/* 300:300 */    cp.normalise();
/* 301:301 */    return cp;
/* 302:    */  }
/* 303:    */  
/* 308:    */  public strictfp float lengthSquared()
/* 309:    */  {
/* 310:310 */    return this.x * this.x + this.y * this.y;
/* 311:    */  }
/* 312:    */  
/* 318:    */  public strictfp float length()
/* 319:    */  {
/* 320:320 */    return (float)Math.sqrt(lengthSquared());
/* 321:    */  }
/* 322:    */  
/* 328:    */  public strictfp void projectOntoUnit(Vector2f b, Vector2f result)
/* 329:    */  {
/* 330:330 */    float dp = b.dot(this);
/* 331:    */    
/* 332:332 */    result.x = (dp * b.getX());
/* 333:333 */    result.y = (dp * b.getY());
/* 334:    */  }
/* 335:    */  
/* 341:    */  public strictfp Vector2f copy()
/* 342:    */  {
/* 343:343 */    return new Vector2f(this.x, this.y);
/* 344:    */  }
/* 345:    */  
/* 348:    */  public strictfp String toString()
/* 349:    */  {
/* 350:350 */    return "[Vector2f " + this.x + "," + this.y + " (" + length() + ")]";
/* 351:    */  }
/* 352:    */  
/* 358:    */  public strictfp float distance(Vector2f other)
/* 359:    */  {
/* 360:360 */    return (float)Math.sqrt(distanceSquared(other));
/* 361:    */  }
/* 362:    */  
/* 370:    */  public strictfp float distanceSquared(Vector2f other)
/* 371:    */  {
/* 372:372 */    float dx = other.getX() - getX();
/* 373:373 */    float dy = other.getY() - getY();
/* 374:    */    
/* 375:375 */    return dx * dx + dy * dy;
/* 376:    */  }
/* 377:    */  
/* 380:    */  public strictfp int hashCode()
/* 381:    */  {
/* 382:382 */    return 997 * (int)this.x ^ 991 * (int)this.y;
/* 383:    */  }
/* 384:    */  
/* 387:    */  public strictfp boolean equals(Object other)
/* 388:    */  {
/* 389:389 */    if ((other instanceof Vector2f)) {
/* 390:390 */      Vector2f o = (Vector2f)other;
/* 391:391 */      return (o.x == this.x) && (o.y == this.y);
/* 392:    */    }
/* 393:    */    
/* 394:394 */    return false;
/* 395:    */  }
/* 396:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.geom.Vector2f
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */