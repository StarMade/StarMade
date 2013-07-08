package org.newdawn.slick;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.newdawn.slick.opengl.ImageData;
import org.newdawn.slick.util.GlUtil;

public class ImageBuffer
  implements ImageData
{
  private int width;
  private int height;
  private int texWidth;
  private int texHeight;
  private byte[] rawData;
  
  public ImageBuffer(int width, int height)
  {
    this.width = width;
    this.height = height;
    this.texWidth = get2Fold(width);
    this.texHeight = get2Fold(height);
    this.rawData = new byte[this.texWidth * this.texHeight * 4];
  }
  
  public byte[] getRGBA()
  {
    return this.rawData;
  }
  
  public int getDepth()
  {
    return 32;
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
  
  public ByteBuffer getImageBufferData()
  {
    ByteBuffer scratch = GlUtil.getDynamicByteBuffer(this.rawData.length);
    scratch.put(this.rawData);
    scratch.flip();
    return scratch;
  }
  
  public void setRGBA(int local_x, int local_y, int local_r, int local_g, int local_b, int local_a)
  {
    if ((local_x < 0) || (local_x >= this.width) || (local_y < 0) || (local_y >= this.height)) {
      throw new RuntimeException("Specified location: " + local_x + "," + local_y + " outside of image");
    }
    int ofs = (local_x + local_y * this.texWidth) * 4;
    if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN)
    {
      this.rawData[ofs] = ((byte)local_b);
      this.rawData[(ofs + 1)] = ((byte)local_g);
      this.rawData[(ofs + 2)] = ((byte)local_r);
      this.rawData[(ofs + 3)] = ((byte)local_a);
    }
    else
    {
      this.rawData[ofs] = ((byte)local_r);
      this.rawData[(ofs + 1)] = ((byte)local_g);
      this.rawData[(ofs + 2)] = ((byte)local_b);
      this.rawData[(ofs + 3)] = ((byte)local_a);
    }
  }
  
  public Image getImage()
  {
    return new Image(this);
  }
  
  public Image getImage(int filter)
  {
    return new Image(this, filter);
  }
  
  private int get2Fold(int fold)
  {
    int ret = 2;
    while (ret < fold) {
      ret *= 2;
    }
    return ret;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.ImageBuffer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */