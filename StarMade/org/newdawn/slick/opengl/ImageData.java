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

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.opengl.ImageData
 * JD-Core Version:    0.6.2
 */