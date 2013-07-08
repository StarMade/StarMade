package org.newdawn.slick.tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Curve;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;

public class CurveTest
  extends BasicGame
{
  private Curve curve;
  private Vector2f field_344 = new Vector2f(100.0F, 300.0F);
  private Vector2f field_345 = new Vector2f(100.0F, 100.0F);
  private Vector2f field_346 = new Vector2f(300.0F, 100.0F);
  private Vector2f field_347 = new Vector2f(300.0F, 300.0F);
  private Polygon poly;
  
  public CurveTest()
  {
    super("Curve Test");
  }
  
  public void init(GameContainer container)
    throws SlickException
  {
    container.getGraphics().setBackground(Color.white);
    this.curve = new Curve(this.field_347, this.field_346, this.field_345, this.field_344);
    this.poly = new Polygon();
    this.poly.addPoint(500.0F, 200.0F);
    this.poly.addPoint(600.0F, 200.0F);
    this.poly.addPoint(700.0F, 300.0F);
    this.poly.addPoint(400.0F, 300.0F);
  }
  
  public void update(GameContainer container, int delta)
    throws SlickException
  {}
  
  private void drawMarker(Graphics local_g, Vector2f local_p)
  {
    local_g.drawRect(local_p.field_1680 - 5.0F, local_p.field_1681 - 5.0F, 10.0F, 10.0F);
  }
  
  public void render(GameContainer container, Graphics local_g)
    throws SlickException
  {
    local_g.setColor(Color.gray);
    drawMarker(local_g, this.field_344);
    drawMarker(local_g, this.field_347);
    local_g.setColor(Color.red);
    drawMarker(local_g, this.field_345);
    drawMarker(local_g, this.field_346);
    local_g.setColor(Color.black);
    local_g.draw(this.curve);
    local_g.fill(this.curve);
    local_g.draw(this.poly);
    local_g.fill(this.poly);
  }
  
  public static void main(String[] argv)
  {
    try
    {
      AppGameContainer container = new AppGameContainer(new CurveTest());
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
 * Qualified Name:     org.newdawn.slick.tests.CurveTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */