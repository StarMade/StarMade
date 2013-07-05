/*    */ package com.google.code.tempusfugit.concurrency;
/*    */ 
/*    */ import com.google.code.tempusfugit.concurrency.annotations.Intermittent;
/*    */ import org.junit.runner.notification.RunNotifier;
/*    */ import org.junit.runners.BlockJUnit4ClassRunner;
/*    */ import org.junit.runners.model.FrameworkMethod;
/*    */ import org.junit.runners.model.InitializationError;
/*    */ 
/*    */ public class IntermittentTestRunner extends BlockJUnit4ClassRunner
/*    */ {
/*    */   private final Class<?> type;
/*    */ 
/*    */   public IntermittentTestRunner(Class<?> type)
/*    */     throws InitializationError
/*    */   {
/* 29 */     super(type);
/* 30 */     this.type = type;
/*    */   }
/*    */ 
/*    */   protected void runChild(FrameworkMethod method, RunNotifier notifier)
/*    */   {
/* 35 */     for (int i = 0; i < repeatCount(method); i++)
/* 36 */       super.runChild(method, notifier);
/*    */   }
/*    */ 
/*    */   private int repeatCount(FrameworkMethod method) {
/* 40 */     if (intermittent(this.type))
/* 41 */       return repetition(this.type);
/* 42 */     if (intermittent(method))
/* 43 */       return repetition(method);
/* 44 */     return 1;
/*    */   }
/*    */ 
/*    */   private static boolean intermittent(FrameworkMethod method) {
/* 48 */     return method.getAnnotation(Intermittent.class) != null;
/*    */   }
/*    */ 
/*    */   private static boolean intermittent(Class<?> type) {
/* 52 */     return type.getAnnotation(Intermittent.class) != null;
/*    */   }
/*    */ 
/*    */   private static int repetition(FrameworkMethod method) {
/* 56 */     return ((Intermittent)method.getAnnotation(Intermittent.class)).repetition();
/*    */   }
/*    */ 
/*    */   private static int repetition(Class<?> type) {
/* 60 */     return ((Intermittent)type.getAnnotation(Intermittent.class)).repetition();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.concurrency.IntermittentTestRunner
 * JD-Core Version:    0.6.2
 */