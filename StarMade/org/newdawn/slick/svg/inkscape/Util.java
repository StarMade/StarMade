package org.newdawn.slick.svg.inkscape;

import java.util.StringTokenizer;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.svg.NonGeometricData;
import org.newdawn.slick.svg.ParsingException;
import org.w3c.dom.Element;

public class Util
{
  public static final String INKSCAPE = "http://www.inkscape.org/namespaces/inkscape";
  public static final String SODIPODI = "http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd";
  public static final String XLINK = "http://www.w3.org/1999/xlink";
  
  static NonGeometricData getNonGeometricData(Element element)
  {
    String meta = getMetaData(element);
    NonGeometricData data = new InkscapeNonGeometricData(meta, element);
    data.addAttribute("id", element.getAttribute("id"));
    data.addAttribute("fill", getStyle(element, "fill"));
    data.addAttribute("stroke", getStyle(element, "stroke"));
    data.addAttribute("opacity", getStyle(element, "opacity"));
    data.addAttribute("stroke-dasharray", getStyle(element, "stroke-dasharray"));
    data.addAttribute("stroke-dashoffset", getStyle(element, "stroke-dashoffset"));
    data.addAttribute("stroke-miterlimit", getStyle(element, "stroke-miterlimit"));
    data.addAttribute("stroke-opacity", getStyle(element, "stroke-opacity"));
    data.addAttribute("stroke-width", getStyle(element, "stroke-width"));
    return data;
  }
  
  static String getMetaData(Element element)
  {
    String label = element.getAttributeNS("http://www.inkscape.org/namespaces/inkscape", "label");
    if ((label != null) && (!label.equals(""))) {
      return label;
    }
    return element.getAttribute("id");
  }
  
  static String getStyle(Element element, String styleName)
  {
    String value = element.getAttribute(styleName);
    if ((value != null) && (value.length() > 0)) {
      return value;
    }
    String style = element.getAttribute("style");
    return extractStyle(style, styleName);
  }
  
  static String extractStyle(String style, String attribute)
  {
    if (style == null) {
      return "";
    }
    StringTokenizer tokens = new StringTokenizer(style, ";");
    while (tokens.hasMoreTokens())
    {
      String token = tokens.nextToken();
      String key = token.substring(0, token.indexOf(':'));
      if (key.equals(attribute)) {
        return token.substring(token.indexOf(':') + 1);
      }
    }
    return "";
  }
  
  static Transform getTransform(Element element)
  {
    return getTransform(element, "transform");
  }
  
  static Transform getTransform(Element element, String attribute)
  {
    String str = element.getAttribute(attribute);
    if (str == null) {
      return new Transform();
    }
    if (str.equals("")) {
      return new Transform();
    }
    if (str.startsWith("translate"))
    {
      str = str.substring(0, str.length() - 1);
      str = str.substring("translate(".length());
      StringTokenizer tokens = new StringTokenizer(str, ", ");
      float local_x = Float.parseFloat(tokens.nextToken());
      float local_y = Float.parseFloat(tokens.nextToken());
      return Transform.createTranslateTransform(local_x, local_y);
    }
    if (str.startsWith("matrix"))
    {
      float[] tokens = new float[6];
      str = str.substring(0, str.length() - 1);
      str = str.substring("matrix(".length());
      StringTokenizer local_x = new StringTokenizer(str, ", ");
      float[] local_y = new float[6];
      for (int local_j = 0; local_j < local_y.length; local_j++) {
        local_y[local_j] = Float.parseFloat(local_x.nextToken());
      }
      tokens[0] = local_y[0];
      tokens[1] = local_y[2];
      tokens[2] = local_y[4];
      tokens[3] = local_y[1];
      tokens[4] = local_y[3];
      tokens[5] = local_y[5];
      return new Transform(tokens);
    }
    return new Transform();
  }
  
  static float getFloatAttribute(Element element, String attr)
    throws ParsingException
  {
    String local_cx = element.getAttribute(attr);
    if ((local_cx == null) || (local_cx.equals(""))) {
      local_cx = element.getAttributeNS("http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd", attr);
    }
    try
    {
      return Float.parseFloat(local_cx);
    }
    catch (NumberFormatException local_e)
    {
      throw new ParsingException(element, "Invalid value for: " + attr, local_e);
    }
  }
  
  public static String getAsReference(String value)
  {
    if (value.length() < 2) {
      return "";
    }
    value = value.substring(1, value.length());
    return value;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.svg.inkscape.Util
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */