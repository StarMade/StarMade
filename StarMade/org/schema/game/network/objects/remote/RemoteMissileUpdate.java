/*  1:   */package org.schema.game.network.objects.remote;
/*  2:   */
/*  3:   */import java.io.DataInputStream;
/*  4:   */import java.io.DataOutputStream;
/*  5:   */import lw;
/*  6:   */import org.schema.schine.network.objects.NetworkObject;
/*  7:   */import org.schema.schine.network.objects.remote.RemoteField;
/*  8:   */
/*  9:   */public class RemoteMissileUpdate
/* 10:   */  extends RemoteField
/* 11:   */{
/* 12:   */  public RemoteMissileUpdate(lw paramlw, NetworkObject paramNetworkObject)
/* 13:   */  {
/* 14:14 */    super(paramlw, paramNetworkObject);
/* 15:   */  }
/* 16:   */  
/* 17:17 */  public RemoteMissileUpdate(lw paramlw, boolean paramBoolean) { super(paramlw, paramBoolean); }
/* 18:   */  
/* 20:   */  public int byteLength()
/* 21:   */  {
/* 22:22 */    return 4;
/* 23:   */  }
/* 24:   */  
/* 25:   */  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/* 26:   */  {
/* 27:27 */    set(lw.a(paramDataInputStream));
/* 28:   */  }
/* 29:   */  
/* 31:   */  public int toByteStream(DataOutputStream paramDataOutputStream)
/* 32:   */  {
/* 33:33 */    ((lw)get()).b(paramDataOutputStream);
/* 34:   */    
/* 35:35 */    return 1;
/* 36:   */  }
/* 37:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteMissileUpdate
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */