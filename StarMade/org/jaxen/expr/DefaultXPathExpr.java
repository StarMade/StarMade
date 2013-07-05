/*     */ package org.jaxen.expr;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.JaxenException;
/*     */ 
/*     */ /** @deprecated */
/*     */ public class DefaultXPathExpr
/*     */   implements XPathExpr
/*     */ {
/*     */   private static final long serialVersionUID = 3007613096320896040L;
/*     */   private Expr rootExpr;
/*     */ 
/*     */   public DefaultXPathExpr(Expr rootExpr)
/*     */   {
/*  71 */     this.rootExpr = rootExpr;
/*     */   }
/*     */ 
/*     */   public Expr getRootExpr()
/*     */   {
/*  76 */     return this.rootExpr;
/*     */   }
/*     */ 
/*     */   public void setRootExpr(Expr rootExpr)
/*     */   {
/*  81 */     this.rootExpr = rootExpr;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/*  86 */     return "[(DefaultXPath): " + getRootExpr() + "]";
/*     */   }
/*     */ 
/*     */   public String getText()
/*     */   {
/*  91 */     return getRootExpr().getText();
/*     */   }
/*     */ 
/*     */   public void simplify()
/*     */   {
/*  96 */     setRootExpr(getRootExpr().simplify());
/*     */   }
/*     */ 
/*     */   public List asList(Context context) throws JaxenException
/*     */   {
/* 101 */     Expr expr = getRootExpr();
/* 102 */     Object value = expr.evaluate(context);
/* 103 */     List result = DefaultExpr.convertToList(value);
/* 104 */     return result;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultXPathExpr
 * JD-Core Version:    0.6.2
 */