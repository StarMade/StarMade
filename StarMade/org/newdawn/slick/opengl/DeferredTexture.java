package org.newdawn.slick.opengl;

import java.io.IOException;
import java.io.InputStream;
import org.newdawn.slick.loading.DeferredResource;
import org.newdawn.slick.loading.LoadingList;

public class DeferredTexture
  extends TextureImpl
  implements DeferredResource
{
  private InputStream field_63;
  private String resourceName;
  private boolean flipped;
  private int filter;
  private TextureImpl target;
  private int[] trans;
  
  public DeferredTexture(InputStream local_in, String resourceName, boolean flipped, int filter, int[] trans)
  {
    this.field_63 = local_in;
    this.resourceName = resourceName;
    this.flipped = flipped;
    this.filter = filter;
    this.trans = trans;
    LoadingList.get().add(this);
  }
  
  public void load()
    throws IOException
  {
    boolean before = InternalTextureLoader.get().isDeferredLoading();
    InternalTextureLoader.get().setDeferredLoading(false);
    this.target = InternalTextureLoader.get().getTexture(this.field_63, this.resourceName, this.flipped, this.filter, this.trans);
    InternalTextureLoader.get().setDeferredLoading(before);
  }
  
  private void checkTarget()
  {
    if (this.target == null) {
      try
      {
        load();
        LoadingList.get().remove(this);
        return;
      }
      catch (IOException local_e)
      {
        throw new RuntimeException("Attempt to use deferred texture before loading and resource not found: " + this.resourceName);
      }
    }
  }
  
  public void bind()
  {
    checkTarget();
    this.target.bind();
  }
  
  public float getHeight()
  {
    checkTarget();
    return this.target.getHeight();
  }
  
  public int getImageHeight()
  {
    checkTarget();
    return this.target.getImageHeight();
  }
  
  public int getImageWidth()
  {
    checkTarget();
    return this.target.getImageWidth();
  }
  
  public int getTextureHeight()
  {
    checkTarget();
    return this.target.getTextureHeight();
  }
  
  public int getTextureID()
  {
    checkTarget();
    return this.target.getTextureID();
  }
  
  public String getTextureRef()
  {
    checkTarget();
    return this.target.getTextureRef();
  }
  
  public int getTextureWidth()
  {
    checkTarget();
    return this.target.getTextureWidth();
  }
  
  public float getWidth()
  {
    checkTarget();
    return this.target.getWidth();
  }
  
  public void release()
  {
    checkTarget();
    this.target.release();
  }
  
  public void setAlpha(boolean alpha)
  {
    checkTarget();
    this.target.setAlpha(alpha);
  }
  
  public void setHeight(int height)
  {
    checkTarget();
    this.target.setHeight(height);
  }
  
  public void setTextureHeight(int texHeight)
  {
    checkTarget();
    this.target.setTextureHeight(texHeight);
  }
  
  public void setTextureID(int textureID)
  {
    checkTarget();
    this.target.setTextureID(textureID);
  }
  
  public void setTextureWidth(int texWidth)
  {
    checkTarget();
    this.target.setTextureWidth(texWidth);
  }
  
  public void setWidth(int width)
  {
    checkTarget();
    this.target.setWidth(width);
  }
  
  public byte[] getTextureData()
  {
    checkTarget();
    return this.target.getTextureData();
  }
  
  public String getDescription()
  {
    return this.resourceName;
  }
  
  public boolean hasAlpha()
  {
    checkTarget();
    return this.target.hasAlpha();
  }
  
  public void setTextureFilter(int textureFilter)
  {
    checkTarget();
    this.target.setTextureFilter(textureFilter);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.opengl.DeferredTexture
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */