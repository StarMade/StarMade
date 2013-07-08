/*   1:    */package org.lwjgl.util.vector;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.nio.FloatBuffer;
/*   5:    */
/*  28:    */public class Matrix4f
/*  29:    */  extends Matrix
/*  30:    */  implements Serializable
/*  31:    */{
/*  32:    */  private static final long serialVersionUID = 1L;
/*  33:    */  public float m00;
/*  34:    */  public float m01;
/*  35:    */  public float m02;
/*  36:    */  public float m03;
/*  37:    */  public float m10;
/*  38:    */  public float m11;
/*  39:    */  public float m12;
/*  40:    */  public float m13;
/*  41:    */  public float m20;
/*  42:    */  public float m21;
/*  43:    */  public float m22;
/*  44:    */  public float m23;
/*  45:    */  public float m30;
/*  46:    */  public float m31;
/*  47:    */  public float m32;
/*  48:    */  public float m33;
/*  49:    */  
/*  50:    */  public Matrix4f()
/*  51:    */  {
/*  52: 52 */    setIdentity();
/*  53:    */  }
/*  54:    */  
/*  55:    */  public Matrix4f(Matrix4f src)
/*  56:    */  {
/*  57: 57 */    load(src);
/*  58:    */  }
/*  59:    */  
/*  62:    */  public String toString()
/*  63:    */  {
/*  64: 64 */    StringBuilder buf = new StringBuilder();
/*  65: 65 */    buf.append(this.m00).append(' ').append(this.m10).append(' ').append(this.m20).append(' ').append(this.m30).append('\n');
/*  66: 66 */    buf.append(this.m01).append(' ').append(this.m11).append(' ').append(this.m21).append(' ').append(this.m31).append('\n');
/*  67: 67 */    buf.append(this.m02).append(' ').append(this.m12).append(' ').append(this.m22).append(' ').append(this.m32).append('\n');
/*  68: 68 */    buf.append(this.m03).append(' ').append(this.m13).append(' ').append(this.m23).append(' ').append(this.m33).append('\n');
/*  69: 69 */    return buf.toString();
/*  70:    */  }
/*  71:    */  
/*  75:    */  public Matrix setIdentity()
/*  76:    */  {
/*  77: 77 */    return setIdentity(this);
/*  78:    */  }
/*  79:    */  
/*  84:    */  public static Matrix4f setIdentity(Matrix4f m)
/*  85:    */  {
/*  86: 86 */    m.m00 = 1.0F;
/*  87: 87 */    m.m01 = 0.0F;
/*  88: 88 */    m.m02 = 0.0F;
/*  89: 89 */    m.m03 = 0.0F;
/*  90: 90 */    m.m10 = 0.0F;
/*  91: 91 */    m.m11 = 1.0F;
/*  92: 92 */    m.m12 = 0.0F;
/*  93: 93 */    m.m13 = 0.0F;
/*  94: 94 */    m.m20 = 0.0F;
/*  95: 95 */    m.m21 = 0.0F;
/*  96: 96 */    m.m22 = 1.0F;
/*  97: 97 */    m.m23 = 0.0F;
/*  98: 98 */    m.m30 = 0.0F;
/*  99: 99 */    m.m31 = 0.0F;
/* 100:100 */    m.m32 = 0.0F;
/* 101:101 */    m.m33 = 1.0F;
/* 102:    */    
/* 103:103 */    return m;
/* 104:    */  }
/* 105:    */  
/* 109:    */  public Matrix setZero()
/* 110:    */  {
/* 111:111 */    return setZero(this);
/* 112:    */  }
/* 113:    */  
/* 118:    */  public static Matrix4f setZero(Matrix4f m)
/* 119:    */  {
/* 120:120 */    m.m00 = 0.0F;
/* 121:121 */    m.m01 = 0.0F;
/* 122:122 */    m.m02 = 0.0F;
/* 123:123 */    m.m03 = 0.0F;
/* 124:124 */    m.m10 = 0.0F;
/* 125:125 */    m.m11 = 0.0F;
/* 126:126 */    m.m12 = 0.0F;
/* 127:127 */    m.m13 = 0.0F;
/* 128:128 */    m.m20 = 0.0F;
/* 129:129 */    m.m21 = 0.0F;
/* 130:130 */    m.m22 = 0.0F;
/* 131:131 */    m.m23 = 0.0F;
/* 132:132 */    m.m30 = 0.0F;
/* 133:133 */    m.m31 = 0.0F;
/* 134:134 */    m.m32 = 0.0F;
/* 135:135 */    m.m33 = 0.0F;
/* 136:    */    
/* 137:137 */    return m;
/* 138:    */  }
/* 139:    */  
/* 144:    */  public Matrix4f load(Matrix4f src)
/* 145:    */  {
/* 146:146 */    return load(src, this);
/* 147:    */  }
/* 148:    */  
/* 154:    */  public static Matrix4f load(Matrix4f src, Matrix4f dest)
/* 155:    */  {
/* 156:156 */    if (dest == null)
/* 157:157 */      dest = new Matrix4f();
/* 158:158 */    dest.m00 = src.m00;
/* 159:159 */    dest.m01 = src.m01;
/* 160:160 */    dest.m02 = src.m02;
/* 161:161 */    dest.m03 = src.m03;
/* 162:162 */    dest.m10 = src.m10;
/* 163:163 */    dest.m11 = src.m11;
/* 164:164 */    dest.m12 = src.m12;
/* 165:165 */    dest.m13 = src.m13;
/* 166:166 */    dest.m20 = src.m20;
/* 167:167 */    dest.m21 = src.m21;
/* 168:168 */    dest.m22 = src.m22;
/* 169:169 */    dest.m23 = src.m23;
/* 170:170 */    dest.m30 = src.m30;
/* 171:171 */    dest.m31 = src.m31;
/* 172:172 */    dest.m32 = src.m32;
/* 173:173 */    dest.m33 = src.m33;
/* 174:    */    
/* 175:175 */    return dest;
/* 176:    */  }
/* 177:    */  
/* 185:    */  public Matrix load(FloatBuffer buf)
/* 186:    */  {
/* 187:187 */    this.m00 = buf.get();
/* 188:188 */    this.m01 = buf.get();
/* 189:189 */    this.m02 = buf.get();
/* 190:190 */    this.m03 = buf.get();
/* 191:191 */    this.m10 = buf.get();
/* 192:192 */    this.m11 = buf.get();
/* 193:193 */    this.m12 = buf.get();
/* 194:194 */    this.m13 = buf.get();
/* 195:195 */    this.m20 = buf.get();
/* 196:196 */    this.m21 = buf.get();
/* 197:197 */    this.m22 = buf.get();
/* 198:198 */    this.m23 = buf.get();
/* 199:199 */    this.m30 = buf.get();
/* 200:200 */    this.m31 = buf.get();
/* 201:201 */    this.m32 = buf.get();
/* 202:202 */    this.m33 = buf.get();
/* 203:    */    
/* 204:204 */    return this;
/* 205:    */  }
/* 206:    */  
/* 214:    */  public Matrix loadTranspose(FloatBuffer buf)
/* 215:    */  {
/* 216:216 */    this.m00 = buf.get();
/* 217:217 */    this.m10 = buf.get();
/* 218:218 */    this.m20 = buf.get();
/* 219:219 */    this.m30 = buf.get();
/* 220:220 */    this.m01 = buf.get();
/* 221:221 */    this.m11 = buf.get();
/* 222:222 */    this.m21 = buf.get();
/* 223:223 */    this.m31 = buf.get();
/* 224:224 */    this.m02 = buf.get();
/* 225:225 */    this.m12 = buf.get();
/* 226:226 */    this.m22 = buf.get();
/* 227:227 */    this.m32 = buf.get();
/* 228:228 */    this.m03 = buf.get();
/* 229:229 */    this.m13 = buf.get();
/* 230:230 */    this.m23 = buf.get();
/* 231:231 */    this.m33 = buf.get();
/* 232:    */    
/* 233:233 */    return this;
/* 234:    */  }
/* 235:    */  
/* 240:    */  public Matrix store(FloatBuffer buf)
/* 241:    */  {
/* 242:242 */    buf.put(this.m00);
/* 243:243 */    buf.put(this.m01);
/* 244:244 */    buf.put(this.m02);
/* 245:245 */    buf.put(this.m03);
/* 246:246 */    buf.put(this.m10);
/* 247:247 */    buf.put(this.m11);
/* 248:248 */    buf.put(this.m12);
/* 249:249 */    buf.put(this.m13);
/* 250:250 */    buf.put(this.m20);
/* 251:251 */    buf.put(this.m21);
/* 252:252 */    buf.put(this.m22);
/* 253:253 */    buf.put(this.m23);
/* 254:254 */    buf.put(this.m30);
/* 255:255 */    buf.put(this.m31);
/* 256:256 */    buf.put(this.m32);
/* 257:257 */    buf.put(this.m33);
/* 258:258 */    return this;
/* 259:    */  }
/* 260:    */  
/* 265:    */  public Matrix storeTranspose(FloatBuffer buf)
/* 266:    */  {
/* 267:267 */    buf.put(this.m00);
/* 268:268 */    buf.put(this.m10);
/* 269:269 */    buf.put(this.m20);
/* 270:270 */    buf.put(this.m30);
/* 271:271 */    buf.put(this.m01);
/* 272:272 */    buf.put(this.m11);
/* 273:273 */    buf.put(this.m21);
/* 274:274 */    buf.put(this.m31);
/* 275:275 */    buf.put(this.m02);
/* 276:276 */    buf.put(this.m12);
/* 277:277 */    buf.put(this.m22);
/* 278:278 */    buf.put(this.m32);
/* 279:279 */    buf.put(this.m03);
/* 280:280 */    buf.put(this.m13);
/* 281:281 */    buf.put(this.m23);
/* 282:282 */    buf.put(this.m33);
/* 283:283 */    return this;
/* 284:    */  }
/* 285:    */  
/* 290:    */  public Matrix store3f(FloatBuffer buf)
/* 291:    */  {
/* 292:292 */    buf.put(this.m00);
/* 293:293 */    buf.put(this.m01);
/* 294:294 */    buf.put(this.m02);
/* 295:295 */    buf.put(this.m10);
/* 296:296 */    buf.put(this.m11);
/* 297:297 */    buf.put(this.m12);
/* 298:298 */    buf.put(this.m20);
/* 299:299 */    buf.put(this.m21);
/* 300:300 */    buf.put(this.m22);
/* 301:301 */    return this;
/* 302:    */  }
/* 303:    */  
/* 310:    */  public static Matrix4f add(Matrix4f left, Matrix4f right, Matrix4f dest)
/* 311:    */  {
/* 312:312 */    if (dest == null) {
/* 313:313 */      dest = new Matrix4f();
/* 314:    */    }
/* 315:315 */    left.m00 += right.m00;
/* 316:316 */    left.m01 += right.m01;
/* 317:317 */    left.m02 += right.m02;
/* 318:318 */    left.m03 += right.m03;
/* 319:319 */    left.m10 += right.m10;
/* 320:320 */    left.m11 += right.m11;
/* 321:321 */    left.m12 += right.m12;
/* 322:322 */    left.m13 += right.m13;
/* 323:323 */    left.m20 += right.m20;
/* 324:324 */    left.m21 += right.m21;
/* 325:325 */    left.m22 += right.m22;
/* 326:326 */    left.m23 += right.m23;
/* 327:327 */    left.m30 += right.m30;
/* 328:328 */    left.m31 += right.m31;
/* 329:329 */    left.m32 += right.m32;
/* 330:330 */    left.m33 += right.m33;
/* 331:    */    
/* 332:332 */    return dest;
/* 333:    */  }
/* 334:    */  
/* 341:    */  public static Matrix4f sub(Matrix4f left, Matrix4f right, Matrix4f dest)
/* 342:    */  {
/* 343:343 */    if (dest == null) {
/* 344:344 */      dest = new Matrix4f();
/* 345:    */    }
/* 346:346 */    left.m00 -= right.m00;
/* 347:347 */    left.m01 -= right.m01;
/* 348:348 */    left.m02 -= right.m02;
/* 349:349 */    left.m03 -= right.m03;
/* 350:350 */    left.m10 -= right.m10;
/* 351:351 */    left.m11 -= right.m11;
/* 352:352 */    left.m12 -= right.m12;
/* 353:353 */    left.m13 -= right.m13;
/* 354:354 */    left.m20 -= right.m20;
/* 355:355 */    left.m21 -= right.m21;
/* 356:356 */    left.m22 -= right.m22;
/* 357:357 */    left.m23 -= right.m23;
/* 358:358 */    left.m30 -= right.m30;
/* 359:359 */    left.m31 -= right.m31;
/* 360:360 */    left.m32 -= right.m32;
/* 361:361 */    left.m33 -= right.m33;
/* 362:    */    
/* 363:363 */    return dest;
/* 364:    */  }
/* 365:    */  
/* 372:    */  public static Matrix4f mul(Matrix4f left, Matrix4f right, Matrix4f dest)
/* 373:    */  {
/* 374:374 */    if (dest == null) {
/* 375:375 */      dest = new Matrix4f();
/* 376:    */    }
/* 377:377 */    float m00 = left.m00 * right.m00 + left.m10 * right.m01 + left.m20 * right.m02 + left.m30 * right.m03;
/* 378:378 */    float m01 = left.m01 * right.m00 + left.m11 * right.m01 + left.m21 * right.m02 + left.m31 * right.m03;
/* 379:379 */    float m02 = left.m02 * right.m00 + left.m12 * right.m01 + left.m22 * right.m02 + left.m32 * right.m03;
/* 380:380 */    float m03 = left.m03 * right.m00 + left.m13 * right.m01 + left.m23 * right.m02 + left.m33 * right.m03;
/* 381:381 */    float m10 = left.m00 * right.m10 + left.m10 * right.m11 + left.m20 * right.m12 + left.m30 * right.m13;
/* 382:382 */    float m11 = left.m01 * right.m10 + left.m11 * right.m11 + left.m21 * right.m12 + left.m31 * right.m13;
/* 383:383 */    float m12 = left.m02 * right.m10 + left.m12 * right.m11 + left.m22 * right.m12 + left.m32 * right.m13;
/* 384:384 */    float m13 = left.m03 * right.m10 + left.m13 * right.m11 + left.m23 * right.m12 + left.m33 * right.m13;
/* 385:385 */    float m20 = left.m00 * right.m20 + left.m10 * right.m21 + left.m20 * right.m22 + left.m30 * right.m23;
/* 386:386 */    float m21 = left.m01 * right.m20 + left.m11 * right.m21 + left.m21 * right.m22 + left.m31 * right.m23;
/* 387:387 */    float m22 = left.m02 * right.m20 + left.m12 * right.m21 + left.m22 * right.m22 + left.m32 * right.m23;
/* 388:388 */    float m23 = left.m03 * right.m20 + left.m13 * right.m21 + left.m23 * right.m22 + left.m33 * right.m23;
/* 389:389 */    float m30 = left.m00 * right.m30 + left.m10 * right.m31 + left.m20 * right.m32 + left.m30 * right.m33;
/* 390:390 */    float m31 = left.m01 * right.m30 + left.m11 * right.m31 + left.m21 * right.m32 + left.m31 * right.m33;
/* 391:391 */    float m32 = left.m02 * right.m30 + left.m12 * right.m31 + left.m22 * right.m32 + left.m32 * right.m33;
/* 392:392 */    float m33 = left.m03 * right.m30 + left.m13 * right.m31 + left.m23 * right.m32 + left.m33 * right.m33;
/* 393:    */    
/* 394:394 */    dest.m00 = m00;
/* 395:395 */    dest.m01 = m01;
/* 396:396 */    dest.m02 = m02;
/* 397:397 */    dest.m03 = m03;
/* 398:398 */    dest.m10 = m10;
/* 399:399 */    dest.m11 = m11;
/* 400:400 */    dest.m12 = m12;
/* 401:401 */    dest.m13 = m13;
/* 402:402 */    dest.m20 = m20;
/* 403:403 */    dest.m21 = m21;
/* 404:404 */    dest.m22 = m22;
/* 405:405 */    dest.m23 = m23;
/* 406:406 */    dest.m30 = m30;
/* 407:407 */    dest.m31 = m31;
/* 408:408 */    dest.m32 = m32;
/* 409:409 */    dest.m33 = m33;
/* 410:    */    
/* 411:411 */    return dest;
/* 412:    */  }
/* 413:    */  
/* 421:    */  public static Vector4f transform(Matrix4f left, Vector4f right, Vector4f dest)
/* 422:    */  {
/* 423:423 */    if (dest == null) {
/* 424:424 */      dest = new Vector4f();
/* 425:    */    }
/* 426:426 */    float x = left.m00 * right.x + left.m10 * right.y + left.m20 * right.z + left.m30 * right.w;
/* 427:427 */    float y = left.m01 * right.x + left.m11 * right.y + left.m21 * right.z + left.m31 * right.w;
/* 428:428 */    float z = left.m02 * right.x + left.m12 * right.y + left.m22 * right.z + left.m32 * right.w;
/* 429:429 */    float w = left.m03 * right.x + left.m13 * right.y + left.m23 * right.z + left.m33 * right.w;
/* 430:    */    
/* 431:431 */    dest.x = x;
/* 432:432 */    dest.y = y;
/* 433:433 */    dest.z = z;
/* 434:434 */    dest.w = w;
/* 435:    */    
/* 436:436 */    return dest;
/* 437:    */  }
/* 438:    */  
/* 442:    */  public Matrix transpose()
/* 443:    */  {
/* 444:444 */    return transpose(this);
/* 445:    */  }
/* 446:    */  
/* 451:    */  public Matrix4f translate(Vector2f vec)
/* 452:    */  {
/* 453:453 */    return translate(vec, this);
/* 454:    */  }
/* 455:    */  
/* 460:    */  public Matrix4f translate(Vector3f vec)
/* 461:    */  {
/* 462:462 */    return translate(vec, this);
/* 463:    */  }
/* 464:    */  
/* 469:    */  public Matrix4f scale(Vector3f vec)
/* 470:    */  {
/* 471:471 */    return scale(vec, this, this);
/* 472:    */  }
/* 473:    */  
/* 480:    */  public static Matrix4f scale(Vector3f vec, Matrix4f src, Matrix4f dest)
/* 481:    */  {
/* 482:482 */    if (dest == null)
/* 483:483 */      dest = new Matrix4f();
/* 484:484 */    src.m00 *= vec.x;
/* 485:485 */    src.m01 *= vec.x;
/* 486:486 */    src.m02 *= vec.x;
/* 487:487 */    src.m03 *= vec.x;
/* 488:488 */    src.m10 *= vec.y;
/* 489:489 */    src.m11 *= vec.y;
/* 490:490 */    src.m12 *= vec.y;
/* 491:491 */    src.m13 *= vec.y;
/* 492:492 */    src.m20 *= vec.z;
/* 493:493 */    src.m21 *= vec.z;
/* 494:494 */    src.m22 *= vec.z;
/* 495:495 */    src.m23 *= vec.z;
/* 496:496 */    return dest;
/* 497:    */  }
/* 498:    */  
/* 504:    */  public Matrix4f rotate(float angle, Vector3f axis)
/* 505:    */  {
/* 506:506 */    return rotate(angle, axis, this);
/* 507:    */  }
/* 508:    */  
/* 515:    */  public Matrix4f rotate(float angle, Vector3f axis, Matrix4f dest)
/* 516:    */  {
/* 517:517 */    return rotate(angle, axis, this, dest);
/* 518:    */  }
/* 519:    */  
/* 528:    */  public static Matrix4f rotate(float angle, Vector3f axis, Matrix4f src, Matrix4f dest)
/* 529:    */  {
/* 530:530 */    if (dest == null)
/* 531:531 */      dest = new Matrix4f();
/* 532:532 */    float c = (float)Math.cos(angle);
/* 533:533 */    float s = (float)Math.sin(angle);
/* 534:534 */    float oneminusc = 1.0F - c;
/* 535:535 */    float xy = axis.x * axis.y;
/* 536:536 */    float yz = axis.y * axis.z;
/* 537:537 */    float xz = axis.x * axis.z;
/* 538:538 */    float xs = axis.x * s;
/* 539:539 */    float ys = axis.y * s;
/* 540:540 */    float zs = axis.z * s;
/* 541:    */    
/* 542:542 */    float f00 = axis.x * axis.x * oneminusc + c;
/* 543:543 */    float f01 = xy * oneminusc + zs;
/* 544:544 */    float f02 = xz * oneminusc - ys;
/* 545:    */    
/* 546:546 */    float f10 = xy * oneminusc - zs;
/* 547:547 */    float f11 = axis.y * axis.y * oneminusc + c;
/* 548:548 */    float f12 = yz * oneminusc + xs;
/* 549:    */    
/* 550:550 */    float f20 = xz * oneminusc + ys;
/* 551:551 */    float f21 = yz * oneminusc - xs;
/* 552:552 */    float f22 = axis.z * axis.z * oneminusc + c;
/* 553:    */    
/* 554:554 */    float t00 = src.m00 * f00 + src.m10 * f01 + src.m20 * f02;
/* 555:555 */    float t01 = src.m01 * f00 + src.m11 * f01 + src.m21 * f02;
/* 556:556 */    float t02 = src.m02 * f00 + src.m12 * f01 + src.m22 * f02;
/* 557:557 */    float t03 = src.m03 * f00 + src.m13 * f01 + src.m23 * f02;
/* 558:558 */    float t10 = src.m00 * f10 + src.m10 * f11 + src.m20 * f12;
/* 559:559 */    float t11 = src.m01 * f10 + src.m11 * f11 + src.m21 * f12;
/* 560:560 */    float t12 = src.m02 * f10 + src.m12 * f11 + src.m22 * f12;
/* 561:561 */    float t13 = src.m03 * f10 + src.m13 * f11 + src.m23 * f12;
/* 562:562 */    dest.m20 = (src.m00 * f20 + src.m10 * f21 + src.m20 * f22);
/* 563:563 */    dest.m21 = (src.m01 * f20 + src.m11 * f21 + src.m21 * f22);
/* 564:564 */    dest.m22 = (src.m02 * f20 + src.m12 * f21 + src.m22 * f22);
/* 565:565 */    dest.m23 = (src.m03 * f20 + src.m13 * f21 + src.m23 * f22);
/* 566:566 */    dest.m00 = t00;
/* 567:567 */    dest.m01 = t01;
/* 568:568 */    dest.m02 = t02;
/* 569:569 */    dest.m03 = t03;
/* 570:570 */    dest.m10 = t10;
/* 571:571 */    dest.m11 = t11;
/* 572:572 */    dest.m12 = t12;
/* 573:573 */    dest.m13 = t13;
/* 574:574 */    return dest;
/* 575:    */  }
/* 576:    */  
/* 582:    */  public Matrix4f translate(Vector3f vec, Matrix4f dest)
/* 583:    */  {
/* 584:584 */    return translate(vec, this, dest);
/* 585:    */  }
/* 586:    */  
/* 593:    */  public static Matrix4f translate(Vector3f vec, Matrix4f src, Matrix4f dest)
/* 594:    */  {
/* 595:595 */    if (dest == null) {
/* 596:596 */      dest = new Matrix4f();
/* 597:    */    }
/* 598:598 */    dest.m30 += src.m00 * vec.x + src.m10 * vec.y + src.m20 * vec.z;
/* 599:599 */    dest.m31 += src.m01 * vec.x + src.m11 * vec.y + src.m21 * vec.z;
/* 600:600 */    dest.m32 += src.m02 * vec.x + src.m12 * vec.y + src.m22 * vec.z;
/* 601:601 */    dest.m33 += src.m03 * vec.x + src.m13 * vec.y + src.m23 * vec.z;
/* 602:    */    
/* 603:603 */    return dest;
/* 604:    */  }
/* 605:    */  
/* 611:    */  public Matrix4f translate(Vector2f vec, Matrix4f dest)
/* 612:    */  {
/* 613:613 */    return translate(vec, this, dest);
/* 614:    */  }
/* 615:    */  
/* 622:    */  public static Matrix4f translate(Vector2f vec, Matrix4f src, Matrix4f dest)
/* 623:    */  {
/* 624:624 */    if (dest == null) {
/* 625:625 */      dest = new Matrix4f();
/* 626:    */    }
/* 627:627 */    dest.m30 += src.m00 * vec.x + src.m10 * vec.y;
/* 628:628 */    dest.m31 += src.m01 * vec.x + src.m11 * vec.y;
/* 629:629 */    dest.m32 += src.m02 * vec.x + src.m12 * vec.y;
/* 630:630 */    dest.m33 += src.m03 * vec.x + src.m13 * vec.y;
/* 631:    */    
/* 632:632 */    return dest;
/* 633:    */  }
/* 634:    */  
/* 639:    */  public Matrix4f transpose(Matrix4f dest)
/* 640:    */  {
/* 641:641 */    return transpose(this, dest);
/* 642:    */  }
/* 643:    */  
/* 649:    */  public static Matrix4f transpose(Matrix4f src, Matrix4f dest)
/* 650:    */  {
/* 651:651 */    if (dest == null)
/* 652:652 */      dest = new Matrix4f();
/* 653:653 */    float m00 = src.m00;
/* 654:654 */    float m01 = src.m10;
/* 655:655 */    float m02 = src.m20;
/* 656:656 */    float m03 = src.m30;
/* 657:657 */    float m10 = src.m01;
/* 658:658 */    float m11 = src.m11;
/* 659:659 */    float m12 = src.m21;
/* 660:660 */    float m13 = src.m31;
/* 661:661 */    float m20 = src.m02;
/* 662:662 */    float m21 = src.m12;
/* 663:663 */    float m22 = src.m22;
/* 664:664 */    float m23 = src.m32;
/* 665:665 */    float m30 = src.m03;
/* 666:666 */    float m31 = src.m13;
/* 667:667 */    float m32 = src.m23;
/* 668:668 */    float m33 = src.m33;
/* 669:    */    
/* 670:670 */    dest.m00 = m00;
/* 671:671 */    dest.m01 = m01;
/* 672:672 */    dest.m02 = m02;
/* 673:673 */    dest.m03 = m03;
/* 674:674 */    dest.m10 = m10;
/* 675:675 */    dest.m11 = m11;
/* 676:676 */    dest.m12 = m12;
/* 677:677 */    dest.m13 = m13;
/* 678:678 */    dest.m20 = m20;
/* 679:679 */    dest.m21 = m21;
/* 680:680 */    dest.m22 = m22;
/* 681:681 */    dest.m23 = m23;
/* 682:682 */    dest.m30 = m30;
/* 683:683 */    dest.m31 = m31;
/* 684:684 */    dest.m32 = m32;
/* 685:685 */    dest.m33 = m33;
/* 686:    */    
/* 687:687 */    return dest;
/* 688:    */  }
/* 689:    */  
/* 692:    */  public float determinant()
/* 693:    */  {
/* 694:694 */    float f = this.m00 * (this.m11 * this.m22 * this.m33 + this.m12 * this.m23 * this.m31 + this.m13 * this.m21 * this.m32 - this.m13 * this.m22 * this.m31 - this.m11 * this.m23 * this.m32 - this.m12 * this.m21 * this.m33);
/* 695:    */    
/* 700:700 */    f -= this.m01 * (this.m10 * this.m22 * this.m33 + this.m12 * this.m23 * this.m30 + this.m13 * this.m20 * this.m32 - this.m13 * this.m22 * this.m30 - this.m10 * this.m23 * this.m32 - this.m12 * this.m20 * this.m33);
/* 701:    */    
/* 705:705 */    f += this.m02 * (this.m10 * this.m21 * this.m33 + this.m11 * this.m23 * this.m30 + this.m13 * this.m20 * this.m31 - this.m13 * this.m21 * this.m30 - this.m10 * this.m23 * this.m31 - this.m11 * this.m20 * this.m33);
/* 706:    */    
/* 710:710 */    f -= this.m03 * (this.m10 * this.m21 * this.m32 + this.m11 * this.m22 * this.m30 + this.m12 * this.m20 * this.m31 - this.m12 * this.m21 * this.m30 - this.m10 * this.m22 * this.m31 - this.m11 * this.m20 * this.m32);
/* 711:    */    
/* 715:715 */    return f;
/* 716:    */  }
/* 717:    */  
/* 725:    */  private static float determinant3x3(float t00, float t01, float t02, float t10, float t11, float t12, float t20, float t21, float t22)
/* 726:    */  {
/* 727:727 */    return t00 * (t11 * t22 - t12 * t21) + t01 * (t12 * t20 - t10 * t22) + t02 * (t10 * t21 - t11 * t20);
/* 728:    */  }
/* 729:    */  
/* 735:    */  public Matrix invert()
/* 736:    */  {
/* 737:737 */    return invert(this, this);
/* 738:    */  }
/* 739:    */  
/* 745:    */  public static Matrix4f invert(Matrix4f src, Matrix4f dest)
/* 746:    */  {
/* 747:747 */    float determinant = src.determinant();
/* 748:    */    
/* 749:749 */    if (determinant != 0.0F)
/* 750:    */    {
/* 756:756 */      if (dest == null)
/* 757:757 */        dest = new Matrix4f();
/* 758:758 */      float determinant_inv = 1.0F / determinant;
/* 759:    */      
/* 761:761 */      float t00 = determinant3x3(src.m11, src.m12, src.m13, src.m21, src.m22, src.m23, src.m31, src.m32, src.m33);
/* 762:762 */      float t01 = -determinant3x3(src.m10, src.m12, src.m13, src.m20, src.m22, src.m23, src.m30, src.m32, src.m33);
/* 763:763 */      float t02 = determinant3x3(src.m10, src.m11, src.m13, src.m20, src.m21, src.m23, src.m30, src.m31, src.m33);
/* 764:764 */      float t03 = -determinant3x3(src.m10, src.m11, src.m12, src.m20, src.m21, src.m22, src.m30, src.m31, src.m32);
/* 765:    */      
/* 766:766 */      float t10 = -determinant3x3(src.m01, src.m02, src.m03, src.m21, src.m22, src.m23, src.m31, src.m32, src.m33);
/* 767:767 */      float t11 = determinant3x3(src.m00, src.m02, src.m03, src.m20, src.m22, src.m23, src.m30, src.m32, src.m33);
/* 768:768 */      float t12 = -determinant3x3(src.m00, src.m01, src.m03, src.m20, src.m21, src.m23, src.m30, src.m31, src.m33);
/* 769:769 */      float t13 = determinant3x3(src.m00, src.m01, src.m02, src.m20, src.m21, src.m22, src.m30, src.m31, src.m32);
/* 770:    */      
/* 771:771 */      float t20 = determinant3x3(src.m01, src.m02, src.m03, src.m11, src.m12, src.m13, src.m31, src.m32, src.m33);
/* 772:772 */      float t21 = -determinant3x3(src.m00, src.m02, src.m03, src.m10, src.m12, src.m13, src.m30, src.m32, src.m33);
/* 773:773 */      float t22 = determinant3x3(src.m00, src.m01, src.m03, src.m10, src.m11, src.m13, src.m30, src.m31, src.m33);
/* 774:774 */      float t23 = -determinant3x3(src.m00, src.m01, src.m02, src.m10, src.m11, src.m12, src.m30, src.m31, src.m32);
/* 775:    */      
/* 776:776 */      float t30 = -determinant3x3(src.m01, src.m02, src.m03, src.m11, src.m12, src.m13, src.m21, src.m22, src.m23);
/* 777:777 */      float t31 = determinant3x3(src.m00, src.m02, src.m03, src.m10, src.m12, src.m13, src.m20, src.m22, src.m23);
/* 778:778 */      float t32 = -determinant3x3(src.m00, src.m01, src.m03, src.m10, src.m11, src.m13, src.m20, src.m21, src.m23);
/* 779:779 */      float t33 = determinant3x3(src.m00, src.m01, src.m02, src.m10, src.m11, src.m12, src.m20, src.m21, src.m22);
/* 780:    */      
/* 782:782 */      dest.m00 = (t00 * determinant_inv);
/* 783:783 */      dest.m11 = (t11 * determinant_inv);
/* 784:784 */      dest.m22 = (t22 * determinant_inv);
/* 785:785 */      dest.m33 = (t33 * determinant_inv);
/* 786:786 */      dest.m01 = (t10 * determinant_inv);
/* 787:787 */      dest.m10 = (t01 * determinant_inv);
/* 788:788 */      dest.m20 = (t02 * determinant_inv);
/* 789:789 */      dest.m02 = (t20 * determinant_inv);
/* 790:790 */      dest.m12 = (t21 * determinant_inv);
/* 791:791 */      dest.m21 = (t12 * determinant_inv);
/* 792:792 */      dest.m03 = (t30 * determinant_inv);
/* 793:793 */      dest.m30 = (t03 * determinant_inv);
/* 794:794 */      dest.m13 = (t31 * determinant_inv);
/* 795:795 */      dest.m31 = (t13 * determinant_inv);
/* 796:796 */      dest.m32 = (t23 * determinant_inv);
/* 797:797 */      dest.m23 = (t32 * determinant_inv);
/* 798:798 */      return dest;
/* 799:    */    }
/* 800:800 */    return null;
/* 801:    */  }
/* 802:    */  
/* 806:    */  public Matrix negate()
/* 807:    */  {
/* 808:808 */    return negate(this);
/* 809:    */  }
/* 810:    */  
/* 815:    */  public Matrix4f negate(Matrix4f dest)
/* 816:    */  {
/* 817:817 */    return negate(this, dest);
/* 818:    */  }
/* 819:    */  
/* 825:    */  public static Matrix4f negate(Matrix4f src, Matrix4f dest)
/* 826:    */  {
/* 827:827 */    if (dest == null) {
/* 828:828 */      dest = new Matrix4f();
/* 829:    */    }
/* 830:830 */    dest.m00 = (-src.m00);
/* 831:831 */    dest.m01 = (-src.m01);
/* 832:832 */    dest.m02 = (-src.m02);
/* 833:833 */    dest.m03 = (-src.m03);
/* 834:834 */    dest.m10 = (-src.m10);
/* 835:835 */    dest.m11 = (-src.m11);
/* 836:836 */    dest.m12 = (-src.m12);
/* 837:837 */    dest.m13 = (-src.m13);
/* 838:838 */    dest.m20 = (-src.m20);
/* 839:839 */    dest.m21 = (-src.m21);
/* 840:840 */    dest.m22 = (-src.m22);
/* 841:841 */    dest.m23 = (-src.m23);
/* 842:842 */    dest.m30 = (-src.m30);
/* 843:843 */    dest.m31 = (-src.m31);
/* 844:844 */    dest.m32 = (-src.m32);
/* 845:845 */    dest.m33 = (-src.m33);
/* 846:    */    
/* 847:847 */    return dest;
/* 848:    */  }
/* 849:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.vector.Matrix4f
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */