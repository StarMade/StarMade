/*  1:   */package org.schema.game.network.objects.remote;
/*  2:   */
/*  3:   */import cC;
/*  4:   */import cH;
/*  5:   */import java.io.DataInputStream;
/*  6:   */import java.io.DataOutputStream;
/*  7:   */import org.schema.schine.network.objects.NetworkObject;
/*  8:   */import org.schema.schine.network.objects.remote.RemoteField;
/*  9:   */import q;
/* 10:   */
/* 14:   */public class RemoteMapEntryAnswer
/* 15:   */  extends RemoteField
/* 16:   */{
/* 17:   */  public RemoteMapEntryAnswer(cH paramcH, NetworkObject paramNetworkObject)
/* 18:   */  {
/* 19:19 */    super(paramcH, paramNetworkObject);
/* 20:   */  }
/* 21:   */  
/* 22:22 */  public RemoteMapEntryAnswer(cH paramcH, boolean paramBoolean) { super(paramcH, paramBoolean); }
/* 23:   */  
/* 25:   */  public int byteLength()
/* 26:   */  {
/* 27:27 */    return 1;
/* 28:   */  }
/* 29:   */  
/* 30:   */  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt) {
/* 31:31 */    ((cH)get()).jdField_a_of_type_Q = new q(paramDataInputStream.readInt(), paramDataInputStream.readInt(), paramDataInputStream.readInt());
/* 32:32 */    ((cH)get()).jdField_a_of_type_Byte = paramDataInputStream.readByte();
/* 33:   */    
/* 34:34 */    ((cH)get()).jdField_a_of_type_ArrayOfCD = cC.a(paramDataInputStream);
/* 35:   */  }
/* 36:   */  
/* 38:   */  public int toByteStream(DataOutputStream paramDataOutputStream)
/* 39:   */  {
/* 40:40 */    paramDataOutputStream.writeInt(((cH)get()).jdField_a_of_type_Q.a);
/* 41:41 */    paramDataOutputStream.writeInt(((cH)get()).jdField_a_of_type_Q.b);
/* 42:42 */    paramDataOutputStream.writeInt(((cH)get()).jdField_a_of_type_Q.c);
/* 43:43 */    paramDataOutputStream.writeByte(((cH)get()).jdField_a_of_type_Byte);
/* 44:   */    
/* 45:45 */    cC.a(paramDataOutputStream, ((cH)get()).jdField_a_of_type_ArrayOfCD);
/* 46:   */    
/* 47:47 */    return byteLength();
/* 48:   */  }
/* 49:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteMapEntryAnswer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */