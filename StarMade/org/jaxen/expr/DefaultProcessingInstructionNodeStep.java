/*     */ package org.jaxen.expr;
/*     */ 
/*     */ import org.jaxen.ContextSupport;
/*     */ import org.jaxen.Navigator;
/*     */ import org.jaxen.expr.iter.IterableAxis;
/*     */ 
/*     */ /** @deprecated */
/*     */ public class DefaultProcessingInstructionNodeStep extends DefaultStep
/*     */   implements ProcessingInstructionNodeStep
/*     */ {
/*     */   private static final long serialVersionUID = -4825000697808126927L;
/*     */   private String name;
/*     */ 
/*     */   public DefaultProcessingInstructionNodeStep(IterableAxis axis, String name, PredicateSet predicateSet)
/*     */   {
/*  73 */     super(axis, predicateSet);
/*     */ 
/*  75 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/*  80 */     return this.name;
/*     */   }
/*     */ 
/*     */   public String getText()
/*     */   {
/*  85 */     StringBuffer buf = new StringBuffer();
/*  86 */     buf.append(getAxisName());
/*  87 */     buf.append("::processing-instruction(");
/*  88 */     String name = getName();
/*  89 */     if ((name != null) && (name.length() != 0))
/*     */     {
/*  91 */       buf.append("'");
/*  92 */       buf.append(name);
/*  93 */       buf.append("'");
/*     */     }
/*  95 */     buf.append(")");
/*  96 */     buf.append(super.getText());
/*  97 */     return buf.toString();
/*     */   }
/*     */ 
/*     */   public boolean matches(Object node, ContextSupport support)
/*     */   {
/* 104 */     Navigator nav = support.getNavigator();
/* 105 */     if (nav.isProcessingInstruction(node))
/*     */     {
/* 107 */       String name = getName();
/* 108 */       if ((name == null) || (name.length() == 0))
/*     */       {
/* 110 */         return true;
/*     */       }
/*     */ 
/* 114 */       return name.equals(nav.getProcessingInstructionTarget(node));
/*     */     }
/*     */ 
/* 118 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultProcessingInstructionNodeStep
 * JD-Core Version:    0.6.2
 */