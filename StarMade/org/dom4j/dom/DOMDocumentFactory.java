package org.dom4j.dom;

import java.util.Map;
import org.dom4j.Attribute;
import org.dom4j.CDATA;
import org.dom4j.Comment;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.Entity;
import org.dom4j.Namespace;
import org.dom4j.ProcessingInstruction;
import org.dom4j.QName;
import org.dom4j.Text;
import org.dom4j.util.SingletonStrategy;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;

public class DOMDocumentFactory
  extends DocumentFactory
  implements DOMImplementation
{
  private static SingletonStrategy singleton = null;
  
  public static DocumentFactory getInstance()
  {
    DOMDocumentFactory fact = (DOMDocumentFactory)singleton.instance();
    return fact;
  }
  
  public org.dom4j.Document createDocument()
  {
    DOMDocument answer = new DOMDocument();
    answer.setDocumentFactory(this);
    return answer;
  }
  
  public org.dom4j.DocumentType createDocType(String name, String publicId, String systemId)
  {
    return new DOMDocumentType(name, publicId, systemId);
  }
  
  public Element createElement(QName qname)
  {
    return new DOMElement(qname);
  }
  
  public Element createElement(QName qname, int attributeCount)
  {
    return new DOMElement(qname, attributeCount);
  }
  
  public Attribute createAttribute(Element owner, QName qname, String value)
  {
    return new DOMAttribute(qname, value);
  }
  
  public CDATA createCDATA(String text)
  {
    return new DOMCDATA(text);
  }
  
  public Comment createComment(String text)
  {
    return new DOMComment(text);
  }
  
  public Text createText(String text)
  {
    return new DOMText(text);
  }
  
  public Entity createEntity(String name)
  {
    return new DOMEntityReference(name);
  }
  
  public Entity createEntity(String name, String text)
  {
    return new DOMEntityReference(name, text);
  }
  
  public Namespace createNamespace(String prefix, String uri)
  {
    return new DOMNamespace(prefix, uri);
  }
  
  public ProcessingInstruction createProcessingInstruction(String target, String data)
  {
    return new DOMProcessingInstruction(target, data);
  }
  
  public ProcessingInstruction createProcessingInstruction(String target, Map data)
  {
    return new DOMProcessingInstruction(target, data);
  }
  
  public boolean hasFeature(String feat, String version)
  {
    if (("XML".equalsIgnoreCase(feat)) || ("Core".equalsIgnoreCase(feat))) {
      return (version == null) || (version.length() == 0) || ("1.0".equals(version)) || ("2.0".equals(version));
    }
    return false;
  }
  
  public org.w3c.dom.DocumentType createDocumentType(String qualifiedName, String publicId, String systemId)
    throws DOMException
  {
    return new DOMDocumentType(qualifiedName, publicId, systemId);
  }
  
  public org.w3c.dom.Document createDocument(String namespaceURI, String qualifiedName, org.w3c.dom.DocumentType docType)
    throws DOMException
  {
    DOMDocument document;
    DOMDocument document;
    if (docType != null)
    {
      DOMDocumentType documentType = asDocumentType(docType);
      document = new DOMDocument(documentType);
    }
    else
    {
      document = new DOMDocument();
    }
    document.addElement(createQName(qualifiedName, namespaceURI));
    return document;
  }
  
  protected DOMDocumentType asDocumentType(org.w3c.dom.DocumentType docType)
  {
    if ((docType instanceof DOMDocumentType)) {
      return (DOMDocumentType)docType;
    }
    return new DOMDocumentType(docType.getName(), docType.getPublicId(), docType.getSystemId());
  }
  
  static
  {
    try
    {
      String defaultSingletonClass = "org.dom4j.util.SimpleSingleton";
      Class clazz = null;
      try
      {
        String singletonClass = defaultSingletonClass;
        singletonClass = System.getProperty("org.dom4j.dom.DOMDocumentFactory.singleton.strategy", singletonClass);
        clazz = Class.forName(singletonClass);
      }
      catch (Exception singletonClass)
      {
        try
        {
          String singletonClass = defaultSingletonClass;
          clazz = Class.forName(singletonClass);
        }
        catch (Exception singletonClass) {}
      }
      singleton = (SingletonStrategy)clazz.newInstance();
      singleton.setSingletonClassName(DOMDocumentFactory.class.getName());
    }
    catch (Exception defaultSingletonClass) {}
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.dom.DOMDocumentFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */