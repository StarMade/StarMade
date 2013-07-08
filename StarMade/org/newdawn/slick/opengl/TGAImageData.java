package org.newdawn.slick.opengl;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.newdawn.slick.util.GlUtil;

public class TGAImageData
  implements LoadableImageData
{
  private int texWidth;
  private int texHeight;
  private int width;
  private int height;
  private short pixelDepth;
  
  private short flipEndian(short signedShort)
  {
    int input = signedShort & 0xFFFF;
    return (short)(input << 8 | (input & 0xFF00) >>> 8);
  }
  
  public int getDepth()
  {
    return this.pixelDepth;
  }
  
  public int getWidth()
  {
    return this.width;
  }
  
  public int getHeight()
  {
    return this.height;
  }
  
  public int getTexWidth()
  {
    return this.texWidth;
  }
  
  public int getTexHeight()
  {
    return this.texHeight;
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
    byte red = 0;
    byte green = 0;
    byte blue = 0;
    byte alpha = 0;
    BufferedInputStream bis = new BufferedInputStream(fis, 100000);
    DataInputStream dis = new DataInputStream(bis);
    short idLength = (short)dis.read();
    short colorMapType = (short)dis.read();
    short imageType = (short)dis.read();
    short cMapStart = flipEndian(dis.readShort());
    short cMapLength = flipEndian(dis.readShort());
    short cMapDepth = (short)dis.read();
    short xOffset = flipEndian(dis.readShort());
    short yOffset = flipEndian(dis.readShort());
    if (imageType != 2) {
      throw new IOException("Slick only supports uncompressed RGB(A) TGA images");
    }
    this.width = flipEndian(dis.readShort());
    this.height = flipEndian(dis.readShort());
    this.pixelDepth = ((short)dis.read());
    if (this.pixelDepth == 32) {
      forceAlpha = false;
    }
    this.texWidth = get2Fold(this.width);
    this.texHeight = get2Fold(this.height);
    short imageDescriptor = (short)dis.read();
    if ((imageDescriptor & 0x20) == 0) {
      flipped = !flipped;
    }
    if (idLength > 0) {
      bis.skip(idLength);
    }
    byte[] rawData = null;
    if ((this.pixelDepth == 32) || (forceAlpha))
    {
      this.pixelDepth = 32;
      rawData = new byte[this.texWidth * this.texHeight * 4];
    }
    else if (this.pixelDepth == 24)
    {
      rawData = new byte[this.texWidth * this.texHeight * 3];
    }
    else
    {
      throw new RuntimeException("Only 24 and 32 bit TGAs are supported");
    }
    if (this.pixelDepth == 24)
    {
      if (flipped) {
        for (int local_i = this.height - 1; local_i >= 0; local_i--) {
          for (int local_j = 0; local_j < this.width; local_j++)
          {
            blue = dis.readByte();
            green = dis.readByte();
            red = dis.readByte();
            int ofs = (local_j + local_i * this.texWidth) * 3;
            rawData[ofs] = red;
            rawData[(ofs + 1)] = green;
            rawData[(ofs + 2)] = blue;
          }
        }
      } else {
        for (int local_i = 0; local_i < this.height; local_i++) {
          for (int local_j = 0; local_j < this.width; local_j++)
          {
            blue = dis.readByte();
            green = dis.readByte();
            red = dis.readByte();
            int ofs = (local_j + local_i * this.texWidth) * 3;
            rawData[ofs] = red;
            rawData[(ofs + 1)] = green;
            rawData[(ofs + 2)] = blue;
          }
        }
      }
    }
    else if (this.pixelDepth == 32) {
      if (flipped) {
        for (int local_i = this.height - 1; local_i >= 0; local_i--) {
          for (int local_j = 0; local_j < this.width; local_j++)
          {
            blue = dis.readByte();
            green = dis.readByte();
            red = dis.readByte();
            if (forceAlpha) {
              alpha = -1;
            } else {
              alpha = dis.readByte();
            }
            int ofs = (local_j + local_i * this.texWidth) * 4;
            rawData[ofs] = red;
            rawData[(ofs + 1)] = green;
            rawData[(ofs + 2)] = blue;
            rawData[(ofs + 3)] = alpha;
            if (alpha == 0)
            {
              rawData[(ofs + 2)] = 0;
              rawData[(ofs + 1)] = 0;
              rawData[ofs] = 0;
            }
          }
        }
      } else {
        for (int local_i = 0; local_i < this.height; local_i++) {
          for (int local_j = 0; local_j < this.width; local_j++)
          {
            blue = dis.readByte();
            green = dis.readByte();
            red = dis.readByte();
            if (forceAlpha) {
              alpha = -1;
            } else {
              alpha = dis.readByte();
            }
            int ofs = (local_j + local_i * this.texWidth) * 4;
            if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN)
            {
              rawData[ofs] = red;
              rawData[(ofs + 1)] = green;
              rawData[(ofs + 2)] = blue;
              rawData[(ofs + 3)] = alpha;
            }
            else
            {
              rawData[ofs] = red;
              rawData[(ofs + 1)] = green;
              rawData[(ofs + 2)] = blue;
              rawData[(ofs + 3)] = alpha;
            }
            if (alpha == 0)
            {
              rawData[(ofs + 2)] = 0;
              rawData[(ofs + 1)] = 0;
              rawData[ofs] = 0;
            }
          }
        }
      }
    }
    fis.close();
    if (transparent != null) {
      for (int local_i = 0; local_i < rawData.length; local_i += 4)
      {
        boolean local_j = true;
        for (int ofs = 0; ofs < 3; ofs++) {
          if (rawData[(local_i + ofs)] != transparent[ofs]) {
            local_j = false;
          }
        }
        if (local_j) {
          rawData[(local_i + 3)] = 0;
        }
      }
    }
    ByteBuffer local_i = GlUtil.getDynamicByteBuffer(rawData.length);
    local_i.put(rawData);
    int local_j = this.pixelDepth / 8;
    if (this.height < this.texHeight - 1)
    {
      int ofs = (this.texHeight - 1) * (this.texWidth * local_j);
      int bottomOffset = (this.height - 1) * (this.texWidth * local_j);
      for (int local_x = 0; local_x < this.texWidth * local_j; local_x++)
      {
        local_i.put(ofs + local_x, local_i.get(local_x));
        local_i.put(bottomOffset + this.texWidth * local_j + local_x, local_i.get(this.texWidth * local_j + local_x));
      }
    }
    if (this.width < this.texWidth - 1) {
      for (int ofs = 0; ofs < this.texHeight; ofs++) {
        for (int bottomOffset = 0; bottomOffset < local_j; bottomOffset++)
        {
          local_i.put((ofs + 1) * (this.texWidth * local_j) - local_j + bottomOffset, local_i.get(ofs * (this.texWidth * local_j) + bottomOffset));
          local_i.put(ofs * (this.texWidth * local_j) + this.width * local_j + bottomOffset, local_i.get(ofs * (this.texWidth * local_j) + (this.width - 1) * local_j + bottomOffset));
        }
      }
    }
    local_i.flip();
    return local_i;
  }
  
  private int get2Fold(int fold)
  {
    int ret = 2;
    while (ret < fold) {
      ret *= 2;
    }
    return ret;
  }
  
  public ByteBuffer getImageBufferData()
  {
    throw new RuntimeException("TGAImageData doesn't store it's image.");
  }
  
  public void configureEdging(boolean edging) {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.opengl.TGAImageData
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */