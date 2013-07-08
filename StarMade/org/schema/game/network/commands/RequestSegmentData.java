/*   1:    */package org.schema.game.network.commands;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*   4:    */import jL;
/*   5:    */import java.io.DataOutputStream;
/*   6:    */import java.io.PrintStream;
/*   7:    */import java.util.Map;
/*   8:    */import org.schema.game.common.data.world.DeserializationException;
/*   9:    */import org.schema.schine.network.Command;
/*  10:    */import org.schema.schine.network.NetworkStateContainer;
/*  11:    */import org.schema.schine.network.RegisteredClientOnServer;
/*  12:    */import org.schema.schine.network.server.ServerStateInterface;
/*  13:    */import tR;
/*  14:    */import vg;
/*  15:    */
/*  16:    */public class RequestSegmentData extends Command
/*  17:    */{
/*  18:    */  protected static final boolean USE_IMMEDIATE_REQUEST = true;
/*  19:    */  
/*  20:    */  public RequestSegmentData()
/*  21:    */  {
/*  22: 22 */    this.mode = 1;
/*  23:    */  }
/*  24:    */  
/*  31:    */  public void clientAnswerProcess(Object[] paramArrayOfObject, org.schema.schine.network.client.ClientStateInterface paramClientStateInterface, short paramShort)
/*  32:    */  {
/*  33: 33 */    paramArrayOfObject = ((ct)paramClientStateInterface).a(paramShort);
/*  34:    */    
/*  36: 36 */    assert (paramArrayOfObject != null) : ("not found: " + paramShort + ": " + ((ct)paramClientStateInterface).a());
/*  37:    */    
/*  38: 38 */    assert ((paramArrayOfObject.g()) || (paramArrayOfObject.a() != null));
/*  39:    */    
/*  41:    */    try
/*  42:    */    {
/*  43: 43 */      paramArrayOfObject.a(paramClientStateInterface.getProcessor().getIn(), -1, false);
/*  44: 44 */      System.currentTimeMillis();mw.d();
/*  45: 45 */    } catch (DeserializationException localDeserializationException) { 
/*  46:    */      
/*  48: 48 */        localDeserializationException.printStackTrace();System.err.println("CRITICAL: SERVER PROVIDED CORRUPT SEGEMNT");
/*  49:    */    }
/*  50: 50 */    if ((paramClientStateInterface.getProcessor().getIn().available() > 0) && 
/*  51: 51 */      (!$assertionsDisabled)) { throw new AssertionError(" Failed to fully deserialize " + paramArrayOfObject.a + "; still available: " + paramClientStateInterface.getProcessor().getIn().available());
/*  52:    */    }
/*  53:    */  }
/*  54:    */  
/*  66:    */  public void serverProcess(org.schema.schine.network.server.ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface arg3, short arg4)
/*  67:    */  {
/*  68: 68 */    paramArrayOfObject = new org.schema.schine.network.Header(RequestSegmentData.class, ???.getId(), paramServerProcessor.getClient().getId(), ???, (byte)123);
/*  69:    */    
/*  72: 72 */    ??? = (vg)???;
/*  73:    */    
/*  74: 74 */    ??? = paramServerProcessor.getIn().readInt();
/*  75: 75 */    int i = paramServerProcessor.getIn().readInt();
/*  76: 76 */    int j = paramServerProcessor.getIn().readInt();
/*  77: 77 */    int k = paramServerProcessor.getIn().readInt();
/*  78:    */    
/*  84: 84 */    if ((??? = (org.schema.game.common.controller.SegmentController)???.getLocalAndRemoteObjectContainer().getLocalObjects().get(???)) != null) {
/*  85: 85 */      if (???.getSegmentBuffer().a(i, j, k)) {
/*  86: 86 */        ??? = (mw)???.getSegmentBuffer().a(i, j, k);
/*  87:    */        
/*  89: 89 */        synchronized (paramServerProcessor.getLock()) {
/*  90: 90 */          assert ((???.a.a == i) && (???.a.b == j) && (???.a.c == k)) : (" serializing " + i + ", " + j + ", " + k + "; toSerialize " + ???.a);
/*  91:    */          
/*  92: 92 */          paramArrayOfObject.write(paramServerProcessor.getOut());
/*  93:    */          
/*  94: 94 */          ???.a(paramServerProcessor.getOut());
/*  95: 95 */          paramServerProcessor.flushDoubleOutBuffer();
/*  96: 96 */          return;
/*  97:    */        } }
/*  98: 98 */      ??? = (mw)((tR)???.getSegmentProvider()).a(i, j, i);
/*  99:    */      
/* 104:104 */      synchronized (paramServerProcessor.getLock()) {
/* 105:105 */        assert ((???.a.a == i) && (???.a.b == j) && (???.a.c == k)) : (" serializing " + i + ", " + j + ", " + k + "; toSerialize " + ???.a);
/* 106:    */        
/* 107:107 */        paramArrayOfObject.write(paramServerProcessor.getOut());
/* 108:108 */        ???.a(paramServerProcessor.getOut());
/* 109:109 */        paramServerProcessor.flushDoubleOutBuffer();
/* 110:110 */        return;
/* 111:    */      }
/* 112:    */    }
/* 113:113 */    System.err.println("[SERVER][ERROR] Exception: Requested SegmentController not found " + ??? + ". sending back dummy");
/* 114:    */    
/* 117:117 */    synchronized (paramServerProcessor.getLock()) {
/* 118:118 */      ??? = new mw(null);
/* 119:119 */      paramArrayOfObject.write(paramServerProcessor.getOut());
/* 120:120 */      ???.a(paramServerProcessor.getOut());
/* 121:121 */      paramServerProcessor.flushDoubleOutBuffer(); return;
/* 122:    */    }
/* 123:    */  }
/* 124:    */  
/* 131:    */  public void writeAndCommitParametriziedCommand(Object[] paramArrayOfObject, int paramInt1, int arg3, short paramShort, org.schema.schine.network.NetworkProcessor paramNetworkProcessor)
/* 132:    */  {
/* 133:133 */    assert ((paramNetworkProcessor.getState() instanceof ct));
/* 134:    */    
/* 135:135 */    paramArrayOfObject = new org.schema.schine.network.Header(getId(), paramShort, (byte)123);
/* 136:136 */    paramInt1 = null;
/* 137:    */    
/* 138:138 */    synchronized (((ct)paramNetworkProcessor.getState()).a())
/* 139:    */    {
/* 140:140 */      paramInt1 = (mw)((ct)paramNetworkProcessor.getState()).a().get(Short.valueOf(paramShort));
/* 141:    */    }
/* 142:    */    
/* 144:144 */    assert (paramInt1 != null);
/* 145:    */    
/* 147:147 */    synchronized (paramNetworkProcessor.getLock())
/* 148:    */    {
/* 149:149 */      paramArrayOfObject.write(paramNetworkProcessor.getOut());
/* 150:    */      
/* 151:151 */      paramNetworkProcessor.getOut().writeInt(paramInt1.a().getId());
/* 152:    */      
/* 153:153 */      System.currentTimeMillis();mw.d();
/* 154:154 */      paramNetworkProcessor.getOut().writeInt(paramInt1.a.a);
/* 155:155 */      paramNetworkProcessor.getOut().writeInt(paramInt1.a.b);
/* 156:156 */      paramNetworkProcessor.getOut().writeInt(paramInt1.a.c);
/* 157:157 */      paramNetworkProcessor.flushDoubleOutBuffer(); return;
/* 158:    */    }
/* 159:    */  }
/* 160:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.commands.RequestSegmentData
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */