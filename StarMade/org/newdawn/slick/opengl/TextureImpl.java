package org.newdawn.slick.opengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;
import org.newdawn.slick.util.GlUtil;
import org.newdawn.slick.util.Log;

public class TextureImpl
  implements Texture
{
  protected static SGL field_403 = ;
  static Texture lastBind;
  private int target;
  private int textureID;
  private int height;
  private int width;
  private int texWidth;
  private int texHeight;
  private float widthRatio;
  private float heightRatio;
  private boolean alpha;
  private String ref;
  private String cacheName;
  private ReloadData reloadData;
  
  public static Texture getLastBind()
  {
    return lastBind;
  }
  
  protected TextureImpl() {}
  
  public TextureImpl(String ref, int target, int textureID)
  {
    this.target = target;
    this.ref = ref;
    this.textureID = textureID;
    lastBind = this;
  }
  
  public void setCacheName(String cacheName)
  {
    this.cacheName = cacheName;
  }
  
  public boolean hasAlpha()
  {
    return this.alpha;
  }
  
  public String getTextureRef()
  {
    return this.ref;
  }
  
  public void setAlpha(boolean alpha)
  {
    this.alpha = alpha;
  }
  
  public static void bindNone()
  {
    lastBind = null;
    field_403.glDisable(3553);
  }
  
  public static void unbind()
  {
    lastBind = null;
  }
  
  public void bind()
  {
    if (lastBind != this)
    {
      lastBind = this;
      field_403.glEnable(3553);
      field_403.glBindTexture(this.target, this.textureID);
    }
  }
  
  public void setHeight(int height)
  {
    this.height = height;
    setHeight();
  }
  
  public void setWidth(int width)
  {
    this.width = width;
    setWidth();
  }
  
  public int getImageHeight()
  {
    return this.height;
  }
  
  public int getImageWidth()
  {
    return this.width;
  }
  
  public float getHeight()
  {
    return this.heightRatio;
  }
  
  public float getWidth()
  {
    return this.widthRatio;
  }
  
  public int getTextureHeight()
  {
    return this.texHeight;
  }
  
  public int getTextureWidth()
  {
    return this.texWidth;
  }
  
  public void setTextureHeight(int texHeight)
  {
    this.texHeight = texHeight;
    setHeight();
  }
  
  public void setTextureWidth(int texWidth)
  {
    this.texWidth = texWidth;
    setWidth();
  }
  
  private void setHeight()
  {
    if (this.texHeight != 0) {
      this.heightRatio = (this.height / this.texHeight);
    }
  }
  
  private void setWidth()
  {
    if (this.texWidth != 0) {
      this.widthRatio = (this.width / this.texWidth);
    }
  }
  
  public void release()
  {
    IntBuffer texBuf = createIntBuffer(1);
    texBuf.put(this.textureID);
    texBuf.flip();
    field_403.glDeleteTextures(texBuf);
    if (lastBind == this) {
      bindNone();
    }
    if (this.cacheName != null) {
      InternalTextureLoader.get().clear(this.cacheName);
    } else {
      InternalTextureLoader.get().clear(this.ref);
    }
  }
  
  public int getTextureID()
  {
    return this.textureID;
  }
  
  public void setTextureID(int textureID)
  {
    this.textureID = textureID;
  }
  
  protected IntBuffer createIntBuffer(int size)
  {
    ByteBuffer temp = ByteBuffer.allocateDirect(4 * size);
    temp.order(ByteOrder.nativeOrder());
    return temp.asIntBuffer();
  }
  
  public byte[] getTextureData()
  {
    ByteBuffer buffer = GlUtil.getDynamicByteBuffer((hasAlpha() ? 4 : 3) * this.texWidth * this.texHeight);
    bind();
    field_403.glGetTexImage(3553, 0, hasAlpha() ? 6408 : 6407, 5121, buffer);
    byte[] data = new byte[buffer.limit()];
    buffer.get(data);
    buffer.clear();
    return data;
  }
  
  public void setTextureFilter(int textureFilter)
  {
    bind();
    field_403.glTexParameteri(this.target, 10241, textureFilter);
    field_403.glTexParameteri(this.target, 10240, textureFilter);
  }
  
  public void setTextureData(int srcPixelFormat, int componentCount, int minFilter, int magFilter, ByteBuffer textureBuffer)
  {
    this.reloadData = new ReloadData(null);
    this.reloadData.srcPixelFormat = srcPixelFormat;
    this.reloadData.componentCount = componentCount;
    this.reloadData.minFilter = minFilter;
    this.reloadData.magFilter = magFilter;
    this.reloadData.textureBuffer = textureBuffer;
  }
  
  public void reload()
  {
    if (this.reloadData != null) {
      this.textureID = this.reloadData.reload();
    }
  }
  
  private class ReloadData
  {
    private int srcPixelFormat;
    private int componentCount;
    private int minFilter;
    private int magFilter;
    private ByteBuffer textureBuffer;
    
    private ReloadData() {}
    
    public int reload()
    {
      Log.error("Reloading texture: " + TextureImpl.this.ref);
      return InternalTextureLoader.get().reload(TextureImpl.this, this.srcPixelFormat, this.componentCount, this.minFilter, this.magFilter, this.textureBuffer);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.opengl.TextureImpl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */