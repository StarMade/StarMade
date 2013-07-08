import java.io.File;
import java.io.OutputStream;
import java.io.RandomAccessFile;

public final class class_726
  extends OutputStream
{
  private RandomAccessFile field_1004;
  
  public class_726(File paramFile)
  {
    if ((paramFile = paramFile.getAbsoluteFile()).getParentFile() != null) {
      paramFile.mkdirs();
    }
    this.field_1004 = new RandomAccessFile(paramFile, "rw");
  }
  
  public final void close()
  {
    this.field_1004.close();
  }
  
  public final void flush() {}
  
  public final long a()
  {
    return this.field_1004.getFilePointer();
  }
  
  public final long b()
  {
    return this.field_1004.length();
  }
  
  public final void a1(long paramLong)
  {
    this.field_1004.seek(paramLong);
  }
  
  public final void b1(long paramLong)
  {
    this.field_1004.setLength(paramLong);
  }
  
  public final void write(byte[] paramArrayOfByte)
  {
    this.field_1004.write(paramArrayOfByte);
  }
  
  public final void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    this.field_1004.write(paramArrayOfByte, paramInt1, paramInt2);
  }
  
  public final void write(int paramInt)
  {
    this.field_1004.write(paramInt);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_726
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */