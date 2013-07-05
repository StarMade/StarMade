/*    */ package it.unimi.dsi.fastutil.objects;
/*    */ 
/*    */ public abstract class AbstractObjectBidirectionalIterator<K> extends AbstractObjectIterator<K>
/*    */   implements ObjectBidirectionalIterator<K>
/*    */ {
/*    */   public int back(int n)
/*    */   {
/* 60 */     int i = n;
/* 61 */     while ((i-- != 0) && (hasPrevious())) previous();
/* 62 */     return n - i - 1;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractObjectBidirectionalIterator
 * JD-Core Version:    0.6.2
 */