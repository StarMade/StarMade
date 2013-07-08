package org.jaxen.expr;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jaxen.Context;
import org.jaxen.Function;
import org.jaxen.JaxenException;

/**
 * @deprecated
 */
public class DefaultFunctionCallExpr
  extends DefaultExpr
  implements FunctionCallExpr
{
  private static final long serialVersionUID = -4747789292572193708L;
  private String prefix;
  private String functionName;
  private List parameters;
  
  public DefaultFunctionCallExpr(String prefix, String functionName)
  {
    this.prefix = prefix;
    this.functionName = functionName;
    this.parameters = new ArrayList();
  }
  
  public void addParameter(Expr parameter)
  {
    this.parameters.add(parameter);
  }
  
  public List getParameters()
  {
    return this.parameters;
  }
  
  public String getPrefix()
  {
    return this.prefix;
  }
  
  public String getFunctionName()
  {
    return this.functionName;
  }
  
  public String getText()
  {
    StringBuffer buf = new StringBuffer();
    String prefix = getPrefix();
    if ((prefix != null) && (prefix.length() > 0))
    {
      buf.append(prefix);
      buf.append(":");
    }
    buf.append(getFunctionName());
    buf.append("(");
    Iterator paramIter = getParameters().iterator();
    while (paramIter.hasNext())
    {
      Expr eachParam = (Expr)paramIter.next();
      buf.append(eachParam.getText());
      if (paramIter.hasNext()) {
        buf.append(", ");
      }
    }
    buf.append(")");
    return buf.toString();
  }
  
  public Expr simplify()
  {
    List paramExprs = getParameters();
    int paramSize = paramExprs.size();
    List newParams = new ArrayList(paramSize);
    for (int local_i = 0; local_i < paramSize; local_i++)
    {
      Expr eachParam = (Expr)paramExprs.get(local_i);
      newParams.add(eachParam.simplify());
    }
    this.parameters = newParams;
    return this;
  }
  
  public String toString()
  {
    String prefix = getPrefix();
    if (prefix == null) {
      return "[(DefaultFunctionCallExpr): " + getFunctionName() + "(" + getParameters() + ") ]";
    }
    return "[(DefaultFunctionCallExpr): " + getPrefix() + ":" + getFunctionName() + "(" + getParameters() + ") ]";
  }
  
  public Object evaluate(Context context)
    throws JaxenException
  {
    String prefix = getPrefix();
    String namespaceURI = null;
    if ((prefix != null) && (!"".equals(prefix))) {
      namespaceURI = context.translateNamespacePrefixToUri(prefix);
    }
    Function func = context.getFunction(namespaceURI, prefix, getFunctionName());
    List paramValues = evaluateParams(context);
    return func.call(context, paramValues);
  }
  
  public List evaluateParams(Context context)
    throws JaxenException
  {
    List paramExprs = getParameters();
    int paramSize = paramExprs.size();
    List paramValues = new ArrayList(paramSize);
    for (int local_i = 0; local_i < paramSize; local_i++)
    {
      Expr eachParam = (Expr)paramExprs.get(local_i);
      Object eachValue = eachParam.evaluate(context);
      paramValues.add(eachValue);
    }
    return paramValues;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.DefaultFunctionCallExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */