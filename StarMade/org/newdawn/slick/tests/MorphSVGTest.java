package org.newdawn.slick.tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.svg.Diagram;
import org.newdawn.slick.svg.InkscapeLoader;
import org.newdawn.slick.svg.SVGMorph;
import org.newdawn.slick.svg.SimpleDiagramRenderer;

public class MorphSVGTest
  extends BasicGame
{
  private SVGMorph morph;
  private Diagram base;
  private float time;
  private float field_54 = -300.0F;
  
  public MorphSVGTest()
  {
    super("MorphShapeTest");
  }
  
  public void init(GameContainer container)
    throws SlickException
  {
    this.base = InkscapeLoader.load("testdata/svg/walk1.svg");
    this.morph = new SVGMorph(this.base);
    this.morph.addStep(InkscapeLoader.load("testdata/svg/walk2.svg"));
    this.morph.addStep(InkscapeLoader.load("testdata/svg/walk3.svg"));
    this.morph.addStep(InkscapeLoader.load("testdata/svg/walk4.svg"));
    container.setVSync(true);
  }
  
  public void update(GameContainer container, int delta)
    throws SlickException
  {
    this.morph.updateMorphTime(delta * 0.003F);
    this.field_54 += delta * 0.2F;
    if (this.field_54 > 550.0F) {
      this.field_54 = -450.0F;
    }
  }
  
  public void render(GameContainer container, Graphics local_g)
    throws SlickException
  {
    local_g.translate(this.field_54, 0.0F);
    SimpleDiagramRenderer.render(local_g, this.morph);
  }
  
  public static void main(String[] argv)
  {
    try
    {
      AppGameContainer container = new AppGameContainer(new MorphSVGTest());
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
 * Qualified Name:     org.newdawn.slick.tests.MorphSVGTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */