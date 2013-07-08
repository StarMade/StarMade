package org.newdawn.slick.svg;

import org.newdawn.slick.geom.Transform;
import org.w3c.dom.Element;

public abstract interface Loader
{
  public abstract void loadChildren(Element paramElement, Transform paramTransform)
    throws ParsingException;
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.svg.Loader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */