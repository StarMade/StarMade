/*    */ package org.jaxen.pattern;
/*    */ 
/*    */ import org.jaxen.Context;
/*    */ import org.jaxen.Navigator;
/*    */ 
/*    */ public class TextNodeTest extends NodeTest
/*    */ {
/* 59 */   public static final TextNodeTest SINGLETON = new TextNodeTest();
/*    */ 
/*    */   public boolean matches(Object node, Context context)
/*    */   {
/* 69 */     return context.getNavigator().isText(node);
/*    */   }
/*    */ 
/*    */   public double getPriority()
/*    */   {
/* 74 */     return -0.5D;
/*    */   }
/*    */ 
/*    */   public short getMatchType()
/*    */   {
/* 79 */     return 3;
/*    */   }
/*    */ 
/*    */   public String getText()
/*    */   {
/* 84 */     return "text()";
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.pattern.TextNodeTest
 * JD-Core Version:    0.6.2
 */