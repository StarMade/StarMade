/*   1:    */package org.jaxen.expr;
/*   2:    */
/*  19:    */abstract class DefaultBinaryExpr
/*  20:    */  extends DefaultExpr
/*  21:    */  implements BinaryExpr
/*  22:    */{
/*  23:    */  private Expr lhs;
/*  24:    */  
/*  40:    */  private Expr rhs;
/*  41:    */  
/*  57:    */  DefaultBinaryExpr(Expr lhs, Expr rhs)
/*  58:    */  {
/*  59: 59 */    this.lhs = lhs;
/*  60: 60 */    this.rhs = rhs;
/*  61:    */  }
/*  62:    */  
/*  63:    */  public Expr getLHS()
/*  64:    */  {
/*  65: 65 */    return this.lhs;
/*  66:    */  }
/*  67:    */  
/*  68:    */  public Expr getRHS()
/*  69:    */  {
/*  70: 70 */    return this.rhs;
/*  71:    */  }
/*  72:    */  
/*  73:    */  public void setLHS(Expr lhs)
/*  74:    */  {
/*  75: 75 */    this.lhs = lhs;
/*  76:    */  }
/*  77:    */  
/*  78:    */  public void setRHS(Expr rhs)
/*  79:    */  {
/*  80: 80 */    this.rhs = rhs;
/*  81:    */  }
/*  82:    */  
/*  83:    */  public abstract String getOperator();
/*  84:    */  
/*  85:    */  public String getText()
/*  86:    */  {
/*  87: 87 */    return "(" + getLHS().getText() + " " + getOperator() + " " + getRHS().getText() + ")";
/*  88:    */  }
/*  89:    */  
/*  90:    */  public String toString()
/*  91:    */  {
/*  92: 92 */    return "[" + getClass().getName() + ": " + getLHS() + ", " + getRHS() + "]";
/*  93:    */  }
/*  94:    */  
/*  95:    */  public Expr simplify()
/*  96:    */  {
/*  97: 97 */    setLHS(getLHS().simplify());
/*  98: 98 */    setRHS(getRHS().simplify());
/*  99:    */    
/* 100:100 */    return this;
/* 101:    */  }
/* 102:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultBinaryExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */