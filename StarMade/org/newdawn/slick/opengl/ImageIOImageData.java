package org.newdawn.slick.opengl;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Hashtable;
import javax.imageio.ImageIO;

public class ImageIOImageData
  implements LoadableImageData
{
  private static final ColorModel glAlphaColorModel = new ComponentColorModel(ColorSpace.getInstance(1000), new int[] { 8, 8, 8, 8 }, true, false, 3, 0);
  private static final ColorModel glColorModel = new ComponentColorModel(ColorSpace.getInstance(1000), new int[] { 8, 8, 8, 0 }, false, false, 1, 0);
  private int depth;
  private int height;
  private int width;
  private int texWidth;
  private int texHeight;
  private boolean edging = true;
  
  public int getDepth()
  {
    return this.depth;
  }
  
  public int getHeight()
  {
    return this.height;
  }
  
  public int getTexHeight()
  {
    return this.texHeight;
  }
  
  public int getTexWidth()
  {
    return this.texWidth;
  }
  
  public int getWidth()
  {
    return this.width;
  }
  
  public ByteBuffer loadImage(InputStream fis)
    throws IOException
  {
    return loadImage(fis, true, null);
  }
  
  public ByteBuffer loadImage(InputStream fis, boolean flipped, int[] transparent)
    throws IOException
  {
    return loadImage(fis, flipped, false, transparent);
  }
  
  public ByteBuffer loadImage(InputStream fis, boolean flipped, boolean forceAlpha, int[] transparent)
    throws IOException
  {
    if (transparent != null) {
      forceAlpha = true;
    }
    BufferedImage bufferedImage = ImageIO.read(fis);
    return imageToByteBuffer(bufferedImage, flipped, forceAlpha, transparent);
  }
  
  public ByteBuffer imageToByteBuffer(BufferedImage image, boolean flipped, boolean forceAlpha, int[] transparent)
  {
    ByteBuffer imageBuffer = null;
    int texWidth = 2;
    int texHeight = 2;
    while (texWidth < image.getWidth()) {
      texWidth *= 2;
    }
    while (texHeight < image.getHeight()) {
      texHeight *= 2;
    }
    this.width = image.getWidth();
    this.height = image.getHeight();
    this.texHeight = texHeight;
    this.texWidth = texWidth;
    boolean useAlpha = (image.getColorModel().hasAlpha()) || (forceAlpha);
    BufferedImage texImage;
    BufferedImage texImage;
    if (useAlpha)
    {
      this.depth = 32;
      WritableRaster raster = Raster.createInterleavedRaster(0, texWidth, texHeight, 4, null);
      texImage = new BufferedImage(glAlphaColorModel, raster, false, new Hashtable());
    }
    else
    {
      this.depth = 24;
      WritableRaster raster = Raster.createInterleavedRaster(0, texWidth, texHeight, 3, null);
      texImage = new BufferedImage(glColorModel, raster, false, new Hashtable());
    }
    Graphics2D local_g = (Graphics2D)texImage.getGraphics();
    if (useAlpha)
    {
      local_g.setColor(new Color(0.0F, 0.0F, 0.0F, 0.0F));
      local_g.fillRect(0, 0, texWidth, texHeight);
    }
    if (flipped)
    {
      local_g.scale(1.0D, -1.0D);
      local_g.drawImage(image, 0, -this.height, null);
    }
    else
    {
      local_g.drawImage(image, 0, 0, null);
    }
    if (this.edging)
    {
      if (this.height < texHeight - 1)
      {
        copyArea(texImage, 0, 0, this.width, 1, 0, texHeight - 1);
        copyArea(texImage, 0, this.height - 1, this.width, 1, 0, 1);
      }
      if (this.width < texWidth - 1)
      {
        copyArea(texImage, 0, 0, 1, this.height, texWidth - 1, 0);
        copyArea(texImage, this.width - 1, 0, 1, this.height, 1, 0);
      }
    }
    byte[] data = ((DataBufferByte)texImage.getRaster().getDataBuffer()).getData();
    if (transparent != null) {
      for (int local_i = 0; local_i < data.length; local_i += 4)
      {
        boolean match = true;
        for (int local_c = 0; local_c < 3; local_c++)
        {
          int value = data[(local_i + local_c)] < 0 ? 256 + data[(local_i + local_c)] : data[(local_i + local_c)];
          if (value != transparent[local_c]) {
            match = false;
          }
        }
        if (match) {
          data[(local_i + 3)] = 0;
        }
      }
    }
    imageBuffer = ByteBuffer.allocateDirect(data.length);
    imageBuffer.order(ByteOrder.nativeOrder());
    imageBuffer.put(data, 0, data.length);
    imageBuffer.flip();
    local_g.dispose();
    return imageBuffer;
  }
  
  public ByteBuffer getImageBufferData()
  {
    throw new RuntimeException("ImageIOImageData doesn't store it's image.");
  }
  
  private void copyArea(BufferedImage image, int local_x, int local_y, int width, int height, int local_dx, int local_dy)
  {
    Graphics2D local_g = (Graphics2D)image.getGraphics();
    local_g.drawImage(image.getSubimage(local_x, local_y, width, height), local_x + local_dx, local_y + local_dy, null);
  }
  
  public void configureEdging(boolean edging)
  {
    this.edging = edging;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.opengl.ImageIOImageData
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */