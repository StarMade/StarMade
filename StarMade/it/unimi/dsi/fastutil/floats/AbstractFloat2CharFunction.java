/*   1:    */package it.unimi.dsi.fastutil.floats;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */
/*  64:    */public abstract class AbstractFloat2CharFunction
/*  65:    */  implements Float2CharFunction, Serializable
/*  66:    */{
/*  67:    */  public static final long serialVersionUID = -4940583368468432370L;
/*  68:    */  protected char defRetValue;
/*  69:    */  
/*  70:    */  public void defaultReturnValue(char rv)
/*  71:    */  {
/*  72: 72 */    this.defRetValue = rv;
/*  73:    */  }
/*  74:    */  
/*  75: 75 */  public char defaultReturnValue() { return this.defRetValue; }
/*  76:    */  
/*  77:    */  public char put(float key, char value) {
/*  78: 78 */    throw new UnsupportedOperationException();
/*  79:    */  }
/*  80:    */  
/*  81: 81 */  public char remove(float key) { throw new UnsupportedOperationException(); }
/*  82:    */  
/*  83:    */  public void clear() {
/*  84: 84 */    throw new UnsupportedOperationException();
/*  85:    */  }
/*  86:    */  
/*  87: 87 */  public boolean containsKey(Object ok) { return containsKey(((Float)ok).floatValue()); }
/*  88:    */  
/*  93:    */  public Character get(Object ok)
/*  94:    */  {
/*  95: 95 */    float k = ((Float)ok).floatValue();
/*  96: 96 */    return containsKey(k) ? Character.valueOf(get(k)) : null;
/*  97:    */  }
/*  98:    */  
/* 102:    */  public Character put(Float ok, Character ov)
/* 103:    */  {
/* 104:104 */    float k = ok.floatValue();
/* 105:105 */    boolean containsKey = containsKey(k);
/* 106:106 */    char v = put(k, ov.charValue());
/* 107:107 */    return containsKey ? Character.valueOf(v) : null;
/* 108:    */  }
/* 109:    */  
/* 113:    */  public Character remove(Object ok)
/* 114:    */  {
/* 115:115 */    float k = ((Float)ok).floatValue();
/* 116:116 */    boolean containsKey = containsKey(k);
/* 117:117 */    char v = remove(k);
/* 118:118 */    return containsKey ? Character.valueOf(v) : null;
/* 119:    */  }
/* 120:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloat2CharFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */