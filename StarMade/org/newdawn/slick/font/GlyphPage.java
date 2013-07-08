/*   1:    */package org.newdawn.slick.font;
/*   2:    */
/*   3:    */import java.awt.AlphaComposite;
/*   4:    */import java.awt.Graphics2D;
/*   5:    */import java.awt.RenderingHints;
/*   6:    */import java.awt.font.FontRenderContext;
/*   7:    */import java.awt.image.BufferedImage;
/*   8:    */import java.awt.image.WritableRaster;
/*   9:    */import java.nio.ByteBuffer;
/*  10:    */import java.nio.ByteOrder;
/*  11:    */import java.nio.IntBuffer;
/*  12:    */import java.util.ArrayList;
/*  13:    */import java.util.Iterator;
/*  14:    */import java.util.List;
/*  15:    */import java.util.ListIterator;
/*  16:    */import org.newdawn.slick.Image;
/*  17:    */import org.newdawn.slick.SlickException;
/*  18:    */import org.newdawn.slick.UnicodeFont;
/*  19:    */import org.newdawn.slick.font.effects.Effect;
/*  20:    */import org.newdawn.slick.opengl.TextureImpl;
/*  21:    */import org.newdawn.slick.opengl.renderer.Renderer;
/*  22:    */import org.newdawn.slick.opengl.renderer.SGL;
/*  23:    */
/*  32:    */public class GlyphPage
/*  33:    */{
/*  34: 34 */  private static final SGL GL = ;
/*  35:    */  
/*  37:    */  public static final int MAX_GLYPH_SIZE = 256;
/*  38:    */  
/*  40: 40 */  private static ByteBuffer scratchByteBuffer = ByteBuffer.allocateDirect(262144);
/*  41:    */  private static IntBuffer scratchIntBuffer;
/*  42:    */  
/*  43: 43 */  static { scratchByteBuffer.order(ByteOrder.LITTLE_ENDIAN);
/*  44:    */    
/*  47: 47 */    scratchIntBuffer = scratchByteBuffer.asIntBuffer();
/*  48:    */    
/*  51: 51 */    scratchImage = new BufferedImage(256, 256, 2);
/*  52:    */    
/*  53: 53 */    scratchGraphics = (Graphics2D)scratchImage.getGraphics();
/*  54:    */    
/*  56: 56 */    scratchGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*  57: 57 */    scratchGraphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
/*  58: 58 */    scratchGraphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON); }
/*  59:    */  
/*  60:    */  private static BufferedImage scratchImage;
/*  61:    */  private static Graphics2D scratchGraphics;
/*  62: 62 */  public static FontRenderContext renderContext = scratchGraphics.getFontRenderContext();
/*  63:    */  private final UnicodeFont unicodeFont;
/*  64:    */  private final int pageWidth;
/*  65:    */  private final int pageHeight;
/*  66:    */  private final Image pageImage;
/*  67:    */  
/*  68:    */  public static Graphics2D getScratchGraphics()
/*  69:    */  {
/*  70: 70 */    return scratchGraphics;
/*  71:    */  }
/*  72:    */  
/*  76:    */  private int pageX;
/*  77:    */  
/*  80:    */  private int pageY;
/*  81:    */  
/*  84:    */  private int rowHeight;
/*  85:    */  
/*  87:    */  private boolean orderAscending;
/*  88:    */  
/*  90: 90 */  private final List pageGlyphs = new ArrayList(32);
/*  91:    */  
/*  98:    */  public GlyphPage(UnicodeFont unicodeFont, int pageWidth, int pageHeight)
/*  99:    */    throws SlickException
/* 100:    */  {
/* 101:101 */    this.unicodeFont = unicodeFont;
/* 102:102 */    this.pageWidth = pageWidth;
/* 103:103 */    this.pageHeight = pageHeight;
/* 104:    */    
/* 105:105 */    this.pageImage = new Image(pageWidth, pageHeight);
/* 106:    */  }
/* 107:    */  
/* 109:    */  public int loadGlyphs(List glyphs, int maxGlyphsToLoad)
/* 110:    */    throws SlickException
/* 111:    */  {
/* 112:    */    int testX;
/* 113:    */    
/* 114:    */    int testY;
/* 115:    */    
/* 116:    */    int testRowHeight;
/* 117:    */    
/* 118:    */    Iterator iter;
/* 119:    */    
/* 120:120 */    if ((this.rowHeight != 0) && (maxGlyphsToLoad == -1))
/* 121:    */    {
/* 122:122 */      testX = this.pageX;
/* 123:123 */      testY = this.pageY;
/* 124:124 */      testRowHeight = this.rowHeight;
/* 125:125 */      for (iter = getIterator(glyphs); iter.hasNext();) {
/* 126:126 */        Glyph glyph = (Glyph)iter.next();
/* 127:127 */        int width = glyph.getWidth();
/* 128:128 */        int height = glyph.getHeight();
/* 129:129 */        if (testX + width >= this.pageWidth) {
/* 130:130 */          testX = 0;
/* 131:131 */          testY += testRowHeight;
/* 132:132 */          testRowHeight = height;
/* 133:133 */        } else if (height > testRowHeight) {
/* 134:134 */          testRowHeight = height;
/* 135:    */        }
/* 136:136 */        if (testY + testRowHeight >= this.pageWidth) return 0;
/* 137:137 */        testX += width;
/* 138:    */      }
/* 139:    */    }
/* 140:    */    
/* 141:141 */    org.newdawn.slick.Color.white.bind();
/* 142:142 */    this.pageImage.bind();
/* 143:    */    
/* 144:144 */    int i = 0;
/* 145:145 */    for (Iterator iter = getIterator(glyphs); iter.hasNext();) {
/* 146:146 */      Glyph glyph = (Glyph)iter.next();
/* 147:147 */      int width = Math.min(256, glyph.getWidth());
/* 148:148 */      int height = Math.min(256, glyph.getHeight());
/* 149:    */      
/* 150:150 */      if (this.rowHeight == 0)
/* 151:    */      {
/* 152:152 */        this.rowHeight = height;
/* 154:    */      }
/* 155:155 */      else if (this.pageX + width >= this.pageWidth) {
/* 156:156 */        if (this.pageY + this.rowHeight + height >= this.pageHeight) break;
/* 157:157 */        this.pageX = 0;
/* 158:158 */        this.pageY += this.rowHeight;
/* 159:159 */        this.rowHeight = height;
/* 160:160 */      } else if (height > this.rowHeight) {
/* 161:161 */        if (this.pageY + height >= this.pageHeight) break;
/* 162:162 */        this.rowHeight = height;
/* 163:    */      }
/* 164:    */      
/* 166:166 */      renderGlyph(glyph, width, height);
/* 167:167 */      this.pageGlyphs.add(glyph);
/* 168:    */      
/* 169:169 */      this.pageX += width;
/* 170:    */      
/* 171:171 */      iter.remove();
/* 172:172 */      i++;
/* 173:173 */      if (i == maxGlyphsToLoad)
/* 174:    */      {
/* 175:175 */        this.orderAscending = (!this.orderAscending);
/* 176:176 */        break;
/* 177:    */      }
/* 178:    */    }
/* 179:    */    
/* 180:180 */    TextureImpl.bindNone();
/* 181:    */    
/* 183:183 */    this.orderAscending = (!this.orderAscending);
/* 184:    */    
/* 185:185 */    return i;
/* 186:    */  }
/* 187:    */  
/* 195:    */  private void renderGlyph(Glyph glyph, int width, int height)
/* 196:    */    throws SlickException
/* 197:    */  {
/* 198:198 */    scratchGraphics.setComposite(AlphaComposite.Clear);
/* 199:199 */    scratchGraphics.fillRect(0, 0, 256, 256);
/* 200:200 */    scratchGraphics.setComposite(AlphaComposite.SrcOver);
/* 201:201 */    scratchGraphics.setColor(java.awt.Color.white);
/* 202:202 */    for (Iterator iter = this.unicodeFont.getEffects().iterator(); iter.hasNext();)
/* 203:203 */      ((Effect)iter.next()).draw(scratchImage, scratchGraphics, this.unicodeFont, glyph);
/* 204:204 */    glyph.setShape(null);
/* 205:    */    
/* 206:206 */    WritableRaster raster = scratchImage.getRaster();
/* 207:207 */    int[] row = new int[width];
/* 208:208 */    for (int y = 0; y < height; y++) {
/* 209:209 */      raster.getDataElements(0, y, width, 1, row);
/* 210:210 */      scratchIntBuffer.put(row);
/* 211:    */    }
/* 212:212 */    GL.glTexSubImage2D(3553, 0, this.pageX, this.pageY, width, height, 32993, 5121, scratchByteBuffer);
/* 213:    */    
/* 214:214 */    scratchIntBuffer.clear();
/* 215:    */    
/* 216:216 */    glyph.setImage(this.pageImage.getSubImage(this.pageX, this.pageY, width, height));
/* 217:    */  }
/* 218:    */  
/* 224:    */  private Iterator getIterator(List glyphs)
/* 225:    */  {
/* 226:226 */    if (this.orderAscending) return glyphs.iterator();
/* 227:227 */    final ListIterator iter = glyphs.listIterator(glyphs.size());
/* 228:228 */    new Iterator() {
/* 229:    */      public boolean hasNext() {
/* 230:230 */        return iter.hasPrevious();
/* 231:    */      }
/* 232:    */      
/* 233:    */      public Object next() {
/* 234:234 */        return iter.previous();
/* 235:    */      }
/* 236:    */      
/* 237:    */      public void remove() {
/* 238:238 */        iter.remove();
/* 239:    */      }
/* 240:    */    };
/* 241:    */  }
/* 242:    */  
/* 247:    */  public List getGlyphs()
/* 248:    */  {
/* 249:249 */    return this.pageGlyphs;
/* 250:    */  }
/* 251:    */  
/* 256:    */  public Image getImage()
/* 257:    */  {
/* 258:258 */    return this.pageImage;
/* 259:    */  }
/* 260:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.font.GlyphPage
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */