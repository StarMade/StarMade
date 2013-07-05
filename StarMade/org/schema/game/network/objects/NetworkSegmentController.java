/*    */ package org.schema.game.network.objects;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*    */ import java.io.PrintStream;
/*    */ import ka;
/*    */ import org.schema.game.network.objects.remote.RemoteSegmentPieceBuffer;
/*    */ import org.schema.schine.network.NetworkStateContainer;
/*    */ import org.schema.schine.network.StateInterface;
/*    */ import org.schema.schine.network.objects.NetworkEntity;
/*    */ import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
/*    */ import org.schema.schine.network.objects.remote.RemoteBuffer;
/*    */ import org.schema.schine.network.objects.remote.RemoteIntPrimitive;
/*    */ import org.schema.schine.network.objects.remote.RemoteIntegerArray;
/*    */ import org.schema.schine.network.objects.remote.RemoteLongPrimitive;
/*    */ import org.schema.schine.network.objects.remote.RemoteString;
/*    */ import org.schema.schine.network.objects.remote.RemoteVector3f;
/*    */ import org.schema.schine.network.objects.remote.RemoteVector3i;
/*    */ import org.schema.schine.network.objects.remote.RemoteVector4i;
/*    */ 
/*    */ public class NetworkSegmentController extends NetworkEntity
/*    */ {
/* 24 */   public RemoteVector3i minSize = new RemoteVector3i(this);
/*    */ 
/* 26 */   public RemoteVector3i maxSize = new RemoteVector3i(this);
/*    */ 
/* 28 */   public RemoteIntPrimitive lastModifiedPlayerId = new RemoteIntPrimitive(0, this);
/*    */ 
/* 30 */   public RemoteString uniqueIdentifier = new RemoteString(this);
/*    */ 
/* 32 */   public RemoteString realName = new RemoteString(this);
/*    */ 
/* 34 */   public RemoteBuffer blockActivationBuffer = new RemoteBuffer(RemoteVector4i.class, this);
/*    */ 
/* 36 */   public RemoteBuffer dirtySegmentBuffer = new RemoteBuffer(RemoteVector3i.class, this);
/*    */   public RemoteSegmentPieceBuffer modificationBuffer;
/* 40 */   public RemoteLongPrimitive initialPower = new RemoteLongPrimitive(0L, this);
/* 41 */   public RemoteLongPrimitive initialShields = new RemoteLongPrimitive(0L, this);
/*    */ 
/* 43 */   public RemoteArrayBuffer controlledByBuffer = new RemoteArrayBuffer(8, RemoteIntegerArray.class, this);
/*    */ 
/* 45 */   public RemoteString dockedTo = new RemoteString("NONE", this);
/*    */ 
/* 47 */   public RemoteVector3f dockingSize = new RemoteVector3f(this);
/*    */ 
/* 49 */   public RemoteVector4i dockedElement = new RemoteVector4i(this);
/*    */ 
/* 51 */   public RemoteBuffer explosions = new RemoteBuffer(RemoteVector3f.class, this);
/*    */ 
/*    */   public NetworkSegmentController(StateInterface paramStateInterface, ka paramka)
/*    */   {
/* 56 */     super(paramStateInterface);
/* 57 */     if (paramka.getState().getLocalAndRemoteObjectContainer().getRemoteObjects().containsKey(paramka.getId())) {
/* 58 */       System.err.println("[WARNING][SEGCONTROLLER] making a new instance of existing NT object " + paramka + ", " + paramka.getState());
/*    */     }
/* 60 */     if (paramka.getNetworkObject() != null) {
/* 61 */       System.err.println("[WARNING][SEGCONTROLLER] overwriting ship's existing NT object " + paramka + ", " + paramka.getState());
/*    */     }
/* 63 */     this.modificationBuffer = new RemoteSegmentPieceBuffer(paramka, this);
/*    */   }
/*    */ 
/*    */   public void onDelete(StateInterface paramStateInterface)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void onInit(StateInterface paramStateInterface)
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.NetworkSegmentController
 * JD-Core Version:    0.6.2
 */