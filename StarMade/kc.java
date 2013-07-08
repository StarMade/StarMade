/*   1:    */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*   2:    */import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*   3:    */import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
/*   4:    */import java.io.PrintStream;
/*   5:    */import java.util.HashMap;
/*   6:    */import org.schema.game.common.controller.SegmentController;
/*   7:    */import org.schema.game.common.controller.elements.ManagerContainer;
/*   8:    */import org.schema.game.common.data.element.ControlElementMap;
/*   9:    */import org.schema.game.network.objects.NetworkSegmentProvider;
/*  10:    */import org.schema.game.network.objects.remote.RemoteControlStructure;
/*  11:    */import org.schema.game.network.objects.remote.RemoteControlStructureBuffer;
/*  12:    */import org.schema.game.network.objects.remote.RemoteInventory;
/*  13:    */import org.schema.game.network.objects.remote.RemoteInventoryBuffer;
/*  14:    */import org.schema.game.network.objects.remote.RemoteSegmentRemoteObj;
/*  15:    */import org.schema.game.network.objects.remote.RemoteSegmentSignature;
/*  16:    */import org.schema.game.server.controller.GameServerController;
/*  17:    */import org.schema.schine.network.NetworkStateContainer;
/*  18:    */import org.schema.schine.network.StateInterface;
/*  19:    */import org.schema.schine.network.objects.NetworkObject;
/*  20:    */import org.schema.schine.network.objects.Sendable;
/*  21:    */import org.schema.schine.network.objects.remote.RemoteBoolean;
/*  22:    */import org.schema.schine.network.objects.remote.RemoteBuffer;
/*  23:    */import org.schema.schine.network.objects.remote.RemoteInteger;
/*  24:    */import org.schema.schine.network.objects.remote.RemoteVector3i;
/*  25:    */import org.schema.schine.network.server.ServerStateInterface;
/*  26:    */
/*  32:    */public class kc
/*  33:    */  implements Sendable
/*  34:    */{
/*  35:    */  private ka jdField_a_of_type_Ka;
/*  36:    */  private StateInterface jdField_a_of_type_OrgSchemaSchineNetworkStateInterface;
/*  37:    */  private final boolean jdField_a_of_type_Boolean;
/*  38:    */  private NetworkSegmentProvider jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider;
/*  39: 39 */  private boolean b = false;
/*  40:    */  
/*  43: 43 */  private int jdField_a_of_type_Int = -123123;
/*  44:    */  
/*  45:    */  private boolean c;
/*  46:    */  
/*  47:    */  private boolean d;
/*  48: 48 */  private boolean e = true;
/*  49:    */  
/*  50:    */  public kc(StateInterface paramStateInterface) {
/*  51: 51 */    this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface = paramStateInterface;
/*  52: 52 */    this.jdField_a_of_type_Boolean = (paramStateInterface instanceof ServerStateInterface);
/*  53:    */  }
/*  54:    */  
/*  56:    */  public void cleanUpOnEntityDelete() {}
/*  57:    */  
/*  59:    */  public int getId()
/*  60:    */  {
/*  61: 61 */    return this.jdField_a_of_type_Int;
/*  62:    */  }
/*  63:    */  
/*  64:    */  public final NetworkSegmentProvider a()
/*  65:    */  {
/*  66: 66 */    return this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider;
/*  67:    */  }
/*  68:    */  
/*  71:    */  public final ka a()
/*  72:    */  {
/*  73: 73 */    if (this.jdField_a_of_type_Ka == null) {
/*  74: 74 */      this.jdField_a_of_type_Ka = ((ka)this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface.getLocalAndRemoteObjectContainer().getLocalObjects().get(getId()));
/*  75:    */    }
/*  76: 76 */    return this.jdField_a_of_type_Ka;
/*  77:    */  }
/*  78:    */  
/*  80: 80 */  public StateInterface getState() { return this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface; }
/*  81:    */  
/*  82:    */  private void a(NetworkSegmentProvider paramNetworkSegmentProvider) {
/*  83: 83 */    synchronized (paramNetworkSegmentProvider.signatureBuffer.getReceiveBuffer()) {
/*  84: 84 */      if (!paramNetworkSegmentProvider.signatureBuffer.getReceiveBuffer().isEmpty())
/*  85:    */      {
/*  87: 87 */        for (int i = 0; i < paramNetworkSegmentProvider.signatureBuffer.getReceiveBuffer().size(); i++)
/*  88:    */        {
/*  89: 89 */          lf locallf = (lf)((RemoteSegmentSignature)paramNetworkSegmentProvider.signatureBuffer.getReceiveBuffer().get(i)).get();
/*  90:    */          
/*  92: 92 */          synchronized (localObjectOpenHashSet = ((Q)a().getSegmentProvider()).b()) {
/*  93:    */            ObjectOpenHashSet localObjectOpenHashSet;
/*  94: 93 */            if ((!f) && (locallf.jdField_a_of_type_Q == null)) throw new AssertionError();
/*  95: 94 */            localObjectOpenHashSet.add(locallf);
/*  96:    */          }
/*  97:    */        }
/*  98:    */      }
/*  99: 98 */      return;
/* 100:    */    }
/* 101:    */  }
/* 102:    */  
/* 104:    */  public void initFromNetworkObject(NetworkObject paramNetworkObject)
/* 105:    */  {
/* 106:105 */    setId(((Integer)this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.id.get()).intValue());
/* 107:    */  }
/* 108:    */  
/* 111:    */  public void initialize() {}
/* 112:    */  
/* 114:    */  public final boolean a()
/* 115:    */  {
/* 116:115 */    return (this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider != null) && (((Boolean)this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.connectionReady.get()).booleanValue());
/* 117:    */  }
/* 118:    */  
/* 119:    */  public boolean isMarkedForDeleteVolatile()
/* 120:    */  {
/* 121:120 */    return this.c;
/* 122:    */  }
/* 123:    */  
/* 124:    */  public boolean isMarkedForDeleteVolatileSent()
/* 125:    */  {
/* 126:125 */    return this.d;
/* 127:    */  }
/* 128:    */  
/* 129:    */  public boolean isOnServer()
/* 130:    */  {
/* 131:130 */    return this.jdField_a_of_type_Boolean;
/* 132:    */  }
/* 133:    */  
/* 135:    */  public void newNetworkObject()
/* 136:    */  {
/* 137:136 */    this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider = new NetworkSegmentProvider(this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface, this);
/* 138:137 */    if (((this.jdField_a_of_type_Ka instanceof ld)) && ((((ld)this.jdField_a_of_type_Ka).a() instanceof mh))) {
/* 139:138 */      this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.invetoryBuffer = new RemoteInventoryBuffer(((ld)this.jdField_a_of_type_Ka).a(), this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider);return;
/* 140:    */    }
/* 141:140 */    this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.invetoryBuffer = new RemoteInventoryBuffer(null, this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider);
/* 142:    */  }
/* 143:    */  
/* 144:    */  public final void a()
/* 145:    */  {
/* 146:145 */    this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.connectionReady.set(Boolean.valueOf(true));
/* 147:    */  }
/* 148:    */  
/* 149:    */  public void setId(int paramInt)
/* 150:    */  {
/* 151:150 */    this.jdField_a_of_type_Int = paramInt;
/* 152:    */  }
/* 153:    */  
/* 154:    */  public void setMarkedForDeleteVolatile(boolean paramBoolean)
/* 155:    */  {
/* 156:155 */    this.c = paramBoolean;
/* 157:    */  }
/* 158:    */  
/* 160:    */  public void setMarkedForDeleteVolatileSent(boolean paramBoolean)
/* 161:    */  {
/* 162:161 */    this.d = paramBoolean;
/* 163:    */  }
/* 164:    */  
/* 168:    */  public final void a(ka paramka)
/* 169:    */  {
/* 170:169 */    this.jdField_a_of_type_Ka = paramka;
/* 171:    */  }
/* 172:    */  
/* 175:    */  public void updateFromNetworkObject(NetworkObject paramNetworkObject)
/* 176:    */  {
/* 177:176 */    if (a() == null) {
/* 178:177 */      System.err.println("[SendableSegmentProvider] no longer updating: missing segment controller: " + getId() + ": " + getState());
/* 179:178 */      return;
/* 180:    */    }
/* 181:    */    
/* 182:181 */    a((NetworkSegmentProvider)paramNetworkObject);
/* 183:182 */    setId(((Integer)this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.id.get()).intValue());
/* 184:    */    
/* 185:184 */    if (isOnServer()) { kc localkc;
/* 186:185 */      NetworkSegmentProvider localNetworkSegmentProvider; mw localmw2; for (int i = 0; i < ((NetworkSegmentProvider)paramNetworkObject).signatureRequestBuffer.getReceiveBuffer().size(); i++) {
/* 187:186 */        q localq = ((RemoteVector3i)((NetworkSegmentProvider)paramNetworkObject).signatureRequestBuffer.getReceiveBuffer().get(i)).getVector();localkc = this; try { mw localmw1; if ((localmw1 = (mw)localkc.a().getSegmentFromCache(localq.jdField_a_of_type_Int, localq.b, localq.c)) != null) { localNetworkSegmentProvider = localkc.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider;System.currentTimeMillis();mw.d(); synchronized (localNetworkSegmentProvider) { localNetworkSegmentProvider.signatureBuffer.add(new RemoteSegmentSignature(new lf(new q(localmw1.jdField_a_of_type_Q), localmw1.a(), localmw1.a().getId(), localmw1.g()), localNetworkSegmentProvider)); } } localNetworkSegmentProvider = localkc.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider;((vg)localkc.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface).a().b(localkc.a(), new q((q)???), localNetworkSegmentProvider); } catch (Exception localException1) { localmw2 = null;localException1.printStackTrace();System.err.println("[SendableSegmentProvider] Exception catched for ID: " + localkc.a() + "; if null, the segmentcontroller has probably been removed (id for both: " + localkc.getId() + ")");
/* 188:    */        } }
/* 189:188 */      for (i = 0; i < ((NetworkSegmentProvider)paramNetworkObject).segmentRequestBuffer.getReceiveBuffer().size(); i++) {
/* 190:189 */        ??? = ((RemoteVector3i)((NetworkSegmentProvider)paramNetworkObject).segmentRequestBuffer.getReceiveBuffer().get(i)).getVector();localkc = this; try { if ((localmw2 = (mw)localkc.a().getSegmentFromCache(((q)???).jdField_a_of_type_Int, ((q)???).b, ((q)???).c)) != null) { localNetworkSegmentProvider = localkc.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider;(??? = new lf(new q(localmw2.jdField_a_of_type_Q), localmw2.a(), localmw2.a().getId(), localmw2.g())).jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController = localkc.a(); synchronized (localNetworkSegmentProvider) { localNetworkSegmentProvider.segmentBuffer.add(new RemoteSegmentRemoteObj((lf)???, localNetworkSegmentProvider)); } } localNetworkSegmentProvider = localObject1.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider;((vg)localObject1.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface).a().a(localObject1.a(), new q((q)???), localNetworkSegmentProvider); } catch (Exception localException2) { ??? = null;localException2.printStackTrace();
/* 191:    */        }
/* 192:    */      }
/* 193:    */    }
/* 194:193 */    if ((isOnServer()) && (!this.b) && (((Boolean)this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.requestedInitialControlMap.get()).booleanValue())) {
/* 195:194 */      this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.initialControlMap.add(new RemoteControlStructure(this, isOnServer()));
/* 196:195 */      this.b = true;
/* 197:    */    }
/* 198:    */    
/* 200:199 */    if ((!isOnServer()) && ((this.jdField_a_of_type_Ka instanceof ld)) && ((((ld)this.jdField_a_of_type_Ka).a() instanceof mh))) {
/* 201:200 */      ManagerContainer localManagerContainer = ((ld)this.jdField_a_of_type_Ka).a();
/* 202:201 */      if (this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.invetoryBuffer.getReceiveBuffer().size() > 0) {
/* 203:202 */        System.err.println("[CLIENT] RECEIVED INITIAL INVETORY LIST FOR " + this.jdField_a_of_type_Ka);
/* 204:203 */        localManagerContainer.handleInventoryFromNT(this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.invetoryBuffer);
/* 205:    */      }
/* 206:    */    }
/* 207:    */    
/* 208:207 */    if ((isOnServer()) && (((Boolean)((NetworkSegmentProvider)paramNetworkObject).signalDelete.get()).booleanValue())) {
/* 209:208 */      setMarkedForDeleteVolatile(true);
/* 210:    */    }
/* 211:    */  }
/* 212:    */  
/* 270:    */  public void updateLocal(xq paramxq)
/* 271:    */  {
/* 272:271 */    if ((a()) && (this.e))
/* 273:    */    {
/* 275:274 */      synchronized ((paramxq = ((ld)this.jdField_a_of_type_Ka).a()).getInventories()) {
/* 276:275 */        for (mf localmf : paramxq.getInventories().values()) {
/* 277:276 */          this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.invetoryBuffer.add(new RemoteInventory(localmf, paramxq, true, this.jdField_a_of_type_Boolean));
/* 278:    */        }
/* 279:    */      }
/* 280:279 */      this.e = false;
/* 281:    */    }
/* 282:    */  }
/* 283:    */  
/* 284:    */  public final void b() {
/* 285:284 */    if (a()) {
/* 286:285 */      this.e = true;
/* 287:286 */      if (isOnServer()) {
/* 288:287 */        ((vg)getState()).a(this);
/* 289:    */      }
/* 290:    */    }
/* 291:    */  }
/* 292:    */  
/* 293:    */  public void updateToFullNetworkObject()
/* 294:    */  {
/* 295:294 */    this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.id.set(Integer.valueOf(getId()));
/* 296:295 */    if (isOnServer()) {
/* 297:296 */      this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.connectionReady.set(Boolean.valueOf(true));
/* 298:    */    }
/* 299:    */  }
/* 300:    */  
/* 301:    */  public void updateToNetworkObject()
/* 302:    */  {
/* 303:302 */    this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.id.set(Integer.valueOf(getId()));
/* 304:303 */    if (isOnServer()) {
/* 305:304 */      this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.connectionReady.set(Boolean.valueOf(true));
/* 306:    */    }
/* 307:    */  }
/* 308:    */  
/* 309:    */  public final void c() {
/* 310:309 */    if (((this.jdField_a_of_type_Ka instanceof ld)) && ((((ld)this.jdField_a_of_type_Ka).a() instanceof mh)))
/* 311:    */    {
/* 312:311 */      ((x)getState().getController()).a(this.jdField_a_of_type_Ka.getId());
/* 313:    */    }
/* 314:    */  }
/* 315:    */  
/* 316:    */  public final void d()
/* 317:    */  {
/* 318:317 */    a().getControlElementMap().setFlagRequested(true);
/* 319:318 */    this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.requestedInitialControlMap.forceClientUpdates();
/* 320:319 */    this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.requestedInitialControlMap.set(Boolean.valueOf(true), true);
/* 321:    */  }
/* 322:    */  
/* 324:    */  public void destroyPersistent() {}
/* 325:    */  
/* 327:    */  public boolean isMarkedForPermanentDelete()
/* 328:    */  {
/* 329:328 */    return false;
/* 330:    */  }
/* 331:    */  
/* 333:    */  public void markedForPermanentDelete(boolean paramBoolean) {}
/* 334:    */  
/* 336:    */  public boolean isUpdatable()
/* 337:    */  {
/* 338:337 */    return true;
/* 339:    */  }
/* 340:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     kc
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */