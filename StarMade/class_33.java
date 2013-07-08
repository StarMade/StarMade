import java.io.InputStream;
import java.nio.ByteBuffer;

public final class class_33
  extends InputStream
{
  private ByteBuffer field_449;
  
  public class_33(ByteBuffer paramByteBuffer)
  {
    this.field_449 = paramByteBuffer;
  }
  
  public final synchronized int read()
  {
    if (!this.field_449.hasRemaining()) {
      return -1;
    }
    return this.field_449.get() & 0xFF;
  }
  
  public final synchronized int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (!this.field_449.hasRemaining()) {
      return -1;
    }
    paramInt2 = Math.min(paramInt2, this.field_449.remaining());
    this.field_449.get(paramArrayOfByte, paramInt1, paramInt2);
    return paramInt2;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_33
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */