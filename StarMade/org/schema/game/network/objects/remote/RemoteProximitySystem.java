/*  1:   */package org.schema.game.network.objects.remote;
/*  2:   */
/*  3:   */import java.io.DataInputStream;
/*  4:   */import java.io.DataOutputStream;
/*  5:   */import mq;
/*  6:   */import org.schema.schine.network.objects.NetworkObject;
/*  7:   */import org.schema.schine.network.objects.remote.RemoteField;
/*  8:   */
/* 12:   */public class RemoteProximitySystem
/* 13:   */  extends RemoteField
/* 14:   */{
/* 15:   */  public RemoteProximitySystem(mq parammq, NetworkObject paramNetworkObject)
/* 16:   */  {
/* 17:17 */    super(parammq, paramNetworkObject);
/* 18:   */  }
/* 19:   */  
/* 20:   */  public RemoteProximitySystem(mq parammq, boolean paramBoolean) {
/* 21:21 */    super(parammq, paramBoolean);
/* 22:   */  }
/* 23:   */  
/* 24:   */  public int byteLength()
/* 25:   */  {
/* 26:26 */    return 255;
/* 27:   */  }
/* 28:   */  
/* 31:   */  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/* 32:   */  {
/* 33:33 */    ((mq)get()).a(paramDataInputStream);
/* 34:   */  }
/* 35:   */  
/* 36:   */  public int toByteStream(DataOutputStream paramDataOutputStream)
/* 37:   */  {
/* 38:38 */    ((mq)get()).a(paramDataOutputStream);
/* 39:39 */    return byteLength();
/* 40:   */  }
/* 41:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteProximitySystem
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */