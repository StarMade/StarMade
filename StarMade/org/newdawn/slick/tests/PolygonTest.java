package org.newdawn.slick.tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;

public class PolygonTest
  extends BasicGame
{
  private Polygon poly;
  private boolean field_317;
  private float field_318;
  
  public PolygonTest()
  {
    super("Polygon Test");
  }
  
  public void init(GameContainer container)
    throws SlickException
  {
    this.poly = new Polygon();
    this.poly.addPoint(300.0F, 100.0F);
    this.poly.addPoint(320.0F, 200.0F);
    this.poly.addPoint(350.0F, 210.0F);
    this.poly.addPoint(280.0F, 250.0F);
    this.poly.addPoint(300.0F, 200.0F);
    this.poly.addPoint(240.0F, 150.0F);
  }
  
  public void update(GameContainer container, int delta)
    throws SlickException
  {
    this.field_317 = this.poly.contains(container.getInput().getMouseX(), container.getInput().getMouseY());
    this.poly.setCenterY(0.0F);
  }
  
  public void render(GameContainer container, Graphics local_g)
    throws SlickException
  {
    if (this.field_317)
    {
      local_g.setColor(Color.red);
      local_g.fill(this.poly);
    }
    local_g.setColor(Color.yellow);
    local_g.fillOval(this.poly.getCenterX() - 3.0F, this.poly.getCenterY() - 3.0F, 6.0F, 6.0F);
    local_g.setColor(Color.white);
    local_g.draw(this.poly);
  }
  
  public static void main(String[] argv)
  {
    try
    {
      AppGameContainer container = new AppGameContainer(new PolygonTest(), 640, 480, false);
      container.start();
    }
    catch (Exception container)
    {
      container.printStackTrace();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.tests.PolygonTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */