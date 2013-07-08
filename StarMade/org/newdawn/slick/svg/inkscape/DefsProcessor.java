/*   1:    */package org.newdawn.slick.svg.inkscape;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import org.newdawn.slick.Color;
/*   5:    */import org.newdawn.slick.geom.Transform;
/*   6:    */import org.newdawn.slick.svg.Diagram;
/*   7:    */import org.newdawn.slick.svg.Gradient;
/*   8:    */import org.newdawn.slick.svg.Loader;
/*   9:    */import org.newdawn.slick.svg.ParsingException;
/*  10:    */import org.newdawn.slick.util.Log;
/*  11:    */import org.w3c.dom.Element;
/*  12:    */import org.w3c.dom.NodeList;
/*  13:    */
/*  21:    */public class DefsProcessor
/*  22:    */  implements ElementProcessor
/*  23:    */{
/*  24:    */  public boolean handles(Element element)
/*  25:    */  {
/*  26: 26 */    if (element.getNodeName().equals("defs")) {
/*  27: 27 */      return true;
/*  28:    */    }
/*  29:    */    
/*  30: 30 */    return false;
/*  31:    */  }
/*  32:    */  
/*  34:    */  public void process(Loader loader, Element element, Diagram diagram, Transform transform)
/*  35:    */    throws ParsingException
/*  36:    */  {
/*  37: 37 */    NodeList patterns = element.getElementsByTagName("pattern");
/*  38:    */    
/*  39: 39 */    for (int i = 0; i < patterns.getLength(); i++) {
/*  40: 40 */      Element pattern = (Element)patterns.item(i);
/*  41: 41 */      NodeList list = pattern.getElementsByTagName("image");
/*  42: 42 */      if (list.getLength() == 0) {
/*  43: 43 */        Log.warn("Pattern 1981 does not specify an image. Only image patterns are supported.");
/*  44:    */      }
/*  45:    */      else {
/*  46: 46 */        Element image = (Element)list.item(0);
/*  47:    */        
/*  48: 48 */        String patternName = pattern.getAttribute("id");
/*  49: 49 */        String ref = image.getAttributeNS("http://www.w3.org/1999/xlink", "href");
/*  50: 50 */        diagram.addPatternDef(patternName, ref);
/*  51:    */      }
/*  52:    */    }
/*  53: 53 */    NodeList linear = element.getElementsByTagName("linearGradient");
/*  54: 54 */    ArrayList toResolve = new ArrayList();
/*  55:    */    
/*  56: 56 */    for (int i = 0; i < linear.getLength(); i++) {
/*  57: 57 */      Element lin = (Element)linear.item(i);
/*  58: 58 */      String name = lin.getAttribute("id");
/*  59: 59 */      Gradient gradient = new Gradient(name, false);
/*  60:    */      
/*  61: 61 */      gradient.setTransform(Util.getTransform(lin, "gradientTransform"));
/*  62:    */      
/*  63: 63 */      if (stringLength(lin.getAttribute("x1")) > 0) {
/*  64: 64 */        gradient.setX1(Float.parseFloat(lin.getAttribute("x1")));
/*  65:    */      }
/*  66: 66 */      if (stringLength(lin.getAttribute("x2")) > 0) {
/*  67: 67 */        gradient.setX2(Float.parseFloat(lin.getAttribute("x2")));
/*  68:    */      }
/*  69: 69 */      if (stringLength(lin.getAttribute("y1")) > 0) {
/*  70: 70 */        gradient.setY1(Float.parseFloat(lin.getAttribute("y1")));
/*  71:    */      }
/*  72: 72 */      if (stringLength(lin.getAttribute("y2")) > 0) {
/*  73: 73 */        gradient.setY2(Float.parseFloat(lin.getAttribute("y2")));
/*  74:    */      }
/*  75:    */      
/*  76: 76 */      String ref = lin.getAttributeNS("http://www.w3.org/1999/xlink", "href");
/*  77: 77 */      if (stringLength(ref) > 0) {
/*  78: 78 */        gradient.reference(ref.substring(1));
/*  79: 79 */        toResolve.add(gradient);
/*  80:    */      } else {
/*  81: 81 */        NodeList steps = lin.getElementsByTagName("stop");
/*  82: 82 */        for (int j = 0; j < steps.getLength(); j++) {
/*  83: 83 */          Element s = (Element)steps.item(j);
/*  84: 84 */          float offset = Float.parseFloat(s.getAttribute("offset"));
/*  85:    */          
/*  86: 86 */          String colInt = Util.extractStyle(s.getAttribute("style"), "stop-color");
/*  87: 87 */          String opaInt = Util.extractStyle(s.getAttribute("style"), "stop-opacity");
/*  88:    */          
/*  89: 89 */          int col = Integer.parseInt(colInt.substring(1), 16);
/*  90: 90 */          Color stopColor = new Color(col);
/*  91: 91 */          stopColor.a = Float.parseFloat(opaInt);
/*  92:    */          
/*  93: 93 */          gradient.addStep(offset, stopColor);
/*  94:    */        }
/*  95:    */        
/*  96: 96 */        gradient.getImage();
/*  97:    */      }
/*  98:    */      
/*  99: 99 */      diagram.addGradient(name, gradient);
/* 100:    */    }
/* 101:    */    
/* 102:102 */    NodeList radial = element.getElementsByTagName("radialGradient");
/* 103:103 */    for (int i = 0; i < radial.getLength(); i++) {
/* 104:104 */      Element rad = (Element)radial.item(i);
/* 105:105 */      String name = rad.getAttribute("id");
/* 106:106 */      Gradient gradient = new Gradient(name, true);
/* 107:    */      
/* 108:108 */      gradient.setTransform(Util.getTransform(rad, "gradientTransform"));
/* 109:    */      
/* 110:110 */      if (stringLength(rad.getAttribute("cx")) > 0) {
/* 111:111 */        gradient.setX1(Float.parseFloat(rad.getAttribute("cx")));
/* 112:    */      }
/* 113:113 */      if (stringLength(rad.getAttribute("cy")) > 0) {
/* 114:114 */        gradient.setY1(Float.parseFloat(rad.getAttribute("cy")));
/* 115:    */      }
/* 116:116 */      if (stringLength(rad.getAttribute("fx")) > 0) {
/* 117:117 */        gradient.setX2(Float.parseFloat(rad.getAttribute("fx")));
/* 118:    */      }
/* 119:119 */      if (stringLength(rad.getAttribute("fy")) > 0) {
/* 120:120 */        gradient.setY2(Float.parseFloat(rad.getAttribute("fy")));
/* 121:    */      }
/* 122:122 */      if (stringLength(rad.getAttribute("r")) > 0) {
/* 123:123 */        gradient.setR(Float.parseFloat(rad.getAttribute("r")));
/* 124:    */      }
/* 125:    */      
/* 126:126 */      String ref = rad.getAttributeNS("http://www.w3.org/1999/xlink", "href");
/* 127:127 */      if (stringLength(ref) > 0) {
/* 128:128 */        gradient.reference(ref.substring(1));
/* 129:129 */        toResolve.add(gradient);
/* 130:    */      } else {
/* 131:131 */        NodeList steps = rad.getElementsByTagName("stop");
/* 132:132 */        for (int j = 0; j < steps.getLength(); j++) {
/* 133:133 */          Element s = (Element)steps.item(j);
/* 134:134 */          float offset = Float.parseFloat(s.getAttribute("offset"));
/* 135:    */          
/* 136:136 */          String colInt = Util.extractStyle(s.getAttribute("style"), "stop-color");
/* 137:137 */          String opaInt = Util.extractStyle(s.getAttribute("style"), "stop-opacity");
/* 138:    */          
/* 139:139 */          int col = Integer.parseInt(colInt.substring(1), 16);
/* 140:140 */          Color stopColor = new Color(col);
/* 141:141 */          stopColor.a = Float.parseFloat(opaInt);
/* 142:    */          
/* 143:143 */          gradient.addStep(offset, stopColor);
/* 144:    */        }
/* 145:    */        
/* 146:146 */        gradient.getImage();
/* 147:    */      }
/* 148:    */      
/* 149:149 */      diagram.addGradient(name, gradient);
/* 150:    */    }
/* 151:    */    
/* 152:152 */    for (int i = 0; i < toResolve.size(); i++) {
/* 153:153 */      ((Gradient)toResolve.get(i)).resolve(diagram);
/* 154:154 */      ((Gradient)toResolve.get(i)).getImage();
/* 155:    */    }
/* 156:    */  }
/* 157:    */  
/* 163:    */  private int stringLength(String value)
/* 164:    */  {
/* 165:165 */    if (value == null) {
/* 166:166 */      return 0;
/* 167:    */    }
/* 168:    */    
/* 169:169 */    return value.length();
/* 170:    */  }
/* 171:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.svg.inkscape.DefsProcessor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */