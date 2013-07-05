/*     */ package com.google.code.tempusfugit.temporal;
/*     */ 
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import org.hamcrest.Matcher;
/*     */ import org.junit.Assert;
/*     */ 
/*     */ public final class Conditions
/*     */ {
/*     */   public static Condition not(Condition condition)
/*     */   {
/*  30 */     return new NotCondition(condition);
/*     */   }
/*     */ 
/*     */   public static Condition shutdown(ExecutorService service) {
/*  34 */     return new ExecutorShutdownCondition(service);
/*     */   }
/*     */ 
/*     */   public static Condition isAlive(Thread thread) {
/*  38 */     return new ThreadAliveCondition(thread);
/*     */   }
/*     */ 
/*     */   public static Condition isWaiting(Thread thread) {
/*  42 */     return new ThreadWaitingCondition(thread);
/*     */   }
/*     */ 
/*     */   public static Condition is(Thread thread, Thread.State state) {
/*  46 */     return new ThreadStateCondition(thread, state);
/*     */   }
/*     */ 
/*     */   public static void assertThat(Condition condition, Matcher<Boolean> booleanMatcher) {
/*  50 */     Assert.assertThat(Boolean.valueOf(condition.isSatisfied()), booleanMatcher);
/*     */   }
/*     */ 
/*     */   public static void assertThat(String message, Condition condition, Matcher<Boolean> booleanMatcher) {
/*  54 */     Assert.assertThat(message, Boolean.valueOf(condition.isSatisfied()), booleanMatcher);
/*     */   }
/*     */ 
/*     */   public static <T> Condition assertion(T actual, Matcher<T> matcher)
/*     */   {
/*  63 */     return new MatcherCondition(matcher, actual);
/*     */   }
/*     */ 
/*     */   private static class MatcherCondition<T>
/*     */     implements Condition
/*     */   {
/*     */     private final Matcher<T> matcher;
/*     */     private final T actual;
/*     */ 
/*     */     public MatcherCondition(Matcher<T> matcher, T actual)
/*     */     {
/* 137 */       this.matcher = matcher;
/* 138 */       this.actual = actual;
/*     */     }
/*     */ 
/*     */     public boolean isSatisfied() {
/* 142 */       return this.matcher.matches(this.actual);
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class ThreadStateCondition
/*     */     implements Condition
/*     */   {
/*     */     private final Thread thread;
/*     */     private final Thread.State state;
/*     */ 
/*     */     public ThreadStateCondition(Thread thread, Thread.State state)
/*     */     {
/* 122 */       this.thread = thread;
/* 123 */       this.state = state;
/*     */     }
/*     */ 
/*     */     public boolean isSatisfied() {
/* 127 */       return this.thread.getState() == this.state;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class ThreadWaitingCondition
/*     */     implements Condition
/*     */   {
/*     */     private final Thread thread;
/*     */ 
/*     */     public ThreadWaitingCondition(Thread thread)
/*     */     {
/* 109 */       this.thread = thread;
/*     */     }
/*     */ 
/*     */     public boolean isSatisfied() {
/* 113 */       return (this.thread.getState() == Thread.State.TIMED_WAITING) || (this.thread.getState() == Thread.State.WAITING);
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class ThreadAliveCondition
/*     */     implements Condition
/*     */   {
/*     */     private final Thread thread;
/*     */ 
/*     */     public ThreadAliveCondition(Thread thread)
/*     */     {
/*  97 */       this.thread = thread;
/*     */     }
/*     */ 
/*     */     public boolean isSatisfied() {
/* 101 */       return this.thread.isAlive();
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class ExecutorShutdownCondition
/*     */     implements Condition
/*     */   {
/*     */     private final ExecutorService executor;
/*     */ 
/*     */     public ExecutorShutdownCondition(ExecutorService executor)
/*     */     {
/*  85 */       this.executor = executor;
/*     */     }
/*     */ 
/*     */     public boolean isSatisfied() {
/*  89 */       return this.executor.isShutdown();
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class NotCondition
/*     */     implements Condition
/*     */   {
/*     */     private final Condition condition;
/*     */ 
/*     */     public NotCondition(Condition condition)
/*     */     {
/*  72 */       this.condition = condition;
/*     */     }
/*     */ 
/*     */     public boolean isSatisfied() {
/*  76 */       return !this.condition.isSatisfied();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.temporal.Conditions
 * JD-Core Version:    0.6.2
 */