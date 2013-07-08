/*   1:    */package org.schema.game.network.objects.remote;
/*   2:    */
/*   3:    */import ct;
/*   4:    */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*   5:    */import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
/*   6:    */import jL;
/*   7:    */import jY;
/*   8:    */import java.io.ByteArrayInputStream;
/*   9:    */import java.io.ByteArrayOutputStream;
/*  10:    */import java.io.DataInputStream;
/*  11:    */import java.io.DataOutputStream;
/*  12:    */import java.io.PrintStream;
/*  13:    */import java.util.ArrayList;
/*  14:    */import org.schema.game.common.controller.SegmentController;
/*  15:    */import org.schema.game.common.controller.SegmentOutOfBoundsException;
/*  16:    */import org.schema.game.common.data.world.Segment;
/*  17:    */import org.schema.game.common.data.world.SegmentData;
/*  18:    */import org.schema.schine.network.NetworkStateContainer;
/*  19:    */import org.schema.schine.network.objects.NetworkObject;
/*  20:    */import org.schema.schine.network.objects.Sendable;
/*  21:    */import org.schema.schine.network.objects.remote.RemoteField;
/*  22:    */import q;
/*  23:    */import tR;
/*  24:    */import vg;
/*  25:    */
/*  26:    */public class RemoteSegmentRemoteObj extends RemoteField
/*  27:    */{
/*  28: 28 */  private static ByteArrayOutputStream tmpArrayBuffer = new ByteArrayOutputStream(102400);
/*  29: 29 */  private static DataOutputStream tmpBuffer = new DataOutputStream(tmpArrayBuffer);
/*  30:    */  
/*  31: 31 */  private static ThreadLocal threadLocal = new RemoteSegmentRemoteObj.1();
/*  32:    */  
/*  40:    */  public RemoteSegmentRemoteObj(NetworkObject paramNetworkObject)
/*  41:    */  {
/*  42: 42 */    super(new lf(), paramNetworkObject);
/*  43:    */  }
/*  44:    */  
/*  45:    */  public RemoteSegmentRemoteObj(lf paramlf, NetworkObject paramNetworkObject) {
/*  46: 46 */    super(paramlf, paramNetworkObject);
/*  47:    */  }
/*  48:    */  
/*  49: 49 */  public RemoteSegmentRemoteObj(boolean paramBoolean) { super(new lf(), paramBoolean); }
/*  50:    */  
/*  51:    */  public RemoteSegmentRemoteObj(lf paramlf, boolean paramBoolean)
/*  52:    */  {
/*  53: 53 */    super(paramlf, paramBoolean);
/*  54:    */  }
/*  55:    */  
/*  56:    */  public int byteLength()
/*  57:    */  {
/*  58: 58 */    return 0;
/*  59:    */  }
/*  60:    */  
/*  63:    */  public void fromByteStream(DataInputStream paramDataInputStream, int arg2)
/*  64:    */  {
/*  65: 65 */    ??? = paramDataInputStream.readInt();
/*  66: 66 */    int i = paramDataInputStream.readInt();
/*  67: 67 */    int j = paramDataInputStream.readInt();
/*  68: 68 */    int k = paramDataInputStream.readInt();
/*  69: 69 */    int m = paramDataInputStream.readInt();
/*  70: 70 */    Object localObject1 = (byte[])threadLocal.get();
/*  71: 71 */    paramDataInputStream.readFully((byte[])localObject1, 0, m);
/*  72: 72 */    paramDataInputStream = new ByteArrayInputStream((byte[])localObject1);
/*  73: 73 */    paramDataInputStream = new DataInputStream(paramDataInputStream);
/*  74:    */    
/*  77: 77 */    if (this.onServer) {
/*  78: 78 */      localObject1 = (Sendable)vg.a.getLocalAndRemoteObjectContainer().getLocalObjects().get(???);
/*  79:    */    } else {
/*  80: 80 */      localObject1 = (Sendable)ct.a.getLocalAndRemoteObjectContainer().getLocalObjects().get(???);
/*  81:    */    }
/*  82:    */    
/*  83: 83 */    if ((localObject1 == null) || (!(localObject1 instanceof SegmentController))) {
/*  84: 84 */      System.err.println("[ERROR][RemoteSegmantRemoteObj] could not find segmentController: " + ??? + ": " + localObject1 + " -> dumping stream");
/*  85: 85 */      Q.a.a(paramDataInputStream, m, true);
/*  86: 86 */      return;
/*  87:    */    }
/*  88:    */    
/*  90: 90 */    localObject1 = (Q)(??? = (SegmentController)localObject1).getSegmentProvider();
/*  91:    */    
/*  92: 92 */    ((lf)get()).jdField_a_of_type_Q = new q(i, j, k);
/*  93:    */    
/*  95: 95 */    int n = 0;
/*  96: 96 */    synchronized (((Q)localObject1).a()) {
/*  97: 97 */      for (int i1 = 0; i1 < ((Q)localObject1).a().size(); i1++)
/*  98:    */      {
/*  99: 99 */        if (((Segment)((Q)localObject1).a().get(i1)).jdField_a_of_type_Q.a(i, j, k))
/* 100:    */        {
/* 101:    */          mw localmw;
/* 102:102 */          if ((localmw = (mw)((Q)localObject1).a().remove(i1)).a() == null)
/* 103:    */          {
/* 105:105 */            ???.getSegmentProvider().a().assignData(localmw);
/* 106:    */          }
/* 107:    */          
/* 115:115 */          localmw.a(paramDataInputStream, m, true);
/* 116:    */          
/* 120:120 */          synchronized (((Q)localObject1).a()) {
/* 121:121 */            assert (localmw != null);
/* 122:122 */            ((Q)localObject1).a().add(localmw);
/* 123:    */          }
/* 124:124 */          n = 1;
/* 125:125 */          break;
/* 126:    */        }
/* 127:    */      }
/* 128:    */    }
/* 129:129 */    if (n == 0)
/* 130:    */    {
/* 135:135 */      Q.a.a(paramDataInputStream, m, true);
/* 136:    */    }
/* 137:    */  }
/* 138:    */  
/* 141:    */  public int toByteStream(DataOutputStream paramDataOutputStream)
/* 142:    */  {
/* 143:143 */    SegmentController localSegmentController = ((lf)get()).jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController;
/* 144:    */    
/* 148:148 */    int i = 0;
/* 149:149 */    assert (tmpArrayBuffer.size() == 0);
/* 150:    */    
/* 151:151 */    int j = 0;
/* 152:    */    Object localObject1;
/* 153:    */    Object localObject2;
/* 154:154 */    try { if ((localObject1 = localSegmentController.getSegmentBuffer().a(((lf)get()).jdField_a_of_type_Q)) != null)
/* 155:    */      {
/* 156:156 */        localObject2 = (mw)localObject1;System.currentTimeMillis();mw.d();
/* 157:157 */        assert (((mw)localObject2).jdField_a_of_type_Q.equals(((lf)get()).jdField_a_of_type_Q)) : (" serializing " + ((lf)get()).jdField_a_of_type_Q + "; toSerialize " + ((mw)localObject2).jdField_a_of_type_Q);
/* 158:158 */        i = ((mw)localObject2).a(tmpBuffer);
/* 159:159 */        j = 1;
/* 160:    */      }
/* 161:161 */    } catch (Exception localException) { localObject1 = null;
/* 162:    */      
/* 163:163 */      localException.printStackTrace();
/* 164:    */    }
/* 165:    */    
/* 167:165 */    if (j == 0)
/* 168:    */    {
/* 169:    */      try
/* 170:    */      {
/* 171:169 */        localObject2 = new q(((lf)get()).jdField_a_of_type_Q);
/* 172:170 */        localObject1 = (mw)((tR)localSegmentController.getSegmentProvider()).a(((lf)get()).jdField_a_of_type_Q.a, ((lf)get()).jdField_a_of_type_Q.b, ((lf)get()).jdField_a_of_type_Q.c);
/* 173:    */        
/* 175:173 */        assert (((mw)localObject1).jdField_a_of_type_Q.equals(((lf)get()).jdField_a_of_type_Q)) : (" REQUESTED " + ((lf)get()).jdField_a_of_type_Q + "; BUT GOT " + ((mw)localObject1).jdField_a_of_type_Q + "; ORIGINAL: " + localObject2);
/* 176:174 */        System.currentTimeMillis();mw.d();
/* 177:175 */        i = ((mw)localObject1).a(tmpBuffer);
/* 178:176 */      } catch (InterruptedException localInterruptedException) { 
/* 179:    */        
/* 182:180 */          localInterruptedException;
/* 183:    */      } catch (SegmentOutOfBoundsException localSegmentOutOfBoundsException) {
/* 184:178 */        
/* 185:    */        
/* 186:180 */          localSegmentOutOfBoundsException;
/* 187:    */      }
/* 188:    */    }
/* 189:    */    
/* 192:184 */    assert (i == tmpArrayBuffer.size()) : ("LEN " + i + "; written: " + tmpBuffer.size());
/* 193:185 */    assert (i > 0);
/* 194:186 */    paramDataOutputStream.writeInt(localSegmentController.getId());
/* 195:187 */    paramDataOutputStream.writeInt(((lf)get()).jdField_a_of_type_Q.a);
/* 196:188 */    paramDataOutputStream.writeInt(((lf)get()).jdField_a_of_type_Q.b);
/* 197:189 */    paramDataOutputStream.writeInt(((lf)get()).jdField_a_of_type_Q.c);
/* 198:190 */    paramDataOutputStream.writeInt(i);
/* 199:191 */    tmpArrayBuffer.writeTo(paramDataOutputStream);
/* 200:192 */    tmpArrayBuffer.reset();
/* 201:    */    
/* 205:197 */    return i + 20;
/* 206:    */  }
/* 207:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteSegmentRemoteObj
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */