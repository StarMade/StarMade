/*     */ package org.dom4j.tree;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.util.Map;
/*     */ import org.dom4j.Namespace;
/*     */ 
/*     */ public class NamespaceCache
/*     */ {
/*     */   private static final String CONCURRENTREADERHASHMAP_CLASS = "EDU.oswego.cs.dl.util.concurrent.ConcurrentReaderHashMap";
/*     */   protected static Map cache;
/*     */   protected static Map noPrefixCache;
/*     */ 
/*     */   public Namespace get(String prefix, String uri)
/*     */   {
/*  80 */     Map uriCache = getURICache(uri);
/*  81 */     WeakReference ref = (WeakReference)uriCache.get(prefix);
/*  82 */     Namespace answer = null;
/*     */ 
/*  84 */     if (ref != null) {
/*  85 */       answer = (Namespace)ref.get();
/*     */     }
/*     */ 
/*  88 */     if (answer == null) {
/*  89 */       synchronized (uriCache) {
/*  90 */         ref = (WeakReference)uriCache.get(prefix);
/*     */ 
/*  92 */         if (ref != null) {
/*  93 */           answer = (Namespace)ref.get();
/*     */         }
/*     */ 
/*  96 */         if (answer == null) {
/*  97 */           answer = createNamespace(prefix, uri);
/*  98 */           uriCache.put(prefix, new WeakReference(answer));
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 103 */     return answer;
/*     */   }
/*     */ 
/*     */   public Namespace get(String uri)
/*     */   {
/* 115 */     WeakReference ref = (WeakReference)noPrefixCache.get(uri);
/* 116 */     Namespace answer = null;
/*     */ 
/* 118 */     if (ref != null) {
/* 119 */       answer = (Namespace)ref.get();
/*     */     }
/*     */ 
/* 122 */     if (answer == null) {
/* 123 */       synchronized (noPrefixCache) {
/* 124 */         ref = (WeakReference)noPrefixCache.get(uri);
/*     */ 
/* 126 */         if (ref != null) {
/* 127 */           answer = (Namespace)ref.get();
/*     */         }
/*     */ 
/* 130 */         if (answer == null) {
/* 131 */           answer = createNamespace("", uri);
/* 132 */           noPrefixCache.put(uri, new WeakReference(answer));
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 137 */     return answer;
/*     */   }
/*     */ 
/*     */   protected Map getURICache(String uri)
/*     */   {
/* 150 */     Map answer = (Map)cache.get(uri);
/*     */ 
/* 152 */     if (answer == null) {
/* 153 */       synchronized (cache) {
/* 154 */         answer = (Map)cache.get(uri);
/*     */ 
/* 156 */         if (answer == null) {
/* 157 */           answer = new ConcurrentReaderHashMap();
/* 158 */           cache.put(uri, answer);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 163 */     return answer;
/*     */   }
/*     */ 
/*     */   protected Namespace createNamespace(String prefix, String uri)
/*     */   {
/* 177 */     return new Namespace(prefix, uri);
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*     */     try
/*     */     {
/*  47 */       Class clazz = Class.forName("java.util.concurrent.ConcurrentHashMap");
/*     */ 
/*  49 */       Constructor construct = clazz.getConstructor(new Class[] { Integer.TYPE, Float.TYPE, Integer.TYPE });
/*     */ 
/*  51 */       cache = (Map)construct.newInstance(new Object[] { new Integer(11), new Float(0.75F), new Integer(1) });
/*     */ 
/*  53 */       noPrefixCache = (Map)construct.newInstance(new Object[] { new Integer(11), new Float(0.75F), new Integer(1) });
/*     */     }
/*     */     catch (Throwable t1)
/*     */     {
/*     */       try {
/*  58 */         Class clazz = Class.forName("EDU.oswego.cs.dl.util.concurrent.ConcurrentReaderHashMap");
/*  59 */         cache = (Map)clazz.newInstance();
/*  60 */         noPrefixCache = (Map)clazz.newInstance();
/*     */       }
/*     */       catch (Throwable t2) {
/*  63 */         cache = new ConcurrentReaderHashMap();
/*  64 */         noPrefixCache = new ConcurrentReaderHashMap();
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.NamespaceCache
 * JD-Core Version:    0.6.2
 */