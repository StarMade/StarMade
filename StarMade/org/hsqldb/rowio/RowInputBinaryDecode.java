package org.hsqldb.rowio;

import java.io.IOException;
import org.hsqldb.persist.Crypto;
import org.hsqldb.types.Type;

public class RowInputBinaryDecode
  extends RowInputBinary
{
  final Crypto crypto;
  
  public RowInputBinaryDecode(Crypto paramCrypto, byte[] paramArrayOfByte)
  {
    super(paramArrayOfByte);
    this.crypto = paramCrypto;
  }
  
  public Object[] readData(Type[] paramArrayOfType)
    throws IOException
  {
    if (this.crypto != null)
    {
      int i = this.pos;
      int j = readInt();
      this.crypto.decode(this.buffer, this.pos, j, this.buffer, i);
      this.pos = i;
    }
    return super.readData(paramArrayOfType);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.rowio.RowInputBinaryDecode
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */