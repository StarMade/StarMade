/*    */ import java.io.InputStream;
/*    */ import java.nio.ByteBuffer;
/*    */ 
/*    */ public final class a extends InputStream
/*    */ {
/*    */   private ByteBuffer a;
/*    */ 
/*    */   public a(ByteBuffer paramByteBuffer)
/*    */   {
/* 12 */     this.a = paramByteBuffer;
/*    */   }
/*    */ 
/*    */   public final synchronized int read() {
/* 16 */     if (!this.a.hasRemaining()) {
/* 17 */       return -1;
/*    */     }
/* 19 */     return this.a.get() & 0xFF;
/*    */   }
/*    */ 
/*    */   public final synchronized int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*    */   {
/* 24 */     if (!this.a.hasRemaining()) {
/* 25 */       return -1;
/*    */     }
/*    */ 
/* 28 */     paramInt2 = Math.min(paramInt2, this.a.remaining());
/* 29 */     this.a.get(paramArrayOfByte, paramInt1, paramInt2);
/* 30 */     return paramInt2;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     a
 * JD-Core Version:    0.6.2
 */