package org.jaxen;

import java.io.Serializable;

public class ContextSupport
  implements Serializable
{
  private static final long serialVersionUID = 4494082174713652559L;
  private transient FunctionContext functionContext;
  private NamespaceContext namespaceContext;
  private VariableContext variableContext;
  private Navigator navigator;
  
  public ContextSupport() {}
  
  public ContextSupport(NamespaceContext namespaceContext, FunctionContext functionContext, VariableContext variableContext, Navigator navigator)
  {
    setNamespaceContext(namespaceContext);
    setFunctionContext(functionContext);
    setVariableContext(variableContext);
    this.navigator = navigator;
  }
  
  public void setNamespaceContext(NamespaceContext namespaceContext)
  {
    this.namespaceContext = namespaceContext;
  }
  
  public NamespaceContext getNamespaceContext()
  {
    return this.namespaceContext;
  }
  
  public void setFunctionContext(FunctionContext functionContext)
  {
    this.functionContext = functionContext;
  }
  
  public FunctionContext getFunctionContext()
  {
    return this.functionContext;
  }
  
  public void setVariableContext(VariableContext variableContext)
  {
    this.variableContext = variableContext;
  }
  
  public VariableContext getVariableContext()
  {
    return this.variableContext;
  }
  
  public Navigator getNavigator()
  {
    return this.navigator;
  }
  
  public String translateNamespacePrefixToUri(String prefix)
  {
    if ("xml".equals(prefix)) {
      return "http://www.w3.org/XML/1998/namespace";
    }
    NamespaceContext context = getNamespaceContext();
    if (context != null) {
      return context.translateNamespacePrefixToUri(prefix);
    }
    return null;
  }
  
  public Object getVariableValue(String namespaceURI, String prefix, String localName)
    throws UnresolvableException
  {
    VariableContext context = getVariableContext();
    if (context != null) {
      return context.getVariableValue(namespaceURI, prefix, localName);
    }
    throw new UnresolvableException("No variable context installed");
  }
  
  public Function getFunction(String namespaceURI, String prefix, String localName)
    throws UnresolvableException
  {
    FunctionContext context = getFunctionContext();
    if (context != null) {
      return context.getFunction(namespaceURI, prefix, localName);
    }
    throw new UnresolvableException("No function context installed");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.ContextSupport
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */