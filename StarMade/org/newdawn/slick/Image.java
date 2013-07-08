/*    1:     */package org.newdawn.slick;
/*    2:     */
/*    3:     */import java.io.IOException;
/*    4:     */import java.io.InputStream;
/*    5:     */import org.newdawn.slick.opengl.ImageData;
/*    6:     */import org.newdawn.slick.opengl.InternalTextureLoader;
/*    7:     */import org.newdawn.slick.opengl.Texture;
/*    8:     */import org.newdawn.slick.opengl.TextureImpl;
/*    9:     */import org.newdawn.slick.opengl.pbuffer.GraphicsFactory;
/*   10:     */import org.newdawn.slick.opengl.renderer.Renderer;
/*   11:     */import org.newdawn.slick.opengl.renderer.SGL;
/*   12:     */import org.newdawn.slick.util.Log;
/*   13:     */
/*   24:     */public class Image
/*   25:     */  implements Renderable
/*   26:     */{
/*   27:     */  public static final int TOP_LEFT = 0;
/*   28:     */  public static final int TOP_RIGHT = 1;
/*   29:     */  public static final int BOTTOM_RIGHT = 2;
/*   30:     */  public static final int BOTTOM_LEFT = 3;
/*   31:  31 */  protected static SGL GL = ;
/*   32:     */  
/*   34:     */  protected static Image inUse;
/*   35:     */  
/*   37:     */  public static final int FILTER_LINEAR = 1;
/*   38:     */  
/*   39:     */  public static final int FILTER_NEAREST = 2;
/*   40:     */  
/*   41:     */  protected Texture texture;
/*   42:     */  
/*   43:     */  protected int width;
/*   44:     */  
/*   45:     */  protected int height;
/*   46:     */  
/*   47:     */  protected float textureWidth;
/*   48:     */  
/*   49:     */  protected float textureHeight;
/*   50:     */  
/*   51:     */  protected float textureOffsetX;
/*   52:     */  
/*   53:     */  protected float textureOffsetY;
/*   54:     */  
/*   55:     */  protected float angle;
/*   56:     */  
/*   57:  57 */  protected float alpha = 1.0F;
/*   58:     */  
/*   59:     */  protected String ref;
/*   60:     */  
/*   61:  61 */  protected boolean inited = false;
/*   62:     */  
/*   64:     */  protected byte[] pixelData;
/*   65:     */  
/*   67:     */  protected boolean destroyed;
/*   68:     */  
/*   70:     */  protected float centerX;
/*   71:     */  
/*   72:     */  protected float centerY;
/*   73:     */  
/*   74:     */  protected String name;
/*   75:     */  
/*   76:     */  protected Color[] corners;
/*   77:     */  
/*   78:  78 */  private int filter = 9729;
/*   79:     */  
/*   82:     */  private boolean flipped;
/*   83:     */  
/*   85:     */  private Color transparent;
/*   86:     */  
/*   89:     */  protected Image(Image other)
/*   90:     */  {
/*   91:  91 */    this.width = other.getWidth();
/*   92:  92 */    this.height = other.getHeight();
/*   93:  93 */    this.texture = other.texture;
/*   94:  94 */    this.textureWidth = other.textureWidth;
/*   95:  95 */    this.textureHeight = other.textureHeight;
/*   96:  96 */    this.ref = other.ref;
/*   97:  97 */    this.textureOffsetX = other.textureOffsetX;
/*   98:  98 */    this.textureOffsetY = other.textureOffsetY;
/*   99:     */    
/*  100: 100 */    this.centerX = (this.width / 2);
/*  101: 101 */    this.centerY = (this.height / 2);
/*  102: 102 */    this.inited = true;
/*  103:     */  }
/*  104:     */  
/*  110:     */  protected Image() {}
/*  111:     */  
/*  116:     */  public Image(Texture texture)
/*  117:     */  {
/*  118: 118 */    this.texture = texture;
/*  119: 119 */    this.ref = texture.toString();
/*  120: 120 */    clampTexture();
/*  121:     */  }
/*  122:     */  
/*  129:     */  public Image(String ref)
/*  130:     */    throws SlickException
/*  131:     */  {
/*  132: 132 */    this(ref, false);
/*  133:     */  }
/*  134:     */  
/*  140:     */  public Image(String ref, Color trans)
/*  141:     */    throws SlickException
/*  142:     */  {
/*  143: 143 */    this(ref, false, 1, trans);
/*  144:     */  }
/*  145:     */  
/*  151:     */  public Image(String ref, boolean flipped)
/*  152:     */    throws SlickException
/*  153:     */  {
/*  154: 154 */    this(ref, flipped, 1);
/*  155:     */  }
/*  156:     */  
/*  163:     */  public Image(String ref, boolean flipped, int filter)
/*  164:     */    throws SlickException
/*  165:     */  {
/*  166: 166 */    this(ref, flipped, filter, null);
/*  167:     */  }
/*  168:     */  
/*  176:     */  public Image(String ref, boolean flipped, int f, Color transparent)
/*  177:     */    throws SlickException
/*  178:     */  {
/*  179: 179 */    this.filter = (f == 1 ? 9729 : 9728);
/*  180: 180 */    this.transparent = transparent;
/*  181: 181 */    this.flipped = flipped;
/*  182:     */    try
/*  183:     */    {
/*  184: 184 */      this.ref = ref;
/*  185: 185 */      int[] trans = null;
/*  186: 186 */      if (transparent != null) {
/*  187: 187 */        trans = new int[3];
/*  188: 188 */        trans[0] = ((int)(transparent.r * 255.0F));
/*  189: 189 */        trans[1] = ((int)(transparent.g * 255.0F));
/*  190: 190 */        trans[2] = ((int)(transparent.b * 255.0F));
/*  191:     */      }
/*  192: 192 */      this.texture = InternalTextureLoader.get().getTexture(ref, flipped, this.filter, trans);
/*  193:     */    } catch (IOException e) {
/*  194: 194 */      Log.error(e);
/*  195: 195 */      throw new SlickException("Failed to load image from: " + ref, e);
/*  196:     */    }
/*  197:     */  }
/*  198:     */  
/*  204:     */  public void setFilter(int f)
/*  205:     */  {
/*  206: 206 */    this.filter = (f == 1 ? 9729 : 9728);
/*  207:     */    
/*  208: 208 */    this.texture.bind();
/*  209: 209 */    GL.glTexParameteri(3553, 10241, this.filter);
/*  210: 210 */    GL.glTexParameteri(3553, 10240, this.filter);
/*  211:     */  }
/*  212:     */  
/*  218:     */  public Image(int width, int height)
/*  219:     */    throws SlickException
/*  220:     */  {
/*  221: 221 */    this(width, height, 2);
/*  222:     */  }
/*  223:     */  
/*  230:     */  public Image(int width, int height, int f)
/*  231:     */    throws SlickException
/*  232:     */  {
/*  233: 233 */    this.ref = super.toString();
/*  234: 234 */    this.filter = (f == 1 ? 9729 : 9728);
/*  235:     */    try
/*  236:     */    {
/*  237: 237 */      this.texture = InternalTextureLoader.get().createTexture(width, height, this.filter);
/*  238:     */    } catch (IOException e) {
/*  239: 239 */      Log.error(e);
/*  240: 240 */      throw new SlickException("Failed to create empty image " + width + "x" + height);
/*  241:     */    }
/*  242:     */    
/*  243: 243 */    init();
/*  244:     */  }
/*  245:     */  
/*  252:     */  public Image(InputStream in, String ref, boolean flipped)
/*  253:     */    throws SlickException
/*  254:     */  {
/*  255: 255 */    this(in, ref, flipped, 1);
/*  256:     */  }
/*  257:     */  
/*  265:     */  public Image(InputStream in, String ref, boolean flipped, int filter)
/*  266:     */    throws SlickException
/*  267:     */  {
/*  268: 268 */    load(in, ref, flipped, filter, null);
/*  269:     */  }
/*  270:     */  
/*  275:     */  Image(ImageBuffer buffer)
/*  276:     */  {
/*  277: 277 */    this(buffer, 1);
/*  278: 278 */    TextureImpl.bindNone();
/*  279:     */  }
/*  280:     */  
/*  286:     */  Image(ImageBuffer buffer, int filter)
/*  287:     */  {
/*  288: 288 */    this(buffer, filter);
/*  289: 289 */    TextureImpl.bindNone();
/*  290:     */  }
/*  291:     */  
/*  296:     */  public Image(ImageData data)
/*  297:     */  {
/*  298: 298 */    this(data, 1);
/*  299:     */  }
/*  300:     */  
/*  305:     */  public Image(ImageData data, int f)
/*  306:     */  {
/*  307:     */    try
/*  308:     */    {
/*  309: 309 */      this.filter = (f == 1 ? 9729 : 9728);
/*  310: 310 */      this.texture = InternalTextureLoader.get().getTexture(data, this.filter);
/*  311: 311 */      this.ref = this.texture.toString();
/*  312:     */    } catch (IOException e) {
/*  313: 313 */      Log.error(e);
/*  314:     */    }
/*  315:     */  }
/*  316:     */  
/*  321:     */  public int getFilter()
/*  322:     */  {
/*  323: 323 */    return this.filter;
/*  324:     */  }
/*  325:     */  
/*  331:     */  public String getResourceReference()
/*  332:     */  {
/*  333: 333 */    return this.ref;
/*  334:     */  }
/*  335:     */  
/*  343:     */  public void setImageColor(float r, float g, float b, float a)
/*  344:     */  {
/*  345: 345 */    setColor(0, r, g, b, a);
/*  346: 346 */    setColor(1, r, g, b, a);
/*  347: 347 */    setColor(3, r, g, b, a);
/*  348: 348 */    setColor(2, r, g, b, a);
/*  349:     */  }
/*  350:     */  
/*  357:     */  public void setImageColor(float r, float g, float b)
/*  358:     */  {
/*  359: 359 */    setColor(0, r, g, b);
/*  360: 360 */    setColor(1, r, g, b);
/*  361: 361 */    setColor(3, r, g, b);
/*  362: 362 */    setColor(2, r, g, b);
/*  363:     */  }
/*  364:     */  
/*  374:     */  public void setColor(int corner, float r, float g, float b, float a)
/*  375:     */  {
/*  376: 376 */    if (this.corners == null) {
/*  377: 377 */      this.corners = new Color[] { new Color(1.0F, 1.0F, 1.0F, 1.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F) };
/*  378:     */    }
/*  379:     */    
/*  380: 380 */    this.corners[corner].r = r;
/*  381: 381 */    this.corners[corner].g = g;
/*  382: 382 */    this.corners[corner].b = b;
/*  383: 383 */    this.corners[corner].a = a;
/*  384:     */  }
/*  385:     */  
/*  394:     */  public void setColor(int corner, float r, float g, float b)
/*  395:     */  {
/*  396: 396 */    if (this.corners == null) {
/*  397: 397 */      this.corners = new Color[] { new Color(1.0F, 1.0F, 1.0F, 1.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F) };
/*  398:     */    }
/*  399:     */    
/*  400: 400 */    this.corners[corner].r = r;
/*  401: 401 */    this.corners[corner].g = g;
/*  402: 402 */    this.corners[corner].b = b;
/*  403:     */  }
/*  404:     */  
/*  407:     */  public void clampTexture()
/*  408:     */  {
/*  409: 409 */    if (GL.canTextureMirrorClamp()) {
/*  410: 410 */      GL.glTexParameteri(3553, 10242, 34627);
/*  411: 411 */      GL.glTexParameteri(3553, 10243, 34627);
/*  412:     */    } else {
/*  413: 413 */      GL.glTexParameteri(3553, 10242, 10496);
/*  414: 414 */      GL.glTexParameteri(3553, 10243, 10496);
/*  415:     */    }
/*  416:     */  }
/*  417:     */  
/*  423:     */  public void setName(String name)
/*  424:     */  {
/*  425: 425 */    this.name = name;
/*  426:     */  }
/*  427:     */  
/*  432:     */  public String getName()
/*  433:     */  {
/*  434: 434 */    return this.name;
/*  435:     */  }
/*  436:     */  
/*  441:     */  public Graphics getGraphics()
/*  442:     */    throws SlickException
/*  443:     */  {
/*  444: 444 */    return GraphicsFactory.getGraphicsForImage(this);
/*  445:     */  }
/*  446:     */  
/*  455:     */  private void load(InputStream in, String ref, boolean flipped, int f, Color transparent)
/*  456:     */    throws SlickException
/*  457:     */  {
/*  458: 458 */    this.filter = (f == 1 ? 9729 : 9728);
/*  459:     */    try
/*  460:     */    {
/*  461: 461 */      this.ref = ref;
/*  462: 462 */      int[] trans = null;
/*  463: 463 */      if (transparent != null) {
/*  464: 464 */        trans = new int[3];
/*  465: 465 */        trans[0] = ((int)(transparent.r * 255.0F));
/*  466: 466 */        trans[1] = ((int)(transparent.g * 255.0F));
/*  467: 467 */        trans[2] = ((int)(transparent.b * 255.0F));
/*  468:     */      }
/*  469: 469 */      this.texture = InternalTextureLoader.get().getTexture(in, ref, flipped, this.filter, trans);
/*  470:     */    } catch (IOException e) {
/*  471: 471 */      Log.error(e);
/*  472: 472 */      throw new SlickException("Failed to load image from: " + ref, e);
/*  473:     */    }
/*  474:     */  }
/*  475:     */  
/*  478:     */  public void bind()
/*  479:     */  {
/*  480: 480 */    this.texture.bind();
/*  481:     */  }
/*  482:     */  
/*  485:     */  protected void reinit()
/*  486:     */  {
/*  487: 487 */    this.inited = false;
/*  488: 488 */    init();
/*  489:     */  }
/*  490:     */  
/*  493:     */  protected final void init()
/*  494:     */  {
/*  495: 495 */    if (this.inited) {
/*  496: 496 */      return;
/*  497:     */    }
/*  498:     */    
/*  499: 499 */    this.inited = true;
/*  500: 500 */    if (this.texture != null) {
/*  501: 501 */      this.width = this.texture.getImageWidth();
/*  502: 502 */      this.height = this.texture.getImageHeight();
/*  503: 503 */      this.textureOffsetX = 0.0F;
/*  504: 504 */      this.textureOffsetY = 0.0F;
/*  505: 505 */      this.textureWidth = this.texture.getWidth();
/*  506: 506 */      this.textureHeight = this.texture.getHeight();
/*  507:     */    }
/*  508:     */    
/*  509: 509 */    initImpl();
/*  510:     */    
/*  511: 511 */    this.centerX = (this.width / 2);
/*  512: 512 */    this.centerY = (this.height / 2);
/*  513:     */  }
/*  514:     */  
/*  519:     */  protected void initImpl() {}
/*  520:     */  
/*  524:     */  public void draw()
/*  525:     */  {
/*  526: 526 */    draw(0.0F, 0.0F);
/*  527:     */  }
/*  528:     */  
/*  534:     */  public void drawCentered(float x, float y)
/*  535:     */  {
/*  536: 536 */    draw(x - getWidth() / 2, y - getHeight() / 2);
/*  537:     */  }
/*  538:     */  
/*  544:     */  public void draw(float x, float y)
/*  545:     */  {
/*  546: 546 */    init();
/*  547: 547 */    draw(x, y, this.width, this.height);
/*  548:     */  }
/*  549:     */  
/*  556:     */  public void draw(float x, float y, Color filter)
/*  557:     */  {
/*  558: 558 */    init();
/*  559: 559 */    draw(x, y, this.width, this.height, filter);
/*  560:     */  }
/*  561:     */  
/*  569:     */  public void drawEmbedded(float x, float y, float width, float height)
/*  570:     */  {
/*  571: 571 */    init();
/*  572:     */    
/*  573: 573 */    if (this.corners == null) {
/*  574: 574 */      GL.glTexCoord2f(this.textureOffsetX, this.textureOffsetY);
/*  575: 575 */      GL.glVertex3f(x, y, 0.0F);
/*  576: 576 */      GL.glTexCoord2f(this.textureOffsetX, this.textureOffsetY + this.textureHeight);
/*  577: 577 */      GL.glVertex3f(x, y + height, 0.0F);
/*  578: 578 */      GL.glTexCoord2f(this.textureOffsetX + this.textureWidth, this.textureOffsetY + this.textureHeight);
/*  579:     */      
/*  580: 580 */      GL.glVertex3f(x + width, y + height, 0.0F);
/*  581: 581 */      GL.glTexCoord2f(this.textureOffsetX + this.textureWidth, this.textureOffsetY);
/*  582: 582 */      GL.glVertex3f(x + width, y, 0.0F);
/*  583:     */    } else {
/*  584: 584 */      this.corners[0].bind();
/*  585: 585 */      GL.glTexCoord2f(this.textureOffsetX, this.textureOffsetY);
/*  586: 586 */      GL.glVertex3f(x, y, 0.0F);
/*  587: 587 */      this.corners[3].bind();
/*  588: 588 */      GL.glTexCoord2f(this.textureOffsetX, this.textureOffsetY + this.textureHeight);
/*  589: 589 */      GL.glVertex3f(x, y + height, 0.0F);
/*  590: 590 */      this.corners[2].bind();
/*  591: 591 */      GL.glTexCoord2f(this.textureOffsetX + this.textureWidth, this.textureOffsetY + this.textureHeight);
/*  592:     */      
/*  593: 593 */      GL.glVertex3f(x + width, y + height, 0.0F);
/*  594: 594 */      this.corners[1].bind();
/*  595: 595 */      GL.glTexCoord2f(this.textureOffsetX + this.textureWidth, this.textureOffsetY);
/*  596: 596 */      GL.glVertex3f(x + width, y, 0.0F);
/*  597:     */    }
/*  598:     */  }
/*  599:     */  
/*  604:     */  public float getTextureOffsetX()
/*  605:     */  {
/*  606: 606 */    init();
/*  607:     */    
/*  608: 608 */    return this.textureOffsetX;
/*  609:     */  }
/*  610:     */  
/*  615:     */  public float getTextureOffsetY()
/*  616:     */  {
/*  617: 617 */    init();
/*  618:     */    
/*  619: 619 */    return this.textureOffsetY;
/*  620:     */  }
/*  621:     */  
/*  626:     */  public float getTextureWidth()
/*  627:     */  {
/*  628: 628 */    init();
/*  629:     */    
/*  630: 630 */    return this.textureWidth;
/*  631:     */  }
/*  632:     */  
/*  637:     */  public float getTextureHeight()
/*  638:     */  {
/*  639: 639 */    init();
/*  640:     */    
/*  641: 641 */    return this.textureHeight;
/*  642:     */  }
/*  643:     */  
/*  650:     */  public void draw(float x, float y, float scale)
/*  651:     */  {
/*  652: 652 */    init();
/*  653: 653 */    draw(x, y, this.width * scale, this.height * scale, Color.white);
/*  654:     */  }
/*  655:     */  
/*  663:     */  public void draw(float x, float y, float scale, Color filter)
/*  664:     */  {
/*  665: 665 */    init();
/*  666: 666 */    draw(x, y, this.width * scale, this.height * scale, filter);
/*  667:     */  }
/*  668:     */  
/*  680:     */  public void draw(float x, float y, float width, float height)
/*  681:     */  {
/*  682: 682 */    init();
/*  683: 683 */    draw(x, y, width, height, Color.white);
/*  684:     */  }
/*  685:     */  
/*  693:     */  public void drawSheared(float x, float y, float hshear, float vshear)
/*  694:     */  {
/*  695: 695 */    drawSheared(x, y, hshear, vshear, Color.white);
/*  696:     */  }
/*  697:     */  
/*  705:     */  public void drawSheared(float x, float y, float hshear, float vshear, Color filter)
/*  706:     */  {
/*  707: 707 */    if (this.alpha != 1.0F) {
/*  708: 708 */      if (filter == null) {
/*  709: 709 */        filter = Color.white;
/*  710:     */      }
/*  711:     */      
/*  712: 712 */      filter = new Color(filter);
/*  713: 713 */      filter.a *= this.alpha;
/*  714:     */    }
/*  715: 715 */    if (filter != null) {
/*  716: 716 */      filter.bind();
/*  717:     */    }
/*  718:     */    
/*  719: 719 */    this.texture.bind();
/*  720:     */    
/*  721: 721 */    GL.glTranslatef(x, y, 0.0F);
/*  722: 722 */    if (this.angle != 0.0F) {
/*  723: 723 */      GL.glTranslatef(this.centerX, this.centerY, 0.0F);
/*  724: 724 */      GL.glRotatef(this.angle, 0.0F, 0.0F, 1.0F);
/*  725: 725 */      GL.glTranslatef(-this.centerX, -this.centerY, 0.0F);
/*  726:     */    }
/*  727:     */    
/*  728: 728 */    GL.glBegin(7);
/*  729: 729 */    init();
/*  730:     */    
/*  731: 731 */    GL.glTexCoord2f(this.textureOffsetX, this.textureOffsetY);
/*  732: 732 */    GL.glVertex3f(0.0F, 0.0F, 0.0F);
/*  733: 733 */    GL.glTexCoord2f(this.textureOffsetX, this.textureOffsetY + this.textureHeight);
/*  734: 734 */    GL.glVertex3f(hshear, this.height, 0.0F);
/*  735: 735 */    GL.glTexCoord2f(this.textureOffsetX + this.textureWidth, this.textureOffsetY + this.textureHeight);
/*  736:     */    
/*  737: 737 */    GL.glVertex3f(this.width + hshear, this.height + vshear, 0.0F);
/*  738: 738 */    GL.glTexCoord2f(this.textureOffsetX + this.textureWidth, this.textureOffsetY);
/*  739: 739 */    GL.glVertex3f(this.width, vshear, 0.0F);
/*  740: 740 */    GL.glEnd();
/*  741:     */    
/*  742: 742 */    if (this.angle != 0.0F) {
/*  743: 743 */      GL.glTranslatef(this.centerX, this.centerY, 0.0F);
/*  744: 744 */      GL.glRotatef(-this.angle, 0.0F, 0.0F, 1.0F);
/*  745: 745 */      GL.glTranslatef(-this.centerX, -this.centerY, 0.0F);
/*  746:     */    }
/*  747: 747 */    GL.glTranslatef(-x, -y, 0.0F);
/*  748:     */  }
/*  749:     */  
/*  758:     */  public void draw(float x, float y, float width, float height, Color filter)
/*  759:     */  {
/*  760: 760 */    if (this.alpha != 1.0F) {
/*  761: 761 */      if (filter == null) {
/*  762: 762 */        filter = Color.white;
/*  763:     */      }
/*  764:     */      
/*  765: 765 */      filter = new Color(filter);
/*  766: 766 */      filter.a *= this.alpha;
/*  767:     */    }
/*  768: 768 */    if (filter != null) {
/*  769: 769 */      filter.bind();
/*  770:     */    }
/*  771:     */    
/*  772: 772 */    this.texture.bind();
/*  773:     */    
/*  774: 774 */    GL.glTranslatef(x, y, 0.0F);
/*  775: 775 */    if (this.angle != 0.0F) {
/*  776: 776 */      GL.glTranslatef(this.centerX, this.centerY, 0.0F);
/*  777: 777 */      GL.glRotatef(this.angle, 0.0F, 0.0F, 1.0F);
/*  778: 778 */      GL.glTranslatef(-this.centerX, -this.centerY, 0.0F);
/*  779:     */    }
/*  780:     */    
/*  781: 781 */    GL.glBegin(7);
/*  782: 782 */    drawEmbedded(0.0F, 0.0F, width, height);
/*  783: 783 */    GL.glEnd();
/*  784:     */    
/*  785: 785 */    if (this.angle != 0.0F) {
/*  786: 786 */      GL.glTranslatef(this.centerX, this.centerY, 0.0F);
/*  787: 787 */      GL.glRotatef(-this.angle, 0.0F, 0.0F, 1.0F);
/*  788: 788 */      GL.glTranslatef(-this.centerX, -this.centerY, 0.0F);
/*  789:     */    }
/*  790: 790 */    GL.glTranslatef(-x, -y, 0.0F);
/*  791:     */  }
/*  792:     */  
/*  800:     */  public void drawFlash(float x, float y, float width, float height)
/*  801:     */  {
/*  802: 802 */    drawFlash(x, y, width, height, Color.white);
/*  803:     */  }
/*  804:     */  
/*  810:     */  public void setCenterOfRotation(float x, float y)
/*  811:     */  {
/*  812: 812 */    this.centerX = x;
/*  813: 813 */    this.centerY = y;
/*  814:     */  }
/*  815:     */  
/*  820:     */  public float getCenterOfRotationX()
/*  821:     */  {
/*  822: 822 */    init();
/*  823:     */    
/*  824: 824 */    return this.centerX;
/*  825:     */  }
/*  826:     */  
/*  831:     */  public float getCenterOfRotationY()
/*  832:     */  {
/*  833: 833 */    init();
/*  834:     */    
/*  835: 835 */    return this.centerY;
/*  836:     */  }
/*  837:     */  
/*  846:     */  public void drawFlash(float x, float y, float width, float height, Color col)
/*  847:     */  {
/*  848: 848 */    init();
/*  849:     */    
/*  850: 850 */    col.bind();
/*  851: 851 */    this.texture.bind();
/*  852:     */    
/*  853: 853 */    if (GL.canSecondaryColor()) {
/*  854: 854 */      GL.glEnable(33880);
/*  855: 855 */      GL.glSecondaryColor3ubEXT((byte)(int)(col.r * 255.0F), (byte)(int)(col.g * 255.0F), (byte)(int)(col.b * 255.0F));
/*  856:     */    }
/*  857:     */    
/*  860: 860 */    GL.glTexEnvi(8960, 8704, 8448);
/*  861:     */    
/*  862: 862 */    GL.glTranslatef(x, y, 0.0F);
/*  863: 863 */    if (this.angle != 0.0F) {
/*  864: 864 */      GL.glTranslatef(this.centerX, this.centerY, 0.0F);
/*  865: 865 */      GL.glRotatef(this.angle, 0.0F, 0.0F, 1.0F);
/*  866: 866 */      GL.glTranslatef(-this.centerX, -this.centerY, 0.0F);
/*  867:     */    }
/*  868:     */    
/*  869: 869 */    GL.glBegin(7);
/*  870: 870 */    drawEmbedded(0.0F, 0.0F, width, height);
/*  871: 871 */    GL.glEnd();
/*  872:     */    
/*  873: 873 */    if (this.angle != 0.0F) {
/*  874: 874 */      GL.glTranslatef(this.centerX, this.centerY, 0.0F);
/*  875: 875 */      GL.glRotatef(-this.angle, 0.0F, 0.0F, 1.0F);
/*  876: 876 */      GL.glTranslatef(-this.centerX, -this.centerY, 0.0F);
/*  877:     */    }
/*  878: 878 */    GL.glTranslatef(-x, -y, 0.0F);
/*  879:     */    
/*  880: 880 */    if (GL.canSecondaryColor()) {
/*  881: 881 */      GL.glDisable(33880);
/*  882:     */    }
/*  883:     */  }
/*  884:     */  
/*  890:     */  public void drawFlash(float x, float y)
/*  891:     */  {
/*  892: 892 */    drawFlash(x, y, getWidth(), getHeight());
/*  893:     */  }
/*  894:     */  
/*  900:     */  public void setRotation(float angle)
/*  901:     */  {
/*  902: 902 */    this.angle = (angle % 360.0F);
/*  903:     */  }
/*  904:     */  
/*  910:     */  public float getRotation()
/*  911:     */  {
/*  912: 912 */    return this.angle;
/*  913:     */  }
/*  914:     */  
/*  919:     */  public float getAlpha()
/*  920:     */  {
/*  921: 921 */    return this.alpha;
/*  922:     */  }
/*  923:     */  
/*  928:     */  public void setAlpha(float alpha)
/*  929:     */  {
/*  930: 930 */    this.alpha = alpha;
/*  931:     */  }
/*  932:     */  
/*  938:     */  public void rotate(float angle)
/*  939:     */  {
/*  940: 940 */    this.angle += angle;
/*  941: 941 */    this.angle %= 360.0F;
/*  942:     */  }
/*  943:     */  
/*  953:     */  public Image getSubImage(int x, int y, int width, int height)
/*  954:     */  {
/*  955: 955 */    init();
/*  956:     */    
/*  957: 957 */    float newTextureOffsetX = x / this.width * this.textureWidth + this.textureOffsetX;
/*  958: 958 */    float newTextureOffsetY = y / this.height * this.textureHeight + this.textureOffsetY;
/*  959: 959 */    float newTextureWidth = width / this.width * this.textureWidth;
/*  960: 960 */    float newTextureHeight = height / this.height * this.textureHeight;
/*  961:     */    
/*  962: 962 */    Image sub = new Image();
/*  963: 963 */    sub.inited = true;
/*  964: 964 */    sub.texture = this.texture;
/*  965: 965 */    sub.textureOffsetX = newTextureOffsetX;
/*  966: 966 */    sub.textureOffsetY = newTextureOffsetY;
/*  967: 967 */    sub.textureWidth = newTextureWidth;
/*  968: 968 */    sub.textureHeight = newTextureHeight;
/*  969:     */    
/*  970: 970 */    sub.width = width;
/*  971: 971 */    sub.height = height;
/*  972: 972 */    sub.ref = this.ref;
/*  973: 973 */    sub.centerX = (width / 2);
/*  974: 974 */    sub.centerY = (height / 2);
/*  975:     */    
/*  976: 976 */    return sub;
/*  977:     */  }
/*  978:     */  
/*  988:     */  public void draw(float x, float y, float srcx, float srcy, float srcx2, float srcy2)
/*  989:     */  {
/*  990: 990 */    draw(x, y, x + this.width, y + this.height, srcx, srcy, srcx2, srcy2);
/*  991:     */  }
/*  992:     */  
/* 1004:     */  public void draw(float x, float y, float x2, float y2, float srcx, float srcy, float srcx2, float srcy2)
/* 1005:     */  {
/* 1006:1006 */    draw(x, y, x2, y2, srcx, srcy, srcx2, srcy2, Color.white);
/* 1007:     */  }
/* 1008:     */  
/* 1021:     */  public void draw(float x, float y, float x2, float y2, float srcx, float srcy, float srcx2, float srcy2, Color filter)
/* 1022:     */  {
/* 1023:1023 */    init();
/* 1024:     */    
/* 1025:1025 */    if (this.alpha != 1.0F) {
/* 1026:1026 */      if (filter == null) {
/* 1027:1027 */        filter = Color.white;
/* 1028:     */      }
/* 1029:     */      
/* 1030:1030 */      filter = new Color(filter);
/* 1031:1031 */      filter.a *= this.alpha;
/* 1032:     */    }
/* 1033:1033 */    filter.bind();
/* 1034:1034 */    this.texture.bind();
/* 1035:     */    
/* 1036:1036 */    GL.glTranslatef(x, y, 0.0F);
/* 1037:1037 */    if (this.angle != 0.0F) {
/* 1038:1038 */      GL.glTranslatef(this.centerX, this.centerY, 0.0F);
/* 1039:1039 */      GL.glRotatef(this.angle, 0.0F, 0.0F, 1.0F);
/* 1040:1040 */      GL.glTranslatef(-this.centerX, -this.centerY, 0.0F);
/* 1041:     */    }
/* 1042:     */    
/* 1043:1043 */    GL.glBegin(7);
/* 1044:1044 */    drawEmbedded(0.0F, 0.0F, x2 - x, y2 - y, srcx, srcy, srcx2, srcy2);
/* 1045:1045 */    GL.glEnd();
/* 1046:     */    
/* 1047:1047 */    if (this.angle != 0.0F) {
/* 1048:1048 */      GL.glTranslatef(this.centerX, this.centerY, 0.0F);
/* 1049:1049 */      GL.glRotatef(-this.angle, 0.0F, 0.0F, 1.0F);
/* 1050:1050 */      GL.glTranslatef(-this.centerX, -this.centerY, 0.0F);
/* 1051:     */    }
/* 1052:1052 */    GL.glTranslatef(-x, -y, 0.0F);
/* 1053:     */  }
/* 1054:     */  
/* 1071:     */  public void drawEmbedded(float x, float y, float x2, float y2, float srcx, float srcy, float srcx2, float srcy2)
/* 1072:     */  {
/* 1073:1073 */    drawEmbedded(x, y, x2, y2, srcx, srcy, srcx2, srcy2, null);
/* 1074:     */  }
/* 1075:     */  
/* 1089:     */  public void drawEmbedded(float x, float y, float x2, float y2, float srcx, float srcy, float srcx2, float srcy2, Color filter)
/* 1090:     */  {
/* 1091:1091 */    if (filter != null) {
/* 1092:1092 */      filter.bind();
/* 1093:     */    }
/* 1094:     */    
/* 1095:1095 */    float mywidth = x2 - x;
/* 1096:1096 */    float myheight = y2 - y;
/* 1097:1097 */    float texwidth = srcx2 - srcx;
/* 1098:1098 */    float texheight = srcy2 - srcy;
/* 1099:     */    
/* 1100:1100 */    float newTextureOffsetX = srcx / this.width * this.textureWidth + this.textureOffsetX;
/* 1101:     */    
/* 1102:1102 */    float newTextureOffsetY = srcy / this.height * this.textureHeight + this.textureOffsetY;
/* 1103:     */    
/* 1104:1104 */    float newTextureWidth = texwidth / this.width * this.textureWidth;
/* 1105:     */    
/* 1106:1106 */    float newTextureHeight = texheight / this.height * this.textureHeight;
/* 1107:     */    
/* 1109:1109 */    GL.glTexCoord2f(newTextureOffsetX, newTextureOffsetY);
/* 1110:1110 */    GL.glVertex3f(x, y, 0.0F);
/* 1111:1111 */    GL.glTexCoord2f(newTextureOffsetX, newTextureOffsetY + newTextureHeight);
/* 1112:     */    
/* 1113:1113 */    GL.glVertex3f(x, y + myheight, 0.0F);
/* 1114:1114 */    GL.glTexCoord2f(newTextureOffsetX + newTextureWidth, newTextureOffsetY + newTextureHeight);
/* 1115:     */    
/* 1116:1116 */    GL.glVertex3f(x + mywidth, y + myheight, 0.0F);
/* 1117:1117 */    GL.glTexCoord2f(newTextureOffsetX + newTextureWidth, newTextureOffsetY);
/* 1118:     */    
/* 1119:1119 */    GL.glVertex3f(x + mywidth, y, 0.0F);
/* 1120:     */  }
/* 1121:     */  
/* 1134:     */  public void drawWarped(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4)
/* 1135:     */  {
/* 1136:1136 */    Color.white.bind();
/* 1137:1137 */    this.texture.bind();
/* 1138:     */    
/* 1139:1139 */    GL.glTranslatef(x1, y1, 0.0F);
/* 1140:1140 */    if (this.angle != 0.0F) {
/* 1141:1141 */      GL.glTranslatef(this.centerX, this.centerY, 0.0F);
/* 1142:1142 */      GL.glRotatef(this.angle, 0.0F, 0.0F, 1.0F);
/* 1143:1143 */      GL.glTranslatef(-this.centerX, -this.centerY, 0.0F);
/* 1144:     */    }
/* 1145:     */    
/* 1146:1146 */    GL.glBegin(7);
/* 1147:1147 */    init();
/* 1148:     */    
/* 1149:1149 */    GL.glTexCoord2f(this.textureOffsetX, this.textureOffsetY);
/* 1150:1150 */    GL.glVertex3f(0.0F, 0.0F, 0.0F);
/* 1151:1151 */    GL.glTexCoord2f(this.textureOffsetX, this.textureOffsetY + this.textureHeight);
/* 1152:1152 */    GL.glVertex3f(x2 - x1, y2 - y1, 0.0F);
/* 1153:1153 */    GL.glTexCoord2f(this.textureOffsetX + this.textureWidth, this.textureOffsetY + this.textureHeight);
/* 1154:     */    
/* 1155:1155 */    GL.glVertex3f(x3 - x1, y3 - y1, 0.0F);
/* 1156:1156 */    GL.glTexCoord2f(this.textureOffsetX + this.textureWidth, this.textureOffsetY);
/* 1157:1157 */    GL.glVertex3f(x4 - x1, y4 - y1, 0.0F);
/* 1158:1158 */    GL.glEnd();
/* 1159:     */    
/* 1160:1160 */    if (this.angle != 0.0F) {
/* 1161:1161 */      GL.glTranslatef(this.centerX, this.centerY, 0.0F);
/* 1162:1162 */      GL.glRotatef(-this.angle, 0.0F, 0.0F, 1.0F);
/* 1163:1163 */      GL.glTranslatef(-this.centerX, -this.centerY, 0.0F);
/* 1164:     */    }
/* 1165:1165 */    GL.glTranslatef(-x1, -y1, 0.0F);
/* 1166:     */  }
/* 1167:     */  
/* 1172:     */  public int getWidth()
/* 1173:     */  {
/* 1174:1174 */    init();
/* 1175:1175 */    return this.width;
/* 1176:     */  }
/* 1177:     */  
/* 1182:     */  public int getHeight()
/* 1183:     */  {
/* 1184:1184 */    init();
/* 1185:1185 */    return this.height;
/* 1186:     */  }
/* 1187:     */  
/* 1193:     */  public Image copy()
/* 1194:     */  {
/* 1195:1195 */    init();
/* 1196:1196 */    return getSubImage(0, 0, this.width, this.height);
/* 1197:     */  }
/* 1198:     */  
/* 1204:     */  public Image getScaledCopy(float scale)
/* 1205:     */  {
/* 1206:1206 */    init();
/* 1207:1207 */    return getScaledCopy((int)(this.width * scale), (int)(this.height * scale));
/* 1208:     */  }
/* 1209:     */  
/* 1216:     */  public Image getScaledCopy(int width, int height)
/* 1217:     */  {
/* 1218:1218 */    init();
/* 1219:1219 */    Image image = copy();
/* 1220:1220 */    image.width = width;
/* 1221:1221 */    image.height = height;
/* 1222:1222 */    image.centerX = (width / 2);
/* 1223:1223 */    image.centerY = (height / 2);
/* 1224:1224 */    return image;
/* 1225:     */  }
/* 1226:     */  
/* 1229:     */  public void ensureInverted()
/* 1230:     */  {
/* 1231:1231 */    if (this.textureHeight > 0.0F) {
/* 1232:1232 */      this.textureOffsetY += this.textureHeight;
/* 1233:1233 */      this.textureHeight = (-this.textureHeight);
/* 1234:     */    }
/* 1235:     */  }
/* 1236:     */  
/* 1243:     */  public Image getFlippedCopy(boolean flipHorizontal, boolean flipVertical)
/* 1244:     */  {
/* 1245:1245 */    init();
/* 1246:1246 */    Image image = copy();
/* 1247:     */    
/* 1248:1248 */    if (flipHorizontal) {
/* 1249:1249 */      this.textureOffsetX += this.textureWidth;
/* 1250:1250 */      image.textureWidth = (-this.textureWidth);
/* 1251:     */    }
/* 1252:1252 */    if (flipVertical) {
/* 1253:1253 */      this.textureOffsetY += this.textureHeight;
/* 1254:1254 */      image.textureHeight = (-this.textureHeight);
/* 1255:     */    }
/* 1256:     */    
/* 1257:1257 */    return image;
/* 1258:     */  }
/* 1259:     */  
/* 1264:     */  public void endUse()
/* 1265:     */  {
/* 1266:1266 */    if (inUse != this) {
/* 1267:1267 */      throw new RuntimeException("The sprite sheet is not currently in use");
/* 1268:     */    }
/* 1269:1269 */    inUse = null;
/* 1270:1270 */    GL.glEnd();
/* 1271:     */  }
/* 1272:     */  
/* 1278:     */  public void startUse()
/* 1279:     */  {
/* 1280:1280 */    if (inUse != null) {
/* 1281:1281 */      throw new RuntimeException("Attempt to start use of a sprite sheet before ending use with another - see endUse()");
/* 1282:     */    }
/* 1283:1283 */    inUse = this;
/* 1284:1284 */    init();
/* 1285:     */    
/* 1286:1286 */    Color.white.bind();
/* 1287:1287 */    this.texture.bind();
/* 1288:1288 */    GL.glBegin(7);
/* 1289:     */  }
/* 1290:     */  
/* 1293:     */  public String toString()
/* 1294:     */  {
/* 1295:1295 */    init();
/* 1296:     */    
/* 1297:1297 */    return "[Image " + this.ref + " " + this.width + "x" + this.height + "  " + this.textureOffsetX + "," + this.textureOffsetY + "," + this.textureWidth + "," + this.textureHeight + "]";
/* 1298:     */  }
/* 1299:     */  
/* 1304:     */  public Texture getTexture()
/* 1305:     */  {
/* 1306:1306 */    return this.texture;
/* 1307:     */  }
/* 1308:     */  
/* 1313:     */  public void setTexture(Texture texture)
/* 1314:     */  {
/* 1315:1315 */    this.texture = texture;
/* 1316:1316 */    reinit();
/* 1317:     */  }
/* 1318:     */  
/* 1324:     */  private int translate(byte b)
/* 1325:     */  {
/* 1326:1326 */    if (b < 0) {
/* 1327:1327 */      return 256 + b;
/* 1328:     */    }
/* 1329:     */    
/* 1330:1330 */    return b;
/* 1331:     */  }
/* 1332:     */  
/* 1339:     */  public Color getColor(int x, int y)
/* 1340:     */  {
/* 1341:1341 */    if (this.pixelData == null) {
/* 1342:1342 */      this.pixelData = this.texture.getTextureData();
/* 1343:     */    }
/* 1344:     */    
/* 1345:1345 */    int xo = (int)(this.textureOffsetX * this.texture.getTextureWidth());
/* 1346:1346 */    int yo = (int)(this.textureOffsetY * this.texture.getTextureHeight());
/* 1347:     */    
/* 1348:1348 */    if (this.textureWidth < 0.0F) {
/* 1349:1349 */      x = xo - x;
/* 1350:     */    } else {
/* 1351:1351 */      x = xo + x;
/* 1352:     */    }
/* 1353:     */    
/* 1354:1354 */    if (this.textureHeight < 0.0F) {
/* 1355:1355 */      y = yo - y;
/* 1356:     */    } else {
/* 1357:1357 */      y = yo + y;
/* 1358:     */    }
/* 1359:     */    
/* 1360:1360 */    int offset = x + y * this.texture.getTextureWidth();
/* 1361:1361 */    offset *= (this.texture.hasAlpha() ? 4 : 3);
/* 1362:     */    
/* 1363:1363 */    if (this.texture.hasAlpha()) {
/* 1364:1364 */      return new Color(translate(this.pixelData[offset]), translate(this.pixelData[(offset + 1)]), translate(this.pixelData[(offset + 2)]), translate(this.pixelData[(offset + 3)]));
/* 1365:     */    }
/* 1366:     */    
/* 1367:1367 */    return new Color(translate(this.pixelData[offset]), translate(this.pixelData[(offset + 1)]), translate(this.pixelData[(offset + 2)]));
/* 1368:     */  }
/* 1369:     */  
/* 1376:     */  public boolean isDestroyed()
/* 1377:     */  {
/* 1378:1378 */    return this.destroyed;
/* 1379:     */  }
/* 1380:     */  
/* 1385:     */  public void destroy()
/* 1386:     */    throws SlickException
/* 1387:     */  {
/* 1388:1388 */    if (isDestroyed()) {
/* 1389:1389 */      return;
/* 1390:     */    }
/* 1391:     */    
/* 1392:1392 */    this.destroyed = true;
/* 1393:1393 */    this.texture.release();
/* 1394:1394 */    GraphicsFactory.releaseGraphicsForImage(this);
/* 1395:     */  }
/* 1396:     */  
/* 1399:     */  public void flushPixelData()
/* 1400:     */  {
/* 1401:1401 */    this.pixelData = null;
/* 1402:     */  }
/* 1403:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.Image
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */