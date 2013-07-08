/*  1:   */package org.schema.game.network.objects;
/*  2:   */
/*  3:   */import ka;
/*  4:   */import mh;
/*  5:   */import ml;
/*  6:   */import org.schema.game.network.objects.remote.RemoteInventoryBuffer;
/*  7:   */import org.schema.game.network.objects.remote.RemoteInventoryMultModBuffer;
/*  8:   */import org.schema.schine.network.StateInterface;
/*  9:   */import org.schema.schine.network.objects.remote.RemoteIntArrayBuffer;
/* 10:   */
/* 11:   */public class NetworkShop
/* 12:   */  extends NetworkSegmentController
/* 13:   */  implements ml
/* 14:   */{
/* 15:15 */  public RemoteIntArrayBuffer pricesUpdateBuffer = new RemoteIntArrayBuffer(2, this);
/* 16:16 */  public RemoteIntArrayBuffer inventoryUpdateBuffer = new RemoteIntArrayBuffer(3, this);
/* 17:17 */  public RemoteIntArrayBuffer inventoryActivateBuffer = new RemoteIntArrayBuffer(3, this);
/* 18:18 */  public RemoteInventoryMultModBuffer inventoryMultModBuffer = new RemoteInventoryMultModBuffer(this);
/* 19:   */  public RemoteInventoryBuffer inventoryBuffer;
/* 20:   */  
/* 21:   */  public RemoteIntArrayBuffer getInventoryActivateBuffer()
/* 22:   */  {
/* 23:23 */    return this.inventoryActivateBuffer;
/* 24:   */  }
/* 25:   */  
/* 26:   */  public NetworkShop(StateInterface paramStateInterface, ka paramka) {
/* 27:27 */    super(paramStateInterface, paramka);
/* 28:28 */    this.inventoryBuffer = new RemoteInventoryBuffer((mh)paramka, this);
/* 29:   */  }
/* 30:   */  
/* 34:   */  public RemoteInventoryBuffer getInventoriesChangeBuffer()
/* 35:   */  {
/* 36:36 */    return this.inventoryBuffer;
/* 37:   */  }
/* 38:   */  
/* 42:   */  public RemoteIntArrayBuffer getInventoryUpdateBuffer()
/* 43:   */  {
/* 44:44 */    return this.inventoryUpdateBuffer;
/* 45:   */  }
/* 46:   */  
/* 47:   */  public RemoteInventoryMultModBuffer getInventoryMultModBuffer()
/* 48:   */  {
/* 49:49 */    return this.inventoryMultModBuffer;
/* 50:   */  }
/* 51:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.NetworkShop
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */