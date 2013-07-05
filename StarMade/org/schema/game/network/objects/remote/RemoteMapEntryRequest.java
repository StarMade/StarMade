/*    */ package org.schema.game.network.objects.remote;
/*    */ 
/*    */ import cD;
/*    */ import cI;
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ import org.schema.schine.network.objects.remote.RemoteField;
/*    */ import q;
/*    */ 
/*    */ public class RemoteMapEntryRequest extends RemoteField
/*    */ {
/*    */   private cD[] reply;
/*    */ 
/*    */   public RemoteMapEntryRequest(cI paramcI, NetworkObject paramNetworkObject)
/*    */   {
/* 19 */     super(paramcI, paramNetworkObject);
/*    */   }
/*    */   public RemoteMapEntryRequest(cI paramcI, boolean paramBoolean) {
/* 22 */     super(paramcI, paramBoolean);
/*    */   }
/*    */ 
/*    */   public int byteLength()
/*    */   {
/* 27 */     return 1;
/*    */   }
/*    */ 
/*    */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt) {
/* 31 */     ((cI)get()).jdField_a_of_type_Q = new q(paramDataInputStream.readInt(), paramDataInputStream.readInt(), paramDataInputStream.readInt());
/* 32 */     ((cI)get()).jdField_a_of_type_Byte = paramDataInputStream.readByte();
/*    */   }
/*    */ 
/*    */   public int toByteStream(DataOutputStream paramDataOutputStream)
/*    */   {
/* 38 */     paramDataOutputStream.writeInt(((cI)get()).jdField_a_of_type_Q.a);
/* 39 */     paramDataOutputStream.writeInt(((cI)get()).jdField_a_of_type_Q.b);
/* 40 */     paramDataOutputStream.writeInt(((cI)get()).jdField_a_of_type_Q.c);
/* 41 */     paramDataOutputStream.writeByte(((cI)get()).jdField_a_of_type_Byte);
/*    */ 
/* 43 */     return byteLength();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteMapEntryRequest
 * JD-Core Version:    0.6.2
 */