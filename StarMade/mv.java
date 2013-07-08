/*   1:    */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*   2:    */import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*   3:    */import java.io.PrintStream;
/*   4:    */import java.util.ArrayList;
/*   5:    */import java.util.Collection;
/*   6:    */import java.util.HashMap;
/*   7:    */import java.util.HashSet;
/*   8:    */import java.util.Iterator;
/*   9:    */import java.util.Map;
/*  10:    */import javax.vecmath.Vector3f;
/*  11:    */import org.schema.common.util.ByteUtil;
/*  12:    */import org.schema.game.network.objects.NetworkSector;
/*  13:    */import org.schema.game.network.objects.remote.RemoteItem;
/*  14:    */import org.schema.game.network.objects.remote.RemoteItemBuffer;
/*  15:    */import org.schema.schine.network.NetworkStateContainer;
/*  16:    */import org.schema.schine.network.StateInterface;
/*  17:    */import org.schema.schine.network.objects.NetworkObject;
/*  18:    */import org.schema.schine.network.objects.Sendable;
/*  19:    */import org.schema.schine.network.objects.remote.RemoteBoolean;
/*  20:    */import org.schema.schine.network.objects.remote.RemoteInteger;
/*  21:    */import org.schema.schine.network.objects.remote.RemoteVector3i;
/*  22:    */import org.schema.schine.network.objects.remote.Streamable;
/*  23:    */import org.schema.schine.network.server.ServerStateInterface;
/*  24:    */
/*  34:    */public class mv
/*  35:    */  implements Sendable
/*  36:    */{
/*  37:    */  private final StateInterface jdField_a_of_type_OrgSchemaSchineNetworkStateInterface;
/*  38: 38 */  private int jdField_a_of_type_Int = -1;
/*  39:    */  
/*  40:    */  private final boolean jdField_a_of_type_Boolean;
/*  41:    */  
/*  42:    */  private boolean jdField_b_of_type_Boolean;
/*  43:    */  private boolean c;
/*  44:    */  private NetworkSector jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSector;
/*  45: 45 */  private Map jdField_a_of_type_JavaUtilMap = new HashMap();
/*  46: 46 */  private final ArrayList jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/*  47: 47 */  private final ArrayList jdField_b_of_type_JavaUtilArrayList = new ArrayList();
/*  48:    */  private Iterator jdField_a_of_type_JavaUtilIterator;
/*  49:    */  private long jdField_a_of_type_Long;
/*  50:    */  
/*  51: 51 */  public mv(StateInterface paramStateInterface) { this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface = paramStateInterface;
/*  52: 52 */    this.jdField_a_of_type_Boolean = (paramStateInterface instanceof ServerStateInterface);
/*  53:    */  }
/*  54:    */  
/*  61:    */  public String toString()
/*  62:    */  {
/*  63: 63 */    if (isOnServer())
/*  64:    */    {
/*  65: 65 */      if (a() != null) {
/*  66: 66 */        return "[SERVER RemoteSector(" + this.jdField_a_of_type_Int + ")" + a().a() + "; " + a().a + "]";
/*  67:    */      }
/*  68:    */      
/*  70: 70 */      return "[SERVER RemoteSector(" + this.jdField_a_of_type_Int + ") sector removed]";
/*  71:    */    }
/*  72:    */    
/*  73: 73 */    return "[CLIENT ReSector(" + this.jdField_a_of_type_Int + ")" + ((Boolean)this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSector.active.get()).booleanValue() + "; " + a() + "; ]";
/*  74:    */  }
/*  75:    */  
/*  81:    */  public final q a()
/*  82:    */  {
/*  83: 83 */    return this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSector.pos.getVector();
/*  84:    */  }
/*  85:    */  
/*  88:    */  public void cleanUpOnEntityDelete() {}
/*  89:    */  
/*  91:    */  public int getId()
/*  92:    */  {
/*  93: 93 */    return this.jdField_a_of_type_Int;
/*  94:    */  }
/*  95:    */  
/*  96:    */  public final NetworkSector a()
/*  97:    */  {
/*  98: 98 */    return this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSector;
/*  99:    */  }
/* 100:    */  
/* 101:    */  public StateInterface getState()
/* 102:    */  {
/* 103:103 */    return this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface;
/* 104:    */  }
/* 105:    */  
/* 107:    */  public void initFromNetworkObject(NetworkObject paramNetworkObject)
/* 108:    */  {
/* 109:109 */    setId(((Integer)paramNetworkObject.id.get()).intValue());
/* 110:    */  }
/* 111:    */  
/* 113:    */  public void initialize()
/* 114:    */  {
/* 115:115 */    if (!isOnServer()) {
/* 116:116 */      this.jdField_a_of_type_JavaUtilMap = new HashMap();
/* 117:    */    }
/* 118:    */  }
/* 119:    */  
/* 144:    */  public boolean isMarkedForDeleteVolatile()
/* 145:    */  {
/* 146:146 */    return this.jdField_b_of_type_Boolean;
/* 147:    */  }
/* 148:    */  
/* 149:    */  public boolean isMarkedForDeleteVolatileSent()
/* 150:    */  {
/* 151:151 */    return this.c;
/* 152:    */  }
/* 153:    */  
/* 154:    */  public boolean isOnServer()
/* 155:    */  {
/* 156:156 */    return this.jdField_a_of_type_Boolean;
/* 157:    */  }
/* 158:    */  
/* 159:    */  public void newNetworkObject()
/* 160:    */  {
/* 161:161 */    this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSector = new NetworkSector(getState());
/* 162:    */  }
/* 163:    */  
/* 164:    */  public void setId(int paramInt)
/* 165:    */  {
/* 166:166 */    this.jdField_a_of_type_Int = paramInt;
/* 167:    */  }
/* 168:    */  
/* 169:    */  public void setMarkedForDeleteVolatile(boolean paramBoolean)
/* 170:    */  {
/* 171:171 */    this.jdField_b_of_type_Boolean = paramBoolean;
/* 172:    */  }
/* 173:    */  
/* 175:    */  public void setMarkedForDeleteVolatileSent(boolean paramBoolean)
/* 176:    */  {
/* 177:177 */    this.c = paramBoolean;
/* 178:    */  }
/* 179:    */  
/* 182:    */  public void updateFromNetworkObject(NetworkObject paramNetworkObject)
/* 183:    */  {
/* 184:184 */    NetworkSector localNetworkSector = (NetworkSector)paramNetworkObject;paramNetworkObject = this; for (int i = 0; i < localNetworkSector.itemBuffer.getReceiveBuffer().size(); i++) { RemoteItem localRemoteItem; if ((localRemoteItem = (RemoteItem)localNetworkSector.itemBuffer.getReceiveBuffer().get(i)).isAdd()) paramNetworkObject.a((me)localRemoteItem.get()); else { paramNetworkObject.a(((me)localRemoteItem.get()).b());
/* 185:    */      }
/* 186:    */    }
/* 187:    */  }
/* 188:    */  
/* 209:    */  public void updateLocal(xq paramxq)
/* 210:    */  {
/* 211:211 */    if (isOnServer())
/* 212:    */    {
/* 213:213 */      paramxq = null; if ((!this.jdField_a_of_type_JavaUtilMap.isEmpty()) || (!this.jdField_a_of_type_JavaUtilArrayList.isEmpty()) || (!this.jdField_b_of_type_JavaUtilArrayList.isEmpty()))
/* 214:    */      {
/* 215:215 */        for (paramxq = ((vg)getState()).b().values().iterator(); paramxq.hasNext();) { lE locallE;
/* 216:216 */          if ((((locallE = (lE)paramxq.next()) instanceof lE)) && (locallE.c() == getId()))
/* 217:    */          {
/* 219:219 */            this.jdField_a_of_type_JavaUtilHashSet.add(locallE);
/* 220:    */          }
/* 221:    */        }
/* 222:    */        
/* 223:223 */        a(true);
/* 224:224 */        this.jdField_a_of_type_JavaUtilHashSet.clear();
/* 226:    */      }
/* 227:    */      
/* 229:    */    }
/* 230:    */    else
/* 231:    */    {
/* 233:233 */      a(false);
/* 234:    */      
/* 235:235 */      paramxq = this; if ((!d) && (!(paramxq.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface instanceof ct))) throw new AssertionError(); ((ct)paramxq.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface).a();
/* 236:    */    }
/* 237:    */  }
/* 238:    */  
/* 271:    */  public final void a(Vector3f paramVector3f, short paramShort, int paramInt)
/* 272:    */  {
/* 273:273 */    if (paramShort != 0) {
/* 274:274 */      a(new me(vg.e++, paramShort, paramInt, paramVector3f));
/* 275:    */    }
/* 276:    */  }
/* 277:    */  
/* 278:    */  private void a(me paramme) {
/* 279:279 */    synchronized (this.jdField_a_of_type_JavaUtilMap)
/* 280:    */    {
/* 281:281 */      this.jdField_a_of_type_JavaUtilArrayList.add(paramme);
/* 282:282 */      return;
/* 283:    */    }
/* 284:    */  }
/* 285:    */  
/* 286:    */  public final void a(int paramInt) {
/* 287:287 */    synchronized (this.jdField_a_of_type_JavaUtilMap)
/* 288:    */    {
/* 289:289 */      if ((me)this.jdField_a_of_type_JavaUtilMap.get(Integer.valueOf(paramInt)) != null) {
/* 290:290 */        this.jdField_b_of_type_JavaUtilArrayList.add(Integer.valueOf(paramInt));
/* 291:    */      } else {
/* 292:292 */        System.err.println((isOnServer() ? "[SERVER]" : "[CLIENT]") + "[RemoteSector] WARNING: trying to delete item id that doesn't exist: " + paramInt);
/* 293:    */      }
/* 294:    */      
/* 297:297 */      return;
/* 298:    */    }
/* 299:    */  }
/* 300:    */  
/* 303:303 */  private HashSet jdField_a_of_type_JavaUtilHashSet = new HashSet();
/* 304:    */  private mx jdField_a_of_type_Mx;
/* 305:    */  
/* 306:    */  private void a(boolean paramBoolean) {
/* 307:    */    Object localObject2;
/* 308:308 */    if (isOnServer()) {
/* 309:309 */      long l = System.currentTimeMillis();
/* 310:310 */      if ((this.jdField_a_of_type_JavaUtilIterator != null) && (l - this.jdField_a_of_type_Long > 200L))
/* 311:    */      {
/* 312:312 */        if (!this.jdField_a_of_type_JavaUtilIterator.hasNext()) {
/* 313:313 */          this.jdField_a_of_type_JavaUtilIterator = this.jdField_a_of_type_JavaUtilMap.values().iterator();
/* 314:    */        }
/* 315:315 */        int i = 0;
/* 316:316 */        while ((this.jdField_a_of_type_JavaUtilIterator.hasNext()) && (i < 100))
/* 317:    */        {
/* 318:318 */          if (!(localObject2 = (me)this.jdField_a_of_type_JavaUtilIterator.next()).a(l)) {
/* 319:319 */            a(((me)localObject2).b());
/* 320:    */          } else {
/* 321:321 */            if (((me)localObject2).a())
/* 322:    */            {
/* 324:324 */              if ((((me)localObject2).a(a())) && (paramBoolean))
/* 325:    */              {
/* 326:326 */                this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSector.itemBuffer.add(new RemoteItem((me)localObject2, Boolean.valueOf(true), this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSector));
/* 327:    */              }
/* 328:    */            }
/* 329:    */            
/* 332:332 */            for (Iterator localIterator = this.jdField_a_of_type_JavaUtilHashSet.iterator(); (localIterator.hasNext()) && 
/* 333:    */                
/* 335:335 */                  (!((lE)localIterator.next()).a((me)localObject2));) {}
/* 336:    */          }
/* 337:337 */          i++;
/* 338:    */        }
/* 339:    */        
/* 346:346 */        if (!this.jdField_a_of_type_JavaUtilIterator.hasNext()) {
/* 347:347 */          this.jdField_a_of_type_Long = l;
/* 348:    */        }
/* 349:    */      }
/* 350:    */    }
/* 351:    */    
/* 352:    */    Object localObject1;
/* 353:    */    me localme;
/* 354:354 */    if (!this.jdField_a_of_type_JavaUtilArrayList.isEmpty()) {
/* 355:355 */      synchronized (this.jdField_a_of_type_JavaUtilMap) {
/* 356:356 */        while (!this.jdField_a_of_type_JavaUtilArrayList.isEmpty()) {
/* 357:357 */          localObject1 = (me)this.jdField_a_of_type_JavaUtilArrayList.remove(0);
/* 358:    */          
/* 359:359 */          if ((localme = (me)this.jdField_a_of_type_JavaUtilMap.put(Integer.valueOf(((me)localObject1).b()), localObject1)) != null) {
/* 360:360 */            ((me)localObject1).a(localme.a());
/* 361:361 */            System.err.println("[REMOTESECTOR] " + getState() + " ITEM change: " + localme + " -> " + localObject1);
/* 362:    */          }
/* 363:    */          
/* 364:364 */          System.err.println("[REMOTESECTOR] ITEM ADDED: " + localObject1 + ": Total: " + this.jdField_a_of_type_JavaUtilMap.size());
/* 365:365 */          if (paramBoolean) {
/* 366:366 */            this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSector.itemBuffer.add(new RemoteItem((me)localObject1, Boolean.valueOf(true), this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSector));
/* 367:    */          }
/* 368:    */          
/* 369:369 */          this.jdField_a_of_type_JavaUtilIterator = this.jdField_a_of_type_JavaUtilMap.values().iterator();
/* 370:    */        }
/* 371:    */      }
/* 372:    */    }
/* 373:373 */    if (!this.jdField_b_of_type_JavaUtilArrayList.isEmpty()) {
/* 374:374 */      synchronized (this.jdField_a_of_type_JavaUtilMap) {
/* 375:375 */        while (!this.jdField_b_of_type_JavaUtilArrayList.isEmpty()) {
/* 376:376 */          localObject1 = (Integer)this.jdField_b_of_type_JavaUtilArrayList.remove(0);
/* 377:377 */          localme = (me)this.jdField_a_of_type_JavaUtilMap.remove(localObject1);
/* 378:    */          
/* 379:379 */          if ((paramBoolean) && (localme != null)) {
/* 380:380 */            localObject2 = new RemoteItem(localme, Boolean.valueOf(false), this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSector);
/* 381:    */            
/* 382:382 */            this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSector.itemBuffer.add((Streamable)localObject2);
/* 383:383 */          } else if (localme == null) {
/* 384:384 */            System.err.println("[SERVER][REMOTESECTOR] deleted invalid id: " + localObject1);
/* 385:    */          }
/* 386:    */          
/* 388:388 */          this.jdField_a_of_type_JavaUtilIterator = this.jdField_a_of_type_JavaUtilMap.values().iterator();
/* 389:    */        }
/* 390:390 */        return;
/* 391:    */      }
/* 392:    */    }
/* 393:    */  }
/* 394:    */  
/* 399:    */  private mx a()
/* 400:    */  {
/* 401:401 */    if ((!d) && (!(this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface instanceof vg))) throw new AssertionError();
/* 402:402 */    return this.jdField_a_of_type_Mx;
/* 403:    */  }
/* 404:    */  
/* 405:    */  public void updateToFullNetworkObject()
/* 406:    */  {
/* 407:407 */    this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSector.id.set(Integer.valueOf(getId()));
/* 408:408 */    mv localmv = this; synchronized (this.jdField_a_of_type_JavaUtilMap) { me localme; for (Iterator localIterator = localmv.jdField_a_of_type_JavaUtilMap.values().iterator(); localIterator.hasNext(); localmv.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSector.itemBuffer.add(new RemoteItem(localme, Boolean.valueOf(true), localmv.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSector))) localme = (me)localIterator.next(); }
/* 409:409 */    this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSector.pos.set(a().a);
/* 410:410 */    this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSector.active.set(Boolean.valueOf(a().a()));
/* 411:    */    Object localObject2;
/* 412:412 */    int i = ByteUtil.d((localObject2 = a().a).jdField_a_of_type_Int);int j = ByteUtil.d(((q)localObject2).b);int k = ByteUtil.d(((q)localObject2).c);(localObject2 = new Vector3f()).set(i - 8, j - 8, k - 8); if (((Vector3f)localObject2).length() >= 6.5F) { ((Vector3f)localObject2).length();
/* 413:    */    }
/* 414:    */    
/* 416:416 */    mI.a(a().a);
/* 417:    */  }
/* 418:    */  
/* 420:    */  public void updateToNetworkObject()
/* 421:    */  {
/* 422:422 */    if (isOnServer()) {
/* 423:423 */      this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSector.id.set(Integer.valueOf(getId()));
/* 424:424 */      this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSector.active.set(Boolean.valueOf(a().a()));
/* 425:    */    }
/* 426:    */  }
/* 427:    */  
/* 431:    */  public final void a(mx parammx)
/* 432:    */  {
/* 433:433 */    this.jdField_a_of_type_Int = parammx.a();
/* 434:434 */    this.jdField_a_of_type_Mx = parammx;
/* 435:    */  }
/* 436:    */  
/* 439:    */  public final Map a()
/* 440:    */  {
/* 441:441 */    return this.jdField_a_of_type_JavaUtilMap;
/* 442:    */  }
/* 443:    */  
/* 444:    */  public final void a(Map paramMap) {
/* 445:445 */    this.jdField_a_of_type_JavaUtilMap = paramMap;
/* 446:446 */    this.jdField_a_of_type_JavaUtilIterator = paramMap.values().iterator();
/* 447:    */  }
/* 448:    */  
/* 451:    */  public void destroyPersistent() {}
/* 452:    */  
/* 455:    */  public boolean isMarkedForPermanentDelete()
/* 456:    */  {
/* 457:457 */    return false;
/* 458:    */  }
/* 459:    */  
/* 462:    */  public void markedForPermanentDelete(boolean paramBoolean) {}
/* 463:    */  
/* 466:    */  public static void a(vg paramvg, Collection paramCollection)
/* 467:    */  {
/* 468:468 */    for (lE locallE : paramvg.b().values())
/* 469:    */    {
/* 470:    */      Sendable localSendable;
/* 471:471 */      if ((localSendable = (Sendable)paramvg.getLocalAndRemoteObjectContainer().getLocalObjects().get(locallE.c())) != null) {
/* 472:472 */        paramCollection.add((mv)localSendable);
/* 473:    */      } else {
/* 474:474 */        System.err.println("[SERVER][REMOTESECTOR] WARNING: REMOTE SECTOR FOR " + locallE + " NOT FOUND: " + locallE.c());
/* 475:    */      }
/* 476:    */    }
/* 477:    */  }
/* 478:    */  
/* 481:    */  public boolean isUpdatable()
/* 482:    */  {
/* 483:483 */    return false;
/* 484:    */  }
/* 485:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     mv
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */