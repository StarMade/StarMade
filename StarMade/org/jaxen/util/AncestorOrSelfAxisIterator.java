/*     */ package org.jaxen.util;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.jaxen.JaxenRuntimeException;
/*     */ import org.jaxen.Navigator;
/*     */ import org.jaxen.UnsupportedAxisException;
/*     */ 
/*     */ public class AncestorOrSelfAxisIterator
/*     */   implements Iterator
/*     */ {
/*     */   private Object contextNode;
/*     */   private Navigator navigator;
/*     */ 
/*     */   public AncestorOrSelfAxisIterator(Object contextNode, Navigator navigator)
/*     */   {
/*  84 */     this.contextNode = contextNode;
/*  85 */     this.navigator = navigator;
/*     */   }
/*     */ 
/*     */   public boolean hasNext()
/*     */   {
/*  98 */     return this.contextNode != null;
/*     */   }
/*     */ 
/*     */   public Object next()
/*     */   {
/*     */     try
/*     */     {
/* 114 */       if (hasNext()) {
/* 115 */         Object result = this.contextNode;
/* 116 */         this.contextNode = this.navigator.getParentNode(this.contextNode);
/* 117 */         return result;
/*     */       }
/* 119 */       throw new NoSuchElementException("Exhausted ancestor-or-self axis");
/*     */     }
/*     */     catch (UnsupportedAxisException e)
/*     */     {
/* 123 */       throw new JaxenRuntimeException(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void remove()
/*     */   {
/* 134 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.util.AncestorOrSelfAxisIterator
 * JD-Core Version:    0.6.2
 */