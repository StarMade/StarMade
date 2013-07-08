package org.dom4j;

import java.io.StringReader;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import org.dom4j.io.SAXReader;
import org.dom4j.rule.Pattern;
import org.jaxen.VariableContext;
import org.xml.sax.InputSource;

public final class DocumentHelper
{
  private static DocumentFactory getDocumentFactory()
  {
    return DocumentFactory.getInstance();
  }
  
  public static Document createDocument()
  {
    return getDocumentFactory().createDocument();
  }
  
  public static Document createDocument(Element rootElement)
  {
    return getDocumentFactory().createDocument(rootElement);
  }
  
  public static Element createElement(QName qname)
  {
    return getDocumentFactory().createElement(qname);
  }
  
  public static Element createElement(String name)
  {
    return getDocumentFactory().createElement(name);
  }
  
  public static Attribute createAttribute(Element owner, QName qname, String value)
  {
    return getDocumentFactory().createAttribute(owner, qname, value);
  }
  
  public static Attribute createAttribute(Element owner, String name, String value)
  {
    return getDocumentFactory().createAttribute(owner, name, value);
  }
  
  public static CDATA createCDATA(String text)
  {
    return DocumentFactory.getInstance().createCDATA(text);
  }
  
  public static Comment createComment(String text)
  {
    return DocumentFactory.getInstance().createComment(text);
  }
  
  public static Text createText(String text)
  {
    return DocumentFactory.getInstance().createText(text);
  }
  
  public static Entity createEntity(String name, String text)
  {
    return DocumentFactory.getInstance().createEntity(name, text);
  }
  
  public static Namespace createNamespace(String prefix, String uri)
  {
    return DocumentFactory.getInstance().createNamespace(prefix, uri);
  }
  
  public static ProcessingInstruction createProcessingInstruction(String local_pi, String local_d)
  {
    return getDocumentFactory().createProcessingInstruction(local_pi, local_d);
  }
  
  public static ProcessingInstruction createProcessingInstruction(String local_pi, Map data)
  {
    return getDocumentFactory().createProcessingInstruction(local_pi, data);
  }
  
  public static QName createQName(String localName, Namespace namespace)
  {
    return getDocumentFactory().createQName(localName, namespace);
  }
  
  public static QName createQName(String localName)
  {
    return getDocumentFactory().createQName(localName);
  }
  
  public static XPath createXPath(String xpathExpression)
    throws InvalidXPathException
  {
    return getDocumentFactory().createXPath(xpathExpression);
  }
  
  public static XPath createXPath(String xpathExpression, VariableContext context)
    throws InvalidXPathException
  {
    return getDocumentFactory().createXPath(xpathExpression, context);
  }
  
  public static NodeFilter createXPathFilter(String xpathFilterExpression)
  {
    return getDocumentFactory().createXPathFilter(xpathFilterExpression);
  }
  
  public static Pattern createPattern(String xpathPattern)
  {
    return getDocumentFactory().createPattern(xpathPattern);
  }
  
  public static List selectNodes(String xpathFilterExpression, List nodes)
  {
    XPath xpath = createXPath(xpathFilterExpression);
    return xpath.selectNodes(nodes);
  }
  
  public static List selectNodes(String xpathFilterExpression, Node node)
  {
    XPath xpath = createXPath(xpathFilterExpression);
    return xpath.selectNodes(node);
  }
  
  public static void sort(List list, String xpathExpression)
  {
    XPath xpath = createXPath(xpathExpression);
    xpath.sort(list);
  }
  
  public static void sort(List list, String expression, boolean distinct)
  {
    XPath xpath = createXPath(expression);
    xpath.sort(list, distinct);
  }
  
  public static Document parseText(String text)
    throws DocumentException
  {
    Document result = null;
    SAXReader reader = new SAXReader();
    String encoding = getEncoding(text);
    InputSource source = new InputSource(new StringReader(text));
    source.setEncoding(encoding);
    result = reader.read(source);
    if (result.getXMLEncoding() == null) {
      result.setXMLEncoding(encoding);
    }
    return result;
  }
  
  private static String getEncoding(String text)
  {
    String result = null;
    String xml = text.trim();
    if (xml.startsWith("<?xml"))
    {
      int end = xml.indexOf("?>");
      String sub = xml.substring(0, end);
      StringTokenizer tokens = new StringTokenizer(sub, " =\"'");
      while (tokens.hasMoreTokens())
      {
        String token = tokens.nextToken();
        if ("encoding".equals(token))
        {
          if (!tokens.hasMoreTokens()) {
            break;
          }
          result = tokens.nextToken();
          break;
        }
      }
    }
    return result;
  }
  
  public static Element makeElement(Branch source, String path)
  {
    StringTokenizer tokens = new StringTokenizer(path, "/");
    Element parent;
    if ((source instanceof Document))
    {
      Document document = (Document)source;
      Element parent = document.getRootElement();
      String name = tokens.nextToken();
      if (parent == null) {
        parent = document.addElement(name);
      }
    }
    else
    {
      parent = (Element)source;
    }
    Element document = null;
    while (tokens.hasMoreTokens())
    {
      String name = tokens.nextToken();
      if (name.indexOf(':') > 0) {
        document = parent.element(parent.getQName(name));
      } else {
        document = parent.element(name);
      }
      if (document == null) {
        document = parent.addElement(name);
      }
      parent = document;
    }
    return document;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.DocumentHelper
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */