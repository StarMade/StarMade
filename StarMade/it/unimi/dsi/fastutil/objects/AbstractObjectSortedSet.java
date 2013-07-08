/*  1:   */package it.unimi.dsi.fastutil.objects;
/*  2:   */
/* 43:   */public abstract class AbstractObjectSortedSet<K>
/* 44:   */  extends AbstractObjectSet<K>
/* 45:   */  implements ObjectSortedSet<K>
/* 46:   */{
/* 47:   */  @Deprecated
/* 48:   */  public ObjectBidirectionalIterator<K> objectIterator()
/* 49:   */  {
/* 50:50 */    return iterator();
/* 51:   */  }
/* 52:   */  
/* 53:   */  public abstract ObjectBidirectionalIterator<K> iterator();
/* 54:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractObjectSortedSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */