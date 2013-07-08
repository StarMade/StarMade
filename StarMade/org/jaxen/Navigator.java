package org.jaxen;

import java.io.Serializable;
import java.util.Iterator;
import org.jaxen.saxpath.SAXPathException;

public abstract interface Navigator
  extends Serializable
{
  public abstract Iterator getChildAxisIterator(Object paramObject)
    throws UnsupportedAxisException;
  
  public abstract Iterator getDescendantAxisIterator(Object paramObject)
    throws UnsupportedAxisException;
  
  public abstract Iterator getParentAxisIterator(Object paramObject)
    throws UnsupportedAxisException;
  
  public abstract Iterator getAncestorAxisIterator(Object paramObject)
    throws UnsupportedAxisException;
  
  public abstract Iterator getFollowingSiblingAxisIterator(Object paramObject)
    throws UnsupportedAxisException;
  
  public abstract Iterator getPrecedingSiblingAxisIterator(Object paramObject)
    throws UnsupportedAxisException;
  
  public abstract Iterator getFollowingAxisIterator(Object paramObject)
    throws UnsupportedAxisException;
  
  public abstract Iterator getPrecedingAxisIterator(Object paramObject)
    throws UnsupportedAxisException;
  
  public abstract Iterator getAttributeAxisIterator(Object paramObject)
    throws UnsupportedAxisException;
  
  public abstract Iterator getNamespaceAxisIterator(Object paramObject)
    throws UnsupportedAxisException;
  
  public abstract Iterator getSelfAxisIterator(Object paramObject)
    throws UnsupportedAxisException;
  
  public abstract Iterator getDescendantOrSelfAxisIterator(Object paramObject)
    throws UnsupportedAxisException;
  
  public abstract Iterator getAncestorOrSelfAxisIterator(Object paramObject)
    throws UnsupportedAxisException;
  
  public abstract Object getDocument(String paramString)
    throws FunctionCallException;
  
  public abstract Object getDocumentNode(Object paramObject);
  
  public abstract Object getParentNode(Object paramObject)
    throws UnsupportedAxisException;
  
  public abstract String getElementNamespaceUri(Object paramObject);
  
  public abstract String getElementName(Object paramObject);
  
  public abstract String getElementQName(Object paramObject);
  
  public abstract String getAttributeNamespaceUri(Object paramObject);
  
  public abstract String getAttributeName(Object paramObject);
  
  public abstract String getAttributeQName(Object paramObject);
  
  public abstract String getProcessingInstructionTarget(Object paramObject);
  
  public abstract String getProcessingInstructionData(Object paramObject);
  
  public abstract boolean isDocument(Object paramObject);
  
  public abstract boolean isElement(Object paramObject);
  
  public abstract boolean isAttribute(Object paramObject);
  
  public abstract boolean isNamespace(Object paramObject);
  
  public abstract boolean isComment(Object paramObject);
  
  public abstract boolean isText(Object paramObject);
  
  public abstract boolean isProcessingInstruction(Object paramObject);
  
  public abstract String getCommentStringValue(Object paramObject);
  
  public abstract String getElementStringValue(Object paramObject);
  
  public abstract String getAttributeStringValue(Object paramObject);
  
  public abstract String getNamespaceStringValue(Object paramObject);
  
  public abstract String getTextStringValue(Object paramObject);
  
  public abstract String getNamespacePrefix(Object paramObject);
  
  public abstract String translateNamespacePrefixToUri(String paramString, Object paramObject);
  
  public abstract XPath parseXPath(String paramString)
    throws SAXPathException;
  
  public abstract Object getElementById(Object paramObject, String paramString);
  
  public abstract short getNodeType(Object paramObject);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.Navigator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */