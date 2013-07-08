/*  1:   */package it.unimi.dsi.fastutil.objects;
/*  2:   */
/* 54:   */public abstract class AbstractObjectIterator<K>
/* 55:   */  implements ObjectIterator<K>
/* 56:   */{
/* 57:   */  public void remove()
/* 58:   */  {
/* 59:59 */    throw new UnsupportedOperationException();
/* 60:   */  }
/* 61:   */  
/* 62:   */  public int skip(int n) {
/* 63:63 */    int i = n;
/* 64:64 */    while ((i-- != 0) && (hasNext())) next();
/* 65:65 */    return n - i - 1;
/* 66:   */  }
/* 67:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractObjectIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */