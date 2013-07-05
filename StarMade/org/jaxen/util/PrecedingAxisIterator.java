/*     */ package org.jaxen.util;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.ListIterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.jaxen.JaxenConstants;
/*     */ import org.jaxen.JaxenRuntimeException;
/*     */ import org.jaxen.Navigator;
/*     */ import org.jaxen.UnsupportedAxisException;
/*     */ 
/*     */ public class PrecedingAxisIterator
/*     */   implements Iterator
/*     */ {
/*     */   private Iterator ancestorOrSelf;
/*     */   private Iterator precedingSibling;
/*     */   private ListIterator childrenOrSelf;
/*     */   private ArrayList stack;
/*     */   private Navigator navigator;
/*     */ 
/*     */   public PrecedingAxisIterator(Object contextNode, Navigator navigator)
/*     */     throws UnsupportedAxisException
/*     */   {
/* 109 */     this.navigator = navigator;
/* 110 */     this.ancestorOrSelf = navigator.getAncestorOrSelfAxisIterator(contextNode);
/* 111 */     this.precedingSibling = JaxenConstants.EMPTY_ITERATOR;
/* 112 */     this.childrenOrSelf = JaxenConstants.EMPTY_LIST_ITERATOR;
/* 113 */     this.stack = new ArrayList();
/*     */   }
/*     */ 
/*     */   public boolean hasNext()
/*     */   {
/*     */     try
/*     */     {
/* 128 */       while (!this.childrenOrSelf.hasPrevious())
/*     */       {
/* 130 */         if (this.stack.isEmpty())
/*     */         {
/* 132 */           while (!this.precedingSibling.hasNext())
/*     */           {
/* 134 */             if (!this.ancestorOrSelf.hasNext())
/*     */             {
/* 136 */               return false;
/*     */             }
/* 138 */             Object contextNode = this.ancestorOrSelf.next();
/* 139 */             this.precedingSibling = new PrecedingSiblingAxisIterator(contextNode, this.navigator);
/*     */           }
/* 141 */           Object node = this.precedingSibling.next();
/* 142 */           this.childrenOrSelf = childrenOrSelf(node);
/*     */         }
/*     */         else
/*     */         {
/* 146 */           this.childrenOrSelf = ((ListIterator)this.stack.remove(this.stack.size() - 1));
/*     */         }
/*     */       }
/* 149 */       return true;
/*     */     }
/*     */     catch (UnsupportedAxisException e)
/*     */     {
/* 153 */       throw new JaxenRuntimeException(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   private ListIterator childrenOrSelf(Object node)
/*     */   {
/*     */     try
/*     */     {
/* 161 */       ArrayList reversed = new ArrayList();
/* 162 */       reversed.add(node);
/* 163 */       Iterator childAxisIterator = this.navigator.getChildAxisIterator(node);
/* 164 */       if (childAxisIterator != null)
/*     */       {
/* 166 */         while (childAxisIterator.hasNext())
/*     */         {
/* 168 */           reversed.add(childAxisIterator.next());
/*     */         }
/*     */       }
/* 171 */       return reversed.listIterator(reversed.size());
/*     */     }
/*     */     catch (UnsupportedAxisException e)
/*     */     {
/* 175 */       throw new JaxenRuntimeException(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Object next()
/*     */     throws NoSuchElementException
/*     */   {
/* 190 */     if (!hasNext())
/*     */     {
/* 192 */       throw new NoSuchElementException();
/*     */     }
/*     */     Object result;
/*     */     while (true) {
/* 196 */       result = this.childrenOrSelf.previous();
/* 197 */       if (!this.childrenOrSelf.hasPrevious()) {
/*     */         break;
/*     */       }
/* 200 */       this.stack.add(this.childrenOrSelf);
/* 201 */       this.childrenOrSelf = childrenOrSelf(result);
/*     */     }
/*     */ 
/* 204 */     return result;
/*     */   }
/*     */ 
/*     */   public void remove()
/*     */     throws UnsupportedOperationException
/*     */   {
/* 216 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.util.PrecedingAxisIterator
 * JD-Core Version:    0.6.2
 */