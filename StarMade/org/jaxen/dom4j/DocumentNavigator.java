package org.jaxen.dom4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Attribute;
import org.dom4j.Branch;
import org.dom4j.CDATA;
import org.dom4j.Comment;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.ProcessingInstruction;
import org.dom4j.QName;
import org.dom4j.Text;
import org.dom4j.io.SAXReader;
import org.jaxen.DefaultNavigator;
import org.jaxen.FunctionCallException;
import org.jaxen.JaxenConstants;
import org.jaxen.NamedAccessNavigator;
import org.jaxen.Navigator;
import org.jaxen.XPath;
import org.jaxen.saxpath.SAXPathException;
import org.jaxen.util.SingleObjectIterator;

public class DocumentNavigator
  extends DefaultNavigator
  implements NamedAccessNavigator
{
  private static final long serialVersionUID = 5582300797286535936L;
  private transient SAXReader reader;
  
  public static Navigator getInstance()
  {
    return Singleton.instance;
  }
  
  public boolean isElement(Object obj)
  {
    return obj instanceof Element;
  }
  
  public boolean isComment(Object obj)
  {
    return obj instanceof Comment;
  }
  
  public boolean isText(Object obj)
  {
    return ((obj instanceof Text)) || ((obj instanceof CDATA));
  }
  
  public boolean isAttribute(Object obj)
  {
    return obj instanceof Attribute;
  }
  
  public boolean isProcessingInstruction(Object obj)
  {
    return obj instanceof ProcessingInstruction;
  }
  
  public boolean isDocument(Object obj)
  {
    return obj instanceof Document;
  }
  
  public boolean isNamespace(Object obj)
  {
    return obj instanceof Namespace;
  }
  
  public String getElementName(Object obj)
  {
    Element elem = (Element)obj;
    return elem.getName();
  }
  
  public String getElementNamespaceUri(Object obj)
  {
    Element elem = (Element)obj;
    String uri = elem.getNamespaceURI();
    if (uri == null) {
      return "";
    }
    return uri;
  }
  
  public String getElementQName(Object obj)
  {
    Element elem = (Element)obj;
    return elem.getQualifiedName();
  }
  
  public String getAttributeName(Object obj)
  {
    Attribute attr = (Attribute)obj;
    return attr.getName();
  }
  
  public String getAttributeNamespaceUri(Object obj)
  {
    Attribute attr = (Attribute)obj;
    String uri = attr.getNamespaceURI();
    if (uri == null) {
      return "";
    }
    return uri;
  }
  
  public String getAttributeQName(Object obj)
  {
    Attribute attr = (Attribute)obj;
    return attr.getQualifiedName();
  }
  
  public Iterator getChildAxisIterator(Object contextNode)
  {
    Iterator result = null;
    if ((contextNode instanceof Branch))
    {
      Branch node = (Branch)contextNode;
      result = node.nodeIterator();
    }
    if (result != null) {
      return result;
    }
    return JaxenConstants.EMPTY_ITERATOR;
  }
  
  public Iterator getChildAxisIterator(Object contextNode, String localName, String namespacePrefix, String namespaceURI)
  {
    if ((contextNode instanceof Element))
    {
      Element node = (Element)contextNode;
      return node.elementIterator(QName.get(localName, namespacePrefix, namespaceURI));
    }
    if ((contextNode instanceof Document))
    {
      Document node = (Document)contextNode;
      Element local_el = node.getRootElement();
      if ((local_el == null) || (!local_el.getName().equals(localName))) {
        return JaxenConstants.EMPTY_ITERATOR;
      }
      if ((namespaceURI != null) && (!namespaceURI.equals(local_el.getNamespaceURI()))) {
        return JaxenConstants.EMPTY_ITERATOR;
      }
      return new SingleObjectIterator(local_el);
    }
    return JaxenConstants.EMPTY_ITERATOR;
  }
  
  public Iterator getParentAxisIterator(Object contextNode)
  {
    if ((contextNode instanceof Document)) {
      return JaxenConstants.EMPTY_ITERATOR;
    }
    Node node = (Node)contextNode;
    Object parent = node.getParent();
    if (parent == null) {
      parent = node.getDocument();
    }
    return new SingleObjectIterator(parent);
  }
  
  public Iterator getAttributeAxisIterator(Object contextNode)
  {
    if (!(contextNode instanceof Element)) {
      return JaxenConstants.EMPTY_ITERATOR;
    }
    Element elem = (Element)contextNode;
    return elem.attributeIterator();
  }
  
  public Iterator getAttributeAxisIterator(Object contextNode, String localName, String namespacePrefix, String namespaceURI)
  {
    if ((contextNode instanceof Element))
    {
      Element node = (Element)contextNode;
      Attribute attr = node.attribute(QName.get(localName, namespacePrefix, namespaceURI));
      if (attr == null) {
        return JaxenConstants.EMPTY_ITERATOR;
      }
      return new SingleObjectIterator(attr);
    }
    return JaxenConstants.EMPTY_ITERATOR;
  }
  
  public Iterator getNamespaceAxisIterator(Object contextNode)
  {
    if (!(contextNode instanceof Element)) {
      return JaxenConstants.EMPTY_ITERATOR;
    }
    Element element = (Element)contextNode;
    List nsList = new ArrayList();
    HashSet prefixes = new HashSet();
    for (Element context = element; context != null; context = context.getParent())
    {
      List declaredNS = new ArrayList(context.declaredNamespaces());
      declaredNS.add(context.getNamespace());
      Iterator iter = context.attributes().iterator();
      while (iter.hasNext())
      {
        Attribute attr = (Attribute)iter.next();
        declaredNS.add(attr.getNamespace());
      }
      Iterator iter = declaredNS.iterator();
      while (iter.hasNext())
      {
        Namespace attr = (Namespace)iter.next();
        if (attr != Namespace.NO_NAMESPACE)
        {
          String prefix = attr.getPrefix();
          if (!prefixes.contains(prefix))
          {
            prefixes.add(prefix);
            nsList.add(attr.asXPathResult(element));
          }
        }
      }
    }
    nsList.add(Namespace.XML_NAMESPACE.asXPathResult(element));
    return nsList.iterator();
  }
  
  public Object getDocumentNode(Object contextNode)
  {
    if ((contextNode instanceof Document)) {
      return contextNode;
    }
    if ((contextNode instanceof Node))
    {
      Node node = (Node)contextNode;
      return node.getDocument();
    }
    return null;
  }
  
  public XPath parseXPath(String xpath)
    throws SAXPathException
  {
    return new Dom4jXPath(xpath);
  }
  
  public Object getParentNode(Object contextNode)
  {
    if ((contextNode instanceof Node))
    {
      Node node = (Node)contextNode;
      Object answer = node.getParent();
      if (answer == null)
      {
        answer = node.getDocument();
        if (answer == contextNode) {
          return null;
        }
      }
      return answer;
    }
    return null;
  }
  
  public String getTextStringValue(Object obj)
  {
    return getNodeStringValue((Node)obj);
  }
  
  public String getElementStringValue(Object obj)
  {
    return getNodeStringValue((Node)obj);
  }
  
  public String getAttributeStringValue(Object obj)
  {
    return getNodeStringValue((Node)obj);
  }
  
  private String getNodeStringValue(Node node)
  {
    return node.getStringValue();
  }
  
  public String getNamespaceStringValue(Object obj)
  {
    Namespace local_ns = (Namespace)obj;
    return local_ns.getURI();
  }
  
  public String getNamespacePrefix(Object obj)
  {
    Namespace local_ns = (Namespace)obj;
    return local_ns.getPrefix();
  }
  
  public String getCommentStringValue(Object obj)
  {
    Comment cmt = (Comment)obj;
    return cmt.getText();
  }
  
  public String translateNamespacePrefixToUri(String prefix, Object context)
  {
    Element element = null;
    if ((context instanceof Element))
    {
      element = (Element)context;
    }
    else if ((context instanceof Node))
    {
      Node node = (Node)context;
      element = node.getParent();
    }
    if (element != null)
    {
      Namespace node = element.getNamespaceForPrefix(prefix);
      if (node != null) {
        return node.getURI();
      }
    }
    return null;
  }
  
  public short getNodeType(Object node)
  {
    if ((node instanceof Node)) {
      return ((Node)node).getNodeType();
    }
    return 0;
  }
  
  public Object getDocument(String uri)
    throws FunctionCallException
  {
    try
    {
      return getSAXReader().read(uri);
    }
    catch (DocumentException local_e)
    {
      throw new FunctionCallException("Failed to parse document for URI: " + uri, local_e);
    }
  }
  
  public String getProcessingInstructionTarget(Object obj)
  {
    ProcessingInstruction local_pi = (ProcessingInstruction)obj;
    return local_pi.getTarget();
  }
  
  public String getProcessingInstructionData(Object obj)
  {
    ProcessingInstruction local_pi = (ProcessingInstruction)obj;
    return local_pi.getText();
  }
  
  public SAXReader getSAXReader()
  {
    if (this.reader == null)
    {
      this.reader = new SAXReader();
      this.reader.setMergeAdjacentText(true);
    }
    return this.reader;
  }
  
  public void setSAXReader(SAXReader reader)
  {
    this.reader = reader;
  }
  
  private static class Singleton
  {
    private static DocumentNavigator instance = new DocumentNavigator();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.dom4j.DocumentNavigator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */