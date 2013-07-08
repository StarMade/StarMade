/*  1:   */package org.newdawn.slick.util;
/*  2:   */
/*  3:   */import java.io.InputStream;
/*  4:   */import java.net.URL;
/*  5:   */
/* 11:   */public class ClasspathLocation
/* 12:   */  implements ResourceLocation
/* 13:   */{
/* 14:   */  public URL getResource(String ref)
/* 15:   */  {
/* 16:16 */    String cpRef = ref.replace('\\', '/');
/* 17:17 */    return ResourceLoader.class.getClassLoader().getResource(cpRef);
/* 18:   */  }
/* 19:   */  
/* 22:   */  public InputStream getResourceAsStream(String ref)
/* 23:   */  {
/* 24:24 */    String cpRef = ref.replace('\\', '/');
/* 25:25 */    return ResourceLoader.class.getClassLoader().getResourceAsStream(cpRef);
/* 26:   */  }
/* 27:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.util.ClasspathLocation
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */