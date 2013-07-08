/*   1:    */package org.lwjgl.util.vector;
/*   2:    */
/*   3:    */import java.nio.FloatBuffer;
/*   4:    */
/*  43:    */public class Quaternion
/*  44:    */  extends Vector
/*  45:    */  implements ReadableVector4f
/*  46:    */{
/*  47:    */  private static final long serialVersionUID = 1L;
/*  48:    */  public float x;
/*  49:    */  public float y;
/*  50:    */  public float z;
/*  51:    */  public float w;
/*  52:    */  
/*  53:    */  public Quaternion()
/*  54:    */  {
/*  55: 55 */    setIdentity();
/*  56:    */  }
/*  57:    */  
/*  62:    */  public Quaternion(ReadableVector4f src)
/*  63:    */  {
/*  64: 64 */    set(src);
/*  65:    */  }
/*  66:    */  
/*  70:    */  public Quaternion(float x, float y, float z, float w)
/*  71:    */  {
/*  72: 72 */    set(x, y, z, w);
/*  73:    */  }
/*  74:    */  
/*  79:    */  public void set(float x, float y)
/*  80:    */  {
/*  81: 81 */    this.x = x;
/*  82: 82 */    this.y = y;
/*  83:    */  }
/*  84:    */  
/*  89:    */  public void set(float x, float y, float z)
/*  90:    */  {
/*  91: 91 */    this.x = x;
/*  92: 92 */    this.y = y;
/*  93: 93 */    this.z = z;
/*  94:    */  }
/*  95:    */  
/* 101:    */  public void set(float x, float y, float z, float w)
/* 102:    */  {
/* 103:103 */    this.x = x;
/* 104:104 */    this.y = y;
/* 105:105 */    this.z = z;
/* 106:106 */    this.w = w;
/* 107:    */  }
/* 108:    */  
/* 115:    */  public Quaternion set(ReadableVector4f src)
/* 116:    */  {
/* 117:117 */    this.x = src.getX();
/* 118:118 */    this.y = src.getY();
/* 119:119 */    this.z = src.getZ();
/* 120:120 */    this.w = src.getW();
/* 121:121 */    return this;
/* 122:    */  }
/* 123:    */  
/* 127:    */  public Quaternion setIdentity()
/* 128:    */  {
/* 129:129 */    return setIdentity(this);
/* 130:    */  }
/* 131:    */  
/* 136:    */  public static Quaternion setIdentity(Quaternion q)
/* 137:    */  {
/* 138:138 */    q.x = 0.0F;
/* 139:139 */    q.y = 0.0F;
/* 140:140 */    q.z = 0.0F;
/* 141:141 */    q.w = 1.0F;
/* 142:142 */    return q;
/* 143:    */  }
/* 144:    */  
/* 147:    */  public float lengthSquared()
/* 148:    */  {
/* 149:149 */    return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
/* 150:    */  }
/* 151:    */  
/* 161:    */  public static Quaternion normalise(Quaternion src, Quaternion dest)
/* 162:    */  {
/* 163:163 */    float inv_l = 1.0F / src.length();
/* 164:    */    
/* 165:165 */    if (dest == null) {
/* 166:166 */      dest = new Quaternion();
/* 167:    */    }
/* 168:168 */    dest.set(src.x * inv_l, src.y * inv_l, src.z * inv_l, src.w * inv_l);
/* 169:    */    
/* 170:170 */    return dest;
/* 171:    */  }
/* 172:    */  
/* 180:    */  public Quaternion normalise(Quaternion dest)
/* 181:    */  {
/* 182:182 */    return normalise(this, dest);
/* 183:    */  }
/* 184:    */  
/* 193:    */  public static float dot(Quaternion left, Quaternion right)
/* 194:    */  {
/* 195:195 */    return left.x * right.x + left.y * right.y + left.z * right.z + left.w * right.w;
/* 196:    */  }
/* 197:    */  
/* 205:    */  public Quaternion negate(Quaternion dest)
/* 206:    */  {
/* 207:207 */    return negate(this, dest);
/* 208:    */  }
/* 209:    */  
/* 218:    */  public static Quaternion negate(Quaternion src, Quaternion dest)
/* 219:    */  {
/* 220:220 */    if (dest == null) {
/* 221:221 */      dest = new Quaternion();
/* 222:    */    }
/* 223:223 */    dest.x = (-src.x);
/* 224:224 */    dest.y = (-src.y);
/* 225:225 */    dest.z = (-src.z);
/* 226:226 */    dest.w = src.w;
/* 227:    */    
/* 228:228 */    return dest;
/* 229:    */  }
/* 230:    */  
/* 233:    */  public Vector negate()
/* 234:    */  {
/* 235:235 */    return negate(this, this);
/* 236:    */  }
/* 237:    */  
/* 240:    */  public Vector load(FloatBuffer buf)
/* 241:    */  {
/* 242:242 */    this.x = buf.get();
/* 243:243 */    this.y = buf.get();
/* 244:244 */    this.z = buf.get();
/* 245:245 */    this.w = buf.get();
/* 246:246 */    return this;
/* 247:    */  }
/* 248:    */  
/* 253:    */  public Vector scale(float scale)
/* 254:    */  {
/* 255:255 */    return scale(scale, this, this);
/* 256:    */  }
/* 257:    */  
/* 264:    */  public static Quaternion scale(float scale, Quaternion src, Quaternion dest)
/* 265:    */  {
/* 266:266 */    if (dest == null)
/* 267:267 */      dest = new Quaternion();
/* 268:268 */    src.x *= scale;
/* 269:269 */    src.y *= scale;
/* 270:270 */    src.z *= scale;
/* 271:271 */    src.w *= scale;
/* 272:272 */    return dest;
/* 273:    */  }
/* 274:    */  
/* 277:    */  public Vector store(FloatBuffer buf)
/* 278:    */  {
/* 279:279 */    buf.put(this.x);
/* 280:280 */    buf.put(this.y);
/* 281:281 */    buf.put(this.z);
/* 282:282 */    buf.put(this.w);
/* 283:    */    
/* 284:284 */    return this;
/* 285:    */  }
/* 286:    */  
/* 289:    */  public final float getX()
/* 290:    */  {
/* 291:291 */    return this.x;
/* 292:    */  }
/* 293:    */  
/* 296:    */  public final float getY()
/* 297:    */  {
/* 298:298 */    return this.y;
/* 299:    */  }
/* 300:    */  
/* 305:    */  public final void setX(float x)
/* 306:    */  {
/* 307:307 */    this.x = x;
/* 308:    */  }
/* 309:    */  
/* 314:    */  public final void setY(float y)
/* 315:    */  {
/* 316:316 */    this.y = y;
/* 317:    */  }
/* 318:    */  
/* 323:    */  public void setZ(float z)
/* 324:    */  {
/* 325:325 */    this.z = z;
/* 326:    */  }
/* 327:    */  
/* 332:    */  public float getZ()
/* 333:    */  {
/* 334:334 */    return this.z;
/* 335:    */  }
/* 336:    */  
/* 341:    */  public void setW(float w)
/* 342:    */  {
/* 343:343 */    this.w = w;
/* 344:    */  }
/* 345:    */  
/* 350:    */  public float getW()
/* 351:    */  {
/* 352:352 */    return this.w;
/* 353:    */  }
/* 354:    */  
/* 355:    */  public String toString() {
/* 356:356 */    return "Quaternion: " + this.x + " " + this.y + " " + this.z + " " + this.w;
/* 357:    */  }
/* 358:    */  
/* 369:    */  public static Quaternion mul(Quaternion left, Quaternion right, Quaternion dest)
/* 370:    */  {
/* 371:371 */    if (dest == null)
/* 372:372 */      dest = new Quaternion();
/* 373:373 */    dest.set(left.x * right.w + left.w * right.x + left.y * right.z - left.z * right.y, left.y * right.w + left.w * right.y + left.z * right.x - left.x * right.z, left.z * right.w + left.w * right.z + left.x * right.y - left.y * right.x, left.w * right.w - left.x * right.x - left.y * right.y - left.z * right.z);
/* 374:    */    
/* 379:379 */    return dest;
/* 380:    */  }
/* 381:    */  
/* 393:    */  public static Quaternion mulInverse(Quaternion left, Quaternion right, Quaternion dest)
/* 394:    */  {
/* 395:395 */    float n = right.lengthSquared();
/* 396:    */    
/* 397:397 */    n = n == 0.0D ? n : 1.0F / n;
/* 398:    */    
/* 399:399 */    if (dest == null)
/* 400:400 */      dest = new Quaternion();
/* 401:401 */    dest.set((left.x * right.w - left.w * right.x - left.y * right.z + left.z * right.y) * n, (left.y * right.w - left.w * right.y - left.z * right.x + left.x * right.z) * n, (left.z * right.w - left.w * right.z - left.x * right.y + left.y * right.x) * n, (left.w * right.w + left.x * right.x + left.y * right.y + left.z * right.z) * n);
/* 402:    */    
/* 412:412 */    return dest;
/* 413:    */  }
/* 414:    */  
/* 421:    */  public final void setFromAxisAngle(Vector4f a1)
/* 422:    */  {
/* 423:423 */    this.x = a1.x;
/* 424:424 */    this.y = a1.y;
/* 425:425 */    this.z = a1.z;
/* 426:426 */    float n = (float)Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
/* 427:    */    
/* 428:428 */    float s = (float)(Math.sin(0.5D * a1.w) / n);
/* 429:429 */    this.x *= s;
/* 430:430 */    this.y *= s;
/* 431:431 */    this.z *= s;
/* 432:432 */    this.w = ((float)Math.cos(0.5D * a1.w));
/* 433:    */  }
/* 434:    */  
/* 442:    */  public final Quaternion setFromMatrix(Matrix4f m)
/* 443:    */  {
/* 444:444 */    return setFromMatrix(m, this);
/* 445:    */  }
/* 446:    */  
/* 456:    */  public static Quaternion setFromMatrix(Matrix4f m, Quaternion q)
/* 457:    */  {
/* 458:458 */    return q.setFromMat(m.m00, m.m01, m.m02, m.m10, m.m11, m.m12, m.m20, m.m21, m.m22);
/* 459:    */  }
/* 460:    */  
/* 468:    */  public final Quaternion setFromMatrix(Matrix3f m)
/* 469:    */  {
/* 470:470 */    return setFromMatrix(m, this);
/* 471:    */  }
/* 472:    */  
/* 482:    */  public static Quaternion setFromMatrix(Matrix3f m, Quaternion q)
/* 483:    */  {
/* 484:484 */    return q.setFromMat(m.m00, m.m01, m.m02, m.m10, m.m11, m.m12, m.m20, m.m21, m.m22);
/* 485:    */  }
/* 486:    */  
/* 493:    */  private Quaternion setFromMat(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22)
/* 494:    */  {
/* 495:495 */    float tr = m00 + m11 + m22;
/* 496:496 */    if (tr >= 0.0D) {
/* 497:497 */      float s = (float)Math.sqrt(tr + 1.0D);
/* 498:498 */      this.w = (s * 0.5F);
/* 499:499 */      s = 0.5F / s;
/* 500:500 */      this.x = ((m21 - m12) * s);
/* 501:501 */      this.y = ((m02 - m20) * s);
/* 502:502 */      this.z = ((m10 - m01) * s);
/* 503:    */    } else {
/* 504:504 */      float max = Math.max(Math.max(m00, m11), m22);
/* 505:505 */      if (max == m00) {
/* 506:506 */        float s = (float)Math.sqrt(m00 - (m11 + m22) + 1.0D);
/* 507:507 */        this.x = (s * 0.5F);
/* 508:508 */        s = 0.5F / s;
/* 509:509 */        this.y = ((m01 + m10) * s);
/* 510:510 */        this.z = ((m20 + m02) * s);
/* 511:511 */        this.w = ((m21 - m12) * s);
/* 512:512 */      } else if (max == m11) {
/* 513:513 */        float s = (float)Math.sqrt(m11 - (m22 + m00) + 1.0D);
/* 514:514 */        this.y = (s * 0.5F);
/* 515:515 */        s = 0.5F / s;
/* 516:516 */        this.z = ((m12 + m21) * s);
/* 517:517 */        this.x = ((m01 + m10) * s);
/* 518:518 */        this.w = ((m02 - m20) * s);
/* 519:    */      } else {
/* 520:520 */        float s = (float)Math.sqrt(m22 - (m00 + m11) + 1.0D);
/* 521:521 */        this.z = (s * 0.5F);
/* 522:522 */        s = 0.5F / s;
/* 523:523 */        this.x = ((m20 + m02) * s);
/* 524:524 */        this.y = ((m12 + m21) * s);
/* 525:525 */        this.w = ((m10 - m01) * s);
/* 526:    */      }
/* 527:    */    }
/* 528:528 */    return this;
/* 529:    */  }
/* 530:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.vector.Quaternion
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */