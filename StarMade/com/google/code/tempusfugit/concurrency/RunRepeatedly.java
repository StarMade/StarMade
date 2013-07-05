/*    */ package com.google.code.tempusfugit.concurrency;
/*    */ 
/*    */ import com.google.code.tempusfugit.concurrency.annotations.Repeating;
/*    */ import junit.framework.AssertionFailedError;
/*    */ import org.junit.runners.model.FrameworkMethod;
/*    */ import org.junit.runners.model.Statement;
/*    */ 
/*    */ class RunRepeatedly extends Statement
/*    */ {
/*    */   private final FrameworkMethod method;
/*    */   private final Statement statement;
/*    */ 
/*    */   RunRepeatedly(FrameworkMethod method, Statement statement)
/*    */   {
/* 30 */     this.method = method;
/* 31 */     this.statement = statement;
/*    */   }
/*    */ 
/*    */   public void evaluate() throws Throwable {
/* 35 */     if (repeating(this.method)) {
/* 36 */       for (int i = 0; i < repetition(this.method); i++)
/*    */         try {
/* 38 */           this.statement.evaluate();
/*    */         } catch (AssertionFailedError e) {
/* 40 */           throw new AssertionFailedError(String.format("%s (failed after %d successful attempts)", new Object[] { e.getMessage(), Integer.valueOf(i) }));
/*    */         }
/*    */     }
/*    */     else
/* 44 */       this.statement.evaluate();
/*    */   }
/*    */ 
/*    */   private static boolean repeating(FrameworkMethod method) {
/* 48 */     return method.getAnnotation(Repeating.class) != null;
/*    */   }
/*    */ 
/*    */   private static int repetition(FrameworkMethod method) {
/* 52 */     return ((Repeating)method.getAnnotation(Repeating.class)).repetition();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.concurrency.RunRepeatedly
 * JD-Core Version:    0.6.2
 */