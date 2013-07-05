/*    */ package it.unimi.dsi.fastutil.objects;
/*    */ 
/*    */ public abstract class AbstractObjectIterator<K>
/*    */   implements ObjectIterator<K>
/*    */ {
/*    */   public void remove()
/*    */   {
/* 59 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ 
/*    */   public int skip(int n) {
/* 63 */     int i = n;
/* 64 */     while ((i-- != 0) && (hasNext())) next();
/* 65 */     return n - i - 1;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractObjectIterator
 * JD-Core Version:    0.6.2
 */