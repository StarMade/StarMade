/*  1:   */package org.schema.schine.network.objects.remote;
/*  2:   */
/*  3:   */import org.schema.schine.network.objects.NetworkObject;
/*  4:   */
/*  5:   */public class RemoteVector3b extends RemoteByteArray
/*  6:   */{
/*  7:   */  public RemoteVector3b(NetworkObject paramNetworkObject)
/*  8:   */  {
/*  9: 9 */    super(3, paramNetworkObject);
/* 10:   */  }
/* 11:   */  
/* 12:12 */  public RemoteVector3b(boolean paramBoolean) { super(3, paramBoolean); }
/* 13:   */  
/* 16:16 */  public o getVector() { return getVector(new o()); }
/* 17:   */  
/* 18:   */  public o getVector(o paramo) {
/* 19:19 */    paramo.b(((Byte)((RemoteField[])super.get())[0].get()).byteValue(), ((Byte)((RemoteField[])super.get())[1].get()).byteValue(), ((Byte)((RemoteField[])super.get())[2].get()).byteValue());
/* 20:20 */    return paramo;
/* 21:   */  }
/* 22:   */  
/* 23:23 */  public void set(byte paramByte1, byte paramByte2, byte paramByte3) { super.set(0, Byte.valueOf(paramByte1));
/* 24:24 */    super.set(1, Byte.valueOf(paramByte2));
/* 25:25 */    super.set(2, Byte.valueOf(paramByte3));
/* 26:   */  }
/* 27:   */  
/* 28:   */  public void set(o paramo) {
/* 29:29 */    super.set(0, Byte.valueOf(paramo.a));
/* 30:30 */    super.set(1, Byte.valueOf(paramo.b));
/* 31:31 */    super.set(2, Byte.valueOf(paramo.c));
/* 32:   */  }
/* 33:   */  
/* 34:   */  public String toString()
/* 35:   */  {
/* 36:36 */    return "(r" + getVector(new o()) + ")";
/* 37:   */  }
/* 38:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteVector3b
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */