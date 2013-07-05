/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.game.common.data.element.meta.MetaObject;
/*     */ import org.schema.game.common.data.element.meta.MetaObjectManager;
/*     */ import org.schema.game.common.data.player.inventory.NoSlotFreeException;
/*     */ import org.schema.game.common.data.world.Universe;
/*     */ import org.schema.game.network.objects.remote.RemoteInventoryMultMod;
/*     */ import org.schema.game.network.objects.remote.RemoteInventoryMultModBuffer;
/*     */ import org.schema.schine.network.NetworkStateContainer;
/*     */ import org.schema.schine.network.StateInterface;
/*     */ import org.schema.schine.network.objects.NetworkObject;
/*     */ import org.schema.schine.network.objects.Sendable;
/*     */ import org.schema.schine.network.objects.remote.RemoteIntArray;
/*     */ import org.schema.schine.network.objects.remote.RemoteIntArrayBuffer;
/*     */ import org.schema.schine.network.server.ServerStateInterface;
/*     */ 
/*     */ public abstract class mf
/*     */   implements Ag
/*     */ {
/*     */   q jdField_a_of_type_Q;
/*     */   protected final Int2ObjectOpenHashMap a;
/*     */   mh jdField_a_of_type_Mh;
/*     */ 
/*     */   public mf(mh parammh, q paramq)
/*     */   {
/*  49 */     this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap = new Int2ObjectOpenHashMap();
/*  50 */     this.jdField_a_of_type_Mh = parammh;
/*  51 */     this.jdField_a_of_type_Q = paramq;
/*     */   }
/*     */ 
/*     */   public final boolean b()
/*     */   {
/*  61 */     return a((short)1) >= 0;
/*     */   }
/*     */ 
/*     */   public void fromTagStructure(Ad paramAd)
/*     */   {
/*  68 */     System.err.println("CLEARING INVENTORY (FROM TAG) " + this.jdField_a_of_type_Q + "; " + this.jdField_a_of_type_Mh);
/*  69 */     this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.clear();
/*     */ 
/*  72 */     if ("inventory".equals(paramAd.a()))
/*     */     {
/*  75 */       Object localObject1 = (
/*  75 */         paramAd = (Ad[])paramAd.a())[
/*  75 */         0];
/*  76 */       Ad[] arrayOfAd = paramAd[1];
/*  77 */       paramAd = paramAd[2];
/*  78 */       localObject1 = (Ad[])((Ad)localObject1).a();
/*  79 */       arrayOfAd = (Ad[])arrayOfAd.a();
/*  80 */       paramAd = (Ad[])paramAd.a();
/*     */ 
/*  82 */       for (int i = 0; i < localObject1.length; i++)
/*     */       {
/*  84 */         if (paramAd[i].a() == Af.d)
/*     */         {
/*  86 */           b(((Integer)localObject1[i].a()).intValue(), ((Short)arrayOfAd[i].a()).shortValue(), ((Integer)paramAd[i].a()).intValue()); } else {
/*  87 */           if (paramAd[i].a() == Af.n)
/*     */           {
/*  89 */             ((Integer)(
/*  89 */               localObject2 = (Ad[])paramAd[i].a())[
/*  89 */               0].a()).intValue();
/*     */             MetaObject localMetaObject1;
/*  92 */             (
/*  93 */               localMetaObject1 = MetaObjectManager.instantiate(((Short)localObject2[1].a()).shortValue()))
/*  93 */               .fromTag(localObject2[2]);
/*  94 */             MetaObject localMetaObject2 = localMetaObject1; int k = ((Short)arrayOfAd[i].a()).shortValue(); int j = ((Integer)localObject1[i].a()).intValue(); Object localObject2 = this; if (k == 0) synchronized (((mf)localObject2).jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap) { ((mf)localObject2).jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.remove(j); } if (( = (mj)((mf)localObject2).jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.get(j)) == null) { ( = new mj()).jdField_a_of_type_Short = localObject3; ((mj)???).jdField_a_of_type_Int = j; } ((mj)???).jdField_a_of_type_JavaLangObject = localMetaObject2; ((mj)???).jdField_a_of_type_Short = localObject3; if ((!jdField_a_of_type_Boolean) && (((mj)???).jdField_a_of_type_Int != j)) throw new AssertionError(); if ((!jdField_a_of_type_Boolean) && (((mj)???).jdField_a_of_type_Short != localObject3)) throw new AssertionError("SLOT: " + ((mj)???).jdField_a_of_type_Short + " TRANSMIT: " + localObject3); synchronized (((mf)localObject2).jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap) { ((mf)localObject2).jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.put(j, ???); } 
/*  95 */           }if (!jdField_a_of_type_Boolean) throw new AssertionError();
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 100 */       return;
/* 101 */     }if (!jdField_a_of_type_Boolean) throw new AssertionError();
/*     */   }
/*     */ 
/*     */   public Ad toTagStructure()
/*     */   {
/* 111 */     System.currentTimeMillis();
/*     */ 
/* 113 */     Ad localAd1 = new Ad("slots", Af.d);
/* 114 */     Ad localAd2 = new Ad("types", Af.c);
/* 115 */     Ad localAd3 = new Ad("values", Af.d);
/*     */ 
/* 117 */     synchronized (this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap)
/*     */     {
/* 119 */       for (Object localObject2 : this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.keySet()) {
/* 120 */         if (!a(((Integer)localObject2).intValue())) {
/* 121 */           Object localObject3 = (mj)this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.get((Integer)localObject2);
/* 122 */           if ((!jdField_a_of_type_Boolean) && (((Integer)localObject2).intValue() != ((mj)localObject3).jdField_a_of_type_Int)) throw new AssertionError();
/* 123 */           localAd1.a(new Ad(Af.d, null, localObject2));
/* 124 */           localAd2.a(new Ad(Af.c, null, Short.valueOf(((mj)localObject3).jdField_a_of_type_Short)));
/* 125 */           localObject2 = (MetaObject)((mj)localObject2).jdField_a_of_type_JavaLangObject; (localObject3 = new Ad[4])[0] = new Ad(Af.d, null, Integer.valueOf(((MetaObject)localObject2).getId())); localObject3[1] = new Ad(Af.c, null, Short.valueOf(((MetaObject)localObject2).getObjectBlockID())); localObject3[2] = ((MetaObject)localObject2).getBytesTag(); localObject3[2] = new Ad(Af.a, null, null); localAd3.a(((localObject2 = localObject3).jdField_a_of_type_JavaLangObject instanceof Integer) ? new Ad(Af.d, null, (Integer)((mj)localObject2).jdField_a_of_type_JavaLangObject) : new Ad(Af.n, null, (Ad[])localObject3));
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 132 */     return new Ad(Af.n, "inventory", new Ad[] { localObject1, localAd2, localAd3, new Ad(Af.a, null, null) });
/*     */   }
/*     */ 
/*     */   private mj a(int paramInt)
/*     */   {
/* 137 */     return (mj)this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.get(paramInt);
/*     */   }
/*     */   public abstract int a();
/*     */ 
/*     */   public final int a(int paramInt) {
/* 141 */     if (!a(paramInt)) {
/* 142 */       return a(paramInt).a();
/*     */     }
/* 144 */     return 0;
/*     */   }
/*     */ 
/*     */   public final int a(short paramShort)
/*     */   {
/* 156 */     synchronized (this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap) {
/* 157 */       for (Integer localInteger : this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.keySet()) {
/* 158 */         if ((!a(localInteger.intValue())) && (a(localInteger.intValue()) == paramShort)) {
/* 159 */           return localInteger.intValue();
/*     */         }
/*     */       }
/*     */     }
/* 163 */     return -1;
/*     */   }
/*     */ 
/*     */   public abstract int b();
/*     */ 
/*     */   public int c()
/*     */   {
/* 174 */     return 0;
/*     */   }
/*     */ 
/*     */   public final int b(short paramShort) {
/* 178 */     int i = 0;
/*     */     Iterator localIterator;
/* 180 */     synchronized (this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap) {
/* 181 */       for (localIterator = this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.values().iterator(); localIterator.hasNext(); )
/*     */       {
/*     */         mj localmj;
/* 182 */         if ((
/* 182 */           localmj = (mj)localIterator.next()).jdField_a_of_type_Short == 
/* 182 */           paramShort) {
/* 183 */           i += localmj.a();
/*     */         }
/*     */       }
/*     */     }
/* 187 */     return i;
/*     */   }
/*     */ 
/*     */   public final void a(short paramShort, Collection paramCollection)
/*     */   {
/* 192 */     int i = 0;
/* 193 */     while ((i = a(paramShort)) >= 0) {
/* 194 */       this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.remove(i);
/* 195 */       paramCollection.add(Integer.valueOf(i));
/*     */     }
/*     */   }
/*     */ 
/*     */   public final q a()
/*     */   {
/* 203 */     return this.jdField_a_of_type_Q;
/*     */   }
/*     */ 
/*     */   public abstract int d();
/*     */ 
/*     */   public final short a(int paramInt)
/*     */   {
/* 210 */     if (!a(paramInt)) {
/* 211 */       return a(paramInt).jdField_a_of_type_Short;
/*     */     }
/* 213 */     return 0;
/*     */   }
/*     */ 
/*     */   public String getUniqueIdentifier()
/*     */   {
/* 218 */     if ((!jdField_a_of_type_Boolean) && (this.jdField_a_of_type_Mh.getName() == null)) throw new AssertionError();
/* 219 */     return this.jdField_a_of_type_Mh.getName() + "_" + this.jdField_a_of_type_Q + "_inventory";
/*     */   }
/*     */ 
/*     */   public final void a(ml paramml)
/*     */   {
/*     */     Object localObject1;
/*     */     Object localObject2;
/* 223 */     if (paramml.getInventoryUpdateBuffer().getReceiveBuffer().size() > 0)
/*     */     {
/* 225 */       localObject1 = new HashSet();
/* 226 */       for (localObject2 = paramml.getInventoryUpdateBuffer().getReceiveBuffer().iterator(); ((Iterator)localObject2).hasNext(); )
/*     */       {
/* 228 */         Integer localInteger1 = Integer.valueOf((
/* 228 */           localObject3 = (RemoteIntArray)((Iterator)localObject2).next())
/* 228 */           .getIntArray()[0]);
/* 229 */         Integer localInteger2 = Integer.valueOf(localObject3.getIntArray()[1]);
/* 230 */         Object localObject3 = Integer.valueOf(localObject3.getIntArray()[2]);
/*     */ 
/* 233 */         b(localInteger1.intValue(), localInteger2.shortValue(), localInteger2.shortValue() != 0 ? ((Integer)localObject3).intValue() : 0);
/*     */ 
/* 235 */         ((HashSet)localObject1).add(localInteger1);
/*     */ 
/* 239 */         if ((this.jdField_a_of_type_Mh.getState() instanceof ServerStateInterface)) {
/* 240 */           a((Collection)localObject1);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 245 */     if (paramml.getInventoryMultModBuffer().getReceiveBuffer().size() > 0)
/* 246 */       for (localObject1 = paramml.getInventoryMultModBuffer().getReceiveBuffer().iterator(); ((Iterator)localObject1).hasNext(); ) { localObject2 = (RemoteInventoryMultMod)((Iterator)localObject1).next();
/* 247 */         a((mi)((RemoteInventoryMultMod)localObject2).get());
/*     */       }
/*     */   }
/*     */ 
/*     */   public final void a(int paramInt1, short paramShort, int paramInt2)
/*     */   {
/*     */     mj localmj;
/* 255 */     if ((
/* 255 */       localmj = a(paramInt1)) != null)
/*     */     {
/*     */       long l;
/* 258 */       if ((
/* 258 */         l = localmj.a() + paramInt2) > 
/* 258 */         2147483647L)
/* 259 */         paramInt2 = 2147483646;
/* 260 */       else if (l < -2147483648L)
/* 261 */         paramInt2 = 0;
/*     */       else {
/* 263 */         paramInt2 = (int)l;
/*     */       }
/*     */ 
/* 269 */       b(paramInt1, paramShort, Math.max(0, paramInt2));
/* 270 */       return;
/* 271 */     }if (paramInt2 < 0) {
/* 272 */       if (!jdField_a_of_type_Boolean) throw new AssertionError("TRYING TO SET INVENTORY TO NEGATIVE VALUE"); 
/*     */     }
/*     */     else
/* 274 */       b(paramInt1, paramShort, Math.max(0, paramInt2));
/*     */   }
/*     */ 
/*     */   public final int a(short paramShort, int paramInt)
/*     */   {
/*     */     int i;
/* 282 */     if ((
/* 282 */       i = a(paramShort)) >= 0)
/*     */     {
/* 283 */       a(i, paramShort, paramInt);
/* 284 */       return i;
/*     */     }
/*     */ 
/* 287 */     throw new NoSlotFreeException();
/*     */   }
/*     */ 
/*     */   public final int a(ml paramml)
/*     */   {
/*     */     int i;
/* 293 */     if ((
/* 293 */       i = a((short)1)) >= 0)
/*     */     {
/* 294 */       a(i, (short)1, -1);
/* 295 */       a(paramml, paramml.getInventoryUpdateBuffer(), i);
/* 296 */       return i;
/*     */     }
/*     */ 
/* 299 */     throw new NoSlotFreeException();
/*     */   }
/*     */   public final int b(short paramShort, int paramInt) {
/* 302 */     synchronized (this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap) {
/* 303 */       for (Integer localInteger : this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.keySet()) {
/* 304 */         if ((!a(localInteger.intValue())) && (a(localInteger.intValue()) == paramShort)) {
/* 305 */           a(localInteger.intValue(), paramShort, paramInt);
/* 306 */           return localInteger.intValue();
/*     */         }
/*     */       }
/*     */     }
/* 310 */     return c(paramShort, paramInt);
/*     */   }
/*     */ 
/*     */   public final boolean c()
/*     */   {
/* 316 */     return this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.isEmpty();
/*     */   }
/*     */ 
/*     */   public final boolean a(int paramInt)
/*     */   {
/* 323 */     return ((
/* 323 */       paramInt = a(paramInt)) == null) || 
/* 323 */       (paramInt.a() <= 0) || (paramInt.jdField_a_of_type_Short == 0);
/*     */   }
/*     */ 
/*     */   public boolean isVolatile() {
/* 327 */     return false;
/*     */   }
/*     */ 
/*     */   public final void b(int paramInt1, short arg2, int paramInt2)
/*     */   {
/* 352 */     if (??? == 0) {
/* 353 */       synchronized (this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap) {
/* 354 */         this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.remove(paramInt1);
/* 355 */         return;
/*     */       }
/*     */     }
/* 358 */     if (( = (mj)this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.get(paramInt1)) == null)
/*     */     {
/* 359 */       ( = new mj()).jdField_a_of_type_Short = 
/* 360 */         ???;
/* 361 */       ((mj)???).jdField_a_of_type_Int = paramInt1;
/*     */     }
/* 363 */     ((mj)???).b(paramInt2);
/* 364 */     ((mj)???).jdField_a_of_type_Short = ???;
/*     */ 
/* 366 */     if ((!jdField_a_of_type_Boolean) && (((mj)???).jdField_a_of_type_Int != paramInt1)) throw new AssertionError();
/* 367 */     if ((!jdField_a_of_type_Boolean) && (((mj)???).jdField_a_of_type_Short != ???)) throw new AssertionError("SLOT: " + ((mj)???).jdField_a_of_type_Short + " TRANSMIT: " + ???);
/* 368 */     synchronized (this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap) {
/* 369 */       this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.put(paramInt1, ???);
/*     */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   public final int c(short paramShort, int paramInt)
/*     */   {
/* 378 */     for (int i = 0; (i < d()) || (d() < 0); i++)
/*     */     {
/* 380 */       if (a(i)) {
/* 381 */         b(i, paramShort, paramInt);
/*     */ 
/* 383 */         return i;
/*     */       }
/*     */     }
/* 386 */     throw new NoSlotFreeException();
/*     */   }
/*     */ 
/*     */   private void a(ml paramml, RemoteIntArrayBuffer paramRemoteIntArrayBuffer, int paramInt)
/*     */   {
/* 394 */     if (!a(paramInt)) {
/* 395 */       a(paramml, paramRemoteIntArrayBuffer, a(paramInt));
/*     */     }
/* 399 */     else if (paramRemoteIntArrayBuffer.getArraySize() == 6) {
/* 400 */       (
/* 401 */         paramml = new RemoteIntArray(6, (NetworkObject)paramml))
/* 401 */         .set(0, paramInt);
/* 402 */       paramml.set(1, 0);
/* 403 */       paramml.set(2, 0);
/* 404 */       paramml.set(3, this.jdField_a_of_type_Q.jdField_a_of_type_Int);
/* 405 */       paramml.set(4, this.jdField_a_of_type_Q.b);
/* 406 */       paramml.set(5, this.jdField_a_of_type_Q.c);
/* 407 */       paramRemoteIntArrayBuffer.add(paramml);
/*     */     }
/*     */     else {
/* 410 */       (
/* 411 */         paramml = new RemoteIntArray(3, (NetworkObject)paramml))
/* 411 */         .set(0, paramInt);
/* 412 */       paramml.set(1, 0);
/* 413 */       paramml.set(2, 0);
/*     */ 
/* 415 */       paramRemoteIntArrayBuffer.add(paramml);
/*     */     }
/*     */ 
/* 420 */     if (paramRemoteIntArrayBuffer.size() > 200)
/*     */       try {
/* 422 */         if ((this.jdField_a_of_type_Mh != null) && ((this.jdField_a_of_type_Mh instanceof Sendable)))
/*     */         {
/* 424 */           paramInt = (Sendable)(
/* 424 */             paramml = (Sendable)this.jdField_a_of_type_Mh)
/* 424 */             .getState().getLocalAndRemoteObjectContainer().getLocalObjects().get(paramml.getId());
/* 425 */           NetworkObject localNetworkObject = (NetworkObject)paramml.getState().getLocalAndRemoteObjectContainer().getRemoteObjects().get(paramml.getId());
/* 426 */           throw new IllegalArgumentException("WARNING: inventory high of " + paramml + ": " + paramRemoteIntArrayBuffer.size() + "; " + paramInt + "; " + localNetworkObject);
/*     */         }
/* 428 */         throw new IllegalArgumentException("WARNING: inventory high: " + paramRemoteIntArrayBuffer.size() + "; no inventory holder "); } catch (IllegalArgumentException localIllegalArgumentException) { localIllegalArgumentException
/* 430 */           .printStackTrace();
/*     */       }
/*     */   }
/*     */ 
/*     */   private void a(ml paramml, RemoteIntArrayBuffer paramRemoteIntArrayBuffer, mj parammj)
/*     */   {
/* 438 */     if (paramRemoteIntArrayBuffer.getArraySize() == 6) {
/* 439 */       (
/* 440 */         paramml = new RemoteIntArray(6, (NetworkObject)paramml))
/* 440 */         .set(0, parammj.jdField_a_of_type_Int);
/* 441 */       paramml.set(1, parammj.jdField_a_of_type_Short);
/* 442 */       paramml.set(2, parammj.a());
/* 443 */       paramml.set(3, this.jdField_a_of_type_Q.jdField_a_of_type_Int);
/* 444 */       paramml.set(4, this.jdField_a_of_type_Q.b);
/* 445 */       paramml.set(5, this.jdField_a_of_type_Q.c);
/* 446 */       paramRemoteIntArrayBuffer.add(paramml);
/* 447 */       return;
/* 448 */     }(
/* 449 */       paramml = new RemoteIntArray(3, (NetworkObject)paramml))
/* 449 */       .set(0, parammj.jdField_a_of_type_Int);
/* 450 */     paramml.set(1, parammj.jdField_a_of_type_Short);
/* 451 */     paramml.set(2, parammj.a());
/*     */ 
/* 453 */     paramRemoteIntArrayBuffer.add(paramml);
/*     */   }
/*     */ 
/*     */   public final void b(ml paramml)
/*     */   {
/* 462 */     synchronized (this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap) {
/* 463 */       for (mj localmj : this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.values()) {
/* 464 */         a(paramml, paramml.getInventoryUpdateBuffer(), localmj);
/*     */       }
/* 466 */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 491 */     a(paramInt1, paramInt2, this, paramInt3);
/*     */   }
/*     */ 
/*     */   public final void a(int paramInt1, int paramInt2, mf parammf, int paramInt3)
/*     */   {
/* 560 */     mj localmj1 = a(paramInt1);
/* 561 */     mj localmj2 = parammf.a(paramInt2);
/*     */ 
/* 563 */     synchronized (this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap) {
/* 564 */       if ((localmj1 != null) && (localmj2 != null) && (localmj1.jdField_a_of_type_Short == localmj2.jdField_a_of_type_Short) && (localmj1 != localmj2)) {
/* 565 */         System.err.println("MERGING SLOT");
/* 566 */         localmj1.a(paramInt3);
/* 567 */         localmj2.a(-paramInt3);
/* 568 */         if (localmj2.a() <= 0)
/* 569 */           parammf.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.remove(paramInt2);
/*     */       }
/*     */       else {
/* 572 */         if (localmj1 != null) {
/* 573 */           System.err.println("COUTNSFDN: " + paramInt3 + " of " + localmj1.a() + " (other: " + localmj2 + ")");
/*     */         }
/* 575 */         if ((localmj1 != null) && (localmj2 != null) && (localmj1.jdField_a_of_type_Short != localmj2.jdField_a_of_type_Short) && (localmj1.jdField_a_of_type_Short != 0)) {
/* 576 */           if ((localmj2.a() == paramInt3) || (localmj1.a() == 0) || (localmj2.a() == 0)) {
/* 577 */             localmj1.jdField_a_of_type_Int = paramInt2;
/* 578 */             if ((!jdField_a_of_type_Boolean) && (localmj1.jdField_a_of_type_Short == 0)) throw new AssertionError();
/* 579 */             parammf.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.put(paramInt2, localmj1);
/* 580 */             this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.remove(paramInt1);
/*     */ 
/* 582 */             localmj2.jdField_a_of_type_Int = paramInt1;
/* 583 */             this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.put(paramInt1, localmj2);
/* 584 */             if (parammf.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.get(paramInt2) != localmj1)
/* 585 */               parammf.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.remove(paramInt2);
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/*     */           mj localmj3;
/* 590 */           if ((localmj1 != null) && (
/* 591 */             (localmj2 == null) || (localmj2.a() == 0))) {
/* 592 */             (
/* 593 */               localmj3 = new mj(localmj1, paramInt2))
/* 593 */               .b(paramInt3);
/* 594 */             if ((!jdField_a_of_type_Boolean) && (localmj3.jdField_a_of_type_Short == 0)) throw new AssertionError();
/* 595 */             parammf.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.put(paramInt2, localmj3);
/*     */ 
/* 598 */             if ((
/* 598 */               localmj3 = (mj)this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.get(paramInt1)) != null)
/*     */             {
/* 599 */               localmj3.a(-paramInt3);
/* 600 */               if (localmj3.a() <= 0) {
/* 601 */                 this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.remove(paramInt1);
/*     */               }
/*     */             }
/*     */ 
/*     */           }
/*     */ 
/* 607 */           if ((localmj2 != null) && (
/* 608 */             (localmj1 == null) || (localmj1.a() == 0))) {
/* 609 */             (
/* 610 */               localmj3 = new mj(localmj2, paramInt1))
/* 610 */               .b(paramInt3);
/* 611 */             if ((!jdField_a_of_type_Boolean) && (localmj3.jdField_a_of_type_Short == 0)) throw new AssertionError();
/* 612 */             this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.put(paramInt1, localmj3);
/*     */ 
/* 616 */             if ((
/* 616 */               localmj3 = (mj)parammf.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.get(paramInt2)) != null)
/*     */             {
/* 617 */               localmj3.a(-paramInt3);
/* 618 */               if (localmj3.a() <= 0) {
/* 619 */                 parammf.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.remove(paramInt2);
/*     */               }
/*     */ 
/*     */             }
/*     */ 
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 647 */     a(paramInt1);
/* 648 */     parammf.a(paramInt2);
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 655 */     return "Inventory: (" + c() + "; " + this.jdField_a_of_type_Q + ")" + this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.toString();
/*     */   }
/*     */ 
/*     */   public final int a(DataOutputStream paramDataOutputStream)
/*     */   {
/* 665 */     int i = 4;
/* 666 */     synchronized (this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap) {
/* 667 */       paramDataOutputStream.writeInt(this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.size());
/*     */ 
/* 669 */       for (mj localmj : this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.values()) {
/* 670 */         paramDataOutputStream.writeInt(localmj.jdField_a_of_type_Int);
/* 671 */         paramDataOutputStream.writeShort(localmj.jdField_a_of_type_Short);
/* 672 */         paramDataOutputStream.writeInt(localmj.a());
/* 673 */         i += 10;
/*     */       }
/*     */     }
/* 676 */     return i;
/*     */   }
/*     */   public final void a(DataInputStream paramDataInputStream) {
/* 679 */     int i = paramDataInputStream.readInt();
/* 680 */     for (int j = 0; j < i; j++) {
/* 681 */       int k = paramDataInputStream.readInt();
/* 682 */       short s = paramDataInputStream.readShort();
/* 683 */       int m = paramDataInputStream.readInt();
/* 684 */       b(k, s, m);
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a(mF arg1)
/*     */   {
/*     */     mx localmx;
/* 690 */     if ((
/* 690 */       localmx = ((vg)???.getState()).a().getSector(???.getSectorId())) != null)
/*     */     {
/* 691 */       System.err.println("[INVENTORY][SPAWNING] spawning inventory at " + this.jdField_a_of_type_Q);
/* 692 */       Vector3f localVector3f1 = new Vector3f(this.jdField_a_of_type_Q.jdField_a_of_type_Int - 8, this.jdField_a_of_type_Q.b - 8, this.jdField_a_of_type_Q.c - 8);
/* 693 */       ???.getWorldTransform().transform(localVector3f1);
/* 694 */       synchronized (this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap) {
/* 695 */         for (Iterator localIterator = this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.values().iterator(); localIterator.hasNext(); )
/*     */         {
/*     */           mj localmj;
/* 696 */           if ((
/* 696 */             localmj = (mj)localIterator.next()).jdField_a_of_type_Short != 0)
/*     */           {
/*     */             Vector3f localVector3f2;
/*     */             void tmp157_155 = (
/* 698 */               localVector3f2 = new Vector3f(localVector3f1));
/* 698 */             tmp157_155.x = ((float)(tmp157_155.x + (Math.random() - 0.5D)));
/*     */             Vector3f tmp176_174 = localVector3f2; tmp176_174.y = ((float)(tmp176_174.y + (Math.random() - 0.5D)));
/*     */             Vector3f tmp195_193 = localVector3f2; tmp195_193.z = ((float)(tmp195_193.z + (Math.random() - 0.5D)));
/* 701 */             System.err.println("[INVENTORY][SPAWNING] spawning inventory at " + this.jdField_a_of_type_Q + " -> " + localVector3f2);
/* 702 */             localmx.a().a(localVector3f2, localmj.jdField_a_of_type_Short, localmj.a());
/*     */           }
/*     */         }
/* 705 */         return; } 
/* 706 */     }System.err.println("[INVENTORY][SPAWN] sector null of " + ???);
/*     */   }
/*     */ 
/*     */   private void a(int paramInt)
/*     */   {
/* 713 */     this.jdField_a_of_type_Mh.sendInventoryModification(paramInt, this.jdField_a_of_type_Q);
/*     */   }
/*     */ 
/*     */   public final void a(Collection paramCollection) {
/* 717 */     this.jdField_a_of_type_Mh.sendInventoryModification(paramCollection, this.jdField_a_of_type_Q);
/*     */   }
/*     */ 
/*     */   public final void a(RemoteIntArray paramRemoteIntArray, ml paramml) {
/* 721 */     Integer localInteger1 = Integer.valueOf(paramRemoteIntArray.getIntArray()[0]);
/* 722 */     Integer localInteger2 = Integer.valueOf(paramRemoteIntArray.getIntArray()[1]);
/* 723 */     paramRemoteIntArray = Integer.valueOf(paramRemoteIntArray.getIntArray()[2]);
/*     */ 
/* 726 */     b(localInteger1.intValue(), localInteger2.shortValue(), localInteger2.shortValue() != 0 ? paramRemoteIntArray.intValue() : 0);
/*     */ 
/* 728 */     if ((this.jdField_a_of_type_Mh.getState() instanceof ServerStateInterface))
/* 729 */       a(paramml, paramml.getInventoryUpdateBuffer(), localInteger1.intValue());
/*     */   }
/*     */ 
/*     */   public final void a(mi parammi)
/*     */   {
/* 736 */     ArrayList localArrayList = null;
/* 737 */     if ((this.jdField_a_of_type_Mh.getState() instanceof ServerStateInterface)) {
/* 738 */       localArrayList = new ArrayList();
/*     */     }
/* 740 */     for (int i = 0; i < parammi.a.length; i++)
/*     */     {
/*     */       mj localmj;
/* 742 */       if ((
/* 742 */         localmj = parammi.a[i]).jdField_a_of_type_Short == 0)
/*     */       {
/* 743 */         this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.remove(localmj.jdField_a_of_type_Int);
/*     */       }
/* 745 */       else this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.put(localmj.jdField_a_of_type_Int, localmj);
/*     */ 
/* 747 */       if ((this.jdField_a_of_type_Mh.getState() instanceof ServerStateInterface)) {
/* 748 */         localArrayList.add(Integer.valueOf(localmj.jdField_a_of_type_Int));
/*     */       }
/*     */     }
/* 751 */     if ((this.jdField_a_of_type_Mh.getState() instanceof ServerStateInterface))
/* 752 */       this.jdField_a_of_type_Mh.sendInventoryModification(localArrayList, this.jdField_a_of_type_Q);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     mf
 * JD-Core Version:    0.6.2
 */