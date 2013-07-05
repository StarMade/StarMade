/*    */ package org.schema.game.network.objects.remote;
/*    */ 
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import lP;
/*    */ import mc;
/*    */ import org.schema.schine.network.objects.NetworkObject;
/*    */ import org.schema.schine.network.objects.remote.RemoteField;
/*    */ import q;
/*    */ 
/*    */ public class RemoteFaction extends RemoteField
/*    */ {
/*    */   public RemoteFaction(lP paramlP, NetworkObject paramNetworkObject)
/*    */   {
/* 18 */     super(paramlP, paramNetworkObject);
/*    */   }
/*    */   public RemoteFaction(lP paramlP, boolean paramBoolean) {
/* 21 */     super(paramlP, paramBoolean);
/*    */   }
/*    */ 
/*    */   public int byteLength()
/*    */   {
/* 26 */     return ((lP)get()).b().length() + 4 + ((lP)get()).a().length() + 4 + ((lP)get()).c().length() + 4 + 4;
/*    */   }
/*    */ 
/*    */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/*    */   {
/* 34 */     paramInt = paramDataInputStream.readUTF();
/* 35 */     String str1 = paramDataInputStream.readUTF();
/* 36 */     String str2 = paramDataInputStream.readUTF();
/* 37 */     int i = paramDataInputStream.readInt();
/* 38 */     boolean bool1 = paramDataInputStream.readBoolean();
/* 39 */     boolean bool2 = paramDataInputStream.readBoolean();
/* 40 */     boolean bool3 = paramDataInputStream.readBoolean();
/* 41 */     boolean bool4 = paramDataInputStream.readBoolean();
/* 42 */     String str3 = paramDataInputStream.readUTF();
/* 43 */     int j = paramDataInputStream.readInt();
/* 44 */     int k = paramDataInputStream.readInt();
/* 45 */     int m = paramDataInputStream.readInt();
/*    */ 
/* 47 */     ((lP)get()).a().a = paramDataInputStream.readInt();
/* 48 */     for (int n = 0; n < 5; n++) {
/* 49 */       ((lP)get()).a().a()[n] = paramDataInputStream.readLong();
/* 50 */       ((lP)get()).a().a()[n] = paramDataInputStream.readUTF();
/*    */     }
/* 52 */     ((lP)get()).a(paramDataInputStream);
/*    */ 
/* 54 */     ((lP)get()).a().b(j, k, m);
/* 55 */     ((lP)get()).d(str3);
/* 56 */     ((lP)get()).b(paramInt);
/* 57 */     ((lP)get()).a(str1);
/* 58 */     ((lP)get()).c(str2);
/* 59 */     ((lP)get()).a(i);
/* 60 */     ((lP)get()).b(bool2);
/* 61 */     ((lP)get()).a(bool1);
/* 62 */     ((lP)get()).c(bool3);
/* 63 */     ((lP)get()).d(bool4);
/*    */   }
/*    */ 
/*    */   public int toByteStream(DataOutputStream paramDataOutputStream)
/*    */   {
/* 71 */     paramDataOutputStream.writeUTF(((lP)get()).b());
/* 72 */     paramDataOutputStream.writeUTF(((lP)get()).a());
/* 73 */     paramDataOutputStream.writeUTF(((lP)get()).c());
/* 74 */     paramDataOutputStream.writeInt(((lP)get()).a());
/*    */ 
/* 76 */     paramDataOutputStream.writeBoolean(((lP)get()).a());
/* 77 */     paramDataOutputStream.writeBoolean(((lP)get()).b());
/* 78 */     paramDataOutputStream.writeBoolean(((lP)get()).c());
/* 79 */     paramDataOutputStream.writeBoolean(((lP)get()).d());
/* 80 */     paramDataOutputStream.writeUTF(((lP)get()).d());
/* 81 */     paramDataOutputStream.writeInt(((lP)get()).a().a);
/* 82 */     paramDataOutputStream.writeInt(((lP)get()).a().b);
/* 83 */     paramDataOutputStream.writeInt(((lP)get()).a().c);
/*    */ 
/* 85 */     paramDataOutputStream.writeInt(((lP)get()).a().a);
/* 86 */     for (int i = 0; i < 5; i++) {
/* 87 */       paramDataOutputStream.writeLong(((lP)get()).a().a()[i]);
/* 88 */       paramDataOutputStream.writeUTF(((lP)get()).a().a()[i]);
/*    */     }
/* 90 */     ((lP)get()).a(paramDataOutputStream);
/*    */ 
/* 92 */     return byteLength();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteFaction
 * JD-Core Version:    0.6.2
 */