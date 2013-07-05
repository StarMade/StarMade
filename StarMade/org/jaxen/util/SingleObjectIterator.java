/*     */ package org.jaxen.util;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class SingleObjectIterator
/*     */   implements Iterator
/*     */ {
/*     */   private Object object;
/*     */   private boolean seen;
/*     */ 
/*     */   public SingleObjectIterator(Object object)
/*     */   {
/*  72 */     this.object = object;
/*  73 */     this.seen = false;
/*     */   }
/*     */ 
/*     */   public boolean hasNext()
/*     */   {
/*  86 */     return !this.seen;
/*     */   }
/*     */ 
/*     */   public Object next()
/*     */   {
/* 101 */     if (hasNext())
/*     */     {
/* 103 */       this.seen = true;
/* 104 */       return this.object;
/*     */     }
/*     */ 
/* 107 */     throw new NoSuchElementException();
/*     */   }
/*     */ 
/*     */   public void remove()
/*     */   {
/* 117 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.util.SingleObjectIterator
 * JD-Core Version:    0.6.2
 */