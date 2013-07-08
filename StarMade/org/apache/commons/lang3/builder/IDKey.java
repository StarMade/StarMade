/*  1:   */package org.apache.commons.lang3.builder;
/*  2:   */
/* 14:   */final class IDKey
/* 15:   */{
/* 16:   */  private final Object value;
/* 17:   */  
/* 27:   */  private final int id;
/* 28:   */  
/* 39:   */  public IDKey(Object _value)
/* 40:   */  {
/* 41:41 */    this.id = System.identityHashCode(_value);
/* 42:   */    
/* 45:45 */    this.value = _value;
/* 46:   */  }
/* 47:   */  
/* 52:   */  public int hashCode()
/* 53:   */  {
/* 54:54 */    return this.id;
/* 55:   */  }
/* 56:   */  
/* 62:   */  public boolean equals(Object other)
/* 63:   */  {
/* 64:64 */    if (!(other instanceof IDKey)) {
/* 65:65 */      return false;
/* 66:   */    }
/* 67:67 */    IDKey idKey = (IDKey)other;
/* 68:68 */    if (this.id != idKey.id) {
/* 69:69 */      return false;
/* 70:   */    }
/* 71:   */    
/* 72:72 */    return this.value == idKey.value;
/* 73:   */  }
/* 74:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.builder.IDKey
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */