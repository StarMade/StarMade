/*   1:    */package org.newdawn.slick;
/*   2:    */
/*   3:    */import java.io.BufferedReader;
/*   4:    */import java.io.IOException;
/*   5:    */import java.io.InputStreamReader;
/*   6:    */import java.util.HashMap;
/*   7:    */import org.newdawn.slick.util.Log;
/*   8:    */import org.newdawn.slick.util.ResourceLoader;
/*   9:    */
/*  21:    */public class PackedSpriteSheet
/*  22:    */{
/*  23:    */  private Image image;
/*  24:    */  private String basePath;
/*  25: 25 */  private HashMap sections = new HashMap();
/*  26:    */  
/*  27: 27 */  private int filter = 2;
/*  28:    */  
/*  33:    */  public PackedSpriteSheet(String def)
/*  34:    */    throws SlickException
/*  35:    */  {
/*  36: 36 */    this(def, null);
/*  37:    */  }
/*  38:    */  
/*  44:    */  public PackedSpriteSheet(String def, Color trans)
/*  45:    */    throws SlickException
/*  46:    */  {
/*  47: 47 */    def = def.replace('\\', '/');
/*  48: 48 */    this.basePath = def.substring(0, def.lastIndexOf("/") + 1);
/*  49:    */    
/*  50: 50 */    loadDefinition(def, trans);
/*  51:    */  }
/*  52:    */  
/*  58:    */  public PackedSpriteSheet(String def, int filter)
/*  59:    */    throws SlickException
/*  60:    */  {
/*  61: 61 */    this(def, filter, null);
/*  62:    */  }
/*  63:    */  
/*  70:    */  public PackedSpriteSheet(String def, int filter, Color trans)
/*  71:    */    throws SlickException
/*  72:    */  {
/*  73: 73 */    this.filter = filter;
/*  74:    */    
/*  75: 75 */    def = def.replace('\\', '/');
/*  76: 76 */    this.basePath = def.substring(0, def.lastIndexOf("/") + 1);
/*  77:    */    
/*  78: 78 */    loadDefinition(def, trans);
/*  79:    */  }
/*  80:    */  
/*  85:    */  public Image getFullImage()
/*  86:    */  {
/*  87: 87 */    return this.image;
/*  88:    */  }
/*  89:    */  
/*  95:    */  public Image getSprite(String name)
/*  96:    */  {
/*  97: 97 */    Section section = (Section)this.sections.get(name);
/*  98:    */    
/*  99: 99 */    if (section == null) {
/* 100:100 */      throw new RuntimeException("Unknown sprite from packed sheet: " + name);
/* 101:    */    }
/* 102:    */    
/* 103:103 */    return this.image.getSubImage(section.x, section.y, section.width, section.height);
/* 104:    */  }
/* 105:    */  
/* 111:    */  public SpriteSheet getSpriteSheet(String name)
/* 112:    */  {
/* 113:113 */    Image image = getSprite(name);
/* 114:114 */    Section section = (Section)this.sections.get(name);
/* 115:    */    
/* 116:116 */    return new SpriteSheet(image, section.width / section.tilesx, section.height / section.tilesy);
/* 117:    */  }
/* 118:    */  
/* 125:    */  private void loadDefinition(String def, Color trans)
/* 126:    */    throws SlickException
/* 127:    */  {
/* 128:128 */    BufferedReader reader = new BufferedReader(new InputStreamReader(ResourceLoader.getResourceAsStream(def)));
/* 129:    */    try
/* 130:    */    {
/* 131:131 */      this.image = new Image(this.basePath + reader.readLine(), false, this.filter, trans);
/* 132:132 */      while ((reader.ready()) && 
/* 133:133 */        (reader.readLine() != null))
/* 134:    */      {
/* 137:137 */        Section sect = new Section(reader);
/* 138:138 */        this.sections.put(sect.name, sect);
/* 139:    */        
/* 140:140 */        if (reader.readLine() == null) {
/* 141:    */          break;
/* 142:    */        }
/* 143:    */      }
/* 144:    */    } catch (Exception e) {
/* 145:145 */      Log.error(e);
/* 146:146 */      throw new SlickException("Failed to process definitions file - invalid format?", e);
/* 147:    */    }
/* 148:    */  }
/* 149:    */  
/* 152:    */  private class Section
/* 153:    */  {
/* 154:    */    public int x;
/* 155:    */    
/* 157:    */    public int y;
/* 158:    */    
/* 160:    */    public int width;
/* 161:    */    
/* 163:    */    public int height;
/* 164:    */    
/* 166:    */    public int tilesx;
/* 167:    */    
/* 169:    */    public int tilesy;
/* 170:    */    
/* 172:    */    public String name;
/* 173:    */    
/* 175:    */    public Section(BufferedReader reader)
/* 176:    */      throws IOException
/* 177:    */    {
/* 178:178 */      this.name = reader.readLine().trim();
/* 179:    */      
/* 180:180 */      this.x = Integer.parseInt(reader.readLine().trim());
/* 181:181 */      this.y = Integer.parseInt(reader.readLine().trim());
/* 182:182 */      this.width = Integer.parseInt(reader.readLine().trim());
/* 183:183 */      this.height = Integer.parseInt(reader.readLine().trim());
/* 184:184 */      this.tilesx = Integer.parseInt(reader.readLine().trim());
/* 185:185 */      this.tilesy = Integer.parseInt(reader.readLine().trim());
/* 186:186 */      reader.readLine().trim();
/* 187:187 */      reader.readLine().trim();
/* 188:    */      
/* 189:189 */      this.tilesx = Math.max(1, this.tilesx);
/* 190:190 */      this.tilesy = Math.max(1, this.tilesy);
/* 191:    */    }
/* 192:    */  }
/* 193:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.PackedSpriteSheet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */