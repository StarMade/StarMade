package org.newdawn.slick.svg.inkscape;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.svg.Diagram;
import org.newdawn.slick.svg.Figure;
import org.newdawn.slick.svg.Loader;
import org.newdawn.slick.svg.NonGeometricData;
import org.newdawn.slick.svg.ParsingException;
import org.w3c.dom.Element;

public class RectProcessor
  implements ElementProcessor
{
  public void process(Loader loader, Element element, Diagram diagram, Transform local_t)
    throws ParsingException
  {
    Transform transform = Util.getTransform(element);
    transform = new Transform(local_t, transform);
    float width = Float.parseFloat(element.getAttribute("width"));
    float height = Float.parseFloat(element.getAttribute("height"));
    float local_x = Float.parseFloat(element.getAttribute("x"));
    float local_y = Float.parseFloat(element.getAttribute("y"));
    Rectangle rect = new Rectangle(local_x, local_y, width + 1.0F, height + 1.0F);
    Shape shape = rect.transform(transform);
    NonGeometricData data = Util.getNonGeometricData(element);
    data.addAttribute("width", "" + width);
    data.addAttribute("height", "" + height);
    data.addAttribute("x", "" + local_x);
    data.addAttribute("y", "" + local_y);
    diagram.addFigure(new Figure(3, shape, data, transform));
  }
  
  public boolean handles(Element element)
  {
    return element.getNodeName().equals("rect");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.svg.inkscape.RectProcessor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */