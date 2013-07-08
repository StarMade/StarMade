package org.newdawn.slick.opengl;

import java.nio.ByteBuffer;

public abstract interface ImageData
{
  public abstract int getDepth();
  
  public abstract int getWidth();
  
  public abstract int getHeight();
  
  public abstract int getTexWidth();
  
  public abstract int getTexHeight();
  
  public abstract ByteBuffer getImageBufferData();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.opengl.ImageData
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */