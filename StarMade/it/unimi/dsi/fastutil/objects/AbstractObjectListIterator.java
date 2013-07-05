/*    */ package it.unimi.dsi.fastutil.objects;
/*    */ 
/*    */ public abstract class AbstractObjectListIterator<K> extends AbstractObjectBidirectionalIterator<K>
/*    */   implements ObjectListIterator<K>
/*    */ {
/*    */   public void set(K k)
/*    */   {
/* 58 */     throw new UnsupportedOperationException();
/*    */   }
/* 60 */   public void add(K k) { throw new UnsupportedOperationException(); }
/*    */ 
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractObjectListIterator
 * JD-Core Version:    0.6.2
 */