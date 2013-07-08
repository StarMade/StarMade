package org.newdawn.slick.tests;

import java.io.IOException;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.imageout.ImageOut;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;

public class ImageOutTest
  extends BasicGame
{
  private GameContainer container;
  private ParticleSystem fire;
  private Graphics field_86;
  private Image copy;
  private String message;
  
  public ImageOutTest()
  {
    super("Image Out Test");
  }
  
  public void init(GameContainer container)
    throws SlickException
  {
    this.container = container;
    try
    {
      this.fire = ParticleIO.loadConfiguredSystem("testdata/system.xml");
    }
    catch (IOException local_e)
    {
      throw new SlickException("Failed to load particle systems", local_e);
    }
    this.copy = new Image(400, 300);
    String[] local_e = ImageOut.getSupportedFormats();
    this.message = "Formats supported: ";
    for (int local_i = 0; local_i < local_e.length; local_i++)
    {
      this.message += local_e[local_i];
      if (local_i < local_e.length - 1) {
        this.message += ",";
      }
    }
  }
  
  public void render(GameContainer container, Graphics local_g)
  {
    local_g.drawString("T - TGA Snapshot", 10.0F, 50.0F);
    local_g.drawString("J - JPG Snapshot", 10.0F, 70.0F);
    local_g.drawString("P - PNG Snapshot", 10.0F, 90.0F);
    local_g.setDrawMode(Graphics.MODE_ADD);
    local_g.drawImage(this.copy, 200.0F, 300.0F);
    local_g.setDrawMode(Graphics.MODE_NORMAL);
    local_g.drawString(this.message, 10.0F, 400.0F);
    local_g.drawRect(200.0F, 0.0F, 400.0F, 300.0F);
    local_g.translate(400.0F, 250.0F);
    this.fire.render();
    this.field_86 = local_g;
  }
  
  private void writeTo(String fname)
    throws SlickException
  {
    this.field_86.copyArea(this.copy, 200, 0);
    ImageOut.write(this.copy, fname);
    this.message = ("Written " + fname);
  }
  
  public void update(GameContainer container, int delta)
    throws SlickException
  {
    this.fire.update(delta);
    if (container.getInput().isKeyPressed(25)) {
      writeTo("ImageOutTest.png");
    }
    if (container.getInput().isKeyPressed(36)) {
      writeTo("ImageOutTest.jpg");
    }
    if (container.getInput().isKeyPressed(20)) {
      writeTo("ImageOutTest.tga");
    }
  }
  
  public static void main(String[] argv)
  {
    try
    {
      AppGameContainer container = new AppGameContainer(new ImageOutTest());
      container.setDisplayMode(800, 600, false);
      container.start();
    }
    catch (SlickException container)
    {
      container.printStackTrace();
    }
  }
  
  public void keyPressed(int key, char local_c)
  {
    if (key == 1) {
      this.container.exit();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.tests.ImageOutTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */