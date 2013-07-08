/*   1:    */package org.newdawn.slick;
/*   2:    */
/*   3:    */import java.applet.Applet;
/*   4:    */import java.awt.BorderLayout;
/*   5:    */import java.awt.Canvas;
/*   6:    */import java.awt.Color;
/*   7:    */import java.awt.Font;
/*   8:    */import java.awt.GridLayout;
/*   9:    */import java.awt.Label;
/*  10:    */import java.awt.Panel;
/*  11:    */import java.awt.TextArea;
/*  12:    */import java.io.PrintWriter;
/*  13:    */import java.io.StringWriter;
/*  14:    */import java.nio.ByteBuffer;
/*  15:    */import org.lwjgl.BufferUtils;
/*  16:    */import org.lwjgl.LWJGLException;
/*  17:    */import org.lwjgl.input.Cursor;
/*  18:    */import org.lwjgl.input.Mouse;
/*  19:    */import org.lwjgl.opengl.Display;
/*  20:    */import org.lwjgl.opengl.DisplayMode;
/*  21:    */import org.lwjgl.opengl.GL11;
/*  22:    */import org.lwjgl.opengl.PixelFormat;
/*  23:    */import org.newdawn.slick.openal.SoundStore;
/*  24:    */import org.newdawn.slick.opengl.CursorLoader;
/*  25:    */import org.newdawn.slick.opengl.ImageData;
/*  26:    */import org.newdawn.slick.opengl.InternalTextureLoader;
/*  27:    */import org.newdawn.slick.util.Log;
/*  28:    */
/*  36:    */public class AppletGameContainer
/*  37:    */  extends Applet
/*  38:    */{
/*  39:    */  protected ContainerPanel canvas;
/*  40:    */  protected Container container;
/*  41:    */  protected Canvas displayParent;
/*  42:    */  protected Thread gameThread;
/*  43:    */  protected boolean alphaSupport;
/*  44:    */  
/*  45:    */  public AppletGameContainer()
/*  46:    */  {
/*  47: 47 */    this.alphaSupport = true;
/*  48:    */  }
/*  49:    */  
/*  51:    */  public void destroy()
/*  52:    */  {
/*  53: 53 */    if (this.displayParent != null) {
/*  54: 54 */      remove(this.displayParent);
/*  55:    */    }
/*  56: 56 */    super.destroy();
/*  57:    */    
/*  58: 58 */    Log.info("Clear up");
/*  59:    */  }
/*  60:    */  
/*  63:    */  private void destroyLWJGL()
/*  64:    */  {
/*  65: 65 */    this.container.stopApplet();
/*  66:    */    try
/*  67:    */    {
/*  68: 68 */      this.gameThread.join();
/*  69:    */    } catch (InterruptedException e) {
/*  70: 70 */      Log.error(e);
/*  71:    */    }
/*  72:    */  }
/*  73:    */  
/*  78:    */  public void start() {}
/*  79:    */  
/*  83:    */  public void startLWJGL()
/*  84:    */  {
/*  85: 85 */    if (this.gameThread != null) {
/*  86: 86 */      return;
/*  87:    */    }
/*  88:    */    
/*  89: 89 */    this.gameThread = new Thread() {
/*  90:    */      public void run() {
/*  91:    */        try {
/*  92: 92 */          AppletGameContainer.this.canvas.start();
/*  93:    */        }
/*  94:    */        catch (Exception e) {
/*  95: 95 */          e.printStackTrace();
/*  96: 96 */          if (Display.isCreated()) {
/*  97: 97 */            Display.destroy();
/*  98:    */          }
/*  99: 99 */          AppletGameContainer.this.displayParent.setVisible(false);
/* 100:100 */          AppletGameContainer.this.add(new AppletGameContainer.ConsolePanel(AppletGameContainer.this, e));
/* 101:101 */          AppletGameContainer.this.validate();
/* 102:    */        }
/* 103:    */        
/* 104:    */      }
/* 105:105 */    };
/* 106:106 */    this.gameThread.start();
/* 107:    */  }
/* 108:    */  
/* 112:    */  public void stop() {}
/* 113:    */  
/* 117:    */  public void init()
/* 118:    */  {
/* 119:119 */    removeAll();
/* 120:120 */    setLayout(new BorderLayout());
/* 121:121 */    setIgnoreRepaint(true);
/* 122:    */    try
/* 123:    */    {
/* 124:124 */      Game game = (Game)Class.forName(getParameter("game")).newInstance();
/* 125:    */      
/* 126:126 */      this.container = new Container(game);
/* 127:127 */      this.canvas = new ContainerPanel(this.container);
/* 128:128 */      this.displayParent = new Canvas() {
/* 129:    */        public final void addNotify() {
/* 130:130 */          super.addNotify();
/* 131:131 */          AppletGameContainer.this.startLWJGL();
/* 132:    */        }
/* 133:    */        
/* 134:134 */        public final void removeNotify() { AppletGameContainer.this.destroyLWJGL();
/* 135:135 */          super.removeNotify();
/* 136:    */        }
/* 137:    */        
/* 139:139 */      };
/* 140:140 */      this.displayParent.setSize(getWidth(), getHeight());
/* 141:141 */      add(this.displayParent);
/* 142:142 */      this.displayParent.setFocusable(true);
/* 143:143 */      this.displayParent.requestFocus();
/* 144:144 */      this.displayParent.setIgnoreRepaint(true);
/* 145:145 */      setVisible(true);
/* 146:    */    } catch (Exception e) {
/* 147:147 */      Log.error(e);
/* 148:148 */      throw new RuntimeException("Unable to create game container");
/* 149:    */    }
/* 150:    */  }
/* 151:    */  
/* 156:    */  public GameContainer getContainer()
/* 157:    */  {
/* 158:158 */    return this.container;
/* 159:    */  }
/* 160:    */  
/* 166:    */  public class ContainerPanel
/* 167:    */  {
/* 168:    */    private AppletGameContainer.Container container;
/* 169:    */    
/* 174:    */    public ContainerPanel(AppletGameContainer.Container container)
/* 175:    */    {
/* 176:176 */      this.container = container;
/* 177:    */    }
/* 178:    */    
/* 182:    */    private void createDisplay()
/* 183:    */      throws Exception
/* 184:    */    {
/* 185:    */      try
/* 186:    */      {
/* 187:187 */        Display.create(new PixelFormat(8, 8, GameContainer.stencil ? 8 : 0));
/* 188:188 */        AppletGameContainer.this.alphaSupport = true;
/* 189:    */      }
/* 190:    */      catch (Exception e) {
/* 191:191 */        AppletGameContainer.this.alphaSupport = false;
/* 192:192 */        Display.destroy();
/* 193:    */        
/* 194:194 */        Display.create();
/* 195:    */      }
/* 196:    */    }
/* 197:    */    
/* 201:    */    public void start()
/* 202:    */      throws Exception
/* 203:    */    {
/* 204:204 */      Display.setParent(AppletGameContainer.this.displayParent);
/* 205:205 */      Display.setVSyncEnabled(true);
/* 206:    */      try
/* 207:    */      {
/* 208:208 */        createDisplay();
/* 209:    */      } catch (LWJGLException e) {
/* 210:210 */        e.printStackTrace();
/* 211:    */        
/* 212:212 */        Thread.sleep(1000L);
/* 213:213 */        createDisplay();
/* 214:    */      }
/* 215:    */      
/* 216:216 */      initGL();
/* 217:217 */      AppletGameContainer.this.displayParent.requestFocus();
/* 218:218 */      this.container.runloop();
/* 219:    */    }
/* 220:    */    
/* 222:    */    protected void initGL()
/* 223:    */    {
/* 224:    */      try
/* 225:    */      {
/* 226:226 */        InternalTextureLoader.get().clear();
/* 227:227 */        SoundStore.get().clear();
/* 228:    */        
/* 229:229 */        this.container.initApplet();
/* 230:    */      } catch (Exception e) {
/* 231:231 */        Log.error(e);
/* 232:232 */        this.container.stopApplet();
/* 233:    */      }
/* 234:    */    }
/* 235:    */  }
/* 236:    */  
/* 244:    */  public class Container
/* 245:    */    extends GameContainer
/* 246:    */  {
/* 247:    */    public Container(Game game)
/* 248:    */    {
/* 249:249 */      super();
/* 250:    */      
/* 251:251 */      this.width = AppletGameContainer.this.getWidth();
/* 252:252 */      this.height = AppletGameContainer.this.getHeight();
/* 253:    */    }
/* 254:    */    
/* 258:    */    public void initApplet()
/* 259:    */      throws SlickException
/* 260:    */    {
/* 261:261 */      initSystem();
/* 262:262 */      enterOrtho();
/* 263:    */      try
/* 264:    */      {
/* 265:265 */        getInput().initControllers();
/* 266:    */      } catch (SlickException e) {
/* 267:267 */        Log.info("Controllers not available");
/* 268:    */      } catch (Throwable e) {
/* 269:269 */        Log.info("Controllers not available");
/* 270:    */      }
/* 271:    */      
/* 272:272 */      this.game.init(this);
/* 273:273 */      getDelta();
/* 274:    */    }
/* 275:    */    
/* 280:    */    public boolean isRunning()
/* 281:    */    {
/* 282:282 */      return this.running;
/* 283:    */    }
/* 284:    */    
/* 287:    */    public void stopApplet()
/* 288:    */    {
/* 289:289 */      this.running = false;
/* 290:    */    }
/* 291:    */    
/* 294:    */    public int getScreenHeight()
/* 295:    */    {
/* 296:296 */      return 0;
/* 297:    */    }
/* 298:    */    
/* 301:    */    public int getScreenWidth()
/* 302:    */    {
/* 303:303 */      return 0;
/* 304:    */    }
/* 305:    */    
/* 310:    */    public boolean supportsAlphaInBackBuffer()
/* 311:    */    {
/* 312:312 */      return AppletGameContainer.this.alphaSupport;
/* 313:    */    }
/* 314:    */    
/* 317:    */    public boolean hasFocus()
/* 318:    */    {
/* 319:319 */      return true;
/* 320:    */    }
/* 321:    */    
/* 325:    */    public Applet getApplet()
/* 326:    */    {
/* 327:327 */      return AppletGameContainer.this;
/* 328:    */    }
/* 329:    */    
/* 333:    */    public void setIcon(String ref)
/* 334:    */      throws SlickException
/* 335:    */    {}
/* 336:    */    
/* 339:    */    public void setMouseGrabbed(boolean grabbed)
/* 340:    */    {
/* 341:341 */      Mouse.setGrabbed(grabbed);
/* 342:    */    }
/* 343:    */    
/* 346:    */    public boolean isMouseGrabbed()
/* 347:    */    {
/* 348:348 */      return Mouse.isGrabbed();
/* 349:    */    }
/* 350:    */    
/* 352:    */    public void setMouseCursor(String ref, int hotSpotX, int hotSpotY)
/* 353:    */      throws SlickException
/* 354:    */    {
/* 355:    */      try
/* 356:    */      {
/* 357:357 */        Cursor cursor = CursorLoader.get().getCursor(ref, hotSpotX, hotSpotY);
/* 358:358 */        Mouse.setNativeCursor(cursor);
/* 359:    */      } catch (Throwable e) {
/* 360:360 */        Log.error("Failed to load and apply cursor.", e);
/* 361:361 */        throw new SlickException("Failed to set mouse cursor", e);
/* 362:    */      }
/* 363:    */    }
/* 364:    */    
/* 370:    */    private int get2Fold(int fold)
/* 371:    */    {
/* 372:372 */      int ret = 2;
/* 373:373 */      while (ret < fold) {
/* 374:374 */        ret *= 2;
/* 375:    */      }
/* 376:376 */      return ret;
/* 377:    */    }
/* 378:    */    
/* 379:    */    public void setMouseCursor(Image image, int hotSpotX, int hotSpotY)
/* 380:    */      throws SlickException
/* 381:    */    {
/* 382:    */      try
/* 383:    */      {
/* 384:384 */        Image temp = new Image(get2Fold(image.getWidth()), get2Fold(image.getHeight()));
/* 385:385 */        Graphics g = temp.getGraphics();
/* 386:    */        
/* 387:387 */        ByteBuffer buffer = BufferUtils.createByteBuffer(temp.getWidth() * temp.getHeight() * 4);
/* 388:388 */        g.drawImage(image.getFlippedCopy(false, true), 0.0F, 0.0F);
/* 389:389 */        g.flush();
/* 390:390 */        g.getArea(0, 0, temp.getWidth(), temp.getHeight(), buffer);
/* 391:    */        
/* 392:392 */        Cursor cursor = CursorLoader.get().getCursor(buffer, hotSpotX, hotSpotY, temp.getWidth(), temp.getHeight());
/* 393:393 */        Mouse.setNativeCursor(cursor);
/* 394:    */      } catch (Throwable e) {
/* 395:395 */        Log.error("Failed to load and apply cursor.", e);
/* 396:396 */        throw new SlickException("Failed to set mouse cursor", e);
/* 397:    */      }
/* 398:    */    }
/* 399:    */    
/* 402:    */    public void setIcons(String[] refs)
/* 403:    */      throws SlickException
/* 404:    */    {}
/* 405:    */    
/* 407:    */    public void setMouseCursor(ImageData data, int hotSpotX, int hotSpotY)
/* 408:    */      throws SlickException
/* 409:    */    {
/* 410:    */      try
/* 411:    */      {
/* 412:412 */        Cursor cursor = CursorLoader.get().getCursor(data, hotSpotX, hotSpotY);
/* 413:413 */        Mouse.setNativeCursor(cursor);
/* 414:    */      } catch (Throwable e) {
/* 415:415 */        Log.error("Failed to load and apply cursor.", e);
/* 416:416 */        throw new SlickException("Failed to set mouse cursor", e);
/* 417:    */      }
/* 418:    */    }
/* 419:    */    
/* 420:    */    public void setMouseCursor(Cursor cursor, int hotSpotX, int hotSpotY)
/* 421:    */      throws SlickException
/* 422:    */    {
/* 423:    */      try
/* 424:    */      {
/* 425:425 */        Mouse.setNativeCursor(cursor);
/* 426:    */      } catch (Throwable e) {
/* 427:427 */        Log.error("Failed to load and apply cursor.", e);
/* 428:428 */        throw new SlickException("Failed to set mouse cursor", e);
/* 429:    */      }
/* 430:    */    }
/* 431:    */    
/* 434:    */    public void setDefaultMouseCursor() {}
/* 435:    */    
/* 437:    */    public boolean isFullscreen()
/* 438:    */    {
/* 439:439 */      return Display.isFullscreen();
/* 440:    */    }
/* 441:    */    
/* 442:    */    public void setFullscreen(boolean fullscreen) throws SlickException {
/* 443:443 */      if (fullscreen == isFullscreen()) {
/* 444:444 */        return;
/* 445:    */      }
/* 446:    */      try
/* 447:    */      {
/* 448:448 */        if (fullscreen)
/* 449:    */        {
/* 450:450 */          int screenWidth = Display.getDisplayMode().getWidth();
/* 451:451 */          int screenHeight = Display.getDisplayMode().getHeight();
/* 452:    */          
/* 454:454 */          float gameAspectRatio = this.width / this.height;
/* 455:455 */          float screenAspectRatio = screenWidth / screenHeight;
/* 456:    */          
/* 457:    */          int newHeight;
/* 458:    */          
/* 459:    */          int newWidth;
/* 460:    */          
/* 461:    */          int newHeight;
/* 462:    */          
/* 463:463 */          if (gameAspectRatio >= screenAspectRatio) {
/* 464:464 */            int newWidth = screenWidth;
/* 465:465 */            newHeight = (int)(this.height / (this.width / screenWidth));
/* 466:    */          } else {
/* 467:467 */            newWidth = (int)(this.width / (this.height / screenHeight));
/* 468:468 */            newHeight = screenHeight;
/* 469:    */          }
/* 470:    */          
/* 472:472 */          int xoffset = (screenWidth - newWidth) / 2;
/* 473:473 */          int yoffset = (screenHeight - newHeight) / 2;
/* 474:    */          
/* 476:476 */          GL11.glViewport(xoffset, yoffset, newWidth, newHeight);
/* 477:    */          
/* 478:478 */          enterOrtho();
/* 479:    */          
/* 481:481 */          getInput().setOffset(-xoffset * this.width / newWidth, -yoffset * this.height / newHeight);
/* 482:    */          
/* 485:485 */          getInput().setScale(this.width / newWidth, this.height / newHeight);
/* 486:    */          
/* 488:488 */          this.width = screenWidth;
/* 489:489 */          this.height = screenHeight;
/* 490:490 */          Display.setFullscreen(true);
/* 491:    */        }
/* 492:    */        else {
/* 493:493 */          getInput().setOffset(0.0F, 0.0F);
/* 494:494 */          getInput().setScale(1.0F, 1.0F);
/* 495:495 */          this.width = AppletGameContainer.this.getWidth();
/* 496:496 */          this.height = AppletGameContainer.this.getHeight();
/* 497:497 */          GL11.glViewport(0, 0, this.width, this.height);
/* 498:    */          
/* 499:499 */          enterOrtho();
/* 500:    */          
/* 501:501 */          Display.setFullscreen(false);
/* 502:    */        }
/* 503:    */      } catch (LWJGLException e) {
/* 504:504 */        Log.error(e);
/* 505:    */      }
/* 506:    */    }
/* 507:    */    
/* 512:    */    public void runloop()
/* 513:    */      throws Exception
/* 514:    */    {
/* 515:515 */      while (this.running) {
/* 516:516 */        int delta = getDelta();
/* 517:    */        
/* 518:518 */        updateAndRender(delta);
/* 519:    */        
/* 520:520 */        updateFPS();
/* 521:521 */        Display.update();
/* 522:    */      }
/* 523:    */      
/* 524:524 */      Display.destroy();
/* 525:    */    }
/* 526:    */  }
/* 527:    */  
/* 533:    */  public class ConsolePanel
/* 534:    */    extends Panel
/* 535:    */  {
/* 536:536 */    TextArea textArea = new TextArea();
/* 537:    */    
/* 542:    */    public ConsolePanel(Exception e)
/* 543:    */    {
/* 544:544 */      setLayout(new BorderLayout());
/* 545:545 */      setBackground(Color.black);
/* 546:546 */      setForeground(Color.white);
/* 547:    */      
/* 548:548 */      Font consoleFont = new Font("Arial", 1, 14);
/* 549:    */      
/* 550:550 */      Label slickLabel = new Label("SLICK CONSOLE", 1);
/* 551:551 */      slickLabel.setFont(consoleFont);
/* 552:552 */      add(slickLabel, "First");
/* 553:    */      
/* 554:554 */      StringWriter sw = new StringWriter();
/* 555:555 */      e.printStackTrace(new PrintWriter(sw));
/* 556:    */      
/* 557:557 */      this.textArea.setText(sw.toString());
/* 558:558 */      this.textArea.setEditable(false);
/* 559:559 */      add(this.textArea, "Center");
/* 560:    */      
/* 562:562 */      add(new Panel(), "Before");
/* 563:563 */      add(new Panel(), "After");
/* 564:    */      
/* 565:565 */      Panel bottomPanel = new Panel();
/* 566:566 */      bottomPanel.setLayout(new GridLayout(0, 1));
/* 567:567 */      Label infoLabel1 = new Label("An error occured while running the applet.", 1);
/* 568:568 */      Label infoLabel2 = new Label("Plese contact support to resolve this issue.", 1);
/* 569:569 */      infoLabel1.setFont(consoleFont);
/* 570:570 */      infoLabel2.setFont(consoleFont);
/* 571:571 */      bottomPanel.add(infoLabel1);
/* 572:572 */      bottomPanel.add(infoLabel2);
/* 573:573 */      add(bottomPanel, "Last");
/* 574:    */    }
/* 575:    */  }
/* 576:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.AppletGameContainer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */