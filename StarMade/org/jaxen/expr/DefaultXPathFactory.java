/*     */ package org.jaxen.expr;
/*     */ 
/*     */ import org.jaxen.JaxenException;
/*     */ import org.jaxen.expr.iter.IterableAncestorAxis;
/*     */ import org.jaxen.expr.iter.IterableAncestorOrSelfAxis;
/*     */ import org.jaxen.expr.iter.IterableAttributeAxis;
/*     */ import org.jaxen.expr.iter.IterableAxis;
/*     */ import org.jaxen.expr.iter.IterableChildAxis;
/*     */ import org.jaxen.expr.iter.IterableDescendantAxis;
/*     */ import org.jaxen.expr.iter.IterableDescendantOrSelfAxis;
/*     */ import org.jaxen.expr.iter.IterableFollowingAxis;
/*     */ import org.jaxen.expr.iter.IterableFollowingSiblingAxis;
/*     */ import org.jaxen.expr.iter.IterableNamespaceAxis;
/*     */ import org.jaxen.expr.iter.IterableParentAxis;
/*     */ import org.jaxen.expr.iter.IterablePrecedingAxis;
/*     */ import org.jaxen.expr.iter.IterablePrecedingSiblingAxis;
/*     */ import org.jaxen.expr.iter.IterableSelfAxis;
/*     */ 
/*     */ public class DefaultXPathFactory
/*     */   implements XPathFactory
/*     */ {
/*     */   public XPathExpr createXPath(Expr rootExpr)
/*     */     throws JaxenException
/*     */   {
/*  78 */     return new DefaultXPathExpr(rootExpr);
/*     */   }
/*     */ 
/*     */   public PathExpr createPathExpr(FilterExpr filterExpr, LocationPath locationPath)
/*     */     throws JaxenException
/*     */   {
/*  84 */     return new DefaultPathExpr(filterExpr, locationPath);
/*     */   }
/*     */ 
/*     */   public LocationPath createRelativeLocationPath()
/*     */     throws JaxenException
/*     */   {
/*  90 */     return new DefaultRelativeLocationPath();
/*     */   }
/*     */ 
/*     */   public LocationPath createAbsoluteLocationPath() throws JaxenException
/*     */   {
/*  95 */     return new DefaultAbsoluteLocationPath();
/*     */   }
/*     */ 
/*     */   public BinaryExpr createOrExpr(Expr lhs, Expr rhs)
/*     */     throws JaxenException
/*     */   {
/* 101 */     return new DefaultOrExpr(lhs, rhs);
/*     */   }
/*     */ 
/*     */   public BinaryExpr createAndExpr(Expr lhs, Expr rhs)
/*     */     throws JaxenException
/*     */   {
/* 108 */     return new DefaultAndExpr(lhs, rhs);
/*     */   }
/*     */ 
/*     */   public BinaryExpr createEqualityExpr(Expr lhs, Expr rhs, int equalityOperator)
/*     */     throws JaxenException
/*     */   {
/* 116 */     switch (equalityOperator)
/*     */     {
/*     */     case 1:
/* 120 */       return new DefaultEqualsExpr(lhs, rhs);
/*     */     case 2:
/* 125 */       return new DefaultNotEqualsExpr(lhs, rhs);
/*     */     }
/*     */ 
/* 129 */     throw new JaxenException("Unhandled operator in createEqualityExpr(): " + equalityOperator);
/*     */   }
/*     */ 
/*     */   public BinaryExpr createRelationalExpr(Expr lhs, Expr rhs, int relationalOperator)
/*     */     throws JaxenException
/*     */   {
/* 136 */     switch (relationalOperator)
/*     */     {
/*     */     case 3:
/* 140 */       return new DefaultLessThanExpr(lhs, rhs);
/*     */     case 5:
/* 145 */       return new DefaultGreaterThanExpr(lhs, rhs);
/*     */     case 4:
/* 150 */       return new DefaultLessThanEqualExpr(lhs, rhs);
/*     */     case 6:
/* 155 */       return new DefaultGreaterThanEqualExpr(lhs, rhs);
/*     */     }
/*     */ 
/* 159 */     throw new JaxenException("Unhandled operator in createRelationalExpr(): " + relationalOperator);
/*     */   }
/*     */ 
/*     */   public BinaryExpr createAdditiveExpr(Expr lhs, Expr rhs, int additiveOperator)
/*     */     throws JaxenException
/*     */   {
/* 166 */     switch (additiveOperator)
/*     */     {
/*     */     case 7:
/* 170 */       return new DefaultPlusExpr(lhs, rhs);
/*     */     case 8:
/* 175 */       return new DefaultMinusExpr(lhs, rhs);
/*     */     }
/*     */ 
/* 179 */     throw new JaxenException("Unhandled operator in createAdditiveExpr(): " + additiveOperator);
/*     */   }
/*     */ 
/*     */   public BinaryExpr createMultiplicativeExpr(Expr lhs, Expr rhs, int multiplicativeOperator)
/*     */     throws JaxenException
/*     */   {
/* 186 */     switch (multiplicativeOperator)
/*     */     {
/*     */     case 9:
/* 190 */       return new DefaultMultiplyExpr(lhs, rhs);
/*     */     case 11:
/* 195 */       return new DefaultDivExpr(lhs, rhs);
/*     */     case 10:
/* 200 */       return new DefaultModExpr(lhs, rhs);
/*     */     }
/*     */ 
/* 204 */     throw new JaxenException("Unhandled operator in createMultiplicativeExpr(): " + multiplicativeOperator);
/*     */   }
/*     */ 
/*     */   public Expr createUnaryExpr(Expr expr, int unaryOperator)
/*     */     throws JaxenException
/*     */   {
/* 210 */     switch (unaryOperator)
/*     */     {
/*     */     case 12:
/* 214 */       return new DefaultUnaryExpr(expr);
/*     */     }
/*     */ 
/* 217 */     return expr;
/*     */   }
/*     */ 
/*     */   public UnionExpr createUnionExpr(Expr lhs, Expr rhs)
/*     */     throws JaxenException
/*     */   {
/* 223 */     return new DefaultUnionExpr(lhs, rhs);
/*     */   }
/*     */ 
/*     */   public FilterExpr createFilterExpr(Expr expr)
/*     */     throws JaxenException
/*     */   {
/* 229 */     return new DefaultFilterExpr(expr, createPredicateSet());
/*     */   }
/*     */ 
/*     */   public FunctionCallExpr createFunctionCallExpr(String prefix, String functionName)
/*     */     throws JaxenException
/*     */   {
/* 235 */     return new DefaultFunctionCallExpr(prefix, functionName);
/*     */   }
/*     */ 
/*     */   public NumberExpr createNumberExpr(int number)
/*     */     throws JaxenException
/*     */   {
/* 241 */     return new DefaultNumberExpr(new Double(number));
/*     */   }
/*     */ 
/*     */   public NumberExpr createNumberExpr(double number) throws JaxenException
/*     */   {
/* 246 */     return new DefaultNumberExpr(new Double(number));
/*     */   }
/*     */ 
/*     */   public LiteralExpr createLiteralExpr(String literal) throws JaxenException
/*     */   {
/* 251 */     return new DefaultLiteralExpr(literal);
/*     */   }
/*     */ 
/*     */   public VariableReferenceExpr createVariableReferenceExpr(String prefix, String variable)
/*     */     throws JaxenException
/*     */   {
/* 257 */     return new DefaultVariableReferenceExpr(prefix, variable);
/*     */   }
/*     */ 
/*     */   public Step createNameStep(int axis, String prefix, String localName)
/*     */     throws JaxenException
/*     */   {
/* 265 */     IterableAxis iter = getIterableAxis(axis);
/* 266 */     return new DefaultNameStep(iter, prefix, localName, createPredicateSet());
/*     */   }
/*     */ 
/*     */   public Step createTextNodeStep(int axis)
/*     */     throws JaxenException
/*     */   {
/* 274 */     IterableAxis iter = getIterableAxis(axis);
/* 275 */     return new DefaultTextNodeStep(iter, createPredicateSet());
/*     */   }
/*     */ 
/*     */   public Step createCommentNodeStep(int axis) throws JaxenException
/*     */   {
/* 280 */     IterableAxis iter = getIterableAxis(axis);
/* 281 */     return new DefaultCommentNodeStep(iter, createPredicateSet());
/*     */   }
/*     */ 
/*     */   public Step createAllNodeStep(int axis) throws JaxenException
/*     */   {
/* 286 */     IterableAxis iter = getIterableAxis(axis);
/* 287 */     return new DefaultAllNodeStep(iter, createPredicateSet());
/*     */   }
/*     */ 
/*     */   public Step createProcessingInstructionNodeStep(int axis, String piName)
/*     */     throws JaxenException
/*     */   {
/* 293 */     IterableAxis iter = getIterableAxis(axis);
/* 294 */     return new DefaultProcessingInstructionNodeStep(iter, piName, createPredicateSet());
/*     */   }
/*     */ 
/*     */   public Predicate createPredicate(Expr predicateExpr)
/*     */     throws JaxenException
/*     */   {
/* 301 */     return new DefaultPredicate(predicateExpr);
/*     */   }
/*     */ 
/*     */   protected IterableAxis getIterableAxis(int axis)
/*     */     throws JaxenException
/*     */   {
/* 307 */     switch (axis)
/*     */     {
/*     */     case 1:
/* 310 */       return new IterableChildAxis(axis);
/*     */     case 2:
/* 312 */       return new IterableDescendantAxis(axis);
/*     */     case 3:
/* 314 */       return new IterableParentAxis(axis);
/*     */     case 5:
/* 316 */       return new IterableFollowingSiblingAxis(axis);
/*     */     case 6:
/* 318 */       return new IterablePrecedingSiblingAxis(axis);
/*     */     case 7:
/* 320 */       return new IterableFollowingAxis(axis);
/*     */     case 8:
/* 322 */       return new IterablePrecedingAxis(axis);
/*     */     case 9:
/* 324 */       return new IterableAttributeAxis(axis);
/*     */     case 10:
/* 326 */       return new IterableNamespaceAxis(axis);
/*     */     case 11:
/* 328 */       return new IterableSelfAxis(axis);
/*     */     case 12:
/* 330 */       return new IterableDescendantOrSelfAxis(axis);
/*     */     case 13:
/* 332 */       return new IterableAncestorOrSelfAxis(axis);
/*     */     case 4:
/* 334 */       return new IterableAncestorAxis(axis);
/*     */     }
/* 336 */     throw new JaxenException("Unrecognized axis code: " + axis);
/*     */   }
/*     */ 
/*     */   public PredicateSet createPredicateSet()
/*     */     throws JaxenException
/*     */   {
/* 343 */     return new PredicateSet();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultXPathFactory
 * JD-Core Version:    0.6.2
 */