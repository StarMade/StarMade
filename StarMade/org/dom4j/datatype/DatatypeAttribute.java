package org.dom4j.datatype;

import com.sun.msv.datatype.DatabindableDatatype;
import com.sun.msv.datatype.SerializationContext;
import com.sun.msv.datatype.xsd.XSDatatype;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.tree.AbstractAttribute;
import org.relaxng.datatype.DatatypeException;
import org.relaxng.datatype.ValidationContext;

public class DatatypeAttribute
  extends AbstractAttribute
  implements SerializationContext, ValidationContext
{
  private Element parent;
  private QName qname;
  private XSDatatype datatype;
  private Object data;
  private String text;
  
  public DatatypeAttribute(QName qname, XSDatatype datatype)
  {
    this.qname = qname;
    this.datatype = datatype;
  }
  
  public DatatypeAttribute(QName qname, XSDatatype datatype, String text)
  {
    this.qname = qname;
    this.datatype = datatype;
    this.text = text;
    this.data = convertToValue(text);
  }
  
  public String toString()
  {
    return getClass().getName() + hashCode() + " [Attribute: name " + getQualifiedName() + " value \"" + getValue() + "\" data: " + getData() + "]";
  }
  
  public XSDatatype getXSDatatype()
  {
    return this.datatype;
  }
  
  public String getNamespacePrefix(String uri)
  {
    Element parentElement = getParent();
    if (parentElement != null)
    {
      Namespace namespace = parentElement.getNamespaceForURI(uri);
      if (namespace != null) {
        return namespace.getPrefix();
      }
    }
    return null;
  }
  
  public String getBaseUri()
  {
    return null;
  }
  
  public boolean isNotation(String notationName)
  {
    return false;
  }
  
  public boolean isUnparsedEntity(String entityName)
  {
    return true;
  }
  
  public String resolveNamespacePrefix(String prefix)
  {
    if (prefix.equals(getNamespacePrefix())) {
      return getNamespaceURI();
    }
    Element parentElement = getParent();
    if (parentElement != null)
    {
      Namespace namespace = parentElement.getNamespaceForPrefix(prefix);
      if (namespace != null) {
        return namespace.getURI();
      }
    }
    return null;
  }
  
  public QName getQName()
  {
    return this.qname;
  }
  
  public String getValue()
  {
    return this.text;
  }
  
  public void setValue(String value)
  {
    validate(value);
    this.text = value;
    this.data = convertToValue(value);
  }
  
  public Object getData()
  {
    return this.data;
  }
  
  public void setData(Object data)
  {
    String local_s = this.datatype.convertToLexicalValue(data, this);
    validate(local_s);
    this.text = local_s;
    this.data = data;
  }
  
  public Element getParent()
  {
    return this.parent;
  }
  
  public void setParent(Element parent)
  {
    this.parent = parent;
  }
  
  public boolean supportsParent()
  {
    return true;
  }
  
  public boolean isReadOnly()
  {
    return false;
  }
  
  protected void validate(String txt)
    throws IllegalArgumentException
  {
    try
    {
      this.datatype.checkValid(txt, this);
    }
    catch (DatatypeException local_e)
    {
      throw new IllegalArgumentException(local_e.getMessage());
    }
  }
  
  protected Object convertToValue(String txt)
  {
    if ((this.datatype instanceof DatabindableDatatype))
    {
      DatabindableDatatype bindable = this.datatype;
      return bindable.createJavaObject(txt, this);
    }
    return this.datatype.createValue(txt, this);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.datatype.DatatypeAttribute
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */