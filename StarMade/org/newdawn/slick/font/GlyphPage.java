/*     */ package org.newdawn.slick.font;
/*     */ 
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.font.FontRenderContext;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.IntBuffer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import org.newdawn.slick.Image;
/*     */ import org.newdawn.slick.SlickException;
/*     */ import org.newdawn.slick.UnicodeFont;
/*     */ import org.newdawn.slick.font.effects.Effect;
/*     */ import org.newdawn.slick.opengl.TextureImpl;
/*     */ import org.newdawn.slick.opengl.renderer.Renderer;
/*     */ import org.newdawn.slick.opengl.renderer.SGL;
/*     */ 
/*     */ public class GlyphPage
/*     */ {
/*  34 */   private static final SGL GL = Renderer.get();
/*     */   public static final int MAX_GLYPH_SIZE = 256;
/*  40 */   private static ByteBuffer scratchByteBuffer = ByteBuffer.allocateDirect(262144);
/*     */   private static IntBuffer scratchIntBuffer;
/*     */   private static BufferedImage scratchImage;
/*     */   private static Graphics2D scratchGraphics;
/*  62 */   public static FontRenderContext renderContext = scratchGraphics.getFontRenderContext();
/*     */   private final UnicodeFont unicodeFont;
/*     */   private final int pageWidth;
/*     */   private final int pageHeight;
/*     */   private final Image pageImage;
/*     */   private int pageX;
/*     */   private int pageY;
/*     */   private int rowHeight;
/*     */   private boolean orderAscending;
/*  90 */   private final List pageGlyphs = new ArrayList(32);
/*     */ 
/*     */   public static Graphics2D getScratchGraphics()
/*     */   {
/*  70 */     return scratchGraphics;
/*     */   }
/*     */ 
/*     */   public GlyphPage(UnicodeFont unicodeFont, int pageWidth, int pageHeight)
/*     */     throws SlickException
/*     */   {
/* 101 */     this.unicodeFont = unicodeFont;
/* 102 */     this.pageWidth = pageWidth;
/* 103 */     this.pageHeight = pageHeight;
/*     */ 
/* 105 */     this.pageImage = new Image(pageWidth, pageHeight);
/*     */   }
/*     */ 
/*     */   public int loadGlyphs(List glyphs, int maxGlyphsToLoad)
/*     */     throws SlickException
/*     */   {
/*     */     int testX;
/*     */     int testY;
/*     */     int testRowHeight;
/*     */     Iterator iter;
/* 120 */     if ((this.rowHeight != 0) && (maxGlyphsToLoad == -1))
/*     */     {
/* 122 */       testX = this.pageX;
/* 123 */       testY = this.pageY;
/* 124 */       testRowHeight = this.rowHeight;
/* 125 */       for (iter = getIterator(glyphs); iter.hasNext(); ) {
/* 126 */         Glyph glyph = (Glyph)iter.next();
/* 127 */         int width = glyph.getWidth();
/* 128 */         int height = glyph.getHeight();
/* 129 */         if (testX + width >= this.pageWidth) {
/* 130 */           testX = 0;
/* 131 */           testY += testRowHeight;
/* 132 */           testRowHeight = height;
/* 133 */         } else if (height > testRowHeight) {
/* 134 */           testRowHeight = height;
/*     */         }
/* 136 */         if (testY + testRowHeight >= this.pageWidth) return 0;
/* 137 */         testX += width;
/*     */       }
/*     */     }
/*     */ 
/* 141 */     org.newdawn.slick.Color.white.bind();
/* 142 */     this.pageImage.bind();
/*     */ 
/* 144 */     int i = 0;
/* 145 */     for (Iterator iter = getIterator(glyphs); iter.hasNext(); ) {
/* 146 */       Glyph glyph = (Glyph)iter.next();
/* 147 */       int width = Math.min(256, glyph.getWidth());
/* 148 */       int height = Math.min(256, glyph.getHeight());
/*     */ 
/* 150 */       if (this.rowHeight == 0)
/*     */       {
/* 152 */         this.rowHeight = height;
/*     */       }
/* 155 */       else if (this.pageX + width >= this.pageWidth) {
/* 156 */         if (this.pageY + this.rowHeight + height >= this.pageHeight) break;
/* 157 */         this.pageX = 0;
/* 158 */         this.pageY += this.rowHeight;
/* 159 */         this.rowHeight = height;
/* 160 */       } else if (height > this.rowHeight) {
/* 161 */         if (this.pageY + height >= this.pageHeight) break;
/* 162 */         this.rowHeight = height;
/*     */       }
/*     */ 
/* 166 */       renderGlyph(glyph, width, height);
/* 167 */       this.pageGlyphs.add(glyph);
/*     */ 
/* 169 */       this.pageX += width;
/*     */ 
/* 171 */       iter.remove();
/* 172 */       i++;
/* 173 */       if (i == maxGlyphsToLoad)
/*     */       {
/* 175 */         this.orderAscending = (!this.orderAscending);
/* 176 */         break;
/*     */       }
/*     */     }
/*     */ 
/* 180 */     TextureImpl.bindNone();
/*     */ 
/* 183 */     this.orderAscending = (!this.orderAscending);
/*     */ 
/* 185 */     return i;
/*     */   }
/*     */ 
/*     */   private void renderGlyph(Glyph glyph, int width, int height)
/*     */     throws SlickException
/*     */   {
/* 198 */     scratchGraphics.setComposite(AlphaComposite.Clear);
/* 199 */     scratchGraphics.fillRect(0, 0, 256, 256);
/* 200 */     scratchGraphics.setComposite(AlphaComposite.SrcOver);
/* 201 */     scratchGraphics.setColor(java.awt.Color.white);
/* 202 */     for (Iterator iter = this.unicodeFont.getEffects().iterator(); iter.hasNext(); )
/* 203 */       ((Effect)iter.next()).draw(scratchImage, scratchGraphics, this.unicodeFont, glyph);
/* 204 */     glyph.setShape(null);
/*     */ 
/* 206 */     WritableRaster raster = scratchImage.getRaster();
/* 207 */     int[] row = new int[width];
/* 208 */     for (int y = 0; y < height; y++) {
/* 209 */       raster.getDataElements(0, y, width, 1, row);
/* 210 */       scratchIntBuffer.put(row);
/*     */     }
/* 212 */     GL.glTexSubImage2D(3553, 0, this.pageX, this.pageY, width, height, 32993, 5121, scratchByteBuffer);
/*     */ 
/* 214 */     scratchIntBuffer.clear();
/*     */ 
/* 216 */     glyph.setImage(this.pageImage.getSubImage(this.pageX, this.pageY, width, height));
/*     */   }
/*     */ 
/*     */   private Iterator getIterator(List glyphs)
/*     */   {
/* 226 */     if (this.orderAscending) return glyphs.iterator();
/* 227 */     final ListIterator iter = glyphs.listIterator(glyphs.size());
/* 228 */     return new Iterator() {
/*     */       public boolean hasNext() {
/* 230 */         return iter.hasPrevious();
/*     */       }
/*     */ 
/*     */       public Object next() {
/* 234 */         return iter.previous();
/*     */       }
/*     */ 
/*     */       public void remove() {
/* 238 */         iter.remove();
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public List getGlyphs()
/*     */   {
/* 249 */     return this.pageGlyphs;
/*     */   }
/*     */ 
/*     */   public Image getImage()
/*     */   {
/* 258 */     return this.pageImage;
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  43 */     scratchByteBuffer.order(ByteOrder.LITTLE_ENDIAN);
/*     */ 
/*  47 */     scratchIntBuffer = scratchByteBuffer.asIntBuffer();
/*     */ 
/*  51 */     scratchImage = new BufferedImage(256, 256, 2);
/*     */ 
/*  53 */     scratchGraphics = (Graphics2D)scratchImage.getGraphics();
/*     */ 
/*  56 */     scratchGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*  57 */     scratchGraphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
/*  58 */     scratchGraphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.font.GlyphPage
 * JD-Core Version:    0.6.2
 */