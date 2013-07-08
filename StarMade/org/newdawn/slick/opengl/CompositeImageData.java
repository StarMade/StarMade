package org.newdawn.slick.opengl;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.newdawn.slick.util.Log;

public class CompositeImageData
  implements LoadableImageData
{
  private ArrayList sources = new ArrayList();
  private LoadableImageData picked;
  
  public void add(LoadableImageData data)
  {
    this.sources.add(data);
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
  
  public ByteBuffer loadImage(InputStream local_is, boolean flipped, boolean forceAlpha, int[] transparent)
    throws IOException
  {
    CompositeIOException exception = new CompositeIOException();
    ByteBuffer buffer = null;
    BufferedInputStream local_in = new BufferedInputStream(local_is, local_is.available());
    local_in.mark(local_is.available());
    int local_i = 0;
    while (local_i < this.sources.size())
    {
      local_in.reset();
      try
      {
        LoadableImageData data = (LoadableImageData)this.sources.get(local_i);
        buffer = data.loadImage(local_in, flipped, forceAlpha, transparent);
        this.picked = data;
      }
      catch (Exception data)
      {
        Log.warn(this.sources.get(local_i).getClass() + " failed to read the data", data);
        exception.addException(data);
        local_i++;
      }
    }
    if (this.picked == null) {
      throw exception;
    }
    return buffer;
  }
  
  private void checkPicked()
  {
    if (this.picked == null) {
      throw new RuntimeException("Attempt to make use of uninitialised or invalid composite image data");
    }
  }
  
  public int getDepth()
  {
    checkPicked();
    return this.picked.getDepth();
  }
  
  public int getHeight()
  {
    checkPicked();
    return this.picked.getHeight();
  }
  
  public ByteBuffer getImageBufferData()
  {
    checkPicked();
    return this.picked.getImageBufferData();
  }
  
  public int getTexHeight()
  {
    checkPicked();
    return this.picked.getTexHeight();
  }
  
  public int getTexWidth()
  {
    checkPicked();
    return this.picked.getTexWidth();
  }
  
  public int getWidth()
  {
    checkPicked();
    return this.picked.getWidth();
  }
  
  public void configureEdging(boolean edging)
  {
    for (int local_i = 0; local_i < this.sources.size(); local_i++) {
      ((LoadableImageData)this.sources.get(local_i)).configureEdging(edging);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.opengl.CompositeImageData
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */