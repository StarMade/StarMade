/*   1:    */package org.lwjgl.util;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */
/*  46:    */public final class Rectangle
/*  47:    */  implements ReadableRectangle, WritableRectangle, Serializable
/*  48:    */{
/*  49:    */  static final long serialVersionUID = 1L;
/*  50:    */  private int x;
/*  51:    */  private int y;
/*  52:    */  private int width;
/*  53:    */  private int height;
/*  54:    */  
/*  55:    */  public Rectangle() {}
/*  56:    */  
/*  57:    */  public Rectangle(int x, int y, int w, int h)
/*  58:    */  {
/*  59: 59 */    this.x = x;
/*  60: 60 */    this.y = y;
/*  61: 61 */    this.width = w;
/*  62: 62 */    this.height = h;
/*  63:    */  }
/*  64:    */  
/*  66:    */  public Rectangle(ReadablePoint p, ReadableDimension d)
/*  67:    */  {
/*  68: 68 */    this.x = p.getX();
/*  69: 69 */    this.y = p.getY();
/*  70: 70 */    this.width = d.getWidth();
/*  71: 71 */    this.height = d.getHeight();
/*  72:    */  }
/*  73:    */  
/*  75:    */  public Rectangle(ReadableRectangle r)
/*  76:    */  {
/*  77: 77 */    this.x = r.getX();
/*  78: 78 */    this.y = r.getY();
/*  79: 79 */    this.width = r.getWidth();
/*  80: 80 */    this.height = r.getHeight();
/*  81:    */  }
/*  82:    */  
/*  83:    */  public void setLocation(int x, int y) {
/*  84: 84 */    this.x = x;
/*  85: 85 */    this.y = y;
/*  86:    */  }
/*  87:    */  
/*  88:    */  public void setLocation(ReadablePoint p) {
/*  89: 89 */    this.x = p.getX();
/*  90: 90 */    this.y = p.getY();
/*  91:    */  }
/*  92:    */  
/*  93:    */  public void setSize(int w, int h) {
/*  94: 94 */    this.width = w;
/*  95: 95 */    this.height = h;
/*  96:    */  }
/*  97:    */  
/*  98:    */  public void setSize(ReadableDimension d) {
/*  99: 99 */    this.width = d.getWidth();
/* 100:100 */    this.height = d.getHeight();
/* 101:    */  }
/* 102:    */  
/* 103:    */  public void setBounds(int x, int y, int w, int h) {
/* 104:104 */    this.x = x;
/* 105:105 */    this.y = y;
/* 106:106 */    this.width = w;
/* 107:107 */    this.height = h;
/* 108:    */  }
/* 109:    */  
/* 110:    */  public void setBounds(ReadablePoint p, ReadableDimension d) {
/* 111:111 */    this.x = p.getX();
/* 112:112 */    this.y = p.getY();
/* 113:113 */    this.width = d.getWidth();
/* 114:114 */    this.height = d.getHeight();
/* 115:    */  }
/* 116:    */  
/* 117:    */  public void setBounds(ReadableRectangle r) {
/* 118:118 */    this.x = r.getX();
/* 119:119 */    this.y = r.getY();
/* 120:120 */    this.width = r.getWidth();
/* 121:121 */    this.height = r.getHeight();
/* 122:    */  }
/* 123:    */  
/* 126:    */  public void getBounds(WritableRectangle dest)
/* 127:    */  {
/* 128:128 */    dest.setBounds(this.x, this.y, this.width, this.height);
/* 129:    */  }
/* 130:    */  
/* 133:    */  public void getLocation(WritablePoint dest)
/* 134:    */  {
/* 135:135 */    dest.setLocation(this.x, this.y);
/* 136:    */  }
/* 137:    */  
/* 140:    */  public void getSize(WritableDimension dest)
/* 141:    */  {
/* 142:142 */    dest.setSize(this.width, this.height);
/* 143:    */  }
/* 144:    */  
/* 149:    */  public void translate(int x, int y)
/* 150:    */  {
/* 151:151 */    this.x += x;
/* 152:152 */    this.y += y;
/* 153:    */  }
/* 154:    */  
/* 158:    */  public void translate(ReadablePoint point)
/* 159:    */  {
/* 160:160 */    this.x += point.getX();
/* 161:161 */    this.y += point.getY();
/* 162:    */  }
/* 163:    */  
/* 167:    */  public void untranslate(ReadablePoint point)
/* 168:    */  {
/* 169:169 */    this.x -= point.getX();
/* 170:170 */    this.y -= point.getY();
/* 171:    */  }
/* 172:    */  
/* 181:    */  public boolean contains(ReadablePoint p)
/* 182:    */  {
/* 183:183 */    return contains(p.getX(), p.getY());
/* 184:    */  }
/* 185:    */  
/* 196:    */  public boolean contains(int X, int Y)
/* 197:    */  {
/* 198:198 */    int w = this.width;
/* 199:199 */    int h = this.height;
/* 200:200 */    if ((w | h) < 0)
/* 201:    */    {
/* 202:202 */      return false;
/* 203:    */    }
/* 204:    */    
/* 205:205 */    int x = this.x;
/* 206:206 */    int y = this.y;
/* 207:207 */    if ((X < x) || (Y < y)) {
/* 208:208 */      return false;
/* 209:    */    }
/* 210:210 */    w += x;
/* 211:211 */    h += y;
/* 212:    */    
/* 213:213 */    return ((w < x) || (w > X)) && ((h < y) || (h > Y));
/* 214:    */  }
/* 215:    */  
/* 223:    */  public boolean contains(ReadableRectangle r)
/* 224:    */  {
/* 225:225 */    return contains(r.getX(), r.getY(), r.getWidth(), r.getHeight());
/* 226:    */  }
/* 227:    */  
/* 241:    */  public boolean contains(int X, int Y, int W, int H)
/* 242:    */  {
/* 243:243 */    int w = this.width;
/* 244:244 */    int h = this.height;
/* 245:245 */    if ((w | h | W | H) < 0)
/* 246:    */    {
/* 247:247 */      return false;
/* 248:    */    }
/* 249:    */    
/* 250:250 */    int x = this.x;
/* 251:251 */    int y = this.y;
/* 252:252 */    if ((X < x) || (Y < y)) {
/* 253:253 */      return false;
/* 254:    */    }
/* 255:255 */    w += x;
/* 256:256 */    W += X;
/* 257:257 */    if (W <= X)
/* 258:    */    {
/* 262:262 */      if ((w >= x) || (W > w)) {
/* 263:263 */        return false;
/* 264:    */      }
/* 265:    */      
/* 267:    */    }
/* 268:268 */    else if ((w >= x) && (W > w)) {
/* 269:269 */      return false;
/* 270:    */    }
/* 271:271 */    h += y;
/* 272:272 */    H += Y;
/* 273:273 */    if (H <= Y) {
/* 274:274 */      if ((h >= y) || (H > h)) {
/* 275:275 */        return false;
/* 276:    */      }
/* 277:277 */    } else if ((h >= y) && (H > h)) {
/* 278:278 */      return false;
/* 279:    */    }
/* 280:280 */    return true;
/* 281:    */  }
/* 282:    */  
/* 292:    */  public boolean intersects(ReadableRectangle r)
/* 293:    */  {
/* 294:294 */    int tw = this.width;
/* 295:295 */    int th = this.height;
/* 296:296 */    int rw = r.getWidth();
/* 297:297 */    int rh = r.getHeight();
/* 298:298 */    if ((rw <= 0) || (rh <= 0) || (tw <= 0) || (th <= 0)) {
/* 299:299 */      return false;
/* 300:    */    }
/* 301:301 */    int tx = this.x;
/* 302:302 */    int ty = this.y;
/* 303:303 */    int rx = r.getX();
/* 304:304 */    int ry = r.getY();
/* 305:305 */    rw += rx;
/* 306:306 */    rh += ry;
/* 307:307 */    tw += tx;
/* 308:308 */    th += ty;
/* 309:    */    
/* 310:310 */    return ((rw < rx) || (rw > tx)) && ((rh < ry) || (rh > ty)) && ((tw < tx) || (tw > rx)) && ((th < ty) || (th > ry));
/* 311:    */  }
/* 312:    */  
/* 325:    */  public Rectangle intersection(ReadableRectangle r, Rectangle dest)
/* 326:    */  {
/* 327:327 */    int tx1 = this.x;
/* 328:328 */    int ty1 = this.y;
/* 329:329 */    int rx1 = r.getX();
/* 330:330 */    int ry1 = r.getY();
/* 331:331 */    long tx2 = tx1;
/* 332:332 */    tx2 += this.width;
/* 333:333 */    long ty2 = ty1;
/* 334:334 */    ty2 += this.height;
/* 335:335 */    long rx2 = rx1;
/* 336:336 */    rx2 += r.getWidth();
/* 337:337 */    long ry2 = ry1;
/* 338:338 */    ry2 += r.getHeight();
/* 339:339 */    if (tx1 < rx1)
/* 340:340 */      tx1 = rx1;
/* 341:341 */    if (ty1 < ry1)
/* 342:342 */      ty1 = ry1;
/* 343:343 */    if (tx2 > rx2)
/* 344:344 */      tx2 = rx2;
/* 345:345 */    if (ty2 > ry2)
/* 346:346 */      ty2 = ry2;
/* 347:347 */    tx2 -= tx1;
/* 348:348 */    ty2 -= ty1;
/* 349:    */    
/* 352:352 */    if (tx2 < -2147483648L)
/* 353:353 */      tx2 = -2147483648L;
/* 354:354 */    if (ty2 < -2147483648L)
/* 355:355 */      ty2 = -2147483648L;
/* 356:356 */    if (dest == null) {
/* 357:357 */      dest = new Rectangle(tx1, ty1, (int)tx2, (int)ty2);
/* 358:    */    } else
/* 359:359 */      dest.setBounds(tx1, ty1, (int)tx2, (int)ty2);
/* 360:360 */    return dest;
/* 361:    */  }
/* 362:    */  
/* 373:    */  public WritableRectangle union(ReadableRectangle r, WritableRectangle dest)
/* 374:    */  {
/* 375:375 */    int x1 = Math.min(this.x, r.getX());
/* 376:376 */    int x2 = Math.max(this.x + this.width, r.getX() + r.getWidth());
/* 377:377 */    int y1 = Math.min(this.y, r.getY());
/* 378:378 */    int y2 = Math.max(this.y + this.height, r.getY() + r.getHeight());
/* 379:379 */    dest.setBounds(x1, y1, x2 - x1, y2 - y1);
/* 380:380 */    return dest;
/* 381:    */  }
/* 382:    */  
/* 400:    */  public void add(int newx, int newy)
/* 401:    */  {
/* 402:402 */    int x1 = Math.min(this.x, newx);
/* 403:403 */    int x2 = Math.max(this.x + this.width, newx);
/* 404:404 */    int y1 = Math.min(this.y, newy);
/* 405:405 */    int y2 = Math.max(this.y + this.height, newy);
/* 406:406 */    this.x = x1;
/* 407:407 */    this.y = y1;
/* 408:408 */    this.width = (x2 - x1);
/* 409:409 */    this.height = (y2 - y1);
/* 410:    */  }
/* 411:    */  
/* 429:    */  public void add(ReadablePoint pt)
/* 430:    */  {
/* 431:431 */    add(pt.getX(), pt.getY());
/* 432:    */  }
/* 433:    */  
/* 439:    */  public void add(ReadableRectangle r)
/* 440:    */  {
/* 441:441 */    int x1 = Math.min(this.x, r.getX());
/* 442:442 */    int x2 = Math.max(this.x + this.width, r.getX() + r.getWidth());
/* 443:443 */    int y1 = Math.min(this.y, r.getY());
/* 444:444 */    int y2 = Math.max(this.y + this.height, r.getY() + r.getHeight());
/* 445:445 */    this.x = x1;
/* 446:446 */    this.y = y1;
/* 447:447 */    this.width = (x2 - x1);
/* 448:448 */    this.height = (y2 - y1);
/* 449:    */  }
/* 450:    */  
/* 473:    */  public void grow(int h, int v)
/* 474:    */  {
/* 475:475 */    this.x -= h;
/* 476:476 */    this.y -= v;
/* 477:477 */    this.width += h * 2;
/* 478:478 */    this.height += v * 2;
/* 479:    */  }
/* 480:    */  
/* 487:    */  public boolean isEmpty()
/* 488:    */  {
/* 489:489 */    return (this.width <= 0) || (this.height <= 0);
/* 490:    */  }
/* 491:    */  
/* 501:    */  public boolean equals(Object obj)
/* 502:    */  {
/* 503:503 */    if ((obj instanceof Rectangle)) {
/* 504:504 */      Rectangle r = (Rectangle)obj;
/* 505:505 */      return (this.x == r.x) && (this.y == r.y) && (this.width == r.width) && (this.height == r.height);
/* 506:    */    }
/* 507:507 */    return super.equals(obj);
/* 508:    */  }
/* 509:    */  
/* 513:    */  public String toString()
/* 514:    */  {
/* 515:515 */    return getClass().getName() + "[x=" + this.x + ",y=" + this.y + ",width=" + this.width + ",height=" + this.height + "]";
/* 516:    */  }
/* 517:    */  
/* 520:    */  public int getHeight()
/* 521:    */  {
/* 522:522 */    return this.height;
/* 523:    */  }
/* 524:    */  
/* 528:    */  public void setHeight(int height)
/* 529:    */  {
/* 530:530 */    this.height = height;
/* 531:    */  }
/* 532:    */  
/* 536:    */  public int getWidth()
/* 537:    */  {
/* 538:538 */    return this.width;
/* 539:    */  }
/* 540:    */  
/* 544:    */  public void setWidth(int width)
/* 545:    */  {
/* 546:546 */    this.width = width;
/* 547:    */  }
/* 548:    */  
/* 552:    */  public int getX()
/* 553:    */  {
/* 554:554 */    return this.x;
/* 555:    */  }
/* 556:    */  
/* 560:    */  public void setX(int x)
/* 561:    */  {
/* 562:562 */    this.x = x;
/* 563:    */  }
/* 564:    */  
/* 568:    */  public int getY()
/* 569:    */  {
/* 570:570 */    return this.y;
/* 571:    */  }
/* 572:    */  
/* 576:    */  public void setY(int y)
/* 577:    */  {
/* 578:578 */    this.y = y;
/* 579:    */  }
/* 580:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.Rectangle
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */