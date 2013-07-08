package org.newdawn.slick.tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.svg.InkscapeLoader;
import org.newdawn.slick.svg.SimpleDiagramRenderer;

public class InkscapeTest
  extends BasicGame
{
  private SimpleDiagramRenderer[] renderer = new SimpleDiagramRenderer[5];
  private float zoom = 1.0F;
  private float field_54;
  private float field_318;
  
  public InkscapeTest()
  {
    super("Inkscape Test");
  }
  
  public void init(GameContainer container)
    throws SlickException
  {
    container.getGraphics().setBackground(Color.white);
    InkscapeLoader.RADIAL_TRIANGULATION_LEVEL = 2;
    this.renderer[3] = new SimpleDiagramRenderer(InkscapeLoader.load("testdata/svg/clonetest.svg"));
    container.getGraphics().setBackground(new Color(0.5F, 0.7F, 1.0F));
  }
  
  public void update(GameContainer container, int delta)
    throws SlickException
  {
    if (container.getInput().isKeyDown(16))
    {
      this.zoom += delta * 0.01F;
      if (this.zoom > 10.0F) {
        this.zoom = 10.0F;
      }
    }
    if (container.getInput().isKeyDown(30))
    {
      this.zoom -= delta * 0.01F;
      if (this.zoom < 0.1F) {
        this.zoom = 0.1F;
      }
    }
    if (container.getInput().isKeyDown(205)) {
      this.field_54 += delta * 0.1F;
    }
    if (container.getInput().isKeyDown(203)) {
      this.field_54 -= delta * 0.1F;
    }
    if (container.getInput().isKeyDown(208)) {
      this.field_318 += delta * 0.1F;
    }
    if (container.getInput().isKeyDown(200)) {
      this.field_318 -= delta * 0.1F;
    }
  }
  
  public void render(GameContainer container, Graphics local_g)
    throws SlickException
  {
    local_g.scale(this.zoom, this.zoom);
    local_g.translate(this.field_54, this.field_318);
    local_g.scale(0.3F, 0.3F);
    local_g.scale(3.333333F, 3.333333F);
    local_g.translate(400.0F, 0.0F);
    local_g.translate(100.0F, 300.0F);
    local_g.scale(0.7F, 0.7F);
    local_g.scale(1.428572F, 1.428572F);
    local_g.scale(0.5F, 0.5F);
    local_g.translate(-1100.0F, -380.0F);
    this.renderer[3].render(local_g);
    local_g.scale(2.0F, 2.0F);
    local_g.resetTransform();
  }
  
  public static void main(String[] argv)
  {
    try
    {
      Renderer.setRenderer(2);
      Renderer.setLineStripRenderer(4);
      AppGameContainer container = new AppGameContainer(new InkscapeTest());
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
 * Qualified Name:     org.newdawn.slick.tests.InkscapeTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */