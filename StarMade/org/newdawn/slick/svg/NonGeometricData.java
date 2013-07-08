/*   1:    */package org.newdawn.slick.svg;
/*   2:    */
/*   3:    */import java.util.Properties;
/*   4:    */import org.newdawn.slick.Color;
/*   5:    */
/*  25:    */public class NonGeometricData
/*  26:    */{
/*  27:    */  public static final String ID = "id";
/*  28:    */  public static final String FILL = "fill";
/*  29:    */  public static final String STROKE = "stroke";
/*  30:    */  public static final String OPACITY = "opacity";
/*  31:    */  public static final String STROKE_WIDTH = "stroke-width";
/*  32:    */  public static final String STROKE_MITERLIMIT = "stroke-miterlimit";
/*  33:    */  public static final String STROKE_DASHARRAY = "stroke-dasharray";
/*  34:    */  public static final String STROKE_DASHOFFSET = "stroke-dashoffset";
/*  35:    */  public static final String STROKE_OPACITY = "stroke-opacity";
/*  36:    */  public static final String NONE = "none";
/*  37: 37 */  private String metaData = "";
/*  38:    */  
/*  39: 39 */  private Properties props = new Properties();
/*  40:    */  
/*  45:    */  public NonGeometricData(String metaData)
/*  46:    */  {
/*  47: 47 */    this.metaData = metaData;
/*  48: 48 */    addAttribute("stroke-width", "1");
/*  49:    */  }
/*  50:    */  
/*  56:    */  private String morphColor(String str)
/*  57:    */  {
/*  58: 58 */    if (str.equals("")) {
/*  59: 59 */      return "#000000";
/*  60:    */    }
/*  61: 61 */    if (str.equals("white")) {
/*  62: 62 */      return "#ffffff";
/*  63:    */    }
/*  64: 64 */    if (str.equals("black")) {
/*  65: 65 */      return "#000000";
/*  66:    */    }
/*  67:    */    
/*  68: 68 */    return str;
/*  69:    */  }
/*  70:    */  
/*  76:    */  public void addAttribute(String attribute, String value)
/*  77:    */  {
/*  78: 78 */    if (value == null) {
/*  79: 79 */      value = "";
/*  80:    */    }
/*  81:    */    
/*  82: 82 */    if (attribute.equals("fill")) {
/*  83: 83 */      value = morphColor(value);
/*  84:    */    }
/*  85: 85 */    if ((attribute.equals("stroke-opacity")) && 
/*  86: 86 */      (value.equals("0"))) {
/*  87: 87 */      this.props.setProperty("stroke", "none");
/*  88:    */    }
/*  89:    */    
/*  90: 90 */    if (attribute.equals("stroke-width")) {
/*  91: 91 */      if (value.equals("")) {
/*  92: 92 */        value = "1";
/*  93:    */      }
/*  94: 94 */      if (value.endsWith("px")) {
/*  95: 95 */        value = value.substring(0, value.length() - 2);
/*  96:    */      }
/*  97:    */    }
/*  98: 98 */    if (attribute.equals("stroke")) {
/*  99: 99 */      if ("none".equals(this.props.getProperty("stroke"))) {
/* 100:100 */        return;
/* 101:    */      }
/* 102:102 */      if ("".equals(this.props.getProperty("stroke"))) {
/* 103:103 */        return;
/* 104:    */      }
/* 105:105 */      value = morphColor(value);
/* 106:    */    }
/* 107:    */    
/* 108:108 */    this.props.setProperty(attribute, value);
/* 109:    */  }
/* 110:    */  
/* 116:    */  public boolean isColor(String attribute)
/* 117:    */  {
/* 118:118 */    return getAttribute(attribute).startsWith("#");
/* 119:    */  }
/* 120:    */  
/* 126:    */  public String getMetaData()
/* 127:    */  {
/* 128:128 */    return this.metaData;
/* 129:    */  }
/* 130:    */  
/* 136:    */  public String getAttribute(String attribute)
/* 137:    */  {
/* 138:138 */    return this.props.getProperty(attribute);
/* 139:    */  }
/* 140:    */  
/* 146:    */  public Color getAsColor(String attribute)
/* 147:    */  {
/* 148:148 */    if (!isColor(attribute)) {
/* 149:149 */      throw new RuntimeException("Attribute " + attribute + " is not specified as a color:" + getAttribute(attribute));
/* 150:    */    }
/* 151:    */    
/* 152:152 */    int col = Integer.parseInt(getAttribute(attribute).substring(1), 16);
/* 153:    */    
/* 154:154 */    return new Color(col);
/* 155:    */  }
/* 156:    */  
/* 162:    */  public String getAsReference(String attribute)
/* 163:    */  {
/* 164:164 */    String value = getAttribute(attribute);
/* 165:165 */    if (value.length() < 7) {
/* 166:166 */      return "";
/* 167:    */    }
/* 168:    */    
/* 169:169 */    value = value.substring(5, value.length() - 1);
/* 170:    */    
/* 171:171 */    return value;
/* 172:    */  }
/* 173:    */  
/* 179:    */  public float getAsFloat(String attribute)
/* 180:    */  {
/* 181:181 */    String value = getAttribute(attribute);
/* 182:182 */    if (value == null) {
/* 183:183 */      return 0.0F;
/* 184:    */    }
/* 185:    */    try
/* 186:    */    {
/* 187:187 */      return Float.parseFloat(value);
/* 188:    */    } catch (NumberFormatException e) {
/* 189:189 */      throw new RuntimeException("Attribute " + attribute + " is not specified as a float:" + getAttribute(attribute));
/* 190:    */    }
/* 191:    */  }
/* 192:    */  
/* 197:    */  public boolean isFilled()
/* 198:    */  {
/* 199:199 */    return isColor("fill");
/* 200:    */  }
/* 201:    */  
/* 206:    */  public boolean isStroked()
/* 207:    */  {
/* 208:208 */    return (isColor("stroke")) && (getAsFloat("stroke-width") > 0.0F);
/* 209:    */  }
/* 210:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.svg.NonGeometricData
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */