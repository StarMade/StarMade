/*     */ package org.jaxen.util;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.jaxen.JaxenConstants;
/*     */ import org.jaxen.JaxenRuntimeException;
/*     */ import org.jaxen.Navigator;
/*     */ import org.jaxen.UnsupportedAxisException;
/*     */ 
/*     */ public class FollowingAxisIterator
/*     */   implements Iterator
/*     */ {
/*     */   private Object contextNode;
/*     */   private Navigator navigator;
/*     */   private Iterator siblings;
/*     */   private Iterator currentSibling;
/*     */ 
/*     */   public FollowingAxisIterator(Object contextNode, Navigator navigator)
/*     */     throws UnsupportedAxisException
/*     */   {
/*  86 */     this.contextNode = contextNode;
/*  87 */     this.navigator = navigator;
/*  88 */     this.siblings = navigator.getFollowingSiblingAxisIterator(contextNode);
/*  89 */     this.currentSibling = JaxenConstants.EMPTY_ITERATOR;
/*     */   }
/*     */ 
/*     */   private boolean goForward()
/*     */   {
/*  94 */     while (!this.siblings.hasNext())
/*     */     {
/*  96 */       if (!goUp())
/*     */       {
/*  98 */         return false;
/*     */       }
/*     */     }
/*     */ 
/* 102 */     Object nextSibling = this.siblings.next();
/*     */ 
/* 104 */     this.currentSibling = new DescendantOrSelfAxisIterator(nextSibling, this.navigator);
/*     */ 
/* 106 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean goUp()
/*     */   {
/* 111 */     if ((this.contextNode == null) || (this.navigator.isDocument(this.contextNode)))
/*     */     {
/* 115 */       return false;
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/* 120 */       this.contextNode = this.navigator.getParentNode(this.contextNode);
/*     */ 
/* 122 */       if ((this.contextNode != null) && (!this.navigator.isDocument(this.contextNode)))
/*     */       {
/* 126 */         this.siblings = this.navigator.getFollowingSiblingAxisIterator(this.contextNode);
/* 127 */         return true;
/*     */       }
/*     */ 
/* 131 */       return false;
/*     */     }
/*     */     catch (UnsupportedAxisException e)
/*     */     {
/* 136 */       throw new JaxenRuntimeException(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean hasNext()
/*     */   {
/* 150 */     while (!this.currentSibling.hasNext())
/*     */     {
/* 152 */       if (!goForward())
/*     */       {
/* 154 */         return false;
/*     */       }
/*     */     }
/*     */ 
/* 158 */     return true;
/*     */   }
/*     */ 
/*     */   public Object next()
/*     */     throws NoSuchElementException
/*     */   {
/* 172 */     if (!hasNext())
/*     */     {
/* 174 */       throw new NoSuchElementException();
/*     */     }
/*     */ 
/* 177 */     return this.currentSibling.next();
/*     */   }
/*     */ 
/*     */   public void remove()
/*     */     throws UnsupportedOperationException
/*     */   {
/* 187 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.util.FollowingAxisIterator
 * JD-Core Version:    0.6.2
 */