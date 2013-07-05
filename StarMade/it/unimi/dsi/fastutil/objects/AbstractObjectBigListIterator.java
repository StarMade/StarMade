/*    */ package it.unimi.dsi.fastutil.objects;
/*    */ 
/*    */ public abstract class AbstractObjectBigListIterator<K> extends AbstractObjectBidirectionalIterator<K>
/*    */   implements ObjectBigListIterator<K>
/*    */ {
/*    */   public void set(K k)
/*    */   {
/* 57 */     throw new UnsupportedOperationException();
/*    */   }
/* 59 */   public void add(K k) { throw new UnsupportedOperationException(); }
/*    */ 
/*    */   public long skip(long n)
/*    */   {
/* 63 */     long i = n;
/* 64 */     while ((i-- != 0L) && (hasNext())) next();
/* 65 */     return n - i - 1L;
/*    */   }
/*    */ 
/*    */   public long back(long n)
/*    */   {
/* 71 */     long i = n;
/* 72 */     while ((i-- != 0L) && (hasPrevious())) previous();
/* 73 */     return n - i - 1L;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractObjectBigListIterator
 * JD-Core Version:    0.6.2
 */