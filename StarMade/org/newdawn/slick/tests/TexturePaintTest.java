package org.newdawn.slick.tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.ShapeRenderer;
import org.newdawn.slick.geom.TexCoordGenerator;
import org.newdawn.slick.geom.Vector2f;

public class TexturePaintTest
  extends BasicGame
{
  private Polygon poly = new Polygon();
  private Image image;
  private Rectangle texRect = new Rectangle(50.0F, 50.0F, 100.0F, 100.0F);
  private TexCoordGenerator texPaint;
  
  public TexturePaintTest()
  {
    super("Texture Paint Test");
  }
  
  public void init(GameContainer container)
    throws SlickException
  {
    this.poly.addPoint(120.0F, 120.0F);
    this.poly.addPoint(420.0F, 100.0F);
    this.poly.addPoint(620.0F, 420.0F);
    this.poly.addPoint(300.0F, 320.0F);
    this.image = new Image("testdata/rocks.png");
    this.texPaint = new TexCoordGenerator()
    {
      public Vector2f getCoordFor(float local_x, float local_y)
      {
        float local_tx = (TexturePaintTest.this.texRect.getX() - local_x) / TexturePaintTest.this.texRect.getWidth();
        float local_ty = (TexturePaintTest.this.texRect.getY() - local_y) / TexturePaintTest.this.texRect.getHeight();
        return new Vector2f(local_tx, local_ty);
      }
    };
  }
  
  public void update(GameContainer container, int delta)
    throws SlickException
  {}
  
  public void render(GameContainer container, Graphics local_g)
    throws SlickException
  {
    local_g.setColor(Color.white);
    local_g.texture(this.poly, this.image);
    ShapeRenderer.texture(this.poly, this.image, this.texPaint);
  }
  
  public static void main(String[] argv)
  {
    try
    {
      AppGameContainer container = new AppGameContainer(new TexturePaintTest());
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
 * Qualified Name:     org.newdawn.slick.tests.TexturePaintTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */