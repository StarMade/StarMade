/*   1:    */package com.google.code.tempusfugit.temporal;
/*   2:    */
/*   3:    */import java.util.concurrent.ExecutorService;
/*   4:    */import org.hamcrest.Matcher;
/*   5:    */import org.junit.Assert;
/*   6:    */
/*  26:    */public final class Conditions
/*  27:    */{
/*  28:    */  public static Condition not(Condition condition)
/*  29:    */  {
/*  30: 30 */    return new NotCondition(condition);
/*  31:    */  }
/*  32:    */  
/*  33:    */  public static Condition shutdown(ExecutorService service) {
/*  34: 34 */    return new ExecutorShutdownCondition(service);
/*  35:    */  }
/*  36:    */  
/*  37:    */  public static Condition isAlive(Thread thread) {
/*  38: 38 */    return new ThreadAliveCondition(thread);
/*  39:    */  }
/*  40:    */  
/*  41:    */  public static Condition isWaiting(Thread thread) {
/*  42: 42 */    return new ThreadWaitingCondition(thread);
/*  43:    */  }
/*  44:    */  
/*  45:    */  public static Condition is(Thread thread, Thread.State state) {
/*  46: 46 */    return new ThreadStateCondition(thread, state);
/*  47:    */  }
/*  48:    */  
/*  49:    */  public static void assertThat(Condition condition, Matcher<Boolean> booleanMatcher) {
/*  50: 50 */    Assert.assertThat(Boolean.valueOf(condition.isSatisfied()), booleanMatcher);
/*  51:    */  }
/*  52:    */  
/*  53:    */  public static void assertThat(String message, Condition condition, Matcher<Boolean> booleanMatcher) {
/*  54: 54 */    Assert.assertThat(message, Boolean.valueOf(condition.isSatisfied()), booleanMatcher);
/*  55:    */  }
/*  56:    */  
/*  61:    */  public static <T> Condition assertion(T actual, Matcher<T> matcher)
/*  62:    */  {
/*  63: 63 */    return new MatcherCondition(matcher, actual);
/*  64:    */  }
/*  65:    */  
/*  66:    */  private static class NotCondition implements Condition
/*  67:    */  {
/*  68:    */    private final Condition condition;
/*  69:    */    
/*  70:    */    public NotCondition(Condition condition)
/*  71:    */    {
/*  72: 72 */      this.condition = condition;
/*  73:    */    }
/*  74:    */    
/*  75:    */    public boolean isSatisfied() {
/*  76: 76 */      return !this.condition.isSatisfied();
/*  77:    */    }
/*  78:    */  }
/*  79:    */  
/*  80:    */  private static class ExecutorShutdownCondition implements Condition
/*  81:    */  {
/*  82:    */    private final ExecutorService executor;
/*  83:    */    
/*  84:    */    public ExecutorShutdownCondition(ExecutorService executor) {
/*  85: 85 */      this.executor = executor;
/*  86:    */    }
/*  87:    */    
/*  88:    */    public boolean isSatisfied() {
/*  89: 89 */      return this.executor.isShutdown();
/*  90:    */    }
/*  91:    */  }
/*  92:    */  
/*  93:    */  private static class ThreadAliveCondition implements Condition {
/*  94:    */    private final Thread thread;
/*  95:    */    
/*  96:    */    public ThreadAliveCondition(Thread thread) {
/*  97: 97 */      this.thread = thread;
/*  98:    */    }
/*  99:    */    
/* 100:    */    public boolean isSatisfied() {
/* 101:101 */      return this.thread.isAlive();
/* 102:    */    }
/* 103:    */  }
/* 104:    */  
/* 105:    */  private static class ThreadWaitingCondition implements Condition {
/* 106:    */    private final Thread thread;
/* 107:    */    
/* 108:    */    public ThreadWaitingCondition(Thread thread) {
/* 109:109 */      this.thread = thread;
/* 110:    */    }
/* 111:    */    
/* 112:    */    public boolean isSatisfied() {
/* 113:113 */      return (this.thread.getState() == Thread.State.TIMED_WAITING) || (this.thread.getState() == Thread.State.WAITING);
/* 114:    */    }
/* 115:    */  }
/* 116:    */  
/* 117:    */  private static class ThreadStateCondition implements Condition {
/* 118:    */    private final Thread thread;
/* 119:    */    private final Thread.State state;
/* 120:    */    
/* 121:    */    public ThreadStateCondition(Thread thread, Thread.State state) {
/* 122:122 */      this.thread = thread;
/* 123:123 */      this.state = state;
/* 124:    */    }
/* 125:    */    
/* 126:    */    public boolean isSatisfied() {
/* 127:127 */      return this.thread.getState() == this.state;
/* 128:    */    }
/* 129:    */  }
/* 130:    */  
/* 131:    */  private static class MatcherCondition<T> implements Condition
/* 132:    */  {
/* 133:    */    private final Matcher<T> matcher;
/* 134:    */    private final T actual;
/* 135:    */    
/* 136:    */    public MatcherCondition(Matcher<T> matcher, T actual) {
/* 137:137 */      this.matcher = matcher;
/* 138:138 */      this.actual = actual;
/* 139:    */    }
/* 140:    */    
/* 141:    */    public boolean isSatisfied() {
/* 142:142 */      return this.matcher.matches(this.actual);
/* 143:    */    }
/* 144:    */  }
/* 145:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.temporal.Conditions
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */