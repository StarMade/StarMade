/*   1:    */package org.newdawn.slick.util;
/*   2:    */
/*   3:    */import java.io.BufferedInputStream;
/*   4:    */import java.io.File;
/*   5:    */import java.io.InputStream;
/*   6:    */import java.net.URL;
/*   7:    */import java.util.ArrayList;
/*   8:    */
/*  15:    */public class ResourceLoader
/*  16:    */{
/*  17: 17 */  private static ArrayList locations = new ArrayList();
/*  18:    */  
/*  19:    */  static {
/*  20: 20 */    locations.add(new ClasspathLocation());
/*  21: 21 */    locations.add(new FileSystemLocation(new File(".")));
/*  22:    */  }
/*  23:    */  
/*  28:    */  public static void addResourceLocation(ResourceLocation location)
/*  29:    */  {
/*  30: 30 */    locations.add(location);
/*  31:    */  }
/*  32:    */  
/*  37:    */  public static void removeResourceLocation(ResourceLocation location)
/*  38:    */  {
/*  39: 39 */    locations.remove(location);
/*  40:    */  }
/*  41:    */  
/*  45:    */  public static void removeAllResourceLocations()
/*  46:    */  {
/*  47: 47 */    locations.clear();
/*  48:    */  }
/*  49:    */  
/*  55:    */  public static InputStream getResourceAsStream(String ref)
/*  56:    */  {
/*  57: 57 */    InputStream in = null;
/*  58:    */    
/*  59: 59 */    for (int i = 0; i < locations.size(); i++) {
/*  60: 60 */      ResourceLocation location = (ResourceLocation)locations.get(i);
/*  61: 61 */      in = location.getResourceAsStream(ref);
/*  62: 62 */      if (in != null) {
/*  63:    */        break;
/*  64:    */      }
/*  65:    */    }
/*  66:    */    
/*  67: 67 */    if (in == null)
/*  68:    */    {
/*  69: 69 */      throw new RuntimeException("Resource not found: " + ref);
/*  70:    */    }
/*  71:    */    
/*  72: 72 */    return new BufferedInputStream(in);
/*  73:    */  }
/*  74:    */  
/*  80:    */  public static boolean resourceExists(String ref)
/*  81:    */  {
/*  82: 82 */    URL url = null;
/*  83:    */    
/*  84: 84 */    for (int i = 0; i < locations.size(); i++) {
/*  85: 85 */      ResourceLocation location = (ResourceLocation)locations.get(i);
/*  86: 86 */      url = location.getResource(ref);
/*  87: 87 */      if (url != null) {
/*  88: 88 */        return true;
/*  89:    */      }
/*  90:    */    }
/*  91:    */    
/*  92: 92 */    return false;
/*  93:    */  }
/*  94:    */  
/* 101:    */  public static URL getResource(String ref)
/* 102:    */  {
/* 103:103 */    URL url = null;
/* 104:    */    
/* 105:105 */    for (int i = 0; i < locations.size(); i++) {
/* 106:106 */      ResourceLocation location = (ResourceLocation)locations.get(i);
/* 107:107 */      url = location.getResource(ref);
/* 108:108 */      if (url != null) {
/* 109:    */        break;
/* 110:    */      }
/* 111:    */    }
/* 112:    */    
/* 113:113 */    if (url == null)
/* 114:    */    {
/* 115:115 */      throw new RuntimeException("Resource not found: " + ref);
/* 116:    */    }
/* 117:    */    
/* 118:118 */    return url;
/* 119:    */  }
/* 120:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.util.ResourceLoader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */