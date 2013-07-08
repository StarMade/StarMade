package org.dom4j.util;

import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.QName;

public class IndexedDocumentFactory
  extends DocumentFactory
{
  protected static transient IndexedDocumentFactory singleton = new IndexedDocumentFactory();
  
  public static DocumentFactory getInstance()
  {
    return singleton;
  }
  
  public Element createElement(QName qname)
  {
    return new IndexedElement(qname);
  }
  
  public Element createElement(QName qname, int attributeCount)
  {
    return new IndexedElement(qname, attributeCount);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.util.IndexedDocumentFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */