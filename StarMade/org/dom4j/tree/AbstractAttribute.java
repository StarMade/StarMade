package org.dom4j.tree;

import java.io.IOException;
import java.io.Writer;
import org.dom4j.Attribute;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.QName;
import org.dom4j.Visitor;

public abstract class AbstractAttribute
  extends AbstractNode
  implements Attribute
{
  public short getNodeType()
  {
    return 2;
  }
  
  public void setNamespace(Namespace namespace)
  {
    String msg = "This Attribute is read only and cannot be changed";
    throw new UnsupportedOperationException(msg);
  }
  
  public String getText()
  {
    return getValue();
  }
  
  public void setText(String text)
  {
    setValue(text);
  }
  
  public void setValue(String value)
  {
    String msg = "This Attribute is read only and cannot be changed";
    throw new UnsupportedOperationException(msg);
  }
  
  public Object getData()
  {
    return getValue();
  }
  
  public void setData(Object data)
  {
    setValue(data == null ? null : data.toString());
  }
  
  public String toString()
  {
    return super.toString() + " [Attribute: name " + getQualifiedName() + " value \"" + getValue() + "\"]";
  }
  
  public String asXML()
  {
    return getQualifiedName() + "=\"" + getValue() + "\"";
  }
  
  public void write(Writer writer)
    throws IOException
  {
    writer.write(getQualifiedName());
    writer.write("=\"");
    writer.write(getValue());
    writer.write("\"");
  }
  
  public void accept(Visitor visitor)
  {
    visitor.visit(this);
  }
  
  public Namespace getNamespace()
  {
    return getQName().getNamespace();
  }
  
  public String getName()
  {
    return getQName().getName();
  }
  
  public String getNamespacePrefix()
  {
    return getQName().getNamespacePrefix();
  }
  
  public String getNamespaceURI()
  {
    return getQName().getNamespaceURI();
  }
  
  public String getQualifiedName()
  {
    return getQName().getQualifiedName();
  }
  
  public String getPath(Element context)
  {
    StringBuffer result = new StringBuffer();
    Element parent = getParent();
    if ((parent != null) && (parent != context))
    {
      result.append(parent.getPath(context));
      result.append("/");
    }
    result.append("@");
    String uri = getNamespaceURI();
    String prefix = getNamespacePrefix();
    if ((uri == null) || (uri.length() == 0) || (prefix == null) || (prefix.length() == 0)) {
      result.append(getName());
    } else {
      result.append(getQualifiedName());
    }
    return result.toString();
  }
  
  public String getUniquePath(Element context)
  {
    StringBuffer result = new StringBuffer();
    Element parent = getParent();
    if ((parent != null) && (parent != context))
    {
      result.append(parent.getUniquePath(context));
      result.append("/");
    }
    result.append("@");
    String uri = getNamespaceURI();
    String prefix = getNamespacePrefix();
    if ((uri == null) || (uri.length() == 0) || (prefix == null) || (prefix.length() == 0)) {
      result.append(getName());
    } else {
      result.append(getQualifiedName());
    }
    return result.toString();
  }
  
  protected Node createXPathResult(Element parent)
  {
    return new DefaultAttribute(parent, getQName(), getValue());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.tree.AbstractAttribute
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */