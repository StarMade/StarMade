/*   1:    */package org.lwjgl.util;
/*   2:    */
/*   3:    */import java.io.PrintStream;
/*   4:    */import java.util.ArrayList;
/*   5:    */import java.util.Arrays;
/*   6:    */import java.util.Comparator;
/*   7:    */import org.lwjgl.LWJGLException;
/*   8:    */import org.lwjgl.LWJGLUtil;
/*   9:    */import org.lwjgl.opengl.DisplayMode;
/*  10:    */
/*  64:    */public final class Display
/*  65:    */{
/*  66:    */  private static final boolean DEBUG = false;
/*  67:    */  
/*  68:    */  public static DisplayMode[] getAvailableDisplayModes(int minWidth, int minHeight, int maxWidth, int maxHeight, int minBPP, int maxBPP, int minFreq, int maxFreq)
/*  69:    */    throws LWJGLException
/*  70:    */  {
/*  71: 71 */    DisplayMode[] modes = org.lwjgl.opengl.Display.getAvailableDisplayModes();
/*  72:    */    
/*  73: 73 */    if (LWJGLUtil.DEBUG) {
/*  74: 74 */      System.out.println("Available screen modes:");
/*  75: 75 */      for (DisplayMode mode : modes) {
/*  76: 76 */        System.out.println(mode);
/*  77:    */      }
/*  78:    */    }
/*  79:    */    
/*  80: 80 */    ArrayList<DisplayMode> matches = new ArrayList(modes.length);
/*  81:    */    
/*  82: 82 */    for (int i = 0; i < modes.length; i++) {
/*  83: 83 */      assert (modes[i] != null) : ("" + i + " " + modes.length);
/*  84: 84 */      if ((minWidth == -1) || (modes[i].getWidth() >= minWidth))
/*  85:    */      {
/*  86: 86 */        if ((maxWidth == -1) || (modes[i].getWidth() <= maxWidth))
/*  87:    */        {
/*  88: 88 */          if ((minHeight == -1) || (modes[i].getHeight() >= minHeight))
/*  89:    */          {
/*  90: 90 */            if ((maxHeight == -1) || (modes[i].getHeight() <= maxHeight))
/*  91:    */            {
/*  92: 92 */              if ((minBPP == -1) || (modes[i].getBitsPerPixel() >= minBPP))
/*  93:    */              {
/*  94: 94 */                if ((maxBPP == -1) || (modes[i].getBitsPerPixel() <= maxBPP))
/*  95:    */                {
/*  98: 98 */                  if (modes[i].getFrequency() != 0) {
/*  99: 99 */                    if ((minFreq == -1) || (modes[i].getFrequency() >= minFreq))
/* 100:    */                    {
/* 101:101 */                      if ((maxFreq != -1) && (modes[i].getFrequency() > maxFreq)) {}
/* 102:    */                    }
/* 103:    */                  } else
/* 104:104 */                    matches.add(modes[i]); } } } } }
/* 105:    */      }
/* 106:    */    }
/* 107:107 */    DisplayMode[] ret = new DisplayMode[matches.size()];
/* 108:108 */    matches.toArray(ret);
/* 109:109 */    if (LWJGLUtil.DEBUG) {}
/* 110:    */    
/* 116:116 */    return ret;
/* 117:    */  }
/* 118:    */  
/* 214:    */  public static DisplayMode setDisplayMode(DisplayMode[] dm, String[] param)
/* 215:    */    throws Exception
/* 216:    */  {
/* 217:217 */    Arrays.sort(dm, new Comparator()
/* 218:    */    {
/* 219:    */      final Display.1FieldAccessor[] accessors;
/* 220:    */      
/* 221:    */      public int compare(DisplayMode dm1, DisplayMode dm2)
/* 222:    */      {
/* 223:184 */        for (Display.1FieldAccessor accessor : this.accessors) {
/* 224:185 */          int f1 = accessor.getInt(dm1);
/* 225:186 */          int f2 = accessor.getInt(dm2);
/* 226:    */          
/* 227:188 */          if ((accessor.usePreferred) && (f1 != f2)) {
/* 228:189 */            if (f1 == accessor.preferred)
/* 229:190 */              return -1;
/* 230:191 */            if (f2 == accessor.preferred) {
/* 231:192 */              return 1;
/* 232:    */            }
/* 233:    */            
/* 234:195 */            int absf1 = Math.abs(f1 - accessor.preferred);
/* 235:196 */            int absf2 = Math.abs(f2 - accessor.preferred);
/* 236:197 */            if (absf1 < absf2)
/* 237:198 */              return -1;
/* 238:199 */            if (absf1 > absf2) {
/* 239:200 */              return 1;
/* 240:    */            }
/* 241:    */          }
/* 242:    */          else {
/* 243:204 */            if (f1 < f2)
/* 244:205 */              return accessor.order;
/* 245:206 */            if (f1 != f2)
/* 246:    */            {
/* 248:209 */              return -accessor.order; }
/* 249:    */          }
/* 250:    */        }
/* 251:212 */        return 0;
/* 252:    */      }
/* 253:    */    });
/* 254:    */    
/* 259:220 */    if (LWJGLUtil.DEBUG) {
/* 260:221 */      System.out.println("Sorted display modes:");
/* 261:222 */      for (DisplayMode aDm : dm) {
/* 262:223 */        System.out.println(aDm);
/* 263:    */      }
/* 264:    */    }
/* 265:226 */    for (DisplayMode aDm : dm) {
/* 266:    */      try {
/* 267:228 */        if (LWJGLUtil.DEBUG)
/* 268:229 */          System.out.println("Attempting to set displaymode: " + aDm);
/* 269:230 */        org.lwjgl.opengl.Display.setDisplayMode(aDm);
/* 270:231 */        return aDm;
/* 271:    */      } catch (Exception e) {
/* 272:233 */        if (LWJGLUtil.DEBUG) {
/* 273:234 */          System.out.println("Failed to set display mode to " + aDm);
/* 274:235 */          e.printStackTrace();
/* 275:    */        }
/* 276:    */      }
/* 277:    */    }
/* 278:    */    
/* 279:240 */    throw new Exception("Failed to set display mode.");
/* 280:    */  }
/* 281:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.Display
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */