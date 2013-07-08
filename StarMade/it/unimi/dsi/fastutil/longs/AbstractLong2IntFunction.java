/*   1:    */package it.unimi.dsi.fastutil.longs;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */
/*  64:    */public abstract class AbstractLong2IntFunction
/*  65:    */  implements Long2IntFunction, Serializable
/*  66:    */{
/*  67:    */  public static final long serialVersionUID = -4940583368468432370L;
/*  68:    */  protected int defRetValue;
/*  69:    */  
/*  70:    */  public void defaultReturnValue(int rv)
/*  71:    */  {
/*  72: 72 */    this.defRetValue = rv;
/*  73:    */  }
/*  74:    */  
/*  75: 75 */  public int defaultReturnValue() { return this.defRetValue; }
/*  76:    */  
/*  77:    */  public int put(long key, int value) {
/*  78: 78 */    throw new UnsupportedOperationException();
/*  79:    */  }
/*  80:    */  
/*  81: 81 */  public int remove(long key) { throw new UnsupportedOperationException(); }
/*  82:    */  
/*  83:    */  public void clear() {
/*  84: 84 */    throw new UnsupportedOperationException();
/*  85:    */  }
/*  86:    */  
/*  87: 87 */  public boolean containsKey(Object ok) { return containsKey(((Long)ok).longValue()); }
/*  88:    */  
/*  93:    */  public Integer get(Object ok)
/*  94:    */  {
/*  95: 95 */    long k = ((Long)ok).longValue();
/*  96: 96 */    return containsKey(k) ? Integer.valueOf(get(k)) : null;
/*  97:    */  }
/*  98:    */  
/* 102:    */  public Integer put(Long ok, Integer ov)
/* 103:    */  {
/* 104:104 */    long k = ok.longValue();
/* 105:105 */    boolean containsKey = containsKey(k);
/* 106:106 */    int v = put(k, ov.intValue());
/* 107:107 */    return containsKey ? Integer.valueOf(v) : null;
/* 108:    */  }
/* 109:    */  
/* 113:    */  public Integer remove(Object ok)
/* 114:    */  {
/* 115:115 */    long k = ((Long)ok).longValue();
/* 116:116 */    boolean containsKey = containsKey(k);
/* 117:117 */    int v = remove(k);
/* 118:118 */    return containsKey ? Integer.valueOf(v) : null;
/* 119:    */  }
/* 120:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLong2IntFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */