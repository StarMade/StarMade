/*     */ package org.jaxen.expr;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.JaxenException;
/*     */ 
/*     */ class DefaultPathExpr extends DefaultExpr
/*     */   implements PathExpr
/*     */ {
/*     */   private static final long serialVersionUID = -6593934674727004281L;
/*     */   private Expr filterExpr;
/*     */   private LocationPath locationPath;
/*     */ 
/*     */   DefaultPathExpr(Expr filterExpr, LocationPath locationPath)
/*     */   {
/*  64 */     this.filterExpr = filterExpr;
/*  65 */     this.locationPath = locationPath;
/*     */   }
/*     */ 
/*     */   public Expr getFilterExpr() {
/*  69 */     return this.filterExpr;
/*     */   }
/*     */ 
/*     */   public void setFilterExpr(Expr filterExpr)
/*     */   {
/*  74 */     this.filterExpr = filterExpr;
/*     */   }
/*     */ 
/*     */   public LocationPath getLocationPath()
/*     */   {
/*  79 */     return this.locationPath;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/*  84 */     if (getLocationPath() != null) {
/*  85 */       return "[(DefaultPathExpr): " + getFilterExpr() + ", " + getLocationPath() + "]";
/*     */     }
/*     */ 
/*  88 */     return "[(DefaultPathExpr): " + getFilterExpr() + "]";
/*     */   }
/*     */ 
/*     */   public String getText()
/*     */   {
/*  93 */     StringBuffer buf = new StringBuffer();
/*     */ 
/*  95 */     if (getFilterExpr() != null) {
/*  96 */       buf.append(getFilterExpr().getText());
/*     */     }
/*     */ 
/*  99 */     if (getLocationPath() != null) {
/* 100 */       if (!getLocationPath().getSteps().isEmpty()) buf.append("/");
/* 101 */       buf.append(getLocationPath().getText());
/*     */     }
/*     */ 
/* 104 */     return buf.toString();
/*     */   }
/*     */ 
/*     */   public Expr simplify()
/*     */   {
/* 109 */     if (getFilterExpr() != null) {
/* 110 */       setFilterExpr(getFilterExpr().simplify());
/*     */     }
/*     */ 
/* 113 */     if (getLocationPath() != null) {
/* 114 */       getLocationPath().simplify();
/*     */     }
/*     */ 
/* 117 */     if ((getFilterExpr() == null) && (getLocationPath() == null)) {
/* 118 */       return null;
/*     */     }
/*     */ 
/* 122 */     if (getLocationPath() == null) {
/* 123 */       return getFilterExpr();
/*     */     }
/*     */ 
/* 126 */     if (getFilterExpr() == null) {
/* 127 */       return getLocationPath();
/*     */     }
/*     */ 
/* 130 */     return this;
/*     */   }
/*     */ 
/*     */   public Object evaluate(Context context) throws JaxenException {
/* 134 */     Object results = null;
/* 135 */     Context pathContext = null;
/* 136 */     if (getFilterExpr() != null) {
/* 137 */       results = getFilterExpr().evaluate(context);
/* 138 */       pathContext = new Context(context.getContextSupport());
/* 139 */       pathContext.setNodeSet(convertToList(results));
/*     */     }
/* 141 */     if (getLocationPath() != null) {
/* 142 */       return getLocationPath().evaluate(pathContext);
/*     */     }
/* 144 */     return results;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultPathExpr
 * JD-Core Version:    0.6.2
 */