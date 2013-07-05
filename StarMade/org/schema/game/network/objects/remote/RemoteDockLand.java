/*    */ package org.schema.game.network.objects.remote;
/*    */ 
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import org.schema.game.network.objects.DockingRequest;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ import org.schema.schine.network.objects.remote.RemoteField;
/*    */ import q;
/*    */ 
/*    */ public class RemoteDockLand extends RemoteField
/*    */ {
/*    */   public RemoteDockLand(DockingRequest paramDockingRequest, NetworkObject paramNetworkObject)
/*    */   {
/* 18 */     super(paramDockingRequest, paramNetworkObject);
/*    */   }
/*    */   public RemoteDockLand(DockingRequest paramDockingRequest, boolean paramBoolean) {
/* 21 */     super(paramDockingRequest, paramBoolean);
/*    */   }
/*    */ 
/*    */   public int byteLength()
/*    */   {
/* 26 */     return 12 + ((DockingRequest)get()).id.length() + 4 + 1;
/*    */   }
/*    */ 
/*    */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/*    */   {
/* 33 */     paramInt = paramDataInputStream.readBoolean();
/* 34 */     String str = paramDataInputStream.readUTF();
/* 35 */     int i = paramDataInputStream.readInt();
/* 36 */     int j = paramDataInputStream.readInt();
/* 37 */     paramDataInputStream = paramDataInputStream.readInt();
/*    */ 
/* 39 */     ((DockingRequest)get()).set(paramInt, str, new q(i, j, paramDataInputStream));
/*    */   }
/*    */ 
/*    */   public int toByteStream(DataOutputStream paramDataOutputStream)
/*    */   {
/* 44 */     paramDataOutputStream.writeBoolean(((DockingRequest)get()).dock);
/* 45 */     paramDataOutputStream.writeUTF(((DockingRequest)get()).id);
/* 46 */     paramDataOutputStream.writeInt(((DockingRequest)get()).pos.a);
/* 47 */     paramDataOutputStream.writeInt(((DockingRequest)get()).pos.b);
/* 48 */     paramDataOutputStream.writeInt(((DockingRequest)get()).pos.c);
/*    */ 
/* 50 */     return byteLength();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteDockLand
 * JD-Core Version:    0.6.2
 */