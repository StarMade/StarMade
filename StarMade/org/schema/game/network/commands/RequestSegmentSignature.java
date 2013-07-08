/*  1:   */package org.schema.game.network.commands;
/*  2:   */
/*  3:   */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*  4:   */import org.schema.schine.network.Command;
/*  5:   */import org.schema.schine.network.NetworkStateContainer;
/*  6:   */import org.schema.schine.network.client.ClientStateInterface;
/*  7:   */import org.schema.schine.network.server.ServerProcessor;
/*  8:   */import org.schema.schine.network.server.ServerStateInterface;
/*  9:   */import tR;
/* 10:   */import vg;
/* 11:   */
/* 12:   */public class RequestSegmentSignature extends Command
/* 13:   */{
/* 14:   */  public RequestSegmentSignature()
/* 15:   */  {
/* 16:16 */    this.mode = 1;
/* 17:   */  }
/* 18:   */  
/* 21:   */  public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort)
/* 22:   */  {
/* 23:23 */    paramClientStateInterface.arrivedReturn(paramShort, paramArrayOfObject);
/* 24:   */  }
/* 25:   */  
/* 29:   */  public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
/* 30:   */  {
/* 31:31 */    Object localObject = (vg)paramServerStateInterface;
/* 32:   */    
/* 33:33 */    int i = ((Integer)paramArrayOfObject[0]).intValue();
/* 34:   */    
/* 35:35 */    int j = ((Integer)paramArrayOfObject[1]).intValue();
/* 36:36 */    int k = ((Integer)paramArrayOfObject[2]).intValue();
/* 37:37 */    paramArrayOfObject = ((Integer)paramArrayOfObject[3]).intValue();
/* 38:   */    
/* 39:   */    try
/* 40:   */    {
/* 41:41 */      if ((localObject = (org.schema.game.common.controller.SegmentController)((vg)localObject).getLocalAndRemoteObjectContainer().getLocalObjects().get(i)) == null) {
/* 42:42 */        throw new IllegalArgumentException("[SERVER] Could NOT find the segment controller ID " + i + ". This CAN happen, when the round gets restarted and there is still some segements in queue");
/* 43:   */      }
/* 44:   */      
/* 45:   */      mw localmw;
/* 46:   */      
/* 47:47 */      if ((localmw = (mw)((org.schema.game.common.controller.SegmentController)localObject).getSegmentFromCache(j, k, paramArrayOfObject)) != null) {
/* 48:48 */        System.currentTimeMillis();mw.d();
/* 49:   */        
/* 50:50 */        createReturnToClient(paramServerStateInterface, paramServerProcessor, paramShort, new Object[] { Long.valueOf(localmw.a()), Boolean.valueOf(localmw.g()) });return;
/* 51:   */      }
/* 52:   */      
/* 55:55 */      paramArrayOfObject = (mw)((tR)((org.schema.game.common.controller.SegmentController)localObject).getSegmentProvider()).a(j, k, paramArrayOfObject);System.currentTimeMillis();mw.d();
/* 56:   */      
/* 57:57 */      createReturnToClient(paramServerStateInterface, paramServerProcessor, paramShort, new Object[] { Long.valueOf(paramArrayOfObject.a()), Boolean.valueOf(paramArrayOfObject.g()) }); return;
/* 58:   */    }
/* 59:   */    catch (Exception localException)
/* 60:   */    {
/* 61:61 */      createReturnToClient(paramServerStateInterface, paramServerProcessor, paramShort, new Object[] { Integer.valueOf(-1), Boolean.valueOf(true) });
/* 62:   */    }
/* 63:   */  }
/* 64:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.commands.RequestSegmentSignature
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */