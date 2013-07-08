package com.eaio.uuid;

import org.omg.CORBA.TypeCode;
import org.omg.CORBA.portable.InputStream;
import org.omg.CORBA.portable.OutputStream;
import org.omg.CORBA.portable.Streamable;

public final class UUIDHolder
  implements Streamable
{
  public UUID value = null;
  
  public UUIDHolder() {}
  
  public UUIDHolder(UUID initialValue)
  {
    this.value = initialValue;
  }
  
  public void _read(InputStream local_i)
  {
    this.value = UUIDHelper.read(local_i);
  }
  
  public void _write(OutputStream local_o)
  {
    UUIDHelper.write(local_o, this.value);
  }
  
  public TypeCode _type()
  {
    return UUIDHelper.type();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.eaio.uuid.UUIDHolder
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */