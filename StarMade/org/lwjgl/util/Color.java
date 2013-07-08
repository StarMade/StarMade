/*   1:    */package org.lwjgl.util;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.nio.ByteBuffer;
/*   5:    */
/*  42:    */public final class Color
/*  43:    */  implements ReadableColor, Serializable, WritableColor
/*  44:    */{
/*  45:    */  static final long serialVersionUID = 1L;
/*  46:    */  private byte red;
/*  47:    */  private byte green;
/*  48:    */  private byte blue;
/*  49:    */  private byte alpha;
/*  50:    */  
/*  51:    */  public Color()
/*  52:    */  {
/*  53: 53 */    this(0, 0, 0, 255);
/*  54:    */  }
/*  55:    */  
/*  58:    */  public Color(int r, int g, int b)
/*  59:    */  {
/*  60: 60 */    this(r, g, b, 255);
/*  61:    */  }
/*  62:    */  
/*  65:    */  public Color(byte r, byte g, byte b)
/*  66:    */  {
/*  67: 67 */    this(r, g, b, (byte)-1);
/*  68:    */  }
/*  69:    */  
/*  72:    */  public Color(int r, int g, int b, int a)
/*  73:    */  {
/*  74: 74 */    set(r, g, b, a);
/*  75:    */  }
/*  76:    */  
/*  79:    */  public Color(byte r, byte g, byte b, byte a)
/*  80:    */  {
/*  81: 81 */    set(r, g, b, a);
/*  82:    */  }
/*  83:    */  
/*  86:    */  public Color(ReadableColor c)
/*  87:    */  {
/*  88: 88 */    setColor(c);
/*  89:    */  }
/*  90:    */  
/*  93:    */  public void set(int r, int g, int b, int a)
/*  94:    */  {
/*  95: 95 */    this.red = ((byte)r);
/*  96: 96 */    this.green = ((byte)g);
/*  97: 97 */    this.blue = ((byte)b);
/*  98: 98 */    this.alpha = ((byte)a);
/*  99:    */  }
/* 100:    */  
/* 103:    */  public void set(byte r, byte g, byte b, byte a)
/* 104:    */  {
/* 105:105 */    this.red = r;
/* 106:106 */    this.green = g;
/* 107:107 */    this.blue = b;
/* 108:108 */    this.alpha = a;
/* 109:    */  }
/* 110:    */  
/* 113:    */  public void set(int r, int g, int b)
/* 114:    */  {
/* 115:115 */    set(r, g, b, 255);
/* 116:    */  }
/* 117:    */  
/* 120:    */  public void set(byte r, byte g, byte b)
/* 121:    */  {
/* 122:122 */    set(r, g, b, (byte)-1);
/* 123:    */  }
/* 124:    */  
/* 127:    */  public int getRed()
/* 128:    */  {
/* 129:129 */    return this.red & 0xFF;
/* 130:    */  }
/* 131:    */  
/* 134:    */  public int getGreen()
/* 135:    */  {
/* 136:136 */    return this.green & 0xFF;
/* 137:    */  }
/* 138:    */  
/* 141:    */  public int getBlue()
/* 142:    */  {
/* 143:143 */    return this.blue & 0xFF;
/* 144:    */  }
/* 145:    */  
/* 148:    */  public int getAlpha()
/* 149:    */  {
/* 150:150 */    return this.alpha & 0xFF;
/* 151:    */  }
/* 152:    */  
/* 155:    */  public void setRed(int red)
/* 156:    */  {
/* 157:157 */    this.red = ((byte)red);
/* 158:    */  }
/* 159:    */  
/* 162:    */  public void setGreen(int green)
/* 163:    */  {
/* 164:164 */    this.green = ((byte)green);
/* 165:    */  }
/* 166:    */  
/* 169:    */  public void setBlue(int blue)
/* 170:    */  {
/* 171:171 */    this.blue = ((byte)blue);
/* 172:    */  }
/* 173:    */  
/* 176:    */  public void setAlpha(int alpha)
/* 177:    */  {
/* 178:178 */    this.alpha = ((byte)alpha);
/* 179:    */  }
/* 180:    */  
/* 183:    */  public void setRed(byte red)
/* 184:    */  {
/* 185:185 */    this.red = red;
/* 186:    */  }
/* 187:    */  
/* 190:    */  public void setGreen(byte green)
/* 191:    */  {
/* 192:192 */    this.green = green;
/* 193:    */  }
/* 194:    */  
/* 197:    */  public void setBlue(byte blue)
/* 198:    */  {
/* 199:199 */    this.blue = blue;
/* 200:    */  }
/* 201:    */  
/* 204:    */  public void setAlpha(byte alpha)
/* 205:    */  {
/* 206:206 */    this.alpha = alpha;
/* 207:    */  }
/* 208:    */  
/* 211:    */  public String toString()
/* 212:    */  {
/* 213:213 */    return "Color [" + getRed() + ", " + getGreen() + ", " + getBlue() + ", " + getAlpha() + "]";
/* 214:    */  }
/* 215:    */  
/* 218:    */  public boolean equals(Object o)
/* 219:    */  {
/* 220:220 */    return (o != null) && ((o instanceof ReadableColor)) && (((ReadableColor)o).getRed() == getRed()) && (((ReadableColor)o).getGreen() == getGreen()) && (((ReadableColor)o).getBlue() == getBlue()) && (((ReadableColor)o).getAlpha() == getAlpha());
/* 221:    */  }
/* 222:    */  
/* 230:    */  public int hashCode()
/* 231:    */  {
/* 232:232 */    return this.red << 24 | this.green << 16 | this.blue << 8 | this.alpha;
/* 233:    */  }
/* 234:    */  
/* 237:    */  public byte getAlphaByte()
/* 238:    */  {
/* 239:239 */    return this.alpha;
/* 240:    */  }
/* 241:    */  
/* 244:    */  public byte getBlueByte()
/* 245:    */  {
/* 246:246 */    return this.blue;
/* 247:    */  }
/* 248:    */  
/* 251:    */  public byte getGreenByte()
/* 252:    */  {
/* 253:253 */    return this.green;
/* 254:    */  }
/* 255:    */  
/* 258:    */  public byte getRedByte()
/* 259:    */  {
/* 260:260 */    return this.red;
/* 261:    */  }
/* 262:    */  
/* 265:    */  public void writeRGBA(ByteBuffer dest)
/* 266:    */  {
/* 267:267 */    dest.put(this.red);
/* 268:268 */    dest.put(this.green);
/* 269:269 */    dest.put(this.blue);
/* 270:270 */    dest.put(this.alpha);
/* 271:    */  }
/* 272:    */  
/* 275:    */  public void writeRGB(ByteBuffer dest)
/* 276:    */  {
/* 277:277 */    dest.put(this.red);
/* 278:278 */    dest.put(this.green);
/* 279:279 */    dest.put(this.blue);
/* 280:    */  }
/* 281:    */  
/* 284:    */  public void writeABGR(ByteBuffer dest)
/* 285:    */  {
/* 286:286 */    dest.put(this.alpha);
/* 287:287 */    dest.put(this.blue);
/* 288:288 */    dest.put(this.green);
/* 289:289 */    dest.put(this.red);
/* 290:    */  }
/* 291:    */  
/* 294:    */  public void writeARGB(ByteBuffer dest)
/* 295:    */  {
/* 296:296 */    dest.put(this.alpha);
/* 297:297 */    dest.put(this.red);
/* 298:298 */    dest.put(this.green);
/* 299:299 */    dest.put(this.blue);
/* 300:    */  }
/* 301:    */  
/* 304:    */  public void writeBGR(ByteBuffer dest)
/* 305:    */  {
/* 306:306 */    dest.put(this.blue);
/* 307:307 */    dest.put(this.green);
/* 308:308 */    dest.put(this.red);
/* 309:    */  }
/* 310:    */  
/* 313:    */  public void writeBGRA(ByteBuffer dest)
/* 314:    */  {
/* 315:315 */    dest.put(this.blue);
/* 316:316 */    dest.put(this.green);
/* 317:317 */    dest.put(this.red);
/* 318:318 */    dest.put(this.alpha);
/* 319:    */  }
/* 320:    */  
/* 324:    */  public void readRGBA(ByteBuffer src)
/* 325:    */  {
/* 326:326 */    this.red = src.get();
/* 327:327 */    this.green = src.get();
/* 328:328 */    this.blue = src.get();
/* 329:329 */    this.alpha = src.get();
/* 330:    */  }
/* 331:    */  
/* 335:    */  public void readRGB(ByteBuffer src)
/* 336:    */  {
/* 337:337 */    this.red = src.get();
/* 338:338 */    this.green = src.get();
/* 339:339 */    this.blue = src.get();
/* 340:    */  }
/* 341:    */  
/* 345:    */  public void readARGB(ByteBuffer src)
/* 346:    */  {
/* 347:347 */    this.alpha = src.get();
/* 348:348 */    this.red = src.get();
/* 349:349 */    this.green = src.get();
/* 350:350 */    this.blue = src.get();
/* 351:    */  }
/* 352:    */  
/* 356:    */  public void readBGRA(ByteBuffer src)
/* 357:    */  {
/* 358:358 */    this.blue = src.get();
/* 359:359 */    this.green = src.get();
/* 360:360 */    this.red = src.get();
/* 361:361 */    this.alpha = src.get();
/* 362:    */  }
/* 363:    */  
/* 367:    */  public void readBGR(ByteBuffer src)
/* 368:    */  {
/* 369:369 */    this.blue = src.get();
/* 370:370 */    this.green = src.get();
/* 371:371 */    this.red = src.get();
/* 372:    */  }
/* 373:    */  
/* 377:    */  public void readABGR(ByteBuffer src)
/* 378:    */  {
/* 379:379 */    this.alpha = src.get();
/* 380:380 */    this.blue = src.get();
/* 381:381 */    this.green = src.get();
/* 382:382 */    this.red = src.get();
/* 383:    */  }
/* 384:    */  
/* 388:    */  public void setColor(ReadableColor src)
/* 389:    */  {
/* 390:390 */    this.red = src.getRedByte();
/* 391:391 */    this.green = src.getGreenByte();
/* 392:392 */    this.blue = src.getBlueByte();
/* 393:393 */    this.alpha = src.getAlphaByte();
/* 394:    */  }
/* 395:    */  
/* 401:    */  public void fromHSB(float hue, float saturation, float brightness)
/* 402:    */  {
/* 403:403 */    if (saturation == 0.0F) {
/* 404:404 */      this.red = (this.green = this.blue = (byte)(int)(brightness * 255.0F + 0.5F));
/* 405:    */    } else {
/* 406:406 */      float f3 = (hue - (float)Math.floor(hue)) * 6.0F;
/* 407:407 */      float f4 = f3 - (float)Math.floor(f3);
/* 408:408 */      float f5 = brightness * (1.0F - saturation);
/* 409:409 */      float f6 = brightness * (1.0F - saturation * f4);
/* 410:410 */      float f7 = brightness * (1.0F - saturation * (1.0F - f4));
/* 411:411 */      switch ((int)f3) {
/* 412:    */      case 0: 
/* 413:413 */        this.red = ((byte)(int)(brightness * 255.0F + 0.5F));
/* 414:414 */        this.green = ((byte)(int)(f7 * 255.0F + 0.5F));
/* 415:415 */        this.blue = ((byte)(int)(f5 * 255.0F + 0.5F));
/* 416:416 */        break;
/* 417:    */      case 1: 
/* 418:418 */        this.red = ((byte)(int)(f6 * 255.0F + 0.5F));
/* 419:419 */        this.green = ((byte)(int)(brightness * 255.0F + 0.5F));
/* 420:420 */        this.blue = ((byte)(int)(f5 * 255.0F + 0.5F));
/* 421:421 */        break;
/* 422:    */      case 2: 
/* 423:423 */        this.red = ((byte)(int)(f5 * 255.0F + 0.5F));
/* 424:424 */        this.green = ((byte)(int)(brightness * 255.0F + 0.5F));
/* 425:425 */        this.blue = ((byte)(int)(f7 * 255.0F + 0.5F));
/* 426:426 */        break;
/* 427:    */      case 3: 
/* 428:428 */        this.red = ((byte)(int)(f5 * 255.0F + 0.5F));
/* 429:429 */        this.green = ((byte)(int)(f6 * 255.0F + 0.5F));
/* 430:430 */        this.blue = ((byte)(int)(brightness * 255.0F + 0.5F));
/* 431:431 */        break;
/* 432:    */      case 4: 
/* 433:433 */        this.red = ((byte)(int)(f7 * 255.0F + 0.5F));
/* 434:434 */        this.green = ((byte)(int)(f5 * 255.0F + 0.5F));
/* 435:435 */        this.blue = ((byte)(int)(brightness * 255.0F + 0.5F));
/* 436:436 */        break;
/* 437:    */      case 5: 
/* 438:438 */        this.red = ((byte)(int)(brightness * 255.0F + 0.5F));
/* 439:439 */        this.green = ((byte)(int)(f5 * 255.0F + 0.5F));
/* 440:440 */        this.blue = ((byte)(int)(f6 * 255.0F + 0.5F));
/* 441:    */      }
/* 442:    */      
/* 443:    */    }
/* 444:    */  }
/* 445:    */  
/* 452:    */  public float[] toHSB(float[] dest)
/* 453:    */  {
/* 454:454 */    int r = getRed();
/* 455:455 */    int g = getGreen();
/* 456:456 */    int b = getBlue();
/* 457:457 */    if (dest == null)
/* 458:458 */      dest = new float[3];
/* 459:459 */    int l = r <= g ? g : r;
/* 460:460 */    if (b > l)
/* 461:461 */      l = b;
/* 462:462 */    int i1 = r >= g ? g : r;
/* 463:463 */    if (b < i1)
/* 464:464 */      i1 = b;
/* 465:465 */    float brightness = l / 255.0F;
/* 466:    */    float saturation;
/* 467:467 */    float saturation; if (l != 0) {
/* 468:468 */      saturation = (l - i1) / l;
/* 469:    */    } else
/* 470:470 */      saturation = 0.0F;
/* 471:    */    float hue;
/* 472:472 */    float hue; if (saturation == 0.0F) {
/* 473:473 */      hue = 0.0F;
/* 474:    */    } else {
/* 475:475 */      float f3 = (l - r) / (l - i1);
/* 476:476 */      float f4 = (l - g) / (l - i1);
/* 477:477 */      float f5 = (l - b) / (l - i1);
/* 478:478 */      float hue; if (r == l) {
/* 479:479 */        hue = f5 - f4; } else { float hue;
/* 480:480 */        if (g == l) {
/* 481:481 */          hue = 2.0F + f3 - f5;
/* 482:    */        } else
/* 483:483 */          hue = 4.0F + f4 - f3; }
/* 484:484 */      hue /= 6.0F;
/* 485:485 */      if (hue < 0.0F)
/* 486:486 */        hue += 1.0F;
/* 487:    */    }
/* 488:488 */    dest[0] = hue;
/* 489:489 */    dest[1] = saturation;
/* 490:490 */    dest[2] = brightness;
/* 491:491 */    return dest;
/* 492:    */  }
/* 493:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.Color
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */