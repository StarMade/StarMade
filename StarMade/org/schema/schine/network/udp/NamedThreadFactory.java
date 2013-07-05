/*    */ package org.schema.schine.network.udp;
/*    */ 
/*    */ import java.util.concurrent.Executors;
/*    */ import java.util.concurrent.ThreadFactory;
/*    */ 
/*    */ public class NamedThreadFactory
/*    */   implements ThreadFactory
/*    */ {
/*    */   private String name;
/*    */   private boolean daemon;
/*    */   private ThreadFactory delegate;
/*    */ 
/*    */   public NamedThreadFactory(String paramString)
/*    */   {
/* 14 */     this(paramString, Executors.defaultThreadFactory());
/*    */   }
/*    */ 
/*    */   public NamedThreadFactory(String paramString, boolean paramBoolean)
/*    */   {
/* 19 */     this(paramString, paramBoolean, Executors.defaultThreadFactory());
/*    */   }
/*    */ 
/*    */   public NamedThreadFactory(String paramString, boolean paramBoolean, ThreadFactory paramThreadFactory)
/*    */   {
/* 24 */     this.name = paramString;
/* 25 */     this.daemon = paramBoolean;
/* 26 */     this.delegate = paramThreadFactory;
/*    */   }
/*    */ 
/*    */   public NamedThreadFactory(String paramString, ThreadFactory paramThreadFactory)
/*    */   {
/* 31 */     this(paramString, false, paramThreadFactory);
/*    */   }
/*    */ 
/*    */   public Thread newThread(Runnable paramRunnable)
/*    */   {
/* 38 */     String str = (
/* 38 */       paramRunnable = this.delegate.newThread(paramRunnable))
/* 38 */       .getName();
/* 39 */     paramRunnable.setName(this.name + "[" + str + "]");
/* 40 */     paramRunnable.setDaemon(this.daemon);
/* 41 */     return paramRunnable;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.udp.NamedThreadFactory
 * JD-Core Version:    0.6.2
 */