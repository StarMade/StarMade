/*     */ package org.jaxen.pattern;
/*     */ 
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.Navigator;
/*     */ 
/*     */ public class NameTest extends NodeTest
/*     */ {
/*     */   private String name;
/*     */   private short nodeType;
/*     */ 
/*     */   public NameTest(String name, short nodeType)
/*     */   {
/*  68 */     this.name = name;
/*  69 */     this.nodeType = nodeType;
/*     */   }
/*     */ 
/*     */   public boolean matches(Object node, Context context)
/*     */   {
/*  76 */     Navigator navigator = context.getNavigator();
/*  77 */     if (this.nodeType == 1)
/*     */     {
/*  79 */       return (navigator.isElement(node)) && (this.name.equals(navigator.getElementName(node)));
/*     */     }
/*     */ 
/*  82 */     if (this.nodeType == 2)
/*     */     {
/*  84 */       return (navigator.isAttribute(node)) && (this.name.equals(navigator.getAttributeName(node)));
/*     */     }
/*     */ 
/*  89 */     if (navigator.isElement(node))
/*     */     {
/*  91 */       return this.name.equals(navigator.getElementName(node));
/*     */     }
/*     */ 
/*  94 */     if (navigator.isAttribute(node))
/*     */     {
/*  96 */       return this.name.equals(navigator.getAttributeName(node));
/*     */     }
/*     */ 
/*  99 */     return false;
/*     */   }
/*     */ 
/*     */   public double getPriority()
/*     */   {
/* 104 */     return 0.0D;
/*     */   }
/*     */ 
/*     */   public short getMatchType()
/*     */   {
/* 110 */     return this.nodeType;
/*     */   }
/*     */ 
/*     */   public String getText()
/*     */   {
/* 115 */     if (this.nodeType == 2)
/*     */     {
/* 117 */       return "@" + this.name;
/*     */     }
/*     */ 
/* 121 */     return this.name;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 127 */     return super.toString() + "[ name: " + this.name + " type: " + this.nodeType + " ]";
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.pattern.NameTest
 * JD-Core Version:    0.6.2
 */