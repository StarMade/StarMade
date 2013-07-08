/*  1:   */package org.newdawn.slick.svg.inkscape;
/*  2:   */
/*  3:   */import org.newdawn.slick.geom.Rectangle;
/*  4:   */import org.newdawn.slick.geom.Shape;
/*  5:   */import org.newdawn.slick.geom.Transform;
/*  6:   */import org.newdawn.slick.svg.Diagram;
/*  7:   */import org.newdawn.slick.svg.Figure;
/*  8:   */import org.newdawn.slick.svg.Loader;
/*  9:   */import org.newdawn.slick.svg.NonGeometricData;
/* 10:   */import org.newdawn.slick.svg.ParsingException;
/* 11:   */import org.w3c.dom.Element;
/* 12:   */
/* 18:   */public class RectProcessor
/* 19:   */  implements ElementProcessor
/* 20:   */{
/* 21:   */  public void process(Loader loader, Element element, Diagram diagram, Transform t)
/* 22:   */    throws ParsingException
/* 23:   */  {
/* 24:24 */    Transform transform = Util.getTransform(element);
/* 25:25 */    transform = new Transform(t, transform);
/* 26:   */    
/* 27:27 */    float width = Float.parseFloat(element.getAttribute("width"));
/* 28:28 */    float height = Float.parseFloat(element.getAttribute("height"));
/* 29:29 */    float x = Float.parseFloat(element.getAttribute("x"));
/* 30:30 */    float y = Float.parseFloat(element.getAttribute("y"));
/* 31:   */    
/* 32:32 */    Rectangle rect = new Rectangle(x, y, width + 1.0F, height + 1.0F);
/* 33:33 */    Shape shape = rect.transform(transform);
/* 34:   */    
/* 35:35 */    NonGeometricData data = Util.getNonGeometricData(element);
/* 36:36 */    data.addAttribute("width", "" + width);
/* 37:37 */    data.addAttribute("height", "" + height);
/* 38:38 */    data.addAttribute("x", "" + x);
/* 39:39 */    data.addAttribute("y", "" + y);
/* 40:   */    
/* 41:41 */    diagram.addFigure(new Figure(3, shape, data, transform));
/* 42:   */  }
/* 43:   */  
/* 46:   */  public boolean handles(Element element)
/* 47:   */  {
/* 48:48 */    if (element.getNodeName().equals("rect")) {
/* 49:49 */      return true;
/* 50:   */    }
/* 51:   */    
/* 52:52 */    return false;
/* 53:   */  }
/* 54:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.svg.inkscape.RectProcessor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */