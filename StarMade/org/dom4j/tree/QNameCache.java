/*     */ package org.dom4j.tree;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.WeakHashMap;
/*     */ import org.dom4j.DocumentFactory;
/*     */ import org.dom4j.Namespace;
/*     */ import org.dom4j.QName;
/*     */ 
/*     */ public class QNameCache
/*     */ {
/*  36 */   protected Map noNamespaceCache = Collections.synchronizedMap(new WeakHashMap());
/*     */ 
/*  43 */   protected Map namespaceCache = Collections.synchronizedMap(new WeakHashMap());
/*     */   private DocumentFactory documentFactory;
/*     */ 
/*     */   public QNameCache()
/*     */   {
/*     */   }
/*     */ 
/*     */   public QNameCache(DocumentFactory documentFactory)
/*     */   {
/*  56 */     this.documentFactory = documentFactory;
/*     */   }
/*     */ 
/*     */   public List getQNames()
/*     */   {
/*  65 */     List answer = new ArrayList();
/*  66 */     answer.addAll(this.noNamespaceCache.values());
/*     */ 
/*  68 */     for (Iterator it = this.namespaceCache.values().iterator(); it.hasNext(); ) {
/*  69 */       Map map = (Map)it.next();
/*  70 */       answer.addAll(map.values());
/*     */     }
/*     */ 
/*  73 */     return answer;
/*     */   }
/*     */ 
/*     */   public QName get(String name)
/*     */   {
/*  85 */     QName answer = null;
/*     */ 
/*  87 */     if (name != null)
/*  88 */       answer = (QName)this.noNamespaceCache.get(name);
/*     */     else {
/*  90 */       name = "";
/*     */     }
/*     */ 
/*  93 */     if (answer == null) {
/*  94 */       answer = createQName(name);
/*  95 */       answer.setDocumentFactory(this.documentFactory);
/*  96 */       this.noNamespaceCache.put(name, answer);
/*     */     }
/*     */ 
/*  99 */     return answer;
/*     */   }
/*     */ 
/*     */   public QName get(String name, Namespace namespace)
/*     */   {
/* 113 */     Map cache = getNamespaceCache(namespace);
/* 114 */     QName answer = null;
/*     */ 
/* 116 */     if (name != null)
/* 117 */       answer = (QName)cache.get(name);
/*     */     else {
/* 119 */       name = "";
/*     */     }
/*     */ 
/* 122 */     if (answer == null) {
/* 123 */       answer = createQName(name, namespace);
/* 124 */       answer.setDocumentFactory(this.documentFactory);
/* 125 */       cache.put(name, answer);
/*     */     }
/*     */ 
/* 128 */     return answer;
/*     */   }
/*     */ 
/*     */   public QName get(String localName, Namespace namespace, String qName)
/*     */   {
/* 144 */     Map cache = getNamespaceCache(namespace);
/* 145 */     QName answer = null;
/*     */ 
/* 147 */     if (localName != null)
/* 148 */       answer = (QName)cache.get(localName);
/*     */     else {
/* 150 */       localName = "";
/*     */     }
/*     */ 
/* 153 */     if (answer == null) {
/* 154 */       answer = createQName(localName, namespace, qName);
/* 155 */       answer.setDocumentFactory(this.documentFactory);
/* 156 */       cache.put(localName, answer);
/*     */     }
/*     */ 
/* 159 */     return answer;
/*     */   }
/*     */ 
/*     */   public QName get(String qualifiedName, String uri) {
/* 163 */     int index = qualifiedName.indexOf(':');
/*     */ 
/* 165 */     if (index < 0) {
/* 166 */       return get(qualifiedName, Namespace.get(uri));
/*     */     }
/* 168 */     String name = qualifiedName.substring(index + 1);
/* 169 */     String prefix = qualifiedName.substring(0, index);
/*     */ 
/* 171 */     return get(name, Namespace.get(prefix, uri));
/*     */   }
/*     */ 
/*     */   public QName intern(QName qname)
/*     */   {
/* 185 */     return get(qname.getName(), qname.getNamespace(), qname.getQualifiedName());
/*     */   }
/*     */ 
/*     */   protected Map getNamespaceCache(Namespace namespace)
/*     */   {
/* 199 */     if (namespace == Namespace.NO_NAMESPACE) {
/* 200 */       return this.noNamespaceCache;
/*     */     }
/*     */ 
/* 203 */     Map answer = null;
/*     */ 
/* 205 */     if (namespace != null) {
/* 206 */       answer = (Map)this.namespaceCache.get(namespace);
/*     */     }
/*     */ 
/* 209 */     if (answer == null) {
/* 210 */       answer = createMap();
/* 211 */       this.namespaceCache.put(namespace, answer);
/*     */     }
/*     */ 
/* 214 */     return answer;
/*     */   }
/*     */ 
/*     */   protected Map createMap()
/*     */   {
/* 223 */     return Collections.synchronizedMap(new HashMap());
/*     */   }
/*     */ 
/*     */   protected QName createQName(String name)
/*     */   {
/* 236 */     return new QName(name);
/*     */   }
/*     */ 
/*     */   protected QName createQName(String name, Namespace namespace)
/*     */   {
/* 251 */     return new QName(name, namespace);
/*     */   }
/*     */ 
/*     */   protected QName createQName(String name, Namespace namespace, String qualifiedName)
/*     */   {
/* 269 */     return new QName(name, namespace, qualifiedName);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.QNameCache
 * JD-Core Version:    0.6.2
 */