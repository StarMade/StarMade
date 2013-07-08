/*   1:    */package org.newdawn.slick.svg.inkscape;
/*   2:    */
/*   3:    */import java.util.StringTokenizer;
/*   4:    */import org.newdawn.slick.geom.Transform;
/*   5:    */import org.newdawn.slick.svg.NonGeometricData;
/*   6:    */import org.newdawn.slick.svg.ParsingException;
/*   7:    */import org.w3c.dom.Element;
/*   8:    */
/*  22:    */public class Util
/*  23:    */{
/*  24:    */  public static final String INKSCAPE = "http://www.inkscape.org/namespaces/inkscape";
/*  25:    */  public static final String SODIPODI = "http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd";
/*  26:    */  public static final String XLINK = "http://www.w3.org/1999/xlink";
/*  27:    */  
/*  28:    */  static NonGeometricData getNonGeometricData(Element element)
/*  29:    */  {
/*  30: 30 */    String meta = getMetaData(element);
/*  31:    */    
/*  32: 32 */    NonGeometricData data = new InkscapeNonGeometricData(meta, element);
/*  33: 33 */    data.addAttribute("id", element.getAttribute("id"));
/*  34: 34 */    data.addAttribute("fill", getStyle(element, "fill"));
/*  35: 35 */    data.addAttribute("stroke", getStyle(element, "stroke"));
/*  36: 36 */    data.addAttribute("opacity", getStyle(element, "opacity"));
/*  37: 37 */    data.addAttribute("stroke-dasharray", getStyle(element, "stroke-dasharray"));
/*  38: 38 */    data.addAttribute("stroke-dashoffset", getStyle(element, "stroke-dashoffset"));
/*  39: 39 */    data.addAttribute("stroke-miterlimit", getStyle(element, "stroke-miterlimit"));
/*  40: 40 */    data.addAttribute("stroke-opacity", getStyle(element, "stroke-opacity"));
/*  41: 41 */    data.addAttribute("stroke-width", getStyle(element, "stroke-width"));
/*  42:    */    
/*  43: 43 */    return data;
/*  44:    */  }
/*  45:    */  
/*  52:    */  static String getMetaData(Element element)
/*  53:    */  {
/*  54: 54 */    String label = element.getAttributeNS("http://www.inkscape.org/namespaces/inkscape", "label");
/*  55: 55 */    if ((label != null) && (!label.equals(""))) {
/*  56: 56 */      return label;
/*  57:    */    }
/*  58:    */    
/*  59: 59 */    return element.getAttribute("id");
/*  60:    */  }
/*  61:    */  
/*  68:    */  static String getStyle(Element element, String styleName)
/*  69:    */  {
/*  70: 70 */    String value = element.getAttribute(styleName);
/*  71:    */    
/*  72: 72 */    if ((value != null) && (value.length() > 0)) {
/*  73: 73 */      return value;
/*  74:    */    }
/*  75:    */    
/*  76: 76 */    String style = element.getAttribute("style");
/*  77: 77 */    return extractStyle(style, styleName);
/*  78:    */  }
/*  79:    */  
/*  86:    */  static String extractStyle(String style, String attribute)
/*  87:    */  {
/*  88: 88 */    if (style == null) {
/*  89: 89 */      return "";
/*  90:    */    }
/*  91:    */    
/*  92: 92 */    StringTokenizer tokens = new StringTokenizer(style, ";");
/*  93:    */    
/*  94: 94 */    while (tokens.hasMoreTokens()) {
/*  95: 95 */      String token = tokens.nextToken();
/*  96: 96 */      String key = token.substring(0, token.indexOf(':'));
/*  97: 97 */      if (key.equals(attribute)) {
/*  98: 98 */        return token.substring(token.indexOf(':') + 1);
/*  99:    */      }
/* 100:    */    }
/* 101:    */    
/* 102:102 */    return "";
/* 103:    */  }
/* 104:    */  
/* 110:    */  static Transform getTransform(Element element)
/* 111:    */  {
/* 112:112 */    return getTransform(element, "transform");
/* 113:    */  }
/* 114:    */  
/* 121:    */  static Transform getTransform(Element element, String attribute)
/* 122:    */  {
/* 123:123 */    String str = element.getAttribute(attribute);
/* 124:124 */    if (str == null) {
/* 125:125 */      return new Transform();
/* 126:    */    }
/* 127:    */    
/* 128:128 */    if (str.equals(""))
/* 129:129 */      return new Transform();
/* 130:130 */    if (str.startsWith("translate")) {
/* 131:131 */      str = str.substring(0, str.length() - 1);
/* 132:132 */      str = str.substring("translate(".length());
/* 133:133 */      StringTokenizer tokens = new StringTokenizer(str, ", ");
/* 134:134 */      float x = Float.parseFloat(tokens.nextToken());
/* 135:135 */      float y = Float.parseFloat(tokens.nextToken());
/* 136:    */      
/* 137:137 */      return Transform.createTranslateTransform(x, y); }
/* 138:138 */    if (str.startsWith("matrix")) {
/* 139:139 */      float[] pose = new float[6];
/* 140:140 */      str = str.substring(0, str.length() - 1);
/* 141:141 */      str = str.substring("matrix(".length());
/* 142:142 */      StringTokenizer tokens = new StringTokenizer(str, ", ");
/* 143:143 */      float[] tr = new float[6];
/* 144:144 */      for (int j = 0; j < tr.length; j++) {
/* 145:145 */        tr[j] = Float.parseFloat(tokens.nextToken());
/* 146:    */      }
/* 147:    */      
/* 148:148 */      pose[0] = tr[0];
/* 149:149 */      pose[1] = tr[2];
/* 150:150 */      pose[2] = tr[4];
/* 151:151 */      pose[3] = tr[1];
/* 152:152 */      pose[4] = tr[3];
/* 153:153 */      pose[5] = tr[5];
/* 154:    */      
/* 155:155 */      return new Transform(pose);
/* 156:    */    }
/* 157:    */    
/* 158:158 */    return new Transform();
/* 159:    */  }
/* 160:    */  
/* 168:    */  static float getFloatAttribute(Element element, String attr)
/* 169:    */    throws ParsingException
/* 170:    */  {
/* 171:171 */    String cx = element.getAttribute(attr);
/* 172:172 */    if ((cx == null) || (cx.equals(""))) {
/* 173:173 */      cx = element.getAttributeNS("http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd", attr);
/* 174:    */    }
/* 175:    */    try
/* 176:    */    {
/* 177:177 */      return Float.parseFloat(cx);
/* 178:    */    } catch (NumberFormatException e) {
/* 179:179 */      throw new ParsingException(element, "Invalid value for: " + attr, e);
/* 180:    */    }
/* 181:    */  }
/* 182:    */  
/* 188:    */  public static String getAsReference(String value)
/* 189:    */  {
/* 190:190 */    if (value.length() < 2) {
/* 191:191 */      return "";
/* 192:    */    }
/* 193:    */    
/* 194:194 */    value = value.substring(1, value.length());
/* 195:    */    
/* 196:196 */    return value;
/* 197:    */  }
/* 198:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.svg.inkscape.Util
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */