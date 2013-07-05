/*     */ package org.jaxen.expr;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.JaxenException;
/*     */ 
/*     */ /** @deprecated */
/*     */ public class DefaultFilterExpr extends DefaultExpr
/*     */   implements FilterExpr, Predicated
/*     */ {
/*     */   private static final long serialVersionUID = -549640659288005735L;
/*     */   private Expr expr;
/*     */   private PredicateSet predicates;
/*     */ 
/*     */   public DefaultFilterExpr(PredicateSet predicateSet)
/*     */   {
/*  72 */     this.predicates = predicateSet;
/*     */   }
/*     */ 
/*     */   public DefaultFilterExpr(Expr expr, PredicateSet predicateSet)
/*     */   {
/*  77 */     this.expr = expr;
/*  78 */     this.predicates = predicateSet;
/*     */   }
/*     */ 
/*     */   public void addPredicate(Predicate predicate)
/*     */   {
/*  83 */     this.predicates.addPredicate(predicate);
/*     */   }
/*     */ 
/*     */   public List getPredicates()
/*     */   {
/*  88 */     return this.predicates.getPredicates();
/*     */   }
/*     */ 
/*     */   public PredicateSet getPredicateSet()
/*     */   {
/*  93 */     return this.predicates;
/*     */   }
/*     */ 
/*     */   public Expr getExpr()
/*     */   {
/*  98 */     return this.expr;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 103 */     return "[(DefaultFilterExpr): expr: " + this.expr + " predicates: " + this.predicates + " ]";
/*     */   }
/*     */ 
/*     */   public String getText()
/*     */   {
/* 108 */     String text = "";
/* 109 */     if (this.expr != null)
/*     */     {
/* 111 */       text = this.expr.getText();
/*     */     }
/* 113 */     text = text + this.predicates.getText();
/* 114 */     return text;
/*     */   }
/*     */ 
/*     */   public Expr simplify()
/*     */   {
/* 119 */     this.predicates.simplify();
/*     */ 
/* 121 */     if (this.expr != null)
/*     */     {
/* 123 */       this.expr = this.expr.simplify();
/*     */     }
/*     */ 
/* 126 */     if (this.predicates.getPredicates().size() == 0)
/*     */     {
/* 128 */       return getExpr();
/*     */     }
/*     */ 
/* 131 */     return this;
/*     */   }
/*     */ 
/*     */   public boolean asBoolean(Context context)
/*     */     throws JaxenException
/*     */   {
/* 138 */     Object results = null;
/* 139 */     if (this.expr != null)
/*     */     {
/* 141 */       results = this.expr.evaluate(context);
/*     */     }
/*     */     else
/*     */     {
/* 145 */       List nodeSet = context.getNodeSet();
/* 146 */       ArrayList list = new ArrayList(nodeSet.size());
/* 147 */       list.addAll(nodeSet);
/* 148 */       results = list;
/*     */     }
/*     */ 
/* 151 */     if ((results instanceof Boolean))
/*     */     {
/* 153 */       Boolean b = (Boolean)results;
/* 154 */       return b.booleanValue();
/*     */     }
/* 156 */     if ((results instanceof List))
/*     */     {
/* 158 */       return getPredicateSet().evaluateAsBoolean((List)results, context.getContextSupport());
/*     */     }
/*     */ 
/* 163 */     return false;
/*     */   }
/*     */ 
/*     */   public Object evaluate(Context context) throws JaxenException
/*     */   {
/* 168 */     Object results = getExpr().evaluate(context);
/*     */ 
/* 170 */     if ((results instanceof List))
/*     */     {
/* 172 */       List newresults = getPredicateSet().evaluatePredicates((List)results, context.getContextSupport());
/*     */ 
/* 174 */       results = newresults;
/*     */     }
/*     */ 
/* 177 */     return results;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultFilterExpr
 * JD-Core Version:    0.6.2
 */