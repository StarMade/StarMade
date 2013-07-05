/*    */ package org.schema.game.network.objects.remote;
/*    */ 
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import lf;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ import org.schema.schine.network.objects.remote.RemoteField;
/*    */ import q;
/*    */ 
/*    */ public class RemoteSegmentSignature extends RemoteField
/*    */ {
/* 13 */   static int len = 24;
/*    */ 
/* 15 */   public RemoteSegmentSignature(NetworkObject paramNetworkObject) { super(new lf(), paramNetworkObject); }
/*    */ 
/*    */   public RemoteSegmentSignature(lf paramlf, NetworkObject paramNetworkObject) {
/* 18 */     super(paramlf, paramNetworkObject);
/*    */   }
/*    */   public RemoteSegmentSignature(boolean paramBoolean) {
/* 21 */     super(new lf(), paramBoolean);
/*    */   }
/*    */   public RemoteSegmentSignature(lf paramlf, boolean paramBoolean) {
/* 24 */     super(paramlf, paramBoolean);
/*    */   }
/*    */ 
/*    */   public int byteLength() {
/* 28 */     return len;
/*    */   }
/*    */ 
/*    */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/*    */   {
/* 33 */     long l = paramDataInputStream.readLong();
/* 34 */     paramInt = paramDataInputStream.readInt();
/* 35 */     int i = paramDataInputStream.readInt();
/* 36 */     int j = paramDataInputStream.readInt();
/* 37 */     int k = paramDataInputStream.readInt();
/* 38 */     paramDataInputStream = paramDataInputStream.readBoolean();
/* 39 */     set(new lf(new q(i, j, k), l, paramInt, paramDataInputStream));
/*    */   }
/*    */ 
/*    */   public int toByteStream(DataOutputStream paramDataOutputStream) {
/* 43 */     paramDataOutputStream.writeLong(((lf)get()).jdField_a_of_type_Long);
/* 44 */     paramDataOutputStream.writeInt(((lf)get()).jdField_a_of_type_Int);
/* 45 */     paramDataOutputStream.writeInt(((lf)get()).jdField_a_of_type_Q.jdField_a_of_type_Int);
/* 46 */     paramDataOutputStream.writeInt(((lf)get()).jdField_a_of_type_Q.b);
/* 47 */     paramDataOutputStream.writeInt(((lf)get()).jdField_a_of_type_Q.c);
/* 48 */     paramDataOutputStream.writeBoolean(((lf)get()).jdField_a_of_type_Boolean);
/* 49 */     return byteLength();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteSegmentSignature
 * JD-Core Version:    0.6.2
 */