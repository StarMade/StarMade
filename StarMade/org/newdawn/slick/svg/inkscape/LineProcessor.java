package org.newdawn.slick.svg.inkscape;

import java.util.StringTokenizer;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.svg.Diagram;
import org.newdawn.slick.svg.Figure;
import org.newdawn.slick.svg.Loader;
import org.newdawn.slick.svg.NonGeometricData;
import org.newdawn.slick.svg.ParsingException;
import org.w3c.dom.Element;

public class LineProcessor
  implements ElementProcessor
{
  private static int processPoly(Polygon poly, Element element, StringTokenizer tokens)
    throws ParsingException
  {
    int count = 0;
    while (tokens.hasMoreTokens())
    {
      String nextToken = tokens.nextToken();
      if (!nextToken.equals("L"))
      {
        if (nextToken.equals("z")) {
          break;
        }
        if (!nextToken.equals("M"))
        {
          if (nextToken.equals("C")) {
            return 0;
          }
          String tokenX = nextToken;
          String tokenY = tokens.nextToken();
          try
          {
            float local_x = Float.parseFloat(tokenX);
            float local_y = Float.parseFloat(tokenY);
            poly.addPoint(local_x, local_y);
            count++;
          }
          catch (NumberFormatException local_x)
          {
            throw new ParsingException(element.getAttribute("id"), "Invalid token in points list", local_x);
          }
        }
      }
    }
    return count;
  }
  
  public void process(Loader loader, Element element, Diagram diagram, Transform local_t)
    throws ParsingException
  {
    Transform transform = Util.getTransform(element);
    transform = new Transform(local_t, transform);
    float local_y2;
    if (element.getNodeName().equals("line"))
    {
      float local_x1 = Float.parseFloat(element.getAttribute("x1"));
      float local_x2 = Float.parseFloat(element.getAttribute("x2"));
      float local_y1 = Float.parseFloat(element.getAttribute("y1"));
      local_y2 = Float.parseFloat(element.getAttribute("y2"));
    }
    else
    {
      String points = element.getAttribute("d");
      StringTokenizer tokens = new StringTokenizer(points, ", ");
      Polygon poly = new Polygon();
      float local_y2;
      if (processPoly(poly, element, tokens) == 2)
      {
        float local_x1 = poly.getPoint(0)[0];
        float local_y1 = poly.getPoint(0)[1];
        float local_x2 = poly.getPoint(1)[0];
        local_y2 = poly.getPoint(1)[1];
      }
      else
      {
        return;
      }
    }
    float local_y2;
    float local_x2;
    float local_y1;
    float local_x1;
    float[] points = { local_x1, local_y1, local_x2, local_y2 };
    float[] tokens = new float[4];
    transform.transform(points, 0, tokens, 0, 2);
    Line poly = new Line(tokens[0], tokens[1], tokens[2], tokens[3]);
    NonGeometricData data = Util.getNonGeometricData(element);
    data.addAttribute("x1", "" + local_x1);
    data.addAttribute("x2", "" + local_x2);
    data.addAttribute("y1", "" + local_y1);
    data.addAttribute("y2", "" + local_y2);
    diagram.addFigure(new Figure(2, poly, data, transform));
  }
  
  public boolean handles(Element element)
  {
    if (element.getNodeName().equals("line")) {
      return true;
    }
    return (element.getNodeName().equals("path")) && (!"arc".equals(element.getAttributeNS("http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd", "type")));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.svg.inkscape.LineProcessor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */