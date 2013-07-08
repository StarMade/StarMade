/*   1:    */package org.dom4j.tree;
/*   2:    */
/*   3:    */import java.lang.ref.WeakReference;
/*   4:    */import java.lang.reflect.Constructor;
/*   5:    */import java.util.Map;
/*   6:    */import org.dom4j.Namespace;
/*   7:    */
/*  37:    */public class NamespaceCache
/*  38:    */{
/*  39:    */  private static final String CONCURRENTREADERHASHMAP_CLASS = "EDU.oswego.cs.dl.util.concurrent.ConcurrentReaderHashMap";
/*  40:    */  protected static Map cache;
/*  41:    */  protected static Map noPrefixCache;
/*  42:    */  
/*  43:    */  static
/*  44:    */  {
/*  45:    */    try
/*  46:    */    {
/*  47: 47 */      Class clazz = Class.forName("java.util.concurrent.ConcurrentHashMap");
/*  48:    */      
/*  49: 49 */      Constructor construct = clazz.getConstructor(new Class[] { Integer.TYPE, Float.TYPE, Integer.TYPE });
/*  50:    */      
/*  51: 51 */      cache = (Map)construct.newInstance(new Object[] { new Integer(11), new Float(0.75F), new Integer(1) });
/*  52:    */      
/*  53: 53 */      noPrefixCache = (Map)construct.newInstance(new Object[] { new Integer(11), new Float(0.75F), new Integer(1) });
/*  54:    */    }
/*  55:    */    catch (Throwable t1)
/*  56:    */    {
/*  57:    */      try {
/*  58: 58 */        Class clazz = Class.forName("EDU.oswego.cs.dl.util.concurrent.ConcurrentReaderHashMap");
/*  59: 59 */        cache = (Map)clazz.newInstance();
/*  60: 60 */        noPrefixCache = (Map)clazz.newInstance();
/*  61:    */      }
/*  62:    */      catch (Throwable t2) {
/*  63: 63 */        cache = new ConcurrentReaderHashMap();
/*  64: 64 */        noPrefixCache = new ConcurrentReaderHashMap();
/*  65:    */      }
/*  66:    */    }
/*  67:    */  }
/*  68:    */  
/*  78:    */  public Namespace get(String prefix, String uri)
/*  79:    */  {
/*  80: 80 */    Map uriCache = getURICache(uri);
/*  81: 81 */    WeakReference ref = (WeakReference)uriCache.get(prefix);
/*  82: 82 */    Namespace answer = null;
/*  83:    */    
/*  84: 84 */    if (ref != null) {
/*  85: 85 */      answer = (Namespace)ref.get();
/*  86:    */    }
/*  87:    */    
/*  88: 88 */    if (answer == null) {
/*  89: 89 */      synchronized (uriCache) {
/*  90: 90 */        ref = (WeakReference)uriCache.get(prefix);
/*  91:    */        
/*  92: 92 */        if (ref != null) {
/*  93: 93 */          answer = (Namespace)ref.get();
/*  94:    */        }
/*  95:    */        
/*  96: 96 */        if (answer == null) {
/*  97: 97 */          answer = createNamespace(prefix, uri);
/*  98: 98 */          uriCache.put(prefix, new WeakReference(answer));
/*  99:    */        }
/* 100:    */      }
/* 101:    */    }
/* 102:    */    
/* 103:103 */    return answer;
/* 104:    */  }
/* 105:    */  
/* 113:    */  public Namespace get(String uri)
/* 114:    */  {
/* 115:115 */    WeakReference ref = (WeakReference)noPrefixCache.get(uri);
/* 116:116 */    Namespace answer = null;
/* 117:    */    
/* 118:118 */    if (ref != null) {
/* 119:119 */      answer = (Namespace)ref.get();
/* 120:    */    }
/* 121:    */    
/* 122:122 */    if (answer == null) {
/* 123:123 */      synchronized (noPrefixCache) {
/* 124:124 */        ref = (WeakReference)noPrefixCache.get(uri);
/* 125:    */        
/* 126:126 */        if (ref != null) {
/* 127:127 */          answer = (Namespace)ref.get();
/* 128:    */        }
/* 129:    */        
/* 130:130 */        if (answer == null) {
/* 131:131 */          answer = createNamespace("", uri);
/* 132:132 */          noPrefixCache.put(uri, new WeakReference(answer));
/* 133:    */        }
/* 134:    */      }
/* 135:    */    }
/* 136:    */    
/* 137:137 */    return answer;
/* 138:    */  }
/* 139:    */  
/* 148:    */  protected Map getURICache(String uri)
/* 149:    */  {
/* 150:150 */    Map answer = (Map)cache.get(uri);
/* 151:    */    
/* 152:152 */    if (answer == null) {
/* 153:153 */      synchronized (cache) {
/* 154:154 */        answer = (Map)cache.get(uri);
/* 155:    */        
/* 156:156 */        if (answer == null) {
/* 157:157 */          answer = new ConcurrentReaderHashMap();
/* 158:158 */          cache.put(uri, answer);
/* 159:    */        }
/* 160:    */      }
/* 161:    */    }
/* 162:    */    
/* 163:163 */    return answer;
/* 164:    */  }
/* 165:    */  
/* 175:    */  protected Namespace createNamespace(String prefix, String uri)
/* 176:    */  {
/* 177:177 */    return new Namespace(prefix, uri);
/* 178:    */  }
/* 179:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.NamespaceCache
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */