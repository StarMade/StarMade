package org.schema.schine.network.objects.remote;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class RemoteLongGZipPackage
  extends RemoteField
{
  public RemoteLongGZipPackage()
  {
    super(null, null);
  }
  
  public int byteLength()
  {
    return 0;
  }
  
  @Deprecated
  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt) {}
  
  @Deprecated
  public int toByteStream(DataOutputStream paramDataOutputStream)
  {
    if (!$assertionsDisabled) {
      throw new AssertionError("deprecated");
    }
    return -1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteLongGZipPackage
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */