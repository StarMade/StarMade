/*   1:    */package org.jaxen.expr;
/*   2:    */
/*   3:    */import java.util.List;
/*   4:    */import org.jaxen.Context;
/*   5:    */import org.jaxen.JaxenException;
/*   6:    */
/*  60:    *//**
/*  61:    */ * @deprecated
/*  62:    */ */
/*  63:    */public class DefaultXPathExpr
/*  64:    */  implements XPathExpr
/*  65:    */{
/*  66:    */  private static final long serialVersionUID = 3007613096320896040L;
/*  67:    */  private Expr rootExpr;
/*  68:    */  
/*  69:    */  public DefaultXPathExpr(Expr rootExpr)
/*  70:    */  {
/*  71: 71 */    this.rootExpr = rootExpr;
/*  72:    */  }
/*  73:    */  
/*  74:    */  public Expr getRootExpr()
/*  75:    */  {
/*  76: 76 */    return this.rootExpr;
/*  77:    */  }
/*  78:    */  
/*  79:    */  public void setRootExpr(Expr rootExpr)
/*  80:    */  {
/*  81: 81 */    this.rootExpr = rootExpr;
/*  82:    */  }
/*  83:    */  
/*  84:    */  public String toString()
/*  85:    */  {
/*  86: 86 */    return "[(DefaultXPath): " + getRootExpr() + "]";
/*  87:    */  }
/*  88:    */  
/*  89:    */  public String getText()
/*  90:    */  {
/*  91: 91 */    return getRootExpr().getText();
/*  92:    */  }
/*  93:    */  
/*  94:    */  public void simplify()
/*  95:    */  {
/*  96: 96 */    setRootExpr(getRootExpr().simplify());
/*  97:    */  }
/*  98:    */  
/*  99:    */  public List asList(Context context) throws JaxenException
/* 100:    */  {
/* 101:101 */    Expr expr = getRootExpr();
/* 102:102 */    Object value = expr.evaluate(context);
/* 103:103 */    List result = DefaultExpr.convertToList(value);
/* 104:104 */    return result;
/* 105:    */  }
/* 106:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultXPathExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */