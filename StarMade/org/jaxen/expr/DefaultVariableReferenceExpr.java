package org.jaxen.expr;

import org.jaxen.Context;
import org.jaxen.UnresolvableException;

class DefaultVariableReferenceExpr
  extends DefaultExpr
  implements VariableReferenceExpr
{
  private static final long serialVersionUID = 8832095437149358674L;
  private String prefix;
  private String localName;
  
  DefaultVariableReferenceExpr(String prefix, String variableName)
  {
    this.prefix = prefix;
    this.localName = variableName;
  }
  
  public String getPrefix()
  {
    return this.prefix;
  }
  
  public String getVariableName()
  {
    return this.localName;
  }
  
  public String toString()
  {
    return "[(DefaultVariableReferenceExpr): " + getQName() + "]";
  }
  
  private String getQName()
  {
    if ("".equals(this.prefix)) {
      return this.localName;
    }
    return this.prefix + ":" + this.localName;
  }
  
  public String getText()
  {
    return "$" + getQName();
  }
  
  public Object evaluate(Context context)
    throws UnresolvableException
  {
    String prefix = getPrefix();
    String namespaceURI = null;
    if ((prefix != null) && (!"".equals(prefix))) {
      namespaceURI = context.translateNamespacePrefixToUri(prefix);
    }
    return context.getVariableValue(namespaceURI, prefix, this.localName);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.DefaultVariableReferenceExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */