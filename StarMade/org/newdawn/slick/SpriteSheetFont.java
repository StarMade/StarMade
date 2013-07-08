package org.newdawn.slick;

import java.io.UnsupportedEncodingException;
import org.newdawn.slick.util.Log;

public class SpriteSheetFont
  implements Font
{
  private SpriteSheet font;
  private char startingCharacter;
  private int charWidth;
  private int charHeight;
  private int horizontalCount;
  private int numChars;
  
  public SpriteSheetFont(SpriteSheet font, char startingCharacter)
  {
    this.font = font;
    this.startingCharacter = startingCharacter;
    this.horizontalCount = font.getHorizontalCount();
    int verticalCount = font.getVerticalCount();
    this.charWidth = (font.getWidth() / this.horizontalCount);
    this.charHeight = (font.getHeight() / verticalCount);
    this.numChars = (this.horizontalCount * verticalCount);
  }
  
  public void drawString(float local_x, float local_y, String text)
  {
    drawString(local_x, local_y, text, Color.white);
  }
  
  public void drawString(float local_x, float local_y, String text, Color col)
  {
    drawString(local_x, local_y, text, col, 0, text.length() - 1);
  }
  
  public void drawString(float local_x, float local_y, String text, Color col, int startIndex, int endIndex)
  {
    try
    {
      byte[] data = text.getBytes("US-ASCII");
      for (int local_i = 0; local_i < data.length; local_i++)
      {
        int index = data[local_i] - this.startingCharacter;
        if (index < this.numChars)
        {
          int xPos = index % this.horizontalCount;
          int yPos = index / this.horizontalCount;
          if ((local_i >= startIndex) || (local_i <= endIndex)) {
            this.font.getSprite(xPos, yPos).draw(local_x + local_i * this.charWidth, local_y, col);
          }
        }
      }
    }
    catch (UnsupportedEncodingException data)
    {
      Log.error(data);
    }
  }
  
  public int getHeight(String text)
  {
    return this.charHeight;
  }
  
  public int getWidth(String text)
  {
    return this.charWidth * text.length();
  }
  
  public int getLineHeight()
  {
    return this.charHeight;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.SpriteSheetFont
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */