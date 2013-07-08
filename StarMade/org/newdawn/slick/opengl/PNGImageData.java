package org.newdawn.slick.opengl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import org.newdawn.slick.util.GlUtil;

public class PNGImageData
  implements LoadableImageData
{
  private int width;
  private int height;
  private int texHeight;
  private int texWidth;
  private PNGDecoder decoder;
  private int bitDepth;
  private ByteBuffer scratch;
  
  public int getDepth()
  {
    return this.bitDepth;
  }
  
  public ByteBuffer getImageBufferData()
  {
    return this.scratch;
  }
  
  public int getTexHeight()
  {
    return this.texHeight;
  }
  
  public int getTexWidth()
  {
    return this.texWidth;
  }
  
  public ByteBuffer loadImage(InputStream fis)
    throws IOException
  {
    return loadImage(fis, false, null);
  }
  
  public ByteBuffer loadImage(InputStream fis, boolean flipped, int[] transparent)
    throws IOException
  {
    return loadImage(fis, flipped, false, transparent);
  }
  
  public ByteBuffer loadImage(InputStream fis, boolean flipped, boolean forceAlpha, int[] transparent)
    throws IOException
  {
    if (transparent != null)
    {
      forceAlpha = true;
      throw new IOException("Transparent color not support in custom PNG Decoder");
    }
    PNGDecoder decoder = new PNGDecoder(fis);
    if (!decoder.isRGB()) {
      throw new IOException("Only RGB formatted images are supported by the PNGLoader");
    }
    this.width = decoder.getWidth();
    this.height = decoder.getHeight();
    this.texWidth = get2Fold(this.width);
    this.texHeight = get2Fold(this.height);
    int perPixel = decoder.hasAlpha() ? 4 : 3;
    this.bitDepth = (decoder.hasAlpha() ? 32 : 24);
    this.scratch = GlUtil.getDynamicByteBuffer(this.texWidth * this.texHeight * perPixel);
    decoder.decode(this.scratch, this.texWidth * perPixel, perPixel == 4 ? PNGDecoder.RGBA : PNGDecoder.RGB);
    if (this.height < this.texHeight - 1)
    {
      int topOffset = (this.texHeight - 1) * (this.texWidth * perPixel);
      int bottomOffset = (this.height - 1) * (this.texWidth * perPixel);
      for (int local_x = 0; local_x < this.texWidth; local_x++) {
        for (int local_i = 0; local_i < perPixel; local_i++)
        {
          this.scratch.put(topOffset + local_x + local_i, this.scratch.get(local_x + local_i));
          this.scratch.put(bottomOffset + this.texWidth * perPixel + local_x + local_i, this.scratch.get(bottomOffset + local_x + local_i));
        }
      }
    }
    if (this.width < this.texWidth - 1) {
      for (int topOffset = 0; topOffset < this.texHeight; topOffset++) {
        for (int bottomOffset = 0; bottomOffset < perPixel; bottomOffset++)
        {
          this.scratch.put((topOffset + 1) * (this.texWidth * perPixel) - perPixel + bottomOffset, this.scratch.get(topOffset * (this.texWidth * perPixel) + bottomOffset));
          this.scratch.put(topOffset * (this.texWidth * perPixel) + this.width * perPixel + bottomOffset, this.scratch.get(topOffset * (this.texWidth * perPixel) + (this.width - 1) * perPixel + bottomOffset));
        }
      }
    }
    if ((!decoder.hasAlpha()) && (forceAlpha))
    {
      ByteBuffer topOffset = GlUtil.getDynamicByteBuffer(this.texWidth * this.texHeight * 4);
      for (int bottomOffset = 0; bottomOffset < this.texWidth; bottomOffset++) {
        for (int local_x = 0; local_x < this.texHeight; local_x++)
        {
          int local_i = local_x * 3 + bottomOffset * this.texHeight * 3;
          int dstOffset = local_x * 4 + bottomOffset * this.texHeight * 4;
          topOffset.put(dstOffset, this.scratch.get(local_i));
          topOffset.put(dstOffset + 1, this.scratch.get(local_i + 1));
          topOffset.put(dstOffset + 2, this.scratch.get(local_i + 2));
          if ((bottomOffset < getHeight()) && (local_x < getWidth())) {
            topOffset.put(dstOffset + 3, (byte)-1);
          } else {
            topOffset.put(dstOffset + 3, (byte)0);
          }
        }
      }
      this.bitDepth = 32;
      this.scratch = topOffset;
    }
    if (transparent != null) {
      for (int topOffset = 0; topOffset < this.texWidth * this.texHeight * 4; topOffset += 4)
      {
        boolean bottomOffset = true;
        for (int local_x = 0; local_x < 3; local_x++) {
          if (toInt(this.scratch.get(topOffset + local_x)) != transparent[local_x]) {
            bottomOffset = false;
          }
        }
        if (bottomOffset) {
          this.scratch.put(topOffset + 3, (byte)0);
        }
      }
    }
    this.scratch.position(0);
    return this.scratch;
  }
  
  private int toInt(byte local_b)
  {
    if (local_b < 0) {
      return 256 + local_b;
    }
    return local_b;
  }
  
  private int get2Fold(int fold)
  {
    int ret = 2;
    while (ret < fold) {
      ret *= 2;
    }
    return ret;
  }
  
  public void configureEdging(boolean edging) {}
  
  public int getWidth()
  {
    return this.width;
  }
  
  public int getHeight()
  {
    return this.height;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.opengl.PNGImageData
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */