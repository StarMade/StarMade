/*   1:    */package org.schema.schine.network.synchronization;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.bytes.ByteArrayList;
/*   4:    */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*   5:    */import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*   6:    */import java.io.DataInputStream;
/*   7:    */import java.io.PrintStream;
/*   8:    */import java.lang.reflect.Constructor;
/*   9:    */import java.util.Collection;
/*  10:    */import java.util.Iterator;
/*  11:    */import org.schema.common.util.ByteUtil;
/*  12:    */import org.schema.schine.network.ControllerInterface;
/*  13:    */import org.schema.schine.network.NetUtil;
/*  14:    */import org.schema.schine.network.StateInterface;
/*  15:    */import org.schema.schine.network.client.ClientStateInterface;
/*  16:    */import org.schema.schine.network.exception.SynchronizationException;
/*  17:    */import org.schema.schine.network.objects.NetworkObject;
/*  18:    */import org.schema.schine.network.objects.remote.RemoteBoolean;
/*  19:    */import org.schema.schine.network.objects.remote.RemoteInteger;
/*  20:    */import org.schema.schine.network.server.ServerStateInterface;
/*  21:    */import zP;
/*  22:    */
/*  23:    */public class SynchronizationReceiver
/*  24:    */{
/*  25: 25 */  public static Class construcorStateClass = StateInterface.class;
/*  26:    */  public static boolean serverDebug;
/*  27:    */  
/*  28:    */  private static void handleChanged(org.schema.schine.network.NetworkStateContainer paramNetworkStateContainer, int paramInt1, StateInterface paramStateInterface, DataInputStream paramDataInputStream, short paramShort, int paramInt2)
/*  29:    */  {
/*  30: 30 */    Int2ObjectOpenHashMap localInt2ObjectOpenHashMap = paramNetworkStateContainer.getRemoteObjects();
/*  31:    */    
/*  33: 33 */    synchronized (localObject1 = paramNetworkStateContainer.getLocalObjects())
/*  34:    */    {
/*  36: 34 */      synchronized (localInt2ObjectOpenHashMap)
/*  37:    */      {
/*  38:    */        Object localObject1;
/*  39: 37 */        NetworkObject localNetworkObject = (NetworkObject)localInt2ObjectOpenHashMap.get(paramInt1);
/*  40: 38 */        assert (localNetworkObject != null) : ("could not find entity " + paramInt1 + "; available remote entities: " + localInt2ObjectOpenHashMap);
/*  41:    */        
/*  42: 40 */        synchronized (localNetworkObject)
/*  43:    */        {
/*  44: 42 */          localObject1 = (org.schema.schine.network.objects.Sendable)((Int2ObjectOpenHashMap)localObject1).get(paramInt1);
/*  45: 43 */          localNetworkObject.id.set(Integer.valueOf(((org.schema.schine.network.objects.Sendable)localObject1).getId()));
/*  46:    */          
/*  47: 45 */          if ((localObject1.toString().startsWith("Missile")) && ((paramStateInterface instanceof ServerStateInterface))) {
/*  48: 46 */            System.err.println("SERVER MISSILE UPDATE " + localObject1);
/*  49: 47 */            serverDebug = true;
/*  50:    */          }
/*  51:    */          
/*  56: 54 */          localNetworkObject.decodeChange(paramStateInterface, paramDataInputStream, paramShort, paramNetworkStateContainer != paramStateInterface.getLocalAndRemoteObjectContainer(), paramInt2);
/*  57: 55 */          assert (localObject1 != null) : ("Object with id " + paramInt1 + " is NOT local yet " + paramNetworkStateContainer.getLocalObjects());
/*  58:    */          
/*  59: 57 */          ((org.schema.schine.network.objects.Sendable)localObject1).updateFromNetworkObject(localNetworkObject);
/*  60: 58 */          localNetworkObject.clearReceiveBuffers();
/*  61:    */          
/*  75: 73 */          serverDebug = false;
/*  76:    */        }
/*  77:    */      }
/*  78:    */      
/*  80:    */      return;
/*  81:    */    }
/*  82:    */  }
/*  83:    */  
/*  86:    */  public static void handleDeleted(org.schema.schine.network.NetworkStateContainer paramNetworkStateContainer, StateInterface paramStateInterface, Collection paramCollection)
/*  87:    */  {
/*  88: 86 */    paramCollection.clear();
/*  89: 87 */    synchronized (paramNetworkStateContainer.getLocalObjects()) { Iterator localIterator;
/*  90: 88 */      Object localObject1; Object localObject2; synchronized (paramNetworkStateContainer.getRemoteObjects())
/*  91:    */      {
/*  92: 90 */        for (localIterator = paramNetworkStateContainer.getLocalObjects().values().iterator(); localIterator.hasNext();) { localObject1 = (org.schema.schine.network.objects.Sendable)localIterator.next();
/*  93:    */          
/*  96: 94 */          if (((localObject2 = (NetworkObject)paramNetworkStateContainer.getRemoteObjects().get(((org.schema.schine.network.objects.Sendable)localObject1).getId())) != null) && (((Boolean)((NetworkObject)localObject2).markedDeleted.get()).booleanValue()))
/*  97:    */          {
/*  98: 96 */            if (((paramStateInterface instanceof ServerStateInterface)) && 
/*  99: 97 */              (!((org.schema.schine.network.objects.Sendable)localObject1).isMarkedForDeleteVolatileSent())) {
/* 100: 98 */              System.err.println("[SERVER] delete not yet sent: " + localObject1);
/* 101:    */            }
/* 102:    */            else
/* 103:    */            {
/* 104:102 */              paramCollection.add(Integer.valueOf(((org.schema.schine.network.objects.Sendable)localObject1).getId()));
/* 105:    */              
/* 106:104 */              ((NetworkObject)localObject2).onDelete(paramStateInterface);
/* 107:    */              
/* 108:106 */              ((org.schema.schine.network.objects.Sendable)localObject1).cleanUpOnEntityDelete();
/* 109:    */              
/* 110:108 */              paramNetworkStateContainer.getGhostObjects().put(((org.schema.schine.network.objects.Sendable)localObject1).getId(), new GhostSendable(System.currentTimeMillis(), (org.schema.schine.network.objects.Sendable)localObject1));
/* 111:    */            }
/* 112:    */          }
/* 113:    */        }
/* 114:    */        
/* 115:113 */        if (paramCollection.size() > 0)
/* 116:    */        {
/* 117:115 */          for (localIterator = paramCollection.iterator(); localIterator.hasNext();) { localObject1 = (Integer)localIterator.next();
/* 118:    */            
/* 121:119 */            localObject2 = paramNetworkStateContainer.removeLocal(((Integer)localObject1).intValue());
/* 122:120 */            paramNetworkStateContainer.getRemoteObjects().remove(localObject1);
/* 123:    */            
/* 124:122 */            paramStateInterface.getController().onRemoveEntity((org.schema.schine.network.objects.Sendable)localObject2);
/* 125:123 */            paramStateInterface.notifyOfRemovedObject((org.schema.schine.network.objects.Sendable)localObject2);
/* 126:124 */            System.err.println("[DELETE][" + paramStateInterface + "] Sendable " + localObject1 + "(" + localObject2 + ") Physically DELETING DONE and Notified!");
/* 127:    */          }
/* 128:    */        }
/* 129:    */      }
/* 130:    */    }
/* 131:    */    
/* 134:132 */    paramNetworkStateContainer.checkGhostObjects();
/* 135:    */  }
/* 136:    */  
/* 147:    */  private static void handleNewObject(org.schema.schine.network.NetworkStateContainer paramNetworkStateContainer, int paramInt1, int paramInt2, byte paramByte, StateInterface paramStateInterface, DataInputStream paramDataInputStream, short paramShort, boolean paramBoolean)
/* 148:    */  {
/* 149:147 */    Int2ObjectOpenHashMap localInt2ObjectOpenHashMap1 = paramNetworkStateContainer.getRemoteObjects();
/* 150:148 */    Int2ObjectOpenHashMap localInt2ObjectOpenHashMap2 = paramNetworkStateContainer.getLocalObjects();
/* 151:    */    
/* 152:150 */    org.schema.schine.network.objects.Sendable localSendable = null;
/* 153:    */    
/* 159:157 */    synchronized (localInt2ObjectOpenHashMap2) {
/* 160:158 */      synchronized (localInt2ObjectOpenHashMap1)
/* 161:    */      {
/* 162:160 */        if (localInt2ObjectOpenHashMap2.containsKey(paramInt1))
/* 163:    */        {
/* 169:167 */          localSendable = (org.schema.schine.network.objects.Sendable)localInt2ObjectOpenHashMap2.get(paramInt1);
/* 170:    */        }
/* 171:    */        else
/* 172:    */        {
/* 173:    */          Class localClass;
/* 174:    */          
/* 175:173 */          if ((localClass = NetUtil.getSendableClass(paramByte)) == null) {
/* 176:174 */            throw new NullPointerException("WRONG CLASS ID RECEIVED: " + paramByte + "; container: " + paramNetworkStateContainer.getClass());
/* 177:    */          }
/* 178:    */          
/* 188:186 */          localSendable = (org.schema.schine.network.objects.Sendable)localClass.getConstructor(new Class[] { construcorStateClass }).newInstance(new Object[] { paramStateInterface });
/* 189:    */        }
/* 190:    */        
/* 191:189 */        synchronized (localSendable) {
/* 192:190 */          long l1 = System.currentTimeMillis();
/* 193:    */          
/* 194:192 */          localSendable.initialize();
/* 195:193 */          localSendable.newNetworkObject();
/* 196:194 */          localSendable.getNetworkObject().init();
/* 197:    */          
/* 198:196 */          synchronized (paramByte = localSendable.getNetworkObject())
/* 199:    */          {
/* 205:202 */            (paramByte = NetworkObject.decode(paramStateInterface, paramDataInputStream, paramByte, paramShort, paramNetworkStateContainer != paramStateInterface.getLocalAndRemoteObjectContainer(), paramInt2)).onInit(paramStateInterface);
/* 206:    */            
/* 207:204 */            if (paramInt1 != ((Integer)paramByte.id.get()).intValue()) {
/* 208:205 */              if ((paramStateInterface instanceof ClientStateInterface)) {
/* 209:206 */                String str = "[ERROR] in " + paramStateInterface + " received changed object \n|stream for a new object. the id of the received object could \n|not be decoded because it wasnt sent (never ment to be sent). \n|the obj was probably create on the server without knowlegde of \n|this client and has therefore to be re-requested\n|[NTID(" + paramByte.id.get() + ") != receivedId[" + paramInt1 + "] received]; (SenderID: " + paramInt2 + "), \n|isSynched(" + paramBoolean + ") that was not yet created in " + paramStateInterface + ", \n|SCHEDULING RESYNC. current remotes: " + localInt2ObjectOpenHashMap1 + ", local: " + localInt2ObjectOpenHashMap2 + "; container: " + paramNetworkStateContainer.getClass();
/* 210:    */                
/* 225:222 */                throw new SynchronizationException(str);
/* 226:    */              }
/* 227:224 */              if (!$assertionsDisabled) { throw new AssertionError("NEW object not correctly en/decoded (probably en)\n on " + paramStateInterface + " received ident: " + paramInt1 + ", \nencoded: " + paramByte.id.get() + ", \nmarkedForDel: " + paramByte.markedDeleted.get() + ", \nSENDER: " + paramInt2 + ", \nsynchronized = " + paramBoolean + ", \nremotes: " + paramNetworkStateContainer);
/* 228:    */              }
/* 229:    */            }
/* 230:    */            
/* 246:243 */            if (((Integer)paramByte.id.get()).intValue() < 0) {
/* 247:244 */              System.err.println("[ERROR][CRITICAL]something fucked up: received id for new object:  " + paramByte.id.get());
/* 248:245 */              return;
/* 249:    */            }
/* 250:247 */            localSendable.setId(((Integer)paramByte.id.get()).intValue());
/* 251:    */            
/* 254:251 */            localSendable.initFromNetworkObject(paramByte);
/* 255:    */            
/* 256:253 */            if ((localSendable instanceof zP)) {
/* 257:254 */              ((zP)localSendable).initPhysics();
/* 258:    */            }
/* 259:256 */            localSendable.updateFromNetworkObject(paramByte);
/* 260:257 */            paramByte.clearReceiveBuffers();
/* 261:    */            
/* 264:261 */            paramNetworkStateContainer.putLocal(localSendable.getId(), localSendable);
/* 265:    */            
/* 267:264 */            if ((paramStateInterface instanceof ClientStateInterface))
/* 268:    */            {
/* 273:270 */              paramByte.newObject = false;
/* 274:    */            }
/* 275:    */            
/* 276:273 */            paramByte.addObserversForFields();
/* 277:    */            
/* 278:275 */            localInt2ObjectOpenHashMap1.put((Integer)paramByte.id.get(), paramByte);
/* 279:276 */            paramStateInterface.notifyOfAddedObject(localSendable);
/* 280:    */            long l2;
/* 281:278 */            if ((l2 = System.currentTimeMillis() - l1) > 10L) {
/* 282:279 */              System.err.println("[SYNC-RECEIVER] " + paramStateInterface + " DECODING OF NEW OBJECT " + localSendable + " TOOK " + l2);
/* 283:    */            }
/* 284:    */          }
/* 285:    */        }
/* 286:    */      }
/* 287:    */      
/* 289:    */      return;
/* 290:    */    }
/* 291:    */  }
/* 292:    */  
/* 295:    */  public static void update(org.schema.schine.network.NetworkStateContainer paramNetworkStateContainer, int paramInt, DataInputStream paramDataInputStream, StateInterface paramStateInterface, boolean paramBoolean1, boolean paramBoolean2, short paramShort)
/* 296:    */  {
/* 297:294 */    Int2ObjectOpenHashMap localInt2ObjectOpenHashMap1 = paramNetworkStateContainer.getRemoteObjects();
/* 298:    */    
/* 305:302 */    if (paramStateInterface.isReady())
/* 306:    */    {
/* 310:307 */      int i = paramDataInputStream.readInt();
/* 311:    */      
/* 312:309 */      if (paramBoolean2)
/* 313:    */      {
/* 315:312 */        System.err.println("[SYNCHRONIZE] FORCED UPDATE");
/* 316:    */      }
/* 317:314 */      int j = 127;
/* 318:315 */      int n; byte b; for (int k = 0; k < i; k++) {
/* 319:316 */        n = ByteUtil.a(paramDataInputStream);
/* 320:317 */        if (paramBoolean2)
/* 321:    */        {
/* 322:319 */          Object localObject1 = null;
/* 323:318 */          synchronized (paramNetworkStateContainer.getLocalObjects())
/* 324:    */          {
/* 325:320 */            synchronized (localInt2ObjectOpenHashMap1)
/* 326:    */            {
/* 327:322 */              localInt2ObjectOpenHashMap1.remove(n);
/* 328:    */            }
/* 329:    */          }
/* 330:    */        }
/* 331:    */        
/* 332:327 */        b = paramDataInputStream.readByte();
/* 333:    */        
/* 336:331 */        ??? = null;
/* 337:332 */        if ((paramBoolean1) && (localInt2ObjectOpenHashMap1.containsKey(n)) && (!paramBoolean2))
/* 338:    */        {
/* 342:337 */          handleChanged(paramNetworkStateContainer, n, paramStateInterface, paramDataInputStream, paramShort, paramInt);
/* 343:338 */        } else if ((??? = (GhostSendable)paramNetworkStateContainer.getGhostObjects().get(n)) != null)
/* 344:    */        {
/* 345:340 */          System.err.println(paramStateInterface + ": Exception: Received update for ghost object: " + n + "; ignoring update");
/* 346:341 */          ((GhostSendable)???).sendable.getNetworkObject().decodeChange(paramStateInterface, paramDataInputStream, paramShort, paramNetworkStateContainer != paramStateInterface.getLocalAndRemoteObjectContainer(), paramInt);
/* 347:342 */          System.err.println(paramStateInterface + ": Exception: Received update for ghost object: " + n + "; ignoring update: DECODED CHANGE TO GHOST OBJECT " + paramNetworkStateContainer.getGhostObjects().get(n));
/* 348:    */        } else {
/* 349:    */          try {
/* 350:345 */            handleNewObject(paramNetworkStateContainer, n, paramInt, b, paramStateInterface, paramDataInputStream, paramShort, paramBoolean1);
/* 356:    */          }
/* 357:    */          catch (NoSuchMethodException localNoSuchMethodException)
/* 358:    */          {
/* 363:358 */            
/* 364:    */            
/* 369:364 */              localNoSuchMethodException.printStackTrace();throw new RuntimeException("the object " + b + " does not provide an acceptable contructor to create an instance from a remote object");
/* 370:    */          }
/* 371:    */        }
/* 372:367 */        paramNetworkStateContainer.debugReceivedClasses.add(b);
/* 373:    */      }
/* 374:    */      
/* 375:370 */      if (paramBoolean2) {
/* 376:    */        Int2ObjectOpenHashMap localInt2ObjectOpenHashMap2;
/* 377:372 */        if (!(localInt2ObjectOpenHashMap2 = paramNetworkStateContainer.getLocalObjects()).keySet().equals(localInt2ObjectOpenHashMap1.keySet())) {
/* 378:    */          try {
/* 379:374 */            System.err.println("LOCAL : " + localInt2ObjectOpenHashMap2);
/* 380:375 */            System.err.println("REMOTE: " + localInt2ObjectOpenHashMap1);
/* 381:376 */            throw new SynchronizationException("[CLIENT] " + paramStateInterface + " invalid synchronization state: local and remote objects differ");
/* 382:377 */          } catch (SynchronizationException localSynchronizationException) { localSynchronizationException;
/* 383:    */            
/* 384:379 */            if (!$assertionsDisabled) { throw new AssertionError();
/* 385:    */            }
/* 386:    */          }
/* 387:    */        }
/* 388:    */      }
/* 389:384 */      if (paramDataInputStream.available() > 0) {
/* 390:385 */        System.err.println("[ERROR][CRITICAL] leftover bytes: " + paramDataInputStream.available() + " last decoded class: " + NetUtil.getSendableClass(b));
/* 391:386 */        for (int m = 0; m < paramNetworkStateContainer.debugReceivedClasses.size(); m++) {
/* 392:387 */          System.err.println("[DEBUGINFO] decoded class #1: " + NetUtil.getSendableClass(paramNetworkStateContainer.debugReceivedClasses.get(m).byteValue()));
/* 393:    */        }
/* 394:389 */        m = paramDataInputStream.readInt();
/* 395:390 */        n = paramDataInputStream.readByte();
/* 396:391 */        System.err.println("[DEBUGINFO] into container: " + paramNetworkStateContainer.getClass());
/* 397:392 */        System.err.println("[DEBUGINFO] decoded next ID: " + m + "; class " + n + " = " + NetUtil.getSendableClass(n));
/* 398:393 */        System.err.println("[DEBUGINFO] if class and object id are sane, this means there was a wrong update size transferred");
/* 399:394 */        System.err.println("[DEBUGINFO] if not, an object did not decode correctly");
/* 400:    */      }
/* 401:    */    }
/* 402:    */    
/* 403:398 */    paramNetworkStateContainer.debugReceivedClasses.clear();
/* 404:    */  }
/* 405:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.synchronization.SynchronizationReceiver
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */