/*     */ package org.jaxen.util;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.jaxen.JaxenRuntimeException;
/*     */ import org.jaxen.Navigator;
/*     */ import org.jaxen.UnsupportedAxisException;
/*     */ 
/*     */ public class DescendantAxisIterator
/*     */   implements Iterator
/*     */ {
/*  70 */   private ArrayList stack = new ArrayList();
/*     */   private Iterator children;
/*     */   private Navigator navigator;
/*     */ 
/*     */   public DescendantAxisIterator(Object contextNode, Navigator navigator)
/*     */     throws UnsupportedAxisException
/*     */   {
/*  83 */     this(navigator, navigator.getChildAxisIterator(contextNode));
/*     */   }
/*     */ 
/*     */   public DescendantAxisIterator(Navigator navigator, Iterator iterator)
/*     */   {
/*  89 */     this.navigator = navigator;
/*  90 */     this.children = iterator;
/*     */   }
/*     */ 
/*     */   public boolean hasNext()
/*     */   {
/* 101 */     while (!this.children.hasNext())
/*     */     {
/* 103 */       if (this.stack.isEmpty())
/*     */       {
/* 105 */         return false;
/*     */       }
/* 107 */       this.children = ((Iterator)this.stack.remove(this.stack.size() - 1));
/*     */     }
/* 109 */     return true;
/*     */   }
/*     */ 
/*     */   public Object next()
/*     */   {
/*     */     try
/*     */     {
/* 125 */       if (hasNext())
/*     */       {
/* 127 */         Object node = this.children.next();
/* 128 */         this.stack.add(this.children);
/* 129 */         this.children = this.navigator.getChildAxisIterator(node);
/* 130 */         return node;
/*     */       }
/* 132 */       throw new NoSuchElementException();
/*     */     }
/*     */     catch (UnsupportedAxisException e)
/*     */     {
/* 136 */       throw new JaxenRuntimeException(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void remove()
/*     */   {
/* 147 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.util.DescendantAxisIterator
 * JD-Core Version:    0.6.2
 */