/*     */ package org.schema.game.network.objects.remote;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import mf;
/*     */ import mh;
/*     */ import mk;
/*     */ import mo;
/*     */ import org.schema.schine.network.objects.remote.RemoteField;
/*     */ import q;
/*     */ 
/*     */ public class RemoteInventory extends RemoteField
/*     */ {
/*     */   private boolean add;
/*     */   private mh holder;
/*     */ 
/*     */   public RemoteInventory(mf parammf, mh parammh, boolean paramBoolean1, boolean paramBoolean2)
/*     */   {
/*  25 */     super(parammf, paramBoolean2);
/*  26 */     setAdd(paramBoolean1);
/*  27 */     this.holder = parammh;
/*     */   }
/*     */ 
/*     */   public int byteLength()
/*     */   {
/*  32 */     return 22;
/*     */   }
/*     */ 
/*     */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/*     */   {
/*  42 */     paramInt = paramDataInputStream.readInt();
/*  43 */     int i = paramDataInputStream.readInt();
/*  44 */     int j = paramDataInputStream.readInt();
/*  45 */     int k = paramDataInputStream.readInt();
/*  46 */     setAdd(paramDataInputStream.readBoolean());
/*     */ 
/*  50 */     if (isAdd())
/*     */     {
/*  52 */       switch (paramInt) { case 1:
/*  53 */         paramInt = new mk(this.holder, new q(i, j, k)); break;
/*     */       default:
/*  54 */         paramInt = new mo(this.holder, new q(i, j, k));
/*     */       }
/*  56 */       paramInt.a(paramDataInputStream);
/*     */     }
/*     */     else {
/*  59 */       switch (paramInt) { case 1:
/*  60 */         paramInt = new mk(this.holder, new q(i, j, k)); break;
/*     */       default:
/*  61 */         paramInt = new mo(this.holder, new q(i, j, k));
/*     */       }
/*     */     }
/*  64 */     set(paramInt);
/*     */   }
/*     */ 
/*     */   public int toByteStream(DataOutputStream paramDataOutputStream)
/*     */   {
/*  73 */     int i = 17;
/*     */ 
/*  76 */     paramDataOutputStream.writeInt(((mf)get()).c());
/*  77 */     paramDataOutputStream.writeInt(((mf)get()).a().a);
/*  78 */     paramDataOutputStream.writeInt(((mf)get()).a().b);
/*  79 */     paramDataOutputStream.writeInt(((mf)get()).a().c);
/*  80 */     paramDataOutputStream.writeBoolean(isAdd());
/*     */ 
/*  82 */     if (isAdd())
/*     */     {
/*  84 */       i = 17 + ((mf)get()).a(paramDataOutputStream);
/*     */     }
/*     */ 
/*  90 */     return i;
/*     */   }
/*     */ 
/*     */   public boolean isAdd()
/*     */   {
/*  97 */     return this.add;
/*     */   }
/*     */ 
/*     */   public void setAdd(boolean paramBoolean)
/*     */   {
/* 104 */     this.add = paramBoolean;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteInventory
 * JD-Core Version:    0.6.2
 */