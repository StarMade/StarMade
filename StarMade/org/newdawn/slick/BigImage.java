package org.newdawn.slick;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.newdawn.slick.opengl.ImageData;
import org.newdawn.slick.opengl.ImageDataFactory;
import org.newdawn.slick.opengl.LoadableImageData;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;
import org.newdawn.slick.util.OperationNotSupportedException;
import org.newdawn.slick.util.ResourceLoader;

public class BigImage
  extends Image
{
  protected static SGL field_204 = ;
  private static Image lastBind;
  private Image[][] images;
  private int xcount;
  private int ycount;
  private int realWidth;
  private int realHeight;
  
  public static final int getMaxSingleImageSize()
  {
    IntBuffer buffer = BufferUtils.createIntBuffer(16);
    field_204.glGetInteger(3379, buffer);
    return buffer.get(0);
  }
  
  private BigImage()
  {
    this.inited = true;
  }
  
  public BigImage(String ref)
    throws SlickException
  {
    this(ref, 2);
  }
  
  public BigImage(String ref, int filter)
    throws SlickException
  {
    build(ref, filter, getMaxSingleImageSize());
  }
  
  public BigImage(String ref, int filter, int tileSize)
    throws SlickException
  {
    build(ref, filter, tileSize);
  }
  
  public BigImage(LoadableImageData data, ByteBuffer imageBuffer, int filter)
  {
    build(data, imageBuffer, filter, getMaxSingleImageSize());
  }
  
  public BigImage(LoadableImageData data, ByteBuffer imageBuffer, int filter, int tileSize)
  {
    build(data, imageBuffer, filter, tileSize);
  }
  
  public Image getTile(int local_x, int local_y)
  {
    return this.images[local_x][local_y];
  }
  
  private void build(String ref, int filter, int tileSize)
    throws SlickException
  {
    try
    {
      LoadableImageData data = ImageDataFactory.getImageDataFor(ref);
      ByteBuffer imageBuffer = data.loadImage(ResourceLoader.getResourceAsStream(ref), false, null);
      build(data, imageBuffer, filter, tileSize);
    }
    catch (IOException data)
    {
      throw new SlickException("Failed to load: " + ref, data);
    }
  }
  
  private void build(final LoadableImageData data, final ByteBuffer imageBuffer, int filter, int tileSize)
  {
    final int dataWidth = data.getTexWidth();
    final int dataHeight = data.getTexHeight();
    this.realWidth = (this.width = data.getWidth());
    this.realHeight = (this.height = data.getHeight());
    if ((dataWidth <= tileSize) && (dataHeight <= tileSize))
    {
      this.images = new Image[1][1];
      ImageData tempData = new ImageData()
      {
        public int getDepth()
        {
          return data.getDepth();
        }
        
        public int getHeight()
        {
          return dataHeight;
        }
        
        public ByteBuffer getImageBufferData()
        {
          return imageBuffer;
        }
        
        public int getTexHeight()
        {
          return dataHeight;
        }
        
        public int getTexWidth()
        {
          return dataWidth;
        }
        
        public int getWidth()
        {
          return dataWidth;
        }
      };
      this.images[0][0] = new Image(tempData, filter);
      this.xcount = 1;
      this.ycount = 1;
      this.inited = true;
      return;
    }
    this.xcount = ((this.realWidth - 1) / tileSize + 1);
    this.ycount = ((this.realHeight - 1) / tileSize + 1);
    this.images = new Image[this.xcount][this.ycount];
    int tempData = data.getDepth() / 8;
    for (int local_x = 0; local_x < this.xcount; local_x++) {
      for (int local_y = 0; local_y < this.ycount; local_y++)
      {
        int finalX = (local_x + 1) * tileSize;
        int finalY = (local_y + 1) * tileSize;
        final int imageWidth = Math.min(this.realWidth - local_x * tileSize, tileSize);
        final int imageHeight = Math.min(this.realHeight - local_y * tileSize, tileSize);
        final int xSize = tileSize;
        final int ySize = tileSize;
        final ByteBuffer subBuffer = BufferUtils.createByteBuffer(tileSize * tileSize * tempData);
        int local_xo = local_x * tileSize * tempData;
        byte[] byteData = new byte[xSize * tempData];
        for (int local_i = 0; local_i < ySize; local_i++)
        {
          int local_yo = (local_y * tileSize + local_i) * dataWidth * tempData;
          imageBuffer.position(local_yo + local_xo);
          imageBuffer.get(byteData, 0, xSize * tempData);
          subBuffer.put(byteData);
        }
        subBuffer.flip();
        ImageData local_i = new ImageData()
        {
          public int getDepth()
          {
            return data.getDepth();
          }
          
          public int getHeight()
          {
            return imageHeight;
          }
          
          public int getWidth()
          {
            return imageWidth;
          }
          
          public ByteBuffer getImageBufferData()
          {
            return subBuffer;
          }
          
          public int getTexHeight()
          {
            return ySize;
          }
          
          public int getTexWidth()
          {
            return xSize;
          }
        };
        this.images[local_x][local_y] = new Image(local_i, filter);
      }
    }
    this.inited = true;
  }
  
  public void bind()
  {
    throw new OperationNotSupportedException("Can't bind big images yet");
  }
  
  public Image copy()
  {
    throw new OperationNotSupportedException("Can't copy big images yet");
  }
  
  public void draw()
  {
    draw(0.0F, 0.0F);
  }
  
  public void draw(float local_x, float local_y, Color filter)
  {
    draw(local_x, local_y, this.width, this.height, filter);
  }
  
  public void draw(float local_x, float local_y, float scale, Color filter)
  {
    draw(local_x, local_y, this.width * scale, this.height * scale, filter);
  }
  
  public void draw(float local_x, float local_y, float width, float height, Color filter)
  {
    float local_sx = width / this.realWidth;
    float local_sy = height / this.realHeight;
    field_204.glTranslatef(local_x, local_y, 0.0F);
    field_204.glScalef(local_sx, local_sy, 1.0F);
    float local_xp = 0.0F;
    float local_yp = 0.0F;
    for (int local_tx = 0; local_tx < this.xcount; local_tx++)
    {
      local_yp = 0.0F;
      for (int local_ty = 0; local_ty < this.ycount; local_ty++)
      {
        Image image = this.images[local_tx][local_ty];
        image.draw(local_xp, local_yp, image.getWidth(), image.getHeight(), filter);
        local_yp += image.getHeight();
        if (local_ty == this.ycount - 1) {
          local_xp += image.getWidth();
        }
      }
    }
    field_204.glScalef(1.0F / local_sx, 1.0F / local_sy, 1.0F);
    field_204.glTranslatef(-local_x, -local_y, 0.0F);
  }
  
  public void draw(float local_x, float local_y, float local_x2, float local_y2, float srcx, float srcy, float srcx2, float srcy2)
  {
    int srcwidth = (int)(srcx2 - srcx);
    int srcheight = (int)(srcy2 - srcy);
    Image subImage = getSubImage((int)srcx, (int)srcy, srcwidth, srcheight);
    int width = (int)(local_x2 - local_x);
    int height = (int)(local_y2 - local_y);
    subImage.draw(local_x, local_y, width, height);
  }
  
  public void draw(float local_x, float local_y, float srcx, float srcy, float srcx2, float srcy2)
  {
    int srcwidth = (int)(srcx2 - srcx);
    int srcheight = (int)(srcy2 - srcy);
    draw(local_x, local_y, srcwidth, srcheight, srcx, srcy, srcx2, srcy2);
  }
  
  public void draw(float local_x, float local_y, float width, float height)
  {
    draw(local_x, local_y, width, height, Color.white);
  }
  
  public void draw(float local_x, float local_y, float scale)
  {
    draw(local_x, local_y, scale, Color.white);
  }
  
  public void draw(float local_x, float local_y)
  {
    draw(local_x, local_y, Color.white);
  }
  
  public void drawEmbedded(float local_x, float local_y, float width, float height)
  {
    float local_sx = width / this.realWidth;
    float local_sy = height / this.realHeight;
    float local_xp = 0.0F;
    float local_yp = 0.0F;
    for (int local_tx = 0; local_tx < this.xcount; local_tx++)
    {
      local_yp = 0.0F;
      for (int local_ty = 0; local_ty < this.ycount; local_ty++)
      {
        Image image = this.images[local_tx][local_ty];
        if ((lastBind == null) || (image.getTexture() != lastBind.getTexture()))
        {
          if (lastBind != null) {
            lastBind.endUse();
          }
          lastBind = image;
          lastBind.startUse();
        }
        image.drawEmbedded(local_xp + local_x, local_yp + local_y, image.getWidth(), image.getHeight());
        local_yp += image.getHeight();
        if (local_ty == this.ycount - 1) {
          local_xp += image.getWidth();
        }
      }
    }
  }
  
  public void drawFlash(float local_x, float local_y, float width, float height)
  {
    float local_sx = width / this.realWidth;
    float local_sy = height / this.realHeight;
    field_204.glTranslatef(local_x, local_y, 0.0F);
    field_204.glScalef(local_sx, local_sy, 1.0F);
    float local_xp = 0.0F;
    float local_yp = 0.0F;
    for (int local_tx = 0; local_tx < this.xcount; local_tx++)
    {
      local_yp = 0.0F;
      for (int local_ty = 0; local_ty < this.ycount; local_ty++)
      {
        Image image = this.images[local_tx][local_ty];
        image.drawFlash(local_xp, local_yp, image.getWidth(), image.getHeight());
        local_yp += image.getHeight();
        if (local_ty == this.ycount - 1) {
          local_xp += image.getWidth();
        }
      }
    }
    field_204.glScalef(1.0F / local_sx, 1.0F / local_sy, 1.0F);
    field_204.glTranslatef(-local_x, -local_y, 0.0F);
  }
  
  public void drawFlash(float local_x, float local_y)
  {
    drawFlash(local_x, local_y, this.width, this.height);
  }
  
  public void endUse()
  {
    if (lastBind != null) {
      lastBind.endUse();
    }
    lastBind = null;
  }
  
  public void startUse() {}
  
  public void ensureInverted()
  {
    throw new OperationNotSupportedException("Doesn't make sense for tiled operations");
  }
  
  public Color getColor(int local_x, int local_y)
  {
    throw new OperationNotSupportedException("Can't use big images as buffers");
  }
  
  public Image getFlippedCopy(boolean flipHorizontal, boolean flipVertical)
  {
    BigImage image = new BigImage();
    image.images = this.images;
    image.xcount = this.xcount;
    image.ycount = this.ycount;
    image.width = this.width;
    image.height = this.height;
    image.realWidth = this.realWidth;
    image.realHeight = this.realHeight;
    if (flipHorizontal)
    {
      Image[][] images = image.images;
      image.images = new Image[this.xcount][this.ycount];
      for (int local_x = 0; local_x < this.xcount; local_x++) {
        for (int local_y = 0; local_y < this.ycount; local_y++) {
          image.images[local_x][local_y] = images[(this.xcount - 1 - local_x)][local_y].getFlippedCopy(true, false);
        }
      }
    }
    if (flipVertical)
    {
      Image[][] images = image.images;
      image.images = new Image[this.xcount][this.ycount];
      for (int local_x = 0; local_x < this.xcount; local_x++) {
        for (int local_y = 0; local_y < this.ycount; local_y++) {
          image.images[local_x][local_y] = images[local_x][(this.ycount - 1 - local_y)].getFlippedCopy(false, true);
        }
      }
    }
    return image;
  }
  
  public Graphics getGraphics()
    throws SlickException
  {
    throw new OperationNotSupportedException("Can't use big images as offscreen buffers");
  }
  
  public Image getScaledCopy(float scale)
  {
    return getScaledCopy((int)(scale * this.width), (int)(scale * this.height));
  }
  
  public Image getScaledCopy(int width, int height)
  {
    BigImage image = new BigImage();
    image.images = this.images;
    image.xcount = this.xcount;
    image.ycount = this.ycount;
    image.width = width;
    image.height = height;
    image.realWidth = this.realWidth;
    image.realHeight = this.realHeight;
    return image;
  }
  
  public Image getSubImage(int local_x, int local_y, int width, int height)
  {
    BigImage image = new BigImage();
    image.width = width;
    image.height = height;
    image.realWidth = width;
    image.realHeight = height;
    image.images = new Image[this.xcount][this.ycount];
    float local_xp = 0.0F;
    float local_yp = 0.0F;
    int local_x2 = local_x + width;
    int local_y2 = local_y + height;
    int startx = 0;
    int starty = 0;
    boolean foundStart = false;
    for (int local_xt = 0; local_xt < this.xcount; local_xt++)
    {
      local_yp = 0.0F;
      starty = 0;
      foundStart = false;
      for (int local_yt = 0; local_yt < this.ycount; local_yt++)
      {
        Image current = this.images[local_xt][local_yt];
        int xp2 = (int)(local_xp + current.getWidth());
        int yp2 = (int)(local_yp + current.getHeight());
        int targetX1 = (int)Math.max(local_x, local_xp);
        int targetY1 = (int)Math.max(local_y, local_yp);
        int targetX2 = Math.min(local_x2, xp2);
        int targetY2 = Math.min(local_y2, yp2);
        int targetWidth = targetX2 - targetX1;
        int targetHeight = targetY2 - targetY1;
        if ((targetWidth > 0) && (targetHeight > 0))
        {
          Image subImage = current.getSubImage((int)(targetX1 - local_xp), (int)(targetY1 - local_yp), targetX2 - targetX1, targetY2 - targetY1);
          foundStart = true;
          image.images[startx][starty] = subImage;
          starty++;
          image.ycount = Math.max(image.ycount, starty);
        }
        local_yp += current.getHeight();
        if (local_yt == this.ycount - 1) {
          local_xp += current.getWidth();
        }
      }
      if (foundStart)
      {
        startx++;
        image.xcount += 1;
      }
    }
    return image;
  }
  
  public Texture getTexture()
  {
    throw new OperationNotSupportedException("Can't use big images as offscreen buffers");
  }
  
  protected void initImpl()
  {
    throw new OperationNotSupportedException("Can't use big images as offscreen buffers");
  }
  
  protected void reinit()
  {
    throw new OperationNotSupportedException("Can't use big images as offscreen buffers");
  }
  
  public void setTexture(Texture texture)
  {
    throw new OperationNotSupportedException("Can't use big images as offscreen buffers");
  }
  
  public Image getSubImage(int offsetX, int offsetY)
  {
    return this.images[offsetX][offsetY];
  }
  
  public int getHorizontalImageCount()
  {
    return this.xcount;
  }
  
  public int getVerticalImageCount()
  {
    return this.ycount;
  }
  
  public String toString()
  {
    return "[BIG IMAGE]";
  }
  
  public void destroy()
    throws SlickException
  {
    for (int local_tx = 0; local_tx < this.xcount; local_tx++) {
      for (int local_ty = 0; local_ty < this.ycount; local_ty++)
      {
        Image image = this.images[local_tx][local_ty];
        image.destroy();
      }
    }
  }
  
  public void draw(float local_x, float local_y, float local_x2, float local_y2, float srcx, float srcy, float srcx2, float srcy2, Color filter)
  {
    int srcwidth = (int)(srcx2 - srcx);
    int srcheight = (int)(srcy2 - srcy);
    Image subImage = getSubImage((int)srcx, (int)srcy, srcwidth, srcheight);
    int width = (int)(local_x2 - local_x);
    int height = (int)(local_y2 - local_y);
    subImage.draw(local_x, local_y, width, height, filter);
  }
  
  public void drawCentered(float local_x, float local_y)
  {
    throw new UnsupportedOperationException();
  }
  
  public void drawEmbedded(float local_x, float local_y, float local_x2, float local_y2, float srcx, float srcy, float srcx2, float srcy2, Color filter)
  {
    throw new UnsupportedOperationException();
  }
  
  public void drawEmbedded(float local_x, float local_y, float local_x2, float local_y2, float srcx, float srcy, float srcx2, float srcy2)
  {
    throw new UnsupportedOperationException();
  }
  
  public void drawFlash(float local_x, float local_y, float width, float height, Color col)
  {
    throw new UnsupportedOperationException();
  }
  
  public void drawSheared(float local_x, float local_y, float hshear, float vshear)
  {
    throw new UnsupportedOperationException();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.BigImage
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */