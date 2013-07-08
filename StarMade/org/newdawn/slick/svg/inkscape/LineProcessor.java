/*   1:    */package org.newdawn.slick.svg.inkscape;
/*   2:    */
/*   3:    */import java.util.StringTokenizer;
/*   4:    */import org.newdawn.slick.geom.Line;
/*   5:    */import org.newdawn.slick.geom.Polygon;
/*   6:    */import org.newdawn.slick.geom.Transform;
/*   7:    */import org.newdawn.slick.svg.Diagram;
/*   8:    */import org.newdawn.slick.svg.Figure;
/*   9:    */import org.newdawn.slick.svg.Loader;
/*  10:    */import org.newdawn.slick.svg.NonGeometricData;
/*  11:    */import org.newdawn.slick.svg.ParsingException;
/*  12:    */import org.w3c.dom.Element;
/*  13:    */
/*  26:    */public class LineProcessor
/*  27:    */  implements ElementProcessor
/*  28:    */{
/*  29:    */  private static int processPoly(Polygon poly, Element element, StringTokenizer tokens)
/*  30:    */    throws ParsingException
/*  31:    */  {
/*  32: 32 */    int count = 0;
/*  33:    */    
/*  34: 34 */    while (tokens.hasMoreTokens()) {
/*  35: 35 */      String nextToken = tokens.nextToken();
/*  36: 36 */      if (!nextToken.equals("L"))
/*  37:    */      {
/*  39: 39 */        if (nextToken.equals("z")) {
/*  40:    */          break;
/*  41:    */        }
/*  42: 42 */        if (!nextToken.equals("M"))
/*  43:    */        {
/*  45: 45 */          if (nextToken.equals("C")) {
/*  46: 46 */            return 0;
/*  47:    */          }
/*  48:    */          
/*  49: 49 */          String tokenX = nextToken;
/*  50: 50 */          String tokenY = tokens.nextToken();
/*  51:    */          try
/*  52:    */          {
/*  53: 53 */            float x = Float.parseFloat(tokenX);
/*  54: 54 */            float y = Float.parseFloat(tokenY);
/*  55:    */            
/*  56: 56 */            poly.addPoint(x, y);
/*  57: 57 */            count++;
/*  58:    */          } catch (NumberFormatException e) {
/*  59: 59 */            throw new ParsingException(element.getAttribute("id"), "Invalid token in points list", e);
/*  60:    */          }
/*  61:    */        }
/*  62:    */      } }
/*  63: 63 */    return count;
/*  64:    */  }
/*  65:    */  
/*  67:    */  public void process(Loader loader, Element element, Diagram diagram, Transform t)
/*  68:    */    throws ParsingException
/*  69:    */  {
/*  70: 70 */    Transform transform = Util.getTransform(element);
/*  71: 71 */    transform = new Transform(t, transform);
/*  72:    */    
/*  75:    */    float y2;
/*  76:    */    
/*  78: 78 */    if (element.getNodeName().equals("line")) {
/*  79: 79 */      float x1 = Float.parseFloat(element.getAttribute("x1"));
/*  80: 80 */      float x2 = Float.parseFloat(element.getAttribute("x2"));
/*  81: 81 */      float y1 = Float.parseFloat(element.getAttribute("y1"));
/*  82: 82 */      y2 = Float.parseFloat(element.getAttribute("y2"));
/*  83:    */    } else {
/*  84: 84 */      String points = element.getAttribute("d");
/*  85: 85 */      StringTokenizer tokens = new StringTokenizer(points, ", ");
/*  86: 86 */      Polygon poly = new Polygon();
/*  87: 87 */      float y2; if (processPoly(poly, element, tokens) == 2) {
/*  88: 88 */        float x1 = poly.getPoint(0)[0];
/*  89: 89 */        float y1 = poly.getPoint(0)[1];
/*  90: 90 */        float x2 = poly.getPoint(1)[0];
/*  91: 91 */        y2 = poly.getPoint(1)[1];
/*  92:    */      } else { return; } }
/*  93:    */    float y2;
/*  94:    */    float x2;
/*  95:    */    float y1;
/*  96:    */    float x1;
/*  97: 97 */    float[] in = { x1, y1, x2, y2 };
/*  98: 98 */    float[] out = new float[4];
/*  99:    */    
/* 100:100 */    transform.transform(in, 0, out, 0, 2);
/* 101:101 */    Line line = new Line(out[0], out[1], out[2], out[3]);
/* 102:    */    
/* 103:103 */    NonGeometricData data = Util.getNonGeometricData(element);
/* 104:104 */    data.addAttribute("x1", "" + x1);
/* 105:105 */    data.addAttribute("x2", "" + x2);
/* 106:106 */    data.addAttribute("y1", "" + y1);
/* 107:107 */    data.addAttribute("y2", "" + y2);
/* 108:    */    
/* 109:109 */    diagram.addFigure(new Figure(2, line, data, transform));
/* 110:    */  }
/* 111:    */  
/* 114:    */  public boolean handles(Element element)
/* 115:    */  {
/* 116:116 */    if (element.getNodeName().equals("line")) {
/* 117:117 */      return true;
/* 118:    */    }
/* 119:119 */    if ((element.getNodeName().equals("path")) && 
/* 120:120 */      (!"arc".equals(element.getAttributeNS("http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd", "type")))) {
/* 121:121 */      return true;
/* 122:    */    }
/* 123:    */    
/* 125:125 */    return false;
/* 126:    */  }
/* 127:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.svg.inkscape.LineProcessor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */