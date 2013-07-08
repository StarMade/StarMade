/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */
/*  63:    */public abstract class AbstractObject2ByteFunction<K>
/*  64:    */  implements Object2ByteFunction<K>, Serializable
/*  65:    */{
/*  66:    */  public static final long serialVersionUID = -4940583368468432370L;
/*  67:    */  protected byte defRetValue;
/*  68:    */  
/*  69:    */  public void defaultReturnValue(byte rv)
/*  70:    */  {
/*  71: 71 */    this.defRetValue = rv;
/*  72:    */  }
/*  73:    */  
/*  74: 74 */  public byte defaultReturnValue() { return this.defRetValue; }
/*  75:    */  
/*  76:    */  public byte put(K key, byte value) {
/*  77: 77 */    throw new UnsupportedOperationException();
/*  78:    */  }
/*  79:    */  
/*  80: 80 */  public byte removeByte(Object key) { throw new UnsupportedOperationException(); }
/*  81:    */  
/*  82:    */  public void clear() {
/*  83: 83 */    throw new UnsupportedOperationException();
/*  84:    */  }
/*  85:    */  
/*  89:    */  public Byte get(Object ok)
/*  90:    */  {
/*  91: 91 */    Object k = ok;
/*  92: 92 */    return containsKey(k) ? Byte.valueOf(getByte(k)) : null;
/*  93:    */  }
/*  94:    */  
/*  98:    */  public Byte put(K ok, Byte ov)
/*  99:    */  {
/* 100:100 */    K k = ok;
/* 101:101 */    boolean containsKey = containsKey(k);
/* 102:102 */    byte v = put(k, ov.byteValue());
/* 103:103 */    return containsKey ? Byte.valueOf(v) : null;
/* 104:    */  }
/* 105:    */  
/* 109:    */  public Byte remove(Object ok)
/* 110:    */  {
/* 111:111 */    Object k = ok;
/* 112:112 */    boolean containsKey = containsKey(k);
/* 113:113 */    byte v = removeByte(k);
/* 114:114 */    return containsKey ? Byte.valueOf(v) : null;
/* 115:    */  }
/* 116:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractObject2ByteFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */