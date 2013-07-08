/*   1:    */import com.bulletphysics.linearmath.Transform;
/*   2:    */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*   3:    */import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*   4:    */import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*   5:    */import java.io.DataInputStream;
/*   6:    */import java.io.DataOutputStream;
/*   7:    */import java.io.PrintStream;
/*   8:    */import java.util.ArrayList;
/*   9:    */import java.util.Collection;
/*  10:    */import java.util.HashSet;
/*  11:    */import java.util.Iterator;
/*  12:    */import javax.vecmath.Vector3f;
/*  13:    */import org.schema.game.common.data.element.meta.MetaObject;
/*  14:    */import org.schema.game.common.data.element.meta.MetaObjectManager;
/*  15:    */import org.schema.game.common.data.player.inventory.NoSlotFreeException;
/*  16:    */import org.schema.game.common.data.world.Universe;
/*  17:    */import org.schema.game.network.objects.remote.RemoteInventoryMultMod;
/*  18:    */import org.schema.game.network.objects.remote.RemoteInventoryMultModBuffer;
/*  19:    */import org.schema.schine.network.NetworkStateContainer;
/*  20:    */import org.schema.schine.network.StateInterface;
/*  21:    */import org.schema.schine.network.objects.NetworkObject;
/*  22:    */import org.schema.schine.network.objects.Sendable;
/*  23:    */import org.schema.schine.network.objects.remote.RemoteIntArray;
/*  24:    */import org.schema.schine.network.objects.remote.RemoteIntArrayBuffer;
/*  25:    */import org.schema.schine.network.server.ServerStateInterface;
/*  26:    */
/*  40:    */public abstract class mf
/*  41:    */  implements Ak
/*  42:    */{
/*  43:    */  q jdField_a_of_type_Q;
/*  44:    */  protected final Int2ObjectOpenHashMap a;
/*  45:    */  mh jdField_a_of_type_Mh;
/*  46:    */  
/*  47:    */  public mf(mh parammh, q paramq)
/*  48:    */  {
/*  49: 49 */    this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap = new Int2ObjectOpenHashMap();
/*  50: 50 */    this.jdField_a_of_type_Mh = parammh;
/*  51: 51 */    this.jdField_a_of_type_Q = paramq;
/*  52:    */  }
/*  53:    */  
/*  59:    */  public final boolean b()
/*  60:    */  {
/*  61: 61 */    return a((short)1) >= 0;
/*  62:    */  }
/*  63:    */  
/*  66:    */  public void fromTagStructure(Ah paramAh)
/*  67:    */  {
/*  68: 68 */    System.err.println("CLEARING INVENTORY (FROM TAG) " + this.jdField_a_of_type_Q + "; " + this.jdField_a_of_type_Mh);
/*  69: 69 */    this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.clear();
/*  70:    */    
/*  72: 72 */    if ("inventory".equals(paramAh.a()))
/*  73:    */    {
/*  75: 75 */      Object localObject1 = (paramAh = (Ah[])paramAh.a())[0];
/*  76: 76 */      Ah[] arrayOfAh = paramAh[1];
/*  77: 77 */      paramAh = paramAh[2];
/*  78: 78 */      localObject1 = (Ah[])((Ah)localObject1).a();
/*  79: 79 */      arrayOfAh = (Ah[])arrayOfAh.a();
/*  80: 80 */      paramAh = (Ah[])paramAh.a();
/*  81:    */      
/*  82: 82 */      for (int i = 0; i < localObject1.length; i++)
/*  83:    */      {
/*  84: 84 */        if (paramAh[i].a() == Aj.d)
/*  85:    */        {
/*  86: 86 */          b(((Integer)localObject1[i].a()).intValue(), ((Short)arrayOfAh[i].a()).shortValue(), ((Integer)paramAh[i].a()).intValue());
/*  87: 87 */        } else { if (paramAh[i].a() == Aj.n)
/*  88:    */          {
/*  89: 89 */            ((Integer)(localObject2 = (Ah[])paramAh[i].a())[0].a()).intValue();
/*  90:    */            
/*  91:    */            MetaObject localMetaObject1;
/*  92:    */            
/*  93: 93 */            (localMetaObject1 = MetaObjectManager.instantiate(((Short)localObject2[1].a()).shortValue())).fromTag(localObject2[2]);
/*  94: 94 */            MetaObject localMetaObject2 = localMetaObject1;int k = ((Short)arrayOfAh[i].a()).shortValue();int j = ((Integer)localObject1[i].a()).intValue();Object localObject2 = this; if (k == 0) synchronized (((mf)localObject2).jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap) { ((mf)localObject2).jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.remove(j); } if ((??? = (mj)((mf)localObject2).jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.get(j)) == null) { (??? = new mj()).jdField_a_of_type_Short = localObject3;((mj)???).jdField_a_of_type_Int = j; } ((mj)???).jdField_a_of_type_JavaLangObject = localMetaObject2;((mj)???).jdField_a_of_type_Short = localObject3; if ((!jdField_a_of_type_Boolean) && (((mj)???).jdField_a_of_type_Int != j)) throw new AssertionError(); if ((!jdField_a_of_type_Boolean) && (((mj)???).jdField_a_of_type_Short != localObject3)) throw new AssertionError("SLOT: " + ((mj)???).jdField_a_of_type_Short + " TRANSMIT: " + localObject3); synchronized (((mf)localObject2).jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap) { ((mf)localObject2).jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.put(j, ???); } }
/*  95: 95 */          if (!jdField_a_of_type_Boolean) { throw new AssertionError();
/*  96:    */          }
/*  97:    */        }
/*  98:    */      }
/*  99:    */      
/* 100:100 */      return; }
/* 101:101 */    if (!jdField_a_of_type_Boolean) { throw new AssertionError();
/* 102:    */    }
/* 103:    */  }
/* 104:    */  
/* 109:    */  public Ah toTagStructure()
/* 110:    */  {
/* 111:111 */    System.currentTimeMillis();
/* 112:    */    
/* 113:113 */    Ah localAh1 = new Ah("slots", Aj.d);
/* 114:114 */    Ah localAh2 = new Ah("types", Aj.c);
/* 115:115 */    Ah localAh3 = new Ah("values", Aj.d);
/* 116:    */    
/* 117:117 */    synchronized (this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap)
/* 118:    */    {
/* 119:119 */      for (Object localObject2 : this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.keySet()) {
/* 120:120 */        if (!a(((Integer)localObject2).intValue())) {
/* 121:121 */          Object localObject3 = (mj)this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.get((Integer)localObject2);
/* 122:122 */          if ((!jdField_a_of_type_Boolean) && (((Integer)localObject2).intValue() != ((mj)localObject3).jdField_a_of_type_Int)) throw new AssertionError();
/* 123:123 */          localAh1.a(new Ah(Aj.d, null, localObject2));
/* 124:124 */          localAh2.a(new Ah(Aj.c, null, Short.valueOf(((mj)localObject3).jdField_a_of_type_Short)));
/* 125:125 */          localObject2 = (MetaObject)((mj)localObject2).jdField_a_of_type_JavaLangObject;(localObject3 = new Ah[4])[0] = new Ah(Aj.d, null, Integer.valueOf(((MetaObject)localObject2).getId()));localObject3[1] = new Ah(Aj.c, null, Short.valueOf(((MetaObject)localObject2).getObjectBlockID()));localObject3[2] = ((MetaObject)localObject2).getBytesTag();localObject3[2] = new Ah(Aj.a, null, null);localAh3.a(((localObject2 = localObject3).jdField_a_of_type_JavaLangObject instanceof Integer) ? new Ah(Aj.d, null, (Integer)((mj)localObject2).jdField_a_of_type_JavaLangObject) : new Ah(Aj.n, null, (Ah[])localObject3));
/* 126:    */        }
/* 127:    */      }
/* 128:    */    }
/* 129:    */    
/* 132:132 */    return new Ah(Aj.n, "inventory", new Ah[] { localObject1, localAh2, localAh3, new Ah(Aj.a, null, null) });
/* 133:    */  }
/* 134:    */  
/* 137:137 */  private mj a(int paramInt) { return (mj)this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.get(paramInt); }
/* 138:    */  
/* 139:    */  public abstract int a();
/* 140:    */  
/* 141:141 */  public final int a(int paramInt) { if (!a(paramInt)) {
/* 142:142 */      return a(paramInt).a();
/* 143:    */    }
/* 144:144 */    return 0;
/* 145:    */  }
/* 146:    */  
/* 154:    */  public final int a(short paramShort)
/* 155:    */  {
/* 156:156 */    synchronized (this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap) {
/* 157:157 */      for (Integer localInteger : this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.keySet()) {
/* 158:158 */        if ((!a(localInteger.intValue())) && (a(localInteger.intValue()) == paramShort)) {
/* 159:159 */          return localInteger.intValue();
/* 160:    */        }
/* 161:    */      }
/* 162:    */    }
/* 163:163 */    return -1;
/* 164:    */  }
/* 165:    */  
/* 168:    */  public abstract int b();
/* 169:    */  
/* 172:    */  public int c()
/* 173:    */  {
/* 174:174 */    return 0;
/* 175:    */  }
/* 176:    */  
/* 177:    */  public final int b(short paramShort) {
/* 178:178 */    int i = 0;
/* 179:    */    Iterator localIterator;
/* 180:180 */    synchronized (this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap) {
/* 181:181 */      for (localIterator = this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.values().iterator(); localIterator.hasNext();) { mj localmj;
/* 182:182 */        if ((localmj = (mj)localIterator.next()).jdField_a_of_type_Short == paramShort) {
/* 183:183 */          i += localmj.a();
/* 184:    */        }
/* 185:    */      }
/* 186:    */    }
/* 187:187 */    return i;
/* 188:    */  }
/* 189:    */  
/* 190:    */  public final void a(short paramShort, Collection paramCollection)
/* 191:    */  {
/* 192:192 */    int i = 0;
/* 193:193 */    while ((i = a(paramShort)) >= 0) {
/* 194:194 */      this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.remove(i);
/* 195:195 */      paramCollection.add(Integer.valueOf(i));
/* 196:    */    }
/* 197:    */  }
/* 198:    */  
/* 201:    */  public final q a()
/* 202:    */  {
/* 203:203 */    return this.jdField_a_of_type_Q;
/* 204:    */  }
/* 205:    */  
/* 206:    */  public abstract int d();
/* 207:    */  
/* 208:    */  public final short a(int paramInt)
/* 209:    */  {
/* 210:210 */    if (!a(paramInt)) {
/* 211:211 */      return a(paramInt).jdField_a_of_type_Short;
/* 212:    */    }
/* 213:213 */    return 0;
/* 214:    */  }
/* 215:    */  
/* 216:    */  public String getUniqueIdentifier()
/* 217:    */  {
/* 218:218 */    if ((!jdField_a_of_type_Boolean) && (this.jdField_a_of_type_Mh.getName() == null)) throw new AssertionError();
/* 219:219 */    return this.jdField_a_of_type_Mh.getName() + "_" + this.jdField_a_of_type_Q + "_inventory"; }
/* 220:    */  
/* 221:    */  public final void a(ml paramml) { Object localObject1;
/* 222:    */    Object localObject2;
/* 223:223 */    if (paramml.getInventoryUpdateBuffer().getReceiveBuffer().size() > 0)
/* 224:    */    {
/* 225:225 */      localObject1 = new HashSet();
/* 226:226 */      for (localObject2 = paramml.getInventoryUpdateBuffer().getReceiveBuffer().iterator(); ((Iterator)localObject2).hasNext();)
/* 227:    */      {
/* 228:228 */        Integer localInteger1 = Integer.valueOf((localObject3 = (RemoteIntArray)((Iterator)localObject2).next()).getIntArray()[0]);
/* 229:229 */        Integer localInteger2 = Integer.valueOf(localObject3.getIntArray()[1]);
/* 230:230 */        Object localObject3 = Integer.valueOf(localObject3.getIntArray()[2]);
/* 231:    */        
/* 233:233 */        b(localInteger1.intValue(), localInteger2.shortValue(), localInteger2.shortValue() != 0 ? ((Integer)localObject3).intValue() : 0);
/* 234:    */        
/* 235:235 */        ((HashSet)localObject1).add(localInteger1);
/* 236:    */        
/* 239:239 */        if ((this.jdField_a_of_type_Mh.getState() instanceof ServerStateInterface)) {
/* 240:240 */          a((Collection)localObject1);
/* 241:    */        }
/* 242:    */      }
/* 243:    */    }
/* 244:    */    
/* 245:245 */    if (paramml.getInventoryMultModBuffer().getReceiveBuffer().size() > 0) {
/* 246:246 */      for (localObject1 = paramml.getInventoryMultModBuffer().getReceiveBuffer().iterator(); ((Iterator)localObject1).hasNext();) { localObject2 = (RemoteInventoryMultMod)((Iterator)localObject1).next();
/* 247:247 */        a((mi)((RemoteInventoryMultMod)localObject2).get());
/* 248:    */      }
/* 249:    */    }
/* 250:    */  }
/* 251:    */  
/* 252:    */  public final void a(int paramInt1, short paramShort, int paramInt2)
/* 253:    */  {
/* 254:    */    mj localmj;
/* 255:255 */    if ((localmj = a(paramInt1)) != null)
/* 256:    */    {
/* 257:    */      long l;
/* 258:258 */      if ((l = localmj.a() + paramInt2) > 2147483647L) {
/* 259:259 */        paramInt2 = 2147483646;
/* 260:260 */      } else if (l < -2147483648L) {
/* 261:261 */        paramInt2 = 0;
/* 262:    */      } else {
/* 263:263 */        paramInt2 = (int)l;
/* 264:    */      }
/* 265:    */      
/* 269:269 */      b(paramInt1, paramShort, Math.max(0, paramInt2));
/* 270:270 */      return; }
/* 271:271 */    if (paramInt2 < 0) {
/* 272:272 */      if (!jdField_a_of_type_Boolean) throw new AssertionError("TRYING TO SET INVENTORY TO NEGATIVE VALUE");
/* 273:    */    } else {
/* 274:274 */      b(paramInt1, paramShort, Math.max(0, paramInt2));
/* 275:    */    }
/* 276:    */  }
/* 277:    */  
/* 279:    */  public final int a(short paramShort, int paramInt)
/* 280:    */  {
/* 281:    */    int i;
/* 282:282 */    if ((i = a(paramShort)) >= 0) {
/* 283:283 */      a(i, paramShort, paramInt);
/* 284:284 */      return i;
/* 285:    */    }
/* 286:    */    
/* 287:287 */    throw new NoSlotFreeException();
/* 288:    */  }
/* 289:    */  
/* 290:    */  public final int a(ml paramml)
/* 291:    */  {
/* 292:    */    int i;
/* 293:293 */    if ((i = a((short)1)) >= 0) {
/* 294:294 */      a(i, (short)1, -1);
/* 295:295 */      a(paramml, paramml.getInventoryUpdateBuffer(), i);
/* 296:296 */      return i;
/* 297:    */    }
/* 298:    */    
/* 299:299 */    throw new NoSlotFreeException();
/* 300:    */  }
/* 301:    */  
/* 302:302 */  public final int b(short paramShort, int paramInt) { synchronized (this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap) {
/* 303:303 */      for (Integer localInteger : this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.keySet()) {
/* 304:304 */        if ((!a(localInteger.intValue())) && (a(localInteger.intValue()) == paramShort)) {
/* 305:305 */          a(localInteger.intValue(), paramShort, paramInt);
/* 306:306 */          return localInteger.intValue();
/* 307:    */        }
/* 308:    */      }
/* 309:    */    }
/* 310:310 */    return c(paramShort, paramInt);
/* 311:    */  }
/* 312:    */  
/* 314:    */  public final boolean c()
/* 315:    */  {
/* 316:316 */    return this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.isEmpty();
/* 317:    */  }
/* 318:    */  
/* 321:    */  public final boolean a(int paramInt)
/* 322:    */  {
/* 323:323 */    return ((paramInt = a(paramInt)) == null) || (paramInt.a() <= 0) || (paramInt.jdField_a_of_type_Short == 0);
/* 324:    */  }
/* 325:    */  
/* 326:    */  public boolean isVolatile() {
/* 327:327 */    return false;
/* 328:    */  }
/* 329:    */  
/* 350:    */  public final void b(int paramInt1, short arg2, int paramInt2)
/* 351:    */  {
/* 352:352 */    if (??? == 0) {
/* 353:353 */      synchronized (this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap) {
/* 354:354 */        this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.remove(paramInt1);
/* 355:355 */        return;
/* 356:    */      }
/* 357:    */    }
/* 358:358 */    if ((??? = (mj)this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.get(paramInt1)) == null)
/* 359:    */    {
/* 360:360 */      (??? = new mj()).jdField_a_of_type_Short = ???;
/* 361:361 */      ((mj)???).jdField_a_of_type_Int = paramInt1;
/* 362:    */    }
/* 363:363 */    ((mj)???).b(paramInt2);
/* 364:364 */    ((mj)???).jdField_a_of_type_Short = ???;
/* 365:    */    
/* 366:366 */    if ((!jdField_a_of_type_Boolean) && (((mj)???).jdField_a_of_type_Int != paramInt1)) throw new AssertionError();
/* 367:367 */    if ((!jdField_a_of_type_Boolean) && (((mj)???).jdField_a_of_type_Short != ???)) throw new AssertionError("SLOT: " + ((mj)???).jdField_a_of_type_Short + " TRANSMIT: " + ???);
/* 368:368 */    synchronized (this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap) {
/* 369:369 */      this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.put(paramInt1, ???); return;
/* 370:    */    }
/* 371:    */  }
/* 372:    */  
/* 376:    */  public final int c(short paramShort, int paramInt)
/* 377:    */  {
/* 378:378 */    for (int i = 0; (i < d()) || (d() < 0); i++)
/* 379:    */    {
/* 380:380 */      if (a(i)) {
/* 381:381 */        b(i, paramShort, paramInt);
/* 382:    */        
/* 383:383 */        return i;
/* 384:    */      }
/* 385:    */    }
/* 386:386 */    throw new NoSlotFreeException();
/* 387:    */  }
/* 388:    */  
/* 392:    */  private void a(ml paramml, RemoteIntArrayBuffer paramRemoteIntArrayBuffer, int paramInt)
/* 393:    */  {
/* 394:394 */    if (!a(paramInt)) {
/* 395:395 */      a(paramml, paramRemoteIntArrayBuffer, a(paramInt));
/* 398:    */    }
/* 399:399 */    else if (paramRemoteIntArrayBuffer.getArraySize() == 6)
/* 400:    */    {
/* 401:401 */      (paramml = new RemoteIntArray(6, (NetworkObject)paramml)).set(0, paramInt);
/* 402:402 */      paramml.set(1, 0);
/* 403:403 */      paramml.set(2, 0);
/* 404:404 */      paramml.set(3, this.jdField_a_of_type_Q.jdField_a_of_type_Int);
/* 405:405 */      paramml.set(4, this.jdField_a_of_type_Q.b);
/* 406:406 */      paramml.set(5, this.jdField_a_of_type_Q.c);
/* 407:407 */      paramRemoteIntArrayBuffer.add(paramml);
/* 408:    */    }
/* 409:    */    else
/* 410:    */    {
/* 411:411 */      (paramml = new RemoteIntArray(3, (NetworkObject)paramml)).set(0, paramInt);
/* 412:412 */      paramml.set(1, 0);
/* 413:413 */      paramml.set(2, 0);
/* 414:    */      
/* 415:415 */      paramRemoteIntArrayBuffer.add(paramml);
/* 416:    */    }
/* 417:    */    
/* 420:420 */    if (paramRemoteIntArrayBuffer.size() > 200) {
/* 421:    */      try {
/* 422:422 */        if ((this.jdField_a_of_type_Mh != null) && ((this.jdField_a_of_type_Mh instanceof Sendable)))
/* 423:    */        {
/* 424:424 */          paramInt = (Sendable)(paramml = (Sendable)this.jdField_a_of_type_Mh).getState().getLocalAndRemoteObjectContainer().getLocalObjects().get(paramml.getId());
/* 425:425 */          NetworkObject localNetworkObject = (NetworkObject)paramml.getState().getLocalAndRemoteObjectContainer().getRemoteObjects().get(paramml.getId());
/* 426:426 */          throw new IllegalArgumentException("WARNING: inventory high of " + paramml + ": " + paramRemoteIntArrayBuffer.size() + "; " + paramInt + "; " + localNetworkObject);
/* 427:    */        }
/* 428:428 */        throw new IllegalArgumentException("WARNING: inventory high: " + paramRemoteIntArrayBuffer.size() + "; no inventory holder ");
/* 429:    */      } catch (IllegalArgumentException localIllegalArgumentException) {
/* 430:430 */        localIllegalArgumentException;
/* 431:    */      }
/* 432:    */    }
/* 433:    */  }
/* 434:    */  
/* 436:    */  private void a(ml paramml, RemoteIntArrayBuffer paramRemoteIntArrayBuffer, mj parammj)
/* 437:    */  {
/* 438:438 */    if (paramRemoteIntArrayBuffer.getArraySize() == 6)
/* 439:    */    {
/* 440:440 */      (paramml = new RemoteIntArray(6, (NetworkObject)paramml)).set(0, parammj.jdField_a_of_type_Int);
/* 441:441 */      paramml.set(1, parammj.jdField_a_of_type_Short);
/* 442:442 */      paramml.set(2, parammj.a());
/* 443:443 */      paramml.set(3, this.jdField_a_of_type_Q.jdField_a_of_type_Int);
/* 444:444 */      paramml.set(4, this.jdField_a_of_type_Q.b);
/* 445:445 */      paramml.set(5, this.jdField_a_of_type_Q.c);
/* 446:446 */      paramRemoteIntArrayBuffer.add(paramml);
/* 447:447 */      return;
/* 448:    */    }
/* 449:449 */    (paramml = new RemoteIntArray(3, (NetworkObject)paramml)).set(0, parammj.jdField_a_of_type_Int);
/* 450:450 */    paramml.set(1, parammj.jdField_a_of_type_Short);
/* 451:451 */    paramml.set(2, parammj.a());
/* 452:    */    
/* 453:453 */    paramRemoteIntArrayBuffer.add(paramml);
/* 454:    */  }
/* 455:    */  
/* 460:    */  public final void b(ml paramml)
/* 461:    */  {
/* 462:462 */    synchronized (this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap) {
/* 463:463 */      for (mj localmj : this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.values()) {
/* 464:464 */        a(paramml, paramml.getInventoryUpdateBuffer(), localmj);
/* 465:    */      }
/* 466:466 */      return;
/* 467:    */    }
/* 468:    */  }
/* 469:    */  
/* 489:    */  public final void a(int paramInt1, int paramInt2, int paramInt3)
/* 490:    */  {
/* 491:491 */    a(paramInt1, paramInt2, this, paramInt3);
/* 492:    */  }
/* 493:    */  
/* 558:    */  public final void a(int paramInt1, int paramInt2, mf parammf, int paramInt3)
/* 559:    */  {
/* 560:560 */    mj localmj1 = a(paramInt1);
/* 561:561 */    mj localmj2 = parammf.a(paramInt2);
/* 562:    */    
/* 563:563 */    synchronized (this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap) {
/* 564:564 */      if ((localmj1 != null) && (localmj2 != null) && (localmj1.jdField_a_of_type_Short == localmj2.jdField_a_of_type_Short) && (localmj1 != localmj2)) {
/* 565:565 */        System.err.println("MERGING SLOT");
/* 566:566 */        localmj1.a(paramInt3);
/* 567:567 */        localmj2.a(-paramInt3);
/* 568:568 */        if (localmj2.a() <= 0) {
/* 569:569 */          parammf.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.remove(paramInt2);
/* 570:    */        }
/* 571:    */      } else {
/* 572:572 */        if (localmj1 != null) {
/* 573:573 */          System.err.println("COUTNSFDN: " + paramInt3 + " of " + localmj1.a() + " (other: " + localmj2 + ")");
/* 574:    */        }
/* 575:575 */        if ((localmj1 != null) && (localmj2 != null) && (localmj1.jdField_a_of_type_Short != localmj2.jdField_a_of_type_Short) && (localmj1.jdField_a_of_type_Short != 0)) {
/* 576:576 */          if ((localmj2.a() == paramInt3) || (localmj1.a() == 0) || (localmj2.a() == 0)) {
/* 577:577 */            localmj1.jdField_a_of_type_Int = paramInt2;
/* 578:578 */            if ((!jdField_a_of_type_Boolean) && (localmj1.jdField_a_of_type_Short == 0)) throw new AssertionError();
/* 579:579 */            parammf.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.put(paramInt2, localmj1);
/* 580:580 */            this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.remove(paramInt1);
/* 581:    */            
/* 582:582 */            localmj2.jdField_a_of_type_Int = paramInt1;
/* 583:583 */            this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.put(paramInt1, localmj2);
/* 584:584 */            if (parammf.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.get(paramInt2) != localmj1) {
/* 585:585 */              parammf.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.remove(paramInt2);
/* 586:    */            }
/* 587:    */          }
/* 588:    */        } else {
/* 589:    */          mj localmj3;
/* 590:590 */          if ((localmj1 != null) && (
/* 591:591 */            (localmj2 == null) || (localmj2.a() == 0)))
/* 592:    */          {
/* 593:593 */            (localmj3 = new mj(localmj1, paramInt2)).b(paramInt3);
/* 594:594 */            if ((!jdField_a_of_type_Boolean) && (localmj3.jdField_a_of_type_Short == 0)) throw new AssertionError();
/* 595:595 */            parammf.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.put(paramInt2, localmj3);
/* 596:    */            
/* 598:598 */            if ((localmj3 = (mj)this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.get(paramInt1)) != null) {
/* 599:599 */              localmj3.a(-paramInt3);
/* 600:600 */              if (localmj3.a() <= 0) {
/* 601:601 */                this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.remove(paramInt1);
/* 602:    */              }
/* 603:    */            }
/* 604:    */          }
/* 605:    */          
/* 607:607 */          if ((localmj2 != null) && (
/* 608:608 */            (localmj1 == null) || (localmj1.a() == 0)))
/* 609:    */          {
/* 610:610 */            (localmj3 = new mj(localmj2, paramInt1)).b(paramInt3);
/* 611:611 */            if ((!jdField_a_of_type_Boolean) && (localmj3.jdField_a_of_type_Short == 0)) throw new AssertionError();
/* 612:612 */            this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.put(paramInt1, localmj3);
/* 613:    */            
/* 616:616 */            if ((localmj3 = (mj)parammf.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.get(paramInt2)) != null) {
/* 617:617 */              localmj3.a(-paramInt3);
/* 618:618 */              if (localmj3.a() <= 0) {
/* 619:619 */                parammf.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.remove(paramInt2);
/* 620:    */              }
/* 621:    */            }
/* 622:    */          }
/* 623:    */        }
/* 624:    */      }
/* 625:    */    }
/* 626:    */    
/* 647:647 */    a(paramInt1);
/* 648:648 */    parammf.a(paramInt2);
/* 649:    */  }
/* 650:    */  
/* 653:    */  public String toString()
/* 654:    */  {
/* 655:655 */    return "Inventory: (" + c() + "; " + this.jdField_a_of_type_Q + ")" + this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.toString();
/* 656:    */  }
/* 657:    */  
/* 663:    */  public final int a(DataOutputStream paramDataOutputStream)
/* 664:    */  {
/* 665:665 */    int i = 4;
/* 666:666 */    synchronized (this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap) {
/* 667:667 */      paramDataOutputStream.writeInt(this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.size());
/* 668:    */      
/* 669:669 */      for (mj localmj : this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.values()) {
/* 670:670 */        paramDataOutputStream.writeInt(localmj.jdField_a_of_type_Int);
/* 671:671 */        paramDataOutputStream.writeShort(localmj.jdField_a_of_type_Short);
/* 672:672 */        paramDataOutputStream.writeInt(localmj.a());
/* 673:673 */        i += 10;
/* 674:    */      }
/* 675:    */    }
/* 676:676 */    return i;
/* 677:    */  }
/* 678:    */  
/* 679:679 */  public final void a(DataInputStream paramDataInputStream) { int i = paramDataInputStream.readInt();
/* 680:680 */    for (int j = 0; j < i; j++) {
/* 681:681 */      int k = paramDataInputStream.readInt();
/* 682:682 */      short s = paramDataInputStream.readShort();
/* 683:683 */      int m = paramDataInputStream.readInt();
/* 684:684 */      b(k, s, m);
/* 685:    */    }
/* 686:    */  }
/* 687:    */  
/* 688:    */  public final void a(mF arg1) {
/* 689:    */    mx localmx;
/* 690:690 */    if ((localmx = ((vg)???.getState()).a().getSector(???.getSectorId())) != null) {
/* 691:691 */      System.err.println("[INVENTORY][SPAWNING] spawning inventory at " + this.jdField_a_of_type_Q);
/* 692:692 */      Vector3f localVector3f1 = new Vector3f(this.jdField_a_of_type_Q.jdField_a_of_type_Int - 8, this.jdField_a_of_type_Q.b - 8, this.jdField_a_of_type_Q.c - 8);
/* 693:693 */      ???.getWorldTransform().transform(localVector3f1);
/* 694:694 */      synchronized (this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap) {
/* 695:695 */        for (Iterator localIterator = this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.values().iterator(); localIterator.hasNext();) { mj localmj;
/* 696:696 */          if ((localmj = (mj)localIterator.next()).jdField_a_of_type_Short != 0) {
/* 697:    */            Vector3f localVector3f2;
/* 698:698 */            void tmp157_155 = (localVector3f2 = new Vector3f(localVector3f1));tmp157_155.x = ((float)(tmp157_155.x + (Math.random() - 0.5D))); Vector3f 
/* 699:699 */              tmp176_174 = localVector3f2;tmp176_174.y = ((float)(tmp176_174.y + (Math.random() - 0.5D))); Vector3f 
/* 700:700 */              tmp195_193 = localVector3f2;tmp195_193.z = ((float)(tmp195_193.z + (Math.random() - 0.5D)));
/* 701:701 */            System.err.println("[INVENTORY][SPAWNING] spawning inventory at " + this.jdField_a_of_type_Q + " -> " + localVector3f2);
/* 702:702 */            localmx.a().a(localVector3f2, localmj.jdField_a_of_type_Short, localmj.a());
/* 703:    */          }
/* 704:    */        }
/* 705:705 */        return; } }
/* 706:706 */    System.err.println("[INVENTORY][SPAWN] sector null of " + ???);
/* 707:    */  }
/* 708:    */  
/* 711:    */  private void a(int paramInt)
/* 712:    */  {
/* 713:713 */    this.jdField_a_of_type_Mh.sendInventoryModification(paramInt, this.jdField_a_of_type_Q);
/* 714:    */  }
/* 715:    */  
/* 716:    */  public final void a(Collection paramCollection) {
/* 717:717 */    this.jdField_a_of_type_Mh.sendInventoryModification(paramCollection, this.jdField_a_of_type_Q);
/* 718:    */  }
/* 719:    */  
/* 720:    */  public final void a(RemoteIntArray paramRemoteIntArray, ml paramml) {
/* 721:721 */    Integer localInteger1 = Integer.valueOf(paramRemoteIntArray.getIntArray()[0]);
/* 722:722 */    Integer localInteger2 = Integer.valueOf(paramRemoteIntArray.getIntArray()[1]);
/* 723:723 */    paramRemoteIntArray = Integer.valueOf(paramRemoteIntArray.getIntArray()[2]);
/* 724:    */    
/* 726:726 */    b(localInteger1.intValue(), localInteger2.shortValue(), localInteger2.shortValue() != 0 ? paramRemoteIntArray.intValue() : 0);
/* 727:    */    
/* 728:728 */    if ((this.jdField_a_of_type_Mh.getState() instanceof ServerStateInterface)) {
/* 729:729 */      a(paramml, paramml.getInventoryUpdateBuffer(), localInteger1.intValue());
/* 730:    */    }
/* 731:    */  }
/* 732:    */  
/* 734:    */  public final void a(mi parammi)
/* 735:    */  {
/* 736:736 */    ArrayList localArrayList = null;
/* 737:737 */    if ((this.jdField_a_of_type_Mh.getState() instanceof ServerStateInterface)) {
/* 738:738 */      localArrayList = new ArrayList();
/* 739:    */    }
/* 740:740 */    for (int i = 0; i < parammi.a.length; i++) {
/* 741:    */      mj localmj;
/* 742:742 */      if ((localmj = parammi.a[i]).jdField_a_of_type_Short == 0) {
/* 743:743 */        this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.remove(localmj.jdField_a_of_type_Int);
/* 744:    */      } else {
/* 745:745 */        this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.put(localmj.jdField_a_of_type_Int, localmj);
/* 746:    */      }
/* 747:747 */      if ((this.jdField_a_of_type_Mh.getState() instanceof ServerStateInterface)) {
/* 748:748 */        localArrayList.add(Integer.valueOf(localmj.jdField_a_of_type_Int));
/* 749:    */      }
/* 750:    */    }
/* 751:751 */    if ((this.jdField_a_of_type_Mh.getState() instanceof ServerStateInterface)) {
/* 752:752 */      this.jdField_a_of_type_Mh.sendInventoryModification(localArrayList, this.jdField_a_of_type_Q);
/* 753:    */    }
/* 754:    */  }
/* 755:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     mf
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */