/*  1:   */package org.schema.game.network.objects.remote;
/*  2:   */
/*  3:   */import cD;
/*  4:   */import cI;
/*  5:   */import java.io.DataInputStream;
/*  6:   */import java.io.DataOutputStream;
/*  7:   */import org.schema.schine.network.objects.NetworkObject;
/*  8:   */import org.schema.schine.network.objects.remote.RemoteField;
/*  9:   */import q;
/* 10:   */
/* 12:   */public class RemoteMapEntryRequest
/* 13:   */  extends RemoteField
/* 14:   */{
/* 15:   */  private cD[] reply;
/* 16:   */  
/* 17:   */  public RemoteMapEntryRequest(cI paramcI, NetworkObject paramNetworkObject)
/* 18:   */  {
/* 19:19 */    super(paramcI, paramNetworkObject);
/* 20:   */  }
/* 21:   */  
/* 22:22 */  public RemoteMapEntryRequest(cI paramcI, boolean paramBoolean) { super(paramcI, paramBoolean); }
/* 23:   */  
/* 25:   */  public int byteLength()
/* 26:   */  {
/* 27:27 */    return 1;
/* 28:   */  }
/* 29:   */  
/* 30:   */  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt) {
/* 31:31 */    ((cI)get()).jdField_a_of_type_Q = new q(paramDataInputStream.readInt(), paramDataInputStream.readInt(), paramDataInputStream.readInt());
/* 32:32 */    ((cI)get()).jdField_a_of_type_Byte = paramDataInputStream.readByte();
/* 33:   */  }
/* 34:   */  
/* 36:   */  public int toByteStream(DataOutputStream paramDataOutputStream)
/* 37:   */  {
/* 38:38 */    paramDataOutputStream.writeInt(((cI)get()).jdField_a_of_type_Q.a);
/* 39:39 */    paramDataOutputStream.writeInt(((cI)get()).jdField_a_of_type_Q.b);
/* 40:40 */    paramDataOutputStream.writeInt(((cI)get()).jdField_a_of_type_Q.c);
/* 41:41 */    paramDataOutputStream.writeByte(((cI)get()).jdField_a_of_type_Byte);
/* 42:   */    
/* 43:43 */    return byteLength();
/* 44:   */  }
/* 45:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteMapEntryRequest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */