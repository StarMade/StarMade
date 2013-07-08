/*  1:   */package org.schema.game.network.objects.remote;
/*  2:   */
/*  3:   */import java.io.DataInputStream;
/*  4:   */import java.io.DataOutputStream;
/*  5:   */import mp;
/*  6:   */import org.schema.schine.network.objects.NetworkObject;
/*  7:   */import org.schema.schine.network.objects.remote.RemoteField;
/*  8:   */
/* 12:   */public class RemoteProximitySector
/* 13:   */  extends RemoteField
/* 14:   */{
/* 15:   */  public RemoteProximitySector(mp parammp, NetworkObject paramNetworkObject)
/* 16:   */  {
/* 17:17 */    super(parammp, paramNetworkObject);
/* 18:   */  }
/* 19:   */  
/* 20:20 */  public RemoteProximitySector(mp parammp, boolean paramBoolean) { super(parammp, paramBoolean); }
/* 21:   */  
/* 23:   */  public int byteLength()
/* 24:   */  {
/* 25:25 */    return 5504;
/* 26:   */  }
/* 27:   */  
/* 30:   */  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/* 31:   */  {
/* 32:32 */    ((mp)get()).a(paramDataInputStream);
/* 33:   */  }
/* 34:   */  
/* 35:   */  public int toByteStream(DataOutputStream paramDataOutputStream)
/* 36:   */  {
/* 37:37 */    ((mp)get()).a(paramDataOutputStream);
/* 38:38 */    return byteLength();
/* 39:   */  }
/* 40:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteProximitySector
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */