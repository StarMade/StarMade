/*     */ package org.jaxen.expr;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.ContextSupport;
/*     */ import org.jaxen.JaxenException;
/*     */ import org.jaxen.UnsupportedAxisException;
/*     */ import org.jaxen.expr.iter.IterableAxis;
/*     */ import org.jaxen.saxpath.Axis;
/*     */ 
/*     */ /** @deprecated */
/*     */ public abstract class DefaultStep
/*     */   implements Step
/*     */ {
/*     */   private IterableAxis axis;
/*     */   private PredicateSet predicates;
/*     */ 
/*     */   public DefaultStep(IterableAxis axis, PredicateSet predicates)
/*     */   {
/*  71 */     this.axis = axis;
/*  72 */     this.predicates = predicates;
/*     */   }
/*     */ 
/*     */   public void addPredicate(Predicate predicate)
/*     */   {
/*  77 */     this.predicates.addPredicate(predicate);
/*     */   }
/*     */ 
/*     */   public List getPredicates()
/*     */   {
/*  82 */     return this.predicates.getPredicates();
/*     */   }
/*     */ 
/*     */   public PredicateSet getPredicateSet()
/*     */   {
/*  87 */     return this.predicates;
/*     */   }
/*     */ 
/*     */   public int getAxis()
/*     */   {
/*  92 */     return this.axis.value();
/*     */   }
/*     */ 
/*     */   public IterableAxis getIterableAxis()
/*     */   {
/*  97 */     return this.axis;
/*     */   }
/*     */ 
/*     */   public String getAxisName()
/*     */   {
/* 102 */     return Axis.lookup(getAxis());
/*     */   }
/*     */ 
/*     */   public String getText()
/*     */   {
/* 107 */     return this.predicates.getText();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 112 */     return getIterableAxis() + " " + super.toString();
/*     */   }
/*     */ 
/*     */   public void simplify()
/*     */   {
/* 117 */     this.predicates.simplify();
/*     */   }
/*     */ 
/*     */   public Iterator axisIterator(Object contextNode, ContextSupport support)
/*     */     throws UnsupportedAxisException
/*     */   {
/* 123 */     return getIterableAxis().iterator(contextNode, support);
/*     */   }
/*     */ 
/*     */   public List evaluate(Context context) throws JaxenException
/*     */   {
/* 128 */     List contextNodeSet = context.getNodeSet();
/* 129 */     IdentitySet unique = new IdentitySet();
/* 130 */     int contextSize = contextNodeSet.size();
/*     */ 
/* 134 */     ArrayList interimSet = new ArrayList();
/* 135 */     ArrayList newNodeSet = new ArrayList();
/* 136 */     ContextSupport support = context.getContextSupport();
/*     */ 
/* 139 */     for (int i = 0; i < contextSize; i++)
/*     */     {
/* 141 */       Object eachContextNode = contextNodeSet.get(i);
/*     */ 
/* 151 */       Iterator axisNodeIter = this.axis.iterator(eachContextNode, support);
/* 152 */       while (axisNodeIter.hasNext())
/*     */       {
/* 154 */         Object eachAxisNode = axisNodeIter.next();
/* 155 */         if (!unique.contains(eachAxisNode))
/*     */         {
/* 157 */           if (matches(eachAxisNode, support))
/*     */           {
/* 159 */             unique.add(eachAxisNode);
/* 160 */             interimSet.add(eachAxisNode);
/*     */           }
/*     */         }
/*     */       }
/* 164 */       newNodeSet.addAll(getPredicateSet().evaluatePredicates(interimSet, support));
/*     */ 
/* 166 */       interimSet.clear();
/*     */     }
/* 168 */     return newNodeSet;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultStep
 * JD-Core Version:    0.6.2
 */