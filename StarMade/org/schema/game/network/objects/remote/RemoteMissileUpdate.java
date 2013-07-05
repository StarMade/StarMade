/*    */ package org.schema.game.network.objects.remote;
/*    */ 
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import lw;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ import org.schema.schine.network.objects.remote.RemoteField;
/*    */ 
/*    */ public class RemoteMissileUpdate extends RemoteField
/*    */ {
/*    */   public RemoteMissileUpdate(lw paramlw, NetworkObject paramNetworkObject)
/*    */   {
/* 14 */     super(paramlw, paramNetworkObject);
/*    */   }
/*    */   public RemoteMissileUpdate(lw paramlw, boolean paramBoolean) {
/* 17 */     super(paramlw, paramBoolean);
/*    */   }
/*    */ 
/*    */   public int byteLength()
/*    */   {
/* 22 */     return 4;
/*    */   }
/*    */ 
/*    */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/*    */   {
/* 27 */     set(lw.a(paramDataInputStream));
/*    */   }
/*    */ 
/*    */   public int toByteStream(DataOutputStream paramDataOutputStream)
/*    */   {
/* 33 */     ((lw)get()).b(paramDataOutputStream);
/*    */ 
/* 35 */     return 1;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteMissileUpdate
 * JD-Core Version:    0.6.2
 */