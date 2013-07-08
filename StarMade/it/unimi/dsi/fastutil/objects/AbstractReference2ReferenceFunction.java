/*  1:   */package it.unimi.dsi.fastutil.objects;
/*  2:   */
/*  3:   */import java.io.Serializable;
/*  4:   */
/* 62:   */public abstract class AbstractReference2ReferenceFunction<K, V>
/* 63:   */  implements Reference2ReferenceFunction<K, V>, Serializable
/* 64:   */{
/* 65:   */  public static final long serialVersionUID = -4940583368468432370L;
/* 66:   */  protected V defRetValue;
/* 67:   */  
/* 68:   */  public void defaultReturnValue(V rv)
/* 69:   */  {
/* 70:70 */    this.defRetValue = rv;
/* 71:   */  }
/* 72:   */  
/* 73:73 */  public V defaultReturnValue() { return this.defRetValue; }
/* 74:   */  
/* 75:   */  public V put(K key, V value) {
/* 76:76 */    throw new UnsupportedOperationException();
/* 77:   */  }
/* 78:   */  
/* 79:79 */  public V remove(Object key) { throw new UnsupportedOperationException(); }
/* 80:   */  
/* 81:   */  public void clear() {
/* 82:82 */    throw new UnsupportedOperationException();
/* 83:   */  }
/* 84:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractReference2ReferenceFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */