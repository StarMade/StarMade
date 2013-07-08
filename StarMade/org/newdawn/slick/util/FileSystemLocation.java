/*  1:   */package org.newdawn.slick.util;
/*  2:   */
/*  3:   */import java.io.File;
/*  4:   */import java.io.FileInputStream;
/*  5:   */import java.io.IOException;
/*  6:   */import java.io.InputStream;
/*  7:   */import java.net.URI;
/*  8:   */import java.net.URL;
/*  9:   */
/* 17:   */public class FileSystemLocation
/* 18:   */  implements ResourceLocation
/* 19:   */{
/* 20:   */  private File root;
/* 21:   */  
/* 22:   */  public FileSystemLocation(File root)
/* 23:   */  {
/* 24:24 */    this.root = root;
/* 25:   */  }
/* 26:   */  
/* 28:   */  public URL getResource(String ref)
/* 29:   */  {
/* 30:   */    try
/* 31:   */    {
/* 32:32 */      File file = new File(this.root, ref);
/* 33:33 */      if (!file.exists()) {
/* 34:34 */        file = new File(ref);
/* 35:   */      }
/* 36:36 */      if (!file.exists()) {
/* 37:37 */        return null;
/* 38:   */      }
/* 39:   */      
/* 40:40 */      return file.toURI().toURL();
/* 41:   */    } catch (IOException e) {}
/* 42:42 */    return null;
/* 43:   */  }
/* 44:   */  
/* 47:   */  public InputStream getResourceAsStream(String ref)
/* 48:   */  {
/* 49:   */    try
/* 50:   */    {
/* 51:51 */      File file = new File(this.root, ref);
/* 52:52 */      if (!file.exists()) {
/* 53:53 */        file = new File(ref);
/* 54:   */      }
/* 55:55 */      return new FileInputStream(file);
/* 56:   */    } catch (IOException e) {}
/* 57:57 */    return null;
/* 58:   */  }
/* 59:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.util.FileSystemLocation
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */