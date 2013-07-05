/*     */ package org.dom4j;
/*     */ 
/*     */ import org.dom4j.tree.AbstractNode;
/*     */ import org.dom4j.tree.DefaultNamespace;
/*     */ import org.dom4j.tree.NamespaceCache;
/*     */ 
/*     */ public class Namespace extends AbstractNode
/*     */ {
/*  25 */   protected static final NamespaceCache CACHE = new NamespaceCache();
/*     */ 
/*  28 */   public static final Namespace XML_NAMESPACE = CACHE.get("xml", "http://www.w3.org/XML/1998/namespace");
/*     */ 
/*  32 */   public static final Namespace NO_NAMESPACE = CACHE.get("", "");
/*     */   private String prefix;
/*     */   private String uri;
/*     */   private int hashCode;
/*     */ 
/*     */   public Namespace(String prefix, String uri)
/*     */   {
/*  52 */     this.prefix = (prefix != null ? prefix : "");
/*  53 */     this.uri = (uri != null ? uri : "");
/*     */   }
/*     */ 
/*     */   public static Namespace get(String prefix, String uri)
/*     */   {
/*  68 */     return CACHE.get(prefix, uri);
/*     */   }
/*     */ 
/*     */   public static Namespace get(String uri)
/*     */   {
/*  81 */     return CACHE.get(uri);
/*     */   }
/*     */ 
/*     */   public short getNodeType() {
/*  85 */     return 13;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  95 */     if (this.hashCode == 0) {
/*  96 */       this.hashCode = createHashCode();
/*     */     }
/*     */ 
/*  99 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   protected int createHashCode()
/*     */   {
/* 109 */     int result = this.uri.hashCode() ^ this.prefix.hashCode();
/*     */ 
/* 111 */     if (result == 0) {
/* 112 */       result = 47806;
/*     */     }
/*     */ 
/* 115 */     return result;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 128 */     if (this == object)
/* 129 */       return true;
/* 130 */     if ((object instanceof Namespace)) {
/* 131 */       Namespace that = (Namespace)object;
/*     */ 
/* 134 */       if (hashCode() == that.hashCode()) {
/* 135 */         return (this.uri.equals(that.getURI())) && (this.prefix.equals(that.getPrefix()));
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 140 */     return false;
/*     */   }
/*     */ 
/*     */   public String getText() {
/* 144 */     return this.uri;
/*     */   }
/*     */ 
/*     */   public String getStringValue() {
/* 148 */     return this.uri;
/*     */   }
/*     */ 
/*     */   public String getPrefix()
/*     */   {
/* 157 */     return this.prefix;
/*     */   }
/*     */ 
/*     */   public String getURI()
/*     */   {
/* 166 */     return this.uri;
/*     */   }
/*     */ 
/*     */   public String getXPathNameStep() {
/* 170 */     if ((this.prefix != null) && (!"".equals(this.prefix))) {
/* 171 */       return "namespace::" + this.prefix;
/*     */     }
/*     */ 
/* 174 */     return "namespace::*[name()='']";
/*     */   }
/*     */ 
/*     */   public String getPath(Element context) {
/* 178 */     StringBuffer path = new StringBuffer(10);
/* 179 */     Element parent = getParent();
/*     */ 
/* 181 */     if ((parent != null) && (parent != context)) {
/* 182 */       path.append(parent.getPath(context));
/* 183 */       path.append('/');
/*     */     }
/*     */ 
/* 186 */     path.append(getXPathNameStep());
/*     */ 
/* 188 */     return path.toString();
/*     */   }
/*     */ 
/*     */   public String getUniquePath(Element context) {
/* 192 */     StringBuffer path = new StringBuffer(10);
/* 193 */     Element parent = getParent();
/*     */ 
/* 195 */     if ((parent != null) && (parent != context)) {
/* 196 */       path.append(parent.getUniquePath(context));
/* 197 */       path.append('/');
/*     */     }
/*     */ 
/* 200 */     path.append(getXPathNameStep());
/*     */ 
/* 202 */     return path.toString();
/*     */   }
/*     */ 
/*     */   public String toString() {
/* 206 */     return super.toString() + " [Namespace: prefix " + getPrefix() + " mapped to URI \"" + getURI() + "\"]";
/*     */   }
/*     */ 
/*     */   public String asXML()
/*     */   {
/* 211 */     StringBuffer asxml = new StringBuffer(10);
/* 212 */     String pref = getPrefix();
/*     */ 
/* 214 */     if ((pref != null) && (pref.length() > 0)) {
/* 215 */       asxml.append("xmlns:");
/* 216 */       asxml.append(pref);
/* 217 */       asxml.append("=\"");
/*     */     } else {
/* 219 */       asxml.append("xmlns=\"");
/*     */     }
/*     */ 
/* 222 */     asxml.append(getURI());
/* 223 */     asxml.append("\"");
/*     */ 
/* 225 */     return asxml.toString();
/*     */   }
/*     */ 
/*     */   public void accept(Visitor visitor) {
/* 229 */     visitor.visit(this);
/*     */   }
/*     */ 
/*     */   protected Node createXPathResult(Element parent) {
/* 233 */     return new DefaultNamespace(parent, getPrefix(), getURI());
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.Namespace
 * JD-Core Version:    0.6.2
 */