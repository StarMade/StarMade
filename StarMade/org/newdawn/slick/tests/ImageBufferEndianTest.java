package org.newdawn.slick.tests;

import java.nio.ByteOrder;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.ImageBuffer;
import org.newdawn.slick.SlickException;

public class ImageBufferEndianTest
  extends BasicGame
{
  private ImageBuffer redImageBuffer;
  private ImageBuffer blueImageBuffer;
  private Image fromRed;
  private Image fromBlue;
  private String endian;
  
  public ImageBufferEndianTest()
  {
    super("ImageBuffer Endian Test");
  }
  
  public static void main(String[] args)
  {
    try
    {
      AppGameContainer container = new AppGameContainer(new ImageBufferEndianTest());
      container.setDisplayMode(800, 600, false);
      container.start();
    }
    catch (SlickException container)
    {
      container.printStackTrace();
    }
  }
  
  public void render(GameContainer container, Graphics local_g)
    throws SlickException
  {
    local_g.setColor(Color.white);
    local_g.drawString("Endianness is " + this.endian, 10.0F, 100.0F);
    local_g.drawString("Image below should be red", 10.0F, 200.0F);
    local_g.drawImage(this.fromRed, 10.0F, 220.0F);
    local_g.drawString("Image below should be blue", 410.0F, 200.0F);
    local_g.drawImage(this.fromBlue, 410.0F, 220.0F);
  }
  
  public void init(GameContainer container)
    throws SlickException
  {
    if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN) {
      this.endian = "Big endian";
    } else if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
      this.endian = "Little endian";
    } else {
      this.endian = "no idea";
    }
    this.redImageBuffer = new ImageBuffer(100, 100);
    fillImageBufferWithColor(this.redImageBuffer, Color.red, 100, 100);
    this.blueImageBuffer = new ImageBuffer(100, 100);
    fillImageBufferWithColor(this.blueImageBuffer, Color.blue, 100, 100);
    this.fromRed = this.redImageBuffer.getImage();
    this.fromBlue = this.blueImageBuffer.getImage();
  }
  
  private void fillImageBufferWithColor(ImageBuffer buffer, Color local_c, int width, int height)
  {
    for (int local_x = 0; local_x < width; local_x++) {
      for (int local_y = 0; local_y < height; local_y++) {
        buffer.setRGBA(local_x, local_y, local_c.getRed(), local_c.getGreen(), local_c.getBlue(), local_c.getAlpha());
      }
    }
  }
  
  public void update(GameContainer container, int delta)
    throws SlickException
  {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.tests.ImageBufferEndianTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */