/*     */ package org.dom4j.tree;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.dom4j.DocumentFactory;
/*     */ import org.dom4j.Namespace;
/*     */ import org.dom4j.QName;
/*     */ 
/*     */ public class NamespaceStack
/*     */ {
/*     */   private DocumentFactory documentFactory;
/*  31 */   private ArrayList namespaceStack = new ArrayList();
/*     */ 
/*  34 */   private ArrayList namespaceCacheList = new ArrayList();
/*     */   private Map currentNamespaceCache;
/*  46 */   private Map rootNamespaceCache = new HashMap();
/*     */   private Namespace defaultNamespace;
/*     */ 
/*     */   public NamespaceStack()
/*     */   {
/*  52 */     this.documentFactory = DocumentFactory.getInstance();
/*     */   }
/*     */ 
/*     */   public NamespaceStack(DocumentFactory documentFactory) {
/*  56 */     this.documentFactory = documentFactory;
/*     */   }
/*     */ 
/*     */   public void push(Namespace namespace)
/*     */   {
/*  67 */     this.namespaceStack.add(namespace);
/*  68 */     this.namespaceCacheList.add(null);
/*  69 */     this.currentNamespaceCache = null;
/*     */ 
/*  71 */     String prefix = namespace.getPrefix();
/*     */ 
/*  73 */     if ((prefix == null) || (prefix.length() == 0))
/*  74 */       this.defaultNamespace = namespace;
/*     */   }
/*     */ 
/*     */   public Namespace pop()
/*     */   {
/*  84 */     return remove(this.namespaceStack.size() - 1);
/*     */   }
/*     */ 
/*     */   public int size()
/*     */   {
/*  93 */     return this.namespaceStack.size();
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/* 100 */     this.namespaceStack.clear();
/* 101 */     this.namespaceCacheList.clear();
/* 102 */     this.rootNamespaceCache.clear();
/* 103 */     this.currentNamespaceCache = null;
/*     */   }
/*     */ 
/*     */   public Namespace getNamespace(int index)
/*     */   {
/* 115 */     return (Namespace)this.namespaceStack.get(index);
/*     */   }
/*     */ 
/*     */   public Namespace getNamespaceForPrefix(String prefix)
/*     */   {
/* 128 */     if (prefix == null) {
/* 129 */       prefix = "";
/*     */     }
/*     */ 
/* 132 */     for (int i = this.namespaceStack.size() - 1; i >= 0; i--) {
/* 133 */       Namespace namespace = (Namespace)this.namespaceStack.get(i);
/*     */ 
/* 135 */       if (prefix.equals(namespace.getPrefix())) {
/* 136 */         return namespace;
/*     */       }
/*     */     }
/*     */ 
/* 140 */     return null;
/*     */   }
/*     */ 
/*     */   public String getURI(String prefix)
/*     */   {
/* 152 */     Namespace namespace = getNamespaceForPrefix(prefix);
/*     */ 
/* 154 */     return namespace != null ? namespace.getURI() : null;
/*     */   }
/*     */ 
/*     */   public boolean contains(Namespace namespace)
/*     */   {
/* 166 */     String prefix = namespace.getPrefix();
/* 167 */     Namespace current = null;
/*     */ 
/* 169 */     if ((prefix == null) || (prefix.length() == 0))
/* 170 */       current = getDefaultNamespace();
/*     */     else {
/* 172 */       current = getNamespaceForPrefix(prefix);
/*     */     }
/*     */ 
/* 175 */     if (current == null) {
/* 176 */       return false;
/*     */     }
/*     */ 
/* 179 */     if (current == namespace) {
/* 180 */       return true;
/*     */     }
/*     */ 
/* 183 */     return namespace.getURI().equals(current.getURI());
/*     */   }
/*     */ 
/*     */   public QName getQName(String namespaceURI, String localName, String qualifiedName)
/*     */   {
/* 188 */     if (localName == null)
/* 189 */       localName = qualifiedName;
/* 190 */     else if (qualifiedName == null) {
/* 191 */       qualifiedName = localName;
/*     */     }
/*     */ 
/* 194 */     if (namespaceURI == null) {
/* 195 */       namespaceURI = "";
/*     */     }
/*     */ 
/* 198 */     String prefix = "";
/* 199 */     int index = qualifiedName.indexOf(":");
/*     */ 
/* 201 */     if (index > 0) {
/* 202 */       prefix = qualifiedName.substring(0, index);
/*     */ 
/* 204 */       if (localName.trim().length() == 0)
/* 205 */         localName = qualifiedName.substring(index + 1);
/*     */     }
/* 207 */     else if (localName.trim().length() == 0) {
/* 208 */       localName = qualifiedName;
/*     */     }
/*     */ 
/* 211 */     Namespace namespace = createNamespace(prefix, namespaceURI);
/*     */ 
/* 213 */     return pushQName(localName, qualifiedName, namespace, prefix);
/*     */   }
/*     */ 
/*     */   public QName getAttributeQName(String namespaceURI, String localName, String qualifiedName)
/*     */   {
/* 218 */     if (qualifiedName == null) {
/* 219 */       qualifiedName = localName;
/*     */     }
/*     */ 
/* 222 */     Map map = getNamespaceCache();
/* 223 */     QName answer = (QName)map.get(qualifiedName);
/*     */ 
/* 225 */     if (answer != null) {
/* 226 */       return answer;
/*     */     }
/*     */ 
/* 229 */     if (localName == null) {
/* 230 */       localName = qualifiedName;
/*     */     }
/*     */ 
/* 233 */     if (namespaceURI == null) {
/* 234 */       namespaceURI = "";
/*     */     }
/*     */ 
/* 237 */     Namespace namespace = null;
/* 238 */     String prefix = "";
/* 239 */     int index = qualifiedName.indexOf(":");
/*     */ 
/* 241 */     if (index > 0) {
/* 242 */       prefix = qualifiedName.substring(0, index);
/* 243 */       namespace = createNamespace(prefix, namespaceURI);
/*     */ 
/* 245 */       if (localName.trim().length() == 0)
/* 246 */         localName = qualifiedName.substring(index + 1);
/*     */     }
/*     */     else
/*     */     {
/* 250 */       namespace = Namespace.NO_NAMESPACE;
/*     */ 
/* 252 */       if (localName.trim().length() == 0) {
/* 253 */         localName = qualifiedName;
/*     */       }
/*     */     }
/*     */ 
/* 257 */     answer = pushQName(localName, qualifiedName, namespace, prefix);
/* 258 */     map.put(qualifiedName, answer);
/*     */ 
/* 260 */     return answer;
/*     */   }
/*     */ 
/*     */   public void push(String prefix, String uri)
/*     */   {
/* 272 */     if (uri == null) {
/* 273 */       uri = "";
/*     */     }
/*     */ 
/* 276 */     Namespace namespace = createNamespace(prefix, uri);
/* 277 */     push(namespace);
/*     */   }
/*     */ 
/*     */   public Namespace addNamespace(String prefix, String uri)
/*     */   {
/* 291 */     Namespace namespace = createNamespace(prefix, uri);
/* 292 */     push(namespace);
/*     */ 
/* 294 */     return namespace;
/*     */   }
/*     */ 
/*     */   public Namespace pop(String prefix)
/*     */   {
/* 306 */     if (prefix == null) {
/* 307 */       prefix = "";
/*     */     }
/*     */ 
/* 310 */     Namespace namespace = null;
/*     */ 
/* 312 */     for (int i = this.namespaceStack.size() - 1; i >= 0; i--) {
/* 313 */       Namespace ns = (Namespace)this.namespaceStack.get(i);
/*     */ 
/* 315 */       if (prefix.equals(ns.getPrefix())) {
/* 316 */         remove(i);
/* 317 */         namespace = ns;
/*     */ 
/* 319 */         break;
/*     */       }
/*     */     }
/*     */ 
/* 323 */     if (namespace == null) {
/* 324 */       System.out.println("Warning: missing namespace prefix ignored: " + prefix);
/*     */     }
/*     */ 
/* 328 */     return namespace;
/*     */   }
/*     */ 
/*     */   public String toString() {
/* 332 */     return super.toString() + " Stack: " + this.namespaceStack.toString();
/*     */   }
/*     */ 
/*     */   public DocumentFactory getDocumentFactory() {
/* 336 */     return this.documentFactory;
/*     */   }
/*     */ 
/*     */   public void setDocumentFactory(DocumentFactory documentFactory) {
/* 340 */     this.documentFactory = documentFactory;
/*     */   }
/*     */ 
/*     */   public Namespace getDefaultNamespace() {
/* 344 */     if (this.defaultNamespace == null) {
/* 345 */       this.defaultNamespace = findDefaultNamespace();
/*     */     }
/*     */ 
/* 348 */     return this.defaultNamespace;
/*     */   }
/*     */ 
/*     */   protected QName pushQName(String localName, String qualifiedName, Namespace namespace, String prefix)
/*     */   {
/* 370 */     if ((prefix == null) || (prefix.length() == 0)) {
/* 371 */       this.defaultNamespace = null;
/*     */     }
/*     */ 
/* 374 */     return createQName(localName, qualifiedName, namespace);
/*     */   }
/*     */ 
/*     */   protected QName createQName(String localName, String qualifiedName, Namespace namespace)
/*     */   {
/* 392 */     return this.documentFactory.createQName(localName, namespace);
/*     */   }
/*     */ 
/*     */   protected Namespace createNamespace(String prefix, String namespaceURI)
/*     */   {
/* 407 */     return this.documentFactory.createNamespace(prefix, namespaceURI);
/*     */   }
/*     */ 
/*     */   protected Namespace findDefaultNamespace()
/*     */   {
/* 417 */     for (int i = this.namespaceStack.size() - 1; i >= 0; i--) {
/* 418 */       Namespace namespace = (Namespace)this.namespaceStack.get(i);
/*     */ 
/* 420 */       if (namespace != null) {
/* 421 */         String prefix = namespace.getPrefix();
/*     */ 
/* 423 */         if ((prefix == null) || (namespace.getPrefix().length() == 0)) {
/* 424 */           return namespace;
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 429 */     return null;
/*     */   }
/*     */ 
/*     */   protected Namespace remove(int index)
/*     */   {
/* 441 */     Namespace namespace = (Namespace)this.namespaceStack.remove(index);
/* 442 */     this.namespaceCacheList.remove(index);
/* 443 */     this.defaultNamespace = null;
/* 444 */     this.currentNamespaceCache = null;
/*     */ 
/* 446 */     return namespace;
/*     */   }
/*     */ 
/*     */   protected Map getNamespaceCache() {
/* 450 */     if (this.currentNamespaceCache == null) {
/* 451 */       int index = this.namespaceStack.size() - 1;
/*     */ 
/* 453 */       if (index < 0) {
/* 454 */         this.currentNamespaceCache = this.rootNamespaceCache;
/*     */       } else {
/* 456 */         this.currentNamespaceCache = ((Map)this.namespaceCacheList.get(index));
/*     */ 
/* 458 */         if (this.currentNamespaceCache == null) {
/* 459 */           this.currentNamespaceCache = new HashMap();
/* 460 */           this.namespaceCacheList.set(index, this.currentNamespaceCache);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 465 */     return this.currentNamespaceCache;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.NamespaceStack
 * JD-Core Version:    0.6.2
 */