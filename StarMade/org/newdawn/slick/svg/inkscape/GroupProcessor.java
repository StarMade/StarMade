/*  1:   */package org.newdawn.slick.svg.inkscape;
/*  2:   */
/*  3:   */import org.newdawn.slick.geom.Transform;
/*  4:   */import org.newdawn.slick.svg.Diagram;
/*  5:   */import org.newdawn.slick.svg.Loader;
/*  6:   */import org.newdawn.slick.svg.ParsingException;
/*  7:   */import org.w3c.dom.Element;
/*  8:   */
/* 15:   */public class GroupProcessor
/* 16:   */  implements ElementProcessor
/* 17:   */{
/* 18:   */  public boolean handles(Element element)
/* 19:   */  {
/* 20:20 */    if (element.getNodeName().equals("g")) {
/* 21:21 */      return true;
/* 22:   */    }
/* 23:23 */    return false;
/* 24:   */  }
/* 25:   */  
/* 27:   */  public void process(Loader loader, Element element, Diagram diagram, Transform t)
/* 28:   */    throws ParsingException
/* 29:   */  {
/* 30:30 */    Transform transform = Util.getTransform(element);
/* 31:31 */    transform = new Transform(t, transform);
/* 32:   */    
/* 33:33 */    loader.loadChildren(element, transform);
/* 34:   */  }
/* 35:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.svg.inkscape.GroupProcessor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */