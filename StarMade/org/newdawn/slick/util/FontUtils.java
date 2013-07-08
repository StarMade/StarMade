/*   1:    */package org.newdawn.slick.util;
/*   2:    */
/*   3:    */import org.newdawn.slick.Color;
/*   4:    */import org.newdawn.slick.Font;
/*   5:    */
/*  35:    */public class FontUtils
/*  36:    */{
/*  37:    */  public static void drawLeft(Font font, String s, int x, int y)
/*  38:    */  {
/*  39: 39 */    drawString(font, s, 1, x, y, 0, Color.white);
/*  40:    */  }
/*  41:    */  
/*  50:    */  public static void drawCenter(Font font, String s, int x, int y, int width)
/*  51:    */  {
/*  52: 52 */    drawString(font, s, 2, x, y, width, Color.white);
/*  53:    */  }
/*  54:    */  
/*  65:    */  public static void drawCenter(Font font, String s, int x, int y, int width, Color color)
/*  66:    */  {
/*  67: 67 */    drawString(font, s, 2, x, y, width, color);
/*  68:    */  }
/*  69:    */  
/*  78:    */  public static void drawRight(Font font, String s, int x, int y, int width)
/*  79:    */  {
/*  80: 80 */    drawString(font, s, 3, x, y, width, Color.white);
/*  81:    */  }
/*  82:    */  
/*  93:    */  public static void drawRight(Font font, String s, int x, int y, int width, Color color)
/*  94:    */  {
/*  95: 95 */    drawString(font, s, 3, x, y, width, color);
/*  96:    */  }
/*  97:    */  
/* 111:    */  public static final int drawString(Font font, String s, int alignment, int x, int y, int width, Color color)
/* 112:    */  {
/* 113:113 */    int resultingXCoordinate = 0;
/* 114:114 */    if (alignment == 1) {
/* 115:115 */      font.drawString(x, y, s, color);
/* 116:116 */    } else if (alignment == 2) {
/* 117:117 */      font.drawString(x + width / 2 - font.getWidth(s) / 2, y, s, color);
/* 118:    */    }
/* 119:119 */    else if (alignment == 3) {
/* 120:120 */      font.drawString(x + width - font.getWidth(s), y, s, color);
/* 121:121 */    } else if (alignment == 4)
/* 122:    */    {
/* 123:123 */      int leftWidth = width - font.getWidth(s);
/* 124:124 */      if (leftWidth <= 0)
/* 125:    */      {
/* 126:126 */        font.drawString(x, y, s, color);
/* 127:    */      }
/* 128:    */      
/* 129:129 */      return drawJustifiedSpaceSeparatedSubstrings(font, s, x, y, calculateWidthOfJustifiedSpaceInPixels(font, s, leftWidth));
/* 130:    */    }
/* 131:    */    
/* 134:134 */    return resultingXCoordinate;
/* 135:    */  }
/* 136:    */  
/* 152:    */  private static int calculateWidthOfJustifiedSpaceInPixels(Font font, String s, int leftWidth)
/* 153:    */  {
/* 154:154 */    int space = 0;
/* 155:155 */    int curpos = 0;
/* 156:    */    
/* 158:158 */    while (curpos < s.length()) {
/* 159:159 */      if (s.charAt(curpos++) == ' ') {
/* 160:160 */        space++;
/* 161:    */      }
/* 162:    */    }
/* 163:    */    
/* 164:164 */    if (space > 0)
/* 165:    */    {
/* 167:167 */      space = (leftWidth + font.getWidth(" ") * space) / space;
/* 168:    */    }
/* 169:169 */    return space;
/* 170:    */  }
/* 171:    */  
/* 198:    */  private static int drawJustifiedSpaceSeparatedSubstrings(Font font, String s, int x, int y, int justifiedSpaceWidth)
/* 199:    */  {
/* 200:200 */    int curpos = 0;
/* 201:201 */    int endpos = 0;
/* 202:202 */    int resultingXCoordinate = x;
/* 203:203 */    while (curpos < s.length()) {
/* 204:204 */      endpos = s.indexOf(' ', curpos);
/* 205:205 */      if (endpos == -1) {
/* 206:206 */        endpos = s.length();
/* 207:    */      }
/* 208:208 */      String substring = s.substring(curpos, endpos);
/* 209:    */      
/* 210:210 */      font.drawString(resultingXCoordinate, y, substring);
/* 211:    */      
/* 212:212 */      resultingXCoordinate += font.getWidth(substring) + justifiedSpaceWidth;
/* 213:    */      
/* 215:215 */      curpos = endpos + 1;
/* 216:    */    }
/* 217:    */    
/* 218:218 */    return resultingXCoordinate;
/* 219:    */  }
/* 220:    */  
/* 221:    */  public class Alignment
/* 222:    */  {
/* 223:    */    public static final int LEFT = 1;
/* 224:    */    public static final int CENTER = 2;
/* 225:    */    public static final int RIGHT = 3;
/* 226:    */    public static final int JUSTIFY = 4;
/* 227:    */    
/* 228:    */    public Alignment() {}
/* 229:    */  }
/* 230:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.util.FontUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */