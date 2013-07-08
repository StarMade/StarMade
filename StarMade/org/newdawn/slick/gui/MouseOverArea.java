/*   1:    */package org.newdawn.slick.gui;
/*   2:    */
/*   3:    */import org.newdawn.slick.Color;
/*   4:    */import org.newdawn.slick.Graphics;
/*   5:    */import org.newdawn.slick.Image;
/*   6:    */import org.newdawn.slick.Input;
/*   7:    */import org.newdawn.slick.Sound;
/*   8:    */import org.newdawn.slick.geom.Rectangle;
/*   9:    */import org.newdawn.slick.geom.Shape;
/*  10:    */
/*  27:    */public class MouseOverArea
/*  28:    */  extends AbstractComponent
/*  29:    */{
/*  30:    */  private static final int NORMAL = 1;
/*  31:    */  private static final int MOUSE_DOWN = 2;
/*  32:    */  private static final int MOUSE_OVER = 3;
/*  33:    */  private Image normalImage;
/*  34:    */  private Image mouseOverImage;
/*  35:    */  private Image mouseDownImage;
/*  36: 36 */  private Color normalColor = Color.white;
/*  37:    */  
/*  39: 39 */  private Color mouseOverColor = Color.white;
/*  40:    */  
/*  42: 42 */  private Color mouseDownColor = Color.white;
/*  43:    */  
/*  45:    */  private Sound mouseOverSound;
/*  46:    */  
/*  48:    */  private Sound mouseDownSound;
/*  49:    */  
/*  51:    */  private Shape area;
/*  52:    */  
/*  54:    */  private Image currentImage;
/*  55:    */  
/*  57:    */  private Color currentColor;
/*  58:    */  
/*  60:    */  private boolean over;
/*  61:    */  
/*  63:    */  private boolean mouseDown;
/*  64:    */  
/*  66: 66 */  private int state = 1;
/*  67:    */  
/*  75:    */  private boolean mouseUp;
/*  76:    */  
/*  84:    */  public MouseOverArea(GUIContext container, Image image, int x, int y, ComponentListener listener)
/*  85:    */  {
/*  86: 86 */    this(container, image, x, y, image.getWidth(), image.getHeight());
/*  87: 87 */    addListener(listener);
/*  88:    */  }
/*  89:    */  
/* 101:    */  public MouseOverArea(GUIContext container, Image image, int x, int y)
/* 102:    */  {
/* 103:103 */    this(container, image, x, y, image.getWidth(), image.getHeight());
/* 104:    */  }
/* 105:    */  
/* 124:    */  public MouseOverArea(GUIContext container, Image image, int x, int y, int width, int height, ComponentListener listener)
/* 125:    */  {
/* 126:126 */    this(container, image, x, y, width, height);
/* 127:127 */    addListener(listener);
/* 128:    */  }
/* 129:    */  
/* 146:    */  public MouseOverArea(GUIContext container, Image image, int x, int y, int width, int height)
/* 147:    */  {
/* 148:148 */    this(container, image, new Rectangle(x, y, width, height));
/* 149:    */  }
/* 150:    */  
/* 160:    */  public MouseOverArea(GUIContext container, Image image, Shape shape)
/* 161:    */  {
/* 162:162 */    super(container);
/* 163:    */    
/* 164:164 */    this.area = shape;
/* 165:165 */    this.normalImage = image;
/* 166:166 */    this.currentImage = image;
/* 167:167 */    this.mouseOverImage = image;
/* 168:168 */    this.mouseDownImage = image;
/* 169:    */    
/* 170:170 */    this.currentColor = this.normalColor;
/* 171:    */    
/* 172:172 */    this.state = 1;
/* 173:173 */    Input input = container.getInput();
/* 174:174 */    this.over = this.area.contains(input.getMouseX(), input.getMouseY());
/* 175:175 */    this.mouseDown = input.isMouseButtonDown(0);
/* 176:176 */    updateImage();
/* 177:    */  }
/* 178:    */  
/* 184:    */  public void setLocation(float x, float y)
/* 185:    */  {
/* 186:186 */    if (this.area != null) {
/* 187:187 */      this.area.setX(x);
/* 188:188 */      this.area.setY(y);
/* 189:    */    }
/* 190:    */  }
/* 191:    */  
/* 196:    */  public void setX(float x)
/* 197:    */  {
/* 198:198 */    this.area.setX(x);
/* 199:    */  }
/* 200:    */  
/* 205:    */  public void setY(float y)
/* 206:    */  {
/* 207:207 */    this.area.setY(y);
/* 208:    */  }
/* 209:    */  
/* 214:    */  public int getX()
/* 215:    */  {
/* 216:216 */    return (int)this.area.getX();
/* 217:    */  }
/* 218:    */  
/* 223:    */  public int getY()
/* 224:    */  {
/* 225:225 */    return (int)this.area.getY();
/* 226:    */  }
/* 227:    */  
/* 233:    */  public void setNormalColor(Color color)
/* 234:    */  {
/* 235:235 */    this.normalColor = color;
/* 236:    */  }
/* 237:    */  
/* 243:    */  public void setMouseOverColor(Color color)
/* 244:    */  {
/* 245:245 */    this.mouseOverColor = color;
/* 246:    */  }
/* 247:    */  
/* 253:    */  public void setMouseDownColor(Color color)
/* 254:    */  {
/* 255:255 */    this.mouseDownColor = color;
/* 256:    */  }
/* 257:    */  
/* 263:    */  public void setNormalImage(Image image)
/* 264:    */  {
/* 265:265 */    this.normalImage = image;
/* 266:    */  }
/* 267:    */  
/* 273:    */  public void setMouseOverImage(Image image)
/* 274:    */  {
/* 275:275 */    this.mouseOverImage = image;
/* 276:    */  }
/* 277:    */  
/* 283:    */  public void setMouseDownImage(Image image)
/* 284:    */  {
/* 285:285 */    this.mouseDownImage = image;
/* 286:    */  }
/* 287:    */  
/* 291:    */  public void render(GUIContext container, Graphics g)
/* 292:    */  {
/* 293:293 */    if (this.currentImage != null)
/* 294:    */    {
/* 295:295 */      int xp = (int)(this.area.getX() + (getWidth() - this.currentImage.getWidth()) / 2);
/* 296:296 */      int yp = (int)(this.area.getY() + (getHeight() - this.currentImage.getHeight()) / 2);
/* 297:    */      
/* 298:298 */      this.currentImage.draw(xp, yp, this.currentColor);
/* 299:    */    } else {
/* 300:300 */      g.setColor(this.currentColor);
/* 301:301 */      g.fill(this.area);
/* 302:    */    }
/* 303:303 */    updateImage();
/* 304:    */  }
/* 305:    */  
/* 308:    */  private void updateImage()
/* 309:    */  {
/* 310:310 */    if (!this.over) {
/* 311:311 */      this.currentImage = this.normalImage;
/* 312:312 */      this.currentColor = this.normalColor;
/* 313:313 */      this.state = 1;
/* 314:314 */      this.mouseUp = false;
/* 315:    */    } else {
/* 316:316 */      if (this.mouseDown) {
/* 317:317 */        if ((this.state != 2) && (this.mouseUp)) {
/* 318:318 */          if (this.mouseDownSound != null) {
/* 319:319 */            this.mouseDownSound.play();
/* 320:    */          }
/* 321:321 */          this.currentImage = this.mouseDownImage;
/* 322:322 */          this.currentColor = this.mouseDownColor;
/* 323:323 */          this.state = 2;
/* 324:    */          
/* 325:325 */          notifyListeners();
/* 326:326 */          this.mouseUp = false;
/* 327:    */        }
/* 328:    */        
/* 329:329 */        return;
/* 330:    */      }
/* 331:331 */      this.mouseUp = true;
/* 332:332 */      if (this.state != 3) {
/* 333:333 */        if (this.mouseOverSound != null) {
/* 334:334 */          this.mouseOverSound.play();
/* 335:    */        }
/* 336:336 */        this.currentImage = this.mouseOverImage;
/* 337:337 */        this.currentColor = this.mouseOverColor;
/* 338:338 */        this.state = 3;
/* 339:    */      }
/* 340:    */    }
/* 341:    */    
/* 343:343 */    this.mouseDown = false;
/* 344:344 */    this.state = 1;
/* 345:    */  }
/* 346:    */  
/* 352:    */  public void setMouseOverSound(Sound sound)
/* 353:    */  {
/* 354:354 */    this.mouseOverSound = sound;
/* 355:    */  }
/* 356:    */  
/* 362:    */  public void setMouseDownSound(Sound sound)
/* 363:    */  {
/* 364:364 */    this.mouseDownSound = sound;
/* 365:    */  }
/* 366:    */  
/* 369:    */  public void mouseMoved(int oldx, int oldy, int newx, int newy)
/* 370:    */  {
/* 371:371 */    this.over = this.area.contains(newx, newy);
/* 372:    */  }
/* 373:    */  
/* 376:    */  public void mouseDragged(int oldx, int oldy, int newx, int newy)
/* 377:    */  {
/* 378:378 */    mouseMoved(oldx, oldy, newx, newy);
/* 379:    */  }
/* 380:    */  
/* 383:    */  public void mousePressed(int button, int mx, int my)
/* 384:    */  {
/* 385:385 */    this.over = this.area.contains(mx, my);
/* 386:386 */    if (button == 0) {
/* 387:387 */      this.mouseDown = true;
/* 388:    */    }
/* 389:    */  }
/* 390:    */  
/* 393:    */  public void mouseReleased(int button, int mx, int my)
/* 394:    */  {
/* 395:395 */    this.over = this.area.contains(mx, my);
/* 396:396 */    if (button == 0) {
/* 397:397 */      this.mouseDown = false;
/* 398:    */    }
/* 399:    */  }
/* 400:    */  
/* 403:    */  public int getHeight()
/* 404:    */  {
/* 405:405 */    return (int)(this.area.getMaxY() - this.area.getY());
/* 406:    */  }
/* 407:    */  
/* 410:    */  public int getWidth()
/* 411:    */  {
/* 412:412 */    return (int)(this.area.getMaxX() - this.area.getX());
/* 413:    */  }
/* 414:    */  
/* 419:    */  public boolean isMouseOver()
/* 420:    */  {
/* 421:421 */    return this.over;
/* 422:    */  }
/* 423:    */  
/* 429:    */  public void setLocation(int x, int y)
/* 430:    */  {
/* 431:431 */    setLocation(x, y);
/* 432:    */  }
/* 433:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.gui.MouseOverArea
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */