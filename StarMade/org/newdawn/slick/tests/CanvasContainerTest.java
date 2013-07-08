package org.newdawn.slick.tests;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.CanvasGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class CanvasContainerTest
  extends BasicGame
{
  private Image tga;
  private Image scaleMe;
  private Image scaled;
  private Image gif;
  private Image image;
  private Image subImage;
  private float rot;
  
  public CanvasContainerTest()
  {
    super("Canvas Container Test");
  }
  
  public void init(GameContainer container)
    throws SlickException
  {
    this.image = (this.tga = new Image("testdata/logo.tga"));
    this.scaleMe = new Image("testdata/logo.tga", true, 2);
    this.gif = new Image("testdata/logo.gif");
    this.scaled = this.gif.getScaledCopy(120, 120);
    this.subImage = this.image.getSubImage(200, 0, 70, 260);
    this.rot = 0.0F;
  }
  
  public void render(GameContainer container, Graphics local_g)
  {
    this.image.draw(0.0F, 0.0F);
    this.image.draw(500.0F, 0.0F, 200.0F, 100.0F);
    this.scaleMe.draw(500.0F, 100.0F, 200.0F, 100.0F);
    this.scaled.draw(400.0F, 500.0F);
    Image flipped = this.scaled.getFlippedCopy(true, false);
    flipped.draw(520.0F, 500.0F);
    Image flipped2 = flipped.getFlippedCopy(false, true);
    flipped2.draw(520.0F, 380.0F);
    Image flipped3 = flipped2.getFlippedCopy(true, false);
    flipped3.draw(400.0F, 380.0F);
    for (int local_i = 0; local_i < 3; local_i++) {
      this.subImage.draw(200 + local_i * 30, 300.0F);
    }
    local_g.translate(500.0F, 200.0F);
    local_g.rotate(50.0F, 50.0F, this.rot);
    local_g.scale(0.3F, 0.3F);
    this.image.draw();
    local_g.resetTransform();
  }
  
  public void update(GameContainer container, int delta)
  {
    this.rot += delta * 0.1F;
    if (this.rot > 360.0F) {
      this.rot -= 360.0F;
    }
  }
  
  public static void main(String[] argv)
  {
    try
    {
      CanvasGameContainer container = new CanvasGameContainer(new CanvasContainerTest());
      Frame frame = new Frame("Test");
      frame.setLayout(new GridLayout(1, 2));
      frame.setSize(500, 500);
      frame.add(container);
      frame.addWindowListener(new WindowAdapter()
      {
        public void windowClosing(WindowEvent local_e)
        {
          System.exit(0);
        }
      });
      frame.setVisible(true);
      container.start();
    }
    catch (Exception container)
    {
      container.printStackTrace();
    }
  }
  
  public void keyPressed(int key, char local_c)
  {
    if (key == 57) {
      if (this.image == this.gif) {
        this.image = this.tga;
      } else {
        this.image = this.gif;
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.tests.CanvasContainerTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */