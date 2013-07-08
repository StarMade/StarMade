package org.newdawn.slick.imageout;

import java.util.HashMap;
import java.util.Set;
import javax.imageio.ImageIO;
import org.newdawn.slick.SlickException;

public class ImageWriterFactory
{
  private static HashMap writers = new HashMap();
  
  public static void registerWriter(String format, ImageWriter writer)
  {
    writers.put(format, writer);
  }
  
  public static String[] getSupportedFormats()
  {
    return (String[])writers.keySet().toArray(new String[0]);
  }
  
  public static ImageWriter getWriterForFormat(String format)
    throws SlickException
  {
    ImageWriter writer = (ImageWriter)writers.get(format);
    if (writer != null) {
      return writer;
    }
    writer = (ImageWriter)writers.get(format.toLowerCase());
    if (writer != null) {
      return writer;
    }
    writer = (ImageWriter)writers.get(format.toUpperCase());
    if (writer != null) {
      return writer;
    }
    throw new SlickException("No image writer available for: " + format);
  }
  
  static
  {
    String[] formats = ImageIO.getWriterFormatNames();
    ImageIOWriter writer = new ImageIOWriter();
    for (int local_i = 0; local_i < formats.length; local_i++) {
      registerWriter(formats[local_i], writer);
    }
    TGAWriter local_i = new TGAWriter();
    registerWriter("tga", local_i);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.imageout.ImageWriterFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */