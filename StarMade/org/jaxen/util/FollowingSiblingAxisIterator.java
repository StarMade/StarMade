/*     */ package org.jaxen.util;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.jaxen.JaxenConstants;
/*     */ import org.jaxen.Navigator;
/*     */ import org.jaxen.UnsupportedAxisException;
/*     */ 
/*     */ public class FollowingSiblingAxisIterator
/*     */   implements Iterator
/*     */ {
/*     */   private Object contextNode;
/*     */   private Navigator navigator;
/*     */   private Iterator siblingIter;
/*     */ 
/*     */   public FollowingSiblingAxisIterator(Object contextNode, Navigator navigator)
/*     */     throws UnsupportedAxisException
/*     */   {
/*  85 */     this.contextNode = contextNode;
/*  86 */     this.navigator = navigator;
/*  87 */     init();
/*     */   }
/*     */ 
/*     */   private void init() throws UnsupportedAxisException
/*     */   {
/*  92 */     Object parent = this.navigator.getParentNode(this.contextNode);
/*     */ 
/*  94 */     if (parent != null)
/*     */     {
/*  96 */       this.siblingIter = this.navigator.getChildAxisIterator(parent);
/*     */ 
/*  98 */       while (this.siblingIter.hasNext())
/*     */       {
/* 100 */         Object eachChild = this.siblingIter.next();
/* 101 */         if (eachChild.equals(this.contextNode))
/*     */           break;
/*     */       }
/*     */     }
/* 105 */     this.siblingIter = JaxenConstants.EMPTY_ITERATOR;
/*     */   }
/*     */ 
/*     */   public boolean hasNext()
/*     */   {
/* 119 */     return this.siblingIter.hasNext();
/*     */   }
/*     */ 
/*     */   public Object next()
/*     */     throws NoSuchElementException
/*     */   {
/* 133 */     return this.siblingIter.next();
/*     */   }
/*     */ 
/*     */   public void remove()
/*     */     throws UnsupportedOperationException
/*     */   {
/* 143 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.util.FollowingSiblingAxisIterator
 * JD-Core Version:    0.6.2
 */