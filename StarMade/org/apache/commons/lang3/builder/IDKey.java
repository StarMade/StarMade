package org.apache.commons.lang3.builder;

final class IDKey
{
  private final Object value;
  private final int field_2144;
  
  public IDKey(Object _value)
  {
    this.field_2144 = System.identityHashCode(_value);
    this.value = _value;
  }
  
  public int hashCode()
  {
    return this.field_2144;
  }
  
  public boolean equals(Object other)
  {
    if (!(other instanceof IDKey)) {
      return false;
    }
    IDKey idKey = (IDKey)other;
    if (this.field_2144 != idKey.field_2144) {
      return false;
    }
    return this.value == idKey.value;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.lang3.builder.IDKey
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */