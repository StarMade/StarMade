/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */
/*  63:    */public abstract class AbstractReference2DoubleFunction<K>
/*  64:    */  implements Reference2DoubleFunction<K>, Serializable
/*  65:    */{
/*  66:    */  public static final long serialVersionUID = -4940583368468432370L;
/*  67:    */  protected double defRetValue;
/*  68:    */  
/*  69:    */  public void defaultReturnValue(double rv)
/*  70:    */  {
/*  71: 71 */    this.defRetValue = rv;
/*  72:    */  }
/*  73:    */  
/*  74: 74 */  public double defaultReturnValue() { return this.defRetValue; }
/*  75:    */  
/*  76:    */  public double put(K key, double value) {
/*  77: 77 */    throw new UnsupportedOperationException();
/*  78:    */  }
/*  79:    */  
/*  80: 80 */  public double removeDouble(Object key) { throw new UnsupportedOperationException(); }
/*  81:    */  
/*  82:    */  public void clear() {
/*  83: 83 */    throw new UnsupportedOperationException();
/*  84:    */  }
/*  85:    */  
/*  89:    */  public Double get(Object ok)
/*  90:    */  {
/*  91: 91 */    Object k = ok;
/*  92: 92 */    return containsKey(k) ? Double.valueOf(getDouble(k)) : null;
/*  93:    */  }
/*  94:    */  
/*  98:    */  public Double put(K ok, Double ov)
/*  99:    */  {
/* 100:100 */    K k = ok;
/* 101:101 */    boolean containsKey = containsKey(k);
/* 102:102 */    double v = put(k, ov.doubleValue());
/* 103:103 */    return containsKey ? Double.valueOf(v) : null;
/* 104:    */  }
/* 105:    */  
/* 109:    */  public Double remove(Object ok)
/* 110:    */  {
/* 111:111 */    Object k = ok;
/* 112:112 */    boolean containsKey = containsKey(k);
/* 113:113 */    double v = removeDouble(k);
/* 114:114 */    return containsKey ? Double.valueOf(v) : null;
/* 115:    */  }
/* 116:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractReference2DoubleFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */