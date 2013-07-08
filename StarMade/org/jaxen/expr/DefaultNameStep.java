package org.jaxen.expr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.jaxen.Context;
import org.jaxen.ContextSupport;
import org.jaxen.JaxenException;
import org.jaxen.Navigator;
import org.jaxen.UnresolvableException;
import org.jaxen.expr.iter.IterableAxis;

/**
 * @deprecated
 */
public class DefaultNameStep
  extends DefaultStep
  implements NameStep
{
  private static final long serialVersionUID = 428414912247718390L;
  private String prefix;
  private String localName;
  private boolean matchesAnyName;
  private boolean hasPrefix;
  
  public DefaultNameStep(IterableAxis axis, String prefix, String localName, PredicateSet predicateSet)
  {
    super(axis, predicateSet);
    this.prefix = prefix;
    this.localName = localName;
    this.matchesAnyName = "*".equals(localName);
    this.hasPrefix = ((this.prefix != null) && (this.prefix.length() > 0));
  }
  
  public String getPrefix()
  {
    return this.prefix;
  }
  
  public String getLocalName()
  {
    return this.localName;
  }
  
  public boolean isMatchesAnyName()
  {
    return this.matchesAnyName;
  }
  
  public String getText()
  {
    StringBuffer buf = new StringBuffer(64);
    buf.append(getAxisName()).append("::");
    if ((getPrefix() != null) && (getPrefix().length() > 0)) {
      buf.append(getPrefix()).append(':');
    }
    return getLocalName() + super.getText();
  }
  
  public List evaluate(Context context)
    throws JaxenException
  {
    List contextNodeSet = context.getNodeSet();
    int contextSize = contextNodeSet.size();
    if (contextSize == 0) {
      return Collections.EMPTY_LIST;
    }
    ContextSupport support = context.getContextSupport();
    IterableAxis iterableAxis = getIterableAxis();
    boolean namedAccess = (!this.matchesAnyName) && (iterableAxis.supportsNamedAccess(support));
    if (contextSize == 1)
    {
      Object contextNode = contextNodeSet.get(0);
      if (namedAccess)
      {
        String uri = null;
        if (this.hasPrefix)
        {
          uri = support.translateNamespacePrefixToUri(this.prefix);
          if (uri == null) {
            throw new UnresolvableException("XPath expression uses unbound namespace prefix " + this.prefix);
          }
        }
        Iterator axisNodeIter = iterableAxis.namedAccessIterator(contextNode, support, this.localName, this.prefix, uri);
        if ((axisNodeIter == null) || (!axisNodeIter.hasNext())) {
          return Collections.EMPTY_LIST;
        }
        List newNodeSet = new ArrayList();
        while (axisNodeIter.hasNext()) {
          newNodeSet.add(axisNodeIter.next());
        }
        return getPredicateSet().evaluatePredicates(newNodeSet, support);
      }
      Iterator uri = iterableAxis.iterator(contextNode, support);
      if ((uri == null) || (!uri.hasNext())) {
        return Collections.EMPTY_LIST;
      }
      List axisNodeIter = new ArrayList(contextSize);
      while (uri.hasNext())
      {
        Object newNodeSet = uri.next();
        if (matches(newNodeSet, support)) {
          axisNodeIter.add(newNodeSet);
        }
      }
      return getPredicateSet().evaluatePredicates(axisNodeIter, support);
    }
    IdentitySet contextNode = new IdentitySet();
    List uri = new ArrayList(contextSize);
    List axisNodeIter = new ArrayList(contextSize);
    if (namedAccess)
    {
      String newNodeSet = null;
      if (this.hasPrefix)
      {
        newNodeSet = support.translateNamespacePrefixToUri(this.prefix);
        if (newNodeSet == null) {
          throw new UnresolvableException("XPath expression uses unbound namespace prefix " + this.prefix);
        }
      }
      for (int local_i = 0; local_i < contextSize; local_i++)
      {
        Object eachContextNode = contextNodeSet.get(local_i);
        Iterator axisNodeIter = iterableAxis.namedAccessIterator(eachContextNode, support, this.localName, this.prefix, newNodeSet);
        if ((axisNodeIter != null) && (axisNodeIter.hasNext()))
        {
          while (axisNodeIter.hasNext())
          {
            Object eachAxisNode = axisNodeIter.next();
            uri.add(eachAxisNode);
          }
          List eachAxisNode = getPredicateSet().evaluatePredicates(uri, support);
          Iterator predicateNodeIter = eachAxisNode.iterator();
          while (predicateNodeIter.hasNext())
          {
            Object eachPredicateNode = predicateNodeIter.next();
            if (!contextNode.contains(eachPredicateNode))
            {
              contextNode.add(eachPredicateNode);
              axisNodeIter.add(eachPredicateNode);
            }
          }
          uri.clear();
        }
      }
    }
    else
    {
      for (int newNodeSet = 0; newNodeSet < contextSize; newNodeSet++)
      {
        Object local_i = contextNodeSet.get(newNodeSet);
        Iterator eachContextNode = axisIterator(local_i, support);
        if ((eachContextNode != null) && (eachContextNode.hasNext()))
        {
          while (eachContextNode.hasNext())
          {
            Object axisNodeIter = eachContextNode.next();
            if (matches(axisNodeIter, support)) {
              uri.add(axisNodeIter);
            }
          }
          List axisNodeIter = getPredicateSet().evaluatePredicates(uri, support);
          Iterator eachAxisNode = axisNodeIter.iterator();
          while (eachAxisNode.hasNext())
          {
            Object predicateNodeIter = eachAxisNode.next();
            if (!contextNode.contains(predicateNodeIter))
            {
              contextNode.add(predicateNodeIter);
              axisNodeIter.add(predicateNodeIter);
            }
          }
          uri.clear();
        }
      }
    }
    return axisNodeIter;
  }
  
  public boolean matches(Object node, ContextSupport contextSupport)
    throws JaxenException
  {
    Navigator nav = contextSupport.getNavigator();
    String myUri = null;
    String nodeName = null;
    String nodeUri = null;
    if (nav.isElement(node))
    {
      nodeName = nav.getElementName(node);
      nodeUri = nav.getElementNamespaceUri(node);
    }
    else
    {
      if (nav.isText(node)) {
        return false;
      }
      if (nav.isAttribute(node))
      {
        if (getAxis() != 9) {
          return false;
        }
        nodeName = nav.getAttributeName(node);
        nodeUri = nav.getAttributeNamespaceUri(node);
      }
      else
      {
        if (nav.isDocument(node)) {
          return false;
        }
        if (nav.isNamespace(node))
        {
          if (getAxis() != 10) {
            return false;
          }
          nodeName = nav.getNamespacePrefix(node);
        }
        else
        {
          return false;
        }
      }
    }
    if (this.hasPrefix)
    {
      myUri = contextSupport.translateNamespacePrefixToUri(this.prefix);
      if (myUri == null) {
        throw new UnresolvableException("Cannot resolve namespace prefix '" + this.prefix + "'");
      }
    }
    else if (this.matchesAnyName)
    {
      return true;
    }
    if (hasNamespace(myUri) != hasNamespace(nodeUri)) {
      return false;
    }
    if ((this.matchesAnyName) || (nodeName.equals(getLocalName()))) {
      return matchesNamespaceURIs(myUri, nodeUri);
    }
    return false;
  }
  
  private boolean hasNamespace(String uri)
  {
    return (uri != null) && (uri.length() > 0);
  }
  
  protected boolean matchesNamespaceURIs(String uri1, String uri2)
  {
    if (uri1 == uri2) {
      return true;
    }
    if (uri1 == null) {
      return uri2.length() == 0;
    }
    if (uri2 == null) {
      return uri1.length() == 0;
    }
    return uri1.equals(uri2);
  }
  
  public String toString()
  {
    String prefix = getPrefix();
    String qName = getPrefix() + ":" + getLocalName();
    return "[(DefaultNameStep): " + qName + "]";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.DefaultNameStep
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */