package org.newdawn.slick.svg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import org.newdawn.slick.geom.Shape;

public class Diagram
{
  private ArrayList figures = new ArrayList();
  private HashMap patterns = new HashMap();
  private HashMap gradients = new HashMap();
  private HashMap figureMap = new HashMap();
  private float width;
  private float height;
  
  public Diagram(float width, float height)
  {
    this.width = width;
    this.height = height;
  }
  
  public float getWidth()
  {
    return this.width;
  }
  
  public float getHeight()
  {
    return this.height;
  }
  
  public void addPatternDef(String name, String href)
  {
    this.patterns.put(name, href);
  }
  
  public void addGradient(String name, Gradient gradient)
  {
    this.gradients.put(name, gradient);
  }
  
  public String getPatternDef(String name)
  {
    return (String)this.patterns.get(name);
  }
  
  public Gradient getGradient(String name)
  {
    return (Gradient)this.gradients.get(name);
  }
  
  public String[] getPatternDefNames()
  {
    return (String[])this.patterns.keySet().toArray(new String[0]);
  }
  
  public Figure getFigureByID(String local_id)
  {
    return (Figure)this.figureMap.get(local_id);
  }
  
  public void addFigure(Figure figure)
  {
    this.figures.add(figure);
    this.figureMap.put(figure.getData().getAttribute("id"), figure);
    String fillRef = figure.getData().getAsReference("fill");
    Gradient gradient = getGradient(fillRef);
    if ((gradient != null) && (gradient.isRadial())) {
      for (int local_i = 0; local_i < InkscapeLoader.RADIAL_TRIANGULATION_LEVEL; local_i++) {
        figure.getShape().increaseTriangulation();
      }
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
  
  public void removeFigure(Figure figure)
  {
    this.figures.remove(figure);
    this.figureMap.remove(figure.getData().getAttribute("id"));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.svg.Diagram
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */