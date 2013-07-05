/*    */ package com.google.code.tempusfugit.concurrency;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.concurrent.TimeoutException;
/*    */ 
/*    */ public final class TimeoutExceptionWithResults extends TimeoutException
/*    */ {
/* 25 */   private List<?> results = new ArrayList();
/*    */ 
/*    */   public TimeoutExceptionWithResults(String message) {
/* 28 */     super(message);
/*    */   }
/*    */ 
/*    */   public <T> TimeoutExceptionWithResults(String message, List<T> results) {
/* 32 */     this(message);
/* 33 */     this.results = new ArrayList(results);
/*    */   }
/*    */ 
/*    */   public <T> TimeoutExceptionWithResults(List<T> results)
/*    */   {
/* 38 */     this.results = new ArrayList(results);
/*    */   }
/*    */ 
/*    */   public <T> List<T> getResults() {
/* 42 */     return this.results;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.concurrency.TimeoutExceptionWithResults
 * JD-Core Version:    0.6.2
 */