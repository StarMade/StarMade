/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */
/*  63:    */public abstract class AbstractReference2CharFunction<K>
/*  64:    */  implements Reference2CharFunction<K>, Serializable
/*  65:    */{
/*  66:    */  public static final long serialVersionUID = -4940583368468432370L;
/*  67:    */  protected char defRetValue;
/*  68:    */  
/*  69:    */  public void defaultReturnValue(char rv)
/*  70:    */  {
/*  71: 71 */    this.defRetValue = rv;
/*  72:    */  }
/*  73:    */  
/*  74: 74 */  public char defaultReturnValue() { return this.defRetValue; }
/*  75:    */  
/*  76:    */  public char put(K key, char value) {
/*  77: 77 */    throw new UnsupportedOperationException();
/*  78:    */  }
/*  79:    */  
/*  80: 80 */  public char removeChar(Object key) { throw new UnsupportedOperationException(); }
/*  81:    */  
/*  82:    */  public void clear() {
/*  83: 83 */    throw new UnsupportedOperationException();
/*  84:    */  }
/*  85:    */  
/*  89:    */  public Character get(Object ok)
/*  90:    */  {
/*  91: 91 */    Object k = ok;
/*  92: 92 */    return containsKey(k) ? Character.valueOf(getChar(k)) : null;
/*  93:    */  }
/*  94:    */  
/*  98:    */  public Character put(K ok, Character ov)
/*  99:    */  {
/* 100:100 */    K k = ok;
/* 101:101 */    boolean containsKey = containsKey(k);
/* 102:102 */    char v = put(k, ov.charValue());
/* 103:103 */    return containsKey ? Character.valueOf(v) : null;
/* 104:    */  }
/* 105:    */  
/* 109:    */  public Character remove(Object ok)
/* 110:    */  {
/* 111:111 */    Object k = ok;
/* 112:112 */    boolean containsKey = containsKey(k);
/* 113:113 */    char v = removeChar(k);
/* 114:114 */    return containsKey ? Character.valueOf(v) : null;
/* 115:    */  }
/* 116:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractReference2CharFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */