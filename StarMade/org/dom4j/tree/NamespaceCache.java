package org.dom4j.tree;

import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.util.Map;
import org.dom4j.Namespace;

public class NamespaceCache
{
  private static final String CONCURRENTREADERHASHMAP_CLASS = "EDU.oswego.cs.dl.util.concurrent.ConcurrentReaderHashMap";
  protected static Map cache;
  protected static Map noPrefixCache;
  
  public Namespace get(String prefix, String uri)
  {
    Map uriCache = getURICache(uri);
    WeakReference ref = (WeakReference)uriCache.get(prefix);
    Namespace answer = null;
    if (ref != null) {
      answer = (Namespace)ref.get();
    }
    if (answer == null) {
      synchronized (uriCache)
      {
        ref = (WeakReference)uriCache.get(prefix);
        if (ref != null) {
          answer = (Namespace)ref.get();
        }
        if (answer == null)
        {
          answer = createNamespace(prefix, uri);
          uriCache.put(prefix, new WeakReference(answer));
        }
      }
    }
    return answer;
  }
  
  public Namespace get(String uri)
  {
    WeakReference ref = (WeakReference)noPrefixCache.get(uri);
    Namespace answer = null;
    if (ref != null) {
      answer = (Namespace)ref.get();
    }
    if (answer == null) {
      synchronized (noPrefixCache)
      {
        ref = (WeakReference)noPrefixCache.get(uri);
        if (ref != null) {
          answer = (Namespace)ref.get();
        }
        if (answer == null)
        {
          answer = createNamespace("", uri);
          noPrefixCache.put(uri, new WeakReference(answer));
        }
      }
    }
    return answer;
  }
  
  protected Map getURICache(String uri)
  {
    Map answer = (Map)cache.get(uri);
    if (answer == null) {
      synchronized (cache)
      {
        answer = (Map)cache.get(uri);
        if (answer == null)
        {
          answer = new ConcurrentReaderHashMap();
          cache.put(uri, answer);
        }
      }
    }
    return answer;
  }
  
  protected Namespace createNamespace(String prefix, String uri)
  {
    return new Namespace(prefix, uri);
  }
  
  static
  {
    try
    {
      Class clazz = Class.forName("java.util.concurrent.ConcurrentHashMap");
      Constructor construct = clazz.getConstructor(new Class[] { Integer.TYPE, Float.TYPE, Integer.TYPE });
      cache = (Map)construct.newInstance(new Object[] { new Integer(11), new Float(0.75F), new Integer(1) });
      noPrefixCache = (Map)construct.newInstance(new Object[] { new Integer(11), new Float(0.75F), new Integer(1) });
    }
    catch (Throwable clazz)
    {
      try
      {
        Class construct = Class.forName("EDU.oswego.cs.dl.util.concurrent.ConcurrentReaderHashMap");
        cache = (Map)construct.newInstance();
        noPrefixCache = (Map)construct.newInstance();
      }
      catch (Throwable construct)
      {
        cache = new ConcurrentReaderHashMap();
        noPrefixCache = new ConcurrentReaderHashMap();
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.tree.NamespaceCache
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */