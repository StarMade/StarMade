/*     */ package org.newdawn.slick;
/*     */ 
/*     */ import java.awt.FontFormatException;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Shape;
/*     */ import java.awt.font.GlyphVector;
/*     */ import java.awt.font.TextAttribute;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import org.newdawn.slick.font.Glyph;
/*     */ import org.newdawn.slick.font.GlyphPage;
/*     */ import org.newdawn.slick.font.HieroSettings;
/*     */ import org.newdawn.slick.opengl.Texture;
/*     */ import org.newdawn.slick.opengl.TextureImpl;
/*     */ import org.newdawn.slick.opengl.renderer.Renderer;
/*     */ import org.newdawn.slick.opengl.renderer.SGL;
/*     */ import org.newdawn.slick.util.ResourceLoader;
/*     */ 
/*     */ public class UnicodeFont
/*     */   implements Font
/*     */ {
/*     */   private static final int DISPLAY_LIST_CACHE_SIZE = 600;
/*     */   private static final int MAX_GLYPH_CODE = 1114111;
/*     */   private static final int PAGE_SIZE = 512;
/*     */   private static final int PAGES = 2175;
/*  47 */   private static final SGL GL = Renderer.get();
/*     */ 
/*  49 */   private static final DisplayList EMPTY_DISPLAY_LIST = new DisplayList();
/*     */ 
/*  52 */   public static boolean info = false;
/*     */ 
/*  74 */   private static final Comparator heightComparator = new Comparator() {
/*     */     public int compare(Object o1, Object o2) {
/*  76 */       return ((Glyph)o1).getHeight() - ((Glyph)o2).getHeight();
/*     */     }
/*  74 */   };
/*     */   private java.awt.Font font;
/*     */   private String ttfFileRef;
/*     */   private int ascent;
/*     */   private int descent;
/*     */   private int leading;
/*     */   private int spaceWidth;
/*  93 */   private final Glyph[][] glyphs = new Glyph[2175][];
/*     */ 
/*  95 */   private final List glyphPages = new ArrayList();
/*     */ 
/*  97 */   private final List queuedGlyphs = new ArrayList(256);
/*     */ 
/*  99 */   private final List effects = new ArrayList();
/*     */   private int paddingTop;
/*     */   private int paddingLeft;
/*     */   private int paddingBottom;
/*     */   private int paddingRight;
/*     */   private int paddingAdvanceX;
/*     */   private int paddingAdvanceY;
/*     */   private Glyph missingGlyph;
/* 117 */   private int glyphPageWidth = 512;
/*     */ 
/* 119 */   private int glyphPageHeight = 512;
/*     */ 
/* 122 */   private boolean displayListCaching = true;
/*     */ 
/* 124 */   private int baseDisplayListID = -1;
/*     */   private int eldestDisplayListID;
/*     */   private DisplayList eldestDisplayList;
/* 131 */   private final LinkedHashMap displayLists = new LinkedHashMap(600, 1.0F, true) {
/*     */     protected boolean removeEldestEntry(Map.Entry eldest) {
/* 133 */       UnicodeFont.DisplayList displayList = (UnicodeFont.DisplayList)eldest.getValue();
/* 134 */       if (displayList != null) UnicodeFont.this.eldestDisplayListID = displayList.id;
/* 135 */       return size() > 600;
/*     */     }
/* 131 */   };
/*     */ 
/*     */   private static java.awt.Font createFont(String ttfFileRef)
/*     */     throws SlickException
/*     */   {
/*     */     try
/*     */     {
/*  63 */       return java.awt.Font.createFont(0, ResourceLoader.getResourceAsStream(ttfFileRef));
/*     */     } catch (FontFormatException ex) {
/*  65 */       throw new SlickException("Invalid font: " + ttfFileRef, ex);
/*     */     } catch (IOException ex) {
/*  67 */       throw new SlickException("Error reading font: " + ttfFileRef, ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   public UnicodeFont(String ttfFileRef, String hieroFileRef)
/*     */     throws SlickException
/*     */   {
/* 147 */     this(ttfFileRef, new HieroSettings(hieroFileRef));
/*     */   }
/*     */ 
/*     */   public UnicodeFont(String ttfFileRef, HieroSettings settings)
/*     */     throws SlickException
/*     */   {
/* 158 */     this.ttfFileRef = ttfFileRef;
/* 159 */     java.awt.Font font = createFont(ttfFileRef);
/* 160 */     initializeFont(font, settings.getFontSize(), settings.isBold(), settings.isItalic());
/* 161 */     loadSettings(settings);
/*     */   }
/*     */ 
/*     */   public UnicodeFont(String ttfFileRef, int size, boolean bold, boolean italic)
/*     */     throws SlickException
/*     */   {
/* 174 */     this.ttfFileRef = ttfFileRef;
/* 175 */     initializeFont(createFont(ttfFileRef), size, bold, italic);
/*     */   }
/*     */ 
/*     */   public UnicodeFont(java.awt.Font font, String hieroFileRef)
/*     */     throws SlickException
/*     */   {
/* 186 */     this(font, new HieroSettings(hieroFileRef));
/*     */   }
/*     */ 
/*     */   public UnicodeFont(java.awt.Font font, HieroSettings settings)
/*     */   {
/* 196 */     initializeFont(font, settings.getFontSize(), settings.isBold(), settings.isItalic());
/* 197 */     loadSettings(settings);
/*     */   }
/*     */ 
/*     */   public UnicodeFont(java.awt.Font font)
/*     */   {
/* 206 */     initializeFont(font, font.getSize(), font.isBold(), font.isItalic());
/*     */   }
/*     */ 
/*     */   public UnicodeFont(java.awt.Font font, int size, boolean bold, boolean italic)
/*     */   {
/* 218 */     initializeFont(font, size, bold, italic);
/*     */   }
/*     */ 
/*     */   private void initializeFont(java.awt.Font baseFont, int size, boolean bold, boolean italic)
/*     */   {
/* 230 */     Map attributes = baseFont.getAttributes();
/* 231 */     attributes.put(TextAttribute.SIZE, new Float(size));
/* 232 */     attributes.put(TextAttribute.WEIGHT, bold ? TextAttribute.WEIGHT_BOLD : TextAttribute.WEIGHT_REGULAR);
/* 233 */     attributes.put(TextAttribute.POSTURE, italic ? TextAttribute.POSTURE_OBLIQUE : TextAttribute.POSTURE_REGULAR);
/*     */     try {
/* 235 */       attributes.put(TextAttribute.class.getDeclaredField("KERNING").get(null), TextAttribute.class.getDeclaredField("KERNING_ON").get(null));
/*     */     }
/*     */     catch (Exception ignored) {
/*     */     }
/* 239 */     this.font = baseFont.deriveFont(attributes);
/*     */ 
/* 241 */     FontMetrics metrics = GlyphPage.getScratchGraphics().getFontMetrics(this.font);
/* 242 */     this.ascent = metrics.getAscent();
/* 243 */     this.descent = metrics.getDescent();
/* 244 */     this.leading = metrics.getLeading();
/*     */ 
/* 247 */     char[] chars = " ".toCharArray();
/* 248 */     GlyphVector vector = this.font.layoutGlyphVector(GlyphPage.renderContext, chars, 0, chars.length, 0);
/* 249 */     this.spaceWidth = vector.getGlyphLogicalBounds(0).getBounds().width;
/*     */   }
/*     */ 
/*     */   private void loadSettings(HieroSettings settings)
/*     */   {
/* 258 */     this.paddingTop = settings.getPaddingTop();
/* 259 */     this.paddingLeft = settings.getPaddingLeft();
/* 260 */     this.paddingBottom = settings.getPaddingBottom();
/* 261 */     this.paddingRight = settings.getPaddingRight();
/* 262 */     this.paddingAdvanceX = settings.getPaddingAdvanceX();
/* 263 */     this.paddingAdvanceY = settings.getPaddingAdvanceY();
/* 264 */     this.glyphPageWidth = settings.getGlyphPageWidth();
/* 265 */     this.glyphPageHeight = settings.getGlyphPageHeight();
/* 266 */     this.effects.addAll(settings.getEffects());
/*     */   }
/*     */ 
/*     */   public void addGlyphs(int startCodePoint, int endCodePoint)
/*     */   {
/* 280 */     for (int codePoint = startCodePoint; codePoint <= endCodePoint; codePoint++)
/* 281 */       addGlyphs(new String(Character.toChars(codePoint)));
/*     */   }
/*     */ 
/*     */   public void addGlyphs(String text)
/*     */   {
/* 291 */     if (text == null) throw new IllegalArgumentException("text cannot be null.");
/*     */ 
/* 293 */     char[] chars = text.toCharArray();
/* 294 */     GlyphVector vector = this.font.layoutGlyphVector(GlyphPage.renderContext, chars, 0, chars.length, 0);
/* 295 */     int i = 0; for (int n = vector.getNumGlyphs(); i < n; i++) {
/* 296 */       int codePoint = text.codePointAt(vector.getGlyphCharIndex(i));
/* 297 */       Rectangle bounds = getGlyphBounds(vector, i, codePoint);
/* 298 */       getGlyph(vector.getGlyphCode(i), codePoint, bounds, vector, i);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void addAsciiGlyphs()
/*     */   {
/* 307 */     addGlyphs(32, 255);
/*     */   }
/*     */ 
/*     */   public void addNeheGlyphs()
/*     */   {
/* 315 */     addGlyphs(32, 128);
/*     */   }
/*     */ 
/*     */   public boolean loadGlyphs()
/*     */     throws SlickException
/*     */   {
/* 327 */     return loadGlyphs(-1);
/*     */   }
/*     */ 
/*     */   public boolean loadGlyphs(int maxGlyphsToLoad)
/*     */     throws SlickException
/*     */   {
/* 339 */     if (this.queuedGlyphs.isEmpty()) return false;
/*     */ 
/* 341 */     if (this.effects.isEmpty()) {
/* 342 */       throw new IllegalStateException("The UnicodeFont must have at least one effect before any glyphs can be loaded.");
/*     */     }
/* 344 */     for (Iterator iter = this.queuedGlyphs.iterator(); iter.hasNext(); ) {
/* 345 */       Glyph glyph = (Glyph)iter.next();
/* 346 */       int codePoint = glyph.getCodePoint();
/*     */ 
/* 349 */       if ((glyph.getWidth() == 0) || (codePoint == 32)) {
/* 350 */         iter.remove();
/*     */       }
/* 355 */       else if (glyph.isMissing()) {
/* 356 */         if (this.missingGlyph != null) {
/* 357 */           if (glyph != this.missingGlyph) iter.remove();
/*     */         }
/*     */         else {
/* 360 */           this.missingGlyph = glyph;
/*     */         }
/*     */       }
/*     */     }
/* 364 */     Collections.sort(this.queuedGlyphs, heightComparator);
/*     */ 
/* 367 */     for (Iterator iter = this.glyphPages.iterator(); iter.hasNext(); ) {
/* 368 */       GlyphPage glyphPage = (GlyphPage)iter.next();
/* 369 */       maxGlyphsToLoad -= glyphPage.loadGlyphs(this.queuedGlyphs, maxGlyphsToLoad);
/* 370 */       if ((maxGlyphsToLoad == 0) || (this.queuedGlyphs.isEmpty())) {
/* 371 */         return true;
/*     */       }
/*     */     }
/*     */ 
/* 375 */     while (!this.queuedGlyphs.isEmpty()) {
/* 376 */       GlyphPage glyphPage = new GlyphPage(this, this.glyphPageWidth, this.glyphPageHeight);
/* 377 */       this.glyphPages.add(glyphPage);
/* 378 */       maxGlyphsToLoad -= glyphPage.loadGlyphs(this.queuedGlyphs, maxGlyphsToLoad);
/* 379 */       if (maxGlyphsToLoad == 0) return true;
/*     */     }
/*     */ 
/* 382 */     return true;
/*     */   }
/*     */ 
/*     */   public void clearGlyphs()
/*     */   {
/* 389 */     for (int i = 0; i < 2175; i++) {
/* 390 */       this.glyphs[i] = null;
/*     */     }
/* 392 */     for (Iterator iter = this.glyphPages.iterator(); iter.hasNext(); ) {
/* 393 */       GlyphPage page = (GlyphPage)iter.next();
/*     */       try {
/* 395 */         page.getImage().destroy();
/*     */       } catch (SlickException ignored) {
/*     */       }
/*     */     }
/* 399 */     this.glyphPages.clear();
/*     */ 
/* 401 */     if (this.baseDisplayListID != -1) {
/* 402 */       GL.glDeleteLists(this.baseDisplayListID, this.displayLists.size());
/* 403 */       this.baseDisplayListID = -1;
/*     */     }
/*     */ 
/* 406 */     this.queuedGlyphs.clear();
/* 407 */     this.missingGlyph = null;
/*     */   }
/*     */ 
/*     */   public void destroy()
/*     */   {
/* 416 */     clearGlyphs();
/*     */   }
/*     */ 
/*     */   public DisplayList drawDisplayList(float x, float y, String text, Color color, int startIndex, int endIndex)
/*     */   {
/* 432 */     if (text == null) throw new IllegalArgumentException("text cannot be null.");
/* 433 */     if (text.length() == 0) return EMPTY_DISPLAY_LIST;
/* 434 */     if (color == null) throw new IllegalArgumentException("color cannot be null.");
/*     */ 
/* 436 */     x -= this.paddingLeft;
/* 437 */     y -= this.paddingTop;
/*     */ 
/* 439 */     String displayListKey = text.substring(startIndex, endIndex);
/*     */ 
/* 441 */     color.bind();
/* 442 */     TextureImpl.bindNone();
/*     */ 
/* 444 */     DisplayList displayList = null;
/* 445 */     if ((this.displayListCaching) && (this.queuedGlyphs.isEmpty())) {
/* 446 */       if (this.baseDisplayListID == -1) {
/* 447 */         this.baseDisplayListID = GL.glGenLists(600);
/* 448 */         if (this.baseDisplayListID == 0) {
/* 449 */           this.baseDisplayListID = -1;
/* 450 */           this.displayListCaching = false;
/* 451 */           return new DisplayList();
/*     */         }
/*     */       }
/*     */ 
/* 455 */       displayList = (DisplayList)this.displayLists.get(displayListKey);
/* 456 */       if (displayList != null) {
/* 457 */         if (displayList.invalid) {
/* 458 */           displayList.invalid = false;
/*     */         } else {
/* 460 */           GL.glTranslatef(x, y, 0.0F);
/* 461 */           GL.glCallList(displayList.id);
/* 462 */           GL.glTranslatef(-x, -y, 0.0F);
/* 463 */           return displayList;
/*     */         }
/* 465 */       } else if (displayList == null)
/*     */       {
/* 467 */         displayList = new DisplayList();
/* 468 */         int displayListCount = this.displayLists.size();
/* 469 */         this.displayLists.put(displayListKey, displayList);
/* 470 */         if (displayListCount < 600)
/* 471 */           displayList.id = (this.baseDisplayListID + displayListCount);
/*     */         else
/* 473 */           displayList.id = this.eldestDisplayListID;
/*     */       }
/* 475 */       this.displayLists.put(displayListKey, displayList);
/*     */ 
/* 478 */       if (info)
/* 479 */         System.err.println("[UNICODE] NEED TO COMPILE NEW DISPLAY LIST: " + this.font.getFontName() + "; size " + this.font.getSize() + "; bold: " + this.font.isBold() + "; displayListFill: " + this.displayLists.size() + ": " + text);
/*     */     }
/*     */     else {
/* 482 */       if (info) {
/* 483 */         System.err.println("[UNICODE] NOT CACHED: " + this.font.getFontName() + "; size " + this.font.getSize() + "; bold: " + this.font.isBold() + "; chaching: " + this.displayListCaching + "; queuedGlyphs: " + this.queuedGlyphs.size() + ": " + text);
/*     */       }
/* 485 */       if (!this.queuedGlyphs.isEmpty()) {
/*     */         try {
/* 487 */           loadGlyphs(-1);
/*     */         } catch (SlickException e) {
/* 489 */           e.printStackTrace();
/*     */         }
/* 491 */         if (info) {
/* 492 */           System.err.println("[UNICODE] LOADED GLYPHS: " + this.font.getFontName() + "; size " + this.font.getSize() + "; bold: " + this.font.isBold() + "; chaching: " + this.displayListCaching + "; queuedGlyphs: " + this.queuedGlyphs.size() + ": " + text);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 497 */     GL.glTranslatef(x, y, 0.0F);
/*     */ 
/* 499 */     if (displayList != null) GL.glNewList(displayList.id, 4865);
/*     */ 
/* 501 */     char[] chars = text.substring(0, endIndex).toCharArray();
/* 502 */     GlyphVector vector = this.font.layoutGlyphVector(GlyphPage.renderContext, chars, 0, chars.length, 0);
/*     */ 
/* 504 */     int maxWidth = 0; int totalHeight = 0; int lines = 0;
/* 505 */     int extraX = 0; int extraY = this.ascent;
/* 506 */     boolean startNewLine = false;
/* 507 */     Texture lastBind = null;
/* 508 */     int glyphIndex = 0; for (int n = vector.getNumGlyphs(); glyphIndex < n; glyphIndex++) {
/* 509 */       int charIndex = vector.getGlyphCharIndex(glyphIndex);
/* 510 */       if (charIndex >= startIndex) {
/* 511 */         if (charIndex > endIndex)
/*     */           break;
/* 513 */         int codePoint = text.codePointAt(charIndex);
/*     */ 
/* 515 */         Rectangle bounds = getGlyphBounds(vector, glyphIndex, codePoint);
/* 516 */         Glyph glyph = getGlyph(vector.getGlyphCode(glyphIndex), codePoint, bounds, vector, glyphIndex);
/*     */ 
/* 518 */         if ((startNewLine) && (codePoint != 10)) {
/* 519 */           extraX = -bounds.x;
/* 520 */           startNewLine = false;
/*     */         }
/*     */ 
/* 523 */         Image image = glyph.getImage();
/* 524 */         if ((image == null) && (this.missingGlyph != null) && (glyph.isMissing())) image = this.missingGlyph.getImage();
/* 525 */         if (image != null)
/*     */         {
/* 527 */           Texture texture = image.getTexture();
/* 528 */           if ((lastBind != null) && (lastBind != texture)) {
/* 529 */             GL.glEnd();
/* 530 */             lastBind = null;
/*     */           }
/* 532 */           if (lastBind == null) {
/* 533 */             texture.bind();
/* 534 */             GL.glBegin(7);
/* 535 */             lastBind = texture;
/*     */           }
/* 537 */           image.drawEmbedded(bounds.x + extraX, bounds.y + extraY, image.getWidth(), image.getHeight());
/*     */         }
/*     */ 
/* 540 */         if (glyphIndex >= 0) extraX += this.paddingRight + this.paddingLeft + this.paddingAdvanceX;
/* 541 */         maxWidth = Math.max(maxWidth, bounds.x + extraX + bounds.width);
/* 542 */         totalHeight = Math.max(totalHeight, this.ascent + bounds.y + bounds.height);
/*     */ 
/* 544 */         if (codePoint == 10) {
/* 545 */           startNewLine = true;
/* 546 */           extraY += getLineHeight();
/* 547 */           lines++;
/* 548 */           totalHeight = 0;
/*     */         }
/*     */       }
/*     */     }
/* 551 */     if (lastBind != null) GL.glEnd();
/*     */ 
/* 553 */     if (displayList != null) {
/* 554 */       GL.glEndList();
/*     */ 
/* 556 */       if (!this.queuedGlyphs.isEmpty()) displayList.invalid = true;
/*     */     }
/*     */ 
/* 559 */     GL.glTranslatef(-x, -y, 0.0F);
/*     */ 
/* 561 */     if (displayList == null) displayList = new DisplayList();
/* 562 */     displayList.width = ((short)maxWidth);
/* 563 */     displayList.height = ((short)(lines * getLineHeight() + totalHeight));
/* 564 */     return displayList;
/*     */   }
/*     */ 
/*     */   public void drawString(float x, float y, String text, Color color, int startIndex, int endIndex) {
/* 568 */     drawDisplayList(x, y, text, color, startIndex, endIndex);
/*     */   }
/*     */ 
/*     */   public void drawString(float x, float y, String text) {
/* 572 */     drawString(x, y, text, Color.white);
/*     */   }
/*     */ 
/*     */   public void drawString(float x, float y, String text, Color col) {
/* 576 */     drawString(x, y, text, col, 0, text.length());
/*     */   }
/*     */ 
/*     */   private Glyph getGlyph(int glyphCode, int codePoint, Rectangle bounds, GlyphVector vector, int index)
/*     */   {
/* 591 */     if ((glyphCode < 0) || (glyphCode >= 1114111))
/*     */     {
/* 593 */       return new Glyph(codePoint, bounds, vector, index, this) {
/*     */         public boolean isMissing() {
/* 595 */           return true;
/*     */         }
/*     */       };
/*     */     }
/* 599 */     int pageIndex = glyphCode / 512;
/* 600 */     int glyphIndex = glyphCode & 0x1FF;
/* 601 */     Glyph glyph = null;
/* 602 */     Glyph[] page = this.glyphs[pageIndex];
/* 603 */     if (page != null) {
/* 604 */       glyph = page[glyphIndex];
/* 605 */       if (glyph != null) return glyph; 
/*     */     }
/* 607 */     else { page = this.glyphs[pageIndex] =  = new Glyph[512]; }
/*     */ 
/* 609 */     glyph = page[glyphIndex] =  = new Glyph(codePoint, bounds, vector, index, this);
/* 610 */     this.queuedGlyphs.add(glyph);
/* 611 */     return glyph;
/*     */   }
/*     */ 
/*     */   private Rectangle getGlyphBounds(GlyphVector vector, int index, int codePoint)
/*     */   {
/* 622 */     Rectangle bounds = vector.getGlyphPixelBounds(index, GlyphPage.renderContext, 0.0F, 0.0F);
/* 623 */     if (codePoint == 32) bounds.width = this.spaceWidth;
/* 624 */     return bounds;
/*     */   }
/*     */ 
/*     */   public int getSpaceWidth()
/*     */   {
/* 631 */     return this.spaceWidth;
/*     */   }
/*     */ 
/*     */   public int getWidth(String text)
/*     */   {
/* 638 */     if (text == null) throw new IllegalArgumentException("text cannot be null.");
/* 639 */     if (text.length() == 0) return 0;
/*     */ 
/* 641 */     if (this.displayListCaching) {
/* 642 */       DisplayList displayList = (DisplayList)this.displayLists.get(text);
/* 643 */       if (displayList != null) return displayList.width;
/*     */ 
/*     */     }
/*     */ 
/* 647 */     char[] chars = text.toCharArray();
/* 648 */     GlyphVector vector = this.font.layoutGlyphVector(GlyphPage.renderContext, chars, 0, chars.length, 0);
/*     */ 
/* 650 */     int width = 0;
/* 651 */     int extraX = 0;
/* 652 */     boolean startNewLine = false;
/* 653 */     int glyphIndex = 0; for (int n = vector.getNumGlyphs(); glyphIndex < n; glyphIndex++) {
/* 654 */       int charIndex = vector.getGlyphCharIndex(glyphIndex);
/* 655 */       int codePoint = text.codePointAt(charIndex);
/* 656 */       Rectangle bounds = getGlyphBounds(vector, glyphIndex, codePoint);
/*     */ 
/* 658 */       if ((startNewLine) && (codePoint != 10)) extraX = -bounds.x;
/*     */ 
/* 660 */       if (glyphIndex > 0) extraX += this.paddingLeft + this.paddingRight + this.paddingAdvanceX;
/* 661 */       width = Math.max(width, bounds.x + extraX + bounds.width);
/*     */ 
/* 663 */       if (codePoint == 10) startNewLine = true;
/*     */     }
/*     */ 
/* 666 */     return width;
/*     */   }
/*     */ 
/*     */   public int getHeight(String text)
/*     */   {
/* 673 */     if (text == null) throw new IllegalArgumentException("text cannot be null.");
/* 674 */     if (text.length() == 0) return 0;
/*     */ 
/* 676 */     if (this.displayListCaching) {
/* 677 */       DisplayList displayList = (DisplayList)this.displayLists.get(text);
/* 678 */       if (displayList != null) return displayList.height;
/*     */     }
/*     */ 
/* 681 */     char[] chars = text.toCharArray();
/* 682 */     GlyphVector vector = this.font.layoutGlyphVector(GlyphPage.renderContext, chars, 0, chars.length, 0);
/*     */ 
/* 684 */     int lines = 0; int height = 0;
/* 685 */     int i = 0; for (int n = vector.getNumGlyphs(); i < n; i++) {
/* 686 */       int charIndex = vector.getGlyphCharIndex(i);
/* 687 */       int codePoint = text.codePointAt(charIndex);
/* 688 */       if (codePoint != 32) {
/* 689 */         Rectangle bounds = getGlyphBounds(vector, i, codePoint);
/*     */ 
/* 691 */         height = Math.max(height, this.ascent + bounds.y + bounds.height);
/*     */ 
/* 693 */         if (codePoint == 10) {
/* 694 */           lines++;
/* 695 */           height = 0;
/*     */         }
/*     */       }
/*     */     }
/* 698 */     return lines * getLineHeight() + height;
/*     */   }
/*     */ 
/*     */   public int getYOffset(String text)
/*     */   {
/* 709 */     if (text == null) throw new IllegalArgumentException("text cannot be null.");
/*     */ 
/* 711 */     DisplayList displayList = null;
/* 712 */     if (this.displayListCaching) {
/* 713 */       displayList = (DisplayList)this.displayLists.get(text);
/* 714 */       if ((displayList != null) && (displayList.yOffset != null)) return displayList.yOffset.intValue();
/*     */     }
/*     */ 
/* 717 */     int index = text.indexOf(10);
/* 718 */     if (index != -1) text = text.substring(0, index);
/* 719 */     char[] chars = text.toCharArray();
/* 720 */     GlyphVector vector = this.font.layoutGlyphVector(GlyphPage.renderContext, chars, 0, chars.length, 0);
/* 721 */     int yOffset = this.ascent + vector.getPixelBounds(null, 0.0F, 0.0F).y;
/*     */ 
/* 723 */     if (displayList != null) displayList.yOffset = new Short((short)yOffset);
/*     */ 
/* 725 */     return yOffset;
/*     */   }
/*     */ 
/*     */   public java.awt.Font getFont()
/*     */   {
/* 734 */     return this.font;
/*     */   }
/*     */ 
/*     */   public int getPaddingTop()
/*     */   {
/* 743 */     return this.paddingTop;
/*     */   }
/*     */ 
/*     */   public void setPaddingTop(int paddingTop)
/*     */   {
/* 752 */     this.paddingTop = paddingTop;
/*     */   }
/*     */ 
/*     */   public int getPaddingLeft()
/*     */   {
/* 761 */     return this.paddingLeft;
/*     */   }
/*     */ 
/*     */   public void setPaddingLeft(int paddingLeft)
/*     */   {
/* 770 */     this.paddingLeft = paddingLeft;
/*     */   }
/*     */ 
/*     */   public int getPaddingBottom()
/*     */   {
/* 779 */     return this.paddingBottom;
/*     */   }
/*     */ 
/*     */   public void setPaddingBottom(int paddingBottom)
/*     */   {
/* 788 */     this.paddingBottom = paddingBottom;
/*     */   }
/*     */ 
/*     */   public int getPaddingRight()
/*     */   {
/* 797 */     return this.paddingRight;
/*     */   }
/*     */ 
/*     */   public void setPaddingRight(int paddingRight)
/*     */   {
/* 806 */     this.paddingRight = paddingRight;
/*     */   }
/*     */ 
/*     */   public int getPaddingAdvanceX()
/*     */   {
/* 815 */     return this.paddingAdvanceX;
/*     */   }
/*     */ 
/*     */   public void setPaddingAdvanceX(int paddingAdvanceX)
/*     */   {
/* 825 */     this.paddingAdvanceX = paddingAdvanceX;
/*     */   }
/*     */ 
/*     */   public int getPaddingAdvanceY()
/*     */   {
/* 834 */     return this.paddingAdvanceY;
/*     */   }
/*     */ 
/*     */   public void setPaddingAdvanceY(int paddingAdvanceY)
/*     */   {
/* 844 */     this.paddingAdvanceY = paddingAdvanceY;
/*     */   }
/*     */ 
/*     */   public int getLineHeight()
/*     */   {
/* 852 */     return this.descent + this.ascent + this.leading + this.paddingTop + this.paddingBottom + this.paddingAdvanceY;
/*     */   }
/*     */ 
/*     */   public int getAscent()
/*     */   {
/* 861 */     return this.ascent;
/*     */   }
/*     */ 
/*     */   public int getDescent()
/*     */   {
/* 871 */     return this.descent;
/*     */   }
/*     */ 
/*     */   public int getLeading()
/*     */   {
/* 880 */     return this.leading;
/*     */   }
/*     */ 
/*     */   public int getGlyphPageWidth()
/*     */   {
/* 889 */     return this.glyphPageWidth;
/*     */   }
/*     */ 
/*     */   public void setGlyphPageWidth(int glyphPageWidth)
/*     */   {
/* 898 */     this.glyphPageWidth = glyphPageWidth;
/*     */   }
/*     */ 
/*     */   public int getGlyphPageHeight()
/*     */   {
/* 907 */     return this.glyphPageHeight;
/*     */   }
/*     */ 
/*     */   public void setGlyphPageHeight(int glyphPageHeight)
/*     */   {
/* 916 */     this.glyphPageHeight = glyphPageHeight;
/*     */   }
/*     */ 
/*     */   public List getGlyphPages()
/*     */   {
/* 925 */     return this.glyphPages;
/*     */   }
/*     */ 
/*     */   public List getEffects()
/*     */   {
/* 935 */     return this.effects;
/*     */   }
/*     */ 
/*     */   public boolean isCaching()
/*     */   {
/* 945 */     return this.displayListCaching;
/*     */   }
/*     */ 
/*     */   public void setDisplayListCaching(boolean displayListCaching)
/*     */   {
/* 955 */     this.displayListCaching = displayListCaching;
/*     */   }
/*     */ 
/*     */   public String getFontFile()
/*     */   {
/* 965 */     if (this.ttfFileRef == null)
/*     */     {
/*     */       try {
/* 968 */         Object font2D = Class.forName("sun.font.FontManager").getDeclaredMethod("getFont2D", new Class[] { java.awt.Font.class }).invoke(null, new Object[] { this.font });
/*     */ 
/* 970 */         Field platNameField = Class.forName("sun.font.PhysicalFont").getDeclaredField("platName");
/* 971 */         platNameField.setAccessible(true);
/* 972 */         this.ttfFileRef = ((String)platNameField.get(font2D));
/*     */       } catch (Throwable ignored) {
/*     */       }
/* 975 */       if (this.ttfFileRef == null) this.ttfFileRef = "";
/*     */     }
/* 977 */     if (this.ttfFileRef.length() == 0) return null;
/* 978 */     return this.ttfFileRef;
/*     */   }
/*     */ 
/*     */   public static class DisplayList
/*     */   {
/*     */     boolean invalid;
/*     */     int id;
/*     */     Short yOffset;
/*     */     public short width;
/*     */     public short height;
/*     */     public Object userData;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.UnicodeFont
 * JD-Core Version:    0.6.2
 */