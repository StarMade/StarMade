/*     */ package org.jaxen.util;
/*     */ 
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ import org.jaxen.Navigator;
/*     */ 
/*     */ /** @deprecated */
/*     */ public abstract class StackedIterator
/*     */   implements Iterator
/*     */ {
/*     */   private LinkedList iteratorStack;
/*     */   private Navigator navigator;
/*     */   private Set created;
/*     */ 
/*     */   public StackedIterator(Object contextNode, Navigator navigator)
/*     */   {
/*  76 */     this.iteratorStack = new LinkedList();
/*  77 */     this.created = new HashSet();
/*     */ 
/*  79 */     init(contextNode, navigator);
/*     */   }
/*     */ 
/*     */   protected StackedIterator()
/*     */   {
/*  85 */     this.iteratorStack = new LinkedList();
/*  86 */     this.created = new HashSet();
/*     */   }
/*     */ 
/*     */   protected void init(Object contextNode, Navigator navigator)
/*     */   {
/*  92 */     this.navigator = navigator;
/*     */   }
/*     */ 
/*     */   protected Iterator internalCreateIterator(Object contextNode)
/*     */   {
/*  99 */     if (this.created.contains(contextNode))
/*     */     {
/* 101 */       return null;
/*     */     }
/*     */ 
/* 104 */     this.created.add(contextNode);
/*     */ 
/* 106 */     return createIterator(contextNode);
/*     */   }
/*     */ 
/*     */   public boolean hasNext()
/*     */   {
/* 111 */     Iterator curIter = currentIterator();
/*     */ 
/* 113 */     if (curIter == null)
/*     */     {
/* 115 */       return false;
/*     */     }
/*     */ 
/* 118 */     return curIter.hasNext();
/*     */   }
/*     */ 
/*     */   public Object next() throws NoSuchElementException
/*     */   {
/* 123 */     if (!hasNext())
/*     */     {
/* 125 */       throw new NoSuchElementException();
/*     */     }
/*     */ 
/* 128 */     Iterator curIter = currentIterator();
/* 129 */     Object object = curIter.next();
/*     */ 
/* 131 */     pushIterator(internalCreateIterator(object));
/*     */ 
/* 133 */     return object;
/*     */   }
/*     */ 
/*     */   public void remove() throws UnsupportedOperationException
/*     */   {
/* 138 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   protected abstract Iterator createIterator(Object paramObject);
/*     */ 
/*     */   protected void pushIterator(Iterator iter)
/*     */   {
/* 145 */     if (iter != null)
/*     */     {
/* 147 */       this.iteratorStack.addFirst(iter);
/*     */     }
/*     */   }
/*     */ 
/*     */   private Iterator currentIterator()
/*     */   {
/* 153 */     while (this.iteratorStack.size() > 0)
/*     */     {
/* 155 */       Iterator curIter = (Iterator)this.iteratorStack.getFirst();
/*     */ 
/* 157 */       if (curIter.hasNext())
/*     */       {
/* 159 */         return curIter;
/*     */       }
/*     */ 
/* 162 */       this.iteratorStack.removeFirst();
/*     */     }
/*     */ 
/* 165 */     return null;
/*     */   }
/*     */ 
/*     */   protected Navigator getNavigator()
/*     */   {
/* 170 */     return this.navigator;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.util.StackedIterator
 * JD-Core Version:    0.6.2
 */