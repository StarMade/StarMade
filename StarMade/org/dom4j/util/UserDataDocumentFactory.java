package org.dom4j.util;

import org.dom4j.Attribute;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.QName;

public class UserDataDocumentFactory
  extends DocumentFactory
{
  protected static transient UserDataDocumentFactory singleton = new UserDataDocumentFactory();
  
  public static DocumentFactory getInstance()
  {
    return singleton;
  }
  
  public Element createElement(QName qname)
  {
    return new UserDataElement(qname);
  }
  
  public Attribute createAttribute(Element owner, QName qname, String value)
  {
    return new UserDataAttribute(qname, value);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.util.UserDataDocumentFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */