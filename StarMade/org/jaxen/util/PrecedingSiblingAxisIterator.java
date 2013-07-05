/*     */ package org.jaxen.util;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.jaxen.JaxenConstants;
/*     */ import org.jaxen.Navigator;
/*     */ import org.jaxen.UnsupportedAxisException;
/*     */ 
/*     */ public class PrecedingSiblingAxisIterator
/*     */   implements Iterator
/*     */ {
/*     */   private Object contextNode;
/*     */   private Navigator navigator;
/*     */   private Iterator siblingIter;
/*     */   private Object nextObj;
/*     */ 
/*     */   public PrecedingSiblingAxisIterator(Object contextNode, Navigator navigator)
/*     */     throws UnsupportedAxisException
/*     */   {
/*  88 */     this.contextNode = contextNode;
/*  89 */     this.navigator = navigator;
/*     */ 
/*  91 */     init();
/*  92 */     if (this.siblingIter.hasNext())
/*     */     {
/*  94 */       this.nextObj = this.siblingIter.next();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void init()
/*     */     throws UnsupportedAxisException
/*     */   {
/* 101 */     Object parent = this.navigator.getParentNode(this.contextNode);
/*     */ 
/* 103 */     if (parent != null)
/*     */     {
/* 105 */       Iterator childIter = this.navigator.getChildAxisIterator(parent);
/* 106 */       LinkedList siblings = new LinkedList();
/*     */ 
/* 108 */       while (childIter.hasNext())
/*     */       {
/* 110 */         Object eachChild = childIter.next();
/* 111 */         if (eachChild.equals(this.contextNode))
/*     */         {
/*     */           break;
/*     */         }
/* 115 */         siblings.addFirst(eachChild);
/*     */       }
/*     */ 
/* 118 */       this.siblingIter = siblings.iterator();
/*     */     }
/*     */     else
/*     */     {
/* 122 */       this.siblingIter = JaxenConstants.EMPTY_ITERATOR;
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean hasNext()
/*     */   {
/* 136 */     return this.nextObj != null;
/*     */   }
/*     */ 
/*     */   public Object next()
/*     */     throws NoSuchElementException
/*     */   {
/* 150 */     if (!hasNext())
/*     */     {
/* 152 */       throw new NoSuchElementException();
/*     */     }
/*     */ 
/* 155 */     Object obj = this.nextObj;
/* 156 */     if (this.siblingIter.hasNext())
/*     */     {
/* 158 */       this.nextObj = this.siblingIter.next();
/*     */     }
/*     */     else {
/* 161 */       this.nextObj = null;
/*     */     }
/* 163 */     return obj;
/*     */   }
/*     */ 
/*     */   public void remove()
/*     */     throws UnsupportedOperationException
/*     */   {
/* 173 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.util.PrecedingSiblingAxisIterator
 * JD-Core Version:    0.6.2
 */