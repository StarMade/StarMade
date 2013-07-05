/*    */ package org.schema.game.network.objects.remote;
/*    */ 
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import javax.vecmath.Vector3f;
/*    */ import me;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ import org.schema.schine.network.objects.remote.RemoteField;
/*    */ 
/*    */ public class RemoteItem extends RemoteField
/*    */ {
/*    */   private boolean add;
/*    */ 
/*    */   public RemoteItem(me paramme, Boolean paramBoolean, NetworkObject paramNetworkObject)
/*    */   {
/* 23 */     super(paramme, paramNetworkObject);
/* 24 */     setAdd(paramBoolean.booleanValue());
/*    */   }
/*    */   public RemoteItem(me paramme, Boolean paramBoolean, boolean paramBoolean1) {
/* 27 */     super(paramme, paramBoolean1);
/* 28 */     setAdd(paramBoolean.booleanValue());
/*    */   }
/*    */ 
/*    */   public int byteLength()
/*    */   {
/* 33 */     return 22;
/*    */   }
/*    */ 
/*    */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/*    */   {
/* 41 */     paramInt = paramDataInputStream.readInt();
/* 42 */     setAdd(paramDataInputStream.readBoolean());
/*    */ 
/* 44 */     if (isAdd()) {
/* 45 */       short s = paramDataInputStream.readShort();
/*    */ 
/* 49 */       int i = paramDataInputStream.readInt();
/* 50 */       float f1 = paramDataInputStream.readFloat();
/* 51 */       float f2 = paramDataInputStream.readFloat();
/* 52 */       paramDataInputStream = paramDataInputStream.readFloat();
/* 53 */       ((me)get()).a(paramInt, s, i, new Vector3f(f1, f2, paramDataInputStream));
/* 54 */       return;
/* 55 */     }((me)get()).a(paramInt, (short)-1, 0, null);
/*    */   }
/*    */ 
/*    */   public int toByteStream(DataOutputStream paramDataOutputStream)
/*    */   {
/* 64 */     paramDataOutputStream.writeInt(((me)get()).b());
/*    */ 
/* 66 */     paramDataOutputStream.writeBoolean(isAdd());
/* 67 */     int i = 5;
/* 68 */     if (isAdd()) {
/* 69 */       paramDataOutputStream.writeShort(((me)get()).a());
/* 70 */       paramDataOutputStream.writeInt(((me)get()).a());
/* 71 */       paramDataOutputStream.writeFloat(((me)get()).a().x);
/* 72 */       paramDataOutputStream.writeFloat(((me)get()).a().y);
/* 73 */       paramDataOutputStream.writeFloat(((me)get()).a().z);
/* 74 */       i += 18;
/*    */     }
/*    */ 
/* 80 */     return i;
/*    */   }
/*    */ 
/*    */   public boolean isAdd()
/*    */   {
/* 87 */     return this.add;
/*    */   }
/*    */ 
/*    */   public void setAdd(boolean paramBoolean)
/*    */   {
/* 94 */     this.add = paramBoolean;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteItem
 * JD-Core Version:    0.6.2
 */