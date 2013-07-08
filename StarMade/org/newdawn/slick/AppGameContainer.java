/*   1:    */package org.newdawn.slick;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.OutputStream;
/*   5:    */import java.nio.ByteBuffer;
/*   6:    */import java.security.AccessController;
/*   7:    */import java.security.PrivilegedAction;
/*   8:    */import org.lwjgl.BufferUtils;
/*   9:    */import org.lwjgl.LWJGLException;
/*  10:    */import org.lwjgl.Sys;
/*  11:    */import org.lwjgl.input.Cursor;
/*  12:    */import org.lwjgl.input.Mouse;
/*  13:    */import org.lwjgl.openal.AL;
/*  14:    */import org.lwjgl.opengl.Display;
/*  15:    */import org.lwjgl.opengl.DisplayMode;
/*  16:    */import org.lwjgl.opengl.PixelFormat;
/*  17:    */import org.newdawn.slick.openal.SoundStore;
/*  18:    */import org.newdawn.slick.opengl.CursorLoader;
/*  19:    */import org.newdawn.slick.opengl.ImageData;
/*  20:    */import org.newdawn.slick.opengl.ImageIOImageData;
/*  21:    */import org.newdawn.slick.opengl.InternalTextureLoader;
/*  22:    */import org.newdawn.slick.opengl.LoadableImageData;
/*  23:    */import org.newdawn.slick.opengl.TGAImageData;
/*  24:    */import org.newdawn.slick.util.Log;
/*  25:    */import org.newdawn.slick.util.ResourceLoader;
/*  26:    */
/*  28:    */public class AppGameContainer
/*  29:    */  extends GameContainer
/*  30:    */{
/*  31:    */  protected DisplayMode originalDisplayMode;
/*  32:    */  protected DisplayMode targetDisplayMode;
/*  33:    */  
/*  34:    */  static
/*  35:    */  {
/*  36: 36 */    AccessController.doPrivileged(new PrivilegedAction() {
/*  37:    */      public Object run() {
/*  38:    */        try {
/*  39: 39 */          Display.getDisplayMode();
/*  40:    */        } catch (Exception e) {
/*  41: 41 */          Log.error(e);
/*  42:    */        }
/*  43: 43 */        return null;
/*  44:    */      }
/*  45:    */    });
/*  46:    */  }
/*  47:    */  
/*  52: 52 */  protected boolean updateOnlyOnVisible = true;
/*  53:    */  
/*  54: 54 */  protected boolean alphaSupport = false;
/*  55:    */  
/*  60:    */  public AppGameContainer(Game game)
/*  61:    */    throws SlickException
/*  62:    */  {
/*  63: 63 */    this(game, 640, 480, false);
/*  64:    */  }
/*  65:    */  
/*  73:    */  public AppGameContainer(Game game, int width, int height, boolean fullscreen)
/*  74:    */    throws SlickException
/*  75:    */  {
/*  76: 76 */    super(game);
/*  77:    */    
/*  78: 78 */    this.originalDisplayMode = Display.getDisplayMode();
/*  79:    */    
/*  80: 80 */    setDisplayMode(width, height, fullscreen);
/*  81:    */  }
/*  82:    */  
/*  87:    */  public boolean supportsAlphaInBackBuffer()
/*  88:    */  {
/*  89: 89 */    return this.alphaSupport;
/*  90:    */  }
/*  91:    */  
/*  96:    */  public void setTitle(String title)
/*  97:    */  {
/*  98: 98 */    Display.setTitle(title);
/*  99:    */  }
/* 100:    */  
/* 107:    */  public void setDisplayMode(int width, int height, boolean fullscreen)
/* 108:    */    throws SlickException
/* 109:    */  {
/* 110:110 */    if ((this.width == width) && (this.height == height) && (isFullscreen() == fullscreen)) {
/* 111:111 */      return;
/* 112:    */    }
/* 113:    */    try
/* 114:    */    {
/* 115:115 */      this.targetDisplayMode = null;
/* 116:116 */      if (fullscreen) {
/* 117:117 */        DisplayMode[] modes = Display.getAvailableDisplayModes();
/* 118:118 */        int freq = 0;
/* 119:    */        
/* 120:120 */        for (int i = 0; i < modes.length; i++) {
/* 121:121 */          DisplayMode current = modes[i];
/* 122:    */          
/* 123:123 */          if ((current.getWidth() == width) && (current.getHeight() == height)) {
/* 124:124 */            if (((this.targetDisplayMode == null) || (current.getFrequency() >= freq)) && (
/* 125:125 */              (this.targetDisplayMode == null) || (current.getBitsPerPixel() > this.targetDisplayMode.getBitsPerPixel()))) {
/* 126:126 */              this.targetDisplayMode = current;
/* 127:127 */              freq = this.targetDisplayMode.getFrequency();
/* 128:    */            }
/* 129:    */            
/* 134:134 */            if ((current.getBitsPerPixel() == this.originalDisplayMode.getBitsPerPixel()) && (current.getFrequency() == this.originalDisplayMode.getFrequency()))
/* 135:    */            {
/* 136:136 */              this.targetDisplayMode = current;
/* 137:137 */              break;
/* 138:    */            }
/* 139:    */          }
/* 140:    */        }
/* 141:    */      } else {
/* 142:142 */        this.targetDisplayMode = new DisplayMode(width, height);
/* 143:    */      }
/* 144:    */      
/* 145:145 */      if (this.targetDisplayMode == null) {
/* 146:146 */        throw new SlickException("Failed to find value mode: " + width + "x" + height + " fs=" + fullscreen);
/* 147:    */      }
/* 148:    */      
/* 149:149 */      this.width = width;
/* 150:150 */      this.height = height;
/* 151:    */      
/* 152:152 */      Display.setDisplayMode(this.targetDisplayMode);
/* 153:153 */      Display.setFullscreen(fullscreen);
/* 154:    */      
/* 155:155 */      if (Display.isCreated()) {
/* 156:156 */        initGL();
/* 157:157 */        enterOrtho();
/* 158:    */      }
/* 159:    */      
/* 160:160 */      if (this.targetDisplayMode.getBitsPerPixel() == 16) {
/* 161:161 */        InternalTextureLoader.get().set16BitMode();
/* 162:    */      }
/* 163:    */    } catch (LWJGLException e) {
/* 164:164 */      throw new SlickException("Unable to setup mode " + width + "x" + height + " fullscreen=" + fullscreen, e);
/* 165:    */    }
/* 166:    */    
/* 167:167 */    getDelta();
/* 168:    */  }
/* 169:    */  
/* 174:    */  public boolean isFullscreen()
/* 175:    */  {
/* 176:176 */    return Display.isFullscreen();
/* 177:    */  }
/* 178:    */  
/* 184:    */  public void setFullscreen(boolean fullscreen)
/* 185:    */    throws SlickException
/* 186:    */  {
/* 187:187 */    if (isFullscreen() == fullscreen) {
/* 188:188 */      return;
/* 189:    */    }
/* 190:    */    
/* 191:191 */    if (!fullscreen) {
/* 192:    */      try {
/* 193:193 */        Display.setFullscreen(fullscreen);
/* 194:    */      } catch (LWJGLException e) {
/* 195:195 */        throw new SlickException("Unable to set fullscreen=" + fullscreen, e);
/* 196:    */      }
/* 197:    */    } else {
/* 198:198 */      setDisplayMode(this.width, this.height, fullscreen);
/* 199:    */    }
/* 200:200 */    getDelta();
/* 201:    */  }
/* 202:    */  
/* 203:    */  public void setMouseCursor(String ref, int hotSpotX, int hotSpotY)
/* 204:    */    throws SlickException
/* 205:    */  {
/* 206:    */    try
/* 207:    */    {
/* 208:208 */      Cursor cursor = CursorLoader.get().getCursor(ref, hotSpotX, hotSpotY);
/* 209:209 */      Mouse.setNativeCursor(cursor);
/* 210:    */    } catch (Throwable e) {
/* 211:211 */      Log.error("Failed to load and apply cursor.", e);
/* 212:212 */      throw new SlickException("Failed to set mouse cursor", e);
/* 213:    */    }
/* 214:    */  }
/* 215:    */  
/* 216:    */  public void setMouseCursor(ImageData data, int hotSpotX, int hotSpotY)
/* 217:    */    throws SlickException
/* 218:    */  {
/* 219:    */    try
/* 220:    */    {
/* 221:221 */      Cursor cursor = CursorLoader.get().getCursor(data, hotSpotX, hotSpotY);
/* 222:222 */      Mouse.setNativeCursor(cursor);
/* 223:    */    } catch (Throwable e) {
/* 224:224 */      Log.error("Failed to load and apply cursor.", e);
/* 225:225 */      throw new SlickException("Failed to set mouse cursor", e);
/* 226:    */    }
/* 227:    */  }
/* 228:    */  
/* 229:    */  public void setMouseCursor(Cursor cursor, int hotSpotX, int hotSpotY)
/* 230:    */    throws SlickException
/* 231:    */  {
/* 232:    */    try
/* 233:    */    {
/* 234:234 */      Mouse.setNativeCursor(cursor);
/* 235:    */    } catch (Throwable e) {
/* 236:236 */      Log.error("Failed to load and apply cursor.", e);
/* 237:237 */      throw new SlickException("Failed to set mouse cursor", e);
/* 238:    */    }
/* 239:    */  }
/* 240:    */  
/* 246:    */  private int get2Fold(int fold)
/* 247:    */  {
/* 248:248 */    int ret = 2;
/* 249:249 */    while (ret < fold) {
/* 250:250 */      ret *= 2;
/* 251:    */    }
/* 252:252 */    return ret;
/* 253:    */  }
/* 254:    */  
/* 255:    */  public void setMouseCursor(Image image, int hotSpotX, int hotSpotY)
/* 256:    */    throws SlickException
/* 257:    */  {
/* 258:    */    try
/* 259:    */    {
/* 260:260 */      Image temp = new Image(get2Fold(image.getWidth()), get2Fold(image.getHeight()));
/* 261:261 */      Graphics g = temp.getGraphics();
/* 262:    */      
/* 263:263 */      ByteBuffer buffer = BufferUtils.createByteBuffer(temp.getWidth() * temp.getHeight() * 4);
/* 264:264 */      g.drawImage(image.getFlippedCopy(false, true), 0.0F, 0.0F);
/* 265:265 */      g.flush();
/* 266:266 */      g.getArea(0, 0, temp.getWidth(), temp.getHeight(), buffer);
/* 267:    */      
/* 268:268 */      Cursor cursor = CursorLoader.get().getCursor(buffer, hotSpotX, hotSpotY, temp.getWidth(), image.getHeight());
/* 269:269 */      Mouse.setNativeCursor(cursor);
/* 270:    */    } catch (Throwable e) {
/* 271:271 */      Log.error("Failed to load and apply cursor.", e);
/* 272:272 */      throw new SlickException("Failed to set mouse cursor", e);
/* 273:    */    }
/* 274:    */  }
/* 275:    */  
/* 277:    */  public void reinit()
/* 278:    */    throws SlickException
/* 279:    */  {
/* 280:280 */    InternalTextureLoader.get().clear();
/* 281:281 */    SoundStore.get().clear();
/* 282:282 */    initSystem();
/* 283:283 */    enterOrtho();
/* 284:    */    try
/* 285:    */    {
/* 286:286 */      this.game.init(this);
/* 287:    */    } catch (SlickException e) {
/* 288:288 */      Log.error(e);
/* 289:289 */      this.running = false;
/* 290:    */    }
/* 291:    */  }
/* 292:    */  
/* 297:    */  private void tryCreateDisplay(PixelFormat format)
/* 298:    */    throws LWJGLException
/* 299:    */  {
/* 300:300 */    if (SHARED_DRAWABLE == null)
/* 301:    */    {
/* 302:302 */      Display.create(format);
/* 303:    */    }
/* 304:    */    else
/* 305:    */    {
/* 306:306 */      Display.create(format, SHARED_DRAWABLE);
/* 307:    */    }
/* 308:    */  }
/* 309:    */  
/* 312:    */  public void start()
/* 313:    */    throws SlickException
/* 314:    */  {
/* 315:    */    try
/* 316:    */    {
/* 317:317 */      setup();
/* 318:    */      
/* 319:319 */      getDelta();
/* 320:320 */      while (running()) {
/* 321:321 */        gameLoop();
/* 322:    */      }
/* 323:    */    } finally {
/* 324:324 */      destroy();
/* 325:    */    }
/* 326:    */    
/* 327:327 */    if (this.forceExit) {
/* 328:328 */      System.exit(0);
/* 329:    */    }
/* 330:    */  }
/* 331:    */  
/* 335:    */  protected void setup()
/* 336:    */    throws SlickException
/* 337:    */  {
/* 338:338 */    if (this.targetDisplayMode == null) {
/* 339:339 */      setDisplayMode(640, 480, false);
/* 340:    */    }
/* 341:    */    
/* 342:342 */    Display.setTitle(this.game.getTitle());
/* 343:    */    
/* 344:344 */    Log.info("LWJGL Version: " + Sys.getVersion());
/* 345:345 */    Log.info("OriginalDisplayMode: " + this.originalDisplayMode);
/* 346:346 */    Log.info("TargetDisplayMode: " + this.targetDisplayMode);
/* 347:    */    
/* 348:348 */    AccessController.doPrivileged(new PrivilegedAction() {
/* 349:    */      public Object run() {
/* 350:    */        try {
/* 351:351 */          PixelFormat format = new PixelFormat(8, 8, GameContainer.stencil ? 8 : 0, AppGameContainer.this.samples);
/* 352:    */          
/* 353:353 */          AppGameContainer.this.tryCreateDisplay(format);
/* 354:354 */          AppGameContainer.this.supportsMultiSample = true;
/* 355:    */        } catch (Exception e) {
/* 356:356 */          Display.destroy();
/* 357:    */          try
/* 358:    */          {
/* 359:359 */            PixelFormat format = new PixelFormat(8, 8, GameContainer.stencil ? 8 : 0);
/* 360:    */            
/* 361:361 */            AppGameContainer.this.tryCreateDisplay(format);
/* 362:362 */            AppGameContainer.this.alphaSupport = false;
/* 363:    */          } catch (Exception e2) {
/* 364:364 */            Display.destroy();
/* 365:    */            try
/* 366:    */            {
/* 367:367 */              AppGameContainer.this.tryCreateDisplay(new PixelFormat());
/* 368:    */            } catch (Exception e3) {
/* 369:369 */              Log.error(e3);
/* 370:    */            }
/* 371:    */          }
/* 372:    */        }
/* 373:    */        
/* 374:374 */        return null;
/* 375:    */      }
/* 376:    */    });
/* 377:377 */    if (!Display.isCreated()) {
/* 378:378 */      throw new SlickException("Failed to initialise the LWJGL display");
/* 379:    */    }
/* 380:    */    
/* 381:381 */    initSystem();
/* 382:382 */    enterOrtho();
/* 383:    */    try
/* 384:    */    {
/* 385:385 */      getInput().initControllers();
/* 386:    */    } catch (SlickException e) {
/* 387:387 */      Log.info("Controllers not available");
/* 388:    */    } catch (Throwable e) {
/* 389:389 */      Log.info("Controllers not available");
/* 390:    */    }
/* 391:    */    try
/* 392:    */    {
/* 393:393 */      this.game.init(this);
/* 394:    */    } catch (SlickException e) {
/* 395:395 */      Log.error(e);
/* 396:396 */      this.running = false;
/* 397:    */    }
/* 398:    */  }
/* 399:    */  
/* 403:    */  protected void gameLoop()
/* 404:    */    throws SlickException
/* 405:    */  {
/* 406:406 */    int delta = getDelta();
/* 407:407 */    if ((!Display.isVisible()) && (this.updateOnlyOnVisible))
/* 408:408 */      try { Thread.sleep(100L);
/* 409:    */      } catch (Exception e) {} else {
/* 410:    */      try {
/* 411:411 */        updateAndRender(delta);
/* 412:    */      } catch (SlickException e) {
/* 413:413 */        Log.error(e);
/* 414:414 */        this.running = false;
/* 415:415 */        return;
/* 416:    */      }
/* 417:    */    }
/* 418:    */    
/* 419:419 */    updateFPS();
/* 420:    */    
/* 421:421 */    Display.update();
/* 422:    */    
/* 423:423 */    if ((Display.isCloseRequested()) && 
/* 424:424 */      (this.game.closeRequested())) {
/* 425:425 */      this.running = false;
/* 426:    */    }
/* 427:    */  }
/* 428:    */  
/* 432:    */  public void setUpdateOnlyWhenVisible(boolean updateOnlyWhenVisible)
/* 433:    */  {
/* 434:434 */    this.updateOnlyOnVisible = updateOnlyWhenVisible;
/* 435:    */  }
/* 436:    */  
/* 439:    */  public boolean isUpdatingOnlyWhenVisible()
/* 440:    */  {
/* 441:441 */    return this.updateOnlyOnVisible;
/* 442:    */  }
/* 443:    */  
/* 445:    */  public void setIcon(String ref)
/* 446:    */    throws SlickException
/* 447:    */  {
/* 448:448 */    setIcons(new String[] { ref });
/* 449:    */  }
/* 450:    */  
/* 453:    */  public void setMouseGrabbed(boolean grabbed)
/* 454:    */  {
/* 455:455 */    Mouse.setGrabbed(grabbed);
/* 456:    */  }
/* 457:    */  
/* 460:    */  public boolean isMouseGrabbed()
/* 461:    */  {
/* 462:462 */    return Mouse.isGrabbed();
/* 463:    */  }
/* 464:    */  
/* 468:    */  public boolean hasFocus()
/* 469:    */  {
/* 470:470 */    return Display.isActive();
/* 471:    */  }
/* 472:    */  
/* 475:    */  public int getScreenHeight()
/* 476:    */  {
/* 477:477 */    return this.originalDisplayMode.getHeight();
/* 478:    */  }
/* 479:    */  
/* 482:    */  public int getScreenWidth()
/* 483:    */  {
/* 484:484 */    return this.originalDisplayMode.getWidth();
/* 485:    */  }
/* 486:    */  
/* 489:    */  public void destroy()
/* 490:    */  {
/* 491:491 */    Display.destroy();
/* 492:492 */    AL.destroy();
/* 493:    */  }
/* 494:    */  
/* 511:    */  public void setIcons(String[] refs)
/* 512:    */    throws SlickException
/* 513:    */  {
/* 514:514 */    ByteBuffer[] bufs = new ByteBuffer[refs.length];
/* 515:515 */    for (int i = 0; i < refs.length; i++)
/* 516:    */    {
/* 517:517 */      boolean flip = true;
/* 518:    */      LoadableImageData data;
/* 519:519 */      LoadableImageData data; if (refs[i].endsWith(".tga")) {
/* 520:520 */        data = new TGAImageData();
/* 521:    */      } else {
/* 522:522 */        flip = false;
/* 523:523 */        data = new ImageIOImageData();
/* 524:    */      }
/* 525:    */      try
/* 526:    */      {
/* 527:527 */        bufs[i] = data.loadImage(ResourceLoader.getResourceAsStream(refs[i]), flip, false, null);
/* 528:    */      } catch (Exception e) {
/* 529:529 */        Log.error(e);
/* 530:530 */        throw new SlickException("Failed to set the icon");
/* 531:    */      }
/* 532:    */    }
/* 533:    */    
/* 534:534 */    Display.setIcon(bufs);
/* 535:    */  }
/* 536:    */  
/* 538:    */  public void setDefaultMouseCursor()
/* 539:    */  {
/* 540:    */    try
/* 541:    */    {
/* 542:542 */      Mouse.setNativeCursor(null);
/* 543:    */    } catch (LWJGLException e) {
/* 544:544 */      Log.error("Failed to reset mouse cursor", e);
/* 545:    */    }
/* 546:    */  }
/* 547:    */  
/* 548:    */  private class NullOutputStream
/* 549:    */    extends OutputStream
/* 550:    */  {
/* 551:    */    private NullOutputStream() {}
/* 552:    */    
/* 553:    */    public void write(int b)
/* 554:    */      throws IOException
/* 555:    */    {}
/* 556:    */  }
/* 557:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.AppGameContainer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */