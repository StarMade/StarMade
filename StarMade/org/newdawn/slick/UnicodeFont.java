package org.newdawn.slick;

import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.font.GlyphVector;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.newdawn.slick.font.Glyph;
import org.newdawn.slick.font.GlyphPage;
import org.newdawn.slick.font.HieroSettings;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureImpl;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;
import org.newdawn.slick.util.ResourceLoader;

public class UnicodeFont
  implements Font
{
  private static final int DISPLAY_LIST_CACHE_SIZE = 600;
  private static final int MAX_GLYPH_CODE = 1114111;
  private static final int PAGE_SIZE = 512;
  private static final int PAGES = 2175;
  private static final SGL field_2 = ;
  private static final DisplayList EMPTY_DISPLAY_LIST = new DisplayList();
  public static boolean info = false;
  private static final Comparator heightComparator = new Comparator()
  {
    public int compare(Object local_o1, Object local_o2)
    {
      return ((Glyph)local_o1).getHeight() - ((Glyph)local_o2).getHeight();
    }
  };
  private java.awt.Font font;
  private String ttfFileRef;
  private int ascent;
  private int descent;
  private int leading;
  private int spaceWidth;
  private final Glyph[][] glyphs = new Glyph[2175][];
  private final List glyphPages = new ArrayList();
  private final List queuedGlyphs = new ArrayList(256);
  private final List effects = new ArrayList();
  private int paddingTop;
  private int paddingLeft;
  private int paddingBottom;
  private int paddingRight;
  private int paddingAdvanceX;
  private int paddingAdvanceY;
  private Glyph missingGlyph;
  private int glyphPageWidth = 512;
  private int glyphPageHeight = 512;
  private boolean displayListCaching = true;
  private int baseDisplayListID = -1;
  private int eldestDisplayListID;
  private DisplayList eldestDisplayList;
  private final LinkedHashMap displayLists = new LinkedHashMap(600, 1.0F, true)
  {
    protected boolean removeEldestEntry(Map.Entry eldest)
    {
      UnicodeFont.DisplayList displayList = (UnicodeFont.DisplayList)eldest.getValue();
      if (displayList != null) {
        UnicodeFont.this.eldestDisplayListID = displayList.field_2239;
      }
      return size() > 600;
    }
  };
  
  private static java.awt.Font createFont(String ttfFileRef)
    throws SlickException
  {
    try
    {
      return java.awt.Font.createFont(0, ResourceLoader.getResourceAsStream(ttfFileRef));
    }
    catch (FontFormatException local_ex)
    {
      throw new SlickException("Invalid font: " + ttfFileRef, local_ex);
    }
    catch (IOException local_ex)
    {
      throw new SlickException("Error reading font: " + ttfFileRef, local_ex);
    }
  }
  
  public UnicodeFont(String ttfFileRef, String hieroFileRef)
    throws SlickException
  {
    this(ttfFileRef, new HieroSettings(hieroFileRef));
  }
  
  public UnicodeFont(String ttfFileRef, HieroSettings settings)
    throws SlickException
  {
    this.ttfFileRef = ttfFileRef;
    java.awt.Font font = createFont(ttfFileRef);
    initializeFont(font, settings.getFontSize(), settings.isBold(), settings.isItalic());
    loadSettings(settings);
  }
  
  public UnicodeFont(String ttfFileRef, int size, boolean bold, boolean italic)
    throws SlickException
  {
    this.ttfFileRef = ttfFileRef;
    initializeFont(createFont(ttfFileRef), size, bold, italic);
  }
  
  public UnicodeFont(java.awt.Font font, String hieroFileRef)
    throws SlickException
  {
    this(font, new HieroSettings(hieroFileRef));
  }
  
  public UnicodeFont(java.awt.Font font, HieroSettings settings)
  {
    initializeFont(font, settings.getFontSize(), settings.isBold(), settings.isItalic());
    loadSettings(settings);
  }
  
  public UnicodeFont(java.awt.Font font)
  {
    initializeFont(font, font.getSize(), font.isBold(), font.isItalic());
  }
  
  public UnicodeFont(java.awt.Font font, int size, boolean bold, boolean italic)
  {
    initializeFont(font, size, bold, italic);
  }
  
  private void initializeFont(java.awt.Font baseFont, int size, boolean bold, boolean italic)
  {
    Map attributes = baseFont.getAttributes();
    attributes.put(TextAttribute.SIZE, new Float(size));
    attributes.put(TextAttribute.WEIGHT, bold ? TextAttribute.WEIGHT_BOLD : TextAttribute.WEIGHT_REGULAR);
    attributes.put(TextAttribute.POSTURE, italic ? TextAttribute.POSTURE_OBLIQUE : TextAttribute.POSTURE_REGULAR);
    try
    {
      attributes.put(TextAttribute.class.getDeclaredField("KERNING").get(null), TextAttribute.class.getDeclaredField("KERNING_ON").get(null));
    }
    catch (Exception ignored) {}
    this.font = baseFont.deriveFont(attributes);
    FontMetrics ignored = GlyphPage.getScratchGraphics().getFontMetrics(this.font);
    this.ascent = ignored.getAscent();
    this.descent = ignored.getDescent();
    this.leading = ignored.getLeading();
    char[] chars = " ".toCharArray();
    GlyphVector vector = this.font.layoutGlyphVector(GlyphPage.renderContext, chars, 0, chars.length, 0);
    this.spaceWidth = vector.getGlyphLogicalBounds(0).getBounds().width;
  }
  
  private void loadSettings(HieroSettings settings)
  {
    this.paddingTop = settings.getPaddingTop();
    this.paddingLeft = settings.getPaddingLeft();
    this.paddingBottom = settings.getPaddingBottom();
    this.paddingRight = settings.getPaddingRight();
    this.paddingAdvanceX = settings.getPaddingAdvanceX();
    this.paddingAdvanceY = settings.getPaddingAdvanceY();
    this.glyphPageWidth = settings.getGlyphPageWidth();
    this.glyphPageHeight = settings.getGlyphPageHeight();
    this.effects.addAll(settings.getEffects());
  }
  
  public void addGlyphs(int startCodePoint, int endCodePoint)
  {
    for (int codePoint = startCodePoint; codePoint <= endCodePoint; codePoint++) {
      addGlyphs(new String(Character.toChars(codePoint)));
    }
  }
  
  public void addGlyphs(String text)
  {
    if (text == null) {
      throw new IllegalArgumentException("text cannot be null.");
    }
    char[] chars = text.toCharArray();
    GlyphVector vector = this.font.layoutGlyphVector(GlyphPage.renderContext, chars, 0, chars.length, 0);
    int local_i = 0;
    int local_n = vector.getNumGlyphs();
    while (local_i < local_n)
    {
      int codePoint = text.codePointAt(vector.getGlyphCharIndex(local_i));
      Rectangle bounds = getGlyphBounds(vector, local_i, codePoint);
      getGlyph(vector.getGlyphCode(local_i), codePoint, bounds, vector, local_i);
      local_i++;
    }
  }
  
  public void addAsciiGlyphs()
  {
    addGlyphs(32, 255);
  }
  
  public void addNeheGlyphs()
  {
    addGlyphs(32, 128);
  }
  
  public boolean loadGlyphs()
    throws SlickException
  {
    return loadGlyphs(-1);
  }
  
  public boolean loadGlyphs(int maxGlyphsToLoad)
    throws SlickException
  {
    if (this.queuedGlyphs.isEmpty()) {
      return false;
    }
    if (this.effects.isEmpty()) {
      throw new IllegalStateException("The UnicodeFont must have at least one effect before any glyphs can be loaded.");
    }
    Iterator iter = this.queuedGlyphs.iterator();
    while (iter.hasNext())
    {
      Glyph glyph = (Glyph)iter.next();
      int codePoint = glyph.getCodePoint();
      if ((glyph.getWidth() == 0) || (codePoint == 32)) {
        iter.remove();
      } else if (glyph.isMissing()) {
        if (this.missingGlyph != null)
        {
          if (glyph != this.missingGlyph) {
            iter.remove();
          }
        }
        else {
          this.missingGlyph = glyph;
        }
      }
    }
    Collections.sort(this.queuedGlyphs, heightComparator);
    Iterator iter = this.glyphPages.iterator();
    while (iter.hasNext())
    {
      GlyphPage glyph = (GlyphPage)iter.next();
      maxGlyphsToLoad -= glyph.loadGlyphs(this.queuedGlyphs, maxGlyphsToLoad);
      if ((maxGlyphsToLoad == 0) || (this.queuedGlyphs.isEmpty())) {
        return true;
      }
    }
    while (!this.queuedGlyphs.isEmpty())
    {
      GlyphPage iter = new GlyphPage(this, this.glyphPageWidth, this.glyphPageHeight);
      this.glyphPages.add(iter);
      maxGlyphsToLoad -= iter.loadGlyphs(this.queuedGlyphs, maxGlyphsToLoad);
      if (maxGlyphsToLoad == 0) {
        return true;
      }
    }
    return true;
  }
  
  public void clearGlyphs()
  {
    for (int local_i = 0; local_i < 2175; local_i++) {
      this.glyphs[local_i] = null;
    }
    Iterator local_i = this.glyphPages.iterator();
    while (local_i.hasNext())
    {
      GlyphPage page = (GlyphPage)local_i.next();
      try
      {
        page.getImage().destroy();
      }
      catch (SlickException ignored) {}
    }
    this.glyphPages.clear();
    if (this.baseDisplayListID != -1)
    {
      field_2.glDeleteLists(this.baseDisplayListID, this.displayLists.size());
      this.baseDisplayListID = -1;
    }
    this.queuedGlyphs.clear();
    this.missingGlyph = null;
  }
  
  public void destroy()
  {
    clearGlyphs();
  }
  
  public DisplayList drawDisplayList(float local_x, float local_y, String text, Color color, int startIndex, int endIndex)
  {
    if (text == null) {
      throw new IllegalArgumentException("text cannot be null.");
    }
    if (text.length() == 0) {
      return EMPTY_DISPLAY_LIST;
    }
    if (color == null) {
      throw new IllegalArgumentException("color cannot be null.");
    }
    local_x -= this.paddingLeft;
    local_y -= this.paddingTop;
    String displayListKey = text.substring(startIndex, endIndex);
    color.bind();
    TextureImpl.bindNone();
    DisplayList displayList = null;
    if ((this.displayListCaching) && (this.queuedGlyphs.isEmpty()))
    {
      if (this.baseDisplayListID == -1)
      {
        this.baseDisplayListID = field_2.glGenLists(600);
        if (this.baseDisplayListID == 0)
        {
          this.baseDisplayListID = -1;
          this.displayListCaching = false;
          return new DisplayList();
        }
      }
      displayList = (DisplayList)this.displayLists.get(displayListKey);
      if (displayList != null)
      {
        if (displayList.invalid)
        {
          displayList.invalid = false;
        }
        else
        {
          field_2.glTranslatef(local_x, local_y, 0.0F);
          field_2.glCallList(displayList.field_2239);
          field_2.glTranslatef(-local_x, -local_y, 0.0F);
          return displayList;
        }
      }
      else if (displayList == null)
      {
        displayList = new DisplayList();
        int displayListCount = this.displayLists.size();
        this.displayLists.put(displayListKey, displayList);
        if (displayListCount < 600) {
          displayList.field_2239 = (this.baseDisplayListID + displayListCount);
        } else {
          displayList.field_2239 = this.eldestDisplayListID;
        }
      }
      this.displayLists.put(displayListKey, displayList);
      if (info) {
        System.err.println("[UNICODE] NEED TO COMPILE NEW DISPLAY LIST: " + this.font.getFontName() + "; size " + this.font.getSize() + "; bold: " + this.font.isBold() + "; displayListFill: " + this.displayLists.size() + ": " + text);
      }
    }
    else
    {
      if (info) {
        System.err.println("[UNICODE] NOT CACHED: " + this.font.getFontName() + "; size " + this.font.getSize() + "; bold: " + this.font.isBold() + "; chaching: " + this.displayListCaching + "; queuedGlyphs: " + this.queuedGlyphs.size() + ": " + text);
      }
      if (!this.queuedGlyphs.isEmpty())
      {
        try
        {
          loadGlyphs(-1);
        }
        catch (SlickException displayListCount)
        {
          displayListCount.printStackTrace();
        }
        if (info) {
          System.err.println("[UNICODE] LOADED GLYPHS: " + this.font.getFontName() + "; size " + this.font.getSize() + "; bold: " + this.font.isBold() + "; chaching: " + this.displayListCaching + "; queuedGlyphs: " + this.queuedGlyphs.size() + ": " + text);
        }
      }
    }
    field_2.glTranslatef(local_x, local_y, 0.0F);
    if (displayList != null) {
      field_2.glNewList(displayList.field_2239, 4865);
    }
    char[] displayListCount = text.substring(0, endIndex).toCharArray();
    GlyphVector vector = this.font.layoutGlyphVector(GlyphPage.renderContext, displayListCount, 0, displayListCount.length, 0);
    int maxWidth = 0;
    int totalHeight = 0;
    int lines = 0;
    int extraX = 0;
    int extraY = this.ascent;
    boolean startNewLine = false;
    Texture lastBind = null;
    int glyphIndex = 0;
    int local_n = vector.getNumGlyphs();
    while (glyphIndex < local_n)
    {
      int charIndex = vector.getGlyphCharIndex(glyphIndex);
      if (charIndex >= startIndex)
      {
        if (charIndex > endIndex) {
          break;
        }
        int codePoint = text.codePointAt(charIndex);
        Rectangle bounds = getGlyphBounds(vector, glyphIndex, codePoint);
        Glyph glyph = getGlyph(vector.getGlyphCode(glyphIndex), codePoint, bounds, vector, glyphIndex);
        if ((startNewLine) && (codePoint != 10))
        {
          extraX = -bounds.x;
          startNewLine = false;
        }
        Image image = glyph.getImage();
        if ((image == null) && (this.missingGlyph != null) && (glyph.isMissing())) {
          image = this.missingGlyph.getImage();
        }
        if (image != null)
        {
          Texture texture = image.getTexture();
          if ((lastBind != null) && (lastBind != texture))
          {
            field_2.glEnd();
            lastBind = null;
          }
          if (lastBind == null)
          {
            texture.bind();
            field_2.glBegin(7);
            lastBind = texture;
          }
          image.drawEmbedded(bounds.x + extraX, bounds.y + extraY, image.getWidth(), image.getHeight());
        }
        if (glyphIndex >= 0) {
          extraX += this.paddingRight + this.paddingLeft + this.paddingAdvanceX;
        }
        maxWidth = Math.max(maxWidth, bounds.x + extraX + bounds.width);
        totalHeight = Math.max(totalHeight, this.ascent + bounds.y + bounds.height);
        if (codePoint == 10)
        {
          startNewLine = true;
          extraY += getLineHeight();
          lines++;
          totalHeight = 0;
        }
      }
      glyphIndex++;
    }
    if (lastBind != null) {
      field_2.glEnd();
    }
    if (displayList != null)
    {
      field_2.glEndList();
      if (!this.queuedGlyphs.isEmpty()) {
        displayList.invalid = true;
      }
    }
    field_2.glTranslatef(-local_x, -local_y, 0.0F);
    if (displayList == null) {
      displayList = new DisplayList();
    }
    displayList.width = ((short)maxWidth);
    displayList.height = ((short)(lines * getLineHeight() + totalHeight));
    return displayList;
  }
  
  public void drawString(float local_x, float local_y, String text, Color color, int startIndex, int endIndex)
  {
    drawDisplayList(local_x, local_y, text, color, startIndex, endIndex);
  }
  
  public void drawString(float local_x, float local_y, String text)
  {
    drawString(local_x, local_y, text, Color.white);
  }
  
  public void drawString(float local_x, float local_y, String text, Color col)
  {
    drawString(local_x, local_y, text, col, 0, text.length());
  }
  
  private Glyph getGlyph(int glyphCode, int codePoint, Rectangle bounds, GlyphVector vector, int index)
  {
    if ((glyphCode < 0) || (glyphCode >= 1114111)) {
      new Glyph(codePoint, bounds, vector, index, this)
      {
        public boolean isMissing()
        {
          return true;
        }
      };
    }
    int pageIndex = glyphCode / 512;
    int glyphIndex = glyphCode & 0x1FF;
    Glyph glyph = null;
    Glyph[] page = this.glyphs[pageIndex];
    if (page != null)
    {
      glyph = page[glyphIndex];
      if (glyph != null) {
        return glyph;
      }
    }
    else
    {
      page = this.glyphs[pageIndex] =  = new Glyph[512];
    }
    glyph = page[glyphIndex] =  = new Glyph(codePoint, bounds, vector, index, this);
    this.queuedGlyphs.add(glyph);
    return glyph;
  }
  
  private Rectangle getGlyphBounds(GlyphVector vector, int index, int codePoint)
  {
    Rectangle bounds = vector.getGlyphPixelBounds(index, GlyphPage.renderContext, 0.0F, 0.0F);
    if (codePoint == 32) {
      bounds.width = this.spaceWidth;
    }
    return bounds;
  }
  
  public int getSpaceWidth()
  {
    return this.spaceWidth;
  }
  
  public int getWidth(String text)
  {
    if (text == null) {
      throw new IllegalArgumentException("text cannot be null.");
    }
    if (text.length() == 0) {
      return 0;
    }
    if (this.displayListCaching)
    {
      DisplayList displayList = (DisplayList)this.displayLists.get(text);
      if (displayList != null) {
        return displayList.width;
      }
    }
    char[] displayList = text.toCharArray();
    GlyphVector vector = this.font.layoutGlyphVector(GlyphPage.renderContext, displayList, 0, displayList.length, 0);
    int width = 0;
    int extraX = 0;
    boolean startNewLine = false;
    int glyphIndex = 0;
    int local_n = vector.getNumGlyphs();
    while (glyphIndex < local_n)
    {
      int charIndex = vector.getGlyphCharIndex(glyphIndex);
      int codePoint = text.codePointAt(charIndex);
      Rectangle bounds = getGlyphBounds(vector, glyphIndex, codePoint);
      if ((startNewLine) && (codePoint != 10)) {
        extraX = -bounds.x;
      }
      if (glyphIndex > 0) {
        extraX += this.paddingLeft + this.paddingRight + this.paddingAdvanceX;
      }
      width = Math.max(width, bounds.x + extraX + bounds.width);
      if (codePoint == 10) {
        startNewLine = true;
      }
      glyphIndex++;
    }
    return width;
  }
  
  public int getHeight(String text)
  {
    if (text == null) {
      throw new IllegalArgumentException("text cannot be null.");
    }
    if (text.length() == 0) {
      return 0;
    }
    if (this.displayListCaching)
    {
      DisplayList displayList = (DisplayList)this.displayLists.get(text);
      if (displayList != null) {
        return displayList.height;
      }
    }
    char[] displayList = text.toCharArray();
    GlyphVector vector = this.font.layoutGlyphVector(GlyphPage.renderContext, displayList, 0, displayList.length, 0);
    int lines = 0;
    int height = 0;
    int local_i = 0;
    int local_n = vector.getNumGlyphs();
    while (local_i < local_n)
    {
      int charIndex = vector.getGlyphCharIndex(local_i);
      int codePoint = text.codePointAt(charIndex);
      if (codePoint != 32)
      {
        Rectangle bounds = getGlyphBounds(vector, local_i, codePoint);
        height = Math.max(height, this.ascent + bounds.y + bounds.height);
        if (codePoint == 10)
        {
          lines++;
          height = 0;
        }
      }
      local_i++;
    }
    return lines * getLineHeight() + height;
  }
  
  public int getYOffset(String text)
  {
    if (text == null) {
      throw new IllegalArgumentException("text cannot be null.");
    }
    DisplayList displayList = null;
    if (this.displayListCaching)
    {
      displayList = (DisplayList)this.displayLists.get(text);
      if ((displayList != null) && (displayList.yOffset != null)) {
        return displayList.yOffset.intValue();
      }
    }
    int index = text.indexOf('\n');
    if (index != -1) {
      text = text.substring(0, index);
    }
    char[] chars = text.toCharArray();
    GlyphVector vector = this.font.layoutGlyphVector(GlyphPage.renderContext, chars, 0, chars.length, 0);
    int yOffset = this.ascent + vector.getPixelBounds(null, 0.0F, 0.0F).y;
    if (displayList != null) {
      displayList.yOffset = new Short((short)yOffset);
    }
    return yOffset;
  }
  
  public java.awt.Font getFont()
  {
    return this.font;
  }
  
  public int getPaddingTop()
  {
    return this.paddingTop;
  }
  
  public void setPaddingTop(int paddingTop)
  {
    this.paddingTop = paddingTop;
  }
  
  public int getPaddingLeft()
  {
    return this.paddingLeft;
  }
  
  public void setPaddingLeft(int paddingLeft)
  {
    this.paddingLeft = paddingLeft;
  }
  
  public int getPaddingBottom()
  {
    return this.paddingBottom;
  }
  
  public void setPaddingBottom(int paddingBottom)
  {
    this.paddingBottom = paddingBottom;
  }
  
  public int getPaddingRight()
  {
    return this.paddingRight;
  }
  
  public void setPaddingRight(int paddingRight)
  {
    this.paddingRight = paddingRight;
  }
  
  public int getPaddingAdvanceX()
  {
    return this.paddingAdvanceX;
  }
  
  public void setPaddingAdvanceX(int paddingAdvanceX)
  {
    this.paddingAdvanceX = paddingAdvanceX;
  }
  
  public int getPaddingAdvanceY()
  {
    return this.paddingAdvanceY;
  }
  
  public void setPaddingAdvanceY(int paddingAdvanceY)
  {
    this.paddingAdvanceY = paddingAdvanceY;
  }
  
  public int getLineHeight()
  {
    return this.descent + this.ascent + this.leading + this.paddingTop + this.paddingBottom + this.paddingAdvanceY;
  }
  
  public int getAscent()
  {
    return this.ascent;
  }
  
  public int getDescent()
  {
    return this.descent;
  }
  
  public int getLeading()
  {
    return this.leading;
  }
  
  public int getGlyphPageWidth()
  {
    return this.glyphPageWidth;
  }
  
  public void setGlyphPageWidth(int glyphPageWidth)
  {
    this.glyphPageWidth = glyphPageWidth;
  }
  
  public int getGlyphPageHeight()
  {
    return this.glyphPageHeight;
  }
  
  public void setGlyphPageHeight(int glyphPageHeight)
  {
    this.glyphPageHeight = glyphPageHeight;
  }
  
  public List getGlyphPages()
  {
    return this.glyphPages;
  }
  
  public List getEffects()
  {
    return this.effects;
  }
  
  public boolean isCaching()
  {
    return this.displayListCaching;
  }
  
  public void setDisplayListCaching(boolean displayListCaching)
  {
    this.displayListCaching = displayListCaching;
  }
  
  public String getFontFile()
  {
    if (this.ttfFileRef == null)
    {
      try
      {
        Object font2D = Class.forName("sun.font.FontManager").getDeclaredMethod("getFont2D", new Class[] { java.awt.Font.class }).invoke(null, new Object[] { this.font });
        Field platNameField = Class.forName("sun.font.PhysicalFont").getDeclaredField("platName");
        platNameField.setAccessible(true);
        this.ttfFileRef = ((String)platNameField.get(font2D));
      }
      catch (Throwable font2D) {}
      if (this.ttfFileRef == null) {
        this.ttfFileRef = "";
      }
    }
    if (this.ttfFileRef.length() == 0) {
      return null;
    }
    return this.ttfFileRef;
  }
  
  public static class DisplayList
  {
    boolean invalid;
    int field_2239;
    Short yOffset;
    public short width;
    public short height;
    public Object userData;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.UnicodeFont
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */