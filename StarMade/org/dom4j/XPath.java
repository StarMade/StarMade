package org.dom4j;

import java.util.List;
import java.util.Map;
import org.jaxen.FunctionContext;
import org.jaxen.NamespaceContext;
import org.jaxen.VariableContext;

public abstract interface XPath
  extends NodeFilter
{
  public abstract String getText();
  
  public abstract boolean matches(Node paramNode);
  
  public abstract Object evaluate(Object paramObject);
  
  /**
   * @deprecated
   */
  public abstract Object selectObject(Object paramObject);
  
  public abstract List selectNodes(Object paramObject);
  
  public abstract List selectNodes(Object paramObject, XPath paramXPath);
  
  public abstract List selectNodes(Object paramObject, XPath paramXPath, boolean paramBoolean);
  
  public abstract Node selectSingleNode(Object paramObject);
  
  public abstract String valueOf(Object paramObject);
  
  public abstract Number numberValueOf(Object paramObject);
  
  public abstract boolean booleanValueOf(Object paramObject);
  
  public abstract void sort(List paramList);
  
  public abstract void sort(List paramList, boolean paramBoolean);
  
  public abstract FunctionContext getFunctionContext();
  
  public abstract void setFunctionContext(FunctionContext paramFunctionContext);
  
  public abstract NamespaceContext getNamespaceContext();
  
  public abstract void setNamespaceContext(NamespaceContext paramNamespaceContext);
  
  public abstract void setNamespaceURIs(Map paramMap);
  
  public abstract VariableContext getVariableContext();
  
  public abstract void setVariableContext(VariableContext paramVariableContext);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.XPath
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */