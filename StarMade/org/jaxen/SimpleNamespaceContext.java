/*     */ package org.jaxen;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class SimpleNamespaceContext
/*     */   implements NamespaceContext, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -808928409643497762L;
/*     */   private Map namespaces;
/*     */ 
/*     */   public SimpleNamespaceContext()
/*     */   {
/*  75 */     this.namespaces = new HashMap();
/*     */   }
/*     */ 
/*     */   public SimpleNamespaceContext(Map namespaces)
/*     */   {
/*  90 */     Iterator entries = namespaces.entrySet().iterator();
/*  91 */     while (entries.hasNext()) {
/*  92 */       Map.Entry entry = (Map.Entry)entries.next();
/*  93 */       if ((!(entry.getKey() instanceof String)) || (!(entry.getValue() instanceof String)))
/*     */       {
/*  95 */         throw new ClassCastException("Non-string namespace binding");
/*     */       }
/*     */     }
/*  98 */     this.namespaces = new HashMap(namespaces);
/*     */   }
/*     */ 
/*     */   public void addElementNamespaces(Navigator nav, Object element)
/*     */     throws UnsupportedAxisException
/*     */   {
/* 116 */     Iterator namespaceAxis = nav.getNamespaceAxisIterator(element);
/*     */ 
/* 118 */     while (namespaceAxis.hasNext()) {
/* 119 */       Object namespace = namespaceAxis.next();
/* 120 */       String prefix = nav.getNamespacePrefix(namespace);
/* 121 */       String uri = nav.getNamespaceStringValue(namespace);
/* 122 */       if (translateNamespacePrefixToUri(prefix) == null)
/* 123 */         addNamespace(prefix, uri);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void addNamespace(String prefix, String URI)
/*     */   {
/* 137 */     this.namespaces.put(prefix, URI);
/*     */   }
/*     */ 
/*     */   public String translateNamespacePrefixToUri(String prefix)
/*     */   {
/* 142 */     if (this.namespaces.containsKey(prefix))
/*     */     {
/* 144 */       return (String)this.namespaces.get(prefix);
/*     */     }
/*     */ 
/* 147 */     return null;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.SimpleNamespaceContext
 * JD-Core Version:    0.6.2
 */