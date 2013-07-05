/*     */ package org.apache.commons.lang3.concurrent;
/*     */ 
/*     */ import java.util.concurrent.Callable;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ 
/*     */ public class CallableBackgroundInitializer<T> extends BackgroundInitializer<T>
/*     */ {
/*     */   private final Callable<T> callable;
/*     */ 
/*     */   public CallableBackgroundInitializer(Callable<T> call)
/*     */   {
/*  80 */     checkCallable(call);
/*  81 */     this.callable = call;
/*     */   }
/*     */ 
/*     */   public CallableBackgroundInitializer(Callable<T> call, ExecutorService exec)
/*     */   {
/*  96 */     super(exec);
/*  97 */     checkCallable(call);
/*  98 */     this.callable = call;
/*     */   }
/*     */ 
/*     */   protected T initialize()
/*     */     throws Exception
/*     */   {
/* 111 */     return this.callable.call();
/*     */   }
/*     */ 
/*     */   private void checkCallable(Callable<T> call)
/*     */   {
/* 122 */     if (call == null)
/* 123 */       throw new IllegalArgumentException("Callable must not be null!");
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.concurrent.CallableBackgroundInitializer
 * JD-Core Version:    0.6.2
 */