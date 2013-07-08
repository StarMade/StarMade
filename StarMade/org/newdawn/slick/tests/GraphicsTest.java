package org.newdawn.slick.tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.util.FastTrig;

public class GraphicsTest
  extends BasicGame
{
  private boolean clip;
  private float ang;
  private Image image;
  private Polygon poly;
  private GameContainer container;
  
  public GraphicsTest()
  {
    super("Graphics Test");
  }
  
  public void init(GameContainer container)
    throws SlickException
  {
    this.container = container;
    this.image = new Image("testdata/logo.tga", true);
    Image temp = new Image("testdata/palette_tool.png");
    container.setMouseCursor(temp, 0, 0);
    container.setIcons(new String[] { "testdata/icon.tga" });
    container.setTargetFrameRate(100);
    this.poly = new Polygon();
    float len = 100.0F;
    for (int local_x = 0; local_x < 360; local_x += 30)
    {
      if (len == 100.0F) {
        len = 50.0F;
      } else {
        len = 100.0F;
      }
      this.poly.addPoint((float)FastTrig.cos(Math.toRadians(local_x)) * len, (float)FastTrig.sin(Math.toRadians(local_x)) * len);
    }
  }
  
  public void render(GameContainer container, Graphics local_g)
    throws SlickException
  {
    local_g.setColor(Color.white);
    local_g.setAntiAlias(true);
    for (int local_x = 0; local_x < 360; local_x += 10) {
      local_g.drawLine(700.0F, 100.0F, (int)(700.0D + Math.cos(Math.toRadians(local_x)) * 100.0D), (int)(100.0D + Math.sin(Math.toRadians(local_x)) * 100.0D));
    }
    local_g.setAntiAlias(false);
    local_g.setColor(Color.yellow);
    local_g.drawString("The Graphics Test!", 300.0F, 50.0F);
    local_g.setColor(Color.white);
    local_g.drawString("Space - Toggles clipping", 400.0F, 80.0F);
    local_g.drawString("Frame rate capped to 100", 400.0F, 120.0F);
    if (this.clip)
    {
      local_g.setColor(Color.gray);
      local_g.drawRect(100.0F, 260.0F, 400.0F, 100.0F);
      local_g.setClip(100, 260, 400, 100);
    }
    local_g.setColor(Color.yellow);
    local_g.translate(100.0F, 120.0F);
    local_g.fill(this.poly);
    local_g.setColor(Color.blue);
    local_g.setLineWidth(3.0F);
    local_g.draw(this.poly);
    local_g.setLineWidth(1.0F);
    local_g.translate(0.0F, 230.0F);
    local_g.draw(this.poly);
    local_g.resetTransform();
    local_g.setColor(Color.magenta);
    local_g.drawRoundRect(10.0F, 10.0F, 100.0F, 100.0F, 10);
    local_g.fillRoundRect(10.0F, 210.0F, 100.0F, 100.0F, 10);
    local_g.rotate(400.0F, 300.0F, this.ang);
    local_g.setColor(Color.green);
    local_g.drawRect(200.0F, 200.0F, 200.0F, 200.0F);
    local_g.setColor(Color.blue);
    local_g.fillRect(250.0F, 250.0F, 100.0F, 100.0F);
    local_g.drawImage(this.image, 300.0F, 270.0F);
    local_g.setColor(Color.red);
    local_g.drawOval(100.0F, 100.0F, 200.0F, 200.0F);
    local_g.setColor(Color.red.darker());
    local_g.fillOval(300.0F, 300.0F, 150.0F, 100.0F);
    local_g.setAntiAlias(true);
    local_g.setColor(Color.white);
    local_g.setLineWidth(5.0F);
    local_g.drawOval(300.0F, 300.0F, 150.0F, 100.0F);
    local_g.setAntiAlias(true);
    local_g.resetTransform();
    if (this.clip) {
      local_g.clearClip();
    }
  }
  
  public void update(GameContainer container, int delta)
  {
    this.ang += delta * 0.1F;
  }
  
  public void keyPressed(int key, char local_c)
  {
    if (key == 1) {
      System.exit(0);
    }
    if (key == 57) {
      this.clip = (!this.clip);
    }
  }
  
  public static void main(String[] argv)
  {
    try
    {
      AppGameContainer container = new AppGameContainer(new GraphicsTest());
      container.setDisplayMode(800, 600, false);
      container.start();
    }
    catch (SlickException container)
    {
      container.printStackTrace();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.tests.GraphicsTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */