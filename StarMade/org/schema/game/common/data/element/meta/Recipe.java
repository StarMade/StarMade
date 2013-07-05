/*     */ package org.schema.game.common.data.element.meta;
/*     */ 
/*     */ import Ad;
/*     */ import Af;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ 
/*     */ public class Recipe extends MetaObject
/*     */ {
/*     */   short[] input;
/*     */   short[] inputCount;
/*     */   short[] output;
/*     */   short[] outputCount;
/*     */ 
/*     */   public void serialize(DataOutputStream paramDataOutputStream)
/*     */   {
/*  21 */     paramDataOutputStream.writeShort(this.input.length);
/*  22 */     for (int i = 0; i < this.input.length; i++) {
/*  23 */       paramDataOutputStream.writeShort(this.input[i]);
/*     */     }
/*  25 */     paramDataOutputStream.writeShort(this.output.length);
/*  26 */     for (i = 0; i < this.output.length; i++) {
/*  27 */       paramDataOutputStream.writeShort(this.output[i]);
/*     */     }
/*     */ 
/*  30 */     paramDataOutputStream.writeShort(this.inputCount.length);
/*  31 */     for (i = 0; i < this.inputCount.length; i++) {
/*  32 */       paramDataOutputStream.writeShort(this.inputCount[i]);
/*     */     }
/*  34 */     paramDataOutputStream.writeShort(this.outputCount.length);
/*  35 */     for (i = 0; i < this.outputCount.length; i++)
/*  36 */       paramDataOutputStream.writeShort(this.outputCount[i]);
/*     */   }
/*     */ 
/*     */   public void deserialize(DataInputStream paramDataInputStream)
/*     */   {
/*  43 */     this.input = new short[paramDataInputStream.readShort()];
/*  44 */     for (int i = 0; i < this.input.length; i++) {
/*  45 */       this.input[i] = paramDataInputStream.readShort();
/*     */     }
/*     */ 
/*  49 */     this.output = new short[paramDataInputStream.readShort()];
/*  50 */     for (i = 0; i < this.output.length; i++) {
/*  51 */       this.output[i] = paramDataInputStream.readShort();
/*     */     }
/*     */ 
/*  54 */     this.inputCount = new short[paramDataInputStream.readShort()];
/*  55 */     for (i = 0; i < this.inputCount.length; i++) {
/*  56 */       this.inputCount[i] = paramDataInputStream.readShort();
/*     */     }
/*     */ 
/*  60 */     this.outputCount = new short[paramDataInputStream.readShort()];
/*  61 */     for (i = 0; i < this.outputCount.length; i++)
/*  62 */       this.outputCount[i] = paramDataInputStream.readShort();
/*     */   }
/*     */ 
/*     */   public short getObjectBlockID()
/*     */   {
/*  68 */     return -10;
/*     */   }
/*     */ 
/*     */   public Ad getBytesTag()
/*     */   {
/*  73 */     Ad[] arrayOfAd1 = new Ad[this.input.length + 1];
/*  74 */     Ad[] arrayOfAd2 = new Ad[this.output.length + 1];
/*     */ 
/*  76 */     arrayOfAd1[this.input.length] = new Ad(Af.a, null, null);
/*  77 */     for (int i = 0; i < this.input.length; i++) {
/*  78 */       arrayOfAd1[i] = new Ad(Af.c, null, Short.valueOf(this.input[i]));
/*     */     }
/*     */ 
/*  82 */     arrayOfAd2[this.output.length] = new Ad(Af.a, null, null);
/*  83 */     for (i = 0; i < this.output.length; i++) {
/*  84 */       arrayOfAd2[i] = new Ad(Af.c, null, Short.valueOf(this.output[i]));
/*     */     }
/*     */ 
/*  88 */     Ad[] arrayOfAd3 = new Ad[this.inputCount.length + 1];
/*  89 */     Ad[] arrayOfAd4 = new Ad[this.outputCount.length + 1];
/*     */ 
/*  91 */     arrayOfAd3[this.inputCount.length] = new Ad(Af.a, null, null);
/*  92 */     for (int j = 0; j < this.inputCount.length; j++) {
/*  93 */       arrayOfAd3[j] = new Ad(Af.c, null, Short.valueOf(this.inputCount[j]));
/*     */     }
/*     */ 
/*  97 */     arrayOfAd4[this.outputCount.length] = new Ad(Af.a, null, null);
/*  98 */     for (j = 0; j < this.outputCount.length; j++) {
/*  99 */       arrayOfAd4[j] = new Ad(Af.c, null, Short.valueOf(this.outputCount[j]));
/*     */     }
/* 101 */     return new Ad(Af.n, null, new Ad[] { new Ad(Af.n, null, arrayOfAd1), new Ad(Af.n, null, arrayOfAd2), new Ad(Af.n, null, arrayOfAd3), new Ad(Af.n, null, arrayOfAd4), new Ad(Af.a, null, null) });
/*     */   }
/*     */ 
/*     */   public void fromTag(Ad paramAd)
/*     */   {
/* 112 */     Ad[] arrayOfAd1 = (Ad[])((Ad[])paramAd.a())[0].a();
/* 113 */     Ad[] arrayOfAd2 = (Ad[])((Ad[])paramAd.a())[1].a();
/*     */ 
/* 115 */     this.input = new short[arrayOfAd1.length - 1];
/* 116 */     for (int j = 0; j < arrayOfAd1.length - 1; j++) {
/* 117 */       this.input[j] = ((Short)arrayOfAd1[j].a()).shortValue();
/*     */     }
/*     */ 
/* 120 */     this.output = new short[arrayOfAd2.length - 1];
/* 121 */     for (j = 0; j < arrayOfAd2.length - 1; j++) {
/* 122 */       this.output[j] = ((Short)arrayOfAd2[j].a()).shortValue();
/*     */     }
/*     */ 
/* 125 */     Ad[] arrayOfAd3 = (Ad[])((Ad[])paramAd.a())[2].a();
/* 126 */     paramAd = (Ad[])((Ad[])paramAd.a())[3].a();
/*     */ 
/* 128 */     this.inputCount = new short[arrayOfAd3.length - 1];
/* 129 */     for (int i = 0; i < arrayOfAd3.length - 1; i++) {
/* 130 */       this.inputCount[i] = ((Short)arrayOfAd3[i].a()).shortValue();
/*     */     }
/*     */ 
/* 133 */     this.outputCount = new short[paramAd.length - 1];
/* 134 */     for (i = 0; i < paramAd.length - 1; i++)
/* 135 */       this.outputCount[i] = ((Short)paramAd[i].a()).shortValue();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.meta.Recipe
 * JD-Core Version:    0.6.2
 */