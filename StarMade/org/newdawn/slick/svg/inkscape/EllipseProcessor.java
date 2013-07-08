package org.newdawn.slick.svg.inkscape;

import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.svg.Diagram;
import org.newdawn.slick.svg.Figure;
import org.newdawn.slick.svg.Loader;
import org.newdawn.slick.svg.NonGeometricData;
import org.newdawn.slick.svg.ParsingException;
import org.w3c.dom.Element;

public class EllipseProcessor
  implements ElementProcessor
{
  public void process(Loader loader, Element element, Diagram diagram, Transform local_t)
    throws ParsingException
  {
    Transform transform = Util.getTransform(element);
    transform = new Transform(local_t, transform);
    float local_x = Util.getFloatAttribute(element, "cx");
    float local_y = Util.getFloatAttribute(element, "cy");
    float local_rx = Util.getFloatAttribute(element, "rx");
    float local_ry = Util.getFloatAttribute(element, "ry");
    Ellipse ellipse = new Ellipse(local_x, local_y, local_rx, local_ry);
    Shape shape = ellipse.transform(transform);
    NonGeometricData data = Util.getNonGeometricData(element);
    data.addAttribute("cx", "" + local_x);
    data.addAttribute("cy", "" + local_y);
    data.addAttribute("rx", "" + local_rx);
    data.addAttribute("ry", "" + local_ry);
    diagram.addFigure(new Figure(1, shape, data, transform));
  }
  
  public boolean handles(Element element)
  {
    if (element.getNodeName().equals("ellipse")) {
      return true;
    }
    return (element.getNodeName().equals("path")) && ("arc".equals(element.getAttributeNS("http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd", "type")));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.svg.inkscape.EllipseProcessor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */