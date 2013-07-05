/*     */ import java.io.IOException;
/*     */ import java.nio.ByteBuffer;
/*     */ 
/*     */ public final class zw
/*     */ {
/*     */   public int a;
/*     */   public int b;
/*     */   public int c;
/*     */   public int d;
/*     */   public int e;
/*     */   public int f;
/*     */   public String a;
/*     */   public int g;
/*     */   public int h;
/*     */   public int i;
/*     */   public int j;
/*     */   public int k;
/*     */ 
/*     */   public zw(ByteBuffer paramByteBuffer)
/*     */   {
/* 174 */     byte[] arrayOfByte = new byte[4];
/* 175 */     paramByteBuffer.get(arrayOfByte);
/*     */     String str;
/* 177 */     if (!(
/* 177 */       str = new String(arrayOfByte, "US-ASCII"))
/* 177 */       .equals("DDS "))
/* 178 */       throw new IOException("Bad magic: " + str);
/*     */     int m;
/* 183 */     if ((
/* 183 */       m = paramByteBuffer.getInt()) != 
/* 183 */       124) {
/* 184 */       throw new IOException("Wrong header size: " + m);
/*     */     }
/*     */ 
/* 187 */     this.jdField_a_of_type_Int = paramByteBuffer.getInt();
/* 188 */     this.b = paramByteBuffer.getInt();
/* 189 */     this.c = paramByteBuffer.getInt();
/* 190 */     paramByteBuffer.getInt();
/* 191 */     this.d = paramByteBuffer.getInt();
/* 192 */     this.e = paramByteBuffer.getInt();
/* 193 */     paramByteBuffer.position(paramByteBuffer.position() + 44);
/*     */ 
/* 197 */     if ((
/* 197 */       m = paramByteBuffer.getInt()) != 
/* 197 */       32) {
/* 198 */       throw new IOException("Wrong pixel format size: " + m);
/*     */     }
/* 200 */     this.f = paramByteBuffer.getInt();
/* 201 */     paramByteBuffer.get(arrayOfByte);
/* 202 */     this.jdField_a_of_type_JavaLangString = ((this.f & 0x4) == 0 ? null : new String(arrayOfByte, "US-ASCII"));
/*     */ 
/* 204 */     paramByteBuffer.getInt();
/* 205 */     this.g = paramByteBuffer.getInt();
/* 206 */     this.h = paramByteBuffer.getInt();
/* 207 */     this.i = paramByteBuffer.getInt();
/* 208 */     this.j = paramByteBuffer.getInt();
/*     */ 
/* 211 */     paramByteBuffer.getInt();
/* 212 */     this.k = paramByteBuffer.getInt();
/* 213 */     paramByteBuffer.position(paramByteBuffer.position() + 8);
/*     */ 
/* 215 */     paramByteBuffer.position(paramByteBuffer.position() + 4);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     zw
 * JD-Core Version:    0.6.2
 */