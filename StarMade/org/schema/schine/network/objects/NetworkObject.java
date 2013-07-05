/*     */ package org.schema.schine.network.objects;
/*     */ 
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import it.unimi.dsi.fastutil.bytes.Byte2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.Object2ByteOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import javax.annotation.PostConstruct;
/*     */ import org.schema.schine.network.NetUtil;
/*     */ import org.schema.schine.network.StateInterface;
/*     */ import org.schema.schine.network.client.ClientState;
/*     */ import org.schema.schine.network.objects.remote.NetworkChangeObserver;
/*     */ import org.schema.schine.network.objects.remote.RemoteBoolean;
/*     */ import org.schema.schine.network.objects.remote.RemoteBuffer;
/*     */ import org.schema.schine.network.objects.remote.RemoteInteger;
/*     */ import org.schema.schine.network.objects.remote.Streamable;
/*     */ import org.schema.schine.network.server.ServerStateInterface;
/*     */ import org.schema.schine.network.synchronization.SynchronizationReceiver;
/*     */ import org.schema.schine.network.synchronization.SynchronizationSender;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ 
/*     */ public abstract class NetworkObject
/*     */   implements NetworkChangeObserver
/*     */ {
/*  94 */   public boolean newObject = true;
/*     */ 
/*  96 */   public RemoteInteger id = new RemoteInteger(Integer.valueOf(-123456), this);
/*  97 */   public RemoteInteger clientId = new RemoteInteger(Integer.valueOf(-777777), this);
/*     */ 
/*  99 */   public RemoteBoolean markedDeleted = new RemoteBoolean(false, this);
/*     */   private Byte2ObjectOpenHashMap fieldMap;
/*     */   private Object2ByteOpenHashMap fieldMapKey;
/*     */   private ObjectArrayList buffers;
/*     */   private final boolean onServer;
/*     */   private final StateInterface state;
/* 107 */   private static final ConcurrentHashMap fieldMapCache = new ConcurrentHashMap();
/* 108 */   private static final ConcurrentHashMap fieldMapKeyCache = new ConcurrentHashMap();
/*     */ 
/* 112 */   private boolean changed = true;
/*     */   private boolean observersInitialized;
/*     */   private static final int FIELD_CODE_NOT_CHANGED = 0;
/*     */   private static final int FIELD_CODE_CHANGED = 1;
/*     */   private static final int FIELD_CODE_CHANGED_KEEP_CHANGED = 2;
/* 121 */   public static long objectDebugIdGen = 777777L;
/*     */   public static boolean global_DEBUG;
/*     */ 
/*     */   public static NetworkObject decode(StateInterface paramStateInterface, DataInputStream paramDataInputStream, NetworkObject paramNetworkObject, short arg3, boolean paramBoolean, int paramInt)
/*     */   {
/* 143 */     synchronized (paramDataInputStream)
/*     */     {
/* 147 */       paramBoolean = paramDataInputStream.readByte();
/* 148 */       if (paramStateInterface.isReadingBigChunk()) {
/* 149 */         System.err.println(paramStateInterface + ": decoding NTObject from big chunk: " + paramNetworkObject.getClass().getSimpleName() + "; fields received: " + paramBoolean);
/*     */       }
/*     */ 
/*     */       try
/*     */       {
/* 158 */         for (boolean bool = false; bool < paramBoolean; bool++)
/*     */         {
/* 160 */           byte b = paramDataInputStream.readByte();
/*     */ 
/* 164 */           synchronized (paramNetworkObject)
/*     */           {
/*     */             Streamable localStreamable;
/* 168 */             if ((
/* 168 */               localStreamable = (Streamable)paramNetworkObject.fieldMap.get(b)) == null)
/*     */             {
/* 169 */               System.err.println("Exception: FIELD not found " + b + ": in " + paramNetworkObject.getClass().getSimpleName() + paramNetworkObject.fieldMap + " ONE POSSIBLE REASON: RemoteArray sizes on Server and Client differ");
/*     */ 
/* 172 */               assert (localStreamable != null) : ("not found " + b + ": in " + paramNetworkObject.getClass().getSimpleName() + "; fromStateID: " + paramInt + "; fields:" + paramNetworkObject.fieldMap + " ONE POSSIBLE REASON: RemoteArray sizes on Server and Client differ");
/*     */             }
/*     */ 
/* 179 */             if (paramStateInterface.isReadingBigChunk()) {
/* 180 */               System.err.println(paramStateInterface + ": decoding FIELD from big chunk: " + localStreamable);
/*     */             }
/* 182 */             if ((SynchronizationReceiver.serverDebug) && ((paramStateInterface instanceof ServerStateInterface)))
/* 183 */               System.err.println("DEBUG: changed field: " + localStreamable);
/*     */             try
/*     */             {
/* 186 */               synchronized (localStreamable) {
/* 187 */                 localStreamable.fromByteStream(paramDataInputStream, paramInt);
/*     */               }
/*     */             } catch (Exception localException2) {
/* 190 */               System.err.println("[EXCEPTION] NT ERROR: From senderID: " + paramInt + " for field: " + localStreamable + " in " + paramNetworkObject.getClass().getSimpleName());
/* 191 */               localException2.printStackTrace();
/* 192 */               throw localException2;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       catch (Exception localException3)
/*     */       {
/*     */         Exception localException1;
/* 201 */         (
/* 203 */           localException1 = 
/* 206 */           localException3).printStackTrace();
/* 204 */         System.err.println("Exit because of critical error in nt object");
/* 205 */         throw localException1;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 212 */     return paramNetworkObject;
/*     */   }
/*     */ 
/*     */   public static boolean encode(Sendable paramSendable, NetworkObject paramNetworkObject, boolean paramBoolean1, DataOutputStream paramDataOutputStream, boolean paramBoolean2, boolean paramBoolean3)
/*     */   {
/* 227 */     paramBoolean3 = false;
/*     */ 
/* 238 */     assert (((Integer)paramNetworkObject.id.get()).intValue() >= 0) : (paramNetworkObject.id.get() + " id for remote object never set. local it is " + paramSendable.getId() + ", " + paramSendable + ", " + paramSendable.getState());
/*     */     try
/*     */     {
/* 242 */       assert (paramNetworkObject.observersInitialized) : (paramSendable + ", " + paramNetworkObject + ": " + paramSendable.getState());
/*     */ 
/* 244 */       paramDataOutputStream.writeInt(((Integer)paramNetworkObject.id.get()).intValue());
/*     */ 
/* 251 */       paramDataOutputStream.writeByte(NetUtil.getSendableKey(paramSendable.getClass()));
/*     */ 
/* 259 */       synchronized (paramNetworkObject)
/*     */       {
/* 268 */         int i = 0;
/* 269 */         for (Iterator localIterator = paramNetworkObject.fieldMap.values().iterator(); localIterator.hasNext(); ) { localObject1 = (Streamable)localIterator.next();
/* 270 */           if ((SynchronizationSender.clientDebug) && ((paramSendable.getState() instanceof ClientState))) {
/* 271 */             System.err.println("DEBUG ENCODING ON CLIENT " + localObject1.getClass().getSimpleName());
/*     */           }
/*     */ 
/* 275 */           if (getFieldCodeLength(paramNetworkObject, (Streamable)localObject1, paramDataOutputStream, paramBoolean1))
/*     */           {
/* 276 */             i++;
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 283 */         assert (i <= 127);
/*     */ 
/* 285 */         paramDataOutputStream.writeByte(i);
/* 286 */         int j = 0;
/* 287 */         for (Object localObject1 = paramNetworkObject.fieldMap.values().iterator(); ((Iterator)localObject1).hasNext(); ) { Streamable localStreamable = (Streamable)((Iterator)localObject1).next();
/*     */           int k;
/* 292 */           if ((
/* 292 */             k = getFieldCode(paramNetworkObject, localStreamable, paramDataOutputStream, paramBoolean1, paramBoolean2)) > 0)
/*     */           {
/* 293 */             if (k == 2)
/*     */             {
/* 297 */               paramBoolean3 = true;
/*     */             }
/* 299 */             j++;
/*     */           }
/*     */         }
/*     */ 
/* 303 */         assert (j == i) : (" ENCODING OF " + paramSendable + " failed; forced " + paramBoolean1 + "; " + i + "/" + j);
/*     */ 
/* 310 */         global_DEBUG = false;
/*     */       } } catch (IllegalArgumentException localIllegalArgumentException) { localIllegalArgumentException
/* 318 */         .printStackTrace(); } catch (IllegalAccessException localIllegalAccessException) { localIllegalAccessException.printStackTrace(); }
/*     */ 
/*     */ 
/* 319 */     return paramBoolean3;
/*     */   }
/*     */ 
/*     */   private static int getFieldCode(NetworkObject paramNetworkObject, Streamable paramStreamable, DataOutputStream paramDataOutputStream, boolean paramBoolean1, boolean paramBoolean2)
/*     */   {
/* 327 */     if ((paramBoolean1) && ((paramStreamable instanceof RemoteBuffer)) && (((RemoteBuffer)paramStreamable).isEmpty())) {
/* 328 */       paramStreamable.setChanged(false);
/*     */ 
/* 330 */       return 0;
/*     */     }
/* 332 */     if ((paramBoolean1) || (paramStreamable.hasChanged()))
/*     */     {
/* 334 */       paramNetworkObject = paramNetworkObject.fieldMapKey.get(paramStreamable).byteValue();
/* 335 */       paramDataOutputStream.writeByte(paramNetworkObject);
/* 336 */       paramDataOutputStream.size();
/* 337 */       paramNetworkObject = paramStreamable.toByteStream(paramDataOutputStream);
/* 338 */       paramDataOutputStream.size();
/* 339 */       paramDataOutputStream = 1;
/*     */ 
/* 348 */       if (((!paramBoolean2) && (!paramStreamable.keepChanged())) || (paramStreamable.initialSynchUpdateOnly()))
/* 349 */         paramStreamable.setChanged(false);
/*     */       else {
/* 351 */         paramDataOutputStream = 2;
/*     */       }
/* 353 */       assert (paramNetworkObject > 0) : (paramStreamable.getClass() + ": , Field: " + paramStreamable);
/*     */ 
/* 356 */       return paramDataOutputStream;
/*     */     }
/*     */ 
/* 360 */     return 0;
/*     */   }
/*     */ 
/*     */   private static boolean getFieldCodeLength(NetworkObject paramNetworkObject, Streamable paramStreamable, OutputStream paramOutputStream, boolean paramBoolean)
/*     */   {
/* 369 */     if ((paramBoolean) && ((paramStreamable instanceof RemoteBuffer))) {
/* 370 */       return !((RemoteBuffer)paramStreamable).isEmpty();
/*     */     }
/* 372 */     return (paramBoolean) || (paramStreamable.hasChanged());
/*     */   }
/*     */ 
/*     */   public NetworkObject(StateInterface paramStateInterface)
/*     */   {
/* 445 */     this.onServer = (paramStateInterface instanceof ServerStateInterface);
/* 446 */     this.state = paramStateInterface;
/*     */   }
/*     */   @PostConstruct
/*     */   public void init() {
/* 450 */     createFieldMap();
/*     */   }
/*     */ 
/*     */   public void addObserversForFields() {
/*     */     try {
/* 455 */       Field[] arrayOfField = getClass().getFields();
/* 456 */       for (int i = 0; i < arrayOfField.length; i++)
/*     */       {
/*     */         Field localField;
/* 459 */         if (((
/* 459 */           localField = (Field)arrayOfField[i])
/* 459 */           .getModifiers() == 1) && 
/* 460 */           (Streamable.class.isAssignableFrom(localField.getType())))
/*     */         {
/* 466 */           ((Streamable)localField.getType().cast(localField.get(this)))
/* 469 */             .setObserver(this);
/*     */         }
/*     */ 
/* 473 */       }
/*     */ this.observersInitialized = true;
/*     */       return;
/*     */     } catch (IllegalArgumentException localIllegalArgumentException) {
/* 480 */       localIllegalArgumentException.printStackTrace();
/*     */       return;
/*     */     } catch (IllegalAccessException localIllegalAccessException) { localIllegalAccessException.printStackTrace(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public void appendToDoc(Node paramNode, Document paramDocument)
/*     */   {
/* 497 */     Element localElement = paramDocument.createElement("entity");
/*     */ 
/* 499 */     (
/* 500 */       localObject1 = paramDocument.createAttribute("class"))
/* 500 */       .setNodeValue(getClass().getSimpleName());
/* 501 */     localElement.setAttributeNode((Attr)localObject1);
/*     */ 
/* 503 */     Object localObject1 = getClass().getFields();
/*     */     try {
/* 505 */       for (int i = 0; i < localObject1.length; i++)
/*     */       {
/*     */         Field localField;
/*     */         String str2;
/*     */         String str1;
/* 509 */         if ((
/* 509 */           localField = (Field)localObject1[i])
/* 509 */           .getGenericType().equals(String.class))
/*     */         {
/* 512 */           str2 = "string";
/* 513 */           System.err.println("fieldname " + localField.getName());
/* 514 */           str1 = localField.get(this).toString();
/* 515 */         } else if (localField.getGenericType().equals(Float.TYPE)) {
/* 516 */           str2 = "float";
/* 517 */           str1 = localField.get(this).toString();
/* 518 */         } else if (localField.getGenericType().equals(Integer.TYPE)) {
/* 519 */           str2 = "int";
/* 520 */           str1 = localField.get(this).toString();
/* 521 */         } else if (localField.getGenericType().equals(Boolean.TYPE)) {
/* 522 */           str2 = "boolean";
/* 523 */           str1 = localField.get(this).toString();
/*     */         }
/*     */         else
/*     */         {
/*     */           int j;
/* 524 */           if (localField.getGenericType().equals([F.class)) {
/* 525 */             localObject2 = (float[])localField.get(this);
/* 526 */             str2 = "floatArray";
/* 527 */             str1 = "";
/* 528 */             for (j = 0; j < localObject2.length; j++) {
/* 529 */               str1 = str1 + localObject2[j];
/* 530 */               if (j < localObject2.length - 1)
/* 531 */                 str1 = str1 + ",";
/*     */             }
/*     */           }
/* 534 */           else if (localField.getGenericType().equals([Ljava.lang.String.class)) {
/* 535 */             localObject2 = (String[])localField.get(this);
/* 536 */             str2 = "stringArray";
/* 537 */             str1 = "";
/* 538 */             for (j = 0; j < localObject2.length; j++) {
/* 539 */               str1 = str1 + localObject2[j];
/* 540 */               if (j < localObject2.length - 1)
/* 541 */                 str1 = str1 + ",";
/*     */             }
/*     */           } else {
/* 544 */             if (!localField.getGenericType().equals([I.class)) break;
/* 545 */             localObject2 = (int[])localField.get(this);
/* 546 */             str2 = "intArray";
/* 547 */             str1 = "";
/* 548 */             for (j = 0; j < localObject2.length; j++)
/*     */             {
/* 550 */               str1 = str1 + localObject2[j];
/* 551 */               if (j < localObject2.length - 1) {
/* 552 */                 str1 = str1 + ",";
/*     */               }
/*     */             }
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 559 */         Object localObject2 = paramDocument.createElement(localField.getName());
/*     */         Attr localAttr;
/* 560 */         (
/* 561 */           localAttr = paramDocument.createAttribute("type"))
/* 561 */           .setNodeValue(str2);
/* 562 */         ((Element)localObject2).setAttributeNode(localAttr);
/* 563 */         ((Element)localObject2).setTextContent(str1);
/*     */ 
/* 565 */         localElement.appendChild((Node)localObject2);
/*     */       }
/*     */ 
/*     */     }
/*     */     catch (DOMException localDOMException)
/*     */     {
/* 573 */       localDOMException.printStackTrace();
/*     */     }
/*     */     catch (IllegalArgumentException localIllegalArgumentException)
/*     */     {
/* 573 */       localIllegalArgumentException.printStackTrace();
/*     */     }
/*     */     catch (IllegalAccessException localIllegalAccessException)
/*     */     {
/* 573 */       localIllegalAccessException.printStackTrace();
/*     */     }
/*     */ 
/* 575 */     System.err.println("appending entity " + getClass().getSimpleName() + ": " + this);
/*     */ 
/* 577 */     paramNode.appendChild(localElement);
/*     */   }
/*     */ 
/*     */   public void clearReceiveBuffers()
/*     */   {
/* 589 */     synchronized (this) {
/* 590 */       for (Iterator localIterator = this.buffers.iterator(); localIterator.hasNext(); ) ((RemoteBuffer)localIterator.next())
/* 591 */           .clearReceiveBuffer();
/*     */ 
/* 593 */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void createFieldMap()
/*     */   {
/*     */     HashMap localHashMap1;
/*     */     HashMap localHashMap2;
/*     */     Object localObject2;
/* 599 */     if (fieldMapCache.containsKey(getClass()))
/*     */     {
/* 601 */       localHashMap1 = (HashMap)fieldMapCache.get(getClass());
/* 602 */       fieldMapKeyCache.get(getClass()); localHashMap2 = null;
/*     */     } else {
/* 604 */       localHashMap1 = new HashMap();
/* 605 */       localHashMap2 = new HashMap();
/*     */ 
/* 608 */       localObject2 = new String[(
/* 608 */         localObject1 = getClass().getFields()).length];
/*     */ 
/* 609 */       for (int i = 0; i < localObject1.length; i++) {
/* 610 */         localObject2[i] = localObject1[i].getName();
/*     */       }
/* 612 */       assert (localObject2.length < 127);
/* 613 */       Arrays.sort((Object[])localObject2);
/*     */       int j;
/* 614 */       for (i = 0; i < localObject2.length; j = (byte)(i + 1)) {
/*     */         try {
/* 616 */           localObject1 = getClass().getField(localObject2[i]);
/* 617 */           localHashMap1.put(Byte.valueOf(i), localObject1);
/* 618 */           localHashMap2.put(localObject1, Byte.valueOf(i));
/*     */         }
/*     */         catch (SecurityException localSecurityException)
/*     */         {
/* 623 */           localSecurityException.printStackTrace();
/*     */         }
/*     */         catch (NoSuchFieldException localNoSuchFieldException)
/*     */         {
/* 623 */           localNoSuchFieldException.printStackTrace();
/*     */         }
/*     */       }
/*     */ 
/* 625 */       fieldMapCache.put(getClass(), localHashMap1);
/* 626 */       fieldMapKeyCache.put(getClass(), localHashMap2);
/*     */     }
/* 628 */     this.buffers = new ObjectArrayList();
/* 629 */     this.fieldMap = new Byte2ObjectOpenHashMap();
/* 630 */     this.fieldMapKey = new Object2ByteOpenHashMap();
/*     */ 
/* 632 */     for (Object localObject1 = localHashMap1.entrySet().iterator(); ((Iterator)localObject1).hasNext(); ) { localObject2 = (Map.Entry)((Iterator)localObject1).next();
/* 633 */       if (Streamable.class.isAssignableFrom(((Field)((Map.Entry)localObject2).getValue()).getType())) {
/*     */         try {
/* 635 */           localObject3 = (Streamable)((Field)((Map.Entry)localObject2).getValue()).get(this);
/* 636 */           assert (localObject3 != null) : (getClass().getSimpleName() + " -> " + ((Field)((Map.Entry)localObject2).getValue()).getName() + ": " + ((Field)((Map.Entry)localObject2).getValue()).getType().getSimpleName());
/* 637 */           this.fieldMap.put((Byte)((Map.Entry)localObject2).getKey(), localObject3);
/* 638 */           this.fieldMapKey.put(localObject3, (Byte)((Map.Entry)localObject2).getKey());
/*     */ 
/* 640 */           if ((localObject3 instanceof RemoteBuffer))
/* 641 */             this.buffers.add((RemoteBuffer)localObject3);
/*     */         } catch (IllegalArgumentException localIllegalArgumentException) {
/* 643 */           (
/* 644 */             localObject3 = 
/* 649 */             localIllegalArgumentException).printStackTrace();
/* 645 */           throw new RuntimeException(localObject3.getClass() + ": " + ((IllegalArgumentException)localObject3).getMessage());
/*     */         }
/*     */         catch (IllegalAccessException localIllegalAccessException)
/*     */         {
/*     */           Object localObject3;
/* 646 */           (
/* 647 */             localObject3 = localIllegalAccessException)
/* 647 */             .printStackTrace();
/* 648 */           throw new RuntimeException(localObject3.getClass() + ": " + ((IllegalAccessException)localObject3).getMessage());
/*     */         }
/*     */       }
/*     */     }
/* 652 */     this.fieldMap.trim();
/* 653 */     this.fieldMapKey.trim();
/*     */   }
/*     */ 
/*     */   public void decodeChange(StateInterface paramStateInterface, DataInputStream paramDataInputStream, short paramShort, boolean paramBoolean, int paramInt)
/*     */   {
/* 668 */     decode(paramStateInterface, paramDataInputStream, this, paramShort, paramBoolean, paramInt);
/*     */   }
/*     */ 
/*     */   public boolean encodeChange(Sendable paramSendable, DataOutputStream paramDataOutputStream, boolean paramBoolean)
/*     */   {
/* 682 */     return encode(paramSendable, paramSendable.getNetworkObject(), false, paramDataOutputStream, false, paramBoolean);
/*     */   }
/*     */ 
/*     */   public boolean equals(Object paramObject)
/*     */   {
/* 692 */     paramObject = (NetworkObject)paramObject;
/* 693 */     return this.id.get() == paramObject.id.get();
/*     */   }
/*     */ 
/*     */   public String getChangedFieldsString()
/*     */   {
/* 723 */     return new StringBuffer()
/* 723 */       .toString();
/*     */   }
/*     */ 
/*     */   public StateInterface getState()
/*     */   {
/* 730 */     return this.state;
/*     */   }
/*     */ 
/*     */   public boolean isChanged()
/*     */   {
/* 737 */     return this.changed;
/*     */   }
/*     */ 
/*     */   public boolean isOnServer()
/*     */   {
/* 744 */     return this.onServer;
/*     */   }
/*     */ 
/*     */   public abstract void onDelete(StateInterface paramStateInterface);
/*     */ 
/*     */   public abstract void onInit(StateInterface paramStateInterface);
/*     */ 
/*     */   public void setAllFieldsChanged()
/*     */   {
/* 766 */     synchronized (this) {
/* 767 */       for (Streamable localStreamable : this.fieldMap.values()) {
/* 768 */         assert (localStreamable != null);
/* 769 */         localStreamable.setChanged(true);
/*     */       }
/* 771 */       setChanged(true);
/* 772 */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setChanged(boolean paramBoolean)
/*     */   {
/* 782 */     this.changed = paramBoolean;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 793 */     return "NetworkObject(" + this.id.get() + ")";
/*     */   }
/*     */ 
/*     */   public void update(Streamable arg1)
/*     */   {
/* 800 */     synchronized (this) {
/* 801 */       setChanged(true);
/* 802 */       return;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.NetworkObject
 * JD-Core Version:    0.6.2
 */