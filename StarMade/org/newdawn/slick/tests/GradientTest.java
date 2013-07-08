package org.newdawn.slick.tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.opengl.renderer.Renderer;

public class GradientTest
  extends BasicGame
{
  private GameContainer container;
  private GradientFill gradient;
  private GradientFill gradient2;
  private GradientFill gradient4;
  private Rectangle rect;
  private Rectangle center;
  private RoundedRectangle round;
  private RoundedRectangle round2;
  private Polygon poly;
  private float ang;
  
  public GradientTest()
  {
    super("Gradient Test");
  }
  
  public void init(GameContainer container)
    throws SlickException
  {
    this.container = container;
    this.rect = new Rectangle(400.0F, 100.0F, 200.0F, 150.0F);
    this.round = new RoundedRectangle(150.0F, 100.0F, 200.0F, 150.0F, 50.0F);
    this.round2 = new RoundedRectangle(150.0F, 300.0F, 200.0F, 150.0F, 50.0F);
    this.center = new Rectangle(350.0F, 250.0F, 100.0F, 100.0F);
    this.poly = new Polygon();
    this.poly.addPoint(400.0F, 350.0F);
    this.poly.addPoint(550.0F, 320.0F);
    this.poly.addPoint(600.0F, 380.0F);
    this.poly.addPoint(620.0F, 450.0F);
    this.poly.addPoint(500.0F, 450.0F);
    this.gradient = new GradientFill(0.0F, -75.0F, Color.red, 0.0F, 75.0F, Color.yellow, true);
    this.gradient2 = new GradientFill(0.0F, -75.0F, Color.blue, 0.0F, 75.0F, Color.white, true);
    this.gradient4 = new GradientFill(-50.0F, -40.0F, Color.green, 50.0F, 40.0F, Color.cyan, true);
  }
  
  public void render(GameContainer container, Graphics local_g)
  {
    local_g.rotate(400.0F, 300.0F, this.ang);
    local_g.fill(this.rect, this.gradient);
    local_g.fill(this.round, this.gradient);
    local_g.fill(this.poly, this.gradient2);
    local_g.fill(this.center, this.gradient4);
    local_g.setAntiAlias(true);
    local_g.setLineWidth(10.0F);
    local_g.draw(this.round2, this.gradient2);
    local_g.setLineWidth(2.0F);
    local_g.draw(this.poly, this.gradient);
    local_g.setAntiAlias(false);
    local_g.fill(this.center, this.gradient4);
    local_g.setAntiAlias(true);
    local_g.setColor(Color.black);
    local_g.draw(this.center);
    local_g.setAntiAlias(false);
  }
  
  public void update(GameContainer container, int delta)
  {
    this.ang += delta * 0.01F;
  }
  
  public static void main(String[] argv)
  {
    try
    {
      Renderer.setRenderer(2);
      AppGameContainer container = new AppGameContainer(new GradientTest());
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
 * Qualified Name:     org.newdawn.slick.tests.GradientTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */