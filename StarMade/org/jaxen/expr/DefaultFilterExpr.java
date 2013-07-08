/*   1:    */package org.jaxen.expr;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import java.util.List;
/*   5:    */import org.jaxen.Context;
/*   6:    */import org.jaxen.JaxenException;
/*   7:    */
/*  59:    *//**
/*  60:    */ * @deprecated
/*  61:    */ */
/*  62:    */public class DefaultFilterExpr
/*  63:    */  extends DefaultExpr
/*  64:    */  implements FilterExpr, Predicated
/*  65:    */{
/*  66:    */  private static final long serialVersionUID = -549640659288005735L;
/*  67:    */  private Expr expr;
/*  68:    */  private PredicateSet predicates;
/*  69:    */  
/*  70:    */  public DefaultFilterExpr(PredicateSet predicateSet)
/*  71:    */  {
/*  72: 72 */    this.predicates = predicateSet;
/*  73:    */  }
/*  74:    */  
/*  75:    */  public DefaultFilterExpr(Expr expr, PredicateSet predicateSet)
/*  76:    */  {
/*  77: 77 */    this.expr = expr;
/*  78: 78 */    this.predicates = predicateSet;
/*  79:    */  }
/*  80:    */  
/*  81:    */  public void addPredicate(Predicate predicate)
/*  82:    */  {
/*  83: 83 */    this.predicates.addPredicate(predicate);
/*  84:    */  }
/*  85:    */  
/*  86:    */  public List getPredicates()
/*  87:    */  {
/*  88: 88 */    return this.predicates.getPredicates();
/*  89:    */  }
/*  90:    */  
/*  91:    */  public PredicateSet getPredicateSet()
/*  92:    */  {
/*  93: 93 */    return this.predicates;
/*  94:    */  }
/*  95:    */  
/*  96:    */  public Expr getExpr()
/*  97:    */  {
/*  98: 98 */    return this.expr;
/*  99:    */  }
/* 100:    */  
/* 101:    */  public String toString()
/* 102:    */  {
/* 103:103 */    return "[(DefaultFilterExpr): expr: " + this.expr + " predicates: " + this.predicates + " ]";
/* 104:    */  }
/* 105:    */  
/* 106:    */  public String getText()
/* 107:    */  {
/* 108:108 */    String text = "";
/* 109:109 */    if (this.expr != null)
/* 110:    */    {
/* 111:111 */      text = this.expr.getText();
/* 112:    */    }
/* 113:113 */    text = text + this.predicates.getText();
/* 114:114 */    return text;
/* 115:    */  }
/* 116:    */  
/* 117:    */  public Expr simplify()
/* 118:    */  {
/* 119:119 */    this.predicates.simplify();
/* 120:    */    
/* 121:121 */    if (this.expr != null)
/* 122:    */    {
/* 123:123 */      this.expr = this.expr.simplify();
/* 124:    */    }
/* 125:    */    
/* 126:126 */    if (this.predicates.getPredicates().size() == 0)
/* 127:    */    {
/* 128:128 */      return getExpr();
/* 129:    */    }
/* 130:    */    
/* 131:131 */    return this;
/* 132:    */  }
/* 133:    */  
/* 135:    */  public boolean asBoolean(Context context)
/* 136:    */    throws JaxenException
/* 137:    */  {
/* 138:138 */    Object results = null;
/* 139:139 */    if (this.expr != null)
/* 140:    */    {
/* 141:141 */      results = this.expr.evaluate(context);
/* 142:    */    }
/* 143:    */    else
/* 144:    */    {
/* 145:145 */      List nodeSet = context.getNodeSet();
/* 146:146 */      ArrayList list = new ArrayList(nodeSet.size());
/* 147:147 */      list.addAll(nodeSet);
/* 148:148 */      results = list;
/* 149:    */    }
/* 150:    */    
/* 151:151 */    if ((results instanceof Boolean))
/* 152:    */    {
/* 153:153 */      Boolean b = (Boolean)results;
/* 154:154 */      return b.booleanValue();
/* 155:    */    }
/* 156:156 */    if ((results instanceof List))
/* 157:    */    {
/* 158:158 */      return getPredicateSet().evaluateAsBoolean((List)results, context.getContextSupport());
/* 159:    */    }
/* 160:    */    
/* 163:163 */    return false;
/* 164:    */  }
/* 165:    */  
/* 166:    */  public Object evaluate(Context context) throws JaxenException
/* 167:    */  {
/* 168:168 */    Object results = getExpr().evaluate(context);
/* 169:    */    
/* 170:170 */    if ((results instanceof List))
/* 171:    */    {
/* 172:172 */      List newresults = getPredicateSet().evaluatePredicates((List)results, context.getContextSupport());
/* 173:    */      
/* 174:174 */      results = newresults;
/* 175:    */    }
/* 176:    */    
/* 177:177 */    return results;
/* 178:    */  }
/* 179:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultFilterExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */