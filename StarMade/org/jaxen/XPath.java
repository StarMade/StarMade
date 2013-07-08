package org.jaxen;

import java.util.List;

public abstract interface XPath
{
  public abstract Object evaluate(Object paramObject)
    throws JaxenException;
  
  /**
   * @deprecated
   */
  public abstract String valueOf(Object paramObject)
    throws JaxenException;
  
  public abstract String stringValueOf(Object paramObject)
    throws JaxenException;
  
  public abstract boolean booleanValueOf(Object paramObject)
    throws JaxenException;
  
  public abstract Number numberValueOf(Object paramObject)
    throws JaxenException;
  
  public abstract List selectNodes(Object paramObject)
    throws JaxenException;
  
  public abstract Object selectSingleNode(Object paramObject)
    throws JaxenException;
  
  public abstract void addNamespace(String paramString1, String paramString2)
    throws JaxenException;
  
  public abstract void setNamespaceContext(NamespaceContext paramNamespaceContext);
  
  public abstract void setFunctionContext(FunctionContext paramFunctionContext);
  
  public abstract void setVariableContext(VariableContext paramVariableContext);
  
  public abstract NamespaceContext getNamespaceContext();
  
  public abstract FunctionContext getFunctionContext();
  
  public abstract VariableContext getVariableContext();
  
  public abstract Navigator getNavigator();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.XPath
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */