package org.newdawn.slick.tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.opengl.renderer.Renderer;

public class GeomTest
  extends BasicGame
{
  private Shape rect = new Rectangle(100.0F, 100.0F, 100.0F, 100.0F);
  private Shape circle = new Circle(500.0F, 200.0F, 50.0F);
  private Shape rect1 = new Rectangle(150.0F, 120.0F, 50.0F, 100.0F).transform(Transform.createTranslateTransform(50.0F, 50.0F));
  private Shape rect2 = new Rectangle(310.0F, 210.0F, 50.0F, 100.0F).transform(Transform.createRotateTransform((float)Math.toRadians(45.0D), 335.0F, 260.0F));
  private Shape circle1 = new Circle(150.0F, 90.0F, 30.0F);
  private Shape circle2 = new Circle(310.0F, 110.0F, 70.0F);
  private Shape circle3 = new Ellipse(510.0F, 150.0F, 70.0F, 70.0F);
  private Shape circle4 = new Ellipse(510.0F, 350.0F, 30.0F, 30.0F).transform(Transform.createTranslateTransform(-510.0F, -350.0F)).transform(Transform.createScaleTransform(2.0F, 2.0F)).transform(Transform.createTranslateTransform(510.0F, 350.0F));
  private Shape roundRect = new RoundedRectangle(50.0F, 175.0F, 100.0F, 100.0F, 20.0F);
  private Shape roundRect2 = new RoundedRectangle(50.0F, 280.0F, 50.0F, 50.0F, 20.0F, 20, 5);
  
  public GeomTest()
  {
    super("Geom Test");
  }
  
  public void init(GameContainer container)
    throws SlickException
  {}
  
  public void render(GameContainer container, Graphics local_g)
  {
    local_g.setColor(Color.white);
    local_g.drawString("Red indicates a collision, green indicates no collision", 50.0F, 420.0F);
    local_g.drawString("White are the targets", 50.0F, 435.0F);
    local_g.pushTransform();
    local_g.translate(100.0F, 100.0F);
    local_g.pushTransform();
    local_g.translate(-50.0F, -50.0F);
    local_g.scale(10.0F, 10.0F);
    local_g.setColor(Color.red);
    local_g.fillRect(0.0F, 0.0F, 5.0F, 5.0F);
    local_g.setColor(Color.white);
    local_g.drawRect(0.0F, 0.0F, 5.0F, 5.0F);
    local_g.popTransform();
    local_g.setColor(Color.green);
    local_g.fillRect(20.0F, 20.0F, 50.0F, 50.0F);
    local_g.popTransform();
    local_g.setColor(Color.white);
    local_g.draw(this.rect);
    local_g.draw(this.circle);
    local_g.setColor(this.rect1.intersects(this.rect) ? Color.red : Color.green);
    local_g.draw(this.rect1);
    local_g.setColor(this.rect2.intersects(this.rect) ? Color.red : Color.green);
    local_g.draw(this.rect2);
    local_g.setColor(this.roundRect.intersects(this.rect) ? Color.red : Color.green);
    local_g.draw(this.roundRect);
    local_g.setColor(this.circle1.intersects(this.rect) ? Color.red : Color.green);
    local_g.draw(this.circle1);
    local_g.setColor(this.circle2.intersects(this.rect) ? Color.red : Color.green);
    local_g.draw(this.circle2);
    local_g.setColor(this.circle3.intersects(this.circle) ? Color.red : Color.green);
    local_g.fill(this.circle3);
    local_g.setColor(this.circle4.intersects(this.circle) ? Color.red : Color.green);
    local_g.draw(this.circle4);
    local_g.fill(this.roundRect2);
    local_g.setColor(Color.blue);
    local_g.draw(this.roundRect2);
    local_g.setColor(Color.blue);
    local_g.draw(new Circle(100.0F, 100.0F, 50.0F));
    local_g.drawRect(50.0F, 50.0F, 100.0F, 100.0F);
  }
  
  public void update(GameContainer container, int delta) {}
  
  public void keyPressed(int key, char local_c)
  {
    if (key == 1) {
      System.exit(0);
    }
  }
  
  public static void main(String[] argv)
  {
    try
    {
      Renderer.setRenderer(2);
      AppGameContainer container = new AppGameContainer(new GeomTest());
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
 * Qualified Name:     org.newdawn.slick.tests.GeomTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */