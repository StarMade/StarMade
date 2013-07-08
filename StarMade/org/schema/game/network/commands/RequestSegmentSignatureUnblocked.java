/*  1:   */package org.schema.game.network.commands;
/*  2:   */
/*  3:   */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*  4:   */import java.io.PrintStream;
/*  5:   */import ka;
/*  6:   */import lf;
/*  7:   */import org.schema.game.network.objects.NetworkSegmentProvider;
/*  8:   */import org.schema.game.network.objects.remote.RemoteSegmentSignature;
/*  9:   */import org.schema.game.server.controller.GameServerController;
/* 10:   */import org.schema.schine.network.Command;
/* 11:   */import org.schema.schine.network.NetworkStateContainer;
/* 12:   */import org.schema.schine.network.RegisteredClientOnServer;
/* 13:   */import org.schema.schine.network.client.ClientStateInterface;
/* 14:   */import org.schema.schine.network.objects.remote.RemoteBuffer;
/* 15:   */import vg;
/* 16:   */
/* 17:   */public class RequestSegmentSignatureUnblocked extends Command
/* 18:   */{
/* 19:   */  public RequestSegmentSignatureUnblocked()
/* 20:   */  {
/* 21:21 */    this.mode = 0;
/* 22:   */  }
/* 23:   */  
/* 27:   */  public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort) {}
/* 28:   */  
/* 32:   */  public void serverProcess(org.schema.schine.network.server.ServerProcessor paramServerProcessor, Object[] arg2, org.schema.schine.network.server.ServerStateInterface paramServerStateInterface, short paramShort)
/* 33:   */  {
/* 34:34 */    int i = ((Integer)???[0]).intValue();
/* 35:   */    
/* 38:38 */    int j = ((Integer)???[1]).intValue();
/* 39:39 */    int k = ((Integer)???[2]).intValue();
/* 40:40 */    ??? = ((Integer)???[3]).intValue();
/* 41:   */    
/* 43:   */    try
/* 44:   */    {
/* 45:   */      kc localkc;
/* 46:   */      
/* 48:   */      ka localka;
/* 49:   */      
/* 51:51 */      if ((localka = (localkc = (kc)paramServerProcessor.getClient().getLocalAndRemoteObjectContainer().getLocalObjects().get(i)).a()) == null) {
/* 52:52 */        throw new IllegalArgumentException("[SERVER] Could NOT find the segment controller ID " + i + ". This CAN happen, when the round gets restarted and there is still some segements in queue");
/* 53:   */      }
/* 54:   */      
/* 55:   */      mw localmw;
/* 56:56 */      if ((localmw = (mw)localka.getSegmentFromCache(j, k, ???)) != null) {
/* 57:57 */        createReturnToClient(paramServerStateInterface, paramServerProcessor, paramShort, new Object[] { Long.valueOf(localmw.a()), Boolean.valueOf(localmw.g()) });
/* 58:58 */        paramServerProcessor = localkc.a();
/* 59:59 */        System.currentTimeMillis();mw.d();
/* 60:   */        
/* 61:61 */        synchronized (paramServerProcessor) {
/* 62:62 */          paramServerProcessor.signatureBuffer.add(new RemoteSegmentSignature(new lf(new q(localmw.a), localmw.a(), localmw.a().getId(), localmw.g()), paramServerProcessor));
/* 63:   */          
/* 65:65 */          return; } }
/* 66:66 */      paramServerProcessor = localkc.a();
/* 67:   */      
/* 70:70 */      ((vg)paramServerStateInterface).a().b(localka, new q(j, k, ???), paramServerProcessor); return;
/* 71:   */    }
/* 72:   */    catch (Exception localException) {
/* 73:73 */      
/* 74:   */      
/* 76:76 */        localException.printStackTrace();System.err.println("Exception catched for ID: " + i);
/* 77:   */    }
/* 78:   */  }
/* 79:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.commands.RequestSegmentSignatureUnblocked
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */