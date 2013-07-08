/*  1:   */package org.schema.game.network.objects.remote;
/*  2:   */
/*  3:   */import java.io.DataInputStream;
/*  4:   */import java.io.DataOutputStream;
/*  5:   */import ka;
/*  6:   */import kc;
/*  7:   */import org.schema.game.common.data.element.ControlElementMap;
/*  8:   */import org.schema.schine.network.objects.NetworkObject;
/*  9:   */import org.schema.schine.network.objects.remote.RemoteField;
/* 10:   */
/* 12:   */public class RemoteControlStructure
/* 13:   */  extends RemoteField
/* 14:   */{
/* 15:   */  private kc segmentController;
/* 16:   */  
/* 17:   */  public RemoteControlStructure(kc paramkc, NetworkObject paramNetworkObject)
/* 18:   */  {
/* 19:19 */    super(Boolean.valueOf(true), paramNetworkObject);
/* 20:20 */    this.segmentController = paramkc;
/* 21:   */  }
/* 22:   */  
/* 23:23 */  public RemoteControlStructure(kc paramkc, boolean paramBoolean) { super(Boolean.valueOf(true), paramBoolean);
/* 24:24 */    this.segmentController = paramkc;
/* 25:   */  }
/* 26:   */  
/* 27:27 */  public boolean initialSynchUpdateOnly() { return true; }
/* 28:   */  
/* 29:   */  public int byteLength()
/* 30:   */  {
/* 31:31 */    return 0;
/* 32:   */  }
/* 33:   */  
/* 34:   */  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt) {
/* 35:35 */    this.segmentController.a().getControlElementMap().deserializeZipped(paramDataInputStream);
/* 36:   */  }
/* 37:   */  
/* 38:   */  public int toByteStream(DataOutputStream paramDataOutputStream)
/* 39:   */  {
/* 40:40 */    this.segmentController.a().getControlElementMap().serializeZipped(paramDataOutputStream);
/* 41:41 */    return 1;
/* 42:   */  }
/* 43:   */  
/* 44:   */  public int toByteStream(DataOutputStream paramDataOutputStream, ka paramka) {
/* 45:45 */    paramka.getControlElementMap().serializeZipped(paramDataOutputStream);
/* 46:46 */    return 1;
/* 47:   */  }
/* 48:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteControlStructure
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */