/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.util.zip.DataFormatException;
/*     */ import java.util.zip.Deflater;
/*     */ import java.util.zip.Inflater;
/*     */ import org.schema.game.common.data.world.Universe;
/*     */ import org.schema.game.network.objects.NetworkPlayer;
/*     */ import org.schema.game.network.objects.remote.RemoteProximitySector;
/*     */ 
/*     */ public class mp
/*     */ {
/*     */   private int jdField_a_of_type_Int;
/*  22 */   private q jdField_a_of_type_Q = new q();
/*  23 */   private byte[] jdField_a_of_type_ArrayOfByte = new byte[5488];
/*     */   private lE jdField_a_of_type_LE;
/*     */ 
/*     */   public mp(lE paramlE)
/*     */   {
/*  27 */     this.jdField_a_of_type_LE = paramlE;
/*     */   }
/*     */ 
/*     */   public final q a(int paramInt, q paramq)
/*     */   {
/*  32 */     paramq.b(this.jdField_a_of_type_Q.jdField_a_of_type_Int - 7, this.jdField_a_of_type_Q.b - 7, this.jdField_a_of_type_Q.c - 7);
/*     */ 
/*  37 */     int i = paramInt / 196;
/*     */ 
/*  39 */     int j = (paramInt - i * 196) / 14;
/*     */ 
/*  41 */     paramInt -= i * 196 + j * 14;
/*     */ 
/*  43 */     paramq.a(paramInt, j, i);
/*     */ 
/*  45 */     return paramq;
/*     */   }
/*     */ 
/*     */   public final byte a(int paramInt) {
/*  49 */     return this.jdField_a_of_type_ArrayOfByte[(paramInt + 2744)];
/*     */   }
/*     */   public final byte b(int paramInt) {
/*  52 */     return this.jdField_a_of_type_ArrayOfByte[paramInt];
/*     */   }
/*     */   public final int a() {
/*  55 */     return this.jdField_a_of_type_Int;
/*     */   }
/*     */ 
/*     */   public final void a(DataInputStream paramDataInputStream)
/*     */   {
/*  63 */     long l1 = System.currentTimeMillis();
/*     */ 
/*  65 */     this.jdField_a_of_type_Int = paramDataInputStream.readInt();
/*     */ 
/*  67 */     int i = paramDataInputStream.readInt();
/*  68 */     int j = paramDataInputStream.readInt();
/*  69 */     int k = paramDataInputStream.readInt();
/*     */ 
/*  71 */     this.jdField_a_of_type_Q.b(i, j, k);
/*     */     byte[] arrayOfByte;
/*     */     Inflater localInflater;
/*  76 */     if (this.jdField_a_of_type_LE.isOnServer()) {
/*  77 */       arrayOfByte = vg.jdField_a_of_type_ArrayOfByte;
/*  78 */       localInflater = vg.jdField_a_of_type_JavaUtilZipInflater;
/*     */     } else {
/*  80 */       arrayOfByte = ct.jdField_a_of_type_ArrayOfByte;
/*  81 */       localInflater = ct.jdField_a_of_type_JavaUtilZipInflater;
/*     */     }
/*     */ 
/*  84 */     k = paramDataInputStream.readInt();
/*     */ 
/*  86 */     synchronized (arrayOfByte) {
/*  87 */       int m = paramDataInputStream.read(arrayOfByte, 0, k);
/*     */ 
/*  89 */       if ((!jdField_a_of_type_Boolean) && (m != k)) throw new AssertionError();
/*  90 */       localInflater.reset();
/*  91 */       localInflater.setInput(arrayOfByte, 0, k);
/*     */       try
/*     */       {
/*  95 */         if ((
/*  95 */           paramDataInputStream = localInflater.inflate(this.jdField_a_of_type_ArrayOfByte)) == 0)
/*     */         {
/*  96 */           System.err.println("WARNING: INFLATED BYTES 0: " + localInflater.needsInput() + " " + localInflater.needsDictionary());
/*     */         }
/*     */ 
/*  99 */         if (paramDataInputStream != this.jdField_a_of_type_ArrayOfByte.length)
/* 100 */           System.err.println("[INFLATER] Exception: " + this.jdField_a_of_type_ArrayOfByte.length + " size received: " + k + ": " + paramDataInputStream + "/" + this.jdField_a_of_type_ArrayOfByte.length + " for " + this.jdField_a_of_type_LE + " pos " + this.jdField_a_of_type_Q);
/*     */       }
/*     */       catch (DataFormatException localDataFormatException)
/*     */       {
/* 104 */         localDataFormatException.printStackTrace();
/*     */       }
/*     */     }
/*     */     long l2;
/* 107 */     if ((
/* 107 */       l2 = System.currentTimeMillis() - l1) > 
/* 107 */       5L)
/* 108 */       System.err.println("[CLIENT] WARNING: deserialized PROXIMITY " + this.jdField_a_of_type_Q + " took " + l2 + "ms");
/*     */   }
/*     */ 
/*     */   public final void a(DataOutputStream paramDataOutputStream)
/*     */   {
/* 117 */     paramDataOutputStream.writeInt(this.jdField_a_of_type_Int);
/* 118 */     paramDataOutputStream.writeInt(this.jdField_a_of_type_Q.jdField_a_of_type_Int);
/* 119 */     paramDataOutputStream.writeInt(this.jdField_a_of_type_Q.b);
/* 120 */     paramDataOutputStream.writeInt(this.jdField_a_of_type_Q.c);
/*     */     byte[] arrayOfByte;
/*     */     Deflater localDeflater;
/* 125 */     if (this.jdField_a_of_type_LE.isOnServer()) {
/* 126 */       arrayOfByte = vg.jdField_a_of_type_ArrayOfByte;
/* 127 */       localDeflater = vg.jdField_a_of_type_JavaUtilZipDeflater;
/*     */     } else {
/* 129 */       arrayOfByte = ct.jdField_a_of_type_ArrayOfByte;
/* 130 */       localDeflater = ct.jdField_a_of_type_JavaUtilZipDeflater;
/*     */     }
/* 132 */     synchronized (arrayOfByte) {
/* 133 */       localDeflater.reset();
/* 134 */       localDeflater.setInput(this.jdField_a_of_type_ArrayOfByte);
/* 135 */       localDeflater.finish();
/* 136 */       int i = localDeflater.deflate(arrayOfByte);
/* 137 */       paramDataOutputStream.writeInt(i);
/* 138 */       paramDataOutputStream.write(arrayOfByte, 0, i);
/*     */     }
/* 140 */     System.err.println("[SERVER] SERIALIZED PROXIMITY " + this.jdField_a_of_type_Q);
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/* 150 */     long l1 = System.currentTimeMillis();
/* 151 */     vg localvg = (vg)this.jdField_a_of_type_LE.getState();
/* 152 */     q localq1 = new q(this.jdField_a_of_type_LE.a());
/*     */ 
/* 154 */     this.jdField_a_of_type_Q.b(localq1);
/* 155 */     this.jdField_a_of_type_Int = this.jdField_a_of_type_LE.c();
/*     */ 
/* 157 */     localvg.a().updateProximitySectorInformation(localq1);
/* 158 */     int i = 0;
/* 159 */     q localq2 = new q();
/*     */ 
/* 161 */     for (int j = localq1.c - 7; j < localq1.c + 7; j++) {
/* 162 */       for (int k = localq1.b - 7; k < localq1.b + 7; k++) {
/* 163 */         for (int m = localq1.jdField_a_of_type_Int - 7; m < localq1.jdField_a_of_type_Int + 7; m++) {
/* 164 */           localq2.b(m, k, j);
/* 165 */           mI localmI = localvg.a().getStellarSystemFromSecPos(localq2);
/*     */ 
/* 167 */           this.jdField_a_of_type_ArrayOfByte[i] = ((byte)localmI.a(localq2).ordinal());
/* 168 */           this.jdField_a_of_type_ArrayOfByte[(i + 2744)] = ((byte)localmI.a(localq2).ordinal());
/* 169 */           i++;
/*     */         }
/*     */       }
/*     */     }
/* 173 */     if ((!jdField_a_of_type_Boolean) && (i != 2744)) throw new AssertionError(i + "/2744");
/* 174 */     this.jdField_a_of_type_LE.a().proximitySector.setChanged(true);
/*     */     long l2;
/* 177 */     if ((
/* 177 */       l2 = System.currentTimeMillis() - l1) > 
/* 177 */       10L)
/* 178 */       System.err.println("[SERVER] WARNING: UPDATING SERVER SECTORPROXMITY TOOK " + l2);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     mp
 * JD-Core Version:    0.6.2
 */