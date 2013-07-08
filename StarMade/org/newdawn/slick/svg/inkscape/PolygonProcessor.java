/*   1:    */package org.newdawn.slick.svg.inkscape;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import java.util.StringTokenizer;
/*   5:    */import org.newdawn.slick.geom.Polygon;
/*   6:    */import org.newdawn.slick.geom.Shape;
/*   7:    */import org.newdawn.slick.geom.Transform;
/*   8:    */import org.newdawn.slick.svg.Diagram;
/*   9:    */import org.newdawn.slick.svg.Figure;
/*  10:    */import org.newdawn.slick.svg.Loader;
/*  11:    */import org.newdawn.slick.svg.NonGeometricData;
/*  12:    */import org.newdawn.slick.svg.ParsingException;
/*  13:    */import org.w3c.dom.Element;
/*  14:    */
/*  27:    */public class PolygonProcessor
/*  28:    */  implements ElementProcessor
/*  29:    */{
/*  30:    */  private static int processPoly(Polygon poly, Element element, StringTokenizer tokens)
/*  31:    */    throws ParsingException
/*  32:    */  {
/*  33: 33 */    int count = 0;
/*  34:    */    
/*  35: 35 */    ArrayList pts = new ArrayList();
/*  36: 36 */    boolean moved = false;
/*  37: 37 */    boolean closed = false;
/*  38:    */    
/*  39: 39 */    while (tokens.hasMoreTokens()) {
/*  40: 40 */      String nextToken = tokens.nextToken();
/*  41: 41 */      if (!nextToken.equals("L"))
/*  42:    */      {
/*  44: 44 */        if (nextToken.equals("z")) {
/*  45: 45 */          closed = true;
/*  46: 46 */          break;
/*  47:    */        }
/*  48: 48 */        if (nextToken.equals("M")) {
/*  49: 49 */          if (!moved) {
/*  50: 50 */            moved = true;
/*  51:    */          }
/*  52:    */          else
/*  53:    */          {
/*  54: 54 */            return 0; }
/*  55:    */        } else {
/*  56: 56 */          if (nextToken.equals("C")) {
/*  57: 57 */            return 0;
/*  58:    */          }
/*  59:    */          
/*  60: 60 */          String tokenX = nextToken;
/*  61: 61 */          String tokenY = tokens.nextToken();
/*  62:    */          try
/*  63:    */          {
/*  64: 64 */            float x = Float.parseFloat(tokenX);
/*  65: 65 */            float y = Float.parseFloat(tokenY);
/*  66:    */            
/*  67: 67 */            poly.addPoint(x, y);
/*  68: 68 */            count++;
/*  69:    */          } catch (NumberFormatException e) {
/*  70: 70 */            throw new ParsingException(element.getAttribute("id"), "Invalid token in points list", e);
/*  71:    */          }
/*  72:    */        }
/*  73:    */      } }
/*  74: 74 */    poly.setClosed(closed);
/*  75: 75 */    return count;
/*  76:    */  }
/*  77:    */  
/*  80:    */  public void process(Loader loader, Element element, Diagram diagram, Transform t)
/*  81:    */    throws ParsingException
/*  82:    */  {
/*  83: 83 */    Transform transform = Util.getTransform(element);
/*  84: 84 */    transform = new Transform(t, transform);
/*  85:    */    
/*  86: 86 */    String points = element.getAttribute("points");
/*  87: 87 */    if (element.getNodeName().equals("path")) {
/*  88: 88 */      points = element.getAttribute("d");
/*  89:    */    }
/*  90:    */    
/*  91: 91 */    StringTokenizer tokens = new StringTokenizer(points, ", ");
/*  92: 92 */    Polygon poly = new Polygon();
/*  93: 93 */    int count = processPoly(poly, element, tokens);
/*  94:    */    
/*  95: 95 */    NonGeometricData data = Util.getNonGeometricData(element);
/*  96: 96 */    if (count > 3) {
/*  97: 97 */      Shape shape = poly.transform(transform);
/*  98:    */      
/*  99: 99 */      diagram.addFigure(new Figure(5, shape, data, transform));
/* 100:    */    }
/* 101:    */  }
/* 102:    */  
/* 105:    */  public boolean handles(Element element)
/* 106:    */  {
/* 107:107 */    if (element.getNodeName().equals("polygon")) {
/* 108:108 */      return true;
/* 109:    */    }
/* 110:    */    
/* 111:111 */    if ((element.getNodeName().equals("path")) && 
/* 112:112 */      (!"arc".equals(element.getAttributeNS("http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd", "type")))) {
/* 113:113 */      return true;
/* 114:    */    }
/* 115:    */    
/* 117:117 */    return false;
/* 118:    */  }
/* 119:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.svg.inkscape.PolygonProcessor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */