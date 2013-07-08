/*  1:   */package it.unimi.dsi.fastutil.objects;
/*  2:   */
/* 54:   */public abstract class AbstractObjectListIterator<K>
/* 55:   */  extends AbstractObjectBidirectionalIterator<K>
/* 56:   */  implements ObjectListIterator<K>
/* 57:   */{
/* 58:58 */  public void set(K k) { throw new UnsupportedOperationException(); }
/* 59:   */  
/* 60:60 */  public void add(K k) { throw new UnsupportedOperationException(); }
/* 61:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractObjectListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */