package org.newdawn.slick.opengl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import org.lwjgl.BufferUtils;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;
import org.newdawn.slick.util.ResourceLoader;

public class InternalTextureLoader
{
  protected static SGL field_1784 = ;
  private static final InternalTextureLoader loader = new InternalTextureLoader();
  private HashMap texturesLinear = new HashMap();
  private HashMap texturesNearest = new HashMap();
  private int dstPixelFormat = 6408;
  private boolean deferred;
  private boolean holdTextureData;
  
  public static InternalTextureLoader get()
  {
    return loader;
  }
  
  public void setHoldTextureData(boolean holdTextureData)
  {
    this.holdTextureData = holdTextureData;
  }
  
  public void setDeferredLoading(boolean deferred)
  {
    this.deferred = deferred;
  }
  
  public boolean isDeferredLoading()
  {
    return this.deferred;
  }
  
  public void clear(String name)
  {
    this.texturesLinear.remove(name);
    this.texturesNearest.remove(name);
  }
  
  public void clear()
  {
    this.texturesLinear.clear();
    this.texturesNearest.clear();
  }
  
  public void set16BitMode()
  {
    this.dstPixelFormat = 32859;
  }
  
  public static int createTextureID()
  {
    IntBuffer tmp = createIntBuffer(1);
    field_1784.glGenTextures(tmp);
    return tmp.get(0);
  }
  
  public Texture getTexture(File source, boolean flipped, int filter)
    throws IOException
  {
    String resourceName = source.getAbsolutePath();
    InputStream local_in = new FileInputStream(source);
    return getTexture(local_in, resourceName, flipped, filter, null);
  }
  
  public Texture getTexture(File source, boolean flipped, int filter, int[] transparent)
    throws IOException
  {
    String resourceName = source.getAbsolutePath();
    InputStream local_in = new FileInputStream(source);
    return getTexture(local_in, resourceName, flipped, filter, transparent);
  }
  
  public Texture getTexture(String resourceName, boolean flipped, int filter)
    throws IOException
  {
    InputStream local_in = ResourceLoader.getResourceAsStream(resourceName);
    return getTexture(local_in, resourceName, flipped, filter, null);
  }
  
  public Texture getTexture(String resourceName, boolean flipped, int filter, int[] transparent)
    throws IOException
  {
    InputStream local_in = ResourceLoader.getResourceAsStream(resourceName);
    return getTexture(local_in, resourceName, flipped, filter, transparent);
  }
  
  public Texture getTexture(InputStream local_in, String resourceName, boolean flipped, int filter)
    throws IOException
  {
    return getTexture(local_in, resourceName, flipped, filter, null);
  }
  
  public TextureImpl getTexture(InputStream local_in, String resourceName, boolean flipped, int filter, int[] transparent)
    throws IOException
  {
    if (this.deferred) {
      return new DeferredTexture(local_in, resourceName, flipped, filter, transparent);
    }
    HashMap hash = this.texturesLinear;
    if (filter == 9728) {
      hash = this.texturesNearest;
    }
    String resName = resourceName;
    if (transparent != null) {
      resName = resName + ":" + transparent[0] + ":" + transparent[1] + ":" + transparent[2];
    }
    resName = resName + ":" + flipped;
    if (this.holdTextureData)
    {
      TextureImpl tex = (TextureImpl)hash.get(resName);
      if (tex != null) {
        return tex;
      }
    }
    else
    {
      SoftReference tex = (SoftReference)hash.get(resName);
      if (tex != null)
      {
        TextureImpl tex = (TextureImpl)tex.get();
        if (tex != null) {
          return tex;
        }
        hash.remove(resName);
      }
    }
    try
    {
      field_1784.glGetError();
    }
    catch (NullPointerException tex)
    {
      throw new RuntimeException("Image based resources must be loaded as part of init() or the game loop. They cannot be loaded before initialisation.");
    }
    TextureImpl tex = getTexture(local_in, resourceName, 3553, filter, filter, flipped, transparent);
    tex.setCacheName(resName);
    if (this.holdTextureData) {
      hash.put(resName, tex);
    } else {
      hash.put(resName, new SoftReference(tex));
    }
    return tex;
  }
  
