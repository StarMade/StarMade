/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.io.BufferedReader;
/*   4:    */import java.io.IOException;
/*   5:    */import java.io.InputStreamReader;
/*   6:    */import java.util.ArrayList;
/*   7:    */import java.util.HashMap;
/*   8:    */import java.util.List;
/*   9:    */import java.util.Map;
/*  10:    */import java.util.Set;
/*  11:    */import java.util.regex.Matcher;
/*  12:    */import java.util.regex.Pattern;
/*  13:    */import org.lwjgl.LWJGLUtil;
/*  14:    */
/*  49:    */public class XRandR
/*  50:    */{
/*  51:    */  private static Screen[] current;
/*  52:    */  private static Map<String, Screen[]> screens;
/*  53:    */  
/*  54:    */  private static void populate()
/*  55:    */  {
/*  56: 56 */    if (screens == null)
/*  57:    */    {
/*  58: 58 */      screens = new HashMap();
/*  59:    */      
/*  63:    */      try
/*  64:    */      {
/*  65: 65 */        Process p = Runtime.getRuntime().exec(new String[] { "xrandr", "-q" });
/*  66:    */        
/*  67: 67 */        List<Screen> currentList = new ArrayList();
/*  68: 68 */        List<Screen> possibles = new ArrayList();
/*  69: 69 */        String name = null;
/*  70:    */        
/*  71: 71 */        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
/*  72:    */        String line;
/*  73: 73 */        while ((line = br.readLine()) != null)
/*  74:    */        {
/*  75: 75 */          line = line.trim();
/*  76: 76 */          String[] sa = line.split("\\s+");
/*  77:    */          
/*  78: 78 */          if ("connected".equals(sa[1]))
/*  79:    */          {
/*  81: 81 */            if (name != null)
/*  82:    */            {
/*  83: 83 */              screens.put(name, possibles.toArray(new Screen[possibles.size()]));
/*  84: 84 */              possibles.clear();
/*  85:    */            }
/*  86: 86 */            name = sa[0];
/*  87:    */            
/*  89: 89 */            parseScreen(currentList, name, sa[2]);
/*  90:    */          }
/*  91: 91 */          else if (Pattern.matches("\\d*x\\d*", sa[0]))
/*  92:    */          {
/*  94: 94 */            parseScreen(possibles, name, sa[0]);
/*  95:    */          }
/*  96:    */        }
/*  97:    */        
/*  98: 98 */        screens.put(name, possibles.toArray(new Screen[possibles.size()]));
/*  99:    */        
/* 100:100 */        current = (Screen[])currentList.toArray(new Screen[currentList.size()]);
/* 101:    */      }
/* 102:    */      catch (Throwable e)
/* 103:    */      {
/* 104:104 */        LWJGLUtil.log("Exception in XRandR.populate(): " + e.getMessage());
/* 105:105 */        screens.clear();
/* 106:106 */        current = new Screen[0];
/* 107:    */      }
/* 108:    */    }
/* 109:    */  }
/* 110:    */  
/* 115:    */  public static Screen[] getConfiguration()
/* 116:    */  {
/* 117:117 */    populate();
/* 118:    */    
/* 119:119 */    return (Screen[])current.clone();
/* 120:    */  }
/* 121:    */  
/* 128:    */  public static void setConfiguration(Screen... screens)
/* 129:    */  {
/* 130:130 */    if (screens.length == 0) {
/* 131:131 */      throw new IllegalArgumentException("Must specify at least one screen");
/* 132:    */    }
/* 133:133 */    List<String> cmd = new ArrayList();
/* 134:134 */    cmd.add("xrandr");
/* 135:    */    
/* 137:137 */    for (Screen screen : current) {
/* 138:138 */      boolean found = false;
/* 139:139 */      for (Screen screen1 : screens) {
/* 140:140 */        if (screen1.name.equals(screen.name)) {
/* 141:141 */          found = true;
/* 142:142 */          break;
/* 143:    */        }
/* 144:    */      }
/* 145:    */      
/* 146:146 */      if (!found) {
/* 147:147 */        cmd.add("--output");
/* 148:148 */        cmd.add(screen.name);
/* 149:149 */        cmd.add("--off");
/* 150:    */      }
/* 151:    */    }
/* 152:    */    
/* 154:154 */    for (Screen screen : screens) {
/* 155:155 */      screen.getArgs(cmd);
/* 156:    */    }
/* 157:    */    
/* 160:    */    try
/* 161:    */    {
/* 162:162 */      Process p = Runtime.getRuntime().exec((String[])cmd.toArray(new String[cmd.size()]));
/* 163:    */      
/* 165:165 */      BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
/* 166:    */      String line;
/* 167:167 */      while ((line = br.readLine()) != null)
/* 168:    */      {
/* 169:169 */        LWJGLUtil.log("Unexpected output from xrandr process: " + line);
/* 170:    */      }
/* 171:171 */      current = screens;
/* 172:    */    }
/* 173:    */    catch (IOException e)
/* 174:    */    {
/* 175:175 */      LWJGLUtil.log("XRandR exception in setConfiguration(): " + e.getMessage());
/* 176:    */    }
/* 177:    */  }
/* 178:    */  
/* 183:    */  public static String[] getScreenNames()
/* 184:    */  {
/* 185:185 */    populate();
/* 186:186 */    return (String[])screens.keySet().toArray(new String[screens.size()]);
/* 187:    */  }
/* 188:    */  
/* 194:    */  public static Screen[] getResolutions(String name)
/* 195:    */  {
/* 196:196 */    populate();
/* 197:    */    
/* 198:198 */    return (Screen[])((Screen[])screens.get(name)).clone();
/* 199:    */  }
/* 200:    */  
/* 201:201 */  private static final Pattern SCREEN_PATTERN1 = Pattern.compile("^(\\d+)x(\\d+)\\+(\\d+)\\+(\\d+)$");
/* 202:    */  
/* 204:204 */  private static final Pattern SCREEN_PATTERN2 = Pattern.compile("^(\\d+)x(\\d+)$");
/* 205:    */  
/* 218:    */  private static void parseScreen(List<Screen> list, String name, String what)
/* 219:    */  {
/* 220:220 */    Matcher m = SCREEN_PATTERN1.matcher(what);
/* 221:221 */    if (!m.matches())
/* 222:    */    {
/* 223:223 */      m = SCREEN_PATTERN2.matcher(what);
/* 224:224 */      if (!m.matches())
/* 225:    */      {
/* 226:226 */        LWJGLUtil.log("Did not match: " + what);
/* 227:227 */        return;
/* 228:    */      }
/* 229:    */    }
/* 230:230 */    int width = Integer.parseInt(m.group(1));
/* 231:231 */    int height = Integer.parseInt(m.group(2));
/* 232:    */    int ypos;
/* 233:233 */    int xpos; int ypos; if (m.groupCount() > 3)
/* 234:    */    {
/* 235:235 */      int xpos = Integer.parseInt(m.group(3));
/* 236:236 */      ypos = Integer.parseInt(m.group(4));
/* 237:    */    }
/* 238:    */    else
/* 239:    */    {
/* 240:240 */      xpos = 0;
/* 241:241 */      ypos = 0;
/* 242:    */    }
/* 243:243 */    list.add(new Screen(name, width, height, xpos, ypos, null));
/* 244:    */  }
/* 245:    */  
/* 250:    */  public static class Screen
/* 251:    */    implements Cloneable
/* 252:    */  {
/* 253:    */    public final String name;
/* 254:    */    
/* 258:    */    public final int width;
/* 259:    */    
/* 263:    */    public final int height;
/* 264:    */    
/* 268:    */    public int xPos;
/* 269:    */    
/* 273:    */    public int yPos;
/* 274:    */    
/* 279:    */    private Screen(String name, int width, int height, int xPos, int yPos)
/* 280:    */    {
/* 281:281 */      this.name = name;
/* 282:282 */      this.width = width;
/* 283:283 */      this.height = height;
/* 284:284 */      this.xPos = xPos;
/* 285:285 */      this.yPos = yPos;
/* 286:    */    }
/* 287:    */    
/* 288:    */    private void getArgs(List<String> argList)
/* 289:    */    {
/* 290:290 */      argList.add("--output");
/* 291:291 */      argList.add(this.name);
/* 292:292 */      argList.add("--mode");
/* 293:293 */      argList.add(this.width + "x" + this.height);
/* 294:294 */      argList.add("--pos");
/* 295:295 */      argList.add(this.xPos + "x" + this.yPos);
/* 296:    */    }
/* 297:    */    
/* 299:    */    public String toString()
/* 300:    */    {
/* 301:301 */      return this.name + " " + this.width + "x" + this.height + " @ " + this.xPos + "x" + this.yPos;
/* 302:    */    }
/* 303:    */  }
/* 304:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.XRandR
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */