/*    */ package org.dom4j.rule;
/*    */ 
/*    */ import org.dom4j.Node;
/*    */ 
/*    */ public class NullAction
/*    */   implements Action
/*    */ {
/* 22 */   public static final NullAction SINGLETON = new NullAction();
/*    */ 
/*    */   public void run(Node node)
/*    */     throws Exception
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.rule.NullAction
 * JD-Core Version:    0.6.2
 */