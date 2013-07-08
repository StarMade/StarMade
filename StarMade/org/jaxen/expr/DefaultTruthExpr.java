/*   1:    */package org.jaxen.expr;
/*   2:    */
/*   3:    */import java.util.List;
/*   4:    */
/*  54:    */abstract class DefaultTruthExpr
/*  55:    */  extends DefaultBinaryExpr
/*  56:    */{
/*  57:    */  DefaultTruthExpr(Expr lhs, Expr rhs)
/*  58:    */  {
/*  59: 59 */    super(lhs, rhs);
/*  60:    */  }
/*  61:    */  
/*  63:    */  public String toString()
/*  64:    */  {
/*  65: 65 */    return "[(DefaultTruthExpr): " + getLHS() + ", " + getRHS() + "]";
/*  66:    */  }
/*  67:    */  
/*  69:    */  protected boolean bothAreSets(Object lhs, Object rhs)
/*  70:    */  {
/*  71: 71 */    return ((lhs instanceof List)) && ((rhs instanceof List));
/*  72:    */  }
/*  73:    */  
/*  77:    */  protected boolean eitherIsSet(Object lhs, Object rhs)
/*  78:    */  {
/*  79: 79 */    return ((lhs instanceof List)) || ((rhs instanceof List));
/*  80:    */  }
/*  81:    */  
/*  84:    */  protected boolean isSet(Object obj)
/*  85:    */  {
/*  86: 86 */    return obj instanceof List;
/*  87:    */  }
/*  88:    */  
/*  89:    */  protected boolean isBoolean(Object obj)
/*  90:    */  {
/*  91: 91 */    return obj instanceof Boolean;
/*  92:    */  }
/*  93:    */  
/*  94:    */  protected boolean setIsEmpty(List set)
/*  95:    */  {
/*  96: 96 */    return (set == null) || (set.size() == 0);
/*  97:    */  }
/*  98:    */  
/* 100:    */  protected boolean eitherIsBoolean(Object lhs, Object rhs)
/* 101:    */  {
/* 102:102 */    return ((lhs instanceof Boolean)) || ((rhs instanceof Boolean));
/* 103:    */  }
/* 104:    */  
/* 108:    */  protected boolean bothAreBoolean(Object lhs, Object rhs)
/* 109:    */  {
/* 110:110 */    return ((lhs instanceof Boolean)) && ((rhs instanceof Boolean));
/* 111:    */  }
/* 112:    */  
/* 116:    */  protected boolean eitherIsNumber(Object lhs, Object rhs)
/* 117:    */  {
/* 118:118 */    return ((lhs instanceof Number)) || ((rhs instanceof Number));
/* 119:    */  }
/* 120:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultTruthExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */