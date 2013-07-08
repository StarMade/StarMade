/*   1:    */package org.apache.commons.lang3.concurrent;
/*   2:    */
/*   3:    */import java.util.Collections;
/*   4:    */import java.util.HashMap;
/*   5:    */import java.util.Map;
/*   6:    */import java.util.Map.Entry;
/*   7:    */import java.util.NoSuchElementException;
/*   8:    */import java.util.Set;
/*   9:    */import java.util.concurrent.ExecutorService;
/*  10:    */
/*  98:    */public class MultiBackgroundInitializer
/*  99:    */  extends BackgroundInitializer<MultiBackgroundInitializerResults>
/* 100:    */{
/* 101:101 */  private final Map<String, BackgroundInitializer<?>> childInitializers = new HashMap();
/* 102:    */  
/* 109:    */  public MultiBackgroundInitializer() {}
/* 110:    */  
/* 117:    */  public MultiBackgroundInitializer(ExecutorService exec)
/* 118:    */  {
/* 119:119 */    super(exec);
/* 120:    */  }
/* 121:    */  
/* 133:    */  public void addInitializer(String name, BackgroundInitializer<?> init)
/* 134:    */  {
/* 135:135 */    if (name == null) {
/* 136:136 */      throw new IllegalArgumentException("Name of child initializer must not be null!");
/* 137:    */    }
/* 138:    */    
/* 139:139 */    if (init == null) {
/* 140:140 */      throw new IllegalArgumentException("Child initializer must not be null!");
/* 141:    */    }
/* 142:    */    
/* 144:144 */    synchronized (this) {
/* 145:145 */      if (isStarted()) {
/* 146:146 */        throw new IllegalStateException("addInitializer() must not be called after start()!");
/* 147:    */      }
/* 148:    */      
/* 149:149 */      this.childInitializers.put(name, init);
/* 150:    */    }
/* 151:    */  }
/* 152:    */  
/* 163:    */  protected int getTaskCount()
/* 164:    */  {
/* 165:165 */    int result = 1;
/* 166:    */    
/* 167:167 */    for (BackgroundInitializer<?> bi : this.childInitializers.values()) {
/* 168:168 */      result += bi.getTaskCount();
/* 169:    */    }
/* 170:    */    
/* 171:171 */    return result;
/* 172:    */  }
/* 173:    */  
/* 179:    */  protected MultiBackgroundInitializerResults initialize()
/* 180:    */    throws Exception
/* 181:    */  {
/* 182:    */    Map<String, BackgroundInitializer<?>> inits;
/* 183:    */    
/* 187:187 */    synchronized (this)
/* 188:    */    {
/* 189:189 */      inits = new HashMap(this.childInitializers);
/* 190:    */    }
/* 191:    */    
/* 194:194 */    ExecutorService exec = getActiveExecutor();
/* 195:195 */    for (BackgroundInitializer<?> bi : inits.values()) {
/* 196:196 */      if (bi.getExternalExecutor() == null)
/* 197:    */      {
/* 198:198 */        bi.setExternalExecutor(exec);
/* 199:    */      }
/* 200:200 */      bi.start();
/* 201:    */    }
/* 202:    */    
/* 204:204 */    Object results = new HashMap();
/* 205:205 */    Map<String, ConcurrentException> excepts = new HashMap();
/* 206:206 */    for (Map.Entry<String, BackgroundInitializer<?>> e : inits.entrySet()) {
/* 207:    */      try {
/* 208:208 */        ((Map)results).put(e.getKey(), ((BackgroundInitializer)e.getValue()).get());
/* 209:    */      } catch (ConcurrentException cex) {
/* 210:210 */        excepts.put(e.getKey(), cex);
/* 211:    */      }
/* 212:    */    }
/* 213:    */    
/* 214:214 */    return new MultiBackgroundInitializerResults(inits, (Map)results, excepts, null);
/* 215:    */  }
/* 216:    */  
/* 224:    */  public static class MultiBackgroundInitializerResults
/* 225:    */  {
/* 226:    */    private final Map<String, BackgroundInitializer<?>> initializers;
/* 227:    */    
/* 233:    */    private final Map<String, Object> resultObjects;
/* 234:    */    
/* 240:    */    private final Map<String, ConcurrentException> exceptions;
/* 241:    */    
/* 248:    */    private MultiBackgroundInitializerResults(Map<String, BackgroundInitializer<?>> inits, Map<String, Object> results, Map<String, ConcurrentException> excepts)
/* 249:    */    {
/* 250:250 */      this.initializers = inits;
/* 251:251 */      this.resultObjects = results;
/* 252:252 */      this.exceptions = excepts;
/* 253:    */    }
/* 254:    */    
/* 262:    */    public BackgroundInitializer<?> getInitializer(String name)
/* 263:    */    {
/* 264:264 */      return checkName(name);
/* 265:    */    }
/* 266:    */    
/* 278:    */    public Object getResultObject(String name)
/* 279:    */    {
/* 280:280 */      checkName(name);
/* 281:281 */      return this.resultObjects.get(name);
/* 282:    */    }
/* 283:    */    
/* 291:    */    public boolean isException(String name)
/* 292:    */    {
/* 293:293 */      checkName(name);
/* 294:294 */      return this.exceptions.containsKey(name);
/* 295:    */    }
/* 296:    */    
/* 306:    */    public ConcurrentException getException(String name)
/* 307:    */    {
/* 308:308 */      checkName(name);
/* 309:309 */      return (ConcurrentException)this.exceptions.get(name);
/* 310:    */    }
/* 311:    */    
/* 318:    */    public Set<String> initializerNames()
/* 319:    */    {
/* 320:320 */      return Collections.unmodifiableSet(this.initializers.keySet());
/* 321:    */    }
/* 322:    */    
/* 328:    */    public boolean isSuccessful()
/* 329:    */    {
/* 330:330 */      return this.exceptions.isEmpty();
/* 331:    */    }
/* 332:    */    
/* 341:    */    private BackgroundInitializer<?> checkName(String name)
/* 342:    */    {
/* 343:343 */      BackgroundInitializer<?> init = (BackgroundInitializer)this.initializers.get(name);
/* 344:344 */      if (init == null) {
/* 345:345 */        throw new NoSuchElementException("No child initializer with name " + name);
/* 346:    */      }
/* 347:    */      
/* 349:349 */      return init;
/* 350:    */    }
/* 351:    */  }
/* 352:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.concurrent.MultiBackgroundInitializer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */