package org.dom4j;

import org.dom4j.tree.AbstractNode;
import org.dom4j.tree.DefaultNamespace;
import org.dom4j.tree.NamespaceCache;

public class Namespace
  extends AbstractNode
{
  protected static final NamespaceCache CACHE = new NamespaceCache();
  public static final Namespace XML_NAMESPACE = CACHE.get("xml", "http://www.w3.org/XML/1998/namespace");
  public static final Namespace NO_NAMESPACE = CACHE.get("", "");
  private String prefix;
  private String uri;
  private int hashCode;
  
  public Namespace(String prefix, String uri)
  {
    this.prefix = (prefix != null ? prefix : "");
    this.uri = (uri != null ? uri : "");
  }
  
  public static Namespace get(String prefix, String uri)
  {
    return CACHE.get(prefix, uri);
  }
  
  public static Namespace get(String uri)
  {
    return CACHE.get(uri);
  }
  
  public short getNodeType()
  {
    return 13;
  }
  
  public int hashCode()
  {
    if (this.hashCode == 0) {
      this.hashCode = createHashCode();
    }
    return this.hashCode;
  }
  
  protected int createHashCode()
  {
    int result = this.uri.hashCode() ^ this.prefix.hashCode();
    if (result == 0) {
      result = 47806;
    }
    return result;
  }
  
  public boolean equals(Object object)
  {
    if (this == object) {
      return true;
    }
    if ((object instanceof Namespace))
    {
      Namespace that = (Namespace)object;
      if (hashCode() == that.hashCode()) {
        return (this.uri.equals(that.getURI())) && (this.prefix.equals(that.getPrefix()));
      }
    }
    return false;
  }
  
  public String getText()
  {
    return this.uri;
  }
  
  public String getStringValue()
  {
    return this.uri;
  }
  
  public String getPrefix()
  {
    return this.prefix;
  }
  
  public String getURI()
  {
    return this.uri;
  }
  
  public String getXPathNameStep()
  {
    if ((this.prefix != null) && (!"".equals(this.prefix))) {
      return "namespace::" + this.prefix;
    }
    return "namespace::*[name()='']";
  }
  
  public String getPath(Element context)
  {
    StringBuffer path = new StringBuffer(10);
    Element parent = getParent();
    if ((parent != null) && (parent != context))
    {
      path.append(parent.getPath(context));
      path.append('/');
    }
    path.append(getXPathNameStep());
    return path.toString();
  }
  
  public String getUniquePath(Element context)
  {
    StringBuffer path = new StringBuffer(10);
    Element parent = getParent();
    if ((parent != null) && (parent != context))
    {
      path.append(parent.getUniquePath(context));
      path.append('/');
    }
    path.append(getXPathNameStep());
    return path.toString();
  }
  
  public String toString()
  {
    return super.toString() + " [Namespace: prefix " + getPrefix() + " mapped to URI \"" + getURI() + "\"]";
  }
  
  public String asXML()
  {
    StringBuffer asxml = new StringBuffer(10);
    String pref = getPrefix();
    if ((pref != null) && (pref.length() > 0))
    {
      asxml.append("xmlns:");
      asxml.append(pref);
      asxml.append("=\"");
    }
    else
    {
      asxml.append("xmlns=\"");
    }
    asxml.append(getURI());
    asxml.append("\"");
    return asxml.toString();
  }
  
  public void accept(Visitor visitor)
  {
    visitor.visit(this);
  }
  
  protected Node createXPathResult(Element parent)
  {
    return new DefaultNamespace(parent, getPrefix(), getURI());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.Namespace
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */