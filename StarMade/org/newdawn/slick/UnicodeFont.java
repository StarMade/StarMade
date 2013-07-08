/*   1:    */package org.newdawn.slick;
/*   2:    */
/*   3:    */import java.awt.FontFormatException;
/*   4:    */import java.awt.FontMetrics;
/*   5:    */import java.awt.Graphics2D;
/*   6:    */import java.awt.Rectangle;
/*   7:    */import java.awt.Shape;
/*   8:    */import java.awt.font.GlyphVector;
/*   9:    */import java.awt.font.TextAttribute;
/*  10:    */import java.io.IOException;
/*  11:    */import java.io.PrintStream;
/*  12:    */import java.lang.reflect.Field;
/*  13:    */import java.lang.reflect.Method;
/*  14:    */import java.util.ArrayList;
/*  15:    */import java.util.Collections;
/*  16:    */import java.util.Comparator;
/*  17:    */import java.util.Iterator;
/*  18:    */import java.util.LinkedHashMap;
/*  19:    */import java.util.List;
/*  20:    */import java.util.Map;
/*  21:    */import java.util.Map.Entry;
/*  22:    */import org.newdawn.slick.font.Glyph;
/*  23:    */import org.newdawn.slick.font.GlyphPage;
/*  24:    */import org.newdawn.slick.font.HieroSettings;
/*  25:    */import org.newdawn.slick.opengl.Texture;
/*  26:    */import org.newdawn.slick.opengl.TextureImpl;
/*  27:    */import org.newdawn.slick.opengl.renderer.Renderer;
/*  28:    */import org.newdawn.slick.opengl.renderer.SGL;
/*  29:    */import org.newdawn.slick.util.ResourceLoader;
/*  30:    */
/*  40:    */public class UnicodeFont
/*  41:    */  implements Font
/*  42:    */{
/*  43:    */  private static final int DISPLAY_LIST_CACHE_SIZE = 600;
/*  44:    */  private static final int MAX_GLYPH_CODE = 1114111;
/*  45:    */  private static final int PAGE_SIZE = 512;
/*  46:    */  private static final int PAGES = 2175;
/*  47: 47 */  private static final SGL GL = ;
/*  48:    */  
/*  49: 49 */  private static final DisplayList EMPTY_DISPLAY_LIST = new DisplayList();
/*  50:    */  
/*  52: 52 */  public static boolean info = false;
/*  53:    */  
/*  58:    */  private static java.awt.Font createFont(String ttfFileRef)
/*  59:    */    throws SlickException
/*  60:    */  {
/*  61:    */    try
/*  62:    */    {
/*  63: 63 */      return java.awt.Font.createFont(0, ResourceLoader.getResourceAsStream(ttfFileRef));
/*  64:    */    } catch (FontFormatException ex) {
/*  65: 65 */      throw new SlickException("Invalid font: " + ttfFileRef, ex);
/*  66:    */    } catch (IOException ex) {
/*  67: 67 */      throw new SlickException("Error reading font: " + ttfFileRef, ex);
/*  68:    */    }
/*  69:    */  }
/*  70:    */  
/*  74: 74 */  private static final Comparator heightComparator = new Comparator() {
/*  75:    */    public int compare(Object o1, Object o2) {
/*  76: 76 */      return ((Glyph)o1).getHeight() - ((Glyph)o2).getHeight();
/*  77:    */    }
/*  78:    */  };
/*  79:    */  
/*  81:    */  private java.awt.Font font;
/*  82:    */  
/*  83:    */  private String ttfFileRef;
/*  84:    */  
/*  85:    */  private int ascent;
/*  86:    */  
/*  87:    */  private int descent;
/*  88:    */  
/*  89:    */  private int leading;
/*  90:    */  
/*  91:    */  private int spaceWidth;
/*  92:    */  
/*  93: 93 */  private final Glyph[][] glyphs = new Glyph[2175][];
/*  94:    */  
/*  95: 95 */  private final List glyphPages = new ArrayList();
/*  96:    */  
/*  97: 97 */  private final List queuedGlyphs = new ArrayList(256);
/*  98:    */  
/*  99: 99 */  private final List effects = new ArrayList();
/* 100:    */  
/* 102:    */  private int paddingTop;
/* 103:    */  
/* 105:    */  private int paddingLeft;
/* 106:    */  
/* 107:    */  private int paddingBottom;
/* 108:    */  
/* 109:    */  private int paddingRight;
/* 110:    */  
/* 111:    */  private int paddingAdvanceX;
/* 112:    */  
/* 113:    */  private int paddingAdvanceY;
/* 114:    */  
/* 115:    */  private Glyph missingGlyph;
/* 116:    */  
/* 117:117 */  private int glyphPageWidth = 512;
/* 118:    */  
/* 119:119 */  private int glyphPageHeight = 512;
/* 120:    */  
/* 122:122 */  private boolean displayListCaching = true;
/* 123:    */  
/* 124:124 */  private int baseDisplayListID = -1;
/* 125:    */  
/* 127:    */  private int eldestDisplayListID;
/* 128:    */  
/* 129:    */  private DisplayList eldestDisplayList;
/* 130:    */  
/* 131:131 */  private final LinkedHashMap displayLists = new LinkedHashMap(600, 1.0F, true) {
/* 132:    */    protected boolean removeEldestEntry(Map.Entry eldest) {
/* 133:133 */      UnicodeFont.DisplayList displayList = (UnicodeFont.DisplayList)eldest.getValue();
/* 134:134 */      if (displayList != null) UnicodeFont.this.eldestDisplayListID = displayList.id;
/* 135:135 */      return size() > 600;
/* 136:    */    }
/* 137:    */  };
/* 138:    */  
/* 144:    */  public UnicodeFont(String ttfFileRef, String hieroFileRef)
/* 145:    */    throws SlickException
/* 146:    */  {
/* 147:147 */    this(ttfFileRef, new HieroSettings(hieroFileRef));
/* 148:    */  }
/* 149:    */  
/* 155:    */  public UnicodeFont(String ttfFileRef, HieroSettings settings)
/* 156:    */    throws SlickException
/* 157:    */  {
/* 158:158 */    this.ttfFileRef = ttfFileRef;
/* 159:159 */    java.awt.Font font = createFont(ttfFileRef);
/* 160:160 */    initializeFont(font, settings.getFontSize(), settings.isBold(), settings.isItalic());
/* 161:161 */    loadSettings(settings);
/* 162:    */  }
/* 163:    */  
/* 171:    */  public UnicodeFont(String ttfFileRef, int size, boolean bold, boolean italic)
/* 172:    */    throws SlickException
/* 173:    */  {
/* 174:174 */    this.ttfFileRef = ttfFileRef;
/* 175:175 */    initializeFont(createFont(ttfFileRef), size, bold, italic);
/* 176:    */  }
/* 177:    */  
/* 183:    */  public UnicodeFont(java.awt.Font font, String hieroFileRef)
/* 184:    */    throws SlickException
/* 185:    */  {
/* 186:186 */    this(font, new HieroSettings(hieroFileRef));
/* 187:    */  }
/* 188:    */  
/* 194:    */  public UnicodeFont(java.awt.Font font, HieroSettings settings)
/* 195:    */  {
/* 196:196 */    initializeFont(font, settings.getFontSize(), settings.isBold(), settings.isItalic());
/* 197:197 */    loadSettings(settings);
/* 198:    */  }
/* 199:    */  
/* 204:    */  public UnicodeFont(java.awt.Font font)
/* 205:    */  {
/* 206:206 */    initializeFont(font, font.getSize(), font.isBold(), font.isItalic());
/* 207:    */  }
/* 208:    */  
/* 216:    */  public UnicodeFont(java.awt.Font font, int size, boolean bold, boolean italic)
/* 217:    */  {
/* 218:218 */    initializeFont(font, size, bold, italic);
/* 219:    */  }
/* 220:    */  
/* 228:    */  private void initializeFont(java.awt.Font baseFont, int size, boolean bold, boolean italic)
/* 229:    */  {
/* 230:230 */    Map attributes = baseFont.getAttributes();
/* 231:231 */    attributes.put(TextAttribute.SIZE, new Float(size));
/* 232:232 */    attributes.put(TextAttribute.WEIGHT, bold ? TextAttribute.WEIGHT_BOLD : TextAttribute.WEIGHT_REGULAR);
/* 233:233 */    attributes.put(TextAttribute.POSTURE, italic ? TextAttribute.POSTURE_OBLIQUE : TextAttribute.POSTURE_REGULAR);
/* 234:    */    try {
/* 235:235 */      attributes.put(TextAttribute.class.getDeclaredField("KERNING").get(null), TextAttribute.class.getDeclaredField("KERNING_ON").get(null));
/* 236:    */    }
/* 237:    */    catch (Exception ignored) {}
/* 238:    */    
/* 239:239 */    this.font = baseFont.deriveFont(attributes);
/* 240:    */    
/* 241:241 */    FontMetrics metrics = GlyphPage.getScratchGraphics().getFontMetrics(this.font);
/* 242:242 */    this.ascent = metrics.getAscent();
/* 243:243 */    this.descent = metrics.getDescent();
/* 244:244 */    this.leading = metrics.getLeading();
/* 245:    */    
/* 247:247 */    char[] chars = " ".toCharArray();
/* 248:248 */    GlyphVector vector = this.font.layoutGlyphVector(GlyphPage.renderContext, chars, 0, chars.length, 0);
/* 249:249 */    this.spaceWidth = vector.getGlyphLogicalBounds(0).getBounds().width;
/* 250:    */  }
/* 251:    */  
/* 256:    */  private void loadSettings(HieroSettings settings)
/* 257:    */  {
/* 258:258 */    this.paddingTop = settings.getPaddingTop();
/* 259:259 */    this.paddingLeft = settings.getPaddingLeft();
/* 260:260 */    this.paddingBottom = settings.getPaddingBottom();
/* 261:261 */    this.paddingRight = settings.getPaddingRight();
/* 262:262 */    this.paddingAdvanceX = settings.getPaddingAdvanceX();
/* 263:263 */    this.paddingAdvanceY = settings.getPaddingAdvanceY();
/* 264:264 */    this.glyphPageWidth = settings.getGlyphPageWidth();
/* 265:265 */    this.glyphPageHeight = settings.getGlyphPageHeight();
/* 266:266 */    this.effects.addAll(settings.getEffects());
/* 267:    */  }
/* 268:    */  
/* 278:    */  public void addGlyphs(int startCodePoint, int endCodePoint)
/* 279:    */  {
/* 280:280 */    for (int codePoint = startCodePoint; codePoint <= endCodePoint; codePoint++) {
/* 281:281 */      addGlyphs(new String(Character.toChars(codePoint)));
/* 282:    */    }
/* 283:    */  }
/* 284:    */  
/* 289:    */  public void addGlyphs(String text)
/* 290:    */  {
/* 291:291 */    if (text == null) { throw new IllegalArgumentException("text cannot be null.");
/* 292:    */    }
/* 293:293 */    char[] chars = text.toCharArray();
/* 294:294 */    GlyphVector vector = this.font.layoutGlyphVector(GlyphPage.renderContext, chars, 0, chars.length, 0);
/* 295:295 */    int i = 0; for (int n = vector.getNumGlyphs(); i < n; i++) {
/* 296:296 */      int codePoint = text.codePointAt(vector.getGlyphCharIndex(i));
/* 297:297 */      Rectangle bounds = getGlyphBounds(vector, i, codePoint);
/* 298:298 */      getGlyph(vector.getGlyphCode(i), codePoint, bounds, vector, i);
/* 299:    */    }
/* 300:    */  }
/* 301:    */  
/* 305:    */  public void addAsciiGlyphs()
/* 306:    */  {
/* 307:307 */    addGlyphs(32, 255);
/* 308:    */  }
/* 309:    */  
/* 313:    */  public void addNeheGlyphs()
/* 314:    */  {
/* 315:315 */    addGlyphs(32, 128);
/* 316:    */  }
/* 317:    */  
/* 324:    */  public boolean loadGlyphs()
/* 325:    */    throws SlickException
/* 326:    */  {
/* 327:327 */    return loadGlyphs(-1);
/* 328:    */  }
/* 329:    */  
/* 336:    */  public boolean loadGlyphs(int maxGlyphsToLoad)
/* 337:    */    throws SlickException
/* 338:    */  {
/* 339:339 */    if (this.queuedGlyphs.isEmpty()) { return false;
/* 340:    */    }
/* 341:341 */    if (this.effects.isEmpty()) {
/* 342:342 */      throw new IllegalStateException("The UnicodeFont must have at least one effect before any glyphs can be loaded.");
/* 343:    */    }
/* 344:344 */    for (Iterator iter = this.queuedGlyphs.iterator(); iter.hasNext();) {
/* 345:345 */      Glyph glyph = (Glyph)iter.next();
/* 346:346 */      int codePoint = glyph.getCodePoint();
/* 347:    */      
/* 349:349 */      if ((glyph.getWidth() == 0) || (codePoint == 32)) {
/* 350:350 */        iter.remove();
/* 354:    */      }
/* 355:355 */      else if (glyph.isMissing()) {
/* 356:356 */        if (this.missingGlyph != null) {
/* 357:357 */          if (glyph != this.missingGlyph) iter.remove();
/* 358:    */        }
/* 359:    */        else {
/* 360:360 */          this.missingGlyph = glyph;
/* 361:    */        }
/* 362:    */      }
/* 363:    */    }
/* 364:364 */    Collections.sort(this.queuedGlyphs, heightComparator);
/* 365:    */    
/* 367:367 */    for (Iterator iter = this.glyphPages.iterator(); iter.hasNext();) {
/* 368:368 */      GlyphPage glyphPage = (GlyphPage)iter.next();
/* 369:369 */      maxGlyphsToLoad -= glyphPage.loadGlyphs(this.queuedGlyphs, maxGlyphsToLoad);
/* 370:370 */      if ((maxGlyphsToLoad == 0) || (this.queuedGlyphs.isEmpty())) {
/* 371:371 */        return true;
/* 372:    */      }
/* 373:    */    }
/* 374:    */    
/* 375:375 */    while (!this.queuedGlyphs.isEmpty()) {
/* 376:376 */      GlyphPage glyphPage = new GlyphPage(this, this.glyphPageWidth, this.glyphPageHeight);
/* 377:377 */      this.glyphPages.add(glyphPage);
/* 378:378 */      maxGlyphsToLoad -= glyphPage.loadGlyphs(this.queuedGlyphs, maxGlyphsToLoad);
/* 379:379 */      if (maxGlyphsToLoad == 0) { return true;
/* 380:    */      }
/* 381:    */    }
/* 382:382 */    return true;
/* 383:    */  }
/* 384:    */  
/* 387:    */  public void clearGlyphs()
/* 388:    */  {
/* 389:389 */    for (int i = 0; i < 2175; i++) {
/* 390:390 */      this.glyphs[i] = null;
/* 391:    */    }
/* 392:392 */    for (Iterator iter = this.glyphPages.iterator(); iter.hasNext();) {
/* 393:393 */      GlyphPage page = (GlyphPage)iter.next();
/* 394:    */      try {
/* 395:395 */        page.getImage().destroy();
/* 396:    */      }
/* 397:    */      catch (SlickException ignored) {}
/* 398:    */    }
/* 399:399 */    this.glyphPages.clear();
/* 400:    */    
/* 401:401 */    if (this.baseDisplayListID != -1) {
/* 402:402 */      GL.glDeleteLists(this.baseDisplayListID, this.displayLists.size());
/* 403:403 */      this.baseDisplayListID = -1;
/* 404:    */    }
/* 405:    */    
/* 406:406 */    this.queuedGlyphs.clear();
/* 407:407 */    this.missingGlyph = null;
/* 408:    */  }
/* 409:    */  
/* 414:    */  public void destroy()
/* 415:    */  {
/* 416:416 */    clearGlyphs();
/* 417:    */  }
/* 418:    */  
/* 430:    */  public DisplayList drawDisplayList(float x, float y, String text, Color color, int startIndex, int endIndex)
/* 431:    */  {
/* 432:432 */    if (text == null) throw new IllegalArgumentException("text cannot be null.");
/* 433:433 */    if (text.length() == 0) return EMPTY_DISPLAY_LIST;
/* 434:434 */    if (color == null) { throw new IllegalArgumentException("color cannot be null.");
/* 435:    */    }
/* 436:436 */    x -= this.paddingLeft;
/* 437:437 */    y -= this.paddingTop;
/* 438:    */    
/* 439:439 */    String displayListKey = text.substring(startIndex, endIndex);
/* 440:    */    
/* 441:441 */    color.bind();
/* 442:442 */    TextureImpl.bindNone();
/* 443:    */    
/* 444:444 */    DisplayList displayList = null;
/* 445:445 */    if ((this.displayListCaching) && (this.queuedGlyphs.isEmpty())) {
/* 446:446 */      if (this.baseDisplayListID == -1) {
/* 447:447 */        this.baseDisplayListID = GL.glGenLists(600);
/* 448:448 */        if (this.baseDisplayListID == 0) {
/* 449:449 */          this.baseDisplayListID = -1;
/* 450:450 */          this.displayListCaching = false;
/* 451:451 */          return new DisplayList();
/* 452:    */        }
/* 453:    */      }
/* 454:    */      
/* 455:455 */      displayList = (DisplayList)this.displayLists.get(displayListKey);
/* 456:456 */      if (displayList != null) {
/* 457:457 */        if (displayList.invalid) {
/* 458:458 */          displayList.invalid = false;
/* 459:    */        } else {
/* 460:460 */          GL.glTranslatef(x, y, 0.0F);
/* 461:461 */          GL.glCallList(displayList.id);
/* 462:462 */          GL.glTranslatef(-x, -y, 0.0F);
/* 463:463 */          return displayList;
/* 464:    */        }
/* 465:465 */      } else if (displayList == null)
/* 466:    */      {
/* 467:467 */        displayList = new DisplayList();
/* 468:468 */        int displayListCount = this.displayLists.size();
/* 469:469 */        this.displayLists.put(displayListKey, displayList);
/* 470:470 */        if (displayListCount < 600) {
/* 471:471 */          displayList.id = (this.baseDisplayListID + displayListCount);
/* 472:    */        } else
/* 473:473 */          displayList.id = this.eldestDisplayListID;
/* 474:    */      }
/* 475:475 */      this.displayLists.put(displayListKey, displayList);
/* 476:    */      
/* 478:478 */      if (info) {
/* 479:479 */        System.err.println("[UNICODE] NEED TO COMPILE NEW DISPLAY LIST: " + this.font.getFontName() + "; size " + this.font.getSize() + "; bold: " + this.font.isBold() + "; displayListFill: " + this.displayLists.size() + ": " + text);
/* 480:    */      }
/* 481:    */    } else {
/* 482:482 */      if (info) {
/* 483:483 */        System.err.println("[UNICODE] NOT CACHED: " + this.font.getFontName() + "; size " + this.font.getSize() + "; bold: " + this.font.isBold() + "; chaching: " + this.displayListCaching + "; queuedGlyphs: " + this.queuedGlyphs.size() + ": " + text);
/* 484:    */      }
/* 485:485 */      if (!this.queuedGlyphs.isEmpty()) {
/* 486:    */        try {
/* 487:487 */          loadGlyphs(-1);
/* 488:    */        } catch (SlickException e) {
/* 489:489 */          e.printStackTrace();
/* 490:    */        }
/* 491:491 */        if (info) {
/* 492:492 */          System.err.println("[UNICODE] LOADED GLYPHS: " + this.font.getFontName() + "; size " + this.font.getSize() + "; bold: " + this.font.isBold() + "; chaching: " + this.displayListCaching + "; queuedGlyphs: " + this.queuedGlyphs.size() + ": " + text);
/* 493:    */        }
/* 494:    */      }
/* 495:    */    }
/* 496:    */    
/* 497:497 */    GL.glTranslatef(x, y, 0.0F);
/* 498:    */    
/* 499:499 */    if (displayList != null) { GL.glNewList(displayList.id, 4865);
/* 500:    */    }
/* 501:501 */    char[] chars = text.substring(0, endIndex).toCharArray();
/* 502:502 */    GlyphVector vector = this.font.layoutGlyphVector(GlyphPage.renderContext, chars, 0, chars.length, 0);
/* 503:    */    
/* 504:504 */    int maxWidth = 0;int totalHeight = 0;int lines = 0;
/* 505:505 */    int extraX = 0;int extraY = this.ascent;
/* 506:506 */    boolean startNewLine = false;
/* 507:507 */    Texture lastBind = null;
/* 508:508 */    int glyphIndex = 0; for (int n = vector.getNumGlyphs(); glyphIndex < n; glyphIndex++) {
/* 509:509 */      int charIndex = vector.getGlyphCharIndex(glyphIndex);
/* 510:510 */      if (charIndex >= startIndex) {
/* 511:511 */        if (charIndex > endIndex)
/* 512:    */          break;
/* 513:513 */        int codePoint = text.codePointAt(charIndex);
/* 514:    */        
/* 515:515 */        Rectangle bounds = getGlyphBounds(vector, glyphIndex, codePoint);
/* 516:516 */        Glyph glyph = getGlyph(vector.getGlyphCode(glyphIndex), codePoint, bounds, vector, glyphIndex);
/* 517:    */        
/* 518:518 */        if ((startNewLine) && (codePoint != 10)) {
/* 519:519 */          extraX = -bounds.x;
/* 520:520 */          startNewLine = false;
/* 521:    */        }
/* 522:    */        
/* 523:523 */        Image image = glyph.getImage();
/* 524:524 */        if ((image == null) && (this.missingGlyph != null) && (glyph.isMissing())) image = this.missingGlyph.getImage();
/* 525:525 */        if (image != null)
/* 526:    */        {
/* 527:527 */          Texture texture = image.getTexture();
/* 528:528 */          if ((lastBind != null) && (lastBind != texture)) {
/* 529:529 */            GL.glEnd();
/* 530:530 */            lastBind = null;
/* 531:    */          }
/* 532:532 */          if (lastBind == null) {
/* 533:533 */            texture.bind();
/* 534:534 */            GL.glBegin(7);
/* 535:535 */            lastBind = texture;
/* 536:    */          }
/* 537:537 */          image.drawEmbedded(bounds.x + extraX, bounds.y + extraY, image.getWidth(), image.getHeight());
/* 538:    */        }
/* 539:    */        
/* 540:540 */        if (glyphIndex >= 0) extraX += this.paddingRight + this.paddingLeft + this.paddingAdvanceX;
/* 541:541 */        maxWidth = Math.max(maxWidth, bounds.x + extraX + bounds.width);
/* 542:542 */        totalHeight = Math.max(totalHeight, this.ascent + bounds.y + bounds.height);
/* 543:    */        
/* 544:544 */        if (codePoint == 10) {
/* 545:545 */          startNewLine = true;
/* 546:546 */          extraY += getLineHeight();
/* 547:547 */          lines++;
/* 548:548 */          totalHeight = 0;
/* 549:    */        }
/* 550:    */      } }
/* 551:551 */    if (lastBind != null) { GL.glEnd();
/* 552:    */    }
/* 553:553 */    if (displayList != null) {
/* 554:554 */      GL.glEndList();
/* 555:    */      
/* 556:556 */      if (!this.queuedGlyphs.isEmpty()) { displayList.invalid = true;
/* 557:    */      }
/* 558:    */    }
/* 559:559 */    GL.glTranslatef(-x, -y, 0.0F);
/* 560:    */    
/* 561:561 */    if (displayList == null) displayList = new DisplayList();
/* 562:562 */    displayList.width = ((short)maxWidth);
/* 563:563 */    displayList.height = ((short)(lines * getLineHeight() + totalHeight));
/* 564:564 */    return displayList;
/* 565:    */  }
/* 566:    */  
/* 567:    */  public void drawString(float x, float y, String text, Color color, int startIndex, int endIndex) {
/* 568:568 */    drawDisplayList(x, y, text, color, startIndex, endIndex);
/* 569:    */  }
/* 570:    */  
/* 571:    */  public void drawString(float x, float y, String text) {
/* 572:572 */    drawString(x, y, text, Color.white);
/* 573:    */  }
/* 574:    */  
/* 575:    */  public void drawString(float x, float y, String text, Color col) {
/* 576:576 */    drawString(x, y, text, col, 0, text.length());
/* 577:    */  }
/* 578:    */  
/* 589:    */  private Glyph getGlyph(int glyphCode, int codePoint, Rectangle bounds, GlyphVector vector, int index)
/* 590:    */  {
/* 591:591 */    if ((glyphCode < 0) || (glyphCode >= 1114111))
/* 592:    */    {
/* 593:593 */      new Glyph(codePoint, bounds, vector, index, this) {
/* 594:    */        public boolean isMissing() {
/* 595:595 */          return true;
/* 596:    */        }
/* 597:    */      };
/* 598:    */    }
/* 599:599 */    int pageIndex = glyphCode / 512;
/* 600:600 */    int glyphIndex = glyphCode & 0x1FF;
/* 601:601 */    Glyph glyph = null;
/* 602:602 */    Glyph[] page = this.glyphs[pageIndex];
/* 603:603 */    if (page != null) {
/* 604:604 */      glyph = page[glyphIndex];
/* 605:605 */      if (glyph != null) return glyph;
/* 606:    */    } else {
/* 607:607 */      page = this.glyphs[pageIndex] =  = new Glyph[512];
/* 608:    */    }
/* 609:609 */    glyph = page[glyphIndex] =  = new Glyph(codePoint, bounds, vector, index, this);
/* 610:610 */    this.queuedGlyphs.add(glyph);
/* 611:611 */    return glyph;
/* 612:    */  }
/* 613:    */  
/* 620:    */  private Rectangle getGlyphBounds(GlyphVector vector, int index, int codePoint)
/* 621:    */  {
/* 622:622 */    Rectangle bounds = vector.getGlyphPixelBounds(index, GlyphPage.renderContext, 0.0F, 0.0F);
/* 623:623 */    if (codePoint == 32) bounds.width = this.spaceWidth;
/* 624:624 */    return bounds;
/* 625:    */  }
/* 626:    */  
/* 629:    */  public int getSpaceWidth()
/* 630:    */  {
/* 631:631 */    return this.spaceWidth;
/* 632:    */  }
/* 633:    */  
/* 636:    */  public int getWidth(String text)
/* 637:    */  {
/* 638:638 */    if (text == null) throw new IllegalArgumentException("text cannot be null.");
/* 639:639 */    if (text.length() == 0) { return 0;
/* 640:    */    }
/* 641:641 */    if (this.displayListCaching) {
/* 642:642 */      DisplayList displayList = (DisplayList)this.displayLists.get(text);
/* 643:643 */      if (displayList != null) { return displayList.width;
/* 644:    */      }
/* 645:    */    }
/* 646:    */    
/* 647:647 */    char[] chars = text.toCharArray();
/* 648:648 */    GlyphVector vector = this.font.layoutGlyphVector(GlyphPage.renderContext, chars, 0, chars.length, 0);
/* 649:    */    
/* 650:650 */    int width = 0;
/* 651:651 */    int extraX = 0;
/* 652:652 */    boolean startNewLine = false;
/* 653:653 */    int glyphIndex = 0; for (int n = vector.getNumGlyphs(); glyphIndex < n; glyphIndex++) {
/* 654:654 */      int charIndex = vector.getGlyphCharIndex(glyphIndex);
/* 655:655 */      int codePoint = text.codePointAt(charIndex);
/* 656:656 */      Rectangle bounds = getGlyphBounds(vector, glyphIndex, codePoint);
/* 657:    */      
/* 658:658 */      if ((startNewLine) && (codePoint != 10)) { extraX = -bounds.x;
/* 659:    */      }
/* 660:660 */      if (glyphIndex > 0) extraX += this.paddingLeft + this.paddingRight + this.paddingAdvanceX;
/* 661:661 */      width = Math.max(width, bounds.x + extraX + bounds.width);
/* 662:    */      
/* 663:663 */      if (codePoint == 10) { startNewLine = true;
/* 664:    */      }
/* 665:    */    }
/* 666:666 */    return width;
/* 667:    */  }
/* 668:    */  
/* 671:    */  public int getHeight(String text)
/* 672:    */  {
/* 673:673 */    if (text == null) throw new IllegalArgumentException("text cannot be null.");
/* 674:674 */    if (text.length() == 0) { return 0;
/* 675:    */    }
/* 676:676 */    if (this.displayListCaching) {
/* 677:677 */      DisplayList displayList = (DisplayList)this.displayLists.get(text);
/* 678:678 */      if (displayList != null) { return displayList.height;
/* 679:    */      }
/* 680:    */    }
/* 681:681 */    char[] chars = text.toCharArray();
/* 682:682 */    GlyphVector vector = this.font.layoutGlyphVector(GlyphPage.renderContext, chars, 0, chars.length, 0);
/* 683:    */    
/* 684:684 */    int lines = 0;int height = 0;
/* 685:685 */    int i = 0; for (int n = vector.getNumGlyphs(); i < n; i++) {
/* 686:686 */      int charIndex = vector.getGlyphCharIndex(i);
/* 687:687 */      int codePoint = text.codePointAt(charIndex);
/* 688:688 */      if (codePoint != 32) {
/* 689:689 */        Rectangle bounds = getGlyphBounds(vector, i, codePoint);
/* 690:    */        
/* 691:691 */        height = Math.max(height, this.ascent + bounds.y + bounds.height);
/* 692:    */        
/* 693:693 */        if (codePoint == 10) {
/* 694:694 */          lines++;
/* 695:695 */          height = 0;
/* 696:    */        }
/* 697:    */      } }
/* 698:698 */    return lines * getLineHeight() + height;
/* 699:    */  }
/* 700:    */  
/* 707:    */  public int getYOffset(String text)
/* 708:    */  {
/* 709:709 */    if (text == null) { throw new IllegalArgumentException("text cannot be null.");
/* 710:    */    }
/* 711:711 */    DisplayList displayList = null;
/* 712:712 */    if (this.displayListCaching) {
/* 713:713 */      displayList = (DisplayList)this.displayLists.get(text);
/* 714:714 */      if ((displayList != null) && (displayList.yOffset != null)) { return displayList.yOffset.intValue();
/* 715:    */      }
/* 716:    */    }
/* 717:717 */    int index = text.indexOf(10);
/* 718:718 */    if (index != -1) text = text.substring(0, index);
/* 719:719 */    char[] chars = text.toCharArray();
/* 720:720 */    GlyphVector vector = this.font.layoutGlyphVector(GlyphPage.renderContext, chars, 0, chars.length, 0);
/* 721:721 */    int yOffset = this.ascent + vector.getPixelBounds(null, 0.0F, 0.0F).y;
/* 722:    */    
/* 723:723 */    if (displayList != null) { displayList.yOffset = new Short((short)yOffset);
/* 724:    */    }
/* 725:725 */    return yOffset;
/* 726:    */  }
/* 727:    */  
/* 732:    */  public java.awt.Font getFont()
/* 733:    */  {
/* 734:734 */    return this.font;
/* 735:    */  }
/* 736:    */  
/* 741:    */  public int getPaddingTop()
/* 742:    */  {
/* 743:743 */    return this.paddingTop;
/* 744:    */  }
/* 745:    */  
/* 750:    */  public void setPaddingTop(int paddingTop)
/* 751:    */  {
/* 752:752 */    this.paddingTop = paddingTop;
/* 753:    */  }
/* 754:    */  
/* 759:    */  public int getPaddingLeft()
/* 760:    */  {
/* 761:761 */    return this.paddingLeft;
/* 762:    */  }
/* 763:    */  
/* 768:    */  public void setPaddingLeft(int paddingLeft)
/* 769:    */  {
/* 770:770 */    this.paddingLeft = paddingLeft;
/* 771:    */  }
/* 772:    */  
/* 777:    */  public int getPaddingBottom()
/* 778:    */  {
/* 779:779 */    return this.paddingBottom;
/* 780:    */  }
/* 781:    */  
/* 786:    */  public void setPaddingBottom(int paddingBottom)
/* 787:    */  {
/* 788:788 */    this.paddingBottom = paddingBottom;
/* 789:    */  }
/* 790:    */  
/* 795:    */  public int getPaddingRight()
/* 796:    */  {
/* 797:797 */    return this.paddingRight;
/* 798:    */  }
/* 799:    */  
/* 804:    */  public void setPaddingRight(int paddingRight)
/* 805:    */  {
/* 806:806 */    this.paddingRight = paddingRight;
/* 807:    */  }
/* 808:    */  
/* 813:    */  public int getPaddingAdvanceX()
/* 814:    */  {
/* 815:815 */    return this.paddingAdvanceX;
/* 816:    */  }
/* 817:    */  
/* 823:    */  public void setPaddingAdvanceX(int paddingAdvanceX)
/* 824:    */  {
/* 825:825 */    this.paddingAdvanceX = paddingAdvanceX;
/* 826:    */  }
/* 827:    */  
/* 832:    */  public int getPaddingAdvanceY()
/* 833:    */  {
/* 834:834 */    return this.paddingAdvanceY;
/* 835:    */  }
/* 836:    */  
/* 842:    */  public void setPaddingAdvanceY(int paddingAdvanceY)
/* 843:    */  {
/* 844:844 */    this.paddingAdvanceY = paddingAdvanceY;
/* 845:    */  }
/* 846:    */  
/* 850:    */  public int getLineHeight()
/* 851:    */  {
/* 852:852 */    return this.descent + this.ascent + this.leading + this.paddingTop + this.paddingBottom + this.paddingAdvanceY;
/* 853:    */  }
/* 854:    */  
/* 859:    */  public int getAscent()
/* 860:    */  {
/* 861:861 */    return this.ascent;
/* 862:    */  }
/* 863:    */  
/* 869:    */  public int getDescent()
/* 870:    */  {
/* 871:871 */    return this.descent;
/* 872:    */  }
/* 873:    */  
/* 878:    */  public int getLeading()
/* 879:    */  {
/* 880:880 */    return this.leading;
/* 881:    */  }
/* 882:    */  
/* 887:    */  public int getGlyphPageWidth()
/* 888:    */  {
/* 889:889 */    return this.glyphPageWidth;
/* 890:    */  }
/* 891:    */  
/* 896:    */  public void setGlyphPageWidth(int glyphPageWidth)
/* 897:    */  {
/* 898:898 */    this.glyphPageWidth = glyphPageWidth;
/* 899:    */  }
/* 900:    */  
/* 905:    */  public int getGlyphPageHeight()
/* 906:    */  {
/* 907:907 */    return this.glyphPageHeight;
/* 908:    */  }
/* 909:    */  
/* 914:    */  public void setGlyphPageHeight(int glyphPageHeight)
/* 915:    */  {
/* 916:916 */    this.glyphPageHeight = glyphPageHeight;
/* 917:    */  }
/* 918:    */  
/* 923:    */  public List getGlyphPages()
/* 924:    */  {
/* 925:925 */    return this.glyphPages;
/* 926:    */  }
/* 927:    */  
/* 933:    */  public List getEffects()
/* 934:    */  {
/* 935:935 */    return this.effects;
/* 936:    */  }
/* 937:    */  
/* 943:    */  public boolean isCaching()
/* 944:    */  {
/* 945:945 */    return this.displayListCaching;
/* 946:    */  }
/* 947:    */  
/* 953:    */  public void setDisplayListCaching(boolean displayListCaching)
/* 954:    */  {
/* 955:955 */    this.displayListCaching = displayListCaching;
/* 956:    */  }
/* 957:    */  
/* 963:    */  public String getFontFile()
/* 964:    */  {
/* 965:965 */    if (this.ttfFileRef == null)
/* 966:    */    {
/* 967:    */      try {
/* 968:968 */        Object font2D = Class.forName("sun.font.FontManager").getDeclaredMethod("getFont2D", new Class[] { java.awt.Font.class }).invoke(null, new Object[] { this.font });
/* 969:    */        
/* 970:970 */        Field platNameField = Class.forName("sun.font.PhysicalFont").getDeclaredField("platName");
/* 971:971 */        platNameField.setAccessible(true);
/* 972:972 */        this.ttfFileRef = ((String)platNameField.get(font2D));
/* 973:    */      }
/* 974:    */      catch (Throwable ignored) {}
/* 975:975 */      if (this.ttfFileRef == null) this.ttfFileRef = "";
/* 976:    */    }
/* 977:977 */    if (this.ttfFileRef.length() == 0) return null;
/* 978:978 */    return this.ttfFileRef;
/* 979:    */  }
/* 980:    */  
/* 981:    */  public static class DisplayList
/* 982:    */  {
/* 983:    */    boolean invalid;
/* 984:    */    int id;
/* 985:    */    Short yOffset;
/* 986:    */    public short width;
/* 987:    */    public short height;
/* 988:    */    public Object userData;
/* 989:    */  }
/* 990:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.UnicodeFont
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */