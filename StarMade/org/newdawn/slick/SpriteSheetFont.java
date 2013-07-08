/*   1:    */package org.newdawn.slick;
/*   2:    */
/*   3:    */import java.io.UnsupportedEncodingException;
/*   4:    */import org.newdawn.slick.util.Log;
/*   5:    */
/*  32:    */public class SpriteSheetFont
/*  33:    */  implements Font
/*  34:    */{
/*  35:    */  private SpriteSheet font;
/*  36:    */  private char startingCharacter;
/*  37:    */  private int charWidth;
/*  38:    */  private int charHeight;
/*  39:    */  private int horizontalCount;
/*  40:    */  private int numChars;
/*  41:    */  
/*  42:    */  public SpriteSheetFont(SpriteSheet font, char startingCharacter)
/*  43:    */  {
/*  44: 44 */    this.font = font;
/*  45: 45 */    this.startingCharacter = startingCharacter;
/*  46: 46 */    this.horizontalCount = font.getHorizontalCount();
/*  47: 47 */    int verticalCount = font.getVerticalCount();
/*  48: 48 */    this.charWidth = (font.getWidth() / this.horizontalCount);
/*  49: 49 */    this.charHeight = (font.getHeight() / verticalCount);
/*  50: 50 */    this.numChars = (this.horizontalCount * verticalCount);
/*  51:    */  }
/*  52:    */  
/*  55:    */  public void drawString(float x, float y, String text)
/*  56:    */  {
/*  57: 57 */    drawString(x, y, text, Color.white);
/*  58:    */  }
/*  59:    */  
/*  62:    */  public void drawString(float x, float y, String text, Color col)
/*  63:    */  {
/*  64: 64 */    drawString(x, y, text, col, 0, text.length() - 1);
/*  65:    */  }
/*  66:    */  
/*  68:    */  public void drawString(float x, float y, String text, Color col, int startIndex, int endIndex)
/*  69:    */  {
/*  70:    */    try
/*  71:    */    {
/*  72: 72 */      byte[] data = text.getBytes("US-ASCII");
/*  73: 73 */      for (int i = 0; i < data.length; i++) {
/*  74: 74 */        int index = data[i] - this.startingCharacter;
/*  75: 75 */        if (index < this.numChars) {
/*  76: 76 */          int xPos = index % this.horizontalCount;
/*  77: 77 */          int yPos = index / this.horizontalCount;
/*  78:    */          
/*  79: 79 */          if ((i >= startIndex) || (i <= endIndex)) {
/*  80: 80 */            this.font.getSprite(xPos, yPos).draw(x + i * this.charWidth, y, col);
/*  81:    */          }
/*  82:    */        }
/*  83:    */      }
/*  84:    */    }
/*  85:    */    catch (UnsupportedEncodingException e)
/*  86:    */    {
/*  87: 87 */      Log.error(e);
/*  88:    */    }
/*  89:    */  }
/*  90:    */  
/*  93:    */  public int getHeight(String text)
/*  94:    */  {
/*  95: 95 */    return this.charHeight;
/*  96:    */  }
/*  97:    */  
/* 100:    */  public int getWidth(String text)
/* 101:    */  {
/* 102:102 */    return this.charWidth * text.length();
/* 103:    */  }
/* 104:    */  
/* 107:    */  public int getLineHeight()
/* 108:    */  {
/* 109:109 */    return this.charHeight;
/* 110:    */  }
/* 111:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.SpriteSheetFont
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */