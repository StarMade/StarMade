/*    */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*    */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*    */ import it.unimi.dsi.fastutil.shorts.Short2ObjectArrayMap;
/*    */ import it.unimi.dsi.fastutil.shorts.ShortOpenHashSet;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import org.schema.game.network.objects.NetworkClientChannel;
/*    */ import org.schema.game.network.objects.remote.RemoteMissileUpdate;
/*    */ import org.schema.game.network.objects.remote.RemoteMissileUpdateBuffer;
/*    */ import org.schema.schine.network.objects.remote.RemoteBuffer;
/*    */ import org.schema.schine.network.objects.remote.RemoteShort;
/*    */ 
/*    */ public final class lj
/*    */ {
/*    */   private ct jdField_a_of_type_Ct;
/* 26 */   private Short2ObjectArrayMap jdField_a_of_type_ItUnimiDsiFastutilShortsShort2ObjectArrayMap = new Short2ObjectArrayMap();
/* 27 */   private ShortOpenHashSet jdField_a_of_type_ItUnimiDsiFastutilShortsShortOpenHashSet = new ShortOpenHashSet();
/*    */ 
/* 29 */   private ArrayList jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/*    */ 
/*    */   public lj(ct paramct) {
/* 32 */     this.jdField_a_of_type_Ct = paramct;
/*    */   }
/*    */ 
/*    */   public final void a(xq paramxq, t paramt)
/*    */   {
/* 40 */     Object localObject = paramt; paramt = this; for (Iterator localIterator = this.jdField_a_of_type_JavaUtilArrayList.iterator(); localIterator.hasNext(); ((lw)localIterator.next()).a(paramt.jdField_a_of_type_Ct, paramt.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2ObjectArrayMap, (t)localObject));
/* 40 */     paramt.jdField_a_of_type_JavaUtilArrayList.clear();
/*    */ 
/* 42 */     for (paramt = this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2ObjectArrayMap.values().iterator(); paramt.hasNext(); ) { localObject = (ln)paramt.next();
/* 43 */       if (this.jdField_a_of_type_ItUnimiDsiFastutilShortsShortOpenHashSet.size() > 0) {
/* 44 */         this.jdField_a_of_type_ItUnimiDsiFastutilShortsShortOpenHashSet.remove(((ln)localObject).a());
/*    */       }
/*    */ 
/* 47 */       ((ln)localObject).b(paramxq);
/* 48 */       ((ln)localObject).a(this.jdField_a_of_type_Ct.a(), this.jdField_a_of_type_Ct.a().a()); }
/*    */   }
/*    */ 
/*    */   public final void a(NetworkClientChannel paramNetworkClientChannel)
/*    */   {
/* 53 */     for (int i = 0; i < paramNetworkClientChannel.missileUpdateBuffer.getReceiveBuffer().size(); i++) {
/* 54 */       lw locallw = (lw)((RemoteMissileUpdate)paramNetworkClientChannel.missileUpdateBuffer.getReceiveBuffer().get(i)).get();
/*    */ 
/* 56 */       this.jdField_a_of_type_JavaUtilArrayList.add(locallw);
/*    */     }
/*    */   }
/*    */ 
/*    */   public final void a(short paramShort, t paramt)
/*    */   {
/* 67 */     if (!this.jdField_a_of_type_ItUnimiDsiFastutilShortsShortOpenHashSet.contains(paramShort)) {
/* 68 */       this.jdField_a_of_type_ItUnimiDsiFastutilShortsShortOpenHashSet.add(paramShort);
/* 69 */       paramt.a().missileMissingRequestBuffer.add(new RemoteShort(paramShort, false));
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     lj
 * JD-Core Version:    0.6.2
 */