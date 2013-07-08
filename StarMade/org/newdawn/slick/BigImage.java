/*   1:    */package org.newdawn.slick;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.nio.ByteBuffer;
/*   5:    */import java.nio.IntBuffer;
/*   6:    */import org.lwjgl.BufferUtils;
/*   7:    */import org.newdawn.slick.opengl.ImageData;
/*   8:    */import org.newdawn.slick.opengl.ImageDataFactory;
/*   9:    */import org.newdawn.slick.opengl.LoadableImageData;
/*  10:    */import org.newdawn.slick.opengl.Texture;
/*  11:    */import org.newdawn.slick.opengl.renderer.Renderer;
/*  12:    */import org.newdawn.slick.opengl.renderer.SGL;
/*  13:    */import org.newdawn.slick.util.OperationNotSupportedException;
/*  14:    */import org.newdawn.slick.util.ResourceLoader;
/*  15:    */
/*  31:    */public class BigImage
/*  32:    */  extends Image
/*  33:    */{
/*  34: 34 */  protected static SGL GL = ;
/*  35:    */  private static Image lastBind;
/*  36:    */  private Image[][] images;
/*  37:    */  private int xcount;
/*  38:    */  private int ycount;
/*  39:    */  private int realWidth;
/*  40:    */  private int realHeight;
/*  41:    */  
/*  42:    */  public static final int getMaxSingleImageSize()
/*  43:    */  {
/*  44: 44 */    IntBuffer buffer = BufferUtils.createIntBuffer(16);
/*  45: 45 */    GL.glGetInteger(3379, buffer);
/*  46:    */    
/*  47: 47 */    return buffer.get(0);
/*  48:    */  }
/*  49:    */  
/*  66:    */  private BigImage()
/*  67:    */  {
/*  68: 68 */    this.inited = true;
/*  69:    */  }
/*  70:    */  
/*  75:    */  public BigImage(String ref)
/*  76:    */    throws SlickException
/*  77:    */  {
/*  78: 78 */    this(ref, 2);
/*  79:    */  }
/*  80:    */  
/*  87:    */  public BigImage(String ref, int filter)
/*  88:    */    throws SlickException
/*  89:    */  {
/*  90: 90 */    build(ref, filter, getMaxSingleImageSize());
/*  91:    */  }
/*  92:    */  
/*  99:    */  public BigImage(String ref, int filter, int tileSize)
/* 100:    */    throws SlickException
/* 101:    */  {
/* 102:102 */    build(ref, filter, tileSize);
/* 103:    */  }
/* 104:    */  
/* 111:    */  public BigImage(LoadableImageData data, ByteBuffer imageBuffer, int filter)
/* 112:    */  {
/* 113:113 */    build(data, imageBuffer, filter, getMaxSingleImageSize());
/* 114:    */  }
/* 115:    */  
/* 123:    */  public BigImage(LoadableImageData data, ByteBuffer imageBuffer, int filter, int tileSize)
/* 124:    */  {
/* 125:125 */    build(data, imageBuffer, filter, tileSize);
/* 126:    */  }
/* 127:    */  
/* 134:    */  public Image getTile(int x, int y)
/* 135:    */  {
/* 136:136 */    return this.images[x][y];
/* 137:    */  }
/* 138:    */  
/* 144:    */  private void build(String ref, int filter, int tileSize)
/* 145:    */    throws SlickException
/* 146:    */  {
/* 147:    */    try
/* 148:    */    {
/* 149:149 */      LoadableImageData data = ImageDataFactory.getImageDataFor(ref);
/* 150:150 */      ByteBuffer imageBuffer = data.loadImage(ResourceLoader.getResourceAsStream(ref), false, null);
/* 151:151 */      build(data, imageBuffer, filter, tileSize);
/* 152:    */    } catch (IOException e) {
/* 153:153 */      throw new SlickException("Failed to load: " + ref, e);
/* 154:    */    }
/* 155:    */  }
/* 156:    */  
/* 164:    */  private void build(final LoadableImageData data, final ByteBuffer imageBuffer, int filter, int tileSize)
/* 165:    */  {
/* 166:166 */    final int dataWidth = data.getTexWidth();
/* 167:167 */    final int dataHeight = data.getTexHeight();
/* 168:    */    
/* 169:169 */    this.realWidth = (this.width = data.getWidth());
/* 170:170 */    this.realHeight = (this.height = data.getHeight());
/* 171:    */    
/* 172:172 */    if ((dataWidth <= tileSize) && (dataHeight <= tileSize)) {
/* 173:173 */      this.images = new Image[1][1];
/* 174:174 */      ImageData tempData = new ImageData() {
/* 175:    */        public int getDepth() {
/* 176:176 */          return data.getDepth();
/* 177:    */        }
/* 178:    */        
/* 179:    */        public int getHeight() {
/* 180:180 */          return dataHeight;
/* 181:    */        }
/* 182:    */        
/* 183:    */        public ByteBuffer getImageBufferData() {
/* 184:184 */          return imageBuffer;
/* 185:    */        }
/* 186:    */        
/* 187:    */        public int getTexHeight() {
/* 188:188 */          return dataHeight;
/* 189:    */        }
/* 190:    */        
/* 191:    */        public int getTexWidth() {
/* 192:192 */          return dataWidth;
/* 193:    */        }
/* 194:    */        
/* 195:    */        public int getWidth() {
/* 196:196 */          return dataWidth;
/* 197:    */        }
/* 198:198 */      };
/* 199:199 */      this.images[0][0] = new Image(tempData, filter);
/* 200:200 */      this.xcount = 1;
/* 201:201 */      this.ycount = 1;
/* 202:202 */      this.inited = true;
/* 203:203 */      return;
/* 204:    */    }
/* 205:    */    
/* 206:206 */    this.xcount = ((this.realWidth - 1) / tileSize + 1);
/* 207:207 */    this.ycount = ((this.realHeight - 1) / tileSize + 1);
/* 208:    */    
/* 209:209 */    this.images = new Image[this.xcount][this.ycount];
/* 210:210 */    int components = data.getDepth() / 8;
/* 211:    */    
/* 212:212 */    for (int x = 0; x < this.xcount; x++) {
/* 213:213 */      for (int y = 0; y < this.ycount; y++) {
/* 214:214 */        int finalX = (x + 1) * tileSize;
/* 215:215 */        int finalY = (y + 1) * tileSize;
/* 216:216 */        final int imageWidth = Math.min(this.realWidth - x * tileSize, tileSize);
/* 217:217 */        final int imageHeight = Math.min(this.realHeight - y * tileSize, tileSize);
/* 218:    */        
/* 219:219 */        final int xSize = tileSize;
/* 220:220 */        final int ySize = tileSize;
/* 221:    */        
/* 222:222 */        final ByteBuffer subBuffer = BufferUtils.createByteBuffer(tileSize * tileSize * components);
/* 223:223 */        int xo = x * tileSize * components;
/* 224:    */        
/* 225:225 */        byte[] byteData = new byte[xSize * components];
/* 226:226 */        for (int i = 0; i < ySize; i++) {
/* 227:227 */          int yo = (y * tileSize + i) * dataWidth * components;
/* 228:228 */          imageBuffer.position(yo + xo);
/* 229:    */          
/* 230:230 */          imageBuffer.get(byteData, 0, xSize * components);
/* 231:231 */          subBuffer.put(byteData);
/* 232:    */        }
/* 233:    */        
/* 234:234 */        subBuffer.flip();
/* 235:235 */        ImageData imgData = new ImageData() {
/* 236:    */          public int getDepth() {
/* 237:237 */            return data.getDepth();
/* 238:    */          }
/* 239:    */          
/* 240:    */          public int getHeight() {
/* 241:241 */            return imageHeight;
/* 242:    */          }
/* 243:    */          
/* 244:    */          public int getWidth() {
/* 245:245 */            return imageWidth;
/* 246:    */          }
/* 247:    */          
/* 248:    */          public ByteBuffer getImageBufferData() {
/* 249:249 */            return subBuffer;
/* 250:    */          }
/* 251:    */          
/* 252:    */          public int getTexHeight() {
/* 253:253 */            return ySize;
/* 254:    */          }
/* 255:    */          
/* 256:    */          public int getTexWidth() {
/* 257:257 */            return xSize;
/* 258:    */          }
/* 259:259 */        };
/* 260:260 */        this.images[x][y] = new Image(imgData, filter);
/* 261:    */      }
/* 262:    */    }
/* 263:    */    
/* 264:264 */    this.inited = true;
/* 265:    */  }
/* 266:    */  
/* 271:    */  public void bind()
/* 272:    */  {
/* 273:273 */    throw new OperationNotSupportedException("Can't bind big images yet");
/* 274:    */  }
/* 275:    */  
/* 280:    */  public Image copy()
/* 281:    */  {
/* 282:282 */    throw new OperationNotSupportedException("Can't copy big images yet");
/* 283:    */  }
/* 284:    */  
/* 287:    */  public void draw()
/* 288:    */  {
/* 289:289 */    draw(0.0F, 0.0F);
/* 290:    */  }
/* 291:    */  
/* 294:    */  public void draw(float x, float y, Color filter)
/* 295:    */  {
/* 296:296 */    draw(x, y, this.width, this.height, filter);
/* 297:    */  }
/* 298:    */  
/* 301:    */  public void draw(float x, float y, float scale, Color filter)
/* 302:    */  {
/* 303:303 */    draw(x, y, this.width * scale, this.height * scale, filter);
/* 304:    */  }
/* 305:    */  
/* 308:    */  public void draw(float x, float y, float width, float height, Color filter)
/* 309:    */  {
/* 310:310 */    float sx = width / this.realWidth;
/* 311:311 */    float sy = height / this.realHeight;
/* 312:    */    
/* 313:313 */    GL.glTranslatef(x, y, 0.0F);
/* 314:314 */    GL.glScalef(sx, sy, 1.0F);
/* 315:    */    
/* 316:316 */    float xp = 0.0F;
/* 317:317 */    float yp = 0.0F;
/* 318:    */    
/* 319:319 */    for (int tx = 0; tx < this.xcount; tx++) {
/* 320:320 */      yp = 0.0F;
/* 321:321 */      for (int ty = 0; ty < this.ycount; ty++) {
/* 322:322 */        Image image = this.images[tx][ty];
/* 323:    */        
/* 324:324 */        image.draw(xp, yp, image.getWidth(), image.getHeight(), filter);
/* 325:    */        
/* 326:326 */        yp += image.getHeight();
/* 327:327 */        if (ty == this.ycount - 1) {
/* 328:328 */          xp += image.getWidth();
/* 329:    */        }
/* 330:    */      }
/* 331:    */    }
/* 332:    */    
/* 334:334 */    GL.glScalef(1.0F / sx, 1.0F / sy, 1.0F);
/* 335:335 */    GL.glTranslatef(-x, -y, 0.0F);
/* 336:    */  }
/* 337:    */  
/* 340:    */  public void draw(float x, float y, float x2, float y2, float srcx, float srcy, float srcx2, float srcy2)
/* 341:    */  {
/* 342:342 */    int srcwidth = (int)(srcx2 - srcx);
/* 343:343 */    int srcheight = (int)(srcy2 - srcy);
/* 344:    */    
/* 345:345 */    Image subImage = getSubImage((int)srcx, (int)srcy, srcwidth, srcheight);
/* 346:    */    
/* 347:347 */    int width = (int)(x2 - x);
/* 348:348 */    int height = (int)(y2 - y);
/* 349:    */    
/* 350:350 */    subImage.draw(x, y, width, height);
/* 351:    */  }
/* 352:    */  
/* 355:    */  public void draw(float x, float y, float srcx, float srcy, float srcx2, float srcy2)
/* 356:    */  {
/* 357:357 */    int srcwidth = (int)(srcx2 - srcx);
/* 358:358 */    int srcheight = (int)(srcy2 - srcy);
/* 359:    */    
/* 360:360 */    draw(x, y, srcwidth, srcheight, srcx, srcy, srcx2, srcy2);
/* 361:    */  }
/* 362:    */  
/* 365:    */  public void draw(float x, float y, float width, float height)
/* 366:    */  {
/* 367:367 */    draw(x, y, width, height, Color.white);
/* 368:    */  }
/* 369:    */  
/* 372:    */  public void draw(float x, float y, float scale)
/* 373:    */  {
/* 374:374 */    draw(x, y, scale, Color.white);
/* 375:    */  }
/* 376:    */  
/* 379:    */  public void draw(float x, float y)
/* 380:    */  {
/* 381:381 */    draw(x, y, Color.white);
/* 382:    */  }
/* 383:    */  
/* 386:    */  public void drawEmbedded(float x, float y, float width, float height)
/* 387:    */  {
/* 388:388 */    float sx = width / this.realWidth;
/* 389:389 */    float sy = height / this.realHeight;
/* 390:    */    
/* 391:391 */    float xp = 0.0F;
/* 392:392 */    float yp = 0.0F;
/* 393:    */    
/* 394:394 */    for (int tx = 0; tx < this.xcount; tx++) {
/* 395:395 */      yp = 0.0F;
/* 396:396 */      for (int ty = 0; ty < this.ycount; ty++) {
/* 397:397 */        Image image = this.images[tx][ty];
/* 398:    */        
/* 399:399 */        if ((lastBind == null) || (image.getTexture() != lastBind.getTexture())) {
/* 400:400 */          if (lastBind != null) {
/* 401:401 */            lastBind.endUse();
/* 402:    */          }
/* 403:403 */          lastBind = image;
/* 404:404 */          lastBind.startUse();
/* 405:    */        }
/* 406:406 */        image.drawEmbedded(xp + x, yp + y, image.getWidth(), image.getHeight());
/* 407:    */        
/* 408:408 */        yp += image.getHeight();
/* 409:409 */        if (ty == this.ycount - 1) {
/* 410:410 */          xp += image.getWidth();
/* 411:    */        }
/* 412:    */      }
/* 413:    */    }
/* 414:    */  }
/* 415:    */  
/* 419:    */  public void drawFlash(float x, float y, float width, float height)
/* 420:    */  {
/* 421:421 */    float sx = width / this.realWidth;
/* 422:422 */    float sy = height / this.realHeight;
/* 423:    */    
/* 424:424 */    GL.glTranslatef(x, y, 0.0F);
/* 425:425 */    GL.glScalef(sx, sy, 1.0F);
/* 426:    */    
/* 427:427 */    float xp = 0.0F;
/* 428:428 */    float yp = 0.0F;
/* 429:    */    
/* 430:430 */    for (int tx = 0; tx < this.xcount; tx++) {
/* 431:431 */      yp = 0.0F;
/* 432:432 */      for (int ty = 0; ty < this.ycount; ty++) {
/* 433:433 */        Image image = this.images[tx][ty];
/* 434:    */        
/* 435:435 */        image.drawFlash(xp, yp, image.getWidth(), image.getHeight());
/* 436:    */        
/* 437:437 */        yp += image.getHeight();
/* 438:438 */        if (ty == this.ycount - 1) {
/* 439:439 */          xp += image.getWidth();
/* 440:    */        }
/* 441:    */      }
/* 442:    */    }
/* 443:    */    
/* 445:445 */    GL.glScalef(1.0F / sx, 1.0F / sy, 1.0F);
/* 446:446 */    GL.glTranslatef(-x, -y, 0.0F);
/* 447:    */  }
/* 448:    */  
/* 451:    */  public void drawFlash(float x, float y)
/* 452:    */  {
/* 453:453 */    drawFlash(x, y, this.width, this.height);
/* 454:    */  }
/* 455:    */  
/* 460:    */  public void endUse()
/* 461:    */  {
/* 462:462 */    if (lastBind != null) {
/* 463:463 */      lastBind.endUse();
/* 464:    */    }
/* 465:465 */    lastBind = null;
/* 466:    */  }
/* 467:    */  
/* 473:    */  public void startUse() {}
/* 474:    */  
/* 480:    */  public void ensureInverted()
/* 481:    */  {
/* 482:482 */    throw new OperationNotSupportedException("Doesn't make sense for tiled operations");
/* 483:    */  }
/* 484:    */  
/* 489:    */  public Color getColor(int x, int y)
/* 490:    */  {
/* 491:491 */    throw new OperationNotSupportedException("Can't use big images as buffers");
/* 492:    */  }
/* 493:    */  
/* 496:    */  public Image getFlippedCopy(boolean flipHorizontal, boolean flipVertical)
/* 497:    */  {
/* 498:498 */    BigImage image = new BigImage();
/* 499:    */    
/* 500:500 */    image.images = this.images;
/* 501:501 */    image.xcount = this.xcount;
/* 502:502 */    image.ycount = this.ycount;
/* 503:503 */    image.width = this.width;
/* 504:504 */    image.height = this.height;
/* 505:505 */    image.realWidth = this.realWidth;
/* 506:506 */    image.realHeight = this.realHeight;
/* 507:    */    
/* 508:508 */    if (flipHorizontal) {
/* 509:509 */      Image[][] images = image.images;
/* 510:510 */      image.images = new Image[this.xcount][this.ycount];
/* 511:    */      
/* 512:512 */      for (int x = 0; x < this.xcount; x++) {
/* 513:513 */        for (int y = 0; y < this.ycount; y++) {
/* 514:514 */          image.images[x][y] = images[(this.xcount - 1 - x)][y].getFlippedCopy(true, false);
/* 515:    */        }
/* 516:    */      }
/* 517:    */    }
/* 518:    */    
/* 519:519 */    if (flipVertical) {
/* 520:520 */      Image[][] images = image.images;
/* 521:521 */      image.images = new Image[this.xcount][this.ycount];
/* 522:    */      
/* 523:523 */      for (int x = 0; x < this.xcount; x++) {
/* 524:524 */        for (int y = 0; y < this.ycount; y++) {
/* 525:525 */          image.images[x][y] = images[x][(this.ycount - 1 - y)].getFlippedCopy(false, true);
/* 526:    */        }
/* 527:    */      }
/* 528:    */    }
/* 529:    */    
/* 530:530 */    return image;
/* 531:    */  }
/* 532:    */  
/* 536:    */  public Graphics getGraphics()
/* 537:    */    throws SlickException
/* 538:    */  {
/* 539:539 */    throw new OperationNotSupportedException("Can't use big images as offscreen buffers");
/* 540:    */  }
/* 541:    */  
/* 544:    */  public Image getScaledCopy(float scale)
/* 545:    */  {
/* 546:546 */    return getScaledCopy((int)(scale * this.width), (int)(scale * this.height));
/* 547:    */  }
/* 548:    */  
/* 551:    */  public Image getScaledCopy(int width, int height)
/* 552:    */  {
/* 553:553 */    BigImage image = new BigImage();
/* 554:    */    
/* 555:555 */    image.images = this.images;
/* 556:556 */    image.xcount = this.xcount;
/* 557:557 */    image.ycount = this.ycount;
/* 558:558 */    image.width = width;
/* 559:559 */    image.height = height;
/* 560:560 */    image.realWidth = this.realWidth;
/* 561:561 */    image.realHeight = this.realHeight;
/* 562:    */    
/* 563:563 */    return image;
/* 564:    */  }
/* 565:    */  
/* 568:    */  public Image getSubImage(int x, int y, int width, int height)
/* 569:    */  {
/* 570:570 */    BigImage image = new BigImage();
/* 571:    */    
/* 572:572 */    image.width = width;
/* 573:573 */    image.height = height;
/* 574:574 */    image.realWidth = width;
/* 575:575 */    image.realHeight = height;
/* 576:576 */    image.images = new Image[this.xcount][this.ycount];
/* 577:    */    
/* 578:578 */    float xp = 0.0F;
/* 579:579 */    float yp = 0.0F;
/* 580:580 */    int x2 = x + width;
/* 581:581 */    int y2 = y + height;
/* 582:    */    
/* 583:583 */    int startx = 0;
/* 584:584 */    int starty = 0;
/* 585:585 */    boolean foundStart = false;
/* 586:    */    
/* 587:587 */    for (int xt = 0; xt < this.xcount; xt++) {
/* 588:588 */      yp = 0.0F;
/* 589:589 */      starty = 0;
/* 590:590 */      foundStart = false;
/* 591:591 */      for (int yt = 0; yt < this.ycount; yt++) {
/* 592:592 */        Image current = this.images[xt][yt];
/* 593:    */        
/* 594:594 */        int xp2 = (int)(xp + current.getWidth());
/* 595:595 */        int yp2 = (int)(yp + current.getHeight());
/* 596:    */        
/* 602:602 */        int targetX1 = (int)Math.max(x, xp);
/* 603:603 */        int targetY1 = (int)Math.max(y, yp);
/* 604:604 */        int targetX2 = Math.min(x2, xp2);
/* 605:605 */        int targetY2 = Math.min(y2, yp2);
/* 606:    */        
/* 607:607 */        int targetWidth = targetX2 - targetX1;
/* 608:608 */        int targetHeight = targetY2 - targetY1;
/* 609:    */        
/* 610:610 */        if ((targetWidth > 0) && (targetHeight > 0)) {
/* 611:611 */          Image subImage = current.getSubImage((int)(targetX1 - xp), (int)(targetY1 - yp), targetX2 - targetX1, targetY2 - targetY1);
/* 612:    */          
/* 614:614 */          foundStart = true;
/* 615:615 */          image.images[startx][starty] = subImage;
/* 616:616 */          starty++;
/* 617:617 */          image.ycount = Math.max(image.ycount, starty);
/* 618:    */        }
/* 619:    */        
/* 620:620 */        yp += current.getHeight();
/* 621:621 */        if (yt == this.ycount - 1) {
/* 622:622 */          xp += current.getWidth();
/* 623:    */        }
/* 624:    */      }
/* 625:625 */      if (foundStart) {
/* 626:626 */        startx++;
/* 627:627 */        image.xcount += 1;
/* 628:    */      }
/* 629:    */    }
/* 630:    */    
/* 631:631 */    return image;
/* 632:    */  }
/* 633:    */  
/* 638:    */  public Texture getTexture()
/* 639:    */  {
/* 640:640 */    throw new OperationNotSupportedException("Can't use big images as offscreen buffers");
/* 641:    */  }
/* 642:    */  
/* 645:    */  protected void initImpl()
/* 646:    */  {
/* 647:647 */    throw new OperationNotSupportedException("Can't use big images as offscreen buffers");
/* 648:    */  }
/* 649:    */  
/* 652:    */  protected void reinit()
/* 653:    */  {
/* 654:654 */    throw new OperationNotSupportedException("Can't use big images as offscreen buffers");
/* 655:    */  }
/* 656:    */  
/* 661:    */  public void setTexture(Texture texture)
/* 662:    */  {
/* 663:663 */    throw new OperationNotSupportedException("Can't use big images as offscreen buffers");
/* 664:    */  }
/* 665:    */  
/* 673:    */  public Image getSubImage(int offsetX, int offsetY)
/* 674:    */  {
/* 675:675 */    return this.images[offsetX][offsetY];
/* 676:    */  }
/* 677:    */  
/* 682:    */  public int getHorizontalImageCount()
/* 683:    */  {
/* 684:684 */    return this.xcount;
/* 685:    */  }
/* 686:    */  
/* 691:    */  public int getVerticalImageCount()
/* 692:    */  {
/* 693:693 */    return this.ycount;
/* 694:    */  }
/* 695:    */  
/* 698:    */  public String toString()
/* 699:    */  {
/* 700:700 */    return "[BIG IMAGE]";
/* 701:    */  }
/* 702:    */  
/* 705:    */  public void destroy()
/* 706:    */    throws SlickException
/* 707:    */  {
/* 708:708 */    for (int tx = 0; tx < this.xcount; tx++) {
/* 709:709 */      for (int ty = 0; ty < this.ycount; ty++) {
/* 710:710 */        Image image = this.images[tx][ty];
/* 711:711 */        image.destroy();
/* 712:    */      }
/* 713:    */    }
/* 714:    */  }
/* 715:    */  
/* 719:    */  public void draw(float x, float y, float x2, float y2, float srcx, float srcy, float srcx2, float srcy2, Color filter)
/* 720:    */  {
/* 721:721 */    int srcwidth = (int)(srcx2 - srcx);
/* 722:722 */    int srcheight = (int)(srcy2 - srcy);
/* 723:    */    
/* 724:724 */    Image subImage = getSubImage((int)srcx, (int)srcy, srcwidth, srcheight);
/* 725:    */    
/* 726:726 */    int width = (int)(x2 - x);
/* 727:727 */    int height = (int)(y2 - y);
/* 728:    */    
/* 729:729 */    subImage.draw(x, y, width, height, filter);
/* 730:    */  }
/* 731:    */  
/* 734:    */  public void drawCentered(float x, float y)
/* 735:    */  {
/* 736:736 */    throw new UnsupportedOperationException();
/* 737:    */  }
/* 738:    */  
/* 742:    */  public void drawEmbedded(float x, float y, float x2, float y2, float srcx, float srcy, float srcx2, float srcy2, Color filter)
/* 743:    */  {
/* 744:744 */    throw new UnsupportedOperationException();
/* 745:    */  }
/* 746:    */  
/* 750:    */  public void drawEmbedded(float x, float y, float x2, float y2, float srcx, float srcy, float srcx2, float srcy2)
/* 751:    */  {
/* 752:752 */    throw new UnsupportedOperationException();
/* 753:    */  }
/* 754:    */  
/* 757:    */  public void drawFlash(float x, float y, float width, float height, Color col)
/* 758:    */  {
/* 759:759 */    throw new UnsupportedOperationException();
/* 760:    */  }
/* 761:    */  
/* 764:    */  public void drawSheared(float x, float y, float hshear, float vshear)
/* 765:    */  {
/* 766:766 */    throw new UnsupportedOperationException();
/* 767:    */  }
/* 768:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.BigImage
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */