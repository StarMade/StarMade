/*    1:     */package org.newdawn.slick;
/*    2:     */
/*    3:     */import java.nio.ByteBuffer;
/*    4:     */import java.nio.DoubleBuffer;
/*    5:     */import java.nio.FloatBuffer;
/*    6:     */import java.security.AccessController;
/*    7:     */import java.security.PrivilegedAction;
/*    8:     */import java.util.ArrayList;
/*    9:     */import org.lwjgl.BufferUtils;
/*   10:     */import org.newdawn.slick.geom.Rectangle;
/*   11:     */import org.newdawn.slick.geom.Shape;
/*   12:     */import org.newdawn.slick.geom.ShapeRenderer;
/*   13:     */import org.newdawn.slick.opengl.Texture;
/*   14:     */import org.newdawn.slick.opengl.TextureImpl;
/*   15:     */import org.newdawn.slick.opengl.renderer.LineStripRenderer;
/*   16:     */import org.newdawn.slick.opengl.renderer.Renderer;
/*   17:     */import org.newdawn.slick.opengl.renderer.SGL;
/*   18:     */import org.newdawn.slick.util.FastTrig;
/*   19:     */import org.newdawn.slick.util.Log;
/*   20:     */
/*   27:     */public class Graphics
/*   28:     */{
/*   29:  29 */  protected static SGL GL = ;
/*   30:     */  
/*   31:  31 */  private static LineStripRenderer LSR = Renderer.getLineStripRenderer();
/*   32:     */  
/*   34:  34 */  public static int MODE_NORMAL = 1;
/*   35:     */  
/*   37:  37 */  public static int MODE_ALPHA_MAP = 2;
/*   38:     */  
/*   40:  40 */  public static int MODE_ALPHA_BLEND = 3;
/*   41:     */  
/*   43:  43 */  public static int MODE_COLOR_MULTIPLY = 4;
/*   44:     */  
/*   46:  46 */  public static int MODE_ADD = 5;
/*   47:     */  
/*   49:  49 */  public static int MODE_SCREEN = 6;
/*   50:     */  
/*   52:     */  private static final int DEFAULT_SEGMENTS = 50;
/*   53:     */  
/*   55:  55 */  protected static Graphics currentGraphics = null;
/*   56:     */  
/*   58:     */  protected static Font DEFAULT_FONT;
/*   59:     */  
/*   61:  61 */  private float sx = 1.0F;
/*   62:     */  
/*   63:  63 */  private float sy = 1.0F;
/*   64:     */  
/*   66:     */  private Font font;
/*   67:     */  
/*   69:     */  public static void setCurrent(Graphics current)
/*   70:     */  {
/*   71:  71 */    if (currentGraphics != current) {
/*   72:  72 */      if (currentGraphics != null) {
/*   73:  73 */        currentGraphics.disable();
/*   74:     */      }
/*   75:  75 */      currentGraphics = current;
/*   76:  76 */      currentGraphics.enable();
/*   77:     */    }
/*   78:     */  }
/*   79:     */  
/*   84:  84 */  private Color currentColor = Color.white;
/*   85:     */  
/*   87:     */  protected int screenWidth;
/*   88:     */  
/*   90:     */  protected int screenHeight;
/*   91:     */  
/*   93:     */  private boolean pushed;
/*   94:     */  
/*   96:     */  private Rectangle clip;
/*   97:     */  
/*   99:  99 */  private DoubleBuffer worldClip = BufferUtils.createDoubleBuffer(4);
/*  100:     */  
/*  102: 102 */  private ByteBuffer readBuffer = BufferUtils.createByteBuffer(4);
/*  103:     */  
/*  105:     */  private boolean antialias;
/*  106:     */  
/*  108:     */  private Rectangle worldClipRecord;
/*  109:     */  
/*  111: 111 */  private int currentDrawingMode = MODE_NORMAL;
/*  112:     */  
/*  114: 114 */  private float lineWidth = 1.0F;
/*  115:     */  
/*  117: 117 */  private ArrayList stack = new ArrayList();
/*  118:     */  
/*  123:     */  private int stackIndex;
/*  124:     */  
/*  129:     */  public Graphics() {}
/*  130:     */  
/*  135:     */  public Graphics(int width, int height)
/*  136:     */  {
/*  137: 137 */    if (DEFAULT_FONT == null) {
/*  138: 138 */      AccessController.doPrivileged(new PrivilegedAction() {
/*  139:     */        public Object run() {
/*  140:     */          try {
/*  141: 141 */            Graphics.DEFAULT_FONT = new AngelCodeFont("org/newdawn/slick/data/defaultfont.fnt", "org/newdawn/slick/data/defaultfont.png");
/*  142:     */          }
/*  143:     */          catch (SlickException e)
/*  144:     */          {
/*  145: 145 */            Log.error(e);
/*  146:     */          }
/*  147: 147 */          return null;
/*  148:     */        }
/*  149:     */      });
/*  150:     */    }
/*  151:     */    
/*  152: 152 */    this.font = DEFAULT_FONT;
/*  153: 153 */    this.screenWidth = width;
/*  154: 154 */    this.screenHeight = height;
/*  155:     */  }
/*  156:     */  
/*  162:     */  void setDimensions(int width, int height)
/*  163:     */  {
/*  164: 164 */    this.screenWidth = width;
/*  165: 165 */    this.screenHeight = height;
/*  166:     */  }
/*  167:     */  
/*  177:     */  public void setDrawMode(int mode)
/*  178:     */  {
/*  179: 179 */    predraw();
/*  180: 180 */    this.currentDrawingMode = mode;
/*  181: 181 */    if (this.currentDrawingMode == MODE_NORMAL) {
/*  182: 182 */      GL.glEnable(3042);
/*  183: 183 */      GL.glColorMask(true, true, true, true);
/*  184: 184 */      GL.glBlendFunc(770, 771);
/*  185:     */    }
/*  186: 186 */    if (this.currentDrawingMode == MODE_ALPHA_MAP) {
/*  187: 187 */      GL.glDisable(3042);
/*  188: 188 */      GL.glColorMask(false, false, false, true);
/*  189:     */    }
/*  190: 190 */    if (this.currentDrawingMode == MODE_ALPHA_BLEND) {
/*  191: 191 */      GL.glEnable(3042);
/*  192: 192 */      GL.glColorMask(true, true, true, false);
/*  193: 193 */      GL.glBlendFunc(772, 773);
/*  194:     */    }
/*  195: 195 */    if (this.currentDrawingMode == MODE_COLOR_MULTIPLY) {
/*  196: 196 */      GL.glEnable(3042);
/*  197: 197 */      GL.glColorMask(true, true, true, true);
/*  198: 198 */      GL.glBlendFunc(769, 768);
/*  199:     */    }
/*  200: 200 */    if (this.currentDrawingMode == MODE_ADD) {
/*  201: 201 */      GL.glEnable(3042);
/*  202: 202 */      GL.glColorMask(true, true, true, true);
/*  203: 203 */      GL.glBlendFunc(1, 1);
/*  204:     */    }
/*  205: 205 */    if (this.currentDrawingMode == MODE_SCREEN) {
/*  206: 206 */      GL.glEnable(3042);
/*  207: 207 */      GL.glColorMask(true, true, true, true);
/*  208: 208 */      GL.glBlendFunc(1, 769);
/*  209:     */    }
/*  210: 210 */    postdraw();
/*  211:     */  }
/*  212:     */  
/*  217:     */  public void clearAlphaMap()
/*  218:     */  {
/*  219: 219 */    pushTransform();
/*  220: 220 */    GL.glLoadIdentity();
/*  221:     */    
/*  222: 222 */    int originalMode = this.currentDrawingMode;
/*  223: 223 */    setDrawMode(MODE_ALPHA_MAP);
/*  224: 224 */    setColor(new Color(0, 0, 0, 0));
/*  225: 225 */    fillRect(0.0F, 0.0F, this.screenWidth, this.screenHeight);
/*  226: 226 */    setColor(this.currentColor);
/*  227: 227 */    setDrawMode(originalMode);
/*  228:     */    
/*  229: 229 */    popTransform();
/*  230:     */  }
/*  231:     */  
/*  235:     */  private void predraw()
/*  236:     */  {
/*  237: 237 */    setCurrent(this);
/*  238:     */  }
/*  239:     */  
/*  244:     */  private void postdraw() {}
/*  245:     */  
/*  250:     */  protected void enable() {}
/*  251:     */  
/*  255:     */  public void flush()
/*  256:     */  {
/*  257: 257 */    if (currentGraphics == this) {
/*  258: 258 */      currentGraphics.disable();
/*  259: 259 */      currentGraphics = null;
/*  260:     */    }
/*  261:     */  }
/*  262:     */  
/*  267:     */  protected void disable() {}
/*  268:     */  
/*  273:     */  public Font getFont()
/*  274:     */  {
/*  275: 275 */    return this.font;
/*  276:     */  }
/*  277:     */  
/*  285:     */  public void setBackground(Color color)
/*  286:     */  {
/*  287: 287 */    predraw();
/*  288: 288 */    GL.glClearColor(color.r, color.g, color.b, color.a);
/*  289: 289 */    postdraw();
/*  290:     */  }
/*  291:     */  
/*  296:     */  public Color getBackground()
/*  297:     */  {
/*  298: 298 */    predraw();
/*  299: 299 */    FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
/*  300: 300 */    GL.glGetFloat(3106, buffer);
/*  301: 301 */    postdraw();
/*  302:     */    
/*  303: 303 */    return new Color(buffer);
/*  304:     */  }
/*  305:     */  
/*  308:     */  public void clear()
/*  309:     */  {
/*  310: 310 */    predraw();
/*  311: 311 */    GL.glClear(16384);
/*  312: 312 */    postdraw();
/*  313:     */  }
/*  314:     */  
/*  317:     */  public void resetTransform()
/*  318:     */  {
/*  319: 319 */    this.sx = 1.0F;
/*  320: 320 */    this.sy = 1.0F;
/*  321:     */    
/*  322: 322 */    if (this.pushed) {
/*  323: 323 */      predraw();
/*  324: 324 */      GL.glPopMatrix();
/*  325: 325 */      this.pushed = false;
/*  326: 326 */      postdraw();
/*  327:     */    }
/*  328:     */  }
/*  329:     */  
/*  332:     */  private void checkPush()
/*  333:     */  {
/*  334: 334 */    if (!this.pushed) {
/*  335: 335 */      predraw();
/*  336: 336 */      GL.glPushMatrix();
/*  337: 337 */      this.pushed = true;
/*  338: 338 */      postdraw();
/*  339:     */    }
/*  340:     */  }
/*  341:     */  
/*  349:     */  public void scale(float sx, float sy)
/*  350:     */  {
/*  351: 351 */    this.sx *= sx;
/*  352: 352 */    this.sy *= sy;
/*  353:     */    
/*  354: 354 */    checkPush();
/*  355:     */    
/*  356: 356 */    predraw();
/*  357: 357 */    GL.glScalef(sx, sy, 1.0F);
/*  358: 358 */    postdraw();
/*  359:     */  }
/*  360:     */  
/*  370:     */  public void rotate(float rx, float ry, float ang)
/*  371:     */  {
/*  372: 372 */    checkPush();
/*  373:     */    
/*  374: 374 */    predraw();
/*  375: 375 */    translate(rx, ry);
/*  376: 376 */    GL.glRotatef(ang, 0.0F, 0.0F, 1.0F);
/*  377: 377 */    translate(-rx, -ry);
/*  378: 378 */    postdraw();
/*  379:     */  }
/*  380:     */  
/*  388:     */  public void translate(float x, float y)
/*  389:     */  {
/*  390: 390 */    checkPush();
/*  391:     */    
/*  392: 392 */    predraw();
/*  393: 393 */    GL.glTranslatef(x, y, 0.0F);
/*  394: 394 */    postdraw();
/*  395:     */  }
/*  396:     */  
/*  402:     */  public void setFont(Font font)
/*  403:     */  {
/*  404: 404 */    this.font = font;
/*  405:     */  }
/*  406:     */  
/*  409:     */  public void resetFont()
/*  410:     */  {
/*  411: 411 */    this.font = DEFAULT_FONT;
/*  412:     */  }
/*  413:     */  
/*  419:     */  public void setColor(Color color)
/*  420:     */  {
/*  421: 421 */    if (color == null) {
/*  422: 422 */      return;
/*  423:     */    }
/*  424:     */    
/*  425: 425 */    this.currentColor = new Color(color);
/*  426: 426 */    predraw();
/*  427: 427 */    this.currentColor.bind();
/*  428: 428 */    postdraw();
/*  429:     */  }
/*  430:     */  
/*  435:     */  public Color getColor()
/*  436:     */  {
/*  437: 437 */    return new Color(this.currentColor);
/*  438:     */  }
/*  439:     */  
/*  451:     */  public void drawLine(float x1, float y1, float x2, float y2)
/*  452:     */  {
/*  453: 453 */    float lineWidth = this.lineWidth - 1.0F;
/*  454:     */    
/*  455: 455 */    if (LSR.applyGLLineFixes()) {
/*  456: 456 */      if (x1 == x2) {
/*  457: 457 */        if (y1 > y2) {
/*  458: 458 */          float temp = y2;
/*  459: 459 */          y2 = y1;
/*  460: 460 */          y1 = temp;
/*  461:     */        }
/*  462: 462 */        float step = 1.0F / this.sy;
/*  463: 463 */        lineWidth /= this.sy;
/*  464: 464 */        fillRect(x1 - lineWidth / 2.0F, y1 - lineWidth / 2.0F, lineWidth + step, y2 - y1 + lineWidth + step);
/*  465: 465 */        return; }
/*  466: 466 */      if (y1 == y2) {
/*  467: 467 */        if (x1 > x2) {
/*  468: 468 */          float temp = x2;
/*  469: 469 */          x2 = x1;
/*  470: 470 */          x1 = temp;
/*  471:     */        }
/*  472: 472 */        float step = 1.0F / this.sx;
/*  473: 473 */        lineWidth /= this.sx;
/*  474: 474 */        fillRect(x1 - lineWidth / 2.0F, y1 - lineWidth / 2.0F, x2 - x1 + lineWidth + step, lineWidth + step);
/*  475: 475 */        return;
/*  476:     */      }
/*  477:     */    }
/*  478:     */    
/*  479: 479 */    predraw();
/*  480: 480 */    this.currentColor.bind();
/*  481: 481 */    TextureImpl.bindNone();
/*  482:     */    
/*  483: 483 */    LSR.start();
/*  484: 484 */    LSR.vertex(x1, y1);
/*  485: 485 */    LSR.vertex(x2, y2);
/*  486: 486 */    LSR.end();
/*  487:     */    
/*  488: 488 */    postdraw();
/*  489:     */  }
/*  490:     */  
/*  498:     */  public void draw(Shape shape, ShapeFill fill)
/*  499:     */  {
/*  500: 500 */    predraw();
/*  501: 501 */    TextureImpl.bindNone();
/*  502:     */    
/*  503: 503 */    ShapeRenderer.draw(shape, fill);
/*  504:     */    
/*  505: 505 */    this.currentColor.bind();
/*  506: 506 */    postdraw();
/*  507:     */  }
/*  508:     */  
/*  516:     */  public void fill(Shape shape, ShapeFill fill)
/*  517:     */  {
/*  518: 518 */    predraw();
/*  519: 519 */    TextureImpl.bindNone();
/*  520:     */    
/*  521: 521 */    ShapeRenderer.fill(shape, fill);
/*  522:     */    
/*  523: 523 */    this.currentColor.bind();
/*  524: 524 */    postdraw();
/*  525:     */  }
/*  526:     */  
/*  532:     */  public void draw(Shape shape)
/*  533:     */  {
/*  534: 534 */    predraw();
/*  535: 535 */    TextureImpl.bindNone();
/*  536: 536 */    this.currentColor.bind();
/*  537:     */    
/*  538: 538 */    ShapeRenderer.draw(shape);
/*  539:     */    
/*  540: 540 */    postdraw();
/*  541:     */  }
/*  542:     */  
/*  548:     */  public void fill(Shape shape)
/*  549:     */  {
/*  550: 550 */    predraw();
/*  551: 551 */    TextureImpl.bindNone();
/*  552: 552 */    this.currentColor.bind();
/*  553:     */    
/*  554: 554 */    ShapeRenderer.fill(shape);
/*  555:     */    
/*  556: 556 */    postdraw();
/*  557:     */  }
/*  558:     */  
/*  566:     */  public void texture(Shape shape, Image image)
/*  567:     */  {
/*  568: 568 */    texture(shape, image, 0.01F, 0.01F, false);
/*  569:     */  }
/*  570:     */  
/*  580:     */  public void texture(Shape shape, Image image, ShapeFill fill)
/*  581:     */  {
/*  582: 582 */    texture(shape, image, 0.01F, 0.01F, fill);
/*  583:     */  }
/*  584:     */  
/*  594:     */  public void texture(Shape shape, Image image, boolean fit)
/*  595:     */  {
/*  596: 596 */    if (fit) {
/*  597: 597 */      texture(shape, image, 1.0F, 1.0F, true);
/*  598:     */    } else {
/*  599: 599 */      texture(shape, image, 0.01F, 0.01F, false);
/*  600:     */    }
/*  601:     */  }
/*  602:     */  
/*  614:     */  public void texture(Shape shape, Image image, float scaleX, float scaleY)
/*  615:     */  {
/*  616: 616 */    texture(shape, image, scaleX, scaleY, false);
/*  617:     */  }
/*  618:     */  
/*  633:     */  public void texture(Shape shape, Image image, float scaleX, float scaleY, boolean fit)
/*  634:     */  {
/*  635: 635 */    predraw();
/*  636: 636 */    TextureImpl.bindNone();
/*  637: 637 */    this.currentColor.bind();
/*  638:     */    
/*  639: 639 */    if (fit) {
/*  640: 640 */      ShapeRenderer.textureFit(shape, image, scaleX, scaleY);
/*  641:     */    } else {
/*  642: 642 */      ShapeRenderer.texture(shape, image, scaleX, scaleY);
/*  643:     */    }
/*  644:     */    
/*  645: 645 */    postdraw();
/*  646:     */  }
/*  647:     */  
/*  662:     */  public void texture(Shape shape, Image image, float scaleX, float scaleY, ShapeFill fill)
/*  663:     */  {
/*  664: 664 */    predraw();
/*  665: 665 */    TextureImpl.bindNone();
/*  666: 666 */    this.currentColor.bind();
/*  667:     */    
/*  668: 668 */    ShapeRenderer.texture(shape, image, scaleX, scaleY, fill);
/*  669:     */    
/*  670: 670 */    postdraw();
/*  671:     */  }
/*  672:     */  
/*  684:     */  public void drawRect(float x1, float y1, float width, float height)
/*  685:     */  {
/*  686: 686 */    float lineWidth = getLineWidth();
/*  687:     */    
/*  688: 688 */    drawLine(x1, y1, x1 + width, y1);
/*  689: 689 */    drawLine(x1 + width, y1, x1 + width, y1 + height);
/*  690: 690 */    drawLine(x1 + width, y1 + height, x1, y1 + height);
/*  691: 691 */    drawLine(x1, y1 + height, x1, y1);
/*  692:     */  }
/*  693:     */  
/*  697:     */  public void clearClip()
/*  698:     */  {
/*  699: 699 */    this.clip = null;
/*  700: 700 */    predraw();
/*  701: 701 */    GL.glDisable(3089);
/*  702: 702 */    postdraw();
/*  703:     */  }
/*  704:     */  
/*  719:     */  public void setWorldClip(float x, float y, float width, float height)
/*  720:     */  {
/*  721: 721 */    predraw();
/*  722: 722 */    this.worldClipRecord = new Rectangle(x, y, width, height);
/*  723:     */    
/*  724: 724 */    GL.glEnable(12288);
/*  725: 725 */    this.worldClip.put(1.0D).put(0.0D).put(0.0D).put(-x).flip();
/*  726: 726 */    GL.glClipPlane(12288, this.worldClip);
/*  727: 727 */    GL.glEnable(12289);
/*  728: 728 */    this.worldClip.put(-1.0D).put(0.0D).put(0.0D).put(x + width).flip();
/*  729: 729 */    GL.glClipPlane(12289, this.worldClip);
/*  730:     */    
/*  731: 731 */    GL.glEnable(12290);
/*  732: 732 */    this.worldClip.put(0.0D).put(1.0D).put(0.0D).put(-y).flip();
/*  733: 733 */    GL.glClipPlane(12290, this.worldClip);
/*  734: 734 */    GL.glEnable(12291);
/*  735: 735 */    this.worldClip.put(0.0D).put(-1.0D).put(0.0D).put(y + height).flip();
/*  736: 736 */    GL.glClipPlane(12291, this.worldClip);
/*  737: 737 */    postdraw();
/*  738:     */  }
/*  739:     */  
/*  742:     */  public void clearWorldClip()
/*  743:     */  {
/*  744: 744 */    predraw();
/*  745: 745 */    this.worldClipRecord = null;
/*  746: 746 */    GL.glDisable(12288);
/*  747: 747 */    GL.glDisable(12289);
/*  748: 748 */    GL.glDisable(12290);
/*  749: 749 */    GL.glDisable(12291);
/*  750: 750 */    postdraw();
/*  751:     */  }
/*  752:     */  
/*  759:     */  public void setWorldClip(Rectangle clip)
/*  760:     */  {
/*  761: 761 */    if (clip == null) {
/*  762: 762 */      clearWorldClip();
/*  763:     */    } else {
/*  764: 764 */      setWorldClip(clip.getX(), clip.getY(), clip.getWidth(), clip.getHeight());
/*  765:     */    }
/*  766:     */  }
/*  767:     */  
/*  773:     */  public Rectangle getWorldClip()
/*  774:     */  {
/*  775: 775 */    return this.worldClipRecord;
/*  776:     */  }
/*  777:     */  
/*  791:     */  public void setClip(int x, int y, int width, int height)
/*  792:     */  {
/*  793: 793 */    predraw();
/*  794:     */    
/*  795: 795 */    if (this.clip == null) {
/*  796: 796 */      GL.glEnable(3089);
/*  797: 797 */      this.clip = new Rectangle(x, y, width, height);
/*  798:     */    } else {
/*  799: 799 */      this.clip.setBounds(x, y, width, height);
/*  800:     */    }
/*  801:     */    
/*  802: 802 */    GL.glScissor(x, this.screenHeight - y - height, width, height);
/*  803: 803 */    postdraw();
/*  804:     */  }
/*  805:     */  
/*  814:     */  public void setClip(Rectangle rect)
/*  815:     */  {
/*  816: 816 */    if (rect == null) {
/*  817: 817 */      clearClip();
/*  818: 818 */      return;
/*  819:     */    }
/*  820:     */    
/*  821: 821 */    setClip((int)rect.getX(), (int)rect.getY(), (int)rect.getWidth(), (int)rect.getHeight());
/*  822:     */  }
/*  823:     */  
/*  830:     */  public Rectangle getClip()
/*  831:     */  {
/*  832: 832 */    return this.clip;
/*  833:     */  }
/*  834:     */  
/*  854:     */  public void fillRect(float x, float y, float width, float height, Image pattern, float offX, float offY)
/*  855:     */  {
/*  856: 856 */    int cols = (int)Math.ceil(width / pattern.getWidth()) + 2;
/*  857: 857 */    int rows = (int)Math.ceil(height / pattern.getHeight()) + 2;
/*  858:     */    
/*  859: 859 */    Rectangle preClip = getWorldClip();
/*  860: 860 */    setWorldClip(x, y, width, height);
/*  861:     */    
/*  862: 862 */    predraw();
/*  863:     */    
/*  864: 864 */    for (int c = 0; c < cols; c++) {
/*  865: 865 */      for (int r = 0; r < rows; r++) {
/*  866: 866 */        pattern.draw(c * pattern.getWidth() + x - offX, r * pattern.getHeight() + y - offY);
/*  867:     */      }
/*  868:     */    }
/*  869:     */    
/*  870: 870 */    postdraw();
/*  871:     */    
/*  872: 872 */    setWorldClip(preClip);
/*  873:     */  }
/*  874:     */  
/*  886:     */  public void fillRect(float x1, float y1, float width, float height)
/*  887:     */  {
/*  888: 888 */    predraw();
/*  889: 889 */    TextureImpl.bindNone();
/*  890: 890 */    this.currentColor.bind();
/*  891:     */    
/*  892: 892 */    GL.glBegin(7);
/*  893: 893 */    GL.glVertex2f(x1, y1);
/*  894: 894 */    GL.glVertex2f(x1 + width, y1);
/*  895: 895 */    GL.glVertex2f(x1 + width, y1 + height);
/*  896: 896 */    GL.glVertex2f(x1, y1 + height);
/*  897: 897 */    GL.glEnd();
/*  898: 898 */    postdraw();
/*  899:     */  }
/*  900:     */  
/*  914:     */  public void drawOval(float x1, float y1, float width, float height)
/*  915:     */  {
/*  916: 916 */    drawOval(x1, y1, width, height, 50);
/*  917:     */  }
/*  918:     */  
/*  935:     */  public void drawOval(float x1, float y1, float width, float height, int segments)
/*  936:     */  {
/*  937: 937 */    drawArc(x1, y1, width, height, segments, 0.0F, 360.0F);
/*  938:     */  }
/*  939:     */  
/*  958:     */  public void drawArc(float x1, float y1, float width, float height, float start, float end)
/*  959:     */  {
/*  960: 960 */    drawArc(x1, y1, width, height, 50, start, end);
/*  961:     */  }
/*  962:     */  
/*  983:     */  public void drawArc(float x1, float y1, float width, float height, int segments, float start, float end)
/*  984:     */  {
/*  985: 985 */    predraw();
/*  986: 986 */    TextureImpl.bindNone();
/*  987: 987 */    this.currentColor.bind();
/*  988:     */    
/*  989: 989 */    while (end < start) {
/*  990: 990 */      end += 360.0F;
/*  991:     */    }
/*  992:     */    
/*  993: 993 */    float cx = x1 + width / 2.0F;
/*  994: 994 */    float cy = y1 + height / 2.0F;
/*  995:     */    
/*  996: 996 */    LSR.start();
/*  997: 997 */    int step = 360 / segments;
/*  998:     */    
/*  999: 999 */    for (int a = (int)start; a < (int)(end + step); a += step) {
/* 1000:1000 */      float ang = a;
/* 1001:1001 */      if (ang > end) {
/* 1002:1002 */        ang = end;
/* 1003:     */      }
/* 1004:1004 */      float x = (float)(cx + FastTrig.cos(Math.toRadians(ang)) * width / 2.0D);
/* 1005:1005 */      float y = (float)(cy + FastTrig.sin(Math.toRadians(ang)) * height / 2.0D);
/* 1006:     */      
/* 1007:1007 */      LSR.vertex(x, y);
/* 1008:     */    }
/* 1009:1009 */    LSR.end();
/* 1010:1010 */    postdraw();
/* 1011:     */  }
/* 1012:     */  
/* 1026:     */  public void fillOval(float x1, float y1, float width, float height)
/* 1027:     */  {
/* 1028:1028 */    fillOval(x1, y1, width, height, 50);
/* 1029:     */  }
/* 1030:     */  
/* 1047:     */  public void fillOval(float x1, float y1, float width, float height, int segments)
/* 1048:     */  {
/* 1049:1049 */    fillArc(x1, y1, width, height, segments, 0.0F, 360.0F);
/* 1050:     */  }
/* 1051:     */  
/* 1070:     */  public void fillArc(float x1, float y1, float width, float height, float start, float end)
/* 1071:     */  {
/* 1072:1072 */    fillArc(x1, y1, width, height, 50, start, end);
/* 1073:     */  }
/* 1074:     */  
/* 1095:     */  public void fillArc(float x1, float y1, float width, float height, int segments, float start, float end)
/* 1096:     */  {
/* 1097:1097 */    predraw();
/* 1098:1098 */    TextureImpl.bindNone();
/* 1099:1099 */    this.currentColor.bind();
/* 1100:     */    
/* 1101:1101 */    while (end < start) {
/* 1102:1102 */      end += 360.0F;
/* 1103:     */    }
/* 1104:     */    
/* 1105:1105 */    float cx = x1 + width / 2.0F;
/* 1106:1106 */    float cy = y1 + height / 2.0F;
/* 1107:     */    
/* 1108:1108 */    GL.glBegin(6);
/* 1109:1109 */    int step = 360 / segments;
/* 1110:     */    
/* 1111:1111 */    GL.glVertex2f(cx, cy);
/* 1112:     */    
/* 1113:1113 */    for (int a = (int)start; a < (int)(end + step); a += step) {
/* 1114:1114 */      float ang = a;
/* 1115:1115 */      if (ang > end) {
/* 1116:1116 */        ang = end;
/* 1117:     */      }
/* 1118:     */      
/* 1119:1119 */      float x = (float)(cx + FastTrig.cos(Math.toRadians(ang)) * width / 2.0D);
/* 1120:1120 */      float y = (float)(cy + FastTrig.sin(Math.toRadians(ang)) * height / 2.0D);
/* 1121:     */      
/* 1122:1122 */      GL.glVertex2f(x, y);
/* 1123:     */    }
/* 1124:1124 */    GL.glEnd();
/* 1125:     */    
/* 1126:1126 */    if (this.antialias) {
/* 1127:1127 */      GL.glBegin(6);
/* 1128:1128 */      GL.glVertex2f(cx, cy);
/* 1129:1129 */      if (end != 360.0F) {
/* 1130:1130 */        end -= 10.0F;
/* 1131:     */      }
/* 1132:     */      
/* 1133:1133 */      for (int a = (int)start; a < (int)(end + step); a += step) {
/* 1134:1134 */        float ang = a;
/* 1135:1135 */        if (ang > end) {
/* 1136:1136 */          ang = end;
/* 1137:     */        }
/* 1138:     */        
/* 1139:1139 */        float x = (float)(cx + FastTrig.cos(Math.toRadians(ang + 10.0F)) * width / 2.0D);
/* 1140:     */        
/* 1141:1141 */        float y = (float)(cy + FastTrig.sin(Math.toRadians(ang + 10.0F)) * height / 2.0D);
/* 1142:     */        
/* 1144:1144 */        GL.glVertex2f(x, y);
/* 1145:     */      }
/* 1146:1146 */      GL.glEnd();
/* 1147:     */    }
/* 1148:     */    
/* 1149:1149 */    postdraw();
/* 1150:     */  }
/* 1151:     */  
/* 1166:     */  public void drawRoundRect(float x, float y, float width, float height, int cornerRadius)
/* 1167:     */  {
/* 1168:1168 */    drawRoundRect(x, y, width, height, cornerRadius, 50);
/* 1169:     */  }
/* 1170:     */  
/* 1187:     */  public void drawRoundRect(float x, float y, float width, float height, int cornerRadius, int segs)
/* 1188:     */  {
/* 1189:1189 */    if (cornerRadius < 0)
/* 1190:1190 */      throw new IllegalArgumentException("corner radius must be > 0");
/* 1191:1191 */    if (cornerRadius == 0) {
/* 1192:1192 */      drawRect(x, y, width, height);
/* 1193:1193 */      return;
/* 1194:     */    }
/* 1195:     */    
/* 1196:1196 */    int mr = (int)Math.min(width, height) / 2;
/* 1197:     */    
/* 1198:1198 */    if (cornerRadius > mr) {
/* 1199:1199 */      cornerRadius = mr;
/* 1200:     */    }
/* 1201:     */    
/* 1202:1202 */    drawLine(x + cornerRadius, y, x + width - cornerRadius, y);
/* 1203:1203 */    drawLine(x, y + cornerRadius, x, y + height - cornerRadius);
/* 1204:1204 */    drawLine(x + width, y + cornerRadius, x + width, y + height - cornerRadius);
/* 1205:     */    
/* 1206:1206 */    drawLine(x + cornerRadius, y + height, x + width - cornerRadius, y + height);
/* 1207:     */    
/* 1209:1209 */    float d = cornerRadius * 2;
/* 1210:     */    
/* 1211:1211 */    drawArc(x + width - d, y + height - d, d, d, segs, 0.0F, 90.0F);
/* 1212:     */    
/* 1213:1213 */    drawArc(x, y + height - d, d, d, segs, 90.0F, 180.0F);
/* 1214:     */    
/* 1215:1215 */    drawArc(x + width - d, y, d, d, segs, 270.0F, 360.0F);
/* 1216:     */    
/* 1217:1217 */    drawArc(x, y, d, d, segs, 180.0F, 270.0F);
/* 1218:     */  }
/* 1219:     */  
/* 1234:     */  public void fillRoundRect(float x, float y, float width, float height, int cornerRadius)
/* 1235:     */  {
/* 1236:1236 */    fillRoundRect(x, y, width, height, cornerRadius, 50);
/* 1237:     */  }
/* 1238:     */  
/* 1255:     */  public void fillRoundRect(float x, float y, float width, float height, int cornerRadius, int segs)
/* 1256:     */  {
/* 1257:1257 */    if (cornerRadius < 0)
/* 1258:1258 */      throw new IllegalArgumentException("corner radius must be > 0");
/* 1259:1259 */    if (cornerRadius == 0) {
/* 1260:1260 */      fillRect(x, y, width, height);
/* 1261:1261 */      return;
/* 1262:     */    }
/* 1263:     */    
/* 1264:1264 */    int mr = (int)Math.min(width, height) / 2;
/* 1265:     */    
/* 1266:1266 */    if (cornerRadius > mr) {
/* 1267:1267 */      cornerRadius = mr;
/* 1268:     */    }
/* 1269:     */    
/* 1270:1270 */    float d = cornerRadius * 2;
/* 1271:     */    
/* 1272:1272 */    fillRect(x + cornerRadius, y, width - d, cornerRadius);
/* 1273:1273 */    fillRect(x, y + cornerRadius, cornerRadius, height - d);
/* 1274:1274 */    fillRect(x + width - cornerRadius, y + cornerRadius, cornerRadius, height - d);
/* 1275:     */    
/* 1276:1276 */    fillRect(x + cornerRadius, y + height - cornerRadius, width - d, cornerRadius);
/* 1277:     */    
/* 1278:1278 */    fillRect(x + cornerRadius, y + cornerRadius, width - d, height - d);
/* 1279:     */    
/* 1281:1281 */    fillArc(x + width - d, y + height - d, d, d, segs, 0.0F, 90.0F);
/* 1282:     */    
/* 1283:1283 */    fillArc(x, y + height - d, d, d, segs, 90.0F, 180.0F);
/* 1284:     */    
/* 1285:1285 */    fillArc(x + width - d, y, d, d, segs, 270.0F, 360.0F);
/* 1286:     */    
/* 1287:1287 */    fillArc(x, y, d, d, segs, 180.0F, 270.0F);
/* 1288:     */  }
/* 1289:     */  
/* 1296:     */  public void setLineWidth(float width)
/* 1297:     */  {
/* 1298:1298 */    predraw();
/* 1299:1299 */    this.lineWidth = width;
/* 1300:1300 */    LSR.setWidth(width);
/* 1301:1301 */    GL.glPointSize(width);
/* 1302:1302 */    postdraw();
/* 1303:     */  }
/* 1304:     */  
/* 1309:     */  public float getLineWidth()
/* 1310:     */  {
/* 1311:1311 */    return this.lineWidth;
/* 1312:     */  }
/* 1313:     */  
/* 1316:     */  public void resetLineWidth()
/* 1317:     */  {
/* 1318:1318 */    predraw();
/* 1319:     */    
/* 1320:1320 */    Renderer.getLineStripRenderer().setWidth(1.0F);
/* 1321:1321 */    GL.glLineWidth(1.0F);
/* 1322:1322 */    GL.glPointSize(1.0F);
/* 1323:     */    
/* 1324:1324 */    postdraw();
/* 1325:     */  }
/* 1326:     */  
/* 1332:     */  public void setAntiAlias(boolean anti)
/* 1333:     */  {
/* 1334:1334 */    predraw();
/* 1335:1335 */    this.antialias = anti;
/* 1336:1336 */    LSR.setAntiAlias(anti);
/* 1337:1337 */    if (anti) {
/* 1338:1338 */      GL.glEnable(2881);
/* 1339:     */    } else {
/* 1340:1340 */      GL.glDisable(2881);
/* 1341:     */    }
/* 1342:1342 */    postdraw();
/* 1343:     */  }
/* 1344:     */  
/* 1349:     */  public boolean isAntiAlias()
/* 1350:     */  {
/* 1351:1351 */    return this.antialias;
/* 1352:     */  }
/* 1353:     */  
/* 1363:     */  public void drawString(String str, float x, float y)
/* 1364:     */  {
/* 1365:1365 */    predraw();
/* 1366:1366 */    this.font.drawString(x, y, str, this.currentColor);
/* 1367:1367 */    postdraw();
/* 1368:     */  }
/* 1369:     */  
/* 1381:     */  public void drawImage(Image image, float x, float y, Color col)
/* 1382:     */  {
/* 1383:1383 */    predraw();
/* 1384:1384 */    image.draw(x, y, col);
/* 1385:1385 */    this.currentColor.bind();
/* 1386:1386 */    postdraw();
/* 1387:     */  }
/* 1388:     */  
/* 1398:     */  public void drawAnimation(Animation anim, float x, float y)
/* 1399:     */  {
/* 1400:1400 */    drawAnimation(anim, x, y, Color.white);
/* 1401:     */  }
/* 1402:     */  
/* 1414:     */  public void drawAnimation(Animation anim, float x, float y, Color col)
/* 1415:     */  {
/* 1416:1416 */    predraw();
/* 1417:1417 */    anim.draw(x, y, col);
/* 1418:1418 */    this.currentColor.bind();
/* 1419:1419 */    postdraw();
/* 1420:     */  }
/* 1421:     */  
/* 1431:     */  public void drawImage(Image image, float x, float y)
/* 1432:     */  {
/* 1433:1433 */    drawImage(image, x, y, Color.white);
/* 1434:     */  }
/* 1435:     */  
/* 1463:     */  public void drawImage(Image image, float x, float y, float x2, float y2, float srcx, float srcy, float srcx2, float srcy2)
/* 1464:     */  {
/* 1465:1465 */    predraw();
/* 1466:1466 */    image.draw(x, y, x2, y2, srcx, srcy, srcx2, srcy2);
/* 1467:1467 */    this.currentColor.bind();
/* 1468:1468 */    postdraw();
/* 1469:     */  }
/* 1470:     */  
/* 1494:     */  public void drawImage(Image image, float x, float y, float srcx, float srcy, float srcx2, float srcy2)
/* 1495:     */  {
/* 1496:1496 */    drawImage(image, x, y, x + image.getWidth(), y + image.getHeight(), srcx, srcy, srcx2, srcy2);
/* 1497:     */  }
/* 1498:     */  
/* 1510:     */  public void copyArea(Image target, int x, int y)
/* 1511:     */  {
/* 1512:1512 */    int format = target.getTexture().hasAlpha() ? 6408 : 6407;
/* 1513:1513 */    target.bind();
/* 1514:1514 */    GL.glCopyTexImage2D(3553, 0, format, x, this.screenHeight - (y + target.getHeight()), target.getTexture().getTextureWidth(), target.getTexture().getTextureHeight(), 0);
/* 1515:     */    
/* 1517:1517 */    target.ensureInverted();
/* 1518:     */  }
/* 1519:     */  
/* 1526:     */  private int translate(byte b)
/* 1527:     */  {
/* 1528:1528 */    if (b < 0) {
/* 1529:1529 */      return 256 + b;
/* 1530:     */    }
/* 1531:     */    
/* 1532:1532 */    return b;
/* 1533:     */  }
/* 1534:     */  
/* 1543:     */  public Color getPixel(int x, int y)
/* 1544:     */  {
/* 1545:1545 */    predraw();
/* 1546:1546 */    GL.glReadPixels(x, this.screenHeight - y, 1, 1, 6408, 5121, this.readBuffer);
/* 1547:     */    
/* 1548:1548 */    postdraw();
/* 1549:     */    
/* 1550:1550 */    return new Color(translate(this.readBuffer.get(0)), translate(this.readBuffer.get(1)), translate(this.readBuffer.get(2)), translate(this.readBuffer.get(3)));
/* 1551:     */  }
/* 1552:     */  
/* 1564:     */  public void getArea(int x, int y, int width, int height, ByteBuffer target)
/* 1565:     */  {
/* 1566:1566 */    if (target.capacity() < width * height * 4)
/* 1567:     */    {
/* 1568:1568 */      throw new IllegalArgumentException("Byte buffer provided to get area is not big enough");
/* 1569:     */    }
/* 1570:     */    
/* 1571:1571 */    predraw();
/* 1572:1572 */    GL.glReadPixels(x, this.screenHeight - y - height, width, height, 6408, 5121, target);
/* 1573:     */    
/* 1574:1574 */    postdraw();
/* 1575:     */  }
/* 1576:     */  
/* 1606:     */  public void drawImage(Image image, float x, float y, float x2, float y2, float srcx, float srcy, float srcx2, float srcy2, Color col)
/* 1607:     */  {
/* 1608:1608 */    predraw();
/* 1609:1609 */    image.draw(x, y, x2, y2, srcx, srcy, srcx2, srcy2, col);
/* 1610:1610 */    this.currentColor.bind();
/* 1611:1611 */    postdraw();
/* 1612:     */  }
/* 1613:     */  
/* 1639:     */  public void drawImage(Image image, float x, float y, float srcx, float srcy, float srcx2, float srcy2, Color col)
/* 1640:     */  {
/* 1641:1641 */    drawImage(image, x, y, x + image.getWidth(), y + image.getHeight(), srcx, srcy, srcx2, srcy2, col);
/* 1642:     */  }
/* 1643:     */  
/* 1674:     */  public void drawGradientLine(float x1, float y1, float red1, float green1, float blue1, float alpha1, float x2, float y2, float red2, float green2, float blue2, float alpha2)
/* 1675:     */  {
/* 1676:1676 */    predraw();
/* 1677:     */    
/* 1678:1678 */    TextureImpl.bindNone();
/* 1679:     */    
/* 1680:1680 */    GL.glBegin(1);
/* 1681:     */    
/* 1682:1682 */    GL.glColor4f(red1, green1, blue1, alpha1);
/* 1683:1683 */    GL.glVertex2f(x1, y1);
/* 1684:     */    
/* 1685:1685 */    GL.glColor4f(red2, green2, blue2, alpha2);
/* 1686:1686 */    GL.glVertex2f(x2, y2);
/* 1687:     */    
/* 1688:1688 */    GL.glEnd();
/* 1689:     */    
/* 1690:1690 */    postdraw();
/* 1691:     */  }
/* 1692:     */  
/* 1709:     */  public void drawGradientLine(float x1, float y1, Color Color1, float x2, float y2, Color Color2)
/* 1710:     */  {
/* 1711:1711 */    predraw();
/* 1712:     */    
/* 1713:1713 */    TextureImpl.bindNone();
/* 1714:     */    
/* 1715:1715 */    GL.glBegin(1);
/* 1716:     */    
/* 1717:1717 */    Color1.bind();
/* 1718:1718 */    GL.glVertex2f(x1, y1);
/* 1719:     */    
/* 1720:1720 */    Color2.bind();
/* 1721:1721 */    GL.glVertex2f(x2, y2);
/* 1722:     */    
/* 1723:1723 */    GL.glEnd();
/* 1724:     */    
/* 1725:1725 */    postdraw();
/* 1726:     */  }
/* 1727:     */  
/* 1733:     */  public void pushTransform()
/* 1734:     */  {
/* 1735:1735 */    predraw();
/* 1736:     */    
/* 1737:     */    FloatBuffer buffer;
/* 1738:1738 */    if (this.stackIndex >= this.stack.size()) {
/* 1739:1739 */      FloatBuffer buffer = BufferUtils.createFloatBuffer(18);
/* 1740:1740 */      this.stack.add(buffer);
/* 1741:     */    } else {
/* 1742:1742 */      buffer = (FloatBuffer)this.stack.get(this.stackIndex);
/* 1743:     */    }
/* 1744:     */    
/* 1745:1745 */    GL.glGetFloat(2982, buffer);
/* 1746:1746 */    buffer.put(16, this.sx);
/* 1747:1747 */    buffer.put(17, this.sy);
/* 1748:1748 */    this.stackIndex += 1;
/* 1749:     */    
/* 1750:1750 */    postdraw();
/* 1751:     */  }
/* 1752:     */  
/* 1756:     */  public void popTransform()
/* 1757:     */  {
/* 1758:1758 */    if (this.stackIndex == 0) {
/* 1759:1759 */      throw new RuntimeException("Attempt to pop a transform that hasn't be pushed");
/* 1760:     */    }
/* 1761:     */    
/* 1762:1762 */    predraw();
/* 1763:     */    
/* 1764:1764 */    this.stackIndex -= 1;
/* 1765:1765 */    FloatBuffer oldBuffer = (FloatBuffer)this.stack.get(this.stackIndex);
/* 1766:1766 */    GL.glLoadMatrix(oldBuffer);
/* 1767:1767 */    this.sx = oldBuffer.get(16);
/* 1768:1768 */    this.sy = oldBuffer.get(17);
/* 1769:     */    
/* 1770:1770 */    postdraw();
/* 1771:     */  }
/* 1772:     */  
/* 1773:     */  public void destroy() {}
/* 1774:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.Graphics
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */