package org.jaxen.jdom;

import org.jdom.Element;
import org.jdom.Namespace;

public class XPathNamespace
{
  private Element jdomElement;
  private Namespace jdomNamespace;
  
  public XPathNamespace(Namespace jdomNamespace)
  {
    this.jdomNamespace = jdomNamespace;
  }
  
  public XPathNamespace(Element jdomElement, Namespace jdomNamespace)
  {
    this.jdomElement = jdomElement;
    this.jdomNamespace = jdomNamespace;
  }
  
  public Element getJDOMElement()
  {
    return this.jdomElement;
  }
  
  public void setJDOMElement(Element jdomElement)
  {
    this.jdomElement = jdomElement;
  }
  
  public Namespace getJDOMNamespace()
  {
    return this.jdomNamespace;
  }
  
  public String toString()
  {
    return "[xmlns:" + this.jdomNamespace.getPrefix() + "=\"" + this.jdomNamespace.getURI() + "\", element=" + this.jdomElement.getName() + "]";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.jdom.XPathNamespace
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */