package org.newdawn.slick;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import org.newdawn.slick.opengl.GLUtils;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;
import org.newdawn.slick.util.BufferedImageUtil;

public class TrueTypeFont
  implements Font
{
  private static final SGL field_2 = ;
  private IntObject[] charArray = new IntObject[256];
  private Map customChars = new HashMap();
  private boolean antiAlias;
  private int fontSize = 0;
  private int fontHeight = 0;
  private Texture fontTexture;
  private int textureWidth = 512;
  private int textureHeight = 512;
  private java.awt.Font font;
  private FontMetrics fontMetrics;
  
  public TrueTypeFont(java.awt.Font font, boolean antiAlias, char[] additionalChars)
  {
    GLUtils.checkGLContext();
    this.font = font;
    this.fontSize = font.getSize();
    this.antiAlias = antiAlias;
    createSet(additionalChars);
  }
  
  public TrueTypeFont(java.awt.Font font, boolean antiAlias)
  {
    this(font, antiAlias, null);
  }
  
  private BufferedImage getFontImage(char local_ch)
  {
    BufferedImage tempfontImage = new BufferedImage(1, 1, 2);
    Graphics2D local_g = (Graphics2D)tempfontImage.getGraphics();
    if (this.antiAlias == true) {
      local_g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }
    local_g.setFont(this.font);
    this.fontMetrics = local_g.getFontMetrics();
    int charwidth = this.fontMetrics.charWidth(local_ch);
    if (charwidth <= 0) {
      charwidth = 1;
    }
    int charheight = this.fontMetrics.getHeight();
    if (charheight <= 0) {
      charheight = this.fontSize;
    }
    BufferedImage fontImage = new BufferedImage(charwidth, charheight, 2);
    Graphics2D local_gt = (Graphics2D)fontImage.getGraphics();
    if (this.antiAlias == true) {
      local_gt.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }
    local_gt.setFont(this.font);
    local_gt.setColor(java.awt.Color.WHITE);
    int charx = 0;
    int chary = 0;
    local_gt.drawString(String.valueOf(local_ch), charx, chary + this.fontMetrics.getAscent());
    return fontImage;
  }
  
  private void createSet(char[] customCharsArray)
  {
    if ((customCharsArray != null) && (customCharsArray.length > 0)) {
      this.textureWidth *= 2;
    }
    try
    {
      BufferedImage imgTemp = new BufferedImage(this.textureWidth, this.textureHeight, 2);
      Graphics2D local_g = (Graphics2D)imgTemp.getGraphics();
      local_g.setColor(new java.awt.Color(255, 255, 255, 1));
      local_g.fillRect(0, 0, this.textureWidth, this.textureHeight);
      int rowHeight = 0;
      int positionX = 0;
      int positionY = 0;
      int customCharsLength = customCharsArray != null ? customCharsArray.length : 0;
      for (int local_i = 0; local_i < 256 + customCharsLength; local_i++)
      {
        char local_ch = local_i < 256 ? (char)local_i : customCharsArray[(local_i - 256)];
        BufferedImage fontImage = getFontImage(local_ch);
        IntObject newIntObject = new IntObject(null);
        newIntObject.width = fontImage.getWidth();
        newIntObject.height = fontImage.getHeight();
        if (positionX + newIntObject.width >= this.textureWidth)
        {
          positionX = 0;
          positionY += rowHeight;
          rowHeight = 0;
        }
        newIntObject.storedX = positionX;
        newIntObject.storedY = positionY;
        if (newIntObject.height > this.fontHeight) {
          this.fontHeight = newIntObject.height;
        }
        if (newIntObject.height > rowHeight) {
          rowHeight = newIntObject.height;
        }
        local_g.drawImage(fontImage, positionX, positionY, null);
        positionX += newIntObject.width;
        if (local_i < 256) {
          this.charArray[local_i] = newIntObject;
        } else {
          this.customChars.put(new Character(local_ch), newIntObject);
        }
        fontImage = null;
      }
      this.fontTexture = BufferedImageUtil.getTexture(this.font.toString(), imgTemp);
    }
    catch (IOException imgTemp)
    {
      System.err.println("Failed to create font.");
      imgTemp.printStackTrace();
    }
  }
  
  private void drawQuad(float drawX, float drawY, float drawX2, float drawY2, float srcX, float srcY, float srcX2, float srcY2)
  {
    float DrawWidth = drawX2 - drawX;
    float DrawHeight = drawY2 - drawY;
    float TextureSrcX = srcX / this.textureWidth;
    float TextureSrcY = srcY / this.textureHeight;
    float SrcWidth = srcX2 - srcX;
    float SrcHeight = srcY2 - srcY;
    float RenderWidth = SrcWidth / this.textureWidth;
    float RenderHeight = SrcHeight / this.textureHeight;
    field_2.glTexCoord2f(TextureSrcX, TextureSrcY);
    field_2.glVertex2f(drawX, drawY);
    field_2.glTexCoord2f(TextureSrcX, TextureSrcY + RenderHeight);
    field_2.glVertex2f(drawX, drawY + DrawHeight);
    field_2.glTexCoord2f(TextureSrcX + RenderWidth, TextureSrcY + RenderHeight);
    field_2.glVertex2f(drawX + DrawWidth, drawY + DrawHeight);
    field_2.glTexCoord2f(TextureSrcX + RenderWidth, TextureSrcY);
    field_2.glVertex2f(drawX + DrawWidth, drawY);
  }
  
  public int getWidth(String whatchars)
  {
    int totalwidth = 0;
    IntObject intObject = null;
    int currentChar = 0;
    for (int local_i = 0; local_i < whatchars.length(); local_i++)
    {
      currentChar = whatchars.charAt(local_i);
      if (currentChar < 256) {
        intObject = this.charArray[currentChar];
      } else {
        intObject = (IntObject)this.customChars.get(new Character((char)currentChar));
      }
      if (intObject != null) {
        totalwidth += intObject.width;
      }
    }
    return totalwidth;
  }
  
  public int getHeight()
  {
    return this.fontHeight;
  }
  
  public int getHeight(String HeightString)
  {
    return this.fontHeight;
  }
  
  public int getLineHeight()
  {
    return this.fontHeight;
  }
  
  public void drawString(float local_x, float local_y, String whatchars, Color color)
  {
    drawString(local_x, local_y, whatchars, color, 0, whatchars.length() - 1);
  }
  
  public void drawString(float local_x, float local_y, String whatchars, Color color, int startIndex, int endIndex)
  {
    color.bind();
    this.fontTexture.bind();
    IntObject intObject = null;
    field_2.glBegin(7);
    int totalwidth = 0;
    for (int local_i = 0; local_i < whatchars.length(); local_i++)
    {
      int charCurrent = whatchars.charAt(local_i);
      if (charCurrent < 256) {
        intObject = this.charArray[charCurrent];
      } else {
        intObject = (IntObject)this.customChars.get(new Character((char)charCurrent));
      }
      if (intObject != null)
      {
        if ((local_i >= startIndex) || (local_i <= endIndex)) {
          drawQuad(local_x + totalwidth, local_y, local_x + totalwidth + intObject.width, local_y + intObject.height, intObject.storedX, intObject.storedY, intObject.storedX + intObject.width, intObject.storedY + intObject.height);
        }
        totalwidth += intObject.width;
      }
    }
    field_2.glEnd();
  }
  
  public void drawString(float local_x, float local_y, String whatchars)
  {
    drawString(local_x, local_y, whatchars, Color.white);
  }
  
  private class IntObject
  {
    public int width;
    public int height;
    public int storedX;
    public int storedY;
    
    private IntObject() {}
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.TrueTypeFont
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */