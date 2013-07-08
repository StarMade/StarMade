/*   1:    */package org.jaxen.expr;
/*   2:    */
/*   3:    */import java.util.List;
/*   4:    */import org.jaxen.Context;
/*   5:    */import org.jaxen.JaxenException;
/*   6:    */
/*  54:    */class DefaultPathExpr
/*  55:    */  extends DefaultExpr
/*  56:    */  implements PathExpr
/*  57:    */{
/*  58:    */  private static final long serialVersionUID = -6593934674727004281L;
/*  59:    */  private Expr filterExpr;
/*  60:    */  private LocationPath locationPath;
/*  61:    */  
/*  62:    */  DefaultPathExpr(Expr filterExpr, LocationPath locationPath)
/*  63:    */  {
/*  64: 64 */    this.filterExpr = filterExpr;
/*  65: 65 */    this.locationPath = locationPath;
/*  66:    */  }
/*  67:    */  
/*  68:    */  public Expr getFilterExpr() {
/*  69: 69 */    return this.filterExpr;
/*  70:    */  }
/*  71:    */  
/*  72:    */  public void setFilterExpr(Expr filterExpr)
/*  73:    */  {
/*  74: 74 */    this.filterExpr = filterExpr;
/*  75:    */  }
/*  76:    */  
/*  77:    */  public LocationPath getLocationPath()
/*  78:    */  {
/*  79: 79 */    return this.locationPath;
/*  80:    */  }
/*  81:    */  
/*  82:    */  public String toString()
/*  83:    */  {
/*  84: 84 */    if (getLocationPath() != null) {
/*  85: 85 */      return "[(DefaultPathExpr): " + getFilterExpr() + ", " + getLocationPath() + "]";
/*  86:    */    }
/*  87:    */    
/*  88: 88 */    return "[(DefaultPathExpr): " + getFilterExpr() + "]";
/*  89:    */  }
/*  90:    */  
/*  91:    */  public String getText()
/*  92:    */  {
/*  93: 93 */    StringBuffer buf = new StringBuffer();
/*  94:    */    
/*  95: 95 */    if (getFilterExpr() != null) {
/*  96: 96 */      buf.append(getFilterExpr().getText());
/*  97:    */    }
/*  98:    */    
/*  99: 99 */    if (getLocationPath() != null) {
/* 100:100 */      if (!getLocationPath().getSteps().isEmpty()) buf.append("/");
/* 101:101 */      buf.append(getLocationPath().getText());
/* 102:    */    }
/* 103:    */    
/* 104:104 */    return buf.toString();
/* 105:    */  }
/* 106:    */  
/* 107:    */  public Expr simplify()
/* 108:    */  {
/* 109:109 */    if (getFilterExpr() != null) {
/* 110:110 */      setFilterExpr(getFilterExpr().simplify());
/* 111:    */    }
/* 112:    */    
/* 113:113 */    if (getLocationPath() != null) {
/* 114:114 */      getLocationPath().simplify();
/* 115:    */    }
/* 116:    */    
/* 117:117 */    if ((getFilterExpr() == null) && (getLocationPath() == null)) {
/* 118:118 */      return null;
/* 119:    */    }
/* 120:    */    
/* 122:122 */    if (getLocationPath() == null) {
/* 123:123 */      return getFilterExpr();
/* 124:    */    }
/* 125:    */    
/* 126:126 */    if (getFilterExpr() == null) {
/* 127:127 */      return getLocationPath();
/* 128:    */    }
/* 129:    */    
/* 130:130 */    return this;
/* 131:    */  }
/* 132:    */  
/* 133:    */  public Object evaluate(Context context) throws JaxenException {
/* 134:134 */    Object results = null;
/* 135:135 */    Context pathContext = null;
/* 136:136 */    if (getFilterExpr() != null) {
/* 137:137 */      results = getFilterExpr().evaluate(context);
/* 138:138 */      pathContext = new Context(context.getContextSupport());
/* 139:139 */      pathContext.setNodeSet(convertToList(results));
/* 140:    */    }
/* 141:141 */    if (getLocationPath() != null) {
/* 142:142 */      return getLocationPath().evaluate(pathContext);
/* 143:    */    }
/* 144:144 */    return results;
/* 145:    */  }
/* 146:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultPathExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */