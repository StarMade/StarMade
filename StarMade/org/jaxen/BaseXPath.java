package org.jaxen;

import java.io.Serializable;
import java.util.List;
import org.jaxen.expr.Expr;
import org.jaxen.expr.XPathExpr;
import org.jaxen.function.BooleanFunction;
import org.jaxen.function.NumberFunction;
import org.jaxen.function.StringFunction;
import org.jaxen.saxpath.SAXPathException;
import org.jaxen.saxpath.XPathReader;
import org.jaxen.saxpath.helpers.XPathReaderFactory;
import org.jaxen.util.SingletonList;

public class BaseXPath
  implements XPath, Serializable
{
  private static final long serialVersionUID = -1993731281300293168L;
  private final String exprText;
  private final XPathExpr xpath;
  private ContextSupport support;
  private Navigator navigator;
  
  protected BaseXPath(String xpathExpr)
    throws JaxenException
  {
    try
    {
      XPathReader reader = XPathReaderFactory.createReader();
      JaxenHandler handler = new JaxenHandler();
      reader.setXPathHandler(handler);
      reader.parse(xpathExpr);
      this.xpath = handler.getXPathExpr();
    }
    catch (org.jaxen.saxpath.XPathSyntaxException reader)
    {
      throw new XPathSyntaxException(reader);
    }
    catch (SAXPathException reader)
    {
      throw new JaxenException(reader);
    }
    this.exprText = xpathExpr;
  }
  
  public BaseXPath(String xpathExpr, Navigator navigator)
    throws JaxenException
  {
    this(xpathExpr);
    this.navigator = navigator;
  }
  
  public Object evaluate(Object context)
    throws JaxenException
  {
    List answer = selectNodes(context);
    if ((answer != null) && (answer.size() == 1))
    {
      Object first = answer.get(0);
      if (((first instanceof String)) || ((first instanceof Number)) || ((first instanceof Boolean))) {
        return first;
      }
    }
    return answer;
  }
  
  public List selectNodes(Object node)
    throws JaxenException
  {
    Context context = getContext(node);
    return selectNodesForContext(context);
  }
  
  public Object selectSingleNode(Object node)
    throws JaxenException
  {
    List results = selectNodes(node);
    if (results.isEmpty()) {
      return null;
    }
    return results.get(0);
  }
  
  /**
   * @deprecated
   */
  public String valueOf(Object node)
    throws JaxenException
  {
    return stringValueOf(node);
  }
  
  public String stringValueOf(Object node)
    throws JaxenException
  {
    Context context = getContext(node);
    Object result = selectSingleNodeForContext(context);
    if (result == null) {
      return "";
    }
    return StringFunction.evaluate(result, context.getNavigator());
  }
  
  public boolean booleanValueOf(Object node)
    throws JaxenException
  {
    Context context = getContext(node);
    List result = selectNodesForContext(context);
    if (result == null) {
      return false;
    }
    return BooleanFunction.evaluate(result, context.getNavigator()).booleanValue();
  }
  
  public Number numberValueOf(Object node)
    throws JaxenException
  {
    Context context = getContext(node);
    Object result = selectSingleNodeForContext(context);
    return NumberFunction.evaluate(result, context.getNavigator());
  }
  
  public void addNamespace(String prefix, String uri)
    throws JaxenException
  {
    NamespaceContext nsContext = getNamespaceContext();
    if ((nsContext instanceof SimpleNamespaceContext))
    {
      ((SimpleNamespaceContext)nsContext).addNamespace(prefix, uri);
      return;
    }
    throw new JaxenException("Operation not permitted while using a non-simple namespace context.");
  }
  
  public void setNamespaceContext(NamespaceContext namespaceContext)
  {
    getContextSupport().setNamespaceContext(namespaceContext);
  }
  
  public void setFunctionContext(FunctionContext functionContext)
  {
    getContextSupport().setFunctionContext(functionContext);
  }
  
  public void setVariableContext(VariableContext variableContext)
  {
    getContextSupport().setVariableContext(variableContext);
  }
  
  public NamespaceContext getNamespaceContext()
  {
    return getContextSupport().getNamespaceContext();
  }
  
  public FunctionContext getFunctionContext()
  {
    return getContextSupport().getFunctionContext();
  }
  
  public VariableContext getVariableContext()
  {
    return getContextSupport().getVariableContext();
  }
  
  public Expr getRootExpr()
  {
    return this.xpath.getRootExpr();
  }
  
  public String toString()
  {
    return this.exprText;
  }
  
  public String debug()
  {
    return this.xpath.toString();
  }
  
  protected Context getContext(Object node)
  {
    if ((node instanceof Context)) {
      return (Context)node;
    }
    Context fullContext = new Context(getContextSupport());
    if ((node instanceof List))
    {
      fullContext.setNodeSet((List)node);
    }
    else
    {
      List list = new SingletonList(node);
      fullContext.setNodeSet(list);
    }
    return fullContext;
  }
  
  protected ContextSupport getContextSupport()
  {
    if (this.support == null) {
      this.support = new ContextSupport(createNamespaceContext(), createFunctionContext(), createVariableContext(), getNavigator());
    }
    return this.support;
  }
  
  public Navigator getNavigator()
  {
    return this.navigator;
  }
  
  protected FunctionContext createFunctionContext()
  {
    return XPathFunctionContext.getInstance();
  }
  
  protected NamespaceContext createNamespaceContext()
  {
    return new SimpleNamespaceContext();
  }
  
  protected VariableContext createVariableContext()
  {
    return new SimpleVariableContext();
  }
  
  protected List selectNodesForContext(Context context)
    throws JaxenException
  {
    List list = this.xpath.asList(context);
    return list;
  }
  
  protected Object selectSingleNodeForContext(Context context)
    throws JaxenException
  {
    List results = selectNodesForContext(context);
    if (results.isEmpty()) {
      return null;
    }
    return results.get(0);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.BaseXPath
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */