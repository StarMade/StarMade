/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import org.schema.game.common.data.world.Universe;
/*     */ import org.schema.game.network.objects.NetworkPlayer;
/*     */ import org.schema.game.network.objects.remote.RemoteProximitySystem;
/*     */ 
/*     */ public class mq
/*     */ {
/*  16 */   private q jdField_a_of_type_Q = new q();
/*     */ 
/*  19 */   private long[] jdField_a_of_type_ArrayOfLong = new long[27];
/*  20 */   private byte[] jdField_a_of_type_ArrayOfByte = new byte[27];
/*     */   private lE jdField_a_of_type_LE;
/*     */ 
/*     */   public mq(lE paramlE)
/*     */   {
/*  25 */     this.jdField_a_of_type_LE = paramlE;
/*     */   }
/*     */ 
/*     */   public final q a(int paramInt, q paramq)
/*     */   {
/*  30 */     paramq.b(this.jdField_a_of_type_Q.a - 1, this.jdField_a_of_type_Q.b - 1, this.jdField_a_of_type_Q.c - 1);
/*     */ 
/*  35 */     int i = paramInt / 9;
/*     */ 
/*  37 */     int j = (paramInt - i * 9) / 3;
/*     */ 
/*  39 */     paramInt -= i * 9 + j * 3;
/*     */ 
/*  41 */     paramq.a(paramInt, j, i);
/*     */ 
/*  43 */     return paramq;
/*     */   }
/*     */ 
/*     */   public final q a()
/*     */   {
/*  48 */     return this.jdField_a_of_type_Q;
/*     */   }
/*     */ 
/*     */   public final void a(DataInputStream paramDataInputStream)
/*     */   {
/*  54 */     int i = paramDataInputStream.readInt();
/*  55 */     int j = paramDataInputStream.readInt();
/*  56 */     int k = paramDataInputStream.readInt();
/*     */ 
/*  58 */     this.jdField_a_of_type_Q.b(i, j, k);
/*     */ 
/*  61 */     for (i = 0; i < 27; i++) {
/*  62 */       this.jdField_a_of_type_ArrayOfByte[i] = paramDataInputStream.readByte();
/*  63 */       this.jdField_a_of_type_ArrayOfLong[i] = paramDataInputStream.readLong();
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a(DataOutputStream paramDataOutputStream)
/*     */   {
/*  73 */     paramDataOutputStream.writeInt(this.jdField_a_of_type_Q.a);
/*  74 */     paramDataOutputStream.writeInt(this.jdField_a_of_type_Q.b);
/*  75 */     paramDataOutputStream.writeInt(this.jdField_a_of_type_Q.c);
/*  76 */     for (int i = 0; i < 27; i++) {
/*  77 */       paramDataOutputStream.writeByte(this.jdField_a_of_type_ArrayOfByte[i]);
/*  78 */       paramDataOutputStream.writeLong(this.jdField_a_of_type_ArrayOfLong[i]);
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/*  87 */     vg localvg = (vg)this.jdField_a_of_type_LE.getState();
/*     */ 
/*  89 */     q localq = new q(this.jdField_a_of_type_LE.a());
/*     */ 
/*  91 */     this.jdField_a_of_type_Q.b(localq);
/*     */ 
/*  93 */     localvg.a().updateProximitySectorInformation(localq);
/*     */ 
/*  95 */     int i = 0;
/*     */ 
/*  97 */     localq = new q(localq);
/*     */ 
/*  99 */     mI localmI1 = localvg.a().getStellarSystemFromSecPos(localq);
/*     */ 
/* 101 */     this.jdField_a_of_type_Q.b(localmI1.jdField_a_of_type_Q);
/*     */ 
/* 103 */     for (int j = -1; j <= 1; j++) {
/* 104 */       for (int k = -1; k <= 1; k++) {
/* 105 */         for (int m = -1; m <= 1; m++)
/*     */         {
/* 107 */           localq.b(this.jdField_a_of_type_Q.a + m, this.jdField_a_of_type_Q.b + k, this.jdField_a_of_type_Q.c + j);
/*     */ 
/* 109 */           mI localmI2 = localvg.a().getStellarSystemFromStellarPos(localq);
/*     */ 
/* 111 */           this.jdField_a_of_type_ArrayOfByte[i] = ((byte)localmI2.jdField_a_of_type_MD.ordinal());
/*     */ 
/* 113 */           this.jdField_a_of_type_ArrayOfLong[i] = localmI2.jdField_a_of_type_Long;
/*     */ 
/* 118 */           i++;
/*     */         }
/*     */       }
/*     */     }
/* 122 */     if ((!jdField_a_of_type_Boolean) && (i != 27)) throw new AssertionError(i + "/27");
/* 123 */     synchronized (this.jdField_a_of_type_LE.a()) {
/* 124 */       this.jdField_a_of_type_LE.a().proximitySystem.setChanged(true);
/*     */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   public final int a(q paramq) {
/* 131 */     q localq = new q(this.jdField_a_of_type_Q.a - paramq.a + 1, this.jdField_a_of_type_Q.b - paramq.b + 1, this.jdField_a_of_type_Q.c - paramq.c + 1);
/*     */ 
/* 146 */     if ((!jdField_a_of_type_Boolean) && ((localq.a < 0) || (localq.b < 0) || (localq.c < 0))) throw new AssertionError(localq + ": " + paramq + " / " + this.jdField_a_of_type_Q);
/*     */ 
/* 148 */     int i = localq.c * 9 + localq.b * 3 + localq.a;
/*     */ 
/* 150 */     if ((!jdField_a_of_type_Boolean) && (!a(i, new q()).equals(paramq))) throw new AssertionError(a(i, new q()) + " / " + paramq);
/*     */ 
/* 152 */     if ((i < 0) || (i >= 27)) {
/* 153 */       return -1;
/*     */     }
/*     */ 
/* 158 */     return i;
/*     */   }
/*     */ 
/*     */   public final byte a(int paramInt)
/*     */   {
/* 163 */     return this.jdField_a_of_type_ArrayOfByte[paramInt];
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     mq
 * JD-Core Version:    0.6.2
 */