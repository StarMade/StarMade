/*  1:   */package org.newdawn.slick.svg.inkscape;
/*  2:   */
/*  3:   */import org.newdawn.slick.geom.Ellipse;
/*  4:   */import org.newdawn.slick.geom.Shape;
/*  5:   */import org.newdawn.slick.geom.Transform;
/*  6:   */import org.newdawn.slick.svg.Diagram;
/*  7:   */import org.newdawn.slick.svg.Figure;
/*  8:   */import org.newdawn.slick.svg.Loader;
/*  9:   */import org.newdawn.slick.svg.NonGeometricData;
/* 10:   */import org.newdawn.slick.svg.ParsingException;
/* 11:   */import org.w3c.dom.Element;
/* 12:   */
/* 18:   */public class EllipseProcessor
/* 19:   */  implements ElementProcessor
/* 20:   */{
/* 21:   */  public void process(Loader loader, Element element, Diagram diagram, Transform t)
/* 22:   */    throws ParsingException
/* 23:   */  {
/* 24:24 */    Transform transform = Util.getTransform(element);
/* 25:25 */    transform = new Transform(t, transform);
/* 26:   */    
/* 27:27 */    float x = Util.getFloatAttribute(element, "cx");
/* 28:28 */    float y = Util.getFloatAttribute(element, "cy");
/* 29:29 */    float rx = Util.getFloatAttribute(element, "rx");
/* 30:30 */    float ry = Util.getFloatAttribute(element, "ry");
/* 31:   */    
/* 32:32 */    Ellipse ellipse = new Ellipse(x, y, rx, ry);
/* 33:33 */    Shape shape = ellipse.transform(transform);
/* 34:   */    
/* 35:35 */    NonGeometricData data = Util.getNonGeometricData(element);
/* 36:36 */    data.addAttribute("cx", "" + x);
/* 37:37 */    data.addAttribute("cy", "" + y);
/* 38:38 */    data.addAttribute("rx", "" + rx);
/* 39:39 */    data.addAttribute("ry", "" + ry);
/* 40:   */    
/* 41:41 */    diagram.addFigure(new Figure(1, shape, data, transform));
/* 42:   */  }
/* 43:   */  
/* 46:   */  public boolean handles(Element element)
/* 47:   */  {
/* 48:48 */    if (element.getNodeName().equals("ellipse")) {
/* 49:49 */      return true;
/* 50:   */    }
/* 51:51 */    if ((element.getNodeName().equals("path")) && 
/* 52:52 */      ("arc".equals(element.getAttributeNS("http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd", "type")))) {
/* 53:53 */      return true;
/* 54:   */    }
/* 55:   */    
/* 57:57 */    return false;
/* 58:   */  }
/* 59:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.svg.inkscape.EllipseProcessor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */