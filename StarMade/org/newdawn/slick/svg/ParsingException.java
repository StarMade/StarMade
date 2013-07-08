/*  1:   */package org.newdawn.slick.svg;
/*  2:   */
/*  3:   */import org.newdawn.slick.SlickException;
/*  4:   */import org.w3c.dom.Element;
/*  5:   */
/* 16:   */public class ParsingException
/* 17:   */  extends SlickException
/* 18:   */{
/* 19:   */  public ParsingException(String nodeID, String message, Throwable cause)
/* 20:   */  {
/* 21:21 */    super("(" + nodeID + ") " + message, cause);
/* 22:   */  }
/* 23:   */  
/* 30:   */  public ParsingException(Element element, String message, Throwable cause)
/* 31:   */  {
/* 32:32 */    super("(" + element.getAttribute("id") + ") " + message, cause);
/* 33:   */  }
/* 34:   */  
/* 40:   */  public ParsingException(String nodeID, String message)
/* 41:   */  {
/* 42:42 */    super("(" + nodeID + ") " + message);
/* 43:   */  }
/* 44:   */  
/* 50:   */  public ParsingException(Element element, String message)
/* 51:   */  {
/* 52:52 */    super("(" + element.getAttribute("id") + ") " + message);
/* 53:   */  }
/* 54:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.svg.ParsingException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */