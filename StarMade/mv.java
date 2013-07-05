/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.common.util.ByteUtil;
/*     */ import org.schema.game.network.objects.NetworkSector;
/*     */ import org.schema.game.network.objects.remote.RemoteItem;
/*     */ import org.schema.game.network.objects.remote.RemoteItemBuffer;
/*     */ import org.schema.schine.network.NetworkStateContainer;
/*     */ import org.schema.schine.network.StateInterface;
/*     */ import org.schema.schine.network.objects.NetworkObject;
/*     */ import org.schema.schine.network.objects.Sendable;
/*     */ import org.schema.schine.network.objects.remote.RemoteBoolean;
/*     */ import org.schema.schine.network.objects.remote.RemoteInteger;
/*     */ import org.schema.schine.network.objects.remote.RemoteVector3i;
/*     */ import org.schema.schine.network.objects.remote.Streamable;
/*     */ import org.schema.schine.network.server.ServerStateInterface;
/*     */ 
/*     */ public class mv
/*     */   implements Sendable
/*     */ {
/*     */   private final StateInterface jdField_a_of_type_OrgSchemaSchineNetworkStateInterface;
/*  38 */   private int jdField_a_of_type_Int = -1;
/*     */   private final boolean jdField_a_of_type_Boolean;
/*     */   private boolean jdField_b_of_type_Boolean;
/*     */   private boolean c;
/*     */   private NetworkSector jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSector;
/*  45 */   private Map jdField_a_of_type_JavaUtilMap = new HashMap();
/*  46 */   private final ArrayList jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/*  47 */   private final ArrayList jdField_b_of_type_JavaUtilArrayList = new ArrayList();
/*     */   private Iterator jdField_a_of_type_JavaUtilIterator;
/*     */   private long jdField_a_of_type_Long;
/* 303 */   private HashSet jdField_a_of_type_JavaUtilHashSet = new HashSet();
/*     */   private mx jdField_a_of_type_Mx;
/*     */ 
/*     */   public mv(StateInterface paramStateInterface)
/*     */   {
/*  51 */     this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface = paramStateInterface;
/*  52 */     this.jdField_a_of_type_Boolean = (paramStateInterface instanceof ServerStateInterface);
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/*  63 */     if (isOnServer())
/*     */     {
/*  65 */       if (a() != null)
/*     */       {
/*  66 */         return "[SERVER RemoteSector(" + this.jdField_a_of_type_Int + ")" + a().a() + "; " + a().a + "]";
/*     */       }
/*     */ 
/*  70 */       return "[SERVER RemoteSector(" + this.jdField_a_of_type_Int + ") sector removed]";
/*     */     }
/*     */ 
/*  73 */     return "[CLIENT ReSector(" + this.jdField_a_of_type_Int + ")" + ((Boolean)this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSector.active.get()).booleanValue() + "; " + a() + "; ]";
/*     */   }
/*     */ 
/*     */   public final q a()
/*     */   {
/*  83 */     return this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSector.pos.getVector();
/*     */   }
/*     */ 
/*     */   public void cleanUpOnEntityDelete()
/*     */   {
/*     */   }
/*     */ 
/*     */   public int getId()
/*     */   {
/*  93 */     return this.jdField_a_of_type_Int;
/*     */   }
/*     */ 
/*     */   public final NetworkSector a()
/*     */   {
/*  98 */     return this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSector;
/*     */   }
/*     */ 
/*     */   public StateInterface getState()
/*     */   {
/* 103 */     return this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface;
/*     */   }
/*     */ 
/*     */   public void initFromNetworkObject(NetworkObject paramNetworkObject)
/*     */   {
/* 109 */     setId(((Integer)paramNetworkObject.id.get()).intValue());
/*     */   }
/*     */ 
/*     */   public void initialize()
/*     */   {
/* 115 */     if (!isOnServer())
/* 116 */       this.jdField_a_of_type_JavaUtilMap = new HashMap();
/*     */   }
/*     */ 
/*     */   public boolean isMarkedForDeleteVolatile()
/*     */   {
/* 146 */     return this.jdField_b_of_type_Boolean;
/*     */   }
/*     */ 
/*     */   public boolean isMarkedForDeleteVolatileSent()
/*     */   {
/* 151 */     return this.c;
/*     */   }
/*     */ 
/*     */   public boolean isOnServer()
/*     */   {
/* 156 */     return this.jdField_a_of_type_Boolean;
/*     */   }
/*     */ 
/*     */   public void newNetworkObject()
/*     */   {
/* 161 */     this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSector = new NetworkSector(getState());
/*     */   }
/*     */ 
/*     */   public void setId(int paramInt)
/*     */   {
/* 166 */     this.jdField_a_of_type_Int = paramInt;
/*     */   }
/*     */ 
/*     */   public void setMarkedForDeleteVolatile(boolean paramBoolean)
/*     */   {
/* 171 */     this.jdField_b_of_type_Boolean = paramBoolean;
/*     */   }
/*     */ 
/*     */   public void setMarkedForDeleteVolatileSent(boolean paramBoolean)
/*     */   {
/* 177 */     this.c = paramBoolean;
/*     */   }
/*     */ 
/*     */   public void updateFromNetworkObject(NetworkObject paramNetworkObject)
/*     */   {
/* 184 */     NetworkSector localNetworkSector = (NetworkSector)paramNetworkObject; paramNetworkObject = this; for (int i = 0; i < localNetworkSector.itemBuffer.getReceiveBuffer().size(); i++)
/*     */     {
/* 184 */       RemoteItem localRemoteItem;
/* 184 */       if ((localRemoteItem = (RemoteItem)localNetworkSector.itemBuffer.getReceiveBuffer().get(i)).isAdd()) paramNetworkObject.a((me)localRemoteItem.get()); else paramNetworkObject.a(((me)localRemoteItem.get()).b());
/*     */     }
/*     */   }
/*     */ 
/*     */   public void updateLocal(xq paramxq)
/*     */   {
/* 211 */     if (isOnServer())
/*     */     {
/* 213 */       paramxq = null; if ((!this.jdField_a_of_type_JavaUtilMap.isEmpty()) || (!this.jdField_a_of_type_JavaUtilArrayList.isEmpty()) || (!this.jdField_b_of_type_JavaUtilArrayList.isEmpty()))
/*     */       {
/* 215 */         for (paramxq = ((vg)getState()).b().values().iterator(); paramxq.hasNext(); )
/*     */         {
/*     */           lE locallE;
/* 216 */           if ((((
/* 216 */             locallE = (lE)paramxq.next()) instanceof lE)) && 
/* 216 */             (locallE.c() == getId()))
/*     */           {
/* 219 */             this.jdField_a_of_type_JavaUtilHashSet.add(locallE);
/*     */           }
/*     */         }
/*     */ 
/* 223 */         a(true);
/* 224 */         this.jdField_a_of_type_JavaUtilHashSet.clear();
/*     */       }
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 233 */       a(false);
/*     */ 
/* 235 */       paramxq = this; if ((!d) && (!(paramxq.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface instanceof ct))) throw new AssertionError(); ((ct)paramxq.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface).a();
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a(Vector3f paramVector3f, short paramShort, int paramInt)
/*     */   {
/* 273 */     if (paramShort != 0)
/* 274 */       a(new me(vg.e++, paramShort, paramInt, paramVector3f));
/*     */   }
/*     */ 
/*     */   private void a(me paramme)
/*     */   {
/* 279 */     synchronized (this.jdField_a_of_type_JavaUtilMap)
/*     */     {
/* 281 */       this.jdField_a_of_type_JavaUtilArrayList.add(paramme);
/* 282 */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a(int paramInt) {
/* 287 */     synchronized (this.jdField_a_of_type_JavaUtilMap)
/*     */     {
/* 289 */       if ((me)this.jdField_a_of_type_JavaUtilMap.get(Integer.valueOf(paramInt)) != null)
/*     */       {
/* 290 */         this.jdField_b_of_type_JavaUtilArrayList.add(Integer.valueOf(paramInt));
/*     */       }
/* 292 */       else System.err.println((isOnServer() ? "[SERVER]" : "[CLIENT]") + "[RemoteSector] WARNING: trying to delete item id that doesn't exist: " + paramInt);
/*     */ 
/* 297 */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void a(boolean paramBoolean)
/*     */   {
/*     */     Object localObject2;
/* 308 */     if (isOnServer()) {
/* 309 */       long l = System.currentTimeMillis();
/* 310 */       if ((this.jdField_a_of_type_JavaUtilIterator != null) && (l - this.jdField_a_of_type_Long > 200L))
/*     */       {
/* 312 */         if (!this.jdField_a_of_type_JavaUtilIterator.hasNext()) {
/* 313 */           this.jdField_a_of_type_JavaUtilIterator = this.jdField_a_of_type_JavaUtilMap.values().iterator();
/*     */         }
/* 315 */         int i = 0;
/* 316 */         while ((this.jdField_a_of_type_JavaUtilIterator.hasNext()) && (i < 100))
/*     */         {
/* 318 */           if (!(
/* 318 */             localObject2 = (me)this.jdField_a_of_type_JavaUtilIterator.next())
/* 318 */             .a(l)) {
/* 319 */             a(((me)localObject2).b());
/*     */           } else {
/* 321 */             if (((me)localObject2).a())
/*     */             {
/* 324 */               if ((((me)localObject2).a(a())) && 
/* 324 */                 (paramBoolean))
/*     */               {
/* 326 */                 this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSector.itemBuffer.add(new RemoteItem((me)localObject2, Boolean.valueOf(true), this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSector));
/*     */               }
/*     */ 
/*     */             }
/*     */ 
/* 332 */             for (Iterator localIterator = this.jdField_a_of_type_JavaUtilHashSet.iterator(); (localIterator.hasNext()) && 
/* 335 */               (!((lE)localIterator.next())
/* 333 */               .a((me)localObject2)); );
/*     */           }
/*     */ 
/* 337 */           i++;
/*     */         }
/*     */ 
/* 346 */         if (!this.jdField_a_of_type_JavaUtilIterator.hasNext())
/* 347 */           this.jdField_a_of_type_Long = l;
/*     */       }
/*     */     }
/*     */     Object localObject1;
/*     */     me localme;
/* 354 */     if (!this.jdField_a_of_type_JavaUtilArrayList.isEmpty()) {
/* 355 */       synchronized (this.jdField_a_of_type_JavaUtilMap) {
/* 356 */         while (!this.jdField_a_of_type_JavaUtilArrayList.isEmpty()) {
/* 357 */           localObject1 = (me)this.jdField_a_of_type_JavaUtilArrayList.remove(0);
/*     */ 
/* 359 */           if ((
/* 359 */             localme = (me)this.jdField_a_of_type_JavaUtilMap.put(Integer.valueOf(((me)localObject1).b()), localObject1)) != null)
/*     */           {
/* 360 */             ((me)localObject1).a(localme.a());
/* 361 */             System.err.println("[REMOTESECTOR] " + getState() + " ITEM change: " + localme + " -> " + localObject1);
/*     */           }
/*     */ 
/* 364 */           System.err.println("[REMOTESECTOR] ITEM ADDED: " + localObject1 + ": Total: " + this.jdField_a_of_type_JavaUtilMap.size());
/* 365 */           if (paramBoolean) {
/* 366 */             this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSector.itemBuffer.add(new RemoteItem((me)localObject1, Boolean.valueOf(true), this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSector));
/*     */           }
/*     */ 
/* 369 */           this.jdField_a_of_type_JavaUtilIterator = this.jdField_a_of_type_JavaUtilMap.values().iterator();
/*     */         }
/*     */       }
/*     */     }
/* 373 */     if (!this.jdField_b_of_type_JavaUtilArrayList.isEmpty())
/* 374 */       synchronized (this.jdField_a_of_type_JavaUtilMap) {
/* 375 */         while (!this.jdField_b_of_type_JavaUtilArrayList.isEmpty()) {
/* 376 */           localObject1 = (Integer)this.jdField_b_of_type_JavaUtilArrayList.remove(0);
/* 377 */           localme = (me)this.jdField_a_of_type_JavaUtilMap.remove(localObject1);
/*     */ 
/* 379 */           if ((paramBoolean) && (localme != null)) {
/* 380 */             localObject2 = new RemoteItem(localme, Boolean.valueOf(false), this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSector);
/*     */ 
/* 382 */             this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSector.itemBuffer.add((Streamable)localObject2);
/* 383 */           } else if (localme == null) {
/* 384 */             System.err.println("[SERVER][REMOTESECTOR] deleted invalid id: " + localObject1);
/*     */           }
/*     */ 
/* 388 */           this.jdField_a_of_type_JavaUtilIterator = this.jdField_a_of_type_JavaUtilMap.values().iterator();
/*     */         }
/* 390 */         return;
/*     */       }
/*     */   }
/*     */ 
/*     */   private mx a()
/*     */   {
/* 401 */     if ((!d) && (!(this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface instanceof vg))) throw new AssertionError();
/* 402 */     return this.jdField_a_of_type_Mx;
/*     */   }
/*     */ 
/*     */   public void updateToFullNetworkObject()
/*     */   {
/* 407 */     this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSector.id.set(Integer.valueOf(getId()));
/* 408 */     mv localmv = this; synchronized (this.jdField_a_of_type_JavaUtilMap)
/*     */     {
/* 408 */       me localme;
/* 408 */       for (Iterator localIterator = localmv.jdField_a_of_type_JavaUtilMap.values().iterator(); localIterator.hasNext(); localmv.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSector.itemBuffer.add(new RemoteItem(localme, Boolean.valueOf(true), localmv.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSector))) localme = (me)localIterator.next(); 
/*     */     }
/* 409 */     this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSector.pos.set(a().a);
/* 410 */     this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSector.active.set(Boolean.valueOf(a().a()));
/*     */     Object localObject2;
/* 412 */     int i = ByteUtil.d((localObject2 = a().a).jdField_a_of_type_Int); int j = ByteUtil.d(((q)localObject2).b); int k = ByteUtil.d(((q)localObject2).c); (localObject2 = new Vector3f()).set(i - 8, j - 8, k - 8); if (((Vector3f)localObject2).length() >= 6.5F) ((Vector3f)localObject2).length();
/*     */ 
/* 416 */     mI.a(a().a);
/*     */   }
/*     */ 
/*     */   public void updateToNetworkObject()
/*     */   {
/* 422 */     if (isOnServer()) {
/* 423 */       this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSector.id.set(Integer.valueOf(getId()));
/* 424 */       this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkSector.active.set(Boolean.valueOf(a().a()));
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a(mx parammx)
/*     */   {
/* 433 */     this.jdField_a_of_type_Int = parammx.a();
/* 434 */     this.jdField_a_of_type_Mx = parammx;
/*     */   }
/*     */ 
/*     */   public final Map a()
/*     */   {
/* 441 */     return this.jdField_a_of_type_JavaUtilMap;
/*     */   }
/*     */ 
/*     */   public final void a(Map paramMap) {
/* 445 */     this.jdField_a_of_type_JavaUtilMap = paramMap;
/* 446 */     this.jdField_a_of_type_JavaUtilIterator = paramMap.values().iterator();
/*     */   }
/*     */ 
/*     */   public void destroyPersistent()
/*     */   {
/*     */   }
/*     */ 
/*     */   public boolean isMarkedForPermanentDelete()
/*     */   {
/* 457 */     return false;
/*     */   }
/*     */ 
/*     */   public void markedForPermanentDelete(boolean paramBoolean)
/*     */   {
/*     */   }
/*     */ 
/*     */   public static void a(vg paramvg, Collection paramCollection)
/*     */   {
/* 468 */     for (lE locallE : paramvg.b().values())
/*     */     {
/*     */       Sendable localSendable;
/* 471 */       if ((
/* 471 */         localSendable = (Sendable)paramvg.getLocalAndRemoteObjectContainer().getLocalObjects().get(locallE.c())) != null)
/*     */       {
/* 472 */         paramCollection.add((mv)localSendable);
/*     */       }
/* 474 */       else System.err.println("[SERVER][REMOTESECTOR] WARNING: REMOTE SECTOR FOR " + locallE + " NOT FOUND: " + locallE.c());
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean isUpdatable()
/*     */   {
/* 483 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     mv
 * JD-Core Version:    0.6.2
 */