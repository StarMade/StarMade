/*     */ package org.apache.commons.lang3.concurrent;
/*     */ 
/*     */ import java.util.concurrent.Callable;
/*     */ import java.util.concurrent.ExecutionException;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.Executors;
/*     */ import java.util.concurrent.Future;
/*     */ 
/*     */ public abstract class BackgroundInitializer<T>
/*     */   implements ConcurrentInitializer<T>
/*     */ {
/*     */   private ExecutorService externalExecutor;
/*     */   private ExecutorService executor;
/*     */   private Future<T> future;
/*     */ 
/*     */   protected BackgroundInitializer()
/*     */   {
/* 102 */     this(null);
/*     */   }
/*     */ 
/*     */   protected BackgroundInitializer(ExecutorService exec)
/*     */   {
/* 116 */     setExternalExecutor(exec);
/*     */   }
/*     */ 
/*     */   public final synchronized ExecutorService getExternalExecutor()
/*     */   {
/* 125 */     return this.externalExecutor;
/*     */   }
/*     */ 
/*     */   public synchronized boolean isStarted()
/*     */   {
/* 136 */     return this.future != null;
/*     */   }
/*     */ 
/*     */   public final synchronized void setExternalExecutor(ExecutorService externalExecutor)
/*     */   {
/* 155 */     if (isStarted()) {
/* 156 */       throw new IllegalStateException("Cannot set ExecutorService after start()!");
/*     */     }
/*     */ 
/* 160 */     this.externalExecutor = externalExecutor;
/*     */   }
/*     */ 
/*     */   public synchronized boolean start()
/*     */   {
/* 175 */     if (!isStarted())
/*     */     {
/* 180 */       this.executor = getExternalExecutor();
/*     */       ExecutorService tempExec;
/* 181 */       if (this.executor == null)
/*     */       {
/*     */         ExecutorService tempExec;
/* 182 */         this.executor = (tempExec = createExecutor());
/*     */       } else {
/* 184 */         tempExec = null;
/*     */       }
/*     */ 
/* 187 */       this.future = this.executor.submit(createTask(tempExec));
/*     */ 
/* 189 */       return true;
/*     */     }
/*     */ 
/* 192 */     return false;
/*     */   }
/*     */ 
/*     */   public T get()
/*     */     throws ConcurrentException
/*     */   {
/*     */     try
/*     */     {
/* 211 */       return getFuture().get();
/*     */     } catch (ExecutionException execex) {
/* 213 */       ConcurrentUtils.handleCause(execex);
/* 214 */       return null;
/*     */     }
/*     */     catch (InterruptedException iex) {
/* 217 */       Thread.currentThread().interrupt();
/* 218 */       throw new ConcurrentException(iex);
/*     */     }
/*     */   }
/*     */ 
/*     */   public synchronized Future<T> getFuture()
/*     */   {
/* 231 */     if (this.future == null) {
/* 232 */       throw new IllegalStateException("start() must be called first!");
/*     */     }
/*     */ 
/* 235 */     return this.future;
/*     */   }
/*     */ 
/*     */   protected final synchronized ExecutorService getActiveExecutor()
/*     */   {
/* 248 */     return this.executor;
/*     */   }
/*     */ 
/*     */   protected int getTaskCount()
/*     */   {
/* 263 */     return 1;
/*     */   }
/*     */ 
/*     */   protected abstract T initialize()
/*     */     throws Exception;
/*     */ 
/*     */   private Callable<T> createTask(ExecutorService execDestroy)
/*     */   {
/* 290 */     return new InitializationTask(execDestroy);
/*     */   }
/*     */ 
/*     */   private ExecutorService createExecutor()
/*     */   {
/* 300 */     return Executors.newFixedThreadPool(getTaskCount());
/*     */   }
/*     */ 
/*     */   private class InitializationTask
/*     */     implements Callable<T>
/*     */   {
/*     */     private final ExecutorService execFinally;
/*     */ 
/*     */     public InitializationTask(ExecutorService exec)
/*     */     {
/* 314 */       this.execFinally = exec;
/*     */     }
/*     */ 
/*     */     public T call()
/*     */       throws Exception
/*     */     {
/*     */       try
/*     */       {
/* 325 */         return BackgroundInitializer.this.initialize();
/*     */       } finally {
/* 327 */         if (this.execFinally != null)
/* 328 */           this.execFinally.shutdown();
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.concurrent.BackgroundInitializer
 * JD-Core Version:    0.6.2
 */