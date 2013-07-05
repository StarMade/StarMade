/*    */ package org.jaxen.expr;
/*    */ 
/*    */ import org.jaxen.ContextSupport;
/*    */ import org.jaxen.expr.iter.IterableAxis;
/*    */ 
/*    */ /** @deprecated */
/*    */ public class DefaultAllNodeStep extends DefaultStep
/*    */   implements AllNodeStep
/*    */ {
/*    */   private static final long serialVersionUID = 292886316770123856L;
/*    */ 
/*    */   public DefaultAllNodeStep(IterableAxis axis, PredicateSet predicateSet)
/*    */   {
/* 68 */     super(axis, predicateSet);
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 73 */     return "[(DefaultAllNodeStep): " + getAxisName() + "]";
/*    */   }
/*    */ 
/*    */   public String getText()
/*    */   {
/* 78 */     return getAxisName() + "::node()" + super.getText();
/*    */   }
/*    */ 
/*    */   public boolean matches(Object node, ContextSupport contextSupport)
/*    */   {
/* 84 */     return true;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultAllNodeStep
 * JD-Core Version:    0.6.2
 */