  private TextureImpl getTexture(InputStream local_in, String resourceName, int target, int magFilter, int minFilter, boolean flipped, int[] transparent)
    throws IOException
  {
    LoadableImageData imageData = ImageDataFactory.getImageDataFor(resourceName);
    ByteBuffer textureBuffer = imageData.loadImage(new BufferedInputStream(local_in), flipped, transparent);
    int textureID = createTextureID();
    TextureImpl texture = new TextureImpl(resourceName, target, textureID);
    field_1784.glBindTexture(target, textureID);
    int width = imageData.getWidth();
    int height = imageData.getHeight();
    boolean hasAlpha = imageData.getDepth() == 32;
    texture.setTextureWidth(imageData.getTexWidth());
    texture.setTextureHeight(imageData.getTexHeight());
    int texWidth = texture.getTextureWidth();
    int texHeight = texture.getTextureHeight();
    IntBuffer temp = BufferUtils.createIntBuffer(16);
    field_1784.glGetInteger(3379, temp);
    int max = temp.get(0);
    if ((texWidth > max) || (texHeight > max)) {
      throw new IOException("Attempt to allocate a texture to big for the current hardware");
    }
    int srcPixelFormat = hasAlpha ? 6408 : 6407;
    int componentCount = hasAlpha ? 4 : 3;
    texture.setWidth(width);
    texture.setHeight(height);
    texture.setAlpha(hasAlpha);
    if (this.holdTextureData) {
      texture.setTextureData(srcPixelFormat, componentCount, minFilter, magFilter, textureBuffer);
    }
    field_1784.glTexParameteri(target, 10241, minFilter);
    field_1784.glTexParameteri(target, 10240, magFilter);
    field_1784.glTexImage2D(target, 0, this.dstPixelFormat, get2Fold(width), get2Fold(height), 0, srcPixelFormat, 5121, textureBuffer);
    return texture;
  }
  
  public Texture createTexture(int width, int height)
    throws IOException
  {
    return createTexture(width, height, 9728);
  }
  
  public Texture createTexture(int width, int height, int filter)
    throws IOException
  {
    ImageData local_ds = new EmptyImageData(width, height);
    return getTexture(local_ds, filter);
  }
  
  public Texture getTexture(ImageData dataSource, int filter)
    throws IOException
  {
    int target = 3553;
    ByteBuffer textureBuffer = dataSource.getImageBufferData();
    int textureID = createTextureID();
    TextureImpl texture = new TextureImpl("generated:" + dataSource, target, textureID);
    int minFilter = filter;
    int magFilter = filter;
    boolean flipped = false;
    field_1784.glBindTexture(target, textureID);
    int width = dataSource.getWidth();
    int height = dataSource.getHeight();
    boolean hasAlpha = dataSource.getDepth() == 32;
    texture.setTextureWidth(dataSource.getTexWidth());
    texture.setTextureHeight(dataSource.getTexHeight());
    int texWidth = texture.getTextureWidth();
    int texHeight = texture.getTextureHeight();
    int srcPixelFormat = hasAlpha ? 6408 : 6407;
    int componentCount = hasAlpha ? 4 : 3;
    texture.setWidth(width);
    texture.setHeight(height);
    texture.setAlpha(hasAlpha);
    IntBuffer temp = BufferUtils.createIntBuffer(16);
    field_1784.glGetInteger(3379, temp);
    int max = temp.get(0);
    if ((texWidth > max) || (texHeight > max)) {
      throw new IOException("Attempt to allocate a texture to big for the current hardware");
    }
    if (this.holdTextureData) {
      texture.setTextureData(srcPixelFormat, componentCount, minFilter, magFilter, textureBuffer);
    }
    field_1784.glTexParameteri(target, 10241, minFilter);
    field_1784.glTexParameteri(target, 10240, magFilter);
    field_1784.glTexImage2D(target, 0, this.dstPixelFormat, get2Fold(width), get2Fold(height), 0, srcPixelFormat, 5121, textureBuffer);
    return texture;
  }
  
  public static int get2Fold(int fold)
  {
    int ret = 2;
    while (ret < fold) {
      ret *= 2;
    }
    return ret;
  }
  
  public static IntBuffer createIntBuffer(int size)
  {
    ByteBuffer temp = ByteBuffer.allocateDirect(4 * size);
    temp.order(ByteOrder.nativeOrder());
    return temp.asIntBuffer();
  }
  
  public void reload()
  {
    Iterator texs = this.texturesLinear.values().iterator();
    while (texs.hasNext()) {
      ((TextureImpl)texs.next()).reload();
    }
    texs = this.texturesNearest.values().iterator();
    while (texs.hasNext()) {
      ((TextureImpl)texs.next()).reload();
    }
  }
  
  public int reload(TextureImpl texture, int srcPixelFormat, int componentCount, int minFilter, int magFilter, ByteBuffer textureBuffer)
  {
    int target = 3553;
    int textureID = createTextureID();
    field_1784.glBindTexture(target, textureID);
    field_1784.glTexParameteri(target, 10241, minFilter);
    field_1784.glTexParameteri(target, 10240, magFilter);
    field_1784.glTexImage2D(target, 0, this.dstPixelFormat, texture.getTextureWidth(), texture.getTextureHeight(), 0, srcPixelFormat, 5121, textureBuffer);
    return textureID;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.opengl.InternalTextureLoader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */