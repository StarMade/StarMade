/*    */ package org.jaxen.pattern;
/*    */ 
/*    */ import org.jaxen.Context;
/*    */ 
/*    */ public class AnyNodeTest extends NodeTest
/*    */ {
/* 59 */   private static AnyNodeTest instance = new AnyNodeTest();
/*    */ 
/*    */   public static AnyNodeTest getInstance()
/*    */   {
/* 63 */     return instance;
/*    */   }
/*    */ 
/*    */   public boolean matches(Object node, Context context)
/*    */   {
/* 72 */     return true;
/*    */   }
/*    */ 
/*    */   public double getPriority()
/*    */   {
/* 77 */     return -0.5D;
/*    */   }
/*    */ 
/*    */   public short getMatchType()
/*    */   {
/* 82 */     return 0;
/*    */   }
/*    */ 
/*    */   public String getText()
/*    */   {
/* 87 */     return "*";
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.pattern.AnyNodeTest
 * JD-Core Version:    0.6.2
 */