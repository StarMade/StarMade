package org.newdawn.slick.svg;

import java.io.PrintStream;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.ShapeRenderer;
import org.newdawn.slick.geom.TexCoordGenerator;
import org.newdawn.slick.opengl.TextureImpl;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;

public class SimpleDiagramRenderer
{
  protected static SGL field_2143 = ;
  public Diagram diagram;
  public int list = -1;
  
  public SimpleDiagramRenderer(Diagram diagram)
  {
    this.diagram = diagram;
  }
  
  public void render(Graphics local_g)
  {
    if (this.list == -1)
    {
      this.list = field_2143.glGenLists(1);
      field_2143.glNewList(this.list, 4864);
      render(local_g, this.diagram);
      field_2143.glEndList();
    }
    field_2143.glCallList(this.list);
    TextureImpl.bindNone();
  }
  
  public static void render(Graphics local_g, Diagram diagram)
  {
    for (int local_i = 0; local_i < diagram.getFigureCount(); local_i++)
    {
      Figure figure = diagram.getFigure(local_i);
      if (figure.getData().isFilled())
      {
        if (figure.getData().isColor("fill"))
        {
          local_g.setColor(figure.getData().getAsColor("fill"));
          local_g.fill(diagram.getFigure(local_i).getShape());
          local_g.setAntiAlias(true);
          local_g.draw(diagram.getFigure(local_i).getShape());
          local_g.setAntiAlias(false);
        }
        String fill = figure.getData().getAsReference("fill");
        if (diagram.getPatternDef(fill) != null) {
          System.out.println("PATTERN");
        }
        if (diagram.getGradient(fill) != null)
        {
          Gradient gradient = diagram.getGradient(fill);
          Shape shape = diagram.getFigure(local_i).getShape();
          TexCoordGenerator local_fg = null;
          if (gradient.isRadial()) {
            local_fg = new RadialGradientFill(shape, diagram.getFigure(local_i).getTransform(), gradient);
          } else {
            local_fg = new LinearGradientFill(shape, diagram.getFigure(local_i).getTransform(), gradient);
          }
          Color.white.bind();
          ShapeRenderer.texture(shape, gradient.getImage(), local_fg);
        }
      }
      if ((figure.getData().isStroked()) && (figure.getData().isColor("stroke")))
      {
        local_g.setColor(figure.getData().getAsColor("stroke"));
        local_g.setLineWidth(figure.getData().getAsFloat("stroke-width"));
        local_g.setAntiAlias(true);
        local_g.draw(diagram.getFigure(local_i).getShape());
        local_g.setAntiAlias(false);
        local_g.resetLineWidth();
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.svg.SimpleDiagramRenderer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */