/*    */ package org.jaxen.expr;
/*    */ 
/*    */ import org.jaxen.ContextSupport;
/*    */ import org.jaxen.Navigator;
/*    */ import org.jaxen.expr.iter.IterableAxis;
/*    */ 
/*    */ /** @deprecated */
/*    */ public class DefaultCommentNodeStep extends DefaultStep
/*    */   implements CommentNodeStep
/*    */ {
/*    */   private static final long serialVersionUID = 4340788283861875606L;
/*    */ 
/*    */   public DefaultCommentNodeStep(IterableAxis axis, PredicateSet predicateSet)
/*    */   {
/* 68 */     super(axis, predicateSet);
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 73 */     return "[(DefaultCommentNodeStep): " + getAxis() + "]";
/*    */   }
/*    */ 
/*    */   public String getText()
/*    */   {
/* 78 */     return getAxisName() + "::comment()";
/*    */   }
/*    */ 
/*    */   public boolean matches(Object node, ContextSupport contextSupport)
/*    */   {
/* 84 */     Navigator nav = contextSupport.getNavigator();
/*    */ 
/* 86 */     return nav.isComment(node);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultCommentNodeStep
 * JD-Core Version:    0.6.2
 */