/*   1:    */package it.unimi.dsi.fastutil.chars;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */
/*  63:    */public abstract class AbstractChar2ObjectFunction<V>
/*  64:    */  implements Char2ObjectFunction<V>, Serializable
/*  65:    */{
/*  66:    */  public static final long serialVersionUID = -4940583368468432370L;
/*  67:    */  protected V defRetValue;
/*  68:    */  
/*  69:    */  public void defaultReturnValue(V rv)
/*  70:    */  {
/*  71: 71 */    this.defRetValue = rv;
/*  72:    */  }
/*  73:    */  
/*  74: 74 */  public V defaultReturnValue() { return this.defRetValue; }
/*  75:    */  
/*  76:    */  public V put(char key, V value) {
/*  77: 77 */    throw new UnsupportedOperationException();
/*  78:    */  }
/*  79:    */  
/*  80: 80 */  public V remove(char key) { throw new UnsupportedOperationException(); }
/*  81:    */  
/*  82:    */  public void clear() {
/*  83: 83 */    throw new UnsupportedOperationException();
/*  84:    */  }
/*  85:    */  
/*  86: 86 */  public boolean containsKey(Object ok) { return containsKey(((Character)ok).charValue()); }
/*  87:    */  
/*  92:    */  public V get(Object ok)
/*  93:    */  {
/*  94: 94 */    char k = ((Character)ok).charValue();
/*  95: 95 */    return containsKey(k) ? get(k) : null;
/*  96:    */  }
/*  97:    */  
/* 101:    */  public V put(Character ok, V ov)
/* 102:    */  {
/* 103:103 */    char k = ok.charValue();
/* 104:104 */    boolean containsKey = containsKey(k);
/* 105:105 */    V v = put(k, ov);
/* 106:106 */    return containsKey ? v : null;
/* 107:    */  }
/* 108:    */  
/* 112:    */  public V remove(Object ok)
/* 113:    */  {
/* 114:114 */    char k = ((Character)ok).charValue();
/* 115:115 */    boolean containsKey = containsKey(k);
/* 116:116 */    V v = remove(k);
/* 117:117 */    return containsKey ? v : null;
/* 118:    */  }
/* 119:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractChar2ObjectFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */