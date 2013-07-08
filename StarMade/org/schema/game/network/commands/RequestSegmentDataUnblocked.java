/*  1:   */package org.schema.game.network.commands;
/*  2:   */
/*  3:   */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*  4:   */import ka;
/*  5:   */import lf;
/*  6:   */import org.schema.game.network.objects.NetworkSegmentProvider;
/*  7:   */import org.schema.game.network.objects.remote.RemoteSegmentRemoteObj;
/*  8:   */import org.schema.game.server.controller.GameServerController;
/*  9:   */import org.schema.schine.network.Command;
/* 10:   */import org.schema.schine.network.NetworkStateContainer;
/* 11:   */import org.schema.schine.network.RegisteredClientOnServer;
/* 12:   */import org.schema.schine.network.objects.remote.RemoteBuffer;
/* 13:   */import org.schema.schine.network.server.ServerStateInterface;
/* 14:   */import q;
/* 15:   */import vg;
/* 16:   */
/* 17:   */public class RequestSegmentDataUnblocked extends Command
/* 18:   */{
/* 19:   */  public RequestSegmentDataUnblocked()
/* 20:   */  {
/* 21:21 */    this.mode = 0;
/* 22:   */  }
/* 23:   */  
/* 27:   */  public void clientAnswerProcess(Object[] paramArrayOfObject, org.schema.schine.network.client.ClientStateInterface paramClientStateInterface, short paramShort) {}
/* 28:   */  
/* 31:   */  public void serverProcess(org.schema.schine.network.server.ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface arg3, short paramShort)
/* 32:   */  {
/* 33:33 */    paramShort = ((Integer)paramArrayOfObject[0]).intValue();
/* 34:   */    
/* 36:36 */    int i = ((Integer)paramArrayOfObject[1]).intValue();
/* 37:37 */    int j = ((Integer)paramArrayOfObject[2]).intValue();
/* 38:38 */    paramArrayOfObject = ((Integer)paramArrayOfObject[3]).intValue();
/* 39:   */    
/* 42:   */    try
/* 43:   */    {
/* 44:44 */      paramServerProcessor = null;
/* 45:   */      
/* 46:   */      ka localka;
/* 47:   */      
/* 48:48 */      if ((localka = (paramServerProcessor = (kc)paramServerProcessor.getClient().getLocalAndRemoteObjectContainer().getLocalObjects().get(paramShort)).a()) == null) {
/* 49:49 */        throw new IllegalArgumentException("[SERVER] Could NOT find the segment controller ID " + paramShort + ". This CAN happen, when the round gets restarted and there is still some segements in queue");
/* 50:   */      }
/* 51:   */      
/* 54:54 */      if ((paramShort = (mw)localka.getSegmentFromCache(i, j, paramArrayOfObject)) != null)
/* 55:   */      {
/* 56:56 */        paramServerProcessor = paramServerProcessor.a();
/* 57:   */        
/* 59:59 */        (paramArrayOfObject = new lf(new q(paramShort.a), paramShort.a(), paramShort.a().getId(), paramShort.g())).a = localka;
/* 60:60 */        synchronized (paramServerProcessor) {
/* 61:61 */          paramServerProcessor.segmentBuffer.add(new RemoteSegmentRemoteObj(paramArrayOfObject, paramServerProcessor));
/* 62:62 */          return; } }
/* 63:63 */      paramServerProcessor = paramServerProcessor.a();
/* 64:   */      
/* 67:67 */      ((vg)???).a().a(localka, new q(i, j, paramArrayOfObject), paramServerProcessor); return;
/* 68:   */    } catch (Exception localException) {
/* 69:69 */      
/* 70:   */      
/* 72:72 */        localException;
/* 73:   */    }
/* 74:   */  }
/* 75:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.commands.RequestSegmentDataUnblocked
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */