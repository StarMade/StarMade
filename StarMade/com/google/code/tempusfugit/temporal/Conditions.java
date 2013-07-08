package com.google.code.tempusfugit.temporal;

import java.util.concurrent.ExecutorService;
import org.hamcrest.Matcher;
import org.junit.Assert;

public final class Conditions
{
  public static Condition not(Condition condition)
  {
    return new NotCondition(condition);
  }
  
  public static Condition shutdown(ExecutorService service)
  {
    return new ExecutorShutdownCondition(service);
  }
  
  public static Condition isAlive(Thread thread)
  {
    return new ThreadAliveCondition(thread);
  }
  
  public static Condition isWaiting(Thread thread)
  {
    return new ThreadWaitingCondition(thread);
  }
  
  public static Condition is(Thread thread, Thread.State state)
  {
    return new ThreadStateCondition(thread, state);
  }
  
  public static void assertThat(Condition condition, Matcher<Boolean> booleanMatcher)
  {
    Assert.assertThat(Boolean.valueOf(condition.isSatisfied()), booleanMatcher);
  }
  
  public static void assertThat(String message, Condition condition, Matcher<Boolean> booleanMatcher)
  {
    Assert.assertThat(message, Boolean.valueOf(condition.isSatisfied()), booleanMatcher);
  }
  
  public static <T> Condition assertion(T actual, Matcher<T> matcher)
  {
    return new MatcherCondition(matcher, actual);
  }
  
  private static class MatcherCondition<T>
    implements Condition
  {
    private final Matcher<T> matcher;
    private final T actual;
    
    public MatcherCondition(Matcher<T> matcher, T actual)
    {
      this.matcher = matcher;
      this.actual = actual;
    }
    
    public boolean isSatisfied()
    {
      return this.matcher.matches(this.actual);
    }
  }
  
  private static class ThreadStateCondition
    implements Condition
  {
    private final Thread thread;
    private final Thread.State state;
    
    public ThreadStateCondition(Thread thread, Thread.State state)
    {
      this.thread = thread;
      this.state = state;
    }
    
    public boolean isSatisfied()
    {
      return this.thread.getState() == this.state;
    }
  }
  
  private static class ThreadWaitingCondition
    implements Condition
  {
    private final Thread thread;
    
    public ThreadWaitingCondition(Thread thread)
    {
      this.thread = thread;
    }
    
    public boolean isSatisfied()
    {
      return (this.thread.getState() == Thread.State.TIMED_WAITING) || (this.thread.getState() == Thread.State.WAITING);
    }
  }
  
  private static class ThreadAliveCondition
    implements Condition
  {
    private final Thread thread;
    
    public ThreadAliveCondition(Thread thread)
    {
      this.thread = thread;
    }
    
    public boolean isSatisfied()
    {
      return this.thread.isAlive();
    }
  }
  
  private static class ExecutorShutdownCondition
    implements Condition
  {
    private final ExecutorService executor;
    
    public ExecutorShutdownCondition(ExecutorService executor)
    {
      this.executor = executor;
    }
    
    public boolean isSatisfied()
    {
      return this.executor.isShutdown();
    }
  }
  
  private static class NotCondition
    implements Condition
  {
    private final Condition condition;
    
    public NotCondition(Condition condition)
    {
      this.condition = condition;
    }
    
    public boolean isSatisfied()
    {
      return !this.condition.isSatisfied();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.google.code.tempusfugit.temporal.Conditions
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */