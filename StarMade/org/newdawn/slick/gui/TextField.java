/*   1:    */package org.newdawn.slick.gui;
/*   2:    */
/*   3:    */import org.lwjgl.Sys;
/*   4:    */import org.newdawn.slick.Color;
/*   5:    */import org.newdawn.slick.Font;
/*   6:    */import org.newdawn.slick.Graphics;
/*   7:    */import org.newdawn.slick.Input;
/*   8:    */import org.newdawn.slick.geom.Rectangle;
/*   9:    */
/*  25:    */public class TextField
/*  26:    */  extends AbstractComponent
/*  27:    */{
/*  28:    */  private static final int INITIAL_KEY_REPEAT_INTERVAL = 400;
/*  29:    */  private static final int KEY_REPEAT_INTERVAL = 50;
/*  30:    */  private int width;
/*  31:    */  private int height;
/*  32:    */  protected int x;
/*  33:    */  protected int y;
/*  34: 34 */  private int maxCharacter = 10000;
/*  35:    */  
/*  37: 37 */  private String value = "";
/*  38:    */  
/*  40:    */  private Font font;
/*  41:    */  
/*  43: 43 */  private Color border = Color.white;
/*  44:    */  
/*  46: 46 */  private Color text = Color.white;
/*  47:    */  
/*  49: 49 */  private Color background = new Color(0.0F, 0.0F, 0.0F, 0.5F);
/*  50:    */  
/*  52:    */  private int cursorPos;
/*  53:    */  
/*  55: 55 */  private boolean visibleCursor = true;
/*  56:    */  
/*  58: 58 */  private int lastKey = -1;
/*  59:    */  
/*  61: 61 */  private char lastChar = '\000';
/*  62:    */  
/*  64:    */  private long repeatTimer;
/*  65:    */  
/*  67:    */  private String oldText;
/*  68:    */  
/*  70:    */  private int oldCursorPos;
/*  71:    */  
/*  73: 73 */  private boolean consume = true;
/*  74:    */  
/*  93:    */  public TextField(GUIContext container, Font font, int x, int y, int width, int height, ComponentListener listener)
/*  94:    */  {
/*  95: 95 */    this(container, font, x, y, width, height);
/*  96: 96 */    addListener(listener);
/*  97:    */  }
/*  98:    */  
/* 115:    */  public TextField(GUIContext container, Font font, int x, int y, int width, int height)
/* 116:    */  {
/* 117:117 */    super(container);
/* 118:    */    
/* 119:119 */    this.font = font;
/* 120:    */    
/* 121:121 */    setLocation(x, y);
/* 122:122 */    this.width = width;
/* 123:123 */    this.height = height;
/* 124:    */  }
/* 125:    */  
/* 130:    */  public void setConsumeEvents(boolean consume)
/* 131:    */  {
/* 132:132 */    this.consume = consume;
/* 133:    */  }
/* 134:    */  
/* 137:    */  public void deactivate()
/* 138:    */  {
/* 139:139 */    setFocus(false);
/* 140:    */  }
/* 141:    */  
/* 149:    */  public void setLocation(int x, int y)
/* 150:    */  {
/* 151:151 */    this.x = x;
/* 152:152 */    this.y = y;
/* 153:    */  }
/* 154:    */  
/* 159:    */  public int getX()
/* 160:    */  {
/* 161:161 */    return this.x;
/* 162:    */  }
/* 163:    */  
/* 168:    */  public int getY()
/* 169:    */  {
/* 170:170 */    return this.y;
/* 171:    */  }
/* 172:    */  
/* 177:    */  public int getWidth()
/* 178:    */  {
/* 179:179 */    return this.width;
/* 180:    */  }
/* 181:    */  
/* 186:    */  public int getHeight()
/* 187:    */  {
/* 188:188 */    return this.height;
/* 189:    */  }
/* 190:    */  
/* 196:    */  public void setBackgroundColor(Color color)
/* 197:    */  {
/* 198:198 */    this.background = color;
/* 199:    */  }
/* 200:    */  
/* 206:    */  public void setBorderColor(Color color)
/* 207:    */  {
/* 208:208 */    this.border = color;
/* 209:    */  }
/* 210:    */  
/* 216:    */  public void setTextColor(Color color)
/* 217:    */  {
/* 218:218 */    this.text = color;
/* 219:    */  }
/* 220:    */  
/* 224:    */  public void render(GUIContext container, Graphics g)
/* 225:    */  {
/* 226:226 */    if (this.lastKey != -1) {
/* 227:227 */      if (this.input.isKeyDown(this.lastKey)) {
/* 228:228 */        if (this.repeatTimer < System.currentTimeMillis()) {
/* 229:229 */          this.repeatTimer = (System.currentTimeMillis() + 50L);
/* 230:230 */          keyPressed(this.lastKey, this.lastChar);
/* 231:    */        }
/* 232:    */      } else {
/* 233:233 */        this.lastKey = -1;
/* 234:    */      }
/* 235:    */    }
/* 236:236 */    Rectangle oldClip = g.getClip();
/* 237:237 */    g.setWorldClip(this.x, this.y, this.width, this.height);
/* 238:    */    
/* 240:240 */    Color clr = g.getColor();
/* 241:    */    
/* 242:242 */    if (this.background != null) {
/* 243:243 */      g.setColor(this.background.multiply(clr));
/* 244:244 */      g.fillRect(this.x, this.y, this.width, this.height);
/* 245:    */    }
/* 246:246 */    g.setColor(this.text.multiply(clr));
/* 247:247 */    Font temp = g.getFont();
/* 248:    */    
/* 249:249 */    int cpos = this.font.getWidth(this.value.substring(0, this.cursorPos));
/* 250:250 */    int tx = 0;
/* 251:251 */    if (cpos > this.width) {
/* 252:252 */      tx = this.width - cpos - this.font.getWidth("_");
/* 253:    */    }
/* 254:    */    
/* 255:255 */    g.translate(tx + 2, 0.0F);
/* 256:256 */    g.setFont(this.font);
/* 257:257 */    g.drawString(this.value, this.x + 1, this.y + 1);
/* 258:    */    
/* 259:259 */    if ((hasFocus()) && (this.visibleCursor)) {
/* 260:260 */      g.drawString("_", this.x + 1 + cpos + 2, this.y + 1);
/* 261:    */    }
/* 262:    */    
/* 263:263 */    g.translate(-tx - 2, 0.0F);
/* 264:    */    
/* 265:265 */    if (this.border != null) {
/* 266:266 */      g.setColor(this.border.multiply(clr));
/* 267:267 */      g.drawRect(this.x, this.y, this.width, this.height);
/* 268:    */    }
/* 269:269 */    g.setColor(clr);
/* 270:270 */    g.setFont(temp);
/* 271:271 */    g.clearWorldClip();
/* 272:272 */    g.setClip(oldClip);
/* 273:    */  }
/* 274:    */  
/* 279:    */  public String getText()
/* 280:    */  {
/* 281:281 */    return this.value;
/* 282:    */  }
/* 283:    */  
/* 289:    */  public void setText(String value)
/* 290:    */  {
/* 291:291 */    this.value = value;
/* 292:292 */    if (this.cursorPos > value.length()) {
/* 293:293 */      this.cursorPos = value.length();
/* 294:    */    }
/* 295:    */  }
/* 296:    */  
/* 302:    */  public void setCursorPos(int pos)
/* 303:    */  {
/* 304:304 */    this.cursorPos = pos;
/* 305:305 */    if (this.cursorPos > this.value.length()) {
/* 306:306 */      this.cursorPos = this.value.length();
/* 307:    */    }
/* 308:    */  }
/* 309:    */  
/* 315:    */  public void setCursorVisible(boolean visibleCursor)
/* 316:    */  {
/* 317:317 */    this.visibleCursor = visibleCursor;
/* 318:    */  }
/* 319:    */  
/* 325:    */  public void setMaxLength(int length)
/* 326:    */  {
/* 327:327 */    this.maxCharacter = length;
/* 328:328 */    if (this.value.length() > this.maxCharacter) {
/* 329:329 */      this.value = this.value.substring(0, this.maxCharacter);
/* 330:    */    }
/* 331:    */  }
/* 332:    */  
/* 337:    */  protected void doPaste(String text)
/* 338:    */  {
/* 339:339 */    recordOldPosition();
/* 340:    */    
/* 341:341 */    for (int i = 0; i < text.length(); i++) {
/* 342:342 */      keyPressed(-1, text.charAt(i));
/* 343:    */    }
/* 344:    */  }
/* 345:    */  
/* 348:    */  protected void recordOldPosition()
/* 349:    */  {
/* 350:350 */    this.oldText = getText();
/* 351:351 */    this.oldCursorPos = this.cursorPos;
/* 352:    */  }
/* 353:    */  
/* 359:    */  protected void doUndo(int oldCursorPos, String oldText)
/* 360:    */  {
/* 361:361 */    if (oldText != null) {
/* 362:362 */      setText(oldText);
/* 363:363 */      setCursorPos(oldCursorPos);
/* 364:    */    }
/* 365:    */  }
/* 366:    */  
/* 369:    */  public void keyPressed(int key, char c)
/* 370:    */  {
/* 371:371 */    if (hasFocus()) {
/* 372:372 */      if (key != -1)
/* 373:    */      {
/* 374:374 */        if ((key == 47) && ((this.input.isKeyDown(29)) || (this.input.isKeyDown(157))))
/* 375:    */        {
/* 376:376 */          String text = Sys.getClipboard();
/* 377:377 */          if (text != null) {
/* 378:378 */            doPaste(text);
/* 379:    */          }
/* 380:380 */          return;
/* 381:    */        }
/* 382:382 */        if ((key == 44) && ((this.input.isKeyDown(29)) || (this.input.isKeyDown(157))))
/* 383:    */        {
/* 384:384 */          if (this.oldText != null) {
/* 385:385 */            doUndo(this.oldCursorPos, this.oldText);
/* 386:    */          }
/* 387:387 */          return;
/* 388:    */        }
/* 389:    */        
/* 391:391 */        if ((this.input.isKeyDown(29)) || (this.input.isKeyDown(157))) {
/* 392:392 */          return;
/* 393:    */        }
/* 394:394 */        if ((this.input.isKeyDown(56)) || (this.input.isKeyDown(184))) {
/* 395:395 */          return;
/* 396:    */        }
/* 397:    */      }
/* 398:    */      
/* 399:399 */      if (this.lastKey != key) {
/* 400:400 */        this.lastKey = key;
/* 401:401 */        this.repeatTimer = (System.currentTimeMillis() + 400L);
/* 402:    */      } else {
/* 403:403 */        this.repeatTimer = (System.currentTimeMillis() + 50L);
/* 404:    */      }
/* 405:405 */      this.lastChar = c;
/* 406:    */      
/* 407:407 */      if (key == 203) {
/* 408:408 */        if (this.cursorPos > 0) {
/* 409:409 */          this.cursorPos -= 1;
/* 410:    */        }
/* 411:    */        
/* 412:412 */        if (this.consume) {
/* 413:413 */          this.container.getInput().consumeEvent();
/* 414:    */        }
/* 415:415 */      } else if (key == 205) {
/* 416:416 */        if (this.cursorPos < this.value.length()) {
/* 417:417 */          this.cursorPos += 1;
/* 418:    */        }
/* 419:    */        
/* 420:420 */        if (this.consume) {
/* 421:421 */          this.container.getInput().consumeEvent();
/* 422:    */        }
/* 423:423 */      } else if (key == 14) {
/* 424:424 */        if ((this.cursorPos > 0) && (this.value.length() > 0)) {
/* 425:425 */          if (this.cursorPos < this.value.length()) {
/* 426:426 */            this.value = (this.value.substring(0, this.cursorPos - 1) + this.value.substring(this.cursorPos));
/* 427:    */          }
/* 428:    */          else {
/* 429:429 */            this.value = this.value.substring(0, this.cursorPos - 1);
/* 430:    */          }
/* 431:431 */          this.cursorPos -= 1;
/* 432:    */        }
/* 433:    */        
/* 434:434 */        if (this.consume) {
/* 435:435 */          this.container.getInput().consumeEvent();
/* 436:    */        }
/* 437:437 */      } else if (key == 211) {
/* 438:438 */        if (this.value.length() > this.cursorPos) {
/* 439:439 */          this.value = (this.value.substring(0, this.cursorPos) + this.value.substring(this.cursorPos + 1));
/* 440:    */        }
/* 441:    */        
/* 442:442 */        if (this.consume) {
/* 443:443 */          this.container.getInput().consumeEvent();
/* 444:    */        }
/* 445:445 */      } else if ((c < '') && (c > '\037') && (this.value.length() < this.maxCharacter)) {
/* 446:446 */        if (this.cursorPos < this.value.length()) {
/* 447:447 */          this.value = (this.value.substring(0, this.cursorPos) + c + this.value.substring(this.cursorPos));
/* 448:    */        }
/* 449:    */        else {
/* 450:450 */          this.value = (this.value.substring(0, this.cursorPos) + c);
/* 451:    */        }
/* 452:452 */        this.cursorPos += 1;
/* 453:    */        
/* 454:454 */        if (this.consume) {
/* 455:455 */          this.container.getInput().consumeEvent();
/* 456:    */        }
/* 457:457 */      } else if (key == 28) {
/* 458:458 */        notifyListeners();
/* 459:    */        
/* 460:460 */        if (this.consume) {
/* 461:461 */          this.container.getInput().consumeEvent();
/* 462:    */        }
/* 463:    */      }
/* 464:    */    }
/* 465:    */  }
/* 466:    */  
/* 470:    */  public void setFocus(boolean focus)
/* 471:    */  {
/* 472:472 */    this.lastKey = -1;
/* 473:    */    
/* 474:474 */    super.setFocus(focus);
/* 475:    */  }
/* 476:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.gui.TextField
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */