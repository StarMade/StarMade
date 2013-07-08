package org.dom4j.datatype;

import com.sun.msv.datatype.DatabindableDatatype;
import com.sun.msv.datatype.SerializationContext;
import com.sun.msv.datatype.xsd.XSDatatype;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.QName;
import org.dom4j.tree.DefaultElement;
import org.relaxng.datatype.DatatypeException;
import org.relaxng.datatype.ValidationContext;

public class DatatypeElement
  extends DefaultElement
  implements SerializationContext, ValidationContext
{
  private XSDatatype datatype;
  private Object data;
  
  public DatatypeElement(QName qname, XSDatatype datatype)
  {
    super(qname);
    this.datatype = datatype;
  }
  
  public DatatypeElement(QName qname, int attributeCount, XSDatatype type)
  {
    super(qname, attributeCount);
    this.datatype = type;
  }
  
  public String toString()
  {
    return getClass().getName() + hashCode() + " [Element: <" + getQualifiedName() + " attributes: " + attributeList() + " data: " + getData() + " />]";
  }
  
  public XSDatatype getXSDatatype()
  {
    return this.datatype;
  }
  
  public String getNamespacePrefix(String uri)
  {
    Namespace namespace = getNamespaceForURI(uri);
    return namespace != null ? namespace.getPrefix() : null;
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
    Namespace namespace = getNamespaceForPrefix(prefix);
    if (namespace != null) {
      return namespace.getURI();
    }
    return null;
  }
  
  public Object getData()
  {
    if (this.data == null)
    {
      String text = getTextTrim();
      if ((text != null) && (text.length() > 0)) {
        if ((this.datatype instanceof DatabindableDatatype))
        {
          DatabindableDatatype bind = this.datatype;
          this.data = bind.createJavaObject(text, this);
        }
        else
        {
          this.data = this.datatype.createValue(text, this);
        }
      }
    }
    return this.data;
  }
  
  public void setData(Object data)
  {
    String local_s = this.datatype.convertToLexicalValue(data, this);
    validate(local_s);
    this.data = data;
    setText(local_s);
  }
  
  public Element addText(String text)
  {
    validate(text);
    return super.addText(text);
  }
  
  public void setText(String text)
  {
    validate(text);
    super.setText(text);
  }
  
  protected void childAdded(Node node)
  {
    this.data = null;
    super.childAdded(node);
  }
  
  protected void childRemoved(Node node)
  {
    this.data = null;
    super.childRemoved(node);
  }
  
  protected void validate(String text)
    throws IllegalArgumentException
  {
    try
    {
      this.datatype.checkValid(text, this);
    }
    catch (DatatypeException local_e)
    {
      throw new IllegalArgumentException(local_e.getMessage());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.datatype.DatatypeElement
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */