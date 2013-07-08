/*  1:   */package org.schema.game.network.objects;
/*  2:   */
/*  3:   */import mw;
/*  4:   */import org.schema.game.network.objects.remote.RemoteSegmentGZipPackage;
/*  5:   */import org.schema.schine.network.StateInterface;
/*  6:   */import org.schema.schine.network.objects.NetworkObject;
/*  7:   */
/*  8:   */@Deprecated
/*  9:   */public class NetworkSegment
/* 10:   */  extends NetworkObject
/* 11:   */{
/* 12:   */  public RemoteSegmentGZipPackage pack;
/* 13:   */  private mw segment;
/* 14:   */  
/* 15:   */  public NetworkSegment(StateInterface paramStateInterface, mw parammw)
/* 16:   */  {
/* 17:17 */    super(paramStateInterface);
/* 18:18 */    this.segment = parammw;
/* 19:19 */    this.pack = new RemoteSegmentGZipPackage(parammw, this);
/* 20:   */  }
/* 21:   */  
/* 22:   */  public void onDelete(StateInterface paramStateInterface) {}
/* 23:   */  
/* 24:   */  public void onInit(StateInterface paramStateInterface) {}
/* 25:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.NetworkSegment
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */