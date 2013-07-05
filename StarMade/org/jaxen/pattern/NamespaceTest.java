/*     */ package org.jaxen.pattern;
/*     */ 
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.ContextSupport;
/*     */ import org.jaxen.Navigator;
/*     */ 
/*     */ public class NamespaceTest extends NodeTest
/*     */ {
/*     */   private String prefix;
/*     */   private short nodeType;
/*     */ 
/*     */   public NamespaceTest(String prefix, short nodeType)
/*     */   {
/*  68 */     if (prefix == null)
/*     */     {
/*  70 */       prefix = "";
/*     */     }
/*  72 */     this.prefix = prefix;
/*  73 */     this.nodeType = nodeType;
/*     */   }
/*     */ 
/*     */   public boolean matches(Object node, Context context)
/*     */   {
/*  80 */     Navigator navigator = context.getNavigator();
/*  81 */     String uri = getURI(node, context);
/*     */ 
/*  83 */     if (this.nodeType == 1)
/*     */     {
/*  85 */       return (navigator.isElement(node)) && (uri.equals(navigator.getElementNamespaceUri(node)));
/*     */     }
/*     */ 
/*  88 */     if (this.nodeType == 2)
/*     */     {
/*  90 */       return (navigator.isAttribute(node)) && (uri.equals(navigator.getAttributeNamespaceUri(node)));
/*     */     }
/*     */ 
/*  93 */     return false;
/*     */   }
/*     */ 
/*     */   public double getPriority()
/*     */   {
/*  98 */     return -0.25D;
/*     */   }
/*     */ 
/*     */   public short getMatchType()
/*     */   {
/* 104 */     return this.nodeType;
/*     */   }
/*     */ 
/*     */   public String getText()
/*     */   {
/* 109 */     return this.prefix + ":";
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 114 */     return super.toString() + "[ prefix: " + this.prefix + " type: " + this.nodeType + " ]";
/*     */   }
/*     */ 
/*     */   protected String getURI(Object node, Context context)
/*     */   {
/* 121 */     String uri = context.getNavigator().translateNamespacePrefixToUri(this.prefix, node);
/* 122 */     if (uri == null)
/*     */     {
/* 124 */       uri = context.getContextSupport().translateNamespacePrefixToUri(this.prefix);
/*     */     }
/* 126 */     if (uri == null)
/*     */     {
/* 128 */       uri = "";
/*     */     }
/* 130 */     return uri;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.pattern.NamespaceTest
 * JD-Core Version:    0.6.2
 */