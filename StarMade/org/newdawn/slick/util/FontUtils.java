package org.newdawn.slick.util;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;

public class FontUtils
{
  public static void drawLeft(Font font, String local_s, int local_x, int local_y)
  {
    drawString(font, local_s, 1, local_x, local_y, 0, Color.white);
  }
  
  public static void drawCenter(Font font, String local_s, int local_x, int local_y, int width)
  {
    drawString(font, local_s, 2, local_x, local_y, width, Color.white);
  }
  
  public static void drawCenter(Font font, String local_s, int local_x, int local_y, int width, Color color)
  {
    drawString(font, local_s, 2, local_x, local_y, width, color);
  }
  
  public static void drawRight(Font font, String local_s, int local_x, int local_y, int width)
  {
    drawString(font, local_s, 3, local_x, local_y, width, Color.white);
  }
  
  public static void drawRight(Font font, String local_s, int local_x, int local_y, int width, Color color)
  {
    drawString(font, local_s, 3, local_x, local_y, width, color);
  }
  
  public static final int drawString(Font font, String local_s, int alignment, int local_x, int local_y, int width, Color color)
  {
    int resultingXCoordinate = 0;
    if (alignment == 1)
    {
      font.drawString(local_x, local_y, local_s, color);
    }
    else if (alignment == 2)
    {
      font.drawString(local_x + width / 2 - font.getWidth(local_s) / 2, local_y, local_s, color);
    }
    else if (alignment == 3)
    {
      font.drawString(local_x + width - font.getWidth(local_s), local_y, local_s, color);
    }
    else if (alignment == 4)
    {
      int leftWidth = width - font.getWidth(local_s);
      if (leftWidth <= 0) {
        font.drawString(local_x, local_y, local_s, color);
      }
      return drawJustifiedSpaceSeparatedSubstrings(font, local_s, local_x, local_y, calculateWidthOfJustifiedSpaceInPixels(font, local_s, leftWidth));
    }
    return resultingXCoordinate;
  }
  
  private static int calculateWidthOfJustifiedSpaceInPixels(Font font, String local_s, int leftWidth)
  {
    int space = 0;
    int curpos = 0;
    while (curpos < local_s.length()) {
      if (local_s.charAt(curpos++) == ' ') {
        space++;
      }
    }
    if (space > 0) {
      space = (leftWidth + font.getWidth(" ") * space) / space;
    }
    return space;
  }
  
  private static int drawJustifiedSpaceSeparatedSubstrings(Font font, String local_s, int local_x, int local_y, int justifiedSpaceWidth)
  {
    int curpos = 0;
    int endpos = 0;
    int resultingXCoordinate = local_x;
    while (curpos < local_s.length())
    {
      endpos = local_s.indexOf(' ', curpos);
      if (endpos == -1) {
        endpos = local_s.length();
      }
      String substring = local_s.substring(curpos, endpos);
      font.drawString(resultingXCoordinate, local_y, substring);
      resultingXCoordinate += font.getWidth(substring) + justifiedSpaceWidth;
      curpos = endpos + 1;
    }
    return resultingXCoordinate;
  }
  
  public class Alignment
  {
    public static final int LEFT = 1;
    public static final int CENTER = 2;
    public static final int RIGHT = 3;
    public static final int JUSTIFY = 4;
    
    public Alignment() {}
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.util.FontUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */