/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.io.PushbackInputStream;
/*     */ import java.util.zip.DataFormatException;
/*     */ import java.util.zip.Deflater;
/*     */ import java.util.zip.GZIPInputStream;
/*     */ import java.util.zip.Inflater;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.data.world.DeserializationException;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import org.schema.game.common.data.world.SegmentData;
/*     */ 
/*     */ public class mw extends Segment
/*     */ {
/*     */   private long jdField_a_of_type_Long;
/*     */   private boolean jdField_a_of_type_Boolean;
/*     */   public Object b;
/*  23 */   private long jdField_b_of_type_Long = -1L;
/*     */   private boolean jdField_b_of_type_Boolean;
/*     */   private boolean d;
/*     */ 
/*     */   public mw(SegmentController paramSegmentController)
/*     */   {
/*  30 */     super(paramSegmentController);
/*     */ 
/*  21 */     this.jdField_b_of_type_JavaLangObject = new Object();
/*     */   }
/*     */ 
/*     */   public void a(boolean paramBoolean)
/*     */   {
/*  36 */     this.jdField_a_of_type_Boolean = paramBoolean; System.currentTimeMillis();
/*     */   }
/*     */ 
/*     */   public final void a(DataInputStream arg1, int paramInt, boolean paramBoolean)
/*     */   {
/*  42 */     ??? = new PushbackInputStream(???, 2);
/*  43 */     byte[] arrayOfByte = new byte[2];
/*  44 */     ???.read(arrayOfByte);
/*  45 */     ???.unread(arrayOfByte);
/*     */ 
/*  49 */     ??? = new DataInputStream(???);
/*  50 */     if ((arrayOfByte[0] == 31) && (arrayOfByte[1] == -117))
/*     */     {
/*  54 */       boolean bool = paramBoolean; paramBoolean = paramInt; paramInt = ???; ??? = this; if (paramBoolean) paramInt = new DataInputStream(new GZIPInputStream(paramInt)); else paramInt = new DataInputStream(new GZIPInputStream(paramInt, paramBoolean)); long l2 = paramInt.readLong(); localDataInputStream = paramInt.readInt(); m = paramInt.readInt(); int n = paramInt.readInt(); if (bool) ???.a(localDataInputStream, m, n); if ((!e) && (!bool) && ((???.jdField_a_of_type_Q.a != localDataInputStream) || (???.jdField_a_of_type_Q.b != m) || (???.jdField_a_of_type_Q.c != n))) throw new AssertionError(" deserialized " + localDataInputStream + ", " + m + ", " + n + "; toSerialize " + ???.jdField_a_of_type_Q);
/*  54 */       int i;
/*  54 */       if ((i = paramInt.readByte()) == 1) { if (???.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData == null) ???.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider().a().assignData(???); ???.d = true; synchronized (???.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData) { ???.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData.deSerialize(paramInt); }  } if ((!e) && (??? != 2)) throw new AssertionError(); ???.jdField_a_of_type_Long = l2; ???.d = false;
/*     */       try
/*     */       {
/*  54 */         int j;
/*  54 */         if ((j = paramInt.read()) != -1) throw new DeserializationException("EoF not reached: " + j + " - size given: " + paramBoolean);
/*     */         return;
/*     */       }
/*     */       catch (IOException localIOException2)
/*     */       {
/*     */         IOException localIOException1;
/*  54 */         (localIOException1 = localIOException2).printStackTrace(); throw new DeserializationException("[WARNING][DESERIALIZE] " + ???.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState() + ": " + ???.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController + ": " + ???.jdField_a_of_type_Q + ": " + localIOException1.getMessage()); } 
/*  55 */     }long l1 = ???.readLong();
/*     */ 
/*  62 */     int k = ???.readInt();
/*  63 */     DataInputStream localDataInputStream = ???.readInt();
/*  64 */     int m = ???.readInt();
/*     */ 
/*  66 */     if (paramBoolean) {
/*  67 */       a(k, localDataInputStream, m);
/*     */     }
/*     */ 
/*  70 */     if ((!e) && (!paramBoolean) && ((this.jdField_a_of_type_Q.a != k) || (this.jdField_a_of_type_Q.b != localDataInputStream) || (this.jdField_a_of_type_Q.c != m))) throw new AssertionError(" deserialized " + k + ", " + localDataInputStream + ", " + m + "; toSerialize " + this.jdField_a_of_type_Q + " on " + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController);
/*     */ 
/*  75 */     if ((
/*  75 */       paramBoolean = ???.readByte()) == 
/*  75 */       1)
/*     */     {
/*  78 */       if ((this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController == null) || (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer())) {
/*  79 */         paramBoolean = vg.jdField_a_of_type_ArrayOfByte;
/*  80 */         paramInt = vg.jdField_a_of_type_JavaUtilZipInflater;
/*     */       } else {
/*  82 */         paramBoolean = ct.jdField_a_of_type_ArrayOfByte;
/*  83 */         paramInt = ct.jdField_a_of_type_JavaUtilZipInflater;
/*     */       }
/*  85 */       k = 0;
/*  86 */       if (this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData == null) {
/*  87 */         this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider().a()
/*  88 */           .assignData(this);
/*     */       }
/*     */       else
/*     */       {
/*  92 */         k = 1;
/*     */       }
/*     */ 
/*  96 */       this.d = true;
/*     */ 
/*  98 */       localDataInputStream = ???.readInt();
/*     */ 
/* 103 */       if ((!e) && (localDataInputStream > paramBoolean.length)) throw new AssertionError(localDataInputStream + "/" + paramBoolean.length);
/* 104 */       synchronized (paramBoolean) {
/* 105 */         ??? = ???.read(paramBoolean, 0, localDataInputStream);
/*     */ 
/* 107 */         if ((!e) && (??? != localDataInputStream)) throw new AssertionError();
/*     */ 
/* 118 */         paramInt.reset();
/*     */ 
/* 120 */         paramInt.setInput(paramBoolean, 0, localDataInputStream);
/*     */ 
/* 122 */         synchronized (this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData)
/*     */         {
/*     */           try {
/* 125 */             if (k != 0) {
/* 126 */               this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData.reset();
/*     */             }
/* 128 */             paramBoolean = paramInt.inflate(this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData.getAsBuffer());
/* 129 */             this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData.setNeedsRevalidate(true);
/* 130 */             System.currentTimeMillis();
/* 131 */             if (!paramBoolean) {
/* 132 */               System.err.println("WARNING: INFLATED BYTES 0: " + paramInt.needsInput() + " " + paramInt.needsDictionary());
/*     */             }
/*     */ 
/* 135 */             if (paramBoolean != this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData.getAsBuffer().length)
/* 136 */               System.err.println("[INFLATER] Exception: " + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState() + " size received: " + localDataInputStream + ": " + paramBoolean + "/" + this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData.getAsBuffer().length + " for " + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController + " pos " + this.jdField_a_of_type_Q);
/*     */           }
/*     */           catch (DataFormatException localDataFormatException)
/*     */           {
/* 140 */             localDataFormatException.printStackTrace();
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 144 */     if ((!e) && (paramBoolean != true)) throw new AssertionError(paramBoolean + "/2: " + k + ", " + localDataInputStream + ", " + ??? + "; byte size: " + paramInt);
/*     */ 
/* 148 */     this.jdField_a_of_type_Long = l1;
/* 149 */     this.d = false;
/*     */   }
/*     */ 
/*     */   public final long a()
/*     */   {
/* 154 */     return this.jdField_a_of_type_Long;
/*     */   }
/*     */ 
/*     */   public final long b()
/*     */   {
/* 161 */     return this.jdField_b_of_type_Long;
/*     */   }
/*     */ 
/*     */   public final void a(le paramle)
/*     */   {
/* 175 */     super.a(paramle);
/* 176 */     if (this.jdField_a_of_type_Boolean)
/* 177 */       this.jdField_a_of_type_Long = System.currentTimeMillis();
/*     */   }
/*     */ 
/*     */   public final boolean e()
/*     */   {
/* 196 */     return this.d;
/*     */   }
/*     */ 
/*     */   public final boolean f()
/*     */   {
/* 210 */     return this.jdField_b_of_type_Boolean;
/*     */   }
/*     */ 
/*     */   public final int a(DataOutputStream paramDataOutputStream)
/*     */   {
/* 265 */     paramDataOutputStream = new DataOutputStream(paramDataOutputStream);
/* 266 */     if ((!e) && (paramDataOutputStream.size() != 0)) throw new AssertionError();
/* 267 */     int i = 0;
/* 268 */     paramDataOutputStream.writeLong(this.jdField_a_of_type_Long);
/*     */ 
/* 270 */     paramDataOutputStream.writeInt(this.jdField_a_of_type_Q.a);
/* 271 */     paramDataOutputStream.writeInt(this.jdField_a_of_type_Q.b);
/* 272 */     paramDataOutputStream.writeInt(this.jdField_a_of_type_Q.c);
/*     */ 
/* 274 */     i += 20;
/* 275 */     if ((!e) && (paramDataOutputStream.size() != 20)) throw new AssertionError(paramDataOutputStream.size() + "/20");
/* 276 */     int k = 0;
/*     */     int j;
/* 278 */     if ((this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData != null) && (!g()))
/*     */     {
/* 281 */       paramDataOutputStream.writeByte(1);
/*     */       byte[] arrayOfByte;
/*     */       Deflater localDeflater;
/* 285 */       if ((this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController == null) || (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer())) {
/* 286 */         arrayOfByte = vg.jdField_a_of_type_ArrayOfByte;
/* 287 */         localDeflater = vg.jdField_a_of_type_JavaUtilZipDeflater;
/*     */       } else {
/* 289 */         arrayOfByte = ct.jdField_a_of_type_ArrayOfByte;
/* 290 */         localDeflater = ct.jdField_a_of_type_JavaUtilZipDeflater;
/*     */       }
/* 292 */       if ((!e) && (paramDataOutputStream.size() != 21)) throw new AssertionError(paramDataOutputStream.size() + "/21");
/*     */       int m;
/* 296 */       synchronized (arrayOfByte) {
/* 297 */         synchronized (this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData) {
/* 298 */           localDeflater.reset();
/* 299 */           localDeflater.setInput(this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData.getAsBuffer());
/* 300 */           localDeflater.finish();
/* 301 */           m = localDeflater.deflate(arrayOfByte);
/*     */         }
/*     */ 
/* 304 */         if ((!e) && (m <= 0)) throw new AssertionError("[DEFLATER] DELFLATED SIZE: " + m + " for: " + this.jdField_a_of_type_Q + " " + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController + ": SData Size: " + this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData.getSize());
/*     */ 
/* 308 */         paramDataOutputStream.writeInt(m);
/* 309 */         if ((!e) && (paramDataOutputStream.size() != 25)) throw new AssertionError(paramDataOutputStream.size() + "/25");
/*     */ 
/* 312 */         paramDataOutputStream.write(arrayOfByte, 0, m);
/*     */       }
/* 314 */       j = m + 25;
/* 315 */       if ((!e) && (paramDataOutputStream.size() != j)) throw new AssertionError(paramDataOutputStream.size() + "/" + j); 
/*     */     }
/* 317 */     else { if ((!e) && (!g())) throw new AssertionError();
/*     */ 
/* 319 */       j++;
/*     */ 
/* 321 */       paramDataOutputStream.writeByte(2);
/*     */     }
/*     */ 
/* 326 */     if ((!e) && (paramDataOutputStream.size() != j)) throw new AssertionError(paramDataOutputStream.size() + "/" + j);
/*     */ 
/* 337 */     return paramDataOutputStream.size();
/*     */   }
/*     */ 
/*     */   public final void a(long paramLong)
/*     */   {
/* 356 */     this.jdField_a_of_type_Long = paramLong;
/*     */   }
/*     */ 
/*     */   public final void b(long paramLong)
/*     */   {
/* 362 */     this.jdField_b_of_type_Long = paramLong;
/*     */   }
/*     */ 
/*     */   public final void f(boolean paramBoolean)
/*     */   {
/* 368 */     this.jdField_b_of_type_Boolean = paramBoolean;
/*     */   }
/*     */ 
/*     */   public String toString() {
/* 372 */     return this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController + "(" + this.jdField_a_of_type_Q + ")[ID" + getId() + "; " + (this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData != null ? Integer.valueOf(this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegmentData.getSize()) : "e") + "]";
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     mw
 * JD-Core Version:    0.6.2
 */