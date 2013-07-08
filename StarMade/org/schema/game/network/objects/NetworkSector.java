/*  1:   */package org.schema.game.network.objects;
/*  2:   */
/*  3:   */import org.schema.game.network.objects.remote.RemoteItemBuffer;
/*  4:   */import org.schema.schine.network.StateInterface;
/*  5:   */import org.schema.schine.network.objects.NetworkObject;
/*  6:   */import org.schema.schine.network.objects.remote.RemoteBoolean;
/*  7:   */import org.schema.schine.network.objects.remote.RemoteVector3i;
/*  8:   */
/* 12:   */public class NetworkSector
/* 13:   */  extends NetworkObject
/* 14:   */{
/* 15:15 */  public RemoteBoolean active = new RemoteBoolean(this);
/* 16:16 */  public RemoteVector3i pos = new RemoteVector3i(this);
/* 17:17 */  public RemoteItemBuffer itemBuffer = new RemoteItemBuffer(this);
/* 18:   */  
/* 19:   */  public NetworkSector(StateInterface paramStateInterface) {
/* 20:20 */    super(paramStateInterface);
/* 21:   */  }
/* 22:   */  
/* 23:   */  public void onDelete(StateInterface paramStateInterface) {}
/* 24:   */  
/* 25:   */  public void onInit(StateInterface paramStateInterface) {}
/* 26:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.NetworkSector
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */