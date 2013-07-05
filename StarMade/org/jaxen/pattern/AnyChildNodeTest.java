/*    */ package org.jaxen.pattern;
/*    */ 
/*    */ import org.jaxen.Context;
/*    */ import org.jaxen.Navigator;
/*    */ 
/*    */ public class AnyChildNodeTest extends NodeTest
/*    */ {
/* 59 */   private static AnyChildNodeTest instance = new AnyChildNodeTest();
/*    */ 
/*    */   public static AnyChildNodeTest getInstance()
/*    */   {
/* 63 */     return instance;
/*    */   }
/*    */ 
/*    */   public boolean matches(Object node, Context context)
/*    */   {
/* 74 */     short type = context.getNavigator().getNodeType(node);
/* 75 */     return (type == 1) || (type == 3) || (type == 8) || (type == 7);
/*    */   }
/*    */ 
/*    */   public double getPriority()
/*    */   {
/* 81 */     return -0.5D;
/*    */   }
/*    */ 
/*    */   public short getMatchType()
/*    */   {
/* 86 */     return 0;
/*    */   }
/*    */ 
/*    */   public String getText()
/*    */   {
/* 91 */     return "*";
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.pattern.AnyChildNodeTest
 * JD-Core Version:    0.6.2
 */