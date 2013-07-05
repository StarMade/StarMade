/*    */ package org.schema.game.network.objects.remote;
/*    */ 
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ import org.schema.schine.network.objects.remote.RemoteField;
/*    */ import sC;
/*    */ 
/*    */ public class RemoteControlledFileStream extends RemoteField
/*    */ {
/*    */   public RemoteControlledFileStream(sC paramsC, NetworkObject paramNetworkObject)
/*    */   {
/* 12 */     super(paramsC, paramNetworkObject);
/*    */   }
/*    */   public RemoteControlledFileStream(NetworkObject paramNetworkObject) {
/* 15 */     super(new sC(), paramNetworkObject);
/*    */   }
/*    */   public RemoteControlledFileStream(sC paramsC, boolean paramBoolean) {
/* 18 */     super(paramsC, paramBoolean);
/*    */   }
/*    */   public RemoteControlledFileStream(boolean paramBoolean) {
/* 21 */     super(new sC(), paramBoolean);
/*    */   }
/*    */ 
/*    */   public int byteLength() {
/* 25 */     return ((sC)get()).jdField_a_of_type_Short;
/*    */   }
/*    */ 
/*    */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/*    */   {
/* 30 */     ((sC)get()).jdField_a_of_type_Boolean = paramDataInputStream.readBoolean();
/* 31 */     ((sC)get()).jdField_a_of_type_Short = paramDataInputStream.readShort();
/* 32 */     paramDataInputStream.readFully(((sC)get()).jdField_a_of_type_ArrayOfByte, 0, ((sC)get()).jdField_a_of_type_Short);
/*    */   }
/*    */ 
/*    */   public int toByteStream(DataOutputStream paramDataOutputStream)
/*    */   {
/* 37 */     paramDataOutputStream.writeBoolean(((sC)get()).jdField_a_of_type_Boolean);
/* 38 */     paramDataOutputStream.writeShort(((sC)get()).jdField_a_of_type_Short);
/* 39 */     paramDataOutputStream.write(((sC)get()).jdField_a_of_type_ArrayOfByte, 0, ((sC)get()).jdField_a_of_type_Short);
/* 40 */     return byteLength();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteControlledFileStream
 * JD-Core Version:    0.6.2
 */