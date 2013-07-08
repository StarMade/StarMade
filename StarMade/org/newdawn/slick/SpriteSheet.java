package org.newdawn.slick;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.newdawn.slick.opengl.Texture;

public class SpriteSheet
  extends Image
{
  private int field_398;
  private int field_399;
  private int margin = 0;
  private Image[][] subImages;
  private int spacing;
  private Image target = this;
  
  public SpriteSheet(URL ref, int local_tw, int local_th)
    throws SlickException, IOException
  {
    this(new Image(ref.openStream(), ref.toString(), false), local_tw, local_th);
  }
  
  public SpriteSheet(Image image, int local_tw, int local_th)
  {
    super(image);
    this.field_398 = local_tw;
    this.field_399 = local_th;
    initImpl();
  }
  
  public SpriteSheet(Image image, int local_tw, int local_th, int spacing, int margin)
  {
    super(image);
    this.field_398 = local_tw;
    this.field_399 = local_th;
    this.spacing = spacing;
    this.margin = margin;
    initImpl();
  }
  
  public SpriteSheet(Image image, int local_tw, int local_th, int spacing)
  {
    this(image, local_tw, local_th, spacing, 0);
  }
  
  public SpriteSheet(String ref, int local_tw, int local_th, int spacing)
    throws SlickException
  {
    this(ref, local_tw, local_th, null, spacing);
  }
  
  public SpriteSheet(String ref, int local_tw, int local_th)
    throws SlickException
  {
    this(ref, local_tw, local_th, null);
  }
  
  public SpriteSheet(String ref, int local_tw, int local_th, Color col)
    throws SlickException
  {
    this(ref, local_tw, local_th, col, 0);
  }
  
  public SpriteSheet(String ref, int local_tw, int local_th, Color col, int spacing)
    throws SlickException
  {
    super(ref, false, 2, col);
    this.field_398 = local_tw;
    this.field_399 = local_th;
    this.spacing = spacing;
  }
  
  public SpriteSheet(String name, InputStream ref, int local_tw, int local_th)
    throws SlickException
  {
    super(ref, name, false);
    this.field_398 = local_tw;
    this.field_399 = local_th;
  }
  
  protected void initImpl()
  {
    if (this.subImages != null) {
      return;
    }
    int tilesAcross = (getWidth() - this.margin * 2 - this.field_398) / (this.field_398 + this.spacing) + 1;
    int tilesDown = (getHeight() - this.margin * 2 - this.field_399) / (this.field_399 + this.spacing) + 1;
    if ((getHeight() - this.field_399) % (this.field_399 + this.spacing) != 0) {
      tilesDown++;
    }
    this.subImages = new Image[tilesAcross][tilesDown];
    for (int local_x = 0; local_x < tilesAcross; local_x++) {
      for (int local_y = 0; local_y < tilesDown; local_y++) {
        this.subImages[local_x][local_y] = getSprite(local_x, local_y);
      }
    }
  }
  
  public Image getSubImage(int local_x, int local_y)
  {
    init();
    if ((local_x < 0) || (local_x >= this.subImages.length)) {
      throw new RuntimeException("SubImage out of sheet bounds: " + local_x + "," + local_y);
    }
    if ((local_y < 0) || (local_y >= this.subImages[0].length)) {
      throw new RuntimeException("SubImage out of sheet bounds: " + local_x + "," + local_y);
    }
    return this.subImages[local_x][local_y];
  }
  
  public Image getSprite(int local_x, int local_y)
  {
    this.target.init();
    initImpl();
    if ((local_x < 0) || (local_x >= this.subImages.length)) {
      throw new RuntimeException("SubImage out of sheet bounds: " + local_x + "," + local_y);
    }
    if ((local_y < 0) || (local_y >= this.subImages[0].length)) {
      throw new RuntimeException("SubImage out of sheet bounds: " + local_x + "," + local_y);
    }
    return this.target.getSubImage(local_x * (this.field_398 + this.spacing) + this.margin, local_y * (this.field_399 + this.spacing) + this.margin, this.field_398, this.field_399);
  }
  
  public int getHorizontalCount()
  {
    this.target.init();
    initImpl();
    return this.subImages.length;
  }
  
  public int getVerticalCount()
  {
    this.target.init();
    initImpl();
    return this.subImages[0].length;
  }
  
  public void renderInUse(int local_x, int local_y, int local_sx, int local_sy)
  {
    this.subImages[local_sx][local_sy].drawEmbedded(local_x, local_y, this.field_398, this.field_399);
  }
  
  public void endUse()
  {
    if (this.target == this)
    {
      super.endUse();
      return;
    }
    this.target.endUse();
  }
  
  public void startUse()
  {
    if (this.target == this)
    {
      super.startUse();
      return;
    }
    this.target.startUse();
  }
  
  public void setTexture(Texture texture)
  {
    if (this.target == this)
    {
      super.setTexture(texture);
      return;
    }
    this.target.setTexture(texture);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.SpriteSheet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */