package org.newdawn.slick.tests;

import java.io.PrintStream;
import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.pbuffer.GraphicsFactory;

public class ImageGraphicsTest
  extends BasicGame
{
  private Image preloaded;
  private Image target;
  private Image cut;
  private Graphics gTarget;
  private Graphics offscreenPreload;
  private Image testImage;
  private Font testFont;
  private float ang;
  private String using = "none";
  
  public ImageGraphicsTest()
  {
    super("Image Graphics Test");
  }
  
  public void init(GameContainer container)
    throws SlickException
  {
    this.testImage = new Image("testdata/logo.png");
    this.preloaded = new Image("testdata/logo.png");
    this.testFont = new AngelCodeFont("testdata/hiero.fnt", "testdata/hiero.png");
    this.target = new Image(400, 300);
    this.cut = new Image(100, 100);
    this.gTarget = this.target.getGraphics();
    this.offscreenPreload = this.preloaded.getGraphics();
    this.offscreenPreload.drawString("Drawing over a loaded image", 5.0F, 15.0F);
    this.offscreenPreload.setLineWidth(5.0F);
    this.offscreenPreload.setAntiAlias(true);
    this.offscreenPreload.setColor(Color.blue.brighter());
    this.offscreenPreload.drawOval(200.0F, 30.0F, 50.0F, 50.0F);
    this.offscreenPreload.setColor(Color.white);
    this.offscreenPreload.drawRect(190.0F, 20.0F, 70.0F, 70.0F);
    this.offscreenPreload.flush();
    if (GraphicsFactory.usingFBO()) {
      this.using = "FBO (Frame Buffer Objects)";
    } else if (GraphicsFactory.usingPBuffer()) {
      this.using = "Pbuffer (Pixel Buffers)";
    }
    System.out.println(this.preloaded.getColor(50, 50));
  }
  
  public void render(GameContainer container, Graphics local_g)
    throws SlickException
  {
    this.gTarget.setBackground(new Color(0, 0, 0, 0));
    this.gTarget.clear();
    this.gTarget.rotate(200.0F, 160.0F, this.ang);
    this.gTarget.setFont(this.testFont);
    this.gTarget.fillRect(10.0F, 10.0F, 50.0F, 50.0F);
    this.gTarget.drawString("HELLO WORLD", 10.0F, 10.0F);
    this.gTarget.drawImage(this.testImage, 100.0F, 150.0F);
    this.gTarget.drawImage(this.testImage, 100.0F, 50.0F);
    this.gTarget.drawImage(this.testImage, 50.0F, 75.0F);
    this.gTarget.flush();
    local_g.setColor(Color.red);
    local_g.fillRect(250.0F, 50.0F, 200.0F, 200.0F);
    this.target.draw(300.0F, 100.0F);
    this.target.draw(300.0F, 410.0F, 200.0F, 150.0F);
    this.target.draw(505.0F, 410.0F, 100.0F, 75.0F);
    local_g.setColor(Color.white);
    local_g.drawString("Testing On Offscreen Buffer", 300.0F, 80.0F);
    local_g.setColor(Color.green);
    local_g.drawRect(300.0F, 100.0F, this.target.getWidth(), this.target.getHeight());
    local_g.drawRect(300.0F, 410.0F, this.target.getWidth() / 2, this.target.getHeight() / 2);
    local_g.drawRect(505.0F, 410.0F, this.target.getWidth() / 4, this.target.getHeight() / 4);
    local_g.setColor(Color.white);
    local_g.drawString("Testing Font On Back Buffer", 10.0F, 100.0F);
    local_g.drawString("Using: " + this.using, 10.0F, 580.0F);
    local_g.setColor(Color.red);
    local_g.fillRect(10.0F, 120.0F, 200.0F, 5.0F);
    int local_xp = (int)(60.0D + Math.sin(this.ang / 60.0F) * 50.0D);
    local_g.copyArea(this.cut, local_xp, 50);
    this.cut.draw(30.0F, 250.0F);
    local_g.setColor(Color.white);
    local_g.drawRect(30.0F, 250.0F, this.cut.getWidth(), this.cut.getHeight());
    local_g.setColor(Color.gray);
    local_g.drawRect(local_xp, 50.0F, this.cut.getWidth(), this.cut.getHeight());
    this.preloaded.draw(2.0F, 400.0F);
    local_g.setColor(Color.blue);
    local_g.drawRect(2.0F, 400.0F, this.preloaded.getWidth(), this.preloaded.getHeight());
  }
  
  public void update(GameContainer container, int delta)
  {
    this.ang += delta * 0.1F;
  }
  
  public static void main(String[] argv)
  {
    try
    {
      GraphicsFactory.setUseFBO(false);
      AppGameContainer container = new AppGameContainer(new ImageGraphicsTest());
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
 * Qualified Name:     org.newdawn.slick.tests.ImageGraphicsTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */