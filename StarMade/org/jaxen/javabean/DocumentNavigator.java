package org.jaxen.javabean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
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
  private static final long serialVersionUID = -1768605107626726499L;
  private static final Class[] EMPTY_CLASS_ARRAY = new Class[0];
  private static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
  private static final DocumentNavigator instance = new DocumentNavigator();
  
  public static Navigator getInstance()
  {
    return instance;
  }
  
  public boolean isElement(Object obj)
  {
    return obj instanceof Element;
  }
  
  public boolean isComment(Object obj)
  {
    return false;
  }
  
  public boolean isText(Object obj)
  {
    return obj instanceof String;
  }
  
  public boolean isAttribute(Object obj)
  {
    return false;
  }
  
  public boolean isProcessingInstruction(Object obj)
  {
    return false;
  }
  
  public boolean isDocument(Object obj)
  {
    return false;
  }
  
  public boolean isNamespace(Object obj)
  {
    return false;
  }
  
  public String getElementName(Object obj)
  {
    return ((Element)obj).getName();
  }
  
  public String getElementNamespaceUri(Object obj)
  {
    return "";
  }
  
  public String getElementQName(Object obj)
  {
    return "";
  }
  
  public String getAttributeName(Object obj)
  {
    return "";
  }
  
  public String getAttributeNamespaceUri(Object obj)
  {
    return "";
  }
  
  public String getAttributeQName(Object obj)
  {
    return "";
  }
  
  public Iterator getChildAxisIterator(Object contextNode)
  {
    return JaxenConstants.EMPTY_ITERATOR;
  }
  
  public Iterator getChildAxisIterator(Object contextNode, String localName, String namespacePrefix, String namespaceURI)
  {
    Class cls = ((Element)contextNode).getObject().getClass();
    String methodName = javacase(localName);
    Method method = null;
    try
    {
      method = cls.getMethod("get" + methodName, EMPTY_CLASS_ARRAY);
    }
    catch (NoSuchMethodException local_e)
    {
      try
      {
        method = cls.getMethod("get" + methodName + "s", EMPTY_CLASS_ARRAY);
      }
      catch (NoSuchMethodException local_ee)
      {
        try
        {
          method = cls.getMethod(localName, EMPTY_CLASS_ARRAY);
        }
        catch (NoSuchMethodException eee)
        {
          method = null;
        }
      }
    }
    if (method == null) {
      return JaxenConstants.EMPTY_ITERATOR;
    }
    try
    {
      Object local_e = method.invoke(((Element)contextNode).getObject(), EMPTY_OBJECT_ARRAY);
      if (local_e == null) {
        return JaxenConstants.EMPTY_ITERATOR;
      }
      if ((local_e instanceof Collection)) {
        return new ElementIterator((Element)contextNode, localName, ((Collection)local_e).iterator());
      }
      if (local_e.getClass().isArray()) {
        return JaxenConstants.EMPTY_ITERATOR;
      }
      return new SingleObjectIterator(new Element((Element)contextNode, localName, local_e));
    }
    catch (IllegalAccessException local_e) {}catch (InvocationTargetException local_e) {}
    return JaxenConstants.EMPTY_ITERATOR;
  }
  
  public Iterator getParentAxisIterator(Object contextNode)
  {
    if ((contextNode instanceof Element)) {
      return new SingleObjectIterator(((Element)contextNode).getParent());
    }
    return JaxenConstants.EMPTY_ITERATOR;
  }
  
  public Iterator getAttributeAxisIterator(Object contextNode)
  {
    return JaxenConstants.EMPTY_ITERATOR;
  }
  
  public Iterator getAttributeAxisIterator(Object contextNode, String localName, String namespacePrefix, String namespaceURI)
  {
    return JaxenConstants.EMPTY_ITERATOR;
  }
  
  public Iterator getNamespaceAxisIterator(Object contextNode)
  {
    return JaxenConstants.EMPTY_ITERATOR;
  }
  
  public Object getDocumentNode(Object contextNode)
  {
    return null;
  }
  
  public Object getParentNode(Object contextNode)
  {
    if ((contextNode instanceof Element)) {
      return ((Element)contextNode).getParent();
    }
    return JaxenConstants.EMPTY_ITERATOR;
  }
  
  public String getTextStringValue(Object obj)
  {
    if ((obj instanceof Element)) {
      return ((Element)obj).getObject().toString();
    }
    return obj.toString();
  }
  
  public String getElementStringValue(Object obj)
  {
    if ((obj instanceof Element)) {
      return ((Element)obj).getObject().toString();
    }
    return obj.toString();
  }
  
  public String getAttributeStringValue(Object obj)
  {
    return obj.toString();
  }
  
  public String getNamespaceStringValue(Object obj)
  {
    return obj.toString();
  }
  
  public String getNamespacePrefix(Object obj)
  {
    return null;
  }
  
  public String getCommentStringValue(Object obj)
  {
    return null;
  }
  
  public String translateNamespacePrefixToUri(String prefix, Object context)
  {
    return null;
  }
  
  public short getNodeType(Object node)
  {
    return 0;
  }
  
  public Object getDocument(String uri)
    throws FunctionCallException
  {
    return null;
  }
  
  public String getProcessingInstructionTarget(Object obj)
  {
    return null;
  }
  
  public String getProcessingInstructionData(Object obj)
  {
    return null;
  }
  
  public XPath parseXPath(String xpath)
    throws SAXPathException
  {
    return new JavaBeanXPath(xpath);
  }
  
  protected String javacase(String name)
  {
    if (name.length() == 0) {
      return name;
    }
    if (name.length() == 1) {
      return name.toUpperCase();
    }
    return name.substring(0, 1).toUpperCase() + name.substring(1);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.javabean.DocumentNavigator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */