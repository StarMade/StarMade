package org.jaxen.xom;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import nu.xom.Attribute;
import nu.xom.Builder;
import nu.xom.Comment;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Node;
import nu.xom.NodeFactory;
import nu.xom.ParentNode;
import nu.xom.ProcessingInstruction;
import nu.xom.Text;
import org.jaxen.BaseXPath;
import org.jaxen.DefaultNavigator;
import org.jaxen.FunctionCallException;
import org.jaxen.JaxenConstants;
import org.jaxen.UnsupportedAxisException;
import org.jaxen.XPath;
import org.jaxen.saxpath.SAXPathException;
import org.jaxen.util.SingleObjectIterator;

public class DocumentNavigator
  extends DefaultNavigator
{
  private static final long serialVersionUID = 3159311338575942877L;
  
  public boolean isAttribute(Object local_o)
  {
    return local_o instanceof Attribute;
  }
  
  public boolean isComment(Object local_o)
  {
    return local_o instanceof Comment;
  }
  
  public boolean isDocument(Object local_o)
  {
    return local_o instanceof Document;
  }
  
  public boolean isElement(Object local_o)
  {
    return local_o instanceof Element;
  }
  
  public boolean isNamespace(Object local_o)
  {
    return local_o instanceof XPathNamespace;
  }
  
  public boolean isProcessingInstruction(Object local_o)
  {
    return local_o instanceof ProcessingInstruction;
  }
  
  public boolean isText(Object local_o)
  {
    return local_o instanceof Text;
  }
  
  public String getAttributeName(Object local_o)
  {
    return isAttribute(local_o) ? ((Attribute)local_o).getLocalName() : null;
  }
  
  public String getAttributeNamespaceUri(Object local_o)
  {
    return isAttribute(local_o) ? ((Attribute)local_o).getNamespaceURI() : null;
  }
  
  public String getAttributeQName(Object local_o)
  {
    return isAttribute(local_o) ? ((Attribute)local_o).getQualifiedName() : null;
  }
  
  public String getAttributeStringValue(Object local_o)
  {
    return isAttribute(local_o) ? ((Attribute)local_o).getValue() : null;
  }
  
  public String getCommentStringValue(Object local_o)
  {
    return isComment(local_o) ? ((Comment)local_o).getValue() : null;
  }
  
  public String getElementName(Object local_o)
  {
    return isElement(local_o) ? ((Element)local_o).getLocalName() : null;
  }
  
  public String getElementNamespaceUri(Object local_o)
  {
    return isElement(local_o) ? ((Element)local_o).getNamespaceURI() : null;
  }
  
  public String getElementQName(Object local_o)
  {
    return isElement(local_o) ? ((Element)local_o).getQualifiedName() : null;
  }
  
  public String getElementStringValue(Object local_o)
  {
    return (local_o instanceof Node) ? ((Node)local_o).getValue() : null;
  }
  
  public String getNamespacePrefix(Object local_o)
  {
    if (isElement(local_o)) {
      return ((Element)local_o).getNamespacePrefix();
    }
    if (isAttribute(local_o)) {
      return ((Attribute)local_o).getNamespacePrefix();
    }
    if ((local_o instanceof XPathNamespace)) {
      return ((XPathNamespace)local_o).getNamespacePrefix();
    }
    return null;
  }
  
  public String getNamespaceStringValue(Object local_o)
  {
    if (isElement(local_o)) {
      return ((Element)local_o).getNamespaceURI();
    }
    if (isAttribute(local_o)) {
      return ((Attribute)local_o).getNamespaceURI();
    }
    if ((local_o instanceof XPathNamespace)) {
      return ((XPathNamespace)local_o).getNamespaceURI();
    }
    return null;
  }
  
  public String getTextStringValue(Object local_o)
  {
    return (local_o instanceof Text) ? ((Text)local_o).getValue() : null;
  }
  
  public Object getDocument(String local_s)
    throws FunctionCallException
  {
    try
    {
      return new Builder(new NodeFactory()).build(local_s);
    }
    catch (Exception local_pe)
    {
      throw new FunctionCallException(local_pe);
    }
  }
  
  public Object getDocumentNode(Object local_o)
  {
    ParentNode parent = null;
    if ((local_o instanceof ParentNode)) {
      parent = (ParentNode)local_o;
    } else if ((local_o instanceof Node)) {
      parent = ((Node)local_o).getParent();
    }
    return parent.getDocument();
  }
  
  public Iterator getAttributeAxisIterator(Object local_o)
  {
    if (isElement(local_o)) {
      new IndexIterator(local_o, 0, ((Element)local_o).getAttributeCount())
      {
        public Object get(Object local_o, int local_i)
        {
          return ((Element)local_o).getAttribute(local_i);
        }
      };
    }
    return JaxenConstants.EMPTY_ITERATOR;
  }
  
  public Iterator getChildAxisIterator(Object local_o)
  {
    if ((isElement(local_o)) || ((local_o instanceof Document))) {
      new IndexIterator(local_o, 0, ((ParentNode)local_o).getChildCount())
      {
        public Object get(Object local_o, int local_i)
        {
          return ((ParentNode)local_o).getChild(local_i);
        }
      };
    }
    return JaxenConstants.EMPTY_ITERATOR;
  }
  
  public Iterator getParentAxisIterator(Object local_o)
  {
    Object parent = null;
    if ((local_o instanceof Node)) {
      parent = ((Node)local_o).getParent();
    } else if (isNamespace(local_o)) {
      parent = ((XPathNamespace)local_o).getElement();
    }
    return parent != null ? new SingleObjectIterator(parent) : null;
  }
  
  public Object getParentNode(Object local_o)
  {
    return (local_o instanceof Node) ? ((Node)local_o).getParent() : null;
  }
  
  public Iterator getPrecedingAxisIterator(Object local_o)
    throws UnsupportedAxisException
  {
    return super.getPrecedingAxisIterator(local_o);
  }
  
  public Iterator getPrecedingSiblingAxisIterator(Object local_o)
    throws UnsupportedAxisException
  {
    return super.getPrecedingSiblingAxisIterator(local_o);
  }
  
  public String getProcessingInstructionData(Object local_o)
  {
    return (local_o instanceof ProcessingInstruction) ? ((ProcessingInstruction)local_o).getValue() : null;
  }
  
  public String getProcessingInstructionTarget(Object local_o)
  {
    return (local_o instanceof ProcessingInstruction) ? ((ProcessingInstruction)local_o).getTarget() : null;
  }
  
  public String translateNamespacePrefixToUri(String local_s, Object local_o)
  {
    Element element = null;
    if ((local_o instanceof Element)) {
      element = (Element)local_o;
    } else if (!(local_o instanceof ParentNode)) {
      if ((local_o instanceof Node)) {
        element = (Element)((Node)local_o).getParent();
      } else if ((local_o instanceof XPathNamespace)) {
        element = ((XPathNamespace)local_o).getElement();
      }
    }
    if (element != null) {
      return element.getNamespaceURI(local_s);
    }
    return null;
  }
  
  public XPath parseXPath(String local_s)
    throws SAXPathException
  {
    return new BaseXPath(local_s, this);
  }
  
  private boolean addNamespaceForElement(Element elt, String uri, String prefix, Map map)
  {
    if ((uri != null) && (uri.length() > 0) && (!map.containsKey(prefix)))
    {
      map.put(prefix, new XPathNamespace(elt, uri, prefix));
      return true;
    }
    return false;
  }
  
  public Iterator getNamespaceAxisIterator(Object local_o)
  {
    if (!isElement(local_o)) {
      return JaxenConstants.EMPTY_ITERATOR;
    }
    Map nsMap = new HashMap();
    Element elt = (Element)local_o;
    for (ParentNode parent = elt; (parent instanceof Element); parent = elt.getParent())
    {
      elt = (Element)parent;
      String uri = elt.getNamespaceURI();
      String prefix = elt.getNamespacePrefix();
      addNamespaceForElement(elt, uri, prefix, nsMap);
      int count = elt.getNamespaceDeclarationCount();
      for (int local_i = 0; local_i < count; local_i++)
      {
        prefix = elt.getNamespacePrefix(local_i);
        uri = elt.getNamespaceURI(prefix);
        addNamespaceForElement(elt, uri, prefix, nsMap);
      }
    }
    addNamespaceForElement(elt, "http://www.w3.org/XML/1998/namespace", "xml", nsMap);
    return nsMap.values().iterator();
  }
  
  private static class XPathNamespace
  {
    private Element element;
    private String uri;
    private String prefix;
    
    public XPathNamespace(Element elt, String uri, String prefix)
    {
      this.element = elt;
      this.uri = uri;
      this.prefix = prefix;
    }
    
    public Element getElement()
    {
      return this.element;
    }
    
    public String getNamespaceURI()
    {
      return this.uri;
    }
    
    public String getNamespacePrefix()
    {
      return this.prefix;
    }
    
    public String toString()
    {
      return "[xmlns:" + this.prefix + "=\"" + this.uri + "\", element=" + this.element.getLocalName() + "]";
    }
  }
  
  private static abstract class IndexIterator
    implements Iterator
  {
    private Object field_1926 = null;
    private int pos = 0;
    private int end = -1;
    
    public IndexIterator(Object local_o, int pos, int end)
    {
      this.field_1926 = local_o;
      this.pos = pos;
      this.end = end;
    }
    
    public boolean hasNext()
    {
      return this.pos < this.end;
    }
    
    public abstract Object get(Object paramObject, int paramInt);
    
    public Object next()
    {
      return get(this.field_1926, this.pos++);
    }
    
    public void remove()
    {
      throw new UnsupportedOperationException();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.xom.DocumentNavigator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */