/*     */ package org.dom4j;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.dom4j.tree.QNameCache;
/*     */ import org.dom4j.util.SingletonStrategy;
/*     */ 
/*     */ public class QName
/*     */   implements Serializable
/*     */ {
/*  29 */   private static SingletonStrategy singleton = null;
/*     */   private String name;
/*     */   private String qualifiedName;
/*     */   private transient Namespace namespace;
/*     */   private int hashCode;
/*     */   private DocumentFactory documentFactory;
/*     */ 
/*     */   public QName(String name)
/*     */   {
/*  69 */     this(name, Namespace.NO_NAMESPACE);
/*     */   }
/*     */ 
/*     */   public QName(String name, Namespace namespace) {
/*  73 */     this.name = (name == null ? "" : name);
/*  74 */     this.namespace = (namespace == null ? Namespace.NO_NAMESPACE : namespace);
/*     */   }
/*     */ 
/*     */   public QName(String name, Namespace namespace, String qualifiedName)
/*     */   {
/*  79 */     this.name = (name == null ? "" : name);
/*  80 */     this.qualifiedName = qualifiedName;
/*  81 */     this.namespace = (namespace == null ? Namespace.NO_NAMESPACE : namespace);
/*     */   }
/*     */ 
/*     */   public static QName get(String name)
/*     */   {
/*  86 */     return getCache().get(name);
/*     */   }
/*     */ 
/*     */   public static QName get(String name, Namespace namespace) {
/*  90 */     return getCache().get(name, namespace);
/*     */   }
/*     */ 
/*     */   public static QName get(String name, String prefix, String uri) {
/*  94 */     if (((prefix == null) || (prefix.length() == 0)) && (uri == null))
/*  95 */       return get(name);
/*  96 */     if ((prefix == null) || (prefix.length() == 0))
/*  97 */       return getCache().get(name, Namespace.get(uri));
/*  98 */     if (uri == null) {
/*  99 */       return get(name);
/*     */     }
/* 101 */     return getCache().get(name, Namespace.get(prefix, uri));
/*     */   }
/*     */ 
/*     */   public static QName get(String qualifiedName, String uri)
/*     */   {
/* 106 */     if (uri == null) {
/* 107 */       return getCache().get(qualifiedName);
/*     */     }
/* 109 */     return getCache().get(qualifiedName, uri);
/*     */   }
/*     */ 
/*     */   public static QName get(String localName, Namespace namespace, String qualifiedName)
/*     */   {
/* 115 */     return getCache().get(localName, namespace, qualifiedName);
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 124 */     return this.name;
/*     */   }
/*     */ 
/*     */   public String getQualifiedName()
/*     */   {
/* 133 */     if (this.qualifiedName == null) {
/* 134 */       String prefix = getNamespacePrefix();
/*     */ 
/* 136 */       if ((prefix != null) && (prefix.length() > 0))
/* 137 */         this.qualifiedName = (prefix + ":" + this.name);
/*     */       else {
/* 139 */         this.qualifiedName = this.name;
/*     */       }
/*     */     }
/*     */ 
/* 143 */     return this.qualifiedName;
/*     */   }
/*     */ 
/*     */   public Namespace getNamespace()
/*     */   {
/* 152 */     return this.namespace;
/*     */   }
/*     */ 
/*     */   public String getNamespacePrefix()
/*     */   {
/* 161 */     if (this.namespace == null) {
/* 162 */       return "";
/*     */     }
/*     */ 
/* 165 */     return this.namespace.getPrefix();
/*     */   }
/*     */ 
/*     */   public String getNamespaceURI()
/*     */   {
/* 174 */     if (this.namespace == null) {
/* 175 */       return "";
/*     */     }
/*     */ 
/* 178 */     return this.namespace.getURI();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 188 */     if (this.hashCode == 0) {
/* 189 */       this.hashCode = (getName().hashCode() ^ getNamespaceURI().hashCode());
/*     */ 
/* 191 */       if (this.hashCode == 0) {
/* 192 */         this.hashCode = 47806;
/*     */       }
/*     */     }
/*     */ 
/* 196 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object) {
/* 200 */     if (this == object)
/* 201 */       return true;
/* 202 */     if ((object instanceof QName)) {
/* 203 */       QName that = (QName)object;
/*     */ 
/* 206 */       if (hashCode() == that.hashCode()) {
/* 207 */         return (getName().equals(that.getName())) && (getNamespaceURI().equals(that.getNamespaceURI()));
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 212 */     return false;
/*     */   }
/*     */ 
/*     */   public String toString() {
/* 216 */     return super.toString() + " [name: " + getName() + " namespace: \"" + getNamespace() + "\"]";
/*     */   }
/*     */ 
/*     */   public DocumentFactory getDocumentFactory()
/*     */   {
/* 226 */     return this.documentFactory;
/*     */   }
/*     */ 
/*     */   public void setDocumentFactory(DocumentFactory documentFactory) {
/* 230 */     this.documentFactory = documentFactory;
/*     */   }
/*     */ 
/*     */   private void writeObject(ObjectOutputStream out)
/*     */     throws IOException
/*     */   {
/* 236 */     out.writeObject(this.namespace.getPrefix());
/* 237 */     out.writeObject(this.namespace.getURI());
/*     */ 
/* 239 */     out.defaultWriteObject();
/*     */   }
/*     */ 
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
/*     */   {
/* 244 */     String prefix = (String)in.readObject();
/* 245 */     String uri = (String)in.readObject();
/*     */ 
/* 247 */     in.defaultReadObject();
/*     */ 
/* 249 */     this.namespace = Namespace.get(prefix, uri);
/*     */   }
/*     */ 
/*     */   private static QNameCache getCache() {
/* 253 */     QNameCache cache = (QNameCache)singleton.instance();
/* 254 */     return cache;
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*     */     try
/*     */     {
/*  33 */       String defaultSingletonClass = "org.dom4j.util.SimpleSingleton";
/*  34 */       Class clazz = null;
/*     */       try {
/*  36 */         String singletonClass = defaultSingletonClass;
/*  37 */         singletonClass = System.getProperty("org.dom4j.QName.singleton.strategy", singletonClass);
/*     */ 
/*  39 */         clazz = Class.forName(singletonClass);
/*     */       } catch (Exception exc1) {
/*     */         try {
/*  42 */           String singletonClass = defaultSingletonClass;
/*  43 */           clazz = Class.forName(singletonClass);
/*     */         } catch (Exception exc2) {
/*     */         }
/*     */       }
/*  47 */       singleton = (SingletonStrategy)clazz.newInstance();
/*  48 */       singleton.setSingletonClassName(QNameCache.class.getName());
/*     */     }
/*     */     catch (Exception exc3)
/*     */     {
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.QName
 * JD-Core Version:    0.6.2
 */