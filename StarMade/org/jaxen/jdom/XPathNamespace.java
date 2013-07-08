/*   1:    */package org.jaxen.jdom;
/*   2:    */
/*   3:    */import org.jdom.Element;
/*   4:    */import org.jdom.Namespace;
/*   5:    */
/*  63:    */public class XPathNamespace
/*  64:    */{
/*  65:    */  private Element jdomElement;
/*  66:    */  private Namespace jdomNamespace;
/*  67:    */  
/*  68:    */  public XPathNamespace(Namespace jdomNamespace)
/*  69:    */  {
/*  70: 70 */    this.jdomNamespace = jdomNamespace;
/*  71:    */  }
/*  72:    */  
/*  76:    */  public XPathNamespace(Element jdomElement, Namespace jdomNamespace)
/*  77:    */  {
/*  78: 78 */    this.jdomElement = jdomElement;
/*  79: 79 */    this.jdomNamespace = jdomNamespace;
/*  80:    */  }
/*  81:    */  
/*  86:    */  public Element getJDOMElement()
/*  87:    */  {
/*  88: 88 */    return this.jdomElement;
/*  89:    */  }
/*  90:    */  
/*  93:    */  public void setJDOMElement(Element jdomElement)
/*  94:    */  {
/*  95: 95 */    this.jdomElement = jdomElement;
/*  96:    */  }
/*  97:    */  
/* 101:    */  public Namespace getJDOMNamespace()
/* 102:    */  {
/* 103:103 */    return this.jdomNamespace;
/* 104:    */  }
/* 105:    */  
/* 106:    */  public String toString()
/* 107:    */  {
/* 108:108 */    return "[xmlns:" + this.jdomNamespace.getPrefix() + "=\"" + this.jdomNamespace.getURI() + "\", element=" + this.jdomElement.getName() + "]";
/* 109:    */  }
/* 110:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.jdom.XPathNamespace
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */