/*  1:   */package org.schema.game.network.objects.remote;
/*  2:   */
/*  3:   */import java.io.DataInputStream;
/*  4:   */import java.io.DataOutputStream;
/*  5:   */import org.schema.schine.network.objects.NetworkObject;
/*  6:   */import org.schema.schine.network.objects.remote.RemoteField;
/*  7:   */
/*  8:   */public class RemoteControlledFileStream extends RemoteField
/*  9:   */{
/* 10:   */  public RemoteControlledFileStream(sC paramsC, NetworkObject paramNetworkObject)
/* 11:   */  {
/* 12:12 */    super(paramsC, paramNetworkObject);
/* 13:   */  }
/* 14:   */  
/* 15:15 */  public RemoteControlledFileStream(NetworkObject paramNetworkObject) { super(new sC(), paramNetworkObject); }
/* 16:   */  
/* 17:   */  public RemoteControlledFileStream(sC paramsC, boolean paramBoolean) {
/* 18:18 */    super(paramsC, paramBoolean);
/* 19:   */  }
/* 20:   */  
/* 21:21 */  public RemoteControlledFileStream(boolean paramBoolean) { super(new sC(), paramBoolean); }
/* 22:   */  
/* 23:   */  public int byteLength()
/* 24:   */  {
/* 25:25 */    return ((sC)get()).jdField_a_of_type_Short;
/* 26:   */  }
/* 27:   */  
/* 28:   */  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/* 29:   */  {
/* 30:30 */    ((sC)get()).jdField_a_of_type_Boolean = paramDataInputStream.readBoolean();
/* 31:31 */    ((sC)get()).jdField_a_of_type_Short = paramDataInputStream.readShort();
/* 32:32 */    paramDataInputStream.readFully(((sC)get()).jdField_a_of_type_ArrayOfByte, 0, ((sC)get()).jdField_a_of_type_Short);
/* 33:   */  }
/* 34:   */  
/* 35:   */  public int toByteStream(DataOutputStream paramDataOutputStream)
/* 36:   */  {
/* 37:37 */    paramDataOutputStream.writeBoolean(((sC)get()).jdField_a_of_type_Boolean);
/* 38:38 */    paramDataOutputStream.writeShort(((sC)get()).jdField_a_of_type_Short);
/* 39:39 */    paramDataOutputStream.write(((sC)get()).jdField_a_of_type_ArrayOfByte, 0, ((sC)get()).jdField_a_of_type_Short);
/* 40:40 */    return byteLength();
/* 41:   */  }
/* 42:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteControlledFileStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */