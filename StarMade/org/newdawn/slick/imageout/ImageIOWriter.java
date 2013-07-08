package org.newdawn.slick.imageout;

import java.awt.Point;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBufferByte;
import java.awt.image.PixelInterleavedSampleModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;

public class ImageIOWriter
  implements ImageWriter
{
  public void saveImage(Image image, String format, OutputStream output, boolean hasAlpha)
    throws IOException
  {
    int len = 4 * image.getWidth() * image.getHeight();
    if (!hasAlpha) {
      len = 3 * image.getWidth() * image.getHeight();
    }
    ByteBuffer out = ByteBuffer.allocate(len);
    for (int local_y = 0; local_y < image.getHeight(); local_y++) {
      for (int local_x = 0; local_x < image.getWidth(); local_x++)
      {
        Color local_c = image.getColor(local_x, local_y);
        out.put((byte)(int)(local_c.field_1776 * 255.0F));
        out.put((byte)(int)(local_c.field_1777 * 255.0F));
        out.put((byte)(int)(local_c.field_1778 * 255.0F));
        if (hasAlpha) {
          out.put((byte)(int)(local_c.field_1779 * 255.0F));
        }
      }
    }
    DataBufferByte local_y = new DataBufferByte(out.array(), len);
    ColorModel local_cm;
    PixelInterleavedSampleModel local_x;
    ColorModel local_cm;
    if (hasAlpha)
    {
      int[] offsets = { 0, 1, 2, 3 };
      PixelInterleavedSampleModel local_x = new PixelInterleavedSampleModel(0, image.getWidth(), image.getHeight(), 4, 4 * image.getWidth(), offsets);
      local_cm = new ComponentColorModel(ColorSpace.getInstance(1000), new int[] { 8, 8, 8, 8 }, true, false, 3, 0);
    }
    else
    {
      int[] offsets = { 0, 1, 2 };
      local_x = new PixelInterleavedSampleModel(0, image.getWidth(), image.getHeight(), 3, 3 * image.getWidth(), offsets);
      local_cm = new ComponentColorModel(ColorSpace.getInstance(1000), new int[] { 8, 8, 8, 0 }, false, false, 1, 0);
    }
    WritableRaster offsets = Raster.createWritableRaster(local_x, local_y, new Point(0, 0));
    BufferedImage img = new BufferedImage(local_cm, offsets, false, null);
    ImageIO.write(img, format, output);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.imageout.ImageIOWriter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */