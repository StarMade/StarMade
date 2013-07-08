/*   1:    */package it.unimi.dsi.fastutil.doubles;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */
/*  64:    */public abstract class AbstractDouble2BooleanFunction
/*  65:    */  implements Double2BooleanFunction, Serializable
/*  66:    */{
/*  67:    */  public static final long serialVersionUID = -4940583368468432370L;
/*  68:    */  protected boolean defRetValue;
/*  69:    */  
/*  70:    */  public void defaultReturnValue(boolean rv)
/*  71:    */  {
/*  72: 72 */    this.defRetValue = rv;
/*  73:    */  }
/*  74:    */  
/*  75: 75 */  public boolean defaultReturnValue() { return this.defRetValue; }
/*  76:    */  
/*  77:    */  public boolean put(double key, boolean value) {
/*  78: 78 */    throw new UnsupportedOperationException();
/*  79:    */  }
/*  80:    */  
/*  81: 81 */  public boolean remove(double key) { throw new UnsupportedOperationException(); }
/*  82:    */  
/*  83:    */  public void clear() {
/*  84: 84 */    throw new UnsupportedOperationException();
/*  85:    */  }
/*  86:    */  
/*  87: 87 */  public boolean containsKey(Object ok) { return containsKey(((Double)ok).doubleValue()); }
/*  88:    */  
/*  93:    */  public Boolean get(Object ok)
/*  94:    */  {
/*  95: 95 */    double k = ((Double)ok).doubleValue();
/*  96: 96 */    return containsKey(k) ? Boolean.valueOf(get(k)) : null;
/*  97:    */  }
/*  98:    */  
/* 102:    */  public Boolean put(Double ok, Boolean ov)
/* 103:    */  {
/* 104:104 */    double k = ok.doubleValue();
/* 105:105 */    boolean containsKey = containsKey(k);
/* 106:106 */    boolean v = put(k, ov.booleanValue());
/* 107:107 */    return containsKey ? Boolean.valueOf(v) : null;
/* 108:    */  }
/* 109:    */  
/* 113:    */  public Boolean remove(Object ok)
/* 114:    */  {
/* 115:115 */    double k = ((Double)ok).doubleValue();
/* 116:116 */    boolean containsKey = containsKey(k);
/* 117:117 */    boolean v = remove(k);
/* 118:118 */    return containsKey ? Boolean.valueOf(v) : null;
/* 119:    */  }
/* 120:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDouble2BooleanFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */