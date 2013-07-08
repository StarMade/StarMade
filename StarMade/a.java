/*  1:   */import java.io.InputStream;
/*  2:   */import java.nio.ByteBuffer;
/*  3:   */
/*  5:   */public final class a
/*  6:   */  extends InputStream
/*  7:   */{
/*  8:   */  private ByteBuffer a;
/*  9:   */  
/* 10:   */  public a(ByteBuffer paramByteBuffer)
/* 11:   */  {
/* 12:12 */    this.a = paramByteBuffer;
/* 13:   */  }
/* 14:   */  
/* 15:   */  public final synchronized int read() {
/* 16:16 */    if (!this.a.hasRemaining()) {
/* 17:17 */      return -1;
/* 18:   */    }
/* 19:19 */    return this.a.get() & 0xFF;
/* 20:   */  }
/* 21:   */  
/* 22:   */  public final synchronized int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/* 23:   */  {
/* 24:24 */    if (!this.a.hasRemaining()) {
/* 25:25 */      return -1;
/* 26:   */    }
/* 27:   */    
/* 28:28 */    paramInt2 = Math.min(paramInt2, this.a.remaining());
/* 29:29 */    this.a.get(paramArrayOfByte, paramInt1, paramInt2);
/* 30:30 */    return paramInt2;
/* 31:   */  }
/* 32:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     a
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */