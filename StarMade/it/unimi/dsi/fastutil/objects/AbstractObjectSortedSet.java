/*    */ package it.unimi.dsi.fastutil.objects;
/*    */ 
/*    */ public abstract class AbstractObjectSortedSet<K> extends AbstractObjectSet<K>
/*    */   implements ObjectSortedSet<K>
/*    */ {
/*    */   @Deprecated
/*    */   public ObjectBidirectionalIterator<K> objectIterator()
/*    */   {
/* 50 */     return iterator();
/*    */   }
/*    */ 
/*    */   public abstract ObjectBidirectionalIterator<K> iterator();
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractObjectSortedSet
 * JD-Core Version:    0.6.2
 */