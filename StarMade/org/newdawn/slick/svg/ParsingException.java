package org.newdawn.slick.svg;

import org.newdawn.slick.SlickException;
import org.w3c.dom.Element;

public class ParsingException
  extends SlickException
{
  public ParsingException(String nodeID, String message, Throwable cause)
  {
    super("(" + nodeID + ") " + message, cause);
  }
  
  public ParsingException(Element element, String message, Throwable cause)
  {
    super("(" + element.getAttribute("id") + ") " + message, cause);
  }
  
  public ParsingException(String nodeID, String message)
  {
    super("(" + nodeID + ") " + message);
  }
  
  public ParsingException(Element element, String message)
  {
    super("(" + element.getAttribute("id") + ") " + message);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.svg.ParsingException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */