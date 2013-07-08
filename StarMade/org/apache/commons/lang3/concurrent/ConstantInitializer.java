/*   1:    */package org.apache.commons.lang3.concurrent;
/*   2:    */
/*   3:    */import org.apache.commons.lang3.ObjectUtils;
/*   4:    */
/*  50:    */public class ConstantInitializer<T>
/*  51:    */  implements ConcurrentInitializer<T>
/*  52:    */{
/*  53:    */  private static final String FMT_TO_STRING = "ConstantInitializer@%d [ object = %s ]";
/*  54:    */  private final T object;
/*  55:    */  
/*  56:    */  public ConstantInitializer(T obj)
/*  57:    */  {
/*  58: 58 */    this.object = obj;
/*  59:    */  }
/*  60:    */  
/*  67:    */  public final T getObject()
/*  68:    */  {
/*  69: 69 */    return this.object;
/*  70:    */  }
/*  71:    */  
/*  77:    */  public T get()
/*  78:    */    throws ConcurrentException
/*  79:    */  {
/*  80: 80 */    return getObject();
/*  81:    */  }
/*  82:    */  
/*  89:    */  public int hashCode()
/*  90:    */  {
/*  91: 91 */    return getObject() != null ? getObject().hashCode() : 0;
/*  92:    */  }
/*  93:    */  
/* 103:    */  public boolean equals(Object obj)
/* 104:    */  {
/* 105:105 */    if (this == obj) {
/* 106:106 */      return true;
/* 107:    */    }
/* 108:108 */    if (!(obj instanceof ConstantInitializer)) {
/* 109:109 */      return false;
/* 110:    */    }
/* 111:    */    
/* 112:112 */    ConstantInitializer<?> c = (ConstantInitializer)obj;
/* 113:113 */    return ObjectUtils.equals(getObject(), c.getObject());
/* 114:    */  }
/* 115:    */  
/* 123:    */  public String toString()
/* 124:    */  {
/* 125:125 */    return String.format("ConstantInitializer@%d [ object = %s ]", new Object[] { Integer.valueOf(System.identityHashCode(this)), String.valueOf(getObject()) });
/* 126:    */  }
/* 127:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.concurrent.ConstantInitializer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */