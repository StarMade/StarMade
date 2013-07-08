/*   1:    */package org.schema.schine.network.objects;
/*   2:    */
/*   3:    */import com.bulletphysics.util.ObjectArrayList;
/*   4:    */import it.unimi.dsi.fastutil.bytes.Byte2ObjectOpenHashMap;
/*   5:    */import it.unimi.dsi.fastutil.objects.Object2ByteOpenHashMap;
/*   6:    */import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*   7:    */import java.io.DataInputStream;
/*   8:    */import java.io.DataOutputStream;
/*   9:    */import java.io.OutputStream;
/*  10:    */import java.io.PrintStream;
/*  11:    */import java.lang.reflect.Field;
/*  12:    */import java.util.Arrays;
/*  13:    */import java.util.HashMap;
/*  14:    */import java.util.Iterator;
/*  15:    */import java.util.Map.Entry;
/*  16:    */import java.util.Set;
/*  17:    */import java.util.concurrent.ConcurrentHashMap;
/*  18:    */import javax.annotation.PostConstruct;
/*  19:    */import org.schema.schine.network.NetUtil;
/*  20:    */import org.schema.schine.network.StateInterface;
/*  21:    */import org.schema.schine.network.client.ClientState;
/*  22:    */import org.schema.schine.network.objects.remote.NetworkChangeObserver;
/*  23:    */import org.schema.schine.network.objects.remote.RemoteBoolean;
/*  24:    */import org.schema.schine.network.objects.remote.RemoteBuffer;
/*  25:    */import org.schema.schine.network.objects.remote.RemoteInteger;
/*  26:    */import org.schema.schine.network.objects.remote.Streamable;
/*  27:    */import org.schema.schine.network.server.ServerStateInterface;
/*  28:    */import org.schema.schine.network.synchronization.SynchronizationReceiver;
/*  29:    */import org.schema.schine.network.synchronization.SynchronizationSender;
/*  30:    */import org.w3c.dom.Attr;
/*  31:    */import org.w3c.dom.DOMException;
/*  32:    */import org.w3c.dom.Document;
/*  33:    */import org.w3c.dom.Element;
/*  34:    */import org.w3c.dom.Node;
/*  35:    */
/*  91:    */public abstract class NetworkObject
/*  92:    */  implements NetworkChangeObserver
/*  93:    */{
/*  94: 94 */  public boolean newObject = true;
/*  95:    */  
/*  96: 96 */  public RemoteInteger id = new RemoteInteger(Integer.valueOf(-123456), this);
/*  97: 97 */  public RemoteInteger clientId = new RemoteInteger(Integer.valueOf(-777777), this);
/*  98:    */  
/*  99: 99 */  public RemoteBoolean markedDeleted = new RemoteBoolean(false, this);
/* 100:    */  
/* 101:    */  private Byte2ObjectOpenHashMap fieldMap;
/* 102:    */  
/* 103:    */  private Object2ByteOpenHashMap fieldMapKey;
/* 104:    */  private ObjectArrayList buffers;
/* 105:    */  private final boolean onServer;
/* 106:    */  private final StateInterface state;
/* 107:107 */  private static final ConcurrentHashMap fieldMapCache = new ConcurrentHashMap();
/* 108:108 */  private static final ConcurrentHashMap fieldMapKeyCache = new ConcurrentHashMap();
/* 109:    */  
/* 112:112 */  private boolean changed = true;
/* 113:    */  
/* 114:    */  private boolean observersInitialized;
/* 115:    */  
/* 116:    */  private static final int FIELD_CODE_NOT_CHANGED = 0;
/* 117:    */  
/* 118:    */  private static final int FIELD_CODE_CHANGED = 1;
/* 119:    */  
/* 120:    */  private static final int FIELD_CODE_CHANGED_KEEP_CHANGED = 2;
/* 121:121 */  public static long objectDebugIdGen = 777777L;
/* 122:    */  
/* 131:    */  public static boolean global_DEBUG;
/* 132:    */  
/* 141:    */  public static NetworkObject decode(StateInterface paramStateInterface, DataInputStream paramDataInputStream, NetworkObject paramNetworkObject, short arg3, boolean paramBoolean, int paramInt)
/* 142:    */  {
/* 143:143 */    synchronized (paramDataInputStream)
/* 144:    */    {
/* 147:147 */      paramBoolean = paramDataInputStream.readByte();
/* 148:148 */      if (paramStateInterface.isReadingBigChunk()) {
/* 149:149 */        System.err.println(paramStateInterface + ": decoding NTObject from big chunk: " + paramNetworkObject.getClass().getSimpleName() + "; fields received: " + paramBoolean);
/* 150:    */      }
/* 151:    */      
/* 156:    */      try
/* 157:    */      {
/* 158:158 */        for (boolean bool = false; bool < paramBoolean; bool++)
/* 159:    */        {
/* 160:160 */          byte b = paramDataInputStream.readByte();
/* 161:    */          
/* 164:164 */          synchronized (paramNetworkObject)
/* 165:    */          {
/* 166:    */            Streamable localStreamable;
/* 167:    */            
/* 168:168 */            if ((localStreamable = (Streamable)paramNetworkObject.fieldMap.get(b)) == null) {
/* 169:169 */              System.err.println("Exception: FIELD not found " + b + ": in " + paramNetworkObject.getClass().getSimpleName() + paramNetworkObject.fieldMap + " ONE POSSIBLE REASON: RemoteArray sizes on Server and Client differ");
/* 170:    */              
/* 172:172 */              assert (localStreamable != null) : ("not found " + b + ": in " + paramNetworkObject.getClass().getSimpleName() + "; fromStateID: " + paramInt + "; fields:" + paramNetworkObject.fieldMap + " ONE POSSIBLE REASON: RemoteArray sizes on Server and Client differ");
/* 173:    */            }
/* 174:    */            
/* 179:179 */            if (paramStateInterface.isReadingBigChunk()) {
/* 180:180 */              System.err.println(paramStateInterface + ": decoding FIELD from big chunk: " + localStreamable);
/* 181:    */            }
/* 182:182 */            if ((SynchronizationReceiver.serverDebug) && ((paramStateInterface instanceof ServerStateInterface))) {
/* 183:183 */              System.err.println("DEBUG: changed field: " + localStreamable);
/* 184:    */            }
/* 185:    */            try {
/* 186:186 */              synchronized (localStreamable) {
/* 187:187 */                localStreamable.fromByteStream(paramDataInputStream, paramInt);
/* 188:    */              }
/* 189:    */            } catch (Exception localException2) {
/* 190:190 */              System.err.println("[EXCEPTION] NT ERROR: From senderID: " + paramInt + " for field: " + localStreamable + " in " + paramNetworkObject.getClass().getSimpleName());
/* 191:191 */              localException2.printStackTrace();
/* 192:192 */              throw localException2;
/* 193:    */            }
/* 194:    */          }
/* 195:    */        }
/* 196:    */      }
/* 197:    */      catch (Exception localException3)
/* 198:    */      {
/* 199:    */        Exception localException1;
/* 200:    */        
/* 203:203 */        (localException1 = 
/* 204:    */        
/* 206:206 */          localException3).printStackTrace();System.err.println("Exit because of critical error in nt object");throw localException1;
/* 207:    */      }
/* 208:    */    }
/* 209:    */    
/* 213:212 */    return paramNetworkObject;
/* 214:    */  }
/* 215:    */  
/* 226:    */  public static boolean encode(Sendable paramSendable, NetworkObject paramNetworkObject, boolean paramBoolean1, DataOutputStream paramDataOutputStream, boolean paramBoolean2, boolean paramBoolean3)
/* 227:    */  {
/* 228:227 */    paramBoolean3 = false;
/* 229:    */    
/* 239:238 */    assert (((Integer)paramNetworkObject.id.get()).intValue() >= 0) : (paramNetworkObject.id.get() + " id for remote object never set. local it is " + paramSendable.getId() + ", " + paramSendable + ", " + paramSendable.getState());
/* 240:    */    
/* 241:    */    try
/* 242:    */    {
/* 243:242 */      assert (paramNetworkObject.observersInitialized) : (paramSendable + ", " + paramNetworkObject + ": " + paramSendable.getState());
/* 244:    */      
/* 245:244 */      paramDataOutputStream.writeInt(((Integer)paramNetworkObject.id.get()).intValue());
/* 246:    */      
/* 252:251 */      paramDataOutputStream.writeByte(NetUtil.getSendableKey(paramSendable.getClass()));
/* 253:    */      
/* 260:259 */      synchronized (paramNetworkObject)
/* 261:    */      {
/* 269:268 */        int i = 0;
/* 270:269 */        for (Iterator localIterator = paramNetworkObject.fieldMap.values().iterator(); localIterator.hasNext();) { localObject1 = (Streamable)localIterator.next();
/* 271:270 */          if ((SynchronizationSender.clientDebug) && ((paramSendable.getState() instanceof ClientState))) {
/* 272:271 */            System.err.println("DEBUG ENCODING ON CLIENT " + localObject1.getClass().getSimpleName());
/* 273:    */          }
/* 274:    */          
/* 276:275 */          if (getFieldCodeLength(paramNetworkObject, (Streamable)localObject1, paramDataOutputStream, paramBoolean1)) {
/* 277:276 */            i++;
/* 278:    */          }
/* 279:    */        }
/* 280:    */        
/* 284:283 */        assert (i <= 127);
/* 285:    */        
/* 286:285 */        paramDataOutputStream.writeByte(i);
/* 287:286 */        int j = 0;
/* 288:287 */        for (Object localObject1 = paramNetworkObject.fieldMap.values().iterator(); ((Iterator)localObject1).hasNext();) { Streamable localStreamable = (Streamable)((Iterator)localObject1).next();
/* 289:    */          
/* 291:    */          int k;
/* 292:    */          
/* 293:292 */          if ((k = getFieldCode(paramNetworkObject, localStreamable, paramDataOutputStream, paramBoolean1, paramBoolean2)) > 0) {
/* 294:293 */            if (k == 2)
/* 295:    */            {
/* 298:297 */              paramBoolean3 = true;
/* 299:    */            }
/* 300:299 */            j++;
/* 301:    */          }
/* 302:    */        }
/* 303:    */        
/* 304:303 */        assert (j == i) : (" ENCODING OF " + paramSendable + " failed; forced " + paramBoolean1 + "; " + i + "/" + j);
/* 305:    */        
/* 311:310 */        global_DEBUG = false;
/* 312:    */      }
/* 313:    */      
/* 315:    */    }
/* 316:    */    catch (IllegalArgumentException localIllegalArgumentException)
/* 317:    */    {
/* 319:318 */      localIllegalArgumentException;
/* 320:    */    }
/* 321:    */    catch (IllegalAccessException localIllegalAccessException)
/* 322:    */    {
/* 323:315 */      
/* 324:    */      
/* 326:318 */        localIllegalAccessException;
/* 327:    */    }
/* 328:    */    
/* 330:319 */    return paramBoolean3;
/* 331:    */  }
/* 332:    */  
/* 336:    */  private static int getFieldCode(NetworkObject paramNetworkObject, Streamable paramStreamable, DataOutputStream paramDataOutputStream, boolean paramBoolean1, boolean paramBoolean2)
/* 337:    */  {
/* 338:327 */    if ((paramBoolean1) && ((paramStreamable instanceof RemoteBuffer)) && (((RemoteBuffer)paramStreamable).isEmpty())) {
/* 339:328 */      paramStreamable.setChanged(false);
/* 340:    */      
/* 341:330 */      return 0;
/* 342:    */    }
/* 343:332 */    if ((paramBoolean1) || (paramStreamable.hasChanged()))
/* 344:    */    {
/* 345:334 */      paramNetworkObject = paramNetworkObject.fieldMapKey.get(paramStreamable).byteValue();
/* 346:335 */      paramDataOutputStream.writeByte(paramNetworkObject);
/* 347:336 */      paramDataOutputStream.size();
/* 348:337 */      paramNetworkObject = paramStreamable.toByteStream(paramDataOutputStream);
/* 349:338 */      paramDataOutputStream.size();
/* 350:339 */      paramDataOutputStream = 1;
/* 351:    */      
/* 359:348 */      if (((!paramBoolean2) && (!paramStreamable.keepChanged())) || (paramStreamable.initialSynchUpdateOnly())) {
/* 360:349 */        paramStreamable.setChanged(false);
/* 361:    */      } else {
/* 362:351 */        paramDataOutputStream = 2;
/* 363:    */      }
/* 364:353 */      assert (paramNetworkObject > 0) : (paramStreamable.getClass() + ": , Field: " + paramStreamable);
/* 365:    */      
/* 367:356 */      return paramDataOutputStream;
/* 368:    */    }
/* 369:    */    
/* 371:360 */    return 0;
/* 372:    */  }
/* 373:    */  
/* 378:    */  private static boolean getFieldCodeLength(NetworkObject paramNetworkObject, Streamable paramStreamable, OutputStream paramOutputStream, boolean paramBoolean)
/* 379:    */  {
/* 380:369 */    if ((paramBoolean) && ((paramStreamable instanceof RemoteBuffer))) {
/* 381:370 */      return !((RemoteBuffer)paramStreamable).isEmpty();
/* 382:    */    }
/* 383:372 */    return (paramBoolean) || (paramStreamable.hasChanged());
/* 384:    */  }
/* 385:    */  
/* 454:    */  public NetworkObject(StateInterface paramStateInterface)
/* 455:    */  {
/* 456:445 */    this.onServer = (paramStateInterface instanceof ServerStateInterface);
/* 457:446 */    this.state = paramStateInterface;
/* 458:    */  }
/* 459:    */  
/* 460:    */  @PostConstruct
/* 461:450 */  public void init() { createFieldMap(); }
/* 462:    */  
/* 463:    */  public void addObserversForFields()
/* 464:    */  {
/* 465:    */    try {
/* 466:455 */      Field[] arrayOfField = getClass().getFields();
/* 467:456 */      for (int i = 0; i < arrayOfField.length; i++)
/* 468:    */      {
/* 469:    */        Field localField;
/* 470:459 */        if (((localField = (Field)arrayOfField[i]).getModifiers() == 1) && 
/* 471:460 */          (Streamable.class.isAssignableFrom(localField.getType())))
/* 472:    */        {
/* 480:469 */          ((Streamable)localField.getType().cast(localField.get(this))).setObserver(this);
/* 481:    */        }
/* 482:    */      }
/* 483:    */      
/* 484:473 */      this.observersInitialized = true; return;
/* 485:474 */    } catch (IllegalArgumentException localIllegalArgumentException) { 
/* 486:    */      
/* 491:480 */        localIllegalArgumentException.printStackTrace(); return;
/* 492:    */    }
/* 493:    */    catch (IllegalAccessException localIllegalAccessException) {
/* 494:477 */      
/* 495:    */      
/* 497:480 */        localIllegalAccessException;
/* 498:    */    }
/* 499:    */  }
/* 500:    */  
/* 515:    */  public void appendToDoc(Node paramNode, Document paramDocument)
/* 516:    */  {
/* 517:497 */    Element localElement = paramDocument.createElement("entity");
/* 518:    */    
/* 519:499 */    (
/* 520:500 */      localObject1 = paramDocument.createAttribute("class")).setNodeValue(getClass().getSimpleName());
/* 521:501 */    localElement.setAttributeNode((Attr)localObject1);
/* 522:    */    
/* 523:503 */    Object localObject1 = getClass().getFields();
/* 524:    */    try {
/* 525:505 */      for (int i = 0; i < localObject1.length; i++) {
/* 526:    */        Field localField;
/* 527:    */        String str2;
/* 528:    */        String str1;
/* 529:509 */        if ((localField = (Field)localObject1[i]).getGenericType().equals(String.class))
/* 530:    */        {
/* 532:512 */          str2 = "string";
/* 533:513 */          System.err.println("fieldname " + localField.getName());
/* 534:514 */          str1 = localField.get(this).toString();
/* 535:515 */        } else if (localField.getGenericType().equals(Float.TYPE)) {
/* 536:516 */          str2 = "float";
/* 537:517 */          str1 = localField.get(this).toString();
/* 538:518 */        } else if (localField.getGenericType().equals(Integer.TYPE)) {
/* 539:519 */          str2 = "int";
/* 540:520 */          str1 = localField.get(this).toString();
/* 541:521 */        } else if (localField.getGenericType().equals(Boolean.TYPE)) {
/* 542:522 */          str2 = "boolean";
/* 543:523 */          str1 = localField.get(this).toString(); } else { int j;
/* 544:524 */          if (localField.getGenericType().equals([F.class)) {
/* 545:525 */            localObject2 = (float[])localField.get(this);
/* 546:526 */            str2 = "floatArray";
/* 547:527 */            str1 = "";
/* 548:528 */            for (j = 0; j < localObject2.length; j++) {
/* 549:529 */              str1 = str1 + localObject2[j];
/* 550:530 */              if (j < localObject2.length - 1) {
/* 551:531 */                str1 = str1 + ",";
/* 552:    */              }
/* 553:    */            }
/* 554:534 */          } else if (localField.getGenericType().equals([Ljava.lang.String.class)) {
/* 555:535 */            localObject2 = (String[])localField.get(this);
/* 556:536 */            str2 = "stringArray";
/* 557:537 */            str1 = "";
/* 558:538 */            for (j = 0; j < localObject2.length; j++) {
/* 559:539 */              str1 = str1 + localObject2[j];
/* 560:540 */              if (j < localObject2.length - 1)
/* 561:541 */                str1 = str1 + ",";
/* 562:    */            }
/* 563:    */          } else {
/* 564:544 */            if (!localField.getGenericType().equals([I.class)) break;
/* 565:545 */            localObject2 = (int[])localField.get(this);
/* 566:546 */            str2 = "intArray";
/* 567:547 */            str1 = "";
/* 568:548 */            for (j = 0; j < localObject2.length; j++)
/* 569:    */            {
/* 570:550 */              str1 = str1 + localObject2[j];
/* 571:551 */              if (j < localObject2.length - 1) {
/* 572:552 */                str1 = str1 + ",";
/* 573:    */              }
/* 574:    */            }
/* 575:    */          }
/* 576:    */        }
/* 577:    */        
/* 579:559 */        Object localObject2 = paramDocument.createElement(localField.getName());
/* 580:    */        Attr localAttr;
/* 581:561 */        (localAttr = paramDocument.createAttribute("type")).setNodeValue(str2);
/* 582:562 */        ((Element)localObject2).setAttributeNode(localAttr);
/* 583:563 */        ((Element)localObject2).setTextContent(str1);
/* 584:    */        
/* 585:565 */        localElement.appendChild((Node)localObject2);
/* 586:    */      }
/* 587:567 */    } catch (DOMException localDOMException) { 
/* 588:    */      
/* 593:573 */        localDOMException;
/* 594:    */    } catch (IllegalArgumentException localIllegalArgumentException) {
/* 595:569 */      
/* 596:    */      
/* 599:573 */        localIllegalArgumentException;
/* 600:    */    } catch (IllegalAccessException localIllegalAccessException) {
/* 601:571 */      
/* 602:    */      
/* 603:573 */        localIllegalAccessException;
/* 604:    */    }
/* 605:    */    
/* 607:575 */    System.err.println("appending entity " + getClass().getSimpleName() + ": " + this);
/* 608:    */    
/* 609:577 */    paramNode.appendChild(localElement);
/* 610:    */  }
/* 611:    */  
/* 619:    */  public void clearReceiveBuffers()
/* 620:    */  {
/* 621:589 */    synchronized (this) {
/* 622:590 */      for (Iterator localIterator = this.buffers.iterator(); localIterator.hasNext();) {
/* 623:591 */        ((RemoteBuffer)localIterator.next()).clearReceiveBuffer();
/* 624:    */      }
/* 625:593 */      return;
/* 626:    */    } }
/* 627:    */  
/* 628:    */  private void createFieldMap() { HashMap localHashMap1;
/* 629:    */    HashMap localHashMap2;
/* 630:    */    Object localObject2;
/* 631:599 */    if (fieldMapCache.containsKey(getClass()))
/* 632:    */    {
/* 633:601 */      localHashMap1 = (HashMap)fieldMapCache.get(getClass());
/* 634:602 */      fieldMapKeyCache.get(getClass());localHashMap2 = null;
/* 635:    */    } else {
/* 636:604 */      localHashMap1 = new HashMap();
/* 637:605 */      localHashMap2 = new HashMap();
/* 638:    */      
/* 640:608 */      localObject2 = new String[(localObject1 = getClass().getFields()).length];
/* 641:609 */      for (int i = 0; i < localObject1.length; i++) {
/* 642:610 */        localObject2[i] = localObject1[i].getName();
/* 643:    */      }
/* 644:612 */      assert (localObject2.length < 127);
/* 645:613 */      Arrays.sort((Object[])localObject2);
/* 646:614 */      int j; for (i = 0; i < localObject2.length; j = (byte)(i + 1)) {
/* 647:    */        try {
/* 648:616 */          localObject1 = getClass().getField(localObject2[i]);
/* 649:617 */          localHashMap1.put(Byte.valueOf(i), localObject1);
/* 650:618 */          localHashMap2.put(localObject1, Byte.valueOf(i));
/* 651:619 */        } catch (SecurityException localSecurityException) { 
/* 652:    */          
/* 655:623 */            localSecurityException;
/* 656:    */        } catch (NoSuchFieldException localNoSuchFieldException) {
/* 657:621 */          
/* 658:    */          
/* 659:623 */            localNoSuchFieldException;
/* 660:    */        }
/* 661:    */      }
/* 662:    */      
/* 663:625 */      fieldMapCache.put(getClass(), localHashMap1);
/* 664:626 */      fieldMapKeyCache.put(getClass(), localHashMap2);
/* 665:    */    }
/* 666:628 */    this.buffers = new ObjectArrayList();
/* 667:629 */    this.fieldMap = new Byte2ObjectOpenHashMap();
/* 668:630 */    this.fieldMapKey = new Object2ByteOpenHashMap();
/* 669:    */    
/* 670:632 */    for (Object localObject1 = localHashMap1.entrySet().iterator(); ((Iterator)localObject1).hasNext();) { localObject2 = (Map.Entry)((Iterator)localObject1).next();
/* 671:633 */      if (Streamable.class.isAssignableFrom(((Field)((Map.Entry)localObject2).getValue()).getType())) {
/* 672:    */        try {
/* 673:635 */          localObject3 = (Streamable)((Field)((Map.Entry)localObject2).getValue()).get(this);
/* 674:636 */          assert (localObject3 != null) : (getClass().getSimpleName() + " -> " + ((Field)((Map.Entry)localObject2).getValue()).getName() + ": " + ((Field)((Map.Entry)localObject2).getValue()).getType().getSimpleName());
/* 675:637 */          this.fieldMap.put((Byte)((Map.Entry)localObject2).getKey(), localObject3);
/* 676:638 */          this.fieldMapKey.put(localObject3, (Byte)((Map.Entry)localObject2).getKey());
/* 677:    */          
/* 678:640 */          if ((localObject3 instanceof RemoteBuffer)) {
/* 679:641 */            this.buffers.add((RemoteBuffer)localObject3);
/* 680:    */          }
/* 681:    */        } catch (IllegalArgumentException localIllegalArgumentException) {
/* 682:644 */          (localObject3 = 
/* 683:    */          
/* 687:649 */            localIllegalArgumentException).printStackTrace();throw new RuntimeException(localObject3.getClass() + ": " + ((IllegalArgumentException)localObject3).getMessage());
/* 688:    */        } catch (IllegalAccessException localIllegalAccessException) { Object localObject3;
/* 689:647 */          (localObject3 = localIllegalAccessException).printStackTrace();
/* 690:648 */          throw new RuntimeException(localObject3.getClass() + ": " + ((IllegalAccessException)localObject3).getMessage());
/* 691:    */        }
/* 692:    */      }
/* 693:    */    }
/* 694:652 */    this.fieldMap.trim();
/* 695:653 */    this.fieldMapKey.trim();
/* 696:    */  }
/* 697:    */  
/* 708:    */  public void decodeChange(StateInterface paramStateInterface, DataInputStream paramDataInputStream, short paramShort, boolean paramBoolean, int paramInt)
/* 709:    */  {
/* 710:668 */    decode(paramStateInterface, paramDataInputStream, this, paramShort, paramBoolean, paramInt);
/* 711:    */  }
/* 712:    */  
/* 722:    */  public boolean encodeChange(Sendable paramSendable, DataOutputStream paramDataOutputStream, boolean paramBoolean)
/* 723:    */  {
/* 724:682 */    return encode(paramSendable, paramSendable.getNetworkObject(), false, paramDataOutputStream, false, paramBoolean);
/* 725:    */  }
/* 726:    */  
/* 732:    */  public boolean equals(Object paramObject)
/* 733:    */  {
/* 734:692 */    paramObject = (NetworkObject)paramObject;
/* 735:693 */    return this.id.get() == paramObject.id.get();
/* 736:    */  }
/* 737:    */  
/* 763:    */  public String getChangedFieldsString()
/* 764:    */  {
/* 765:723 */    return new StringBuffer().toString();
/* 766:    */  }
/* 767:    */  
/* 770:    */  public StateInterface getState()
/* 771:    */  {
/* 772:730 */    return this.state;
/* 773:    */  }
/* 774:    */  
/* 777:    */  public boolean isChanged()
/* 778:    */  {
/* 779:737 */    return this.changed;
/* 780:    */  }
/* 781:    */  
/* 784:    */  public boolean isOnServer()
/* 785:    */  {
/* 786:744 */    return this.onServer;
/* 787:    */  }
/* 788:    */  
/* 794:    */  public abstract void onDelete(StateInterface paramStateInterface);
/* 795:    */  
/* 800:    */  public abstract void onInit(StateInterface paramStateInterface);
/* 801:    */  
/* 806:    */  public void setAllFieldsChanged()
/* 807:    */  {
/* 808:766 */    synchronized (this) {
/* 809:767 */      for (Streamable localStreamable : this.fieldMap.values()) {
/* 810:768 */        assert (localStreamable != null);
/* 811:769 */        localStreamable.setChanged(true);
/* 812:    */      }
/* 813:771 */      setChanged(true);
/* 814:772 */      return;
/* 815:    */    }
/* 816:    */  }
/* 817:    */  
/* 822:    */  public void setChanged(boolean paramBoolean)
/* 823:    */  {
/* 824:782 */    this.changed = paramBoolean;
/* 825:    */  }
/* 826:    */  
/* 833:    */  public String toString()
/* 834:    */  {
/* 835:793 */    return "NetworkObject(" + this.id.get() + ")";
/* 836:    */  }
/* 837:    */  
/* 840:    */  public void update(Streamable arg1)
/* 841:    */  {
/* 842:800 */    synchronized (this) {
/* 843:801 */      setChanged(true);
/* 844:802 */      return;
/* 845:    */    }
/* 846:    */  }
/* 847:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.NetworkObject
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */