/*  1:   */package it.unimi.dsi.fastutil.objects;
/*  2:   */
/* 54:   */public abstract class AbstractObjectBidirectionalIterator<K>
/* 55:   */  extends AbstractObjectIterator<K>
/* 56:   */  implements ObjectBidirectionalIterator<K>
/* 57:   */{
/* 58:   */  public int back(int n)
/* 59:   */  {
/* 60:60 */    int i = n;
/* 61:61 */    while ((i-- != 0) && (hasPrevious())) previous();
/* 62:62 */    return n - i - 1;
/* 63:   */  }
/* 64:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractObjectBidirectionalIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */