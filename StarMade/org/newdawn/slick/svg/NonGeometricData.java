package org.newdawn.slick.svg;

import java.util.Properties;
import org.newdawn.slick.Color;

public class NonGeometricData
{
  public static final String field_481 = "id";
  public static final String FILL = "fill";
  public static final String STROKE = "stroke";
  public static final String OPACITY = "opacity";
  public static final String STROKE_WIDTH = "stroke-width";
  public static final String STROKE_MITERLIMIT = "stroke-miterlimit";
  public static final String STROKE_DASHARRAY = "stroke-dasharray";
  public static final String STROKE_DASHOFFSET = "stroke-dashoffset";
  public static final String STROKE_OPACITY = "stroke-opacity";
  public static final String NONE = "none";
  private String metaData = "";
  private Properties props = new Properties();
  
  public NonGeometricData(String metaData)
  {
    this.metaData = metaData;
    addAttribute("stroke-width", "1");
  }
  
  private String morphColor(String str)
  {
    if (str.equals("")) {
      return "#000000";
    }
    if (str.equals("white")) {
      return "#ffffff";
    }
    if (str.equals("black")) {
      return "#000000";
    }
    return str;
  }
  
  public void addAttribute(String attribute, String value)
  {
    if (value == null) {
      value = "";
    }
    if (attribute.equals("fill")) {
      value = morphColor(value);
    }
    if ((attribute.equals("stroke-opacity")) && (value.equals("0"))) {
      this.props.setProperty("stroke", "none");
    }
    if (attribute.equals("stroke-width"))
    {
      if (value.equals("")) {
        value = "1";
      }
      if (value.endsWith("px")) {
        value = value.substring(0, value.length() - 2);
      }
    }
    if (attribute.equals("stroke"))
    {
      if ("none".equals(this.props.getProperty("stroke"))) {
        return;
      }
      if ("".equals(this.props.getProperty("stroke"))) {
        return;
      }
      value = morphColor(value);
    }
    this.props.setProperty(attribute, value);
  }
  
  public boolean isColor(String attribute)
  {
    return getAttribute(attribute).startsWith("#");
  }
  
  public String getMetaData()
  {
    return this.metaData;
  }
  
  public String getAttribute(String attribute)
  {
    return this.props.getProperty(attribute);
  }
  
  public Color getAsColor(String attribute)
  {
    if (!isColor(attribute)) {
      throw new RuntimeException("Attribute " + attribute + " is not specified as a color:" + getAttribute(attribute));
    }
    int col = Integer.parseInt(getAttribute(attribute).substring(1), 16);
    return new Color(col);
  }
  
  public String getAsReference(String attribute)
  {
    String value = getAttribute(attribute);
    if (value.length() < 7) {
      return "";
    }
    value = value.substring(5, value.length() - 1);
    return value;
  }
  
  public float getAsFloat(String attribute)
  {
    String value = getAttribute(attribute);
    if (value == null) {
      return 0.0F;
    }
    try
    {
      return Float.parseFloat(value);
    }
    catch (NumberFormatException local_e)
    {
      throw new RuntimeException("Attribute " + attribute + " is not specified as a float:" + getAttribute(attribute));
    }
  }
  
  public boolean isFilled()
  {
    return isColor("fill");
  }
  
  public boolean isStroked()
  {
    return (isColor("stroke")) && (getAsFloat("stroke-width") > 0.0F);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.svg.NonGeometricData
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */