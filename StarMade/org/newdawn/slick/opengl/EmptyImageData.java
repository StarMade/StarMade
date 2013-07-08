package org.newdawn.slick.opengl;

import java.nio.ByteBuffer;
import org.newdawn.slick.util.GlUtil;

public class EmptyImageData
  implements ImageData
{
  private int width;
  private int height;
  
  public EmptyImageData(int width, int height)
  {
    this.width = width;
    this.height = height;
  }
  
  public int getDepth()
  {
    return 32;
  }
  
  public int getHeight()
  {
    return this.height;
  }
  
  public ByteBuffer getImageBufferData()
  {
    return GlUtil.getDynamicByteBuffer(getTexWidth() * getTexHeight() * 4);
  }
  
  public int getTexHeight()
  {
    return InternalTextureLoader.get2Fold(this.height);
  }
  
  public int getTexWidth()
  {
    return InternalTextureLoader.get2Fold(this.width);
  }
  
  public int getWidth()
  {
    return this.width;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.opengl.EmptyImageData
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */