package org.jaxen;

import java.util.Iterator;
import org.jaxen.util.AncestorAxisIterator;
import org.jaxen.util.AncestorOrSelfAxisIterator;
import org.jaxen.util.DescendantAxisIterator;
import org.jaxen.util.DescendantOrSelfAxisIterator;
import org.jaxen.util.FollowingAxisIterator;
import org.jaxen.util.FollowingSiblingAxisIterator;
import org.jaxen.util.PrecedingAxisIterator;
import org.jaxen.util.PrecedingSiblingAxisIterator;
import org.jaxen.util.SelfAxisIterator;

public abstract class DefaultNavigator
  implements Navigator
{
  public Iterator getChildAxisIterator(Object contextNode)
    throws UnsupportedAxisException
  {
    throw new UnsupportedAxisException("child");
  }
  
  public Iterator getDescendantAxisIterator(Object contextNode)
    throws UnsupportedAxisException
  {
    return new DescendantAxisIterator(contextNode, this);
  }
  
  public Iterator getParentAxisIterator(Object contextNode)
    throws UnsupportedAxisException
  {
    throw new UnsupportedAxisException("parent");
  }
  
  public Iterator getAncestorAxisIterator(Object contextNode)
    throws UnsupportedAxisException
  {
    return new AncestorAxisIterator(contextNode, this);
  }
  
  public Iterator getFollowingSiblingAxisIterator(Object contextNode)
    throws UnsupportedAxisException
  {
    return new FollowingSiblingAxisIterator(contextNode, this);
  }
  
  public Iterator getPrecedingSiblingAxisIterator(Object contextNode)
    throws UnsupportedAxisException
  {
    return new PrecedingSiblingAxisIterator(contextNode, this);
  }
  
  public Iterator getFollowingAxisIterator(Object contextNode)
    throws UnsupportedAxisException
  {
    return new FollowingAxisIterator(contextNode, this);
  }
  
  public Iterator getPrecedingAxisIterator(Object contextNode)
    throws UnsupportedAxisException
  {
    return new PrecedingAxisIterator(contextNode, this);
  }
  
  public Iterator getAttributeAxisIterator(Object contextNode)
    throws UnsupportedAxisException
  {
    throw new UnsupportedAxisException("attribute");
  }
  
  public Iterator getNamespaceAxisIterator(Object contextNode)
    throws UnsupportedAxisException
  {
    throw new UnsupportedAxisException("namespace");
  }
  
  public Iterator getSelfAxisIterator(Object contextNode)
    throws UnsupportedAxisException
  {
    return new SelfAxisIterator(contextNode);
  }
  
  public Iterator getDescendantOrSelfAxisIterator(Object contextNode)
    throws UnsupportedAxisException
  {
    return new DescendantOrSelfAxisIterator(contextNode, this);
  }
  
  public Iterator getAncestorOrSelfAxisIterator(Object contextNode)
    throws UnsupportedAxisException
  {
    return new AncestorOrSelfAxisIterator(contextNode, this);
  }
  
  public Object getDocumentNode(Object contextNode)
  {
    return null;
  }
  
  public String translateNamespacePrefixToUri(String prefix, Object element)
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
  
  public short getNodeType(Object node)
  {
    if (isElement(node)) {
      return 1;
    }
    if (isAttribute(node)) {
      return 2;
    }
    if (isText(node)) {
      return 3;
    }
    if (isComment(node)) {
      return 8;
    }
    if (isDocument(node)) {
      return 9;
    }
    if (isProcessingInstruction(node)) {
      return 7;
    }
    if (isNamespace(node)) {
      return 13;
    }
    return 14;
  }
  
  public Object getParentNode(Object contextNode)
    throws UnsupportedAxisException
  {
    Iterator iter = getParentAxisIterator(contextNode);
    if ((iter != null) && (iter.hasNext())) {
      return iter.next();
    }
    return null;
  }
  
  public Object getDocument(String url)
    throws FunctionCallException
  {
    return null;
  }
  
  public Object getElementById(Object contextNode, String elementId)
  {
    return null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.DefaultNavigator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */