/*  1:   */package org.schema.game.network.objects;
/*  2:   */
/*  3:   */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*  4:   */import java.io.PrintStream;
/*  5:   */import ka;
/*  6:   */import org.schema.game.network.objects.remote.RemoteSegmentPieceBuffer;
/*  7:   */import org.schema.schine.network.NetworkStateContainer;
/*  8:   */import org.schema.schine.network.StateInterface;
/*  9:   */import org.schema.schine.network.objects.NetworkEntity;
/* 10:   */import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
/* 11:   */import org.schema.schine.network.objects.remote.RemoteBuffer;
/* 12:   */import org.schema.schine.network.objects.remote.RemoteIntPrimitive;
/* 13:   */import org.schema.schine.network.objects.remote.RemoteIntegerArray;
/* 14:   */import org.schema.schine.network.objects.remote.RemoteLongPrimitive;
/* 15:   */import org.schema.schine.network.objects.remote.RemoteString;
/* 16:   */import org.schema.schine.network.objects.remote.RemoteVector3f;
/* 17:   */import org.schema.schine.network.objects.remote.RemoteVector3i;
/* 18:   */import org.schema.schine.network.objects.remote.RemoteVector4i;
/* 19:   */
/* 21:   */public class NetworkSegmentController
/* 22:   */  extends NetworkEntity
/* 23:   */{
/* 24:24 */  public RemoteVector3i minSize = new RemoteVector3i(this);
/* 25:   */  
/* 26:26 */  public RemoteVector3i maxSize = new RemoteVector3i(this);
/* 27:   */  
/* 28:28 */  public RemoteIntPrimitive lastModifiedPlayerId = new RemoteIntPrimitive(0, this);
/* 29:   */  
/* 30:30 */  public RemoteString uniqueIdentifier = new RemoteString(this);
/* 31:   */  
/* 32:32 */  public RemoteString realName = new RemoteString(this);
/* 33:   */  
/* 34:34 */  public RemoteBuffer blockActivationBuffer = new RemoteBuffer(RemoteVector4i.class, this);
/* 35:   */  
/* 36:36 */  public RemoteBuffer dirtySegmentBuffer = new RemoteBuffer(RemoteVector3i.class, this);
/* 37:   */  
/* 38:   */  public RemoteSegmentPieceBuffer modificationBuffer;
/* 39:   */  
/* 40:40 */  public RemoteLongPrimitive initialPower = new RemoteLongPrimitive(0L, this);
/* 41:41 */  public RemoteLongPrimitive initialShields = new RemoteLongPrimitive(0L, this);
/* 42:   */  
/* 43:43 */  public RemoteArrayBuffer controlledByBuffer = new RemoteArrayBuffer(8, RemoteIntegerArray.class, this);
/* 44:   */  
/* 45:45 */  public RemoteString dockedTo = new RemoteString("NONE", this);
/* 46:   */  
/* 47:47 */  public RemoteVector3f dockingSize = new RemoteVector3f(this);
/* 48:   */  
/* 49:49 */  public RemoteVector4i dockedElement = new RemoteVector4i(this);
/* 50:   */  
/* 51:51 */  public RemoteBuffer explosions = new RemoteBuffer(RemoteVector3f.class, this);
/* 52:   */  
/* 54:   */  public NetworkSegmentController(StateInterface paramStateInterface, ka paramka)
/* 55:   */  {
/* 56:56 */    super(paramStateInterface);
/* 57:57 */    if (paramka.getState().getLocalAndRemoteObjectContainer().getRemoteObjects().containsKey(paramka.getId())) {
/* 58:58 */      System.err.println("[WARNING][SEGCONTROLLER] making a new instance of existing NT object " + paramka + ", " + paramka.getState());
/* 59:   */    }
/* 60:60 */    if (paramka.getNetworkObject() != null) {
/* 61:61 */      System.err.println("[WARNING][SEGCONTROLLER] overwriting ship's existing NT object " + paramka + ", " + paramka.getState());
/* 62:   */    }
/* 63:63 */    this.modificationBuffer = new RemoteSegmentPieceBuffer(paramka, this);
/* 64:   */  }
/* 65:   */  
/* 66:   */  public void onDelete(StateInterface paramStateInterface) {}
/* 67:   */  
/* 68:   */  public void onInit(StateInterface paramStateInterface) {}
/* 69:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.NetworkSegmentController
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */