/*   1:    */package org.apache.commons.lang3.mutable;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */
/*  47:    */public class MutableBoolean
/*  48:    */  implements Mutable<Boolean>, Serializable, Comparable<MutableBoolean>
/*  49:    */{
/*  50:    */  private static final long serialVersionUID = -4830728138360036487L;
/*  51:    */  private boolean value;
/*  52:    */  
/*  53:    */  public MutableBoolean() {}
/*  54:    */  
/*  55:    */  public MutableBoolean(boolean value)
/*  56:    */  {
/*  57: 57 */    this.value = value;
/*  58:    */  }
/*  59:    */  
/*  66:    */  public MutableBoolean(Boolean value)
/*  67:    */  {
/*  68: 68 */    this.value = value.booleanValue();
/*  69:    */  }
/*  70:    */  
/*  76:    */  public Boolean getValue()
/*  77:    */  {
/*  78: 78 */    return Boolean.valueOf(this.value);
/*  79:    */  }
/*  80:    */  
/*  85:    */  public void setValue(boolean value)
/*  86:    */  {
/*  87: 87 */    this.value = value;
/*  88:    */  }
/*  89:    */  
/*  95:    */  public void setValue(Boolean value)
/*  96:    */  {
/*  97: 97 */    this.value = value.booleanValue();
/*  98:    */  }
/*  99:    */  
/* 106:    */  public boolean isTrue()
/* 107:    */  {
/* 108:108 */    return this.value == true;
/* 109:    */  }
/* 110:    */  
/* 116:    */  public boolean isFalse()
/* 117:    */  {
/* 118:118 */    return !this.value;
/* 119:    */  }
/* 120:    */  
/* 126:    */  public boolean booleanValue()
/* 127:    */  {
/* 128:128 */    return this.value;
/* 129:    */  }
/* 130:    */  
/* 137:    */  public Boolean toBoolean()
/* 138:    */  {
/* 139:139 */    return Boolean.valueOf(booleanValue());
/* 140:    */  }
/* 141:    */  
/* 151:    */  public boolean equals(Object obj)
/* 152:    */  {
/* 153:153 */    if ((obj instanceof MutableBoolean)) {
/* 154:154 */      return this.value == ((MutableBoolean)obj).booleanValue();
/* 155:    */    }
/* 156:156 */    return false;
/* 157:    */  }
/* 158:    */  
/* 164:    */  public int hashCode()
/* 165:    */  {
/* 166:166 */    return this.value ? Boolean.TRUE.hashCode() : Boolean.FALSE.hashCode();
/* 167:    */  }
/* 168:    */  
/* 176:    */  public int compareTo(MutableBoolean other)
/* 177:    */  {
/* 178:178 */    boolean anotherVal = other.value;
/* 179:179 */    return this.value ? 1 : this.value == anotherVal ? 0 : -1;
/* 180:    */  }
/* 181:    */  
/* 188:    */  public String toString()
/* 189:    */  {
/* 190:190 */    return String.valueOf(this.value);
/* 191:    */  }
/* 192:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.mutable.MutableBoolean
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */