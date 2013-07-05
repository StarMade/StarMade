/*     */ package org.lwjgl.util;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public final class Rectangle
/*     */   implements ReadableRectangle, WritableRectangle, Serializable
/*     */ {
/*     */   static final long serialVersionUID = 1L;
/*     */   private int x;
/*     */   private int y;
/*     */   private int width;
/*     */   private int height;
/*     */ 
/*     */   public Rectangle()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Rectangle(int x, int y, int w, int h)
/*     */   {
/*  59 */     this.x = x;
/*  60 */     this.y = y;
/*  61 */     this.width = w;
/*  62 */     this.height = h;
/*     */   }
/*     */ 
/*     */   public Rectangle(ReadablePoint p, ReadableDimension d)
/*     */   {
/*  68 */     this.x = p.getX();
/*  69 */     this.y = p.getY();
/*  70 */     this.width = d.getWidth();
/*  71 */     this.height = d.getHeight();
/*     */   }
/*     */ 
/*     */   public Rectangle(ReadableRectangle r)
/*     */   {
/*  77 */     this.x = r.getX();
/*  78 */     this.y = r.getY();
/*  79 */     this.width = r.getWidth();
/*  80 */     this.height = r.getHeight();
/*     */   }
/*     */ 
/*     */   public void setLocation(int x, int y) {
/*  84 */     this.x = x;
/*  85 */     this.y = y;
/*     */   }
/*     */ 
/*     */   public void setLocation(ReadablePoint p) {
/*  89 */     this.x = p.getX();
/*  90 */     this.y = p.getY();
/*     */   }
/*     */ 
/*     */   public void setSize(int w, int h) {
/*  94 */     this.width = w;
/*  95 */     this.height = h;
/*     */   }
/*     */ 
/*     */   public void setSize(ReadableDimension d) {
/*  99 */     this.width = d.getWidth();
/* 100 */     this.height = d.getHeight();
/*     */   }
/*     */ 
/*     */   public void setBounds(int x, int y, int w, int h) {
/* 104 */     this.x = x;
/* 105 */     this.y = y;
/* 106 */     this.width = w;
/* 107 */     this.height = h;
/*     */   }
/*     */ 
/*     */   public void setBounds(ReadablePoint p, ReadableDimension d) {
/* 111 */     this.x = p.getX();
/* 112 */     this.y = p.getY();
/* 113 */     this.width = d.getWidth();
/* 114 */     this.height = d.getHeight();
/*     */   }
/*     */ 
/*     */   public void setBounds(ReadableRectangle r) {
/* 118 */     this.x = r.getX();
/* 119 */     this.y = r.getY();
/* 120 */     this.width = r.getWidth();
/* 121 */     this.height = r.getHeight();
/*     */   }
/*     */ 
/*     */   public void getBounds(WritableRectangle dest)
/*     */   {
/* 128 */     dest.setBounds(this.x, this.y, this.width, this.height);
/*     */   }
/*     */ 
/*     */   public void getLocation(WritablePoint dest)
/*     */   {
/* 135 */     dest.setLocation(this.x, this.y);
/*     */   }
/*     */ 
/*     */   public void getSize(WritableDimension dest)
/*     */   {
/* 142 */     dest.setSize(this.width, this.height);
/*     */   }
/*     */ 
/*     */   public void translate(int x, int y)
/*     */   {
/* 151 */     this.x += x;
/* 152 */     this.y += y;
/*     */   }
/*     */ 
/*     */   public void translate(ReadablePoint point)
/*     */   {
/* 160 */     this.x += point.getX();
/* 161 */     this.y += point.getY();
/*     */   }
/*     */ 
/*     */   public void untranslate(ReadablePoint point)
/*     */   {
/* 169 */     this.x -= point.getX();
/* 170 */     this.y -= point.getY();
/*     */   }
/*     */ 
/*     */   public boolean contains(ReadablePoint p)
/*     */   {
/* 183 */     return contains(p.getX(), p.getY());
/*     */   }
/*     */ 
/*     */   public boolean contains(int X, int Y)
/*     */   {
/* 198 */     int w = this.width;
/* 199 */     int h = this.height;
/* 200 */     if ((w | h) < 0)
/*     */     {
/* 202 */       return false;
/*     */     }
/*     */ 
/* 205 */     int x = this.x;
/* 206 */     int y = this.y;
/* 207 */     if ((X < x) || (Y < y)) {
/* 208 */       return false;
/*     */     }
/* 210 */     w += x;
/* 211 */     h += y;
/*     */ 
/* 213 */     return ((w < x) || (w > X)) && ((h < y) || (h > Y));
/*     */   }
/*     */ 
/*     */   public boolean contains(ReadableRectangle r)
/*     */   {
/* 225 */     return contains(r.getX(), r.getY(), r.getWidth(), r.getHeight());
/*     */   }
/*     */ 
/*     */   public boolean contains(int X, int Y, int W, int H)
/*     */   {
/* 243 */     int w = this.width;
/* 244 */     int h = this.height;
/* 245 */     if ((w | h | W | H) < 0)
/*     */     {
/* 247 */       return false;
/*     */     }
/*     */ 
/* 250 */     int x = this.x;
/* 251 */     int y = this.y;
/* 252 */     if ((X < x) || (Y < y)) {
/* 253 */       return false;
/*     */     }
/* 255 */     w += x;
/* 256 */     W += X;
/* 257 */     if (W <= X)
/*     */     {
/* 262 */       if ((w >= x) || (W > w)) {
/* 263 */         return false;
/*     */       }
/*     */ 
/*     */     }
/* 268 */     else if ((w >= x) && (W > w)) {
/* 269 */       return false;
/*     */     }
/* 271 */     h += y;
/* 272 */     H += Y;
/* 273 */     if (H <= Y) {
/* 274 */       if ((h >= y) || (H > h))
/* 275 */         return false;
/*     */     }
/* 277 */     else if ((h >= y) && (H > h)) {
/* 278 */       return false;
/*     */     }
/* 280 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean intersects(ReadableRectangle r)
/*     */   {
/* 294 */     int tw = this.width;
/* 295 */     int th = this.height;
/* 296 */     int rw = r.getWidth();
/* 297 */     int rh = r.getHeight();
/* 298 */     if ((rw <= 0) || (rh <= 0) || (tw <= 0) || (th <= 0)) {
/* 299 */       return false;
/*     */     }
/* 301 */     int tx = this.x;
/* 302 */     int ty = this.y;
/* 303 */     int rx = r.getX();
/* 304 */     int ry = r.getY();
/* 305 */     rw += rx;
/* 306 */     rh += ry;
/* 307 */     tw += tx;
/* 308 */     th += ty;
/*     */ 
/* 310 */     return ((rw < rx) || (rw > tx)) && ((rh < ry) || (rh > ty)) && ((tw < tx) || (tw > rx)) && ((th < ty) || (th > ry));
/*     */   }
/*     */ 
/*     */   public Rectangle intersection(ReadableRectangle r, Rectangle dest)
/*     */   {
/* 327 */     int tx1 = this.x;
/* 328 */     int ty1 = this.y;
/* 329 */     int rx1 = r.getX();
/* 330 */     int ry1 = r.getY();
/* 331 */     long tx2 = tx1;
/* 332 */     tx2 += this.width;
/* 333 */     long ty2 = ty1;
/* 334 */     ty2 += this.height;
/* 335 */     long rx2 = rx1;
/* 336 */     rx2 += r.getWidth();
/* 337 */     long ry2 = ry1;
/* 338 */     ry2 += r.getHeight();
/* 339 */     if (tx1 < rx1)
/* 340 */       tx1 = rx1;
/* 341 */     if (ty1 < ry1)
/* 342 */       ty1 = ry1;
/* 343 */     if (tx2 > rx2)
/* 344 */       tx2 = rx2;
/* 345 */     if (ty2 > ry2)
/* 346 */       ty2 = ry2;
/* 347 */     tx2 -= tx1;
/* 348 */     ty2 -= ty1;
/*     */ 
/* 352 */     if (tx2 < -2147483648L)
/* 353 */       tx2 = -2147483648L;
/* 354 */     if (ty2 < -2147483648L)
/* 355 */       ty2 = -2147483648L;
/* 356 */     if (dest == null)
/* 357 */       dest = new Rectangle(tx1, ty1, (int)tx2, (int)ty2);
/*     */     else
/* 359 */       dest.setBounds(tx1, ty1, (int)tx2, (int)ty2);
/* 360 */     return dest;
/*     */   }
/*     */ 
/*     */   public WritableRectangle union(ReadableRectangle r, WritableRectangle dest)
/*     */   {
/* 375 */     int x1 = Math.min(this.x, r.getX());
/* 376 */     int x2 = Math.max(this.x + this.width, r.getX() + r.getWidth());
/* 377 */     int y1 = Math.min(this.y, r.getY());
/* 378 */     int y2 = Math.max(this.y + this.height, r.getY() + r.getHeight());
/* 379 */     dest.setBounds(x1, y1, x2 - x1, y2 - y1);
/* 380 */     return dest;
/*     */   }
/*     */ 
/*     */   public void add(int newx, int newy)
/*     */   {
/* 402 */     int x1 = Math.min(this.x, newx);
/* 403 */     int x2 = Math.max(this.x + this.width, newx);
/* 404 */     int y1 = Math.min(this.y, newy);
/* 405 */     int y2 = Math.max(this.y + this.height, newy);
/* 406 */     this.x = x1;
/* 407 */     this.y = y1;
/* 408 */     this.width = (x2 - x1);
/* 409 */     this.height = (y2 - y1);
/*     */   }
/*     */ 
/*     */   public void add(ReadablePoint pt)
/*     */   {
/* 431 */     add(pt.getX(), pt.getY());
/*     */   }
/*     */ 
/*     */   public void add(ReadableRectangle r)
/*     */   {
/* 441 */     int x1 = Math.min(this.x, r.getX());
/* 442 */     int x2 = Math.max(this.x + this.width, r.getX() + r.getWidth());
/* 443 */     int y1 = Math.min(this.y, r.getY());
/* 444 */     int y2 = Math.max(this.y + this.height, r.getY() + r.getHeight());
/* 445 */     this.x = x1;
/* 446 */     this.y = y1;
/* 447 */     this.width = (x2 - x1);
/* 448 */     this.height = (y2 - y1);
/*     */   }
/*     */ 
/*     */   public void grow(int h, int v)
/*     */   {
/* 475 */     this.x -= h;
/* 476 */     this.y -= v;
/* 477 */     this.width += h * 2;
/* 478 */     this.height += v * 2;
/*     */   }
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/* 489 */     return (this.width <= 0) || (this.height <= 0);
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 503 */     if ((obj instanceof Rectangle)) {
/* 504 */       Rectangle r = (Rectangle)obj;
/* 505 */       return (this.x == r.x) && (this.y == r.y) && (this.width == r.width) && (this.height == r.height);
/*     */     }
/* 507 */     return super.equals(obj);
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 515 */     return getClass().getName() + "[x=" + this.x + ",y=" + this.y + ",width=" + this.width + ",height=" + this.height + "]";
/*     */   }
/*     */ 
/*     */   public int getHeight()
/*     */   {
/* 522 */     return this.height;
/*     */   }
/*     */ 
/*     */   public void setHeight(int height)
/*     */   {
/* 530 */     this.height = height;
/*     */   }
/*     */ 
/*     */   public int getWidth()
/*     */   {
/* 538 */     return this.width;
/*     */   }
/*     */ 
/*     */   public void setWidth(int width)
/*     */   {
/* 546 */     this.width = width;
/*     */   }
/*     */ 
/*     */   public int getX()
/*     */   {
/* 554 */     return this.x;
/*     */   }
/*     */ 
/*     */   public void setX(int x)
/*     */   {
/* 562 */     this.x = x;
/*     */   }
/*     */ 
/*     */   public int getY()
/*     */   {
/* 570 */     return this.y;
/*     */   }
/*     */ 
/*     */   public void setY(int y)
/*     */   {
/* 578 */     this.y = y;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.Rectangle
 * JD-Core Version:    0.6.2
 */