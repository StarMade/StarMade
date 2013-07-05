/*     */ package org.jaxen.util;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ /** @deprecated */
/*     */ public class LinkedIterator
/*     */   implements Iterator
/*     */ {
/*     */   private List iterators;
/*     */   private int cur;
/*     */ 
/*     */   public LinkedIterator()
/*     */   {
/*  67 */     this.iterators = new ArrayList();
/*  68 */     this.cur = 0;
/*     */   }
/*     */ 
/*     */   public void addIterator(Iterator i)
/*     */   {
/*  73 */     this.iterators.add(i);
/*     */   }
/*     */ 
/*     */   public boolean hasNext()
/*     */   {
/*  78 */     boolean has = false;
/*     */ 
/*  80 */     if (this.cur < this.iterators.size())
/*     */     {
/*  82 */       has = ((Iterator)this.iterators.get(this.cur)).hasNext();
/*     */ 
/*  84 */       if ((!has) && (this.cur < this.iterators.size()))
/*     */       {
/*  88 */         this.cur += 1;
/*  89 */         has = hasNext();
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*  94 */       has = false;
/*     */     }
/*     */ 
/*  97 */     return has;
/*     */   }
/*     */ 
/*     */   public Object next()
/*     */   {
/* 102 */     if (!hasNext())
/*     */     {
/* 104 */       throw new NoSuchElementException();
/*     */     }
/*     */ 
/* 107 */     return ((Iterator)this.iterators.get(this.cur)).next();
/*     */   }
/*     */ 
/*     */   public void remove()
/*     */   {
/* 117 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.util.LinkedIterator
 * JD-Core Version:    0.6.2
 */