/*     */ package org.jaxen.expr;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ abstract class DefaultTruthExpr extends DefaultBinaryExpr
/*     */ {
/*     */   DefaultTruthExpr(Expr lhs, Expr rhs)
/*     */   {
/*  59 */     super(lhs, rhs);
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/*  65 */     return "[(DefaultTruthExpr): " + getLHS() + ", " + getRHS() + "]";
/*     */   }
/*     */ 
/*     */   protected boolean bothAreSets(Object lhs, Object rhs)
/*     */   {
/*  71 */     return ((lhs instanceof List)) && ((rhs instanceof List));
/*     */   }
/*     */ 
/*     */   protected boolean eitherIsSet(Object lhs, Object rhs)
/*     */   {
/*  79 */     return ((lhs instanceof List)) || ((rhs instanceof List));
/*     */   }
/*     */ 
/*     */   protected boolean isSet(Object obj)
/*     */   {
/*  86 */     return obj instanceof List;
/*     */   }
/*     */ 
/*     */   protected boolean isBoolean(Object obj)
/*     */   {
/*  91 */     return obj instanceof Boolean;
/*     */   }
/*     */ 
/*     */   protected boolean setIsEmpty(List set)
/*     */   {
/*  96 */     return (set == null) || (set.size() == 0);
/*     */   }
/*     */ 
/*     */   protected boolean eitherIsBoolean(Object lhs, Object rhs)
/*     */   {
/* 102 */     return ((lhs instanceof Boolean)) || ((rhs instanceof Boolean));
/*     */   }
/*     */ 
/*     */   protected boolean bothAreBoolean(Object lhs, Object rhs)
/*     */   {
/* 110 */     return ((lhs instanceof Boolean)) && ((rhs instanceof Boolean));
/*     */   }
/*     */ 
/*     */   protected boolean eitherIsNumber(Object lhs, Object rhs)
/*     */   {
/* 118 */     return ((lhs instanceof Number)) || ((rhs instanceof Number));
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultTruthExpr
 * JD-Core Version:    0.6.2
 */