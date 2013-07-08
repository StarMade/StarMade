/*  1:   */package org.newdawn.slick.svg.inkscape;
/*  2:   */
/*  3:   */import org.newdawn.slick.geom.Shape;
/*  4:   */import org.newdawn.slick.geom.Transform;
/*  5:   */import org.newdawn.slick.svg.Diagram;
/*  6:   */import org.newdawn.slick.svg.Figure;
/*  7:   */import org.newdawn.slick.svg.Loader;
/*  8:   */import org.newdawn.slick.svg.NonGeometricData;
/*  9:   */import org.newdawn.slick.svg.ParsingException;
/* 10:   */import org.w3c.dom.Element;
/* 11:   */
/* 19:   */public class UseProcessor
/* 20:   */  implements ElementProcessor
/* 21:   */{
/* 22:   */  public boolean handles(Element element)
/* 23:   */  {
/* 24:24 */    return element.getNodeName().equals("use");
/* 25:   */  }
/* 26:   */  
/* 30:   */  public void process(Loader loader, Element element, Diagram diagram, Transform transform)
/* 31:   */    throws ParsingException
/* 32:   */  {
/* 33:33 */    String ref = element.getAttributeNS("http://www.w3.org/1999/xlink", "href");
/* 34:34 */    String href = Util.getAsReference(ref);
/* 35:   */    
/* 36:36 */    Figure referenced = diagram.getFigureByID(href);
/* 37:37 */    if (referenced == null) {
/* 38:38 */      throw new ParsingException(element, "Unable to locate referenced element: " + href);
/* 39:   */    }
/* 40:   */    
/* 41:41 */    Transform local = Util.getTransform(element);
/* 42:42 */    Transform trans = local.concatenate(referenced.getTransform());
/* 43:   */    
/* 44:44 */    NonGeometricData data = Util.getNonGeometricData(element);
/* 45:45 */    Shape shape = referenced.getShape().transform(trans);
/* 46:46 */    data.addAttribute("fill", referenced.getData().getAttribute("fill"));
/* 47:47 */    data.addAttribute("stroke", referenced.getData().getAttribute("stroke"));
/* 48:48 */    data.addAttribute("opacity", referenced.getData().getAttribute("opacity"));
/* 49:49 */    data.addAttribute("stroke-width", referenced.getData().getAttribute("stroke-width"));
/* 50:   */    
/* 51:51 */    Figure figure = new Figure(referenced.getType(), shape, data, trans);
/* 52:52 */    diagram.addFigure(figure);
/* 53:   */  }
/* 54:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.svg.inkscape.UseProcessor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */