/*  1:   */package org.newdawn.slick.imageout;
/*  2:   */
/*  3:   */import java.util.HashMap;
/*  4:   */import java.util.Set;
/*  5:   */import javax.imageio.ImageIO;
/*  6:   */import org.newdawn.slick.SlickException;
/*  7:   */
/* 14:   */public class ImageWriterFactory
/* 15:   */{
/* 16:16 */  private static HashMap writers = new HashMap();
/* 17:   */  
/* 18:   */  static
/* 19:   */  {
/* 20:20 */    String[] formats = ImageIO.getWriterFormatNames();
/* 21:21 */    ImageIOWriter writer = new ImageIOWriter();
/* 22:22 */    for (int i = 0; i < formats.length; i++) {
/* 23:23 */      registerWriter(formats[i], writer);
/* 24:   */    }
/* 25:   */    
/* 26:26 */    TGAWriter tga = new TGAWriter();
/* 27:27 */    registerWriter("tga", tga);
/* 28:   */  }
/* 29:   */  
/* 36:   */  public static void registerWriter(String format, ImageWriter writer)
/* 37:   */  {
/* 38:38 */    writers.put(format, writer);
/* 39:   */  }
/* 40:   */  
/* 45:   */  public static String[] getSupportedFormats()
/* 46:   */  {
/* 47:47 */    return (String[])writers.keySet().toArray(new String[0]);
/* 48:   */  }
/* 49:   */  
/* 56:   */  public static ImageWriter getWriterForFormat(String format)
/* 57:   */    throws SlickException
/* 58:   */  {
/* 59:59 */    ImageWriter writer = (ImageWriter)writers.get(format);
/* 60:60 */    if (writer != null) {
/* 61:61 */      return writer;
/* 62:   */    }
/* 63:   */    
/* 64:64 */    writer = (ImageWriter)writers.get(format.toLowerCase());
/* 65:65 */    if (writer != null) {
/* 66:66 */      return writer;
/* 67:   */    }
/* 68:   */    
/* 69:69 */    writer = (ImageWriter)writers.get(format.toUpperCase());
/* 70:70 */    if (writer != null) {
/* 71:71 */      return writer;
/* 72:   */    }
/* 73:   */    
/* 74:74 */    throw new SlickException("No image writer available for: " + format);
/* 75:   */  }
/* 76:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.imageout.ImageWriterFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */