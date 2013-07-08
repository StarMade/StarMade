/*   1:    */package org.apache.commons.lang3.mutable;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */
/*  44:    */public class MutableObject<T>
/*  45:    */  implements Mutable<T>, Serializable
/*  46:    */{
/*  47:    */  private static final long serialVersionUID = 86241875189L;
/*  48:    */  private T value;
/*  49:    */  
/*  50:    */  public MutableObject() {}
/*  51:    */  
/*  52:    */  public MutableObject(T value)
/*  53:    */  {
/*  54: 54 */    this.value = value;
/*  55:    */  }
/*  56:    */  
/*  62:    */  public T getValue()
/*  63:    */  {
/*  64: 64 */    return this.value;
/*  65:    */  }
/*  66:    */  
/*  71:    */  public void setValue(T value)
/*  72:    */  {
/*  73: 73 */    this.value = value;
/*  74:    */  }
/*  75:    */  
/*  89:    */  public boolean equals(Object obj)
/*  90:    */  {
/*  91: 91 */    if (obj == null) {
/*  92: 92 */      return false;
/*  93:    */    }
/*  94: 94 */    if (this == obj) {
/*  95: 95 */      return true;
/*  96:    */    }
/*  97: 97 */    if (getClass() == obj.getClass()) {
/*  98: 98 */      MutableObject<?> that = (MutableObject)obj;
/*  99: 99 */      return this.value.equals(that.value);
/* 100:    */    }
/* 101:101 */    return false;
/* 102:    */  }
/* 103:    */  
/* 110:    */  public int hashCode()
/* 111:    */  {
/* 112:112 */    return this.value == null ? 0 : this.value.hashCode();
/* 113:    */  }
/* 114:    */  
/* 121:    */  public String toString()
/* 122:    */  {
/* 123:123 */    return this.value == null ? "null" : this.value.toString();
/* 124:    */  }
/* 125:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.mutable.MutableObject
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */