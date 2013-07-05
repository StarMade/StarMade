/*     */ package org.newdawn.slick.openal;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.nio.ByteBuffer;
/*     */ 
/*     */ public class OggDecoder
/*     */ {
/*  15 */   private int convsize = 16384;
/*     */ 
/*  17 */   private byte[] convbuffer = new byte[this.convsize];
/*     */ 
/*     */   public OggData getData(InputStream input)
/*     */     throws IOException
/*     */   {
/*  33 */     if (input == null) {
/*  34 */       throw new IOException("Failed to read OGG, source does not exist?");
/*     */     }
/*  36 */     ByteArrayOutputStream dataout = new ByteArrayOutputStream();
/*     */ 
/* 311 */     OggInputStream oggInput = new OggInputStream(input);
/*     */ 
/* 313 */     boolean done = false;
/* 314 */     while (!oggInput.atEnd()) {
/* 315 */       dataout.write(oggInput.read());
/*     */     }
/*     */ 
/* 318 */     OggData ogg = new OggData();
/* 319 */     ogg.channels = oggInput.getChannels();
/* 320 */     ogg.rate = oggInput.getRate();
/*     */ 
/* 322 */     byte[] data = dataout.toByteArray();
/* 323 */     ogg.data = ByteBuffer.allocateDirect(data.length);
/* 324 */     ogg.data.put(data);
/* 325 */     ogg.data.rewind();
/*     */ 
/* 327 */     return ogg;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.openal.OggDecoder
 * JD-Core Version:    0.6.2
 */