/*  1:   */package org.schema.schine.network.objects.remote;
/*  2:   */
/*  3:   */import java.io.DataInputStream;
/*  4:   */import java.io.DataOutputStream;
/*  5:   */
/*  6:   */public class RemoteLongGZipPackage
/*  7:   */  extends RemoteField
/*  8:   */{
/*  9:   */  public RemoteLongGZipPackage()
/* 10:   */  {
/* 11:11 */    super(null, null);
/* 12:   */  }
/* 13:   */  
/* 15:   */  public int byteLength()
/* 16:   */  {
/* 17:17 */    return 0;
/* 18:   */  }
/* 19:   */  
/* 38:   */  @Deprecated
/* 39:   */  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt) {}
/* 40:   */  
/* 58:   */  @Deprecated
/* 59:   */  public int toByteStream(DataOutputStream paramDataOutputStream)
/* 60:   */  {
/* 61:61 */    if (!$assertionsDisabled) { throw new AssertionError("deprecated");
/* 62:   */    }
/* 63:   */    
/* 92:92 */    return -1;
/* 93:   */  }
/* 94:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteLongGZipPackage
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */