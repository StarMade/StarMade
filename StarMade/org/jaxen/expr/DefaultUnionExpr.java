/*     */ package org.jaxen.expr;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.JaxenException;
/*     */ import org.jaxen.XPathSyntaxException;
/*     */ 
/*     */ /** @deprecated */
/*     */ public class DefaultUnionExpr extends DefaultBinaryExpr
/*     */   implements UnionExpr
/*     */ {
/*     */   private static final long serialVersionUID = 7629142718276852707L;
/*     */ 
/*     */   public DefaultUnionExpr(Expr lhs, Expr rhs)
/*     */   {
/*  76 */     super(lhs, rhs);
/*     */   }
/*     */ 
/*     */   public String getOperator()
/*     */   {
/*  82 */     return "|";
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/*  87 */     return "[(DefaultUnionExpr): " + getLHS() + ", " + getRHS() + "]";
/*     */   }
/*     */ 
/*     */   public Object evaluate(Context context) throws JaxenException
/*     */   {
/*  92 */     List results = new ArrayList();
/*     */     try
/*     */     {
/*  95 */       List lhsResults = (List)getLHS().evaluate(context);
/*  96 */       List rhsResults = (List)getRHS().evaluate(context);
/*     */ 
/*  98 */       Set unique = new HashSet();
/*     */ 
/* 100 */       results.addAll(lhsResults);
/* 101 */       unique.addAll(lhsResults);
/*     */ 
/* 103 */       Iterator rhsIter = rhsResults.iterator();
/*     */ 
/* 105 */       while (rhsIter.hasNext())
/*     */       {
/* 107 */         Object each = rhsIter.next();
/*     */ 
/* 109 */         if (!unique.contains(each))
/*     */         {
/* 111 */           results.add(each);
/* 112 */           unique.add(each);
/*     */         }
/*     */       }
/*     */ 
/* 116 */       Collections.sort(results, new NodeComparator(context.getNavigator()));
/*     */ 
/* 118 */       return results;
/*     */     } catch (ClassCastException e) {
/*     */     }
/* 121 */     throw new XPathSyntaxException(getText(), context.getPosition(), "Unions are only allowed over node-sets");
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultUnionExpr
 * JD-Core Version:    0.6.2
 */