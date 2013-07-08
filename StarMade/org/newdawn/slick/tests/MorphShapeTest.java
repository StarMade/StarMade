package org.newdawn.slick.tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.MorphShape;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

public class MorphShapeTest
  extends BasicGame
{
  private Shape field_412;
  private Shape field_413;
  private Shape field_414;
  private MorphShape morph;
  private float time;
  
  public MorphShapeTest()
  {
    super("MorphShapeTest");
  }
  
  public void init(GameContainer container)
    throws SlickException
  {
    this.field_412 = new Rectangle(100.0F, 100.0F, 50.0F, 200.0F);
    this.field_412 = this.field_412.transform(Transform.createRotateTransform(0.1F, 100.0F, 100.0F));
    this.field_413 = new Rectangle(200.0F, 100.0F, 50.0F, 200.0F);
    this.field_413 = this.field_413.transform(Transform.createRotateTransform(-0.6F, 100.0F, 100.0F));
    this.field_414 = new Rectangle(300.0F, 100.0F, 50.0F, 200.0F);
    this.field_414 = this.field_414.transform(Transform.createRotateTransform(-0.2F, 100.0F, 100.0F));
    this.morph = new MorphShape(this.field_412);
    this.morph.addShape(this.field_413);
    this.morph.addShape(this.field_414);
    container.setVSync(true);
  }
  
  public void update(GameContainer container, int delta)
    throws SlickException
  {
    this.time += delta * 0.001F;
    this.morph.setMorphTime(this.time);
  }
  
  public void render(GameContainer container, Graphics local_g)
    throws SlickException
  {
    local_g.setColor(Color.green);
    local_g.draw(this.field_412);
    local_g.setColor(Color.red);
    local_g.draw(this.field_413);
    local_g.setColor(Color.blue);
    local_g.draw(this.field_414);
    local_g.setColor(Color.white);
    local_g.draw(this.morph);
  }
  
  public static void main(String[] argv)
  {
    try
    {
      AppGameContainer container = new AppGameContainer(new MorphShapeTest());
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
 * Qualified Name:     org.newdawn.slick.tests.MorphShapeTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */