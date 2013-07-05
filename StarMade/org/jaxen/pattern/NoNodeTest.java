/*    */ package org.jaxen.pattern;
/*    */ 
/*    */ import org.jaxen.Context;
/*    */ 
/*    */ public class NoNodeTest extends NodeTest
/*    */ {
/* 59 */   private static NoNodeTest instance = new NoNodeTest();
/*    */ 
/*    */   public static NoNodeTest getInstance()
/*    */   {
/* 63 */     return instance;
/*    */   }
/*    */ 
/*    */   public boolean matches(Object node, Context context)
/*    */   {
/* 74 */     return false;
/*    */   }
/*    */ 
/*    */   public double getPriority()
/*    */   {
/* 79 */     return -0.5D;
/*    */   }
/*    */ 
/*    */   public short getMatchType()
/*    */   {
/* 84 */     return 14;
/*    */   }
/*    */ 
/*    */   public String getText()
/*    */   {
/* 89 */     return "";
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.pattern.NoNodeTest
 * JD-Core Version:    0.6.2
 */