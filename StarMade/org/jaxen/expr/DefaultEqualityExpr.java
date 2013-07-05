/*     */ package org.jaxen.expr;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.JaxenException;
/*     */ import org.jaxen.Navigator;
/*     */ import org.jaxen.function.BooleanFunction;
/*     */ import org.jaxen.function.NumberFunction;
/*     */ import org.jaxen.function.StringFunction;
/*     */ 
/*     */ abstract class DefaultEqualityExpr extends DefaultTruthExpr
/*     */   implements EqualityExpr
/*     */ {
/*     */   DefaultEqualityExpr(Expr lhs, Expr rhs)
/*     */   {
/*  63 */     super(lhs, rhs);
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/*  68 */     return "[(DefaultEqualityExpr): " + getLHS() + ", " + getRHS() + "]";
/*     */   }
/*     */ 
/*     */   public Object evaluate(Context context) throws JaxenException
/*     */   {
/*  73 */     Object lhsValue = getLHS().evaluate(context);
/*  74 */     Object rhsValue = getRHS().evaluate(context);
/*     */ 
/*  76 */     if ((lhsValue == null) || (rhsValue == null)) {
/*  77 */       return Boolean.FALSE;
/*     */     }
/*     */ 
/*  80 */     Navigator nav = context.getNavigator();
/*     */ 
/*  82 */     if (bothAreSets(lhsValue, rhsValue)) {
/*  83 */       return evaluateSetSet((List)lhsValue, (List)rhsValue, nav);
/*     */     }
/*  85 */     if ((isSet(lhsValue)) && (isBoolean(rhsValue))) {
/*  86 */       Boolean lhsBoolean = ((List)lhsValue).isEmpty() ? Boolean.FALSE : Boolean.TRUE;
/*  87 */       Boolean rhsBoolean = (Boolean)rhsValue;
/*  88 */       return Boolean.valueOf(evaluateObjectObject(lhsBoolean, rhsBoolean, nav));
/*     */     }
/*  90 */     if ((isBoolean(lhsValue)) && (isSet(rhsValue))) {
/*  91 */       Boolean lhsBoolean = (Boolean)lhsValue;
/*  92 */       Boolean rhsBoolean = ((List)rhsValue).isEmpty() ? Boolean.FALSE : Boolean.TRUE;
/*  93 */       return Boolean.valueOf(evaluateObjectObject(lhsBoolean, rhsBoolean, nav));
/*     */     }
/*  95 */     if (eitherIsSet(lhsValue, rhsValue)) {
/*  96 */       if (isSet(lhsValue)) {
/*  97 */         return evaluateSetSet((List)lhsValue, convertToList(rhsValue), nav);
/*     */       }
/*     */ 
/* 100 */       return evaluateSetSet(convertToList(lhsValue), (List)rhsValue, nav);
/*     */     }
/*     */ 
/* 104 */     return Boolean.valueOf(evaluateObjectObject(lhsValue, rhsValue, nav));
/*     */   }
/*     */ 
/*     */   private Boolean evaluateSetSet(List lhsSet, List rhsSet, Navigator nav)
/*     */   {
/* 114 */     if ((setIsEmpty(lhsSet)) || (setIsEmpty(rhsSet))) {
/* 115 */       return Boolean.FALSE;
/*     */     }
/*     */ 
/* 118 */     for (Iterator lhsIterator = lhsSet.iterator(); lhsIterator.hasNext(); )
/*     */     {
/* 120 */       lhs = lhsIterator.next();
/*     */ 
/* 122 */       for (rhsIterator = rhsSet.iterator(); rhsIterator.hasNext(); )
/*     */       {
/* 124 */         Object rhs = rhsIterator.next();
/*     */ 
/* 126 */         if (evaluateObjectObject(lhs, rhs, nav))
/*     */         {
/* 128 */           return Boolean.TRUE;
/*     */         }
/*     */       }
/*     */     }
/*     */     Object lhs;
/*     */     Iterator rhsIterator;
/* 133 */     return Boolean.FALSE;
/*     */   }
/*     */ 
/*     */   private boolean evaluateObjectObject(Object lhs, Object rhs, Navigator nav)
/*     */   {
/* 138 */     if (eitherIsBoolean(lhs, rhs))
/*     */     {
/* 140 */       return evaluateObjectObject(BooleanFunction.evaluate(lhs, nav), BooleanFunction.evaluate(rhs, nav));
/*     */     }
/*     */ 
/* 143 */     if (eitherIsNumber(lhs, rhs))
/*     */     {
/* 145 */       return evaluateObjectObject(NumberFunction.evaluate(lhs, nav), NumberFunction.evaluate(rhs, nav));
/*     */     }
/*     */ 
/* 152 */     return evaluateObjectObject(StringFunction.evaluate(lhs, nav), StringFunction.evaluate(rhs, nav));
/*     */   }
/*     */ 
/*     */   protected abstract boolean evaluateObjectObject(Object paramObject1, Object paramObject2);
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultEqualityExpr
 * JD-Core Version:    0.6.2
 */