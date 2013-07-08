package org.newdawn.slick.svg.inkscape;

import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.svg.Diagram;
import org.newdawn.slick.svg.Gradient;
import org.newdawn.slick.svg.Loader;
import org.newdawn.slick.svg.ParsingException;
import org.newdawn.slick.util.Log;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class DefsProcessor
  implements ElementProcessor
{
  public boolean handles(Element element)
  {
    return element.getNodeName().equals("defs");
  }
  
  public void process(Loader loader, Element element, Diagram diagram, Transform transform)
    throws ParsingException
  {
    NodeList patterns = element.getElementsByTagName("pattern");
    for (int local_i = 0; local_i < patterns.getLength(); local_i++)
    {
      Element pattern = (Element)patterns.item(local_i);
      NodeList list = pattern.getElementsByTagName("image");
      if (list.getLength() == 0)
      {
        Log.warn("Pattern 1981 does not specify an image. Only image patterns are supported.");
      }
      else
      {
        Element image = (Element)list.item(0);
        String patternName = pattern.getAttribute("id");
        String ref = image.getAttributeNS("http://www.w3.org/1999/xlink", "href");
        diagram.addPatternDef(patternName, ref);
      }
    }
    NodeList local_i = element.getElementsByTagName("linearGradient");
    ArrayList pattern = new ArrayList();
    for (int list = 0; list < local_i.getLength(); list++)
    {
      Element image = (Element)local_i.item(list);
      String patternName = image.getAttribute("id");
      Gradient ref = new Gradient(patternName, false);
      ref.setTransform(Util.getTransform(image, "gradientTransform"));
      if (stringLength(image.getAttribute("x1")) > 0) {
        ref.setX1(Float.parseFloat(image.getAttribute("x1")));
      }
      if (stringLength(image.getAttribute("x2")) > 0) {
        ref.setX2(Float.parseFloat(image.getAttribute("x2")));
      }
      if (stringLength(image.getAttribute("y1")) > 0) {
        ref.setY1(Float.parseFloat(image.getAttribute("y1")));
      }
      if (stringLength(image.getAttribute("y2")) > 0) {
        ref.setY2(Float.parseFloat(image.getAttribute("y2")));
      }
      String ref = image.getAttributeNS("http://www.w3.org/1999/xlink", "href");
      if (stringLength(ref) > 0)
      {
        ref.reference(ref.substring(1));
        pattern.add(ref);
      }
      else
      {
        NodeList steps = image.getElementsByTagName("stop");
        for (int local_j = 0; local_j < steps.getLength(); local_j++)
        {
          Element local_s = (Element)steps.item(local_j);
          float offset = Float.parseFloat(local_s.getAttribute("offset"));
          String colInt = Util.extractStyle(local_s.getAttribute("style"), "stop-color");
          String opaInt = Util.extractStyle(local_s.getAttribute("style"), "stop-opacity");
          int col = Integer.parseInt(colInt.substring(1), 16);
          Color stopColor = new Color(col);
          stopColor.field_1779 = Float.parseFloat(opaInt);
          ref.addStep(offset, stopColor);
        }
        ref.getImage();
      }
      diagram.addGradient(patternName, ref);
    }
    NodeList list = element.getElementsByTagName("radialGradient");
    for (int image = 0; image < list.getLength(); image++)
    {
      Element patternName = (Element)list.item(image);
      String ref = patternName.getAttribute("id");
      Gradient ref = new Gradient(ref, true);
      ref.setTransform(Util.getTransform(patternName, "gradientTransform"));
      if (stringLength(patternName.getAttribute("cx")) > 0) {
        ref.setX1(Float.parseFloat(patternName.getAttribute("cx")));
      }
      if (stringLength(patternName.getAttribute("cy")) > 0) {
        ref.setY1(Float.parseFloat(patternName.getAttribute("cy")));
      }
      if (stringLength(patternName.getAttribute("fx")) > 0) {
        ref.setX2(Float.parseFloat(patternName.getAttribute("fx")));
      }
      if (stringLength(patternName.getAttribute("fy")) > 0) {
        ref.setY2(Float.parseFloat(patternName.getAttribute("fy")));
      }
      if (stringLength(patternName.getAttribute("r")) > 0) {
        ref.setR(Float.parseFloat(patternName.getAttribute("r")));
      }
      String steps = patternName.getAttributeNS("http://www.w3.org/1999/xlink", "href");
      if (stringLength(steps) > 0)
      {
        ref.reference(steps.substring(1));
        pattern.add(ref);
      }
      else
      {
        NodeList local_j = patternName.getElementsByTagName("stop");
        for (int local_s = 0; local_s < local_j.getLength(); local_s++)
        {
          Element offset = (Element)local_j.item(local_s);
          float colInt = Float.parseFloat(offset.getAttribute("offset"));
          String opaInt = Util.extractStyle(offset.getAttribute("style"), "stop-color");
          String col = Util.extractStyle(offset.getAttribute("style"), "stop-opacity");
          int stopColor = Integer.parseInt(opaInt.substring(1), 16);
          Color stopColor = new Color(stopColor);
          stopColor.field_1779 = Float.parseFloat(col);
          ref.addStep(colInt, stopColor);
        }
        ref.getImage();
      }
      diagram.addGradient(ref, ref);
    }
    for (int image = 0; image < pattern.size(); image++)
    {
      ((Gradient)pattern.get(image)).resolve(diagram);
      ((Gradient)pattern.get(image)).getImage();
    }
  }
  
  private int stringLength(String value)
  {
    if (value == null) {
      return 0;
    }
    return value.length();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.svg.inkscape.DefsProcessor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */