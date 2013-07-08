package org.dom4j.util;

import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.QName;

public class NonLazyDocumentFactory
  extends DocumentFactory
{
  protected static transient NonLazyDocumentFactory singleton = new NonLazyDocumentFactory();
  
  public static DocumentFactory getInstance()
  {
    return singleton;
  }
  
  public Element createElement(QName qname)
  {
    return new NonLazyElement(qname);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.util.NonLazyDocumentFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */