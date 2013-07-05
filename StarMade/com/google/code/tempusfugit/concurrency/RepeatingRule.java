/*    */ package com.google.code.tempusfugit.concurrency;
/*    */ 
/*    */ import org.junit.rules.MethodRule;
/*    */ import org.junit.runners.model.FrameworkMethod;
/*    */ import org.junit.runners.model.Statement;
/*    */ 
/*    */ public class RepeatingRule
/*    */   implements MethodRule
/*    */ {
/*    */   public Statement apply(Statement base, FrameworkMethod method, Object target)
/*    */   {
/* 26 */     return new RunRepeatedly(method, base);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.concurrency.RepeatingRule
 * JD-Core Version:    0.6.2
 */