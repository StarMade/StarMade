package org.newdawn.slick;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;
import org.newdawn.slick.util.Log;
import org.newdawn.slick.util.ResourceLoader;

public class AngelCodeFont
  implements Font
{
  private static SGL field_2 = ;
  private static final int DISPLAY_LIST_CACHE_SIZE = 200;
  private static final int MAX_CHAR = 255;
  private boolean displayListCaching = true;
  private Image fontImage;
  private CharDef[] chars;
  private int lineHeight;
  private int baseDisplayListID = -1;
  private int eldestDisplayListID;
  private DisplayList eldestDisplayList;
  private final LinkedHashMap displayLists = new LinkedHashMap(200, 1.0F, true)
  {
    protected boolean removeEldestEntry(Map.Entry eldest)
    {
      AngelCodeFont.this.eldestDisplayList = ((AngelCodeFont.DisplayList)eldest.getValue());
      AngelCodeFont.this.eldestDisplayListID = AngelCodeFont.this.eldestDisplayList.field_2140;
      return false;
    }
  };
  
  public AngelCodeFont(String fntFile, Image image)
    throws SlickException
  {
    this.fontImage = image;
    parseFnt(ResourceLoader.getResourceAsStream(fntFile));
  }
  
  public AngelCodeFont(String fntFile, String imgFile)
    throws SlickException
  {
    this.fontImage = new Image(imgFile);
    parseFnt(ResourceLoader.getResourceAsStream(fntFile));
  }
  
  public AngelCodeFont(String fntFile, Image image, boolean caching)
    throws SlickException
  {
    this.fontImage = image;
    this.displayListCaching = caching;
    parseFnt(ResourceLoader.getResourceAsStream(fntFile));
  }
  
  public AngelCodeFont(String fntFile, String imgFile, boolean caching)
    throws SlickException
  {
    this.fontImage = new Image(imgFile);
    this.displayListCaching = caching;
    parseFnt(ResourceLoader.getResourceAsStream(fntFile));
  }
  
  public AngelCodeFont(String name, InputStream fntFile, InputStream imgFile)
    throws SlickException
  {
    this.fontImage = new Image(imgFile, name, false);
    parseFnt(fntFile);
  }
  
  public AngelCodeFont(String name, InputStream fntFile, InputStream imgFile, boolean caching)
    throws SlickException
  {
    this.fontImage = new Image(imgFile, name, false);
    this.displayListCaching = caching;
    parseFnt(fntFile);
  }
  
  private void parseFnt(InputStream fntFile)
    throws SlickException
  {
    if (this.displayListCaching)
    {
      this.baseDisplayListID = field_2.glGenLists(200);
      if (this.baseDisplayListID == 0) {
        this.displayListCaching = false;
      }
    }
    try
    {
      BufferedReader local_in = new BufferedReader(new InputStreamReader(fntFile));
      String info = local_in.readLine();
      String common = local_in.readLine();
      String page = local_in.readLine();
      Map kerning = new HashMap(64);
      List charDefs = new ArrayList(255);
      int maxChar = 0;
      boolean done = false;
      while (!done)
      {
        String line = local_in.readLine();
        if (line == null)
        {
          done = true;
        }
        else
        {
          if ((!line.startsWith("chars c")) && (line.startsWith("char")))
          {
            CharDef def = parseChar(line);
            if (def != null)
            {
              maxChar = Math.max(maxChar, def.field_1849);
              charDefs.add(def);
            }
          }
          if ((!line.startsWith("kernings c")) && (line.startsWith("kerning")))
          {
            StringTokenizer def = new StringTokenizer(line, " =");
            def.nextToken();
            def.nextToken();
            short first = Short.parseShort(def.nextToken());
            def.nextToken();
            int second = Integer.parseInt(def.nextToken());
            def.nextToken();
            int offset = Integer.parseInt(def.nextToken());
            List values = (List)kerning.get(new Short(first));
            if (values == null)
            {
              values = new ArrayList();
              kerning.put(new Short(first), values);
            }
            values.add(new Short((short)(offset << 8 | second)));
          }
        }
      }
      this.chars = new CharDef[maxChar + 1];
      Iterator line = charDefs.iterator();
      while (line.hasNext())
      {
        CharDef def = (CharDef)line.next();
        this.chars[def.field_1849] = def;
      }
      Iterator line = kerning.entrySet().iterator();
      while (line.hasNext())
      {
        Map.Entry def = (Map.Entry)line.next();
        short first = ((Short)def.getKey()).shortValue();
        List second = (List)def.getValue();
        short[] offset = new short[second.size()];
        int values = 0;
        Iterator valueIter = second.iterator();
        while (valueIter.hasNext())
        {
          offset[values] = ((Short)valueIter.next()).shortValue();
          values++;
        }
        this.chars[first].kerning = offset;
      }
    }
    catch (IOException local_in)
    {
      Log.error(local_in);
      throw new SlickException("Failed to parse font file: " + fntFile);
    }
  }
  
  private CharDef parseChar(String line)
    throws SlickException
  {
    CharDef def = new CharDef(null);
    StringTokenizer tokens = new StringTokenizer(line, " =");
    tokens.nextToken();
    tokens.nextToken();
    def.field_1849 = Short.parseShort(tokens.nextToken());
    if (def.field_1849 < 0) {
      return null;
    }
    if (def.field_1849 > 255) {
      throw new SlickException("Invalid character '" + def.field_1849 + "': AngelCodeFont does not support characters above " + 255);
    }
    tokens.nextToken();
    def.field_1850 = Short.parseShort(tokens.nextToken());
    tokens.nextToken();
    def.field_1851 = Short.parseShort(tokens.nextToken());
    tokens.nextToken();
    def.width = Short.parseShort(tokens.nextToken());
    tokens.nextToken();
    def.height = Short.parseShort(tokens.nextToken());
    tokens.nextToken();
    def.xoffset = Short.parseShort(tokens.nextToken());
    tokens.nextToken();
    def.yoffset = Short.parseShort(tokens.nextToken());
    tokens.nextToken();
    def.xadvance = Short.parseShort(tokens.nextToken());
    def.init();
    if (def.field_1849 != 32) {
      this.lineHeight = Math.max(def.height + def.yoffset, this.lineHeight);
    }
    return def;
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
    this.fontImage.bind();
    col.bind();
    field_2.glTranslatef(local_x, local_y, 0.0F);
    if ((this.displayListCaching) && (startIndex == 0) && (endIndex == text.length() - 1))
    {
      DisplayList displayList = (DisplayList)this.displayLists.get(text);
      if (displayList != null)
      {
        field_2.glCallList(displayList.field_2140);
      }
      else
      {
        displayList = new DisplayList(null);
        displayList.text = text;
        int displayListCount = this.displayLists.size();
        if (displayListCount < 200)
        {
          displayList.field_2140 = (this.baseDisplayListID + displayListCount);
        }
        else
        {
          displayList.field_2140 = this.eldestDisplayListID;
          this.displayLists.remove(this.eldestDisplayList.text);
        }
        this.displayLists.put(text, displayList);
        field_2.glNewList(displayList.field_2140, 4865);
        render(text, startIndex, endIndex);
        field_2.glEndList();
      }
    }
    else
    {
      render(text, startIndex, endIndex);
    }
    field_2.glTranslatef(-local_x, -local_y, 0.0F);
  }
  
  private void render(String text, int start, int end)
  {
    field_2.glBegin(7);
    int local_x = 0;
    int local_y = 0;
    CharDef lastCharDef = null;
    char[] data = text.toCharArray();
    for (int local_i = 0; local_i < data.length; local_i++)
    {
      int local_id = data[local_i];
      if (local_id == 10)
      {
        local_x = 0;
        local_y += getLineHeight();
      }
      else if (local_id < this.chars.length)
      {
        CharDef charDef = this.chars[local_id];
        if (charDef != null)
        {
          if (lastCharDef != null) {
            local_x += lastCharDef.getKerning(local_id);
          }
          lastCharDef = charDef;
          if ((local_i >= start) && (local_i <= end)) {
            charDef.draw(local_x, local_y);
          }
          local_x += charDef.xadvance;
        }
      }
    }
    field_2.glEnd();
  }
  
  public int getYOffset(String text)
  {
    DisplayList displayList = null;
    if (this.displayListCaching)
    {
      displayList = (DisplayList)this.displayLists.get(text);
      if ((displayList != null) && (displayList.yOffset != null)) {
        return displayList.yOffset.intValue();
      }
    }
    int stopIndex = text.indexOf('\n');
    if (stopIndex == -1) {
      stopIndex = text.length();
    }
    int minYOffset = 10000;
    for (int local_i = 0; local_i < stopIndex; local_i++)
    {
      int local_id = text.charAt(local_i);
      CharDef charDef = this.chars[local_id];
      if (charDef != null) {
        minYOffset = Math.min(charDef.yoffset, minYOffset);
      }
    }
    if (displayList != null) {
      displayList.yOffset = new Short((short)minYOffset);
    }
    return minYOffset;
  }
  
  public int getHeight(String text)
  {
    DisplayList displayList = null;
    if (this.displayListCaching)
    {
      displayList = (DisplayList)this.displayLists.get(text);
      if ((displayList != null) && (displayList.height != null)) {
        return displayList.height.intValue();
      }
    }
    int lines = 0;
    int maxHeight = 0;
    for (int local_i = 0; local_i < text.length(); local_i++)
    {
      int local_id = text.charAt(local_i);
      if (local_id == 10)
      {
        lines++;
        maxHeight = 0;
      }
      else if (local_id != 32)
      {
        CharDef charDef = this.chars[local_id];
        if (charDef != null) {
          maxHeight = Math.max(charDef.height + charDef.yoffset, maxHeight);
        }
      }
    }
    maxHeight += lines * getLineHeight();
    if (displayList != null) {
      displayList.height = new Short((short)maxHeight);
    }
    return maxHeight;
  }
  
  public int getWidth(String text)
  {
    DisplayList displayList = null;
    if (this.displayListCaching)
    {
      displayList = (DisplayList)this.displayLists.get(text);
      if ((displayList != null) && (displayList.width != null)) {
        return displayList.width.intValue();
      }
    }
    int maxWidth = 0;
    int width = 0;
    CharDef lastCharDef = null;
    int local_i = 0;
    int local_n = text.length();
    while (local_i < local_n)
    {
      int local_id = text.charAt(local_i);
      if (local_id == 10)
      {
        width = 0;
      }
      else if (local_id < this.chars.length)
      {
        CharDef charDef = this.chars[local_id];
        if (charDef != null)
        {
          if (lastCharDef != null) {
            width += lastCharDef.getKerning(local_id);
          }
          lastCharDef = charDef;
          if (local_i < local_n - 1) {
            width += charDef.xadvance;
          } else {
            width += charDef.width;
          }
          maxWidth = Math.max(maxWidth, width);
        }
      }
      local_i++;
    }
    if (displayList != null) {
      displayList.width = new Short((short)maxWidth);
    }
    return maxWidth;
  }
  
  public int getLineHeight()
  {
    return this.lineHeight;
  }
  
  private static class DisplayList
  {
    int field_2140;
    Short yOffset;
    Short width;
    Short height;
    String text;
  }
  
  private class CharDef
  {
    public short field_1849;
    public short field_1850;
    public short field_1851;
    public short width;
    public short height;
    public short xoffset;
    public short yoffset;
    public short xadvance;
    public Image image;
    public short dlIndex;
    public short[] kerning;
    
    private CharDef() {}
    
    public void init()
    {
      this.image = AngelCodeFont.this.fontImage.getSubImage(this.field_1850, this.field_1851, this.width, this.height);
    }
    
    public String toString()
    {
      return "[CharDef id=" + this.field_1849 + " x=" + this.field_1850 + " y=" + this.field_1851 + "]";
    }
    
    public void draw(float local_x, float local_y)
    {
      this.image.drawEmbedded(local_x + this.xoffset, local_y + this.yoffset, this.width, this.height);
    }
    
    public int getKerning(int otherCodePoint)
    {
      if (this.kerning == null) {
        return 0;
      }
      int low = 0;
      int high = this.kerning.length - 1;
      while (low <= high)
      {
        int midIndex = low + high >>> 1;
        int value = this.kerning[midIndex];
        int foundCodePoint = value & 0xFF;
        if (foundCodePoint < otherCodePoint) {
          low = midIndex + 1;
        } else if (foundCodePoint > otherCodePoint) {
          high = midIndex - 1;
        } else {
          return value >> 8;
        }
      }
      return 0;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.AngelCodeFont
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */