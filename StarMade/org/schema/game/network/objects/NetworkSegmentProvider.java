/*  1:   */package org.schema.game.network.objects;
/*  2:   */
/*  3:   */import kc;
/*  4:   */import org.schema.game.network.objects.remote.RemoteControlStructureBuffer;
/*  5:   */import org.schema.game.network.objects.remote.RemoteInventoryBuffer;
/*  6:   */import org.schema.game.network.objects.remote.RemoteSegmentRemoteObj;
/*  7:   */import org.schema.game.network.objects.remote.RemoteSegmentSignature;
/*  8:   */import org.schema.schine.network.StateInterface;
/*  9:   */import org.schema.schine.network.objects.NetworkObject;
/* 10:   */import org.schema.schine.network.objects.remote.RemoteBoolean;
/* 11:   */import org.schema.schine.network.objects.remote.RemoteBuffer;
/* 12:   */import org.schema.schine.network.objects.remote.RemoteVector3i;
/* 13:   */
/* 15:   */public class NetworkSegmentProvider
/* 16:   */  extends NetworkObject
/* 17:   */{
/* 18:18 */  public RemoteBuffer signatureRequestBuffer = new RemoteBuffer(RemoteVector3i.class, this);
/* 19:19 */  public RemoteBuffer segmentRequestBuffer = new RemoteBuffer(RemoteVector3i.class, this);
/* 20:   */  
/* 21:21 */  public RemoteBuffer signatureBuffer = new RemoteBuffer(RemoteSegmentSignature.class, this);
/* 22:22 */  public RemoteBuffer segmentBuffer = new RemoteBuffer(RemoteSegmentRemoteObj.class, this);
/* 23:   */  
/* 24:24 */  public RemoteBoolean requestedInitialControlMap = new RemoteBoolean(this);
/* 25:   */  
/* 26:   */  public RemoteControlStructureBuffer initialControlMap;
/* 27:   */  
/* 28:   */  public RemoteInventoryBuffer invetoryBuffer;
/* 29:29 */  public RemoteBoolean connectionReady = new RemoteBoolean(false, this);
/* 30:30 */  public RemoteBoolean signalDelete = new RemoteBoolean(false, this);
/* 31:   */  
/* 32:   */  public NetworkSegmentProvider(StateInterface paramStateInterface, kc paramkc) {
/* 33:33 */    super(paramStateInterface);
/* 34:34 */    this.initialControlMap = new RemoteControlStructureBuffer(paramkc, this);
/* 35:   */  }
/* 36:   */  
/* 37:   */  public void onDelete(StateInterface paramStateInterface) {}
/* 38:   */  
/* 39:   */  public void onInit(StateInterface paramStateInterface) {}
/* 40:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.NetworkSegmentProvider
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */