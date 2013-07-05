/*     */ package org.jaxen.expr;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.JaxenException;
/*     */ import org.jaxen.Navigator;
/*     */ import org.jaxen.function.NumberFunction;
/*     */ 
/*     */ abstract class DefaultRelationalExpr extends DefaultTruthExpr
/*     */   implements RelationalExpr
/*     */ {
/*     */   DefaultRelationalExpr(Expr lhs, Expr rhs)
/*     */   {
/*  63 */     super(lhs, rhs);
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/*  68 */     return "[(DefaultRelationalExpr): " + getLHS() + ", " + getRHS() + "]";
/*     */   }
/*     */ 
/*     */   public Object evaluate(Context context) throws JaxenException
/*     */   {
/*  73 */     Object lhsValue = getLHS().evaluate(context);
/*  74 */     Object rhsValue = getRHS().evaluate(context);
/*  75 */     Navigator nav = context.getNavigator();
/*     */ 
/*  77 */     if (bothAreSets(lhsValue, rhsValue))
/*     */     {
/*  79 */       return evaluateSetSet((List)lhsValue, (List)rhsValue, nav);
/*     */     }
/*     */ 
/*  82 */     if (eitherIsSet(lhsValue, rhsValue))
/*     */     {
/*  84 */       if (isSet(lhsValue))
/*     */       {
/*  86 */         return evaluateSetSet((List)lhsValue, convertToList(rhsValue), nav);
/*     */       }
/*     */ 
/*  90 */       return evaluateSetSet(convertToList(lhsValue), (List)rhsValue, nav);
/*     */     }
/*     */ 
/*  94 */     return evaluateObjectObject(lhsValue, rhsValue, nav) ? Boolean.TRUE : Boolean.FALSE;
/*     */   }
/*     */ 
/*     */   private Object evaluateSetSet(List lhsSet, List rhsSet, Navigator nav)
/*     */   {
/*  99 */     if ((setIsEmpty(lhsSet)) || (setIsEmpty(rhsSet)))
/*     */     {
/* 101 */       return Boolean.FALSE;
/*     */     }
/*     */ 
/* 104 */     for (Iterator lhsIterator = lhsSet.iterator(); lhsIterator.hasNext(); )
/*     */     {
/* 106 */       lhs = lhsIterator.next();
/*     */ 
/* 108 */       for (rhsIterator = rhsSet.iterator(); rhsIterator.hasNext(); )
/*     */       {
/* 110 */         Object rhs = rhsIterator.next();
/*     */ 
/* 112 */         if (evaluateObjectObject(lhs, rhs, nav))
/*     */         {
/* 114 */           return Boolean.TRUE;
/*     */         }
/*     */       }
/*     */     }
/*     */     Object lhs;
/*     */     Iterator rhsIterator;
/* 119 */     return Boolean.FALSE;
/*     */   }
/*     */ 
/*     */   private boolean evaluateObjectObject(Object lhs, Object rhs, Navigator nav)
/*     */   {
/* 124 */     if ((lhs == null) || (rhs == null))
/*     */     {
/* 126 */       return false;
/*     */     }
/*     */ 
/* 129 */     Double lhsNum = NumberFunction.evaluate(lhs, nav);
/* 130 */     Double rhsNum = NumberFunction.evaluate(rhs, nav);
/*     */ 
/* 132 */     if ((NumberFunction.isNaN(lhsNum)) || (NumberFunction.isNaN(rhsNum)))
/*     */     {
/* 134 */       return false;
/*     */     }
/*     */ 
/* 137 */     return evaluateDoubleDouble(lhsNum, rhsNum);
/*     */   }
/*     */ 
/*     */   protected abstract boolean evaluateDoubleDouble(Double paramDouble1, Double paramDouble2);
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultRelationalExpr
 * JD-Core Version:    0.6.2
 */