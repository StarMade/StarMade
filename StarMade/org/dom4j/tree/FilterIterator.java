/*    */ package org.dom4j.tree;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.NoSuchElementException;
/*    */ 
/*    */ /** @deprecated */
/*    */ public abstract class FilterIterator
/*    */   implements Iterator
/*    */ {
/*    */   protected Iterator proxy;
/*    */   private Object next;
/* 29 */   private boolean first = true;
/*    */ 
/*    */   public FilterIterator(Iterator proxy) {
/* 32 */     this.proxy = proxy;
/*    */   }
/*    */ 
/*    */   public boolean hasNext() {
/* 36 */     if (this.first) {
/* 37 */       this.next = findNext();
/* 38 */       this.first = false;
/*    */     }
/*    */ 
/* 41 */     return this.next != null;
/*    */   }
/*    */ 
/*    */   public Object next() throws NoSuchElementException {
/* 45 */     if (!hasNext()) {
/* 46 */       throw new NoSuchElementException();
/*    */     }
/*    */ 
/* 49 */     Object answer = this.next;
/* 50 */     this.next = findNext();
/*    */ 
/* 52 */     return answer;
/*    */   }
/*    */ 
/*    */   public void remove()
/*    */   {
/* 63 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ 
/*    */   protected abstract boolean matches(Object paramObject);
/*    */ 
/*    */   protected Object findNext()
/*    */   {
/* 78 */     if (this.proxy != null) {
/* 79 */       while (this.proxy.hasNext()) {
/* 80 */         Object nextObject = this.proxy.next();
/*    */ 
/* 82 */         if ((nextObject != null) && (matches(nextObject))) {
/* 83 */           return nextObject;
/*    */         }
/*    */       }
/*    */ 
/* 87 */       this.proxy = null;
/*    */     }
/*    */ 
/* 90 */     return null;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.FilterIterator
 * JD-Core Version:    0.6.2
 */