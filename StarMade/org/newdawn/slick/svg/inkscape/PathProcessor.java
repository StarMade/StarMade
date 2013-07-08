/*   1:    */package org.newdawn.slick.svg.inkscape;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import java.util.StringTokenizer;
/*   5:    */import org.newdawn.slick.geom.Path;
/*   6:    */import org.newdawn.slick.geom.Shape;
/*   7:    */import org.newdawn.slick.geom.Transform;
/*   8:    */import org.newdawn.slick.svg.Diagram;
/*   9:    */import org.newdawn.slick.svg.Figure;
/*  10:    */import org.newdawn.slick.svg.Loader;
/*  11:    */import org.newdawn.slick.svg.NonGeometricData;
/*  12:    */import org.newdawn.slick.svg.ParsingException;
/*  13:    */import org.w3c.dom.Element;
/*  14:    */
/*  26:    */public class PathProcessor
/*  27:    */  implements ElementProcessor
/*  28:    */{
/*  29:    */  private static Path processPoly(Element element, StringTokenizer tokens)
/*  30:    */    throws ParsingException
/*  31:    */  {
/*  32: 32 */    int count = 0;
/*  33:    */    
/*  34: 34 */    ArrayList pts = new ArrayList();
/*  35: 35 */    boolean moved = false;
/*  36: 36 */    boolean reasonToBePath = false;
/*  37: 37 */    Path path = null;
/*  38:    */    
/*  39: 39 */    while (tokens.hasMoreTokens()) {
/*  40:    */      try {
/*  41: 41 */        String nextToken = tokens.nextToken();
/*  42: 42 */        if (nextToken.equals("L")) {
/*  43: 43 */          float x = Float.parseFloat(tokens.nextToken());
/*  44: 44 */          float y = Float.parseFloat(tokens.nextToken());
/*  45: 45 */          path.lineTo(x, y);
/*  47:    */        }
/*  48: 48 */        else if (nextToken.equals("z")) {
/*  49: 49 */          path.close();
/*  51:    */        }
/*  52: 52 */        else if (nextToken.equals("M")) {
/*  53: 53 */          if (!moved) {
/*  54: 54 */            moved = true;
/*  55: 55 */            float x = Float.parseFloat(tokens.nextToken());
/*  56: 56 */            float y = Float.parseFloat(tokens.nextToken());
/*  57: 57 */            path = new Path(x, y);
/*  58:    */          }
/*  59:    */          else
/*  60:    */          {
/*  61: 61 */            reasonToBePath = true;
/*  62: 62 */            float x = Float.parseFloat(tokens.nextToken());
/*  63: 63 */            float y = Float.parseFloat(tokens.nextToken());
/*  64: 64 */            path.startHole(x, y);
/*  65:    */          }
/*  66:    */          
/*  67:    */        }
/*  68: 68 */        else if (nextToken.equals("C")) {
/*  69: 69 */          reasonToBePath = true;
/*  70: 70 */          float cx1 = Float.parseFloat(tokens.nextToken());
/*  71: 71 */          float cy1 = Float.parseFloat(tokens.nextToken());
/*  72: 72 */          float cx2 = Float.parseFloat(tokens.nextToken());
/*  73: 73 */          float cy2 = Float.parseFloat(tokens.nextToken());
/*  74: 74 */          float x = Float.parseFloat(tokens.nextToken());
/*  75: 75 */          float y = Float.parseFloat(tokens.nextToken());
/*  76: 76 */          path.curveTo(x, y, cx1, cy1, cx2, cy2);
/*  77:    */        }
/*  78:    */      }
/*  79:    */      catch (NumberFormatException e) {
/*  80: 80 */        throw new ParsingException(element.getAttribute("id"), "Invalid token in points list", e);
/*  81:    */      }
/*  82:    */    }
/*  83:    */    
/*  84: 84 */    if (!reasonToBePath) {
/*  85: 85 */      return null;
/*  86:    */    }
/*  87:    */    
/*  88: 88 */    return path;
/*  89:    */  }
/*  90:    */  
/*  93:    */  public void process(Loader loader, Element element, Diagram diagram, Transform t)
/*  94:    */    throws ParsingException
/*  95:    */  {
/*  96: 96 */    Transform transform = Util.getTransform(element);
/*  97: 97 */    transform = new Transform(t, transform);
/*  98:    */    
/*  99: 99 */    String points = element.getAttribute("points");
/* 100:100 */    if (element.getNodeName().equals("path")) {
/* 101:101 */      points = element.getAttribute("d");
/* 102:    */    }
/* 103:    */    
/* 104:104 */    StringTokenizer tokens = new StringTokenizer(points, ", ");
/* 105:105 */    Path path = processPoly(element, tokens);
/* 106:106 */    NonGeometricData data = Util.getNonGeometricData(element);
/* 107:107 */    if (path != null) {
/* 108:108 */      Shape shape = path.transform(transform);
/* 109:    */      
/* 110:110 */      diagram.addFigure(new Figure(4, shape, data, transform));
/* 111:    */    }
/* 112:    */  }
/* 113:    */  
/* 116:    */  public boolean handles(Element element)
/* 117:    */  {
/* 118:118 */    if ((element.getNodeName().equals("path")) && 
/* 119:119 */      (!"arc".equals(element.getAttributeNS("http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd", "type")))) {
/* 120:120 */      return true;
/* 121:    */    }
/* 122:    */    
/* 124:124 */    return false;
/* 125:    */  }
/* 126:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.svg.inkscape.PathProcessor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */