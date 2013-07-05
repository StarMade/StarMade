/*     */ package org.jaxen.expr;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.ContextSupport;
/*     */ import org.jaxen.JaxenException;
/*     */ 
/*     */ abstract class DefaultLocationPath extends DefaultExpr
/*     */   implements LocationPath
/*     */ {
/*     */   private List steps;
/*     */ 
/*     */   DefaultLocationPath()
/*     */   {
/*  69 */     this.steps = new LinkedList();
/*     */   }
/*     */ 
/*     */   public void addStep(Step step)
/*     */   {
/*  74 */     getSteps().add(step);
/*     */   }
/*     */ 
/*     */   public List getSteps()
/*     */   {
/*  79 */     return this.steps;
/*     */   }
/*     */ 
/*     */   public Expr simplify()
/*     */   {
/*  84 */     Iterator stepIter = getSteps().iterator();
/*  85 */     Step eachStep = null;
/*  86 */     while (stepIter.hasNext())
/*     */     {
/*  88 */       eachStep = (Step)stepIter.next();
/*  89 */       eachStep.simplify();
/*     */     }
/*  91 */     return this;
/*     */   }
/*     */ 
/*     */   public String getText()
/*     */   {
/*  96 */     StringBuffer buf = new StringBuffer();
/*  97 */     Iterator stepIter = getSteps().iterator();
/*  98 */     while (stepIter.hasNext())
/*     */     {
/* 100 */       buf.append(((Step)stepIter.next()).getText());
/* 101 */       if (stepIter.hasNext())
/*     */       {
/* 103 */         buf.append("/");
/*     */       }
/*     */     }
/* 106 */     return buf.toString();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 111 */     StringBuffer buf = new StringBuffer();
/* 112 */     Iterator stepIter = getSteps().iterator();
/* 113 */     while (stepIter.hasNext())
/*     */     {
/* 115 */       buf.append(stepIter.next().toString());
/* 116 */       if (stepIter.hasNext())
/*     */       {
/* 118 */         buf.append("/");
/*     */       }
/*     */     }
/* 121 */     return buf.toString();
/*     */   }
/*     */ 
/*     */   public boolean isAbsolute()
/*     */   {
/* 126 */     return false;
/*     */   }
/*     */ 
/*     */   public Object evaluate(Context context) throws JaxenException
/*     */   {
/* 131 */     List nodeSet = context.getNodeSet();
/* 132 */     List contextNodeSet = new ArrayList(nodeSet);
/* 133 */     ContextSupport support = context.getContextSupport();
/* 134 */     Context stepContext = new Context(support);
/* 135 */     Iterator stepIter = getSteps().iterator();
/* 136 */     while (stepIter.hasNext())
/*     */     {
/* 138 */       Step eachStep = (Step)stepIter.next();
/* 139 */       stepContext.setNodeSet(contextNodeSet);
/* 140 */       contextNodeSet = eachStep.evaluate(stepContext);
/*     */ 
/* 142 */       if (isReverseAxis(eachStep)) {
/* 143 */         Collections.reverse(contextNodeSet);
/*     */       }
/*     */     }
/*     */ 
/* 147 */     if ((getSteps().size() > 1) || (nodeSet.size() > 1)) {
/* 148 */       Collections.sort(contextNodeSet, new NodeComparator(support.getNavigator()));
/*     */     }
/*     */ 
/* 151 */     return contextNodeSet;
/*     */   }
/*     */ 
/*     */   private boolean isReverseAxis(Step step)
/*     */   {
/* 156 */     int axis = step.getAxis();
/* 157 */     return (axis == 8) || (axis == 6) || (axis == 4) || (axis == 13);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultLocationPath
 * JD-Core Version:    0.6.2
 */