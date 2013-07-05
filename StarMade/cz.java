/*     */ import it.unimi.dsi.fastutil.longs.LongSet;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import org.schema.game.common.data.element.ControlElementMap;
/*     */ import org.schema.game.common.data.element.ControlElementMapper;
/*     */ import org.schema.game.common.data.element.ElementCollection;
/*     */ import org.schema.game.network.objects.NetworkPlayer;
/*     */ import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
/*     */ import org.schema.schine.network.objects.remote.RemoteBuffer;
/*     */ import org.schema.schine.network.objects.remote.RemoteIntegerArray;
/*     */ import org.schema.schine.network.objects.remote.RemoteString;
/*     */ 
/*     */ public final class cz
/*     */   implements Ag
/*     */ {
/*     */   private String jdField_a_of_type_JavaLangString;
/*     */   private String b;
/*     */   public ConcurrentHashMap a;
/*     */   public ConcurrentHashMap b;
/*     */   private lE jdField_a_of_type_LE;
/*     */ 
/*     */   public cz(lE paramlE, String paramString)
/*     */   {
/*  22 */     this.jdField_a_of_type_JavaUtilConcurrentConcurrentHashMap = new ConcurrentHashMap();
/*  23 */     this.jdField_b_of_type_JavaUtilConcurrentConcurrentHashMap = new ConcurrentHashMap();
/*     */ 
/*  28 */     this.jdField_b_of_type_JavaLangString = paramlE.getUniqueIdentifier();
/*  29 */     this.jdField_a_of_type_LE = paramlE;
/*  30 */     this.jdField_a_of_type_JavaLangString = paramString;
/*     */   }
/*     */ 
/*     */   public final boolean equals(Object paramObject)
/*     */   {
/*  35 */     return (this.jdField_a_of_type_JavaLangString.equals(((cz)paramObject).jdField_a_of_type_JavaLangString)) && (this.jdField_b_of_type_JavaLangString.equals(((cz)paramObject).jdField_b_of_type_JavaLangString));
/*     */   }
/*     */ 
/*     */   public final void fromTagStructure(Ad paramAd)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final q a(int paramInt)
/*     */   {
/*  44 */     return (q)this.jdField_a_of_type_JavaUtilConcurrentConcurrentHashMap.get(Integer.valueOf(paramInt));
/*     */   }
/*     */ 
/*     */   public final String getUniqueIdentifier()
/*     */   {
/*  55 */     return null;
/*     */   }
/*     */   public final boolean a(int paramInt) {
/*  58 */     return this.jdField_a_of_type_JavaUtilConcurrentConcurrentHashMap.containsKey(Integer.valueOf(paramInt));
/*     */   }
/*     */ 
/*     */   public final int hashCode()
/*     */   {
/*  63 */     return this.jdField_a_of_type_JavaLangString.hashCode() + this.jdField_b_of_type_JavaLangString.hashCode();
/*     */   }
/*     */   public final boolean a(q paramq) {
/*  66 */     return this.jdField_a_of_type_JavaUtilConcurrentConcurrentHashMap.containsValue(paramq);
/*     */   }
/*     */ 
/*     */   public final boolean isVolatile()
/*     */   {
/*  72 */     return false;
/*     */   }
/*     */ 
/*     */   public final boolean a(int paramInt, q paramq, boolean paramBoolean) {
/*  76 */     System.err.println("[SHIP][KEYCONFIG] ASSIGNED Key " + paramInt + " to " + paramq + " on " + this.jdField_a_of_type_JavaLangString + " on " + this.jdField_a_of_type_LE.getState());
/*     */ 
/*  78 */     Object localObject = (q)this.jdField_a_of_type_JavaUtilConcurrentConcurrentHashMap.get(Integer.valueOf(paramInt));
/*  79 */     if (paramq.equals(localObject))
/*     */     {
/*  81 */       return false;
/*     */     }
/*  83 */     if (localObject != null) {
/*  84 */       this.jdField_b_of_type_JavaUtilConcurrentConcurrentHashMap.remove(localObject);
/*     */     }
/*     */ 
/*  87 */     if ((
/*  87 */       localObject = (Integer)this.jdField_b_of_type_JavaUtilConcurrentConcurrentHashMap.get(paramq)) != null)
/*     */     {
/*  88 */       this.jdField_a_of_type_JavaUtilConcurrentConcurrentHashMap.remove(localObject);
/*     */     }
/*     */ 
/*  91 */     this.jdField_a_of_type_JavaUtilConcurrentConcurrentHashMap.put(Integer.valueOf(paramInt), paramq);
/*  92 */     this.jdField_b_of_type_JavaUtilConcurrentConcurrentHashMap.put(paramq, Integer.valueOf(paramInt));
/*  93 */     if (paramBoolean) {
/*  94 */       a(paramInt, paramq, true);
/*     */     }
/*  96 */     return true;
/*     */   }
/*     */ 
/*     */   public final void a(ct paramct, jD paramjD) {
/* 100 */     paramct = paramct.a();
/* 101 */     int i = 0;
/* 102 */     if (paramct != null)
/*     */     {
/* 104 */       q localq = null; long l1 = ElementCollection.getIndex(new q(8, 8, 8));
/*     */ 
/* 105 */       for (paramct = paramct.getControlElementMap().getControllingMap().keySet().iterator(); paramct.hasNext(); )
/*     */       {
/*     */         long l2;
/* 106 */         if ((
/* 106 */           l2 = ((Long)paramct.next()).longValue()) != 
/* 106 */           l1) {
/* 107 */           localq = ElementCollection.getPosFromIndex(l2, new q());
/* 108 */           if (paramjD.a.contains(localq))
/*     */           {
/* 110 */             a(i, localq, true);
/*     */           }
/* 112 */           if (i > 9) break;
/* 113 */           i++;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 118 */       return;
/* 119 */     }System.err.println("[ShipKeyConfig] WARNING: no ship for state but tried to update assignments");
/*     */   }
/*     */ 
/*     */   public final q a(int paramInt, boolean paramBoolean)
/*     */   {
/*     */     q localq;
/* 126 */     if ((
/* 126 */       localq = (q)this.jdField_a_of_type_JavaUtilConcurrentConcurrentHashMap.get(Integer.valueOf(paramInt))) != null)
/*     */     {
/* 127 */       this.jdField_b_of_type_JavaUtilConcurrentConcurrentHashMap.remove(localq);
/* 128 */       this.jdField_a_of_type_JavaUtilConcurrentConcurrentHashMap.remove(Integer.valueOf(paramInt));
/* 129 */       if (paramBoolean) {
/* 130 */         a(paramInt, localq, false);
/*     */       }
/* 132 */       return localq;
/*     */     }
/* 134 */     return null;
/*     */   }
/*     */ 
/*     */   public final int a(q paramq)
/*     */   {
/*     */     Integer localInteger;
/* 139 */     if ((
/* 139 */       localInteger = (Integer)this.jdField_b_of_type_JavaUtilConcurrentConcurrentHashMap.get(paramq)) != null)
/*     */     {
/* 140 */       this.jdField_b_of_type_JavaUtilConcurrentConcurrentHashMap.remove(paramq);
/* 141 */       this.jdField_a_of_type_JavaUtilConcurrentConcurrentHashMap.remove(localInteger);
/* 142 */       a(localInteger.intValue(), paramq, false);
/*     */ 
/* 145 */       return localInteger.intValue();
/*     */     }
/* 147 */     return -1;
/*     */   }
/*     */   private void a(int paramInt, q paramq, boolean paramBoolean) {
/* 150 */     synchronized (this.jdField_a_of_type_LE.a())
/*     */     {
/*     */       RemoteIntegerArray localRemoteIntegerArray;
/* 151 */       (
/* 152 */         localRemoteIntegerArray = new RemoteIntegerArray(4, this.jdField_a_of_type_LE.a()))
/* 152 */         .set(0, Integer.valueOf(paramBoolean ? paramInt : -paramInt - 1));
/* 153 */       localRemoteIntegerArray.set(1, Integer.valueOf(paramq.a));
/* 154 */       localRemoteIntegerArray.set(2, Integer.valueOf(paramq.b));
/* 155 */       localRemoteIntegerArray.set(3, Integer.valueOf(paramq.c));
/* 156 */       this.jdField_a_of_type_LE.a().controllerKeyNameBuffer.add(new RemoteString(this.jdField_a_of_type_JavaLangString, this.jdField_a_of_type_LE.a()));
/* 157 */       this.jdField_a_of_type_LE.a().controllerKeyBuffer.add(localRemoteIntegerArray);
/* 158 */       return;
/*     */     }
/*     */   }
/* 161 */   public final void a() { synchronized (this.jdField_a_of_type_LE.a()) {
/* 162 */       for (Map.Entry localEntry : this.jdField_a_of_type_JavaUtilConcurrentConcurrentHashMap.entrySet()) {
/* 163 */         a(((Integer)localEntry.getKey()).intValue(), (q)localEntry.getValue(), true);
/*     */       }
/* 165 */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   public final String toString()
/*     */   {
/* 173 */     return "ShipKeyConfiguration [shipIdentifier=" + this.jdField_a_of_type_JavaLangString + ", playerIdentifier=" + this.jdField_b_of_type_JavaLangString + ", keyControllerMap=" + this.jdField_a_of_type_JavaUtilConcurrentConcurrentHashMap + ", controllerKeyMap=" + this.jdField_b_of_type_JavaUtilConcurrentConcurrentHashMap + "]";
/*     */   }
/*     */ 
/*     */   public final Ad toTagStructure()
/*     */   {
/* 182 */     return null;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     cz
 * JD-Core Version:    0.6.2
 */