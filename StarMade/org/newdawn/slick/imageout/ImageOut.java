/*   1:    */package org.newdawn.slick.imageout;
/*   2:    */
/*   3:    */import java.io.FileOutputStream;
/*   4:    */import java.io.IOException;
/*   5:    */import java.io.OutputStream;
/*   6:    */import org.newdawn.slick.Image;
/*   7:    */import org.newdawn.slick.SlickException;
/*   8:    */
/*  19:    */public class ImageOut
/*  20:    */{
/*  21:    */  private static final boolean DEFAULT_ALPHA_WRITE = false;
/*  22: 22 */  public static String TGA = "tga";
/*  23:    */  
/*  24: 24 */  public static String PNG = "png";
/*  25:    */  
/*  26: 26 */  public static String JPG = "jpg";
/*  27:    */  
/*  33:    */  public static String[] getSupportedFormats()
/*  34:    */  {
/*  35: 35 */    return ImageWriterFactory.getSupportedFormats();
/*  36:    */  }
/*  37:    */  
/*  44:    */  public static void write(Image image, String format, OutputStream out)
/*  45:    */    throws SlickException
/*  46:    */  {
/*  47: 47 */    write(image, format, out, false);
/*  48:    */  }
/*  49:    */  
/*  56:    */  public static void write(Image image, String format, OutputStream out, boolean writeAlpha)
/*  57:    */    throws SlickException
/*  58:    */  {
/*  59:    */    try
/*  60:    */    {
/*  61: 61 */      ImageWriter writer = ImageWriterFactory.getWriterForFormat(format);
/*  62: 62 */      writer.saveImage(image, format, out, writeAlpha);
/*  63:    */    } catch (IOException e) {
/*  64: 64 */      throw new SlickException("Unable to write out the image in format: " + format, e);
/*  65:    */    }
/*  66:    */  }
/*  67:    */  
/*  74:    */  public static void write(Image image, String dest)
/*  75:    */    throws SlickException
/*  76:    */  {
/*  77: 77 */    write(image, dest, false);
/*  78:    */  }
/*  79:    */  
/*  86:    */  public static void write(Image image, String dest, boolean writeAlpha)
/*  87:    */    throws SlickException
/*  88:    */  {
/*  89:    */    try
/*  90:    */    {
/*  91: 91 */      int ext = dest.lastIndexOf('.');
/*  92: 92 */      if (ext < 0) {
/*  93: 93 */        throw new SlickException("Unable to determine format from: " + dest);
/*  94:    */      }
/*  95:    */      
/*  96: 96 */      String format = dest.substring(ext + 1);
/*  97: 97 */      write(image, format, new FileOutputStream(dest), writeAlpha);
/*  98:    */    } catch (IOException e) {
/*  99: 99 */      throw new SlickException("Unable to write to the destination: " + dest, e);
/* 100:    */    }
/* 101:    */  }
/* 102:    */  
/* 109:    */  public static void write(Image image, String format, String dest)
/* 110:    */    throws SlickException
/* 111:    */  {
/* 112:112 */    write(image, format, dest, false);
/* 113:    */  }
/* 114:    */  
/* 121:    */  public static void write(Image image, String format, String dest, boolean writeAlpha)
/* 122:    */    throws SlickException
/* 123:    */  {
/* 124:    */    try
/* 125:    */    {
/* 126:126 */      write(image, format, new FileOutputStream(dest), writeAlpha);
/* 127:    */    } catch (IOException e) {
/* 128:128 */      throw new SlickException("Unable to write to the destination: " + dest, e);
/* 129:    */    }
/* 130:    */  }
/* 131:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.imageout.ImageOut
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */