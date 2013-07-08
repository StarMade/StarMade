package org.newdawn.slick.svg;

import java.util.ArrayList;
import org.newdawn.slick.geom.MorphShape;

public class SVGMorph
  extends Diagram
{
  private ArrayList figures = new ArrayList();
  
  public SVGMorph(Diagram diagram)
  {
    super(diagram.getWidth(), diagram.getHeight());
    for (int local_i = 0; local_i < diagram.getFigureCount(); local_i++)
    {
      Figure figure = diagram.getFigure(local_i);
      Figure copy = new Figure(figure.getType(), new MorphShape(figure.getShape()), figure.getData(), figure.getTransform());
      this.figures.add(copy);
    }
  }
  
  public void addStep(Diagram diagram)
  {
    if (diagram.getFigureCount() != this.figures.size()) {
      throw new RuntimeException("Mismatched diagrams, missing ids");
    }
    for (int local_i = 0; local_i < diagram.getFigureCount(); local_i++)
    {
      Figure figure = diagram.getFigure(local_i);
      String local_id = figure.getData().getMetaData();
      for (int local_j = 0; local_j < this.figures.size(); local_j++)
      {
        Figure existing = (Figure)this.figures.get(local_j);
        if (existing.getData().getMetaData().equals(local_id))
        {
          MorphShape morph = (MorphShape)existing.getShape();
          morph.addShape(figure.getShape());
          break;
        }
      }
    }
  }
  
  public void setExternalDiagram(Diagram diagram)
  {
    for (int local_i = 0; local_i < this.figures.size(); local_i++)
    {
      Figure figure = (Figure)this.figures.get(local_i);
      for (int local_j = 0; local_j < diagram.getFigureCount(); local_j++)
      {
        Figure newBase = diagram.getFigure(local_j);
        if (newBase.getData().getMetaData().equals(figure.getData().getMetaData()))
        {
          MorphShape shape = (MorphShape)figure.getShape();
          shape.setExternalFrame(newBase.getShape());
          break;
        }
      }
    }
  }
  
  public void updateMorphTime(float delta)
  {
    for (int local_i = 0; local_i < this.figures.size(); local_i++)
    {
      Figure figure = (Figure)this.figures.get(local_i);
      MorphShape shape = (MorphShape)figure.getShape();
      shape.updateMorphTime(delta);
    }
  }
  
  public void setMorphTime(float time)
  {
    for (int local_i = 0; local_i < this.figures.size(); local_i++)
    {
      Figure figure = (Figure)this.figures.get(local_i);
      MorphShape shape = (MorphShape)figure.getShape();
      shape.setMorphTime(time);
    }
  }
  
  public int getFigureCount()
  {
    return this.figures.size();
  }
  
  public Figure getFigure(int index)
  {
    return (Figure)this.figures.get(index);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.svg.SVGMorph
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */