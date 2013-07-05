package org.dom4j.jaxb;

import javax.xml.bind.Element;

public abstract interface JAXBObjectModifier
{
  public abstract Element modifyObject(Element paramElement)
    throws Exception;
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.jaxb.JAXBObjectModifier
 * JD-Core Version:    0.6.2
 */