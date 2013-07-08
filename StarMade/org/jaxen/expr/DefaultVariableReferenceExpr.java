/*   1:    */package org.jaxen.expr;
/*   2:    */
/*   3:    */import org.jaxen.Context;
/*   4:    */import org.jaxen.UnresolvableException;
/*   5:    */
/*  55:    */class DefaultVariableReferenceExpr
/*  56:    */  extends DefaultExpr
/*  57:    */  implements VariableReferenceExpr
/*  58:    */{
/*  59:    */  private static final long serialVersionUID = 8832095437149358674L;
/*  60:    */  private String prefix;
/*  61:    */  private String localName;
/*  62:    */  
/*  63:    */  DefaultVariableReferenceExpr(String prefix, String variableName)
/*  64:    */  {
/*  65: 65 */    this.prefix = prefix;
/*  66: 66 */    this.localName = variableName;
/*  67:    */  }
/*  68:    */  
/*  69:    */  public String getPrefix()
/*  70:    */  {
/*  71: 71 */    return this.prefix;
/*  72:    */  }
/*  73:    */  
/*  74:    */  public String getVariableName()
/*  75:    */  {
/*  76: 76 */    return this.localName;
/*  77:    */  }
/*  78:    */  
/*  79:    */  public String toString()
/*  80:    */  {
/*  81: 81 */    return "[(DefaultVariableReferenceExpr): " + getQName() + "]";
/*  82:    */  }
/*  83:    */  
/*  84:    */  private String getQName() {
/*  85: 85 */    if ("".equals(this.prefix))
/*  86:    */    {
/*  87: 87 */      return this.localName;
/*  88:    */    }
/*  89: 89 */    return this.prefix + ":" + this.localName;
/*  90:    */  }
/*  91:    */  
/*  92:    */  public String getText()
/*  93:    */  {
/*  94: 94 */    return "$" + getQName();
/*  95:    */  }
/*  96:    */  
/*  97:    */  public Object evaluate(Context context) throws UnresolvableException
/*  98:    */  {
/*  99: 99 */    String prefix = getPrefix();
/* 100:100 */    String namespaceURI = null;
/* 101:    */    
/* 102:102 */    if ((prefix != null) && (!"".equals(prefix))) {
/* 103:103 */      namespaceURI = context.translateNamespacePrefixToUri(prefix);
/* 104:    */    }
/* 105:    */    
/* 106:106 */    return context.getVariableValue(namespaceURI, prefix, this.localName);
/* 107:    */  }
/* 108:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultVariableReferenceExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */