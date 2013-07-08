package org.apache.commons.lang3.concurrent;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.ExecutorService;

public class MultiBackgroundInitializer
  extends BackgroundInitializer<MultiBackgroundInitializerResults>
{
  private final Map<String, BackgroundInitializer<?>> childInitializers = new HashMap();
  
  public MultiBackgroundInitializer() {}
  
  public MultiBackgroundInitializer(ExecutorService exec)
  {
    super(exec);
  }
  
  public void addInitializer(String name, BackgroundInitializer<?> init)
  {
    if (name == null) {
      throw new IllegalArgumentException("Name of child initializer must not be null!");
    }
    if (init == null) {
      throw new IllegalArgumentException("Child initializer must not be null!");
    }
    synchronized (this)
    {
      if (isStarted()) {
        throw new IllegalStateException("addInitializer() must not be called after start()!");
      }
      this.childInitializers.put(name, init);
    }
  }
  
  protected int getTaskCount()
  {
    int result = 1;
    Iterator local_i$ = this.childInitializers.values().iterator();
    while (local_i$.hasNext())
    {
      BackgroundInitializer<?> local_bi = (BackgroundInitializer)local_i$.next();
      result += local_bi.getTaskCount();
    }
    return result;
  }
  
  protected MultiBackgroundInitializerResults initialize()
    throws Exception
  {
    Map<String, BackgroundInitializer<?>> inits;
    synchronized (this)
    {
      inits = new HashMap(this.childInitializers);
    }
    ExecutorService exec = getActiveExecutor();
    Iterator local_i$ = inits.values().iterator();
    while (local_i$.hasNext())
    {
      BackgroundInitializer<?> local_bi = (BackgroundInitializer)local_i$.next();
      if (local_bi.getExternalExecutor() == null) {
        local_bi.setExternalExecutor(exec);
      }
      local_bi.start();
    }
    Object local_i$ = new HashMap();
    Map<String, ConcurrentException> local_bi = new HashMap();
    Iterator local_i$1 = inits.entrySet().iterator();
    while (local_i$1.hasNext())
    {
      Map.Entry<String, BackgroundInitializer<?>> local_e = (Map.Entry)local_i$1.next();
      try
      {
        ((Map)local_i$).put(local_e.getKey(), ((BackgroundInitializer)local_e.getValue()).get());
      }
      catch (ConcurrentException cex)
      {
        local_bi.put(local_e.getKey(), cex);
      }
    }
    return new MultiBackgroundInitializerResults(inits, (Map)local_i$, local_bi, null);
  }
  
  public static class MultiBackgroundInitializerResults
  {
    private final Map<String, BackgroundInitializer<?>> initializers;
    private final Map<String, Object> resultObjects;
    private final Map<String, ConcurrentException> exceptions;
    
    private MultiBackgroundInitializerResults(Map<String, BackgroundInitializer<?>> inits, Map<String, Object> results, Map<String, ConcurrentException> excepts)
    {
      this.initializers = inits;
      this.resultObjects = results;
      this.exceptions = excepts;
    }
    
    public BackgroundInitializer<?> getInitializer(String name)
    {
      return checkName(name);
    }
    
    public Object getResultObject(String name)
    {
      checkName(name);
      return this.resultObjects.get(name);
    }
    
    public boolean isException(String name)
    {
      checkName(name);
      return this.exceptions.containsKey(name);
    }
    
    public ConcurrentException getException(String name)
    {
      checkName(name);
      return (ConcurrentException)this.exceptions.get(name);
    }
    
    public Set<String> initializerNames()
    {
      return Collections.unmodifiableSet(this.initializers.keySet());
    }
    
    public boolean isSuccessful()
    {
      return this.exceptions.isEmpty();
    }
    
    private BackgroundInitializer<?> checkName(String name)
    {
      BackgroundInitializer<?> init = (BackgroundInitializer)this.initializers.get(name);
      if (init == null) {
        throw new NoSuchElementException("No child initializer with name " + name);
      }
      return init;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.lang3.concurrent.MultiBackgroundInitializer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */