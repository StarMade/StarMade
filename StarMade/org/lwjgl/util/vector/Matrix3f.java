/*   1:    */package org.lwjgl.util.vector;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.nio.FloatBuffer;
/*   5:    */
/*  48:    */public class Matrix3f
/*  49:    */  extends Matrix
/*  50:    */  implements Serializable
/*  51:    */{
/*  52:    */  private static final long serialVersionUID = 1L;
/*  53:    */  public float m00;
/*  54:    */  public float m01;
/*  55:    */  public float m02;
/*  56:    */  public float m10;
/*  57:    */  public float m11;
/*  58:    */  public float m12;
/*  59:    */  public float m20;
/*  60:    */  public float m21;
/*  61:    */  public float m22;
/*  62:    */  
/*  63:    */  public Matrix3f()
/*  64:    */  {
/*  65: 65 */    setIdentity();
/*  66:    */  }
/*  67:    */  
/*  72:    */  public Matrix3f load(Matrix3f src)
/*  73:    */  {
/*  74: 74 */    return load(src, this);
/*  75:    */  }
/*  76:    */  
/*  82:    */  public static Matrix3f load(Matrix3f src, Matrix3f dest)
/*  83:    */  {
/*  84: 84 */    if (dest == null) {
/*  85: 85 */      dest = new Matrix3f();
/*  86:    */    }
/*  87: 87 */    dest.m00 = src.m00;
/*  88: 88 */    dest.m10 = src.m10;
/*  89: 89 */    dest.m20 = src.m20;
/*  90: 90 */    dest.m01 = src.m01;
/*  91: 91 */    dest.m11 = src.m11;
/*  92: 92 */    dest.m21 = src.m21;
/*  93: 93 */    dest.m02 = src.m02;
/*  94: 94 */    dest.m12 = src.m12;
/*  95: 95 */    dest.m22 = src.m22;
/*  96:    */    
/*  97: 97 */    return dest;
/*  98:    */  }
/*  99:    */  
/* 107:    */  public Matrix load(FloatBuffer buf)
/* 108:    */  {
/* 109:109 */    this.m00 = buf.get();
/* 110:110 */    this.m01 = buf.get();
/* 111:111 */    this.m02 = buf.get();
/* 112:112 */    this.m10 = buf.get();
/* 113:113 */    this.m11 = buf.get();
/* 114:114 */    this.m12 = buf.get();
/* 115:115 */    this.m20 = buf.get();
/* 116:116 */    this.m21 = buf.get();
/* 117:117 */    this.m22 = buf.get();
/* 118:    */    
/* 119:119 */    return this;
/* 120:    */  }
/* 121:    */  
/* 129:    */  public Matrix loadTranspose(FloatBuffer buf)
/* 130:    */  {
/* 131:131 */    this.m00 = buf.get();
/* 132:132 */    this.m10 = buf.get();
/* 133:133 */    this.m20 = buf.get();
/* 134:134 */    this.m01 = buf.get();
/* 135:135 */    this.m11 = buf.get();
/* 136:136 */    this.m21 = buf.get();
/* 137:137 */    this.m02 = buf.get();
/* 138:138 */    this.m12 = buf.get();
/* 139:139 */    this.m22 = buf.get();
/* 140:    */    
/* 141:141 */    return this;
/* 142:    */  }
/* 143:    */  
/* 148:    */  public Matrix store(FloatBuffer buf)
/* 149:    */  {
/* 150:150 */    buf.put(this.m00);
/* 151:151 */    buf.put(this.m01);
/* 152:152 */    buf.put(this.m02);
/* 153:153 */    buf.put(this.m10);
/* 154:154 */    buf.put(this.m11);
/* 155:155 */    buf.put(this.m12);
/* 156:156 */    buf.put(this.m20);
/* 157:157 */    buf.put(this.m21);
/* 158:158 */    buf.put(this.m22);
/* 159:159 */    return this;
/* 160:    */  }
/* 161:    */  
/* 166:    */  public Matrix storeTranspose(FloatBuffer buf)
/* 167:    */  {
/* 168:168 */    buf.put(this.m00);
/* 169:169 */    buf.put(this.m10);
/* 170:170 */    buf.put(this.m20);
/* 171:171 */    buf.put(this.m01);
/* 172:172 */    buf.put(this.m11);
/* 173:173 */    buf.put(this.m21);
/* 174:174 */    buf.put(this.m02);
/* 175:175 */    buf.put(this.m12);
/* 176:176 */    buf.put(this.m22);
/* 177:177 */    return this;
/* 178:    */  }
/* 179:    */  
/* 186:    */  public static Matrix3f add(Matrix3f left, Matrix3f right, Matrix3f dest)
/* 187:    */  {
/* 188:188 */    if (dest == null) {
/* 189:189 */      dest = new Matrix3f();
/* 190:    */    }
/* 191:191 */    left.m00 += right.m00;
/* 192:192 */    left.m01 += right.m01;
/* 193:193 */    left.m02 += right.m02;
/* 194:194 */    left.m10 += right.m10;
/* 195:195 */    left.m11 += right.m11;
/* 196:196 */    left.m12 += right.m12;
/* 197:197 */    left.m20 += right.m20;
/* 198:198 */    left.m21 += right.m21;
/* 199:199 */    left.m22 += right.m22;
/* 200:    */    
/* 201:201 */    return dest;
/* 202:    */  }
/* 203:    */  
/* 210:    */  public static Matrix3f sub(Matrix3f left, Matrix3f right, Matrix3f dest)
/* 211:    */  {
/* 212:212 */    if (dest == null) {
/* 213:213 */      dest = new Matrix3f();
/* 214:    */    }
/* 215:215 */    left.m00 -= right.m00;
/* 216:216 */    left.m01 -= right.m01;
/* 217:217 */    left.m02 -= right.m02;
/* 218:218 */    left.m10 -= right.m10;
/* 219:219 */    left.m11 -= right.m11;
/* 220:220 */    left.m12 -= right.m12;
/* 221:221 */    left.m20 -= right.m20;
/* 222:222 */    left.m21 -= right.m21;
/* 223:223 */    left.m22 -= right.m22;
/* 224:    */    
/* 225:225 */    return dest;
/* 226:    */  }
/* 227:    */  
/* 234:    */  public static Matrix3f mul(Matrix3f left, Matrix3f right, Matrix3f dest)
/* 235:    */  {
/* 236:236 */    if (dest == null) {
/* 237:237 */      dest = new Matrix3f();
/* 238:    */    }
/* 239:239 */    float m00 = left.m00 * right.m00 + left.m10 * right.m01 + left.m20 * right.m02;
/* 240:    */    
/* 241:241 */    float m01 = left.m01 * right.m00 + left.m11 * right.m01 + left.m21 * right.m02;
/* 242:    */    
/* 243:243 */    float m02 = left.m02 * right.m00 + left.m12 * right.m01 + left.m22 * right.m02;
/* 244:    */    
/* 245:245 */    float m10 = left.m00 * right.m10 + left.m10 * right.m11 + left.m20 * right.m12;
/* 246:    */    
/* 247:247 */    float m11 = left.m01 * right.m10 + left.m11 * right.m11 + left.m21 * right.m12;
/* 248:    */    
/* 249:249 */    float m12 = left.m02 * right.m10 + left.m12 * right.m11 + left.m22 * right.m12;
/* 250:    */    
/* 251:251 */    float m20 = left.m00 * right.m20 + left.m10 * right.m21 + left.m20 * right.m22;
/* 252:    */    
/* 253:253 */    float m21 = left.m01 * right.m20 + left.m11 * right.m21 + left.m21 * right.m22;
/* 254:    */    
/* 255:255 */    float m22 = left.m02 * right.m20 + left.m12 * right.m21 + left.m22 * right.m22;
/* 256:    */    
/* 258:258 */    dest.m00 = m00;
/* 259:259 */    dest.m01 = m01;
/* 260:260 */    dest.m02 = m02;
/* 261:261 */    dest.m10 = m10;
/* 262:262 */    dest.m11 = m11;
/* 263:263 */    dest.m12 = m12;
/* 264:264 */    dest.m20 = m20;
/* 265:265 */    dest.m21 = m21;
/* 266:266 */    dest.m22 = m22;
/* 267:    */    
/* 268:268 */    return dest;
/* 269:    */  }
/* 270:    */  
/* 278:    */  public static Vector3f transform(Matrix3f left, Vector3f right, Vector3f dest)
/* 279:    */  {
/* 280:280 */    if (dest == null) {
/* 281:281 */      dest = new Vector3f();
/* 282:    */    }
/* 283:283 */    float x = left.m00 * right.x + left.m10 * right.y + left.m20 * right.z;
/* 284:284 */    float y = left.m01 * right.x + left.m11 * right.y + left.m21 * right.z;
/* 285:285 */    float z = left.m02 * right.x + left.m12 * right.y + left.m22 * right.z;
/* 286:    */    
/* 287:287 */    dest.x = x;
/* 288:288 */    dest.y = y;
/* 289:289 */    dest.z = z;
/* 290:    */    
/* 291:291 */    return dest;
/* 292:    */  }
/* 293:    */  
/* 297:    */  public Matrix transpose()
/* 298:    */  {
/* 299:299 */    return transpose(this, this);
/* 300:    */  }
/* 301:    */  
/* 306:    */  public Matrix3f transpose(Matrix3f dest)
/* 307:    */  {
/* 308:308 */    return transpose(this, dest);
/* 309:    */  }
/* 310:    */  
/* 316:    */  public static Matrix3f transpose(Matrix3f src, Matrix3f dest)
/* 317:    */  {
/* 318:318 */    if (dest == null)
/* 319:319 */      dest = new Matrix3f();
/* 320:320 */    float m00 = src.m00;
/* 321:321 */    float m01 = src.m10;
/* 322:322 */    float m02 = src.m20;
/* 323:323 */    float m10 = src.m01;
/* 324:324 */    float m11 = src.m11;
/* 325:325 */    float m12 = src.m21;
/* 326:326 */    float m20 = src.m02;
/* 327:327 */    float m21 = src.m12;
/* 328:328 */    float m22 = src.m22;
/* 329:    */    
/* 330:330 */    dest.m00 = m00;
/* 331:331 */    dest.m01 = m01;
/* 332:332 */    dest.m02 = m02;
/* 333:333 */    dest.m10 = m10;
/* 334:334 */    dest.m11 = m11;
/* 335:335 */    dest.m12 = m12;
/* 336:336 */    dest.m20 = m20;
/* 337:337 */    dest.m21 = m21;
/* 338:338 */    dest.m22 = m22;
/* 339:339 */    return dest;
/* 340:    */  }
/* 341:    */  
/* 344:    */  public float determinant()
/* 345:    */  {
/* 346:346 */    float f = this.m00 * (this.m11 * this.m22 - this.m12 * this.m21) + this.m01 * (this.m12 * this.m20 - this.m10 * this.m22) + this.m02 * (this.m10 * this.m21 - this.m11 * this.m20);
/* 347:    */    
/* 350:350 */    return f;
/* 351:    */  }
/* 352:    */  
/* 355:    */  public String toString()
/* 356:    */  {
/* 357:357 */    StringBuilder buf = new StringBuilder();
/* 358:358 */    buf.append(this.m00).append(' ').append(this.m10).append(' ').append(this.m20).append(' ').append('\n');
/* 359:359 */    buf.append(this.m01).append(' ').append(this.m11).append(' ').append(this.m21).append(' ').append('\n');
/* 360:360 */    buf.append(this.m02).append(' ').append(this.m12).append(' ').append(this.m22).append(' ').append('\n');
/* 361:361 */    return buf.toString();
/* 362:    */  }
/* 363:    */  
/* 367:    */  public Matrix invert()
/* 368:    */  {
/* 369:369 */    return invert(this, this);
/* 370:    */  }
/* 371:    */  
/* 377:    */  public static Matrix3f invert(Matrix3f src, Matrix3f dest)
/* 378:    */  {
/* 379:379 */    float determinant = src.determinant();
/* 380:    */    
/* 381:381 */    if (determinant != 0.0F) {
/* 382:382 */      if (dest == null) {
/* 383:383 */        dest = new Matrix3f();
/* 384:    */      }
/* 385:    */      
/* 392:392 */      float determinant_inv = 1.0F / determinant;
/* 393:    */      
/* 395:395 */      float t00 = src.m11 * src.m22 - src.m12 * src.m21;
/* 396:396 */      float t01 = -src.m10 * src.m22 + src.m12 * src.m20;
/* 397:397 */      float t02 = src.m10 * src.m21 - src.m11 * src.m20;
/* 398:398 */      float t10 = -src.m01 * src.m22 + src.m02 * src.m21;
/* 399:399 */      float t11 = src.m00 * src.m22 - src.m02 * src.m20;
/* 400:400 */      float t12 = -src.m00 * src.m21 + src.m01 * src.m20;
/* 401:401 */      float t20 = src.m01 * src.m12 - src.m02 * src.m11;
/* 402:402 */      float t21 = -src.m00 * src.m12 + src.m02 * src.m10;
/* 403:403 */      float t22 = src.m00 * src.m11 - src.m01 * src.m10;
/* 404:    */      
/* 405:405 */      dest.m00 = (t00 * determinant_inv);
/* 406:406 */      dest.m11 = (t11 * determinant_inv);
/* 407:407 */      dest.m22 = (t22 * determinant_inv);
/* 408:408 */      dest.m01 = (t10 * determinant_inv);
/* 409:409 */      dest.m10 = (t01 * determinant_inv);
/* 410:410 */      dest.m20 = (t02 * determinant_inv);
/* 411:411 */      dest.m02 = (t20 * determinant_inv);
/* 412:412 */      dest.m12 = (t21 * determinant_inv);
/* 413:413 */      dest.m21 = (t12 * determinant_inv);
/* 414:414 */      return dest;
/* 415:    */    }
/* 416:416 */    return null;
/* 417:    */  }
/* 418:    */  
/* 423:    */  public Matrix negate()
/* 424:    */  {
/* 425:425 */    return negate(this);
/* 426:    */  }
/* 427:    */  
/* 432:    */  public Matrix3f negate(Matrix3f dest)
/* 433:    */  {
/* 434:434 */    return negate(this, dest);
/* 435:    */  }
/* 436:    */  
/* 442:    */  public static Matrix3f negate(Matrix3f src, Matrix3f dest)
/* 443:    */  {
/* 444:444 */    if (dest == null) {
/* 445:445 */      dest = new Matrix3f();
/* 446:    */    }
/* 447:447 */    dest.m00 = (-src.m00);
/* 448:448 */    dest.m01 = (-src.m02);
/* 449:449 */    dest.m02 = (-src.m01);
/* 450:450 */    dest.m10 = (-src.m10);
/* 451:451 */    dest.m11 = (-src.m12);
/* 452:452 */    dest.m12 = (-src.m11);
/* 453:453 */    dest.m20 = (-src.m20);
/* 454:454 */    dest.m21 = (-src.m22);
/* 455:455 */    dest.m22 = (-src.m21);
/* 456:456 */    return dest;
/* 457:    */  }
/* 458:    */  
/* 462:    */  public Matrix setIdentity()
/* 463:    */  {
/* 464:464 */    return setIdentity(this);
/* 465:    */  }
/* 466:    */  
/* 471:    */  public static Matrix3f setIdentity(Matrix3f m)
/* 472:    */  {
/* 473:473 */    m.m00 = 1.0F;
/* 474:474 */    m.m01 = 0.0F;
/* 475:475 */    m.m02 = 0.0F;
/* 476:476 */    m.m10 = 0.0F;
/* 477:477 */    m.m11 = 1.0F;
/* 478:478 */    m.m12 = 0.0F;
/* 479:479 */    m.m20 = 0.0F;
/* 480:480 */    m.m21 = 0.0F;
/* 481:481 */    m.m22 = 1.0F;
/* 482:482 */    return m;
/* 483:    */  }
/* 484:    */  
/* 488:    */  public Matrix setZero()
/* 489:    */  {
/* 490:490 */    return setZero(this);
/* 491:    */  }
/* 492:    */  
/* 497:    */  public static Matrix3f setZero(Matrix3f m)
/* 498:    */  {
/* 499:499 */    m.m00 = 0.0F;
/* 500:500 */    m.m01 = 0.0F;
/* 501:501 */    m.m02 = 0.0F;
/* 502:502 */    m.m10 = 0.0F;
/* 503:503 */    m.m11 = 0.0F;
/* 504:504 */    m.m12 = 0.0F;
/* 505:505 */    m.m20 = 0.0F;
/* 506:506 */    m.m21 = 0.0F;
/* 507:507 */    m.m22 = 0.0F;
/* 508:508 */    return m;
/* 509:    */  }
/* 510:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.vector.Matrix3f
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */