/*  1:   */package org.newdawn.slick.imageout;
/*  2:   */
/*  3:   */import java.awt.Point;
/*  4:   */import java.awt.color.ColorSpace;
/*  5:   */import java.awt.image.BufferedImage;
/*  6:   */import java.awt.image.ColorModel;
/*  7:   */import java.awt.image.ComponentColorModel;
/*  8:   */import java.awt.image.DataBufferByte;
/*  9:   */import java.awt.image.PixelInterleavedSampleModel;
/* 10:   */import java.awt.image.Raster;
/* 11:   */import java.awt.image.WritableRaster;
/* 12:   */import java.io.IOException;
/* 13:   */import java.io.OutputStream;
/* 14:   */import java.nio.ByteBuffer;
/* 15:   */import javax.imageio.ImageIO;
/* 16:   */import org.newdawn.slick.Color;
/* 17:   */import org.newdawn.slick.Image;
/* 18:   */
/* 29:   */public class ImageIOWriter
/* 30:   */  implements ImageWriter
/* 31:   */{
/* 32:   */  public void saveImage(Image image, String format, OutputStream output, boolean hasAlpha)
/* 33:   */    throws IOException
/* 34:   */  {
/* 35:35 */    int len = 4 * image.getWidth() * image.getHeight();
/* 36:36 */    if (!hasAlpha) {
/* 37:37 */      len = 3 * image.getWidth() * image.getHeight();
/* 38:   */    }
/* 39:   */    
/* 40:40 */    ByteBuffer out = ByteBuffer.allocate(len);
/* 41:   */    
/* 43:43 */    for (int y = 0; y < image.getHeight(); y++) {
/* 44:44 */      for (int x = 0; x < image.getWidth(); x++) {
/* 45:45 */        Color c = image.getColor(x, y);
/* 46:   */        
/* 47:47 */        out.put((byte)(int)(c.r * 255.0F));
/* 48:48 */        out.put((byte)(int)(c.g * 255.0F));
/* 49:49 */        out.put((byte)(int)(c.b * 255.0F));
/* 50:50 */        if (hasAlpha) {
/* 51:51 */          out.put((byte)(int)(c.a * 255.0F));
/* 52:   */        }
/* 53:   */      }
/* 54:   */    }
/* 55:   */    
/* 57:57 */    DataBufferByte dataBuffer = new DataBufferByte(out.array(), len);
/* 58:   */    
/* 59:   */    ColorModel cm;
/* 60:   */    
/* 61:   */    PixelInterleavedSampleModel sampleModel;
/* 62:   */    ColorModel cm;
/* 63:63 */    if (hasAlpha) {
/* 64:64 */      int[] offsets = { 0, 1, 2, 3 };
/* 65:65 */      PixelInterleavedSampleModel sampleModel = new PixelInterleavedSampleModel(0, image.getWidth(), image.getHeight(), 4, 4 * image.getWidth(), offsets);
/* 66:   */      
/* 69:69 */      cm = new ComponentColorModel(ColorSpace.getInstance(1000), new int[] { 8, 8, 8, 8 }, true, false, 3, 0);
/* 71:   */    }
/* 72:   */    else
/* 73:   */    {
/* 74:74 */      int[] offsets = { 0, 1, 2 };
/* 75:75 */      sampleModel = new PixelInterleavedSampleModel(0, image.getWidth(), image.getHeight(), 3, 3 * image.getWidth(), offsets);
/* 76:   */      
/* 79:79 */      cm = new ComponentColorModel(ColorSpace.getInstance(1000), new int[] { 8, 8, 8, 0 }, false, false, 1, 0);
/* 80:   */    }
/* 81:   */    
/* 86:86 */    WritableRaster raster = Raster.createWritableRaster(sampleModel, dataBuffer, new Point(0, 0));
/* 87:   */    
/* 90:90 */    BufferedImage img = new BufferedImage(cm, raster, false, null);
/* 91:   */    
/* 92:92 */    ImageIO.write(img, format, output);
/* 93:   */  }
/* 94:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.imageout.ImageIOWriter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */