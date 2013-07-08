/*  1:   */package com.eaio.uuid;
/*  2:   */
/*  3:   */import org.omg.CORBA.TypeCode;
/*  4:   */import org.omg.CORBA.portable.InputStream;
/*  5:   */import org.omg.CORBA.portable.OutputStream;
/*  6:   */import org.omg.CORBA.portable.Streamable;
/*  7:   */
/* 13:   */public final class UUIDHolder
/* 14:   */  implements Streamable
/* 15:   */{
/* 16:16 */  public UUID value = null;
/* 17:   */  
/* 19:   */  public UUIDHolder() {}
/* 20:   */  
/* 22:   */  public UUIDHolder(UUID initialValue)
/* 23:   */  {
/* 24:24 */    this.value = initialValue;
/* 25:   */  }
/* 26:   */  
/* 27:   */  public void _read(InputStream i)
/* 28:   */  {
/* 29:29 */    this.value = UUIDHelper.read(i);
/* 30:   */  }
/* 31:   */  
/* 32:   */  public void _write(OutputStream o)
/* 33:   */  {
/* 34:34 */    UUIDHelper.write(o, this.value);
/* 35:   */  }
/* 36:   */  
/* 37:   */  public TypeCode _type()
/* 38:   */  {
/* 39:39 */    return UUIDHelper.type();
/* 40:   */  }
/* 41:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.eaio.uuid.UUIDHolder
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */