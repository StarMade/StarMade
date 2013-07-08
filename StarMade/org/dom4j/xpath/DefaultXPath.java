package org.dom4j.xpath;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.dom4j.InvalidXPathException;
import org.dom4j.Node;
import org.dom4j.NodeFilter;
import org.dom4j.XPathException;
import org.jaxen.FunctionContext;
import org.jaxen.JaxenException;
import org.jaxen.NamespaceContext;
import org.jaxen.SimpleNamespaceContext;
import org.jaxen.VariableContext;
import org.jaxen.dom4j.Dom4jXPath;

public class DefaultXPath
  implements org.dom4j.XPath, NodeFilter, Serializable
{
  private String text;
  private org.jaxen.XPath xpath;
  private NamespaceContext namespaceContext;
  
  public DefaultXPath(String text)
    throws InvalidXPathException
  {
    this.text = text;
    this.xpath = parse(text);
  }
  
  public String toString()
  {
    return "[XPath: " + this.xpath + "]";
  }
  
  public String getText()
  {
    return this.text;
  }
  
  public FunctionContext getFunctionContext()
  {
    return this.xpath.getFunctionContext();
  }
  
  public void setFunctionContext(FunctionContext functionContext)
  {
    this.xpath.setFunctionContext(functionContext);
  }
  
  public NamespaceContext getNamespaceContext()
  {
    return this.namespaceContext;
  }
  
  public void setNamespaceURIs(Map map)
  {
    setNamespaceContext(new SimpleNamespaceContext(map));
  }
  
  public void setNamespaceContext(NamespaceContext namespaceContext)
  {
    this.namespaceContext = namespaceContext;
    this.xpath.setNamespaceContext(namespaceContext);
  }
  
  public VariableContext getVariableContext()
  {
    return this.xpath.getVariableContext();
  }
  
  public void setVariableContext(VariableContext variableContext)
  {
    this.xpath.setVariableContext(variableContext);
  }
  
  public Object evaluate(Object context)
  {
    try
    {
      setNSContext(context);
      List answer = this.xpath.selectNodes(context);
      if ((answer != null) && (answer.size() == 1)) {
        return answer.get(0);
      }
      return answer;
    }
    catch (JaxenException answer)
    {
      handleJaxenException(answer);
    }
    return null;
  }
  
  public Object selectObject(Object context)
  {
    return evaluate(context);
  }
  
  public List selectNodes(Object context)
  {
    try
    {
      setNSContext(context);
      return this.xpath.selectNodes(context);
    }
    catch (JaxenException local_e)
    {
      handleJaxenException(local_e);
    }
    return Collections.EMPTY_LIST;
  }
  
  public List selectNodes(Object context, org.dom4j.XPath sortXPath)
  {
    List answer = selectNodes(context);
    sortXPath.sort(answer);
    return answer;
  }
  
  public List selectNodes(Object context, org.dom4j.XPath sortXPath, boolean distinct)
  {
    List answer = selectNodes(context);
    sortXPath.sort(answer, distinct);
    return answer;
  }
  
  public Node selectSingleNode(Object context)
  {
    try
    {
      setNSContext(context);
      Object answer = this.xpath.selectSingleNode(context);
      if ((answer instanceof Node)) {
        return (Node)answer;
      }
      if (answer == null) {
        return null;
      }
      throw new XPathException("The result of the XPath expression is not a Node. It was: " + answer + " of type: " + answer.getClass().getName());
    }
    catch (JaxenException answer)
    {
      handleJaxenException(answer);
    }
    return null;
  }
  
  public String valueOf(Object context)
  {
    try
    {
      setNSContext(context);
      return this.xpath.stringValueOf(context);
    }
    catch (JaxenException local_e)
    {
      handleJaxenException(local_e);
    }
    return "";
  }
  
  public Number numberValueOf(Object context)
  {
    try
    {
      setNSContext(context);
      return this.xpath.numberValueOf(context);
    }
    catch (JaxenException local_e)
    {
      handleJaxenException(local_e);
    }
    return null;
  }
  
  public boolean booleanValueOf(Object context)
  {
    try
    {
      setNSContext(context);
      return this.xpath.booleanValueOf(context);
    }
    catch (JaxenException local_e)
    {
      handleJaxenException(local_e);
    }
    return false;
  }
  
  public void sort(List list)
  {
    sort(list, false);
  }
  
  public void sort(List list, boolean distinct)
  {
    if ((list != null) && (!list.isEmpty()))
    {
      int size = list.size();
      HashMap sortValues = new HashMap(size);
      for (int local_i = 0; local_i < size; local_i++)
      {
        Object object = list.get(local_i);
        if ((object instanceof Node))
        {
          Node node = (Node)object;
          Object expression = getCompareValue(node);
          sortValues.put(node, expression);
        }
      }
      sort(list, sortValues);
      if (distinct) {
        removeDuplicates(list, sortValues);
      }
    }
  }
  
  public boolean matches(Node node)
  {
    try
    {
      setNSContext(node);
      List answer = this.xpath.selectNodes(node);
      if ((answer != null) && (answer.size() > 0))
      {
        Object item = answer.get(0);
        if ((item instanceof Boolean)) {
          return ((Boolean)item).booleanValue();
        }
        return answer.contains(node);
      }
      return false;
    }
    catch (JaxenException answer)
    {
      handleJaxenException(answer);
    }
    return false;
  }
  
  protected void sort(List list, Map sortValues)
  {
    Collections.sort(list, new Comparator()
    {
      private final Map val$sortValues;
      
      public int compare(Object local_o1, Object local_o2)
      {
        local_o1 = this.val$sortValues.get(local_o1);
        local_o2 = this.val$sortValues.get(local_o2);
        if (local_o1 == local_o2) {
          return 0;
        }
        if ((local_o1 instanceof Comparable))
        {
          Comparable local_c1 = (Comparable)local_o1;
          return local_c1.compareTo(local_o2);
        }
        if (local_o1 == null) {
          return 1;
        }
        if (local_o2 == null) {
          return -1;
        }
        return local_o1.equals(local_o2) ? 0 : -1;
      }
    });
  }
  
  protected void removeDuplicates(List list, Map sortValues)
  {
    HashSet distinctValues = new HashSet();
    Iterator iter = list.iterator();
    while (iter.hasNext())
    {
      Object node = iter.next();
      Object value = sortValues.get(node);
      if (distinctValues.contains(value)) {
        iter.remove();
      } else {
        distinctValues.add(value);
      }
    }
  }
  
  protected Object getCompareValue(Node node)
  {
    return valueOf(node);
  }
  
  protected static org.jaxen.XPath parse(String text)
  {
    try
    {
      return new Dom4jXPath(text);
    }
    catch (JaxenException local_e)
    {
      throw new InvalidXPathException(text, local_e.getMessage());
    }
    catch (Throwable local_e)
    {
      throw new InvalidXPathException(text, local_e);
    }
  }
  
  protected void setNSContext(Object context)
  {
    if (this.namespaceContext == null) {
      this.xpath.setNamespaceContext(DefaultNamespaceContext.create(context));
    }
  }
  
  protected void handleJaxenException(JaxenException exception)
    throws XPathException
  {
    throw new XPathException(this.text, exception);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.xpath.DefaultXPath
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */